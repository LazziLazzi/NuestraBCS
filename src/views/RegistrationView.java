package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class RegistrationView extends JFrame{
	
	//atributos
	private Image scaledBackground;
	
	/*"Nombre:", "Apellido Paterno:", "Apellido Materno:", 
	"Nombre de Usuario:", "Fecha de Nacimiento:", 
	"Correo electronico:", "Contraseña:", "Confirmar Contraseña:"*/
	
	private JTextField fieldName;
	private JLabel errorName;
	
	private JTextField fieldLastNameP;
	private JLabel errorLastnNameP;
	
	private JTextField fieldLastNameM;
	private JLabel errorLastNameM;
	
	private JTextField fieldNameUser;
	private JLabel errorNameUser;
	
	private JTextField fieldDate;
	private JLabel errorFieldDate;
	
	private JTextField fieldEmail;
	private JLabel errorEmail;
	
	private JPasswordField fieldPassword;
	private JLabel errorPassword;
	
	private JPasswordField fieldConfirmPassword;
	private JLabel errorConfirmPassword;
	
	private JButton confirmButton;
	private JButton backButton;
	
	private JRadioButton rbMasculino;
	private JRadioButton rbFemenino;
	private JRadioButton rbOtro;
	private ButtonGroup grupoGenero;
	
	private Color yellow = new Color(255, 255, 204);
	private Color lemonGreen = new Color(228,225,106);
	private Color green = new Color(56,142,60);
	private Color opaqueGreen = new Color(133,184,95);
	private Color darkGreen = new Color(0, 102, 0);
	private Color lightGreen = new Color(240, 255, 240);
	Color defaultButtonColor;
	
	private Font generalFont = new Font("Arial", Font.BOLD, 18);
	private final Font labelFont = new Font("Arial", Font.BOLD, 13);
	private final Font errorFont = new Font("Arial", Font.ITALIC, 8);
	
	// Componentes principales
	private JPanel containerPanel;
	
	public RegistrationView() {
		setupWindow();
		initializeComponents();
		setVisible(true); // Se llama al final para asegurar que todo cargue antes de mostrarse
	}
	
	
	public String getNameText() { return fieldName.getText(); }
	public String getLastNamePText() { return fieldLastNameP.getText(); }
	public String getLastNameMText() { return fieldLastNameM.getText(); }
	public String getNameUserText() { return fieldNameUser.getText(); }
	public String getDateText() { return fieldDate.getText(); }
	public String getEmailText() { return fieldEmail.getText(); }
	public String getPasswordText() { return new String(fieldPassword.getPassword()); }
	public String getConfirmPasswordText() { return new String(fieldConfirmPassword.getPassword()); }
	
	public String getGenderSelected() {
		if (rbMasculino.isSelected()) {
			return "Masculino";
		} else if (rbFemenino.isSelected()) {
		    return "Femenino";
		} else if (rbOtro.isSelected()) {
		    return "Otro";
		}
		    return "No especificado";
	}	

	public void showNameError(String msg) { errorName.setText(msg); }
	public void showLastNamePError(String msg) { errorLastnNameP.setText(msg); }
	public void showLastNameMError(String msg) { errorLastNameM.setText(msg); }
	public void showNameUserError(String msg) { errorNameUser.setText(msg); }
	public void showDateError(String msg) { errorFieldDate.setText(msg); }
	public void showEmailError(String msg) { errorEmail.setText(msg); }
	public void showPasswordError(String msg) { errorPassword.setText(msg); }
	public void showConfirmPasswordError(String msg) { errorConfirmPassword.setText(msg); }
		
	public void addConfirmListener(ActionListener l) { confirmButton.addActionListener(l); }
	public void addBackListener(ActionListener l) { backButton.addActionListener(l); }
	public void setWindowClosingListener(WindowAdapter adapter) { addWindowListener(adapter); }

	public void addNameValidator(Runnable validator) { addRealTimeValidation(fieldName, validator); }
	public void addLastNamePValidator(Runnable validator) { addRealTimeValidation(fieldLastNameP, validator); }
	public void addLastNameMValidator(Runnable validator) { addRealTimeValidation(fieldLastNameM, validator); }
	public void addNameUserValidator(Runnable validator) { addRealTimeValidation(fieldNameUser, validator); }
	public void addDateValidator(Runnable validator) { addRealTimeValidation(fieldDate, validator); }
	public void addEmailValidator(Runnable validator) { addRealTimeValidation(fieldEmail, validator); }
	public void addPasswordValidator(Runnable validator) { addRealTimeValidation(fieldPassword, validator); }
	public void addConfirmPasswordValidator(Runnable validator) { addRealTimeValidation(fieldConfirmPassword, validator); }

	public void showSuccessMessage() { JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE); }
	public void showIncompleteDataMessage() { JOptionPane.showMessageDialog(this, "Si desea continuar, ingrese sus datos.", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE); }
	public int showCloseConfirmation() { return JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir? Se perderán los datos ingresados.", "Confirmar salida", JOptionPane.YES_NO_OPTION); }
	public int showBackConfirmation() { return JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán los datos", "Confirmar", JOptionPane.YES_NO_OPTION); }
	
	private void setupWindow() {
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		try {
			Image icono = tk.getImage("src/images/image.jpg");
			setIconImage(icono);
		} catch(Exception e) { System.out.println("No se encontró el icono"); }
	}
	
	private void initializeComponents() {
		createTitle();
		
		// Panel principal que conttiene el formulario
		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		containerPanel.setBackground(yellow);
		
		createFormFields();
		createGenderSelection();
		createButtons();
		assignUIListeners();
		
		// Agrega el scroll
		JScrollPane scroll = new JScrollPane(containerPanel);
		scroll.setBorder(null);
		add(scroll, BorderLayout.CENTER);
	}
	
	private void createTitle() {
		JLabel titleLabel = new JLabel("Ingrese sus datos para crear su cuenta:");
		titleLabel.setFont(generalFont);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true); 
		titleLabel.setBackground(yellow); 
		titleLabel.setForeground(darkGreen);
		
		add(titleLabel, BorderLayout.NORTH);
	}
	
	private void createFormFields() {
		// Inicializamos los campos
		fieldName = new JTextField(20);
		errorName = new JLabel(" ");

		fieldLastNameP = new JTextField(20);
		errorLastnNameP = new JLabel(" ");

		fieldLastNameM = new JTextField(20);
		errorLastNameM = new JLabel(" ");

		fieldNameUser = new JTextField(20);
		errorNameUser = new JLabel(" ");

		fieldDate = new JTextField(20);
		errorFieldDate = new JLabel(" ");

		fieldEmail = new JTextField(20);
		errorEmail = new JLabel(" ");

		fieldPassword = new JPasswordField(20);
		errorPassword = new JLabel(" ");

		fieldConfirmPassword = new JPasswordField(20);
		errorConfirmPassword = new JLabel(" ");

		// Metodo para llenar los campos
		construirCampo("Nombre:", fieldName, errorName);
		construirCampo("Apellido Paterno:", fieldLastNameP, errorLastnNameP);
		construirCampo("Apellido Materno:", fieldLastNameM, errorLastNameM);
		construirCampo("Nombre de Usuario:", fieldNameUser, errorNameUser);
		construirCampo("Fecha de nacimiento:", fieldDate, errorFieldDate);
		construirCampo("Correo electrónico:", fieldEmail, errorEmail);
		construirCampo("Contraseña:", fieldPassword, errorPassword);
		construirCampo("Confirmar contraseña:", fieldConfirmPassword, errorConfirmPassword);
	}

	// Método auxiliar para construir etiquetas, campos y errores dinámicamente
	private void construirCampo(String texto, JTextField campo, JLabel error) {
		JLabel label = new JLabel(texto);
		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(labelFont);
		label.setForeground(darkGreen);

		campo.setAlignmentX(LEFT_ALIGNMENT);
		campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, campo.getPreferredSize().height));

		error.setAlignmentX(LEFT_ALIGNMENT);
		error.setFont(errorFont);
		error.setForeground(Color.RED); // Texto rojo para los errores

		containerPanel.add(label);
		containerPanel.add(Box.createVerticalStrut(2));
		containerPanel.add(campo);
		containerPanel.add(Box.createVerticalStrut(2));
		containerPanel.add(error);
		containerPanel.add(Box.createVerticalStrut(10));
	}
	
	//Para el genero
	private void createGenderSelection() {
	    JLabel genderLabel = new JLabel("Seleccione su género:");
	    genderLabel.setFont(labelFont);
	    genderLabel.setAlignmentX(LEFT_ALIGNMENT);
	    genderLabel.setForeground(darkGreen);
	    containerPanel.add(genderLabel);
	    
	    // Usar las variables de clase en lugar de crear nuevas
	    rbMasculino = new JRadioButton("Masculino", true);
	    rbMasculino.setOpaque(false);
	    rbMasculino.setForeground(darkGreen);

	    rbFemenino = new JRadioButton("Femenino", false);
	    rbFemenino.setOpaque(false);
	    rbFemenino.setForeground(darkGreen);
	    
	    rbOtro = new JRadioButton("Otro", false);
	    rbOtro.setOpaque(false);
	    rbOtro.setForeground(darkGreen);
	    
	    grupoGenero = new ButtonGroup();
	    grupoGenero.add(rbMasculino);
	    grupoGenero.add(rbFemenino);
	    grupoGenero.add(rbOtro);
	    
	    containerPanel.add(rbMasculino);
	    containerPanel.add(rbFemenino);
	    containerPanel.add(rbOtro);
	    //containerPanel.add(errorGenero); 
	    containerPanel.add(Box.createVerticalStrut(15));
	}
	
	private void createButtons() {
		confirmButton = new JButton("Crear cuenta"); 
	    confirmButton.setBackground(green); 
	    confirmButton.setForeground(Color.WHITE); 
	    confirmButton.setFont(generalFont); 
	    defaultButtonColor = confirmButton.getBackground();
	    
	    confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) { changeBackground(confirmButton); }
			public void mouseExited(MouseEvent e) { resetBackground(confirmButton); }
		});
	    
	    containerPanel.add(confirmButton);
	    containerPanel.add(Box.createVerticalStrut(10)); 
					
		backButton = new JButton("Regresar"); 
		backButton.setBackground(opaqueGreen); 
		backButton.setForeground(Color.WHITE); 
		backButton.setToolTipText("De click para regresar");
		backButton.setFont(new Font("Arial", Font.BOLD, 12)); 
		backButton.setPreferredSize(new Dimension(100, 30));
		backButton.setMaximumSize(new Dimension(100, 30));
		
		containerPanel.add(backButton);
	}
	
	private void changeBackground(JComponent c) {
		c.setBackground(lemonGreen);
		c.setForeground(Color.WHITE);
	}
	
	private void resetBackground(JComponent c) {
		c.setBackground(defaultButtonColor);
	}
	
	private void assignUIListeners() {
		focusListener(fieldName); focusListener(fieldLastNameP); focusListener(fieldLastNameM);
		focusListener(fieldNameUser); focusListener(fieldDate); focusListener(fieldEmail);
		focusListener(fieldPassword); focusListener(fieldConfirmPassword);

		fieldNameUser.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == ' ') { e.consume(); Toolkit.getDefaultToolkit().beep(); }
			}
			@Override public void keyPressed(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
		});		
	}
		
	private void focusListener(JTextField textField) {
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// Cambia el fondo del jtex del nombre al avitvarlo
				textField.setBackground(lightGreen); 
			}
							
			@Override
			public void focusLost(FocusEvent e) {
				// Regresa al color blanco cuando quita el activo
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

