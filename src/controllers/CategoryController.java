package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.CategoryView;
import views.SpeciesDetailWindow;

public class CategoryController {
    private CategoryView view;

    public CategoryController(CategoryView view) {
        this.view = view;
        // Le asignamos el listener a todos los botones guardados en la lista de la vista
        this.view.addSpeciesListener(new SpeciesAction());
    }

    private class SpeciesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Captura el nombre exacto escrito en el botón (ej. "Ballena Gris")
            String nombreEspecie = e.getActionCommand();
            
            // Variables provisionales en lo que integran la base de datos
            String scientificName = "Desconocido";
            String kingdom = "Desconocido";
            String phylum = "Desconocido";
            String speciesClass = "Desconocido";
            String family = "Desconocido";
            String genus = "Desconocido";
            String description = "Descripción provisional en lo que se conecta la base de datos.";

            // Simulación de consulta a la base de datos
            if (nombreEspecie.equals("Ballena Gris")) {
                scientificName = "Eschrichtius robustus";
                kingdom = "Animalia";
                phylum = "Chordata";
                speciesClass = "Mammalia"; // Nota: si en tu ventana se llama speciesClass, mapealo ahí
                family = "Eschrichtiidae";
                genus = "Eschrichtius";
                description = "La ballena gris es un mamífero marino que realiza una de las migraciones más largas del mundo, llegando a las lagunas de Baja California Sur para reproducirse.";
            } else if (nombreEspecie.equals("Cardón Gigante")) {
                scientificName = "Pachycereus pringlei";
                kingdom = "Plantae";
                phylum = "Tracheophyta";
                speciesClass = "Magnoliopsida";
                family = "Cactaceae";
                genus = "Pachycereus";
                description = "El cardón gigante es una de las plantas más emblemáticas y grandes del desierto de la península de Baja California.";
            } else if (nombreEspecie.equals("Avispa Caza-Tarántulas")) {
                scientificName = "Pepsis formosa";
                kingdom = "Animalia";
                phylum = "Arthropoda";
                speciesClass = "Insecta";
                family = "Pompilidae";
                genus = "Pepsis";
                description = "Este insecto es famoso en la región por su potente picadura y por cazar tarántulas para alimentar a sus larvas.";
            }

            // se crea la ventana y se le pasan la info correspondiente
            SpeciesDetailWindow detailWindow = new SpeciesDetailWindow(
                nombreEspecie, scientificName, kingdom, phylum, speciesClass, family, genus, description
            );
            detailWindow.setVisible(true);
        }
    }
}