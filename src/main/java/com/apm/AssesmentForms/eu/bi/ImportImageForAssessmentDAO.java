package com.apm.AssesmentForms.eu.bi;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;

public interface ImportImageForAssessmentDAO {

	ArrayList<Assessment> getImportImageList();

	int insertImageData(String imagename, String filepath, String filename);

	int deleteImage(String id);

}
