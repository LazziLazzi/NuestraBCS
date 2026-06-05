package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import utils.Colors;

public class SpeciesDetailView extends JPanel {
	
    private JButton noteBtn;
    private JButton btnBack;

    public SpeciesDetailView(String name, String scientificName, String kingdom, 
                             String phylum, String speciesClass, String family, 
                             String genus, String description, String bannerPath) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.yellow);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        btnBack = new JButton("Regresar a la ventana anterior");
        btnBack.setBackground(Colors.opaqueGreen);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(300, 30));
        btnBack.setToolTipText("De click para volver a la ventana anterior");
        btnBack.setFocusPainted(false);
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnBack.setBackground(Colors.lemonGreen);
                btnBack.setForeground(Colors.green);
            }
            public void mouseExited(MouseEvent evt) {
                btnBack.setBackground(Colors.opaqueGreen);
                btnBack.setForeground(Color.WHITE);
            }
        });

        add(btnBack);
        add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel imagePlaceholder = new JLabel();
        imagePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(new Color(230, 230, 230));
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Colors.green, 2));
        imagePlaceholder.setAlignmentX(CENTER_ALIGNMENT);
        imagePlaceholder.setMaximumSize(new Dimension(550, 200));
        imagePlaceholder.setPreferredSize(new Dimension(550, 200));
        
        try {
            String cleanPath = bannerPath.startsWith("/") ? bannerPath.substring(1) : bannerPath;
            File file = new File(cleanPath);
            if(file.exists()) {
                BufferedImage img = ImageIO.read(file);
                if(img != null) {
                    Image scaledImg = img.getScaledInstance(550, 200, Image.SCALE_SMOOTH);
                    imagePlaceholder.setIcon(new ImageIcon(scaledImg));
                } else {
                    imagePlaceholder.setText("Formato de imagen inválido");
                }
            } else {
                imagePlaceholder.setText("Archivo no encontrado: " + cleanPath);
            }
        } catch (Exception e) {
            imagePlaceholder.setText("Error al cargar la imagen");
        }
        add(imagePlaceholder);

        add(Box.createRigidArea(new Dimension(0, 15)));
        
        //Panel para el titulo y el boton de nota
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Colors.yellow); 
        titlePanel.setMaximumSize(new Dimension(550, 40)); 
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(name);
        titleLabel.setForeground(Colors.green);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 27));
        titleLabel.setToolTipText(name);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT); 

        noteBtn = new JButton("Nota");
        noteBtn.setBackground(Colors.green);
        noteBtn.setForeground(Color.WHITE);
        noteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        noteBtn.setFocusPainted(false);
        noteBtn.setPreferredSize(new Dimension(90, 35));

        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(noteBtn, BorderLayout.EAST);

        add(titlePanel);

        add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel tablePanel = new JPanel(new GridLayout(6, 2));
        tablePanel.setMaximumSize(new Dimension(550, 180));
        tablePanel.setBackground(Colors.yellow);
        tablePanel.setBorder(BorderFactory.createLineBorder(Colors.green, 2)); 

        addTableRow(tablePanel, "Nombre científico:", scientificName);
        addTableRow(tablePanel, "Reino:", kingdom);
        addTableRow(tablePanel, "Filo:", phylum);
        addTableRow(tablePanel, "Clase:", speciesClass);
        addTableRow(tablePanel, "Familia:", family);
        addTableRow(tablePanel, "Género:", genus);

        add(tablePanel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descArea.setForeground(Colors.darkGreen);
        descArea.setLineWrap(true); 
        descArea.setWrapStyleWord(true); 
        descArea.setOpaque(false);
        descArea.setEditable(false); 

        JScrollPane scroll = new JScrollPane(descArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null); 
        scroll.setAlignmentX(CENTER_ALIGNMENT);
        add(scroll);
    }

    private void addTableRow(JPanel table, String title, String value) {
        JLabel lblTitle = new JLabel("  " + title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Colors.darkGreen);
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Colors.green));

        JLabel lblValue = new JLabel("  " + value);
        lblValue.setFont(new Font("Arial", Font.PLAIN, 14));
        lblValue.setForeground(Colors.darkGreen);
        lblValue.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.green));

        table.add(lblTitle);
        table.add(lblValue);
    }

    public void addNoteListener(ActionListener listener) {
        noteBtn.addActionListener(listener);
    }
    
    public void addRegresarListener(ActionListener listener) { btnBack.addActionListener(listener); }
}