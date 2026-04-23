var sendsmsclientname = '';
var wraperDivId = '0';
var editClientName  = '';

var elabspatientid = '';
var elabsreqid = '';
var elabsregdatetime = '';
var elabspatienttype = '';
var elabstestregid = '';
var clinicuserid = '';
var machineinvlist = '';
var elabspatientname = '';
var mchinereqidlist = '';
var elabage = '';
var elabgender = '';
var elabreqdate = '';
var elabagetype = '';
var mchininvcharges = '';
var elabdept = '';
var elabdoctorname = '';
var invsavenprint = '';
var emailorwhatsup='';

if(document.getElementById('isnewgynicform11')){
	var patientId = document.getElementById("clientid").value;
}

function showInvestigation(){
	
	    document.getElementById('jobtitle').value = 'Pathlab';
		//document.getElementById("prisctable").innerHTML = '';
		removeSession();
		if(document.getElementById('isnewgynicform11')){
			//  27-06-2020 is it from gynic form
			var patientId = document.getElementById("clientid").value;
			getPriscClientInfo(patientId);
		}else{
			getPriscClientInfo(patientId);
		}
		
//		jQuery.noConflict();
		window.$('#investigationpopup').modal( "show" );
	

}

function showopdInvestigation(){
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;


	showInvestigation();
}


function showEditInvestigationPopup(){
	
	getPriscClientInfo(patientId);
	$('#investigationpopup').modal( "show" );
}

function removeSession(){
var url = "rmoveInvestigation";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = removeSessionRequest;
    req.open("GET", url, true); 
    
    req.send(null);

	
}

function removeSessionRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
		}
	}

	
}

function showGroupData(id){
	
	var url = "showgroupInvestigation?selectedid="+id+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showGroupDataReqiest;
	    req.open("GET", url, true); 
	    
	    req.send(null);
	}


	function showGroupDataReqiest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('grouptd').innerHTML = req.responseText;
			}
		}
	}
	
	
	function showNameData(id){
		
		
	/*	if(document.getElementById('invstreporttype').value==0){
			document.getElementById('invsttype').value = 0;
			jAlert('error', 'Please Select Report Type.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{*/
			$('#baselayout1loaderPopup').modal( "show" );
			
			var url = "shownameInvestigation?selectedid="+id+" ";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = showNameDataReqiest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
		}
		
		
		
		


		function showNameDataReqiest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('invstnametd').innerHTML = req.responseText;
					
					document.getElementById('invstreporttype').value = document.getElementById('rpttype').value;
					
					
					$('#baselayout1loaderPopup').modal( "hide" );
				
					//auto selected investigation
					document.getElementById('selecctall').checked = true;
					
					  $('.case').each(function() { //loop through each checkbox
		                    this.checked = true;  //select all checkboxes with class "checkbox1"               
		                });
					
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
				    
				    if(document.getElementById('consulationForm')){
				    	addTempInvestigaation();
				    }
				       
				}
			}
		}
		
		

		function showspecimen(id){
			
			var url = "showspecimenInvestigation?selectedid="+id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showspecimenReqiest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}


		function showspecimenReqiest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('specimentd').innerHTML = req.responseText;
					
					showreport('0');
				}
			}
		}
		
		
		
		

		function showreport(id){
			
			var url = "showreportInvestigation?selectedid="+id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showreportReqiest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}


		function showreportReqiest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('reporttd').innerHTML = req.responseText;
					
					showunit('0');
				}
			}
		}
		
		

		function showunit(id){
			
			var url = "showunitInvestigation?selectedid="+id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showunitReqiest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}


		function showunitReqiest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('unittd').innerHTML = req.responseText;
				}
			}
		}
		
		
		
	//starrt main coding
		
	

		function addTempInvestigaation(){
			  var selectedinvstid = 0;
			     $('.case').each(function() { //loop through each checkbox
			        // this.checked = true;  //select all checkboxes with class "checkbox1" 
			        if(this.checked==true){
			        	selectedinvstid = selectedinvstid + ',' + this.value;
			        }
			         
			     });
			     
			   //  alert(selectedinvstid)
			     var nonselected=true;
			     if(selectedinvstid==0){
			    	 nonselected=false;
			     }
			
			var invstcode = $("#invstcode option:selected").text();
			var invsttype = $("#invsttype option:selected").text();
			var invstgroup = $("#invstgroup option:selected").text();
			var invstname = selectedinvstid;
			var specimen = $("#specimen option:selected").text();
			var invstreporttype = $("#invstreporttype option:selected").text();
			var invstUnit = $("#invstUnit option:selected").text();
			
			var icode = document.getElementById('invstcode').value;
			var itype = document.getElementById('invsttype').value;
			var igroup = document.getElementById('invstgroup').value;
			var iname = selectedinvstid;
			var ispcimen = document.getElementById('specimen').value;
			var irtype = document.getElementById('invstreporttype').value;
			var iunts = document.getElementById('invstUnit').value;
			var normvalue = document.getElementById('normvalue').value;
			
			var invsttypeid=document.getElementById("invsttype").value;
			
			if(icode==0){
				invstcode = '';
			}
			if(itype==0){
				invsttype = '';
			}
			if(igroup==0){
				invstgroup = '';
			}
			/*if(iname==0){
				invstname = '';			
			}*/
			if(ispcimen==0){
				specimen = '';
			}
			if(irtype==0){
				invstreporttype = '';
			}
			if(iunts==0){
				invstUnit = '';
			}
			var invespkg="";
			if(document.getElementById('invpkg')){
				invespkg=document.getElementById('invpkg').value;
			}
		if(nonselected){
			var isFromCF ="0";
			if(document.getElementById('consulationForm')){
				isFromCF ="1";
		    }
			
			var url ="";
			if(invespkg=='0'){
				url = "addtempInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&invstcode="+invstcode+"&invstgroup="+invstgroup+"&invstname="+invstname+"&specimen="+specimen+"&invstreporttype="+invstreporttype+"&invstUnit="+invstUnit+"&invsttype="+invsttype+"&normvalue="+normvalue+"&invsttypeid="+invsttypeid+"&isFromCF="+isFromCF+"";
			}else{
				url = "addpkgtempInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&invstcode="+invstcode+"&invstgroup="+invstgroup+"&invstname="+invstname+"&specimen="+specimen+"&invstreporttype="+invstreporttype+"&invstUnit="+invstUnit+"&invsttype="+invsttype+"&normvalue="+normvalue+"&invsttypeid="+invsttypeid+"&isFromCF="+isFromCF+"";
			}
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addTempInvestigaationReqiest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
			
		}else{
			jAlert('error', 'Please Select Atleast One Investigation.', 'Error Dialog');
		}	
			
		
		}


		function addTempInvestigaationReqiest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById('invstlistid').innerHTML = req.responseText;
					 jAlert('success', "Investigation Added successfully!", 'Success Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
				}
			}
		}
		
		
		function saveViewInvestigation(){
			saveviewinvdbInvestigation(0);
			//document.getElementById('searchfrm').submit();
			//window.location.reload();
		}
		
		
		
		function saveviewinvdbInvestigation(){
			var prepay = 0;
			var postpay = 0; 
			var otherpay = 0;
			var invstreporttype = $("#invstreporttype option:selected").text();
			document.getElementById('investigationsavebtn').style.visibility='hidden';
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
			
			if(document.getElementById("language1").checked==true){
				var english = 1
			}
			if(document.getElementById("language2").checked==true){
				var regional = 1;
			}
			if(document.getElementById("language3").checked==true){
				var hindi = 1;
			}
			
			if(document.getElementById("invstlistid")){
				var chkinvstlist=document.getElementById("invstlistid").innerHTML;
				if(chkinvstlist==''||chkinvstlist==' '){
					jAlert('error', 'Please select Investigation.', 'Error Dialog');
					document.getElementById('investigationsavebtn').style.visibility='visible';
					return;
				}
			}
			var invpkg = document.getElementById('invpkg').value;
			$('#baselayout1loaderPopup').modal( "show" );
			
			
			var url = "insertInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&advoice="+advoice+"&invstautoid="+invstautoid+"&invstreporttype="+invstreporttype+"&jobtitle="+jobtitle+"&invpkg="+invpkg+"";
//			var url = "insertInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&advoice="+advoice+"&invstautoid="+invstautoid+"&invstreporttype="+invstreporttype+"&jobtitle="+jobtitle+"&invpkg="+invpkg+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveviewinvdbInvestigationRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}
		
		
		function saveviewinvdbInvestigationRequest(){
			
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					jAlert('success', 'Record saved successfully.', 'success Dialog');
					document.getElementById('investigationsavebtn').style.visibility='visible';
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					$('#baselayout1loaderPopup').modal( "hide" );
					$('#investigationpopup').modal( "hide" );
					
					
					document.getElementById("invchargeinfodata").innerHTML = req.responseText;
					var d = document.getElementById("invchargeinfo").value;
					var tmp = d.split('~');
					var invid1 = tmp[0];
					var cid1 = tmp[1];
					var pid1 = tmp[2];
					var invreq1 = tmp[3];
					var cname1 = tmp[4];
					var pname1 = tmp[5];
					elabdoctorname = tmp[5];
					var whopay1 = tmp[6];
					
					var invsttypeid1 = tmp[7];
					var ipdid1 = tmp[8];
					var abrivationid1 = tmp[9];
					clinicuserid = tmp[10]; 
					elabage = tmp[11];
					elabgender = tmp[12];
					elabreqdate = tmp[13];
					elabagetype = tmp[14];
					
					var selftp = tmp[15];
					var wardname = tmp[16];
					elabdept = wardname;
					
					elabspatientname = cname1;
					elabspatientid = abrivationid1;
					elabsreqid = invreq1;
					if(ipdid1==0){
						elabspatienttype = 2;
					}else{
						elabspatienttype = 1;
					}
					elabstestregid = invsttypeid1;
					machineinvlist = document.getElementById("machineinvlist").value;
					mchinereqidlist = document.getElementById("mchinereqidlist").value;
					
					mchininvcharges = document.getElementById("mchininvcharges").value;
					
					
					  if(clinicuserid=='sevenstar'){
						  if( machineinvlist!='0'){
							 /* var tmp = machineinvlist.split(',');
							  var tmp1 = mchinereqidlist.split(',');
							  for(i=0;i<tmp.length;i++){
								  SyncTestRegn(tmp[i],tmp1[i]);
							  }*/
							  
							  SyncTestRegn();
							 
						  }
					   }

					  window.location.reload();
				}
			}
			
		
			
			//$('#addchargepopup').modal( "show" );
			
			
		}

		
		function eresend(clientId,invrequest){
			
				var url = "elabresendInvestigation?clientId="+clientId+"&invrequest="+invrequest+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = eresendRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}
		
		function eresendRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
