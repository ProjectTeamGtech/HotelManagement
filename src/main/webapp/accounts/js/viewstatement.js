$(document).ready(function() {

showIpdAdmissionLog();
});
function sendmail(){
	
	
	
	$( "#sendEmailPopUp2" ).modal( "show" );
}


function sendPdfMail(id){
	
	var cc = document.getElementById('ccEmail').value;
	var to = document.getElementById('thirdPartEmail').value;
	var subject = document.getElementById('subject').value;
	var notes = nicEditors.findEditor( "emailBody" ).getContent();
	var clientid =  document.getElementById('hiddenclientid').value;
	var type = "Account";
	
	
	 var url = "emailCharges?to="+to+"&cc="+cc+"&subject="+subject+"&notes="+notes+"&id="+id+"&clientid="+clientid+"&type="+type+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendPdfMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function sendPdfMailRequest(){
	
		if (req.readyState == 4) {
				if (req.status == 200) {
					
					
					
					
		            jAlert('success', 'Email Send Sucessfully!!', 'Success Dialog');
		            setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
		            //tempAlert("Email Send Sucessfully", 5000);
		            $('#sendEmailPopUp').dialog( "close" );
				}
		}
	
}

function showhidechargedetails(id){
	if(document.getElementById('charges'+id).style.display==''){
		document.getElementById('charges'+id).style.display='none';
	}else{
		document.getElementById('charges'+id).style.display='';
	}
}

function showhideInvoicedchargedetails(id){
	if(document.getElementById('invch'+id).style.display==''){
		document.getElementById('invch'+id).style.display='none';
	}else{
		document.getElementById('invch'+id).style.display='';
	}
}

function showhidechargeNumberdetails(id){
	if(document.getElementById('chno'+id).style.display==''){
		document.getElementById('chno'+id).style.display='none';
	}else{
		document.getElementById('chno'+id).style.display='';
	}
}

function showChargesEstimate(id){
	
}



function deletechages(id,action){
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	   var url = "delchargeStatement?id="+id+"&action="+action+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deletechagesRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  return true;
	  }
	else
	  {
	  return false;
	  }
	
}

function deletechagesRequest(){
	if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();
				}
		}
}

function changeChargeQty(id,val){
	  var url = "updqtyStatement?id="+id+"&qty="+val+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changeChargeQtyRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  return true;
}

function changeChargeQtyRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					jAlert('success', 'Record Updated!!.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
		window.location.reload();
		
		}
		}
}


function showChargeEditPopupAjax(ipdid,chargeid,assesmentid,stdcharge,chargename,upchargeid,mastername,orignalname){

	var url = "editchargeStatement?ipdid="+ipdid+"&chargeid="+chargeid+"&assesmentid="+assesmentid+"&stdcharge="+stdcharge+"&chargename="+chargename+"&updchargeid="+upchargeid+"&mastername="+mastername+" " ;
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showChargeEditPopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	$('#dtailschargemodel').modal('show');
}

function showChargeEditPopupAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			var data = str.split("@#");
			var itr=data[0];
			var temp=data[1];
		document.getElementById('chargedtailsbody').innerHTML = itr;
		if(temp!=1){
			
		
		$("#fdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

	   });
		}
		 $("#apmttype").trigger("chosen:updated");
	  	   $(".chosen").chosen({allow_single_deselect: true});
			//window.location.reload();
			
         }
	}
}
function showChargeEditPopup(ipdid,chargeid,assesmentid,stdcharge,chargename,upchargeid,mastername,orignalname){
var dataObj={
			
			
		  	"ipdid":""+ipdid+"",
		  	"chargeid":""+chargeid+"",
		  	"assesmentid":""+assesmentid+"",
		  	"stdcharge":""+stdcharge+"",
		  	"chargename":""+chargename+"",
		  	"updchargeid":""+upchargeid+"",
		  	"mastername":""+mastername+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "editchargeBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   document.getElementById("chargedtailsbody").innerHTML=data.chargedtailsbody;
		   var ngppdole=data.ngppadole;
		   if(ngppdole!=1){
				
				
				$("#fdate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

			   });
				}
				 $("#apmttype").trigger("chosen:updated");
			  	   $(".chosen").chosen({allow_single_deselect: true});
					//window.location.reload();
					
		         
			  	 $('#dtailschargemodel').modal('show');
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
	
	
function showIpdAdmissionLog(){
		
		var clientId = document.getElementById("clientId").value;
		
		var url = "admissionIpdLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showIpdAdmissionLogRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}
	
	function showIpdAdmissionLogRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>IPD Admissions</b>";
				if(document.getElementById('filterdivid')){
				document.getElementById('filterdivid').style.display = 'none';
				}
				if(document.getElementById('filterbtnid')){
				document.getElementById('filterbtnid').style.display = 'none';
				}
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
function showbedchangedata(id){
	if(document.getElementById('ad'+id).style.display==''){
		document.getElementById('ad'+id).style.display='none';
	}else{
		document.getElementById('ad'+id).style.display='';
	}
}

function showbedloddatepopup(indx,size,frmdate,id){
var clientId = document.getElementById("clientId").value;

	var url = "edbedlogStatement?clientId="+clientId+"&indx="+indx+"&size="+size+"&frmdate="+frmdate+"&id="+id+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showbedloddatepopupRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	
	$('#bedlogmodel').modal('show');
}

function showbedloddatepopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		document.getElementById('bedlogbody').innerHTML = req.responseText;
		
			//window.location.reload();
			
         }
	}
} 


function shareChargesToDr(indiorall,invoiceid,chargeid,chargetotal,chargename){
	document.getElementById("indiorall").value = indiorall;
	document.getElementById("share_invoiceid").value = invoiceid;
	document.getElementById("share_chargeid").value = chargeid;
	document.getElementById("share_chargetotal").innerHTML=chargetotal;
	document.getElementById("share_chargename").value = chargename;
	document.getElementById("share_doctorid").value = '0';
	document.getElementById("share_amount").value = '';
	document.getElementById("share_calamount").innerHTML = '0';
	$('#sharecharge').modal('show');
}



