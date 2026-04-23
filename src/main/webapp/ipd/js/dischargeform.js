


function isValidForm(){

	
	

      var eddischrgeOutcomes=document.getElementById("eddischrgeOutcomes").value;
      var eddischargeStatus=document.getElementById("eddischargeStatus").value;
      var kunalfinaldiagnosis="";
      if(document.getElementById("kunalfinaldiagnosis")){
    	  kunalfinaldiagnosis= nicEditors.findEditor( "kunalfinaldiagnosis" ).getContent();
      }
      
      var diagosis=0;
      $('.classD').each(function() { 
  		if(this.checked == true){
  			
  			diagosis=diagosis+","+this.value;
  		}
  								
  	});
  	//for parihar secondary consult
  	var doctors=0;
		$('.doctors').each(function() { 
			if(this.checked == true){
			    doctors=doctors+","+this.value; 
			} 
									
		});
		document.getElementById("secndryconsult").value=doctors;
      
       if(diagosis=='0'&&kunalfinaldiagnosis==""){
          
          jAlert('error', 'Please Select Final Diagnosis.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);


         return false;
                
      }
     /* else if(eddischrgeOutcomes=='0'){
          
       jAlert('error', 'Please Select discharge Outcome.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);


         return false;
       
      }*/ else if(eddischargeStatus=='0'){
      
          jAlert('error', 'Please Select discharge Status.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);


         return false;
      } else {
        if(document.getElementById("emercontdetail")){
        	var emercontdetail=  nicEditors.findEditor( "emercontdetail" ).getContent();
        	document.getElementById("emercontdetail").value = emercontdetail;
        }
    	 	
          return true;
      }
   
}

	function getPriscriptionGiven(id){
		var url = "getPriscriptionGivenIpd?id="+id+"";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getPriscriptionGivenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function getPriscriptionGivenRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				 	
				 	$('#treatgiven').modal( "show" );
			}
		}
	}
function selectAll(obj){
	 
	     if(obj.checked==true){
	      		 $('.case').each(function() { // loop through each checkbox
			          this.checked=true;
				 });	
		 }
	      else {
	     	  $('.case').each(function() { // loop through each checkbox
			          this.checked=false;
				 });	
				
	     }
	     
	}

