package janelas;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Movimentos.Operacoes;
import ClasseDeDados.Conta;
import ClasseDeDados.ContaCorrente;
import ClasseDeDados.ContaPoupanca;
import ClasseDeDados.ContaSalario;
import ClasseDeDados.Empresa;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class IncluirNovaConta extends JFrame {

	private JPanel contentPane;
	private JTextField textSaldoInicial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncluirNovaConta frame = new IncluirNovaConta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Operacoes p = new Operacoes();
	JComboBox comboBoxCpf;
	JComboBox comboBoxCNPJ;
	JComboBox comboBoxTpoConta;
	public IncluirNovaConta() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IncluirNovaConta.class.getResource("/Img/AdcConta.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Cadastrar conta");
		setBounds(100, 100, 279, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] valoresPossiveis = { "Escolha uma opção", "Conta corrente", "Conta poupança", "Conta salário" };
		comboBoxTpoConta = new JComboBox(valoresPossiveis);
		comboBoxTpoConta.setBackground(Color.WHITE);
		comboBoxTpoConta.setBounds(10, 162, 243, 20);
		contentPane.add(comboBoxTpoConta);
		
		JLabel lbCPF = new JLabel("CPF do titular*");
		lbCPF.setBounds(10, 11, 224, 14);
		contentPane.add(lbCPF);
		
		String[] cpfs = p.Posicao3("Usuarios.txt");
		comboBoxCpf = new JComboBox(cpfs);
		comboBoxCpf.setBackground(Color.WHITE);
		comboBoxCpf.setBounds(10, 25, 243, 20);
		contentPane.add(comboBoxCpf);
		
		JLabel lblCNPJ = new JLabel("CNPJ empregadora");
		lblCNPJ.setBounds(10, 61, 200, 14);
		contentPane.add(lblCNPJ);
		
		String[] cnpjs = p.Posicao1("Empresas.txt");
		comboBoxCNPJ = new JComboBox(cnpjs);
		comboBoxCNPJ.setBackground(Color.WHITE);
		comboBoxCNPJ.setBounds(10, 75, 243, 20);
		contentPane.add(comboBoxCNPJ);
		
		JLabel lblSaldoInicial = new JLabel("Saldo inicial*");
		lblSaldoInicial.setBounds(10, 106, 200, 14);
		contentPane.add(lblSaldoInicial);
		
		textSaldoInicial = new JTextField();
		textSaldoInicial.setBounds(10, 120, 243, 20);
		contentPane.add(textSaldoInicial);
		textSaldoInicial.setColumns(10);
		
		JLabel lblTipoConta = new JLabel("Tipo de conta banc\u00E1ria*");
		lblTipoConta.setBounds(10, 145, 199, 14);
		contentPane.add(lblTipoConta);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(18, 214, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidaCampos();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(145, 214, 100, 30);
		contentPane.add(btnCadastrar);
						
	}
	public void ValidaCampos() throws IOException{
		String cpf = String.valueOf(comboBoxCpf.getSelectedItem());
		String cnpj = String.valueOf(comboBoxCNPJ.getSelectedItem());
		String valor = textSaldoInicial.getText();
		ArrayList<String> cpf_contas = new ArrayList<String>();
		if(p.consultaCPF(cpf, "Contas.txt") != null) {
			JOptionPane.showMessageDialog(null, "Já existe uma conta com o CPF: " + cpf, "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			if(textSaldoInicial.getText().equals("") ||
			comboBoxCpf.getSelectedItem().equals("Escolha uma opção") ||
			comboBoxTpoConta.getSelectedItem().equals("Escolha uma opção")
			) {
				JOptionPane.showMessageDialog(null, "Campo obrigatório está vazio", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
			} else if(Double.parseDouble(valor) < 0 || Double.parseDouble(valor) > 100000) {
				JOptionPane.showMessageDialog(null, "Valor inicial invalido, limite [0,100000] por conta", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
			}else if(comboBoxTpoConta.getSelectedItem().equals("Conta corrente")) {
				try {
					ContaCorrente c1 = new ContaCorrente(cpf, Double.parseDouble(valor));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				LimparTex();
			} else if(comboBoxTpoConta.getSelectedItem().equals("Conta salário")) {
				
				try {
					ContaSalario c1 = new ContaSalario(cpf, Double.parseDouble(valor));
					
					if(!(comboBoxCNPJ.getSelectedItem().equals("Escolha uma opção"))){
						Empresa em = new Empresa();
						cpf_contas.add(cpf);
					 	em.adicionarContasAEmpresa(cnpj, cpf_contas);
					}
					
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				LimparTex();
			} else if(comboBoxTpoConta.getSelectedItem().equals("Conta poupança")) {
				try {
					ContaPoupanca c1 = new ContaPoupanca(cpf, Double.parseDouble(valor));
				} catch (NumberFormatException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				LimparTex();
			}
		}
	}
	
	public void LimparTex() {
		textSaldoInicial.setText(null);
		comboBoxTpoConta.setSelectedIndex(0);
		comboBoxCNPJ.setSelectedIndex(0);
		comboBoxCpf.setSelectedIndex(0);
	}
}
