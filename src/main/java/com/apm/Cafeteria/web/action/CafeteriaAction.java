package com.apm.Cafeteria.web.action;

import com.apm.Cafeteria.web.form.CafeteriaForm;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class CafeteriaAction extends BaseAction implements ModelDriven<CafeteriaForm> {

	CafeteriaForm cafeteriaForm=new CafeteriaForm();
	
	public CafeteriaForm getModel() {
		// TODO Auto-generated method stub
		return cafeteriaForm;
	}

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
	public String history() throws Exception{
		
		
		return "history";
	}
	
}
