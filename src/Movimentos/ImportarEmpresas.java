package Movimentos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.management.AttributeNotFoundException;
import javax.swing.JOptionPane;

import ClasseDeDados.Empresa;
import excecoes.ArquivoNaoExiste;

public class ImportarEmpresas {

	public ImportarEmpresas() throws Exception {
		Operacoes operacao = new Operacoes();
		ArrayList<String> a = new ArrayList<String>();

		ArrayList<String> CpfContasAdicionar = new ArrayList<String>();
		String cpfcontas = null;
		int arquivoConfere = 0;
		a = operacao.leitor(a, "EmpresasImportar.txt");
		if (a.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de importação de empresas", "Aviso de erro",
					JOptionPane.ERROR_MESSAGE);
		}

		if (a.isEmpty() == false) {

			try {
				for (int j = 0; j < a.size(); j++) {
					String[] campos = a.get(j).split("#");
					while (true) {

						cpfcontas = JOptionPane.showInputDialog("Digite o CPF da conta a ser associada a empresa "
								+ campos[1] + " ou 0 para terminar a adição de contas");

						if (cpfcontas == null || cpfcontas.isBlank()) {
							break;
						}

						if (cpfcontas.equals("0")) {
							break;
						}
						CpfContasAdicionar.add(cpfcontas);

					}
					try {
						Empresa f = new Empresa(campos[0], campos[1], campos[2], campos[3], CpfContasAdicionar);
						CpfContasAdicionar.clear();
					} catch (NullPointerException np) {
						JOptionPane.showMessageDialog(null, "Null Pointer Exception Importar Empresas");
					}
				}
			} catch (ArquivoNaoExiste ane) {
				arquivoConfere = 1;
			}
			if (arquivoConfere == 0) {
				JOptionPane.showMessageDialog(null, "Arquivo de empresas importado com sucesso", "Aviso!",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
