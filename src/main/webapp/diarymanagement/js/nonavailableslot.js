var clientFullName = " ";
var tempCount = 0;
var month = 0;
var ct = 0;
var year = 0;
var susername = " ";
var globalEndTime = 0;
var actionType = 0;
var clientName = "";
var clientId = ""
	
var bookSlotid = 0;
var bookCommencing = "";
var bookLocation = "";
var bookDiaryUserid = 0;
var bookDiaryUser = "";
var bookStartTime = "";
var bookEndTime = "";
var bookAppointmentType = "";
var globalTimeTop = 0;
var globalTpType = 0;
var globaltpid = 0;
var editpateintpayby = "";
var editgpid = 0;
var globalSlotStartTime = "";
var editTpName = "";
var globalDna = "";
var saveAppointmntid = 0;
var savedclient = 0;
isappointmentinvoiced = 0;
var bookwithpayment = 0;
var executeonce=0;

var appointmenttypeid=0;

//javascript css
	var style = document.createElement('style');
	style.type = 'text/css';
	style.innerHTML = '.cssClass {padding: 0px; margin: 0px; height:144px; text-align:center }';
	document.getElementsByTagName('head')[0].appendChild(style);
	var todayDate = new Date().getDate();
	$( "#initialdob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
		 changeYear: true,
		 maxDate: new Date(new Date().setDate(todayDate))
	 });
	
	
	/*if(openedb=='otdb'){
		
		var style = document.createElement('style');
		style.type = 'text/css';
		style.innerHTML = '.cssClass1 { min-height:10px; cursor:pointer; }';
		document.getElementsByTagName('head')[0].appendChild(style);
		var time=0; 
		
	}else{
		var style = document.createElement('style');
		style.type = 'text/css';
		style.innerHTML = '.cssClass1 { min-height:20px; cursor:pointer; }';
		document.getElementsByTagName('head')[0].appendChild(style);
		var time=0; 
	}*/
	
	

/* 
 * hide or show dialog box on delete event
 * 
 * */
/*displayTooltip = false;
var messageDelay = 10000;
jQuery.fn.center = function () {
		
		this.css("position","absolute");
		//this.css("top", ( $(window).height() - this.height() ) / 2+$(window).scrollTop() + "px");
		//this.css("left", ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + "px");
		
		return this;
	};*/
	

/*$(document).ready(function(){
	$(document.getElementById('masterbodydiv')).css('padding-top', '0px');
	 $(document.getElementById('masterbodycontainer')).css('padding-left', '0px');
	 $(document.getElementById('masterbodycontainer')).css('padding-right', '0px');
	 
	  //called when key is pressed in textbox
	  $("#quantity").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	     
	     
	     $("#quantity1").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	 
	 var cdtime = formatAMPM(new Date());
	 
	 if(cdtime == '08pm'){
		 document.getElementById('tgnowmarker').style.display = 'none';
   }
	 
	 var puretoday = new Date();
 $( "#puredob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 maxDate: puretoday,
		 changeMonth: true,
	     changeYear: true
		 
			 
	 });
	 
//$("#dashboardDiv").hide();
	//document.getElementById('closediv').style.display = '';
	 $( "#commencing" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
		 changeYear: true	 
	 });
	 
	 $( "#referalDate" ).datepicker({
		 
		 	dateFormat:'dd/mm/yy',
		 	yearRange: yearrange,
		 	minDate : '30/12/1880',
			changeMonth: true,
		    changeYear: true	 
	 });
	 
	 
	 
	 //set menu calender date
	 var cdate = document.getElementById('commencing').value;
	 var ctempd = cdate.split('/');
	 
	 var queryDate = ctempd[2] + '-' + ctempd[1] + '-' + ctempd[0],
	    dateParts = queryDate.match(/(\d+)/g)
	    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
	                                    // months are 0-based!

	$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' }); // format to show
	$('#datepicker').datepicker('setDate', realDate);

	 $( "#avlbltyDate" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 yearRange: yearrange,
		 minDate : '30/12/1880',
		 changeMonth: true,
		 changeYear: true	 
	 });
	 
	 
	 
	 var date = new Date();
	 var hour = date.getHours();
	 var min = date.getMinutes();
	 
	 var halftime = hour + ':' + min;
	 
	 var curdate = date.getDate();
	 var commencingdate = document.getElementById('commencing').value;
	 var cd = commencingdate.split('/');
	 if(cd[0]!=curdate){
		 document.getElementById('tgnowmarker').style.display = 'none';
	 }else{
		 document.getElementById('tgnowmarker').style.display = '';
	 }
	 
    
	 if(hour==8){
		 globalTimeTop = -4;
		 if(min<=5){
			 globalTimeTop = 0;
		 }
		 
		 globalTimeTop = 35;
	 }else if(halftime=="8:30"){
		 globalTimeTop = 70
	 }else if(hour==9){
		 globalTimeTop = 106;
	 }else if(halftime=="9:30"){
		globalTimeTop = 143;
	 }else if(hour==10){
		 globalTimeTop = 179;
	 }else if(halftime=="10:30"){
		 globalTimeTop = 216
	 }else if(hour==11){
		 globalTimeTop = 253;
	 }else if(halftime=="11:30"){
		 globalTimeTop = 289;
	 }else if(hour==12){
		 globalTimeTop = 326;
	 }else if(halftime=="12:30"){
		 globalTimeTop = 362
	 }else if(hour==13){
		 globalTimeTop = 398;
	 }else if(halftime=="13:30"){
		 globalTimeTop = 435;
	 }else if(hour==14){
		 globalTimeTop = 471;
	 }else if(halftime=="14:30"){
		 globalTimeTop = 508;
	 }else if(hour==15){
		 globalTimeTop = 544;
	 }else if(halftime=="15:30"){
		 globalTimeTop = 581;
	 }else if(hour==16){
		 globalTimeTop = 617;
	 }else if(halftime=="16:30"){
		 globalTimeTop = 655;
	 }else if(hour==17){
		 globalTimeTop = 691;
	 }else if(halftime=="17:30"){
		 globalTimeTop = 727
	 }else if(hour==18){
		 globalTimeTop = 763;
	 }else if(halftime=="18:30"){
		 globalTimeTop = 801;
	 }else if(hour==19){
		 globalTimeTop = 836;
	 }else if(halftime=="19:30"){
		 globalTimeTop = 874;
	 }else if(hour==20){
		 globalTimeTop = 910;
	 }else if(halftime=="20:01"){
		 document.getElementById('tgnowmarker').style.display = 'none';
	 }
	 
	 var ccmin = min/5;
	 var resmin = ccmin*6;
	 globalTimeTop = globalTimeTop + resmin;
	 
	
	 
	 function gowithTime(){
		 var date = new Date();
		 var hour = date.getHours();
		 var min = date.getMinutes();
		
		 calMinute(min);
		 
		 if(cdtime == '08pm'){
			 document.getElementById('tgnowmarker').style.display = 'none';
	    }
	 }	 
		 
	 
	 
	 function calMinute(min){
		 
		globalTimeTop = globalTimeTop + 6;
		$(document.getElementById('tgnowmarker')).css('top', ''+globalTimeTop+'px');
	 }

	    //set an interval
	    setInterval(gowithTime, 300000);
	    
	   
	    
	  //  setInterval($('#followupsrminderpopup').modal( "show" ), 30);

	    //Call the function
	    gowithTime();
	
	 //alert($('#thtestid').width());
	// var resizewidth = $('#thtestid').width();
	    
	    
	 
});		
*/

function formatAMPM(date) {
	  var hours = date.getHours();
	  var minutes = date.getMinutes();
	  var ampm = hours >= 12 ? 'pm' : 'am';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ' ' + ampm;
	  
	 
	  return strTime;
	}
	 
	


