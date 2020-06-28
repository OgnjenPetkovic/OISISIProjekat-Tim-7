package application;

import gui.LoginDialog;
import gui.MainWindow;
import model.Podaci;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//Ucitavanje korisnika i login
		Podaci.getInstance().loadKorisnici();
		if (LoginDialog.showLoginDialog() == LoginDialog.LOGIN_CONFIRM) {
			//Ucitavanje podataka i pokretanje glavne aplikacije
			Podaci.getInstance().loadData();
			MainWindow mw = MainWindow.getInstance();
		} else {
			System.exit(0);
		}
		
	}

}
