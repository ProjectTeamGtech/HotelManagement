var pbodytmplate;
var pbodyeditedtmplate;
var chkdiartyuserid=0;
var puresevaclientname="";
var warname="YUVICAREAPK";
var opdid=0;
var ipdaddmissionid = 0;
var opdclientid=0;
/*$(document).ready(function() {
	$( "#commencing" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '0',
		 changeMonth: true,
		 changeYear: true	 
	});
	});*/
function showpartapmt(id,val){
	/*$('#loading').modal({
	    backdrop: 'static',
	    keyboard: false  
	})
	$('#loading').modal( "show" );
	rdddval = val;
	
	setCommonAction();
	$('#loading').modal( "hide" );*/
	

var url = "rddBookAppointmentAjax?val="+val+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showpartapmtRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showpartapmtRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setCommonAction();
			/*$('#loading').modal({
			    backdrop: 'static',
			    keyboard: false  
			})
			$('#loading').modal( "show" );
			//rdddval = val;
			
			setCommonAction();
			$('#loading').modal( "hide" );*/
		}
	}
	
}


function getDaySearch(){
	
	//set wraperdiv element
	 if(editAppointId!=0){
			getWraperDivChildren(wraperDivId);
	 }
	
	jQuery('.new').children().unwrap();
	
	 		actionType =  2;
			var date = document.getElementById("commencing").value;
			var id = document.getElementById('diaryUsersss').value;
			var locationid = document.getElementById('locationid').value;
			var count = 0;
		/*	for(i=1;i<=13;i++){
				$(document.getElementById(i)).css('border-bottom', '1px solid #DFD8D4');
				document.getElementById(i).ondblclick = '';
				for(j=0;j<=11;j++){
					count = 5*j;
					$(document.getElementById(count+'min'+i)).css('background-color', 'white');
					$(document.getElementById(count+'min'+i)).css('border-bottom', '');
					//$(document.getElementById(count+'min'+i)).css('border-bottom-style', '');
					document.getElementById(count+'min'+i).innerHTML = '';
					count = parseInt(count)+1;
				}
				
				
				//document.getElementById(i).innerHTML = "";
			}
			*/
				var tempDate = date.split("/");
				/*var mm = tempDate[1];
				var yy = tempDate[2];
				var dd = parseInt(tempDate[0]) + j;
				//date = dd + "/" + mm + "/" + yy;
				date = getCalDate(dd,mm,yy)*/
				
				date = tempDate[0] + "-" + tempDate[1] +  "-" + tempDate[2];
				
				setDayJsoData(id,date,locationid);
			
			
			
}


function setDayJsoData(diaryUserId,date,locationid){

	$.ajax({
		url: "diarymanagement/pages/JQGridMasterNotAvailableSlot.jsp?diaryUserId="+diaryUserId+"&date="+date+"&locationid="+locationid+" " ,
		dataType : "json",
		success : function(json) {
			//alert("Your JSON : " + JSON.stringify(json));
			
			var data = JSON.parse(JSON.stringify(json));        
                     $.each(data,function(row,store)  {    
                        $.each(store,function(key,value) {
                            //id = value.id;
                            //name = value.name;
                           // address = value.address;
                            //age = value.age;
                           // alert(id + " : " + name + "  : " +address + " : " + age); 
                          	
                          	
                          	
                          globalEndTime = value.endtime;
                          	
                          
                          		
                         		var col = 1;
                          		//start time diff
                          		var stdiff = parseInt(value.starttime) - value.clinicstime;
                          		stdiff = (parseInt(stdiff) * 1) + parseInt(col);
                          		var tempStdiff = stdiff;
                          		
                          		//end time diff
                          		var etdiff = parseInt(value.endtime) - value.clinicstime;
                          		etdiff = (parseInt(etdiff) * 1) + parseInt(col);
                          		
                          		var i=0;
                          		var j=0;
                          		
                          		var stemp = value.starttime.split(":");
                          		var etemp = value.endtime.split(":");
                          		var slength = parseInt(stemp[1])/5;
                          		var elength = parseInt(etemp[1])/5;
                          		
                          		var action = "edit";
                          		
                          		
                          		var timediff = getTimeDifference(value.starttime,value.endtime);
                          		var diffemp = timediff.split(":");
                          		var hour = parseInt(diffemp[0])*60;
                          		timediff = hour + parseInt(diffemp[1]);
                          		var divlength = timediff/5;
                          		
                          		var size = 1;
                          		
                          		//$('#1').attr('colspan', 3);
								for (i=parseInt(stdiff);i<parseInt(etdiff);i=(i+size)){
									//$(document.getElementById(i)).css('border-bottom', '0px');
									$(document.getElementById(i)).css('font-size', '12px');
									document.getElementById(i).className = 'cssClass';
									document.getElementById(i).title = value.id+"/"+diaryUserId;
									document.getElementById(i).onclick = "";
									//document.getElementById(i).ondblclick = function() {
									//	$(this).MessageBox(value.weekfullname,0,action,value.id,stdiff,value.starttime,value.endtime,value.apmtduration,value.location,value.onlinebooking,time);
									//}
									
								}
								
								
									var jcount = 0;
									var divtime = 0;
									
									divlength = divlength - 1;
									
									for(j=0;j<=divlength;j++){
										jcount = 5*parseInt(slength);
										
										document.getElementById(jcount+'min'+stdiff).className = 'cssClass1';
										//aqua
										
										//$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#fcf8e3');
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', value.color);
										
											$(document.getElementById('30min'+stdiff)).css('background-image', 'url("diarymanagement/img/line.png")');
											$(document.getElementById('30min'+stdiff)).css('background-repeat', 'repeat-x');
										
											$(document.getElementById(jcount+'min'+stdiff)).css('background-color', value.color);
											
											newTitle = "";
											mytitle = "";
											var tmtitle = document.getElementById(jcount+'min'+stdiff).title;
											tmtitle = tmtitle.split(' ');
											if(tmtitle.length > 1){
												document.getElementById(jcount+'min'+stdiff).title = tmtitle[0];
											}
											
											 mytitle = document.getElementById(jcount+'min'+stdiff).title;
											 newTitle = mytitle + ' ' + '('+value.locationName+')';
											document.getElementById(jcount+'min'+stdiff).title = newTitle;
											
											
											$('#'+jcount+'min'+stdiff).click(function(){
											      //Some code
												$(this).MessageBox(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,this.title);
												
												document.getElementById('diciplineName').value = value.discipline;
												isnewopd = 0;
												$( "#appointment" ).modal( "show" );
												clearFiledCommonAction();
											
												
												document.getElementById('client').value = "";
												document.getElementById('notes').value = "";
												
												document.getElementById('apmtType').value = 0;
												$("#apmtType").trigger("chosen:updated");
												$(".chosen").chosen({allow_single_deselect: true});
												
												document.getElementById('condition').value = value.discipline;
												$("#condition").trigger("chosen:updated");
												$(".chosen").chosen({allow_single_deselect: true});
												
												var tstr = '<select name="treatmentEpisode" id="treatmentEpisode" class="form-control showToolTip chosen"><option value="0">Select Treatment Episode</option></select>'
												document.getElementById('treatmentepisodeajax').innerHTML = tstr;
												$("#treatmentEpisode").trigger("chosen:updated");
												$(".chosen").chosen({allow_single_deselect: true});
												
												
												//$('#appointment').dialog('option', 'title', 'New Appointment');
												editAppointId = 0;
												editCommencing = value.commencing;
												slotstarttime = value.starttime;
												document.getElementById('date').value = value.commencing;
												document.getElementById('blockdate').value = value.commencing;
											
											//set cookie commencing
											document.cookie = "cookiecommencing=" + commencing;
											document.cookie = "cookiePractitionerId=" + diaryUserId;
											 });
											
										/*	document.getElementById(jcount+'min'+stdiff).onclick = function() {
												alert('hello')
														$(this).MessageBox(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,this.title);
														
														document.getElementById('diciplineName').value = value.discipline;
														
														$( "#appointment" ).modal( "show" );
														clearFiledCommonAction();
													
														
														document.getElementById('client').value = "";
														document.getElementById('notes').value = "";
														
														document.getElementById('apmtType').value = 0;
														$("#apmtType").trigger("chosen:updated");
														$(".chosen").chosen({allow_single_deselect: true});
														
														document.getElementById('condition').value = 0;
														$("#condition").trigger("chosen:updated");
														$(".chosen").chosen({allow_single_deselect: true});
														
														var tstr = '<select name="treatmentEpisode" id="treatmentEpisode" class="form-control showToolTip chosen"><option value="0">Select Treatment Episode</option></select>'
														document.getElementById('treatmentepisodeajax').innerHTML = tstr;
														$("#treatmentEpisode").trigger("chosen:updated");
														$(".chosen").chosen({allow_single_deselect: true});
														
														
														//$('#appointment').dialog('option', 'title', 'New Appointment');
														editAppointId = 0;
														editCommencing = value.commencing;
														slotstarttime = value.starttime;
														document.getElementById('date').value = value.commencing;
														document.getElementById('blockdate').value = value.commencing;
													
													//set cookie commencing
													document.cookie = "cookiecommencing=" + commencing;
													document.cookie = "cookiePractitionerId=" + diaryUserId;
													
												
											
										}*/
									/*	document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
										}*/
											
											$('#'+jcount+'min'+stdiff).click(function(){
												setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
											});
										slength = parseInt(slength) + 1;
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + 1;
											
										}
										
										
									}
									
								setDayUserjsonAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,value.practitionerid,1,value.commencing,value.clinicstime);
								
								
							
							
							
							
                        });        
                     });
			
		}
	});
			
			
}	



function setDayUserjsonAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,size,commencing,clinicstime){

	

	$.ajax({
		url: "diarymanagement/pages/JQGridMasterAvailableData.jsp?diaryuserid="+diaryuserid+"&practitionerid="+practitionerid+"&rdddval="+rdddval+" " ,
		dataType : "json",
		success : function(json) {
			//alert("Your JSON : " + JSON.stringify(json));
			
			
			
			  var data = JSON.parse(JSON.stringify(json));        
                     $.each(data,function(row,store)  {    
                        $.each(store,function(key,value) {
                            //id = value.id;
                            //name = value.name;
                           // address = value.address;
                            //age = value.age;
                           // alert(id + " : " + name + "  : " +address + " : " + age); 
                          	
                          
                          		var stdiff = parseInt(value.starttime) - clinicstime;
                          		stdiff = (parseInt(stdiff) * size) + parseInt(col);
                          		var tempStdiff = stdiff;
                          		
                          		
                          
                          //set appointment type color
                          		var stemp = value.starttime.split(":");
                          		var etemp = value.endtime.split(":");
                          		var slength = parseInt(stemp[1])/5;
                          		var elength = parseInt(etemp[1])/5;
                          		
                          		var timediff = getTimeDifference(value.starttime,value.endtime);
                          		var diffemp = timediff.split(":");
                          		var hour = parseInt(diffemp[0])*60;
                          		timediff = hour + parseInt(diffemp[1]);
                          		var divlength = timediff/5;
                          		
                          		var stdiff = tempStdiff;
                          			
                          		var tdhtmldata = "";
                          		
                          		var max=10;
                          		var min=4;
                          		
                          		var random = value.id;
                          		random = random + 's';
                          		
                          		var style = document.createElement('style');
                          		style.type = 'text/css';
                          		style.innerHTML = '.cssClass'+random+' { min-height:20px; min-width:99px; cursor:pointer; }';
                          		document.getElementsByTagName('head')[0].appendChild(style);
                          		
                          		var jcount = 0;
									
									divlength = divlength - 1;
									
									for(j=0;j<=divlength;j++){
										jcount = 5*parseInt(slength);
										
										document.getElementById(jcount+'min'+stdiff).className = 'cssClass'+random;
										if(jcount==30){
											$(document.getElementById('30min'+stdiff)).css('background-image', '');
										}
										
										
										
										if(value.status == 0){
											if(value.iscompleted == true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#81AB6D');
												
											
											}else if(value.dna==true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#F96467');
												
											}
											else if(value.otid>0){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(173, 220, 255)');
												
											}
											
											else{
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#FCBA63');
											}
											
											/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
												wraperDivId = 'new'+random;
												setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
												
											}*/
										}else if(value.status == 1){
											$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
											/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
												wraperDivId = 'new'+random;
												setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
												
											}*/
											
										}else{
											document.getElementById(jcount+'min'+stdiff).onclick = function() {
												setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
												 
											}
										}
										
										
										  if(value.chargecompleted==true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#cccccc');
											
											}
									
										
										slength = parseInt(slength) + 1;
										
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + size;
											tdhtmldata = document.getElementById(stdiff).innerHTML;
										}
										
										
									}
									
									
									//wrap all
									var zp = '<div style="width:100px;height:100px;position:absolute;background-color:yellow;top:20px;left:20px;z-index:0;opacity:0.5;border:1px solid #333333;" onclick="zproperty()">z-index 0</div>'
									 $( ".cssClass"+random ).wrapAll( "<div id='new"+random+"'  class='new' ></div>");
									 var resizewidth = $('#thtestid').width();
									 var resultwidth = parseInt(resizewidth)+6;
									// alert(resultwidth);
									// resultwidth = resultwidth/4;
									$(document.getElementById('new'+random)).css('width', resultwidth);
									 if(tdhtmldata!=""){
										//alert(tdhtmldata)
										 //changeWrapedDivId('new'+random);
										 document.getElementById(stdiff).innerHTML = tdhtmldata; 
										 initilizeTdElement(stdiff,diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
										 
										 if(stdiff!=6){
											// $(document.getElementById('new'+random)).css('position', 'absolute');
										 }
										
										 //$(document.getElementById('new'+random)).css('width', '95%');
										 
										 
										 //reinitilize wraped element
										 renitilizeWrapdedEvent(stdiff,'new'+random);
										 
									 }
									 
									 
									
									// $( "#new"+random ).resizable();
									/* document.getElementById('new'+random).onclick = function() {

										 $( "#appointment" ).modal( "hide" );
											wraperDivId = 'new'+random;
											setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay);
											
										}*/
									
									 //modify popup
									 if(value.iscompleted == false){
										
										 document.getElementById('new'+random).onclick = function() {

											 $( "#appointment" ).modal( "hide" );
												wraperDivId = 'new'+random;
												 loc = value.locid;
												 
												 if(value.otid==0){
													setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname,value.imagename,value.height,value.weight,value.bmi,value.pulse,value.sysbp,value.diabp,value.planed,value.procedure,value.surgeon,value.anesthesia,value.ipdno,value.wardid,value.asisdoclist,value.sugarfasting,value.postmeal,value.temprature,value.spo,value.bsa);
												 }else{
												 	
												 	showotmodifypopuop(value.id,value.clientId,value.clientname,value.location,value.whopay,value.imagename,value.height,value.weight,value.bmi,value.pulse,value.sysbp,value.diabp,value.temprature,value.spo,value.bsa);
												 }
											}
									 }
									 else if(value.iscompleted == true){
										 wraperDivId = 'new'+random;
										

										 patientId = value.clientId;
										 editClientName = value.clientname;
										 editwhopay = value.whopay;
										 editTreatmentEpisode = value.treatmentepisodeid;
										
										 document.getElementById('new'+random).onclick = function() {
											 wraperDivId = 'new'+random;

											 $( "#appointment" ).modal( "hide" );	
											 
											 if(value.chargecompleted==false){
												 wraperDivId = 'new'+random;
												 
												 //editCompleteApmt(value.id);
												 patientId = value.clientId;
												 pppid = value.clientId;
												 pppcname  = value.clientname;
												 pppwhopay  = value.whopay;
												 ppptepisode  = value.treatmentepisodeid;
												 
												 editAppointId = value.id;
												 editwhopay = value.whopay; //
												 editClientName = value.clientname;
												 loc = value.locid;
												 diaryuserId = userid;
												 editcondition_id = value.conditionid;
												 editTreatmentEpisode = value.treatmentepisodeid;
												 openCompleteActionPopup(value.id,height,weight,bmi,pulse,sysbp,diabp,temprature,spo,bsa);
											 }else{
												 
												
												  wraperDivId = 'new'+random;
												 
												 patientId = value.clientId;
												 pppid = value.clientId;
												 pppcname  = value.clientname;
												 pppwhopay  = value.whopay;
												 ppptepisode  = value.treatmentepisodeid;
												 
												 editAppointId = value.id;
												 editwhopay = value.whopay;
												 editClientName = value.clientname;
												 loc = value.locid;
												 diaryuserId = userid;
												 editcondition_id = value.conditionid;
												 editTreatmentEpisode = value.treatmentepisodeid;
													//document.getElementById('modifyClient2').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href = "ClientLog?id='+patientId+'" target="blank"> Log </a>)';
												 document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
													
													
												 //jAlert('info', 'Invoice has been created for this appointment', 'Info Dialog');
												 $( "#recordpaymentpopup" ).modal( "show" );
											 }
											 
										
										 }
									 }

									 else{

										 document.getElementById('new'+random).onclick = function() {
											 
											 $( "#appointment" ).modal( "hide" );
											 
										 }
									 }
									
                          			
									
                          
                       		
                          	//write text 
								var stext = parseInt(stemp[1]);
                          		var etext = parseInt(etemp[1]);
                          		
                          		
                          		
                          		var showtext =  "";
                          		var otconfirmed = "";
                          		
                          		
								if(value.otid>0){
									value.clientname = value.clientname + ' ' + '(OT in '+value.otname+') '
								}
								if(value.otmsg==1){
									otconfirmed = "<span title='OT Confirmed By "+value.otaccname+"' style='float:left;padding-left: 5px;margin-top: 0px;'>OT</span>";
								}
                          		
                          		if(value.whopay=='Client'){
                          			if(value.notes!=""){
                          				if(value.bal>0){
                          					showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<span class='selbal' title='Balance="+currencySign+value.bal+"'>"+currencySign+"</span><br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" "> ';
                          				}else{
                          					showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")'style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" ">';
                          				}
                          				
                          			}else{
                          				if(value.bal>0){
                          					showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<span class='selbal' title='Balance="+currencySign+value.bal+"'>"+currencySign+"</span><br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
                          				}else{
                          					showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
                          				}
                          				
                          			}
                          			
                          		}else{
                          			if(value.notes!=""){
                          				showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<br> " +  " "+value.usedsession+" " + ' '+ " "+value.apmttypetext+"" + '<img src="common/images/note.ico" title="'+value.notes+'" ">';
                          			}else{
                          				showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-heartbeat' aria-hidden='true'></i> ("+value.token+")</span> &nbsp; <span title='Request Prescription' onclick='showopdpriscription()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-medkit' aria-hidden='true'></i></span> &nbsp; <span title='Request Investigation' onclick='showopdInvestigation()' style='float:left;padding-left: 5px;margin-top: 0px;'><i class='fa fa-info-circle' aria-hidden='true'></i></span>" +otconfirmed + value.clientname + "<br> " +  " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br> ";
                          			}
                          		}
                          		
                          		
                          		if(value.isreportsent==1){
                          			showtext = "<img src='common/images/r-icon.png' style='width:13px;' title='Report Sent'>" + " " + showtext;
                          		}
                          	
                          			
                          			//if no notes
                          			if(value.status == 0){
    									//document.getElementById(etext+'min'+tempStdiff).innerHTML = '<img src="src="common/images/nIcon.png">';
    									if(value.arrivedstatus == 1){
    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:#398C10">' +  showtext  +  '</div>';
    									}else if(value.arrivedstatus == 2){
    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:#FFFFFF">' +  showtext  +  '</div>';
    									}else{
    										
    										if(value.firstapmt==1){
    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black"> <img style="width:12px; height:11px; padding-right: 2px" src="common/images/nIcon.png">' +  showtext  +  '</div>';
    										}else{
    										
    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext  +  '</div>';
    										}
    										
    									}
    									
    								}else{
    									if(value.notes!=""){
    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + value.reasonforblock + ","+value.starttime+"-" + ""+value.endtime+" " + '<img align="" src="common/images/note.ico" title="'+value.notes+'" ">'+ '</div>';
    									}else{
    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + value.reasonforblock + ","+value.starttime+"-" + ""+value.endtime+" " +  '</div>';
    									}
    									
    									//document.getElementById(stext+'min'+tempStdiff).title = value.notes + "," + "["+value.apmttype+"]";
    								}
    								
    								if(value.status==0 && value.dna==true){
    									showtext = "<span title='EMR' onclick='redircttoNewEmr("+value.clientId+","+userid+","+value.conditionid+")'>(E)</span> " + value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"";
    									
    									//document.getElementById(stext+'min'+tempStdiff).style.color = "Red";
    									if(value.dnanotes!=""){
    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext + '<img  src="common/images/note.ico" title="'+value.dnanotes+'" ">' + '</div>';
    									}else{
    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext +  '</div>';
    									}
    									
    								}
                          		
		                          		
										
								
                        
                          	
                      });        
                     });
			
		}
	});
}




