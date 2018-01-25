/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Usuario;
import Modelo.UsuarioRepositorio;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String mostrarForm() {
        return "register";
    }

    @RequestMapping(value = "perfil", method = GET)
    public String userProfile(int uid, HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u = repositorio.procurarPorId(uid);
        model.addAttribute("user", u);
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
                return "admin/users";
            } else {
                model.addAttribute("admin", false);
            }
        }

        return "ranking";
    }

    @RequestMapping(value = "registrar", method = RequestMethod.POST)
    public String validarRegistro(@RequestParam("login") String login,
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("gender") String gender,
            @RequestParam("birthday") String birthday,
            @RequestParam("country") String country,
            @RequestParam("institution") String institution,
            @RequestParam("email") String email,
            @RequestParam("gui_language") String gui_language,
            @RequestParam("password") String password,
            HttpSession sessao) {
        if (repositorio.procurarExistencia(login, email)) {
            return "register";
        } else {
            Usuario u = new Usuario(2, login, password, first_name, last_name, email, gender,
                    birthday, country, institution, gui_language, 0);
            repositorio.salvar(u);
            sessao.setAttribute("usuario", u);

            return "redirect:/index";
        }
    }

}
