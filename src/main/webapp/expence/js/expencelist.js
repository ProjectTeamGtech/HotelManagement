 var totalExpenceCheckbox = 0;
$(document).ready(function() {
	$('#selecctall').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.case').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.case').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
    });
});


function createReport(){
	totalExpenceCheckbox = 0;
	if(document.getElementById('reportName').value=='' ){

		jAlert('error', 'Please Enter Report Name.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
		  $('.case').each(function() { //loop through each checkbox
			  if(this.checked==true){
				  totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;   
			  }
          }); 
		  
		  if(totalExpenceCheckbox==0){
			  jAlert('error', 'Please select atlist one checkbox.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		  }else{
			  document.getElementById('totalExpenceCheckbox').value = totalExpenceCheckbox; 
			  
			  document.getElementById('expencereportfrm').submit();
			  
		  }
	}
}


function getSortedList() {
	
	document.expencereportfrm.action="ExpenceManagement";
	document.expencereportfrm.submit();
}


function printPdf(id) {
	
	var url = "printPdfExpenceManagement?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveChargePdfRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function saveChargePdfRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			jAlert('success', 'PDF Created successfully!!', 'Expence Report');
			
         }
	}
}

function printExcel(id) {
	
	var url = "printexcelExpenceManagement?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = printExcelRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function printExcelRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			chargesReportFilename = req.responseText;
			
			jAlert('success', 'Excel Sheet Created successfully!!', 'Expence Report');
			
         }
	}
}


function saveexpmngmntdata(){
	
	if(document.getElementById('epayment').value=='Opening'){
		
		document.getElementById('saveexpfrm').submit();
	}
	
	else if(document.getElementById('epayment').value=='Purchase'){
		
		document.getElementById('saveexpfrm').submit();
	}
	
	else if(document.getElementById('epayment').value=='Journal' && document.getElementById("editaction").value==0){
		if(ctotal != dtotal){
			jAlert('error', 'Credut and Debit total not matched!!.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			
			document.getElementById('saveexpfrm').submit();
		}
	
	}else{
		var ledger = document.getElementById('ledgername').value;
		if(ledger==0 && document.getElementById("editaction").value==0){
			
			jAlert('error', 'Please select ledger.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}else{
			
			var xpayment = document.getElementById('xpayment').value;
			
			if(xpayment=='Contra'){
				
				if(document.getElementById('contratrans').value==0){
					jAlert('error', 'Please select transaction type.', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}else{
					document.getElementById('saveexpfrm').submit();
				}
			}
			
			else{
				document.getElementById('saveexpfrm').submit();
			}
			
		}
	}
	

}



function addNewDebitors(){
	var debitor = document.getElementById('debitornametxt').value;
	var url = "debitorExpenceManagement?debitor="+debitor+" ";
	  
	 
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addNewDebitorsRequest;
	    req.open("GET", url, true); 
	    
	    
	              
	    req.send(null);
}

function addNewDebitorsRequest(){
	document.getElementById('newdebtorsdivid').innerHTML = req.responseText;
	
	 $("#debiorname").trigger("chosen:updated");
 	  $(".chosen").chosen({allow_single_deselect: true});
 	  
 	 jAlert('success', 'Record Saved!!.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}
