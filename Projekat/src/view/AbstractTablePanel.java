package view;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import model.Podaci;
import model.util.TipKorisnika;
import view.util.CustomTableModel;
import view.util.TableHeader;

@SuppressWarnings("serial")
public abstract class AbstractTablePanel extends JPanel {

	protected AbstractPanel parent;
	protected CustomTableModel tableModel;
	protected JTable table;
	protected RowFilter<Object, Object> initialRF;
	protected TableRowSorter<CustomTableModel> tableRowSorter;
	protected int obrisanColumnIndex;
	
	protected AbstractTablePanel(AbstractPanel parent, int obrisanColumnIndex) {
		this.parent = parent;
		this.obrisanColumnIndex = obrisanColumnIndex;
	}
	
	protected void initGui() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		table.setAutoCreateRowSorter(true);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setBorder(new MatteBorder(2,0,0,0, Color.BLACK));
		table.setBackground(Color.decode("#B2B2B2"));
		table.setForeground(Color.BLACK);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.setDefaultRenderer(new TableHeader(header.getDefaultRenderer()));
		tableRowSorter = new TableRowSorter<CustomTableModel>(tableModel);
		
		clearRowFilters();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.GRAY);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);
	}
	
	public AbstractPanel getParent() {
		return parent;
	}

	public CustomTableModel getTableModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}
	
	public int getObrisanColumnIndex() {
		return obrisanColumnIndex;
	}
	
	public void filterRows(List<RowFilter<Object,Object>> filters) {
		if (!TipKorisnika.ADMIN.equals(Podaci.getInstance().getLoggedInUser().getTip())) {
			filters.add(initialRF);
		}
		tableRowSorter.setRowFilter(RowFilter.andFilter(filters));
		table.setRowSorter(tableRowSorter);
	}
	
	public void clearRowFilters() {
		if (!TipKorisnika.ADMIN.equals(Podaci.getInstance().getLoggedInUser().getTip())) {
			tableRowSorter.setRowFilter(initialRF);
		} else {
			tableRowSorter.setRowFilter(null);
		}
		table.setRowSorter(tableRowSorter);
	}
	
}
