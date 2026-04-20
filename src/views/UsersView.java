package views;

import javax.swing.*;
import java.awt.*;
import models.UserTableModel;

public class UsersView extends JPanel {
    private JTable table;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    public UsersView() {
        //Configura el panel principal
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Tabla central
        table = new JTable();
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        //Panel para los botones debajo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Agregar Usuario");
        btnEdit = new JButton("Editar Seleccionado");
        btnDelete = new JButton("Eliminar");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    //Metodos para que el controlador acceda ala vista
    public void setTableModel(UserTableModel model) {
        table.setModel(model);
    }

    public JTable getTable() { 
    		return table; 
    	}
    public JButton getBtnAdd() { 
    		return btnAdd; 
    	}
    public JButton getBtnEdit() { 
    		return btnEdit; 
    	}
    public JButton getBtnDelete() { 
    		return btnDelete; 
    	}
}
