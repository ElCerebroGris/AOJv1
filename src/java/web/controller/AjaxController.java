/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import Tools.Data;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Zamba
 */
@Controller
public class AjaxController {
    
    @RequestMapping("/get_time")
    @ResponseBody
    public String getServerTime(){
        Date d = new Date();
        return Data.toString(d).split(" ")[1];             
    }
}
