package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import pizzaria.Mesa;

import javax.swing.border.EtchedBorder;
import java.awt.List;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFecharMesa extends JFrame {

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
					TelaFecharMesa frame = new TelaFecharMesa();
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
	 * @throws IOException
	 */
	public TelaFecharMesa() throws IOException {
		setResizable(false);
		setTitle("Fechar Mesa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(165, 42, 42));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		List listaMesas = new List();
		new Mesa();
		for (String mesa : Mesa.getMesas()) {
			listaMesas.add(mesa);
		}
		listaMesas.setBounds(10, 10, 394, 179);
		panel.add(listaMesas);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					new Mesa();
					Mesa.fecharConta(listaMesas.getSelectedIndex());
					listaMesas.removeAll();
					for (String mesa : Mesa.getMesas()) {
						listaMesas.add(mesa);
					}
				} catch (NullPointerException a) {
					JOptionPane.showMessageDialog(null, "Selecione uma mesa", "Erro", JOptionPane.ERROR_MESSAGE);
					listaMesas.select(0);
				} catch(IndexOutOfBoundsException a) {
					JOptionPane.showMessageDialog(null, "Selecione uma mesa", "Erro", JOptionPane.ERROR_MESSAGE);
					listaMesas.select(0);
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFechar.setBackground(Color.LIGHT_GRAY);
		btnFechar.setBounds(315, 205, 89, 23);
		panel.add(btnFechar);
	}

}
