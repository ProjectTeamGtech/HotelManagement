/**
 * 
 *   05 April 18
 * 
 */

function donabhsubmit(action) {
       $('#baselayout1loaderPopup').modal( "show" );
       document.getElementById("action").value=action;
       document.getElementById("nabhfrm").submit(); 
}

function submitnabhdashboard(val) {
	var formt= document.getElementById("nabhfrm");
	val = val+''+'Nabh';
    formt.action=val;
    formt.submit();
}
var srnno=0;
var akkipiiid=0;
function saveNewKPIResult(kpiid,indicatorid,kpi_year,kpi_month,kpi_dataid,srno) {
	var val1="";
	var val2="";
	var val3="";
	var val4="";
	var val5="";
	srnno = srno;
	akkipiiid = kpiid;
	$('.akash'+kpiid+'').each(function(){
		if(this.value=='1'){
			var val11=this.value+""+kpiid;
			val1 = document.getElementById("input"+val11).value;
		}else if(this.value=='2'){
			var val11=this.value+""+kpiid;
			val2 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='3'){
			var val11=this.value+""+kpiid;
			val3 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='4'){
			var val11=this.value+""+kpiid;
			val4 = document.getElementById("input"+val11).value;
		}
		else if(this.value=='5'){
			var val11=this.value+""+kpiid;
			val5 = document.getElementById("input"+val11).value;
		}
	});
	kpi_month = document.getElementById("monthfilter"+kpiid).value;
	kpi_year = document.getElementById("kpiyearfilter"+kpiid).value;
	var result = document.getElementById("result"+kpiid).value;
	var target = document.getElementById("target"+kpiid).value;
	if(kpi_month=='00'){
		jAlert('error', "Please select month!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		var url="savekpiresultNabh?kpiid="+kpiid+"&val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&indicatorid="+indicatorid+"&result="+result+"&kpi_year="+kpi_year+"&kpi_month="+kpi_month+"&kpi_dataid="+kpi_dataid+"&target="+target+"&srno="+srno+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = saveNewKPIResultRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
	
}
function saveNewKPIResultRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				var kpi_year11 = document.getElementById("kpiyearfilter"+akkipiiid).value;
				var str= req.responseText;
				var data= str.split("~~~");
				var newData = [];
				var resultvalue = data[0].split(",");
				for (var i = 0; i < resultvalue.length; i++) {
					var array_element = resultvalue[i];
					if(array_element!=','){
						newData.push(parseFloat(array_element));
					}
				}
				
				var newData1 = [];
				var resultvalue1 = data[1].split(",");
				for (var i = 0; i < resultvalue1.length; i++) {
					var array_element = resultvalue1[i];
					if(array_element!=','){
						newData1.push(parseFloat(array_element));
					}
				}
				
				
				var chart = $('#indicator'+srnno).highcharts();
				chart.series[0].setData(newData, true);
				chart.series[1].setData(newData1, true);
				chart.setTitle({text: kpi_year11});
				$('#indicator'+srnno).highcharts().redraw();
	   }
	}	 
}

