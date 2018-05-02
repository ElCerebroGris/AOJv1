/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Contest;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Usuario;
import Modelo.AdminRepositorio;
import Modelo.ContestRepositorio;
import Modelo.RepositorioMaster;
import Modelo.UsuarioRepositorio;
import Tools.ConfSingleton;
import Tools.Data;
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

    private ContestRepositorio repositorio_contest;
    private UsuarioRepositorio respositorio_usuario;
    private RepositorioMaster repositorio;
    private AdminRepositorio admin_repo;

    @Autowired
    public AdminController(ContestRepositorio repositorio_contest, UsuarioRepositorio repositorio2,
            AdminRepositorio admin_repo, RepositorioMaster repositorio) {
        this.repositorio_contest = repositorio_contest;
        this.respositorio_usuario = repositorio2;
        this.admin_repo = admin_repo;
        this.repositorio = repositorio;
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
        return "redirect:users_admin";
    }

    @RequestMapping(value = {"/active_user"}, method = GET)
    public String active_user(int uid) {
        admin_repo.abilitarUser(uid);
        return "redirect:users_admin";
    }

    @RequestMapping(value = {"/make_admin"}, method = GET)
    public String make_admin(int uid) {
        admin_repo.makeAdmin(uid);
        return "redirect:users_admin";
    }

    @RequestMapping(value = {"/unmake_admin"}, method = GET)
    public String unmake_admin(int uid) {
        admin_repo.unmakeAdmin(uid);
        return "redirect:users_admin";
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

    @RequestMapping(value = "users_admin", method = RequestMethod.GET)
    public String usersadmin(Model model, HttpSession sessao) {

        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                List<Usuario> users = admin_repo.listarUsuario();
                model.addAttribute("users", users);
                model.addAttribute("admin", true);
                return "admin/users";
            } else {
                model.addAttribute("admin", false);
            }
        }

        return "redirect:index";
    }
    
    @RequestMapping(value = "/problems_admin", method = GET)
    public String Listarproblemas(Model model, HttpSession sessao) {
        List<Problema> lista = repositorio.listar_problemas();
                
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                lista = admin_repo.listarProblema();
                model.addAttribute("problemas", lista);
                model.addAttribute("admin", true);
                return "admin/problems";
            } else {
                model.addAttribute("admin", false);
            }
        }
        
        model.addAttribute("problemas", lista);
        return "problems";
    }
    
    @RequestMapping(value = "/edit_problem", method = GET)
    public String verProblemaEditar(long pid, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio.buscarProblemaPorId(pid);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        model.addAttribute("problema", p);
        if (u == null) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("problem", p);
                model.addAttribute("admin", true);
                return "admin/edit_problem";
            } else {
                model.addAttribute("admin", false);
            }
        }
        return "desc_problema";
    }
}
