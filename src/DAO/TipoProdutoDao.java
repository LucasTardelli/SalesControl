
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.TipoProduto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aragao
 */
public class TipoProdutoDao {
    public int adicionaTipoProduto(TipoProduto tp){//adiciona TipoProduto tp
        int keyTipo=0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "INSERT INTO tipoprod (tipo) VALUES (?)";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, tp.getTipo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            keyTipo = rs.getInt(1);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return keyTipo;
    }
    
    public List<TipoProduto> pesquisaTipoProduto(){
        List<TipoProduto> tiposProds = new ArrayList<>();
        TipoProduto tp =null;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt= null;
        String sql = "SELECT * FROM tipoprod";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                tp = new TipoProduto();
                tp.setCod_tipo(rs.getInt("cod_tipo"));
                tp.setTipo(rs.getString("tipo"));
                tiposProds.add(tp);
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return tiposProds;
    }
    
    public int atualizarTipoProd(TipoProduto tp){
        int atualizou = 0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "UPDATE tipoprod SET tipo = ? WHERE cod_prod=?";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, tp.getTipo());
            pstmt.setInt(2, tp.getCod_tipo());
            atualizou=pstmt.executeUpdate();
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Erro na atualização!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return atualizou;
    }
    
    public int deletarTipo(TipoProduto tp){
        int deletou = 0;
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM tipoprod WHERE cod_cliente=?";
        PreparedStatement pstmt=null;
        try {
            pstmt= conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, tp.getCod_tipo());
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