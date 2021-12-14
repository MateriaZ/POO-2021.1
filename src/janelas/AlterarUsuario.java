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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class AlterarUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarUsuario frame = new AlterarUsuario();
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
	JTextPane textCPF;
	JTextPane textId;
	JTextPane textNome;
	JTextPane textTelefone;
	JTextPane textSexo;
	JComboBox comboBoxNome;
	JComboBox comboBoxCPF ;
	String[] usuario = null;
	public AlterarUsuario() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarUsuario.class.getResource("/Img/UsuarioAltera.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Escolha uma Opção", "ID do Usuário", "CPF do Usuário", "Nome do Usuário"}));
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
				}else if(comboBox.getSelectedItem().equals("ID do Usuário")) {
					comboBoxId.setVisible(true);
					comboBoxNome.setVisible(false);
					comboBoxCPF.setVisible(false);
					comboBoxNome.setSelectedIndex(0);
					comboBoxCPF.setSelectedIndex(0);
				}
				else if(comboBox.getSelectedItem().equals("CPF do Usuário")){
					comboBoxId.setVisible(false);
					comboBoxNome.setVisible(false);
					comboBoxCPF.setVisible(true);
					comboBoxId.setSelectedIndex(0);
					comboBoxNome.setSelectedIndex(0);
				}
				else if(comboBox.getSelectedItem().equals("Nome do Usuário")) {
					comboBoxId.setVisible(false);
					comboBoxNome.setVisible(true);
					comboBoxCPF.setVisible(false);
					comboBoxId.setSelectedIndex(0);
					comboBoxCPF.setSelectedIndex(0);
				}
			}
		});
		btnNewButton.setBounds(203, 24, 89, 23);
		contentPane.add(btnNewButton);
		
		String[] ids = null;
		try {
			ids = op.IDs("Usuarios.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxId = new JComboBox(ids);
		comboBoxId.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxId);
		comboBoxId.setVisible(false);
		
		String[] nomes = null;
		try {
			nomes = op.Posicao1("Usuarios.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxNome = new JComboBox(nomes);
		comboBoxNome.setVisible(false);
		comboBoxNome.setSelectedIndex(0);
		comboBoxNome.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxNome);
		
		String[] cpfs = null;
		try {
			cpfs = op.Posicao3("Usuarios.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxCPF = new JComboBox(cpfs);
		comboBoxCPF.setVisible(false);
		comboBoxCPF.setSelectedIndex(0);
		comboBoxCPF.setBounds(20, 77, 144, 22);
		contentPane.add(comboBoxCPF);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o Usu\u00E1rio");
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
		
		textNome = new JTextPane();
		textNome.setBounds(55, 126, 219, 20);
		contentPane.add(textNome);
		
		JLabel LabelNome = new JLabel("Nome");
		LabelNome.setBounds(55, 110, 46, 14);
		contentPane.add(LabelNome);
		
		JLabel LabelTelefone = new JLabel("Telefone");
		LabelTelefone.setBounds(284, 110, 78, 14);
		contentPane.add(LabelTelefone);
		
		textTelefone = new JTextPane();
		textTelefone.setBounds(284, 126, 99, 20);
		contentPane.add(textTelefone);
		
		textCPF = new JTextPane();
		textCPF.setToolTipText("CPF n\u00E3o pode ser alterado");
		textCPF.setEditable(false);
		textCPF.setBounds(55, 164, 120, 20);
		contentPane.add(textCPF);
		
		JLabel LabelCPF = new JLabel("CPF");
		LabelCPF.setBounds(55, 148, 46, 14);
		contentPane.add(LabelCPF);
		
		JLabel LabelSexo = new JLabel("Sexo");
		LabelSexo.setBounds(20, 148, 46, 14);
		contentPane.add(LabelSexo);
		
		textSexo = new JTextPane();
		textSexo.setBounds(20, 164, 25, 20);
		contentPane.add(textSexo);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Escolha uma Opção")){
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}
				else if(comboBox.getSelectedItem().equals("ID do Usuário")) {
					PorId();
				}
				else if(comboBox.getSelectedItem().equals("CPF do Usuário")) {
					PorCPF();
				}
				else if(comboBox.getSelectedItem().equals("Nome do Usuário")) {
					PorNome();
				}	
			}
		});
		btnLocalizar.setBounds(174, 77, 89, 23);
		contentPane.add(btnLocalizar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					op.alteraUniversal(textId.getText() + "#" + 
									   textNome.getText() + "#" + 
									   textTelefone.getText() + "#" + 
									   textCPF.getText() + "#" + 
									   textSexo.getText(), "Usuarios.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				LimparTex();
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
		btnVoltar.setBounds(273, 161, 89, 23);
		contentPane.add(btnVoltar);
		
		
	}
	public void LimparTex() {
		comboBox.setSelectedIndex(0);
		comboBoxId.setSelectedIndex(0);
		comboBoxNome.setSelectedIndex(0);
		comboBoxCPF.setSelectedIndex(0);
		comboBoxId.setVisible(false);
		comboBoxNome.setVisible(false);
		comboBoxCPF.setVisible(false);
		textId.setText("");
		textNome.setText("");
		textTelefone.setText("");
		textCPF.setText("");
		textSexo.setText("");
	}
	public void PorId() {
		if (comboBoxId.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				int id = Integer.parseInt((String) comboBoxId.getSelectedItem());
				usuario = op.consultaId(id, "Usuarios.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(usuario[0]);
			textNome.setText(usuario[1]);
			textTelefone.setText(usuario[2]);
			textCPF.setText(usuario[3]);
			textSexo.setText(usuario[4]);
		}
	}
	public void PorCPF() {
		if (comboBoxCPF.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			try {
				usuario = op.consultaCPF((String) comboBoxCPF.getSelectedItem(), "Usuarios.txt");
			} catch (IOException e1) { e1.printStackTrace(); }
			textId.setText(usuario[0]);
			textNome.setText(usuario[1]);
			textTelefone.setText(usuario[2]);
			textCPF.setText(usuario[3]);
			textSexo.setText(usuario[4]);
		}
	}
	public void PorNome() {
		if (comboBoxNome.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
		}
		else {
			String nome = (String) comboBoxNome.getSelectedItem();
			try {
				usuario = op.consultaCNPJ(nome, "Usuarios.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textId.setText(usuario[0]);
			textNome.setText(usuario[1]);
			textTelefone.setText(usuario[2]);
			textCPF.setText(usuario[3]);
			textSexo.setText(usuario[4]);
		}
	}
}
