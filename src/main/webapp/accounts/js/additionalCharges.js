var chk = 0;
var isreordadd = 0;

var ipdclientname = '';
var ipdpractitionername = '';
var ipdclientid = '';
var ipdpractitionerid = '';


$(document).ready(function(){
	if(document.getElementById('clientId').value!="" || document.getElementById('clientId').value!="0"){
		var clientid = document.getElementById('clientId').value;
		setClientDetails('',clientid,'','');
	}
	
	//called when key is pressed in textbox
  $("#quantity").keypress(function (e) {
     //if the letter is not digit then display error and don't type anything
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
               return false;
    }
   });
  
	
});


function addCharge(){
	isreordadd = 1;
	var creditDebitCharge = $("input[name='creditDebitCharge']:checked").val();
	var payBuy = $("input[name='payby']:checked").val();
	
	
	var clientid = document.getElementById('clientId').value;
	var clientname = document.getElementById('client').value;
	var type = document.getElementById('apmtType').value;
	var location = document.getElementById('location').value;
	
	var payby = "";
	if(payBuy == 1){
		payby = "Third Party";
		document.getElementById("payBuy1").disabled = true;
	}
	else{
		payby = "Client";
		document.getElementById("payBuy2").disabled = true;

	}
	
	
	
	document.getElementById("hdncdclientid").value = clientid;
	document.getElementById("hdncdclient").value = clientname;
	document.getElementById("hdncdapmttype").value = type;
	document.getElementById("hdncdloc").value = location;
	document.getElementById("hdncdpayby").value = payby;
	document.getElementById("casdeskAdditional_creditDebitCharge").value = creditDebitCharge;
	
	document.getElementById("hdnciclientid").value = clientid;
	document.getElementById("hdncliclient").value = clientname;
	document.getElementById("hdnciapmttype").value = type;
	document.getElementById("hdnciloc").value = location;
	document.getElementById("hdncipayby").value = payby;
	
	
	//document.getElementById("estimateclientid").value = clientid;
	
	
	
		
	var manualTypeName = document.getElementById('manualTypeName').value;
	var manualCharge = document.getElementById('manualCharge').value;
	document.getElementById("clientError").innerHTML = "";	
	document.getElementById("apmtTypeError").innerHTML = "";
	document.getElementById("manualTypeNameError").innerHTML = "";
	document.getElementById("manualChargeError").innerHTML = "";
	document.getElementById("creditDebitChargeError").innerHTML = "";
	document.getElementById("paybyError").innerHTML = "";
	
	/*if(creditDebitCharge == "undefined"){
		var cdcharge = document.createElement("label");
		cdcharge.innerHTML = "Please Select Charge";
	    document.getElementById('creditDebitChargeError').appendChild(cdcharge);
	    chk=1;
	}
	else{
		chk = 0;
	}
	
	if(payBuy == "undefined"){
		var payBuy1 = document.createElement("label");
		payBuy1.innerHTML = "Please Select PayBy";
	    document.getElementById('paybyError').appendChild(payBuy1);
	    chk=1;
	}
	else{
		chk = 0;
	}
	*/
	if(clientid == ""){
		var clientid = document.createElement("label");
		clientid.innerHTML = "Please Select Client";
	    document.getElementById('clientError').appendChild(clientid);
	    chk=1;
	}
	else{
		chk = 0;
	}
	/*if(type == "0"){
		var type = document.createElement("label");
		type.innerHTML = "Please Select Type";
	    document.getElementById('apmtTypeError').appendChild(type);
	    chk=1;
	}
	
	else{
		chk = 0;
	}*/
	
	var type = document.getElementById('apmtType').value;
	if(type == 0){
		if(manualTypeName == ""){
			var manualTypeName = document.createElement("label");
			manualTypeName.innerHTML = "Enter Manual Type Name";
		    document.getElementById('manualTypeNameError').appendChild(manualTypeName);
		    chk=1;
		}
		else{
			chk = 0;
		}
		if(manualCharge == ""){
			var manualCharge = document.createElement("label");
			manualCharge.innerHTML = "Enter Manual Charge";
		    document.getElementById('manualChargeError').appendChild(manualCharge);
		    chk=1;
		}
		else{
			chk = 0;
		}
	}
	if(chk == 1){
		return false;
	}
	else{
	var url = "saveChargeAdditional?clientid="+clientid+"&clientname="+clientname+"&type="+type+"&manualTypeName="+manualTypeName+"&manualCharge="+manualCharge+"&creditDebitCharge="+creditDebitCharge+"&payBuy="+payBuy+"";


	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addChargeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}


	}

	function addChargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("chargeTotalajax").innerHTML = req.responseText;
					setCashDesk();		
				
				
				
			}
		}
	}

	function setCashDesk(){
		var clientid = document.getElementById('clientId').value;
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		var url = "cashDeskAdditional?clientid="+clientid+"" ;


		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setCashDeskRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);


		}

		function setCashDeskRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					   document.getElementById("cashDesk").innerHTML = req.responseText;
							document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
					
							
								
					
				}
			}
		}
		
		
function disabledText(){
	var type = document.getElementById('apmtType').value;
	
	if(type!= 0){
		document.getElementById('manualTypeName').disabled = true;
		document.getElementById('manualCharge').disabled = true;
		
		var url = "getChargeAmountAdditional?type="+type+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = disabledTextRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
		
	
	else{
		document.getElementById('manualTypeName').disabled = false;
		document.getElementById('manualCharge').disabled = false;
		document.getElementById("chargeAmount").value = '';
		document.getElementById("chargeAmount").disabled = true;
	}
}

function disabledTextRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("chargeAmount").value = req.responseText;
				document.getElementById("chargeAmount").disabled = true;
	         }
		}
	}

