package pizzariaTelas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ItemEvent;

public class TelaAjuda extends JFrame {

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
					TelaAjuda frame = new TelaAjuda();
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
	public TelaAjuda() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ajuda");
		setBounds(100, 100, 600, 620);
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
		panel.setBounds(10, 10, 580, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TextArea resposta = new TextArea();
		resposta.setEditable(false);
		resposta.setFont(new Font("Arial", Font.PLAIN, 18));
		resposta.setBounds(10, 240, 560, 300);
		resposta.setFocusable(false);
		panel.add(resposta);
		
		List listaPerguntas = new List();
		listaPerguntas.setFont(new Font("Arial", Font.PLAIN, 16) );
		listaPerguntas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				switch (listaPerguntas.getSelectedIndex()) {
				case 0:
					resposta.setText("O expediente de pizzarias geralmente dura até depois da\n"
							+ "meia noite, portanto, para evitar problemas com datas de\n"
							+ "pedidos realizados em uma mesa antes e depois das 00h, \n"
							+ "a data deve ser manualmente definida.\n\n"
							+ "Conta-se como um dia de trabalho não das 00h01 às \n"
							+ "23H59, mas sim o intervalo de tempo entre o abrir e \n"
							+ "fechar da pizzaria.");
					break;
				case 1:
					resposta.setText("Dando um duplo clique sobre o fornecedor, funcionario,\n"
							+ "item etc. em sua lista na tela de cadastros.\n"
							+ "\nExemplo: exibir dados de uma receita\n"
							+ "Menu Principal > Cadastros > Receita > duplo clique sobre a\n"
							+ "receita requisitada na lista de receitas.");
					break;
				case 2:
					resposta.setText("Para finalizar e limpar todos os pedidos realizados por ela,\n"
							+ "deixando-a pronta para receber um novo cliente.\n\n"
							+ "Não se preocupe com o histórico de pedidos! \n\n"
							+ "Eles não serão perdidos, todos estão salvos nos\n"
							+ "arquivos pedidosDelivery e pedidosPresenciais na pasta do programa.\n\n"
							+ "A próxima versão incluirá uma opção de escolha de diretório no seu\n"
							+ "computador para a gravação destes arquivos.");
					break;
				case 3:
					resposta.setText("Clicando em Atualizar, no topo da tela, ou teclando Alt+R.");
					break;
				case 4:
					resposta.setText("Indo em Cadastros > Ingrediente > Selecione o ingrediente\n"
							+ "desejado na lista de itens cadastrados > Editar > Altere o valor do\n"
							+ "campo Quantidade.");
					break;
				case 5:
					resposta.setText("F1: Abre esta tela de ajuda;\n"
							+ "\n************** PEDIDOS **************\n\n"
							+ "F2: Abre a aba Pedido Presencial;\n"
							+ "F3: Abre a aba Pedido Delivery;\n"
							+ "F4: Abre a aba Fechar Mesa;\n"
							+ "\n************ CADASTROS *************\n\n"
							+ "Alt+1: Cadastro de Fornecedores;\n"
							+ "Alt+2: Cadastro de Mesas;\n"
							+ "Alt+3: Cadastro de Ingredientes;\n"
							+ "Alt+4: Cadastro de Funcionários;\n"
							+ "Alt+5: Cadastro de Receitas.");
					break;
				case 6:
					resposta.setText("42");
					break;
				}
			}
		});
		//mudei aqui també, git
		listaPerguntas.add("Por que preciso selecionar a data manualmente?");
		listaPerguntas.add("Como vejo os dados de um um fornecedor, funcionario, item etc. cadastrado?");
		listaPerguntas.add("Por que devo fechar uma mesa?");
		listaPerguntas.add("Como atualizo minha lista de cadastros em qualquer uma das telas de cadastro?");
		listaPerguntas.add("Como atualizo o estoque de um ingrediente?");
		listaPerguntas.add("Quais teclas de atalho posso usar no meu principal para rapidamente abrir outras telas?");
		listaPerguntas.add("Qual o sentido da vida, do universo e tudo mais?");
		listaPerguntas.select(0);
		listaPerguntas.setBounds(10, 10, 560, 220);
		panel.add(listaPerguntas);
		

	}
	
	private void fechar() {
		dispose();
	}
}
