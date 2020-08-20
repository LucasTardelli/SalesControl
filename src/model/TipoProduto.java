
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Aragao
 */
public class TipoProduto {
    private int cod_tipo;
    private String tipo;
    
    
//    private List<String> tiposDeProdutos=new ArrayList<>();
//
//    
//    public List<String> getTiposDeProdutos() {
//        return tiposDeProdutos;
//    }
//
//    public void setTiposDeProdutos(List<String> tiposDeProdutos) {
//        this.tiposDeProdutos = tiposDeProdutos;
//    }

    public int getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(int cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
	if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TipoProduto other = (TipoProduto)obj;
        if(!this.tipo.equals(other.tipo)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
