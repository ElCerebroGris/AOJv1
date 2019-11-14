/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Zamba
 */
public class Team extends Usuario {

    private String team_name;
    private String user1;
    private String user2;
    private String user3;
    //Só pode ser usado durante o concurso
    private boolean enabled;

    public Team() {
    }    
    
    public Team(String team_name, String el1, String el2, String el3, boolean enabled) {
        this.team_name = team_name;
        this.user1 = el1;
        this.user2 = el2;
        this.user3 = el3;
        this.enabled = enabled;
    } 

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser3() {
        return user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
    
}
