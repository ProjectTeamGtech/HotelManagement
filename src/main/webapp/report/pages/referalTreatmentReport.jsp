<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<style>
	.chosen-container, .chosen-container-single, .chosen {
    width: 100% !important;
    min-width: 100%;
}
</style>

<script>


  function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Referal Treatment Report List",
					filename: "referalReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  


var totalExpenceCheckbox = 0;

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
	
	$('#selecctall').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.case').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.case').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
    });
	
});




function showreport(){
	
	  $('.case').each(function() { //loop through each checkbox
		  if(this.checked==true){
			  totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;   
		  }
      }); 
	
	  document.getElementById('diaryUser').value = totalExpenceCheckbox;
	document.getElementById('referalFrm').submit();
}


</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Treatment States by Referral Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										
<s:form action="treatmentReferralSummary" theme="simple" id = "referalFrm">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'All Paid By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control chosen-select" onchange="setActionForAll()"></s:select>
			</div>
			<div class="form-group">
				<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="setActionForAll()"
				listValue="thirdParty"  headerKey="0" headerValue="All TP"
				list="thirdPartyList" cssClass="form-control showToolTip chosen-select"></s:select>
			</div>
			<div class="form-group">
				<s:select id="gpid" name="gpid" listKey="id" onchange="setActionForAll()"
				listValue="thirdParty"  headerKey="0" headerValue="All GP"
				list="gpList" cssClass="form-control showToolTip chosen-select"></s:select>
			</div>
			<div class="form-group">
				<s:select id="condition" name="condition" listKey="id" onchange="setActionForAll()"
				listValue="treatmentName"  headerKey="0" headerValue="All Conditaion"
				list="treatmentTypeList" cssClass="form-control showToolTip chosen-select"></s:select>
			</div>
			<div class="form-group">
				<s:if test="%{#referalList != 'null'}" >
			<s:select cssStyle="height: 31px;padding: 0px" cssClass="form-control chosen-select" 
						id="referal" name="referal" list="referalList" listKey="id" listValue="referal" 
						headerKey="0" theme="simple" headerValue="All Referred By"  />
			</s:if>
			</div>
			<div class="form-group" style="wdith:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="wdith:100%;"></s:textfield>
			</div>
			<div class="form-group" style="wdith:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="wdith:100%;"></s:textfield>
			</div>
			
			<div class="form-group">
					<s:hidden name="diaryUser" id="diaryUser"/>
			
			<script>
	
	window.onload = function(){
		if(document.getElementById('diaryUser').value==''){
			
			document.getElementById('selecctall').checked = true;
			 $('.case').each(function() { //loop through each checkbox
		         this.checked = true; //deselect all checkboxes with class "checkbox1"                       
		     }); 
		}else{
			
			var data = document.getElementById('diaryUser').value;
			var temp = data.split(',');
			 
			
			for(i=0;i<temp.length;i++){
				if(temp[i]!=0){
					document.getElementById('ch'+temp[i]).checked = true; 
				}
				
			} 
		}
	}
	
	</script>
			
			<div id="scroll">
			<s:checkbox id="selecctall" name="selecctall" type="checkbox"  /> Select All Practitioner<br>
			<s:iterator value="userList">
				<input class="case" type="checkbox"  id="ch<s:property value="id"/>" name="ch<s:property value="id"/>" value="<s:property value="id"/>">
				<s:property value="diaryUser"/><br>
				
				
			</s:iterator>
			</div>
			</div>
			<div class="form-group">
				<input onclick="showreport()" type="button" value="Go" theme="simple" class="btn btn-primary">
		<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
		<a type="button" title="Save As PDF" class="btn btn-primary" onclick="return previewReferalTreatment();"><i class="fa fa-file-pdf-o"></i></a>
		<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			<div class="form-group">
				<div id="sendMailreferalTreatmentId"></div>
			</div>
		</div>
	
	
		
		
		
	</div>
	
			
	</s:form>	
	
	
	
	
	
	
	<s:if test="treatmentReferralList.size!=0">
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />			
				<table class="my-table xlstable" style="width: 100%;">
					<thead>
						<tr>
							<th>Payed By</th>
							<th>Practitoner</th>
							<th>TP Name</th>
							<th>GP Name</th>
							<!-- <th>Sr. No.</th> -->
							<th>Patient No</th>
							<th>Patient Name</th>
							<th>Ward Name</th>								
							<th>Address</th>
							<th>Town</th>
							<th>Treatment Name</th>
							<th>ref_date</th>
							<th>SOI</th>
							<th>Condition</th>
							<th>Referred By</th>
							
						
							</tr>
					</thead>
					<tbody>							
						<% int srno = 1;%>
							<s:iterator value="treatmentReferralList" status="rowstatus">
								<tr>
									<%-- <td><%=srno%></td> --%>
									<s:if test="payby=='Client'">
										<td>Self</td>
									</s:if>
									<s:else>
										<td><s:property value="payby"/></td>
									</s:else>
									
									<td><s:property value="practitionerName"/></td>
									<td><s:property value="tpName"/></td>
									<td><s:property value="gpName"/></td>
									<td>
									<s:if test="abrivationid==1">
										0000<s:property  value="clientId" />
									</s:if>
									<s:else>
										<s:property value="abrivationid"/>/<s:property  value="clientId" />
									</s:else>
									</td>	
									<td><s:property  value="clientname" /><br><s:property  value="email" /> / <s:property  value="mobno" /></td>									
									<td><s:property  value="wardname" /></td>
									<td><s:property  value="address" /></td>
									<td><s:property  value="town" /></td>
									<td><s:property  value="treatmentName" /></td>
									<td><s:property  value="ref_date" /></td>
									<td><s:property  value="ref_source" /></td>
									<td><s:property  value="condition" /></td>
									<td><s:property  value="referal" /></td>
									
									</tr>
									<%srno++;%>									
							</s:iterator>
				</tbody>				
			</table>
		</div>
	</div>
	</s:if>
	
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> No Treatment States by Referral Report found!!
					</h3>
				</s:else>	

	
	
		<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailReferalPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
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
							<s:textfield theme="simple" id = "referalEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "referalccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "referalsubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="referalEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="referalReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendReferalReportMail();">Send</button>
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



<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
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
$(function () {
    $('#scroll').slimScroll({
        height: '100px',
        railVisible: true,
		alwaysVisible: true
    });
   
});

</script>

 
