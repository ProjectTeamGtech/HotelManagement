var protine = 0;
var creatine = 0;
var num = 0;
function showupr(r,id,val,finalid){
//alert(r + '/' + id + '/' + val);

if(r==1){
protine = val;
}
if(r==2){
creatine = val;
}
num = parseFloat(protine)/parseFloat(creatine);
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number


document.getElementById("obs"+finalid).value = num;

}

var totalchol = 0;
var hdlchol = 0;
var trig = 0;
function showsnhlipidprofile(r,id,val,finalid){
 totalchol = document.getElementById("obs"+(finalid)).value;
 trig = document.getElementById("obs"+(finalid+1)).value;
 hdlchol = document.getElementById("obs"+(finalid+2)).value;
 
 
 var vldl = parseFloat(trig)/5;
 
 num = vldl;
num = num.toString(); //If it's not already a String
//num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+4)).value = num.toFixed(2);
	
	var hdlvldl = parseFloat(hdlchol) + parseFloat(vldl);
	var ldl = parseFloat(totalchol) - parseFloat(hdlvldl);
	
	num = ldl;
num = num.toString(); //If it's not already a String
//num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
var x=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+3)).value = x.toFixed(2);
}

function showlipidprofile(r,id,val,finalid){

	totalchol = document.getElementById("obs"+(finalid)).value;
	hdlchol = document.getElementById("obs"+(finalid+1)).value;
	
	trig = document.getElementById("obs"+(finalid+2)).value;
	
	//document.getElementById("obs"+(finalid+2)).value = trig;
	
	var tc = parseFloat(trig)/5;
	
	var ldl = parseFloat(totalchol) - parseFloat(hdlchol) - parseFloat(tc);
	
	num = ldl;
num = num.toString(); //If it's not already a String
//num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+3)).value = num.toFixed(2);
	
	var vldl = parseFloat(totalchol) - parseFloat(hdlchol) - parseFloat(ldl)
	
num = vldl;
num = num.toString(); //If it's not already a String
//num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+4)).value = num.toFixed(2);
	
	var totalhdlchol = parseFloat(hdlchol) /  parseFloat(totalchol);
	
num = totalhdlchol;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+6)).value = num.toFixed(2);
	
	var ldlorhdl = parseFloat(ldl) / parseFloat(hdlchol); 
	
	num = ldlorhdl;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+7)).value = num.toFixed(2);
	
	
}



//liver function 

function showsnhliverfunction(r,id,val,finalid){
var totalbilrubin = document.getElementById("obs"+(finalid)).value;
var bildirect = document.getElementById("obs"+(finalid+1)).value;

var bilindirect = parseFloat(totalbilrubin) - parseFloat(bildirect);

	num = bilindirect;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
num=Number(num); //If you need it back as a Number


document.getElementById("obs"+(finalid+2)).value = num;

var totalprotiens = document.getElementById("obs"+(finalid+6)).value;
var seralubmin = document.getElementById("obs"+(finalid+7)).value;

var serglubmin = parseFloat(totalprotiens) - parseFloat(seralubmin);

	num = serglubmin;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+8)).value = num;

var agratioo = parseFloat(seralubmin) / parseFloat(serglubmin)

num = agratioo;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+9)).value = num;

}

function showliverfunction(r,id,val,finalid){
var totalbilrubin = document.getElementById("obs"+(finalid)).value;
var bildirect = document.getElementById("obs"+(finalid+1)).value;

var bilindirect = parseFloat(totalbilrubin) - parseFloat(bildirect);

	num = bilindirect;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number


document.getElementById("obs"+(finalid+2)).value = num;

var totalprotiens = document.getElementById("obs"+(finalid+3)).value;

var seralubmin = document.getElementById("obs"+(finalid+4)).value;

var serglubmin = parseFloat(totalprotiens) - parseFloat(seralubmin);

	num = serglubmin;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+5)).value = num;


var agratioo = parseFloat(seralubmin) / parseFloat(serglubmin)

num = agratioo;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+6)).value = num;

}

