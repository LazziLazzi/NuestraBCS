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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class RegistrationForm extends JFrame{
	
	//atributos
	private Image scaledBackground;
	
	/*"Nombre:", "Apellido Paterno:", "Apellido Materno:", 
	"Nombre de Usuario:", "Fecha de Nacimiento:", 
	"Correo electronico:", "Contraseña:", "Confirmar Contraseña:"*/
	
	
	private JTextField campoNombre;
	private JLabel errorNombre;
	
	private JTextField campoApellidoP;
	private JLabel errorApellidoP;
	
	private JTextField campoApellidoM;
	private JLabel errorApellidoM;
	
	private JTextField campoNombreUsuario;
	private JLabel errorNombreUsuario;
	
	private JTextField campoFechaNacimiento;
	private JLabel errorFechaNacimiento;
	
	private JLabel correo;
	private JTextField campoCorreo;
	private JLabel errorCorreo;
	
	private JPasswordField campoContrasenia;
	private JLabel errorContrasenia;
	
	private JPasswordField campoConfirmarContrasenia;
	private JLabel errorConfirmarContrasenia;
	
	private JButton confirmButton;
	
	private Color yellow = new Color(255, 255, 204);
	private Color green = new Color(56,142,60);
	private Color opaqueGreen = new Color(133,184,95);
	private Color darkGreen = new Color(0, 102, 0);
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
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(true);
			setTitle("Registro");
			setLocationRelativeTo(null);
			setLayout(new BorderLayout());
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image icono = tk.getImage("src/images/image.jpg");
			setIconImage(icono);
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
			
			JLabel nombre = new JLabel("Nombre"); 
			nombre.setAlignmentX(LEFT_ALIGNMENT);
			nombre.setFont(labelFont);
			nombre.setForeground(darkGreen);
			 
			campoNombre = new JTextField(20);
			campoNombre.setAlignmentX(LEFT_ALIGNMENT);
			campoNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorNombre = new JLabel("No digito bien el nombre");
			errorNombre.setAlignmentX(LEFT_ALIGNMENT);
			errorNombre.setFont(errorFont);
			errorNombre.setVisible(false);
			
			containerPanel.add(nombre);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoNombre);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorNombre);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel apellidoP = new JLabel("Apellido Paterno"); 
			apellidoP.setAlignmentX(LEFT_ALIGNMENT);
			apellidoP.setFont(labelFont);
			apellidoP.setForeground(darkGreen);
			 
			campoApellidoP = new JTextField(20);
			campoApellidoP.setAlignmentX(LEFT_ALIGNMENT);
			campoApellidoP.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorApellidoP = new JLabel("No digito bien el apellido");
			errorApellidoP.setAlignmentX(LEFT_ALIGNMENT);
			errorApellidoP.setFont(errorFont);
			errorApellidoP.setVisible(false);
			
			containerPanel.add(apellidoP);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoApellidoP);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorApellidoP);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel apellidoM = new JLabel("Apellido Materno"); 
			apellidoM.setAlignmentX(LEFT_ALIGNMENT);
			apellidoM.setFont(labelFont);
			apellidoM.setForeground(darkGreen);
			 
			campoApellidoM = new JTextField(20);
			campoApellidoM.setAlignmentX(LEFT_ALIGNMENT);
			campoApellidoM.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorApellidoM = new JLabel("No digito bien el apellido");
			errorApellidoM.setAlignmentX(LEFT_ALIGNMENT);
			errorApellidoM.setFont(errorFont);
			errorApellidoM.setVisible(false);
			
			containerPanel.add(apellidoM);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoApellidoM);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorApellidoM);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel nombreUsuario = new JLabel("Apellido Materno"); 
			nombreUsuario.setAlignmentX(LEFT_ALIGNMENT);
			nombreUsuario.setFont(labelFont);
			nombreUsuario.setForeground(darkGreen);
			 
			campoNombreUsuario = new JTextField(20);
			campoNombreUsuario.setAlignmentX(LEFT_ALIGNMENT);
			campoNombreUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorNombreUsuario = new JLabel("No digito bien el apellido");
			errorNombreUsuario.setAlignmentX(LEFT_ALIGNMENT);
			errorNombreUsuario.setFont(errorFont);
			errorNombreUsuario.setVisible(false);
			
			containerPanel.add(nombreUsuario);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoNombreUsuario);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorNombreUsuario);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel fechaNacimiento = new JLabel("Apellido Materno"); 
			fechaNacimiento.setAlignmentX(LEFT_ALIGNMENT);
			fechaNacimiento.setFont(labelFont);
			fechaNacimiento.setForeground(darkGreen);
			 
			campoFechaNacimiento = new JTextField(20);
			campoFechaNacimiento.setAlignmentX(LEFT_ALIGNMENT);
			campoFechaNacimiento.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorFechaNacimiento = new JLabel("No digito bien el apellido");
			errorFechaNacimiento.setAlignmentX(LEFT_ALIGNMENT);
			errorFechaNacimiento.setFont(errorFont);
			errorFechaNacimiento.setVisible(false);
			
			containerPanel.add(fechaNacimiento);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoFechaNacimiento);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorFechaNacimiento);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel correo = new JLabel("Apellido Materno"); 
			correo.setAlignmentX(LEFT_ALIGNMENT);
			correo.setFont(labelFont);
			correo.setForeground(darkGreen);
			 
			campoCorreo = new JTextField(20);
			campoCorreo.setAlignmentX(LEFT_ALIGNMENT);
			campoCorreo.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorCorreo = new JLabel("No digito bien el apellido");
			errorCorreo.setAlignmentX(LEFT_ALIGNMENT);
			errorCorreo.setFont(errorFont);
			errorCorreo.setVisible(false);
			
			containerPanel.add(correo);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoCorreo);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorCorreo);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel contrasenia = new JLabel("Apellido Materno"); 
			contrasenia.setAlignmentX(LEFT_ALIGNMENT);
			contrasenia.setFont(labelFont);
			contrasenia.setForeground(darkGreen);
			 
			campoContrasenia = new JPasswordField(20);
			campoContrasenia.setAlignmentX(LEFT_ALIGNMENT);
			campoContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorContrasenia = new JLabel("No digito bien el apellido");
			errorContrasenia.setAlignmentX(LEFT_ALIGNMENT);
			errorContrasenia.setFont(errorFont);
			errorContrasenia.setVisible(false);
			
			containerPanel.add(contrasenia);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(campoContrasenia);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(errorContrasenia);
			containerPanel.add(Box.createVerticalStrut(5));
			
			JLabel confirmarContrasenia = new JLabel("Confirmar contrasena"); 
			confirmarContrasenia.setAlignmentX(LEFT_ALIGNMENT);
			confirmarContrasenia.setFont(labelFont);
			confirmarContrasenia.setForeground(darkGreen);
			 
			campoConfirmarContrasenia = new JPasswordField(20);
			campoConfirmarContrasenia.setAlignmentX(LEFT_ALIGNMENT);
			campoConfirmarContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, nombre.getPreferredSize().height));
			
			errorConfirmarContrasenia = new JLabel("No digito bien el apellido");
			errorConfirmarContrasenia.setAlignmentX(LEFT_ALIGNMENT);
			errorConfirmarContrasenia.setFont(errorFont);
			errorConfirmarContrasenia.setVisible(false);
			
			containerPanel.add(confirmarContrasenia);
			containerPanel.add(Box.createVerticalStrut(5)); 
			containerPanel.add(confirmarContrasenia);
			containerPanel.add(Box.createVerticalStrut(20));
			containerPanel.add(confirmarContrasenia);
			containerPanel.add(Box.createVerticalStrut(5));
			
			
			
			
			/*String[] labels = {
				"Nombre:", "Apellido Paterno:", "Apellido Materno:", 
				"Nombre de Usuario:", "Fecha de Nacimiento:", 
				"Correo electronico:", "Contraseña:", "Confirmar Contraseña:"
			};
			
			// Declara los labels y los textbox en un for
			for (String labelText : labels) {
				JLabel lbl = new JLabel(labelText);
				lbl.setAlignmentX(LEFT_ALIGNMENT);
				lbl.setFont(labelFont);
				lbl.setForeground(darkGreen);
				
				JTextField txt = new JTextField(20);
				txt.setAlignmentX(LEFT_ALIGNMENT);
				txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, txt.getPreferredSize().height));
				
				containerPanel.add(lbl);
				containerPanel.add(Box.createVerticalStrut(5)); 
				containerPanel.add(txt);
				containerPanel.add(Box.createVerticalStrut(20));
			}*/
		}
		
		private void createGenderSelection() {
			JLabel genderLabel = new JLabel("Seleccione su genero:");
			genderLabel.setFont(labelFont);
			genderLabel.setAlignmentX(LEFT_ALIGNMENT);
			genderLabel.setForeground(darkGreen);
			containerPanel.add(genderLabel);
			
			JCheckBox cbMasculino = new JCheckBox("Masculino", true);
			cbMasculino.setOpaque(false);
			cbMasculino.setForeground(darkGreen);

			JCheckBox cbFemenino = new JCheckBox("Femenino", false);
			cbFemenino.setOpaque(false);
			cbFemenino.setForeground(darkGreen);
			
			JCheckBox cbOtro = new JCheckBox("Otro", false);
			cbOtro.setOpaque(false);
			cbOtro.setForeground(darkGreen);
			
			// Esto es para que no deje seleccionar más de un checkbox a la vez
			ButtonGroup buttonsGroup = new ButtonGroup();
			buttonsGroup.add(cbMasculino);
			buttonsGroup.add(cbFemenino);
			buttonsGroup.add(cbOtro);
			
			containerPanel.add(cbMasculino);
			containerPanel.add(cbFemenino);
			containerPanel.add(cbOtro);
			containerPanel.add(Box.createVerticalStrut(15));
		}
		
		private void createButtons() {
			confirmButton = new JButton("Crear cuenta"); 
			confirmButton.setBackground(green); 
			confirmButton.setForeground(Color.WHITE); 
			confirmButton.setToolTipText("De click para finalizar su registro");
			confirmButton.setFont(generalFont); 
			
			//confirmButton.addActionListener(e -> handleRegistration());
			confirmButton.addActionListener(e -> {
				boolean error = false;
				
				if(campoNombre.getText().isEmpty()) {
					errorNombre.setVisible(true);
					error = true;
				} else {
					errorNombre.setVisible(false);
				}
				
				if(campoApellidoP.getText().isEmpty()) {
					errorApellidoP.setVisible(true);
					error = true;
				} else {
					errorApellidoP.setVisible(false);
				}
				
				if (campoContrasenia.getPassword().length == 0) {
					campoContrasenia.setVisible(true);
					error = true;
				} else {
					errorContrasenia.setVisible(false);
				}
				
				if(error) {
					popupWindow();
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
			
			backButton.addActionListener(e -> handleBack());
			containerPanel.add(backButton);
		}
		
		private void handleRegistration() {
			System.out.println("Se hizo click en el botón confirmar");
			JOptionPane.showMessageDialog(this,
					"Si desea continuar, ingrese sus datos para crear una cuenta nueva",
					"Atención",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
		
		private void handleBack() {
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán los datos", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				new LoginView().setVisible(true); 
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
		
		private void validacionConfirmar() {
			
			
			
		}
	
}

