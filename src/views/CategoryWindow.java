package views;

import javax.swing.JFrame;

public class CategoryWindow extends JFrame {
    
    public CategoryWindow(String title, String[] items) {
        setTitle("NuestraBCS - " + title);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        CategoryView categoryPanel = new CategoryView(title, items);
        add(categoryPanel);
    }
}
