var selectedledgerid = '0,';
var selectedaheadid = '0,';





$(document).ready(function() {
	
	var dbselectedservices =  document.getElementById('dbselectedservices').value;
	
	var tmp = dbselectedservices.split(',');
	
	for(i=1;i<tmp.length;i++){
		document.getElementById('chledger'+tmp[i]).checked = true;
	}
	
});
function saveLedgerServices(){
	
	  $('.ledgercase').each(function() { //loop through each checkbox
		  if(this.checked) {
			  selectedledgerid = selectedledgerid + this.value + ","; 
		  }
		              
      });
	  
	  document.getElementById('hdnledgerserviceid').value = selectedledgerid;
	  
	  if(document.getElementById('ledgername').value==0){
			jAlert('error', 'Please select ledger.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	  }else{
		  document.getElementById('ledgerfrm').submit();
	  }
	  
	 
	
}


function showSelectedServices(){
	
	document.getElementById('selectedledgernameid').value = document.getElementById('ledgername').value;
	document.getElementById('servicefrm').submit();
}

function addnewLedger(){
	document.getElementById('hdnnewledger').value  = document.getElementById('newledger').value ;
	document.getElementById('hdnhowpaid').value  = document.getElementById('howpaid').value ;
	document.getElementById('bnkname').value  = document.getElementById('hdnbnkname').value ;
	document.getElementById('ltype').value  = document.getElementById('xltype').value ;
	
	document.getElementById('aheadname').value  = document.getElementById('aheadnamex').value ;
	document.getElementById('opbal').value  = document.getElementById('opbalx').value ;
	
	
	if(document.getElementById('newledger').value==''){
		jAlert('error', 'Please enter ledger name.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	else if(document.getElementById('xltype').value!=0 && document.getElementById('howpaid').value==0){
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(document.getElementById('xltype').value==0 && document.getElementById('howpaid').value!=0){
		jAlert('error', 'Please select ledger type.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(document.getElementById('aheadname').value==0){
jAlert('error', 'Please select account head.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}
	
	else{
		document.getElementById('newledgerfrm').submit();
	}
	
	
}

function viewledgerreport(){
	var ledger = document.getElementById('ledgername').value;
	var lname = $("#ledgername option:selected").text();
/*	if(ledger==0){
		
		jAlert('error', 'Please select ledger.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		openDisplayPopup('viewledrportAppointmentType?ledger='+ledger+'&lname='+lname+'');
	}*/
	
	openDisplayPopup('viewledrportAppointmentType?ledger='+ledger+'&lname='+lname+'');
}



//account head js
function addnewahead(){
	
	document.getElementById('hdnnewahead').value  = document.getElementById('newahead').value ;
	document.getElementById('hdnnewobal').value  = document.getElementById('openingbal').value ;
	document.getElementById('actype').value  = document.getElementById('hdnactype').value ;
	
	document.getElementById('aheadname22').value  = document.getElementById('aheadnamex22').value ;
	
	if(document.getElementById('newahead').value==''){
jAlert('error', 'Please Enter New Account Head.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	else if(document.getElementById('actype').value=='0'){
		jAlert('error', 'Please select account type!!.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
	
/*	else if(document.getElementById('openingbal').value=='0'){
		jAlert('error', 'Please Enter Opening Balance.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}*/
	
			else{
				document.getElementById('newaheadfrm').submit();
			}
	
}


function saveAheadServices(){
	
	  $('.chaheadcase').each(function() { //loop through each checkbox
		  if(this.checked) {
			  selectedaheadid = selectedaheadid + this.value + ","; 
		  }
		              
      });
	  
	  document.getElementById('hdnaheadserviceid').value = selectedaheadid;
	  
	  if(document.getElementById('aheadname').value==0){
			jAlert('error', 'Please select account head.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	  }else{
		  document.getElementById('aheadfrm').submit();
	  }
	
}

function showSelectedAheadServices(){
	
	document.getElementById('selectedledgernameid').value = document.getElementById('aheadname').value;
	document.getElementById('hdnsearchtext').value = document.getElementById('searchtext').value;
	var lname = $("#aheadname option:selected").text();
	
	document.getElementById('aheadnametxt').value = lname;
	document.getElementById('servicefrm').submit();
}

function updateledgers(){
	var editledgerid=document.getElementById('editledgerid').value;
	var ledgername=document.getElementById('editedledger').value;
	var url = "updateledgernameCommonnew?ledgerid="+editledgerid+"&ledgername="+ledgername;
	if(ledgername==''||editledgerid==''){
		jAlert('error', 'Please select account .', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		document.getElementById('ledn'+editledgerid).innerHTML=ledgername;
		document.getElementById('toedit').innerHTML=ledgername;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateledgersReq;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
}
function updateledgersReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			 jAlert('success', 'Data Saved Successfully !.', 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
}




function dledger(id){
	if(confirm("Do you want to delete ledger ?")){
		var url = "delledgerCommonnew?ledgerid="+id;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = dledgerReq;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
}


function dledgerReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var status=req.responseText;
			if(status=='1'){
				alert('The Ledger is used !');
			}else if(status=='2'){
				alert('Done');
			}
		}
	
	}
}
