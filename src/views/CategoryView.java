package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.Colors;

public class CategoryView extends JPanel {
	
	private java.util.ArrayList<JButton> botonesEspecies = new java.util.ArrayList<>();
	
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
        JPanel card = new JPanel(new BorderLayout(0, 10)); 
        card.setOpaque(false);

        // Espacio para la imagen (por mientras es un recuadro gris con texto)
        JLabel imagePlaceholder = new JLabel("Foto de " + name, SwingConstants.CENTER);
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(Colors.gray);
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Colors.green, 2));

        // El botón blanco con letras verdes
        JButton nameButton = new JButton(name);
        nameButton.setBackground(Color.WHITE);
        nameButton.setForeground(Colors.darkGreen);
        nameButton.setFont(new Font("Arial", Font.BOLD, 16));
        nameButton.setFocusPainted(false);
        
        card.add(imagePlaceholder, BorderLayout.CENTER);
        card.add(nameButton, BorderLayout.SOUTH);
        
        botonesEspecies.add(nameButton);
        
        return card;
    }
    
    public void addSpeciesListener(java.awt.event.ActionListener listener) {
        for (JButton btn : botonesEspecies) {
            btn.addActionListener(listener);
        }
    }
}
