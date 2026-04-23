<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/events.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />

<script>

$(document).ready(function() {

	$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
});

var flag=0;

function setAll(){
   
   if(flag==0){
     
     $('.case').each(function() { //loop through each checkbox
		  		this.checked=true;
      }); 
      
      flag=1;
   }else {
      
     $('.case').each(function() { //loop through each checkbox
		  		this.checked=false;
      }); 
      flag=0;
   }
      
}


</script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Events</h4>
								</div>
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="saveEvents" id="eventsform" theme="simple">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12" style="margin-top: 10px;">
<div class="col-lg-4 col-md-4 col-sm-4">
<div>
  <div class="form-group">
    <label for="exampleInputEmail1">Event Title</label>
   <s:textfield id="eventname" name="eventname" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Event Name" placeholder="Enter Event Name" />
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Description</label>
   <s:textarea id="description" name="description" rows="5" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Description" placeholder="Enter Description"/>
  </div>
  
  <div class="row">
  <div class="col-lg-12 col-md-12" style="margin-bottom: 15px;">
  <label for="exampleInputEmail3">Event Time</label>
  <div class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">Event Time</label>
    <s:select list="timelist" cssClass="form-control" name="time1" id="time1" headerValue="00:00" headerKey="00:00"></s:select>
  </div>
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3"></label>
     <select id="time2" name="ampm" class="form-control">
				 <option value="am">am</option>
				 <option value="pm">pm</option>
				</select>
  </div>
</div>
  </div>
  </div>
  <div class="row">
  <div class="col-lg-12 col-md-12">
  <div class="col-lg-6 col-md-6" style="padding-left: 0px;">
  <div class="form-group">
    <label for="exampleInputPassword1">From Date</label>
   <s:textfield id="fromdate" name="fromdate" title="Select From Date" cssClass="form-control"></s:textfield>
  </div>
  </div>
  <div class="col-lg-6 col-md-6" style="padding-right: 0px;">
  <div class="form-group">
    <label for="exampleInputPassword1">To Date</label>
   <s:textfield id="todate" name="todate" title="Select To Date" cssClass="form-control"></s:textfield>
  </div>
  </div>
  </div>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Place</label>
   <s:textfield id="place" name="place" title="Enter Place" data-toggle="tooltip" placeholder="Enter Place" cssClass="form-control"></s:textfield>
  </div>
  <div class="row">
		<div class="col-lg-12 col-md-12">
			<input type="button" class="btn btn-primary" value="Save" onclick="submitData()" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Events" class="btn btn-primary">Back</a>
		</div>
	</div>
</div>
</div>
<div class="col-lg-4 col-md-4 col-sm-4">
<div class="form-group">
    <label for="exampleInputPassword1">Job Title</label><br>
    <input type="checkbox" id="" onclick="setAll()"> Select All <br> 
   <s:iterator value="jobtitleList">
				         <s:if test="checked==true">
				             <input class="case" type="checkbox" value="<s:property value="id"/>" checked="checked"/> <s:property value="jobtitle"/> <br>
				         </s:if>
				         <s:else>
				            <input class="case" type="checkbox" value="<s:property value="id"/>"/> <s:property value="jobtitle"/> <br>
				         </s:else>
				     </s:iterator>
  </div>
  
</div>
<div class="col-lg-4 col-md-4 col-sm-4">
</div>
</div>
	 <s:hidden name="jobtitle" id="jobtitle"></s:hidden>
				<s:hidden name="time" id="time"></s:hidden>
				<s:hidden name="id" id="id"></s:hidden>
	
	
</div>




	
	<s:token></s:token>
</s:form>
