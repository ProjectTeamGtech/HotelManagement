var validchk = 0;
var validchk1 = 0;
var gusertype = 0;
$(document).ready(function(){

	
	
	if(document.getElementById("whopay").value=='Third Party'){
		
		document.getElementById("policyNo").disabled = false;
		document.getElementById("policyExcess").disabled = false;
		
	}
	
	/*if(gusertype!=2){
		document.getElementById("whopay").disabled = true;
		document.getElementById("type").disabled = true;
		document.getElementById("typeName").disabled = true;
		document.getElementById("policyNo").disabled = true;
		document.getElementById("policyExcess").disabled = true;
	}*/
	
	
	$("#gptypeName").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	
	$("#sourceOfIntro").chosen({	   
		no_results_text: "Oops, nothing found! <a id='AddNew_sourceOfIntro' class='AddNewLink'>Add New</a>",
	  });
	$("#reference").chosen({	   
		no_results_text: "Oops, nothing found! <a id='AddNew_reference' class='AddNewLink'>Add New</a>",
	  });
	$("#occupation").chosen({	   
		no_results_text: "Oops, nothing found! <a id='AddNew_occupation' class='AddNewLink'>Add New</a>",
	  });
	$("#treatmentType").chosen({	   
		no_results_text: "Oops, nothing found! <a id='AddNew_treatmentType' class='AddNewLink'>Add New</a>",
	  });
	$("#chosen").chosen();
//$("#dashboardDiv").hide();
	//document.getElementById('closediv').style.display = '';
	var today = new Date();
	var date = today.getDate();
	var maonth = today.getMonth();
	var year = today.getFullYear(); 
	 $( "#dob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 maxDate: today,
		 changeMonth: true,
	     changeYear: true
		 
			 
	 });
	 $( "#dob1" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 maxDate: today,
		 changeMonth: true,
	     changeYear: true
		 
			 
	 });
	 
	 $( "#expiryDate" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 minDate: today,
		 changeMonth: true,
	     changeYear: true
	 });
	 
	 $( "#expiryDate1" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 minDate: today,
		 changeMonth: true,
	     changeYear: true
	 });
 $( "#referedDate" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 changeMonth: true,
	     changeYear: true
	 });
 
	
 
     
});	

function confirmedDelete(){

	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }

	}    	
	    
	function setTPName(id){
		var isforlmh=0;
		if(document.getElementById("isfromaddpatientlmh123")){
			isforlmh =1;
		}
		
		/*var url = "setTypeNameClntDropDownClient?id="+id+"&isforlmh="+isforlmh+" ";*/
		var url = "setTypeNameClntDropDownBookAppointmentAjax?id="+id+"&isforlmh="+isforlmh+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setTypeNameClientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    
	    if(document.getElementById("type").value == 2 && document.getElementById("whopay").selectedIndex == 1) 
		{			
				//document.getElementById("type").disabled = false;
				//document.getElementById("typeName").disabled = false;
				document.getElementById("policyNo").disabled = false;
				document.getElementById("expiryDate").disabled = false;
				document.getElementById("policyExcess").disabled = false;
		}
		else{
			
				//document.getElementById("type").disabled = true;
				//document.getElementById("typeName").disabled = true;
				document.getElementById("policyNo").disabled = true;
				document.getElementById("expiryDate").disabled = true;
				document.getElementById("policyExcess").disabled = true;
		}
	    var typenamebyuser=document.getElementById("type").options[document.getElementById('type').selectedIndex].text;
	    if(typenamebyuser=='CGHS')
	    	
		{	
	    	if(document.getElementById('newtpdiv')){
				
	    		document.getElementById('newtpdiv').className = "";
			
				}
	    	if(document.getElementById('addtpdiv')){
				
	    		document.getElementById('addtpdiv').className = "";
			
				}
	    	if(document.getElementById('ipdtpadd')){
				
	    		document.getElementById('ipdtpadd').className = "";
			
				}
	    		if(document.getElementById("islmh")){
	    			document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('unitstationdiv').className ="col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('relationvbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
		    		document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
		    		document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
		    		document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		}else{
	    			document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
		    		document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
		    		document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3";
		    		document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3";
		    		document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3";
		    		document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3";
		    		document.getElementById('collierydiv').className = "col-lg-3 col-md-3 hidden";
		    		document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3 hidden";
		    		document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 hidden";
	    		}
	    		
	    		
		}
	    else if(typenamebyuser=='WCL'){
			
				
			if(document.getElementById('newtpdiv')){
				
	    		document.getElementById('newtpdiv').className = "";
			
				}
			if(document.getElementById('addtpdiv')){
				
	    		document.getElementById('addtpdiv').className = "";
			
				}
			if(document.getElementById('ipdtpadd')){
	
				document.getElementById('ipdtpadd').className = "";

			}
			if(document.getElementById("islmh")){
				document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('unitstationdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('relationvbytpdiv').className ="col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
    		}else{
    			document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
        		document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
        		document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3";
        		document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3 hidden";
        		document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3 hidden" ;
        		document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3 ";
        		document.getElementById('collierydiv').className = "col-lg-3 col-md-3";
        		document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3";
        		document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 hidden";
    		}
			
	    }else if(document.getElementById("type").value == 2 && document.getElementById("whopay").selectedIndex == 1){
			
			if(document.getElementById('newtpdiv')){
				
	    		document.getElementById('newtpdiv').className = "";
			
				}
			if(document.getElementById('addtpdiv')){
				
	    		document.getElementById('addtpdiv').className = "";
			
				}
			if(document.getElementById('ipdtpadd')){
	
				document.getElementById('ipdtpadd').className = "";

			}
			if(document.getElementById("islmh")){
				document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
				document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
				document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
				document.getElementById('unitstationdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
				document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
				document.getElementById('relationvbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
				document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
				document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
				document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero";
    		}else{
    			document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
    			document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
    			document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3 hidden";
    			document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3 hidden";
    			document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3 hidden" ;
    			document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3 ";
    			document.getElementById('collierydiv').className = "col-lg-3 col-md-3 hidden";
    			document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3 hidden";
    			document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 ";
    		}
						
		}
		else{
			if(document.getElementById('newtpdiv')){
			
    		document.getElementById('newtpdiv').className = "hidden";
		
			}
			if(document.getElementById('addtpdiv')){
				
	    		document.getElementById('addtpdiv').className = "hidden";
			
				}
		if(document.getElementById('ipdtpadd')){
						
			    		document.getElementById('ipdtpadd').className = "hidden";
					
						}
					
		
		}
	
	}    
	function setTypeNameClientRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				//alert('hello');
	    		document.getElementById("typeName").innerHTML = req.responseText;
	    		$("#typeName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				
				
				if(document.getElementById("isfromaddpatientlmh123")){
					document.getElementById("registrationcharge").value="0";
					calculatepatientafterDiscount();
				}
				
	         }
		}
	 
	}


	function showHide() 
	{
		
	            if(document.getElementById("whopay").value == 'Third Party') 
	            {
	                document.getElementById("hidden_html").style.display = ""; // This line makes the DIV visible
	            } 
	            else {            
	                document.getElementById("hidden_html").style.display = "none"; // This line hides the DIV
	            }
	            
	            if(document.getElementById("whopay").selectedIndex == 0) 
	            {
	    			//document.getElementById("type").disabled = false;
	    			//document.getElementById("typeName").disabled = false;
	    			document.getElementById("policyNo").disabled = true;
	    			document.getElementById("expiryDate").disabled = true;
	    			document.getElementById("policyExcess").disabled = true;
	            }
	          
	 } 
	
	function enabledFiled(){
//		if(document.getElementById("whopay").selectedIndex == 1) 
//        {
//        	document.getElementById("type").disabled = false;
//        	$("#type").trigger("chosen:updated");
//        	$(".chosen").chosen({allow_single_deselect: true});
//			document.getElementById("typeName").disabled = false;
//			$("#typeName").trigger("chosen:updated");
//        	$(".chosen").chosen({allow_single_deselect: true});
//			document.getElementById("policyNo").disabled = false;
//			document.getElementById("expiryDate").disabled = false;
//			document.getElementById("policyExcess").disabled = false;
//			document.getElementById("tpmemb").disabled = false;
//        }
//        else{
//        	document.getElementById("type").disabled = true;
//        	$("#type").trigger("chosen:updated");
//        	$(".chosen").chosen({allow_single_deselect: true});
//			document.getElementById("typeName").disabled = true;
//			$("#typeName").trigger("chosen:updated");
//        	$(".chosen").chosen({allow_single_deselect: true});
//			document.getElementById("policyNo").disabled = true;
//			document.getElementById("expiryDate").disabled = true;
//			document.getElementById("policyExcess").disabled = true;
//			document.getElementById("tpmemb").disabled = true;
//			document.getElementById("newtpdiv").className="hidden";
//        }
		if( document.getElementById("whopay").selectedIndex == 1) 
		{	
			if(document.getElementById("hidethirdpartydetails")){
				document.getElementById("hidethirdpartydetails").className=""
			}
			document.getElementById("type").disabled = false;
        	$("#type").trigger("chosen:updated");
        	$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById("typeName").disabled = false;
			$("#typeName").trigger("chosen:updated");
        	$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById("policyNo").disabled = false;
			document.getElementById("expiryDate").disabled = false;
			document.getElementById("policyExcess").disabled = false;
			document.getElementById("tpmemb").disabled = false;
		}
		else{
			if(document.getElementById("hidethirdpartydetails")){
				document.getElementById("hidethirdpartydetails").className="hidden"
			}
			
			document.getElementById("type").disabled = true;
        	$("#type").trigger("chosen:updated");
        	$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById("typeName").disabled = true;
			$("#typeName").trigger("chosen:updated");
        	$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById("policyNo").disabled = true;
			document.getElementById("expiryDate").disabled = true;
			document.getElementById("policyExcess").disabled = true;
			document.getElementById("tpmemb").disabled = true;
		}
		var typenamebyuser=document.getElementById("type").options[document.getElementById('type').selectedIndex].text;
		if(typenamebyuser=='CGHS'){	
	    	if(document.getElementById('newtpdiv')){
				document.getElementById('newtpdiv').className = "";
			}
	    	if(document.getElementById('addtpdiv')){
				document.getElementById('addtpdiv').className = "";
			}
	    	if(document.getElementById('ipdtpadd')){
				document.getElementById('ipdtpadd').className = "";
			}
	    	if(document.getElementById("islmh")){
	    		document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('unitstationdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('relationvbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    	}else{
	    		document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
	    		document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('collierydiv').className = "col-lg-3 col-md-3 hidden";
	    		document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3 hidden";
	    		document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 hidden";
	    	}
    		
		}else if(typenamebyuser=='WCL'){
			if(document.getElementById('newtpdiv')){
				document.getElementById('newtpdiv').className = "";
			}
			if(document.getElementById('addtpdiv')){
				document.getElementById('addtpdiv').className = "";
			}
			if(document.getElementById('ipdtpadd')){
				document.getElementById('ipdtpadd').className = "";
			}
			if(document.getElementById("islmh")){
				document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('unitstationdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
	    		document.getElementById('relationvbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
	    		document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
			}else{
				document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
	    		document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3 hidden";
	    		document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3 hidden" ;
	    		document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3 ";
	    		document.getElementById('collierydiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3";
	    		document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 hidden";
			}
			
		}else if(document.getElementById("type").value == 2 && document.getElementById("whopay").selectedIndex == 1){
				if(document.getElementById('newtpdiv')){
					document.getElementById('newtpdiv').className = "";
				}
				if(document.getElementById('addtpdiv')){
					document.getElementById('addtpdiv').className = "";					
				}
				if(document.getElementById('ipdtpadd')){
					document.getElementById('ipdtpadd').className = "";
				}
				if(document.getElementById("islmh")){
					document.getElementById('compnamediv').className = "col-lg-12 col-md-12 ajpadzero";
					document.getElementById('neisnodiv').className = "col-lg-12 col-md-12 ajpadzero";
					document.getElementById('designationbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
					document.getElementById('unitstationdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
					document.getElementById('claimbytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
					document.getElementById('relationvbytpdiv').className = "col-lg-12 col-md-12 ajpadzero";
					document.getElementById('collierydiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
					document.getElementById('areabytpdiv').className = "col-lg-12 col-md-12 ajpadzero hidden";
					document.getElementById('policyholderdiv').className = "col-lg-12 col-md-12 ajpadzero";	
				}else{
					document.getElementById('compnamediv').className = "col-lg-3 col-md-3";
					document.getElementById('neisnodiv').className = "col-lg-3 col-md-3";
					document.getElementById('designationbytpdiv').className = "col-lg-3 col-md-3 hidden";
					document.getElementById('unitstationdiv').className = "col-lg-3 col-md-3 hidden";
					document.getElementById('claimbytpdiv').className = "col-lg-3 col-md-3 hidden" ;
					document.getElementById('relationvbytpdiv').className = "col-lg-3 col-md-3 ";
					document.getElementById('collierydiv').className = "col-lg-3 col-md-3 hidden";
					document.getElementById('areabytpdiv').className = "col-lg-3 col-md-3 hidden";
					document.getElementById('policyholderdiv').className = "col-lg-3 col-md-3 ";			
				}
				
			}else{
				if(document.getElementById('newtpdiv')){
					document.getElementById('newtpdiv').className = "hidden";
				}
				if(document.getElementById('addtpdiv')){
					document.getElementById('addtpdiv').className = "hidden";
				}
				if(document.getElementById('ipdtpadd')){
					document.getElementById('ipdtpadd').className = "hidden";
				}
					
			}
			
		if(document.getElementById("isfromaddpatientlmh123")){
			var whopayvalue = document.getElementById("whopay").value;
			var patientType=1;
			if(whopayvalue=='Self' || whopayvalue=='Client' || whopayvalue=='0'){
				patientType =0;
			}
			document.getElementById("typeName").disabled = false;
			$("#typeName").trigger("chosen:updated");
        	$(".chosen").chosen({allow_single_deselect: true});
			addPatientPayerType(patientType);
		}
		 
	}
	 function showOtherRefernce(){
				var e = document.getElementById('reference');
				var reference = e.options[e.selectedIndex].text;
				//alert(reference);
	 		 	if(reference == 'ADD NEW') 
	            {
	                document.getElementById("reference_other").style.display = ""; // This line makes the DIV visible
	            } 
	            else {            
	                document.getElementById("reference_other").style.display = "none"; // This line hides the DIV
	            }
	 }
	function showOtherOccupation(){
				var e = document.getElementById('occupation');
				var occupation = e.options[e.selectedIndex].text;
				
				
	 		 	if(occupation == 'ADD NEW') 
	            {
	                document.getElementById("occupation_other").style.display = ""; // This line makes the DIV visible
	            } 
	            else {            
	                document.getElementById("occupation_other").style.display = "none"; // This line hides the DIV
	            }
	}
	$(document).on("change",".ddlAddNew",function(){
		var Id=$(this).attr("id");
		if($("#"+Id+" option:selected").text()=='ADD NEW'){
			$("#"+Id+"_other").css("display","block");
		}else{
			$("#"+Id+"_other").css("display","none");
		}
	});
	$(document).on("click",".AddNewLink",function(){
		var Id=$(this).attr("id").split("_")[1];
		$("#"+Id+"_other").css("display","block");
		
		$("#"+Id).trigger('chosen:close');
		//var val = $("#ddlval").val();
		//alert(val);
		$("#txt_"+Id).val($("#chosenTypedText").val());
	});
	$(document).on("keyup","#chosenText",function(){
		$("#chosenTypedText").val($(this).val());
	});
	function setValueTOdd(val){
		alert(val);
	}
	
	function showOtherCondition(){
		var e = document.getElementById('treatmentType');
		var treatmentType = e.options[e.selectedIndex].text;
		
		
		 	if(treatmentType == 'ADD NEW') 
        {
            document.getElementById("condition_other").style.display = ""; // This line makes the DIV visible
        } 
        else {            
            document.getElementById("condition_other").style.display = "none"; // This line hides the DIV
        }
	}
	function addOtherOccupation(otherOccupation){
	//alert(otherOccupation);
	var url = "addOtherOccupationClient?otherOccupation="+otherOccupation+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addOtherOccupationRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}    
	function addOtherOccupationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		
	         
	         }
		}
	}

	function addOtherReference(otherReference){
	//alert(otherReference);
	var url = "addOtherReferenceClient?otherReference="+otherReference+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addOtherReferenceRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}    
	function addOtherReferenceRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		
	         
	         }
		}
	}
 
	function addOtherSourceOfIntro(otherSourceOfIntro){
		
		var url = "addOtherSourceOfIntroClient?otherSourceOfIntro="+otherSourceOfIntro+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addOtherSourceOfIntroRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	function addOtherSourceOfIntroRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					
		    		
		         
		         }
			}
		}
 
 function addOtherCondition(otherCondition){
	 var url = "addOtherConditionClient?otherCondition="+otherCondition+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addOtherConditionRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
 }
 function addOtherConditionRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					
		    		
		         
		         }
			}
		}
