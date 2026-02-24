package views;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class FormularioRegistro extends JFrame{
	
	public FormularioRegistro() {
		
		setSize(300, 500);
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
		
		JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		int cantidad = 9;
		JLabel[] listaLabels = new JLabel[cantidad];
		JTextField[] listatxt = new JTextField[cantidad];
		
		
		/*listaLabels[0] = new JLabel("Nombre: ");
		listaLabels[1] = new JLabel("Apellido Paterno: ");
		listaLabels[2] = new JLabel("Apellido Materno: ");		
		listaLabels[3] = new JLabel("Nombre de Usuario: ");
		listaLabels[4] = new JLabel("Fecha de Nacimiento: ");
		listaLabels[5] = new JLabel("Genero: ");
		listaLabels[6] = new JLabel("Correo electronico: ");
		listaLabels[7] = new JLabel("Contrasenia: ");
		listaLabels[8] = new JLabel("Confirmar Coantrasenia: ");
		//add(listaLabels[0]);
		
		for(int i=0; i<cantidad; i++) {
			panelComponentes.add(listaLabels[i]);
			panelComponentes.add(listatxt[i]);
		}*/
		
		/*JLabel lbl1 = new JLabel("Nombre: ");
		JTextField txt = new JTextField();
		panelComponentes.add(txt);*/

		
		for(int i = 0; i < 20; i++) {
			JLabel lbl = new JLabel("Campo " + i);
			panelComponentes.add(lbl);
			JTextField txt = new JTextField(20);
			panelComponentes.add(txt);
		}
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
	
	public static void main(String[] args) {
		FormularioRegistro formulario = new FormularioRegistro();

	}
	
	/*public void cargarFuente() {
		
		Font fuente = null;
		
		try {
			
		} catch
		
	}*/
	
}

