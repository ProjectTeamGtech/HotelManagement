<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>  
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <link rel="stylesheet" href="_assets/newtheme/css/main.css">
  <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">

  <link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
  <link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">
  <link rel="stylesheet" href="emr/plugin/jquery.treeview.css" />
   <link href="common/assets/css/style.css" rel="stylesheet" />
 
  <style>
  td {
    padding: 8px 10px;
  }
  #upload {
    background-color: #fff;
    padding: 0px;
    border-radius: 0px;
}

  td:first-child {
    font-family: monospace;
  }

  h3 {
    margin-top: 100px;
  }

  h5 {
    margin-bottom: 0;
  }

  hr {
    margin-top: 2px;
  }

  .example-1 {
    height: 600px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }

  .example-2, .example-3, .example-4, .example-5, .example-6 {
    height: 250px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }

  .example-flex {
    height: 200px;
    border: 1px solid #ddd;
    border-radius: 4px;
    display: flex;
    flex-direction: row;
  }

  .example-flex-reverse {
    flex-direction: row-reverse;
  }

  #one, #two {
    padding: 10px;
  }

  #one p {
    padding: 0;
  }

  .example-4, .example-5 {
    height: 400px;
  }

  .split p, .split-flex p {
    padding: 20px;
  }

  .split, .split-flex {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;

    overflow-y: auto;
    overflow-x: hidden;
  }

  .gutter {
    background-color: #eee;

    background-repeat: no-repeat;
    background-position: 50%;
  }

  .gutter.gutter-horizontal {
    background-image: url('emr/plugin/docs/vertical.png');
    cursor: ew-resize;
  }

 

  .split.split-horizontal, .gutter.gutter-horizontal {
    height: 93%;
    float: left;
  }
  .topheadbaxck {
    background-color: rgb(239, 239, 239);
    padding: 8px 0px;
}
.pclass{
    color: #777;
    margin:0px;
}
.fa-2x {
    font-size: 14px;
}


.btn:focus, .btn:active, button:focus, button:active {
  outline: none !important;
  box-shadow: none !important;
}

#image-gallery .modal-footer{
  display: block;
}

.thumb{
  margin-top: 15px;
  margin-bottom: 15px;
}

  </style>
 
 <script type="text/javascript">
 
 
 </script>
 
 
 
 
 <!-- JavaScript Includes -->
<script src="common/assets/js/jquery.fileupload.js"></script>


<script src="common/assets/js/jquery.knob.js"></script> 
<script src="common/assets/js/script.js"></script> 
   
	<script src="emr/plugin/jquery.cookie.js"></script>
	<script src="emr/plugin/jquery.treeview.js"></script>
	<script type="text/javascript" src="emr/plugin/demo.js"></script>

	<script type="text/javascript" src="emr/js/consultationnote.js"></script>
<script type="text/javascript" src="emr/js/emrDocsNew.js"></script>


 

 
 <script type="text/javascript">
 
       function filterDoc(d){
    	   
    	   $('#dashboardloaderPopup').modal( "show" );
    	      document.getElementById("doctype").value=d;
    	      document.getElementById("treeform").submit();
    	      
    	      
    	     
       }  
       
       $(document).ready(function() {

   		$("#fromdate").datepicker({

   			dateFormat : 'dd-mm-yy',
   			yearRange: yearrange,
   			minDate : '30-12-1880',
   			changeMonth : true,
   			changeYear : true

   		});
   		$("#todate").datepicker({

   			dateFormat : 'dd-mm-yy',
   			yearRange: yearrange,
   			minDate : '30-12-1880',
   			changeMonth : true,
   			changeYear : true

   		});
   		
   	});
 
 </script>



</head>

<body>


								

<div class="container emr-doc-styles">


