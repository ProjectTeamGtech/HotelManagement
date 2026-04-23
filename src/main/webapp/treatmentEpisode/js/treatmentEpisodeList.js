var selectedid = 0;
var selectedlimit = 0;
function showEditTreatmentEpisodePopup(id){
	
	selectedid = id;
	var url = "editTreatmentEpisode?selectedid="+id+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showEditTreatmentEpisodePopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function showEditTreatmentEpisodePopupRequest(){
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
			
			 $( "#addTreatmentEpisodeDiv" ).modal( "show" );
			 
			 
			
			
		}
	}
	
}


function editSaveTreatment(){
	 
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
               
    req.onreadystatechange = editSaveTreatmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    }
	
}


function editSaveTreatmentRequest(){
	
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 $( "#addTreatmentEpisodeDiv" ).modal( "hide" );
			 
			
			 
			 setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
			 
				
				
				jAlert('success', 'Treatment Episode has been updated successfully.', 'Success Dialog');
				
				goreferesh();
				
				
		}
		
	}
	
}

