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
public class PLanguage {
    
    private String name;
    private long AC;
    private long TLE;
    private long WA;
    private long CE;
    private long RTE;

    public PLanguage() {
    }

    public PLanguage(String name, long AC, long TLE, long WA) {
        this.name = name;
        this.AC = AC;
        this.TLE = TLE;
        this.WA = WA;
    }

    public long getRTE() {
        return RTE;
    }

    public void setRTE(long RTE) {
        this.RTE = RTE;
    }

    public long getCE() {
        return CE;
    }

    public void setCE(long CE) {
        this.CE = CE;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAC() {
        return AC;
    }

    public void setAC(long AC) {
        this.AC = AC;
    }

    public long getTLE() {
        return TLE;
    }

    public void setTLE(long TLE) {
        this.TLE = TLE;
    }

    public long getWA() {
        return WA;
    }

    public void setWA(long WA) {
        this.WA = WA;
    }
    
    
    
}