function calculateShareAmount(val){
	var disctype = document.getElementById("disctype").value;
	var share_chargetotal = Number(document.getElementById("share_chargetotal").innerHTML);
	var total_share =0;
	if(disctype=='Percent'){
		if(val<=100 &&val>0){
			total_share = (share_chargetotal*val)/100;
			document.getElementById("share_calamount").innerHTML = total_share;
		}else{
			document.getElementById("share_amount").value='0';
			document.getElementById("share_calamount").innerHTML = '0';
			jAlert('error', "Please enter proper percentage !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
	}
	if(disctype=='Rupees'){
		if(val<=share_chargetotal &&val>0){
			total_share = val;
			document.getElementById("share_calamount").innerHTML = total_share;
		}else{
			document.getElementById("share_amount").value='0';
			document.getElementById("share_calamount").innerHTML = '0';
			jAlert('error', "Please entered value greater than charges !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
	}
}

function changeShareDiscountType(){
	document.getElementById("share_calamount").innerHTML = '0';
	document.getElementById("share_amount").value='0';
}


function saveShareChargeToDr(){
	var clientId = document.getElementById("clientId").value;
	var doctorId = document.getElementById("share_doctorid").value;
	var disctype = document.getElementById("disctype").value;
	var share_calamount = document.getElementById("share_calamount").innerHTML;
	var sharing_remark = document.getElementById("sharing_remark").value;
	var share_invoiceid = document.getElementById("share_invoiceid").value;
	var share_chargeid = document.getElementById("share_chargeid").value;
	var indiorall = document.getElementById("indiorall").value;
	var share_chargename = document.getElementById("share_chargename").value;
	var chargetotal = document.getElementById("share_chargetotal").innerHTML;
	if(doctorId=='0'){
		jAlert('error', "Please select doctor !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(share_calamount=='0'){
		jAlert('error', "Please enter amount or percentage to be share!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(sharing_remark==''){
		jAlert('error', "Please enter remark !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url = "savesharechargetodrStatement?clientId="+clientId+"&doctorId="+doctorId+"&disctype="+disctype+"&share_calamount="+share_calamount+"&sharing_remark="+sharing_remark+"&share_invoiceid="+share_invoiceid+"&share_chargeid="+share_chargeid+"&all_or_indi="+indiorall+"&share_chargename="+share_chargename+"&chargetotal="+chargetotal+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	req.onreadystatechange = saveShareChargeToDrRequest;
	req.open("GET", url, true); 
	req.send(null);
	}
	
}
function saveShareChargeToDrRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "Successfuly charges share!", 'Success Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			setTimeout(window.location.reload(), 6000);
		}
	}
} 


function shareChargeToDrEdit(id,totalamount,commtype,amount,practid,remark){
	document.getElementById("share_chargetotal").innerHTML = totalamount;
	document.getElementById("share_doctorid").className = "form-control chosen";
	document.getElementById("share_doctorid").value = practid;
	$("#share_doctorid").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true}); 
	
	document.getElementById("disctype").value = commtype;
	document.getElementById("share_calamount").innerHTML = amount;
	document.getElementById("sharing_remark").value = remark;
	document.getElementById("shareid").value= id;
	$('#sharecharge').modal('show');
}



function editShareChargeToDr(){
	var id = document.getElementById("shareid").value;
	var doctorId = document.getElementById("share_doctorid").value;
	var disctype = document.getElementById("disctype").value;
	var share_calamount = document.getElementById("share_calamount").innerHTML;
	var sharing_remark = document.getElementById("sharing_remark").value;
	if(doctorId=='0'){
		jAlert('error', "Please select doctor !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(share_calamount=='0'){
		jAlert('error', "Please enter amount or percentage to be share!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(sharing_remark==''){
		jAlert('error', "Please enter remark !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url = "editsharechargetodrStatement?id="+id+"&doctorId="+doctorId+"&disctype="+disctype+"&share_calamount="+share_calamount+"&sharing_remark="+sharing_remark+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	req.onreadystatechange = saveShareChargeToDrRequest;
	req.open("GET", url, true); 
	req.send(null);
	}
	
}
function saveShareChargeToDrRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "Successfuly charges updated!", 'Success Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			setTimeout(window.location.reload(), 6000);
		}
	}
} 
function shareChargesToDrNew(indiorall,invoiceid,chargeid,chargetotal,chargename){
	var url = "getsharechargetodrStatement?indiorall="+indiorall+"&invoiceid="+invoiceid+"&chargeid="+chargeid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = shareChargesToDrNewRequest;
	req.open("GET", url, true); 
	req.send(null);
}

function shareChargesToDrNewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("newshare_invoiceid").value = data[0];
			document.getElementById("newshare_chargeid").value = data[1];
			document.getElementById("newindiorall").value = data[2];
			document.getElementById("newshare_chargename").innerHTML = data[3];
			
			document.getElementById("newshare_chargetotal").innerHTML = data[4];
			document.getElementById("newshare_amount").innerHTML = data[5];
			document.getElementById("newshare_balance").innerHTML = data[6];
			document.getElementById("tbodysharecharge").innerHTML = data[7];
			document.getElementById("newshare_doctorid").value = '0';
			document.getElementById("newshare_amount").value = '';
			$('#newsharecharge').modal('show');
		}
	}
}

function newchangeShareDiscountType(){
	document.getElementById("newshare_calamount").innerHTML = '0';
	document.getElementById("newshare_amount").value='0';
}

