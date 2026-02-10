package ejercicio;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import views.LoginView;

public class Ventana extends JFrame {
	public Ventana() {
		setSize(500, 500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(500, 200);
		//setBounds(getBounds());
		
		setResizable(false);
		setTitle("Silencio Gil");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("C:\\Users\\lalac\\Documents\\Universidad\\4to semestre\\Programación 3\\Prog Unidad 1\\ejercicioDeEjemplo\\src\\images\\image.jpg");
		setIconImage(icono);
		
		LoginView panelito = new LoginView();
		add(panelito);
		
		setVisible(true);
		validate();
		repaint();
	}
}
