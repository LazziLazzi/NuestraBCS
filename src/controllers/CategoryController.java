package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import config.DatabaseConnection; 
import views.CategoryView;
import views.SpeciesDetailWindow;

public class CategoryController {
    private CategoryView view;

    public CategoryController(CategoryView view) {
        this.view = view;
        // Le asignamos el listener a todos los botones guardados en la lista de la vista
        this.view.addSpeciesListener(new SpeciesAction());
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
            		view.setPortada(rs.getString("nombre_especie"), rs.getString("portada"));
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar portadas: " + ex.getMessage());
        }
    }

    private class SpeciesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
String nombreEspecie = e.getActionCommand();
            
            // Consulta que trae ambas imagenes
            String sql = "SELECT e.descripcion, c.nombre_cientifico, c.reino, c.filo, c.clase, c.familia, c.genero, i.banner " +
                         "FROM Especies e " +
                         "LEFT JOIN Caracteristicas c ON e.id_especie = c.id_especie " +
                         "LEFT JOIN Imagenes i ON e.id_especie = i.id_especie " +
                         "WHERE e.nombre_especie = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, nombreEspecie);
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
                    
                    
                    SpeciesDetailWindow detailWindow = new SpeciesDetailWindow(
                        nombreEspecie, sci, rei, fil, cla, fam, gen, desc, rutaBanner
                    );
                    detailWindow.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron datos.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}