//new opd script
function showpopupfornewopd(){
	
	
	
document.getElementById('apmtwithz').innerHTML = $("#diaryUsersss option:selected").text();
	
	var ndate = document.getElementById('commencing').value;
	var nduserid = document.getElementById('diaryUsersss').value;
	if(nduserid=="0" || nduserid=="" || nduserid=="1"){
		jAlert('error', 'Please Select Doctor.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		
	
	var url = "newopdBookAppointmentAjax?ndate="+ndate+"&nduserid="+nduserid+" ";
	if(actionType==5){
		
	}
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showpopupfornewopdRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
	
}

function showpopupfornewopdRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('puresevaclntname').innerHTML="";
			openedb = 'opd';
			isnewopd = 1;
			
			
			
			var data = req.responseText;
			var tmp = data.split('~');
			
			var slotid = tmp[0];
			var stime = tmp[1];
			var etime = tmp[2];
			var duration = tmp[3];
			
			
			var location = tmp[4];
			var diaryUser = tmp[5];
			
			var title = tmp[7];
			if(actionType==5){
				stime=starttimess1;
//				etime=endtimess1;
//				duration=durationss1;
				title=starttimess1;
			}
			if(actionType==5){
				$( "#checkaval" ).modal( "hide" );
			}
			var commencing = tmp[8];
			var diaryUserId = tmp[9];
			chkdiartyuserid=tmp[10];
			 if(chkdiartyuserid==1){
					jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
				
				document.getElementById('setdiarydiv').style.display='inline';
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				 
			/*	var box=bootalert('error',"Please set Doctor Diary", "Error Dialog");
				  setTimeout(function() {
					    box.modal('hide');
					}, 3000);*/
				
				 
			 }else{
//			$(this).MessageBox(slotid,stime,etime,duration,location,diaryUser,diaryUserId,title);
               //  document.getElementById('setdiarydiv').style.display='none';
				 opdnewfun(slotid,stime,etime,duration,location,diaryUser,diaryUserId,title);
				 
			document.getElementById('diciplineName').value = location;
			$( "#appointment" ).modal( "show" );
			 }
			clearFiledCommonAction();

			
			document.getElementById('client').value = "";
			document.getElementById('notes').value = "";
			
			document.getElementById('apmtType').value = 0;
			document.getElementById('apmtType').className="";
			if(actionType==5){
//				$( "#checkaval" ).modal( "hide" );
//				setTimeout(() => {  setClientName(puresevaclientid,'0','','Client',''); }, 2000);
//				setClientName(puresevaclientid,'0','','Client','');
				 dbname = puresevaclientname;
				 dbid =puresevaclientid ;
				 dbtype = '0';
				 dbtypename = '';
				 dbpayby = 'Client';
				 dbgpid = '';
				 document.getElementById("clientId").value = puresevaclientid;
					//set cookie data
					
					document.cookie = "cookieClientId=" + puresevaclientid;
					document.getElementById('clientId').value = puresevaclientid;
				 document.getElementById('client').value = puresevaclientname;
//				setAppointmentTypeTimeAjax(appointmenttypeid);
//				document.getElementById('client').value=puresevaclientname;
				setTimeout(() => {  setAppointmentTypeTimeAjax(appointmenttypeid); }, 1000);
				document.getElementById('apmtType').value=appointmenttypeid;
//				slotstarttime = starttimess1;
			}
//			$("#apmtType").trigger("chosen:updated");
//			$(".chosen").chosen({allow_single_deselect: true});
//			
			document.getElementById('condition').value = location;
//			$("#condition").trigger("chosen:updated");
//			$(".chosen").chosen({allow_single_deselect: true});
			
			var tstr = '<select name="treatmentEpisode" id="treatmentEpisode" class="form-control showToolTip chosen"><option value="0">Select Treatment Episode</option></select>'
			document.getElementById('treatmentepisodeajax').innerHTML = tstr;
//			$("#treatmentEpisode").trigger("chosen:updated");
//			$(".chosen").chosen({allow_single_deselect: true});
			slotstarttime = stime;
//			if(actionType==5){
//				setClientName(puresevaclientid,'0','','Client','');
////				document.getElementById('client').value=puresevaclientname;
//				setAppointmentTypeTimeAjax('982');
////				slotstarttime = starttimess1;
//			}
			
			//$('#appointment').dialog('option', 'title', 'New Appointment');
			editAppointId = 0;
			editCommencing = commencing;
			
			
			document.getElementById('date').value = commencing;
			document.getElementById('blockdate').value = commencing;

		//set cookie commencing
		document.cookie = "cookiecommencing=" + commencing;
		document.cookie = "cookiePractitionerId=" + diaryUserId;
		if(actionType==5){
		document.getElementById('puresevaclntname').innerHTML = puresevaclientname;
		document.getElementById("client").className="hidden hidden-xs";
		document.getElementById("client").style.visibility="hidden";
		document.getElementById("client").style.display="none";
		$('#client').toggleClass('hidden');
		}else{
			
			document.getElementById("client").value="";
			document.getElementById('puresevaclntname').className="";
		}
			}
	}
	
	}



//new opd display
/*if(actionType!=5){
	 $('#example').DataTable().destroy();
	}*/
function showdisplaynewopd(sts){
	if(sts==undefined){
		sts="";
	}
	var ndate = document.getElementById('commencing').value;
	var nduserid = document.getElementById('diaryUsersss').value;
	
	var url = "newdisplayBookAppointmentAjax?ndate="+ndate+"&nduserid="+nduserid+"&opdstatus="+sts+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showdisplaynewopdRequest;
    req.open("GET", url, true); 
              
    req.send(null);

	
}

function showdisplaynewopdRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 var data=req.responseText.split("~~~");
			 var data=req.responseText.split("~~~");
			 if(document.getElementById('idenot')){
				 data[0]=data[0].replace(/shownewdto/g,"shownewdto1");	 
			 }
			document.getElementById('newopdbodyid').innerHTML = data[0];
			document.getElementById('slotId').value=data[1];
			if(document.getElementById('undoneappt')){
				document.getElementById('undoneappt').innerHTML=data[2];
			}
			
			/*if(data[3]==''||data[3]==3){
			document.getElementById('orderby').value=data[3];
			}*/
			if(actionType!=5){
				bootstrapplugin();
				}
			
			
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
		
}

function goforvdyocall(){
	openDisplayPopup('vdyoBookAppointmentAjax');
}



