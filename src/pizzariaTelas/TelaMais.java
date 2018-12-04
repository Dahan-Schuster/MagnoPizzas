package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;

public class TelaMais extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMais frame = new TelaMais();
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
	public TelaMais() {
		setTitle("Mais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 219);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				fechar();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 267, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAjuda = new JButton("Ajuda");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaAjuda().setVisible(true);
			}
		});
		btnAjuda.setBackground(Color.LIGHT_GRAY);
		btnAjuda.setBounds(30, 76, 89, 23);
		panel.add(btnAjuda);
		
		JButton btnSobre = new JButton("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sobre = "AUTORES: Ingrid Pauline e Dahan Schuster\n"
						+ "VERSÃO: 1.0\n"
						+ "DATA DE PUBLICAÇÃO: 11/10/2018"
						+ "\n\n  Magno Pizzas é um software que simula um sistema de pizzaria,\n"
						+ "desenvolvido e apresentado a fim da obtenção da nota referente ao\n"
						+ "3º bimestre da disciplina Programação II do 2º ano do curso\n"
						+ "Integrado em Informática do Instituto Federal de Sergipe - IFS."
						+ "\n\nOs autores agradecem a atenção!";
				JOptionPane.showMessageDialog(null, sobre, "Sobre", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnSobre.setBackground(Color.LIGHT_GRAY);
		btnSobre.setBounds(141, 76, 89, 23);
		panel.add(btnSobre);
	}
	
	private  void fechar() {
		dispose();
	}
}
