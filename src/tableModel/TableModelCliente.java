
package tableModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.CliTel;
import model.Cliente;

/**
 *
 * @author Aragao
 */
public class TableModelCliente extends AbstractTableModel{
    private List<Cliente> clientes = new ArrayList<>();
    private List<CliTel> telefones = new ArrayList<>();
    private String[] colunas = {"Id","Nome", "Idade", "CPF", "Genero", "Telefone1","Telefone2", "Cliente desde","Situação"};
    Date hoje = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    
    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: {return clientes.get(linha).getCod_cliente();}
            case 1: {return clientes.get(linha).getNome();}
            case 2: {return clientes.get(linha).getCalcIdade();}
            case 3: {return clientes.get(linha).getCpf();}
            case 4: {return clientes.get(linha).getGenero();}
            case 5: {return clientes.get(linha).getTelefones().getTel(0);}
            case 6: {return clientes.get(linha).getTelefones().getTel(1);}
            case 7: {return clientes.get(linha).getCadString();}
            case 8: {return clientes.get(linha).getSituacao().getSituacao();}
        }
        return null;
    }
//String colus = {"Id","Nome", "Idade", "CPF", "Genero", "Telefone1","Telefone2", "Cliente desde","Situação"};
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        switch(coluna){
            case 0: {
                clientes.get(linha).setNome(String.valueOf(valor));
            }
            case 1: {
                
                //clientes.get(linha).setDataDeCad(dt = sdf.parse(String.valueOf(valor)));
            }
            case 2: {
                clientes.get(linha).setCpf(String.valueOf(valor));
            }
            case 3: {
                clientes.get(linha).setGenero(String.valueOf(valor));
            }
        }
    }
        
    public void addRow(Cliente c){
        this.clientes.add(c);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.clientes.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes=clientes;
    }

    public List<CliTel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<CliTel> telefones) {
        this.telefones = telefones;
    }
    
}
