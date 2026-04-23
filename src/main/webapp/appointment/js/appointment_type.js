

function checkAAPpointmentTypeExist(selectedid){
	
	 
	 var url = "checkAppointmentType?selectedid="+selectedid+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = checkAAPpointmentTypeExistRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
}

function checkAAPpointmentTypeExistRequest(){
	
	 if (req.readyState == 4) {
			if (req.status == 200) {
				var result = req.responseText;
				result=  'true';
				if(result=='true'){
					alert('cant delete');
					return false;
				}
			}
	 }
}

function confirmedDelete(){

var r=confirm("Are you sure you want to delete this entry");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }

}

function changeDiv(){
	
	var chargeType = document.getElementById('chargeType').value;
	//alert(chargeType);
	if(chargeType == 'Appointment Charge'){
		document.getElementById('hiddenDiv').style.display = '';
			
	}
	else{
		document.getElementById('hiddenDiv').style.display = 'none';
}
}

function validEntry (){
	
	//alert(document.getElementById('reportField').checked);
	var chk=0;
	document.getElementById('chargeTypeError').innerHTML= "";
	var chargeType = document.getElementById('chargeType').value;
	var charge = document.getElementById('charges').value;
	var duration =  document.getElementById('duration').value;
	var name = document.getElementById('name').value;
	var code = document.getElementById('code').value;;
	
	var regEx =/^-?\d*\.?\d*$/;
	
	document.getElementById('chargeTypeError').innerHTML="";
    document.getElementById('chargesError').innerHTML="";
    document.getElementById('nameError').innerHTML="";
    document.getElementById('codeError').innerHTML="";
    
    var data1 = document.getElementById('chargesError').innerHTTML;
    
	if (chargeType ==  "0") {
		var chargeType = document.createElement("label");
        chargeType.innerHTML = "Select Charge Type"; 
        document.getElementById('chargeTypeError').appendChild(chargeType);
        chk=1;
     }  
	if (charge ==  "") {
        var charge = document.createElement("label");
        charge.innerHTML = "Enter Charge Amount"; 
        document.getElementById('chargesError').appendChild(charge);
        chk=1;
     }  
	if(chk==0 && !regEx.test(charge)){
		var charge = document.createElement("label");
        charge.innerHTML = "Charge Amount should be Numeric."; 
        document.getElementById('chargesError').appendChild(charge);
        chk=1;
	}
	if (name ==  "") {
        var name = document.createElement("label");
        name.innerHTML = "Enter Name"; 
        document.getElementById('nameError').appendChild(name);
        chk=1;
     }  
	
	/*if(code!="" &&  !regEx.test(code)){
		 var name = document.createElement("label");
	     name.innerHTML = "Code should be Numeric."; 
	     document.getElementById('codeError').appendChild(name);
	     chk=1;
	}*/
	
	
	if(chargeType == 'Appointment Charge'){
		document.getElementById('durationError').innerHTML="";
		if (duration ==  "0") {
	        var duration = document.createElement("label");
	        duration.innerHTML = "Select duration"; 
	        document.getElementById('durationError').appendChild(duration);
	        chk=1;
	     }  
	}
	
     if(chk==1)
     {
        /*return false;*/
     }
     else
     {
    	if(document.getElementById("editapmtid")){
    		document.getElementById("appointType_form").submit();
    	}else{
    		if(code!=''){
        		validateCodeDuplocation();
        	}else{
        		 /*return true;*/
        		document.getElementById("appointType_form").submit();
        	}
    	}
     }
}

function validateCodeDuplocation(){
	var id=0;
	if(document.getElementById("editapmtid")){
		id = document.getElementById("editapmtid").value;
	}
	var code = document.getElementById('code').value;;
	var url = "validateCodeDuplicateBookAppointmentAjax?code="+code+"&id="+id+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = validateCodeDuplocationRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function validateCodeDuplocationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
	          var data =req.responseText;
	          if(data==1){
	        	 var name = document.createElement("label");
	     	     name.innerHTML = "Code already present."; 
	     	     document.getElementById('codeError').appendChild(name);
	          }else{
	        	  document.getElementById("appointType_form").submit();
	          }
       }
	}	 
}

function addappointmenttypeRow(tableID){
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	var url = "addnewrowAppointmentType?tableid="+table+"&rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addappointmenttypeRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function addappointmenttypeRowRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
	          row.innerHTML=req.responseText;	
       }
	}	 
}


function searchpriscmaster(name){
	var url = "searchbyajaxpriscPrescriptiondetails?searchtext="+name;
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchpriscmasterRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function searchpriscmasterRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innertablep").innerHTML=req.responseText;	
       }
		}	 
	

}
function updatebreakage(val,check){
	var url = "updateBreakageNewChargeType?id="+val+"&checked="+check;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatebreakageRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function updatebreakageRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		windows.reload();
		
		}
	}
}
function setAll(l) {
	
	  var chk=0;
	   if(l.checked==true){
		   
		   $('.case').each(function() {
			   
			     this.checked=true;
			    chk=chk+","+this.value;
		   });
		   
	   } else {
		   $('.case').each(function() {
			   
			     this.checked=false;
			     chk=0;
		   });
		   
		   
	   }
	   var url = "checkedAllBreakageNewChargeType?allval="+chk;
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkedAllBreakageRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}

	function checkedAllBreakageRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			
			}
		}
	
}
	function setnoneditable(val,check){
		var url = "setnoneditableAppointmentType?id="+val+"&checked="+check+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setnoneditableRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}

	function setnoneditableRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			
			}
		}
	}
	function searchcreatecharge(name,viewaccess){
		var url = "searchcreatechargeAppointmentType?searchtext="+name+"&viewaccess="+viewaccess+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = searchcreatechargeRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	function searchcreatechargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("tblchrg").innerHTML=req.responseText;	
	       }
			}	 
		

	}
