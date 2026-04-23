/**
 * 
 */

var selected1=0;
var selectedhist=0;
var selectedInvst=0;
var selectednotes=0;
var selectedobs=0;
var selectecharges=0;
var selecttooth="";
function calculateBMI(){
   var w=document.getElementById("weight").value; 
   var h=document.getElementById("height").value; 
    
   var d=h/100; 
   var bmi=w/(d*d);
   var result=Math.round(bmi*100)/100; 
   document.getElementById("bmi").value=result;	    

}

function setConsultantFormChiefComplaints(id){
	  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setConsultantFormChiefComplaintsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setConsultantFormChiefComplaintsRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		        var chiefcomplains= nicEditors.findEditor( "chiefcomplains1" ).getContent();	  
				chiefcomplains=chiefcomplains+""+req.responseText;
				nicEditors.findEditor('chiefcomplains1').setContent(chiefcomplains);	          
         }
	}	 
}

function setConsultantChiefComp(val){
	var chiefcomplains= nicEditors.findEditor( "chiefcomplains1" ).getContent();	  
	chiefcomplains=chiefcomplains+""+val;
	nicEditors.findEditor('chiefcomplains1').setContent(chiefcomplains);	      
}

function getCFInvstemplate(id) {
	 
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getCFInvstemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getCFInvstemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var suggestedtrtment= nicEditors.findEditor( "investigation" ).getContent();	  
				suggestedtrtment=suggestedtrtment+req.responseText;        
				nicEditors.findEditor('investigation').setContent(suggestedtrtment);	  	          
         }
	}	 
}

function getCFTabulardata(id){
	
	var clientid=document.getElementById("clientnewid").value;
	var admndate=document.getElementById("newadmndate").value;
	var admn=admndate.split(" ");
	var url = "gettemplateTypelistInvestigation?id="+id+"&clientid="+clientid+"&admndate="+admn[0];
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange =getCFTabulardataRequest;
    req.open("GET", url, true); 
    
    req.send(null);

	
	
}


function getCFTabulardataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data=nicEditors.findEditor( "investigation" ).getContent();
			data=data+'<br>'+req.responseText;
			nicEditors.findEditor( "investigation" ).setContent(data);
		}
	}
	
}

function removeMedicineDiscCF(r,id){
	 
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("priscTable").deleteRow(i);
	
    removeTHisMedicineCF(id);	
	
}

function removeTHisMedicineCF(id){
	var url = "removemedicineBookAppointmentAjax?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = removeTHisMedicineCFRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function removeTHisMedicineCFRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    
		}
	}

}

function getCFkunalmedtext(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getCFkunalmedtextRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getCFkunalmedtextRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var discharge_default= nicEditors.findEditor( "kunal_manual_medicine_text" ).getContent();	  
			discharge_default=discharge_default+req.responseText;        
			nicEditors.findEditor('kunal_manual_medicine_text').setContent(discharge_default);	  	
     }
}	 
}


function getnursingtemplateCF(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getnursingtemplateCFRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getnursingtemplateCFRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var discadvnotes= nicEditors.findEditor( "discadvnotes" ).getContent();	  
			discadvnotes=discadvnotes+req.responseText;        
			nicEditors.findEditor('discadvnotes').setContent(discadvnotes);	  	          
     }
}	 
}


function getdisctemplateCF(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getdisctemplateCFRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getdisctemplateCFRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discharge_default= nicEditors.findEditor( "discharge_default" ).getContent();	  
				discharge_default=discharge_default+req.responseText;        
				nicEditors.findEditor('discharge_default').setContent(discharge_default);	  	
         }
	}	 
}

function searchdiagnosisJSONCF(d){
	if(d==''){
		
		setAllDiagosisJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selected" : "" + selected + "",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getdiagnosisJSONCFBookAppointmentAjax",
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

function setCheckedDataCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selected=selected+","+d.value;
		setAllDiagosisJSONCF();
	} 
}

