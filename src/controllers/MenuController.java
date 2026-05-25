package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.MenuView;
import views.CategoryWindow;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;

        // Animales random
        String[] animalesBCS = {"Ballena Gris", "Borrego Cimarrón", "Zorra del Desierto", "Lobo Marino", "Coyote", "Liebre Rabona"};
        String[] plantasBCS = {"Cardón Gigante", "Palo Blanco", "Biznaga", "Torote", "Lomboy", "Pitahaya"};
        String[] insectosBCS = {"Avispa Caza-Tarántulas", "Escarabajo", "Mariposa Monarca", "Abeja", "Hormiga", "Mantis Religiosa"};
        String[] aracnidosBCS = {"Tarántula", "Viuda Negra", "Araña Violinista", "Alacrán", "Garrapata", "Ácaro"};

        // se conecta cada botón con su respectiva acción y sus datos
        this.view.addAnimalesListener(new OpenCategoryAction("Animales", animalesBCS));
        this.view.addPlantasListener(new OpenCategoryAction("Plantas", plantasBCS));
        this.view.addInsectosListener(new OpenCategoryAction("Insectos", insectosBCS));
        this.view.addAracnidosListener(new OpenCategoryAction("Arácnidos", aracnidosBCS));
    }

    private class OpenCategoryAction implements ActionListener {
        private String categoryTitle;
        private String[] items;

        public OpenCategoryAction(String categoryTitle, String[] items) {
            this.categoryTitle = categoryTitle;
            this.items = items;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Se crea la ventana con la categoría que corresponda al botón que se selecciono
            CategoryWindow window = new CategoryWindow(categoryTitle, items);
            window.setVisible(true); 

            // javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
        }
    }
}
