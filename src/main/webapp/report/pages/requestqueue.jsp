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

<style>
td{
border:3px solid #DFD8D4 !important ;padding: 5px !important;
font-size: 11px !important;
}
td a{
color: #822828fa;
}
</style>

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

</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

							
					
<div class="row details" style="background-color: #3c6ea0  !important">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Support Request Queue</h4>

									</div>

</div>	
<h3>Welcome <s:property value="username"/> !</h3>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">


	
</div>							
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable" style="width:100%">
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Sr.</td>
								
								<td>Ticket Id</td>
								<td>User </td>
								<td style="width: 7%"> Date / 
								
								Time</td>
								
								<td>Request type</td>
								<td style="width: 40%">Request</td>
								<td>Remark</td>
								<td style="width: 5%">Send Chat/ ScreenShots</td>
								<td>Status</td>
								<td>Is Chargeable / Time Taken</td>
							<!-- 	<td style="width: 2%">Message</td>  -->
								
								</tr>
								<%int i=0; %>
								<s:iterator value="supportrequestlist">
								<%String bgcolor=""; %>
								<s:if test="chargable">
									<%bgcolor="#ceecf2"; %>
								</s:if>
								<tr style="background-color: <%=bgcolor%>">
								<td><%=++i %></td>
								
								<td>SC<s:property value="id"/></td>
								<td><s:property value="fullname"/></td>
								<td><s:property value="date"/> / 
								 <s:property value="time"/></td>
								<td><s:property value="query_type"/></td>
								<td><a href="#" onclick="getconversationall(<s:property value="id"/>,'User')"><s:property value="query"/></a>
								<s:if test="attached">
									<b><i class='fa fa-paperclip  fa-3x ' ></i></b>
								</s:if>
								</td>
								<td><s:property value="remark"/></td>
								<td><a href="#" onclick="getconversationall(<s:property value="id"/>,'User')"><b style="/* color: #1e376f */"> Upload Screenshot&nbsp;&nbsp;&nbsp;<i class="fa fa-upload fa-2x"></i></b></a></td>
								
								<td >
								<s:if test="status==0"><label style="color: green">Requested</label></s:if>
								<s:elseif test="status==1"><label style="color: black">Work In Progress</label></s:elseif>
								<s:elseif test="status==2"><label style="color: blue">Fixed    </label>  - <span><s:property value="fixedDate"/></span></s:elseif>
								<s:else><label style="color: red">Not Possible</label></s:else>
								</td>
								<%-- <td>
								<s:if test="username==usr">
								<a onclick="opencPopup('requestdetailsSupport?ticketid=<s:property value="id"/>')"><i class="fa fa-envelope"></i></a>
								<s:if test="msgread">
								<img src="dashboardicon/newdiet.gif"></img>
								</s:if>
								</s:if>
								</td> --%>
								<!-- <td><input type="file" name="newf" accept="image/*" onchange="sayhi(this.value)"></td> -->
								<td style="color: black;font-weight: bold;"><s:if test="chargable">Chargeable / <s:property value="timeTaken"/>hrs</s:if><s:else>NO</s:else> </td>
								</tr>
								<tr class='hidden' id='newrow<s:property value='id' />'> 
								<td colspan="9"  ><div id='newcol<s:property value='id' />' class=''></div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 20px;">
								<form method="post" action="savescreenshotSupport" id='form<s:property value='id' />'  enctype='multipart/form-data'>
								<s:hidden name="subuploadfilesContentType"></s:hidden>
								<s:hidden name="subuploadfilesFileName"></s:hidden>
								<p class='hidden' id='ppp<s:property value='id' />'></p>
								<s:file accept="image/*" cssClass="form-control col-lg-4 col-md-4  " theme="simple" name="subuploadfiles" id="filesub_uploadfiles" style='width:20%'></s:file>
								
								<input type="button" class='btn btn-primary ' value="Save Screenshot" onclick="submitl(<s:property value='id' />)">
								</form>
								</div>
								
								
								</td>
								<td><s:if test="chargable">YES / <s:property value="timeTaken"/> hrs</s:if><s:else>NO</s:else> </td>
								</tr>
								</s:iterator>
								</table>
								
<div style="margin-top: 20px !important">
<!-- pagination div -->
<s:form action="requestqueue1Support" name="paginationForm" id="paginationForm"
	theme="simple">
		
		<s:hidden name="totalcount"></s:hidden>
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalcount" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
		
		
	</div>
</s:form> 

</div>
								
								
</div>								




</div>
<div id="remarksmodal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"> Messages</h4>
      </div>
      <div class="modal-body">
 
			<input type="hidden" name="ticketid" id="ticketid">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="col-lg-12 col-md-12 col-sm-12" id="messagediv">
			
			<label style="float: left;color: red" >user</label><br>
			
			
			<label style="float: right;color: blue"">admin</label>
			
			</div>
			<br><br>
			<input type="text" class="form-control" name="message" id="message">
			<br><br>
			<div><button class="btn btn-primary">Save</button></div>					
      		</div>
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         
        
      </div>
    </div>

  </div>
</div>
</div>

<script>
function sayhi(){
	$("#remarksmodal").modal('show');
	
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
