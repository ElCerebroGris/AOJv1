/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Contest;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Submissao;
import Entidades.Usuario;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Zamba
 */
public interface ContestRepositorio {
    
    Contest get_contest(long id);
    Contest add_contest(Contest c);
    Contest edit_contest(Contest c);
    List<Contest> listar_contest();
    void delete_contest(int id);
    void add_problem_to_contest(long cid, long pid);
    void remove_problem_from_contest(long cid, long pid);
    void add_user_to_contest(long cid, long uid);
    void remove_user_from_contest(long cid, long uid);
    Vector<ProblemaAd> list_problems_contest(long cid);
    Vector<Usuario> list_user_contest(long cid);
    void submeter(Submissao s);
    Vector<Submissao> list_submission(long cid);
    boolean isContestant(long cid, long uid);
    Vector<ProblemaAd> list_user_problem_contest(Contest c, long uid);
}