function shownewdto(apmtid,pid,conid,clientid,indx,popstatus,bodytmplate,bodyeditedtmplate,drcomplete,patientseen){
	pbodytmplate = bodytmplate;	
	pbodyeditedtmplate = bodyeditedtmplate;
	
//	var opdmodifylog=document.getElementById('opdmodifylogin').value;
//	if(opdmodifylog!='true'){
		document.getElementById('opdmoddivid').style.display = 'none';
//	}
	 document.getElementById('emrapmtId').value=apmtid;
	 patientId = clientid;
	 pppid = clientid;
	 pppcname  = document.getElementById('ncname'+indx).value;
	 pppwhopay  = document.getElementById('nwhopay'+indx).value;
	 ppptepisode  = 0;
	 opdclientid=clientid;
	 editAppointId = apmtid;
	 opdid=apmtid;
	 editwhopay = document.getElementById('nwhopay'+indx).value;;
	 editClientName = document.getElementById('ncname'+indx).value;
	 loc = 1;
	 diaryuserId = pid;
	 editcondition_id = conid;
	 editTreatmentEpisode = 0
		
	 document.cookie = "cookiecommencing=" + document.getElementById('commencing').value;
	 document.cookie = "cookiePractitionerId=" + pid;
	 
	 document.cookie = "cookieClientId=" + pppid;
	 document.cookie = "cookieUserName=" + editClientName;
	 document.cookie = "cookieSelectedAppointmentid=" + editAppointId;
	 document.cookie = "cookieTreatmentEpidodeSessions=" + 0;
	 document.cookie = "complocationid=" + loc;
	 document.cookie = "cookiePractitionerId=" + pid;
//	 var nn=document.getElementById('didnotattendNotes').value;
//		if(nn=='null'){
//			document.getElementById('didnotattendNotes').value="";
//		}
	 document.getElementById('clientisbeingseentxt').style.color='black';
	 document.getElementById('finalcompletetxt').style.color='black';
	 document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Forms</p></a></div>';
	if(popstatus==1){
		
		//document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Forms</p></a></div>';
		document.getElementById('clientarrived123').style.display='';
		document.getElementById('clientisbeingseentxt').style.color='black';
		document.getElementById('clientseen111').style.pointerEvents = 'none';
		document.getElementById('finalcompletetxt').style.color='black';
		document.getElementById('completeapmt111').style.pointerEvents = 'none';
		document.getElementById('completeappointment').style.display='';
		document.getElementById('clientdidnotcome123').style.display='';
		document.getElementById('cancelappointment').style.display='';
		document.getElementById('resettocomplete').style.display='none';
		document.getElementById('finalcomplete').style.display='';
		document.getElementById('clientdidnotcome').style.display='none';
		document.getElementById('accountsopt').style.display='';
		document.getElementById('addotequip').style.display='none';
		document.getElementById('listotequip').style.display='none';
		document.getElementById('otnot').style.display='none';
		document.getElementById('clientisbeingseen123').style.display='';
		 document.getElementById('clientseen111').style.pointerEvents = 'none';
		 document.getElementById('completeapmt111').style.pointerEvents = 'none';

		$( "#modifyPopup" ).modal( "show" );
	}
  if(popstatus==2){
		 	
	  //document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Forms</p></a></div>';
//		 $( "#completeActionPopup" ).modal( "show" );
		 document.getElementById('clientarrived123').style.display='none';
		 document.getElementById('completeapmt111').style.pointerEvents = 'none';
		 document.getElementById('clientseen111').style.pointerEvents = 'none';
		 if(patientseen==0){
			 document.getElementById('clientseen111').style.pointerEvents = 'auto';
		 }else if(patientseen==1){
			 document.getElementById('clientisbeingseentxt').style.color='brown';
			 document.getElementById('clientseen111').style.pointerEvents = 'none';
			 document.getElementById('completeapmt111').style.pointerEvents = 'auto';
		 }
		  if(drcomplete==1){
			  document.getElementById('finalcompletetxt').style.color='brown';
			  document.getElementById('completeapmt111').style.pointerEvents = 'none';
		 }
		 document.getElementById('completeappointment').style.display='none';
		 document.getElementById('cancelappointment').style.display='none';
		 document.getElementById('resettocomplete').style.display='';
		 document.getElementById('finalcomplete').style.display='';
		 document.getElementById('clientdidnotcome123').style.display='none';
		 document.getElementById('accountsopt').style.display='';
		 document.getElementById('addotequip').style.display='none';
		 document.getElementById('listotequip').style.display='none';
		 document.getElementById('otnot').style.display='none';
		 $( "#modifyPopup" ).modal( "show" );
	}
  if(popstatus==3){
	  //document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;font-weight: 700;">Forms</p></a></div>';
//		 $( "#recordpaymentpopup" ).modal( "show" );
		 document.getElementById('clientarrived123').style.display='none';
		 document.getElementById('completeapmt111').style.pointerEvents = 'none';
		 document.getElementById('clientseen111').style.pointerEvents = 'none';
		 if(patientseen==0){
			 document.getElementById('clientseen111').style.pointerEvents = 'auto';
		 }else if(patientseen==1){
			 document.getElementById('clientisbeingseentxt').style.color='brown';
			 document.getElementById('clientseen111').style.pointerEvents = 'none';
			 document.getElementById('completeapmt111').style.pointerEvents = 'auto';
		 }
		  if(drcomplete==1){
			  document.getElementById('finalcompletetxt').style.color='brown';
			  document.getElementById('completeapmt111').style.pointerEvents = 'none';
		 }
		 document.getElementById('completeappointment').style.display='none';
		 document.getElementById('cancelappointment').style.display='none';
		 document.getElementById('resettocomplete').style.display='none';
//		 document.getElementById('finalcomplete').style.display='';
		 document.getElementById('clientdidnotcome123').style.display='none';
		 document.getElementById('accountsopt').style.display='';
		 document.getElementById('addotequip').style.display='none';
		 document.getElementById('listotequip').style.display='none';
		 document.getElementById('otnot').style.display='none';
		 
		 $( "#modifyPopup" ).modal( "show" );
	}
  if(popstatus==4){
		 //document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
		 document.getElementById('clientarrived123').style.display='none';
		 document.getElementById('clientisbeingseen123').style.display='none';
		 document.getElementById('completeappointment').style.display='none';
		 document.getElementById('cancelappointment').style.display='none';
		 document.getElementById('resettocomplete').style.display='none';
		 document.getElementById('finalcomplete').style.display='none';
		 document.getElementById('clientdidnotcome123').style.display='';
		 document.getElementById('accountsopt').style.display='none';
		 document.getElementById('addotequip').style.display='none';
		 document.getElementById('listotequip').style.display='none';
		 document.getElementById('otnot').style.display='none';
		 $('#modifyPopup').modal( "show" );
	}
	
  if(popstatus==5){
		// document.getElementById('puremodifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
		 $('#puremodifyPopup').modal( "show" );
	}
  if(popstatus==6){
		// document.getElementById('puremodifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
		document.getElementById("deleteapmtiddiv123").className="hidden";
		document.getElementById("qrcodediv").className="hidden";
		$('#puremodifyPopup').modal( "show" );
	}
  
  	setHISBMIData(clientid,indx);  
	
}

	
function opentoipd(){
		//  26-03-2020
	   /* var cheeckcomplete = $('#completeapmt').is(':disabled');
		if(cheeckcomplete){
			checkPatientadmittedNew(patientId);
		}else{
			jAlert('error', 'Please complete appointment', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}*/
		//  27-03-2020 first need to create charge 
		checkPatientchargesCreated(editAppointId);
		//checkPatientadmittedNew(patientId);
}

function checkPatientchargesCreated(editAppointId){
	var url = "checkpatientchargescreatedBookAppointmentAjax?apmtid="+editAppointId+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkPatientchargesCreatedRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	
}



function checkPatientchargesCreatedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var chargecreated = req.responseText;
			if(chargecreated==1){
				checkPatientadmittedNew(patientId);
			}else{
				jAlert('error', 'Please complete appointment first', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
	}
}
	

function checkPatientadmittedNew(clientid){
	
	//var url = "admittedIpd?clientid="+clientid+"";
	var url = "admittedIpdAjax?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkPatientadmittedNewRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	
}


function checkPatientadmittedNewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var bedid = req.responseText;
			if(bedid==0){
				 document.getElementById("toipd").style.display = "block";
			}else{
				jAlert('error', 'Patient Already admitted.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
	}
}
	
/*function checksetdiaries(commencing,diaryuserid){
var url = "checksetdiaryBookAppointmentAjax?commencing="+commencing+"&diaryuserid="+diaryuserid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checksetdiariesRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
}
function checksetdiariesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			chkdiartyuserid = req.responseText;
		}
	}
}*/
var mobusermobile="";

