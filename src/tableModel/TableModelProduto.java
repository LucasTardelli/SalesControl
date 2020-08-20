/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produto;
import model.ProdutoMarca;
import model.TipoProduto;

/**
 *
 * @author Aragao
 */
public class TableModelProduto extends AbstractTableModel{
    private List<Produto> produtos = new ArrayList<>();
    private String[] colunas={"id","Produto","Marca","Tipo","Vence em", "Valor Atacado", "Valor Varejo", "Ganho(%)", "QTD"};
    
    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: {
                return produtos.get(linha).getCodProduto();
            }
            case 1: {
                return produtos.get(linha).getNomeProduto();
            }
            case 2: {
                return produtos.get(linha).getMarca().getNomeMarca();
            }
            case 3: {
                return produtos.get(linha).getTipoProduto().getTipo();
            }
            case 4: {
                return produtos.get(linha).getDiasPraVencer();
            }
            case 5: {
                return produtos.get(linha).getValorAtacado();
            }
            case 6: {
                return produtos.get(linha).getValorVarejo();
            }
            case 7: {
                return produtos.get(linha).calculaGanho();
            }
            case 8: {
                return produtos.get(linha).getQuantidade();
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date dt = sdf.parse();
        ProdutoMarca pm = new ProdutoMarca();
        TipoProduto tp = new TipoProduto();
        switch(coluna){
            case 0: {
                produtos.get(linha).setCodProduto(Integer.valueOf(String.valueOf(valor)));
            }
            case 1: {
                produtos.get(linha).setNomeProduto(String.valueOf(valor));
            }
            case 2: {
                pm.setNomeMarca(String.valueOf(valor));
                produtos.get(linha).setMarca(pm);
            }
            case 3: {
                tp.setTipo(String.valueOf(valor));
                produtos.get(linha).setTipoProduto(tp);
            }
            case 4: {
                produtos.get(linha).setDiasPraVencer(Integer.valueOf(String.valueOf(valor)));
            }
            case 5: {
                produtos.get(linha).setValorAtacado(Double.parseDouble(String.valueOf(valor)));
            }
            case 6: {
                produtos.get(linha).setValorVarejo(Double.parseDouble(String.valueOf(valor)));
            }
            case 7: {
                produtos.get(linha).setGanho(Float.parseFloat(String.valueOf(valor)));
            }
            case 8: {
                produtos.get(linha).setQuantidade(Integer.valueOf(String.valueOf(valor)));
            }
        }
    }
    
    public void addRow(Produto p){
        this.produtos.add(p);
        this.fireTableDataChanged();
    }
    public void removeRow(int linha){
        this.produtos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos=produtos;
    }
    
}