function casdeskChargesChecked(){
	$( "#baselayout1loaderPopup" ).modal( "show" );
	document.getElementById('casdeskAdditional').submit();
		
	}

function invoiceChargesChecked(){
	document.getElementById('createInvoiceAdditional').submit();
		
	}

function showPopUpDetails(){
	
	var url = "showListAdditional";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPopUpDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showPopUpDetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allClientDetails").innerHTML = req.responseText;
				
	         	
				
	         }
		}
	}
	
	
function setClientDetails(name,id,type,typeName){
	//deleteChargeAccountsTableAjax();
	if(id==""){
		id=document.getElementById("clientId").value;
	}
	document.getElementById("clientId").value = id;
	var url = "getClientDetailsAdditional?id="+id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setClientDetailsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
		
}

function setClientDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split("~");
			var clientFullName = temp[0];			
			var tpDetails= temp[1];
			var clientDetails= temp[2];
			var client_addr=temp[3];
			var client_uhid=temp[4];
			//alert(data);
			
			document.getElementById("client").value = clientFullName;
			if(document.getElementById("client_uhid")){
				document.getElementById("client_uhid").innerHTML=client_uhid;
			}
			if(document.getElementById("client_addr")){
			document.getElementById("client_addr").innerHTML=client_addr;
			}
			document.getElementById("clientDetailsId").innerHTML = clientDetails;
			if(tpDetails!='null null null null null'){
				document.getElementById("tpDetailsId").innerHTML = tpDetails;
			}
			
			$('#clientSearch').modal('hide');
			
			
			var clientid = document.getElementById("clientId").value;
			setSelectedClientSessionRecordAjax(clientid);
			
			
		}
	}

}

function searchClientDetails(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientAdditional?searchText="+searchText+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchClientDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function searchClientDetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allClientDetails").innerHTML = req.responseText;
	         	
				
	         }
		}
	}


function setresetaction(){
	if(document.getElementById("creditCharge").checked==true){
		document.getElementById("createchargebtn").disabled=true;
		document.getElementById("createinvoicebtn").disabled=true;
		
	}else{
		document.getElementById("createchargebtn").disabled=false;
		document.getElementById("createinvoicebtn").disabled=false;
	}
}

function setLocValidate(){
	
	var location = document.getElementById('location').value;
	
	
	//alert(location);
	document.getElementById("locError").innerHTML = "";
	
	if(document.getElementById("creditCharge").checked==true){
		
		if(isreordadd==0){
			var rec = document.createElement("label");
			rec.innerHTML = "Please add some record in list";
		    document.getElementById('recerror').appendChild(rec);
		    return false;
		}else if(document.getElementById('hiddenTotal').value==currencySign){
			var rec = document.createElement("label");
			rec.innerHTML = "Please add some record in list";
		    document.getElementById('recerror').appendChild(rec);
		    return false;
		}
		else{
			return true;
		}
		
	}else{
		
		
		
		if(location == "0"){
			var loc = document.createElement("label");
			loc.innerHTML = "Please Select Location";
		    document.getElementById('locError').appendChild(loc);
		    return false;
		}else if(isreordadd==0){
			var rec = document.createElement("label");
			rec.innerHTML = "Please add some record in list";
		    document.getElementById('recerror').appendChild(rec);
		    return false;
		}else if(document.getElementById('hiddenTotal').value==currencySign){
			var rec = document.createElement("label");
			rec.innerHTML = "Please add some record in list";
		    document.getElementById('recerror').appendChild(rec);
		    return false;
		}
		else{
			return true;
		}
		
	}
	
	
	
	
	
}


//debit charges js
		var newchargetype = "";
		function filterCharges(chargetype){
			newchargetype = chargetype;
		    var tpid=document.getElementById("tpid").value;
		    var ipdwhopay=document.getElementById("payee").value;
		    var ipdaddmissionid=document.getElementById("ipdaddmissionid").value;
		    var clientId = document.getElementById("clientId").value;
		  	//var url = "chargeIpd?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
		  	//  16 May 2018
		    var url = "chargeBookAppointmentAjax?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = filterChargesRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				
		}
		
		function filterChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data= req.responseText;
					var data1= data.split("!@");
					 //lokesh 22/11/2018
					if(document.getElementById("compulsaryconsultant")){
						 document.getElementById("compulsaryconsultant").value =data1[1];
					}
					
					
					 document.getElementById("additionalChargeAjax").innerHTML = data1[0];
					 $("#chargeTYpe").trigger("chosen:updated");
    			 	 $(".chosen").chosen({allow_single_deselect: true});
    			 	 if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge'||data1[1]=='1'){
    			 		//akash 20 July 2018
    			 		 //lokesh on the req of praful sir 29/11/2018
    			 		 setvisitingdrlist();
    			 	 }else{
    			 		document.getElementById("visitingconsltantdiv").innerHTML = "<input type='hidden' id='consultantdr' value='0'>";
    			 		if(serachByCodeId>0){
    			 			document.getElementById('chargeTYpe').className="";
        					document.getElementById('chargeTYpe').value=serachByCodeId;
        					document.getElementById('chargeTYpe').className="form-control showToolTip chosen-select";
        					$("#chargeTYpe").trigger("chosen:updated");
        				  	$(".chosen-select").chosen({allow_single_deselect: true});
        				  	var serachByCodeIdNew =serachByCodeId;
        				  	serachByCodeId =0;
        				  	setAdditionalChargeAjax1(serachByCodeIdNew);
    			 		}
    			 	}
    			 	 //akash 20 July 2018
    			 	
				}
			}
		}
		

