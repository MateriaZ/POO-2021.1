package janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Movimentos.Operacoes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class ExcluirConta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirConta frame = new ExcluirConta();
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
	JComboBox comboBoxCpf;
	public ExcluirConta() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExcluirConta.class.getResource("/Img/ExcluirConta.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 140);
		setResizable(false);
		setTitle("Remover conta");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("Selecione o CPF do titular da conta: ");
		lblTexto.setBounds(10, 10, 264, 16);
		contentPane.add(lblTexto);
		
		String[] cpfs = p.Posicao3("Contas.txt");
		comboBoxCpf = new JComboBox(cpfs);
		comboBoxCpf.setBackground(Color.WHITE);
		comboBoxCpf.setBounds(10, 26, 264, 22);
		contentPane.add(comboBoxCpf);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(10, 60, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnNewButton_1 = new JButton("Remover");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					remover();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(174, 59, 100, 30);
		contentPane.add(btnNewButton_1);
	}
	public void remover() throws IOException {
		if(comboBoxCpf.getSelectedItem().equals("Escolha uma opção")) {
			JOptionPane.showMessageDialog(null, "Nenhum CPF selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else {
			String cpf = (String) comboBoxCpf.getSelectedItem();
			String[] confere = p.consultaCPF(cpf,"Contas.txt");
			if(confere == null) {
				JOptionPane.showMessageDialog(null, "Empresa já removida, por favor reinicie a janela","Aviso!", JOptionPane.WARNING_MESSAGE);
			}else {
				int res = JOptionPane.showConfirmDialog(null,
	    				"Tem certeza que deseja remover a conta com CPF " + confere[3] + "?",
	    				"Confirmação",
	    				JOptionPane.YES_NO_OPTION);
	    		//Obs: Retorna um inteiro (0- yes 1-no 2- cancel)
	    		if(res == 0) {
	    			int id_conta =  Integer.parseInt(confere[0]);
	    			p.remove(id_conta, "Contas.txt");
					JOptionPane.showMessageDialog(null, "Conta removida com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
	    		}else {
	    			JOptionPane.showMessageDialog(null,"Operação cancelada", "Aviso de cancelamento!", JOptionPane.WARNING_MESSAGE);
	    		}
			}
		LimparTex();
		}
	}
	private void LimparTex() {
		comboBoxCpf.setSelectedIndex(0);
	}
}
