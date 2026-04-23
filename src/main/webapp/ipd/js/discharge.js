function updatedischargestatusdb(te,c1,c2,di) {
    
    $('#baselayout1loaderPopup').modal( "show" );
	ipdtreatmentepisodeid = te;
	idis_initiate_status = di;
	setDischargeStatus(c1,c2);
	window.location.reload();
	
}
function updatedischargestatusdb1(te,c1,c2,di) {
    
    $('#baselayout1loaderPopup').modal( "show" );
	ipdtreatmentepisodeid = te;
	idis_initiate_status = di;
	setDischargeStatus1(c1,c2);
	//window.location.reload();
	//showInitiateDischarge();
	$('#initialdischarge').modal( "hide" );
}

var ipdid=0;
var ipdpatientid=0;
var ipdtreatmentid=0;


function setInitiateDischargeStatus(c1,c2){
	idis_initiate_status = 1;
	setDischargeStatus1(c1,c2);
}

function setDischargeStatus1(column,column2){
	var flag = true;
	if(idis_initiate_status==0){
			jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(!flag){
		jAlert('error', "Please complete nursing checklist!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}
	else if(column=='dis_nursing_status'){
	
	        if(idis_nursing_status==0){
	              setviewadvoice();
	        } else {
	            doup(column,column2);
	        }
	
	}else{
	
     doup(column,column2);
    }
}



function setDischargeStatus(column,column2){
	var flag = true;
	  $('.akashdclass'+ipdtreatmentepisodeid).each(function() { 
			if(this.checked == false){
			    flag=false;
			} 
									
		});

	
	if(idis_initiate_status==0){
			jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(!flag){
		jAlert('error', "Please complete nursing checklist!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}
	else if(column=='dis_nursing_status'){
	
	        if(idis_nursing_status==0){
	              setviewadvoice();
	        } else {
	            doup(column,column2);
	        }
	
	}else{
	
     doup(column,column2);
    }
}


function doup(c1,c2){
   
   var url = "updateInitialDischarge?column="+c1+"&ipdtreatmentepisodeid="+ipdtreatmentepisodeid+"&column2="+c2+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setDischargeStatusRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}



function setinitialDischargeIpd(column,column2,ipdtreatmentepisodeID,teststatus) {
 
     var msg="";

    if(teststatus==0) {
       msg="Do you Want to Start Discharge Process?";
    }
    else {
       msg="Do you Want to Stop Discharge Process?";
    }
    
    var t=confirm(msg);
    
    if(t==true){

		$('#dashboardloaderPopup').modal( "show" );

    } else {
       return;
    }
    
    idis_initiate_status = 1;
    var url = "updateInitialDischarge?column="+column+"&ipdtreatmentepisodeid="+ipdtreatmentepisodeID+"&column2="+column2+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setinitialDischargeIpdRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
   

function setinitialDischargeIpdRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				//document.getElementById("allPatient").innerHTML = req.responseText;
				document.location.reload();
	//         	showInitiateDischarge();
				
	         }
		}
	}
	



function setDischargeStatusRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				//document.getElementById("allPatient").innerHTML = req.responseText;
				$('#viedschnadv').modal( "hide" );
				//   05-12-2019 unwantedly called from dashboard
				if(document.getElementById("isfromdashboard_disc")){
					window.location.reload();
				}else{
					if(neq_fromdashboard==1){
						// if from discharge dashboard
						window.location.reload();
					}else{
						showInitiateDischarge();
					}
				}
				
	         	
				
	         }
		}
	}
	
	
	function showInitiateDischarge(){
		var url = "statusCommonnew?ipdtreatmentepisodeid="+ipdtreatmentepisodeid+"&clientid="+ipdclientid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showInitiateDischargeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
		
	}
	
	function showInitiateDischargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				var data = req.responseText;
				var temp = data.split('~');
				
				
				
				 idis_form_status = temp[0];
				 idis_form_time = temp[1];
				
				 idis_mdicine_status = temp[2];
				 idis_mdicine_time = temp[3];
				
				
				 idis_bill_status = temp[4];
				 idis_bill_time = temp[5];
				
				
				 idis_nursing_status = temp[6];
				 idis_nursing_time = temp[7];
				 
				 idis_initiate_status = temp[8];
								
				  document.getElementById("mydislabel").innerHTML="Discharge Status For ("+temp[10]+")";
				  
				  document.getElementById("disformcheckdiv").innerHTML =temp[11];
				  document.getElementById("dismedicalcheckdiv").innerHTML =temp[12];
				  document.getElementById("disbillingcheckdiv").innerHTML =temp[13];
				  document.getElementById("disnursingcheckdiv").innerHTML =temp[14];
				  
				  showDischargeStatus();
				  $('#initialdischarge').modal( "show" );
				
	         }
		}
	}
	
	
	function showDischargeStatus(){
	
		if(idis_initiate_status==0){
			document.getElementById("disinitbtnid").innerHTML = 'START';
		}else{
			document.getElementById("disinitbtnid").innerHTML = 'STOP';
		}
	
		if(idis_form_status==0){
			document.getElementById("disformcompletedid").innerHTML = '<i class="fa fa-spinner fa-2x fa-pulse text-danger"></i>';
		}else{
			document.getElementById("disformcompletedid").innerHTML = '<i class="fa fa-check fa-2x text-success"></i><br><small style="font-size: 15px;">Completed</small><p class="dischptext"> '+idis_form_time+'</p>';
		}
		
		if(idis_mdicine_status==0){
			document.getElementById("dismedicinecompletedid").innerHTML = '<i class="fa fa-spinner fa-2x fa-pulse text-danger"></i>';
		}else{
			document.getElementById("dismedicinecompletedid").innerHTML = '<i class="fa fa-check fa-2x text-success"></i><br><small style="font-size: 15px;">Completed</small><p class="dischptext"> '+idis_mdicine_time+'</p>';
		}
		
		if(idis_bill_status==0){
			document.getElementById("disbillcompletedid").innerHTML = '<i class="fa fa-spinner fa-2x fa-pulse text-danger"></i>';
		}else{
			document.getElementById("disbillcompletedid").innerHTML = '<i class="fa fa-check fa-2x text-success"></i><br><small style="font-size: 15px;">Completed</small><p class="dischptext"> '+idis_bill_time+'</p>';
		}
		
		if(idis_nursing_status==0){
			document.getElementById("disnursingcompletedid").innerHTML = '<i class="fa fa-spinner fa-2x fa-pulse text-danger"></i>';
		}else{
			document.getElementById("disnursingcompletedid").innerHTML = '<i class="fa fa-check fa-2x text-success"></i><br><small style="font-size: 15px;">Completed</small><p class="dischptext"> '+idis_nursing_time+'</p>';
		}
		
	}
	
	function endDischarge(){
		if(idis_initiate_status==0){
			jAlert('error', 'Please initiate  discharge proccess first.', 'Error Dialog');
			
			return;
		}
		if(idis_form_status==0){
			jAlert('error', 'Please complete discharge form.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(idis_mdicine_status==0){
			jAlert('error', 'Please complete medicine kit.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(idis_bill_status==0){
			jAlert('error', 'Please complete bill statement.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(idis_nursing_status==0){
			jAlert('error', 'Please complete nursing advoice.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var flag = false;
			if(document.getElementById("isfromdischargedashbaord")){
				if(document.getElementById("isfromdischargedashbaord").value==1){
					document.getElementById("endclientip").value = neq_ipdid;
					flag =true;
				}else{
					document.getElementById("endclientip").value = ipdaddmissionid;
				}
			}else{
				document.getElementById("endclientip").value = ipdaddmissionid;
			}
			
			document.getElementById("endclientid").value = ipdclientid;
			document.getElementById("endtreatmentEpisode").value = ipdtreatmentepisodeid;
			
			var balance=document.getElementById("balanceid").value;
			 
			 if(balance==0.0 || balance==0){
			 
			      var ttt=confirm("Do you really want to Discharge this Patient?");
			      if(ttt==true){
			    	if(!flag){
			    		  $('#ipdpopup').modal( "hide" ); 
			    		  $('#initialdischarge').modal( "hide" );  
			    	}
			    	$('#circleloading').modal( "show" );   
			        document.getElementById("enddischargefrm").submit(); 
			      } 
			      
			 }else {
			
			     $('#viewdch').modal( "show" );
			
			 }
			
			 
			
			/*var t = 'formtarget';
				var left = (screen.width / 2) - (700 / 2);
				var top = (screen.height / 2) - (550 / 2);
				var oldwindow = window.open("", t,
						"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
								+ ",top=" + 50);
				
				oldwindow.focus();*/
			  
			 // document.getElementById("enddischargefrm").submit();
			
		
		}
	}
	
	
	function setviewadvoice(){
		
	     /*var url = "getdisadvoiceIpd?treatmentepisode="+ipdtreatmentepisodeid+"";*/
		var url = "getdisadvoiceBookAppointmentAjax?treatmentepisode="+ipdtreatmentepisodeid+"";
		//var contentType = "application/x-www-form-urlencoded; charset=utf-8";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setviewadvoiceRequest;
	    req.open("GET", url, true); 
	    
	    /*req.setRequestHeader('Content-type', contentType);
	    if (req.overrideMimeType) 
	    	req.overrideMimeType(contentType);*/
	    req.send(null);     
	}
	
	
	function setviewadvoiceRequest(){
	
	         if (req.readyState == 4) {
			   if (req.status == 200) {
				   
				   document.getElementById("piddvadv").innerHTML=req.responseText;
				    $('#viedschnadv').modal( "show" );				       
				   
			  } 
			}
	}
	
	
	function setnursingadvoice(tid){
	   $('#baselayout1loaderPopup').modal( "show" );
	     ipdtreatmentepisodeid=tid;
	     
	     doup('dis_nursing_status','dis_nursing_time');
	     window.location.reload();
	     
	}
	
	
	
	
	
	
	
	function submitdischarge() {
	
		  $('#dashboardloaderPopup').modal( "show" );	
		  document.getElementById("endclientip").value = ipdaddmissionid;
			document.getElementById("endclientid").value = ipdclientid;
			document.getElementById("endtreatmentEpisode").value = ipdtreatmentepisodeid;			  
		  
	      document.getElementById("enddischargefrm").submit();
	}
	function openviewAccount() {
	
	    openPopup("Statement?clientId="+ipdclientid+"");
	
	}
	
	function setallStatus(clientId,treatmentEpid){
	
	       var url = "statusInitialDischarge?ipdtreatmentepisodeid="+treatmentEpid+"&clientid="+clientId+"";
	       ipdclientid=clientId;

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	       req.onreadystatechange = setallStatusRequest;
	       req.open("GET", url, true); 
	              
	      req.send(null);
	  
	 }
	var neq_fromdashboard=0;
	function setallStatusRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				var data = req.responseText;
				var temp = data.split('~');
				
				
				
				 idis_form_status = temp[0];
				 idis_form_time = temp[1];
				
				 idis_mdicine_status = temp[2];
				 idis_mdicine_time = temp[3];
				
				
				 idis_bill_status = temp[4];
				 idis_bill_time = temp[5];
				
				
				 idis_nursing_status = temp[6];
				 idis_nursing_time = temp[7];
				 
				 idis_initiate_status = temp[8];
				 
				 //  05-12-2019 responce not waiting so called from directCmpAccountFormNew method to response
				 if(neq_update==1){
					 neq_update=0;
					 if(idis_form_status==0){
				           
			               	jAlert('error', 'Please Complete Discharge Form First!.', 'Error Dialog');
					
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
			           } else if(neq_balance>0){
			             	 $('#viewdch').modal( "show" );
			           } else {
			        	   neq_fromdashboard =1;
			           		updatedischargestatusdb1(neq_tepisodeid,neq_col_one,neq_col_two,neq_di);	
			           }
				 }
				 
			}
		 }
	   }	 		 
	   
	   
	   function directCmpDischargeForm(tepisodeid,c1,c2,di,clientid) {
	   		ipdclientid=clientid;
			setallStatus(clientid,tepisodeid);	   		
	   
	   }
	   
	   function directCmpAccountForm(tepisodeid,c1,c2,di,clientid,balance,addmissionid) {
	          
	           ipdclientid=clientid;
	           ipdpatientid=clientid;
	           ipdtreatmentid=tepisodeid;
	           ipdid= addmissionid;
	           setallStatus(clientid,tepisodeid);
	           
	           if(idis_form_status==0){
	           
	               	jAlert('error', 'Please Complete Discharge Form First!.', 'Error Dialog');
			
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	           } else if(balance>0){
	             	 $('#viewdch').modal( "show" );
	           } else {
	           
	           		updatedischargestatusdb1(tepisodeid,c1,c2,di);	
	           }
	           
	   }
	   var neq_col_one ='';
	   var neq_col_two ='';
	   var neq_tepisodeid=0;
	   var neq_di=0;
	   var neq_update=0;
	   var neq_balance=0;
	   function directCmpAccountFormNew(tepisodeid,c1,c2,di,clientid,balance,addmissionid) {
	          
           ipdclientid=clientid;
           ipdpatientid=clientid;
           ipdtreatmentid=tepisodeid;
           ipdid= addmissionid;
           
           neq_col_one=c1;
           neq_col_two = c2;
           neq_tepisodeid =tepisodeid;
           neq_di = di;
           neq_balance =balance;
           neq_update =1;
           setallStatus(clientid,tepisodeid);
           
          /* if(idis_form_status==0){
           
               	jAlert('error', 'Please Complete Discharge Form First!.', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
           } else if(balance>0){
             	 $('#viewdch').modal( "show" );
           } else {
           
           		updatedischargestatusdb1(tepisodeid,c1,c2,di);	
           }*/
           
   }
	   
	   
	function completeMedicine(){
		var flag = true;
		  $('.akashbclass'+ipdtreatmentepisodeid).each(function() { 
				if(this.checked == false){
				    flag=false;
				} 
										
			});
		  if(idis_initiate_status==0){
				jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else
		  if(flag){
			  var c=window.confirm("Do you want to complete medicine kit?");
			     if(c==true) {
			          
			            updatedischargestatusdb1(ipdtreatmentepisodeid,'dis_mdicine_status','dis_mdicine_time',1); 
			            
			            openIpdPopup('Prescription');
			            
			     } else {
			     
			     
			     }
		  }else{
			  jAlert('error', "Please complete medical checklist!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		  }
	     
	
	}
	
	function completeAccount() {
		
		var flag = true;
		  $('.akashcclass'+ipdtreatmentepisodeid).each(function() { 
				if(this.checked == false){
				    flag=false;
				} 
										
			});
		  
		  if(idis_initiate_status==0){
				jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(flag){
			  var c=window.confirm("Do you want to complete Account Settlemet?");
			     if(c==true) {
			          
			            updatedischargestatusdb1(ipdtreatmentepisodeid,'dis_bill_status','dis_bill_time',1); 
			            redirectToViewAcc();
			            
			     } else {
			     
			     
			     }
		  }else{
			  jAlert('error', "Please complete billing checklist!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		  }
		
		
	    
	
	}
	
	
	function opencancelDischarge(ipdadmitid,treatmentepisodeid) {
		
		ipdtreatmentid=treatmentepisodeid;
		ipdid=ipdadmitid;
		document.getElementById("admitnotes").value='';
		 $('#readmittpopup').modal( "show" );     		
   }
function cancelDischarge() {
		var wardid=document.getElementById("wardname").value;
		var bedid=0;
		if(document.getElementById("bedname")){
		bedid=document.getElementById("bedname").value;
		}
		 var admitnotes=document.getElementById("admitnotes").value;
		
		 if(admitnotes=='' || admitnotes==' '){
			 
			 jAlert('error', "Please enter reason to readmitt this patient!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			 
		 } 
		 
		 else {
			 if(bedid==0 || wardid==0){
				 
				 var confirm=window.confirm("Are you want to readmit patient on same bed!");
				 if(confirm){
					 var url="canceldischargeIpd?ipdid="+ipdid+"&treatmentepisodeid="+ipdtreatmentid+"&admitnotes="+admitnotes+"&wardid="+wardid+"&bedid="+bedid+" ";
					 if (window.XMLHttpRequest) {
							req = new XMLHttpRequest();
					 }
					 else if (window.ActiveXObject) {
							isIE = true;
							req = new ActiveXObject("Microsoft.XMLHTTP");
							
					 }   
					 req.onreadystatechange = cancelDischargeRequest;
					 req.open("GET", url, true); 
					    
					req.send(null);
			
			 }
			 }else{
				 var url="canceldischargeIpd?ipdid="+ipdid+"&treatmentepisodeid="+ipdtreatmentid+"&admitnotes="+admitnotes+"&wardid="+wardid+"&bedid="+bedid+" ";
				 if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				 }
				 else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
				 }   
				 req.onreadystatechange = cancelDischargeRequest;
				 req.open("GET", url, true); 
				    
				req.send(null);
			 }
	         
	        
			               
			
		 }            
               
          
 }
 
 
 function cancelDischargeRequest() {
 
        if (req.readyState == 4) {
         if (req.status == 200) {     
               
 						var r= req.responseText;
 						if(r=="1"){
 						   
 						    window.location.reload();  //updated to same bed reload it
 						      
 						}else if(r=="2"){
 							jAlert('error', 'Patient Already Admitted!!.', 'Error Dialog');
 							
 							setTimeout(function() {
 								$("#popup_container").remove();
 								removeAlertCss();
 							}, alertmsgduration);
 						}
 						
 						else {
 						 	//show popup
 						 	document.getElementById("tpeid").value=ipdtreatmentid;
 						 	document.getElementById("tipdid").value= ipdid;
 						    $('#selbed').modal( "show" );
 						}                   
              }
         }
 }
 function setBedList(wid){
	
	var url = "bedIpd?wid="+wid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBedListRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function setBedListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('bedlistdiv').innerHTML = req.responseText;
		}
	}

}
function gorefereshnew()
{
 document.getElementById('refresh').value="1";
window.location = 'InitialDischarge';
}

function updateDischargeCheckListStatus(val,ische1){
	
	if(idis_initiate_status==0){
		jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
	document.getElementById("discklnameid"+val).checked=false;
	}else{
		var ische = document.getElementById("discklnameid"+val).checked;
		var url = "updatedischargecheckliststatusIpd?disdataid="+val+"&ische="+ische+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateDischargeCheckListStatusRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);
	}
	
}
	   
function updateDischargeCheckListStatusRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
		    var str=data.split("~");
			if(str[1]=='0'){
				document.getElementById('discklnameid'+str[0]).Checked = false;
			}else{
				document.getElementById('discklnameid'+str[0]).Checked = true;
			}
		    
		}
	}

}   
var neq_ipdid=0;
function showInitiateDischargedash(treatementid,clientid,admissionid,patientIdAbrivation,clientname,age,gender,wardname,bedname,practitionername){
	 ipdclientid=clientid;
     ipdpatientid=clientid;
     ipdtreatmentid=treatementid;
     ipdid= admissionid;
     ipdtreatmentepisodeid=treatementid;
     neq_ipdid = admissionid;
     document.getElementById("patientIdAbrivationprint").innerHTML = patientIdAbrivation;
     document.getElementById("clientnameprint").innerHTML = clientname;
     document.getElementById("agegenderprint").innerHTML = age+"/"+gender;
     
     document.getElementById("admissionidprint").innerHTML = admissionid;
     document.getElementById("wardbedprint").innerHTML = wardname+"/"+bedname;
     document.getElementById("practitionerprint").innerHTML = practitionername;
     
	showInitiateDischarge();
}


