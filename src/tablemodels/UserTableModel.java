package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Usuario;

public class UserTableModel extends AbstractTableModel{

	private List<Usuario> users;
	
	private final String[] columns = {
		"Nombre",
		"Email"
	};
	
	public UserTableModel(List<Usuario> users) {
		this.users = users;
	}
	
	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Usuario user = users.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return user.getNombre();
		case 1:
			return user.getApellido();
		}
		
		return null;
	}
	
	public Usuario getUserAt(int row) {
		return users.get(row);
	}
	
	public void setUsers(List<Usuario> users) {
		this.users = users;
		fireTableDataChanged();
	}
	
	

	
	
}
