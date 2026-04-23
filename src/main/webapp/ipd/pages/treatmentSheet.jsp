<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta name="description" content="">
<meta name="author" content="Dashboard">


<link rel="icon" href="assets/img/favicon.ico">
<title>YUVICARE</title>
<!-- Bootstrap core CSS -->

  <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">   
   <script type="text/javascript" src="ipd/js/addcharge.js"></script>


    <style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
}
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #339966;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 0px !important;
}

p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px;
    border-right: none !important;
    border-left: none !important;
}

 @media print
{
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 0px !important;
	    font-size: 8px !important;
	}
	.form-group {
    margin-bottom: 0px !important;
}
.setuseanme {
    color: #9a9a9a !important;
    margin-bottom: 0px !important;
    font-size: 8px !important;
    margin-top: -5px !important;
}

.titleset {
    color: blank !important;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    font-weight: bold !important;
    line-height: 20px;
    padding: 2px 0px 0px 6px !important;
    background-color: #efefef !important;
    -webkit-print-color-adjust: exact !important; 
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th {
    font-size: 8px !important;
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
}
.table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
	font-size: 8px !important;
}

}
    </style>
    <style>

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 135px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
    background-color: #efefef;
     
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
    
}
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.form-group {
    margin-top: 0px;
}
.setuseanme{
	color: #9a9a9a;
    margin-bottom: 0px !important;
    font-size: 9px;
    margin-top: -5px !important;
}

h5, .h5 {
    font-size: 12px;
    margin-bottom: 2px;
    font-weight: bold;
    color: brown;
}
</style>


<script>


