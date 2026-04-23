
var clientid="";
var practitioner="";
var conditionid="";
var ipdid="";
function repeateNursingAjax(cid,pid,conid,ipdaddmissionid){
	var url = "getrecordNursing?clientId="+cid+"&prectionerid="+pid+"&conditionid="+conid+" ";
	
	clientid=cid;
	practitioner=pid;
	conditionid=conid;
	ipdid=ipdaddmissionid;
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = repeateNursingAjaxRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function repeateNursingAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			var data=str.split("~");
			loadNusringRepeatList(ipdid);
			document.getElementById("nursingmyModalLabel").innerHTML=data[0];
            document.getElementById("ppdatetime").innerHTML=data[1];
            document.getElementById("nursingdoctornameid").innerHTML=data[2];
            
            document.getElementById("nursingtable").innerHTML='';
            $('#addnursing').modal( "show" );   			 
		}
	}
}

function addtempnursing(val){

     var nursingtype_id=document.getElementById("nursingtype_id").value;
     var taskname=document.getElementById("taskname").value;
     var freq=document.getElementById("freq").value;
     var nursingcdays=document.getElementById("nursingcdays").value;
     var cid=clientid;
    // var notes=document.getElementById("noteselect").value;
     if(val==1){
    	cid= document.getElementById("cid").value;
     } 
     var url="addtempNursing?clientid="+cid+"&nursingtype_id="+nursingtype_id+"&taskname="+taskname+"&freq="+freq+"&nursingcdays="+nursingcdays+""; 
      
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addtempnursingRequest;
    req.open("GET", url, true); 
    
    req.send(null);
          
}

function addtempnursingRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
					document.getElementById("nursingtable").innerHTML=req.responseText;
		}
	}
}


function saveNursing(){

     document.getElementById("nclientid").value=clientid;
     document.getElementById("nipdid").value=ipdid;
     document.getElementById("nconditionid").value=conditionid;
     document.getElementById("npractitionerid").value=practitioner;


     document.mynursingform.submit();
}

function deletenursingdata(id){
			
			var url = "delnurNursing?selectedid="+id+"&clientId="+clientid+"&prectionerid="+practitioner+"&conditionid="+conditionid+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = deletenursingdataRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);

}

function deletenursingdataRequest(){

    if (req.readyState == 4) {
				if (req.status == 200) {
					
					document.getElementById('nursingtable').innerHTML = req.responseText;
				}
			}
     

}


function setNursingtask(id){
	var url = "getnursingtasknamelistCommonnew?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setNursingtaskRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setNursingtaskRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("nursingdiv").innerHTML = req.responseText;
				$("#taskname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				//taskname
			}
		}
}
	 

function loadNusringRepeatList(ipdid){
	$( "#baselayout1loaderPopup" ).modal( "show" );
	   var url = "nursingrRequestsNursing?ipdid="+ipdid+"";
	    if (window.XMLHttpRequest) {
	     req = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject) {
	     isIE = true;
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	    }   
	       req.onreadystatechange = loadNusringRepeatListRequest;
	       req.open("GET", url, true); 
	       req.send(null); 

	  }  

function loadNusringRepeatListRequest() {
	 if (req.readyState == 4) {
		  if (req.status == 200) {  
			  var str = req.responseText;
			  
			  document.getElementById('rpeatdivajax11').innerHTML=str;
			  document.getElementById('repeatlist').className='chosen-select';
			  $("#repeatlist").trigger("chosen:updated");
		      $(".chosen-select").chosen({allow_single_deselect: true});
			  $( "#baselayout1loaderPopup" ).modal( "hide" );
		  }}}


function loadnursingcareRepeat(obj) {
	$( "#baselayout1loaderPopup" ).modal( "show" );
	   var url = "loadnursingcareRepeatNursing?parent="+obj.value+"";
	    if (window.XMLHttpRequest) {
	     req = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject) {
	     isIE = true;
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	    }   
	       req.onreadystatechange = loadnursingcareRepeatRequest;
	       req.open("GET", url, true); 
	       req.send(null); 

}


function loadnursingcareRepeatRequest(){
	 if (req.readyState == 4) {
		  if (req.status == 200) {  
			  var str = req.responseText;
			  document.getElementById('nursingtable').innerHTML = req.responseText;
			  $( "#baselayout1loaderPopup" ).modal( "hide" );
		  }}
}