package windows;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import controllers.MenuController;
import views.MenuView;

public class MenuWindow extends JFrame{
	public MenuWindow() {
		setTitle("NuestraBCS - Menú");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        MenuView menuPanel = new MenuView();
        MenuController controlador = new MenuController(menuPanel);
        add(menuPanel);
        
        setSize(400, 500); 
        setResizable(false);
        setLocationRelativeTo(null);
        
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/BCS.png")).getImage();
            setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el icono");
        }
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, 
                    "¿Desea salir de NuestraBCS?", "Salir", 
                    JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });        
	}		
}