

function isValid(){

   var fromamount=document.getElementById("fromamount").value;
   var toamount=document.getElementById("toamount").value;
   var tax=document.getElementById("tax").value;
   
   var isError=false;
   
   if(fromamount.length<1){
   
        isError=true;
        document.getElementById("errfromamount").innerText="please enter from amount!";
   }
   else {
   
       document.getElementById("errfromamount").innerText="";   
   }
   if(toamount.length<1){
    
        isError=true;
        document.getElementById("errtoamount").innerText="please enter to amount!";
   }
   else {
     
       document.getElementById("errtoamount").innerText="";  
   }
   if(tax.length<1){
    
        isError=true;
        document.getElementById("errtax").innerText="please enter tax amount!";
   }
   else {
     
       document.getElementById("errtax").innerText="";  
   }
 
   if(isError==true){
   
       return false;
       
   }
   else {
   
      return true;
   }
   
}


function addUpdateTax() {

   var t=isValid();
  
   if(t==true){
   var id=document.getElementById("id").value;
  
   if(id>0) {
       document.taxform.action="updateTax";
       document.taxform.submit();
   }
   else {
       document.taxform.action="addTax";
       document.taxform.submit();  
   }
  } 
}


function edittax(id) {

   var url="editTax?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = edittaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function edittaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("fromamount").value=data[1];
			document.getElementById("toamount").value=data[2];
			if(data[3]=="Male") {
			  document.getElementById("male").checked=true;
			}
			else if(data[3]=="Female") {
			 document.getElementById("female").checked=true;
			    
			}
 			document.getElementById("tax").value=data[4];
 			
 			$('#addtax	').modal( "show" );
 			
 		}
  }
}