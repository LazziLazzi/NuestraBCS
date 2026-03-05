package ejercicio;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import views.RegistrationForm;
import views.LoginView;

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
		
		LoginView panel = new LoginView();
		add(panel);
		
		setVisible(true);
		validate();
		repaint();
	}
}
