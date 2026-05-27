package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.Colors;

public class CategoryView extends JPanel {
	
	private java.util.ArrayList<JButton> buttonSpecies = new java.util.ArrayList<>();
	
	private HashMap<String, JLabel> imageLabels = new HashMap<>();
	
    // Constructor que recibe el título y la lista de nombres
    public CategoryView(String categoryTitle, String[] itemNames) {
        initPanel(categoryTitle, itemNames);
    }

    private void initPanel(String categoryTitle, String[] itemNames) {
        // Layout principal con separación entre los bordes
        setLayout(new BorderLayout(20, 20));
        setBackground(Colors.yellow); 
        
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel(categoryTitle, SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(Colors.green);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setPreferredSize(new Dimension(0, 60));
        add(header, BorderLayout.NORTH);

        // La cuadrícula de 3 filas y 2 columnas
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        gridPanel.setOpaque(false); 

        for (String name : itemNames) {
            gridPanel.add(createItemCard(name));
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    // Método para construir cada panel individual
    private JPanel createItemCard(String name) {
    	 	 JPanel card = new JPanel(new BorderLayout());
         card.setOpaque(false);

        JLabel imagePlaceholder = new JLabel("", SwingConstants.CENTER);
        imagePlaceholder.setOpaque(false);
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        
        imagePlaceholder.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                String path = (String) imagePlaceholder.getClientProperty("imagePath");
                if (path == null) return;
                try {
                    BufferedImage img = ImageIO.read(new File(path));
                    if (img != null) {
                        int w = imagePlaceholder.getWidth();
                        int h = imagePlaceholder.getHeight();
                        if (w > 0 && h > 0) {
                            Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                            imagePlaceholder.setIcon(new ImageIcon(scaled));
                        }
                    }
                } catch (Exception ex) {
                	
                }
            }
        });
        
        imageLabels.put(name, imagePlaceholder);

        JButton nameButton = new JButton(name);
        nameButton.setBackground(Color.WHITE);
        nameButton.setForeground(Colors.darkGreen);
        nameButton.setFont(new Font("Arial", Font.BOLD, 16));
        nameButton.setFocusPainted(false);
        nameButton.setBorderPainted(false);
        nameButton.setOpaque(true);
        
        card.add(imagePlaceholder, BorderLayout.CENTER);
        card.add(nameButton, BorderLayout.SOUTH);
        
        buttonSpecies.add(nameButton);
        
        return card;
    }
    
    //Le da la imagen ala portada
    public void setPortada(String name, String imagePath) {
        JLabel label = imageLabels.get(name);
        if (label != null && imagePath != null && !imagePath.isEmpty()) {
            try {
                String cleanPath = imagePath.startsWith("/") ? imagePath.substring(1) : imagePath;
                File imgFile = new File(cleanPath);
                if (imgFile.exists()) {
                    label.putClientProperty("imagePath", cleanPath);
                    label.revalidate();
                    label.repaint();
                }
            } catch (Exception e) {
                
            }
        }
    }
    
    public void addSpeciesListener(java.awt.event.ActionListener listener) {
        for (JButton btn : buttonSpecies) {
            btn.addActionListener(listener);
        }
    }
}
