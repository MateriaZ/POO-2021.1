package janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Movimentos.Operacoes;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.JSpinner;
import java.awt.Label;
import java.awt.Color;

public class AlterarEmpresa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarEmpresa frame = new AlterarEmpresa();
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
	JTextPane textCNPJ;
	JTextPane textId;
	JTextPane textNomeF;
	JTextPane textResponsavel;
	JComboBox comboBoxNome;
	JComboBox comboBoxCNPJ ;
	JTextPane textRazao;
	JComboBox comboBoxContas;
	JComboBox comboBoxVincula;
	JButton btnAlterar;
	String[] empresa = null;
	String[] conta = null;
	ArrayList<String> indice5 = new ArrayList<>();
	public AlterarEmpresa() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarEmpresa.class.getResource("/Img/AlteraEmpresa.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Escolha uma Opção", "ID da Empresa", "CNPJ da Empresa", "Nome Fantasia"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(20, 24, 144, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Alterar por:");
		lblNewLabel.setBounds(23, 11, 117, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Escolha uma Opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBox.getSelectedItem().equals("ID da Empresa")) {
					comboBoxId.setVisible(true);
					comboBoxNome.setVisible(false);
					comboBoxCNPJ.setVisible(false);
					comboBoxNome.setSelectedIndex(0);
					comboBoxCNPJ.setSelectedIndex(0);
				}
				else if(comboBox.getSelectedItem().equals("CNPJ da Empresa")){
					comboBoxId.setVisible(false);
					comboBoxNome.setVisible(false);
					comboBoxCNPJ.setVisible(true);
					comboBoxId.setSelectedIndex(0);
					comboBoxNome.setSelectedIndex(0);
				}
				else if(comboBox.getSelectedItem().equals("Nome Fantasia")) {
					comboBoxId.setVisible(false);
					comboBoxNome.setVisible(true);
					comboBoxCNPJ.setVisible(false);
					comboBoxId.setSelectedIndex(0);
					comboBoxCNPJ.setSelectedIndex(0);
				}
			}
		});
		btnNewButton.setBounds(203, 24, 89, 23);
		contentPane.add(btnNewButton);
		
		String[] ids = null;
		try {
			ids = op.IDs("Empresas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxId = new JComboBox(ids);
		comboBoxId.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxId);
		comboBoxId.setVisible(false);
		
		String[] nomes = null;
		try {
			nomes = op.Posicao2("Empresas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxNome = new JComboBox(nomes);
		comboBoxNome.setVisible(false);
		comboBoxNome.setSelectedIndex(0);
		comboBoxNome.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxNome);
		
		String[] cnpjs = null;
		try {
			cnpjs = op.Posicao1("Empresas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxCNPJ = new JComboBox(cnpjs);
		comboBoxCNPJ.setVisible(false);
		comboBoxCNPJ.setSelectedIndex(0);
		comboBoxCNPJ.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxCNPJ);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione a Empresa");
		lblNewLabel_1.setBounds(20, 62, 144, 14);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextPane();
		textId.setToolTipText("N\u00E3o pode alterar ID");
		textId.setEditable(false);
		textId.setBounds(20, 126, 25, 20);
		contentPane.add(textId);
		
		JLabel LabelId = new JLabel("ID");
		LabelId.setBounds(20, 110, 46, 14);
		contentPane.add(LabelId);
		
		textNomeF = new JTextPane();
		textNomeF.setBounds(55, 126, 219, 20);
		contentPane.add(textNomeF);
		
		JLabel LabelNomeF = new JLabel("Nome Fantasia");
		LabelNomeF.setBounds(55, 110, 109, 14);
		contentPane.add(LabelNomeF);
		
		JLabel LabelResponsavel = new JLabel("Respons\u00E1vel");
		LabelResponsavel.setBounds(284, 110, 78, 14);
		contentPane.add(LabelResponsavel);
		
		textResponsavel = new JTextPane();
		textResponsavel.setEditable(false);
		textResponsavel.setBounds(284, 126, 122, 20);
		contentPane.add(textResponsavel);
		
		textCNPJ = new JTextPane();
		textCNPJ.setToolTipText("CPF n\u00E3o pode ser alterado");
		textCNPJ.setEditable(false);
		textCNPJ.setBounds(20, 164, 120, 20);
		contentPane.add(textCNPJ);
		ArrayList<String> contaarray = new ArrayList<>();
		try {
			contaarray = op.listar("Contas.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		String[] temp = null;
		int cont = 0;
		String[] aux = new String[10];
		for(int i = 0; i < contaarray.size(); i++) {
			temp = contaarray.get(i).split("#");
			if(temp[2].equals("ContaSalario")) {
				aux[cont] = temp[3];
				cont++;
			}
		}
		int help = 0;
		for(int i= 0; i < aux.length;i++) {
			if(aux[i] == null) {
				cont = i;
			}
			else {
				help = cont;
			}
		}
		conta = new String[help];
		for(int i= 0; i < help;i++) {
			conta[i] = aux[i];
		}
		comboBoxVincula = new JComboBox(conta);
		comboBoxVincula.setSelectedIndex(0);
		comboBoxVincula.setBounds(20, 239, 122, 22);
		contentPane.add(comboBoxVincula);
		
		JLabel LabelCNPJ = new JLabel("CNPJ");
		LabelCNPJ.setBounds(20, 149, 46, 14);
		contentPane.add(LabelCNPJ);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indice5.clear();
				if (comboBox.getSelectedItem().equals("Escolha uma Opção")){
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else if(comboBox.getSelectedItem().equals("ID da Empresa")) {
					
					PorId();
				}
				else if(comboBox.getSelectedItem().equals("CNPJ da Empresa")) {
					PorCNPJ();
				}
				else if(comboBox.getSelectedItem().equals("Nome Fantasia")) {
					PorNome();
				}
				btnAlterar.setEnabled(true);
			}
		});
		btnLocalizar.setBounds(174, 77, 89, 23);
		contentPane.add(btnLocalizar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Escolha uma Opção") ) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else if(comboBox.getSelectedItem().equals("ID da Empresa") && comboBoxId.getSelectedItem().equals("Escolha uma opção")
						|| comboBox.getSelectedItem().equals("CNPJ da Empresa") && comboBoxCNPJ.getSelectedItem().equals("Escolha uma opção") 
						|| comboBox.getSelectedItem().equals("Nome Fantasia") && comboBoxNome.getSelectedItem().equals("Escolha uma opção")){
					JOptionPane.showMessageDialog(null, "Selecione um item e clique Localizar","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else {
				try {
					String contas5 = "[";
					for (int i = 0; i < indice5.size(); i++) {
						if(i == 0) 
							contas5 += indice5.get(i) ;
						else 
							contas5 += ", " + indice5.get(i) ;
					}
					contas5 += "]";
					op.alteraUniversal(textId.getText() + "#" + 
									   textCNPJ.getText() + "#" + 
									   textNomeF.getText() + "#" + 
									   textRazao.getText() + "#" + 
									   textResponsavel.getText() + "#" +
									   contas5 , "Empresas.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				LimparTex();
				indice5.clear();
				btnAlterar.setEnabled(false);
			}
			}
		});
		btnAlterar.setBounds(273, 77, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(317, 199, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel LabelContas = new JLabel("Contas Vinculadas");
		LabelContas.setBounds(20, 187, 124, 14);
		contentPane.add(LabelContas);
		
		JLabel LabelRazao = new JLabel("Raz\u00E3o Social");
		LabelRazao.setBounds(150, 149, 109, 14);
		contentPane.add(LabelRazao);
		
		textRazao = new JTextPane();
		textRazao.setBounds(150, 164, 194, 20);
		contentPane.add(textRazao);
		
		comboBoxContas = new JComboBox();
		comboBoxContas.setBounds(20, 200, 124, 22);
		contentPane.add(comboBoxContas);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Vincular Conta");
		lblNewLabel_2.setBounds(20, 226, 122, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxContas.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione o CPF de uma conta para excluir","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else {
					for (int i = 0; i < indice5.size(); i++) {
						if(indice5.get(i).equals(comboBoxContas.getSelectedItem())) {
							indice5.remove(i);
						}
					}
					comboBoxVincula.addItem(comboBoxContas.getSelectedItem());
					comboBoxContas.removeItem(comboBoxContas.getSelectedItem());
					comboBoxContas.setSelectedIndex(0);
					comboBoxVincula.setSelectedIndex(0);
				}
			}
		});
		btnExcluir.setBackground(new Color(128, 0, 0));
		btnExcluir.setBounds(150, 199, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxVincula.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione o CPF de uma conta para vincular","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else {
					boolean test = true;
					for(int i = 0; i < indice5.size(); i++) {
						if(indice5.get(i).equals((String) comboBoxVincula.getSelectedItem())) {
							test = false;
							JOptionPane.showMessageDialog(null, "CPF de conta já esta vinculado","Aviso!", JOptionPane.WARNING_MESSAGE);
						}
					}
				if(test) {
					comboBoxContas.addItem(comboBoxVincula.getSelectedItem());
					indice5.add((String) (comboBoxVincula.getSelectedItem()));
					comboBoxVincula.removeItem(comboBoxVincula.getSelectedItem());
					comboBoxContas.setSelectedIndex(0);
					comboBoxVincula.setSelectedIndex(0);
				}
				}
			}
		});
		btnAdicionar.setBackground(new Color(0, 100, 0));
		btnAdicionar.setBounds(150, 239, 89, 23);
		contentPane.add(btnAdicionar);
		
		
	}
	public void LimparTex() {
		comboBox.setSelectedIndex(0);
		comboBoxId.setSelectedIndex(0);
		comboBoxNome.setSelectedIndex(0);
		comboBoxCNPJ.setSelectedIndex(0);
		comboBoxContas.removeAllItems();
		comboBoxId.setVisible(false);
		comboBoxNome.setVisible(false);
		comboBoxCNPJ.setVisible(false);
		textId.setText("");
		textNomeF.setText("");
		textResponsavel.setText("");
		textCNPJ.setText("");
		textRazao.setText("");
	}
	public void PorId() {
		if (comboBoxId.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				int id = Integer.parseInt((String) comboBoxId.getSelectedItem());
				empresa = op.consultaId(id, "Empresas.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(empresa[0]);
			textCNPJ.setText(empresa[1]);
			textNomeF.setText(empresa[2]);
			textRazao.setText(empresa[3]);
			textResponsavel.setText(empresa[4]);
			String contas = new String();
			contas = empresa[5];
			String[] separador = contas.split(",");
			for (int i = 0; i < separador.length; i++) {
				separador[i] = separador[i].replace("[", "");
				separador[i] = separador[i].replace("]", "");
				separador[i] = separador[i].replace(" ", "");
			}
			comboBoxContas.setModel(new DefaultComboBoxModel(separador));
			for (int i = 0; i < separador.length; i++) {
				indice5.add(separador[i]);
			}
		}
	}
	public void PorCNPJ() {
		if (comboBoxCNPJ.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				empresa = op.consultaCNPJ((String) comboBoxCNPJ.getSelectedItem(), "Empresas.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(empresa[0]);
			textCNPJ.setText(empresa[1]);
			textNomeF.setText(empresa[2]);
			textRazao.setText(empresa[3]);
			textResponsavel.setText(empresa[4]);
			String contas = new String();
			contas = empresa[5];
			String[] separador = contas.split(",");
			for (int i = 0; i < separador.length; i++) {
				separador[i] = separador[i].replace("[", "");
				separador[i] = separador[i].replace("]", "");
				separador[i] = separador[i].replace(" ", "");
			}
			comboBoxContas.setModel(new DefaultComboBoxModel(separador));
			for (int i = 0; i < separador.length; i++) {
				indice5.add(separador[i]);
			}
		}
	}
	public void PorNome() {
		if (comboBoxNome.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			String nome = (String) comboBoxNome.getSelectedItem();
			try {
				empresa = op.consultaNomeFantasia(nome, "Empresas.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			textId.setText(empresa[0]);
			textCNPJ.setText(empresa[1]);
			textNomeF.setText(empresa[2]);
			textRazao.setText(empresa[3]);
			textResponsavel.setText(empresa[4]);
			String contas = new String();
			contas = empresa[5];
			String[] separador = contas.split(",");
			for (int i = 0; i < separador.length; i++) {
				separador[i] = separador[i].replace("[", "");
				separador[i] = separador[i].replace("]", "");
				separador[i] = separador[i].replace(" ", "");
			}
			comboBoxContas.setModel(new DefaultComboBoxModel(separador));
			for (int i = 0; i < separador.length; i++) {
				indice5.add(separador[i]);
			}
		}
	}
}