<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>EMR Docs </h4>

									</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                       <div class="col-md-12">
                          <div class="form-inline">
						  	<s:form  name="searchForm" theme="simple" action="emrdocsEmr" method="post">
						  		 <s:hidden name="clientId" ></s:hidden>
				                 <s:hidden name="diaryUser"></s:hidden>
				                 <s:hidden name="condition"></s:hidden>
						  		
						  		<div class="form-group cstm-wd" >
						  			<s:textfield  name="fromdate"  id="fromdate" cssClass="form-control" cssStyle="width:100%;" placeholder="From Date" />
						  		</div>
						  		<div class="form-group">
						  			<span>To</span>
						  		</div>
						  		<div class="form-group cstm-wd" >
						  			<s:textfield  name="todate"  id="todate" cssClass="form-control" cssStyle="width:100%;" placeholder="To Date" />
						  		</div>
						  		
						  		<div class="form-group">
						  		<button type="submit" class="btn btn-primary">Go</button>
						  		</div>
						  		&nbsp;&nbsp;&nbsp;&nbsp;
						  		<div class="form-group">
						  			<p class="pclass"><b><s:property value="abrivationid"/>  <s:property value="fullname"/> | <s:property value="ageandgender"/> | <s:property value="dob"/> | <s:property value="mobNo"/> | <s:property value="town"/> </b> </p>
						  		</div>
						  		<s:if test="hideuploadbtn==1">
						  			
						  		</s:if>
						  		<s:else>
						  			<a href="#" class="btn btn-primary" onclick="openuploaddoc()"  style="float: right;">Upload</a>
						  		</s:else>
						  	
						   </s:form>
						   </div>
                       </div>
                    </div>



        <div class="example-1">
            <div id="one" class="split split-horizontal tree-one">
            <s:form theme="simple" id="treeform" action="emrdocsEmr" method="post">
                 <s:hidden name="clientId" id="clientId"></s:hidden>
                 <s:hidden name="diaryUser" id="diaryuserid"></s:hidden>
                 <s:hidden name="condition" id="conditionid"></s:hidden>
                 <s:hidden name="doctype" id="doctype"></s:hidden>
                  <s:hidden name="apmtId" ></s:hidden>
                <div id="main">
                <article>
				<input id="search" name="search" class="form-control" placeholder="Search file" type="text" data-list=".default_list" autocomplete="off">
				<div class="">
					<ul id="browser" class="filetree">
						<li class=""><span class="folder" onclick="filterDoc(2)" > OPD</span>
							<ul class="vertical default_list">
							  <s:iterator value="opdDocumentList">
								<%-- <li><a href="downloadDocEmr?filename=<s:property value="fileName"/>"><span class="file"> <s:property value="fileName"/> </span></a></li> --%>
								<li><span class="file"> <s:property value="fileName"/> </span></li>
								<%-- <li><span class="file"> OPD_file 1.2</span></li>
								<li><span class="file"> OPD_file 1.3</span></li>
								<li><span class="file"> OPD_file 1.4</span></li>
								<li><span class="file"> OPD_file 1.5</span></li>
								<li><span class="file"> OPD_file 1.6</span></li>
								<li><span class="file"> OPD_file 1.7</span></li>
								<li><span class="file"> OPD_file 1.8</span></li>
								<li><span class="file"> OPD_file 1.9</span></li> --%>
							</s:iterator>	
							</ul>
						</li>
						<li class=""><span class="folder" onclick="filterDoc(1)" > IPD</span>
							 <ul class="vertical default_list">
							  <s:iterator value="ipdDocumentList">
								<%-- <li><span class="file"> IPD_file 2.1</span></li> --%> 
								<%-- <li><a href="downloadDocEmr?filename=<s:property value="fileName"/>"><span class="file"> <s:property value="fileName"/> </span></a></li> --%>
								<li><span class="file"> <s:property value="fileName"/> </span></li>
								</s:iterator> 
							</ul>
						</li>
						<li class=""><span class="folder" onclick="filterDoc(0)" > EMR</span>
							 <ul class="vertical default_list">
								 <s:iterator value="emrDocumentList">
								<%-- <li><span class="file"> IPD_file 2.1</span></li> --%> 
								<%-- <li><a href="downloadDocEmr?filename=<s:property value="fileName"/>"><span class="file"> <s:property value="fileName"/> </span></a></li> --%>
								<li><span class="file"> <s:property value="fileName"/> </span></li>
								</s:iterator> 
							</ul>
						</li>
					</ul>
				</div>
		</article>
                
	



