/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Entidades.Contest;
import Entidades.PLanguage;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Submissao;
import Entidades.Usuario;
import Modelo.AdminRepositorio;
import Modelo.ContestRepositorio;
import Modelo.RepositorioMaster;
import Modelo.UsuarioRepositorio;
import Tools.CompararProblema;
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
        roles1(cid, model, sessao);
        return "contest/preview_contest";
    }

    @RequestMapping(value = {"/next_contest"}, method = GET)
    public String list_next_contest(Model model, HttpSession sessao) {
        roles2(model, sessao);
        List<Contest> lista = repositorio_contest.listar_contest();
        List<Contest> l = new ArrayList<>();
        for (Contest lista1 : lista) {
            if(lista1.isComing()){
                l.add(lista1);
            }
        }
        model.addAttribute("contests", l);
        return "contest/list_contests";
    }
    
    @RequestMapping(value = {"/running_contest"}, method = GET)
    public String list_running_contest(Model model, HttpSession sessao) {
        roles2(model, sessao);
        List<Contest> lista = repositorio_contest.listar_contest();
        List<Contest> l = new ArrayList<>();
        for (Contest lista1 : lista) {
            if(lista1.isRunning()){
                l.add(lista1);
            }
        }
        model.addAttribute("contests", l);
        return "contest/list_contests";
    }
    
    @RequestMapping(value = {"/past_contest"}, method = GET)
    public String list_past_contest(Model model, HttpSession sessao) {
        roles2(model, sessao);
        List<Contest> lista = repositorio_contest.listar_contest();
        List<Contest> l = new ArrayList<>();
        for (Contest lista1 : lista) {
            if(lista1.isPast()){
                l.add(lista1);
            }
        }
        model.addAttribute("contests", l);
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
     *
     * @param c
     * @param bindingResult
     * @param sessao
     * @param model
     * @param hora1
     * @param data1
     * @param hora2
     * @param data2
     * @return 
     */
    @RequestMapping(value = {"/add_contest_form"}, method = RequestMethod.POST)
    public String add_contes_validar(Contest c, BindingResult bindingResult, HttpSession sessao, Model model,
            @RequestParam("hora_inicio") String hora1, @RequestParam("inicio") String data1,
            @RequestParam("hora_fim") String hora2, @RequestParam("fim") String data2) {

        roles2(model, sessao);

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
     *
     * @param cid
     * @param pid
     *
     */
    @RequestMapping(value = {"/add_problem_contest"}, method = RequestMethod.POST)
    public String add_proble_contest(int cid, Model model, HttpSession sessao,
            @RequestParam("busca") int pid) {
        roles1(cid, model, sessao);
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
     * Remover um problema do concurso
     *
     * @param cid
     * @param pid
     *
     */
    @RequestMapping(value = {"/rem_problem_contest"}, method = RequestMethod.GET)
    public String rem_proble_contest(long cid, long pid, Model model, HttpSession sessao) {
        roles1(cid, model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.remove_problem_from_contest(cid, pid);
                return "redirect:edit_contest?cid=" + cid;
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }

    /**
     * Metodo para Adiconar um usuario no concurso
     *
     * @param cid
     * @param uid
     *
     */
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
    @RequestMapping(value = {"/rem_user_contest"}, method = RequestMethod.GET)
    public String rem_user_contest(long cid, long uid, Model model, HttpSession sessao) {
        roles1(cid, model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.remove_user_from_contest(cid, uid);
                return "redirect:edit_contest?cid=" + cid;
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }
    
    @RequestMapping(value = {"/desactive_contest"}, method = RequestMethod.GET)
    public String set_inactive_contest(long cid, Model model, HttpSession sessao) {
        roles1(cid, model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.desactive_contest(cid);
                return "redirect:list_contest";
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }
    
    /**
     * Activar a visibildade a um contest
     * @param cid
     * @param model
     * @param sessao
     * @return 
     */
    @RequestMapping(value = {"/active_contest"}, method = RequestMethod.GET)
    public String set_ctive_contest(long cid, Model model, HttpSession sessao) {
        roles1(cid, model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            return "redirect:index";
        } else {
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                //Valido
                model.addAttribute("admin", true);
                repositorio_contest.active_contest(cid);
                return "redirect:list_contest";
            } else {
                model.addAttribute("admin", false);
                return "redirect:index";
            }
        }

    }

    /**
     * Metodo para Ver a lista de problemas de um concurso
     *
     * @param cid
     *
     */
    @RequestMapping(value = {"/cproblems"}, method = GET)
    public String list_contest_problems(int cid, HttpServletRequest request, Model model,
            HttpSession sessao) {
        roles1(cid, model, sessao);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        Contest c = repositorio_contest.get_contest(cid);
        if (c.isRunning() || c.isPast()) {
            model.addAttribute("contest", c);
            return "contest/cproblems";
        }
        return "redirect:preview?cid=" + c.getId();
    }

    /**
     * Metodo Ver um problema no concurso
     *
     * @param cid
     * @param pid
     */
    @RequestMapping(value = {"/cproblem"}, method = GET)
    public String view_problem_contest(long cid, long pid, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio_master.buscarProblemaPorId(pid);
        Contest c = repositorio_contest.get_contest(cid);
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u != null) {
            if (repositorio_contest.isContestant(cid, u.getId())) {
                u.setContestant(true);
            }
        } else {
            return "redirect:preview?cid=" + cid;
        }
        model.addAttribute("contest", c);
        model.addAttribute("problema", p);
        model.addAttribute("user", u);
        roles1(cid, model, sessao);
        return "contest/cproblem";
    }

    /**
     * Metodo para Ver o Rank do concurso
     *
     * @param cid
     *
     */
    @RequestMapping(value = {"/crank"}, method = GET)
    public String list_contest_rank(int cid, HttpServletRequest request, Model model,
            HttpSession sessao) {

        roles1(cid, model, sessao);
        Contest c = repositorio_contest.get_contest(cid);
        if (c.isComing()) {
            return "redirect:preview?cid=" + c.getId();
        }

        List<Usuario> lista_u = new ArrayList<>();
        //De todos os usuario atualizar a sua penalidade em cada problema
        for (Usuario user : c.getUsers()) {
            user.setProblems(repositorio_contest.list_user_problem_contest(c, user.getId()));
            int quant_solved = 0;
            for (int i = 0; i < user.getProblems().size(); i++) {
                //Se a penalidade for maior q 0 seignifica q o usuario ja acertou este problema
                if (user.getProblems().get(i).getPenalidade() > 0) {
                    user.getProblems().get(i).setAccepted(true);
                    //Logo o tempo total de penaliddade do usuario aumenta
                    user.setTotal_time(user.getTotal_time() + user.getProblems().get(i).getPenalidade());
                    //Numero de acertados pelo o usuario
                    ++quant_solved;
                }
            }
            user.setSolved(quant_solved);
            lista_u.add(user);
        }

        //Ordenar a lista do rank segundo os pontos
        Collections.sort(lista_u);
        //Ordena os problemas por ID
        for (int i = 0; i < lista_u.size(); i++) {
            Collections.sort(lista_u.get(i).getProblems(), new CompararProblema());
        }
        

        model.addAttribute("contest", c);
        model.addAttribute("users", lista_u);
        return "contest/contest_rank";
    }

    @RequestMapping(value = "cuser_profile", method = GET)
    public String userProfile(long cid, long uid, HttpServletRequest request, Model model, HttpSession sessao) {
        Usuario u = repositorio_usuario.procurarPorId(uid);
        model.addAttribute("user", u);
        return "contest/cuser_profile";
    }

    @RequestMapping(value = "/csubmit", method = GET)
    public String submeter_problemas_Contest(long cid, long pid, Model model, HttpSession sessao) {
        ProblemaAd p = repositorio_master.buscarProblemaPorId(pid);
        Contest c = repositorio_contest.get_contest(cid);
        model.addAttribute("contest", c);
        model.addAttribute("problema", p);
        roles1(cid, model, sessao);
        return "contest/csubmit";
    }

    @RequestMapping(value = "/csubmission", method = RequestMethod.POST)
    public String enviarCProblema(long cid, long pid, @RequestParam("codigo") String codigo,
            @RequestParam("linguagem") String linguagem, HttpSession sessao) {
        Usuario online = (Usuario) sessao.getAttribute("usuario");
        Submissao s = new Submissao(pid, online.getId(), cid, codigo, linguagem, online.getLogin());
        repositorio_contest.submeter(s);
        return "redirect:cstatus?cid=" + cid;
    }

    @RequestMapping(value = "/cstatus", method = GET)
    public String listarSubmissoesContest(long cid, Model model, HttpSession sessao) {
        List<Submissao> lista = repositorio_contest.list_submission(cid);
        Contest c = repositorio_contest.get_contest(cid);
        model.addAttribute("contest", c);
        model.addAttribute("submisso", lista);
        roles1(cid, model, sessao);
        return "contest/cstatus";
    }
    
    @RequestMapping(value = {"/global_contest_statistics"}, method = GET)
    public String view_estatisticas(HttpServletRequest request, Model model, HttpSession sessao) {
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
        List<PLanguage> lista = repositorio_contest.statistics();
        model.addAttribute("languages", lista);
        return "contest/statistics";
    }
    
    @RequestMapping(value = {"/contest_statistics"}, method = GET)
    public String view_estatisticas_cid(long cid, HttpServletRequest request, Model model, HttpSession sessao) {
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
        Contest c = repositorio_contest.get_contest(cid);
        model.addAttribute("contest", c);
        List<PLanguage> lista = repositorio_contest.statistics(cid);
        model.addAttribute("languages", lista);
        return "contest/statistics_contest";
    }

    //Controla nivel de usuario e verifica se Ele é concorrente
    private void roles1(long cid, Model model, HttpSession sessao) {
        Usuario u = (Usuario) sessao.getAttribute("usuario");
        if (u == null) {
            model.addAttribute("online", false);

        } else {
            if (repositorio_contest.isContestant(cid, u.getId())) {
                u.setContestant(true);
            }
            model.addAttribute("online", true);
            if (u.getRole() == 0) {
                model.addAttribute("admin", true);
            } else {
                model.addAttribute("admin", false);

            }
        }
    }

    private void roles2(Model model, HttpSession sessao) {
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