function getSearch(){
	
	//set wraperdiv element
	 if(editAppointId!=0){
			getWraperDivChildren(wraperDivId);
	 }
	
	jQuery('.new').children().unwrap();
	 		actionType = 0;
	 		
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
				 
				 setJsoData(id,date,locationid);
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



function setJsoData(diaryUserId,date,locationid){

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
												$(this).MessageBox(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,this.title);
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
												
												document.getElementById('diciplineName').value = value.discipline;
												
													//set book appointment heading
			
													document.getElementById('apmtwithz').innerHTML = document.getElementById('user').value; 
													document.getElementById('diciplinez').innerHTML = $("#diciplineName option:selected").text();
													document.getElementById('locationz').innerHTML = $("#location option:selected").text();
													document.getElementById('datez').innerHTML = date;
													
													document.getElementById('apmtwithbz').innerHTML = document.getElementById('user').value; 
													document.getElementById('locationbz').innerHTML = $("#location option:selected").text();
													document.getElementById('datebz').innerHTML = date;
													
													
													document.getElementById("tkepmntwithz").innerHTML=document.getElementById('user').value; 
									    			document.getElementById("tkepmntlocationz").innerHTML=$("#location option:selected").text();
									    			document.getElementById('tkepmntdatez').innerHTML = commencing;
									    			document.getElementById('tkepmntdiciplinez').innerHTML = $("#diciplineName option:selected").text();
													
													document.getElementById("tkepmntwithz").innerHTML=document.getElementById('user').value; 
												    document.getElementById("tkepmntlocationz").innerHTML=$("#location option:selected").text();
												    document.getElementById('tkepmntdatez').innerHTML = commencing;
												    document.getElementById('tkepmntdiciplinez').innerHTML = $("#diciplineName option:selected").text();
													
													
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
									
								setjsonAvailableData(value.id,value.starttime,value.endtime,value.apmtduration,value.location,value.diaryUser,diaryUserId,col,value.practitionerid,value.commencing,value.clinicstime);
								
								
								
							}//col 1
							
							if(value.weekfullname == "Monday"){
                          		var col = 2;
                          		setWeekTimer(value.weekfullname,2,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 2
							
							if(value.weekfullname == "Tuesday"){
                          		var col = 3;
                          		setWeekTimer(value.weekfullname,3,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 3
							
							if(value.weekfullname == "Wednesday"){
                          		var col = 4;
                          		setWeekTimer(value.weekfullname,4,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 4
							
							if(value.weekfullname == "Thursday"){
                          		var col = 5;
                          		setWeekTimer(value.weekfullname,5,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 5
							
							if(value.weekfullname == "Friday"){
                          		var col = 6;
                          		setWeekTimer(value.weekfullname,6,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 6
							
							if(value.weekfullname == "Saturday"){
                          		var col = 7;
                          		setWeekTimer(value.weekfullname,7,value.starttime,value.endtime,value.id,value.apmtduration,value.location,value.onlinebooking,value.color,tempStdiff,value.diaryUser,diaryUserId,value.practitionerid,value.commencing,value.locationName,value.discipline,value.clinicstime)
							}//col 7
							
							
                        });        
                     });
			
		}
	});
			
			
}	


function setjsonAvailableData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,col,practitionerid,commencing,clinicstime){

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
                      		style.innerHTML = '.cssClass'+random+' { min-height:20px; cursor:pointer;}';
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
									/*	document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
											
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

										}
										
*/
										
										/*document.getElementById(jcount+'min'+stdiff).ondblclick = function() {
											wraperDivId = 'new'+random;
											setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted);
											
										}*/
									}else if(value.status == 1){
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										
										$(document.getElementById(jcount+'min'+stdiff)).css('background-color', 'rgb(236, 147, 147)');
										
									/*	document.getElementById(jcount+'min'+stdiff).onmousemove = function() {
											
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
*/									
									}else{
										document.getElementById(jcount+'min'+stdiff).onclick = function() {
											setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing);
											 
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
								 var resizewidth = $('#wn0').width();
								 var resultwidth = parseInt(resizewidth)+6;
								// alert(resultwidth);
								// resultwidth = resultwidth/4;
								$(document.getElementById('new'+random)).css('width', resultwidth);
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
								 
								 
								 
								
								// $( "#new"+random ).resizable();
								 
								
								 //modify popup
								 if(value.iscompleted == false){
									
									 document.getElementById('new'+random).onclick = function() {

										 $( "#appointment" ).modal( "hide" );
											wraperDivId = 'new'+random;
											loc = value.locid;
											if(value.otid==0){
												setModifyPopup(value.status,value.starttime,value.endtime,value.duration,value.clientname,value.notes,value.apmttype,value.id,value.arrivedstatus,value.dna,userid,value.clientId,value.commencing,value.practitionerEmail,value.clientEmail,value.charge,commencing,starttime,value.treatmentepisodeid,value.iscompleted,diaryuserid,value.apmttypetext,value.location,value.conditionid,value.tptypeid,value.tpnameid,value.whopay,value.tpname,value.height,value.weight,value.bmi,value.pulse,value.sysbp,value.diabp,value.temprature,value.spo,value.bsa);
											}else{
												showotmodifypopuop(value.id,value.clientId,value.clientname,value.location,value.whopay);
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
											 editwhopay = value.whopay;
											 editClientName = value.clientname;
											 loc = value.locid;
											 diaryuserId = userid;
											 editcondition_id = value.conditionid;
											 editTreatmentEpisode = value.treatmentepisodeid;
											  	     height=value.height;
													 weight=value.weight;
													 bmi=value.bmi;
													 pulse=value.pulse;
													 sysbp=value.sysbp;
													 diabp=value.diabp;
													 temprature=value.temprature;
													 spo=value.spo;
													 bsa=value.bsa;
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
											  height=value.height;
													 weight=value.weight;
													 bmi=value.bmi;
													 pulse=value.pulse;
													 sysbp=value.sysbp;
													 diabp=value.diabp;
													 temprature=value.temprature;
													 spo=value.spo;
													 bsa=value.bsa;
												//document.getElementById('modifyClient2').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href = "ClientLog?id='+patientId+'" target="blank"> Log </a>)';
											 document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3"><a href="#" style="min-width: 146px !important;margin-left: 0px;margin-bottom: 15px;background-color: #424A5D !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;min-height: 58px !important;line-height: 1.42857;" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" style="margin-bottom: 4px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png"/><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Edit</p></a> <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png"/><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Log</p></a>  <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png"/><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">EMR</p></a>  <a href = "#" style="margin-bottom: 0px;background-color: rgba(212, 212, 212, 0.11) !important;border: 1px solid #DDD;border-radius: 0px;padding: 0px;transition: all 0.2s ease-in-out 0s;display: inline-block;text-align: center;height: auto;line-height: 1.42857;width: 45%;" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png"/><p style="color: rgb(61, 61, 61) !important; font-size: 12px !important;">Forms</p></a></div>';
												
											 document.getElementById("height6").innerHTML=height;			
							 			 document.getElementById("weight6").innerHTML=weight;
			 				 			 document.getElementById("bmi6").innerHTML=bmi;
			                			 document.getElementById("pulse6").innerHTML=pulse;
							 			document.getElementById("sysbp6").innerHTML=sysbp;
							 			document.getElementById("diabp6").innerHTML=diabp;	
							 			document.getElementById("spo6").innerHTML=spo;
							 			document.getElementById("bsa6").innerHTML=bsa;
							 			document.getElementById("temprature6").innerHTML=temprature;
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
	                          		
	                          		
	                          		if(value.otid>0){
										value.clientname = value.clientname + ' (OT) '
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
	    									if(value.notes!=""){
	    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + value.reasonforblock + ","+value.starttime+"-" + ""+value.endtime+" " + '<img align="" src="common/images/note.ico" title="'+value.notes+'" ">'+ '</div>';
	    									}else{
	    										document.getElementById(stext+'min'+tempStdiff).innerHTML =  '<div class="green" style="color:black">' + value.reasonforblock + ","+value.starttime+"-" + ""+value.endtime+" " +  '</div>';
	    									}
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



function setGlobalData(diaryuserid,starttime,endtime,appointmentduration,location,diaryusername,userid,commencing,divtime){
	//alert(diaryusername)
	
	 var tepmtile = divtime.split(' '); 
	 divtime = tepmtile[0];
	
	diaryUser1 = diaryuserid;
	stime = starttime;
	blockDivTime = divtime;
	etime = endtime;
	loc = location;
	id1 = userid;
	tempDiaryUserName = diaryusername;
	editCommencing = commencing;
	tempDiaryUseridsss = userid;
	
	
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
function setWeekTimer(weekfullname,col,starttime,endtime,id,apmtduration,location,onlinebooking,color,tempStdiff,diaryUser,diaryUserId,practitionerid,commencing,locationName,discipline,clinicstime){
	
	//var col = 1;
	//start time diff
	//start time diff
                         		var stdiff = parseInt(starttime) - clinicstime;
                         		stdiff = (parseInt(stdiff) * 7) + parseInt(col);
                         		var tempStdiff = stdiff;
                         		
                         		//end time diff
                         		var etdiff = parseInt(endtime) - clinicstime;
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
										editCommencing = commencing;
										slotstarttime = starttime;

										document.getElementById('date').value = commencing;
										document.getElementById('blockdate').value = commencing;
											
										//set cookie commencing
										document.cookie = "cookiecommencing=" + commencing;
										document.cookie = "cookiePractitionerId=" + diaryUserId;
										
											//set book appointment heading
			
													document.getElementById('apmtwithz').innerHTML = document.getElementById('user').value; 
													document.getElementById('diciplinez').innerHTML = $("#diciplineName option:selected").text();
													document.getElementById('locationz').innerHTML = $("#location option:selected").text();
													document.getElementById('datez').innerHTML = date;
													
													document.getElementById('apmtwithbz').innerHTML = document.getElementById('user').value; 
													document.getElementById('locationbz').innerHTML = $("#location option:selected").text();
													document.getElementById('datebz').innerHTML = date;
										
										
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
								
							setjsonAvailableData(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,col,practitionerid,commencing,clinicstime);
								
	
}


function resetAppointmentField(){
	/*executeonce=1;*/
	/*document.getElementById('endTime').value = etime;
	document.getElementById('apmtDuration').value = '0';
	document.getElementById('apmtType').value = '0';*/
	var aptypeid = document.getElementById('apmtType').value;
	globalSlotStartTime = document.getElementById('sTime').value;
	setAppointmentTypeTimeAjax(aptypeid);
}	

var tempDuration = 0;
var checkEventExist = false;
function setAppointmentTypeTimeAjax(selectedid){
	
	var url = "durationNotAvailableSlot?selectedid="+selectedid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAppointmentTypeTimeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function setAppointmentTypeTimeAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var data1=data.split("~");
//			if(actionType==5){
//				duration=durationss1;
//			}
			setAppointmentTypeTime(data1[0]);
			document.getElementById('apmtTypeDuration').innerHTML = "Duration: "+data1[0];
			document.getElementById('apmtType').value=data1[1];
			appointmenttypeid=data1[1];
//			if(actionType==5){
//				document.getElementById('client').value=puresevaclientname;
//				}
			
			
			
		}
	}
}


function settpapmttype(whopay){
	setTpAppointmentTYpe(whopay);
}


function setTpAppointmentTYpe(whopay){
	
	var isot = document.getElementById('radio3').checked;
	
	var url = "whopayClient?patientId="+patientId+"&whopay="+whopay+"&isot="+isot+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTpAppointmentTYpeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function setTpAppointmentTYpeAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			
			
			
		}
	}
}


function paybyTreatmentEpisodeAjax(payby){
	
	editpateintpayby = payby;
	
	var url = "setTreatmentEpisode?clientid="+patientId+"&payby="+payby+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = paybyTreatmentEpisodeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function paybyTreatmentEpisodeAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('treatmentepisodeajax').innerHTML = req.responseText;
			
			
			
			document.getElementById('treatmentEpisode').value = 0;
			
			$("#treatmentEpisode").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById('sessionDetail').innerHTML = "";
			
			
			setThirdPartyExclusiveAppointmentTypeOnly(editpateintpayby,globaltpid);
			
		}
	}
}


function getPatientTpNameAjax(){
	var url = "tpnameAppointmentType?selectedid="+globaltpid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTpAppointmentTYpeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getPatientTpNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
	
}

var tduration = 0;
function setAppointmentTypeTime(duration){
	if(actionType==5){
//		document.getElementById('client').value=puresevaclientname;
		}
	tduration = duration;
	getSetupDiaryDuration();
	
}

function getSetupDiaryDuration(){
	var diaryslotid = document.getElementById('slotId').value;
	var url = "diarydurationBookAppointmentAjax?diaryslotid="+diaryslotid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getSetupDiaryDurationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
} 

function getSetupDiaryDurationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			if(actionType==5){
//				document.getElementById('client').value=puresevaclientname;
				}
			var duration = tduration;
			diaryduration = req.responseText;
			
			if(editAppointId==0){
				if(diaryduration!='0'){
					duration = diaryduration;
				}
			}
			/*executeonce=0;*/
			tempDuration=duration;
			total = "00:00:00";
			globalEndTime = etime;
			
//			if(actionType==5){
//				duration=durationss;
//			}
			
			document.getElementById('apmtDuration').value = duration;
			var stime = document.getElementById('sTime').value;
			var stimedata=stime.split(":");
			var stimeampm=formatAMPM123(stimedata[0],stimedata[1]);
			document.getElementById('sTimelable').innerHTML=stimeampm;

			if(checkEventExist=='true'){
				var tmp=stime.split(':');
				var hh=tmp[0];
				var mm=tmp[1];
				var time = moment(hh+':'+mm,'HH:mm');
				var dur=duration.split(':');
				time.add(dur[1],'m');
				var bbb=time.format("HH:mm");
				var stime=bbb;
				document.getElementById('sTime').value=stime;
				var stimedata=stime.split(":");
				var stimeampm=formatAMPM123(stimedata[0],stimedata[1]);
				document.getElementById('sTimelable').innerHTML=stimeampm;
				executeonce=1;
			}
			var total = getTimeTotal(duration,stime);
			
			if(total=='000:00'){
				total = '24:00';
			}
			
			//alert(total)
			document.getElementById('endTime').value = total;
			var totaledata=total.split(":");
			var etimeampm=formatAMPM123(totaledata[0],totaledata[1]);
//			document.getElementById('endTimelable').innerHTML=etimeampm;
			document.getElementById('bookdatelable').innerHTML=document.getElementById('commencing').value;
			var commencing = document.getElementById('date').value;
			var location = document.getElementById('location').value;
			var diaryuserId = document.getElementById('diaryUserId').value;
			checkEventAllreadyExist(commencing,location,diaryuserId,stime,total,editAppointId);
			//alert(commencing +"/"+ location +"/"+ diaryuserId);
			if(actionType==5){
				document.getElementById('apmtType').value=appointmenttypeid;
				}
			
		}
	}
}

