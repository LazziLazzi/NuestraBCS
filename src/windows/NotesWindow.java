package windows;

import javax.swing.JFrame;
import controllers.NotesController;
import views.NotesView;

public class NotesWindow extends JFrame {

    public NotesWindow(String speciesName) {
        setTitle("Notas de " + speciesName);
        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        NotesView notesView = new NotesView(speciesName);
        new NotesController(notesView, this, speciesName);
        
        add(notesView);
    }
}
