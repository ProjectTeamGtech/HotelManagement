
function selectCategoryDetails(id){
	 var url ="getcategorydetailsDietarydetails?name="+id+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectCategoryRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectCategoryRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			   document.getElementById("tdsubcat").innerHTML=req.responseText;
			   $("#subcategory").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
		}
	}
}



function selectCalories(id){
	//var id1 = parseInt(id);
	 var url ="getallcalorisDietarydetails?id="+id+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectCaloriesRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectCaloriesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			var data = req.responseText;
			var str = data.split("~");
			
			   document.getElementById("tdcal").innerHTML=str[0];
			   document.getElementById("tdproo").innerHTML=str[1];
			   document.getElementById("natd").innerHTML=str[2];
			   document.getElementById("ktd").innerHTML=str[3];
			   $("#calories").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
		}
	}
}

 function afterchangeDietryQty(){
	 var qty=Number(document.getElementById("dqty").value);
	 var protien=Number(document.getElementById("prohd").value);
	 var calary=Number(document.getElementById("calhi").value);
	 var sodium=Number(document.getElementById("sohi").value);
	 var potasium=Number(document.getElementById("pothi").value);
	 
	 document.getElementById("caloriesval").value=(qty*calary).toFixed(2);
	 document.getElementById("proteinval").value=(qty*protien).toFixed(2);
	 document.getElementById("nasodium").value=(qty*sodium).toFixed(2);
	 document.getElementById("kpotassium").value=(qty*potasium).toFixed(2);
 }
 
 
 
var xxobj; 
function addTempDiet(obj){
	  
	var dietplan = document.getElementById("dietplan").value;
	var category = document.getElementById("category").value;
	var subcategory = document.getElementById("subcategory").value;
	var protein = document.getElementById("protein").value;
	var calories = document.getElementById("calories").value;
	var feed = document.getElementById("feed").value;
	var duration = document.getElementById("duration").value;
	var potassium=document.getElementById("pothi").value;
	var sodium=document.getElementById("sohi").value;
	var dqty=document.getElementById("dqty").value;
	var remark=document.getElementById("dremark").value;
	
	if(dietplan=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(category=='0'){
		jAlert('error', "please select Diet Category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(subcategory=='0'){
		jAlert('error', "please select Diet!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			
	}else if(calories=='0'){
		jAlert('error', "please select Energy!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(feed=='0'){
		jAlert('error', "please select Feed!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	} else{
		var url = "addTempDietarydetails?dietplan="+dietplan+"&category="+category+"&subcategory="+subcategory+"&protein="+protein+"&calories="+calories+"&feed="+feed+"&duration="+duration+"";
		url=url+"&dqty="+dqty+"&potassium="+potassium+"&sodium="+sodium+"&remark="+remark;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
    	req.onreadystatechange = addTempRequest;
    	req.open("GET", url, true); 
    	req.send(null);
 	}
 }
 function addTempRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
				//document.getElementById("dietplan").value = '0';
				//document.getElementById("category").value = '0';
				//document.getElementById("subcategory").value = '0';
				//document.getElementById("calories").value = '0';
				//document.getElementById("feed").value = '0';
			    document.getElementById("diettable").innerHTML=req.responseText;
			    document.getElementById("dremark").value="";
			    
			    document.getElementById("feed").value='0';
		}
	}
}


function deletedietdata(i){
	var url = "deleteTempDietarydetails?i="+i+"";
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}
	else if(window.ActiveObject){
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = deleteTempRequest;
	req.open("GET",url,true);
	req.send(null);
}
function deleteTempRequest(){
	if(req.readyState == 4){
		if(req.status == 200){
		 	document.getElementById("diettable").innerHTML = req.responseText;
		}
	}
}


function selectedbed(id){
 		var url = "getpatientinfoDietarydetails?ipdid="+id+"";
 		if(window.XMLHttpRequest){
	 		req = new XMLHttpRequest();
 		}
 		else if(window.ActiveObject){
 			isIE = true;
 			req = new ActiveXObject("Microsoft.XMLHTTP");		
 		} 
 		req.onreadystatechange = getallpatientinfoRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function getallpatientinfoRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				var data = str.split("~");
				document.getElementById("dcondition").innerHTML = data[0];
				document.getElementById("ward").innerHTML= data[1];
				document.getElementById("bedid").innerHTML= data[2];
				document.getElementById("age").innerHTML= data[3];
				document.getElementById("gender").innerHTML= data[4];
				document.getElementById("consultant").innerHTML= data[5];
				/*document.getElementById("datetime").innerHTML= data[6];*/
				//document.getElementById("divtemplate").innerHTML= data[7];
				$('#dietmodal').modal( "show" );
			}
		}
	}

