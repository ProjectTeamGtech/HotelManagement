<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.table>thead:first-child>tr:first-child>th{
	text-align: center;
    width: 3%;
}
.backkpi{
	background-color: rgba(255, 248, 220, 0.55) !important;
}
.tdpadnil{
	padding: 1px 1px 1px 1px !important;
}
  .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 1px 1px 1px 1px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px;
}
.savebigbtn {
    background-color: #339966 !important;
}
</style>

<script type="text/javascript">

     window.onload= function(){
             
    	 $("#date").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange: yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});
     };
     
     function goDate(){
    	 
    	 document.getElementById("vitalform").action="vitalstatisticsIpd"; 
    	 document.getElementById("vitalform").submit();
     }
     
    
</script>

</head>
<body>
<s:form theme="simple" action="savevitalsIpd" id="vitalform" >
<div class="row details hidden-print">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<h4>Vital Statistics</h4>
		</div>
	</div>
	
	<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			
		</div>
		<div class="" style="height: 135px;">
		<div class="col-lg-12">
		   
		
			<div class="form-inline hidden-print">
				<div class="form-group" style="width:7%;">
					<s:textfield type="text" name="date" id="date" cssClass="form-control" cssStyle="width:100%;"  />
				</div>
				<div class="form-group" style="width:7%;">
				   <button class="btn btn-primary" onclick="goDate()"  >GO</button>
				</div>
				
				<div class="">
					 <input type="button" class="btn btn-primary savebtn savebigbtn" value="Print" onclick="printpage()" style="float: right;margin-top: -25px;">
				</div>
			</div>
		</div>
</div>

<div class="">

      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="background-color: rgba(91, 192, 222, 0.16);border-bottom: 2px solid #ddd;">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-mg visible-print" style="padding-left:0px;padding-right:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors" style="border-bottom: 4px double #ddd;font-size: 16px;color: firebrick;">Vital Statistics</b>
								<s:hidden name="clientid"></s:hidden>
								<s:hidden name="ipdid"></s:hidden>
						</div>
					</div>
					</div>
				</div>
						<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
							<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding: 0px;">
								<label>UHID: <span style="font-weight: normal;"><s:property value="abrivationid"/></span></label> | <label>Admission Date: <span style="font-weight: normal;"><s:property value="admissiondate"/></span></label>
							</div>
							<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="padding: 0px;">
								<label>Date: <span style="font-weight: normal;"><s:property value="curdatetime"/></span></label>
							</div>
							
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
							<label>Patient Details: <span style="font-weight: normal;"><s:property value="client"/> | <s:property value="agegender"/> | <s:property value="wardname"/></span></label>
						</div>
					</div>
      </div>
      
      <div class="row">
      		<div class="col-lg-12 col-md-12 col-xs-12">
      			<table class="table table-striped gridexample table-responsive">
      				<thead>
      					<tr>
      					   
      						<th style="width:1%;"></th>
      						<s:iterator value="timeList">
      							<th>
      							     <s:property />
      							</th>
      						</s:iterator>
      					</tr>
      				</thead>
      				<tbody>
      				   <s:iterator value="vitalMasterList">
      					<tr>
      					         <td><s:property value="name" /></td>
      					          <s:iterator value="vitalDataList">
      					             	<td><input type="text" class="form-control" name="<s:property value="keyname"/>" value="<s:property value="finding"/>" /></td>
      					          </s:iterator>
      					</tr>
      					</s:iterator>
      					<!-- <tr>
      						<td>HR</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>RR</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>BP</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>CVP...Hrly</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>MODE</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>Fio2</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>TV-I/E</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>PEEP</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>RR-I/E</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>SpO2</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>GCS</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>PRL-Rt</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr>
      					<tr>
      						<td>PRL-Lt</td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      						<td><input type="text" class="form-control"></td>
      					</tr> -->
      				</tbody>
      			</table>
      		</div>
      	</div>
      	<div class="row">
      		<div class="col-lg-12 col-xs-12 col-md-12">
      			
      		</div>
      	</div>
      	<div class="row hidden">
      		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left">
      			<label>Write Note</label>
      			<textarea rows="3" cols="3" class="form-control" style="background-color: rgba(245, 245, 220, 0.31);"></textarea>
		        
		    </div>
      		<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6 text-right" style="padding-right: 25px;">
      			<s:submit cssClass="btn btn-primary hidden-print" value="Save" /><br><br>
      			<%-- <span>Printed By: demo 07-10-2017 13:19:23</span> --%>
      		</div>
      	</div>
      	</s:form>
      	
      	
      	
   <script>
   /*!
   * based on formNavigation https://github.com/omichelsen/FormNavigation
   */
   (function ($) {
     $.fn.formNavigation = function () {
       $(this).each(function () {
         // Events triggered on keyup
         $(this).find('input').on('keyup', function(e) {
           switch (e.which) {
             // arrow right
             case 39:
               $(this).closest('td').next().find('input').focus();
               break;

             // arrow left
             case 37:
               $(this).closest('td').prev().find('input').focus();
               break;

             // arrow bottom
             case 40:
               $(this).closest('tr').next().children().eq($(this).closest('td').index()).find('input').focus();
               break;

             // arrow top
             case 38:
               $(this).closest('tr').prev().children().eq($(this).closest('td').index()).find('input').focus();
               break;

             // enter
             case 13:
               if ($(this).closest('td').next().find('input').length>0) {
                 // when there is another column on right
                 $(this).closest('td').next().find('input').focus();
               } else {
                 // when last column reached
                 $(this).closest('tr').next().children().eq(1).find('input').focus();
               }
               break;
           }
         });
         
         // Events triggered on keydown (repeatable when holding the key)
         $(this).find('input').on('keydown', function(e) {
           // Vertical navigation using tab as OP wanted
           if (e.which === 9 && !e.shiftKey) {
             // navigate forward
             if ($(this).closest('tr').next().find('input').length>0) {
               // when there is another row below
               e.preventDefault();
               $(this).closest('tr').next().children().eq($(this).closest('td').index()).find('input').focus();
             } else if ($(this).closest('tbody').find('tr:first').children().eq($(this).closest('td').index()+1).find('input').length>0) {
               // when last row reached
               e.preventDefault();
               $(this).closest('tbody').find('tr:first').children().eq($(this).closest('td').index()+1).find('input').focus();
             }
           } else if (e.which === 9 && e.shiftKey) {
             // navigate backward
             if ($(this).closest('tr').prev().find('input').length>0) {
               // when there is another row above
               e.preventDefault();
               $(this).closest('tr').prev().children().eq($(this).closest('td').index()).find('input').focus();
             } else if ($(this).closest('tbody').find('tr:last').children().eq($(this).closest('td').index()-1).find('input').length>0) {
               // when first row reached
               e.preventDefault();
               $(this).closest('tbody').find('tr:last').children().eq($(this).closest('td').index()-1).find('input').focus();
             }
           }
         });
       });
     };
   })(jQuery);

   // usage
   $('.gridexample').formNavigation();
   </script>

</body>
</html>