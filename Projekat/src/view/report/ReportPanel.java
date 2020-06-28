package view.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

import model.Podaci;
import model.entity.Korisnik;
import model.entity.ReportItem;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import view.util.FormButton;
import view.util.FormTextField;
import view.util.Utility;

@SuppressWarnings("serial")
public class ReportPanel extends JPanel {
	
	private GridBagConstraints gBagC;
	private JPanel settingsPanel;
	private JPanel reportViewerPanel;
	private JProgressBar progressBar;
	private ButtonGroup rbGroup;
	private JRadioButton rbUkupno;
	private JRadioButton rbUkupnoApotekar;
	private JRadioButton rbUkupnoProizvodjac;
	private FormTextField apotekarTxtFld;
	private FormTextField proizvodjacTxtFld;
	private FormButton generate;
	private String rbState = "UKUPNO";
	
	public ReportPanel() {
		setLayout(new BorderLayout());

		gBagC = new GridBagConstraints();
		gBagC.fill = GridBagConstraints.HORIZONTAL;
		gBagC.anchor = GridBagConstraints.PAGE_START;
		gBagC.gridy = -1;
		gBagC.weighty = 1.0;
		gBagC.insets = new Insets(5,3,5,3);
		
		rbGroup = new ButtonGroup();
		
		settingsPanel = new JPanel();
		settingsPanel.setBackground(Color.decode("#565857"));
		settingsPanel.setLayout(new GridBagLayout());
		
		gBagC.gridx = 0;
		gBagC.gridy = 0;
		rbUkupno = new JRadioButton("Ukupno prodatih lekova");
		rbUkupno.setBackground(Color.decode("#565857"));
		rbUkupno.setForeground(Color.WHITE);
		rbUkupno.setActionCommand("UKUPNO");
		rbUkupno.addActionListener(new ReportRadioButtonActionListener());
		rbUkupno.setSelected(true);
		settingsPanel.add(rbUkupno, gBagC);
		rbGroup.add(rbUkupno);
		
		gBagC.gridx=0;
		gBagC.gridy++;
		rbUkupnoApotekar = new JRadioButton("Ukupno prodatih lekova po apotekaru");
		rbUkupnoApotekar.setBackground(Color.decode("#565857"));
		rbUkupnoApotekar.setForeground(Color.WHITE);
		rbUkupnoApotekar.setActionCommand("UKUPNO-AP");
		rbUkupnoApotekar.addActionListener(new ReportRadioButtonActionListener());
		settingsPanel.add(rbUkupnoApotekar, gBagC);
		rbGroup.add(rbUkupnoApotekar);
		gBagC.gridx+=1;
		apotekarTxtFld = new FormTextField();
		apotekarTxtFld.setEditable(false);
		settingsPanel.add(apotekarTxtFld, gBagC);
		
		gBagC.gridx=0;
		gBagC.gridy++;
		rbUkupnoProizvodjac = new JRadioButton("Ukupno prodatih lekova po proizvođaču");
		rbUkupnoProizvodjac.setBackground(Color.decode("#565857"));
		rbUkupnoProizvodjac.setForeground(Color.WHITE);
		rbUkupnoProizvodjac.setActionCommand("UKUPNO-PR");
		rbUkupnoProizvodjac.addActionListener(new ReportRadioButtonActionListener());
		settingsPanel.add(rbUkupnoProizvodjac, gBagC);
		rbGroup.add(rbUkupnoProizvodjac);
		gBagC.gridx+=1;
		proizvodjacTxtFld = new FormTextField();
		proizvodjacTxtFld.setEditable(false);
		settingsPanel.add(proizvodjacTxtFld, gBagC);
		
		gBagC.gridx+=1;
		generate = new FormButton("Generiši");
		generate.addActionListener(new GenerateReportButtonActionListener());
		settingsPanel.add(generate, gBagC);
		
		add(settingsPanel, BorderLayout.NORTH);
		reportViewerPanel = new JPanel();
		reportViewerPanel.setLayout(new BorderLayout());
		add(reportViewerPanel, BorderLayout.CENTER);
		progressBar = new JProgressBar(0, 100);
		progressBar.setForeground(Color.GREEN);
		add(progressBar, BorderLayout.SOUTH);
		
	}
	
