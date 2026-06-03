package windows;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import controllers.CategoryController;
import views.CategoryView;

public class CategoryWindow extends JFrame {
    
    public CategoryWindow(JFrame previousWindow, String title, String[] items) {
        setTitle("NuestraBCS - " + title);
        setSize(600, 650); 
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/BCS.png")).getImage();
            setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el icono");
        }
        
        CategoryView categoryPanel = new CategoryView(title, items);
        CategoryController controller = new CategoryController(categoryPanel, this, previousWindow);
        add(categoryPanel);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int option = javax.swing.JOptionPane.showConfirmDialog(null, 
                    "¿Desea salir de NuestraBCS?", "Salir", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
                if (option == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0); // para cerrar toda la aplicación por completo
                }
            }
        });        
    }
}
