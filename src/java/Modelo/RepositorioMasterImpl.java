/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Comentario;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Submissao;
import Tools.Data;
import Tools.Ficheiro;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class RepositorioMasterImpl implements RepositorioMaster {

    private JdbcOperations jdbcOperations;

    public RepositorioMasterImpl() {
    }

    public RepositorioMasterImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void submeter(Submissao s) {
        try {
            int c = quantSubmissoes() + 1;
            s.setUrl(Ficheiro.gerarFicheiro(s, c, s.getLinguagem()));
            Connection connect = ConectaNormal.getConnection();
            Calendar date = Calendar.getInstance();
            Data d = new Data(date);
            String sql = "insert into submissao(id_sublmissao , id_usuario, id_problema, url, linguagem, status, "
                    + "data_submissao, usuario) "
                    + "values (" + c + "," + s.getId_usuario() + "," + s.getId_problema()
                    + ",'" + s.getUrl() + "','" + s.getLinguagem() + "','Evaluating','"+d.toString()+"',"
                    + "'"+s.getLogin_user()+"');";           
            
            String update = "update usuario set last_submission='"+d.toString()+"' where id_usuario="
                    + s.getId_usuario() + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            ps = connect.prepareStatement(update);
            ps.executeUpdate();
            connect.close();
            //JOptionPane.showMessageDialog(null, "Adicionado na BD com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    private static int quantSubmissoes() {
        int r = -1;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT count(*) FROM submissao;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            r = rs.getInt(1);
            connect.close();
            return r;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return r;
    }

    @Override
    public List<Submissao> listar_submissoes() {

        List<Submissao> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM submissao ORDER BY id_sublmissao desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Submissao(rs.getLong("id_sublmissao"), rs.getLong("id_problema"),
                        rs.getLong("id_usuario"), rs.getString("status"), rs.getString("linguagem"),
                        rs.getString("data_submissao"), rs.getString("usuario")));
            }
            connect.close();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public List<Problema> listar_problemas() {
        List<Problema> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT id_problema, nome_problema, tempo_limite, pontos, visible, ac, total"
                    + " FROM problema WHERE visible=true order by id_problema;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Problema(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getFloat(4),
                        rs.getBoolean(5), rs.getInt(6), rs.getInt(7)));
            }
            connect.close();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public ProblemaAd buscarProblemaPorId(long id) {
        ProblemaAd p = null;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM problema where id_problema=" + id + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            p = new ProblemaAd(rs.getLong("id_problema"), rs.getString("nome_problema"),rs.getInt("tempo_limite"),
                    rs.getString("texto_problema"),rs.getString("exp_entrada"), rs.getString("exp_saida"),
                    rs.getString("exemplo_entrada"), rs.getString("exemplo_saida"), rs.getString("usuario"));
            p.setTexto(Ficheiro.pegarTexto(p.getTexto()));
            p.setExpecificacaoEntrada(Ficheiro.pegarTexto(p.getExpecificacaoEntrada()));
            p.setExpecificacaoSaida(Ficheiro.pegarTexto(p.getExpecificacaoSaida()));
            p.setExemploEntrada(Ficheiro.pegarTexto(p.getExemploEntrada()));
            p.setExemploSaida(Ficheiro.pegarTexto(p.getExemploSaida()));
            connect.close();
            return p;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return p;
    }

    private static int quantProblemas(String tabela) {
        int r = -1;
        Connection connect;
        try {
            connect = ConectaNormal.getConnection();
            String sql = "SELECT count(*) FROM " + tabela + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            r = rs.getInt(1);
            connect.close();
            //JOptionPane.showMessageDialog(null, "Quant OK");
            return r;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao pesquisar na BD");
        }
        return r;
    }

    @Override
    public void comentar(Comentario c) {
        int quant = -1;
        try {
            quant = quantProblemas("mural");
            quant++;
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into mural(id_mural,usuario,texto_mural) "
                    + "values (" + quant + ",'" + c.getUsuario() + "','" + c.getTexto() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
            //JOptionPane.showMessageDialog(null, "Adicionado na BD com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public List<Comentario> lista_comentarios() {
        List<Comentario> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM mural ORDER BY data_mural desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();  
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date dt = rs.getDate("data_mural");
                lista.add(new Comentario(rs.getString("usuario"), rs.getString("texto_mural"),
                        dt.toString()));
            }
            connect.close();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

}
