package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.Fornecedor;
import pizzaria.Mesa;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JFormattedTextField;

public class TelaCadastroMesa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroMesa frame = new TelaCadastroMesa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public TelaCadastroMesa() throws ParseException, IOException {
		setTitle("Cadastrar Mesa");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 278, 497);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "INFORMA\u00C7\u00D5ES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 249, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(20, 21, 60, 14);
		panel.add(lblNmero);
		
		JLabel lblQuantidadeDeAssentos = new JLabel("Quantidade de assentos");
		lblQuantidadeDeAssentos.setBounds(20, 70, 170, 20);
		panel.add(lblQuantidadeDeAssentos);
		

		MaskFormatter maskNumero = new MaskFormatter("##");
		maskNumero.setPlaceholderCharacter('0');
		
		JFormattedTextField ftxtNumero = new JFormattedTextField(maskNumero);
		ftxtNumero.setBounds(20, 39, 40, 20);
		panel.add(ftxtNumero);
		


		MaskFormatter maskQuant = new MaskFormatter("##");
		maskQuant.setPlaceholderCharacter('0');
		
		JFormattedTextField ftxtQuantAss = new JFormattedTextField(maskQuant);
		ftxtQuantAss.setBounds(20, 95, 40, 20);
		panel.add(ftxtQuantAss);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		
        btnCadastrar.addActionListener(new ActionListener() {
			
			// A��O DO BOT�O CADASTRAR
			public void actionPerformed(ActionEvent arg0) {
				
				// Verificando se todos os dados foram preenchidos
	     		if (Integer.parseInt(ftxtNumero.getText()) == 0 || (Integer.parseInt(ftxtQuantAss.getText())==0)){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
						// Cadastrando a nova mesa
						try {
							new Mesa(Integer.parseInt(ftxtNumero.getText()), Integer.parseInt(ftxtQuantAss.getText())).cadastrar();
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Mesa cadastrada com succeso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
						// Limpando os campos e retornando o foco ao Nome
						ftxtNumero.setText("0");
						ftxtQuantAss.setText("0");
						ftxtNumero.requestFocus();
					
					
					
				}
			}
		});
		
		
		btnCadastrar.setBounds(10, 126, 110, 23);
		panel.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnCancelar.setBounds(130, 126, 110, 23);
		panel.add(btnCancelar);
		
		List listaMesa = new List();
		
		new Mesa();
		StringTokenizer st;
		for(String mesa : Mesa.getMesas()) {
			st = new StringTokenizer(mesa);
			listaMesa.add(st.nextToken(";") + " | " + st.nextToken(";")); // 11.222.333/4444-55 | Fornecedor Exemplo S/A
		}
		listaMesa.setBounds(25, 226, 218, 99);
		contentPane.add(listaMesa);
		
		JPanel panel_listaMesa = new JPanel();
		panel_listaMesa.setBackground(Color.WHITE);
		panel_listaMesa.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de Mesas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_listaMesa.setBounds(10, 182, 249, 246);
		contentPane.add(panel_listaMesa);
		panel_listaMesa.setLayout(null);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.LIGHT_GRAY);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaMesa.getSelectedIndex();
					
					StringTokenizer st = new StringTokenizer(Fornecedor.getFornecedores().get(index));
					ftxtNumero.setText(st.nextToken(";"));
					ftxtQuantAss.setText(st.nextToken(";"));
					
					txtPesquisa.setText("");
				} catch (NullPointerException n) {
					JOptionPane.showMessageDialog(null, "Nenhum mesa selecionada", "Erro", JOptionPane.INFORMATION_MESSAGE);
				}  catch (ArrayIndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Nenhum mesa selecionada", "Erro", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		
		btnEditar.setBounds(13, 196, 89, 23);
		panel_listaMesa.add(btnEditar);
		
		JButton btnRemover = new JButton("Remover\r\n");
		btnRemover.setBackground(Color.LIGHT_GRAY);
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = listaMesa.getSelectedIndex();
					
					StringTokenizer st = new StringTokenizer(Mesa.getMesas().get(index));
					new Mesa().remover(st.nextToken(";"));
					
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
		btnRemover.setBounds(130, 196, 110, 23);
		panel_listaMesa.add(btnRemover);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(13, 154, 86, 20);
		panel_listaMesa.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JButton btnPesquisa = new JButton("");
		btnPesquisa.setBackground(Color.LIGHT_GRAY);
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando a exist�ncia de dados no campo Pesquisa
				if (txtPesquisa.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira uma chave de pesquisa.", "Erro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					for(int i = 0; i < Fornecedor.getFornecedores().size(); i++) {
						if (Mesa.getMesas().get(i).contains(txtPesquisa.getText())) {
							listaMesa.select(i);
							break;
						}
					}
				}
			}
		});
		
		btnPesquisa.setBounds(109, 154, 33, 20);
		panel_listaMesa.add(btnPesquisa);
		
		JLabel lblN = new JLabel("N\u00BA");
		lblN.setBounds(15, 24, 25, 14);
		panel_listaMesa.add(lblN);
		
		JLabel lblQuantidade = new JLabel("Lugares");
		lblQuantidade.setBounds(45, 24, 89, 14);
		panel_listaMesa.add(lblQuantidade);
		
		JLabel label = new JLabel("|");
		label.setBounds(38, 24, 46, 14);
		panel_listaMesa.add(label);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("ATUALIZAR");
		setJMenuBar(menuBar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaMesa.removeAll();
				try {
					new Mesa();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String mesa : Mesa.getMesas()) {
					StringTokenizer st = new StringTokenizer(mesa);
					listaMesa.add(st.nextToken(";") + " | " + st.nextToken(";")); // 11.222.333/4444-55 | Fornecedor Exemplo S/A
				}
			}
		});
		
		mntmAtualizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		menuBar.add(mntmAtualizar);
		
		
	}
}



