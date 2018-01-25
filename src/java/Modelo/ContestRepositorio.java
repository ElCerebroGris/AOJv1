/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Contest;
import java.util.List;

/**
 *
 * @author Zamba
 */
public interface ContestRepositorio {
    
    Contest get_contest(int id);
    Contest add_contest(Contest c);
    Contest edit_contest(Contest c);
    List<Contest> listar_contest();
    void delete_contest(int id);
    
}
