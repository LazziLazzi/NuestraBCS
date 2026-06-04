package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.Colors;
import utils.AppFont;

//Pantalla donde el usuario selecciona que categoria de especies quiere explorar
//o si desea cerrar su sesión.

public class MenuView extends JPanel {
    
    private JButton animales, plantas, insectos, aracnidos,cerrarSesion;
    private Font titleFont = new Font("Arial", Font.BOLD, 22);
    private Font buttonFont = new Font("Arial", Font.PLAIN, 18);
    private Image stretchedBackground;

    // Constructor que carga la imagen y dibuja los botones
    public MenuView() {
        uploadBCSImage(); 
        initPanel();
    }
    
    //Configura la interfaz, el titulo y acomoda los botones en forma vertical
    private void initPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.yellow);
        setOpaque(true);

        add(Box.createVerticalGlue()); 
        
        JLabel nuestraBCSTitle = new JLabel("NuestraBCS");
        nuestraBCSTitle.setFont(AppFont.title());
        nuestraBCSTitle.setForeground(Colors.green);
        nuestraBCSTitle.setAlignmentX(0.5f); 
        add(nuestraBCSTitle);
        
        add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel title = new JLabel("Menú Principal", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Colors.green);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 30)));

        animales = createButton("Animales");
        plantas = createButton("Plantas");
        insectos = createButton("Insectos");
        aracnidos = createButton("Arácnidos");
        
        cerrarSesion = createCloseBtn("Cerrar Sesion");
        
        add(animales);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(plantas);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(insectos);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(aracnidos);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(cerrarSesion);
        
        add(Box.createVerticalGlue()); 
    }

    //Busca y carga en memoria la imagen
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

	// Sobreescribe cómo dibuja Java para poder poner la imagen como fondo
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (stretchedBackground != null) {
	    	int panelWidth = getWidth();
	    	int panelHeight = getHeight();
	    	
	    	int x = (panelWidth - stretchedBackground.getWidth(this)) / 2;
	    	int y = (panelHeight - stretchedBackground.getHeight(this)) / 2;
	    	
	        g.drawImage(stretchedBackground,x, y, this);
	        
	    }
	}
	
	// Construye y le da estilo especifico al boton de cerrar sesion
    private JButton createCloseBtn(String text) {
        JButton button = new JButton(text);
        button.setBackground(Colors.opaqueGreen);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 30)); 
        button.setFocusPainted(false);
        button.setToolTipText("De click para cerrar su sesión");
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.lemonGreen);
                button.setForeground(Colors.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.opaqueGreen);
                button.setForeground(Color.WHITE);
            }
        });
        
        return button;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Colors.green);
        button.setForeground(Color.WHITE);
        button.setFont(buttonFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 80)); 
        button.setFocusPainted(false);
        button.setToolTipText("Abrir la categoría " + text);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.lemonGreen);
                button.setForeground(Colors.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Colors.green);
                button.setForeground(Color.WHITE);
            }
        });
        
        return button;
    }
    
 // Métodos para que el controlador pueda agregarles acciones a los botones
    public void addAnimalesListener(java.awt.event.ActionListener listener) { animales.addActionListener(listener); }
    public void addPlantasListener(java.awt.event.ActionListener listener) { plantas.addActionListener(listener); }
    public void addInsectosListener(java.awt.event.ActionListener listener) { insectos.addActionListener(listener); }
    public void addAracnidosListener(java.awt.event.ActionListener listener) { aracnidos.addActionListener(listener); }
    public void addCloseSesionListener(java.awt.event.ActionListener listener) { cerrarSesion.addActionListener(listener); }
}

