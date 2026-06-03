package windows;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import views.SpeciesDetailView;

public class SpeciesDetailWindow extends JFrame {

    private SpeciesDetailView detailPanel;

    public SpeciesDetailWindow(String name, String scientificName, String kingdom, 
            String phylum, String speciesClass, String family, 
            String genus, String description, String bannerPath) {

		setTitle("NuestraBCS - " + name);
		setSize(600, 650);
		setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        try {
            Image icon = new ImageIcon(getClass().getResource("/images/BCS.png")).getImage();
            setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el icono");
        }
        
        this.detailPanel = new SpeciesDetailView(
            name, scientificName, kingdom, phylum, speciesClass, family, genus, description, bannerPath
        );
        add(this.detailPanel);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int option = javax.swing.JOptionPane.showConfirmDialog(null, 
                    "¿Desea salir de NuestraBCS?", "Salir", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
                if (option == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0); // para cerrar toda la aplicación por completo
                }
            }
        });        
    }

    public SpeciesDetailView getDetailPanel() {
        return detailPanel;
    }
}