<!-- <div id="sidetree" class="hidden">
  <div class="treeheader">&nbsp;</div>
  <div id="sidetreecontrol"> <a href="?#">Collapse All</a> | <a href="?#">Expand All</a> </div>

  <ul class="treeview" id="tree">

	<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/index.cfm">Home</a>
	<ul style="display: none;">
		<li><a href="?/enewsletters/index.cfm">Airdrie eNewsletters </a></li>
		<li><a href="?/index.cfm">Airdrie Directories</a></li>
		<li><a href="?/economic_development/video/index.cfm">Airdrie Life Video</a></li>

		<li><a href="?/index.cfm">Airdrie News</a></li>

		<li><a href="?/index.cfm">Airdrie Quick Links</a></li>
		<li><a href="?http://weather.ibegin.com/ca/ab/airdrie/" target="_blank">Airdrie Weather</a></li>
		<li><a href="?/human_resources/index.cfm">Careers</a> | <a href="?/contact_us/index.cfm">Contact Us</a> | <a href="?/site_map/index.cfm">Site Map</a> | <a href="?/links/index.cfm">Links</a></li>

		<li><a href="?/calendars/index.cfm">Community Calendar </a></li>
		<li><a href="?/conditions_of_use/index.cfm">Conditions of Use and Privacy Statement</a></li>
		<li><a href="?/index.cfm">I'd like to find out about... </a></li>
		<li><a href="?/index.cfm">Opportunities</a></li>
		<li><a href="?/links/index.cfm">Resource Links</a></li>
		<li class="last"><a href="?/index.cfm">Special Notices</a></li>

	</ul>
	</li>
	<li class="expandable"><div class="hitarea expandable-hitarea"></div><span>City Services</span>
	<ul style="display: none;">
		<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/assessment/index.cfm">Assessment</a>
		<ul style="display: none;">
			<li><a href="?/assessment/assessment_faqs.cfm">Assessment FAQs</a></li>

			<li><a href="?/assessment/property_assessment_notices.cfm">2007 Property Assessment Notices</a></li>
			<li><a href="?http://www.creb.com/" target="_blank">CREB</a></li>
			<li><a href="?/assessment/non_residential_assessment_tax_comparisons.cfm">Non-Residential Assessment / Tax Comparisons</a></li>
			<li><a href="?/assessment/how_to_file_a_complaint.cfm">How to File a Complaint</a></li>
			<li class="last"><a href="?/assessment/supplementary_assessment_tax.cfm">Supplementary Assessment and Tax</a></li>
		</ul>

		</li>
		<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/building_development/index.cfm">Building &amp; Development </a>
		<ul style="display: none;">
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/building_inspections/index.cfm">Building Inspections</a>
			<ul style="display: none;">
				<li><a href="?/building_inspections/builder_forums.cfm">Builder Forums</a></li>

				<li><a href="?/building_inspections/contact_us.cfm">Contact Us</a></li>
				<li><a href="?/building_inspections/contractor_notices.cfm">Contractor Notices</a></li>
				<li><a href="?/building_inspections/inspector_guidelines.cfm">Inspector Guidelines</a></li>
				<li><a href="?/building_inspections/links.cfm">Links</a></li>
				<li class="expandable lastExpandable"><div class="hitarea expandable-hitarea lastExpandable-hitarea"></div><a href="?/building_inspections/statistics_2007.cfm">Statistics</a>
				<ul style="display: none;">

					<li><a href="?/building_inspections/statistics_2006.cfm">Statistics 2006</a></li>
					<li class="last"><a href="?/building_inspections/statistics_2005.cfm">Statistics 2005</a></li>
				</ul>
				</li>
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a title="City Infrastructure" href="?/building_development/city_infrastructure/index.cfm">City Infrastructure</a>

			<ul style="display: none;">

				<li><a href="?/building_development/city_infrastructure/roadway_improvement.cfm">Roadway Improvement</a></li>
				<li><a href="?/building_development/city_infrastructure/traffic.cfm">Traffic</a></li>
				<li><a href="?/building_development/city_infrastructure/transportation_planning.cfm">Transportation &amp; Infrastructure Planning</a></li>
				<li class="last"><a href="?/building_development/city_infrastructure/water_sewer_construction.cfm">Water &amp; Sewer Construction</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a title="Commercial/Industrial Development" href="?/building_development/commercial_industrial_development/index.cfm">Commercial / Industrial / Multi-Family Development</a>
			<ul style="display: none;">
				<li><a title="Call Before You Dig" href="?/building_development/commercial_industrial_development/call_before_you_dig.cfm">Call Before You Dig</a></li>
				<li><a title="New Development" href="?/building_development/commercial_industrial_development/new_development.cfm">New Development</a></li>
				<li><a title="Existing Development" href="?/building_development/commercial_industrial_development/existing_development.cfm">Existing Development</a></li>

				<li><a title="Signage" href="?/building_development/commercial_industrial_development/signage.cfm">Signage</a></li>
				<li><a title="Notice of Development" href="?/building_development/planning/notice_of_development/notice_of_development.cfm">Notice of Development</a></li>
				<li><a title="Appeals" href="?/public_meetings/appeals/index.cfm">Appeals</a></li>
				<li><a title="Customer Feedback" href="?/building_development/commercial_industrial_development/customer_feedback.cfm">Customer Feedback</a></li>
				<li><a title="Certificate of Compliance" href="?/building_development/commercial_industrial_development/certificate_of_compliance.cfm">Certificate of Compliance</a></li>
				<li><a title="Permit Applications &amp; Forms" href="?/building_development/commercial_industrial_development/permit_applications_forms.cfm">Permit Applications &amp; Forms</a></li>

				<li class="last"><a title="Fees" href="?/building_development/commercial_industrial_development/fees.cfm">Fees</a></li>
			</ul>
			</li>
			<li class="expandable lastExpandable"><div class="hitarea expandable-hitarea lastExpandable-hitarea"></div>This links to an empty content page (25 Sept 2007) <a title="Residential Development" href="?/building_development/residential_development/index.cfm">Residential Development</a>
			<ul style="display: none;">
				<li><a title="Call Before You Dig" href="?/building_development/residential_construction/building_permit_requirements.cfm">Building Permit Requirements</a></li>
				<li><a title="New Development" href="?/building_development/residential_construction/new_homes.cfm">New Homes</a></li>

				<li><a title="Existing Development" href="?/building_development/residential_construction/basements.cfm">Basements</a></li>
				<li><a title="Signage" href="?/building_development/commercial_industrial_development/call_before_you_dig.cfm">Call Before You Dig</a></li>
				<li><a title="Decks" href="?/building_development/residential_development/decks.cfm">Decks</a></li>
				<li><a title="Detached Garages or Accessory Building" href="?/building_development/residential_development/detached_garages_or_accessory_building.cfm">Detached Garages or Accessory Building</a></li>
				<li><a title="Grading" href="?/building_development/residential_development/grading.cfm">Grading</a></li>
				<li><a title="Fences" href="?/building_development/residential_development/fences.cfm">Fences</a></li>

				<li><a title="Applications, Permits &amp; Checklists" href="?/building_development/residential_development/applications_permits_checklists.cfm">Applications, Permits &amp; Checklists</a></li>
				<li><a title="Certificate of Compliance" href="?/building_development/commercial_industrial_development/certificate_of_compliance.cfm">Certificate of Compliance</a></li>
				<li><a title="Fees" href="?/building_development/residential_development/fees.cfm">Fees</a></li>
				<li><a title="Notice of Development" href="?/building_development/planning/notice_of_development/notice_of_development.cfm">Notice of Development</a></li>
				<li class="last"><a title="Street Addresses for New Construction" href="?/gis/index.cfm">Street Addresses for New Construction</a></li>

			</ul>
			</li>
		</ul>
		</li>
		<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/community_safety/index.cfm">Community Safety</a>
		<ul style="display: none;">
			<li><a href="?/disaster_services/index.cfm">Disaster Services</a></li>

			<li><a href="?/emergency_services/index.cfm">Emergency Services</a></li>
			<li><a href="?/municipal_enforcement/index.cfm">Municipal Enforcement</a></li>
			<li class="expandable lastExpandable"><div class="hitarea expandable-hitarea lastExpandable-hitarea"></div><a href="?/rcmp/index.cfm">Royal Canadian Mounted Police</a>
			<ul style="display: none;">
				<li><a title="Community Partnership Programs" href="?/rcmp/community_partnership_programs.cfm">Community Partnership Programs</a></li>
				<li class="last"><a title="Traffic Services" href="?/rcmp/traffic_services.cfm">Traffic Services</a></li>

			</ul>
			</li>
		</ul>
		</li>
		<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/community_services/index.cfm">Community Services</a>
		<ul style="display: none;">
			<li><a href="?/directories/community_directory/index.cfm">Community Directory</a></li>

			<li class="last"><a href="?/calendars/index.cfm">Community Calendar</a></li>

		</ul>
		</li>
		<li><a href="?/engineering/index.cfm">Engineering Services </a></li>
		<li><a href="?/finance/index.cfm">Finance</a></li>
		<li><a href="?/gis/index.cfm">Maps (GIS)</a></li>

		<li><a href="?/parks/parks_recreation.cfm">Parks &amp; Recreation</a></li>

		<li><a href="?/public_works/index.cfm">Public Works</a></li>
		<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="?/recycling_waste/index.cfm">Recycling, Waste &amp; Composting</a>
		<ul style="display: none;">
			<li class="last"><a href="?/environmental_services/index.cfm">Environmental Services </a></li>

		</ul>
		</li>

		<li><a href="?/social_planning/index.cfm">Social Planning</a></li>
		<li><a href="?/taxation/index.cfm">Taxation</a></li>
		<li><a href="?/transit/index.cfm">Transit</a></li>
		<li class="last"><a href="?/utilities/index.cfm">Water &amp; Sewer (Utilities)</a></li>

	</ul>
	</li>

