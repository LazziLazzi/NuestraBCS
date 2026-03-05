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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class RegistrationForm extends JFrame{
	
	//atributos
	private Image scaledBackground;
	
	private Color yellow = new Color(255, 255, 204);
	private Color green = new Color(56,142,60);
	private Color opaqueGreen = new Color(133,184,95);
	private Color darkGreen = new Color(0, 102, 0);
	private Font generalFont = new Font("Arial", Font.BOLD, 18);
	
	public RegistrationForm() {
		panel();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/images/image.jpg");
		setIconImage(icono);
	}
	
	public void panel() {
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		startComponents();
		setVisible(true);		
	}
	
	public void startComponents() {
		components();
	}
	
	public void components() {
		int amount = 8;
		JLabel[] listaLabels = new JLabel[amount];
		JTextField[] listatxt = new JTextField[amount];
		String[] labels = {
	            "Nombre:", "Apellido Paterno:", "Apellido Materno:", 
	            "Nombre de Usuario:", "Fecha de Nacimiento:", 
	            "Correo electronico:", "Contraseña:", "Confirmar Contraseña:"
	        };
		
		
		JLabel titleLabel = new JLabel("Ingrese sus datos para crear su cuenta:");
		titleLabel.setFont(generalFont);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true); 
		titleLabel.setBackground(yellow); 
		titleLabel.setForeground(darkGreen);
		add(titleLabel, BorderLayout.NORTH);
		
		
		//Panel que contiene todo
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		containerPanel.setBackground(yellow);
		
		//Declara los labels y los textbox en un for para que no sea uno por uno
		for(int i = 0; i < labels.length; i++) {
		    JLabel lbl = new JLabel(labels[i]);
		    lbl.setAlignmentX(LEFT_ALIGNMENT);
		    lbl.setFont(new Font("Arial", Font.BOLD, 13));
		    lbl.setForeground(darkGreen);
		    
		    JTextField txt = new JTextField(20);
		    txt.setAlignmentX(LEFT_ALIGNMENT);
		    
		    containerPanel.add(lbl);
		    containerPanel.add(javax.swing.Box.createVerticalStrut(5)); 
		    containerPanel.add(txt);
		    containerPanel.add(javax.swing.Box.createVerticalStrut(20));
		}
		
		JLabel genderLabel = new JLabel("Seleccione su genero");
	    genderLabel.setFont(new Font("Arial", Font.BOLD, 13));
	    genderLabel.setAlignmentX(LEFT_ALIGNMENT);
	    genderLabel.setForeground(darkGreen);
	    containerPanel.add(genderLabel);
	    
	   
	    //Chech box
		JCheckBox cbMasculino = new JCheckBox("Masculino", true);
		cbMasculino.setOpaque(false);
		cbMasculino.setForeground(darkGreen);
		//cbMasculino.setAlignmentX(CENTER_ALIGNMENT);

		JCheckBox cbFemenino = new JCheckBox("Femenino", false);
		cbFemenino.setOpaque(false);
		cbFemenino.setForeground(darkGreen);
		//cbFemenino.setAlignmentX(CENTER_ALIGNMENT);
		
		JCheckBox cbOtro = new JCheckBox("Otro", false);
		cbOtro.setOpaque(false);
		cbOtro.setForeground(darkGreen);
		//cbOtro.setAlignmentX(CENTER_ALIGNMENT);
		
		//Esto es para que no deje seleccionar mas de un checbox a la vez
		ButtonGroup buttonsGroup = new ButtonGroup();
		
		buttonsGroup.add(cbMasculino);
		containerPanel.add(cbMasculino);
		
		buttonsGroup.add(cbFemenino);
		containerPanel.add(cbFemenino);
		
		buttonsGroup.add(cbOtro);
		containerPanel.add(cbOtro);
		containerPanel.add(javax.swing.Box.createVerticalStrut(15));
		
		
		JButton confirmButton = new JButton();
		confirmButton = new JButton("Crear cuenta"); 
		confirmButton.setBackground(green); 
		confirmButton.setForeground(Color.WHITE); 
		confirmButton.setToolTipText("De click para finalizar su registro");
		confirmButton.setFont(generalFont); 
		containerPanel.add(confirmButton);
		

		//ActionListener para dar mensaje cuando se presiona el botón de registro
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se hizo click en el botón confirmar");
				JOptionPane.showMessageDialog(null,
						"Si desea continuar, ingrese sus datos para crear una cuenta nueva",
						"Atención",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		
		containerPanel.add(Box.createVerticalStrut(5)); 
				
		JButton backButton = new JButton();
		backButton = new JButton("Regresar"); 
		backButton.setBackground(opaqueGreen); 
		backButton.setForeground(Color.WHITE); 
		backButton.setToolTipText("De click para regresar");
		backButton.setFont(new Font("Arial", Font.BOLD, 12)); 
		//backButton.setBounds(100, 50, 20, 30);
		backButton.setPreferredSize(new Dimension(20, 30));
		//backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		containerPanel.add(backButton);
		
		backButton.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this,"Seguro que deseas regresar? Se perderan los datos");
			if(option == JOptionPane.YES_OPTION) {
				new LoginWindow().setVisible(true);
				dispose();
			}
		
		});
		
		//Agrega el scroll
		JScrollPane scroll = new JScrollPane(containerPanel);
        scroll.setBorder(null);
        
        add(scroll, BorderLayout.CENTER);
	}
	
}

