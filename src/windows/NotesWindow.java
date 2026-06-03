package windows;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import controllers.NotesController;
import views.NotesView;

public class NotesWindow extends JFrame {

    public NotesWindow(String speciesName) {
        setTitle("Notas de " + speciesName);
        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/BCS.png")).getImage();
            setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el icono");
        }
        
        NotesView notesView = new NotesView(speciesName);
        new NotesController(notesView, this, speciesName);
        
        add(notesView);
    }
}