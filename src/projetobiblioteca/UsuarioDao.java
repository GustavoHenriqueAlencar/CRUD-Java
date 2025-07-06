/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo33297826
 */
public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        this.connection = new ConnectionFactory().conectaDB();
    }

    public void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome,email,telefone,tipo_usuario) VALUES (?,?,?,?)";
        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getTelefone());
            pstm.setString(4, usuario.getTipo_usuario());

            pstm.executeUpdate();
            System.out.println("Deu bom");

        } catch (SQLException e) {
            System.out.println("Deu ruim" + e.getMessage());
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));

                lista.add(usuario);

            }

        } catch (SQLException e) {
            System.out.println("Deu ruim " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
        return lista;
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        PreparedStatement pstm = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));

            }

        } catch (SQLException e) {
            System.out.println("Deu ruim " + e.getMessage());
        } finally {
            
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            
        }
        return usuario;
    }
    
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ?, tipo_usuario = ? WHERE id = ?";
        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getTelefone());
            pstm.setString(4, usuario.getTipo_usuario());
            pstm.setInt(5, usuario.getId());

            
            
            int linhasAfetadas = pstm.executeUpdate();
            if(linhasAfetadas > 0) {
                System.out.println("Deu bom!");
            }else{
                System.out.println("Nenhum usuario encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Deu ruim" + e.getMessage());
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }
    
    public void deletarUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement pstm = null;

        try {

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, usuario.getId());

            
            System.out.println("Usuario Deletado");
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Deu ruim" + e.getMessage());
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }
    
    

}
