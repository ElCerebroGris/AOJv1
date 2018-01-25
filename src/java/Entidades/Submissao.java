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
public class Submissao {
    
    private long id_submissao;
    private long id_problema;
    private long id_usuario;
    private String status;
    private String url;
    private String linguagem;
    private String data;

    private String codigo;
    private String login_user;
    
    public Submissao(){}

    //Pra enviar
    public Submissao(long id_problema, long id_usuario, String codigo, String url, String linguagem, 
            String usuario) {
        this.id_problema = id_problema;
        this.id_usuario = id_usuario;
        this.codigo = codigo;
        this.url = url;
        this.linguagem = linguagem;
        this.login_user = usuario;
    }

    //Pra receber
    public Submissao(long id_submissao, long id_problema, long id_usuario, String status, 
            String lingagem, String data, String usuario) {
        this.id_submissao = id_submissao;
        this.id_problema = id_problema;
        this.id_usuario = id_usuario;
        this.status = status;
        this.linguagem = lingagem;
        this.data = data;
        this.login_user = usuario;
    }

    public long getId_problema() {
        return id_problema;
    }

    public void setId_problema(long id_problema) {
        this.id_problema = id_problema;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId_submissao() {
        return id_submissao;
    }

    public void setId_submissao(long id_submissao) {
        this.id_submissao = id_submissao;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String lingagem) {
        this.linguagem = lingagem;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
