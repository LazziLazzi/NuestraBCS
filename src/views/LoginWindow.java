package views;

import javax.swing.JFrame;
import java.awt.Dimension;

public class LoginWindow extends JFrame{
		public LoginWindow() {
			setTitle("NuestraBCS - Iniciar Sesión");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	        LoginView loginPanel = new LoginView();
	        add(loginPanel);
	        
	        setSize(400, 500); 
	        setLocationRelativeTo(null);
		}
}
