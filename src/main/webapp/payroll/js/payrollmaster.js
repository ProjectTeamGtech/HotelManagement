

function isBankValidate() {

     var bank=document.getElementById("bank").value;
     var bank_account=document.getElementById("bank_account").value;
     var ifsccode=document.getElementById("ifsccode").value;
     var bankaddress= document.getElementById("bankaddress").value;
     var isError=false;
     
     if(bank.length<1){
      
          isError=true;
          document.getElementById("errbank").innerText="please enter bank name!";
     }
     else {
        document.getElementById("errbank").innerText="";
     }
     
     if(bank_account.length<1){
        
           isError=true;
           document.getElementById("errbank_account").innerText="please enter bank account!";
     }
     else {
          document.getElementById("errbank_account").innerText="";
     }
     if(ifsccode.length<1){
         isError=true;
         document.getElementById("errifsccode").innerText="please enter the ifsccode";
         
     }
     else {
         document.getElementById("errifsccode").innerText="";
     }
     
     if(isError==true){
        return false;
     }
     else {
        return true;
     }
     
     
}


function addUpdateBank() {

    var t=isBankValidate();
    
    if(t==true){
 
     var id=document.getElementById("id").value;
     
     if(id>0) {
     
          document.bankform.action="updateBank";
          document.bankform.submit();
     }
     else {
       
          document.bankform.action="addBank";
          document.bankform.submit();
     
     }
    }  
}

function editBank(id) {

    var url="editBank?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editBankRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
}

function editBankRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("bank").value=data[1];
			document.getElementById("bankaddress").value=data[2];
			document.getElementById("bank_account").value=data[3];
			document.getElementById("ifsccode").value=data[4];
			document.getElementById("name").value=data[5];
 			
 			$('#addbank').modal( "show" );
 			
 		}
    }
}


function addBankPopup() {

            document.getElementById("id").value="";            			
			document.getElementById("bank").value="";
			document.getElementById("bankaddress").value="";
			document.getElementById("bank_account").value="";
			document.getElementById("ifsccode").value="";
 			
 			$('#addbank').modal( "show" );     
}



function isLoanValidate() {

      var name=document.getElementById("name").value;
      var branch=document.getElementById("branch").value;
      var amount=document.getElementById("amount").value;
      var date_format=document.getElementById("date_format").value;
      var installments=document.getElementById("installments").value;
      
      var isError=false;
      
      if(name.length<1){
      
             isError=true;
             document.getElementById("errname").innerText="please select employee name!";
             
      } else {
           document.getElementById("errname").innerText="";
         
      }
      if(branch.length<1){
           isError=true;
           document.getElementById("errbranch").innerText="please select branch!";
      } else {
      
         document.getElementById("errbranch").innerText="";
      }
      if(amount.length<1){
        
          isError=true;
          document.getElementById("erramount").innerText="please enter amount!";
      } 
      else {
          document.getElementById("erramount").innerText="";
      }
      if(date_format.length<1){
      
           isError=true;
           document.getElementById("errdate_format").innerText="please select date!";
      } else {
       
          document.getElementById("errdate_format").innerText="";
          
      }
      if(installments.length<1){
        
           isError=true;
           document.getElementById("errinstallments").innerText="please enter number of installments!";
      
      } else {
      
           document.getElementById("errinstallments").innerText="";
      }
      
      if(isError==true){
      
          return false;
      } else {
         return true;
     }
      
}



function addUpdateLoan() {

     var t=isLoanValidate();
     if(t==true){

      var id=document.getElementById("id").value; 
  
      if(id>0) {
      
          document.loanform.action="updateloanPayrollMaster";
          document.loanform.submit();
      }
      else {
        
          document.loanform.action="addloanPayrollMaster";
          document.loanform.submit(); 
      }
     }
}

function editLoan(id) {

    var url="editloanPayrollMaster?selectedid="+id+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editLoanRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
}

function editLoanRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split(" ");
            document.getElementById("id").value=data[0];            			
			document.getElementById("name").value=data[1];
			document.getElementById("branch").value=data[2];
			document.getElementById("amount").value=data[3];
			document.getElementById("date_format").value=data[4];
            document.getElementById("installments").value=data[5];
            if(data[4]=="Yes") {
            
               document.getElementById("yesded").checked=true;
            }
            else {
            
               document.getElementById("noded").checked=true;  
            }
 			
 			
 			$('#addadvance').modal( "show" );
 			
 		}
    }
}

function addLoanPopup() {

            document.getElementById("id").value="";            			
			document.getElementById("name").value="";
			document.getElementById("branch").value="";
			document.getElementById("amount").value="";
			document.getElementById("date_format").value="";
            document.getElementById("installments").value="";
            $('#addadvance').modal( "show" );
            
}


function addUpdateLeave(){

    var id=document.getElementById("id").value;

    if(id>0) {
        document.leaveform.action="updateleavePayrollMaster";
        document.leaveform.submit();
    }
    else {
        document.leaveform.action="addleavePayrollMaster";
        document.leaveform.submit();
    }
}

function addUpdateEmployee() {

    var empid=document.getElementById("emp_id").value;
    
    if(empid>0) {
       
         document.employeeform.action="updatePayrollEmployee";
         document.employeeform.submit();
       
    }else {
       
         document.employeeform.action="addPayrollEmployee";
         document.employeeform.submit();
    } 
}


function addEmployeePopup() {

     document.getElementById("name").value="";
     document.getElementById("designation").value="";
     document.getElementById("date_join").value="";
     document.getElementById("qualification").value="";
     document.getElementById("dob").value=""; 
     document.getElementById("currentaddress").value="";
     document.getElementById("permanentaddress").value="";
     document.getElementById("contact").value="";
     document.getElementById("panno").value="";
     document.getElementById("empcode").value="";
     
  
    $('#addemployee').modal( "show" );
}

