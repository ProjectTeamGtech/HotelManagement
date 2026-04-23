function addbrand() {

    var vendor=document.getElementById("vendor").value;
    
    var brand=document.getElementById("brand").value;
    
    if(brand.length<1) {
    
        jAlert('error', 'Please Enter Brand Name', 'Error Dialog');   
    } 
    else {
    	var url="addbrandInventory?vendor="+vendor+"&brand="+brand+"";
   
   		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addbrandRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
    }
}


function addbrandRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			    
			     var id=req.responseText;
			     id=parseInt(id);
			     document.xyzname.id.value=id;
			     document.xyzname.submit();
			  }
	}
}

function confirmdelete(brandid) {

        var t=confirm("Are you sure to delete?");
        if(t==true){
          
          var vendorid=document.getElementById("vendor").value;
          
          
           var url="deletebrandInventory?vendorid="+vendorid+"&brandid="+brandid+"";
   
   			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    	req.onreadystatechange = confirmdeleteRequest;
	    	req.open("GET", url, true); 
	              
	    	req.send(null);        
        
        } 
}

function confirmdeleteRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			    
			     var id=req.responseText;
			     id=parseInt(id);
			     document.xyzname.id.value=id;
			     document.xyzname.submit();
			  }
	}
}

var tempid=0;
function editbrnd(brandid,rowcount) {

            tempid=brandid;
            var vendorid=document.getElementById("vendor").value; 
            var url="editbrandInventory?brandid="+brandid+"&vendorid="+vendorid+"&row="+rowcount+"";
   
   			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    	req.onreadystatechange = editbrndRequest;
	    	req.open("GET", url, true); 
	              
	    	req.send(null);        
 

}


function editbrndRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			    
			      document.getElementById(tempid).innerHTML=req.responseText;
			  }
	}
}


function savebrand(brandid) {
			 
			 tempid=brandid;
             var brand=document.getElementById("b"+brandid).value;
           
             var url="updatebrandInventory?brandid="+brandid+"&brand="+brand+"";
   
   			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    	req.onreadystatechange = savebrandRequest;
	    	req.open("GET", url, true); 
	              
	    	req.send(null);   
}


function savebrandRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			    
			      var vendor=document.getElementById("vendor").value;
			      document.xyzname.id.value=vendor;
			      document.xyzname.submit();
			  }
	}
}

function reloadthis(){

    var vendor=document.getElementById("vendor").value;
			      document.xyzname.id.value=vendor;
			      document.xyzname.submit();
}

