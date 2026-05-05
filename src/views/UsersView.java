package views;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

import models.UserTableModel;
import utils.AppFont;

public class UsersView extends JPanel {
    private JTable table;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnPdf;

    public UsersView() {
   
        //Configura el panel principal
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Tabla central
        table = new JTable();
        styleTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        //Panel para los botones debajo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Agregar Usuario");
        btnEdit = new JButton("Editar Seleccionado");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnPdf);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    //MEtodo que abre la ventana para guardar el archvo
    public File selectPdfFile() {
        String path = System.getProperty("user.home") + "/Desktop"; 
        JFileChooser chooser = new JFileChooser(path);
        
        chooser.setSelectedFile(new File("Reporte_Usuarios.pdf"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF", "pdf");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        
        int option = chooser.showDialog(this, "Guardar PDF");
        
        if(option != JFileChooser.APPROVE_OPTION) {
            return null; // Si cancela regrsa null
        }
        
        File file = chooser.getSelectedFile();
        
        // Asegura que termine en .pdf
        if(!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }
        
        return file;
    }
    
 
    private void styleTable() {
        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(AppFont.normal());
        
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Estilo del encabezado
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.bold());
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);
        
        //Color de las celdas
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245));
                    }
                    c.setForeground(Color.BLACK);
                }
                
                // Hace que la columna 1 resalte en azul y negritas
                if(column == 1) {
                    c.setFont(AppFont.bold());
                    if(!isSelected) {
                        c.setForeground(new Color(41, 128, 185));
                    }
                } else {
                    c.setFont(AppFont.normal());
                }
            
                return c;
            }
        });
    }

    public void setTableModel(UserTableModel model) {
        table.setModel(model);
    }

    public JTable getTable() { 
    		return table; 
    	}
    public JButton getBtnAdd() { 
    		return btnAdd; 
    	}
    public JButton getBtnEdit() { 
    		return btnEdit; 
    	}
    public JButton getBtnDelete() { 
    		return btnDelete; 
    	}
    public JButton getBtnPdf() {
    		return btnPdf; 
    	} 
    
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
    
}
