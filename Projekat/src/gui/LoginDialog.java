package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Podaci;
import model.entity.Korisnik;

public class LoginDialog {
		
	public static final int LOGIN_CANCEL = 0;
	public static final int LOGIN_CONFIRM = 1;

	private static LoginDialogAction lda = new LoginDialogAction(LOGIN_CANCEL);
	
	public static int showLoginDialog() {
		JDialog loginDialog = new JDialog((Frame) null, "Prijava", true);
		loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginDialog.setMinimumSize(new Dimension(250,150));
		
		JPanel panCenter = new JPanel(new GridLayout(4,2));
		JPanel panSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
		
		JLabel usernameLbl = new JLabel("Korisničko ime");
		JLabel passwordLbl = new JLabel("Lozinka");
		JTextField usernameTxtFld = new JTextField();
		JPasswordField passwordFld = new JPasswordField();
		
		JButton confirm = new JButton("Potvrdi");
		JButton cancel = new JButton("Odustani");
		
		usernameTxtFld.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirm.doClick();
				}
			}
		});
		passwordFld.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirm.doClick();
				}
			}
		});
		
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String passValue = new String(passwordFld.getPassword());
				Korisnik korisnik = Podaci.getInstance().getKorisnici().findByKorImeAndLozinka(usernameTxtFld.getText(), passValue);
				if(korisnik != null) {
					if (korisnik.isObrisan()) {
						JOptionPane.showMessageDialog(loginDialog, "Vaš nalog je deaktiviran.",
								"Greška", JOptionPane.ERROR_MESSAGE);
					} else {
						Podaci.getInstance().setLoggedInUser(korisnik);
						lda.setAction(LOGIN_CONFIRM);
						loginDialog.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(loginDialog, "Pogrešno korisničko ime ili lozinka.",
							"Greška", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginDialog.dispose();
			}
		});
		
		panCenter.add(usernameLbl);
		panCenter.add(usernameTxtFld);
		panCenter.add(passwordLbl);
		panCenter.add(passwordFld);
		
		panSouth.add(confirm);
		panSouth.add(cancel);
		loginDialog.add(panSouth, BorderLayout.SOUTH);
		loginDialog.add(panCenter,BorderLayout.CENTER);
		
		loginDialog.pack();
		loginDialog.setLocationRelativeTo(null);
		loginDialog.setVisible(true);
		
		return lda.getAction();
	}
	
	private static class LoginDialogAction {
		
		private int action;
		
		public LoginDialogAction(int action) {
			this.action = action;
		}
		
		public void setAction(int action) {
			this.action = action;
		}
		
		public int getAction() {
			return action;
		}
	}
}
