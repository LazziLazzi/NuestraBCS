package ejercicio;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

import controllers.LoginController;
import views.RegistrationView;
import views.LoginView;
import controllers.LoginController;

public class Window extends JFrame {
	public Window() {
		setSize(400, 500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setTitle("NuestraBCS");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icon = tk.getImage("src/images/image.jpg");
		setIconImage(icon);
		
		LoginView vistaLogin = new LoginView();
		LoginController controller = new LoginController(vistaLogin);
		
		add(vistaLogin);
		
		setVisible(true);
		validate();
		repaint();
	}
}
