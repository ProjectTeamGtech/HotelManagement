var startorendstd='';
var ipdclientname;
var ipdpractitionername;
var ipdpractmob = '';
var ipdclientid;
var ipdpractitionerid;
var ipdcharge = 0;
var ipdtpid;
var ipdaddmissionid = 0;
var ipdwhopay = '';
var editAppointId = 0;
var wraperDivId = '0';
var editClientName  = '';
var newaction='0';
var balance=0;
var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;
var ipdbalance = 0;
var ipdtreatmentepisodeid = 0;
var idis_initiate_time = '';
var idis_initiate_status = '';
var imagename='';
var idis_form_time = '';
var idis_form_status = '';
var idis_mdicine_time = '';
var idis_mdicine_status = '';
var idis_bill_time = '';
var idis_bill_status = '';
var idis_nursing_time = '';
var idis_nursing_status = '';
var ward='';
var opdid=0;
var opdclientid=0;

function showAddchargePopup(){
	var url = "truncateIpdDashboard";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showAddchargePopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
	
}

function showAddchargePopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#addchargepopup').modal( "show" );
			showPackageListAjax();
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
			 document.getElementById("mannualcharge").value = $("#ipdpackage option:selected").text();
			 
			 calcmanualcharge();
		}
	}
}

function showPackageListAjax(){
	var url = "pkglistIpdDashboard?admissionid="+ipdaddmissionid+"&ipdclientid="+ipdclientid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showPackageListAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function showPackageListAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('patientpkgspan').innerHTML = req.responseText;
		}
	}
}


function setAddmissionidInSession(admissionid){
	//var url = "admissionIpdDashboard?admissionid="+admissionid+" ";
	var url = "admissionBookAppointmentAjax?admissionid="+admissionid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAddmissionidInSessionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function  setAddmissionidInSessionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}
var pidbcode="",uhidbcode="",pnamebcode="";
var abid="";
function showipdpopup(bedid,cid,pid,conid,cname,pname,dob,town,admissionid,age,whopay,tpid,tpname,pmob,bedname,wardname,balance,treatmentepisodeid,
dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status, dis_mdicine_time, 
dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, dis_nursing_status,image,action,abrivationid,ipdseqno){
	
	pidbcode=pid;
	uhidbcode=abrivationid;
	pnamebcode=cname;
	
	ipdtreatmentepisodeid = treatmentepisodeid;
	newaction=action;
	//discharge details
	 idis_initiate_time = idis_initiate_time;
	 idis_initiate_status = dis_initiate_status;
	 idis_form_time = dis_form_time;
	 idis_form_status = dis_form_status;
	 idis_mdicine_time = dis_mdicine_time;
	 idis_mdicine_status = dis_mdicine_status;
	 idis_bill_time = dis_bill_time;
	 idis_bill_status = dis_bill_status;
	 idis_nursing_time = dis_nursing_time;
	 idis_nursing_status = dis_nursing_status;
	
	setAddmissionidInSession(admissionid);
	
	editClientName  = cname;
	 ipdclientname = cname;
	 ipdpractitionername = pname;
	 ipdclientid = cid;
	 ipdpractitionerid = pid;
	 ipdtpid = tpid;
	 ipdaddmissionid = admissionid;
	 ipdwhopay =  whopay;
	 ipdpractmob = pmob;
	 ipdbalance = balance;
	 imagename=image;
	 
	 	patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
	 
	  document.getElementById("ipddaytodayheaderaction").innerHTML = '<a href="#" onclick="openClientPrintPopup('+cid+')" >'+cname+'</a> <a href="#" onclick="openEditClientPrintPopup('+cid+')" title="Edit Client Record" ><img src="_assets/img/edit.png"></a> <a href="#" title="Log" onclick="openClientLogPopup('+cid+')"><img src="_assets/img/log.png"></a>  <a href="#" onclick="redircttoNewEmr('+cid+','+pid+','+conid+','+admissionid+')" title="EMR" ><img src="_assets/img/emr.png"></a>  <a href="#" onclick="openAssesmentFormPopup('+cid+','+pid+','+editAppointId+')" title="Assessment Form" ><img src="_assets/img/asmnt.png"></a>';
	ward=wardname;
	document.getElementById("ipdbednoid").innerHTML = wardname + ' / ' + bedname;
	document.getElementById("ipdclientnameid").innerHTML = cname;
	document.getElementById("ipddobid").innerHTML = dob;
	document.getElementById("ipdageid").innerHTML = age;
	document.getElementById("ipdtownid").innerHTML = town;
	document.getElementById("ipddoctornameid").innerHTML = pname;
	//document.getElementById("addchargehead").innerHTML = cname;
	document.getElementById("ipdadmissionid").innerHTML = ipdseqno;
	//document.getElementById("ipdpatientid").innerHTML = cid;
	document.getElementById("ipdpatientid").innerHTML = abrivationid;
	abid=abrivationid;
	//set for add charges
	document.getElementById("compPractName").innerHTML = pname + 'at' 
	
	document.getElementById("apmtchargelabel").style.display='none';
	document.getElementById("chargefordiv").style.display='none';
	document.getElementById("costdiv").style.display='none';
	
	$(document.getElementById('payeediv')).css('margin-top', '-42px');
	
	//set payee
	if(whopay=='Client'){
		document.getElementById("payee").innerHTML = 'Self';
		document.getElementById("chargeTo1").innerHTML = cname;
		document.getElementById("payBuy").checked = true;
		document.getElementById("payBuynew").checked = true;
		
	}else{
		document.getElementById("payee").innerHTML = 'Third Party';
		document.getElementById("chargeTo1").innerHTML = tpname;
		document.getElementById("payBuy1").checked = true;
		document.getElementById("payBuy1new").checked = true;
	}
	if(imagename==''){
	    document.getElementById("timagename").innerHTML="<img src='popicons/avatar2.png' style='width:100%;'>";   
	}else {
	
	   document.getElementById("timagename").innerHTML="<img src='liveData/"+imagename+"' style='width:100%;'>";
	}
	
	
	if(tpid==''){
		document.getElementById("payBuy1").disabled = true;
	}else{
		document.getElementById("payBuy1").disabled = false;
	}
	
	
	//setAdditionalChargeTypeAjax(whopay);
	
	
	document.getElementById("balanceid").value=ipdbalance;
	getNurseNotes(admissionid);
	
	/*setPatientIPDJsonData(cid,admissionid,treatmentepisodeid);
	
	setTimeout(function() {
		
	}, 4000*2);*/
	
	if(treatmentepisodeid==0 || pid==''){
		openeditipd();
	}else{
		counsultationcount(ipdaddmissionid);
		$('#ipdpopup').modal( "show" );
	}
}

function setAdditionalChargeAjax1(apmtTypeid){
	
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
				document.getElementById("charge").readOnly = true;
			}else{
				document.getElementById("charge").readOnly = false;
			}
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
			setCashDesk();
		}
	}
   
}

function setChargeAmount(){
	
	var chargetype = document.getElementById("chargeTYpe").value;
	var quantity = document.getElementById("quantity").value;
	var charge = document.getElementById("hdncharge").value;;
	var mannualcharge = document.getElementById("mannualcharge").value;
	var packageid = document.getElementById("packageid").value;
	var date = document.getElementById("date").value;
	
	var payby = $("input[name='payBuy']:checked").val();
	
	var masterchargetype =  $("#masterchargetype option:selected").text();
	
	var markappointment = 1;
	
	//akash 02 feb 2018
	var visitingconsulatntdr =0;
	var compulsary_con=document.getElementById("compulsaryconsultant").value;
	var isindisharecharge = document.getElementById("isindisharecharge").value;
	if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'||compulsary_con=='1'){
		visitingconsulatntdr = document.getElementById('consultantdr').value;
	 }
	
	var url = "savechargeIpdDashboard?ipdclientname="+ipdclientname+"&ipdpractitionername="+ipdpractitionername+"&ipdclientid="+ipdclientid+"&ipdpractitionerid="+ipdpractitionerid+"&chargetype="+chargetype+"&charge="+charge+"&payby="+payby+"&markappointment="+markappointment+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&date="+date+"&packageid="+packageid+"&visitingconsulatntdr="+visitingconsulatntdr+"&isindisharecharge="+isindisharecharge+" ";


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

	function setChargeAmountRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("chargeTotalajax").innerHTML = req.responseText;
				   document.getElementById('packageid').value="";
					setCashDesk();		
				
				
				
			}
		}
	}
	
	
	function setCashDesk(){
		var selectedUser = ipdclientid;
		var cookiecommencing = document.getElementById("date").value;
		
		if(cookiecommencing!=''){
		 
		     var str= cookiecommencing.split("-");
		     var yymmdd= str[2]+"-"+str[1]+"-"+str[0];
		     cookiecommencing= yymmdd;
		}
		
		var cookieSelectedAppointmentid = 0;
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		//var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
		//  2018-May-04
		var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
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
						setCashDesk();
					
					
					
				}
			}
		}

		
		
		function createChargeAndUpdateAccount(action){
			gcash = action;
			
			var clientid = ipdclientid; 
			var practitionerid = ipdpractitionerid;
			var clientName = ipdclientname;
			var practitionerName = ipdpractitionername;
			var appointmentid = '0';
			var tratmentepisodeid = '0';
			var treatmenntsessions = '0';
			var location = document.getElementById('complocationid').value;
			var ipd = 1;
			var date= document.getElementById("date").value;
			
			if(location==0){
				jAlert('error', 'Please select location.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				
				$('#baselayout1loaderPopup').modal( "show" );
				var ginventeredcharges = '0~';
				 $('.invunitchargecase').each(function() { //loop through each checkbox
					 ginventeredcharges = ginventeredcharges + this.value + '~';               
	             });  
				var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&date="+date+"&ginvstid="+ginvstid+"&ginventeredcharges="+ginventeredcharges+" ";
				   
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = createChargeAndUpdateAccountRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
			}
			
			
			
		}

		function createChargeAndUpdateAccountRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var invoiceid = req.responseText;
					
					if(gcash=='cash'){
						setInstantCashDesk(invoiceid);
					}else{
						$('#addchargepopup').modal( "hide" );
						
						$('#baselayout1loaderPopup').modal( "hide" );
						
						jAlert('success', 'Charge added successfully.', 'Success Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}
					
					
				}
			}
		}
		
		
		function openeditipdnewmethod(){
			opencPopup('editCommonnew?selectedid='+ipdaddmissionid+'&action='+newaction);
		}
		
		function openeditipd(){
			openIpdPopup('editCommonnew?selectedid='+ipdaddmissionid+'&action='+newaction);
		}
		
		function openadmitpatient(actionfrom){
			openIpdPopup('inputIpd?selectedid='+ipdaddmissionid+'&action=0&clId='+ipdclientid+'&actionfrom='+actionfrom);
		}
		function leftcasualtyoatient(){
			opencPopup('leftIpd?selectedid='+ipdaddmissionid+'&action=1 ');
		}