function setAllDiagosisJSONCF(){
	
	  var dataObj={
			    
			    "selected" : "" + selected + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllDiagosisJSONCFBookAppointmentAjax",
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

function reoveThisRowCF(obj,d){
	selected= selected.replace(d,'0');
	deleteThisConditionCF(obj);
}

function deleteThisConditionCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchDiagnosis").deleteRow(i);
}

function setfreedischargedata(val){
	 setCheckedDataCF(val);
}

function savediagnosisfastJSONCF(){
	var condition= document.getElementById("newdiagnosis").value;
	var conditiontext = condition;
	if(condition=='' || condition==' '){
		 
		 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "condition" : "" + condition + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "savediagnosisfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var condition= data.condition;
					   if(condition==0){
						   jAlert('error', 'Diagnosis already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selected=selected+","+data.condition;
					   //  21 May 2018
					   setAllDiagosisJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving diagnosis");
				   }
				  });
				   
		
	}
} 

function changeCFSrNo(val,id,columnName){
	var freq=document.getElementById("discpriscdose"+id).value;
	var days=document.getElementById("dicpriscdays"+id).value;
	  var str =  freq.split('-');
      var priscqtys=0;
        for(var i = 0; i < str.length; i++) {
			
			/*var hasNumber = /\d/;
				var res = hasNumber.test(str[i]); // false
				//hasNumber.test("Easy as 123"); // true
			*/	
			var test = parseInt(str[i]);
			
			if(test=='NaN'){
				continue;
			}
			if(str[i]=='0'){
				continue;
			}
			if(str[i]=='1/2'){
				continue;
			}
			 priscqtys=priscqtys+(parseInt(str[i])*parseInt(days));
			}
     
      document.getElementById("dicpriscdrqty"+id).value=priscqtys;

	if(columnName=='sqno'){
		if(val==''){
			val=0;
		}
	}
	
	 var dataObj= {
				"id":""+id+"", 
				"val":""+val+"", 
				"column":""+columnName+"", 
				"priscqtys":""+priscqtys+"",
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "savecfmedicineCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  consultationPriscData();
		  },
		  error : function(result) {
		   console.log("error");
		  }
		 });
	}
var editAppointId=0;
function showCFInvestigation(pppid,pppcname,pppwhopay){
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	editAppointId = document.getElementById("apmtId").value;
	showInvestigationCF();
}

function showInvestigationCF(){
	document.getElementById('jobtitle').value = 'Pathlab';
	removeSession();
	getPriscClientInfo(patientId);
	window.$('#investigationpopup').modal( "show" );
}

function setGivenInvestigationData(ids){
	  $( "#baselayout1loaderPopup" ).modal( "show" );
	    var dataObj= {
				"ids":""+ids+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "getinvestigationlistforCFCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  var pre=nicEditors.findEditor( "investigation" ).getContent();
			  nicEditors.findEditor( "investigation" ).setContent(pre+'<br>'+data.textdata);
			 
			  $( "#baselayout1loaderPopup" ).modal( "hide" );
		    },
		  error : function(result) {
		   console.log("error");
		  }
		 });

}

function giveCFFollowup(followupdate){
	var clientid=document.getElementById("clientid").value;
	var dd=nicEditors.findEditor( "discadvnotes" ).getContent();
	var data =dd+"<b>Follow Up Date:</b> "+followupdate;
	nicEditors.findEditor( "discadvnotes" ).setContent(data);
	/* var ipdid= document.getElementById("jsonipdid").value;
	 var isbookapmt=document.getElementById("bkapmtipd").checked;
	 var url = "givefollowupCommonnew?ipdid="+ipdid+"&date="+followupdate+"&bookapmt="+isbookapmt;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange =giveCFFollowupReq;
	    req.open("GET", url, true); 
	    
	    req.send(null)
	*/
}

function changeInvCFVal(id,val){
	
	var isFromCF ="0";
	if(document.getElementById('consulationForm')){
		isFromCF ="1";
    }
	var url = "changeInvCFvalInvestigation?selectedid="+id+"&val="+val+"&isFromCF="+isFromCF+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changeInvCFValRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}


function changeInvCFValRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('invstlistid').innerHTML = req.responseText;
		}
	}

}

function setConsultantFormExamination(id){
	  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setConsultantFormExaminationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setConsultantFormExaminationRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		        var exam= nicEditors.findEditor( 'examination1' ).getContent();	  
				exam=exam+""+req.responseText;
				nicEditors.findEditor('examination1').setContent(exam);	          
         }
	}	 
}



function getCFondiet(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getondietRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getondietRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var ondiet= nicEditors.findEditor( "ondiet" ).getContent();	  
				ondiet=ondiet+req.responseText;        
				nicEditors.findEditor('ondiet').setContent(ondiet);	  	          
         }
	}	 
}

