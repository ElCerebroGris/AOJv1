/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Contest;
import Entidades.Problema;
import Entidades.Submissao;
import Entidades.Usuario;
import Tools.Data;
import Tools.Ficheiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class ContestRepositorioImpl implements ContestRepositorio {

    public ContestRepositorioImpl() {
    }

    @Override
    public Contest add_contest(Contest c) {
        long quant = -1;
        try {
            quant = quantContest();
            quant++;
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into contest "
                    + "values (" + quant + ",'" + c.getNome() + "','" + Data.toString(c.getInicio())
                    + "','" + Data.toString(c.getFim()) + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao converter data");
        }
        c.setId(quant);
        return c;
    }

    @Override
    public Contest edit_contest(Contest c) {
        return c;
    }

    @Override
    public List<Contest> listar_contest() {
        List<Contest> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest order by inicio desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] data1 = rs.getString("inicio").split(" ");
                String[] data2 = rs.getString("fim").split(" ");

                Date d1 = Data.FormatarData(data1[0]);
                d1.setHours(Integer.parseInt(data1[1].split(":")[0]));
                d1.setMinutes(Integer.parseInt(data1[1].split(":")[1]));
                d1.setSeconds(0);

                Date d2 = Data.FormatarData(data2[0]);
                d2.setHours(Integer.parseInt(data2[1].split(":")[0]));
                d2.setMinutes(Integer.parseInt(data2[1].split(":")[1]));
                d2.setSeconds(0);
                lista.add(new Contest(rs.getLong("id_contest"), rs.getString("nome"), d1, d2));
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public void delete_contest(int id) {

    }

    @Override
    public Contest get_contest(long id) {
        Contest c = null;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest WHERE id_contest=" + id + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] data1 = rs.getString("inicio").split(" ");
                String[] data2 = rs.getString("fim").split(" ");

                Date d1 = Data.FormatarData(data1[0]);
                d1.setHours(Integer.parseInt(data1[1].split(":")[0]));
                d1.setMinutes(Integer.parseInt(data1[1].split(":")[1]));
                d1.setSeconds(0);

                Date d2 = Data.FormatarData(data2[0]);
                d2.setHours(Integer.parseInt(data2[1].split(":")[0]));
                d2.setMinutes(Integer.parseInt(data2[1].split(":")[1]));
                d2.setSeconds(0);
                c = new Contest(rs.getLong("id_contest"), rs.getString("nome"), d1, d2);
            }
            //Pegar a lista dos id dos problemas deste concurso
            c.setProblems(list_problems_contest(c.getId()));
            c.setUsers(list_user_contest(c.getId()));
            connect.close();
            return c;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao pesquisar na BD");
        }
        return c;
    }

    @Override
    public void add_problem_to_contest(long cid, long pid) {
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into contest_problem " + "values (" + cid + "," + pid + ");";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public Vector<Problema> list_problems_contest(long cid) {
        Vector<Problema> lista = new Vector<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT c.id_contest, c.id_problem, c.letra, p.nome_problema "
                    + "FROM contest_problem c JOIN problema p ON c.id_problem=p.id_problema "
                    + "WHERE c.id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Problema(rs.getInt("id_problem"), rs.getString("nome_problema"), " ",
                        rs.getString("letra")));
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public void add_user_to_contest(long cid, long uid) {
        int id = quantGeral("contest_user");
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into contest_user(id,id_contest,id_user) "
                    + "values (" + id + "," + cid + "," + uid + ");";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public Vector<Integer> list_user_contest(long cid) {
        Vector<Integer> lista = new Vector<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest_user WHERE id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("id_user"));
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    private static int quantContest() {
        int r = 0;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest order by id_contest desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = rs.getInt("id_contest");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return r;
    }

    private static int quantGeral(String table) {
        int r = 0;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM " + table + " order by id desc;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = rs.getInt("id");
            }
            ++r;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return r;
    }

    @Override
    public void submeter(Submissao s) {
        try {
            int c = quantSubmissoes() + 1;
            s.setUrl(Ficheiro.gerarFicheiroContest(s, c, s.getLinguagem()));
            Connection connect = ConectaNormal.getConnection();
            Calendar date = Calendar.getInstance();
            Data d = new Data(date);
            String sql = "insert into contest_submission(id_sublmissao , id_contest, id_usuario, id_problema, url, linguagem, status, "
                    + "data_submissao, usuario) "
                    + "values (" + c + "," +s.getId_contest()+","+ s.getId_usuario() + "," + s.getId_problema()
                    + ",'" + s.getUrl() + "','" + s.getLinguagem() + "','Evaluating','" + d.toString() + "',"
                    + "'" + s.getLogin_user() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
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
    public boolean isContestant(long cid, long uid) {
        return false;
    }

}