function setAdditionalChargeAjax1(apmtTypeid){
	glbapmtTypeid = apmtTypeid;
	if(document.getElementById('chargeTYpe').value==0){
		document.getElementById('addchargebtn').disabled = 'disabled';
	}else{
		
		document.getElementById('addchargebtn').disabled = '';
	}
	
	if(document.getElementById('chargeTYpe').value==0){
		document.getElementById('mannualcharge').disabled = '';
		document.getElementById('charge').disabled = '';
		
	}else{
		document.getElementById('mannualcharge').value = '';
		document.getElementById('mannualcharge').disabled = 'disabled';
		document.getElementById('charge').disabled = '';
	}
	
	var masterchargetype = document.getElementById('masterchargetype').value;
	var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+"&masterchargetype="+masterchargetype+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAdditionalChargeAjax1Request;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
}

function setAdditionalChargeAjax1Request(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
			/*document.getElementById('charge').value = currencySign+req.responseText; 
			ipdcharge = req.responseText;
			document.getElementById('hdncharge').value = req.responseText; 
			var charge = req.responseText;
			calcamount(charge);*/
			var clientid=document.getElementById('hiddenclientid').value;
	         document.getElementById('charge').value = data[0]; 
				ipdcharge = data[0];
				document.getElementById('hdncharge').value = data[0]; 
				var charge = data[0];
				calcamount(charge);
				
			
			if(document.getElementById('chargeTYpe').value==0){
				document.getElementById('charge').value = '';
				document.getElementById('amount').innerHTML = '';
			}
			document.getElementById('isindisharecharge').value = data[1]; 
			var nonedit=data[2];
			if(nonedit==1){
				document.getElementById("charge").readOnly = true;
			}else{
				document.getElementById("charge").readOnly = false;
			}
			if(data[3]=='true'){
				document.getElementById("charge").readOnly = false;
			}else{
				//if(clientid=='matrusewa' || clientid=='vrundavandemo' || clientid=='ayushman' || clientid=='demoayush'){
				//document.getElementById("charge").readOnly = false;
				//}else{
				document.getElementById("charge").value = '';
			//	}
			}
			
			document.getElementById("sessionno").value=data[4];
		}
	}
	
}


function calcmanualcharge(){
	
	
document.getElementById('hdncharge').value = document.getElementById('charge').value;


if(document.getElementById('mannualcharge').value=='' || document.getElementById('hdncharge').value==''){
	document.getElementById('addchargebtn').disabled = '';
}else{
	document.getElementById('addchargebtn').disabled = '';
}
document.getElementById('hdncharge').value = document.getElementById('charge').value;
var charge = document.getElementById('hdncharge').value;
calcamount(charge);
}	

function calcamount(){
	var charge = document.getElementById('hdncharge').value;
	
	var qty = document.getElementById('quantity').value;
	var amount = parseFloat(charge) * qty;
	document.getElementById('amount').innerHTML = currencySign+amount;
}



//tptype temp charge updation
function updatetptypetempcharge(dbid,id,val){
	if(val<0){
		jAlert('error', 'Please Enter Amount Greater than 0.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById(""+id).value=0;
		val=0;
	}else{
		
	}
	var url = "invnewchargeCompleteApmt?dbid="+dbid+"&charge="+val+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatetptypetempchargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);

   
}


function updatetptypetempchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setCashDesk1();
		}
	}
   
}

