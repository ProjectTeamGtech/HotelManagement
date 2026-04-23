var editpriscid = 0;
var editparentpriscid = 0;
var istemplate = 0;
var dddose = 0;

var treatmentadvc=0;
var treatmentgivensatus=0;
function showpriscription(){
	if(document.getElementById("prisc_location_list")){
		var prisc_location_list = document.getElementById("prisc_location_list").value;
		if(prisc_location_list==1){
			var bydefaultlocationid = document.getElementById("bydefaultlocationid").value;
			document.getElementById("requestlocationid").value = bydefaultlocationid;
		}else{
			/*document.getElementById("requestlocationid").value = 0;*/
		}
	}
	
	editparentpriscid = 0;
	istemplate = 0;
	document.getElementById("prisctable").innerHTML = '';
	getPriscClientInfo(patientId);
//	jQuery.noConflict();
	window.$('#priscriptionpopup').modal( "show" );
}


function showopdpriscription(){
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	//showpriscription();
	repeatePriscDateAjax(patientId,diaryuserId,editcondition_id);
	
	$('#priscriptionpopup').modal( "show" );

}

function saveTemplae(){
	istemplate = 1;
	uptemplate = 0;
	var mdtemplatename = document.getElementById("mdtemplatename").value;
	if(mdtemplatename==''){
		jAlert('error', 'Please enter template name!!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		insertPriscription();
	}
	
}

function showeditpriscriptionpopup(){
	getPriscClientInfo(patientId);
	
	$('#priscriptionpopup').modal( "show" );
	
}

function getPriscClientInfo(id){
	if(document.getElementById('isnewgynicform11')){
		diaryuserId = document.getElementById("practitionerid").value;
	}
	if(document.getElementById('consulationForm')){
		diaryuserId = document.getElementById("practitionerId").value;
	}
	var url = "getInfoClient?clientId="+id+"&diaryuser="+diaryuserId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getPriscClientInfoRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getPriscClientInfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			if(document.getElementById('invstlistid')){
				document.getElementById('invstlistid').innerHTML = '';
			}
			var info = req.responseText;
			var temp = info.split("*");
			//document.getElementById("priscclientname").value = temp[0];
			//document.getElementById("regno").value = patientId;
			
			 var date =  document.getElementById("priscdate").value;
			 var year = date.split('/');
			
			 var dob = temp[5];
			// var dobyear = dob.split('/');
			 var age =temp[5]; //year[2] - dobyear[2];
			 
			 var strname ="";
			 if(temp[11]=='0'){
				  strname = 'NAME: '+temp[0]+'  | AGE:'+age+' / '+temp[9]+' </b>';
			 }else{
				
				  strname = 'NAME: '+temp[0]+'  | AGE:'+age+' / '+temp[9]+' | Weight:'+temp[11]+'</b>';
			 }
			 
			 
			 document.getElementById("priscmyModalLabel").innerHTML = strname;
			 
			 document.getElementById("datetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 
			 document.getElementById("priscdoctornameid").innerHTML = temp[10];
			 
			 //investigation client info
			 document.getElementById("invstcmyModalLabel").innerHTML = strname;
			 document.getElementById("invstdatetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 document.getElementById("invstpriscdoctornameid").innerHTML = temp[10];
			 
			 //ot equipment client info
			 /* document.getElementById("eotpriscmyModalLabel").innerHTML = strname;
			 document.getElementById("eotdatetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 document.getElementById("eotpriscdoctornameid").innerHTML = temp[10];*/
			 
			 
			 //document.getElementById("priscclientage").value = age;
			 
			// document.getElementById("priscaddress").value = temp[1];
			
		/*	document.getElementById("infoAddress").innerHTML = ' '+temp[1];
			document.getElementById("infoSecondAdress").innerHTML = temp[2];
			document.getElementById("infoTown").innerHTML = temp[3];
			document.getElementById("infoPostcode").innerHTML = temp[4];
			document.getElementById("infoDob").innerHTML = temp[5];
			document.getElementById("infotp").innerHTML = temp[6];
			document.getElementById("infomobile").innerHTML = temp[7];
			document.getElementById("infoemail").innerHTML = temp[8];*/
			
			// getpriscriptionDetails();
			
			removeSession();
			
         }
	}
}


function getPriscClientInfoWithSession(id){
	
	var url = "getInfoClient?clientId="+id+"&diaryuser="+diaryuserId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getPriscClientInfoWithSessionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getPriscClientInfoWithSessionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			var info = req.responseText;
			var temp = info.split("*");
			//document.getElementById("priscclientname").value = temp[0];
			//document.getElementById("regno").value = patientId;
			
			 var date =  document.getElementById("priscdate").value;
			 var year = date.split('/');
			
			 var dob = temp[5];
			 var dobyear = dob.split('/');
			 var age = year[2] - dobyear[2];
			 
			 var strname = 'NAME: '+temp[0]+'  | AGE:'+age+' / '+temp[9]+'</b>';
			 
			 document.getElementById("priscmyModalLabel").innerHTML = strname;
			 
			 document.getElementById("datetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 
			 document.getElementById("priscdoctornameid").innerHTML = temp[10];
			 
			 //investigation client info
			 document.getElementById("invstcmyModalLabel").innerHTML = strname;
			 document.getElementById("invstdatetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 document.getElementById("invstpriscdoctornameid").innerHTML = temp[10];
			 
			 //ot equipment client info
			  document.getElementById("eotpriscmyModalLabel").innerHTML = strname;
			 document.getElementById("eotdatetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 document.getElementById("eotpriscdoctornameid").innerHTML = temp[10];
			
         }
	}
}

function getPriscClientInfoForEdit(id){
	
	var url = "getInfoClient?clientId="+id+"&diaryuser="+diaryuserId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getPriscClientInfoForEditRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getPriscClientInfoForEditRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById('invstlistid').innerHTML = '';
			var info = req.responseText;
			var temp = info.split("*");
			//document.getElementById("priscclientname").value = temp[0];
			//document.getElementById("regno").value = patientId;
			
			 var date =  document.getElementById("priscdate").value;
			 var year = date.split('/');
			
			 var dob = temp[5];
			// var dobyear = dob.split('/');
			 var age =temp[5]; //year[2] - dobyear[2];
			 
			 var strname ="";
			 if(temp[11]=='0'){
				  strname = 'NAME: '+temp[0]+'  | AGE:'+age+' / '+temp[9]+' </b>';
			 }else{
				
				  strname = 'NAME: '+temp[0]+'  | AGE:'+age+' / '+temp[9]+' | Weight:'+temp[11]+'</b>';
			 }
			 
			 
			 document.getElementById("priscmyModalLabel").innerHTML = strname;
			 
			 document.getElementById("datetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 
			 document.getElementById("priscdoctornameid").innerHTML = temp[10];
			 
			 //investigation client info
			 document.getElementById("invstcmyModalLabel").innerHTML = strname;
			 document.getElementById("invstdatetimeid").innerHTML = document.getElementById("priscdateandtime").value;
			 document.getElementById("invstpriscdoctornameid").innerHTML = temp[10];
			
         }
	}
}


var catgid = 0;
function setEditMedicineName(id){
	catgid = id;
	
	var url = "mdicineNotAvailableSlot?selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setEditMedicineNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setEditMedicineNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("mdicinediv").innerHTML = req.responseText;
			document.getElementById("mdicinename").value = catgid;
			
			
		}
	}
}

function setMedicineName(id){
	$('#baselayout1loaderPopup').modal( "show" );
	var url = "mdicineNotAvailableSlot?selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setMedicineNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setMedicineNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("mdicinediv").innerHTML = req.responseText;
			
			$("#mdicinename").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
	
			
			$('#baselayout1loaderPopup').modal( "hide" );
			
			
		}
	}
}

 


