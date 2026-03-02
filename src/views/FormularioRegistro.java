package views;

import java.awt.BorderLayout;
import java.awt.Color;
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


public class FormularioRegistro extends JFrame{
	
	//atributos
	private Image fondoEscalado;
	
	private Color amarillito = new Color(255, 255, 204);
	private Color verde = new Color(56,142,60);
	private Color verdeOscuro = new Color(0, 102, 0);
	private Font general = new Font("Arial", Font.BOLD, 18);
	
	public FormularioRegistro() {
		panel();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/images/image.jpg");
		setIconImage(icono);
	}
	
	public void panel() {
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		inicializarComponentes();
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
		componentes();
	}
	
	public void componentes() {
		int cantidad = 8;
		JLabel[] listaLabels = new JLabel[cantidad];
		JTextField[] listatxt = new JTextField[cantidad];
		String[] etiquetas = {
	            "Nombre:", "Apellido Paterno:", "Apellido Materno:", 
	            "Nombre de Usuario:", "Fecha de Nacimiento:", 
	            "Correo electronico:", "Contraseña:", "Confirmar Contraseña:"
	        };
		
		
		JLabel lblTitulo = new JLabel("Ingrese sus datos para crear su cuenta:");
		lblTitulo.setFont(general);
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true); 
		lblTitulo.setBackground(amarillito); 
		lblTitulo.setForeground(verdeOscuro);
		add(lblTitulo, BorderLayout.NORTH);
		
		
		//Panel que contiene todo
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
		panelContenedor.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelContenedor.setBackground(amarillito);
		
		//Declara los labels y los textbox en un for para que no sea uno por uno
		for(int i = 0; i < etiquetas.length; i++) {
		    JLabel lbl = new JLabel(etiquetas[i]);
		    lbl.setAlignmentX(LEFT_ALIGNMENT);
		    lbl.setFont(new Font("Arial", Font.BOLD, 13));
		    lbl.setForeground(verdeOscuro);
		    
		    JTextField txt = new JTextField(20);
		    txt.setAlignmentX(LEFT_ALIGNMENT);
		    
		    panelContenedor.add(lbl);
		    panelContenedor.add(javax.swing.Box.createVerticalStrut(5)); 
		    panelContenedor.add(txt);
		    panelContenedor.add(javax.swing.Box.createVerticalStrut(20));
		}
		
		JLabel lblGenero = new JLabel("Seleccione su genero");
	    lblGenero.setFont(new Font("Arial", Font.BOLD, 13));
	    lblGenero.setAlignmentX(LEFT_ALIGNMENT);
	    lblGenero.setForeground(verdeOscuro);
	    panelContenedor.add(lblGenero);
	    
	   
	    //Chech box
		JCheckBox cbMasculino = new JCheckBox("Masculino", true);
		cbMasculino.setOpaque(false);
		cbMasculino.setForeground(verdeOscuro);
		//cbMasculino.setAlignmentX(CENTER_ALIGNMENT);

		JCheckBox cbFemenino = new JCheckBox("Femenino", false);
		cbFemenino.setOpaque(false);
		cbFemenino.setForeground(verdeOscuro);
		//cbFemenino.setAlignmentX(CENTER_ALIGNMENT);
		
		JCheckBox cbOtro = new JCheckBox("Otro", false);
		cbOtro.setOpaque(false);
		cbOtro.setForeground(verdeOscuro);
		//cbOtro.setAlignmentX(CENTER_ALIGNMENT);
		
		//Esto es para que no deje seleccionar mas de un checbox a la vez
		ButtonGroup grupoBotones = new ButtonGroup();
		
		grupoBotones.add(cbMasculino);
		panelContenedor.add(cbMasculino);
		
		grupoBotones.add(cbFemenino);
		panelContenedor.add(cbFemenino);
		
		grupoBotones.add(cbOtro);
		panelContenedor.add(cbOtro);
		panelContenedor.add(javax.swing.Box.createVerticalStrut(15));
		
		
		JButton confirmar = new JButton();
		confirmar = new JButton("Crear cuenta"); 
		confirmar.setBackground(verde); 
		confirmar.setForeground(Color.WHITE); 
		confirmar.setToolTipText("De click para finalizar su registro");
		confirmar.setFont(general); 
		panelContenedor.add(confirmar);
		

		//ActionListener para dar mensaje cuando se presiona el botón de registro
		confirmar.addActionListener(new ActionListener() {
			
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
		
		//Agrega el scroll
		JScrollPane scroll = new JScrollPane(panelContenedor);
        scroll.setBorder(null);
        
        add(scroll, BorderLayout.CENTER);
	}
	
}

