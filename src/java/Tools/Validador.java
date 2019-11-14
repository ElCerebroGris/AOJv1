/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entidades.Contest;
import Entidades.ProblemaAd;
import Entidades.Team;
import Entidades.Usuario;
import java.util.Vector;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

/**
 *
 * @author Zamba
 */
public class Validador {

    //Feito
    public static boolean validarSubmit(String code, Model model){
        if(code.equals("") || code.isEmpty()){
            model.addAttribute("erros", "Code is empty");
            return true;
        }
        return false;
    }
    
    public boolean validarContest(Contest c, Model model) {
        Vector<String> lista = new Vector<>();
        boolean erros = false;
        if (c.getNome().equals("") || c.getNome() == null) {
            lista.add("contest name is null");
            erros = true;
        } else if (c.getNome().length() < 5 || c.getNome().length() > 30) {
            lista.add("Size of contest name must be betwen 5 and 30");
            erros = true;
        } else {
            lista.add("");
        }
        
        if (c.getNome().equals("") || c.getNome() == null) {
            lista.add("contest name is null");
            erros = true;
        } else if (c.getNome().length() < 5 || c.getNome().length() > 30) {
            lista.add("Size of contest name must be betwen 5 and 30");
            erros = true;
        } else {
            lista.add("");
        }
        
        model.addAttribute("erros", lista);
        return erros;
    }

    public String validarProblema(ProblemaAd p) {
        return "";
    }

    public static boolean validarUsuario(Usuario p, Model model) {
        Vector<String> lista = new Vector<>();
        boolean erros = false;
        if (p.getLogin().equals("") || p.getLogin() == null) {
            lista.add("Username is null");
            erros = true;
        } else if (p.getLogin().length() <= 5 || p.getLogin().length() >= 30) {
            lista.add("Size of Username must be betwen 5 and 30");
            erros = true;
        } else {
            lista.add("");
        }
        
        return erros;
    }

    public static boolean validarTeam(Team t, Model model) {
        Vector<String> lista = new Vector<>();
        boolean erros = false;
        if (t.getLogin().equals("") || t.getLogin() == null) {
            lista.add("Username is null");
            erros = true;
        } else if (t.getLogin().length() < 3 || t.getLogin().length() > 30) {
            lista.add("Size of Username must be betwen 3 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        if (t.getPassword().equals("") || t.getPassword() == null) {
            lista.add("Password is invalid");
            erros = true;
        } else if (t.getPassword().length() < 8 || t.getLogin().length() > 30) {
            lista.add("Size of Password must be betwen 8 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        if (!t.getPassword().equals(t.getConfirm_password())) {
            lista.add("Password don't match");
            erros = true;
        } else {
            lista.add("");
        }

        if (t.getTeam_name().equals("") || t.getTeam_name() == null) {
            lista.add("Team name is null");
            erros = true;
        } else if (t.getTeam_name().length() < 3 || t.getTeam_name().length() > 30) {
            lista.add("Size of Team name must be betwen 3 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        if (t.getUser1().equals("") || t.getUser1() == null) {
            lista.add("User is null");
            erros = true;
        } else if (t.getUser1().length() < 3 || t.getUser1().length() > 30) {
            lista.add("Size of User must be betwen 3 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        if (t.getUser2().equals("") || t.getUser2() == null) {
            lista.add("User is null");
            erros = true;
        } else if (t.getUser2().length() < 3 || t.getUser2().length() > 30) {
            lista.add("Size of User must be betwen 3 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        if (t.getUser3().equals("") || t.getUser3() == null) {
            lista.add("User is null");
            erros = true;
        } else if (t.getUser3().length() < 3 || t.getUser3().length() > 30) {
            lista.add("Size of User must be betwen 3 and 30");
            erros = true;
        } else {
            lista.add("");
        }

        model.addAttribute("erros", lista);
        return erros;
    }

}
