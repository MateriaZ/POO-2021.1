package excecoes;

import javax.swing.JOptionPane;

public class Negativo extends RuntimeException {
	public Negativo() {
		 JOptionPane.showMessageDialog(null,"Valores negativos s�o inv�lidos", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	}
}