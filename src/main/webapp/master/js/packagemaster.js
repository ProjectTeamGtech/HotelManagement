var cell;
var row;
function addcharge(){
	var package_amount = document.getElementById("package_amount").value;
	var charge = document.getElementById("charge").value;
	var packageA = document.getElementById("packageA").checked;
	if(packageA==true){
		if(package_amount==''){
			jAlert('error', "please Enter Package Amount!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);			 		
		}else if(charge=='0'){
			jAlert('error', "please Select Charge!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);			 		
		}else{
			var table = document.getElementById("tablecount");
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
			var counts = rowCount;	
			cell=row.insertCell(0);
		
			var chargeid = document.getElementById("charge").value;
			var chargetype = document.getElementById("chargetype").value;
			var url ="addChargeTempPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"&packagetype=packageA";
		  	if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	    	req.onreadystatechange = addchargeRequest;
	    	req.open("GET", url, true); 
	    	req.send(null);
	 	}
	}else{
		if(charge=='0'){
			jAlert('error', "please Select Charge!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);			 		
		}else{
			var table = document.getElementById("tablecount");
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
			var counts = rowCount;	
			cell=row.insertCell(0);
		
			var chargeid = document.getElementById("charge").value;
			var chargetype = document.getElementById("chargetype").value;
			var url ="addChargeTempPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"&packagetype=packageB";
		  	if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	    	req.onreadystatechange = addchargeRequest;
	    	req.open("GET", url, true); 
	    	req.send(null);
	 	}
	}
	
}
 function addchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			  var dd=req.responseText;
			    row.innerHTML=dd;
			    var total=Number(document.getElementById("tempcount").value);
		    	total++; 
				document.getElementById("tempcount").value=total;
		}
	}
}

var rowtp;
var celltp;
var cl;
var chrg;
 function addchargetp(){
	 var clientid=document.getElementById("clientid").value;
	 cl=clientid;
	 var blank=document.getElementById("chargetp").value;
	 if(blank=='0'){
		 alert("Charge cant be blank ");
		 return;
	 }
	 var table = document.getElementById("tablecounttp");
		var rowCount = table.rows.length;
		rowtp=table.insertRow(rowCount);
		var counts = rowCount;	
		celltp=rowtp.insertCell(0)
		var chargeid= document.getElementById("chargetp").value;
		var chargetype=document.getElementById("chargetype_tp").value;
		chrg=chargetype;
		var url ="addChargeTempTPPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"&packagetype=packageB&clientid="+clientid;
	  	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
    	req.onreadystatechange = addchargetpRequest;
    	req.open("GET", url, true); 
    	req.send(null);
 	

 } 
 
 function addchargetpRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {	  
				  var dd=req.responseText;
				    rowtp.innerHTML=dd;
				    var total=Number(document.getElementById("tempcounttp").value);
			    	total++; 
					document.getElementById("tempcounttp").value=total;
					if(chrg=='2'&&cl!=''){
						var newtp= 0;
						  $(".amounttp").each(function(){
							  newtp= Number(this.value)+Number(newtp);
						  });
						  document.getElementById("package_amount_tp").value=newtp;
					
					}
			}
		}
 }
 
function deleteTPchargenew(r){
	var i = parseInt(r);
	 //var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("tablecounttp").deleteRow(i);
	var total=Number(document.getElementById("tempcounttp").value);
	total--; 
	document.getElementById("tempcounttp").value=total;	 
	
}


function changeTotalAmtTP(val){
	var tptotal= 0;
	  $(".amounttp").each(function(){
		  tptotal= Number(this.value)+Number(tptotal);
	  });
	  document.getElementById("package_amount_tp").value=tptotal;
	  document.getElementById("package_amount_self").value=tptotal;
}

function saveTpPackage(){
	var pkgname=document.getElementById("package_name_tp").value;
	var amt= document.getElementById("package_amount_tp").value;
	if(pkgname==''){
		alert("Enter Proper package");
		return;
	}
	if(amt=='0'){
		alert("Enter Ammount");
		return;
	}
	var dattta ='0';
	$('.lokeshinv').each(function() { 
		dattta = dattta+','+this.value;
	});
	 document.getElementById("inveschargeidtp").value=dattta;
	
}


