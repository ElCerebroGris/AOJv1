/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Zamba
 */
public class Contest {
    
    private int id;
    private String nome;
    private Date inicio;
    private Date fim;
    private int frozen_time;
    private Vector<Integer> users;
    private Vector<Integer> problems;

    public Contest() {
    }

    public Contest(String nome, Date inicio, Date fim, int frozen_time, Vector<Integer> users, Vector<Integer> problems) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.frozen_time = frozen_time;
        this.users = users;
        this.problems = problems;
    }

    public Contest(int id, String nome, Date inicio, Date fim, int frozen_time, Vector<Integer> users, Vector<Integer> problems) {
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.frozen_time = frozen_time;
        this.users = users;
        this.problems = problems;
    }

    public Contest(int id, String nome, Date inicio, Date fim, int frozen_time) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.frozen_time = frozen_time;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public int getFrozen_time() {
        return frozen_time;
    }

    public void setFrozen_time(int frozen_time) {
        this.frozen_time = frozen_time;
    }

    public Vector<Integer> getUsers() {
        return users;
    }

    public void setUsers(Vector<Integer> users) {
        this.users = users;
    }

    public Vector<Integer> getProblems() {
        return problems;
    }

    public void setProblems(Vector<Integer> problems) {
        this.problems = problems;
    }
    
    
    
}
