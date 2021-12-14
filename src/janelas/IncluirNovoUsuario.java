package janelas;

import ClasseDeDados.Usuario;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class IncluirNovoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncluirNovoUsuario frame = new IncluirNovoUsuario();
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
	JLabel LabelNome;
	JLabel lblCpf;
	JLabel lblTelefone;
	JRadioButton RadioButtonMasculino;
	JRadioButton RadioButtonFeminino;
	public IncluirNovoUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IncluirNovoUsuario.class.getResource("/Img/AdcUsuario.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false); 
		setTitle("Cadastrar cliente");
		setBounds(100, 100, 272, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LabelNome = new JLabel("Nome completo*");
		LabelNome.setBounds(10, 10, 273, 14);
		contentPane.add(LabelNome);
		
		textNome = new JTextField();
		textNome.setBounds(10, 25, 240, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(10, 50, 110, 14);
		contentPane.add(lblCpf);
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		textCpf.setBounds(10, 65, 110, 20);
		contentPane.add(textCpf);
		
		lblTelefone = new JLabel("Telefone*");
		lblTelefone.setBounds(140, 50, 110, 14);
		contentPane.add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setColumns(10);
		textTelefone.setBounds(140, 65, 110, 20);
		contentPane.add(textTelefone);
		
		RadioButtonMasculino = new JRadioButton("Masculino");
		RadioButtonMasculino.setBounds(45, 90, 83, 23);
		contentPane.add(RadioButtonMasculino);
		
		JLabel LabelSexo = new JLabel("Sexo*");
		LabelSexo.setBounds(10, 90, 46, 23);
		contentPane.add(LabelSexo);
		
		RadioButtonFeminino = new JRadioButton("Feminino");
		RadioButtonFeminino.setBounds(130, 90, 100, 23);
		contentPane.add(RadioButtonFeminino);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(15, 120, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidaCampos();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(145, 120, 100, 30);
		contentPane.add(btnCadastrar);	
	}
	
	public void ValidaCampos() throws IOException{
		char sexo = 0;
		if(textNome.getText().equals("") ||
				textCpf.getText().equals("") ||
				textTelefone.getText().equals("") ||
				RadioButtonFeminino.isSelected() == false &&
				RadioButtonMasculino.isSelected() == false
					) {
					JOptionPane.showMessageDialog(null, "Campo obrigatório está vazio", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else if(textNome.getText().equals("") ||
				textCpf.getText().equals("") ||
				textTelefone.getText().equals("") ||
				RadioButtonFeminino.isSelected() == true &&
				RadioButtonMasculino.isSelected() == true) {
					JOptionPane.showMessageDialog(null, "Mais de um campo 'sexo' selecionado", "Aviso de Erro!", JOptionPane.ERROR_MESSAGE);
		}else if(textNome.getText().equals("") ||
				textCpf.getText().equals("") ||
				textTelefone.getText().equals("") ||
				RadioButtonFeminino.isSelected() == true &&
				RadioButtonMasculino.isSelected() == false) {
					sexo = 'F';
					Usuario us = new Usuario(textNome.getText(), textTelefone.getText(), textCpf.getText(), sexo);
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
					LimparTex();
		}else if(textNome.getText().equals("") ||
				textCpf.getText().equals("") ||
				textTelefone.getText().equals("") ||
				RadioButtonFeminino.isSelected() == false &&
				RadioButtonMasculino.isSelected() == true) {
					sexo = 'M';
					Usuario us = new Usuario(textNome.getText(), textTelefone.getText(), textCpf.getText(), sexo);
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Aviso!", JOptionPane.PLAIN_MESSAGE);
					LimparTex();
		}
	}
	
	public void LimparTex() {
		textNome.setText(null);
		textCpf.setText(null);
		textTelefone.setText(null);
		RadioButtonMasculino.setSelected(false);;
		RadioButtonFeminino.setSelected(false);
	}
}
