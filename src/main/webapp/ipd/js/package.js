
function showPackageData(id){
	  var url = "pkgIpdDashboard?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPackageDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showPackageDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
		    var str=data.split("#");
			document.getElementById('pkgdtailbody').innerHTML = str[0];
			document.getElementById('pkgamtid').value =  str[1];
		}
	}
}


function showPertotal(id,val){
var res = 0;
	var size = document.getElementById('hdnpkgsize').value;
	var pkgamt = document.getElementById('gpgamt').value;
	
	var r = parseFloat(pkgamt)*parseFloat(val) / 100;
	
	document.getElementById('pkgamt'+id).value = currencySign + r;
	
	for(i=1;i<=size;i++){
		 res = parseFloat(res) +  parseFloat(pkgamt)*parseFloat(document.getElementById('pkgper'+i).value) / 100;
	}
	
	document.getElementById('chtotal').value = currencySign + res;
}

function changePackageTotal(val){
		var res = 0;
		$('.akash').each(function() { 
			var val1 = this.value;
			var pkgper = document.getElementById('pkgper'+val1).value;
			var cal = parseFloat(val)*parseFloat(pkgper) / 100;
			document.getElementById('pkgamt'+val1).value =cal;
			res = res+cal;
		});
		
		document.getElementById('chtotal').value = currencySign + res;
}

function setReadOnly(val){
	if(val==''){
		document.getElementById("pkgamtid").readOnly = true;
	}else{
		document.getElementById("pkgamtid").readOnly = false;
	}
}

function validatepackage(){
	var data = document.getElementById('pkgamtid').value;
	if(data==''){
		jAlert('error', "Plz enter amount", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		return false;		
	}else if(data==''){
		jAlert('error', "Plz enter amount", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		return false;		
	}else{
		return true;
	} 
}


