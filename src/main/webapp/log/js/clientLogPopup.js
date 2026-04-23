var filterflag = "";
var txt = '';

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
	         
	         }
		}
	}
	
	function showAccountCharges(){
		var client = document.getElementById("client").value;
		var clientId = document.getElementById("clientId").value;
		var url = "showAccountChargesAccountLog?clientId="+clientId+"";
		
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
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';
	         
	         }
		}
	}
	
	function showAppointments(){
		
		var clientId = document.getElementById("clientId").value;
		
		
		var url = "showAppointmentClientLog?clientId="+clientId+"&txt="+txt+"";
		
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
				document.getElementById("headingTitle").innerHTML = "All Appointments";
				document.getElementById("allApmts").style.color = "Red";
				document.getElementById("pastApmts").style.color = "";
				document.getElementById("futureApmts").style.color = "";
				document.getElementById("dnaApmts").style.color = "";
				filterflag = "All";
				document.getElementById("dataList").innerHTML = req.responseText;
	         
	         }
		}
	}
	
	
	
	
	function showPastAppointments(){
		var clientId = document.getElementById("clientId").value;
		var url = "showPastAppointmentClientLog?clientId="+clientId+"&txt="+txt+"";
		
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
					document.getElementById("headingTitle").innerHTML = "Past Appointments";
					document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "Red";
					document.getElementById("futureApmts").style.color = "";
					document.getElementById("dnaApmts").style.color = "";
					filterflag = "past";
					document.getElementById("dataList").innerHTML = req.responseText;
		         
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
					document.getElementById("headingTitle").innerHTML = "Future Appointments";
					document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "";
					document.getElementById("futureApmts").style.color = "Red";
					document.getElementById("dnaApmts").style.color = "";
					filterflag = "future";
					document.getElementById("dataList").innerHTML = req.responseText;
		         
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
					document.getElementById("headingTitle").innerHTML = "DNA Appointments";
					document.getElementById("allApmts").style.color = "";
					document.getElementById("pastApmts").style.color = "";
					document.getElementById("futureApmts").style.color = "";
					document.getElementById("dnaApmts").style.color = "Red";
					filterflag = "dna";
					document.getElementById("dataList").innerHTML = req.responseText;
		         
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
				
				document.getElementById('filterdivid').style.display = 'none';
				document.getElementById('filterbtnid').style.display = 'none';







	         
	         }
		}
	}
	
	function showEmailHistory(){
		var searchText = document.getElementById("searchText").value;
		var toDate = document.getElementById("toDate").value;
		var fromDate = document.getElementById("fromDate").value;
		var url = "showEmailHistoryClientLog?searchText="+searchText+"&toDate="+toDate+"&fromDate="+fromDate+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showEmailHistoryRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function showEmailHistoryRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dataList").innerHTML = req.responseText;
	         
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
	        	showAppointments();
	        }    
	        
	        if(filterflag == 'past'){
	        	showPastAppointments();
	        }  
	        
	        if(filterflag == 'future'){
	        	showFutureAppointments();
	        } 
	        if(filterflag == 'dna'){
	        	showDnaAppointments();
	        } 
	}
	
	