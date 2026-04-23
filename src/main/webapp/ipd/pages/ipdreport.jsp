
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/finder.js"></script>

<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script> 
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>
 --%>
<script src="notification/js/bootstrap-select.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<!--  <link href="_assets/newtheme/css/main.css" rel="stylesheet" />	 -->
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.newbtnyes {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #38a538 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtnno {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #e22626 !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.newbtn {
	position: absolute !important;
	font-size: 14px !important;
	color: #fff !important;
	background-color: #15536E !important;
	border-color: #15536E !important;
	margin-right: -203px !important;
	border-radius: 0.50rem;
}

.btn {
	background-color: #15536E !important;
	border-radius: 0.50rem;
}

.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
	background-color: #c7e7e8;
}
.hhmm{
			width: 100%;
		}
</style>



<script>
	$(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#dischargedate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
		

	});

	function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "OPD TAT Report",
			filename : "DailyRegistrationReport",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
</script>


<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
<div class="reportprint">




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

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="background-color: #41cfd6;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<h4 style="font-size: 17px; font-weight: 600; color: white">Daily IPD REPORT
						</h4>
				</div>
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div>
					<s:form action="ipdreportCommonnew" id="deptfrm" theme="simple">
						<s:hidden name="clientId" id="clientId"></s:hidden>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print"
							style="display: flex; flex-wrap: wrap;">
							
				           <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
					              <label>Doctor List</label>
				                        <s:select cssClass="form-control  chosen-select" id="diaryUser" name="Practitionerid" list="userList" listKey="id"
					                     listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select Practitioner"/>
			               </div>
							
							 <%-- <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
					              <label>Admitted By</label>
				                        <s:select cssClass="form-control  chosen-select" id="Userid" name="Userid" list="AdmittedbyuserList" listKey="id"
					                     listValue="Userid" headerKey="0" theme="simple" headerValue="Select User"/>
			                   </div> --%>
							
							
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>From Date : </label>
								<s:textfield readonly="true" name="fromdate" id="fromdate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>To Date : </label>
								<s:textfield readonly="true" name="todate" id="todate"
									cssClass="form-control" theme="simple" placeholder="To date"></s:textfield>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label></label><br> 
									<input type="submit" value="Go" class="btn btn-primary active" /> 
									<a type="button" class="btn btn-primary" title="Print" onclick="printpage()">
									<i class="fa fa-print"></i></a> 
									<a type="button" id="btnxls" title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
							</div>

						</div>
					</s:form>
					<br />
					
					
		<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total Count Of Ipd:<label class="text-info"><s:property
						value="totalRecords" /></label>
			</div>

                    <%
						int i = 1;
					%>

					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
								<table
									class="table xlstable table-hover table-condensed table-bordered">
									<thead style="text-align: center;">


										<tr>
											<td>Serial No</td>
											<td>Reg No</td>
											<td>Patient Name</td>
											<td>Age/Gender</td>
											<td>Address</td>
											<td>Practitioner</td>
											<td>Department</td>
											<td>Diagnosis</td>
											<td>Date of Admission</td>
											<td>Date of Discharge</td>
										</tr>

									</thead>
									<tbody style="text-align: center;" >

										<s:iterator value="ipdReport">
											<tr>
											<td><%=i++%></td>
											<td hidden><s:property value="OldClientId" /></td>
											 <s:if test="OldClientId==null ">
										      <td><s:property value="abrivationid" /></td>
										   </s:if>
										<s:else>
										     <td><s:property value="OldClientId" /></td>
										</s:else>
											<td><s:property value="clientname" /></td>
											<td><s:property value="age" />/<s:property value="gender" /></td>
											<td><s:property value="clientAddress" /></td>
											<td><s:property value="Practitionername" /></td>
											<td><s:property value="department" /></td>
											<td><s:property value="diagnosis" /></td>
											<td><s:property value="Admissiondate" /></td>
										<s:if test="ischeck==0 ">
 											<td  style="vertical-align: middle;"><a href="#" onclick="editdate(<s:property value="Clientid"/>,<s:property value="id"/>)"><s:property value="DischargeDate" /></a></td>
 										</s:if>
 										<s:else>
 										    <td><s:property value="DischargeDate" /></td>
 										</s:else>
											<%-- <td style="vertical-align: middle;"><a href="#" onclick="showremarklist(<s:property value="id"/>)">Read Remark</a></td> --%>
											<%-- <td><a href="#"  data-toggle="modal" data-target="#ireq"><s:property value="DischargeDate" /></a></td> --%>
<%-- 											<td><input onclick="showsittingforPatient(<s:property value="id"/>)"><s:property value="DischargeDate" /></td>
 --%>											
											</tr>
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

</div>


 <div class="modal fade" id="updatedisdate" role="dialog">
    <div class="modal-dialog modal-md" role="document">
    
  <!-- Modal content-->
  
 <div class="modal-content" style="height: 216px;width: 419px;margin-left: 29%">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
             <h4 class="modal-title">Update Discharge Date</h4>
        </div>
        
     <div class="modal-body">
           <s:hidden id="sitting_Clientid"></s:hidden>
            <s:hidden id="id"></s:hidden>       
         
           
			             <div class="col-lg-4 col-md-6 col-xs-12 col-sm-6">
                             <div class="form-group">
                                         <label for="exampleInputEmail1" class="visible-xs visible-sm hidden-md hidden-lg">DOA</label>
                                            <label for="exampleInputEmail1" class="hidden-xs hidden-sm visible-md visible-lg"> Date</label>
                                            <s:textfield cssClass="form-control" readonly="true" id="dischargedate" name="dischargedate" />
                                        </div>
                                   </div>     
                                  
                         <div class="col-lg-6 col-md-6 col-xs-6 col-sm-3">
                              <div class="form-inline hhmm" >
                                   <label for="exampleInputName2">HH:MM</label><br>
									   <div class="form-group">
									         <s:select  name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="minute" id="minute" list="minuteList" cssClass="form-control" headerKey="0" headerValue="Select"/>
									     
									  </div>
							</div>
                        </div>
                    </div>
     
         
                   <div class="modal-footer">
                     <button type="button" class="btn btn-primary"
							   onclick="updatedischargedate()" style="margin-top: 9px;">Update</button>
		    </div>
   </div>
      
           </div>
        </div>
   
  


<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "95%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
</script>
