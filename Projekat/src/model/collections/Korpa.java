package model.collections;

import java.util.ArrayList;

import model.Podaci;
import model.entity.KorpaItem;
import model.entity.Lek;
import model.entity.Recept;
import view.util.exceptions.RelatedRecordNotFound;

public class Korpa implements ITableData {
	
	private ArrayList<KorpaItem> data;
	
	public Korpa() {
		data = new ArrayList<>();
	}
	
	public ArrayList<KorpaItem> getData() {
		return data;
	}

	public void setData(ArrayList<KorpaItem> data) {
		this.data = data;
	}

	@Override
	public String[] getColumnNames() {
		String[] columnNames = { "Šifra leka",
	            "Naziv",
	            "Količina",
	            "Jed. cena"};
		return columnNames;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0: return data.get(rowIndex).getLek().getSifra();
			case 1: return data.get(rowIndex).getLek().getNaziv();
			case 2: return data.get(rowIndex).getKolicina();
			case 3: return data.get(rowIndex).getLek().getCena();
			default: return "";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 2: return Integer.class;
			case 3: return Float.class;
			default: return String.class;
		}
	}
	
	public float addItem(String sifraLeka, int kolicina) throws RelatedRecordNotFound {
		Lek lek = Podaci.getInstance().getLekovi().findBySifra(sifraLeka);
		if (lek == null) {
			throw new RelatedRecordNotFound("Lek pod tom šifrom ne postoji!");
		}
		boolean kiFound = false;
		for (KorpaItem ki : data) {
			if (ki.getLek().getSifra().equalsIgnoreCase(lek.getSifra())) {
				ki.setKolicina(ki.getKolicina() + kolicina);
				kiFound = true;
				break;
			}
		}
		if (!kiFound) {
			data.add(new KorpaItem(lek, kolicina));
		}
		return lek.getCena() * kolicina;
	}
	
	public float addItem(int sifraRecepta) throws RelatedRecordNotFound {
		Recept recept = Podaci.getInstance().getRecepti().findBySifra(sifraRecepta);
		if (recept == null) {
			throw new RelatedRecordNotFound("Recept pod tom šifrom ne postoji!");
		}
		boolean kiFound = false;
		for (KorpaItem ki : data) {
			if (ki.getLek().getSifra().equalsIgnoreCase(recept.getLek().getSifra())) {
				ki.setKolicina(ki.getKolicina() + recept.getKolicina());
				kiFound = true;
				break;
			}
		}
		if (!kiFound) {
			data.add(new KorpaItem(recept.getLek(), recept.getKolicina()));
		}
		return recept.getLek().getCena() * recept.getKolicina();
	}
	
	public void clearCart() {
		data.clear();
	}

}
