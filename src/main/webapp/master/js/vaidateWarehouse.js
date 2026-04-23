function validteWarehouse(){
 var check="0";

  $('.case').each(function() {
       
        
        if(this.checked==true)
        {
        
         check =check+","+this.value;
       }
      
      });
      
      if(check=="0"){
          jAlert('error', "Please, select warehouse.", 'Error Dialog'); 
       setTimeout(function() {
        $("#popup_container").remove();
        removeAlertCss();
       }, alertmsgduration);
        return false;
      }
      else
      {
      document.getElementById("warehouseids").value=check;
     return true;
     
      }
 
}

function validtepharmacy(){
	var check="0";

	  $('.case').each(function() {
	       
	        
	        if(this.checked==true)
	        {
	        
	         check =1;
	       }
	      
	      });
	      
	     
	      document.getElementById("pharmacycheck").value=check;
	     return true;
	     
}
function validteStripCheck(){
 var check="0";

  $('.case').each(function() {
       
        
        if(this.checked==true)
        {
        
         check =1;
       }
       else{
       check=0;
       }
      
      });
      
      
      document.getElementById("stripcheck").value=check;
     return true;
     
     
 
}

function validateInvSection(){

var name =document.getElementById("name").value;
  if(name=="")
  {
  jAlert('error', "Please, enter terms and condition.", 'Error Dialog'); 
       setTimeout(function() {
        $("#popup_container").remove();
        removeAlertCss();
       }, alertmsgduration);
        return false;
  }
  else
  {
  return true;
  }
}

function validateTermSection(){

var name =document.getElementById("name").value;
  if(name=="")
  {
  jAlert('error', "Please, enter terms and condition.", 'Error Dialog'); 
       setTimeout(function() {
        $("#popup_container").remove();
        removeAlertCss();
       }, alertmsgduration);
        return false;
  }
  else
  {
  return true;
  }
}