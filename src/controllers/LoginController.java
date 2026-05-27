package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.User;
import repository.LoginRepository;
import views.CredencialErrorException;
import views.EmailErroreException;
import views.LoginView;
import views.MainWindow;
import views.MenuWindow;
import views.PasswordErrorException;
import views.RegistrationView;

public class LoginController {
	private LoginView view;
	private LoginRepository loginRepo;

    public LoginController(LoginView view) {
        this.view = view;
        this.loginRepo = new LoginRepository();

        this.view.addLoginListener(new LoginAction());
        this.view.addRegisterListener(new RegisterAction());
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
       
            view.clearErrors();

            try {
                String correoText = view.getEmail();
                String passText = view.getPassword();

                
                validateCredencial(correoText, passText);

               
                if (javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                    javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
                }
                
             
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		RegistrationView registerView = new RegistrationView(); 
          
            RegistrationController registerController = new RegistrationController(registerView);
            
            if (javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
            }
        }
    }

    private void validateCredencial(String correo, String contrasenia) 
            throws EmailErroreException, PasswordErrorException, CredencialErrorException {
        
        if(correo.isEmpty()) {
            throw new EmailErroreException("El correo es obligatorio");
        } else if(!correo.contains("@") || !correo.contains(".")) {
            throw new EmailErroreException("Correo invalido");
        }
        
        if(contrasenia.isEmpty()) {
            throw new PasswordErrorException("Contrasenia obligatoria");
        } else if(contrasenia.length() < 8) {
            throw new PasswordErrorException("Minimo 8 digitos");
        }
        
        User user = loginRepo.login(correo, contrasenia);
        
        // Checa si hay usuario
        if(user != null) {
            if(user.getEmail().equals("admin@gmail.com")) {
                System.out.println("Administrador inicio sesión");
                MainWindow mainWindow = new MainWindow();
                new HomeController(mainWindow);
                mainWindow.btnUsers.doClick();
                mainWindow.setVisible(true);
            } else {
                System.out.println("Usuario normal inicio sesión");
                MenuWindow menu = new MenuWindow(); 
                menu.setVisible(true);
            }
            
        } else {
            //No existe en el sql
            throw new CredencialErrorException("Correo o contraseña incorrectos");
        }
    }
}
