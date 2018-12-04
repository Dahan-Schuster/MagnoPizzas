package pizzaria;

import java.io.IOException;
import java.util.ArrayList;

public class Delivery extends Pedidos {

	private String endereco;
	private String bairro;
	private String referencia;
	private ArrayList<String> pedidosRealizados;
	private static ArrayList<String> pedidosDelivery = new ArrayList<>();

	public Delivery(ArrayList<String> receitas, float preco, String funcionario, String data, String endereco,
			String bairro, String referencia) throws IOException {
		super(receitas, preco, funcionario, data);
		// TODO Auto-generated constructor stub
		this.endereco = endereco;
		this.pedidosRealizados = receitas;
		this.bairro = bairro;
		this.referencia = referencia;
		pedidosDelivery.clear();
		pedidosDelivery = new Arquivo("pedidosDelivery").lerArquivoLeitura();

	}

	public Delivery() throws IOException {
		super();
		this.pedidosRealizados = new ArrayList<>();
		this.endereco = "N�o informado";
		this.bairro = "N�o informado";
		this.referencia = "N�o informado";
		pedidosDelivery.clear();
		pedidosDelivery = new Arquivo("pedidosDelivery").lerArquivoLeitura();
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void gerarComprovante() {
		// TODO Auto-generated method stub

	}

	public static ArrayList<String> getPedidosDelivery() {
		return pedidosDelivery;
	}

	public static void setPedidosDelivery(ArrayList<String> pedidosDelivery) {
		Delivery.pedidosDelivery = pedidosDelivery;
	}

	@Override
	public void realizarPedido() throws IOException {
		// TODO Auto-generated method stub
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("pedidosDelivery");

		// Limpando todos os dados do ArrayList
		pedidosDelivery.clear();

		// Adicionando os dados do arquivo
		pedidosDelivery = arquivo.lerArquivoLeitura();

		// Formatando as informa��es

		String dados = String.format("%s;%s;%s;%s;%s;%s;%5.2f;", this.getData(), this.getHora(), this.endereco, this.bairro,
				this.referencia, this.getFuncionario(), this.getPreco());
		for (String pedido : this.pedidosRealizados) {
			dados = dados +"@"+ pedido;

		}

		pedidosDelivery.add(dados);
		// Pondo os novos dados no arquivo
		for (String pedidos : pedidosDelivery) {

			arquivo.gravarDados(pedidos + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();

	}

	@Override
	public void exibirDados(int id) {
		// TODO Auto-generated method stub

	}

}