function savedietaryInfo(){

	var addmissionid = document.getElementById("patient_id").value;
	var date = document.getElementById("fromdate1").value;
	var rowCount = $('#tabletrcount >tbody >tr').length;
	
	var url= "storedietDietarydetails?addmissionid="+addmissionid+"&date1="+date+"";
	if(addmissionid=='0'){
		jAlert('error', "please select Patient!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(rowCount=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = savedietaryRequest;
    req.open("GET", url, true); 
    req.send(null);
  }
}
 function savedietaryRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			location.reload();
		}
	}
}

 
 
 
 function savedietaryInfoAjax(){

		var addmissionid = document.getElementById("patient_id").value;
		var date = document.getElementById("fromdate1").value;
		var rowCount = $('#tabletrcount >tbody >tr').length;
		var hh=document.getElementById("hh").value;
		var mm=document.getElementById("mm").value;
		
		var url= "storediet_ajaxDietarydetails?addmissionid="+addmissionid+"&date1="+date+"&hh="+hh+"&mm="+mm;
		if(addmissionid=='0'){
			jAlert('error', "please select Patient!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);			 		
		}else if(rowCount=='0'){
			jAlert('error', "please select Diet Plan!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
		}else{
			$('#baselayout1loaderPopup').modal( "show" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = savedietaryAjaxRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	  }
	}
	 function savedietaryAjaxRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {	  
				var str= req.responseText;
			       if(document.getElementById('maintextarea')){
				    	  
						nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str + "<br>");
			       }
			       $('#baselayout1loaderPopup').modal( "hide" );
			       $('#dietmodal').modal( "hide" );
			       jAlert('success', 'Diet Stored!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			       
			}
		}
	}
 
 function setdeitFromClinical(){
	 var ipdid=document.getElementById('ipdid').value;
	 if(ipdid!='0'){
		 $('#dietmodal').modal( "show" );
		 document.getElementById('patient_id').value=ipdid;
		 document.getElementById('diettable').innerHTML='';
		 
		 selectedbed(ipdid);
		 
	 }
	 
 }
 
 
 
 
function confirmDelete(){
var agree=confirm("Are you sure you want to delete this plan?");
	if(agree){
    	 return true ;
	}
	else{
    	 return false ;
	}
}

function editDiet(id,pid){
 		var url = "getPatientDataForEditDietarydetails?ipdid="+id+"&parentid="+pid+"";
 		if(window.XMLHttpRequest){
	 		req = new XMLHttpRequest();
 		}
 		else if(window.ActiveObject){
 			isIE = true;
 			req = new ActiveXObject("Microsoft.XMLHTTP");		
 		} 
 		req.onreadystatechange = selectedBedfrEditRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function selectedBedfrEditRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				var data = str.split("~");
				document.getElementById("econdition").innerHTML = data[0];
				document.getElementById("eward").innerHTML= data[1];
				document.getElementById("ebedid").innerHTML= data[2];
				document.getElementById("eage").innerHTML= data[3];
				document.getElementById("egender").innerHTML= data[4];
				document.getElementById("econsultant").innerHTML= data[5];
				document.getElementById("edatetime").innerHTML= data[6];
				document.getElementById("seleditdiv").innerHTML= data[7];
				document.getElementById("editdietdiv").innerHTML= data[8];
				document.getElementById("pid").value= data[9];
				document.getElementById("tempcount").value= data[10];
				$('#editdietmodal').modal( "show" );
		}
	}
}

