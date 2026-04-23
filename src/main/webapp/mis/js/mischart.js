$(document).ready(function() {

        
         var actionval= document.getElementById("action").value;
         if(document.getElementById(actionval)){
        	 document.getElementById(actionval).className="list-group-item text-center active";
         }
         if(document.getElementById(actionval+"t")){
        	 document.getElementById(actionval+"t").className="bhoechie-tab-content active"; 
         }
         if(document.getElementById("ismanasmis")){
        	 if(document.getElementById(actionval)){
            	 document.getElementById(actionval).className="nav-link-2 active";
             }
             if(document.getElementById(actionval+"t")){
            	 document.getElementById(actionval+"t").className="tab-pane fade show active"; 
             }
         }
         

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
		
		
	});
	
	
/*function hideother(action){
  
   if(action=='opd2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll'; 
       	  document.getElementById("ipd2").innerHTML='';
       	  document.getElementById("bedstatus2").innerHTML='';
       	  document.getElementById("invoice2").innerHTML='';
       	  document.getElementById("paymentmode2").innerHTML='';
       	  document.getElementById("expense2").innerHTML='';
       	  document.getElementById("advref2").innerHTML='';
       	  document.getElementById("accountinfo2").innerHTML='';
       	  document.getElementById("dailysummary2").innerHTML='';
       	  document.getElementById("patientview2").innerHTML='';
       	  document.getElementById("clinicalview2").innerHTML='';
       	  document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       	  
   }
   if(action=='ipd2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
	      document.getElementById("opd2").innerHTML='';
          document.getElementById("bedstatus2").innerHTML='';
          document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("opd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       
       
   }
   if(action=='bedstatus2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
          
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       
   }
   if(action=='invoice2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
   }
   if(action=='paymentmode2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       
   }
   if(action=='expense2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
   }
   if(action=='advref2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
   }
   if(action=='accountinfo2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       
   }
   if(action=='dailysummary2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
       
   }
   if(action=='patientview2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("clinicalview2").innerHTML='';
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
       	  document.getElementById("clinicalview").innerHTML='';
   }
   if(action=='clinicalview2'){
        
          document.getElementById(action).className='col-lg-7 col-md-8 col-sm-9 paddinillll';
       document.getElementById("ipd2").innerHTML='';
       document.getElementById("bedstatus2").innerHTML='';
       document.getElementById("invoice2").innerHTML='';
       document.getElementById("paymentmode2").innerHTML='';
       document.getElementById("expense2").innerHTML='';
       document.getElementById("advref2").innerHTML='';
       document.getElementById("accountinfo2").innerHTML='';
       document.getElementById("dailysummary2").innerHTML='';
       document.getElementById("opd2").innerHTML='';
       document.getElementById("patientview2").innerHTML='';
       
       
       document.getElementById("ipd").innerHTML='';
       	  document.getElementById("bedstatus").innerHTML='';
       	  document.getElementById("invoice").innerHTML='';
       	  document.getElementById("paymentmode").innerHTML='';
       	  document.getElementById("expense").innerHTML='';
       	  document.getElementById("advref").innerHTML='';
       	  document.getElementById("accountinfo").innerHTML='';
       	  document.getElementById("dailysummary").innerHTML='';
       	  document.getElementById("patientview").innerHTML='';
       	  document.getElementById("opd").innerHTML='';
   }
   
}	
	*/
	
	
	
function dosubmit(action) {
       
       $('#baselayout1loaderPopup').modal( "show" );
       document.getElementById("action").value=action;
       document.getElementById("mischartfrm").submit();
       
}
var kpiidtemp ='';
function calculateKPI(kpiid,indicatorid) {
	kpiidtemp = kpiid;
	var val1="";
	var val2="";
	var val3="";
	var val4="";
	var val5="";
	
	$('.akash'+kpiid+'').each(function(){
		if(this.value=='1'){
			var val11=this.value+""+kpiid;
			val1 = document.getElementById("input"+val11).value;
		}else if(this.value=='2'){
			var val11=this.value+""+kpiid;
			val2 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='3'){
			var val11=this.value+""+kpiid;
			val3 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='4'){
			var val11=this.value+""+kpiid;
			val4 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='5'){
			var val11=this.value+""+kpiid;
			val5 = document.getElementById("input"+val11).value;
		}
	});
	
	var url="calculatekpiMisChart?kpiid="+kpiid+"&val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&indicatorid="+indicatorid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = calculateKPIRequest;
    req.open("GET", url, true); 
    req.send(null);
}
function calculateKPIRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				 var str= req.responseText;
				 document.getElementById("result"+kpiidtemp).value=str;
	         }
		}	 
	}
	
