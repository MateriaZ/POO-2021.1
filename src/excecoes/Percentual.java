package excecoes;

import javax.swing.JOptionPane;

public class Percentual extends Exception {
	public Percentual() {
		JOptionPane.showMessageDialog(null,"O percentual deve ser entre 0 e 100", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);			
	}
}