//redirect to emr 
		
		function redircttoNewEmr(clientid,practitionerid,conditionid,ipdaddmissionid){
		
			
			var t = 'formtarget';

			document.getElementById('clientnameApmt').value = clientid;
			document.getElementById('diaryUserApmt').value = practitionerid;
			document.getElementById('conditionsApmt').value = conditionid;
			document.getElementById('hdnaction').value = 'emr';
			document.getElementById('apmtId').value = 0;
			document.getElementById('emripdid').value = ipdaddmissionid;
			/* document.getElementById('getPatientRecord').submit(); */

			var left = (screen.width / 2) - (700 / 2);
			var top = (screen.height / 2) - (550 / 2);
			
			var oldwindow = window.open("", t,
					"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
							+ ",top=" + 50);
			
			oldwindow.focus();

			document.getElementById('getPatientRecord').submit();
			
			
		}
		
		
		function openAssesmentFormPopupIpd(){
			openAssesmentFormPopup(ipdclientid,ipdpractitionerid,editcondition_id);
		}
		
		function openClientPrintPopupIpd(){
			openClientPrintPopup(ipdclientid);
		}
		
		function openEditClientPrintPopupIpd(){
			//openEditClientPrintPopup(ipdclientid);
			openEditClientPrintPopupold(ipdclientid);
		}
		
		function openClientLogPopupIpd(){
			openClientLogPopup(ipdclientid);
		}
		function showEmrWindoe(){
			
			redircttoNewEmr(ipdclientid,ipdpractitionerid,editcondition_id,ipdaddmissionid);
		}
		
		function showPriscWithSpecialization(){
			
			var url="getprisctemplateIpd?doctorid="+ipdpractitionerid+"";
			
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showPriscWithSpecializationRequest;
		    req.open("GET", url, true); 
		    req.send(null);
		          	
		}
		
		function showPriscWithSpecializationRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("prisctemplatediv").innerHTML= req.responseText;
					 $("#templatename").trigger("chosen:updated");
					 $(".chosen").chosen({allow_single_deselect: true});
					 showipdpriscwindow();
				}
			}
		}
		
		
		
		
		
		function showipdpriscwindow(){
			sessionStorage.setItem("location", "ipd");
			repeatePriscDateAjax(ipdclientid,ipdpractitionerid,editcondition_id);
		}
		
		function showipdnursingwindow() {
		   repeateNursingAjax(ipdclientid,ipdpractitionerid,editcondition_id,ipdaddmissionid);
		}
		
		function immunizationopen(){
			  openPopup('newimmunizationchartSlotAvailable?clientId='+patientId+'');
			
		}
		function immunizationopen1(){
			  openPopup('newimmunizationchartSlotAvailable?clientId='+patientId+'&type=1');
			
		}
		
		
		function showInvestigationwindow(){
			
			 patientId = ipdclientid;
			 diaryuserId = ipdpractitionerid;
			 $('#investigationpopup').modal( "show" );
			 removeSession();
				
				
			 showInvestigation();
			 
		}
		
		
		function filterCharges(chargetype){
		  	var url = "chargeIpd?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+ipdtpid+"&ipdaddmissionid="+ipdaddmissionid+" ";
			  
			 
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
					
					 document.getElementById("additionalChargeAjax").innerHTML = req.responseText;
					 
					  $("#chargeTYpe").trigger("chosen:updated");
    			 	  $(".chosen").chosen({allow_single_deselect: true});
					 
				}
			}
		}
		
		var newchargetype="";
			function filterIpdCharges(chargetype){
				newchargetype = chargetype;
		  	/*var url = "chargeIpd?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+ipdtpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+ipdclientid+" ";*/
				var url = "chargeBookAppointmentAjax?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+ipdtpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+ipdclientid+" ";
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = filterIpdChargesRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				
		}
		
		function filterIpdChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					var data= req.responseText;
					var data1= data.split("!@");
					
					 document.getElementById("additionalChargeAjax").innerHTML =data1[0];
					 //lokesh 22/11/2018
					 document.getElementById("compulsaryconsultant").value =data1[1];
					 
					  $("#chargeTYpe").trigger("chosen:updated");
    			 	  $(".chosen").chosen({allow_single_deselect: true});
					 
    			 	  
    			 	 if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge'||data1[1]=='1'){
    			 		 //akash 20 July 2018
        			 	 //setvisitingdrlist();
    			 		 //lokesh on the req of praful sir 29/11/2018
    			 		 setvisitingdrlist();
    			 	 }else{
    			 		document.getElementById("visitingconsltantdiv").innerHTML = "<input type='hidden' id='consultantdr' value='0'>";
    			 	 }
    			 	 //akash 20 July 2018
    			 	/* setvisitingdrlist();*/
				}
			}
		}
		
		
		

		
		function showWardBed(id){
			
			 /*document.getElementById("ipddashboardfrm").submit();*/
			opencPopup('IpdDashboard?action=0&directwardid='+id);
		}
		
		
		function redirectToViewAcc(){
			document.getElementById('viewAccClientid').value = ipdclientid;
			document.getElementById('viewAccclientname').value = ipdclientname;
			document.getElementById('viewAccPayby').value = ipdwhopay;
			document.getElementById('viewAccLocationid').value = "All";
			document.getElementById('viewAccthirdparty').value = "All";
			document.getElementById('viewAcctransactionType').vlue = "All";
			document.getElementById('viewAccfromDateid').value = "";
			document.getElementById('viewAcctoDateid').value = "";
			document.getElementById('accipdid').value=ipdaddmissionid;
			
			
			var t = 'formtarget';

			
			
			var left = (screen.width / 2) - (700 / 2);
			var top = (screen.height / 2) - (550 / 2);
			var oldwindow = window.open("", t,
					"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
							+ ",top=" + 50);
			
			oldwindow.focus();

			
			
			
			document.getElementById('view_acc_frm').submit();
			


			return true;

		}
		
		
		
		function redirectToDailynote(){
			document.getElementById('noteClientid').value = ipdclientid;
			document.getElementById('noteAccPayby').value = ipdwhopay;
			/*document.getElementById('viewAccclientname').value = ipdclientname;
			
			document.getElementById('viewAccLocationid').value = "All";
			document.getElementById('viewAccthirdparty').value = "All";
			document.getElementById('viewAcctransactionType').vlue = "All";
			document.getElementById('viewAccfromDateid').value = "";
			document.getElementById('viewAcctoDateid').value = "";*/
			document.getElementById('noteipdid').value=ipdaddmissionid;
			document.getElementById('isnote').value = "0";
			
			var t = 'formtarget';

			
			
			var left = (screen.width / 2) - (700 / 2);
			var top = (screen.height / 2) - (550 / 2);
			var oldwindow = window.open("", t,
					"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
							+ ",top=" + 50);
			
			oldwindow.focus();

			
			
			
			document.getElementById('daily_note_frm').submit();
			


			return true;

		}
		
		//set redirectToCreateCharge
		function redirectToCreateCharge(){
			
			//alert(editwhopay);
			document.getElementById('accountChargeClientid').value = ipdclientid;
			document.getElementById('accountchargethirdparty').value = "All";
			document.getElementById('accountchargetransactionType').vlue = "All";
			document.getElementById('accountsLocationid').value = "All";
			document.getElementById('accountclientid').value = ipdclientname;
			document.getElementById('accountpayby').value = ipdwhopay;
			
			
				var t = 'formtarget';


				
				var left = (screen.width / 2) - (700 / 2);
				var top = (screen.height / 2) - (550 / 2);
				var oldwindow = window.open("", t,
						"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
								+ ",top=" + 50);
				
				oldwindow.focus();
				
				
				
				
				document.getElementById('accountchargefrm').submit();
			
			
			
			
		}
		
		function redirectToRecordPayment(){
			
			document.getElementById('accountpaymentClientid').value = ipdclientid;
			document.getElementById('accountpaymentthirdparty').value = "All";
			document.getElementById('accountpaymenttransactionType').vlue = "All";
			document.getElementById('accountspaymentLocationid').value = "All";
			document.getElementById('accountpaymentclientid').value = ipdclientname;
			document.getElementById('accountspaymentfromDateid').value = "";
			document.getElementById('accountspaymentfromDateid').value = "";
			document.getElementById('accountPaymentPayby').value = ipdwhopay;
			
		var t = 'formtarget';


			
			var left = (screen.width / 2) - (700 / 2);
			var top = (screen.height / 2) - (550 / 2);
			var oldwindow = window.open("", t,
					"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
							+ ",top=" + 50);
			
			oldwindow.focus();
			
			
			document.getElementById('accountpaymentfrm').submit();
			
			
			
		}
		
		function showcrddebit(){
			openEmrPopup('crAdditional?clientid='+patientId+'')
		}

		function showipdprintpoup(){
			openEmrPopup('printCommonnew?selectedid='+ipdaddmissionid+'');
		}
		
		function showdischargepoup(){
		$('#initialdischarge').modal( "hide" );
				
			/*var whopay = ipdwhopay;
			var bal = ipdbalance;
			if(whopay=='Client'){
				if(parseFloat(bal)>0){
					//document.getElementById("edchkDischarge").checked = false;
					jAlert('error', "Final Invoicing still not Settled.Please complete it before discharging!!.", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}else{
					openEmrPopup('dischargeIpd?selectedid='+ipdaddmissionid+'');
				}
			}else{
				openEmrPopup('dischargeIpd?selectedid='+ipdaddmissionid+'');
			}*/
			openEmrPopup('dischargeCommonnew?selectedid='+ipdaddmissionid+'');
			
		}
		
		function sendsmsipdpopupopen(){
			document.getElementById('smsuserNameletter').value = ipdpractitionername;
			document.getElementById('smsmobile').value  = ipdpractmob;
			document.getElementById('smstxt').value  = ipdclientname;
			//document.getElementById('alldataprisctable').value 
			
			$('#semdsmspopup').modal( "show" );
		}
		
		
		
		var priscid;
		var dosecolname;
		var doseval;
		
		function toggleConfirmation(id,colname,toglevalue){
			var savetogle = '';
			if(toglevalue==true){
				savetogle = false;
			}else{
				savetogle = true;
			}
			
			priscid=id;
			dosecolname=colname;
			doseval=toglevalue;
			
            var url = "prisctogleIpdDashboard?selectedid="+id+"&colname="+colname+"&savetogle="+savetogle+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = toggleConfirmationRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		function toggleConfirmationRequest(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					updateDoseGiven(priscid,dosecolname,doseval);
					//document.getElementById('alldataprisctable').innerHTML = req.responseText;
				}
			}

		}
		
		function updateDoseGiven(id,colname,savetogle) {
			
			 var url = "updatedoseIpdDashboard?selectedid="+id+"&colname="+colname+"&savetogle="+savetogle+"";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateDoseGivenRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   
		
		}
		
		function updateDoseGivenRequest() {
			
			if (req.readyState == 4) {
				if (req.status == 200) {					
					
				}
			}
		}
		
		var nursingid="";
		var dosecolname1="";
		var doseval1="";
		function togglenursingConfirm(id,colname,toglevalue){
			var savetogle = '';
			if(toglevalue==true){
				savetogle = false;
			}else{
				savetogle = true;
			}
			
			nursingid=id;
			dosecolname1=colname;
			doseval1=toglevalue;
			
            var url = "nursingtogleIpdDashboard?selectedid="+id+"&colname="+colname+"&savetogle="+savetogle+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = togglenursingConfirmRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		
		function togglenursingConfirmRequest(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					updateNursingDoseGiven(nursingid,dosecolname1,doseval1);
					//document.getElementById('alldataprisctable').innerHTML = req.responseText;
				}
			}

		}
		
		
		function updateNursingDoseGiven(id,colname,savetogle) {
			
			 var url = "updatenursingdoseIpdDashboard?selectedid="+id+"&colname="+colname+"&savetogle="+savetogle+"";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateNursingDoseGivenRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   
		
		}
		
		function updateNursingDoseGivenRequest() {
			
			if (req.readyState == 4) {
				if (req.status == 200) {					
					
				}
			}
		}
		
		
		
		function problem_listing() {
			
			   openEmrPopup('ProblemListing?clientid='+patientId+'&ipdid='+ipdaddmissionid+'');
				
			}
		
		function treatment_sheet() {
			openEmrPopup('treatmentsheetIpdDashboard?clientid='+patientId+'&ipdid='+ipdaddmissionid+'');
		}
		
		
		function getbellcolor() {
			 var url = "getbellcolorBookAppointmentAjax";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getbellcolorRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   
		}
		
		
      function getbellcolorRequest() {
   
   		if (req.readyState == 4) {
    		if (req.status == 200) {     
     
     		var list=req.responseText;
     
           	var templist=list.split("/");

           var count=templist[templist.length-1];
           for(var i=0;i<templist.length;i++) {
            
                var ipdandcolor= templist[i].split("-");
                var ipdid=ipdandcolor[0];
                var color=ipdandcolor[1];
                var rowid=ipdandcolor[2];
                if(color=="#fff"){
                  document.getElementById('s'+ipdid+'').style.color="#fff";
                        
                        document.getElementById('m'+ipdid+'').style.color="#fff";
                        document.getElementById(rowid).style.background="#fff";
                }
                else if(color=="Orange") {
                        document.getElementById('s'+ipdid+'').style.color="orange";
                        document.getElementById('m'+ipdid+'').style.color="orange";
                        document.getElementById('c'+ipdid+'').style.color="orange";
                        document.getElementById(rowid).style.background="orange";
                      } 
                      else if(color=="Red") {
                      document.getElementById(rowid).style.background="red";
                       document.getElementById('s'+ipdid+'').style.color="red";
                       document.getElementById('s'+ipdid+'').innerHTML="<i class='fa fa-bell faa-ring animated faa-slow'></i>";
                       document.getElementById('m'+ipdid+'').style.color="red";
                       document.getElementById('m'+ipdid+'').innerHTML="<i class='fa fa-bell faa-ring animated faa-slow'></i>";
                       
                       document.getElementById('c'+ipdid+'').style.color="red";
                       document.getElementById('c'+ipdid+'').innerHTML="<i class='fa fa-bell faa-ring animated faa-slow'></i>";
                      
                       var audio = new Audio('sound/Bell-ringing-church-bell.mp3');
                       audio.play();
                       
                      }
                document.getElementById("count").innerHTML=count;
           }
     
    }
   }
  }
		
		
		function getfromdaysSheet(date) {
  
      document.getElementById("datetime").value=date;
      document.getElementById("formdemoredirect").submit();
             
  }
  
  var isOncharge=false;
  var stdChargestotal="";
  
  function getstdcharge(ipdid,clientid,stdids,isStatus,wardid) {
  
                isOncharge=isStatus;
                patientId=clientid;
                ipdaddmissionid=ipdid;
                stdChargestotal=stdids;

                var url="getclientstdchargedataIpdDashboard?wardid="+wardid+"&clientid="+clientid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getstdchargeRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);      
    
   
  }
  
  
  function getstdchargeRequest() {
  
      if (req.readyState == 4) {
         if (req.status == 200) {     
              
             var data=req.responseText;
             document.getElementById("resultsearch").innerHTML=data;  
             autoCharge(ipdaddmissionid,patientId,stdChargestotal,isOncharge);
       }
     }
  }
  
  
  
  function autoCharge(ipdid,clientid,stdids,isStatus) {
               
                isOncharge=isStatus;
                patientId=clientid;
                ipdaddmissionid=ipdid;
    
                var ss;
                if(isStatus==true) {
                   ss=stdids.split(",");
                   for(var i=0;i<ss.length;i++) {
                      
                        if(ss[i]=="0"){
                            continue;
                        }
                         
                        document.getElementById("ch"+ss[i]).checked=true;
                        
                   }
                   
                              
                }      
 
                var url="getclientdataIpdDashboard?ipdid="+ipdid+"&clientid="+clientid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = autoChargeRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   
  }
  
  function autoChargeRequest() {
  
      if (req.readyState == 4) {
         if (req.status == 200) {     
              
             var data=req.responseText;
             var str=data.split("~");
             document.getElementById("updatestdcharge").innerText=str[0];
             $('#addautocharge').modal( "show" );
             
       }
     }
  }
  
  
  var tempscharges="0";
  
  function updatestandardCharges() {
               
                tempscharges="0";
                var unselected="0";
                
                $('.scase').each(function() { //loop through each checkbox
		  		if(this.checked==true){
			  		tempscharges = tempscharges + ',' + this.value;   
		  		} else {
		  		    unselected = unselected + ',' + this.value;
		  		}
        		});
        		var stddate= document.getElementById("stddate").value; 
        		var starthour= document.getElementById("starthour").value;
        		var startminute= document.getElementById("startminute").value;
        		var enddate= document.getElementById("enddate").value;
        		var endhour= document.getElementById("endhour").value;
        		var endminute= document.getElementById("endminute").value;
  
                var url="updatestdchargeIpd?ipdid="+ipdaddmissionid+"&clientid="+patientId+"&standardids="+tempscharges+"&unselected="+unselected+"&stddate="+stddate+"&starthour="+starthour+"&startminute="+startminute+"&enddate="+enddate+"&endhour="+endhour+"&endminute="+endminute+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updatestandardChargesRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
  
  }
  
  function updatestandardChargesRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
                
                  //   updateAccountinvoice(tempscharges,ipdaddmissionid,patientId);
                     
                      $('#addautocharge').modal( "hide" );
                     jAlert('success', 'Auto Charge Raised successfully.', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
                     
                    document.location.reload();
                     
              }
         }
  }
  
  
  function updateAccountinvoice(stndchrgid,ipdid,clientid) {
    
                var url="updateAccountCompleteApmt?standard_chargeid="+stndchrgid+"&ipdid="+ipdid+"&clientid="+clientid+"&appointmentid=0";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateAccountinvoiceRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
  
  }
  
  function updateAccountinvoiceRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
               
                      
              }
         }
  }
  
  
  function getvisitingconsult(ipdid,clientid) {
     
      ipdaddmissionid=ipdid;
      patientId=clientid;
  
     			var url="showvisitingconsultIpdDashboard?clientid="+clientid+"&ipdid="+ipdid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getvisitingconsultRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
               
  }
  
   function getvisitingconsultRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
                       var data=req.responseText;
                       var str=data.split("~"); 
                       document.getElementById("lblvisiting").innerText=str[0];
                       document.getElementById("tbvisited").innerHTML=str[1];
                        $('#visitingconsult').modal( "show" );          
              }
         }
  }
  
  
  
  function addvisitingconsult() {
  
       			  document.getElementById("visitid").value="0"; 
               //   document.getElementById("visitingConsultDoctors").value=""; 
                  //document.getElementById("vdate").value="";
                  document.getElementById("time").value="";
                  document.getElementById("fees").value="";  
                  
                  document.getElementById("clientname").className="";
                  document.getElementById("clientname").disabled=false;
                  document.getElementById("clientname").className="form-control showToolTip chosen-select";
                  $("#clientname").trigger("chosen:updated");
                  $(".chosen-select").chosen({allow_single_deselect: true});
                  document.getElementById("tds").value=10;
       $('#visitingconsult').modal( "hide" );
      $('#addvisitingconsult').modal( "show" );
      
  }
  function submitvisitingconsult() {
      document.getElementById("vipdid").value=ipdaddmissionid; 
      document.getElementById("vclientid").value=patientId;
      var visitid=document.getElementById("visitid").value;
      if(visitid>0){
    	  var doctor=0;
          var date=document.getElementById("vdate").value;
          var time=document.getElementById("time").value;
          var fees=document.getElementById("fees").value;
          var tds = document.getElementById("tds").value;
          var vdrid = document.getElementById("vdrid").value;
          doctor = vdrid;
          var float_regexp = /^\d+(\.\d+)?$/;
      	  if(doctor==0){
              jAlert('error', "please select one doctor atleast!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
          }else if(date.length<1){
              jAlert('error', "please select date!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
          }else if(time.length<1){
              jAlert('error', "please select time!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
          }else if(fees==''){
	       		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	    			setTimeout(function() {
	    				$("#popup_container").remove();
	    				removeAlertCss();
	    			}, alertmsgduration);
          }else if(fees.length<1){
              jAlert('error', "please enter fees!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
          }else if(!float_regexp.test(fees)){
      		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	       			setTimeout(function() {
	       				$("#popup_container").remove();
	       				removeAlertCss();
	       			}, alertmsgduration);
          }else if(parseFloat(fees)<=0){
	       		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	    			setTimeout(function() {
	    				$("#popup_container").remove();
	    				removeAlertCss();
	    			}, alertmsgduration);
          }else if(tds==''){
              	jAlert('error', "please enter TDS!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
          }else if(!float_regexp.test(tds)){
     		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
  			setTimeout(function() {
  				$("#popup_container").remove();
  				removeAlertCss();
  			}, alertmsgduration);
		  }else if(parseFloat(tds)<=0){
		    		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
		 			setTimeout(function() {
		 				$("#popup_container").remove();
		 				removeAlertCss();
		 			}, alertmsgduration);
		  }else if(parseFloat(tds)>100){
	    		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
		 			setTimeout(function() {
		 				$("#popup_container").remove();
		 				removeAlertCss();
		 			}, alertmsgduration);
		  }else{
		    	 var x = confirm("Are You Sure!!");
		 		 if(x){
		 			$("#dashboardloaderPopup").modal('show'); 
		 			var formt= document.getElementById("visitform");
			        formt.action="updatevisitingIpdAjax";
			        formt.submit();
		 		 }
		   }
	  }else {
            var doctor=0;
            $('.case').each(function() { // loop through each checkbox
			 	  if(this.checked == true){
			 	       doctor=doctor+","+this.value;
			 	  } 
		    });
            var date=document.getElementById("vdate").value;
            var time=document.getElementById("time").value;
            var fees=document.getElementById("fees").value;
            var tds = document.getElementById("tds").value;
            var vdrid = document.getElementById("vdrid").value;
            doctor = vdrid;
            var float_regexp = /^\d+(\.\d+)?$/;
        	if(doctor==0){
                jAlert('error', "please select one doctor atleast!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            }else if(date.length<1){
                jAlert('error', "please select date!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            }else if(time.length<1){
                jAlert('error', "please select time!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            }else if(fees==''){
	       		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	    			setTimeout(function() {
	    				$("#popup_container").remove();
	    				removeAlertCss();
	    			}, alertmsgduration);
            }else if(fees.length<1){
                jAlert('error', "please enter fees!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            }else if(!float_regexp.test(fees)){
        		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	       			setTimeout(function() {
	       				$("#popup_container").remove();
	       				removeAlertCss();
	       			}, alertmsgduration);
            }else if(parseFloat(fees)<=0){
	       		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
	    			setTimeout(function() {
	    				$("#popup_container").remove();
	    				removeAlertCss();
	    			}, alertmsgduration);
            }else if(tds==''){
                	jAlert('error', "please enter TDS!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            }else if(!float_regexp.test(tds)){
       		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
    			setTimeout(function() {
    				$("#popup_container").remove();
    				removeAlertCss();
    			}, alertmsgduration);
		     }else if(parseFloat(tds)<=0){
		    		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
		 			setTimeout(function() {
		 				$("#popup_container").remove();
		 				removeAlertCss();
		 			}, alertmsgduration);
		     }else if(parseFloat(tds)>100){
	    		 jAlert('error', "Please enter valid TDS!", 'Error Dialog');
		 			setTimeout(function() {
		 				$("#popup_container").remove();
		 				removeAlertCss();
		 			}, alertmsgduration);
		     }else{
		    	 var x = confirm("Are You Sure!!");
		 		 if(x){
		 			$("#dashboardloaderPopup").modal('show'); 
		 			document.getElementById("doctors").value= doctor;  
		 			document.getElementById("visitform").submit();
		 		 }
		     }
      }
      
      
  }
  
  
  function showVisitConsult() {
    
     openPopup('viewvisitingconsultIpdAjax?ipdid='+ipdaddmissionid+'');
  }
  
  function showvital(){
	  openPopup('vitalstatisticsIpd?clientid='+ipdclientid+'&ipdid='+ipdaddmissionid+'');
  }
  
  
  function updateVisitedorNot(id,status,ipdid,clientid){
    
       /* if(status==1) {
        
               var tt=confirm("Do you want to add Visiting Charges to Patient Account!");
               if(tt==true){
                 document.getElementById("btnisvisited"+id).innerHTML="<a class='btn btn-primary'  onclick='updateVisitedorNot("+id+",0)' href='#'>Visited</a>"; 
               }
        } else {
              var tt=confirm("Do you want to remove Visiting Charges from Patient Account!");
              if(tt==true){
                 document.getElementById("btnisvisited"+id).innerHTML="<a class='btn btn-primary'  onclick='updateVisitedorNot("+id+",1)' href='#'>Not Visited</a>";
              }
        }
        var url="updatevisitedornotIpdDashboard?id="+id+"&status="+status+"";*/
	  
	  var url="";
      /*  if(status==1) {
        
               var tt=confirm("Do you want to add Visiting Charges to Patient Account!");
               if(tt==true){
                 document.getElementById("btnisvisited"+id).innerHTML="<a class='visited'  onclick='updateVisitedorNot("+id+",0)' href='#'>Visited</a>"; 
                 url="updatevisitedornotIpdDashboard?id="+id+"&status="+status+"";
               }
        } else {
              var tt=confirm("Do you want to remove Visiting Charges from Patient Account!");
              if(tt==true){
                 //document.getElementById("btnisvisited"+id).innerHTML="<a class='planned'  onclick='updateVisitedorNot("+id+",1)' href='#'>Not Visited</a>";
            	  document.getElementById("btnisvisited"+id).innerHTML="<p class='visited'>Visited</p>";
            	  
              }
        }*/
		  var tt=confirm("Do you want to add Visiting Charges to Patient Account!");
	      if(tt==true){
	    	  $("#dashboardloaderPopup").modal('show');
	        //document.getElementById("btnisvisited"+id).innerHTML="<a class='visited'  onclick='updateVisitedorNot("+id+",0)' href='#'>Visited</a>"; 
	    	  document.getElementById("btnisvisited"+id).innerHTML="<a class='visited' href='#'>Visited</a>"; 
	    	url="updatevisitedornotIpdAjax?id="+id+"&status="+status+"&clientid="+clientid+"&ipdid="+ipdid+"";
		  } 
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateVisitedorNotRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
        
  }
  
  
  function updateVisitedorNotRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
        	 window.location.reload();	

              }
         }
  }
  
  function addchargornot(visitid,ipdid,clientid,status) {
  
        if(status==1){
        
            document.getElementById("btnpvisit"+visitid).innerHTML="<a class='btn btn-primary' onclick='addchargornot("+visitid+","+ipdid+","+clientid+",0)' href='#'>Paid</a>"; 
        }else {
            document.getElementById("btnpvisit"+visitid).innerHTML="<a class='btn btn-primary' onclick='addchargornot("+visitid+","+ipdid+","+clientid+",1)' href='#'>Not Paid</a>";
        }
            
        var url="addchargeornotIpdDashboard?visitid="+visitid+"&status="+status+"&clientid="+clientid+"&ipdid="+ipdid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = addchargornotRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
  
  }
  
   function addchargornotRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
              }
         }
  }
  
  function editIpdVisit(visitid) {
	  	$("#dashboardloaderPopup").modal('show');
        var url="editipdvisitIpdAjax?visitid="+visitid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = editIpdVisitRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
  }
  
   function editIpdVisitRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
        	 	  $("#dashboardloaderPopup").modal('hide');
                  var data=req.responseText;
                  var str=data.split("~");
                  document.getElementById("visitid").value=str[0]; 
                  //document.getElementById("visitingConsultDoctors").value=str[1]; 
                  document.getElementById("vdrid").className="";
                  document.getElementById("vdrid").value=str[1]; 
                  document.getElementById("vdrid").className="form-control showToolTip chosen-select";
                  $("#vdrid").trigger("chosen:updated");
                  $(".chosen-select").chosen({allow_single_deselect: true});
                  document.getElementById("vdate").value=str[2];
                  document.getElementById("time").value=str[3];
                  document.getElementById("fees").value=str[4];   
                  document.getElementById("clientname").className="";
                  document.getElementById("clientname").value=str[5];
                  document.getElementById("clientname").disabled=true;
                  document.getElementById("clientname").className="form-control showToolTip chosen-select";
                  $("#clientname").trigger("chosen:updated");
                  $(".chosen-select").chosen({allow_single_deselect: true});
                  document.getElementById("tds").value=str[6];
                  $("#dashboardloaderPopup").modal('hide');
                  $('#addvisitingconsult').modal( "show" );
                  $('#visitingconsult').modal( "hide" );
              }
         }
  }

  function deleteIpdVisit(visitid) {
  
       var t=confirm("Are you sure you want to cancel?");
       if(t==true){
    	   		$("#dashboardloaderPopup").modal('show');
                var url="deleteipdvisitIpdAjax?visitid="+visitid+"";
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = deleteIpdVisitRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);       
             
       }
   
  }
  
 
 
  function deleteIpdVisitRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
                  window.location.reload();
              }
         }
  }   
  
  
  
  function loadcommonbell(){
             $('#dashboardloaderPopup').modal( "show" );
             
             /*var url="getcommonbellajaxIpdDashboard";    */
             var url="getcommonbellajaxIpdAjax";    
             if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = loadcommonbellRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);                   
  }
  
  
  function loadcommonbellRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {
              
                      
                      var str=req.responseText;
                      var data=str.split("~");
                      document.getElementById("pricscommonlist").innerHTML=data[0]; 
                      document.getElementById("nursingcommonlist").innerHTML=data[1];
                      document.getElementById("dietarylist").innerHTML=data[2];
                      $('#dashboardloaderPopup').modal( "hide" );
                      $('#commonbellpopup').modal( "show" );
                      
              }
         }
  }  
  
  
  
   function getajaxnotification(ipdid){
    
      $('#dashboardloaderPopup').modal( "show" );
      /*var url="getajaxnursingdataIpdDashboard?ipdid="+ipdid+"";*/
      var url="getajaxnursingdataBookAppointmentAjax?ipdid="+ipdid+"";
               ipdaddmissionid=ipdid;
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getajaxnotificationRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);     

  }
  
  
  function getajaxnotificationRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {
              
                 getAjaxPrisc(ipdaddmissionid);
                    
              }
         }
  }   
  
  
  
  
  function getAjaxPrisc(ipdid) {
  
             /*  var url="getallpriscajaxIpdDashboard?ipdid="+ipdid+"";*/
	  		var url="getallpriscajaxBookAppointmentAjax?ipdid="+ipdid+"";
               ipdaddmissionid=ipdid;
     
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getAjaxPriscRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);     
  }
  
  function getAjaxPriscRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
               
                  var str=req.responseText;
                  document.getElementById("ddprisc").innerHTML=str;
                  getAjaxNursing(ipdaddmissionid);
                 
              }
         }
  }   
  
  function getAjaxNursing(ipdid) {
  
              /* var url="getallnursingajaxIpdDashboard?ipdid="+ipdid+"";*/
	  var url="getallnursingajaxBookAppointmentAjax?ipdid="+ipdid+"";
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getAjaxNursingRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);     
  }
  
  function getAjaxNursingRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
               
                  var str=req.responseText;
                  document.getElementById("ddnursing").innerHTML=str;
                  getAjaxPerPatientInvestigation(ipdaddmissionid);
                  //getAjaxPerPatientDietary(ipdaddmissionid);
                  //$('#dashboardloaderPopup').modal( "hide" );
                  //$('#viewpriscpopup').modal( "show" );
              }
         }
  }
  
  function getAjaxPerPatientInvestigation(ipdid) {
	  //  03 oct 2017 set investigation to ipd
      /*var url="getajaxperpatientinvestigationIpdDashboard?ipdid="+ipdid+"";*/
	  var url="getajaxperpatientinvestigationBookAppointmentAjax?ipdid="+ipdid+"";
			if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getAjaxPerPatientInvestigationRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);     
}

