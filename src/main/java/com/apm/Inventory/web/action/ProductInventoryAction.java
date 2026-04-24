package com.apm.Inventory.web.action;

import com.apm.Inventory.web.form.ProductForm;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ProductInventoryAction extends BaseAction implements ModelDriven<ProductForm>,Preparable {

	
	ProductForm productForm=new ProductForm();
	
	public ProductForm getModel() {
		// TODO Auto-generated method stub
		return productForm;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String add() {
		
		
		return SUCCESS;
	}
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