function deletePackageTemp(r){
		var i = parseInt(r)+1;
    	 //var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("tablecount").deleteRow(i);
    	var total=Number(document.getElementById("tempcount").value);
    	total--; 
		document.getElementById("tempcount").value=total;	 
}

function calculateamount(per,i){
	var package_amount = document.getElementById("package_amount").value;
	var cal = (per * package_amount)/100;
	document.getElementById("cal_amount"+i+"").value = cal;
}

//function savePackage(){

	//var package_name = document.getElementById("package_name").value;
	//var package_amount = document.getElementById("package_amount").value;
	
	//var url= "savePackageMaster?package_name="+package_name+"&package_amount="+package_amount+"";
	//if(package_name==null || package_name==""){
	//	jAlert('error', "please Enter Package Name!", 'Error Dialog');	
	//		setTimeout(function() {
	//			$("#popup_container").remove();
	//				removeAlertCss();
	//			}, alertmsgduration);			 		
	//}else {
	//if (window.XMLHttpRequest) {
	//	req = new XMLHttpRequest();
	//}
	//else if (window.ActiveXObject) {
	//	isIE = true;
	//	req = new ActiveXObject("Microsoft.XMLHTTP");
	//}   
	//req.onreadystatechange = savePackageRequest;
    //req.open("GET", url, true); 
    //req.send(null);
  //}
//}
 //function savePackageRequest(){
//	if (req.readyState == 4) {
//		if (req.status == 200) {	  
//			
//			var dd = req.responseText;
//			if(dd!='0'){
//			jAlert('success', 'Template Stored!', 'Success Dialog');
//					
//					setTimeout(function() {
//						$("#popup_container").remove();
//						removeAlertCss();
//					}, alertmsgduration);
//			}
//			$('#pacakgesp').modal( "hide" );
//		}
//	}
//}