jAlert('success', 'Record sent successfully.', 'success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					document.getElementById("invchargeinfodata").innerHTML = req.responseText;
					var d = document.getElementById("invchargeinfo").value;
					var tmp = d.split('~');
					var invid1 = tmp[0];
					var cid1 = tmp[1];
					var pid1 = tmp[2];
					var invreq1 = tmp[3];
					var cname1 = tmp[4];
					var pname1 = tmp[5];
					elabdoctorname = tmp[5];
					var whopay1 = tmp[6];
					
					var invsttypeid1 = tmp[7];
					var ipdid1 = tmp[8];
					var abrivationid1 = tmp[9];
					clinicuserid = tmp[10]; 
					elabage = tmp[11];
					elabgender = tmp[12];
					elabreqdate = tmp[13];
					elabagetype = tmp[14];
					
					var selftp = tmp[15];
					var wardname = tmp[16];
					elabdept = wardname;
					
					elabspatientname = cname1;
					elabspatientid = abrivationid1;
					elabsreqid = invreq1;
					if(ipdid1==0){
						elabspatienttype = 2;
					}else{
						elabspatienttype = 1;
					}
					elabstestregid = invsttypeid1;
					machineinvlist = document.getElementById("machineinvlist").value;
					mchinereqidlist = document.getElementById("mchinereqidlist").value;
					
					mchininvcharges = document.getElementById("mchininvcharges").value;
					
					
					  if(clinicuserid=='sevenstar'){
						  if( machineinvlist!='0'){
							 /* var tmp = machineinvlist.split(',');
							  var tmp1 = mchinereqidlist.split(',');
							  for(i=0;i<tmp.length;i++){
								  SyncTestRegn(tmp[i],tmp1[i]);
							  }*/
							  
							  SyncTestRegn();
							 
						  }
					   }

					
				}
			}
			
		}
		
		function saveIpdInvestigation(val){
			var prepay = 0;
			var postpay = 0; 
			var otherpay = 0;
			invsavenprint=val;
			
			var invstreporttype = $("#invstreporttype option:selected").text();
			if(document.getElementById('investigationsavebtn')){
			document.getElementById('investigationsavebtn').style.visibility='hidden';
			}
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
			
			if(document.getElementById("language1").checked==true){
				var english = 1
			}
			if(document.getElementById("language2").checked==true){
				var regional = 1;
			}
			if(document.getElementById("language3").checked==true){
				var hindi = 1;
			}
			
			if(document.getElementById("invstlistid")){
				var chkinvstlist=document.getElementById("invstlistid").innerHTML;
				if(chkinvstlist==''||chkinvstlist==' '){
					jAlert('error', 'Please select Investigation.', 'Error Dialog');
					document.getElementById('investigationsavebtn').style.visibility='visible';
					return;
				}
			}
			
			var invpkg = document.getElementById('invpkg').value;
			var invstbackdate = document.getElementById('invstDate').value;
			
			var isFromCF=0;
			if(document.getElementById('consulationForm')){
				isFromCF = 1;
			}else{
				$('#baselayout1loaderPopup').modal( "show" );
			}
			var emr=0;
			var idss=0;
			if(document.getElementById('savetoemr')){
				emr=document.getElementById('savetoemr').value;
				if(emr==1){
					idss=editAppointId;
				}else if(emr==2){
					idss=ipdaddmissionid;
				}
			}
			
			var url = "insertInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&advoice="+advoice+"&invstautoid="+invstautoid+"&invstreporttype="+invstreporttype+"&jobtitle="+jobtitle+"&invpkg="+invpkg+"&action="+val+"&saveemr="+emr+"&appid="+idss+"&isFromCF="+isFromCF+"&invstbackdate="+invstbackdate+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveIpdInvestigationrequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		}
		
		
		function saveIpdInvestigationrequest(){
			
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					if(invsavenprint==1){
						window.location = 'printInvestigation';
					}else{
						jAlert('success', 'Record saved successfully.', 'success Dialog');
						document.getElementById('investigationsavebtn').style.visibility='visible';
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						
						$('#baselayout1loaderPopup').modal( "hide" );
						$('#investigationpopup').modal( "hide" );
						
						if(document.getElementById('consulationForm')){
							setGivenInvestigationData(req.responseText);
						}else{
							document.getElementById("invchargeinfodata").innerHTML = req.responseText;
							var d = document.getElementById("invchargeinfo").value;
							var tmp = d.split('~');
							var invid1 = tmp[0];
							var cid1 = tmp[1];
							var pid1 = tmp[2];
							var invreq1 = tmp[3];
							var cname1 = tmp[4];
							var pname1 = tmp[5];
							elabdoctorname = tmp[5];
							var whopay1 = tmp[6];
							
							var invsttypeid1 = tmp[7];
							var ipdid1 = tmp[8];
							var abrivationid1 = tmp[9];
							clinicuserid = tmp[10]; 
							elabage = tmp[11];
							elabgender = tmp[12];
							elabreqdate = tmp[13];
							elabagetype = tmp[14];
							
							var selftp = tmp[15];
							var wardname = tmp[16];
							elabdept = wardname;
							
							elabspatientname = cname1;
							elabspatientid = abrivationid1;
							elabsreqid = invreq1;
							if(ipdid1==0){
								elabspatienttype = 2;
							}else{
								elabspatienttype = 1;
							}
							elabstestregid = invsttypeid1;
							machineinvlist = document.getElementById("machineinvlist").value;
							mchinereqidlist = document.getElementById("mchinereqidlist").value;
							
							mchininvcharges = document.getElementById("mchininvcharges").value;
							
							document.getElementById("addinvnewchargehead").innerHTML = elabspatientname + " / Payee:" + selftp + " / " + wardname;
							showAddipdopdinvchargePopupinv(invid1,cid1,pid1,pname1,cname1,whopay1,invreq1);
							/*if(ipdid1!=0){
								showAddipdopdinvchargePopupinv(invid1,cid1,pid1,pname1,cname1,whopay1,invreq1);
							}*/
						}
					
					}
				}
			}
			
		
			
			//$('#addchargepopup').modal( "show" );
			
			
		}
		
		
		function insertInvestigation(){
			if(document.getElementById('isnewgynicform11')){
				patientId = document.getElementById("clientid").value;
			}
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
			var jobtitle = document.getElementById("jobtitle").value;
			
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
			
			var invpkg = document.getElementById('invpkg').value;
			$('#baselayout1loaderPopup').modal( "show" );
			
			
			var url = "insertInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&advoice="+advoice+"&invstautoid="+invstautoid+"&invstreporttype="+invstreporttype+"&jobtitle="+jobtitle+"&invpkg="+invpkg+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = insertInvestigationRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
		
		}
		
		function insertInvestigationRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					if(document.getElementById('isnewgynicform11')){
						//  27-06-2020 for investigation in gynic form
						/*getInvestigationGynicData();*/
						
						
						var str = "";
						//30-06-2020
						if(req.responseText!=""){
						   str = '<table  cellspacing="0" width="100%">'
					       str = str + '<thead>'
					       str = str + ' <tr class="tableback">'
					       str = str + '<th>Investigation</th>'
					       str = str + ' </tr>'
					       str = str + ' </thead>'
					                   
					       str = str + '<tbody >'
					       str = str + req.responseText;
					       str = str + '</tbody>'
					       str = str + '</table>'
					       
					       var investigation_advice= nicEditors.findEditor( "investigation_advice" ).getContent();	  
					       investigation_advice=investigation_advice+ "" + str + "<br>";
						   nicEditors.findEditor('investigation_advice').setContent(investigation_advice);
						
						}
						
						$('#baselayout1loaderPopup').modal( "hide" );
						$('#investigationpopup').modal( "hide" );
					}else{
					
					var oldData
					//nicEditors.findEditor ("consNote").setContent(data);
					if(document.getElementById('maintextarea')){
						oldData=nicEditors.findEditor ("maintextarea").getContent();
					}else{
						 oldData = nicEditors.findEditor('consNote').getContent();
					}
					
					var newData = req.responseText;
					
					var str = "";
					
					if(req.responseText!=""){
						
						//document.getElementById('prischdrdiv').style.display = '';
						
						str = '<table  cellspacing="0" width="70%">'
						       str = str + '<thead>'
						       /*str = str + '<tr><th>Investigation : </th></tr>'*/
						       str = str + ' <tr class="tableback">'
						       str = str + '<th>Investigation</th>'
						       /*str = str + '<th>Invest Group</th>'*/
						       // : below code by irfan sir comment by me its not hide test name
						       //str = str + ' <th class="displaynonesrno">Test Name</th>'
						       // : 11 April 2018 add hidden class by me  
						       //str = str + ' <th class="hidden">Test Name</th>'
						       /*str = str + ' <th>Sample Specimen</th>'
						       str = str + ' <th>Report Type</th>'
						       str = str + ' <th>Units</th>'*/
						       str = str + ' </tr>'
						       str = str + ' </thead>'
						                   
						       str = str + '<tbody >'
						        str = str + req.responseText;
						       str = str + '</tbody>'
						       str = str + '</table>'
					
						       if(document.getElementById('maintextarea')){
						    	  
									nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str + "<br>");
						       }else{
						    	   nicEditors.findEditor ("consNote").setContent(oldData + "<br>" + str);
									nicEditors.findEditor ("editconsNote").setContent(nicEditors.findEditor('editconsNote').getContent() + "<br>" + str );
						       }	       
					
					}
					
					var d = document.getElementById("invchargeinfo").value;
					var tmp = d.split('~');
					var invid1 = tmp[0];
					var cid1 = tmp[1];
					var pid1 = tmp[2];
					var invreq1 = tmp[3];
					var cname1 = tmp[4];
					var pname1 = tmp[5];
					elabdoctorname = tmp[5];
					var whopay1 = tmp[6];
					
					var invsttypeid1 = tmp[7];
					var ipdid1 = tmp[8];
					var abrivationid1 = tmp[9];
					clinicuserid = tmp[10]; 
					elabage = tmp[11];
					elabgender = tmp[12];
					elabreqdate = tmp[13];
					elabagetype = tmp[14];
					
					var selftp = tmp[15];
					var wardname = tmp[16];
					elabdept = wardname;
					
					elabspatientname = cname1;
					elabspatientid = abrivationid1;
					elabsreqid = invreq1;
					if(ipdid1==0){
						elabspatienttype = 2;
					}else{
						elabspatienttype = 1;
					}
					elabstestregid = invsttypeid1;
					machineinvlist = document.getElementById("machineinvlist").value;
					mchinereqidlist = document.getElementById("mchinereqidlist").value;
					
					mchininvcharges = document.getElementById("mchininvcharges").value;
					
					$('#baselayout1loaderPopup').modal( "hide" );
					$('#investigationpopup').modal( "hide" );
					
					getinvestigationViewList();
					}
				}
			}
					

		}
		
		
		function getinvestigationViewList(){
			var url = "viewInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = getinvestigationViewListRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		function getinvestigationViewListRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('allinvesttable').innerHTML = req.responseText;
					document.getElementById('alleditinvesttable').innerHTML = req.responseText;
					document.getElementById('emrinvstdiv').innerHTML = req.responseText;
					
					
					  if(clinicuserid=='sevenstar'){
						  if( machineinvlist!='0'){
							 /* var tmp = machineinvlist.split(',');
							  var tmp1 = mchinereqidlist.split(',');
							  for(i=0;i<tmp.length;i++){
								  SyncTestRegn(tmp[i],tmp1[i]);
							  }*/
							  
							  SyncTestRegn();
							 
						  }
					   }

				}
			}

			
		}
		
		
		function editparentinvstc(id){
			
			var url = "editInvestigation?selectedid="+id+" ";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = editparentinvstcRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
			}


			function editparentinvstcRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						
						document.getElementById('invstlistid').innerHTML = req.responseText;
						
						var data = document.getElementById("parentinvsteditdataid").value;
						var temp = data.split('~');
						
						var prepaid = temp[0];
						var postpaid = temp[1];
						var otherpay = temp[2];
						
						var english = temp[3];
						var regional =  temp[4];
						var hindi =  temp[5];
						var invstagdvoice = temp[6];
						
						if(prepaid==1){
							document.getElementById('ipaymode1').checked = true;
						}
						if(postpaid==1){
							document.getElementById('ipaymode2').checked = true;
						}
						if(otherpay==1){
							document.getElementById('ipaymode3').checked = true;
						}
						
						if(english==1){
							document.getElementById('ilanguage1').checked = true;
						}
						if(regional==1){
							document.getElementById('ilanguage2').checked = true;
						}
						if(hindi==1){
							document.getElementById('ilanguage3').checked = true;
						}
						
						document.getElementById('advoice').value = invstagdvoice;
						
						getPriscClientInfo(patientId);
						
						$('#investigationpopup').modal( "show" );
						
					}
				}
			}
		
		
			
		function delparentinvst(id){
			var url = "deleteparentInvestigation?selectedid="+id+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = delparentinvstRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		function delparentinvstRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					getinvestigationViewList();
					
					//document.getElementById('alldataprisctable').innerHTML = req.responseText;
				}
			}

		}
		
		
		function deleteinvstdata(id){
			
			var isFromCF ="0";
			if(document.getElementById('consulationForm')){
				isFromCF ="1";
		    }
			
			var url = "delinvstInvestigation?selectedid="+id+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&isFromCF="+isFromCF+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = deleteinvstdataRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		
		function deleteinvstdataRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('invstlistid').innerHTML = req.responseText;
				}
			}

		}
		
		
		
		
		//update investigation code
		var ccid = 0;
		var ppid = 0;
		var cbcinvid = 0;
		var cbcaction;
		var gname = '';
		var tstat=0;
		var cmpdate="";
		var deptname="";
		
		function updatecbc(id){
			var cbcid = id;
			showupdatepopup(cbcinvid,cbcaction,ccid,ppid,cbcid);
		}
		
		function showupdatepopup(id,action,cid,pid,cbcid,gpname,tstatus,cdate,dept){
			ccid = cid;
			ppid = pid;
			cbcinvid = id;
			cbcaction = action;
			gname = gpname;
			deptname=dept;
			tstat=tstatus; 
			cmpdate=cdate;
			document.getElementById('numericalinvstrefid').innerHTML = id;
			
			var url = "rporteditInvestigation?selectedid="+id+"&action="+action+"&cbcid="+cbcid+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showupdatepopupRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		function showupdatepopupRequest(){
			
				if (req.readyState == 4) {
					if (req.status == 200) {
						var x= req.responseText;
						var cbrd=x.split("hello-how-are-you/");
						var reqiid=  cbrd[0].split("lhg-kits");
						
						var invsttype=x.split("~");
						var invtyp=invsttype[9].split("~");
						var clinic=invsttype[10].split("~");
						if(invtyp=='WIDAL' && clinic==true){
							document.getElementById('reqiid1').innerHTML=reqiid[1];
							document.getElementById('rporteditinvsttable1').innerHTML = reqiid[0];
							getInvstClientInfo();
						
						document.getElementById("deptid").innerHTML= deptname;
						document.getElementById("deptid1").innerHTML= deptname;
						var investigation_approve = document.getElementById('investigation_approve').value;
						if(tstat=="0"){
									if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}
					     			document.getElementById("cmpdate").innerHTML="";
					     			document.getElementById("cmpdate1").innerHTML="";
					     			
							}	else {
							
									document.getElementById("cmpdate").innerHTML=cmpdate;
									document.getElementById("cmpdate1").innerHTML=cmpdate;
									if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="btn btn-primary";
						     			document.getElementById("btnapproved1").className="btn btn-primary";
									}else{
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}
									
							}
						
						var dd= document.getElementById("parentinvsteditdataid").value;
						var str= dd.split("~");
						document.getElementById("comment1").value= str[7];
						var remk= cbrd[1];
						nicEditors.findEditor('comment').setContent(str[6]);
						
						var newrem= cbrd[0].split("~");
						nicEditors.findEditor('comment').setContent(newrem[8]);
						var newrede=newrem[8];
						newrede=newrede.replace("<div class='hidden'>","");
						newrede=newrede.replace("</div>","");
						if(remk=='ok'){
							if(cbrd[2]==''){
							var dff="<p><b>PERIPHERAL SMEAR:</b></p>"+	"<p><b>RBC's:</b> Predominantly Normocytic "+   " Normochromic</p>"+""+ "<p><b>WBC's</b>: As mentioned Above</p>"+	 "<p><b>Platelets </b>- Adequate</p><p>No parasite seen</p>";
	                          nicEditors.findEditor('comment').setContent(dff);
							}else{
							nicEditors.findEditor('comment').setContent(cbrd[2]);
							}
						}else{
							nicEditors.findEditor('comment').setContent(newrede);
						}
						$('#investedit1').modal( "show" );
						}else{
						document.getElementById('reqiid').innerHTML=reqiid[1];
						document.getElementById('rporteditinvsttable').innerHTML = reqiid[0];
						getInvstClientInfo();
						
						document.getElementById("deptid").innerHTML= deptname;
						document.getElementById("deptid1").innerHTML= deptname;
						var investigation_approve = document.getElementById('investigation_approve').value;
						if(tstat=="0"){
									if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}
					     			document.getElementById("cmpdate").innerHTML="";
					     			document.getElementById("cmpdate1").innerHTML="";
					     			
							}	else {
							
									document.getElementById("cmpdate").innerHTML=cmpdate;
									document.getElementById("cmpdate1").innerHTML=cmpdate;
									if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="btn btn-primary";
						     			document.getElementById("btnapproved1").className="btn btn-primary";
									}else{
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}
									
							}
						
						var dd= document.getElementById("parentinvsteditdataid").value;
						var str= dd.split("~");
						document.getElementById("comment").value= str[7];
						var remk= cbrd[1];
						nicEditors.findEditor('comment').setContent(str[6]);
						
						var newrem= cbrd[0].split("~");
						nicEditors.findEditor('comment').setContent(newrem[8]);
						var newrede=newrem[8];
						newrede=newrede.replace("<div class='hidden'>","");
						newrede=newrede.replace("</div>","");
						if(remk=='ok'){
							if(cbrd[2]==''){
							var dff="<p><b>PERIPHERAL SMEAR:</b></p>"+	"<p><b>RBC's:</b> Predominantly Normocytic "+   " Normochromic</p>"+""+ "<p><b>WBC's</b>: As mentioned Above</p>"+	 "<p><b>Platelets </b>- Adequate</p><p>No parasite seen</p>";
	                          nicEditors.findEditor('comment').setContent(dff);
							}else{
							nicEditors.findEditor('comment').setContent(cbrd[2]);
							}
						}else{
							nicEditors.findEditor('comment').setContent(newrede);
						}
						$('#investedit').modal( "show" );
						}
						
					}
				}

		}
		
		
	/*	function saveUpdatedReport(){
			var url = "updaterportInvestigation";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = saveUpdatedReportRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

		}
		
		function saveUpdatedReportRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
				}
			}
		}
		
		*/
		function uploadfilepopup(id,action,cid,pid,gpname,tstatus,cdate,dept){
			
			var url = "upinvfileInvestigation?selectedid="+id+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = uploadfilepopupRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
			
			
		}
		
		function uploadfilepopupRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					$('#uploadfile').modal( "show" );
				}
			}
			
		}
		
		//writeup code
		var writeupinvstid = 0;
		function editwriteup(id,action,cid,pid,gpname,tstatus,cdate,dept){
			ccid = cid;
			ppid = pid;
			gname = gpname;
			writeupinvstid = id;
			tstat=tstatus; 
			cmpdate=cdate;
			deptname=dept;
			document.getElementById('writupinvstrefno').innerHTML = id;
			
			var url = "editwriteupInvestigation?selectedid="+id+"&action="+action+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = editwriteupRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);
			
		}
		
		function editwriteupRequest(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					var newstr=req.responseText;
					var datanew=newstr.split("XXXthisisremarkforwriteupXXX");
					var remarkwriteup=datanew[1];
					 nicEditors.findEditor( "commentwriteup" ).setContent(remarkwriteup);
					 document.getElementById('wreqiid').innerHTML=datanew[2];
					var str=datanew[0]; 
					var data=str.split("#");
					document.getElementById('writeuprporteditinvsttable').innerHTML = data[0];
					getInvstClientInfo();
					document.getElementById("deptid").innerHTML= deptname;
					document.getElementById("deptid1").innerHTML= deptname;
					var investigation_approve = document.getElementById('investigation_approve').value;
					if(tstat=="0"){
					     			//document.getElementById("btnapproved").className="hidden";
					     			//document.getElementById("btnapproved1").className="hidden";
					     			if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}

					     			document.getElementById("cmpdate").innerHTML="";
					     			document.getElementById("cmpdate1").innerHTML="";
					     			
							}	else {
							
									document.getElementById("cmpdate").innerHTML=cmpdate;
									document.getElementById("cmpdate1").innerHTML=cmpdate;
									/*document.getElementById("btnapproved").className="btn btn-primary";
					     			document.getElementById("btnapproved1").className="btn btn-primary";*/
									if(investigation_approve=='1'){
										document.getElementById("btnapproved").className="btn btn-primary";
						     			document.getElementById("btnapproved1").className="btn btn-primary";
									}else{
										document.getElementById("btnapproved").className="hidden";
						     			document.getElementById("btnapproved1").className="hidden";
									}
							}
					
					var rpttype =document.getElementById("rpttype").value;
					if(rpttype=='Text'){
						document.getElementById("templatediv").style.display=""; 
						document.getElementById('divothertemplate').style.display="";
						document.getElementById("templatediv").innerHTML= data[1];
						
						 $("#invstemplate").trigger("chosen:updated");
	    			 	  $(".chosen-select").chosen({allow_single_deselect: true});
						
						nicEditors.findEditor('templatesec').setContent(data[2]);
						
					} else {
						document.getElementById("templatediv").style="display:none";
						document.getElementById('divothertemplate').style="display:none";
					}
					
					$('#investwrite').modal( "show" );
					
					
				}
			}
		}
		
		
		
		function getInvstClientInfo(){
			
			var url = "getInfoClient?clientId="+ccid+"&diaryuser="+ppid+" ";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = getInvstClientInfoRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		function getInvstClientInfoRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
				
					
					var info = req.responseText;
					var temp = info.split("*");
					//document.getElementById("priscclientname").value = temp[0];
					//document.getElementById("regno").value = patientId;
					
					 var date =  document.getElementById("invsdate").value;
					 var year = date.split('/');
					
					 var dob = temp[5];
					/* var dobyear = dob.split('/');
					 var age = year[2] - dobyear[2];*/
					 //for showing dob By  
					 var age = dob;
					 var strname = 'Name : '+temp[0]+'  | AGE:'+age+' / '+temp[9]+'';
					 
					 document.getElementById("ninvstlableemodel").innerHTML = strname;
					 
					 document.getElementById("ndatetimeid").innerHTML = document.getElementById("invstdateandtime").value;
					 
					 document.getElementById("npriscdoctornameid").innerHTML = temp[10];
					 
					 
					 document.getElementById("wrname").innerHTML = temp[0];
					 document.getElementById("wrage").innerHTML = age;
					 document.getElementById("wrgender").innerHTML = temp[9];
					
					 //investigation client info
					 document.getElementById("winvstlableemodel").innerHTML = strname;
					 document.getElementById("wdatetimeid").innerHTML = document.getElementById("invstdateandtime").value;
					 document.getElementById("wpriscdoctornameid").innerHTML = temp[10];
					 
					 if(ppid==0){
							 document.getElementById("npriscdoctornameid").innerHTML = gname;
							  document.getElementById("wpriscdoctornameid").innerHTML = gname;
						}
					 
					 
					 showInvstAttchment();
					 
					
					
		         }
			}
		}
		
		
		function showInvstAttchment(){
			var url = "attachmentInvestigation?id="+writeupinvstid+"";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = showInvstAttchmentRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
			}
			
			function showInvstAttchmentRequest(){
				if (req.readyState == 4) {
				if (req.status == 200) {
			
				document.getElementById("attachdfilediv").innerHTML = req.responseText;
				
	         	
				
			         }
				}
			}
		
		
		//collecting invst name id
		var selectedinvid = 0;
		function collectinvestnameid(){
			 var container = document.getElementById('invstnametd');
			 var childArray = container.children;
			 for(var i = 0; i < childArray.length; i++){
				if(document.getElementById('ch'+childArray[i].id).checked==true){
					selectedinvid = selectedinvid + ',' + childArray[i].value;
				}
			 }
			 
			
		}
		
		
		
		//sending sms
		
		function setsmsuser(cid,pid,cname,pname,pmob){
			document.getElementById('smsuserNameletter').value = pname;
			document.getElementById('smsmobile').value = pmob;
			document.getElementById('smstxt').value = cname
			
			sendsmsclientname = cname;
			
			$('#semdsmspopup').modal( "show" );
		}
		

		
		//send attachment code
		
		function showAttachmentPopup(){
			$('#attachfilepopup').modal( "show" );
		}
		
		
		var c=0;
		function saveFindings(){
			var writeupid = document.getElementById('writeupid').value;
			var rpttype =document.getElementById("rpttype").value;
			var fromdate = document.getElementById("fromdate").value;
			var todate = document.getElementById("todate").value;
			var searchText = document.getElementById("searchText").value;
			
			document.getElementById("fromdate1").value = fromdate;
			document.getElementById("todate1").value = todate;
			document.getElementById("searchText21").value = searchText;
			 var commentwriteup =   nicEditors.findEditor( "commentwriteup" ).getContent();
			 document.getElementById("commentwriteup").value=commentwriteup;
			
			if(rpttype=='Text'){
				
				var temp = writeupid.split(',');
				for(i=0;i<temp.length;i++){
					
					var selectedid = temp[i];
					var finding= nicEditors.findEditor( "templatesec" ).getContent();
					
					document.getElementById("tfinding").value= finding;
					document.getElementById("tselectedid").value= selectedid;
					document.getElementById("impresssiontext").value= commentwriteup;
					document.getElementById("form7").submit();
					
				}
			} 
			else {
				document.getElementById("fromdate3").value = fromdate;
				document.getElementById("todate3").value = todate;
				document.getElementById("searchText3").value = searchText;
				document.getElementById("hdnselectedid").value = writeupid;
				document.getElementById("writeupfrm").submit();
				
		/*	var temp = writeupid.split(',');
				for(i=0;i<temp.length;i++){
					
				var finding = document.getElementById('f'+temp[i]).value; 
				var biorefrange = document.getElementById('b'+temp[i]).value; 
				var method = document.getElementById('m'+temp[i]).value;
				var selectedid = temp[i];
				
				var url = "savefindingsInvestigation?finding="+finding+"&biorefrange="+biorefrange+"&method="+method+"&selectedid="+selectedid+" ";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = saveFindingsRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);
			    c++;
			    	
				}*/
		   }	
			
			
			
		}
		
		function saveFindingsRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					/*var writeupidsize = document.getElementById('writeupidsize').value;;
					if(c==writeupidsize){
					document.getElementById('upload').submit();
					}*/
					window.location.reload();
					//document.getElementById('alldataprisctable').innerHTML = req.responseText;
				}
			}

		}
		
		
		
		var pppid = 0;
		var pppcname  = '';
		var pppwhopay = '';
		function sendinvstigationLetter(clientname,clientid){
			pppid = clientid;
			editClientName = clientname;
			pppcname = clientname;
			sendLetter();
			
			
			
		}
		
		function sendinvstigationLetter1(clientname,clientid,emailorwhatsup,email){
					pppid = clientid;
					editClientName = clientname;
					pppcname = clientname;
					document.getElementById("emaorwhatsup").value = emailorwhatsup;
					document.getElementById("aftid").value = clientid;
					document.getElementById("sms_clientid").value = clientid;
					document.getElementById("gpLetterEmail").value = email;
					sendLetter();
	 }
		
		
		

