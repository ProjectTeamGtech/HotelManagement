
var globalid="";
var globalstateid="";
var globalcityid="";
function allnumericFake(id)  
{  
	globalid=id;
	var dd=document.getElementById("year"+globalid).value;
   var numbers = /^[0-9]+$/;  
   if(!dd.match(numbers))  
   {  
  	alert('only number permitted...');  
   }  
   else 
   {
         var today= new Date();
         var yyyy = today.getFullYear();
         var year= parseInt(yyyy)- parseInt(dd);
         var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
         
         if(dd<10) {
 			dd='0'+dd
			} 

			if(mm<10) {
 			mm='0'+mm
			} 

			var str = dd+'/'+mm+'/'+year;
			document.getElementById("dob"+globalid).value=str;
			
			
   		
   }
   
}   


/*function setdob(dd)  
{  
   var numbers = /^[0-9]+$/;  
   if(!dd.match(numbers))  
   {  
  	alert('only number permitted...');  
   }  
   else 
   {
         var today= new Date();
         var yyyy = today.getFullYear();
         var year= parseInt(yyyy)- parseInt(dd);
         var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
         
         if(dd<10) {
 			dd='0'+dd
			} 

			if(mm<10) {
 			mm='0'+mm
			} 

			var str = dd+'/'+mm+'/'+year;
			document.getElementById("dob").value=str;
   		document.getElementById("days").value=0;
   }
   
}   */

function getAgendDaysFake(id) {
	globalid=id;
	var dob=document.getElementById('dob'+id).value;
    var url="getageClient?dob="+dob+"";

    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getAgendDaysFakeRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getAgendDaysFakeRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		     var data= str.split("~");
		     document.getElementById("year"+globalid).value=data[0];
		    /* document.getElementById("month"+globalid).innerHTML=data[1];
		     document.getElementById("days"+globalid).innerHTML=data[2];*/
		     
        }
	}	 
}
function setdept(dept) {
	document.getElementById("alldept").value=dept;
}


