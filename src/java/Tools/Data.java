/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Zamba
 */
public class Data {
    
    private Calendar data;
    
    public Data(Calendar data){
        this.data = data;
    }

    @Override
    public String toString() {
        return data.get(Calendar.YEAR)+"-"+data.get(Calendar.MONTH)+"-"+data.get(Calendar.DAY_OF_MONTH)+" "+
                data.get(Calendar.HOUR)+":"+data.get(Calendar.MINUTE)+":"+data.get(Calendar.SECOND);
    }
    
    public static String FormatarData(Date data) throws ParseException{
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = sdf1.parse(data.getYear()+"-"+data.getMonth()+"-"+data.getDay()
        +" "+data.getHours()+":"+data.getMinutes()+":"+data.getSeconds());
        return sdf1.format(d);
    }
}
