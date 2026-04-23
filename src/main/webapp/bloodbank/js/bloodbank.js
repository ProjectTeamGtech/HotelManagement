
var cell;
var row;
var indexq=0;	

function addRow(tableID) {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	indexq=rowCount-1;
	var url = "addajaxgroupBloodbank?&rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


 function addRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;
		          
		          
					  $("#expiry_date"+indexq+"").datepicker({
			
						dateFormat : 'dd-mm-yy',
						yearRange: yearrange,
						minDate : '30-12-1880',
						changeMonth : true,
						changeYear : true
			
					});
      
	         }
		}	 
	}



  function addBloodDonor(id){
  
      var blood_group=document.getElementById("blood_group"+id+"").value;
      var no_bags=document.getElementById("no_bags"+id+"").value;
      var expiry_date=document.getElementById("expiry_date"+id+"").value;
     
      if(blood_group=='0'){
     		 jAlert('error', "please select blood group!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
     		    
      }else if(no_bags==''){
             jAlert('error', "please enter no_bags!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            
      
      }else if(expiry_date==''){
           
              jAlert('error', "please select expiry date!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
      } else { 
     
			  document.getElementById("tblood_group").value=blood_group;
			  document.getElementById("tno_bags").value=no_bags;
			  document.getElementById("texpiry_date").value=expiry_date;
			  document.getElementById("tmpbloodform").submit();
			 
     }
  }


 function editgroupajax(id){
  
     indexq=id;
     var url = "editgroupajaxBloodbank?&id="+id+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editgroupajaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
  
 }
 
 function editgroupajaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			     document.getElementById(indexq).innerHTML=req.responseText;
			     
			     $("#expiry_date"+indexq+"").datepicker({
			
						dateFormat : 'dd-mm-yy',
						yearRange: yearrange,
						minDate : '30-12-1880',
						changeMonth : true,
						changeYear : true
			
					});
      
	         }
		}	 
	}


   function updategroup(id) {
   
         var blood_group=document.getElementById("blood_group"+id+"").value;
      var no_bags=document.getElementById("no_bags"+id+"").value;
      var expiry_date=document.getElementById("expiry_date"+id+"").value;
     
      if(blood_group=='0'){
     		 jAlert('error', "please select blood group!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
     		    
      }else if(no_bags==''){
             jAlert('error', "please enter no_bags!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
            
      
      }else if(expiry_date==''){
           
              jAlert('error', "please select expiry date!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
      } else { 
     
			  document.getElementById("tblood_group").value=blood_group;
			  document.getElementById("tno_bags").value=no_bags;
			  document.getElementById("texpiry_date").value=expiry_date;
			  document.getElementById("tid").value=id;
			  var d=document.getElementById("tmpbloodform");
			  d.action="updategroupBloodbank";
			  d.submit();
      }
   
   }



function addUpdateBloodGroup() {

     var id=document.getElementById("bid").value;

     if(id>0){
          
         document.bloodgroupform.action="updategroupBloodbank";
         document.bloodgroupform.submit(); 
         
          //update
     } else {
         //add
         document.bloodgroupform.action="addgroupBloodbank";
         document.bloodgroupform.submit();
         
     }
      
    
}

function isValidDonor(){

   var name=document.getElementById("name").value;
   var age=document.getElementById("age").value;
   var phone=document.getElementById("phone").value;
   var email=document.getElementById("email").value;
   var bloodgroup=document.getElementById("bg1").value;
   var last_donation_date=document.getElementById("last_donation_date").value;
   
   var isError=false;
   
   if(name.length<1){
        isError=true;
        document.getElementById("errname").innerText="please enter the name!";
        
   }
   else {
       document.getElementById("errname").innerText="";
   }
   if(age.length<1){
        isError=true;
        document.getElementById("errage").innerText="please enter the age!";
        
   }
   else {
       document.getElementById("errage").innerText="";
   }
   if(phone.length<10){
        isError=true;
        document.getElementById("errphone").innerText="please enter the valid phone!";
        
   }
   else {
       document.getElementById("errphone").innerText="";
   }
   if(bloodgroup.length<1){
      document.getElementById("errbg1").innerText="please select blood group..";
      isError=true;
    
   }else {
      document.getElementById("errbg1").innerText="";
   
   }
   if(email.search("@")<0 || email.search(".")<0){
        isError=true;
        document.getElementById("erremail").innerText="please enter the valid email!";
        
   }
   else {
       document.getElementById("erremail").innerText="";
   }
    if(last_donation_date.length<1){
        isError=true;
        document.getElementById("errlast_donation_date").innerText="please enter the valid date!";
        
   }
   else {
       document.getElementById("errlast_donation_date").innerText="";
   }
   
   if(isError==true){
      
        return false;
   } else {
   
       return true;
   }
   
   
}


function addUpdateBloodDonor() {

  var t=isValidDonor();
  
  if(t==true) {
   var id=document.getElementById("id").value;
   
   if(id>0){
          //update 
           document.blooddonor_form.action="updatedonorBloodbank";
             document.blooddonor_form.submit();
      
   } else {
       //add
       
       document.blooddonor_form.action="adddonorBloodbank";
       document.blooddonor_form.submit();
   }
   
  } 

}


function editdonor(id){
  
    var url="editdonorBloodbank?selectedid="+id+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editdonorRequest;
    req.open("GET", url, true); 
              
    req.send(null);
     
} 

function editdonorRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
 		  
 		    var str=req.responseText;
 		    
 		    var data=str.split("$");
 		    
 		    document.getElementById("name").value=data[0];
 		     
 		    if(data[1]=="Male"){
 		       document.getElementById("male").checked=true;  
 		    }
 		    else {
 		       document.getElementById("female").checked=true;  
 		    }
 		    
 		    document.getElementById("age").value=data[2];
 		    document.getElementById("phone").value=data[3];      
 			document.getElementById("email").value=data[4];
 			document.getElementById("bg1").value=data[5];
 			document.getElementById("last_donation_date").value=data[6];
 			document.getElementById("id").value=data[7];
 			 
 			$('#addDonor').modal( "show" );
 		}
    }
}