function showliverfunctionlft(r,id,val,finalid){
	var totalbilirubin = document.getElementById("obs"+(finalid)).value;
	var directbilirubin = document.getElementById("obs"+(finalid+1)).value;
	
	var indirectbilirubin = parseFloat(totalbilirubin) - parseFloat(directbilirubin);
	
	indirectbilirubin = indirectbilirubin.toFixed(2)
	
	num = indirectbilirubin;
	num = num.toString(); //If it's not already a String
	num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	Number(num); //If you need it back as a Number

	document.getElementById("obs"+(finalid+2)).value = num;
	
	
	var totalprotiens = document.getElementById("obs"+(finalid+6)).value;
var seralubmin = document.getElementById("obs"+(finalid+7)).value;

var serglubmin = parseFloat(totalprotiens) - parseFloat(seralubmin);

serglubmin = serglubmin.toFixed(3);

	num = serglubmin;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+8)).value = num;

var agratioo = parseFloat(seralubmin) / parseFloat(serglubmin)

num = agratioo;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+9)).value = num;
	
}


function showtotalprotein(r,id,val,finalid){
	var totalprotein = document.getElementById("obs"+(finalid)).value;
	var albumin = document.getElementById("obs"+(finalid+1)).value;
	
	var serglubmin = parseFloat(totalprotein) - parseFloat(albumin);
	
	num = serglubmin;
	num = num.toString(); //If it's not already a String
	num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	num=Number(num); //If you need it back as a Number

	document.getElementById("obs"+(finalid+2)).value = num.toFixed(2);
	
	var agratioo = parseFloat(albumin) / parseFloat(serglubmin)

	num = agratioo;
	num = num.toString(); //If it's not already a String
	num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	num=Number(num); //If you need it back as a Number

	document.getElementById("obs"+(finalid+3)).value = num.toFixed(2);
}

function showsnhprothombin(r,id,val,finalid){
/*var ptest = document.getElementById("obs"+(finalid)).value;
var control = document.getElementById("obs"+(finalid+1)).value;

var inr = parseFloat(ptest) / parseFloat(control);

	num = inr;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

num = Math.pow(num, 1.1);
num =num.toFixed(2);
document.getElementById("obs"+(finalid+2)).value = num;*/
}

function showprothombin(r,id,val,finalid){
var ptest = document.getElementById("obs"+(finalid)).value;
var control = document.getElementById("obs"+(finalid+1)).value;

var inr = parseFloat(ptest) / parseFloat(control);

	num = inr;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number

document.getElementById("obs"+(finalid+2)).value = num;
}

function showcalciumcreatin(r,id,val,finalid){
num = 0;
	var ucalcium = document.getElementById("obs"+(finalid)).value;
	var ucreatine = document.getElementById("obs"+(finalid+1)).value;
	
	var result = parseFloat(ucalcium) / parseFloat(ucreatine);
	
num = result;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+2)).value = num;
}

function showirondata(r,id,val,finalid){
num = 0;
	var sriron = document.getElementById("obs"+(finalid)).value;
	var ferritin = document.getElementById("obs"+(finalid+1)).value;
	var transfersar = document.getElementById("obs"+(finalid+2)).value;
	var tibc = document.getElementById("obs"+(finalid+3)).value;
	
	var result = parseFloat(sriron) / parseFloat(tibc);
	result=result*100; 
num = result;
num = num.toString(); //If it's not already a String
num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+2)).value = num;
}

function showupcrndata(r,id,val,finalid){
num = 0;
	var protein = document.getElementById("obs"+(finalid)).value;
	var creatine = document.getElementById("obs"+(finalid+1)).value;
	
	var result = parseFloat(protein) / parseFloat(creatine);
	num = result;
	num = num.toString(); //If it's not already a String
	num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+2)).value = num;
}

function showcalcration(r,id,val,finalid){
num = 0;
	var calcium = document.getElementById("obs"+(finalid)).value;
	var creatine = document.getElementById("obs"+(finalid+1)).value;
	
	var result = parseFloat(calcium) / parseFloat(creatine);
	num = result;
	num = num.toString(); //If it's not already a String
	num = num.slice(0, (num.indexOf("."))+3); //With 3 exposing the hundredths place
	Number(num); //If you need it back as a Number
	
	document.getElementById("obs"+(finalid+2)).value = num;
}


function refreshhere() {
   
   document.refreshform.submit();
}

function cnfmdelete() {

      var t=confirm("Are you sure you want to delete?");
      
      if(t==true){
         return true;
      } else {
         return false;
      }
}


