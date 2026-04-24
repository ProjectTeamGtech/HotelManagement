package com.apm.Accounts.eu.entity;

public class Charges {
	
	private int id;
	
	private String chargeName;
	
	private String charge;
	
	private String commencing;
	
	private String chargeinvid;
	
	private int quantity;
	
	private String chargeTotal;
	private String isshared;
	private String shared_amt;
	public String getIsshared() {
		return isshared;
	}

	public void setIsshared(String isshared) {
		this.isshared = isshared;
	}

	public String getShared_amt() {
		return shared_amt;
	}

	public void setShared_amt(String shared_amt) {
		this.shared_amt = shared_amt;
	}

	public String getAll_shared() {
		return all_shared;
	}

	public void setAll_shared(String all_shared) {
		this.all_shared = all_shared;
	}

	private String all_shared;
	
	

	public String getChargeTotal() {
		return chargeTotal;
	}

	public void setChargeTotal(String chargeTotal) {
		this.chargeTotal = chargeTotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getChargeinvid() {
		return chargeinvid;
	}

	public void setChargeinvid(String chargeinvid) {
		this.chargeinvid = chargeinvid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	
	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	
	
	
	

}