$(document).ready(function() {

	$("#today").datepicker({

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

	
	
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
		<div class="hidden letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			
		</div>
	</div>
	
	
	<div class="">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">DAILY TREATMENT RECORD</b>
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group hidden" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
							
						</div>
						
					</div>
					</div>
				</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Ward / Bed</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
							<s:if test="contact!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
							</s:if>
							
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="abrivationid"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="client"/></span>
							</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="bedid"/></span> 
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="address"/></span>
						</div>
							<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: <s:property value="contact"/></span>
								</div>
							</s:if>
							
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Age / Gender</b>  
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Height / Weight</b>  
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Blood Group</b>  
							</div>
						
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''"><span>: <s:property value="agegender" /></span></s:if>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="height"/> / <s:property value="weight"/></span>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="bloodgroup"/></span>
							</div>
						</div>
					</div>
					</div>
				</div>
				
				
				<div class="col-lg-12 col-md-12 topback2 hidden-print">
										<div class="form-inline">
										   <s:form theme="simple" action="treatmentsheetIpdDashboard">
										     <s:hidden name="clientid"></s:hidden>
										     <s:hidden name="ipdid"></s:hidden>
											<div class="form-group">
										    <%-- <select name="filter" class="form-control chosen-select">
										    	<option value="All" >Select All</option>
										    	<option value="Medicine">Medicine</option>
										    	<option value="Investigation">Investigation</option>
										    	<option value="Nursing">Nursing Care</option>
										    	<option value="Dietary">Dietary</option>
										    	<option value="Consultant">Consultant Visited</option>
										    	<option value="Blood">Blood Bank</option>
										    	<option value="Transfer">Transfer Advice</option>
										    	<option value="Vitals">Vitals</option>
										    	<option value="Equipment">Equipment Used</option>
										    	<option value="Catheter">Catheter & Tube Care</option>
										    </select> --%>
										   <s:select name="filter" theme="simple" cssClass="form-control chosen-select" list="#{'All':'Select All','Medicine':'Medicine','Investigation':'Investigation Care','Nursing':'Nursing Care','Dietary':'Dietary','Consultant':'Consultant Visited','Blood':'Blood Bank','Transfer':'Transfer Advice','Vitals':'Vitals','Equipment':'Equipment Used','Catheter':'Catheter & Tube Care','I/O':'Intake/Output','IV':'IV','Ventilator':'Ventilator','Nursing Care Plan':'Nursing Care Plan'}"></s:select> 
										    
										    
										  </div>
										  <div class="form-group" style="width:7%;">
										    <s:textfield id="today" name="date" cssClass="form-control" cssStyle="color:black;width: 100%;" onchange="getfromdaysSheet(this.value)" />
										  </div>
										 
										  <button type="submit" class="btn btn-primary">Go</button>
										  <a href="#" onclick="refreshData()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
										  
										  <a href="#" id="button" class="hidden-print btn btn-warning" style="float: right;" onclick="printpage()">Print</a>
										  </s:form>
										</div>
										</div>
										
										
										<s:if test="filter=='Medicine' || filter=='All'">
										<!-- Medicine design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
												<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Medicine Care <a href="#" id="button" class="hidden-print btn btn-warning" style="float: right;" onclick="printpage()">Print</a></h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 17%;">Medicine Name</th>
															<th style="width: 5%;" class="text-center">08:00</th>
															<th style="width: 5%;" class="text-center">09:00</th>
															<th style="width: 5%;" class="text-center">10:00</th>
															<th style="width: 5%;" class="text-center">12:00</th>
															<th style="width: 5%;" class="text-center">14:00</th>
															<th style="width: 5%;" class="text-center">16:00</th>
															<th style="width: 5%;" class="text-center">18:00</th>
															<th style="width: 5%;" class="text-center">20:00</th>
															<th style="width: 5%;" class="text-center">21:00</th>
															<th style="width: 5%;" class="text-center">22:00</th>
															<th style="width: 28%;"> Notes</th>
														</tr>
													</thead>
													<tbody>
													
													<s:iterator value="listMedicineLog">
														<tr>
															<td><s:property value="mdicinenametxt" /></td>
															<s:if test="dosesize==3"> 
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime1"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime2"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime3"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															</s:if>
															<s:if test="dosesize==4">
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime1"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime2"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime3"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime4"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															</s:if>
															
															<td><s:property value="dosenotes"/></td>
														</tr>
													</s:iterator>
													</tbody>
												</table>
											</div>
											</s:if>
											
											
											<s:if test="filter=='Investigation' || filter=='All'">
											<!-- Investigation design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Investigation</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
												<thead>
				                                    <tr>
				                                    	<th style="width: 29%;">Test Name</th>
														<th style="width: 15%;">RESULT/FINDING</th>
														<th>REFERENCE RANGE</th>
				                                    </tr>
				                                </thead>
				                                <tbody>
				                                
				                                           <s:iterator value="investigationList">
					                                    	<tr> 
					                                    		<td><span style="background-color: #fff !important;color: #ea6a4d;font-weight: bold;padding: 0px !important;"><s:property value="name"/> </span> </td> 
					                                    		   <td></td>
					                                    		<td></td>
					                                    	</tr>
					                                    	<s:iterator value="investiNames">
					                                    	<tr> 
					                                    		<td><s:property value="name"/></td> 
					                                    		   <td><s:property value="finding"/> <s:property value="unit"/> </td>
					                                    		<td> <s:property value="reference"/> </td>
					                                    	</tr>
					                                    	</s:iterator>
					                                    	</s:iterator>
					                                    	
					                                    	<!-- <tr> 
					                                    		<td>CREATININE</td> 
					                                    		<td style="font-weight: bold;color: red;"><u>0.54 mg/dl</u></td>
					                                    		<td>0.7-1.3</td>
					                                    	</tr>
					                                    	<tr> 
					                                    		<td>URIC ACID</td> 
					                                    		<td>2.28 mg/dl</td>
					                                    		<td>Male 3.5-7.2, Female 2.6-6.0</td>
					                                    	</tr>
					                                    	<tr> 
					                                    		<td>SODIUM</td> 
					                                    		<td style="font-weight: bold;color: red;"><u>134.0 mmol/L</u></td>
					                                    		<td>135-150</td>
					                                    	</tr>
					                                    	<tr> 
					                                    		<td>POTASIUM</td> 
					                                    		<td style="font-weight: bold;color: red;"><u>3.20 mmol/L</u></td>
					                                    		<td>3.5-5.3</td>
					                                    	</tr>
					                                    	<tr> 
					                                    		<td>CHLORIDE</td> 
					                                    		<td>96.0 mmol/L</td>
					                                    		<td>95 - 110</td>
					                                    	</tr> -->
				                                    
				                                </tbody>
				                            </table>
											</div>
											</s:if>
											
											
											<s:if test="filter=='Nursing' || filter=='All'">
											<!-- Nuesing Care design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Nursing Care</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 17%;">Nursing Care</th>
															<th style="width: 5%;" class="text-center">08:00</th>
															<th style="width: 5%;" class="text-center">09:00</th>
															<th style="width: 5%;" class="text-center">10:00</th>
															<th style="width: 5%;" class="text-center">12:00</th>
															<th style="width: 5%;" class="text-center">14:00</th>
															<th style="width: 5%;" class="text-center">16:00</th>
															<th style="width: 5%;" class="text-center">18:00</th>
															<th style="width: 5%;" class="text-center">20:00</th>
															<th style="width: 5%;" class="text-center">21:00</th>
															<th style="width: 5%;" class="text-center">22:00</th>
															<th style="width: 28%;"> Notes</th>
														</tr>
													</thead>
													<tbody>
													   
													   <s:iterator value="listNursingLog">
														<tr>
															<td><s:property value="taskname"/></td>
															<s:if test="dosesize==3"> 
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime1"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime2"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															<td class="text-center">-</td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime3"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															<td class="text-center">-</td>
															</s:if>
															<s:if test="dosesize==4">
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime1"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime2"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime3"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															  <td class="text-center">-</td>
															  <td class="text-center">-</td>
															  <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="dosetime4"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
															</s:if>
															<td><s:property value="notes"/></td>
														</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
											</s:if>
										
										
										 <s:if test="filter=='All' || filter=='Dietary'">
										<!-- Dietary Care design By Abhi 24oct2017-->
										<!-- Dietary Care data set By   10Nov2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Dietary Care</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 10%;">Diet</th>
															<th style="width: 5%;" class="text-center">Morning</th>
															<th style="width: 5%;" class="text-center">Breakfast</th>
															<th style="width: 5%;" class="text-center">Mid Morning</th>
															<th style="width: 5%;" class="text-center">Lunch</th>
															<th style="width: 5%;" class="text-center">After Lunch</th>
															<th style="width: 5%;" class="text-center">Tea Time</th>
															<th style="width: 5%;" class="text-center">Evening Snacks</th>
															<th style="width: 5%;" class="text-center">Dinner</th>
															<th style="width: 5%;" class="text-center">Bedtime</th>
														</tr>
													</thead>
													<tbody>
														
															<s:iterator value="dietarydatalist">
															<tr>
															<td><s:property value="subcategory"/></td>
																<s:iterator value="dietgivenlist">
																	<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="datetime"/></p><p class="setuseanme"><s:property value="userid"/></p></td>
																</s:iterator>
															</tr>
															</s:iterator>
															<!-- <td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p><p class="setuseanme">Arun123</p></td> -->
														
													</tbody>
												</table>
											</div>
											</s:if>
											
											
											<s:if test="filter=='All' || filter=='Consultant'">
											<!-- Consultant Visited design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Consultant Visited</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Consultant Visited</th>
															<th style="width: 5%;" class="text-center">Time</th>
															<th style="width: 5%;" class="text-center">Fees</th>
															<th style="width: 5%;" class="text-center">Status</th>
															<th style="width: 5%;" class="text-center">Payment</th>
														</tr>
													</thead>
													<tbody>
													 <s:iterator value="visitingConsultList">
														<tr>
															<td><s:property value="practitionername"/></td>
															<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="time"/></p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">Rs <s:property value="fees"/></p></td>
															<s:if test="status==1">
															  <td class="text-center"><p style="margin-bottom:0px !important;">Visited</p><p class="setuseanme"></p></td>
															</s:if>
															<s:else>
															    <td class="text-center"><p style="margin-bottom:0px !important;">Not Visited</p><p class="setuseanme"></p></td>
															</s:else>
															<s:if test="payment==1">
																	<td class="text-center"><p style="margin-bottom:0px !important;">Paid</p><p class="setuseanme"></p></td>
															</s:if>
															<s:else>
																	<td class="text-center"><p style="margin-bottom:0px !important;">Not Paid</p><p class="setuseanme"></p></td>
															</s:else>
															
														</tr>
														</s:iterator>
														<!-- <tr>
															<td>Dr. BINOD AGRAWAL</td>
															<td class="text-center"><p style="margin-bottom:0px !important;">01:21</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">Rs.500</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">Planned</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">Unpaid</p></td>
														</tr> -->
													</tbody>
												</table>
											</div>
											</s:if>
											
											<s:if test="filter=='All' || filter=='Blood'">
											<!-- Blood Bank design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Blood Bank</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Request Time</th>
															<th style="width: 5%;">Blood Group</th>
															<th style="width: 5%;" class="text-center">Unit</th>
															<th style="width: 5%;" class="text-center">Allotted</th>
															<th style="width: 5%;" class="text-center">Allotted Time</th>
														</tr>
													</thead>
													<tbody>
													  <s:iterator value="bloodRequestedList">
														<tr>
															<td><p style="margin-bottom:0px !important;"><s:property value="time"/></p><p class="setuseanme"><s:property value="userid" /></p></td>
															<td><s:property value="blood_group"/></td>
															<td class="text-center"><s:property value="reqqty"/> Units</td>
															<s:if test="given==0">
															  	<td class="text-center">Not Alloted</td>
															</s:if>
															<s:else>
															    <td class="text-center"><s:property value="reqqty"/> Units</td>
															</s:else>
															<s:if test="given==0">
															   <td class="text-center">-</td>
															</s:if>
															<s:else>
																<td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="time"/></p><p class="setuseanme"></p></td>
															</s:else>
														</tr>
													  </s:iterator>
													</tbody>
												</table>
											</div>
											</s:if>
											
											<s:if test="filter=='All' || filter=='Transfer'">
											<!-- Transfer Advice design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Transfer Advice</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Ward / Bed</th>
															<th style="width: 5%;" class="text-center">Date / Time</th>
															<th style="width: 5%;" class="text-center">From Time</th>
															<th style="width: 5%;" class="text-center">To Time</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><p style="margin-bottom:0px !important;">1F-CASUALTY 134/EME02</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center">25-10-2017 03:39</td>
															<td class="text-center">03:39</td>
															<td class="text-center">03:39</td>
														</tr>
													</tbody>
												</table>
											</div>
											</s:if>
											
											
											<s:if test="filter=='All' || filter=='Vitals'">
											<!-- Vitals design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Vitals</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Vitals</th>
															  <s:iterator value="timeList">
															 	<th style="width: 5%;" class="text-center"> <s:property /> </th> 
															  </s:iterator>
															 
														
															
														</tr>
													</thead>
													<tbody>
													     <s:iterator value="vitalMasterList">
      													<tr>
								      					         <td><s:property value="name" /> (<s:property value="unit" />)</td>
								      					          <s:iterator value="vitalDataList">
								      					                 <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="finding"/></p><p class="setuseanme"><s:property value="userid"/></p></td> 
								      					          </s:iterator>
								      					</tr>
								      					</s:iterator>
														<!-- <tr>
															<td>CVP...Hrly</td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
														</tr> -->
													</tbody>
												</table>
											</div>
											</s:if>
	
	
	</div>
	
	
	
		<s:if test="filter=='All' || filter=='I/O'">
											<!-- Vitals design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Intake/Output</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Input/Output</th>
															  <s:iterator value="iotimeList">
															 	<th style="width: 5%;" class="text-center"> <s:property /> </th> 
															  </s:iterator>
															 
														
															
														</tr>
													</thead>
													<tbody>
													     <s:iterator value="vitalMasterIOList">
      													<tr>
								      					         <td><s:property value="name" /> (<s:property value="unit" />)</td>
								      					          <s:iterator value="vitalDataList">
								      					                 <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="finding"/></p><p class="setuseanme"><s:property value="userid"/></p></td> 
								      					          </s:iterator>
								      					</tr>
								      					</s:iterator>
														<!-- <tr>
															<td>CVP...Hrly</td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
														</tr> -->
													</tbody>
												</table>
											</div>
											</s:if>
	
	
 										<s:if test="filter=='All' || filter=='IV'">
											<!-- Vitals design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> IV</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Name</th>
															  <s:iterator value="ivtimeList">
															 	<th style="width: 5%;" class="text-center"> <s:property /> </th> 
															  </s:iterator>
															 
														
															
														</tr>
													</thead>
													<tbody>
													     <s:iterator value="vitalMasterIVList">
      													<tr>
								      					         <td><s:property value="name" /> (<s:property value="unit" />)</td>
								      					          <s:iterator value="vitalDataList">
								      					                 <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="finding"/></p><p class="setuseanme"><s:property value="userid"/></p></td> 
								      					          </s:iterator>
								      					</tr>
								      					</s:iterator>
														<!-- <tr>
															<td>CVP...Hrly</td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
														</tr> -->
													</tbody>
												</table>
											</div>
											</s:if>	
	
	
										<s:if test="filter=='All' || filter=='Ventilator'">
											<!-- Vitals design By Abhi 24oct2017-->
											<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
											<h5><i class="fa fa-caret-right" aria-hidden="true"></i> Equipment</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
														<tr>
															<th style="width: 5%;">Name</th>
															  <s:iterator value="eqtimeList">
															 	<th style="width: 5%;" class="text-center"> <s:property /> </th> 
															  </s:iterator>
															 
														
															
														</tr>
													</thead>
													<tbody>
													     <s:iterator value="vitalMasterEquipmentList">
      													<tr>
								      					        <td><s:property value="name" /> (<s:property value="unit" />)</td>
								      					          <s:iterator value="vitalDataList">
								      					                 <td class="text-center"><p style="margin-bottom:0px !important;"><s:property value="finding"/></p><p class="setuseanme"><s:property value="userid"/></p></td> 
								      					          </s:iterator>
								      					</tr>
								      					</s:iterator>
														<!-- <tr>
															<td>CVP...Hrly</td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
															<td class="text-center"><p style="margin-bottom:0px !important;">23</p><p class="setuseanme">Arun123</p></td>
														</tr> -->
													</tbody>
													</table>
													</div>
													</s:if>
													
													
													<s:if test="filter=='All' || filter=='Nursing Care Plan'">
													<div class="Col-lg-12 col-xs-12 col-sm-12" style="padding:0px;">
														<h5><i class="fa fa-caret-right" aria-hidden="true"></i>Nursing Care Plan</h5>
												<table class="table" style="border: 1px solid #eee;width: 100%;">
													<thead>
													
													<tr>
													
													<th>Subjective care</th>
													<th>Objective care</th>
													<th>Diagnosis care</th>
													<th>Inference care</th>
													<th>Planning care</th>
													<th>Intervention care</th>
													<th>Rationale care</th>
													<th>Evaluation care</th>
													
													</tr></thead>
													     <s:iterator value="nursingcarelist">
      													<tbody><tr>
								      					        <td><s:property value="subjectivecare" /></td>
								      					         <td><s:property value="objectivecare" /></td>
								      					          <td><s:property value="diagnosiscare" /></td>
								      					           <td><s:property value="inferencecare" /></td>
								      					            <td><s:property value="planningcare" /></td>
								      					             <td><s:property value="interventioncare" /></td>
								      					              <td><s:property value="rationalecare" /></td>
								      					               <td><s:property value="evaluationcare" /></td>
								      					          
								      					</tr>
								      					</s:iterator>
													</tbody>
														
												</table>
											</div>
											</s:if>	
	
	
		


	<!-- /MAIN CONTENT -->
	<!--main content end-->

	
<script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script>

</body>
</html>
