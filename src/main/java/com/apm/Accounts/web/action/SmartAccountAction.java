package com.apm.Accounts.web.action;

import java.sql.Connection;

import com.apm.Accounts.web.form.AccountsForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SmartAccountAction extends BaseAction implements ModelDriven<AccountsForm>,Preparable{
	AccountsForm accountsForm = new AccountsForm();
	public AccountsForm getModel() {
		return accountsForm;
	}
	
	public String execute() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String action = accountsForm.getAction();
			if(action==null){
				action ="smartaccdash";
			}else if(action.equals("")){
				action="smartaccdash";
			}
			
			if(action.equals("smartaccdash")){
				return SUCCESS;
			}/*else  if(action.equals("irfan")){
				return "irfan";
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
