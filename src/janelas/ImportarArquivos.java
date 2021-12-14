package janelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ClasseDeDados.Empresa;
import Movimentos.ImportarContas;
import Movimentos.ImportarEmpresas;
import Movimentos.ImportarUsuarios;
import java.awt.Toolkit;
public class ImportarArquivos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportarArquivos frame = new ImportarArquivos();
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
	JRadioButton RadioButtonEmpresastxt;
	JRadioButton RadioButtonClientestxt;
	JRadioButton RadioButtonContastxt;

	public ImportarArquivos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImportarArquivos.class.getResource("/Img/salvar-arquivo (1).png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Importar arquivos");
		// setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 344, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelImportarArquivos = new JLabel("Importar Arquivos");
		LabelImportarArquivos.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImportarArquivos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		LabelImportarArquivos.setBounds(48, 11, 246, 29);
		getContentPane().add(LabelImportarArquivos);

		JLabel LabelEscolhaArquivo = new JLabel("Escolha o arquivo:");
		LabelEscolhaArquivo.setBounds(38, 72, 107, 14);
		getContentPane().add(LabelEscolhaArquivo);

		RadioButtonClientestxt = new JRadioButton("Usuarios.txt");
		RadioButtonClientestxt.setBounds(36, 93, 109, 23);
		getContentPane().add(RadioButtonClientestxt);

		RadioButtonEmpresastxt = new JRadioButton("Empresas.txt");
		RadioButtonEmpresastxt.setBounds(36, 119, 109, 23);
		getContentPane().add(RadioButtonEmpresastxt);

		RadioButtonContastxt = new JRadioButton("Contas.txt");
		RadioButtonContastxt.setBounds(36, 145, 109, 23);
		getContentPane().add(RadioButtonContastxt);

		JButton ButtonImportar = new JButton("Importar");
		ButtonImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImportaArquivo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ButtonImportar.setBounds(184, 178, 99, 33);
		getContentPane().add(ButtonImportar);

		JButton ButtonVoltar = new JButton("Voltar");
		ButtonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		ButtonVoltar.setBounds(59, 178, 99, 33);
		getContentPane().add(ButtonVoltar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 308, 2);
		contentPane.add(separator);
	}

	public void ImportaArquivo() throws NumberFormatException, Exception {
		// apenas Cliente.txt foi selecionado
		try {
			if (RadioButtonClientestxt.isSelected() == true && RadioButtonEmpresastxt.isSelected() == false
					&& RadioButtonContastxt.isSelected() == false) {
				ImportarUsuarios u = new ImportarUsuarios();
			} // apenas Empresa.txt foi selecionado
			else if (RadioButtonClientestxt.isSelected() == false && RadioButtonEmpresastxt.isSelected() == true
					&& RadioButtonContastxt.isSelected() == false) {
				ImportarEmpresas e = new ImportarEmpresas();
			} // apenas conta.txt foi selecionado
			else if (RadioButtonClientestxt.isSelected() == false && RadioButtonEmpresastxt.isSelected() == false
					&& RadioButtonContastxt.isSelected() == true) {
				ImportarContas c = new ImportarContas();
			} // nenhum arquivo foi selecionado
			else if (RadioButtonClientestxt.isSelected() == false && RadioButtonEmpresastxt.isSelected() == false
					&& RadioButtonContastxt.isSelected() == false) {
				JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "Aviso de Erro!",
						JOptionPane.ERROR_MESSAGE);
			} // possivelmente dois arquivos foram selecionados
			else {
				JOptionPane.showMessageDialog(null, "Mais de um arquivo selecionado", "Aviso de Erro!",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (FileNotFoundException fnf) {
		} catch (NullPointerException np) {
		}
	}
}
