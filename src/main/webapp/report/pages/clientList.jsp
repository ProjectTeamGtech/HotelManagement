<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>		
<script type="text/javascript" src="report/js/client.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>

    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Client List Report",
					filename: "clientList",
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
	
	
	function setActionForAll(){
		//document.getElementById('invoicerportfrm').submit();
	}
	
	$("#thirdParty").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	  bkLib.onDomLoaded(function() {
	        
	      	 new nicEditor().panelInstance('clientListEmailBody');
	      	 $('.nicEdit-panelContain').parent().width('500px');
	      	 $('.nicEdit-panelContain').parent().next().width('500px');
	      	 
	      	 $('.nicEdit-main').width('100%');
	      	 $('.nicEdit-main').height('80px');
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
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>All Patient List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">


											<s:form action="clientListClientRpt" theme="simple" id="invoicerportfrm">
	<s:hidden name="order" id="order"/>
    <s:hidden name="orderby" id="orderby"/>
	
		<div class="col-lg-12 col-md-12 topback2 hidden-print">
			<div class="form-inline">
				<div class="form-group">
					<s:select name="diaryUser" list="userList" listKey="id" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="0" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
				</div>
				<div class="form-group">
					<s:select name="payby" id="payby" 
					list="#{'0':'All Payed by','Client':'Self','Third Party':'Third Party'}"
					cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
				</div>
				<div class="form-group">
					<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="setActionForAll()"
					listValue="thirdParty"  headerKey="0" headerValue="All TP"
					list="thirdPartyList" cssClass="form-control showToolTip chosen-select"></s:select>
				</div>
				<div class="form-group">
					<s:select id="location" name="location" listKey="id"
					listValue="location" headerKey="0" headerValue="All Referral Location"
					list="locationList" value="location" cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
				</div>
				<div class="form-group" style="width:7%;">
					<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
				</div>
				<div class="form-group" style="width:7%;">
					<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
				</div>
				<div class="form-group">
					<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
					<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
					<a type="button" title="Save As PDF" onclick="return saveAsPdfClientList();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
				    <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
				</div>
			</div>
		
		</div>	
			
		
</s:form>




		<s:if test="clientList!=null">
		
			<table class="my-table xlstable" style="width: 100%;font-size: 8px">
				<thead>
					<tr>
					<th>Patient No. <a href="#" onclick="setSorting('clientid','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
					<th>Practitioner</th>
					<th>Name <a href="#" onclick="setSorting('firstname','<s:property value="order"/>')"><i class="fa fa-sort"></i></a></th>
					<th>Address</th>
					<th>Town</th>
					<th>State</th>
					<th>PostCode</th>
					<th>Contact No.</th>
					<th>Email</th>
					<th>DOB</th>
					<th>No. Of Apmts</th>
					<th>TP Charges</th>
					<th>Self Charges</th>
					<th>TP Paid Amt</th>
					<th>Self Paid Amt</th>
					<th>TP Balance</th>
					<th>Self Balance</th>
				</tr>
					</thead>
					<tbody>
					<s:if test="clientList.size>0">
						<s:iterator value="clientList">
							<tr>
								<td><s:property value="abrivationid"/>/<s:property value="id"/></td>
								<td><s:property value="diaryUser"/></td>
								<td><s:property value="initial"/><s:property value="firstName"/> <s:property value="middlename"/> <s:property value="lastName"/></td>
								<td><s:property value="address"/></td>	
								<td><s:property value="town"/></td>
								<td><s:property value="county"/></td>
								<td><s:property value="postCode"/></td>
								<td><s:property value="mobNo"/></td>	
								<td><s:property value="email"/></td>
								<td><s:property value="dob"/></td>	
								<td><s:property value="noApmts"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalTPCharges"/></td>	
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalSelfCharges"/></td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalTPPaidAmt"/></td>	
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalSelfPaidAmt"/></td>	
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalTPBalance"/></td>	
								<td><%=Constants.getCurrency(loginfo)%><s:property value="totalSelfBalnace"/></td>	
							
								</tr>
						
						</s:iterator>
					</s:if>
					
					
					<s:else>
						<h3 class="text-center"><i class="fa fa-times text-danger"></i> There is no Record found!!</h3>
					</s:else>
					 			
								</tbody>
							</table>
				
				
		</s:if>
<!-- Modal Email-->
<div class="modal fade" id="sendEmailClientListPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "clientListEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "clientListccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "clientListSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="clientListEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfClientListMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendClientListMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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

	

<%-- <s:form action="clientListClientRpt" name="paginationForm" id="paginationForm" theme="simple">

	<div class="row hidden-print">
		<div class="col-lg-4 col-md-4 text-right">
			Showing all <label class="text-info">(<s:property
					value="pagerecords" /> of <s:property value="totalRecords" />
				Records)
			</label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form> --%>