function addDonorPopup() {

            document.getElementById("name").value="";   
 		    document.getElementById("age").value="";
 		    document.getElementById("phone").value="";      
 			document.getElementById("email").value="";
 			document.getElementById("bg1").value="";
 			document.getElementById("last_donation_date").value="";
 			document.getElementById("id").value="";
 			 
 			$('#addDonor').modal( "show" );
}



function editgroup(id){
  
    var url="editgroupBloodbank?selectedid="+id+"";
    
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editgroupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
     
} 

function editgroupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
 		  
 		    var str=req.responseText;
 		    
 		    var data=str.split("$");
 		   
 		    document.getElementById("bid").value=data[0];
 			document.getElementById("blood_group").value=data[1];
 			document.getElementById("no_bags").value=data[2]; 			 
 			$('#editbgroup').modal( "show" );
 		}
    }
}

function cnfmDelete() {
  
   var t=confirm("Are you sure you want to delete?");  
   if(t==true){
       return true;
   } else {
      return false;
   }
}


function searchDonor() {

    document.searchform.submit();    
 
}





function adddonorRow(tableID) {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	indexq=rowCount-1;
	var url = "addajaxdonorBloodbank?&rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = adddonorRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


 function adddonorRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;
		          
		          
					  $("#dob"+indexq+"").datepicker({
			
						dateFormat : 'dd-mm-yy',
						yearRange: yearrange,
						minDate : '30-12-1880',
						changeMonth : true,
						changeYear : true
			
					});
      
	         }
		}	 
	}


