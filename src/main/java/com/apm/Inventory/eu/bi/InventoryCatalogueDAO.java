package com.apm.Inventory.eu.bi;

import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Product;

public interface InventoryCatalogueDAO {

	 public Product getProductDetails(String catalogueid);
	 public ArrayList<Product> getAllProductList(String location);
	 public int saveNewCatalogue(Product product);
	 public int updateCatalogueIdInProduct(int catalogueid, String product_name);
	public int isExistThisName(String product_name);
	public String getSubcategoryId(String name);
	public ArrayList<Product> getAllProductListItemWiseSale(String location);
	public ArrayList<Product> getProductListForIndentReplace(String warehouse_id, String catalogueids);
	public ArrayList<Product> getAllProductListLMH(String id);
}