function addMedicinePriscription(){
	var product=0;
	$('.case').each(function() { // loop through each checkbox
		if(this.checked == true ){
				product=product+","+this.value;
		}	
	});
	
	var url = "addMedicinePriscriptionIpd?product="+product+"";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addMedicinePriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);					
}
	function addMedicinePriscriptionRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
					 var chiefcomplains= nicEditors.findEditor( "treatmentgiven" ).getContent();  
					 
    				chiefcomplains=chiefcomplains+""+req.responseText;
    				nicEditors.findEditor('treatmentgiven').setContent(chiefcomplains);
					//document.getElementById("treatmentgiven").value = req.responseText;
					$('#treatgiven').modal( "hide" );
			}
		}
	}

	
	function removeMedicineDisc(r,id){
		 
		var i = r.parentNode.parentNode.rowIndex;
		document.getElementById("priscTable").deleteRow(i);
		
	    removeTHisMedicine(id);	
		
	}

	
	function removeMedicineDisc1(r,id){
		 
		var i = r.parentNode.parentNode.rowIndex;
		document.getElementById("priscTabletreat").deleteRow(i);
		
	    removeTHisMedicine(id);	
		
	}
	function removeTHisMedicine(id){
		var url = "removemedicineBookAppointmentAjax?id="+id+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = removeTHisMedicineRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);
	}

	function removeTHisMedicineRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			    
			}
		}

	}
	
	function checkdeathstatus(val) {
		showReasonBoxForLamaDama();
		
		if(val=='3'){
			if(document.getElementById("audiagnosis")){
				document.getElementById("audiagnosis").innerHTML=' DIAGNOSIS  / CAUSE OF DEATH ';
			}
		
			
			//document.getElementById("allergiesdiv").className ="hidden";
			//document.getElementById("chiefcomplaintsdiv").className ="hidden";
			//document.getElementById("presentillnessdiv").className ="hidden";
			//document.getElementById("historydiv").className ="hidden";
			document.getElementById("surgicalnotesdiv").className ="hidden";
			document.getElementById("treatmentgivendiv").className ="hidden";
			document.getElementById("deathcausediv").className ="";
			var xx=nicEditors.findEditor( "death_note" );
			xx.removeInstance('death_note');
			
			document.getElementById("btn5").className="tablinks";
			document.getElementById("btn4").className="hidden";
			document.getElementById("btn9").className="hidden";
			
			
		
		}else{
			if(document.getElementById("audiagnosis")){
				document.getElementById("audiagnosis").innerHTML=' DIAGNOSIS  ';
			}
			//document.getElementById("allergiesdiv").className ="";
			//document.getElementById("chiefcomplaintsdiv").className ="";
			//document.getElementById("presentillnessdiv").className ="";
			//document.getElementById("historydiv").className ="";
			document.getElementById("surgicalnotesdiv").className ="";
			document.getElementById("treatmentgivendiv").className ="";
			document.getElementById("deathcausediv").className ="hidden";
			
			document.getElementById("btn5").className="hidden";
			document.getElementById("btn4").className="tablinks";
			document.getElementById("btn9").className="tablinks";
			
		}
		
	}   


    function checkDates(){
 	var adm=  document.getElementById("admissiondate").innerHTML;
  	var disch=   document.getElementById("dischargedate").value;
  	adm = adm.split(" ");
  	var adm1= toDate(adm[0]);
  	var disch1= toDate(disch);
  	var timeDiff =( disch1.getTime() -adm1.getTime());
  	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
  	if(diffDays<0){
  		alert("Discharge date is lesser than Admission date ");
  	}
  	
    }
    
    
    function toDate(dateStr) {
        var parts = dateStr.split("-");
        return new Date(parts[2], parts[1] - 1, parts[0]);
    }
    
    
    
    
    
    
    function submitaddmissionFor1(){
    	 var ipdid= document.getElementById("ipdseqno").value;
	   	 var clientid= document.getElementById("clientid").value;
   	 //data
   
   	 var surgicalnotes= nicEditors.findEditor( "operation_notes" ).getContent();
   	 var hospitalcourse=  nicEditors.findEditor( "hospitalcourse" ).getContent();
   	 var treatmentgiven=  nicEditors.findEditor( "treatmentgiven" ).getContent();
   	 var investigation=  nicEditors.findEditor( "investigation" ).getContent();
   	 var findondis=  nicEditors.findEditor( "finddis" ).getContent();
   	 var dischargeadvice=  nicEditors.findEditor( "discadvnotes" ).getContent();
   

   	 
   	
   	 var birthhist=   nicEditors.findEditor( "birthhist" ).getContent();
   	var diethist=  nicEditors.findEditor( "diethist" ).getContent();
   	var emmunizationhist = nicEditors.findEditor( "emmunizationhist" ).getContent();
   	 var developmenthist =   nicEditors.findEditor( "developmenthist" ).getContent();
   	 var dataObj= {
   	   "history":""+history+"",
   	"surgicalnotes":""+surgicalnotes+"",
   	"hospitalcourse":""+hospitalcourse+"",
   	"treatmentgiven":""+treatmentgiven+"",
   	"investigation":""+investigation+"",
   	"findondis":""+findondis+"",
   
   	
   	"dischargeadvice":""+dischargeadvice+"",
   	   "birthhist":""+birthhist+"",
   	   "diethist":""+diethist+"",
   	   "emmunizationhist":""+emmunizationhist+"",
   	   "developmenthist":""+developmenthist+"",
   	   
   	   "ipdid" : "" + ipdid + "",
   	   "clientid" : "" + clientid + "",
   	   
   	   };
   	 var data1 =  JSON.stringify(dataObj);
   	 $.ajax({

   	  url : "saveTempIPDDischargeDataBookAppointmentAjax",
   	  data : data1,
   	  dataType : 'json',
   	  contentType : 'application/json',
   	  type : 'POST',
   	  async : true,
   	  // beforeSend: function () { LockScreen(); },
   	  success : function(data) {
   		  

   		  
   	    },
   	  error : function(result) {
   	   console.log("error");
   	  }
   	 });
   }


   function getIPDTempData1(){
   	  
		 var ipdid= document.getElementById("ipdseqno").value;
	   	 var clientid= document.getElementById("clientid").value;
   	  var dataObj={
   	    
   	    "ipdid" : "" + ipdid + "",
   	    "clientid" : "" + clientid + "",
   	  };
   	  var data1 =  JSON.stringify(dataObj);
   	  $.ajax({

   	   url : "getIPDTempDischargeDataBookAppointmentAjax",
   	   data : data1,
   	   dataType : 'json',
   	   contentType : 'application/json',
   	   type : 'POST',
   	   async : true,
   	   // beforeSend: function () { LockScreen(); },
   	   success : function(data) {

   		   
   		   
   		

   	   nicEditors.findEditor( "operation_notes" ).setContent(data.surgicalnotes); 
    nicEditors.findEditor( "hospitalcourse" ).setContent(data.hospitalcourse); 
   	  nicEditors.findEditor( "treatmentgiven" ).setContent(data.treatmentgiven); 
   	   	 nicEditors.findEditor( "investigation" ).setContent(data.investigation); 
   	    nicEditors.findEditor( "finddis" ).setContent(data.findondis); 
   	    nicEditors.findEditor( "discadvnotes" ).setContent(data.dischargeadvice); 
   	   
   			   
   			   
   		  
   		   nicEditors.findEditor( "birthhist" ).setContent(data.birthhist); 
   		   nicEditors.findEditor( "diethist" ).setContent(data.diethist); 
   		   nicEditors.findEditor( "developmenthist" ).setContent(data.developmenthist); 
   		   nicEditors.findEditor( "emmunizationhist" ).setContent(data.emmunizationhist); 
   		   
   		   console.log(data);
   	     },
   	   error : function(result) {
   	    console.log("error");
   	   }
   	  });
   	   
   	              
   	 
   	 }

