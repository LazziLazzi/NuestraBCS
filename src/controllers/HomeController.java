package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import models.User;
import models.UserTableModel;
import repository.UserRepository;
import views.HomeView;
import views.MainWindow;

public class HomeController {
	private MainWindow view; // Cambiamos HomeView por MainWindow
    private UserRepository repository;
    private UserController userController; // Agregamos esta variable que faltaba

    public HomeController(MainWindow view) {
        this.view = view;
        registerListener();
    }
    
    public void registerListener() {
    		
    		view.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    handleClose();
                }
            });
            
            view.btnUsers.addActionListener(e -> {
                showUsers();
            });
            
            view.btnHome.addActionListener(e -> {
                view.showView(MainWindow.HOME);
                updateMenuState(MainWindow.HOME);
            });
    }

    private void showUsers() {
        if(userController == null) {
            userController = new UserController(view.usersPanel);
        }
        userController.loadUsers();
        
        view.showView(MainWindow.USERS);
        updateMenuState(MainWindow.USERS);
    }
    
    private void handleClose() {
        view.dispose();
    }
    
    private void updateMenuState(String viewName) {
        view.btnUsers.setEnabled(!viewName.equals(MainWindow.USERS));
        view.btnHome.setEnabled(!viewName.equals(MainWindow.HOME));
    }
}
