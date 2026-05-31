package windows;

import java.awt.*;
import javax.swing.*;

import views.UsersView;

public class MainWindow extends JFrame {
    
    //Para cambiar de pestanias
    public static final String HOME = "Home";
    public static final String USERS = "Usuarios";

    //Botones
    public JButton btnHome;
    public JButton btnUsers;
    public UsersView usersPanel;

    private CardLayout cardLayout;
    private JPanel container;

    public MainWindow() {
        setSize(850, 550);
        setTitle("NuestraBCS - Sistema de Administración");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createNavbar();
        createViews();

        setVisible(true);
    }

    public void createNavbar() {
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnHome = new JButton("Inicio");
        btnUsers = new JButton("Usuarios");

        navbar.add(btnHome);
        navbar.add(btnUsers);

        add(navbar, BorderLayout.NORTH);

        //Al hacer click cambia de vista
        btnHome.addActionListener(e -> showView(HOME));
        btnUsers.addActionListener(e -> showView(USERS));
    }

    private void createViews() {
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        //Inicio
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.add(new JLabel("Bienvenido al Sistema de NuestraBCS"));

        //Panel de usuarios
        usersPanel = new UsersView();

        container.add(homePanel, HOME);
        container.add(usersPanel, USERS);

        add(container, BorderLayout.CENTER);
    }

    public void showView(String view) {
        cardLayout.show(container, view);
    }
}
