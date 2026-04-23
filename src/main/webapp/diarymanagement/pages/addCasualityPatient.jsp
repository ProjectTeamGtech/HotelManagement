<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
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
.chosen-container-single .chosen-single span {
    display: none;
}
</style>
</head>
<body>
	<div class="col-lg-12 col-xs-12 col-md-12">
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<div class="clinicname"><s:property value="clinicName"/></div>
					<div class="clicniaddress"> <s:property value="clinicaddress"/><br></div>
						<div class="bottext mabset">Tel/Fax:<s:property value="clinicPhone"/>, E: <s:property value="clinicemail"/>, W: <s:property value="websiteUrl"/><br></div>
			<!-- <div class="col-lg-10 col-md-10 col-xs-8 col-sm-8" style="padding-left:0px;padding-right:0px;">
				 <div class="clinicname"><s:property value="clinicName"/></div>
					<div class="clicniaddress"> <s:property value="clinicaddress"/><br></div>
						<div class="bottext mabset">Tel/Fax:<s:property value="clinicPhone"/>, E: <s:property value="clinicemail"/>, W: <s:property value="websiteUrl"/><br></div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-4 col-sm-4 text-right" style="padding-left:0px;padding-right:0px;">
				<p style="margin: 0px;"><b>Sr.No :</b> &nbsp;<span class="">01</span></p>
				<p style="margin: 0px;"><b>Yearly No :</b>&nbsp; <span class="">1701</span></p>
				<p style="margin: 0px;"><b>Patient ID :</b>&nbsp; <span class="">091334</span></p>
				<p style="margin: 0px;"><b>Date :</b> &nbsp;<span class="">04/03/2017</span></p>
				<p style="margin: 0px;"><b>Time:</b> &nbsp;<span class="">12:28 PM</span></p>
			</div> -->
			
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding: 0px;border-bottom: 1px solid #ddd;">
		
		<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 10px 0px 0px 0px;background-color: #f5f5f5;">
				<div class="col-lg-4 col-xs-3 col-md-4">
					<div class="form-group">
					    <label for="exampleInputEmail1">Patient Name</label>
					    <input type="text" class="form-control">
					  </div>
				</div>
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Age | Gender</label>
					    <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
					    	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
					    <input type="text" class="form-control">
					    </div>
					    <div class="col-lg-6 col-md-6 col-xs-6" style="padding: 0px;">
					    	 <select class="form-control chosen-select">
				      			<option></option>
								<option>Male</option>
								<option>Female</option>
							</select>
					    </div>
					    </div>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-3 col-md-4">
					<div class="form-group">
					    <label for="exampleInputEmail1">Address</label>
					    <input type="text" class="form-control">
					  </div>
				</div>
				<div class="col-lg-2 col-xs-3 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Phone No</label>
					    <input type="text" class="form-control">
					  </div>
				</div>
			
			</div>
		
		</div>
		
		<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Prov. Diagnosis</label>
					    <select class="form-control chosen-select">
				      			<option></option>
								<option>asd</option>
								<option>asdad</option>
							</select>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Records | Belonging</label>
					     <select class="form-control chosen-select">
				     			<option></option>
								<option>pt. admit on IPD basis</option>
								<option>Pt. admit on OPD basis</option>
								<option>Pt. admit on OT basis</option>
							</select>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Dr.Incharge</label>
					     <select class="form-control chosen-select">
				      			<option></option>
								<option>Dr.ABC</option>
								<option>DR.BCD</option>
							</select>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">MLC | Emplanelled</label>
					    <select class="form-control chosen-select">
				      			<option></option>
								<option>No</option>
								<option>Yes</option>
								<option>Not Required</option>
							</select>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Reffered By</label>
					    <select class="form-control chosen-select">
				     			<option></option>
								<option>Dr.ABC</option>
								<option>DR.BCD</option>
							</select>
					  </div>
				</div>
				<div class="col-lg-2 col-xs-6 col-md-2">
					<div class="form-group">
					    <label for="exampleInputEmail1">Type Of Discharge</label>
					    <select class="form-control chosen-select">
				      			<option></option>
								<option>Shifted to IPD</option>
								<option>Shifted to OT</option>
								<option>Shifted to OPD</option>
							</select>
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
					    <textarea class="form-control" rows="3"></textarea>
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
						<a href="#" class="btn btn-primary">Save</a>
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
</div>

<script src="popupdialog/dialog/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="common/BootstrapNew/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
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