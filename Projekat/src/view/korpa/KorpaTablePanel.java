package view.korpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

import model.Podaci;
import view.util.CustomTableModel;
import view.util.FormLabel;
import view.util.FormTextField;
import view.util.TableHeader;
import view.util.renderers.CustomCellRenderer;

@SuppressWarnings("serial")
public class KorpaTablePanel extends JPanel {
	
	private JTable table;
	private CustomTableModel tableModel;
	private JPanel ukupnoPanel;
	private FormLabel ukupnoLbl;
	private FormTextField ukupnoTxtFld;
	
	public KorpaTablePanel() {
		this.setLayout(new BorderLayout());
		tableModel = new CustomTableModel(Podaci.getInstance().getKorpa());
		table = new JTable(tableModel);
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
		table.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.GRAY);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane, BorderLayout.CENTER);
		
		ukupnoPanel = new JPanel();
		ukupnoPanel.setBackground(Color.decode("#565857"));
		ukupnoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ukupnoLbl = new FormLabel("Ukupno");
		ukupnoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ukupnoPanel.add(ukupnoLbl);
		ukupnoTxtFld = new FormTextField();
		ukupnoTxtFld.setEditable(false);
		clearUkupnoTxtFld();
		ukupnoPanel.add(ukupnoTxtFld);
		ukupnoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(ukupnoPanel, BorderLayout.SOUTH);
	}
	
	public void clearUkupnoTxtFld() {
		ukupnoTxtFld.setText("0.0");
	}
	
	public void updateUkupnoTxtFld(float newCena) {
		ukupnoTxtFld.setText(Float.toString(Float.parseFloat(ukupnoTxtFld.getText()) + newCena));
	}

	public JTable getTable() {
		return table;
	}

	public CustomTableModel getTableModel() {
		return tableModel;
	}

	public FormTextField getUkupnoTxtFld() {
		return ukupnoTxtFld;
	}
	
}
