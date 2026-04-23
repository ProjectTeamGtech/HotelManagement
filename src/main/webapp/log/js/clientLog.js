var filterflag = "";
var txt = '';
var orderby = '';
var order = '';
var curaction = '';
function showPopUp(){
	
	var url = "showListClient";

	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllPatientPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showAllPatientPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				
	         }
		}
	}
	

/*	function setPatientName(name,id,type,typeName){
		
		//document.getElementById("client").value = name;
		document.getElementById("clientId").value = id;
		
		setSelectedClientSessionRecordLogAjax(id);
		//getClientInfo(id);
		
	}*/
	
	function setPatientName(id,type,typeName){
		
		//document.getElementById("client").value = name;
		document.getElementById("clientId").value = id;
		
		setSelectedClientSessionRecordLogAjax(id);
		//getClientInfo(id);
		
	}
	
	
	function setSelectedClientSessionRecordLogAjax(clientid){
		
		var url = "selectedClient?selectedid="+clientid+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setSelectedClientSessionRecordLogAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}

	function setSelectedClientSessionRecordLogAjaxRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				var id = document.getElementById("clientId").value;
				getClientInfo(id);
				
				
			}
		}
	}
	
	function getClientInfo(id){
		var diaryuserId = '0';
		var url = "getInfoClient?clientId="+id+"&diaryuser="+diaryuserId+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getClientInfoRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function getClientInfoRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("infoclient").style.display = "";
				var info = req.responseText;
				var temp = info.split("*");
				document.getElementById("infoName").innerHTML = ' '+temp[0];
				document.getElementById("infoAddress").innerHTML = ' '+temp[1];
				document.getElementById("infoSecondAdress").innerHTML = temp[2];
				document.getElementById("infoTown").innerHTML = temp[3];
				document.getElementById("infoPostcode").innerHTML = temp[4];
				document.getElementById("infoAge").innerHTML = temp[5];
				document.getElementById("infotp").innerHTML = temp[6];
				document.getElementById("infomobile").innerHTML = temp[7];
				document.getElementById("infoemail").innerHTML = temp[8];
				$('#clientSearch').modal('hide');
				
				document.getElementById("client").value = temp[0];
				showAppointments('All','appmt_id','desc');
	         }
		}
	}
	
	
	
	
	
	
	
	
	
	function showTreatmentEpisode(){
		var client = document.getElementById("client").value;
		var clientId = document.getElementById("clientId").value;
		
		var url = "showTreatmentEpisodeClientLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showTreatmentEpisodeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}


	function showTreatmentEpisodeRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				document.getElementById("headingTitle").innerHTML = "<b>Report</b>";
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				
				var clientid = document.getElementById("clientId").value;
				
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	
	function showBedChangeLog(){
		var clientId = document.getElementById("clientId").value;
		
		var url = "bedchangeIpdLog?clientId="+clientId+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showBedChangeLogRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);

		
	}
	
	function showBedChangeLogRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>Bed Change</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	
	function showIpdAdmissionLog(){
		
		var clientId = document.getElementById("clientId").value;
		
		var url = "admissionIpdLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showIpdAdmissionLogRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}
	
	function showIpdAdmissionLogRequest(){
		
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>IPD Admissions</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	
	function showIpdDischargeLog(){
		var clientId = document.getElementById("clientId").value;
		
		var url = "dischargeIpdLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showIpdDischargeLogRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
	
	function showIpdDischargeLogRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>IPD Discharge</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	function showAccountCharges(){
		var client = document.getElementById("client").value;
		var clientId = document.getElementById("clientId").value;
		var url = " showAccountChargesAccountLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAccountChargesRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showAccountChargesRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>Charges</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	function showAccountInvoice(){
		var clientId = document.getElementById("clientId").value;
		var url = "showAccountInvoiceAccountLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAccountInvoiceRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showAccountInvoiceRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>Invoice</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				
				var clientid = document.getElementById("clientId").value;
			//	getClientInfo(clientid);
	         
	         }
		}
	}
	
	var alltempaction = "";
	function showAppointments(action,orderby,order){
		alltempaction = action;
		var clientId = document.getElementById("clientId").value;
		var fromdate=document.getElementById("fromDate").value;
		var todate=document.getElementById("toDate").value;
	
		curaction = action;
		
		//$('#loaderPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' });
		$('#loaderPopup').modal( "show" );
		
		var url = "showAppointmentClientLog?clientId="+clientId+"&txt="+txt+"&action="+action+"&orderby="+orderby+"&order="+order+"&fromdate="+fromdate+"&todate="+todate+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAppointmentsRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showAppointmentsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("headingTitle").innerHTML = "<b>"+alltempaction+" Appointments</b>";
				/*document.getElementById("allApmts").style.color = "Red";
				document.getElementById("pastApmts").style.color = "";
				document.getElementById("futureApmts").style.color = "";
				document.getElementById("dnaApmts").style.color = "";*/
				filterflag = alltempaction;
				//
			
				var dateset = req.responseText;
				var temp = dateset.split("~@");
				document.getElementById("dataList").innerHTML = temp[0];
				document.getElementById("fromDate").value =temp[1];
				document.getElementById("toDate").value = temp[2];
				
			
				document.getElementById('filterdivid').style.display = '';
				document.getElementById('filterbtnid').style.display = '';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
				
				//$('#loaderPopup').unblock();
				$('#loaderPopup').modal( "hide" );
	         
	         }
		}
	}
	
	
	var ptempaction = "";
	function showPastAppointments(action,orderby,order){
		ptempaction = action;
		curaction = action;
		var clientId = document.getElementById("clientId").value;
		
		$('#loaderPopup').modal( "show" );
		
		var url = "showPastAppointmentClientLog?clientId="+clientId+"&txt="+txt+"&action="+action+"&orderby="+orderby+"&order="+order+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPastAppointmentsRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}
	function showPastAppointmentsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("headingTitle").innerHTML = "<b>"+ptempaction+" Appointments</b>";
					/*document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "Red";
					document.getElementById("futureApmts").style.color = "";
					document.getElementById("dnaApmts").style.color = "";*/
					//filterflag = "past";
					document.getElementById("dataList").innerHTML = req.responseText;
					
					
					document.getElementById('filterdivid').style.display = '';
					document.getElementById('filterbtnid').style.display = '';


					$('#loaderPopup').modal( "hide" );




					
		         
		         }
			}
		}
	
	function showFutureAppointments(){
		var clientId = document.getElementById("clientId").value;
		var url = "showFutureAppointmentClientLog?clientId="+clientId+"&txt="+txt+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showFutureAppointmentsRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
	function showFutureAppointmentsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("headingTitle").innerHTML = "<b>Future Appointments</b>";
					/*document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "";
					document.getElementById("futureApmts").style.color = "Red";
					document.getElementById("dnaApmts").style.color = "";*/
					filterflag = "future";
					document.getElementById("dataList").innerHTML = req.responseText;
					
					document.getElementById('filterdivid').style.display = '';
					document.getElementById('filterbtnid').style.display = '';







		         
		         }
			}
		}
	function showDnaAppointments(){
		var clientId = document.getElementById("clientId").value;
		var url = "showDnaAppointmentClientLog?clientId="+clientId+"&txt="+txt+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showDnaAppointmentsRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
	function showDnaAppointmentsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("headingTitle").innerHTML = "<b>DNA Appointments</b>";
					/*document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "";
					document.getElementById("futureApmts").style.color = "";
					document.getElementById("dnaApmts").style.color = "Red";*/
					filterflag = "dna";
					document.getElementById("dataList").innerHTML = req.responseText;
					
					document.getElementById('filterdivid').style.display = 'none';
					document.getElementById('filterbtnid').style.display = 'none';







		         
		         }
			}
		}
	
	function showAccountPayments(){
		var clientId = document.getElementById("clientId").value;
		var url = "showAccountPaymentsAccountLog?clientId="+clientId+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAccountPaymentsRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showAccountPaymentsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>Payments</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
			//	getClientInfo(clientid);
	         
	         }
		}
	}
	
	function showEmailLogOnly(){
		var client = document.getElementById("clientId").value;
		var type = 'Email';
		var url = "showEmailHistoryEmailLetterLog?client="+client+"&type="+type+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showEmailLogOnlyRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showEmailLogOnlyRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>E-Mails</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
				//getClientInfo(clientid);
	         
	         }
		}
	}
	
	
	
	function showLetterHistory(){
		var client = document.getElementById("clientId").value;
		var url = "showLetterHistoryEmailLetterLog?client="+client+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showLetterHistoryRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showLetterHistoryRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
				
				document.getElementById("headingTitle").innerHTML = "<b>Letters</b>";
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
				
				var clientid = document.getElementById("clientId").value;
			//	getClientInfo(clientid);
	         
	         }
		}
	}
	
	function filterList(){
		document.getElementById("dataList").innerHTML = " ";
		 txt = '';
		var select = document.getElementById("apmtFilter");
		var clientId = document.getElementById("clientId").value;
	        if (select.selectedIndex > -1) {
	            var idxs = [];
	           
	            for (var i = 0; i < select.options.length; i++) {
	                var option = select.options[i];
	                if (option.selected) {
	                    idxs.push (i);
	                    txt += option.value +"/";
	                }
	            }
	        }
	      //alert(filterflag);
	        if(filterflag == 'All'){
	        	//showAppointments();
	        	showPastAppointments('All','id','desc');
	        }    
	        
	        if(filterflag == 'Past'){
	        	showPastAppointments('Past','id','desc');
	        }  
	        
	        if(filterflag == 'Future'){
	        	//showFutureAppointments();
	        	showPastAppointments('Future','id','desc');
	        } 
	        if(filterflag == 'dna'){
	        	showDnaAppointments();
	        } 
	}
	function searchPatient(){
		var searchText = document.getElementById("searchText1").value;
		var url = "searchParticularPatientClient?searchText="+searchText+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = searchPatientRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
function searchPatientRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					document.getElementById("allPatient").innerHTML = req.responseText;
		         	
					
		         }
			}
		}
	



