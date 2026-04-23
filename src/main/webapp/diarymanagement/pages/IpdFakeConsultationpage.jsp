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
<script type="text/javascript" src="inventory/js/procurement.js"></script>
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
	
	$("#commencing").datepicker({

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

										<h4>OPD Consultation Note</h4>

									</div>

</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="saveopdfaketempIpdConsultation" theme="simple" id = "invoicerportfrm">
  <input type="hidden" id="docid">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
			 <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
				<label>Select Date</label>
				  <s:textfield readonly="true" name="FromDate" id="fromDate" placeholder="Search by Date"
				     cssClass="form-control" theme="simple" />
			</div>
			
			
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding hidden" >
			     <label>Select Doctor & Department:</label>  
				   <s:select id="doctor" name="doctor" list="Docspecialitylist" listValue="doctor" listKey="id"
									headerKey="" headerValue="Select Doctor" onchange="checkdoctordiary(this.value)"
									title="Select Doctor form list" theme="simple"
									cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"/>
			</div>
			
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding " style="margin-top: 20px;" >
			  <input type="button" value="Set Diary" id="setbtn" class="btn btn-info active" onclick="setdoctordiary(0)">
		 </div>	
			
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding " style="margin-top: 25px;" >
			<label>Check for Follow Up</label>  
		           <s:checkbox name="followup" id="followup" label="followup"/>
			</div>
			
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding hidden" >
			    <label>Enter Opd Count</label> 
			  <input type="number" class="form-control" id="patientno" name="patientno" placeholder="OPD Patient Count" onchange="setopdcount(this.value)">
			</div>
			
			<!-- <div class="form-group hidden" style="margin-top: 2%;">
			<input type="submit" value="Go" onclick="return validate()" id="gobtn" class="btn btn-primary">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div> -->
			<div class="form-group" style="margin-top: 2%;">
			<input type="submit" value="Go" id="gobtn"  class="btn btn-primary">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
		
	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable newlok" style="width:100%;border: 2px solid !important" >
								<tr>
								<td style="width: 2%;" class="hidden-print hidden">
                                    <input type="checkbox" id="selectalliventorystockid" onclick="selectAllCheckBox(this)" />
                                </td>
								<th>Sr.</th>
								<th>Practitioner Name</th>
								<th class="hidden">Department</th>
								<th>Opd Capacity</th>
								<th class="hidden">Ipd Capacity</th>
								<th>As per norms(opd)</th>
								<th class="hidden">As per norms(Ipd)</th>
								
								</tr>
								<%int i=0; %>
								<s:iterator value="Docspecialitylist">
								<tr >
								 <td class="hidden-print hidden">
                                   	<input type="checkbox" class="case" value="<s:property value="id"/>" class="form-control" />
                                </td>
								<td><%=++i %></td>
								
								<td><s:property value="Doctor"/></td>
								<td class="hidden"><s:property value="Speciality"/></td>
								<td style="width: 5px;"><input type="number" class="form-control" name="patientcount<s:property value="id"/>" onchange="checkdoctordiary(<s:property value="id"/>)"/></td>
								<td style="width: 5px;" class="hidden"><input type="number" class="form-control" name=" " /></td>
								<td>15 per day</td>
								<td class="hidden">10 per day</td>
				                <td class="hidden"><input type="hidden" name="doctor<s:property value="id"/>" value="<s:property value="id"/>"></td>

								
								</s:iterator>
								</table>
</div>								



	
	</s:form>
	</div>
	

</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
<s:form action="ipdFakePatientIpdConsultation" theme="simple" id = "invoicerportfrm">
<input type="hidden" id="islmh" value="1">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	
	          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
				<label>Select Date</label>
				   <s:textfield  name="FromDate" readonly="true" cssClass="form-control" id="commencing" onchange="" />
			</div>
	
	
		
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
			     <label>Select Doctor & Department:</label>    
				       <s:select id="doctor" name="doctor" list="Docspecialitylist" listValue="doctor" listKey="id"
							headerKey="" headerValue="Select Doctor"
									title="Select Doctor form list" theme="simple"
									cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"/>
				 </div>					
			
			  <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
			    <label>Enter Ipd Count</label> 
			      <input type="number" class="form-control" id="patientno1" name="patientno1" placeholder="IPD Patient count">
			  </div>
		
		   
		    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 "style="margin-top: 2%;" >
		         <label>Check for OT</label>  
		         <s:checkbox name="otcheck" id="isot"/>
		    </div>
		   
		   
			<div class="form-group" style="margin-top: 2%;">
			<input type="submit" value="Go" onclick="return validate()" class="btn btn-primary">
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		
	</div>
	</s:form>
	</div>
	

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

function validate(){
	
	if(document.getElementById('islmh')){
		$("#newloaderPopup").modal('show');
	}else{
		document.getElementById("yuviloader").style.display="";
	}
	return true;
	
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