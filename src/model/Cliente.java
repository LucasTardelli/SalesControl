
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Aragao
 */
public class Cliente {
    private int cod_cliente;
    private String nome;
    private Date Dtnascimento;
    private int idade;
    private String cpf;
    private String genero;
    private Date dataDeCad;
    private Endereco endereco;
    private CliSituacao situacao;
    private CliTel telefones;
    private Date agora = new Date();
    
    
    public int getCalcIdade(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] datan=sdf.format(this.Dtnascimento).split("/");
        LocalDate nasci = LocalDate.of(Integer.parseInt(datan[2]), Integer.parseInt(datan[1]), Integer.parseInt(datan[0]));
//        LocalDate agr = LocalDate.ofEpochDay(agora.getTime());
        LocalDate agr = LocalDate.now();
        System.out.println(agr);
        Period periodo = Period.between(nasci, agr);
        return periodo.getYears();
    }
    
    public String getCadString(){
        SimpleDateFormat sdfCad = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdfCad.format(this.dataDeCad);
    }
    public void setCadString(String dtCad){
        SimpleDateFormat sdfcad = new SimpleDateFormat("dd/MM/yyyy HH");
    }
    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public Date getDtnascimento() {
        return Dtnascimento;
    }

    public void setDtnascimento(Date Dtnascimento) {
        this.Dtnascimento = Dtnascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public Date getDataDeCad() {
        return dataDeCad;
    }

    public void setDataDeCad(String dataDeCad) {
        SimpleDateFormat sdfcad = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.dataDeCad = sdfcad.parse(dataDeCad);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na Conversão da data de cadastro!", JOptionPane.ERROR_MESSAGE);
        }
         
    }

    public CliSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(CliSituacao situacao) {
        this.situacao = situacao;
    }

    public CliTel getTelefones() {
        return telefones;
    }

    public void setTelefones(CliTel telefones) {
        this.telefones = telefones;
    }
    
}