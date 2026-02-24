package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import utils.AppFont;

public class LoginView extends JPanel {
	
	//Atributos
	private JTextField correo;
	private JPasswordField contrasenia;
	private JButton acceder;
	private Image fondoEscalado;
	
	//Colores
	private Color verde = new Color(56,142,60);
	private Color amarillito = new Color(255, 255, 204);
	private Font fuenteTitulo = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
	private Font general = new Font("Arial", Font.BOLD, 16);
	private Font fuenteError = new Font("Arial", Font.BOLD, 10);
	
	
	// Constructor
	public LoginView() {
		panel();
	}
	
	// Métodos
	
	private void panel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(amarillito);
		
		add(Box.createVerticalGlue()); 
		
		etiquetas();
		camposTexto();
		
		add(Box.createVerticalStrut(5)); 
		boton();
		
		add(Box.createVerticalGlue()); 
		
		cargarImagenBCS();
	}
	
	private void etiquetas() {
		// Texto del título
		JLabel titulo = new JLabel("NuestraBCS");
		titulo.setFont(AppFont.title());
		titulo.setForeground(verde);
		titulo.setAlignmentX(0.5f); 
		add(titulo);
		
		add(Box.createVerticalStrut(10));
		
		JLabel ingreseCorreo = new JLabel("Ingrese su correo electrónico:"); 
		ingreseCorreo.setFont(general);
		ingreseCorreo.setForeground(Color.BLACK);
		ingreseCorreo.setAlignmentX(0.5f);
		add(ingreseCorreo);
	}
	
	private void camposTexto() {
		JPanel panelCorreo = new JPanel();
		panelCorreo.setBackground(amarillito);
		
		correo = new JTextField(25); 
		correo.setFont(new Font("Arial", Font.PLAIN, 18));
		panelCorreo.add(correo);
		add(panelCorreo);
		
		JLabel errorCorreo = new JLabel("Correo inválido."); 
		errorCorreo.setFont(fuenteError);
		errorCorreo.setForeground(Color.RED);
		errorCorreo.setAlignmentX(0.5f);
		add(errorCorreo);
		
		add(Box.createVerticalStrut(5));
		
		JLabel ingreseContrasenia = new JLabel("Ingrese su contraseña:"); 
		ingreseContrasenia.setFont(general);
		ingreseContrasenia.setForeground(Color.BLACK);
		ingreseContrasenia.setAlignmentX(0.5f);
		add(ingreseContrasenia);
		
		JPanel panelPass = new JPanel();
		panelPass.setBackground(amarillito);
		
		contrasenia = new JPasswordField(25);
		contrasenia.setFont(new Font("Arial", Font.PLAIN, 18));
		panelPass.add(contrasenia);
		add(panelPass);
		
		JLabel errorContrasenia = new JLabel("Mínimo 8 digitos."); 
		errorContrasenia.setFont(fuenteError);
		errorContrasenia.setForeground(Color.RED);
		errorContrasenia.setAlignmentX(0.5f);
		add(errorContrasenia);
	}
	
	private void boton() {
		// Encerramos el boton en un panel para que no se estire
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(amarillito);
		
		acceder = new JButton("Acceder"); 
		acceder.setBackground(verde); 
		acceder.setForeground(Color.WHITE); 
		acceder.setToolTipText("Da click para iniciar sesión");
		acceder.setFont(general); 
		cargarIcono(acceder);
		
		/*acceder.addActionListener(e ->{
			
		}*/
		acceder.addActionListener(e -> {
	        new FormularioRegistro(); 
	    });
		
		panelBoton.add(acceder);
		add(panelBoton);
	}
	
	private void cargarIcono(JButton boton) {
		try {
			Image icono = ImageIO.read(getClass().getResource("../images/flecha.png")); 
			if(icono != null) {
				icono = icono.getScaledInstance(20, 25, Image.SCALE_SMOOTH); 
				boton.setIcon(new ImageIcon(icono));
			}
		}catch(Exception ex) {
			System.out.println("Sin imágen encontrada.");
		}
	}
	
	private void cargarImagenBCS() {
		try {
			Image fondoOriginal = ImageIO.read(getClass().getResource("../images/BCS.png")); 
			if(fondoOriginal != null) {
				fondoEscalado = fondoOriginal.getScaledInstance(140, 100, Image.SCALE_SMOOTH);
				repaint();
			}
		} catch(Exception ex) {
			System.out.println("Sin imágen encontrada.");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (fondoEscalado != null) {
	        // Se muevo un poco a la esquina
	        g.drawImage(fondoEscalado,250, 15, this); 
	    }
	}

}