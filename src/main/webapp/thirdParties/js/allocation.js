function showAllocationPopup(){
	$('#allocationpopup').modal( "show" );
}

function savealtdata(){
	if(document.getElementById("thirdParty").value==0){
		jAlert('error', 'Please Select Third Party', 'Error Dialog');
  			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}
	else if(document.getElementById("allotamount").value==''){
		jAlert('error', 'Please Enter Amount', 'Error Dialog');
  			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		document.getElementById("savaltfrm").submit();
	}

}

function updatealt(){
	document.getElementById('altfrm').submit();
}

function showRniningBaCalc(invno,bal){
var result = 0;
	var dedtn = document.getElementById("dedtn"+invno).value;
	var tds = document.getElementById("tds"+invno).value;
	var stmdcine = document.getElementById("stmdcine"+invno).value;
	var recdamt = document.getElementById("recdamt"+invno).value;
	
	var sum = parseFloat(dedtn) + parseFloat(tds) + parseFloat(stmdcine) + parseFloat(recdamt);
	 result = parseFloat(bal) - parseFloat(sum);
	document.getElementById("runbal"+invno).value = result;
}