package janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Ajuda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajuda frame = new Ajuda();
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
	@SuppressWarnings("unchecked")
	public Ajuda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ajuda.class.getResource("/Img/help (1).png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(197, 250, 100, 30);
		contentPane.add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("CheckBox.border"));
		panel.setBackground(SystemColor.info);
		panel.setBounds(20, 70, 450, 169);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.info);
		textPane.setBounds(10, 10, 430, 151);
		panel.add(textPane);

		Object[] valoresPossiveis = { "Escolha uma opção", "Ajuda", "Alterar Conta", "Alterar Cor", "Alterar Usuário",
				"Alterar Empresa", "Buscar Empresa", "Buscar Conta", "Buscar Usuário", "Excluir Empresa",
				"Excluir Conta", "Excluir Usuário", "Incluir Nova Empresa", "Incluir Nova Conta",
				"Incluir Novo Usuário", "Listar Empresa", "Listar Conta", "Listar Usuário", "Operações Bancárias",
				"Importar Arquivos", "Diagrama de Caso de Uso", "Diagrama de Classes" };
		JComboBox comboBox = new JComboBox(valoresPossiveis);
		comboBox.setBackground(SystemColor.text);
		comboBox.setForeground(SystemColor.desktop);
		comboBox.setBounds(20, 31, 297, 30);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Selecionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique selecione", "Aviso!",
							JOptionPane.WARNING_MESSAGE);
					textPane.setText("");
				} else if (comboBox.getSelectedItem().equals("Ajuda")) {
					textPane.setText("Caminho\r\n" + "APOIO / AJUDA\r\n" + "Descrição\r\n"
							+ "PARA BUSCAR AJUDA, SELECIONE UMA OPÇÃO E CLIQUE EM SELECIONAR");
				} else if (comboBox.getSelectedItem().equals("Alterar Conta")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / CONTA / ALTERAR CONTA\r\n" + "Descrição");
				} else if (comboBox.getSelectedItem().equals("Alterar Cor")) {
					textPane.setText("Caminho\r\n" + "APOIO / ALTERAR COR\r\n" + "Descrição\r\n"
							+ "PARA ALTERAR A COR DE FUNDO, CLIQUE NO BOTÃO DA COR E CLIQUE EM APLICAR");
				} else if (comboBox.getSelectedItem().equals("Alterar Empresa")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / EMPRESA / ALTERAR EMPRESA\r\n" + "Descrição");
				} else if (comboBox.getSelectedItem().equals("Alterar Usuário ")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / USUÁRIO / ALTERAR USUÁRIO\r\n" + "Descrição");
				} else if (comboBox.getSelectedItem().equals("Buscar Conta")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / CONTA / BUSCAR CONTA\r\n" + "Descrição\r\n"
							+ "PARA BUSCAR UM CONTA, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM BUSCAR");
				} else if (comboBox.getSelectedItem().equals("Buscar Empresa")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / EMPRESA / BUSCAR EMPRESA\r\n" + "Descrição\r\n"
							+ "PARA BUSCAR UM EMPRESA, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM BUSCAR");
				} else if (comboBox.getSelectedItem().equals("Buscar Usuário")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / USUÁRIO / BUSCAR USUÁRIO\r\n" + "Descrição\r\n"
							+ "PARA BUSCAR UM USUÁRIO, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM BUSCAR");
				} else if (comboBox.getSelectedItem().equals("Excluir Conta")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / CONTA / EXCLUIR CONTA\r\n" + "Descrição\r\n"
							+ "PARA REMOVER UMA CONTA, SELECIONE O CPF DO TITULAR DA CONTA E CLIQUE EM REMOVER");
				} else if (comboBox.getSelectedItem().equals("Excluir Empresa")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / EMPRESA / EXCLUIR EMPRESA\r\n" + "Descrição\r\n"
							+ "PARA REMOVER UMA EMPRESA, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM REMOVER");
				} else if (comboBox.getSelectedItem().equals("Excluir Usuário")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / USUÁRIO / EXCLUIR USUÁRIO\r\n" + "Descrição\r\n"
							+ "PARA REMOVER UM USUÁRIO, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM REMOVER\r\n"
							+ "Observação\r\n"
							+ "Se o usuário a ser removido tive conta ou ser reponsável por uma empresa, as mesma também serão removidas");
				} else if (comboBox.getSelectedItem().equals("Incluir Nova Conta")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / CONTA / INCLUIR NOVA CONTA\r\n" + "Descrição\r\n"
							+ "PARA INCLUIR UMA NOVA CONTA, PREENCHA OS CAMPOS OBRIGATÓRIOS E CLIQUE EM CADASTRAR\r\n"
							+ "Observação\r\n"
							+ "O usuário titular da conta ter sido incluido antes da inclusão da conta\r\n"
							+ "A empresa do titular da conta ter sido incluido antes da inclusão da conta\r\n"
							+ "Apenas as contas sálarios são associadas a empresas");
				} else if (comboBox.getSelectedItem().equals("Incluir Nova Empresa")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / EMPRESA / INCLUIR NOVA EMPRESA\r\n" + "Descrição\r\n"
							+ "PARA INCLUIR UMA NOVA EMPRESA, PREENCHA OS CAMPOS OBRIGATÓRIOS E CLIQUE EM CADASTRAR\r\n"
							+ "Observação\r\n" + "Formato do CNPJ: XX.XXX.XXX/XXXX-XX\r\n"
							+ "O usuário responsável deve ter sido incluido antes da inclusão da empresa");
				} else if (comboBox.getSelectedItem().equals("Incluir Novo Usuário")) {
					textPane.setText("Caminho\r\n" + "CADASTRO / USUÁRIO / INCLUIR NOVO USUÁRIO\r\n" + "Descrição\r\n"
							+ "PARA INCLUIR UM NOVO USUÁRIO, PREENCHA OS CAMPOS OBRIGATÓRIOS E CLIQUE EM CADASTRAR\r\n"
							+ "Observação\r\n" + "Formato do CPF: XXX.XXX.XXX-XX\r\n"
							+ "Formato do Telefone: (XX) 9XXXX-XXXX\r\n" + "Apenas um sexo pode ser escolhido");
				} else if (comboBox.getSelectedItem().equals("Listar Conta")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / CONTA / LISTAR CONTA\r\n" + "Descrição\r\n"
							+ "PARA LISTAR AS EMPRESAS, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM LISTAR");
				} else if (comboBox.getSelectedItem().equals("Listar Empresa")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / EMPRESA / LISTAR EMPRESA\r\n" + "Descrição\r\n"
							+ "PARA LISTAR AS EMPRESAS, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM LISTAR");
				} else if (comboBox.getSelectedItem().equals("Listar Usuário")) {
					textPane.setText("Caminho\r\n" + "CONSULTA / USUÁRIO / LISTAR USUÁRIO\r\n" + "Descrição\r\n"
							+ "PARA LISTAR OS USUÁRIOS, PREENCHA OS FILTROS DE ACORDO COM SUA NECESSIDADE E CLIQUE EM LISTAR");
				} else if (comboBox.getSelectedItem().equals("Operações Bancárias")) {
					textPane.setText("Caminho\r\n" + "MOVIMENTO / OPERAÇÕES BANCARIAS\r\n" + "Descrição\r\n"
							+ "PARA REALIZAR UMA OPERAÇÃO BANCÁRIA, ESCOLHA O CPF DA CONTA TITULAR E A OPERAÇÃO DEJESADA E CLIQUE EM CADASTRAR\r\n"
							+ "Observação\r\n" + "Para conta corrente há uma taxa de R$ 0.05 por operação bancária\r\n"
							+ "Para conta salária há uma taxa de R$ 0.01 por operação bancária\r\n"
							+ "Apenas conta poupança tem a opção de 'Atualizar saldo'");
				} else if (comboBox.getSelectedItem().equals("Importar Arquivos")) {
					textPane.setText("Caminho\r\n" + "MOVIMENTO / IMPORTAR ARQUIVOS\r\n" + "Descrição\r\n"
							+ "PARA IMPORTAR UM ARQUIVO, SELECIONE O ARQUIVO A SER IMPORTADO E CLIQUE EM IMPORTAR\r\n"
							+ "Observação\r\n" + "Os arquivos de importação deve estar na mesma que executável (.JAR)");
				} else if (comboBox.getSelectedItem().equals("Diagrama de Caso de Uso")) {
					ImageIcon icon = new javax.swing.ImageIcon(
							getClass().getResource("/Img/Diagrama de Caso de Uso.png"));
					textPane.setText("Diagrama de Casos de Uso");
					Mostrar("Diagrama de Casos de Uso", icon);
					// textPane.insertIcon(new
					// javax.swing.ImageIcon(getClass().getResource("/Img/Diagrama de Caso de
					// Uso.png")));
				} else if (comboBox.getSelectedItem().equals("Diagrama de Classes")) {
					try {
						URI link = new URI(
								"https://drive.google.com/file/d/1bzGj7-CE6uG-CxbWe2AOdO3m8mSaGzqZ/view?usp=sharing");
						Desktop.getDesktop().browse(link);
						textPane.setText("Diagrama de Casos de Uso\n" + "Redirecioando para o arquivo");
					} catch (URISyntaxException e1) {
						textPane.setText(
								"Diagrama de Casos de Uso\n" + "Infelizmente não foi possível abrir o arquivo");
						e1.printStackTrace();
					} catch (IOException e1) {
						textPane.setText(
								"Diagrama de Casos de Uso\n" + "Infelizmente não foi possível abrir o arquivo");

						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(360, 29, 100, 30);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Ajuda em:");
		lblNewLabel.setBounds(20, 11, 297, 14);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(22, 260, 450, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 270, 450, 2);
		contentPane.add(separator_1);
		setTitle("Ajuda");
		setResizable(false);
	}

	private void Mostrar(String str, ImageIcon icon) {
		JFrame fr = new JFrame();
		fr.setTitle(str);
		JLabel label = new JLabel(icon);
		fr.getContentPane().add(label);
		fr.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
	}
}