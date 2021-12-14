package janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class InterfaceMenuPrincipal extends JFrame {

	/**
	 * Launch the application.
	 */
	static InterfaceMenuPrincipal frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceMenuPrincipal();
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
	public InterfaceMenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfaceMenuPrincipal.class.getResource("/Img/bank (1).png")));
		setTitle("Sistema Banc\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu MenuCadastros = new JMenu("Cadastros");
		MenuCadastros.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(MenuCadastros);
		
		JMenu subMenuUsuario = new JMenu("Usu\u00E1rio");
		subMenuUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/usuario-verificado (1).png")));
		MenuCadastros.add(subMenuUsuario);
		
		JMenuItem MenuItemIncluirNovoUsuario = new JMenuItem("Incluir novo usu\u00E1rio");
		MenuItemIncluirNovoUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/AdcUsuario.png")));
		MenuItemIncluirNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemIncluirNovoUsuarioItemActionPerformed(evt);
            }
        });
		subMenuUsuario.add(MenuItemIncluirNovoUsuario);
		
		JMenuItem MenuItemExcluirUsuario = new JMenuItem("Excluir usu\u00E1rio");
		MenuItemExcluirUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ExcluirUsuario.png")));
		MenuItemExcluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemExcluirUsuarioItemActionPerformed(evt);
            }
        });
		subMenuUsuario.add(MenuItemExcluirUsuario);
		
		JMenuItem MenuItemAlterarUsuario = new JMenuItem("Alterar usu\u00E1rio");
		MenuItemAlterarUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/UsuarioAltera.png")));
		MenuItemAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					MenuItemAlterarUsuarioItemActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
		subMenuUsuario.add(MenuItemAlterarUsuario);
		
		JMenu subMenuEmpresa = new JMenu("Empresa");
		subMenuEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/companhia (1).png")));
		MenuCadastros.add(subMenuEmpresa);
		
		JMenuItem MenuItemIncluirNovaEmpresa = new JMenuItem("Incluir nova empresa");
		MenuItemIncluirNovaEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/AdcEmpresa.png")));
		MenuItemIncluirNovaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					MenuItemIncluirNovaEmpresaItemActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
		subMenuEmpresa.add(MenuItemIncluirNovaEmpresa);
		
		JMenuItem MenuItemExcluirEmpresa = new JMenuItem("Excluir empresa");
		MenuItemExcluirEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ExcluirEmpresa.png")));
		MenuItemExcluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemExcluirEmpresaItemActionPerformed(evt);
            }
        });
		subMenuEmpresa.add(MenuItemExcluirEmpresa);
		
		JMenuItem MenuItemAlterarEmpresa = new JMenuItem("Alterar empresa");
		MenuItemAlterarEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/AlteraEmpresa.png")));
		MenuItemAlterarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					MenuItemAlterarEmpresaItemActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		subMenuEmpresa.add(MenuItemAlterarEmpresa);
		
		JMenu subMenuConta = new JMenu("Conta");
		subMenuConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/conta (1).png")));
		MenuCadastros.add(subMenuConta);
		
		JMenuItem MenuItemIncluirNovaConta = new JMenuItem("Incluir nova conta");
		MenuItemIncluirNovaConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/AdcConta.png")));
		MenuItemIncluirNovaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					MenuItemIncluirNovaContaItemActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
		subMenuConta.add(MenuItemIncluirNovaConta);
		
		JMenuItem MenuItemExluirConta = new JMenuItem("Excluir conta");
		MenuItemExluirConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ExcluirConta.png")));
		MenuItemExluirConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemExluirContaItemActionPerformed(evt);
            }
        });
		subMenuConta.add(MenuItemExluirConta);
		
		JMenuItem MenuItemAlterarConta = new JMenuItem("Alterar conta");
		MenuItemAlterarConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/AlterarConta.png")));
		MenuItemAlterarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemAlterarContaItemActionPerformed(evt);
            }
        });
		subMenuConta.add(MenuItemAlterarConta);
		
		JMenu mnNewMenu = new JMenu("Sair");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/exit (2).png")));
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 System.exit(0);
			}
		});
		
		JMenu MenuMovimento = new JMenu("Movimento");
		MenuMovimento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(MenuMovimento);
		
		JMenuItem MenuItemOperacoesBancarias = new JMenuItem("Opera\u00E7\u00F5es banc\u00E1rias");
		MenuItemOperacoesBancarias.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/execucao (1).png")));
		MenuItemOperacoesBancarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemOperacoesBancariasItemActionPerformed(evt);
            }
        });
		MenuMovimento.add(MenuItemOperacoesBancarias);
		
		JMenuItem MenuItemImportarArquivos = new JMenuItem("Importar Arquivos");
		MenuItemImportarArquivos.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/salvar-arquivo (1).png")));
		MenuItemImportarArquivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemImportarArquivosItemActionPerformed(evt);
            }
        });
		MenuMovimento.add(MenuItemImportarArquivos);

		
		JMenu MenuConsulta = new JMenu("Consulta");
		MenuConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(MenuConsulta);
		
		JMenu subMenuUsuario_1 = new JMenu("Usu\u00E1rio");
		subMenuUsuario_1.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/usuario-verificado (1).png")));
		MenuConsulta.add(subMenuUsuario_1);
		
		JMenuItem MenuItemBuscarUsuario = new JMenuItem("Buscar usu\u00E1rio");
		MenuItemBuscarUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/BuscaUsuario.png")));
		MenuItemBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemBuscarUsuarioItemActionPerformed(evt);
            }
        });
		subMenuUsuario_1.add(MenuItemBuscarUsuario);
		
		JMenuItem MenuItemListarUsuario = new JMenuItem("Listar usu\u00E1rio");
		MenuItemListarUsuario.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ListaUsuario.png")));
		MenuItemListarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemListarUsuarioItemActionPerformed(evt);
            }
        });
		subMenuUsuario_1.add(MenuItemListarUsuario);
		
		JMenu subMenuEmpresa_1 = new JMenu("Empresa");
		subMenuEmpresa_1.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/companhia (1).png")));
		MenuConsulta.add(subMenuEmpresa_1);
		
		JMenuItem MenuItemBuscarEmpresa = new JMenuItem("Buscar empresa");
		MenuItemBuscarEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/BuscaEmpresa.png")));
		MenuItemBuscarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemBuscarEmpresaItemActionPerformed(evt);
            }
        });
		subMenuEmpresa_1.add(MenuItemBuscarEmpresa);
		
		JMenuItem MenuItemListarEmpresa = new JMenuItem("Listar empresa");
		MenuItemListarEmpresa.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ListaEmpresa.png")));
		MenuItemListarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemListarEmpresaItemActionPerformed(evt);
            }
        });
		subMenuEmpresa_1.add(MenuItemListarEmpresa);
		
		JMenu subMenuConta_1 = new JMenu("Conta");
		subMenuConta_1.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/conta (1).png")));
		MenuConsulta.add(subMenuConta_1);
		
		JMenuItem MenuItemBuscarConta = new JMenuItem("Buscar conta");
		MenuItemBuscarConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/BuscaConta.png")));
		MenuItemBuscarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemBuscarContaItemActionPerformed(evt);
            }
        });
		subMenuConta_1.add(MenuItemBuscarConta);
		
		JMenuItem MenuItemListarConta = new JMenuItem("Listar conta");
		MenuItemListarConta.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/ListaConta.png")));
		MenuItemListarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemListarContaItemActionPerformed(evt);
            }
        });
		subMenuConta_1.add(MenuItemListarConta);
		
		JMenu MenuApoio = new JMenu("Apoio");
		MenuApoio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(MenuApoio);
		
		JMenuItem MenuItemAjuda = new JMenuItem("Ajuda");
		MenuItemAjuda.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/help (1).png")));
		MenuItemAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemAjudaItemActionPerformed(evt);
            }
        });
		MenuApoio.add(MenuItemAjuda);
		
		JMenuItem MenuItemAlterarCor = new JMenuItem("Alterar cor");
		MenuItemAlterarCor.setIcon(new ImageIcon(InterfaceMenuPrincipal.class.getResource("/Img/wheel (1).png")));
		MenuItemAlterarCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	MenuItemAlterarCorItemActionPerformed(evt);
            }
        });
		MenuApoio.add(MenuItemAlterarCor);
		menuBar.add(mnNewMenu);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Sistema Banc\u00E1rio");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Vinicius, Rafael e Guilherme");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.ITALIC, 11));
	}
	
	private void MenuItemIncluirNovoUsuarioItemActionPerformed(java.awt.event.ActionEvent evt) {
		IncluirNovoUsuario NovoUsuario = new IncluirNovoUsuario();
		NovoUsuario.getContentPane().setBackground(getContentPane().getBackground());
		NovoUsuario.setVisible(true);
    }
	
	private void MenuItemImportarArquivosItemActionPerformed(java.awt.event.ActionEvent evt) {
		ImportarArquivos ImportarArquivos = new ImportarArquivos();
		ImportarArquivos.getContentPane().setBackground(getContentPane().getBackground());
		ImportarArquivos.setVisible(true);
	}
	
	private void MenuItemExcluirUsuarioItemActionPerformed(java.awt.event.ActionEvent evt) {
		ExcluirUsuario ExcluirUsuario = new ExcluirUsuario();
		ExcluirUsuario.getContentPane().setBackground(getContentPane().getBackground());
		ExcluirUsuario.setVisible(true);
	}
	
	private void MenuItemAlterarUsuarioItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		AlterarUsuario AlterarUsuario = new AlterarUsuario();
		AlterarUsuario.getContentPane().setBackground(getContentPane().getBackground());
		AlterarUsuario.setVisible(true);
	}
	private void MenuItemIncluirNovaEmpresaItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException  {
		IncluirNovaEmpresa IncluirNovaEmpresa = new IncluirNovaEmpresa();
		IncluirNovaEmpresa.getContentPane().setBackground(getContentPane().getBackground());
		IncluirNovaEmpresa.setVisible(true);
	}
	private void MenuItemExcluirEmpresaItemActionPerformed(java.awt.event.ActionEvent evt) {
		ExcluirEmpresa ExcluirEmpresa = new ExcluirEmpresa();
		ExcluirEmpresa.getContentPane().setBackground(getContentPane().getBackground());
		ExcluirEmpresa.setVisible(true);
	}
	private void MenuItemAlterarEmpresaItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		AlterarEmpresa AlterarEmpresa = new AlterarEmpresa();
		AlterarEmpresa.getContentPane().setBackground(getContentPane().getBackground());
		AlterarEmpresa.setVisible(true);
	}
	//fasfd
	private void MenuItemIncluirNovaContaItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		IncluirNovaConta incluirNovaConta = new IncluirNovaConta();
		setBackground(getContentPane().getBackground());
		incluirNovaConta.setVisible(true);
	}
	private void MenuItemExluirContaItemActionPerformed(java.awt.event.ActionEvent evt) {
		ExcluirConta ExcluirConta = null;
		try {
			ExcluirConta = new ExcluirConta();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExcluirConta.getContentPane().setBackground(getContentPane().getBackground());
		ExcluirConta.setVisible(true);
	}
	private void MenuItemAlterarContaItemActionPerformed(java.awt.event.ActionEvent evt) {
		AlterarConta AlterarConta = new AlterarConta();
		AlterarConta.getContentPane().setBackground(getContentPane().getBackground());
		AlterarConta.setVisible(true);
	}
	private void MenuItemOperacoesBancariasItemActionPerformed(java.awt.event.ActionEvent evt) {
		OperacoesBancarias OperacoesBancarias = new OperacoesBancarias();
		OperacoesBancarias.getContentPane().setBackground(getContentPane().getBackground());
		OperacoesBancarias.setVisible(true);
	}
	private void MenuItemBuscarUsuarioItemActionPerformed(java.awt.event.ActionEvent evt) {
		BuscarUsuario BuscarUsuario = new BuscarUsuario();
		BuscarUsuario.getContentPane().setBackground(getContentPane().getBackground());
		BuscarUsuario.setVisible(true);
	}
	
	private void MenuItemListarUsuarioItemActionPerformed(java.awt.event.ActionEvent evt) {
		ListarUsuario ListarUsuario = new ListarUsuario();
		ListarUsuario.getContentPane().setBackground(getContentPane().getBackground());
		ListarUsuario.setVisible(true);
	}
	private void MenuItemBuscarEmpresaItemActionPerformed(java.awt.event.ActionEvent evt) {
		BuscarEmpresa BuscarEmpresa = new BuscarEmpresa();
		BuscarEmpresa.getContentPane().setBackground(getContentPane().getBackground());
		BuscarEmpresa.setVisible(true);
	}
	private void MenuItemListarEmpresaItemActionPerformed(java.awt.event.ActionEvent evt) {
		ListarEmpresa ListarEmpresa = new ListarEmpresa();
		ListarEmpresa.getContentPane().setBackground(getContentPane().getBackground());
		ListarEmpresa.setVisible(true);
	}
	private void MenuItemBuscarContaItemActionPerformed(java.awt.event.ActionEvent evt) {
		BuscarConta BuscarConta = new BuscarConta();
		BuscarConta.getContentPane().setBackground(getContentPane().getBackground());
		BuscarConta.setVisible(true);
	}
	private void MenuItemListarContaItemActionPerformed(java.awt.event.ActionEvent evt) {
		ListarConta ListarConta = new ListarConta();
		ListarConta.getContentPane().setBackground(getContentPane().getBackground());
		ListarConta.setVisible(true);
	}
	private void MenuItemAjudaItemActionPerformed(java.awt.event.ActionEvent evt) {
		Ajuda Ajuda = new Ajuda();
		Ajuda.getContentPane().setBackground(getContentPane().getBackground());
		Ajuda.setVisible(true);
	}
	private void MenuItemAlterarCorItemActionPerformed(java.awt.event.ActionEvent evt) {
		AlterarCor AlteraCor = new AlterarCor(frame);
		AlteraCor.setVisible(true);
	}

	void AtualizarCor(Color cor){
		getContentPane().setBackground(cor);
	}
}
