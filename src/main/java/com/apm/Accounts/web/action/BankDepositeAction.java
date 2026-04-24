package com.apm.Accounts.web.action;

import com.apm.Accounts.web.form.AccountsForm;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

public class BankDepositeAction extends BaseAction implements ModelDriven<AccountsForm>{

	AccountsForm accountsForm=new AccountsForm();
	
	public AccountsForm getModel() {
		// TODO Auto-generated method stub
		return accountsForm;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String cheque(){
		
		return "cheque";
	}
	
	
}
