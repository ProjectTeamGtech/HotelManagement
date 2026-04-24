package com.apm.Ipd.eu.entity;

import java.util.ArrayList;

import com.apm.Ambulance.eu.entity.Duty;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Master.web.action.VitalMasterAction;

public class Bams {

	public String getKarma() {
		return karma;
	}
	public void setKarma(String karma) {
		this.karma = karma;
	}
	public String getProcedurebams() {
		return procedurebams;
	}
	public void setProcedurebams(String procedurebams) {
		this.procedurebams = procedurebams;
	}
	private ArrayList<Bams> vitalList;
	
	private String height;
	private String weight;
	private String bmi;
	private String pulse;
	private String sysbp;
	private String diabp;
	private String sugarfasting;
	private String postmeal;
	private String temp;
	private String spo2;
	private String headCir;
	private String showDate;
	private ArrayList<Priscription> priscList;
	private ArrayList<Investigation> investList;
	private ArrayList<Duty> punchList;
	private ArrayList<Bed> physioList;
	private String karma,procedurebams;
	public ArrayList<Bams> getVitalList() {
		return vitalList;
	}
	public void setVitalList(ArrayList<Bams> vitalList) {
		this.vitalList = vitalList;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getSysbp() {
		return sysbp;
	}
	public void setSysbp(String sysbp) {
		this.sysbp = sysbp;
	}
	public String getDiabp() {
		return diabp;
	}
	public void setDiabp(String diabp) {
		this.diabp = diabp;
	}
	public String getSugarfasting() {
		return sugarfasting;
	}
	public void setSugarfasting(String sugarfasting) {
		this.sugarfasting = sugarfasting;
	}
	public String getPostmeal() {
		return postmeal;
	}
	public void setPostmeal(String postmeal) {
		this.postmeal = postmeal;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getSpo2() {
		return spo2;
	}
	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}
	public String getHeadCir() {
		return headCir;
	}
	public void setHeadCir(String headCir) {
		this.headCir = headCir;
	}
	public ArrayList<Priscription> getPriscList() {
		return priscList;
	}
	public void setPriscList(ArrayList<Priscription> priscList) {
		this.priscList = priscList;
	}
	public ArrayList<Investigation> getInvestList() {
		return investList;
	}
	public void setInvestList(ArrayList<Investigation> investList) {
		this.investList = investList;
	}
	public ArrayList<Duty> getPunchList() {
		return punchList;
	}
	public void setPunchList(ArrayList<Duty> punchList) {
		this.punchList = punchList;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public ArrayList<Bed> getPhysioList() {
		return physioList;
	}
	public void setPhysioList(ArrayList<Bed> physioList) {
		this.physioList = physioList;
	}
	
	
	
	
}
