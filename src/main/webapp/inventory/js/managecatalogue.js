var selectedledgerid = '0,';
var selectedaheadid = '0,';

function saveLedgerServices(){
	
	  $('.catlogcase').each(function() { //loop through each checkbox
		  if(this.checked) {
			  selectedledgerid = selectedledgerid + this.value + ","; 
		  }
		              
      });
	  
	  document.getElementById('hdnledgerserviceid').value = selectedledgerid;
	  
	  if(document.getElementById('secstorename').value==0){
			jAlert('error', 'Please select store.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	  }else{
		  document.getElementById('ledgerfrm').submit();
	  }
	  
	 
	
}

