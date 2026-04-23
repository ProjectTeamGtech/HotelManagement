/**
 * 
 */

var flag = 0;
var totalExpenceCheckbox = 0;
var selectedproblems="0";

function selectAll() {

	if (flag == 0) {
		$('.case').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
		showreport();
		flag = 1;
		
	} else {
		$('.case').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
		showreport();
		flag = 0;
		
	}
}


function selectAllProb(i) {

	if (flag == 0) {
		$('.dcase'+i+'').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
		flag = 1;
	} else {
		$('.dcase'+i+'').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
	    								// "checkbox1"
		});
		flag = 0;
	}
	
	
	$('.dcase'+i+'').each(function() { // loop through each checkbox
		if (this.checked == true) {
			tid = tid + ',' + this.value;
		}
	}); 
	
	totalProblems=tid;

    showproblems();	
	
}


var totalconditions;

function showreport(val) {

    var totalExpenceCheckbox=0;	
 
    //  03 August 2018
    
	/*$('.case').each(function() { // loop through each checkbox
		if (this.checked == true) {
			totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;
		}
	});*/
    
    $('.case').each(function() { // loop through each checkbox
    	if(this.value==val){
    		totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;
		}else {
			this.checked=false;
		}
	});
    
    
	totalconditions=totalExpenceCheckbox;
	var url = "listproblemProblemListing?count=" + totalExpenceCheckbox + "&selectedpids="+tid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = showreportRequest;
	req.open("GET", url, true);

	req.send(null);
}

function showreportRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("gohere").innerHTML = req.responseText;
		}
	}

}

function submitData() {

	var str = totalconditions.split(",");

	for (var i = 0; i < str.length; i++) {
   
        var temp=0;
		if (i == 0) {
			continue;
		}
	 
	    $('.dcase'+i+'').each(function() { // loop through each checkbox
		if (this.checked == true) {
			 temp = temp + ',' + this.value;
		 }
	   });
	
		var url = "saveProblemListing?conditionid=" + str[i] + "&probemids="
				+ temp + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = submitDataRequest;
		req.open("GET", url, true);

		req.send(null);

	}

}


function submitDataRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			sendToConsult();
		}
	}
}




function showproblemstemp(tempid,chkbox) {
  	
        var t = (chkbox.checked) ? "true" : "false"; 
    
        if(t=="true") {
            var n=totalProblems.search(""+tempid+"");
            if(n==-1) {
              totalProblems = totalProblems + ',' +tempid;
               tid=totalProblems;
     	      showproblems();
     	    } 
     	} else {       
     	        var str=totalProblems.replace(""+tempid+"","0"); 
     	        totalProblems=str;
     	        tid=totalProblems;
     	        showproblems();
     	} 
  	
}

var totalProblems="0";	
var totalproblemscount="0";

var tid="0";	

function showproblems() {

	totalproblemscount=totalProblems;
	var url = "listinterventionProblemListing?countintervention=" + tid + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = showproblemsRequest;
	req.open("GET", url, true);

	req.send(null);

}

function showproblemsRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			//sendToConsult();
			document.getElementById("divspace").innerHTML=req.responseText;
		}
	}
}



function sendToConsult() {

	var url = "sendtoconsultProblemListing";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = sendToConsultRequest;
	req.open("GET", url, true);

	req.send(null);

}

function sendToConsultRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var tmp = data.split(",");
			document.getElementById("diaryUser").value = tmp[0];
			document.getElementById("condition").value = tmp[1];
			document.getElementById("consultform").submit();
		}
	}
}

function refreshData() {

    window.location.reload();
}



