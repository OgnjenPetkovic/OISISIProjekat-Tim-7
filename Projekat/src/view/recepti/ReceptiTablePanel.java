package view.recepti;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Podaci;
import model.entity.Recept;
import model.util.TipKorisnika;
import view.AbstractDetailsPanel;
import view.AbstractTablePanel;
import view.util.CustomTableModel;
import view.util.DetailsFormState;
import view.util.renderers.CustomCellRenderer;

@SuppressWarnings("serial")
public class ReceptiTablePanel extends AbstractTablePanel {

	public ReceptiTablePanel(ReceptiPanel parent) {
		super(parent);
		tableModel = new CustomTableModel(Podaci.getInstance().getRecepti());
		table = new JTable(tableModel);
		ListSelectionModel lsm = table.getSelectionModel();
	    lsm.addListSelectionListener(new ReceptiTableLSListener());
	    table.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer());
	    table.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer());
	    table.getColumnModel().getColumn(5).setCellRenderer(new CustomCellRenderer());
	    table.getColumnModel().getColumn(6).setCellRenderer(new CustomCellRenderer());
	    
	    if (!TipKorisnika.ADMIN.equals(Podaci.getInstance().getLoggedInUser().getTip())) {
			table.removeColumn(table.getColumnModel().getColumn(7));
		}
		initialRF = RowFilter.regexFilter("Ne", 7);
	    
		initGui();
	}
	
	@Override
	protected boolean isObrisan(String uniqueColumnValue) {
		Recept recept = Podaci.getInstance().getRecepti().findBySifra(Integer.parseInt(uniqueColumnValue));
		if (recept != null) {
			return recept.isObrisan();
		} else {
			return true;
		}
	}
	
	private class ReceptiTableLSListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				AbstractDetailsPanel detailsPanel = parent.getContentPanel().getDetails();
				DetailsFormState formState = detailsPanel.getState();
				if (DetailsFormState.ADD.equals(formState) || DetailsFormState.EDIT.equals(formState)) {
					return;
				}
	        	int row = table.getSelectedRow();
	        	if (row == -1) // ništa nije selektovano 
	        		return;
	        	int sifra = (Integer) table.getValueAt(row, 0);
	        	Recept selected = Podaci.getInstance().getRecepti().findBySifra(sifra);
	        	if (selected != null) {
	        		detailsPanel.insertTableData(selected);
	        	}
			}
		}
		
	}

}
