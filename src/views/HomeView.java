package views;

import javax.swing.*;
import java.awt.*;
import models.UserTableModel;

//Es la ventana que muestra la tabla con la información de todos los usuarios.

public class HomeView extends JFrame {
    private JTable tableUsers;
    private JButton btnLoad;

    public HomeView() {
        setTitle("Home view");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        //Estilo de la tabla(Lala)
        tableUsers = new  JTable();
        tableUsers.setRowHeight(25);
        tableUsers.setGridColor(new Color(200, 200, 200));
        
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(scrollPane, BorderLayout.CENTER);
        
        
        //Panel para el boton
        btnLoad = new JButton("Actualizar");
        btnLoad.setPreferredSize(new Dimension(120,30));
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBtn.add(btnLoad);
        add(pnlBtn, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public void setTableModel(UserTableModel model) {
        tableUsers.setModel(model);
    }

    public void addReloadListener(java.awt.event.ActionListener l) {
        btnLoad.addActionListener(l);
    }
}