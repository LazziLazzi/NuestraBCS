package models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel{
	
	//Los nombres de las columnas de la tabla 
	private final String[] columnNames = {
		"Apellido Paterno", "Apellido Materno", "Nombre",
		"Usuario", "Género", "Email", "Fecha de nacimiento"	
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
	        case 0: return user.getLastNameP();
	        case 1: return user.getLastNameM();
	        case 2: return user.getName();
	        case 3: return user.getUsername();
	        case 4: return user.getGender();
	        case 5: return user.getEmail();
	        case 6: return user.getBirthDate();
	        default: return null;
        }
    }
    
    public User getUserAt(int rowIndex) { 
        return userList.get(rowIndex); 
    }
    
    public void setUsers(List<User> users) {
        this.userList = users;
        fireTableDataChanged();
    }
    
}
