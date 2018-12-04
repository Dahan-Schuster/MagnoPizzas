package pizzaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Receita {

	private String nome;
	private static int ID_receita;
	private ArrayList<String> itens;
	private float[] preco = new float[] { 0, 0, 0, 0, 0, };
	private boolean disponivel;
	private static ArrayList<String> receitas = new ArrayList<>();

	public Receita() throws IOException {
		this.nome = "Não informado";
		this.itens = new ArrayList<>();
		this.disponivel = false;
		receitas.clear();
		receitas = new Arquivo("receitas").lerArquivoLeitura();
	}

	public Receita(String nome, ArrayList<String> itens, float preco1, float preco2, float preco3, float preco4,
			float preco5) throws IOException {
		super();
		this.nome = nome;
		this.itens = itens;
		this.preco[0] = preco1;
		this.preco[1] = preco2;
		this.preco[2] = preco3;
		this.preco[3] = preco4;
		this.preco[4] = preco5;
		receitas.clear();
		receitas = new Arquivo("receitas").lerArquivoLeitura();
		
		// Verificando a disponibilidade da receita
		this.disponivel = true;
		StringTokenizer st;
		for (String item : itens) {
			new Item();
			for (String item2 : Item.getItens()) {
				st = new StringTokenizer(item2);
				if (item.substring(0, 4).equals(st.nextToken(";"))) {
					st.nextToken(";");
					if (Integer.parseInt(st.nextToken(";")) == 0) {
						this.disponivel = false;
						break;
					}
				}
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static int getID_receita() {
		return ID_receita;
	}

	public static void setID_receita(int iD_receita) {
		ID_receita = iD_receita;
	}

	public ArrayList<String> getItens() {
		return itens;
	}

	public void setItens(ArrayList<String> itens) {
		this.itens = itens;
	}

	public float[] getPreco() {
		return preco;
	}

	public void setPreco(float[] preco) {
		this.preco = preco;
	}

	public boolean isDisponivel() {
		return disponivel;
	}
	
	public static String isDisponivel(String id) throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("receitas");

		// Limpando todos os dados do ArrayList
		receitas.clear();

		// Adicionando os dados do arquivo
		receitas = arquivo.lerArquivoLeitura();

		// Pesquisando o fornecedor requisitado
		StringTokenizer st, st2;
		String disponivel = "Sim";
		for (int i = 0; i < receitas.size(); i++) {
			st = new StringTokenizer(receitas.get(i));
			if (st.nextToken(";").equals(id)) {
				st.nextToken(";");
				st.nextToken(";");
				st.nextToken(";");
				st.nextToken(";");
				st.nextToken(";");
				st.nextToken(";");
				st.nextToken(";");
				
				while (st.hasMoreTokens()) {
					String item = st.nextToken(";");
					new Item();
					for (String item2 : Item.getItens()) {
						st2 = new StringTokenizer(item2);
						if (item.equals(st2.nextToken(";"))) {
							st2.nextToken(";");
							if (Integer.parseInt(st2.nextToken(";")) == 0) {
								disponivel = "Não";
								break;
							}
						}
					}
				}
			}
		}
		
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public static ArrayList<String> getReceitas() {
		return receitas;
	}

	public static void setReceitas(ArrayList<String> receitas) {
		Receita.receitas = receitas;
	}

	public void cadastrar() throws IOException {
		Arquivo arquivo = new Arquivo("receitas");

		receitas.clear();

		receitas = arquivo.lerArquivoLeitura();

		// Formatando as informações
		String dados = String.format("%s;%s;%5.2f;%5.2f;%5.2f;%5.2f;%5.2f;", this.nome, this.disponivel,
				this.preco[0], this.preco[1], this.preco[2], this.preco[3], this.preco[4]);
		
		for(String item : this.itens) {
			dados = dados + item.substring(0, 4) + ";";
		}

		boolean encontrado = false;
		// Verificando se a receita já está cadastrada
		for (int i = 0; i < receitas.size(); i++) {
			StringTokenizer st = new StringTokenizer(receitas.get(i));
			st.nextToken(";");
			if (st.nextToken(";").equals(this.nome)) {
				// Caso esteja, atualiza seus dados
				receitas.set(i, receitas.get(i).substring(0, 5)+dados);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			// Se não estiver, atualiza o ArrayList com o novo dado

			// Atualizando o ID
			try {
				ID_receita = Integer.parseInt(receitas.get(receitas.size() - 1).substring(0, 4));
			} catch (ArrayIndexOutOfBoundsException a) {
				ID_receita = 0;
			}
			
			ID_receita++;
			dados = String.format("%04d;%s", ID_receita, dados);

			receitas.add(dados);
		}

		// Pondo os novos dados no arquivo
		for (String receita : receitas) {
			arquivo.gravarDados(receita + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}

	public static void remover(String id) throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("receitas");

		// Limpando todos os dados do ArrayList
		receitas.clear();

		// Adicionando os dados do arquivo
		receitas = arquivo.lerArquivoLeitura();

		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < receitas.size(); i++) {
			st = new StringTokenizer(receitas.get(i));
			if (st.nextToken(";").equals(id)) {
				String[] escolha = { "Sim", "Não" };
				if (JOptionPane.showOptionDialog(null, "Deseja realmente remover a receita " + st.nextToken(";") + "?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha,
						escolha[1]) == 0) {
					receitas.remove(i);
					JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso.", "Remover",
							JOptionPane.INFORMATION_MESSAGE);
				}

				break;
			}
		}

		// Pondo os novos dados no arquivo
		for (int i = 0; i < receitas.size(); i++) {
			arquivo.gravarDados(receitas.get(i) + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}

	public static String exibirDados(String id) throws IOException {
		String dados = "";
		
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("receitas");

		// Limpando todos os dados do ArrayList
		receitas.clear();

		// Adicionando os dados do arquivo
		receitas = arquivo.lerArquivoLeitura();
		
		boolean encontrado = false;
		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < receitas.size(); i++) {
			st = new StringTokenizer(receitas.get(i));
			dados = st.nextToken(";");
			if (dados.equals(id)) {
				dados = st.nextToken(";").toUpperCase()+"\n\n"+
						"ID: "+dados+"\n";
				st.nextToken(";");
				dados = dados +	"Disponível: "+isDisponivel(id)+"\n"+
						"Preço P: R$"+st.nextToken(";")+"\n"+
						"Preço M: R$"+st.nextToken(";")+"\n"+
						"Preço G: R$"+st.nextToken(";")+"\n"+
						"Preço F: R$"+st.nextToken(";")+"\n"+
						"Preço GF: R$"+st.nextToken(";")+"\n\n"+
						"Ingredientes:\n";
				
				int count=0;
				while (st.hasMoreTokens()) {
					count++;
					dados = dados + "  "+count+". "+st.nextToken(";")+"\n"; 
				}
				
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			dados = "Nenhuma receita encontrada";
		}
		
		return dados;
	}

}