function setInstantCashDesk(invoiceid){
	
	
	
	//checkAppointmentInvoicedForCashDesk();
	
	document.getElementById('cashClientid').value = ipdclientid;
	document.getElementById('cashclientname').value = ipdclientname;
	document.getElementById('cashPayby').value = invstwhopay;
	document.getElementById('cashLocationid').value = 1;
	document.getElementById('cashAppointmentid').value = 0;
	document.getElementById('cashinvoiceid').value = invoiceid;
	
	
		
		document.getElementById('cashdeskfrm1').submit();
	
	
	
	
	


	
}




function cancelIvestigation(val){
	document.getElementById("pro_id").value = val;
	$('#investigationcancelmodel').modal( "show" );
}

function cancelIvestigation1(id,invid,abrid,name,invtype){
	document.getElementById("pro_id").value = id;
	document.getElementById("cancelsnhid").innerHTML = abrid;
	document.getElementById("cancelpatientname").innerHTML = name;
	document.getElementById("cancelinvestitype").innerHTML = invtype;
	$('#investigationcancelmodel').modal( "show" );
}


//investigation package

function showInvtigationPackageList(){
	var url = "pkglistInvestigation";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInvtigationPackageListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function showInvtigationPackageListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('patientpkgspan').innerHTML = req.responseText;
		}
	}
}


function addpackageoncreatecharge(id){

	var url = "pkgdtailsIpdDashboard?id="+id+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addpackageoncreatechargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function addpackageoncreatechargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
		     var data=str.split("~");
	         
		
			document.getElementById("quantity").value = 1;
			document.getElementById("hdncharge").value = data[0];
			document.getElementById("charge").value = data[0];
			document.getElementById("packageid").value=data[1];
			 document.getElementById("mannualcharge").value = $("#ipdpackage option:selected").text();
			 
			 calcmanualcharge();
		}
	}
}

function saveOutsourceInvestigation(val,invid) {
	var url = "saveinvestoutsourceBookAppointmentAjax?invid="+invid+"&val="+val+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveOutsourceInvestigationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function saveOutsourceInvestigationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 jAlert('success', "Investigation outsource updated successfully!", 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				window.location.reload();
		}
	}
}

function openOutSourcePopUp(outsourceid, inparentid) {
	var dataObj={
		  	"outsourceid":""+outsourceid+"",
		  	"inparentid":""+inparentid+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "getoutsourcedetailsBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   document.getElementById("outsourceidos").value=outsourceid;
		   document.getElementById("inparentidos").value=inparentid;
		   document.getElementById("outsourcediv").innerHTML=data.outsourcediv;
		   document.getElementById("outsourcehandoverdiv").innerHTML=data.outsourcehandoverdiv;
		   document.getElementById("outsourceisretunddiv").innerHTML=data.outsourceisretunddiv;
		   document.getElementById("outsourcereturnuserdiv").innerHTML=data.outsourcereturnuserdiv;
		   document.getElementById("outsourcebtndiv").innerHTML=data.outsourcebtndiv;
		   $("#isreturnOS").trigger("chosen:updated");
		   $(".chosen").chosen({allow_single_deselect: true}); 
		   $('#invesoutsourcemodel').modal( "show" );	
	   },
	   error : function(result) {
		   jAlert('error', "Something wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }
	});	
}

