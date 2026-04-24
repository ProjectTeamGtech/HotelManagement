package com.apm.common.web.utils;





import java.util.StringTokenizer;

import com.apm.main.common.constants.Constants;
import com.apm.main.common.constants.RegEx;






public class  StringUtils {
	
	/**
	 * Method for retrieving first name, middle name and last name from given name 
	 * 
	 * @param combinedName User name as combined string 
	 * (consist of first name, middle name and last name) (middle name is optional)
	 * @return return first name, middle name and last name as string array, 
	 * 	if middle name is not present replace it with empty string
	 */
	public static String[] getDistinctNames(String combinedName){
		
		String[] distictNames = new String[3];		// it will hold first name, middle name and last name
		
		// Intailly set all array elements as empty string
		distictNames[0] = "";
		distictNames[1] = "";
		distictNames[2] = "";
		
		// if given combined name is null or empty, then return array with empty strings
		if(combinedName == null || combinedName.equals("")){
			return distictNames;
		} else {	
			
			combinedName.trim();
			
			// split combined name with space (one or more, if user accidently type more space between strings)
			String[] tempDistinctNames = combinedName.split(RegEx.ONE_OR_MORE_SPACE);
			
			if( tempDistinctNames.length == 1 ){		// if tempDistinctNames has one element, then set it as first name
				distictNames[0] = tempDistinctNames[0];
			}else if( tempDistinctNames.length == 2 ){	// if tempDistinctNames has two elements, then set it as first and last name
				distictNames[0] = tempDistinctNames[0];
				distictNames[2] = tempDistinctNames[1];
			} else if( tempDistinctNames.length == 3 ){	// if tempDistinctNames has three elements, then set it as first, middle and last name
				distictNames[0] = tempDistinctNames[0];
				distictNames[1] = tempDistinctNames[1];
				distictNames[2] = tempDistinctNames[2];
			}else if ( tempDistinctNames.length > 3 ){ // if tempDistinctNames has more than three elements, then set it as first, middle and last name
													   // where last name will contain all remaining strings (special case for South Indians :) )
				distictNames[0] = tempDistinctNames[0];
				distictNames[1] = tempDistinctNames[1];
				for(int i = 2; i < tempDistinctNames.length; i++){	// starting array from index 2,
																	// string at first and second index are set as first name and middle name
					distictNames[2] += tempDistinctNames[i]+" ";
				}
			}
			
		}
		
		
		return distictNames;
		
	}
	
	/**
	 * 
	 * Calculates experience on from given experience in year and months
	 * 
	 * @param experienceInYear experience in year
	 * @param experienceInMonths experience in month
	 * @return calculated experience, it is string since it may contain non integer characters
	 * return it in format: 'experienceInYear.experienceInMonths' or 'experienceInYear+' , for example '2.6','20+'
	 */
	public static String calculateExperience( String experienceInYear, String experienceInMonths ){
		
		StringBuilder  calculatedExperience = new StringBuilder ();	// it will hold calculated experience
		
		// if experience in year is null or empty then add 0 to calculated experience
		if(experienceInYear == null || experienceInYear.equals("")){
			calculatedExperience.append("0");
			
		}else if (experienceInYear.contains("+")){	// if experience in years contains + then set it as calculated experience and return
			calculatedExperience.append(experienceInYear);
			return calculatedExperience.toString();
			
		}else{
			calculatedExperience.append(experienceInYear);
			
			// if experience in month is null or empty then add 0 to calculated experience, separating with dot
			if(experienceInMonths == null || experienceInMonths.equals("")){
				calculatedExperience.append(".0");
				
			}else{		// if experience in month is not null or empty then add it to calculated experience, separating with dot
				calculatedExperience.append("."+experienceInMonths);
				
			}
		}
		
		return calculatedExperience.toString();
		
	}
	
	/**
	 * Calculates salary from salary in lacs and salary in hundred and append currency separated space
	 *  
	 * @param salaryInLacs salary in lacs
	 * @param salaryInThousand salary in thousands
	 * @param curreny currency
	 * @return calculate salary and return it in format: 'salaryInLac.salaryInThousand currency' or 'salaryInLac+ currency'
	 * for example '4.55 INR', '25+ USD'
	 */
	public static String calculateSalary (String salaryInLacs, String salaryInThousand, String curreny ){
		
		StringBuilder  calculatedSalary = new StringBuilder ();	// it will hold calculated salary
		
		
		// if salary in lacs is null or empty, then add '0' to calculated slary
		if(salaryInLacs == null || salaryInLacs.equals("")){ // if experience in years contains + then set it as calculated experience and return
			calculatedSalary.append("0");	
			
		} else if (salaryInLacs.contains("+")) {	// if salary in lacs contains + then set it as calculated salary and return
			calculatedSalary.append(salaryInLacs+" "+curreny);
			return calculatedSalary.toString();
			
		}else{
			
			calculatedSalary.append(salaryInLacs);
			
			// if salary in thousands is null or empty then add 0 to calculated salary, separating with dot
			if(salaryInThousand == null || salaryInThousand.equals("")){
				calculatedSalary.append(".0");
				
			}else{		// if salary in thousands is not null or empty then add it to calculated salary, separating with dot
				calculatedSalary.append("."+salaryInThousand);
				
			}
			
		}
		
		// finally add currency to calculated salary
		calculatedSalary.append(" "+curreny);
		
		return calculatedSalary.toString();
		
	}
	
	/**
	 * 
	 * Formulate the comma separated string from given array of strings
	 * 
	 * @param arrayOfStrings array of strings
	 * @return comma separated string
	 */
	public static String getCommaSeparatedString(String[] arrayOfStrings){
		
		StringBuilder  commaSeparatedString = new StringBuilder ();		// it will hold calculatted comma seperated string
		
		// if array of string is not null then calculatte comma separated string, else return empty string
		if(arrayOfStrings != null){			
			// concat string from array to comma separated string
			for( int i = 0; i < arrayOfStrings.length; i++ ){
				commaSeparatedString.append(arrayOfStrings[i]+",");
			}
			// remove last extra comma
			commaSeparatedString = commaSeparatedString.deleteCharAt(commaSeparatedString.lastIndexOf(","));
		}
		
		return commaSeparatedString.toString();
		
	}
	
	public static String[] getStringArray(String string){
		
		String[] distictString = new String[3];	
		
		distictString[0] = "";
		distictString[1] = "";
		distictString[2] = "";	
		
		StringTokenizer expInYearAndMonth = new StringTokenizer(string,".");		
		distictString[0] = expInYearAndMonth.nextToken();
		
		StringTokenizer s = new StringTokenizer(expInYearAndMonth.nextToken()," ");		
		distictString[1] = s.nextToken();
		
		if(s.hasMoreTokens()){
			distictString[2] = s.nextToken();			
		}
		
		
		return distictString;
	}
	
	public static String[] getYearAndMonth(String string){
		
		String[] distictString = new String[3];	
		
		distictString[0] = "";
		distictString[1] = "";		
		distictString[2] = "";	
		
		StringTokenizer expInYearAndMonth = new StringTokenizer(string,"-");		
		distictString[0] = expInYearAndMonth.nextToken();
		
		StringTokenizer s = new StringTokenizer(expInYearAndMonth.nextToken()," ");		
		distictString[1] = s.nextToken();
		
		if(s.hasMoreTokens()){
			distictString[2] = s.nextToken();			
		}
		
		
		return distictString;
	}
}