function addTempPriscription(){
	document.getElementById("prischide").style.display = 'none';
	var categoryid = document.getElementById("mdicinecategory").value;
	var mdicinenameid = document.getElementById("mdicinename").value;
	var mdicinenametxt="";
/*	var mdicinenametxt = $("#mdicinename option:selected").text();*/
	//var priscdose = document.getElementById("priscdose").value;
	var priscdose = "0";
	var priscfreq = document.getElementById("priscfreq").value;
	var priscdays = document.getElementById("priscdays").value;
	var prisctype =  document.getElementById("prisctype").value;
	//var prisccode = document.getElementById("prisccode").value;
	var prisccode="0";
	var prisctotal = document.getElementById("prisctotal").value;
	var dosenotes=$("#priscdosenotes option:selected").text();
	var priscdurationtype = "";//document.getElementById("priscdurationtype").value;
	var templatename = document.getElementById("templatename").value;
    var genericName= document.getElementById("genericName").value;
    var medicineName= document.getElementById("medicineName").value;
    var unit= document.getElementById("unit").value;
    var prisccode = document.getElementById("prisccode").value;
    var dddose = document.getElementById("dddose").value;
//  prisctime,priscindivisualremark
    var priscindivisualremark = document.getElementById("priscindivisualremark").value;
    var prisctime = document.getElementById("prisctime").value;
    var prisctimename = $("#prisctime option:selected").text();
    priscdose = prisccode;
    
    var unitextension = document.getElementById("unitextension").value;
    var priscdosenotes = document.getElementById("priscdosenotes").value;
    var priscqty = document.getElementById("priscqty").value;
    var masterdose = document.getElementById("masterdose").value;
    
    if(prisctime=='0'){
    	prisctimename='';
    }
    var isFromSaiMed = false;
	if(document.getElementById("consulationForm")){
		isFromSaiMed = true;
	}
    
    var checking = true;
    	$('.akash').each(function() { 
				if(this.value == mdicinenameid){
				    
				    checking = false;
				} 					
			});
    	//  hidden 11 Apr 18 its come in select tag
    	/*$('.jituradioclass').each(function() { 
			if(this.checked==true){
			    
				priscdose = this.value;
			} 					
		});*/
    	//  28/10/2020 start remove double product adding
    	/*if(!checking){
    		var r = confirm("Product already added! Do you want add same product again");
    		if (r == true) {
    			checking = true;
    		}  
    	}*/
    	//end 
    	var unitexchecking= false;
    	if(unit!='' || unit!='0'){
 			if(unitextension=='0'){
 				unitexchecking=true;
 			}
 		}
    	
    	//  10 May 2018
    	var dosegiventime1 = document.getElementById('dosegiventime1').value;
		var dosegiventime2 = document.getElementById('dosegiventime2').value;
		var dosegiventime3 = document.getElementById('dosegiventime3').value;
    	//var dosegiventime = dosegiventime1+'-'+dosegiventime2+'-'+dosegiventime3;
    	if(!checking){
    		jAlert('error', "Product already added!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						document.getElementById("prischide").style.display = '';
    	}else if(mdicinenameid=='0' && medicineName==''){
				jAlert('error', "Plz select product name!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						document.getElementById("prischide").style.display = '';
			
		}/*else if(priscdose=='0' || priscdose==''){
			 jAlert('error', "Plz select priscription dose!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						document.getElementById("prischide").style.display = '';
		}*/ /*else if(priscdays==0 || priscdays==''){
			
			jAlert('error', "Plz enter priscription days!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 	
			document.getElementById("prischide").style.display = '';
		}*/
		/*else if(priscdosenotes=='0'){
			jAlert('error', "Plz select priscription dose note!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						document.getElementById("prischide").style.display = '';
		}*/
		else if(!isFromSaiMed && priscqty==''){
			jAlert('error', "Please enter qty!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("prischide").style.display = '';
		}else if(!isFromSaiMed && Number(priscqty)<=0){
			jAlert('error', "Please enter qty!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("prischide").style.display = '';
		}
    	
    	
    	/*else if(unitexchecking){
			jAlert('error', "Plz select unit extension!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 	
		}*/else{
			var url = "addtemppriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&mdicinenametxt="+mdicinenametxt+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&priscdays="+priscdays+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prisctotal="+prisctotal+"&priscdurationtype="+priscdurationtype+"&dosenotes="+dosenotes+"&templatename="+templatename+"&genericName="+genericName+"&medicineName="+medicineName+"&unit="+unit+"&priscindivisualremark="+priscindivisualremark+"&prisctime="+prisctime+"&prisctimename="+prisctimename+"&unitextension="+unitextension+"&dosegiventime1="+dosegiventime1+"&dosegiventime2="+dosegiventime2+"&dosegiventime3="+dosegiventime3+"&priscqty="+priscqty+"&masterdose="+masterdose+"&dddose="+dddose+" ";
			
			//var url = "addtemppriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
    		req.onreadystatechange = addTempPriscriptionRequest1;
    		req.open("GET", url, true); 
              
    		req.send(null);
		}
}


function addTempPriscriptionRequest1(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
			document.getElementById('manualdosetimediv').style.display='none';
			document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
			document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value=""  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
			document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
			if(document.getElementById('followupdiv1')){
				document.getElementById('followupdiv1').style.backgroundColor ='yellow';
			}
			document.getElementById("prischide").style.display = '';
			if(document.getElementById("langbyusr")){
				var language = document.getElementById("langbyusr").value;
				  var selectField = document.querySelector("#google_translate_element select");
				  for(var i=0; i < selectField.children.length; i++){
				    var option = selectField.children[i];
				    // find desired langauge and change the former language of the hidden selection-field 
				    if(option.value==language){
				       selectField.selectedIndex = i;
				       // trigger change event afterwards to make google-lib translate this side
				       selectField.dispatchEvent(new Event('change'));
				       break;
				    }
				  }
				}
			
			if(document.getElementById("consulationForm")){
				document.getElementById("mdicinename").className="form-control";
				document.getElementById("mdicinename").value =0;
				document.getElementById("mdicinename").className="form-control chosen";
				$("#mdicinename").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});	
				
			}
		}
	}
	
}


function insertPriscription(action){
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn")){
		if(document.getElementById("prescs_save_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_btn").disabled = true;
		}
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn")){
		if(document.getElementById("prescs_save_print_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_print_btn").disabled = true;
		}
	}
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
	var admission =0;
	/*if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}*/
	
	var priscautoid ="1"; //document.getElementById("paymode2").value;
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscdays = document.getElementById("priscdays").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
    var repeatdate=document.getElementById("repeatdate").value;
    var mdtemplatename = document.getElementById("mdtemplatename").value;
	var requestlocationid = document.getElementById("requestlocationid").value;
	var bamsbckdate = document.getElementById("backDate").value;
	var priscdose=0;
	$('.jituradioclass').each(function() { 
		if(this.checked==true){
		    
			priscdose = this.value;
		} 					
	});
	
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
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', "Please enter qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		if(action==1){
			openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
		}
		//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscautoid="+priscautoid+"&prisctotal="+prisctotal+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+" ";
		
		
		/*if(priscdose==0 && action==0){
				jAlert('error', 'Please Select Dosage.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}*/
		var locations=sessionStorage.getItem("location");
		var isfromemrdashb=0;
		if(document.getElementById("isfromemrdashb")){
			isfromemrdashb = document.getElementById("isfromemrdashb").value;
		}
		var savepriscemr=0;
		var idss=0;
		if(document.getElementById("priscsavemr")){
			savepriscemr=document.getElementById("priscsavemr").value;
			if(savepriscemr==1){
				idss=editAppointId;
			}else if(savepriscemr==2){
				idss=ipdaddmissionid;
			}
		}
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+"&uptemplate="+uptemplate+"&uptemplateid="+uptemplateid+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&isfromemrdashb="+isfromemrdashb+"&saveprisctoemr="+savepriscemr+"&idss="+idss+"&bamsbckdate="+bamsbckdate+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertPriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
}

function insertPriscriptionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById("prisctable").innerHTML = req.responseText;
			
		
			
			showPriscTemplateList();
			
			
			
		}
	}
}

function showPriscTemplateList(){
	var url = "prisctemplateEmr";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showPriscTemplateListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function showPriscTemplateListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("mdtemplatename").value = '';
			document.getElementById("prisctemplatediv").innerHTML = req.responseText;
			
			$("#templatename").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});	
			
			if(document.getElementById("prescs_save_btn")){
				document.getElementById("prescs_save_btn").disabled = false;
			}
			if(document.getElementById("prescs_save_print_btn")){
				document.getElementById("prescs_save_print_btn").disabled = false;
			}
			
			jAlert('success', 'Prescription saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			//  26 March 2018 prisc saved multiple time from ipd so commented
			//addEmrPrisc();	
			
		}
	}
}


function saveparentprisctemplatedata(discharge){
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn")){
		if(document.getElementById("prescs_save_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_btn").disabled = true;
		}
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn")){
		if(document.getElementById("prescs_save_print_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_print_btn").disabled = true;
		}
	}	

	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	//var discharge = 0;
/*	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}*/
	
	var priscautoid ="1"; /*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
    var repeatdate=document.getElementById("repeatdate").value;
    var mdtemplatename = document.getElementById("mdtemplatename").value;
    var bamsbckdate = document.getElementById("backDate").value;
	
	
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
	var locations=sessionStorage.getItem("location");
	var requestlocationid = document.getElementById("requestlocationid").value;
	var isfromemrdashb=0;
	if(document.getElementById("isfromemrdashb")){
		isfromemrdashb = document.getElementById("isfromemrdashb").value;
	}
	if(btndisableflag==false){
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url = "saveparentpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+"&locations="+locations+"&requestlocationid="+requestlocationid+"&bamsbckdate="+bamsbckdate+"&isfromemrdashb="+isfromemrdashb;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveparentprisctemplatedataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    
	}
	
}
var parentsuperlistsizeid=0;
function saveparentprisctemplatedataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//var parentpriscidii=req.responseText;
			var listsizeid = document.getElementById("listsizeid").value;
			parentsuperlistsizeid = listsizeid;
				//alert(listsizeid);
				
				if(document.getElementById("outoprisc").value==1){
					var tstr = "0";
					 $('.nimaicase').each(function() { //loop through each checkbox
		                    //this.checked = true;  //select all checkboxes with class "checkbox1"
						 var t = this.id;
						 tstr = tstr + "," + t;
						// t = parseInt(t)-1;
						
							/*if(listsizeid==2){
								setTimeout(function() {
									$("#popup_container").remove();
									saveTemplatePriscData(t);
								}, alertmsgduration); 
							}else{
								saveTemplatePriscData(t);
							}*/
							
						 
						 
		                });
					// alert(tstr)
					 var mt = tstr.split(',');
					 parentsuperlistsizeid = mt.length;
					 for(m=0;m<mt.length;m++){
						// alert(mt[m])
						 if(m!=0){
							 saveTemplatePriscData(mt[m]); 
						 }
						 
					 }
				}else{
					for(i=0;i<listsizeid;i++){
						saveTemplatePriscData(i)
					}
				}
				if(document.getElementById("prescs_save_btn")){
					document.getElementById("prescs_save_btn").disabled = false;
				}
				if(document.getElementById("prescs_save_print_btn")){
					document.getElementById("prescs_save_print_btn").disabled = false;
				}
				
				//saveChildParentPriscTempData();
				
				//$('#priscriptionpopup').modal( "hide" );
		}
	}
}

function saveChildParentPriscTempData() {
	var url = "savechildparentpriscdataEmr";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = saveChildParentPriscTempDataRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function saveChildParentPriscTempDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}


function saveTemplatePriscData(i){
	if(document.getElementById("outoprisc").value==1){
		setTimeout(function() {
			$("#popup_container").remove();
			//saveTemplatePriscData(t);
		}, 500); 
	}
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

	var mdcinenametxt = document.getElementById("mdcinenametxt"+i).innerHTML;
	var priscfreq = document.getElementById("priscfreq"+i).innerHTML;
	var dosage = document.getElementById("dosage"+i).value;
	var days = document.getElementById("priscday"+i).value;
	var dosenotes = document.getElementById("dosenotes"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		dosenotes = document.getElementById("dosenotes"+i).value;
	}
	var mdicinenameid = document.getElementById("mdicinenameid"+i).innerHTML;
	var priscqty = document.getElementById("drgivenqty"+i).value;
	var dddose ='0';
	if(document.getElementById("dddose"+i)){
		dddose= document.getElementById("dddose"+i).value;
	}
		
	
	
	
	/*if(document.getElementById("drgivenqty"+i)){
		priscqty = document.getElementById("drgivenqty"+i).value;
	}*/
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	/*if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}*/
	
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
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
	
	var prisctimename = document.getElementById("prisctimename"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		prisctimename = document.getElementById("prisctimename"+i).value;
		
	}
	var srno = parseInt(i) +1;
	
	var unitextension ='0';
	if(document.getElementById("uom"+i)){
		unitextension=document.getElementById("uom"+i).value;
	}
	var priscindivisualremark='';
	if(document.getElementById("priscindivisualremark"+i)){
	 priscindivisualremark = document.getElementById("priscindivisualremark"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		priscindivisualremark = document.getElementById("priscindivisualremark"+i).value;
		
	}}
	if(document.getElementById("priscsavemr")){
		savepriscemr=document.getElementById("priscsavemr").value;
		if(savepriscemr==1){
			idss=editAppointId;
		}else if(savepriscemr==2){
			idss=ipdaddmissionid;
		}
	}
	var locations=sessionStorage.getItem("location");
	var url = "savetemplatepriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&mdcinenametxt="+mdcinenametxt+"&priscfreq="+priscfreq+"&dosage="+dosage+"&days="+days+"&dosenotes="+dosenotes+"&mdicinenameid="+mdicinenameid+"&priscqty="+priscqty+"&dddose="+dddose+"&prisctimename="+prisctimename+"&priscindivisualremark="+priscindivisualremark+"&unitextension="+unitextension+"&srno="+srno+"&parentsuperlistsizeid="+parentsuperlistsizeid+"&current_size_data="+i+"&saveprisctoemr="+savepriscemr+"&idss="+idss+"&location="+locations+" ";
	
	
	//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveTemplatePriscDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    
	
}

function saveTemplatePriscDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			jAlert('success', 'Prescription saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//showDischargePopup();
			
			$('#priscriptionpopup').modal( "hide" );
			document.getElementById("priscnotes").innerHTML = req.responseText;
			
		}
	}
}




function insertDischargePriscription(action){
	$("#dashboardloaderPopup").modal('show');
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_btn").disabled = true;
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_print_btn").disabled = true;
	}
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
	//var discharge = 0;
	var discharge = 1;
	var admission =0;
	
	if(discharge==1&&treatmentadvc==1){
		treatmentgivensatus=1;
	}else{
		treatmentgivensatus=0;
	}
	
/*	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	*/
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
	var repeatdate=document.getElementById("repeatdate").value;
	 var mdtemplatename = document.getElementById("mdtemplatename").value;
	 var requestlocationid = document.getElementById("requestlocationid").value;
	  var bamsbckdate = document.getElementById("backDate").value;  //For Bams
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
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		goaction = action;
		//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscautoid="+priscautoid+"&prisctotal="+prisctotal+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+" ";
		
		var priscdose=0;
		$('.jituradioclass').each(function() { 
			if(this.checked==true){
			    
				priscdose = this.value;
			} 					
		});
		
		
		/*if(priscdose==0){
				jAlert('error', 'Please Select Dosage.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}*/
		
		var locations=sessionStorage.getItem("location");
		locations="ipd";
		var jsonipdid = 0;
		
		if(document.getElementById("jsonipdid")){
			jsonipdid = document.getElementById("jsonipdid").value;
		}
		var apmtId =0;
		if(document.getElementById("consulationForm")){
			apmtId = document.getElementById("apmtId").value;
			locations="OPD";
		}
		
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&mdtemplatename="+mdtemplatename+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&treatmentgivensatus="+treatmentgivensatus+"&json_ipdid="+jsonipdid+"&bamsbckdate="+bamsbckdate+"&apmtId="+apmtId;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertDischargePriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
	
}
var priscadc='';
var goaction =0;
function insertDischargePriscriptionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prescs_save_btn").disabled = false;
			document.getElementById("prescs_save_print_btn").disabled = false;
			$("#dashboardloaderPopup").modal('hide');
			
			priscadc=req.responseText;
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//showDischargePopup();
			if(document.getElementById("consulationForm")){
				consultationPriscData();
			}else{
				dischargePriscData();
			}
			
			$('#priscriptionpopup').modal( "hide" );
			
			if(goaction==1){
				openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
			}
			
		}
	}
}
function consultationPriscData(){
	var apmtId = document.getElementById("apmtId").value;
	var url = "consultpriscdataBookAppointmentAjax?apmtId="+apmtId;

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = consultationPriscDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function consultationPriscDataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("dischargedataid").innerHTML = req.responseText;
				document.getElementById("priscnotes").innerHTML=priscadc;
			}
		}
}

function dischargePriscData(){
	var url = "discdataBookAppointmentAjax?treatmentgivensatus="+treatmentgivensatus;

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = dischargePriscDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function dischargePriscDataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			if(treatmentgivensatus==1){
				document.getElementById("dischargedataidtreat").innerHTML = req.responseText;
				document.getElementById("priscnotestreat").innerHTML=priscadc;	
			}else{
				document.getElementById("dischargedataid").innerHTML = req.responseText;
				document.getElementById("priscnotes").innerHTML=priscadc;	
			}
				
				
	         }
		}
}

function printParentPrisc(id){
	openEmrPopup('printpriscEmr?selectedid='+id+'&halfprint=0');
}
function printinvstigationrecord(id,rpt){
	if(rpt=='0'){
		rpt = 'Numerical';
	}else{
		rpt = 'Writeup';
	}
	openEmrPopup('printInvestigation?selectedid='+id+'&rpt='+rpt+'&sectionid=none')
	
}

function viewpacsreport(invstid){
	openEmrPopup('pacsreportEmr?imgid='+invstid+'')
}

//save emr

function insertEmrPriscription(action){
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn")){
		if(document.getElementById("prescs_save_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_btn").disabled = true;
		}
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn")){
		if(document.getElementById("prescs_save_print_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_print_btn").disabled = true;
		}
	}	
	goaction = action;
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	var admission=0;
/*	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	*/
	var priscautoid ="1"; /*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
	 var mdtemplatename = document.getElementById("mdtemplatename").value;
	 var requestlocationid = document.getElementById("requestlocationid").value;
	 var repeatdate=0;
	if(document.getElementById("repeatdate")){
		repeatdate=document.getElementById("repeatdate").value;
	}	 
	 
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
	
	var priscdose=0;
	$('.jituradioclass').each(function() { 
		if(this.checked==true){
		    
			priscdose = this.value;
		} 					
	});
	//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscautoid="+priscautoid+"&prisctotal="+prisctotal+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+" ";
	
	/*if(priscdose==0 && action=='0'){
			jAlert('error', 'Please Select Dosage.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}*/
	
	if(document.getElementById("prisctable")){
		var chktdexist=document.getElementById("prisctable").innerHTML;
		if(chktdexist.includes("<td>")){
			
		}else{
			jAlert('error', 'Please add Priscprition.', 'Error Dialog');
			return;
		}
	}
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}if(errorflag){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', "Please enter qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		if(document.getElementById("prescs_save_btn")){
			document.getElementById("prescs_save_btn").disabled = false;
		}
		if(document.getElementById("prescs_save_print_btn")){
			document.getElementById("prescs_save_print_btn").disabled = false;
		}
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		
		var locations=sessionStorage.getItem("location");
		var isfromemrdashb=0;
		if(document.getElementById("isfromemrdashb")){
			isfromemrdashb = document.getElementById("isfromemrdashb").value;
		}
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&mdtemplatename="+mdtemplatename+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&repeatdate="+repeatdate+"&isfromemrdashb="+isfromemrdashb;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertEmrPriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
}

function insertEmrPriscriptionRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			if(document.getElementById("prescs_save_btn")){
				document.getElementById("prescs_save_btn").disabled = false;
			}
			if(document.getElementById("prescs_save_print_btn")){
				document.getElementById("prescs_save_print_btn").disabled = false;
			}
			
			if(goaction==1){
				
				$('#priscriptionpopup').modal( "hide" );
				if(document.getElementById("saveandprinthalf").value==1){
					openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
				}else{
					openEmrPopup('printpriscEmr?selectedid=0&halfprint=0');
				}
				
			}
			
			//document.getElementById("prisctable").innerHTML = req.responseText;
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			$('#priscriptionpopup').modal( "hide" );
			
			addpriscconsultationnote();
			
			$('#baselayout1loaderPopup').modal( "hide" );
			
		}
	}
}



function getpriscriptionDetails(){
	
	var url = "showpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getpriscriptionDetailsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getpriscriptionDetailsRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}
function backtoaddPriscription(){
	
	document.getElementById("priscbtndiv").innerHTML = ' <a href="#" onclick="insertPriscription()" type="submit" class="btn btn-primary btnset">ADD</a>';
	document.getElementById("backtoaddid").style.display = 'none';
}

function showedit(id){
	editpriscid = id;
	
	document.getElementById("priscbtndiv").innerHTML = ' <a href="#" onclick="updatePriscription()" type="submit" class="btn btn-primary btnset">Update</a>';
	document.getElementById("backtoaddid").style.display = '';
	
	var url = "editpriscEmr?selectedid="+id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showeditRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function showeditRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			
			var temp = data.split('~');
			
			document.getElementById("mdicinecategory").value = temp[0];
			$("#mdicinecategory").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
		
			setEditMedicineName(temp[0]);
			
		//	document.getElementById("mdicinename").value = temp[1];
		
			
			document.getElementById("priscdose").value = temp[2];
			document.getElementById("priscfreq").value = temp[3];
			document.getElementById("priscdays").value = temp[4];
			document.getElementById("priscdurationtype").value = temp[5];
		}
	}
	
}

function updatePriscription(){
	
	var categoryid = document.getElementById("mdicinecategory").value;
	var mdicinenameid = document.getElementById("mdicinename").value;
	var mdicinenametxt = $("#mdicinename option:selected").text();
	var priscdose = document.getElementById("priscdose").value;
	var priscfreq = document.getElementById("priscfreq").value;
	var priscdays = document.getElementById("priscdays").value;
	
	
	
	var url = "editsavepriscEmr?selectedid="+editpriscid+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatePriscriptionRequest;
    req.open("GET", url, true); 
    
    req.send(null);
              
}

function updatePriscriptionRequest(id){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
}


