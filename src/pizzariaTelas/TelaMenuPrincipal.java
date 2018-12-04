package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
	private JButton setData;

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

	/**
	 * Create the frame.
	 */
	public TelaMenuPrincipal() {
		setTitle("MAGNO PIZZAS - MENU PRINCIPAL");
		setResizable(false);
		setPreferredSize(new Dimension(450, 300));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 479);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					if (e.isAltDown()) {
						switch (e.getKeyCode()) {
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

					} else {
						switch (e.getKeyCode()) {
						// Atalho para a tela de Ajuda
						case KeyEvent.VK_F1:
							new TelaAjuda().setVisible(true);
							break;

						// Atalhos para as telas de pedidos
						case KeyEvent.VK_F2:
							new TelaPresencial().setVisible(true);
							break;
						case KeyEvent.VK_F3:
							new TelaDelivery().setVisible(true);
							break;
						case KeyEvent.VK_F4:
							new TelaFecharMesa().setVisible(true);
							break;
						}
					}

				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 505, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		int[] i = {1,2,3,4};
		
		for (int j = 0; j < i.length; j++) {
			System.out.println(j);
		}

		setData = new JButton(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		setData.setBounds(350, 380, 150, 40);
		setData.setBackground(new Color(165, 42, 42));
		setData.setForeground(Color.white);
		setData.setFocusable(false);
		setData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaAlterarData().setVisible(true);
			}
		});
		panel.add(setData);

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
					new TelaTipoPedido(setData.getText()).setVisible(true);
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

		// c�digo aqui
		new TelaCadastros().setVisible(true);

	}
	
	private void setDataText(String text) {
		setData.setText(text);
	}

	private class TelaAlterarData extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final CalendarView calendario = new CalendarView();

		private JPanel contentPane;
		private JPanel panel;

		private JButton btOk;

		public TelaAlterarData() {
			calendario.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
			calendario.setBounds(40, 40, 200, 35);
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("Alterar data");
			setBounds(10, 10, 300, 200);
			setLocationRelativeTo(null);

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

			btOk = new JButton("OK");
			btOk.setBounds(100, 100, 80, 30);
			btOk.setBackground(new Color(165, 42, 42));
			btOk.setForeground(Color.WHITE);
			btOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getData();
				}
			});
			panel.add(btOk);
			
			
			
		}
		
		public void getData() {
			String data = calendario.getText();
			if (data.length() == 11) {
				data = data.substring(0, 3) + data.substring(4);
			}
			
			setDataText(data);
			dispose();
			
		}

	}
}
