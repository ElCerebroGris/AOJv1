/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Comentario;
import Entidades.PLanguage;
import Entidades.Usuario;
import Modelo.RepositorioMaster;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Zamba
 */
@Controller
public class HomeController {

    private RepositorioMaster repositorio;

    @Autowired
    public HomeController(RepositorioMaster repositorio) {
        this.repositorio = repositorio;
    }

    @RequestMapping(value = {"/", "/index", "/homepage"}, method = GET)
    public String home(HttpServletRequest request, Model model, HttpSession sessao) {
        List<Comentario> lista = repositorio.lista_comentarios();
        Usuario u = (Usuario) sessao.getAttribute("usuario");

        model.addAttribute("mural", lista);

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
        return "index";
    }

    @RequestMapping(value = {"/statistics_submit"}, method = GET)
    public String view_estatisticas_submissao(HttpServletRequest request, Model model, HttpSession sessao) {
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
        //Result
        List<PLanguage> lista = repositorio.statistics();
        model.addAttribute("languages", lista);
        return "statistics";
    }

    @RequestMapping(value = {"/recuperar"}, method = GET)
    public String recuperar_tela(HttpServletRequest request) {
        return "recuperar_senha";
    }

    @RequestMapping(value = "credits", method = GET)
    public String sobre(HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
        }
        return "credits";
    }

    @RequestMapping(value = "contacto", method = GET)
    public String contacto(HttpServletRequest request) {
        return "contacto";
    }

    @RequestMapping(value = "logout", method = GET)
    public String sair(HttpServletRequest request, HttpSession sessao) {
        sessao.removeAttribute("usuario");
        String ref = request.getHeader("Referer");
        return "redirect:" + ref;
    }

}
