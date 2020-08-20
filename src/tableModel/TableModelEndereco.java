
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Endereco;

/**
 *
 * @author Aragao
 */
public class TableModelEndereco extends AbstractTableModel{
    private List<Endereco> enderecos = new ArrayList<>();
    private String[] colunas = {"Id","Tipo","Nome","Número", "Bairro", "Cidade", "Estado", "Complemento"};

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public int getRowCount() {
        return enderecos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: enderecos.get(linha).getId_end();
            case 1: enderecos.get(linha).getTipo();
            case 2: enderecos.get(linha).getNome();
            case 3: enderecos.get(linha).getNumero();
            case 4: enderecos.get(linha).getBairro();
            case 5: enderecos.get(linha).getCidade();
            case 6: enderecos.get(linha).getEstado();
            case 7: enderecos.get(linha).getComplemento();
            
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void addRow(Endereco e){
        this.enderecos.add(e);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.enderecos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
