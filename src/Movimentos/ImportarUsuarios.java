package Movimentos;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ClasseDeDados.Usuario;

public class ImportarUsuarios {

	public ImportarUsuarios() throws IOException {

		Operacoes operacao = new Operacoes();
		ArrayList<String> a = new ArrayList<String>();

		a = operacao.leitor(a, "UsuariosImportar.txt");
		if(a.isEmpty()) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel ler o arquivo de importa��o de usu�rios", "Aviso de erro",
					JOptionPane.ERROR_MESSAGE);
		}

		if (a.isEmpty() == false) {

			for (int j = 0; j < a.size(); j++) {
				String[] campos = a.get(j).split("#");

				Usuario f = new Usuario(campos[0], campos[1], campos[2], campos[3].charAt(0));

			}
			if(a!=null) {
				JOptionPane.showMessageDialog(null, "Arquivo de usu�rios importado com sucesso", "Aviso!",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

}
