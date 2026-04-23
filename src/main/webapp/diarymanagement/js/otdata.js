var gggotcharge = 0;
var proceduredata='';
var charge=0;
var procedureid='';
function showotstaff(){
	resetot();
if(document.getElementById('client').value==''){
	document.getElementById('radio3').checked = false;
	jAlert('error', 'Please Select Client.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}else if(document.getElementById('apmtDuration').value==0){
	document.getElementById('radio3').checked = false;
	jAlert('error', 'Please Select Duration.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}

else{
	document.getElementById('otstaffdiv').style.display = '';
	checkOtDoctorAvailibility();
	
	
}


}

function checkOtDoctorAvailibility(){
	var location = document.getElementById('location').value;
	var sTime = document.getElementById('sTime').value;
	var endTime = document.getElementById('endTime').value;
	var commencing = document.getElementById('date').value;
	
	var url = "otdoctorNotAvailableSlot?location="+location+"&sTime="+sTime+"&endTime="+endTime+"&commencing="+commencing+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkOtDoctorAvailibilityRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function checkOtDoctorAvailibilityRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var selecteddoctor = document.getElementById('diaryUserId').value;;
			document.getElementById("otst"+selecteddoctor).checked = true;
			
			var otdoctorlist = req.responseText;
			var temp = otdoctorlist.split(',');
			
			for(i=0;i<temp.length;i++){
				document.getElementById("otst"+temp[i]).disabled = false;
			}
		
			
		}
	}
}




function checkotroom(){
     var radios = document.getElementsByName("otroom");

     for (var i = 0, len = radios.length; i < len; i++) {
          if (radios[i].checked) {
          var val = radios[i].id;
          	document.getElementById('selectedotroom').value = document.getElementById(val).value;
              return true;
          }
     }

     return false;
 }
 
 function showotmodifypopuop(apmtid,clientid,cname,location,whopay){
 patientId = clientid;
 editClientName = cname;
 loc = location;
 editwhopay = whopay;
 	$( "#otmodifypopup" ).modal( "show" );	
 }
 

 
 function delOtApmt(){

	var r=confirm("Are you sure you want to delete OT");
	if (r==true)
	  {
	  		 var myString = wraperDivId;
			if(editAppointId==0){
				myString = myString.replace(/[^\d]/g, ''); 
				editAppointId = myString;
				
			}
		 	deleteBlockSlot(editAppointId);
		 		window.location.reload()
		 	
	  return true;
	  }
	else
	  {
	  return false;
	  }

	}    
	
	
	function checkotcharge(){
	
			 var myString = wraperDivId;
			if(editAppointId==0){
				myString = myString.replace(/[^\d]/g, ''); 
				editAppointId = myString;
				
			}
			
		var url = "otchargeNotAvailableSlot?editAppointId="+editAppointId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkotchargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
	
	function checkotchargeRequest(){
	 if (req.readyState == 4) {
		if (req.status == 200) {
			var r = req.responseText;
			if(r==1){
				jAlert('error', "Charge has been created, Can't delete.", 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				delOtApmt();
			}
		}
		}
	}
	
	
	function modfyDiagnosis(){
	
	 var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	
	var url = "diagnosisNotAvailableSlot?editAppointId="+editAppointId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = modfyDiagnosisRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	


}

function modfyDiagnosisRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			  /*  var data=req.responseText;
			    var str=data.split("~"); 
			    document.getElementById('modffycondition').value = str[0];
			    
			    $("#modffycondition").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
			    
				document.getElementById('modfydiagnosisforid').innerHTML = editClientName;
				document.getElementById('innertable').innerHTML = str[1];*/
				$( "#modfydiagnosispopup" ).modal( "show" );	
				document.getElementById('searchDiagnosis').innerHTML="";
		}
	}
}





function updatediagnosis(){
	
	if(document.getElementById('modffycondition').value==0){
			jAlert('error', 'Please Select Diagnosis/Conditrion.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	
	
	     var conid =  document.getElementById('modffycondition').value;
	     patientId=document.getElementById("viewAccClientid").value;
	     document.getElementById("edtconid").value=conid;
	     document.getElementById("edtapmtid").value=editAppointId;
	     document.getElementById("edtpatientId").value=patientId;
	     document.getElementById("demoform").submit();
	
  }
}	


var tempids="0";

function tempstore(id) {
       tempids=tempids+","+id;
}

function searchConditions(val) {

        var url="searchconditionsNotAvailableSlot?ids="+tempids+"&text="+val+"";
     
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
			else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
               
    	req.onreadystatechange = searchConditionsRequest;
    	req.open("GET", url, true); 
              
    	req.send(null);
  
}


function searchConditionsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("resultsearch").innerHTML=req.responseText;
		}
	}
}	

var cell;
var row;

function addnewCondition(tableID) {
  
    var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;
	counts=counts+1;	
    var url = "addnewrowNotAvailableSlot?rowcount="+counts+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addnewConditionRequest;
    req.open("GET", url, true); 
              
    req.send(null);    
   
}


function addnewConditionRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          row.innerHTML=req.responseText;
         }
	}	 
}

function deleteMoreCon(tableId) {
  
		var table = document.getElementById(tableId);
		var rowCount = table.rows.length;	
        table.deleteRow(rowCount-1); 
}




// new ot 

function showPlanedDetails(){

	if(document.getElementById('otplaned').value=='Planed'){
	  //var url = "otinfoIpd?clientid="+dbid+" ";
	  var url = "otinfoBookAppointmentAjax?clientid="+dbid+" "; 
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showPlanedDetailsRequest;
    req.open("GET", url, true); 
              
    req.send(null);   
    
    }else{
    	document.getElementById('otagegender').innerHTML = "";
		document.getElementById('otipdno').innerHTML = "0" ;
		document.getElementById('otwardbedno').innerHTML = "";
    } 
}

function showPlanedDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			
			var temp = data.split('~');
			var agegender = temp[0];
			var ipdid = temp[1];
			var ward = temp[2];
			var bed = temp[3];
			
			document.getElementById('otagegender').innerHTML = agegender;
			document.getElementById('otipdno').innerHTML = ipdid ;
			document.getElementById('otwardbedno').innerHTML = ward + " / " + bed;;
			
		}
	}
}


function filterOtCharges(chargetype){
	
	var isot = document.getElementById('radio3').checked;
	
	
	if(isot==true){
		if(chargetype!=0){
			document.getElementById('morechargesspanid').style.display = '';
		}else{
			document.getElementById('morechargesspanid').style.display = 'none';
		}
		
	}
		  	var url = "procedureNotAvailableSlot?chargetype="+chargetype+"&patientId="+patientId+" ";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = filterOtChargesRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				
		}
		
		function filterOtChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					 document.getElementById("tpappointmenttype").innerHTML = req.responseText;
					 
					 document.getElementById('apmtType').value = gggotcharge;
					 $("#apmtType").trigger("chosen:updated");
    			  	  $(".chosen").chosen({allow_single_deselect: true});
    			  	  
    			  	  
				}
			}
		}
		
		
		//new ot charges
	function showaddotchargepopup(){
		
		
		
	/*	 var myString = wraperDivId;
			if(editAppointId==0){
				myString = myString.replace(/[^\d]/g, ''); 
				editAppointId = myString;
				
			}*/
		
		
		var chargetype = document.getElementById('otprocedureplaned').value;
		var otplaned = document.getElementById('otplaned').value;
		var otcharge=document.getElementById("otcharge").value;
		var procedureid=document.getElementById("procedureid").value;
		
			if(document.getElementById('client').value==''){
			jAlert('error', 'Please Select Patient.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		else if(chargetype==0){
			
				jAlert('error', 'Please Select Procedure.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		
		}else{
		
	 	var url = "newotchargeNotAvailableSlot?chargetype="+chargetype+"&patientId="+patientId+"&otplaned="+otplaned+"&editAppointId="+editAppointId+"&otcharge="+otcharge+"&procedureid="+procedureid+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showaddotchargepopupRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		
	}
	
	function showaddotchargepopupRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$( "#newotchargepopupid" ).modal( "show" );	
				
				var data = req.responseText;
				var tmp = data.split(',');
				
				document.getElementById('potcharge').value = tmp[7];
				document.getElementById('psurcharge').value = tmp[1];
				document.getElementById('panetcharge').value = tmp[2];
				document.getElementById('sic').value = tmp[3];
				
				if(editAppointId!=0){
					document.getElementById('anifees').value = tmp[4];
					document.getElementById('anidoctor').value = tmp[5];
					document.getElementById('assistaffcharge').value = tmp[6];
				}else{
					document.getElementById('assistaffcharge').value = tmp[4];
					document.getElementById('anifees').value = tmp[5];
					document.getElementById('anidoctor').value = tmp[6];
				}
					
				
					if(document.getElementById('otanesthesia').value!=0){
						showdoctornameforfees();
					}
				
				
			}
		}
	}
	
	
	function saveproceduralOtCharge(){
		
		var chargetype = document.getElementById('otprocedureplaned').value;
		var otplaned = document.getElementById('otplaned').value;
		
		var potcharge = document.getElementById('potcharge').value;
		var psurcharge = document.getElementById('psurcharge').value;
		var panetcharge = document.getElementById('panetcharge').value;
		var otduration = document.getElementById('apmtDuration').value;
		var sic = document.getElementById('sic').value;
		var assistaffcharge = document.getElementById('assistaffcharge').value;
		
		
		
		var url = "saveotchargeNotAvailableSlot?chargetype="+chargetype+"&patientId="+patientId+"&otplaned="+otplaned+"&patientId="+patientId+"&potcharge="+potcharge+"&psurcharge="+psurcharge+"&panetcharge="+panetcharge+"&otduration="+otduration+"&sic="+sic+"&assistaffcharge="+assistaffcharge+" ";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveproceduralOtChargeRequest;
		    req.open("GET", url, true); 
		    
		    
		              
		    req.send(null);
	}
	
	function saveproceduralOtChargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$( "#newotchargepopupid" ).modal( "hide" );	
				
				var chargeid = req.responseText;
				gggotcharge = chargeid;
				
				var chargetype = document.getElementById('otprocedureplaned').value;
				
				filterOtCharges(chargetype);
				
				
				
			  	  
			  	
				
			}
		}
		
	}
	
	function saveAnisthesiaDoctor(doctor){
		
		var url = "saveaniNotAvailableSlot?doctor="+doctor+" ";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveAnisthesiaDoctorRequest;
		    req.open("GET", url, true); 
		    
		    
		              
		    req.send(null);
	}
	
	function saveAnisthesiaDoctorRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				
				document.getElementById('newanidoctordiv').innerHTML = req.responseText;
				var dd = document.getElementById('hdnanidoctorid').value;
				
				
				document.getElementById('otanesthesia').value = dd;
				 $("#otanesthesia").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			  	  
			  	
			}
		}
		
	}
	
	
	function showdoctornameforfees(){
		var doctorname = $("#otanesthesia option:selected").text();
		
		document.getElementById('anidoctor').value = doctorname;
		
	}
	
	
	function showotprocedureList(id,action){
		var url = "departprocNotAvailableSlot?id="+id+"&action="+action+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showotprocedureListRequest;
		    req.open("GET", url, true); 
		    
		    
		              
		    req.send(null);
	}
	
	function showotprocedureListRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('otprocedurediv').innerHTML = req.responseText;
				
				 $("#otprocedureplaned").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			}
		}
	}

