/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Team;
import Entidades.Usuario;
import Tools.Ficheiro;
import Tools.Iniciar;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class AdminRepositorioImpl implements AdminRepositorio {

    private UsuarioRepositorio repositorio_usuario;

    @Autowired
    public AdminRepositorioImpl(UsuarioRepositorio ru) {
        this.repositorio_usuario = ru;
    }

    @Override
    public void desabilitarProblema(long pid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE problema SET visible=false WHERE id_problema=" + pid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public List<Problema> listarProblema() {
        List<Problema> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT id_problema, nome_problema, tempo_limite, pontos, visible, ac, total"
                    + " FROM problema order by id_problema;";

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
    public void abilitarProblema(long pid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE problema SET visible=true WHERE id_problema=" + pid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void desabilitarUser(long uid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE usuario SET state=false WHERE id_usuario=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void abilitarUser(long uid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE usuario SET state=true WHERE id_usuario=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void makeAdmin(long uid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE usuario SET role=0 WHERE id_usuario=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public void unmakeAdmin(long uid) {
        try {
            Connection connect = ConectaNormal.getConnection();

            String sql = "UPDATE usuario SET role=2 WHERE id_usuario=" + uid + ";";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

    @Override
    public ProblemaAd createProblem(ProblemaAd p) {
        int c = -1;
        try {
            c = quantGeral("problema");
            c++;
            p.setId(c);

            String[] res = Ficheiro.gerarFicheiroProblema(p.getId(), p.getTexto(), p.getExemploEntrada(),
                    p.getExemploSaida(), p.getExpecificacaoEntrada(), p.getExpecificacaoSaida());
            p.setTexto(res[0]);
            p.setExemploEntrada(res[1]);
            p.setExemploSaida(res[2]);
            p.setExpecificacaoEntrada(res[3]);
            p.setExpecificacaoSaida(res[4]);

            //File f = new File(Iniciar.getUrl() + "Problemas/P_" + p.getId());
            //Files.createDirectory(f.toPath());
            File fin;

            for (int i = 0; i < p.getEntrada().size(); i++) {
                fin = new File(Iniciar.getUrl() + "Problemas/P_" + p.getId() + "/input" + i + ".txt");
                fin.createNewFile();
                try (FileOutputStream in = new FileOutputStream(fin)) {
                    in.write(p.getEntrada().get(i));
                    in.flush();
                }
            }

            File fout;
            for (int i = 0; i < p.getSaida().size(); i++) {
                fout = new File(Iniciar.getUrl() + "Problemas/P_" + p.getId() + "/output" + i + ".txt");
                fout.createNewFile();
                try (FileOutputStream in = new FileOutputStream(fout)) {
                    in.write(p.getSaida().get(i));
                    in.flush();
                }
            }

            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into problema "
                    + "values (" + c + ",'" + p.getNome() + "','" + p.getTexto()
                    + "'," + p.getTempo() + ",' ',' ','" + p.getExemploSaida() + "','"
                    + p.getExemploEntrada() + "','" + p.getUsuario() + "','"
                    + p.getExpecificacaoEntrada() + "','" + p.getExpecificacaoSaida() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
            //JOptionPane.showMessageDialog(null, "Adicionado na BD com sucesso");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao adicionar na BD 1");
        }
        return p;
    }

    private static int quantGeral(String tabela) {
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
    public List<Usuario> listarUsuario() {
        List<Usuario> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM usuario order by id_usuario asc;";

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
    public List<Team> listarEquipas() {
        List<Team> lista = new ArrayList<>();

        try {
            Connection connect = ConectaNormal.getConnection();
            String sql = "SELECT * FROM team order by team_name;";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Team t = new Team(rs.getString("team_name"), rs.getString("user1"), rs.getString("user2"),
                rs.getString("user3"), rs.getBoolean("active"));
                t.setId(rs.getLong("id_team"));
                t.setInstitution(rs.getString("institution"));
                t.setCountry(rs.getString("country"));
                lista.add(t);
            }
            connect.close();
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao pesquisar na BD");
        }
        return lista;
    }

    @Override
    public void addEquipa(Team t) {

        try {

            long c = quantGeral("team");
            c++;
            Connection connect = ConectaNormal.getConnection();
            String sql = "insert into team "
                    + "values (" + c + ",'" + t.getLogin() + "','" + t.getPassword() + "','" + t.getCountry()
                    + "','" + t.getInstitution() + "','" + t.getTeam_name() + "','" + t.getUser1()
                    + "','" + t.getUser2()+ "','" + t.getUser3() + "');";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao adicionar na BD");
        }
    }

}
