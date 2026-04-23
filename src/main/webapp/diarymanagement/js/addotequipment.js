function showotpriscription(){
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	//showpriscription();
	repeateOTPriscDateAjax(patientId,diaryuserId,editcondition_id);

}

//set repeatdate dropdown
function repeateOTPriscDateAjax(cid,pid,conid){
	var url = "rpeatPrescription?clientId="+cid+"&prectionerid="+pid+"&conditionid="+conid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = repeateOTPriscDateAjaxRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function repeateOTPriscDateAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('eotrpeatdivajax').innerHTML = req.responseText;
			
			showOTpriscription();
		}
	}

}

function showOTpriscription(){
	editparentpriscid = 0;
	istemplate = 0;
	document.getElementById("eotprisctable").innerHTML = '';
	//getPriscClientInfo(patientId);
	removeSession();
	$('#modifyPopup').modal( "hide" );
	$('#otequipmentpopup').modal( "show" );
}


function addTempEquipment(){
	
	var categoryid = document.getElementById("eotmdicinecategory").value;
	var mdicinenameid = document.getElementById("eotmdicinename").value;
	var mdicinenametxt = $("#eotmdicinename option:selected").text();
	var priscdose = document.getElementById("eotpriscdose").value;
	var priscfreq = document.getElementById("eotpriscfreq").value;
	var priscdays = document.getElementById("eotpriscdays").value;
	var prisctype =  document.getElementById("eotprisctype").value;
	//var prisccode = document.getElementById("eotprisccode").value;
	var prisccode="0";
	var prisctotal = document.getElementById("eotprisctotal").value;
	var dosenotes=document.getElementById("eotpriscdosenotes").value;
	var priscdurationtype = document.getElementById("eotpriscdurationtype").value;
	var templatename = document.getElementById("eottemplatename").value;


	var url = "addtempeqEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&mdicinenametxt="+mdicinenametxt+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&priscdays="+priscdays+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prisctotal="+prisctotal+"&priscdurationtype="+priscdurationtype+"&dosenotes="+dosenotes+"&templatename="+templatename+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addaddTempEquipmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}


function addaddTempEquipmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("eotprisctable").innerHTML = req.responseText;
			
		}
	}
	
}

function deleteeqdata(id){
	
	var templatename = document.getElementById("eottemplatename").value;
	
	var url = "deloteqEmr?selectedid="+id+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&templatename="+templatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deleteeqdataRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function deleteeqdataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("eotprisctable").innerHTML = req.responseText;
		}
	}
	
}


function addoteqdata(){
var templatename = document.getElementById("eottemplatename").value;
if(templatename==0){
	

	insertotequipment(0)
	$('#otequipmentpopup').modal( "hide" );
	
	

}else{
	saveparentoteqemplatedata(0);
	
}
	
}


function insertotequipment(action){
	
	//multi values
	/*var categoryid = document.getElementById("mdicinecategory").value;
	var mdicinenameid = document.getElementById("mdicinename").value;
	var mdicinenametxt = $("#mdicinename option:selected").text();
	var priscdose = document.getElementById("priscdose").value;
	var priscfreq = document.getElementById("priscfreq").value;
	var priscdays = document.getElementById("priscdays").value;
	var prisctype =  document.getElementById("prisctype").value;
	var prisccode = document.getElementById("prisccode").value;
	var prisctotal = document.getElementById("prisctotal").value;
*/
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	if(document.getElementById("eotpaymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("eotpaymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("eotpaymode3").checked==true){
		var otherpay = 1;
	}
	
	var priscautoid = document.getElementById("eotpaymode2").value;
	var priscdosenotes = document.getElementById("eotpriscdosenotes").value;
	var followupsqty = document.getElementById("eotfollowupsqty").value;
	var followupstype = document.getElementById("eotfollowupstype").value;
	var priscadvoice = document.getElementById("eotpriscadvoice").value;
    var mdtemplatename = document.getElementById("eotmdtemplatename").value;
	
	
	var english = 0;
	var regional  = 0;
	var hindi = 0;
	
	if(document.getElementById("eotlanguage1").checked==true){
		var english = 1
	}
	if(document.getElementById("eotlanguage2").checked==true){
		var regional = 1;
	}
	if(document.getElementById("eotlanguage3").checked==true){
		var hindi = 1;
	}
	
	
	 var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	
	var url = "addoteqEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+
	"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+
	"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+
	"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&istemplate="+istemplate+
	"&mdtemplatename="+mdtemplatename+"&editAppointId="+editAppointId+" ";
	//&repeatdate="+repeatdate+
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = insertotequipmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    
}

function insertotequipmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById("prisctable").innerHTML = req.responseText;
			
		
			
			showoteqtemplateList();
			
			
			
		}
	}
}


