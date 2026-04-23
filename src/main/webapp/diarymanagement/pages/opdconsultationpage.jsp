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
<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
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
	document.getElementById("yuviloader").style.display="none";

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


window.onload= function(){

	document.getElementById("gobtn").disabled=true;
	document.getElementById("setbtn").disabled=true;

}

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

										<h4>OP/IP Consultation Note</h4>

									</div>

</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="saveipdFakeTempOpdConsultation" theme="simple" id = "invoicerportfrm">
<input type="hidden" id="islmh" value="1">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<input type="hidden" id="docid">
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
				<label>Select Date</label>
			<!--  <label style="font-size: medium;font-weight: 900;">Date</label> -->
		                              <s:textfield  name="commencing" readonly="true" cssClass="form-control" id="fromDate" onchange="" />
			</div>
			
			
			<div hidden class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
			     <label>Select Doctor & Department:</label> 
				<s:select id="doctor" name="doctor" list="Docspecialitylist" listValue="doctor" listKey="id"
									headerKey="" headerValue="Select Doctor" onchange="checkdoctordiary(this.value)"
									title="Select Doctor form list" theme="simple"
									cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"/>
			</div>
			
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" style="margin-top: 20px;" >
			<input type="button" value="Set Diary" id="setbtn" class="btn btn-info active" onclick="setdoctordiary(0)">
		</div>	
			
			<!-- <div class="form-group">
			  <input type="number" class="form-control" id="patientno" name="patientno" placeholder="opd Patient">
			</div> -->
			 <div hidden class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
			    <label>Enter Patient Count</label> 
			  <input type="number" class="form-control" id="patientno1" name="patientno1" placeholder="Enter Patient count">
			</div>
			<!-- <div class="form-group">
			<input type="checkbox" class="form-control" id="isot" name="isot" >
		   </div> -->
		    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" style="margin-top: 2%;" >
		      <label></label> 
		         <label>Check for OT</label>  
		           <s:checkbox name="otcheck" id="isot" label="Ot"/>
		   </div>
		   
			<!-- <div class="form-group" style="margin-top: 2%;">
			<input type="submit" value="Go" onclick="return validate()" id="gobtn" class="btn btn-primary">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div> -->

            <div class="form-group" style="margin-top: 2%;">
			<input type="submit" value="Go" id="gobtn" class="btn btn-primary">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>			
		
	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable newlok" style="width:100%;border: 2px solid !important" >
								<tr>
								<td hidden style="width: 2%;">
                                    <input type="checkbox" id="selectalliventorystockid" onclick="selectAllCheckBox(this)" />
                                </td>
								<th>Sr.</th>
								<th>Practitioner Name</th>
								<th hidden>Department</th>
								<th class="hidden">Opd Capacity</th>
								<th>Ipd Capacity</th>
								<th hidden>As per norms(opd)</th>
								<th>As per norms(Ipd)</th>
								</tr>
								<%int i=0; %>
								<s:iterator value="Docspecialitylist">
								<tr >
								 <td hidden class="hidden-print">
                                   	<input type="checkbox" class="case" value="<s:property value="id"/>" class="form-control" />
                                </td>
								<td><%=++i %></td>
								
								<td><s:property value="Doctor"/></td>
								<td hidden><s:property value="Speciality"/></td>
								<td hidden style="width: 5px;"><input type="number" class="form-control hidden" name=" " /></td>
								<td style="width: 5px;"><input type="number" class="form-control" name="ipdpatientcount<s:property value="id"/>" onchange="checkdoctordiary(<s:property value="id"/>)"/></td>
								<td hidden>15 per day</td>
								<td>10 per day</td>
				                <td class="hidden"><input type="hidden" name="doctor<s:property value="id"/>" value="<s:property value="id"/>"></td>
                  
								
								</s:iterator>
								</table>
</div>								
	
	</s:form>
	</div>
	

</div>							
<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable newlok" style="width:100%;border: 2px solid !important" >
								<tr>
								<th>Sr.</th>
								<th>Practitioner Name</th>
								<th>Patient Name</th>
								<th>Contact No.</th>
								<th>Given Date </th>
								<th>Follow Up Date </th>
								<th>Send SMS</th>
								<th>Status</th>
								</tr>
								
								</table>
</div>								 -->




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

<div class="overlay" id="yuviloader" style="z-index: 9999999;height: -webkit-fill-available;">
  <div class="spinner"></div>
  <div class="label">Loading</div>
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

<script>

function validate(){
	
	if(document.getElementById('islmh')){
		$("#newloaderPopup").modal('show');
	}else{
		document.getElementById("yuviloader").style.display="";
	}
	return true;
	
}

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