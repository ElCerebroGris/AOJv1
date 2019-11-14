
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Team;
import Entidades.Usuario;
import Modelo.AdminRepositorio;
import Tools.Validador;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Zamba
 */
@Controller
public class TeamController {

    private AdminRepositorio repositorio;

    @Autowired
    public TeamController(AdminRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @RequestMapping(value = {"/teams"}, method = GET)
    public String list_teams(Model model, HttpServletRequest request, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                List<Team> lista = repositorio.listarEquipas();

                model.addAttribute("equipas", lista);
                model.addAttribute("admin", true);
                return "team/teams";
            } else {
                model.addAttribute("admin", false);
            }
        }
        return "redirect:index";
    }

    @RequestMapping(value = {"/add_team_form"}, method = GET)
    public String add_team(Model model, HttpServletRequest request, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
                if (!model.containsAttribute("team")) {
                    model.addAttribute("team", new Team());
                }
                return "team/add_team_frm";
            } else {
                model.addAttribute("admin", false);
            }
        }
        return "redirect:index";
    }

    @RequestMapping(value = {"/add_team"}, method = RequestMethod.POST)
    public String add_team_server(Model model, Team team, HttpSession sessao,
            HttpServletRequest request) {

        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
                if (Validador.validarTeam(team, model)) {
                    model.addAttribute("team", team);
                    //return "team/add_team_frm";
                    return add_team(model, request, sessao);
                }
                //repositorio.addEquipa(team);
                return "redirect:teams";
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }
        return "redirect:index";
    }

    private void roles(HttpSession sessao, Model model, String pagina) {
    }

}