function showdoctpopup(val){
	
	if(val!=0){
		 document.getElementById("docid").value=val;
		 document.getElementById("docclientid").value=clientid;
		 document.getElementById("docapt").value=id;
		var department=document.getElementById("deptfrm_dept").value;
		var date=document.getElementById("fromDate").value;

		 var url="checkdiaryexistFinder?nuserid="+val+"&date="+date+"&dept="+department+"";
			  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = showdoctpopupRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   	
		
		 
	}
	}
	function showdoctpopupRequest(){
	if (req.readyState == 4) {
	         if (req.status == 200) {
		
			var data=req.responseText.split("@#");
			if(data[0]==1){
			
							jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
							setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						document.getElementById("doctid").value="0";
	             }else{
						
						 $('#doctorconfirm').modal( "show" );
						document.getElementById("secondarydoc").innerHTML=data[1];
						document.getElementById("primarydocname").innerHTML=data[2];
					}
	         }	
	}
	}
	
	function multipledrselect(val) {
		var selectedwardid = 0;
	     $('.muldrselect').each(function() { 
//	        if(this.checked==true){
//	        	var check=document.getElementById("drselect"+this.value).value;
//	        	if(check==1){
//	        		jAlert('error', 'Doctor Already assign for Selected Patient.', 'Error Dialog');
//					setTimeout(function() {
//					$("#popup_container").remove();
//					removeAlertCss();
//				}, alertmsgduration);
//					document.getElementById("chk"+this.value).checked = false;
//					
//	        	}else{
	    	 if(this.checked==true){
	        		selectedwardid = selectedwardid + ',' + this.value;	
	    	 }
//	        	}
//	        	
//	        }
	         
	     });
	     var array="";
	     if(selectedwardid.length>2){
	    	 array=selectedwardid.split(',');
			}else if(selectedwardid.length>1){
				array=selectedwardid;
			}
		if(array!=''){
		array = array.filter(val => val !== "0");
		}
		var valu=array.toString();
	     document.getElementById('referselected').value = valu;
		
	}
	
	function showdoctpopup(){
		
		var val=document.getElementById("diaryuser").value;
		if(val!=0){
		var department=document.getElementById("userdepartment").value;
		var date=document.getElementById("date").value;

		 var url="checkdiaryexistFinder?nuserid="+val+"&date="+date+"&dept="+department+"";
			  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = showdoctpopupRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   	
		
		 
	}else{
		jAlert('error', 'Please Select Doctor.', 'Error Dialog');
		setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
	}
	}
	function showdoctpopupRequest(){
	if (req.readyState == 4) {
	         if (req.status == 200) {
		
			var data=req.responseText.split("@#");
			if(data[0]==1){
			
				jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
				}, alertmsgduration);
				document.getElementById("doctid").value="0";
	        }else{
						
				document.getElementById("secondarydoc").innerHTML=data[1];
				document.getElementById("primarydocname").innerHTML=data[2];
			}
	     }	
		}
	}
	function showconfirmpopup() {
		var val=document.getElementById("diaryuser").value;
		if(val!=0){
			
		var selected=document.getElementById("referselected").value;
		var idarray='';
		if(selected.length>2){
			idarray=selected.split(',');
		}else if(selected.length>1){
			idarray=selected;
		}
		for (let element of idarray) {
			var check=document.getElementById("drselect"+element).value;
	    	if(check==1){
				document.getElementById("chk"+element).checked = false;
				multipledrselect(element)
	    	}else{
	    		if(selected==''){
	    			jAlert('error', 'Select Patient Or Doctor Already Assign.', 'Error Dialog');
					setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
					}, alertmsgduration);
	    		}else{
	    			$('#doctorconfirm').modal( "show" );
	    		}
			
			
	    	}
		}
		}else{
			jAlert('error', 'Please Select Doctor.', 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		}
		
	}
	
	function opdconfirmfake(){
		$('#newloaderPopup').modal('show');
		document.getElementById("opdyes").disabled = true;
		var val=document.getElementById("diaryuser").value;
		var id=document.getElementById("referselected").value;
		var selectsecondary=document.getElementById("selectedsecondary").value;
			 var url="opdconfirmfakePatientdata?diaryuser="+val+"&aptid="+id+"&secondary="+selectsecondary+"";
			  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = opdconfirmRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   
	}
	function opdconfirmRequest(){
		 if (req.readyState == 4) {
	         if (req.status == 200) {
	        	 $('#doctorconfirm').modal( "hide" );
	        	 jAlert('success', 'Patient OPD Book Successfully.', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					window.location.reload();
	             }
	         }	
	}
	
	function allids(){
		var secondry = 0;
	     $('.allchk').each(function() { //loop through each checkbox
	        // this.checked = true;  //select all checkboxes with class "checkbox1" 
	        if(this.checked==true){
	        	secondry = secondry + ',' + this.value;
	        }
	         
	     });
		var valu='';
		if(secondry!='0'){
		var array = secondry.split(",");
		
		array = array.filter(val => val !== "0");
		valu=array.toString();
		}else{
			valu='';
		}
	     document.getElementById('selectedsecondary').value = valu;
	}
	function refertootherdepartment() {
		$('#newloaderPopup').modal('show');
		document.getElementById("referdeptbtn").disabled = true;
		var selected=document.getElementById("referselected").value;
		var dept = document.getElementById("dept").value;
		var idarray='';
		if(selected!=''){
		if(selected.length>2){
			idarray=selected.split(',');
		}else if(selected.length>1){
			idarray=selected;
		}
		var globalerr=false;
		
		for (let element of idarray) {
			var check=document.getElementById("drselect"+element).value;
	    	if(check==1){
	    		
	    	}else{
	    		//document.getElementById("chk"+element).checked = false;
	    		//multipledrselect(element)
	    		globalerr=true;
	    	}
		}
		}
		if(globalerr){
			jAlert('error', 'Please Assign Doctor.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			$('#newloaderPopup').modal('hide');
			document.getElementById("referdeptbtn").disabled = false;
			
		}else if(dept=='0'){
			jAlert('error', 'Please Select Department.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			$('#newloaderPopup').modal('hide');
			document.getElementById("referdeptbtn").disabled = false;
		}else if(idarray.length==0){
			jAlert('error', 'Please Select Patient.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			$('#newloaderPopup').modal('hide');
			document.getElementById("referdeptbtn").disabled = false;
		}
		var finalselect=document.getElementById("referselected").value;
		if(finalselect.length>1){
			referdepartment();
		}
		
	}

	function referdepartment() {
		var referremark="";
		var dept=document.getElementById("dept").value;
		var selected=document.getElementById("referselected").value;
		$('#newloaderPopup').modal('show');
		if(dept!=0 && selected.length>1){
		var url = "referdeptPatientdata?dept="+dept+"&aptid="+selected+"&sts=0&referremark="+referremark+"";
		
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();	
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = referdepartmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}else if(dept=='' || dept==0){
		jAlert('error', 'Please Select Department.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById("referdeptbtn").disabled = false;
		$('#newloaderPopup').modal('hide');
	}
	}
	function referdepartmentRequest() {

		if (req.readyState == 4) {
			if (req.status == 200) {
					jAlert('success', 'Patient Referred Successfully.', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				window.location.reload();
				
			}
		}
		}
	
function checkdeptselected() {
	var dept=document.getElementById("dept").value
	var isfromKaalmegha = document.getElementById("isfromKaalmegha").value;
	var drId = document.getElementById("drId").value;
	if(isfromKaalmegha==0 && dept==0){
		jAlert('error', 'Please Select Department.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}/*else if(isfromKaalmegha==1 && drId=='0'){
		jAlert('error', 'Please Select Doctor.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}*/else{
		document.getElementById("mainform").submit();
	}
}	


	
function chosemethod()
{
	
	document.getElementById('savenshift').style.visibility = "hidden";
	var ptype=document.getElementById("patienttype").value;
	var flag =true;
	var selectFlag = false;
	$('.fakeclass').each(function() { 
		selectFlag = true;
	var id = this.value;
	var firstname = document.getElementById("firstName"+this.value).value;
	var lastname = document.getElementById("lastName"+this.value).value;
	var mobNoNew = document.getElementById("mobNo"+this.value).value;
	var title = document.getElementById("title"+this.value).value;
	if(title==''){
		flag = false;
		
		jAlert('error', "Please Select Title", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}
	else if( firstname.match(/^[a-z][a-z\s.]*$/) || firstname==""){
		flag = false;
	
		jAlert('error', "Special Symbol & Number Not Allow In First Name.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}
	else if( lastname.match(/^[a-z][a-z\s.]*$/) || lastname=="" ){
		flag = false;
		
		jAlert('error', "Special Symbol & Number Not Allow In Last Name.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}	
	else if(mobNoNew!='' && mobNoNew.length<10) {
		flag = false;
		jAlert('error', "Please Enter Valid Mobile No", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}else if(mobNoNew!='' && !checkNumberOrNotofadjustdata(mobNoNew) ){
		flag = false;
		jAlert('error', "Please Enter Valid Mobile No", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}
	   /* else if(ptype==0){
		$('#newloaderPopup').modal('show');
		document.getElementById("movepatientfrm").submit();
	
	}*/
	
	/*else{
		$('#newloaderPopup').modal('show');
		document.getElementById("movepatientfrm").submit();
		oldreferdepartment();
		document.getElementById('savenshift').style.visibility = "visible";
	}*/
  });
	
	if(ptype==0 && !selectFlag){
		jAlert('error', "Please Add Patient", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		document.getElementById('savenshift').style.visibility = "visible";
	}else{
		if(flag){
			if(ptype==0){
				$('#newloaderPopup').modal('show');
				document.getElementById("movepatientfrm").submit();
			}
			else{
				oldreferdepartment();
				document.getElementById('savenshift').style.visibility = "visible";
			}
			
		}
	}
	
	
}

function checkNumberOrNotofadjustdata(inputtxt)
{
	 var numbers = /^[0-9]+$/;  
     
     if(inputtxt.match(numbers))  {
        return true;
     } else {
          return false;
     }
} 

function oldreferdepartment() {
	$('#newloaderPopup').modal('show');
	var referremark="";
	var dept=document.getElementById("dept").value;
	var selected=document.getElementById("referselected").value;

	var ids=""; 
	$('.checkrandomclass').each(function(){
		if(this.checked == true){
			if(ids==''){
				ids = this.value;
			}else{
				ids=ids+","+this.value;
			}
		} 
	});
	selected = ids;
	var patienttype = document.getElementById("patienttype").value;
	var currentDate ="";
	if(document.getElementById("currentDate")){
		currentDate = document.getElementById("currentDate").value;
	}
	if(dept!=0 && selected.length>1){
	var url = "oldreferdeptPatientdata?dept="+dept+"&aptid="+selected+"&sts=0&referremark="+referremark+"&patienttype="+patienttype+"&currentDate="+currentDate+"";
	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = oldreferdepartmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}else{
	jAlert('error', 'Please Select Department.', 'Error Dialog');
	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
	$('#newloaderPopup').modal('hide');
}
}

function oldreferdepartmentRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#newloaderPopup').modal('show');
				jAlert('success', 'Patient Referred Successfully.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				window.location='Patientdata';
				
		}
	}
	}

function selectallCheckBox(val){
	var selectedwardid = 0;
		if (val== true) {
			$('.muldrselect').each(function() { 
				this.checked = true; 	
				selectedwardid = selectedwardid + ',' + this.value;	
			});
		} else {
			$('.muldrselect').each(function() { 
				this.checked = false; 
			});
		}
		
		 var array="";
	     if(selectedwardid.length>2){
	    	 array=selectedwardid.split(',');
			}else if(selectedwardid.length>1){
				array=selectedwardid;
			}
		if(array!=''){
		array = array.filter(val1 => val1 !== "0");
		}
		var valu=array.toString();
	     document.getElementById('referselected').value = valu;
}


function getCitiesajax(val,id) {
	globalstateid=id;
    var url="getcitiesajaxInventory?state="+val+"";
 
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getCitiesajaxRequest;
    req.open("GET", url, true); 
             
    req.send(null);   

}

function getCitiesajaxRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		   
		   document.getElementById("citydiv"+globalstateid).innerHTML=req.responseText;
		    $("#town").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
        }
	}	 	
}

function getStateAjax(val,id) {
	globalcityid=id;
    var url="getstateajaxInventory?city="+val+"";
 
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getStateAjaxRequest;
    req.open("GET", url, true); 
             
    req.send(null);   

}

function getStateAjaxRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		    document.getElementById("statediv"+globalcityid).innerHTML=req.responseText;
		    $("#state").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
			
			/*if(document.getElementById("country_chosen")){
				document.getElementById("country_chosen").style.marginBottom  ="10px";
			}*/
			
        }
	}	 
}


/*function getFullName(id) {
	
	globalid=id;
	var fullname=document.getElementById('fullname'+id).value;
	
    var url="getfullnameClient?fullname="+fullname+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange =getFullNameRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getFullNameRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		     var data= str.split("~");
		     document.getElementById("fullname"+globalid).innerHTML=req.responseText;
		     
		     
        }
	}	 
}

function setvalue(id){
	var fakeid = document.getElementById('fakeid').value;
	document.getElementById("fullname").innerHTML=req.responseText;
}


function getAddress(id) {
	
	globalid=id;
	var address=document.getElementById('address'+id).value;
	
    var url="getaddressClient?address="+address+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getAddressRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getAddressRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		    
		     document.getElementById("address"+globalid).innerHTML=req.responseText;
		     
		     
        }
	}	 
}


function getTitle(val) {
	
	globalid=id;
	var title=document.getElementById('title'+val).value;
	
    var url="gettitleClient?title="+title+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getTitleRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getTitleRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		     var str= req.responseText;
		    
		     document.getElementById("title"+val).innerHTML=req.responseText;
		     
		     
        }
	}	 
}
*/

function getFakeData(val,id) {
	
	globalid=id;
	var fullname=document.getElementById('fullname'+id).value;
	var address=document.getElementById('address'+id).value;
	var mobNo = document.getElementById('mobNo'+id).value;
	var title = document.getElementById('title'+id).value;
	
    var url="getfakedataClient?fullname="+fullname+"&address="+address+"&mobNo="+mobNo+"&title="+title+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getFakeDataRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getFakeDataRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
		     
		   
        }
	}	 
}

function validateMobNo(id){
	globalid = id ;
	
	var mobNo = document.getElementById("mobNo"+id).value;
	
	if(mobNo.length!=10 ) {
		    
			 jAlert('error', "Please Enter Valid Mobile No.", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}
	

}

function validateFirstName(id){
	globalid= id;
	var firstname = document.getElementById("firstName"+id).value;
	 
	if( firstname.match(/^[a-z][a-z\s.]*$/) ){
		jAlert('error', "Special Symbol & Number Not Allow In First Name.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}
}

function validateLastName(id){
	globalid= id;
	var lastname = document.getElementById("lastName"+id).value;
	 
	if( lastname.match(/^[a-z][a-z\s.]*$/) ){
		jAlert('error', "Special Symbol & Number Not Allow In Last Name.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}
}
/*

if(mobNo.length!=10 && mobNo!='' && chk==0) {
	if(document.getElementById('islmh')){
		 jAlert('error', "Please Enter Valid Mobile No.", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 chk=1;
	}else{
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
	    document.getElementById('mobNoError').appendChild(mobNo);
	    chk=1;
	    setError3(mobNoErrorlbl);
	}
}else{
	removeError3(mobNoErrorlbl);
}
*/

function showdoctpopupNew(){
	
	var val=document.getElementById("diaryuser").value;
	if(val!=0){
	var department=document.getElementById("userdepartment").value;
	var date=document.getElementById("fromDate").value;

	 var url="checkdiaryexistFinder?nuserid="+val+"&date="+date+"&dept="+department+"";
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showdoctpopupNewRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);   	
	
	 
}else{
	jAlert('error', 'Please Select Doctor.', 'Error Dialog');
	setTimeout(function() {
	$("#popup_container").remove();
	removeAlertCss();
}, alertmsgduration);
}
}
function showdoctpopupNewRequest(){
if (req.readyState == 4) {
         if (req.status == 200) {
	
		var data=req.responseText.split("@#");
		if(data[0]==1){
		
			jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration);
			if(document.getElementById("doctid")){
				document.getElementById("doctid").value="0";
			}
			if(document.getElementById("diaryuser")){
				document.getElementById("diaryuser").value=0;
			}
			
        }else{
					
			document.getElementById("secondarydoc").innerHTML=data[1];
			document.getElementById("primarydocname").innerHTML=data[2];
		}
     }	
	}
}

function getPatientDeptCount(date) {
	
    var url="getPatientDeptCountBookAppointmentAjax?date="+date+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
              
    req.onreadystatechange = getFakeDataRequest;
    req.open("GET", url, true); 
             
    req.send(null);      
        
    
} 

function getFakeDataRequest(){
   if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("patientDeptCountDiv").innerHTML= data;
		   
        }
	}	 
}

function selectFakeCheckBoxfun(val){
	
	if (val== true) {
		$('.checkrandomclass').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.checkrandomclass').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function changeFollowUpDate(){
	var followUpDate = document.getElementById("followUpDate").value;
	var ids="0"; 
	$('.checkrandomclass').each(function(){
		if(this.checked == true){
			ids=ids+","+this.value;
		} 
	});
	
	if(ids=='0'){
		jAlert('error', "Please Select Patient For Change Follow Up Date", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(followUpDate==''){
		jAlert('error', "Please Select Follow Up Date", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "changefollowupdatePatientdata?ids="+ids+"&followUpDate="+followUpDate+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changeFollowUpDateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
}
function changeFollowUpDateRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
				$('#newloaderPopup').modal('show');
				jAlert('success', 'Patient Follow Date Change Successfully.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				window.location='Patientdata';
		}
	}
}


