package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Item;
import pizzaria.Receita;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.InputEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarReceita extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastrarReceita frame = new TelaCadastrarReceita();
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
	public TelaCadastrarReceita() throws ParseException, IOException {
		setResizable(false);
		setTitle("Cadastrar Receita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 447);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informa\u00E7\u00F5es", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 288, 361);
		contentPane.add(panel);

		Choice choiceItens = new Choice();
		new Item();
		for (String item : Item.getItens()) {
			StringTokenizer st = new StringTokenizer(item);
			choiceItens.add(st.nextToken(";") + " | " + st.nextToken(";"));
		}
		choiceItens.setBounds(52, 198, 198, 20);
		panel.add(choiceItens);

		List listaItens = new List();
		listaItens.setBounds(52, 223, 198, 85);
		panel.add(listaItens);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 28, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(60, 25, 216, 20);
		panel.add(txtNome);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(160, 148, 110, 23);
		panel.add(btnCancelar);


		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Pre\u00E7os",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel2.setBounds(10, 53, 266, 84);
		panel.add(panel2);
		panel2.setLayout(null);

		JLabel lblP = new JLabel("P");
		lblP.setBounds(10, 23, 25, 14);
		panel2.add(lblP);

		JLabel lblM = new JLabel("M");
		lblM.setBounds(61, 23, 25, 14);
		panel2.add(lblM);

		JLabel lblG = new JLabel("G");
		lblG.setBounds(111, 23, 25, 14);
		panel2.add(lblG);

		JLabel lblF = new JLabel("F");
		lblF.setBounds(166, 23, 25, 14);
		panel2.add(lblF);

		JLabel lblGf = new JLabel("GF");
		lblGf.setBounds(216, 23, 25, 14);
		panel2.add(lblGf);

		MaskFormatter maskPreco = new MaskFormatter("##.##");
		maskPreco.setPlaceholderCharacter('0');

		JFormattedTextField ftxtP = new JFormattedTextField(maskPreco);
		ftxtP.setBounds(10, 37, 40, 20);
		panel2.add(ftxtP);

		JFormattedTextField ftxtM = new JFormattedTextField(maskPreco);
		ftxtM.setBounds(61, 37, 40, 20);
		panel2.add(ftxtM);

		JFormattedTextField ftxtG = new JFormattedTextField(maskPreco);
		ftxtG.setBounds(111, 37, 40, 20);
		panel2.add(ftxtG);

		JFormattedTextField ftxtF = new JFormattedTextField(maskPreco);
		ftxtF.setBounds(166, 37, 40, 20);
		panel2.add(ftxtF);

		JFormattedTextField ftxtGF = new JFormattedTextField(maskPreco);
		ftxtGF.setBounds(216, 37, 40, 20);
		panel2.add(ftxtGF);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Receitas", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(308, 11, 288, 361);
		contentPane.add(panel_1);

		List listaReceitas = new List();
		listaReceitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					
					try {
						int index = listaReceitas.getSelectedIndex();

						new Receita();
						StringTokenizer st = new StringTokenizer(Receita.getReceitas().get(index));
						String dados = Receita.exibirDados(st.nextToken(";"));
						JOptionPane.showMessageDialog(null, dados, "Dados", JOptionPane.PLAIN_MESSAGE);
						
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
		new Receita();
		for (String receita : Receita.getReceitas()) {
			StringTokenizer st = new StringTokenizer(receita);
			listaReceitas.add(st.nextToken(";") + " | " + st.nextToken(";")); // 0001 | Chicken Little
		}
		listaReceitas.setBounds(10, 23, 259, 264);
		panel_1.add(listaReceitas);

		JButton button_2 = new JButton("Editar");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaReceitas.getSelectedIndex();

					new Receita();
					StringTokenizer st = new StringTokenizer(Receita.getReceitas().get(index));
					st.nextToken(";");
					txtNome.setText(st.nextToken(";"));
					st.nextToken(";");
					ftxtP.setText(st.nextToken(";"));
					ftxtM.setText(st.nextToken(";"));
					ftxtG.setText(st.nextToken(";"));
					ftxtF.setText(st.nextToken(";"));
					ftxtGF.setText(st.nextToken(";"));

					listaItens.removeAll();
					StringTokenizer st2;
					while (st.hasMoreTokens()) {
						String id = st.nextToken(";");
						new Item();
						for (String item2 : Item.getItens()) {
							st2 = new StringTokenizer(item2);
							String item2ID = st2.nextToken(";"),
									item2Nome = st2.nextToken(";");
							
							if (id.equals(item2ID)) {
								listaItens.add(item2ID+" | "+item2Nome);	
							}
						}
					}

					txtPesquisa.setText("");
				} catch (NullPointerException n) {
					JOptionPane.showMessageDialog(null, "null", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum fornecedor selecionado", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		button_2.setBounds(189, 327, 89, 23);
		panel_1.add(button_2);

		txtPesquisa = new JTextField();
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(10, 293, 86, 23);
		panel_1.add(txtPesquisa);

		JButton button_3 = new JButton("");
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando a exist�ncia de dados no campo Pesquisa
				if (txtPesquisa.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira uma chave de pesquisa.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						new Receita();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i = 0; i < Receita.getReceitas().size(); i++) {
						StringTokenizer st = new StringTokenizer(Receita.getReceitas().get(i));

						if (st.nextToken(";").contains(txtPesquisa.getText())
								|| st.nextToken(";").contains(txtPesquisa.getText())) {
							listaReceitas.select(i);
							break;
						}
					}
				}
			}
		});
		button_3.setBounds(106, 293, 33, 23);
		panel_1.add(button_3);

		JButton button_4 = new JButton("Remover");
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaReceitas.getSelectedIndex();

					new Receita();
					StringTokenizer st = new StringTokenizer(Receita.getReceitas().get(index));
					Receita.remover(st.nextToken(";"));

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
		button_4.setBounds(7, 327, 110, 23);
		panel_1.add(button_4);

		JLabel lblItens = new JLabel("Itens:");
		lblItens.setBounds(10, 201, 45, 14);
		panel.add(lblItens);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().trim().isEmpty() || Float.parseFloat(ftxtP.getText()) == (float) 0.0
						|| Float.parseFloat(ftxtM.getText()) == (float) 0.0
						|| Float.parseFloat(ftxtG.getText()) == (float) 0.0
						|| Float.parseFloat(ftxtF.getText()) == (float) 0.0
						|| Float.parseFloat(ftxtGF.getText()) == (float) 0.0 || listaItens.getItemCount() == 0) {

					JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);

					txtNome.requestFocus();
					txtNome.selectAll();

				} else {
					try {
						ArrayList<String> itens = new ArrayList<>();
						
						for (int i = 0; i < listaItens.getItemCount(); i++) {
							itens.add(listaItens.getItem(i));
						}
						new Receita(txtNome.getText(), itens, Float.parseFloat(ftxtP.getText()),
								Float.parseFloat(ftxtM.getText()), Float.parseFloat(ftxtG.getText()),
								Float.parseFloat(ftxtF.getText()), Float.parseFloat(ftxtGF.getText())).cadastrar();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "Item cadastrado com succeso!", "Cadastro",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpando os campos e retornando o foco ao Nome
					txtNome.setText("");
					ftxtP.setText("");
					ftxtM.setText("");
					ftxtG.setText("");
					ftxtF.setText("");
					ftxtGF.setText("");
					listaItens.removeAll();
					choiceItens.select(0);
					txtNome.requestFocus();
				}

			}
		});
		btnCadastrar.setBounds(10, 148, 110, 23);
		panel.add(btnCadastrar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(Color.LIGHT_GRAY);
		btnAdicionar.setBounds(32, 321, 110, 23);
		panel.add(btnAdicionar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(Color.LIGHT_GRAY);
		btnRemover.setBounds(161, 321, 110, 23);
		panel.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaItens.remove(listaItens.getSelectedIndex());
				} catch (ArrayIndexOutOfBoundsException n) {
					JOptionPane.showMessageDialog(null, "Selecione um item da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean adicionado = false;
					for (int i = 0; i < listaItens.getItemCount(); i++) {
						if (listaItens.getItem(i).equals(choiceItens.getSelectedItem())) {
							JOptionPane.showMessageDialog(null, "Item j� adicionado", "Erro",
									JOptionPane.INFORMATION_MESSAGE);
							adicionado = true;
							break;
						}
					}
					if (!adicionado) {
						listaItens.add(choiceItens.getSelectedItem());
					}
				} catch (ArrayIndexOutOfBoundsException n) {
					JOptionPane.showMessageDialog(null, "Selecione um item da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaReceitas.removeAll();
				try {
					new Receita();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (String receita : Receita.getReceitas()) {
					StringTokenizer st = new StringTokenizer(receita);
					listaReceitas.add(st.nextToken(";") + " | " + st.nextToken(";")); // 0001 | Chicken Little
				}
			}
		});
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuBar.add(mntmAtualizar);
	}
}