function getCFphysio(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getCFphysioRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getCFphysioRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var physio= nicEditors.findEditor( "physio" ).getContent();	  
				physio=physio+req.responseText;        
				nicEditors.findEditor('physio').setContent(physio);	  	          
         }
	}	 
}



function getCFkarma(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getCFkarmaRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getCFkarmaRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var karma= nicEditors.findEditor( "karma" ).getContent();	  
				karma=karma+req.responseText;        
				nicEditors.findEditor('karma').setContent(karma);	  	          
         }
	}	 
}


function getCFprocedure(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getCFprocedureRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getCFprocedureRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var procedurebams= nicEditors.findEditor( "procedurebams" ).getContent();	  
				procedurebams=procedurebams+req.responseText;        
				nicEditors.findEditor('procedurebams').setContent(procedurebams);	  	          
         }
	}	 
}


function getCFprocedure1(id){

		var url="getproceduretemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getCFprocedure1Request;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getCFprocedure1Request(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var procedurebams= nicEditors.findEditor( "procedurebams" ).getContent();	  
				procedurebams=procedurebams+req.responseText;        
				nicEditors.findEditor('procedurebams').setContent(procedurebams);	  	          
         }
	}	 
}

function openconsultation(date,clientId,apmtid){
	
	var url="previouscounsultationCommonnew?date="+date+"&clientId="+clientId+"&apmtid="+apmtid+"";
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =openconsultationRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
}


function openconsultationRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			var data= req.responseText;
			var temp = data.split('~~');
			document.getElementById('datebtn'+temp[1]).style.backgroundColor='orange';
			document.getElementById('consulthist'+temp[1]).innerHTML = temp[0];	

					var div=document.getElementById('consulthist'+temp[1]);		          
					if (div.style.display ==='none'){
					   
						 div.style.display = 'block';
					 }else{
						div.style.display = 'none';
						document.getElementById('datebtn'+temp[1]).style.backgroundColor='black';
						document.getElementById('datebtn'+temp[1]).style.color='white';
					 }
         }
	}	 
}

function saveeyereading(){
	var left_dist=document.getElementById("left_dist").value;
	var left_nr=document.getElementById("left_nr").value;
	var with_glass1=document.getElementById("with_glass1").value;
	var withoutgls1=document.getElementById("withoutgls1").value;
	var pin1=document.getElementById("pin1").value;
	var iop1=document.getElementById("iop1").value;
	
	var right_dist=document.getElementById("right_dist").value;
	var right_nr=document.getElementById("right_nr").value;
	var with_glass2=document.getElementById("with_glass2").value;
	var withoutgls2=document.getElementById("withoutgls2").value;
	var pin2=document.getElementById("pin2").value;
	var iop2=document.getElementById("iop2").value;
	var url="saveEyeReadingCommonnew?left_dist="+left_dist+"&left_nr="+left_nr+"&with_glass1="+with_glass1+"&withoutgls1="+withoutgls1+"&pin1="+pin1+"&iop1="+iop1+"&right_dist="+right_dist+"&right_nr="+right_nr+"&with_glass2="+with_glass2+"&withoutgls2="+withoutgls2+"&pin2="+pin2+"&iop2="+iop2+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		           
		req.onreadystatechange =saveeyereadingRequest;
		req.open("GET", url, true); 
		          
		req.send(null);
}


function saveeyereadingRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var eyereading= nicEditors.findEditor( "eyereading" ).getContent();	  
				eyereading=req.responseText;        
				nicEditors.findEditor('eyereading').setContent(eyereading);	  	          
         }
	}	 
}


function getHistemplateCF(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getHistemplateCFRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getHistemplateCFRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var history= nicEditors.findEditor( "history" ).getContent();	  
				history=history+req.responseText;        
				nicEditors.findEditor('history').setContent(history);	  	
         }
	}	 
}



function searchChiefcomplains(d,id){
	if(d==''){
		
		setAllChiefcomplainsJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selected1" : "" + selected1 + "",
			"id" : ""+id+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchChiefcomplainsJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var condition= data.responsej;
			   document.getElementById("searchChiefcomplains").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving Chief Complains");
		   }
		});
	}
}

function setCheckedComplainsCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selected1=selected1+","+d.value;
		setAllChiefcomplainsJSONCF();
	} 
}



function setAllChiefcomplainsJSONCF(){
	
	  var dataObj={
			    
			    "selected1" : "" + selected1 + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllChiefComplainsJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchChiefcomplains").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving Chief Complains");
	   }
	  });
	
}

