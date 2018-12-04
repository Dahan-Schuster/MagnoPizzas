package pizzaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Mesa {
	private int numero;
	private int qteLugares;
	private ArrayList<String> pedidos;
	private boolean fechada;
	private float conta;
	private static ArrayList<String> mesas = new ArrayList<>();

	public Mesa() throws IOException {
		this.numero = 0;
		this.qteLugares = 0;
		this.pedidos = new ArrayList<String>();
		this.fechada = true;
		this.conta = (float) 0.0;
		mesas.clear();
		mesas = new Arquivo("mesas").lerArquivoLeitura();
	}

	public Mesa(int numero, int qteLugares) throws IOException {
		super();
		this.numero = numero;
		this.qteLugares = qteLugares;
		this.pedidos = new ArrayList<String>();
		this.fechada = true;
		this.conta = (float) 0.0;
		mesas.clear();
		mesas = new Arquivo("mesas").lerArquivoLeitura();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getQteLugares() {
		return qteLugares;
	}

	public void setQteLugares(int qteLugares) {
		this.qteLugares = qteLugares;
	}

	public ArrayList<String> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<String> pedidos) {
		this.pedidos = pedidos;
	}

	public String isFechada() {
		String situacao;
		if (fechada) {
			situacao = "FECHADA";
		} else {
			situacao = "ABERTA";
		}
		return situacao;
	}

	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}

	public float getConta() {
		return conta;
	}

	public void setConta(float conta) {
		this.conta = conta;
	}

	public static ArrayList<String> getMesas() {
		return mesas;
	}

	public static void setMesas(ArrayList<String> mesas) {
		Mesa.mesas = mesas;
	}

	public static void fecharConta(int mesa) throws IOException {
		Arquivo arquivo = new Arquivo("mesas");
		
		mesas.clear();
		mesas = arquivo.lerArquivoLeitura();
		
		StringTokenizer st = new StringTokenizer(mesas.get(mesa), ";");
		String num = st.nextToken();
		String lugares = st.nextToken();
		
		mesas.set(mesa, num+";"+lugares+";FECHADA; 00.00;");
		
		for (String mesinhas : mesas) {
			arquivo.gravarDados(mesinhas + "%n");
		}
		
		arquivo.fecharArquivoGravacao();
	}

	public void adicionarPedidos(int mesa, String pedido, float preco) throws IOException{
		Arquivo arquivo = new Arquivo("mesas");
		
		mesas.clear();
		mesas = arquivo.lerArquivoLeitura();
		
		StringTokenizer st;
		for (int i = 0; i < mesas.size(); i++) {
			if (mesa == Integer.parseInt(mesas.get(i).substring(0, 2))) {
				st = new StringTokenizer(mesas.get(i), ";");
				String id = st.nextToken();
				String assentos = st.nextToken();
				String situacao = st.nextToken();
				float conta = Float.parseFloat(st.nextToken()) + preco;
				
				if (situacao.equals(" ABERTA")) {
					st = new StringTokenizer(mesas.get(i), "@");
					st.nextToken();
					String pedidosAnteriores="";
					while (st.hasMoreTokens()) {
						pedidosAnteriores = st.nextToken() + "@";
					}
					
					mesas.set(i, String.format("%s;%s;%s;%5.2f;@%s%s", id, assentos, situacao, conta, pedidosAnteriores, pedido));
				} else {
					mesas.set(i, String.format("%s;%s; ABERTA;%5.2f;@%s", id, assentos, conta, pedido));
				}
				break;
			}

		}
		
		// Pondo os novos dados no arquivo
		for (String mesinha : mesas) {
			arquivo.gravarDados(mesinha + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
		
		
	}

	public void cadastrar() throws IOException {

		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("mesas");

		// Limpando todos os dados do ArrayList
		mesas.clear();

		// Adicionando os dados do arquivo
		mesas = arquivo.lerArquivoLeitura();

		// Formatando as informa��es
		String dados = String.format("%02d;%02d;%8s;%5.2f;", this.numero, this.qteLugares, this.isFechada(), this.conta);

		for (String pedido : this.pedidos) {
			dados = dados + pedido + ";";
		}

		boolean encontrado = false;
		// Verificando se a mesa j� est� cadastrada
		for (int i = 0; i < mesas.size(); i++) {
			if (Integer.parseInt(new StringTokenizer(mesas.get(i)).nextToken(";")) == this.numero) {
				// Caso esteja, atualiza seus dados
				mesas.set(i, dados);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			// Se n�o estiver, atualiza o ArrayList com o novo dado
			mesas.add(dados);
		}

		// Pondo os novos dados no arquivo
		for (String mesa : mesas) {
			arquivo.gravarDados(mesa + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();

	}

	public void remover(String mesa) {
		
	}
	
}
