
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CliTel;

/**
 *
 * @author Aragao
 */
public class CliTelDAO {
    public int adicionarTel(CliTel ct){
        if(ct.getTelefones().isEmpty()){
            return 0;
        }
        String sql= "INSERT INTO cli_telefone VALUES (?, ?)";
        for(int i = 1; i< ct.getTelefones().size();i++){
            sql+= ",(?,?)";
        }
        int linhasAfetadas=0;
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            for(int i=0;i<ct.getTelefones().size();i++){
                pstmt.setInt(((2*i)+1), ct.getKeyCli());
                pstmt.setString(((2*i)+2), ct.getTelefones().get(i));
            }
            linhasAfetadas = pstmt.executeUpdate();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return linhasAfetadas;
    }
    
    public CliTel procuraTelefones(int keyCli){
        List<String> telefones = new ArrayList<>();
        CliTel telefone = new CliTel();;
        String sql = "SELECT * FROM cli_telefone WHERE fk_cli_tel=?";
        Conexao conexao= new Conexao();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, keyCli);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                telefone.setKeyCli(rs.getInt("fk_cli_tel"));
                telefones.add(rs.getString("n_tel"));
            }
            telefone.setTelefones(telefones);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Busca!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return telefone;
    }
    
    public int atualizaTelefone(CliTel ct){
        int atualizou = 0;
        
        return atualizou;
    }
}