	private void clearFields() {
		apotekarTxtFld.setText("");
		proizvodjacTxtFld.setText("");
	}
	
	private void rbOnChange(String actionCommand) {
		rbState = actionCommand;
		clearFields();
		if ("UKUPNO".equals(actionCommand)) {
			apotekarTxtFld.setEditable(false);
			proizvodjacTxtFld.setEditable(false);
		} else if ("UKUPNO-AP".equals(actionCommand)){
			apotekarTxtFld.setEditable(true);
			proizvodjacTxtFld.setEditable(false);
		} else {
			apotekarTxtFld.setEditable(false);
			proizvodjacTxtFld.setEditable(true);
		}
	}
	
	private CustomJRViewer generateReport() throws JRException {
		HashMap<String, Object> params = new HashMap<>();
		ArrayList<ReportItem> data;
		String reportPath;
		if ("UKUPNO".equals(rbState)) {
			data = Podaci.getInstance().getRacuni().generateReportData();
			reportPath = "reports/binaries/UkupnaProdajaReport.jasper";
		} else if ("UKUPNO-AP".equals(rbState)){
			Korisnik apotekar = Podaci.getInstance().getKorisnici().findByKorIme(apotekarTxtFld.getText());
			if (apotekar == null) {
				Utility.showErrorMessage("Apotekar pod tim korisničkim imenom ne postoji!");
				return null;
			}
			data = Podaci.getInstance().getRacuni().generateReportData(apotekar);
			params.put("ApotekarParam", apotekar.getIme() + " " + apotekar.getPrezime());
			reportPath = "reports/binaries/UkupnaProdajaApotekarReport.jasper";
		} else {
			data = Podaci.getInstance().getRacuni().generateReportData(proizvodjacTxtFld.getText());
			params.put("ProizvodjacParam", proizvodjacTxtFld.getText());
			reportPath = "reports/binaries/UkupnaProdajaProizvodjacReport.jasper";
		}
		if (data.size() == 0) {
			Utility.showErrorMessage("Nema podataka za prikaz ovog izveštaja!");
			return null;
		}
		progressBar.setValue(progressBar.getValue() + 25);
		JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportPath));
		JRBeanCollectionDataSource jrbc = new JRBeanCollectionDataSource(data);
		params.put("ItemCollection", jrbc);
		progressBar.setValue(progressBar.getValue() + 25);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
		progressBar.setValue(progressBar.getValue() + 25);
		
		return new CustomJRViewer(jasperPrint);
	}
	
	private class ReportRadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			rbOnChange(e.getActionCommand());
		}
		
	}
	
	private class GenerateReportButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (("UKUPNO-AP".equals(rbState) && apotekarTxtFld.getText().isEmpty()) ||
					("UKUPNO-PR".equals(rbState) && proizvodjacTxtFld.getText().isEmpty())) {
				Utility.showErrorMessage("Morate uneti vrednost za izveštaj!");
			} else {
				GenerateReportSwingWorker grsw = new GenerateReportSwingWorker();
				grsw.execute();
			}
		}
		
	}
	
	private class GenerateReportSwingWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			progressBar.setValue(0);
			reportViewerPanel.removeAll();
			try {
				CustomJRViewer cjrv = generateReport();
				if (cjrv != null) {
					reportViewerPanel.add(cjrv, BorderLayout.CENTER);
				}
			} catch (JRException e) {
				Utility.showErrorMessage(e.getMessage());
			}
			reportViewerPanel.revalidate();
			reportViewerPanel.repaint();
			progressBar.setValue(100);
			return null;
		}
		
	}

}
