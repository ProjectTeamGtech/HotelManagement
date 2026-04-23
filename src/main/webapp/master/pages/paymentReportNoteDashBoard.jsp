<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%LoginInfo loginInfo= LoginHelper.getLoginInfo(request);
String adminfun="";
	if(loginInfo.getJobTitle().equals("Accounts")||loginInfo.getJobTitle().equals("BILLING INCHARGE")||loginInfo.getJobTitle().equals("Admin")){
		adminfun="setpopupforStatus";	
			}
	 %>	
<style>
.my-table th {
background-color: #197117;
text-align: center !important;
height: 25px !important;
}
.my-table td {

height: 35px !important;
text-align: center !important;
}
</style>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "newgst",
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
function setpopupforStatus(status,id){
	var data=document.getElementById('notes'+id).innerHTML;
	document.getElementById('notesdiv').innerHTML=data;
	document.getElementById('statusid').value=id;
	
	if(status=='1'){
		document.getElementById('butdiv').className="hidden";
	}else{
		if(status=='2'){
			document.getElementById('penbut').className="hidden";
			
		}else{
			document.getElementById('penbut').className='btn btn-danger';
		}
		document.getElementById('butdiv').className="";
	}
	$('#chnagestatus').modal( "show" );
}
</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div style="height:40px;text-align: center;width: 100%;background-color: #197117;color: white;"><h3 style="padding-top: 9px;">Payment Report Notes DashBoard</h3></div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px;">
<s:form action="paymentreportNotesChargesRpt">
<div class="form-inline">
		<input type="hidden" id='statusid'>	
			<div class="form-group" style="width:15%;">
			<label>From Date</label>
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:80%;" placeholder="From Date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:15%;">
			<label>To Date</label>
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:80%;" placeholder="To Date"></s:textfield>
			</div>
			<div class="form-group" style="width:15%;">
				<br>
				<s:submit value="Go" theme="simple" cssClass="btn btn-success"></s:submit>
			</div>

</div>	
</s:form>		
</div>

			<table class="my-table xlstable" style="width:100%">
			
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Date/Time</th>
				<th>Status</th>
				<th>Note</th>
				<th>Approved Date</th>
				<th>Pending Date</th>
			
				
			</tr>
			<%int i=1; %>
			<s:iterator value="list">
			<tr>
				<td><%=i %><%i++; %></td>
				<td><s:property value='username'/></td>
				<td><s:property value='date'/></td>
				<td>
			<%String color=""; %>
			<s:if test="status==0"><%color="red"; %></s:if>
			<s:elseif test="status==1"><%color="blue"; %></s:elseif>
			
			<s:else><%color="green"; %></s:else>
				<div style="background-color:<%=color %>;color:white;text-align: center;" onclick="<%=adminfun%>(<s:property value='status'/>,<s:property value='id'/>)">
				
				<s:if test="status==0">Created</s:if>
				<s:elseif test="status==1">Approved</s:elseif>
				<s:else>Pending</s:else>
				
				</div>
				
				</td>
				<td id="notes<s:property value='id'/>"><s:property value='notes'/></td>
				<td><s:property value='apprDate'/></td>
				<td><s:property value='penDate'/></td>
			</tr>
			</s:iterator>
			</table>
			
			
</div>


<div id="chnagestatus" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content" id="crmodel">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<b>Set Status For Notes</b>
				</h4>
			</div>
			<div class="modal-body" style="padding-left: 30px;padding-right: 30px;">
			Note: <label id='notesdiv'></label>
			<div style="width: 100%" id='butdiv'>
			<input type="button" value="Approve"  class='btn btn-success' style="width: 48%" onclick="setpymentreportstatus(1)">
			<input type="button" value="Pending" id='penbut'  class='btn btn-danger' style="width: 48%" onclick="setpymentreportstatus(2)">
			</div>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>		


<script >

</script>	