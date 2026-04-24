package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;

public interface InvestigationTemplateDAO {

	ArrayList<InvestigationTemplate> getAllInvestigationTemplateList(
			String searchText, Pagination pagination);

	ArrayList<InvestigationTemplate> getInvestigationTypeList();

	int addInvestigationTemplate(InvestigationTemplate master);

	InvestigationTemplate getAllInvestigationTemplateListById(
			String getID);

	int updateinvestigationtemplatelist(InvestigationTemplate master);
	
	ArrayList<Master> getAllInvestigationSectionList();

	int deleteInvestigationTemplate(InvestigationTemplate master);

	InvestigationTemplate getAllInvestigationTemplateListById(
			InvestigationTemplate master);

	int getTotalInvTempCount();

	
	
	
}
