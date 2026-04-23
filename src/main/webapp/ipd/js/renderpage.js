/**
 * 
 */

var count=0;
$(document).ready(function() {

	var totalchrge=0;
	var chargeid='';
	var balnceamt='';
	//alert("hello");
	
	
	 $('#selecctall').click(function(event) {  //on click 
         if(this.checked) { // check select status
             $('.case').each(function() { //loop through each checkbox
                 this.checked = true;//select all checkboxes with class "checkbox1"
             });
         }else{
             $('.case').each(function() { //loop through each checkbox
                 this.checked = false; //deselect all checkboxes with class "checkbox1"                       
             });         
         }
     });
	 
	 $('.textcase').each(function(){
	     
		 totalchrge=parseFloat(totalchrge)+parseFloat(this.value);
		 chargeid=this.id+","+chargeid;
		 balnceamt=this.value+","+balnceamt;
	 });
	 
	 document.getElementById("hiddentotalchrge").value=totalchrge;
	 document.getElementById("hiddenchargeid").value=chargeid;
	 document.getElementById("hiddenbalnceamount").value=balnceamt;
});


function settotalchargedata(){
	
	var totalchrge=0;
	var chargeid='';
	var balnceamt='';
	count=0;
	
	$('.case').each(function() { //loop through each checkbox
       if(this.checked == true){//select all checkboxes with class "checkbox1"
    	      count++;
              totalchrge=parseFloat(totalchrge)+parseFloat(document.getElementById(this.value).value);
              chargeid=this.value+","+chargeid;
              balnceamt=document.getElementById(this.value).value+","+balnceamt;
      }
	
   });
	
	document.getElementById("hiddentotalchrge").value=totalchrge;
	document.getElementById("hiddenchargeid").value=chargeid;
	document.getElementById("hiddenbalnceamount").value=balnceamt;

}



function renderPayment(chargeid,totalamt){
	

	var totalchrge1=document.getElementById("hiddentotalchrge").value;
	var charge_id=document.getElementById("hiddenchargeid").value;
	var balnceamt=document.getElementById("hiddenbalnceamount").value;
	document.getElementById("to_charge").value=totalchrge1;
	
	//alert(count);
	
	if(count==1){
		$('#to_charge').prop('readonly', false);
	}else{
		
		$('#to_charge').prop('readonly', true);
	}
	
	if(document.getElementById("selecctall").checked==true){
		//document.getElementById("to_charge").readonly=true;
		$('#to_charge').prop('readonly', true);
	}else{
		$('#to_charge').prop('readonly', false);
	}
	
	$('#rnderpaymentpopup').modal('show');
	 
}

function saverenderpayment() {
	
	
	var paymenttype=document.getElementById("paymenttype").value;
	var charge_amount=document.getElementById("to_charge").value;
	var chargeid=document.getElementById("hiddenchargeid").value;
	var balnceamt=document.getElementById("hiddenbalnceamount").value;
	
	
	if (paymenttype == '') {

		jAlert('error', "Please select doctor!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}
	else{
	
	     $("#rnderpaymentpopup").modal( "show" );
	     var url ="saverenderpaymentStatement?paymenttype="+paymenttype+"&charge_amount="+charge_amount+"&chargeid="+chargeid+"&balnceamt="+balnceamt+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = saverenderpaymentRequest;
		req.open("GET", url, true);
		req.send(null);
		
	}
		
	}

function saverenderpaymentRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			$("#baselayout1loaderPopup").modal("hide");
			
		if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
				window.location.reload();
                     // success
			}
			
         }
		
     }
	
  }




