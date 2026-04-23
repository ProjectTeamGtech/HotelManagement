<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>

<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Current Patient List",
					filename: "currenrpatientlist",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

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


function setSorting(column,order){
	if(order=='asc'){
		order = 'desc';
	}else{
		order = 'asc';
	}
	document.getElementById('orderby').value = column;
	document.getElementById('order').value = order;
	document.getElementById('invoicerportfrm').submit();
}


</script>

<div class="">
							<div class="">
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
								<div class="row details">
								
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									

										<h4>IPD Current Patient Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="currentpatientsSummary" theme="simple" id = "invoicerportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			
			<div class="form-group" style="width: 8%">
				<s:select name="days" id="days" 
				list="#{'0':'Select Days','20':'20 Days','30':'30 Days','40':'40 Days','60':'60 Days','70':'70 Days','90':'90 Days'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div>
			<div class="form-group">
				<s:select id="ward" name="ward" listKey="wardid"
				listValue="wardname" headerKey="0" headerValue="Ward list"
				list="wardlist" value="wardname" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
		<div class="form-group">
		
				<s:select name="orderby1" id="orderby1" 
					list="#{'':'Order By','0':'Ward/bed','1':'Practitioner'}"
					cssClass="form-control chosen-select" ></s:select>
			</div>
		 <div class="form-group" style="width: 10%">
		
				<s:select name="paymentmode" id="paymentmode" 
					list="#{'0':'Payment Mode','1':'Self','2':'Third Party'}"
					cssClass="form-control chosen-select" ></s:select>
			</div>
			<%-- <div class="form-group">
				<s:select cssClass="form-control showToolTip chosen-select" name="dischargeStatus"
				id="dischargeStatus" list="dischargeStatusList"
				listKey="id" listValue="name" headerKey="0"
				headerValue="All Discharge Status" />
			</div>
			 --%><%-- <div class="form-group">
				<s:select name="discharge" id="discharge" 
					list="#{'All':'Discharged All','0':'No','1':'Yes'}"
					cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
	 --%>		
	 <div class="form-group">
	 
	 				<s:select name="ismlc" id="mlclilist" 
					list="#{'0':'All Patients','1':'MLC','2':'NON MLC'}"
					cssClass="form-control chosen-select" >
					</s:select>
	 </div>
	 
	 <div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<div class="form-group">
			<s:if test='issecondery=="yes"'>
			<span><input type="radio" name="issecondery" value="yes" checked="checked"> Secondary Consultant</span><br>
			<span>	<input type="radio" name="issecondery" value="no" > Primary Consultant</span>
			</s:if>
			<s:else>
			<span><input type="radio" name="issecondery" value="yes"> Secondery Consultant</span><br>
			<span>	<input type="radio" name="issecondery" value="no" checked="checked"> Primary Consultant</span>
			</s:else>
			</div>
			
		</div>
	</div>
	
	</s:form>
	
	
	<table class="my-table xlstable  table-striped table-bordered" id="example" style="width: 100%">
	<thead>
				<tr>
				<td style="width:3%">sr. no.</td>
				<td>UHID</td>
				<td>IPD No.</td>
				<%-- <%if(loginInfo.getIpd_abbr_access()==1) {%>
				<td>New IPD ID</td>
				<%} %> --%>
					<td style="width:11%">Patient Name</td>
					<td>Pay by</td>
					<td width="110px" >Ward/ Bed</td>
					<td style="width:11%">Primary Consultant</td>
					<td style="width:11%">Secondary Consultant</td>
						<td style="width:11%">MLC Consultant</td>
					<td>Diagnosis </td>
					<td>Admission Date</td>
					<td>Total No Of Days</td>
					<td>Address</td>
					<td>Age</td>
				<td>Mob No</td>	
				<td>Father Name</td>
				<td>Mother Name</td>
				<td>Birth Place</td>
				</tr>
			</thead>	
				
				<s:if test="currentPatientsList.size!=0">
				<% int i=1;%>
							<s:iterator value="currentPatientsList" status="rowstatus">
							
						
						
								<tr><td><%= i++%></td>
								<td><s:property value="abrivationid"/></td>
								<%-- <td><s:property value="ipdid"/></td> --%>
								<td><s:property value="newipdabbr"/></td>
								<%-- <%if(loginInfo.getIpd_abbr_access()==1) {%>
								<td><s:property value="newipdabbr"/></td>
								<%} %> --%>
									<td><s:property value="clientname"/></td>
									<s:if test="tpName==''">
									<td>Self</td></s:if>
									<s:else>
									<td><s:property value="tpName"/></td>
									</s:else>
								<td><s:property value="wardbed"/></td>
								
								<td><s:property value="practitionerName"/></td>
								<td><s:iterator value="sec_consultantlist">
								<s:property value="sec_consultant"/>
								</s:iterator></td>
								<td><s:iterator value="mlclist">
								<s:property value="sec_consultant"/>
								</s:iterator></td>
								<td><s:property value="diagnosis"/></td>
						<td>	<s:property value="admissiondate"/></td>
								
								<td><s:property value="admitdays"/></td>		
									<td><s:property value="address"/></td>		
								<td><s:property value="age1"/></td>
								<td><s:property value="mobno"/></td>		
								<td><s:property value="fathername"/></td>
								<td><s:property value="Mothername"/></td>		
								<td><s:property value="birthplace"/></td>		
								</tr>
								
							</s:iterator>
						</s:if>
						
						<s:else>
					No Record Found!!
				</s:else>
				
</table>
											

											
										
									</div>
								</div>
							</div>
						</div>


<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis','print' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.print.js"></script>
	


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>



						
	
	