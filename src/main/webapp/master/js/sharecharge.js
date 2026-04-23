
var totalChecked="0";

function updateShareCharges(){ 

	$('#baselayout1loaderPopup').modal("show");
	var chargeType=document.getElementById("chargeType").value;
    
   	$('.case').each(function() { // loop through each checkbox
		if (this.checked == true) {
			totalChecked = totalChecked + ',' + this.value;
		}
	});  
	
	var url="updateallSharecharge?chargeType="+chargeType+"&userids="+totalChecked+"";
	
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateShareChargesRequest;
			    req.open("GET", url, true); 
				req.send(null);

}

function updateShareChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
	
						window.location.reload();					 
				}
		}
 }





function getusers(val){

      var url = "getSharecharge?chargetype="+val+"";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getusersRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

}


function getusersRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
						 document.getElementById("scroll").innerHTML=req.responseText;
					 
				}
			}
 }

 
 var g=0;
 function selectAll(){
 
        if(g==0){
        
             $('.case').each(function() { // loop through each checkbox
				this.checked =true; 
			 });
			g=1;
        } else {
        
         	 $('.case').each(function() { // loop through each checkbox
				 this.checked =false; 
			 });		
        	g=0;
        }
 
 }
 
 