function savenewprocedure() {
	var addprocedurename = document.getElementById("addprocedurename").value;
	var addproceduredescription = document
			.getElementById("addproceduredescription").value;
	if (addprocedurename == '') {
		jAlert('error', 'Please enter procedure name', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else if (addproceduredescription == '0') {
		jAlert('error', 'Please Select Department', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		var dataObj = {
			"addprocedurename" : "" + addprocedurename + "",
			"addproceduredescription" : "" + addproceduredescription + ""
		};
		var data1 = JSON.stringify(dataObj);

		$.ajax({

			url : "addnewprocudureNotAvailableSlot",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			// beforeSend: function () { LockScreen(); },
			success : function(res) {
				$( "#addproocedurepopup" ).modal( "hide" );	
				var vall = document.getElementById("otdepartment").value;
				showotprocedureList(vall);
			},
			error : function(result) {
				console.log("error");
			}
		});

	}
}


var selected='0';


function searchdiagnosisJSON(d){
	
	
	if(d==''){
		
	} else {
		
		
  var dataObj={
		    "search" : "" + d + "",
			    "selected" :  ""+selected+"",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getdiagnosisJSONBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchDiagnosis").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving diagnosis");
	   }

	  });
	}
	}

function setAllDiagosisJSON(){
	
	  var dataObj={
			    
			    "selected" : "" + selected + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllDiagosisJSONBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchDiagnosis").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving diagnosis");
	   }
	  });
	
}

function reoveThisRow(obj,d){
	
	selected= selected.replace(d,'0');
	deleteThisCondition(obj);
}

function deleteThisCondition(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchDiagnosis").deleteRow(i);
}

function setCheckedData(d){
	
	if(d.checked==true){
		  
		selected=selected+","+d.value;
		setAllDiagosisJSON();
	} 
}

function addProcedure(){
	    var procedure=document.getElementById('otprocedureplaned').value;
		var left=document.getElementById('left').value;
	    var data=	document.getElementById('otprocedures').value
		var url = "addproceduresNotAvailableSlot?procedure="+procedure+"&left="+left+"&data="+data+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addproceduresRequest;
		    req.open("GET", url, true); 
		    
		    
		              
		    req.send(null);
	}
	
	function addproceduresRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
			    var data=str.split("~~");
				
				var newprocedure=data[0]+",";
				proceduredata=data[5];
				proceduredata=proceduredata+newprocedure;
				charge=parseFloat(charge)+parseFloat(data[3]);
				procedureid=procedureid+data[4]+",";
				document.getElementById('otcharge').value =charge;
				document.getElementById('otprocedures').value=proceduredata;
				document.getElementById('procedureid').value=procedureid;
				
			}
		}
	}
	
	
	
	function addChargeschange(val){
		var procedure=val;
		var url ="addnewchargesNotAvailableSlot?procedure="+procedure+"";
		if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = addChargeschangeRequest;
			    req.open("GET", url, true); 
			    
			    
			              
			    req.send(null);
	}
	
	function addChargeschangeRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					var str=req.responseText;
				    var data=str.split("~~");
					
					
					//procedureid=procedureid+data[4]+",";
					document.getElementById('otcharge').value =data[1];
					document.getElementById('otprocedures').value=data[0];
					//document.getElementById('procedureid').value=procedureid;
					charge=0;
				}
			}
		}
		