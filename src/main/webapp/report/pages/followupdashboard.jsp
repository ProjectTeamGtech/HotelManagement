<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<script type="text/javascript" src="ipd/js/addcharges.js"> </script>
<script type="text/javascript" src="emr/js/emr.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "followuplist",
					filename: "followuplist",
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
<style>
.newlok tr{
border: 1px solid !important;
text-align: center;

}
.newlok td{
border-right: 2px solid !important
}
.newlok th{
border-right: 2px solid !important;
text-align: center !important;
}
</style>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Follow up List</h4>

									</div>

</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="followupdashboardClient" theme="simple" id = "invoicerportfrm">

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
				
			<%-- <div class="form-group">
				<s:select name="practitionerName" list="userList" listKey="diaryUser" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div> --%>
			
			
			
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		</div>
	</div>
	</div>
	</s:form>
	<form action="sendfollowupsmsallClient" style="text-align: right;margin:10px ">
	<s:hidden name='fromdate'></s:hidden>
	<s:hidden name='todate'></s:hidden>
	<input type="submit" value="Send sms to all" class="btn btn-success"  style="border-radius: 12px;">
	</form>		
</div>							
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable newlok" style="width:100%;border: 2px solid !important" >
								<tr>
								<th>Sr.</th>
								<th class='hidden' >Practitioner Name</th>
								<th>Client Name</th>
								<th>Contact No.</th>
								<th>Given Date </th>
								<th>Follow Up Date </th>
								<th>Send SMS</th>
								<th>Status</th>
								</tr>
								<%int i=0; %>
								<s:iterator value="condtitionList">
								<tr >
								<td><%=++i %></td>
								
								<td class='hidden'><s:property value="diaryUser"/></td>
								<td><s:property value="clientName"/></td>
								<td><b><s:property value="mobNo"/></b></td>
								<td><s:property value="date"/></td>
								<td><s:property value="followupdate"/></td>
								<td>
								<s:if test="state==0">
								<button class="btn btn-warning" style="border-radius: 12px;" onclick="sendfollowupsms('<s:property value="diaryUser"/>','<s:property value="followupdate"/>',<s:property value="clientId"/>,<s:property value="id"/>)"> Send Follow up SMS</button>
								</s:if>
								<s:else><b>SMS Sent</b></s:else>
								</td>
								<td><a href='#' onclick="openstatusmodal('<s:property value="diaryUser"/>','<s:property value="clientName"/>','<s:property value="followupdate"/>','<s:property value="id"/>')"><b><s:property value="fstatus"/></b></a></td>
								</tr>
								</s:iterator>
								</table>
</div>								




</div>

<!-- for status -->
<div id="followupstatusmodal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"> Select Follow up Status </h4>
      </div>
      <div class="modal-body">
      <input type="hidden" name='followupid' id='followupid' value='0'>
  		<div class="col-lg-12 col-md-12 col-sm-12">
			<label style="color: green">Client :</label><span id='clientname'></span><br>
			<label style="color: green">Practitioner :</label><span id='pra'></span><br>
			<label style="color: green">Follow Up Date :</label><span id='fdate'></span><br>
			<label>Add New Follow Up</label>
			<input type="text" readonly name='followupdatenew' id='followupdatenew' class='form-control'>
			<br><br>
			<select class="form-control chosen-select" onchange="setStatusFollowup(this.value)">
			<option value='0'>New</option>
			<option value='1'>DND</option>
			<option value='2'>Done</option>
			</select>
				</div>
      <div class="modal-footer">
      
         
        
      </div>
    </div>

  </div>
</div>
</div>
<script>

$(document).ready(function() {

	$("#followupdatenew").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
});


function openstatusmodal(doctor,clientname,followupdate,id){
	document.getElementById("pra").innerHTML=doctor;
	document.getElementById("fdate").innerHTML=followupdate;
	document.getElementById("clientname").innerHTML=clientname;
	document.getElementById("followupid").value=id;
	$('#followupstatusmodal').modal( "show" );
}

</script>
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