//add charge code

var ipdinvid = 0;
var ipdclientname = '';
var ipdpractitionername = '';
var ipdclientid = 0;
var ipdpractitionerid = 0;
var gpriscid = 0;
var ginvstid = 0;
var invstwhopay = 0;
var ipdinvreq = 0;

//prescription charge
function showAddchargePopupPriscription(invid,cid,pid,pname,cname,whopay){
	ipdinvid = invid;
	gpriscid = invid;
	ipdclientid = cid;
	ipdpractitionerid = pid;
	ipdclientname = cname;
	ipdpractitionername = pname;
	
	document.getElementById("addchargehead").innerHTML = cname;
	
	if(whopay=='Third Party'){
		
		document.getElementById("payBuy1").checked = true;
		document.getElementById("payBuy").checked = false;
	}else{
		document.getElementById("payBuy").checked = true;
		document.getElementById("payBuy1").checked = false;
	}
	

	var url = "truncateIpdDashboard";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showAddchargePopupPriscriptionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
	
}

function showAddchargePopupPriscriptionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			addOutoPriscriptionCharge();
			//$('#addchargepopupinv').modal( "show" );
		}
	}
	
}


function addOutoPriscriptionCharge(){
	var url = "outochargePrescription?selectedid="+ipdinvid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addOutoPriscriptionChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function addOutoPriscriptionChargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setCashDesk();
			$('#addchargepopupinv').modal( "show" );
		}
	}
	
}