function showclinicalnotesproblem(val){
	var totalExpenceCheckbox = "0";
	totalExpenceCheckbox = totalExpenceCheckbox +','+val;
	var flag = false;
	$('.case').each(function() { // loop through each checkbox
		if(this.value==val){
			if (this.checked == true) {
				flag = true;
			}
		}
	});
	if(flag){
	
	  var dataObj={
	    "totalExpenceCheckbox" : "" + totalExpenceCheckbox + "",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setclinicalcareproblemProblemListing",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   document.getElementById("gohere").innerHTML = data.problemdiagnosis;
		   var surgicalnotes= nicEditors.findEditor( "maintextarea" ).getContent();
		   if(surgicalnotes=='' || surgicalnotes=='<br>'){
			   surgicalnotes = '<h4>Clinical Notes</h4><b>'+data.diagnosisname+'</b>';
		   }else{
			   surgicalnotes = surgicalnotes +'<br><b>'+data.diagnosisname+'</b>';
		   }
		   nicEditors.findEditor( "maintextarea" ).setContent(surgicalnotes); 
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	}
}

function showClinicalNotesIntervation(val){
	var totalExpenceCheckbox = "0";
	totalExpenceCheckbox = totalExpenceCheckbox +','+val;
	var flag = false;
	$('.dcase').each(function() { // loop through each checkbox
		if(this.value==val){
			if (this.checked == true) {
				flag = true;
			}
		}
	});
	if(flag){
	var dataObj={
	    "totalExpenceCheckbox" : "" + totalExpenceCheckbox + "",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setclinicalnotesiterventionProblemListing",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   document.getElementById("akashhere").innerHTML = data.problemdiagnosis;
		   var surgicalnotes= nicEditors.findEditor( "maintextarea" ).getContent();
		   if(surgicalnotes=='' || surgicalnotes=='<br>'){
			   surgicalnotes = '&nbsp&nbsp'+data.diagnosisname;
		   }else{
			   surgicalnotes = surgicalnotes +'<br>&nbsp&nbsp'+data.diagnosisname;
		   }
		   nicEditors.findEditor( "maintextarea" ).setContent(surgicalnotes); 
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	}
}


function setClinicalIntervation(val){
	var totalExpenceCheckbox = "0";
	totalExpenceCheckbox = totalExpenceCheckbox +','+val;
	
	var flag = false;
	$('.akashtest').each(function() { // loop through each checkbox
		if(this.value==val){
			if (this.checked == true) {
				flag = true;
			}
		}
	});
	if(flag){
	
	var dataObj={
	    "totalExpenceCheckbox" : "" + totalExpenceCheckbox + "",
	};
	var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setiterventionnotesProblemListing",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var surgicalnotes= nicEditors.findEditor( "maintextarea" ).getContent();
		   if(surgicalnotes=='' || surgicalnotes=='<br>'){
			   surgicalnotes = '&nbsp'+data.diagnosisname;
		   }else{
			   surgicalnotes = surgicalnotes +'&nbsp'+data.diagnosisname;
		   }
		   nicEditors.findEditor( "maintextarea" ).setContent(surgicalnotes); 
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	}
}

function setdayweekmonth(val) {
	  var surgicalnotes= nicEditors.findEditor( "maintextarea" ).getContent();
	   if(surgicalnotes=='' || surgicalnotes=='<br>'){
		   surgicalnotes = '&nbsp'+val;
	   }else{
		   surgicalnotes = surgicalnotes +'&nbsp'+val;
	   }
	   nicEditors.findEditor( "maintextarea" ).setContent(surgicalnotes); 
}


function submitDataNew(val){
	var flag =false; 
	var maintextarea= nicEditors.findEditor( "maintextarea" ).getContent();
	   if(maintextarea=='' || maintextarea=='<br>'){
		   flag =true;
		   jAlert('error', "Please select clinical notes", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	   }else{
		   $('#newloaderPopup').modal( "show" );
	   }
	   
	   
	if(!flag){
	var dataObj={
	    "maintextarea" : "" + maintextarea + "",
	};
	var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "saveclinicalnotesProblemListing",
	   data : data1,
	   dataType : 'json',
	   contentType: "application/json; charset=utf-8",
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   document.getElementById("diaryUser").value =  data.practiidd;
			document.getElementById("condition").value = data.condiiid;
			document.getElementById("consultform").submit();
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	}
}


function savetoclinicalnotesmaster(){
	var mastername= document.getElementById("clinicalnotestomaster").value;
	
	var f="0";

	$('.case').each(function() { 
		if(this.checked == true){
		 
		     f=f+","+this.value;
		} 
								
	});
	
	document.getElementById("clinicalnotestomasterdiv").className="hidden";
	var url = "saveclinicalnotesbyajaxProblemListing?master="+mastername+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = savetoclinicalnotesmasterRequest;
	req.open("GET", url, true);

	req.send(null);
}


function savetoclinicalnotesmasterRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("clinicalmasterlist").innerHTML = req.responseText;
		}
	}

}

