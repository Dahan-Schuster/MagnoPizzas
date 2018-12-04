package pizzaria;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * Classe Pedidos para realizar funções referentes ao pedido, como
 * gerar comprovante, realizar pedido etc.
 */
public abstract class Pedidos {
	
	private ArrayList<String> receita;
	private float preco;
	private String funcionario;
	private String hora;
	private String data;
	
	public Pedidos() {
		this.receita = new ArrayList<>();
		this.preco = (float) 0.0;
		this.funcionario = "Não informado";
		this.hora = "Não informada";
		this.data = "Não informada";
	}

	public Pedidos(ArrayList<String> receita, float preco, String funcionario, String data) {
		super();
		this.receita = receita;
		this.preco = preco;
		this.funcionario = funcionario;
		this.data = data;
		this.hora = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}

	public ArrayList<String> getReceita() {
		return receita;
	}

	public void setReceita(ArrayList<String> receita) {
		this.receita = receita;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	public abstract void realizarPedido() throws IOException;
	
	public abstract void exibirDados(int id);
	
	

}

