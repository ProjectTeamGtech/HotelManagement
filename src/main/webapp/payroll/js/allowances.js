
function isValid(){

   var allowance_name=document.getElementById("allowance_name").value;
   var value=document.getElementById("value").value;
  
   var isError=false;
   
   if(allowance_name.length<1){
     
        document.getElementById("errallowance_name").innerText="please enter allowance name!";
        isError=true;
   }
   else {
       document.getElementById("errallowance_name").innerText="";
   }
   
   if(value.length<1){
       
        document.getElementById("errvalue").innerText="please enter value!";
        isError=true;
   }
   else {
        document.getElementById("errvalue").innerText="";
    }
   
    if(isError==true){
    
        return false;
    }
    else {
       return true;
    }
    
}

function addEditAllowance(){

   var t=isValid();

   if(t==true){

    var id=document.getElementById("id").value; 
    
    if(id>0) {
    
        document.allowanceform.action="updatePayrollAllowance";
        document.allowanceform.submit();
        
    } else {
    
        document.allowanceform.action="addPayrollAllowance";
        document.allowanceform.submit();
    }   
  }       
}

function editAllowance(id) {

    var url="editPayrollAllowance?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editAllowanceRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
}

function editAllowanceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("allowance_name").value=data[1];
			document.getElementById("allowanceType").value=data[2];
			document.getElementById("value").value=data[3];
 			
 			$('#addallow').modal( "show" );
 			
 		}
    }
}

function addPopup() {

            document.getElementById("id").value="";            			
			document.getElementById("allowance_name").value="";
			document.getElementById("value").value="";
 			
 			$('#addallow').modal( "show" );
  
}



function calculateTotalHour() {

     
      var count=Number(document.getElementById("count").value);        
      
      for(var i=0;i<count;i++){
      
    	  var basicsalary=Number(document.getElementById("basicsalary"+i+"").value);
    	  var days=Number(document.getElementById("days"+i+"").value);
    	  
    	  var test = new Date();
    	  var year= test.getFullYear();
    	  var month=Number(document.getElementById("month").value);
    	  
    	  var daysInMonth= new Date(year, month+1, 0).getDate();
    	  var perday = basicsalary/daysInMonth;
    	  
    	  
    	  var salaryTotal= perday * days;
    	  salaryTotal = roundTwo(salaryTotal);
    	   
    	  document.getElementById("salaryTotal"+i+"").value=salaryTotal;
          /*var monday=Number(document.getElementById("monday"+i+"").value);
          var tuesday=Number(document.getElementById("tuesday"+i+"").value);
          var wednesday=Number(document.getElementById("wednesday"+i+"").value);
          var thursday=Number(document.getElementById("thursday"+i+"").value);
          var friday=Number(document.getElementById("friday"+i+"").value);
          var saturday=Number(document.getElementById("saturday"+i+"").value);
          var sunday=Number(document.getElementById("sunday"+i+"").value);
          
          var total=monday+tuesday+wednesday+thursday+friday+saturday+sunday;
          
          document.getElementById("total_hour"+i+"").value=total;*/
          
      }  
      
     
}

function roundTwo(val){
	 
	   val =Math.round(val * 100) / 100;
	   //val=Math.floor(val);
	   
	   return val;
	}
















