package com.apm.Inventory.web.action;

import com.apm.Inventory.web.form.StorageForm;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InventoryStorageAction extends BaseAction implements ModelDriven<StorageForm>,Preparable {

	StorageForm storageForm=new StorageForm();
	
	public StorageForm getModel() {
		// TODO Auto-generated method stub
		return storageForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		

	}

	
	public String stock() {
		
		
		return "stock";
	}
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
	
}
