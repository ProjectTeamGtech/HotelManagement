function addRow(tableID) 
{
	 var table = document.getElementById(tableID);
	 
	 var rowCount = table.rows.length;
     var row = table.insertRow(rowCount);
     var counts = rowCount - 1;
     
     var cell1 = row.insertCell(0);
 	 var element1 = document.createElement("input");
	 element1.type = "checkbox";
 	 element1.name="chkbox[]";
 	 element1.title="Delete Row";
 	 cell1.appendChild(element1);

	 var cell2 = row.insertCell(1);
 	 cell2.innerHTML = counts + 1;
 	 
 	 var cell3 = row.insertCell(2);
 	 cell3.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle detail' name = 'painArea[" + counts + "].detail'>";
	
 	 var cell4 = row.insertCell(3);
 	 cell4.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle vas' name = 'painArea[" + counts + "].vas'>";
	
}

function deleteRow(tableID){
	try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    if(rowCount <= 2) {
                        
                        jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

                        break;
                    }
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
            	jAlert('error', e, 'Error Dialog');

            }
}

function addRowGeneralHealth(tableID) 
{
	 
	 var table = document.getElementById(tableID);
	 
	 var rowCount = table.rows.length;
     var row = table.insertRow(rowCount);
     var counts = rowCount - 1;    
     
     var cell1 = row.insertCell(0);
 	 var element1 = document.createElement("input");
	 element1.type = "checkbox";
 	 element1.name="chkbox[]";
 	 element1.title="Delete Row";
 	 cell1.appendChild(element1);

	 var cell2 = row.insertCell(1);
 	 cell2.innerHTML = counts + 1;
 	 
 	 var cell3 = row.insertCell(2);
 	 cell3.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle health' name = 'generalHealth[" + counts + "].health'>";
	
 	 var cell4 = row.insertCell(3);
 	 cell4.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle yorn' name = 'generalHealth[" + counts + "].yorn'>";
	
 	 var cell5 = row.insertCell(4);
 	 cell5.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle gdetails' name = 'generalHealth[" + counts + "].gdetails'>";
	
 	
}

function deleteRowGeneralHealth(tableID){
	try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                  /*  if(rowCount <= 2) {
                        
                        jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

                        break;
                    }*/
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
            	jAlert('error', e, 'Error Dialog');

            }
}

function addRowOther(tableID) 
{	 
	var table = document.getElementById(tableID);
	 
	var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var counts = rowCount - 1;    
    
    var cell1 = row.insertCell(0);
	 var element1 = document.createElement("input");
	 element1.type = "checkbox";
	 element1.name="chkbox[]";
	 element1.title="Delete Row";
	 cell1.appendChild(element1);

	 var cell2 = row.insertCell(1);
	 cell2.innerHTML = counts + 1;
	 
	 var cell3 = row.insertCell(2);
	 cell3.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle type' name = 'other[" + counts + "].type'>";
	
	 var cell4 = row.insertCell(3);
	 cell4.innerHTML = "<input type='text'  class='form-control showToolTip ppstyle details' name = 'other[" + counts + "].details'>";
	
}

function deleteRowOther(tableID){
	try {
           var table = document.getElementById(tableID);
           var rowCount = table.rows.length;

           for(var i=0; i<rowCount; i++) {
               var row = table.rows[i];
               var chkbox = row.cells[0].childNodes[0];
               if(null != chkbox && true == chkbox.checked) {
                 /*  if(rowCount <= 2) {
                       
                       jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

                       break;
                   }*/
                   table.deleteRow(i);
                   rowCount--;
                   i--;
               }


           }
           }catch(e) {
           	jAlert('error', e, 'Error Dialog');

           }
}


function showAssessmentPopUp(){
	
	var url = "showListClient";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAssessmentPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showAssessmentPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById("allPatient").innerHTML = req.responseText;
				
	         }
		}
	}
	
	function setPatientName(globalClientId){
		//alert('hi');
		//document.getElementById("clientId").value = globalClientId;
		//document.getElementById("clientId1").value = globalClientId;
		var url = "getFullNameClient?id="+globalClientId+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setPatientNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		     
			
	}
	function setPatientNameRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var clientFullName = req.responseText;
			
				//document.getElementById("client").value = clientFullName;
				document.getElementById("client1").value = clientFullName;
				
				
				//$('#clientSearch').modal('hide');
				
				
				
			}
		}

	}
	
	function insertImagePhysio(){
		
		
		var Pic = '';
		//var j = 1;
		 for(var i in LAYERS){
			 Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
			// alert(i);
		}

		 document.getElementById('imageData').value = Pic;
		 
		 document.getElementById('physio_form').submit();
		 
		 
	}
	
	function editImagePhysio(){
		
		var Pic;
		 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
	}
	 
	 document.getElementById('imageData').value = Pic;
	 
	 document.getElementById('physioEdit_form').submit();
	 
	}
	
	
	function setClientsAssessment(practId){
		
		var url = "setClientsAssesmentForms?practId="+practId+"";	
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = setClientsAssessmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}

		function setClientsAssessmentRequest(){
		if (req.readyState == 4) {
				
				
					if (req.status == 200) {
						document.getElementById("physioClient").innerHTML =  req.responseText;
						$("#physioClient").trigger("chosen:updated");
						$(".chosen").chosen({allow_single_deselect: true});	
						
					}
					
			}
		}
		
		function setConditionAssessment(clientId){
				
			//document.getElementById("clientId").value = clientId;
			
			var url = "setClientConditionAssesmentForms?clientId="+clientId+"";	
			globalClientId = clientId;
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = setConditionAssessmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		function setConditionAssessmentRequest(){
			if (req.readyState == 4) {
				
				
				if (req.status == 200) {
					document.getElementById("physioCondition").innerHTML =  req.responseText;
					$("#physioCondition").trigger("chosen:updated");
					$(".chosen").chosen({allow_single_deselect: true});	
					
					document.getElementById("clientId1").value = globalClientId;
					
					
					//setPatientName(globalClientId);
					
				}
				
		}

		}
