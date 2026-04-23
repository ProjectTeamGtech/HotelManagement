/**
 * 28-10-2021
 * Payer Creation for VSPM
 */

function openNonDiscountablePopup() {
	$('#nonDiscoutablePopup').modal('show');
}

function openDiscountablePopup() {
	$('#discoutablePopup').modal('show');
}

function openPayerType(){
	var url = "openpayertypeBookAppointmentAjax";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = openPayerTypeRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function openPayerTypeRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			document.getElementById('payerlist').innerHTML=data;
			/*document.getElementById('addnewdiv').className="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden";
			document.getElementById("pt_patientType").value="";
			document.getElementById("pt_payerType").value="";*/
			$('#payergrouptype').modal('show');
			
		}
	}
}
function addNewHidden() {
	document.getElementById("pt_patientType").value="";
	document.getElementById("pt_payerType").value="";
	$('#addnewdiv').modal('show');
}

function validateNewPayerType(){
	var patientType = document.getElementById("pt_patientType").value; 
	var pt_payerType = document.getElementById("pt_payerType").value;
	
	var regex = /^[a-zA-Z\s]+$/;
	
	if(patientType==''){
		 jAlert('error', "Please Select Patient Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else if(pt_payerType==''){
		jAlert('error', "Please Enter Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else if(!pt_payerType.match(regex)){
		jAlert('error', "Please Enter Valid Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else{
		var url = "validatepayertypeBookAppointmentAjax?patientType="+patientType+"&payerType="+pt_payerType+"";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = validateNewPayerTypeRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
}
function validateNewPayerTypeRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			if(data==3){
				 jAlert('error', "Payer Type Already Available!", 'Error Dialog');
				 setTimeout(function() {
				 $("#popup_container").remove();
				 removeAlertCss();
				 }, alertmsgduration);
				 //location.reload()
				 openPayerType();
			}else{
				jAlert('success', "Payer Type added!", 'Error Dialog');
				 setTimeout(function() {
				 $("#popup_container").remove();
				 removeAlertCss();
				 }, alertmsgduration);
				 $('#addnewdiv').modal('hide');
				 //$('#payergrouptype').modal('hide');
				 openPayerType();
			}
			
		}
	}
}

function setPayerType_NDS(val){
	var url = "getPayerTypeListDataThirdParty?id="+val+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	               
	req.onreadystatechange = setPayerType_NDSRequest;
	req.open("GET", url, true); 
	req.send(null);

}    
function setPayerType_NDSRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("typepayerdiv").innerHTML = req.responseText;
    		$("#type1").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
			document.getElementById("nds_tbody").innerHTML= "";
			document.getElementById("nds_service_tbody").innerHTML= "";
			document.getElementById("ndsId").value= 0;
			setTypeName(0);
	    }
	}
}

function setTypeName(id){
	var url = "setTypeNameDropDownPharmacyAjax?id="+id+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = setTypeNameRequest;
	req.open("GET", url, true); 
	req.send(null);
}    
function setTypeNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	
	    	document.getElementById("typeName1").innerHTML = req.responseText;
	    	$("#typeName1").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById("nds_tbody").innerHTML= "";
			document.getElementById("nds_service_tbody").innerHTML= "";
			document.getElementById("ndsId").value= 0;
		}
	}
}

