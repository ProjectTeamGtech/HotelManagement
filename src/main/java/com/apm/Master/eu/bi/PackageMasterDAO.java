package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.common.utils.Pagination;

public interface PackageMasterDAO {

	String getChargeNameById(String chargeid);

	int storePackageParentData(String package_name, String package_amount, String packagetype, String inveschargeid,String istp, String description, String days);

	int storePackageChildData(PackageMaster packageMaster, int result);

	ArrayList<PackageMaster> getAllPackage(String searchText, Pagination pagination);

	int deletePackage(PackageMaster packageMaster);

	ArrayList<PackageMaster> getPerticularPackageForEdit(String id);

	PackageMaster getPerticularPackage(String id);

	ArrayList<PackageMaster> getPackageFromChild(int id);

	int updateParentPackageData(String parentid, String package_name,
			String package_amount, String inveschargeid);

	int updateChildPackageData(PackageMaster packageMaster);

	int getTotalPackageMasterCount();

	int checkPackageName(String name);

	ArrayList<Master> getmasterChageNameList();

	String getChargeNameByIdNew(String chargeid);
	//lokesh
	int deleteChildPkgAjax(String childid);
	String getTPPkgList();
	String nameofApmtCharge(String id);
	Master getCodeAndAmmountOfTpCharge(String clientid,String charge);

	ArrayList<PackageMaster> getAllPackagelist(String searchText, String whopay, Pagination pagination);

	ArrayList<PackageMaster> getAllChildPackagelist(String parentid);
}
