package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Delivery;
import pizzaria.Funcionario;
import pizzaria.Receita;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Choice;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.List;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TelaDelivery extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String data;
	private static JFormattedTextField ftxtQuant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDelivery frame = new TelaDelivery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaDelivery(String data) throws IOException, ParseException{
		TelaDelivery.data = data;
		setTitle("Pedido delivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 686);
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
	public TelaDelivery() throws IOException, ParseException {
		setTitle("Pedido delivery");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 686);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start(contentPane);

	}
	
	private static void start(JPanel contentPane) throws ParseException, IOException {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informa\u00E7\u00F5es de entrega",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 22, 553, 165);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLabelRua = new JLabel("Endere\u00E7o (N\u00FAmero/Apto/Bloco):");
		lblLabelRua.setBounds(10, 21, 408, 21);
		panel.add(lblLabelRua);

		JTextField txtEndereco = new JTextField();
		txtEndereco.setBounds(10, 44, 533, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 78, 46, 14);
		panel.add(lblBairro);

		JTextField txtBairro = new JTextField();
		txtBairro.setBounds(10, 96, 221, 20);
		panel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblPontoDeReferncia = new JLabel("Ponto de refer\u00EAncia:");
		lblPontoDeReferncia.setBounds(243, 78, 146, 14);
		panel.add(lblPontoDeReferncia);

		JTextField txtPontoDe = new JTextField();
		txtPontoDe.setBounds(241, 96, 302, 20);
		panel.add(txtPontoDe);
		txtPontoDe.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informa\u00E7\u00F5es do pedido",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 205, 553, 431);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		ButtonGroup tamanhos = new ButtonGroup();

		JRadioButton rdbtnP = new JRadioButton("P");
		rdbtnP.setSelected(true);
		rdbtnP.setActionCommand("P");
		rdbtnP.setBounds(17, 23, 43, 23);
		panel_1.add(rdbtnP);

		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setActionCommand("M");
		rdbtnM.setBounds(61, 23, 43, 23);
		panel_1.add(rdbtnM);

		JRadioButton rdbtnG = new JRadioButton("G");
		rdbtnG.setActionCommand("G");
		rdbtnG.setBounds(106, 23, 43, 23);
		panel_1.add(rdbtnG);

		JRadioButton rdbtnGf = new JRadioButton("GF");
		rdbtnGf.setActionCommand("GF");
		rdbtnGf.setBounds(196, 23, 55, 23);
		panel_1.add(rdbtnGf);

		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setActionCommand("F");
		rdbtnF.setBounds(151, 23, 43, 23);
		panel_1.add(rdbtnF);

		tamanhos.add(rdbtnP);
		tamanhos.add(rdbtnF);
		tamanhos.add(rdbtnG);
		tamanhos.add(rdbtnM);
		tamanhos.add(rdbtnGf);

		List listReceitas = new List();
		listReceitas.setBounds(17, 146, 510, 145);
		panel_1.add(listReceitas);

		Choice choiceReceitas = new Choice();
		new Receita();
		for (String receita : Receita.getReceitas()) {
			StringTokenizer st = new StringTokenizer(receita);
			choiceReceitas.add(st.nextToken(";") + " | " + st.nextToken(";"));

		}
		choiceReceitas.setBounds(17, 77, 184, 20);
		panel_1.add(choiceReceitas);

		JTextField txtValor = new JTextField();

		txtValor.setText("00.00");
		txtValor.setEditable(false);
		txtValor.setBounds(130, 352, 86, 20);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StringTokenizer st = new StringTokenizer(listReceitas.getSelectedItem(), "|");
					
					st.nextToken();
					st.nextToken();
					
					float preco = tryParsePreco(st.nextToken());
					
					listReceitas.remove(listReceitas.getSelectedIndex());
					
					float Valortot = tryParsePreco(txtValor.getText()) - preco;
					txtValor.setText(String.format("%5.2f", Valortot));
				} catch (ArrayIndexOutOfBoundsException n) {
					JOptionPane.showMessageDialog(null, "Selecione uma receita da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemover.setBackground(Color.LIGHT_GRAY);
		btnRemover.setBounds(418, 297, 110, 23);
		panel_1.add(btnRemover);


		JLabel lblValorTotal = new JLabel("Valor Total (R$):");
		lblValorTotal.setBounds(17, 355, 110, 14);
		panel_1.add(lblValorTotal);

		MaskFormatter maskQte = new MaskFormatter("###");
		maskQte.setPlaceholderCharacter('0');
		
		ftxtQuant = new JFormattedTextField(maskQte);
		ftxtQuant.setBounds(216, 78, 50, 20);
		ftxtQuant.addKeyListener(new KeyAdapter() {
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
		panel_1.add(ftxtQuant);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(Color.LIGHT_GRAY);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (Integer.parseInt(ftxtQuant.getText()) == 0) {
						JOptionPane.showMessageDialog(null, "Indique uma quantidade", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						new Receita();
						String produto;
						if (Receita.isDisponivel(choiceReceitas.getSelectedItem().substring(0, 4)).equals("Sim")) {
							float valor = 0;
							for (String receita : Receita.getReceitas()) {
								StringTokenizer st = new StringTokenizer(receita);
								if (choiceReceitas.getSelectedItem().substring(0, 4).equals(st.nextToken(";"))) {
									switch (tamanhos.getSelection().getActionCommand()) {
									case "P":
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQuant.getText())));
										produto = choiceReceitas.getSelectedItem();
										listReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQuant.getText()), valor,
												produto.substring(7)));
										break;
									case "M":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQuant.getText())));
										produto = choiceReceitas.getSelectedItem();
										listReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQuant.getText()), valor,
												produto.substring(7)));
										break;
									case "G":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQuant.getText())));
										produto = choiceReceitas.getSelectedItem();
										listReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQuant.getText()), valor,
												produto.substring(7)));
										break;
									case "F":
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										st.nextToken(";");
										valor = (Float.parseFloat(st.nextToken(";"))
												* (Integer.parseInt(ftxtQuant.getText())));
										produto = choiceReceitas.getSelectedItem();
										listReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQuant.getText()), valor,
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
												* (Integer.parseInt(ftxtQuant.getText())));
										produto = choiceReceitas.getSelectedItem();
										listReceitas.add(String.format("%s | %03d | %5.2f | %s",
												produto.substring(0, 4), Integer.parseInt(ftxtQuant.getText()), valor,
												produto.substring(7)));
										break;

									}
									break;

								}

							}
							float Valortot;
							Valortot = Float.parseFloat(txtValor.getText()) + valor;
							txtValor.setText(String.format("%5.2f", Valortot));

						} else {
							JOptionPane.showMessageDialog(null, "Receita indispon�vel", "Falha",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (ArrayIndexOutOfBoundsException n) {
					JOptionPane.showMessageDialog(null, "Selecione um item da lista", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdicionar.setBounds(17, 297, 110, 23);
		panel_1.add(btnAdicionar);

		Choice choiceFuncionario = new Choice();

		new Funcionario();

		for (String funcionario : Funcionario.getFuncionarios()) {
			StringTokenizer st = new StringTokenizer(funcionario);
			st.nextToken(";");
			choiceFuncionario.add(st.nextToken(";"));
		}

		choiceFuncionario.setBounds(350, 77, 177, 20);
		panel_1.add(choiceFuncionario);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio:");
		lblFuncionrio.setBounds(350, 57, 177, 14);
		panel_1.add(lblFuncionrio);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(218, 53, 94, 14);
		panel_1.add(lblQuantidade);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(17, 57, 87, 14);
		panel_1.add(lblProduto);

		JLabel lblIdNome = new JLabel("ID  | Quantidade | Valor | Nome");
		lblIdNome.setBounds(17, 126, 273, 14);
		panel_1.add(lblIdNome);

		JButton btnLancar = new JButton("Lan\u00E7ar");

		btnLancar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtEndereco.getText().trim().isEmpty() || txtBairro.getText().trim().isEmpty()
						|| txtPontoDe.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Preencha todos os campos referente as informa��es de endere�o.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					try {
						ArrayList<String> pedidosRealizados = new ArrayList<>();
						for (int i = 0; i < listReceitas.getItemCount(); i++) {
							pedidosRealizados.add(listReceitas.getItem(i));
						}

						new Delivery(pedidosRealizados, Float.parseFloat(txtValor.getText()),
								choiceFuncionario.getSelectedItem(), data, txtEndereco.getText(), txtBairro.getText(),
								txtPontoDe.getText()).realizarPedido();

						JOptionPane.showMessageDialog(null, "Pedido lan�ado!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						
						// RESETANDO TODOS OS DADOS
						txtEndereco.setText("");
						txtBairro.setText("");
						txtPontoDe.setText("");
						txtValor.setText("00.00");
						choiceFuncionario.select(0);
						listReceitas.removeAll();
						txtEndereco.requestFocus();
						

					} catch (NumberFormatException e1) {

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

		btnLancar.setBackground(Color.LIGHT_GRAY);
		btnLancar.setBounds(17, 397, 89, 23);
		panel_1.add(btnLancar);
	}
	

	private static void addQte(int qte) {
		try {
		int oldQte = Integer.parseInt(ftxtQuant.getText());
		int newQte = oldQte + qte;
		if (newQte > 999) {
			ftxtQuant.setText("999");
		} else if (newQte < 1) {
			ftxtQuant.setText("001");
		} else {
			ftxtQuant.setText(String.format("%03d", newQte));
		}
		} catch (NumberFormatException n) {
			ftxtQuant.setText("000");
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