function showAddchargePopupinv(invid,cid,pid,pname,cname,whopay,invreq,ward,selftp){
	ipdinvid = invid;
	ginvstid = invid;
	ipdclientid = cid;
	ipdpractitionerid = pid;
	ipdclientname = cname;
	ipdpractitionername = pname;
	invstwhopay = whopay;
	ipdinvreq = invreq;
	
//	document.getElementById("addchargebtn").style.visibility="hidden";
	$('.hidedd'+ipdinvreq).hide();
	setTimeout(function() {
//		document.getElementById("addchargebtn").style.visibility="visible";
		$('.hidedd'+ipdinvreq).show();
	}, 5000);
	
	
	document.getElementById("addchargehead").innerHTML = cname;
	
	if(whopay=='Third Party'){
		
		document.getElementById("payBuy1").checked = true;
		document.getElementById("payBuy").checked = false;
	}else{
		document.getElementById("payBuy").checked = true;
		document.getElementById("payBuy1").checked = false;
	}
	
	if(ward==''){
		ward='OPD';
	}
	document.getElementById("addinvnewchargehead").innerHTML = cname + " / Payee:" + selftp + " / " + ward;

	var url = "truncateIpdDashboard";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showAddchargePopupinvRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
	
}

function showAddchargePopupinvRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			addOutoInvestigationCharge();
			//$('#addchargepopupinv').modal( "show" );
		}
	}
	
}


