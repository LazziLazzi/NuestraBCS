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
    }

    private class SpeciesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Captura el nombre exacto escrito en el botón (ej. "Ballena Gris")
            String nombreEspecie = e.getActionCommand();
            
            // Variables provisionales en lo que integran la base de datos
            String scientificName = "Desconocido";
            String kingdom = "Desconocido";
            String phylum = "Desconocido";
            String speciesClass = "Desconocido";
            String family = "Desconocido";
            String genus = "Desconocido";
            String description = "Descripción provisional en lo que se conecta la base de datos.";
            String bannerPath = "";

            // Consulta que une las 3 tablaS
            String sql = "SELECT e.descripcion, c.nombre_cientifico, c.reino, c.filo, c.clase, c.familia, c.genero, i.portada " +
                         "FROM Especies e " +
                         "LEFT JOIN Caracteristicas c ON e.id_especie = c.id_especie " +
                         "LEFT JOIN Imagenes i ON e.id_especie = i.id_especie " +
                         "WHERE e.nombre_especie = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, nombreEspecie);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    description = rs.getString("descripcion");
                    scientificName = rs.getString("nombre_cientifico");
                    kingdom = rs.getString("reino");
                    phylum = rs.getString("filo");
                    speciesClass = rs.getString("clase");
                    family = rs.getString("familia");
                    genus = rs.getString("genero");
                    
                    bannerPath = rs.getString("portada"); 
                } else {
                    JOptionPane.showMessageDialog(null, "Aún no hay datos en la BD para: " + nombreEspecie);
                    return; 
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al conectar con la Base de Datos.");
                return;
            }

            // Creamos la ventana enviando los datos reales extraidos de SQL
            SpeciesDetailWindow detailWindow = new SpeciesDetailWindow(
                nombreEspecie, scientificName, kingdom, phylum, speciesClass, family, genus, description, bannerPath
            );
            detailWindow.setVisible(true);
        }
    }
}