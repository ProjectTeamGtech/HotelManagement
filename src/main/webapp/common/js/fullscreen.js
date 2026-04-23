var currencySign = 'Rs.';
var hisonlineusers = 0;
var openedb = 'otdb';
var isnewopd = 0;
var warname="YUVICAREAPK";
var apmuserlist = "";
var yearrange = "1880:2050";

var openpopupwidth = 1920;
var openpopupheight = 1024;
alertmsgduration = 1500;

var telemedmsgduration =10000;

/***********************************************
* Auto maximize the window into new window screen
***********************************************/
top.window.moveTo(0,0);
if (document.all) {
top.window.resizeTo(screen.availWidth,screen.availHeight);
}
else if (document.layers||document.getElementById) {
if (top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
top.window.outerHeight = screen.availHeight;
top.window.outerWidth = screen.availWidth;
}
}


function checkAllWeek(){
	
	if(document.getElementById('wholeweek').checked==true){
		for(i=1;i<=7;i++){
			document.getElementById('weekName-'+i).checked = true;
		}
	}else{
		for(i=1;i<=7;i++){
			document.getElementById('weekName-'+i).checked = false;
		}
	}
}	


function checkBlockAllWeek(){
	
	if(document.getElementById('blockwholeweek').checked==true){
		for(i=1;i<=7;i++){
			document.getElementById('blockWeekName-'+i).checked = true;
		}
	}else{
		for(i=1;i<=7;i++){
			document.getElementById('blockWeekName-'+i).checked = false;
		}
	}
}


/*$(document).ready(function(){
	$(".chosen").chosen({
		allow_single_deselect: true		
		});
	});*/
// alert("\u00A3");
/*(function() {
	var fullScreenApi = {
		supportsFullScreen : false,
		nonNativeSupportsFullScreen : false,
		isFullScreen : function() {
			return false;
		},
		requestFullScreen : function() {
		},
		cancelFullScreen : function() {
		},
		fullScreenEventName : '',
		prefix : ''
	}, browserPrefixes = 'webkit moz o ms khtml'.split(' ');

	// check for native support
	if (typeof document.cancelFullScreen != 'undefined') {
		fullScreenApi.supportsFullScreen = true;
	} else {
		// check for fullscreen support by vendor prefix
		for (var i = 0, il = browserPrefixes.length; i < il; i++) {
			fullScreenApi.prefix = browserPrefixes[i];

			if (typeof document[fullScreenApi.prefix + 'CancelFullScreen'] != 'undefined') {
				fullScreenApi.supportsFullScreen = true;

				break;
			}
		}
	}

	// update methods to do something useful
	if (fullScreenApi.supportsFullScreen) {
		fullScreenApi.fullScreenEventName = fullScreenApi.prefix
				+ 'fullscreenchange';

		fullScreenApi.isFullScreen = function() {
			switch (this.prefix) {
			case '':
				return document.fullScreen;
			case 'webkit':
				return document.webkitIsFullScreen;
			default:
				return document[this.prefix + 'FullScreen'];
			}
		}
		fullScreenApi.requestFullScreen = function(el) {
			return (this.prefix === '') ? el.requestFullScreen()
					: el[this.prefix + 'RequestFullScreen']();
		}
		fullScreenApi.cancelFullScreen = function(el) {
			return (this.prefix === '') ? document.cancelFullScreen()
					: document[this.prefix + 'CancelFullScreen']();
		}
	} else if (typeof window.ActiveXObject !== "undefined") { // IE.
		fullScreenApi.nonNativeSupportsFullScreen = true;
		fullScreenApi.requestFullScreen = fullScreenApi.requestFullScreen = function(
				el) {
			var wscript = new ActiveXObject("WScript.Shell");
			if (wscript !== null) {
				wscript.SendKeys("{F11}");
			}
		}
		fullScreenApi.isFullScreen = function() {
			return document.body.clientHeight == screen.height
					&& document.body.clientWidth == screen.width;
		}
	}

	// jQuery plugin
	if (typeof jQuery != 'undefined') {
		jQuery.fn.requestFullScreen = function() {

			return this.each(function() {
				if (fullScreenApi.supportsFullScreen) {
					fullScreenApi.requestFullScreen(this);
				}
			});
		};
	}

	// export api
	window.fullScreenApi = fullScreenApi;
})();*/

function getCurrentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '/' + mm + '/' + dd;
	// document.write(today);

	return today;
}

function dateFormat(date) {
	var tempDate = date.split("/");

	var dd = tempDate[0];
	var mm = tempDate[1]; // January is 0!
	var yyyy = tempDate[2];

	var result = yyyy + '/' + mm + '/' + dd;

	return result;
}

function getCurrentTime() {
	var d = new Date();
	var hh = d.getHours(); // => 9
	var mm = d.getMinutes(); // => 30
	var ss = 0; // => 51

	if (hh < 10) {
		hh = '0' + hh
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	if (ss < 10) {
		ss = '0' + ss
	}

	var result = hh + ':' + mm + ':' + ss;

	return result;

}

function getMonthName(cnt) {

	var result = "";
	var weekname = new Array(6);
	weekname[0] = "Monday";
	weekname[1] = "Tuesday";
	weekname[2] = "Wednesday";
	weekname[3] = "Thursday";
	weekname[4] = "Friday";
	weekname[5] = "Saturday";
	weekname[6] = "Sunday";

	result = weekname[cnt]

	return result;
}

function read_cookie(key) {
	var result;
	return (result = new RegExp('(^|; )' + encodeURIComponent(key) + '=([^;]*)')
			.exec(document.cookie)) ? result[2] : null;
}

function getTimeTotal(time1, time2) {
	var time1_hr = "";
	var time1_min = "";
	var time2_hr = "";
	var time2_min = "";
	var total_hrtime = "";
	var total_mintime = "";
	var generated_Hour = 0;
	time1_hr = time1.split(":")[0];
	time1_min = time1.split(":")[1];
	time2_hr = time2.split(":")[0];
	time2_min = time2.split(":")[1];

	total_hrtime = 1 * time1_hr + 1 * time2_hr;

	// alert(total_hrtime);
	total_mintime = 1 * time1_min + 1 * time2_min;
	// alert(total_mintime);

	if (total_mintime >= 60) {
		total_mintime = total_mintime - 60;
		total_hrtime = total_hrtime + 1;
	}

	if (total_hrtime >= 24) {
		total_hrtime = total_hrtime - 24;
		if (total_hrtime < 10)
			total_hrtime = "0" + total_hrtime;

	}

	if (total_hrtime <= 9) {
		total_hrtime = "0" + total_hrtime;
	}
	if (total_mintime <= 5) {
		total_mintime = "0" + total_mintime;
	}

	return total_hrtime + ":" + total_mintime;

	// alert(total_hrtime+":"+total_mintime);
}

function getTimeSubstration(end, start) {
	s = start.split(':');
	e = end.split(':');

	min = e[1] - s[1];
	hour_carry = 0;
	if (min < 0) {
		min += 60;
		hour_carry += 1;
	}
	hour = e[0] - s[0] - hour_carry;

	if (hour <= 9) {
		hour = '0' + hour;
	}
	if (min <= 9) {
		min = '0' + min;
	}

	diff = hour + ":" + min;

	return diff;
}

function tempAlert(msg, duration) {
	var el = document.createElement("div");
	el.setAttribute("style", "position:absolute;top:7%;left:38%;");
	el.id = 'effectdivid';
	el.className = 'alert-box success';
	el.innerHTML = msg;
	setTimeout(function() {
		el.parentNode.removeChild(el);
	}, duration);
	document.body.appendChild(el);
}


function tempAlert1(msg, duration) {
	var el = document.createElement("div");
	el.setAttribute("style", "position:absolute;top:7%;left:38%;");
	el.id = 'effectdivid1';
	el.className = 'alert-box success';
	el.innerHTML = msg;
	setTimeout(function() {
		el.parentNode.removeChild(el);
	}, duration);
	document.body.appendChild(el);
}

// Initialize bootstrap tooltip plugin
$(document).ready(function() {
	$('.showToolTip').tooltip();
});

// Function to show growl
function showGrowl(title, msg, type, iconclass) {
	$.growl({
		title : title,
		message : msg,
		type : type,
		icon : iconclass
	});
}


/*//Jquery calender for mozila
var enforceModalFocusFn = $.fn.modal.Constructor.prototype.enforceFocus;

$.fn.modal.Constructor.prototype.enforceFocus = function() {};

$confModal.on('hidden', function() {
    $.fn.modal.Constructor.prototype.enforceFocus = enforceModalFocusFn;
});

*/



//appointment method

function getWraperDivChildren(el){
	
	var size = 0;
	
	if(actionType == 0){
		size = 7;
	}else if(actionType == 1){
		size = parseInt(document.getElementById('alluserpagesize').value);
		
	}else{
	
		size = 1;
	}
	
	 var container = document.getElementById(el);
	 var childArray = container.children;
	 var tempid = 0;
	 
	 var firsttdid = childArray[0].id;
	 var splitid = firsttdid.split("min");
	 tempid = splitid[1];
	 
	 var title = childArray[0].title;
	 title = title.split(':');
	 
	 //setHtmlString(tempid,title[0]);
	 
	 var length =  childArray.length -1;
	 var lasttdid = childArray[length].id;
	 var latsplitid = lasttdid.split("min");
	 var lasttempid = latsplitid[1];
	 
	 var loopLenght = lasttempid - tempid;
	 
	 if(loopLenght == 0){
		 loopLenght = 1;
	 }
	 
	 for(p=0;p<loopLenght;p++){
		 
		 
		 var  newtitle = parseInt(title) + p;
		 
		 setHtmlString(tempid, newtitle);
		 
		 tempid = parseInt(tempid) + size;
		 
		 if(tempid>lasttempid){
			 break;
		 }
	 }
	 
	
	/* var length =  childArray.length -1;
	 var lasttdid = childArray[length].id;
	 splitid = lasttdid.split("min");
	 tempid = splitid[1];
	 
	 title = childArray[length].title;
	 title = title.split(':');
	 
	 setHtmlString(tempid,title[0]);*/
	 
	/* for(var i = 0; i < childArray.length; i++){
		 tempid = childArray[i].id;
		 var splitid = tempid.split("min");
		 tempid = splitid[1];
		 alert(childArray[8].id);
	}*/
}

function changeWrapedDivId(el){
	 	var container = document.getElementById(el);
	    var childArray = container.children;
	    //var results = document.getElementById('results');
	    //results.innerHTML = container.id+" has "+childArray.length+" children.<br />";
	    
	    
	    for(var i = 0; i < childArray.length; i++){
	    	var id = childArray[i].id;
	    	document.getElementById(id).setAttribute("id", id+'-'+el);
	    }
}

function setHtmlString(id,title){
	if(id>0){
		
		if(title<=9){
			title = '0'+title;
		}
		
		document.getElementById(id).innerHTML = "";
		
		var str = '';
		for(i=0;i<=11;i++){
			var min = 5*i;
			if(min<=9){
				min = '0'+min;
			}
			
			str = str+'<div id="'+(5*i)+'min'+id+'" class="cssClass1" title="'+title+':'+min+'" style="background-color: rgb(245, 245, 245);"></div>';
		}
		
		document.getElementById(id).innerHTML = str;
		
	}
	
	
}
function initialFirstCap(field) {
	   field.value = field.value.substr(0, 1).toUpperCase() + field.value.substr(1);
	}
function initialCap(field) {
	   field.value = field.value.toUpperCase();
	}
function allFirstInitCap(id) {
    /* First letter as uppercase, rest lower */
	var txt  = document.getElementById(id);
    txt.value = txt.value.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});      
}

