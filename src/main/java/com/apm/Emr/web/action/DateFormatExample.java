package com.apm.Emr.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Java program to show how to format date in Java using SimpleDateFormat
 * Examples. Java allows to include date, time and timezone information
 * while formatting dates in Java.
 *
 * @author http://java67.blogspot.com
 */
public class DateFormatExample {

    public static void main(String args[]) {
      
        // This is how to get today's date in Java
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        DATE_FORMAT = new SimpleDateFormat("dd MMM yy / HH:mm");
        date = DATE_FORMAT.format(today);
//        System.out.println("Today in dd MM yy HH:mm:SSZ : " + date);
        
      
    } 
  
}