function deletepriscdata(id){
	
	var templatename = document.getElementById("templatename").value;
	
	var url = "delpriscEmr?selectedid="+id+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&templatename="+templatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletepriscdataRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function deletepriscdataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

function showTemplatePrisc(id){
	var url = "tenplatepriscEmr?selectedid="+id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showTemplatePriscRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function showTemplatePriscRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
			
			var data = document.getElementById("parenteditdataid").value;
			var temp = data.split('~');
			
			var prepaid = temp[0];
			var postpaid = temp[1];
			var otherpay = temp[2];
			var priscdosenotes = temp[3];
			var followupsqty = temp[4];
			var followupstype = temp[5];
			var english = temp[6];
			var regional =  temp[7];
			var hindi =  temp[8];
			var priscadvoice = temp[9];
			
			document.getElementById("priscdosenotes").value = priscdosenotes;
			
			/*if(prepaid==1){
				document.getElementById('paymode1').checked = true;
			}
			if(postpaid==1){
				document.getElementById('paymode2').checked = true;
			}
			if(otherpay==1){
				document.getElementById('paymode3').checked = true;
			}*/
			
			if(english==1){
				document.getElementById('language1').checked = true;
			}
			if(regional==1){
				document.getElementById('language2').checked = true;
			}
			if(hindi==1){
				document.getElementById('language3').checked = true;
			}
			
			document.getElementById('followupsqty').value = followupsqty;
			document.getElementById('followupstype').value = followupstype;
			document.getElementById('priscadvoice').value = priscadvoice;
			
			//getPriscClientInfo(patientId);
			
			$('#priscriptionpopup').modal( "show" );
		}
	}
}




function editdischargeprisc(id,clientid,ipdid,pid,condid){
	
	editparentpriscid = id;
	patientId = clientid;
	diaryuserId = pid;
	editcondition_id = condid;
	
	
	var url="editdischargemedicineEmr?selectedid="+id+"&ipdid="+ipdid+"&clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = editparentpriscRequest;
    req.open("GET", url, true); 
    
    req.send(null);
	
}


function editparentprisc(id){

	editparentpriscid = id;
	
	var url = "editparentpriscEmr?selectedid="+id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = editparentpriscRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function editparentpriscRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
			
			var data = document.getElementById("parenteditdataid").value;
			var temp = data.split('~');
			
			var prepaid = temp[0];
			var postpaid = temp[1];
			var otherpay = temp[2];
			var priscdosenotes = temp[3];
			var followupsqty = temp[4];
			var followupstype = temp[5];
			var english = temp[6];
			var regional =  temp[7];
			var hindi =  temp[8];
			var priscadvoice = temp[9];
			
			document.getElementById("priscdosenotes").value = priscdosenotes;
			
		/*	if(prepaid==1){
				document.getElementById('paymode1').checked = true;
			}
			if(postpaid==1){
				document.getElementById('paymode2').checked = true;
			}
			if(otherpay==1){
				document.getElementById('paymode3').checked = true;
			}*/
			
			if(english==1){
				document.getElementById('language1').checked = true;
			}
			if(regional==1){
				document.getElementById('language2').checked = true;
			}
			if(hindi==1){
				document.getElementById('language3').checked = true;
			}
			
			document.getElementById('followupsqty').value = followupsqty;
			document.getElementById('followupstype').value = followupstype;
			document.getElementById('priscadvoice').value = priscadvoice;
			
			//getPriscClientInfoWithSession(patientId);
			getPriscClientInfoForEdit(patientId);
			$('#priscriptionpopup').modal( "show" );
		}
	}
}


function delparentprisc(id){
	
var url = "deleteparentpriscEmr?selectedid="+id+"&clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = delparentpriscReqiest;
    req.open("GET", url, true); 
    
    req.send(null);
}


function delparentpriscReqiest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('alldataprisctable').innerHTML = req.responseText;
			document.getElementById('editalldataprisctable').innerHTML = req.responseText;
		}
	}
}


//view priscription code
function insertViewEmrPriscription(){
		/*if(document.getElementById("priscdose").value==0){
			jAlert('error', 'Please Select Dosage.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		
		
	}*/
	insertPriscription(0)
	//jAlert('Success', 'Record saved successfully.', 'Success Dialog');
	window.location.reload();
}

function addDischargePrisc(){
var templatename = document.getElementById("templatename").value;
if(templatename==0){
	
/*	var priscdose=0;
	$('.jituradioclass').each(function() { 
		if(this.checked==true){
		    
			priscdose = this.value;
		} 					
	});
	
	if(priscdose==0){
			jAlert('error', 'Please Select Dosage.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{

	
	
	}
*/
	
	insertDischargePriscription(0);
	$('#priscriptionpopup').modal( "hide" );
	
}else{
	saveparentprisctemplatedata(1);
	
}
}

function addEmrPrisc(){

var templatename = document.getElementById("templatename").value;
var priscdose=0;
$('.jituradioclass').each(function() { 
	if(this.checked==true){
	    
		priscdose = this.value;
	} 					
});
var errorflag=false;
var oneerrorflag = true;
$('.akashclasss').each(function() {
	oneerrorflag = false;
	var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
	if(drqtyin=='' && errorflag==false){
		errorflag = true;
	}else if(Number(drqtyin)<=0 && errorflag==false){
		errorflag = true;
	}
});
var requestlocationid = document.getElementById("requestlocationid").value;
	if(oneerrorflag){
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		jAlert('error', "Please enter valid qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		if(templatename==0){

			if(document.getElementById("outoprisc").value==1){
				saveEmrparentprisctemplatedata(0);
			}else{
				insertEmrPriscription(2);
			}
			
			$('#priscriptionpopup').modal( "hide" );
			

		}else{
			saveEmrparentprisctemplatedata(0);
			
			
		}
	}

		

	
}

function addViewPrisc(){
var templatename = document.getElementById("templatename").value;
if(templatename==0){
	/*if(document.getElementById("priscdose").value==0){
			jAlert('error', 'Please Select Dosage.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{

	
	
	}*/
	insertViewEmrPriscription(0)
	$('#priscriptionpopup').modal( "hide" );
}else{
	saveparentprisctemplatedata(0);
	
}
}

function addIpdPrisc(){
var templatename = document.getElementById("templatename").value;
var priscdose=0;
$('.jituradioclass').each(function() { 
	if(this.checked==true){
	    
		priscdose = this.value;
	} 					
});
var requestlocationid = document.getElementById("requestlocationid").value;
var errorflag=false;
var oneerrorflag = true;
$('.akashclasss').each(function() { 
	oneerrorflag = false;
	var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
	if(drqtyin=='' && errorflag==false){
		errorflag = true;
	}else if(Number(drqtyin)<=0 && errorflag==false){
		errorflag = true;
	}
});
if(oneerrorflag){
	jAlert('error', "Please add at least one product!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
}else if(errorflag){
	jAlert('error', "Please enter valid qty!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
}else if(requestlocationid==0){
	jAlert('error', 'Please Select Location', 'Error Dialog');
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
}else{
	var outoprisc = document.getElementById("outoprisc").value;
	if(document.getElementById("prisctable")){
		var chktdexist=document.getElementById("prisctable").innerHTML;
		if(chktdexist.includes("<td>")){
			
		}else{
			jAlert('error', 'Please add Priscprition.', 'Error Dialog');
			return;
		}
	}
	if(templatename==0){
		if(outoprisc==1){
			saveparentprisctemplatedata(0);
		}else{
			insertPriscription(0);
		}
		jAlert('success', 'Prescription saved successfully.', 'success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		$('#priscriptionpopup').modal( "hide" );

	}else{
		saveparentprisctemplatedata(0);
	jAlert('success', 'Prescription saved successfully.', 'success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		$('#priscriptionpopup').modal( "hide" );
	}
}

	
}

//set repeatdate dropdown
function repeatePriscDateAjax(cid,pid,conid){
	if(document.getElementById("prisc_location_list")){
		var prisc_location_list = document.getElementById("prisc_location_list").value;
		if(prisc_location_list==1){
			var bydefaultlocationid = document.getElementById("bydefaultlocationid").value;
			document.getElementById("requestlocationid").value = bydefaultlocationid;
		}else{
			/*document.getElementById("requestlocationid").value = 0;*/
		}
	}
	
	if(document.getElementById("templatename")){
		document.getElementById("templatename").className ="form-control";
		document.getElementById("templatename").value = "0";
		document.getElementById("templatename").className ="form-control chosen-select";
		$("#templatename").trigger("chosen:updated");
		$(".chosen-select").chosen({allow_single_deselect:true});
	}
	var url = "rpeatPrescription?clientId="+cid+"&prectionerid="+pid+"&conditionid="+conid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = repeatePriscDateAjaxRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function repeatePriscDateAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('rpeatdivajax').innerHTML = req.responseText;
			//if its from ipd edi discharge
			if(document.getElementById('admi_disc_ipd_prisclist')){
				if(document.getElementById('foripddashboardpriscription')){
					showpriscription();
				}else{
					getmedicinelistJSON();
				}
			}else{
				showpriscription();
			}
			
		}
	}

}


function repeateEditPriscDateAjax(cid,pid,conid){
	var url = "rpeatPrescription?clientId="+cid+"&prectionerid="+pid+"&conditionid="+conid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = repeateEditPriscDateAjaxRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function repeateEditPriscDateAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('rpeatdivajax').innerHTML = req.responseText;
			
			editparentprisc(editparentpriscid);
			
			
		}
	}

}

function saveEmrparentprisctemplatedata(discharge){
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn")){
		if(document.getElementById("prescs_save_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_btn").disabled = true;
		}
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn")){
		if(document.getElementById("prescs_save_print_btn").disabled==false){
			btndisableflag = true;
			document.getElementById("prescs_save_print_btn").disabled = true;
		}
	}
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	//var discharge = 0;
	/*if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	*/
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
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
	var locations=sessionStorage.getItem("location");
	var requestlocationid = document.getElementById("requestlocationid").value;
	var isfromemrdashb=0;
	if(document.getElementById("isfromemrdashb")){
		isfromemrdashb = document.getElementById("isfromemrdashb").value;
	}
	if(btndisableflag==false){
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url = "saveparentpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+"&requestlocationid="+requestlocationid+"&isfromemrdashb="+isfromemrdashb+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = saveEmrparentprisctemplatedataRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
	
}
function saveEmrparentprisctemplatedataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var listsizeid = document.getElementById("listsizeid").value;
			var data= req.responseText;
				//alert(listsizeid);
			parentsuperlistsizeid = listsizeid;
			if(document.getElementById("outoprisc").value==1){
				var tstr = "0";
				 $('.nimaicase').each(function() { //loop through each checkbox
	                   
					 var t = this.id;
					 tstr = tstr + "," + t;
					
					 
					 
	                });
				
				 var mt = tstr.split(',');
				 parentsuperlistsizeid = mt.length;
				 for(m=0;m<mt.length;m++){
					 if(m!=0){
						 saveEmrTemplatePriscData(mt[m]); 
					 }
				}
					 
				 }else{
					
					if(data=='raiprachi')
						{
							saveEmrTemplatePriscData(0)
							
						}else{
					 for(i=0;i<listsizeid;i++){
							saveEmrTemplatePriscData(i)
						}
					}
				 }

				
				//saveChildParentPriscTempData();
				
				//$('#priscriptionpopup').modal( "hide" );
			if(document.getElementById("prescs_save_btn")){
				document.getElementById("prescs_save_btn").disabled = false;
			}
			if(document.getElementById("prescs_save_print_btn")){
				document.getElementById("prescs_save_print_btn").disabled = false;
			}	
		}
	}
}

