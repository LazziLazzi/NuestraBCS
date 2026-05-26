package views;

import javax.swing.JFrame;
import controllers.CategoryController; // <--- ASEGÚRATE DE IMPORTARLO

public class CategoryWindow extends JFrame {
    
    public CategoryWindow(String title, String[] items) {
        setTitle("NuestraBCS - " + title);
        setSize(600, 650); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        CategoryView categoryPanel = new CategoryView(title, items);
  
        CategoryController controlador = new CategoryController(categoryPanel);
        
        add(categoryPanel);
    }
}
