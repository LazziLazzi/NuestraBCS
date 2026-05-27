package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.MenuView;
import views.CategoryWindow;
import views.LoginView;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;

        // Animales random
        String[] animalesBCS = {"Liebre Negra de Espíritu Santo", "Ratón de la Isla Santa Catalina", "Lobo Marino Californiano", "Berrendo Peninsular", "Zafiro Bajacaliforniano", "Cuicacoche Bajacaliforniano"};
        String[] plantasBCS = {"Cardón Gigante", "Pitaya de Baja California", "Biznaga Barril Delgada", "Biznaga Llavina", "Agave de la Costa", "Lentisco"};
        String[] insectosBCS = {"Chinche Chichona", "Mariposa Bufón de Alas Azules", "Libélula Zurcidora de Baja California", "Mariposa Jaspeada", "Mariposa Metalmark", "Coccobius sp"};
        String[] aracnidosBCS = {"Araña Violinista", "Araña Errante", "Araña Plateada de Jardín", "Araña Tejedora Espinosa", "Araña Saltarina Fénix", "Araña Cazadora Dorada"};
        
        // se conecta cada botón con su respectiva acción y sus datos
        this.view.addAnimalesListener(new OpenCategoryAction("Animales", animalesBCS));
        this.view.addPlantasListener(new OpenCategoryAction("Plantas", plantasBCS));
        this.view.addInsectosListener(new OpenCategoryAction("Insectos", insectosBCS));
        this.view.addAracnidosListener(new OpenCategoryAction("Arácnidos", aracnidosBCS));
        this.view.addCerrarSesionListener(null );
        
        // Conecta para que cierre sesion
        this.view.addCerrarSesionListener(new closeSesion());
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
    
    private class closeSesion implements ActionListener{
    	@Override
        public void actionPerformed(ActionEvent e) {
            // Se crea la ventana con la categoría que corresponda al botón que se selecciono
            LoginView window = new LoginView();
            window.setVisible(true);
          
            javax.swing.SwingUtilities.getWindowAncestor(view).dispose();
        }
    }
}
