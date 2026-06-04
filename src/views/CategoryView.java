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

//Es la pantalla que muestra la cuadricula con todas las tarjetas (foto y nombre) 
//de las especies que pertenecen a una categoría especifica


public class CategoryView extends JPanel {
	
	private JButton btnBack;
	
	private java.util.ArrayList<JButton> buttonSpecies = new java.util.ArrayList<>();
	
	private HashMap<String, JLabel> imageLabels = new HashMap<>();
	
    // Constructor que recibe el título y la lista de nombres
    public CategoryView(String categoryTitle, String[] itemNames) {
        initPanel(categoryTitle, itemNames);
    }

    //Configura el diseño principal de la ventana
    private void initPanel(String categoryTitle, String[] itemNames) {
        setLayout(new BorderLayout(20, 20));
        setBackground(Colors.yellow); 
        
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new javax.swing.BoxLayout(northPanel, javax.swing.BoxLayout.Y_AXIS));
        northPanel.setOpaque(false);
        
        // boton para volver
        btnBack = new JButton("Regresar a la ventana anterior");
        btnBack.setBackground(Colors.opaqueGreen);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBack.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(300, 30));
        btnBack.setToolTipText("De click para volver a la ventana anterior");
        btnBack.setFocusPainted(false);
        
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(Colors.lemonGreen);
                btnBack.setForeground(Colors.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setBackground(Colors.opaqueGreen);
                btnBack.setForeground(Color.WHITE);
            }
        });

        JLabel header = new JLabel(categoryTitle, SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(Colors.green);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setMaximumSize(new Dimension(550, 60));
        header.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        northPanel.add(btnBack);
        northPanel.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
        northPanel.add(header);

        add(northPanel, BorderLayout.NORTH);

        // La cuadrícula de 3 filas y 2 columnas
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        gridPanel.setOpaque(false); 

        for (String name : itemNames) {
            gridPanel.add(createItemCard(name));
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    //Crea el botón en forma de tarjeta con la foto y el nombre de la especie
    private JPanel createItemCard(String name) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JButton cardButton = new JButton();
        cardButton.setLayout(new BorderLayout());
        cardButton.setFocusPainted(false);
        cardButton.setBorder(BorderFactory.createLineBorder(Colors.green, 2));
        cardButton.setBackground(Colors.yellow);
        cardButton.setOpaque(true);
        cardButton.setActionCommand(name); 
        cardButton.setToolTipText(name);

        // el JLabel para la imagen
        JLabel imagePlaceholder = new JLabel("", SwingConstants.CENTER);
        imagePlaceholder.setOpaque(false);
        
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
                    System.out.println("Error al cargar la imagen");
                }
            }
        });
        
        imageLabels.put(name, imagePlaceholder);
        
        // el JLabel del nombre de la especie dentro de la tarjeta
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Colors.green); 
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        cardButton.add(imagePlaceholder, BorderLayout.CENTER);
        cardButton.add(nameLabel, BorderLayout.SOUTH);

        cardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameLabel.setBackground(Colors.lemonGreen);
                nameLabel.setForeground(Colors.green);
                cardButton.setBorder(BorderFactory.createLineBorder(Colors.lemonGreen, 2));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameLabel.setBackground(Colors.green);
                nameLabel.setForeground(Color.WHITE);
                cardButton.setBorder(BorderFactory.createLineBorder(Colors.green, 2));
            }
        });

        buttonSpecies.add(cardButton);
        wrapper.add(cardButton, BorderLayout.CENTER);
        return wrapper;
    }
    
    //Le da la imagen ala portada
    public void setFront(String name, String imagePath) {
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
    
    public void addComeListener(java.awt.event.ActionListener listener) { btnBack.addActionListener(listener); }
}
