/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.Conexao;
import interfaces.ConverterDatas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoMarca;
import model.TipoProduto;

/**
 *
 * @author Aragao
 */
public class ProdutoDAO {
    ConverterDatas cd = new ConverterDatas();
    
    public int adcionarProduto(Produto produto){
        TipoProdutoDao tpd = new TipoProdutoDao();
        int keyProduto=0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "INSERT INTO produto (nome_prod, fk_marca_prod, fk_cod_tipo,"
                + "data_fab, data_venc, preco_varejo, preco_atacado, qtd) VALUES (?,?,?,?,?,?,?,?)";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, produto.getNomeProduto());
            pstmt.setInt(2, produto.getMarca().getCod_marca());
            pstmt.setInt(3, produto.getTipoProduto().getCod_tipo());
            pstmt.setString(4, produto.getsDataFab());
            pstmt.setString(5, produto.getsDataVenc());
            pstmt.setDouble(6, produto.getValorVarejo());
            pstmt.setDouble(7, produto.getValorAtacado());
            pstmt.setInt(8, produto.getQuantidade());
            pstmt.executeUpdate();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, getClass().getName()+e, "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return keyProduto;
    }
    
    public List<Produto> pesquisaProduto(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto = null;
        ProdutoMarca pm = null;
        TipoProduto tp = null;
        Conexao conexao = new Conexao();
        String sql ="SELECT * from produto INNER JOIN marca_prod ON produto.fk_marca_prod = marca_prod.cod_marca"
                                        + " INNER JOIN tipoprod ON produto.fk_cod_tipo = tipoprod.cod_tipo";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                produto = new Produto();
                pm = new ProdutoMarca();
                tp=new TipoProduto();
                
                produto.setCodProduto(rs.getInt("cod_prod"));
                produto.setNomeProduto(rs.getString("nome_prod"));
                pm.setCod_marca(rs.getInt("fk_marca_prod"));
                tp.setCod_tipo(rs.getInt("fk_cod_tipo"));
                produto.setsDataFab(rs.getString("data_fab"));
                produto.setsDataVenc(rs.getString("data_venc"));
                produto.setValorVarejo(rs.getDouble("preco_varejo"));
                produto.setValorAtacado(rs.getDouble("preco_atacado"));
                produto.setQuantidade(rs.getInt("qtd"));
                pm.setNomeMarca(rs.getString("marca_tipo"));
                produto.setMarca(pm);
                tp.setTipo(rs.getString("tipo"));
                produto.setTipoProduto(tp);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Busca!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return produtos;
    }
    
    public int atualizarProduto(Produto produto){
        //retorna o numero de linhas que atualizou
        int atualizou=0;
        ConverterDatas cd = new ConverterDatas();
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "UPDATE produto SET nome_prod = ?, fk_marca_prod = ?, fk_cod_tipo = ?, data_fab = ?,"
                + "data_venc = ?, preco_varejo = ?, preco_atacado = ?, qtd = ? WHERE cod_prod=?";
        try{
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, produto.getNomeProduto());
            pstmt.setInt(2, produto.getMarca().getCod_marca());
            pstmt.setInt(3, produto.getTipoProduto().getCod_tipo());
            pstmt.setDate(4, cd.convertDataSQL(produto.getDataFab()));
            pstmt.setDate(5, cd.convertDataSQL(produto.getDataVencimento()));
            pstmt.setDouble(6, produto.getValorVarejo());
            pstmt.setDouble(7, produto.getValorAtacado());
            pstmt.setInt(8, produto.getQuantidade());
            pstmt.setInt(9, produto.getCodProduto());
            atualizou=pstmt.executeUpdate();
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na atualização!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return atualizou;
    }
    
    public int removerProduto(Produto produto){
        int deletou=0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "DELETE FROM produto WHERE cod_prod=?";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, produto.getCodProduto());
            deletou = pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na remoção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return deletou;
    }
}