function confirmopdotp(){
	$( "#confirmuhid" ).modal( "hide" );
	$( "#preloader" ).modal( "show" );
	var mob=document.getElementById('entermob').value;
	mobusermobile=mob;
	if(email==''){
		email =   document.getElementById('oriemail').value;
	}
	var url = "otpClient?email="+email+"&mob="+mob+"&fname="+puresavefname+"&lname="+puresavelname+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = confirmOtpAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
function confirmOtpAjaxRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			otp = req.responseText;
			$( "#confirmuhid" ).modal( "hide" );
			$( "#preloader" ).modal( "hide" );
			$( "#atppopup2" ).modal( "show" );
			document.getElementById("timer2").innerHTML="5:00";
			countdown2(5);
		}
		}
}
var statu=0;
function otpConfirmedmob(){
	var enteredotp=document.getElementById('otptxt2').value;
	otp="775266";
	if(enteredotp==""){
jAlert('error', 'Please Enter OTP!', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	if(enteredotp==otp){
		$( "#atppopup2" ).modal( "hide" );
		listofregpatient();
	
	}else{
jAlert('error', 'OTP did not matched!', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}	
	}
}
function firstconfirmotp(){
	var entermob = document.getElementById("entermob").value;
	var initialdob = document.getElementById("initialdob").value;
	var age = document.getElementById("pureage").value;
	
	document.getElementById("puredob").value = initialdob;
	document.getElementById("puremob").value = entermob;
	$( "#confirmuhid" ).modal( "hide" );
	var todayDate = new Date().getDate();
	 $("#puredob").datepicker({
			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true,
			maxDate: new Date(new Date().setDate(todayDate))

		});
	 if(age<=125){
		 if(document.getElementById("hiddenpuresevareg")){
				document.getElementById("hiddenpuresevareg").className='';
		 }
	 }else{
		 if(document.getElementById("hiddenpuresevareg")){
				document.getElementById("hiddenpuresevareg").className='hidden';
		 }
	 }
	$( "#puresevaclientdetailsdiv" ).modal( "show" );
	getAgendDays2(initialdob);
//	$( "#addPatientDiv" ).modal( "show" );
	
}



function isValidMobileNo(mobno){
  var phoneno = /^\d{10}$/;
  if((mobno.match(phoneno))) {
      return true;
     }
    else{
        return false;
    }
}



function confirm1(){
	
		
	var title=document.getElementById('title123').value;
	var email = document.getElementById('pureemail').value;
	var fname =  document.getElementById('purefname').value;
	var lname = document.getElementById('purelname').value;
	var mob = document.getElementById('puremob').value;
	var dob = document.getElementById('puredob').value;
	var gender = document.getElementById('gender123').value;
	
	var state=document.getElementById('state123').value;
	var city=document.getElementById('city123').value;
	var address=document.getElementById('address123').value;
	
	var profImg=document.getElementById('profileimg').value;
	var docimg=document.getElementById('docimg').value;
	var relativedocimg = document.getElementById('relativedocimg').value;
	
	var town=document.getElementById('town123').value;
	var pincode123 = document.getElementById('pincode123').value;
	
	var gloalisError=false;
	
	var puresevadobyear = document.getElementById('puresevadobyear').value;
	var puresevadobmonth = document.getElementById('puresevadobmonth').value;
	var puresevadobdays = document.getElementById('puresevadobdays').value;
	
	var relativename123 = document.getElementById('relativename123').value;
	var relativecontact123 = document.getElementById('relativecontact123').value;
	var relativerelation123 =   document.getElementById('relativerelation123').value;
	var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var regex = /^[a-zA-Z ]*$/;
	var flagforvalidatedob1 =false;
	if(puresevadobyear==5){
		if(puresevadobmonth==0 && puresevadobdays==0){
			flagforvalidatedob1 =true;
		}
	}else if(puresevadobyear<5){
		flagforvalidatedob1 =true;
	}
	
	var flagforvalidatedob =false;
	
	if(puresevadobyear==16){
		if(puresevadobmonth==0 && puresevadobdays==0){
			flagforvalidatedob =true;
		}
	}else if(puresevadobyear<16){
		flagforvalidatedob =true;
	}
	
	if(fname=='undefined'){
		fname = '';
	}
	if(lname=='undefined'){
		lname = '';
	}
	if(mob=='undefined'){
		mob = '';
	}
	if(dob=='undefined'){
		dob = '';
	}
	alertmsgduration=5000;
	
	/*if(email==''){
		jAlert('error', 'Please enter email.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		gloalisError=true;
	}*/
	var bugisthere= false;
	if(flagforvalidatedob || flagforvalidatedob1){
		if(relativename123==''){
			bugisthere= true;
		}else if(relativecontact123==''){
			bugisthere= true;
		}else if(!isValidMobileNo(relativecontact123)){
			bugisthere= true;
		}
		else if(relativerelation123==''){
			bugisthere= true;
		}
		
		else if(relativedocimg==''){
			bugisthere= true;
		}
	}
	
	
	/*if (docimg=='' && flagforvalidatedob1==false) {
		  if (true) {
			  jAlert('error', 'Please select Identity Document.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, telemedmsgduration);
				gloalisError=true;
		  }
	}*/
	/*else if(bugisthere){
		if(relativename123==''){
			jAlert('error', "Please enter Patient's Parent / Guardian Name.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}else if(!regex.test(relativename123)){
			jAlert('error', "Parent / Guardian : No Special/Number are allowed", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}else if(relativecontact123==''){
			jAlert('error', "Please enter Parent / Guardian Contact Number.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}else if(!isValidMobileNo(relativecontact123)){
			jAlert('error', "Please enter valid Parent / Guardian Contact Number.", 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}else if(relativerelation123==''){
			jAlert('error', "Please select Relationship with Patient's.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}
		
		else if(relativedocimg==''){
			jAlert('error', "Please enter Parent / Guardian Identity Document.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
		}
	}*/else if(validateEmail(email)==false && email!=''){
			jAlert('error', 'Please enter valid email address.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
	} else if (email == '') {
		jAlert('error', "Please enter Email ID!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		gloalisError = true;
	} else if (!emailregEx.test(email)) {
		jAlert('error', "Please enter Valid Email ID!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		gloalisError = true;
	}else if(title=='' || title=='0'){
		jAlert('error', 'Please select Salutation.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(fname=='' && fname!=undefined){
		jAlert('error', "Please enter Patient's First / Given Name.", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(!regex.test(fname)){
		jAlert('error', "First Name : No Special/Number are allowed", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(lname==''){
		jAlert('error', "Please enter Patient's Last / Family Name.", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(!regex.test(lname)){
		jAlert('error', "Last Name : No Special/Number are allowed", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(gender=='' || gender=='0'){
		jAlert('error', 'Please select Gender.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}/*else if(mob==''){
		jAlert('error', 'Please enter Patient Mobile Number.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		gloalisError=true;
	}*/else if(!isValidMobileNo(mob)){
		jAlert('error', "Please enter valid patient's mobile number !", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(dob==''){
		jAlert('error', "Please enter Patient's Date of Birth.", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(address==''){
		jAlert('error', "Please add valid Patient's Address.", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true
	}/*else if(town==''){
		jAlert('error', 'Please add valid Village / Town.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true
	}*/else if(!regex.test(town)){
		jAlert('error', "Village/Town : No Special/Number are allowed", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true;
	}else if(city==''){
		jAlert('error', 'Please select valid City / District.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true
	}else if(state==''){
		jAlert('error', 'Please select valid state.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true
	}/*else if(pincode123.length>6){
		jAlert('error', 'Please select valid Postal Code (Zip / Pin).', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, telemedmsgduration);
		gloalisError=true
	} */
	
	/*else if ( profImg=='') {
		  if (true) {
			  jAlert('error', 'Please enter profile image.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				gloalisError=true;
		  }
	}*/
	/*else if (docimg=='') {
		  if (true) {
			  jAlert('error', 'Please select Identity Document.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				gloalisError=true;
		  }
	}	 */
	
	if(mobileregstst==1){
		 jAlert('error', 'Mobile number already registered.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, telemedmsgduration);
			gloalisError=true;
	}
	 if(gloalisError==false){
		 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
		 $( "#preloader" ).modal( "show" );
		 
		 var url = "otpClient?email="+email+"&mob="+mob+"&fname="+fname+"&lname="+lname+"&isregistrationmsg=1";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = confirmOtpAjax1Request;
			    req.open("GET", url, true); 
			              
			    req.send(null);
	 }else{
		 
	 }
	
	
}
function confirmOtpAjax1Request() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			$( "#preloader" ).modal( "hide" );
			otp = req.responseText;
			$( "#puresevaclientdetailsdiv" ).modal( "hide" );	
			$( "#atppopup1" ).modal( "show" );
			document.getElementById("timer1").innerHTML="5:00";
			countdown1(5);
		}
		}
}
function otpConfirmedmob1(){
	var enteredotp=document.getElementById('otptxt1').value;
/*	if(enteredotp==""){
		jAlert('error', 'Please Enter OTP!', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{*/
	otp="775266";
	if(enteredotp==otp){
		savepuredata()	
	}else{
jAlert('error', 'OTP did not matched!', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}	
	
/*}*/	
}
function departmentconfirm() {
	var clinicid=document.getElementById('clinicid').value;
	var uhid=document.getElementById("oriuhid").value;
	var linkaddress=document.getElementById("linkaddress").value;
	var dept=document.getElementById("diciplineName123").value;
	var url="http://"+linkaddress+":8080/warname/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+uhid+"&dept="+dept+"";
//	var url="https://"+linkaddress+":8443/"+warname+"/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+uhid+"&dept="+dept+"";
	window.location=url;	
}




function getslot() {
	
	var diaryuserId=document.getElementById('diaryUsersss').value;
	if(diaryuserId!=0){
		$( "#newloaderPopup" ).modal( "show" );
	}
	var date = document.getElementById("commencing").value;
	sdate = date;
	sdiaryuserId = diaryuserId;
	var url = "showallAvailabilityfordrBookAppointmentAjax?diaryuserId=" + diaryuserId
			+ "&date=" + date + "&location=1";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = getslotRequest;
	req.open("GET", url, true);

	req.send(null);

}
var appointmenttypeid="";
function getslotRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
//			$('#motloader').modal("hide");
//			
//			
//			if(document.getElementById("r56").checked==true){
//				var ctimeid =  req.responseText;
//				document.getElementById("next2").disabled = false;
//				document.getElementById("TimingSlot").innerHTML = '';
//				document.getElementById("selcttimeid").style.display = 'none';
//				
//				getSelectedSlotTime(ctimeid);
//			}else{
			$( "#newloaderPopup" ).modal( "hide" );
			var temp = req.responseText;
			var str = temp.split("~~");
			$( "#checkaval" ).modal( "show" );
			document.getElementById("avalslot").innerHTML = str[0];
			appointmenttypeid=str[1];
			if(temp==""){
				document.getElementById("drnamewithqul").innerHTML="Dr Slot Not Available";
//				document.getElementById("drspecialityname").innerHTML="";
			}else{
			document.getElementById("drnamewithqul").innerHTML = str[2];
			document.getElementById("selecteddiarydate").innerHTML = str[4];
//			document.getElementById("drspecialityname").innerHTML ="(" +str[3]+")";
//			
			}
			var actype=document.getElementById("actiontype").value;
			if(actype==5){
				actionType=5;
			}
//			if(actionType!=5){
//
//				document.getElementById("drnamewithqul").innerHTML="";
//				document.getElementById("drspecialityname").innerHTML="";	
//			}
//			document.getElementById("apmslotid").value = str[1];
//			document.getElementById("user").value = str[2];
//			document.getElementById("tdate").value = sdate;
//			document.getElementById("diaryUserid").value = str[2];
//			}
			/*
			 * var slot=str[3]; document.getElementById("slot").value=slot;
			 * document.getElementById("slottemp").value=slot;
			 */
//			document.getElementById("dddd").style.visibility = "hidden";
//			document.getElementById("ddtt").style.visibility = "hidden";
			showdisplaynewopd();
		}
	}
}

var starttimess1="";
var endtimess1="";
var durationss1="";
function setslottemp(idss,diaryuseridsss,starttimess,endtimess,durationss){
	if(actionType==5){
	showpopupfornewopd()
	}else{
		$( "#checkaval" ).modal( "hide" );
		$( "#blockdiv" ).modal( "show" );
		document.getElementById('blocksTime').value=starttimess;
		document.getElementById('blockendTime').value=endtimess;
		document.getElementById('blockapmtDuration').value=durationss;
	}
	starttimess1=starttimess;
	endtimess1=endtimess;
	durationss1=durationss;
} 
function showconfirmationpopup(){
	$( "#confirmuhid" ).modal( "show" );
}
function showspecialitypopup(){
	$( "#selectdept" ).modal( "show" );
}


function showselecteddr(deptrtmnt) {
//	document.getElementById("drnamewithqul").innerHTML="";
//	document.getElementById("drspecialityname").innerHTML="";
var url = "getselecteddrlistBookAppointmentAjax?dept="+deptrtmnt+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showselecteddrRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	
}


function showselecteddrRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("selecteddrwithsp").innerHTML=req.responseText;
			$("#diaryUsersss").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
		}
	}
}

var mobileregstst=0;

function checkexistingmobno(){
	var mobNo=document.getElementById("puremob").value;
	var url = "existmobilenoNotAvailableSlot?mob="+mobNo+" ";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();	
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkexistingmobnoRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkexistingmobnoRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			var data=str.split("~~~");
			if(data[0]!=0){
				jAlert('error', 'Mobile No Already Registerd.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				mobileregstst=1;
			}else{
				mobileregstst=0;
			}
		}
	}
	}


function confirmpopupreg(){
	var mobNo=document.getElementById("entermob").value;
	if(!isValidMobileNo(mobNo)){
		jAlert('error', 'Please enter valid mobile number !', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	var url = "existmobilenoNotAvailableSlot?mob="+mobNo+" ";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();	
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange =confirmpopupregRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function confirmpopupregRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			var data=str.split("~~~");
//			if(data[0]!=0){
//				jAlert('error', 'Mobile No Already Registerd.', 'Error Dialog');
//				
//				setTimeout(function() {
//					$("#popup_container").remove();
//					removeAlertCss();
//				}, alertmsgduration);
//				
//			}else{
				firstconfirmotp();
//			}
		}
	}
	}
var timeoutHandle;
function countdown1(minutes) {
    var seconds = 60;
    var mins = minutes
    function tick() {
        var counter = document.getElementById("timer1");
        var current_minutes = mins-1
        seconds--;
        counter.innerHTML =
        current_minutes.toString() + ":" + (seconds < 10 ? "0" : "") + String(seconds);
        if( seconds > 0 ) {
            timeoutHandle=setTimeout(tick, 1000);
        } else {
 
            if(mins > 1){
 
               // countdown(mins-1);   never reach “00″ issue solved:Contributed by Victor Streithorst
               setTimeout(function () { countdown1(mins - 1); }, 1000);
 
            }
        }
    }
    tick();
}
var timeoutHandle;
function countdown2(minutes) {
    var seconds = 60;
    var mins = minutes
    function tick() {
        var counter = document.getElementById("timer2");
        var current_minutes = mins-1
        seconds--;
        counter.innerHTML =
        current_minutes.toString() + ":" + (seconds < 10 ? "0" : "") + String(seconds);
        if( seconds > 0 ) {
            timeoutHandle=setTimeout(tick, 1000);
        } else {
 
            if(mins > 1){
 
               // countdown(mins-1);   never reach “00″ issue solved:Contributed by Victor Streithorst
               setTimeout(function () { countdown2(mins - 1); }, 1000);
 
            }
        }
       
    }
    tick();
}
function listofregpatient(){
var url = "listofregpatientBookAppointmentAjax?mob="+mobusermobile+" ";	
if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();	
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange =listofregpatientRequest;
req.open("GET", url, true); 
          
req.send(null);
}
function listofregpatientRequest() {

if (req.readyState == 4) {
	if (req.status == 200) {
		var str=req.responseText;
//		str=str.replace("onclick='setoriguhid(","onclick='setoriguhid('");
//		str=str.replace(")","')");
		document.getElementById("avalablept").innerHTML=str;
		$( "#availablepatient" ).modal( "show" );
	}
}
}

function setoriguhid(val){
	var selectuhid=document.getElementById('selectabbr'+val).value;
	var clinicid=document.getElementById('clinicid').value;
	var uhid=selectuhid;
	var linkaddress=document.getElementById("linkaddress").value;
	//var url="http://localhost:8081/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+uhid+"&flag=1";

	var url="http://"+linkaddress+":8080/YUVICAREAPK/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+uhid+"&flag=1";
//	var url="https://"+linkaddress+":8443/"+warname+"/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+uhid+"";
	window.location=url;
}

function openvideoconfirm(clntid,apoiid){
	$( "#confirmviddr" ).modal( "show" );
	document.getElementById("selectedappid").value=apoiid;
	document.getElementById("selectedcid").value=clntid;
	
}
function confirmnotes() {
	document.getElementById("doctornotes").style.display="";
	document.getElementById("yesbtnid").style.visibility='hidden';
	document.getElementById("nobtnid").innerHTML="No";
	var v=document.getElementById("nobtnid").innerHTML;
	var notes=document.getElementById('drrejectrem').value;
	if(v=="No"){
		if(notes==''){
			jAlert('error', 'Please Enter Remark !', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
	updatedrnoremark(notes);	
		}
		}
}
function updatedrnoremark(val) {
	var id=document.getElementById("selectedappid").value;
	var clientid=document.getElementById("selectedcid").value;
	var url = "updatevideoremarkBookAppointmentAjax?id="+id+"&notes="+val+"&clientid="+clientid+" ";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();	
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =updatedrnoremarkRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
	}
	function updatedrnoremarkRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			$( "#confirmviddr" ).modal( "hide" );
			showdisplaynewopd();
		}
	}
}



function setvideobydr() {
	var id=document.getElementById("selectedappid").value;
	var clientid=document.getElementById("selectedcid").value;
	var url = "updatevideostatusBookAppointmentAjax?id="+id+"&clientid="+clientid+" ";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();	
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =updatedrnoremarkRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
	}
	function updatedrnoremarkRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			$( "#confirmviddr" ).modal( "hide" );
			showdisplaynewopd();
		}
	}
}
	function openremarkPopup(clid,appid) {
		var url = "getremarkBookAppointmentAjax?id="+appid+"&clientid="+clid+" ";	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		           
		req.onreadystatechange =openremarkPopupRequest;
		req.open("GET", url, true); 
		          
		req.send(null);
		}
		function openremarkPopupRequest() {

		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
				var data=str.split("~~~~");
				document.getElementById("remm1").innerHTML=data[0];
				document.getElementById("remm2").innerHTML=data[1];
				$( "#remarkbyhosp" ).modal( "show" );
				
			}
		}	
	}
	function showdrprofile(val) {
		var url = "getdrinfoBookAppointmentAjax?id="+val+" ";	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		           
		req.onreadystatechange =showdrprofileRequest;
		req.open("GET", url, true); 
		          
		req.send(null);
		}
		function showdrprofileRequest() {

		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
				var data=str.split("~~~~");
				document.getElementById("drname").innerHTML=data[0];
				document.getElementById("drqual").innerHTML=data[1];
				document.getElementById("despec").innerHTML=data[2];
				document.getElementById("drreg").innerHTML=data[3];
				document.getElementById("drphone").innerHTML=data[4];
				$( "#showdrprofile" ).modal( "show" );
				
			}
		}	
	}
	
function checkage(val){
	var data=val.split("/");
	var year=Number(data[2]);
	today_date = new Date();
    var today_year = today_date.getFullYear();
    var diffyear=today_year-year;
    document.getElementById("diffyear").value=diffyear;
    if(diffyear<=16){
    	
    	document.getElementById("majorminor").innerHTML="Minor"
    }else{
   	 document.getElementById("majorminor").innerHTML="Major "
    }
    showinstruct();
    document.getElementById("mobdiv").className="form-group";  
}		
function showdob(val){
	if(val=='old'){
		document.getElementById("cdob").className="hidden";
		showinstruct();
	}else{
	document.getElementById("cdob").className="row";
	}
}
function showinstruct() {
var val="";
	if (document.getElementById('newpatient').checked) {
		   val=document.getElementById('newpatient').value;
		  } else if (document.getElementById('oldpatient').checked) {
		    val=document.getElementById('oldpatient').value;
		  }
	var diffyear=document.getElementById("diffyear").value;
	
	if(val=="new"){
		if(diffyear<=5){
			document.getElementById("new1").className="";
			document.getElementById("new").className="hidden";
			document.getElementById("old").className="hidden";
			document.getElementById("agenew").className="hidden";
		}else if(diffyear>5 && diffyear<=16 ){
			document.getElementById("new").className="hidden";
			document.getElementById("agenew").className="";
			document.getElementById("new1").className="hidden";
			document.getElementById("old").className="hidden";
		}else{
			document.getElementById("new").className="";
			document.getElementById("new1").className="hidden";
			document.getElementById("old").className="hidden";
			document.getElementById("agenew").className="hidden";
		}
		document.getElementById("newregpatient").style.visibility ="visible";
		document.getElementById("confirmmob").style.visibility ="hidden";
	}else{
		document.getElementById("old").className="";
		document.getElementById("new").className="hidden";
		document.getElementById("new1").className="hidden";
		document.getElementById("newregpatient").style.visibility ="hidden";
		document.getElementById("confirmmob").style.visibility ="visible";
	}
	document.getElementById("mobdiv").className="form-group";
}
var puresevadob="";

function getAgendDays4(dob) {
	puresevadob=dob;
    var url="getageClient?dob="+dob+"";

    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getAgendDays4Request;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getAgendDays4Request(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		     var data= str.split("~");
		     document.getElementById("pureage").value=data[0];
		     if(data[0]<=16){
		    	 document.getElementById("majorminor").innerHTML="Minor"
		     }else{
		    	 document.getElementById("majorminor").innerHTML="Major "
		     }
//		     document.getElementById("month").value=data[1];
//		     document.getElementById("days").value=data[2];
		     /* var year= parseInt(data[0]);
		     var days=parseInt(data[1]);
		     if(year==0&&days<8){
		    	 $('#babyconfirm').modal('show');
		    	/* if(confirm("Is This Baby Born in Hospital")){
		    		 alert("Baby is born inj Hosp")
		    	 }}*/
		     checkage(puresevadob);
    
		}
   }
   }

function allnumeric4(dd)  {  
   var numbers = /^[0-9]+$/;  
   if(!dd.match(numbers))  
   {  
  	alert('only number permitted...');  
   }  
   else 
   {
         var today= new Date();
         var yyyy = today.getFullYear();
         var year= parseInt(yyyy)- parseInt(dd);
         var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
         
         if(dd<10) {
 			dd='0'+dd
			} 

			if(mm<10) {
 			mm='0'+mm
			} 

			var str = dd+'/'+mm+'/'+year;
			document.getElementById("initialdob").value=str;
   		document.getElementById("days").value=0;
   		checkage(str);
   }
   
}   




var d=0;
function tokendisplay(){
	var drid=document.getElementById("drids").value;
	var dridsdata=drid.split(",");
		var t=setInterval(function(){ 
			if(d<dridsdata.length){
				showdisplaynewopd1(dridsdata[d]);
			}else{
				clearInterval(t);
				 d=0;
				tokendisplay();	
			}
			
			 
			  }, 2000);
			

}

var xt=0;

function showdisplaynewopd1(did){
//	delay(function(){
	var ndate = document.getElementById('commencing').value;
//	var nduserid = document.getElementById('druid').value;
	var nduserid=did;
	var sts="";
//	alert(xt);
//	xt++;
	var dataObj={
				
				
			  	"ndate":""+ndate+"",
			  	"nduserid":""+nduserid+"",
			  	"opdstatus":""+sts+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		
		   url : "newdisplaytokenBookAppointmentAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			 
			   $('#example').DataTable().destroy();
			   document.getElementById('newopdbodyid').innerHTML = data.opdtable;
			   document.getElementById('slotId').value=data.slotid;
			   document.getElementById('headdr').innerHTML=data.drname;
				bootstrapplugin();
				d++;
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				d++;
		   }
		});	
//	 }, 10000);
	}
function hidelabe(val){
	
	}


function setcommenc(val) {
	alert("hi");
//	document.getElementById("commencing").value=val;
}
function openscanpopup(apppid) {
	var url = "getappointmentdataforpayBookAppointmentAjax?apmtid="+apppid+"";
		
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();	
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = openscanpopupRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
		
	function openscanpopupRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			$("#scanandpay").modal( "show" );
			var str=req.responseText;
			 document.getElementById("scanamount").innerHTML=str;
		}
	}
	}
	
	
	
	

	function showpopupfornewot(){
		
		
		
	document.getElementById('apmtwithz').innerHTML = $("#diaryUsersss option:selected").text();
		
		var ndate = document.getElementById('commencing').value;
		var nduserid = document.getElementById('diaryUsersss').value;
		if(nduserid=="0" || nduserid=="" || nduserid=="1"){
			jAlert('error', 'Please Select Doctor.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			
		
		var url = "newopdBookAppointmentAjax?ndate="+ndate+"&nduserid="+nduserid+" ";
		if(actionType==5){
			
		}
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showpopupfornewotRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		}
		
	}

	function showpopupfornewotRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('puresevaclntname').innerHTML="";
				openedb = 'otdb';
				
				if(openedb=='otdb'){
					document.getElementById("radio3").checked = true;
				}
				isnewopd = 1;
				
				
				
				var data = req.responseText;
				var tmp = data.split('~');
				
				var slotid = tmp[0];
				var stime = tmp[1];
				var etime = tmp[2];
				var duration = tmp[3];
				
				
				var location = tmp[4];
				var diaryUser = tmp[5];
				
				var title = tmp[7];
				if(actionType==5){
					stime=starttimess1;
//					etime=endtimess1;
//					duration=durationss1;
					title=starttimess1;
				}
				if(actionType==5){
					$( "#checkaval" ).modal( "hide" );
				}
				var commencing = tmp[8];
				var diaryUserId = tmp[9];
				chkdiartyuserid=tmp[10];
				 if(chkdiartyuserid==1){
						jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
					
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					 
				/*	var box=bootalert('error',"Please set Doctor Diary", "Error Dialog");
					  setTimeout(function() {
						    box.modal('hide');
						}, 3000);*/
					 
					 
				 }else{
//				$(this).MessageBox(slotid,stime,etime,duration,location,diaryUser,diaryUserId,title);
					 opdnewfun(slotid,stime,etime,duration,location,diaryUser,diaryUserId,title);
					 
				document.getElementById('diciplineName').value = location;
				$( "#appointment" ).modal( "show" );
				resetot();
				 }
				clearFiledCommonAction();

				
				document.getElementById('client').value = "";
				document.getElementById('notes').value = "";
				
				document.getElementById('apmtType').value = 0;
				document.getElementById('apmtType').className="";
				if(actionType==5){
//					$( "#checkaval" ).modal( "hide" );
//					setTimeout(() => {  setClientName(puresevaclientid,'0','','Client',''); }, 2000);
//					setClientName(puresevaclientid,'0','','Client','');
					 dbname = puresevaclientname;
					 dbid =puresevaclientid ;
					 dbtype = '0';
					 dbtypename = '';
					 dbpayby = 'Client';
					 dbgpid = '';
					 document.getElementById("clientId").value = puresevaclientid;
						//set cookie data
						
						document.cookie = "cookieClientId=" + puresevaclientid;
						document.getElementById('clientId').value = puresevaclientid;
					 document.getElementById('client').value = puresevaclientname;
//					setAppointmentTypeTimeAjax(appointmenttypeid);
//					document.getElementById('client').value=puresevaclientname;
					setTimeout(() => {  setAppointmentTypeTimeAjax(appointmenttypeid); }, 1000);
					document.getElementById('apmtType').value=appointmenttypeid;
//					slotstarttime = starttimess1;
				}
//				$("#apmtType").trigger("chosen:updated");
//				$(".chosen").chosen({allow_single_deselect: true});
//				
				document.getElementById('condition').value = location;
//				$("#condition").trigger("chosen:updated");
//				$(".chosen").chosen({allow_single_deselect: true});
				
				var tstr = '<select name="treatmentEpisode" id="treatmentEpisode" class="form-control showToolTip chosen"><option value="0">Select Treatment Episode</option></select>'
				document.getElementById('treatmentepisodeajax').innerHTML = tstr;
//				$("#treatmentEpisode").trigger("chosen:updated");
//				$(".chosen").chosen({allow_single_deselect: true});
				slotstarttime = stime;
//				if(actionType==5){
//					setClientName(puresevaclientid,'0','','Client','');
////					document.getElementById('client').value=puresevaclientname;
//					setAppointmentTypeTimeAjax('982');
////					slotstarttime = starttimess1;
//				}
				
				//$('#appointment').dialog('option', 'title', 'New Appointment');
				editAppointId = 0;
				editCommencing = commencing;
				
				
				document.getElementById('date').value = commencing;
				document.getElementById('blockdate').value = commencing;

			//set cookie commencing
			document.cookie = "cookiecommencing=" + commencing;
			document.cookie = "cookiePractitionerId=" + diaryUserId;
			if(actionType==5){
			document.getElementById('puresevaclntname').innerHTML = puresevaclientname;
			document.getElementById("client").className="hidden hidden-xs";
			document.getElementById("client").style.visibility="hidden";
			document.getElementById("client").style.display="none";
			$('#client').toggleClass('hidden');
			}else{
				
				document.getElementById("client").value="";
				document.getElementById('puresevaclntname').className="";
			}
				}
		}
		
		}

	function shownewdto1(apmtid,pid,conid,clientid,indx,popstatus,bodytmplate,bodyeditedtmplate,drcomplete,patientseen){
		pbodytmplate = bodytmplate;	
		pbodyeditedtmplate = bodyeditedtmplate;
		
//		var opdmodifylog=document.getElementById('opdmodifylogin').value;
//		if(opdmodifylog!='true'){
			document.getElementById('opdmoddivid').style.display = 'none';
//		}
		
		 patientId = clientid;
		 pppid = clientid;
		 pppcname  = document.getElementById('ncname'+indx).value;
		 pppwhopay  = document.getElementById('nwhopay'+indx).value;
		 ppptepisode  = 0;
		 
		 editAppointId = apmtid;
		 editwhopay = document.getElementById('nwhopay'+indx).value;;
		 editClientName = document.getElementById('ncname'+indx).value;
		 loc = 1;
		 diaryuserId = pid;
		 editcondition_id = conid;
		 editTreatmentEpisode = 0
			
		 document.cookie = "cookiecommencing=" + document.getElementById('commencing').value;;
		 document.cookie = "cookiePractitionerId=" + pid;
		 
		 document.cookie = "cookieClientId=" + pppid;
		 document.cookie = "cookieUserName=" + editClientName;
		 document.cookie = "cookieSelectedAppointmentid=" + editAppointId;
		 document.cookie = "cookieTreatmentEpidodeSessions=" + 0;
		 document.cookie = "complocationid=" + loc;
		 document.cookie = "cookiePractitionerId=" + pid;
		 document.getElementById('clientisbeingseentxt').style.color='black';
		 document.getElementById('finalcompletetxt').style.color='black';
			if(popstatus==1){
				
				document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
				document.getElementById('clientarrived123').style.display='';
				 document.getElementById('clientisbeingseentxt').style.color='black';
				 document.getElementById('clientseen111').style.pointerEvents = 'none';
				 document.getElementById('finalcompletetxt').style.color='black';
				 document.getElementById('completeapmt111').style.pointerEvents = 'none';
				 document.getElementById('completeappointment').style.display='';
				 document.getElementById('clientdidnotcome').style.display='';
				 document.getElementById('cancelappointment').style.display='';
				document.getElementById('resettocomplete').style.display='none';
//				document.getElementById('finalcomplete').style.display='none';
				document.getElementById('clientdidnotcome').style.display='none';
				document.getElementById('accountsopt').style.display='';
				 document.getElementById('addotequip').style.display='';
				 document.getElementById('listotequip').style.display='';
				 document.getElementById('otnot').style.display='';
				$( "#modifyPopup" ).modal( "show" );
			}
		  if(popstatus==2){
				 	
				 document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//					$( "#completeActionPopup" ).modal( "show" );
				 document.getElementById('clientarrived123').style.display='none';
				document.getElementById('completeapmt111').style.pointerEvents = 'none';
				 document.getElementById('clientseen111').style.pointerEvents = 'none';
				 if(patientseen==0){
				 document.getElementById('clientseen111').style.pointerEvents = 'auto';
				 }else if(patientseen==1){
					 document.getElementById('clientisbeingseentxt').style.color='brown';
					 document.getElementById('clientseen111').style.pointerEvents = 'none';
					 document.getElementById('completeapmt111').style.pointerEvents = 'auto';
				 }
				  if(drcomplete==1){
					  document.getElementById('finalcompletetxt').style.color='brown';
						 document.getElementById('completeapmt111').style.pointerEvents = 'none';
					 }
				 document.getElementById('completeappointment').style.display='none';
				 document.getElementById('clientdidnotcome').style.display='none';
				 document.getElementById('cancelappointment').style.display='none';
				 document.getElementById('resettocomplete').style.display='';
//					document.getElementById('finalcomplete').style.display='';
					document.getElementById('clientdidnotcome123').style.display='none';
					document.getElementById('accountsopt').style.display='';
					 document.getElementById('addotequip').style.display='';
					 document.getElementById('listotequip').style.display='';
					 document.getElementById('otnot').style.display='';
				 $( "#modifyPopup" ).modal( "show" );
				}
		  if(popstatus==3){
				 document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//					$( "#recordpaymentpopup" ).modal( "show" );
				 document.getElementById('clientarrived123').style.display='none';
				 document.getElementById('completeapmt111').style.pointerEvents = 'none';
				 document.getElementById('clientseen111').style.pointerEvents = 'none';
				 if(patientseen==0){
				 document.getElementById('clientseen111').style.pointerEvents = 'auto';
				 }else if(patientseen==1){
					 document.getElementById('clientisbeingseentxt').style.color='brown';
					 document.getElementById('clientseen111').style.pointerEvents = 'none';
					 document.getElementById('completeapmt111').style.pointerEvents = 'auto';
				 }
				  if(drcomplete==1){
					  document.getElementById('finalcompletetxt').style.color='brown';
						 document.getElementById('completeapmt111').style.pointerEvents = 'none';
					 }
				 document.getElementById('completeappointment').style.display='none';
				 document.getElementById('clientdidnotcome').style.display='none';
				 document.getElementById('cancelappointment').style.display='none';
				 document.getElementById('resettocomplete').style.display='none';
//					document.getElementById('finalcomplete').style.display='';
					document.getElementById('clientdidnotcome123').style.display='none';
					 document.getElementById('accountsopt').style.display='';
					 document.getElementById('addotequip').style.display='';
					 document.getElementById('listotequip').style.display='';
					 document.getElementById('otnot').style.display='';
				 $( "#modifyPopup" ).modal( "show" );
				}
		  if(popstatus==4){
				 document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopupold('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
				 document.getElementById('clientdidnotcome').style.display='';
				 document.getElementById('clientarrived123').style.display='none';
				 document.getElementById('clientisbeingseen123').style.display='none';
				 document.getElementById('completeappointment').style.display='none';
				 document.getElementById('cancelappointment').style.display='none';
				 document.getElementById('resettocomplete').style.display='none';
					document.getElementById('finalcomplete').style.display='none';
					document.getElementById('clientdidnotcome123').style.display='none';
				 document.getElementById('accountsopt').style.display='none';
				 document.getElementById('addotequip').style.display='';
				 document.getElementById('listotequip').style.display='';
				 document.getElementById('otnot').style.display='';
				 $('#modifyPopup').modal( "show" );
				}
			
		  if(popstatus==5){
				// document.getElementById('puremodifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
				 $('#puremodifyPopup').modal( "show" );
				}
		  if(popstatus==6){
				// document.getElementById('puremodifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset iconwd" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset iconwd" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset iconwd" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset iconwd" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
				document.getElementById("deleteapmtiddiv123").className="hidden";
				document.getElementById("qrcodediv").className="hidden";
			  $('#puremodifyPopup').modal( "show" );
				}
		  
		  setHISBMIData(clientid,indx);  
	
			
//		 
//		if(popstatus==1){
//			
//			document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//			$( "#modifyPopup" ).modal( "show" );
//		}
//	  if(popstatus==2){
//			 	
//			 document.getElementById('modifyClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//				$( "#completeActionPopup" ).modal( "show" );
//			}
//	  if(popstatus==3){
//			 document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//				$( "#recordpaymentpopup" ).modal( "show" );
//			}
//	  if(popstatus==4){
//			 document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
//			 $('#modifydnapopup').modal( "show" );
//			}
//		
//	  setHISBMIData(clientid,indx);  
//		
	}
	function getCookie(cname) {
		  var name = cname + "=";
		  var decodedCookie = decodeURIComponent(document.cookie);
		  var ca = decodedCookie.split(';');
		  for(var i = 0; i < ca.length; i++) {
		    var c = ca[i];
		    while (c.charAt(0) == ' ') {
		      c = c.substring(1);
		    }
		    if (c.indexOf(name) == 0) {
		      return c.substring(name.length, c.length);
		    }
		  }
		  return "";
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
		/*var x=document.getElementById("chdept");
		var arr=document.getElementById('sellect').value;
		var arr2=arr.split(",");
		var val="0";
	    for (var i = 0; i < x.options.length; i++) {
	       if(x.options[i].selected ==true){
	           val=val +","+x.options[i].value;
	        }
	    }
	    var tmp=val.split(",");
	    for(var i=0; i<tmp.length;i++ )
	    { 
	       if(tmp[i]=="0")
	           tmp.splice(i,1); 
	     }*/
		let difference = document.getElementById('referselected').value; 
	    //let difference = tmp.filter(x => !arr2.includes(x));
//		var duserid=document.getElementById('chdept').multiple;
		var clientd=document.getElementById('cllid').value;
		var aptid=document.getElementById('appptid').value;
		var referremark=document.getElementById('referremark').value;
		if(difference.length!=0 || difference!=''){
		var url = "referdeptBookAppointmentAjax?dept="+difference+"&clientid="+clientd+"&aptid="+aptid+"&sts=1&referremark="+referremark+"";
		
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
				showdisplaynewopd();
			//window.location.reload();
			document.getElementById("refrbtn").style.display="";
		}
	}
	}
	function refersect(){
		var selectedwardid = 0;
	     $('.sscc').each(function() { //loop through each checkbox
	        // this.checked = true;  //select all checkboxes with class "checkbox1" 
	        if(this.checked==true){
	        	selectedwardid = selectedwardid + ',' + this.value;
	        }
	         
	     });
		var array = selectedwardid.split(",");
		
		array = array.filter(val => val !== "0");
		var valu=array.toString();
	     document.getElementById('referselected').value = valu;
	}
	
	
	function lmhcategorynewopd(){
	
	var ndate = document.getElementById('commencing').value;
	var nduserid = document.getElementById('diaryUsersss').value;
	var orderby=document.getElementById("orderappt").value;
	var patientcategoty=document.getElementById("patientcategory").value;
	var url = "newdisplayBookAppointmentAjax?ndate="+ndate+"&nduserid="+nduserid+"&opdstatus="+orderby+"&patientcategory="+patientcategoty+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = lmhcategorynewopdRequest;
    req.open("GET", url, true); 
              
    req.send(null);

	
}

function lmhcategorynewopdRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 var data=req.responseText.split("~~~");
			 var data=req.responseText.split("~~~");
			 if(document.getElementById('idenot')){
				 data[0]=data[0].replace(/shownewdto/g,"shownewdto1");	 
			 }
			document.getElementById('newopdbodyid').innerHTML = data[0];
			document.getElementById('slotId').value=data[1];
			document.getElementById('undoneappt').innerHTML=data[2];
			/*if(data[3]==''||data[3]==3){
			document.getElementById('orderby').value=data[3];
			}*/
			if(actionType!=5){
				bootstrapplugin();
				}
			
			
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
		
}

function historyConsulatationForm(clientid,apmtid){
	
	var url = "historyConsulatationFormCommonnew?clientid="+clientid+"&apmtid="+apmtid+"";
		
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();	
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = consulthistoryRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	
	
}

function consulthistoryRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('consult').innerHTML = req.responseText;
			$("#consultdetails").modal('show');
		}
	}
	
	
}

function setGender123(val){
	 if(val=='Mr.' || val=='Babyboyof' || val=='Master'){
	  document.getElementById("gender123").value = 'Male';
	 }else if(val=='Mrs.' || val=='Miss.' || val=='BabyGirlof' || val=='Baby'){
	  document.getElementById("gender123").value = 'Female';
	 }
	}


