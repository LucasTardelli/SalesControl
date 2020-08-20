
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aragao
 */
public class Conexao {
    private Connection conexao;
    Connection conn =null;
    public Conexao(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:/Users/Aragao/Documents/NetBeansProjects/ControleVendas/db/sqlitejava.db";
            conexao = DriverManager.getConnection(url);
            //System.out.println("Conexao com o bando de dados estabelecida!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro: ", JOptionPane.ERROR_MESSAGE);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Classe: ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Connection getConexao(){
        return conexao;
    }
    
    public void close(){
                try {
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
        }

    }    
}