</ul>
</div> -->

</div>
</s:form>
            </div>
            <%String val=(String)session.getAttribute("headddoc"); %>
            <%if(val.equals("2")){ %>
            <h4 id="headdoc">OPD Document</h4>
            <%}else if(val.equals("1")){ %>
            <h4 id="headdoc">IPD Document</h4>
			<%}else if(val.equals("0")){ %>
            <h4 id="headdoc">EMR Document</h4>
			<%}else{ %>	
            <h4 id="headdoc">Document</h4>
			<%} %>
            <div id="two" class="split split-horizontal tree-two">
            
              <table class="table table-responsive u-full-width">
                <thead>
                  <tr>
                    <th style="width: 5%;">No</th>
                    <th style="width: 15%;">Document Name</th>
                    <th style="width: 25%;">Note</th>
                    <th style="width: 27%;">Upload By | Date/Time</th>
                    <th class="hidden"></th>
                    <th class="hidden"></th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <%int i=0; %>
                  <s:iterator value="documentDetailedList">
                  <tr>
                    <td><%=++i %></td>
                    <%-- <td><a href="#" style="color:#000;" title="Download"><s:property value="fileName"/></a></td> --%>
                    <td><s:property value="doctType"/></td>
                    <td><s:property value="notes"/></td>
                    <td><s:property value="uploadBy"/> | <s:property value="lastModified"/></td>
                    <td class="hidden"><a href="downloadDocEmr?filename=<s:property value="fileName"/>" title="Print"><i class="fa fa-print fa-2x"></i></a></td>
                    <td class="hidden"><a href="javascript:void(0)" data-toggle="modal" data-target="#uploaddoc" onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
	 						title="Edit"><i class="fa fa-pencil fa-2x"></i></a></td>
                    <td>
                    	<s:if test="isavilablefordelete==1">
                    		<a href="#" onclick="deleteDocumentsnew('<s:property value = "id"/>')" title="Delete" class="text-danger"><i class="fa fa-trash-o text-danger fa-2x"></i></a>
                    	</s:if>
                    </td>
                	<td>
                		<%-- <a class="thumbnail" href="#" data-image-id="" data-toggle="modal" data-title=""
                   			data-image="liveData/document/<s:property value="fileName"/>"
                  			data-target="#image-gallery">
                    		<img class="img-thumbnail" style="height: 50px; width: 50px"
                         		src="liveData/document/<s:property value="fileName"/>"
                         		alt="Another alt text">
               			</a> --%>
               			
               			
               			 <a class="thumbnail" href="#" data-image-id="" onclick="openBlankPopup('liveData/document/<s:property value="fileName"/>')"
																	data-toggle="modal" data-title=""
																	data-image="liveData/document/<s:property value="fileName"/>"
																	data-target=""> <img
																	class="img-thumbnail" style="height: 50px; width: 50px"
																	src="liveData/document/<s:property value="fileName"/>"
																	alt="DOCS">
																</a>
               			
                	</td>
				 </tr>
                  </s:iterator>
                  <!-- <tr>
                    <td>2</td>
                    <td><a href="#" style="color:#000;" title="Download">OPD001_Admissionform001</a></td>
                    <td>note comes here</td>
                    <td>snhtesting | 13-11-2017 03:11</td>
                    <td><a href="#" title="Print"><i class="fa fa-print fa-2x"></i></a></td>
                    <td><a href="#" title="Edit"><i class="fa fa-pencil fa-2x"></i></a></td>
                    <td><a href="#" title="Delete"><i class="fa fa-trash-o text-danger fa-2x"></i></a></td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td><a href="#" style="color:#000;" title="Download">OPD001_Dischaargeform001</a></td>
                    <td>note comes here</td>
                    <td>snhtesting | 13-11-2017 03:11</td>
                   <td><a href="#" title="Print"><i class="fa fa-print fa-2x"></i></a></td>
                    <td><a href="#" title="Edit"><i class="fa fa-pencil fa-2x"></i></a></td>
                    <td><a href="#" title="Delete"><i class="fa fa-trash-o text-danger fa-2x"></i></a></td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <td><a href="#" style="color:#000;" title="Download">OPD001_Assessmentform_Dailytreatmentlog</a></td>
                    <td>note comes here</td>
                    <td>snhtesting | 13-11-2017 03:11</td>
                    <td><a href="#" title="Print"><i class="fa fa-print fa-2x"></i></a></td>
                    <td><a href="#" title="Edit"><i class="fa fa-pencil fa-2x"></i></a></td>
                    <td><a href="#" title="Delete"><i class="fa fa-trash-o text-danger fa-2x"></i></a></td>
                  </tr> -->
                </tbody>
              </table>
            </div>
        </div>
       
    </div>
    
    
    
    
	<!--Upload Model-->
