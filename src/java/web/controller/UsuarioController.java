/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Usuario;
import Modelo.UsuarioRepositorio;
import Tools.Validador;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Zamba
 */
@Controller
public class UsuarioController {

    private UsuarioRepositorio repositorio;

    @Autowired
    public UsuarioController(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String Autenticar(HttpServletRequest request, @RequestParam("login") String login,
            @RequestParam("senha") String senha, HttpSession sessao) {
        Usuario u = repositorio.procurarPorNome(login, senha);
        if (u != null) {
            sessao.setAttribute("usuario", u);
            String ref = request.getHeader("Referer");
            return "redirect:" + ref;
        }
        String ref = request.getHeader("Referer");
        return "redirect:" + ref;
    }

    @RequestMapping(value = "registrar", method = RequestMethod.GET)
    public String mostrarForm(Model model, HttpSession sessao) {
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
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @RequestMapping(value = "edit_user", method = RequestMethod.GET)
    public String mostrarFormEdit(long uid, HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u1 = repositorio.procurarPorId(uid);
        model.addAttribute("user", u1);

        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);
            return "redirect:/";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);
            }
        }
        return "edit_user";
    }

    @RequestMapping(value = "editar_usuario", method = RequestMethod.POST)
    public String validarEditar(Model model, @Valid Usuario user,
            BindingResult bindingResult, HttpSession sessao) {
        user = repositorio.editar(user);
        sessao.setAttribute("usuario", user);
        return "redirect:/perfil?uid="+user.getId();
    }

    @RequestMapping(value = "perfil", method = GET)
    public String userProfile(int uid, HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u1 = repositorio.procurarPorId(uid);
        model.addAttribute("user", u1);

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

        return "user_profile";
    }

    @RequestMapping(value = "ranking", method = RequestMethod.GET)
    public String ranking(Model model, HttpSession sessao) {
        List<Usuario> users = repositorio.listar();
        model.addAttribute("users", users);

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

        return "ranking";
    }

    @RequestMapping(value = "registrar", method = RequestMethod.POST)
    public String validarRegistro(Model model, @Valid Usuario user, 
            BindingResult bindingResult, HttpSession sessao) {
        user = repositorio.salvar(user);
        sessao.setAttribute("usuario", user);
        return "redirect:/perfil?uid="+user.getId();
    }

}
