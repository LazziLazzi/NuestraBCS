package windows;

import java.awt.*;
import javax.swing.*;
import views.UsersView;

public class MainWindow extends JFrame {
    
	// Panel principal
    public UsersView usersPanel;

    public MainWindow() {
        setSize(850, 550);
        setTitle("NuestraBCS - Sistema de Administración");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Cargar la imagen del icono
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/BCS.png")).getImage();
            setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el icono");
        }

        // Agregamos directamente el panel de usuarios para que ocupe todo
        usersPanel = new UsersView();
        add(usersPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
