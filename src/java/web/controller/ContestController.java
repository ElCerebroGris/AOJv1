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
import Tools.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Zamba
 */
@Controller
public class ContestController {

    private ContestRepositorio repositorio_contest;
    private UsuarioRepositorio repositorio_usuario;
    private AdminRepositorio admin_repo;
    private RepositorioMaster repositorio_master;

    @Autowired
    public ContestController(ContestRepositorio repositorio, UsuarioRepositorio repositorio2,
            AdminRepositorio admin_repo, RepositorioMaster repositorio_master) {
        this.repositorio_contest = repositorio;
        this.repositorio_usuario = repositorio2;
        this.admin_repo = admin_repo;
        this.repositorio_master = repositorio_master;
    }

    @RequestMapping(value = {"/preview"}, method = GET)
    public String view_contest(int cid, Model model, HttpSession sessao) {
        Contest c = repositorio_contest.get_contest(cid);
        Long timeToInit = ((c.getInicio().getTime()) - new Date().getTime()) / 1000L;
        model.addAttribute("contest", c);
        sessao.setAttribute("cid", c.getId());
        model.addAttribute("timeInit", timeToInit);
        roles1(model, sessao);
        return "contest/preview_contest";
    }

    @RequestMapping(value = {"/list_contest"}, method = GET)
    public String list_contest(Model model, HttpSession sessao) {
        List<Contest> lista = repositorio_contest.listar_contest();
        model.addAttribute("contests", lista);
        roles1(model, sessao);
        return "contest/list_contests";
    }

    @RequestMapping(value = {"/edit_contest"}, method = GET)
    public String edit_contest(int cid, Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                Contest c = repositorio_contest.get_contest(cid);
                model.addAttribute("contest", c);
                return "contest/edit_contest";
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }
    }

    @RequestMapping(value = {"/add_contest"}, method = GET)
    public String add_contest(HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                model.addAttribute("contest", new Contest());
                return "contest/add_contest";
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }
    }

    /**
     * Validar os dados do formulario para criação do concurso
     **/
    @RequestMapping(value = {"/add_contest_form"}, method = RequestMethod.POST)
    public String add_contes_validar(Contest c, BindingResult bindingResult, HttpSession sessao, Model model,
            @RequestParam("hora_inicio") String hora1, @RequestParam("inicio") String data1,
            @RequestParam("hora_fim") String hora2, @RequestParam("fim") String data2) {

        roles1(model, sessao);

        int h1 = Integer.parseInt(hora1.split(":")[0]);
        int min1 = Integer.parseInt(hora1.split(":")[1]);
        int h2 = Integer.parseInt(hora2.split(":")[0]);
        int min2 = Integer.parseInt(hora2.split(":")[1]);

        //Setar as datas pegas em String para o objecto Contest
        c.setInicio(Data.FormatarData(data1));
        c.setFim(Data.FormatarData(data2));

        //Setar o horario do contest no objecto Contest
        c.getInicio().setHours(h1);
        c.getInicio().setMinutes(min1);
        c.getInicio().setSeconds(0);
        c.getFim().setHours(h2);
        c.getFim().setMinutes(min2);
        c.getFim().setSeconds(0);

        Contest c1 = repositorio_contest.add_contest(c);
        sessao.setAttribute("contest", c1);

        return "redirect:edit_contest?cid=" + c1.getId();
    }

    /**
     * Adicionar um problema no concurso
     * @param cid
     * @param pid
     **/
    @RequestMapping(value = {"/add_problem_contest"}, method = RequestMethod.POST)
    public String add_proble_contest(int cid, Model model, HttpSession sessao,
            @RequestParam("busca") int pid) {
        roles1(model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.add_problem_to_contest(cid, pid);
                return "redirect:edit_contest?cid=" + cid;
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }

    /**
     * Adiconar um usuario no concurso
     * @param cid
     * @param  uid
     **/
    @RequestMapping(value = {"/add_user_contest"}, method = RequestMethod.POST)
    public String add_user_contest(int cid, Model model, HttpSession sessao,
            @RequestParam("buscaU") int uid) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.add_user_to_contest(cid, uid);
                return "redirect:edit_contest?cid=" + cid;
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }

    /**
     * Ver a lista de problemas de um concurso
     * @param  cid
     **/
    @RequestMapping(value = {"/cproblems"}, method = GET)
    public String list_contest_problems(int cid, HttpServletRequest request, Model model,
            HttpSession sessao) {
        roles1(model, sessao);
        Contest c = repositorio_contest.get_contest(cid);
        if (c.isRunning() || c.isPast()) {
            
            model.addAttribute("contest", c);
            return "contest/cproblems";
        }
        return "redirect:preview?cid="+c.getId();
    }

    /**
     * Ver um problema no concurso
     * @param  cid
     * @param pid
     */
    @RequestMapping(value = {"/cproblem"}, method = GET)
    public String list_contest(long cid, long pid, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio_master.buscarProblemaPorId(pid);
        Contest c = repositorio_contest.get_contest(cid);
        Usuario u = (Usuario)sessao.getAttribute("usuario");
        if(repositorio_contest.isContestant(cid, u.getId()))
            u.setContestant(true);
        model.addAttribute("contest", c);
        model.addAttribute("problema", p);
        roles1(model, sessao);
        return "contest/cproblem";
    }

    /**
     * Ver o Rank do concurso
     * @param cid
     **/
    @RequestMapping(value = {"/crank"}, method = GET)
    public String list_contest_rank(int cid, HttpServletRequest request, Model model,
            HttpSession sessao) {

        roles1(model, sessao);
        Contest c = repositorio_contest.get_contest(cid);
        if(c.isComing())
            return "redirect:preview?cid="+c.getId();
        
        List<Usuario> lista_p = new ArrayList<>();
        //Pegar todos os dados de cada Usuario atravez do seu ID
        for(Integer u : c.getUsers()){
            Usuario user = repositorio_usuario.procurarPorId(u); 
            for (int i = 0; i < c.getProblems().size(); i++) {
                user.getProblems().add(0);
            }
            lista_p.add(user);
        }
        lista_p.get(2).getProblems().set(0, 1);
        lista_p.get(1).getProblems().set(0, -1);
        
        //Ordenar a lista do rank segundo os pontos(num de problemas resolvidos)
        Collections.sort(lista_p);

        model.addAttribute("contest", c);
        model.addAttribute("users", lista_p);
        return "contest/contest_rank";
    }
    
    @RequestMapping(value = "cuser_profile", method = GET)
    public String userProfile(int uid, HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u = repositorio_usuario.procurarPorId(uid);
        model.addAttribute("user", u);
        return "contest/cuser_profile";
    }

    private void roles1(Model model, HttpSession sessao) {
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