//check event exist
function checkEventAllreadyExist(commencing,location,diaryuserId,starttime,endtime,editAppointId){
	
	var url = "eventExistBookAppointmentAjax?commencing="+commencing+"&location="+location+"&diaryuserId="+diaryuserId+"&starttime="+starttime+"&endtime="+endtime+"&editAppointId="+editAppointId+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkEventAllreadyExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function checkEventAllreadyExistRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			checkEventExist = req.responseText;
			setPendingAmountOfClient(lastAppointmentClientid);
			if(checkEventExist=='true'){
				/*if(executeonce==0){*/
				getSetupDiaryDuration();
				/*}*/
			}
			if(actionType==5){
//				document.getElementById('client').value=puresevaclientname;
				}				
		}
	}
}
function setPendingAmountOfClient(lastAppointmentClientid){
	var url = "setPendingAmountOfClientBookAppointmentAjax?clientId="+lastAppointmentClientid+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPendingAmountOfClientRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setPendingAmountOfClientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('amountPending').innerHTML  = '';
			
			if(editpateintpayby=='Client'){
				document.getElementById('amountPending').innerHTML = "Balance: "+currencySign +" "+ req.responseText;
			}else{
				document.getElementById('amountPending').innerHTML  = '';
			}
			
			setlastbookappointment(dbid);
			
		
		}
	}
}