var cell;
var row;
function editTempDiet(){

	var dietplan = document.getElementById("e_dietplan").value;
	var category = document.getElementById("e_category").value;
	var subcategory = document.getElementById("e_subcategory").value;
	var calories = document.getElementById("p_protein").value;
	var calories = document.getElementById("e_calories").value;
	var feed = document.getElementById("e_feed").value;
	var duration = document.getElementById("e_duration").value;
	
	var table = document.getElementById("tablecount");
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	
	var url = "editTempDataDietarydetails?dietplan="+dietplan+"&category="+category+"&subcategory="+subcategory+"&protein="+protein+"&calories="+calories+"&feed="+feed+"&duration="+duration+"&counts="+counts+"";
	
	if(dietplan=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(category=='0'){
		jAlert('error', "please select Diet Category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(subcategory=='0'){
		jAlert('error', "please select Diet!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(calories=='0'){
		jAlert('error', "please select Energy!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(feed=='0'){
		jAlert('error', "please select Feed!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	} else{
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
    	req.onreadystatechange = editTempRequest;
    	req.open("GET", url, true); 
    	req.send(null);
 	}
 }
 function editTempRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			    //document.getElementById("editdietdiv").innerHTML=req.responseText;
			    var dd=req.responseText;
			    row.innerHTML=dd;
		        var total=Number(document.getElementById("tempcount").value);
		    	total++; 
				document.getElementById("tempcount").value=total;
		}
	}
}


//function deleteUpdatedData(i){
//	var url = "deleteUpdatedDataDietarydetails?i="+i+"";
//	if(window.XMLHttpRequest){
//		req = new XMLHttpRequest();
//	}
//	else if(window.ActiveObject){
//		isIE = true;
//		req = new ActiveXObject("Microsoft.XMLHTTP");
//	}
//	req.onreadystatechange = deleteUpdatedDataRequest;
//	req.open("GET",url,true);
//	req.send(null);
//}
//function deleteUpdatedDataRequest(){
//	if(req.readyState == 4){
//		if(req.status == 200){
//		 	document.getElementById("editdietdiv").innerHTML = req.responseText;
//		}
//	}
//}

function selectSubDiet(id){
	 var url ="getSubDietDietarydetails?name="+id+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectSubDietRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectSubDietRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			   document.getElementById("subcat").innerHTML=req.responseText;
		}
	}
}



function selectSubCal(id){
	//var id1 = parseInt(id);
	 var url ="getSubCalorisDietarydetails?id="+id+"";
	 
	 
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectSubCalRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectSubCalRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var str = data.split("~");
			   document.getElementById("subcal").innerHTML= str[0];
			   document.getElementById("prooedit").innerHTML=str[1];
		}
	}
}

function updateDietaryInfo(){
	var addmissionid = document.getElementById("epatient_id").value;
	var parentid = document.getElementById("pid").value;
	
	var rowCount = $('#tabletrcount >tbody >tr').length;
	
	var url= "updateDietDietarydetails?addmissionid="+addmissionid+"&parentid="+parentid+"";
	if(addmissionid=='0'){
		jAlert('error', "please select Patient!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(rowCount=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = savedietaryRequest;
    req.open("GET", url, true); 
    req.send(null);
  }
}
 function savedietaryRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			location.reload();
		}
	}
}
var x;
function selectNewSubDiet(id,i){
	 x=i;
	 var url ="getNewSubDietDietarydetails?name="+id+"&i="+i+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectNewSubDietRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectNewSubDietRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			   document.getElementById("subcat"+x+"").innerHTML=req.responseText;
		}
	}
}

var y;
function selectNewSubCal(id,i){
	//var id1 = parseInt(id);
	 y=i;
	 var url ="getNewSubCalorisDietarydetails?id="+id+"&i="+i+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectNewSubCalRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectNewSubCalRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	
			var data = req.responseText;
			var str = data.split("~");
			   document.getElementById("subcal"+y+"").innerHTML=str[0];
			   document.getElementById("prooedit"+y+"").innerHTML = str[1];
		}
	}
}

