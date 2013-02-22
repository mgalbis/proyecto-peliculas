/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Conversiones de formato
 *
 * @author María Galbis
 */
public class Format {

    public static String toMySql(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }
    
    public static int year(String fecha){
        fecha = fecha.substring(6, 10);
        return Integer.parseInt(fecha);
    }
    
    public static int month(String fecha){
        fecha = fecha.substring(3, 5);
        return Integer.parseInt(fecha);
    }
    
    public static int day(String fecha){
        fecha = fecha.substring(0, 2);
        return Integer.parseInt(fecha);
    }

    public static String fromMySql(Date fecha) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(fecha);
        int dia = calendar.get(Calendar.DAY_OF_MONTH); //dia del mes 
        int mes = calendar.get(Calendar.MONTH) + 1; //mes, de 0 a 11 
        int anyo = calendar.get(Calendar.YEAR); //año 

        return toLength(dia, 2) + "-" + toLength(mes, 2) + "-" + anyo;
    }

    public static String toLength(int num, int length) {
        String numero = String.valueOf(num);

        for (int i = numero.length(); i < length; i++) {
            numero = "0" + numero;
        }

        return numero;
    }

}
