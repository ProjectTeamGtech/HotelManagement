<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib uri="/struts-tags" prefix="s" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>

<SCRIPT type="text/javascript">

   window.onload =function() {
           
            var today = new Date();
			var date = today.getDate();
			var maonth = today.getMonth();
			var year = today.getFullYear(); 
         
          $( "#dob" ).datepicker({
		 
		 		 dateFormat:'dd/mm/yy',
				 minDate : '30/12/1880',
				 yearRange: yearrange,
				 maxDate: today,
				 changeMonth: true,
	    		 changeYear: true
		 
   			});
         
         
         
         
         
         
         
          $('#addPatientEq').modal( "show");
   };
   
   function dosubmit(){
   
       var title=document.getElementById("title").value;
	var firstName=document.getElementById("firstName").value;
	var middleName=document.getElementById("middleName").value;
	var lastName=document.getElementById("lastName").value;
	var condition=document.getElementById("treatmentType").value;
    var gender=document.getElementById("gender").value;    	
	var dob=document.getElementById("dob").value;
	var address=document.getElementById("address").value;	
	var mobNo=document.getElementById("mobNo").value;
	var email=document.getElementById("email").value;
	
	document.getElementById('fnameError').innerHTML = "";
	document.getElementById('lnameError').innerHTML = "";
	var chk=0;
	
	var regEx = /^[0123456789]\d{10}$/;
	    var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		if(firstName == ""){
			var firstn = document.createElement("label");
			firstn.innerHTML = "Please Enter First Name";
		    document.getElementById('fnameError').appendChild(firstn);
		    chk=1;
		}
		if(firstName.match(/\s/g)){
			var firstn = document.createElement("label");
			firstn.innerHTML = "Space Not Allow";
		    document.getElementById('fnameError').appendChild(firstn);
		    chk=1;
		}
		if(address==""){
			var add = document.createElement("label");
			add.innerHTML = "please enter address";
		    document.getElementById('addressError').appendChild(add);
		    chk=1;
		}
		
		if(email == ""){
			var em = document.createElement("label");
			em.innerHTML = "Please Enter Email";
		    document.getElementById('emailError1').appendChild(em);
		    chk=1;
		}
		
		if(gender == "0"){
			var gen = document.createElement("label");
			gen.innerHTML = "Please Select Gender";
		    document.getElementById('genError').appendChild(gen);
		    chk=1;
		}
		
		
		if(lastName == ""){
			var lastName = document.createElement("label");
			lastName.innerHTML = "Please Enter Last Name";
		    document.getElementById('lnameError').appendChild(lastName);
		    chk=1;
		}
        if(condition == "0"){
			var con = document.createElement("label");
			con.innerHTML = "Please Select Condition";
		    document.getElementById('conditionError').appendChild(con);
		    chk=1;
		}
 

		if(dob == ""){
			var  doctorName= document.createElement("label");
			doctorName.innerHTML = "Please Select Date of Birth";
		    document.getElementById('dobError').appendChild(doctorName);
		    chk=1;
		}
        		
		if(chk==1) {
		
			 return false;
		}	
		else {
		     document.getElementById("patientFormeq").submit();
					   	
		}
   
   }
   
</SCRIPT>

</head>
<body>


    
</body>

    
    <!-- Add New Patient -->
	<div class="modal fade" id="addPatientEq" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body" id="newpaa">
					<%@ include file="/eq/pages/registereq.jsp"%>
					
				</div>
				 <div class="modal-footer">
					 <button type="button" class="btn btn-primary" onclick="dosubmit()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div> 
			</div>
		</div>
	</div>
	
     
</html>

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
  </script>