function getAjaxPerPatientInvestigationRequest() {

if (req.readyState == 4) {
if (req.status == 200) {     
      
         var str=req.responseText;
         document.getElementById("tbodyinvestigationalert").innerHTML=str;
         getAjaxPerPatientDietary(ipdaddmissionid);
         //$('#dashboardloaderPopup').modal( "hide" );
         //$('#viewpriscpopup').modal( "show" );
     }
}
}   
  function getAjaxPerPatientDietary(ipdid) {
  
               /*var url="getAjaxPerpatientdietaryIpdDashboard?ipdid="+ipdid+"";*/
	  var url="getAjaxPerpatientdietaryBookAppointmentAjax?ipdid="+ipdid+"";
       			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getAjaxPerPatientDietaryRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);     
  }
  
  function getAjaxPerPatientDietaryRequest() {
     if (req.readyState == 4) {
         if (req.status == 200) {     
                  var str=req.responseText;
                  document.getElementById("dddietary").innerHTML=str;
                  $('#dashboardloaderPopup').modal( "hide" );
                  $('#viewpriscpopup').modal( "show" );
              }
         }
  }   
  
  function openGraph(paTientId) {
  
        openPopup('templateInvestigation?clientId='+paTientId+'');
  
  }
 var stds_tpid=0;
 var stds_payee="";
  function openStdChargePopup(tpid,wardid,clientid,ipdid,payee) {
       			
       			ipdaddmissionid=ipdid;
       			patientId=clientid;
       			ward=wardid;
       			stds_tpid = tpid;
       			stds_payee =payee;
       			$('#baselayout1loaderPopup').modal( "show" );
       			/*var url="getstdchargeIpd?wardid="+wardid+"&tpid="+tpid+"&clientId="+clientid+"&ipdid="+ipdid+"&payee="+payee+"";*/
       			var url="getstdchargeBookAppointmentAjax?wardid="+wardid+"&tpid="+tpid+"&clientId="+clientid+"&ipdid="+ipdid+"&payee="+payee+"";
                if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = openStdChargePopupRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);     
  }	
  
  function openStdChargePopupRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
        	 $('#baselayout1loaderPopup').modal( "hide" );
                  var data=req.responseText;
                  var str=data.split("~");
                  document.getElementById("updatestdcharge").innerText=str[0];
                  document.getElementById("resultsearch").innerHTML=str[1];
                  document.getElementById("endminute").value = "00";
                  document.getElementById("endhour").value = "00";
                  $('#addautocharge').modal( "show" );
              }
         }
  }   
  
  function editdatetime(ipdid,chargeid,startend,clientid,childid) {
	  startorendstd=startend;
	  var url="editchargedateBookAppointmentAjax?ipdid="+ipdid+"&chargeid="+chargeid+"&startend="+startend+"&clientid="+clientid+"&childid="+childid+"";
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = editdatetimeRequest;
	    req.open("GET", url, true); 
	    req.send(null);     
}	

