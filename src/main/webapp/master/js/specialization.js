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
	var specialization = document.getElementById('specialization').value;
	var description = document.getElementById('description').value;
	
	document.getElementById("specializationError").innerHTML = "";
	document.getElementById("descriptionError").innerHTML = "";
	
	var chk = 0;
	
	if(specialization == ""){		
		var spec = document.createElement("label");
		spec.innerHTML = "Please add specialization";
	    document.getElementById('specializationError').appendChild(spec);
	    chk=1;
	}
	
	if(description == ""){		
		var desc = document.createElement("label");
		desc.innerHTML = "Please add description";
	    document.getElementById('descriptionError').appendChild(desc);
	    chk=1;
	}
	
	if(chk==1)
    {
       return false;
    }
    else
    {
    	return true;
    }
}