function addOutoInvestigationCharge(){
	var url = "outochargeInvestigation?selectedid="+ipdinvid+"&ipdclientid="+ipdclientid+"&ipdinvreq="+ipdinvreq+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addOutoInvestigationChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function addOutoInvestigationChargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//setCashDesk();
			setinvipdopdCashDesk();
			$('#addchargepopupinv').modal( "show" );
		}
	}
	
}

function filterCharges(chargetype){
	
			
		  	var url = "chargeIpd?chargetype="+chargetype+"&ward="+ward+"&tpid="+ipdtpid+"&clientId="+ipdclientid+"";
			  
			 
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
		document.getElementById('charge').disabled = 'disabled';
	}
	
	var masterchargetype = document.getElementById('masterchargetype').value;
	
	var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+"&masterchargetype="+masterchargetype+" ";
	
	//var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+" ";
	   
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
			
	         document.getElementById('charge').value = currencySign+ data[0]; 
				ipdcharge =  data[0];
				document.getElementById('hdncharge').value =  data[0]; 
				var charge =  data[0];
				calcamount(charge);
			
			if(document.getElementById('chargeTYpe').value==0){
				document.getElementById('charge').value = '';
				document.getElementById('amount').innerHTML = '';
			}
			document.getElementById('isindisharecharge').value = data[1]; 
		}
	}
	
}


