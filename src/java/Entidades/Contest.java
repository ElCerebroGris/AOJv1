/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Tools.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Zamba
 */
public class Contest {

    private long id;
    private String nome;
    private Date inicio;
    private Date fim;
    private Vector<Integer> users;
    private Vector<Problema> problems;
    private String time_long;
    private boolean coming;
    private boolean running;
    private boolean past;

    public Contest() {
        
        users = new Vector<>();
        problems = new Vector<>();

    }

    public Contest(String nome, Date inicio, Date fim, Vector<Integer> users, Vector<Problema> problems) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.users = users;
        this.problems = problems;
    }

    public Contest(long id, String nome, Date inicio, Date fim, Vector<Integer> users, Vector<Problema> problems) {
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.users = users;
        this.problems = problems;
    }

    public Contest(long id, String nome, Date inicio, Date fim) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.id = id;
        users = new Vector<>();
        problems = new Vector();
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim(){
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Vector<Integer> getUsers() {
        return users;
    }

    public void setUsers(Vector<Integer> users) {
        this.users = users;
    }

    public Vector<Problema> getProblems() {
        return problems;
    }

    public void setProblems(Vector<Problema> problems) {
        this.problems = problems;
    }

    public String getTime_long(){
        return Data.toString(inicio);
    }

    public boolean isComing() {
        Calendar c = Calendar.getInstance();
        return c.getTime().before(inicio);
    }

    public boolean isRunning() {
        Calendar c = Calendar.getInstance();
        if(c.getTime().after(inicio) && c.getTime().before(fim))
            return true;
        return false;
    }

    public boolean isPast() {
        if(getInicio().getTime() - new Date().getTime()<0L)
            return true;
        else
            return false;
    }

}
