/**
 * 
 */
  var treatmentepisodeid=0;
  var patientId=0;
  


function showtpEdit(id,clientid){
	
	patientId=clientid;
	treatmentepisodeid = id;
	selectedid = id;
	var url = "editTreatmentEpisode?selectedid="+id+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showtpEditRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function showtpEditRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str = req.responseText;
			
			var temp = str.split("*");
			
			document.getElementById("treatmentType1").value = temp[0];
			$("#treatmentType1").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			document.getElementById("treatmentEpisodeName").value = temp[1];
			
			document.getElementById("referalDate").value = temp[2];
			
			document.getElementById("referralName").value = temp[3];
			
			document.getElementById("referralSource").value = temp[4];
			
			var payby = temp[5];
			
			if(payby=="Client"){
				
				document.getElementById('payby1').checked = true;
				document.getElementById('payby').checked = false;
			}else{
				document.getElementById('payby').checked = true;
				document.getElementById('payby1').checked = false;
			}
			
			document.getElementById('invoicee').value = temp[6];
			
			document.getElementById('authorisationCode').value = temp[7];
			
			document.getElementById('spendLimit').value = temp[8];
			document.getElementById('approvedLimit').value = temp[9];
			document.getElementById('consultationLimit').value = temp[10];
			selectedlimit =  temp[10];
			
			if(temp[11]=='false'){
				document.getElementById('urgent').checked = false;
			}else{
				document.getElementById('urgent').checked = true;
			}
			
			if(temp[12]=='0'){
				document.getElementById('opd').checked = true;
				document.getElementById('ipd').checked = false;
			}else{
				document.getElementById('ipd').checked = true;
				document.getElementById('opd').checked = false;
			}
			
			var refenddate = temp[13];
			document.getElementById('referalendDate').value = refenddate;
			
			document.getElementById("btnsave").className="btn btn-primary disblebtnone";
			document.getElementById("btnsubmit").className="btn btn-primary disblebtnone";
			document.getElementById("btnapproved").className="btn btn-primary disblebtnone hidden";
			document.getElementById("btnreject").className="btn btn-primary disblebtnone hidden";
			 $( "#addTreatmentEpisodeDiv" ).modal( "show" );
			 
		}
	}
	
}








function showTemplateDetails(){
		//	alert(userNameLetter);
			
			var id1 = document.getElementById("id").value;
			var templateId = document.getElementById('templateName');
			 var templateName = templateId.options[templateId.selectedIndex].text;
			 var tempId = document.getElementById('templateName').value;
			 //alert(tempId);
			// document.getElementById("userError").innerHTML = "";
			/* var user = document.getElementById('userletter').value;

				if(user == ""){		
					var userl = document.createElement("label");
					userl.innerHTML = "Please select User name";
				    document.getElementById('userError').appendChild(userl);
				    return false;  
				}
			    else
			    {*/
			// alert(id1);
			var userNameLetter = 'Third Party';
			var url = "getTemplateDetailsTPReferal?templateName="+templateName+"&userName="+userNameLetter+"&id1="+id1+"&tempId="+tempId+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setTemplateDetailsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null); 
		    
		   // return true;  
			   // }
				
		}
		function setTemplateDetailsRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					var temp = data.split("#");
					var template = temp[0];			
					var email = temp[1];
					var subject = temp[2];
					//document.getElementById('pdfFileIdLetter').style.display = 'none';
					
					document.getElementById('gpLetterEmail').value = email;
					document.getElementById('gpLettersubject').value = subject;
					
					setTimeout(function(){
						$(".nicEdit-main").html(template);			
					}, 1000);
					
					document.getElementById('pdfFileIdLetter').style.display = 'none';
					document.getElementById('sendmail').style.display = 'none';
					document.getElementById('printId').style.display = 'none';
				}
			}
		}
		



function openMailPopup(clientid,id){
	
	var url="setmailTpa?clientid="+clientid+"&treatmentepisodeid="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = openMailPopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}


function openMailPopupRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				var str=req.responseText;
				var data=str.split("~");
				document.getElementById("userNameletter").value=data[0];
				document.getElementById("id").value=data[1];
				document.getElementById("treatmentEpisodeId").value=data[2];
				$('#sendLetterPopup').modal( "show" );
	         }
		}
	}