function saveDonor(id){
   
    var name=document.getElementById("name"+id+"").value;   
    var dob=document.getElementById("dob"+id+"").value;
    var gender=document.getElementById("gender"+id+"").value;
    var blood_group=document.getElementById("blood_group"+id+"").value;
    var weight=document.getElementById("weight"+id+"").value;
    var donor_state=document.getElementById("donor_state"+id+"").value;
    var phone=document.getElementById("phone"+id+"").value;
    var email=document.getElementById("email"+id+"").value;
    var address=document.getElementById("address"+id+"").value;
    var city=document.getElementById("city"+id+"").value;
    
    if(name==''){
           jAlert('error', "please enter donor name!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);  
         
    } else if(dob==''){
    
           jAlert('error', "please enter dob!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(gender=='0'){
    
           jAlert('error', "please select gender!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(blood_group=='0'){
    
           jAlert('error', "please select gender!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(weight==''){
    
           jAlert('error', "please enter weight!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(donor_state=='0'){
    
           jAlert('error', "please select donor state!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(phone==''){
    
           jAlert('error', "please enter phone!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(email==''){
    
           jAlert('error', "please enter email!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    }  else if(city==''){
    
           jAlert('error', "please enter city!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else {
       
             document.getElementById("tname").value=name;
             document.getElementById("tdob").value=dob;
             document.getElementById("tgender").value=gender;
             document.getElementById("tblood_group").value=blood_group;
             document.getElementById("tweight").value=weight;
             document.getElementById("tdonor_state").value=donor_state;
             document.getElementById("tphone").value=phone;
             document.getElementById("temail").value=email;
             document.getElementById("taddress").value=address;
             document.getElementById("tcity").value=city;
             
             document.getElementById("donorform").submit();
             
           
    }
    
}


function editdonorajax(id){

    indexq=id;
    var url = "editdonorajaxBloodbank?&id="+id+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editdonorajaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

}

function editdonorajaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			     document.getElementById(indexq).innerHTML=req.responseText;
			     
			     $("#dob"+indexq+"").datepicker({
			
						dateFormat : 'dd-mm-yy',
						yearRange: yearrange,
						minDate : '30-12-1880',
						changeMonth : true,
						changeYear : true
			
					});
      
	         }
		}	 
	}


 function updateDonor(id){
    
     var name=document.getElementById("name"+id+"").value;   
    var dob=document.getElementById("dob"+id+"").value;
    var gender=document.getElementById("gender"+id+"").value;
    var blood_group=document.getElementById("blood_group"+id+"").value;
    var weight=document.getElementById("weight"+id+"").value;
    var donor_state=document.getElementById("donor_state"+id+"").value;
    var phone=document.getElementById("phone"+id+"").value;
    var email=document.getElementById("email"+id+"").value;
    var address=document.getElementById("address"+id+"").value;
    var city=document.getElementById("city"+id+"").value;
    
    if(name==''){
           jAlert('error', "please enter donor name!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);  
         
    } else if(dob==''){
    
           jAlert('error', "please enter dob!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(gender=='0'){
    
           jAlert('error', "please select gender!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(blood_group=='0'){
    
           jAlert('error', "please select gender!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(weight==''){
    
           jAlert('error', "please enter weight!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(donor_state=='0'){
    
           jAlert('error', "please select donor state!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(phone==''){
    
           jAlert('error', "please enter phone!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else if(email==''){
    
           jAlert('error', "please enter email!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    }  else if(city==''){
    
           jAlert('error', "please enter city!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
    } else {
       
             document.getElementById("tname").value=name;
             document.getElementById("tdob").value=dob;
             document.getElementById("tgender").value=gender;
             document.getElementById("tblood_group").value=blood_group;
             document.getElementById("tweight").value=weight;
             document.getElementById("tdonor_state").value=donor_state;
             document.getElementById("tphone").value=phone;
             document.getElementById("temail").value=email;
             document.getElementById("taddress").value=address;
             document.getElementById("tcity").value=city;
             document.getElementById("tid").value=id;
             
             var d=document.getElementById("donorform");
             d.action="updatedonorBloodbank";
             d.submit();
             
           
    }
    
 }
 
 
 function donatePatient(tableID){
 
      var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	indexq=rowCount-1;
	var url = "addpatientbloodBloodbank?&rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = donatePatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
 
 }
 
 function donatePatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;
		          
	         }
		}	 
	}


   var tid;

   function setbloodGroup(bid,index) {
       tid=index;
       var url = "getgroupBloodbank?&id="+bid+"&index="+index+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setbloodGroupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
   
   }
   
   function setbloodGroupRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          document.getElementById("blood_group"+tid+"").value=req.responseText;
		          
	         }
		}	 
	}
	
	function getipddetails(ipd,index) {
	  
	   ipdid=ipd;
	   tid=index;
       var url = "getipddataBloodbank?&id="+ipd+"&index="+index+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getipddetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
	   
	}
	
	function getipddetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			      var dstr=req.responseText;
			      var str=dstr.split("~");
		          document.getElementById("wardbed"+tid+"").innerHTML=str[0];
		          clientid=str[1];
	         }
		}	 
	}
	
	
	function saveDonorpatient(id){

	  tid=id;
	  var donorid=document.getElementById("donor"+id+"").value;
	  var blood_group=document.getElementById("blood_group"+id+"").value;
	  
	  var url = "donatetopatientBloodbank?&donorid="+donorid+"&ipdid="+ipdid+"&clientid="+clientid+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveDonorpatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
	 
	
	}
	
	function saveDonorpatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			     window.location.reload();
	         }
		}	 
	}
	
	
   function editDonorPatient(id) {
   
        var url="editdonatepatientBloodbank?id="+id+"";
   		tid=id;
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editDonorPatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
      
   }	
	
   function editDonorPatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			     document.getElementById(tid).innerHTML=req.responseText;  
			     
	         }
		}	 
	}
		
	function updateDonorpatient(id){
	  tid=id;
	  var donorid=document.getElementById("donor"+id+"").value;
	  var blood_group=document.getElementById("blood_group"+id+"").value;
	  var url = "updatedonatetopatientBloodbank?&donorid="+donorid+"&ipdid="+ipdid+"&clientid="+clientid+"&id="+id+"";
   		tid=id;
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateDonorpatientRequest;
	    req.open("GET", url, true); 
	    req.send(null);   
	}
	
	function updateDonorpatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			     window.location.reload();
	         }
		}	 
	}
	
	
	function editBlood(index,required) {
	
	    var givval=Number(document.getElementById("given"+index).innerHTML);
		document.getElementById("given"+index).innerHTML="<input type='text' value='"+givval+"' id='alloc"+index+"' onchange='checkwrap(this,"+required+")' class='form-control stockheight' style='width: 60%;'>";					
	    document.getElementById("edit"+index).innerHTML="<a href='#' disabled='disabled' onclick='allocateBlood("+index+")'>Update</a>";
	}
	
	
	
	
	function allocateBlood(index){
	  
	    var alloc=document.getElementById("alloc"+index).value;
	   
	    var url="allocatebloodBloodbank?id="+index+"&alloc="+alloc+"";
	 
	    if (window.XMLHttpRequest) {
		   req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = allocateBloodRequest;
	    req.open("GET", url, true); 
	    req.send(null);   
	
	}
	function allocateBloodRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			     window.location.reload();
	         }
		}	 
	}
	
	function checkwrap(obj,req) {
	
	      var val=Number(obj.value);
	      if(val>req || val==0){
				var id=obj.id;
				document.getElementById(id).value='';
	      } 
	}
	
	
	function addbankRow(tableID){
	
	    var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		row=table.insertRow(rowCount);
		
		row.innerHTML="<td><input type='text' class='form-control' id='name"+rowCount+"' placeholder='Enter Bank Name'></td><td><input type='text' id='address"+rowCount+"' class='form-control' placeholder='Enter Address'></td><td><input type='text' id='mobile"+rowCount+"' class='form-control' placeholder='Enter Mobile'></td><td><input type='button' class='btn btn-primary' onclick='savebankData("+rowCount+")'  value='SAVE'></td>";
				
	}
	
	
	function savebankData(idx) {
	 
	   var name=document.getElementById("name"+idx).value;
	   var address=document.getElementById("address"+idx).value;
	   var mobile=document.getElementById("mobile"+idx).value;
	   
	   document.getElementById("bname").value=name;
	   document.getElementById("baddress").value=address;
	   document.getElementById("bmobile").value=mobile;
	    
	   document.getElementById("donorform").submit();
	
	}
	
	
	
	function editbankajax(idx) {
	    
	    indexq=idx;
	     	var url="editbankBloodbank?id="+idx+"";
	           if (window.XMLHttpRequest) {
		   			req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
	    		req.onreadystatechange = editbankajaxRequest;
	    		req.open("GET", url, true); 
	    		req.send(null);   
	}
	
	function editbankajaxRequest(){
		if(req.readyState == 4) {
			if(req.status == 200) {
			     
			      document.getElementById(indexq).innerHTML=req.responseText;
	         }
		}	 
	}
	
	function updateBankDetails(id){
	
	     var name=document.getElementById("name"+id).value;
	     var address=document.getElementById("address"+id).value;
	     var mobile=document.getElementById("mobile"+id).value;
	     
	     
	      document.getElementById("bid").value=id;
	      document.getElementById("bname").value=name;
	   	  document.getElementById("baddress").value=address;
	      document.getElementById("bmobile").value=mobile;

		  document.getElementById("donorform").action="updatebankBloodbank";		      
	      
	   	  document.getElementById("donorform").submit();
	    
	}
	
	
	function bloodrequest(bg){
			var url="smstodonorBloodbank?bloodgroup="+bg+"";
	           if (window.XMLHttpRequest) {
		   			req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
	    		req.onreadystatechange = bloodrequestRequest;
	    		req.open("GET", url, true); 
	    		req.send(null);   
	}
	
	function bloodrequestRequest(){
		if(req.readyState == 4) {
			if(req.status == 200) {
			     
			      var data=req.responseText;
			      var str=data.split("~");
			      document.getElementById("blood_group").value=str[0];
			      document.getElementById("donorlist").innerHTML=str[1];
			      document.getElementById("banklist").innerHTML=str[2];
			      document.getElementById("smstext").value=str[3];
				 $('#donarlist').modal( "show" ); 				     
	         }
		}	 
	}
	
	function selectAllUser(obj,classname){
	
	            if(obj.checked==true){
	                 
	                  $('.'+classname+'').each(function() {
	                  
							this.checked = true; // 
					 });
	                  
	            }  else {
	             
	            		$('.'+classname+'').each(function() {
	                  
							this.checked = false; // 
						});
	            }     
	        
	}
	
	
	
	function sendsms(){
	
	     var users=0;
	     var banks=0;
	     
	      var msg=document.getElementById("smstext").value;
	      $('.ucase').each(function() {
	                  
	                  if (this.checked == true) {
	                     
	                         users=users+","+this.value;
	                  }
		  });
	       $('.bcase').each(function() {
	                  
	                  if (this.checked == true){
	                     
	                         banks=banks+","+this.value;
	                  }
							 
		  });
	      
	      document.getElementById("message").value=msg;
	      document.getElementById("users").value=users;
	      document.getElementById("banks").value=banks;
	      document.getElementById("smsform").submit();
	      
	}
	

function doCrossMatch(id,blood_group_id,blood_group,clientname,pract_name,qty) {
	
	 var dataObj={
	  	"id":""+id+"",
	  	"blood_group_id":""+blood_group_id+"",
	  	"qty":""+qty+"",
	 };
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "getcrossmatchdataBloodbank",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   document.getElementById("patientnamecrossmatchdiv").innerHTML=clientname;
		   document.getElementById("practnamecrossmatchdiv").innerHTML=pract_name;
		   document.getElementById("bloodgroupcrossmatchdiv").innerHTML=blood_group;
		   document.getElementById("reqqtycrossmatchdiv").innerHTML=qty;
		   document.getElementById("crossmatchid").value=id;
		   document.getElementById("productlistcrossmatchdiv").innerHTML =data.crossProductList;
		   $("#crossmatchproductid").trigger("chosen:updated");
		   $(".chosen").chosen({allow_single_deselect: true});
		   $("#crossmatchmodel").modal('show');
	   },
	   error : function(result) {
		   jAlert('error', "Something wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }
	}); 
}

function validatecrossmatch() {
	 	var productid = document.getElementById("crossmatchproductid").value;
	 	var crossmatchid = document.getElementById("crossmatchid").value;
		if(productid=='0'){
			jAlert('error', "Please select blood bag!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	 	}else{
 			var dataObj={
			  	"crossmatchid":""+crossmatchid+"",
			  	"productid":""+productid+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "savecrossmatchdataBloodbank",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   window.location.reload();	    
			   },
			   error : function(result) {
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			}); 
	 	}
}


function doIssueBloodBank(id,blood_group,clientname,qty,crossmatch_productid) {
 		var dataObj={
		  	"id":""+id+"",
		  	"blood_group":""+blood_group+"",
		  	"qty":""+qty+"",
		  	"clientname":""+clientname+"",
		  	"productid":""+crossmatch_productid+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getissueproductdataBloodbank",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   document.getElementById("patientnameissuediv").innerHTML=clientname;
			   document.getElementById("bloodgroupissuediv").innerHTML=blood_group;
			   document.getElementById("reqqtyissuediv").innerHTML=qty;
			   document.getElementById("reqqtyissueqty").value=qty;
			   document.getElementById("issuebloodid").value=id;
			   document.getElementById("productissueid").value = crossmatch_productid;
			   document.getElementById("productlistissuediv").innerHTML =data.productdata;
			   $("#issuebloodbankmodel").modal('show');
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
}

function validateissuebloodbank() {
 	var issuehandoverid = document.getElementById("issuehandoverid").value;
 	var issuebloodid = document.getElementById("issuebloodid").value;
 	var reqqtyissueqty = document.getElementById("reqqtyissueqty").value;
 	var productissueid = document.getElementById("productissueid").value;
	if(issuehandoverid==''){
		jAlert('error', "Please enter handover to!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
 	}else{
			var dataObj={
		  	"issuehandoverid":""+issuehandoverid+"",
		  	"issuebloodid":""+issuebloodid+"",
		  	"reqqtyissueqty":""+reqqtyissueqty+"",
		  	"productissueid":""+productissueid+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "saveissuebloodbankdataBloodbank",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   window.location.reload();	    
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
 	}
}



//  07 Sep 2018
var bloodbankid="0";
var bloodbankclientid="0";
function showAddchargePopupBloodbank(id,clientid){
	bloodbankid=id;
	bloodbankclientid =clientid;
	var url = "truncateBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = showAddchargePopupCathRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function showAddchargePopupCathRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			addOutoBloodBankCharge();
		}
	}
	
}


function addOutoBloodBankCharge(){
	var url = "setchargesofbloodBloodbank?bloodbankid="+bloodbankid+"&ipdclientid="+bloodbankclientid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addOutoBloodBankChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function addOutoBloodBankChargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setbloodbankipdopdCashDesk();
			$('#addchargepopupblood').modal( "show" );
		}
	}
}

