
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CliSituacao;

/**
 *
 * @author Aragao
 */
public class CliSituacaoDAO {
    public CliSituacao procuraKeySituacao(int id){
        int keySituacao = 0;
        CliSituacao cs=null;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "SELECT * FROM cli_situacao WHERE cod_sit=?";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                cs = new CliSituacao();
                cs.setCod_sit(rs.getInt("cod_sit"));
                cs.setSituacao(rs.getString("situacao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Inserção!Endereco", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                pstmt.close();
                conexao.getConexao().close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return cs;
    }
    
    public int procuraSituacao(String sit){
        int keySituacao = 0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "SELECT * FROM cli_situacao WHERE situacao=?";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, sit);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Inserção!Endereco", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                pstmt.close();
                conexao.getConexao().close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return keySituacao;
    }
    
    public List<CliSituacao> todasSituacoes(){
        List<CliSituacao> situacoes= new ArrayList<>();
        CliSituacao cs = new CliSituacao();
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        String sql = "SELECT * FROM cli_situacao";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                cs.setCod_sit(rs.getInt("cod_sit"));
                cs.setSituacao(rs.getString("situacao"));
                situacoes.add(cs);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Inserção!Endereco", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                pstmt.close();
                conexao.getConexao().close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return situacoes;
    }
}
