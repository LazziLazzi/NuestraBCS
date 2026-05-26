package views;

import javax.swing.JFrame;

public class SpeciesDetailWindow extends JFrame {

    public SpeciesDetailWindow(String name, String scientificName, String kingdom, 
                               String phylum, String speciesClass, String family, 
                               String genus, String description) {
        
        setTitle("NuestraBCS - Detalle de " + name);
        setSize(600, 650);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        SpeciesDetailView detailPanel = new SpeciesDetailView(
            name, scientificName, kingdom, phylum, speciesClass, family, genus, description
        );
        
        add(detailPanel);
    }
}