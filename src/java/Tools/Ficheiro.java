/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entidades.Submissao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Zamba
 */
public class Ficheiro {

    public static String gerarFicheiro(Submissao s, int quant, String linguagem) {
        File f = null;
        if (linguagem.endsWith("C++")) {
            f = new File(Iniciar.getUrl()+"Envios/S_" + quant + "_" + s.getId_usuario() + "_"
                    + s.getId_problema() + ".cpp");
        } else {
            f = new File(Iniciar.getUrl()+"Envios/S_" + quant + "_" + s.getId_usuario() + "_"
                    + s.getId_problema() + "."+linguagem);
        }
        try {
            f.createNewFile();
            PrintWriter p = new PrintWriter(f);
            p.append(s.getCodigo());
            p.flush();
            p.close();
        } catch (Exception ex) {
            return "Erro ao criar arquivo";
        }
        return f.getAbsolutePath();
    }
    
    public static String gerarFicheiroContest(Submissao s, int quant, String linguagem) {
        File f = null;
        if (linguagem.endsWith("C++")) {
            f = new File(Iniciar.getUrl()+"Envios/Contest/S_" + quant + "_" + s.getId_usuario() + "_"
                    + s.getId_problema() + ".cpp");
        } else {
            f = new File(Iniciar.getUrl()+"Envios/Contest/S_" + quant + "_"+s.getId_contest()+"_" + 
                    s.getId_usuario() + "_" + s.getId_problema() + "."+linguagem);
        }
        try {
            f.createNewFile();
            PrintWriter p = new PrintWriter(f);
            p.append(s.getCodigo());
            p.flush();
            p.close();
        } catch (Exception ex) {
            return "Erro ao criar arquivo";
        }
        return f.getAbsolutePath();
    }

    public static String[] gerarFicheiroProblema(long id, String texto,
            String exemploEntrada, String exemploSaida,String exp_entrada, String exp_saida) {
        File f = new File(Iniciar.getUrl()+"Problemas/P_" + id);

        String[] urls = {"/texto.txt", "/exemplo_entrada.txt", "/exemplo_saida.txt",
            "/expecificacao_entrada.txt", "/expecificacao_saida.txt"};
        String[] textos = {texto, exemploEntrada, exemploSaida, exp_entrada, exp_saida};
        String[] res = new String[urls.length];
        try {
            Files.createDirectory(f.toPath());
            File f1 = null;
            PrintWriter p = null;
            for (int i = 0; i < urls.length; i++) {
                Escrever(textos[i], f.getAbsoluteFile() + urls[i]);
                res[i] = f.getAbsoluteFile() + urls[i];
            }
            //JOptionPane.showMessageDialog(null, "Ok 1");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return res;
    }
    
    public static void Escrever(String texto, String caminho) {
        Path p = Paths.get(caminho);
        Charset utf = StandardCharsets.UTF_8;
        try (BufferedWriter br = Files.newBufferedWriter(p, utf)) {
            String [] cad = texto.split("<br />");
            for (int i = 0; i < cad.length; i++) {
                br.write(cad[i]);
            }
            br.flush();
            br.close();
        } catch (IOException e) {

        }
    }

    public static String pegarTexto(String url) {
        File f;
        StringBuilder res = new StringBuilder();
        try {
            f = new File(url);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                res.append(s.nextLine());
                res.append("<br />");
            }
        } catch (Exception ex) {
            return "Erro "+ex;
        }
        return res.toString();
    }

}