function newcalculateShareAmount(val){
	var disctype = document.getElementById("sharetype").value;
	var share_chargetotal = Number(document.getElementById("newshare_balance").innerHTML);
	var total_share =0;
	if(disctype=='Percent'){
		if(val<=100 &&val>0){
			var share_charged = Number(document.getElementById("newshare_chargetotal").innerHTML);
			total_share = (share_charged*val)/100;
			if(total_share>share_chargetotal){
				document.getElementById("newshare_calamount").innerHTML = '0';
				jAlert('error', "Some %/Rs already shared !", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				document.getElementById("newshare_calamount").innerHTML = total_share;
			}
			
		}else{
			document.getElementById("newshare_amount").value='0';
			document.getElementById("newshare_calamount").innerHTML = '0';
			jAlert('error', "Please enter proper percentage !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
	}
	if(disctype=='Rupees'){
		if(val<=share_chargetotal &&val>0){
			total_share = val;
			document.getElementById("newshare_calamount").innerHTML = total_share;
		}else{
			document.getElementById("newshare_amount").value='0';
			document.getElementById("newshare_calamount").innerHTML = '0';
			jAlert('error', "Please entered value greater than charges !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
	}
}


function saveShareChargeToDrNew(){
	var clientId = document.getElementById("clientId").value;
	var doctorId = document.getElementById("newshare_doctorid").value;
	var disctype = document.getElementById("sharetype").value;
	var share_calamount = document.getElementById("newshare_calamount").innerHTML;
	var sharing_remark = document.getElementById("newsharing_remark").value;
	var share_invoiceid = document.getElementById("newshare_invoiceid").value;
	var share_chargeid = document.getElementById("newshare_chargeid").value;
	var indiorall = document.getElementById("newindiorall").value;
	var share_chargename = document.getElementById("newshare_chargename").innerHTML;
	var chargetotal = document.getElementById("newshare_chargetotal").innerHTML;
	var share_balance = document.getElementById("newshare_balance").innerHTML;
	var newshare_amount = document.getElementById("newshare_amount").innerHTML;
	var inputamount = document.getElementById("iptext").value;
	
	if(doctorId=='0'){
		jAlert('error', "Please select doctor !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(share_calamount=='0'){
		jAlert('error', "Please enter amount or percentage to be share!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(sharing_remark==''){
		jAlert('error', "Please enter remark !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url = "savesharechargetodrnewStatement?clientId="+clientId+"&doctorId="+doctorId+"&disctype="+disctype+"&share_calamount="+share_calamount+"&sharing_remark="+sharing_remark+"&share_invoiceid="+share_invoiceid+"&share_chargeid="+share_chargeid+"&all_or_indi="+indiorall+"&share_chargename="+share_chargename+"&chargetotal="+chargetotal+"&share_balance="+share_balance+"&newshare_amount="+newshare_amount+"&inputamount="+inputamount+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	req.onreadystatechange = saveShareChargeToDrNewRequest;
	req.open("GET", url, true); 
	req.send(null);
	}
	
}
function saveShareChargeToDrNewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "Successfuly charges share!", 'Success Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			setTimeout(window.location.reload(), 6000);
		}
	}
} 


function changeChargeQtyNew(chargeid,val) {
	
	if(val<0){
		jAlert('error', "Please Enter Valid Quantity!", 'Success Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("changeqtxt"+chargeid).value=1;
	}else{
	
	var total = document.getElementById("changeunitq"+chargeid).value;
	var totalamt = parseFloat(total)*val;
	document.getElementById("chargetotalamt"+chargeid).innerHTML = "Rs."+" "+totalamt;
	var url = "changeQtyStatement?&chargeid="+chargeid+"&val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = changeChargeQtyNewreq();
	req.open("GET", url, true); 
	req.send(null);
	}
}
function changeChargeQtyNewreq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			}
		}
}

function updateAllChargesQty() {
	 var ids="0";
	 var flag = false;
     $('.changeqtyclass').each(function() { 
		if(this.checked == true){
		     var i=this.value;
		     ids = ids+","+i;
		     flag = true;
		}  
	});
     if(!flag){
    	 jAlert('error', "Please select at least one checkbox !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
     }else{
    	 document.getElementById("allchargeids").value=ids;
    	 document.getElementById("chargeidform").submit();
     }
     
}

function changeAptmType1(chargeid,val){
	var url = "changechargesnameStatement?&chargeid="+chargeid+"&val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = changeapmttypereq();
	req.open("GET", url, true); 
	req.send(null);
}
function changeapmttypereq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			}
		}
}


function changeUnitCost(chargeid,val){
	var orignalcost=document.getElementById("originalunitcst"+chargeid).value;
	var regx=/^[0-9]+([,.][0-9]+)?$/g;
	var regexp = /^\S*$/;
	if(!regexp.test(val)){
		jAlert('error', "Unit cost Field is not empty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		document.getElementById("changeunitq"+chargeid).value=orignalcost;
	}else if(!regx.test(val)){
		jAlert('error', "Unit cost should be Number!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		document.getElementById("changeunitq"+chargeid).value=orignalcost;
	}else{
		
	
	var url = "changeUnitCostStatement?&chargeid="+chargeid+"&val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	var unitcost=document.getElementById("changeunitq"+chargeid).value;
	var qty=document.getElementById("changeqtxt"+chargeid).value;
	var tot=parseFloat(unitcost)*qty;
	if(unitcost>0){
		document.getElementById("chargetotalamt"+chargeid).innerHTML="Rs."+" "+parseFloat(tot);
	}
	req.onreadystatechange = changeUnitCostreq();
	req.open("GET", url, true); 
	req.send(null);
	}
}
function changeUnitCostreq(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			window.location.reload();
			}
		}
}
function changeAptmTypeCode1(chargeid,val){
	var url = "changeAptmTypeCodeStatement?&chargeid="+chargeid+"&val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = changeAptmTypeCode1req();
	req.open("GET", url, true); 
	req.send(null);
}
function changeAptmTypeCode1req(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			}
		}
}

