package view.util;

import javax.swing.table.AbstractTableModel;

import model.collections.ITableData;

@SuppressWarnings("serial")
public class CustomTableModel extends AbstractTableModel {
	
	private String[] columns;
	private ITableData tableData;
	
	public CustomTableModel(ITableData tableData) {
		this.columns = tableData.getColumnNames();
		this.tableData = tableData;
	}
	
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public int getRowCount() {
		return tableData.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData.getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public boolean isCellEditable(int row, int column){  
        return false;  
    }

	@Override
	public Class<?> getColumnClass(int paramInt) {
		return tableData.getColumnClass(paramInt);
	}

}
