var tmentepisodeid = 0;
var tempclientid = 0;
function modifyAppointment(date,diaryuser,starttime,duration,apmtType,notes,endtime,location,treatmentepisodeid,id,apmtSlotId,diaryuserid,clientid,condition){
	
	$('#appointment').modal( "show" );
	
	document.getElementById('user').value = diaryuser;
	document.getElementById('client').value = document.getElementById('findclient').value;
	document.getElementById('location').value = location;
	document.getElementById('date').value = date;
	document.getElementById('sTime').value = starttime;
	document.getElementById('endTime').value = endtime;
	document.getElementById('apmtDuration').value = duration;
	document.getElementById('apmtType').value = apmtType;
	$("#apmtType").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	document.getElementById('condition').value = condition;
	$("#condition").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	document.getElementById('notes').value = notes;
	
	var cid = clientid;
	
	
	tmentepisodeid = treatmentepisodeid;
	
	editStartTime = starttime;
	editAppointId = id;
	document.getElementById('slotId').value = apmtSlotId;
	document.getElementById('diaryUserId').value = diaryuserid;
	document.getElementById('clientId').value =clientid;
	
	
	
	findTreatmentEpisode(cid);
	
	
	
}


function findTreatmentEpisode(clientid){
	
	// var clientid = read_cookie("cookieClientId");
	
	var url = "setTreatmentEpisode?clientid="+clientid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = findTreatmentEpisodeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function findTreatmentEpisodeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('treatmentepisodeajax').innerHTML = req.responseText;
			document.getElementById('treatmentEpisode').value = tmentepisodeid;
		}
	}
}


function showPopUp(){
	
	var url = "Client";

	$('#clientSearchDiv').modal( "show" );
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	//function showAllPatientPopUpRequest(){
	function showPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				
	         	
				
	         }
		}
	}
	
	
/*
 * function setPatientName(name,id,type,typeName){
 * 
 * document.getElementById("findclient").value = name;
 * document.getElementById("client").value = name;
 * document.getElementById("clientId").value = id; getClientFullName(id);
 * 
 * 
 * $('#clientSearchDiv').modal( "hide" );
 * 
 * 
 * 
 *  }
 */

var finderapmtid = 0;


function openFinderCancelApmtPopup(id){
	finderapmtid = id;
	
	 $( "#cancelApmtNoteDiv" ).modal( "show" );
	 
}

