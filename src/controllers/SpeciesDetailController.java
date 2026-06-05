package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import views.SpeciesDetailView;
import windows.NotesWindow;
import windows.SpeciesDetailWindow;

//Se encarga de manejar la tarjeta informativa de un animal/planta especifico,
//permitiendo abrir la ventana de notas o regresar a la categoria anterior

public class SpeciesDetailController {
    private SpeciesDetailView view;
    private String speciesName;
    private SpeciesDetailWindow currentWindow;
    private JFrame previousWindow;
    private NotesWindow windowNotesOpen = null;

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
    
    //Para destruir la ventana de notas si es que el usuario la dejo abierta
    private void closeNotes() {
        if (windowNotesOpen != null) {
            windowNotesOpen.dispose();
        }
    }
    
    // Clase que se ejecuta al darle clic al boton de Nota
    private class OpenNotesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (windowNotesOpen == null || !windowNotesOpen.isVisible()) {
                windowNotesOpen = new NotesWindow(speciesName);
                windowNotesOpen.setVisible(true);
            } else {
                windowNotesOpen.toFront(); 
            }
        }
    }
}