function savePackage(){
	var total =0;
	var data ='0';
	var packageA = document.getElementById("packageA").checked;
	if(packageA==true){
		$('.akash').each(function() { 
			var percentage = Number(document.getElementById("percentage"+this.value).value);
			if(percentage==''){
				data='1';
			}
			total = total +percentage;
		});
		if(data=='1'){
			jAlert('error', "Plz enter % value!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(total>100){
			jAlert('error', "Total % greater than 100%!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(total<100){
			jAlert('error', "Total % less than 100%!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(total==100){
			validatesavepackage();
		}
	}else{
		$('.akash').each(function() { 
			var cal_amount = Number(document.getElementById("cal_amount"+this.value).value);
			if(cal_amount==''){
				data='1';
			}
			total = total +cal_amount;
		});
		if(data=='1'){
			jAlert('error', "Plz enter Rs value!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			document.getElementById("package_amount").value = total;
			$('.akash').each(function() { 
				var cal_amount = Number(document.getElementById("cal_amount"+this.value).value);
				var cal = (cal_amount / total)*100;
				document.getElementById("percentage"+this.value).value = roundTwo(cal);
			});
			validatesavepackage();
		}
	}
}

function validatesavepackage(){
	var package_name = document.getElementById("package_name").value;
	var package_amount = document.getElementById("package_amount").value;
	var validname = document.getElementById("validname").value;
	/*if(validname!='0'){
		jAlert('error', "Entered package name already present!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else */if(package_name==null || package_name==""){
		jAlert('error', "please Enter Package Name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else {
		var dattta ='0';
		$('.akash22').each(function() { 
			dattta = dattta+','+this.value;
		});
		document.getElementById("invechargeid").value = dattta;
		document.getElementById("savepackageform").submit();	
	}
}



function editPackage(id){
 	var url = "editPackageMaster?id="+id+"";
 	if(window.XMLHttpRequest){
	 	req = new XMLHttpRequest();
 	}
 	else if(window.ActiveObject){
 		isIE = true;
 		req = new ActiveXObject("Microsoft.XMLHTTP");		
 	} 
 		req.onreadystatechange = editPackageRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function editPackageRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				var data = str.split("#");
				document.getElementById("parentid").value = data[0];
				document.getElementById("editpackage_name").value = data[1];
				document.getElementById("editpackage_amount").value = data[2];
				document.getElementById("edittbodyid").innerHTML = data[3];
				document.getElementById("edittempcount").value = data[4];
				document.getElementById("editpkgtype").innerHTML = data[5];
				document.getElementById("editchargelistid").innerHTML = data[6];
				
				 $("#editcharge").trigger("chosen:updated");
			  		
				 $(".chosen-select").chosen({allow_single_deselect: true});
				   

				$('#editpacakge').modal( "show" );
		}
	}	
}

function updatePackage(){
	var package_name = document.getElementById("editpackage_name").value;
	var package_amount = document.getElementById("editpackage_amount").value;
	var total = 0;
	var data ='0';
	$('.editakash').each(function() { 
		var percentage = Number(document.getElementById("editpercentage"+this.value).value);
		if(percentage==''){
			data='1';
		}
		total = total +percentage;
	});
	if(data=='1'){
		jAlert('error', "Plz enter % value!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(total>100){
		jAlert('error', "Total % greater than 100%!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(total<100){
		jAlert('error', "Total % less than 100%!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(package_name==null || package_name==""){
		jAlert('error', "please Enter Package Name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else if(package_amount==null || package_amount==""){
		jAlert('error', "please Enter Package Ammount!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);			 		
	}else{
		var dattta ='0';
		$('.editakash22').each(function() { 
			dattta = dattta+','+this.value;
		});
		document.getElementById("editinvechargeid").value = dattta;
		document.getElementById("updatepackageform").submit();	
	}
}

function saveTPpkg(){
	var pkgname=document.getElementById("package_name_tp").value;
	var amt= document.getElementById("package_amount_tp").value;
	
	if(pkgname==''){
		alert("Enter Proper package");
		return;
	}
	if(amt=='0'){
		alert("Enter Ammount");
		return;
	}
	var dattta ='0';
	$('.lokeshinv').each(function() { 
		dattta = dattta+','+this.value;
	});
	 document.getElementById("inveschargeidtp").value=dattta;
	
	document.getElementById("savepackageformtp").submit();	
}
function calculateamountedit(per,i){
	var package_amount = document.getElementById("editpackage_amount").value;
	var cal = (per * package_amount)/100;
	document.getElementById("editcal_amount"+i+"").value = cal;
}

function checkPackageName(val) {
	var name = val;
	if(val==''){
		jAlert('error', "Please enter package name!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);		
	}else{
		var url = "checkpackagenamePackageMaster?name="+val+"";
	 	if(window.XMLHttpRequest){
		 	req = new XMLHttpRequest();
	 	}
	 	else if(window.ActiveObject){
	 		isIE = true;
	 		req = new ActiveXObject("Microsoft.XMLHTTP");		
	 	} 
	 		req.onreadystatechange = checkPackageNameRequest;
	 		req.open("GET",url,true);
	 		req.send(null);
	}
}


function checkPackageNameRequest(){
	if(req.readyState == 4){
		if(req.status == 200){
			var str = req.responseText;
			if(str!='0'){
				document.getElementById("package_name").value='';
				jAlert('error', "Entered package name already present!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			}
			
			document.getElementById("validname").value = str;
	}
}
}


function setChargeNameList(val){
 	var url = "getchargenametypelistPackageMaster?val="+val+"";
 	
 	if(window.XMLHttpRequest){
	 	req = new XMLHttpRequest();
 	}
 	else if(window.ActiveObject){
 		isIE = true;
 		req = new ActiveXObject("Microsoft.XMLHTTP");		
 	} 
 		req.onreadystatechange = setChargeNameListRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function setChargeNameListRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				document.getElementById("chargetypediv").innerHTML = str;
				 $("#charge").trigger("chosen:updated");
				  $(".chosen-select").chosen({allow_single_deselect: true});
				  
				  document.getElementById("addpackagetbody").innerHTML ='';
				  document.getElementById("addpackagetbody2").innerHTML ='';
				 	document.getElementById("tempcount").value ='0';
		}
	}
}

function setChargeNameListTP(val){
	var clientid=document.getElementById("clientid").value;
 	var url = "getchargenametypelistPackageMaster?val="+val+"&type=1&clientid="+clientid;
 	
 	if(window.XMLHttpRequest){
	 	req = new XMLHttpRequest();
 	}
 	else if(window.ActiveObject){
 		isIE = true;
 		req = new ActiveXObject("Microsoft.XMLHTTP");		
 	} 
 		req.onreadystatechange = setChargeNameListTPRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function setChargeNameListTPRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				document.getElementById("chargetypedivtp").innerHTML = str;
				 $("#charge").trigger("chosen:updated");
				  $(".chosen-select").chosen({allow_single_deselect: true});
				  
				 
					  
				 	/*document.getElementById("tempcount").value ='0';*/
		}
	}
}




function resetAllPackageData(val){
	if(val=='packageA'){
		document.getElementById("package_name").value ='';
		document.getElementById("package_amount").value ='';
		document.getElementById("package_amount").readOnly = false;
		document.getElementById("addpackagetbody").innerHTML ='';
		 document.getElementById("addpackagetbody2").innerHTML ='';
		document.getElementById("tempcount").value ='0';
		document.getElementById("validname").value="0";
		document.getElementById("packageB").checked=false;
		document.getElementById("packagetype").value="0";
		document.getElementById("savebtnid").innerHTML ='<button type="button" class="btn btn-primary" onclick="savePackage()" style="margin-top: 15px;">Save</button>';
	}else{
		document.getElementById("package_name").value ='';
		document.getElementById("package_amount").value ='';
		document.getElementById("package_amount").readOnly = true;
		document.getElementById("addpackagetbody").innerHTML ='';
		 document.getElementById("addpackagetbody2").innerHTML ='';
		document.getElementById("tempcount").value ='0';
		document.getElementById("validname").value="0";
		document.getElementById("packageA").checked=false;
		document.getElementById("packagetype").value="1";
		document.getElementById("savebtnid").innerHTML ='<button type="button" class="btn btn-primary" onclick="CalculatePackage()" style="margin-top: 15px;">Calculate</button> <button type="button" class="btn btn-primary" onclick="savePackage()" style="margin-top: 15px;">Save</button>';
	}
}

function calculateamountpackageB(per,i){
	var package_amount = document.getElementById("cal_amount0").value;
	var cal = (per * package_amount)/100;
	document.getElementById("cal_amount"+i+"").value = cal;
}

function roundTwo(val){
	 
	   val =Math.round(val * 100) / 100;
	   //val=Math.floor(val);
	   
	   return val;
	}


function CalculatePackage(){
	var total =0;
	data="";
	$('.akash').each(function() { 
		var cal_amount = Number(document.getElementById("cal_amount"+this.value).value);
		if(cal_amount==''){
			data='1';
		}
		total = total +cal_amount;
	});
	if(data=='1'){
		jAlert('error', "Plz enter Rs value!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		document.getElementById("package_amount").value = total;
		$('.akash').each(function() { 
			var cal_amount = Number(document.getElementById("cal_amount"+this.value).value);
			var cal = (cal_amount / total)*100;
			document.getElementById("percentage"+this.value).value = roundTwo(cal);
		});
}
}


function deleteEditPackage(x,r){
	var i = parseInt(r)+1;
	
	
	 //var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("edittableidd").deleteRow(i);
	
/*	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = deleteEditPackageRequest;
	req.open("GET", url, true); 
	req.send(null);*/
	/*var total=Number(document.getElementById("edittempcount").value);
	total--; 
	document.getElementById("edittempcount").value=total;*/	 
	
	
	var url ="deletechildpkgajaxPackageMaster?childid="+x+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deleteEditPackageRequest;
    req.open("GET", url, true); 
              
    req.send(null);   
}

function deleteEditPackageRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			  var dd=req.responseText;
			    
		}
	}
}
var cell;
var row;
var globeltotal=0;
function editchargeadd(){
	var package_amount = document.getElementById("package_amount").value;
	var charge = document.getElementById("charge").value;
	var table = document.getElementById("edittableidd");
	var total=Number(document.getElementById("edittempcount").value);
	var counts = total+1;
	
	
	document.getElementById("edittempcount").value=counts;
	row=table.insertRow(counts);
	cell=row.insertCell(0);
	/*var total=Number(document.getElementById("edittempcount").value);
	var counts = total++;*/
	var chargeid = document.getElementById("editcharge").value;
	if(chargeid=='0'){
		
	}else{
		var chargetype = document.getElementById("editchargetype").value;
		var url ="editchargeaddPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"";
	  	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = editchargeaddRequest;
		req.open("GET", url, true); 
		req.send(null);
	}
	
}
 function editchargeaddRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			  var dd=req.responseText;
			    row.innerHTML=dd;
		}
	}
}

 
function editTpPkg(id){
	
	var url = "editTpPackageMaster?id="+id+"";
 	if(window.XMLHttpRequest){
	 	req = new XMLHttpRequest();
 	}
 	else if(window.ActiveObject){
 		isIE = true;
 		req = new ActiveXObject("Microsoft.XMLHTTP");		
 	} 
 		req.onreadystatechange = editTpPkgRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function editTpPkgRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				var data = str.split("#");
				document.getElementById("edittpparentid").value = data[0];
				document.getElementById("package_name_tp_edit").value = data[1];
				document.getElementById("package_amount_tp_edit").value = data[2];
				document.getElementById("table_edit_tp").innerHTML = data[3];
				document.getElementById("edittpcount").value = data[4];
				
				
			/*	 $("#editcharge").trigger("chosen:updated");
			  		
				 $(".chosen-select").chosen({allow_single_deselect: true});
				   
*/
				$('#tppackage_edit').modal( "show" );
		}
	}	
}