function breakage(l){
	
		
		var chk=0;
		   if(l.checked==true){
			   
			   document.getElementById('hidebreakage').className="hidden";
			   
		   } else {
			   document.getElementById('hidebreakage').className="";
		   }
			   
			   
		 
	
	
}


function breakage1(valu){
	
	$('.hidll'+valu).each(function() { 
		this.innerHTML='';
	});
}


function openpatientpkgmaster(){
	var patientId=document.getElementById("clientId").value;
	openSamePopup("PackageMaster?clientid="+patientId+"");
	
}
function showPackageData(id){
	  var url = "pkgIpdDashboard?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPackageDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showPackageDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
		    var str=data.split("#");
			document.getElementById('pkgdtailbody').innerHTML = str[0];
			document.getElementById('pkgamtid').value =  str[1];
			document.getElementById('pkgfromdate').value=str[3];
			document.getElementById('pkgtodate').value=str[4];
			//for popup purpose
			document.getElementById('actualpkgfromdate').value=str[3];
			document.getElementById('actualpkgtodate').value=str[4];
			document.getElementById('days').value=str[5];
			document.getElementById('actualdays').value=str[5];
			
		}
	}
}
function validatepackage(){
	var data = document.getElementById('pkgamtid').value;
	if(data==''){
		jAlert('error', "Plz enter amount", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		return false;		
	}else if(data==''){
		jAlert('error', "Plz enter amount", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		return false;		
	}else{
		
		document.getElementById("selectpkgbtn").className='hidden';
		return true;
	} 
}

function setAutocharge(){
	
	   var clientid= document.getElementById("clientId").value;
	   var flag= document.getElementById("autocharge").checked;
	   if(flag==true){
		   flag="1";
	   } else {
		   flag="0";
	   }
	   if(flag==1){
		   var d=window.confirm("Are you sure you want to Start Auto Charge?"); 
		   if(d){
			   var url="switchautochargeStatement?clientid="+clientid+"&flag="+flag+"";
			   
			   if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = setAutochargeRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
		   }else{
			   var url="switchautochargeStatement?clientid="+clientid+"&flag="+flag+"";
			   
			   if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = setAutochargeRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
		   }
	   }else{
		   var url="switchautochargeStatement?clientid="+clientid+"&flag="+flag+"";
		   
		   if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setAutochargeRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	   }
	 
	
}
function setAutochargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			location.reload();
		}
	}
	
}

