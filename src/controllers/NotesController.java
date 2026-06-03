package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        // listener de cuando el usuario le pica a cerrar ventana (la "x")
        this.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
    }

    // Método auxiliar para centralizar la advertencia de salida
    private void confirmarSalida() {
        int option = JOptionPane.showConfirmDialog(window, 
            "Se borrará lo que no hayas guardado, ¿estás seguro que deseas salir?", 
            "Confirmar salida", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
        if (option == JOptionPane.YES_OPTION) {
            window.dispose(); // Cierra solo la ventana de notas
        }
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
        if (Session.getUserLogged() == null || idEspecie == -1) {
        		return;
        }
        
        String sql = "SELECT contenido FROM Notas WHERE id_usuario = ? AND id_especie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, Session.getUserLogged().getId()); 
            stmt.setInt(2, idEspecie);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                view.setNotesContent(rs.getString("contenido"));
            } else {
                view.setNotesContent("Notas de " + Session.getUserLogged().getUsername() + ":\n\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class CancelAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            confirmarSalida();
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
            
            String sql = "INSERT INTO Notas (id_usuario, id_especie, contenido) VALUES (?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE contenido = ?";
                         
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, Session.getUserLogged().getId());
                stmt.setInt(2, idEspecie);
                stmt.setString(3, contenido);
                stmt.setString(4, contenido); 
                stmt.executeUpdate();
                window.dispose(); 
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(window, "Error al guardar en BD.");
            }
        }
    }
}