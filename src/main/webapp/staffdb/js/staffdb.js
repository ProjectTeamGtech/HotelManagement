	/*var style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = '.cssClass {padding: 0px; margin: 0px; height:100%; text-align:center }';
	document.getElementsByTagName('head')[0].appendChild(style);
	
	*/
	
var style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = '.cssClassstaffdb1 { min-height:10px; min-width:99px; }';
	document.getElementsByTagName('head')[0].appendChild(style);
	var time=0;

function setjsonDataAllUser(diaryUserId,commencing,col,size,location){
	
	//document.getElementById('diaryUser').value = diaryUserId;
	
	
	$.ajax({
		url: "diarymanagement/pages/JQGridMasterAllDiaryUser.jsp?diaryuserid="+diaryUserId+"&commencing="+commencing+"&location="+location+" " ,
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
                          	//alert(value.id+"/"+diaryUserId);
                        
                          
                          var stdiff = parseInt(value.starttime) - value.clinicstime;
                          		stdiff = (parseInt(stdiff) * size) + parseInt(col);
                          		var tempStdiff = stdiff;
                          		
                          		//end time diff
                          		var etdiff = parseInt(value.endtime) - value.clinicstime;
                          		etdiff = (parseInt(etdiff) * size) + parseInt(col);
                          		
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
									
									divlength = divlength - 1;
									
										for(j=0;j<=divlength;j++){
										
										jcount = 5*parseInt(slength);
										
										document.getElementById(jcount+'min'+stdiff).className = 'cssClassstaffdb1';
										
										//document.getElementById(jcount+'min'+stdiff).innerHTML = '<span  style="opacity: 0.3; font-size:4px;">Result</span>';
										
										$(document.getElementById('30min'+stdiff)).css('background-image', 'url("diarymanagement/img/line.png")');
										$(document.getElementById('30min'+stdiff)).css('background-repeat', 'repeat-x');
										
										//$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#fcf8e3');
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
										
										document.getElementById(jcount+'min'+stdiff).onclick = function() {
													$(this).MessageBox(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,this.title);
													//Open Pop up 
													clearFiledCommonAction();
													
													$( "#blockdiv" ).modal( "show" );
													
													
													editAppointId = 0;
													editCommencing = value.commencing;
													slotstarttime = value.starttime;
												//set cookie commencing
												document.cookie = "cookiecommencing=" + commencing;
												document.cookie = "cookiePractitionerId=" + diaryUserId;
													
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
												
												
												
												//radio button coding
												setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
												
												
												var temp = commencing.split('/');
												var date = temp[2] + '-' + temp[1] + '-' + temp[0];
												
												document.getElementById('date').value = date;
												
												document.getElementById('diciplineName').value = value.discipline;
												
												
													//set book appointment heading
			
													document.getElementById('apmtwithz').innerHTML = document.getElementById('user').value; 
													document.getElementById('diciplinez').innerHTML = $("#diciplineName option:selected").text();
													document.getElementById('locationz').innerHTML = $("#location option:selected").text();
													document.getElementById('datez').innerHTML = date;
													
													document.getElementById('apmtwithbz').innerHTML = document.getElementById('user').value; 
													document.getElementById('locationbz').innerHTML = $("#location option:selected").text();
													document.getElementById('datebz').innerHTML = date;
												
												showBlockingDialogBox();
												
										}
										
										slength = parseInt(slength) + 1;
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + size;
											
										}
										
										
									}
									
									
									
									
								setjsonAllUserAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,diaryUserId,size,value.commencing,value.clinicstime);
								
								
                          	
                        
                          	
                      });        
                     });
			
		}
	});
}

function setjsonAllUserAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,size,commencing,clinicstime){

	/*var clientid = document.getElementById('dashclientId').value;
	var mobile = document.getElementById('mobiledata').value;*/

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
                          	//alert(value.id)
                        	//alert(value.conditionid);
                          		var stdiff = parseInt(value.starttime) - clinicstime;
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
                          		style.innerHTML = '.cssClass'+random+' { min-height:10px; min-width:99px; cursor:pointer;}';
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
												
											}else if(value.otid>0){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(173, 220, 255)');
												
											}
											
											else{
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#FCBA63');
											}
											document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
												
												var count  = 0;
												var globalYmove = 0;
											
												/*$('#new'+random).draggable({
													// get the initial X and Y position when dragging starts
												   start: function(event, ui) {
												      xpos = ui.position.left;
												      ypos = ui.position.top;
												       count=0;
												     
												    },
												    drag: function() {
												    	count++;
												    	// $('div#pop-up').hide();
												    	//document.getElementById('xytext').value =  count;
												    },

												    // when dragging stops
												    stop: function(event, ui) {
												      // calculate the dragged distance, with the current X and Y position and the "xpos" and "ypos"
												      var xmove = ui.position.left - xpos;
												      var ymove = ui.position.top - ypos;
												      //count = 0;

												      // define the moved direction: right, bottom (when positive), left, up (when negative)
												      var xd = xmove >= 0 ? ' right: ' : '  left: ';
												      var yd = ymove >= 0 ? ' Bottom: ' : ' Up: ';
												     
												     
												      
												     // document.getElementById('xytext').value =  xd+ xmove+ ' pixels \n'+ yd+ ymove+ ' px,'+'user:'+count;
												      
												      //alert(read_cookie("cookieddpractitionerid"));
												      var practitionerid = read_cookie("cookieddpractitionerid");
												      getWraperDivChildren('new'+random);
												      setDragAndDropAjaxData(practitionerid,value.starttime,value.duration,ymove,value.id,value.locid);
												      
												      if(value.iscompleted == true){
												    	  $("#new"+random).draggable({ disabled: false });
														}else{
															$("#new"+random).draggable({ disabled: true });
														}
												      
												    }
												  });

												$( "#allusertable td" ).droppable({
													 	accept: '#new'+random,
													    activeClass: 'ui-state-hover',
													    hoverClass: 'ui-state-active',
													drop: function( event, ui ) {
														
														 var xmove = ui.position.left - xpos;
													      var ymove = ui.position.top - ypos;
													      //count = 0;

													      // define the moved direction: right, bottom (when positive), left, up (when negative)
													      var xd = xmove >= 0 ? ' right: ' : '  left: ';
													      var yd = ymove >= 0 ? ' Bottom: ' : ' Up: ';
													     
													     
													      
													      document.getElementById('xytext').value =  xd+ xmove+ ' pixels \n'+ yd+ ymove+ ' px,'+'user:'+count;
														//alert(document.getElementById('new'+random).innerHTML)
														
														
														newCell = $(this).attr("id");
														newCellTitle = $(this).attr("title");
														document.cookie = "cookieddpractitionerid=" + newCellTitle;
														
														
														
														if(getChildren(newCell) != "cssClassstaffdb1"){
															$("#new"+random).draggable({ revert: 'valid' });
																
															
														}else if(getChildren('new'+random) == "new ui-draggable"){
															$("#new"+random).draggable({ revert: 'valid' });
															
														}else if(getChildren('new'+random) == "new ui-draggable ui-draggable-disabled ui-state-disabled"){
															$("#new"+random).draggable({ revert: 'valid' });
														}
														else{
															$("#new"+random).draggable({ revert: 'invalid' });
														}
														
													}
												});
												if(value.iscompleted == true){
													$("#new"+random).draggable({ revert: 'valid' });
													$("#new"+random).draggable({ disabled: true });
												}else if(value.status==0 && value.dna==true){
													$("#new"+random).draggable({ disabled: true });
												}else if(value.arrivedstatus == 2){
													$("#new"+random).draggable({ disabled: true });
												}else if(value.otid>0){
													$("#new"+random).draggable({ disabled: true });
												}else{
													$("#new"+random).draggable({ disabled: false });
												}*/

											}
											
											/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
												wraperDivId = 'new'+random;
												setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
												
											}*/
										}else if(value.status == 1){
											
											if(value.work==0){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#22beef');
											}else{
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#339966');
												$(document.getElementById(jcount+'min'+stdiff)).css('color', '#ffffff');
											}
											
											document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
												var count  = 0;
												var globalYmove = 0;
											/*	$('#new'+random).draggable({
												    // get the initial X and Y position when dragging starts
												   start: function(event, ui) {
												      xpos = ui.position.left;
												      ypos = ui.position.top;
												       count=0;
												       
												    },
												    drag: function() {
												    	
												    	count++;
												    	
												    	//document.getElementById('xytext').value =  count;
												    },

												    // when dragging stops
												    stop: function(event, ui) {
												      // calculate the dragged distance, with the current X and Y position and the "xpos" and "ypos"
												      var xmove = ui.position.left - xpos;
												      var ymove = ui.position.top - ypos;
												      //count = 0;

												      // define the moved direction: right, bottom (when positive), left, up (when negative)
												      var xd = xmove >= 0 ? ' right: ' : '  left: ';
												      var yd = ymove >= 0 ? ' Bottom: ' : ' Up: ';
												     
												      xymove = xmove + '/' + ymove;
												      
												    //  document.getElementById('xytext').value =  xd+ xmove+ ' pixels \n'+ yd+ ymove+ ' px,'+'user:'+count;
												      
												      //alert(read_cookie("cookieddpractitionerid"));
												      var practitionerid = read_cookie("cookieddpractitionerid");
												      getWraperDivChildren('new'+random);
												      setDragAndDropAjaxData(practitionerid,value.starttime,value.duration,ymove,value.id,value.locid);
												      
												    }
												  });

												$( "#allusertable td" ).droppable({
													 	accept: '#new'+random,
													    activeClass: 'ui-state-hover',
													    hoverClass: 'ui-state-active',
													drop: function( event, ui ) {
														
													
														
														
														newCell = $(this).attr("id");
														newCellTitle = $(this).attr("title");
														document.cookie = "cookieddpractitionerid=" + newCellTitle;
														
														
														if(getChildren(newCell) != "cssClass1"){
															$("#new"+random).draggable({ revert: 'valid' });
															
															
														}else if(getChildren('new'+random) == "new ui-draggable"){
															$("#new"+random).draggable({ revert: 'valid' });
															
														}else if(getChildren('new'+random) == "new ui-draggable ui-draggable-disabled ui-state-disabled"){
															$("#new"+random).draggable({ revert: 'valid' });
														}else{
															$("#new"+random).draggable({ revert: 'invalid' });
														}
														
													}
												});

*/
											}
											
											/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
												wraperDivId = 'new'+random;
												setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
												
											}*/
										}else{
											document.getElementById(jcount+'min'+stdiff).onclick = function() {
												setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
												 $( "#blockdiv" ).modal( "hide" );
											}
										}
										
										  if(value.chargecompleted==true){
												$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#cccccc');
											
											}
									
										
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
										 initilizeTdElementstaff(stdiff,diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
										 
										 $(document.getElementById('new'+random)).css('position', 'absolute');
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
									// if(value.iscompleted == false){
										
										 document.getElementById('new'+random).onclick = function() {

											 $( "#blockdiv" ).modal( "hide" );
												wraperDivId = 'new'+random;
												 loc = value.locid;
												 
												 if(value.otid==0){
													setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname,value.imagename);
												 }else{
												 	
												 	showotmodifypopuop(value.id,value.clientId,value.clientname,value.location,value.whopay,value.imagename);
												 	$( "#blockdiv" ).modal( "hide" );
												 }
											}
									// }
		/*							 else if(value.iscompleted == true){
										 wraperDivId = 'new'+random;
										

										 patientId = value.clientId;
										 editClientName = value.clientname;
										 editwhopay = value.whopay;
										 editTreatmentEpisode = value.treatmentepisodeid;
										
										 document.getElementById('new'+random).onclick = function() {
											 wraperDivId = 'new'+random;

											 $( "#blockdiv" ).modal( "hide" );	
											 
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
												 document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3"><a href="#" style="min-width: 146px !important;margin-left: 0px;margin-bottom: 15px;background-color: #424A5D !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;min-height: 58px !important;line-height: 1.42857;" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" style="margin-bottom: 4px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" /><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Edit</p></a> <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" /><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Log</p></a>  <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" /><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">EMR</p></a>  <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" /><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Forms</p></a></div>';
													
													
												 //jAlert('info', 'Invoice has been created for this appointment', 'Info Dialog');
												 $( "#recordpaymentpopup" ).modal( "show" );
											 }
											 
										
										 }
									 }*/

									/* else{

										 document.getElementById('new'+random).onclick = function() {
											 
											 $( "#blockdiv" ).modal( "hide" );
											 
										 }
									 }*/
									
                          			
									
                          
                       		
                          	//write text 
								var stext = parseInt(stemp[1]);
                          		var etext = parseInt(etemp[1]);
                          		
                          		
                          		var showtext =  "";
                          		
                          		
								if(value.otid>0){
									value.clientname = value.clientname + ' ' + '(OT in '+value.otname+') '
								}
                          		
                          		if(value.whopay=='Client'){
                          			if(value.notes!=""){
                          				if(value.bal>0){
                          					showtext = value.clientname + "<span class='selbal' title='Balance="+currencySign+value.bal+"'>"+currencySign+"</span><br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" ">';
                          				}else{
                          					showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" ">';
                          				}
                          				
                          			}else{
                          				if(value.bal>0){
                          					showtext = value.clientname + "<span class='selbal' title='Balance="+currencySign+value.bal+"'>"+currencySign+"</span><br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
                          				}else{
                          					showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
                          				}
                          				
                          			}
                          			
                          		}else{
                          			if(value.notes!=""){
                          				showtext = value.clientname + "<br> " +  " "+value.usedsession+" " + ' '+ " "+value.apmttypetext+"" + '<img src="common/images/note.ico" title="'+value.notes+'" ">';
                          			}else{
                          				showtext = value.clientname + "<br> " +  " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br> ";
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
    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green"> <img style="width:12px; height:11px; padding-right: 2px" src="common/images/nIcon.png">' +  showtext  +  '</div>';
    										}else if(value.lastapmt==1){
    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green"> <img style="width:12px; height:11px; padding-right: 2px" src="common/images/L-icon.png">' +  showtext  +  '</div>';
    										}else{
    										
    											document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext  +  '</div>';
    										}
    										
    									}
    									
    								}else{
    									if(value.notes!=""){
    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:white">' + value.notes + ","+value.starttime+"-" + ""+value.endtime+" " + '<img align="" src="common/images/note.ico" title="'+value.notes+'" ">'+ '</div>';
    									}else{
    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:white">' +value.starttime+"-" + ""+value.endtime+" " +  '</div>';
    									}
    									
    									//document.getElementById(stext+'min'+tempStdiff).title = value.notes + "," + "["+value.apmttype+"]";
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


function showNoteMsg(){
	
	alert("hello")
}



/*function getChildren(el) {
    var container = document.getElementById(el);
    var childArray = container.children;
    //var results = document.getElementById('results');
    //results.innerHTML = container.id+" has "+childArray.length+" children.<br />";
    for(var i = 0; i < childArray.length; i++){
       // results.innerHTML += childArray[i].id+"<br />";
        //childArray[i].style.color = "#F00";
       // alert(childArray[i].id)
    	
    	var className = $('#'+childArray[i].id+'').attr('class');
    	
    	  return className;
    }
}

function setDragAndDropAjaxData(practitionerid,starttime,duration,ymove,availableSlotID,location){
	//alert(practitionerid);
	//starttime = getTimeTotal(duration,starttime);
	var evalTime = 0;
	var endTime = 0;
	if(ymove > 0){
		if(ymove%6 == 0){
			var px = ymove/6;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeTotal(minute,starttime);
		}else{
			for(i=1;i<=6;i++){
				ymove++;
				if(ymove%6==0)
					break;
			}
			var px = ymove/6;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeTotal(minute,starttime);
		}
		
	}else{
		
		if(ymove%6 == 0){
			var px = ymove/6;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeSubstration(starttime,minute);
		}else{
			for(i=1;i<=6;i++){
				ymove++;
				if(ymove%6==0)
					break;
			}
			var px = ymove/6;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeSubstration(starttime,minute);
		}
		
	}
	
	endTime = getTimeTotal(evalTime,duration);
	
	if(evalTime < '08:00:00' || evalTime > '20:00:00'){
		//alert(evalTime+'/'+endTime);
		$("#new"+availableSlotID+'s').draggable({ revert: 'valid' });
	}else{
		submitDragAndDropAjax(practitionerid,evalTime,availableSlotID,endTime,location);
	}
	
	//alert(evalTime+'/'+endTime);
}


function minutesToStr(minutes) {
	 var sign ='';
	 if(minutes < 0){
	  sign = '-';
	 }

	 var hours = leftPad(Math.floor(Math.abs(minutes) / 60));
	 var minutes = leftPad(Math.abs(minutes) % 60);

	 return  hours +':'+minutes ;

	}


function leftPad(number) {
	 return ((number < 10 && number >= 0) ? '0' : '') + number;
}
*/
