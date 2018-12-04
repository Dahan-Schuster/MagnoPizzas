package pizzaria;

import java.io.IOException;
import java.util.ArrayList;

public class Presencial extends Pedidos {

	private int mesa;
	private static ArrayList<String> pedidosPresenciais = new ArrayList<>();

	public Presencial() throws IOException {
		super();
		this.mesa = 0;

		pedidosPresenciais.clear();
		pedidosPresenciais = new Arquivo("pedidosPresenciais").lerArquivoLeitura();
	}

	public Presencial(ArrayList<String> receita, float preco, String funcionario, String data, int mesa)
			throws IOException {
		super(receita, preco, funcionario, data);
		// TODO Auto-generated constructor stub
		this.mesa = mesa;
		pedidosPresenciais.clear();
		pedidosPresenciais = new Arquivo("pedidosPresenciais").lerArquivoLeitura();
	}

	/**
	 * @return the mesa
	 */
	public int getMesa() {
		return mesa;
	}

	/**
	 * @param mesa the mesa to set
	 */
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	public void gerarComprovante(int id) {
		// TODO Auto-generated method stub

	}

	public static ArrayList<String> getPedidosPresenciais() {
		return pedidosPresenciais;
	}

	public static void setPedidosPresenciais(ArrayList<String> pedidosPresenciais) {
		Presencial.pedidosPresenciais = pedidosPresenciais;
	}

	@Override
	public void realizarPedido() throws IOException{
		Arquivo arquivo;
		try {
			arquivo = new Arquivo("pedidosPresenciais");

			pedidosPresenciais.clear();
			pedidosPresenciais = arquivo.lerArquivoLeitura();

			String dados = String.format("%s;%s;%02d;%s;%5.2f;", this.getData(), this.getHora(), this.getMesa(),
					this.getFuncionario(), this.getPreco());

			for (String receita : this.getReceita()) {
				dados = dados +"@"+ receita;
			}

			pedidosPresenciais.add(dados);

			for (String pedido : pedidosPresenciais) {
				arquivo.gravarDados(pedido + "%n");
			}
			
			arquivo.fecharArquivoGravacao();
			
			new Mesa().adicionarPedidos(this.getMesa(), dados, this.getPreco());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			


	}

	@Override
	public void exibirDados(int id) {
		// TODO Auto-generated method stub

	}

}