function editdatetimeRequest() {
		if (req.readyState == 4) {
			if (req.status == 200) {     
		     
		        var data=req.responseText;
		        var str=data.split("~~");
		        document.getElementById("editupdatestdcharge").innerText=str[0];
		        document.getElementById("editstdchargename").innerHTML=str[1];
		        document.getElementById("editstddate").value=str[2];
		        document.getElementById("editstarthour").value=str[3];
		        document.getElementById("editstartminute").value=str[4];
		        document.getElementById("editstdipdid").value=str[5];
		        document.getElementById("editstdchargeid").value=str[6];
		        document.getElementById("editstdstartend").value=str[7];
		        document.getElementById("editstdclientId").value=str[8];
		        document.getElementById("editstdxhildid").value=str[9];
		        /*document.getElementById("editstdoffdate").value=str[10];*/
		        $('#addautocharge').modal( "hide" );
		        $('#editautocharge').modal( "show" );
		    }
		}
}
function updatestDdatetime() {
	$('#dashboardloaderPopup').modal( "show" );
	var editstddate= document.getElementById("editstddate").value; 
    var editstarthour= document.getElementById("editstarthour").value;
    var editstartminute= document.getElementById("editstartminute").value;
    var editstdipdid= document.getElementById("editstdipdid").value;
    var editstdchargeid= document.getElementById("editstdchargeid").value;
    var editstdstartend= document.getElementById("editstdstartend").value;
    var editstdclientId = document.getElementById("editstdclientId").value;
    var editstdxhildid = document.getElementById("editstdxhildid").value;
 /*   var editstdondate = document.getElementById("editstdondate").value;
    var editstdoffdate = document.getElementById("editstdoffdate").value;*/
    
    var url="updatestdonofftimedateBookAppointmentAjax?ipdid="+editstdipdid+"&chargeid="+editstdchargeid+"&clientid="+editstdclientId+"&stddate="+editstddate+"&starthour="+editstarthour+"&startminute="+editstartminute+"&editstdstartend="+editstdstartend+"&editstdxhildid="+editstdxhildid+"&startorend="+startorendstd;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
     req.onreadystatechange = updatestDdatetimeRequest;
    req.open("GET", url, true); 
    req.send(null);    
 }

