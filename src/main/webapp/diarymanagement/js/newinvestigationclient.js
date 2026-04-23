/**
 * 
 */

function savePatient() {
	
	var button= document.getElementById("invstibtn");
	button.style.display = "none";
	var title=document.getElementById("title").value;
	var firstName=document.getElementById("firstName").value;
	var middleName=document.getElementById("middleName").value;
	var lastName=document.getElementById("lastName").value;
    	
	var age=document.getElementById("age").value;
	var address=document.getElementById("address").value;	
	var mobNo=document.getElementById("mobNo").value;
	var email=document.getElementById("email").value;
    var doctorName=document.getElementById("doctorname").value;    	
	var state= document.getElementById("state").value;
	var town= document.getElementById("town").value;
	var dob= document.getElementById("dob").value;
	var gender = document.getElementById("gender").value;
	var department=document.getElementById('diciplineName').value;
	
	document.getElementById('fnameError').innerHTML = "";
	document.getElementById('lnameError').innerHTML = "";
	document.getElementById('doctorError').innerHTML="";
	document.getElementById('stateError').innerHTML="";
	document.getElementById('ageError').innerHTML="";
	document.getElementById('townError').innerHTML="";
	document.getElementById('dobError').innerHTML="";
	if(document.getElementById('genderError1')){
		document.getElementById('genderError1').innerHTML="";
	}
	const reg = new RegExp('^[0-9]+$');
	var chk=0;
	
	var regEx = /^\d{10}$/;
	    var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


		if(title == ""){
				jAlert('error', "Please Select Title!", 'Error Dialog');
				//$.alerts.alert('info', "Please Select Title!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			},alertmsgduration );
		    chk=1;
		}else if(firstName == ""){
				jAlert('error', "Please Enter First Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(!/^[a-zA-Z]*$/g.test(firstName)){
			
				jAlert('error', "No Special Character or Number Allowed in First Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(!/^[a-zA-Z]*$/g.test(middleName)){
				jAlert('error', "No Special Character or Number Allowed in Middle Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(lastName == ""){
				jAlert('error', "Please Enter First Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if( !/^[a-zA-Z]*$/g.test(lastName)){
				jAlert('error', "No Special Character or Number Allowed in Last Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		    
		}else if(dob == ""){
				jAlert('error', "Please Select DOB", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(age == ""){
				jAlert('error', "Please Enter Age", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(gender==''){
				jAlert('error', "Please Select Gender", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
				chk=1;
		}else if(!regEx.test(mobNo)) {
				jAlert('error', "Please Enter Valid Mobile No", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(mobNo.length!=10) {
			jAlert('error', "Please Enter 10 Digit Mobile No", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(email!="" && !emailregEx.test(email)){
			jAlert('error', "Please Enter Valid E-Mail Id", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(address == ""){
				jAlert('error', "Please Enter Address", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(state == "0"){
				jAlert('error', "Please Select State", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(town == "0"){
				jAlert('error', "Please Select City", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(doctorName == ""){
				jAlert('error', "Please Enter Doctor Name", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;
		}else if(department=='0'){
				jAlert('error', "Please Select Doctor Specialization", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		    chk=1;	
		}
		
			
		
		if(chk==1) {
			button.style.display = "";
			 return false;
		}	
		else {
			
			var title=document.getElementById("title").value;
			var firstName=document.getElementById("firstName").value;
			var middleName=document.getElementById("middleName").value;
			var lastName=document.getElementById("lastName").value;
		    	
			var age=document.getElementById("age").value;
			var address=document.getElementById("address").value;	
			var mobNo=document.getElementById("mobNo").value;
			var email=document.getElementById("email").value;
		    var doctorName=document.getElementById("doctorname").value;    	
			var state= document.getElementById("state").value;
			var town= document.getElementById("town").value;
			var dob= document.getElementById("dob").value;
			var gender = document.getElementById("gender").value;
			var department=document.getElementById('diciplineName').value;
			
			
			$("#baselayout1loaderPopup").modal("show");
		     document.getElementById("patientForm").submit();
					   	
		}
		 
}


function showPopUp(){
	
	var url = "showclientInvestigation";

	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllPatientPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showAllPatientPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				$('#clientSearchDiv').modal( "show" );	
	         }
		}
	}
	
	
	function setPatientName(id,type,age,gender,dname) {
		var name=document.getElementById("firstnameid"+id).value;
	 patientId = id;
	 
 diaryuserId = 0;
 editcondition_id = 0;
 editAppointId = 0;
 

	
		
		var n=name.split("/");
		var newname="";
		
		if(n.size==3){
			newname=n[0]+" "+n[1]+" "+n[2]; 
		}else{
			 newname=n[0]+" "+n[1]; 
		}
		
		
		var d=dname.split("/");
		var doctorname = '';
		for(i=0;i<d.length;i++){
			doctorname = d[0]+" "+d[1]+" "+d[2]; 
		}
		
		var data="Name: "+newname+" | Age: "+age+"/ "+gender+"";
		
	    document.getElementById("invstcmyModalLabel").innerHTML=data;
	    if(doctorname=='  undefined'){
	    	document.getElementById("invstpriscdoctornameid").innerHTML = "";
	    }else{
	    	document.getElementById("invstpriscdoctornameid").innerHTML = doctorname;
	    }
	    
	     document.getElementById("invstdatetimeid").innerHTML = document.getElementById("invstdateandtime").value;
	     
	    
		
		$('#investigationpopup').modal( "show" );

        $('#clientSearchDiv').modal( "hide" );	 		
		
	}
	
	
	
	function searchPatient(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientInvestigation?searchText="+searchText+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchPatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function searchPatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
	         	
				
	         }
		}
	}
	


function getCitiesajax(val) {
     var url="getcitiesajaxInventory?state="+val+"";
  
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getCitiesajaxRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 
 }
 
 function getCitiesajaxRequest(){
    if (req.readyState == 4) {
		if (req.status == 200) {
		   
		   document.getElementById("citydiv").innerHTML=req.responseText;
		    $("#town").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
         }
	}	 	
}

  function getStateAjax(val) {
     var url="getstateajaxInventory?city="+val+"";
  
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getStateAjaxRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 
 }
 
 function getStateAjaxRequest(){
    if (req.readyState == 4) {
		if (req.status == 200) {
		   document.getElementById("statediv").innerHTML=req.responseText;
         }
	}	 
}

function getStateAjaxnew(val) {
     var url="getstateajaxnewInventory?city="+val+"";
  
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getStateAjaxRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 
 }
 
 function getStateAjaxRequest(){
    if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("state").className="";
			  document.getElementById("state").value=req.responseText;
				 $("#state").trigger("chosen:updated");
					$(".chosen-select").chosen({allow_single_deselect: true});
		   
         }
	}	 
}



function setInvestigationtemplate(id){
  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvestigationtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setInvestigationtemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var onexamination= nicEditors.findEditor( "comment" ).getContent();	  
						onexamination=onexamination+req.responseText;        
						nicEditors.findEditor('comment').setContent(onexamination);	            
         }
	}	 
}


function setInvestigationtemplate1(id){
  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvestigationtemplate1Request;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setInvestigationtemplate1Request(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var onexamination= nicEditors.findEditor( "commentwriteup" ).getContent();	  
						onexamination=onexamination+req.responseText;        
						nicEditors.findEditor('commentwriteup').setContent(onexamination);	    
						//window.location.reload();        
         }
	}	 
}



