package ClasseDeDados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Movimentos.Operacoes;
import excecoes.ArquivoNaoExiste;

public class Empresa {
	protected String cpf_responsavel;
	protected String CNPJ;
	protected String nomeFantasia;
	protected String razaoSocial;
	protected Usuario responsavel;
	protected int id;
	protected static int contador = 0;
	protected ArrayList<ContaSalario> contas = new ArrayList<ContaSalario>();

	public Empresa() {

	}

	public Empresa(String CNPJ, String nome, String razao, String cpf_responsavel, ArrayList<String> cpf_contas)
			throws Exception {

		String[] usuario = null;
		Operacoes operacao = new Operacoes();
		int ultimoid;

		ultimoid = operacao.consultaMaiorId("Empresas.txt");
		contador = ultimoid;

		try {
			operacao.confereArquivo("Usuarios.txt");

			usuario = operacao.consultaCPF(cpf_responsavel, "Usuarios.txt");
			if (usuario == null) {
				JOptionPane.showMessageDialog(null, "O usuario responsável de CPF: " + cpf_responsavel
						+ " não foi encontrado nos registros, não foi possível cadastrar a empresa");
			} else {
				// **Conferindo se ja existe uma empresa com tal CNPJ, retornando null caso nao
				// exista
				String[] confere = operacao.consultaCNPJ(CNPJ, "Empresas.txt");
				if (confere == null) {
					// **Como nao existe ele atribui o objeto Empresa e as contas associadas e
					// escreve no arquivo
					contador++;
					this.atribuirContasAEmpresa(cpf_contas, usuario);
					JOptionPane.showMessageDialog(null, "Contas Salário associadas: " + this.contas.size());
					this.atribuirObjetoEmpresa(CNPJ, nome, razao, cpf_responsavel, usuario);
					String linha = this.id + "#" + this.CNPJ + "#" + this.nomeFantasia + "#" + this.razaoSocial + "#"
							+ this.cpf_responsavel + "#" + this.contas;
					operacao.escritor(linha, "Empresas.txt");
				} else {
					if (this.percorreObjetosEmpresa(CNPJ) == 0) {
						this.reatribuirContasAEmpresa(confere, usuario);
						this.recriarObjetoEmpresa(CNPJ, nome, razao, cpf_responsavel, Integer.parseInt(confere[0]),
								usuario);
						JOptionPane.showMessageDialog(null, "Já existe uma empresa com o CNPJ: " + CNPJ);
					} else {
						JOptionPane.showMessageDialog(null, "Já existe uma empresa com o CNPJ: " + CNPJ);
					}
				}
			}
		} catch (ArquivoNaoExiste ane) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível ler o arquivo de usuários, cadastre pelo menos um usuário responsável");
			throw new ArquivoNaoExiste();
		}

	}

	public int percorreObjetosEmpresa(String CNPJ) {
		int j = 0;
		for (j = 0; j < Operacoes.empresas.size(); j++) {
			if ((Operacoes.empresas.get(j).CNPJ).equals(CNPJ)) {
				break;
			}
		}
		if (j == Operacoes.empresas.size()) {
			return 0;
		} else {
			return 1;
		}
	}

	public int percorreContasDaEmpresa(String contas) {
		for (int j = 0; j < this.contas.size(); j++) {
			if (this.contas.get(j).cpf_usuario.equals(contas)) {
				return 0;
			}
		}
		return 1;
	}

	public void percorreContasEmpresaRemove(String contas) {
		for (int j = 0; j < this.contas.size(); j++) {
			if (this.contas.get(j).cpf_usuario.equals(contas)) {
				this.contas.remove(j);
			}
		}

	}

	public int reatribuirContasAEmpresa(String[] dadosEmpresas, String[] dadosUsuario) throws IOException {
		
		String[] contasSalario = null;
		ContaSalario c = null;
		contasSalario = dadosEmpresas[5].split(",");
		contasSalario[0].replace("[", "");
		contasSalario[0].replace("]", "");
		this.recriarObjetoEmpresa(dadosEmpresas[1], dadosEmpresas[2], dadosEmpresas[3], dadosEmpresas[4],
				Integer.parseInt(dadosEmpresas[0]), dadosUsuario);
		String[] contasRegistradas = null;

		Operacoes operacao = new Operacoes();
		for (int j = 0; j < contasSalario.length; j++) {
			String contas = contasSalario[j];
			contas = contas.replace("[", "");
			contas = contas.replace("]", "");
			contas = contas.replace(" ", "");
			contasRegistradas = operacao.consultaCPF(contas, "Contas.txt");
			if (contasRegistradas != null) {
				c = new ContaSalario();
				c.recriarObjetoConta(Integer.parseInt(contasRegistradas[0]), contasRegistradas[3],
						Double.parseDouble(contasRegistradas[1]), dadosUsuario);
				if (percorreContasDaEmpresa(contas) == 1) {
					this.contas.add(c);
				}
			}
		}

		return 1;
	}

	public int atribuirContasAEmpresa(ArrayList<String> cpf_contas, String[] dadosUsuario) throws IOException {
		String[] contasSalario = null;
		ContaSalario c = null;
		Operacoes operacao = new Operacoes();
		
		for (int j = 0; j < cpf_contas.size(); j++) {
			contasSalario = operacao.consultaCPF(cpf_contas.get(j), "Contas.txt");
			if (contasSalario != null) {
				if (contasSalario[2].equals("ContaSalario")) {
					c = new ContaSalario();
					c.atribuirObjetoConta(contasSalario[3], Double.parseDouble(contasSalario[1]), dadosUsuario);
					if (percorreContasDaEmpresa(contasSalario[3]) == 1) {
						this.contas.add(c);
					}

				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Conta de CPF: " + cpf_contas.get(j) + " não foi encontrada nos registros");
			}
		}
		return 1;
	}

	public void adicionarContasAEmpresa(String CNPJ, ArrayList<String> cpf_contas) throws Exception {
		Operacoes operacao = new Operacoes();
		String[] dadosEmpresas = operacao.consultaCNPJ(CNPJ, "Empresas.txt");
		String[] dadosUsuario = operacao.consultaCPF(dadosEmpresas[4], "Usuarios.txt");
		if ((this.reatribuirContasAEmpresa(dadosEmpresas, dadosUsuario) == 1)) {
			int qtdContasAnterior = this.contas.size();
			if ((this.atribuirContasAEmpresa(cpf_contas, dadosUsuario) == 1)) {
				operacao.alteraEmpresa(Integer.parseInt(dadosEmpresas[0]), dadosEmpresas[1], dadosEmpresas[2],
						dadosEmpresas[3], dadosEmpresas[4], this.contas, "Empresas.txt");
				if (this.contas.size() > qtdContasAnterior) {
					int resultado = this.contas.size() - qtdContasAnterior;
					JOptionPane.showMessageDialog(null, "Contas Salário associadas: " + resultado);
				} else {

				}
			}
		}
	}

	public void removerContasEmpresa(String CNPJ, ArrayList<String> cpf_contas) throws Exception {
		Operacoes operacao = new Operacoes();
		String[] dadosEmpresas = operacao.consultaCNPJ(CNPJ, "Empresas.txt");
		String[] dadosUsuario = operacao.consultaCPF(dadosEmpresas[4], "Usuarios.txt");
		this.reatribuirContasAEmpresa(dadosEmpresas, dadosUsuario);
		int qtdContasAnterior = this.contas.size();
		for (int j = 0; j < cpf_contas.size(); j++) {
			this.percorreContasEmpresaRemove(cpf_contas.get(j));
		}
		if (this.contas.size() < qtdContasAnterior) {
			operacao.alteraEmpresa(Integer.parseInt(dadosEmpresas[0]), dadosEmpresas[1], dadosEmpresas[2],
					dadosEmpresas[3], dadosEmpresas[4], this.contas, "Empresas.txt");
			int resultado = qtdContasAnterior - this.contas.size();
			JOptionPane.showMessageDialog(null, "Contas Salário removidas: " + resultado);
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma conta foi removida!");
		}
	}

	public void atribuirObjetoEmpresa(String CNPJ, String nome, String razao, String cpf_responsavel,
			String[] usuario) {
		this.CNPJ = CNPJ;
		this.nomeFantasia = nome;
		this.razaoSocial = razao;
		this.cpf_responsavel = cpf_responsavel;
		this.id = contador;
		// **Atribuindo objeto usuario que será o dono da empresaa
		this.responsavel = new Usuario();
		this.responsavel.atribuirObjetoUsuario(usuario[1], usuario[2], usuario[3], usuario[4].charAt(0));
		if (this.percorreObjetosEmpresa(CNPJ) == 0) {
			Operacoes.empresas.add(this);
		}

	}

	public void recriarObjetoEmpresa(String CNPJ, String nome, String razao, String cpf_responsavel, int id,
			String[] usuario) {
		this.CNPJ = CNPJ;
		this.nomeFantasia = nome;
		this.razaoSocial = razao;
		this.cpf_responsavel = cpf_responsavel;
		this.id = id;
		// **Atribuindo objeto usuario que será o dono da empresaa
		this.responsavel = new Usuario();
		this.responsavel.reatribuirObjetoUsuario(usuario[1], usuario[2], usuario[3], usuario[4].charAt(0),
				Integer.parseInt(usuario[0]));
		if (this.percorreObjetosEmpresa(CNPJ) == 0) {
			Operacoes.empresas.add(this);
		}
	}

	public String getCNPJ() {
		return this.CNPJ;
	}

	public String toStringGravar() {
		return (this.id + "#" + this.CNPJ + "#" + this.nomeFantasia + "#" + this.razaoSocial + "#" + this.responsavel);
	}

	public ArrayList<ContaSalario> getConta() {
		return this.contas;
	}
}
