package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import utils.Colors;

public class NotesView extends JPanel {

    private JTextArea textArea;
    private JButton btnCancel;
    private JButton btnSave;

    public NotesView(String speciesName) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.yellow);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titulo
        JLabel titleLabel = new JLabel(speciesName, SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Colors.green);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setToolTipText(speciesName);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(550, 60));
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 15)));

        // Area del texto
        textArea = new JTextArea(); 
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setForeground(Colors.darkGreen);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Colors.green, 4));
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 20)));

        // botones de guardar y cancelar
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setOpaque(false);
        btnPanel.setAlignmentX(CENTER_ALIGNMENT);

        btnCancel = createButton("Cancelar");
        btnSave = createButton("Guardar Cambios");

        btnPanel.add(btnCancel);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(btnSave);

        add(btnPanel);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Colors.green);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        return btn;
    }
    
    public void setNotesContent(String text) { 
        textArea.setText(text); 
    }

    // Métodos para el controlador
    public String getNotesContent() { return textArea.getText(); }
    public void addCancelListener(ActionListener listener) { btnCancel.addActionListener(listener); }
    public void addSaveListener(ActionListener listener) { btnSave.addActionListener(listener); }
}