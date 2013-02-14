/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author María Galbis
 */
public class DateFormat {
    
    public static String toMySql(Date fecha) {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }
    
    public static String fromMySql(Date fecha) {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(fecha);
    }
}
