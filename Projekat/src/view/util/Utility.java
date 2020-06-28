package view.util;

import javax.swing.JOptionPane;

import gui.MainWindow;

public class Utility {
	
	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(MainWindow.getInstance(), 
				message, "Greška", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInfoMessage(String message) {
		JOptionPane.showMessageDialog(MainWindow.getInstance(), 
				message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

}
