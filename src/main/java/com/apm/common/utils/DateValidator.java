package com.apm.common.utils;






 
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class DateValidator{
 
  private Pattern pattern;
  private Matcher matcher;
 
  private static final String DATE_PATTERN = 
          "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
 
  public DateValidator(){
	  pattern = Pattern.compile(DATE_PATTERN);
  }
 
  /**
   * Validate date format with regular expression
   * @param date date address for validation
   * @return true valid date fromat, false invalid date format
   */
   public boolean validate(String date){
	   
	   
	   String temp[] = null;
	   temp = date.split("/");
	   
	   if(temp[1].equals("Jan"))
		   temp[1] = "1";
	   if(temp[1].equals("Feb"))
		   temp[1] = "2";
	   if(temp[1].equals("Mar"))
		   temp[1] = "3";
	   if(temp[1].equals("Apr"))
		   temp[1] = "4";
	   if(temp[1].equals("May"))
		   temp[1] = "5";
	   if(temp[1].equals("Jun"))
		   temp[1] = "6";
	   if(temp[1].equals("Jul"))
		   temp[1] = "7";
	   if(temp[1].equals("Aug"))
		   temp[1] = "8";
	   if(temp[1].equals("Sep"))
		   temp[1] = "9";
	   if(temp[1].equals("Oct"))
		   temp[1] = "10";
	   if(temp[1].equals("Nov"))
		   temp[1] = "11";
	   if(temp[1].equals("Dec"))
		   temp[1] = "12";
	   
	   date = temp[0]+"/"+temp[1]+"/"+temp[2];
		   
	   
 
     matcher = pattern.matcher(date);
 
     if(matcher.matches()){
 
	 matcher.reset();
 
	 if(matcher.find()){
 
             String day = matcher.group(1);
	     String month = matcher.group(2);
	     int year = Integer.parseInt(matcher.group(3));
 
	     if (day.equals("31") && 
		  (month.equals("4") || month .equals("6") || month.equals("9") ||
                  month.equals("11") || month.equals("04") || month .equals("06") ||
                  month.equals("09"))) {
			return false; // only 1,3,5,7,8,10,12 has 31 days
	     } else if (month.equals("2") || month.equals("02")) {
                  //leap year
		  if(year % 4==0){
			  if(day.equals("30") || day.equals("31")){
				  return false;
			  }else{
				  return true;
			  }
		  }else{
		         if(day.equals("29")||day.equals("30")||day.equals("31")){
				  return false;
		         }else{
				  return true;
			  }
		  }
	      }else{				 
		return true;				 
	      }
	   }else{
    	      return false;
	   }		  
     }else{
	  return false;
     }			    
   }
   
   public static void main(String[] args) {
	  /* Date  date = new Date();
	   System.out.println(date.toString());*/
	   
	  DateValidator d1 = new DateValidator();
	  System.out.println(d1.validate("25-2-2012"));
	   
	   
	
}
}