function setConditionOfPatient(lastAppointmentClientid){
	var url = "getConditionPatientBookAppointmentAjax?clientId="+lastAppointmentClientid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setConditionOfPatientRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function setConditionOfPatientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			/*document.getElementById('condition').value = req.responseText;
			
			$("#condition").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});*/
			
			//next ajax
			getLastAppointmentidAjax(lastAppointmentClientid);
		
		}
	}
}


function setBookAppointmentEndTime(duration){
	tempDuration = duration;
	var stime = document.getElementById('sTime').value;
	var endtime = getTimeTotal(stime,duration);
	
	if(endtime=='000:00'){
		endtime = '24:00';
	}
	
	document.getElementById('endTime').value = endtime;
	
	
	var commencing = document.getElementById('date').value;
	var location = document.getElementById('location').value;
	var diaryuserId = id1;
	
	//alert(commencing +"/"+ location +"/"+ diaryuserId);
	checkEventAllreadyExist(commencing,location,diaryuserId,stime,endtime,editAppointId);
}

//block div js code
function setBlockDivEndTime(duration){
	tempDuration = duration;
	var stime = document.getElementById('blocksTime').value;
	var endtime = getTimeTotal(stime,duration);
	
	
	if(endtime=='000:00'){
		endtime = '24:00';
	}
	
	document.getElementById('blockendTime').value = endtime;
	
	
	var commencing = document.getElementById('blockdate').value;
	var location = document.getElementById('blocklocation').value;
	var diaryuserId = id1;
	
	//alert(commencing +"/"+ location +"/"+ diaryuserId);
	checkEventAllreadyExist(commencing,location,diaryuserId,stime,endtime,editAppointId);
	
}

function resetBlockDivField(){
	
	//document.getElementById('blockendTime').value = etime;
	//document.getElementById('blockapmtDuration').value = '0';
	setBlockDivEndTime(tempDuration);
}



function getCalDate(caldate,month,year){
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
	
	
	caldate = caldate + "-" + month + "-" + year;
	//alert(caldate);
	return caldate;
}


var dbname = "";
var dbid = "";
var dbtype = "";
var dbtypename = "";
var dbpayby = "";
var dbgpid = "";
function setClientName(id,type,typeName,payby,gpid){
//		window.opener.setValue(name,id,type,typeName);
//		window.close();
//		return false;
	var name= '';
		if(actionType !=5){
			name = document.getElementById("firstnameid"+id).value;
		}
		
		if(actionType ==5){
			mobselectedclient=puresevaclientname;
			name = mobselectedclient;
			
		}
			document.getElementById('puresevaclntname').innerHTML = name.split('/').join(" ");
	 document.getElementById('btnBookWithPayment').className = 'btn btn-primary';
	 $('#btnBookWithPayment').show();
	
var isot = document.getElementById('radio3').checked;
	
	
	if(isot==true){
		document.getElementById('morechargesspanid').style.display = '';
		
	}
	
	 dbname = name;
	 dbid = id;
	 dbtype = type;
	 dbtypename = typeName;
	 dbpayby = payby;
	 dbgpid = gpid;
	 
	 $('#clientSearchDiv').modal( "hide" );
		
	// $('#dashboardloaderPopup').modal( "show" );
	
	setSelectedClientSessionRecordDBAjax();
	
	//setValue(dbname,dbid,dbtype,dbtypename,dbpayby,dbgpid);
	//document.getElementById('addTreatment').disabled = true;
	
	
        
		
}



function setSelectedClientSessionRecordDBAjax(){
	
	var url = "selectedClient?selectedid="+dbid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSelectedClientSessionRecordDBAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setSelectedClientSessionRecordDBAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			setValue(dbname,dbid,dbtype,dbtypename,dbpayby,dbgpid);
			
		}
	}
}




function setValue(name,id,type,typeName,payby,gpid){
		
	
	document.getElementById('exclusivetpname').innerHTML  = '';
		
		document.getElementById("clientId").value = id;
		//set cookie data
		
		document.cookie = "cookieClientId=" + id;
		document.getElementById('clientId').value = id;
		
		
		
		clientExclusiveName = name;
		clientId = id;
		patientId = id;
		globalTpType = type;
		globaltpid = typeName;
		editpateintpayby = payby;
		editgpid = gpid;
		
		if(globalTpType==2){
			document.getElementById('paybypatient1').checked = true;
			//document.getElementById('sessionDetail1').innerHTML = "";
			getClientTpCompanyNameAjax(clientId);
			
			//setThirdPartyExclusiveAppointmentType('tp',globaltpid);
			
		}else if(payby=='tp'){
			document.getElementById('paybypatient1').checked = true;
			//document.getElementById('sessionDetail1').innerHTML = "";
			getClientTpCompanyNameAjax(clientId);
			
		}
		else{
			document.getElementById('paybypatient').checked = true;
			//document.getElementById('sessionDetail1').innerHTML = "";
			
			getSelfAppointmentTypeListAgax('Client',globaltpid);
		}
		
		
		
}

function getSelfAppointmentTypeListAgax(whopay,tpid){

	var isot = document.getElementById('radio3').checked;
	var commencing=document.getElementById('commencing').value;
	var otprocedureplaned = document.getElementById('otprocedureplaned').value;
	var docid=document.getElementById('diaryUsersss').value;
	var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+"&otprocedureplaned="+otprocedureplaned+"&clientId="+clientId+"&commencing="+commencing+"&docid="+docid+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getSelfAppointmentTypeListAgaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getSelfAppointmentTypeListAgaxRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			var data1=data.split("~~");
			document.getElementById('tpappointmenttype').innerHTML =data1[0];
			
			document.getElementById('apmtType').value = data1[1];
			$("#apmtType").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			getClientFullName(clientId,clientExclusiveName);
			if(document.getElementById('lastapp')){
				document.getElementById('lastapp').innerHTML = "";
				
			}if(document.getElementById('lastappdate')){
				document.getElementById('lastappdate').innerHTML = "";
				
			}
		if(document.getElementById('lastappcharges')){
			document.getElementById('lastappcharges').innerHTML = "";
			
		}
		}
	}

}


function getClientTpCompanyNameAjax(clientId){
	var url = "partyAppointmentType?clientId="+clientId+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getClientTpCompanyNameAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
} 

function getClientTpCompanyNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var cname = req.responseText;
			
			document.getElementById('exclusivetpname').innerHTML = '('+cname+')';
			setThirdPartyExclusiveAppointmentType('tp',globaltpid);
		}
		
	}
	
}


function setThirdPartyExclusiveAppointmentTypeOnly(whopay,tpid){
	
	var isot = document.getElementById('radio3').checked;
	var otprocedureplaned = document.getElementById('otprocedureplaned').value;
	var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+"&otprocedureplaned="+otprocedureplaned+" ";

	//var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+" ";
	
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setThirdPartyExclusiveAppointmentTypeOnlyRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setThirdPartyExclusiveAppointmentTypeOnlyRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('tpappointmenttype').innerHTML = req.responseText;
			
			
			$("#apmtType").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			document.getElementById('apmtType').value = 0;
			
			
		}
	}
} 