//ipd and opd add charges

function setbloodbankipdopdCashDesk(){
	var selectedUser = bloodbankclientid;
	var cookiecommencing = '';
	var cookieSelectedAppointmentid = 0;
	var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
		
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setbloodbankipdopdCashDeskRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

	function setbloodbankipdopdCashDeskRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("cashDesk31").innerHTML = req.responseText;
				   document.getElementById('chargeTotal3').value = document.getElementById('hiddenTotal').value;
			}
		}
	}
	
	function confirmedDelete1(id){
		//alert(id);
		
	  	var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
	  
	 
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = confirmedDeleteRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
	}

	function confirmedDeleteRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				setbloodbankipdopdCashDesk();
			}
		}
	}
	
	
	var opdcash = '';
	function createChargeAndUpdateAccountBloodbank(action){
		opdcash = action;
		var clientid = bloodbankclientid; 
		var practitionerid = "0";
		var clientName = "0";
		var practitionerName = "";
		var appointmentid = "0";
		var tratmentepisodeid = "0";
		var treatmenntsessions = "0";
		var location = "1";
		
		if(location==0){
			jAlert('error', 'Please select location.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var ipd = 0;
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+" ";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createChargeAndUpdateAccountRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
	}
		
	function createChargeAndUpdateAccountRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				updateBloodbankStatus(bloodbankid);
			}
		}
	}
	
	var opdcash = '';
	function updateBloodbankStatus(bloodbankid){
			var url = "updatebloodbankstatusBloodbank?bloodbankid="+bloodbankid+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = updateBloodbankStatusRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
		
	function updateBloodbankStatusRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				jAlert('success', 'Charge added successfully.', 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
				window.location.reload();
			}
		}
	}

	
	
	
	
 