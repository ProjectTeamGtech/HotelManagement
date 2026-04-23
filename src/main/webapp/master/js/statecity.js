
function savestate(){
	var name = document.getElementById("name").value;
	if(name==''){
      	jAlert('error', "please enter name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
      	else{
      		return true;
      	}

}


function saveshelf(){
	var name = document.getElementById("name").value;
	var departmentid = document.getElementById("departmentid").value;
	if(name==''){
      	jAlert('error', "please enter name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}else if(document.getElementById("departmentid").value=='0'){
      		jAlert('error', "Please select department!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}else{
			var id=0;
      		checkShelfAlreadyPresent(name,departmentid,id,0);
      	}
}


function updateshelf(){
	var name = document.getElementById("name").value;
	var departmentid = document.getElementById("departmentid").value;
	var id = document.getElementById("id").value;
	if(name==''){
      	jAlert('error', "please enter name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}else if(document.getElementById("departmentid").value=='0'){
      		jAlert('error', "Please select department!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}else{
      		checkShelfAlreadyPresent(name,departmentid,id,1);
      	}
}


function checkShelfAlreadyPresent(name,departmentid,id,val) {
	  var url="checkshelfalreadypresentShelfMaster?name="+name+"&departmentid="+departmentid+"&id="+id+"&val="+val+"";   	
	  if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	  }else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	  req.onreadystatechange = setSMSRequest;
	  req.open("GET", url, true); 
	  req.send(null);
}
function setSMSRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			if(data=='1'){
				jAlert('error', "Shelf already added to department!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
			}else{
				 document.getElementById("master_form").submit();
			}
		}
	}
}



function savecity(){
	var city = document.getElementById("city").value;
	var stateid = document.getElementById("stateid").value;
	
  	 if(stateid=='0'){
      	jAlert('error', "please select state!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
    
      	}
      	else if(city==''){
      	jAlert('error', "please enter city!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
      	else{
      		return true;
      	}

}

function savecity1(){
	var name = document.getElementById("name").value;
	var link = document.getElementById("link").value;
	var book = document.getElementById("book").value;
	var discr = document.getElementById("discription").value;
  	 if(stateid==''){
      	jAlert('error', "please select name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
    
      	}
      	else if(link==''){
      	jAlert('error', "please enter link!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
    	else if(book==''){
          	jAlert('error', "please enter book!", 'Error Dialog');	
    			setTimeout(function() {
    				$("#popup_container").remove();
    					removeAlertCss();
    				}, alertmsgduration); 	
    			 return false;	
          	}
    	else if(discr==''){
          	jAlert('error', "please enter discription!", 'Error Dialog');	
    			setTimeout(function() {
    				$("#popup_container").remove();
    					removeAlertCss();
    				}, alertmsgduration); 	
    			 return false;	
          	}
      	else{
      		return true;
      	}

}



function ConfirmDelete()
	{
  		var x = confirm("Are you sure you want to delete?");
  		if (x)
      		return true;
  		else
    		return false;
}

//Location Master
function savelocation(){
	var name = document.getElementById("name").value;
	var speciality=document.getElementById("speciality").value;
	var check="0";
	$('.case').each(function() {
		if(this.checked==true){
	    	check =1;
	    }
	});
	document.getElementById("pharmacycheck").value=check;
	   
	if(name==''){
      	jAlert('error', "please enter name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}else if(speciality==''){
	              jAlert('error', "please select speciality!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
	    }
      	else{
      		//return true;
      		checklocation(speciality)
      	}
}

function checklocation(speciality){
	
	var url = "checklocLocationMaster?speciality="+speciality+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checklocationRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	
	
}
function checklocationRequest(){
	 
	 if (req.readyState == 4) {
	      if (req.status == 200) {
		   var str = req.responseText;
		   if(str=='0'){
		   		document.getElementById("master_form").submit();
		   }else{
				 jAlert('error', "already added !", 'Error Dialog');	
			}
		
		}
	}
	
}


function updatelocation(){
	
	var name = document.getElementById("name").value;
	
	var check="0";
	$('.case').each(function() {
		if(this.checked==true){
	    	check =1;
	    }
	});
	document.getElementById("pharmacycheck").value=check;
	   
	if(name==''){
      	jAlert('error', "please enter name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
		}
      	else{
      	     document.getElementById("master_editform").submit();
      	}
	}

//product type master

function saveproducttypemaster(){
	var name = document.getElementById("name").value;
	if(name==''){
      	jAlert('error', "please enter Product type!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
      	else{
      		return true;
      	}
}

function savenabhsubcat(){
	var name = document.getElementById("name").value;
	var catagoryid = document.getElementById("catagoryid").value;
	
  	 if(catagoryid=='0'){
      	jAlert('error', "please select category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
    
      	}
      	else if(city==''){
      	jAlert('error', "please enter sub category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
      	else{
      		return true;
      	}

}


function savenabharea(){
	var name = document.getElementById("name").value;
	var subcatagoryid = document.getElementById("subcatagoryid").value;
	
  	 if(subcatagoryid=='0'){
      	jAlert('error', "please select sub category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
    
      	}
      	else if(city==''){
      	jAlert('error', "please enter area name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
      	else{
      		return true;
      	}

}
function check1(){
	
	var y=	document.getElementById("sharing_type").value;
	var z= document.getElementById("ammount").value;



	
	if(y=='0'){
		if(z>100){
			alert("% cant be more than 100");
			return false;
		}
	}
	}

function addnewusercharge(tableId){
	  
    var table = document.getElementById(tableId);
	 var rowCount = table.rows.length;
	 var sharechargeid=document.getElementById("sharechargeid").value;
	 var shareuserid=document.getElementById("shareuserid").value;
	
	 var flag = false;
	 
	if(sharechargeid=='0'){
		 jAlert('error', "Please select share charge!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(shareuserid=='0'){
		 jAlert('error', "Please select share user!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
		 row=table.insertRow(rowCount);
		 rowCount--;
	     var url="addnewtempuserchargeMaster?count="+rowCount+"&sharechargeid="+sharechargeid+"&shareuserid="+shareuserid+"";
	   
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	     req.onreadystatechange = addnewuserchargeRequest;
	     req.open("GET", url, true); 
	     req.send(null);   
	 }
	 
}
function addnewuserchargeRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			row.innerHTML=req.responseText;
		}
	}	 
}

function savegenericname(){
	var val=document.getElementById('name').value;
	if(val==''){
		jAlert('error', "Please enter generic name!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "checkexistgenericnameMaster?genericname="+val+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savegenericnameRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
}
function savegenericnameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			if(val==1){
				document.getElementById('existlbl').className = "";
				return false;
			}else{
				document.getElementById('existlbl').className = "text-danger hidden";
				document.getElementById('master_form').submit();
			}
       }
		}	 
}

