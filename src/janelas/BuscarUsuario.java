package janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Movimentos.Operacoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Toolkit;

public class BuscarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textConteudo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarUsuario frame = new BuscarUsuario();
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
	JRadioButton RadioButtonID;
	JRadioButton RadioButtonCPF;
	JRadioButton RadioButtonNome;
	JTextPane textResultado;
	public BuscarUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarUsuario.class.getResource("/Img/BuscaUsuario.png")));
		setResizable(false);
		setTitle("Buscar Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 315);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RadioButtonID = new JRadioButton("ID Usu\u00E1rio");
		RadioButtonID.setBounds(35, 29, 98, 23);
		contentPane.add(RadioButtonID);
		
		RadioButtonCPF = new JRadioButton("CPF Usu\u00E1rio");
		RadioButtonCPF.setBounds(135, 29, 109, 23);
		contentPane.add(RadioButtonCPF);
		
		RadioButtonNome = new JRadioButton("Nome Usu\u00E1rio");
		RadioButtonNome.setBounds(246, 29, 109, 23);
		contentPane.add(RadioButtonNome);
		
		JLabel LebelBuscar = new JLabel("Buscar via");
		LebelBuscar.setBounds(35, 11, 74, 14);
		contentPane.add(LebelBuscar);
		
		textConteudo = new JTextField();
		textConteudo.setBounds(35, 75, 267, 20);
		contentPane.add(textConteudo);
		textConteudo.setColumns(10);
		
		JLabel LabelConteudo = new JLabel("Digite o Conte\u00FAdo da Busca");
		LabelConteudo.setBounds(35, 59, 179, 14);
		contentPane.add(LabelConteudo);
		
		JButton BtnVoltar = new JButton("Voltar");
		BtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BtnVoltar.setBounds(35, 231, 110, 23);
		contentPane.add(BtnVoltar);
		
		JButton BtnBuscar = new JButton("Buscar");
		BtnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidaCampos();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		BtnBuscar.setBounds(223, 231, 110, 23);
		contentPane.add(BtnBuscar);
		
		textResultado = new JTextPane();
		textResultado.setEditable(false);
		textResultado.setBounds(35, 106, 298, 114);
		contentPane.add(textResultado);
	}
	public void ValidaCampos() throws IOException {
		Operacoes op = new Operacoes();
		if(RadioButtonID.isSelected() == false &&
		   RadioButtonCPF.isSelected() == false &&
		   RadioButtonNome.isSelected() == false ||
		   textConteudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo obrigatório está vazio", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}
		else if(RadioButtonID.isSelected() == true &&
				RadioButtonCPF.isSelected() == true ||
				RadioButtonID.isSelected() == true &&
				RadioButtonNome.isSelected() == true ||
				RadioButtonCPF.isSelected() == true &&
				RadioButtonNome.isSelected() == true ||
				textConteudo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mais de uma forma de busca assinalada", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}
		else if(RadioButtonID.isSelected() == true &&
				RadioButtonCPF.isSelected() == false &&
				RadioButtonNome.isSelected() == false ||
				textConteudo.getText().equals("")) {
					int id;
					if(textConteudo.getText().matches("[0-9]+")) {
						String[] str = op.consultaId(id = Integer.parseInt(textConteudo.getText()), "Usuarios.txt");
						if(str == null) {
							JOptionPane.showMessageDialog(null, "ID do usuário não localizado na base de dados!", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							textResultado.setText(toString(str));
						}
						LimparTex();
					}else {
						JOptionPane.showMessageDialog(null, "Digite um valor numérico", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
					}
		}
		else if(RadioButtonID.isSelected() == false &&
				RadioButtonCPF.isSelected() == true &&
				RadioButtonNome.isSelected() == false ||
				textConteudo.getText().equals("")) {
					String[] str = op.consultaCPF(textConteudo.getText(), "Usuarios.txt");
					if(str == null) {
						JOptionPane.showMessageDialog(null, "CPF do usuário não localizado na base de dados!", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
					}
					else {
						textResultado.setText(toString(str));
					}
					LimparTex();
		}
		else if(RadioButtonID.isSelected() == false &&
				RadioButtonCPF.isSelected() == false &&
				RadioButtonNome.isSelected() == true ||
				textConteudo.getText().equals("")) {
				ArrayList<String> str = op.consultaNome(textConteudo.getText(), "Usuarios.txt");
					if(str == null || str.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nome do usuário não localizado na base de dados!", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
					}
					else if(str.size() > 1){
							String fixa = "";
							for(int i = 0; i < str.size(); i++) fixa += (str.get(i) + "\n");
							str.set(0, fixa);
					}
					if(!str.isEmpty()) {
						textResultado.setText(str.get(0));
						LimparTex();
					}
		}
	}
	public void LimparTex() {
		textConteudo.setText(null);
		RadioButtonID.setSelected(false);
		RadioButtonCPF.setSelected(false);
		RadioButtonNome.setSelected(false);
	}
	public String toString(String[] str) {
		return ("Id: " + str[0] +  "\nNome: "+ str[1] + "\nTelefone: " + str[2] + "\nCPF: " + str[3] + "\nSexo: " + str[4]);
	}
}
