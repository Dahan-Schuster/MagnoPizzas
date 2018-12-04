package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Fornecedor;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroFornecedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFornecedor frame = new TelaCadastroFornecedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public TelaCadastroFornecedor() throws IOException, ParseException {
		setResizable(false);
		setTitle("Cadastrar Fornecedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 570);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informa\u00E7\u00F5es", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 270, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 28, 46, 14);
		panel.add(lblNome);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(12, 56, 46, 14);
		panel.add(lblCnpj);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 84, 70, 14);
		panel.add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 112, 46, 14);
		panel.add(lblEmail);
		
		txtNome = new JTextField();
		txtNome.setBounds(60, 25, 190, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
		maskCnpj.setPlaceholderCharacter('X');
		
		JFormattedTextField ftxtCnpj = new JFormattedTextField(maskCnpj);
		ftxtCnpj.setBounds(60, 53, 190, 20);
		panel.add(ftxtCnpj);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(80, 81, 125, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(60, 109, 190, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.addActionListener(new ActionListener() {
			
			// A��O DO BOT�O CADASTRAR
			public void actionPerformed(ActionEvent arg0) {
				
				// Verificando se todos os dados foram preenchidos
				if (txtNome.getText().trim().isEmpty() || ftxtCnpj.getText().contains("X") || txtTelefone.getText().contains("X") || txtEmail.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						// Cadastrando o novo fornecedor
						new Fornecedor(txtNome.getText(), ftxtCnpj.getText(), txtTelefone.getText(), txtEmail.getText()).cadastrar();
						
						JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com succeso!", "Cadastrpo", JOptionPane.INFORMATION_MESSAGE);
						// Limpando os campos e retornando o foco ao Nome
						txtNome.setText("");
						ftxtCnpj.setText("");
						txtTelefone.setText("");
						txtEmail.setText("");
						txtNome.requestFocus();
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnCadastrar.setBounds(10, 148, 110, 23);
		panel.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(151, 148, 110, 23);
		panel.add(btnCancelar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Fornecedores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 204, 270, 292);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		List listaFornecedores = new List();
		listaFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					try {
						int index = listaFornecedores.getSelectedIndex();
						
						new Fornecedor();
						StringTokenizer st = new StringTokenizer(Fornecedor.getFornecedores().get(index));
						String dados = Fornecedor.exibirDados(st.nextToken(";"));
						JOptionPane.showMessageDialog(null, dados, "Dados", JOptionPane.PLAIN_MESSAGE);
	
					}  catch (ArrayIndexOutOfBoundsException a) {
						JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		new Fornecedor();
		StringTokenizer st;
		for(String fornecedor : Fornecedor.getFornecedores()) {
			st = new StringTokenizer(fornecedor);
			listaFornecedores.add(st.nextToken(";") + " | " + st.nextToken(";")); // 11.222.333/4444-55 | Fornecedor Exemplo S/A
		}
		listaFornecedores.setBounds(10, 23, 250, 180);
		panel_1.add(listaFornecedores);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.LIGHT_GRAY);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaFornecedores.getSelectedIndex();
					
					new Fornecedor();
					StringTokenizer st = new StringTokenizer(Fornecedor.getFornecedores().get(index));
					ftxtCnpj.setText(st.nextToken(";"));
					txtNome.setText(st.nextToken(";"));
					txtTelefone.setText(st.nextToken(";"));
					txtEmail.setText(st.nextToken(";"));
					
					txtPesquisa.setText("");
				} catch (NullPointerException n) {
					JOptionPane.showMessageDialog(null, "null", "Erro", JOptionPane.INFORMATION_MESSAGE);
				}  catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(171, 258, 89, 23);
		panel_1.add(btnEditar);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(10, 209, 86, 23);
		panel_1.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JButton btnPesquisa = new JButton("");
		btnPesquisa.setBackground(Color.LIGHT_GRAY);
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando a exist�ncia de dados no campo Pesquisa
				if (txtPesquisa.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira uma chave de pesquisa.", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						new Fornecedor();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(int i = 0; i < Fornecedor.getFornecedores().size(); i++) {
						StringTokenizer st = new StringTokenizer(Fornecedor.getFornecedores().get(i));
						
						if (st.nextToken(";").contains(txtPesquisa.getText()) || st.nextToken(";").contains(txtPesquisa.getText())) {
							listaFornecedores.select(i);
							break;
						}
					}
				}
			}
		});
		btnPesquisa.setBounds(106, 209, 33, 23);
		panel_1.add(btnPesquisa);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(Color.LIGHT_GRAY);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaFornecedores.getSelectedIndex();
					
					new Fornecedor();
					StringTokenizer st = new StringTokenizer(Fornecedor.getFornecedores().get(index));
					Fornecedor.remover(st.nextToken(";"));
					
					txtPesquisa.setText("");
				} catch (NullPointerException n) {
					JOptionPane.showMessageDialog(null, "null", "Erro", JOptionPane.INFORMATION_MESSAGE);
				}  catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemover.setBounds(7, 258, 110, 23);
		panel_1.add(btnRemover);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaFornecedores.removeAll();
				try {
					new Fornecedor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String fornecedor : Fornecedor.getFornecedores()) {
					StringTokenizer st = new StringTokenizer(fornecedor);
					listaFornecedores.add(st.nextToken(";") + " | " + st.nextToken(";")); // 11.222.333/4444-55 | Fornecedor Exemplo S/A
				}
			}
		});
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuBar.add(mntmAtualizar);
	}
}