function updateDietData(){
	var rowCount = document.getElementById("tempcount").value;
	if(rowCount==null){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{ 
		document.getElementById("updateForm").submit();
	}
}

function deleteUpdatedData(r){
		var i = parseInt(r)+1;
    	 //var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("tablecount").deleteRow(i);
    	
    	var total=Number(document.getElementById("tempcount").value);
    	total--; 
		document.getElementById("tempcount").value=total;	    	
}

function deleteDataFromDB(i,childid){
	var url = "deleteDataFromDBDietarydetails?i="+i+"&childid="+childid+"";
	var result = confirm("Are you sure (it's delete permantaly)?");
	if (result) {
    if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}
	else if(window.ActiveObject){
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = deleteDataFromDBRequest;
	req.open("GET",url,true);
	req.send(null);
  }
}
function deleteDataFromDBRequest(){
	if(req.readyState == 4){
		if(req.status == 200){
		 	var dd = req.responseText;
			var i = parseInt(dd)+1;
			//var i = dd.parentNode.parentNode.rowIndex;
			document.getElementById("tablecount").deleteRow(i);   	 	
			var total=Number(document.getElementById("tempcount").value);
    		total--; 
			document.getElementById("tempcount").value=total;
		}
	}
}

//Template Data

function saveTemplate(){

	var addmissionid = document.getElementById("patient_id").value;
	var templatename = document.getElementById("templatename").value;
	var rowCount = $('#tabletrcount >tbody >tr').length;
	
	var url= "storeDietTemplateDietarydetails?addmissionid="+addmissionid+"&templatename="+templatename+"";
	if(templatename==null || templatename==""){
		jAlert('error', "please Enter Template Name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(addmissionid=='0'){
		jAlert('error', "please select Patient!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(rowCount=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = saveTemplateRequest;
    req.open("GET", url, true); 
    req.send(null);
  }
}
 function saveTemplateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			
			var dd = req.responseText;
			if(dd!='0'){
			jAlert('success', 'Template Stored!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}
		}
	}
}


function selectedTemplate(parentid){
		var ipdid = document.getElementById("patient_id").value;
		var url = "getStoredTemplateDataDietarydetails?ipdid="+ipdid+"&parentid="+parentid+"";
 		if(window.XMLHttpRequest){
	 		req = new XMLHttpRequest();
 		}
 		else if(window.ActiveObject){
 			isIE = true;
 			req = new ActiveXObject("Microsoft.XMLHTTP");		
 		} 
 		req.onreadystatechange = selectedTemplateRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function selectedTemplateRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				var data = str.split("~");
				document.getElementById("diettable").innerHTML= data[0];
				document.getElementById("tcount").value= data[1];
				document.getElementById("addtd").innerHTML= data[2];
				document.getElementById("savebtndiv").innerHTML= data[3];
		}
	}
}

var a;
function selectTemplateSubDiet(id,i){
	 a=i;
	 var url ="getTemplateSubDietDietarydetails?name="+id+"&i="+i+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectTemplateSubDietRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectTemplateSubDietRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			   document.getElementById("subcat"+a+"").innerHTML=req.responseText;
		}
	}
}

var b;
function selectTemplateSubCal(id,i){
	 b=i;
	 var url ="getTemplateSubCalorisDietarydetails?id="+id+"&i="+i+"";
	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = selectTemplateSubCalRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function selectTemplateSubCalRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("subcal"+b+"").innerHTML = data[0];
			document.getElementById("tdproo"+b+"").innerHTML=data[1];
		}
	}
}
function deleteTemplateData(r){
		var i = parseInt(r)+1;
    	 //var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("tabletrcount").deleteRow(i);
    	
    	var total=Number(document.getElementById("tcount").value);
    	total--; 
		document.getElementById("tcount").value=total;	    	
}

