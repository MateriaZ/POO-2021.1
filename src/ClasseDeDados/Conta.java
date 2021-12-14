package ClasseDeDados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Movimentos.Operacoes;
import excecoes.ArquivoNaoExiste;

public abstract class Conta {
	protected String cpf_usuario;
	protected int numero;
	public static int contadorConta = 0;
	private Usuario dono;
	protected double saldo;
	public static String tipoConta;

	public Conta() {

	}

	Conta(String cpf_usuario, double saldo) throws Exception {
		String[] usuario = null;
		Operacoes operacao = new Operacoes();
		Usuario u = null;

		// **Conferir de qual tipo de Conta o objeto é**//
		if (this.getClass().toString().equals("class ClasseDeDados.ContaSalario")) {
			tipoConta = "ContaSalario";
		} else {
			if (this.getClass().toString().equals("class ClasseDeDados.ContaPoupanca")) {
				tipoConta = "ContaPoupanca";
			} else {
				if (this.getClass().toString().equals("class ClasseDeDados.ContaCorrente")) {
					tipoConta = "ContaCorrente";
				}
			}
		}

		int ultimoid;

		ultimoid = operacao.consultaMaiorId("Contas.txt");
		contadorConta = ultimoid;

		// **Verificar se existe um usuario com tal CPF no arquivo Usuarios.txt**,
		// retornando os dados do usuario ou null//
		try {
			operacao.confereArquivo("Usuarios.txt");

			usuario = operacao.consultaCPF(cpf_usuario, "Usuarios.txt");
			if (usuario == null) {
				JOptionPane.showMessageDialog(null, "Usuario com cpf: " + cpf_usuario
						+ " nao foi encontrado nos registros, nao foi possível cadastrar a conta");
			} else {
				// **Conferindo se ja existe uma conta com tal CPF, retornando null caso nao
				// exista
				String[] confere = operacao.consultaCPF(cpf_usuario, "Contas.txt");
				if (confere == null) {
					// **Como nao existe ele atribui o objeto Conta e escreve no arquivo
					contadorConta++;
					this.atribuirObjetoConta(cpf_usuario, saldo, usuario);
					String linha = this.numero + "#" + this.saldo + "#" + tipoConta + "#" + this.cpf_usuario;
					operacao.escritor(linha, "Contas.txt");
				} else {
					// **Como ja existe o arquivo ele somente atribui ao objeto
					if (this.percorreObjetosConta(cpf_usuario) == 0) {
						this.recriarObjetoConta(Integer.parseInt(confere[0]), cpf_usuario, saldo, usuario);
						JOptionPane.showMessageDialog(null, "Já existe uma conta com o CPF: " + cpf_usuario);
					} else {
						JOptionPane.showMessageDialog(null, "Já existe uma conta com o CPF: " + cpf_usuario);
					}
				}
			}
		} catch (ArquivoNaoExiste ane) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível ler o arquivo de usuários, cadastre pelo menos um usuário");
			throw new ArquivoNaoExiste();
		}

	}

	public int percorreObjetosConta(String cpf_usuario) {
		int j = 0;
		for (j = 0; j < Operacoes.contas.size(); j++) {
			if ((Operacoes.contas.get(j).cpf_usuario).equals(cpf_usuario)) {
				break;
			}
		}
		if (j == Operacoes.contas.size()) {
			return 0;
		} else {
			return 1;
		}
	}

	public void atribuirObjetoConta(String cpf_usuarioconta, double saldo, String[] usuario) {
		this.cpf_usuario = cpf_usuarioconta;
		this.saldo = saldo;
		// **Atribuindo objeto usuario que será o dono da conta
		this.dono = new Usuario();
		this.dono.atribuirObjetoUsuario(usuario[1], usuario[2], usuario[3], usuario[4].charAt(0));
		this.numero = contadorConta;
		if (this.percorreObjetosConta(cpf_usuarioconta) == 0) {
			Operacoes.contas.add(this);
		}
	}

	public void recriarObjetoConta(int numero, String cpf_usuario, double saldo, String[] usuario) {
		this.cpf_usuario = cpf_usuario;
		this.saldo = saldo;
		// **Atribuindo objeto usuario que será o dono da conta
		this.dono = new Usuario();
		this.dono.reatribuirObjetoUsuario(usuario[1], usuario[2], usuario[3], usuario[4].charAt(0),
				Integer.parseInt(usuario[0]));
		this.numero = numero;
		if (this.percorreObjetosConta(cpf_usuario) == 0) {
			Operacoes.contas.add(this);
		}
	}

	public String getCpfUsuario() {
		return this.cpf_usuario;
	}

	public void setCpfUsuario(String cpf) {
		this.cpf_usuario = cpf;
	}

	public void sacar(double valor) {
		saldo = saldo - valor;
	}

	public void depositar(double valor) {
		saldo = saldo + valor;
	}

	public abstract void transferir(double valor, Conta destino);

	public void setSaldo(double valor) {
		saldo = valor;
	}

	public double getSaldo() {
		return Math.round(saldo * 100)/100.0;
	}

	public String toString() {
		return this.cpf_usuario;

	}

	public String Nome() {
		return dono.getNome();

	}
}