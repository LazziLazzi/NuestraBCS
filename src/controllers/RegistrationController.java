package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import views.RegistrationView;
import views.HomeView;
import views.LoginWindow;
import views.MainWindow;
import models.User;
import repository.UserRepository;

public class RegistrationController {
	private RegistrationView view;

    public RegistrationController(RegistrationView view) {
        this.view = view;

        //La logica a los controles
        this.view.addConfirmListener(new ConfirmAction());
        this.view.addBackListener(new BackAction());

        // Para cerrar la ventana
        this.view.setWindowClosingListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (view.showCloseConfirmation() == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Validaciones
        this.view.addNameValidator(this::validateNombre);
        this.view.addLastNamePValidator(this::validateApellidoP);
        this.view.addLastNameMValidator(this::validateApellidoM);
        this.view.addNameUserValidator(this::validateNombreUsuario);
        this.view.addDateValidator(this::validateFechaNacimiento);
        this.view.addEmailValidator(this::validateCorreo);
        this.view.addPasswordValidator(this::validateContrasenia);
        this.view.addConfirmPasswordValidator(this::validateConfirmarContrasenia);
    }

    private class ConfirmAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (validateNombre() && validateApellidoP() && validateApellidoM() && 
                    validateNombreUsuario() && validateFechaNacimiento() && 
                    validateCorreo() && validateContrasenia() && validateConfirmarContrasenia()) { 
                    
                //Extrae los datos 
                String name = view.getNameText(); 
                String lastNameP = view.getLastNamePText();
                String lastNameM = view.getLastNameMText();
                String username = view.getNameUserText(); 
                String birthDate = view.getDateText();
                String email = view.getEmailText(); 
                String password = view.getPasswordText();
                String gender = view.getGenderSelected();

                //Crea el objeto user con los datos llamados
                User newUser = new User(name, lastNameP, lastNameM, username, birthDate, email, password, gender, null);

                //Lo guarda en el csv
                UserRepository repository = new UserRepository();
                try {
                    repository.save(newUser);
                    javax.swing.JOptionPane.showMessageDialog(null, "Usuario registrado");
                    
                    // Abre la ventana de Login nuevamente
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                    
                    // Cierra la ventana de registro actual
                    view.dispose();
                  
                    view.dispose();

                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        }
    }

    private class BackAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.showBackConfirmation() == javax.swing.JOptionPane.YES_OPTION) {
                LoginWindow login = new LoginWindow();
                login.setVisible(true); 
                view.dispose();
            }
        }
    }

    private boolean validateAll() {
        boolean v1 = validateNombre();
        boolean v2 = validateApellidoP();
        boolean v3 = validateApellidoM(); 
        boolean v4 = validateNombreUsuario(); 
        boolean v5 = validateFechaNacimiento(); 
        boolean v6 = validateCorreo();
        boolean v7 = validateContrasenia();
        boolean v8 = validateConfirmarContrasenia();
                
        return v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8;
    }

    private boolean validateNombre() {
        if (view.getNameText().trim().isEmpty()) { 
            view.showNameError("El nombre es obligatorio"); 
            return false; 
        }
        view.showNameError(" "); 
        return true;
    }

    private boolean validateApellidoP() {
        if (view.getLastNamePText().trim().isEmpty()) { 
            view.showLastNamePError("El apellido paterno es obligatorio"); 
            return false; 
        }
        view.showLastNamePError(" "); 
        return true;
    }
    
    private boolean validateApellidoM() {
        if (view.getLastNameMText().trim().isEmpty()) { 
            view.showLastNameMError("El apellido materno es obligatorio"); 
            return false; 
        }
        view.showLastNameMError(" "); 
        return true;
    }

    private boolean validateNombreUsuario() {
        String user = view.getNameUserText().trim();
        if (user.isEmpty()) {
            view.showNameUserError("El usuario es obligatorio"); 
            return false; 
        } 
        else if (user.contains(" ")) { 
            view.showNameUserError("No debe contener espacios"); 
            return false; 
        } 
        else if (user.length() < 4) { 
            view.showNameUserError("Mínimo 4 caracteres"); 
            return false; 
        }
        
        UserRepository repo = new UserRepository();
        if (repo.userExists(user)) {
            view.showNameUserError("Ese nombre de usuario ya existe"); 
            return false; // Detiene el registro porque ya esta registrado
        }
        
        view.showNameUserError(" "); 
        return true;
    }

    private boolean validateFechaNacimiento() {
    		String fecha = view.getDateText(); 
        
        if (fecha.contains("_")) { 
            view.showDateError("Complete la fecha (DD/MM/AAAA)"); 
            return false; 
        } 
        view.showDateError(" "); 
        return true;
    }

    private boolean validateCorreo() {
    		String email = view.getEmailText().trim();
        if (email.isEmpty()) { 
            view.showEmailError("El correo es obligatorio"); 
            return false; 
        } 
        else if (!email.contains("@") || !email.contains(".")) { 
            view.showEmailError("Ingrese un correo válido"); 
            return false; 
        }
       
        UserRepository repo = new UserRepository();
        if (repo.emailExists(email)) {
            view.showEmailError("Este correo ya está registrado"); 
            return false; // Detiene el registro porque ya esta registrado
        }

        view.showEmailError(" "); 
        return true;
    }

    private boolean validateContrasenia() {
        if (view.getPasswordText().length() < 8) { 
            view.showPasswordError("Mínimo 8 caracteres"); 
            return false; 
        }
        view.showPasswordError(" ");
        validateConfirmarContrasenia(); // Valida que sean iguales
        return true;
    }

    private boolean validateConfirmarContrasenia() {
        String pass1 = view.getPasswordText();
        String pass2 = view.getConfirmPasswordText();
        
        if (pass2.isEmpty()) { 
            view.showConfirmPasswordError("Confirme su contraseña"); 
            return false; 
        } 
        else if (!pass1.equals(pass2)) { 
            view.showConfirmPasswordError("Las contraseñas no coinciden"); 
            return false; 
        }
        view.showConfirmPasswordError(" "); 
        return true;
    }
}
