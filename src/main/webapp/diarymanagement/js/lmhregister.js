function showAllPatientPopUp(){
var url = "Client";
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
			var data=req.responseText;
			var data=data.replace(/setClientName/g,"editclientname");
			document.getElementById("allPatient").innerHTML = data;
//			jQuery.noConflict(); 
			$('#clientSearchDiv').modal( "show" );
			 setTimeout('clickontextbox();',1000);
         }
	}
}

function showAllUserPopUp(){
	
	var url = "searchbyEmpcodeClient";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showAllUserPopUpRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function showAllUserPopUpRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var data=req.responseText;
			 var data=data.replace(/setUserName/g,"editusername");
			 document.getElementById("alluser").innerHTML = data;
			$('#employeeSearchDiv').modal( "show" );
			 setTimeout('clickontextbox();',1000);
         }
	}
	
}

function clickontextbox() {
	document.getElementById('searchText').focus();
//	document.getElementById('searchText').click();
}
function searchPatient(){
	var searchText = document.getElementById("searchText").value;
	var url = "searchPatientClient?searchText="+searchText+"";

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
				
				var data=req.responseText;
				
			var data=data.replace(/setClientName/g,"editclientname");
			document.getElementById("allPatient").innerHTML = data;
	         	if(document.getElementById("langbyusr")){
				var language = document.getElementById("langbyusr").value;
				  var selectField = document.querySelector("#google_translate_element select");
				  for(var i=0; i < selectField.children.length; i++){
				    var option = selectField.children[i];
				    // find desired langauge and change the former language of the hidden selection-field 
				    if(option.value==language){
				       selectField.selectedIndex = i;
				       // trigger change event afterwards to make google-lib translate this side
				       selectField.dispatchEvent(new Event('change'));
				       break;
				    }
				  }
				}
	         }
		}
	}
	
function searchEmployee(){
	var searchText = document.getElementById("empsearchText").value;
	var url = "searchEmployeeClient?searchText="+searchText+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchEmployeeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function searchEmployeeRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			    var data=req.responseText;
			    var data=data.replace(/setUserName/g,"editusername");
			    document.getElementById("alluser").innerHTML = data;
			    if(document.getElementById("langbyusr")){
					var language = document.getElementById("langbyusr").value;
					  var selectField = document.querySelector("#google_translate_element select");
					  for(var i=0; i < selectField.children.length; i++){
					    var option = selectField.children[i];
					    // find desired langauge and change the former language of the hidden selection-field 
					    if(option.value==language){
					       selectField.selectedIndex = i;
					       // trigger change event afterwards to make google-lib translate this side
					       selectField.dispatchEvent(new Event('change'));
					       break;
					    }
					  }
					}
	         }
		}
	}
	

function editclientname(id,type,typeName,payby,gpid,planid,activeplan,regularpatient){
	
    document.getElementById("selectedid").value=id;
    document.getElementById('editclientfrm').submit();
    
    /*	document.getElementById("regularpatients").value=regularpatient;
    	alert("hello"+document.getElementById("regularpatients").value);*/
    	//regularpatientidss==regularpatient;
    /*	document.getElementById("plan"+activeplan).disabled=false;
    	document.getElementById("plan"+activeplan).disabled=false;
    	document.getElementById("plan"+activeplan).disabled=false;*/
    
   /* var plan="plan"+activeplan;
	document.getElementById(plan).disabled="";*/
	//alert(document.getElementById(plan).disabled);
 
}


function editusername(id,typeName){
	 document.getElementById("selectedid1").value=id;
	 document.getElementById('edituserfrm').submit();
	}

function changeInvoiceNote(val){
	if(val=='Cash'){
		document.getElementById("invNoteBox").style.display='none';
		document.getElementById("invoiceNote").value="";
	}else{
		document.getElementById("invNoteBox").style.display='';
		document.getElementById("invoiceNote").value="";
	}
}

function searchChargeByEmpCode() {
	var searchcode=document.getElementById("searchcode").value;
	if(searchcode!=''){
		var url="searchempcodeClient?searchcode="+searchcode+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = searchChargeByEmpCodeRequest;
		req.open("GET", url, true); 
		req.send(null);
	}
	
}

function searchChargeByEmpCodeRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		var data= req.responseText;
		var data1=data.split("~~");
		/*document.getElementById('selectedid1').value=data1[0];
		document.getElementById('type').className="";
		document.getElementById('type').value=data1[1]
		document.getElementById('type').className="form-control showToolTip chosen-select";
		$("#type").trigger("chosen:updated");
		$(".chosen-select").chosen({allow_single_deselect: true});
	  	document.getElementById('typeName').className="";
	  	document.getElementById('typeName').value=data1[2]
		document.getElementById('typeName').className="form-control showToolTip chosen-select";
		$("#typeName").trigger("chosen:updated");
	  	$(".chosen-select").chosen({allow_single_deselect: true});*/
		if(data1[0]==0){
			 jAlert('error', 'Employee Code not found!!!', 'Error Dialog');
	 		 setTimeout(function() {
			 $("#popup_container").remove();
			 removeAlertCss();
			 }, alertmsgduration);
		}else{
		document.getElementById("selectedid1").value= data1[0];
		document.getElementById("type").className ="";
		document.getElementById("type").value= data1[1];
		document.getElementById("type").className ="form-control chosen-select";
		$("#type").trigger("chosen:updated");
	  	$(".chosen-select").chosen({allow_single_deselect: true});
	  	
	  	document.getElementById("typeName").className ="";
		document.getElementById("typeName").value= data1[2];
		$("#typeName").trigger("chosen:updated");
	  	$(".chosen-select").chosen({allow_single_deselect: true});
	  	
	  	document.getElementById('edituserfrm').submit();
	}
	  	
     }
  }
}


function savenewlocation() {
	var location = document.getElementById("addlocation").value;
	
	if (location == '') {
		jAlert('error', 'Please enter procedure name', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	
	} else {
		var dataObj = {
			"location" : "" + location + ""
		};
		var data1 = JSON.stringify(dataObj);

		$.ajax({

			url : "addnewlocationRegistration",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			// beforeSend: function () { LockScreen(); },
			success : function(res) {
				$( "#addlocationpopup" ).modal( "hide" );	
				//var vall = document.getElementById("otdepartment").value;
				showlocationList();
				
			},
			error : function(result) {
				console.log("error");
			}
		});

	}
}
function showlocationList(){
		var url = "locationlistRegistration";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showolocationListRequest;
		    req.open("GET", url, true); 
		    req.send(null);
	}
	
	function showolocationListRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('camplocationdiv').innerHTML = req.responseText;
				
				 $("#camplocation").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			}
		}
	}
	
	
	
function saveclientType() {
	var clienttype = document.getElementById("addclient").value;
	
	if (clienttype == '') {
		jAlert('error', 'Please enter clienttype', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	
	} else {
		var dataObj = {
			"clienttype" : "" + clienttype + ""
		};
		var data1 = JSON.stringify(dataObj);

		$.ajax({

			url : "addnewclienttypeRegistration",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			// beforeSend: function () { LockScreen(); },
			success : function(res) {
				$( "#addlocationpopup" ).modal( "hide" );	
				//var vall = document.getElementById("otdepartment").value;
				clientTypeList();
				
			},
			error : function(result) {
				console.log("error");
			}
		});

	}
}
function clientTypeList(){
		var url = "clientTypeRegistration";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showolocationListRequest;
		    req.open("GET", url, true); 
		    req.send(null);
	}
	
	function showolocationListRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('clienttypediv').innerHTML = req.responseText;
				
				 $("#camplocation").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			}
		}
	}