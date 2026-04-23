/**
 *   Prescription Details JS
 */

/**
 * 
 */

var cell;
var row;
function addRow(tableID) {

	var allcount=Number(document.getElementById("allcount").value);
	allcount++;
	document.getElementById("allcount").value=allcount;	
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	var url = "addnewrowPrescriptiondetails?tableid="+table+"&rowcount="+rowCount+"";
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
	         }
		}	 
	}

function deleteRow(tableID) {
	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var allcount=Number(document.getElementById("allcount").value);
		allcount--;
		document.getElementById("allcount").value=allcount;	

		for (var i = 0; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				if (rowCount <= 2) {

					jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

					break;
				}
				table.deleteRow(i);
				rowCount--;
				i--;
			}

		}
	} catch (e) {
		jAlert('error', e, 'Error Dialog');

	}
}

function confirmedDelete() {

	var r = confirm("Are you sure you want to delete this entry");
	if (r == true) {
		return true;
	} else {
		return false;
	}

}

var s1 = 0;

function validateAF() {

	$(".filedname").each(
			function() {
				var filednameValue = $(this).val();
				if (filednameValue != null && filednameValue != ""
						&& filednameValue != undefined) {
					s1 = 1;
				} else {
					s1 = 0;
					setError(this);
				}

			});

	if (s1 == 1) {
		return true;
	} else {
		return false;
	}

}

function editValidate() {

	validatePromoJSON = {
		"validatorData" : [ {
			"Element" : "#filedname",
			"FunctionName" : "notEmpty"
		} ]
	};
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function addRowSubHead(tableID) {
	var table = document.getElementById(tableID);

	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	var counts = rowCount - 1;

	var cell1 = row.insertCell(0);
	var element1 = document.createElement("input");
	element1.type = "checkbox";
	element1.name = "chkbox[]";
	element1.title = "Delete Row";
	cell1.appendChild(element1);

	var cell2 = row.insertCell(1);
	cell2.innerHTML = counts + 1;

	var cell3 = row.insertCell(2);
	cell3.innerHTML = "<input type='text'  class='form-control showToolTip headingName' name = 'subhead["
			+ counts + "].headingName'>";

}

function deleteRowSubHead(tableID) {
	try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;

		for (var i = 0; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				if (rowCount <= 2) {

					jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

					break;
				}
				table.deleteRow(i);
				rowCount--;
				i--;
			}

		}
	} catch (e) {
		jAlert('error', e, 'Error Dialog');

	}
}
var h1 = 0;

function validateSH() {

	$(".headingName").each(
			function() {
				var filednameValue = $(this).val();
				if (filednameValue != null && filednameValue != ""
						&& filednameValue != undefined) {
					h1 = 1;
				} else {
					h1 = 0;
					setError(this);
				}

			});

	if (h1 == 1) {
		return true;
	} else {
		return false;
	}

}

function editValidateSH() {

	validatePromoJSON = {
		"validatorData" : [ {
			"Element" : "#headingName",
			"FunctionName" : "notEmpty"
		} ]
	};
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function alpha(e) {
	var k;
	document.all ? k = e.keyCode : k = e.which;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
}

function selectAll(name,obj) {
 
       if(obj.checked==true){
       			
       		$('.'+name+'').each(function() { // loop through each checkbox
				this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		    });	 
        
       } else {
       		
           	$('.'+name+'').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
       }
}


function saveValidate(){

     var allcount=Number(document.getElementById("allcount").value);
     
     for(var i=0;i<=allcount;i++){
              
              var location=0;
              var specialization=0;
              $('.casem'+i+'').each(function() { 
				 if(this.checked ==true){
				  
				       location=location+","+this.value;
				 }
     		  });	 
     		  
     		   $('.case').each(function() { 
				 if(this.checked ==true){
				  
				       specialization=specialization+","+this.value;
				 }
     		  });	 
              
           document.getElementById("location"+i).value=location;
           document.getElementById("specialization"+i).value=specialization;       
              
     }

     document.getElementById("investigation_name_form").submit();

}


function updateValidate(){

              var location=0;
              var specialization=0;
              $('.casem0').each(function() { 
				 if(this.checked ==true){
				  
				       location=location+","+this.value;
				 }
     		  });	 
     		  
     		   $('.case').each(function() { 
				 if(this.checked ==true){
				  
				       specialization=specialization+","+this.value;
				 }
     		  });	 
              
           document.getElementById("location").value=location;
           document.getElementById("specialization").value=specialization;  
           document.getElementById("investigation_update_form").submit();
}






