/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Zamba
 */
public class Comentario {
    
    private String usuario;
    private String texto;
    private String data;

    public Comentario(String usuario, String texto, String data) {
        this.usuario = usuario;
        this.texto = texto;
        this.data = data;
    }    

    public Comentario(String usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
