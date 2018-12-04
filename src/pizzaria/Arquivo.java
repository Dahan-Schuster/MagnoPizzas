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
       
        
        //Inst�ncia dos novos objetos de gravacao
        this.setArqGravacaoFisico(new FileWriter(this.getNomeArquivo())); //F�sico
        this.setArqGravacaoLogico(new PrintWriter(arqGravacaoFisico)); //L�gico
        
        //Definindo o estado do arquivo como Aberto
        this.setArquivoAberto(true);
    }
    
    
    public void gravarDados(String dados) throws IOException {
        //Verifica se o arquivo j� est� aberto
        if (!this.isArquivoAberto()) {
            this.abrirArquivoGravacao(); //Abre o arquivo se este estiver fechado
        }
        //Por fim, tendo verificado o estado do arquivo, salva os dados recebidos no arquivo de grava��o l�gico
        this.arqGravacaoLogico.printf(dados);
    }
    
    
    public void fecharArquivoGravacao(){
    	if (this.isArquivoAberto()) {
            //Fecha o arquivo de grava��o l�gico
            this.arqGravacaoLogico.close();
            //Atualiza o estado Aberto para falso
            this.setArquivoAberto(false);
    	}
    }
    
    
    public ArrayList<String> lerArquivoLeitura() {
        
        String linha; //Vari�vel para receber uma linha do arquivo
        ArrayList<String> dados = new ArrayList<>(); //ArrayList para armazenar todas as linhas lidas
        try {        
            //Inst�ncia dos novos objetos de leitura
            this.setArqLeituraFisico(new FileReader(this.getNomeArquivo())); // F�sico
            this.setArqLeituraLogico(new BufferedReader(arqLeituraFisico)); // L�gico
            
            //Ler os dados e salvar no ArrayList<>
            linha = this.arqLeituraLogico.readLine();
            while (linha != null) { //Continua a salvar no arquivo enquanto a linha n�o receber um valor nulo
                dados.add(linha); // Adiciona uma linha no ArrayList<>
                linha = this.arqLeituraLogico.readLine(); //L� a pr�xima linha
            }
            
            //Fecha o arquivo de leitura l�gico
            this.arqLeituraLogico.close();
            
            //Retorna o ArrayList<>
            return dados;
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo n�o encontrado.");
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dados;
    }
    
    
}