var cell;
var row;
function addTemplateDiet(){
	var dietplan = document.getElementById("dietplan").value;
	var category = document.getElementById("category").value;
	var subcategory = document.getElementById("subcategory").value;
	var calories = document.getElementById("calories").value;
	var feed = document.getElementById("feed").value;
	var duration = document.getElementById("duration").value;
	
	var table = document.getElementById("tabletrcount");
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	
	var url = "addTemplateDietDataDietarydetails?dietplan="+dietplan+"&category="+category+"&subcategory="+subcategory+"&calories="+calories+"&feed="+feed+"&duration="+duration+"&counts="+counts+"";
	
	if(dietplan=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(category=='0'){
		jAlert('error', "please select Diet Category!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(subcategory=='0'){
		jAlert('error', "please select Diet!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(calories=='0'){
		jAlert('error', "please select Energy!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(feed=='0'){
		jAlert('error', "please select Feed!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	} else{
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
    	req.onreadystatechange = addTemplateDietRequest;
    	req.open("GET", url, true); 
    	req.send(null);
 	}
 }
 function addTemplateDietRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			    var dd=req.responseText;
			    row.innerHTML=dd;
		        var total=Number(document.getElementById("tcount").value);
		    	total++; 
				document.getElementById("tcount").value=total;
		}
	}
}

function saveNewDiet(){

	var addmissionid = document.getElementById("patient_id").value;
	var rowCount = $('#tabletrcount >tbody >tr').length;
	
	if(addmissionid=='0'){
		jAlert('error', "please select Patient!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(rowCount=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
		document.getElementById("saveForm").submit();
	}
}
 
function saveUpdatedTemplate(){

	var addmissionid = document.getElementById("patient_id").value;
	var templatename = document.getElementById("templatename").value;
	var rowCount = $('#tabletrcount >tbody >tr').length;
	
	var url= "storeUpdatedTemplateDietarydetails?addmissionid="+addmissionid+"&templatename="+templatename+"";
	if(templatename==null || templatename==""){
		jAlert('error', "please Enter Template Name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(addmissionid=='0'){
		jAlert('error', "please select Patient!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(rowCount=='0'){
		jAlert('error', "please select Diet Plan!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = saveUpdatedTemplateRequest;
    req.open("GET", url, true); 
    req.send(null);
  }
}
 function saveUpdatedTemplateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			
			var dd = req.responseText;
			if(dd!='0'){
			jAlert('success', 'Template Stored!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}
		}
	}
}
 
/* Adarsh*/
function addTemplate(){
	document.getElementById("savebtndiv").innerHTML="<button type='button' class='btn btn-primary' onclick='saveTemplate()'>Save Template</button> <button type='button' class='btn btn-primary' onclick='savedietaryInfo()'>Save</button>"
		document.getElementById("tempdiv").innerHTML="<input type='text' id='templatename' name='templatename' placeholder= 'Template Name'>";
}
		
/*function demo(){
	 
	   var f =document.getElementById("ad4");
	   
	   f.action="savegeneraldietplanDietarydetails";
	   f.submit();
	  
}  */
function saveGeneralDiet(){
	 var diettimeshift =document.getElementById("diettimeshift").value;
	
	var url= "savediettimehiftDietarydetails?diettimeshift="+diettimeshift+"";
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}  
	req.onreadystatechange = saveGeneralDietRequest;
    req.open("GET", url, true); 
    req.send(null);

}
 function saveGeneralDietRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			
			var diettimeshift1 = req.responseText;
			if(diettimeshift1=='1'){
				jAlert('error', "Already Added Diet Plan!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);			 		
			}
			
		else{
			
			
			$(".myclass").each(function(){
				var selected="0";
				 var tid= this.value;
				
				$(".fcase"+tid).each(function(){
						  
						  if(this.checked==true){
							  selected=selected+","+this.value;
						  }
					  
				});
				document.getElementById("diaetplan"+tid).value=selected;
				
			});
			
			
			document.getElementById("ad4").submit();
		}  
			
		}
	}
}
function updateDietPlan(){
	
	$(".myclass").each(function(){
		var selected="0";
		 var tid= this.value;
		
		$(".fcase"+tid).each(function(){
				  
				  if(this.checked==true){
					  selected=selected+","+this.value;
				  }
			  
		});
		document.getElementById("diaetplan"+tid).value=selected;
		
	});
	
	
	document.getElementById("ad4").submit();
  
	}
function dofilter(){
	var x = document.getElementById("all")
	var selected=0;
	
	$(".pacss").each(function(){
		  
		  if(this.checked==true){
			  selected=selected+","+this.value;
		  }
	  
  });
	
	document.getElementById("allward").value=selected;
	document.getElementById("printform").submit();
	
	wardfilter
}
function wardfilter(){
	
	var selected=0;
	
	$(".pacss").each(function(){
		  
		  if(this.checked==true){
			  selected=selected+","+this.value;
		  }
	  
  });
	
	document.getElementById("allward1").value=selected;
	document.getElementById("addform").submit();
	
	
}