function saveEmrTemplatePriscData(i){
	
	if(document.getElementById("outoprisc").value==1){
		setTimeout(function() {
			$("#popup_container").remove();
			//saveTemplatePriscData(t);
		}, 500);
	}

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

	var mdcinenametxt = document.getElementById("mdcinenametxt"+i).innerHTML;
	var priscfreq = document.getElementById("priscfreq"+i).innerHTML;
	var dosage = document.getElementById("dosage"+i).value;
	var days = document.getElementById("priscday"+i).value;
	var dosenotes = document.getElementById("dosenotes"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		dosenotes = document.getElementById("dosenotes"+i).value;
	}
	var mdicinenameid = document.getElementById("mdicinenameid"+i).innerHTML;
	var priscqty = document.getElementById("drgivenqty"+i).value;;
	var dddose = document.getElementById("dddose"+i).value;;
	

	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	/*if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}*/
	
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
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
	
	var prisctimename = document.getElementById("prisctimename"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		prisctimename = document.getElementById("prisctimename"+i).value;
		
	}
	var srno = parseInt(i) +1;
	var unitextension = document.getElementById("uom"+i).value;
	var priscindivisualremark = document.getElementById("priscindivisualremark"+i).innerHTML;
	if(document.getElementById("outoprisc").value==1){
		priscindivisualremark = document.getElementById("priscindivisualremark"+i).value;
		
	}
	
	var url = "savetemplatepriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&mdcinenametxt="+mdcinenametxt+"&priscfreq="+priscfreq+"&dosage="+dosage+"&days="+days+"&dosenotes="+dosenotes+"&mdicinenameid="+mdicinenameid+"&priscqty="+priscqty+"&dddose="+dddose+"&prisctimename="+prisctimename+"&priscindivisualremark="+priscindivisualremark+"&unitextension="+unitextension+"&srno="+srno+"&parentsuperlistsizeid="+parentsuperlistsizeid+"&current_size_data="+i+" ";
	
	
	//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&istemplate="+istemplate+"&mdtemplatename="+mdtemplatename+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveEmrTemplatePriscDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    
	
}

function saveEmrTemplatePriscDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById("prisctable").innerHTML = req.responseText;
			
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			addpriscconsultationnote();
			
			$('#baselayout1loaderPopup').modal( "hide" );
			
			$('#priscriptionpopup').modal( "hide" );
			
			
		}
	}
}


function refreshData(){

       document.getElementById("priscform").submit();
}


function getDoseNote(id) {
	
	var url="getdosenotePrescriptiondetails?id="+id+"&patientId="+patientId+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getDoseNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	 
}
function getDoseNoteRequest(){
	 if (req.readyState == 4) {
	  if (req.status == 200) {
	   
	       var str= req.responseText;
	       str1=str.split("~");
	       if(str1[1]=="null"){
	        str1[1]="";
	       }
	       
	       document.getElementById("priscdays").value= str1[1];
	       document.getElementById("unit").value= str1[3];
	       var stttt = str1[3].split(".");
	       document.getElementById("unit").value= stttt[0];
	        document.getElementById("prisccodediv").innerHTML= str1[4];
	        document.getElementById("priscdosenotesdiv").innerHTML= str1[5];
	        
	       document.getElementById("prisctimediv").innerHTML= str1[6];
	        var dosetime1 = str1[8];
	        var dosetime2 = str1[9];
	        var dosetime3 = str1[10];
	        document.getElementById("priscqty").value=str1[11];
	        if(str1[0]=='1-0-1' || str1[0]=='2-0-2'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" value="'+dosetime1+'" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0" id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" value="'+dosetime3+'" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='1-1-1' || str1[0]=='2-2-2'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="'+dosetime1+'" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value="'+dosetime2+'"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="'+dosetime3+'" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='1-0-0' || str1[0]=='2-0-0'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="'+dosetime1+'"  id="dosegiventime1"  class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='1-1-0' || str1[0]=='2-2-0'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="'+dosetime1+'"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value="'+dosetime2+'"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='0-1-0' || str1[0]=='0-2-0'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value="'+dosetime2+'" id="dosegiventime2"  class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='0-1-1' || str1[0]=='0-2-2'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value="'+dosetime2+'"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="'+dosetime3+'" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else if(str1[0]=='0-0-1' || str1[0]=='0-0-2'){
	    		document.getElementById('manualdosetimediv').style.display='';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="'+dosetime3+'" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	    	}else{
	    		document.getElementById('manualdosetimediv').style.display='none';
	    		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
	    		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value=""  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
	    		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';

	    	}
	        
	        //  31 July 2018
	        
	        var frequency = str1[12];
	        var iswbd =  str1[13];
	        var caldose = str1[14];
	        var strength = str1[15];
	        var ddays = str1[16];
	        var rnotes = str1[17];
	        var qqty = str1[18];
	        var dddose = str1[19];
	        var dosefreq = str1[20];
	        var uom = str1[21];
	        var rmark = str1[22];
	        var dtxt = str1[23];
	        
	        
	        
	        var num = 0;
	        num = parseFloat(dddose);
	        num = num.toString(); //If it's not already a String
	        num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	        Number(num); //If you need it back as a Number
	        
	        document.getElementById("unitextension").value= uom;
	        
	        //  09-07-2020 Data not selected becauseof it. It already came selected from java
	        //document.getElementById("priscdosenotes").value= dtxt;
	        //document.getElementById("prisccode").value= dosefreq;
	        if(rmark='null'){
	        	rmark='';
	        }
	        document.getElementById("priscindivisualremark").value= rmark;
	        document.getElementById("priscdays").value= ddays;
	        if(document.getElementById("outoprisc").value=="1"){
	        	document.getElementById("priscqty").value= qqty;
	        }
	        document.getElementById("dddose").value= num;
	        
	        if(iswbd=="1"){
	        	var sttttxx = strength.split(".");
	        	document.getElementById("unit").value= sttttxx[0];
	        }
	        
	        document.getElementById("masterfrequency").value= frequency;
	        document.getElementById("masteriswbd").value= iswbd;
	        document.getElementById("mastercaldose").value= caldose;
	        document.getElementById("masterstrength").value= strength;
	      
	        $("#priscindivisualremark").trigger("chosen:updated");
		       $(".chosen").chosen({allow_single_deselect: true});
	        
	        $("#prisccodediv").trigger("chosen:updated");
	       $(".chosen").chosen({allow_single_deselect: true});
	       
	        $("#priscdosenotes").trigger("chosen:updated");
	       $(".chosen").chosen({allow_single_deselect: true});
	     
	       setPriscWeightforCal(patientId);
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





function savenewmdicine(){
	
	var medicinename= document.getElementById("newmedicine").value;
	var genericname= document.getElementById("newgeneric").value;
	
	if(medicinename=='' || medicinename==' '){

		jAlert('error', 'Please enter product name!', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else {
		
		var url="newmedicineEmr?medicinename="+medicinename+"&genericname="+genericname+"";
		  
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = savenewmdicineRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);	
		
	}
	 
}

function savenewmdicineRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			jAlert('success', 'New product added!', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		  	 
			document.getElementById('hiddendiv').style.display='none';
			document.getElementById('mdicinediv').innerHTML=req.responseText;
			$("#mdicinename").trigger("chosen:updated");
		  	$(".chosen").chosen({allow_single_deselect: true});
			
		}
	}

}

var ghdnmdnameid = 0;
var gemdindex = 0;
function showprisceditmdcinenameindb(index,mid){
	ghdnmdnameid = mid;
	gemdindex = index;
	var mname = document.getElementById('mdcinenametxt'+index).innerHTML;
	document.getElementById('priscmdcineedit').value = mname;
	$('#edtmdcinenamepopupid').modal( "show" );
}

function getsrchdmdicinename(mid){
	
	var mname =  $("#mdicinenamesrch option:selected").text();
	document.getElementById('priscmdcineedit').value = mname;
}

function updatemdcinename(){
	if(document.getElementById("outoprisc").value==1){
		var medicinename = document.getElementById('priscmdcineedit').value ;
		document.getElementById('mdcinenametxt'+gemdindex).innerHTML = medicinename;
		
	}else{
	
	var templatename = document.getElementById('templatename').value ;
	var medicinename = document.getElementById('priscmdcineedit').value ;
	var url="updatemdcineEmr?medicinename="+medicinename+"&ghdnmdnameid="+ghdnmdnameid+"&gemdindex="+gemdindex+"&templatename="+templatename+" ";
	  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatemdcinenameRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
    
	}
	
}


function updatemdcinenameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
			
			showMedicineNameAjax();
			
		}
	}
	
}


function showMedicineNameAjax(){
	var url="showmdEmr";
	  
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showMedicineNameAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function showMedicineNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('mdicinediv').innerHTML=req.responseText;
			$("#mdicinename").trigger("chosen:updated");
		  	$(".chosen").chosen({allow_single_deselect: true});
			
			
		}
	}
	
}

function setPriscTemplate(id) {
	
	$('#baselayout1loaderPopup').modal( "show" );
	var url="settemplatepriscIpd?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPriscTemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
	
}
function setPriscTemplateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById("prisctemplatediv").innerHTML= req.responseText;
			 $("#templatename").trigger("chosen:updated");
			 $(".chosen").chosen({allow_single_deselect: true});
			 $('#baselayout1loaderPopup').modal( "hide" );
			 $('#selecttemplate').modal( "hide" );
			
		}
	}
	
}

function changepriscgivensrno(index,srno) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivensrnoEmr?index="+index+"&srno="+srno+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivensrnoRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivensrnoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

