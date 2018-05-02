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
    
    private int tempo_limite;
    private float pontos;
    private boolean visible;
    private int ac;
    private int total;
    
    //Para concurso
    private String letra;
    private int penalidade;
    private boolean accepted;

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
    
    //Para concurso
    public ProblemaAd(long id, String nome, String url_file, String letra) {
        this.id = id;
        this.nome = nome;
        this.url_file = url_file;
        this.letra = letra;
    }

    //Pra Receber
    public ProblemaAd(long id, String nome, int tempo_limite, float pontos, boolean visible) {
        this.id = id;
        this.nome = nome;
        this.tempo_limite = tempo_limite;
        this.pontos = pontos;
        this.visible = visible;
    }

    public ProblemaAd(long id, String nome, int tempo_limite, float pontos, boolean visible, int ac, int total) {
        this.id = id;
        this.nome = nome;
        this.tempo_limite = tempo_limite;
        this.pontos = pontos;
        this.visible = visible;
        this.ac = ac;
        this.total = total;
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

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(int penalidade) {
        this.penalidade = penalidade;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getTempo_limite() {
        return tempo_limite;
    }

    public void setTempo_limite(int tempo_limite) {
        this.tempo_limite = tempo_limite;
    }

    public float getPontos() {
        return pontos;
    }

    public void setPontos(float pontos) {
        this.pontos = pontos;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
