package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Funcionario;

import java.text.ParseException;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Choice;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.List;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField txtTelefone;
	private JFormattedTextField txtCPF;
	private JLabel label_3;
	private JTextField txtPesquisa;
	private JButton btnCancelar;
	private JMenuBar menuBar;
	private JMenuItem mntmAtualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public TelaCadastroFuncionario() throws ParseException, IOException {
		setResizable(false);
		setTitle("Cadastrar Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 587);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "INFORMA\u00C7\u00D5ES",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 312, 208);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(59, 28, 242, 20);
		panel.add(txtNome);

		label = new JLabel("NOME:");
		label.setBounds(10, 31, 46, 14);
		panel.add(label);

		label_1 = new JLabel("CPF:");
		label_1.setBounds(10, 59, 46, 17);
		panel.add(label_1);

		label_2 = new JLabel("TELEFONE:");
		label_2.setBounds(10, 87, 80, 14);
		panel.add(label_2);

		MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
		maskTelefone.setPlaceholderCharacter('X');

		txtTelefone = new JFormattedTextField(maskTelefone);
		txtTelefone.setBounds(90, 84, 110, 20);
		panel.add(txtTelefone);

		MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
		maskCpf.setPlaceholderCharacter('X');

		txtCPF = new JFormattedTextField(maskCpf);
		txtCPF.setBounds(90, 57, 110, 20);
		panel.add(txtCPF);

		label_3 = new JLabel("FUN\u00C7\u00C3O:");
		label_3.setBounds(10, 118, 65, 14);
		panel.add(label_3);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setBounds(10, 173, 115, 23);
		panel.add(btnCadastrar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBackground(Color.LIGHT_GRAY);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCancelar.setBounds(196, 173, 105, 23);
		panel.add(btnCancelar);

		Choice BOXFuncao = new Choice();
		BOXFuncao.add("ATENDENTE");
		BOXFuncao.add("GARÇOM");
		BOXFuncao.add("ENTREGADOR");
		BOXFuncao.add("PIZZAIOLO");
		BOXFuncao.setBounds(90, 118, 117, 20);
		panel.add(BOXFuncao);

		// A��O DO BOT�O CADASTRAR
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Verificando se todos os dados foram preenchidos
				if (txtNome.getText().trim().isEmpty() || txtCPF.getText().contains("X")
						|| txtTelefone.getText().contains("X")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					// Cadastrando o novo fornecedor
					try {
						new Funcionario(txtNome.getText(), txtCPF.getText(), txtTelefone.getText(),
								(String) BOXFuncao.getSelectedItem()).cadastrar();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "Funcionario cadastrado com succeso!", "Cadastro",
							JOptionPane.INFORMATION_MESSAGE);
					// Limpando os campos e retornando o foco ao Nome
					txtNome.setText("");
					txtCPF.setText("");
					txtTelefone.setText("");
					BOXFuncao.select(0);
					txtNome.requestFocus();

				}
			}

		});

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "FUNCION\u00C1RIOS",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 230, 313, 190);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		List listaFuncionarios = new List();
		listaFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					
					try {
						int index = listaFuncionarios.getSelectedIndex();

						new Funcionario();
						StringTokenizer st = new StringTokenizer(Funcionario.getFuncionarios().get(index));
						String dados = Funcionario.exibirDados(st.nextToken(";"));
						JOptionPane.showMessageDialog(null, dados, "Dados", JOptionPane.PLAIN_MESSAGE);

						txtPesquisa.setText("");
					} catch (ArrayIndexOutOfBoundsException a) {
						JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		new Funcionario();
		StringTokenizer st;
		for (String funcionario : Funcionario.getFuncionarios()) {
			st = new StringTokenizer(funcionario);
			listaFuncionarios.add(st.nextToken(";") + " | " + st.nextToken(";")); // 123.456.789-00 | Jubileu Marconi
		}

		listaFuncionarios.setBounds(10, 26, 293, 154);
		panel_1.add(listaFuncionarios);

		txtPesquisa = new JTextField();
		txtPesquisa.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		txtPesquisa.setBackground(SystemColor.text);
		txtPesquisa.setBounds(10, 442, 143, 20);
		contentPane.add(txtPesquisa);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBackground(Color.LIGHT_GRAY);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaFuncionarios.getSelectedIndex();

					new Funcionario();
					StringTokenizer st = new StringTokenizer(Funcionario.getFuncionarios().get(index));
					txtCPF.setText(st.nextToken(";"));
					txtNome.setText(st.nextToken(";"));
					txtTelefone.setText(st.nextToken(";"));

					String funcao = st.nextToken(";");
					for (int i = 0; i < BOXFuncao.getItemCount(); i++) {
						if (BOXFuncao.getItem(i).equals(funcao)) {
							BOXFuncao.select(i);
							break;
						}
					}

					txtPesquisa.setText("");
				} catch (NullPointerException n) {
					JOptionPane.showMessageDialog(null, "Nenhum funcion�rio selecionado", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum funcion�rio selecionado", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {

				}
			}
		});

		btnEditar.setBounds(10, 485, 89, 23);
		contentPane.add(btnEditar);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(10, 209, 86, 23);
		panel_1.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton bntPesquisa = new JButton("");
		bntPesquisa.setBackground(Color.LIGHT_GRAY);

		JButton btnPesquisa = new JButton("");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando a exist�ncia de dados no campo Pesquisa
				if (txtPesquisa.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira uma chave de pesquisa.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (int i = 0; i < Funcionario.getFuncionarios().size(); i++) {
						if (Funcionario.getFuncionarios().get(i).contains(txtPesquisa.getText())) {
							listaFuncionarios.select(i);
							break;
						}
					}
				}
			}
		});
		bntPesquisa.setBounds(180, 442, 33, 20);
		contentPane.add(bntPesquisa);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBackground(Color.LIGHT_GRAY);

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaFuncionarios.getSelectedIndex();

					new Funcionario();
					StringTokenizer st = new StringTokenizer(Funcionario.getFuncionarios().get(index));
					Funcionario.remover(st.nextToken(";"));

					txtPesquisa.setText("");
				} catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnRemover.setBounds(209, 485, 113, 23);
		contentPane.add(btnRemover);

		mntmAtualizar = new JMenuItem("Atualizar");

		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaFuncionarios.removeAll();
				try {
					new Funcionario();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (String funcionario : Funcionario.getFuncionarios()) {
					StringTokenizer st = new StringTokenizer(funcionario);
					listaFuncionarios.add(st.nextToken(";") + " | " + st.nextToken(";")); 
				}
			}
		});
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuBar.add(mntmAtualizar);

	}
}
