package view.korisnici;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Podaci;
import model.entity.Korisnik;
import view.AbstractDetailsPanel;
import view.AbstractTablePanel;
import view.util.CustomTableModel;
import view.util.DetailsFormState;
import view.util.renderers.CustomCellRenderer;

@SuppressWarnings("serial")
public class KorisniciTablePanel extends AbstractTablePanel {
	
	public KorisniciTablePanel(KorisniciPanel parent) {
		super(parent, 5);
		tableModel = new CustomTableModel(Podaci.getInstance().getKorisnici());
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRenderer());
		ListSelectionModel lsm = table.getSelectionModel();
	    lsm.addListSelectionListener(new KorisniciTableLSListener());
	    
		initGui();
	}
	
	private class KorisniciTableLSListener implements ListSelectionListener {

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
	        	String korisnickoIme = (String) table.getValueAt(row, 0);
	        	String lozinka = (String) table.getValueAt(row, 1);
	        	Korisnik selected = Podaci.getInstance().getKorisnici().findByKorImeAndLozinka(korisnickoIme, lozinka);
	        	if (selected != null) {
	        		detailsPanel.insertTableData(selected);
	        	}
			}
		}
		
	}
}
