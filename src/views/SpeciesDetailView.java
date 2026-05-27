package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import utils.Colors;

public class SpeciesDetailView extends JPanel {
	
    public SpeciesDetailView(String name, String scientificName, String kingdom, 
                             String phylum, String speciesClass, String family, 
                             String genus, String description, String bannerPath) {
        
        // BoxLayout vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.yellow);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // placeholder estirado
        JLabel imagePlaceholder = new JLabel();
        imagePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(new Color(230, 230, 230));
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Colors.green, 2));
        imagePlaceholder.setAlignmentX(CENTER_ALIGNMENT);
        imagePlaceholder.setMaximumSize(new Dimension(550, 200));
        imagePlaceholder.setPreferredSize(new Dimension(550, 200));
        
        try {
            // Le quita el / del inicio para que no haya problemas con las lecturas
            String cleanPath = bannerPath.startsWith("/") ? bannerPath.substring(1) : bannerPath;
            ImageIcon icon = new ImageIcon(cleanPath);
            // Escala la imagen 
            Image img = icon.getImage().getScaledInstance(550, 200, Image.SCALE_SMOOTH);
            imagePlaceholder.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imagePlaceholder.setText("Imagen no encontrada");
        }
        add(imagePlaceholder);
        
        //--------

        add(Box.createRigidArea(new Dimension(0, 15)));

        // titulo recuadro verde con nombre del animal
        JLabel titleLabel = new JLabel(name, SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Colors.green);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(550, 50));
        titleLabel.setPreferredSize(new Dimension(550, 50));
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 15)));

        // gridlayout para la tabla de las caracteristicas
        JPanel tablePanel = new JPanel(new GridLayout(6, 2));
        tablePanel.setMaximumSize(new Dimension(550, 180));
        tablePanel.setBackground(Colors.yellow);
        tablePanel.setBorder(BorderFactory.createLineBorder(Colors.green, 2)); // Borde exterior

        // filas de la supuesta tabla
        addTableRow(tablePanel, "Nombre científico:", scientificName);
        addTableRow(tablePanel, "Reino:", kingdom);
        addTableRow(tablePanel, "Filo:", phylum);
        addTableRow(tablePanel, "Clase:", speciesClass);
        addTableRow(tablePanel, "Familia:", family);
        addTableRow(tablePanel, "Género:", genus);

        add(tablePanel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        // descripcion de la especie
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descArea.setForeground(Colors.darkGreen);
        descArea.setLineWrap(true); // Hace que el texto baje de línea si no cabe
        descArea.setWrapStyleWord(true); // Corta por palabras, no por letras
        descArea.setOpaque(false);
        descArea.setEditable(false); // El usuario no puede editar este texto

        // Scroll por si hace falta
        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); // Sin bordes feos
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        add(scrollPane);
    }

    private void addTableRow(JPanel table, String title, String value) {
        JLabel lblTitle = new JLabel("  " + title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Colors.darkGreen);
        
        // Borde para que parezca tabla
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Colors.green));

        JLabel lblValue = new JLabel("  " + value);
        lblValue.setFont(new Font("Arial", Font.PLAIN, 14));
        lblValue.setForeground(Colors.darkGreen);
        lblValue.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.green));

        table.add(lblTitle);
        table.add(lblValue);
    }
}