function calcmanualcharge(){
document.getElementById('hdncharge').value = document.getElementById('charge').value;
if(document.getElementById('mannualcharge').value=='' || document.getElementById('hdncharge').value==''){
	document.getElementById('addchargebtn').disabled = 'disabled';
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



function setChargeAmount(){
	
	var chargetype = document.getElementById("chargeTYpe").value;
	var quantity = document.getElementById("quantity").value;
	var charge = document.getElementById("hdncharge").value;
	var mannualcharge = document.getElementById("mannualcharge").value;
	var date =document.getElementById("date").value;
	var packageid = document.getElementById("packageid").value;
	var payby = $("input[name='payBuy']:checked").val();
	
	var masterchargetype =  $("#masterchargetype option:selected").text();
	if(document.getElementById('ipdpackage').value!=0){
		masterchargetype = $("#ipdpackage option:selected").text();
	}
	
	var markappointment = 1;
	
	//akash 02 feb 2018
	var visitingconsulatntdr =0;
	var isindisharecharge = document.getElementById("isindisharecharge").value;
	if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'){
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
				document.getElementById('ipdpackage').value = 0;
				   document.getElementById("chargeTotalajax").innerHTML = req.responseText;
				   document.getElementById('packageid').value="";
					setCashDesk();		
				 
				
				
			}
		}
	}
	
	function getInventoryProductStock(){
		var masterchargetype = document.getElementById("masterchargetype").value;
		var prodid = document.getElementById("chargeTYpe").value;
	
		var flag = false;
		var compulsary_con=document.getElementById("compulsaryconsultant").value;
		var isindisharecharge = document.getElementById("isindisharecharge").value;
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
						/*jAlert('error', 'No more stock!!', 'Error Dialog');
		
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);*/
						setChargeAmount();
					}
				}else{
					setChargeAmount();	
				}
			}
		}
	}
	
	
	function setCashDesk(){
		var selectedUser = ipdclientid;
		var cookiecommencing = '';
		var cookieSelectedAppointmentid = 0;
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		/*var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;*/
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
					   document.getElementById("packageid").value="";
							
					  // patientpkgspan		
					 //  showInvtigationPackageList();
					
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

		
		function createOpdInvAddChargeAndUpdateAccount(){
			document.getElementById('complocationid').value = 1;
			createChargeAndUpdateAccount('cash');
			
		}
		
		var gcash = '';
		function createChargeAndUpdateAccount(action){
			gcash = action;
			
			var clientid = ipdclientid; 
			var practitionerid = ipdpractitionerid;
			var clientName = ipdclientname;
			var practitionerName = ipdpractitionername;
			var appointmentid = '0';
			var tratmentepisodeid = '0';
			var treatmenntsessions = '0';
			//var location = document.getElementById('complocationid').value;
			var location = 1;
			var date= document.getElementById("date").value;
			var ipd = 1;
			
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
				var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&gpriscid="+gpriscid+"&ginvstid="+ginvstid+"&date="+date+"&ginventeredcharges="+ginventeredcharges+"";
				   
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
					
					$('#addchargepopupinv').modal( "hide" );
					
					$('#baselayout1loaderPopup').modal( "hide" );
					
					jAlert('success', 'Charge added successfully.', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					window.location.reload();
					
					}
				}
				
			}
		}
		


//set redirectToCreateCharge
		function redirectToCreateCharge1(invid,cid,pid,pname,cname,whopay){
			
			//alert(editwhopay);
			document.getElementById('accountChargeClientid1').value = cid;
			document.getElementById('accountchargethirdparty1').value = "All";
			document.getElementById('accountchargetransactionType1').vlue = "All";
			document.getElementById('accountsLocationid1').value = "All";
			document.getElementById('accountclientid1').value = cname;
			document.getElementById('accountpayby1').value = whopay;
			
			
				var t = 'formtarget';


				
				var left = (screen.width / 2) - (700 / 2);
				var top = (screen.height / 2) - (550 / 2);
				var oldwindow = window.open("", t,
						"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
								+ ",top=" + 50);
				
				oldwindow.focus();
				
				
				
				
				document.getElementById('accountchargefrm1').submit();
			
			
			
			
		}
		
		
		function redirectToRecordPayment2(invid,cid,pid,pname,cname,whopay){
			
			document.getElementById('accountpaymentClientid2').value = cid;
			document.getElementById('accountpaymentthirdparty2').value = "All";
			document.getElementById('accountpaymenttransactionType2').vlue = "All";
			document.getElementById('accountspaymentLocationid2').value = "All";
			document.getElementById('accountpaymentclientid2').value = cname;
			document.getElementById('accountspaymentfromDateid2').value = "";
			document.getElementById('accountspaymentfromDateid2').value = "";
			document.getElementById('accountPaymentPayby2').value = whopay;
			
		var t = 'formtarget';


			
			var left = (screen.width / 2) - (700 / 2);
			var top = (screen.height / 2) - (550 / 2);
			var oldwindow = window.open("", t,
					"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
							+ ",top=" + 50);
			
			oldwindow.focus();
			
			
			document.getElementById('accountpaymentfrm2').submit();
			
			
			
		}
		
		
    function collectData(invsid,chargeraised){
    
    	/*if(chargeraised!=0){
        
		    }else{
		    	jAlert('error', 'Please add charge.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
				}, alertmsgduration);
		    }*/
    	
    	if(!confirm("Are You Sure? You Want To Collect Investigation!")){
    	
    	}else{
    		 var url = "collectInvestigation?selectedid="+invsid+"";
   		  
    		 
    	 		if (window.XMLHttpRequest) {
    	 				req = new XMLHttpRequest();
    	 			}
    	 			else if (window.ActiveXObject) {
    	 				isIE = true;
    	 				req = new ActiveXObject("Microsoft.XMLHTTP");
    	 			}   
    	 		               
    	 		    req.onreadystatechange = collectDataRequest;
    	 		    req.open("GET", url, true); 
    	 		              
    	 		    req.send(null);
    	}
    	
    
    }		
    function collectDataRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					window.location.reload();
				}
			}
		}
    
    
    function showInvstTypeNameData(sectionid){
    	   var url = "invtypeInvestigation?sectionid="+sectionid+"";
 		  
  		 
   		if (window.XMLHttpRequest) {
   				req = new XMLHttpRequest();
   			}
   			else if (window.ActiveXObject) {
   				isIE = true;
   				req = new ActiveXObject("Microsoft.XMLHTTP");
   			}   
   		               
   		    req.onreadystatechange = showInvstTypeNameDataRequest;
   		    req.open("GET", url, true); 
   		              
   		    req.send(null);
    	
    }
    
    function showInvstTypeNameDataRequest(){
    	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('invtypediv').innerHTML = req.responseText;
			}
		}
    }
    
    
    function showPackageInvstTypeNameData(sectionid){
 	  // var url = "packageinvtypeInvestigation?id="+sectionid+"";
    	 var url = "packageinvtypechkInvestigation?id="+sectionid+"";
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showPackageInvstTypeNameDataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
 	
 }
 
 function showPackageInvstTypeNameDataRequest(){
 	if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				var data=str.split('#$');
				document.getElementById('invtypediv').innerHTML = data[0];
//				document.getElementById('invsttype').value=data[1];
				showNameData1(data[1]);	
//				setTimeout(() => {  showNameData1(data[1]); }, 2000);
				
			}
		}
 }
    
    
    function getinvstemplate(id){
var sure=confirm("Are you sure? Adding templete will erase your current data!");
    	
    if(sure){
    	var url="getinvstemplateInvestigation?id="+id+"";
        if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    	}
    	else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	}   
                   
        req.onreadystatechange = getinvstemplateRequest;
        req.open("GET", url, true); 
                  
        req.send(null);
    }	
	
}

function getinvstemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discadvnotes=''
					/*nicEditors.findEditor( "templatesec" ).getContent();	*/  
				discadvnotes=discadvnotes+req.responseText;        
				nicEditors.findEditor('templatesec').setContent(discadvnotes);	  	          
         }
	}	 
}


function openAprovedInvestigation(id){
	var url="getaprovedinvestigationIpdDashboard?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = openAprovedInvestigationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function openAprovedInvestigationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('investtbody').innerHTML = req.responseText;   
				$('#investipop').modal( "show" );
	         }
		}	 
	}
		


//ipd and opd add charges

