/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BD.ConectaNormal;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Tools.Ficheiro;
import Tools.Iniciar;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class AdminRepositorioImpl implements AdminRepositorio {

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
    public ProblemaAd createProblem(ProblemaAd p) {
        int c = -1;
        try {
            c = quantProblemas("problema");
            c++;
            p.setId(c);

            String[] res = Ficheiro.gerarFicheiroProblema(p.getId(), p.getTexto(), p.getExemploEntrada(),
                    p.getExemploSaida(), p.getExpecificacaoEntrada(), p.getExpecificacaoSaida());
            p.setTexto(res[0]);
            p.setExemploEntrada(res[1]);
            p.setExemploSaida(res[2]);
            p.setExpecificacaoEntrada(res[3]);
            p.setExpecificacaoSaida(res[4]);
            File fin;

            for (int i = 0; i < p.getEntrada().size(); i++) {
                fin = new File(Iniciar.getUrl()+"Problemas/P_" + p.getId() + "/input"+i+".txt");
                fin.createNewFile();
                try (FileOutputStream in = new FileOutputStream(fin)) {
                    in.write(p.getEntrada().get(i));
                    in.flush();
                }
            }

            File fout;
            for (int i = 0; i < p.getSaida().size(); i++) {
                fout = new File(Iniciar.getUrl()+"Problemas/P_" + p.getId() + "/output"+i+".txt");
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

}
