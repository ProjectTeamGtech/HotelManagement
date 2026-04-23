<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
    
    <%
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




	<link rel="stylesheet" href="_assets/css/priscription/Notification.css">
         <link rel="stylesheet" href="_assets/css/priscription/hospitalresponsive.css">
         <link href="common/assets/css/style.css" rel="stylesheet" />
      
     <link rel="stylesheet" href="plugin/slidervitals/infinityCarousel.css">
    
    
<!-- JavaScript Includes -->
<script src="common/assets/js/jquery.knob.js"></script> 
<script src="common/assets/js/script.js"></script>
<script src="common/assets/js/jquery.fileupload.js"></script>   

  <script type="text/javascript" src="plugin/slidervitals/infinityCarousel.js"></script>
  <!-- Speed -->
      <%-- 	<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script> --%>
	<%-- <script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script> --%>
         
    <script type="text/javascript" src="ipd/js/discharge.js"></script>  
    <script type="text/javascript" src="ipd/js/package.js"></script> 
    <script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
    <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>  
    <script type="text/javascript" src="emr/js/addnursingcare.js"></script>
    
	 <!-- BookBed.jsp Javascript -->
	 <script type="text/javascript" src="emr/js/addInvestigation.js"></script>
	  <script type="text/javascript" src="ipd/js/addcharge.js"></script>
      <script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
	  <script type="text/javascript" src="diarymanagement/js/sendsms.js"></script>
	  <script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
	  
	    <script type="text/javascript" src="emr/js/clinical_notes.js"></script>
	  
	 <%--  <script type="text/javascript" src="tools/js/sendLetter.js"></script> --%>
     <!-- /BookBed.jsp -->    
     
     <!-- jQuery File Upload Dependencies -->
<%-- <script src="common/assets/js/jquery.ui.widget.js"></script>
<script src="common/assets/js/jquery.iframe-transport.js"></script>  --%>



   
     
      
      <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
    
 

<head>
<style type="text/css">
/* cyrillic-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSKmu1aB.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSumu1aB.woff2) format('woff2');
  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSOmu1aB.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSymu1aB.woff2) format('woff2');
  unicode-range: U+0370-03FF;
}
/* hebrew */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS2mu1aB.woff2) format('woff2');
  unicode-range: U+0590-05FF, U+20AA, U+25CC, U+FB1D-FB4F;
}
/* vietnamese */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSCmu1aB.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSGmu1aB.woff2) format('woff2');
  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 300;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS-muw.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}
/* cyrillic-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSKmu1aB.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSumu1aB.woff2) format('woff2');
  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSOmu1aB.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSymu1aB.woff2) format('woff2');
  unicode-range: U+0370-03FF;
}
/* hebrew */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS2mu1aB.woff2) format('woff2');
  unicode-range: U+0590-05FF, U+20AA, U+25CC, U+FB1D-FB4F;
}
/* vietnamese */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSCmu1aB.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSGmu1aB.woff2) format('woff2');
  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS-muw.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}
/* cyrillic-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSKmu1aB.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSumu1aB.woff2) format('woff2');
  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSOmu1aB.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSymu1aB.woff2) format('woff2');
  unicode-range: U+0370-03FF;
}
/* hebrew */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS2mu1aB.woff2) format('woff2');
  unicode-range: U+0590-05FF, U+20AA, U+25CC, U+FB1D-FB4F;
}
/* vietnamese */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSCmu1aB.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSGmu1aB.woff2) format('woff2');
  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS-muw.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}
/* cyrillic-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSKmu1aB.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSumu1aB.woff2) format('woff2');
  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSOmu1aB.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSymu1aB.woff2) format('woff2');
  unicode-range: U+0370-03FF;
}
/* hebrew */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS2mu1aB.woff2) format('woff2');
  unicode-range: U+0590-05FF, U+20AA, U+25CC, U+FB1D-FB4F;
}
/* vietnamese */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSCmu1aB.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTSGmu1aB.woff2) format('woff2');
  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 700;
  font-stretch: 100%;
  font-display: swap;
  src: url(newMnsIpd/assets/open_Sans/memvYaGs126MiZpBA-UvWbX2vVnXBbObj2OVTS-muw.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}


