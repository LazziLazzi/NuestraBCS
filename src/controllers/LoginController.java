package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import views.CredencialErrorException;
import views.EmailErroreException;
import views.LoginView;
import views.MainWindow;
import views.MenuWindow;
import views.PasswordErrorException;
import views.RegistrationView;

public class LoginController {
	private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

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
        
        // Verifica si es el usuario normal
        if(correo.equals("usuario@gmail.com") && contrasenia.equals("12345678")) {
            System.out.println("Usuario inicio sesion");
            MenuWindow menu = new MenuWindow(); 
            menu.setVisible(true);
        } 
        else if(correo.equals("admin@gmail.com") && contrasenia.equals("12345678")) {
            System.out.println("Administrador inicio sesion");
            MainWindow mainWindow = new MainWindow();
            new HomeController(mainWindow);
            mainWindow.btnUsers.doClick();
            mainWindow.setVisible(true);
        } 
        else {
            throw new CredencialErrorException("Correo o contraseña incorrectos");
        }
    }
}
