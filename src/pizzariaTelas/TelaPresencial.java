package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import pizzaria.Funcionario;
import pizzaria.Mesa;
import pizzaria.Presencial;
import pizzaria.Receita;

import javax.swing.JRadioButton;
import java.awt.Choice;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.List;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TelaPresencial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String data;
	private static JTextField ftxtQte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPresencial frame = new TelaPresencial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPresencial(String data) throws IOException, ParseException {
		TelaPresencial.data = data;
		setTitle("Pedido Presencial");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start(contentPane);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public TelaPresencial() throws IOException, ParseException {
		setTitle("Pedido Presencial");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start(contentPane);
	}

	private static void start(JPanel contentPane) throws IOException, ParseException {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es do Pedido", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 540, 218);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setBounds(10, 31, 70, 14);
		panel.add(lblTamanho);

		ButtonGroup tamanho = new ButtonGroup();

		JRadioButton rdbtnP = new JRadioButton("P");
		rdbtnP.setActionCommand("P");
		rdbtnP.setSelected(true);
		rdbtnP.setBounds(20, 52, 51, 23);
		panel.add(rdbtnP);

		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setActionCommand("M");
		rdbtnM.setBounds(73, 52, 51, 23);
		panel.add(rdbtnM);

		JRadioButton rdbtnG = new JRadioButton("G");
		rdbtnG.setActionCommand("G");
		rdbtnG.setBounds(126, 52, 51, 23);
		panel.add(rdbtnG);

		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setActionCommand("F");
		rdbtnF.setBounds(179, 52, 51, 23);
		panel.add(rdbtnF);

		JRadioButton rdbtnGf = new JRadioButton("GF");
		rdbtnGf.setActionCommand("GF");
		rdbtnGf.setBounds(232, 52, 51, 23);
		panel.add(rdbtnGf);

		tamanho.add(rdbtnP);
		tamanho.add(rdbtnM);
		tamanho.add(rdbtnG);
		tamanho.add(rdbtnF);
		tamanho.add(rdbtnGf);

		Choice choiceReceita = new Choice();
		new Receita();
		for (String receita : Receita.getReceitas()) {
			StringTokenizer st = new StringTokenizer(receita);
			choiceReceita.add(st.nextToken(";") + " | " + st.nextToken(";"));
		}
		choiceReceita.setBounds(20, 106, 157, 20);
		panel.add(choiceReceita);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 82, 61, 14);
		panel.add(lblProduto);

		MaskFormatter maskQte = new MaskFormatter("###");
		
		ftxtQte = new JFormattedTextField(maskQte);
		ftxtQte.setText("001");
		ftxtQte.setBounds(185, 106, 45, 20);
		ftxtQte.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					addQte(1);
					break;
				case KeyEvent.VK_DOWN:
					addQte(-1);
					break;
				case KeyEvent.VK_PAGE_UP:
					addQte(10);
					break;
				case KeyEvent.VK_PAGE_DOWN:
					addQte(-10);
					break;
				}

			}
		});
		panel.add(ftxtQte);

		JLabel lblQuantidade = new JLabel("QTE");
		lblQuantidade.setBounds(179, 86, 70, 14);
		panel.add(lblQuantidade);

		List listaReceitas = new List();
		listaReceitas.setBounds(306, 52, 224, 108);
		panel.add(listaReceitas);

		JLabel lblValorTotal = new JLabel("Valor total: R$");
		lblValorTotal.setBounds(350, 169, 110, 14);
		panel.add(lblValorTotal);

		JTextField txtValortot = new JTextField();
		txtValortot.setText("00.00");
		txtValortot.setEditable(false);
		txtValortot.setBounds(454, 166, 76, 20);
		panel.add(txtValortot);
		txtValortot.setColumns(10);

		JLabel lblFuncionario = new JLabel("Funcion\u00E1rio:");
		lblFuncionario.setBounds(10, 190, 100, 14);
		panel.add(lblFuncionario);

		Choice choiceFuncionario = new Choice();
		new Funcionario();
		for (String funcionario : Funcionario.getFuncionarios()) {
			StringTokenizer st = new StringTokenizer(funcionario, ";");
			st.nextToken();
			choiceFuncionario.add(st.nextToken());
		}
		choiceFuncionario.setBounds(110, 188, 180, 20);
		panel.add(choiceFuncionario);

		JLabel lblNNome = new JLabel("ID        |Qte   | Valor   |  Nome   ");
		lblNNome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNNome.setBounds(306, 31, 199, 14);
		panel.add(lblNNome);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mesa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 240, 540, 247);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		List listaMesas = new List();
		new Mesa();
		for (String mesa : Mesa.getMesas()) {
			StringTokenizer st = new StringTokenizer(mesa, ";");
			String mesaFormat = st.nextToken() + " | " + st.nextToken() + " | " + st.nextToken();
			st.nextToken();

			while (st.hasMoreTokens()) {
				mesaFormat = mesaFormat + " | " + st.nextToken();
			}
			listaMesas.add(mesaFormat);
		}
		listaMesas.setBounds(10, 40, 520, 153);
		panel_1.add(listaMesas);

		JLabel lblN = new JLabel("N\u00BA  | As. | Situa\u00E7\u00E3o      | Pedidos");
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblN.setBounds(10, 20, 199, 14);
		panel_1.add(lblN);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StringTokenizer st = new StringTokenizer(listaReceitas.getSelectedItem(), "|");
					st.nextToken();
					st.nextToken();
					
					float preco = tryParsePreco(st.nextToken());

					listaReceitas.remove(listaReceitas.getSelectedIndex());

					float Valortot = tryParsePreco(txtValortot.getText()) - preco;
					txtValortot.setText(String.format("%5.2f", Valortot));
				} catch (ArrayIndexOutOfBoundsException | NullPointerException  n) {
					JOptionPane.showMessageDialog(null, "Selecione uma receita da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemover.setBackground(Color.LIGHT_GRAY);
		btnRemover.setBounds(126, 137, 104, 23);
		panel.add(btnRemover);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float valor = 0, Valortot = 0;
				try {
					if (Integer.parseInt(ftxtQte.getText()) == 0) {
					
						JOptionPane.showMessageDialog(null, "Indique uma quantidade", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
					
					} else {

						new Receita();
						if (Receita.isDisponivel(choiceReceita.getSelectedItem().substring(0, 4)).equals("Sim")) {

							String produto = choiceReceita.getSelectedItem();
							for (String receita : Receita.getReceitas()) {
								StringTokenizer st = new StringTokenizer(receita);
								if (choiceReceita.getSelectedItem().substring(0, 4).equals(st.nextToken(";"))) {
									switch (tamanho.getSelection().getActionCommand()) {
									case "P":
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQte.getText())));
										listaReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQte.getText()), valor,
												produto.substring(7)));
										break;
									case "M":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQte.getText())));
										listaReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQte.getText()), valor,
												produto.substring(7)));
										break;
									case "G":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQte.getText())));
										listaReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQte.getText()), valor,
												produto.substring(7)));
										break;
									case "F":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQte.getText())));
										listaReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQte.getText()), valor,
												produto.substring(7)));
										break;
									case "GF":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQte.getText())));
										listaReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQte.getText()), valor,
												produto.substring(7)));
										break;
									}
									break;
								}

							}
							
							try {
							Valortot = Float.parseFloat(txtValortot.getText()) + valor;
							} catch (NumberFormatException n) {
								String[] v = txtValortot.getText().split(","); // 15,00
								String s = v[0] + "." + v[1]; // "15" + "." + "00" = 15.00
								Valortot = Float.parseFloat(s) + valor;
							}
							txtValortot.setText(String.format("%5.2f", Valortot));
						} else {
							JOptionPane.showMessageDialog(null, "Item indisponível.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (ArrayIndexOutOfBoundsException n) {
					JOptionPane.showMessageDialog(null, "Selecione uma receita da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnAdicionar.setBackground(Color.LIGHT_GRAY);
		btnAdicionar.setBounds(20, 137, 104, 23);
		panel.add(btnAdicionar);

		JButton btnLanar = new JButton("Lan\u00E7ar");
		btnLanar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<String> receitas = new ArrayList<>();
					for (int i = 0; i < listaReceitas.getItemCount(); i++) {
						receitas.add(listaReceitas.getItem(i));
					}

					new Presencial(receitas, Float.parseFloat(txtValortot.getText()),
							choiceFuncionario.getSelectedItem(), data,
							Integer.parseInt(listaMesas.getSelectedItem().substring(0, 2))).realizarPedido();

					JOptionPane.showMessageDialog(null, "Pedido lan�ado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

					// RESETANDO TODOS OS CAMPOS
					listaMesas.removeAll();
					new Mesa();
					for (String mesa : Mesa.getMesas()) {
						listaMesas.add(mesa);
					}

					listaReceitas.removeAll();
					txtValortot.setText("00.00");
					choiceFuncionario.select(0);
					choiceReceita.requestFocus();

				} catch (NullPointerException a) {
					JOptionPane.showMessageDialog(null, "Selecione uma mesa", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLanar.setBackground(Color.LIGHT_GRAY);
		btnLanar.setBounds(441, 213, 89, 23);
		panel_1.add(btnLanar);
	}

	private static void addQte(int qte) {
		try {
		int oldQte = Integer.parseInt(ftxtQte.getText());
		int newQte = oldQte + qte;
		if (newQte > 999) {
			ftxtQte.setText("999");
		} else if (newQte < 1) {
			ftxtQte.setText("001");
		} else {
			ftxtQte.setText(String.format("%03d", newQte));
		}
		} catch (NumberFormatException n) {
			ftxtQte.setText("000");
		}

	}
	
	private static float tryParsePreco(String preco) {
		try {
			return Float.parseFloat(preco);
		} catch (NumberFormatException e) {
			String[] numeros = preco.split(",");
			return Float.parseFloat(numeros[0] + "." + numeros[1]);
		}
	}
}
