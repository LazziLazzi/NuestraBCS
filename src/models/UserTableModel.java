package models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel{

	
	//Los nombres de las columnas  de la tabla 
	private final String[] columnNames = {
		"Nombre", "Usuario", "Genero", "Email", "Fecha de cumplanios"	
	};
	
	private List<User> userList;
	
	public UserTableModel(List<User> userList) {
		this.userList = userList;
	}
	
	@Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    //Asigna los atributos del User a su columna correspondiente
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getName() + " " + user.getLastNameP();
            case 1: return user.getUsername();
            case 2: return user.getGender();
            case 3: return user.getEmail();
            case 4: return user.getBirthDate();
            default: return null;
        }
    }
	
}
