/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Contest;
import Entidades.PLanguage;
import Entidades.ProblemaAd;
import Entidades.Submissao;
import Entidades.Usuario;
import Tools.Data;
import Tools.Ficheiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class ContestRepositorioImpl implements ContestRepositorio {

    private UsuarioRepositorio repositorio_usuario;
    private RepositorioMaster repositorio_master;

    @Autowired
    public ContestRepositorioImpl(UsuarioRepositorio ru, RepositorioMaster rm) {
        this.repositorio_usuario = ru;
        this.repositorio_master = rm;
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
                lista.add(new Contest(rs.getLong("id_contest"), rs.getString("nome"), d1, d2,
                        rs.getBoolean("visible")));
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
                c = new Contest(rs.getLong("id_contest"), rs.getString("nome"), d1, d2, rs.getBoolean("visible"));
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
            int id = quantGeral("contest_problem");
            int tam = list_problems_contest(cid).size();
            char c = 'A';
            for (char i = 0; i < tam; i++) {
                ++c;
            }

            String sql = "insert into contest_problem " + "values (" + id + "," + cid + "," + pid + ", "
                    + "'" + c + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void remove_problem_from_contest(long cid, long pid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "delete from contest_problem where id_contest=" + cid + " and id_problem=" + pid + ";";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public Vector<ProblemaAd> list_problems_contest(long cid) {
        Vector<ProblemaAd> lista = new Vector<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT c.id_contest, c.id_problem, c.letra, p.nome_problema "
                    + "FROM contest_problem c JOIN problema p ON c.id_problem=p.id_problema "
                    + "WHERE c.id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ProblemaAd(rs.getInt("id_problem"), rs.getString("nome_problema"), " ",
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
    public void remove_user_from_contest(long cid, long uid) {
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "delete from contest_user where id_contest=" + cid + " and id_user=" + uid + ";";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public Vector<Usuario> list_user_contest(long cid) {
        Vector<Usuario> lista = new Vector<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest_user WHERE id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getInt("id_user");
                Usuario u = repositorio_usuario.procurarPorId(id);
                u.setSolved(rs.getInt("user_ac"));
                lista.add(u);
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
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
                    + "values (" + c + "," + s.getId_contest() + "," + s.getId_usuario() + "," + s.getId_problema()
                    + ",'" + s.getUrl() + "','" + s.getLinguagem() + "','Evaluating','" + d.toString() + "',"
                    + "'" + s.getLogin_user() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD id=" + s.getId_problema());
        }
    }

    private static int quantSubmissoes() {
        int r = -1;
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT count(*) FROM contest_submission;";

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
        Connection connect = null;
        try {
            connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest_user WHERE id_contest=" + cid + " and id_user=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
            }
        }
        return false;
    }

    @Override
    public Vector<ProblemaAd> list_user_problem_contest(Contest c, long uid) {
        Vector<ProblemaAd> lista = new Vector<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest_user_problem WHERE id_contest=" + c.getId() + " and "
                    + "id_user=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long pid = rs.getLong("id_problem");
                ProblemaAd p = repositorio_master.buscarProblemaPorId(pid);
                p.setPenalidade(rs.getInt("penality"));
                lista.add(p);
            }

            //Adicionar problemas q o usuario ainda não submeteu
            for (int i = 0; i < c.getProblems().size(); i++) {
                boolean yes = false;

                for (int j = 0; j < lista.size(); j++) {
                    if (c.getProblems().get(i).getId() == lista.get(j).getId()
                            && lista.get(j).getPenalidade() != 0) {
                        yes = true;
                        break;
                    }
                }

                if (!yes) {
                    ProblemaAd p = c.getProblems().get(i);
                    lista.add(p);
                }
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public Vector<Submissao> list_submission(long cid) {
        Vector<Submissao> lista = new Vector<>();
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM contest_submission WHERE id_contest=" + cid
                    + " ORDER BY id_sublmissao desc;";

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
    public void desactive_contest(long cid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE contest SET visible=false WHERE id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void active_contest(long cid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE contest SET visible=true WHERE id_contest=" + cid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public List<PLanguage> statistics() {
        List<PLanguage> lista = new ArrayList<>();
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT count(*) FROM contest_submission where linguagem='java' and status='Ok';";
            String sql1 = "SELECT count(*) FROM contest_submission where linguagem='java' and status='Compilation error';";
            String sql2 = "SELECT count(*) FROM contest_submission where linguagem='java' and status='Time Limited Exceded';";
            String sql3 = "SELECT count(*) FROM contest_submission where linguagem='java' and status='Wrong answer';";
            String sql4 = "SELECT count(*) FROM contest_submission where linguagem='java' and status='Runtime error'";
            PLanguage pl = new PLanguage();
            pl.setName("Java");
            
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            pl.setAC(rs.getLong(1));
            
            ps = connect.prepareStatement(sql1);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setCE(rs.getLong(1));
            
            ps = connect.prepareStatement(sql2);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setTLE(rs.getLong(1));
            
            ps = connect.prepareStatement(sql3);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setWA(rs.getLong(1));
            
            ps = connect.prepareStatement(sql4);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setRTE(rs.getLong(1));
            
            lista.add(pl);
            connect.close();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public List<PLanguage> statistics(long cid) {
        List<PLanguage> lista = new ArrayList<>();
        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT count(*) FROM contest_submission "
                    + "WHERE id_contest="+cid+" AND linguagem='java' AND status='Ok';";
            String sql1 = "SELECT count(*) FROM contest_submission "
                    + "WHERE id_contest="+cid+" AND linguagem='java' AND status='Compilation error';";
            String sql2 = "SELECT count(*) FROM contest_submission "
                    + "WHERE id_contest="+cid+" AND linguagem='java' AND status='Time Limited Exceded';";
            String sql3 = "SELECT count(*) FROM contest_submission "
                    + "WHERE id_contest="+cid+" AND linguagem='java' AND status='Wrong answer';";
            String sql4 = "SELECT count(*) FROM contest_submission "
                    + "WHERE id_contest="+cid+" AND linguagem='java' AND status='Runtime error'";
            PLanguage pl = new PLanguage();
            pl.setName("Java");
            
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            rs.next();
            pl.setAC(rs.getLong(1));
            
            ps = connect.prepareStatement(sql1);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setCE(rs.getLong(1));
            
            ps = connect.prepareStatement(sql2);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setTLE(rs.getLong(1));
            
            ps = connect.prepareStatement(sql3);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setWA(rs.getLong(1));
            
            ps = connect.prepareStatement(sql4);
            ps.executeQuery();
            rs = ps.executeQuery();
            rs.next();
            pl.setRTE(rs.getLong(1));
            
            lista.add(pl);
            connect.close();
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao pesquisar na BD ");
        }
        return lista;
    }

}
