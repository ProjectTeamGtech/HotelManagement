

function addEditVendor() {

     var id=document.getElementById("id").value;
     
     if(id>0){
         
          //update 
           document.vendorform.action="updatevendorInventory";
           document.vendorform.submit();
          
     
     }else {
         //add
         document.vendorform.action="savevendorInventory";
         document.vendorform.submit();
     }

}

function vendorPopup() {

  
              document.getElementById("id").value="";
	    	  document.getElementById("name").value="";
	    	  document.getElementById("address").value="";
	    	  document.getElementById("email").value="";
	    	  document.getElementById("brand_name").value="";
	    	  document.getElementById("mobile_pri").value="";
	    	  document.getElementById("phone1").value=""; 
	          document.getElementById("min_delivery_time").value="";
	          
	    
	          $('#addvendor').modal( "show" );	    
 
}


function editVendor(id) {

   var url="getvendorInventory?selectedid="+id+"";
 
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = editVendorRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function editVendorRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
	    	 
	    	  var daa=req.responseText;
	    	  var data=daa.split("$");
	    	 
	    	  document.getElementById("id").value=data[0];
	    	  document.getElementById("name").value=data[1];
	    	  document.getElementById("address").value=data[2];
	    	  document.getElementById("email").value=data[3];
	    	  document.getElementById("brand_name").value=data[4];
	    	  document.getElementById("mobile_pri").value=data[5];
	    	  document.getElementById("phone1").value=data[7]; 
	          document.getElementById("min_delivery_time").value=data[9];
	          
	    
	          $('#addvendor').modal( "show" );	 
		}
	}
 }	
		
 
		
		
		