function getTabulardata(id){
	
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
               
    req.onreadystatechange =getTabulardataRequest;
    req.open("GET", url, true); 
    
    req.send(null);

	
	
}


function getTabulardataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data=nicEditors.findEditor( "investigation" ).getContent();
			data=data+'<br>'+req.responseText;
			nicEditors.findEditor( "investigation" ).setContent(data);
		}
	}
	
}


function giveIpdFollowup(followupdate){
	var clientid=document.getElementById("clientnewid").value;
	var dd=nicEditors.findEditor( "discadvnotes" ).getContent();
	var data =dd+"<b>Follow Up Date:</b> "+followupdate;
	nicEditors.findEditor( "discadvnotes" ).setContent(data);
	 var ipdid= document.getElementById("jsonipdid").value;
	 var isbookapmt=document.getElementById("bkapmtipd").checked;
	 var url = "givefollowupCommonnew?ipdid="+ipdid+"&date="+followupdate+"&bookapmt="+isbookapmt;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange =giveIpdFollowupReq;
	    req.open("GET", url, true); 
	    
	    req.send(null)
	
}

function giveIpdFollowupReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}

function entrInvestigation(){
	 var ipdid= document.getElementById("jsonipdid").value;
	  $( "#baselayout1loaderPopup" ).modal( "show" );
	/* var url = "getinvestigationlistforipdCommonnew?ipdid="+ipdid;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange =entrInvestigationREq;
	    req.open("GET", url, true); 
	    
	    req.send(null);*/
	    
	    
	    
	    
	    
	    
	    var dataObj= {
				"ipdid":""+ipdid+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "getinvestigationlistforipdCommonnew",
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

function entrInvestigationREq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			nicEditors.findEditor( "investigation" ).setContent(data);
			
		}
	}
}
function getonexamtemp123(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getonexamtempRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getonexamtempRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var onexamination= nicEditors.findEditor( "onexaminationlok" ).getContent();	  
			onexamination=onexamination+req.responseText;        
			nicEditors.findEditor('onexaminationlok').setContent(onexamination);	  	          
     }
}	 
}


function savediagnosisAreaOFDiagnosisDiv(){
	$('#disloaderPopup').modal( "show" );
	 var ipdid= document.getElementById("ipdseqno").value;
  	 var clientid= document.getElementById("clientid").value;
	 //data

	 var kunalfinaldiagnosis= nicEditors.findEditor( "kunalfinaldiagnosis" ).getContent();
	 var hospitalcourse=  nicEditors.findEditor( "hospitalcourse" ).getContent();
	 var treatmentgiven=  nicEditors.findEditor( "treatmentgiven" ).getContent();
	 var investigation=  nicEditors.findEditor( "investigation" ).getContent();
	 var findondis=  nicEditors.findEditor( "finddis" ).getContent();
	 var dischargeadvice=  nicEditors.findEditor( "discadvnotes" ).getContent();


	 
	
	 var birthhist=   nicEditors.findEditor( "birthhist" ).getContent();
	var diethist=  nicEditors.findEditor( "diethist" ).getContent();
	var emmunizationhist = nicEditors.findEditor( "emmunizationhist" ).getContent();
	 var developmenthist =   nicEditors.findEditor( "developmenthist" ).getContent();
	 var dataObj= {
	   "history":""+history+"",
	//"surgicalnotes":""+surgicalnotes+"",
	"hospitalcourse":""+hospitalcourse+"",
	"treatmentgiven":""+treatmentgiven+"",
	"investigation":""+investigation+"",
	"findondis":""+findondis+"",

	
	"dischargeadvice":""+dischargeadvice+"",
	   "birthhist":""+birthhist+"",
	   "diethist":""+diethist+"",
	   "emmunizationhist":""+emmunizationhist+"",
	   "developmenthist":""+developmenthist+"",
	   
	   "ipdid" : "" + ipdid + "",
	   "clientid" : "" + clientid + "",
	   
	   };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveTempIPDDischargeDataBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  
		  //openBlock(event, 'ADMISSION')
		  
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn2').click();
	 $('#disloaderPopup').modal( "hide" );
}




function getAdmissionDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("admn_summdiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getAdmissionDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "chiefcomplains1" )){
			  nicEditors.findEditor( "chiefcomplains1" ).setContent(data.chiefcomplains); 
		  }
		  if( document.getElementById( "alergies" )){
			  nicEditors.findEditor( "alergies" ).setContent(data.alergies);   
		  }
		  if( document.getElementById( "presentillness" )){
			  nicEditors.findEditor( "presentillness" ).setContent(data.presentillness);  
		  }
		  
		  document.getElementById( "headcircumference" ).value=data.headcircumference; 
		  document.getElementById( "midarmcircumference" ).value=data.midarmcircumference; 
		  document.getElementById( "leng" ).value=data.length; 
		  document.getElementById( "wtaddmission" ).value=data.wtaddmission; 
		  document.getElementById( "wtdischarge" ).value=data.wtdischarge; 
		  
		 

		  
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}

function saveAdmissionDivDataOfDischargeForm(){
	var hospitalcourse="",chiefcomplains="",alergies="",presentillness="",headcircumference="",midarmcircumference="",leng="",wtaddmission="",wtdischarge;
	$('#disloaderPopup').modal( "show" );
	var ipdid= document.getElementById("jsonipdid").value;
	  if( document.getElementById( "chiefcomplains1" )){
		  chiefcomplains=nicEditors.findEditor( "chiefcomplains1" ).getContent(); 
	  }
	  if( document.getElementById( "alergies" )){
		  alergies= nicEditors.findEditor( "alergies" ).getContent(); 
	  }
	  if( document.getElementById( "presentillness" )){
		  presentillness= nicEditors.findEditor( "presentillness" ).getContent();
	  }
	  document.getElementById( "admn_summdiv" ).value="1";
	  
	  headcircumference=document.getElementById( "headcircumference" ).value;
	  midarmcircumference=document.getElementById( "midarmcircumference" ).value;
	  leng=document.getElementById( "leng" ).value; 
	  wtaddmission= document.getElementById( "wtaddmission" ).value; 
	  wtdischarge=document.getElementById( "wtdischarge" ).value; 
	  
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"chiefcomplains":""+encodeEntity(chiefcomplains)+"", 
			"alergies":""+encodeEntity(alergies)+"", 
			"presentillness":""+encodeEntity(presentillness)+"", 
			"headcircumference":""+encodeEntity(headcircumference)+"", 
			"midarmcircumference":""+encodeEntity(midarmcircumference)+"", 
			"length":""+encodeEntity(leng)+"", 
			"wtaddmission":""+encodeEntity(wtaddmission)+"", 
			"wtdischarge":""+encodeEntity(wtdischarge)+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveAdmissionDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn3').click();
	 document.getElementById('dropdown1').value='HISTORY';
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}





function getHistoryDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("histrydiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getHistoryDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "familyhistlok" )){
			  nicEditors.findEditor( "familyhistlok" ).setContent(data.familyhist); 
		  }
		  if( document.getElementById( "personalhistlok" )){
			  nicEditors.findEditor( "personalhistlok" ).setContent(data.personalhist);   
		  }
		  if( document.getElementById( "earlierinvestlok" )){
			  nicEditors.findEditor( "earlierinvestlok" ).setContent(data.earlierinvest);  
		  }
		  if( document.getElementById( "suggestedtrtmentlok" )){
			  nicEditors.findEditor( "suggestedtrtmentlok" ).setContent(data.suggestedtrtment);   
		  }
		  if( document.getElementById( "surgicalnotes" )){
			  nicEditors.findEditor( "surgicalnotes" ).setContent(data.surgicalnotes);  
		  } 
		  if( document.getElementById( "diethist" )){
			  nicEditors.findEditor( "diethist" ).setContent(data.diethist);   
		  }
		  if( document.getElementById( "birthhist" )){
			  nicEditors.findEditor( "birthhist" ).setContent(data.birthhist);  
		  }
		  if( document.getElementById( "developmenthist" )){
			  nicEditors.findEditor( "developmenthist" ).setContent(data.developmenthist);  
		  }
		  if( document.getElementById( "emmunizationhist" )){
			  nicEditors.findEditor( "emmunizationhist" ).setContent(data.emmunizationhist);   
		  }
		  if( document.getElementById( "discharge_default" )){
			  nicEditors.findEditor( "discharge_default" ).setContent(data.example);  
		  }
		  if( document.getElementById( "onexaminationlok" )){
			  nicEditors.findEditor( "onexaminationlok" ).setContent(data.onexamination);  
		  }
		 /* if( document.getElementById( "operation_notes" )){
			  nicEditors.findEditor( "operation_notes" ).setContent(data.otNotes);  
		  }*/
		  if( document.getElementById( "pasthistorylok" )){
			  nicEditors.findEditor( "pasthistorylok" ).setContent(data.pasthistory);  
		  }
		  
		  /*document.getElementById( "headcircumference" ).value=data.headcircumference; 
		  document.getElementById( "midarmcircumference" ).value=data.midarmcircumference; 
		  document.getElementById( "leng" ).value=data.length; 
		  document.getElementById( "wtaddmission" ).value=data.wtaddmission; 
		  document.getElementById( "wtdischarge" ).value=data.wtdischarge; */
		  
		 


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}


function saveHistoryDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var familyhistlok="",personalhistlok="",earlierinvestlok="",suggestedtrtmentlok="",surgicalnotes="",diethist="",birthhist="",developmenthist="",discharge_default="",onexaminationlok="",operation_notes="";
	var pasthistory="";
	var ipdid= document.getElementById("jsonipdid").value;
	var dischargestatus=document.getElementById("eddischargeStatus").value
	if(nicEditors.findEditor( "familyhistlok" )){
		 familyhistlok= nicEditors.findEditor( "familyhistlok" ).getContent(); 
	  }
	  if(nicEditors.findEditor( "personalhistlok" )){
		  personalhistlok=nicEditors.findEditor( "personalhistlok" ).getContent();  
	  }
	  if(nicEditors.findEditor( "earlierinvestlok" )){
		  earlierinvestlok= nicEditors.findEditor( "earlierinvestlok" ).getContent();    
	  }
	  if( document.getElementById( "suggestedtrtmentlok" )){
		  suggestedtrtmentlok= nicEditors.findEditor( "suggestedtrtmentlok" ).getContent();     
	  }
	  if( nicEditors.findEditor( "surgicalnotes" )){
		  surgicalnotes=nicEditors.findEditor( "surgicalnotes" ).getContent();  
	  } 
	  if( nicEditors.findEditor( "diethist" )){
		  diethist=nicEditors.findEditor( "diethist" ).getContent();  
	  }
	  if(nicEditors.findEditor( "birthhist" )){
		  birthhist=nicEditors.findEditor( "birthhist" ).getContent();  
	  }
	  if( nicEditors.findEditor( "developmenthist" )){
		  developmenthist=nicEditors.findEditor( "developmenthist" ).getContent();  
	  }
	  if( nicEditors.findEditor( "emmunizationhist" )){
		  emmunizationhist= nicEditors.findEditor( "emmunizationhist" ).getContent();    
	  }
	  if( nicEditors.findEditor( "discharge_default" )){
		  discharge_default=nicEditors.findEditor( "discharge_default" ).getContent();  
	  }
	  if(  nicEditors.findEditor( "onexaminationlok" )){
		  onexaminationlok= nicEditors.findEditor( "onexaminationlok" ).getContent();  
	  }
	  if(  nicEditors.findEditor( "operation_notes" )){
		  operation_notes=  nicEditors.findEditor( "operation_notes" ).getContent(); 
	  }
	  if( nicEditors.findEditor( "pasthistorylok" )){
		  pasthistory=  nicEditors.findEditor( "pasthistorylok" ).getContent(); 
	  }
	  
	  document.getElementById("histrydiv").value='1';
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"familyhist":""+encodeEntity(familyhistlok)+"", 
			"personalhist":""+encodeEntity(personalhistlok)+"", 
			"earlierinvest":""+encodeEntity(earlierinvestlok)+"", 
			"suggestedtrtment":""+encodeEntity(suggestedtrtmentlok)+"", 
			"surgicalnotes":""+encodeEntity(surgicalnotes)+"", 
			"diethist":""+encodeEntity(diethist)+"", 
			"birthhist":""+encodeEntity(birthhist)+"", 
			"developmenthist":""+encodeEntity(developmenthist)+"", 
			"emmunizationhist":""+encodeEntity(emmunizationhist)+"",
			"discharge_default":""+encodeEntity(discharge_default)+"",
			"onexaminationlok":""+encodeEntity(onexaminationlok)+"",
			"operation_notes":""+encodeEntity(operation_notes)+"",
			"pasthistory":""+encodeEntity(pasthistory)+"",
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveHistoryDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 

	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	
	 if(dischargestatus!=3){
		 document.getElementById('btn4').click();
		 document.getElementById('dropdown1').value='SURGICAL';
		 }
		 else{
		 document.getElementById('btn5').click();
		 document.getElementById('dropdown1').value='DEATH';
		 }
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}


function getHospitalCourseDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("hospital_coursediv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getHospitalCourseDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "hospitalcourse" )){
			  nicEditors.findEditor( "hospitalcourse" ).setContent(data.hospitalcourse); 
		  }
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}



function saveHospitalCourseDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var hospitalcourse="";
	var ipdid= document.getElementById("jsonipdid").value;
	 if( document.getElementById( "hospitalcourse" )){
		 hospitalcourse=nicEditors.findEditor( "hospitalcourse" ).getContent(); 
	  }
	 
	 document.getElementById("hospital_coursediv").value='1';
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"hospitalcourse":""+encodeEntity(hospitalcourse)+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveHospitalCourseDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn7').click();
	 document.getElementById('dropdown1').value='TG';
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}



function getTearmentGivenDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("treatmnt_givendiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getTearmentGivenDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "treatmentgiven" )){
			  nicEditors.findEditor( "treatmentgiven" ).setContent(data.treatmentgiven); 
		  }
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}


function getOTHERDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("otherdiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getOtherDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "finddis" )){
			  nicEditors.findEditor( "finddis" ).setContent(data.findondischarge); 
		  }
		  if( document.getElementById( "discadvnotes" )){
			  var eta=data.discadvnotes;
				
			  nicEditors.findEditor( "discadvnotes" ).setContent(eta); 
			  
		  }
		  if( document.getElementById( "kunal_manual_medicine_text" )){
			  nicEditors.findEditor( "kunal_manual_medicine_text" ).setContent(data.kunal_manual_medicine_text); 
		  }
	
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	
}

function replaceAll(str, find, replace) {
	var x=true;
    while(x){
    	if(str.includes(find)){
    		str=str.replace(find, replace);
    	}else{
    		x=false;
    	}
    }
    return str;
}




function encodeEntity(str) {
	/*str=str.replace(/(<([^>]+)>)/ig,"");*/
	var buf = [];
	var status=true;
	str=replaceAll(str,'&nbsp;',' ');
	for (var i=str.length-1;i>=0;i--) {
		var x=str[i];
		if(str[i]=='>'){
			status=false;
		}
		
		if(status){
			buf.unshift(['&#', str[i].charCodeAt(), ';'].join(''));
		}else{
			buf.unshift(str[i]);
		}
		if(str[i]=='<'){
			status=true;
		}
		
	}
	var data=buf.join('');
	
	
	
	return data;
}


 function decodeEntity(str) {
	return str.replace(/&#(\d+);/g, function(match, dec) {
		return String.fromCharCode(dec);
	});
}


function saveOtherDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var finddis="",discadvnotes="",kunal_manual_medicine_text="";
	var ipdid= document.getElementById("jsonipdid").value;
	  if( document.getElementById( "finddis" )){
		  finddis=encodeEntity(nicEditors.findEditor( "finddis" ).getContent()); 
	  }
	  if( document.getElementById( "discadvnotes" )){
		  discadvnotes= nicEditors.findEditor( "discadvnotes" ).getContent(); 
		
		  discadvnotes=encodeEntity(discadvnotes);
	
		 
	  }
	  if( document.getElementById( "kunal_manual_medicine_text" )){
		  kunal_manual_medicine_text=encodeEntity(nicEditors.findEditor( "kunal_manual_medicine_text" ).getContent()); 
	  }
	  
	  document.getElementById("otherdiv").value='1';
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"finddis":""+finddis+"", 
			"discadvnotes":""+discadvnotes+"", 
			"kunal_manual_medicine_text":""+kunal_manual_medicine_text+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveOtherDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json; charset=utf-8',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn10').click();
	 document.getElementById('dropdown1').value='EME';
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}




function getEmergencyDetailsDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("emergency_detdiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	
	 $.ajax({
		 
	  url : "getEmergencyDetailsDivDataOfDischargeFormCommonnew",
	 // url:"http://192.168.1.21:8080/HISTEST/getEmergencyDetailsDivDataOfDischargeFormCommonnew",
	  data : data1,
	
	  contentType : 'application/json',
	  type : 'POST',
	  dataType: 'json',
     
	  
	  
      
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "emercontdetail" )){
			  nicEditors.findEditor( "emercontdetail" ).setContent(data.emergencydetail); 
		  }
		 
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	
}
 

 
function saveEmergencydataDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var emercontdetail="";
	var ipdid= document.getElementById("jsonipdid").value;
	
	 if( document.getElementById( "emercontdetail" )){
		 emercontdetail=encodeEntity(nicEditors.findEditor( "emercontdetail" ).getContent()); 
	  }
	 document.getElementById("emergency_detdiv").value='1'
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"emergencydet":""+emercontdetail+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveEmergencyDetailsDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}


function getInvestigationDivDataOfDischargeForm(){
	var ipdid= document.getElementById("jsonipdid").value;
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("invst_div").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getInvstigationDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "investigation" )){
			  nicEditors.findEditor( "investigation" ).setContent(data.investigation); 
		  }
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}



function saveInvestigationDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var investigation="";
	var ipdid= document.getElementById("jsonipdid").value;
	var dischargestatus=document.getElementById("eddischargeStatus").value
	 if( document.getElementById( "investigation" )){
		 investigation=encodeEntity(nicEditors.findEditor( "investigation" ).getContent()); 
	  }
	document.getElementById("invst_div").value='1';
	
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"investigation":""+investigation+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveInvstigationDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 if(dischargestatus!=3){
		 document.getElementById('btn9').click();	
		 document.getElementById('dropdown1').value='MY1';
		 }
		 else{
		 document.getElementById('btn10').click();
		 document.getElementById('dropdown1').value='EME';
		 }	 
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}


function saveTreatmentGivenDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var treatmentgiven="";
	var ipdid= document.getElementById("jsonipdid").value;
	 if( document.getElementById( "treatmentgiven" )){
		 treatmentgiven=encodeEntity(nicEditors.findEditor( "treatmentgiven" ).getContent()); 
	  }
	 document.getElementById("treatmnt_givendiv").value='1';
	 
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"treatmentgiven":""+treatmentgiven+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveTreatmentGivenDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn8').click();
	 document.getElementById('dropdown1').value='INVESTIGATIONS';
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}


function saveCauseOfDeathDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var death_note="";
	var ipdid= document.getElementById("jsonipdid").value;
	 if( document.getElementById( "death_note" )){
		 death_note=encodeEntity(nicEditors.findEditor( "death_note" ).getContent()); 
	  }
	
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"death_note":""+death_note+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveDeathNoteDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn6').click();
	 document.getElementById('dropdown1').value='COURSE';
	 $('#disloaderPopup').modal( "hide" );
	 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}

function getCauseOfDeathDivDataOfDischargeForm(){
	$('#disloaderPopup').modal( "show" );
	var death_note="";
	var ipdid= document.getElementById("jsonipdid").value;
	
	
	 
	 var dataObj= {
			"ipdid":""+ipdid+"", 
		
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getDeathNoteDivDataOfDischargeFormCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		 
		  if( document.getElementById( "death_note" )){
			  nicEditors.findEditor( "death_note" ).setContent(data.death_note); 
		  }


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	
	 $('#disloaderPopup').modal( "hide" );
	
}



function saveDiagnosisDivDataOfDischargeForm(){
	
	var diagosis="0";
	var ipdid= document.getElementById("jsonipdid").value;
    $('.classD').each(function() { 
  		if(this.checked == true){
  			
  			diagosis=diagosis+","+this.value;
  		}
  								
  	});
    
    
    var disstatus="",disOutcome="",dischargedate="" ,dishour="",dismin="",reasonlamdama="";
    dischargedate=document.getElementById('dischargedate').value;
    
    disstatus=document.getElementById('eddischargeStatus').value;
    disOutcome=document.getElementById('eddischrgeOutcomes').value;
    dishour=document.getElementById('edithour').value;
    dismin=document.getElementById('editminute').value;
    reasonlamdama=nicEditors.findEditor( "tttr" ).getContent();
    var flag =true;
    if(disstatus=='0'){
    	flag=false;
    	alert("Please Select Discharge Status !");
    }
    if(diagosis=='0'){
    	flag=false;
    	alert("Please Select Diagnosis !");
    }
    
    if(flag){
    	$('#disloaderPopup').modal( "show" );
    	 var dataObj= {
    				"ipdid":""+ipdid+"", 
    				"diagosis":""+diagosis+"", 
    				"dischargedate":""+dischargedate+"", 
    				"disstatus":""+disstatus+"", 
    				"disOutcome":""+disOutcome+"", 
    				"dishour":""+dishour+"", 
    				"dismin":""+dismin+"", 
    				"reasonlamdama":""+reasonlamdama+"", 
    				
    		 };
    		 var data1 =  JSON.stringify(dataObj);
    		 $.ajax({

    		  url : "saveDiagnosisOfDischargeFormCommonnew",
    		  data : data1,
    		  dataType : 'json',
    		  contentType : 'application/json',
    		  type : 'POST',
    		  async : true,
    		  // beforeSend: function () { LockScreen(); },
    		  success : function(data) {
    			 
    			  


    		    },
    		  error : function(result) {
    		   console.log("error");
    		  }
    		 });
    		 document.getElementById('btn2').click();
    		 document.getElementById('dropdown1').value='ADMISSION';
    		 $('#disloaderPopup').modal( "hide" );
    		 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
    			
    			setTimeout(function() {
    				$("#popup_container").remove();
    				removeAlertCss();
    			}, alertmsgduration);
    }
	
}


function getSurgicalNotewsDivData(){
	var ipdid= document.getElementById("jsonipdid").value;
	
	if((document.getElementById("jsondiagnosis").value=='1')&&(document.getElementById("surgical_notesdiv").value=='0')){
		if(document.getElementById("diagnosisipdid").value!='0'){
			ipdid=document.getElementById("diagnosisipdid").value;
		}
		
	}
	 var dataObj= {
			"ipdid":""+ipdid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "getSurgicalNotewsDivDataCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  if( document.getElementById( "operation_notes" )){
			  nicEditors.findEditor( "operation_notes" ).setContent(data.operation_notes); 
		  }
		  


	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}

function saveSurgicalNotewsDivData(){
	$('#disloaderPopup').modal( "show" );
	var ipdid= document.getElementById("jsonipdid").value;
	var otnotes="";
	if( document.getElementById( "operation_notes" )){
		otnotes= nicEditors.findEditor( "operation_notes" ).getContent(); 
	  }
	var surgon, anesthsia, anestheologidt,procedure;
	if(document.getElementById( "surgeon" )){
		surgon=document.getElementById( "surgeon" ).value;
	}
	if(document.getElementById( "anesthesiologist" )){
		anestheologidt=document.getElementById( "anesthesiologist" ).value;
	}
	if(document.getElementById( "anesthesia" )){
		anesthsia=document.getElementById( "anesthesia" ).value;
	}
	if(document.getElementById( "appointmentText" )){
		procedure=document.getElementById( "appointmentText" ).value;
	}
	document.getElementById( "surgical_notesdiv" ).value='1'
	
	
	 var dataObj= {
			"ipdid":""+ipdid+"", 
			"otnotes":""+otnotes+"",
			"surgeon":""+surgon+"",
			"anesthesiologist":""+anestheologidt+"",
			"anesthesia":""+anesthsia+"",
			"procedure":""+procedure+"",
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveSurgicalNotewsDivDataCommonnew",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
	
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
	 document.getElementById('btn6').click();
	 document.getElementById('dropdown1').value='COURSE'
	 $('#disloaderPopup').modal( "hide" );
	 
	 
		jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
}

function funchange(Name){
	var n=Name;
	var btns=document.getElementById('b1');
	if(Name=='DIAGNOSIS'){
	document.getElementById('b1').onclick=function(){saveDiagnosisDivDataOfDischargeForm()};
	}
	else if(Name=='ADMISSION'){
	document.getElementById('b1').onclick=function(){saveAdmissionDivDataOfDischargeForm()};
	}
	else if(Name=='HISTORY'){
	document.getElementById('b1').onclick=function(){saveHistoryDivDataOfDischargeForm()};
	}
	else if(Name=='SURGICAL'){
	document.getElementById('b1').onclick=function(){saveSurgicalNotewsDivData()};
	}
	else if(Name=='DEATH'){
	document.getElementById('b1').onclick=function(){saveCauseOfDeathDivDataOfDischargeForm()};
	}
	else if(Name=='COURSE'){
	document.getElementById('b1').onclick=function(){saveHospitalCourseDivDataOfDischargeForm()};
	}
	else if(Name=='TG'){
	document.getElementById('b1').onclick=function(){saveTreatmentGivenDivDataOfDischargeForm()};
	}
	else if(Name=='INVESTIGATIONS'){
	document.getElementById('b1').onclick=function(){saveInvestigationDivDataOfDischargeForm()};
	}
	else if(Name=='MY1'){
	document.getElementById('b1').onclick=function(){saveOtherDivDataOfDischargeForm()};
	}
	else if(Name=='EME'){
	document.getElementById('b1').onclick=function(){saveEmergencydataDivDataOfDischargeForm()};
	}
	}

function showReasonBoxForLamaDama(){
	var status= document.getElementById('eddischargeStatus').value;
	
	if(status=='7'||status=='8'){
		/*document.getElementById('islamadama').style.display = "block";*/
		if(status=='7'){
			document.getElementById('islamadamatext').innerHTML='Reason For LAMA :';
		}else if(status=='8'){
			document.getElementById('islamadamatext').innerHTML='Reason For DAMA :';
		}
	}else{
		/*document.getElementById('islamadama').style.display ='none';*/
		document.getElementById('islamadamatext').innerHTML='Reason For LAMA/DAMA  :';
	}
}






var editor_id_globel='';
function getTemplateDataByAjax(id,editor_id){
	editor_id_globel=editor_id;
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getTemplateDataByAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function getTemplateDataByAjaxRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		        var nicedata= nicEditors.findEditor(editor_id_globel).getContent();	  
		        nicedata=nicedata+""+req.responseText;
				nicEditors.findEditor(editor_id_globel).setContent(nicedata);	          
         }
	}	 
}


