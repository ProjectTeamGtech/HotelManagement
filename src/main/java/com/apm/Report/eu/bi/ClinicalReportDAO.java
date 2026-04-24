package com.apm.Report.eu.bi;

import java.util.ArrayList;

import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

public interface ClinicalReportDAO {

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String practitionerId, String fromDate, String toDate,String orderby,String order);

}
