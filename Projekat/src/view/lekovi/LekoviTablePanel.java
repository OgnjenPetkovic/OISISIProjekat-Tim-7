package view.lekovi;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Podaci;
import model.entity.Lek;
import model.util.TipKorisnika;
import view.AbstractDetailsPanel;
import view.AbstractTablePanel;
import view.util.CustomTableModel;
import view.util.DetailsFormState;
import view.util.renderers.CustomCellRenderer;

@SuppressWarnings("serial")
public class LekoviTablePanel extends AbstractTablePanel {
	
	public LekoviTablePanel(LekoviPanel parent) {
		super(parent);
		tableModel = new CustomTableModel(Podaci.getInstance().getLekovi());
		table = new JTable(tableModel);
		ListSelectionModel lsm = table.getSelectionModel();
	    lsm.addListSelectionListener(new LekoviTableLSListener());
	    table.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRenderer());
	    
	    if (!TipKorisnika.ADMIN.equals(Podaci.getInstance().getLoggedInUser().getTip())) {
			table.removeColumn(table.getColumnModel().getColumn(5));
		}
		initialRF = RowFilter.regexFilter("Ne", 5);
	    
		initGui();
	}
	
	@Override
	protected boolean isObrisan(String uniqueColumnValue) {
		Lek lek = Podaci.getInstance().getLekovi().findBySifra((String)uniqueColumnValue);
		if (lek != null) {
			return lek.isObrisan();
		} else {
			return true;
		}
	}

	private class LekoviTableLSListener implements ListSelectionListener {

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
	        	String sifra = (String) table.getValueAt(row, 0);
	        	Lek selected = Podaci.getInstance().getLekovi().findBySifra(sifra);
	        	if (selected != null) {
	        		detailsPanel.insertTableData(selected);
	        	}
			}
		}
		
	}
}
