package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import config.DatabaseConnection;
import utils.Session;
import views.NotesView;
import windows.NotesWindow;

public class NotesController {
    private NotesView view;
    private NotesWindow window;
    private String speciesName;
    private int idEspecie = -1;

    public NotesController(NotesView view, NotesWindow window, String speciesName) {
        this.view = view;
        this.window = window;
        this.speciesName = speciesName;

        getIdSpecies();
        loadNotes();
        
        this.view.addCancelListener(new CancelAction());
        this.view.addSaveListener(new SaveAction());
    }

    //Trae la el Id dependiendo del nombre de la especie
    private void getIdSpecies() {
        String sql = "SELECT id_especie FROM Especies WHERE nombre_especie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, speciesName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idEspecie = rs.getInt("id_especie");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Metodo que carga las notas
    private void loadNotes() {
        // Esto evita que el programa se rompa si no encontro usuario o si no existe la especie
        if (Session.getUserLogged() == null || idEspecie == -1) {
        		return;
        }
        
        String sql = "SELECT contenido FROM Notas WHERE id_usuario = ? AND id_especie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            //Cambia los ? por los datos traidos de la consulta
            stmt.setInt(1, Session.getUserLogged().getId()); 
            stmt.setInt(2, idEspecie);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Si ya hania notas guardadas se las muestra
                view.setNotesContent(rs.getString("contenido"));
            } else {
                // Si es la primera vez se muestra nombre
                view.setNotesContent("Notas de " + Session.getUserLogged().getUsername() + ":\n\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class CancelAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose(); // se cierra sin guardar
        }
    }

    //Guarda las notas escritas
    private class SaveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Session.getUserLogged() == null || idEspecie == -1) {
                JOptionPane.showMessageDialog(window, "Error: No hay sesión activa.");
                return;
            }

            String contenido = view.getNotesContent();
            
            // ON DUPLICATE KEY UPDATE hace que si ya existe la nota, solo sobreescriba el texto
            String sql = "INSERT INTO Notas (id_usuario, id_especie, contenido) VALUES (?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE contenido = ?";
                         
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, Session.getUserLogged().getId());
                stmt.setInt(2, idEspecie);
                stmt.setString(3, contenido);
                stmt.setString(4, contenido); // Variable para el UPDATE
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(window, "Notas guardadas correctamente.");
                window.dispose(); 
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(window, "Error al guardar en BD.");
            }
        }
    }
}