function refreshPkgData(ipdid,clientid){
	var url = "refreshPkgDataStatement?&ipdid="+ipdid+"&clientid="+clientid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
   isIE = true;
   req = new ActiveXObject("Microsoft.XMLHTTP");
}   


req.onreadystatechange =refreshPkgDataRequest;

req.open("GET", url, true); 
req.send(null);

}

function refreshPkgDataRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		 window.location.reload();	
	}
}
}

function confirmdelete(ipdid,chargeid){
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	   var url = "confirmdeleteStatement?ipdid="+ipdid+"&chargeid="+chargeid+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = confirmdeleteRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  return true;
	  }
	else
	  {
	  return false;
	  }
	
}

function confirmdeleteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			if(data=='0'){
				jAlert('error', "Invoice Already created for this charge", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				window.location.reload();	
			}
			 	
		}
	}
	}

function selectedipdid(){
	document.getElementById('detailfrm').submit();
}

function refreshAutoChargesData(clientid) {
	$("#dashboardloaderPopup").modal("show");
	opencPopup('updateautochargeStatement?clientId='+clientid+'');
}


function changepaytype(val,id,clientid){
	  var url = "changepaytypeStatement?chargeid="+id+"&val="+val+"&clientid="+clientid+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changepaytypeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  return true;
}

function changepaytypeRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					jAlert('success', 'Payee Changed!!.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
		window.location.reload();
		
		}
		}
}
function validatedisc(id,val,sts) {
//	var val=document.getElementById(' id').value;
	var disctype=document.getElementById('disctype1').value;
	var discount=document.getElementById('discount'+id+"_"+val).value;
	var unitcharges=document.getElementById('unitcharges'+id+"_"+val).value;
	
	if(disctype==0 || disctype==""){
		if(discount>100){
			document.getElementById('discount'+id+"_"+val).value='';
			document.getElementById('masterdisc'+id).value='';
			jAlert('error', "Plz Enter Discount Below 100% or Change Discount Type", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 3000); 
		}
	}else{
		if(Number(discount)>Number(unitcharges)){
			document.getElementById('discount'+id+"_"+val).value='';
			document.getElementById('masterdisc'+id).value='';
			jAlert('error', "Plz Enter Discount Below Unit Cost or Change Discount Type", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 3000); 
		}
	}
	if(discount==""||Number(discount)==0){
		document.getElementById("indvdiscbtn").disabled=true;
		
	}else{
		document.getElementById("indvdiscbtn").disabled=false;
		
	}
	if(sts==0 || discount==""){
		document.getElementById("masterdisc"+id).disabled=false;
	}else{
		document.getElementById("masterdisc"+id).disabled=true;
	}
}


function diffoftwodate() {
	var fromdate=document.getElementById('pkgfromdate').value;
	var todate=document.getElementById('pkgtodate').value;
	var actualfromdate=document.getElementById('actualpkgfromdate').value;
	var actualtodate=document.getElementById('actualpkgtodate').value;
	var days=document.getElementById('days').value;
	var actualdays=document.getElementById('actualdays').value;
	var arrayfdate = fromdate.split("-");
	var arraytdate = todate.split("-");
	var fromdate1=arrayfdate[1]+"/"+arrayfdate[0]+"/"+arrayfdate[2];
	var todate1=arraytdate[1]+"/"+arraytdate[0]+"/"+arraytdate[2];
// To set two dates to two variables 
var date1 = new Date(fromdate1); 
var date2 = new Date(todate1); 

//To calculate the time difference of two dates 
var Difference_In_Time = date2.getTime() - date1.getTime(); 

//To calculate the no. of days between two dates 
var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24); 
if(Number(Difference_In_Days)>Number(actualdays)){
	var r=confirm("Your Duration exceeding with actual package duration ("+actualdays+" Days), Want to Proceed?");
	if (r==true)
	  {
	   
		document.getElementById('days').value=Difference_In_Days;
	               
	    
	 
	  }
	else
	  {
		document.getElementById('pkgfromdate').value = actualfromdate;
		document.getElementById('pkgtodate').value  = actualtodate;
		document.getElementById('days').value=actualdays
	  }
	
}else if(Number(Difference_In_Days)<0){
	jAlert('error', "Duration days Should not be nagative", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, 3000); 
	document.getElementById('pkgfromdate').value = actualfromdate;
	document.getElementById('pkgtodate').value  = actualtodate;
	document.getElementById('days').value=actualdays
}else{
	document.getElementById('days').value=Difference_In_Days;
}
//To display the final no. of days (result) 
//document.write("Total number of days between dates  <br>"
//           + date1 + "<br> and <br>" 
//           + date2 + " is: <br> " 
//           + Difference_In_Days); 

}

