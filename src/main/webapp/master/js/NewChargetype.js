/**
 * 
 */

function setsitting(id) {
	var url="getsittinglistNewChargeType?id="+id+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	req.onreadystatechange = setSittingRequest;
    req.open("GET", url, true); 
    req.send(null);
    
}
function setSittingRequest(){
	if(req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("proddiv").innerHTML=req.responseText;
			   $("#sitting_id").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
	   }
	}	 
}



function validatecharges(){
	
	var isprocedured=document.getElementById("procedure").checked;
	var department=document.getElementById("dept").value;
	var sitting=document.getElementById("sitting_id").value;
	var name=document.getElementById("name").value;
	
	var isFromLmh = document.getElementById("isFromLmh").value;
	
	if(name==''){
		jAlert('error', "Please select name!",'Error Dialog');
		return false;
    }else if(isprocedured==true && isFromLmh==1){
    	if(department=='0'){
    		jAlert('error', "Please select department!", 'Error Dialog');
			return false;
    	}else if(sitting=='0'){
    		jAlert('error', "Please select sitting!", 'Error Dialog');
			return false;
		}else{ 
			return true;
		}
	 }else{
		 return true;
	 }
}