function chngeEditAmt(obj){
	var classname= obj.className;
	var tptotal= 0;
	  $(".lokesheditpkg").each(function(){
		  tptotal= Number(this.value)+Number(tptotal);
	  });
	  document.getElementById("package_amount_tp_edit").value=tptotal;
	
}

var ct;
var cl1;
var chrg1;
function addTotempedit(){
	var count=Number(document.getElementById("edittpcount").value);
	var chargeid;
	if(document.getElementById("chargetp_edit")){
		chargeid=document.getElementById("chargetp_edit").value;	
	}else{
		chargeid=document.getElementById("chargetp").value;
	}
	
	var chargetype=document.getElementById("chargetype_tp_edit").value;
	var table = document.getElementById("tablecounttp_edit");
var counts = count;
	ct=count;
	
	document.getElementById("edittpcount").value=counts;
	row=table.insertRow(counts);
	cell=row.insertCell(0);
	var clientid=document.getElementById("clientid").value;
	cl1=clientid;
	chrg1=chargetype;
	var url ="addTempEditTpPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"&clientid="+clientid;
  	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = addTotempeditRequest;
	req.open("GET", url, true); 
	req.send(null);


}

function addTotempeditRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	 
			document.getElementById("edittpcount").value=ct+1;
			  var dd=req.responseText;
			    row.innerHTML=dd;
			    if(cl1!=''&&chrg1=='2'){
					var newtp= 0;
					  $(".lokesheditpkg").each(function(){
						  newtp= Number(this.value)+Number(newtp);
					  });
					  document.getElementById("package_amount_tp_edit").value=newtp;
				
				}
				
		}
	}
}