function kpiyearchange(val,kpiid,indicatorid,srno) {
	kpi_month = val;
	/*var year_filter = document.getElementById("year_filter").value;*/
	var year_filter = val;
	if(year_filter=='0'){
		document.getElementById("monthfilter"+kpiid).value ='00';
		alert('Please select year first!!!');	
	}else{
		
		document.getElementById("monthfilter"+kpiid).value= '00';
		var val1="";
		var val2="";
		var val3="";
		var val4="";
		var val5="";
		$('.akash'+kpiid+'').each(function(){
			if(this.value=='1'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = 0;
			}else if(this.value=='2'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = 0;
			}
			else if(this.value=='3'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = 0;
			}
			else if(this.value=='4'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value = 0;
			}
			else if(this.value=='5'){
				var val11=this.value+""+kpiid;
				document.getElementById("input"+val11).value= 0;
			}
		});
		var url="getkpigraphdataNabh?kpiid="+kpiid+"&kpi_year="+year_filter+"&indicatorid="+indicatorid+"&srno="+srno+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = kpiyearchangeRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
	
}
function kpiyearchangeRequest(){
	if (req.readyState == 4) {
			if(req.status == 200) {
				
				var str= req.responseText;
				var data= str.split("~~~");
				var newData = [];
				var resultvalue = data[3].split(",");
				for (var i = 0; i < resultvalue.length; i++) {
					var array_element = resultvalue[i];
					if(array_element!=','){
						newData.push(parseFloat(array_element));
					}
				}
				
				var newData1 = [];
				var resultvalue1 = data[4].split(",");
				for (var i = 0; i < resultvalue1.length; i++) {
					var array_element = resultvalue1[i];
					if(array_element!=','){
						newData1.push(parseFloat(array_element));
					}
				}
				
				var chart = $('#indicator'+data[0]).highcharts();
				chart.series[0].setData(newData, true);
				chart.series[1].setData(newData1, true);
				chart.setTitle({text: data[2]});
				$('#indicator'+data[0]).highcharts().redraw();
			    
	   }
	}	 
}

function changearealist(val) {
		var url="changearealistNabh?subcategoryid="+val+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = changearealistRequest;
		req.open("GET", url, true); 
		req.send(null);
}

function changearealistRequest(){
	if (req.readyState == 4) {
		if(req.status == 200) {
			var str= req.responseText;
			document.getElementById("changearealistdiv").innerHTML =str;
		}
	}	 
}

function validatefilesubmission() {
	var filesubmission_category = document.getElementById("filesubmission_category").value;
	var filesubremark = document.getElementById("filesubremark").value;
	var nme = document.getElementById("filesub_uploadfiles");
	var FileSize = nme.files[0].size/1024/1024; // in MB
	if(filesubmission_category=='0'){
		alert('Please select category!');
	}else if(filesubremark==''){
		alert('Please enter remark!');
	}else if(nme.value.length < 4) {
		alert('Must select any of file for upload!');
	    nme.focus();
	}else if(FileSize> 2) {
		document.getElementById("filesub_uploadfiles").value="";
        alert('File size exceeds 2 MB');
	}else{
		$("#baselayout1loaderPopup").modal("show");
		document.getElementById("filesubmitformids").submit();
	}
}

function submitCompilanceTracker() {
	var filter_subcategoryid = document.getElementById("filter_subcategoryid").value;
	var filter_areaid = document.getElementById("filter_areaid").value;
	
	if(filter_areaid=='0'){
		alert('Please select KPI area!');
	}else{
		document.getElementById("nabhaccreditationtrackerform").submit();
	}
}

function checksizeoffile() {
	var uploadField = document.getElementById("filesub_uploadfiles");
	 var FileSize = uploadField.files[0].size/1024/1024; // in MB
     if (FileSize > 2) {
    	 document.getElementById("filesub_uploadfiles").value="";
         alert('File size exceeds 2 MB');
     } 
}
function deleteuploadedfile(val) {
	
	
	 var d=window.confirm("Are you sure to Delete this file!");
	 if(d==true){
		 $("#baselayout1loaderPopup").modal("show");
		 var url="deleteuploadedfileNabh?id="+val+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = deleteuploadedfileRequest;
			req.open("GET", url, true); 
			req.send(null);
	 }
	
	
}

function deleteuploadedfileRequest(){
if (req.readyState == 4) {
	if(req.status == 200) {
		 window.location.reload();
	}
}	 
}


function checksizeoffileKPI(id) {
	var uploadField = document.getElementById("filesub_uploadfiles"+id);
	 var FileSize = uploadField.files[0].size/1024/1024; // in MB
     if (FileSize > 2) {
    	 document.getElementById("filesub_uploadfiles"+id).value="";
         alert('File size exceeds 2 MB');
     } 
}
 
function validatefilesubmissionKPI(id,status) {
	var filesubremark = document.getElementById("filesubremark"+id).value;
	var filter_subcategoryid = document.getElementById("filter_subcategoryid").value;
	var filter_areaid = document.getElementById("filter_areaid").value;
	document.getElementById("chklistsubvatid"+id).value =filter_subcategoryid;
	document.getElementById("chklistareaid"+id).value = filter_areaid;
	
	
	/*var nme = "";
	if(status==1){
		nme = document.getElementById("filesub_uploadfiles"+id);
		var FileSize = nme.files[0].size/1024/1024; // in MB
	}*/
	
	if(filesubremark==''){
		alert('Please enter remark!');
	}
	/*else if(nme.value.length < 4) {
		alert('Must select any of file for upload!');
	    nme.focus();
	}else if(FileSize> 2) {
		document.getElementById("filesub_uploadfiles").value="";
        alert('File size exceeds 2 MB');
	}*/
	else{
		$("#baselayout1loaderPopup").modal("show");
		document.getElementById("filesubmitformids"+id).submit();
	}
}
var openpopupwidth = 1920;
var openpopupheight = 1024;

function openNABHBlankPopup(URL) { 
	var oldwindow = window.open(URL, "_blank", "status = 1, height = "+openpopupheight+", width = "+openpopupwidth+", resizable = 0,scrollbars=yes" ); 
	oldwindow.focus();
}

function switchVisibleNewchecklist() {
	if (document.getElementById('switchVisibleNewCommittee1')) {
		if (document.getElementById('switchVisibleNewCommittee1').style.display == 'none') {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'block';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'none';
			document.getElementById('categorydiv0').className="col-md-3";
			document.getElementById('changearealistdiv').className="col-md-3";
			document.getElementById('categorydiv1').className="col-md-2";
			document.getElementById('categorydiv2').className="col-md-8 hidden";
		}else {
			document.getElementById('switchVisibleNewCommittee1').style.display = 'none';
			document.getElementById('switchVisibleNewCommittee2').style.display = 'block';
			document.getElementById('categorydiv0').className="col-md-3 hidden";
			document.getElementById('changearealistdiv').className="col-md-3 hidden";
			document.getElementById('categorydiv1').className="col-md-2 hidden";
			document.getElementById('categorydiv2').className="col-md-8";
		}
	}
}

function addchangearealist(val) {
	var url="addchangearealistNabh?subcategoryid="+val+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = addchangearealistRequest;
	req.open("GET", url, true); 
	req.send(null);
}

function addchangearealistRequest(){
	if (req.readyState == 4) {
		if(req.status == 200) {
			var str= req.responseText;
			document.getElementById("addareaiddiv").innerHTML =str;
			document.getElementById("addindicatoriddiv").innerHTML ="";
		}
	}	 
}

function addchangeindicator(val) {
	var url="addchangeindicatorlistNabh?areaid="+val+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = addchangeindicatorRequest;
	req.open("GET", url, true); 
	req.send(null);
}

function addchangeindicatorRequest(){
	if (req.readyState == 4) {
		if(req.status == 200) {
			var str= req.responseText;
			document.getElementById("addindicatoriddiv").innerHTML =str;
		}
	}	 
}

function validateindicatorsubmission() {
	var addareaid = document.getElementById("addareaid").value;
	var addindicator = document.getElementById("addindicator").value;
	var remark = document.getElementById("addremark").value;
	if(addareaid=='0'){
		alert('Please select area!');
	}else if(addindicator=='0'){
		alert('Please select indicator!');
	}else if(remark=='' || remark==' ' || remark=='  ' || remark=='   '){
		alert('Please enter checklist!');
	}else{
		$("#baselayout1loaderPopup").modal("show");
		document.getElementById("savenewchecklistform").submit();
	}
}

function dovideotutorialsubmit(action) {
    $('#baselayout1loaderPopup').modal( "show" );
    document.getElementById("action").value=action;
    document.getElementById("hisvideotutorialfrm").submit(); 
}
function checksizeofvideotutorial() {
	var uploadField = document.getElementById("filesub_uploadfiles");
	 var FileSize = uploadField.files[0].size/1024/1024; // in MB
     if (FileSize > 100) {
    	 document.getElementById("filesub_uploadfiles").value="";
         alert('File size exceeds 100 MB');
     } 
}

function validatevideotutorial() {
	var filesubmission_category = document.getElementById("filesubmission_category").value;
	var filesubremark = document.getElementById("filesubremark").value;
	var nme = document.getElementById("filesub_uploadfiles");
	var filesubsubmodule = document.getElementById("filesubsubmodule").value;
	var filesubserachkey = document.getElementById("filesubserachkey").value;
	var filesubfeatures = document.getElementById("filesubfeatures").value;
	var filesubtitle = document.getElementById("filesubtitle").value;
	if(filesubmission_category=='0'){
		alert('Please select category!');
	}else if(filesubsubmodule=='0') {
		alert('Please select submodule');
	}else if(filesubfeatures==''){
		alert('Please enter features!');
	}else if(filesubserachkey=='') {
		alert('Please enter serach key');
	}else if(nme.value.length < 4) {
		alert('Must select any of file for upload!');
	    nme.focus();
	}else if(filesubtitle==''){
		alert('Please enter title!');
	}else if(filesubremark==''){
		alert('Please enter description!');
	}else{
		$("#baselayout1loaderPopup").modal("show");
		document.getElementById("filesubmitformids").submit();
	}
}

function savenewsubmodule() {
	var filesubnewsubmodule = document.getElementById("filesubnewsubmodule").value;
	var videotutorialmodule_action = document.getElementById("videotutorialmodule_action").value;
	if(filesubnewsubmodule==''){
		alert('Please enter sub module name');
	}else{
		 $("#baselayout1loaderPopup").modal("show");
		 var dataObj={
		  	"submodule":""+filesubnewsubmodule+"",
		  	"action":""+videotutorialmodule_action+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "savesubmodulenameNabh",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   if(data.alreadysaved==1){
				   $("#baselayout1loaderPopup").modal("hide");
				   alert('Already saved ');
			   }else{
				   $("#baselayout1loaderPopup").modal("hide");
				  	document.getElementById("submodulediv").innerHTML =data.selecttaglist;
					document.getElementById("filesubnewsubmodule").value ='';
			   }
			   
			   var classname=document.getElementById('addnewsubmoduleopendiv').className;
				if(classname=='form-group hidden'){
					document.getElementById('addnewsubmoduleopendiv').className='form-group';
					document.getElementById('newsubmoduleaddtag').text='Hide New Submodule';
				}else{
					document.getElementById('addnewsubmoduleopendiv').className='form-group hidden';
					document.getElementById('newsubmoduleaddtag').text='Add New Submodule';
				}
			 
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
}

function addnewsubmoduleopenhidden(){
	var classname=document.getElementById('addnewsubmoduleopendiv').className;
	if(classname=='form-group hidden'){
		document.getElementById('addnewsubmoduleopendiv').className='form-group';
		document.getElementById('newsubmoduleaddtag').text='Hide New Submodule';
	}else{
		document.getElementById('addnewsubmoduleopendiv').className='form-group hidden';
		document.getElementById('newsubmoduleaddtag').text='Add New Submodule';
	}
}

function showmoredetailsoffile(count){
	var classname=document.getElementById('trchangevt'+count).className;
	if(classname=='hidden'){
		document.getElementById('trchangevt'+count).className='';
		document.getElementById('trdatechangevt'+count).className='';
		document.getElementById('tdchangevt'+count).innerHTML="-";
		document.getElementById('downtoupss'+count).className='fa fa-arrow-up';
	}else{
		document.getElementById('trchangevt'+count).className='hidden';
		document.getElementById('trdatechangevt'+count).className='hidden';
		document.getElementById('tdchangevt'+count).innerHTML="+";
		document.getElementById('downtoupss'+count).className='fa fa-arrow-down';
	}
}

function deletevideotutorialuploadedfile(val) {
	 var d=window.confirm("Are you sure to Delete this file!");
	 if(d==true){
		 $("#baselayout1loaderPopup").modal("show");
		 var url="deletevideotutorialuploadedfileNabh?id="+val+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = deletevideotutorialuploadedfileRequest;
			req.open("GET", url, true); 
			req.send(null);
	 }
}

function deletevideotutorialuploadedfileRequest(){
if (req.readyState == 4) {
	if(req.status == 200) {
		 window.location.reload();
	}
}	 
}
