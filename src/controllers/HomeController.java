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
import windows.MainWindow;

public class HomeController {
	private MainWindow view; 
    private UserRepository repository;
    private UserController userController; 

    public HomeController(MainWindow view) {
        this.view = view;
        loadWindowPreferences();
        registerListener();
        showUsers();
    }
    
    public void registerListener() {
    		
    		view.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                	saveWindowPreferences();    
                	handleClose();
                }
            });
    }

    //Carga los usuarios y los muestra
    private void showUsers() {
        if(userController == null) {
            userController = new UserController(view.usersPanel);
        }
        userController.loadUsers();
    }
    
    private void handleClose() {
        view.dispose();
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
