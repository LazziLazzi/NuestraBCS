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


public class RegistrationForm extends JFrame{
	
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
	
	private JRadioButton rbMasculino;
	private JRadioButton rbFemenino;
	private JRadioButton rbOtro;
	private ButtonGroup grupoGenero;
	
	private Color yellow = new Color(255, 255, 204);
	private Color lightGreen = new Color(228,225,106);
	private Color green = new Color(56,142,60);
	private Color opaqueGreen = new Color(133,184,95);
	private Color darkGreen = new Color(0, 102, 0);
	Color defaultButtonColor;
	private Font generalFont = new Font("Arial", Font.BOLD, 18);
	private final Font labelFont = new Font("Arial", Font.BOLD, 13);
	private final Font errorFont = new Font("Arial", Font.ITALIC, 8);
	
	// Componentes principales
		private JPanel containerPanel;
		
		public RegistrationForm() {
			setupWindow();
			initializeComponents();
			setVisible(true); // Se llama al final para asegurar que todo cargue antes de mostrarse
		}
		
		private void setupWindow() {
			setSize(400, 500);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setResizable(true);
			setTitle("Registro");
			setLocationRelativeTo(null);
			setLayout(new BorderLayout());
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image icono = tk.getImage("src/images/image.jpg");
			setIconImage(icono);
			
			addWindowListener(new WindowListener() {
				@Override public void windowOpened(WindowEvent e) {}
				@Override public void windowIconified(WindowEvent e) {}
				@Override public void windowDeiconified(WindowEvent e) {}
				@Override public void windowDeactivated(WindowEvent e) {}
				@Override public void windowClosed(WindowEvent e) {}
				@Override public void windowActivated(WindowEvent e) {}
				
				@Override
				public void windowClosing(WindowEvent e) {
					handleCloseWindow();
				}
			});
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
			
			assignListeners();
			
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
		    changueMouse();
		    
		    confirmButton.addActionListener(e -> {
		        if (validateAll()) {
		            JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            new LoginView();
		        } else {
		            popupWindow(); // Muestra el mensaje de que faltan datos
		        }
		    });
		    
		    containerPanel.add(confirmButton);
		    containerPanel.add(Box.createVerticalStrut(10)); 
						
			JButton backButton = new JButton("Regresar"); 
			backButton.setBackground(opaqueGreen); 
			backButton.setForeground(Color.WHITE); 
			backButton.setToolTipText("De click para regresar");
			backButton.setFont(new Font("Arial", Font.BOLD, 12)); 
			backButton.setPreferredSize(new Dimension(100, 30));
			backButton.setMaximumSize(new Dimension(100, 30));
			
			backButton.addActionListener(e ->{
				handleBack();
				//new LoginView();
			});
			containerPanel.add(backButton);
		}
		
		public void changueMouse() {
			confirmButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					changeBackground(confirmButton);

				}
				
