/* 
	author: istockphp.com
*/



jQuery(function($) {

 $.fn.MessageBox = function(weekname,daynumber,action,id,tdid,starttime,endtime,apmtduration,location,onlinebooking,time) {
 	
 	
 	
 	if(action=="edit"){
 		document.getElementById('apmtheading').innerHTML = "<u>Edit Appointment</u>";
 		document.getElementById('sTime').value = starttime;
 		document.getElementById('endTime').value = endtime;
 		document.getElementById('apmtDuration').value = apmtduration;
 		document.getElementById('location').value = location;
 		document.getElementById('onlineBooking').checked = onlinebooking;
 		
 		var str = '<input class="btn btn-primary" type="button" value="Submit" onclick="$(this).saveSlot(\''+id+'\');"/>'
 		str = str + '<input class = "buttons" type="button" value="Delete" onclick="deleteSlot(\''+id+'\');"/>'
 		str = str + '<input class = "buttons" type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
 		document.getElementById('submitslot').innerHTML = str;
 		
 		daynumber = parseInt(daynumber)-1;
 	}
 	if(action=="save"){
 		var addendtime = "";
 		addendtime = parseInt(time) + 1;
 		
 		if(time=='8' || time=='9'){
 			time = "0" + time + ":00" 
 			
 		}else{
 			time = time + ":00";
 		}
 		
 		if(addendtime=='8' || addendtime=='9'){
 			addendtime = "0" + addendtime + ":00" 
 			
 		}else{
 			addendtime = addendtime + ":00";
 		}
 		
 		
 		
 		document.getElementById('sTime').value = time;
 		document.getElementById('endTime').value = addendtime;
 		
 		/*document.getElementById('apmtheading').innerHTML = "<u>Add Appointment</u> <input type='checkbox' name='wholeweek' id='wholeweek'/> Repeat for whole week";
 		var str = '<input class = "buttons" type="button" value="Book" onclick="$(this).saveSlot(\''+0+'\');"/>'
 		str = str + '<input class = "buttons" type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
 		document.getElementById('submitslot').innerHTML = str;*/
 		//window.location.reload();
 	}
          /* loading(); // loading
			setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup 
			}, 500); // .5 second
			*/
			document.getElementById('wcday').innerHTML = weekname;
			document.getElementById('selectedDiaryUser').value = document.getElementById('diaryUser').value;
			document.getElementById('selecteddiaryUserid').value = document.getElementById('diaryuserid').value;
			
			//set commencing date for appointment slot
			
			var commencing = document.getElementById('commencing').value;
			var temp = commencing.split("/");
			if(daynumber == 0){
				 date = parseInt(temp[0]);
				 var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				document.getElementById('wc').innerHTML = date;
				
			}
			
			if(daynumber == 1){
				 date = parseInt(temp[0]);
				 date = date + 1;
				
				 var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
				 
				// alert(date)
			}
			if(daynumber == 2){
				 date = parseInt(temp[0]);
				 date = date + 2;
				 
				 var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
			}
			if(daynumber == 3){
				 date = parseInt(temp[0]);
				 date = date + 3;
				
				  var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
			}
			if(daynumber == 4){
				 date = parseInt(temp[0]);
				 date = date + 4;
				
				 var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
			} 
			if(daynumber == 5){
				 date = parseInt(temp[0]);
				 date = date + 5;
				 
				 var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
			}  
			if(daynumber == 6){
				 date = parseInt(temp[0]);
				 date = date + 6;
				 
				  var month = getMonthNumber(temp[1]);
				 date = getDiaryCalDate(date,month,temp[2])
				 document.getElementById('wc').innerHTML = date;
			} 
			
			     
			
	return false;
        };
	
	
	
	/* event for close the popup */
	/*$("div.close").hover(
					function() {
						$('span.ecs_tooltip').show();
					},
					function () {
    					$('span.ecs_tooltip').hide();
  					}
				);
	
	$("div.close").click(function() {
		disablePopup();  // function close pop up
	});
	
	$(this).keyup(function(event) {
		if (event.which == 27) { // 27 is 'Ecs' in the keyboard
			disablePopup();  // function close pop up
		}  	
	});
	
	$("div#backgroundPopup").click(function() {
		disablePopup();  // function close pop up
	});
	
	$('a.livebox').click(function() {
		alert('Hello World!');
	return false;
	});
	*/

	 /************** start: functions. **************/
	/*function loading() {
		$("div.loader").show();  
	}
	function closeloading() {
		$("div.loader").fadeOut('normal');  
	}
	
	var popupStatus = 0; // set value
	
	function loadPopup() { 
		if(popupStatus == 0) { // if value is 0, show popup
			closeloading(); // fadeout loading
			$("#toPopup").fadeIn(0500); // fadein popup div
			$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			$("#backgroundPopup").fadeIn(0001); 
			popupStatus = 1; // and set value to 1
		}	
	}
		
	function disablePopup() {
		if(popupStatus == 1) { // if value is 1, close popup
			$("#toPopup").fadeOut("normal");  
			$("#backgroundPopup").fadeOut("normal");  
			popupStatus = 0;  // and set value to 0
		}
	}*/
	/************** end: functions. **************/
	
	$.fn.saveSlot = function(id){
		var commencing = document.getElementById('wc').innerHTML;
		var currentdate = getCurrentDate();
		commencing = dateFormat(commencing);
		
		var initStime = document.getElementById('sTime').value;
		initStime = initStime + ':' + '00';
		
		
		if (new Date(commencing) < new Date(currentdate)) {
            jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

		}else if((new Date(commencing).getTime() == new Date(currentdate).getTime()) && (initStime < getCurrentTime())){
            jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

		}else{
			saveAppointmentSlot(id);
		}
		
		disablePopup(); 
	
	};
	
	$.fn.cancelSlot = function(){
		
		disablePopup(); 
	
	};
	
	
}); // jQuery End