function savetoclinicalproblemmaster(){
	var mastername= document.getElementById("clinicalproblemtomaster").value;
	var parentid= document.getElementById("problemparentid").value;
	var f="0";
	
	document.getElementById("clinicalproblemtomasterdiv").className="hidden";
	var url = "saveclinicalproblembyajaxProblemListing?master="+mastername+"&parentid="+parentid;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = savetoclinicalproblemmasterRequest;
	req.open("GET", url, true);

	req.send(null);
}


function savetoclinicalproblemmasterRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("clinicalproblembody").innerHTML = req.responseText;
		}
	}

}


function savetoclinicalintervationtomaster(){
	var mastername= document.getElementById("clinicalintervationtomaster").value;
	var parentid= document.getElementById("intervationparent").value;
	var f="0";
	
	document.getElementById("clinicalintervationtomasterdiv").className="hidden";
	var url = "saveclinicalintervationbyajaxProblemListing?master="+mastername+"&parentid="+parentid;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = savetoclinicalintervationtomasterRequest;
	req.open("GET", url, true);

	req.send(null);
}

function savetoclinicalintervationtomasterRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("intervationbody").innerHTML = req.responseText;
		}
	}

}

/*function addtoemrothertemplate(){
	var templatename=document.getElementById("templatename").value;
	if(templatename==""){
		alert("template name can't be blank ");
		return;
	}
	var text=  nicEditors.findEditor( "maintextarea" ).getContent();
	if(text==""){
		alert("Data can't be blank");
	}
	var ipdid=document.getElementById("ipdid").value;
	
	var url = "addtoothertemplatebyajaxProblemListing?templatename="+templatename+"&text33="+text+"&ipdid="+ipdid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = addtoemrothertemplateRequest;
	req.open("GET", url, true);

	req.send(null);
}

function addtoemrothertemplateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			alert("Saved");
			document.getElemetById("saveastemplate").className="hidden";
			document.getElemetById("saveastemplatebtn").className="";
		}
	}
}
*/



function addtoemrothertemplate(obj){
	var templatename=document.getElementById("templatename").value;
	if(templatename==""){
		alert("template name can't be blank ");
		return;
	}
	
	
	var text=  nicEditors.findEditor( "maintextarea" ).getContent();
	if(text==""){
		alert("Data can't be blank");
	}
	
	/*obj.style.display = "none";*/
	obj.style.visibility = "hidden";
	var ipdid=document.getElementById("ipdid").value;
	var dataObj={
	    "templatename" : "" + templatename + "",
	    "text" : "" + text + "",
	    "ipdid" : "" + ipdid + "",
	    
	};
	var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "addtoothertemplatebyajaxProblemListing",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   alert("Saved");
		   document.getElementById("templatename").value='';
		   obj.style.visibility = "visible";
			document.getElemetById("saveastemplate").className="hidden";
			document.getElemetById("saveastemplatebtn").className="";
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	
}

function searchcln(){
	var mastername= document.getElementById("cln").value;
	
	var url = "searchclnProblemListing?master="+mastername;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = searchclnRequest;
	req.open("GET", url, true);

	req.send(null);
}

function searchclnRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("clinicalmasterlist").innerHTML = req.responseText;
		}
	}
}

function searchclnprob(parentid){
var mastername= document.getElementById("clnprob").value;
	
	var url = "searchprobclnProblemListing?master="+mastername+"&parentid="+parentid;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = searchclnprobRequest;
	req.open("GET", url, true);

	req.send(null);
}

function searchclnprobRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("clinicalproblembody").innerHTML = req.responseText;
		}
	}
}



function searchclnintr(parentid){
	var mastername= document.getElementById("clnintr").value;
		
		var url = "searchintrclnProblemListing?master="+mastername+"&parentid="+parentid;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = searchclnintrRequest;
		req.open("GET", url, true);

		req.send(null);
	}

	function searchclnintrRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("intervationbody").innerHTML = req.responseText;
			}
		}
	}