<div class="modal fade" id="uploaddoc" style="z-index: 9999" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="uploadDocTitle">Upload New Document</h4>
			</div>

			<div class="modal-body">
				<div class="row">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<s:form id="upload" method="post" action="uploadDocumentsEmr" enctype="multipart/form-data" theme="simple">
					<s:hidden name="isvideo" id="isvideo"></s:hidden>
					<s:hidden name="clientname"></s:hidden>
					<div class="form-group">
						<select class="form-control fbackwhi" onchange="setIpdOpdEmr(this.value)" style="margin-bottom: 10px !important;" >
						     <option value="1">IPD</option>
						     <option value="2">OPD</option>
						     <option value="0">EMR</option>
						</select>
					</div>
					
					<div class="form-group">
					<%if(loginInfo.isAyushman()){ %>
					    <s:select cssClass="form-control fbackwhi" headerKey="0" theme="simple"
						headerValue="Select Type" name="doctType" id="doctType" onchange="setemruploaddocAjax(this.value,'doctype')"
						list="{'Consultant Report','Admission Form','Nursing','Investigation','Assessment Report','Discharge','Others'}" cssStyle="margin-bottom: 10px !important;"></s:select>
					<%}else{ %>
						<s:select cssClass="form-control fbackwhi" headerKey="0" theme="simple"
						headerValue="Select Type" name="doctType" id="doctType" onchange="setemruploaddocAjax(this.value,'doctype')"
						list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','Others'}" cssStyle="margin-bottom: 10px !important;"></s:select>
					<%}%>
					</div>
					
					<div class="form-group">
						<s:textarea cssClass="form-control fbackwhi" onblur="setemruploaddocAjax(this.value,'disc')"
						placeholder="Enter Document Note" rows="3" name="documentDesc"
						id="documentDesc"></s:textarea>
					</div>
					
					
					<span id="filename" style="color: white"></span>


					<div id="drop" style="background-color: #efefef;">
						Click Here For Upload Documents <a>Browse</a>
						<s:file name="files" theme="simple" multiple="multiple">
						</s:file>
					</div>

					<ul class="popmodals123">
						<!-- The file uploads will be shown here -->
					</ul>

				</s:form>
				</div>
					
				</div>


			</div>
			<div class="modal-footer">
				<s:form action="addDocumentsEmr" method="post" theme="simple">
					<s:hidden id="clientname" value="%{clientId}" name="clientname"></s:hidden>
					<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
					<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
					<s:hidden id="editDoctId" name="editDoctId"></s:hidden>
					<s:hidden id="ipdopdemr" value="1" name="ipdopdemr"></s:hidden>
					<s:hidden id="docapmtId" name="apmtId" />
					<s:hidden name="fromdashboard" value="1"></s:hidden>
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->

					<button type="submit" class="btn btn-primary start">Save</button>
				</s:form>

			</div>
		</div>
	</div>
