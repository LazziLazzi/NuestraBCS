package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.CredencialErrorException;
import views.EmailErroreException;
import views.LoginView;
import views.MainWindow;
import views.PasswordErrorException;
import views.RegistrationView;

public class LoginController {
	private LoginView view;
	//private RegistrationView registrationView;

    public LoginController(LoginView view) {
        this.view = view;

        // Asignamos los listener sus acciones
        this.view.addLoginListener(new LoginAction());
        this.view.addRegisterListener(new RegisterAction());
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // ocultamos los mensajes de erroes
            view.clearErrors();

            try {
                String correoText = view.getEmail();
                String passText = view.getPassword();

                //La logica
                validateCredencial(correoText, passText);

                //No hubo errores todo bien
                view.showSuccessMessage();
                
                //Reiniciamos la ventana
                new LoginView();
                
                //Cerramos la ventana actual
                if (javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                    javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
                }

            } catch (EmailErroreException ex) {
                // Atrapa el error de correo 
                view.showErrorEmail(ex.getMessage());
                
            } catch (PasswordErrorException ex) {
                //Atrapa el error de contrasenia
                view.showErrorPass(ex.getMessage());
                
            } catch (CredencialErrorException ex) {
                // Atrapa el error general
                view.showErrorEmail(ex.getMessage());
                view.showErrorPass(ex.getMessage());
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
        
        // Inicio de sesion del usuario
        if(correo.equals("usuario@gmail.com") || contrasenia.equals("12345678")) {
        		System.out.println("Usuaro inicio sesion");
        } else {
        		throw new CredencialErrorException("Algo no coincide");
        }
        
        // Inicio de sesion del administrador 
        if(correo.equals("usuario@gmail.com") || contrasenia.equals("12345678")) {
    			System.out.println("Administrador inicio sesion");
			//Abre la ventana
            MainWindow mainWindow = new MainWindow();
            
            // Conecta el HomeController a la ventana
            new HomeController(mainWindow);
            
            // Forzamos a que inicie en la vista de usuarios
            mainWindow.btnUsers.doClick();

	    } else {
	    		throw new CredencialErrorException("Algo no coincide");
	    }
    }
}