function savediary(actiontype){
	//var selectedid=document.getElementById("diaryUsersss").value;
	//alert(selectedid);

	//var commencing=document.getElementById("commencing").value;
	//opencPopup('DiaryMangent?selectedid='+selectedid+'&commencing='+commencing+'&actiontype='+actiontype+'');
	
	//new set diary rahul above priyanka code commented
	
	
	var commencing=document.getElementById("commencing").value;
	var docid=document.getElementById("diaryUsersss").value;
	
	var url="insertDoctordiaryOpdConsultation?commencing="+commencing+"&docid="+docid+"&actiontype="+actiontype+"";

        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = setdoctoropdRequest;
		req.open("GET", url, true);
		req.send(null);
	
	
		
	
}

function setdoctoropdRequest(){
	if (req.readyState == 4) {
	    if (req.status == 200) {
		  var str = req.responseText;
		  
		 if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
                 
                 document.getElementById("setbtn").className="hidden";
			}
		}
	}
}
 
 
 function openipdrequestnote(val){

    $("#ipdrequestnote").modal('show');
    document.getElementById("actiontype").value=val;
    //counsultationcount(ipdaddmissionid);
    shownote(opdid);

}

function savereqnote(){
	var reqid=document.getElementById("reqid").value;
	var actiontype=document.getElementById("actionnote").value;
	var note=document.getElementById("reqnotes").value;
	var ispunchkarma=document.getElementById("actiontype").value;
	var procedureid=document.getElementById("procedureid").value;
	var date =document.getElementById("fromDate").value;
	var dataObj={
		"note":""+note+"",
		"ipdaddmissionid":""+ipdaddmissionid+"",
		"actiontype":""+actiontype+"",
		"reqid":""+reqid+"",
		"ispunchkarma":""+ispunchkarma+"",
		"procedureid":""+procedureid+"",
		"opdid":""+opdid+"",
		"opdclientid":""+opdclientid+"",
		"ipdclientid":""+ipdclientid+"",
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


//function savesittingRequest(){
	
	//if (req.readyState == 4) {
	//	 if (req.status == 200) {
	//		var str = req.responseText;
			//document.getElementById('note').innerHTML = req.responseText;
			//$("#notedetails").modal('show');
	//	if (str == '0') {
	//		 alert("data not added.");                   // error
		//	} else {
				//alert("Added Successfully.");
				//window.location.reload();
			//	$("#ipdrequestnote").modal('show');
                     // success
		//	}
			
      //   }
		
   //  }
	
 // }
  
  
  function shownote(ipdadmissionid){
	
	var url = "requestnotelistNotAvailableSlot?ipdadmissionid="+ipdadmissionid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
	
    req.onreadystatechange = setrequestnoteRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setrequestnoteRequest(){
	
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

//function deletesittingRequest(){ same method in finder.js isiliye commented
     	//if (req.readyState == 4) {
		  //  if (req.status == 200) {
			//  var str = req.responseText;
			//   $("#baselayout1loaderPopup").modal("hide");
			
			//if (confirm("Do you want to delete?") == true) {
                 //        "Data deleted successfully!";
                         //window.location.reload();
            // } else {
           //              " Cancelled!";
          // }
		//}
    //}

//}
 
function editrequestnote(id,actiontype){
	
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
			document.getElementById("reqnotes").value=reqnote;
			document.getElementById("actionnote").value=actiontype;
			document.getElementById("reqid").value=id;
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
