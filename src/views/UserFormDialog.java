package views;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import models.User;

public class UserFormDialog extends JDialog {
    private JTextField txtName, txtLastNameP, txtLastNameM, txtUsername, txtEmail, txtDate;
    private JPasswordField txtPassword;
    private JComboBox<String> cbGender;
    private JButton btnSave, btnCancel;

    public User user;
    private boolean saved = false;
    
    public UserFormDialog(JFrame parent, User user) {
    		super(parent, true);
        this.user = user;
        setTitle(user == null ? "Agregar usuario" : "Editar usuario");
        
        setSize(400, 450);
        setLocationRelativeTo(parent);
     
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        
        contentPane.add(createTitlePanel(), BorderLayout.NORTH);
        contentPane.add(createFormPanel(), BorderLayout.CENTER);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
        
        loadData();
    }
    
    private JPanel createTitlePanel() {
    		JPanel panel = new  JPanel();
    		panel.add(new JLabel("Formulario de Usuario"));
    		return panel;
    }
    
    private JPanel createButtonPanel() {
    		JPanel panel = new JPanel();
    		btnSave = new JButton("Guardar");
    		btnCancel = new JButton("Cancelar");
    		
    		panel.add(btnSave);
    		panel.add(btnCancel);
    		
    		btnSave.addActionListener(e -> save());
    		btnCancel.addActionListener(e -> dispose());
    		
    		return panel;
    }
    
    private JScrollPane createFormPanel() {
    		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(14);

        txtName = new JTextField();
        txtLastNameP = new JTextField();
        txtLastNameM = new JTextField();
        txtUsername = new JTextField();
        txtEmail = new JTextField();
        txtDate = new JTextField();
        txtPassword = new JPasswordField();
        
        cbGender = new JComboBox<>(new String[] { "Masculino", "Femenino", "Otro" });

        panel.add(createField("Nombre:", txtName));
        panel.add(createField("Apellido Paterno:", txtLastNameP));
        panel.add(createField("Apellido Materno:", txtLastNameM));
        panel.add(createField("Usuario:", txtUsername));
        panel.add(createField("Género:", cbGender));
        panel.add(createField("Email:", txtEmail));
        panel.add(createField("Fecha de Nacimiento:", txtDate));
        panel.add(createField("Contraseña:", txtPassword));
        
        return scroll;
    }
    
    private JPanel createField(String labelText, Component field) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(field);
        return panel;
    }
    
    private void loadData() {
    		if(user != null) {
    			txtName.setText(user.getName());
    			txtLastNameP.setText(user.getLastNameP());
            txtLastNameM.setText(user.getLastNameM());
            txtUsername.setText(user.getUsername());
            cbGender.setSelectedItem(user.getGender());
            txtEmail.setText(user.getEmail());
            txtDate.setText(user.getBirthDate());
            txtPassword.setText(user.getPassword());
    		}
    }
    
    private void save() {
		String name = txtName.getText();
		String email = txtEmail.getText();
	    String gender = (String) cbGender.getSelectedItem();
	    String lastNameP = txtLastNameP.getText();
	    String lastNameG = txtLastNameM.getText();
	    String userName = txtUsername.getText();
	    String date = txtDate.getText();
	    
	
	    user = new User(name, lastNameP, lastNameG, userName, date, email, gender);
        
        saved = true;
        dispose();
    	}

    public JButton getBtnSave() { 
    		return btnSave; 
    	}
    
    public boolean isSaved() {
    		return saved;
    }
    
    public User getUser() {
    		return user;
    }
    

}