function setThirdPartyExclusiveAppointmentTypeEdit(whopay,tpid){

	var isot = document.getElementById('radio3').checked;
	var otprocedureplaned = document.getElementById('otprocedureplaned').value;
	var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+"&otprocedureplaned="+otprocedureplaned+" ";
	//var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setThirdPartyExclusiveAppointmentTypeEditRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setThirdPartyExclusiveAppointmentTypeEditRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('tpappointmenttype').innerHTML = req.responseText;
			
			document.getElementById('apmtType').value = editAppointType;
			$("#apmtType").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			setAppointmentTypeTime(editDuration);
			/*var temp = clientExclusiveName.split('/');
			
			var clientFullName = temp[0] + ' ' + temp[1] + ' ' + temp[2];
			
			document.getElementById('client').value = clientFullName;
			document.getElementById('findclient').value = clientFullName;
			document.cookie = "cookieUserName=" + clientFullName;
			document.getElementById('invoicee').value = clientFullName;
			clientName = clientFullName;
			editClientName = clientFullName;
			
			masterClientID = clientId;*/
			if(editTreatmentEpisode!=0){
				setEpisodeDetails(editTreatmentEpisode);
			}
			
		}
	}
} 


function setThirdPartyExclusiveAppointmentType(whopay,tpid){

	var isot = document.getElementById('radio3').checked;
	var commencing=document.getElementById('commencing').value;
	var otprocedureplaned = document.getElementById('otprocedureplaned').value;
	var docid=document.getElementById('diaryUsersss').value;
	var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+"&isot="+isot+"&otprocedureplaned="+otprocedureplaned+"&clientId="+clientId+"&commencing="+commencing+"&docid="+docid+" ";
	//var url = "tpAppointmentType?selectedid="+tpid+"&whopay="+whopay+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setThirdPartyExclusiveAppointmentTypeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setThirdPartyExclusiveAppointmentTypeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var data=req.responseText;
			var data1=data.split("~~");

			document.getElementById('tpappointmenttype').innerHTML = data1[0];
			document.getElementById('apmtType').value = data1[1];
			$("#apmtType").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			var temp = clientExclusiveName.split('/');
			
			var clientFullName = temp[0] + ' ' + temp[1] + ' ' + temp[2];
			
			document.getElementById('client').value = clientFullName;
			document.getElementById('findclient').value = clientFullName;
			document.cookie = "cookieUserName=" + clientFullName;
			//document.getElementById('invoicee').value = clientFullName;
			clientName = clientFullName;
			editClientName = clientFullName;
			
			masterClientID = clientId;
			
			setTreatmentEpisode(masterClientID);
		}
	}
} 




/*function setPayBy(payby){
	if(payby == 'Third Party'){
		document.getElementById('authorisationCode').disabled = '';
		
		
		
		setThirdPartyNameAjax();
	}else{
		document.getElementById('invoicee').value = clientName;
		document.getElementById("authorisationCode").disabled = true;
		document.getElementById('spendLimit').disabled = false;
		document.getElementById('consultationLimit').disabled = false;
		
		
		
		
	}
	

}	*/

function setThirdPartyNameAjax(){
	
	//var clientid = clientId;
	
	
	
	var url = "thirdpartyTreatmentEpisode?clientid="+patientId+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setThirdPartyNameAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setThirdPartyNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById('invoicee').value = req.responseText;
			if(req.responseText == 'null' || req.responseText == " "){
				$('#addPatientThirdPartyPopup').modal( "show" );
				document.getElementById('invoicee').value = '';
				document.getElementById('authorisationCode').disabled = false;
				document.getElementById('spendLimit').disabled = false;
				document.getElementById('consultationLimit').disabled = false;
				
			}
			else{
				document.getElementById('invoicee').value = req.responseText;
			}
		}
	}

}

var lastAppointmentClientid = 0;

function setTreatmentEpisode(clientid){
	//alert(editpateintpayby)
	
	var payee = "";
	lastAppointmentClientid = clientid;
	
	//var clientid = read_cookie("cookieClientId");

	if(editpateintpayby == 'Client'){
		payee = "Client";
		document.getElementById("tpDIV").className="col-lg-3 col-md-3 col-sm-3 col-xs-12 hidden";
	}
	else{
		payee = "Third Party";
		document.getElementById("tpDIV").className="col-lg-3 col-md-3 col-sm-3 col-xs-12";
		document.getElementById("btnBookWithOutPayment").className="btn btn-primary";
	}
	
	var url = "setTreatmentEpisode?clientid="+clientid+"&payby="+payee+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTreatmentEpisodeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setTreatmentEpisodeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('treatmentepisodeajax').innerHTML = req.responseText;
			//next ajax
			setConditionOfPatient(lastAppointmentClientid);
			
		}
	}
}



function setNewTreatmentEpisodeAjax(clientid){
	
	var payee = "";
	
	if(document.getElementById('paybypatient1').checked==true){
		payee = "Third Party";
	}
	else{
		payee = "Client";
	}
	
	var url = "setTreatmentEpisode?clientid="+clientid+"&payby="+payee+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setNewTreatmentEpisodeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setNewTreatmentEpisodeAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('treatmentepisodeajax').innerHTML = req.responseText;
			
			document.getElementById('treatmentEpisode').value = 0;
			
			$("#treatmentEpisode").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			document.getElementById('sessionDetail').innerHTML = "";
			
			
		}
	}
}



function getLastAppointmentidAjax(lastAppointmentClientid){
	var commencing=document.getElementById('commencing').value;
	var docid=document.getElementById('diaryUsersss').value;
	var payby=document.querySelector('input[name="paybypatient"]:checked').value;
	
	var url = "lastapmtTreatmentEpisode?clientid="+lastAppointmentClientid+"&apmtId="+editAppointId+"&commencing="+commencing+"&docid="+docid+"&tpid="+globaltpid+"&payby="+payby+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getLastAppointmentidAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function getLastAppointmentidAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var result = req.responseText;
			
			var temp = result.split('/');
			
			if(temp[6]=='Third Party' || temp[6]=='null'){
				document.getElementById('amountPending').innerHTML  = '';
			}
			
			grefenddate = temp[7];
			greffromdate = temp[8];
			
			if(temp[0]!='null'){
				
				document.getElementById('apmtType').value = temp[0];
				$("#apmtType").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});

				document.getElementById('treatmentEpisode').value = temp[1];
				$("#treatmentEpisode").trigger("chosen:updated");

				document.getElementById('sessionDetail').innerHTML="";
				document.getElementById('treatmentEpisodeError').innerHTML="";
				
			 	var type = document.createElement("label");
			 	
			 	var episode = "";
			 	
			 	if(temp[1]!=0){
			 		episode = temp[4];
			 		if(temp[3]==temp[4]){
			 			type.id = "sessionDetail1";
					   	type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
					   	document.getElementById('sessionDetail').appendChild(type);
			 		}else{
			 			episode = parseInt(temp[4]) + 1;
			 			type.id = "sessionDetail1";
				 		var usedsession = parseInt(temp[3]);
					   	type.innerHTML = "(Treatment Session "+episode+" of "+usedsession+" )";
					   	document.getElementById('sessionDetail').appendChild(type);
			 		}
			 	}else{
			 		document.getElementById('sessionDetail').innerHTML = "";
			 	}
			 	
			 	
			 	
			 /*	if(editAppointId < 0 && temp[1]==temp[5])
			 	{
			 		if(parseInt(episode)<=parseInt(temp[3])){
				 		
				 		type.id = "sessionDetail1";
				 		var usedsession = parseInt(temp[3]);
					   	type.innerHTML = "(Treatment Session "+episode+" of "+usedsession+" )";
					   	document.getElementById('sessionDetail').appendChild(type);
				 	}
				 	else if(temp[1] == '0' || temp[1] == 0){
				 		document.getElementById('sessionDetail').innerHTML = "";
				 		
				 	}
				 	
				 	
				 	else{
				 		
				 		type.id = "sessionDetail1";
					   	type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
					   	document.getElementById('sessionDetail').appendChild(type);
				 		
				 	}
			 	}
			 	
			 	else if(temp[1]!=0){
			 		if(temp[3]==temp[4]){
			 			type.id = "sessionDetail1";
					   	type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
					   	document.getElementById('sessionDetail').appendChild(type);
			 		}
			 		
			 	}
			 	
			 	
			 	else{
			 	episode = parseInt(temp[4]) + 1;
			 	
			 	
			 	if(parseInt(episode)<=parseInt(temp[3])){
			 		
			 		type.id = "sessionDetail1";
			 		var usedsession = parseInt(temp[3]);
				   	type.innerHTML = "(Treatment Session "+episode+" of "+usedsession+" )";
				   	document.getElementById('sessionDetail').appendChild(type);
			 	}
			 	else if(temp[1] == '0' || temp[1] == 0){
			 		document.getElementById('sessionDetail').innerHTML = "";
			 		
			 	}
			 	
			 	
			 	else{
			 		
			 		document.getElementById('sessionDetail').innerHTML = "";
			 		
			 	}
			 	}*/
				
			 
				//setAppointmentTypeTimeAjax(temp[0]);
			 	setAppointmentDurationAjax(temp[0]);
			}else{
				document.getElementById('sessionDetail').innerHTML = "";
			}
			
