package janelas;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Movimentos.Operacoes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class ExcluirEmpresa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirEmpresa frame = new ExcluirEmpresa();
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
	JComboBox comboBoxNome;
	JComboBox comboBoxCNPJ;
	JComboBox comboBoxID;
	JComboBox comboBoxItem;
	public ExcluirEmpresa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExcluirEmpresa.class.getResource("/Img/ExcluirEmpresa.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Remover empresa");
		setBounds(100, 100, 295, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Remover por:");
		lblNewLabel.setBounds(10, 10, 176, 14);
		contentPane.add(lblNewLabel);
		
		String[] valoresPossiveis = { "Escolha uma opção", "ID", "CNPJ", "Razão Social" };
		comboBoxItem = new JComboBox(valoresPossiveis);
		comboBoxItem.setBackground(Color.WHITE);
		comboBoxItem.setBounds(10, 25, 200, 25);
		contentPane.add(comboBoxItem);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxItem.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxItem.getSelectedItem().equals("ID")) {
					comboBoxID.setVisible(true);
					comboBoxNome.setVisible(false);
					comboBoxCNPJ.setVisible(false);
				}else if(comboBoxItem.getSelectedItem().equals("CNPJ")) {
					comboBoxID.setVisible(false);
					comboBoxNome.setVisible(false);
					comboBoxCNPJ.setVisible(true);
				}else if(comboBoxItem.getSelectedItem().equals("Razão Social")) {
					comboBoxID.setVisible(false);
					comboBoxNome.setVisible(true);
					comboBoxCNPJ.setVisible(false);
				}
			}
		});
		btnOk.setBounds(220, 25, 50, 25);
		contentPane.add(btnOk);
		
		String[] ids = null;
		try {
			ids = p.IDs("Empresas.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxID = new JComboBox(ids);
		comboBoxID.setBackground(Color.WHITE);
		comboBoxID.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxID);
		comboBoxID.setVisible(false);
		
		String[] nomes = null;
		try {
			nomes = p.Posicao3("Empresas.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxNome = new JComboBox(nomes);
		comboBoxNome.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxNome);
		comboBoxNome.setVisible(false);
		
		String[] cnpjs = null;
		try {
			cnpjs = p.Posicao1("Empresas.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxCNPJ = new JComboBox(cnpjs);
		comboBoxCNPJ.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxCNPJ);
		comboBoxCNPJ.setVisible(false);
		
		JLabel lblItem = new JLabel("Selecione um item:");
		lblItem.setBounds(10, 58, 274, 14);
		contentPane.add(lblItem);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(20, 115, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxItem.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxItem.getSelectedItem().equals("ID")
				) {
					try {
						porID();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}else if(comboBoxItem.getSelectedItem().equals("CNPJ")) {
					try {
						porCNPJ();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}else if(comboBoxItem.getSelectedItem().equals("Razão Social")) {
					try {
						porNome();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btnRemover.setBounds(156, 115, 100, 30);
		contentPane.add(btnRemover);
	}
	public void porID() throws IOException{
		if(comboBoxID.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum ID selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String nome = (String) comboBoxNome.getSelectedItem();
			String[] confere = p.consultaRazaoSocial(nome, "Empresas.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Empresa já removida, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = JOptionPane.showConfirmDialog(null,
	    				"Tem certeza que deseja remover a empresa " + confere[3] + "?",
	    				"Confirmação",
	    				JOptionPane.YES_NO_OPTION);
	    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
	    		if(res == 0) {
	    			int id = Integer.parseInt(confere[0]);
					try {
						p.remove(id, "Empresas.txt");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Empresa removida com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
	    		}else {
	    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	    		}
	    		LimparTex();
			}
		}
	}
	public void porNome() throws IOException{
		if(comboBoxNome.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhuma razão social selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String nome = (String) comboBoxNome.getSelectedItem();
			String[] confere = p.consultaRazaoSocial(nome, "Empresas.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Empresa já removida, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = JOptionPane.showConfirmDialog(null,
	    				"Tem certeza que deseja remover a empresa " + confere[3] + "?",
	    				"Confirmação",
	    				JOptionPane.YES_NO_OPTION);
	    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
	    		if(res == 0) {
	    			int id = Integer.parseInt(confere[0]);
					try {
						p.remove(id, "Empresas.txt");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Empresa removida com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
	    		}else {
	    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	    		}
	    		LimparTex();
			}
		}
	}
	public void porCNPJ() throws IOException{
		if(comboBoxCNPJ.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum CNPJ selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String cnpj = (String) comboBoxCNPJ.getSelectedItem();
			String[] confere = p.consultaCNPJ(cnpj, "Empresas.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Empresa já removida, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = JOptionPane.showConfirmDialog(null,
	    				"Tem certeza que deseja remover a empresa " + confere[3] + "?",
	    				"Confirmação",
	    				JOptionPane.YES_NO_OPTION);
	    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
	    		if(res == 0) {
	    			int id = Integer.parseInt(confere[0]);
					try {
						p.remove(id, "Empresas.txt");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Empresa removida com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
	    		}else {
	    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	    		}
				LimparTex();
			}
		}
	}
	public void LimparTex() {
		comboBoxItem.setSelectedIndex(0);
		comboBoxID.setSelectedIndex(0);
		comboBoxNome.setSelectedIndex(0);
		comboBoxCNPJ.setSelectedIndex(0);
		comboBoxID.setVisible(false);
		comboBoxNome.setVisible(false);
		comboBoxCNPJ.setVisible(false);
	}
}
