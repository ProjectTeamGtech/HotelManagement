function savechildgrowthdata(val){
	var heightdata = document.getElementById("heightdata").value;
	var heightmonth = document.getElementById("heightmonth").value;
	var weightdata = document.getElementById("weightdata").value;
	var weightmonth = document.getElementById("weightmonth").value;
	var bmidata = document.getElementById("bmidata").value;
	var bmimonth = document.getElementById("bmimonth").value;
	var headcircumferncedata = document.getElementById("headcircumferncedata").value;
	var headcircumferncemonth = document.getElementById("headcircumferncemonth").value;
	var clientId = document.getElementById("clientId").value;

	var month ='';
	var flag=0;
	if(val=='height'){
		month =  document.getElementById("heightmonth").value;
		if(heightdata==''){
			 jAlert('error', 'Please enter height!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(heightdata >= 126){
			 jAlert('error', 'Please enter height less than or equal to 125 cm!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(heightdata <= 0 || heightmonth < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(heightmonth==''){
			 jAlert('error', 'Please enter height month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(heightmonth >= 61){
			 jAlert('error', 'Please enter height month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='weight'){
		month =  document.getElementById("weightmonth").value;
		if(weightdata==''){
			 jAlert('error', 'Please enter weight!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(weightdata >= 31){
			 jAlert('error', 'Please enter height less than or equal to 30 kg!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(weightdata <= 0 || weightmonth < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(weightmonth==''){
			 jAlert('error', 'Please enter weight month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(weightmonth >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='bmi'){
		month =  document.getElementById("bmimonth").value;
		if(bmidata==''){
			 jAlert('error', 'Please enter bmi!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(bmidata >= 22.5){
			 jAlert('error', 'Please enter BMI less than or equal to 22.5 cm!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(bmidata <= 0 || bmimonth < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(bmimonth==''){
			 jAlert('error', 'Please enter bmi month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(bmimonth >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='headcircumfernce'){
		month =  document.getElementById("headcircumferncemonth").value;
		if(headcircumferncedata==''){
			 jAlert('error', 'Please enter head circumfernce!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(headcircumferncedata >= 56){
			 jAlert('error', 'Please enter head circumfernce less than or equal to 55 cm!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(headcircumferncedata <= 0 || headcircumferncemonth < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(headcircumferncemonth==''){
			 jAlert('error', 'Please enter head circumfernce month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(headcircumferncemonth >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}
	
	if(flag==1){
		var url="savechildgrowthdataBookAppointmentAjax?clientId="+clientId+"&heightdata="+heightdata+"&heightmonth="+heightmonth+"&weightdata="+weightdata+"&weightmonth="+weightmonth+"&bmidata="+bmidata+"&bmimonth="+bmimonth+"&headcircumferncedata="+headcircumferncedata+"&headcircumferncemonth="+headcircumferncemonth+"&val="+val+"&month="+month+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange =savechildgrowthdataRequest;
	    req.open("GET", url, true); 
	    req.send(null);    	
	}
	
}

function savechildgrowthdataRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			 window.location.reload();
         }
	}	 
}
function savevacinationjson(masterid,mastername,givendate,clientid,duedate){
	  var dataObj={
		   	    "masterid":""+masterid+"",
		   	    "mastername":""+mastername+"",
		   	    "givendate":""+givendate+"",
		   	    "duedate" : "" + duedate + "",
		   	    "clientid" : "" + clientid + "",
		   	  };
		   	  var data1 =  JSON.stringify(dataObj);
		  
		   	  $.ajax({

		   	   url : "savevacinationimmunizationajaxNotAvailableSlot",
		   	   data : data1,
		   	   dataType : 'json',
		   	   contentType : 'application/json',
		   	   type : 'POST',
		   	   async : true,
		   	   // beforeSend: function () { LockScreen(); },
		   	   success : function(data) {

		   		   document.getElementById(""+masterid+"").value=givendate;
		   		   document.getElementById("spanbcgdt"+masterid).innerHTML=givendate;
		   		   
		   		   console.log(data);
		   		   jAlert('success', "Data saved successfully!", 'Success Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		   		   window.location.reload();
		   	     },
		   	   error : function(result) {
		   	    console.log("error");
		   	   }
		   	  });
		   	   

}


function  changeDuedate(id, cliientid, value){
	 var dataObj={
		   	    "masterid":""+id+"",
		   	  
		   	  
		   	    "duedate" : "" + value + "",
		   	    "clientid" : "" + cliientid + "",
		   	  };
		   	  var data1 =  JSON.stringify(dataObj);
		   	document.getElementById("spanbcg"+id).innerHTML=value;
		   	  $.ajax({

		   	   url : "changeduedateNotAvailableSlot",
		   	   data : data1,
		   	   dataType : 'json',
		   	   contentType : 'application/json',
		   	   type : 'POST',
		   	   async : true,
		   	   // beforeSend: function () { LockScreen(); },
		   	   success : function(data) {

		   		jAlert('success', "Data saved successfully!", 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
	   		   window.location.reload();
		   		
		   		
		   		   console.log(data);
		   	     },
		   	   error : function(result) {
		   	    console.log("error");
		   	   }
		   	  });
}


function updateremarkvacinationjson(masterid,clientid,remark){
	  var dataObj={
		   	    "masterid":""+masterid+"",
		   	  
		   	 "remark":""+remark+"",
		   	    "clientid" : "" + clientid + "",
		   	  };
		   	  var data1 =  JSON.stringify(dataObj);
		   	  $.ajax({

		   	   url : "updateremarkvacinationimmunizationajaxNotAvailableSlot",
		   	   data : data1,
		   	   dataType : 'json',
		   	   contentType : 'application/json',
		   	   type : 'POST',
		   	   async : true,
		   	   // beforeSend: function () { LockScreen(); },
		   	   success : function(data) {

		   		document.getElementById('spanremark1'+masterid).innerHTML=remark;
		   		jAlert('success', "Data saved successfully!", 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
	   		   /*window.location.reload();*/
		   		   console.log(data);
		   	     },
		   	   error : function(result) {
		   	    console.log("error");
		   	   }
		   	  });
}


function changemonthofcgd(month,val){
	var clientId = document.getElementById("clientId").value;

	var flag=0;
	if(val=='height'){
		if(month < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month==''){
			 jAlert('error', 'Please enter height month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month >= 61){
			 jAlert('error', 'Please enter height month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='weight'){
		if(month < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month==''){
			 jAlert('error', 'Please enter weight month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='bmi'){
		if(month < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month==''){
			 jAlert('error', 'Please enter bmi month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}else if(val=='headcircumfernce'){
		if(month < 0 ){
			 jAlert('error', 'Please enter postive number!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month==''){
			 jAlert('error', 'Please enter head circumfernce month!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(month >= 61){
			 jAlert('error', 'Please enter month less than or equal to 60 months!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			flag=1;
		}
	}
	
	if(flag==1){
		var url="getchildgrowthdataofmonthBookAppointmentAjax?clientId="+clientId+"&month="+month+"&val="+val+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange =changemonthofcgdRequest;
	    req.open("GET", url, true); 
	    req.send(null);    	
	}
	
}

function changemonthofcgdRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
		    var str=data.split("~~~");
			var type= str[1];
		 	if(type=='height'){
		 		document.getElementById("heightdata").value=str[0];
			}else if(type=='weight'){
				document.getElementById("weightdata").value=str[0];
			}else if(type=='bmi'){
				document.getElementById("bmidata").value=str[0];
			}else if(type=='headcircumfernce'){
				document.getElementById("headcircumferncedata").value=str[0];
			}
		     
         }
	}	 
}



function scheduleApmt(val){
	var type="0";
	if(val=='0'){
		type="1";
	}else if(val=='1'){
		type="2";
	}
	var clid=document.getElementById('clientid').value;
	 $('#baselayout1loaderPopup').modal( "show" );
	var url="makeapmtvaccineNotAvailableSlot?clientId="+clid+"&type="+type+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange =scheduleApmtRequest;
    req.open("GET", url, true); 
    req.send(null);    	
}

function scheduleApmtRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 window.location.reload();
			}
		}
}

function showAllContact() {
	$('#baselayout1loaderPopup').modal( "show" );
	var flag = false;
	var val ="";
	$('.akashdclass').each(function() { 
		if(this.checked == true){
			flag = true;
			val = this.value;
		} 
	});
	var smstype = document.getElementById("smstype").checked;
	var emailtype = document.getElementById("emailtype").checked;
	var sendtype ="";
	if(smstype==true){
		sendtype = 'sms';
	}else{
		sendtype = 'email';
	}
	if(smstype==false && emailtype==false){
		$('#baselayout1loaderPopup').modal( "hide" );
		jAlert('error', "Please select at send type!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(!flag){
		$('#baselayout1loaderPopup').modal( "hide" );
		jAlert('error', "Please check radio button!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		if(val=='1'){
			var dobdate = document.getElementById("dobdate").value;
			if(dobdate==''){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please enter dob!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='5'){
			var thirdpartynameid = document.getElementById("thirdpartynameid").value;
			if(thirdpartynameid=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select third party!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='11'){
			var refferedby = document.getElementById("refferedby").value;
			if(refferedby=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select reference doctor!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='10'){
			var mobileoremail = document.getElementById("mobileoremail").value;
			if(mobileoremail=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select mobile or email or both!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='14'){
			var jobtitleid = document.getElementById("jobtitleid").value;
			var specialityid = document.getElementById("specialityid").value;
			if(jobtitleid=='0' && specialityid=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select job title or Department!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='4'){
			var invtname = document.getElementById("invtname").value;
			if(invtname=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select invsetigation name!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				getAllContact(val);
			}
		}else if(val=='3'){
			var mdicinename= document.getElementById("mdicinename").value;
			var medafterdate = document.getElementById("medafterdate").value;
			if(mdicinename=='0'){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select product!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else if(medafterdate==''){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "Please select product date!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				getAllContact(val);
			}
		}else if(val=='15'){
			//list of employee
			getAllContact(val);
		}else if(val=='16'){
			//list of patient
			getAllContact(val);
		}
	}
	//$('#baselayout1loaderPopup').modal( "hide" );
}


function getAllContact(val){
	var smstype = document.getElementById("smstype").checked;
	var emailtype = document.getElementById("emailtype").checked;
	var sendtype ="";
	if(smstype==true){
		sendtype = 'sms';
	}else{
		sendtype = 'email';
	}
	var url ="";
	if(val=='1'){
		var dobdate = document.getElementById("dobdate").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&dobdate="+dobdate+"&sendtype="+sendtype+"";
	}else if(val=='5'){
		var thirdpartynameid = document.getElementById("thirdpartynameid").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&thirdpartynameid="+thirdpartynameid+"&sendtype="+sendtype+"";
	}else if(val=='11'){
		var refferedby = document.getElementById("refferedby").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&refferedby="+refferedby+"&sendtype="+sendtype+"";
	}else if(val=='10'){
		var mobileoremail = document.getElementById("mobileoremail").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&mobileoremail="+mobileoremail+"&sendtype="+sendtype+"";
	}else if(val=='14'){
		var jobtitleid = document.getElementById("jobtitleid").value;
		var specialityid = document.getElementById("specialityid").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&jobtitleid="+jobtitleid+"&specialityid="+specialityid+"&sendtype="+sendtype+"";
	}else if(val=='4'){
		var invtname = document.getElementById("invtname").value;
		var greaterthanid = document.getElementById("greaterthanid").value;
		var lessthanid = document.getElementById("lessthanid").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&invtname="+invtname+"&greaterthanid="+greaterthanid+"&lessthanid="+lessthanid+"&sendtype="+sendtype+"";
	}else if(val=='3'){
		var mdicinename = document.getElementById("mdicinename").value;
		var medafterdate = document.getElementById("medafterdate").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&mdicinename="+mdicinename+"&medafterdate="+medafterdate+"&sendtype="+sendtype+"";
	}else if(val=='15'){
		url ="showallcontactDataDiaryMangent?val="+val+"&sendtype="+sendtype+"";
	}else if(val=='16'){
		var clientType = document.getElementById("clientTypeid").value;
		url ="showallcontactDataDiaryMangent?val="+val+"&sendtype="+sendtype+"&clientType="+clientType+"";
	}
	 if (window.XMLHttpRequest) {
	 	req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
	 	isIE = true;
	 	req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	    req.onreadystatechange = getAllContactRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
 function getAllContactRequest(){
 	if (req.readyState == 4) {
 		if (req.status == 200) {
 			document.getElementById("tbodyallname").innerHTML = req.responseText;
 			$('#baselayout1loaderPopup').modal( "hide" );
 		}
 	}
 }

function changesendtype(val) {
	if(val=='sms'){
		document.getElementById("subjecthiddendiv").className = '';
		document.getElementById("subjecthiddendiv").className = 'form-group hidden';
		document.getElementById("tbodyallname").innerHTML = "";
	}else{
		document.getElementById("subjecthiddendiv").className = '';
		document.getElementById("subjecthiddendiv").className = 'form-group';
		document.getElementById("tbodyallname").innerHTML = "";
	}
}
	  
	  function selectAllMarketingChecked(val) {
		  $('#baselayout1loaderPopup').modal( "show" );
		  if (val.checked == true) {
				$('.dclass').each(function() { // loop through each checkbox
					this.checked = true; // deselect all checkboxes with class
											// "checkbox1"
				});
			} else {
				$('.dclass').each(function() { // loop through each checkbox
					this.checked = false; // deselect all checkboxes with class
											// "checkbox1"
				});
			}
		  $('#baselayout1loaderPopup').modal( "hide" );
	}
	  
function sendMessageToAll() {
		$('#baselayout1loaderPopup').modal( "show" );
		var subject = document.getElementById("subject").value;
		var smstxt = document.getElementById("smstxt").value;
		var smstype = document.getElementById("smstype").checked;
		var emailtype = document.getElementById("emailtype").checked;
		var allmobileno="0";
		var allemailid="0";
		var flag =false;
		var flag1 = false;
		$('.dclass').each(function() { 
			if(this.checked == true){
				flag1 = true;
			}
		});
		var sendtype ="";
		
		/*if(smstype==true){
			sendtype = 'sms';
			$('.dclass').each(function() { 
				if(this.value!=''){
					allmobileno = allmobileno +","+ this.value;
					flag = true;
					
				}
			});
		}else {
			sendtype = 'email';
			$('.eclass').each(function() { 
				if(this.value!=''){
					allemailid = allemailid +","+ this.value;
					flag = true;
				}
			});
		}*/
		if(smstype==true){
			sendtype = 'sms';
			$('.dclass').each(function() { 
				if(this.checked ==true){
					allmobileno = allmobileno +","+ document.getElementById("mobnno"+this.value).value;
					flag = true;
					
				}
			});
		}else {
			sendtype = 'email';
			$('.dclass').each(function() { 
				if(this.checked == true){
					allemailid = allemailid +","+ document.getElementById("emmailid"+this.value).value;
					flag = true;
				}
			});
		}
		if(smstype==false && emailtype==false){
			$('#baselayout1loaderPopup').modal( "hide" );
			jAlert('error', "Please select at send type!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(flag1==false){
			$('#baselayout1loaderPopup').modal( "hide" );
			jAlert('error', "Please select at least client!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(flag==false){
			if(smstype==true){
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "There is no mobile number available!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}else {
				$('#baselayout1loaderPopup').modal( "hide" );
				jAlert('error', "There is no email address available!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}
		}else{
			var url ="sendsmsOrmailDiaryMangent?subject="+subject+"&smstxt="+smstxt+"&allmobileno="+allmobileno+"&sendtype="+sendtype+"&allemailid="+allemailid+"";
		 	  if (window.XMLHttpRequest) {
		 		req = new XMLHttpRequest();
		 	}
		 	else if (window.ActiveXObject) {
		 		isIE = true;
		 		req = new ActiveXObject("Microsoft.XMLHTTP");
		 	}   
		     req.onreadystatechange = sendMessageToAllRequest;
		     req.open("GET", url, true); 
		     req.send(null);
		}
		 
}
function sendMessageToAllRequest(){
 	if (req.readyState == 4) {
 		if (req.status == 200) {	  
 			 $('#baselayout1loaderPopup').modal( "hide" );
 			window.location.reload();
 		}
 	}
 }

function selectSmSTemplate(val){
	$('#baselayout1loaderPopup').modal( "show" );
	 var url ="getSmsTemplateDataDiaryMangent?tempid="+val+"";
	 	  if (window.XMLHttpRequest) {
	 		req = new XMLHttpRequest();
	 	}
	 	else if (window.ActiveXObject) {
	 		isIE = true;
	 		req = new ActiveXObject("Microsoft.XMLHTTP");
	 	}   
	     req.onreadystatechange = selectSmSTemplateRequest;
	     req.open("GET", url, true); 
	     req.send(null);
	     
	  }
	  function selectSmSTemplateRequest(){
	 	if (req.readyState == 4) {
	 		if (req.status == 200) {	  
	 			var data = req.responseText;
	 			var str=data.split("~");
				 document.getElementById("smtemplatename").value= str[0];		     
			     document.getElementById("smstxt").value=str[1];
			     document.getElementById("subject").value=str[2];
			     $('#baselayout1loaderPopup').modal( "hide" );
	 		}
	 	}
	 }
	  
	  
	  function sendMsgAndTemplate() {
		  		$('#baselayout1loaderPopup').modal( "show" );
				var subject = document.getElementById("subject").value;
				var smstxt = document.getElementById("smstxt").value;
				var smtemplatename = document.getElementById("smtemplatename").value;
				var smstype = document.getElementById("smstype").checked;
				var emailtype = document.getElementById("emailtype").checked;
				var allmobileno="0";
				var allemailid="0";
				var flag =false;
				var flag1 = false;
				$('.dclass').each(function() { 
					if(this.checked == true){
						flag1 = true;
					}
				});
				var sendtype ="";
				
				
				if(smstype==true){
					sendtype = 'sms';
					$('.dclass').each(function() { 
						if(this.checked == true){
							allmobileno = allmobileno +","+ document.getElementById("mobnno"+this.value).value;
							flag = true;
						}
					});
				}else {
					sendtype = 'email';
					$('.dclass').each(function() { 
						if(this.checked == true){
							allemailid = allemailid +","+ document.getElementById("emmailid"+this.value).value;
							flag = true;
						}
					});
				}
				
				if(smtemplatename==''){
					$('#baselayout1loaderPopup').modal( "hide" );
					jAlert('error', "Please enter template name!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}else if(flag1==false){
					$('#baselayout1loaderPopup').modal( "hide" );
					jAlert('error', "Please select at least client!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}else if(flag==false){
					if(smstype==true){
						$('#baselayout1loaderPopup').modal( "hide" );
						jAlert('error', "There is no mobile number available!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}else {
						$('#baselayout1loaderPopup').modal( "hide" );
						jAlert('error', "There is no email address available!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}
				}else{
					var url ="sendsmsOrmailandsaveDiaryMangent?subject="+subject+"&smstxt="+smstxt+"&allmobileno="+allmobileno+"&smtemplatename="+smtemplatename+"&sendtype="+sendtype+"&allemailid="+allemailid+"";
				 	  if (window.XMLHttpRequest) {
				 		req = new XMLHttpRequest();
				 	}
				 	else if (window.ActiveXObject) {
				 		isIE = true;
				 		req = new ActiveXObject("Microsoft.XMLHTTP");
				 	}   
				     req.onreadystatechange = sendMsgAndTemplateRequest;
				     req.open("GET", url, true); 
				     req.send(null);
					}
				}
				
				
				
				
				
		function sendMsgAndTemplateRequest(){
		 	if (req.readyState == 4) {
		 		if (req.status == 200) {	  
		 			 $('#baselayout1loaderPopup').modal( "hide" );
		 			window.location.reload();
		 		}
		 	}
		 }


		 function savevacinationjsonvet(masterid,mastername,givendate,clientid,duedate){
		 	  var dataObj={
		 		   	    "masterid":""+masterid+"",
		 		   	    "mastername":""+mastername+"",
		 		   	    "givendate":""+givendate+"",
		 		   	    "duedate" : "" + duedate + "",
		 		   	    "clientid" : "" + clientid + "",
		 		   	  };
		 		   	  var data1 =  JSON.stringify(dataObj);
		 		  
		 		   	  $.ajax({

		 		   	   url : "savevacinationimmunizationajaxVetNotAvailableSlot",
		 		   	   data : data1,
		 		   	   dataType : 'json',
		 		   	   contentType : 'application/json',
		 		   	   type : 'POST',
		 		   	   async : true,
		 		   	   // beforeSend: function () { LockScreen(); },
		 		   	   success : function(data) {

		 		   		   document.getElementById(""+masterid+"").value=givendate;
		 		   		   document.getElementById("spanbcgdt"+masterid).innerHTML=givendate;
		 		   		   
		 		   		   console.log(data);
		 		   		   jAlert('success', "Data saved successfully!", 'Success Dialog');
		 					setTimeout(function() {
		 						$("#popup_container").remove();
		 						removeAlertCss();
		 					}, alertmsgduration); 
		 		   		   window.location.reload();
		 		   	     },
		 		   	   error : function(result) {
		 		   	    console.log("error");
		 		   	   }
		 		   	  });
		 		   	   

		 }
function  updatesmtp(){
	
	
	var email=document.getElementById("smtpemail").value;
	var password=document.getElementById("password").value;
	var smtp=document.getElementById("smtp").value;
	
	if (email == '') {

		jAlert('error', "Please enter email!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} 

	else {

		$("#baselayout1loaderPopup").modal("show");
		var url = "updatesmtpDiaryMangent?smtp="+smtp+"&password="+password+"&email="+email+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savenewunitRequest;
		req.open("GET", url, true);
		req.send(null);

	}
	
}	
	
function savenewunitRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				$("#baselayout1loaderPopup").modal("hide");
				
				if (str == '0') {
					// error
					alert("data not added.");
				} else {
					alert("Update Successfully.");
					window.location.reload();

					// success
				}
			}
		}
	}