function editEmployee(id) {

    var url="editPayrollEmployee?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editEmployeeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
}

function editEmployeeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
 		}
    }
}


function editleave(id) {

    var url="editleavePayrollMaster?selectedid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editleaveRequest;
    req.open("GET", url, true); 
              
    req.send(null);


}


function editleaveRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("short_name").value=data[0];            			
			document.getElementById("leave_type").value=data[1];
			document.getElementById("no_days").value=data[2];
			document.getElementById("encashable").value=data[3];
			document.getElementById("carryover").value=data[4];
            document.getElementById("name").value=data[5];
            document.getElementById("branch").value=data[6];             
 			document.getElementById("id").value=data[7];
 			
 			$('#addleave').modal( "show" );
 			
 		}
    }
}
function checkForFixed(){
	var x= document.getElementById("fixedemp").checked;
	if(x==true){
		setSalaryData();
	}
	else{
		
	}
}

function setSalaryData() {
	var fixedsalary=Number(document.getElementById("fixedsalary").value);
   var basic=Number(document.getElementById("basic").value);
   var hra=Number(document.getElementById("hra").value);
   var medical_allowance=Number(document.getElementById("medical_allowance").value);
   var allowances=Number(document.getElementById("allowances").value);
   var da_on_ta=Number(document.getElementById("da_on_ta").value);
   var special_pay=Number(document.getElementById("special_pay").value);
   var personal_pay=Number(document.getElementById("personal_pay").value);
   var transport_allowance=Number(document.getElementById("transport_allowance").value);
   var da=Number(document.getElementById("da").value);
   var npa=Number(document.getElementById("npa").value);
   var emp_pf=Number(document.getElementById("emp_pf").value);
   var emp_esi=Number(document.getElementById("emp_esi").value);
   var comp_pf=Number(document.getElementById("comp_pf").value);
   var comp_esi=Number(document.getElementById("comp_esi").value);
   var otherincome=Number(document.getElementById("otherincome").value);
   var conveyance=Number(document.getElementById("conveyance").value);
   var washing=Number(document.getElementById("washing").value);
   var perdevallow=Number(document.getElementById("perdevallow").value);
   var vehiclepass=Number(document.getElementById("vehiclepass").value);
   var perfixedsalary =Number(document.getElementById("perfixedsalary").value);
   var perbasic = Number(document.getElementById("perbasic").value);
 if(fixedsalary=='0'){
	 fixedsalary=basic*100/45;
 }else {
	 if(perfixedsalary!='0'){
		 basic=fixedsalary*perfixedsalary/100;
	 }else{
		 basic=fixedsalary*45/100;
	 }
	 
 }
 if(fixedsalary<15000){
	 if(perfixedsalary!='0'){
		 basic=fixedsalary*perfixedsalary/100;
	 }else{
		 basic=fixedsalary*45/100;
	 } 
 }else{
	 if(perfixedsalary!='0'){
		 basic=fixedsalary*perfixedsalary/100;
	 }else{
		 basic=fixedsalary*40/100;
	 }
 }
 
 
/* if(fixedsalary<10000){
	 hra=basic*10/100;
 }else if(fixedsalary>=10000 && fixedsalary<15000){
	 hra= basic*20/100;
 }else if(fixedsalary>=15000 && fixedsalary<20000){
	 hra= basic*40/100;
 }else {
	 hra= basic*50/100;
 }*/
 if(perbasic=='0'){

 if(fixedsalary<10000){
	 hra=basic*10/100;
 }else if(fixedsalary>=10000 && fixedsalary<15000){
	 hra= basic*20/100;
 }else if(fixedsalary>=15000 && fixedsalary<20000){
	 hra= basic*40/100;
 }else {
	 hra= basic*50/100;
 }
 }else{
	 hra = basic*perbasic/100;
 }
 
/* if(basic<10000){
	 hra=basic*10/100;
 }else if(basic>=10000 && basic<15000){
	 hra= basic*20/100;
 }else if(basic>=15000 && basic<20000){
	 hra= basic*40/100;
 }else {
	 hra= basic*50/100;
 }*/
 
   /*basic=fixedsalary*45/100;*/
  
 /*hra=basic*40/100;*/
 
 
   /* medical_allowance=360;*/
    emp_pf=basic*12/100;  
   /* emp_esi=basic*2/100;*/
    perdevallow=fixedsalary-(basic+hra+conveyance+washing);
 
    document.getElementById("basic").value=basic;
    document.getElementById("hra").value=hra;
    document.getElementById("medical_allowance").value=medical_allowance;
    document.getElementById("emp_pf").value=emp_pf;
    document.getElementById("emp_esi").value=emp_esi;
    document.getElementById("perdevallow").value=perdevallow;
    
}

function modifyDeductions(){

    
   /*var basic=Number(document.getElementById("basic").value);*/ 
   var salaryTotal=Number(document.getElementById("salaryTotal").value);
   var emp_pf=Number(document.getElementById("emp_pf").value);
   var emp_esi=Number(document.getElementById("emp_esi").value);
   var other_deduction=Number(document.getElementById("other_deduction").value);
   var leave=Number(document.getElementById("leave").value);
   var prefessional_tax=Number(document.getElementById("prefessional_tax").value);
   var tds=Number(document.getElementById("tds").value);
   var loan=Number(document.getElementById("loan").value);
   
   var deductions=emp_pf+emp_esi+other_deduction+leave+prefessional_tax+tds+loan;   
   document.getElementById("deductions").value=deductions;    

   var allowances=Number(document.getElementById("allowances").value);
   
    var gs=salaryTotal+allowances;
    var netpay=gs-deductions;
   
    document.getElementById("gross_pay").innerText=gs;  
    document.getElementById("netpay").innerText=netpay;
    
    var emp_id=document.getElementById("emp_id").value;
    var year = document.getElementById("selectyear").value;
    var month = document.getElementById("selectedmonth").value;
    
    var url="editdeductionPayrollincrement?emp_id="+emp_id+"&salaryTotal="+salaryTotal+"&emp_pf="+emp_pf+"&emp_esi="+emp_esi+"&other_deduction="+other_deduction+"&leave="+leave+"&prefessional_tax="+prefessional_tax+"&tds="+tds+"&deductions="+deductions+"&allowances="+allowances+"&gross_pay="+gs+"&netpay="+netpay+"&month="+month+"&year="+year+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = modifyDeductionsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
      
       
}

function modifyDeductionsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
 			
 			$('#Deduction').modal( "hide" );
 		}
    }
}



function modifyAllowances() {
   
  /* var basic=Number(document.getElementById("basic").value);*/ 
  var salaryTotal=Number(document.getElementById("salaryTotal").value);
   var medical_allowance=Number(document.getElementById("medical_allowance").value);
   var da_on_ta=Number(document.getElementById("da_on_ta").value);
   var special_pay=Number(document.getElementById("special_pay").value);
   var personal_pay=Number(document.getElementById("personal_pay").value);
   var transport_allowance=Number(document.getElementById("transport_allowance").value);
   var hra=Number(document.getElementById("hra").value);
   var da=Number(document.getElementById("da").value);
   var npa=Number(document.getElementById("npa").value);
   var conveyance = Number(document.getElementById("conveyance").value);
   var washing = Number(document.getElementById("washing").value);
   var perdevallow = Number(document.getElementById("perdevallow").value);
   var vehiclepass = Number(document.getElementById("vehiclepass").value);
   var year = document.getElementById("selectyear").value;
   var month = document.getElementById("selectedmonth").value;
   /*var allowances=medical_allowance+da_on_ta+special_pay+personal_pay+transport_allowance+hra+da+npa+conveyance+perdevallow+vehiclepass+washing;*/
   var allowances=medical_allowance+da_on_ta+special_pay+personal_pay+transport_allowance+hra+da+npa+conveyance+perdevallow+washing+vehiclepass;
   
   var deductions=Number(document.getElementById("deductions").value);
   
  /* var gs=basic+allowances;*/
  var gs=salaryTotal+allowances;
    var netpay=gs-deductions;
    
   document.getElementById("allowances").value=allowances; 
   document.getElementById("gross_pay").innerText=gs;  
   document.getElementById("netpay").innerText=netpay;
   var emp_id=document.getElementById("emp_id").value;
   
   var url="editallowancesPayrollincrement?emp_id="+emp_id+"&salaryTotal="+salaryTotal+"&medical_allowance="+medical_allowance+"&da_on_ta="+da_on_ta+"&special_pay="+special_pay+"&personal_pay="+personal_pay+"&transport_allowance="+transport_allowance+"&hra="+hra+"&da="+da+"&npa="+npa+"&conveyance="+conveyance+"&washing="+washing+"&perdevallow="+perdevallow+"&allowances="+allowances+"&deductions="+deductions+"&gross_pay="+gs+"&netpay="+netpay+"&year="+year+"&month="+month+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = modifyAllowancesRequest;
    req.open("GET", url, true); 
              
    req.send(null);
 
   
   
}

function modifyAllowancesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
 			
 			$('#Allowances').modal( "hide" );
 		}
    }
}


function sendToHolidays(){

   var url="";
   var date=document.getElementById("dateholiday").value;
   var event=document.getElementById("event").value;
   var selectedid=Number(document.getElementById("selectedid").value);
   
   if(selectedid>0){
    
        url="updateholidayPayrollMaster?selectedid="+selectedid+"&date="+date+"&event="+event+"";
   } 
   else {
        url="addholidayPayrollMaster?date="+date+"&event="+event+"";
   }
   

   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = sendToHolidaysRequest;
    req.open("GET", url, true); 
              
    req.send(null);
      
      
      
             

}

function sendToHolidaysRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
 			
 			$('#addholiday').modal( "hide" );
 		}
    }
}


          function setDateSeven(fromdate) {
          
               var url="caldateAttendance?fromdate="+fromdate+"";
          
               if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
				    req = new ActiveXObject("Microsoft.XMLHTTP");
			   }   

    		    req.onreadystatechange = setDateSevenRequest;
    			req.open("GET", url, true); 
              
    			req.send(null);
          }
 
     function setDateSevenRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
 			
 				  var str=req.responseText;
 				  
 				  document.getElementById("todate").value=str;
 			  }
    		}
	  }


      function sortLeavesByBranch(id) {
      
            document.getElementById("keybranch").value=id;
            document.branchform.submit();
      
      }
      
     


      /*/// Adarsh
      */function saveLeav() {
      	var total=0;
      	 $('.dclass').each(function() {
                var i=this.value;
               // total=total+i;   
                total=total+","+this.value;
           });
      	 if(total==0){
      		 jAlert('error', "Please select at least one leave type!", 'Error Dialog');	
      			setTimeout(function() {
      				$("#popup_container").remove();
      				removeAlertCss();
      			}, alertmsgduration); 
      	 }else{
      		 document.getElementById("allleaveid").value= total;
      	     document.getElementById("leaveform").submit();
      	 }
      	 
      }
      function addnewLeave(){
      	  
          
      	
      	 var leave_type=document.getElementById("leave_type").value;
      	 var leavefromdate=document.getElementById("leavefromdate").value;
      	 var leavetodate=document.getElementById("leavetodate").value;
      	var leave_reason=document.getElementById("leave_reason").value;
      	var days=document.getElementById("days").value;
      	 

      	     var url="addnewleavePayrollDashBoard?leave_type="+leave_type+"&leavefromdate="+leavefromdate+"&leavetodate="+leavetodate+"&leave_reason="+leave_reason+"&days="+days+"";
      	   
      	     if (window.XMLHttpRequest) {
      			req = new XMLHttpRequest();
      		 }
      		 else if (window.ActiveXObject) {
      			isIE = true;
      			req = new ActiveXObject("Microsoft.XMLHTTP");
      		 }   
      	               
      	     req.onreadystatechange = addnewLeaveRequest;
      	     req.open("GET", url, true); 
      	              
      	     req.send(null);   
      	 }
      	 

      function addnewLeaveRequest(){
      	if (req.readyState == 4) {
      			if (req.status == 200) {
      			  document.getElementById("leavebody").innerHTML=req.responseText;
      				
      					
      				}
      	         }
      		}	 



      function proveleave(id,val){
    	  var url = "showleaverequestPayrollDashBoard?id="+id+"&val="+val+"";

    	  if (window.XMLHttpRequest) {
    	    req = new XMLHttpRequest();
    	   }
    	   else if (window.ActiveXObject) {
    	    isIE = true;
    	    req = new ActiveXObject("Microsoft.XMLHTTP");
    	   }   
    	                 
    	      req.onreadystatechange = proveleaveRequest;
    	      req.open("GET", url, true); 
    	                
    	      req.send(null);

    	  }
    	 function proveleaveRequest(){
    	  if (req.readyState == 4) {
    	    if (req.status == 200) {
    	    var str = req.responseText;
    	  var data = str.split("~");
    	   /*  document.getElementById("leaveno").innerHTML = data[0];*/
    	  if(document.getElementById("requestdate")){
    	     document.getElementById("requestdate").innerHTML = data[0];
    	  }
    	  document.getElementById("requestname").innerHTML = data[2];
    	     document.getElementById("userid").innerHTML = data[1];
    	     document.getElementById("requserid").innerHTML = data[1];
    	     document.getElementById("tbodyleaveid").innerHTML = data[3];
    	     
    	     document.getElementById("empleave_id").value = data[4];
    	     document.getElementById("adarsh").innerHTML = data[5];
    	     document.getElementById("notes").value=data[6];
//    	    document.getElementById("hospitaltitlediv3").innerHTML = data[6];
    	/*     document.getElementById("username").innerHTML = data[7];
    	     document.getElementById("userdatetime").innerHTML = data[8];*/
    	     /*document.getElementById("parentid23").value = data[10];*/
    	  
    	    /*document.getElementById("notes").value=data[10];*/
    	    
    	     /*document.getElementById("receivedbyid").innerHTML=data[10];*/
    	    /* document.getElementById("noteTextBox").innerHTML=data[9];*/
    	    
    	     document.getElementById("days").innerHTML = data[10];
    	     /*document.getElementById("requestaddress").innerHTML = data[13];*/
    	     $('#proveleave').modal( "show" ); 
    	           }
    	   }
    	  }
    	 
