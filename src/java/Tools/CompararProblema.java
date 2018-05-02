/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entidades.ProblemaAd;
import java.util.Comparator;

/**
 *
 * @author Zamba
 */
public class CompararProblema implements Comparator<ProblemaAd> {

    @Override
    public int compare(ProblemaAd o1, ProblemaAd o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
    
}
