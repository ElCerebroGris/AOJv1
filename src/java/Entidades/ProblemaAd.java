/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Vector;

/**
 *
 * @author Zamba
 */
public class ProblemaAd {
    
    private String nome;
    
    private int tempo;
    
    private String texto;
    
    private String expecificacaoEntrada;
    private String expecificacaoSaida;
    private String exemploEntrada;
    private String exemploSaida;
    private String category;
    
    private Vector<byte[]> entrada;
    private Vector<byte[]> saida;
    
    private String usuario;
    
    private long id;
    private String url_file;

    public ProblemaAd() {
    }

    //Adicionar
    public ProblemaAd(String nome, int tempo, String texto, String expecificacaoEntrada, String expecificacaoSaida,
            String exemploEntrada, String exemploSaida, Vector<byte[]> entrada, Vector<byte[]> saida, String usuario, 
            String category) {
        this.nome = nome;
        this.tempo = tempo;
        this.texto = texto;
        this.expecificacaoEntrada = expecificacaoEntrada;
        this.expecificacaoSaida = expecificacaoSaida;
        this.exemploEntrada = exemploEntrada;
        this.exemploSaida = exemploSaida;
        this.entrada = entrada;
        this.saida = saida;
        this.usuario = usuario;
        this.category = category;
    }

    public ProblemaAd(long id, String nome, int tempo, String texto, String expecificacaoEntrada,
            String expecificacaoSaida, String exemploEntrada, String exemploSaida, String usuario) {
        this.nome = nome;
        this.tempo = tempo;
        this.texto = texto;
        this.expecificacaoEntrada = expecificacaoEntrada;
        this.expecificacaoSaida = expecificacaoSaida;
        this.exemploEntrada = exemploEntrada;
        this.exemploSaida = exemploSaida;
        this.usuario = usuario;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getExemploEntrada() {
        return exemploEntrada;
    }

    public void setExemploEntrada(String exemploEntrada) {
        this.exemploEntrada = exemploEntrada;
    }

    public String getExemploSaida() {
        return exemploSaida;
    }

    public void setExemploSaida(String exemploSaida) {
        this.exemploSaida = exemploSaida;
    }

    public Vector<byte[]> getEntrada() {
        return entrada;
    }

    public void setEntrada(Vector<byte[]> entrada) {
        this.entrada = entrada;
    }

    public Vector<byte[]> getSaida() {
        return saida;
    }

    public void setSaida(Vector<byte[]> saida) {
        this.saida = saida;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl_file() {
        return url_file;
    }

    public void setUrl_file(String url_file) {
        this.url_file = url_file;
    }

    public String getExpecificacaoEntrada() {
        return expecificacaoEntrada;
    }

    public void setExpecificacaoEntrada(String expecificacaoEntrada) {
        this.expecificacaoEntrada = expecificacaoEntrada;
    }

    public String getExpecificacaoSaida() {
        return expecificacaoSaida;
    }

    public void setExpecificacaoSaida(String expecificacaoSaida) {
        this.expecificacaoSaida = expecificacaoSaida;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