function removechiefRowCF(obj,d){
	selected1= selected1.replace(d,'0');
	deleteThiscomplainCF(obj);
}

function deleteThiscomplainCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchChiefcomplains").deleteRow(i);
}


function saveChiefcomplainfastJSONCF(){
	var chiefcomplain= document.getElementById("newchief").value;
	var chiefcomplaintext = chiefcomplain;
	if(chiefcomplain=='' || chiefcomplain==' '){
		 
		 jAlert('error', 'Chief Complain will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "chiefcomplain" : "" + chiefcomplain + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "saveChiefcomplainfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var chiefcomplain= data.chiefcomplain;
					   if(chiefcomplain==0){
						   jAlert('error', 'Chief Complain already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selected1=selected1+","+data.chiefcomplain;
					   //  21 May 2018
					   setAllChiefcomplainsJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving Chief Complain");
				   }
				  });
				   
		
	}
} 



function searchPastHistory(d,id){
	if(d==''){
		
		setAllHistoryJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selectedhist" : "" + selectedhist + "",
			"id" : ""+id+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchHistoryJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var history= data.responsej;
			   document.getElementById("searchHistory").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving History");
		   }
		});
	}
}



function setCheckedHistoryCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selectedhist=selectedhist+","+d.value;
		setAllHistoryJSONCF();
	} 
}



function setAllHistoryJSONCF(){
	
	  var dataObj={
			    
			    "selectedhist" : "" + selectedhist + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllHistoryJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchHistory").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving History");
	   }
	  });
	
}


function removeHistoryRowCF(obj,d){
	selectedhist= selectedhist.replace(d,'0');
	deleteThisHistoryCF(obj);
}

function deleteThisHistoryCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchHistory").deleteRow(i);
}


function saveHistoryfastJSONCF(){
	var pasthistory= document.getElementById("pasthistory").value;
	var pasthistorytext = pasthistory;
	if(pasthistory=='' || pasthistory==' '){
		 
		 jAlert('error', 'History will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "pasthistory" : "" + pasthistory + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "saveHistoryfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var history= data.pasthistory;
					   if(history==0){
						   jAlert('error', 'History already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selectedhist=selectedhist+","+data.pasthistory;
					   //  21 May 2018
					   setAllHistoryJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving History");
				   }
				  });
				   
		
	}
} 


function searchInvestigation(d){
	if(d==''){
		
		setAllInvestigationJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selectedInvst" : "" + selectedInvst + "",
			
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchInvestigationJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var investigation= data.responsej;
			   document.getElementById("searchInvestigation").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving Investigation");
		   }
		});
	}
}


function setCheckedInvestigationCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selectedInvst=selectedInvst+","+d.value;
		setAllInvestigationJSONCF();
	} 
}



function setAllInvestigationJSONCF(){
	
	  var dataObj={
			    
			    "selectedInvst" : "" + selectedInvst + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllInvestigationJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var invst= data.responsej;
		   document.getElementById("searchInvestigation").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving Investigation");
	   }
	  });
	
}


function removeInvestigationRowCF(obj,d){
	selectedInvst= selectedInvst.replace(d,'0');
	deleteThisInvestigationCF(obj);
}

function deleteThisInvestigationCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchInvestigation").deleteRow(i);
}



function saveInvestigationfastJSONCF(){
	var investigation= document.getElementById("investigationName").value;
	var investigationtext = investigation;
	if(investigation=='' || investigation==' '){
		 
		 jAlert('error', 'Investigation will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "investigation" : "" + investigation + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "saveInvestigationfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var history= data.pasthistory;
					   if(history==0){
						   jAlert('error', 'Investigation already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selectedInvst=selectedInvst+","+data.investigation;
					   //  21 May 2018
					   setAllInvestigationJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving Investigation");
				   }
				  });
				   
		
	}
} 



function searchNotes(d,id){
	if(d==''){
		
		setAllNOtesJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selectednotes" : "" + selectednotes + "",
			"id" : ""+id+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchNotesJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var notes= data.responsej;
			   document.getElementById("searchNotes").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving Notes");
		   }
		});
	}
}




function setCheckedNotesCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selectednotes=selectednotes+","+d.value;
		setAllNOtesJSONCF();
	} 
}