function saveKPIResult(kpiid,indicatorid,kpi_year,kpi_month,kpi_dataid) {
	var val1="";
	var val2="";
	var val3="";
	var val4="";
	var val5="";
	
	$('.akash'+kpiid+'').each(function(){
		if(this.value=='1'){
			var val11=this.value+""+kpiid;
			val1 = document.getElementById("input"+val11).value;
		}else if(this.value=='2'){
			var val11=this.value+""+kpiid;
			val2 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='3'){
			var val11=this.value+""+kpiid;
			val3 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='4'){
			var val11=this.value+""+kpiid;
			val4 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='5'){
			var val11=this.value+""+kpiid;
			val5 = document.getElementById("input"+val11).value;
		}
	});
	kpi_month = document.getElementById("monthfilter"+kpiid).value;
	var result = document.getElementById("result"+kpiid).value;
	var target = document.getElementById("target"+kpiid).value;
	if(kpi_month=='00'){
		jAlert('error', "Please select month!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		var url="savekpiresultMisChart?kpiid="+kpiid+"&val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&indicatorid="+indicatorid+"&result="+result+"&kpi_year="+kpi_year+"&kpi_month="+kpi_month+"&kpi_dataid="+kpi_dataid+"&target="+target+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = saveKPIResultRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
	
}
function saveKPIResultRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				window.location.reload();
	   }
	}	 
}




function opendownloadExcel(kpiid) {
	document.getElementById("kpiidfordownload").value = kpiid;
	var year_filter = document.getElementById("year_filter").value;
	document.getElementById("kpiyearfordownload").value = year_filter;
	$('#downreport').modal( "show" );
}


function downloadExcelForKPI() {
	var kpiid = document.getElementById("kpiidfordownload").value;
	var kpimonth = document.getElementById("monthdownloadid").value;
	var year_filter = document.getElementById("kpiyearfordownload").value;
	var url="getKPIExcelDataMisChart?kpiid="+kpiid+"&kpi_month="+kpimonth+"&year_filter="+year_filter+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = downloadExcelForKPIRequest;
    req.open("GET", url, true); 
    req.send(null);
}
function downloadExcelForKPIRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				 var data=req.responseText;
			     var str=data.split("~");
			     document.getElementById("kpitheadid").innerHTML=str[0];
				 document.getElementById("kpitbodyid").innerHTML=str[1];
				 printKPIIndiExcel();
	         }
		}	 
	}


function kpimonthchange(val,kpiid) {
	
	kpi_month = val;
	/*var year_filter = document.getElementById("year_filter").value;*/
	var year_filter = document.getElementById("kpiyearfilter"+kpiid).value;
	if(year_filter=='0'){
		document.getElementById("monthfilter"+kpiid).value ='00';
		alert('Please select year first!!!');	
	}else if(kpi_month=='00'){
		var val1="";
		var val2="";
		var val3="";
		var val4="";
		var val5="";
		$('.akash'+kpiid+'').each(function(){
			if(this.value=='1'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = val1;
			}else if(this.value=='2'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = val2;
			}
			else if(this.value=='3'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = val3;
			}
			else if(this.value=='4'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = val4;
			}
			else if(this.value=='5'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value= val5;
			}
		});
	}else{
		$('#baselayout1loaderPopup').modal( "show" );
		var url="getkpisaveddataMisChart?kpiid="+kpiid+"&kpi_year="+year_filter+"&kpi_month="+kpi_month+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = kpimonthchangeRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
	
}
function kpimonthchangeRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				$('#baselayout1loaderPopup').modal( "hide" );
				var data=req.responseText;
			    var str=data.split("~");
				var kpiid = str[0];
			    var val1=str[3];
				var val2=str[4];
				var val3=str[5];
				var val4=str[6];
				var val5=str[7];
				
				$('.akash'+kpiid+'').each(function(){
					if(this.value=='1'){
						var val11=this.value+""+kpiid;
						document.getElementById("input"+val11).value = val1;
					}else if(this.value=='2'){
						var val11=this.value+""+kpiid;
						document.getElementById("input"+val11).value = val2;
					}
					else if(this.value=='3'){
						var val11=this.value+""+kpiid;
						document.getElementById("input"+val11).value = val3;
					}
					else if(this.value=='4'){
						var val11=this.value+""+kpiid;
						document.getElementById("input"+val11).value = val4;
					}
					else if(this.value=='5'){
						var val11=this.value+""+kpiid;
						document.getElementById("input"+val11).value = val5;
					}
				});
				document.getElementById("result"+kpiid).value = str[1];
				document.getElementById("target"+kpiid).value = str[2];
	   }
	}	 
}

function saveSATResult(kpiid,indicatorid,kpi_year,kpi_month,kpi_dataid) {
	var input1 = document.getElementById("input1"+kpiid).value;
	var input2 = document.getElementById("input2"+kpiid).value;
	var evidence= document.getElementById("evidence"+kpiid).value;
	var result= document.getElementById("result"+kpiid).value;
	var target= document.getElementById("target"+kpiid).value;
	if(input1=='2'){
		jAlert('error', "Please select Document!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(input2=='2'){
		jAlert('error', "Please select Implementation!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(evidence==''){
		jAlert('error', "Please enter evidence!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(result==''){
		jAlert('error', "Please enter score!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		var url="savesatresultMisChart?kpiid="+kpiid+"&val1="+input1+"&val2="+input2+"&indicatorid="+indicatorid+"&result="+result+"&kpi_year="+kpi_year+"&kpi_month="+kpi_month+"&kpi_dataid="+kpi_dataid+"&evidence="+evidence+"&target="+target+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = saveSATResultRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
	
}
function saveSATResultRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				window.location.reload();
	   }
	}	 
}