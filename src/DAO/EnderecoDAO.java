
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Endereco;

/**
 *
 * @author Aragao
 */
public class EnderecoDAO {
    public int adicionaEndereco(Endereco endereco) {
        int keyEnd = 0;
        String sql = "INSERT INTO endereco (tipo, n_end, numero, bairro,cidade,estado, end_complemento)"
                + "values (?, ?, ?, ?, ?, ?, ?)";
        Conexao conexao = new Conexao();
        
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, endereco.getTipo());
            pstmt.setString(2,endereco.getNome());
            pstmt.setInt(3,endereco.getNumero());
            pstmt.setString(4,endereco.getBairro());
            pstmt.setString(5,endereco.getCidade());
            pstmt.setString(6,endereco.getEstado());
            pstmt.setString(7,endereco.getComplemento());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                keyEnd= rs.getInt(1);
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
        return keyEnd;
    }
    
    public List<Endereco> pesquisaEndereço(){
        List<Endereco> enderecos= new ArrayList<>();
        Endereco endereco=null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM endereco";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
        
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                endereco = new Endereco();
                endereco.setId_end(rs.getInt("cod_end"));
                endereco.setTipo(rs.getString("tipo"));
                endereco.setNome(rs.getString("n_end"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setComplemento(rs.getString("end_complemento"));
                enderecos.add(endereco);
            }rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na Busca!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                pstmt.close();
                conexao.getConexao().close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Erro ao fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return enderecos;
    }
    
    public Endereco clienteEndereco(int kend){
        Endereco end =null;
        PreparedStatement pstmt=null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM endereco WHERE cod_end = ?";
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, kend);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                end = new Endereco();
                end.setId_end(rs.getInt("cod_end"));
                end.setTipo(rs.getString("tipo"));
                end.setNome(rs.getString("n_end"));
                end.setNumero(rs.getInt("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setComplemento(rs.getString("end_complemento"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na busca!"+getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                pstmt.close();
                conexao.getConexao().close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Erro ao fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return end;
    }
    
    public int atualizarEndereco(Endereco endereco){
        int atualizou=0;
        Conexao conexao = new Conexao();
        String sql = "UPDATE endereco SET tipo = ?, n_end = ?, numero = ?, bairro = ?, cidade = ?, "
                + "estado = ?, complemento=? WHERE cod_end = ?";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
        
        pstmt.setString(1, endereco.getTipo());
        pstmt.setString(2, endereco.getNome());
        pstmt.setInt(3, endereco.getNumero());
        pstmt.setString(4, endereco.getBairro());
        pstmt.setString(5, endereco.getCidade());
        pstmt.setString(6, endereco.getEstado());
        pstmt.setString(7, endereco.getComplemento());
        pstmt.setInt(8, endereco.getId_end());
        atualizou= pstmt.executeUpdate();
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
    
    public int deletarEndereco(Endereco endereco){
        int deletou = 0;
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM endereco WHERE cod_end=?";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, endereco.getId_end());
            deletou = pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro na remoção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return deletou;
    }
}
