package pizzariaTelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class TelaCadastros extends JFrame {

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
					TelaCadastros frame = new TelaCadastros();
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
	public TelaCadastros() {
		setTitle("Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 400, 280);
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
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 370, 229);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnFornecedor = new JButton("Fornecedor");
		btnFornecedor.setBackground(Color.LIGHT_GRAY);
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					new TelaCadastroFornecedor().setVisible(true);
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnFornecedor.setBounds(30, 64, 120, 23);
		panel.add(btnFornecedor);

		JButton btnIngrediente = new JButton("Ingrediente");
		btnIngrediente.setBackground(Color.LIGHT_GRAY);
		btnIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaCadastrarItem().setVisible(true);
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIngrediente.setBounds(30, 114, 120, 23);
		panel.add(btnIngrediente);

		JButton btnReceita = new JButton("Receita");
		btnReceita.setBackground(Color.LIGHT_GRAY);
		btnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaCadastrarReceita().setVisible(true);
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReceita.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent f) {
				fechar();
			}
		});
		btnReceita.setBounds(125, 164, 120, 23);
		panel.add(btnReceita);

		JButton btnMesa = new JButton("Mesa");
		btnMesa.setBackground(Color.LIGHT_GRAY);
		btnMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaCadastroMesa().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMesa.setBounds(220, 64, 120, 23);
		panel.add(btnMesa);

		JButton btnFuncionrio = new JButton("Funcion\u00E1rio");
		btnFuncionrio.setBackground(Color.LIGHT_GRAY);
		btnFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaCadastroFuncionario().setVisible(true);
				} catch (ParseException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFuncionrio.setBounds(220, 114, 120, 23);
		panel.add(btnFuncionrio);

	}
	
	private void fechar() {
		dispose();
	}
}
