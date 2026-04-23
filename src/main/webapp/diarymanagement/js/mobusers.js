var isotpconfirmd = 0;
function mobSearch(){
	
	//set wraperdiv element
	/* if(editAppointId!=0){
			getWraperDivChildren(wraperDivId);
	 }*/
	
	//jQuery('.new').children().unwrap();
	 		actionType = 5;
	 		
			var date = document.getElementById("commencing").value;
			var id = document.getElementById('diaryUser').value;
			var locationid = document.getElementById('locationid').value;
			var count = 0;
			/*for(i=1;i<=91;i++){
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
			}*/
			
			
			
			var tempDate = date.split("/");
			var mm = tempDate[1];
			var yy = tempDate[2];
			var week = getWeek(new Date(''+tempDate[2]+'/'+tempDate[1]+'/'+tempDate[0]+''));
			var month = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
			
			
			
			for(j=0;j<=6;j++){
				 var demp = week[j].toString();
				 var temp = demp.split(" ");
				 document.getElementById('wn'+j).innerHTML = temp[0] + " "+ temp[1] + " "+ temp[2];
				 
				 var mm = (parseInt(month.indexOf(temp[1]))+1);
				 if(mm<=9){
					 mm = '0'+mm;
				 }
				 var date = temp[2] +"-"+mm +"-" +temp[3];
				 
				 setmobJsoData(id,date,locationid);
			}
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
                          	
                          	if(value.weekfullname == "Sunday"){
                          		
                         		var col = 1;
                          		//start time diff
                          		var stdiff = parseInt(value.starttime) - value.clinicstime;
                          		stdiff = (parseInt(stdiff) * 7) + parseInt(col);
                          		var tempStdiff = stdiff;
                          		
                          		//end time diff
                          		var etdiff = parseInt(value.endtime) - value.clinicstime;
                          		etdiff = (parseInt(etdiff) * 7) + parseInt(col);
                          		
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
                          		
                          		var size = 7;
                          		
                          		//$('#1').attr('colspan', 3);
								for (i=parseInt(stdiff);i<=parseInt(etdiff);i=(i+size)){
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
												$(this).MessageBox(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,this.title);
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
												
												document.getElementById('diciplineName').value = value.discipline;
											});
											
										
											
											$('#'+jcount+'min'+stdiff).click(function(){
												setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
											});
										/*document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
										}*/
										slength = parseInt(slength) + 1;
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + 7;
											
										}
										
										
									}
									
								setjsonMobAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,value.practitionerid,value.commencing,value.clinicstime);
								
								
								
							}//col 1
							
							if(value.weekfullname == "Monday"){
                          		var col = 2;
                          		setMobWeekTimer(value.weekfullname,2,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 2
							
							if(value.weekfullname == "Tuesday"){
                          		var col = 3;
                          		setMobWeekTimer(value.weekfullname,3,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 3
							
							if(value.weekfullname == "Wednesday"){
                          		var col = 4;
                          		setMobWeekTimer(value.weekfullname,4,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 4
							
							if(value.weekfullname == "Thursday"){
                          		var col = 5;
                          		setMobWeekTimer(value.weekfullname,5,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 5
							
							if(value.weekfullname == "Friday"){
                          		var col = 6;
                          		setMobWeekTimer(value.weekfullname,6,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 6
							
							if(value.weekfullname == "Saturday"){
                          		var col = 7;
                          		setMobWeekTimer(value.weekfullname,7,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline)
							}//col 7
							
							
                        });        
                     });
			
		}
	});
			
			
}	


function setjsonMobAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,commencing){

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
                          	
                          
                        	var size = 7;
                            
                        	var stdiff = parseInt(value.starttime) - 8
                      		stdiff = (parseInt(stdiff) * size) + parseInt(col);
                      		var tempStdiff = stdiff;
                      		var tdhtmldata = "";
                      		
                      
                      
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
                      		
                      		var max=10;
                      		var min=4;
                      		
                      	  var moveLeft = 20;
                          var moveDown = 10;
                          var xymove = 0;
                      		
                      		var random = value.id;
                      		random = random + 's';
                      		
                      		var style = document.createElement('style');
                      		style.type = 'text/css';
                      		style.innerHTML = '.cssClass'+random+' { min-height:6px; cursor:pointer;}';
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
										
										else{
											$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#FCBA63');
											document.getElementById(jcount+'min'+stdiff).onclick = function() {
												if(value.loggedemail==value.clientEmail){
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
													document.getElementById(jcount+'min'+stdiff).onclick = "";
												}
												
											}
										}
									}else if(value.status == 1){
										//$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										document.getElementById(jcount+'min'+stdiff).onclick = function() {
											editAppointId='invalid';
											 $( "#appointment" ).modal( "hide" );
											 $( "#atppopup" ).modal( "hide" );
											 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
										}

										
									
									}/*else{
										document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
											 
										}
									}*/
									
								
									
									slength = parseInt(slength) + 1;
									//tdhtmldata = document.getElementById(stdiff).innerHTML;
									
									if(jcount==55){
										slength = 0;
										divlength = parseInt(divlength) - j;
										j = 0;
										
										stdiff = parseInt(stdiff) + size;
										
										tdhtmldata = document.getElementById(stdiff).innerHTML;
										
									}
									
									
								}
								
								
								
							
								
								
								//wrap all
							/*	var zp = '<div style="width:100px;height:100px;position:absolute;background-color:yellow;top:20px;left:20px;z-index:0;opacity:0.5;border:1px solid #333333;" onclick="zproperty()">z-index 0</div>'
								 $( ".cssClass"+random ).wrapAll( "<div id='new"+random+"'  class='new' ></div>");
								 var resizewidth = $('#wn0').width();
								 var resultwidth = parseInt(resizewidth)+32;
								// alert(resultwidth);
								// resultwidth = resultwidth/4;
								 $(document.getElementById('new'+random)).css('width', resultwidth);
								 if(tdhtmldata!=""){
									 document.getElementById(stdiff).innerHTML = tdhtmldata; 
									 initilizeTdElement(stdiff,diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
									 $(document.getElementById('new'+random)).css('position', 'absolute');
									 //$(document.getElementById('new'+random)).css('width', '95%');
									 
									 //reinitilize wraped element
									 renitilizeWrapdedEvent(stdiff,'new'+random);
								 }*/
								 
								
								// $( "#new"+random ).resizable();
								 
								
								 //modify popup
							/*	 if(value.iscompleted == false){
									
									 document.getElementById('new'+random).onclick = function() {

										 $( "#appointment" ).modal( "hide" );
											wraperDivId = 'new'+random;
											loc = value.locid;
											setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname);
											
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
											 editAppointId = value.id;
											 editwhopay = value.whopay;
											 editClientName = value.clientname;
											 loc = value.locid;
											 diaryuserId = userid;
											 editcondition_id = value.conditionid;
											 editTreatmentEpisode = value.treatmentepisodeid;
											 openCompleteActionPopup(value.id);
										 }else{
											 wraperDivId = 'new'+random;
											 
											 patientId = value.clientId;
											 editAppointId = value.id;
											 editwhopay = value.whopay;
											 editClientName = value.clientname;
											 loc = value.locid;
											 diaryuserId = userid;
											 editcondition_id = value.conditionid;
											 editTreatmentEpisode = value.treatmentepisodeid;
												//document.getElementById('modifyClient2').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href = "ClientLog?id='+patientId+'" target="blank"> Log </a>)';
											 document.getElementById('modifyClient2').innerHTML = '<a href="#" onclick="openClientPrintPopup('+patientId+')">'+editClientName+'</a> <a href="#" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png"/></a>  <a href = "#" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png"/></a>   <a href = "#" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png"/></a>  <a href = "#" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png"/></a>';
	
											 //jAlert('info', 'Invoice has been created for this appointment', 'Info Dialog');
											 $( "#recordpaymentpopup" ).modal( "show" );
										 }
										 
									
									 }
								 }

								 else{

									 document.getElementById('new'+random).onclick = function() {
										 
										 $( "#appointment" ).modal( "hide" );
										 
									 }
								 }*/
								
								
                      			
							
                      
                   		
								 	//write text 
									var stext = parseInt(stemp[1]);
	                          		var etext = parseInt(etemp[1]);
	                          		
	                          		
	                          		var showtext =  "";
	                          		
	                          		
	                          		
	                          		
	                          		if(value.whopay=='Client'){
	                          			
	                          			
	                          			if(value.loggedemail==value.clientEmail && value.clientEmail!=""){
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
	                          			if(value.loggedemail==value.clientEmail && value.clientEmail!=""){
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
	    										}else if(value.lastapmt==1){
	    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black"> <img style="width:12px; height:11px; padding-right: 2px" src="common/images/L-icon.png">' +  showtext  +  '</div>';
	    										}else{
	    										
	    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext  +  '</div>';
	    										}
	    										
	    									}
	    									
	    								}else{
	    									document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + "N/A" + '</div>';
	    								}
	    								
	    								if(value.status==0 && value.dna==true){
	    									showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"";
	    									
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


function setMobWeekTimer(weekfullname,col,starttime,endtime,id,apmtduration,location,onlinebooking,color,tempStdiff,diaryUser,diaryUserId,practitionerid,commencing,locationName,discipline){
	
	//var col = 1;
	//start time diff
	//start time diff
                         		var stdiff = parseInt(starttime) - 8
                         		stdiff = (parseInt(stdiff) * 7) + parseInt(col);
                         		var tempStdiff = stdiff;
                         		
                         		//end time diff
                         		var etdiff = parseInt(endtime) - 8
                         		etdiff = (parseInt(etdiff) * 7) + parseInt(col);
                         		
                         		var i=0;
                         		var j=0;
                         		
                         		var stemp = starttime.split(":");
                         		var etemp = endtime.split(":");
                         		var slength = parseInt(stemp[1])/5;
                         		var elength = parseInt(etemp[1])/5;
                         		
                         		var action = "edit";
                         		
                         		
                         		var timediff = getTimeDifference(starttime,endtime);
                         		var diffemp = timediff.split(":");
                         		var hour = parseInt(diffemp[0])*60;
                         		timediff = hour + parseInt(diffemp[1]);
                         		var divlength = timediff/5;
                         		
                         		//$('#1').attr('colspan', 3);
							for (i=parseInt(stdiff);i<=parseInt(etdiff);i=(i+7)){
								//$(document.getElementById(i)).css('border-bottom', '0px');
								//$(document.getElementById(i)).css('background-color', value.color);
								$(document.getElementById(i)).css('font-size', '12px');
								document.getElementById(i).className = 'cssClass';
								document.getElementById(i).title = id+"/"+diaryUserId;
								document.getElementById(i).onclick = "";
								//document.getElementById(i).ondblclick = function() {
								//	$(this).MessageBox(value.weekfullname,0,action,value.id,stdiff,value.starttime,value.endtime,value.apmtduration,value.location,value.onlinebooking,time);
								//}
								
							}
							
							
								var jcount = 0;
								
								divlength = divlength - 1;
								
								for(j=0;j<=divlength;j++){
									jcount = 5*parseInt(slength);
									//aqua
									document.getElementById(jcount+'min'+stdiff).className = 'cssClass1';
									
									$(document.getElementById('30min'+stdiff)).css('background-image', 'url("diarymanagement/img/line.png")');
									$(document.getElementById('30min'+stdiff)).css('background-repeat', 'repeat-x');
									
									//$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#fcf8e3');
									$(document.getElementById(jcount+'min'+stdiff)).css('background-color', color);
									
									
									newTitle = "";
									mytitle = "";
									var tmtitle = document.getElementById(jcount+'min'+stdiff).title;
									tmtitle = tmtitle.split(' ');
									if(tmtitle.length > 1){
										document.getElementById(jcount+'min'+stdiff).title = tmtitle[0];
									}
									
									 mytitle = document.getElementById(jcount+'min'+stdiff).title;
									 newTitle = mytitle + ' ' + '('+locationName+')';
									document.getElementById(jcount+'min'+stdiff).title = newTitle;
									
									$('#'+jcount+'min'+stdiff).click(function(){
										$(this).MessageBox(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,this.title);
										
										document.getElementById('diciplineName').value = discipline;
										
										// $( "#appointment" ).modal( "show" );
										if(isotpconfirmd==0){
											 $( "#puresevaclientdetailsdiv" ).modal( "show" );
										}else{
											// $( "#appointment" ).modal( "show" );
											otpConfirmed();
										}
										
										// sendClientOtpAjax();
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
										editCommencing = commencing;
										slotstarttime = starttime;

										document.getElementById('date').value = commencing;
										document.getElementById('blockdate').value = commencing;
											
										//set cookie commencing
										document.cookie = "cookiecommencing=" + commencing;
										document.cookie = "cookiePractitionerId=" + diaryUserId;
										
										
									});
									
									
									
									$('#'+jcount+'min'+stdiff).click(function(){
										setGlobalData(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,commencing,this.title);
									});
									slength = parseInt(slength) + 1;
									if(jcount==55){
										slength = 0;
										divlength = parseInt(divlength) - j;
										j = 0;
										stdiff = parseInt(stdiff) + 7;
										
									}
									
									
								}
								
							setjsonMobAvailableData(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,col,practitionerid,commencing);
								
	
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
	
	
	if(isotpconfirmd==1){
		otp='ok';
		enteredOtp = 'ok';
	}
	
	if(otp==enteredOtp){
		
		isotpconfirmd = 1;
		
		var url = "puresevaClient?email="+email+"&fname="+fname+"&lname="+lname+"&mob="+mob+"&dob="+dob+" ";

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
			setClientName(name,id,type,tpid,pyby,"");
			
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


function validateEmail(emailField){
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(emailField);

}