function displayChargesData() {
	
	var nds_department="";
    var x=document.getElementById("nds_department");
    for (var i = 0; i < x.options.length; i++) {
    	if(x.options[i].selected ==true){
    		if(nds_department==''){
    			nds_department = "'"+x.options[i].value+"'";
    		}else{
    			nds_department = nds_department+','+ "'"+x.options[i].value+"'";
    		}
        }
    }
    if(fromchangepayer==1 && nds_department==''){
    	//fromchangepayer =0;
    	nds_department ="00";
    }
    var nds_payerType = document.getElementById("nds_payerType").value;
    var type1 = document.getElementById("type1").value;
    var typeName1 = document.getElementById("typeName1").value;
    if(nds_payerType==''){
    	 jAlert('error', "Please select Patient Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
    }else if(type1=='0'){
    	 jAlert('error', "Please select Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
    }else if(typeName1=='0'){
    	 jAlert('error', "Please select Payer!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
    }else if(nds_department==''){
		 jAlert('error', "Please select Department!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
		 document.getElementById("nds_tbody").innerHTML= "";
		 document.getElementById("nds_service_tbody").innerHTML= "";
		 document.getElementById("ndsId").value= 0;
	}else{
		if(nds_department=='00'){
			nds_department ="";
		}
		var dataObj={
			"department":""+nds_department+"",
			"patientType":""+nds_payerType+"",
			"payerType":""+type1+"",
			"payer":""+typeName1+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getNDSChargesPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			  var servicelist= data.val1;
			  document.getElementById("nds_tbody").innerHTML= servicelist;
			  
			  var chargeslist= data.val2;
			  document.getElementById("nds_service_tbody").innerHTML= chargeslist;
			  
			  var ndsId= data.ndsId;
			  document.getElementById("ndsId").value= ndsId;
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		});
	}	
}

function selectNDSCheckBox(val){
	if (val== true) {
		$('.nds_services').each(function() { 
			this.checked = true; 	
		});
	} else {
		$('.nds_services').each(function() { 
			this.checked = false; 
		});
	}
}

function saveNDS(){
	var ids='';
	$('.nds_services').each(function() {
		if(this.checked == true){
			if(ids==''){
				ids = this.value;
			}else{
				ids = ids+','+this.value;
			}
		}
	});
	var nds_payerType = document.getElementById("nds_payerType").value;
    var type1 = document.getElementById("type1").value;
    var typeName1 = document.getElementById("typeName1").value;
    var nds_department="";
    var ndsId = document.getElementById("ndsId").value;
    var x=document.getElementById("nds_department");
    for (var i = 0; i < x.options.length; i++) {
    	if(x.options[i].selected ==true){
    		if(nds_department==''){
    			nds_department = "'"+x.options[i].value+"'";
    		}else{
    			nds_department = nds_department+','+ "'"+x.options[i].value+"'";
    		}
        }
    }
    
   if(nds_payerType==''){
   	 jAlert('error', "Please select Patient Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else if(type1=='0'){
   	 jAlert('error', "Please select Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else if(typeName1=='0'){
   	 jAlert('error', "Please select Payer!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else if(nds_department==''){
		 jAlert('error', "Please select Department!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else if(ids==''){
		jAlert('error', "Please select at least one Service/Charge!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else{
		var previousIds='';
		$('.nds_charges').each(function() {
			if(previousIds==''){
				previousIds = this.value;
			}else{
				previousIds = previousIds+','+this.value;
			}
		});
		if(previousIds==''){
			ids = '0,'+ids;
		}else{
			ids = '0,'+ids+','+previousIds;
		}
		
		var dataObj={
			"department":""+nds_department+"",
			"patientType":""+nds_payerType+"",
			"payerType":""+type1+"",
			"payer":""+typeName1+"",
			"ids":""+ids+"",
			"ndsId":""+ndsId+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "saveNDSchargesPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   var val= data.val1;
			   if(val==1){
				   jAlert('success', "Saved successfully", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
				   }, alertmsgduration);
			   }else{
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			   displayChargesData();
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		});
	}
}
var fromchangepayer=0;
function changeNDSDepartment(){
	document.getElementById("nds_tbody").innerHTML= "";
	document.getElementById("nds_service_tbody").innerHTML= "";
	document.getElementById("ndsId").value= 0;
	fromchangepayer =1;
	displayChargesData();
}

function closedPopUpWindow(){
	window.location.reload();
}

function deleteNDS(id){
	
	var nds_payerType = document.getElementById("nds_payerType").value;
    var type1 = document.getElementById("type1").value;
    var typeName1 = document.getElementById("typeName1").value;
    var ndsId = document.getElementById("ndsId").value;
    
   if(nds_payerType==''){
   	 jAlert('error', "Please select Patient Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else if(type1=='0'){
   	 jAlert('error', "Please select Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else if(typeName1=='0'){
   	 jAlert('error', "Please select Payer!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
   }else{
	   var previousIds='';
		$('.nds_charges').each(function() {
			if(this.value!=id){
				if(previousIds==''){
					previousIds = this.value;
				}else{
					previousIds = previousIds+','+this.value;
				}
			}
		});
		previousIds = '0,'+previousIds;
		
		var dataObj={
			"department":""+nds_department+"",
			"patientType":""+nds_payerType+"",
			"payerType":""+type1+"",
			"payer":""+typeName1+"",
			"ids":""+previousIds+"",
			"ndsId":""+ndsId+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "saveNDSchargesPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   var val= data.val1;
			   if(val==1){
				   jAlert('success', "Saved successfully", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
				   }, alertmsgduration);
			   }else{
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			   displayChargesData();
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		});
	}
}

function editPayerType(val){
	document.getElementById("edit_pt_patientType").value=document.getElementById("patientTypeId_"+val).value;
	document.getElementById("edit_pt_payerType").value=document.getElementById("payerName_"+val).value;
	document.getElementById("edit_pt_payerID").value=val;
	$('#editpayertype').modal('show');
}

function validateUpdatePayerType(){
	var edit_Id = document.getElementById("edit_pt_payerID").value; 
	var patientType = document.getElementById("edit_pt_patientType").value;
	var pt_payerType = document.getElementById("edit_pt_payerType").value;
	
	var regex = /^[a-zA-Z\s]+$/;
	
	if(patientType==''){
		 jAlert('error', "Please Select Patient Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else if(pt_payerType==''){
		jAlert('error', "Please Enter Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else if(!pt_payerType.match(regex)){
		jAlert('error', "Please Enter Valid Payer Type!", 'Error Dialog');
		 setTimeout(function() {
		 $("#popup_container").remove();
		 removeAlertCss();
		 }, alertmsgduration);
	}else{
		var url = "validatepayertypeBookAppointmentAjax?patientType="+patientType+"&payerType="+pt_payerType+"&edit_Id="+edit_Id+"";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = validateUpdatePayerTypeRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
}
function validateUpdatePayerTypeRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			if(data==3){
				 jAlert('error', "Payer Type Already Available!", 'Error Dialog');
				 setTimeout(function() {
				 $("#popup_container").remove();
				 removeAlertCss();
				 }, alertmsgduration);
				 //location.reload()
			}else{
				jAlert('success', "Payer Type updated!", 'Error Dialog');
				 setTimeout(function() {
				 $("#popup_container").remove();
				 removeAlertCss();
				 }, alertmsgduration);
				 $('#editpayertype').modal('hide');
				 //$('#payergrouptype').modal('hide');
				 openPayerType();
			}
			
		}
	}
}

function reloadPayerPage(){
	location.reload();
}