function setChargeAmount(){
	document.getElementById('addchargebtn').disabled = 'true';
	var packageid ='';
	var ipdclientid = document.getElementById('clientId').value;
	var ipdclientname = document.getElementById('client').value;
	
	var chargetype = document.getElementById("chargeTYpe").value;
	var quantity = document.getElementById("quantity").value;
	var charge = document.getElementById("hdncharge").value;;
	var mannualcharge = document.getElementById("mannualcharge").value;
	var sessioncount= document.getElementById("sessionno").value;
	if(document.getElementById("packageid")){
	 packageid = document.getElementById("packageid").value;
	}
	var date = document.getElementById("date").value;
	var payby = $("input[name='payBuy']:checked").val();
	
	var masterchargetype =  $("#masterchargetype option:selected").text();
	
	var markappointment = 1;
	
	if(document.getElementById('client').value==''){
			jAlert('error', 'Please select client.', 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
	
	var clientid = document.getElementById('clientId').value;
	var clientname = document.getElementById('client').value;
	//var type = document.getElementById('apmtType').value;
	var location = document.getElementById('location').value;
	var creditDebitCharge = $("input[name='creditDebitCharge']:checked").val();
	var clinicid=document.getElementById('hiddenclientid').value;
	if(clinicid=='vrundavandemo'){
	var diaryUsersss=document.getElementById('diaryUsersss').value;
	}
    else{
	diaryUsersss='';
}
//	var currentipdid=0;
//	if(document.getElementById("lastipdid")){
//		currentipdid=document.getElementById("lastipdid").value;
//	}
	document.getElementById("sessioncount").value = sessioncount;
	document.getElementById("hdncdclientid").value = clientid;
	document.getElementById("hdncdclient").value = clientname;
	//document.getElementById("hdncdapmttype").value = type;
	document.getElementById("hdncdloc").value = location;
	document.getElementById("hdncdpayby").value = payby;
	document.getElementById("casdeskAdditional_creditDebitCharge").value = creditDebitCharge;
	
	document.getElementById("hdnciclientid").value = clientid;
	document.getElementById("hdncliclient").value = clientname;
	//document.getElementById("hdnciapmttype").value = type;
	document.getElementById("hdnciloc").value = location;
	document.getElementById("hdncipayby").value = payby;
	document.getElementById("hdncddate").value = date;
	document.getElementById("hdncidate").value = date;
	
	if(clinicid=='muredemo' || clientid=='mure'){
    var outsourcename=document.getElementById("Outsourcename").value;
	document.getElementById("hdnoutsource").value=outsourcename;
	document.getElementById("hdnoutsourceid").value=outsourcename;
	}/*else{
	outsourcename='0';
	document.getElementById("hdnoutsource").value=outsourcename;
	document.getElementById("hdnoutsourceid").value=outsourcename;
	}*/
	if(clinicid=='vrundavandemo' ){
	document.getElementById("hdncashdocid").value=diaryUsersss;
	document.getElementById("hdndocid").value=diaryUsersss;
	}
	//lokesh 29/11/18
	
	var compulsary_con="";
	if(document.getElementById("compulsaryconsultant")){
		 compulsary_con=document.getElementById("compulsaryconsultant").value;
	}
	var taxtypes="";
	var chargedescription="";
	if(document.getElementById("taxtypes")){
		if(document.getElementById('pcsaccount')){
			taxtypes=document.getElementById("taxtypes").value;
			chargedescription=document.getElementById("chargedescriptionnew").value;
			chargedescription=nicEditors.findEditor( "chargedescriptionnew" ).getContent();
			chargedescription=replaceAll(chargedescription,'&nbsp;',' ');
			chargedescription=replaceAll(chargedescription,'&','and');
			nicEditors.findEditor( "chargedescriptionnew" ).setContent("");
		}
	}
	//akash 02 feb 2018
	var visitingconsulatntdr =0;
	var isindisharecharge = 0;
	if(document.getElementById("isindisharecharge")){
		isindisharecharge =document.getElementById("isindisharecharge").value;
	}
	if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'||compulsary_con=='1'){
		visitingconsulatntdr = document.getElementById('consultantdr').value;
	 }
	
	/*var url = "savechargeIpdDashboard?ipdclientname="+ipdclientname+"&ipdpractitionername="+ipdpractitionername+"&ipdclientid="+ipdclientid+"&ipdpractitionerid="+ipdpractitionerid+"&chargetype="+chargetype+"&charge="+charge+"&payby="+payby+"&markappointment="+markappointment+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&date="+date+"&visitingconsulatntdr="+visitingconsulatntdr+"";*/
	var url = "savechargeBookAppointmentAjax?ipdclientname="+ipdclientname+"&ipdpractitionername="+ipdpractitionername+"&ipdclientid="+ipdclientid+"&ipdpractitionerid="+ipdpractitionerid+"&chargetype="+chargetype+"&charge="+charge+"&payby="+payby+"&markappointment="+markappointment+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&date="+date+"&visitingconsulatntdr="+visitingconsulatntdr+"&isindisharecharge="+isindisharecharge+"&packageid="+packageid+"&taxtypes="+taxtypes+"&chargedescriptnew="+chargedescription+"&diaryUsersss="+diaryUsersss+"&sessioncount="+sessioncount+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setChargeAmountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);


	}
	}

