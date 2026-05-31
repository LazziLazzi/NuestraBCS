package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.SpeciesDetailView;
import windows.NotesWindow;
import windows.SpeciesDetailWindow;

public class SpeciesDetailController {
    private SpeciesDetailView view;
    private String speciesName;
    private SpeciesDetailWindow currentWindow;
    private JFrame previousWindow;

    public SpeciesDetailController(SpeciesDetailView view, SpeciesDetailWindow currentWindow, javax.swing.JFrame previousWindow, String speciesName) {
        this.view = view;
        this.currentWindow = currentWindow;
        this.previousWindow = previousWindow;
        this.speciesName = speciesName;

        this.view.addNoteListener(new OpenNotesAction());
        
        // Lógica para regresar a Categorías
        this.view.addRegresarListener(e -> {
            previousWindow.setVisible(true); // Muestra la categoría oculta
            currentWindow.dispose();
        });
    }

    private class OpenNotesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abre la nueva ventana de notas cuando se hace clic en el botón
            NotesWindow notesWindow = new NotesWindow(speciesName);
            notesWindow.setVisible(true);
        }
    }
}
