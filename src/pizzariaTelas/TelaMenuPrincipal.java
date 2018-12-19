package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;

import com.towel.swing.calendar.CalendarView;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TelaMenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botaoAbrirTelaAlterarData;
	private JMenuBar menuBar;
	private JMenu menuAtalhos;
	private JMenuItem atalhoTelaAjuda;
	private JMenuItem atalhoTelaPedidoPresencial;
	private JMenuItem atalhoTelaPedidoDelivery;
	private JMenuItem atalhoTelaFecharMesa;

	static final int WIDTH = 530;
	static final int HEIGTH = 500;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Dimension getDimension() {
		return new Dimension(WIDTH, HEIGTH);
	}

	private void chamarTelaPorAtalho(int atalho, boolean isAltDown) {
		try {
			if (isAltDown) {
				switch (atalho) {
				// Atalhos para as telas de cadastro
				case KeyEvent.VK_1:
					new TelaCadastroFornecedor().setVisible(true);
					break;
				case KeyEvent.VK_2:
					new TelaCadastroMesa().setVisible(true);
					break;
				case KeyEvent.VK_3:
					new TelaCadastrarItem().setVisible(true);
					break;
				case KeyEvent.VK_4:
					new TelaCadastroFuncionario().setVisible(true);
					break;
				case KeyEvent.VK_5:
					new TelaCadastrarReceita().setVisible(true);
					break;
				}
			}

		} catch (IOException | ParseException possibleExeptions) {
			possibleExeptions.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaMenuPrincipal() {
		setTitle("MAGNO PIZZAS - MENU PRINCIPAL");
		setPreferredSize(getDimension());
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent eventoTeclado) {

				int atalho = eventoTeclado.getKeyCode();

				if (eventoTeclado.isAltDown()) 
					chamarTelaPorAtalho(atalho, true);

			}
		});

		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		menuBar = new JMenuBar();
		menuAtalhos = (JMenu) menuBar.add(new JMenu("Atalhos"));

		atalhoTelaAjuda = (JMenuItem) menuAtalhos.add(new JMenuItem("Ajuda"));
		atalhoTelaAjuda.setAccelerator(KeyStroke.getKeyStroke("F1"));
		atalhoTelaAjuda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaAjuda().setVisible(true);
			}
		});

		atalhoTelaPedidoPresencial = (JMenuItem) menuAtalhos.add(new JMenuItem("Presencial"));
		atalhoTelaPedidoPresencial.setAccelerator(KeyStroke.getKeyStroke("F2"));
		atalhoTelaPedidoPresencial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaPresencial().setVisible(true);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		atalhoTelaPedidoDelivery = (JMenuItem) menuAtalhos.add(new JMenuItem("Delivery"));
		atalhoTelaPedidoDelivery.setAccelerator(KeyStroke.getKeyStroke("F3"));
		atalhoTelaPedidoDelivery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaDelivery().setVisible(true);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		atalhoTelaFecharMesa = (JMenuItem) menuAtalhos.add(new JMenuItem("Fechar Mesa"));
		atalhoTelaFecharMesa.setAccelerator(KeyStroke.getKeyStroke("F4"));
		atalhoTelaFecharMesa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaFecharMesa().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		contentPane.add(menuBar, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 505, 427);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);

		botaoAbrirTelaAlterarData = new JButton(
				new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		botaoAbrirTelaAlterarData.setBounds(350, 380, 150, 40);
		botaoAbrirTelaAlterarData.setBackground(new Color(165, 42, 42));
		botaoAbrirTelaAlterarData.setForeground(Color.white);
		botaoAbrirTelaAlterarData.setFocusable(false);
		botaoAbrirTelaAlterarData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaAlterarData().setVisible(true);
				;
			}
		});
		panel.add(botaoAbrirTelaAlterarData);

		// eita

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/pizzariaTelas/magnoLogo.png")));
		label.setBounds(180, 0, 190, 120);
		panel.add(label);

		JLabel lblCadastro = new JLabel("");
		lblCadastro.setToolTipText("Cadastrar");
		lblCadastro.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/pizzariaTelas/cadastro.png")));
		lblCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					chamarTelaCadastros();
				}
			}
		});
		lblCadastro.setBounds(56, 207, 100, 100);
		panel.add(lblCadastro);

		JLabel lblPedidos = new JLabel("");
		lblPedidos.setToolTipText("Realizar pedidos");
		lblPedidos.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/pizzariaTelas/pedido.png")));
		lblPedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();

					// c�digo aqui
					new TelaTipoPedido(botaoAbrirTelaAlterarData.getText()).setVisible(true);
				}

			}
		});
		lblPedidos.setBounds(212, 207, 100, 100);
		panel.add(lblPedidos);

		JLabel lblAjuda = new JLabel("");
		lblAjuda.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/pizzariaTelas/mais.png")));
		lblAjuda.setToolTipText("Obter ajuda");
		lblAjuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();

					// c�digo aqui
					new TelaMais().setVisible(true);
				}
			}
		});
		lblAjuda.setBounds(362, 207, 100, 100);
		panel.add(lblAjuda);

		JLabel lblCadastros = new JLabel("Cadastros");
		lblCadastros.setBounds(70, 318, 75, 14);
		panel.add(lblCadastros);

		JLabel lblPedidos_1 = new JLabel("Pedidos");
		lblPedidos_1.setBounds(235, 318, 70, 14);
		panel.add(lblPedidos_1);

		JLabel lblAjuda_1 = new JLabel("Mais");
		lblAjuda_1.setBounds(397, 318, 46, 14);
		panel.add(lblAjuda_1);

		JLabel lblDataDeExpediente = new JLabel("Data de expediente:");
		lblDataDeExpediente.setBounds(200, 392, 159, 14);
		panel.add(lblDataDeExpediente);

	}

	private void chamarTelaCadastros() {
		new TelaCadastros().setVisible(true);

	}

	private void AlterarTextoBotaoData(String text) {
		botaoAbrirTelaAlterarData.setText(text);
	}

	private class TelaAlterarData extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final CalendarView calendario = new CalendarView();

		private JPanel contentPane;
		private JPanel panel;

		private JButton btn_Ok;

		public TelaAlterarData() {

			SimpleDateFormat formatadorDeData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataSemFormatacao = new Date(System.currentTimeMillis());
			String dataFormatada = formatadorDeData.format(dataSemFormatacao);

			calendario.setText(dataFormatada);
			calendario.setBounds(40, 40, 200, 35);

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("Alterar data");
			setResizable(false);
			setBounds(10, 10, 300, 200);
			setLocationRelativeTo(null);

			addWindowFocusListener(new WindowFocusListener() {

				@Override
				public void windowLostFocus(WindowEvent e) {
					fechar();

				}

				@Override
				public void windowGainedFocus(WindowEvent e) {
				}
			});

			contentPane = new JPanel();
			contentPane.setBackground(new Color(165, 42, 42));
			contentPane.setLayout(null);
			setContentPane(contentPane);

			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 10, 280, 150);
			panel.setLayout(null);
			contentPane.add(panel);
			panel.add(calendario);

			btn_Ok = new JButton("OK");
			btn_Ok.setBounds(100, 100, 80, 30);
			btn_Ok.setBackground(new Color(165, 42, 42));
			btn_Ok.setForeground(Color.WHITE);
			btn_Ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getData();
				}
			});
			panel.add(btn_Ok);

		}

		static final int TAMANHO_CORRETO_PARA_DATA = 10;

		public void getData() {
			String dataEscolhida = calendario.getText();

			final int TAMANHO_DA_DATA = dataEscolhida.length();

			if (TAMANHO_DA_DATA > TAMANHO_CORRETO_PARA_DATA) {

				String dataApenasComDias = dataEscolhida.substring(0, 3);
				String dataComMesEAno = dataEscolhida.substring(4);

				dataEscolhida = dataApenasComDias + dataComMesEAno;
			}

			AlterarTextoBotaoData(dataEscolhida);
			fechar();

		}

		private void fechar() {
			dispose();
		}
	}

}
