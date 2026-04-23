function opennursingPopup(){
	
	 $('#nursingmod').modal( "show" );
	 document.getElementById('nursingtable').innerHTML='';
	 loadNusringRepeatList(document.getElementById('ipdid').value);
}


function savenursing_by_ajax(cid,ipdid,practionerid,condid){
	 $('#baselayout1loaderPopup').modal( "show" );

	 	var date=document.getElementById('datenurse').value;
		var hh=document.getElementById('nhh').value;
		var mm=document.getElementById('nmm').value;
		
		
		var todate=document.getElementById('todatenurse').value;
		var tohh=document.getElementById('tonhh').value;
		var tomm=document.getElementById('tonmm').value;
		
		var d = new Date();
	 
		if(hh=='0'){
			hh=d.getHours();
		}
		if(mm=='0'){
			mm=d.getMinutes();
		}
		if(hh.length==1){
			hh='0'+hh;
		}
		if(mm.length==1){
			mm='0'+mm;
		}
		
		if(tohh=='0'){
			tohh=d.getHours();
		}
		if(tomm=='0'){
			tomm=d.getMinutes();
		}
		if(tohh.length==1){
			tohh='0'+tohh;
		}
		if(tomm.length==1){
			tomm='0'+tomm;
		}
		var givento=document.getElementById('ngivento').value;
		var fromtime=hh+':'+mm+':00';
		var totime=tohh+':'+tomm+':00';
	   var url="save_nursing_ajaxNursing?cid="+cid+"&ipdid="+ipdid+"&condid="+condid+"&practionerid="+practionerid; 
	    url=url+'&date='+date+'&todate='+todate+'&fromtime='+fromtime+'&totime='+totime+'&givento='+givento;  
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = savenursing_by_ajaxRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);
}

function savenursing_by_ajaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str= req.responseText;
		       if(document.getElementById('maintextarea')){
			    	  
					nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str + "<br>");
		       }
		       $('#baselayout1loaderPopup').modal( "hide" );
		       $('#nursingmod').modal( "hide" );
		       jAlert('success', ' Saved!', 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
}





function openVitalClinical(){
	 $('#vitalmod').modal( "show" );
}

function saveclinicalnotesvitals(clientId,ipdid){
	 $('#baselayout1loaderPopup').modal( "show" );
		var masterId='';
		var value='';
		var datetime=''
		var date=document.getElementById('datevital').value;
		var hh=document.getElementById('vhh').value;
		var mm=document.getElementById('vmm').value;
		var d = new Date();
		if(date==''){
			
			if(d.getDate()<10){
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
		}
		
		if(hh=='0'){
			hh=d.getHours();
		}
		if(mm=='0'){
			mm=d.getMinutes();
		}
		if(hh.length==1){
			hh='0'+hh;
		}
		if(mm.length==1){
			mm='0'+mm;
		}
		var itms='';
		datetime=date+" "+hh+":"+mm;
		
		
		var htmlsContent="<h3>Vitals :  "+datetime+"</h3><table class='my-table' style='width:30%'><tr><th>Vital Name</th><th>Findings</th><th>Unit</th></tr>";
		
		var name='';
		var unit='';
		$('.lokloop').each(function() { 
			masterId=this.id;
			value=this.value;
			if(value!=''){
				name=document.getElementById('namev'+masterId).innerHTML;
				unit=document.getElementById('unitv'+masterId).innerHTML;
				htmlsContent=htmlsContent+"<tr><td>"+name+"</td><td>"+value+"</td><td>"+unit+"</td></tr>";
				saveFindingVitalsClinical(value, ipdid, clientId, masterId,datetime,0);
			}
			
			this.value='';
		});
		htmlsContent=htmlsContent+"</table>";
		
		
		
		 if(document.getElementById('maintextarea')){
	    	  
				nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + htmlsContent + "<br>");
	       }
		
		$('#baselayout1loaderPopup').modal( "hide" );
		 $('#vitalmod').modal( "hide" );
		 
		 jAlert('success', 'Vitals Given!', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		
	}


function saveFindingVitalsClinical(val,ipdid,clientid,vitalid,time,isfromdcard){
	  
	 
	  var url="saveclientfindingCommonnew?finding="+val+"&ipdid="+ipdid+"&clientid="+clientid+"&vitalid="+vitalid+"&time="+time+"&isfromclinical=1&isfromdcard="+isfromdcard;
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveFindingVitalsClinicalRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	  
	  
} 
function saveFindingVitalsClinicalRequest() {
	  
	     if (req.readyState == 4) {
	         if (req.status == 200) {
	        	   
	        	   
	             }
	         }
	  }  

