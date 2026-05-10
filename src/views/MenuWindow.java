package views;

import javax.swing.JFrame;

import controllers.MenuController;

public class MenuWindow extends JFrame{
	public MenuWindow() {
		setTitle("NuestraBCS - Menú");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        MenuView menuPanel = new MenuView();
        MenuController controlador = new MenuController(menuPanel);
        add(menuPanel);
        
        setSize(400, 500); 
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int option = javax.swing.JOptionPane.showConfirmDialog(null, 
                    "¿Desea salir de NuestraBCS?", "Salir", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
                if (option == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });        
	}		
}