function replaceAll(str, find, replace) {
	var x=true;
    while(x){
    	if(str.includes(find)){
    		str=str.replace(find, replace);
    	}else{
    		x=false;
    	}
    }
    return str;
}

	function setChargeAmountRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
							  var data = str.split("~~");
							  var edd=data[1];
							  var lmh=data[2];
				   //document.getElementById("chargeTotalajax").innerHTML = req.responseText;
			if(lmh=true){	   
				   if(edd == 0){
				   		jAlert('error', 'Please Select same charge type as below', 'Error Dialog');
				   		
				   		setTimeout(function() {
				   			$("#popup_container").remove();
				   			removeAlertCss();
				   		}, alertmsgduration);
				   		
				   	}else{  
						setCashDesk1();		
						if(document.getElementById("taxtypes")){
						$('.lokclass').each(function() {
						this.checked =false;
					  });
			    	}
			     }
					
				}else{  
					setCashDesk1();		
					if(document.getElementById("taxtypes")){
						$('.lokclass').each(function() {
							this.checked =false;
						});
					}
				}
				
			}
		}
	}
	
	
	function getInventoryProductStock(){
		var masterchargetype = document.getElementById("masterchargetype").value;
		var prodid = document.getElementById("chargeTYpe").value;
		var isindisharecharge = document.getElementById("isindisharecharge").value;
		var compulsary_con='';
		if( document.getElementById("compulsaryconsultant")){
			compulsary_con=document.getElementById("compulsaryconsultant").value;
		}
		
		var flag = false;
		if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'||compulsary_con=='1'){
			var consultantdr = document.getElementById("consultantdr").value;
			if(consultantdr=='0'){
				flag = true;
			}
		 }
		
		if(flag==true){
			jAlert('error', 'Please select consultant.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		else{
			//var url = "prodstockIpd?masterchargetype="+masterchargetype+"&prodid="+prodid+" ";
			//17 April 2018 for speed up
			var url = "prodstockBookAppointmentAjax?masterchargetype="+masterchargetype+"&prodid="+prodid+" "; 
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getInventoryProductStockRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
		 }
	}
	
	function getInventoryProductStockRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				var temp = data.split('/');
				
				var isinventory = temp[0];
				var stock = temp[1];
				var sumqty = temp[2];
				var quantity = document.getElementById('quantity').value;
				
				if(parseInt(stock)==parseInt(sumqty)){
					quantity =  quantity + sumqty;
				}
				
				if(isinventory>0){
					if(stock>0){
						if(parseInt(quantity)<=parseInt(stock)){
							setChargeAmount();
						}else{
							jAlert('error', 'Quantity can not be greater than stock!!', 'Error Dialog');
		
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
							
					}else{
						jAlert('error', 'No more stock!!', 'Error Dialog');
		
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}
				}else{
					setChargeAmount();	
				}
			}
		}
	}
	
	
	function setCashDesk1(){
		var selectedUser = document.getElementById('clientId').value;;
		var cookiecommencing = document.getElementById("date").value;
		var cookieSelectedAppointmentid = 0;
		
		
		if(cookiecommencing!=''){
		 
		     var str= cookiecommencing.split("-");
		     var yymmdd= str[2]+"-"+str[1]+"-"+str[0];
		     cookiecommencing= yymmdd;
		}
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		/*var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;*/
		var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setCashDesk1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);


		}

		function setCashDesk1Request(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					   document.getElementById("cashDesk").innerHTML = req.responseText;
							//document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
					
					   if(document.getElementById('quantity')){
							document.getElementById('quantity').value=1;
						}
						if(document.getElementById('chargeTYpe')){
							document.getElementById('chargeTYpe').className="";
							document.getElementById('chargeTYpe').value='0';
							document.getElementById('chargeTYpe').className="form-control chosen";
							  $("#chargeTYpe").trigger("chosen:updated");
						  	   $(".chosen").chosen({allow_single_deselect: true});
							setAdditionalChargeAjax1(document.getElementById('chargeTYpe').value);
							if(document.getElementById('mannualcharge')){
								document.getElementById('mannualcharge').value="";
							}
						}
								
					
				}
			}
		}



	function confirmedDelete1(id){
			//alert(id);
			
		  	var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = confirmedDeleteRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}

		function confirmedDeleteRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
						setCashDesk1();
					
					
					
				}
			}
		}

		var newclientid = "";
		function checkAdvance(){
			 $( "#baselayout1loaderPopup" ).modal( "show" );
			var clientid = document.getElementById('clientId').value;;
			newclientid = clientid;
			var url = "advanceAdditional?clientid="+clientid+"";
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = checkAdvanceRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		
		function checkAdvanceRequest(){
				if (req.readyState == 4) {
				if (req.status == 200) {
					  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
						
					var bal = req.responseText;
					var str = document.getElementById("hiddenTotal").value; 
					var n = str.length;
					var charge = str.substring(3, n);
					
					var newinvoiceid = document.getElementById("newinvoiceid").value;
					var refundnote = document.getElementById("refundnote").value;
					if(newinvoiceid=='' || newinvoiceid==' ' || newinvoiceid=='  '){
						if(parseFloat(str)>parseFloat(bal)){
							$( "#baselayout1loaderPopup" ).modal( "hide" );
							jAlert('error', 'Refund should be less than balance amount', 'Error Dialog');
			
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}else{
							document.getElementById("manualinvoiceid").value= newinvoiceid;
							document.getElementById("refundnotes").value= refundnote;
							document.getElementById("refundfrm").submit();
							
						}
					}else if(refundnote==''){
						$( "#baselayout1loaderPopup" ).modal( "hide" );
						jAlert('error', 'Please enter refund note', 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}else{
						checkinvoiceidofclient(newinvoiceid,charge,newclientid);
						setTimeout(function() {
							if(testresult==0){
								document.getElementById("manualinvoiceid").value= newinvoiceid;
								document.getElementById("refundnotes").value= refundnote;
								document.getElementById("refundfrm").submit();
							}
						}, 3000*2);
						
						
					}
					
				}
			}
		}
		
		
		function setChargeDate(d){
			document.getElementById("hdncrinvdate").value = d;
			document.getElementById("hdncddate").value = d;
			
		}
		
		

function setvisitingdrlist() {
	
	//var url = "getvisitingconsultantlistIpd";
	var url = "getvisitingconsultantlistBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setvisitingdrlistRequest;
	req.open("GET", url, true);

	req.send(null);

}

function setvisitingdrlistRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("visitingconsltantdiv").innerHTML = req.responseText;
			$("#consultantdr").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			if(serachByCodeId>0){
	 			document.getElementById('chargeTYpe').className="";
				document.getElementById('chargeTYpe').value=serachByCodeId;
				document.getElementById('chargeTYpe').className="form-control showToolTip chosen-select";
				$("#chargeTYpe").trigger("chosen:updated");
			  	$(".chosen-select").chosen({allow_single_deselect: true});
			  	var serachByCodeIdNew =serachByCodeId;
			  	serachByCodeId =0;
			  	setAdditionalChargeAjax1(serachByCodeIdNew);
	 		}
		}
	}

}
var newcharge = "";
function checkinvoiceidofclient(newinvoiceid,charge,newclientid){
	newcharge = charge;
	var url = "checkinvoiceidofclientAdditional?clientid="+newclientid+"&newinvoiceid="+newinvoiceid+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = checkinvoiceidofclientRequest;
	req.open("GET", url, true);

	req.send(null);
}
var testresult =0;
function checkinvoiceidofclientRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
	         if(data[0]=='0'){
	        	 jAlert('error', 'Invoice id is not related to client id', 'Error Dialog');
	 			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	        	 testresult = 1;
					return false;
	         }/*else if(parseFloat(newcharge)>parseFloat(data[1])){
	        	 jAlert('error', 'Refund should be less than invoice total amount', 'Error Dialog');
	 			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					 testresult = 1;
					return false;
	         }*/
		}
	}
}

