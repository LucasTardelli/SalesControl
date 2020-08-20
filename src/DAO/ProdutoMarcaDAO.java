
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ProdutoMarca;

/**
 *
 * @author Aragao
 */
public class ProdutoMarcaDAO {
    public int adicionaMarca(ProdutoMarca pm){
        int keyMarca =0;
        String sql = "INSERT INTO marca_prod(marca_tipo) values (?)";
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        try {
            pstmt= conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, pm.getNomeMarca());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            keyMarca = rs.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return keyMarca;
    }
    public List<ProdutoMarca> procuraProdMarca(){
        List<ProdutoMarca> marcas = new ArrayList<>();
        ProdutoMarca pm =null;
        String sql = "SELECT * FROM marca_prod";
        Conexao conexao = new Conexao();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                pm = new ProdutoMarca();
                pm.setCod_marca(rs.getInt("cod_marca"));
                pm.setNomeMarca(rs.getString("marca_tipo"));
                marcas.add(pm);
            }
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
        return marcas;
    }
}
