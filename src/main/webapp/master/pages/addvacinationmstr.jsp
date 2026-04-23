<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>

	<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
</div><h4>&nbsp;&nbsp; Add Vaccination </h4>
</div>
	<div class="row ">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<br>									


<s:form action="savevaccinationmasterMaster" theme="simple" id='vtm'>
<div class="form-inline">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="border-right:4px solid">
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Vaccine Group</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			 <s:select list="parentvacinationlist" headerKey="" headerValue="Seletc Group" id="vacine_parent" name="vacine_parent" listKey="parentid" listValue="vacine_parent"  cssClass="form-control chosen-select"></s:select>			   
			</div>
			<br><br>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Vaccine Type</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
				<s:select name="type"
				list="#{'0':'Child','1':'Gynic','2':'Nephrology'}"
				cssClass="form-control chosen-select" ></s:select>
				</div>
			<br><br><br>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Vaccine Name</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<s:textfield id="vacinname" name="vacinname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Vaccination name" placeholder="Vaccination name"/>
		 	
			</div>
			<br><br>
			
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Depends On</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<s:select list="vacinationlist" onchange="setScheduleDays()" headerKey="" headerValue="Seletc Dependancy" id="dependupon" name="vacine_dependson" listKey="id" listValue="vacinname"  cssClass="form-control chosen-select" cssStyle="width:50%"></s:select>			   
			
			</div>
			
			<br><br>
			<br>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Scheduled on(days) Only If Depends on:</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<input type="number" id="depndscheduledon" name="depndscheduledon" class='form-control'  title="Only If Depends on">
			</div>
			
			
			
			<br><br>
			<br>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Scheduled on(days)</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<input type="number" name="scheduledon" class='form-control' required="required">
			</div>
			
			<br><br>
			
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Duration</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<input type="text" name="duration"  class='form-control'>
			</div>
			<br><br>
			
			
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Compulsary</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<s:checkbox name="vacine_iscompulsary"></s:checkbox>	YES	   
			</div>
			
			
			<br><br><br><br>
			</div>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"  >
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Exclude</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="sun" value="1">Sunday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="mon" value="2">Monday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="tue" value="3">Tuesday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="wed" value="4">Wednesday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="thu" value="5">Thursday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="fri" value="6">Friday
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		 	<input type="checkbox" name="sat" value="7">Saturday
			</div>
			
			</div>
				<br><br>	<br><br>
			<br><br>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
				
				<div class='col-lg-6 col-md-6 col-xs-6 col-sm-6'>
				<label>Vaccination For:</label>
				</div>
				<div class='col-lg-6 col-md-6 col-xs-6 col-sm-6'>
				<s:select name="gendervaccine"
				list="#{'0':'All','1':'Male','2':'Female'}"
				cssClass="form-control chosen-select" ></s:select>
				</div>
				
				</div>
				<p></p>
			<br><br>	
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<label>Information</label>
			</div>
			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9">
			<s:textfield id="vacine_info" name="vacine_info"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Vaccination info" placeholder="Vaccination info" style="width:100%;"/>
		 	
			</div>
			</div>
	
	
	</div>
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">	
	<%-- <s:submit cssClass="btn btn-primary" value="Save" ></s:submit>	 --%>
	<input type="button" class='btn btn-primary' value="Save" onclick="savevacci()">
	</div>	
</div>
</s:form>

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
    
    
    
    function savevacci(){
    	var name=document.getElementById('vacinname').value;
    	if(name==''){
    		alert("Name The Vaccine! ");
    	}else{
    		$('#baselayout1loaderPopup').modal( "show" );
    		document.getElementById('vtm').submit();
    		
    	}
    }
  </script>
  <script>
  function setScheduleDays(){
		var dependupon = document.getElementById("dependupon").value;
		if(dependupon==''){
			document.getElementById("depndscheduledon").value = 0;
			document.getElementById("depndscheduledon").readOnly = true;
		}else{
			document.getElementById("depndscheduledon").value = 0;
			document.getElementById("depndscheduledon").readOnly = false;
		}
	}
  $(document).ready(function() {
	 	var dependupon = document.getElementById("dependupon").value;
		if(dependupon==''){
			document.getElementById("depndscheduledon").value = 0;
			document.getElementById("depndscheduledon").readOnly = true;
		}
  });
</script>