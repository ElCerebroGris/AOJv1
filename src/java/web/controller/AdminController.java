/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Contest;
import Entidades.ProblemaAd;
import Entidades.Usuario;
import Modelo.AdminRepositorio;
import Modelo.ContestRepositorio;
import Modelo.UsuarioRepositorio;
import Tools.ConfSingleton;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Zamba
 */
@Controller
public class AdminController {

    private ContestRepositorio repositorio;
    private UsuarioRepositorio repositorio2;
    private AdminRepositorio admin_repo;

    @Autowired
    public AdminController(ContestRepositorio repositorio, UsuarioRepositorio repositorio2,
            AdminRepositorio admin_repo) {
        this.repositorio = repositorio;
        this.repositorio2 = repositorio2;
        this.admin_repo = admin_repo;
    }

    @RequestMapping(value = {"/admin_server"}, method = GET)
    public String view_server(Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");

        if (u == null) {
            model.addAttribute("online", false);
            return "redirect:index";
        } else {
            model.addAttribute("online", true);

            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }
        model.addAttribute("db", ConfSingleton.getInstance().getDbPath());
        model.addAttribute("files", ConfSingleton.getInstance().getFilePath());
        return "admin/configServer";
    }

    @RequestMapping(value = {"/add_problem_form"}, method = GET)
    public String view_add_problem(Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");

        if (u == null) {
            model.addAttribute("online", false);

        } else {
            model.addAttribute("online", true);

            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }
        model.addAttribute("problema", new ProblemaAd());
        return "admin/addProblem";
    }

    @RequestMapping(value = {"/disable"}, method = GET)
    public String des_problema(int pid) {
        admin_repo.desabilitarProblema(pid);
        return "redirect:problems";
    }

    @RequestMapping(value = {"/active"}, method = GET)
    public String active_problema(int pid) {
        admin_repo.abilitarProblema(pid);
        return "redirect:problems";
    }

    @RequestMapping(value = {"/disable_user"}, method = GET)
    public String des_user(int uid) {
        admin_repo.desabilitarUser(uid);
        return "redirect:ranking";
    }

    @RequestMapping(value = {"/active_user"}, method = GET)
    public String active_user(int uid) {
        admin_repo.abilitarUser(uid);
        return "redirect:ranking";
    }

    @RequestMapping(value = "/add_problem", method = RequestMethod.POST)
    public String AddProblema(ProblemaAd p, BindingResult bindingResult, HttpSession sessao,
            @RequestParam(value = "input[]") MultipartFile[] input,
            @RequestParam(value = "output[]") MultipartFile[] output) {

        if (bindingResult.hasErrors()) {

        }

        Vector<byte[]> a = new Vector<>(), b = new Vector<>();
        try {
            for (int i = 0; i < input.length; i++) {
                a.add(input[i].getBytes());
            }
            for (int i = 0; i < output.length; i++) {
                b.add(input[i].getBytes());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao ler arquivo");
        }
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        p.setSaida(b);
        p.setEntrada(a);
        p.setUsuario(u.getLogin());
        admin_repo.createProblem(p);
        return "redirect:problems";
    }

    @RequestMapping(value = {"/preview"}, method = GET)
    public String view_contest(int cid, Model model, HttpSession sessao) {
        Contest c = repositorio.get_contest(cid);
        model.addAttribute("contest", c);
        roles(model, sessao);
        return "contest/preview_contest";
    }

    @RequestMapping(value = {"/list_contest"}, method = GET)
    public String list_contest(Model model, HttpSession sessao) {
        List<Contest> lista = repositorio.listar_contest();
        model.addAttribute("contests", lista);
        roles(model, sessao);
        return "contest/list_contests";
    }

    @RequestMapping(value = {"/add_contest"}, method = GET)
    public String add_contest(HttpServletRequest request, Model model, HttpSession sessao) {
        roles(model, sessao);
        return "contest/add_contest";
    }

    @RequestMapping(value = {"/add_contest_form"}, method = RequestMethod.POST)
    public String add_contes_validar(HttpServletRequest request) {
        return "contest/edit_contest";
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
