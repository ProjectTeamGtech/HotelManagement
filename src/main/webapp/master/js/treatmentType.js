function confirmedDelete(){

var r=confirm("Are you sure you want to delete this entry");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }

}

function saveValidation(){
	var diaryUser = document.getElementById('diaryUser').value;
	//alert(diaryUser);
	//var treatmentName = document.getElementById('treatmentName').value;
	//var treatmentNotes = document.getElementById('treatmentNotes').value;
	
	document.getElementById("practitionerError").innerHTML = "";
	//document.getElementById("tnameError").innerHTML = "";
	//document.getElementById("tnotesError").innerHTML = "";
	
	var chk = 0;
	
	if(diaryUser == 0){		
		var pract = document.createElement("label");
		pract.innerHTML = "Please Select Practitioner";
	    document.getElementById('practitionerError').appendChild(pract);
	    chk=1;
	}
	
	/*if(treatmentName == ""){		
		var tname = document.createElement("label");
		tname.innerHTML = "Please add Template Name";
	    document.getElementById('tnameError').appendChild(tname);
	    chk=1;
	}
	if(treatmentNotes == ""){		
		var tnotes = document.createElement("label");
		tnotes.innerHTML = "Please add Template Notes";
	    document.getElementById('tnotesError').appendChild(tnotes);
	    chk=1;
	}*/
	
	if(chk==1)
    {
       return false;
    }
    else
    {
    	return true;
    }
}