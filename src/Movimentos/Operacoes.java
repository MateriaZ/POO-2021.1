package Movimentos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ClasseDeDados.Conta;
import ClasseDeDados.ContaSalario;
import ClasseDeDados.Empresa;
import ClasseDeDados.Usuario;
import excecoes.ArquivoNaoExiste;

public class Operacoes {
	// Declaração de variaveis
	public static ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Conta> contas = new ArrayList<Conta>();

	// Construtor da classe
	public Operacoes() {

	}
	
	// Escritor de todas as classes, recebe uma string e grava ela no fim do
	// arquivo passado
	public void escritor(String linha, String nomearquivo) throws IOException {
		FileWriter os = new FileWriter(nomearquivo, true);
		try (PrintWriter bw = new PrintWriter(os)) {
			bw.println(linha);
			bw.close();
		}
	}

	// Escritor identico ao anterior mas não escreve no fim do arquivo, escreve no
	// inicio
	// apagando os outros dados
	public void escritorSobrepoe(String linha, String nomearquivo) throws IOException {
		try (PrintWriter bw = new PrintWriter(nomearquivo)) {
			bw.println(linha);
			bw.close();
		}
	}
	
	public void atualizaSaldo(String cpf_conta, double valor) throws Exception {
		Operacoes operacao = new Operacoes();
		try {
			String[] dadosConta = operacao.consultaCPF(cpf_conta, "Contas.txt");
			String linha = dadosConta[0] + "#" + valor + "#" + dadosConta[2] + "#" + dadosConta[3];
			operacao.alteraUniversal(linha, "Contas.txt");
		}catch(NullPointerException np) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível alterar o saldo da conta");
		}

	
	}
	
	public void confereArquivo (String arquivo) throws ArquivoNaoExiste{
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivo));
		}catch(FileNotFoundException fnf) {
			throw new ArquivoNaoExiste();
		}
	}

	// Leitor de todas classes, lê o txt completo retornando um arry de strings
	public ArrayList<String> leitor(ArrayList<String> s, String arquivo) throws FileNotFoundException, IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivo));
			String linha;
			while ((linha = br.readLine()) != null) {
				s.add(linha);
			}
			br.close();
			return (s);
		}catch(FileNotFoundException fnf) {			
			return (s);
		}
	}

	// Verificar se está completo
	public String[] leitorSeparado(String arquivo) throws FileNotFoundException, IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		int intId;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			return separador;
		}
		return null;
	}

	// Consulta o ID de qualquer classe que possua ID como primeiro digito
	// retornando vetor de string com os atributos ou null caso não encontre
	public String[] consultaId(int id, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		int intId;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((intId = Integer.parseInt(separador[0])) == id) {
				return separador;
			}
		}
		return null;
	}

	// Consulta o ID de maior valor do arquivo passado, retorna valor int
	public int consultaMaiorId(String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador = null;
		int intId = 0;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((Integer.parseInt(separador[0])) > intId) {
				intId = Integer.parseInt(separador[0]);
			}
		}
		return intId;
	}

	// Consulta cpf no arquivo passado (cpf como terceiro atributo após usar
	// o .split
	public String[] consultaCPF(String cpf, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String cpfNumero;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((cpfNumero = separador[3]).equals(cpf)) {
				return separador;
			}
		}
		return null;
	}

	// Consultar CNPJ no arquivo passado (cnpj primeiro após .split
	public String[] consultaCNPJ(String CNPJ, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((separador[1]).equals(CNPJ)) {
				return separador;
			}
		}
		return null;
	}

	// Consulta o nome fantasia da empresa e retorna o vetor de String com todos
	// atributos ou null caso não encontre
	public String[] consultaNomeFantasia(String nomeFantasia, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((separador[2]).equals(nomeFantasia)) {
				return separador;
			}
		}
		return null;
	}

	// Consulta a razão social e retorna o vetor de String com todos os
	// atributos ou null se não encontrar
	public String[] consultaRazaoSocial(String razaoSocial, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((separador[3]).equals(razaoSocial)) {
				return separador;
			}
		}
		return null;
	}

	// Consulta o responsável de empresa e retorna vetor de String ou null
	// caso não localize
	public ArrayList<String> consultaResponsavel(String responsavel, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		ArrayList<String> encontrados = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((separador[4]).equals(responsavel)) {
				encontrados.add(all.get(i));
			}
		}
		if (encontrados.isEmpty())
			return null;
		else
			return (encontrados);
	}

	public ArrayList<String> consultaNome(String nome, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		ArrayList<String> armazena = new ArrayList<String>();
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if(arquivo.equals("Empresas.txt")) {
				if ((separador[2]).equals(nome)) {
					armazena.add("Id: " + separador[0] +  "\nCNPJ: "+ separador[1] + "\nNome: " + separador[2] + "\nNomeRazao: " 
				+ separador[3] + "\nCPFresponsável: " + separador[4] + "\nContas: " +separador[5]);
				}
			}else {
				if ((separador[1]).equals(nome)) {
					armazena.add( "ID: " + separador[0] + "\nNome: "+ separador[1] + "\nTelefone: " + separador[2]
							+  "\nCPF: " + separador[3] + "\nSexo: " + separador[4]);
				}
			}
		}
		return (armazena);
	}
	// altera o cadastro de empresa, recebe todos os dados da empresa
	public void alteraEmpresa(int id, String novoCNPJ, String novoNome, String novaRazao, String novoResponsavel,ArrayList<ContaSalario> contas,
			String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		int intId;
		String linha;
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((intId = Integer.parseInt(separador[0])) == id) {
				linha = Integer.toString(id) + "#" + novoCNPJ + "#" + novoNome + "#" + novaRazao + "#"
						+ novoResponsavel + "#" + contas;
				all.set(i, linha);
				this.remove(id, arquivo);
			}
			if (i == 0)
				escritorSobrepoe(all.get(i), arquivo);
			else
				escritor(all.get(i), arquivo);

		}
	}

	// altera registro de qualquer classe que tenha id como primeiro atributo
	// recebe o caminho do txt e a String
	public void alteraUniversal(String linha, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String[] separaLinha = linha.split("#");
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if (separador[0].equals(separaLinha[0])) {
				all.set(i, linha);
			}
			if (i == 0)
				escritorSobrepoe(all.get(i), arquivo);
			else
				escritor(all.get(i), arquivo);
		}
	}

	// remove o registro de toda classe que inicia com id, passando o nome do
	// arquivo txt que será alterado
	public void remove(int id, String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		int intId;
		boolean removeu = false;
		String cpf = "";
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			if ((intId = Integer.parseInt(separador[0])) == id) {
				//caso a remoção seja em usuarios
				if (arquivo.equals("Empresas.txt"))
					cpf = separador[4];
				else 
					cpf = separador[3];
				//all.remove(i);
				removeu = true;
			} else {
				if (i == 0 || i == 1 && removeu == true) {
					escritorSobrepoe(all.get(i), arquivo);
					removeu = false;
				}
				else
					escritor(all.get(i), arquivo);
			}
		}
		all.clear();
		if(cpf != "") {
			String [] verificador;
			if(arquivo.equals("Usuarios.txt")) {
				//verificar se o usuario esta vinculado a uma conta ou empresa
				verificador = consultaCPF(cpf, "Contas.txt");
				if(verificador != null) {
					//se consultacpf retornar uma conta ela sera removida do arquivo contas
					remove(Integer.parseInt(verificador[0]), "Contas.txt");
				}
				ArrayList<String> empresas = new ArrayList<>();
				empresas = consultaResponsavel(cpf, "Empresas.txt");
				if (empresas != null) {
					for(int j = 0; j < empresas.size(); j++) {
						verificador = empresas.get(j).split("#");
						remove(Integer.parseInt(verificador[0]), "Empresas.txt");
					}
				}
			}
			if(arquivo.equals("Contas.txt")) {
				all = leitor(all, "Empresas.txt");
				String[] dadosusuarios = null;
				Empresa e ;
				for(int j = 0; j < all.size(); j++) {
					separador = all.get(j).split("#");
					//separador == dados da empresa
					verificador = separador[5].split(",");
					verificador[0].replace("[", "");
					verificador[0].replace("]", "");
					for (int h = 0; h < verificador.length; h++) {
						String contas = verificador[h];
						contas = contas.replace("[", "");
						contas = contas.replace("]", "");
						contas = contas.replace(" ", "");
						if (!contas.equals("")) {
							dadosusuarios = consultaCPF(contas, "Usuarios.txt");
							e = new Empresa();
							e.reatribuirContasAEmpresa(separador, dadosusuarios);
							alteraEmpresa(Integer.parseInt(separador[0]), separador[1], separador[2], separador[3], separador[4], e.getConta(), "Empresas.txt");
						}
					}
				}
			}
			all.clear();
		}
		
	}

	// Impressão no console de todo o txt passado.
	public ArrayList<String> listar(String nomearquivo) throws IOException {
		ArrayList<String> lista = new ArrayList<String>();
		Operacoes op = new Operacoes();
		op.leitor(lista, nomearquivo);
		return lista;
	}
	
	// Retorna um array com todos os dados da posição 1 de um arquivo
	public String[] Posicao1(String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String[] cnpjs = new String[all.size() + 1];
		cnpjs[0] = "Escolha uma opção";
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			cnpjs[i + 1] = separador[1];
		}
		return cnpjs;
	}
	
	public String[] Posicao2(String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String[] temp = new String[all.size() + 1];
		temp[0] = "Escolha uma opção";
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			temp[i + 1] = separador[2];
		}
		return temp;
	}
	
	// Retorna um array com todos os dados da posição 3 de qualquer arquivo
	public String[] Posicao3(String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String[] temp = new String[all.size() + 1];
		temp[0] = "Escolha uma opção";
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			temp[i + 1] = separador[3];
		}
		return temp;
	}
	
	// Retorna um array com todos os id de qualquer arquivo
	public String[] IDs(String arquivo) throws IOException {
		ArrayList<String> all = new ArrayList<String>();
		all = leitor(all, arquivo);
		String[] separador;
		String[] ids = new String[all.size() + 1];
		ids[0] = "Escolha uma opção";
		for (int i = 0; i < all.size(); i++) {
			separador = all.get(i).split("#");
			ids[i + 1] = separador[0];
		}
		return ids;
	}
	
}