function deleteFinderNotAviSlot(){
	if(document.getElementById('cancelApmtNote').value==''){
		jAlert('error', 'Please enter cancel appointment note.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		document.getElementById("id").value = finderapmtid;
		document.getElementById("cancelfinderApmtNote").value =  document.getElementById("cancelApmtNote").value;
		/* document.getElementById("finderfrm").submit(); */
		deleFinder(finderapmtid,document.getElementById("cancelApmtNote").value);
		$( "#cancelApmtNoteDiv" ).modal( "hide" );
		
	}
	
	
}


function deleFinder(apmtId, cancelNote){
	$( "#baselayout1loaderPopup" ).modal( "show" );
	  var url="deletenewFinder?apmtId="+apmtId+"&cancelnote="+cancelNote;
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleFinderRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
}
function deleFinderRequest(){
	 if (req.readyState == 4) {
         if (req.status == 200) {
        	   
        	 $( "#baselayout1loaderPopup" ).modal( "hide" );
        	 document.location.reload();
             }
         }
}


function sendfollowsms(){
	var clientid=document.getElementById("clid").value;
	var notes=document.getElementById("transliterateTextarea").value;
	  var url="sendfollowsmsFinder?clientid="+clientid+"&msg="+notes+"";
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendfollowsmsRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
}
function sendfollowsmsRequest(){
	 if (req.readyState == 4) {
         if (req.status == 200) {
        	 $('#smssend').modal( "hide" );
        	 jAlert('success', 'SMS Send successfully.', 'Success Dialog');
 			
 			setTimeout(function() {
 				$("#popup_container").remove();
 				removeAlertCss();
 			}, alertmsgduration);
             }
         }	
}
function showdeptpopup(val,id,clientid){
	 $('#departmentconfirm').modal( "show" );
	 document.getElementById("deptid").value=val;
	 document.getElementById("deptclientid").value=clientid;
	 document.getElementById("deptapt").value=id;
}

function showconfirm(){
		
		var val=document.getElementById("deptid").value;
		var id=document.getElementById("deptapt").value;
		var clientid= document.getElementById("deptclientid").value;
		 var url="showconfirmFinder?chngdept="+val+"&aptid="+id+"&clientid="+clientid+"";
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showconfirmRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);   
	  
}
function showconfirmRequest(){
	 if (req.readyState == 4) {
         if (req.status == 200) {
        	 $('#departmentconfirm').modal( "hide" );
        	 jAlert('success', 'Patient Referred Successfully.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
             }
         }	
}

function showdoctpopup(val,id,clientid){
	if(val!=0){
	 document.getElementById("docid").value=val;
	 document.getElementById("docclientid").value=clientid;
	 document.getElementById("docapt").value=id;
	var department=document.getElementById("deptfrm_dept").value;
	var date=document.getElementById("fromDate").value;

	 var url="checkdiaryexistFinder?nuserid="+val+"&date="+date+"&dept="+department+"";
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showdoctpopupRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);   	
	
	 
}
}
function showdoctpopupRequest(){
if (req.readyState == 4) {
         if (req.status == 200) {
	
		var data=req.responseText.split("@#");
		if(data[0]==1){
		
						jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
						setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("doctid").value="0";
             }else{
					
					 $('#doctorconfirm').modal( "show" );
					document.getElementById("secondarydoc").innerHTML=data[1];
					document.getElementById("primarydocname").innerHTML=data[2];
				}
         }	
}
}
function opdconfirm(){
	var val=document.getElementById("docid").value;
	var id=document.getElementById("docapt").value;
	var selectsecondary=document.getElementById("selectedsecondary").value;
	var clientid= document.getElementById("docclientid").value;
		 var url="opdconfirmFinder?diaryuser="+val+"&aptid="+id+"&clientid="+clientid+"&secondary="+selectsecondary+"";
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = opdconfirmRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);   
}
function opdconfirmRequest(){
	 if (req.readyState == 4) {
         if (req.status == 200) {
        	 $('#doctorconfirm').modal( "hide" );
        	 jAlert('success', 'Patient OPD Book Successfully.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
				window.location.reload();
             }
         }	
}
function changedepartment(clientd,aptid) {
	
	var url = "changedepartmentBookAppointmentAjax?clientid="+clientd+"&aptid="+aptid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changedepartmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	
function changedepartmentRequest() {

if (req.readyState == 4) {
	if (req.status == 200) {
		$("#changedeoartment").modal( "show" );
		var data=req.responseText.split('@%$');
		document.getElementById('depatylist').innerHTML =data[0];
		document.getElementById('predept').innerHTML =data[1];
		$("#chdept").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
	}
}
}

function referdept() {
	
	document.getElementById("refrbtn").style.visibility = "hidden";
	/*
	 * var x=document.getElementById("chdept"); var
	 * arr=document.getElementById('sellect').value; var arr2=arr.split(",");
	 * var val="0"; for (var i = 0; i < x.options.length; i++) {
	 * if(x.options[i].selected ==true){ val=val +","+x.options[i].value; } }
	 * var tmp=val.split(","); for(var i=0; i<tmp.length;i++ ) {
	 * if(tmp[i]=="0") tmp.splice(i,1); }
	 */
	let difference = document.getElementById('referselected').value; 
    // let difference = tmp.filter(x => !arr2.includes(x));
// var duserid=document.getElementById('chdept').multiple;
	var clientd=document.getElementById('cllid').value;
	var aptid=document.getElementById('appptid').value;
	var referremark=document.getElementById('referrema').value;
	if(difference.length!=0 || difference!=''){
	var url = "referdeptBookAppointmentAjax?dept="+difference+"&clientid="+clientd+"&aptid="+aptid+"&sts=0&referremark="+referremark+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = referdeptRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}else{
		jAlert('error', 'Please Select Department.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById("refrbtn").style.visibility = "visible";
	}
	
}
	
function referdeptRequest() {

if (req.readyState == 4) {
	if (req.status == 200) {
		$("#changedeoartment").modal( "hide" );
			jAlert('success', 'Patient Referred Successfully.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		/* showdisplaynewopd(); */
		window.location.reload();
		document.getElementById("refrbtn").style.display="";
	}
}
}


function openmodifypopup(aptid,clientid){
		
	var url = "getmodifyappointmentFinder?clientid="+clientid+"&aptid="+aptid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openmodifypopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	
function openmodifypopupRequest() {

if (req.readyState == 4) {
	if (req.status == 200) {
		$("#modifypopup").modal( "show" );
		var tmp=req.responseText.split("#@");
		document.getElementById('modifyselect').innerHTML =tmp[0];
		document.getElementById("modifyptname").innerHTML=tmp[1];
		document.getElementById("modifybooktime").innerHTML=tmp[2];
		$("#modifyslect").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		}
	}
}
function updatemodifyappointment(){
	var appointmentid=document.getElementById('modifyappoid').value;
	var clientid=document.getElementById('modifyappoclientid').value;
	var department=document.getElementById('modifyslect').value;
	var url = "updatemodifyappointmentFinder?clientid="+clientid+"&aptid="+appointmentid+"&department="+department+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updatemodifyappointmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function updatemodifyappointmentRequest() {
if (req.readyState == 4) {
	if (req.status == 200) {
		jAlert('success', 'Patient Department Modified Successfully.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		window.location.reload();
		}
	}
}
function setdefaultdr(){
	document.getElementById("doctid").value="0";
	
}
function allids(){
	var secondry = 0;
     $('.allchk').each(function() { // loop through each checkbox
        // this.checked = true; //select all checkboxes with class "checkbox1"
        if(this.checked==true){
        	secondry = secondry + ',' + this.value;
        }
         
     });
	var valu='';
	if(secondry!='0'){
	var array = secondry.split(",");
	
	array = array.filter(val => val !== "0");
	valu=array.toString();
	}else{
		valu='';
	}
     document.getElementById('selectedsecondary').value = valu;
}

function showremarklist(val){
	
  $("#showremark").modal( "show" );
	 var url = "showremarklistFinder?id="+val;
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showremarklistRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function showremarklistRequest() {
  if (req.readyState == 4) {
	    if (req.status == 200) {
		 $("#showremark").modal( "show" );
		var tmp=req.responseText.split("@#");
		document.getElementById('listremark').innerHTML=tmp[0];
		document.getElementById('patientname').innerHTML=tmp[1];
		document.getElementById('refertodept').innerHTML=tmp[2];
		document.getElementById('referfromdept').innerHTML=tmp[3];
		}
	}
}

function showsittingforPatient(val,clientid,clientName,selecteddept){
	
	document.getElementById("sitting_Clientid").value=clientid;
	document.getElementById("dept_opdid").value=val;
	document.getElementById("patientnm").innerHTML=clientName;
	
	
	deptOpdlist(selecteddept,clientid);
}


function deptOpdlist(selecteddept,clientid){

   var url="deptOpdlistFinder?selecteddept="+selecteddept+"&clientid="+clientid+"";

        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = opdlistRequest;
		req.open("GET", url, true);
		req.send(null);

}


function opdlistRequest(){

 if (req.readyState == 4) {
	    if (req.status == 200) {
		  var str = req.responseText;
		    var data = str.split("~~");
			document.getElementById("sittingDeptDiv").innerHTML=data[0];
			document.getElementById("proddiv").innerHTML=data[1];
			
			$("#dept").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
		  	
	        
			$('#showsitting').modal( "show" );	
	}

  }

}

function showsittingforPatient1(val,clientid,clientName,selecteddept,totalsetting,ipdsetting){
	
	document.getElementById("sitting_Clientid").value=clientid;
	document.getElementById("dept_opdid").value=val;
	document.getElementById("patientnm").innerHTML=clientName;
	
	
	deptOpdlist1(selecteddept,clientid,totalsetting,ipdsetting);
}


function deptOpdlist1(selecteddept,clientid,totalsetting,ipdsetting){
   if(totalsetting==ipdsetting){
	alert("Session has been expired");
   }else{
   var url="deptOpdlistFinder?selecteddept="+selecteddept+"&clientid="+clientid+"";

        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = opdlistRequest;
		req.open("GET", url, true);
		req.send(null);
  }
}


function opdlistRequest(){

 if (req.readyState == 4) {
	    if (req.status == 200) {
		  var str = req.responseText;
		    var data = str.split("~~");
			document.getElementById("sittingDeptDiv").innerHTML=data[0];
			document.getElementById("proddiv").innerHTML=data[1];
			document.getElementById("sittingno").value=data[2];
			$("#dept").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
		  	
	        
			$('#showsitting').modal( "show" );	
	}

  }

}

function showsittingfollowuplist(val) {
	
	//var patientname=document.getElementById("patientnm").value
	var deptopdid=document.getElementById("dept_opdid").value;
	var clientid=document.getElementById("sitting_Clientid").value;
	var department=document.getElementById("dept").value;
	var sitting=document.getElementById("sitting_id").value;
	var date=document.getElementById("sittingDate").value;
	/*var followup=document.getElementById("sittingFollowup").checked;*/
	var remark=document.getElementById("referremark").value;
	var proceduremaster=document.getElementById("master_id").value;
	var procedureid=document.getElementById("procedure_id").value;
	var sittingnum=document.getElementById("sittingno").value;
	var sittinbckdate=document.getElementById("sittingBackDate").value;
	var diagnosis;
	if(val==false){
		 diagnosis="";
	}else{
	 diagnosis=document.getElementById("diagnosis").value;
    }
	var flag = false;
	var apmtId=0;
	if(document.getElementById("emrPageId")){
		apmtId = document.getElementById("sitting_appointmentid").value;
		flag= true;
	}

	
	if (department == '') {

		jAlert('error', "Please select department!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else if (proceduremaster == '') {

		jAlert('error', "Please select proceduremaster!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	else if (procedureid == '0') {

		jAlert('error', "Please select procedurelist!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(deptopdid==0){
		jAlert('error', "patient does not belong to that department!", 'Error Dialog');
		
	}else if(flag && apmtId==0){
	
		jAlert('error', "Opd not book !", 'Error Dialog');
	}
	
	
	else{
	
	     $("#showsitting").modal( "show" );
	     var url ="savesittingFinder?department="+department+"&sitting="+sitting+"&date="+date+"&remark="+remark+"&clientid="+clientid+"&procedure="+proceduremaster+"&procedureid="+procedureid+"&deptopdid="+deptopdid+"&sittingnum="+sittingnum+"&diagnosis="+diagnosis+"&sittinbckdate="+sittinbckdate+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = savesittingRequest;
		req.open("GET", url, true);
		req.send(null);
		
	}
		
	}

function savesittingRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			  var deptopdid=document.getElementById("dept_opdid").value;
		      var appointmentid=document.getElementById("sitting_appointmentid").value;
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
  
function editSitting(id) {
	
	var url="editsittingFinder?id="+id+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = seteditSittingRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function seteditSittingRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			
			document.getElementById("id").value= data[0];
			document.getElementById("edit_dept").className ="";
			document.getElementById("edit_sittingDeptDiv").innerHTML= data[1];
			document.getElementById("edit_dept").className ="form-control chosen-select";
			$("#edit_dept").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
			document.getElementById("ed_div").innerHTML= data[2];
			$("#edit_sittings").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
			document.getElementById("edit_remark").value= data[3];
			document.getElementById("ed_sittingDate").value= data[4];
			document.getElementById("mased_div").innerHTML= data[5];
			$("#edit_master_id").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
			
			document.getElementById("pro_durediv").innerHTML= data[6];
			$("#edit_procedure").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
		  	
		  	document.getElementById("si_no").value= data[7];
			document.getElementById("edit_sittingBackDate").value= data[8];
			$('#editsitting').modal( "show" );	
		 }
	}

}

function updatesittingfollowup(){

    var id=document.getElementById("id").value;
	var deptopdid=document.getElementById("opdid").value;
	var clientid=document.getElementById("sittingClientid").value;
	var department=document.getElementById("edit_dept").value;
	var sitting=document.getElementById("edit_sittings").value;
	var date=document.getElementById("ed_sittingDate").value;
	var remark=document.getElementById("edit_remark").value;
	var proceduremaster=document.getElementById("edit_master_id").value;
	var procedureid=document.getElementById("edit_procedure").value;
	var sittingnum=document.getElementById("si_no").value;
	var sittingdate=document.getElementById("edit_sittingBackDate").value;
	
	if (department == '') {

		jAlert('error', "Please select department!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else if (proceduremaster == '') {

		jAlert('error', "Please select proceduremaster!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else if (procedureid == '0') {

		jAlert('error', "Please select procedure!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}
	
  else{
	 $("#showsitting").modal( "hide" );
	var url = "updatesittingFinder?id="+id+"&department="+department+"&sitting="+sitting+"&proceduremaster="+proceduremaster+"&procedureid="+procedureid+"&date="+date+"&remark="+remark+"&sittingnum="+sittingnum+"&sittingdate="+sittingdate+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatesittingRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}

}

function updatesittingRequest(){


      if (req.readyState == 4) {
		 if (req.status == 200) {
			 var str = req.responseText;
			  $("#baselayout1loaderPopup").modal("hide");
			  if (str == '0') {
			      alert("data not added.");                   // error
			  } else {
				alert("update Successfully.");
				window.location.reload();
                     // success
			}
			  
		 }
	}
          
}


function deleteSitting(id){
	
	var url = "deletesittingFinder?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = deletesittingRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function deletesittingRequest(){
     	if (req.readyState == 4) {
		    if (req.status == 200) {
			  var str = req.responseText;
			   $("#baselayout1loaderPopup").modal("hide");
			
			if (confirm("Do you want to delete?") == true) {
                         "Data deleted successfully!";
                         window.location.reload();
             } else {
                         " Cancelled!";
           }
		}
    }

}

function shositting(clientid){
	
	var url = "sittingfollowuplistEmr?clientid="+clientid+"";
	
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
			 $( "#sitting_details" ).modal( "show" );		
		}
	}
	
}

function setDepartmentsitting(id) {
	
	var url="getsittingdepartmentFinder?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	 else if (window.ActiveXObject) {
		 isIE = true;
		 req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setSittingofDepartmentRequest;
    req.open("GET", url, true); 
    req.send(null);
    
  }
  
function setSittingofDepartmentRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("proddiv").innerHTML=req.responseText;
			   $("#sitting_id").trigger("chosen:updated");
		  	   $(".chosen-select").chosen({allow_single_deselect: true});
		  	   
		  	   if(document.getElementById("emrPageId")){
	               Departmentopdid();
	             }
		  	  
         }
	}	 
	
}

function Departmentopdid(){

 var clientid=document.getElementById("sitting_Clientid").value;
 var deptid=document.getElementById("dept").value;
 
 var url="deptOpdidEmr?clientid="+clientid+"&deptid="+deptid+"";
 
 if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = departopdidRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}


function departopdidRequest(){

    if (req.readyState == 4)  {
		   if (req.status == 200) {
			     var str = req.responseText;
			     var data = str.split("~~");
			        document.getElementById("dept_opdid").value= data[0];
			        document.getElementById("sitting_appointmentid").value= data[1];
				   

			 }
		  	  
         }
	}

function setProcedurelist(id){
	
	var url="getproceduremasterlistFinder?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setProcedurelistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}


function  setProcedurelistRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("pro_dure").innerHTML=req.responseText;
			   $("#procedure_id").trigger("chosen:updated");
		  	   $(".chosen-select").chosen({allow_single_deselect: true});
		  	  
         }
	}	 
}

function setproceduremaster(id){
	
	var url="masterlistFinder?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setmasterlistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function  setmasterlistRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("mas_div").innerHTML=req.responseText;
			   $("#master_id").trigger("chosen:updated");
		  	   $(".chosen-select").chosen({allow_single_deselect: true});
         
		 }
	}	 
	
}
function refersect(){
	var selectedwardid = 0;
     $('.sscc').each(function() { // loop through each checkbox
        // this.checked = true; //select all checkboxes with class "checkbox1"
        if(this.checked==true){
        	selectedwardid = selectedwardid + ',' + this.value;
        }
         
     });
	var array = selectedwardid.split(",");
	
	array = array.filter(val => val !== "0");
	var valu=array.toString();
    document.getElementById('referselected').value = valu;
}
function setdept(id) {
	document.getElementById('alldept').value=id;
}

function openFollowUpPopUp(deptid,clientid){
	
var url = "getProcedureListFinder?deptid="+deptid+"&clientid="+clientid+" ";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openFollowUpPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function openFollowUpPopUpRequest() {
if (req.readyState == 4) {
	if (req.status == 200) {
		var tmp=req.responseText.split("@#");
		document.getElementById('procedure').innerHTML = req.responseText ;
		$("#addfollowup").modal("show");
		}
	}
}



function saveFollowUp(){
	var procedure = document.getElementById("procedure").value;
	if(procedure == 0){
		jAlert('error', 'Please Select Procedure', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		document.getELementById("saveFollowUp").submit;
		
	}
}

function gettplist(patientType) {
	var dataObj={
		"patientType":""+patientType+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "getMainTpListFinder",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		  var tpList= data.tpList;
		  document.getElementById("dstp").innerHTML= data.tpList;
		  $("#tpid").trigger("chosen:updated");
		  $(".chosen-select").chosen({allow_single_deselect: true});
		  
		  
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

function selectAllCheckBoxOfDailyopd(val){
	
	if (val.checked == true) {
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

function addTocoloursave(val){
	
	var ids="0";
	    $('.case').each(function() { 
			if(this.checked == true){
			    ids=ids+","+this.value;
			} 
		});
		
		var url="savecolorcodeFinder?data="+ids+"&color="+val+"";	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = savecolorcodeRequest;
	    req.open("GET", url, true); 
	    req.send(null);  	
	
	
}
function savecolorcodeRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			$('.case').each(function() { 
				if(this.checked == true){
					 this.checked = false;    
				} 
			});
			
		 }
	}	 
}

function showipdsittingfollowuplist(val) {
	
	//var patientname=document.getElementById("patientnm").value
	var deptopdid=document.getElementById("dept_opdid").value;
	var clientid=document.getElementById("sitting_Clientid").value;
	var department=document.getElementById("dept").value;
	var sitting=document.getElementById("sitting_id").value;
	var date=document.getElementById("sittingDate").value;
	/*var followup=document.getElementById("sittingFollowup").checked;*/
	var remark=document.getElementById("referremark").value;
	var proceduremaster=document.getElementById("master_id").value;
	var procedureid=document.getElementById("procedure_id").value;
	var sittingnum=document.getElementById("sittingno").value;
	var bed_ward=document.getElementById("bed_ward").value;
	var Hosp_name=document.getElementById("Hosp_name").value;
	var diagnosis;
	if(val==false){
		 diagnosis="";
	}else{
	 diagnosis=document.getElementById("diagnosis").value;
    }
	var flag = false;
	var apmtId=0;
	if(document.getElementById("emrPageId")){
		apmtId = document.getElementById("sitting_appointmentid").value;
		flag= true;
	}

	
	if (department == '') {

		jAlert('error', "Please select department!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else{
	
	     $("#showsitting").modal( "show" );
	     var url ="saveipdsittingFinder?department="+department+"&sitting="+sitting+"&date="+date+"&remark="+remark+"&clientid="+clientid+"&procedure="+proceduremaster+"&procedureid="+procedureid+"&deptopdid="+deptopdid+"&sittingnum="+sittingnum+"&diagnosis="+diagnosis+"&bed_ward="+bed_ward+"&Hosp_name="+Hosp_name+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = saveipdsittingfollowuplistRequest;
		req.open("GET", url, true);
		req.send(null);
		
	}
		
	}

function saveipdsittingfollowuplistRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			  var deptopdid=document.getElementById("dept_opdid").value;
		      var appointmentid=document.getElementById("sitting_appointmentid").value;
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

  
  function showipdsitting(clientid){
	
	var url = "ipdsittingfollowuplistEmr?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
	
    req.onreadystatechange = showipdsittingfollowuplistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function showipdsittingfollowuplistRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str = req.responseText;
			 var data = str.split("~");
			   document.getElementById("carttbody").innerHTML = data[0];
			 $( "#sitting_details" ).modal( "show" );		
		}
	}
	
}