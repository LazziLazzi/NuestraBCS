package controllers;

import repository.UserRepository;
import models.UserTableModel;
import views.UserFormDialog;
import views.UsersView;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class UserController {

    private UsersView view;
    private UserRepository repo;
    private UserTableModel model;
    
    public UserController(UsersView view) {
        this.view = view;
        this.repo = new UserRepository();
     
        //El boton agregar abre el userformdialog
        view.getBtnAdd().addActionListener(e -> {
            //Busca la ventana principal para central el Dialog sobre ella
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            UserFormDialog form = new UserFormDialog(parentFrame, null);
            form.setVisible(true);
        });
        
        //Boton editar
        view.getBtnEdit().addActionListener(e -> {
            System.out.println("Editar laik");
        });

        //Boton eliminar
        view.getBtnDelete().addActionListener(e -> {
            System.out.println("Borrar dislaik");
        });
    }
}