package pizzariaTelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class TelaTipoPedido extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTipoPedido frame = new TelaTipoPedido();
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
	public TelaTipoPedido(String data) {
		TelaTipoPedido.data = data;
		setTitle("Tipo de Pedido");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 270);
		setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start(contentPane, this);
	}
	
	public TelaTipoPedido() {
		setTitle("Tipo de Pedido");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 270);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start(contentPane, this);
	}
	
	private static void start(JPanel contentPane, JFrame frame) {
		
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				fechar(frame);
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 327, 219);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnPresencial = new JButton("Presencial");
		btnPresencial.setBackground(Color.LIGHT_GRAY);
        btnPresencial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					new TelaPresencial().setVisible(true);
				}
				catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				
				
			}});
		btnPresencial.setBounds(39, 79, 104, 23);
		panel.add(btnPresencial);
		
		JButton btnDelivery = new JButton("Delivery");
		btnDelivery.setBackground(Color.LIGHT_GRAY);
        btnDelivery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					new TelaDelivery(data).setVisible(true);
				}
				catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnDelivery.setBounds(180, 79, 104, 23);
		panel.add(btnDelivery);
		
		JButton btnFecharMesa = new JButton("Fechar Mesa");
		btnFecharMesa.setBackground(Color.LIGHT_GRAY);
		btnFecharMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					new TelaFecharMesa().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFecharMesa.setBounds(97, 127, 121, 23);
		panel.add(btnFecharMesa);
	}
	
	private static void fechar(JFrame frame) {
		frame.dispose();
	}
}


