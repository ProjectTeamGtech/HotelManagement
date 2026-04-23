var cell;
var row;
function addRow(tableID) {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	var url = "addnewrowStandardcharges?tableid="+table+"&rowcount="+rowCount+"";
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