function changepriscgivendose(index,val) {
	var duration=document.getElementById("priscday"+index).value;
	var drgivenqty=document.getElementById("drgivenqty"+index).value;
	var prisctimename=document.getElementById("dosage"+index).value;
	var dosenotes=document.getElementById("dosenotes"+index).value;
	var priscindivisualremark=document.getElementById("priscindivisualremark"+index);
	if(val=='1-0-1' || val=='2-0-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0" id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-1-1' || val=='2-2-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-0-0' || val=='2-0-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1"  class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-1-0' || val=='2-2-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-1-0' || val=='0-2-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2"  class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-1-1'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-0-1' || val=='0-0-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else{
		document.getElementById('manualdosetimediv').style.display='none';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value=""  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}
	if(document.getElementById('isfromoutoincriprisc')){
		var isfromoutoincriprisc = document.getElementById('isfromoutoincriprisc').value;
		if(isfromoutoincriprisc=='0'){
			changepriscgivendays(index,val);
			
		}
	}
	priscmodifyjs(index,val,duration,drgivenqty,prisctimename,dosenotes,priscindivisualremark);
	/*var templatename = document.getElementById("templatename").value;
	var url="changepriscgivendoseEmr?index="+index+"&srno="+srno+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivendoseRequest;
    req.open("GET", url, true); 
              
    req.send(null);	*/
	
}

function changepriscgivendoseRequest(){
	/*if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	*/
}

function changepriscgivendays(index,srno) {
	var prisccode = document.getElementById("dosage"+index).value;
	var priscdays = document.getElementById("priscday"+index).value;
	var drgivenqty= document.getElementById("drgivenqty"+index).value;
	//var prisctimename=document.getElementById("prisctimename"+index).value;
	var prisctimename=document.getElementById("dosage"+index).value;
	var dosenotes=document.getElementById("dosenotes"+index).value;
	var priscindivisualremark=document.getElementById("priscindivisualremark"+index);
	
	if(prisccode=='0'){
		document.getElementById("drgivenqty"+index).value='0'
	}else if(priscdays=='' || priscdays=='0'){
		document.getElementById("drgivenqty"+index).value='0'
	}else{
		var str =  prisccode.split('-');
		var priscqtys=0;
		for(var i = 0; i < str.length; i++) {
			
			/*var hasNumber = /\d/;
				var res = hasNumber.test(str[i]); // false
				//hasNumber.test("Easy as 123"); // true
			*/	
			var test = parseInt(str[i]);
			
			if(test=='NaN'){
				continue;
			}
			if(str[i]=='0'){
				continue;
			}
			if(str[i]=='1/2'){
				continue;
			}
			priscqtys = priscqtys+(parseInt(str[i])*parseInt(priscdays));
		}
		document.getElementById("drgivenqty"+index).value=priscqtys;
	}
	priscmodifyjs(index,prisccode,priscdays,priscqtys,prisctimename,dosenotes,priscindivisualremark);
	/*var templatename = document.getElementById("templatename").value;
	var url="changepriscgivendaysEmr?index="+index+"&srno="+srno+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivendaysRequest;
    req.open("GET", url, true); 
              
    req.send(null);	*/
	
}

function changepriscgivendaysRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}
var uptemplate='0';
var uptemplateid='';
function updateTemplate(){
	istemplate = 1;
	uptemplate =1;
	var templatename = document.getElementById("templatename").value;
	if(templatename=='0'){
		jAlert('error', 'Please select template!!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		uptemplateid=templatename;
		insertPriscription();
	}
	
}

function setdosegiventiming(val) {
	if(val=='1-0-1' || val=='2-0-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0" id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-1-1' || val=='2-2-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-0-0' || val=='2-0-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1"  class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='1-1-0' || val=='2-2-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-1-0' || val=='0-2-0'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2"  class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-1-1'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0"  id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else if(val=='0-0-1' || val=='0-0-2'){
		document.getElementById('manualdosetimediv').style.display='';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" readonly="readonly" value="0" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" readonly="readonly" value="0"  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}else{
		document.getElementById('manualdosetimediv').style.display='none';
		document.getElementById('dosetimimg1').innerHTML ='<input type="text" value="" id="dosegiventime1" class="form-control" placeholder="Dose Time" />';
		document.getElementById('dosetimimg2').innerHTML ='-<input type="text" value=""  id="dosegiventime2" class="form-control" placeholder="Dose Time" />-';
		document.getElementById('dosetimimg3').innerHTML ='<input type="text" value="" id="dosegiventime3" class="form-control" placeholder="Dose Time" />';
	}
	if(document.getElementById('isfromoutoincriprisc')){
		var isfromoutoincriprisc = document.getElementById('isfromoutoincriprisc').value;
		if(isfromoutoincriprisc=='0'){
			calculatePriscQty();
		}
	}
	
	
}

function savenewmdicineJSON(val){
	
	var medicinename= document.getElementById("newmedicine").value;
	var genericname= document.getElementById("newgeneric").value;
	var newcomponent = document.getElementById("newcomponent").value;
	if(medicinename=='' || medicinename==' '){
		jAlert('error', 'Please enter product name!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else {
		
		var url="newmedicineEmr?medicinename="+medicinename+"&genericname="+genericname+"&istemp="+val+"";
		  
		 var dataObj={
				    
				    "medicinename" : "" + medicinename + "",
				    "genericname" : "" + genericname + "",
				    "istemp" : "" + val + "",
				    "newcomponent" : "" + newcomponent + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "newmedicineJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   
				   success : function(data) {
								
					   
					   jAlert('success', 'New Product Added!', 'success Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					  	 
						document.getElementById('hiddendiv').style.display='none';
						document.getElementById('mdicinediv').innerHTML=data.responsej;
						$("#mdicinename").trigger("chosen:updated");
					  	$(".chosen").chosen({allow_single_deselect: true});
						
					  	//  09-07-2020 remove data of field after save
					  	document.getElementById('newmedicine').value="";
					  	document.getElementById('newgeneric').value="";
					  	document.getElementById('newcomponent').value="";
					   
					    },
				   error : function(result) {
				    console.log("error in saving product");
				   }
				  });
				   
		
		  
	}

}

function openPriscStatusPopup(parentid,count){
		 var dataObj={
				    "parentid" : "" + parentid + "",
				  };
		var data1 =  JSON.stringify(dataObj);
			$.ajax({
				   url : "priscnurseJsonBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					   var test=""; 
					   var k =0;
					   document.getElementById("priscstatustbody").innerHTML=data.responsej;
					   $('.akashcase').each(function(){
						   if(k==0){
							   test=document.getElementById("mdrequestqty"+this.value).value +"-"+this.value;
						   }else{
							   test=test+","+document.getElementById("mdrequestqty"+this.value).value +"-"+this.value;
						   }
						   k=k+1;
						  
					   });
					    var testarray = test.split(',');
						for(var i = 0; i < testarray.length; i++) {
							var idsarray = testarray[i].split('-');
							document.getElementById("mdrequestqty"+idsarray[1]).value = idsarray[0];
						}
						
						/*var default_phar_location = document.getElementById("default_phar_location").value;*/
						var default_phar_location = data.locationid_data;
						document.getElementById("default_phar_location_new").className ="";
						document.getElementById("default_phar_location_new").value =default_phar_location;
						document.getElementById("default_phar_location_new").className ="form-control chosen";
						$("#default_phar_location_new").trigger("chosen:updated");
						document.getElementById("default_phar_location").value= default_phar_location;
						if(count>0){
							document.getElementById("displaywarningdivs").style.display="block";
						}else{
							document.getElementById("displaywarningdivs").style.display="none";
						}
						document.getElementById("rqeuestcouts").value=count;
						$(".chosen").chosen({allow_single_deselect: true});
						$('#priscstatuspopup').modal( "show" );
					},
				   	error : function(result) {
				   		jAlert('error', "Something wrong!!!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   }
				  });
}

function addnewpriscbynurse() {
	var newmedicineid = document.getElementById("priscstatusmedid").value; 
	var parentid = document.getElementById("priscstatusparentid").value; 
	var medid = document.getElementById("medid").value; 
	var flag = false;
	var medid_array = medid.split(',');
	for(var i = 0; i < medid_array.length; i++) {
	  if(medid_array[i]==newmedicineid){
		  flag = true;
	  }
	}
	
	if(newmedicineid=='0'){
		jAlert('error', "Please select product", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(flag){
		jAlert('error', "Product already added!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	
		var dataObj={
			"parentid" : "" + parentid + "",
			"newmedicineid" : "" +newmedicineid+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
			url : "addnewpriscbynurseBookAppointmentAjax",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			success : function(data) {
				jAlert('success', "Product added successfully!!!", 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				var count = document.getElementById("rqeuestcouts").value;
				openPriscStatusPopup(parentid,count);
			},
				error : function(result) {
					jAlert('error', "Something wrong!!!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}
		});
	}
}


function savepriscbynurse(val) {
	var ids="0"; 
	$('.akashcase').each(function(){
		if(this.checked == true){
		    ids=ids+","+this.value;
		} 
	});
	
	var flag = false;
	var medid_array = ids.split(',');
	for(var i = 0; i < medid_array.length; i++) {
		if(medid_array[i]=='0'){
			continue;
		}
		var qty = document.getElementById("mdrequestqty"+medid_array[i]).value;
		if(qty=='' || qty=='0'){
			flag = true;
			break;
		}
	}
	var default_phar_location_new = document.getElementById("default_phar_location_new").value;
	if(ids=='0'){
		jAlert('error', "Please select at least one checkbox!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(flag){
		jAlert('error', "Please enter quantity!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(default_phar_location_new=='0'){
		jAlert('error', "Please select request pharmacy location!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		if(confirm("Are you sure?")){
			$("#dashboardloaderPopup").modal("show");
			document.getElementById("hiddenval").value=val;
			document.getElementById("priscreqids").value=ids;
			document.getElementById("newmedreqform").submit();
		}
	}
}

function openPriscRequestCountPopup(id) {
	  var url="openmultiplerequestnewPrescription?id="+id+"";   	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
 	req.onreadystatechange = openPriscRequestCountPopupRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function openPriscRequestCountPopupRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		var data=req.responseText;
		document.getElementById("priscrequestnewbody").innerHTML=data;
		$('#priscrequestnewpopup').modal("show");
	}
}
}

function calculatePriscQty() {
	var prisccode = document.getElementById("prisccode").value;
	var priscdays = document.getElementById("priscdays").value;
	if(prisccode=='0'){
		document.getElementById("priscqty").value='0'
	}else if(priscdays=='' || priscdays=='0'){
		document.getElementById("priscqty").value='0'
	}else{
		var str =  prisccode.split('-');
		var priscqtys=0;
		for(var i = 0; i < str.length; i++) {
			
			/*var hasNumber = /\d/;
				var res = hasNumber.test(str[i]); // false
				//hasNumber.test("Easy as 123"); // true
			*/	
			var test = parseInt(str[i]);
			
			if(test=='NaN'){
				continue;
			}
			if(str[i]=='0'){
				continue;
			}
			if(str[i]=='1/2'){
				continue;
			}
			priscqtys = priscqtys+(parseInt(str[i])*parseInt(priscdays));
		}
		document.getElementById("priscqty").value=priscqtys;
	}
}


function insertAdmissionPriscription(action){
	var btndisableflag= false;
	if(document.getElementById("editipdformsavepricbtn").disabled==false){
		btndisableflag = true;
		document.getElementById("editipdformsavepricbtn").disabled = true;
	}
	if(document.getElementById("editipdformsaveprintpricbtn").disabled==false){
		btndisableflag = true;
		document.getElementById("editipdformsaveprintpricbtn").disabled = true;
	}
	
	$("#dashboardloaderPopup").modal('show');
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
	var admission =1;
	//var discharge = 1;
/*	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	*/
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
	var repeatdate=document.getElementById("repeatdate").value;
	 var mdtemplatename = document.getElementById("mdtemplatename").value;
	 var requestlocationid = document.getElementById("requestlocationid").value;
	  var bamsbckdate = document.getElementById("backDate").value;
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
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		document.getElementById("editipdformsavepricbtn").disabled = false;
		document.getElementById("editipdformsaveprintpricbtn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		document.getElementById("editipdformsavepricbtn").disabled = false;
		document.getElementById("editipdformsaveprintpricbtn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		document.getElementById("editipdformsavepricbtn").disabled = false;
		document.getElementById("editipdformsaveprintpricbtn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		if(action==1){
			openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
		}
		//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscautoid="+priscautoid+"&prisctotal="+prisctotal+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+" ";
		
		var priscdose=0;
		$('.jituradioclass').each(function() { 
			if(this.checked==true){
			    
				priscdose = this.value;
			} 					
		});
		
		
		/*if(priscdose==0){
				jAlert('error', 'Please Select Dosage.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}*/
		var locations="ipd";
		var isfromemrdashb=0;
		if(document.getElementById("isfromemrdashb")){
			isfromemrdashb = document.getElementById("isfromemrdashb").value;
		}
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&mdtemplatename="+mdtemplatename+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&bamsbckdate="+bamsbckdate+"&isfromemrdashb="+isfromemrdashb;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertAdmissionPriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
	
}

function insertAdmissionPriscriptionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("editipdformsavepricbtn").disabled = false;
			document.getElementById("editipdformsaveprintpricbtn").disabled = false;
			$("#dashboardloaderPopup").modal('hide');
			priscadc=req.responseText;
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//showDischargePopup();
			admissionPriscData();
			$('#priscriptionpopup').modal( "hide" );
			
		}
	}
}

function admissionPriscData(){
	var url = "admissinpriscdataBookAppointmentAjax";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = admissionPriscDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function admissionPriscDataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dischargedataid").innerHTML = req.responseText;
				document.getElementById("priscnotes").innerHTML=priscadc;
				
	         }
		}
}

function selectPriscCheckBox(val){
	
	if (val== true) {
		$('.akashcase').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.akashcase').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function changepriscgivenqty(index,qty) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivenqtyEmr?index="+index+"&qty="+qty+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenqtyRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenqtyRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}


function changepriscgivenremark(index,reamrk) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivenremarkEmr?index="+index+"&reamrk="+reamrk+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenremarkRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenremarkRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}


function changepriscgivenunit(index,unit) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivenunitEmr?index="+index+"&unit="+unit+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenunitRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenunitRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

function changepriscgivenunitextenstion(index,unitextenstion) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivenunitextenstionEmr?index="+index+"&unitextenstion="+unitextenstion+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenunitextenstionRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenunitextenstionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

//setPriscWeightforCal
function setPriscWeightforCal(clientid) {
	var url="getclientweightBookAppointmentAjax?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPriscWeightforCalRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function setPriscWeightforCalRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			document.getElementById("masterweight").value=data;
			//document.getElementById("weightspan").innerHTML=data;
			if(data!=0){
				calculatedose();
			}else{
				if(document.getElementById("consulationForm")){
					addTempPriscription();
				}
			}
		}
	}
	
}


function calculatedose() {
	var weight = parseFloat(document.getElementById("masterweight").value);
	var frequency = parseFloat(document.getElementById("masterfrequency").value);
	var iswbd = document.getElementById("masteriswbd").value;
	var caldose = parseFloat(document.getElementById("mastercaldose").value);
	var strength = parseFloat(document.getElementById("masterstrength").value);
	var total=0;
	if(iswbd=="1"){
		total =caldose*weight*frequency;
		var dose = total/strength;
		var dose1 = dose.toFixed(2);
		
		var data = dose1.toString().split(".");
		if(data[1]>75){
			data[1]=75;
		}else if(data[1]>50){
			data[1]=75;
		} if(data[1]>25){
			data[1]=50;
		}else if(data[1]>5){
			data[1]=25;
		}
		var lastdata = data[0]+"."+data[1];
		document.getElementById("masterdose").value=lastdata;
		document.getElementById("dosespan").innerHTML=lastdata;
	}else{
		document.getElementById("masterdose").value=0;
		document.getElementById("dosespan").innerHTML=0;
	}
	
	if(document.getElementById("consulationForm")){
		addTempPriscription();
	}
}


function addremarktomaster(){
	
	var eng=document.getElementById("remarkenglish").value;
	var hindi= document.getElementById("remarkhindi").value;
	var marathi= document.getElementById("remarkmarathi").value;
	if(eng==''&&hindi==''&&marathi==''){
		jAlert('error', 'Fiil Atleast One!', 'error Dialog');
	}	
	hindi= encodeURI(hindi);
	marathi= encodeURI(marathi);
		 var dataObj={
				    
				    "eng" : "" + eng + "",
				    "hindi" : "" + hindi + "",
				    "marathi" : "" + marathi + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "addremarkmasterbyjsonMaster",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   
				   success : function(data) {
								
					   
					   jAlert('success', 'New Remark Added!', 'success Dialog');
						
						
					  	 
					   $('#addremarkmaster').modal( "hide" );
						document.getElementById('remlang').innerHTML=data.responsej;
						
					   
					    },
				   error : function(result) {
				    console.log("error in saving product");
				   }
				  });
}



function refreshremarks(){
	
	
		 var dataObj={
				    
				    "test" : "test",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "refreshedremarksMaster",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   
				   success : function(data) {
					
						document.getElementById('remlang').innerHTML=data.responsej;
						
					   
					    },
				   error : function(result) {
				    console.log("error in saving product");
				   }
				  });
}

function getmedicinelistJSON(){
		var dataObj={
				 "istemp" : "" + 1 + "",
		};
		  var data1 =  JSON.stringify(dataObj);
		  $.ajax({

		   url : "getpriscmedicinelistBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   
		   success : function(data) {
				document.getElementById('hiddendiv').style.display='none';
				var data1=data.responsej;
				
				document.getElementById('mdicinediv').innerHTML=data1;
				$("#mdicinename").trigger("chosen:updated");
			  	$(".chosen").chosen({allow_single_deselect: true});
				
			/*	document.getElementById('diagnosdiv').innerHTML=temp[1];
								$("#diagnosisname").trigger("chosen:updated");
							  	$(".chosen").chosen({allow_single_deselect: true});*/
			  	showpriscription();
			  	 },
		   error : function(result) {
		    console.log("error in saving product");
		   }
		  });
}

function openPriscriptionRequest(parentid,val){
	 var dataObj={
			    "parentid" : "" + parentid + "",
			    "val" : "" + val + "",
			  };
	var data1 =  JSON.stringify(dataObj);
		$.ajax({
			   url : "priscrequestBookAppointmentAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   document.getElementById("priscrequesttbody").innerHTML=data.responsej;
				   if(val==1){
					   document.getElementById("deliverbtn111").style.display="none";
				   }else{
					   document.getElementById("deliverbtn111").style.display="block";
				   }
				   $('#priscrequestpopup').modal( "show" );
				},
			   	error : function(result) {
			   		jAlert('error', "Something wrong!!!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			  });
}

function selectPriscCheckBox(val){
	
	if (val== true) {
		$('.akashcase_request').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.akashcase_request').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function deliverPriscRequest() {
	var ids="0"; 
	$('.akashcase_request').each(function(){
		if(this.checked == true){
		    ids=ids+","+this.value;
		} 
	});
	
	if(ids=='0'){
		jAlert('error', "Please select at least one checkbox!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal("show");
		document.getElementById("priscreqids_request").value=ids;
		document.getElementById("newmedreqform_request").submit();
	}
}

function openPriscriptionReturn(parentid){
	 var dataObj={
			    "parentid" : "" + parentid + "",
			  };
	var data1 =  JSON.stringify(dataObj);
		$.ajax({
			   url : "priscrequestforreturnBookAppointmentAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   document.getElementById("priscreturntbody").innerHTML=data.responsej;
				   $('#priscreturnpopup').modal( "show" );
				},
			   	error : function(result) {
			   		jAlert('error', "Something wrong!!!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			  });
}

function selectPriscReturnCheckBox(val){
	
	if (val== true) {
		$('.akashcase_return').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.akashcase_return').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function returnproductrequested() {
	var ids="0"; 
	var flag =true;
	var checkflag = true;
	$('.akashcase_return').each(function(){
		if(this.checked == true){
		    ids=ids+","+this.value;
		    var availbeqty = document.getElementById("availaberetrunqty"+this.value).value;
		    var returnqty = document.getElementById("mdrequestqty_return"+this.value).value;
		    if(returnqty==''){
		    	flag = false;
		    }else if(returnqty==0){
		    	flag = false;
		    }else if(returnqty<0){
		    	flag = false;
		    }else if(Number(returnqty)>Number(availbeqty)){
		    	checkflag = false;
		    }
		} 
	});
	
	if(ids=='0'){
		jAlert('error', "Please select at least one checkbox!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(!flag){
		jAlert('error', "Please enter valid return quantity!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(!checkflag){
		jAlert('error', "Entered return quantity greater than available quantity!!!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal("show");
		document.getElementById("priscreqids_return").value=ids;
		document.getElementById("newmedreqform_return").submit();
	}
}


function setclientidbyClinicalnotes(){
	patientId=document.getElementById('cid').value;	
	diaryuserId=document.getElementById('diaryUser').value;	
}

function selectPriscCheckBoxfun(val){
	
	if (val== true) {
		$('.akashcase').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.akashcase').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function deletePrescription(val){
	document.getElementById("parent_id").value = val;
	$('#deletemodel').modal( "show" );
}

function deletePrescription1(){
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	if(delete_reason=='' || delete_reason==' ' || delete_reason=='   ' || delete_reason=='    '){
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="deletepriscriptionPrescription?parentid="+parentid+"&delete_reason="+delete_reason+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deleteIndent1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function deleteIndent1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}
function saveprinttakenstatus(val,id){
	var val1=0;
	if(val==true){
		val1 =1;
	}
	  var url="saveprinttakenstatusPrescription?id="+id+"&val1="+val1+"";  	  
	  if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	  req.onreadystatechange = saveprinttakenstatusRequest;
	  req.open("GET", url, true); 
	  req.send(null);  
}
function saveprinttakenstatusRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}


function deletepriscdatacleanfull(){
	var templatename = document.getElementById("templatename").value;
	var url = "deleteprisctempallEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&templatename="+templatename+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = deletepriscdatacleanfullRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function deletepriscdatacleanfullRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
}


function showopdpriscription123(){
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	//showpriscription();
	repeatePriscDateAjax(patientId,diaryuserId,editcondition_id);
	$('#priscriptionpopup').modal( "show" );
	if(document.getElementById("langbyusr")){
	var language = document.getElementById("langbyusr").value;
	  var selectField = document.querySelector("#google_translate_element select");
	  for(var i=0; i < selectField.children.length; i++){
	    var option = selectField.children[i];
	    // find desired langauge and change the former language of the hidden selection-field 
	    if(option.value==language){
	       selectField.selectedIndex = i;
	       // trigger change event afterwards to make google-lib translate this side
	       selectField.dispatchEvent(new Event('change'));
	       break;
	    }
	  }
	}

}


//gynic form
function insertGynicPriscription(action){
	$("#dashboardloaderPopup").modal('show');
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_btn").disabled = true;
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_print_btn").disabled = true;
	}
	
	//single value
	
	var prepay = 0;
	var postpay = 0; 
	var otherpay = 0;
	var discharge = 0;
	var admission =0;
	
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
	var repeatdate=document.getElementById("repeatdate").value;
	 var mdtemplatename = document.getElementById("mdtemplatename").value;
	 var requestlocationid = document.getElementById("requestlocationid").value;
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
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		goaction = action;
		var priscdose=0;
		$('.jituradioclass').each(function() { 
			if(this.checked==true){
				priscdose = this.value;
			} 					
		});
		
		var locations=sessionStorage.getItem("location");
		locations="OPD";
		var jsonipdid = 0;
		
		if(document.getElementById("jsonipdid")){
			jsonipdid = document.getElementById("jsonipdid").value;
		}
		
		var lastappointmentid = document.getElementById("lastappointmentid").value;
		var gynicform =1;
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&mdtemplatename="+mdtemplatename+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&treatmentgivensatus="+treatmentgivensatus+"&json_ipdid="+jsonipdid+"&lastappointmentid="+lastappointmentid+"&gynicform="+gynicform;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertGynicPriscriptionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
	
}
function insertGynicPriscriptionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prescs_save_btn").disabled = false;
			document.getElementById("prescs_save_print_btn").disabled = false;
			$("#dashboardloaderPopup").modal('hide');
			
			priscadc=req.responseText;
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			gynicPriscData();
			$('#priscriptionpopup').modal( "hide" );
			
			if(goaction==1){
				openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
			}
			
		}
	}
}


function gynicPriscData(){
	var lastappointmentid = document.getElementById("lastappointmentid").value;
	var url = "gynicpriscdataBookAppointmentAjax?clientid="+patientId+"&lastappointmentid="+lastappointmentid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = gynicPriscDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function gynicPriscDataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("gynicformtreat").innerHTML = req.responseText;
			 }
		}
}

function deletegynicmed(r,id){
	 
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("priscTabletreat").deleteRow(i);
	
	deletegynicmedfromtable(id);	
	
}
function deletegynicmedfromtable(id){
	var url = "removemedicineBookAppointmentAjax?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deletegynicmedfromtableRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function deletegynicmedfromtableRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    
		}
	}
}

function changepriscgivenPrisctimename(index,srno) {
	//Prisctimename
	var templatename = document.getElementById("templatename").value;
	var dosage=document.getElementById("dosage"+index).value;
	changepriscgivendays(index,dosage);
	var url="changepriscgiventimenameEmr?index="+index+"&srno="+srno+"&templatename="+templatename+"&dosage="+dosage+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenPrisctimenameRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenPrisctimenameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

function changepriscgivenroute(index,qty) {
	var templatename = document.getElementById("templatename").value;
	var url="changepriscgivenrouteEmr?index="+index+"&qty="+qty+"&templatename="+templatename+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changepriscgivenrouteRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
	
}

function changepriscgivenrouteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prisctable").innerHTML = req.responseText;
		}
	}
	
}

function priscmodifyjs(index,val,duration,drgivenqty,prisctimename,dosenotes,priscindivisualremark){
	var url="priscmodifyEmr?index="+index+"&frequency="+val+"&duration="+duration+"&drgivenqty="+drgivenqty+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = priscmodifyjsRequest;
    req.open("GET", url, true); 
              
    req.send(null);	
}
function priscmodifyjsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			/*document.getElementById("prisctable").innerHTML = req.responseText;*/
		}
	}
	
}










function insertDischargePriscription1(action){
	$("#dashboardloaderPopup").modal('show');
	var btndisableflag= false;
	if(document.getElementById("prescs_save_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_btn").disabled = true;
	}else{
		btndisableflag = true;
	}
	if(document.getElementById("prescs_save_print_btn").disabled==false){
		btndisableflag = true;
		document.getElementById("prescs_save_print_btn").disabled = true;
	}
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
	//var discharge = 1;
	var admission =0;
	
	if(discharge==1&&treatmentadvc==1){
		treatmentgivensatus=1;
	}else{
		treatmentgivensatus=0;
	}
	
/*	if(document.getElementById("paymode2").checked==true){
		var prepay = 1;
	}
	if(document.getElementById("paymode1").checked==true){
		var postpay = 1;
	}
	if(document.getElementById("paymode3").checked==true){
		var otherpay = 1;
	}
	*/
	var priscautoid = "1";/*document.getElementById("paymode2").value;*/
	var priscdosenotes = document.getElementById("priscdosenotes").value;
	var followupsqty = document.getElementById("followupsqty").value;
	var followupstype = document.getElementById("followupstype").value;
	var priscadvoice = document.getElementById("priscadvoice").value;
	var repeatdate=document.getElementById("repeatdate").value;
	 var mdtemplatename = document.getElementById("mdtemplatename").value;
	 var requestlocationid = document.getElementById("requestlocationid").value;
	  var bamsbckdate = document.getElementById("backDate").value;  //For Bams
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
	var errorflag=false;
	var oneerrorflag = true;
	$('.akashclasss').each(function() { 
		oneerrorflag = false;
		var drqtyin = document.getElementById("drgivenqty"+this.value).value;	
		if(drqtyin=='' && errorflag==false){
			errorflag = true;
		}else if(Number(drqtyin)<=0 && errorflag==false){
			errorflag = true;
		}
	});
	if(btndisableflag==false){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Double click not allowed!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(oneerrorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorflag){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid qty!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(requestlocationid==0){
		document.getElementById("prescs_save_btn").disabled = false;
		document.getElementById("prescs_save_print_btn").disabled = false;
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', 'Please Select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		goaction = action;
		//var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&categoryid="+categoryid+"&mdicinenameid="+mdicinenameid+"&priscdose="+priscdose+"&priscfreq="+priscfreq+"&priscdays="+priscdays+"&mdicinenametxt="+mdicinenametxt+"&prisctype="+prisctype+"&prisccode="+prisccode+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscautoid="+priscautoid+"&prisctotal="+prisctotal+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+" ";
		
		var priscdose=0;
		$('.jituradioclass').each(function() { 
			if(this.checked==true){
			    
				priscdose = this.value;
			} 					
		});
		
		
		/*if(priscdose==0){
				jAlert('error', 'Please Select Dosage.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}*/
		
		var locations=sessionStorage.getItem("location");
		locations="ipd";
		var jsonipdid = 0;
		
		if(document.getElementById("jsonipdid")){
			jsonipdid = document.getElementById("jsonipdid").value;
		}
		var apmtId =0;
		if(document.getElementById("consulationForm")){
			apmtId = document.getElementById("apmtId").value;
			locations="OPD";
		}
		
		var url = "addpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+"&prepay="+prepay+"&postpay="+postpay+"&otherpay="+otherpay+"&priscdosenotes="+priscdosenotes+"&followupsqty="+followupsqty+"&followupstype="+followupstype+"&english="+english+"&regional="+regional+"&hindi="+hindi+"&priscadvoice="+priscadvoice+"&discharge="+discharge+"&editparentpriscid="+editparentpriscid+"&repeatdate="+repeatdate+"&mdtemplatename="+mdtemplatename+"&admission="+admission+"&locations="+locations+"&requestlocationid="+requestlocationid+"&treatmentgivensatus="+treatmentgivensatus+"&json_ipdid="+jsonipdid+"&bamsbckdate="+bamsbckdate+"&apmtId="+apmtId;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = insertDischargePriscription1Request;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
	
}

function insertDischargePriscription1Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("prescs_save_btn").disabled = false;
			document.getElementById("prescs_save_print_btn").disabled = false;
			$("#dashboardloaderPopup").modal('hide');
			
			priscadc=req.responseText;
			jAlert('success', 'Record saved successfully.', 'success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//showDischargePopup();
			if(document.getElementById("consulationForm")){
				consultationPriscData();
			}else{
				dischargePriscData();
			}
			
			$('#priscriptionpopup').modal( "hide" );
			
			if(goaction==1){
				openEmrPopup('printpriscEmr?selectedid=0&halfprint=1');
			}
			
		}
	}
}



function getmedondiagnosis(id) {
	
	var url="getmedondiagnosisPrescriptiondetails?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getmedondiagnosisRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	 
}


function getmedondiagnosisRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('mdicinediv').innerHTML=req.responseText;
							$("#mdicinename").trigger("chosen:updated");
						  	$(".chosen").chosen({allow_single_deselect: true});
		}
	}
	
}

