package model.collections;

public interface ITableData {

	String[] getColumnNames();
	
	int getRowCount();
	
	Object getValueAt(int rowIndex, int columnIndex);
	
	Class<?> getColumnClass(int columnIndex);
	
}