//    	 function getdiffirencedays(){
//    		 var leavefromdate=document.getElementById("leavefromdate").value;
//          	 var leavetodate=document.getElementById("leavetodate").value;
//    		 
//          	 
//    		 if(leavefromdate!='' && leavetodate!=''){
//    			 var date1 = leavefromdate.split('-');
//    	          	var date2 = leavetodate.split('-');
//    	          	date1 = new Date(date1[2], date1[1], date1[0]);
//    	          	date2 = new Date(date2[2], date2[1], date2[0]);
//    	          	var days = days_between(date1,date2);
//    	          	document.getElementById("days").value=days;
//    		 }
//    		 
//          
//          	
//    	 }
    	 
    	 function days_between(date1, date2) {

    		    // The number of milliseconds in one day
    		    var ONE_DAY = 1000 * 60 * 60 * 24

    		    // Convert both dates to milliseconds
    		    var date1_ms = date1.getTime()
    		    var date2_ms = date2.getTime()

    		    // Calculate the difference in milliseconds
    		    var difference_ms = Math.abs(date1_ms - date2_ms)

    		    // Convert back to days and return
    		    return Math.round(difference_ms/ONE_DAY)

    		}
    	 
    	/* function DifferenceInDays(leavefromdate, leavetodate) {
    		 var leavefromdate=document.getElementById("leavefromdate").value;
          	 var leavetodate=document.getElementById("leavetodate").value;
    		 
    		    return Math.round((leavefromdate-leavetodate)/(1000*60*60*24));
    		}*/
    	 
    	 
    	 
    	 
    	 function updateapproveleave() {
    		 var notes=document.getElementById("notes").value;
    		 if(notes==""){
    			 jAlert('error', "Please enter note!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
    		 }else{
    	     document.getElementById("leaverequestform").action="updateapproveleavePayrollDashBoard";
    	     document.getElementById("leaverequestform").submit();
    		 }
    	}
    	 function rejectleave(id){
    			var notes= document.getElementById("notes").value;
    			if(notes==''){
    					jAlert('error', "Please enter note!", 'Error Dialog');	
    							setTimeout(function() {
    								$("#popup_container").remove();
    								removeAlertCss();
    							}, alertmsgduration); 	
    			}else{
    				var url = "rejectleavePayrollDashBoard?id="+id+"&notes="+notes+"&reject=1";
    				if (window.XMLHttpRequest) {
    					req = new XMLHttpRequest();
    				}
    				else if (window.ActiveXObject) {
    					isIE = true;
    					req = new ActiveXObject("Microsoft.XMLHTTP");
    				}   
    			               
    			    req.onreadystatechange = rejectleaveRequest;
    			    req.open("GET", url, true); 
    			              
    			    req.send(null);
    			}
    		}
    		function rejectleaveRequest(){
    			if (req.readyState == 4) {
    					if (req.status == 200) {
    						window.location.reload();
    					 }
    				}
    			}
    		
    		function changedays (index){
    			
    			  var test = new Date();
    	    	  var year= test.getFullYear();
    	    	  var month=Number(index);
    			  var daysInMonth= new Date(year, month+1, 0).getDate();
    			  document.getElementById("totaldays").innerHTML=daysInMonth;
    			var id = document.getElementById("emp_id").value;
    			var url = "changeddaysPayrollincrement?id="+id+"&month="+index+"";
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = changedaysRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
    		}
    		
    		
    		function changedaysRequest(){
    			if (req.readyState == 4) {
    					if (req.status == 200) {
    						var str=req.responseText;
    						var data=str.split("~");
    						document.getElementById("workeddays").innerHTML=data[0];
    						document.getElementById("salaryTotal").value=data[1];
    						
    					 }
    				}
    			}
    		
    	/*	function setPerFixedsalary(perfixedsalary){
    			var fixedsalary = Number(document.getElementById("fixedsalary").value);
    			
    		}*/
    		
    		 function updateHrapproveleave() {
       		  
        	     document.getElementById("leaverequestHrform").action="updateHrapproveleavePayrollDashBoard";
        	     document.getElementById("leaverequestHrform").submit();
        	}
    		
    		
    		function proveHrleave(id,val){
    	    	  var url = "showHrleaverequestPayrollDashBoard?id="+id+"&val="+val+"";

    	    	  if (window.XMLHttpRequest) {
    	    	    req = new XMLHttpRequest();
    	    	   }
    	    	   else if (window.ActiveXObject) {
    	    	    isIE = true;
    	    	    req = new ActiveXObject("Microsoft.XMLHTTP");
    	    	   }   
    	    	                 
    	    	      req.onreadystatechange = proveHrleaveRequest;
    	    	      req.open("GET", url, true); 
    	    	                
    	    	      req.send(null);

    	    	  }
    	    	 function proveHrleaveRequest(){
    	    	  if (req.readyState == 4) {
    	    	    if (req.status == 200) {
    	    	    var str = req.responseText;
    	    	  var data = str.split("~");
    	    	     /*document.getElementById("leaveno").innerHTML = data[0];*/
    	    	     document.getElementById("hrdate").innerHTML = data[0];
    	    	     document.getElementById("hruserid").innerHTML = data[1];
    	    	     document.getElementById("tbodyHrleaveid").innerHTML = data[2];
    	    	    
    	    	    /* document.getElementById("empleave_id").value = data[3];*/
    	    	     document.getElementById("empleaveid").value = data[3];
    	    	     document.getElementById("adarsh1").innerHTML = data[4];
    	    	     document.getElementById("notes").value=data[5];
//    	    	    document.getElementById("hospitaltitlediv3").innerHTML = data[6];
    	    	     /*document.getElementById("username").innerHTML = data[7];
    	    	     document.getElementById("userdatetime").innerHTML = data[8];*/
//    	    	     document.getElementById("parentid23").value = data[10];
    	    	  
    	    	   /* document.getElementById("notes").value=data[10];
    	    	    
    	    	     document.getElementById("receivedbyid").innerHTML=data[10];
    	    	     document.getElementById("noteTextBox").innerHTML=data[9];*/
    	    	     document.getElementById("hrrequestname").innerHTML = data[6];
    	    	     document.getElementById("hrrequestcontact").innerHTML= data[7];
    	    	     document.getElementById("hrrequestdepartment").innerHTML = data[8];
    	    	     document.getElementById("days").innerHTML = data[9];
    	    	  /*   document.getElementById("requestaddress").innerHTML = data[13];*/
    	    	     $('#proveHrleave').modal( "show" ); 
    	    	           }
    	    	   }
    	    	 } 
    	    	 
    	    	 function searchempname(name){
    	    			var url = "searchempnamePayrollEmployee?searchtext="+name+"";
    	    			if (window.XMLHttpRequest) {
    	    					req = new XMLHttpRequest();
    	    				}
    	    				else if (window.ActiveXObject) {
    	    					isIE = true;
    	    					req = new ActiveXObject("Microsoft.XMLHTTP");
    	    				}   
    	    			               
    	    			    req.onreadystatechange = searchempnameRequest;
    	    			    req.open("GET", url, true); 
    	    			              
    	    			    req.send(null);
    	    		}
    	    		function searchempnameRequest(){
    	    			if (req.readyState == 4) {
    	    				if (req.status == 200) {
    	    					document.getElementById("tblsr").innerHTML=req.responseText;	
    	    		       }
    	    				}	 
    	    			

    	    		}
    	    	 
    	    		function getallownces(empid,month,year){
    	    			var url = "getallowncesPayrollMaster?empid="+empid+"&month="+month+"&year="+year+"";
    	    			if (window.XMLHttpRequest) {
    	    					req = new XMLHttpRequest();
    	    				}
    	    				else if (window.ActiveXObject) {
    	    					isIE = true;
    	    					req = new ActiveXObject("Microsoft.XMLHTTP");
    	    				}   
    	    			               
    	    			    req.onreadystatechange = getallowncesRequest;
    	    			    req.open("GET", url, true); 
    	    			              
    	    			    req.send(null);
    	    		}
    	    		function getallowncesRequest(){
    	    			if (req.readyState == 4) {
    	    				if (req.status == 200) {
    	    					 var str = req.responseText;
    	    	    	    	  var data = str.split("~");
    	    	    	    	  document.getElementById("basicsal").value = data[0];
    	    	    	    	  document.getElementById("medical_allownces").value = data[1];
    	    	    	    	  document.getElementById("hra").value = data[2];
    	    	    	    	  document.getElementById("da").value = data[3];
    	    	    	    	  document.getElementById("conveyance").value = data[4];
    	    	    	    	  document.getElementById("perdevallow").value = data[5];
    	    	    	    	  document.getElementById("empid").value = data[6];
    	    	    	    	  document.getElementById("emp_pf").value = data[7];
    	    	    	    	  document.getElementById("emp_esi").value = data[8];
    	    	    	    	  document.getElementById("leaves").value = data[9];
    	    	    	    	  document.getElementById("proftax").value = data[10];
    	    	    	    	  document.getElementById("tds").value = data[11];
    	    	    	    	  document.getElementById("netsal").value = data[12];
    	    	    	    	  document.getElementById("empname").value = data[13];
    	    	    	    	  document.getElementById("ot").value = data[14];
    	    	    	    	  document.getElementById("advance").value = data[15];
    	    	    	    	  document.getElementById("loan").value = data[16];
    	    	    	    	  
    	    	    	    	  document.getElementById("originalbasicsal").value = data[0];
    	    	    	    	  document.getElementById("originalmedical_allownces").value = data[1];
    	    	    	    	  document.getElementById("originalhra").value = data[2];
    	    	    	    	  document.getElementById("originalda").value = data[3];
    	    	    	    	  document.getElementById("originalconveyance").value = data[4];
    	    	    	    	  document.getElementById("originalperdevallow").value = data[5];
    	    	    	    	  document.getElementById("originalemp_pf").value = data[7];
    	    	    	    	  document.getElementById("originalemp_esi").value = data[8];
    	    	    	    	  document.getElementById("originalleaves").value = data[9];
    	    	    	    	  document.getElementById("originalproftax").value = data[10];
    	    	    	    	  document.getElementById("originaltds").value = data[11];
    	    	    	    	  document.getElementById("originalot").value = data[14];
    	    	    	    	  document.getElementById("originaladvance").value = data[15];
    	    	    	    	  document.getElementById("originalloan").value = data[16];
    	    	    	    	  $('#edit_salary').modal( "show" ); 
    	    		       }
    	    				}	 
    	    			

    	    		}
    	    		
    	    		
    	    		function updateallownces(colname,value,id){
    	    			 var empid=document.getElementById("empid").value;
    	    			 var month=document.getElementById("month").value;
    	    			 var year=document.getElementById("year").value;
    	    			 var val=document.getElementById(id).value;
    	    			 var originalval=document.getElementById("original"+id).value;
    	    			 var regx=/^\d*\.?\d*$/;
    	    				if( !regx.test(val) || val<0){
    	    					jAlert('error', "Please Enter Non Nagative and Number Value!", 'Error Dialog');
    	    					setTimeout(function() {
    	    						$("#popup_container").remove();
    	    						removeAlertCss();
    	    					}, alertmsgduration);
    	    					document.getElementById(id).value=originalval;
    	    				}else{
    	    					
    	    			
    	    			var url = "updateallowncesPayrollMaster?empid="+empid+"&month="+month+"&year="+year+"&colname="+colname+"&value="+value+"";
    	    			if (window.XMLHttpRequest) {
    	    					req = new XMLHttpRequest();
    	    				}
    	    				else if (window.ActiveXObject) {
    	    					isIE = true;
    	    					req = new ActiveXObject("Microsoft.XMLHTTP");
    	    				}   
    	    			               
    	    			    req.onreadystatechange = updateallowncesRequest;
    	    			    req.open("GET", url, true); 
    	    			              
    	    			    req.send(null);
    	    		}
    	    		}
    	    		function updateallowncesRequest(){
    	    			if (req.readyState == 4) {
    	    				if (req.status == 200) {
    	    					setnetearn();
    	    		       }
    	    				}	 
    	    			

    	    		}
    	    		function getdeduction(empid,month,year){
    	    			var url = "getdeductionPayrollMaster?empid="+empid+"&month="+month+"&year="+year+"";
    	    			if (window.XMLHttpRequest) {
    	    					req = new XMLHttpRequest();
    	    				}
    	    				else if (window.ActiveXObject) {
    	    					isIE = true;
    	    					req = new ActiveXObject("Microsoft.XMLHTTP");
    	    				}   
    	    			               
    	    			    req.onreadystatechange = getdeductionRequest;
    	    			    req.open("GET", url, true); 
    	    			              
    	    			    req.send(null);
    	    		}
    	    		function getdeductionRequest(){
    	    			if (req.readyState == 4) {
    	    				if (req.status == 200) {
    	    					 var str = req.responseText;
    	    	    	    	  var data = str.split("~");
    	    	    	    	  document.getElementById("emp_pf").value = data[0];
    	    	    	    	  document.getElementById("emp_esi").value = data[1];
    	    	    	    	  document.getElementById("other_deduction").value = data[2];
    	    	    	    	  document.getElementById("leave").value = data[3];
    	    	    	    	  document.getElementById("prefessional_tax").value = data[4];
    	    	    	    	  document.getElementById("tds").value = data[5];
    	    	    	    	  document.getElementById("empid").value = data[6];
    	    	    	    	  $('#Deduction').modal( "show" ); 
    	    		       }
    	    				}	 
    	    			

    	    		}
    	    		
    	    		
    	    		
    	    		
    	    		 function updatededuction(colname,value,id){
   	    			 var empid=document.getElementById("empid").value;
	    			 var month=document.getElementById("month").value;
	    			 var year=document.getElementById("year").value;
	    			 var val=document.getElementById(id).value;
	    			 var originalval=document.getElementById("original"+id).value;
	    			 var regx=/^\d*\.?\d*$/;
	    				if( !regx.test(val) || val<0){
	    					jAlert('error', "Please Enter Non Nagative and Number Value!", 'Error Dialog');
	    					setTimeout(function() {
	    						$("#popup_container").remove();
	    						removeAlertCss();
	    					}, alertmsgduration);
	    					document.getElementById(id).value=originalval;
	    				}else{
	    			var url = "updatedeductionPayrollMaster?empid="+empid+"&month="+month+"&year="+year+"&colname="+colname+"&value="+value+"";
	    			if (window.XMLHttpRequest) {
	    					req = new XMLHttpRequest();
	    				}
	    				else if (window.ActiveXObject) {
	    					isIE = true;
	    					req = new ActiveXObject("Microsoft.XMLHTTP");
	    				}   
	    			               
	    			    req.onreadystatechange = updatedeductionRequest;
	    			    req.open("GET", url, true); 
	    			              
	    			    req.send(null);
	    				}
	    		}
	    		function updatedeductionRequest(){
	    			if (req.readyState == 4) {
	    				if (req.status == 200) {
	    					setnetearn();
	    		       }
	    				}	 
	    			

	    		}
	    		 function getCitiesajax(val) {
	    		     var url="getcitiesajaxInventory?state="+val+"";
	    		  
	    		     if (window.XMLHttpRequest) {
	    				req = new XMLHttpRequest();
	    			 }
	    			 else if (window.ActiveXObject) {
	    				isIE = true;
	    				req = new ActiveXObject("Microsoft.XMLHTTP");
	    			 }   
	    		               
	    		     req.onreadystatechange = getCitiesajaxRequest;
	    		     req.open("GET", url, true); 
	    		              
	    		     req.send(null);   
	    		 
	    		 }
	    		 function getCitiesajaxRequest(){
	    			    if (req.readyState == 4) {
	    					if (req.status == 200) {
	    					   
	    					   document.getElementById("citydiv").innerHTML=req.responseText;
//	    					    $("#town").trigger("chosen:updated");
//	    						$(".chosen").chosen({allow_single_deselect: true});
	    			         }
	    				}	 	
	    			}

	    			function getStateAjaxnew(val) {
	    			     var url="getstateajaxnewInventory?city="+val+"";
	    			  
	    			     if (window.XMLHttpRequest) {
	    			  req = new XMLHttpRequest();
	    			  }
	    			  else if (window.ActiveXObject) {
	    			  isIE = true;
	    			  req = new ActiveXObject("Microsoft.XMLHTTP");
	    			  }   
	    			               
	    			     req.onreadystatechange = getStateAjaxnewRequest;
	    			     req.open("GET", url, true); 
	    			              
	    			     req.send(null);   
	    			 
	    			 }
	    			 
	    			 function getStateAjaxnewRequest(){
	    			    if (req.readyState == 4) {
	    			  if (req.status == 200) {
	    				  document.getElementById("state").className="";
	    				  document.getElementById("state").value=req.responseText;
	    					 $("#state").trigger("chosen:updated");
	    						$(".chosen-select").chosen({allow_single_deselect: true});
	    			   
	    			         }
	    			 }  
	    			}


	    			  function getStateAjax(val) {
	    			     var url="getstateajaxInventory?city="+val+"";
	    			  
	    			     if (window.XMLHttpRequest) {
	    					req = new XMLHttpRequest();
	    				 }
	    				 else if (window.ActiveXObject) {
	    					isIE = true;
	    					req = new ActiveXObject("Microsoft.XMLHTTP");
	    				 }   
	    			               
	    			     req.onreadystatechange = getStateAjaxRequest;
	    			     req.open("GET", url, true); 
	    			              
	    			     req.send(null);   
	    			 
	    			 }
	    			 
	    			 function getStateAjaxRequest(){
	    			    if (req.readyState == 4) {
	    					if (req.status == 200) {
	    					   document.getElementById("statediv").innerHTML=req.responseText;
	    			         }
	    				}	 
	    			}
function setnetearn(val) {
	var leaves=0;
	var ot=0;
	var netsal = Number(document.getElementById("netsal").value);
	var basic=Number(document.getElementById("basicsal").value);
	var da=Number(document.getElementById("da").value);
	var hra=Number(document.getElementById("hra").value);
	var conveyance=Number(document.getElementById("conveyance").value);
	var perdevallow=Number(document.getElementById("perdevallow").value);
	var medical_allownces=Number(document.getElementById("medical_allownces").value);
	var tds=Number(document.getElementById("tds").value);
	var esi=Number(document.getElementById("emp_esi").value);
	var pf=Number(document.getElementById("emp_pf").value);
	var proftax=Number(document.getElementById("proftax").value);
	var advance=0;
	var loan=0;
	var temp=0;
	if(document.getElementById("leaves")){
	 leaves=Number(document.getElementById("leaves").value);
	}
	if(document.getElementById("ot")){
	 ot=Number(document.getElementById("ot").value);
	}
	if(document.getElementById(val)){
		 temp=document.getElementById(val).value;
	}
	if(document.getElementById("advance")){
		 advance=Number(document.getElementById("advance").value);
		}
	if(document.getElementById("loan")){
		 loan=Number(document.getElementById("loan").value);
		}
	var regx=/^\d*\.?\d*$/;
	if(temp<0 || !regx.test(temp)){
		jAlert('error', "Please enter Non Negative and Number Value!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 	
		document.getElementById(val).value=0;
	}else{
	var dedsal=tds+esi+pf+proftax+leaves+loan;
	var sal = basic+da+hra+conveyance+perdevallow+medical_allownces+ot+advance;
	if(document.getElementById("gross")){
	document.getElementById("gross").value=sal.toFixed(2);
	}
	var totsal=sal-dedsal;
	document.getElementById("netsal").value=totsal.toFixed(2);
	}
}

function getholidays(val) {
	var url = "listholidaysPayrollMaster?id="+val+"";
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	               
	     req.onreadystatechange = getholidays1Request;
	     req.open("GET", url, true); 
	              
	     req.send(null);   
	 
}
function getholidays1Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str = req.responseText;
	    	  var data = str.split("~");
	    	  document.getElementById("selectedid").value = data[0];
	    	  document.getElementById("eventname").value = data[2];
	    	  document.getElementById("eventdate").value = data[1];
	    	  $('#edit_holiday').modal( "show" ); 
       }
		}	 
}
function deleteholiday(val){
	  $('#delete_holiday').modal( "show" );
	  document.getElementById("deletedid").value=val;
}