//Tab Validations handler

 $(document).on("click", ".tabAnchor", function () {
 	var currentElem=$(this);
 	var targetId = $(this).attr("data-target-div");
 	var validationResule=true;
 	$(".tab-pane").each(function (){
 		if($(this).hasClass('active')){
 			var validationFun = $(this).attr("data-validation-function");
 			
 			if(window[validationFun]()){				
 				$(this).removeClass("active in");
 				var activeTabId=$(this).attr("data-tab-id");
 				$(activeTabId).parent().removeClass("active");
 				
 				$(currentElem).parent().addClass("active");
 				$(targetId).addClass("active in");
 				
 				validationResule= false;
 			}
 			if(!validationResule){
 				return validationResule;
 			}
 		}
 	});
 	
 });

 //Open Next Tab
 $(document).on("click", ".next, .previous", function () { 	
 	var targetDivId = $(this).attr("data-target-div");
 	var targetTabId = $(this).attr("data-target-tab");
 	var validationResule=true;
 	$(".tab-pane").each(function (){
 		if($(this).hasClass('active')){
 			var validationFun = $(this).attr("data-validation-function");
 			
 			if(window[validationFun]()){				
 				$(this).removeClass("active in");
 				var activeTabId=$(this).attr("data-tab-id");
 				$(activeTabId).parent().removeClass("active");
 				
 				$(targetTabId).parent().addClass("active");
 				$(targetDivId).addClass("active in");
 				
 				validationResule= false;
 			}
 			if(!validationResule){
 				return validationResule;
 			}
 		}
 	}); 	
 });

 //Validation functions goes here

 function validateNA(){
 	validatePromoJSON = {
             "validatorData": [
                     { "Element": "#title", "FunctionName": "dropDown" },
                     { "Element": "#firstName", "FunctionName": "alphabetsOnlyWithSpace" },
                     { "Element": "#middleName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
                     { "Element": "#lastName", "FunctionName": "alphabetsOnlyWithSpace" },
                     { "Element": "#dob", "FunctionName": "notEmpty" },
                     { "Element": "#address", "FunctionName": "notEmpty" }, 
                     { "Element": "#town", "FunctionName": "notEmpty" },
                     { "Element": "#country", "FunctionName": "dropDown" }, 
                     { "Element": "#postCode", "FunctionName": "notEmpty" }
                    
             ]
         };
 	
 	return iterateThroughtElemsForValidations(validatePromoJSON);
 }
 
 function validateCT(){
	 validatePromoJSON = {
             "validatorData": [
                     { "Element": "#homeNo", "FunctionName": "numbersOnlyOptional" },
                     { "Element": "#workNo", "FunctionName": "numbersOnlyOptional" },
                     { "Element": "#mobNo", "FunctionName": "mobileNoOptional" },
                     { "Element": "#email", "FunctionName": "emailIdOptional" },
                     { "Element": "#emailCc", "FunctionName": "emailIdOptional" },
                     { "Element": "#emergencyContName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
                     { "Element": "#emergencyContNo", "FunctionName": "numbersOnlyOptional" }
                    
                     
                   
                    
             ]
         };
 	
 	return iterateThroughtElemsForValidations(validatePromoJSON);
 }
 function  validateOI(){
	 validatePromoJSON = {
             "validatorData": [
                     { "Element": "#reference", "FunctionName": "dropDown" }
                    
                
             ]
         };
 	
 	return iterateThroughtElemsForValidations(validatePromoJSON);
 }

 function  validateSP(){
	 var whopay = document.getElementById('whopay').value;
	 if(whopay == "Third Party"){
	
	 validatePromoJSON = {
             "validatorData": [
                     { "Element": "#type", "FunctionName": "dropDown" },
                     { "Element": "#typeName", "FunctionName": "dropDown" },
                     { "Element": "#policyNo", "FunctionName": "notEmpty" } 
                     
                     
                
             ]
         };
 	}
 	
 	return iterateThroughtElemsForValidations(validatePromoJSON);
 }
 function alphabetsOnlyWithSpace1(elem) {
	    var regEx = /^[a-zA-Z-]*$/;
	    return validateCompulsaryFeild1(elem, regEx);
	}
 function validateCompulsaryFeild1(elem, regEX) {
	    var elemValue = $(elem).val();
	    if (elemValue.length == 0 || elemValue == undefined || elemValue == null || elemValue == "") {
	        setError1(elem);
	        return false;
	    }
	    if (regEX.test(elemValue)) {
	        removeError1(elem);
	        return true;
	    }
	    else {
	        setError1(elem);
	        return false;
	    }
}
//function to set error for client side validation
 function setError1(element) {
     $(element).css("background-color", "#F2DEDE");
     $(element).parent().addClass("has-error");
     $(element).focus();
     $(element).tooltip('show');
 }

 //function to remove error for client side validation
 function removeError1(element) {
     $(element).css("background-color", "#FFFFFF");
     if ($(element).parent().hasClass("has-error")) {
         $(element).parent().removeClass("has-error");
     }
     $(element).tooltip('hide');
 } 
 function setError3(element) {
	    $(element).css("background-color", "#F2DEDE");
	   // $(element).parent().addClass("has-error");
	    $(element).focus();
	   $(element).tooltip('show');
	}

	//function to remove error for client side validation
	function removeError3(element) {
	    $(element).css("background-color", "#FFFFFF");
	    if ($(element).parent().hasClass("has-error")) {
	        $(element).parent().removeClass("has-error");
	    }
	   $(element).tooltip('hide');
	}
 function validateAllDetails(){
	// alert('hi');
	 var whopay = document.getElementById('whopay').value;



	 var opdwithreg=""; 
	if(document.getElementsByName("opdoption")){
		 var ele = document.getElementsByName('opdoption');
              
            for(i = 0; i < ele.length; i++) {
                if(ele[i].checked){
				opdwithreg=ele[i].value;
				}
			}	
	}
	
	
	
	 var chk = 0;
/*		var reference = document.getElementById('reference').value;
*/		var title1 = document.getElementById('title1').value;
		var treatmentType = document.getElementById('treatmentType').value;
		var whopay = document.getElementById('whopay').value;
		var type = document.getElementById('type').value;
		var typeName = document.getElementById('typeName').value;
		var gptypeName = document.getElementById('gptypeName').value;
		var firstname = document.getElementById('firstName').value;
		var middlename = document.getElementById('middleName').value;
		var lastName = document.getElementById('lastName').value;
		var dob = document.getElementById('dob').value;
		var address = document.getElementById('address').value;
		var town = document.getElementById('town').value;
		var postCode = document.getElementById('postCode').value;
		var homeNo = document.getElementById('homeNo').value;
		var mobNo = document.getElementById('mobNo').value;
		var workNo = document.getElementById('workNo').value;
		var email = document.getElementById('email').value;
		var emailCc = document.getElementById('emailCc').value;
		var emergencyContNo = document.getElementById('emergencyContNo').value;
		var gpname = document.getElementById('gpname').value;
		var adhno = document.getElementById("adhno").value;
		var tpmemb = document.getElementById("tpmemb").value;
		var gender = document.getElementById("gender").value
		var fathername = document.getElementById("fathername").value;
		var mothername = document.getElementById("mothername").value;
		var campArea = document.getElementById("campArea").value;
		var typeName = document.getElementById("typeName").value;
		var deptcampArea=document.getElementById("deptcampArea").value;
		
		document.getElementById('adhnoError').innerHTML = "";
		document.getElementById('firstNameError').innerHTML = "";
		document.getElementById('middleNameError').innerHTML = "";
		document.getElementById('lastNameError').innerHTML = "";
		document.getElementById('dobError').innerHTML = "";
		document.getElementById('addressError').innerHTML = "";
		document.getElementById('townError').innerHTML = "";
		document.getElementById('postCodeError').innerHTML = "";
		document.getElementById('homeNoError').innerHTML = "";
		document.getElementById('mobNoError').innerHTML = "";
		document.getElementById('workNoError').innerHTML = "";
		document.getElementById('emailError').innerHTML = "";
		document.getElementById('emailCcError').innerHTML = "";
		document.getElementById('emergencyContNoError').innerHTML = "";
/*		document.getElementById("refError").innerHTML = "";	
*/		document.getElementById("conError").innerHTML = "";
		document.getElementById("whopayError").innerHTML = "";	
		document.getElementById("tpError").innerHTML = "";	
		document.getElementById("tpnameError").innerHTML = "";	
		document.getElementById("gpnameError").innerHTML = "";
		document.getElementById("genderError").innerHTML = "";
		document.getElementById("campAreaError").innerHTML = "";
		document.getElementById("deptcampError").innerHTML = "";
		
		var adhnoregEx = /^\d{4}\s\d{4}\s\d{4}$/;
		var adharnoregEx = /^[0123456789]\d{11}$/;
		var regEx = /^\d{10}$/;
	    var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    
	    if(title1 == "" || title1=="0"){
	    	jAlert('error', "Please Select Initial!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
	    }
		if(firstname == "" && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter First Name!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var firstName = document.createElement("label");
				firstName.innerHTML = "Please Enter First Name";
			    document.getElementById('firstNameError').appendChild(firstName);
			    chk=1;
			    setError3(firstNameErrorlbl);
			}
			
		}else{
			removeError3(firstNameErrorlbl);
		}
		if(lastName == "" && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter last Name!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var lastName = document.createElement("label");
				lastName.innerHTML = "Please Enter last Name";
			    document.getElementById('lastNameError').appendChild(lastName);
			    chk=1;
			    setError3(lastNameErrorlbl);
			}
			
		}else{
			removeError3(firstNameErrorlbl);
		}
		if(document.getElementById('islmh') && firstname.match(/\s/g) && chk==0){
			jAlert('error', "Space not allowed in First Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		if(document.getElementById('islmh') &&  firstname.match(/[^a-zA-Z]+/) && chk==0){
			jAlert('error', "Special Symbol & Number Not Allow In First Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
		
		if(document.getElementById('islmh') && middlename!='' && middlename.match(/\s/g) && chk==0){
			jAlert('error', "Space Not Allowed In Middle Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		if(document.getElementById('islmh') && middlename!='' &&  middlename.match(/[^a-zA-Z]+/) && chk==0){
			jAlert('error', "Special Symbol & Number Not Allow In Middle Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
//		if(firstname.match(/\s/g)){
//			var firstName = document.createElement("label");
//			firstName.innerHTML = "Space Not Allow";
//		    document.getElementById('firstNameError').appendChild(firstName);
//		    chk=1;
//		    setError3(firstNameErrorlbl);
//			
//
//			}
//		else{
//			removeError3(firstNameErrorlbl);
//		}
//		if(firstname.match(/[^a-zA-Z]+/)){
//			var firstName = document.createElement("label");
//			firstName.innerHTML = "Special & Number Not Allow";
//		    document.getElementById('firstNameError').appendChild(firstName);
//		    chk=1;
//		    setError3(firstNameErrorlbl);
//			
//
//			}
//		else{
//			removeError3(firstNameErrorlbl);
//		}
		if(lastName == "" && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter Last Name!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var lastName = document.createElement("label");
				lastName.innerHTML = "Please Enter Last Name";
			    document.getElementById('lastNameError').appendChild(lastName);
			    chk=1;
			    setError3(firstNameErrorlbl);
			}
		}else{
			removeError3(firstNameErrorlbl);
		}
		
		if(document.getElementById('islmh') && lastName.match(/\s/g) && chk==0){
			jAlert('error', "Space Not Allowed In Last Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		if(document.getElementById('islmh') &&  lastName.match(/[^a-zA-Z]+/) && chk==0){
			jAlert('error', "Special Symbol & Number Not Allow In Last Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
		if(document.getElementById('islmh') && fathername!='' && fathername.match(/\s/g) && chk==0){
			jAlert('error', "Space Not Allowed In Father Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		if(document.getElementById('islmh') && fathername!='' &&  fathername.match(/[^a-zA-Z]+/) && chk==0){
			jAlert('error', "Special Symbol & Number Not Allow In Father Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
		if(document.getElementById('islmh') && mothername!='' && mothername.match(/\s/g) && chk==0){
			jAlert('error', "Space Not Allowed In Mother Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		if(document.getElementById('islmh') && mothername!='' &&  mothername.match(/[^a-zA-Z]+/) && chk==0){
			jAlert('error', "Special Symbol & Number Not Allow In Mother Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
		if(typeName==68){
		if((typeName==68 || document.getElementById("isCampArea").value==1) && deptcampArea=="" && chk==0){
			
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter deptCamp Area!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var deptcamp = document.createElement("label");
				deptcamp.innerHTML = "Please Enter Dept ";
			    document.getElementById('deptcampError').appendChild(deptcamp);
			    chk=1;
			    setError3(firstNameErrorlbl);
			}
		}else{
			removeError3(campAreaErrorlbl);
		}
	}	
		
		//var lastName = document.getElementById('lastName').value;
//		if(lastName.match(/\s/g)){
//			var lastName = document.createElement("label");
//			lastName.innerHTML = "Space Not Allow";
//		    document.getElementById('lastNameError').appendChild(lastName);
//		    chk=1;
//		    setError3(firstNameErrorlbl);
//		}
//		else{
//			removeError3(firstNameErrorlbl);
//		}
//		if(lastName.match(/[^a-zA-Z]+/)){
//			var lastName = document.createElement("label");
//			lastName.innerHTML = "Special & Number Not Allow";
//		    document.getElementById('lastNameError').appendChild(lastName);
//		    chk=1;
//		    setError3(firstNameErrorlbl);
//		}
//		else{
//			removeError3(firstNameErrorlbl);
//		}
//		if(middlename.match(/[^a-zA-Z]+/)){
//			var lastName = document.createElement("label");
//			lastName.innerHTML = "Special & Number Not Allow";
//		    document.getElementById('lastNameError').appendChild(lastName);
//		    chk=1;
//		    setError3(firstNameErrorlbl);
//		}
//		else{
//			removeError3(firstNameErrorlbl);
//		}
		
		if(dob == "" && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter DOB!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var dob = document.createElement("label");
				dob.innerHTML = "Please Enter DOB";
			    document.getElementById('dobError').appendChild(dob);
			    chk=1;
			    setError3(doblbl);
			}
		}
		else{
			removeError3(doblbl);
		}
		if((gender == "0" || gender == "") && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Select Gender!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var gender = document.createElement("label");
				gender.innerHTML = "Please Select gender";
			    document.getElementById('genderError').appendChild(gender);
			    chk=1;
			    setError3(genderErrorlbl);
			}
		}else{
			removeError3(genderErrorlbl);
		}
		
		
		if((typeName==52 || document.getElementById("isCampArea").value==1) && campArea=="" && chk==0){
			
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter Camp Area!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var campArea = document.createElement("label");
				campArea.innerHTML = "Please Enter Camp Area";
			    document.getElementById('campAreaError').appendChild(campArea);
			    chk=1;
			    setError3(campAreaErrorlbl);
			}
		}else{
			removeError3(campAreaErrorlbl);
		}
		
		
		
		//if(postCode == ""){
		//	var postCode = document.createElement("label");
		//	postCode.innerHTML = "Please Enter Post Code / Pin Code";
		//    document.getElementById('postCodeError').appendChild(postCode);
		//    chk=1;
		//    setError3(postCodeErrorlbl);
		//}
		//else{
		//	removeError3(postCodeErrorlbl);
		//}
		//if(homeNo == ""){
			/*var homeNo = document.createElement("label");
			homeNo.innerHTML = "Please Enter Home No.";
		    document.getElementById('homeNoError').appendChild(homeNo);
		    chk=1;*/
	/*		removeError3(homeNoErrorlbl);
		}
		else if(!regEx.test(document.getElementById('homeNo').value)) {
			var homeNo = document.createElement("label");
			homeNo.innerHTML = "Please Enter Valid No.";
		    document.getElementById('homeNoError').appendChild(homeNo);
		    chk=1;
		    setError3(homeNoErrorlbl);
		}
		else{
			removeError3(homeNoErrorlbl);
		}*/
		/*if(mobNo == "") {
			var mobNo = document.createElement("label");
			mobNo.innerHTML = "Please Enter Mobile Number";
		    document.getElementById('mobNoError').appendChild(mobNo);
		    chk=1;
		    setError3(mobNoErrorlbl);
		}
		else*/
		
		/*if(!regEx.test(mobNo)) {
			var mobNo = document.createElement("label");
			mobNo.innerHTML = "Please Enter Valid No.";
		    document.getElementById('mobNoError').appendChild(mobNo);
		    chk=1;
		    setError3(mobNoErrorlbl);
		    jAlert('error', "Please enter valid mobile no.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		else{
			removeError3(mobNoErrorlbl);
		}*/
		if(document.getElementById('islmh')){
			if(mobNo == "" && chk==0) {
				jAlert('error', "Please Enter Contact No!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regEx.test(mobNo) && chk==0){
				jAlert('error', "Please Enter Valid Contact No.", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(email!="" && !emailregEx.test(email)){
				jAlert('error', "Please Enter Valid Email", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
		}
		if(mobNo.length!=10 && mobNo!='' && chk==0) {
			if(document.getElementById('islmh')){
				 jAlert('error', "Please Enter Valid Mobile No.", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				 chk=1;
			}else{
				var mobNo = document.createElement("label");
				mobNo.innerHTML = "Please Enter Valid No.";
			    document.getElementById('mobNoError').appendChild(mobNo);
			    chk=1;
			    setError3(mobNoErrorlbl);
			}
		}else{
			removeError3(mobNoErrorlbl);
		}
		
		if(address == "" && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter Address!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var address = document.createElement("label");
				address.innerHTML = "Please Enter Address";
			    document.getElementById('addressError').appendChild(address);
			    chk=1;
			    setError3(addressErrorlbl);
			}
		}
		else{
			removeError3(addressErrorlbl);
		}
		if((town == "0" || town == "") && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Enter Town!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var town = document.createElement("label");
				town.innerHTML = "Please Enter Town";
			    document.getElementById('townError').appendChild(town);
			    chk=1;
			    setError3(townErrorlbl);
			}
		}
		else{
			removeError3(townErrorlbl);
		}
		
		const reg = new RegExp('^[0-9]+$');
			if(document.getElementById('islmh')){
				if(postCode!='' && (!postCode.match(reg))&& chk==0){
					console.log(!postCode.match(reg));
			jAlert('error', "Special Symbol & Alphabet Not Allow In Pin Code.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		}
		
		if(!adharnoregEx.test(adhno) && adhno !="" && chk==0) {
			if(document.getElementById('islmh')){
				 jAlert('error', "Please Enter Valid Aadhar No.", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				 chk=1;
			}else{
				var adhno = document.createElement("label");
				adhno.innerHTML = "Please Enter Valid Adhar No.";
			    document.getElementById('adhnoError').appendChild(adhno);
			    chk=1;
			    setError3(adhnoErrorlbl);
			}
		}
		else{
			removeError3(adhnoErrorlbl);
		}
		
		
		
		/*if(workNo == "") {
			removeError3(workNoErrorlbl);
		}
		else if(!regEx.test(workNo)) {
			var workNo = document.createElement("label");
			workNo.innerHTML = "Please Enter Valid No.";
		    document.getElementById('workNoError').appendChild(workNo);
		    chk=1;
		    setError3(workNoErrorlbl);
		}
		else{
			removeError3(workNoErrorlbl);
		}
		if(emergencyContNo == "") {
			removeError3(emergencyContNoErrorlbl);
		}
		else if(!regEx.test(emergencyContNo)) {
			var emergencyContNo = document.createElement("label");
			emergencyContNo.innerHTML = "Please Enter Valid No.";
		    document.getElementById('emergencyContNoError').appendChild(emergencyContNo);
		    chk=1;
		    setError3(emergencyContNoErrorlbl);
		}
		else{
			removeError3(emergencyContNoErrorlbl);
		}
		if(email == "") {
			removeError3(emailErrorlbl);
		}
		else if(!emailregEx.test(email)){
			var email = document.createElement("label");
			email.innerHTML = "Please Enter Valid No.";
		    document.getElementById('emailError').appendChild(email);
		    chk=1;
		    setError3(emailErrorlbl);
		}
		if(emailCc == "") {
			removeError3(emailCcErrorlbl);
		}
		else if(!emailregEx.test(emailCc)){
			var emailCc = document.createElement("label");
			emailCc.innerHTML = "Please Enter Valid No.";
		    document.getElementById('emailCcError').appendChild(emailCc);
		    chk=1;
		    setError3(emailCcErrorlbl);
		}
		else{
			removeError3(emailCcErrorlbl);
		}*/
		
		if(document.getElementById('islmh')){
			//1.Patient registration->Relative Details->Name should accept only alphabet
			//2.Patient registration->Relative Details->Phone No should accept only numeric value
			var emergencyContName = document.getElementById("emergencyContName").value;
			var emergencyContNo = document.getElementById("emergencyContNo").value;
			
			if(emergencyContName!='' && chk==0 && emergencyContName.match(/[^a-zA-Z, ]/)){
				jAlert('error', "Please Enter Valid Relative Name.", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
			
			if(emergencyContNo!='' && chk==0 && (emergencyContNo.length!=10 || validateNumberaddPatientTab(emergencyContNo)==false)){
				jAlert('error', "Please Enter Valid Relative Phone No.", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
			
		}
		
		if(gptypeName!=0){
			if(gpname == 0 && chk==0){
				if(document.getElementById('islmh')){
					jAlert('error', "Please Select GP Name", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					chk=1;
				}else{
					var gpname = document.createElement("label");
					gpname.innerHTML = "Please Select GP Name";
				    document.getElementById('gpnameError').appendChild(gpname);
				    chk=1;
				    setError3(gpnameErrorlbl);
				}
			}
			else{
				removeError3(gpnameErrorlbl);

			}
		}
		/*if(reference == 0){		
			var ref = document.createElement("label");
			ref.innerHTML = "Please select Reference";
		    document.getElementById('refError').appendChild(ref);
		    chk=1;
		}
		
		if(treatmentType == 0){		
			var con = document.createElement("label");
			con.innerHTML = "Please select treatmentType";
		    document.getElementById('conError').appendChild(con);
		    chk=1;
		}*/
		if(whopay == 0 && chk==0){		
			if(document.getElementById('islmh')){
		    	jAlert('error', "Please Select Who Pay", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else{
				var wwp = document.createElement("label");
				wwp.innerHTML = "Please Select Who Pay";
			    document.getElementById('wwpError').appendChild(wwp);
			    chk=1;
			}
		}
		var typenamebyuse='';
		var compname='';
		var neisno='';
		var designationbytp='';
		var unitstation='';
		var claimbytp='';
		var relationvbytp='';
		var colliery='';
		var areabytp='';
		var policyholder='';
		if(whopay == "Third Party" && chk==0){
			if(type==''){
				if(document.getElementById('islmh')){
					jAlert('error', "Please Select Payer Type!", 'Error Dialog');
				}else{
					jAlert('error', "Please Enter Type!", 'Error Dialog');
				}
		        setTimeout(function() {
		        	$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				chk=1;
			}
			/*if(type == 0){		
			var type1 = document.createElement("label");
			type1.innerHTML = "Please Select Value";
		    document.getElementById('tpError').appendChild(type1);
		    chk=1;
		    setError3(tpErrorlbl);
		}
		else{
			removeError3(tpErrorlbl);
		}*/
//		if(typeName == 0){		
//			var tpname = document.createElement("label");
//			tpname.innerHTML = "Please Select typeName";
//		    document.getElementById('tpnameError').appendChild(tpname);
//		    chk=1;
//		    setError3(tpErrorlbl);
//		}
//		else{
//			removeError3(tpErrorlbl);
//		}
		if(typeName==0 && chk==0){
			if(document.getElementById('islmh')){
				jAlert('error', "Please Select Payer!", 'Error Dialog');
			}else{
				jAlert('error', "Please Enter Third Party Name!", 'Error Dialog');
			}
	        setTimeout(function() {
	        $("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			chk=1;
		}
		
		/*else{
			removeError3(tpErrorlbl);
		}*/
		 typenamebyuser=document.getElementById("type").options[document.getElementById('type').selectedIndex].text;
		 compname=document.getElementById("compname").value;
		 neisno=document.getElementById("neisno").value;
		 designationbytp=document.getElementById("designationbytp").value;
		 unitstation=document.getElementById("unitstation").value;
		 claimbytp=document.getElementById("claimbytp").value;
		 relationvbytp=document.getElementById("relationvbytpe").value;
		 colliery=document.getElementById("colliery").value;
		 areabytp=document.getElementById("areabytp").value;
//		if(typenamebyuser=='CGHS'){
//			if(compname==''){
//		        
//		          jAlert('error', "please enter Employee name!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(neisno==''){
//			        
//		          jAlert('error', "please enter NEIS/Card No!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(designationbytp==''){
//			        
//		          jAlert('error', "please enter Designation!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(unitstation==''){
//			        
//		          jAlert('error', "please enter Unit/Station!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(claimbytp==''){
//			        
//		          jAlert('error', "please enter Claim ID!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(relationvbytp==''){
//			        
//		          jAlert('error', "please enter Relation!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }
//			
//			
//		}else if(typenamebyuser=='WCL'){
//			
//			if(compname==''){
//		        
//		          jAlert('error', "please enter Employee name!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(neisno==''){
//			        
//		          jAlert('error', "please enter NEIS/Card No!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(designationbytp==''){
//			        
//		          jAlert('error', "please enter Designation!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(colliery==''){
//			        
//		          jAlert('error', "please enter Colliery!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(areabytp==''){
//			        
//		          jAlert('error', "please enter Area!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }else if(relationvbytp==''){
//			        
//		          jAlert('error', "please enter Relation!", 'Error Dialog');
//					
//						setTimeout(function() {
//							$("#popup_container").remove();
//							removeAlertCss();
//						}, alertmsgduration);
//						chk=1;
//		     }
//			
//		}
		
		}
		if(document.getElementById('islmh') && whopay != "Third Party" && chk==0){
			if(type=='0'){
				if(document.getElementById('islmh')){
					jAlert('error', "Please Select Payer Type!", 'Error Dialog');
				}else{
					jAlert('error', "Please Enter Type!", 'Error Dialog');
				}
		        setTimeout(function() {
		        	$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				chk=1;
			}
			
			if(typeName==0 && chk==0){
				if(document.getElementById('islmh')){
					jAlert('error', "Please Select Payer!", 'Error Dialog');
				}else{
					jAlert('error', "Please Enter Third Party Name!", 'Error Dialog');
				}
		        setTimeout(function() {
		        $("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
			
		}
		
		if(document.getElementById('islmh') && (opdwithreg=="regwopd" || opdwithreg=="")){
			var regexp = /^\d+(\.\d{1,2})?$/;
			//var patientcategory = document.getElementById('patientcategory').value;
			var diaryUsersss = document.getElementById('diaryUsersss').value;
			var paymenttype = document.getElementById('paymenttype').value;
			var registrationcharge=document.getElementById("registrationcharge").value;
			var consultationcharge='0';
			if(document.getElementById("consultationcharge")){
			consultationcharge=document.getElementById("consultationcharge").value;
			}
			var othercharge='0';
			if(document.getElementById("othercharge")){
			othercharge=document.getElementById("othercharge").value;	
			}
			
			
			var discountvalue='0';
			if(document.getElementById("discountvalue")){
				discountvalue=document.getElementById("discountvalue").value
			}
			var netamount=document.getElementById("netamount").value;
			
			/*if(patientcategory=='' && chk==0){
				jAlert('error', "Please Select Patient Category!", 'Error Dialog');
					setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
					}, alertmsgduration);
				chk=1;
			}else */if(diaryUsersss=='0' && chk==0){
				jAlert('error', "Please Select Department!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regexp.test(registrationcharge) && chk==0){
				jAlert('error', "Registration Charge: Accept Numeric Value Only", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regexp.test(consultationcharge) && chk==0){
				jAlert('error', "Consultation Charge: Accept Numeric Value Only", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regexp.test(othercharge) && chk==0){
				jAlert('error', "Other Charge: Accept Numeric Value Only", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regexp.test(discountvalue) && chk==0){
				jAlert('error', "Discount: Accept Numeric Value Only", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}else if(!regexp.test(netamount) && chk==0){
				jAlert('error', "Net Amount: Should Be Numeric Value Only", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
			if(paymenttype=='' && chk==0){
				jAlert('error', "Please Select Payment Mode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				chk=1;
			}
			
		}
		if(chk==1)
	    {
	       return false;
	    }
		
		else{
			
//			$('#baselayout1loaderPopup').modal('show');
			if(document.getElementById('islmh')){
				$("#newloaderPopup").modal('show');
			}else{
				document.getElementById("yuviloader").style.display="";
			}
			return true;
			
		}
		
		
	 
	/* if(whopay == "Third Party"){
	 validatePromoJSON = {
             "validatorData": [
                     { "Element": "#firstName", "FunctionName": "alphabetsOnlyWithSpace" },
                     { "Element": "#middleName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
                     { "Element": "#lastName", "FunctionName": "alphabetsOnlyWithSpace" },
                     { "Element": "#dob", "FunctionName": "notEmpty" },
                     { "Element": "#address", "FunctionName": "notEmpty" }, 
                     { "Element": "#town", "FunctionName": "notEmpty" },
                     { "Element": "#postCode", "FunctionName": "notEmpty" },
                     { "Element": "#country", "FunctionName": "dropDown" }, 
                     { "Element": "#homeNo", "FunctionName": "mobileNo" },
                     { "Element": "#mobNo", "FunctionName": "mobileNoOptional" },
                     { "Element": "#email", "FunctionName": "emailIdOptional" },
                     { "Element": "#whopay", "FunctionName": "dropDown" },
                     { "Element": "#type", "FunctionName": "dropDown" },
                     { "Element": "#typeName", "FunctionName": "dropDown" }                     
	 
             ]
         };
	 }
	 else{
		 validatePromoJSON = {
	             "validatorData": [
	                     { "Element": "#firstName", "FunctionName": "alphabetsOnlyWithSpace" },
	                     { "Element": "#middleName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
	                     { "Element": "#lastName", "FunctionName": "alphabetsOnlyWithSpace" },
	                     { "Element": "#dob", "FunctionName": "notEmpty" },
	                     { "Element": "#address", "FunctionName": "notEmpty" }, 
	                     { "Element": "#town", "FunctionName": "notEmpty" },
	                     { "Element": "#postCode", "FunctionName": "notEmpty" },
	                     { "Element": "#country", "FunctionName": "dropDown" }, 
	                     { "Element": "#homeNo", "FunctionName": "mobileNo" },
	                     { "Element": "#mobNo", "FunctionName": "mobileNoOptional" },
	                     { "Element": "#email", "FunctionName": "emailIdOptional" },
	                     { "Element": "#whopay", "FunctionName": "dropDown" }
	                     
		 
	             ]
	         };
	 }
	
	
	 if(validchk == 0 && validchk1 == 0){
		
		 return iterateThroughtElemsForValidations(validatePromoJSON);
	 }
	 else{
		 
		 return false;
	 }*/
 }
 
 function validateNumberaddPatientTab(inputtxt)
 {
 	 var numbers = /^[0-9]+$/;  
      
      if(inputtxt.match(numbers))  {
         return true;
      } else {
           return false;
      }
 } 
 
 function saveNewThirdPartyDetail(){
		var chk =0;
		var thirdPartyType1 = document.getElementById("thirdPartyType1").value;  
		var thirdPartyCompanyName = document.getElementById("thirdPartyCompanyName").value;  
		var compnyEmail = document.getElementById("compnyEmail").value;
		var compnyTelephone = document.getElementById("compnyTelephone").value;  
		var outInvoiceLimit = document.getElementById("outInvoiceLimit").value;
		var accountWarningLimit = document.getElementById("accountWarningLimit").value;  
		var dnaLimit = document.getElementById("dnaLimit").value;
		
		 document.getElementById('thirdPartyTypeError1').innerHTML = "";
		 document.getElementById('thirdPartyCompanyNameError').innerHTML = "";
		 document.getElementById('compnyEmailError').innerHTML = "";
		 document.getElementById('compnyEmailError').innerHTML = "";
		 document.getElementById('compnyTelephoneError').innerHTML = "";
		 document.getElementById('outInvoiceLimitError').innerHTML = "";
		 document.getElementById('accountWarningLimitError').innerHTML = "";
		 document.getElementById('dnaLimitError').innerHTML = "";
		if (thirdPartyType1 ==  "0") {
	      	var thirdPartyType1 = document.createElement("label");
	      	thirdPartyType1.innerHTML = "Select Type";
	        document.getElementById('thirdPartyTypeError1').appendChild(thirdPartyType1);
	        chk=1;
	     }  
		
		if (thirdPartyCompanyName ==  "") {
	      	var thirdPartyCompanyName = document.createElement("label");
	      	thirdPartyCompanyName.innerHTML = "Enter Company Name";
	        document.getElementById('thirdPartyCompanyNameError').appendChild(thirdPartyCompanyName);
	        chk=1;
	     } 
		var atpos=compnyEmail.indexOf("@");
		var dotpos=compnyEmail.lastIndexOf(".");
		if (compnyEmail ==  "") {
	      	var compnyEmail = document.createElement("label");
	      	compnyEmail.innerHTML = "Enter company email";
	        document.getElementById('compnyEmailError').appendChild(compnyEmail);
	        chk=1;
	    } 
		 else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=compnyEmail.length)
	  	 {	
	  	 	
	  	 	var email1 = document.createElement("label");
	        email1.innerHTML = "Enter valid Email"; 
	        document.getElementById('compnyEmailError').appendChild(email1);
	  	 
	  	 }
		if (compnyTelephone ==  "") {
	      	var compnyTelephone = document.createElement("label");
	      	compnyTelephone.innerHTML = "Eneter contact no.";
	        document.getElementById('compnyTelephoneError').appendChild(compnyTelephone);
	        chk=1;
	     } 
		 else if (isNaN(compnyTelephone)===true){
		  	 var mobiledig = document.createElement("label");
		     mobiledig.innerHTML = "Enter valid No."; 
		     document.getElementById('compnyTelephoneError').appendChild(mobiledig);
		  	chk=1;
		}
		
		
		if (outInvoiceLimit ==  "") {
	      	var outInvoiceLimit = document.createElement("label");
	      	outInvoiceLimit.innerHTML = "Enter Invoice Lmt.";
	        document.getElementById('outInvoiceLimitError').appendChild(outInvoiceLimit);
	        chk=1;
	     } 
		else if (isNaN(outInvoiceLimit)===true){
		  	 var dig = document.createElement("label");
		     dig.innerHTML = "Enter valid No."; 
		     document.getElementById('outInvoiceLimitError').appendChild(dig);
		  	 chk=1;
		}
		if (accountWarningLimit ==  "") {
	      	var accountWarningLimit = document.createElement("label");
	      	accountWarningLimit.innerHTML = "Enter Acc Warning Lmt.";
	        document.getElementById('accountWarningLimitError').appendChild(accountWarningLimit);
	        chk=1;
	     } 
		else if (isNaN(accountWarningLimit)===true){
		  	 var dig = document.createElement("label");
		     dig.innerHTML = "Enter valid No."; 
		     document.getElementById('accountWarningLimitError').appendChild(dig);
		  	 chk=1;
		}
		if (dnaLimit ==  "") {
	      	var dnaLimit = document.createElement("label");
	      	dnaLimit.innerHTML = "Enter DNA Lmt.";
	        document.getElementById('dnaLimitError').appendChild(dnaLimit);
	        chk=1;
	     } 
		else if (isNaN(dnaLimit)===true){
		  	 var dig = document.createElement("label");
		     dig.innerHTML = "Enter valid No."; 
		     document.getElementById('dnaLimitError').appendChild(dig);
		  	 chk=1;
		}
		
		
		if(chk==1)
	    {
	       return false;
	    }
	    else
	    {
	    	var url = "saveNew1ThirdParty?thirdPartyType="+thirdPartyType1+"&thirdPartyCompanyName="+thirdPartyCompanyName+"&compnyEmail="+compnyEmail+"&compnyTelephone="+compnyTelephone+"&outInvoiceLimit="+outInvoiceLimit+"&accountWarningLimit="+accountWarningLimit+"&dnaLimit="+dnaLimit+"";

	    	if (window.XMLHttpRequest) {
	    			req = new XMLHttpRequest();
	    		}
	    		else if (window.ActiveXObject) {
	    			isIE = true;
	    			req = new ActiveXObject("Microsoft.XMLHTTP");
	    		}   
	    	               
	    	    req.onreadystatechange = saveNewThirdPartyDetailRequest;
	    	    req.open("GET", url, true); 
	    	              
	    	    req.send(null);


	    	    return true;
	    }
		
		
	}
	function saveNewThirdPartyDetailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			document.getElementById("typeName").innerHTML = req.responseText;
			//jAlert('success', 'Third Party Details added successfully.', 'Patient');
			tempAlert("Third Party Details added successfully.",5000);
			resetNewThirdPartyDetails();
			$('#thirdPartyDetailsPopup').modal( "hide" );

	     
	     }
	}
	}
	 function saveNewThirdPartyDetail1(){
			var chk =0;
			var thirdPartyType1 = document.getElementById("thirdPartyType1").value;  
			var thirdPartyCompanyName = document.getElementById("thirdPartyCompanyName").value;  
			var compnyEmail = document.getElementById("compnyEmail").value;
			var compnyTelephone = document.getElementById("compnyTelephone").value;  
			var outInvoiceLimit = document.getElementById("outInvoiceLimit").value;
			var accountWarningLimit = document.getElementById("accountWarningLimit").value;  
			var dnaLimit = document.getElementById("dnaLimit").value;
			
			 document.getElementById('thirdPartyTypeError1').innerHTML = "";
			 document.getElementById('thirdPartyCompanyNameError').innerHTML = "";
			 document.getElementById('compnyEmailError').innerHTML = "";
			 document.getElementById('compnyEmailError').innerHTML = "";
			 document.getElementById('compnyTelephoneError').innerHTML = "";
			 document.getElementById('outInvoiceLimitError').innerHTML = "";
			 document.getElementById('accountWarningLimitError').innerHTML = "";
			 document.getElementById('dnaLimitError').innerHTML = "";
			if (thirdPartyType1 ==  "0") {
		      	var thirdPartyType1 = document.createElement("label");
		      	thirdPartyType1.innerHTML = "Select Type";
		        document.getElementById('thirdPartyTypeError1').appendChild(thirdPartyType1);
		        chk=1;
		     }  
			
			if (thirdPartyCompanyName ==  "") {
		      	var thirdPartyCompanyName = document.createElement("label");
		      	thirdPartyCompanyName.innerHTML = "Enter Company Name";
		        document.getElementById('thirdPartyCompanyNameError').appendChild(thirdPartyCompanyName);
		        chk=1;
		     } 
			var atpos=compnyEmail.indexOf("@");
			var dotpos=compnyEmail.lastIndexOf(".");
			if (compnyEmail ==  "") {
		      	var compnyEmail = document.createElement("label");
		      	compnyEmail.innerHTML = "Enter company email";
		        document.getElementById('compnyEmailError').appendChild(compnyEmail);
		        chk=1;
		    } 
			 else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=compnyEmail.length)
		  	 {	
		  	 	
		  	 	var email1 = document.createElement("label");
		        email1.innerHTML = "Enter valid Email"; 
		        document.getElementById('compnyEmailError').appendChild(email1);
		  	 
		  	 }
			if (compnyTelephone ==  "") {
		      	var compnyTelephone = document.createElement("label");
		      	compnyTelephone.innerHTML = "Eneter contact no.";
		        document.getElementById('compnyTelephoneError').appendChild(compnyTelephone);
		        chk=1;
		     } 
			 else if (isNaN(compnyTelephone)===true){
			  	 var mobiledig = document.createElement("label");
			     mobiledig.innerHTML = "Enter valid No."; 
			     document.getElementById('compnyTelephoneError').appendChild(mobiledig);
			  	chk=1;
			}
			
			
			if (outInvoiceLimit ==  "") {
		      	var outInvoiceLimit = document.createElement("label");
		      	outInvoiceLimit.innerHTML = "Enter Invoice Lmt.";
		        document.getElementById('outInvoiceLimitError').appendChild(outInvoiceLimit);
		        chk=1;
		     } 
			else if (isNaN(outInvoiceLimit)===true){
			  	 var dig = document.createElement("label");
			     dig.innerHTML = "Enter valid No."; 
			     document.getElementById('outInvoiceLimitError').appendChild(dig);
			  	 chk=1;
			}
			if (accountWarningLimit ==  "") {
		      	var accountWarningLimit = document.createElement("label");
		      	accountWarningLimit.innerHTML = "Enter Acc Warning Lmt.";
		        document.getElementById('accountWarningLimitError').appendChild(accountWarningLimit);
		        chk=1;
		     } 
			else if (isNaN(accountWarningLimit)===true){
			  	 var dig = document.createElement("label");
			     dig.innerHTML = "Enter valid No."; 
			     document.getElementById('accountWarningLimitError').appendChild(dig);
			  	 chk=1;
			}
			if (dnaLimit ==  "") {
		      	var dnaLimit = document.createElement("label");
		      	dnaLimit.innerHTML = "Enter DNA Lmt.";
		        document.getElementById('dnaLimitError').appendChild(dnaLimit);
		        chk=1;
		     } 
			else if (isNaN(dnaLimit)===true){
			  	 var dig = document.createElement("label");
			     dig.innerHTML = "Enter valid No."; 
			     document.getElementById('dnaLimitError').appendChild(dig);
			  	 chk=1;
			}
			
			
			if(chk==1)
		    {
		       return false;
		    }
		    else
		    {
		    	var url = "saveNew1ThirdParty?thirdPartyType="+thirdPartyType1+"&thirdPartyCompanyName="+thirdPartyCompanyName+"&compnyEmail="+compnyEmail+"&compnyTelephone="+compnyTelephone+"&outInvoiceLimit="+outInvoiceLimit+"&accountWarningLimit="+accountWarningLimit+"&dnaLimit="+dnaLimit+"";

		    	if (window.XMLHttpRequest) {
		    			req = new XMLHttpRequest();
		    		}
		    		else if (window.ActiveXObject) {
		    			isIE = true;
		    			req = new ActiveXObject("Microsoft.XMLHTTP");
		    		}   
		    	               
		    	    req.onreadystatechange = saveNewThirdPartyDetailRequest1;
		    	    req.open("GET", url, true); 
		    	              
		    	    req.send(null);


		    	    return true;
		    }
			
			
		}
		function saveNewThirdPartyDetailRequest1(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				
				document.getElementById("typeName").innerHTML = req.responseText;
				//jAlert('success', 'Third Party Details added successfully.', 'Patient');
				tempAlert("Third Party Details added successfully.",5000);
				resetNewThirdPartyDetails();
				$('#thirdPartyDetailsPopup1').modal( "hide" );

		     
		     }
		}
		}
	function resetNewThirdPartyDetails(){
		document.getElementById("thirdPartyType1").value = "0";  
		document.getElementById("thirdPartyCompanyName").value = "";  
		document.getElementById("compnyEmail").value = "";
		document.getElementById("compnyTelephone").value = "";  
		document.getElementById("outInvoiceLimit").value = "";
		document.getElementById("accountWarningLimit").value = "";  
		document.getElementById("dnaLimit").value = " ";
	}
function setTypeField(){
	var type = document.getElementById("type").value;
//	document.getElementById("type").value = type;
	
	document.getElementById("savetpbtn").style.display = 'none';
	//document.getElementById("tpbreadcrum").style.display = 'none';

	document.getElementById('type1').value = type;
	
	document.getElementById("hiddentype").value = type;

	document.getElementById("type").value = "0";
	document.getElementById("typeName").value = "0";
	
	
	
	$("#type").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	$("#typeName").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	
	$("#type1").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	//alert('hi');
	/* document.getElementById('type1').value = type;
	 document.getElementById('typeName1').value = document.getElementById('typeName').value;	*/
	
	
	
	var t = 'formtarget';

	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

	document.getElementById('addthirpartfrm').submit();
	

	 
	 //$('#thirdPartyDetailsPopup1').modal( "show" );
	
}

function setTypeFieldofTp(){
	var type = document.getElementById("type").value;
	document.getElementById("type1").className="";
	document.getElementById("type1").value = type;
	$("#type1").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
//	document.getElementById("savetpbtn").style.display = 'none';
//	document.getElementById("tpbreadcrum").style.display = 'none';
	$('#thirdPartyDetailsPopup1').modal( "show" );
	
}

function checkMobileValidation(mob){
	
	
	var url = "checkMobileExistClient?mob="+mob+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkMobileValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkMobileValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchk1 = 1;
				}
				
				 if(false)
			     {	
					 document.getElementById('mobNoError1').innerHTML="";
					 var mob = document.createElement("label");
					 mob.innerHTML = "Mobile No already Exist Please try another";
				     document.getElementById('mobNoError1').appendChild(mob);
				     chk = 1;
				     validchk1 = 1;
				     return false;
			     }
			     else
			     {
			    	
			    	 document.getElementById('mobNoError1').innerHTML="";
//			    	 addZero('mobNo');
			    	 chk = 0;
			    	 validchk1 = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}

function checkEmailValidation(email){
	
	
	var url = "checkEmailExistClient?email="+email+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkEmailValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkEmailValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchk = 1;
				}
				
				 if(chk == 1)
			     {	
					
					 document.getElementById('emailError1').innerHTML="";
					 var mob = document.createElement("label");
					 mob.innerHTML = "Email Id already Exist Please try another";
				     document.getElementById('emailError1').appendChild(mob);
				     chk = 1;
				     validchk = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('emailError1').innerHTML="";
			    	
			    	 chk = 0;
			    	 validchk = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}
function setGP(id){
	var url = "setgpClient?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setGPRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setGPRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('gpname').innerHTML = req.responseText;
			$("#gpname").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
		}
	}
}

function setGPList(id){
	var url = "setgpListClient?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setGPListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setGPListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('gpname').innerHTML = req.responseText;
			$("#gpname").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
		}
	}
}






//MANOJ

function setGPTypeNamePopup(id){
	var url = "setTypeNameDropDownClient?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setGPTypeNamePopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	
}

function setGPTypeNamePopupRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
    		document.getElementById("gptypeNamepopup").innerHTML = req.responseText;
    		
    		/*$("#gptypeName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});*/
         }
	}
}




function setGPTypeName(id){
	

		var url = "setTypeNameDropDownClient?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setGPTypeNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}    
	

function setGPTypeNameRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		document.getElementById("gptypeName").innerHTML = req.responseText;
	    		document.getElementById("gpname").innerHTML = '<option value = "0">Select GP</option>';
	    		/*$("#gptypeName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});*/
	         }
		}
	}
	
	
	function setGPDataList(surgeryid){
	
		var url = "gpdataClient?surgeryid="+surgeryid+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setGPDataListRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function setGPDataListRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
				
	    		document.getElementById("gpname").innerHTML = req.responseText;
	    		$("#gpname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
	    		
	    		
	         }
		}
	}
	
	
	
	//save gp data
	
	function saveNewGpData(){
		
		var validchkemail = 0;
		var validchklmob = 0;
		var popgptype = 1;
		var gptypeNamepopup = document.getElementById('gptypeNamepopup').value;
		var gpnameid = document.getElementById('gpnameid').value;
		var workphno = document.getElementById('workphno').value;
		var gpemailid = document.getElementById('gpemailid').value;
		var gpfax = document.getElementById('gpfax').value;
		var gpNote = document.getElementById('gpNote').value;
		
		validatePromoJSON = {
	            "validatorData": [
						{ "Element": "#gptypeNamepopup", "FunctionName": "dropDown" },
						{ "Element": "#gpemailid", "FunctionName": "emailIdOptional" },
	                    { "Element": "#workphno", "FunctionName": "phoneOptional" },
	                    { "Element": "#gptypeNamepopup", "FunctionName": "notEmpty" },
	                    { "Element": "#gpnameid", "FunctionName": "notEmpty" }                   
	                    
	            ]
	        };
		
	
	    if(iterateThroughtElemsForValidations(validatePromoJSON)){
	    	 if(validchkemail == 0 && validchklmob == 0){
	    	var url = "savegpClient?popgptype="+popgptype+"&gptypeNamepopup="+gptypeNamepopup+"&gpnameid="+gpnameid+"&workphno="+workphno+"&gpemailid="+gpemailid+"&gpfax="+gpfax+"&gpNote="+gpNote+" ";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveNewGpDataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);	
		    return true;
	    	 }
	    	
	    	
	    }else{
	    	return false;
	    }
	    
	   
		
	}
	
	function saveNewGpDataRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		document.getElementById("gpname").innerHTML = req.responseText;
	    		$("#gpname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				$('#gpDetailsPopup').modal( "hide" );
	    		
				
				// window.location.reload();
	         }
		}
		
	}
	
	
	
	
	
	function addNewGpData(){	
		
		 
		 var typename = document.getElementById('gptypeName').value;
		// alert(typename);
		 document.getElementById('gptypeNamepopup').value = typename;		 
		 $("#gptypeNamepopup").trigger("chosen:updated");
		 $(".chosen").chosen({allow_single_deselect: true});		
		 document.getElementById('gpnameid').value = "";
		 document.getElementById('workphno').value = "";
		 document.getElementById('gpemailid').value = "";
		 document.getElementById('gpfax').value = "";
		 document.getElementById('gpNote').value = "";
		 
		 var url = "gpdtailsClient?id="+typename+" ";
			
		    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addNewGpDataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		 
		
		
		
		
	}
	
	function addNewGpDataRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				var data = req.responseText;
				
				var temp = data.split('*');
				var phnno = temp[0];
				var email = temp[1];
				var fax = temp[2];
				var note = temp[3];
				
				if(note=='null'){
					note = "";
				}
				
				 document.getElementById('workphno').value = phnno ;
				 document.getElementById('gpemailid').value = email;
				 document.getElementById('gpfax').value = fax;
				 document.getElementById('gpNote').value = note;
				
				
				 $('#gpDetailsPopup').modal( "show" );
			}
		}
	}
	
	
	
	function addNewGpDataPopup(){	
		
		 
		 var typename = document.getElementById('doctorsurgery').value;
		 //alert(typename);
		 document.getElementById('gptypeNamepopup').value = typename;		 
		 $("#gptypeNamepopup").trigger("chosen:updated");
		 $(".chosen").chosen({allow_single_deselect: true});		
		 document.getElementById('gpnameid').value = "";
		 document.getElementById('workphno').value = "";
		 document.getElementById('gpemailid').value = "";
		 document.getElementById('gpfax').value = "";
		 document.getElementById('gpNote').value = "";
		 $('#gpDetailsPopup').modal( "show" );
		
		
		
	}
	
	
function saveTpDetails(){
	//alert('hi');
	var companyName = document.getElementById('typeName1').value;
	var id = document.getElementById('type1').value;
	//get all values
	var type = document.getElementById('type1').value;
	var newtpname = document.getElementById('companyName').value;
	var telephoneLine = document.getElementById('telephoneLine').value;
	var compnyEmail = document.getElementById('compnyEmail').value;
	var address = document.getElementById('tpaddress').value;
	var town = document.getElementById('tptown').value;
	
	var postcode = document.getElementById('tppostcode').value;
	var country = document.getElementById('tpcountry').value;
	var gpname = document.getElementById('gpname').value;
	var workphno = document.getElementById('workphno').value;
	var gpemailid = document.getElementById('gpemailid').value;
	var gpfax = document.getElementById('gpfax').value;
	var notes = document.getElementById('notes').value;
	var outInvoiceLimit = document.getElementById('outInvoiceLimit').value;
	var accountWarningLimit = document.getElementById('accountWarningLimit').value;
	var creditDuration = document.getElementById('creditDuration').value;
	var creditReminderDuration = document.getElementById('creditReminderDuration').value;
	var dnaLimit = document.getElementById('dnaLimit').value;
	var dnaInitialApmt = document.getElementById('dnaInitialApmt').value;
	var compltInitialApmt = document.getElementById('compltInitialApmt').value;
	var dnafollowupApmt = document.getElementById('dnafollowupApmt').value;
	var compltfollowupApmt = document.getElementById('compltfollowupApmt').value;
	var dnafinalApmt = document.getElementById('dnafinalApmt').value;
	var compltfinalApmt = document.getElementById('compltfinalApmt').value;
	var dnamaintenanceApmt = document.getElementById('dnamaintenanceApmt').value;
	var compltmaintenanceApmt = document.getElementById('compltmaintenanceApmt').value;

	 var chk = 0;
	 
		document.getElementById("tpTypeError").innerHTML = "";	
		//document.getElementById("tpnameError").innerHTML = "";	
		
		 if(type == 0){		
				var tptype = document.createElement("label");
				tptype.innerHTML = "Please select Third Party Type";
			    document.getElementById('tpTypeError').appendChild(tptype);
			    chk=1;
			}			
			
			if(chk==1)
		    {
		       return false;
		    }
	 		

	 if(companyName==0){
	 validatePromoJSON = {
            "validatorData": [
                    { "Element": "#type1", "FunctionName": "dropDown" },
                    { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
                    { "Element": "#companyName", "FunctionName": "notEmpty" },
                    { "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dnafinalApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compltInitialApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compltfinalApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }

            ]
        };
	 }
	 else{
		 validatePromoJSON = {
	             "validatorData": [
	                  { "Element": "#type1", "FunctionName": "dropDown" },
	                  { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
	                  { "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#dnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#dnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#dnafinalApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#dnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#compltInitialApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#compltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#compltfinalApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#compltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }
	                     
		 
	             ]
	         };
	 }
	
	 if(id==1){
		 if(companyName==0){
			 validatePromoJSON = {
		             "validatorData": [
		                     { "Element": "#type1", "FunctionName": "dropDown" },
		                     { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
		                     { "Element": "#companyName", "FunctionName": "notEmpty" },
		                     { "Element": "#gpname", "FunctionName": "notEmpty" }

		             ]
		         };
			 }
			 else{
				 validatePromoJSON = {
			             "validatorData": [
			                  { "Element": "#type1", "FunctionName": "dropDown" },
			                  { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
			                  { "Element": "#gpname", "FunctionName": "notEmpty" }
			                     
				 
			             ]
			         };
			 }
		 
	 }
	 if(id==2){
		
		 if(companyName==0){
			 validatePromoJSON = {
		             "validatorData": [
		                     { "Element": "#type1", "FunctionName": "dropDown" },
		                     { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
		                     { "Element": "#companyName", "FunctionName": "notEmpty" },
		                     { "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#dnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#dnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#dnafinalApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#dnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#compltInitialApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#compltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#compltfinalApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#compltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }

		                     
		                     

		             ]
		         };
			 }
			 else{
				 validatePromoJSON = {
			             "validatorData": [
			                    { "Element": "#type1", "FunctionName": "dropDown" },
			                  	{ "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
			                  	{ "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
			                  	{ "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#dnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#dnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#dnafinalApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#dnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#compltInitialApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#compltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#compltfinalApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#compltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }
			                     
				 
			             ]
			         };
			 }
	 }
		
		 if(iterateThroughtElemsForValidations(validatePromoJSON))
		 {
			 var url = "saveNewTpAjaxThirdParty?type="+type+"&newtpname="+newtpname+"&telephoneLine="+telephoneLine+"&compnyEmail="+compnyEmail+"" +
			 "&address="+address+"&town="+town+"&postcode="+postcode+"&country="+country+"&gpname="+gpname+"&workphno="+workphno+"" +
			 "&gpemailid="+gpemailid+"&gpfax="+gpfax+"&notes="+notes+"&outInvoiceLimit="+outInvoiceLimit+"&accountWarningLimit="+accountWarningLimit+"" +
			 "&creditDuration="+creditDuration+"&creditReminderDuration="+creditReminderDuration+"&dnaLimit="+dnaLimit+"&dnaInitialApmt="+dnaInitialApmt+"" +
			 "&compltInitialApmt="+compltInitialApmt+"&dnafollowupApmt="+dnafollowupApmt+"&compltfollowupApmt="+compltfollowupApmt+"&dnafinalApmt="+dnafinalApmt+"" +
			 "&compltfinalApmt="+compltfinalApmt+"&dnamaintenanceApmt="+dnamaintenanceApmt+"&compltmaintenanceApmt="+compltmaintenanceApmt+"";
			 
		    	if (window.XMLHttpRequest) {
		    			req = new XMLHttpRequest();
		    		}
		    		else if (window.ActiveXObject) {
		    			isIE = true;
		    			req = new ActiveXObject("Microsoft.XMLHTTP");
		    		}   
		    	               
		    	    req.onreadystatechange = saveTpDetailsRequest;
		    	    req.open("GET", url, true); 
		    	              
		    	    req.send(null);
		    	    return true;
		 }
		 else{
			 return false;
		 }
			 
			 
		 
}	
function saveTpDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("typeName").innerHTML = req.responseText;
			$("#typeName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			tempAlert("Third Party Details added successfully.",5000);
			$('#thirdPartyDetailsPopup1').modal( "hide" );

	     
	     }
	}
	}

function addNewDoctorSurgery(){
	$('#doctorSurgeryPopup').modal( "show" );

}

function saveSurgeryDetails(){
	//get all values
	var type = document.getElementById('dstype').value;
	var dsname = document.getElementById('dsname').value;
	var telephoneLine = document.getElementById('dstelephoneLine').value;
	var compnyEmail = document.getElementById('dscompnyEmail').value;
	var address = document.getElementById('dsaddress').value;
	var town = document.getElementById('dstown').value;
	
	var postcode = document.getElementById('dspostcode').value;
	var country = document.getElementById('dscountry').value;
	var gpname = document.getElementById('dsgpname').value;
	var workphno = document.getElementById('dsworkphno').value;
	var gpemailid = document.getElementById('dsgpemailid').value;
	var gpfax = document.getElementById('dsgpfax').value;
	var notes = document.getElementById('dsnotes').value;
	
	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#dstype", "FunctionName": "dropDown" },
                    { "Element": "#dsname", "FunctionName": "notEmpty" },
                    { "Element": "#dstelephoneLine", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dscompnyEmail", "FunctionName": "emailIdOptional" },
                    { "Element": "#dsgpname", "FunctionName": "notEmpty" },
                    { "Element": "#dsworkphno", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dsgpemailid", "FunctionName": "emailIdOptional" }

            ]
        };
	 if(iterateThroughtElemsForValidations(validatePromoJSON))
	 {
		 var url = "saveDoctorSurgeryAjaxThirdParty?type="+type+"&dsname="+dsname+"&telephoneLine="+telephoneLine+"&compnyEmail="+compnyEmail+"" +
		 "&address="+address+"&town="+town+"&postcode="+postcode+"&country="+country+"&gpname="+gpname+"&workphno="+workphno+"" +
		 "&gpemailid="+gpemailid+"&gpfax="+gpfax+"&notes="+notes+"";
		 
	    	if (window.XMLHttpRequest) {
	    			req = new XMLHttpRequest();
	    		}
	    		else if (window.ActiveXObject) {
	    			isIE = true;
	    			req = new ActiveXObject("Microsoft.XMLHTTP");
	    		}   
	    	               
	    	    req.onreadystatechange = saveSurgeryDetailsRequest;
	    	    req.open("GET", url, true); 
	    	              
	    	    req.send(null);
	    	    return true;
	 }
	 else{
		 return false;
	 }
	
}
function saveSurgeryDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data =req.responseText;
			var temp = data.split("*");
			//alert(temp[0]);
			//alert(temp[1]);
		
			document.getElementById("gptypeName").innerHTML = temp[0];
			$("#gptypeName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			document.getElementById("gpname").innerHTML = temp[1];
			$("#gpname").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			tempAlert("Doctor surgery added successfully.",5000);
			$('#doctorSurgeryPopup').modal( "hide" );

	     
	     }
	}
	}
function saveNewPopupSurgeryDetails(){
	//get all values
	var type = document.getElementById('dstype').value;
	var dsname = document.getElementById('dsname').value;
	var telephoneLine = document.getElementById('dstelephoneLine').value;
	var compnyEmail = document.getElementById('dscompnyEmail').value;
	var address = document.getElementById('dsaddress').value;
	var town = document.getElementById('dstown').value;
	
	var postcode = document.getElementById('dspostcode').value;
	var country = document.getElementById('dscountry').value;
	var gpname = document.getElementById('dsgpname').value;
	var workphno = document.getElementById('dsworkphno').value;
	var gpemailid = document.getElementById('dsgpemailid').value;
	var gpfax = document.getElementById('dsgpfax').value;
	var notes = document.getElementById('dsnotes').value;
	
	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#dstype", "FunctionName": "dropDown" },
                    { "Element": "#dsname", "FunctionName": "notEmpty" },
                    { "Element": "#dstelephoneLine", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dscompnyEmail", "FunctionName": "emailIdOptional" },
                    { "Element": "#dsgpname", "FunctionName": "notEmpty" },
                    { "Element": "#dsworkphno", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dsgpemailid", "FunctionName": "emailIdOptional" }

            ]
        };
	 if(iterateThroughtElemsForValidations(validatePromoJSON))
	 {
		 var url = "saveDoctorSurgeryAjaxThirdParty?type="+type+"&dsname="+dsname+"&telephoneLine="+telephoneLine+"&compnyEmail="+compnyEmail+"" +
		 "&address="+address+"&town="+town+"&postcode="+postcode+"&country="+country+"&gpname="+gpname+"&workphno="+workphno+"" +
		 "&gpemailid="+gpemailid+"&gpfax="+gpfax+"&notes="+notes+"";
		 
	    	if (window.XMLHttpRequest) {
	    			req = new XMLHttpRequest();
	    		}
	    		else if (window.ActiveXObject) {
	    			isIE = true;
	    			req = new ActiveXObject("Microsoft.XMLHTTP");
	    		}   
	    	               
	    	    req.onreadystatechange = saveNewPopupSurgeryDetailsRequest;
	    	    req.open("GET", url, true); 
	    	              
	    	    req.send(null);
	    	    return true;
	 }
	 else{
		 return false;
	 }
	
}
function saveNewPopupSurgeryDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data =req.responseText;
			var temp = data.split("*");
			//alert(temp[0]);
			//alert(temp[1]);
		
			document.getElementById("doctorsurgery").innerHTML = temp[0];
			$("#doctorsurgery").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			document.getElementById("gpname").innerHTML = temp[1];
			$("#gpname").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			tempAlert("Doctor surgery added successfully.",5000);
			$('#doctorSurgeryPopup').modal( "hide" );

	     
	     }
	}
}


function getStateAjaxnew2(val) {
    var url="getstateajaxnewInventory?city="+val+"";
 
    if (window.XMLHttpRequest) {
 req = new XMLHttpRequest();
 }
 else if (window.ActiveXObject) {
 isIE = true;
 req = new ActiveXObject("Microsoft.XMLHTTP");
 }   
              
    req.onreadystatechange = getStateAjaxnewRequest2;
    req.open("GET", url, true); 
             
    req.send(null);   

}

function getStateAjaxnewRequest2(){
   if (req.readyState == 4) {
 if (req.status == 200) {
	 var data=req.responseText;

//	 data=data.replace("id='state'","id='state123'");
//    document.getElementById("statediv2").innerHTML=data;
	 document.getElementById("state123").className="";
	 document.getElementById("state123").value=data;
	 $("#state123").trigger("chosen:updated");
		$(".chosen-select").chosen({allow_single_deselect: true});
        }
}  
}


function getCitiesajax2(val) {
    var url="getcitiesajaxInventory?state="+val+"";
 
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getCitiesajaxRequest2;
    req.open("GET", url, true); 
             
    req.send(null);   

}

function getCitiesajaxRequest2(){
   if (req.readyState == 4) {
		if (req.status == 200) {
			 var data=req.responseText;
			 data=data.replace("id='town'","id='city123'");
		   document.getElementById("citydiv2").innerHTML=data;
		    
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
		    $("#town").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
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
		    $("#state").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
			
			/*if(document.getElementById("country_chosen")){
				document.getElementById("country_chosen").style.marginBottom  ="10px";
			}*/
			
         }
	}	 
}

function setGender(val){
 if(val=='Mr.' || val=='Babyboyof' || val=='Master'){
  document.getElementById("gender").value = 'Male';
 }else if(val=='Mrs.' || val=='Miss.' || val=='BabyGirlof' || val=='Baby'){
  document.getElementById("gender").value = 'Female';
 }
}


function getAgendDays2(dob) {

    var url="getageClient?dob="+dob+"";

    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getAgendDaysRequest2;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getf(val,id){
	document.getElementById(id).value=val;
	var temp=val.split('\\');
	document.getElementById(id+"1").innerHTML=temp[2];
	if(id=='docimg'){
		if(document.getElementById("hiddenpuresevareg")){
			document.getElementById("hiddenpuresevareg").className='';
		}
	}
}

function getAgendDaysRequest2(){
   if (req.readyState == 4) {
		if (req.status == 200) {
			 var str= req.responseText;
			 document.getElementById('dobe').innerHTML=str;
		     var data= str.split("~");
		     
		     var year= parseInt(data[0]);
		     var months=parseInt(data[1]);
		     var days = data[2];
		     
		    var flagforvalidatedob =false;
		 	if(year==5){
		 		if(months==0 && days==0){
		 			flagforvalidatedob =true;
		 		}
		 	}else if(year<5){
		 		flagforvalidatedob =true;
		 	}
		 	
		 	var flagforvalidatedob1 =false;
		 	if(year==16){
		 		if(months==0 && days==0){
		 			flagforvalidatedob1 =true;
		 		}
		 	}else if(year<16){
		 		flagforvalidatedob1 =true;
		 	}
		    
		 	if(flagforvalidatedob){
		 		document.getElementById('dobe').innerHTML=year+'yr '+months+' months '+data[2]+' days'+'(Minor)' ;
		 		if(document.getElementById("pureage1")){
		 			document.getElementById("pureage1").value= year;
		 		}
		 	}else{
		 		if(flagforvalidatedob1){
		 			document.getElementById('dobe').innerHTML=year+'yr'+'(Minor)' ;
		 		}else{
		 			document.getElementById('dobe').innerHTML=year+'yr'+'(Major)' ;
		 		}
		 		
		 		if(document.getElementById("pureage1")){
		 			document.getElementById("pureage1").value= year;
		 		}
		 	}
		 	if(document.getElementById('puresevadobyear')){
		    	document.getElementById('puresevadobyear').value = year;
		    }
		    if(document.getElementById('puresevadobmonth')){
		    	document.getElementById('puresevadobmonth').value = months;
		    }
		    if(document.getElementById('puresevadobdays')){
		    	document.getElementById('puresevadobdays').value = data[2];
		    }
		    if(year<=5){
		    	if(document.getElementById("registrationguide1")){
					document.getElementById("registrationguide1").className='hidden';
		    	}
		    	if(document.getElementById("registrationguide2")){
					document.getElementById("registrationguide2").className='hidden';
		    	}
		    	if(document.getElementById("registrationguide3")){
					document.getElementById("registrationguide3").className='';
		    	}
		    	if(document.getElementById("relativedetailssdiv")){
					document.getElementById("relativedetailssdiv").className='col-lg-12';
		    	}
		    	if(document.getElementById("relativedetailsdocdiv")){
					document.getElementById("relativedetailsdocdiv").className='col-lg-12';
		    	}
		    	if(document.getElementById("relativeidentiyspan")){
					document.getElementById("relativeidentiyspan").innerHTML='*';
		    	}
		    	if(document.getElementById("patientidentiyspan")){
					document.getElementById("patientidentiyspan").innerHTML='';
		    	}
		    	if(document.getElementById("parentguardiannamespan")){
					document.getElementById("parentguardiannamespan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardiancontactspan")){
					document.getElementById("parentguardiancontactspan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardianrelationspan")){
					document.getElementById("parentguardianrelationspan").innerHTML='*';
		    	}
		    }else if(year>=5 && year<=16){
		    	if(document.getElementById("registrationguide1")){
					document.getElementById("registrationguide1").className='hidden';
		    	}
		    	if(document.getElementById("registrationguide2")){
					document.getElementById("registrationguide2").className='';
		    	}
		    	if(document.getElementById("registrationguide3")){
					document.getElementById("registrationguide3").className='hidden';
		    	}
		    	if(document.getElementById("relativedetailssdiv")){
					document.getElementById("relativedetailssdiv").className='col-lg-12';
		    	}
		    	if(document.getElementById("relativedetailsdocdiv")){
					document.getElementById("relativedetailsdocdiv").className='col-lg-12';
		    	}
		    	
		    	if(document.getElementById("relativeidentiyspan")){
					document.getElementById("relativeidentiyspan").innerHTML='*';
		    	}
		    	if(document.getElementById("patientidentiyspan")){
					document.getElementById("patientidentiyspan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardiannamespan")){
					document.getElementById("parentguardiannamespan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardiancontactspan")){
					document.getElementById("parentguardiancontactspan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardianrelationspan")){
					document.getElementById("parentguardianrelationspan").innerHTML='*';
		    	}
		    }else{
		    	if(document.getElementById("registrationguide1")){
					document.getElementById("registrationguide1").className='';
		    	}
		    	if(document.getElementById("registrationguide2")){
					document.getElementById("registrationguide2").className='hidden';
		    	}
		    	if(document.getElementById("registrationguide3")){
					document.getElementById("registrationguide3").className='hidden';
		    	}
		    	
		    	if(document.getElementById("relativedetailssdiv")){
					document.getElementById("relativedetailssdiv").className='col-lg-12 hidden';
		    	}
		    	if(document.getElementById("relativedetailsdocdiv")){
					document.getElementById("relativedetailsdocdiv").className='col-lg-12 hidden';
		    	}
		    	
		    	if(document.getElementById("relativeidentiyspan")){
					document.getElementById("relativeidentiyspan").innerHTML='';
		    	}
		    	if(document.getElementById("patientidentiyspan")){
					document.getElementById("patientidentiyspan").innerHTML='*';
		    	}
		    	if(document.getElementById("parentguardiannamespan")){
					document.getElementById("parentguardiannamespan").innerHTML='';
		    	}
		    	if(document.getElementById("parentguardiancontactspan")){
					document.getElementById("parentguardiancontactspan").innerHTML='';
		    	}
		    	if(document.getElementById("parentguardianrelationspan")){
					document.getElementById("parentguardianrelationspan").innerHTML='';
		    	}
		    }
		     
		}
   }
} 

function getAgendDays(dob) {

     var url="getageClient?dob="+dob+"";

     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getAgendDaysRequest;
     req.open("GET", url, true); 
              
     req.send(null);      
         
     
} 

function getAgendDaysRequest(){
    if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		     var data= str.split("~");
		     document.getElementById("age").value=data[0];
		     document.getElementById("days").value=data[1];
		     var year= parseInt(data[0]);
		     var days=parseInt(data[1]);
		     if(year==0&&days<8){
		    	 $('#babyconfirm').modal('show');
		    	/* if(confirm("Is This Baby Born in Hospital")){
		    		 alert("Baby is born inj Hosp")
		    	 }*/
		     }
         }
	}	 
}

function sethospitalborn(){
	document.getElementById("hospitalborn").checked = true;
	 $('#babyconfirm').modal('toggle');
}
function closehospitalborn(){
	document.getElementById("hospitalborn").checked = false;
	 $('#babyconfirm').modal('toggle');
}


function getAgendDays(dob) {

    var url="getageClient?dob="+dob+"";

    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getAgendDaysRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getAgendDaysRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		     var data= str.split("~");
		     document.getElementById("age").value=data[0];
		     document.getElementById("month").value=data[1];
		     document.getElementById("days").value=data[2];
		     /* var year= parseInt(data[0]);
		     var days=parseInt(data[1]);
		     if(year==0&&days<8){
		    	 $('#babyconfirm').modal('show');
		    	/* if(confirm("Is This Baby Born in Hospital")){
		    		 alert("Baby is born inj Hosp")
		    	 }}*/
		      
        }
	}	 
}
function allnumeric(dd)  
{  
   var numbers = /^[0-9]+$/;  
   if(!dd.match(numbers))  
   {  
  	alert('only number permitted...');  
   }  
   else 
   {
         var today= new Date();
         var yyyy = today.getFullYear();
         var year= parseInt(yyyy)- parseInt(dd);
         var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
         
         if(dd<10) {
 			dd='0'+dd
			} 

			if(mm<10) {
 			mm='0'+mm
			} 

			var str = dd+'/'+mm+'/'+year;
			document.getElementById("dob").value=str;
   		document.getElementById("days").value=0;
   }
   
}   



/*function showAge(dobYear, dobMonth, dobDay) {
	var bthDate, curDate, days;
	var ageYears, ageMonths, ageDays;
	bthDate = new Date(dobYear, dobMonth-1, dobDay);
	curDate = new Date();
	if (bthDate>curDate) return;
	days = Math.floor((curDate-bthDate)/(1000*60*60*24));
	ageYears = Math.floor(days/365);
	ageMonths = Math.floor((days%365)/31);
	ageDays = days - (ageYears*365) - (ageMonths*31);
	if (ageYears>0) {
		document.write(ageYears+" year");
		if (ageYears>1) document.write("s"); 
		if ((ageMonths>0)||(ageDays>0)) document.write(", ");
	}
	if (ageMonths>0) {
		document.write(ageMonths+" month");
		if (ageMonths>1) document.write("s");
		if (ageDays>0) document.write(", ");
	}
	if (ageDays>0) {
		document.write(ageDays+" day");
		if (ageDays>1) document.write("s");
	}
}*/

function agecalculate()  
{  
//   var numbers = /^[0-9]+$/;  
//   if(!dd.match(numbers))  
//   {  
//  	alert('only number permitted...');  
//   }  
//   else 
//   {
	 var age=document.getElementById("age").value;
	 if(age==''){
		 age=0;
	 }
	 var month=document.getElementById("month").value;
	 if(month==''){
		 month=0;
	 }
	 var days=document.getElementById("days").value;
	 var today= new Date();
     var yyyy = today.getFullYear();
     var currentmonth=today.getMonth()+1;
     var currentdays=today.getDate();
     var year= parseInt(yyyy)- parseInt(age);
     var res=0;
     if(month<currentmonth){
    	var diffmonth= parseInt(currentmonth)- parseInt(month);
    	if(currentdays<10) {
    		currentdays='0'+currentdays
   			} 

   			if(diffmonth<10) {
   				diffmonth='0'+diffmonth
   			} 
    	 var str = currentdays+'/'+diffmonth+'/'+year;
     }else{
     
     
	 if(month>0){
	 
		 for (var i = 1; i<=month; i++) {
			 var monthdays= new Date(year, i, 0).getDate();
			  res=res+monthdays;
		 }
	 }
	 
	 if(age>0){
		var agedays=age*365;
	 }else{
		 var agedays=0;
	 }
	 var abc=agedays+res;
	 var date1 = new Date();
	 date1.setDate(date1.getDate() - abc);	
	 var year1=date1.getFullYear();
	 var month1=date1.getMonth()+1;
	 var days1=date1.getDate();
	 
	 if(days1<10) {
		 days1='0'+days1
			} 

			if(month1<10) {
				month1='0'+month1
			} 
     
     
	 var str = days1+'/'+month1+'/'+year1;
     }
     
			document.getElementById("dob").value=str;
   		document.getElementById("days").value=0;
}   

function allnumeric5(dd)  {  
	   var numbers = /^[0-9]+$/;  
	   if(!dd.match(numbers))  {  
		   alert('only number permitted...');  
	   }  else {
	         var today= new Date();
	         var yyyy = today.getFullYear();
	         var year= parseInt(yyyy)- parseInt(dd);
	         var dd = today.getDate();
			 var mm = today.getMonth()+1; //January is 0!
	         
	         if(dd<10) {
	 			dd='0'+dd
	         } 

	         if(mm<10) {
	        	 mm='0'+mm
	         } 

	        var str = dd+'/'+mm+'/'+year;
	        var months = mm;
	        var days = dd;
	        document.getElementById("puredob").value=str;
	        getAgendDays2(str);
	  }
	   
} 



function addbirthplacePopup(){
	$('#bpmodal').modal('show');
}


function saveBirthplace(){
	var nme= document.getElementById("bptext").value;
	 var url="addBirthPlaceListCommonnew?name="+nme+"";
	if(nme==''){
		alert('Cant be blank')
	}else{
		if(confirm("Are you sure?")){
		 	if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
		    req.onreadystatechange =saveBirthplaceRequest;
		    req.open("GET", url, true); 
		    req.send(null);

		}
	}	    
}

function saveBirthplaceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     alert('saved');
		     document.getElementById("bptext").value="";
		     $('#bpmodal').modal('hide');
		     
		     getBirthplaceList();
		}
	}  
} 



function getBirthplaceList(){
	var nme= document.getElementById("bptext").value;
	 var url="getBirthPlaceListCommonnew";

	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	              
	    req.onreadystatechange =getBirthplaceListRequest;
	    req.open("GET", url, true); 
	             
	    req.send(null);      
	        
	    
}

function getBirthplaceListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		     
			document.getElementById("bpldiv").innerHTML=req.responseText;
			$("#birthplace").trigger("chosen:updated");
		  	 $(".chosen-select").chosen({allow_single_deselect: true});
		}
	}  
} 


function getpatientfordatalist(val,divid,colmnname){
	if(val!=''){
	  var dataObj={
			  	"val" : "" + val + "",
			    "colmnname" : "" + colmnname + "",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getpatientfordatalistBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   document.getElementById(divid).innerHTML=data.datalistdata;
		},
	   error : function(result) {
		   jAlert('error', "Something goes wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }

	  });
	}
}

function testtingpatient() {
	 document.getElementById("pdl_firstname").innerHTML="";
}

function changepatientdisctype() {
	document.getElementById("netamount").value=0;
	document.getElementById("discountvalue").value=0;
}
function calculatepatientafterDiscount() {
	var requestdisctype = "1";
	if(document.getElementById("discounttype")){
		requestdisctype = document.getElementById("discounttype").value
	}
	var requestdiscinvoiceamt =0;
	var registrationcharge = parseFloat(document.getElementById("registrationcharge").value);
	var consultationcharge = 0.0;
	if(document.getElementById("consultationcharge")){
		consultationcharge = parseFloat(document.getElementById("consultationcharge").value)
	}
	
	var othercharge = 0.0;
	if(document.getElementById("othercharge")){
		othercharge = parseFloat(document.getElementById("othercharge").value)
	}
	//var othercharge = parseFloat(document.getElementById("othercharge").value);
	requestdiscinvoiceamt = registrationcharge + consultationcharge + othercharge;
	var val =0;
	if(document.getElementById("discountvalue")){
		val = parseFloat(document.getElementById("discountvalue").value); 
	}
	var discountamount = val;
	if(requestdisctype==0){
		discountamount = (requestdiscinvoiceamt * val)/100;
	}
	var afterdiscountinvoiceamount = requestdiscinvoiceamt - discountamount;
	document.getElementById("netamount").value= afterdiscountinvoiceamount;
}

function serachpatientbyUHID() {
	var searchText = document.getElementById("searchText").value;
	if(searchText==''){
		jAlert('error', "Please enter UHID!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var dataObj={
			  	"searchText" : "" + searchText + "",
		};
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "validateuhidPharmacyAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var validid=data.datalistdata;
		   if(validid==0){
			   jAlert('error', "Please enter valid UHID!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }else{
			   document.getElementById("selectedid").value=validid;
			   document.getElementById('editclientfrm').submit();
		   }
		},
	   error : function(result) {
		   jAlert('error', "Something goes wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }

	  });
	}
}
function hidevaldiv(val){
	if(val==1){
		document.getElementById("departmentdiv").style.display="none";
		document.getElementById("paymentdiv").style.display="none";
		document.getElementById("savebtn").disabled=false;
	}else{
		document.getElementById("departmentdiv").style.display="";
		document.getElementById("paymentdiv").style.display="";
		document.getElementById("savebtn").disabled=true;
	}
}



function addPatientPayerType(val){
	var patientType=1;
	if(val=='Self' || val=='Client' || val=='0'){
		patientType =0;
	}
	
	var url = "getPayerTypeListDataBookAppointmentAjax?id="+patientType+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = addPatientPayerTypeRequest;
	req.open("GET", url, true); 
	req.send(null);

}    
function addPatientPayerTypeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("typepayerdiv").innerHTML = req.responseText;
    		$("#type").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
			setTPName(0);
	    }
	}
}

function setRegistrationCharge(val){
	
	var id =0;
	if(document.getElementById("id")){
		id = document.getElementById("id").value;
	}
	
	var dataObj={
		"thirdPartyId":""+val+"",
		"id":""+id+"",
	}
	var data1 = JSON.stringify(dataObj);
	$.ajax({
		url : "getRegistrationChargeBookAppointmentAjax",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		success : function(responseData){
			var registrationAmount = responseData.registrationcharge;
			document.getElementById("registrationcharge").value=registrationAmount;
			if(responseData.paymentType!=''){
				document.getElementById("paymenttype").className ="";
				document.getElementById("paymenttype").value = responseData.paymentType;
				document.getElementById("paymenttype").className ="form-control showToolTip chosen-select ajsolidborder";
				$("#paymenttype").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true}); 
			}
			if(responseData.enrollcode==1){
//				document.getElementById('addenrollcode').style.display = '';
				document.getElementById('addenrollcode').className = "";
			}else{
				document.getElementById('addenrollcode').className = "hidden";
			}
			if(val=='68'){//@ rahul we are adding this beacuse for payer dept camp text box show in camp area
				if(responseData.campArea==1){
				document.getElementById("isCampArea").value=1;
				$('#campareaId1').show();
			}else{
				document.getElementById("isCampArea").value=0;
				$('#campareaId1').hide();
			}
		}else{//end here @ rahul we are adding this beacuse for payer dept camp text box show in camp area
			if(responseData.campArea==1){
				document.getElementById("isCampArea").value=1;
				$('#campareaId').show();
			}else{
				document.getElementById("isCampArea").value=0;
				$('#campareaId').hide();
			}
		 }	
			calculatepatientafterDiscount();
			
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	});
	
	// code for physio
	document.getElementById("plan1").disabled="";
	
}


function getChargeAjax(val) {
    var url="getchargeajaxInventory?planid="+val+"";
 
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getchargeajaxRequest;
    req.open("GET", url, true); 
             
    req.send(null);   

}
function getchargeajaxRequest(){
    if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("chargediv").innerHTML=req.responseText;
		    $("#charge").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
			
			/*if(document.getElementById("country_chosen")){
				document.getElementById("country_chosen").style.marginBottom  ="10px";
			}*/
			
         }
	}	 
}









function setRegistrationChargePhysioplan(val,planid,activeplanid){
	document.getElementById("savebtn").disabled=false;
	var thirdPartyId=document.getElementById("typeName").value;
	//document.getElementById("registrationcharge").value=val;
	var id =0;
	if(document.getElementById("id")){
		id = document.getElementById("id").value;
	}
	
	var dataObj={
		"charges":""+val+"",
		"planid":""+planid+"",
		"activeplanid":""+activeplanid+"",
		"id":""+id+"",
		"thirdPartyId":""+thirdPartyId+"",
	}
	var data1 = JSON.stringify(dataObj);
	$.ajax({
		url : "getRegistrationPhysioChargeBookAppointmentAjax",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		success : function(responseData){
			var registrationAmount = responseData.registrationcharge;
			document.getElementById("registrationcharge").value=registrationAmount;
			if(responseData.paymentType!=''){
				document.getElementById("paymenttype").className ="";
				document.getElementById("paymenttype").value = responseData.paymentType;
				document.getElementById("paymenttype").className ="form-control showToolTip chosen-select ajsolidborder";
				$("#paymenttype").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true}); 
			}
			if(responseData.enrollcode==1){
//				document.getElementById('addenrollcode').style.display = '';
				document.getElementById('addenrollcode').className = "";
			}else{
				document.getElementById('addenrollcode').className = "hidden";
			}
			
			if(responseData.campArea==1){
				document.getElementById("isCampArea").value=1;
				$('#campareaId').show();
			}else{
				document.getElementById("isCampArea").value=0;
				$('#campareaId').hide();
			}
			calculatepatientafterDiscount();
			
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	});

	
}

function setRegiCharge(val){
	
	var id =0;
	if(document.getElementById("id")){
		id = document.getElementById("id").value;
	}
	
	var dataObj={
		"regdate":""+val+"",
		"id":""+id+"",
	}
	var data1 = JSON.stringify(dataObj);
	$.ajax({
		url : "setregichargeajaxBookAppointmentAjax",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		success : function(responseData){
			var registrationAmount = responseData.registrationcharge;
			document.getElementById("registrationcharge").value=registrationAmount;
			if(responseData.paymentType!=''){
				document.getElementById("paymenttype").className ="";
				document.getElementById("paymenttype").value = responseData.paymentType;
				document.getElementById("paymenttype").className ="form-control showToolTip chosen-select ajsolidborder";
				$("#paymenttype").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true}); 
			}
			if(responseData.enrollcode==1){
//				document.getElementById('addenrollcode').style.display = '';
				document.getElementById('addenrollcode').className = "";
			}else{
				document.getElementById('addenrollcode').className = "hidden";
			}
			
			if(responseData.campArea==1){
				document.getElementById("isCampArea").value=1;
				$('#campareaId').show();
			}else{
				document.getElementById("isCampArea").value=0;
				$('#campareaId').hide();
			}
			calculatepatientafterDiscount();
			
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	});

	
}
