var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;


$(document).ready(function(){
	
	$("#dischargedate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
	
	$("#editdischargedate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
	
	var treatmentEpisodeid = document.getElementById('treatmentEpisodeid').value;
	if(treatmentEpisodeid!=0){
		var id = document.getElementById('apmtId').value;
		/*if(id!='0'){
			document.getElementById(id).checked = true;*/
		//}
		
	}	
	
	patientId = document.getElementById('clientname').value;
	diaryuserId = document.getElementById('diaryUser').value;
	editcondition_id = document.getElementById('condition').value;
	//  03-09-2019 above called on click
	//set all prescription
	//getEmrClientAllPriscriptionData();
	
	
 $( "#commencing" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
		 changeYear: true	 
	 });
	
	
	
	var action = document.getElementById('hdnaction').value;
	
	action = 'emr';
	
	if(action=='ad'){
		document.getElementById('AD').className = 'tab-pane active';
		document.getElementById('EMR').className = 'tab-pane';
		//getSearch();
	}else if(action=='emr'){
		document.getElementById('AD').className = 'tab-pane';
		document.getElementById('EMR').className = 'tab-pane active';
	}else{
		document.getElementById('AD').className = 'tab-pane active';
		document.getElementById('EMR').className = 'tab-pane';
		
		
		
		/*$(document.getElementById('adid')).css('background-color', '#FCBA63');
		$(document.getElementById('emrid')).css('background-color', '#ffffff');*/
	}
	
	
});




function getEmrClientAllPriscriptionData(){
	//03-09-2019   commented and link change
	/*var url = "vieemrwallclientpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";*/
	var url = "vieemrwallclientpriscBookAppointmentAjax?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getEmrClientAllPriscriptionDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function getEmrClientAllPriscriptionDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			/*document.getElementById('alldataprisctable').innerHTML = req.responseText;*/
			document.getElementById('emrprecrdiv').innerHTML = req.responseText;
			$("#presscription_details").modal('show');
			//03-09-2019   commented below 
			//shoe investigation list
			//getEmrinvestigationViewList();
		}
	}
	
}


function clincalnoteselements(){
	var url='getclinical_notes_new_list_to_emrCommonnew?cid='+patientId+'&pid='+diaryuserId;
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = clincalnoteselementsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function clincalnoteselementsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('clinicaln').innerHTML = req.responseText;
			$("#clinicalass").modal('show');
			
		}
	}
}

function getEmrinvestigationViewList(){
	/*var url = "viewemrInvestigation?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";*/
	var url = "viewemrBookAppointmentAjax?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getEmrinvestigationViewListRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function getEmrinvestigationViewListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			/*document.getElementById('allinvesttable').innerHTML = req.responseText;
			document.getElementById('alleditinvesttable').innerHTML = req.responseText;*/
			document.getElementById('emrinvstdiv').innerHTML = req.responseText;
			//03-09-2019   added coe for onclick instead of onload
			$("#investigation_details").modal('show');

		}
	}

	
}





function getSearch(){
	

	
	
	jQuery('.new').children().unwrap();
	 		
	 		
			var date = document.getElementById("commencing").value;
			//var date = '11/02/2015';
			var id = document.getElementById('diaryUser').value;
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
				 
				 setJsoData(id,date);
			}
			//setJsoData(id,date);
			
			
}




function getWeek(fromDate){
	 
	 var sunday = new Date(fromDate.setDate(fromDate.getDate()-fromDate.getDay()))
	    ,result = [new Date(sunday)];
	 while (sunday.setDate(sunday.getDate()+1) && sunday.getDay()!==0) {
	  result.push(new Date(sunday));
	 }
	 return result;
	}



