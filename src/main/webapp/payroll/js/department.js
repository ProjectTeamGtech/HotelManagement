
function isValidate(){

    var dept_name=document.getElementById("dept_name").value; 
    var rate=document.getElementById("rate").value;
    
    var isError=false;
    
    if(dept_name.length<1){
    
          document.getElementById("errdept_name").innerText="please enter dept name!";
          isError=true;
    }
    else {
     
        document.getElementById("errdept_name").innerText="";  
    }
    
    if(rate.length<1){
        document.getElementById("errrate").innerText="please enter rate!";
        isError=true;
    }
    else {

        document.getElementById("errrate").innerText=""; 
    }

    if(isError==true){
        return false;
    }     
    else {
      return true;
    }
   

}

 

function addEditDept() {
 
   var t=isValidate(); 
   if(t==true){ 
  
    var id=document.getElementById("id").value; 
    
    if(id>0) {
    
           document.deptform.action="updatePayrollDepartment"; 
           document.deptform.submit();
    
         //edit code
    }
    else {
       
         document.deptform.action="addPayrollDepartment"; 
         document.deptform.submit();
        
    }
  } 
}

function showaddPopup(){ 

            document.getElementById("id").value="";            			
			document.getElementById("dept_name").value="";
			document.getElementById("rate").value="";
 			
 			$('#adddepart').modal( "show" );
}


function editDepartment(id) {

   var url="editPayrollDepartment?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editDepartmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function editDepartmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("dept_name").value=data[1];
			document.getElementById("overtime_status").value=data[2];
			document.getElementById("rate").value=data[3];
 			
 			$('#adddepart').modal( "show" );
 			
 		}
  }
}

function editCompanyData(){
   
    var url="editPayrollDashBoard?selectedid=1";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editCompanyDataRequest;
    req.open("GET", url, true); 
              
    req.send(null); 
}

function editCompanyDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("#");
			document.getElementById("company_name").value=data[1];
			document.getElementById("date_format").value=data[2];
			document.getElementById("itmonth").value=data[3];
			document.getElementById("tinno").value=data[4];
			document.getElementById("pan_no").value=data[5];
			document.getElementById("esi_no").value=data[6];
			document.getElementById("pf_no").value=data[7];
			document.getElementById("hourly_type").value=data[8];
			document.getElementById("fixed_hour").value=data[9];
			document.getElementById("no_hours").value=data[10];
			document.getElementById("ot_status").value=data[11];
			document.getElementById("permissions").value=data[12];
 			document.getElementById("permi_penalty").value=data[13];
 			document.getElementById("no_permission").value=data[14];
 			
 			
 			$('#myModal').modal( "show" );
 			
 		}
  }
}