function checkadvanceorrefund() {
	var newinvoiceid = document.getElementById("newinvoiceid").value;
	var refundnote = document.getElementById("refundnote").value;
	if(refundnote==''){
		jAlert('error', 'Please enter refund note', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(newinvoiceid=='' || newinvoiceid==' ' || newinvoiceid=='  '){
		checkAdvanceNew();
	}else{
		var clientid = document.getElementById('clientId').value;
		var str = document.getElementById("hiddenTotal").value; 
		var hiddenTotal1 = document.getElementById("hiddenTotal1").value;
		if(hiddenTotal1==' ' || hiddenTotal1=='  '){
			jAlert('error', 'Total is blank', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(hiddenTotal1==''){
			jAlert('error', 'Total is blank', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(hiddenTotal1=='0'){
			jAlert('error', 'Total is 0', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var n = str.length;
			var charge = str.substring(3, n);
			//checkinvoiceidofclientNew(newinvoiceid,charge,clientid);
			checkinvoiceidofclientNew(newinvoiceid,hiddenTotal1,clientid);
		}
	}
}

function checkAdvanceNew(){
	var clientid = document.getElementById('clientId').value;
	var url = "advanceAdditional?clientid="+clientid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkAdvanceNewRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function checkAdvanceNewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var bal = req.responseText;
			var str = document.getElementById("hiddenTotal").value; 
			var n = str.length;
			var charge = str.substring(3, n);
			
			var newinvoiceid = document.getElementById("newinvoiceid").value;
			var refundnote = document.getElementById("refundnote").value;
			
			if(parseFloat(charge)>parseFloat(bal)){
				jAlert('error', 'Refund should be less than balance amount', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				document.getElementById("manualinvoiceid").value= newinvoiceid;
				document.getElementById("refundnotes").value= refundnote;
				 if(confirm("Are you sure?")){
					 document.getElementById("refundrequestfrm").submit(); 
				 }
			}
		}
	}
}

var chargen = "";
function checkinvoiceidofclientNew(newinvoiceid,charge,newclientid){
	chargen = charge;
	var url = "checkinvoiceidofclientAdditional?clientid="+newclientid+"&newinvoiceid="+newinvoiceid+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = checkinvoiceidofclientNewRequest;
	req.open("GET", url, true);

	req.send(null);
}
function checkinvoiceidofclientNewRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
	         if(data[0]=='0'){
	        	 jAlert('error', 'Invoice id is not related to client id', 'Error Dialog');
	 			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	        }else if(data[0]=='2'){
	        	 jAlert('error', 'Previous refund request is pending. Please clear that request First', 'Error Dialog');
		 			setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	        }
	         /*else if(parseFloat(chargen)>parseFloat(data[1])){
	        	 jAlert('error', 'Refund should be less than invoice total amount', 'Error Dialog');
	 			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			 }*/
	        else{
				 var newinvoiceid = document.getElementById("newinvoiceid").value;
				 var refundnote = document.getElementById("refundnote").value;
				 document.getElementById("manualinvoiceid").value= newinvoiceid;
				 document.getElementById("refundnotes").value= refundnote;
				 if(confirm("Are you sure?")){
					 document.getElementById("refundrequestfrm").submit();
				 }
				 
			 }
		}
	}
}

function deleteRefundRequest(val){
	document.getElementById("parent_id").value = val;
	$('#deletemodel').modal( "show" );
}

function deleteRefundRequest1(){
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	
	var url="deleterefundrequestAdditional?parentid="+parentid+"&delete_reason="+delete_reason+"";  	  
	  if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	  req.onreadystatechange = deleteRefundRequest1Request;
	  req.open("GET", url, true); 
	  req.send(null);  
	 }
function deleteRefundRequest1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function checkinvoiceidandsetdate(id) {
	var newinvoiceid = id;
	
	if(newinvoiceid==''){
		jAlert('error', 'Please enter invoice id', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(newinvoiceid=='' || newinvoiceid==' ' || newinvoiceid=='  '){
		jAlert('error', 'Please enter invoice id', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var clientid = document.getElementById('clientId').value;
		
		checkinvoiceidandsetdate1(newinvoiceid,clientid);
	}
}

var refundinvoiceid = "";
function checkinvoiceidandsetdate1(newinvoiceid,newclientid){
	refundinvoiceid = newinvoiceid;
	var url = "checkinvoiceidofclientAdditional?clientid="+newclientid+"&newinvoiceid="+newinvoiceid+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = checkinvoiceidandsetdate1Request;
	req.open("GET", url, true);

	req.send(null);
}
function checkinvoiceidandsetdate1Request() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
	         if(data[0]=='0'){
	        	 jAlert('error', 'Invoice id is not related to client id', 'Error Dialog');
	 			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	        }else{
				 var newinvoiceid = refundinvoiceid;
				 getRefundInvoiceData(refundinvoiceid);
			 }
		}
	}
}
function getRefundInvoiceData(refundinvoiceid) {
	var clientid = document.getElementById('clientId').value;
	document.getElementById('chargeajaxqty').innerHTML = "";
	document.getElementById('chargeajax').innerHTML = "";
	document.getElementById('advancebtndiv').innerHTML ="";
	document.getElementById('addrefundbtnhide').innerHTML ="";
	var url = "getrefundinvoicedataAdditional?clientId="+clientid+"&newinvoiceid="+refundinvoiceid+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getRefundInvoiceDataRequest;
	req.open("GET", url, true);

	req.send(null);
}

function getRefundInvoiceDataRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			setRefundCashDesk();
		}
	}
}
//Adarsh 13March for approve
function approverefund(val){
	document.getElementById("approve_id").value = val;
	$('#approvemodel').modal( "show" );
}
/*function aproverefundamountAdditional(){
	 var id = document.getElementById("approve_id").value;
		var approve_reason = document.getElementById("approve_reason").value;
		var adarsh = "aproverefundAdditional?id="+id+"&approve_reason="+approve_reason+"";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = aproverefundamountAdditionalRequest;
		  req.open("GET", adarsh, true); 
		  req.send(null);  
 }
	function aproverefundamountAdditionalRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location ='refundrequestdashboardAdditional';
		         }
			}	 
} */

	function aproverefundamountAdditional(id){
		var id = document.getElementById("approve_id").value;
		var approve_reason = document.getElementById("approve_reason").value;
		if(approve_reason==''){
			jAlert('error', 'Please enter aprrove note!!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			$("#dashboardloaderPopup").modal('show');
			var searchtext = document.getElementById("searchtext").value;
			var fromdate = document.getElementById("fromdate").value;
			var todate = document.getElementById("todate").value;
			var filter_status = document.getElementById("filter_status").value;
			var countdata = document.getElementById("countdata").value;
			 var dataObj={
				  	"id":""+id+"",
				  	"approve_reason":""+approve_reason+"",
				  	"searchtext":""+searchtext+"",
				  	"fromdate":""+fromdate+"",
				  	"todate":""+todate+"",
				  	"filter_status":""+filter_status+"",
				  	"countdata":""+countdata+"",
			 };
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "aproverefundAdditional",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   window.location ='refundrequestdashboardAdditional';
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
	
	function setRefundCashDesk(){
		var selectedUser = document.getElementById('clientId').value;;
		var cookiecommencing = document.getElementById("date").value;
		var cookieSelectedAppointmentid = 0;
		
		
		if(cookiecommencing!=''){
		 
		     var str= cookiecommencing.split("-");
		     var yymmdd= str[2]+"-"+str[1]+"-"+str[0];
		     cookiecommencing= yymmdd;
		}
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		var url = "refundcashdeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;


		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setRefundCashDeskRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);


		}

		function setRefundCashDeskRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					   document.getElementById("cashDesk").innerHTML = req.responseText;
							//document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
					
							
								
					
				}
			}
		}
		
		function confirmedrefundDelete1(id){
			//alert(id);
			
		  	var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = confirmedrefundDelete1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}

		function confirmedrefundDelete1Request(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
						setRefundCashDesk();
					
					
					
				}
			}
		}
		
		//  28 May 2018
		function checkRefundPaidStaus(id,clientid){
			var url = "checkrefundpaidstatusBookAppointmentAjax?id="+id+"&clientid="+clientid+"" ;

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    
			req.onreadystatechange = checkRefundPaidStausRequest;
		    req.open("GET", url, true); 
		    req.send(null);
		}

		function checkRefundPaidStausRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					var str=req.responseText;
				    var data=str.split("~");
					if(data[0]=='1'){
						 jAlert('error', 'Already refunded', 'Error Dialog');
				 			setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}else{
						 openBlankPopup('refundinvoiceAdditional?id='+data[1]+'&clientId='+data[2]+'');
					}
				   
				}
			}
		}
		
		
		function deletediscount(){
			var reason = document.getElementById("deldel").value;
			var id = document.getElementById("deleteid").value;
			var inv=document.getElementById("invid").value;
			if(reason==""){
				alert("plz give reason to delete!");
				return;
			}
			var url = "deletediscountBookAppointmentAjax?id="+id+"&reason="+reason+"&invid="+inv ;

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    
			req.onreadystatechange = deletediscountRequest;
		    req.open("GET", url, true); 
		    req.send(null);
		}
		
		function deletediscountRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();	 
				}
			}
		}
		
		
		function addpackageoncreatecharge(id){

			var url = "pkgdtailsIpdDashboard?id="+id+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addpackageoncreatechargeRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}
		function addpackageoncreatechargeRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					 var str=req.responseText;
				     var data=str.split("~");
			         
				
					document.getElementById("quantity").value = 1;
					document.getElementById("hdncharge").value = data[0];
					document.getElementById("charge").value = data[0];
					document.getElementById("packageid").value=data[1];
					 document.getElementById("mannualcharge").value = data[2];
					 
					 calcmanualcharge();
				}
			}
		}
		function calcmanualcharge(){
			
			var appttype=document.getElementById("chargeTYpe").value;
			var amot=document.getElementById("charge").value;
			document.getElementById('hdncharge').value = document.getElementById('charge').value;
			if(document.getElementById('mannualcharge').value=='' || document.getElementById('hdncharge').value==''){
				document.getElementById('addchargebtn').disabled = '';
			}else{
				document.getElementById('addchargebtn').disabled = '';
			}
			
			if( appttype==0){
				if(document.getElementById('mannualcharge').value!='' && amot!=''){
					document.getElementById('addchargebtn').disabled = '';
				}else{
					document.getElementById('addchargebtn').disabled = 'disabled';	
				}
				
			}else{
				document.getElementById('addchargebtn').disabled = '';
			}
			
			document.getElementById('hdncharge').value = document.getElementById('charge').value;
			var charge = document.getElementById('hdncharge').value;
			calcamount(charge);
			}	

			function calcamount(){
				var charge = document.getElementById('hdncharge').value;
				var qty = document.getElementById('quantity').value;
				if(document.getElementById('chargedescriptionnew')){
					var taxper=0;
					document.getElementById('taxtypes').value='0';
					var typids='0'
					$('.lokclass').each(function() {
						if(this.checked){
							taxper=Number(taxper)+Number(this.value);
							typids=typids+","+this.id+"~"+this.value;
						}
					});
					document.getElementById('taxtypes').value=typids;
					var taxamt= (taxper*Number(charge))/100.0;
					taxamt=taxamt.toFixed(2);
					charge=(Number(charge)+Number(taxamt));
					/*document.getElementById('hdncharge').value=charge;*/
					document.getElementById('taxammt').innerHTML = currencySign+(Number(taxamt)*Number(qty));
				}
				var amount = parseFloat(charge) * qty;
				document.getElementById('amount').innerHTML = currencySign+amount;
			}
			function showpkgsapplied(){
				showEditPackageListAjax();
				$('#editpack').modal( "show" );
			}

			function showEditPackageListAjax(){
				var ipdaddmissionid = document.getElementById('lastipdid').value;
				var patientId = document.getElementById('clientId').value;
				var url = "pkglisteditIpdDashboard?admissionid="+ipdaddmissionid+"&ipdclientid="+patientId+" ";
				   
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = showEditPackageListAjaxRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
			}
			function showEditPackageListAjaxRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						document.getElementById('appliedpkglistdiv').innerHTML = req.responseText;
					}
				}
			}
			function getapplpkglist(id){
				var url = "pkgdtailsappliedIpdDashboard?id="+id+"";
				   
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
				           
				req.onreadystatechange = getapplpkglistRequest;
				req.open("GET", url, true); 
				          
				req.send(null);

				}
				function getapplpkglistRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						var ipdaddmissionid = document.getElementById('lastipdid').value;
						 var str=req.responseText;
						 var text=str.split('!@#');
						 document.getElementById('listeditpkgbody').innerHTML = text[0];
						 document.getElementById('amtpkgedit').value=text[1];
						
						 var fdt=text[2].split(' ');
						 var ft=fdt[1].split(':');
						 document.getElementById('hrpkg1').value=ft[0];
						 document.getElementById('mntpkg1').value=ft[1];
						 document.getElementById('fdt1').value=fdt[0];
						 var tdt=text[3].split(' ');
						 var tt=tdt[1].split(':');
						 document.getElementById('hrpkg').value=tt[0];
						 document.getElementById('mntpkg').value=tt[1];
						 document.getElementById('tdt1').value=tdt[0];
						 document.getElementById('hidenipdid').value=ipdaddmissionid;
						 }
					}
				}
				function deleteinvdiscount(){
						var reason = document.getElementById("delinddel").value;
						var id = document.getElementById("deleteindid").value;
						if(reason==""){
							alert("please give reason to delete!");
							return;
						}
						var url = "deleteinddiscountBookAppointmentAjax?id="+id+"&reason="+reason+"" ;
	
						if (window.XMLHttpRequest) {
							req = new XMLHttpRequest();
						}
						else if (window.ActiveXObject) {
							isIE = true;
							req = new ActiveXObject("Microsoft.XMLHTTP");
						}   
					    req.onreadystatechange = deleteinddiscountRequest;
					    req.open("GET", url, true); 
					    req.send(null);
				}
				
				function deleteinddiscountRequest(){
					if (req.readyState == 4) {
						if (req.status == 200) {
							window.location.reload();	 
						}
					}
				}
				
				
