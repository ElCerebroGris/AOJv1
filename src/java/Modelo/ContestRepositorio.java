/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Contest;
import Entidades.Problema;
import Entidades.Submissao;
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
    void add_user_to_contest(long cid, long uid);
    Vector<Problema> list_problems_contest(long cid);
    Vector<Integer> list_user_contest(long cid);
    void submeter(Submissao s);
    boolean isContestant(long cid, long uid);
}
