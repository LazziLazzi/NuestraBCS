package windows;

import javax.swing.JFrame;
import controllers.CategoryController;
import views.CategoryView;

public class CategoryWindow extends JFrame {
    
    public CategoryWindow(JFrame previousWindow, String title, String[] items) {
        setTitle("NuestraBCS - " + title);
        setSize(600, 650); 
        setResizable(false);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        CategoryView categoryPanel = new CategoryView(title, items);
        CategoryController controller = new CategoryController(categoryPanel, this, previousWindow);
        add(categoryPanel);
    }
}