function createinvoicedbl(){
	document.getElementById("createinvoicebtn").disabled = true;
	$('#dashboardloaderPopup').modal('show');
	document.getElementById('createInvoiceAdditional').submit();
}			

function cashdeskdbl(){
	document.getElementById("cashdeskbtn").disabled = true;
	$('#dashboardloaderPopup').modal('show');
	document.getElementById('casdeskAdditional').submit();
}
var serachByCodeId=0;
function searchChargeByCode(){
    var searchByCode = document.getElementById("searchByCode").value;
    var tpid=document.getElementById("tpid").value;
    var ipdwhopay=document.getElementById("payee").value;
    var clientId = document.getElementById("clientId").value;
    if(searchByCode!=''){
    	var url = "searchChargeByCodeBookAppointmentAjax?searchByCode="+searchByCode+"&ipdtpid="+tpid+"&ipdwhopay="+ipdwhopay+"&clientId="+clientId+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = searchChargeByCodeRequest;
		req.open("GET", url, true); 
		req.send(null);	
    }
}

function searchChargeByCodeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data= req.responseText;
			var data1= data.split("~~");
			if(data1[0]==0){
				 jAlert('error', 'Code related charge not found!!!', 'Error Dialog');
		 		 setTimeout(function() {
				 $("#popup_container").remove();
				 removeAlertCss();
				 }, alertmsgduration);
			}else{
				document.getElementById('masterchargetype').className="";
				document.getElementById('masterchargetype').value=data1[3];
				document.getElementById('masterchargetype').className="form-control showToolTip chosen-select";
				$("#masterchargetype").trigger("chosen:updated");
			  	$(".chosen-select").chosen({allow_single_deselect: true});
				
			  	serachByCodeId=data1[2];
				filterCharges(data1[3]);
			}
		}
	}
}
	