function showAllStatus(id,size){
	
	
	for(j=0;j<size;j++){
		//document.getElementById(j+'d'+id).style.display = 'none';
		
		if(document.getElementById(j+'d'+id).style.display=='none'){
			document.getElementById(j+'d'+id).style.display = '';
		}else{
			document.getElementById(j+'d'+id).style.display = 'none';
		}
	}
	
}


function showApmtOrder(){
	 orderby = 'commencing';
	
	if(order=='asc'){
		order = 'desc';
		
	}else{
		order = 'asc';
	}
	showAppointments(curaction,orderby,order);
	
}

function showPastApmtOrder(){
	 orderby = 'commencing';
		
		if(order=='asc'){
			order = 'desc';
			
		}else{
			order = 'asc';
		}
		
		showPastAppointments(curaction,orderby,order);
}


//show group data
/*function showAllStatus(id,msize,dsize,csize,cmsize,delsize){
	//modified
	if(document.getElementById('m'+id).style.display=='none'){
		document.getElementById('m'+id).style.display = '';
	}else{
		document.getElementById('m'+id).style.display = 'none';
	}
	
	for(i=0;i<msize;i++){
		document.getElementById(i+'m'+id).style.display = 'none';
	}
	
	//dna
	
	if(document.getElementById('d'+id).style.display=='none'){
		document.getElementById('d'+id).style.display = '';
	}else{
		document.getElementById('d'+id).style.display = 'none';
	}
	
	for(j=0;j<dsize;j++){
		document.getElementById(j+'d'+id).style.display = 'none';
	}
	
	//completed
	
	if(document.getElementById('c'+id).style.display=='none'){
		document.getElementById('c'+id).style.display = '';
	}else{
		document.getElementById('c'+id).style.display = 'none';
	}
	
	for(k=0;k<csize;k++){
		document.getElementById(k+'c'+id).style.display = 'none';
	}
	
	//cm
	
	if(document.getElementById('cm'+id).style.display=='none'){
		document.getElementById('cm'+id).style.display = '';
	}else{
		document.getElementById('cm'+id).style.display = 'none';
	}
	
	for(l=0;l<cmsize;l++){
		document.getElementById(l+'cm'+id).style.display = 'none';
	}
	
	
	//deleted
	
	if(document.getElementById('del'+id).style.display=='none'){
		document.getElementById('del'+id).style.display = '';
	}else{
		document.getElementById('del'+id).style.display = 'none';
	}
	
	for(m=0;m<cmsize;m++){
		document.getElementById(m+'del'+id).style.display = 'none';
	}
	
}


function showAllDeltatus(id,size){
	
	for(i=0;i<size;i++){
		if(document.getElementById(i+'del'+id).style.display == ''){
			document.getElementById(i+'del'+id).style.display = 'none'
		}else{
			document.getElementById(i+'del'+id).style.display = '';
		}
	}
	
}

//completed modified
function showAllCMStatus(id,size){
	
	for(i=0;i<size;i++){
		if(document.getElementById(i+'cm'+id).style.display == ''){
			document.getElementById(i+'cm'+id).style.display = 'none'
		}else{
			document.getElementById(i+'cm'+id).style.display = '';
		}
	}
}

//completed
function showAllCompletedStatus(id,size){
	
	for(i=0;i<size;i++){
		if(document.getElementById(i+'c'+id).style.display == ''){
			document.getElementById(i+'c'+id).style.display = 'none'
		}else{
			document.getElementById(i+'c'+id).style.display = '';
		}
	}
}

//DNA
function showAllDNAStatus(id,size){
	
	for(i=0;i<size;i++){
		if(document.getElementById(i+'d'+id).style.display == ''){
			document.getElementById(i+'d'+id).style.display = 'none'
		}else{
			document.getElementById(i+'d'+id).style.display = '';
		}
	}
}


//modified
function showAllModifyStatus(id,size){
	
	for(i=0;i<size;i++){
			if(document.getElementById(i+'m'+id).style.display == ''){
				document.getElementById(i+'m'+id).style.display = 'none'
			}else{
				document.getElementById(i+'m'+id).style.display = '';
			}
		}
	
}
*/

function showcancelnote(id){
	
	var url = "cancelnoteClientLog?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showcancelnoteRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showcancelnoteRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var note = req.responseText;
         	alert(note);
			
         }
	}
}

function showbedchangedata(id){
	if(document.getElementById('ad'+id).style.display==''){
		document.getElementById('ad'+id).style.display='none';
	}else{
		document.getElementById('ad'+id).style.display='';
	}
}

function showpatientdetailsupdatelog(){
	var clientId = document.getElementById("clientId").value;
	
	var url = "showpatientdetailsupdatelogIpdLog?clientId="+clientId+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showpatientdetailsupdatelogRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function showpatientdetailsupdatelogRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("dataList").innerHTML = req.responseText;
			
			document.getElementById("headingTitle").innerHTML = "<b>Patient Details Update Log</b>";
			
			document.getElementById('filterdivid').style.display = 'none';
			document.getElementById('filterbtnid').style.display = 'none';
			
			var clientid = document.getElementById("clientId").value;
			//getClientInfo(clientid);
         
         }
	}
}