</div>
<!--/Upload Model-->

	    
  <div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" style="height: 600px;">
                    <div class="modal-header">
                        <%-- <h4 class="modal-title" id="image-gallery-title">Preview</h4>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span>
                        </button> --%>
                        <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
						</button>
                    	<h4 class="modal-title hidden" id="image-gallery-title">Preview</h4>
                    	<h4 class="modal-title" >Preview</h4>
                    </div>
                    <div class="modal-body" >
                        <img style="height: 530px;" id="image-gallery-image" class="img-responsive col-md-12" src="">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary float-left" id="show-previous-image"><i class="fa fa-arrow-left"></i>
                        </button>

                        <button type="button" id="show-next-image" class="btn btn-secondary float-right"><i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>   
    
    
    
    
    
</body>



 <!-- JS -->
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
  <script src="inventory/js/searchtext/javascripts/initializers.js"></script>
  <!-- JS ends -->
  
   <script type="text/javascript">
		var r = document.getElementById('result');
</script>



<script src="emr/plugin/docs/split.js"></script>
<script>
    Split(['#one', '#two'], {
        sizes: [29, 71],
        minSize: 200
    });
</script>

<script type="text/javascript">
		$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "fast",
				control:"#sidetreecontrol",
				prerendered: true,
				persist: "location"
			});
		})

