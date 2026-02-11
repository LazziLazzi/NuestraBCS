package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class LoginView extends JPanel {
	public LoginView() {
		
		Font fuente = new Font("Arial", Font.BOLD, 20);
		Color verde = new Color(56, 142, 60);
		Color amarillito = new Color(255,255,204);
		
		setLayout(null);
		setBackground(amarillito);
		
		//Título y sus componentes
		JLabel titulo = new JLabel("NuestraBCS"); 
		titulo.setFont(new Font("Arial", Font.BOLD, 30));
		titulo.setBounds(160,20,300,100);
		titulo.setForeground(verde);
		add(titulo);
		
		//Texto para ingresar correo
		JLabel ingreseCorreo = new JLabel("Ingrese su correo electrónico:"); 
		ingreseCorreo.setFont(new Font("Arial", Font.BOLD, 16));
		ingreseCorreo.setBounds(115,60,300,100);
		ingreseCorreo.setForeground(Color.BLACK);
		add(ingreseCorreo);
		
		//Texto para ingresar contraseña
		JLabel ingreseContrasenia = new JLabel("Ingrese su contraseña:"); 
		ingreseContrasenia.setFont(new Font("Arial", Font.BOLD, 16));
		ingreseContrasenia.setBounds(115,140,300,100);
		ingreseContrasenia.setForeground(Color.BLACK);
		add(ingreseContrasenia);
		
		//Caja de texto para ingresar correo
		JTextField correo = new JTextField();
		correo.setFont(new Font("Arial", Font.PLAIN, 18));
		correo.setBounds(110,130,280,40);
		add(correo);
		
		//Caja de texto para ingresar contraseña
		JPasswordField contrasenia = new JPasswordField();
		contrasenia.setFont(new Font("Arial", Font.PLAIN, 25));
		contrasenia.setBounds(110,210,280,40);
		add(contrasenia);
		
		//Botón de acceso y sus componentes
		JButton boton = new JButton("Acceder"); //creación del botón
		boton.setBounds(150,300,200,40); //coordenadas del botón
		boton.setBackground(verde); //color de fondo del botón
		boton.setForeground(Color.WHITE); //color de la fuente de texto del botón
		boton.setToolTipText("Da click para iniciar sesión");
		boton.setFont(fuente); //ajuste de la fuente del boton
		
		try {
			Image icono = ImageIO.read(getClass().getResource("../images/flecha.png")); //lee la imagen
			icono = icono.getScaledInstance(20, 25, Image.SCALE_SMOOTH); //escala la imagen/cambia el tamaño
			boton.setIcon(new ImageIcon(icono));
		}catch(Exception ex) {
			System.out.println("No está la imagen del icono.");
		}
		
		add(boton);
	}
}