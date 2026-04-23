

var cell;
var row;
function addsaleProduct(tableId) {
  
    var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;
    var url = "addsaleproductProduct?rowcount="+counts+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addsaleProductRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addsaleProductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
            
            	 	 row.innerHTML=req.responseText;
         }
	}	 
}



var tid=0;

function getprodtype(id,index) {
  
    tid=index;
    var url = "getsubproductssaleProduct?id="+id+"&index="+index+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getprodtypeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
}

function getprodtypeRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("tdsub"+tid).innerHTML=req.responseText;
         }
	}	 
}


function getsalesproduct(id,index) {
    tid=index;
    var url = "getsalesproductProduct?vendorid="+id+"&index="+index+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getsalesproductRequest;
    req.open("GET", url, true); 
              
    req.send(null);
  
}


function getsalesproductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("tdprod"+tid).innerHTML=req.responseText;
         }
	}	 
}


function getstockandprice(id,index){

    tid=index;
    var url = "getproddetailsProduct?pid="+id+"&index="+index+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getstockandpriceRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getstockandpriceRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
		      var str=req.responseText;
		      var data=str.split("~"); 
		      document.getElementById("minstock"+tid).innerHTML=data[0];
		      document.getElementById("saleprice"+tid).innerHTML="Rs. "+data[1];
         }
	}	 
}


function saleproduct(){

    document.getElementById("saletableform").submit();
}

function viewReturnpop(id){

    var url = "getsaledetailsProduct?id="+id+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = viewReturnpopRequest;
    req.open("GET", url, true); 
              
    req.send(null);
  
}


function viewReturnpopRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
		       document.getElementById("returnsale").innerHTML=req.responseText;
		       $('#returnstock').modal( "show" ); 
         }
	}	 
}

function updatesalestock() {

    var id=document.getElementById("saleidhidden").value;
    var returnqty=document.getElementById("retqty").value;
    
    var url="returnsaleqtyProduct?id="+id+"&returnqty="+returnqty+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatesalestockRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function updatesalestockRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		     window.location.reload();
         }
	}	 
}


function changeEntrySale(tval){

    document.getElementById("tmanual_entry").value=tval;
    document.getElementById("manualentryform").submit();
}



function addnewRowsale(tableId) {
      
    var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount-1;
	
	tid=counts;
    var url = "addmoresaleproductProduct?rowcount="+counts+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addnewRowsaleRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addnewRowsaleRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
            
            	 	 row.innerHTML=req.responseText;
            	 	 
            	 	 $("#quantity"+tid+"").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			        //display error message
			        	$("#errmsg").html("Digits Only").show().fadeOut("slow");
			               return false;
			    		}
			   });
            	 	 
         }
	}	 
}


function submitsaleProduct() {

    document.getElementById("saleproductform").submit();
}


function addproductBarcodesale(tableId,id){
   
   var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount-2;
    var url = "addmoresaleproductbarcodeProduct?rowcount="+counts+"&pid="+id+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addproductBarcodesaleRequest;
    req.open("GET", url, true); 
              
    req.send(null); 
    
}

function addproductBarcodesaleRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
            
            	 	 row.innerHTML=req.responseText;
         }
	}	 
}


function savebarodesale(){
  
   document.getElementById("barodeprocsale").submit();
}
