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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import utils.AppFont;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ejercicio.Window;

public class LoginView extends JPanel {
	
	//Atributos
	private JTextField email;
	private JPasswordField password;
	private JLabel errorEmail;
	private JLabel errorPass;
	private JButton access;
	private JButton registerButton;
	
	private Image stretchedBackground;
	
	//Colores
	private Color lemonGreen = new Color(228,225,106);
	private Color green = new Color(56,142,60);
	private Color yellow = new Color(255, 255, 204);
	private Color darkGreen = new Color(0, 102, 0);
	private Color lightGreen = new Color(240, 255, 240);
	
	//Fuentes
	private Font titleFont = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
	private Font generalFont = new Font("Arial", Font.BOLD, 16);
	private Font errorFont = new Font("Arial", Font.BOLD, 10);
	
	
	
	// Constructor
	public LoginView() {
		panel();
			
	}
	
	public String getEmail() {
		return email.getText();
	}
	
	public String getPassword() {
		return new String(password.getPassword());
	}
	
	public void showErrorEmail(String message) {
		errorEmail.setText(message);
		errorEmail.setVisible(true);
	}
	
	public void showErrorPass(String message) {
		errorPass.setText(message);
		errorPass.setVisible(true);
	}
	
	public void clearErrors() {
		errorEmail.setVisible(false);
		errorPass.setVisible(false);
	}

	public void addLoginListener(ActionListener listener) {
		access.addActionListener(listener);
	}

	public void addRegisterListener(ActionListener listener) {
		registerButton.addActionListener(listener);
	}
	
	public void showSuccessMessage() {
		JOptionPane.showMessageDialog(
				this,
				"Se inicio la sesion",
				"Sesion iniciada",
				JOptionPane.INFORMATION_MESSAGE
		);
	}

	// Métodos
	private void panel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setBackground(yellow);
		uploadBCSImage();
		add(Box.createVerticalGlue()); 
		titleLabel();
		add(Box.createRigidArea(new Dimension(50, 20)));
		textFields();
		
		assignRealTimeListeners();
		
		accessButton();
		add(Box.createVerticalStrut(5)); 
		registrationButton();
		
		add(Box.createVerticalGlue()); 
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
		transBackground(panelEmail);
		
		email = new JTextField(25); 
		email.setFont(new Font("Arial", Font.PLAIN, 18));
		panelEmail.add(email);
		add(panelEmail);
		
		JPanel panelErrorEmail = new JPanel();
		transBackground(panelErrorEmail);
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
		transBackground(panelPass);
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
		transBackground(buttonPanel);
		
		access = new JButton("Acceder"); 
		access.setBackground(green); 
		access.setForeground(Color.WHITE); 
		access.setToolTipText("Da click para iniciar sesión");
		access.setFont(generalFont); 
		
		uploadIcon(access);
		
		access.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(access);
				//btnLogin.setIcon(new ImageIcon(getClass().getResource("/img/icono.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(access);
			}
		});
		
		buttonPanel.add(access);
		add(buttonPanel);
		
		
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
		transBackground(panelRegisterButton);
		
		registerButton = new JButton("Registrate aquí");
		registerButton.setBackground(green); 
		registerButton.setForeground(Color.WHITE); 
		registerButton.setToolTipText("De click para registrarse");
		registerButton.setFont(new Font("Arial", Font.BOLD, 13));
		
		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(registerButton);
				//btnLogin.setIcon(new ImageIcon(getClass().getResource("/img/icono.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(registerButton);
			}
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
			Image bcsImage = ImageIO.read(getClass().getResource("../images/BCS.png")); 
			if(bcsImage != null) {
				stretchedBackground = bcsImage.getScaledInstance(400, 325, Image.SCALE_SMOOTH);
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
	    	int panelWidth = getWidth();
	    	int panelHeight = getHeight();
	    	
	    	int x = (panelWidth - stretchedBackground.getWidth(this)) / 2;
	    	int y = (panelHeight - stretchedBackground.getHeight(this)) / 2;
	    	
	        // Se mueve un poco a la esquina
	        g.drawImage(stretchedBackground,x, y, this);
	        
	    }
	}
	
	private void transBackground(JPanel panel) {
		panel.setOpaque(false);
		panel.setBackground(new Color(0,0,0));
	}
	
	private void changeBackground(JComponent c) {
		c.setBackground(lemonGreen);
		c.setForeground(green);
	}
	
	private void resetBackground(JComponent c) {
		c.setBackground(green);
		c.setForeground(Color.WHITE);
	}
	
	private void assignRealTimeListeners() {
	    addRealTimeValidation(email, () -> errorEmail.setVisible(false));
	    addRealTimeValidation(password, () -> errorPass.setVisible(false));
	    
	    focusListener(email);
	    focusListener(password);
	}
	
	/**
	 * Agrega un focus listener con color verde claso al
	 * @param textField que se pase.
	 */
	private void focusListener(JTextField textField) {
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setBackground(lightGreen); 
			}
							
			@Override
			public void focusLost(FocusEvent e) {
				textField.setBackground(Color.WHITE);
			}
		});
	}

	private void addRealTimeValidation(JTextField field, Runnable validatorMethod) {
	    field.getDocument().addDocumentListener(new DocumentListener() {
	        @Override public void insertUpdate(DocumentEvent e) { validatorMethod.run(); }
	        @Override public void removeUpdate(DocumentEvent e) { validatorMethod.run(); }
	        @Override public void changedUpdate(DocumentEvent e) { validatorMethod.run(); }
	    });
	}

}