<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="diarymanagement/js/checkAvailability.js"></script>
<script>
$( "button" ).button();
$(document).ready(function(){
	$(function() {
		/* $( "#allAvailabilityPopup" ).dialog({
			autoOpen: false,
			resizable: false,
			height:450,
			width:550,
			modal: true,
			buttons: {
				
				Cancel: function() {
					$( this ).dialog( "close" );
				},
				
			}
		}); */
 
	 $( "#avlbltyDate").datepicker({
		 
		 	 dateFormat:'dd/mm/yy',
		 	yearRange: yearrange,
		 	minDate : '30/12/1880',
			 changeMonth: true,
		     changeYear: true	 
	 });
	 
	 
	 
	 
	
	});	 
	 
});		

</script>


    <div class="row">
    <div class="col-lg-1 col-md-1">
    <b>Search:</b>
    </div>
    <div class="col-lg-3 col-md-3" >
	<s:textfield name="avlbltyDate" id = "avlbltyDate" cssClass="form-control" theme="simple"></s:textfield>
	</div>
	<div class="col-lg-3 col-md-3">
	<s:if test="%{#userList != 'null'}" >
		<s:select cssClass="form-control" id="chdiaryUser" name="diaryUser" list="userList" listKey="diaryUser" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select Practitioner" onchange="setSelectedDiaryUser(this.value)"   />
	</s:if>
	</div>
	<div class="col-lg-3 col-md-3">
	<s:if test="%{#locationList != 'null'}" >
		<s:select cssClass="form-control" id="chlocation" name="location" list="locationList" listKey="location" listValue="location" headerKey="0" theme="simple" headerValue="Select Location" onchange="setApmtLocation(this.value)"   />
	</s:if>
	</div>
	<div class="col-lg-2 col-md-2">
	 <button onclick="checkAvailabilityAjax()" class="btn btn-primary">Go</button>
	</div> 
 </div>
 

<hr> 

<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table id="checkavlbltytable" class="table table-hover table-condensed table-bordered">

				</table>
			</div>
		</div>
</div>





<!-- Appointment Availability Modal -->

<div class="modal fade" id="allAvailabilityPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">All Availability</h4>
      </div>
      <div class="modal-body">
      	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table id="allAvailabilitytable" class="table table-hover table-condensed table-bordered">
      	

				</table>
				</div>
			</div>
		</div>
		
	   </div>
      <div class="modal-footer">
    
      	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="setClientDidNotComeConfirm();">Ok</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>	