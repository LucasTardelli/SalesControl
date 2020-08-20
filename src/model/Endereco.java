
package model;

import java.util.Comparator;

/**
 *
 * @author Aragao
 */
public class Endereco implements Comparator<Endereco> {
    private int id_end;
    private String tipo;
    private String nome;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    @Override
    public int compare(Endereco e1, Endereco e2) {
        if(e1.id_end < e2.id_end){
            return  -1;
        }
        if(e2.id_end < e1.id_end){
            return 1;
        }
        return 0;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getId_end() {
        return id_end;
    }

    public void setId_end(int id_end) {
        this.id_end = id_end;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return tipo + ": " + nome + ", n: " + numero + ", complemento: " + complemento + ", Bairro: " + bairro + ", Cidade:" + cidade + " Estado: " + estado;
    }
    
}
