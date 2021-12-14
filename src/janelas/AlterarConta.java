package janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Movimentos.Operacoes;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AlterarConta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarConta frame = new AlterarConta();
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
	Operacoes op = new Operacoes();
	JComboBox comboBoxId;
	JComboBox comboBox;
	JTextPane textId;
	JTextPane textSaldo;
	JComboBox comboBoxNome;
	JComboBox comboBoxCPF ;
	JComboBox comboBoxTipo;
	JComboBox comboBoxCPFC;
	String[] cpf = null;
	String[] contas = null;
	static String  ex ;
	public AlterarConta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarConta.class.getResource("/Img/AlterarConta.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Alterar Conta");	
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Escolha uma Opção", "ID da Conta", "CPF da Conta"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(20, 24, 208, 25);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Alterar por:");
		lblNewLabel.setBounds(20, 11, 130, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Escolha uma Opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBox.getSelectedItem().equals("ID da Conta")) {
					comboBoxId.setVisible(true);
					comboBoxCPF.setVisible(false);
					comboBoxCPF.setSelectedIndex(0);
				}
				else if(comboBox.getSelectedItem().equals("CPF da Conta")){
					comboBoxId.setVisible(false);
					comboBoxCPF.setVisible(true);
					comboBoxId.setSelectedIndex(0);
				}
			}
		});
		btnNewButton.setBounds(236, 25, 100, 25);
		contentPane.add(btnNewButton);
		
		String[] ids = null;
		try {
			ids = op.IDs("Contas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxId = new JComboBox(ids);
		comboBoxId.setBackground(Color.WHITE);
		comboBoxId.setBounds(20, 75, 208, 25);
		contentPane.add(comboBoxId);
		comboBoxId.setVisible(false);
		
		String[] cpfs = null;
		try {
			cpfs = op.Posicao3("Usuarios.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			cpf = op.Posicao3("Contas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxCPF = new JComboBox(cpf);
		comboBoxCPF.setVisible(false);
		comboBoxCPF.setSelectedIndex(0);
		comboBoxCPF.setBounds(20, 77, 208, 25);
		contentPane.add(comboBoxCPF);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione a Conta");
		lblNewLabel_1.setBounds(20, 60, 130, 14);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextPane();
		textId.setToolTipText("N\u00E3o pode alterar ID");
		textId.setEditable(false);
		textId.setBounds(20, 124, 25, 25);
		contentPane.add(textId);
		
		JLabel LabelId = new JLabel("ID");
		LabelId.setBounds(20, 110, 46, 14);
		contentPane.add(LabelId);
		
		JLabel LabelNome = new JLabel("Tipo de Conta");
		LabelNome.setBounds(55, 110, 85, 14);
		contentPane.add(LabelNome);
		
		JLabel LabelTelefone = new JLabel("Saldo");
		LabelTelefone.setBounds(236, 110, 78, 14);
		contentPane.add(LabelTelefone);
		
		textSaldo = new JTextPane();
		textSaldo.setBounds(236, 124, 100, 25);
		contentPane.add(textSaldo);
		
		JLabel LabelCPF = new JLabel("CPF do Titular");
		LabelCPF.setBounds(20, 150, 89, 14);
		contentPane.add(LabelCPF);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Escolha uma Opção")){
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else if(comboBox.getSelectedItem().equals("ID da Conta")) {
					PorId();
				}
				else if(comboBox.getSelectedItem().equals("CPF da Conta")) {
					PorCPF();
				}	
			}
		});
		btnLocalizar.setBounds(236, 75, 100, 25);
		contentPane.add(btnLocalizar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCPFC.getSelectedItem().equals("Escolha uma opção") || comboBoxTipo.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Selecione o CPF do titular e o tipo da conta para alterar","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else{
					int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja alterar a conta com CPF " + comboBoxCPFC.getSelectedItem() + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
		    			String cpf = (String) comboBoxCPFC.getSelectedItem();
		    			Double saldo = Double.parseDouble(textSaldo.getText());
		    			try {
							op.atualizaSaldo(cpf, saldo);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Conta alterada com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
				}
			}
		});
		btnAlterar.setBounds(236, 162, 100, 30);
		contentPane.add(btnAlterar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(148, 162, 80, 30);
		contentPane.add(btnVoltar);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBackground(Color.WHITE);
		comboBoxTipo.setEnabled(false);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Tipo conta", "ContaCorrente", "ContaPoupanca", "ContaSalario"}));
		comboBoxTipo.setSelectedIndex(0);
		comboBoxTipo.setBounds(55, 124, 173, 25);
		contentPane.add(comboBoxTipo);
		
		
		comboBoxCPFC = new JComboBox(cpfs);
		comboBoxCPFC.setBackground(Color.WHITE);
		comboBoxCPFC.setEnabled(false);
		comboBoxCPFC.setBounds(20, 165, 120, 25);
		contentPane.add(comboBoxCPFC);
		
		
	}
	public void LimparTex() {
		comboBox.setSelectedIndex(0);
		comboBoxId.setSelectedIndex(0);
		comboBoxCPF.setSelectedIndex(0);
		comboBoxId.setVisible(false);
		comboBoxCPF.setVisible(false);
		comboBoxCPFC.setSelectedIndex(0);
		textId.setText("");
		textSaldo.setText("");
	}
	public void PorId() {
		if (comboBoxId.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				int id = Integer.parseInt((String) comboBoxId.getSelectedItem());
				contas = op.consultaId(id, "Contas.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(contas[0]);
			textSaldo.setText(contas[1]);
			comboBoxTipo.setSelectedItem(contas[2]);
			comboBoxCPFC.setSelectedItem(contas[3]);
			ex = contas[3];
			
		}
	}
	public void PorCPF() {
		if (comboBoxCPF.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				contas = op.consultaCPF((String) comboBoxCPF.getSelectedItem(), "Contas.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(contas[0]);
			textSaldo.setText(contas[1]);
			comboBoxTipo.setSelectedItem(contas[2]);
			comboBoxCPFC.setSelectedItem(contas[3]);
			ex = contas[3];
		}
	}
}
