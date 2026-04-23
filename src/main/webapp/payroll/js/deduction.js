
function isValid(){

    var deduction=document.getElementById("deduction").value;
    var emp_contribution=document.getElementById("emp_contribution").value;
    var comp_contribution=document.getElementById("comp_contribution").value;
    var isError=false;
    
    if(deduction.length<1){
    
         isError=true;
         document.getElementById("errdeduction").innerText="please enter deduction !";
         
    }
    else {
       document.getElementById("errdeduction").innerText="";
        
    }
    if(emp_contribution.length<1){
        isError=true;
        document.getElementById("erremp_contribution").innerText="please enter the emp contribution";
    }
    else {
    
       document.getElementById("erremp_contribution").innerText="";
    }

    if(isError==true){
    
         return false;
    }
    else {
        return true;
    }
    
    
}



function addUpdateDeduction(){

   var t=isValid();

   if(t==true){

     var id=document.getElementById("id").value;   
   
     if(id>0){
     
         document.deductionform.action="updateDeduction";
         document.deductionform.submit();
     }
     else {
       
        document.deductionform.action="addDeduction";
        document.deductionform.submit();
         
     }
    } 
 }
 
 
 function editdeduction(id) {
 
   var url="editDeduction?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editdeductionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
 }
 
 function editdeductionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("deduction").value=data[1];
			document.getElementById("deduction_type").value=data[2];
			document.getElementById("emp_contribution").value=data[3];
			document.getElementById("comp_contribution").value=data[4];
 			
 			$('#adddeduc').modal( "show" );
 			
 		}
  }
}
 
 
 
 
 
 
 