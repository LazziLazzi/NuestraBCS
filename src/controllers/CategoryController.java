package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import config.DatabaseConnection; 
import views.CategoryView;
import windows.CategoryWindow;
import windows.SpeciesDetailWindow;


//Controller de la seccion menu dependiendo de que categoria elegiste
public class CategoryController {
    private CategoryView view;
    private CategoryWindow currentWindow;
    private JFrame previousWindow;

    public CategoryController(CategoryView view, CategoryWindow currentWindow, JFrame previousWindow) {
        this.view = view;
        this.currentWindow = currentWindow;
        this.previousWindow = previousWindow;

        this.view.addSpeciesListener(new SpeciesAction());
        
        // Lógica del botón de regresar
        this.view.addComeListener(e -> {
            previousWindow.setVisible(true); // se nuestra el menú de nuevo
            currentWindow.dispose();
        });
        
        uploadCover();
    }
    
	// Busca las imagenes de la portada
    private void uploadCover() {
    		String sql = "SELECT e.nombre_especie, i.portada " +
                "FROM Especies e " +
                "INNER JOIN Imagenes i ON e.id_especie = i.id_especie";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
            		view.setFront(rs.getString("nombre_especie"), rs.getString("portada"));
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar portadas: " + ex.getMessage());
        }
    }
    
    // Clase que actua cuando se le da clic a una de las tarjetas de especie 
    private class SpeciesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	String nameSpecies = e.getActionCommand();
            
        		// Consulta que trae toda la información técnica, descripción y el banner
            String sql = "SELECT e.descripcion, c.nombre_cientifico, c.reino, c.filo, c.clase, c.familia, c.genero, i.banner " +
                         "FROM Especies e " +
                         "LEFT JOIN Caracteristicas c ON e.id_especie = c.id_especie " +
                         "LEFT JOIN Imagenes i ON e.id_especie = i.id_especie " +
                         "WHERE e.nombre_especie = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, nameSpecies);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    //Captura de datos
                    String desc = rs.getString("descripcion");
                    String sci = rs.getString("nombre_cientifico");
                    String rei = rs.getString("reino");
                    String fil = rs.getString("filo");
                    String cla = rs.getString("clase");
                    String fam = rs.getString("familia");
                    String gen = rs.getString("genero");
                    String rutaBanner = rs.getString("banner"); 
                    
                    // Crea la nueva ventana de detalles pasándole toda la información
                    SpeciesDetailWindow detailWindow = new SpeciesDetailWindow(
                        nameSpecies, sci, rei, fil, cla, fam, gen, desc, rutaBanner
                    );
                    
                    // Arranca el controlador de esa nueva ventana
                    new SpeciesDetailController(detailWindow.getDetailPanel(), detailWindow, currentWindow, nameSpecies);
                    
                    detailWindow.setVisible(true);
                    currentWindow.setVisible(false);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron datos.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}