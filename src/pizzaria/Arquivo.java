package pizzaria;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Arquivo extends File{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeArquivo;
    private FileWriter arqGravacaoFisico;
    private PrintWriter arqGravacaoLogico;
    private FileReader arqLeituraFisico;
    private BufferedReader arqLeituraLogico;
    private boolean arquivoAberto;
    
    //Contrutor
     public Arquivo(String nome) throws IOException { 
    	super(nome+".txt");
        nomeArquivo = nome+".txt";
        if (!this.exists()) {
        	createNewFile();
        }
     }

     //Getters e Setters
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public FileWriter getArqGravacaoFisico() {
        return arqGravacaoFisico;
    }

    public void setArqGravacaoFisico(FileWriter arqGravacaoFisico) {
        this.arqGravacaoFisico = arqGravacaoFisico;
    }

    public PrintWriter getArqGravacaoLogico() {
        return arqGravacaoLogico;
    }

    public void setArqGravacaoLogico(PrintWriter arqGravacaoLogico) {
        this.arqGravacaoLogico = arqGravacaoLogico;
    }

    public FileReader getArqLeituraFisico() {
        return arqLeituraFisico;
    }

    public void setArqLeituraFisico(FileReader arqLeituraFisica) {
        this.arqLeituraFisico = arqLeituraFisica;
    }

    public BufferedReader getArqLeituraLogico() {
        return arqLeituraLogico;
    }

    public void setArqLeituraLogico(BufferedReader arqLeituraLogico) {
        this.arqLeituraLogico = arqLeituraLogico;
    }

    public boolean isArquivoAberto() {
        return arquivoAberto;
    }

    public void setArquivoAberto(boolean aberto) {
        this.arquivoAberto = aberto;
    }
    
    
    
    public void abrirArquivoGravacao() throws IOException{
       
        
        //Instância dos novos objetos de gravacao
        this.setArqGravacaoFisico(new FileWriter(this.getNomeArquivo())); //Físico
        this.setArqGravacaoLogico(new PrintWriter(arqGravacaoFisico)); //Lógico
        
        //Definindo o estado do arquivo como Aberto
        this.setArquivoAberto(true);
    }
    
    
    public void gravarDados(String dados) throws IOException {
        //Verifica se o arquivo já está aberto
        if (!this.isArquivoAberto()) {
            this.abrirArquivoGravacao(); //Abre o arquivo se este estiver fechado
        }
        //Por fim, tendo verificado o estado do arquivo, salva os dados recebidos no arquivo de gravação lógico
        this.arqGravacaoLogico.printf(dados);
    }
    
    
    public void fecharArquivoGravacao(){
    	if (this.isArquivoAberto()) {
            //Fecha o arquivo de gravação lógico
            this.arqGravacaoLogico.close();
            //Atualiza o estado Aberto para falso
            this.setArquivoAberto(false);
    	}
    }
    
    
    public ArrayList<String> lerArquivoLeitura() {
        
        String linha; //Variável para receber uma linha do arquivo
        ArrayList<String> dados = new ArrayList<>(); //ArrayList para armazenar todas as linhas lidas
        try {        
            //Instância dos novos objetos de leitura
            this.setArqLeituraFisico(new FileReader(this.getNomeArquivo())); // Físico
            this.setArqLeituraLogico(new BufferedReader(arqLeituraFisico)); // Lógico
            
            //Ler os dados e salvar no ArrayList<>
            linha = this.arqLeituraLogico.readLine();
            while (linha != null) { //Continua a salvar no arquivo enquanto a linha não receber um valor nulo
                dados.add(linha); // Adiciona uma linha no ArrayList<>
                linha = this.arqLeituraLogico.readLine(); //Lê a próxima linha
            }
            
            //Fecha o arquivo de leitura lógico
            this.arqLeituraLogico.close();
            
            //Retorna o ArrayList<>
            return dados;
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado.");
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dados;
    }
    
    
}
