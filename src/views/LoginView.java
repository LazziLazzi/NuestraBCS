package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginView extends JPanel {
	public LoginView() {
		
		Font fuente = new Font("Arial", Font.ITALIC, 20);
		
		setLayout(null);
		
		setBackground(new Color(0, 0, 0));
		
		JButton boton = new JButton("Botoncito", new ImageIcon("src/images/image.jpg")); //creacion del boton
		
		boton.setBounds(200,200,100,30); //coordenadas del boton
		
		boton.setBackground(Color.RED); //color de fondo del boton
		
		boton.setForeground(Color.WHITE); //color de la fuente de texto del boton
		
		boton.setToolTipText("Haz click aquí");
		
		boton.setFont(fuente); //ajuste de la fuente del boton
		
		try {
			Image icono = ImageIO.read(getClass().getResource("../images/image.jpg")); //lee la imagen
			
			icono = icono.getScaledInstance(50, 50, Image.SCALE_SMOOTH); //escala la imagen/cambia el tamaño
			
			boton.setIcon(new ImageIcon(icono));
			
		}catch(Exception ex) {
			System.out.println("No está la imagen del icono.");
		}
		
		add(boton);
		
	}
}