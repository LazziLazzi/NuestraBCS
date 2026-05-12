package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import config.Config;
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
        loadWindowPreferences();
        registerListener();
    }
    
    public void registerListener() {
    		
    		view.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                	saveWindowPreferences();    
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
    
    private void saveWindowPreferences() {
        Dimension size = view.getSize();
        Point point = view.getLocation();
        
        Config.set("mainwindow.width", String.valueOf(size.width));
        Config.set("mainwindow.height", String.valueOf(size.height));
        Config.set("mainwindow.x", String.valueOf(point.x));
        Config.set("mainwindow.y", String.valueOf(point.y));
    }
    
    private void loadWindowPreferences() {
        try {
            String w = Config.get("mainwindow.width");
            String h = Config.get("mainwindow.height");
            String x = Config.get("mainwindow.x");
            String y = Config.get("mainwindow.y");

            // Si existen datos guardados, lo aplica
            if (w != null && h != null) {
                view.setSize(Integer.parseInt(w), Integer.parseInt(h));
            }
            // Si existen datos guardados, lo aplica igual pero para posicion
            if (x != null && y != null) {
                view.setLocation(Integer.parseInt(x), Integer.parseInt(y));
            }
        } catch (Exception e) {
            System.out.println("Iniciando con los valores por defecto de la ventana.");
        }
    }
}
