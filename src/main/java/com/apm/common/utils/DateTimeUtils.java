package com.apm.common.utils;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.web.common.ApmDate;
import com.apm.main.common.constants.Constants;


public class DateTimeUtils {

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Calcutta")); // set time
																	// zone for
																	// Indian
																	// standard
																	// time
	}

	/** Time unit in years */
	public static final int UNIT_YEARS = 1;
	/** Time unit in months */
	public static final int UNIT_MONTHS = 2;
	/** Time unit in weeks */
	public static final int UNIT_WEEKS = 3;
	/** Time unit in days */
	public static final int UNIT_DAYS = 4;
	/** Time unit in hours */
	public static final int UNIT_HOURS = 5;
	/** Time unit in minutes */
	public static final int UNIT_MINUTES = 6;
	/** Time unit in seconds */
	public static final int UNIT_SECONDS = 7;

	/** milliseconds in one average year */
	private static final long MS_IN_YEAR = 31556952000l;
	/** milliseconds in one average month */
	private static final long MS_IN_MONTH = 2592000000l;
	/** milliseconds in one week */
	private static final long MS_IN_WEEK = 604800000l;
	/** milliseconds in one day */
	private static final long MS_IN_DAY = 86400000l;
	/** milliseconds in one hour */
	private static final long MS_IN_HOUR = 3600000l;
	/** milliseconds in one minute */
	private static final long MS_IN_MINUTE = 60000l;
	/** milliseconds in one second */
	private static final long MS_IN_SECOND = 1000l;

	/**
	 * Get current date
	 * 
	 * @return Date current date
	 */
	public static Date getCurrentDateTime() {

		Date currentDate = null;

		// get calender instance for
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));

		// get current date
		currentDate = calendar.getTime();

		return currentDate;

	}

	/**
	 * Return the difference between two dates in milliseconds
	 * 
	 * @param olderDate
	 *            older date
	 * @param newerDate
	 *            newer date
	 * @return difference in milliseconds
	 */
	public static long getDateDifferenceInMillis(Date olderDate, Date newerDate) {

		long difference = 0;

		long oldMillis = olderDate.getTime(); // get milliseconds in older date
		long newMillis = newerDate.getTime(); // get milliseconds in newer date

		difference = newMillis - oldMillis; // get difference in milliseconds

		return difference;

	}

	/**
	 * *
	 * 
	 * Checks if given date is expired against given expiration period
	 * 
	 * @param startDate
	 *            when expiration period started
	 * @param expPeriod
	 *            expiration period
	 * @param expPeriodUnit
	 *            unit of expiration period. Values should be one of the
	 *            following:
	 *            <ul>
	 *            <li>DateTimeUtils.UNIT_YEARS</li>
	 *            <li>DateTimeUtils.UNIT_MONTHS</li>
	 *            <li>DateTimeUtils.UNIT_WEEKS</li>
	 *            <li>DateTimeUtils.UNIT_DAYS</li>
	 *            <li>DateTimeUtils.UNIT_HOURS</li>
	 *            <li>DateTimeUtils.UNIT_MINUTES</li>
	 *            <li>DateTimeUtils.UNIT_SECONDS</li>
	 *            </ul>
	 * @return true if date is expired, else false
	 * @throws Exception
	 *             may throw exception if expPeriodUnit value is other than
	 *             above mentioned values
	 */
	public static boolean isExpired(Date startDate, int expPeriod, int expPeriodUnit) throws Exception {

		boolean isExpired = false;

		long converter = 1; // initialize converter

		switch (expPeriodUnit) { // switch to given expiration unit and set
									// appropriate converter
		case UNIT_YEARS: {
			converter = MS_IN_YEAR;
			break;
		}
		case UNIT_MONTHS: {
			converter = MS_IN_MONTH;
			break;
		}
		case UNIT_WEEKS: {
			converter = MS_IN_WEEK;
			break;
		}
		case UNIT_DAYS: {
			converter = MS_IN_DAY;
			break;
		}
		case UNIT_HOURS: {
			converter = MS_IN_HOUR;
			break;
		}
		case UNIT_MINUTES: {
			converter = MS_IN_MINUTE;
			break;
		}
		case UNIT_SECONDS: {
			converter = MS_IN_SECOND;
			break;
		}
		default: { // if unit value is out of range then throw exception
			throw new Exception("Invalid Unit of Expiration Period");
		}
		}

		long expPeriodInMilis = expPeriod * converter; // convert expiration
														// time to milliseconds
		long timeDiffInMillis = getDateDifferenceInMillis(startDate, getCurrentDateTime()); // get
																							// difference
																							// in
																							// milliseconds
																							// between
																							// start
																							// date
																							// and
																							// current
																							// date
		// if time difference is greater than expiration time, then date expired
		if (timeDiffInMillis >= expPeriodInMilis) {
			isExpired = true;
		}

		return isExpired;

	}

	/**
	 * get date in "dd-MM-yyyy' at 'hh:mm a"
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateinSimpleStringFormate(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy' at 'hh:mm a");
		String s = df.format(date);
		return s;
	}

	public static String getDateinSimpleFormate(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy' at 'hh-mm a");
		String s = df.format(date);
		return s;
	}

	/**
	 * this method is used to parse current date into sql timestamp
	 * 
	 * @return currentTimestamp
	 */
	public static Timestamp getCurrentDateInSQLCasting() {

		Date currentDate = getCurrentDateTime();

		Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

		return currentTimestamp;
	}

	public static String getCurrentDateInDDMMYYYYCasting() {

		Date currentDate = getCurrentDateTime();

		// Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
		String datePickerFromer = "";

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		datePickerFromer = df.format(currentDate);

		return datePickerFromer;

	}

	/*
	 * toDateFromUKFormat() method converts string in the UK format "dd/mm/YYYY"
	 * to date
	 */
	public static Calendar toDateFromUKFormat(String strDate) {
		Calendar calendar = Calendar.getInstance();
		String[] dateStr = strDate.split("/");
		calendar.set(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]) - 1, Integer.parseInt(dateStr[0]), 0, 0,
				0);
		return calendar;

	}

	/**
	 * get provided date 'dd/MM/yyyy' in 'dd-MM-yyyy HH:mm:ss'
	 * 
	 * @param dateInDDMMYYYY
	 * @return
	 */
	public static Date getConverDateToStandardPattern(String dateInDDMMYYYY) {
		String currentDate = "";

		Calendar cToDate = toDateFromUKFormat(dateInDDMMYYYY);

		Date dToDate = cToDate.getTime();

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		currentDate = df.format(dToDate);

		try {
			dToDate = df.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dToDate;
	}

	/**
	 * Conver to date picker formate i.e dd/MM/YYYY
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDatePickerFormate(Date date) {
		String datePickerFromer = "";

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		datePickerFromer = df.format(date);

		return datePickerFromer;
	}

	public static String changeDateFormat(String date) {

		String temp[] = date.split("/");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];

		return date;
	}

	public static String changeDateFormattoPicker(String date) {

		String temp[] = date.split("-");
		date = temp[2] + "/" + temp[1] + "/" + temp[0];

		return date;
	}

	public static String getWeekShortName(String weekname) {

		String shortname = "";
		if (weekname.equals(Constants.MONDAY)) {
			shortname = "M";
		}
		if (weekname.equals(Constants.TUESDAY)) {
			shortname = "T";
		}
		if (weekname.equals(Constants.WEDNEDAY)) {
			shortname = "W";
		}
		if (weekname.equals(Constants.THUSRDAY)) {
			shortname = "T";
		}
		if (weekname.equals(Constants.FRIDAY)) {
			shortname = "F";
		}
		if (weekname.equals(Constants.SATURDAY)) {
			shortname = "S";
		}
		if (weekname.equals(Constants.SUNDAY)) {
			shortname = "S";
		}

		return shortname;
	}

	public static String getYearNumber(String year) {
		String yearNumber = "";
		if (year.equals(Constants.JAN)) {
			yearNumber = "01";
		}
		if (year.equals(Constants.FEB)) {
			yearNumber = "02";
		}
		if (year.equals(Constants.MAR)) {
			yearNumber = "03";
		}
		if (year.equals(Constants.APR)) {
			yearNumber = "04";
		}

		if (year.equals(Constants.MAY)) {
			yearNumber = "05";
		}
		if (year.equals(Constants.Jun)) {
			yearNumber = "06";
		}
		if (year.equals(Constants.JUL)) {
			yearNumber = "07";
		}
		if (year.equals(Constants.AUG)) {
			yearNumber = "08";
		}

		if (year.equals(Constants.SEP)) {
			yearNumber = "09";
		}
		if (year.equals(Constants.OCT)) {
			yearNumber = "10";
		}
		if (year.equals(Constants.NOV)) {
			yearNumber = "11";
		}
		if (year.equals(Constants.DEC)) {
			yearNumber = "12";
		}

		return yearNumber;

	}

	public static void main(String arr[]) {

		// getConverDateToStandardPattern("18/07/2012");
		// Calendar cToDate = toDateFromUKFormat("18/07/2012");

		/*
		 * Date dToDate = new Date();
		 * 
		 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); String s =
		 * df.format(dToDate);
		 * 
		 * System.out.println(s); System.out.println(dToDate.toString());
		 * System.out.println(dToDate);
		 * System.out.println(dToDate.toGMTString());
		 * System.out.println(dToDate.toLocaleString());
		 */

		int[] s = new int[5];

		for (int i = 0; i < 5; i++) {
			s[i] = i;
		}
		// System.out.println();
	}

	public static ApmDate getApmDate(String commencing, int num) {
		ApmDate apmDate = new ApmDate();
		String temp[] = commencing.split("/");
		int date = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);

		int year = Integer.parseInt(temp[2]);
		date = date + num;

		apmDate.setDate(date);
		apmDate.setMonth(month);
		apmDate.setYear(year);

		if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
				|| (month == 12)) {
			if (date > 31) {
				date = date - 31;
				apmDate.setDate(date);

				month = month + 1;
				apmDate.setMonth(month);

				if (month == 13) {
					year = year + 1;
					apmDate.setYear(year);
					month = 1;
					apmDate.setMonth(month);
				}
			}
		} else if (month == 2) {
			if (year % 4 == 0) {

				if (date > 29) {
					// caldate = parseInt(caldate) - 1
					date = date - 29;
					apmDate.setDate(date);

					month = month + 1;
					apmDate.setMonth(month);
				}
			} else {

				if (date > 28) {
					// caldate = parseInt(caldate) - 1
					date = date - 28;
					apmDate.setDate(date);

					month = month + 1;
					apmDate.setMonth(month);
				}
			}

		} else {
			if (date > 30) {
				date = date - 30;
				apmDate.setDate(date);

				month = month + 1;
				apmDate.setMonth(month);
			}
		}

		return apmDate;
	}

	public static String getInvoiceCommencingDate(String commencing) {

		String date = "";

		try {

			String temp[] = commencing.split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];

		} catch (Exception e) {

			e.printStackTrace();
		}

		return date;
	}

	public static String getCommencingDate(String commencing) {

		String temp[] = commencing.split("/");
		commencing = temp[2] + "-" + temp[1] + "-" + temp[0];

		return commencing;
	}

	public static String getCommencingDatePicker(String commencing) {

		String temp[] = commencing.split("/");
		commencing = temp[0] + "-" + temp[1] + "-" + temp[2];

		return commencing;
	}

	public static String getCommencingDate1(String commencing) {

		String data = "";
		try {
			if (!isNull(commencing).equals("")) {
				String temp[] = commencing.split("-");
				commencing = temp[2] + "-" + temp[1] + "-" + temp[0];
				data = commencing;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return data;
	}

	public static String getCommencingDate2(String commencing) {

		String temp[] = commencing.split("-");
		commencing = temp[2] + "/" + temp[1] + "/" + temp[0];

		return commencing;
	}

	public static String getCommencingDate3(String commencing) {

		String temp[] = commencing.split("/");
		commencing = temp[2] + "/" + temp[1] + "/" + temp[0];

		return commencing;
	}

	public static String getCommencingDate4(String commencing) {

		String temp[] = commencing.split("/");
		commencing = temp[2] + "-" + temp[1] + "-" + temp[0];

		return commencing;
	}
	public static String getTime(String time) {

		String stemp[] = time.split(" ");

		return stemp[3];
	}

	public static String getSimpleDateFormat(Date myDate) {

		String date = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
		return date;
	}

	public static Long getDifferenceOfTwoDate(String d1, String d2) throws ParseException {

		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String inputString1 = d1;
		String inputString2 = d2;

		Date date1 = myFormat.parse(inputString1);
		Date date2 = myFormat.parse(inputString2);
		long diff = date2.getTime() - date1.getTime();
		// System.out.println ("Days: " + TimeUnit.DAYS.convert(diff,
		// TimeUnit.MILLISECONDS));
		long result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		return result;

	}

	public static int getDifferanceofDateWithTime(String dateStart, String dateStop, int chargeHour) {
		if (chargeHour == 0) {
			chargeHour = 24;
		}
		/*
		 * String dateStart = "10-06-2017 13:45:00"; String dateStop =
		 * "13-06-2017 20:15:00";
		 */

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		int duration = 0;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			// System.out.print(diffDays + " days, ");
			// System.out.print(diffHours + " hours, ");
			// System.out.print(diffMinutes + " minutes, ");
			// System.out.print(diffSeconds + " seconds.");

			if (chargeHour != 0) {
				duration = 24 * (int) diffDays;
				int result = duration + (int) diffHours;

				duration = result / chargeHour;

				/*
				 * if(result%chargeHour!=0){ duration++; }
				 */
			}

			/*
			 * if(diffMinutes>0){ duration++; }
			 */

			if (chargeHour == 0) {
				duration = (int) diffDays;
			}

			// System.out.println(duration);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return duration;

	}

	public static Long getDifferenceOfTwoDateDBFormat(String d1, String d2) throws ParseException {

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String inputString1 = d1;
		String inputString2 = d2;

		Date date1 = myFormat.parse(inputString1);
		Date date2 = myFormat.parse(inputString2);
		long diff = date2.getTime() - date1.getTime();
		// System.out.println ("Days: " + TimeUnit.DAYS.convert(diff,
		// TimeUnit.MILLISECONDS));
		long result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		return result;

	}

	public static Long getDifferenceOfTwoDateDBFormatinHours(String d1, String d2) throws ParseException {

		long result = 0;
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String inputString1 = d1;
			String inputString2 = d2;

			Date date1 = myFormat.parse(inputString1);
			Date date2 = myFormat.parse(inputString2);
			long diff = date2.getTime() - date1.getTime();
			// System.out.println ("Days: " + TimeUnit.DAYS.convert(diff,
			// TimeUnit.MILLISECONDS));
			result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	public static String getTotalofTwoTime(String startTime, String duration) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String resultHour = "";
		String resultMinute = "";

		startTime = startTime + ":" + "00";
		String temp[] = duration.split(":");
		int hh = Integer.parseInt(temp[0]);
		int mm = Integer.parseInt(temp[1]);

		Date d = df.parse("2008-04-16 " + startTime + " ");
		Calendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.HOUR, hh);
		gc.add(Calendar.MINUTE, mm);
		Date d2 = gc.getTime();

		int hour = d2.getHours();
		resultHour = Integer.toString(hour);
		if (hour <= 9) {
			resultHour = "0" + resultHour;
		}

		int minute = d2.getMinutes();
		resultMinute = Integer.toString(minute);
		if (minute <= 9) {
			resultMinute = "0" + resultMinute;
		}

		String result = resultHour + ":" + resultMinute;

		return result;

	}

	public static String getSumofTime(String time1, String time2) {

		String sumTime = "";
		try {

			time1 = "0:" + time1;
			time2 = "0:" + time2;

			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			Date date1 = timeFormat.parse(time1);
			Date date2 = timeFormat.parse(time2);

			long sum = date1.getTime() + date2.getTime();

			String date3 = timeFormat.format(new Date(sum));
			// System.out.println("The sum is "+date3);

			String temp[] = date3.split(":");

			sumTime = temp[1] + ":" + temp[2];

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sumTime;

	}

	// get duration

	public static String getDuration(String startTime, String stopTime) {

		/*
		 * String startTime = "10:30"; String stopTime = "11:30";
		 */

		String dateStart = "01/14/2012 " + startTime + ":00";
		String dateStop = "01/15/2012 " + stopTime + ":00";

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		String duration = "";

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			/*
			 * System.out.print(diffDays + " days, ");
			 * System.out.print(diffHours + " hours, ");
			 * System.out.print(diffMinutes + " minutes, ");
			 * System.out.print(diffSeconds + " seconds.");
			 */

			String hh = "";
			String mm = "";

			if (diffHours <= 9) {
				hh = "0" + diffHours;
			} else {
				hh = Long.toString(diffHours);
			}

			if (diffMinutes <= 9) {
				mm = "0" + diffMinutes;
			} else {
				mm = Long.toString(diffMinutes);
			}

			duration = hh + ":" + mm;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return duration;
	}

	public static String getMonthName(String month) {

		String arr[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		int x = Integer.parseInt(month);

		x = x - 1;

		String monthName = arr[x];

		return monthName;
	}

	public static String getWeekName(int year, int month, int day) throws ParseException {
		// First convert to Date. This is one of the many ways.
		String dateString = String.format("%d-%d-%d", year, month, day);
		Date date = new SimpleDateFormat("yyyy-M-d").parse(dateString);

		// Then get the day of week from the Date based on specific locale.
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

		// System.out.println(dayOfWeek); // Friday
		return dayOfWeek;
	}

	public static String changeFormat(double x) {

		String str = "0.00";
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");
			str = formatter.format(x);
		} catch (Exception e) {
		}
		return str;

	}

	public static String changeStringFormat(String val) {

		String str = "0.00";
		try {
			if (val == null) {
				val = "0";
			}
			if (val.equals("")) {
				val = "0";
			}

			double x = Double.parseDouble(val);
			NumberFormat formatter = new DecimalFormat("#0.00");
			str = formatter.format(x);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return str;
	}

	public static String changeDateFormatToddmmyyyy(String date) {
		String temp[] = date.split("-");
		date = temp[2] + "-" + temp[1] + "-" + temp[0];
		return date;
	}

	public static String changeDateFormatTemplate(String date) {

		String weekname[] = { "month", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		// String date = "25-12-2015";

		String temp[] = date.split("-");

		String newdate = temp[0] + " " + weekname[Integer.parseInt(temp[1])] + " " + temp[2];

		// System.out.println(newdate);

		return newdate;
	}

	public static String currentDateDDMMYYandTime() {
		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(today);
		DATE_FORMAT = new SimpleDateFormat("dd MMM yy / HH:mm");
		date = DATE_FORMAT.format(today);
		// System.out.println("Today in dd MM yy HH:mm:SSZ : " + date);
		return date;
	}

	public static String getDashboardTodayDate(String country) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		f.setTimeZone(TimeZone.getTimeZone(country));
		// System.out.println(f.format(GregorianCalendar.getInstance().getTime()));
		String date = f.format(GregorianCalendar.getInstance().getTime());

		return date;
	}

	public static String getPriscDatetime(String country) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
		f.setTimeZone(TimeZone.getTimeZone(country));
		// System.out.println(f.format(GregorianCalendar.getInstance().getTime()));
		String date = f.format(GregorianCalendar.getInstance().getTime());

		return date;
	}

	public static String getUKCurrentDataTime(String country) {

		// country = "Europe/London";

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		f.setTimeZone(TimeZone.getTimeZone(country));
		// System.out.println(f.format(GregorianCalendar.getInstance().getTime()));
		String date = f.format(GregorianCalendar.getInstance().getTime());

		return date;
	}

	public static String getOTP() {

		int otp = 5783;

		String country = "Europe/London";

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		f.setTimeZone(TimeZone.getTimeZone(country));
		// System.out.println(f.format(GregorianCalendar.getInstance().getTime()));
		String date = f.format(GregorianCalendar.getInstance().getTime());

		String temp[] = date.split(" ");
		String time = temp[1];

		String temp1[] = time.split(":");

		int min = Integer.parseInt(temp1[1]);
		int sec = Integer.parseInt(temp1[2]);

		int cal = min + sec;

		otp = otp * cal;

		String newotp = Integer.toString(otp);
		if (newotp.length() == 5) {
			newotp = newotp + "0";
			// System.out.println(newotp);
		}
		// System.out.println(newotp);

		return newotp;
	}

	public static String getIndianDateTimeFormat(String date) {

		String temp[] = date.split(" ");
		String temp1[] = temp[0].split("-");
		String cdate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		date = cdate + " " + temp[1];

		return date;
	}

	public static String getValidFieldName(String filedname) {
		String fieldValue = filedname.replace(" ", "_");
		String temp1 = fieldValue.replace("(", "_");
		String temp2 = temp1.replace(")", "_");
		String temp3 = temp2.replace("-", "_");
		String temp4 = temp3.replace("/", "_");
		String temp5 = temp4.replace("?", "_");
		String temp6 = temp5.replace(",", "_");
		String temp7 = temp6.replace("&", "_");
		String temp8 = temp7.replace("+", "_");
		String temp9 = temp8.replace(".", "_");
		String temp10 = temp9.replace("'", "_");

		return temp10;
	}

	public static String removeAllSpecialChar(String s) {
		try {
			s = s.replaceAll("[^\\p{L}\\p{Z}]", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String removeLastunderScore(String str) {
		try {
			char c[] = str.toCharArray();
			String d = Character.toString(c[str.length() - 1]);
			if (d.equals("_")) {
				str = str.substring(0, str.length() - 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static long getDiffofTwoDates(String date1, String date2) {
		long ddays = 0;

		String temp[] = date1.split("-");
		String semp[] = date2.split("-");

		int y1 = Integer.parseInt(temp[0]);
		int m1 = Integer.parseInt(temp[1]);
		int d1 = Integer.parseInt(temp[2]);

		int y2 = Integer.parseInt(semp[0]);
		int m2 = Integer.parseInt(semp[1]);
		int d2 = Integer.parseInt(semp[2]);

		try {
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.set(y1, m1, d1);
			calendar2.set(y2, m2, d2);
			long milsecs1 = calendar1.getTimeInMillis();
			long milsecs2 = calendar2.getTimeInMillis();
			long diff = milsecs2 - milsecs1;
			long dsecs = diff / 1000;
			long dminutes = diff / (60 * 1000);
			long dhours = diff / (60 * 60 * 1000);
			ddays = diff / (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ddays;
	}

	public static long getDatesum(String date1) {
		long ddays = 0;

		String temp[] = date1.split("-");
		

		int y1 = Integer.parseInt(temp[0]);
		int m1 = Integer.parseInt(temp[1]);
		int d1 = Integer.parseInt(temp[2]);

		

		try {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(y1, m1, d1);
		    long milsecs1 = calendar1.getTimeInMillis();
			
			
			ddays = milsecs1 / (24 * 60 * 60 * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ddays;
	}

	
	public static String getAge(String dob) {

		int age = 0;
		try {

			String arr[] = new String[3];

			int i = -1;
			for (String str : dob.split("/")) {
				arr[++i] = str;
			}
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			age = year - Integer.parseInt(arr[2]);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return String.valueOf(age);
	}

	/*
	 * public static String getAge1(String dob){
	 * 
	 * String x1="", x2="",x3=""; try {
	 * 
	 * String arr[] = new String[3]; int i=-1; for (String str : dob.split("/"))
	 * { arr[++i] = str; }
	 * 
	 * 
	 * 
	 * LocalDate bday = LocalDate.of(Integer.parseInt(arr[2]),
	 * Integer.parseInt(arr[1]), Integer.parseInt(arr[0])); LocalDate today =
	 * LocalDate.now(); Period age = Period.between(bday, today); int years =
	 * age.getYears(); int months = age.getMonths();
	 * System.out.println("number of years: " + years);
	 * System.out.println("number of months: " + months);
	 * 
	 * x2= String.valueOf(years); x3=String.valueOf(months);
	 * 
	 * x1= x2+"."+x3; } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * 
	 * return String.valueOf(x1); }
	 */
	/*
	 * public static String getAge1(String dob1){ String age1=""; int
	 * age=0,year=0,month=0,days=0; try { Date date1 =new
	 * SimpleDateFormat("dd/MM/yyyy").parse(dob1); Calendar now =
	 * Calendar.getInstance(); Calendar dob = Calendar.getInstance();
	 * dob.setTime(date1); if (dob.after(now)) { throw new
	 * IllegalArgumentException("Can't be born in the future"); } int year1 =
	 * now.get(Calendar.YEAR); int year2 = dob.get(Calendar.YEAR); year = year1
	 * - year2; int month1 = now.get(Calendar.MONTH); int month2 =
	 * dob.get(Calendar.MONTH);
	 * 
	 * if(month1>month2){ month=month1-month2; } if (month2 > month1) { year--;
	 * int x=month2-month1; month= 12-x; } if (month1 == month2) { int day1 =
	 * now.get(Calendar.DAY_OF_MONTH); int day2 =
	 * dob.get(Calendar.DAY_OF_MONTH); if(day1>day2){ days=day1-day2; } if (day2
	 * > day1) { month--; int y=day2-day1; days= 30-y; } } if(year>=1){ return
	 * String.valueOf(year)+" yr"; }else if(month>1){ return
	 * String.valueOf(month)+" months"; } else{ if(month<=1){ return
	 * String.valueOf(days)+" days"; }
	 * 
	 * return String.valueOf(month)+" month "+String.valueOf(days)+" days"; } }
	 * catch (ParseException e) { e.printStackTrace(); }
	 * 
	 * return String.valueOf(year)+" yr";
	 * 
	 * }
	 * 
	 */
	public static String getAge1(String dob1) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			String dt[] = dob1.split("/");
			String n = dt[1];
			int tr = Integer.parseInt(n);
			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>1&&days>0) { return String.valueOf(months+" month"); }
			 * if(days<=30){ String dy= String.valueOf(days+" days");
			 * 
			 * return dy; }
			 */

			/*
			 * if(days>0&&months==0&&years==0){ String dy=
			 * String.valueOf(days+" days");
			 * 
			 * return dy; }else if(years<=2){ return
			 * String.valueOf(months)+" months"; }else{ String yr=
			 * String.valueOf(years)+" yr"; return yr; }
			 * 
			 */

			if (years > 5) {
				String yr = String.valueOf(years) + "Y ";
				return yr;
			}else if (years<1) {
				String age =String.valueOf(months) + "M " + String.valueOf(days) + "D ";
				return age;
			}else {

				String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " + String.valueOf(days) + "D ";
				return age;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf((days + 1) + "D");

	}
	
	//for balgopal age year month show 
	public static String getAgeyear(String dob1) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			String dt[] = dob1.split("/");
			String n = dt[1];
			int tr = Integer.parseInt(n);
			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>1&&days>0) { return String.valueOf(months+" month"); }
			 * if(days<=30){ String dy= String.valueOf(days+" days");
			 * 
			 * return dy; }
			 */

			/*
			 * if(days>0&&months==0&&years==0){ String dy=
			 * String.valueOf(days+" days");
			 * 
			 * return dy; }else if(years<=2){ return
			 * String.valueOf(months)+" months"; }else{ String yr=
			 * String.valueOf(years)+" yr"; return yr; }
			 * 
			 */

			/*
			 * if (years > 5) { String yr = String.valueOf(years) + "Y "; return yr; } else
			 * {
			 * 
			 * String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " +
			 * String.valueOf(days) + "D "; return age; }
			 */
			
			String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " + String.valueOf(days) + "D ";
			return age;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf((days + 1) + "D");

	}
	public static String getAge2(String dob1) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);

			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>1&&days>0) { return String.valueOf(months+" month"); }
			 * if(days<=30){ String dy= String.valueOf(days+" days");
			 * 
			 * return dy; }
			 */

			/*
			 * if(days>0&&months==0&&years==0){ String dy=
			 * String.valueOf(days+" days");
			 * 
			 * return dy; }else if(years<=2){ return
			 * String.valueOf(months)+" months"; }else{ String yr=
			 * String.valueOf(years)+" yr"; return yr; }
			 * 
			 */
			if (years > 0) {
				String yr = String.valueOf(years) + " Y";
				return yr;
			} else if (months > 1) {
				return String.valueOf(months - 1) + " M";
			} else {
				String dy = String.valueOf((days + 1) + " D");
				return dy;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf((days + 1) + " days");

	}

	public static String getAge1onAddmission(String dob1, String dateofAdmn) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			Date currentdt = new SimpleDateFormat("yyyy-MM-dd").parse(dateofAdmn);

			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();

			now.setTimeInMillis(currentdt.getTime());
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>1&&days>0) { return String.valueOf(months+" month"); }
			 * if(days<=30){ String dy= String.valueOf(days+" days");
			 * 
			 * return dy; }
			 */

			/*
			 * if(days>0&&months==0&&years==0){ String dy=
			 * String.valueOf(days+" days");
			 * 
			 * return dy; }else if(years<=2){ return
			 * String.valueOf(months)+" months"; }else{ String yr=
			 * String.valueOf(years)+" yr"; return yr; }
			 * 
			 */

			if (years > 5) {
				String yr = String.valueOf(years) + "Y ";
				return yr;
			} else {

				String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " + String.valueOf(days) + "D ";
				return age;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf((days + 1) + "D");

	}
	public static String getAge1onPaymentDate(String dob1, String dateofAdmn) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			Date currentdt = new SimpleDateFormat("yyyy-MM-dd").parse(dateofAdmn);

			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();

			now.setTimeInMillis(currentdt.getTime());
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>1&&days>0) { return String.valueOf(months+" month"); }
			 * if(days<=30){ String dy= String.valueOf(days+" days");
			 * 
			 * return dy; }
			 */

			/*
			 * if(days>0&&months==0&&years==0){ String dy=
			 * String.valueOf(days+" days");
			 * 
			 * return dy; }else if(years<=2){ return
			 * String.valueOf(months)+" months"; }else{ String yr=
			 * String.valueOf(years)+" yr"; return yr; }
			 * 
			 */

			/*
			 * if (years > 5) { String yr = String.valueOf(years) + "Y "; return yr; } else
			 * {
			 * 
			 * String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " +
			 * String.valueOf(days) + "D "; return age; }
			 */
			String age = String.valueOf(years) + "Y " + String.valueOf(months) + "M " + String.valueOf(days) + "D ";
			return age;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf((days + 1) + "D");

	}

	public static String getAmPmTime(String stime) {
		// String stime = "12:00";
		String temp[] = stime.split(":");
		if (Integer.parseInt(temp[0]) <= 11) {
			stime = stime + " AM";
		} else {
			stime = stime + " PM";
		}
		// System.out.println(stime);

		return stime;
	}

	public static boolean checkispm(String stime) {
		boolean result = false;

		String temp[] = stime.split(":");
		if (Integer.parseInt(temp[0]) >= 11) {
			return true;
		}
		return result;
	}

	public static String getDBDate(String date) {

		String result = "";
		String tm = "";
		try {
			String temp[] = date.split(" ");
			String dt = getCommencingDate1(temp[0]);
			if (date.contains(":")) {
				String time[] = temp[1].split(":");
				tm = time[0] + ":" + time[1];
			}

			result = dt + " " + tm;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public static String getNextDaysInSimpleDBFormat(int nextDays) {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, nextDays);
		Date date = calendar.getTime();
		String newdate = f.format(date);

		return newdate;
	}

	public static List<String> getCbcHLValue(String str) {
		List<String> output = new ArrayList<String>();
		Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]+").matcher(str);
		while (match.find()) {
			output.add(match.group());
		}
		// System.out.println(output.get(output.size()-1));
		return output;
	}

	public static String getDBToSimpleDate(String date) {
		String temp[] = date.split(" ");
		String dt = getCommencingDate1(temp[0]);
		String time[] = temp[1].split(":");
		String tm = time[0] + ":" + time[1];

		String result = dt;

		return result;
	}

	public static String converToMMMYYYYforExp(String commencing) { // commencing
																	// 2017-22-22

		String resuDate = "";
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(commencing);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
			String nowdate = format.format(date);

			resuDate = nowdate.split("-")[1] + "-" + nowdate.split("-")[0];

		} catch (Exception e) {

			e.printStackTrace();
		}
		return resuDate;
	}

	public static String getAgeToDob(String age) {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int ageee = Integer.parseInt(age);

		int dobyear = year - ageee;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = dateFormat.format(calendar.getTime());
		String splitdate[] = date.split("/");
		String dodd = splitdate[0] + "/" + splitdate[1] + "/" + dobyear;

		return dodd;
	}

	public static String getMonthandDays(String dateOfBirth) { // dd/MM/yyyy

		String str = "";
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date birthdate = dateFormat.parse(dateOfBirth);
			Calendar calendar = Calendar.getInstance();

			Calendar birthCal = Calendar.getInstance();
			birthCal.setTime(birthdate);

			int date = birthCal.get(Calendar.DATE);
			int nowDAte = calendar.get(Calendar.DATE);
			int days = 0;
			if (date > nowDAte) {
				days = date - nowDAte;
			} else {
				days = nowDAte - date;
			}

			int monthsDiff = calendar.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);

			str = "" + monthsDiff + " months " + days + " days ";

		} catch (Exception e) {

			e.printStackTrace();
		}
		return str;
	}

	public static String getAge1onAddmissionimmu(String dob1, String dateofAdmn) {
		int years = 0;
		int months = 0;
		int days = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd-MM-yyyy").parse(dob1);
			Date currentdt = new SimpleDateFormat("dd-MM-yyyy").parse(dateofAdmn);

			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();

			now.setTimeInMillis(currentdt.getTime());
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			// Create new Age object
			/*
			 * if(years>=1){ String yr= String.valueOf(years+" yr"); return yr;
			 * }
			 * 
			 * if(months>=1) { return String.valueOf(months+" month"); }
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		String dy = "";
		if (years == 0) {
			dy = String.valueOf(months + " Months") + " " + String.valueOf(days + " Days");

		} else if (years == 0 && months == 0) {
			dy = String.valueOf(days + " Days");
		} else {
			dy = String.valueOf(years + " Years") + " " + String.valueOf(months + " Months") + " "
					+ String.valueOf(days + " Days");

		}

		return dy;

	}

	public static String setDoubleToTwoDigit(double number) {
		DecimalFormat twoPlaces = new DecimalFormat("0.00");

		return twoPlaces.format(number);
	}

	public static String isNull(String string) {
		if (string == null || string.equals("undefined") || string.equals("null")) {
			string = "";
		}
		return string;
	}

	public static int convertToInteger(String str) {
		int digit = 0;
		str = numberCheck(str);
		try {
			digit = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}

		return digit;

	}

	public static double convertToDouble(String str) {
		double digit = 0;
		str = numberCheck(str);
		try {
			digit = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}

		return digit;
	}

	public static String getFinancialYearRange() {
		String yearrangenew = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String todaydate = dateFormat.format(cal.getTime());
			String endfinancialyeardate = "31-03-" + todaydate.split("-")[2];

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date today = sdf.parse(todaydate);
			Date lastdayoffinyr = sdf.parse(endfinancialyeardate);

			if (today.after(lastdayoffinyr)) {
				int year = Integer.parseInt(todaydate.split("-")[2]);
				String yearrange = year + "-" + (year + 1);
				yearrangenew = yearrange;

			} else {
				int year = Integer.parseInt(todaydate.split("-")[2]);
				String yearrange = (year - 1) + "-" + year;
				yearrangenew = yearrange;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return yearrangenew;
	}

	public static String removeBreaks(String str) {
		str = DateTimeUtils.isNull(str);
		if (str.matches("(<br>)+")) {
			str = "";
		}

		return str;
	}

	public static String numberCheck1(String string) {
		if (string == null || string.equals("")) {
			string = "1";
		}
		return string;
	}

	public static String numberCheck(String string) {
		if (string == null || string.equals("")) {
			string = "0";
		}
		return string;
	}

	public static int getmonthsfromdob(String dob1) {
		int years = 0;
		int months = 0;
		int totalmonth = 0;
		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			String dt[] = dob1.split("/");
			String n = dt[1];
			int tr = Integer.parseInt(n);
			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}

			totalmonth = years * 12 + months;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalmonth;

	}

	public static String getMonthName(int monthNumber) {
		String[] months = new DateFormatSymbols().getMonths();
		int n = monthNumber - 1;
		return (n >= 0 && n <= 11) ? months[n] : "wrong number";
	}

	public static String getCommencingDatePayroll(String commencing) {

		String temp[] = commencing.split("/");
		commencing = temp[2] + "/" + temp[1] + "/" + temp[0];

		return commencing;
	}

	public static String getCommencingDatemmddyyy(String commencing) {
		String temp[] = commencing.split("/");
		commencing = temp[1] + "/" + temp[0] + "/" + temp[2];

		return commencing;
	}

	public static String generateOTP(int length) {
		String otp = "";
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			otp = otp + random.nextInt(10);
		}
		return otp;
	}

	public static String getdatewithmonth(String ddmmyy) {
		String day, month, year;
		day = ddmmyy.split("-")[0];
		month = ddmmyy.split("-")[1];
		year = ddmmyy.split("-")[2];
		String monthname = getMonthName(month);
		String res = day + "-" + monthname + "-" + year;
		return res;
	}

	public static String getAge3(String dob) {
		String age ="";
		try {
			String dob1 = dob;

			int years = 0;
			int months = 0;
			int days = 0;
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			String dt[] = dob1.split("/");
			String n = dt[1];
			int tr = Integer.parseInt(n);
			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			age = String.valueOf(years) + " Year's " + String.valueOf(months) + " Months " + String.valueOf(days)
					+ " Days";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return age;
	}
	public static String getAgeyearmonthdays(String dob) {
		String age ="";
		try {
			String dob1 = dob;

			int years = 0;
			int months = 0;
			int days = 0;
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob1);
			String dt[] = dob1.split("/");
			String n = dt[1];
			int tr = Integer.parseInt(n);
			// create calendar object for birth day
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTimeInMillis(birthDate.getTime());
			// create calendar object for current day
			long currentTime = System.currentTimeMillis();
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(currentTime);
			// Get difference between years
			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			int currMonth = now.get(Calendar.MONTH) + 1;
			int birthMonth = birthDay.get(Calendar.MONTH) + 1;
			// Get difference between months
			months = currMonth - birthMonth;
			// if month difference is in negative then reduce years by one and
			// calculate the number of months.
			if (months < 0) {
				years--;
				months = 12 - birthMonth + currMonth;
				if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
					months--;
			} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				years--;
				months = 11;
			} else if (months > 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
			// Calculate the days
			if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
				days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
			else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				int today = now.get(Calendar.DAY_OF_MONTH);
				now.add(Calendar.MONTH, -1);
				days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
			} else {
				days = 0;
				if (months == 12) {
					years++;
					months = 0;
				}
			}
			age = String.valueOf(years) + "~~" + String.valueOf(months) + "~~" + String.valueOf(days);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return age;
	}
	public static String booleanCheck(String string) {
		if (string == null || string.equals("off") || string.equals("false") || string.equals("")) {
			string = "0";
		}else if(string.equals("on") || string.equals("true")){
			string = "1";
		}
		return string;
	}
	
	
	
	public static Long getDifferenceOfTwoDateDBFormatExactinHours(String d1, String d2) throws ParseException {

		long result = 0;
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String inputString1 = d1;
			String inputString2 = d2;

			Date date1 = myFormat.parse(inputString1);
			Date date2 = myFormat.parse(inputString2);
			long diff = date2.getTime() - date1.getTime();
			// System.out.println ("Days: " + TimeUnit.DAYS.convert(diff,
			// TimeUnit.MILLISECONDS));
			 result = diff / (60 * 60 * 1000);
//			result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}
	
	
	public static void saveFileToGlobelDirectory(String localFilePath,String fileName,File file){
		try {
				String filePath=localFilePath;
				if(filePath.contains("YUVICARE1")){
					filePath=filePath.replace("YUVICARE1", "YUVICAREDATA");
				}else if(filePath.contains("YUVICARETEST")){
					filePath=filePath.replace("YUVICARETEST", "YUVICAREDATA");
				}else if(filePath.contains("YUVICARE")){
				filePath=filePath.replace("YUVICARE", "YUVICAREDATA");
				}
				
				
				File uploadFolder = new File(filePath);
				if (!uploadFolder.exists()) {
					uploadFolder.mkdirs();
				}
				if(file!=null&&file.exists()){
					File fileToCreate = new File(filePath, fileName);
					FileUtils.copyFile(file, fileToCreate);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveFileToLocalDirectory(String localFilePath,String fileName){
		try {
				String filePath=localFilePath;
				if(filePath.contains("YUVICARE1")){
					filePath=filePath.replace("YUVICARE1", "YUVICAREDATA");
				}else if(filePath.contains("YUVICARETEST")){
					filePath=filePath.replace("YUVICARETEST", "YUVICAREDATA");
				}else if(filePath.contains("YUVICARE")){
				filePath=filePath.replace("YUVICARE", "YUVICAREDATA");
				}
				File golbelFolder = new File(filePath);
				if (!golbelFolder.exists()) {
					golbelFolder.mkdirs();
				}
			
				File LocalFileName= new File(localFilePath, fileName);
				if (golbelFolder.exists()) {
					File golbelFile=new File(filePath, fileName);
					if(golbelFile.exists()){
						FileUtils.copyFile(golbelFile, LocalFileName);
					}
					
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Date[] getFinanacialYearStartAndEndDates(Date date){
		Date[] dates=new Date[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date todaysDate=date;
			String todaydateString = dateFormat.format(todaysDate);
			String currentYearString=todaydateString.split("-")[2];
			Date financialYearStartDate=dateFormat.parse("01-04-"+currentYearString);
			String startDateString="",endDateString="";
			if(todaysDate.after(financialYearStartDate)){
				startDateString="01-04-"+currentYearString;
				endDateString="31-03-"+(DateTimeUtils.convertToInteger(currentYearString)+1);
			}else{
				startDateString="01-04-"+(DateTimeUtils.convertToInteger(currentYearString)-1);
				endDateString="31-03-"+currentYearString;
			}
			dates[0]=dateFormat.parse(startDateString);
			dates[1]=dateFormat.parse(endDateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	} 
	
	public static String getPatientFullname(Client client){
		String name="";
		try {
			if(!isNull(client.getMiddlename()).equals("")){
				name=client.getTitle() + " " + client.getFirstName() + " " +client.getMiddlename()+" "+ client.getLastName();
			}else{
				name=client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	public static boolean validateDateBetweenRange(String date){
		try {
			
			LocalDate localDate = LocalDate.now();
			LocalDate olderdate = localDate.minusMonths(6);
			String currentDate = localDate.toString();
			String olderDate = olderdate.toString();
			
		 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date todate = sdf.parse(currentDate);
	        Date fromdate = sdf.parse(olderDate);
	        Date specificdate = sdf.parse(date);
	        
	        if(specificdate.getTime() >= fromdate.getTime() && specificdate.getTime() <= todate.getTime()){
	            return true;
	        }else{
	        	return false;
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
public static String findTimeBTDateTime(String join_date, String leave_date)   
{   
    // Create an instance of the SimpleDateFormat class  
    SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");   
    // In the try block, we will try to find the difference  
    String res="";
    try {   
        // Use parse method to get date object of both dates  
        Date date1 = obj.parse(join_date);   
        Date date2 = obj.parse(leave_date);   
        // Calucalte time difference in milliseconds   
        long time_difference = date2.getTime() - date1.getTime();  
        // Calucalte time difference in days  
        long days_difference = (time_difference / (1000*60*60*24)) % 365;   
        // Calucalte time difference in years  
        long years_difference = (time_difference / (1000l*60*60*24*365));   
        // Calucalte time difference in seconds  
        long seconds_difference = (time_difference / 1000)% 60;   
        // Calucalte time difference in minutes  
        long minutes_difference = (time_difference / (1000*60)) % 60;   
          
        // Calucalte time difference in hours  
        long hours_difference = (time_difference / (1000*60*60)) % 24;   
        // Show difference in years, in days, hours, minutes, and seconds   
        System.out.print(   
            "Difference "  
            + "between two dates is: ");   
        System.out.println(   
            hours_difference   
            + " hours, "  
            + minutes_difference   
            + " minutes, "  
            + seconds_difference   
            + " seconds, "  
            + years_difference   
            + " years, "  
            + days_difference   
            + " days"  
            );   
         res=hours_difference + " hours, " + minutes_difference+ " minutes, "+ seconds_difference + " seconds" ;
    }   // Catch parse exception   
    catch (ParseException excep) {   
        excep.printStackTrace();   
    }   
    return res;
}

public static String calnewdate(String d1,int num){
	
	String testDate =d1;
	try {
		
		/*String testDate = "29/Apr/2010,13:00:14 PM";*/
		DateFormat formatter = new SimpleDateFormat("d/MM/yyyy,HH:mm:ss aaa");
		Date date1=null;
		try {
			date1 = formatter.parse(testDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println(date1);
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	cal.setTime(date1);
	cal.add(Calendar.DATE, num); 
	testDate = dateFormat.format(cal.getTime());
	
	/*testDate=DateTimeUtils.getCommencingDate1(testDate);*/
	
	System.out.println(testDate);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return testDate;
}
public static int getlastmonthdate(int month,int year) {
	
	 //int year=2024;
	 int leapyear=0;
	 if(year%4==0) {
		 leapyear=29;
	 }else {
		 leapyear=28;
	 }
		          /* jan,feb,mar,apr,may,june,july,aug,sept,oct,nov,dec */
	 int [] month1= {31,leapyear,31,30,31,30,31,31,30,31,30,31};
	
	 int lastdate=month1 [month-1];
	 
	 return lastdate;
}


}