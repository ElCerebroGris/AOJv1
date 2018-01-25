/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Problema;
import Entidades.ProblemaAd;
import java.util.List;

/**
 *
 * @author Zamba
 */
public interface AdminRepositorio {
    
    void desabilitarProblema(long pid);
    void abilitarProblema(long pid);
    void desabilitarUser(long uid);
    void abilitarUser(long uid);
    List<Problema> listarProblema();
    ProblemaAd createProblem(ProblemaAd p);
    
}
