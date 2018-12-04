package pizzaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Fornecedor {

	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	private static ArrayList<String> fornecedores = new ArrayList<>();

	public Fornecedor() throws IOException {
		this.nome = "Não informado";
		this.cnpj = "XX.XXX.XXX/XXXX-XX";
		this.telefone = "(XX) XXXX-XXXX";
		this.email = "nãoinformado@email.com";
		fornecedores.clear();
		fornecedores = new Arquivo("fornecedores").lerArquivoLeitura();
	}

	public Fornecedor(String nome, String cnpj, String telefone, String email) throws IOException {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		fornecedores.clear();
		fornecedores = new Arquivo("fornecedores").lerArquivoLeitura();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static ArrayList<String> getFornecedores() {
		return fornecedores;
	}

	public static void setFornecedores(ArrayList<String> fornecedores) {
		Fornecedor.fornecedores = fornecedores;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void cadastrar() throws IOException {

		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("fornecedores");

		// Limpando todos os dados do ArrayList
		fornecedores.clear();

		// Adicionando os dados do arquivo
		fornecedores = arquivo.lerArquivoLeitura();

		// Formatando as informações
		String dados = String.format("%s;%s;%s;%s", this.cnpj, this.nome, this.telefone, this.email);

		boolean encontrado = false;
		// Verificando se o fornecedor já está cadastrado
		for (int i = 0; i < fornecedores.size(); i++) {
			if (new StringTokenizer(fornecedores.get(i)).nextToken(";").equals(this.cnpj)) {
				// Caso esteja, atualiza seus dados
				fornecedores.set(i, dados);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			// Se não estiver, atualiza o ArrayList com o novo dado
			fornecedores.add(dados);
		}

		// Pondo os novos dados no arquivo
		for (String fornecedor : fornecedores) {
			arquivo.gravarDados(fornecedor + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();

	}

	public static void remover(String cnpj) throws IOException {
		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("fornecedores");

		// Limpando todos os dados do ArrayList
		fornecedores.clear();

		// Adicionando os dados do arquivo
		fornecedores = arquivo.lerArquivoLeitura();

		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < fornecedores.size(); i++) {
			st = new StringTokenizer(fornecedores.get(i));
			if (st.nextToken(";").equals(cnpj)) {
				String[] escolha = { "Sim", "Não" };
				if (JOptionPane.showOptionDialog(null,
						"Deseja realmente remover o fornecedor " + st.nextToken(";") + "?", "Confirmação",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[1]) == 0) {
					fornecedores.remove(i);
					JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso.", "Remover",
							JOptionPane.INFORMATION_MESSAGE);
				}

				break;
			}
		}

		// Pondo os novos dados no arquivo
		for (String fornecedor : fornecedores) {
			arquivo.gravarDados(fornecedor + "%n");
		}

		// Fechando o arquivo
		arquivo.fecharArquivoGravacao();
	}

	public static String exibirDados(String cnpj) throws IOException {
		String dados = "";

		// Criando o novo objeto Arquivo
		Arquivo arquivo = new Arquivo("fornecedores");

		// Limpando todos os dados do ArrayList
		fornecedores.clear();

		// Adicionando os dados do arquivo
		fornecedores = arquivo.lerArquivoLeitura();

		boolean encontrado = false;
		
		// Pesquisando o fornecedor requisitado
		StringTokenizer st;
		for (int i = 0; i < fornecedores.size(); i++) {
			st = new StringTokenizer(fornecedores.get(i));
			dados = st.nextToken(";");
			if (dados.equals(cnpj)) {
				dados = "Cnpj: " + dados + "\n" + "Empresa: " + st.nextToken(";") + "\n" + "Telefone: "
						+ st.nextToken(";") + "\n" + "E-mail: " + st.nextToken(";");
				
				encontrado = true;
				break;
			}
		}
		
		if (!encontrado) {
			dados = "Nenhum fornecedor encontrado";
		}
		
		return dados;
	}

}
