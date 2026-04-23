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
.my-table td{
border:3px solid #DFD8D4 !important ;padding: 5px !important;
font-size: 11px !important;
}

.lokeshdiv{
border: 2px dashed grey; 
min-height: 100px;

}

.loktitle{
font-family: cursive;
}

.loksepdiv{
padding: 20px;
}

.Admin{
height: 50px;
background-color: cyan;
text-align: left;
}
.User{
height: 50px;
background-color: yellow;
text-align: right;
}

.newdiv1{
padding: 30px;
font-family: cursive;
height: 400px;
overflow: scroll;
}
.newdiv1 span{
padding: 10px;
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

							
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Support Request Assessment </h4>

									</div>

</div>	
<div>
<h2 class="loktitle">Ticket id (SC<s:property value="ticketid"/>)</h2>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding-top: 20px">
	
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 lokeshdiv" >
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 loksepdiv" >
	<p><label>Clinic--User</label></p>
	<s:property value="clinicid"/>--<b><s:property value="userid"/></b>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 loksepdiv" >
	<p><label>Query Type</label></p>
	<s:property value="querytype"/>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 loksepdiv" >
	<p><label>Query</label></p>
	<s:property value="query"/>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 loksepdiv" >
	<label>Handled By</label>
	<s:select name="executive"   cssClass="form-control chosen-select" list="executivelist" listKey="support_executive" listValue="support_executive" headerKey="" headerValue="All Executives"></s:select>
	</div>
	</div>
	
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 lokeshdiv">
	<h4>Messages</h4>
	<div class="newdiv1" >
	<s:iterator value="messagelist">
	
	<div class="<s:property value="who"/>">
	<span><s:property value="who"/>&nbsp;&nbsp;&nbsp;(<s:property value="datetime"/>)</span><br>
	<span><b><s:property value="msgs"/></b></span>
	</div>
	</s:iterator>
	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " >
	<input type="text"  name="msg" id="msg" class="form-control" style="border-color: green"><input type="button" class="btn btn-success" value="Send" onclick="sendsupportmsg('Admin',<s:property value="ticketid"/>)"><br><br>
	
	</div>
	</div>
	
	
</div>							




</div>
	<!-- Modal for  status - lokesh -->
<div id="changesta" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"> Select status</h4>
      </div>
      <div class="modal-body">
 
			<input type="hidden" name="ticketid" id="ticketid">
			
			<div class="col-lg-12 col-md-12 col-sm-12">
			
			<p><label style="color: green">Clinic:</label> <b id="clinicname"></b></p>
			<p><label style="color: green">Query :</label> <b id="queryname"></b></p>
			<s:select name="newstatus" id="newstatus" cssClass="form-control chosen-select" onchange="setSupportReqStatus(this.value)"
									list="#{'':'All','0':'Requested','1':'Work In Progress','2':'Fixed','3':'Not Possible'}"
			></s:select>
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
function setdata(status,ticketid,query,clinic){
	document.getElementById("newstatus").className="";
	document.getElementById("newstatus").value=status;
	 $("#newstatus").trigger("chosen:updated");
	   $(".chosen-select").chosen({allow_single_deselect: true});
	document.getElementById("ticketid").value=ticketid;
	document.getElementById("queryname").innerHTML=query;
	document.getElementById("clinicname").innerHTML=clinic;
	 $("#changesta").modal('show');
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
