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
import assets.Colors;
import utils.AppFont;

public class MenuView extends JPanel {
    
    private JButton animales, plantas, insectos, aracnidos;
    private Font titleFont = new Font("Arial", Font.BOLD, 22);
    private Font buttonFont = new Font("Arial", Font.PLAIN, 18);
    private Image stretchedBackground; // Variable para la imagen

    public MenuView() {
        uploadBCSImage(); // Cargamos la imagen al iniciar
        initPanel();
    }

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

        add(animales);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(plantas);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(insectos);
        add(Box.createRigidArea(new Dimension(0, 18)));
        add(aracnidos);

        add(Box.createVerticalGlue()); 
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
	    	
	        g.drawImage(stretchedBackground,x, y, this);
	        
	    }
	}

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Colors.green);
        button.setForeground(Color.WHITE);
        button.setFont(buttonFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 80)); 
        button.setFocusPainted(false);
        
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
}