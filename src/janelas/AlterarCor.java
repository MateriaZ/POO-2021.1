package janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarCor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarCor frame = new AlterarCor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param frame 
	 */
	public AlterarCor(InterfaceMenuPrincipal frame) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarCor.class.getResource("/Img/wheel (1).png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 389, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Alterar cor");
		
		JLabel lblCor = new JLabel("Escolha uma cor");
		lblCor.setBounds(10, 11, 348, 14);
		contentPane.add(lblCor);
		
		JButton btnPadrao = new JButton("Padr\u00E3o");
		btnPadrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreVisualizacao.setBackground(new Color(240, 240, 240));
			}
		});
		btnPadrao.setBounds(10, 30, 100, 30);
		contentPane.add(btnPadrao);
		
		JButton btnBranco = new JButton("Branco");
		btnBranco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreVisualizacao.setBackground(Color.WHITE);
			}
		});
		btnBranco.setBounds(135, 30, 100, 30);
		contentPane.add(btnBranco);
		
		JButton btnAzul = new JButton("Azul");
		btnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreVisualizacao.setBackground(new Color(204, 255, 255));
			}
		});
		btnAzul.setBounds(258, 30, 100, 30);
		contentPane.add(btnAzul);
		
		PreVisualizacao = new JPanel();
		PreVisualizacao.setBackground(new Color(204, 255, 255));
		PreVisualizacao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PreVisualizacao.setBackground(Color.WHITE);
		PreVisualizacao.setBounds(10, 94, 348, 127);
		contentPane.add(PreVisualizacao);
		
		JLabel lblPreVisualizacao = new JLabel("Pr\u00E9 vizualiza\u00E7\u00E3o");
		lblPreVisualizacao.setBounds(10, 76, 348, 14);
		contentPane.add(lblPreVisualizacao);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(71, 243, 100, 30);
		contentPane.add(btnVoltar);
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setBackground(PreVisualizacao.getBackground());
				JOptionPane.showMessageDialog(null, "Cor alterada com sucesso\n" + "Reinice as janelas que tiverm abertas", "Aviso!", JOptionPane.PLAIN_MESSAGE);
				frame.AtualizarCor(PreVisualizacao.getBackground());
				
			}
		});
		btnAplicar.setBounds(214, 243, 100, 30);
		contentPane.add(btnAplicar);
		
		JLabel lblNewLabel = new JLabel("_________________________________________________");
		lblNewLabel.setBounds(10, 251, 363, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("_________________________________________________");
		lblNewLabel_1.setBounds(10, 243, 348, 14);
		contentPane.add(lblNewLabel_1);	
	}
	
	private JPanel PreVisualizacao;
}