function saveTPpkg_edit(){
	cl1=document.getElementById("clientid").value;
	chrg1=document.getElementById("chargetype_tp_edit").value;
	var pkgname=document.getElementById("package_name_tp_edit").value;
	var amt= document.getElementById("package_amount_tp_edit").value;
	if(pkgname==''){
		alert("Enter Proper package");
		return;
	}
	if(amt=='0'){
		alert("Enter Ammount");
		return;
	}
	var dattta ='0';
	$('.lokeshtpedit').each(function() { 
		dattta = dattta+','+this.value;
	});
	 document.getElementById("inveschargeidtp_edit").value=dattta;
	
	document.getElementById("updatepackageformtp").submit();	
	
}



function setChargeNameListTPEdit(val){
	var clientid=document.getElementById("clientid").value;
 	var url = "getchargenametypelisteditPackageMaster?val="+val+"&type=1&clientid="+clientid;
 	
 	if(window.XMLHttpRequest){
	 	req = new XMLHttpRequest();
 	}
 	else if(window.ActiveObject){
 		isIE = true;
 		req = new ActiveXObject("Microsoft.XMLHTTP");		
 	} 
 		req.onreadystatechange = setChargeNameListTPEditRequest;
 		req.open("GET",url,true);
 		req.send(null);
	}
