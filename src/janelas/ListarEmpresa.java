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

public class ListarEmpresa extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarEmpresa frame = new ListarEmpresa();
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
	private JLabel lblNewLabel;
	private JTextPane textPane;
	public ListarEmpresa() {
		setResizable(false);
		setTitle("Listar Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 428);
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
				"ID", "CNPJ", "Nome", "Razao", "Cpf Responsável","Contas"}){

			private static final long serialVersionUID = 1L;
			//private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false,false
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
		
		lblNewLabel = new JLabel("Listar por:");
		lblNewLabel.setBounds(36, 28, 150, 14);
		contentPane.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.info);
		textPane.setEditable(false);
		textPane.setBounds(539, 357, 130, 20);
		contentPane.add(textPane);
		textPane.setText("Total de registros: 0");
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
	}
	
	public void Action() throws IOException {
		ArrayList<String> lista = new ArrayList<>();
		Operacoes op = new Operacoes();
		try {
			op.confereArquivo("Empresas.txt");
		}catch(ArquivoNaoExiste ane) {
			JOptionPane.showMessageDialog(null, "Registro de empresas não foi encontrado");
		}
		lista = op.listar("Empresas.txt");
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
				organiza.add(separador[2]);
			}
			organiza.sort(null);
			for(int i = 0; i < lista.size(); i++) {
				separador = op.consultaNomeFantasia(organiza.get(i), "Empresas.txt");
				model.addRow(new Object[] {separador[0], separador[1], separador[2], separador[3], separador[4],separador[5]});
			}
			textPane.setText("Total de registros: " + lista.size());
		}
		else if(comboBox.getSelectedItem().equals("Listar por ID")) {
			for(int i = 0; i < lista.size(); i++) {
				separador = lista.get(i).split("#");
				model.addRow(new Object[] {separador[0], separador[1], separador[2], separador[3], separador[4],separador[5]});
			}
			textPane.setText("Total de registros: " + lista.size());
		}
		
	}
}
