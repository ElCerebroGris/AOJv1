/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Comentario;
import Entidades.Problema;
import Entidades.ProblemaAd;
import Entidades.Submissao;
import java.util.List;

/**
 *
 * @author Zamba
 */
public interface RepositorioMaster {
    
    void submeter(Submissao s);
    List<Submissao> listar_submissoes();
    List<Problema> listar_problemas();
    ProblemaAd buscarProblemaPorId(long id);
    void comentar(Comentario c);
    List<Comentario> lista_comentarios();
    
}
