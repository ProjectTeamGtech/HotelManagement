package com.apm.Appointment.web.form;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Registration.eu.entity.Location;

public class AppointmentForm {
	
	ArrayList<Appointment>appointmentList;
	
	private String month;
	
	private String monday;
	
	private String tuesday;
	
	private String wednesday;
	
	private String thursday;
	
	private String friday;
	
	private String saturday;
	
	private String sunday;
	
	private String monthName;
	
	private ArrayList<String>monthList;
	
	private String year;
	
	private ArrayList<String> yearList;
	
	private String hour;
	
	private ArrayList<String>hourList;
	
	private String minute;
	
	private ArrayList<String>minuteList;
	
	private String practitioner;
	
	private String appointmentType;
	
	private String patientType;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String contactNo;
	
	private String dob;
	
	private String autoremaindertype;
	
	private String when;
	
	private String tohour;
	
	private String tominute;
	
	private String repeat;
	
	private String revery;
	
	private String happointment;
	
	private String notes;
	
	private String comDevice;
	
	private String email;
	
	private String practitionerName;
	
	private int practitionerID;
	
	



	private String location;
	
	private String dnaLimit;
	private String outInvoiceLimit;
	private String chargeType;
	
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	public String getDnaLimit() {
		return dnaLimit;
	}

	public void setDnaLimit(String dnaLimit) {
		this.dnaLimit = dnaLimit;
	}

	public String getOutInvoiceLimit() {
		return outInvoiceLimit;
	}

	public void setOutInvoiceLimit(String outInvoiceLimit) {
		this.outInvoiceLimit = outInvoiceLimit;
	}

	
	
	private ArrayList<Appointment>practitionerList;
	
	private ArrayList<Location>locationList; 
	
	private ArrayList<AppointmentType>appointmentTypeList;
	
	
	

	public ArrayList<AppointmentType> getAppointmentTypeList() {
		return appointmentTypeList;
	}

	public void setAppointmentTypeList(
			ArrayList<AppointmentType> appointmentTypeList) {
		this.appointmentTypeList = appointmentTypeList;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComDevice() {
		return comDevice;
	}

	public void setComDevice(String comDevice) {
		this.comDevice = comDevice;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public ArrayList<String> getHourList() {
		return hourList;
	}

	public void setHourList(ArrayList<String> hourList) {
		this.hourList = hourList;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public ArrayList<String> getMinuteList() {
		return minuteList;
	}

	public void setMinuteList(ArrayList<String> minuteList) {
		this.minuteList = minuteList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ArrayList<String> getYearList() {
		return yearList;
	}

	public void setYearList(ArrayList<String> yearList) {
		this.yearList = yearList;
	}

	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	

	

	public ArrayList<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(ArrayList<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getPractitioner() {
		return practitioner;
	}

	public void setPractitioner(String practitioner) {
		this.practitioner = practitioner;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAutoremaindertype() {
		return autoremaindertype;
	}

	public void setAutoremaindertype(String autoremaindertype) {
		this.autoremaindertype = autoremaindertype;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getTohour() {
		return tohour;
	}

	public void setTohour(String tohour) {
		this.tohour = tohour;
	}

	public String getTominute() {
		return tominute;
	}

	public void setTominute(String tominute) {
		this.tominute = tominute;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getRevery() {
		return revery;
	}

	public void setRevery(String revery) {
		this.revery = revery;
	}

	public String getHappointment() {
		return happointment;
	}

	public void setHappointment(String happointment) {
		this.happointment = happointment;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public int getPractitionerID() {
		return practitionerID;
	}

	public void setPractitionerID(int practitionerID) {
		this.practitionerID = practitionerID;
	}

	public ArrayList<Appointment> getPractitionerList() {
		return practitionerList;
	}

	public void setPractitionerList(ArrayList<Appointment> practitionerList) {
		this.practitionerList = practitionerList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	
	
	

	

}