function deleteselectedhol(val){
	
	 var val=document.getElementById("deletedid").value;
	var url = "deleteholidaysPayrollMaster?id="+val+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getholidaysRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function getholidaysRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
       }
		}	 
}



function deletedeparment(){
	var val=document.getElementById("deptid").value;
var url = "deletedeparmentPayrollDepartment?id="+val+"";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletedeparmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function deletedeparmentRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		window.location.reload();
   }
	}	 
}



function deletedesigntn(){
	var val=document.getElementById("desiid").value;
var url = "deletedesigntnPayrollDepartment?id="+val+"";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletedesigntnRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function deletedesigntnRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		window.location.reload();
   }
	}	 
}



function deleteleave(){
	var val=document.getElementById("leaveid").value;
var url = "deleteleavePayrollDashBoard?id="+val+"";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletedesigntnRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function deletedesigntnRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		window.location.reload();
   }
	}	 
}


function deletequalification(){
	var val=document.getElementById("qualificid").value;
var url = "deletequalificationPayrollDepartment?id="+val+"";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletedeparmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function deletedeparmentRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		window.location.reload();
   }
	}	 
}

function aproveot(id,val,sts){
	  var url = "showotrequestPayrollDashBoard?id="+id+"&val="+val+"&sts="+sts+"";

	  if (window.XMLHttpRequest) {
	    req = new XMLHttpRequest();
	   }
	   else if (window.ActiveXObject) {
	    isIE = true;
	    req = new ActiveXObject("Microsoft.XMLHTTP");
	   }   
	                 
	      req.onreadystatechange = aproveotRequest;
	      req.open("GET", url, true); 
	                
	      req.send(null);

	  }