function validatediscnewmetho(val,val1) {
	
	$('.dsdsclasss').each(function() { 
		if(document.getElementById("discount"+val+"_"+this.value)){
			document.getElementById("discount"+val+"_"+this.value).value = val1;
	 		if(val1>0){
	 		document.getElementById("discount"+val+"_"+this.value).readOnly=true;
	 		}else{
	 			document.getElementById("discount"+val+"_"+this.value).readOnly=false;
	 		}
	 		validatedisc(val,this.value,0);
		}
 		
 		
 	});
	
}
function validatepayby(val,val1) {
	var clientid=document.getElementById("clientId").value;
	$('.dsdsclasss').each(function() { 
		if(document.getElementById("paybyclient"+val+"_"+this.value)){
			document.getElementById("paybyclient"+val+"_"+this.value).value = val1;
			var chargeid=document.getElementById("chargeiddd"+val+"_"+this.value).value;
			
			changepaytype(val1,chargeid,clientid);
	 		
		}
//		if(document.getElementById("chargeid"+val+"_"+this.value)){
			
//		}
 		
 	});
	
}

function updatechargedata(){
	
	var newchargename= document.getElementById("newchargename").value;
	if(newchargename==''){
		  jAlert('error', 'Please Enter Charge Name', 'Success Dialog');
		            setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	}else{
		 document.getElementById("savechargefrm").submit();
	}
	
}



function renderpopup(chargeid,chargename,charges){
	
	document.getElementById("chrge_name").innerHTML=chargename;
	document.getElementById("charge").value=charges;
	document.getElementById("charge_id").value=chargeid;
	
	$('#rnderpopup').modal('show');
	 
}

function saverender() {
	
	//var patientname=document.getElementById("patientnm").value
	var chargename=document.getElementById("chrge_name").innerHTML;
	var charge=document.getElementById("charge").value;
	var doctor=document.getElementById("doctor").value;
	var chargeid=document.getElementById("charge_id").value;
	
	
	
	if (doctor == '') {

		jAlert('error', "Please select doctor!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}
	else{
	
	     $("#rnderpopup").modal( "show" );
	     var url ="saverenderStatement?doctor="+doctor+"&charge="+charge+"&chargename="+chargename+"&chargeid="+chargeid+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = saverenderRequest;
		req.open("GET", url, true);
		req.send(null);
		
	}
		
	}

function saverenderRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			$("#baselayout1loaderPopup").modal("hide");
			
		if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
				window.location.reload();
                     // success
			}
			
         }
		
     }
	
  }

function renderPayment(chargeid,totalamt){
	
	
	/*document.getElementById("total_amt").value=totalamt;
	document.getElementById("payment_charge_id").value=chargeid;*/
	
	$('#rnderpaymentpopup').modal('show');
	 
}

/*function selectAllCheckBox(){
	
	 $('#selecctall').click(function(event) {  //on click 
         if(this.checked) { // check select status
             $('.case').each(function() { //loop through each checkbox
                 this.checked = true;  //select all checkboxes with class "checkbox1"               
             });
         }else{
             $('.case').each(function() { //loop through each checkbox
                 this.checked = false; //deselect all checkboxes with class "checkbox1"                       
             });         
         }
     });
}*/

//$(document).ready(function(){
	    
          
	         // var tt= Number(document.getElementById("tthidden").value);

		     // document.getElementById("word").innerHTML=convertNumberToWords(tt);});

