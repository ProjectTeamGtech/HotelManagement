var totalsubjectid = 0;
$(document).ready(function(){
	
	$( "#invoiceDate" ).datepicker({
		 
		 dateFormat:'dd-mm-yy',
		 yearRange: yearrange,
		 minDate : '30-12-1880',
		 changeMonth: true,
	     changeYear: true
		 
			 
	 });
	document.getElementById('totalassesment').value = totalsubjectid;
	
	
	
	$("#assementtable").click(function() {
		
		
		totalsubjectid=0;
		
		  var chk=$("#assementtable :checked").size();
		 
		   if(chk > 1){
               
	  		  $("#assementtable :checked").each(function() {
				 //alert("value = " + $(this).val());
				totalsubjectid= totalsubjectid + "," +	 $(this).val();
				});
				
			}else{
				$("#assementtable :checked").each(function() {
					totalsubjectid = $(this).val();
				});
			}
			
			
			
			if(chk==1){
				totalsubjectid = '0,'+totalsubjectid;
			}
			
			document.getElementById('totalassesment').value = totalsubjectid;
			
		 
	});
	
	
});	

function raiseInvoice(){
	
	document.getElementById('submitInvoiceNotes').value=""+nicEditors.findEditor('submitInvoiceNotes').getContent();
	var chk=$("#assementtable :checked").size();
	var doctorid = document.getElementById('doctorid').value;
	var lmhac=document.getElementById('lmhac').value;
	if(doctorid=='0' && lmhac!='true'){
		jAlert('error', 'Please select doctor', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(chk==0){
		jAlert('error', 'Please select atleast one charge', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById('invcetype').value==0){
		jAlert('error', 'Please select invoice type', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	// $( "#baselayout1loaderPopup" ).modal( "show" );
	
	var invtype = $("#invcetype option:selected").text();
	 var t=confirm("Invoice Type:                 "+invtype+"");
      
      if(t==true){
				$( "#baselayout1loaderPopup" ).modal( "show" );
				document.getElementById('raiseinvoicefrm').submit();
		}else{
			$( "#baselayout1loaderPopup" ).modal( "hide" );
		}
		 //finalconfirmsubmitPopup2();
		//document.getElementById('raiseinvoicefrm').submit();
	}
}

function finalconfirmsubmitPopup2() {
	$( "#baselayout1loaderPopup" ).modal( "hide" );
	var invtype = $("#invcetype option:selected").text();
	document.getElementById("finalconfirmInvtype").innerHTML = invtype;
	$('#finalconfirmpopup').modal( "show" );	
}
function submitFinalConfirm2() {
	$( "#baselayout1loaderPopup" ).modal( "show" );
	document.getElementById('raiseinvoicefrm').submit();
}

function setTotalCharges(charges,selectedid){
	var total = document.getElementById('hdntotal').value;
	var chk = document.getElementById(selectedid).checked;
	
	if(chk == true ){
		total = parseInt(total)+ parseInt(charges);
	}
	if(chk == false ){
		total = parseInt(total)- parseInt(charges);
	}
	 document.getElementById('hdntotal').value = total;
	document.getElementById('totaltdid').innerHTML = currencySign + total;
	document.getElementById('balancetotalid').innerHTML = currencySign + total;
	document.getElementById('hdndebittotal').value = total;
}

function givPayment(){
	
	 if(document.getElementById('howpaid').value==0){
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		document.getElementById('paymentfrm').submit();
	}

}



function setAmountDueTotal(payment){
	
	document.getElementById('payAmount').value = payment;
	var totalamt1 = document.getElementById('hdntotal').value;
	if(parseFloat(payment) > parseFloat(totalamt1)){
		jAlert('error', 'Paid amt is greater than balance Amount.', 'Error Dialog');
	}
	
}