function callsendsms(){
	var url = "callsendsmsIpdDashboard";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = callsendsmsRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}
	   
function callsendsmsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    
		}
	}

}   

function selectAllChecklist(classname,val,treatmentid){
	
	if(idis_initiate_status==0){
		jAlert('error', 'Please initiate discharge first!!.', 'Error Dialog');
	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
	}else{
		
	
	if (val== true) {
		$('.'+classname+''+treatmentid).each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.'+classname+''+treatmentid).each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
	var modid = '';
	if(classname=='akashbclass'){
		modid='2';
	}else if(classname=='akashcclass'){
		modid='3';
	}else{
		modid='4';
	}
	var url = "updatealldischargechecklistInitialDischarge?treatmentid="+treatmentid+"&modelll="+modid+"&val="+val+"&ipdclientid="+ipdclientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = selectAllDisChecklistRequest;
    req.open("GET", url, true); 
    
    req.send(null);
	}
	
}

function selectAllDisChecklistRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    
		}
	}

}
function getbeds(wardid){
	var url ="getallnotoccupiedbeds1Bed?wardid="+wardid;
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
    req.onreadystatechange = getbedsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getbedsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("bedlist").innerHTML=req.responseText;
		}
	}
	
}
var cliw='';
var ipdii='';
function openvitalbed(cli,ipd){
	cliw=cli;
	ipdii=ipd;
	 $('#vitalmod').modal( "show" );
}

function savipdvitals(){
	saveclinicalnotesvitals(cliw,ipdii);
}
function testendDischarge() {
	$('#ipdpopup').modal( "hide" );  
	$('#initialdischarge').modal( "hide" );  
	$('#circleloading').modal( "show" );   
}
//print method

function printdichargepage(val){
	if(val==1){
		document.getElementById("btnPrint").className ="btn btn-primary btnwidtfixed hidden-print";
		document.getElementById("initialdischargedivforhidden").className ="modal-content";
		document.getElementById("dischargelistprintdiv").className ="print-visible hidden-md hidden-lg hidden-print";
	}else if(val==0){
		document.getElementById("initialdischargedivforhidden").className ="modal-content hidden-print";
		document.getElementById("dischargelistprintdiv").className ="print-visible hidden-md hidden-lg";
	}
	window.print();	
}

function closeIntitalpopup() {
	$('#initialdischarge').modal( "hide" );  
}

function showdischargepoupnew(){
		$('#initialdischarge').modal( "hide" );
		openEmrPopup('dischargeCommonnew?selectedid='+ipdid+'');
		
}
	   