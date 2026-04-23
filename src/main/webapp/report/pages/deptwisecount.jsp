<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<style>

 .table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
    background-color: #c7e7e8;
}
</style>

<script type="text/javascript">
	
var todayDate = new Date().getDate();

 $(document).ready(function() {
	
	$("#currentDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		//minDate : new Date(new Date().setDate(todayDate-6)),
		changeMonth : true,
		changeYear : true,
		maxDate: new Date(new Date().setDate(todayDate))
	});
	
	$("#fromDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
}); 
</script>

<script type="text/javascript" src="manasopd/js/adjustdata.js"></script>
	<body>
		<div class="">
		<div
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print">
			<div class="row letterheadhgt" style="height: 135px;">
				<div id="newpost"
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<link href="common/css/printpreview.css" rel="stylesheet" />
					<%@ include file="/accounts/pages/letterhead.jsp"%>
				</div>
			</div>
		</div>
		
					<div class="row" style="padding: 0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
							 	<h4>Department wise Count Report </h4>
							</div>
						</div>
					</div>
			<div class="row" style="padding-top: 15px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			
			<form action="deptwisecountPatientdata" id="mainform">
			  <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			 
	            <div class="form-group" >
	             <label>From Date</label>
				      <s:textfield readonly="true" name="fromDate" id="fromDate"
				       cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			   </div>
			   </div>
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			   <div class="form-group" >
			     <label>To Date</label>
				      <s:textfield readonly="true" name="toDate" id="toDate"
				      cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
			   </div>
			   </div>
			    <%-- <div class="col-md-2 col-lg-2 col-xs-2 col-sm-2">
			   <label></label>
			     <div class="form-group" >
				<s:select list="Departmentlist" name='dept' listKey="id" listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select" theme="simple"></s:select>
					
			   </div>
			  </div> --%>
			   <div class=" col-lg-2 col-md-2 col-xs-2 col-sm-2  hidden-print">
			   <label></label>
			     <div class="form-group" >
					<button type="submit" class="btn btn-primary">Go</button>
					<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			   </div>
			  </div>
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			  <div class="form-group" >
			    
			    </div>
			  </div>
			</form>
					</div>	
				</div>
				<div class="row " style="padding-top: 15px;">
					
					
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
								    <td>Sr. No.</td>
									<td>Department</td>
									<td style="text-align: center;">New Patient</td>
									<td style="text-align: center;">ARV New Patient</td>
									<td style="text-align: center;">Old Patient</td>
									<td style="text-align: center;">ARV Old Patient</td>
									<td style="text-align: center;">Total</td>
								
								</tr>
							</thead>
							<tbody>
						             <%int i=1; %>
										<s:iterator value="DeptWiseCountList">
										<tr>
										    <td><%=i++ %></td>
											<td><s:property value="discipline"/></td>
											<td style="text-align: center;"><s:property value="Newpatient"/></td>
											<td style="text-align: center;"><s:property value="Arvnew"/></td>
											<td style="text-align: center;"><s:property value="Oldpatient"/></td>
											
											<td style="text-align: center;"><s:property value="Arvold"/></td>
											<td style="text-align: center;"><s:property value="Totalcount"/></td>
										</tr>
								</s:iterator>
							</tbody>
							
						</table>
					</div>
					
					
				</div>
					
					
				
		</div>
		
		
		
		
		
		
		 <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93)" id="newloaderPopup" aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
		
	</body>
</html>