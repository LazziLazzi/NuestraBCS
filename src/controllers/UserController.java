package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import repository.UserRepository;
import models.UserTableModel; 
import views.UserFormDialog;
import views.UsersView;

public class UserController {

    private UsersView view;
    private UserRepository repo;
    private UserTableModel model;
    
    public UserController(UsersView view) {
        this.view = view;
        repo = new UserRepository();
     
        //Agregar
        this.view.getBtnAdd().addActionListener(e -> {
        		openForm(null);
        });
        
        //Editar
        this.view.getBtnEdit().addActionListener(e -> {
            int row = view.getSelectedRow(); 
            if(row == -1) {
                JOptionPane.showMessageDialog(view, "Selecciona un usuario");
                return;
            }
            openForm(model.getUserAt(row));
        });
        
     	//Eliminar
        this.view.getBtnDelete().addActionListener(e -> {
            int row = view.getSelectedRow();
            if(row == -1) {
                JOptionPane.showMessageDialog(view, "Selecciona un usuario para eliminar");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(view, "¿Estas seguro de eliminar este registro?", "Confirmar eliminar", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                try {
                    repo.delete(row); // Lo borra del CSV
                    loadUsers();      // Recarga la tabla
                    JOptionPane.showMessageDialog(view, "Usuario eliminado");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
                }
            }
        });
        
    }
    
    public void loadUsers() {
    		try {
    			List<User> users = repo.getUsers();
    			
    			if(model == null) {
    				model = new UserTableModel(users);
    				view.setTableModel(model);
    			}else {
    				model.setUsers(users);
    			}
    			
    		} catch(IOException ex) {
    			JOptionPane.showMessageDialog(view, ex.getMessage());
    		}
    }
    
    private void openForm(User user) {
    		UserFormDialog dialog = new UserFormDialog(null, user);
    		dialog.setVisible(true);
    		
    		if(dialog.isSaved()) {
    			User savedUser = dialog.getUser();
    			try {
    				if(user == null) {
    					repo.save(savedUser);
    				} else {
    					int row = view.getSelectedRow();
    					repo.update(row, savedUser);
    				}
    				loadUsers();
    			} catch(Exception e) {
    				e.printStackTrace();
    				JOptionPane.showMessageDialog(view, e.getMessage());
    			}
    		}
    		
    }
   
    
}