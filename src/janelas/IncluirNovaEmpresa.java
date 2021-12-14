package janelas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ClasseDeDados.Empresa;
import Movimentos.Operacoes;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;

public class IncluirNovaEmpresa extends JFrame {

	private JPanel contentPane;
	private JTextField textCNPJ;
	private JTextField textRazaoSocial;
	private JTextField textFantasia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncluirNovaEmpresa frame = new IncluirNovaEmpresa();
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
	public IncluirNovaEmpresa() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IncluirNovaEmpresa.class.getResource("/Img/AdcEmpresa.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false); 
		setTitle("Cadastrar empresa");
		setBounds(100, 100, 420, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCNPJ = new JLabel("CNPJ* (XX.XXX.XXX/XXXX-XX)");
		lblCNPJ.setBounds(10, 87, 183, 14);
		contentPane.add(lblCNPJ);
		
		textCNPJ = new JTextField();
		textCNPJ.setBounds(10, 102, 183, 20);
		contentPane.add(textCNPJ);
		textCNPJ.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome fantasia");
		lblNome.setBounds(10, 48, 142, 14);
		contentPane.add(lblNome);
		
		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o social*");
		lblRazaoSocial.setBounds(10, 11, 121, 14);
		contentPane.add(lblRazaoSocial);
		
		textRazaoSocial = new JTextField();
		textRazaoSocial.setBounds(10, 24, 390, 20);
		contentPane.add(textRazaoSocial);
		textRazaoSocial.setColumns(10);
		
		textFantasia = new JTextField();
		textFantasia.setBounds(10, 63, 390, 20);
		contentPane.add(textFantasia);
		textFantasia.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF respons\u00E1vel* ");
		lblCPF.setBounds(217, 87, 183, 14);
		contentPane.add(lblCPF);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(51, 131, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnCadastrado = new JButton("Cadastrado");
		btnCadastrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidaCampos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCadastrado.setBounds(258, 131, 100, 30);
		contentPane.add(btnCadastrado);
		
		String[] cpfs = p.Posicao3("Usuarios.txt");
		comboBoxCpf = new JComboBox(cpfs);
		comboBoxCpf.setBackground(Color.WHITE);
		comboBoxCpf.setBounds(217, 102, 183, 20);
		contentPane.add(comboBoxCpf);
		
	}
	
	public void ValidaCampos() throws Exception {
		String cpf = (String) comboBoxCpf.getSelectedItem();
		if(textRazaoSocial.getText().equals("") ||
			textCNPJ.getText().equals("") ||
			comboBoxCpf.getSelectedItem().equals("Escolha uma opção")			
		) {
			JOptionPane.showMessageDialog(null, "Campo obrigatório está vazio", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else if(textFantasia.getText().equals("")) {
			Empresa em = new Empresa(textCNPJ.getText(), textRazaoSocial.getText(), textRazaoSocial.getText(), cpf , null);
			LimparTex();
			JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
		}else {
			Empresa em = new Empresa(textCNPJ.getText(), textFantasia.getText(), textRazaoSocial.getText(), cpf, null);
			LimparTex();
			JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void LimparTex() {
		textCNPJ.setText(null);
		textFantasia.setText(null);
		textRazaoSocial.setText(null);
		comboBoxCpf.setSelectedIndex(0);
	}
}
