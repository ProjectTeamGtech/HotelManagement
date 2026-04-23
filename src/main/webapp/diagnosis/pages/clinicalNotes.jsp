<!DOCTYPE html>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%Client clientDetails=(Client)request.getAttribute("clientDetails"); %>
<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<%LoginInfo loginInfo= LoginHelper.getLoginInfo(request); %>

<link href="toggale/js/smartmenus-0.9.7/css/sm-clean/sm-clean.css"
 rel="stylesheet" />
<link href="toggale/js/smartmenus-0.9.7/css/sm-core-css.css"
 rel="stylesheet" />


  <link rel="stylesheet" href="_assets/newtheme/css/main.css">

<!-- Custom styles for this template -->
<link href="toggale/css/style.css" rel="stylesheet">
 <!-- <link href="toggale/css/style-responsive.css" rel="stylesheet">  -->
<link href="common/assets/css/style.css" rel="stylesheet" /> 
<link href="common/owlcarousel/owl-carousel/owl.carousel.css"
 rel="stylesheet" />
<!--   <link href="emr/css/jquery.treeview.css" rel="stylesheet" /> -->

<!-- MY Resizable Plugin css and js -->
<link rel="stylesheet" href="common/Font-Awesome-master/css/font-awesome.min.css">
<!-- Theme -->
    <link href="_assets/plugin/reziseplugin/main.css" rel="stylesheet" type="text/css" />
    <link href="_assets/plugin/reziseplugin/plugins.css" rel="stylesheet" type="text/css" />
    <link href="_assets/plugin/reziseplugin/responsive.css" rel="stylesheet" type="text/css" />
   <link href="_assets/plugin/reziseplugin/icons.css" rel="stylesheet" type="text/css" />
    <link href="_assets/plugin/bottomemenu/wheelmenu.css" rel="stylesheet" type="text/css" />
    <link href="_assets/plugin/bottomemenu/menuboticon.css" rel="stylesheet" type="text/css" />
    
   
   <link href="emr/plugin/side-slider.css" rel="stylesheet" type="text/css" media="screen">


 <script type="text/javascript" src="_assets/plugin/reziseplugin/breakpoints.js"></script>
    <script type="text/javascript" src="_assets/plugin/reziseplugin/respond.min.js"></script> <!-- Polyfill for min/max-width CSS3 Media Queries (only for IE8) -->
    <script type="text/javascript" src="_assets/plugin/reziseplugin/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="_assets/plugin/reziseplugin/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="_assets/plugin/reziseplugin/jquery.slimscroll.horizontal.min.js"></script>
    <!-- App -->
    <script type="text/javascript" src="_assets/plugin/reziseplugin/app.js"></script>
    <script type="text/javascript" src="_assets/plugin/reziseplugin/plugins.js"></script>
    <script type="text/javascript" src="_assets/plugin/reziseplugin/plugins.form-components.js"></script>
	<script type="text/javascript" src="_assets/plugin/bottomemenu/jquery.wheelmenu.js"></script> 
   
    <script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
   
   <script type="text/javascript" src="dietary/js/dietaryCatDetail.js"></script>
   <script type="text/javascript" src="emr/js/clinical_notes.js"></script>
   
      <script type="text/javascript" src="emr/js/addnursingcare.js"></script>
   
<!--jquery dependencies-->
        <link href="emr/css/dropdownuse/jquery-ui.css" rel="stylesheet" />   
        <script src="emr/css/dropdownuse/jquery-ui.js"></script>

        <!--pqSelect dependencies-->
        <link href="emr/css/dropdownuse/pqselect.dev.css" rel="stylesheet" />
        <script src="emr/css/dropdownuse/pqselect.dev.js"></script>
        <script src="emr/js/jquery.sieve.js"></script>
       
       
       <script src="common/assets/js/jquery.iframe-transport.js"></script>  
<script src="common/assets/js/jquery.fileupload.js"></script>


<!-- JavaScript Includes -->
<script src="common/assets/js/jquery.knob.js"></script> 
<script src="common/assets/js/script.js"></script> 

<!-- js placed at the end of the document so the pages load faster -->
	<script src="toggale/js/slimScroll/jquery.slimscroll.min.js"></script>
	
	<script src="toggale/js/smartmenus-0.9.7/jquery.smartmenus.min.js"></script>
	
	<script src="toggale/js/jquery-ui-1.9.2.custom.min.js"></script>
	
	<script class="include" type="text/javascript"
		src="toggale/js/jquery.dcjqaccordion.2.7.js"></script>
		
	<script src="toggale/js/common-scripts.js"></script> 
	
	 
        



<script type="text/javascript" src="diagnosis/js/problemlisting.js"></script>
<script type="text/javascript" src="ipd/js/addcharge.js"></script>
  <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
  <script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
  <script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>  
  <script type="text/javascript" src="emr/js/emrNew.js"></script>
<script type="text/javascript">

$(window).on('load', function() {
	 /* document.getElementById("opdloaderPopup").className="";
	  document.getElementById("opdloaderPopup").className="modal fade";
	  $('#opdloaderPopup').css('display','none');
	  
	  $('body').removeClass('modal-open'); */
	$('#newloaderPopup').modal('hide');
});


$(document).ready(function() {
	 $('#newloaderPopup').modal('show');
	$("#fromdate1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
	$(".picker").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
});	





bkLib.onDomLoaded(function() {
     new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 200}).panelInstance('maintextarea');
	 $('.nicEdit-panelContain').parent().width('auto');
	 $('.nicEdit-panelContain').parent().next().width('auto');
	 $('.nicEdit-main').width('100%');
	 $('.nicEdit-main').height('480px');
	 
	 setclientidbyClinicalnotes();
	 setclientidbyClinicalnotesInv();
});



/* window.onload=function(){
	showreport()
}; */
</script>
<style>
.my-table td {
text-align: center;

}
.my-table th {
text-align: center !important;
}
.loktable th{
border: 2px dashed;
height: 50px;
}
.loktable td{
border: 2px dashed;
height: 50px;
}

/* .loktable tr{
border: 2px thick;
height: 50px;
} */