function setJsoData(diaryUserId,date){

	$.ajax({
		url: "diarymanagement/pages/JQGridMasterNotAvailableSlot.jsp?diaryUserId="+diaryUserId+"&date="+date+" " ,
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
                          	
                          	
                          	
                       
                          	
                          	if(value.weekfullname == "Sunday"){
                          		
                         		var col = 1;
                          		//start time diff
                          		var stdiff = parseInt(value.starttime) - 8
                          		stdiff = (parseInt(stdiff) * 7) + parseInt(col);
                          		var tempStdiff = stdiff;
                          		
                          		//end time diff
                          		var etdiff = parseInt(value.endtime) - 8
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
											
											document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
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
													
												
											
										}
										document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,value.commencing,this.title);
										}
										slength = parseInt(slength) + 1;
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + 7;
											
										}
										
										
									}
									
								setjsonAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,value.practitionerid,value.commencing);
								
								
								
							}//col 1
							
							if(value.weekfullname == "Monday"){
                          		var col = 2;
                          		setWeekTimer(value.weekfullname,2,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 2
							
							if(value.weekfullname == "Tuesday"){
                          		var col = 3;
                          		setWeekTimer(value.weekfullname,3,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 3
							
							if(value.weekfullname == "Wednesday"){
                          		var col = 4;
                          		setWeekTimer(value.weekfullname,4,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 4
							
							if(value.weekfullname == "Thursday"){
                          		var col = 5;
                          		setWeekTimer(value.weekfullname,5,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 5
							
							if(value.weekfullname == "Friday"){
                          		var col = 6;
                          		setWeekTimer(value.weekfullname,6,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 6
							
							if(value.weekfullname == "Saturday"){
                          		var col = 7;
                          		setWeekTimer(value.weekfullname,7,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName)
							}//col 7
							
							
                        });        
                     });
			
		}
	});
			
			
}	


function setjsonAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,commencing){

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
                      		style.innerHTML = '.cssClass'+random+' { min-height:6px; min-width:99px; cursor:pointer;}';
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
										
										else{
											$(document.getElementById(jcount+'min'+stdiff)).css('background-color', '#FCBA63');
										}
										/*document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
											
											var count  = 0;
											var globalYmove = 0;
											$('#new'+random).draggable({
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
													
													
													
													if(getChildren(newCell) != "cssClass1"){
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
											}
											else{
												$("#new"+random).draggable({ disabled: false });
											}

										}*/
										

										
										/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
											wraperDivId = 'new'+random;
											setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
											
										}*/
									}else if(value.status == 1){
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										
										document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
											
											var count  = 0;
											var globalYmove = 0;
											$('#new'+random).draggable({
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


										}
										//document.getElementById(jcount+'min'+stdiff).style.display = 'none';
										//document.getElementById('new'+random).style.display = 'none';
										
									
									}else{
										/*document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
											 
										}*/
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
									 renitilizeWrapdedEvent(stdiff);
								 }
								 
								
								// $( "#new"+random ).resizable();
								 
								
								 //modify popup
								 if(value.iscompleted == false){
									
									 document.getElementById('new'+random).onclick = function() {

										 $( "#appointment" ).modal( "hide" );
											wraperDivId = 'new'+random;
											//setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname);
											 if(value.status==0){
												 document.getElementById('hdnaction').value = 'emr';
												 document.getElementById('clientname').value = value.clientId;
												 document.getElementById('conditionsApmt').value = value.conditionid;
												 
												 getPatientRecord();
											 }else{
												 document.getElementById('hdnaction').value = 'ad';
												alert('Blocked Appointment');
											 }
											 
											
										}
								 }
								 else if(value.iscompleted == true){

									 patientId = value.clientId;
									 editClientName = value.clientname;
									 editwhopay = value.whopay;
									
									 document.getElementById('new'+random).onclick = function() {

										 $( "#appointment" ).modal( "hide" );	
										 
										 if(value.chargecompleted==false){
											 //editCompleteApmt(value.id);
											 patientId = value.clientId;
											 editAppointId = value.id;
											 editwhopay = value.whopay;
											 editClientName = value.clientname;
											 loc = value.locid;
											// openCompleteActionPopup(value.id);
											 document.getElementById('hdnaction').value = 'emr';
											 document.getElementById('clientname').value = value.clientId;
											 document.getElementById('conditionsApmt').value = value.conditionid;
											 getPatientRecord();
										 }else{
											 patientId = value.clientId;
											 editAppointId = value.id;
											 editwhopay = value.whopay;
											 editClientName = value.clientname;
											 loc = value.locid;
												document.getElementById('modifyClient2').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href = "ClientLog?id='+patientId+'" target="blank"> Log </a>)';
	
											 //jAlert('info', 'Invoice has been created for this appointment', 'Info Dialog');
											// $( "#recordpaymentpopup" ).modal( "show" );
												document.getElementById('hdnaction').value = 'emr';
												 document.getElementById('clientname').value = value.clientId;
												 document.getElementById('conditionsApmt').value = value.conditionid;
												getPatientRecord();
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
	                          		
	                          		
	                          		
	                          		
	                          		if(value.whopay=='Client'){
	                          			if(value.notes!=""){
	                          				showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+" "+ '<img src="common/images/note.ico" title="'+value.notes+'" ">';
	                          			}else{
	                          				showtext = value.clientname + "<br> "  + " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br>";
	                          			}
	                          			
	                          		}else{
	                          			if(value.notes!=""){
	                          				showtext = value.clientname + "<br> " +  " "+value.usedsession+" " + ' '+ " "+value.apmttypetext+"" + '<img src="common/images/note.ico" title="'+value.notes+'" ">';
	                          			}else{
	                          				showtext = value.clientname + "<br> " +  " "+value.usedsession+"" + ' '+ " "+value.apmttypetext+"<br> ";
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
	    									document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + value.reasonforblock + ","+value.starttime+"-" + ""+value.endtime+" " + '</div>';
	    									//document.getElementById(stext+'min'+tempStdiff).title = value.notes + "," + "["+value.apmttype+"]";
	    								}
	    								
	    								if(value.status==0 && value.dna==true){
	    									//document.getElementById(stext+'min'+tempStdiff).style.color = "Red";
	    									document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green" style="color:black">' +  showtext +  '</div>';
	    								}
	                          		
									
							
	    								//setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
	    								
	    							/*	if(document.getElementById('clientname').value!=value.clientId){
	    									document.getElementById('new'+random).style.display = 'none';
	    								}else{
	    									document.getElementById('new'+random).style.display = '';
	    								}*/
                    
                      	
                  });        
                 });

			
		}
	});
	
	
	 
}



