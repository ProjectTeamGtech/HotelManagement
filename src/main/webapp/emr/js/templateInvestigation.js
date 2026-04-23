var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;
var editAppointId = 0;


function showInvestigationPopup(){
$('#investigationpopup').modal( "show" );
}

function saveTemplateInvestigation(){

			var prepay = 0;
			var postpay = 0; 
			var otherpay = 0;
			var invstreporttype = $("#invstreporttype option:selected").text();
			if(document.getElementById("ipaymode2").checked==true){
				var prepay = 1;
			}
			if(document.getElementById("ipaymode1").checked==true){
				var postpay = 1;
			}
			if(document.getElementById("ipaymode3").checked==true){
				var otherpay = 1;
			}
			
			
			var advoice = document.getElementById("advoice").value;
			var invstautoid = document.getElementById("invstautoid").value;
			var jobtitle = document.getElementById('jobtitle').value;
			
			var english = 0;
			var regional  = 0;
			var hindi = 0;
			
			if(document.getElementById("ilanguage1").checked==true){
				var english = 1
			}
			if(document.getElementById("ilanguage2").checked==true){
				var regional = 1;
			}
			if(document.getElementById("ilanguage3").checked==true){
				var hindi = 1;
			}
			
			if(document.getElementById("templatetext").value==""){
				jAlert('error', 'Enter Template Name.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
			}else{
			$('#baselayout1loaderPopup').modal( "show" );
			
			var templatetext = document.getElementById("templatetext").value;
			
			var url = "tempsaveInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&advoice="+advoice+"&invstautoid="+invstautoid+"&invstreporttype="+invstreporttype+"&jobtitle="+jobtitle+"&templatetext="+templatetext+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveTemplateInvestigationRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		    
		    }
		}
		
		
		function saveTemplateInvestigationRequest(){
			  if (req.readyState == 4) {
			         if (req.status == 200) {    
			        	 
			        	 
			        		jAlert('success', 'Record saved successfully.', 'success Dialog');
			    			
			    			setTimeout(function() {
			    				$("#popup_container").remove();
			    				removeAlertCss();
			    			}, alertmsgduration);
			    			window.location.reload();
			    			$('#baselayout1loaderPopup').modal( "hide" );
			    			$('#investigationpopup').modal( "hide" );
			    			        	 
			        	 
			         }}
			
			
		}
		
		
		
		
		function showhideinvsttypedetails(id){
			if(document.getElementById(id).style.display==''){
				document.getElementById(id).style.display='none';
			}else{
				document.getElementById(id).style.display='';
			}
		}
		
		function showhideinvsttestdetails(id){
			if(document.getElementById(id).style.display==''){
				document.getElementById(id).style.display='none';
			}else{
				document.getElementById(id).style.display='';
			}
		}
		
		
		function showGreap(id){
	
	var clientid = document.getElementById("clientId").value;
	var fromdate = document.getElementById("fromDate").value;
	var todate = document.getElementById("toDate").value;
	
	document.getElementById("grfromDate").value = fromdate;
	document.getElementById("grtoDate").value = todate;
	if(clientid==''){
			jAlert('error', 'Select Patient.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	
	}else{
	
		var selectedinvsttype = '';
			 $('.casegraph'+id).each(function() { //loop through each checkbox
	            if(this.checked==true){
	            	selectedinvsttype = selectedinvsttype + this.value + ",";
	            }                     
	           }); 
	           document.getElementById('selectedinvsttype').value = selectedinvsttype;
	           document.getElementById('selectedtemplateid').value = id;
	           
	           document.getElementById('selectedgraphclientid').value = clientid;
	           
	        //   alert(selectedinvsttype)
	        
	        	var t = 'formtarget';

	
	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	/*oldwindow.focus();*/
	
	document.getElementById('invstgraphfrm').submit();
	}
	


	return true;
}

		function showGreap1(id){
			 
			 var clientid = document.getElementById("clientId").value;
			 var fromdate = document.getElementById("fromDate").value;
			 var todate = document.getElementById("toDate").value;
			 var tempname= document.getElementById("tempname").innerHTML;
			 
			 var temp= new Array();
			 temp= tempname.split("(");
			 /*for(a in temp){
			   temp[a] = parseInt(temp[a], 1);
			 }
			 */tempname=temp[0];
			 
			 document.getElementById("grfromDate1").value = fromdate;
			 document.getElementById("grtoDate1").value = todate;
			 if(clientid==''){
			   jAlert('error', 'Select Patient.', 'Error Dialog');
			    setTimeout(function() {
			     $("#popup_container").remove();
			     removeAlertCss();
			    }, alertmsgduration);
			 
			 }else{
			 
			  var selectedinvsttype = '';
			    $('.casegraph'+id).each(function() { //loop through each checkbox
			             if(this.checked==true){
			              selectedinvsttype = selectedinvsttype + this.value + ",";
			             }                     
			            }); 
			            document.getElementById('selectedinvsttype1').value = selectedinvsttype;
			            document.getElementById('selectedtemplateid1').value = id;
			            document.getElementById('investtemp1').value =  tempname;
			            document.getElementById('selectedgraphclientid1').value = clientid;
			            
			         //   alert(selectedinvsttype)
		/*	         
			          var t = 'formtarget';

			 
			 
			 var left = (screen.width / 2) - (700 / 2);
			 var top = (screen.height / 2) - (550 / 2);
			 var oldwindow = window.open("", t,
			   "status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
			     + ",top=" + 50);
			 
			 oldwindow.focus();
			 */
			 document.getElementById('invstgraphfrm1').submit();
			 }
			 


			 return true;
			}
		
		
function showhidepaymentinvstigationdetails(id){
				if(document.getElementById(id).style.display==''){
				document.getElementById(id).style.display='none';
				}else{
					document.getElementById(id).style.display='';
				}
		}
		

 function savecharge(id,invsparentid,index){

    var doctorid=document.getElementById("user"+index).value;
    
    var totalExpenceCheckbox=0;	
    var totalCharges=0;
 
 
	$('.case'+index+'').each(function() { // loop through each checkbox
		if (this.checked == true) {
			totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;
			var temp="charge"+this.value;
			var charge=document.getElementById(temp).value;
			totalCharges=totalCharges+','+charge;
		}
	});      
 
 	
 	if(totalExpenceCheckbox!=0){
 	   
 	     var url="createinvoiceInvestigation?invnameId="+totalExpenceCheckbox+"&charges="+totalCharges+"&doctorid="+doctorid+"&patientid="+id+"&invsparentid="+invsparentid+"";
 
   		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savechargeRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
 	    
 	} else {
 	
 	     	jAlert('error', 'please select at least one charge!.', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
 	}
    
     
 }
 
  function savechargeRequest(){
  
        if (req.readyState == 4) {
         if (req.status == 200) {     
          
                jAlert('success', 'Invoice Created!.', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					
					window.location.reload();  
              }
         }
  
  }
  
  var f=0;
  
  function selectAll(id){
  
         if(f==0){
             
             	$('.case'+id+'').each(function() { // loop through each checkbox
					this.checked = true; // deselect all checkboxes with class
				});
            f=1;
         }else {
         
              $('.case'+id+'').each(function() { // loop through each checkbox
					this.checked = false; // deselect all checkboxes with class
				});
            f=0;
         }
  }
     
  
   function createcharge(id,invsparentid,index){ 
    
       var doctorid=document.getElementById("user"+index).value;
       var totalExpenceCheckbox=0;	
  	   var totalCharges=0;
 
 
    	$('.case'+index+'').each(function() { // loop through each checkbox
	    	if (this.checked == true) {
		    	totalExpenceCheckbox = totalExpenceCheckbox + ',' + this.value;
				var temp="charge"+this.value;
				var charge=document.getElementById(temp).value;
				totalCharges=totalCharges+','+charge;
			}
		});      
 
 	
 	if(totalExpenceCheckbox!=0){
 	   
 	     var url="createchargeInvestigation?invnameId="+totalExpenceCheckbox+"&charges="+totalCharges+"&doctorid="+doctorid+"&patientid="+id+"&invsparentid="+invsparentid+"";
 
   		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savechargeRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
 	    
 	} else {
 	
 	     	jAlert('error', 'please select at least one charge!.', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
 	}     
    
   
  }  
     
 

		
