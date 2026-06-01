package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import repository.UserRepository;
import models.UserTableModel; 
import services.PDFExporter;
import views.UserFormDialog;
import views.UsersView;


//Esta clase es importante porque sirve como lazo entre userview y el userrepository
public class UserController {
	
    private UsersView view; 
    private UserRepository repo;  //Sirve para las consultas sql
    private UserTableModel model;
    private PDFExporter pdfExporter;
    
    public UserController(UsersView view) {
        this.view = view;
        repo = new UserRepository();
        this.pdfExporter = new PDFExporter();
     
        //Agregar
        this.view.getBtnAdd().addActionListener(e -> {
        		openForm(null);
        });
        
        //Editar
        this.view.getBtnEdit().addActionListener(e -> {
            int row = view.getSelectedRow(); 
            if(row == -1) { //Si no encuentra un usuario
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
                		//Quita el usuario seleccionado
                		User userToDelete = model.getUserAt(row);
                		//Elimina el usuario de la base de datos usando el id
                    repo.delete(userToDelete.getId()); 
                    loadUsers();      
                    JOptionPane.showMessageDialog(view, "Usuario eliminado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
                }
            }
        });
        
        //Exportar a pdf
        this.view.getBtnPdf().addActionListener(e -> generatePdf());
    }
    
    //Carga de usuarios
    public void loadUsers() {
    		try {
    			//Trae toda la lista completa de usuarios desde sql
    			List<User> users = repo.getUsers();
    			
    			if(model == null) {
    				model = new UserTableModel(users);
    				view.setTableModel(model);
    			}else {
    				//Se le da la informacion ala tabla
    				model.setUsers(users);
    			}
    			
    		} catch(Exception ex) {
    			JOptionPane.showMessageDialog(view, ex.getMessage());
    		}
    }
    
    //Abre la ventana emergente para crear o editar un usuario
    private void openForm(User user) {
    		//Crea 
    		UserFormDialog dialog = new UserFormDialog(null, user);
    		dialog.setVisible(true);
    		
    		
    		if(dialog.isSaved()) {
    			//Guarda los datos del usuario que escribio en los campos
    			User savedUser = dialog.getUser();
    			try {
    				if(user == null) {
    					repo.save(savedUser);
    				} else {
                    savedUser.setId(user.getId());
    					repo.update(savedUser);
    				}
    				loadUsers();
    			} catch(Exception e) {
    				e.printStackTrace();
    				JOptionPane.showMessageDialog(view, e.getMessage());
    			}
    		}
    		
    }
    
    // Metodopara la exportacion
    public void generatePdf() {
        File file = view.selectPdfFile();
        
        //Si el usuario cerro o cancelo la ventana
        if(file == null) {
            return; 
        }
        
        try {
            //Se mandan los datos
            pdfExporter.exportUsers(repo.getUsers(), file);
            
            //Si la compu deja, abre el pdf d una
            if(Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al exportar el PDF: " + ex.getMessage());
        }
    }
   
    
}