function submitandsavetp(){
	
	var conitionid = document.getElementById('treatmentType1').value;
	var tepisodename = document.getElementById('treatmentEpisodeName').value;
	var referalDate = document.getElementById('referalDate').value;
	var referalendDate = document.getElementById('referalendDate').value;
	var referralName = document.getElementById('referralName').value;
	var referralSource =  document.getElementById('referralSource').value;
	var urgent = document.getElementById('urgent').checked;
	var consultationLimit = document.getElementById('consultationLimit').value;
	
	var payby = "";
	
	if(document.getElementById('payby1').checked==true){
		payby = "Client";
	}else{
		payby = "Third Party";
	}
	
	
	var invoicee =  document.getElementById('invoicee').value;
	
	var authorisationCode = document.getElementById('authorisationCode').value;
	
	var spendLimit = document.getElementById('spendLimit').value;
	var approvedLimit = document.getElementById('approvedLimit').value;

	var consultationLimit = document.getElementById('consultationLimit').value;
	
	 var ipdopd = '0';
	 if(document.getElementById('opd').checked==true){
		 ipdopd = '0';
	 }else{
		 ipdopd = '1';
	 }
	 
	// openIpdPopup('inputIpd');
	if(tepisodename==''){
		jAlert('error', 'Please enter treatment episode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(referalDate==''){
		jAlert('error', 'Please enter referal from date.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(referalendDate==''){
		jAlert('error', 'Please enter referal end date.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(consultationLimit==''){
		jAlert('error', 'Please enter referal end date.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	
	var url = "editsaveTreatmentEpisode?selectedid="+selectedid+"&conitionid="+conitionid+"&tepisodename="+tepisodename+"&referalDate="+referalDate+"&referralName="+referralName+"&referralSource="+referralSource+"&payby="+payby+"&invoicee="+invoicee+"&authorisationCode="+authorisationCode+"&spendLimit="+spendLimit+"&approvedLimit="+approvedLimit+"&consultationLimit="+consultationLimit+"&urgent="+urgent+"&ipdopd="+ipdopd+"&referalendDate="+referalendDate+" ";
	
	
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = submitandsavetpRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    }
	
}


function submitandsavetpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				$('#addTreatmentEpisodeDiv').modal( "hide" );
				openMailPopup(patientId,treatmentepisodeid);
	         }
		}
	}





function openApprovedOrRejectPop(id,clientid) {
	
	patientId=clientid;
	treatmentepisodeid = id;
	var url = "editTreatmentEpisode?selectedid="+id+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = openApprovedOrRejectPopRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function openApprovedOrRejectPopRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				var str = req.responseText;
				
				var temp = str.split("*");
				
				document.getElementById("treatmentType1").value = temp[0];
				$("#treatmentType1").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				
				document.getElementById("treatmentEpisodeName").value = temp[1];
				
				document.getElementById("referalDate").value = temp[2];
				
				document.getElementById("referralName").value = temp[3];
				
				document.getElementById("referralSource").value = temp[4];
				
				var payby = temp[5];
				
				if(payby=="Client"){
					
					document.getElementById('payby1').checked = true;
					document.getElementById('payby').checked = false;
				}else{
					document.getElementById('payby').checked = true;
					document.getElementById('payby1').checked = false;
				}
				
				document.getElementById('invoicee').value = temp[6];
				
				document.getElementById('authorisationCode').value = temp[7];
				
				document.getElementById('spendLimit').value = temp[8];
				
				document.getElementById('approvedLimit').value = temp[9];
				document.getElementById('consultationLimit').value = temp[10];
				selectedlimit =  temp[10];
				
				if(temp[11]=='false'){
					document.getElementById('urgent').checked = false;
				}else{
					document.getElementById('urgent').checked = true;
				}
				
				if(temp[12]=='0'){
					document.getElementById('opd').checked = true;
					document.getElementById('ipd').checked = false;
				}else{
					document.getElementById('ipd').checked = true;
					document.getElementById('opd').checked = false;
				}
				
				var refenddate = temp[13];
				document.getElementById('referalendDate').value = refenddate;
				
				document.getElementById("tepid").value=treatmentepisodeid;
				
				document.getElementById("btnsave").className="btn btn-primary disblebtnone hidden";
				document.getElementById("btnsubmit").className="btn btn-primary disblebtnone hidden";
				document.getElementById("btnapproved").className="btn btn-primary disblebtnone";
				document.getElementById("btnreject").className="btn btn-primary disblebtnone";
				 $( "#addTreatmentEpisodeDiv" ).modal( "show" );
				
	         }
		}
	}











 function openCnfPopup(id){
	 document.getElementById("tepid").value=id;
	 $('#rejaccept').modal( "show" );
 }

 function rejectCnfPopup(){
	 
	 
	 document.getElementById("tpaform").action="rejectformTpa";
	 document.getElementById("tpaform").submit();
	 
 }
 
function cnfirmPopup(){
	 
	 
	 document.getElementById("tpaform").action="acceptformTpa";
	 document.getElementById("tpaform").submit();
	 
 }
 
function deletetpa(val){
	document.getElementById("canceltpaid").value = val;
	$('#deletetpa').modal( "show" );
}

function deletetpaDetails(){
	 var id = document.getElementById("canceltpaid").value;
		var delete_reason = document.getElementById("delete_reason").value;
		var adarsh = "deletedataTpa?id="+id+"&delete_reason="+delete_reason+"";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deletetpaDetailsRequest;
		  req.open("GET", adarsh, true); 
		  req.send(null);  
  }
	function deletetpaDetailsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();	 
		         }
			}	 
} 