@charset "UTF-8";
.bedsearch {
	margin-left: -19px;
}
.resub{
float: right;
    margin-right: 40px;
    margin-top: 20px;
}
.caktitle{
    background-color: #eeeeee;
}
.infoicon{
             	font-size: 19px !important;
    			line-height: 33px !important;
         }
  /* .tables_ui th, td{
border: 1px dashed gray !important;
} */


<style>
.tables_ui th,td {
  text-align: left;
  padding: 2px 4px;
  border: 1px solid;
}

<!--

#upload {
    background-color: #fff;
    padding: 0px;
    border-radius: 0px;
}
.radio, .checkbox {
    position: relative;
    display: block;
    min-height: 14px;
    margin-top: 2px;
    margin-bottom: 1px;
}
.topbd{
background-color: #43b9be;
    padding: 6px 0px 8px 8px;
    width: 100%;
    color:#fff;
}
.navbar .navbar-nav {
    width: 100% !important;
}
.clientnamefon{
font-size: 12px;
}
.set{
	margin-top: -15px;
    margin-left: 18px;
}
.fbackwhi {
   background-color: #fff !important;
}
.emricons{
    margin-right: -29px;
}
.ionemrse{
    display: inline-flex;
    float: right;
}
.navbar {
    background: #43b9be;
    min-height: 40px !important;
        border-bottom: none !important;
    }
    .page-title span {
    display: block;
    font-size: 12px;
    color: #888888;
    font-weight: normal;
    line-height: 16px;
}
.setnotfor{
       
           padding-left: 0px;
    padding-right: 0px;
	}
	.tofilter{
		    padding-left: 0px;
    padding-right: 0px;
    background-color: #efefef;
    padding: 4px;
    margin-top: -5px;
        height: 34px;
	}
	.mt {
    margin-top: -11px !important;
    padding-top: 20px;
   /* background-color: #43b9be;*/
}
.panel .panel-heading .panel-title > a {
    display: block;
    cursor: pointer;
    font-size: 14px;
    padding: 0;
}

.updatbtnset{
	background-color: #555;
    color: #fff;
    padding: 4px 10px 7px 10px;
    border-radius: 21px;
    font-size: 15px;
}

.menusetleft{
	float: left;
    padding-top: 0px;
    background-color: #6699CC;
    color: #fff;
   	display: inline-flex;
   	border-top: 1px solid #fff;
    border-left: 1px solid #fff;
}
.setplubtn{
	padding-left: 0px;
    margin-top: 6px;
    margin-left: 2px;
}
#dropset {
                display: none;
            }
            .panel-group {
    margin-bottom: 5px;
}
.topsave{
	float: right;
    margin-top: -2px;
    margin-right: 20px;
    background-color: #555;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 3px 7px;
    text-decoration: none;
    line-height: 25px;
}
li a:hover {
    background-color: rgb(66, 74, 93);
    color:#fff !important;
    line-height: 25px;
}
.spanslash{
	    padding-top: 8px;
}
.emrtitle{
	padding-left:0px;padding-right:0px;margin-top: 7px;margin-left: 23px;height:50px;
}
.marmin8{
	margin-left: -8px; margin-top:17px;
}
/* md */
@media (min-width: 992px) and (max-width: 1199px) {
 li a {
    padding: 3px 12px !important;
} 
.mt {
    padding-top: 35px !important;
}
}

/* sm */
@media (min-width: 768px) and (max-width: 991px) {

li a {
    padding: 3px 12px !important;
}
.mt {
 margin-top: 0px !important;
    padding-top: 35px !important;
}.emrtitle {
    padding-left: 0px;
    padding-right: 0px;
    margin-top: 7px;
    margin-left: 0px;
    height: 57px;
}
.marmin8 {
    margin-left: -8px;
    margin-top: -7px;
}


}

.panel-title {
    font-size: 13px;
    font-weight: 600;
    padding: 6px;
}
.micimg{
	width: 5%;
    float:right;
}
@media only screen and (max-width: 479px) { 
.emrtitle{
	padding-left:0px;padding-right:0px;margin-top: 0px !important;margin-left: 0px !important;
}
.marmin8{
	          margin-left: -16px;
    width: 110%;
}
li a {
    padding: 3px 4px !important;
}
.mt {
    margin-top: -20px !important;
    padding-top: 20px !important;
    /* background-color: #43b9be; */
}
.row1 {
    margin-left: 0px !important;
    margin-right: 0px !important;
}
.widthtd{
		    width: 10% !important;
	}
}
 /* filter table specific styling */
    td.alt { background-color: #ffc; background-color: rgba(255, 255, 0, 0.2); }

-->
body {
    background: #fff;
}
.oneseticonleft {
    padding-right: 0px;
    padding-left: 0px;
    margin-left: -11px;
    padding-top: 2px;
    padding-bottom: 3px;
}
ul li {
    list-style: none;
    border-bottom: 1px solid rgba(222, 222, 222, 0.68);
    padding: 2px 0px 2px 5px;
    font-size: 11px;
    text-transform: uppercase;
}
.assesheight{
	    padding: 0px;
    border: 1px solid #ddd;
    min-height: 570px;
}

.panel-heading {
    padding: 3px 5px;
}
.social {
  text-align: center;
}

.social .link {
  display: inline-block;
  vertical-align: middle;
  position: relative;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-clip: content-box;
  padding: 0px;
  transition: .5s;
  color: #D7D0BE;
  margin-left: 0px;
  margin-right: 0px;
  font-size: 18px;
  line-height: 30px;
}

.social .link.iconset {
  background-color: #6699cc;
  color: white;
}
.heiige{
background-color: #55555530;
    padding: 4px 4px 4px 4px;
    margin-bottom: 5px;
}

#ui-datepicker-div {    width: 340px;
}

