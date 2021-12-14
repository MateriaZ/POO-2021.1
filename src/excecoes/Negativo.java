package excecoes;

import javax.swing.JOptionPane;

public class Negativo extends RuntimeException {
	public Negativo() {
		 JOptionPane.showMessageDialog(null,"Valores negativos são inválidos", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	}
}