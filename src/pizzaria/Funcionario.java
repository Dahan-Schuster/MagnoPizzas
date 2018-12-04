package pizzaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Funcionario {

	private String nome;
	private String cpf;
	private String telefone;
	private String funcao;
	private static ArrayList<String> funcionarios = new ArrayList<>();
	
	public Funcionario() throws IOException {
		this.nome = "Não informado.";
		this.cpf = "XXX.XXX.XXX-XX";
		this.telefone = "(XX) XXXX-XXXX";
		this.funcao = "Não informado.";
		funcionarios.clear();
		funcionarios = new Arquivo("funcionarios").lerArquivoLeitura();
	}
	
	public Funcionario(String nome, String cpf, String telefone, String funcao) throws IOException {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.funcao = funcao;
		funcionarios.clear();
		funcionarios = new Arquivo("funcionarios").lerArquivoLeitura();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public static ArrayList<String> getFuncionarios() {
		return funcionarios;
	}



	public static void setFuncionarios(ArrayList<String> funcionarios) {
		Funcionario.funcionarios = funcionarios;
	}



	public void cadastrar() throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("funcionarios");

		// Limpando todos os dados do ArrayList
		funcionarios.clear();

		// Adicionando os dados do arquivo
		funcionarios = arquivo.lerArquivoLeitura();

		// Formatando as informações
		String dados = String.format("%s;%s;%s;%s", this.cpf, this.nome, this.telefone, this.funcao);
		
		boolean encontrado = false;
		// Verificando se o fornecedor já está cadastrado
		for (int i = 0; i < funcionarios.size(); i++) {
			if (new StringTokenizer(funcionarios.get(i)).nextToken(";").equals(this.cpf)) {
				// Caso esteja, atualiza seus dados
				funcionarios.set(i, dados);
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			// Se não estiver, atualiza o ArrayList com o novo dado
			funcionarios.add(dados);
		}
		
		// Pondo os novos dados no arquivo
		for (String funcionario : funcionarios) {
			arquivo.gravarDados(funcionario+"%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}
	
	public static void remover(String cpf) throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("funcionarios");

		// Limpando todos os dados do ArrayList
		funcionarios.clear();

		// Adicionando os dados do arquivo
		funcionarios = arquivo.lerArquivoLeitura();
		
		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for(int i = 0; i < funcionarios.size(); i++) {
			st = new StringTokenizer(funcionarios.get(i));
			if (st.nextToken(";").equals(cpf)) {
				String[] escolha = {"Sim", "Não"}; 
				if (JOptionPane.showOptionDialog(null, "Deseja realmente remover o fornecedor "+st.nextToken(";")+"?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[1])
					== 0) {
					funcionarios.remove(i);
					JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso.", "Remover", JOptionPane.INFORMATION_MESSAGE);
				}
				
				break;
			}
		}
		
		// Pondo os novos dados no arquivo
		for (String funcionario : funcionarios) {
			arquivo.gravarDados(funcionario + "%n");
			// Fechando o arquivo
			arquivo.fecharArquivoGravacao();
		}

	}
	
	public static String exibirDados(String cpf)  throws IOException{
		String dados = "";
		
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("funcionarios");

		// Limpando todos os dados do ArrayList
		funcionarios.clear();

		// Adicionando os dados do arquivo
		funcionarios = arquivo.lerArquivoLeitura();

		boolean encontrado = false;
		
		// Pesquisando o funcionario requisitado
		StringTokenizer st;
		for (int i = 0; i < funcionarios.size(); i++) {
			st = new StringTokenizer(funcionarios.get(i));
			dados = st.nextToken(";");
			if (dados.equals(cpf)) {
				dados = "Cpf: " + dados + "\n" + "Nome: " + st.nextToken(";") + "\n" + "Telefone: "
						+ st.nextToken(";") + "\n" + "Funcao: ";
				
				String funcao =  st.nextToken(";");
				funcao = funcao.charAt(0) + funcao.substring(1).toLowerCase();
				dados = dados + funcao;
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			dados = "Nenhum funcionario encontrado";
		}
		
		return dados;
	}

}