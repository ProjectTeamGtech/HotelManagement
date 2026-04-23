function showprodpackajax(prodid){
	if(prodid=='0'){
		 jAlert('error', "please select product!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
		var url = "showpackPharmacy?prodid="+prodid+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showprodpackajaxRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	

}

function showprodpackajaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("qty").value = req.responseText;
			}
		}

}
var count=0;
function addtempbarcode(){
	
	var prodid = document.getElementById("newmedicine").value; 
	var prodtxt = $("#newmedicine option:selected").text();
	var qty = document.getElementById("qty").value;

	
		count++;
	
	if(prodid=='0'){
		jAlert('error', "please select product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(qty=='' || qty<0 || qty==0){
		jAlert('error', "please enter quantity!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(false){
		jAlert('error', "please add only one product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		
		/*var url = "addtempbarcodePharmacy?prodid="+prodid+"&prodtxt="+prodtxt+"&qty="+qty+" ";*/
		var url = "addtempbarcodePharmacy?prodid="+prodid+"&qty="+qty+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = addtempbarcodeRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
	
}

function addtempbarcodeRequest(){
if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("barcodedataid").innerHTML = req.responseText;
			}
		}
}

function deletedata(id){
	count =0;
   var url = "delbarcodePharmacy?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deletedataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function deletedataRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("barcodedataid").innerHTML = req.responseText;
			}
		}
}
function generatebarcodeofinventory(type) {
	var flag = true;
	$('.ajclass').each(function() { 
		flag = false;
	});
	if(!flag){
		opencPopup('barcodePharmacy?type='+type);
	}else{
		jAlert('error', "please select at least one product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}
	
}

function setproductofwarehouse(id) {

	var url = "getstoreproductforbarcodePharmacyAjax?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = setproductofwarehouseRequest;
	req.open("GET", url, true);
	req.send(null);

}
function setproductofwarehouseRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("productdatadiv").innerHTML = req.responseText;
			$("#newmedicine").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}