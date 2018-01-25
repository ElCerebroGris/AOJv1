/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Entidades.Submissao;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Zamba
 */
public class Conectar {
    
    
    public DataSource dataSource(){
        
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/online");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("postgre");
        ds.setPassword("zamba23");
        return ds;
        
    }
    
    
    public JdbcTemplate template(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    
}