function saveTemplateAjax(obj,boxId){
	
	var name=document.getElementById(boxId+'_tempname').value;
	if(name==''){
		  jAlert('error', 'Please Enter Template Name.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

	}else{
		document.getElementById(boxId+'_tempname').value='';
		var text=nicEditors.findEditor(boxId).getContent(); 
		text=encodeEntity(text);
		var dataObj= {
				"text":""+text+"", 
				"name":""+name+"", 
				"boxId":""+boxId+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "save_template_ajaxCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  document.getElementById(boxId+"_list").innerHTML=data.list;
			  
			  jAlert('success', 'Template Saved !.', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		    },
		  error : function(result) {
		   console.log("error");
		  }
		 });
	}
	
}





function saveDischargeFieldsTreatmentEpisode(val,column,ipdid ){
	var url="savedischargefieldsCommonnew?colid="+column+"&value="+val;
	
	 var dataObj= {
				"ipdid":""+ipdid+"", 
				"val":""+val+"", 
				"column":""+column+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "savedischargefieldsCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  
		  },
		  error : function(result) {
		   console.log("error");
		  }
		 });

}


function newTemplateSaveWay(templateid){
	var json={"templates":[
		  { "id":"", "name":"Doe" },
		  { "id":"", "name":"Smith" },
		  { "id":"", "name":"Jones" }
		]}
}