				public void mouseExited(MouseEvent e) {
					resetBackground(confirmButton);
				}
			});
		}
		
		private void changeBackground(JComponent c) {
			c.setBackground(lightGreen);
			c.setForeground(Color.WHITE);
		}
		
		private void resetBackground(JComponent c) {
			c.setBackground(defaultButtonColor);
		}
		
		private void handleRegistration() {
			System.out.println("Se hizo click en el botón confirmar");
			JOptionPane.showMessageDialog(this,
					"Si desea continuar, ingrese sus datos para crear una cuenta nueva",
					"Atención",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
		
	
		private void handleCloseWindow() {
			int option = JOptionPane.showConfirmDialog(this, 
					"¿Seguro que deseas salir? Se perderán los datos ingresados.", 
					"Confirmar salida", 
					JOptionPane.YES_NO_OPTION);
					
			if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
			}
		}
		
		private void handleBack() {
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán los datos", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				LoginWindow login = new LoginWindow();
		        login.setVisible(true); 
		        dispose();
			}
		}
		
		private void popupWindow() {
			JOptionPane.showMessageDialog(
					null,
					"Si desea continuar, ingrese sus datos.",
					"ATENCIÓN",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
		
		private void assignListeners() {
			addRealTimeValidation(fieldName, this::validateNombre);
			addRealTimeValidation(fieldLastNameP, this::validateApellidoP);
			addRealTimeValidation(fieldLastNameM, this::validateApellidoM);
			addRealTimeValidation(fieldNameUser, this::validateNombreUsuario);
			addRealTimeValidation(fieldDate, this::validateFechaNacimiento);
			addRealTimeValidation(fieldEmail, this::validateCorreo);
			addRealTimeValidation(fieldPassword, this::validateContrasenia);
			addRealTimeValidation(fieldConfirmPassword, this::validateConfirmarContrasenia);
			// Focus Listener
			fieldName.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					// Cambia el fondo del jtex del nombre al avitvarlo
					fieldName.setBackground(new Color(240, 255, 240)); 
				}
								
				@Override
				public void focusLost(FocusEvent e) {
					// Regresa al color blanco cuando quita el activo
					fieldName.setBackground(Color.WHITE);
				}
			});
	
			// Key listener
			fieldNameUser.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					// Hace que el usuario no pueda usar los espacios
					if (e.getKeyChar() == ' ') {
						e.consume(); //Consume el evento para que no se repita
						Toolkit.getDefaultToolkit().beep(); // Sonido de windows xd
					}
				}

				@Override public void keyPressed(KeyEvent e) {}
				@Override public void keyReleased(KeyEvent e) {}
			});		
		}

		private void addRealTimeValidation(JTextField field, Runnable validatorMethod) {
			field.getDocument().addDocumentListener(new DocumentListener() {
				@Override public void insertUpdate(DocumentEvent e) { validatorMethod.run(); }
				@Override public void removeUpdate(DocumentEvent e) { validatorMethod.run(); }
				@Override public void changedUpdate(DocumentEvent e) { validatorMethod.run(); }
			});
		}
		
		// Agrupa todas las validaciones
		private boolean validateAll() {
			// Ejecura todas las validaciones
			boolean v1 = validateNombre();
		    boolean v2 = validateApellidoP();
		    boolean v3 = validateApellidoM(); 
		    boolean v4 = validateNombreUsuario(); 
		    boolean v5 = validateFechaNacimiento(); 
		    boolean v6 = validateCorreo();
			boolean v7 = validateContrasenia();
			boolean v8 = validateConfirmarContrasenia();
				    
			// Retorna si todas las validaciones son true
			return v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8;
		}

		private boolean validateNombre() {
			if (fieldName.getText().trim().isEmpty()) { 
				errorName.setText("El nombre es obligatorio"); 
				return false; 
			}
			errorName.setText(" "); 
			return true;
		}

		private boolean validateApellidoP() {
			if (fieldLastNameP.getText().trim().isEmpty()) { 
				errorLastnNameP.setText("El apellido paterno es obligatorio"); 
				return false; 
			}
			errorLastnNameP.setText(" "); 
			return true;
		}
		
		private boolean validateApellidoM() {
			if (fieldLastNameM.getText().trim().isEmpty()) { 
				errorLastNameM.setText("El apellido materno es obligatorio"); 
				return false; 
			}
			errorLastNameM.setText(" "); 
			return true;
		}

		private boolean validateNombreUsuario() {
			String usuario = fieldNameUser.getText().trim();
			if (usuario.isEmpty()) {
				errorNameUser.setText("El usuario es obligatorio"); 
				return false; 
			} 
			else if (usuario.contains(" ")) { 
				errorNameUser.setText("No debe contener espacios"); 
				return false; 
			} 
			else if (usuario.length() < 4) { 
				errorNameUser.setText("Mínimo 4 caracteres"); 
				return false; 
			}
			errorNameUser.setText(" "); 
			return true;
		}

		private boolean validateFechaNacimiento() {
			String fecha = fieldDate.getText().trim();
			
			if (fecha.isEmpty()) { 
				errorFieldDate.setText("La fecha es obligatoria"); 
				return false; 
			} 
			errorFieldDate.setText(" "); 
			return true;
		}

		private boolean validateCorreo() {
			String correo = fieldEmail.getText().trim();
			if (correo.isEmpty()) { 
				errorEmail.setText("El correo es obligatorio"); 
				return false; 
			} 
			else if (!correo.contains("@") || !correo.contains(".")) { 
				errorEmail.setText("Ingrese un correo válido"); 
				return false; 
			}
			errorEmail.setText(" "); 
			return true;
		}

		private boolean validateContrasenia() {
			if (fieldPassword.getPassword().length < 6) { 
				errorPassword.setText("Minimo 6 caracteres"); 
				return false; 
			}
			errorPassword.setText(" ");
			validateConfirmarContrasenia(); 
			return true;
		}

		private boolean validateConfirmarContrasenia() {
			String pass1 = new String(fieldPassword.getPassword());
			String pass2 = new String(fieldConfirmPassword.getPassword());
			if (pass2.isEmpty()) { 
				errorConfirmPassword.setText("Confirme su contraseña"); 
				return false; 
			} 
			else if (!pass1.equals(pass2)) { 
				errorConfirmPassword.setText("Las contraseñas no coinciden"); 
				return false; 
			}
			errorConfirmPassword.setText(" "); 
			return true;
		}	
}

