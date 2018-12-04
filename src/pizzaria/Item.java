package pizzaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Item {

	private static int ID_item;
	private String fornecedor;
	private String nome;
	private int quantidade;
	private static ArrayList<String> itens = new ArrayList<>();

	public Item() throws IOException {
		this.fornecedor = "Não informado";
		this.nome = "Não informado";
		this.quantidade = 0;
		itens.clear();
		itens = new Arquivo("itens").lerArquivoLeitura();
	}

	public Item(String fornecedor, String nome, int quantidade) throws IOException {
		super();
		this.fornecedor = fornecedor;
		this.nome = nome;
		this.quantidade = quantidade;
		itens.clear();
		itens = new Arquivo("itens").lerArquivoLeitura();
	}

	public static int getID_item() {
		return ID_item;
	}

	public String getForncedor() {
		return fornecedor;
	}

	public void setForncedor(String forncedor) {
		this.fornecedor = forncedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public static ArrayList<String> getItens() {
		return itens;
	}

	public static void setItens(ArrayList<String> itens) {
		Item.itens = itens;
	}

	public void cadastrar() throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("itens");

		// Limpando todos os dados do ArrayList
		itens.clear();

		// Adicionando os dados do arquivo
		itens = arquivo.lerArquivoLeitura();

		// Formatando as informações
		String dados = String.format("%s;%03d;%s;", this.nome, this.quantidade, this.fornecedor);

		boolean encontrado = false;
		// Verificando se o fornecedor já está cadastrado
		for (int i = 0; i < itens.size(); i++) {
			StringTokenizer st = new StringTokenizer(itens.get(i));
			st.nextToken(";");
			if (st.nextToken(";").equals(this.nome)) {
				// Caso esteja, atualiza seus dados
				itens.set(i, itens.get(i).substring(0, 5)+ dados);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			// Se não estiver, atualiza o ArrayList com o novo dado

			// Atualizando o ID
			try {
				ID_item = Integer.parseInt(itens.get(itens.size() - 1).substring(0, 4));
			} catch (ArrayIndexOutOfBoundsException a) {
				ID_item = 0;
			}
			
			ID_item++;

			dados = String.format("%04d;%s", ID_item, dados);

			itens.add(dados);
		}

		// Pondo os novos dados no arquivo
		for (String item : itens) {
			arquivo.gravarDados(item + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}

	public static void remover(String id) throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("itens");

		// Limpando todos os dados do ArrayList
		itens.clear();

		// Adicionando os dados do arquivo
		itens = arquivo.lerArquivoLeitura();

		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < itens.size(); i++) {
			st = new StringTokenizer(itens.get(i));
			if (st.nextToken(";").equals(id)) {
				String[] escolha = { "Sim", "Não" };
				if (JOptionPane.showOptionDialog(null, "Deseja realmente remover o item " + st.nextToken(";") + "?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha,
						escolha[1]) == 0) {
					itens.remove(itens.get(i));
					JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso.", "Remover",
							JOptionPane.INFORMATION_MESSAGE);
				}

				break;
			}
		}

		// Pondo os novos dados no arquivo
		for (String item : itens) {
			arquivo.gravarDados(item + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}

	public static String exibirDados(String id) throws IOException {
		String dados = "";
		
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("itens");

		// Limpando todos os dados do ArrayList
		itens.clear();

		// Adicionando os dados do arquivo
		itens = arquivo.lerArquivoLeitura();
		
		boolean encontrado = false;
		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < itens.size(); i++) {
			st = new StringTokenizer(itens.get(i));
			dados = st.nextToken(";");
			if (dados.equals(id)) {
				dados = st.nextToken(";").toUpperCase()+"\n\n"+
						"ID: "+dados+"\n"+
						"Quantidade: "+st.nextToken(";")+"\n"+
						"Fornecedor: "+st.nextToken(";");
				
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			dados = "Nenhum item encontrado";
		}
		
		return dados;
	}
}
