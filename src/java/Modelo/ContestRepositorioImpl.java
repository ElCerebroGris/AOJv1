/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidades.Contest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zamba
 */
@Component
public class ContestRepositorioImpl implements ContestRepositorio {

    public ContestRepositorioImpl() {
    }

    @Override
    public Contest add_contest(Contest c) {
        return c;
    }

    @Override
    public Contest edit_contest(Contest c) {
        return c;
    }

    @Override
    public List<Contest> listar_contest() {
        List<Contest> l = new ArrayList<>();
        Date d1 = new Date(2017, 11, 2, 9, 30);
        
        l.add(new Contest(1,"Progressive L1", new Date(2017, 11, 2, 9, 30), new Date(2017, 11, 2, 14, 30), 20));
        l.add(new Contest(2,"Progressive L2", new Date(2017, 11, 3, 9, 30), new Date(2017, 11, 3, 14, 30), 20));
        return l;
    }

    @Override
    public void delete_contest(int id) {
        
    }

    @Override
    public Contest get_contest(int id) {
        return new Contest(id,"Progressive L2", new Date(2017, 11, 3, 9, 30), new Date(2017, 11, 3, 14, 30), 20);
    }
    
    
}
