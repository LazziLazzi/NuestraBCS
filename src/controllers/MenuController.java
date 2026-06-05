package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import views.MenuView;
import windows.CategoryWindow;
import windows.LoginWindow;
import views.LoginView;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;

        // Animales actuales de la base de datos
        String[] animalesBCS = {"Liebre Negra de Espíritu Santo", "Ratón de la Isla Santa Catalina", "Lobo Marino Californiano", "Berrendo Peninsular", "Zafiro Bajacaliforniano", "Cuicacoche Bajacaliforniano"};
        String[] plantasBCS = {"Cardón Gigante", "Pitaya de Baja California", "Biznaga Barril Delgada", "Biznaga Llavina", "Agave de la Costa", "Lentisco"};
        String[] insectosBCS = {"Chinche Chichona", "Mariposa Bufón de Alas Azules", "Libélula Zurcidora de Baja California", "Mariposa Jaspeada", "Mariposa Metalmark", "Coccobius sp"};
        String[] aracnidosBCS = {"Araña Violinista", "Araña Errante", "Araña Plateada de Jardín", "Araña Tejedora Espinosa", "Araña Saltarina Fénix", "Araña Cazadora Dorada"};
        
        // se conecta cada botón con su respectiva acción y sus datos
        this.view.addAnimalesListener(new OpenCategoryAction("Animales", animalesBCS));
        this.view.addPlantasListener(new OpenCategoryAction("Plantas", plantasBCS));
        this.view.addInsectosListener(new OpenCategoryAction("Insectos", insectosBCS));
        this.view.addAracnidosListener(new OpenCategoryAction("Arácnidos", aracnidosBCS));
        this.view.addCloseSesionListener(null );
        
        // Conecta para que cierre sesion
        this.view.addCloseSesionListener(new closeSesion());
    }

    //Hace el cambio de ventanas entre el menu y la ventana de categoria
    private class OpenCategoryAction implements ActionListener {
        private String categoryTitle;
        private String[] items;
        //Trae los datos de la categoria
        public OpenCategoryAction(String categoryTitle, String[] items) {
            this.categoryTitle = categoryTitle;
            this.items = items;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtenemos la ventana actual del menú
            JFrame currentWindow = (JFrame) SwingUtilities.getWindowAncestor(view);
            
            CategoryWindow window = new CategoryWindow(currentWindow, categoryTitle, items);
            window.setVisible(true); 
            currentWindow.setVisible(false); 
        }
    }

    //Cierre de sesion
    private class closeSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(view, 
                "¿Está seguro que desea cerrar sesión?", "Cerrar Sesión", 
                JOptionPane.YES_NO_OPTION);
                
            if (option == JOptionPane.YES_OPTION) {
                SwingUtilities.getWindowAncestor(view).dispose(); 
                
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.setVisible(true);
            }
        }
    }
}
