var tempCount = 0;
var month = 0;
var ct = 0;
var year = 0;
var startValue = 1;
var editDiarymanagementid = 0;

/* 
 * hide or show dialog box on delete event
 * 
 * */
displayTooltip = false;
var messageDelay = 10000;
jQuery.fn.center = function () {
		
		this.css("position","absolute");
		//this.css("top", ( $(window).height() - this.height() ) / 2+$(window).scrollTop() + "px");
		this.css("left", ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + "px");
		
		return this;
	};
	

	
	
$(document).ready(function(){
	/*$("#previewDiv").hide();
	$("#previewDiv").hide();*/
	//document.getElementById('slotpopuptable').style.display = '';
	//document.getElementById('slotpopupdiv').style.display = '';
	
	
	
	//set up diary frozen
	 $('#freeze-table').freezeTableColumns({
        width:       1000,   // required
        height:      400,   // required
        numFrozen:   1,     // optional
        frozenWidth: 100,   // optional
        clearWidths: false,  // optional
      });
	 
	 
	
	
});		
	
	
function goClose(){
	document.getElementById('hdnactionid').value = "";
	$("#background").fadeOut("slow");
	$("#previewDiv").hide();
	document.getElementById('fullcalfrm').submit();
	//window.location.reload();
	//document.getElementById('calander').style.display = "";
	//$(document.getElementById("c"+tempCount)).css('background-color', '');
	
	
	
}

var tempusername = "";
var tempdate = "";
var tempyear = "";
var tempdiaryuserid = "";
var temptdcode = "";


function getWeek(fromDate){
	var curr = fromDate;
	var firstday = new Date(curr.setDate(curr.getDate() - curr.getDay()));
	var lastday = new Date(curr.setDate(curr.getDate() - curr.getDay()-6));
	
	
	return lastday;
	}


