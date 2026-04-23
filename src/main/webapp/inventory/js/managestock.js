
function editstock(pid) {

     var url="editstockProduct?id="+pid+"";
     
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editstockRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
     
}


function editstockRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			
			        var str=req.responseText;
			        var data=str.split("~");
			        document.getElementById("id").value=data[0];
			        document.getElementById("category").value=data[1];
			        document.getElementById("subcategory").value=data[2];
			        document.getElementById("vendor").value=data[3];
			        document.getElementById("brand_id").value=data[4];
			        document.getElementById("product_code").value=data[5]; 
			        document.getElementById("product_name").value=data[6];
 					document.getElementById("mrp").value=data[7];			      
			        document.getElementById("purchase_price").value=data[8]; 
			        document.getElementById("sale_price").value=data[9]; 
			        document.getElementById("purchase_discount").value=data[10]; 
			        document.getElementById("sale_discount").value=data[11];
			        document.getElementById("weight").value=data[12];
			        document.getElementById("unit").value=data[13];
			        document.getElementById("description").value=data[14];
			
			       $("#editprod").modal("show");
		}
  }
   
}


function setdetails(id) {

    var url="setproductinfoProduct?selectedid="+id+"";
    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setdetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
     
     
}

function setdetailsRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			
			var data=req.responseText;
			var str=data.split("~");
			document.getElementById("product_code").value=str[0];
			document.getElementById("product_name").value=str[1];
			document.getElementById("mrp").value=str[2];
			document.getElementById("purchase_price").value=str[3];
			document.getElementById("sale_price").value=str[4];
			document.getElementById("purchase_discount").value=str[5];
			document.getElementById("sale_discount").value=str[6]; 
			document.getElementById("weight").value=str[7];
			document.getElementById("unit").value=str[8];
			document.getElementById("description").value=str[9];
			
			
		}
  }
}



function setsubdata(id) {
  
   var url="getsubproductsProduct?id="+id+"";
   
   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setsubdataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   

}

function setsubdataRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			
			     document.getElementById("subx1").innerHTML=req.responseText;
		}
  }
}

function setsubproductsdataRequest() {
  
  if (req.readyState == 4) {
			if (req.status == 200) {
			
			  document.getElementById("subx2").innerHTML=req.responseText;
		}
    }
}


function setsubproductsdata(id) {
  
   var url="getproductsProduct?id="+id+"";
   
   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setsubproductsdataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   

}
