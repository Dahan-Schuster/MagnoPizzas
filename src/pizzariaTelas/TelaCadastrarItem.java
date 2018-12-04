package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Fornecedor;
import pizzaria.Item;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.List;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarItem extends JFrame {

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
					TelaCadastrarItem frame = new TelaCadastrarItem();
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
	public TelaCadastrarItem() throws ParseException, IOException {
		setResizable(false);
		setTitle("Cadastrar Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 570);
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
		panel.setBounds(10, 11, 270, 182);
		contentPane.add(panel);

		JLabel label = new JLabel("Nome:");
		label.setBounds(12, 28, 46, 14);
		panel.add(label);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(12, 56, 90, 14);
		panel.add(lblFornecedor);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(12, 84, 90, 14);
		panel.add(lblQuantidade);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(60, 25, 190, 20);
		panel.add(txtNome);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(150, 148, 110, 23);
		panel.add(btnCancelar);

		MaskFormatter maskQte = new MaskFormatter("###");
		maskQte.setPlaceholderCharacter('0');

		JFormattedTextField ftxtQte = new JFormattedTextField(maskQte);
		ftxtQte.setBounds(110, 81, 74, 20);
		panel.add(ftxtQte);

		Choice choiceFornecedor = new Choice();
		new Fornecedor();
		for (String fornecedor : Fornecedor.getFornecedores()) {
			StringTokenizer st = new StringTokenizer(fornecedor);
			st.nextToken(";");
			choiceFornecedor.add(st.nextToken(";"));
		}
		choiceFornecedor.setBounds(110, 56, 140, 20);
		panel.add(choiceFornecedor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Itens", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 204, 270, 292);
		contentPane.add(panel_1);

		List listaItens = new List();
		listaItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();

					try {
						int index = listaItens.getSelectedIndex();

						new Item();
						StringTokenizer st = new StringTokenizer(Item.getItens().get(index));
						String dados = Item.exibirDados(st.nextToken(";"));
						JOptionPane.showMessageDialog(null, dados, "Dados", JOptionPane.PLAIN_MESSAGE);

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
			}
		});
		new Item();
		for (String item : Item.getItens()) {
			StringTokenizer st = new StringTokenizer(item);
			listaItens.add(st.nextToken(";") + " | " + st.nextToken(";")); // 0001 | Batata
		}
		listaItens.setBounds(10, 23, 250, 180);
		panel_1.add(listaItens);

		JButton button_2 = new JButton("Editar");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int index = listaItens.getSelectedIndex();

					new Item();
					StringTokenizer st = new StringTokenizer(Item.getItens().get(index));
					st.nextToken(";");
					txtNome.setText(st.nextToken(";"));
					ftxtQte.setText(st.nextToken(";"));
					String fornecedor = st.nextToken(";");
					for (int i = 0; i < choiceFornecedor.getItemCount(); i++) {
						if (choiceFornecedor.getItem(i).equals(fornecedor)) {
							choiceFornecedor.select(i);
							break;
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
		button_2.setBounds(171, 258, 89, 23);
		panel_1.add(button_2);

		txtPesquisa = new JTextField();
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(10, 209, 86, 23);
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
						new Item();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i = 0; i < Item.getItens().size(); i++) {
						StringTokenizer st = new StringTokenizer(Item.getItens().get(i));

						if (st.nextToken(";").contains(txtPesquisa.getText())
								|| st.nextToken(";").contains(txtPesquisa.getText())) {
							listaItens.select(i);
							break;
						}
					}
				}
			}
		});
		button_3.setBounds(106, 209, 33, 23);
		panel_1.add(button_3);

		JButton button_4 = new JButton("Remover");
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int index = listaItens.getSelectedIndex();

					new Item();
					StringTokenizer st = new StringTokenizer(Item.getItens().get(index));
					Item.remover(st.nextToken(";"));

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
		button_4.setBounds(7, 258, 110, 23);
		panel_1.add(button_4);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira um nome", "Erro", JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
					txtNome.selectAll();
				} else {
					try {
						new Item(choiceFornecedor.getSelectedItem(), txtNome.getText(),
								Integer.parseInt(ftxtQte.getText())).cadastrar();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					;

					JOptionPane.showMessageDialog(null, "Item cadastrado com succeso!", "Cadastro",
							JOptionPane.INFORMATION_MESSAGE);

					// Limpando os campos e retornando o foco ao Nome
					txtNome.setText("");
					ftxtQte.setText("");
					txtNome.requestFocus();

				}
			}
		});
		btnCadastrar.setBounds(10, 148, 110, 23);
		panel.add(btnCadastrar);

		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaItens.removeAll();
				for (String item : Item.getItens()) {
					StringTokenizer st = new StringTokenizer(item);
					listaItens.add(st.nextToken(";") + " | " + st.nextToken(";")); // 0001 | Batata
				}
			}
		});
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuBar.add(mntmAtualizar);
	}
	
}
