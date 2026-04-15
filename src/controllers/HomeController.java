package controllers;

import models.User;
import repository.UserRepository;
import views.HomeView;
import java.io.IOException;
import java.util.List;

public class HomeController {
    private HomeView view;
    private UserRepository repository;

    public HomeController(HomeView view) {
        this.view = view;
        this.repository = new UserRepository();

        //Se presioa el boton carga los datos
        this.view.addLoadListener(e -> loadUsers());
        
        // Cargamos la ventana
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<User> users = repository.getUsers();
            StringBuilder sb = new StringBuilder();
         
            sb.append("Nombre | Usuario | Genero | Email | Cumpleanios\n");
            sb.append("-------------------------------------------------------\n");
            
            for (User u : users) {
                sb.append(u.getName()).append(" ")
                  .append(" | ").append(u.getUsername())
                  .append(" | ").append(u.getGender()) 
                  .append(" | ").append(u.getEmail()).append("\n");
            }
            view.setUsersText(sb.toString());

        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error en la carga: " + ex.getMessage());
        }
    }
}