function saveOutsourceDetails() {
	var oshandoverto = document.getElementById("oshandoverto").value;
	var isreturnOS =  document.getElementById("isreturnOS").value;
	var oshandoverfrom = document.getElementById("oshandoverfrom").value;
	var inparentidos = document.getElementById("inparentidos").value;
	var oshandovertostatus = document.getElementById("oshandovertostatus").value;
	var oshandoverfromstatus = document.getElementById("oshandoverfromstatus").value;
	var outexpdate=document.getElementById("outexpdsate").value;
	var flag =false;
	if(isreturnOS=='1'){
		/*if(oshandoverfrom==''){
			flag = true;
		}*/
	}
	
	if(flag){
			jAlert('error', "Please enter handover from name !", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		var url = "updateinvestoutsourceBookAppointmentAjax?invid="+inparentidos+"&oshandoverto="+oshandoverto+"&isreturnOS="+isreturnOS+"&oshandoverfrom="+oshandoverfrom+"&oshandovertostatus="+oshandovertostatus+"&outexpdsate="+outexpdate;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveOutsourceDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
}
	
	function saveOutsourceDetailsRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				 $('#invesoutsourcemodel').modal( "hide" );	
				 jAlert('success', "Investigation outsource updated successfully!", 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
			}
		}
	}
	
	
	function updatedateInvetstDate(){
		var investid="";
		investid=document.getElementById("hiddt").value
		var date=document.getElementById("datw").value
		var time=document.getElementById("datw1").value
		var url = "updateinvstkunalInvestigation?id="+investid+"&date="+date+"&time="+time;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updatedateInvetstDateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
		
	}
	function updatedateInvetstDateRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				 document.location.reload();
				}
			}
	}
	
	
	function showkunalbilburin(r,id,val,finalid){
		var tottalbilburin=0;
		var  directbilburin=0;
		var indirectbilburin=0;
		tottalbilburin=Number(document.getElementById("obs"+(finalid)).value);
		directbilburin=Number(document.getElementById("obs"+(finalid+1)).value);
		
		
		indirectbilburin=tottalbilburin-directbilburin;
		document.getElementById("obs"+(finalid+2)).value=indirectbilburin.toFixed(2);
	}
	
	
	function calprothombinpt(r,id,val,finalid){
		var test=0;
		var  control=0;
		var inr=0;
		test=Number(document.getElementById("obs"+(finalid)).value);
		control=Number(document.getElementById("obs"+(finalid+1)).value);
		if(control!=0){
			inr=test/control;
			document.getElementById("obs"+(finalid+2)).value=inr.toFixed(2);
		}
		
		
	}
	
	
	function showkunaltotalprotien(r,id,val,finalid){
		var tottalprotien=0;
		var  albumin=0;
		var globulin=0;
		var agratio=0;
		tottalprotien=Number(document.getElementById("obs"+(finalid)).value);
		albumin=Number(document.getElementById("obs"+(finalid+1)).value);
		globulin=Number(document.getElementById("obs"+(finalid+2)).value);
		agratio=Number(document.getElementById("obs"+(finalid+3)).value);
		
		globulin=tottalprotien-albumin;
		agratio=albumin/globulin;
		
		document.getElementById("obs"+(finalid+2)).value=globulin.toFixed(2);
		document.getElementById("obs"+(finalid+3)).value=agratio.toFixed(2);
	}
	
	
	
	function showkunallft(r,id,val,finalid){
		var tottalbilburin=0;
		var  directbilburin=0;
		var indirectbilburin=0;
		
		var tottalprotien=0;
		var  albumin=0;
		var globulin=0;
		var agratio=0;
		
		tottalbilburin=document.getElementById("obs"+(finalid)).value;
		directbilburin=document.getElementById("obs"+(finalid+1)).value;
		
		tottalprotien=document.getElementById("obs"+(finalid+6)).value;
		albumin=document.getElementById("obs"+(finalid+7)).value;
		globulin=document.getElementById("obs"+(finalid+8)).value;
		agratio=document.getElementById("obs"+(finalid+9)).value;
		
		indirectbilburin=tottalbilburin-directbilburin;
		document.getElementById("obs"+(finalid+2)).value=indirectbilburin.toFixed(2);
		
		globulin=tottalprotien-albumin;
		agratio=albumin/globulin;
		
		document.getElementById("obs"+(finalid+8)).value=globulin.toFixed(2);
		document.getElementById("obs"+(finalid+9)).value=agratio.toFixed(2);
	}
	
	
	
	function showkunalexlipid(r,id,val,finalid){
		var cholestrol=0,tryglyceride=0;
		var hdl=0,ldl=0,vldl=0;
		cholestrol=Number(document.getElementById("obs"+(finalid)).value);
		tryglyceride=Number(document.getElementById("obs"+(finalid+1)).value);
		hdl=Number(document.getElementById("obs"+(finalid+2)).value);
		ldl=Number(document.getElementById("obs"+(finalid+3)).value);
		vldl=Number(document.getElementById("obs"+(finalid+4)).value);
		
		vldl=tryglyceride/5;
		ldl=cholestrol-(vldl+hdl);
		if(true){
			document.getElementById("obs"+(finalid+4)).value=vldl;
		}
		if(true){
			document.getElementById("obs"+(finalid+3)).value=ldl;
		}
		
	}
	
	
	