<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
					filename: "labreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
});
</script>

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

										<h4>IPD Patients</h4>

									</div>
								</div>

<div class="row ">

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="newIpdCurrentPatientSummary" theme="simple" id = "invoicerportfrm">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
<div class="form-inline">
			
			<div class="form-group" style="width:7%;">
			FROM DATE
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:5%;">
			TO DATE	
			</div>
			<div class="form-group" style="width:7%;">
			<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="form-group"  style="width: 15%">
				<s:select name="diaryUser"  style="width: 100%" list="userList" listKey="id" listValue="diaryUser"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="All Practitioners"  ></s:select>
			</div>
		&nbsp;&nbsp;&nbsp;
				<div class="form-group"  style="width: 10%">
				<s:select name="adm"
				list="#{'all':'All Patient','admitted':'Admitted Patient'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
					&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;
			<div class="form-group" style="width: 20%">
			<label style="background-color:indianred">MLC</label>
			<label style="background-color:#b5b7b4">Expired</label>
			</div>

</div>
</div>
</s:form>
<%int i=1; %>
<table class="my-table xlstable" style="width: 100%" id="example">
	<thead>
				<tr bgcolor="#4E7894" >
			
					<td style="color:#eee;width: 5%">Sr. No.</td>
					<td style="color:#eee">UHID</td>
					<td style="color:#eee">IPD ID</td>
					<td style="color:#eee">MLC No</td>
					<td style="color:#eee">Self/TP</td>
					<td style="color:#eee">Patient Name</td>
					<td style="color:#eee">Father Name</td>
					<td style="color:#eee">Mother Name</td>
					<td style="color:#eee">Date of birth </td>
					<td style="color:#eee">Age/Gender</td>
					<td style="color:#eee;width: 20%">Diagnosis </td>
					<td style="color:#eee">Primary Consultant </td>
					<td style="color:#eee">Secondary Consultant </td>
					<td style="color:#eee;width: 3%">MLC</td>
					<td style="color:#eee">Addmission date</td>
					<td style="color:#eee">Discharge date</td>
					<td style="color:#eee">Discharged</td>
					
				</tr>
				</thead>
				<s:if test="currentPatientsList.size!=0">
					<s:iterator value="currentPatientsList">
						<s:if test="isdead==true">
							<tr bgcolor="#b5b7b4">
						</s:if>
						<s:elseif test="ismlc==1">
							<tr bgcolor="indianred" style="color: white">
						</s:elseif>
						<s:else>
							<tr>
						</s:else>
							<td><%=i++ %></td>
							<td><s:property value="abrivationid"/></td>
							<td>
								<%if(loginfo.isBalgopal()){ %>
									<s:property value="ipdabrivationid"/>
								<%}else{ %>
									<s:property value="ipdid"/>
								<%} %>
								
							</td>
							
							<td><s:property value="mlcno"/></td>
							<td><s:property value="tpName"/></td>
							<td><s:property value="clientname"/></td>
							<td><s:property value="Mothername"/></td>
							<td><s:property value="Fathername"/></td>
							<td><s:property value="dob"/></td>
							<td><s:property value="agegender"/></td>
							<td><s:property value="Diagnosis"/></td>
							<td><s:property value="practitionerName"/></td>
							<td><s:property value="sec_consultant"/></td>
							<s:if test="ismlc==1">
								<td>Yes</td>
							</s:if>
							<s:else>
								<td>No</td>
							</s:else>
					
							<td><s:property value="admissiondate"/></td>
							<td>
								<s:if test="isdead==true">
									(Expired)  [<s:property value="dischargedate"/>]
								</s:if>
								<s:elseif test="cancel==1">
									Admission Cancel
								</s:elseif>
								<s:else>
									<s:property value="dischargedate"/>
								</s:else>
							</td>
							<s:if test="discharge==0">
									<td style="text-align: center;">No</td> 
							</s:if>
							<s:else>
								<td style="text-align: center;" >Yes</td> 
							</s:else>
								<%-- <s:if test="cancel==1">
								<td>Cancel</td>
								</s:if>
								<s:else>
								<td></td> --%>
								<%-- </s:else> --%>
							</tr>
					</s:iterator>
				</s:if>
				</table>
</div>
</div>

</div>


  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
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
	

