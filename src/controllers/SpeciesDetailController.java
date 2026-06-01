package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import views.SpeciesDetailView;
import windows.NotesWindow;
import windows.SpeciesDetailWindow;

public class SpeciesDetailController {
    private SpeciesDetailView view;
    private String speciesName;
    private SpeciesDetailWindow currentWindow;
    private JFrame previousWindow;
    private NotesWindow ventanaNotasAbierta = null;

    public SpeciesDetailController(SpeciesDetailView view, SpeciesDetailWindow currentWindow, javax.swing.JFrame previousWindow, String speciesName) {
        this.view = view;
        this.currentWindow = currentWindow;
        this.previousWindow = previousWindow;
        this.speciesName = speciesName;

        this.view.addNoteListener(new OpenNotesAction());
        
        this.view.addRegresarListener(e -> {
            closeNotes(); // Cierra las notas antes de regresar
            previousWindow.setVisible(true); // Muestra la categoria oculta
            currentWindow.dispose();
        });
        
        this.currentWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                closeNotes();
            }
        });
    }
    
    private void closeNotes() {
        if (ventanaNotasAbierta != null) {
            ventanaNotasAbierta.dispose();
        }
    }

    private class OpenNotesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ventanaNotasAbierta == null || !ventanaNotasAbierta.isVisible()) {
                ventanaNotasAbierta = new NotesWindow(speciesName);
                ventanaNotasAbierta.setVisible(true);
            } else {
                ventanaNotasAbierta.toFront(); 
            }
        }
    }
}
