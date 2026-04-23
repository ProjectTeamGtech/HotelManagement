<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>

.borderbot{
	border-bottom: 2px solid #000;
    padding-top: 15px;
    padding-bottom: 15px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}




</style>

<script>
	function savecasualty(){
		if(document.getElementById('title1').value==0){
			jAlert('error', 'Please select title.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('fname').value==''){
			jAlert('error', 'Please enter first name.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('lname').value==''){
			jAlert('error', 'Please enter last name.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('age').value==''){
			jAlert('error', 'Please enter age.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('gender').value==0){
			jAlert('error', 'Please select gender.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('address').value==''){
			jAlert('error', 'Please select address.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('mob').value==''){
			jAlert('error', 'Please select phone no.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('provdiag').value=='0'){
		
			jAlert('error', 'Please select Prov. Diagnosis.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('recbelonging').value=='0'){
			jAlert('error', 'Please select Records | Belonging.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('drincharge').value=='0'){
			jAlert('error', 'Please select Dr.Incharge.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(document.getElementById('mlc').value=='0'){
			jAlert('error', 'Please select MLC | Emplanelled.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}/* else if(document.getElementById('typeofdis').value=='0'){
				jAlert('error', 'Please select Type Of Discharge.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		} */
		
		
		else{
			document.getElementById('savefrm').submit()
		}
	}
</script>
<%String x="";
if(x.equals("")){
	System.out.println("");
}
%>

</head>
<body>

<s:form action="editsaveCasualty" theme="simple" id="savefrm">
<s:hidden name="id"/>
	<div class="col-lg-12 col-xs-12 col-md-12">
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
					<div class="clinicname"><s:property value="clinicName"/></div>
					<div class="clicniaddress"> <s:property value="clinicaddress"/><br></div>
					<div class="bottext mabset">Tel/Fax:<s:property value="clinicPhone"/>, E: <s:property value="clinicemail"/>, W: <s:property value="websiteUrl"/><br></div>
			<%-- <div class="col-lg-10 col-md-10 col-xs-8 col-sm-8" style="padding-left:0px;padding-right:0px;">
				 <div class="clinicname"><s:property value="clinicName"/></div>
					<div class="clicniaddress"> <s:property value="clinicaddress"/><br></div>
						<div class="bottext mabset">Tel/Fax:<s:property value="clinicPhone"/>, E: <s:property value="clinicemail"/>, W: <s:property value="websiteUrl"/><br></div>
			</div> --%>
			<%-- <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4 text-right" style="padding-left:0px;padding-right:0px;">
				<p style="margin: 0px;"><b>Sr.No :</b> &nbsp;<span class="">01</span></p>
				<p style="margin: 0px;"><b>Yearly No :</b>&nbsp; <span class="">1701</span></p>
				<p style="margin: 0px;"><b>Patient ID :</b>&nbsp; <span class="">091334</span></p>
				<p style="margin: 0px;"><b>Date :</b> &nbsp;<span class="">04/03/2017</span></p>
				<p style="margin: 0px;"><b>Time:</b> &nbsp;<span class="">12:28 PM</span></p>
			</div> --%>
			
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;border-bottom: 1px solid #ddd;">
		
		<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 10px 0px 0px 0px;background-color: #f5f5f5;">
			
			<div class="col-lg-1 col-xs-1 col-md-1 hidden-print">
					<div class="form-group">
					    <label for="exampleInputEmail1">Title</label>
					    <s:select  id="title1" name="title" list="initialList"  theme="simple" cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  headerKey="0" headerValue="Select"/>
					  </div>
				</div>
			
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">First Name</label>
					   <s:textfield name="fname" id="fname" cssClass="form-control"/>
					  </div>
				</div>
				
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Last Name</label>
					     <s:textfield name="lname" id="lname" cssClass="form-control"/>
					  </div>
				</div>
			
				
				
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Age | Gender</label>
					    <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
					    	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
					    <s:textfield onkeypress="return isNumberKey(event,this);" name="age" id="age" cssClass="form-control"/>
					    </div>
					    <div class="col-lg-6 col-md-6 col-xs-6" style="padding: 0px;">
					    	<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip chosen-select "
									data-toggle="tooltip" title="Select Gender"  headerValue="Select"/>											
					    </div>
					    </div>
					  </div>
					  
				</div>
				<div class="col-lg-2 col-xs-3 col-md-4">
					<div class="form-group">
					    <label for="exampleInputEmail1">Address</label>
					    <s:textfield name="address" id="address" cssClass="form-control"/>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Phone No</label>
					    <s:textfield name="mob" id="mob" cssClass="form-control"/>
					  </div>
				</div>
			
			</div>
		
		</div>
		
		<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Prov. Diagnosis</label>
					    <s:select name="provdiag" id="provdiag" list="diagnosisList"
					    listKey="id" listValue="treatmentType" cssClass="form-control showToolTip chosen-select"
					    headerKey="0" headerValue="Select Condition"
					    />
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Admission Type</label>
					     <s:select id="recbelonging" name="recbelonging" list="{'admit on IPD basis','admit on OPD basis','admit on OT basis'}"  theme="simple" cssClass="form-control showToolTip chosen-select "
									data-toggle="tooltip" title="Select Prov Diagnosis"  headerKey="0" headerValue="Select"/>
					   
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Dr.Incharge</label>
					   <s:if test="%{#userList != 'null'}">
										<s:select cssClass="form-control chosen-select"
											id="drincharge" name="practitionerid" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Practitioner"
											 />
											
									</s:if>

					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">MLC | Emplanelled</label>
					    <s:select id="mlc" name="mlc" list="{'No','Yes','Not Required'}"  theme="simple" cssClass="form-control showToolTip chosen-select "
									data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>	
					   
					  </div>
				</div>
				
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">MLC No</label>
					   <s:textfield name="mlcno" id="mlcno" cssClass="form-control"/>
					  </div>
				</div>
				
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Reffered By</label>
					    <s:if test="%{#userList != 'null'}">
										<s:select cssClass="form-control chosen-select"
											id="refby" name="refferedby" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Practitioner"
											 />
											
									</s:if>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Type Of Discharge</label>
					    
					    <s:select cssClass="form-control chosen-select"  list="#{'Shifted to IPD':'Shifted to IPD','Shifted to OT':'Shifted to OT','Shifted to OPD':'Shifted to OPD'}" theme="simple" name="typeofdis" id="typeofdis" 
					    headerKey="0" headerValue="Select"/>	
					  </div>
				</div>
			
			</div>
		
		</div>
		
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;">
		
		<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-6 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Note</label>
					   <s:textarea name="note" id="note" cssClass="form-control"/>
					  </div>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-2" style="text-align:right">
					<div class="form-group">
					    <label for="exampleInputEmail1">Signature of Patient or Representative Relationship</label><br><br><br><br>
					     -----------------------------------------------
					  </div>
				</div>
				
				
				
			
			</div>
		
		</div>
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row hidden-print">
		<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
						<a href="#" onclick="myFunction()" class="btn btn-primary">Print</a>
						<input type="button" class="btn btn-primary" value="Save" onclick="savecasualty()">
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
</div>
</s:form>
<%String y="";
if(y.equals("")){
	System.out.println("");
}
%>

<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>

<script>
	function myFunction() {
	    window.print();
	}
</script>
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
  
</body>
</html>