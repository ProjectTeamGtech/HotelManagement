function checknewDisFields(obj){
	var val='';
	
	if(obj.checked){
		if(document.getElementById(obj.id+'_text')){
		document.getElementById(obj.id+'_text').readOnly='' ;
		}
		val='1';
	}else{
		if(document.getElementById(obj.id+'_text')){
		document.getElementById(obj.id+'_text').readOnly='readonly' ;
		}
		val='';
	}
	var ipdid= document.getElementById('jsonipdid').value;
	saveDischargeField(val, obj.id, ipdid);
	/*jAlert('success', " Updated!", 'Success Dialog');
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); */
}



function saveDataToNewField(obj){
	var ipdid= document.getElementById('jsonipdid').value;
	
	saveDischargeField(encodeEntity(obj.value), obj.id, ipdid);
}

function saveDischargeField(val,column,ipdid ){
	var url="setdichargecheckersCommonnew?colid="+column+"&value="+val;
	
	 var dataObj= {
				"ipdid":""+ipdid+"", 
				"val":""+val+"", 
				"column":""+column+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "setdichargecheckersCommonnew",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  
		  },
		  error : function(result) {
		   console.log("error");
		  }
		 });

}




function setdischecked(){
	
		$('.lokchecker').each(function() {	
		if(this.value=='1'){
			this.checked=true;
			document.getElementById(this.id+'_text').readOnly='' ;
		}else{
			document.getElementById(this.id+'_text').readOnly='readonly' ;
		}
		
	});
}



function savevitalsNew(obj){
	var id=obj.id;
	var value=obj.value;
	var date='';
	var ipdid= document.getElementById('jsonipdid').value;
	var clientId= document.getElementById('clientid').value;
	var d = new Date();
		if(d.getDate().length==1){
			date="0"+d.getDate();
		}else{
			date=d.getDate();
		}
		if((d.getMonth()+1).length==1){
			date=date+"-0"+(d.getMonth()+1);
		}else{
			date=date+"-"+(d.getMonth()+1);
		}
		date=date+"-"+ d.getFullYear();
	var mm=''+d.getMinutes();
	var hh=''+d.getHours();
	if(hh.length==1){
		hh='0'+hh;
	}
	if(mm.length==1){
		mm='0'+mm;
	}
	
	var datetime=date+" "+hh+":"+mm;
	saveFindingVitalsClinical(value,ipdid,clientId,id,datetime,1);
}