//			setAppointmentDurationAjax(temp[0]);
			
			$('#dashboardloaderPopup').modal( "hide" );
		}
	}
	
}


function setAppointmentDurationAjax(selectedid){
	if(actionType==5){
//	document.getElementById('client').value=puresevaclientname;
	}
	var url = "durationBookAppointmentAjax?selectedid="+selectedid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAppointmentDurationAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function setAppointmentDurationAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var duration = req.responseText;
		
			setAppointmentTypeTime(duration);
			document.getElementById('apmtTypeDuration').innerHTML = "Duration: "+duration;
			
			
			
			//set third party appointment type
		 	/*if(globalTpType==2){
				document.getElementById('paybypatient1').checked = true;
				setTpAppointmentTYpe('Third Party');
				
			}else{
				document.getElementById('paybypatient').checked = true;
				setTpAppointmentTYpe('Client');
				
			}*/
			
		}
	}
}



function saveTreatment(){
	
	var chk=0;
	/*var client = document.getElementById('client').value;
	var clientId = document.getElementById('clientId').value;*/
	
	var date = document.getElementById('date').value;
	var diaryUser = document.getElementById('diaryUserId').value;
	var condition = document.getElementById('treatmentType1').value;
	
	var treatmentEpisodeName = document.getElementById('treatmentEpisodeName').value;
	var referalDate = document.getElementById('referalDate').value;
	var referralName = document.getElementById('referralName').value;
	var referalendDate = document.getElementById('referalendDate').value;
	var referralSource = document.getElementById('referralSource').value;
	//var condition = document.getElementById('treatmentType').value;
	/*var referralContact = document.getElementById('referralContact').value;
	var referralLetter = document.getElementById('referralLetter').value;*/
	var payby = "";
	var invoicee1 = document.getElementById('invoicee').value;
	var authorisationCode = document.getElementById('authorisationCode').value;
	var spendLimit = document.getElementById('spendLimit').value;
	var consultationLimit = document.getElementById('consultationLimit').value;
	

	
	document.getElementById('condition').value = condition;
	$("#condition").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	document.getElementById('authorisationCodeError').innerHTML = "";
	document.getElementById('treatmentTypeError').innerHTML = "";
	document.getElementById('consultationLimitError').innerHTML = "";
	document.getElementById('treatmentNameError').innerHTML ="";
	document.getElementById('invoiceeError').innerHTML ="";
	if(document.getElementById('payby1').checked){
		payby = "Client";
	}
	
	
	/*if (spendLimit ==  " ") {
      	var spendLimit = document.createElement("label");
      	spendLimit.innerHTML = "Enter Spend Limit";
        document.getElementById('spendLimitError').appendChild(spendLimit);
        chk=1;
     }  */
	if (condition == 0) {
      	var condition = document.createElement("label");
      	condition.innerHTML = "Select Condition";
        document.getElementById('treatmentTypeError').appendChild(condition);
        chk=1;
     } 
	if (consultationLimit == "" || consultationLimit == undefined) {
      	var consultationLimit = document.createElement("label");
      	consultationLimit.innerHTML = "Enter Consultation Limit";
        document.getElementById('consultationLimitError').appendChild(consultationLimit);
        chk=1;
     }  
	if (invoicee1 ==  "" || invoicee1 == null || invoicee1 == undefined) {
      	var consultationLimit = document.createElement("label");
      	consultationLimit.innerHTML = "Enter Invoice";
        document.getElementById('invoiceeError').appendChild(consultationLimit);
        chk=1;
     }  
	
   	if (treatmentEpisodeName == "") {
      	var treatmentEpisodeName = document.createElement("label");
      	treatmentEpisodeName.innerHTML = "Enter Name";
        document.getElementById('treatmentNameError').appendChild(treatmentEpisodeName);
        chk=1;
     }  
	
	if(document.getElementById('payby').checked){
		payby = "Third Party";
		if (authorisationCode ==  "") {
	      	var authorisationCode = document.createElement("label");
	      	authorisationCode.innerHTML = "Enter Code";
	        document.getElementById('authorisationCodeError').appendChild(authorisationCode);
	        chk=1;
	     }  
	}
	
   	
   	if(chk==1)
    {
       return false;
    }
    else
    {
     
    var urgent = document.getElementById('urgent').checked;
    
    var ipdopd = '0';
	 if(document.getElementById('opd').checked==true){
		 ipdopd = '0';
	 }else{
		 ipdopd = '1';
	 }
    
     var url = "saveTreatmentEpisode?treatmentEpisodeName="+treatmentEpisodeName+"&payby="+payby+"&invoicee="+invoicee1+"&authorisationCode="+authorisationCode+"&spendLimit="+spendLimit+"&consultationLimit="+consultationLimit+"&client="+editClientName+"&clientId="+patientId+"&date="+date+"&diaryUser="+diaryUser+"&treatmentType="+condition+"&referalDate="+referalDate+"&referralName="+referralName+"&referralSource="+referralSource+"&urgent="+urgent+"&ipdopd="+ipdopd+"&referalendDate="+referalendDate+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveTreatmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    }
	
	
}

function saveTreatmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		
			
			//setTreatmentEpisode(patientId);
			
			setNewTreatmentEpisodeAjax(patientId);
			
			
			 $( "#addTreatmentEpisodeDiv" ).modal( "hide" );
			jAlert('success', 'Treatment Episode Added Successfully.', 'Success Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				$("#popup_overlay").css({
					position: '',
					zIndex: 0,
					top: '0px',
					left: '0px',
					width: '',
					height: '',
					background: '',
					opacity:''
				});
			}, alertmsgduration);
			//tempAlert("Treatment Episode Added Successfully.",5000);

			resetTreatmentEpisodeFields();
			
			//openPopup('inputIpd');
			
			
		}
	}
}

function resetTreatmentEpisodeFields(){
	document.getElementById('treatmentEpisodeName').value = "";
	/*document.getElementById('referalDate').value = "";
	document.getElementById('referralName').value = "";
	document.getElementById('referralSource').value = "0";*/
	/*document.getElementById('referralContact').value = "";
	document.getElementById('referralLetter').value = "";*/
	//$('input[id=payby]').attr('checked',false);
	//document.getElementById("payby1").checked = true;
	document.getElementById('invoicee').value = " ";
	document.getElementById('authorisationCode').value = "";
	document.getElementById('spendLimit').value = "";
	document.getElementById('consultationLimit').value = "";
}
function resetAddClientFileds(){
	//alert('hi');
	document.getElementById('title').value = "Mr.";
	document.getElementById('firstName').value ="";
	document.getElementById('middleName').value="";
	document.getElementById('lastName').value = "";
	document.getElementById('gender').value = "Male";
	document.getElementById('dob').value = "";
	document.getElementById('address').value = "";
	//document.getElementById('town').value = "";
	document.getElementById('postCode').value = "";
/*	document.getElementById('reference').value = "0"; 
*/	document.getElementById('mobNo').value = "";
	document.getElementById('email').value = "";
	document.getElementById('doctorsurgery').value = "0";
	document.getElementById('gpname').value= "0";
	document.getElementById('treatmentType').value= "0";
	document.getElementById('whopay').value = "0";
	document.getElementById('type').value = "0";
	document.getElementById('typeName').value = "0";
	document.getElementById('policyNo').value = "";
	document.getElementById('expiryDate').value = "";
	
	//  29 Jan 2018
	//document.getElementById('town').className = "";
	document.getElementById('town').value = "0";
	//document.getElementById('town').className = "form-control showToolTip chosen-select";
	
	
	//document.getElementById('state').className = "";
	document.getElementById('state').value = "0";
	//document.getElementById('state').className = "form-control showToolTip chosen-select";
	
	document.getElementById('age').value="";
}

