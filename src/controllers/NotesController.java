package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import views.NotesView;
import windows.NotesWindow;

public class NotesController {
    private NotesView view;
    private NotesWindow window;
    private String speciesName;

    public NotesController(NotesView view, NotesWindow window, String speciesName) {
        this.view = view;
        this.window = window;
        this.speciesName = speciesName;

        this.view.addCancelarListener(new CancelarAction());
        this.view.addGuardarListener(new GuardarAction());
    }

    private class CancelarAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose(); // se cierra si guardar
        }
    }

    private class GuardarAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String contenido = view.getNotesContent();
            
            // aqui es para hacer el insert en la base de datos
            System.out.println("Guardando en BD para " + speciesName + ": " + contenido);
            
            JOptionPane.showMessageDialog(window, "Notas guardadas correctamente.");
            window.dispose(); // Cierra la ventana con la info ya guardada
        }
    }
}