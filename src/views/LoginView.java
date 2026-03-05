package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import utils.AppFont;

public class LoginView extends JPanel {
	
	//Atributos
	private JTextField email;
	private JPasswordField password;
	private JButton access;
	private JButton registerButton;
	private JLabel errorEmail;
	private JLabel errorPass;
	private Image stretchedBackground;
	
	//Colores
	private Color green = new Color(56,142,60);
	private Color yellow = new Color(255, 255, 204);
	private Color darkGreen = new Color(0, 102, 0);
	
	//Fuentes
	private Font titleFont = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
	private Font generalFont = new Font("Arial", Font.BOLD, 16);
	private Font errorFont = new Font("Arial", Font.BOLD, 10);
	
	
	
	// Constructor
	public LoginView() {
		panel();
	}
	
	// Métodos
	private void panel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(yellow);
		
		add(Box.createVerticalGlue()); 
		
		titleLabel();
		add(Box.createRigidArea(new Dimension(50, 20)));
		textFields();
		accessButton();
		
		add(Box.createVerticalStrut(5)); 
		registrationButton();
		
		add(Box.createVerticalGlue()); 
		
		uploadBCSImage();
	}
	
	private void titleLabel() {
		// Texto del título
		JLabel title = new JLabel("NuestraBCS");
		title.setFont(AppFont.title());
		title.setForeground(green);
		title.setAlignmentX(0.5f); 
		add(title);
		
		add(Box.createVerticalStrut(10));
	}
	
	private void textFields() {
		JLabel enterEmail = new JLabel("Ingrese su correo electrónico:"); 
		enterEmail.setFont(generalFont);
		enterEmail.setForeground(Color.BLACK);
		enterEmail.setAlignmentX(0.5f);
		add(enterEmail);
		
		JPanel panelEmail = new JPanel();
		panelEmail.setBackground(yellow);
		
		email = new JTextField(25); 
		email.setFont(new Font("Arial", Font.PLAIN, 18));
		panelEmail.add(email);
		add(panelEmail);
		
		JPanel panelErrorEmail = new JPanel();
		panelErrorEmail.setBackground(yellow);
		errorEmail = new JLabel("Correo inválido."); 
		errorEmail.setVisible(false);
		errorEmail.setFont(errorFont);
		errorEmail.setForeground(Color.RED);
		errorEmail.setAlignmentX(0.5f);
		panelErrorEmail.add(errorEmail);
		add(errorEmail);
		
		add(Box.createRigidArea(new Dimension(50, 30)));
		
		add(Box.createVerticalStrut(5));
		
		JLabel enterPass = new JLabel("Ingrese su contraseña:"); 
		enterPass.setFont(generalFont);
		enterPass.setForeground(Color.BLACK);
		enterPass.setAlignmentX(0.5f);
		add(enterPass);
		
		JPanel panelPass = new JPanel();
		panelPass.setBackground(yellow);
		
		password = new JPasswordField(25);
		password.setFont(new Font("Arial", Font.PLAIN, 18));
		panelPass.add(password);
		add(panelPass);
		
		errorPass = new JLabel("Mínimo 8 digitos."); 
		errorPass.setVisible(false);
		errorPass.setFont(errorFont);
		errorPass.setForeground(Color.RED);
		errorPass.setAlignmentX(0.5f);
		add(errorPass);
		
		add(Box.createRigidArea(new Dimension(50, 20)));
	}
	
	private void accessButton() {
		//Encerramos el boton en un panel para que no se estire
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(yellow);
		
		access = new JButton("Acceder"); 
		access.setBackground(green); 
		access.setForeground(Color.WHITE); 
		access.setToolTipText("Da click para iniciar sesión");
		access.setFont(generalFont); 
		
		uploadIcon(access);
		
		buttonPanel.add(access);
		add(buttonPanel);
		
//		acceder.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Se hizo click en el botón acceder");
//				//se pone null si el panel en el que se esta escribiendo
//				JOptionPane.showMessageDialog(null, 
//						"Si desea continuar, ingrese sus datos para acceder a su cuenta",
//						"Atención",
//						JOptionPane.INFORMATION_MESSAGE
//				);
//			}
//		});
		
		access.addActionListener(e -> {
			boolean error = false;
			
			if(email.getText().isEmpty()) {
				errorEmail.setVisible(true);
				error = true;
			} else {
				errorEmail.setVisible(false);
			}
			
			if (password.getPassword().length == 0) {
				errorPass.setVisible(true);
				error = true;
			} else {
				errorPass.setVisible(false);
			}
			
			if(error) {
				popupWindow();
			}
			
		});
	}
	
	private void popupWindow() {
		JOptionPane.showMessageDialog(
				null,
				"Si desea continuar, ingrese sus datos para acceder a su cuenta.",
				"ATENCIÓN",
				JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Botón que manda hacia el panel de registro. El método también tiene
	 * el JLabel que indica para que sirve el botón.
	 */
	private void registrationButton() {
		JLabel registerLabel = new JLabel("¿Aún no tienes una cuenta?"); 
		registerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		registerLabel.setForeground(darkGreen);
		registerLabel.setAlignmentX(0.5f);
		add(registerLabel);
		
		JPanel panelRegisterButton = new JPanel();
		panelRegisterButton.setBackground(yellow);
		
		registerButton = new JButton("Registrate aquí");
		registerButton.setBackground(green); 
		registerButton.setForeground(Color.WHITE); 
		registerButton.setToolTipText("De click para registrarse");
		registerButton.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		registerButton.addActionListener(e -> {
	        new RegistrationForm(); 
	        javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
	    });
		
		panelRegisterButton.add(registerButton);
		add(panelRegisterButton);
	}
	
	private void uploadIcon(JButton boton) {
		try {
			Image icon = ImageIO.read(getClass().getResource("../images/flecha.png")); 
			if(icon != null) {
				icon = icon.getScaledInstance(20, 25, Image.SCALE_SMOOTH); 
				boton.setIcon(new ImageIcon(icon));
			}
		}catch(Exception ex) {
			System.out.println("Sin imágen encontrada.");
		}
	}
	
	private void uploadBCSImage() {
		try {
			Image fondoOriginal = ImageIO.read(getClass().getResource("../images/BCS.png")); 
			if(fondoOriginal != null) {
				stretchedBackground = fondoOriginal.getScaledInstance(120, 90, Image.SCALE_SMOOTH);
				repaint();
			}
		} catch(Exception ex) {
			System.out.println("Sin imágen encontrada.");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (stretchedBackground != null) {
	        // Se muevo un poco a la esquina
	        g.drawImage(stretchedBackground,220, 10, this); 
	    }
	}
	
//	private void login() {
//		if(correo.getText().trim().isEmpty()) {
//			mostrarErrorCorreo("El correo es obligatorio");
//		}
//	}
	
	private String validateLogin() {
		
		if(email.getText().trim().isEmpty()) {
			return "El correo es obligatorio";
		}
		
		if(password.getPassword().toString().trim().isEmpty()) {
			return "La contraseña es obligatoria";
		}
		
		return "Correctos";
	}
	
//	private JPanel createField(String labelText, Component field, JLabel errorLabel) {
//		JLabel ingreseCorreo = new JLabel("Ingrese su correo electrónico:"); 
//		ingreseCorreo.setFont(generalFont);
//		ingreseCorreo.setForeground(Color.BLACK);
//		ingreseCorreo.setAlignmentX(0.5f);
//		add(ingreseCorreo);
//		
//		JPanel panelCorreo = new JPanel();
//		panelCorreo.setBackground(yellow);
//		
//		email = new JTextField(25); 
//		email.setFont(new Font("Arial", Font.PLAIN, 18));
//		panelCorreo.add(email);
//		add(panelCorreo);
//		
//		JPanel panelErrorCorreo = new JPanel();
//		panelErrorCorreo.setBackground(yellow);
//		errorEmail = new JLabel("Correo inválido."); 
//		errorEmail.setVisible(false);
//		errorEmail.setFont(errorFont);
//		errorEmail.setForeground(Color.RED);
//		errorEmail.setAlignmentX(0.5f);
//		panelErrorCorreo.add(errorEmail);
//		add(errorEmail);
//		
//		return panelCorreo;
//		
//		
//	}

}