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
	
	//Atributos
	private JTextField correo;
	private JPasswordField contrasenia;
	private JButton acceder;
	
	//Colores
	private Color verde = new Color(56,142,60);
	private Color amarillito = new Color(255, 255, 204);
	private Font fuenteTitulo = new Font("Arial", Font.BOLD, 30);
	private Font general = new Font("Arial", Font.BOLD, 16);
	
	//Contructor
	public LoginView() {
		panel();
		etiquetas();
		camposTexto();
		boton();
	}
	
	//Metodos
	
	/**
	 * Agrega el fondo de color al panel
	 * @Override
	 */
	private void panel() {
		setLayout(null);
		setBackground(amarillito);
	}
	
	/**
	 * Labels donde se agregan el títilo y las instrucciones para el usuario
	 * @Override
	 */
	private void etiquetas() {
		//Texto del título
		JLabel titulo = new JLabel("NuestraBCS");
		titulo.setFont(fuenteTitulo);
		titulo.setBounds(160,20,300,100);
		titulo.setForeground(verde);
		add(titulo);
		//Instrucción de ingresar correo electrónico
		JLabel ingreseCorreo = new JLabel("Ingrese su correo electrónico:"); 
		ingreseCorreo.setFont(general);
		ingreseCorreo.setBounds(115,60,300,100);
		ingreseCorreo.setForeground(Color.BLACK);
		add(ingreseCorreo);
		//Intrucción de ingresar contraseña
		JLabel ingreseContrasenia = new JLabel("Ingrese su contraseña:"); 
		ingreseContrasenia.setFont(general);
		ingreseContrasenia.setBounds(115,155,300,100);
		ingreseContrasenia.setForeground(Color.BLACK);
		add(ingreseContrasenia);
	}
	
	/**
	 * Agrega los cuadros de texto para agregar correo y agregar contraseña
	 * @Override
	 */
	private void camposTexto() {
		//Cooreo electrónico
		JTextField correo = new JTextField();
		correo.setFont(new Font("Arial", Font.PLAIN, 18));
		correo.setBounds(110,130,280,40);
		add(correo);
		
		//Contraseña
		JPasswordField contrasenia = new JPasswordField();
		contrasenia.setFont(new Font("Arial", Font.PLAIN, 25));
		contrasenia.setBounds(110,225,280,40);
		add(contrasenia);
	}
	
	/**
	 * Es el boton para acceder, o sea, iniciar sesión
	 * @override
	 */
	private void boton() {
		JButton boton = new JButton("Acceder"); //creación del botón
		boton.setBounds(150,300,200,40); //coordenadas del botón
		boton.setBackground(verde); //color de fondo del botón
		boton.setForeground(Color.WHITE); //color de la fuente de texto del botón
		boton.setToolTipText("Da click para iniciar sesión");
		boton.setFont(general); //ajuste de la fuente del boton
		cargarIcono(boton);
		add(boton);
	}
	
	/**
	 * Carga el ícono de flecha que esta en el botón acceder
	 * @Override
	 * @param boton pasa el boton al que se le agregara el ícono
	 */
	private void cargarIcono(JButton boton) {
		try {
			Image icono = ImageIO.read(getClass().getResource("../images/flecha.png")); //lee la imagen
			if(icono != null) {
				icono = icono.getScaledInstance(20, 25, Image.SCALE_SMOOTH); //escala la imagen/cambia el tamaño
				boton.setIcon(new ImageIcon(icono));
			}
		}catch(Exception ex) {
			System.out.println("No está la imagen del icono.");
		}
	}
}