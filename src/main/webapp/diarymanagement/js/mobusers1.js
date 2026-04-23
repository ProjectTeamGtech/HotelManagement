/*
$(document).ready(function(){
	
	getDaySearch();
});
*/
var isotpconfirmd = 0;
function mobSearch(){
	
	//set wraperdiv element
	 if(editAppointId!=0){
			getWraperDivChildren(wraperDivId);
	 }
	
	jQuery('.new').children().unwrap();
	
			actionType = 5;
			
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
				
				setmobJsoData(id,date,locationid);
			
			
			
}


function setmobJsoData(diaryUserId,date,locationid){

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
												
												//$( "#appointment" ).modal( "show" );
												/*if(isotpconfirmd==0){
													 $( "#puresevaclientdetailsdiv" ).modal( "show" );
												}else{
													// $( "#appointment" ).modal( "show" );
													otpConfirmed();
												}*/
												
												
												clearFiledCommonAction();
												otpConfirmed();
											
												
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
									
									setjsonMobAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,value.practitionerid,1,value.commencing,value.clinicstime);
								
								
							
							
							
							
                        });        
                     });
			
		}
	});
			
			
}	



function setjsonMobAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,size,commencing,clinicstime){

	

	$.ajax({
		url: "diarymanagement/pages/JQGridMasterAvailableData.jsp?diaryuserid="+diaryuserid+"&practitionerid="+practitionerid+" " ,
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
											document.getElementById(jcount+'min'+stdiff).onclick = function() {
												 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
												 $( "#appointment" ).modal( "hide" );
												 $( "#atppopup" ).modal( "hide" );
												
												 
											}
											if(value.iscompleted == true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#81AB6D');
												
											
											}else if(value.dna==true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#F96467');
												
											}
											else if(value.otid>0){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'blue');
												
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
											document.getElementById(jcount+'min'+stdiff).onclick = function() {
												editAppointId='invalid';
												 $( "#appointment" ).modal( "hide" );
												 $( "#atppopup" ).modal( "hide" );
												 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
											}
											
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
									/* $( ".cssClass"+random ).wrapAll( "<div id='new"+random+"'  class='new' />");
									 var resizewidth = $('#wn0').width();
									 var resultwidth = parseInt(resizewidth)+6;
									// $(document.getElementById('new'+random)).css('width', resultwidth);
									 if(tdhtmldata!=""){
										document.getElementById(stdiff).innerHTML = tdhtmldata; 
										 initilizeTdElement(stdiff,diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
										 $(document.getElementById('new'+random)).css('position', 'absolute');
										 $(document.getElementById('new'+random)).css('width', '93%');
										 
									 }*/
									 
									//wrap all
									var zp = '<div style="width:100px;height:100px;position:absolute;background-color:yellow;top:20px;left:20px;z-index:0;opacity:0.5;border:1px solid #333333;" onclick="zproperty()">z-index 0</div>'
									 $( ".cssClass"+random ).wrapAll( "<div id='new"+random+"'  class='new' ></div>");
									 var resizewidth = $('#wn0').width();
									 var resultwidth = parseInt(resizewidth)+6;
									// alert(resultwidth);
									// resultwidth = resultwidth/4;
									//$(document.getElementById('new'+random)).css('width', resultwidth);
									 if(tdhtmldata!=""){
										//alert(tdhtmldata)
										 //changeWrapedDivId('new'+random);
										 document.getElementById(stdiff).innerHTML = tdhtmldata; 
										 initilizeTdElement(stdiff,diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
										 
										 $(document.getElementById('new'+random)).css('position', 'absolute');
										 //$(document.getElementById('new'+random)).css('width', '95%');
										 
										 
										 //reinitilize wraped element
										 renitilizeWrapdedEvent(stdiff,'new'+random);
										 
									 }
									 
										 

									 
									 //modify popup
										 //modify popup
										 if(value.iscompleted == false){
											
											 document.getElementById('new'+random).onclick = function() {
												 if(value.luhid!=''){
													 if(value.luhid==value.uhid){
														 $( "#appointment" ).modal( "hide" );
														 $( "#atppopup" ).modal( "hide" );
															wraperDivId = 'new'+random;
															loc = value.locid;
															setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname);
															
															$('#modifyPopup').modal( "hide" );
															
															if(isotpconfirmd==0){
																$('#puremodifyPopup').modal( "hide" );
																 $( "#puresevaclientdetailsdiv" ).modal( "show" );
															}else{
																 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
																$('#puremodifyPopup').modal( "show" );
															}
															
															
													}else{
														editAppointId='invalid';
														 $( "#appointment" ).modal( "hide" );
														 $( "#atppopup" ).modal( "hide" );
														 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
														 document.getElementById('new'+random).onclick = "";
													}
												 }else{
													 editAppointId='invalid';
													 $( "#appointment" ).modal( "hide" );
													 $( "#atppopup" ).modal( "hide" );
													 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
													 document.getElementById('new'+random).onclick = "";
												 }
												
												 

												/* $( "#appointment" ).modal( "hide" );
													wraperDivId = 'new'+random;
													loc = value.locid;
													setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname);
													*/
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
												 editAppointId='invalid';
												 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
												 $( "#appointment" ).modal( "hide" );
												 $( "#atppopup" ).modal( "hide" );
												 
												 if(value.chargecompleted==false){
													 editAppointId='invalid';
													 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
													 $( "#appointment" ).modal( "hide" );
													 $( "#atppopup" ).modal( "hide" );
												 }else{
													 wraperDivId = 'new'+random;
													 editAppointId='invalid';
													 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
													 $( "#appointment" ).modal( "hide" );
													 $( "#atppopup" ).modal( "hide" );
												 }
												 
											
											 }
										 }

										 else{

											 document.getElementById('new'+random).onclick = function() {
												 editAppointId='invalid';
												 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
												 $( "#appointment" ).modal( "hide" );
												 $( "#atppopup" ).modal( "hide" );
												 
											 }
										 }
										
									 	//write text 
										var stext = parseInt(stemp[1]);
		                          		var etext = parseInt(etemp[1]);
										 
										 var stext = stemp[1];
			                          		var etext = etemp[1];
		                          		
		                          		
		                          		var showtext =  "";
		                          		
		                          		if(value.otid>0){
											value.clientname = value.clientname + ' ' + '(OT in '+value.otname+') '
										}
		                          		
		                          		
		                          		if(value.whopay=='Client'){
		                          			
		                          			
		                          			if(value.luhid==value.uhid && value.uhid!=""){
		                          				if(value.notes!=""){
			                          				//showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" ">';
		                          					showtext = value.clientname;
			                          			}else{
			                          				//showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
			                          				showtext = value.clientname;
			                          			}
		                          				
		                          			}else{
		                          				showtext = "N/A";
		                          			}
		                          			
		                          		}else{
		                          			if(value.luhid==value.uhid && value.uhid!=""){
		                          				if(value.notes!=""){
			                          				//showtext = value.clientname + "<br> " +  " "+value.usedsession+" " + ' '+ " "+value.apmttypetext+"" + '<img src="common/images/note.ico" title="'+value.notes+'" ">';
		                          					showtext = value.clientname;
			                          			}else{
			                          				//showtext = value.clientname + "<br> " +  " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br> ";
			                          				showtext = value.clientname;
			                          			}
		                          			}else{
		                          				showtext = "N/A";
		                          			}
		                          			
		                          			
		                          			
		                          		}
		                          		if(value.iscompleted == true){
		                          			showtext = showtext+ "(<a title='Video Call' href='vdyoBookAppointmentAjax' target='_blank'><span class='glyphicon glyphicon-facetime-video'></span></a>)";
		                          			
		                          		
		                          		}
		                          		
		                          	
		                          			
		                          			//if no notes
		                          			if(value.status == 0){
		    									//document.getElementById(etext+'min'+tempStdiff).innerHTML = '<img src="src="common/images/nIcon.png">';
		    									if(value.arrivedstatus == 1){
		    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:#398C10">' +  showtext  +  '</div>';
		    									}else if(value.arrivedstatus == 2){
		    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:#FFFFFF">' +  showtext  +  '</div>';
		    									}else{
		    										
		    										document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black"> <img style="width:12px; height:11px; padding-right: 2px" src="common/images/nIcon.png">' +  showtext  +  '</div>';
		    										
		    									}
		    									
		    								}else{
		    									if(value.notes!=""){
		    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">N/A</div>';
		    									}else{
		    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">N/A</div>';
		    									}
		    								}
		    								
		    								if(value.status==0 && value.dna==true){
		    									//showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"";
		    									
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




var otp = "";
function otpConfirmed(){
	
	document.getElementById('client').onclick = "";
	document.getElementById('addTreatment').style.display = 'none';
	//document.getElementById('vewTreatment').style.display = 'none';
	document.getElementById('bookapmtrdiodiv').style.display = 'none';
	document.getElementById('bookapmtrptdiv').style.display = 'none';
	document.getElementById('paybypatient1').disabled = true;
	document.getElementById('apmtDuration').disabled = true;
	
	
	
	var enteredOtp = document.getElementById('otptxt').value;
	
	var email = document.getElementById('pureemail').value;
	var fname =  document.getElementById('purefname').value;
	var lname = document.getElementById('purelname').value;
	var mob = document.getElementById('puremob').value;
	var dob = document.getElementById('puredob').value;
	var puruhid = document.getElementById('puruhid').value;
	
	if(isotpconfirmd==1){
		otp='ok';
		enteredOtp = 'ok';
	}
	
	if(otp==enteredOtp){
		
		isotpconfirmd = 1;
		
		var url = "puresevaClient?email="+email+"&fname="+fname+"&lname="+lname+"&mob="+mob+"&dob="+dob+"&puruhid="+puruhid+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = otpConfirmedRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}else{
		jAlert('error', 'OTP did not matched!', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	

	
}

function otpConfirmedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			var data = req.responseText;
			var temp = data.split("*");
			var id = temp[0];
			var name = temp[1];
			var pyby = temp[2];
			var type = temp[3];
			var tpid = temp[4];
			if(pyby=='Third Party'){
				pyby = 'tp';
			}
			//setClientName(name,id,type,tpid,pyby,"");
			mobselectedclient = name;
			setClientName(id,type,'',pyby,'')
//			setClientName(id,'null','0','Client','null')
			if(editAppointId!='invalid'){
				 if(editAppointId!=0){
					 $( "#atppopup" ).modal( "hide" );
					 $( "#appointment" ).modal( "hide" );
					 $( "#puremodifyPopup" ).modal( "show" );
					 
				 }else{
					
						 $( "#puremodifyPopup" ).modal( "hide" );
						 $( "#atppopup" ).modal( "hide" );
						 $( "#appointment" ).modal( "show" );
					
					
				 }
			}
			
				 
				 
		}
	}
}
var puresevaclientid=0;
function sendClientOtpAjax(){
	
	
	var email = document.getElementById('pureemail').value;
	
	var email = document.getElementById('pureemail').value;
	var fname =  document.getElementById('purefname').value;
	var lname = document.getElementById('purelname').value;
	var mob = document.getElementById('puremob').value;
	var dob = document.getElementById('puredob').value;
	
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
	
	if(email==''){
		jAlert('error', 'Please enter email.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(validateEmail(email)==false){
			jAlert('error', 'Please enter valid email address.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	
	
	else if(fname=='' && fname!=undefined){
		jAlert('error', 'Please enter first name.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(lname==''){
		jAlert('error', 'Please enter last name.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(mob==''){
		jAlert('error', 'Please enter mobile number.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(dob==''){
		jAlert('error', 'Please enter date of birth.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		
		$( "#loaderPopup" ).modal( "show" );
		
		var url = "otpClient?email="+email+"&mob="+mob+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = sendClientOtpAjaxRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	


}

function sendClientOtpAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			otp = req.responseText;
			$( "#loaderPopup" ).modal( "hide" );
			$( "#puresevaclientdetailsdiv" ).modal( "hide" );
			$( "#atppopup" ).modal( "show" );
		}
	}
}

function setPuresevaExistingClientData(){
	var email = document.getElementById('pureemail').value;
	if(validateEmail(email)==false){
		jAlert('error', 'Please enter valid email address.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "existClient?email="+email+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setPuresevaExistingClientDataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
	
}

function setPuresevaExistingClientDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split('*');
			
			var fname = temp[0];
			var lname = temp[1];
			var mob = temp[2];
			var dob = temp[3];
			
			  document.getElementById('purefname').value = fname;
			 document.getElementById('purelname').value = lname;
			 document.getElementById('puremob').value = mob;
			 document.getElementById('puredob').value = dob;
		}
	}
}


