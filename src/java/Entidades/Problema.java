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
public class Problema {

    private long id;

    private String nome;

    private String url_file;
    
    private int tempo_limite;
    private float pontos;
    private boolean visible;
    private int ac;
    private int total;

    private float percentagem;

    public Problema() {

    }

    public Problema(long id, String nome, String url_file) {
        this.id = id;
        this.nome = nome;
        this.url_file = url_file;
    }

    //Pra Receber
    public Problema(long id, String nome, int tempo_limite, float pontos, boolean visible) {
        this.id = id;
        this.nome = nome;
        this.tempo_limite = tempo_limite;
        this.pontos = pontos;
        this.visible = visible;
    }

    public Problema(long id, String nome, int tempo_limite, float pontos, boolean visible, int ac, int total) {
        this.id = id;
        this.nome = nome;
        this.tempo_limite = tempo_limite;
        this.pontos = pontos;
        this.visible = visible;
        this.ac = ac;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl_file() {
        return url_file;
    }

    public void setUrl_file(String url_file) {
        this.url_file = url_file;
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

    public float getPercentagem() {
        if (total != 0) {
            return (ac * 100) / total;
        } else {
            return 0;
        }
    }

    public void setPercentagem(float percentagem) {
        this.percentagem = percentagem;
    }

}
