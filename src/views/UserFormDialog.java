package views;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import models.User;
import utils.Colors;

public class UserFormDialog extends JDialog {
    private JTextField txtName, txtLastNameP, txtLastNameM, txtUsername, txtEmail, txtDate;
    private JPasswordField txtPassword;
    private JComboBox<String> cbGender;
    private JButton btnSave, btnCancel;
    private JButton btnSelectImage;
    private JLabel lblImagePreview;
    private String selectedImagePath = "";
    
 // Fuentes modernas
    private Font titleFont = new Font("Arial", Font.BOLD, 18);
    private Font labelFont = new Font("Arial", Font.BOLD, 13);
    private Font fieldFont = new Font("Arial", Font.PLAIN, 13);

    public User user;
    private boolean saved = false;
    
    public UserFormDialog(JFrame parent, User user) {
    		super(parent, true);
        this.user = user;
        setTitle(user == null ? "Agregar usuario" : "Editar usuario");
        
        setSize(400, 450);
        setLocationRelativeTo(parent);
     
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Colors.lightGreen());
        setContentPane(contentPane);
        
        contentPane.add(createTitlePanel(), BorderLayout.NORTH);
        contentPane.add(createFormPanel(), BorderLayout.CENTER);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
        
        loadData();
    }
    
    private JPanel createTitlePanel() {
    	JPanel panel = new JPanel();
        panel.setBackground(Colors.darkGreen()); 
        panel.setPreferredSize(new Dimension(0, 50));
        
        JLabel title = new JLabel("DATOS DEL USUARIO");
        title.setForeground(Color.WHITE);
        title.setFont(titleFont);
        panel.add(title);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        panel.setBackground(Colors.lightGreen());
        
        btnSave = new JButton("Guardar");
        btnCancel = new JButton("Cancelar");
        
        // Estilo de botones
        styleButton(btnSave, Colors.green(), Color.WHITE);
        styleButton(btnCancel, new Color(231, 76, 60), Color.WHITE);
        
        panel.add(btnCancel);
        panel.add(btnSave);
        
        btnSave.addActionListener(e -> save());
        btnCancel.addActionListener(e -> dispose());
        
        return panel;
    }
    
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(labelFont);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private JScrollPane createFormPanel() {
    	JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Colors.lightGreen());

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Colors.lightGreen());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txtName = createStyledField();
        txtLastNameP = createStyledField();
        txtLastNameM = createStyledField();
        txtUsername = createStyledField();
        txtEmail = createStyledField();
        txtDate = createStyledField();
        txtPassword = new JPasswordField();
        txtPassword.setFont(fieldFont);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.green()), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        cbGender = new JComboBox<>(new String[] { "Masculino", "Femenino", "Otro" });
        cbGender.setFont(fieldFont);
        cbGender.setBackground(Color.WHITE);

        panel.add(createField("Nombre:", txtName));
        panel.add(createField("Apellido Paterno:", txtLastNameP));
        panel.add(createField("Apellido Materno:", txtLastNameM));
        panel.add(createField("Usuario:", txtUsername));
        panel.add(createField("Género:", cbGender));
        panel.add(createField("Email:", txtEmail));
        panel.add(createField("Fecha de Nacimiento:", txtDate));
        panel.add(createField("Contraseña:", txtPassword));
        
        btnSelectImage = new JButton("Seleccionar Foto de Perfil");
        styleButton(btnSelectImage, Colors.lemonGreen(), Colors.darkGreen());
        
        lblImagePreview = new JLabel("Sin imagen");
        lblImagePreview.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblImagePreview.setForeground(Color.GRAY);
        
        btnSelectImage.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(this);
            if(res == JFileChooser.APPROVE_OPTION){
                selectedImagePath = chooser.getSelectedFile().getAbsolutePath();
                lblImagePreview.setText("✓ Imagen seleccionada");
                lblImagePreview.setForeground(Colors.darkGreen());
            }
        });

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagePanel.setBackground(Colors.lightGreen());
        imagePanel.add(btnSelectImage);
        imagePanel.add(lblImagePreview);
        panel.add(imagePanel);
        
        return scroll;
    }
    
    private JTextField createStyledField() {
        JTextField field = new JTextField();
        field.setFont(fieldFont);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colors.green()), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return field;
    }
    
    
    private JPanel createField(String labelText, Component field) {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.lightGreen());
        panel.setLayout(new BorderLayout(0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Colors.darkGreen());

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
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
            
            if(user.getImagePath() != null && !user.getImagePath().isEmpty()) {
                selectedImagePath = user.getImagePath();
                lblImagePreview.setText("Imagen cargada");
                lblImagePreview.setForeground(Colors.darkGreen());
            }
    		}
    }
    
    private void save() {
    
        String name = txtName.getText().trim();
        String lastNameP = txtLastNameP.getText().trim();
        String lastNameG = txtLastNameM.getText().trim();
        String userName = txtUsername.getText().trim();
        String date = txtDate.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String gender = (String) cbGender.getSelectedItem();

        
        if (name.isEmpty() || lastNameP.isEmpty() || lastNameG.isEmpty() || userName.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos personales.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return; // Detiene el método, no guarda nada y mantiene la ventana abierta
        }

        
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un correo electrónico válido.", "Correo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

       
        if (password.isEmpty() || password.length() < 8) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres.", "Contraseña débil", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        user = new User(name, lastNameP, lastNameG, userName, date, email, password, gender, selectedImagePath);
        
        saved = true;
        dispose(); // Cierra la ventana solo si todo salió bien
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
