package janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Movimentos.Operacoes;
import excecoes.ArquivoNaoExiste;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class ListarUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarUsuario frame = new ListarUsuario();
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
	JComboBox comboBox;
	public ListarUsuario() {
		setResizable(false);
		setTitle("Listar Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setToolTipText("Clique para Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Action();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListar.setBounds(274, 44, 100, 30);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 102, 659, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {
				"ID", "Nome", "Telefone", "CPF", "Sexo"}){

			private static final long serialVersionUID = 1L;
			//private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		String[] valores = {"Escolha uma opção", "Listar por Nome", "Listar por ID"};
		comboBox = new JComboBox(valores);
		comboBox.setBackground(Color.WHITE);
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(4);
		comboBox.setBounds(36, 44, 150, 30);
		contentPane.add(comboBox);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(488, 44, 100, 30);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel = new JLabel("Listar por:");
		lblNewLabel.setBounds(36, 30, 150, 14);
		contentPane.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.info);
		textPane.setEditable(false);
		textPane.setBounds(538, 357, 131, 20);
		contentPane.add(textPane);
		textPane.setText("Total de registros: 0");
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		
	}
	JTextPane textPane;
	public void Action() throws IOException {
		ArrayList<String> lista = new ArrayList<>();
		Operacoes op = new Operacoes();
		try {
			op.confereArquivo("Usuarios.txt");
		}catch(ArquivoNaoExiste ane) {
			JOptionPane.showMessageDialog(null, "Registro de usuários não foi encontrado");
		}
		lista = op.listar("Usuarios.txt");
		PreencheTabela(lista);
	}
	
	public void PreencheTabela(ArrayList<String> lista) throws IOException {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String[] separador;
		Operacoes op = new Operacoes();
		ArrayList<String> organiza = new ArrayList<>();
		if(comboBox.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Selecione um item e clique Listar","Aviso!", JOptionPane.WARNING_MESSAGE);
			textPane.setText("Total de registros: 0");
		}
		else if(comboBox.getSelectedItem().equals("Listar por Nome")) {
			for(int i = 0; i < lista.size(); i++) {
				separador = lista.get(i).split("#");
				organiza.add(separador[1]);
			}
			organiza.sort(null);
			for(int i = 0; i < lista.size(); i++) {
				separador = op.consultaCNPJ(organiza.get(i), "Usuarios.txt");
				model.addRow(new Object[] {separador[0], separador[1], separador[2], separador[3], separador[4]});
			}
			textPane.setText("Total de registros: " +  lista.size());
		}
		else if(comboBox.getSelectedItem().equals("Listar por ID")) {
			for(int i = 0; i < lista.size(); i++) {
				separador = lista.get(i).split("#");
				model.addRow(new Object[] {separador[0], separador[1], separador[2], separador[3], separador[4]});
			}
			textPane.setText("Total de registros: " +  lista.size());
		}
		
	}
}
