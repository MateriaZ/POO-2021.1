package Movimentos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.management.AttributeNotFoundException;
import javax.swing.JOptionPane;

import ClasseDeDados.ContaCorrente;
import ClasseDeDados.ContaPoupanca;
import ClasseDeDados.ContaSalario;
import excecoes.ArquivoNaoExiste;

public class ImportarContas {

	public ImportarContas() throws NumberFormatException, Exception {

		Operacoes operacao = new Operacoes();
		ArrayList<String> a = new ArrayList<String>();

		a = operacao.leitor(a, "ContasImportar.txt");
		int arquivoConfere = 0;

		if (a.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de importação de contas", "Aviso de erro",
					JOptionPane.ERROR_MESSAGE);
		}

		if (a.isEmpty() == false) {
			try {
				for (int j = 0; j < a.size(); j++) {
					String[] campos = a.get(j).split("#");

					if (campos[1].equals("ContaSalario")) {
						ContaSalario f = new ContaSalario(campos[2], Double.parseDouble(campos[0]));
					} else {
						if (campos[1].equals("ContaPoupanca")) {
							ContaPoupanca f = new ContaPoupanca(campos[2], Double.parseDouble(campos[0]));
						} else {
							if (campos[1].equals("ContaCorrente")) {
								ContaCorrente f = new ContaCorrente(campos[2], Double.parseDouble(campos[0]));
							}
						}
					}
				}
			} catch (ArquivoNaoExiste ane) {
				arquivoConfere = 1;
			}
			if (arquivoConfere == 0) {
				JOptionPane.showMessageDialog(null, "Arquivo de contas importado com sucesso", "Aviso!",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

}
