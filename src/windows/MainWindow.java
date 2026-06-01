package windows;

import java.awt.*;
import javax.swing.*;

import views.UsersView;

public class MainWindow extends JFrame {
    
	// Panel principal (Único)
    public UsersView usersPanel;

    public MainWindow() {
        setSize(850, 550);
        setTitle("NuestraBCS - Sistema de Administración");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregamos directamente el panel de usuarios para que ocupe todo
        usersPanel = new UsersView();
        add(usersPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
