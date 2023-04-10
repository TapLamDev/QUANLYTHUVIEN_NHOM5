/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static Date toDate(String date, String pattern){
        try {
            if(date == null){
               return XDate.now();
            }
            if(pattern.length()>0){
                formater.applyPattern(pattern);
            }
            
            return formater.parse(date);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String toString(Date date, String pattern){
        if(date == null){
               date = XDate.now();
            }
       if(pattern.length()>0){
            formater.applyPattern(pattern);
       }
        
        return formater.format(date);
    }
    
    public static Date addDays(Date date,long days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    
    public static Date now(){
        return new Date();
    }
}