function setinvipdopdCashDesk(){
	var selectedUser = ipdclientid;
	var cookiecommencing = '';
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
	               
	    req.onreadystatechange = setinvipdopdCashDeskRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);


	}

	function setinvipdopdCashDeskRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("cashDesk31").innerHTML = req.responseText;
				   document.getElementById('chargeTotal3').value = document.getElementById('hiddenTotal').value;
				   
				   if(clinicuserid=='sevenstar'){
					  if( machineinvlist!='0'){
						 /* var tmp = machineinvlist.split(',');
						  var tmp1 = mchinereqidlist.split(',');
						  for(i=0;i<tmp.length;i++){
							  SyncTestRegn(tmp[i],tmp1[i]);
						  }*/
						  
						  SyncTestRegn();
						 
					  }
				   }
				 //  document.getElementById("packageid3").value="";
						
							
				
			}
		}
	}

	
	function showAddipdopdinvchargePopupinv(invid,cid,pid,pname,cname,whopay,invreq){
		ipdinvid = invid;
		ginvstid = invid;
		ipdclientid = cid;
		ipdpractitionerid = pid;
		ipdclientname = cname;
		ipdpractitionername = pname;
		invstwhopay = whopay;
		ipdinvreq = invreq;
		
		document.getElementById("addchargehead").innerHTML = cname;
		
		if(whopay=='Third Party'){
			
			document.getElementById("payBuy1").checked = true;
			document.getElementById("payBuy").checked = false;
		}else{
			document.getElementById("payBuy").checked = true;
			document.getElementById("payBuy1").checked = false;
		}
		

		var url = "truncateIpdDashboard";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAddipdopdinvchargePopupinvRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
		
		
	}

	function showAddipdopdinvchargePopupinvRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				addOutoipdopdInvestigationCharge();
				//$('#addchargepopupinv').modal( "show" );
			}
		}
		
	}
	
	function addOutoipdopdInvestigationCharge(){
		var url = "outochargeInvestigation?selectedid="+ipdinvid+"&ipdclientid="+ipdclientid+"&ipdinvreq="+ipdinvreq+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addOutoipdopdInvestigationChargeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function addOutoipdopdInvestigationChargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				setinvipdopdCashDesk();
				$('#addchargepopupipdopdinv').modal( "show" );
			}
		}
		
	}
	
	
	
	function createipdopdChargeAndUpdateAccount(action){
		gcash = action;
		
		var clientid = ipdclientid; 
		var practitionerid = ipdpractitionerid;
		var clientName = ipdclientname;
		var practitionerName = ipdpractitionername;
		var appointmentid = '0';
		var tratmentepisodeid = '0';
		var treatmenntsessions = '0';
		//var location = document.getElementById('complocationid3').value;
		var location =1; 
		var date= document.getElementById("date").value;
		var ipd = 1;
		
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
			
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&gpriscid="+gpriscid+"&ginvstid="+ginvstid+"&date="+date+"&ginventeredcharges="+ginventeredcharges+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createipdopdChargeAndUpdateAccountRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		
		
		
	}

	function createipdopdChargeAndUpdateAccountRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
			var invoiceid = req.responseText;
			
			if(gcash=='cash'){
				setInstantCashDesk(invoiceid);
			}else{
				
				$('#addchargepopupinv').modal( "hide" );
				
				$('#baselayout1loaderPopup').modal( "hide" );
				
				jAlert('success', 'Charge added successfully.', 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
				window.location.reload();
				
				}
			}
			
		}
	}
	
	
	
	function openhiddendiv(id){
		
		if(document.getElementById(id).style.display==''){
			document.getElementById(id).style.display='none';
		}else{
			document.getElementById(id).style.display='';
		}
	}
	
	
	var ztid=0;
	
	function savenewinvsName(){
		
		var invstypeid= document.getElementById("invsttype").value;
		var newinvsname= document.getElementById("newinvsname").value;
		ztid =invstypeid;
		if(invstypeid=='0' || invstypeid==''){
			
						jAlert('error', 'Please select investigation type!', 'Error Dialog');
									
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration);
		}else if(newinvsname=='' || newinvsname=='0'){
			
						jAlert('error', 'Please enter investigation name!', 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
		} 	
		
		else {
			
			var url="savenewinvsInvestigation?invstypeid="+invstypeid+"&newinvsname="+newinvsname+"";    
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savenewinvsNameRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}
		
	}
	
	
	function savenewinvsNameRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById("hidedivnew").style.display='none';
				jAlert('success', 'New Investigation Name Added!', 'success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
				showNameData(ztid);
				
				
			}
		}

	}

	
	
	
	
	
	
	function createinvestigationopdinstantcashdesk(){
		document.getElementById('complocationid').value=1;
		var clientid = read_cookie("cookieClientId"); 
		var practitionerid = read_cookie("cookiePractitionerId");
		var clientName = read_cookie("cookieUserName");
		var practitionerName = read_cookie("cookiePractitioner");
		var appointmentid = 0;
		var tratmentepisodeid = read_cookie("cookieTreatmentEpisode");
		var treatmenntsessions = read_cookie("cookieTreatmentEpidodeSessions");
		var location = document.getElementById('complocationid').value;
		var action = 'cash';
		if(location==0){
			jAlert('error', 'Please select location.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var ipd = 0;
			
			var ginventeredcharges = '0~';
			 $('.invunitchargecase').each(function() { //loop through each checkbox
				 ginventeredcharges = ginventeredcharges + this.value + '~';               
            });  
			
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&ginvstid="+ginvstid+"&ginventeredcharges="+ginventeredcharges+" ";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createinvestigationopdinstantcashdeskRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
}
	function createinvestigationopdinstantcashdeskRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
				var invoiceid = req.responseText;
				
				
					setopdInstantCashDeskforinvestigation(invoiceid);
				
			}
		}
		
	}
	
	
	function setopdInstantCashDeskforinvestigation(invoiceid){
		
		document.getElementById('icashClientid').value = pppid; 
		document.getElementById('icashclientname').value = pppcname;
		document.getElementById('icashPayby').value = pppwhopay;
		document.getElementById('icashLocationid').value = 1;
		document.getElementById('icashAppointmentid').value = 0;
		document.getElementById('icashinvoiceid').value = invoiceid;
		
			
		
		
			
			document.getElementById('cashdeskfrm2').submit();
	}
	
	
	//elab api
	   function SyncTestRegn() {
		   
		   var InvestigationIds = '132,941,147,148';
		   machineinvlist = machineinvlist.substring(0, machineinvlist.length - 1);
           var objsplit = machineinvlist.split(',');
           
           var tobj = mchininvcharges.split(',');
           
           var objTestRegnTrans = [];
           for (i = 0; i < objsplit.length; i++) {
               var tmpTrans = {
                   "TestID": objsplit[i],
                   "TestPrice": tobj[i],
                   "TestRegnID": 0,
                   "TestProfileID": 0,
                   "BarCode": "",
                   "IsPrint": false,
                   "CreatedDate": convertToJSONDate(elabreqdate),
                   "ModifiedDate": "\/Date(1513150939268)\/",
                   "CreatedByUserID": 0,
                   "ModifiedByUserID": 0,
                   "TestType": 1,
                   "TestOrProfileID": objsplit[i],
                   "EntityID": 3,
                   "EntityTypeID": 3,
                   "DisplayOrder": 0,
                   "WorkFlowCurrentState": 1,
                   "IsDispatched": false
               };
               objTestRegnTrans.push(tmpTrans);
           }

	        var msg = JSON.stringify(
			{  
	   "registerTest":{  
	      "ExternalRegn":[  
	         {  
	            "Title":"Ms.",
	            "PatientName":elabspatientname,
	            "Age":elabage,
	            "AgeType":elabagetype,
	            "Gender":elabgender,
	            "MobileNumber":"",
	            "TelNumber":"",
	            "PatientEmailID":"",
	            "ExternalPatientID":elabspatientid,
	            "TestRegnID":0,
	            "VoucherNo":"",
	            "LabCode":elabsreqid,
	            "RegnDateTime":"\/Date(1513150939268)\/",
	            "refDocName":elabdoctorname,
	            "AffiliationID":0,
	            "PatientAddress":elabdept,
	            "ExternalRegnCollectionID":"3",
	            "EntityID":0,
	            "EntityTypeID":0,
	            "TransferState":0,
	            "UserID":"c0fbb40a-caa7-49a2-8cce-bbb19368696d",
	            "SelectedTests":"",
	            "UrgencyCharges":false,            
	            "CollectionCentreID":3,
	            "CreatedDate":convertToJSONDate(elabreqdate),
	            "CommissionAmount":0.0,
	            "Barcode":"04/00103",
	            "PatientType":elabspatienttype,
	            "IsSynch":false,
	            "LabID":"9a3874ce-20f8-4cea-8fac-b8b4877a17aa",
	            "GroupBarcode":[  

	            ],
	            "TestRegnTrans": objTestRegnTrans,
	            "TestParamValue":[  

	            ]
	         }
	      ],
	      "Task":1,
	      "LabID":"9a3874ce-20f8-4cea-8fac-b8b4877a17aa"
	   }
	})
			
			;
	        var URL = "http://Live.elabassist.com/Services/MachineService.svc/ExternalRegistrationSynchForLab";
	        $.ajax({
	            type: "POST",
	            url: URL,
	            crossDomain: true,
	            data: msg,
	            dataType: "json",
	            contentType: "application/json; charset=utf-8",
	            // beforeSend: function () { LockScreen(); },
	            success: function (data) {
	                console.log("success"+msg);
	            },
	            error: function (result) {
					console.log("error");
	            }
	        });
	    }
	   
	   
	   
	   
	   function convertToJSONDate(strDate){
		   var mt = strDate.split(' ');
		   strDate = mt[0];
		   var splitted = strDate.split("-");
		   //var dt = new Date(splitted[2],splitted[0],splitted[1]);
		   var newDate = new Date(Date.UTC(splitted[0], splitted[1], splitted[2]));
		   return '/Date(' + newDate.getTime() + ')/';
		 }
	   
	   
	   function showinvchargedetails(dbid,id,val){
			var url = "invnewchargeCompleteApmt?dbid="+dbid+"&charge="+val+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = showinvchargedetailsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		   
		   
	   }
	   
	   
	   function showinvchargedetailsRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					setinvipdopdCashDesk();
				}
			}
		   
	   }
	   function gettreattemplate(id){

		   var url="treatmenttempTreatmentEpisode?id="+id+"";
		     if (window.XMLHttpRequest) {
		     req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
		   isIE = true;
		   req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		                
		     req.onreadystatechange = getinvstemplateRequest1;
		     req.open("GET", url, true); 
		               
		     req.send(null);
		 }


		 function getinvstemplateRequest1(){
		  if (req.readyState == 4) {
		    if (req.status == 200) {
		                 
		      var discadvnotes= nicEditors.findEditor( "treatmentgiven" ).getContent();   
		      discadvnotes=discadvnotes+req.responseText;        
		      nicEditors.findEditor('treatmentgiven').setContent(discadvnotes);              
		           }
		   }  
		  }
		 
		 function gettreattemplateEme(id){

			   var url="treatmenttempTreatmentEpisode?id="+id+"";
			     if (window.XMLHttpRequest) {
			     req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
			   isIE = true;
			   req = new ActiveXObject("Microsoft.XMLHTTP");
			  }   
			                
			     req.onreadystatechange = getinvstemplateRequest1;
			     req.open("GET", url, true); 
			               
			     req.send(null);
			 }


			 function getinvstemplateRequest1(){
			  if (req.readyState == 4) {
			    if (req.status == 200) {
			                 
			      var discadvnotes= nicEditors.findEditor( "emercontdetail" ).getContent();   
			      discadvnotes=discadvnotes+req.responseText;        
			      nicEditors.findEditor('emercontdetail').setContent(discadvnotes);              
			           }
			   }  
			  }
			 
		 
		 function getTPInvestigations(tpvalue){
			 var url="gettpinvsttypeInvestigation?tpid="+tpvalue+"";
		     if (window.XMLHttpRequest) {
		     req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
		   isIE = true;
		   req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		                
		     req.onreadystatechange = getTPInvestigationsRequest;
		     req.open("GET", url, true); 
		               
		     req.send(null);
		 
		 }
		 
		 function getTPInvestigationsRequest(){
			 if (req.readyState == 4) {
				    if (req.status == 200) {
				    	document.getElementById("tpinvstlist").innerHTML=req.responseText;	
				    	 $("#tpinvstname").trigger("chosen:updated");
	    			 	  $(".chosen-select").chosen({allow_single_deselect: true});
				    }
			 	}   
		 }
		 
		 function checkifok(){
			 var status=true;
			 var invsttype=document.getElementById("invsttype").value;
			 if(invsttype==''){
				 jAlert('error', 'Please Select  Type.', 'Error Dialog');
				 return false;
			 }
			 var tpid=document.getElementById("thirdPartyCompanyName").value;
			 if(tpid==''){
				 jAlert('error', 'Please Select  TP.', 'Error Dialog');
				 return false;
			 }
			 var tpinvst=document.getElementById("tpinvstname").value;
			 if(tpinvst==''){
				 tpinvst=document.getElementById("nametext").value;
			 }
			 if(tpinvst==''){
				 jAlert('error', 'Please Select  TP Investigation Type.', 'Error Dialog');
				 return false; 
			 }
			 return true;
		 }
		 
		 
		 
		 
		 
		 var globelid='';

		 function getinvstemplate1(id,index){
			 globelid=index;
				var url="getinvstemplateInvestigation?id="+id+"";
		    if (window.XMLHttpRequest) {
		    req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = getinvstemplate1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}

		function getinvstemplate1Request(){
		if (req.readyState == 4) {
				if (req.status == 200) {
							          
						var discadvnotes=''
							/*nicEditors.findEditor( "templatesec" ).getContent();	*/  
						discadvnotes=discadvnotes+req.responseText;        
						nicEditors.findEditor('comment'+globelid).setContent(discadvnotes);	  	          
		         }
			}	 
		}

		
		function setclientidbyClinicalnotesInv(){
			patientId=document.getElementById('cid').value;	
			diaryuserId=document.getElementById('diaryUser').value;	
		}
		
		function collectErrorForAddCharge() {
			jAlert('error', 'Please add charge before collect!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		
		
		function showInvestigation123(){
			
		    document.getElementById('jobtitle').value = 'Pathlab';
			//document.getElementById("prisctable").innerHTML = '';
			removeSession();
			getPriscClientInfo(patientId);
			window.$('#investigationpopup').modal( "show" );
		

	}

	function showopdInvestigation123(){
		patientId = pppid;
		editClientName = pppcname;
		editwhopay = pppwhopay;


		showInvestigation123();
	}		
	
	
	
	 function getInvestigationGynicData(){
		var url="getinvsgynicdataBookAppointmentAjax";
	    if (window.XMLHttpRequest) {
	    req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getInvestigationGynicDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}

	function getInvestigationGynicDataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
					var investigation_advice = document.getElementById('investigation_advice').value;
					if(investigation_advice==''){
						investigation_advice = req.responseText;
					}else{
						investigation_advice = investigation_advice+","+req.responseText;
					}
					document.getElementById('investigation_advice').value = investigation_advice;
					$('#baselayout1loaderPopup').modal( "hide" );
					$('#investigationpopup').modal( "hide" );
	         }
		}	 
	}
		
	function selectedinv() {
		var selectedinvstid = 0;
	     $('.sshubh').each(function() { //loop through each checkbox
	        // this.checked = true;  //select all checkboxes with class "checkbox1" 
	        if(this.checked){
	        	selectedinvstid = selectedinvstid + ',' + this.value;
	        }
	         
	     });
	     var temp="0";
	     if(selectedinvstid.length>0){
	     var data=selectedinvstid.split(',');
	     
	     for(var i=0;i<data.length;i++){
	    	 if(data[i]!='0'){
	    		 if(temp=='0'){
	    			 temp=data[i];	 
	    		 }else{
	    			 temp=temp+","+data[i];
	    		 }
	    		 
	    	 }
	    	 
	     }
	     }
	     showNameData1(temp);
	     
	}
	

	function showNameData1(id){
		var dataObj={
				
				
			  	"selectedid":""+id+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "showname1Investigation",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
				
				document.getElementById('invstnametd').innerHTML = data.obdata;
				
				document.getElementById('invstreporttype').value = document.getElementById('rpttype').value;
				
				
				$('#baselayout1loaderPopup').modal( "hide" );
			
				//auto selected investigation
				document.getElementById('selecctall').checked = true;
				
				  $('.case').each(function() { //loop through each checkbox
	                    this.checked = true;  //select all checkboxes with class "checkbox1"               
	                });
				
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
function addInvest(val) {
	var invstid = val;
	

	var url="InvestigationMaster?invstid="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addInvestRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function addInvestRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  
			  $("#addinvst").modal( "show" );
	          
		 }
	}
	
}
	

function addnewchargename() {
	
	var tpid=document.getElementById("tpname").value;
	var ward=document.getElementById("ward").value;
	var chargename=document.getElementById("chargename").value;
	var code=document.getElementById("code").value;
	var amount=document.getElementById("amount").value;
	var url="addnewInvstInvestigationMaster?tpid="+tpid+"&chargename="+chargename+"&ward="+ward+"&code="+code+"&amount="+amount+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
   req.onreadystatechange = addnewchargenameRequest;
	req.open("GET", url, true);
	req.send(null);
	
}

function addnewchargenameRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  
			  $("#addinvst").modal( "hide" );
	          
		 }
	}
}
	
	function showdischargeInvestigation(patientId){
	
	  document.getElementById('jobtitle').value = 'Pathlab';
		//document.getElementById("prisctable").innerHTML = '';
		removeSession();
		if(document.getElementById('isnewgynicform11')){
			//  27-06-2020 is it from gynic form
			//var patientId = document.getElementById("clientid").value;
			getPriscClientInfo(patientId);
		}else{
			getPriscClientInfo(patientId);
		}
		
//		jQuery.noConflict();
		window.$('#investigationpopup').modal( "show" );
	

}


function editwriteup1(id,action,cid,pid,gpname,tstatus,cdate,dept){
	var url = "templateinvInvestigation";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = editwriteup1Request;
		    req.open("GET", url, true); 
		    
		    req.send(null);
}
	function editwriteup1Request(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					}
					}
					}
					
					
 function getinvstemplate1(id){
var sure=confirm("Are you sure? Adding templete will erase your current data!");
    	
    if(sure){
    	var url="getinvstemplateInvestigation?id="+id+"";
        if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    	}
    	else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	}   
                   
        req.onreadystatechange = getinvstemplate1Request;
        req.open("GET", url, true); 
                  
        req.send(null);
    }	
	
}

function getinvstemplate1Request(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discadvnotes=''
					/*nicEditors.findEditor( "templatesec" ).getContent();	*/  
				discadvnotes=discadvnotes+req.responseText;        
				nicEditors.findEditor('template_text').setContent(discadvnotes);	  	          
         }
	}	 
}					
					