.ui-datepicker-title{
color: black;
}       
</style>
</head>
<body>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="col-lg-12 col-md-12 col-sm-12">
											<h4 align="center"><b>Clinical Notes</b>
											<a href="#" style="color:#fff;"><i style="margin-top: -7px;" class="fa fa-info-circle infoicon" aria-hidden="true"></i></a></h4>
										</div>
										
									</div>
								</div>
								<div class="row ">
									<div class="">
										<div>
														<div >
				<div class="col-lg-12 col-md-12">
					<div class="col-lg-2 col-md-2" style="padding-left: 0px;">
						<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row ">
								<div id="inner-content-div">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
										<div class="">
											<div class="some-content-related-div">
											<p><input type="text" name="cln" id="cln" onkeyup="searchcln()" placeholder="search clinical notes" class="form-control" style="width:80%" ></p>
											
											
											<table class="table table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr class="tableback">
														<th><!-- <input type="checkbox" class="case" 
															onclick="selectAll()" /> --></th>
														<th>Diagnosis (Step 1 Choose Diagnosis) &nbsp;&nbsp;&nbsp;<i class="fa fa-plus" onclick="addtoclinicalnotesmaster()"></i></th>
													</tr>
												</thead>
												<tbody id="clinicalmasterlist">
													
													<s:iterator value="diagnosislist">
													<%-- 	<tr>
															<td><input type="checkbox" class="case"
																id="ch<s:property value="id"/>"
																name="ch<s:property value="id"/>"
																value="<s:property value="id"/>"
																onclick="showreport(this.value)" /></td>
															<td><s:property value="name" /></td>
														</tr> --%>
														 <tr>
												              <s:if test="autodiagnosis==1">
												              	<td>
												              		<input type="checkbox" checked="checked" class="case" id="ch<s:property value="id"/>" name="ch<s:property value="id"/>"
												                	value="<s:property value="id"/>" onclick="showclinicalnotesproblem(this.value)" />
												              	</td>
												              </s:if>
												              <s:else>
												              	<td>
												              		<input type="checkbox" class="case" id="ch<s:property value="id"/>" name="ch<s:property value="id"/>"
												                	value="<s:property value="id"/>" onclick="showclinicalnotesproblem(this.value)" />
												                </td>
												              </s:else>
               												<td><s:property value="name" /></td>
              											</tr>
              											<s:hidden name="lastclinicalnotesid" id="lastcount"/>
													</s:iterator>
												</tbody>
											</table>
											</div>
											</div>
										</div>
									</div>
								</div>
					
							</div>
				</div>
				
				<!--lokesh  -->
					<div id="clinicalnotestomasterdiv" class="hidden">
					<input type="text" name="clinicalnotestomaster" id="clinicalnotestomaster" class="form-control" style="width:80%" placeholder="Add Clinical Notes">
					<button class="btn btn-primary" onclick="savetoclinicalnotesmaster()">Save</button>
					</div>		
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">

							<%
								String ipdid = (String) session.getAttribute("ipdid");
								String clientid = (String) session.getAttribute("clientid");
								String practitionerid = (String) session.getAttribute("practitionerid");
								String condition = (String) session.getAttribute("condition");
								Bed bed=(Bed) request.getAttribute("bed");
								String date=(String) request.getAttribute("date");
							%>
							<s:form action="getPatientRecordEmr" id="consultform">

								<input type="hidden" name="clientname" id='cid' value="<%=clientid%>"  />
								<input type="hidden" name="diaryUser" id="diaryUser" value='<s:property value='diaryUser'/>'/>
								<input type="hidden" name="condition" id="condition" />

							</s:form>

							
						</div>
					</div>
					<div class="col-lg-2 col-md-2" style="padding-right: 0px;">
						<!--Second Table  -->
					 <div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row">
								<div class="some-content-related-div">
											<div id="inner-content-div2">
											
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose" id="gohere">
								
										<table class="table table-bordered" cellspacing="0"
												width="100%">
											 <thead>
											 <tr class="tableback">
											 <th></th>
												<th>Problem (Step 2 Choose Problems)</th>
											 </tr>
											 </thead>
											 <tbody>
											 </tbody>
										</table>
									
							</div>
							</div>
							</div>
								</div>
						
							</div>
						</div>
						<!--lokesh  -->
					<div id="clinicalproblemtomasterdiv" class="hidden">
					<input type="text" name="clinicalnotestomaster" id="clinicalproblemtomaster" class="form-control" style="width:80%" placeholder="Add Clinical Problem">
					<button class="btn btn-primary" onclick="savetoclinicalproblemmaster()">Save</button>
					</div>	
					</div>
					
					<div class="col-lg-2 col-md-2" style="padding-right: 0px;">
						<!--Second Table  -->
						<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row">
									<div class="some-content-related-div">
										<div id="inner-content-div3">
											<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose" id="akashhere" >
												<table class="table table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr class="tableback">
														<th></th>
														<th>Intervention (Step 3 Choose Intervention)</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
							<!--lokesh  -->
					<div id="clinicalintervationtomasterdiv" class="hidden">
					<input type="text" name="clinicalnotestomaster" id="clinicalintervationtomaster" placeholder="Add Clinical Interventiom" class="form-control" style="width:60%">
					<button class="btn btn-primary" onclick="savetoclinicalintervationtomaster()">Save</button>
					</div>	
					</div>
					
					
					<div class="col-lg-2 col-md-2" style="padding-right: 0px;">
						<!--Second Table  -->
						<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row">
									<div class="some-content-related-div">
										<div class="lokdiv">
											<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
												<table class="table table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr class="tableback">
														<th></th>
														<th>Step 4</th>
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody>
												<%for(int jj=1;jj<=12;jj=jj+2){ %>
												
													<tr>
												
														<td><input type="checkbox" value="<%=jj %>"
																onclick="setdayweekmonth(this.value)" />
														</td>
														<td><%=jj %></td>
												
														<td><input type="checkbox" value="<%=jj+1 %>"
																onclick="setdayweekmonth(this.value)" />
														</td>
														<td><%=jj+1 %></td>
												
													</tr>
												
												<%} %>
													<tr>
														<td><input type="checkbox" value="day"
																onclick="setdayweekmonth(this.value)" />
														</td>
														<td>day</td>
													
														<td><input type="checkbox" value="weeks"
																onclick="setdayweekmonth(this.value)" />
														</td>
														<td>weeks</td>
													</tr>
													<tr>
														<td><input type="checkbox" value="months"
																onclick="setdayweekmonth(this.value)" />
														</td>
														<td>months</td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					
					</div>
					
					
					
					
					<div class="col-lg-4 col-md-4" style="padding-right: 0px;">
						<!--Second Table  -->
						<div class="panel panel-primary topbane6">
							<div class="panel-body">
								<div class="row">
									<div class="some-content-related-div">
										<div class='lokdiv'>
											<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
												<!-- EmR Code Here -->
												
											<!-- Side Div -->
												
												
												<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 aadedf">
				 
				  <div class="panel-group managewidhe" id="accordion" role="tablist" aria-multiselectable="true" >
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingOne">
						      <h4 class="panel-title">
						      <span>
						        <a class="templateformeditcolor" role="button" data-toggle="collapse" data-parent="#accordion" href="#editcon1" aria-expanded="true" aria-controls="editcon1">
						         Medicine
						        </a>
						       </span>
						        <%if (!loginInfo.getJobTitle().equals("Pathlab")) {%>
						      		 <a href="#"><i onclick="showpriscription()" title="Add Prescription" class="fa fa-plus-square fa-2x aadpres" style="margin-top: -12px;"></i></a>
						      	<% }%>
						      </h4>
						    </div>
						    <div id="editcon1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
						      <div class="panel-body">
						        <div id="editalldataprisctable" class="row rowblank" >
						        
						     
                                 </div>
						      </div>
						    </div>
						  </div>
						 
						
						 
						  
						  
						  
						
						  
						   <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingThree">
						      <h4 class="panel-title">
						      <span>
						        <a  class="collapsed templateformeditcolor" role="button" data-toggle="collapse" data-parent="#accordion" href="#editcon3" aria-expanded="false" aria-controls="editcon3">
						          Investigation
						        </a>
						       </span>
						       <%if (!loginInfo.getJobTitle().equals("Medical Store")) {%>
						        	<a href="#"><i onclick="showEditInvestigationPopup()" title="Add Investigation" class="fa fa-plus-square fa-2x aadpres" style="margin-top: -12px;"></i></a>
						        <%} %>
						      </h4>
						    </div>
						    <div id="editcon3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
						      <div class="panel-body">
						      		 <div id="alleditinvesttable" class="row rowblank" >
						      		 
						      		 </div>
						       
						      </div>
						    </div>
						  </div>
						  
						   <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingTwo">
						      <h4 class="panel-title">
						        <a class="collapsed templateformeditcolor" role="button" data-toggle="collapse" data-parent="#accordion" href="#editcon2" aria-expanded="false" aria-controls="editcon2">
						           Symbols <small style="color:#fff;">(Drag & Drop)</small>
						        </a>
						      </h4>
						    </div>
						    <div id="editcon2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						      <div class="panel-body" style="padding: 5px !important;">
						    <div class='form-inline'>
						      <div class='form-group'>
						         <img src="emr/img/downarow.png" name="image1" />
						      </div>  
						      <div class='form-group'> 
								 <img src="emr/img/leftarow.png" name="image2" />
								</div> 
								<div class='form-group'>
			 				 	<img src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
			 				 	</div>
			 				 	<div class='form-group'>
								 <img src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" />
								</div>
								<div class='form-group'> 
								  <img src="emr/img/linedownarow.png" name="image5" />
								</div>
								<div class='form-group'>  
								 <img src="emr/img/linedownarowright.png" name="image6" />
								</div>
								<div class='form-group'> 
								 <img src="emr/img/linedownbreakleft.png" name="image7" />
								</div> 
								 <img src="emr/img/linedownbreakright.png" name="image8" />
								 <img src="emr/img/lineupdownarow.png" name="image9" />
								 <img src="emr/img/rightarow.png" name="image10" />
								 <img src="emr/img/southpoll.png" name="image11" /> 
								 <img src="emr/img/uparow.png" name="image12" />
							</div> 
						      </div>
						    </div>
						  </div>
						  
						    <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingfour">
						      <h4 class="panel-title">
						        <a class="collapsed templateformeditcolor" role="button" data-toggle="collapse" data-parent="#accordion" href="#editcon4" aria-expanded="false" aria-controls="editcon3">
						           Templates & Forms   
						        </a>
						       
						       
						        <a href="#" type="button" class="btn btn-sm btn-primary templateformedit" style="margin-left: 5px;" data-toggle="modal" data-target="#editselectothertemlate"><i class="fa fa-align-justify" aria-hidden="true"></i></a>
						      </h4>
						    </div>
						    <div id="editcon4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfour">
						      <div class="panel-body">
						       		<s:if test="otherTemplateList.size>0">
						       			<span style="font-weight:bold">Template : </span>
						       			
						       			
						       			
						       			<% int ot=1;%>
						       			<table width="100%" id="edittemplatetableid">
						       				<s:iterator value="otherTemplateList">
						       					<tr>
						       						<td><%=ot %>.</td>
						       						 <td><a href="#" onclick="setselectedtemplatedata('<s:property value="title"/>','<s:property value="id"/>')"><s:property value="title"/></a></td> 
						       						
						       					</tr>
						       					<%ot++; %>
						       				</s:iterator>
						       			</table>
						       			
						       		</s:if>
						      </div>
						    </div>
						  </div>
						  
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingXXX">
						      <h4 class="panel-title">
						        <a class="collapsed " role="button" data-toggle="collapse" data-parent="<!-- #accordion -->" href="<!-- #editcon4 -->" aria-expanded="false" aria-controls="<!-- editcon3 -->">
						           	Dietary   
						        </a>
						       <a  href="#" data-toggle="modal" onclick="setdeitFromClinical()"><i class="fa fa-plus-square fa-2x aadpres" style="margin-top: -12px;"></i></a> 
						        
						     </h4>
						    </div>
						  </div>
						  
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingXXXVit">
						      <h4 class="panel-title">
						        <a class="collapsed " role="button" data-toggle="collapse" data-parent="<!-- #accordion -->" href="<!-- #editcon4 -->" aria-expanded="false" aria-controls="<!-- editcon3 -->">
						           	Vitals   
						        </a>
						       <a  href="#" data-toggle="modal" onclick="openVitalClinical()"><i class="fa fa-plus-square fa-2x aadpres" style="margin-top: -12px;"></i></a> 
						        
						     </h4>
						    </div>
						  </div>
						  
						   <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingXXXNur">
						      <h4 class="panel-title">
						        <a class="collapsed " role="button" data-toggle="collapse" data-parent="<!-- #accordion -->" href="<!-- #editcon4 -->" aria-expanded="false" aria-controls="<!-- editcon3 -->">
						           	Nursing Care   
						        </a>
						       <a  href="#" data-toggle="modal" onclick="opennursingPopup()"><i class="fa fa-plus-square fa-2x aadpres" style="margin-top: -12px;"></i></a> 
						        
						     </h4>
						    </div>
						  </div>
						  
						    </div>
						    </div>
						    
						    
						    
						  <div class="panel panel-default hidden">
						    <div class="panel-heading" role="tab" id="headingfive">
						      <h4 class="panel-title">
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#editcon5" aria-expanded="false" aria-controls="editcon5">
						           Discharge Client
						        </a>
						      </h4>
						    </div>
						    <div id="editcon5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfive">
						      <div class="panel-body">
						       		<div style="margin-bottom:5px !important;">
						       		<span>Outcome</s>
						       		<s:select cssClass="form-control" name="dischrgeOutcomes"
									id="eddischrgeOutcomes" list="dischargeOutcomeList"
									listKey="id" listValue="name" headerKey="0"
									headerValue="Select Outcomes" />
						       		</div>
						       		<div style="margin-bottom:5px !important;">
						       		<span>Status</span>
						       		<s:select cssClass="form-control" name="dischargeStatus"
									id="eddischargeStatus" list="dischargeStatusList" listKey="id"
									listValue="name" headerKey="0" headerValue="Select Status" />
						       		</div>
						       		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                   
                                     <div class="form-group">
	                                            
	                                            <div class="row">
	                                             <div class="col-lg-6 col-md-6 volvo">
	                                            <label for="exampleInputEmail1">Date</label>
	                                            <s:textfield cssClass="form-control" id="editdischargedate" name="dischargedate"/>
	                                            </div>
	                                            <div class="col-lg-3 col-md-3">
	                                            <label for="exampleInputEmail1">HH</label>
	                                            <s:select cssStyle="width:42px;" name="hour" id="edithour" list="hourList"
	                                            	cssClass="form-control"
	                                            	headerKey="0" headerValue="Select"></s:select>
	                                            </div>
	                                             <div class="col-lg-3 col-md-3">
	                                             <label for="exampleInputEmail1">MM</label>
	                                             <s:select cssStyle="width:42px;" name="minute" id="editminute" list="minuteList"
	                                            	cssClass="form-control"
	                                            	headerKey="0" headerValue="Select"/>
	                                            </div>
	                                            </div>
	                                            
	                                           
												
	                                             <%-- <s:textfield type="email" cssClass="form-control" id="admissiontime" name="admissiontime" /> --%>
	                                             
	                                        </div>
                                    
                                    
                                    	
	                                 </div>
						       		<div>
						       		
										<div class="col-lg-3 col-md-3 col-xs-3 ditrxas">
											<label>Discharge:</label>
										</div>
										<div class="col-lg-2 col-md-2 col-xs-2">
											<s:checkbox cssStyle="margin-right:110px;" theme="simple"
												name="chkDischarge" id="edchkDischarge" />
											<div id="addconbtnsdiv">
										</div>
										
										
										</div>
										<div class="col-lg-7 col-md-7 col-xs-7">
											<button onclick="editSaveOnlyConsultationNote()" type="button" class="btn btn-primary discharight"
									>Discharge</button>
												
											</div>
									
						       		</div>
						      </div>
						    </div>
						  </div>
						  
						  
						  <div style="margin-top: 5px;border: 2px solid #6699CC;">
							  <div id="paragraphs1" style="padding: 5px;">
							  <a href="#" class="btn btn-sm btn-success" style="float: right;background-color: #555;" onclick="savediagnosisfasteditEmrJSON()">Save</a>
							     	<div class="form-inline">
						  	            <input type="text" class="form-control" id="newdiagnosisedit" placeholder="Search diagnosis here" onkeyup="searchdiagnosiseditEmrJSON(this.value)" /> 
						  	        </div>
							  		<s:hidden name="odconditionstr" id="eodconditionstr" />
							  		<table class="table table-responsive" style="width: 100%;border:none;">
							  		
					                  <tbody id="conditiontbody2" style='height:275px;display:block;overflow:scroll;width:100% !important;'>
							              
					        			</tbody>
					   			 </table>
							  		
							  </div>
						  
 						</div>
						  
					</div>
				 
	 				
	 				 </div>  	
					
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					
					</div>
					
					
				</div>
				<s:hidden name="ipdid" id="ipdid"/>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12"  style="padding: 30px !important">
					<!-- <div  class="col-lg-3 col-md-3">
					 -->	<s:textarea name="maintextarea" id="maintextarea"  rows="50"></s:textarea>
					<!-- </div>
					 --><!-- <div class="col-lg-3 col-md-3"></div>
					<div class="col-lg-3 col-md-3"></div>
					<div class="col-lg-3 col-md-3"></div> -->
					</div>
						<div class="col-lg-12 col-md-12" style="margin-top: 0px;">
			<div class="col-lg-12 col-md-12" style="text-align: right;">
			<span id="saveastemplate" class="hidden"><input type ="text" name="templatename" id="templatename" required>
			<a class="btn btn-danger" onclick="addtoemrothertemplate(this)">Save </a></span>
				<span id="saveastemplatebtn"><a class="btn btn-danger" onclick="saveastemplateclincalfield()">Save as Template</a>&nbsp;&nbsp;&nbsp;</span>
				<a class="btn btn-primary" onclick="submitDataNew()">Insert into EMR</a>&nbsp;&nbsp;&nbsp;
				<a class="btn btn-primary" onclick="refreshData()"><i class="fa fa-refresh"></i></a>
			</div>
					</div>
					
				</div>
				<br>
		
			</div>
			</div>
										
											

											
										</div>
									</div>
								</div>
							</div>
						

