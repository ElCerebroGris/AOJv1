/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Submissao;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Usuario;
import Modelo.AdminRepositorio;
import Modelo.RepositorioMaster;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Zamba
 */
@Controller
@SessionAttributes("usuario")
public class ProblemaController {

    private RepositorioMaster repositorio;
    private AdminRepositorio admin_repositorio;

    @Autowired
    public ProblemaController(RepositorioMaster repositorio, AdminRepositorio admin_repositorio) {
        this.repositorio = repositorio;
        this.admin_repositorio = admin_repositorio;
    }

    @RequestMapping(value = "/problems", method = GET)
    public String Listarproblemas(Model model, HttpSession sessao) {
        List<Problema> lista = repositorio.listar_problemas();
        
        
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                lista = admin_repositorio.listarProblema();
                model.addAttribute("problemas", lista);
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);
            }
        }
        
        model.addAttribute("problemas", lista);
        return "problems";
    }

    @RequestMapping(value = "/submit", method = GET)
    public String Submeterproblemas(int id, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio.buscarProblemaPorId(id);
        model.addAttribute("problema", p);
        roles(model, sessao);
        return "submit";
    }

    @RequestMapping(value = "/status", method = GET)
    public String listarSubmissoes(Model model, HttpSession sessao) {
        List<Submissao> lista = repositorio.listar_submissoes();
        model.addAttribute("submisso", lista);
        roles(model, sessao);
        return "status";
    }

    @RequestMapping(value = "/send_submission", method = RequestMethod.POST)
    public String enviarProblema(long id_problema, @RequestParam("codigo") String codigo,
            @RequestParam("linguagem") String linguagem, HttpSession sessao) {
        Usuario online = (Usuario) sessao.getAttribute("usuario");
        Submissao s = new Submissao(id_problema, online.getId(), codigo, "Evaluating", linguagem, 
                online.getLogin());
        repositorio.submeter(s);
        return "redirect:status";
    }

    @RequestMapping(value = "/verProblema", method = GET)
    public String verProblema(int id, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio.buscarProblemaPorId(id);
        model.addAttribute("problema", p);
        roles(model, sessao);
        return "desc_problema";
    }

    private void roles(Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);
            }
        }
    }

}