function updatestDdatetimeRequest() {
 if (req.readyState == 4) {
     if (req.status == 200) {     
    	 $('#dashboardloaderPopup').modal( "hide" );
    	 $('#editautocharge').modal( "hide" );
     		openStdChargePopup(stds_tpid,ward,patientId,ipdaddmissionid,stds_payee);
         }
     }
}  
  
  function updateOnOffStd(ipdid,chargeId,childid,previousstatus,parentid) {
	
	  var s=confirm("Please Confirm On/Off Charge!");
	  if(s){
		  $('#dashboardloaderPopup').modal( "show" );
		  var obj=document.getElementById("ch"+chargeId);
	        var status="0";
	        var stddate= document.getElementById("stddate").value; 
	        var starthour= document.getElementById("starthour").value;
	        var startminute= document.getElementById("startminute").value;
	        var enddate= document.getElementById("enddate").value;
	        var endhour= document.getElementById("endhour").value;
	        var endminute= document.getElementById("endminute").value;
	        var stdbackdatechk = document.getElementById("stdbackdatechk").checked;
	      
	        if(obj.checked==true){
	             status="1";
	        } else {
	        	status="0";
	        }
	        
	           /*var url="updatestdonoffIpd?ipdid="+ipdid+"&status="+status+"&chargeid="+chargeId+"&clientid="+patientId+"&stddate="+stddate+"&starthour="+starthour+"&startminute="+startminute+"&enddate="+enddate+"&endhour="+endhour+"&endminute="+endminute+"&stdbackdatechk="+stdbackdatechk+" ";*/
	           var url="updatestdonoffBookAppointmentAjax?ipdid="+ipdid+"&status="+status+"&chargeid="+chargeId+"&clientid="+patientId+"&stddate="+stddate+"&starthour="+starthour+"&startminute="+startminute+"&enddate="+enddate+"&endhour="+endhour+"&endminute="+endminute+"&stdbackdatechk="+stdbackdatechk+"&childid="+childid+"&previousstatus="+previousstatus+"&parentid="+parentid+" ";
	       			if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = updateOnOffStdRequest;
				    req.open("GET", url, true); 
				    
				    req.send(null);     
	  }else{
		  document.getElementById("ch"+chargeId).checked = false;
	  }
       
         
  }
  
  function updateOnOffStdRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
        	 $('#dashboardloaderPopup').modal( "hide" );
         	var r = req.responseText;
               
                if(r==1){
                	   jAlert('success', "Charge Updated!", 'Success Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
                }else{
                
                	   jAlert('error', "Charge should not be ib process!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
                }
                if(r==1){
                	openStdChargePopup(stds_tpid,ward,patientId,ipdaddmissionid,stds_payee);
                }
              
              }
         }
  }  
  function toggleforautochargenew(val){
	  	if(val==true){
	  		document.getElementById("autochargepp1").className = "";
	  		document.getElementById("autochargepp2").className = "";
	  		document.getElementById("enddate").disabled = false;
	  	}else{
	  		document.getElementById("autochargepp1").className = "hidden";
	  		document.getElementById("autochargepp2").className = "hidden";
	  		document.getElementById("enddate").disabled = true;
	  	}
	  } 
  
  function toggleforenddate(){
  	var stdbackdatechk = document.getElementById("stdbackdatechk").checked;
  	if(stdbackdatechk==true){
  		document.getElementById("enddate").disabled = false;
  	}else{
  		document.getElementById("enddate").disabled = true;
  	}
  } 
  
 
  function allCheck(obj){
  
       if(obj.checked==true){
             
            $('.case').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		    });  
       
       } else {
            
            $('.case').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
	     	});
       }
     
  }
  
  function getpackage(){
	document.getElementById("pkgipdid").value = ipdaddmissionid;
	document.getElementById("pkgclienid").value = ipdclientid;
	$('#pacakgesp').modal( "show" );
}
 

 
 function openVitalClient(clientid,ipdid){
	   
	 ipdaddmissionid=ipdid;
	 patientId=clientid;
	    var url="getvitalmasteranddataIpdDashboard?ipdid="+ipdid+"&clientid="+clientid+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openVitalClientRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	 
	  
 }
 
 function openVitalClientRequest() {
	  
     if (req.readyState == 4) {
         if (req.status == 200) {
        	 
        	   var str=	req.responseText;
        	   var data=str.split("~");
        	   document.getElementById("pnamevital").innerHTML=data[0];
        	   document.getElementById("vitaltime").value=data[1];
        	   $('#vitals').modal( "show" );
             }
         }
  }
 
 function saveClientFinding(val,vitalid){
	 
	 saveFinding(val,ipdaddmissionid,patientId,vitalid);
 }
 
 
 
 
  function saveFinding(val,ipdid,clientid,vitalid){
	  
	  var time=document.getElementById("vitaltime").value;
	  
	  var url="saveclientfindingIpdDashboard?finding="+val+"&ipdid="+ipdid+"&clientid="+clientid+"&vitalid="+vitalid+"&time="+time+"";
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveFindingRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	  
	  
  } 
  function saveFindingRequest() {
	  
	     if (req.readyState == 4) {
	         if (req.status == 200) {
	        	   
	        	   
	             }
	         }
	  }  
  
  
 function updateDietaryGivenStatus(parentid,dietplan,val){
	  var url="updatedietarygivenstatusIpdDashboard?parentid="+parentid+"&dietplan="+dietplan+"&val="+val+"";
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateDietaryGivenStatusRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	  
	  
  } 
  function updateDietaryGivenStatusRequest() {
	     if (req.readyState == 4) {
	         if (req.status == 200) {
	        
	         }
	      }
  }
  
  
  function opencancelIpd(){
	  
	  $('#cancelipdpopup').modal( "show" );
	  
  }
  
  function cancelIpd(){
	  
	        var cancelnotes= document.getElementById("cancelnotes").value;
	        if(cancelnotes=='' || cancelnotes==' '){
	        	
	        	jAlert('error', "Please enter reason to cancel admission!", 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	        } else {
	        	 $('#dashboardloaderPopup').modal( "show" );
				  //var url="checkinvoiceIpd?ipdid="+ipdaddmissionid+"&clientid="+patientId+"";
	        	 var url="checkinvoiceBookAppointmentAjax?ipdid="+ipdaddmissionid+"&clientid="+patientId+""; 
	        	 
				  if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = cancelIpdRequest;
				    req.open("GET", url, true); 
				    
				    req.send(null);       
	        }
		    
  }
  
  function cancelIpdRequest() {
	     if (req.readyState == 4) {
	         if (req.status == 200) {
	        
	        	 
	        	 	var res=req.responseText;
	        	 	if(res=='1'){
	        	 		$('#dashboardloaderPopup').modal( "hide" );
	        	 		jAlert('error', "Invoice created cant cancel IPD!", 'Error Dialog');
	    				
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
	        	 		
	        	 		
	        	 	} else {
	        	 			   //cALL 
	        	 			  doipdcancel();
	        	 	}
	        	 
	         }
	      }
}
  
 
function doipdcancel(){
	
	var cancelnotes= document.getElementById("cancelnotes").value;
	 $('#dashboardloaderPopup').modal( "show" );
	//var url="cancelipdIpd?ipdid="+ipdaddmissionid+"&clientid="+patientId+"&cancelnotes="+cancelnotes+"";
	 var url="cancelipdBookAppointmentAjax?ipdid="+ipdaddmissionid+"&clientid="+patientId+"&cancelnotes="+cancelnotes+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = doipdcancelRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	
}
  
function doipdcancelRequest() {
    if (req.readyState == 4) {
        if (req.status == 200) {
       
        	   window.location.reload();
        	
        }
     }
}

function setemruploaddocAjax(data,type){
	var url = "setEmr?data="+data+"&type="+type+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setemruploaddocAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setemruploaddocAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}



function openuploaddoc(){
	
	
	  document.getElementById("clientname").value=patientId;
	  document.getElementById("diaryUser").value=ipdpractitionerid;
	  document.getElementById("condition").value=editcondition_id;
	  document.getElementById("editDoctId").value="";
	  $('#uploaddoc').modal( "show" );
	  
	
}

function saveNurseNoteAjax(){
	var nursemsg = document.getElementById("nursemsg").value;
	if(nursemsg==''){
		jAlert('error', "Please enter note!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "savenursenoteajaxIpdDashboard?nursemsg="+nursemsg+"&ipdclientid="+ipdclientid+"&ipdaddmissionid="+ipdaddmissionid+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = saveNurseNoteAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
}

function saveNurseNoteAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("nursemsgdiv").innerHTML=req.responseText;
			document.getElementById("nursemsg").value = '';
		}
	}
}
function getNurseNotes(admissionid){
		//var url = "getnursenotesajaxIpdDashboard?admissionid="+admissionid+"";
		var url = "getnursenotesajaxBookAppointmentAjax?admissionid="+admissionid+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = getNurseNotesRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
	
}

function getNurseNotesRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("nursemsgdiv").innerHTML=req.responseText;
		}
	}
}
  

function setInstantCashDesk(invoiceid){
	
	
	
	//checkAppointmentInvoicedForCashDesk();
	
	document.getElementById('cashClientid').value = ipdclientid;
	document.getElementById('cashclientname').value = ipdclientname;
	document.getElementById('cashPayby').value = invstwhopay;
	document.getElementById('cashLocationid').value = 1;
	document.getElementById('cashAppointmentid').value = 0;
	document.getElementById('cashinvoiceid').value = invoiceid;
	
	
		
		document.getElementById('cashdeskfrm1').submit();
	
	
	
	
	


	
}

  
function showdischargeprintpage(){
	openEmrPopup('printdischargeCommonnew?clientid='+patientId+'')
}


/*function addnewconsultantvalidate(){
	var consultantname = document.getElementById("consultantname").value;
	var url = "validateaddnewconsltalreadyIpdDashboard?consultantname="+consultantname+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = addnewconsultantvalidateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addnewconsultantvalidateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			if(data=='0'){
				validateaddnewconslt();
			}else{
				jAlert('error', "Name already present!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
	}
}*/