function resetPatientThirdPartyDetails(){
	document.getElementById("ctpthirdPartyType").value = "0";
	document.getElementById("ctpName").value = "0";
	document.getElementById("thirdPartyPolicyNo").value = "";
	document.getElementById("thirdPartyExpiryDate").value = "";
	
}
function resetNewThirdPartyDetails(){
	document.getElementById("thirdPartyType1").value = "0";  
	document.getElementById("thirdPartyCompanyName").value = "";  
	document.getElementById("compnyEmail").value = "";
	document.getElementById("compnyTelephone").value = "";  
	document.getElementById("outInvoiceLimit").value = "";
	document.getElementById("accountWarningLimit").value = "";  
	document.getElementById("dnaLimit").value = "";
}
function checkTreatAlreadyExit(){
	var treatmentEpisodeName = document.getElementById('treatmentEpisodeName').value;
	/*var client = document.getElementById('client').value;
	var clientId = document.getElementById('clientId').value;*/
	
	
 	
	var url = "checkNameTreatmentEpisode?treatmentEpisodeName="+treatmentEpisodeName+"&client="+editClientName+"&clientId="+patientId+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkTreatAlreadyExitRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    }
	
	


function checkTreatAlreadyExitRequest(){
	var chk = 0;
	document.getElementById('treatmentNameError').innerHTML="";
	if (req.readyState == 4) {
		if (req.status == 200) {
			var status = req.responseText;
			//alert(status);
			
		 	if (status ==  'false') {
		 		document.getElementById('treatmentNameError').innerHTML="";
		      	var treatmentEpisodeName = document.createElement("label");
		      	treatmentEpisodeName.innerHTML = "Already Exist";
		        document.getElementById('treatmentNameError').appendChild(treatmentEpisodeName);
		        chk=1;
		     }  
		 	
		 	if(chk==1)
		    {
		       return false;
		    }
		    else
		    {
			 return true;
		    }
			
		}
	}
}
function closeAddTreatmentDiv(){
	$(document.getElementById('dashboardDiv')).css('width', '40%');
	document.getElementById('anothertd').style.display = 'none';
	document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
	document.getElementById('treatmenttd').style.display = 'none';
	document.getElementById('appointment').style.display = '';
	document.getElementById('clientSearchDiv').style.display = 'none';
	document.getElementById('addPatientDiv').style.display = 'none';
}

/*function setTypeName(id){

	alert('hello');
	var url = "setTypeNameDropDownClient?id="+id+" ";

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTypeNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
    if(document.getElementById("type").value == 2 && document.getElementById("whopay").selectedIndex == 1) 
	{
		 alert('hi');
			//document.getElementById("type").disabled = false;
			//document.getElementById("typeName").disabled = false;
			document.getElementById("policyNo").disabled = false;
			document.getElementById("expiryDate").disabled = false;
			document.getElementById("policyExcess").disabled = false;
	}
	else{
		//document.getElementById("type").disabled = true;
			//document.getElementById("typeName").disabled = true;
			document.getElementById("policyNo").disabled = true;
			document.getElementById("expiryDate").disabled = true;
			document.getElementById("policyExcess").disabled = true;
	}

}
*/

function setTypeNameRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		

    		document.getElementById("typeName").innerHTML = req.responseText;
    		$("#typeName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
         
         }
		
	}

}

function confirmedDelete(){

var r=confirm("Are you sure you want to delete this entry");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }

}

function getWeekPrintData(){
var practitionerId = document.getElementById('diaryUser').value;
var date = document.getElementById('commencing').value;


var e = document.getElementById('diaryUser');
var practitioner = e.options[e.selectedIndex].text;

var url = "getPrintDataOfWeekNotAvailableSlot?practitionerId="+practitionerId+"&date="+date+"&practitioner="+practitioner+"";

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getWeekPrintDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function getWeekPrintDataRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("printData").innerHTML = req.responseText;
			$( "#previewPopup" ).dialog( "open" );
			// $( "#previewPopup" ).modal( "hide" );
         
         }
	}
}

function getPrintData(){
var practitionerId = document.getElementById('diaryUser').value;
var date = document.getElementById('commencing').value;

var e = document.getElementById('diaryUser');
			var practitioner = e.options[e.selectedIndex].text;

var url = "getPractionerPrintDataNotAvailableSlot?practitionerId="+practitionerId+"&date="+date+"&practitioner="+practitioner+"";


if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getPrintDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function getPrintDataRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("printData").innerHTML = req.responseText;
			$( "#previewPopup" ).dialog( "open" );
		//	$( "#previewPopup" ).modal( "show" );
         
         }
	}
}


function getPrintDataOfAll(t){

	
	document.getElementById('printCommencing').value = document.getElementById('commencing').value;
	document.getElementById('printLocation').value = document.getElementById('locationid').value;
	document.getElementById('printDiaryserid').value = document.getElementById('diaryUser').value;

var left = (screen.width / 2) - (700 / 2);
var top = (screen.height / 2) - (550 / 2);
var oldwindow =  window.open("", t,
		"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
			+ ",top=" + 50);

oldwindow.focus();

return true;
	
	
	/*var date = document.getElementById('commencing').value;
var url = "getAllPrintDataNotAvailableSlot?date="+date+"";


if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getPrintDataOfAllRequest;
    req.open("GET", url, true); 
              
    req.send(null);
*/
}


/*function getPrintDataOfAllRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		 
			document.getElementById("printData").innerHTML = req.responseText;
			$( "#previewPopup" ).dialog( "open" );
			//$( "#previewPopup" ).modal( "show" );
         
         }
	}
}

*/

function showApmtAvalblty(){
	//$( "#checkavlbltydivpopup" ).dialog( "open" );
	$( "#checkavlbltydivpopup" ).modal("show");
	
}
var masterClientID = 0;

function getClientFullName(id,name){
	
	var temp = name.split('/');
	
	var clientFullName = temp[0] + ' ' + temp[1] + ' ' + temp[2];
	
	document.getElementById('client').value = clientFullName;
	document.getElementById('findclient').value = clientFullName;
	document.cookie = "cookieUserName=" + clientFullName;
	if(document.getElementById('invoicee')){
		document.getElementById('invoicee').value = clientFullName;
	}
	clientName = clientFullName;
	editClientName = clientFullName;
	
	masterClientID = id;
	
	setTreatmentEpisode(masterClientID);

	
	/*var url = "getFullNameClient?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getClientFullNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);*/

}



function getClientFullNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			clientFullName = req.responseText;
			
			document.getElementById('client').value = clientFullName;
			document.getElementById('findclient').value = clientFullName;
			document.cookie = "cookieUserName=" + clientFullName;
			document.getElementById('invoicee').value = clientFullName;
			clientName = clientFullName;
			editClientName = clientFullName;
			
			// next ajax
			setTreatmentEpisode(masterClientID);
			
			
		}
	}

}


function setblockduration(endtime){
	
	var starttime = document.getElementById('blocksTime').value;
	
	var duration = getTimeSubstration(endtime,starttime);
	
	document.getElementById('blockapmtDuration').value = duration;
}



