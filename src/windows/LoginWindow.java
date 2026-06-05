package windows;

import controllers.LoginController;
import views.LoginView;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame{
	public LoginWindow() {
		setTitle("NuestraBCS - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        LoginView loginPanel = new LoginView();
        LoginController controlador = new LoginController(loginPanel);
        add(loginPanel);
        
        setSize(400, 500); 
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Cargar la imagen del icono
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
