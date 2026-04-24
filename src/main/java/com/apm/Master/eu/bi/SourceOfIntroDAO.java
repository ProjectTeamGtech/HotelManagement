package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SourceOfIntro;
import com.apm.common.utils.Pagination;

public interface SourceOfIntroDAO {

	int getTotalSourceOfIntroCount();

	ArrayList<SourceOfIntro> getsourceOfIntroList(Pagination pagination);

	int saveSourceOfIntro(SourceOfIntro sourceOfIntro);

	SourceOfIntro getSourceOfIntro(int id, SourceOfIntro sourceOfIntro);

	int updateSourceOfIntro(SourceOfIntro sourceOfIntro, int id);
	public ArrayList<Master> getMasterList();
	int deleteSourceOfIntro(int id);

}
