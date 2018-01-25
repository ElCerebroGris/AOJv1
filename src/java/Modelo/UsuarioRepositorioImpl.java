/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class UsuarioRepositorioImpl implements UsuarioRepositorio {

    private JdbcOperations jdbcOperations;

    public UsuarioRepositorioImpl() {
    }

    public UsuarioRepositorioImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Usuario procurar(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario salvar(Usuario u) {
        long c = -1;
        try {
            c = quantUsuario();
            c++;
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into usuario "
                    + "values (" + c + ",'" + u.getFirst_name() + "','" + u.getLogin()
                    + "','" + u.getPassword() + "','" + u.getLast_name() + "', 0.0, 2, "
                    + "'" + u.getEmail() + "','" + u.getGender() + "','" + u.getCountry() + "',"
                    + "'" + u.getInstitution() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
        u.setId(c);
        return u;
    }

    private static int quantUsuario() {
        int r = -1;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario order by id_usuario desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            r = rs.getInt("id_usuario");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return r;
    }

    @Override
    public Usuario procurarPorNome(String login, String senha) {
        Usuario u = null;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario WHERE login='" + login + "' AND senha='" + senha + "' "
                    + "AND state=true;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getLong("id_usuario"), rs.getInt("role"), rs.getString("login"),
                        rs.getString("senha"), rs.getString("nome_usuario"), rs.getString("ultimo_nome"),
                        rs.getFloat("pontos"), rs.getString("email"), rs.getString("gender"));
            }
            return u;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD 1");
        }
        return u;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario order by pontos desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(rs.getLong("id_usuario"), rs.getInt("role"), rs.getString("login"),
                        rs.getString("senha"), rs.getString("nome_usuario"), rs.getString("ultimo_nome"),
                        rs.getString("email"), rs.getString("gender"), rs.getString("country"),
                        rs.getString("institution"), rs.getFloat("pontos"), rs.getBoolean("state"),
                        rs.getString("last_submission"), rs.getInt("solved")));
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public boolean procurarExistencia(String login, String email) {
        Usuario u = null;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario WHERE login='" + login + "' OR email='" + email + "';";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD 2");
        }
        return u == null;
    }

    @Override
    public Usuario procurarPorId(long uid) {
        Usuario u = null;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario WHERE id_usuario=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getLong("id_usuario"), rs.getString("login"), rs.getString("gender")
                        , rs.getString("country"), rs.getString("institution"),rs.getFloat("pontos"), 
                        rs.getString("last_submission"), rs.getInt("solved"));
            }
            return u;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD 1");
        }
        return u;
    }    

}
