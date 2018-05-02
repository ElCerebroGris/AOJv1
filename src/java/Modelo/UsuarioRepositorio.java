/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Usuario;
import java.util.List;

/**
 *
 * @author Zamba
 */
public interface UsuarioRepositorio {
    
    List<Usuario> listar();
    Usuario procurar(long id);
    Usuario salvar(Usuario u);
    Usuario procurarPorNome(String login, String senha);
    Usuario procurarPorId(long uid);
    boolean procurarExistencia(String login, String email);
    
}