<!-- add invesgtigation Modal -->
	 <div class="modal fade" id="investigationpopup" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.72);">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME: </b></h5>
				</div>
				<div class="modal-body" style="padding:0px;" >
						
						
					<%@ include file="/emr/pages/addInvestigation.jsp" %>
				    
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="insertInvestigation(0)">Save</button>
						
						<!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)">Save & Print</button> -->

					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- add prescription Modal -->
	 <div class="modal fade" id="priscriptionpopup" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.72);">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" style="margin-left:-100px;margin-right:-100px;">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Create Prescription For <b class="pname" id="priscmyModalLabel">NAME: </b></h5>
				</div>
				<div class="modal-body">
						
						
					<%-- <%@ include file="/diarymanagement/pages/addpriscription.jsp" %> --%>
				    <s:include value="/diarymanagement/pages/addpriscription.jsp"></s:include>
					
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary hidden"
						onclick="saveTemplae(0)">Save Template</button>
					<button type="button" class="btn btn-primary" id="prescs_save_btn" onclick="addEmrPrisc()">Save</button>
						 <!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button> -->

					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
		<!--NusrSing care Popup -->
	<div class="modal fade" id="nursingmod" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg setbig" style="width: 70%">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h3>Patient Name : <%=clientDetails.getFullname() %>
				<button  type="button" class="close" data-dismiss="modal"style="margin-top: -10px;">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button></h3>
				</div>
				
				<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 20px;padding-left: 30px;" >
				 <label>From Date :</label>
				 <input type="text" class='form-control picker' id='datenurse' style='width: 8%'  value="<%=date %>" readonly="readonly" placeholder="Select date"> 
									 &nbsp;
									
				 					  <label>HH : </label>
                                      <select class="form-control " id='nhh' style='width: 4%'>
                                      <%for(int i=0;i<24;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                     &nbsp;&nbsp;
                              		  <label>MM : </label>
                                      <select class="form-control " id='nmm' style='width: 4%'>
                                      <%for(int i=0;i<60;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
				 				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
								<label>To Date :</label>	
				 					 <input type="text" class='form-control picker' id='todatenurse' style='width: 8%'  value="<%=date %>" readonly="readonly" placeholder="Select date"> 
								
									
				 					  <label>HH : </label>
                                      <select class="form-control " id='tonhh' style='width: 4%'>
                                      <%for(int i=0;i<24;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                     &nbsp;&nbsp;
                              		  <label>MM : </label>
                                      <select class="form-control " id='tonmm' style='width: 4%'>
                                      <%for(int i=0;i<60;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>	
                                      
                                      
                                      <label style="padding-left: 2%">Given To :  </label>
                                      <select class="form-control " id='ngivento' style='width: 10%'>
                                      <option value="To Nurse">To Nurse</option>
                                      <option value="To Doctor">To Doctor</option>
                                      </select>	
                                     
				 						
				 
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 5px;padding-left: 20px;">
				  <label class='col-lg-1 col-md-1 col-xs-1 col-sm-1'>Repeat Care :  </label>
				 <div class='col-lg-2 col-md-2 col-xs-2 col-sm-2' id='rpeatdivajax11' style="width:20%"></div>
				 </div>
				 
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 15px;">
				  <table class="table" style="width: 100%">
                                  <thead>
                                      <tr><th class="thfont" >Category</th>
                                          <th class="thfont" >Taskname</th>
                                          <th class="thfont" >Frequency</th>
                                          <th class="thfont" >Duration</th>
                             			  <th class="thfont"></th> 
                                         </tr>
                                  </thead>
                                  <tbody>
                                  <tr>
                                  	<td >
                                             	<s:select onchange="setNursingtask(this.value)" cssClass="form-control showToolTip chosen-select" name="nursingtype_id" id="nursingtype_id" list="nursingcategorylist" listKey="id"  theme="simple"  listValue="name" headerKey="0" headerValue="Select Category"/>
                                    </td>
                                    <td id="nursingdiv" >
                                    <select class='form-control showToolTip chosen-select'>
                                    <option class=''>Select Task</option>
                                    </select>
                                    </td>
                                    <td>
                                          <s:select cssClass="form-control showToolTip chosen-select" name="priscdose" id="freq" list="dosageList" listKey="name" listValue="name" headerKey="0" headerValue="Select Frequency" theme="simple"/> 
                                     </td>
                                      <td >
                                     <select name="priscdays" id="nursingcdays" class="form-control follow">
                                             	<%for(int i=1;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                                             	<%} %>
                                      </select> 
                                     </td>
                                     <td >
                                           <a href="#" type="button" onclick="addtempnursing(1)" title="Add" class="btn btn-success"><i class="fa fa-plus"></i></a>
									</td>
                                  </tr>
                                  </tbody>    
                      </table>
				 </div>
				 
				  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 15px;">
				  <table class="table" style="width: 100%">
                                  <thead>
                                      <tr><th class="thfont" >Category</th>
                                          <th class="thfont" >Taskname</th>
                                          <th class="thfont" >Frequency</th>
                                          <th class="thfont" >Duration</th>
                             			  <th class="thfont">Delete</th> 
                                         </tr>
                                  </thead>
                                  <tbody>
                                  <tbody id="nursingtable">
                                  	
                                      

                                  </tbody>
                                  
                                  </tbody>
                  </table>
				 
				 </div>
				 
				</div>
				
				<div class="modal-footer" >
				<input type="button" class='btn btn-primary' value="Save Nursing" onclick="savenursing_by_ajax('<%=clientid%>','<%=ipdid%>','<%=bed.getPractitionerid()%>','<%=bed.getConditionid()%>')">
				</div>
				
			
		</div>
	</div>			
	</div>
	
	
	<!--Vital Popup -->
	<div class="modal fade" id="vitalmod" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-xs setbig" >
			<div class="modal-content">
				<div class="modal-header bg-primary">
				
				<h3>Patient Name : <%=clientDetails.getFullname() %>
				<button  type="button" class="close" data-dismiss="modal" style="margin-top: -10px;">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button></h3>
				</div>
				<div class="modal-body">
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 20px;padding-left: 30px;" >
				 <label>Select Date :</label>
				 <input type="text" class='form-control picker' id='datevital' style='width: 12%' readonly="readonly" placeholder="Select date"> 
									 &nbsp;&nbsp;
				 					  <label>Hours : </label>
                                      <select class="form-control " id='vhh' style='width: 7%'>
                                      <%for(int i=0;i<24;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                     &nbsp;&nbsp;
                              		  <label>Minutes : </label>
                                      <select class="form-control " id='vmm' style='width: 7%'>
                                      <%for(int i=0;i<60;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
				 
				 
				 </div>
				 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 30px;">
				 <table class='my-table loktable' style="width: 100%">
				 <tr><th>Vital</th><th>Value</th><th>Min-Max</th><th>Unit</th></tr>
				 <s:iterator value="vitalList">
					<tr>
					
					<td id="namev<s:property value='id'/>"><s:property value='name'/></td>
					
					<td><input type="text" class='form-control lokloop' style="width: 40%;height: 35px" id="<s:property value='id'/>" placeholder="Enter <s:property value='name'/> Value" ></td>
					<td><s:property value='min_value_m'/> - <s:property value='max_value_m'/></td>
					<td id="unitv<s:property value='id'/>"><s:property value='unit'/></td>
					</tr>				 
				 
				 </s:iterator>
				  </table>
				 </div>
				</div>
				<div class="modal-footer" >
				<input type="button" class='btn btn-primary' value="Save Vitals" onclick="saveclinicalnotesvitals('<%=clientid%>','<%=ipdid%>')">
				</div>
			</div>
		</div>
	</div>			
				
	
		 <div class="modal fade" id="dietmodal" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg setbig" style="width: 93%">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Create Diet Chart</h5>
				</div>
				<s:form action="saveDietPlanDietarydetails" id="saveForm"  theme="simple">
				<div class="modal-body">
					<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                      <div class="form-group">
									    <label for="exampleInputEmail1"> Patient</label>
									    	<%-- <s:select list="bedlist" name="patient_id" id="patient_id" headerKey="0" headerValue="Select Patients" 
									    	listKey="addmissionid" listValue="clientname" onchange="selectedbed(this.value)" cssClass="form-control chosen-select">
									    	</s:select> --%>
									    	<br><%=clientDetails.getFullname() %>
									    	<input type="hidden" id='patient_id'>
									  	</div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                    
                                       <div class="form-group">
                                          <b>Condition</b>
                                          <p id="dcondition"></p>
                                      </div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Ward / Bed</b>
                                         <div class="clearfix"></div> 
                                         <span id="ward"></span> / <span id="bedid"></span>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Age / Gender</b>
                                            <div class="clearfix"></div> 
                                          <span id="age"></span> / <span id="gender"></span>
                                           
                                       
                                      </div>
                                  </div>
                          </div>
                      </div>
                      <div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                            <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Consultant</b>
                                          <p id="consultant"></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                       <div class="form-group">
                                          <b>Nutrition Incharge</b>
                                          <p id="Nutrition_Incharge"></p>
                                      </div>
									  </div>
									  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
									  
                                       <div class="form-group " style="width: 60%">
                                          <b>Date / Time</b>
                                          <p id="datetime"></p>
                                          	<s:textfield readonly="true" name="fromDate" id="fromdate1"
											cssClass="form-control" theme="simple" placeholder="Date"></s:textfield>
                                      </div>
                                      <div class=" col-xs-4 col-sm-4" >
                                      <label>Hours</label>
                                      <select class="form-control " id='hh'>
                                      <%for(int i=0;i<24;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                      </div>
                                      <div class="col-xs-4 col-sm-4" >
                                      <label>Minutes</label>
                                      <select class="form-control " id='mm'>
                                      <%for(int i=0;i<60;i++){ %>
                                      <option value="<%=i%>"><%=i%></option>
                                      <%} %>
                                      </select>
                                      </div>
                                    
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                     
                                  </div>
                                 
                                  </div>
                          </div>
                      
                      <hr>
                      
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <br>
                             
                              
                              
                             
                              <div class="clearfix"></div>
                             
                              <div class="col-md-3 padingleferightzero">
                               <label>Select Category</label>
                           <s:select list="dietarycategoryList" theme="simple" name="category" id="category" headerKey="0" headerValue="Select Diet Category" listKey="id" listValue="name" onchange="selectCategoryDetails(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
                              </div>
                              <div class="col-md-3 padingleferightzero"></div>
                               <div class="col-md-3" id="tempdiv">
                             <%--  <s:textfield id="templatename" name="templatename" cssClass="templatenamediet" placeholder= " Template Name"></s:textfield>  --%>
                                                           <input type="button" class='btn btn-primary' value='Diet Master' onclick="openPopup('Dietary?selectedid=41')">
                              </div>
                               
                               <div class="col-md-3 padingleferightzero">
                            <s:select list="templatelist" name='template' id='template' listKey="id" listValue="templatename" cssClass='form-control showToolTip chosen-select' onchange='selectedTemplate(this.value)' headerKey="0" headerValue="Select Template" ></s:select>
                              </div>
                             <div class="divhightsixpx"> &nbsp; <br> </div>
                              <table class="table" cellspacing="0" width="100%">
                                  <thead>
                                      <tr>
                                          <th class="thfont">Day Plan</th>
                                         
                                          <th class="thfont">Diet</th>
                                          <th class="thfont">Feed</th>
                                           <th class="thfont">Qty</th>
                                           
                                            <th class="thfont">Sodium</th>
                                            <th class="thfont">Potassium</th>
                                            
                                           <th class="thfont" >Protein</th>
                                          <th class="thfont">Calories</th>
                                          <th class="thfont">Remark</th>
                                          <!-- <th class="thfont">Feed</th> -->
                                          <th class="thfont">Duration</th>
                                          <th class="thfont"></th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td style="width: 15%">
                                           
											<s:select list="dietplanlist" theme="simple" name="dietplan" id="dietplan" headerKey="0" headerValue="Select Day Plan" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
                                          </td>
                                          
                                          
     									  
                                          <td style="width: 10%" id="tdsubcat">
                                   		 <select name="subcategory" id="subcategory" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select SubCategory</option>                                     	
          									</select>    
          									                                   	
                                          </td>
                                          
                                          <td style="width: 12%">
                                           
											<s:select list="dietfeedlist" theme="simple" name="feed" id="feed" headerKey="0" headerValue="Select Feed" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
											
                                          </td>
                                          
                                          <td style="width: 5%" id=''><input type="number" id='dqty' min="1" value="1" class='form-control' onchange="afterchangeDietryQty()"></td>
                                          <td style="width: 5%" id='natd'><input type="number" id='nasodium' min="0" value="0" disabled="disabled" class='form-control' ></td>
                                          <td style="width: 5%" id='ktd'><input type="number" id='kpotassium' min="0" value="0" disabled="disabled" class='form-control' ></td>
                                          
                                          <td style="width: 5%" id="tdproo">    
                                          
          									<input  type="text" name="protein" id="protein" class="form-control"> 
                                          </td> 
                                          
                                          
                                          
                                          
                                          <td style="width: 5%" id="tdcal">    
                                          	<%-- <select name="calories" id="calories" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Calories</option>                                     	
          									</select> --%>
          									<input type="text" name="calories" id="calories" class="form-control"> 
                                          </td>                                                                        
                                          
                                    		<td style="width: 10%" id="tdproo"> <input type="text" class="form-control" id="dremark"></td> 
                                          
                                          <td style="width: 9%;">
                                          <div class="col-sm-6 col-xs-6 col-md-6" style="padding-left:0px;">
                                             <select name="duration" id="duration" class="form-control follow">
                                             		<option value="1">1</option>
                                             		<option value="2">2</option>
                                             		<option value="3">3</option>
                                             		<option value="4">4</option>
                                              </select>
                                          </div>
                                          
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                          		<p class="dayw">Days</p>
                                          </div>
                                          </td>
                                          <td style="width: 5%;" id="addtd">
                                           		<a href="#" type="button" onclick="addTempDiet(this)" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
										  </td>
                                      </tr>
                                  </tbody>
                              </table> 
                          </div>
                         <div class="slimScrollDiv" ><div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight scrolldiet" >
                              <table class="table table-bordered" cellspacing="0" width="100%" id="tabletrcount">
                                  <thead>
                                      <tr class="tableback">
                                      	  <th class="med8">Day Plan</th>
                                          <th class="med21">Diet Category</th>
                                          <th class="med9">Diet</th>
                                          <th class="med9">Feed</th>
                                          
                                          <th class="med9">Qty</th>
                                          <th class="med9">Sodium</th>
                                          <th class="med9">Potassium</th>
                                          
                                           <th class="med9">Protein</th>
                                          <th class="med9">Calories</th>
                                            <th class="med9">Remark</th>
                                          <!-- <th class="med9">Feed</th> -->
                                          <th class="med30">Duration</th>
                                          <th>Delete</th>
                                      </tr>
                                  </thead>
                                 
                                  <tbody id="diettable"></tbody>
                                  <input type="hidden" id="tcount">
                              </table>

                          </div>
                          <div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 190px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10">
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10">
                                  <form>
                                      <div class="form-group hidden">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language1" value="English" checked=""> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language2" value="Regional"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language3" value="Hindi"> Hindi
                                              </label>
                                          </div>
                                          
                                          
                                      </div>

                                  </form>
                                  <form class="form-horizontal hidden">
                                   
                                      <textarea name="dietadvoice" id="dietadvoice" class="form-control setyerx" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                              
                          </div>
                      </div>
				</div>
				<div id="frreload"></div>
				<div class="modal-footer" id="savebtndiv">
					<!-- <button type="button" class="btn btn-primary" onclick="saveTemplate()">Save Template</button> -->
					<!-- <button type="button" class="btn btn-primary" onclick="addTemplate()">Save Template</button> -->
					<button type="button" class="btn btn-primary" onclick="savedietaryInfoAjax()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
				</s:form>
			</div>
		</div>
	</div>
	
	
	 <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
	
	
		
	<!-- /MAIN CONTENT -->
	<!--main content end-->
	<script type="text/javascript" src="common/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script> <script
	src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript"
	charset="utf-8"></script>
	<script>
	$(function(){
	    $('#inner-content-div').slimScroll({
	        height: '300px',
	        railVisible: true,
			alwaysVisible: true
	    });
	    $('#inner-content-div2').slimScroll({
	         height: '300px',
	         railVisible: true,
			 alwaysVisible: true
	    });
	    $('#inner-content-div3').slimScroll({
	         height: '300px',
	         railVisible: true,
			 alwaysVisible: true
	    });
	    $('.lokdiv').slimScroll({
	         height: '300px',
	         railVisible: true,
			 alwaysVisible: true
	    });
	});
	</script>
	  <script>
            $(window).load(function(){

                $('#select-all').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select input').prop('checked', false);
                    }
                });
                $('#select-all1').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select1 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select1 input').prop('checked', false);
                    }
                });
                $('#select-all2').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select2 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select2 input').prop('checked', false);
                    }
                });
                $('#select-all3').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select3 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select3 input').prop('checked', false);
                    }
                });

            });
            
            
            function addtoclinicalnotesmaster(){
            	document.getElementById("clinicalnotestomasterdiv").className="";
            }
            
            function addtoproblemmaster(){
            	document.getElementById("clinicalproblemtomasterdiv").className="";
            }
            function addtoclinicalintervationmaster(){
            	document.getElementById("clinicalintervationtomasterdiv").className="";
            }
            function saveastemplateclincalfield(){
            	document.getElementById("saveastemplate").className="";
            	document.getElementById("saveastemplatebtn").className="hidden";
            }
        </script>
	
</body>
</html>
