
package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CliTel;
import model.Cliente;
import model.Endereco;

/**
 *
 * @author Aragao
 */
public class ClienteDAO {
    public int adicionarCliente(Cliente cliente){
        SimpleDateFormat sdfNasc = new SimpleDateFormat("yyyy/MM/dd");
        EnderecoDAO eDAO = new EnderecoDAO();
        
        int keyCliente = 0;
        int keyEnd=0;
        String sql = "INSERT INTO cliente (nome, data_nasc, cpf, genero, data_cad, fk_cod_end, fk_cod_sit) values (?, ?, ?, ?, DATETIME('NOW'), ?, ?)";
//String sql = "INSERT INTO cliente (nome, data_nasc, cpf, genero, telefone1, telefone2, cod_end) values (?, ?, ?, ?, ?, ?, (SELECT MAX(cod_end)from endereco))";
        Conexao conexao = new Conexao();
        PreparedStatement pstmt=null;
        keyEnd=eDAO.adicionaEndereco(cliente.getEndereco());
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setDate(2, new java.sql.Date(cliente.getDtnascimento().getTime()));
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getGenero());
            pstmt.setInt(5, keyEnd);
            pstmt.setInt(6, cliente.getSituacao().getCod_sit());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            keyCliente = rs.getInt(1);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Inserção!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return keyCliente;
    }
    
    public List<Cliente> pesquisaCliente(){
        SimpleDateFormat sdfCad = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        EnderecoDAO eDAO = new EnderecoDAO();
        CliSituacaoDAO csDAO =new CliSituacaoDAO();
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        Endereco endereco = null;
        CliTel ct=null;
        CliTelDAO ctDAO = new CliTelDAO();
        Date dt = new Date();
        
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM cliente";// INNER JOIN endereco ON cliente.cod_end = endereco.cod_end";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                cliente =new Cliente();
                cliente.setCod_cliente(rs.getInt("cod_cliente"));
                ct=ctDAO.procuraTelefones(cliente.getCod_cliente());
                cliente.setTelefones(ctDAO.procuraTelefones(cliente.getCod_cliente()));
                cliente.setNome(rs.getString("nome"));
                cliente.setDtnascimento(rs.getDate("data_nasc"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setDataDeCad(rs.getString("data_cad"));
                
                cliente.setEndereco(eDAO.clienteEndereco(rs.getInt("fk_cod_end")));
                cliente.setSituacao(csDAO.procuraKeySituacao(rs.getInt("fk_cod_sit")));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex+" "+getClass().getName(), "Erro na Pesquisa! CliDao", JOptionPane.ERROR_MESSAGE);
        /*} catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage()+getClass().getName(), "Erro na conversão de datas!", JOptionPane.ERROR_MESSAGE);
        */}finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return clientes;
    }
    
    public int atualizarCliente(Cliente cliente){
        int atualizou=0;
        Date datesql = new Date(cliente.getDtnascimento().getTime());
        Conexao conexao = new Conexao();
        String sql = "UPDATE cliente SET nome = ?, data_nasc = ?, cpf = ?, genero = ?,"
                + " cod_end=?, fk_cod_sit = ? WHERE cod_cliente = ?";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getDtnascimento().toString());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getGenero());
            pstmt.setInt(5, cliente.getEndereco().getId_end());
            pstmt.setInt(6, cliente.getSituacao().getCod_sit());
            pstmt.setInt(7, cliente.getCod_cliente());
            
            atualizou= pstmt.executeUpdate();
    }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro na atualização!", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                pstmt.close();
                conexao.getConexao().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar conexão!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return atualizou;
    }
    
    public int deletarCliente(Cliente cliente){
        int deletou = 0;
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM cliente WHERE cod_cliente=?";
        PreparedStatement pstmt=null;
        try {
            pstmt = conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, cliente.getCod_cliente());
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
