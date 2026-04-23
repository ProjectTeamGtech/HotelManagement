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
	
	var sourceName = document.getElementById('sourceName').value;
	var description = document.getElementById('description').value;
	
	document.getElementById("snameError").innerHTML = "";
	document.getElementById("snotesError").innerHTML = "";
	
	var chk = 0;
	
	if(sourceName == ""){		
		var sname = document.createElement("label");
		sname.innerHTML = "Please add SourceOfIntro";
	    document.getElementById('snameError').appendChild(sname);
	    chk=1;
	}
	if(description == ""){		
		var snotes = document.createElement("label");
		snotes.innerHTML = "Please add Description";
	    document.getElementById('snotesError').appendChild(snotes);
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