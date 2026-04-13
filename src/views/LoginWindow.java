package views;
import controllers.LoginController;
import javax.swing.JFrame;
import java.awt.Dimension;

public class LoginWindow extends JFrame{
	public LoginWindow() {
		setTitle("NuestraBCS - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        LoginView loginPanel = new LoginView();
        LoginController controlador = new LoginController(loginPanel);
        add(loginPanel);
        
        setSize(400, 500); 
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int option = javax.swing.JOptionPane.showConfirmDialog(null, 
                    "¿Desea salir de NuestraBCS?", "Salir", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
                if (option == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });        
	}		
}