function setWeekTimer(weekfullname,col,starttime,endtime,id,apmtduration,location,onlinebooking,color,tempStdiff,diaryUser,diaryUserId,practitionerid,commencing,locationName){
	
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
									

									
									document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
												//$(this).MessageBox(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,this.title);
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
												editCommencing =value.commencing;
												slotstarttime = starttime;

												document.getElementById('date').value = commencing;
												document.getElementById('blockdate').value = commencing;
													
												//set cookie commencing
												document.cookie = "cookiecommencing=" + commencing;
												document.cookie = "cookiePractitionerId=" + diaryUserId;
												
												
												
										
									}
									/*document.getElementById(jcount+'min'+stdiff).onclick = function() {
										setGlobalData(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,commencing,this.title);
									}*/
									slength = parseInt(slength) + 1;
									if(jcount==55){
										slength = 0;
										divlength = parseInt(divlength) - j;
										j = 0;
										stdiff = parseInt(stdiff) + 7;
										
									}
									
									
								}
								
							setjsonAvailableData(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,col,practitionerid,commencing);
								
	
}


function getTimeDifference(startTime,endTime){
	var result = "";
	
   var startTimeArray = startTime.split(":");
   var startInputHrs = parseInt(startTimeArray[0]);
   var startInputMins = parseInt(startTimeArray[1]);

   var endTimeArray = endTime.split(":");
   var endInputHrs = parseInt(endTimeArray[0]);
   var endInputMins = parseInt(endTimeArray[1]);

   var startMin = startInputHrs*60 + startInputMins;
   var endMin = endInputHrs*60 + endInputMins;

   var result;

   if (endMin < startMin) {
       var minutesPerDay = 24*60; 
       result = minutesPerDay - startMin;  // Minutes till midnight
       result += endMin; // Minutes in the next day
   } else {
      result = endMin - startMin;
   }

   var minutesElapsed = result % 60;
   var hoursElapsed = (result - minutesElapsed) / 60;

  result =  hoursElapsed + ":" + (minutesElapsed < 10 ? '0'+minutesElapsed : minutesElapsed)  ;
  
  return result;

}


function renitilizeWrapdedEvent(el){
	
	 var container = document.getElementById(el);
	    var childArray = container.children;
	    
	  /* document.getElementById('new15s').onclick = function() {
		   getrewrapedDataAjax('new15s');
	    }*/
	    
	    
	    for(var i = 0; i < childArray.length; i++){
	    	var className = $('#'+childArray[i].id+'').attr('class');
	    	if(className=='new'){
	    		var eventid = childArray[i].id;
	    		document.getElementById(eventid).onclick = function() {
	    			//alert('hello');
	    			getrewrapedDataAjax(eventid);
	    			
	    			
	    			//setModifyPopup(status, starttime, endtime, duration, clientname, notes, apmttype, id, arrivedstatus, dna, userid, clientId, commencing, practitionerEmail, clientEmail, charge, commencing, starttime, treatmentepisodeid, iscompleted, diaryuserid, apmttypetext, location, conditionid, tptypeid, tpnameid, whopay);
	    		}
	    		
	    	}
	    }
}



function getcaldate(date){
	/*document.getElementById('caldate').value = date;
	document.getElementById('selecteduserid').value = document.getElementById('diaryUser').value;*/
	document.getElementById('hdnaction').value = 'ad';
	document.getElementById('caldate').value = date;
	getPatientRecord();
}


function setSelectedDiaryUser(id){
	document.getElementById('caldate').value = document.getElementById('commencing').value;
	document.getElementById('selecteduserid').value = id;
}


