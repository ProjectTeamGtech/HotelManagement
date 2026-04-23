<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<style>
#detailscard{
margin-top: 0px;
background-color: rgba(91, 192, 222, 0.16);
min-height: 0px;
display: block;
border: 1px solid #6699cc;
}

#lablesdiv{
margin-top: 10px;
}

#lableleft{

}

#lablelright{

}

td{

border: 1px solid #dfd8d4 !important;
}
tr{
border: 1px solid #dfd8d4 !important;
text-align: center;
height: 40px; !important;
}

h3{
text-align: center;
color: red;
margin-left: -50px !important;
}

.declare{
color: red;
 text-decoration: underline;
}

@media print {
#detailscard{
margin-top: 10px;
margin-right:-40px !important;
background-color: rgba(91, 192, 222, 0.16) !important;


}

#printth{
border:1px solid !important;
background-color:rgba(91, 192, 222, 0.16) !important;
color: white !important;

}
h3{
color: red !important;

}

}
</style>




<div class="print-visible hidden-md hidden-lg  letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>

	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id='detailscard'>
	<h3>Blood Requisition Form</h3>	
		<div class="" id='lablesdiv'>
		
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" id='lableleft' align="left">
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Name</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: <s:property value='clientname'/>
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Age/Gender</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: <s:property value="agegender"/>
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Ward/Bed</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: <s:property value="wardbed"/>
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Address</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: <s:property value="address"/>
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Transfusion Indication</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: Indicator
		</div>
		</div>
		
		
		</div>
		
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" id='lableright' align="left">
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Blood Group</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: A+
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>IPD ID</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: 1234
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Diagnosis</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: Mela Hota
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Hb %</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: 50 %
		</div>
		</div>
		
		<div class='row'>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<label>Date/Time</label>
		</div>
		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
		: 12-01-2018 / 10:00:05
		</div>
		</div>
		
		
		</div>
		
		</div>
	</div>
	
	<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="margin-top: 20px;">
		<table class="my-table newlok" style="width:100%">
			<tr bgcolor="#3c6ea0" style="color:white;" id='printth' >
			<td style="width: 5% !important">SN</td>
			<td>Blood / Component Required</td>
			<td>ID-NAT Tested</td>
			<td>Leuco Depleted</td>
			<td>No. of Units</td>
			<td>Issue Stat </td>
			<td>Cross Match Ready / Group Reverse: Date-Time</td>
			</tr>
			<tr>
			<td>1</td>
			<td>Red Cell Concentrate / RCC</td>
			<td>Yes</td>
			<td>LD/ LR/ Plan</td>
			<td>No. of Units</td>
			<td>Issue Stat</td>
			<td>Cross Match Ready </td>
			</tr>
		</table>							
	</div>	
	<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="margin-top: 20px;">
	<p>
	<label class='declare'>INFORMED CONSENT</label> 
	<span>Blood products have the potential to save lives, and frequently provide considerable benefit to patients when used appropriately. There are significant risks associated with transfusion and the decision to transfuse a patient should be a carefully considered decision and involve a discussion with the patient/parent, with an opportunity for the patient/parent to ask any questions. 
	The following should be part of the consent discussion</span>
	</p>
	<p>
	<label class='declare'>ID-NAT Tested Blood</label> 
	<span>Blood products have the potential to save lives, and frequently provide considerable benefit to patients when used appropriately. There are significant risks associated with transfusion and the decision to transfuse a patient should be a carefully considered decision and involve a discussion with the patient/parent, with an opportunity for the patient/parent to ask any questions. 
	The following should be part of the consent discussionBlood products have the potential to save lives, and frequently provide considerable benefit to patients when used appropriately. There are significant risks associated with transfusion and the decision to transfuse a patient should be a carefully considered decision and involve a discussion with the patient/parent, with an opportunity for the patient/parent to ask any questions. 
	The following should be part of the consent discussionBlood products have the potential to save lives, and frequently provide considerable benefit to patients when used appropriately. There are significant risks associated with transfusion and the decision to transfuse a patient should be a carefully considered decision and involve a discussion with the patient/parent, with an opportunity for the patient/parent to ask any questions. 
	The following should be part of the consent discussion</span>
	</p>
	<p>
	<label class='declare'>Leuco Depleted Blood</label> 
	<span>Blood products have the potential to save lives, and frequently provide considerable benefit to patients when used appropriately. There are significant risks associated with transfusion and the decision to transfuse a patient should be a carefully considered decision and involve a discussion with the patient/parent, with an opportunity for the patient/parent to ask any questions. 
	The following should be part of the consent discussion</span>
	</p>
	</div>
	
	<div class='' style="margin-top: 30px;">
	
	<div class='col-lg-4 col-md-4 col-xs-4 col-sm-4'  >
	<h4 align="center"><b>Hospital</b></h4><br>
	<label>Name :</label><span></span><br>
	<label>Phone No :</label><span></span><br>
	<label>Hospital Stamp :</label><span></span>
	</div>
	
	<div class='col-lg-4 col-md-4 col-xs-4 col-sm-4' >
	<h4 align="center"><b>Consultant Doctor </b></h4><br>
	<label>Sign :</label><span></span><br>
	<label>Name :</label><span></span><br>
	<label>Mobile No. :</label><span></span>
	</div>
	
	<div class='col-lg-4 col-md-4 col-xs-4 col-sm-4' >
	<h4 align="center"><b>Phlebotomist (MO/RMO/Nurse)</b></h4><br>
	<label>Sign :</label><span></span><br>
	<label>Name :</label><span></span><br>
	<label>Mobile No. :</label><span></span>
	</div>
	
	</div>

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print' style="margin-top: 20px;text-align: right;">
	<a class='btn btn-success' style="width: 10%;height: 10%;" onclick="printpage()">Print <i class="fa fa-file-excel-o"></i></a>
</div>