function validateaddnewconslt() {
	/*var initial = document.getElementById("initial").value;
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;*/
	var diciplineName = document.getElementById("diciplineName").value;
	var mobileno = document.getElementById("mobileno").value;
	var visitingconfees = document.getElementById("visitingconfees").value;
	var consultantname = document.getElementById("consultantname").value;
	var isvisitingcons = document.getElementById("isvisitingcons").checked;
	var issurgeon = document.getElementById("issurgeon").checked;
	var isanesthesiologist = document.getElementById("isanesthesiologist").checked;
	var isreferred = document.getElementById("isreferred").checked;
	var existingdr = document.getElementById("existingdr").value;
	var emailid = document.getElementById("emailid").value;
	var ismlc = document.getElementById("ismlc").checked;
	var mlcqualification= document.getElementById("mlcqualification").value;
	var sharepercentage = document.getElementById("sharepercentage").value;
	var refshareammount = document.getElementById("refshareammount").value;
	var refsharetype = document.getElementById("refsharetype").value;
	
	var float_regexp = /^\d+(\.\d+)?$/;
	var phone_regexp = /^[789]\d{9}$/;
	var numbers = /^[0-9]+$/;  
	var email_regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var flag =0;
	if(refsharetype==0){
		if(refshareammount!='' || refshareammount!='0' || refshareammount!=' '){
			if(parseFloat(refshareammount)<0){
				flag=1;
			}else if(parseFloat(refshareammount)>100){
				flag=2;
			}
		}
	}
	
	
	if(diciplineName==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please select speciality!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(mobileno=='' || mobileno==' ' || mobileno=='   '){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter mobileno!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(!numbers.test(mobileno)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid mobileno!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(emailid=='' || emailid==' ' || emailid=='   '){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter email!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}else if(!email_regexp.test(emailid)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid email!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(mlcqualification=='' || mlcqualification==' ' || mlcqualification=='  '){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qualification!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}
	else if(visitingconfees==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter fees!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(sharepercentage==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter share percentage of amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(!float_regexp.test(sharepercentage)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter share percentage of amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(parseFloat(sharepercentage)>100 || parseFloat(sharepercentage)<0){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid share percentage!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}else if(!float_regexp.test(visitingconfees)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(parseFloat(visitingconfees)<0){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter fees!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(flag==1 || flag==2){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid refernce share amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else{
		$("#dashboardloaderPopup").modal('hide');
		var x = confirm("Are You Sure!!");
		if(x){
			$("#dashboardloaderPopup").modal('show');
			/*var url = "savenewconsultantIpdDashboard?initial="+initial+"&firstname="+firstname+"&lastname="+lastname+"&diciplineName="+diciplineName+"&mobileno="+mobileno+"&visitingconfees="+visitingconfees+"";*/
			var url = "savenewconsultantIpdAjax?consultantname="+consultantname+"&diciplineName="+diciplineName+"&mobileno="+mobileno+"&visitingconfees="+visitingconfees+"&isvisitingcons="+isvisitingcons+"&issurgeon="+issurgeon+"&isanesthesiologist="+isanesthesiologist+"&isreferred="+isreferred+"&existingdr="+existingdr+"&emailid="+emailid+"&ismlc="+ismlc+"&mlcqualification="+mlcqualification+"&sharepercentage="+sharepercentage+"&refshareammount="+refshareammount+"&refsharetype="+refsharetype+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = validateaddnewconsltRequest;
		    req.open("GET", url, true); 
		    req.send(null);
		}
	}
}

function validateaddnewconsltRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}


function checkdralreadypresent(val){
	var url = "checkdralreadypresentIpdAjax?drid="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkdralreadypresentRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function checkdralreadypresentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data =req.responseText;
			var str = data.split('~');
			if(str[0]=='1'){
				 jAlert('error', "Practioner already entered!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("existingdr").className="";
					document.getElementById("existingdr").value=0;
					document.getElementById("existingdr").className="form-control showToolTip chosen-select";
					$("#existingdr").trigger("chosen:updated");
					$(".chosen-select").chosen({allow_single_deselect: true});
					document.getElementById("consultantname").value="";
					document.getElementById("consultantname").readOnly =false;
			}else{
				document.getElementById("consultantname").value = str[1];
				document.getElementById("diciplineName").className="";
				if(str[2]=='0'){
					document.getElementById("diciplineName").value = 0;
				}else{
					document.getElementById("diciplineName").value = str[2];
				}
				document.getElementById("existingdr").className="form-control showToolTip chosen-select";
				$("#diciplineName").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				document.getElementById("mobileno").value = str[3];
				document.getElementById("emailid").value = str[4];
				document.getElementById("tabledrname").innerHTML='';
				document.getElementById("referid").value = '';
				document.getElementById("updatebtnid").innerHTML='<button type="button" onclick="addnewconsultantvalidate()" class="btn btn-primary">Save</button>';
				document.getElementById("visitingconfees").value='';
				document.getElementById("isvisitingcons").value='';
				document.getElementById("issurgeon").value='';
				document.getElementById("isreferred").value='';
				document.getElementById("ismlc").value='';
				document.getElementById("mlcqualification").value='';
				document.getElementById("sharepercentage").value='70';
				document.getElementById("refshareammount").value='';
				if(str[5]=='0'){
					document.getElementById("consultantname").readOnly =false;
				}else{
					document.getElementById("consultantname").readOnly =true;
				}
				
			}
		}
	}
}


function searchexistingDr(val){
	var url = "searchexistingconsultantdrIpdAjax?val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = searchexistingDrRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function searchexistingDrRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("tabledrname").innerHTML=req.responseText;
		}
	}
}


function setrefereddrinfo(val){
	$("#dashboardloaderPopup").modal("show");
	var url = "setrefereddrinfoIpdAjax?id="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setrefereddrinfoRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setrefereddrinfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
				var data =req.responseText;
				var str = data.split('~');
				document.getElementById("referid").value= str[0];
				document.getElementById("consultantname").value = str[1];
				//document.getElementById("diciplineName").value = str[2];
				document.getElementById("diciplineName").className="";
				document.getElementById("diciplineName").value = str[2];
				document.getElementById("diciplineName").className="form-control showToolTip chosen-select";
				$("#diciplineName").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				document.getElementById("mobileno").value = str[3];
				document.getElementById("emailid").value = str[4];
				document.getElementById("tabledrname").innerHTML='';
				document.getElementById("visitingconfees").value=str[5];
				document.getElementById("existingdr").value=str[6];
				document.getElementById("isvisitingconsdiv").innerHTML=str[7];
				document.getElementById("issurgeondiv").innerHTML=str[8];
				document.getElementById("isanesthesiologistdiv").innerHTML=str[9];
				document.getElementById("isreferreddiv").innerHTML=str[10];
				document.getElementById("ismlcdiv").innerHTML=str[11];
				document.getElementById("mlcqualification").value=str[12];
				document.getElementById("refshareammount").value=str[13];
				document.getElementById("reftypediv").innerHTML=str[14];
				document.getElementById("sharepercentage").value=str[15];
				document.getElementById("updatebtnid").innerHTML='<button type="button" onclick="updateconsultantvalidate()" class="btn btn-primary">Update</button>';
				
				document.getElementById("existingdr").className="";
				document.getElementById("existingdr").disabled =true;
				document.getElementById("existingdr").className="form-control showToolTip chosen-select";
				$("#existingdr").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				document.getElementById("consultantname").readOnly =true;
				document.getElementById("existingdr_update").value=str[6];
				
				$("#dashboardloaderPopup").modal("hide");
		}
	}
}


function updateconsultantvalidate() {
	var refsharetype = document.getElementById("refsharetype").value;
	 var refshareammount = document.getElementById("refshareammount").value;
	var diciplineName = document.getElementById("diciplineName").value;
	var mobileno = document.getElementById("mobileno").value;
	var visitingconfees = document.getElementById("visitingconfees").value;
	var consultantname = document.getElementById("consultantname").value;
	var isvisitingcons = document.getElementById("isvisitingcons").checked;
	var issurgeon = document.getElementById("issurgeon").checked;
	var isanesthesiologist = document.getElementById("isanesthesiologist").checked;
	var isreferred = document.getElementById("isreferred").checked;
	var existingdr = document.getElementById("existingdr").value;
	var emailid = document.getElementById("emailid").value;
	var referid = document.getElementById("referid").value;
	var ismlc = document.getElementById("ismlc").checked;
	var mlcqualification = document.getElementById("mlcqualification").value;
	
	var sharepercentage = document.getElementById("sharepercentage").value;
	var existingdr_update = document.getElementById("existingdr_update").value;
	var float_regexp = /^\d+(\.\d+)?$/;
	var phone_regexp = /^[789]\d{9}$/;
	var numbers = /^[0-9]+$/;  
	var email_regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var flag =0;
	if(refsharetype==0){
		if(refshareammount!='' || refshareammount!='0' || refshareammount!=' '){
			if(parseFloat(refshareammount)<0){
				flag=1;
			}else if(parseFloat(refshareammount)>100){
				flag=2;
			}
		}
	}
	
	
	if(diciplineName==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please select speciality!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(mobileno=='' || mobileno==' ' || mobileno=='   '){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter mobileno!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(!phone_regexp.test(mobileno)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid mobileno!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(emailid=='' || emailid==' ' || emailid=='   '){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter email!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}else if(!email_regexp.test(emailid)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid email!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(mlcqualification=='' || mlcqualification==' ' || mlcqualification=='  '){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qualification!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}
	else if(visitingconfees==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter fees!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(sharepercentage==''){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter share percentage of amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(!float_regexp.test(sharepercentage)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter share percentage of amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(parseFloat(sharepercentage)>100 || parseFloat(sharepercentage)<0){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid share percentage!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}else if(!float_regexp.test(visitingconfees)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid fees!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(parseFloat(visitingconfees)<=0){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter fees!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(flag==1 || flag==2){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid refernce share amount!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else{
		$("#dashboardloaderPopup").modal('hide');
		var x = confirm("Are You Sure!!");
		if(x){
			$("#dashboardloaderPopup").modal('show');
			var url = "updatenewconsultantIpdAjax?consultantname="+consultantname+"&diciplineName="+diciplineName+"&mobileno="+mobileno+"&visitingconfees="+visitingconfees+"&isvisitingcons="+isvisitingcons+"&issurgeon="+issurgeon+"&isanesthesiologist="+isanesthesiologist+"&isreferred="+isreferred+"&existingdr="+existingdr+"&emailid="+emailid+"&referid="+referid+"&ismlc="+ismlc+"&mlcqualification="+mlcqualification+"&refsharetype="+refsharetype+"&refshareammount="+refshareammount+"&sharepercentage="+sharepercentage+"&existingdr_update="+existingdr_update+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = updateconsultantvalidateRequest;
		    req.open("GET", url, true); 
		    req.send(null);
		}
	}
	
}

function updateconsultantvalidateRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}

function setVistingConsultantFees(val) {
	var url = "setvisitingconsultantfeesIpdAjax?id="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setVistingConsultantFeesRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setVistingConsultantFeesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data =req.responseText;
			var str = data.split('~');
			document.getElementById("fees").value= str[0];
			//document.getElementById("doctors").value = str[1];
				
		}
	}
}

function openOtNotes(){
	openPopup("otnotesNotAvailableSlot?apmtid=0&clientid="+patientId+"");
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
		}
	}

}
function opennursingcareplan(){
	   openIpdPopup('nursingcareIpdDashboard?ipdclientid='+ipdclientid+'&ipdid='+ipdaddmissionid+'');
	  }

function addnewconsultantvalidate(){
	var nameReg = /^([^0-9]*)$/;
	$("#dashboardloaderPopup").modal('show');
	var consultantname = document.getElementById("consultantname").value;
	var diciplineName = document.getElementById("diciplineName").value;
	if(!nameReg.test(consultantname)){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter valid consultant name!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		return false;
	}else if(consultantname=='' || consultantname==' ' || consultantname=='  '){
		jAlert('error', "Please enter consultant name!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		$("#dashboardloaderPopup").modal('hide');
	}else if(consultantname=='null'){
		jAlert('error', "Please enter consultant name!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		$("#dashboardloaderPopup").modal('hide');
	}	else if(diciplineName=='null' || diciplineName=='0' ){
			jAlert('error', "Please Select Specialization!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			$("#dashboardloaderPopup").modal('hide');
	}
	else{
		var url = "validateaddnewconsltalreadyIpdAjax?consultantname="+consultantname+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = addnewconsultantvalidateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
}

function addnewconsultantvalidateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			if(data=='0'){
				validateaddnewconslt();
			}else{
				$("#dashboardloaderPopup").modal('hide');
				jAlert('error', "Name already present!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
	}
}

function payconsultantcharge(id,ipdid,clientid,status) {
	var url = "getconsulatntchargeIpdAjax?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = payconsultantchargeRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function payconsultantchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data =req.responseText;
			var str = data.split('~');
			document.getElementById("payfees").innerHTML= str[0];
			document.getElementById("paytds").innerHTML= str[1];
			document.getElementById("paybeforetotal").innerHTML= str[2];
			document.getElementById("payaftertotal").innerHTML= str[3];
			document.getElementById("ipdvisiconsultantid").value= str[4];
			document.getElementById("payperc").innerHTML= str[5];
			$('#payvisitingconsultfees').modal( "show" );
		}
	}
}

function payconsultantfees() {
	$("#dashboardloaderPopup").modal('show');
	var id = document.getElementById("ipdvisiconsultantid").value;
	var total = document.getElementById("payaftertotal").innerHTML;
	var url = "updateconsultantpaidstatusIpdAjax?id="+id+"&total="+total+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = payconsultantfeesRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function payconsultantfeesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
				
		}
	}
}

function setPatientIPDJsonData(clientid,admissionid,treatmentid){

	$.ajax({
		url: "ipd/pages/ipddetailjsons.jsp?clientid="+clientid+"&admissionid="+admissionid+"&treatmentid="+treatmentid+" " ,
		dataType : "json",
		success : function(json) {
			//alert("Your JSON : " + JSON.stringify(json));
			
			var data = JSON.parse(JSON.stringify(json));        
                     $.each(data,function(row,store)  {    
                        $.each(store,function(key,value) {
                         var stdChargeID = value.stdChargeID;
                         var checkStandardChargeExist = value.checkStandardChargeExist;
                         var temp = checkStandardChargeExist;
                        });        
                     });
			
		}
	});
}

function deleteChildPriscIPD(prischildid,ipdid) {
	  
    /*  var url="getallpriscajaxIpdDashboard?ipdid="+ipdid+"";*/
		var url="deletechildpriscBookAppointmentAjax?prischildid="+prischildid+"";
      ipdaddmissionid=ipdid;

			if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange =deleteChildPriscIPDRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);     
}

function deleteChildPriscIPDRequest() {

	if (req.readyState == 4) {
			if (req.status == 200) {
				
				getajaxnotification(ipdaddmissionid)
			}
	}
}   


function printlabel(){
	openPopup('patientlabelIpd?selectedid='+patientId+'');
}

function openRMONotesplan(){
	  var dataObj={
			  	"clientid" : "" + patientId + "",
			    "ipdid" : "" + ipdaddmissionid + "",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getipdrmodataBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		  /* var condition= data.responsej;*/
		   document.getElementById("todayrmonote").innerHTML=data.curentdaynotes;
		   document.getElementById("prermonote").innerHTML=data.prenoteslist;
		   $('#rmonotespopup').modal( "show" );
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

function savermonotes(){
	var rmonotes=document.getElementById("rmonewdaynote").value;
	var rmocurdateid=document.getElementById("rmocurdateid").value;
	var rmocurday=document.getElementById("rmocurday").value;
	  var dataObj={
			  	"clientid" : "" + patientId + "",
			    "ipdid" : "" + ipdaddmissionid + "",
			    "rmonotes" :""+rmonotes+"",
			    "rmocurdateid":""+rmocurdateid+"",
			    "rmocurday":""+rmocurday+"",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "savermonotesBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   var condition= data.response1;
		   jAlert('success', "RMO notes saved successfuly!", 'Success Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		   $('#rmonotespopup').modal("hide");
		   openRMONotesplan();
		},
	   error : function(result) {
		   jAlert('error', "Not saved!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }

	  });
}

function clinicalnotes() {
	
	   openEmrPopup('clinicalnotesEmr?clientid='+patientId+'&ipdid='+ipdaddmissionid+'&diaryuser='+diaryuserId);
		
	}



  //lokesh


//saving data div open 
function showdiv(){
	document.getElementById("bmiid").className="";
	document.getElementById("editSave1").innerHTML='<a href="#" style="color: yellow;" onclick="saveBMI()" title="Edit"><i class="fa fa-save" aria-hidden="true"></i></a>';
}
//saving data div close
function showdiv1(){
	document.getElementById("bmiid").className="hidden";
	document.getElementById("editSave1").innerHTML='<a href="#" style="color: yellow;" onclick="showdiv()" title="Edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>';
}
function calbmi(){

	   var w=document.getElementById("weight").value; 
	   var h=document.getElementById("height").value; 
	    
	   var d=h/100; 
	   var bmi=w/(d*d);
	   var result=Math.round(bmi*100)/100; 
	   document.getElementById("bmi").value=result;	    
	   
	   var bsa
	   bsa=h*w;
	   bsa=bsa/3600
	   bsa=Math.sqrt( bsa);
	   document.getElementById("bsa").value=bsa;
	}
	
	
function saveBMI(){
	
	height= document.getElementById("height").value;
	weight=document.getElementById("weight").value;
	bmi=document.getElementById("bmi").value;
	pulse=document.getElementById("pulse").value;
	sysbp=document.getElementById("sysbp").value;
  diabp=document.getElementById("diabp").value;
	//lokesh
  sugarfasting = document.getElementById("sugarfasting").value;
  postmeal = document.getElementById("postmeal").value;
  date=document.getElementById("fromDate").value;
  
  var url="savebmiBookAppointmentAjax?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"&patientId="+patientId+"&sugarfasting="+sugarfasting+"&postmeal="+postmeal+"&date="+date+"";
   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveBMIRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

}	

function saveBMIRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 //window.location.reload();
			  document.getElementById("height").innerHTML=height;
  			  document.getElementById("weight").innerHTML=weight;
  			  document.getElementById("bmi").innerHTML=bmi;
  			  document.getElementById("pulse").innerHTML=pulse;
  			  document.getElementById("sysbp").innerHTML=sysbp;
  			  document.getElementById("diabp").innerHTML=diabp;
  			  //lokesh 
  			  document.getElementById("sugarfasting").innerHTML=sugarfasting;
 			  document.getElementById("postmeal").innerHTML=postmeal;
 			 document.getElementById("bmiid").className="hidden";
 			 document.getElementById("fromDate").innerHTML=date;
 				document.getElementById("editSave1").innerHTML='<a href="#" style="color: yellow;" onclick="getdataofbmi()" title="Edit"><i class="fa fa-medkit" aria-hidden="true"></i></a>';
		}
	}
}
//lokesh 
function getdataofbmi(){
	
	document.getElementById("bmiid").className="";
	document.getElementById("editSave1").innerHTML='<a href="#" style="color: yellow;" onclick="saveBMI()" title="Edit"><i class="fa fa-save" aria-hidden="true"></i></a>';
	 var url='getBMIdata1NotAvailableSlot?clientid='+patientId+'&apmtid='+editAppointId+'';
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getdataofbmiRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

} 
function getdataofbmiRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data1 =req.responseText;
			var data= data1.split('~');
			  document.getElementById("height").value=data[0];
  			  document.getElementById("weight").value=data[1];
  			  document.getElementById("bmi").value=data[2];
  			  document.getElementById("pulse").value=data[3];
  			  document.getElementById("sysbp").value=data[4];
  			  document.getElementById("diabp").value=data[5];
  			  
  			  document.getElementById("sugarfasting").value=data[6];
 			  document.getElementById("postmeal").value=data[7];
			
			}
		}
		}

function returnnursmedicine(){
	   openIpdPopup('returnipdmedicineIpdAjax?ipdclientid='+ipdclientid+'&ipdid='+ipdaddmissionid+'');
}
var tmid=0;
function addnursereturnRow(tableID){
   counts= Number(document.getElementById("tempcount").value);
   var qty=document.getElementById("qty").value;
   var chargeid=document.getElementById("newmedicine").value;
   tmid=chargeid;
   if(chargeid=='0'){
              jAlert('error', "please select product!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					  
     
   } else if(qty==''){
              jAlert('error', "please enter return quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					  
     
   } else {
    	var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		row=table.insertRow(rowCount);
		var url="addtonursereturnBookAppointmentAjax?chargeid="+chargeid+"&count="+counts+"&qty="+qty+"";
		document.getElementById("qty").value = '';
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = addnursereturnRowRequest;
		req.open("GET", url, true); 
		req.send(null);
   } 
}
function addnursereturnRowRequest() {
   if (req.readyState == 4) {
	   if (req.status == 200) {     
			 var dd= req.responseText;  
		   	 var flag=false;
		   		$('.pclass').each(function() {
					var i=this.value;
					var tid= document.getElementById("id"+i).value;
					if(tid==tmid){
						flag=true;
					}
					var previqty=Number(document.getElementById("tempsale"+i).value);
					var returnqty=Number(document.getElementById("returnqty"+i).value);    
					if(returnqty>previqty){
					    document.getElementById("returnqty"+i).value=0;
					      jAlert('error', "Retrun qty cant greater than sale qty!", 'Error Dialog');
				
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						returnqty=0;	 
				    } 
				});	
		   		if(flag){
		   			jAlert('error', "This product already added!", 'Error Dialog');
						setTimeout(function() {
						$("#popup_container").remove();	
						removeAlertCss();
						}, alertmsgduration);   
		   		} else {
		   			row.innerHTML=dd;
				    counts++;
					document.getElementById("tempcount").value=counts;	  
					validatePharmacyNurseBill();
			   }
		   }
       }
}

function validatePharmacyNurseBill() {
		$('.pclass').each(function() {
			var i=this.value;
			var previqty=Number(document.getElementById("tempsale"+i).value);
			var returnqty=Number(document.getElementById("returnqty"+i).value);    
			if(returnqty>previqty){
			    document.getElementById("returnqty"+i).value=0;
			      jAlert('error', "Retrun qty cant greater than sale qty!", 'Error Dialog');
		
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			} 
		});	
}

function deleteRowtempNurse(r){
	   
    var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mytable").deleteRow(i);
}

function refundMedicineByNurse() {
	var flag = true;
	$('.pclass').each(function() {
	    
        var i=this.value;
		var sale=document.getElementById("returnqty"+i).value;		   
	     if(sale==''){
	            
	            flag=false;
                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	     }  
	}); 

 	if(flag){
 		if(document.getElementById('returnbtn')){
	 		  document.getElementById('returnbtn').style.visibility='hidden';
		}
 	   document.getElementById("creditform").action="refundmedicinebynursePharmacy";
 	   document.getElementById("creditform").submit();
 	}
}


function setfollowupdata(){
	
	var folowdate= document.getElementById("lokeshfollowdatenew").value;
	var ipdid=ipdaddmissionid;
	var clientid= patientId;
	var type="1";
	if(folowdate==""){
		alert("insert date");
		return;
	}
	var url ="savetofollowupClient?clientid="+clientid+"&ipdid="+ipdid+"&followdate="+folowdate+"&reqtype="+type+"" ;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	document.getElementById("followupmodal").style.display = "none";           
    req.onreadystatechange = setfollowupdataRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function setfollowupdataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
	
}

function openInvestigationdash1(){
	
	openPopup("Investigation?uhid="+abid);
}


function submitExcessamtbt(){
	document.getElementById("excessamtbt").value="1";
}


function openpatientpkgmaster(){
	openPopup('PackageMaster?clientid='+patientId);
	
}




function showEditPackageListAjax(){
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

function showpkgsapplied(){
	showEditPackageListAjax();
	$('#pkgeditlistmodal').modal( "show" );
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

function addTotalPkgamt(){
	var tptotal= 0;
	  $(".lock").each(function(){
		  tptotal= Number(this.value)+Number(tptotal);
	  });
	  document.getElementById("amtpkgedit").value=tptotal;
}

function showBedstatus(id){
	
	 /*document.getElementById("ipddashboardfrm").submit();*/
	opencPopup('IpdDashboard?bedstaus='+id);
}


function requestBloodBank(){
		 var dataObj={
				  	"ipdaddmissionid":""+ipdaddmissionid+"",
				  	"patientId":""+ipdclientid+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "requestbloodforclientBloodbank",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   document.getElementById("bloodclientid").value=ipdclientid;
			   document.getElementById("bloodsipdid").value=ipdaddmissionid;
			   
			   document.getElementById("blood_group_div").innerHTML = data.cataloguelist;
	           $("#blood_group").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
			   $("#requestblood").modal('show');
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

function addbloodrequest(){
	   var blood_group = document.getElementById("blood_group").value;
	   var bloodbank_component = document.getElementById("bloodbank_component").value;
	   var bloodbank_idnattested = document.getElementById("bloodbank_idnattested").value;
	   var bloodbank_leuco_depleted = document.getElementById("bloodbank_leuco_depleted").value;
	   var blood_bank_qty = document.getElementById("blood_bank_qty").value;
	   if(blood_group=='0'){
		   jAlert('error', "Please select blood group!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
	   }else if(bloodbank_component=='0'){
		   jAlert('error', "Please select blood component!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
	   }else if(bloodbank_leuco_depleted=='0'){
		   jAlert('error', "Please select Leuco Depleted!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
	   }else if(blood_bank_qty=='0'){
		   jAlert('error', "Please select quantity!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
	   }else{
		   document.getElementById("requestform").submit();	     
	   }
	   
} 


function openipdwardpopup(){
	var x='';
	document.getElementById("clname").innerHTML=ipdclientname;
	document.getElementById("wnamed").innerHTML=ward;
	
	$('#wardselect').modal( "show" );
}



function savewardchargerateforbed(){
	var clientid=ipdclientid;
	var wardid= document.getElementById("wdname").value;
	if(wardid=='0'){
		jAlert('error', 'Please select ward.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		return false;
	}
	var url = "savewardchargechangerateCommonnew?clientid="+clientid+"&wardid="+wardid;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange = savewardchargerateforbedRequ();
	req.open("GET", url, true); 
	          
	req.send(null);

}

function savewardchargerateforbedRequ(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			alert("Saved");
			}
		}
	}


function opennewdis(){
	openPopup('newdischargeformCommonnew?selectedid='+ipdaddmissionid);
}

var abid="";
function openprisciprionfromdashboard(bedid,cid,pid,conid,cname,pname,dob,town,admissionid,age,whopay,tpid,tpname,pmob,bedname,wardname,balance,treatmentepisodeid,
dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status, dis_mdicine_time, 
dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, dis_nursing_status,image,action,abrivationid,ipdseqno){
	 ipdtreatmentepisodeid = treatmentepisodeid;
	 newaction=action;
	 idis_initiate_time = idis_initiate_time;
	 idis_initiate_status = dis_initiate_status;
	 idis_form_time = dis_form_time;
	 idis_form_status = dis_form_status;
	 idis_mdicine_time = dis_mdicine_time;
	 idis_mdicine_status = dis_mdicine_status;
	 idis_bill_time = dis_bill_time;
	 idis_bill_status = dis_bill_status;
	 idis_nursing_time = dis_nursing_time;
	 idis_nursing_status = dis_nursing_status;
	 editClientName  = cname;
	 ipdclientname = cname;
	 ipdpractitionername = pname;
	 ipdclientid = cid;
	 ipdpractitionerid = pid;
	 ipdtpid = tpid;
	 ipdaddmissionid = admissionid;
	 ipdwhopay =  whopay;
	 ipdpractmob = pmob;
	 ipdbalance = balance;
	 imagename=image;
	 patientId = cid;
	 diaryuserId = pid;
	 editcondition_id = conid;
	 ward=wardname;
	 abid=abrivationid;
	 showipdpriscwindow();
}

function getOnoffTime(ipdid,id){
	
	var url ="getonoffchargesBookAppointmentAjax?ipdid="+ipdid+"&id="+id+"" ;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = getOnoffTimeRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function getOnoffTimeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#infohourlycharges').modal( "show" );
			 var str=req.responseText;
			
			 document.getElementById("chrgname").innerHTML=req.responseText;
			
		}
	}
	
}


function delonnoffstd(id){

	var url ="delonnoffstdBookAppointmentAjax?child="+id ;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = delonnoffstdRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function delonnoffstdRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText.split("-");
			jAlert('success', "deleted successfuly!", 'Success Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			getOnoffTime(str[0],str[1]);
			setTimeout(function() {
				openStdChargePopup(stds_tpid,ward,patientId,ipdaddmissionid,stds_payee);
			}, 2000);
			
			}
		}
}




function ipdbcodegen(){
	openPopup('patientbarcodeCommonnew?id='+ipdclientid+"&abrivation="+uhidbcode+"&patientNameOrig="+pnamebcode);
}

function openaddvistingchargepopup(){
	$("#dashboardloaderPopup").modal("show");
	document.getElementById("existingdr").className="";
	document.getElementById("existingdr").value=0;
	document.getElementById("existingdr").className="form-control showToolTip chosen-select";
	$("#existingdr").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});
	document.getElementById("consultantname").value="";
	document.getElementById("consultantname").readOnly =false;
	document.getElementById("consultantname").value = "";
	document.getElementById("diciplineName").className="";
	document.getElementById("diciplineName").value = 0;
	$("#diciplineName").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});
	document.getElementById("mobileno").value ="";
	document.getElementById("emailid").value = "";
	document.getElementById("tabledrname").innerHTML='';
	document.getElementById("referid").value = '';
	document.getElementById("visitingconfees").value='';
	document.getElementById("isvisitingcons").value='';
	document.getElementById("issurgeon").value='';
	document.getElementById("isreferred").value='';
	document.getElementById("ismlc").value='';
	document.getElementById("existingdr_update").value=0;
	document.getElementById("referid").value='0';
	document.getElementById("existingdr").disabled=false;
	document.getElementById("updatebtnid").innerHTML='<button type="button" onclick="addnewconsultantvalidate()" class="btn btn-primary">Save</button>';
	
	$("#dashboardloaderPopup").modal("hide");
	$("#addnewvisitingconsultdr").modal('show');
	
}

function openipdshiftpatient(){

	var url ="getpatientshiftdataBookAppointmentAjax?ipdaddmissionid="+ipdaddmissionid+"" ;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = openipdshiftpatientRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function openipdshiftpatientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText.split("~~~");
			document.getElementById("shiftpatient_name").innerHTML=ipdclientname;
			document.getElementById("shiftpatient_warddiv").innerHTML=str[0];
			document.getElementById("shiftpatient_beddiv").innerHTML=str[1];
			document.getElementById("shiftpatient_oldwardid").value=str[2];
			document.getElementById("shiftpatient_oldbedid").value=str[3];
			document.getElementById("shiftpatient_admissionid").value=ipdaddmissionid;
			$("#shiftpatient_wardid").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			$("#shiftpatient_bedid").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			$("#shiftpatientpopup").modal('show');
			
		}
	}
}

function getbedFromWardid(val){
	var shiftpatient_oldbedid = document.getElementById("shiftpatient_oldbedid").value;
	var url ="getbedFromWardidBookAppointmentAjax?wardid="+val+"&bedid="+shiftpatient_oldbedid+"" ;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = getbedFromWardidRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getbedFromWardidRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			document.getElementById("shiftpatient_beddiv").innerHTML=str;
			$("#shiftpatient_bedid").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
		}
	}
}

function validateShiftPatient() {
	var shiftpatient_wardid = document.getElementById("shiftpatient_wardid").value;
	var shiftpatient_bedid = document.getElementById("shiftpatient_bedid").value;
	if(shiftpatient_wardid=='0'){
		jAlert('error', 'Please select ward.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(shiftpatient_bedid=='0'){
		jAlert('error', 'Please select bed.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#shiftpatientpopup").modal('hide');
		jAlert('success', "Please wait for sometime. Updating in process", 'Success Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, 30000); 
		document.getElementById("saveshiftpatient").disabled = true;
		var url ="savepatientshiftdataBookAppointmentAjax?ipdaddmissionid="+ipdaddmissionid+"&wardid="+shiftpatient_wardid+"&bedid="+shiftpatient_bedid+"" ;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = validateShiftPatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
}
function validateShiftPatientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "updated successfuly! Please wait for reflect", 'Success Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 30000); 
			 window.location.reload();
		}
	}
}

function openfreezeerror(){
	jAlert('error', 'Please unfreeze first!!!', 'Error Dialog');
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, 30000);
}

function setfreezevalue(val){
	document.getElementById('freez_status').value=val;
}


function openipdrequestnote(val){

    $("#ipdrequestnote").modal('show');
    document.getElementById("actiontype").value=val;
    //counsultationcount(ipdaddmissionid);
    shownote(ipdaddmissionid);

}

function savereqnote(){
	var reqid=document.getElementById("reqid").value;
	var actiontype=document.getElementById("actionnote").value;
	var note=document.getElementById("reqnotes").value;
	var ispunchkarma=document.getElementById("actiontype").value;
	var procedureid=document.getElementById("otprocedureplaned").value;
	var date =document.getElementById("fromDate2").value;
	var dataObj={
		"note":""+note+"",
		"ipdaddmissionid":""+ipdaddmissionid+"",
		"actiontype":""+actiontype+"",
		"reqid":""+reqid+"",
		"ispunchkarma":""+ispunchkarma+"",
		"procedureid":""+procedureid+"",
		"opdid":""+opdid+"",
		"ipdclientid":""+ipdclientid+"",
		"opdclientid":""+opdclientid+"",
		"date":""+date+""
		
	}
	var data1 = JSON.stringify(dataObj);
	$.ajax({
		url : "savenoteBookAppointmentAjax",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		success : function(responseData){
			var ipdid=responseData.ipdaddmissionid;
			shownote(ipdid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	/* var url ="savenoteBookAppointmentAjax?note="+note+"&ipdaddmissionid="+ipdaddmissionid+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = savesittingRequest;
		req.open("GET", url, true);
		req.send(null);
		*/
	
	
}


function savesittingRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			//document.getElementById('note').innerHTML = req.responseText;
			//$("#notedetails").modal('show');
		if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
				//window.location.reload();
				$("#ipdrequestnote").modal('show');
                     // success
			}
			
         }
		
     }
	
  }
  
  
  function shownote(ipdadmissionid){
	
	var url = "requestnotelistNotAvailableSlot?ipdadmissionid="+ipdadmissionid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
	
    req.onreadystatechange = setsittingfollowuplistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setsittingfollowuplistRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str = req.responseText;
			 var data = str.split("~");
			   document.getElementById("carttbody").innerHTML = data[0];
			 //$( "#sitting_details" ).modal( "show" );		
		}
	}
	
}
  
  
function deleterequestnote(id){
	
	var dataObj={
		"id":""+id+"",
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"deletenoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var ipdid=responseData.ipdaddmissionid;
			shownote(ipdid);
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

function deletesittingRequest(){
     	if (req.readyState == 4) {
		    if (req.status == 200) {
			  var str = req.responseText;
			   $("#baselayout1loaderPopup").modal("hide");
			
			if (confirm("Do you want to delete?") == true) {
                         "Data deleted successfully!";
                         //window.location.reload();
             } else {
                         " Cancelled!";
           }
		}
    }

}
 
function editSitting(id,actiontype){
	
		var dataObj={
		"id":""+id+"",
		"actiontype":""+actiontype+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"editnoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var reqnote=responseData.reqnote;
			var actiontype=responseData.actiontype;
			var id=responseData.id;
			var procedure=responseData.procedure;
			document.getElementById("reqnotes").value=reqnote;
			document.getElementById("actionnote").value=actiontype;
			document.getElementById("reqid").value=id;
			document.getElementById("otprocedureplaned").value=procedure;
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

function seennote(val){
	
	
	$("#seeneuestpopup").modal('show');
	seenrequestnot(val);
	
}

function seenrequestnot(id){
	
	var dataObj={
		"id":""+id+"",
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"seennoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var reqnote=responseData.reqnote;
			var ipdaddmissionid=responseData.ipdaddmissionid;
			document.getElementById("setnote").innerHTML=reqnote;
			shownote(ipdaddmissionid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	//var url = "seennoteBookAppointmentAjax?id="+val+"";
	
	//if (window.XMLHttpRequest) {
		//req = new XMLHttpRequest();
	//}
	//else if (window.ActiveXObject) {
		//isIE = true;
		//req = new ("Microsoft.XMLHTTP");
	//}  
	
    //req.onreadystatechange = setseenrequestnoteRequest;
    //req.open("GET", url, true); 
    //req.send(null);
	
	
	
}

function setseenrequestnoteRequest(){

     if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  document.getElementById("setnote").innerHTML = str;
				
		 }
	}
			   
	}		   

function counsultationcount(ipdaddmissionid){
	
	var dataObj={
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"notecontFinder",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var notecount=responseData.notecount;
			var ipdaddmissionid=responseData.ipdaddmissionid;
			document.getElementById("notecount").innerHTML= "("+notecount+")";
			//shownote(ipdaddmissionid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	//var url="notecontFinder?ipdaddmissionid="+ipdaddmissionid+"";
	
	
	//if (window.XMLHttpRequest) {
		//req = new XMLHttpRequest();
	//}
	///else if (window.ActiveXObject) {
		//isIE = true;
		//req = new ("Microsoft.XMLHTTP");
	//}  
    //req.onreadystatechange = setconsultcountRequest;
    //req.open("GET", url, true); 
   // req.send(null);
	
	
	
}

function setconsultcountRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			document.getElementById("notecount").innerHTML= "("+str+")";
	
		 }
	}

}