function aproveotRequest(){
	  if (req.readyState == 4) {
	    if (req.status == 200) {
	    var str = req.responseText;
	  var data = str.split("~");
	  if(document.getElementById("requestdate")){
	     document.getElementById("requestdate").innerHTML = data[0];
	  }
	  document.getElementById("requestname").innerHTML = data[2];
	     document.getElementById("userid").innerHTML = data[1];
	     document.getElementById("requserid").innerHTML = data[1];
	     document.getElementById("tbodyleaveid").innerHTML = data[3];
	     
	     document.getElementById("empot_id").value = data[4];
	     document.getElementById("cooldude").innerHTML = data[5];
	     document.getElementById("notes").value=data[6];
	     document.getElementById("sts").value=data[11];
	     $('#othodapprove').modal( "show" ); 
	           }
	   }
	  }
	 function updateapproveot() {
		 var notes=document.getElementById("notes").value;
		 if(notes==""){
			 jAlert('error', "Please enter note!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		 }else{
	     document.getElementById("otrequestform").submit();
		 }
}
	 
	 
	 function rejectot(id,sts){
			var notes= document.getElementById("notes").value;
			if(notes==''){
					jAlert('error', "Please enter note!", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 	
			}else{
				var url = "rejectotPayrollDashBoard?id="+id+"&notes="+notes+"&reject=3"+"&sts="+sts;
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = rejectotRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
			}
		}
	 function rejectotRequest(){
			if (req.readyState == 4) {
					if (req.status == 200) {
						window.location.reload();
					 }
				}
			}
	 
	 
	 
	 
	 function deleteot(){
		var val=document.getElementById("leaveid").value;
		var url = "deleteotPayrollDashBoard?id="+val+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = deleteotRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		function deleteotRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
		   }
			}	 
		}
		
		
		function paySal1(empid,status){
			document.getElementById("paysalslip").value=0;
						var payslip=document.getElementById("paysalslip").value;
						var month=document.getElementById("month").value
						var year=document.getElementById("year").value
						var url="finalizesalary1PayrollMaster?empid="+empid+"&status="+status+"&payslip="+payslip+"&month="+month+"&year="+year+"";
						if (window.XMLHttpRequest) {
										req = new XMLHttpRequest();
									}
									else if (window.ActiveXObject) {
										isIE = true;
										req = new ActiveXObject("Microsoft.XMLHTTP");
									}   
								               
								    req.onreadystatechange = paySal1Request;
								    req.open("GET", url, true); 
								              
								    req.send(null);
					 
					    
		}
		
		function paySal1Request(){
			   		if (req.readyState == 4) {
			   			if (req.status == 200) {
							window.location.reload();
			   		   }
			   			}	 
			   		}
			   		 
		
		function paySalslip1(empid,status) {
			document.getElementById("paysalslip").value=1;
			var payslip=document.getElementById("paysalslip").value;
			var month=document.getElementById("month").value
			var year=document.getElementById("year").value
			var url="finalizesalary1PayrollMaster?empid="+empid+"&status="+status+"&payslip="+payslip+"&month="+month+"&year="+year+"";
			if (window.XMLHttpRequest) {
							req = new XMLHttpRequest();
						}
						else if (window.ActiveXObject) {
							isIE = true;
							req = new ActiveXObject("Microsoft.XMLHTTP");
						}   
					               
					    req.onreadystatechange = paySalslip1Request;
					    req.open("GET", url, true); 
					              
					    req.send(null);
		 
		    
		   }
		   
		   function paySalslip1Request(){
		   		if (req.readyState == 4) {
		   			if (req.status == 200) {
						window.location.reload();
		   		   }
		   			}	 
		   		}
		   		 