</style>

<style type="text/css">
#priscriptionpopup .modal-lg{
min-width: 81% !important;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div id="preloader">
        <div class="loader"></div>
    </div>
   <div class="main-content-inner">
   			<div class="sales-report-area sales-style-two" style="display: ;">
            	<div class="container-fluid" style="background-color: #CEECF2;">
                    <p style="color: #15536E;font-weight: 600;padding: 20px 0px;font-size: 20px;">IPD (Hospital Inpatient Care)</p>
                    <div class="row custom-block-1" style="padding-bottom: 20px;">
                        
                        <div class="col-xl-2 col-ml-2 col-md-6 mt-2">
                             <div class="card mb-3" style="max-width: 540px;border-radius: 15px;cursor: pointer;" onclick="openIpdPopup('InitialDischarge?filter=1&maintype=1')">
                                 <div class="row no-gutters" style="padding-left: 13px;">
                                   <div class="col-md-6 card-1">
                                     <img src="manasmaindashboard/images/ICON_New IPD Admission.svg"  alt="...">
                                     <h1 style="display: inline;font-size: 22px;"><s:property value="totolintitaldischarge"/></h1>
                                   </div>
                                   <div class="col-md-6">
                                     <div class="card-body">
                                       <p style="color: #15536E;font-weight: 600;">Discharge<br>Initiated</p>      
                                     </div>
                                   </div>
                                 </div>
                               </div>
                        </div>
                        <div class="col-xl-2 col-ml-3 col-md-6 mt-2">
                            <div class="card mb-3" style="max-width: 540px;border-radius: 15px;cursor: pointer;" title="Inhouse Patient" onclick="openPopup('currentpatientsSummary')">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_In-House Patients.svg"  alt="...">
                                    <h1 style="display: inline;font-size: 22px;"><s:property value="totalbookedbed"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">In - House<br>Patients</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                        <div class="col-xl-2 col-ml-3 col-md-6 mt-2">
                             <div class="card mb-3" style="max-width: 540px;border-radius: 15px;cursor: pointer;" title="Discharged Patient" onclick="openIpdPopup('InitialDischarge?filter=6&maintype=2')">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_Discharge Initiaated.svg"  alt="...">
                                    <h1 style="display: inline;font-size: 22px;"><s:property value="totaldischarge"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">Discharged<br>Patient</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                        <div class="col-xl-2 col-ml-2 col-md-6 mt-2">
                            <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_Vacant BEds.svg"  alt="...">
                                    <h1 style="display: inline;font-size: 22px;"><s:property value="totalbed"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">Total<br>Beds</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                       
                        <div class="col-xl-2 col-ml-2 col-md-6 mt-2">
                            <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_Vacant BEds.svg"  alt="...">
                                    <h1 style="display: inline;font-size: 22px;"><s:property value="totalvacantbed"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">Vacants<br>Beds</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                    </div>
                </div>
            </div>
            
                <div class="container-fluid" style="background-image: url('assets/images/Union 12.svg'); background-repeat: no-repeat, repeat;background-color: honeydew;">
                    <div class="row" style="padding-top: 10px;background-color: #ceecf2;padding-bottom: 30px;" >
                    		<div class="col-xl-12 col-ml-12 col-md-12 ">
                    		<s:form action="IpdDashboard" id="ipddashboardfrm"  theme="simple">
                    			<s:hidden name="androidpractid"></s:hidden>
								<s:hidden name="isfromandroid"></s:hidden>
								<s:hidden name="androidpractuserid"></s:hidden>
								<s:hidden name="excessamtbt" id="excessamtbt"></s:hidden>
								<input type="hidden" name="action" value='<s:property value="casualtyipd"/>'>
								<div class="col-xl-2 col-ml-2 col-md-2 mt-2">
									<input type="text" placeholder="Search Patient/Bed" id="filter" value="" class="form-control" style="text-transform: uppercase;"/>
								</div>
								
									<s:if test="casualtyipd==2">
			                        </s:if>
			                        <s:elseif test="casualtyipd==1"></s:elseif>
			                        <s:else>
			                        <div class="col-xl-2 col-ml-2 col-md-2 mt-2">
			                          	<s:select name="wardid" id="wardid" list="wardList" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="All" onchange="showWardBed(this.value)"/>
			                        </div>
			                        </s:else>
			                        
								
								<div class="col-xl-2 col-ml-2 col-md-2 mt-2">
									<s:select list="#{'0':'Inactive Bed','1':'Active Bed'}" theme="simple" name="activefilter" onchange="showBedstatus(this.value)" cssClass="form-control chosen-select"  ></s:select>
								</div>
								<div class="col-xl-3 col-ml-3 col-md-3 mt-3">
									<input type="submit" class="btn mybtn" onclick="submitExcessamtbt()" value="Excess Amt">
									&nbsp;
									<s:hidden name="freez_status" id="freez_status"></s:hidden>
									<s:hidden id="foripddashboardpriscription"></s:hidden>
									<s:if test="freez_status==0">
										<input type="submit" onclick="setfreezevalue(0)" class="btn mybtn" value="Freez">
									</s:if>
									<s:else>
										<input type="submit" onclick="setfreezevalue(1)" class="btn mybtn-orange" value="Unfreeze">
									</s:else>
									
								</div>
								<div class="col-xl-1 col-ml-1 col-md-1 mt-1">
									
								</div>
							</s:form>	
                           	</div>
                           <%--      <div class="col-lg-7 col-md-6 Isolation-1">
                                    <div class="search-box-2 pull-left">
                                        <form action="#">
                                           
                                            <input type="text" name="search" placeholder="Search patients, appointments, help or anything else " required class="opd-search">
                                            
                                           
                                              <div class="dropdown">
                                                 <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Type of Bed
                                                 </button>
                                                 <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                                  <!--  <button class="dropdown-item" type="button">Action</button>
                                                   <button class="dropdown-item" type="button">Another action</button>
                                                   <button class="dropdown-item" type="button">Something else here</button> -->
                                                    <s:iterator value="mnsWardList">
                                                     <button class="dropdown-item" type="button"><s:property value="name"/></button>
                                                    </s:iterator>
                                                 </div>
                                               </div>
                                               <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Bed Status
                                                </button>
                                                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                                  <button class="dropdown-item" type="button">Action</button>
                                                  <button class="dropdown-item" type="button">Another action</button>
                                                  <button class="dropdown-item" type="button">Something else here</button>
                                                </div>
                                              </div>
                                        </form>
                                       
                                    </div>
                                  
                                </div>
                                <div class="col-lg-5 col-md-6 Isolation">
                                    <a href="" class="btn btn-warning">Search</a>
                                        <a href="" class="btn btn-warning" style="margin-left: 30px;">Check Bed Availability</a>
                                    <a href="" class="btn btn-light-1" ><img src="newMnsIpd/assets/images/Reportss_Icon.svg" style="padding-right: 15px;">Get Reports</a>
                                </div> --%>
                    </div>
                    
                    <%int ttlvaccntbed=0,totalbed=0; %>
                    <%--  <s:iterator value="mnsWardList">
                            <s:iterator value="bedList">
                            	<%totalbed++; %>
	                            <s:if test="status!=1">
	                             	<%ttlvaccntbed++; %>
	                            </s:if>
                            </s:iterator>
                     </s:iterator>         --%>                
         <div class="container-fluid">
               <div class="row" style="margin-top: 30px;">
                   <div class="col-lg-12 col-12 custom-block-bedlist" style="width: width:100%;" >
                    <s:iterator value="bedlist">
                    	<s:if test="status==1">
                    		<div class="card-deck commentlist">
                                 <div class="card-isolation">
                                     <div class="card-body">
                                        <div class="">
                                        	<a href="#" onclick="showipdpopup('<s:property value="id"/>','<s:property value="clientid"/>',
															'<s:property value="practitionerid"/>','<s:property value="conditionid"/>','<s:property value="clientname"/>',
															'<s:property value="practitionername"/>','<s:property value="dob"/>','<s:property value="town"/>',
															'<s:property value="addmissionid"/>','<s:property value="age"/>',
															'<s:property value="whopay"/>','<s:property value="tpid"/>',
															'<s:property value="tpname"/>','<s:property value="practitionerMob"/>',
															'<s:property value="bedname"/>','<s:property value="wardname"/>',
															'<s:property value="balance"/>','<s:property value="treatmentepisodeid"/>',
															'<s:property value="dis_initiate_time"/>','<s:property value="dis_initiate_status"/>',
															'<s:property value="dis_form_time"/>','<s:property value="dis_form_status"/>',
															'<s:property value="dis_mdicine_time"/>','<s:property value="dis_mdicine_status"/>',
															'<s:property value="dis_bill_time"/>','<s:property value="dis_bill_status"/>',
															'<s:property value="dis_nursing_time"/>','<s:property value="dis_nursing_status"/>',
															'<s:property value="imagename"/>','<s:property value="action"/>','<s:property value="abrivationid"/>',
															'<s:property value="ipdseqno"/>')">
	                                         <div class="col-lg-6 col-6">
	                                         		<s:if test="excessAmt==1">
											        	<s:if test="mlccase==1">
											        		<div class="card-left-isolation" style="background-color: #ea371a;">
				                                                 <h1><s:property value="wardname"/></h1>
				                                                 <h1> <s:property value='bedname'/></h1>
				                                            </div>
											        	</s:if>
											        	<s:else>
											        		<div class="card-left-isolation" style="background-color: #0E93FF;">
				                                                 <h1><s:property value="wardname"/></h1>
				                                                 <h1> <s:property value='bedname'/></h1>
				                                            </div>
											        	</s:else>
											        </s:if>
											        <s:else>
											        	<s:if test="mlccase==1">
											        		<div class="card-left-isolation" style="background-color: #ea371a;">
				                                                 <h1><s:property value="wardname"/></h1>
				                                                 <h1> <s:property value='bedname'/></h1>
				                                            </div>
											        	</s:if>
											        	<s:else>
												        	<s:if test="payby=='SELF'">
												        		<div class="card-left-isolation">
					                                                 <h1><s:property value="wardname"/></h1>
					                                                 <h1> <s:property value='bedname'/></h1>
				                                            	</div>
												        	</s:if>
												        	<s:else>
												        	<!-- Ayushman Bharat Yojana -->
												        		<s:if test="payby=='AYB'">
												        			<div class="card-left-isolation" style="background-color: #a14fa7;">
						                                                 <h1><s:property value="wardname"/></h1>
						                                                 <h1> <s:property value='bedname'/></h1>
				                                            		</div>
												        		</s:if>
												        		
												        		<!-- Biju -->
												        		<s:elseif test="payby=='BSKY'">
												        		    <div class="card-left-isolation" style="background-color: #0000ff;">
						                                                 <h1><s:property value="wardname"/></h1>
						                                                 <h1> <s:property value='bedname'/></h1>
				                                            		</div>
												        		</s:elseif>
												        		 <s:else>
												        			<div class="card-left-isolation" style="background-color: #1abf26;">
						                                                 <h1><s:property value="wardname"/></h1>
						                                                 <h1> <s:property value='bedname'/></h1>
				                                            		</div>
												        		</s:else>
												        		
												        	</s:else>
											        	</s:else>
											        </s:else>
	                                             
	                                         </div>
	                                         <div class="col-lg-6 col-6" style="padding-left: 0px;padding-right: 0px;padding-top: 5px;">
	                                         <!-- <h3>Patient ID</h3> -->
	                                                 <p><s:property value="clientname"/></p>
	                                                 <p><s:property value="age"/>/<s:property value="gender"/> (<s:property value="payby"/>)</p> 
	                                                 <p><s:property value="admissiondate"/></p>
	                                                 <p><s:property value='abrivationid'/></p>
	                                                 <p>
	                                                	 <s:property value="ipdseqno"/>
	                                                 </p> 
													 <p><s:property value="practitionername"/></p> 
													 <%if(loginInfo.isSaimed()) {%>
													 <s:if test="balance>0">
													<p style="color: red;"><s:property value="balance"/></p>
													</s:if>
													 <s:if test="balance==0 && advance>0">
													<p style="color: green;"><s:property value="advance"/></p></s:if>
													 <s:if test="balance==0 && advance==0">
													<p>0</p></s:if><%} %>
	                                        </div>
	                                        </a>
                                        </div>
                                     </div>
                                     <div class="card-footer">
                                     		<a class="" href="#" onclick="getajaxnotification(<s:property value="addmissionid"/>)">
                                     			<img src="newMnsIpd/assets/images/Bell icon.svg"  title="Notification">
                                     		</a>
                                          
                                            <s:if test="freez_status==0">
												<s:if test="initialdischargeStatus==0">
		                                          	<a href="#" onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',0)" >
		                                          		<img src="newMnsIpd/assets/images/Initiate Discharge.svg" style="color:#22beef !important;" title="Initiate Discharge Process">
		                                          	</a>
		                                        </s:if>
		                                        <s:else>
		                                          	<a href="#" onclick="setinitialDischargeIpd('dis_initiate_status','dis_initiate_time','<s:property value="treatmentepisodeid"/>',1)">
		                                          		<img src="newMnsIpd/assets/images/Initiate Discharge Oranage.svg"  style="color:orange !important;" title="Discharge Initiated">
		                                          	</a>
		                                        </s:else>
											</s:if>
											<s:else>
												<s:if test="initialdischargeStatus==0">
		                                          	<a href="#" onclick="openfreezeerror()" >
		                                          		<img src="newMnsIpd/assets/images/Initiate Discharge.svg" style="color:#22beef !important;" title="Initiate Discharge Process">
		                                          	</a>
		                                        </s:if>
		                                        <s:else>
		                                          	<a href="#" onclick="openfreezeerror()">
		                                          		<img src="newMnsIpd/assets/images/Initiate Discharge Oranage.svg"  style="color:orange !important;" title="Discharge Initiated">
		                                          	</a>
		                                        </s:else>
											</s:else>
                                          
                                          	
                                         
                                          <%if(loginInfo.isSaimed()) {%>
                                          
                                          <s:if test="autochargeraised==0">
                                          	<a href="#" class="hidden" onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')">
                                          		<img src="newMnsIpd/assets/images/Auto Charge.svg"  style="color:#22efd4 !important;" title="Icu Charge" >
                                          	</a>
                                          	
                                          </s:if>
                                          <s:else>
                                          	<a href="#" class="hidden" onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')">
                                          		<img src="newMnsIpd/assets/images/Auto Charge.svg"  style="color:#ecf04e !important;" title="Icu Charge">
                                          	</a>
                                          </s:else>
                                          <%}else{ %>
                                          <s:if test="autochargeraised==0">
                                          	<a href="#" onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')">
                                          		<img src="newMnsIpd/assets/images/Auto Charge.svg"  style="color:#22efd4 !important;" title="Icu Charge">
                                          	</a>
                                          	
                                          </s:if>
                                          <s:else>
                                          	<a href="#" onclick="openStdChargePopup(<s:property value="tpid"/>,<s:property value="wardid"/>,<s:property value="clientid"/>,<s:property value="addmissionid"/>,'<s:property value="whopay"/>')">
                                          		<img src="newMnsIpd/assets/images/Auto Charge.svg"  style="color:#ecf04e !important;" title="Icu Charge">
                                          	</a>
                                          </s:else>
                                          
                                          <%} %>
                                          <s:if test="invsid>0">
                                          	<a href="#" onclick="openGraph(<s:property value="clientid"/>)" title="Investigation Chart">
                                          		<img src="newMnsIpd/assets/images/Investigation Chart.svg" >
                                          	</a>
                                          </s:if>
                                          <a href="#" onclick="openPopup('detailStatement?clientId=<s:property value="clientid"/>')" title="Charges Details">
                                          	<img src="newMnsIpd/assets/images/Charge Details.svg" >
                                          </a>
                                          
                                     </div>
                                  </div>
                             </div>
                    	</s:if>
                    	<s:else>
                    		<s:if test="isactive==1">
                    			<div class="card-deck commentlist">
                                 <div class="card-isolation">
                                     <div class="card-body">
                                     	<div class="row">
                                        	 <div class="col-lg-6 col-6">
                                            	<div class="card-left-isolation-2" style="background-color: #43b9be;">
                                                	<h1><s:property value="wardname"/></h1>
                                             		<h1> <s:property value='bedname'/></h1>
                                             	</div>
                                         	 </div>
	                                         <div class="col-lg-6 col-6 Reserve">
	                                         	<p >BED AVAILABLE</p>
	                                         </div>
                                    	</div>
                                     </div>
                                     <%-- <a href="#" class="btn btn-dark" onclick="opencPopup('inputIpd?wardid=<s:property value="wardid"/>&bedid=<s:property value="id"/>&action=<s:property value="action"/>')" >Reserve the Bed</a> --%>
                             	 	 
                             	 	<s:if test="freez_status==0">
										<a href="#" class="btn btn-dark" onclick="opencPopup('inputCommonnew?wardid=<s:property value="wardid"/>&bedid=<s:property value="id"/>&action=<s:property value="action"/>')" >Reserve the Bed</a>
									</s:if>
									<s:else>
										<a href="#" class="btn btn-dark" onclick="openfreezeerror()" >Reserve the Bed</a>
									</s:else>
                             	 	 
                             	 	  
                             	 </div>
                           		</div>
                    		</s:if>
                    		<s:else>
                    			<div class="card-deck commentlist">
                                 <div class="card-isolation">
                                     <div class="card-body">
                                     	<div class="row">
                                        	 <div class="col-lg-6 col-6">
                                            	<div class="card-left-isolation-2" style="background-color: #b1abab;">
                                                	<h1><s:property value="wardname"/></h1>
                                             		<h1> <s:property value='bedname'/></h1>
                                             	</div>
                                         	 </div>
	                                         <div class="col-lg-6 col-6 Reserve">
	                                         	<p >Not Available</p>
	                                         </div>
                                    	</div>
                                     </div>
                                 </div>
                           		</div>
                    		</s:else>
                    		
                    	</s:else>
                    </s:iterator>
              </div>
          </div>
       </div>
                    
                    
                </div>
        </div>
        
        
        <jsp:include  page="/newMnsIpd/pages/ipd_includer.jsp" />
       
        
     
        
        
        </body>
        <script>
	$(document).ready(function(){
		    $("#filter").keyup(function(){
		 
		        // Retrieve the input field text and reset the count to zero
		        var filter = $(this).val(), count = 0;
		 
		        // Loop through the comment list
		        $(".commentlist").each(function(){
		 
		            // If the list item does not contain the text phrase fade it out
		            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
		                $(this).fadeOut();
		 
		            // Show the list item if the phrase matches and increase the count by 1
		            } else {
		                $(this).show();
		                count++;
		            }
		        });
		 
		        // Update the count
		        var numberItems = count;
		        $("#filter-count").text("Number of Comments = "+count);
		    });
		});
</script>
</html>