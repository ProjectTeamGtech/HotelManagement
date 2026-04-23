var tempCount = 0;
var month = 0;
var ct = 0;
var year = 0;
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
	$("#previewDiv").hide();
	
	g_globalObject = new JsDatePick({
			useMode:1,
			isStripped:true,
			target:"div3_example"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
		
		g_globalObject.setOnSelectedDelegate(function(){
			var obj = g_globalObject.getSelectedDay();
			//alert("a date was just selected and the date is : " + obj.day + "/" + obj.month + "/" + obj.year);
			//document.getElementById("div3_example_result").innerHTML = obj.day + "/" + obj.month + "/" + obj.year;
			var selectedDate = obj.day + "/" + obj.month + "/" + obj.year;
			month = obj.month;
			year = obj.year;
			
			//set date according to calander date
			setCalanderDateAjax(selectedDate);
		});
		
		
		
		g_globalObject2 = new JsDatePick({
			useMode:1,
			isStripped:false,
			target:"div4_example",
			cellColorScheme:"beige"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
		
		g_globalObject2.setOnSelectedDelegate(function(){
			var obj = g_globalObject2.getSelectedDay();
			//alert("a date was just selected and the date is : " + obj.day + "/" + obj.month + "/" + obj.year);
			document.getElementById("div3_example_result").innerHTML = obj.day + "/" + obj.month + "/" + obj.year;
		});		
	
});		
		
		
		
	
function goClose(){
	
	$("#background").fadeOut("slow");
	$("#previewDiv").hide();
	document.getElementById('calander').style.display = "";
	$(document.getElementById("c"+tempCount)).css('background-color', '');
	
	
	
}

function goPreview(count,wday,apTime){

	
	if(year == 0){
		year = document.getElementById('year').value;
	}
	
	var choseDate = document.getElementById('th'+wday).innerHTML;
	document.getElementById('when').value = choseDate + " " + year;
	
	var fromHour = getFromHourValue(apTime);
	//document.getElementById('hour').value = fromHour;
	//document.getElementById('minute').value = '00';
	
	//document.getElementById('tohour').value = fromHour;
	//document.getElementById('tominute').value = '45';
	
	tempCount = count;
	$(document.getElementById("c"+count)).css('background-color', '#4CB2E6');

	
	$("#background").css({"opacity" : "0.7"}).fadeIn("slow");			
	$("#previewDiv").center().fadeIn("slow");	
	$("#calander").hide();
	
	
	
}

function getFromHourValue(fromhour){
	var result = "";
	var fhour = fromhour.split(" ");
	result = fhour[0];
	
	return result;
}


//save appointment
function addAppointment(){
	
	
	
	if(document.getElementById('practitionerID').value==0){
		
        jAlert('error', 'Please select Practitioner.', 'Error Dialog');
    	setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else{
		//appointment type duration
		var duration = document.getElementById('appointmentType').value;
		var temp = duration.split("_");
		duration = temp[1];
		
		//selected time duration
		var hour = document.getElementById('hour').value;
		var minute = document.getElementById('minute').value;
		var starttime = hour + ":" + minute;
		
		var tohour = document.getElementById('tohour').value;
		var tominute = document.getElementById('tominute').value;
		var endtime = tohour + ":" + tominute;
		
		var result = getTimeDifference(starttime,endtime);
		
		//convert result into minute
		var tempresult = result.split(":");
		var hh = parseInt(tempresult)*60;
		var totalmin = parseInt(hh) + parseInt(tempresult[1]);
		
		if(parseInt(totalmin) > parseInt(duration)){
            jAlert('error', "your selected appointment type is of "+duration+" min and your selected time duration is greater than appointment type.", 'Error Dialog');
        	setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

		}else if(tempHour!= document.getElementById('hour').value){
            jAlert('error', 'Please enter valid start hour.', 'Error Dialog');
        	setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

		}else if(tempMinute!=document.getElementById('minute').value){
            jAlert('error', 'Please enter valid start minute.', 'Error Dialog');
        	setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

		}else{
			
            jAlert('success', 'Save.', 'Appointment');

		}
		
		
	}
}

//set appointment type
var temppractitionerid = 0;
function setAppointmentType(practitionerid){
	temppractitionerid = practitionerid;
	var url = "settypeAppointment?practitionerid="+practitionerid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAppointmentTypeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function setAppointmentTypeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('appointmenttypeajax').innerHTML = req.responseText;
			checkAvailiability();
			
			
		}
	}
}

//set location
function setLocation(){
	
	var url = "locationAppointment?practitionerid="+temppractitionerid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setLocationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setLocationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('locationajax').innerHTML = req.responseText;
		}
	}
}

//check availiability
function checkAvailiability(){
	var when = document.getElementById('when').value;
	var url = "checkAppointment?practitionerid="+temppractitionerid+"&when="+when+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkAvailiabilityRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function checkAvailiabilityRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert(req.responseText)
			document.getElementById('msg').innerHTML = req.responseText;
			
			if( req.responseText == "<ul><li>Not Avaibable</li></ul>"){
				
				document.getElementById('addapptbutton').disabled="disabled";
			}else{
			
				document.getElementById('addapptbutton').disabled = "";
			}
			
			setLocation();
		
		}
	}
}



function setPatient(patienttype){
	if(patienttype=="New Patient"){
		document.getElementById('newpatient').style.display = "";
	}else{
		document.getElementById('newpatient').style.display = "none";
	}

}


function setCalanderDateAjax(selectedDate){
	var url = "calanderAppointment?selectedDate="+selectedDate+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCalanderDateAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setCalanderDateAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		var weekmonth=new Array(12);
		weekmonth[0]="Jan";
		weekmonth[1]="Feb";
		weekmonth[2]="Mar";
		weekmonth[3]="Apr";
		weekmonth[4]="May";
		weekmonth[5]="Jun";
		weekmonth[6]="Jul";
		weekmonth[7]="Aug";
		weekmonth[8]="Sep";
		weekmonth[9]="Oct";
		weekmonth[10]="Nov";
		weekmonth[11]="Dec";
		
		month = month -1;
			
			var str = req.responseText;
			var res = str.split(",");
			//document.getElementById('th1').value = "Mon 23 Dec";
			document.getElementById('th1').innerHTML = "Mon, "+res[0]+" "+weekmonth[month]+"";
			document.getElementById('th2').innerHTML = "Tue, "+res[1]+" "+weekmonth[month]+"";
			document.getElementById('th3').innerHTML = "Wed, "+res[2]+" "+weekmonth[month]+"";
			document.getElementById('th4').innerHTML = "Thu, "+res[3]+" "+weekmonth[month]+"";
			document.getElementById('th5').innerHTML = "Fri, "+res[4]+" "+weekmonth[month]+"";
			document.getElementById('th6').innerHTML = "Sat, "+res[5]+" "+weekmonth[month]+"";
			document.getElementById('th7').innerHTML = "Sun, "+res[6]+" "+weekmonth[month]+"";
			
    	}
	}
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
