package com.apm.Appointment.eu.bi;


import java.util.ArrayList;

import com.apm.common.utils.Pagination;
import com.apm.Appointment.eu.entity.Category;

public interface CategoryDAO {

	//ArrayList<Category> getCategoryList(String userId,Pagination pagination);

	int saveCategory(Category category, String userId)throws Exception;

	int getCategoryCount(String searchText,String userID)throws Exception;

	Category getCategory(int selectedid);

	int updateCategory(Category category, int selectedid);

	int deleteCategory(int selectedid);

	ArrayList<Category> getCategoryList(Pagination pagination,String searchText,String userid)throws Exception;

	//int getCategoryCount()throws Exception;

	ArrayList<Category> getCategoryListByBranch(String selectedBranch,
			Pagination pagination)throws Exception;

	int getCategoryCountByBranch(String selectedBranch)throws Exception;
	
	boolean isCategoryExist(String categoryName, String userId);

	int deleteProduct(int selectedid)throws Exception;

	int deleteSubCategory(int selectedid)throws Exception;

	boolean isProductExist(int categoryID,String productName)throws Exception;

}