function saveAppointmentSlot(id){
	
	var commencing = document.getElementById('wc').innerHTML;
	var selectedDiaryUser = document.getElementById('selectedDiaryUser').value;
	var location = document.getElementById('location').value;
	var room = document.getElementById('room').value;
	//var description = document.getElementById('description').value;
	var sTime = document.getElementById('sTime').value;
	var endTime = document.getElementById('endTime').value;
	var apmtDuration = document.getElementById('apmtDuration').value;
	var onlineBooking = document.getElementById('onlineBooking').checked;
	var selecteddiaryUserid = document.getElementById('selecteddiaryUserid').value;
	var tdcode = document.getElementById('tdcode').value;
	var weekname = document.getElementById('wcday').innerHTML;
	
	if(parseFloat(id)== 0){
		var wholeweek = document.getElementById('wholeweek').checked;
		var url = "saveslotDiaryMangent?commencing="+commencing+"&selectedDiaryUser="+selectedDiaryUser+"&location="+location+"&room="+room+"&sTime="+sTime+"&endTime="+endTime+"&apmtDuration="+apmtDuration+"&onlineBooking="+onlineBooking+"&selecteddiaryUserid="+selecteddiaryUserid+"&tdcode="+tdcode+"&weekname="+weekname+"&selectedid="+id+"&wholeweek="+wholeweek+" ";
	}else{
		var url = "saveslotDiaryMangent?commencing="+commencing+"&selectedDiaryUser="+selectedDiaryUser+"&location="+location+"&room="+room+"&sTime="+sTime+"&endTime="+endTime+"&apmtDuration="+apmtDuration+"&onlineBooking="+onlineBooking+"&selecteddiaryUserid="+selecteddiaryUserid+"&tdcode="+tdcode+"&weekname="+weekname+"&selectedid="+id+" ";
	}
	
	
	
	
	
	//var url = "saveslotDiaryMangent";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveAppointmentSlotRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function saveAppointmentSlotRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', 'Appointment slot has been set successfully!!', 'Success Dialog');
			//goPreview(tempusername,tempdate,tempyear,tempdiaryuserid,temptdcode)
			window.location.reload();
		}
	}
}

function deleteSlot(id){

	var url = "deleteslotDiaryMangent?selectedid="+id+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deleteSlotRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function deleteSlotRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', 'Appointment slot has been deleted successfully!!', 'Success Dialog');
			window.location.reload();
			//goPreview(tempusername,tempdate,tempyear,tempdiaryuserid,temptdcode)
			
		}
	}
}



function getDiaryCalDate(caldate,month,year){
	if(month<=9){
		month='0'+month;
	}
	if((month==1) || (month==3) || (month==5) || (month==7) || (month==8) || (month==10) ||  (month==12)){
		if(caldate > 31){
			
			//caldate =  parseInt(caldate) - 1
			caldate =  parseInt(caldate) - 31 
			month = parseInt(month)+1;
			
			if(month==13){
				month = 1;
				year = parseInt(year)+1;
			}
			
			if(month <=9){
				month = "0"+month;
			}
		}
	}else if(month==2){
		if(parseInt(year)%4==0){
			
			if(caldate > 29){
				//caldate =  parseInt(caldate) - 1
				caldate =  parseInt(caldate) - 29 
				month = parseInt(month)+1;
				
				if(month <=9){
					month = "0"+month;
				}
			}
		}else{
		
			if(caldate > 28){
				//caldate =  parseInt(caldate) - 1
				caldate =  parseInt(caldate) - 28
				month = parseInt(month)+1;
				 
				if(month <=9){
					month = "0"+month;
				}
			}
		}
	
	}else{
		if(caldate > 30){
			//caldate =  parseInt(caldate) - 1
			caldate =  parseInt(caldate) - 30 
			month = parseInt(month)+1;
			
			if(month <=9){
				month = "0"+month;
			}
			
		}
	}
	
	
	if(caldate <=9){
		caldate = "0"+caldate;
	}
	
	
	caldate = caldate + "/" + month + "/" + year;
	//alert(caldate);
	return caldate;
}



