package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import exceptions.CredencialErrorException;
import exceptions.EmailErroreException;
import exceptions.PasswordErrorException;
import models.User;
import repository.LoginRepository;
import views.LoginView;
import views.RegistrationView;
import windows.MainWindow;
import windows.MenuWindow;

//Se encarga de validar las credenciales ingresadas, comunicarse con la base de datos 
//para autenticar al usuario y decidir a que pantalla enviarlo

public class LoginController {
	private LoginView view;
	private LoginRepository loginRepo;

    public LoginController(LoginView view) {
        this.view = view;
        this.loginRepo = new LoginRepository();

        this.view.addLoginListener(new LoginAction());
        this.view.addRegisterListener(new RegisterAction());
    }

    //Clase que se ejecuta al darle clic al botón de Iniciar Sesion
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
       
            view.clearErrors();

            try {
                String emailText = view.getEmail();
                String passText = view.getPassword();

                // Manda a validar el correo y contraseña escritos
                validateCredencial(emailText, passText);
                
                // Si todo sale bien, cierra la ventana de Login
                if (javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                    javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Clase que se ejecuta al darle clic al botón de Registrarse
    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		// Abre la pantalla de registro
        		RegistrationView registerView = new RegistrationView(); 
        		
            RegistrationController registerController = new RegistrationController(registerView);
            // Cierra la ventana actual de Login
            if (javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
            }
        }
    }
    
    
    //Valida que el correo y contraseña cumplan los formatos y existan en la Base de Datos.
    //Si todo es correcto, guarda la sesión y abre la ventana correspondiente.
    private void validateCredencial(String email, String password) 
            throws EmailErroreException, PasswordErrorException, CredencialErrorException {
        
        if(email.isEmpty()) {
            throw new EmailErroreException("El correo es obligatorio");
        } else {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!email.matches(regex)) {
                throw new EmailErroreException("Correo inválido (ej: usuario@correo.com)");
            }
        }
        
        if(password.isEmpty()) {
            throw new PasswordErrorException("Contrasenia obligatoria");
        } else if(password.length() < 8) {
            throw new PasswordErrorException("Minimo 8 digitos");
        }
        
        User user = loginRepo.login(email, password);
        
        // Checa si hay usuario
        if(user != null) {
        		//Guarda que usuario ingreso en la memoria
        		utils.Session.setUserLogged(user);
        	
            if(user.getEmail().equals("admin@gmail.com")) {
                System.out.println("Administrador inicio sesión");
                MainWindow mainWindow = new MainWindow();
                new HomeController(mainWindow);
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
