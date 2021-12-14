package janelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Movimentos.Operacoes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Color;

public class ExcluirUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirUsuario frame = new ExcluirUsuario();
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
	JComboBox comboBoxItem;
	JComboBox comboBoxCPF;
	JComboBox comboBoxID;
	JComboBox comboBoxNome;
	@SuppressWarnings("unchecked")
	public ExcluirUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExcluirUsuario.class.getResource("/Img/ExcluirUsuario.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Remover usuário");
		setBounds(100, 100, 295, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItem = new JLabel("Remover por:");
		lblItem.setBounds(10, 10, 152, 14);
		contentPane.add(lblItem);
		
		String[] valoresPossiveis = { "Escolha uma opção", "ID do usuário", "CPF do usuário", "Nome do usuário" };
		comboBoxItem = new JComboBox(valoresPossiveis);
		comboBoxItem.setBackground(Color.WHITE);
		comboBoxItem.setBounds(10, 24, 200, 25);
		contentPane.add(comboBoxItem);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxItem.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxItem.getSelectedItem().equals("ID do usuário")) {
					comboBoxID.setVisible(true);
					comboBoxNome.setVisible(false);
					comboBoxCPF.setVisible(false);
				}else if(comboBoxItem.getSelectedItem().equals("CPF do usuário")) {
					comboBoxID.setVisible(false);
					comboBoxNome.setVisible(false);
					comboBoxCPF.setVisible(true);
				}else if(comboBoxItem.getSelectedItem().equals("Nome do usuário")) {
					comboBoxID.setVisible(false);
					comboBoxNome.setVisible(true);
					comboBoxCPF.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(220, 24, 50, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblItem1 = new JLabel("Selecione um item:");
		lblItem1.setBounds(10, 58, 274, 14);
		contentPane.add(lblItem1);
		
		String[] ids = null;
		try {
			ids = p.IDs("Usuarios.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxID = new JComboBox(ids);
		comboBoxID.setBackground(Color.WHITE);
		comboBoxID.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxID);
		comboBoxID.setVisible(false);
		
		String[] cpfs = null;
		try {
			cpfs = p.Posicao3("Usuarios.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxCPF = new JComboBox(cpfs);
		comboBoxCPF.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxCPF);
		comboBoxCPF.setVisible(false);
		
		String[] nomes = null;
		try {
			nomes = p.Posicao1("Usuarios.txt");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		comboBoxNome = new JComboBox<Object>(nomes);
		comboBoxNome.setBounds(10, 73, 260, 25);
		contentPane.add(comboBoxNome);
		comboBoxNome.setVisible(false);
		
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
				}else if(comboBoxItem.getSelectedItem().equals("ID do usuário")
				) {
					try {
						porID();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else if(comboBoxItem.getSelectedItem().equals("CPF do usuário")) {
					try {
						porCPF();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else if(comboBoxItem.getSelectedItem().equals("Nome do usuário")) {
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
	protected void porNome() throws IOException {
		if(comboBoxNome.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum nome selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String nome = (String) comboBoxNome.getSelectedItem();
			String[] confere = p.consultaCNPJ(nome, "Usuarios.txt");	
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Usuário já removido, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = 2;
				
				String[] str_conta = p.consultaCPF(confere[3], "Contas.txt");
				String[] str_empresa = p.consultaCPF(confere[3], "Empresas.txt");
				
				if(str_conta == null &&
					str_empresa == null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja remover o usuário " + confere[1] + "?",
							"Confirmação",
							JOptionPane.YES_NO_OPTION);	
				}else if(str_conta == null &&
						str_empresa != null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida\n" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa == (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa != (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nUsuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}
				
				if(res == 0) {
					int id = Integer.parseInt(confere[0]);
					try {
						p.remove(id, "Usuarios.txt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Usuário removido com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				}else {
		    		JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
				}
			}
		LimparTex();
		}
	}
	protected void porCPF() throws IOException {
		if(comboBoxCPF.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum CPF selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String cpf = (String) comboBoxCPF.getSelectedItem();
			String[] confere = p.consultaCPF(cpf, "Usuarios.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Usuário já removido, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = 2;
				
				String[] str_conta = p.consultaCPF(cpf, "Contas.txt");
				String[] str_empresa = p.consultaCPF(cpf, "Empresas.txt");
				
				if(str_conta == null &&
					str_empresa == null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja remover o usuário " + confere[1] + "?",
							"Confirmação",
							JOptionPane.YES_NO_OPTION);	
				}else if(str_conta == null &&
						str_empresa != null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida\n" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa == (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa != (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nUsuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}
				
				if(res == 0) {
					int id = Integer.parseInt(confere[0]);
					try {
						p.remove(id, "Usuarios.txt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Usuário removido com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				}else {
		    		JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
				}
			}
		LimparTex();
		}
	}
	protected void porID() throws IOException {
		if(comboBoxID.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum ID selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			int id = Integer.parseInt((String) comboBoxID.getSelectedItem());
			String[] confere = p.consultaId(id, "Usuarios.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Usuário já removido, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = 2;
				
				String[] str_conta = p.consultaCPF(confere[3], "Contas.txt");
				String[] str_empresa = p.consultaCPF(confere[3], "Empresas.txt");
				
				if(str_conta == null &&
					str_empresa == null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja remover o usuário " + confere[1] + "?",
							"Confirmação",
							JOptionPane.YES_NO_OPTION);	
				}else if(str_conta == null &&
						str_empresa != null
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida\n" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa == (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}else if(str_conta != null  &&
						str_empresa != (null)
				) {
					res = JOptionPane.showConfirmDialog(null,
							"Usuário tem uma conta, se remover o mesmo a conta também será removida" +
							"\nUsuário é responsável por ao menos uma empresa, se remover o mesmo a empresa também será removida" +
						    "\nTem certeza que deseja remover o usuário " + confere[1] + "?",
						    "Confirmação",
						    JOptionPane.YES_NO_OPTION);	
				}
				
				if(res == 0) {
					try {
						p.remove(id, "Usuarios.txt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Usuário removido com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				}else {
		    		JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
				}
			}
		LimparTex();
		}	
	}
	public void LimparTex() {
		comboBoxItem.setSelectedIndex(0);
		comboBoxID.setSelectedIndex(0);
		comboBoxNome.setSelectedIndex(0);
		comboBoxCPF.setSelectedIndex(0);
		comboBoxID.setVisible(false);
		comboBoxNome.setVisible(false);
		comboBoxCPF.setVisible(false);
	}
}