function addZero(id){
	if(document.getElementById(id).value.length == 10){
	document.getElementById(id).value = "0"+document.getElementById(id).value;
	}
}


// print method

function printpage(){
	
	window.print();
	
	
}


function setSelectedClientSessionRecordAjax(clientid){
	
	var url = "selectedClient?selectedid="+clientid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSelectedClientSessionRecordAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setSelectedClientSessionRecordAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			/*if(document.getElementById('clientrefundinvoiceid')){
				var clientrefundinvoiceid = document.getElementById('clientrefundinvoiceid').value;
				checkinvoiceidandsetdate(clientrefundinvoiceid);
			}*/
			if(document.getElementById("isfromrefundprocess")){
				if(document.getElementById("isfromrefundprocess").value==1){
					 var refundinvoiceid = document.getElementById('clientrefundinvoiceid').value;
						if(refundinvoiceid=='0'){
							
						}else{
							document.getElementById('newinvoiceid').value = refundinvoiceid;
							checkinvoiceidandsetdate(refundinvoiceid);
						} 
				}
				
			}
			
		}
	}
}

function commonConfirmedDelete(){

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



function removeAlertCss(){
	
	$("#popup_overlay").css({
		position: 'relative !important;',
		zIndex: 0,
		top: '0px',
		left: '0px',
		width: '',
		height: '',
		background: '',
		opacity:''
	});
}

$('#modifyPopup').on('hide.bs.modal', function(e) {

   alert('hellodiv')

});

function openSamePopup(URL) { 
	var oldwindow = window.open(URL, "_self", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
} 

function openIpdPopup(URL) { 
	var oldwindow = window.open(URL, "ipd", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	} 

function openPacsPopup(URL) { 
	var oldwindow = window.open(URL, "pacs", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	}

function openotimgPopup(URL) { 
	var oldwindow = window.open(URL, "otimg", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	}


function opencPopup(URL) { 
	var oldwindow = window.open(URL, "Client panel", "status = 1,height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0" ); 
	oldwindow.focus();
	} 



function openPopup(URL) { 
	var oldwindow = window.open(URL, "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	} 
function openBlankPopup(URL) { 
	var oldwindow = window.open(URL, "_blank", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	} 

function openEmrPopup(URL) { 
	var oldwindow = window.open(URL, "Client Emr", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	}

function openSamll(URL){
	 myWindow = window.open(URL, "_blank", "width=800, height=600, addressbar=no,");
}

function openDisplayPopup(URL) { 
	var oldwindow = window.open(URL, "Apmt Display", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
	}


function openClientPrintPopup(cid){
	var oldwindow = window.open("displayProfileClient?selectedid="+cid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	oldwindow.focus();
	
}

function openEditClientPrintPopup(cid){
	
	//var oldwindow = window.open("editClient?selectedid="+cid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	var oldwindow = window.open("editpatientnewClient?selectedid="+cid+"", "_blank", "width=800, height=600, addressbar=no,");
	oldwindow.focus();
}
function openEditClientPrintPopupold(cid){
	
	//var oldwindow = window.open("editClient?selectedid="+cid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	var oldwindow = window.open("editClient?selectedid="+cid+"", "_blank", "height = "+openpopupheight+", width = "+openpopupwidth+", addressbar=no,");
	oldwindow.focus();
}
function openClientLogPopup(cid){
	var oldwindow = window.open("ClientLog?id="+cid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	oldwindow.focus();
}

function openAssesmentFormPopup(cid,pid,aid){
	
	/*var oldwindow = window.open("redirectListAssessmentForm?clientId="+cid+"&practitionerId="+pid+"&appointId="+aid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );*/
	var oldwindow = window.open("showtemplateAssesmentForms?clientId="+cid+"&practitionerId="+pid+"&appointId="+aid+"", "Client Container", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" );
	oldwindow.focus();
	
}

function goreferesh(){
	window.location.reload();
}

function validateDecimalNumber(v){
	
	  var RE = /^\d*\.?\d*$/;
	    if(RE.test(v)){
	    
	      return true;
	    }
	    else
	    {
	    	 return false;
	    }
}


var oldide = 0;
function setEmrSelectedRows(id,clientid){
	
	$(document.getElementById(id)).css('background-color', 'rgb(229, 217, 129)');
	
	
	if(oldide!=0){
		$(document.getElementById(oldide)).css('background-color', 'white');
	}

	
	oldide = id;
	
	setSelectedClientSessionRecordAjax(clientid)
}

function isNumberKey(evt, element) {
  var charCode = (evt.which) ? evt.which : event.keyCode
  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charCode == 8))
    return false;
  else {
    var len = $(element).val().length;
    var index = $(element).val().indexOf('.');
    if (index > 0 && charCode == 46) {
      return false;
    }
    if (index > 0) {
      var CharAfterdot = (len + 1) - index;
      if (CharAfterdot > 3) {
        return false;
      }
    }

  }
  return true;
}

function validateEmail(emailField){
	 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(emailField);

}

function bootalert(pop,msg,titl){
	
	
	
	
	var error="";
	if(pop=='error'){
		error="red";
	}else{
		error="black";
	}
	 var box=bootbox.alert({
		 	title: titl,
		 	message: "<span style='color:"+error+"';>"+msg+"</span>",
		    size:"small"
		});
	 return box;
}

function shownewpopup(){
		$('#dashboardloaderPopup').modal('show');
	}
	
function shownewidpopup(){
		$('#newloaderPopup').modal('show');
	}	