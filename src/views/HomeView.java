package views;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {
    private JTextArea txtUsers;
    private JButton btnLoad;

    public HomeView() {
        setTitle("Home view");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Para mostrar el jtextarea
        txtUsers = new JTextArea();
        txtUsers.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtUsers);
        add(scrollPane, BorderLayout.CENTER);
        
        //boton prueba
        btnLoad = new JButton("Cargar");
        add(btnLoad, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public void setUsersText(String text) {
        txtUsers.setText(text);
    }

    public void addLoadListener(java.awt.event.ActionListener listener) {
        btnLoad.addActionListener(listener);
    }
}