function setAllNOtesJSONCF(){
	
	  var dataObj={
			    
			    "selectednotes" : "" + selectednotes + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllNotesJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchNotes").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving Notes");
	   }
	  });
	
}



function removeNotesRowCF(obj,d){
	selectedInvst= selectedInvst.replace(d,'0');
	deleteThisNotesCF(obj);
}

function deleteThisNotesCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchNotes").deleteRow(i);
}



function saveNotesfastJSONCF(){
	var newnotes= document.getElementById("newnotes").value;
	var newnotestext = newnotes;
	if(newnotes=='' || newnotes==' '){
		 
		 jAlert('error', 'Notes will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "newnotes" : "" + newnotes + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "saveNotesfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var newnotes= data.newnotes;
					   if(newnotes==0){
						   jAlert('error', 'Notes already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selectednotes=selectednotes+","+data.newnotes;
					   //  21 May 2018
					   setAllNOtesJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving Notes");
				   }
				  });
				   
		
	}
} 



function searchObservation(d,id){
	if(d==''){
		
		setAllObservJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selectedobs" : "" + selectedobs+ "",
			"id" : ""+id+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchObservationJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var obs= data.responsej;
			   document.getElementById("searchObservation").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving Observation");
		   }
		});
	}
}



function setCheckedObservCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selectedobs=selectedobs+","+d.value;
		setAllObservJSONCF();
	} 
}



function setAllObservJSONCF(){
	
	  var dataObj={
			    
			    "selectedobs" : "" + selectedobs + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllObservJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchObservation").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving Observation");
	   }
	  });
	
}




function removeObserRowCF(obj,d){
	selectedobs= selectedobs.replace(d,'0');
	deleteThisObservCF(obj);
}

function deleteThisObservCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchObservation").deleteRow(i);
}


function saveObservationfastJSONCF(){
	var observation= document.getElementById("observation").value;
	var observationtext = observation;
	if(observation=='' || observation==' '){
		 
		 jAlert('error', 'Observation will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "observation" : "" + observation + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "saveObservfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var observation= data.observation;
					   if(observation==0){
						   jAlert('error', 'Observation already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selectedobs=selectedobs+","+data.observation;
					   //  21 May 2018
					   setAllObservJSONCF();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving Observation");
				   }
				  });
				   
		
	}
} 



function searchChargesJSONCF(d){
	if(d==''){
		
		setAllChargesJSONCF();
		
	} else {
		var dataObj={
			"search" : "" + d + "",
			"selectecharges" : "" + selectecharges+ "",
			
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "searchChargesJSONCFBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   // beforeSend: function () { LockScreen(); },
		   success : function(data) {
			   var obs= data.responsej;
			   document.getElementById("searchCharges").innerHTML=data.responsej;
		   },
		   error : function(result) {
			   console.log("error in saving ChargeName");
		   }
		});
	}
}


function setCheckedChargesCF(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selectecharges=selectecharges+","+d.value;
		setAllChargesJSONCF();
	} 
}



function setAllChargesJSONCF(){
	
	  var dataObj={
			    
			    "selectecharges" : "" + selectecharges + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllChargesJSONCFBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchCharges").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving ChargeName");
	   }
	  });
	
}




function removeChargesRowCF(obj,d){
	selectecharges= selectecharges.replace(d,'0');
	deleteThisChargeCF(obj);
}

function deleteThisChargeCF(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchCharges").deleteRow(i);
}

function saveCharges(){
	
	var chargename=document.getElementById("chargename").value;
	var charge=document.getElementById("charge").value;
	var toothnum=document.getElementById("toothnum").value;
	chargename=chargename+"-"+toothnum;
	 var url="saveApmtChargesBookAppointmentAjax?chargename="+chargename+"&charge="+charge+"&toothnum="+toothnum+"";
	 if (window.XMLHttpRequest) {
	 req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
	 	isIE = true;
	 	req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	            
	 req.onreadystatechange = setsaveChargesRequest;
	 req.open("GET", url, true); 
	           
	 req.send(null);
	
}

function setsaveChargesRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
			$( "#insercharges" ).modal( "hide" );		  	          
         }
	}	 
}


function setToothnum(val){
	if(selecttooth ==""){
	selecttooth=val;
	}else{
	selecttooth=selecttooth+","+val;
	}
	document.getElementById("toothnum").value=selecttooth;
}