function deleteDocumentsnew(val){
			if(confirm('Sure You want to delete?')){
				var url ="deletedocCommonnew?docid="+val ;
				   
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			    req.onreadystatechange = deleteDocumentsnewReq;
			    req.open("GET", url, true); 
			              
			    req.send(null);
			}
			
		}		
		function deleteDocumentsnewReq(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					document.location.reload();
				}
			}	
		}
		
	</script>
	<script type="text/javascript">
	let modalId = $('#image-gallery');

	$(document)
	  .ready(function () {

	    loadGallery(true, 'a.thumbnail');

	    //This function disables buttons when needed
	    function disableButtons(counter_max, counter_current) {
	      $('#show-previous-image, #show-next-image')
	        .show();
	      if (counter_max === counter_current) {
	        $('#show-next-image')
	          .hide();
	      } else if (counter_current === 1) {
	        $('#show-previous-image')
	          .hide();
	      }
	    }

	    /**
	     *
	     * @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.
	     * @param setClickAttr  Sets the attribute for the click handler.
	     */

	    function loadGallery(setIDs, setClickAttr) {
	      let current_image,
	        selector,
	        counter = 0;

	      $('#show-next-image, #show-previous-image')
	        .click(function () {
	          if ($(this)
	            .attr('id') === 'show-previous-image') {
	            current_image--;
	          } else {
	            current_image++;
	          }

	          selector = $('[data-image-id="' + current_image + '"]');
	          updateGallery(selector);
	        });

	      function updateGallery(selector) {
	        let $sel = selector;
	        current_image = $sel.data('image-id');
	        $('#image-gallery-title')
	          .text($sel.data('title'));
	        $('#image-gallery-image')
	          .attr('src', $sel.data('image'));
	        disableButtons(counter, $sel.data('image-id'));
	      }

	      if (setIDs == true) {
	        $('[data-image-id]')
	          .each(function () {
	            counter++;
	            $(this)
	              .attr('data-image-id', counter);
	          });
	      }
	      $(setClickAttr)
	        .on('click', function () {
	          updateGallery($(this));
	        });
	    }
	  });

	// build key actions
	$(document)
	  .keydown(function (e) {
	    switch (e.which) {
	      case 37: // left
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-previous-image').is(":visible")) {
	          $('#show-previous-image')
	            .click();
	        }
	        break;

	      case 39: // right
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-next-image').is(":visible")) {
	          $('#show-next-image')
	            .click();
	        }
	        break;

	      default:
	        return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	  });

	
	</script>
</html>

