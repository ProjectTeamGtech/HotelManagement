
var wardid=0;
var sectionid=0;
var bedid=0;

function setWardId(id) {
	
	wardid=id;
}

function setSectionId(id){
	sectionid=id;
}

function setBedId(id){
    bedid=id;	
}

function saveWardandContinue() {
	 $('#baselayout1loaderPopup').modal( "show" );
	var newward=document.getElementById('newward').value;
	var procedure= document.getElementById("procedure").value;
	var proc = document.getElementsByName('proc');
	var procval=0;
	for(var i = 0; i < proc.length; i++){
    	if(proc[i].checked){
        	procval = proc[i].value;
    	}
	}
	
	var url="addBed?wardname="+newward+"&plusminus="+procval+"&procedure="+procedure+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveWardandContinueRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function saveWardandContinueRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#baselayout1loaderPopup').modal( "hide" );
			document.getElementById("step2").innerHTML = req.responseText;
			$('.nav-tabs a[href="#tab2"]').tab('show');
		}
	}
	
}

function saveSectionandContinue(){
	$('#baselayout1loaderPopup').modal( "show" );
	 var newsection=document.getElementById("newsection").value;
	 var url="addSectionBed?sectionname="+newsection+"&wardid="+wardid+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveSectionandContinueRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function saveSectionandContinueRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#baselayout1loaderPopup').modal( "hide" );
			document.getElementById("step3").innerHTML = req.responseText;
            setWardList();			
            $('.nav-tabs a[href="#tab3"]').tab('show');
		}
	}	
}


function saveBedandContinue() {
	$('#baselayout1loaderPopup').modal( "show" );
    wardid=document.getElementById("fward").value;
	sectionid=document.getElementById("fsection").value;
	var casualty=document.getElementById("casualty").value;
	if(wardid==''){
		alert("Plz select ward");
		
	}else{
		
		var bedname=document.getElementById("bedname").value;
		var url="addbedBed?bedname="+bedname+"&wardid="+wardid+"&sectionid="+sectionid+"&casualty="+casualty;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveBedandContinueRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
}

function saveBedandContinueRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("step4").innerHTML = req.responseText;
            setWardList2();
          //  setSectionList();
            $('#baselayout1loaderPopup').modal( "hide" );
          $('.nav-tabs a[href="#tab4"]').tab('show');
		}
	}	
}



function setWardList() {
	
	var url="setWardListBed";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setWardListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setWardListRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("stepp2").innerHTML = req.responseText;
			//setSectionList();
		}
	}	
}

function setSectionList() {
	var url="setSectionListBed";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSectionListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setSectionListRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("newsectionlist").innerHTML = req.responseText;
		}
	}	
}


function setWardList2() {
	
	var url="setWardListBed";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setWardList2Request;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setWardList2Request() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("newwardlist").innerHTML = req.responseText;
			setSectionList();
		}
	}	
}

function saveEquipmentandSubmit() {
	
	wardid=document.getElementById("fward").value;
	if(wardid==''){
		alert("plz select ward");
		
	}else{
		$('#baselayout1loaderPopup').modal( "show" );
		sectionid=document.getElementById("fsection").value;
		bedid=document.getElementById("fbed").value;
		var equipmentname=document.getElementById("equipmentname").value;
		var url="saveequipmentBed?equipment="+equipmentname+"&wardid="+wardid+"&sectionid="+sectionid+"&bedid="+bedid+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setWardList2Request;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	

}

function saveEquipmentandSubmitRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("demo").innerHTML = req.responseText;
			//setSectionList();
		}
	}	
}





