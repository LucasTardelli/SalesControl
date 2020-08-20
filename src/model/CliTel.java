
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aragao
 */
public class CliTel {
    private int keyCli;
    private List<String> telefones = new ArrayList<>();

    public String getTel(int num){
        if(this.keyCli==0){
            return "Sem Telefone";
        }else {
            return telefones.get(num);
        }
    }
    
    //Verifica se o telefone é nulo
    public void addTel(String telef){
        if(!"(  )     -    ".equals(telef)){
            
            this.telefones.add(telef);
        }
    }
    public int getKeyCli() {
        return keyCli;
    }

    public void setKeyCli(int keyCli) {
        this.keyCli = keyCli;
    }
        
    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }
}