function setChargeNameListTPEditRequest(){
		if(req.readyState == 4){
			if(req.status == 200){
				var str = req.responseText;
				
				  
				  document.getElementById("chargetypediv1").innerHTML = str;
					 $("#chargetp_edit").trigger("chosen:updated");
					  $(".chosen-select").chosen({allow_single_deselect: true});
					  
				 	/*document.getElementById("tempcount").value ='0';*/
		}
	}
}
var newchargetype = "";
function filterCharges(chargetype){
	chargetype=document.getElementById("masterchargetype").options[document.getElementById('masterchargetype').selectedIndex].text;
	newchargetype = chargetype;
    var tpid=0;
    var ipdwhopay='Client';
    var ipdaddmissionid=0;
    var clientId = document.getElementById("clientid").value;
  	//var url = "chargeIpd?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
  	//  16 May 2018
    var url = "selfchargeBookAppointmentAjax?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = filterChargesRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
}

function filterChargesRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data= req.responseText;
			var data1= data.split("!@");
			 //lokesh 22/11/2018
			if(document.getElementById("compulsaryconsultant")){
				 document.getElementById("compulsaryconsultant").value =data1[1];
			}
			
			
			 document.getElementById("additionalChargeAjax").innerHTML = data1[0];
			  $("#chargeTYpe").trigger("chosen:updated");
		 	 $(".chosen").chosen({allow_single_deselect: true});
		 	 if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge'||data1[1]=='1'){
		 		//akash 20 July 2018
		 		 //lokesh on the req of praful sir 29/11/2018
		 		 setvisitingdrlist();
		 	 }else{
		 		document.getElementById("visitingconsltantdiv").innerHTML = "<input type='hidden' id='consultantdr' value='0'>";
		 	 }
		 	 //akash 20 July 2018
		 	
		}
	}
}
var rowtp;
var celltp;
var cl;
var chrg;
 function addchargeself(){
	 var clientid=document.getElementById("clientid").value;
	 cl=clientid;
	 var blank=document.getElementById("chargeTYpe").value;
	 if(blank=='0'){
		 alert("Charge cant be blank ");
		 return;
	 }
	 var table = document.getElementById("tablecountself");
		var rowCount = table.rows.length;
		rowtp=table.insertRow(rowCount);
		var counts = rowCount;	
		celltp=rowtp.insertCell(0)
		var chargeid= document.getElementById("chargeTYpe").value;
		var chargetype=document.getElementById("masterchargetype").value;
		chrg=chargetype;
		var url ="addChargeTempSelfPackageMaster?chargeid="+chargeid+"&counts="+counts+"&chargetype="+chargetype+"&packagetype=packageB&clientid="+clientid;
	  	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
    	req.onreadystatechange = addchargeselfRequest;
    	req.open("GET", url, true); 
    	req.send(null);
 	

 } 
 
 function addchargeselfRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {	  
				  var dd=req.responseText;
				    rowtp.innerHTML=dd;
				    var total=Number(document.getElementById("tempcounttp").value);
			    	total++; 
					document.getElementById("tempcount").value=total;
					
						var newtp= 0;
						  $(".amounttp").each(function(){
							  newtp= Number(this.value)+Number(newtp);
						  });
						  document.getElementById("package_amount_self").value=newtp;
					
					
			}
		}
 }
 function saveSelfpkg(){
		var pkgname=document.getElementById("package_name_self").value;
		var amt= document.getElementById("package_amount_self").value;
		
		if(pkgname==''){
			alert("Enter Proper package");
			return;
		}
		else if(amt=='0'){
			alert("Enter Ammount");
			return;
		}else{
			document.getElementById("selfpkg").disabled=true;
		var dattta ='0';
		$('.lokeshinv').each(function() { 
			dattta = dattta+','+this.value;
		});
		 document.getElementById("inveschargeidtp").value=dattta;
		
		document.getElementById("savepackageformtp1").submit();	
 }
	}