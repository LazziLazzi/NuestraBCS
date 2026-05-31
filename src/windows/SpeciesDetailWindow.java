package windows;

import javax.swing.JFrame;
import views.SpeciesDetailView;

public class SpeciesDetailWindow extends JFrame {

    private SpeciesDetailView detailPanel;

    public SpeciesDetailWindow(javax.swing.JFrame previousWindow, String name, String scientificName, String kingdom, 
            String phylum, String speciesClass, String family, 
            String genus, String description, String bannerPath) {

		setTitle("NuestraBCS - " + name);
		setSize(600, 650);
		setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Guardamos el panel instanciado en nuestra variable global
        this.detailPanel = new SpeciesDetailView(
            name, scientificName, kingdom, phylum, speciesClass, family, genus, description, bannerPath
        );
        
        add(this.detailPanel);
    }

    // Método para exponer la vista al controlador
    public SpeciesDetailView getDetailPanel() {
        return detailPanel;
    }
}