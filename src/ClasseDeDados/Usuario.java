package ClasseDeDados;

import java.io.IOException;

import javax.swing.JOptionPane;

import Movimentos.Operacoes;

public class Usuario {
	private int id;
	private String cpf;
	private String nome;
	private String telefone;
	private char sexo;
	protected static int contadorUsuario = 0;

	public Usuario(String nome, String telefone, String cpf, char sexo) throws IOException {
		Operacoes operacao = new Operacoes();

		int ultimoid = 0;

		ultimoid = operacao.consultaMaiorId("Usuarios.txt");
		contadorUsuario = ultimoid;

		// **Confere se ja existe um usuario com este CPF no arquivo, retornando null
		// caso nao exista
		String[] confere = operacao.consultaCPF(cpf, "Usuarios.txt");
		if (confere == null) {
			// **Como nao existe ele atribui o objeto e escreve no arquivo
			contadorUsuario++;
			this.atribuirObjetoUsuario(nome, telefone, cpf, sexo);
			String linha = this.id + "#" + this.nome + "#" + this.telefone + "#" + this.cpf + "#" + this.sexo;
			operacao.escritor(linha, "Usuarios.txt");
		} else {
			// **Como existe ele somente recria o objeto
			if (this.percorreObjetosUsuario(cpf) == 0) {
				this.reatribuirObjetoUsuario(nome, telefone, cpf, sexo, Integer.parseInt(confere[0]));
				JOptionPane.showMessageDialog(null, "Já existe um usuário com o CPF: " + cpf);
			} else {
				JOptionPane.showMessageDialog(null, "Já existe um usuário com o CPF: " + cpf);
			}
		}

	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public void atribuirObjetoUsuario(String nome, String telefone, String cpf, char sexo) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.sexo = sexo;
		this.id = contadorUsuario;
		if (this.percorreObjetosUsuario(cpf) == 0) {
			Operacoes.usuarios.add(this);
		}
	}

	public void reatribuirObjetoUsuario(String nome, String telefone, String cpf, char sexo, int id) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.sexo = sexo;
		this.id = id;
		if (this.percorreObjetosUsuario(cpf) == 0) {
			Operacoes.usuarios.add(this);
		}
	}

	public int percorreObjetosUsuario(String cpf) {
		int j = 0;
		for (j = 0; j < Operacoes.usuarios.size(); j++) {
			if ((Operacoes.usuarios.get(j).cpf).equals(cpf)) {
				break;
			}
		}
		if (j == Operacoes.usuarios.size()) {
			return 0;
		} else {
			return 1;
		}
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}