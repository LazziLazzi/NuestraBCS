package controllers;

import java.io.IOException;
import java.util.List;
import models.User;
import models.UserTableModel;
import repository.UserRepository;
import views.HomeView;

public class HomeController {
    private HomeView view;
    private UserRepository repository;

    public HomeController(HomeView view) {
        this.view = view;
        this.repository = new UserRepository();

        //Listener para el boton de recargar
        this.view.addReloadListener(e -> refreshTable());
        
        //Carga inicial al abrir la ventana
        refreshTable();
    } 

    private void refreshTable() {
		try {
			List<User> users = repository.getUsers();
			//Crea el modelo con los datos del CSV
			UserTableModel model = new UserTableModel(users);
			view.setTableModel(model);
		}catch(IOException ex){
			javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
		}
    }
}