function goPreview(username,date,year,diaryuserid,tdcode){
	
	var monnthArray = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var str = date.split(" ");
	
	var a = monnthArray.indexOf(str[0]);
	a = a+1;
	if(parseInt(a)<=9){
		a = '0'+a;
	}
	
	var selectedDate =  year + "/" + a + "/" + str[1];
	var currentdate = getCurrentDate();
	var lastdate = getWeek(new Date(currentdate));
	
	
	var preDate = parseInt(str[0]) - 6;
	var commencing = year + "/" + str[0] + "/" + str[1];
	
	
	/*if (new Date(commencing) <= new Date(lastdate)) {
            jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

	}else{*/
		tempusername = username;
	tempdate = date;
	tempyear = year;
	tempdiaryuserid = diaryuserid;
	temptdcode = tdcode;
	
	
/*	$("#background").css({"opacity" : "0.7"}).fadeIn("slow");			
	$("#previewDiv").center().fadeIn("slow");*/
	
	
	$( "#appointmentTableDiv2" ).modal( "show" );
	
	document.getElementById('diaryUser').value = username;
	document.getElementById('diaryuserid').value = diaryuserid;
	document.getElementById('tdcode').value = tdcode;
	
	var str = date.split(" ");
	var commencing = str[1] + "/" + str[0] + "/" + year;
	document.getElementById('commencing').value = commencing;
	
	var caldate = parseInt(str[1]);
	
	
	document.getElementById('mon_id').innerHTML = "Monday"  + "<br> " + caldate + " " + str[0];
		caldate = caldate+1;
		month = getMonthName(caldate,str[0],year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('tue_id').innerHTML = "Tuesday"  + " <br>" + caldate + " " + month;
		caldate = caldate+1;
		month = getMonthName(caldate,month,year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('wed_id').innerHTML = "Wednesday" + " <br>" + caldate + " " + month;;
		caldate = caldate+1;
		month = getMonthName(caldate,month,year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('thu_id').innerHTML = "Thursday" + "<br> " + caldate + " " + month;;
		caldate = caldate+1;
		month = getMonthName(caldate,month,year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('fri_id').innerHTML = "Friday" + " <br>" + caldate + " " + month;;
		caldate = caldate+1;
		month = getMonthName(caldate,month,year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('sat_id').innerHTML = "Saturday" + "<br> " + caldate + " " + month;;
		caldate = caldate+1;
		month = getMonthName(caldate,month,year)
		caldate = getCalDate(caldate,str[0],year);
		
	document.getElementById('sun_id').innerHTML = "Sunday" + "<br> " + caldate + " " + month;;
	
	
	
	setJsoData(diaryuserid,tdcode);
	//}
	
	
}





//data repeat by week function 
function repeatByWeek(){
	
	var tdcode = temptdcode;
	
	var weekNumber =document.getElementById('weekNumber').value;
	
	var diaryYear = document.getElementById('diaryYear').value;
	var diaryUser = document.getElementById('diaryUser').value;
	
	var url = "repeatslotDiaryMangent?tdcode="+tdcode+"&weekNumber="+weekNumber+"&diaryYear="+diaryYear+"&diaryUserid="+tempdiaryuserid+"&diaryUser="+diaryUser+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = repeatByWeektRequest;
    req.open("GET", url, true); 
              
    req.send(null);
} 

function repeatByWeektRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
            //jAlert('success', 'Appointment slot has been repeated successfully!!', 'Success Dialog');
            tempAlert("Appointment slot has been repeated successfully", 5000);
			window.location.reload();
		}
	}
}


	var style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = '.cssClass {padding: 0px; margin: 0px; height:72px; text-align:center }';
	document.getElementsByTagName('head')[0].appendChild(style);
	
	var style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = '.cssClass1 { min-height:10px; min-width:99px; cursor:pointer;}';
	document.getElementsByTagName('head')[0].appendChild(style);
	var time=0;
function setJsoData(diaryuserid,tdcode){
	$.ajax({
		url: "diarymanagement/pages/JQGridMaster.jsp?diaryuserid="+diaryuserid+"&tdcode="+tdcode+" " ,
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
                          	//alert(value.location);
                          	
                          
                                          
                          	
                          	if(value.weekfullname == "Monday"){
                          		
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
                          		
                          		//$('#1').attr('colspan', 3);
								for (i=parseInt(stdiff);i<parseInt(etdiff);i=(i+7)){
									//$(document.getElementById(i)).css('border-bottom', '0px');
									$(document.getElementById(i)).css('font-size', '10px');
									document.getElementById(i).className = 'cssClass';
									document.getElementById(i).onclick = "";
									//document.getElementById(i).ondblclick = function() {
									//	$(this).MessageBox(value.weekfullname,col,action,value.id,stdiff,value.starttime,value.endtime,value.apmtduration,value.location,value.onlinebooking,time);
									//}
									
								}
								
								
									var jcount = 0;
									
									divlength = divlength - 1;
									
									for(j=0;j<=divlength;j++){
										jcount = 5*parseInt(slength);
										
										document.getElementById(jcount+'min'+stdiff).className = 'cssClass1';
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', value.color);
										slength = parseInt(slength) + 1;
										document.getElementById(jcount+'min'+stdiff).onclick = "";
										document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
											$(this).MessageBox(value.weekfullname,col,action,value.id,stdiff,value.starttime,value.endtime,value.apmtduration,value.location,value.onlinebooking,time);
											//$( '#toPopup' ).dialog( "open" );
											
											$( "#appointmendiv3" ).modal("show");
											//$( "#appointmendiv" ).dialog( "open" );
											//$('#appointmendiv').dialog('option', 'title', 'Edit Appointment');
										}
										if(jcount==55){
											slength = 0;
											divlength = parseInt(divlength) - j;
											j = 0;
											stdiff = parseInt(stdiff) + 7;
											
										}
										
										
									}
									
								
								//write text 
                          		if(parseInt(stemp[1]) >= 45){
                          			tempStdiff = parseInt(tempStdiff)+7;
                          			stemp[1] = 5;
                          		}
                          		
								var stext = parseInt(stemp[1]);
								var apttime = value.starttime + "-" + value.endtime + "," + value.location;
								
								document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green">'+apttime+'</div>';
								//stext = parseInt(stext)+5;
								//document.getElementById(stext+'min'+tempStdiff).innerHTML = "("+value.apmtduration+"min)";
								//stext = parseInt(stext)+5;
								//document.getElementById(stext+'min'+tempStdiff).innerHTML = value.location;
								
								
							}//col 1
							
							if(value.weekfullname == "Tuesday"){
                          		var col = 2;
                          		setWeekTimer(value.weekfullname,2,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 2
							
							if(value.weekfullname == "Wednesday"){
                          		var col = 3;
                          		setWeekTimer(value.weekfullname,3,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 3
							
							if(value.weekfullname == "Thursday"){
                          		var col = 4;
                          		setWeekTimer(value.weekfullname,4,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 4
							
							if(value.weekfullname == "Friday"){
                          		var col = 5;
                          		setWeekTimer(value.weekfullname,5,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 5
							
							if(value.weekfullname == "Saturday"){
                          		var col = 6;
                          		setWeekTimer(value.weekfullname,6,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 6
							
							if(value.weekfullname == "Sunday"){
                          		var col = 7;
                          		setWeekTimer(value.weekfullname,7,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,value.clinicstime)
							}//col 7
							
							
                        });        
                     });
			
		}
	});
	
	}



function setWeekTimer(weekfullname,col,starttime,endtime,id,apmtduration,location,onlinebooking,color,clinicstime){
	
	//var col = 1;
	//start time diff
	var stdiff = parseInt(starttime) - clinicstime
	stdiff = (parseInt(stdiff) * 7) + parseInt(col);
	var tempStdiff = stdiff;
	
	//end time diff
	var etdiff = parseInt(endtime) - clinicstime
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
	for (i=parseInt(stdiff);i<parseInt(etdiff);i=(i+7)){
		//$(document.getElementById(i)).css('border-bottom', '0px');
		$(document.getElementById(i)).css('font-size', '10px');
		document.getElementById(i).className = 'cssClass';
		document.getElementById(i).onclick = "";
		//document.getElementById(i).ondblclick = function() {
		//	$(this).MessageBox(weekfullname,col,action,id,stdiff,starttime,endtime,apmtduration,location,onlinebooking,time);
		//}
		
	}


	var jcount = 0;
	
	divlength = divlength - 1;
	
	for(j=0;j<=divlength;j++){
		jcount = 5*parseInt(slength);
		
		document.getElementById(jcount+'min'+stdiff).className = 'cssClass1';
		$(document.getElementById(jcount+'min'+stdiff)).css('background-color', color);
		slength = parseInt(slength) + 1;
		document.getElementById(jcount+'min'+stdiff).onclick = "";
		document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
			
			$(this).MessageBox(weekfullname,col,action,id,stdiff,starttime,endtime,apmtduration,location,onlinebooking,time);
			
			
			$( "#appointmendiv3" ).modal("show");
			
			//$( "#appointmendiv" ).dialog( "open" );
			//$('#appointmendiv').dialog('option', 'title', 'Edit Appointment');
		}
		if(jcount==55){
			slength = 0;
			divlength = parseInt(divlength) - j;
			j = 0;
			stdiff = parseInt(stdiff) + 7;
			
		}
		
		
	}
	

	//write text 
	if(parseInt(stemp[1]) >= 45){
		tempStdiff = parseInt(tempStdiff)+7;
		stemp[1] = 5;
	}
                  		
	var stext = parseInt(stemp[1]);
	var apttime = starttime + "-" + endtime + "," + location;
	
	document.getElementById(stext+'min'+tempStdiff).innerHTML = '<div class="green">'+apttime+'</div>';
	//stext = parseInt(stext)+5;
	//document.getElementById(stext+'min'+tempStdiff).innerHTML = "("+apmtduration+"min)";
	//stext = parseInt(stext)+5;
	//document.getElementById(stext+'min'+tempStdiff).innerHTML = location;
	
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


function getMonthName(caldate,month,year){
	var monnthArray = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var a = monnthArray.indexOf(month);
	
	if((month=="Jan") || (month=="Mar") || (month=="May") || (month=="Jul") || (month=="Aug") || (month=="Oct") ||  (month=="Dec")){
		if(caldate > 31){
			a = parseInt(a) + 1;
			month = monnthArray[a];
		}
	}else if(month=="Feb"){
		if(parseInt(year)%4==0){
			if(caldate > 29){
				a = parseInt(a) + 1;
				month = monnthArray[a];
			}
		}else{
		
			if(caldate > 28){
				a = parseInt(a) + 1;
				 month = monnthArray[a];
			}
		}
	
	}else{
		if(caldate > 30){
			a = parseInt(a) + 1;
			month = monnthArray[a];
		}
	}
	
	return month;
	
}

//get caldate
function getCalDate(caldate,month,year){
	
	if((month=="Jan") || (month=="Mar") || (month=="May") || (month=="Jul") || (month=="Aug") || (month=="Oct") ||  (month=="Dec")){
		if(caldate > 31){
			caldate =  parseInt(caldate) - 1
			caldate =  parseInt(caldate) - 30 
		}
	}else if(month=="Feb"){
		if(parseInt(year)%4==0){
			
			if(caldate > 29){
				//caldate =  parseInt(caldate) - 1
				caldate =  parseInt(caldate) - 29 
			}
		}else{
		
			if(caldate > 28){
				//caldate =  parseInt(caldate) - 1
				caldate =  parseInt(caldate) - 28
			}
		}
	
	}else{
		if(caldate > 30){
			//caldate =  parseInt(caldate) - 1
			caldate =  parseInt(caldate) - 30 
		}
	}
	
	 
	
	return caldate;
}


function getMonthNumber(month){
	var result = 0;
	if(month=="Jan")
		result = 01;
	if(month=="Feb")
		result = 02;
	if(month=="Mar")
		result = 03;
	if(month=="Apr")
		result = 04;
	if(month=="May")
		result = 05;
	if(month=="Jun")
		result = 06;
	if(month=="Jul")
		result = 07;
	if(month=="Aug")
		result = 08;
	if(month=="Sep")
		result = 09;
	if(month=="Oct")
		result = 10;
	if(month=="Nov")
		result = 11;
	if(month=="Dec")
		result = 12;
		
		return  result;
}
 function saveSlot1(){
	 
	 if(document.getElementById('location').value==0){
		 jAlert('error', 'Please Select Location', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	 }else{
		 
		 	var commencing = document.getElementById('wc').innerHTML;
		 	var selectedDiaryUser = document.getElementById('selecteddiaryUserid').value;
			var location = document.getElementById('location').value;
			var room = document.getElementById('room').value;
			//var description = document.getElementById('description').value;
			var sTime = document.getElementById('sTime').value;
			var endTime = document.getElementById('endTime').value;
		 
			checkEventExist(commencing,selectedDiaryUser,location,sTime,endTime);
		// $(this).saveSlot(editappointmentid);
	 }
	 
	
 }
 
 
 function checkEventExist(commencing,selectedDiaryUser,location,sTime,endTime){
	 var url = "eventExistDiaryMangent?selectedid="+editappointmentid+"&commencing="+commencing+"&selectedDiaryUser="+selectedDiaryUser+"&location="+location+"&sTime="+sTime+"&endTime="+endTime+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = checkEventExistRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

 }
 
 function checkEventExistRequest(){
	 
	 if (req.readyState == 4) {
			if (req.status == 200) {
				var res = req.responseText;
				if(res=='true'){
					 jAlert('error', 'The slot is already booked of you selected time.Please select valid time.', 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}else{
					 $(this).saveSlot(editappointmentid);
				}
				
			}
	 	}
	 
 }
 
 
 
 
 function resetEditId(){
	 editappointmentid = 0;
	 
 }
 
 function checkAppointmentExistOnSlot(){
	 
	 var url = "checkDiaryMangent?selectedid="+editappointmentid+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = checkAppointmentExistOnSlotRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
 }
 
 function checkAppointmentExistOnSlotRequest(){
	 
	 if (req.readyState == 4) {
			if (req.status == 200) {
				var result = req.responseText;
				if(result=='true'){
					alert('The slot have some appointment. You can not delete this slot!! ');
				}else{
					deleteSchedule();				
				}
			}
	 }
 }
 
 function deleteSchedule(){
	 
	 var r=confirm("Are you sure you want to delete this entry?");
		if (r==true)
		  {
			 var url = "deleteslotDiaryMangent?selectedid="+editappointmentid+"";

				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = deleteScheduleRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
		  return true;
		  }
		else
		  {
		  return false;
		  }

	 
	

 }
 
 function deleteScheduleRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				 jAlert('success', 'Record Deleted Successfully.', 'Success Dialog');
				window.location.reload();
			}
	 	}
	 
 }
 
 function confirmedDelete(){

	 var r=confirm("Are you sure you want to delete this entry");
	 if (r==true)
	   {
		 $( "#appointmentTableDiv2" ).modal( "hide" );
	   return true;
	   }
	 else
	   {
		 $( "#appointmentTableDiv2" ).modal( "hide" );
	   return false;
	   }
	 }
	 
function submitSupportMail(){
	var query = document.getElementById("query_type").value;
	var message = document.getElementById("message").value;
	var module=document.getElementById("modulename").value;
	var altmobno=document.getElementById("altmobno").value;
	 var phoneno = /^\d{10}$/;
	 var flag=true;
	 if(altmobno!=''){
		  if(!(phoneno.test(altmobno))){
			  flag=false;
			 
		  }else{
			  flag=true; 
		  }
	 }
	 
	if(query=='0'){
			jAlert('error', "please select Query Type!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);	
	}else if(message==''){
			jAlert('error', "please Enter Something Comment!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	}else if(!flag){
		
		 jAlert('error', "please enter proper mob number else don't!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
		document.getElementById("submitbutt").style.display = "none";
		
		  var dataObj={
				    "query" : "" + query + "",
					    "message" : "" + message + "",
					    "module" : "" + module + "",
					    "altmobno" : "" + altmobno + "",
					  };
/*		var url = "submitSupportMailDiaryMangent?query="+query+"&message="+message+"";*/
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

			   url : "submitSupportMailDiaryMangent",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   // beforeSend: function () { LockScreen(); },
			   success : function(data) {
				   var condition= data.responsej;
				   var result = data.responsej;
					if(result!='0'){
							/*jAlert('success', "Mail sent successfully! Thank you for reaching out. One of our support agents will reply as soon as possible.  if it's an emergency , you can contact us by phone at (9404022226).", 'Success Dialog');*/
							jAlert('success', "Mail sent successfully! Thank you for reaching out. One of our support agents will reply as soon as possible.", 'Success Dialog');	
							document.getElementById("submitbutt").style.display = "block";
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							document.getElementById("query_type").value="0";
							document.getElementById("message").value="";
						}
				   
				   
				    },
			   error : function(result) {
			    console.log("error in saving diagnosis");
			   }

			  });

	}
 }
 function submitSupportMailRequest(){
	 if (req.readyState == 4) {
		if (req.status == 200) {
			var result = req.responseText;
			if(result!='0'){
					jAlert('success', 'Mail sent successfully! ', 'Success Dialog');
					document.getElementById("submitbutt").style.display = "block";
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("query_type").value="0";
					document.getElementById("message").value="";
				}
		}
	 }
 }
 
 
 
 function openPharmacyUserProfile(){
		var url = "openpharmacyuserprofileDiaryMangent";

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = openPharmacyUserProfileRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
	function openPharmacyUserProfileRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var str = req.responseText;
					var data = str.split("~");
					document.getElementById("user_fullname").value = data[0];
					document.getElementById("user_userid").value = data[1];
					document.getElementById("user_state").value = data[2];
					document.getElementById("user_location").value = data[3];
					document.getElementById("phonediv").innerHTML = data[4];
					document.getElementById("user_id").value = data[5];
					//document.getElementById("user_oldpwd").value = data[6];
					$('#myProfile').modal("show");
					 
			     }
			}
		}
	
 function openIpd(newwardid){
	 
	
	opencPopup('IpdDashboard?action=0&directwardid='+newwardid+'');
	
	 
 }
 
 
 function takehospitalbk() {
	 document.getElementById("bckup").text="Please Wait...";
		var url = "takehospitalbkMainDashBoard";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openPharmacyUserProfileRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function openPharmacyUserProfileRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("bckup").text="Hospital Backup";
				jAlert('success', 'Hospital Backup Done Successfully! Download using Download Backup ', 'Success Dialog');
				document.getElementById("submitbutt").style.display = "block";
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		     }
		}
	}



function downloadhospitalbk() {
		var url = "downloaddatabaseMainDashBoard";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = downloadhospitalbkRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function downloadhospitalbkRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				jAlert('success', 'Hospital Backup Download  Successfully!', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		     }
		}
	}
function downloadjsp(){
	var url = "downloadjspMainDashBoard";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = downloadjspRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function downloadjspRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var url = req.responseText;
				if (!url.match(/^https?:\/\//i)) {
			        url = 'http://' + url;
			    }
				
				window.open(url, 'mywindow','location=1,status=1,scrollbars=1, resizable=1, directories=1, toolbar=1, titlebar=1,height=400,width=400');
				jAlert('success', 'Backup Downloading In Progress!', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		     }
		}
	}


function changeuserlanguage(val){
	var url = "changeuserlanguageBookAppointmentAjax?language="+val+"";

		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changeuserlanguageRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function changeuserlanguageRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
	    }
	}
}



