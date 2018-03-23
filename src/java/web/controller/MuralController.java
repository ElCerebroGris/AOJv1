/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Comentario;
import Entidades.Usuario;
import Modelo.RepositorioMaster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Zamba
 */
@Controller
@SessionAttributes("usuario")
public class MuralController {

    private RepositorioMaster repositorio;

    @Autowired
    public MuralController(RepositorioMaster repositorio) {
        this.repositorio = repositorio;
    }
    
    @RequestMapping(value={"/comentar"}, method = RequestMethod.POST)
    public String home(HttpServletRequest request, HttpSession sessao,
            @RequestParam("texto") String texto) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        repositorio.comentar(new Comentario(u.getLogin(), texto));
        
        return "redirect:index";
    }
    
}
