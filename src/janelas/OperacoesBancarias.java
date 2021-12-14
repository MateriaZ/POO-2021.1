package janelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClasseDeDados.ContaCorrente;
import ClasseDeDados.ContaPoupanca;
import ClasseDeDados.ContaSalario;
import ClasseDeDados.Usuario;
import Movimentos.Operacoes;
import excecoes.Negativo;
import excecoes.Percentual;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class OperacoesBancarias extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperacoesBancarias frame = new OperacoesBancarias();
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
	public OperacoesBancarias() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OperacoesBancarias.class.getResource("/Img/execucao (1).png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Operações bancárias");
		setBounds(100, 100, 300, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCPF_titular = new JLabel("Selecione o CPF da conta do titular e clique ok:");
		lblCPF_titular.setBounds(10, 10, 274, 14);
		contentPane.add(lblCPF_titular);
		
		String[] cpfs = null;
		try {
			cpfs = p.Posicao3("Contas.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxCPF_titular = new JComboBox<Object>(cpfs);
		comboBoxCPF_titular.setBackground(Color.WHITE);
		comboBoxCPF_titular.setForeground(Color.BLACK);
		comboBoxCPF_titular.setBounds(10, 25, 200, 25);
		contentPane.add(comboBoxCPF_titular);
		
		
		btnOk = new JButton("Ok"); //titular
		btnOk.setBackground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxOpcoes.setSelectedIndex(0);
				comboBoxCPF_transfere.setSelectedIndex(0);
				comboBoxCPF_transfere.setEnabled(false);
				lblEscolhaOCpf.setEnabled(false);
				textPaneDadosTrans.setEnabled(false);
				textPaneDadosTrans.setEditable(false);
				textPaneDadosTrans.setText("Para habilitar a opção de transfêrencia\n"
						+ "deixe-a marcada e clique em Confirmar");
				if(comboBoxCPF_titular.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
					textPaneDadosTitular.setText("");
				}else {
					String cpf = (String) comboBoxCPF_titular.getSelectedItem();
					str_conta_titular = null;
					try {
						str_conta_titular = p.consultaCPF(cpf, "Contas.txt");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					if(str_conta_titular[2].equals("ContaSalario")) { cont = "Conta Salário"; }
					else if(str_conta_titular[2].equals("ContaPoupanca")) { cont = "Conta Poupança"; }
					else if(str_conta_titular[2].equals("ContaCorrente")){ cont = "Conta Corrente"; }
					
					double saldo = Double.parseDouble(str_conta_titular[1]);
					
					try {
						escrevePainel(cpf, cont, saldo);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnOk.setBounds(220, 25, 50, 25);
		contentPane.add(btnOk);
		
		lblSelecioneAOperao = new JLabel("Selecione a opera\u00E7\u00E3o desejada:");
		lblSelecioneAOperao.setBounds(10, 142, 226, 14);
		contentPane.add(lblSelecioneAOperao);
		
		String[] valoresPossiveis = { "Escolha uma opção", "Sacar", "Depositar", "Transferência", "Atualizar Saldo"};
		comboBoxOpcoes = new JComboBox<Object>(valoresPossiveis);
		comboBoxOpcoes.setBackground(Color.WHITE);
		comboBoxOpcoes.setForeground(Color.BLACK);
		comboBoxOpcoes.setBounds(10, 157, 260, 25);
		contentPane.add(comboBoxOpcoes);
		
		comboBoxCPF_transfere = new JComboBox<Object>(cpfs);
		comboBoxCPF_transfere.setEnabled(false);
		comboBoxCPF_transfere.setBackground(Color.WHITE);
		comboBoxCPF_transfere.setBounds(10, 203, 200, 25);
		contentPane.add(comboBoxCPF_transfere);
		
		lblEscolhaOCpf = new JLabel("Escolha o CPF para transfer\u00EAncia:");
		lblEscolhaOCpf.setEnabled(false);
		lblEscolhaOCpf.setBounds(10, 187, 226, 14);
		contentPane.add(lblEscolhaOCpf);
		
		lblVisualisarDados = new JLabel("Dados da conta:");
		lblVisualisarDados.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVisualisarDados.setBounds(10, 50, 274, 14);
		contentPane.add(lblVisualisarDados);
		
		textPaneDadosTitular = new JTextPane();
		textPaneDadosTitular.setBackground(SystemColor.info);
		textPaneDadosTitular.setEditable(false);
		textPaneDadosTitular.setBounds(10, 66, 260, 73);
		contentPane.add(textPaneDadosTitular);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(20, 295, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnConfirmacao = new JButton("Confirmar");
		btnConfirmacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCPF_titular.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxOpcoes.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione uma operação","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxOpcoes.getSelectedItem().equals("Transferência") &&
				comboBoxCPF_transfere.isEnabled() == false
				 ){
					comboBoxOpcoes.setSelectedIndex(3);
					comboBoxCPF_transfere.setSelectedIndex(0);
					comboBoxCPF_transfere.setEnabled(true);
					lblEscolhaOCpf.setEnabled(true);
					textPaneDadosTrans.setEnabled(true);
					btnOk2.setEnabled(true);
					textPaneDadosTrans.setText("");
				}else if(comboBoxOpcoes.getSelectedItem().equals("Sacar")) {
					Sacar();
				}else if(comboBoxOpcoes.getSelectedItem().equals("Depositar")) {
					Depositar();
				}else if(comboBoxOpcoes.getSelectedItem().equals("Atualizar Saldo") &&
				cont.equals("Conta Salário")) {
					JOptionPane.showMessageDialog(null, "Operação de 'Atualizar Saldo' somente para contas poupanças","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxOpcoes.getSelectedItem().equals("Atualizar Saldo") &&
				cont.equals("Conta Poupança")) {
					atualizaSaldo();
				}else if(comboBoxOpcoes.getSelectedItem().equals("Atualizar Saldo") &&
				cont.equals("Conta Corrente")) {
					JOptionPane.showMessageDialog(null, "Operação de 'Atualizar Saldo' somente para contas poupanças","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxCPF_transfere.getSelectedItem().equals("Escolha uma opção") ||
				textPaneDadosTrans.getText().equals("")
				) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
				}else if(comboBoxCPF_transfere.getSelectedItem().equals(comboBoxCPF_titular.getSelectedItem())){
					JOptionPane.showMessageDialog(null, "Não é possível transfir para a mesma conta","Aviso!", JOptionPane.ERROR_MESSAGE);
					LimparCampos();
				}else if(comboBoxOpcoes.getSelectedItem().equals("Transferência") &&
				comboBoxCPF_transfere.isEnabled() == true
				 ){
					transfere();
				}
			}
		});
		btnConfirmacao.setBounds(160, 295, 100, 30);
		contentPane.add(btnConfirmacao);
		
		textPaneDadosTrans = new JTextPane();
		textPaneDadosTrans.setBackground(SystemColor.info);
		textPaneDadosTrans.setEnabled(false);
		textPaneDadosTrans.setEditable(false);
		textPaneDadosTrans.setBounds(10, 246, 260, 42);
		contentPane.add(textPaneDadosTrans);
		textPaneDadosTrans.setText("Para habilitar a opção de transfêrencia\n"
				+ "deixe-a marcada e clique em Confirmar");
		
		btnOk2 = new JButton("Ok");
		btnOk2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCPF_transfere.getSelectedItem().equals("Escolha uma opção")) {
					JOptionPane.showMessageDialog(null, "Selecione um item e clique OK","Aviso!", JOptionPane.WARNING_MESSAGE);
					textPaneDadosTrans.setText("");
				}else {
					String cpf = (String) comboBoxCPF_transfere.getSelectedItem();
					str_conta_trans = null;
					try {
						str_conta_trans = p.consultaCPF(cpf, "Contas.txt");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					if(str_conta_trans[2].equals("ContaSalario")) { contT = "Conta Salário"; }
					else if(str_conta_trans[2].equals("ContaPoupanca")) { contT = "Conta Poupança"; }
					else if(str_conta_trans[2].equals("ContaCorrente")){ contT = "Conta Corrente"; }
					
					double saldo = Double.parseDouble(str_conta_trans[1]);
					
					try {
						escrevePainelTras(cpf, contT, saldo);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnOk2.setBackground(Color.WHITE);
		btnOk2.setBounds(220, 204, 50, 25);
		contentPane.add(btnOk2);
		btnOk2.setEnabled(false);
		
		JLabel lblVisualisarDados_1 = new JLabel("Dados da conta:");
		lblVisualisarDados_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVisualisarDados_1.setBounds(10, 230, 274, 14);
		contentPane.add(lblVisualisarDados_1);
		
	}
	protected void atualizaSaldo() {
		boolean continua = false;
		boolean prosseguir = true;
		double valor = 0;
		while(continua == false) {
			try {
				valor = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o valor do percentual", "Atualizar Saldo",
				JOptionPane.QUESTION_MESSAGE));				//Exibe um diálogo que impõe um pergunta ao usuário. Em botões Yes ou NO
				if(valor <= 0){
					throw new Negativo();
				}if(valor>100)
					throw new Percentual();
				continua = true;
			}catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null,"Valor inválido", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	        }catch (Negativo ex) {
	        	//chama a JOptionPane da classe
	        }catch (NullPointerException ex) {
	        	JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	        	continua = true;
	        	prosseguir = false;
	        } catch (Percentual e) {
	        	//chama a JOptionPane da classe
				e.printStackTrace();
			}
		}
		if(prosseguir == true) {
		ContaPoupanca conta_titular = new ContaPoupanca();
		conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
				Double.parseDouble(str_conta_titular[1]), Usuario_titular);
    		DecimalFormat df = new DecimalFormat("#.00");
    		int res = JOptionPane.showConfirmDialog(null,
    				"Tem certeza que deseja atualizar o saldo em " + df.format(valor) + "% ?",
    				"Confirmação",
    				JOptionPane.YES_NO_OPTION);
    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
    		if(res == 0) {
	    		conta_titular.atualizaSaldo(valor);
	    		try {
					p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
						JOptionPane.PLAIN_MESSAGE);
    		}else {
    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
    		}		    	
    	LimparCampos();
		}
	}
	protected void transfere() {
		boolean continua = false;
		boolean prosseguir = true;
		double valor = 0;
		double valor2 = 0;
		while(continua == false) {
			try {
				valor = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o valor da transferência", "Transfirir",
				JOptionPane.QUESTION_MESSAGE));				//Exibe um diálogo que impõe um pergunta ao usuário. Em botões Yes ou NO
				if(valor <= 0){
					throw new Negativo();
				}
				continua = true;
			}catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null,"Valor inválido", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	        }catch (Negativo ex) {
	        	//chama a JOptionPane da classe
	        }catch (NullPointerException ex) {
	        	JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	        	continua = true;
	        	prosseguir = false;
	        }
		}
		if(prosseguir == true) {
			if(cont == "Conta Corrente") {
				ContaCorrente conta_titular = new ContaCorrente();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if((valor -conta_titular.getTaxa()) <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja transferir R$ " + df.format(valor) + " para "+ Usuario_transferente[1] +"?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
		    			if(contT == "Conta Corrente") {
		    				ContaCorrente conta_transferente = new ContaCorrente();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}else if(contT == "Conta Poupança") {
		    				ContaPoupanca conta_transferente = new ContaPoupanca();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			if(contT == "Conta Salário") {
		    				ContaSalario conta_transferente = new ContaSalario();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
							p.atualizaSaldo(str_conta_trans[3], valor2);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	LimparCampos();
		    	
			}else if(cont == "Conta Poupança") {
				ContaPoupanca conta_titular = new ContaPoupanca();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if(valor <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja transferir R$ " + df.format(valor) + " para "+ Usuario_transferente[1] +"?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
		    			if(contT == "Conta Corrente") {
		    				ContaCorrente conta_transferente = new ContaCorrente();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}else if(contT == "Conta Poupança") {
		    				ContaPoupanca conta_transferente = new ContaPoupanca();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			if(contT == "Conta Salário") {
		    				ContaSalario conta_transferente = new ContaSalario();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
							p.atualizaSaldo(str_conta_trans[3], valor2);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	
		    	LimparCampos();
				
			}else if(cont == "Conta Salário") {
				ContaSalario conta_titular = new ContaSalario();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if(valor-conta_titular.getTaxa() <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja transferir R$ " + df.format(valor) + " para "+ Usuario_transferente[1] +"?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
		    			if(contT == "Conta Corrente") {
		    				ContaCorrente conta_transferente = new ContaCorrente();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}else if(contT == "Conta Poupança") {
		    				ContaPoupanca conta_transferente = new ContaPoupanca();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			if(contT == "Conta Salário") {
		    				ContaSalario conta_transferente = new ContaSalario();
		    				conta_transferente.recriarObjetoConta(Integer.parseInt(str_conta_trans[0]), str_conta_trans[3],
		    						Double.parseDouble(str_conta_trans[1]), Usuario_transferente);
		    				conta_titular.transferir(valor, conta_transferente);
		    				valor2 = conta_transferente.getSaldo();
		    			}
		    			try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
							p.atualizaSaldo(str_conta_trans[3], valor2);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	
		    	LimparCampos();
			}
		}
	}
	protected void Depositar() {
		boolean continua = false;
		boolean prosseguir = true;
		double valor = 0;
		while(continua == false) {
			try {
				valor = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o valor do deposito", "Depositar",
				JOptionPane.QUESTION_MESSAGE));				//Exibe um diálogo que impõe um pergunta ao usuário. Em botões Yes ou NO
				if(valor <= 0){
					throw new Negativo();
				}
				continua = true;
			}catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null,"Valor inválido", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	        }catch (Negativo ex) {
	        	//chama a JOptionPane da classe
	        }catch (NullPointerException ex) {
	        	JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	        	continua = true;
	        	prosseguir = false;
	        }
		}
		if(prosseguir == true) {
			if(cont == "Conta Corrente") {
				ContaCorrente conta_titular = new ContaCorrente();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja depositar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.depositar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}		    	
		    	LimparCampos();
		    	
			}else if(cont == "Conta Poupança") {
				ContaPoupanca conta_titular = new ContaPoupanca();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja depositar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.depositar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}		    	
		    	LimparCampos();
				
			}else if(cont == "Conta Salário") {
				ContaSalario conta_titular = new ContaSalario();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja depositar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.depositar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}		    	
		    	LimparCampos();
		    	
			}		
		}
	}
	
	protected void Sacar() {
		boolean continua = false;
		boolean prosseguir = true;
		double valor = 0;
		while(continua == false) {
			try {
				valor = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite o valor do saque", "Sacar",
				JOptionPane.QUESTION_MESSAGE));				//Exibe um diálogo que impõe um pergunta ao usuário. Em botões Yes ou NO
				if(valor <= 0){
					throw new Negativo();
				}
				continua = true;
			}catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null,"Valor inválido", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);	
	        }catch (Negativo ex) {
	        	//chama a JOptionPane da classe
	        }catch (NullPointerException ex) {
	        	JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	        	continua = true;
	        	prosseguir = false;
	        }
		}
		if(prosseguir == true) {
			if(cont == "Conta Corrente") {
				ContaCorrente conta_titular = new ContaCorrente();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if(valor-conta_titular.getTaxa() <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja sacar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.sacar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	
		    	LimparCampos();
			}else if(cont == "Conta Poupança") {
				ContaPoupanca conta_titular = new ContaPoupanca();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if(valor <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja sacar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.sacar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	
		    	LimparCampos();
				
			}else if(cont == "Conta Salário") {
				ContaSalario conta_titular = new ContaSalario();
				conta_titular.recriarObjetoConta(Integer.parseInt(str_conta_titular[0]), str_conta_titular[3],
						Double.parseDouble(str_conta_titular[1]), Usuario_titular);
				if(valor-conta_titular.getTaxa() <= conta_titular.getSaldo()) {
		    		DecimalFormat df = new DecimalFormat("#.00");
		    		int res = JOptionPane.showConfirmDialog(null,
		    				"Tem certeza que deseja sacar R$ " + df.format(valor) + "?",
		    				"Confirmação",
		    				JOptionPane.YES_NO_OPTION);
		    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
		    		if(res == 0) {
			    		conta_titular.sacar(valor);
			    		try {
							p.atualizaSaldo(conta_titular.getCpfUsuario(), conta_titular.getSaldo());
						} catch (Exception e) {
							e.printStackTrace();
						}
			    		JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "Aviso!",
								JOptionPane.PLAIN_MESSAGE);
		    		}else {
		    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
		    		}
		    	}else {
			    		JOptionPane.showMessageDialog(null,"Saldo insuficiente", "Aviso de erro!", JOptionPane.ERROR_MESSAGE);
			    	}
		    	
		    	LimparCampos();
			}		
		}
	}
	
	private void LimparCampos() {
		comboBoxCPF_titular.setSelectedIndex(0);
		textPaneDadosTitular.setText("");
		comboBoxOpcoes.setSelectedIndex(0);
		comboBoxCPF_transfere.setSelectedIndex(0);
		comboBoxCPF_transfere.setEnabled(false);
		lblEscolhaOCpf.setEnabled(false);
		textPaneDadosTrans.setEnabled(false);
		textPaneDadosTrans.setEditable(false);
		textPaneDadosTrans.setText("Para habilitar a opção de transfêrencia\n"
				+ "deixe-a marcada e clique em Confirmar");
	}

	protected String[] Usuario_titular;
	protected void escrevePainel(String cpf, String tipoConta, double saldo) throws IOException {
		Usuario_titular = p.consultaCPF(cpf, "Usuarios.txt");
		if(Usuario_titular == null) {
			textPaneDadosTitular.setText("Usuario não encontrado, verifique!");
		}else {
			DecimalFormat df = new DecimalFormat("#.00");
			textPaneDadosTitular.setText("Nome titular: "+ Usuario_titular[1] + 
					"\nTelefone: " + Usuario_titular[2] + 
					"\nSaldo da conta: R$" + df.format(saldo) +
					"\n" + tipoConta
					);
		}
	}
	protected String[] Usuario_transferente;
	protected void escrevePainelTras(String cpf, String tipoConta, double saldo) throws IOException {
		Usuario_transferente = p.consultaCPF(cpf, "Usuarios.txt");
		textPaneDadosTrans.setText("Nome titular: "+ Usuario_transferente[1] +
				"\n" + tipoConta
				);
	}
	private Operacoes p = new Operacoes();
	private JComboBox comboBoxCPF_titular;
	private JComboBox comboBoxOpcoes;
	private JComboBox comboBoxCPF_transfere;
	private JLabel lblCPF_titular;
	private JLabel lblSelecioneAOperao;
	private JLabel lblEscolhaOCpf;
	private JButton btnOk;
	private JButton btnOk2;
	private JTextPane textPaneDadosTitular;
	private JTextPane textPaneDadosTrans;
	private JLabel lblVisualisarDados;
	private String cont = null; //tipo conta do titular
	private String contT = null; // tipo de conta do tranferente
	private String[] str_conta_titular; //conta do titular
	private String[] str_conta_trans; //conta para transferencia
}