function saveoteqTemplae(){
	istemplate = 1;
	var mdtemplatename = document.getElementById("eotmdtemplatename").value;
	if(mdtemplatename==''){
		jAlert('error', 'Please enter template name!!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		insertotequipment();
	}
	
}

function showoteqtemplateList(){
		var url = "oteqtemplateEmr";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showoteqtemplateListRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function showoteqtemplateListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("eotmdtemplatename").value = '';
			document.getElementById("eotprisctemplatediv").innerHTML = req.responseText;
			
			$("#eottemplatename").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});	
			
			jAlert('success', 'Template saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			}
			
		}
}


function showTemplateoteq(id){
	var url = "tenplateoteqEmr?selectedid="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showTemplateoteqRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function showTemplateoteqRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("eotprisctable").innerHTML = req.responseText;
			
			
			getPriscClientInfo(patientId);
			
			$('#otequipmentpopup').modal( "show" );
		}
	}
}


//save template data
function saveparentoteqemplatedata(discharge){
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	if(document.getElementById("eotpaymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("eotpaymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("eotpaymode3").checked==true){
		var otherpay = 1;
	}
	
	var priscautoid = document.getElementById("eotpaymode2").value;
	var priscdosenotes = document.getElementById("eotpriscdosenotes").value;
	var followupsqty = document.getElementById("eotfollowupsqty").value;
	var followupstype = document.getElementById("eotfollowupstype").value;
	var priscadvoice = document.getElementById("eotpriscadvoice").value;
    var mdtemplatename = document.getElementById("eotmdtemplatename").value;
	
	
	var english = 0;
	var regional  = 0;
	var hindi = 0;
	
	if(document.getElementById("eotlanguage1").checked==true){
		var english = 1
	}
	if(document.getElementById("eotlanguage2").checked==true){
		var regional = 1;
	}
	if(document.getElementById("eotlanguage3").checked==true){
		var hindi = 1;
	}
	
	
	
	var url = "saveparentoteqEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveparentoteqemplatedataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    
    
}
function saveparentoteqemplatedataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var listsizeid = document.getElementById("eotlistsizeid").value;
				//alert(listsizeid);
				
				for(i=0;i<listsizeid;i++){
					saveTemplateoteqData(i)
				}
				
				//$('#priscriptionpopup').modal( "hide" );
		}
	}
}


function saveTemplateoteqData(i){

//multi values
	/*var categoryid = document.getElementById("mdicinecategory").value;
	var mdicinenameid = document.getElementById("mdicinename").value;
	var mdicinenametxt = $("#mdicinename option:selected").text();
	var priscdose = document.getElementById("priscdose").value;
	var priscfreq = document.getElementById("priscfreq").value;
	var priscdays = document.getElementById("priscdays").value;
	var prisctype =  document.getElementById("prisctype").value;
	var prisccode = document.getElementById("prisccode").value;
	var prisctotal = document.getElementById("prisctotal").value;
*/

	var mdcinenametxt = document.getElementById("eotmdcinenametxt"+i).innerHTML;
	var priscfreq = '';
	var dosage = '';
	var days = document.getElementById("eotdays"+i).value;
	var dosenotes = document.getElementById("eotdosage"+i).value;
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	
	var priscautoid = document.getElementById("paymode2").value;
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
    var repeatdate=document.getElementById("repeatdate").value;
    var mdtemplatename = document.getElementById("mdtemplatename").value;
	
	
	var english = 0;
	var regional  = 0;
	var hindi = 0;
	
	if(document.getElementById("language1").checked==true){
		var english = 1
	}
	if(document.getElementById("language2").checked==true){
		var regional = 1;
	}
	if(document.getElementById("language3").checked==true){
		var hindi = 1;
	}
	
	 var myString = wraperDivId;
			if(editAppointId==0){
				myString = myString.replace(/[^\d]/g, ''); 
				editAppointId = myString;
				
			}
	
	var url = "savetemplateoteqEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&mdcinenametxt="+mdcinenametxt+"&priscfreq="+priscfreq+"&dosage="+dosage+"&days="+days+"&dosenotes="+dosenotes+"&editAppointId="+editAppointId+" ";
	
	
	//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveTemplateoteqDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    
	
}

function saveTemplateoteqDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//showDischargePopup();
			
			$('#otequipmentpopup').modal( "hide" );
			//document.getElementById("priscnotes").innerHTML = req.responseText;
			
		}
	}
}

function listotequipmwnt(){
var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	
	openEmrPopup('listotEmr?apmtid='+editAppointId+' ')
}