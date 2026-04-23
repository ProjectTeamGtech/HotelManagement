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
 	cell3.innerHTML = "<input type='text'  class='form-control showToolTip imagename' name = 'imagename'>";
 	/* cell3.innerHTML = "<input type='text'  class='form-control showToolTip imagename' name = 'assessment[" + counts + "].imagename'>";*/
 	 
 	 var cell4 = row.insertCell(3);
	// cell4.innerHTML = "<input type='file'  name = 'assessment[" + counts + "].file'>";
	 cell4.innerHTML = "<input type='file'  name = 'fileUpload' class = 'fileUpload'>";

	 
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

var s1=0;

function validate(){	
	 
	$(".imagename").each(function () {
        var filednameValue = $(this).val();
        if (filednameValue != null && filednameValue != "" && filednameValue != undefined) {
        	  s1 = 1;          
        }
        else {
        	  s1 = 0;
            setError(this);          
        }
       
    });
	$(".fileUpload").each(function () {
        var filednameValue = $(this).val();
        if (filednameValue != null && filednameValue != "" && filednameValue != undefined) {
        	  s1 = 1;          
        }
        else {
        	  s1 = 0;
            setError(this);          
        }
       
    });
	
	if(s1==1){
		return true;
	}
	else{
		return false;
	}
	

}