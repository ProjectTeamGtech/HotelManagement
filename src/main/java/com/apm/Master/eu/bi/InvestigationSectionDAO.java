package com.apm.Master.eu.bi;
import java.util.ArrayList;

import com.apm.Master.eu.entity.InvestigationSection;
import com.apm.common.utils.Pagination;

public interface InvestigationSectionDAO {

	int getTotalInvsSectionCount();
	ArrayList<InvestigationSection> getInvestigationSectionList(Pagination pagination ,String SearchText);
 
	InvestigationSection getAllInvestigationSectionListById(InvestigationSection invOBJ);
	int addInvestigationSection(InvestigationSection invOBJ);
	int deleteInvestigationSection(InvestigationSection invOBJ);
	int updateInvestigationSectionlist(InvestigationSection invOBJ);
}
