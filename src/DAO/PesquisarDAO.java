/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.Conexao;
import model.Produto;
import model.TipoProduto;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Aragao
 */
public class PesquisarDAO {
    public Produto PesquisarNome(String nome){//(String nome, Tipo tipo)
        Produto produto=null;
        Conexao conexao = new Conexao();
        TipoProduto tipo;
        String sql = "SELECT * FROM produto WHERE nome_prod = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
        
        pstmt.setString(1, nome);
        ResultSet rs = pstmt.executeQuery();
        produto = null;
        if(rs.next()){
            produto = new Produto();
            tipo=new TipoProduto();
            produto.setCodProduto(rs.getInt("cod_prod"));
            produto.setNomeProduto(rs.getString("nome_prod"));
            //produto.setTipoProduto(PesquisarTipo(rs.getInt("fk_cod_tipo")));
            produto.setValorAtacado(rs.getDouble("preco_atacado"));
            produto.setValorVarejo(rs.getDouble("preco_varejo"));
            
            
        }
        pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Inserção!Endereco", JOptionPane.ERROR_MESSAGE);
        }
        return produto;
    }
    
    public String PesquisarTipo(int codTipo){
        String tp=null;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt;
        try {
            pstmt = conexao.getConexao().prepareStatement("SELECT * FROM tipoprod WHERE cod_tipo=?");
        
        pstmt.setInt(1, codTipo);
        ResultSet rs = pstmt.executeQuery();
        tp=null;
        if(rs.next()){
            tp = new String();
            tp=(rs.getString("tipo"));
        }
    
        pstmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Inserção!Endereco", JOptionPane.ERROR_MESSAGE);
        }
        return tp;
    }
    
}