function updateClientCondition(id){
	var clientId5 = document.getElementById('clientId').value;
	var url = "updateClientConditionNotAvailableSlot?id="+id+"&clientId="+clientId5+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updateClientConditionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function updateClientConditionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
		}
	}
}


function redirectconstoemr(){
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var t = 'formtarget';

	document.getElementById('clientnameApmt').value = patientId;
	document.getElementById('diaryUserApmt').value = diaryuserId;
	document.getElementById('conditionsApmt').value = editcondition_id;
	document.getElementById('hdnaction').value = 'emr';
	document.getElementById('emrapmtId').value = editAppointId;

	/* document.getElementById('getPatientRecord').submit(); */

	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

	document.getElementById('getPatientRecord').submit();

	
}


function redircttoNewEmr(clientid,practitionerid,conditionid){
	/*<s:hidden name="diaryUser" id="diaryUserApmt"/>
    <s:hidden id = "conditionsApmt"  name = "conditionsApmt"></s:hidden>
    <s:hidden id="clientname" name="clientnameApmt"/>
       <s:hidden name="action" id="hdnaction"/>
            <s:hidden name="caldate" id="caldate"/>
    */
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var t = 'formtarget';

	document.getElementById('clientnameApmt').value = clientid;
	document.getElementById('diaryUserApmt').value = practitionerid;
	document.getElementById('conditionsApmt').value = conditionid;
	document.getElementById('hdnaction').value = 'emr';
	document.getElementById('emrapmtId').value = editAppointId;

	/* document.getElementById('getPatientRecord').submit(); */

	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

	document.getElementById('getPatientRecord').submit();
	
	
}

function sendAutoSMSAjax(appointmentid){
	
	var url = "autosmsNotAvailableSlot?appointmentid="+appointmentid+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendAutoSMSAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function sendAutoSMSAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert("mail sent successfully")
		}
	}
	
}

function sendAppointmentAutoMaticEmail(appointmentid){
	
	var url = "autoemailNotAvailableSlot?appointmentid="+appointmentid+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendAppointmentAutoMaticEmailRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function sendAppointmentAutoMaticEmailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert("mail sent successfully")
		}
	}
	
}

function showApptTreatment(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "tmentepisodecountClientLog?whopay="+0+"&treatmentEpisodeid="+0+"&clientId="+0+"&apptid="+editAppointId+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = viewTreatmentEpisodeCountDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function viewTreatmentEpisodeCountData(){
	
	
	 var whopay = "";
	 
	 if(document.getElementById('paybypatient').checked==true){
		 whopay = "Client";
	 }else{
		 whopay = "Third Party"; 
	 }
	 var treatmentEpisodeid = document.getElementById('treatmentEpisode').value;
	 
	 var client = document.getElementById('client').value;
	 var clientId = document.getElementById('clientId').value;
	 
	 if(client ==""){
	        jAlert('error', 'Plese Select Client.', 'Error Dialog');
	    	setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	    }else if(treatmentEpisodeid==0){
	    	   jAlert('error', 'Plese Select Treatment Episode.', 'Error Dialog');
		    	setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	    }else{
	    	
	    	var url = "tmentepisodecountClientLog?whopay="+whopay+"&treatmentEpisodeid="+treatmentEpisodeid+"&clientId="+clientId+"&apptid="+editAppointId+" ";
			
		    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = viewTreatmentEpisodeCountDataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	    }
	 
		
	 
	
}

function viewTreatmentEpisodeCountDataRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 $( "#treatmentEpisodeCounterViewPopup" ).modal( "show" );	
			 document.getElementById('showtpcountdiv').innerHTML = req.responseText;
			 document.getElementById('showtptitlediv').innerHTML = document.getElementById('ttlname').value;
			 
			//alert("mail sent successfully")
		}
	}
}



function getApmLoggedInUserList(){
	
	
	var url = "loggedinuserBookAppointmentAjax";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getApmLoggedInUserListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function getApmLoggedInUserListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert("mail sent successfully")
			apmuserlist = req.responseText;
		}
	}
	
}


function showApmtWeekList(val){
	
	/*document.getElementById('weekNameListdiv').style.display = 'none';
	 $(document.getElementById('bookapmtrptdiv')).css('height', '0px');*/
}

function showcrddebit(){
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	
	openEmrPopup('creditAdditional?clientid='+patientId+'&apmtid='+editAppointId+' ')
}

function adddebitchargess(){
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	patientId = pppid;
	openEmrPopup('debitAdditional?clientid='+patientId+'&apmtid='+editAppointId+' ')
}



	function withpaymentCompleteAppointment(){
	
			var url = "opdcompleteCompleteApmt?appointmentid="+editAppointId+"";
			
		    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = withpaymentCompleteAppointmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);		
	}
	
	
	function withpaymentCompleteAppointmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
				$("#modifyPopup").modal("hide");
				setCommonAction();
				
		}
	}
	
}
	
	function setopdInstantCashDesk(invoiceid){
		
		
		
		//checkAppointmentInvoicedForCashDesk();
		
		document.getElementById('cashClientid2').value = pppid; 
		document.getElementById('cashthirdparty2').value = globaltpid;
		document.getElementById('cashLocationid2').value = 1;
		document.getElementById('cashclientname2').value = pppcname;
		document.getElementById('cashPayby2').value = pppwhopay;
		
		document.getElementById('cashAppointmentid2').value = editAppointId;
		//document.getElementById('cashinvoiceid').value = invoiceid;
		document.getElementById('cashdeskfrm1').submit();
	}	
	
function saveOptimalForm(opdid,clientid){
	document.getElementById('saveoptionalform').submit();
}
	
function setlastbookappointment(clientid){
	if(actionType==5){
//		document.getElementById('client').value=puresevaclientname;
		}
	var url = "opdlastappointmentCompleteApmt?clientid="+clientid+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setlastbookappointmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);		
}


function setlastbookappointmentRequest(){
if (req.readyState == 4) {
if (req.status == 200) {
	var str = req.responseText;
	  var data = str.split("~");
	  if(document.getElementById('lastapp')){
			document.getElementById('lastapp').innerHTML = data[0];
			
		}if(document.getElementById('lastappdate')){
			document.getElementById('lastappdate').innerHTML = data[1];
			
		}
	if(document.getElementById('lastappcharges')){
		document.getElementById('lastappcharges').innerHTML = data[2];
		
	}
	if(document.getElementById('lastapmtdays')){
		document.getElementById('lastapmtdays').innerHTML = data[3];
		
	}
}
}

}
function formatAMPM123(hh,mm) {
    var h = hh;
    return (h % 12 || 12)
        + ':' + mm
        + ' ' + (h < 12 ? 'A' : 'P') + 'M';
}




function updatetptypetempcharge(dbid,id,val,clientid){
	
	var url = "invnewchargeCompleteApmt?dbid="+dbid+"&charge="+val+"&clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatetptypetempchargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
   
}


function updatetptypetempchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var clientId= req.responseText;
			setCashDesk(clientId);
		}
	}
   
}

function setCashDesk(clientId){

	//alert(selectedUser);
	//alert(cookiecommencing);
	//alert(cookieSelectedAppointmentid);
//var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+editAppointId+"" ;
//  2018-May-04
var url = "cashDeskBookAppointmentAjax?selectedUser="+clientId+"&apmtSlotId=0" ;

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCashDeskRequest;
    req.open("GET", url, true); 
              
    req.send(null);


} 

function setCashDeskRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			  /* document.getElementById("invamtchargeid").innerHTML = req.responseText;
					document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
			
//					$( "#preloader" ).modal( "show" );
					if(document.getElementById("yuviloader")){
					document.getElementById("yuviloader").style.display = "none";
					}
			*/
			$('#addchargepopupconsume').modal( "show" );
			document.getElementById("cashDesk31").innerHTML = req.responseText;
		    document.getElementById('chargeTotal3').value = document.getElementById('hiddenTotal').value;
		}
	}
}

