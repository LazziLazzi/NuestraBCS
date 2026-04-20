package views;

import javax.swing.*;
import java.awt.*;
import models.User;

public class UserFormDialog extends JDialog {
    private JTextField txtName, txtLastNameP, txtLastNameM, txtUsername, txtEmail, txtDate;
    private JPasswordField txtPassword;
    private JComboBox<String> cbGender;
    private JButton btnSave, btnCancel;

    public UserFormDialog(JFrame parent, User user) {
        //Si user es igual a null pone agregar, si no dice editar
        super(parent, user == null ? "Agregar Usuario" : "Editar Usuario", true);
        
        setSize(400, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        //Formulario
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Nombre:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Apellido Paterno:"));
        txtLastNameP = new JTextField();
        formPanel.add(txtLastNameP);

        formPanel.add(new JLabel("Apellido Materno:"));
        txtLastNameM = new JTextField();
        formPanel.add(txtLastNameM);

        formPanel.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Género:"));
        cbGender = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        formPanel.add(cbGender);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Nacimiento:"));
        txtDate = new JTextField();
        formPanel.add(txtDate);

        formPanel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        //Si se edita, llenamos los campos con los datos del usuario
        if (user != null) {
            txtName.setText(user.getName());
            txtLastNameP.setText(user.getLastNameP());
            txtLastNameM.setText(user.getLastNameM());
            txtUsername.setText(user.getUsername());
            cbGender.setSelectedItem(user.getGender());
            txtEmail.setText(user.getEmail());
            txtDate.setText(user.getBirthDate());
        }

        add(formPanel, BorderLayout.CENTER);

        //Botones
        JPanel btnPanel = new JPanel();
        btnSave = new JButton("Guardar");
        btnCancel = new JButton("Cancelar");
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

        //Cierra la ventana
        btnCancel.addActionListener(e -> dispose());
    }

    public JButton getBtnSave() { 
    		return btnSave; 
    	}

}
