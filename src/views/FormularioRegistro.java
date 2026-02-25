package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class FormularioRegistro extends JFrame{
	
	private Color amarillito = new Color(255, 255, 204);
	private Color verde = new Color(56,142,60);
	//private Font fuenteTitulo = new Font("Arial Rounded MT Bold", Font.BOLD, 40);
	private Font general = new Font("Arial", Font.BOLD, 18);
	
	public FormularioRegistro() {
		panel();
	}
	
	public void panel() {
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		inicializarComponentes();
		
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
		componentes();
		//checkGenero();
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
		
		JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(general);
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);
		
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
		panelContenedor.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelContenedor.setBackground(amarillito);
		
		for(int i=0; i<etiquetas.length; i++) {
		    JLabel lbl = new JLabel(etiquetas[i]);
		    lbl.setAlignmentX(LEFT_ALIGNMENT);
		    lbl.setFont(new Font("Arial", Font.BOLD, 13));
		    
		    JTextField txt = new JTextField(20);
		    txt.setAlignmentX(LEFT_ALIGNMENT);
		    
		    panelContenedor.add(lbl);
		    panelContenedor.add(javax.swing.Box.createVerticalStrut(5)); 
		    panelContenedor.add(txt);
		    panelContenedor.add(javax.swing.Box.createVerticalStrut(10));
		}
		
		JLabel lblGenero = new JLabel("Seleccione su genero");
	    lblGenero.setAlignmentX(LEFT_ALIGNMENT);
	    lblGenero.setFont(new Font("Arial", Font.BOLD, 13));
	    panelContenedor.add(lblGenero);
	    
	   
	    
		JCheckBox cbMasculino = new JCheckBox("Masculino", true);
		cbMasculino.setOpaque(false);

		JCheckBox cbFemenino = new JCheckBox("Femenino", true);
		cbFemenino.setOpaque(false);
		
		JCheckBox cbOtro = new JCheckBox("Otro", true);
		cbOtro.setOpaque(false);
		
		ButtonGroup grupoBotones = new ButtonGroup();
		
		grupoBotones.add(cbMasculino);
		panelContenedor.add(cbMasculino);
		
		grupoBotones.add(cbFemenino);
		panelContenedor.add(cbFemenino);
		
		grupoBotones.add(cbOtro);
		panelContenedor.add(cbOtro);
		
		
		JButton confirmar = new JButton();
		confirmar = new JButton("Confirmar"); 
		confirmar.setBackground(verde); 
		confirmar.setForeground(Color.WHITE); 
		confirmar.setToolTipText("Da click para la siguiente seccion");
		confirmar.setFont(general); 
		//confirmar.setAlignmentX(CENTER_ALIGNMENT);
		panelContenedor.add(confirmar);
		
		
		JScrollPane scroll = new JScrollPane(panelContenedor);
        scroll.setBorder(null);
        
        add(scroll, BorderLayout.CENTER);
	}
	
	public void checkGenero() {
		JCheckBox cbMasculino = new JCheckBox("Masculino", true);
		add(cbMasculino);
		JCheckBox cbFemenino = new JCheckBox("Femenino", true);
		add(cbFemenino);
		JCheckBox cbOtro = new JCheckBox("Otro", true);
		add(cbOtro);
	}
	
	/*public void cargarFuente() {
		
		Font fuente = null;
		
		try {
			
		} catch
		
	}*/
	
}

