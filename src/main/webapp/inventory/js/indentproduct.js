function getsub(id) {
           
      var url="getsubcategoriesProduct?id="+id+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getsubRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }


 
function getsubRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("subdiv").innerHTML=req.responseText;
	           $("#subcategory_id").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
         }
	}	 
}

function getsubproduct(id) {
           
      var category=document.getElementById("category_id").value;     
      var url="setmedicineproductProduct?medicinetype="+id+"&category="+category+"";
     
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getsubproductRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }
 
 
function getsubproductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("proddiv").innerHTML=req.responseText;
	           $("#product_id").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
         }
	}	 
}

var row;

function addnewIndent(tableId){
  
     var table = document.getElementById(tableId);
	 var rowCount = table.rows.length;
	 var pid=document.getElementById("product_id").value;
	 var qty=document.getElementById("qty").value;
	 var exp_date=document.getElementById("expected_date").value;
	 var warehouse_id = document.getElementById("warehouse_id").value;
	 var isnumberqty = checkNumberOrNotInIndent(qty);
	 var flag = false;
	 var data ='0';
	 $('.dclass').each(function() {
         var i=this.value;
         if(i==pid){
        	 flag = true;
        	 
         }
         data = data +","+this.value;
	 });
	 var flagstatus=false;
	 var locationMisMatchFlag =false;
	 if(document.getElementById("parentTransferId").value!=0){
		 if(warehouse_id!=document.getElementById("parentTransferlocation").value){
			 locationMisMatchFlag =true;
		 }
	 }
	 
	if(warehouse_id=='0' && flagstatus==false){
			flagstatus=true;
		 jAlert('error', "Please select store!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(pid=='0' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please select at least one item!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(qty=='' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter quantity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(!isnumberqty && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter valid qunatity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(qty=='0' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter quantity greater than 0!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(exp_date=='' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter expected date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(locationMisMatchFlag){
		flagstatus=true;
		 jAlert('error', "Below added product warehouse location is different from selected warehouse location !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(flag==false){
		 row=table.insertRow(rowCount);
		 rowCount--;
	     var url="addnewindentProduct?count="+rowCount+"&pid="+pid+"&exp_date="+exp_date+"&qty="+qty+"&data="+data+"&warehouse_id="+warehouse_id;
	   
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	               
	     req.onreadystatechange = addnewIndentRequest;
	     req.open("GET", url, true); 
	              
	     req.send(null);   
	 }else{
		 jAlert('error', "Already added this item!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }
	 
}
function addnewIndentRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			if(data=='0'){
				 jAlert('error', "Already added this product!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
			}else if(data=='1'){
				jAlert('error', "Location is yet to be allocated to your ID. Please co-ordinate support team.!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				 row.innerHTML=req.responseText;
			}
         }
	}	 
}


function checkNumberOrNotInIndent(inputtxt){
	 //var numbers = /^[0-9]+$/;  
	 var numbers =  /^-{0,1}\d*\.{0,1}\d+$/;
     if(inputtxt.match(numbers))  {
        return true;
     } else {
          return false;
     }
} 

function saveInden(val) {
	$("#dashboardloaderPopup").modal('show');
	var total=0;
	 $('.dclass').each(function() {
          var i=this.value;
         // total=total+i;   
          total=total+","+this.value;
          var comment = document.getElementById("comment"+i).value;
          if(comment!=''){
        	  var str = replaceallindentproduct(comment,"'",' ');
              document.getElementById("comment"+i).value= str;
              str = replaceallindentproduct(str,'"',' ');
              document.getElementById("comment"+i).value= str;
          }
     });
	 if(total==0){
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please request at least one product!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else{
		 $("#dashboardloaderPopup").modal('hide');
	 	 var x = confirm("Are You Sure!!");
		 if(x){
			 $("#dashboardloaderPopup").modal('show');
			 document.getElementById("indentModifyButton").value = val;
			 document.getElementById("allproductid").value= total;
		     document.getElementById("indentform").submit();
		 }
		 
	 }
	 
}

function updateTransferLog(){
	 $("#dashboardloaderPopup").modal('show');
	var flagstatus = false;
	$('.indnetclass').each(function() {
        var i=this.value;
        if(!flagstatus){
        	var qty = document.getElementById("reqqty"+i).value;
        	var isnumberqty = checkNumberOrNotofindentproduct(qty);
        	if(qty=='' && flagstatus==false){
        		flagstatus=true;
        		 jAlert('error', "Please enter quantity!", 'Error Dialog');	
        			setTimeout(function() {
        				$("#popup_container").remove();
        				removeAlertCss();
        			}, alertmsgduration); 
        	}else if(!isnumberqty && flagstatus==false){
        		flagstatus=true;
        		 jAlert('error', "Please enter valid qunatity!", 'Error Dialog');	
        			setTimeout(function() {
        				$("#popup_container").remove();
        				removeAlertCss();
        			}, alertmsgduration); 
        	}else if(qty=='0' && flagstatus==false){
        		flagstatus=true;
        		 jAlert('error', "Please enter quantity greater than 0!", 'Error Dialog');	
        			setTimeout(function() {
        				$("#popup_container").remove();
        				removeAlertCss();
        			}, alertmsgduration); 
        	}else{
        		var comment = document.getElementById("comment"+i).value;
                if(comment!=''){
              	    var str = replaceallindentproduct(comment,"'",' ');
                    document.getElementById("comment"+i).value= str;
                    str = replaceallindentproduct(str,'"',' ');
                    document.getElementById("comment"+i).value= str;
                }
        	}
        }
     });
	
	if(!flagstatus){
		 $("#dashboardloaderPopup").modal('hide');
		 	var x = confirm("Are You Sure!!");
			if(x){
				$("#dashboardloaderPopup").modal('show');
				document.getElementById("deptrequestform").action='updaterequestProduct';
				document.getElementById("deptrequestform").submit();
			}
		
	}else{
		 $("#dashboardloaderPopup").modal('hide');
	}
    
      
}

 function deleteRow(r,table,id) {
	 $("#dashboardloaderPopup").modal('show');
     var i = r.parentNode.parentNode.rowIndex;
     document.getElementById(table).deleteRow(i);
    	 
     var url="deleteindentreqProduct?id="+id+"";  	  
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = deleteRowRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
}
function deleteRowRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			$("#dashboardloaderPopup").modal('hide');
         }
	}	 
}


function updateapproveindent() {
  
	 $("#dashboardloaderPopup").modal('show');
		var flagstatus = false;
		$('.indnetclass').each(function() {
	        var i=this.value;
	        if(!flagstatus){
	        	var qty = document.getElementById("reqqty"+i).value;
	        	var isnumberqty = checkNumberOrNotofindentproduct(qty);
	        	if(qty=='' && flagstatus==false){
	        		flagstatus=true;
	        		 jAlert('error', "Please enter quantity!", 'Error Dialog');	
	        			setTimeout(function() {
	        				$("#popup_container").remove();
	        				removeAlertCss();
	        			}, alertmsgduration); 
	        	}else if(!isnumberqty && flagstatus==false){
	        		flagstatus=true;
	        		 jAlert('error', "Please enter valid qunatity!", 'Error Dialog');	
	        			setTimeout(function() {
	        				$("#popup_container").remove();
	        				removeAlertCss();
	        			}, alertmsgduration); 
	        	}else if(qty=='0' && flagstatus==false){
	        		flagstatus=true;
	        		 jAlert('error', "Please enter quantity greater than 0!", 'Error Dialog');	
	        			setTimeout(function() {
	        				$("#popup_container").remove();
	        				removeAlertCss();
	        			}, alertmsgduration); 
	        	}
	        }
	     });
		if(document.getElementById("notes")){
			var comment = document.getElementById("notes").value;
	        if(comment!=''){
	      	    var str = replaceallindentproduct(comment,"'",' ');
	            document.getElementById("notes").value= str;
	            str = replaceallindentproduct(str,'"',' ');
	            document.getElementById("notes").value= str;
	        }
		}
		
		if(!flagstatus){
			 $("#dashboardloaderPopup").modal('hide');
			 	var x = confirm("Are You Sure!!");
				if(x){
					$("#dashboardloaderPopup").modal('show');
					document.getElementById("deptrequestform").action="updateapproveindentProduct";
				     document.getElementById("deptrequestform").submit();
				}
		}else{
			 $("#dashboardloaderPopup").modal('hide');
		}
}



function setwarehouse(val){
   
        var url="warehousecategoryProcurement?id="+val+"";
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setwarehouseRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function setwarehouseRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			        document.getElementById("categorydiv").innerHTML=req.responseText;
			        $("#category_id").trigger("chosen:updated");
			  		$(".chosen").chosen({allow_single_deselect: true});
	         }
		}
}


function setvendorwarehouse(val){
	   
    var url="setwarehouseProcurement?id="+val+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setvendorwarehouseRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setvendorwarehouseRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		        document.getElementById("categorydiv").innerHTML=req.responseText;
		        $("#category_id").trigger("chosen:updated");
		  		$(".chosen").chosen({allow_single_deselect: true});
         }
	}
}





function getsubcategory(id) {
           
      var url="getsubcategoriesProcurement?id="+id+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getsubcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }


 
function getsubcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("subdiv").innerHTML=req.responseText;
	           $("#subcategory_id").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
         }
	}	 
}
function getSubPoproduct(id) {
           
      var category=document.getElementById("category_id").value;     
      var url="setmedicineproductProcurement?medicinetype="+id+"&category="+category+"";
     
     if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getSubPoproductRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }
 
 
function getSubPoproductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("proddiv").innerHTML=req.responseText;
	           $("#product_id").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
         }
	}	 
}
function setdiscRate(id) {
	var flag =false;
	var val =0;
	if(document.getElementById("warehouse")){
		val = document.getElementById("warehouse").value;
		if(val==0){
			flag = true;
		}
	}
	if(flag){
		jAlert('error', "Please select warehouse!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="setproddiscProcurement?id="+id+"&val="+val+"";
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	     req.onreadystatechange =setdiscRateRequest;
	     req.open("GET", url, true); 
	     req.send(null);   
	}
     
}
function setdiscRateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          var str=req.responseText;
	          var data=str.split("~");
	          document.getElementById("rate").value=data[0];
	          document.getElementById("discount").value=data[1];
	          if(document.getElementById("vendid")){
	        	  document.getElementById("vendid").innerHTML= data[2];
	        	  $("#vendorid").trigger("chosen:updated");
				   $(".chosen").chosen({allow_single_deselect: true});
	          }
	          if(document.getElementById("qty")){
	        	  document.getElementById("qty").value= 0;
	          }
	          
	          if(document.getElementById("xtotal")){
	        	  document.getElementById("xtotal").value= 0;
	          }
	          
	           
	          
         }
	}	 
}

function saveNewGrn() {
     
     var warehouse=document.getElementById("warehouse").value;
     if(warehouse=='0'){
	               jAlert('error', "Please select warehouse !", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flag=true;
	 }
     var flag=false;
	 var product_id= document.getElementById("product_id").value; 
	 if(product_id=="0"){
	          jAlert('error', "Please select product !", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	         flag=true;
	 }
	 var vendorid= document.getElementById("vendorid").value; 
	 if(!flag && vendorid=="0"){
	          jAlert('error', "Please select vendorid !", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	         flag=true;
	 }
	  var rate= document.getElementById("rate").value; 
	  var regexp = /^\d+(\.\d+)?$/;
	  if(!flag && (rate=="0" || rate=='' || isNaN(rate))){
          jAlert('error', "Please enter rate !", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flag=true;
	  }
	  if(!flag && !regexp.test(rate)){
			 jAlert('error', "Please enter rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
				flag=true;
	  }
	 
	  if(!flag && Number(rate)<0){
          		jAlert('error', "Please enter rate !", 'Error Dialog');	
          			setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flag=true;
	  }
	   if(!flag){
            var qty= document.getElementById("qty").value; 
			if(qty=="0" || qty=='' || qty=="0.0"){
		          jAlert('error', "Please enter qty !", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
		         flag=true;
			}else if(Number(qty)<0){
				jAlert('error', "Please enter non negative qty !", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flag=true;
			}else if(!checkNumberOrNotofindentproduct(qty)){
				jAlert('error', "Please enter valid qty !", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flag=true;
			}else{
	            var isfromworkorder ="0";
	            if(document.getElementById("isfromworkorder")){
	            	isfromworkorder = document.getElementById("isfromworkorder").value;
	            }
				$("#dashboardloaderPopup").modal('show');
		        var url="savenewgrnProcurement?product_id="+product_id+"&qty="+qty+"&vendorid="+vendorid+"&warehouse="+warehouse+"&rate="+rate+"&isfromworkorder="+isfromworkorder+""; 
			     if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				 }
				 else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				 }   
			     req.onreadystatechange =saveNewGrnRequest;
			     req.open("GET", url, true); 
			     req.send(null); 
			 }
		} 
	      
}
function saveNewGrnRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	
				//window.location.reload();	   
			getGRNWithPOSelectedList();
         }
	}	 
}
function deleteTempReq(r,table,id,catalogueid) {

    var d=window.confirm("Are you sure to Delete this Entry!");
    
    if(d==true){
    	
    	 var i = r.parentNode.parentNode.rowIndex;
         document.getElementById(table).deleteRow(i);
         
         var url="deletenewgrnProcurement?id="+id+"&catalogueid="+catalogueid+"";  	  
         if (window.XMLHttpRequest) {
    		req = new XMLHttpRequest();
    	 }
    	 else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	 }   
                   
         req.onreadystatechange = deleteTempReqRequest;
         req.open("GET", url, true); 
                  
         req.send(null);  
    } 
    	
 }
 function deleteTempReqRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	
         }
	}	 
}


function checkSubmit(){
	try {
        var flag=false;
	    var selectprod = false;
	    
	    var isfromworkorder ="0";
        if(document.getElementById("isfromworkorder")){
        	isfromworkorder = document.getElementById("isfromworkorder").value;
        }
	    var poDepartmentId = "0";
	    var noteSheetNo = "";
	    var noteSheetDate = "";
	    var durationFrom ="";
	    var durationTo="";
	    var amcCmc="";
	    
	    if(isfromworkorder==1){
	    	poDepartmentId = document.getElementById("poDepartmentId").value;
	 	}
	    if(isfromworkorder==1 || isfromworkorder==2){
	    	noteSheetNo = document.getElementById("noteSheetNo").value;
	 	    noteSheetDate = document.getElementById("noteSheetDate").value;
	    }
	    if(isfromworkorder==2){
	    	durationFrom = document.getElementById("durationFrom").value;
	    	durationTo = document.getElementById("durationTo").value;
	    	amcCmc = document.getElementById("amcCmc").value;
	    }
	    
		var warehouseid = document.getElementById("warehouseid").value;
		if(warehouseid==''){
			jAlert('error', "Please select warehouse !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			return false;
		}else if(warehouseid=='0'){
			jAlert('error', "Please select warehouse !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if(isfromworkorder=='1' && poDepartmentId==0){
			jAlert('error', "Please select Department!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if((isfromworkorder=='1' || isfromworkorder=='2') && noteSheetNo==''){
			jAlert('error', "Please enter note sheet number!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if((isfromworkorder=='1' || isfromworkorder=='2') && noteSheetDate==''){
			jAlert('error', "Please enter note sheet date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if(isfromworkorder=='2' && durationFrom==''){
			jAlert('error', "Please select duration from date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if(isfromworkorder=='2' && durationTo==''){
			jAlert('error', "Please select duration to date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else{
			$('.case').each(function() { 
				var i=this.value;
	        	if(this.checked == true){
					 selectprod = true;
				     var tven= 0;
				     if(document.getElementById("vendoridsss"+i)){
				    	 tven = document.getElementById("vendoridsss"+i).value;
				     }else if(document.getElementById("vendoridsss"+i+"_chosen")){
				    	 tven = document.getElementById("vendoridsss"+i+"_chosen").value;
				     }else{
				    	 window.location.reload();	  
				     }
				     var tqty= document.getElementById("qty"+i).value;
				     var rate= document.getElementById("rate"+i).value;
			      	 var rates= Number(rate);
					 var regexp = /^\d+(\.\d+)?$/;
					 if(isNaN(rate)){
						 jAlert('error', "Please enter rate!", 'Error Dialog');
							setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
							flag=true;
							return false;
					 }
					 if(!regexp.test(rate)){
						 jAlert('error', "Please enter rate!", 'Error Dialog');
							setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
							flag=true;
							return false;
					 }
					 if(rate=='' || rate=='0'){
						 jAlert('error', "Please enter rate!", 'Error Dialog');
							setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
							flag=true;
							return false;
					 }
					 if(rates<0){
						 jAlert('error', "Please enter non negative rate!", 'Error Dialog');
							setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
							flag=true;
							return false;
					 }
				     
				     if(tven=="0"){
				     		jAlert('error', "Please select supplier !", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
							flag=true;
							return false;
				     }
				      if(tqty=="0" || tqty=='' || tqty=="0.0"){
				     		jAlert('error', "Please enter qty !", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
							flag=true;
							return false;
				     }
				      if(Number(tqty)<0){
				     		jAlert('error', "Please enter non negative qty !", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
							flag=true;
							return false;
				     }
				      
				      if(!checkNumberOrNotofindentproduct(tqty)){
							jAlert('error', "Please enter valid qty !", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
							flag=true;
							return false;
						}
				      document.getElementById("status"+i).value = "on";
				      
				      //this.value="on";
				}else{
					document.getElementById("status"+i).value = "off";
				}  
			});
			if(!selectprod){
				jAlert('error', "Please select at least one product !", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flag=true;
			}
		}
	    
	    
          			  
		if(flag){
			return false;
		}else {
			var confirmation = confirm("Are you sure?");
			if (confirmation){
				 $("#dashboardloaderPopup").modal("show");
				 document.getElementById("grnwithposubmitid").submit();
				return true;
			}else{
				return false;
			}
			 
		}
	} catch (e) {
		window.location.reload();	      
	}
}

function deleteIndentRow(r) {
    var i = r.parentNode.parentNode.rowIndex;
    document.getElementById("indenttable").deleteRow(i);
}



function selectAllCheckBox(val){
	
	if (val.checked == true) {
		$('.case').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.case').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}


function addHandover_To(val){
	//var parentid = document.getElementById("parentid").value;
	var handover_to = document.getElementById("handover_to").value;
	if(handover_to==''){
		 jAlert('error', "Plz add name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(handover_to=='0'){
		 jAlert('error', "Plz add name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
		var url = "addHandover_ToProduct?parentid="+val+"&handover_to="+handover_to+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = addHandover_ToRequest;
		req.open("GET", url, true); 
		req.send(null);

	}
}
function addHandover_ToRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 document.getElementById("handover_to_div").innerHTML=req.responseText;
	   }
	}
}


function deleteIndent(val){
	document.getElementById("parent_id").value = val;
	$('#deletemodel').modal( "show" );
}

function deleteIndent1(){
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	if(delete_reason=='' || delete_reason==' ' || delete_reason=='   ' || delete_reason=='    '){
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="deleteindentProduct?parentid="+parentid+"&delete_reason="+delete_reason+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deleteIndent1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function deleteIndent1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function addHandover_ToNew(val){
	//var parentid = document.getElementById("parentid").value;
	var handover_to = document.getElementById("handover_to2").value;
	if(handover_to==''){
		 jAlert('error', "Plz add name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(handover_to=='0'){
		 jAlert('error', "Plz add name!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
		var url = "addHandover_ToNewProduct?parentid="+val+"&handover_to="+handover_to+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = addHandover_ToRequest;
		req.open("GET", url, true); 
		req.send(null);

	}
}
function addHandover_ToNewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 document.getElementById("handover_to_div").innerHTML=req.responseText;
	   }
	}
}

var val1='0';
function getsubcatagory(id,val) {
    val1=val;
    var url="getsubcatagoryCatalogue?id="+id+"&val="+val+"";
   
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
             
   req.onreadystatechange = getsubcatagoryRequest;
   req.open("GET", url, true); 
            
   req.send(null);   
}



function getsubcatagoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("subcatdiv"+val1).innerHTML=req.responseText;
	           $("#subcategory_id"+val1).trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
       }
	}	 
}

function deleteProductRow(r) {
    var i = r.parentNode.parentNode.rowIndex;
   	document.getElementById("mytable").deleteRow(i);
}


var cell;
var row;
var catalogueproductaddcount = "";
function addMoreProductRow(tableID) {
	document.getElementById("addmorecataloguebtn").disabled = true;
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	catalogueproductaddcount = rowCount -1;
	var url = "addnewrowCatalogue?rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = addMoreProductRowRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	    
	    
}


function addMoreProductRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;	
		          $("#category_id"+catalogueproductaddcount).trigger("chosen:updated");
			  	   $(".chosen").chosen({allow_single_deselect: true});
			  	 $("#prodtype"+catalogueproductaddcount).trigger("chosen:updated");
			  	   $(".chosen").chosen({allow_single_deselect: true});
			  	 if(document.getElementById("isautogenericname")){
				  	   $("#generic_name"+catalogueproductaddcount).trigger("chosen:updated");
				  	   $(".chosen").chosen({allow_single_deselect: true});
				  	   $("#mfg"+catalogueproductaddcount).trigger("chosen:updated");
				  	   $(".chosen").chosen({allow_single_deselect: true});
			  	 }
			  	document.getElementById("addmorecataloguebtn").disabled = false;
			 }
		}	 
	}

function getcalsaleprice(val) {
    var mrp = Number(document.getElementById("mrp"+val).value);
    var pack = Number(document.getElementById("pack"+val).value);
    var calsale_price = roundTwo(mrp/pack);
    if(calsale_price=='NaN'){
    	calsale_price=0;
    }
    document.getElementById("sale_price"+val).value= calsale_price;
}


function validateaddproduct()
{	
	if(document.getElementById("location")){
		var location = document.getElementById("location").value;
		if(location=='0'){
			jAlert('error', "Please select location from top filter first!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			return false;		
		}else{
			$("#dashboardloaderPopup").modal('show');
			var y=true;;
		 	$('.akash').each(function() { 
		 		if(y==true){
		 			y =validateaddproducts(this.value);
		 		}
		 	});
		 	
		 	if (y){
				document.getElementById("savaprodform").submit();
			}else{
				$("#dashboardloaderPopup").modal('hide');
				return false;
			}
		 	
		}
	}else{
		$("#dashboardloaderPopup").modal('show');
		var y=true;
	 	$('.akash').each(function() { 
	 		if(y==true){
	 			y =validateaddproducts(this.value);
	 		}
	 	});
	 	
	 	if (y){
			document.getElementById("savaprodform").submit();
		}else{
			$("#dashboardloaderPopup").modal('hide');
			return false;
		}
	}
	
	
}


function validateaddproducts(val){
var category_id = document.getElementById("category_id"+val).value;
var subcategory_id = document.getElementById("subcategory_id"+val).value;
var product_name = document.getElementById("product_name"+val).value;
var pack = document.getElementById("pack"+val).value;
var mrp = document.getElementById("mrp"+val).value;
var purchase_price = document.getElementById("purchase_price"+val).value;
var sale_price = document.getElementById("sale_price"+val).value;
var vat = document.getElementById("vat"+val).value;
var prodtype = document.getElementById("prodtype"+val).value;
var mfg = document.getElementById("mfg"+val).value;
var hsnno = document.getElementById("hsnno"+val).value;
var minorder = document.getElementById("minorder"+val).value;
var maxorder = document.getElementById("maxorder"+val).value;
if(category_id=='0'){
	jAlert('error', "Please select category!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				return false;		
}else if(subcategory_id=='0'){
	jAlert('error', "Please select subcategory!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(prodtype=='0'){
	jAlert('error', "Please select product type!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(product_name==''){
	jAlert('error', "Please enter product name!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(pack=='' || pack=='0'){
	jAlert('error', "Please enter pack!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;	
}else if(!checkNumberOrNotofindentproduct(pack)){
	jAlert('error', "Please enter valid pack!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(mrp=='' || mrp=='0'){
	jAlert('error', "Please enter MRP!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(parseFloat(mrp)<0){
	   jAlert('error', "Please enter valid MRP!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		return false;		
}else if(purchase_price=='' || purchase_price=='0'){
	jAlert('error', "Please enter purchase price!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(parseFloat(purchase_price)<0){
	jAlert('error', "Please enter valid purchase price!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(sale_price=='' || sale_price=='0'){
	jAlert('error', "Please enter sale price!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(parseFloat(sale_price)<0){
	jAlert('error', "Please enter valid sale price!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(mfg==''){
	jAlert('error', "Please enter MGF(manufacturer)!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(vat==''){
	jAlert('error', "Please select GST!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(hsnno==''){
	jAlert('error', "Please enter HSN NO!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(Number(minorder)<0){
	jAlert('error', "Please enter valid min order!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}else if(Number(maxorder)<0){
	jAlert('error', "Please enter valid max order!", 'Error Dialog');	
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration); 
	return false;		
}
else{
	$("#dashboardloaderPopup").modal('show');
	return true;
}
}

function setProductofStore(id) {
	$("#dashboardloaderPopup").modal('show');
	var url="getstoreproductsProduct?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setProductofStoreRequest;
    req.open("GET", url, true); 
    req.send(null);

	
}
function setProductofStoreRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				$("#dashboardloaderPopup").modal('hide');
			   document.getElementById("proddiv").innerHTML=req.responseText;
			   $("#product_id").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
		  	   if(document.getElementById("indentbody")){
		  		   if(modifyIndentStatus==0){
		  			 document.getElementById("indentbody").innerHTML='<tr></tr>';
		  		   }
		  	   }
		  	   if(document.getElementById("isFromGatePass")){
		  		   var warehouse_id = document.getElementById("warehouse_id").value;
		  		   getsupplierListAccordingLocation(warehouse_id);
		  	   }
	        }
		}	 
	}

  function setProdCatandType(id) {
	  
	  var url="setprodcatandtypeProduct?id="+id+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setProdCatandTypeRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	  
  }
  function setProdCatandTypeRequest(){
		if (req.readyState == 4) {
				if(req.status == 200) {
					 var str= req.responseText;
					 var data= str.split("~");
					 document.getElementById("category_id").value= data[0];
					 document.getElementById("subcategory_id").value= data[1];
					 document.getElementById("category_id").className="form-control chosen";
					 document.getElementById("subcategory_id").className="form-control chosen";
					   $("#category_id").trigger("chosen:updated");
				  	   $(".chosen").chosen({allow_single_deselect: true});
				  	 $("#subcategory_id").trigger("chosen:updated");
				  	   $(".chosen").chosen({allow_single_deselect: true});
					 
		         }
			}	 
		}

  
  
  function cancelprocurment(val) {
	 		document.getElementById("pro_id").value = val;
			$('#proccancelmodel').modal("show");
  }

function returntoSupplier() {

	var flag = false;

	$('.case').each(function() {
		if (this.checked == true) {

			var i = this.value;
			var tqty = document.getElementById("qty" + i).value;
			if (tqty == "0" || tqty == "") {
				jAlert('error', "Please enter qty !", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				flag = true;
				return false;
			}
			this.value = "on";

		}
	});

	if (flag) {
		return false;
	} else {
		return true;
	}
}

function setProductofStoreInPO(id) {

	var url = "getstoreproductsProcurement?id=" + id + "";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = setProductofStoreInPORequest;
	req.open("GET", url, true);
	req.send(null);

}
function setProductofStoreInPORequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("proddiv").innerHTML = req.responseText;
			$("#product_id").trigger("chosen:updated");
			$(".chosen").chosen({
				allow_single_deselect : true
			});
		}
	}
}

function cancelRequestedRequest(id,parentid) {
	document.getElementById('invnetorychildid').value = id;
	document.getElementById('invnetoryparentid').value = parentid;
	$('#deletemodel').modal("show");
}

function deleteRequestedEntry() {
	$("#dashboardloaderPopup").modal('show');
	var id = document.getElementById('invnetorychildid').value;
	var delete_reason = document.getElementById('delete_reason').value;
	var parentid = document.getElementById('invnetoryparentid').value;
	if(delete_reason==''){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal('hide');
		var x = confirm("Are You Sure!!");
		if (x) {
			$("#dashboardloaderPopup").modal('show');
			var url = "cancelrequestedentryProduct?id="+id+"&parentid="+parentid+"&delete_reason="+delete_reason+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = deleteRequestedEntryRequest;
			req.open("GET", url, true);
			req.send(null);
		} else {
			return false;
		}
	}
}
function deleteRequestedEntryRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("reqlisttbody").innerHTML = req.responseText;
			window.location.reload();	       
		}
	}
}

function getsubproducttype(id) {
    
    var url="getsubproducttypeProduct?id="+id+"";
   
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
             
   req.onreadystatechange = getsubproducttypeRequest;
   req.open("GET", url, true); 
            
   req.send(null);   
}



function getsubproducttypeRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("subprodtypediv").innerHTML=req.responseText;
	           $("#subcategory").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
       }
	}	 
}

function submitIndentStatementReport(){
	var fromdate = document.getElementById("fromdate").value;
	var todate = document.getElementById("todate").value;
	if(fromdate==''){
		jAlert('error', "Please enter from date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(todate==''){
		jAlert('error', "Please enter to date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		/*var parts = fromdate.split("-");
		fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];
		
		var parts1 = todate.split("-");
		todate = parts1[1] + "/" + parts1[0] + "/" + parts1[2];
		
		var date1 = new Date(fromdate);
		var date2 = new Date(todate);
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
		if(diffDays>=32){
			jAlert('error', "Date differnce is greater one month!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			document.getElementById("transferreportformid").submit();
		}*/
		document.getElementById("transferreportformid").submit();
	}
	
}


function checkdmSubmit(){
	 var voucherno = document.getElementById("voucherno").value;
	    var voucherdate = document.getElementById("voucherdate").value;
	    var security_date= document.getElementById("security_date").value;
	    var security_no =document.getElementById("security_no").value;
    var flag=false;
    var ids="0";
          $('.case').each(function() { 
			if(this.checked == true){
			     var i=this.value;
			     ids = ids+","+i;
			     flag = true;
			}  
		});
          
          document.getElementById("dmgrnids").value =ids;
if(!flag){
    	jAlert('error', "Please select at least one dm !", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
    	return false;
     }else{
    	 if(voucherno==""){
             jAlert('error', "Please enter invoice no!", 'Error Dialog');
    				
    					setTimeout(function() {
    						$("#popup_container").remove();
    						removeAlertCss();
    					}, alertmsgduration);
    					return false;
         } else if(voucherdate==""){
        	 
             jAlert('error', "Please select invoice date!", 'Error Dialog');
    				
    					setTimeout(function() {
    						$("#popup_container").remove();
    						removeAlertCss();
    					}, alertmsgduration); 
    					return false;
         } else if(security_date==""){
         	
             jAlert('error', "Please select security date!", 'Error Dialog');
    				
    					setTimeout(function() {
    						$("#popup_container").remove();
    						removeAlertCss();
    					}, alertmsgduration); 
    					return false;
         } else if(security_no==""){
         	
             jAlert('error', "Please select security no!", 'Error Dialog');
    					setTimeout(function() {
    						$("#popup_container").remove();
    						removeAlertCss();
    					}, alertmsgduration); 
    					return false;
         }
     }    
   
	if(flag){
		checkVendorRepeatVoucherDm();
	}else {
		return false;
	}
}

function checkVendorRepeatVoucherDm(){
		var voucher=document.getElementById("voucherno").value;
		var vendorid= document.getElementById("vendorid").value;
		var url="vendorvoucherexistancefordmProcurement?vendorid="+vendorid+"&voucherno="+voucher+"";
		if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = checkVendorRepeatVoucherDmRequest;
	req.open("GET", url, true); 
	req.send(null);       
}
function checkVendorRepeatVoucherDmRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
			 var data = req.responseText;
			 if(data=='1'){
				 jAlert('error', "Invoice no. already present!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					document.getElementById("voucherno").value="";
			 }else{
				 document.getElementById("dmformsbid").submit();
			 }
    }
}	 
}


function changeRateToCatalogue(val) {
	if(document.getElementById("product_id")){
		 var product_id = document.getElementById("product_id").value;
		 var val1= Number(val);
		 var regexp = /^\d+(\.\d+)?$/;
		 if(isNaN(val)){
			 jAlert('error', "Please enter rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 }else if(!regexp.test(val)){
			 jAlert('error', "Please enter rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 }else if(val=='' || val=='0'){
			 jAlert('error', "Please enter rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 }else  if(val1<0){
			 jAlert('error', "Please enter non negative rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 }else if(product_id=='0'){
		    jAlert('error', "Please select product!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			return false;
		}else{
			 var x = confirm("Are You Sure!! If you change its rate then it reflect to catalogue also.");
	  		 if(x){
	  			var url="changecataloguerateProduct?product_id="+product_id+"&rate="+val+"";
			    if (window.XMLHttpRequest) {
			    	req = new XMLHttpRequest();
			    }else if (window.ActiveXObject) {
			    	isIE = true;
			    	req = new ActiveXObject("Microsoft.XMLHTTP");
			    }   
			    req.onreadystatechange = changeRateToCatalogueRequest;
			    req.open("GET", url, true); 
			    req.send(null); 
	  		}else{
	  			if(document.getElementById("rate")){
	  				document.getElementById("rate").value="0";
	  			}
	  		}
		}
	}else{
		 jAlert('error', "Please select product!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			return false;
	}
}



function changeRateToCatalogueRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "Successfully updated!", 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
       }
		
		//  03 oct 2018 when its come from Procurment dashboard confirm PO popoup
		if(document.getElementById("xtotal")){
			calculateWithPoDisc();
		}
	}	 
}

var isfrompopupmain="0";
function changeRateToCatalogueByProduct(val,product_id,isfrompopup,count) {
	isfrompopupmain = isfrompopup;
	
		 if(val=='' || val=='0'){
			 jAlert('error', "Please enter rate!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 }else if(product_id=='0' || product_id==''){
		    jAlert('error', "Please select product!", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			return false;
		}else{
			 var x = confirm("Are You Sure!! If you change its rate then it reflect to catalogue also.");
	  		 if(x){
	  			var url="changecataloguerateProduct?product_id="+product_id+"&rate="+val+"";
			    if (window.XMLHttpRequest) {
			    	req = new XMLHttpRequest();
			    }else if (window.ActiveXObject) {
			    	isIE = true;
			    	req = new ActiveXObject("Microsoft.XMLHTTP");
			    }   
			    req.onreadystatechange = changeRateToCatalogueByProductRequest;
			    req.open("GET", url, true); 
			    req.send(null); 
	  		}else{
	  			var previusvalue = document.getElementById("previouspurprice"+count).value;
	  			document.getElementById("rate"+count).value= previusvalue;
	  		}
		}
	
}



function changeRateToCatalogueByProductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			jAlert('success', "Successfully updated!", 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			if(isfrompopupmain=='1'){
				setAllConfirmData();
			}
       }
	}	 
}

//checkProductCodeExist

function checkProductCodeExist(val){
		var dataObj={
			  	"val":""+val+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "checkproductcodeexistCatalogue",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			  var condition= data.val1;
			  if(condition=='1'){
				  jAlert('error', "Product code already exist!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("pro_code").value="";
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

function deleteMultipleTempPo() {
	var flag= false;
	var ids="0";
	$('.dcase').each(function() {
		if (this.checked == true) {
			var i = this.value;
			flag = true;
			var catalogueid = document.getElementById("catalogueidold"+i).value;
			ids = ids +","+ catalogueid;
		}
	});
	
	if(!flag){
		jAlert('error', "Select at least one product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	var d=window.confirm("Are you sure to Delete this Entry!");
    if(d==true){
	    	
    		 var url="deletemultiplenewgrnProcurement?ids="+ids+"";  	  
	         if (window.XMLHttpRequest) {
	    		req = new XMLHttpRequest();
	    	 }
	    	 else if (window.ActiveXObject) {
	    		isIE = true;
	    		req = new ActiveXObject("Microsoft.XMLHTTP");
	    	 }   
	                   
	         req.onreadystatechange = deleteMultipleTempPoRequest;
	         req.open("GET", url, true); 
	                  
	         req.send(null);  
	    } 
	}	
 }
 function deleteMultipleTempPoRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();	
         }
	}	 
}
 
 function completePendingProcurmentPO(val) {
		document.getElementById("com_pending_pro_id").value = val;
		$('#proccompletepomodel').modal("show");
	}
 
 function validateReasonofPOComplete() {
	 var flag = false;
		var reason = document.getElementById("completependingpo").value;
		if(reason==''){
			jAlert('error', "Please enter reason for completing PO without receiving product!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			 var d=window.confirm("Are you sure to complete PO without receiving product!");
			 if(d==true){
				 document.getElementById("completepoform").submit();
			 }
		}
	}
 
 function getreplaceindentproductpopup(catalogueid,id,parentid){
	 var indentlocationid =document.getElementById("indentlocationid").value;
	 var dataObj={
			  	"catalogueid":""+catalogueid+"",
			  	"indentlocationid":""+indentlocationid+"",
			  	"parentid":""+parentid+"",
	 };
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "getreplaceindentproductlistProduct",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		  document.getElementById("indent_change_catlist_div").innerHTML =data.cataloguelist;
		  document.getElementById("changecatindent_productname").innerHTML =data.cataloguename;
		  document.getElementById("change_indent_oldcatid").value =catalogueid;
		  document.getElementById("change_indent_childid").value =id;
		  document.getElementById("change_indent_parentid").value =parentid;
		  $("#indent_changecatlid").trigger("chosen:updated");
	  	  $(".chosen").chosen({allow_single_deselect: true});
		  $("#changeindentprodmodel").modal('show');
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
 
 function savechangeindentproductdata(){
	 var change_indent_oldcatid =document.getElementById("change_indent_oldcatid").value;
	 var change_indent_childid =document.getElementById("change_indent_childid").value;
	 var change_indent_parentid =document.getElementById("change_indent_parentid").value;
	 var indent_changecatlid =document.getElementById("indent_changecatlid").value;
	 var change_indent_comment =document.getElementById("change_indent_comment").value;
	 
	 if(indent_changecatlid=="0"){
		 jAlert('error', "Please select product!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	 }else if(change_indent_comment==''){
	    jAlert('error', "Please enter remark!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		 var dataObj={
				  	"change_indent_oldcatid":""+change_indent_oldcatid+"",
				  	"change_indent_childid":""+change_indent_childid+"",
				  	"change_indent_parentid":""+change_indent_parentid+"",
				  	"indent_changecatlid":""+indent_changecatlid+"",
				  	"change_indent_comment":""+change_indent_comment+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "savereplaceindentproductlistProduct",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   window.location.reload();	    
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
 
 
 function calofInventoryOpening(){
	 var fromdate =document.getElementById("fromdate").value;
	 var todate =document.getElementById("todate").value;
	 var searchbyprodname =document.getElementById("searchbyprodname").value;
	 var location_filter =document.getElementById("location_filter").value;
	 
	 var dataObj={
	  	"fromdate":""+fromdate+"",
	  	"todate":""+todate+"",
	  	"searchbyprodname":""+searchbyprodname+"",
	  	"location_filter":""+location_filter+"",
	 };
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "calculateinventoryopeingProduct",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   document.getElementById("pharmacysalepricediv").innerHTML ="Rs."+data.pharmacysaleprice;
		   document.getElementById("directsalepricediv").innerHTML ="Rs."+data.directsaleprice;
		   document.getElementById("requestsalepricediv").innerHTML ="Rs."+data.requestsaleprice;
		   document.getElementById("returnsalepricediv").innerHTML ="Rs."+data.returnsaleprice;
		   document.getElementById("returnsupliersalepricediv").innerHTML ="Rs."+data.returnsupliersaleprice;
		   document.getElementById("consumesalepricediv").innerHTML ="Rs."+data.consumesaleprice;
		   document.getElementById("adjustsalepricediv").innerHTML ="Rs."+data.adjustsaleprice;
		   $("#salepricetotalmodel").modal('show');
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

 
 function addnewcatalogueproduct() {
	 if(document.getElementById("location")){
			var location = document.getElementById("location").value;
			if(location=='0'){
				jAlert('error', "Please select location from top filter first!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				$("#addproduct").modal('show');
			}
	}else{
		$("#addproduct").modal('show');
	}
}
 
 function chkNameExistIncatalogue(d) {
		var warehouse=document.getElementById("warehouse").value;
		if(warehouse=='' || warehouse=='0'){
			 jAlert('error', "Please select warehouse!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				document.getElementById("prodname").value ='';
		 }else{
				obj=d;
				var url="catnameexistnewCatalogue?name="+d.value+"&warehouse="+warehouse+"";
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = chkNameExistInCatalogueRequest;
			    req.open("GET", url, true); 
			    req.send(null);
		 }
	}
	function chkNameExistInCatalogueRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					 
					 var res= req.responseText;
					 if(res=="1"){
						    
						 obj.value='';
						 jAlert('error', "Product Name Exist!", 'Error Dialog');	
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
					 }	
					 
		         }
			}
		}

	function calofInventoryOpeningInvalue(){
		 var fromdate =document.getElementById("fromdate").value;
		 var todate =document.getElementById("todate").value;
		 var searchbyprodname =document.getElementById("searchbyprodname").value;
		 var location_filter =document.getElementById("location_filter").value;
		 
		 var dataObj={
		  	"fromdate":""+fromdate+"",
		  	"todate":""+todate+"",
		  	"searchbyprodname":""+searchbyprodname+"",
		  	"location_filter":""+location_filter+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "calculateinventoryopeinginvalueProduct",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   document.getElementById("purchasevaluediv").innerHTML ="Rs."+data.purchasevalue;
			   document.getElementById("pharmacyreturnvaluediv").innerHTML ="Rs."+data.pharmacyreturnvalue;
			   document.getElementById("directtranfervaluediv").innerHTML ="Rs."+data.directtranfervalue;
			   document.getElementById("requesttransfervaluediv").innerHTML ="Rs."+data.requesttransfervalue;
			   document.getElementById("returntostorevaluediv").innerHTML ="Rs."+data.returntostorevalue;
			   document.getElementById("adjustmentvaluediv").innerHTML ="Rs."+data.adjustmentvalue;
			   document.getElementById("purchasefreevaluediv").innerHTML ="Rs."+data.purchasefreevalue;
			   $("#qtyinvaluetotalmodel").modal('show');
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
	
	 function calofInventoryOpeningOutward(){
		 var fromdate =document.getElementById("fromdate").value;
		 var todate =document.getElementById("todate").value;
		 var searchbyprodname =document.getElementById("searchbyprodname").value;
		 var location_filter =document.getElementById("location_filter").value;
		 
		 var dataObj={
		  	"fromdate":""+fromdate+"",
		  	"todate":""+todate+"",
		  	"searchbyprodname":""+searchbyprodname+"",
		  	"location_filter":""+location_filter+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "calculateinventoryopeingoutwardProduct",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   document.getElementById("pharmacysalepricediv1").innerHTML ="Rs."+data.pharmacysaleprice;
			   document.getElementById("directsalepricediv1").innerHTML ="Rs."+data.directsaleprice;
			   document.getElementById("requestsalepricediv1").innerHTML ="Rs."+data.requestsaleprice;
			   document.getElementById("returnsalepricediv1").innerHTML ="Rs."+data.returnsaleprice;
			   document.getElementById("returnsupliersalepricediv1").innerHTML ="Rs."+data.returnsupliersaleprice;
			   document.getElementById("consumesalepricediv1").innerHTML ="Rs."+data.consumesaleprice;
			   document.getElementById("adjustsalepricediv1").innerHTML ="Rs."+data.adjustsaleprice;
			   $("#outwardvaluetotalmodel").modal('show');
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
	 
	 function checkProductCodeExistNew(count,val){
			if(val!=''){
				var dataObj={
					  	"val":""+val+"",
				};
				var data1 =  JSON.stringify(dataObj);
				$.ajax({
				   url : "checkproductcodeexistCatalogue",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					  var condition= data.val1;
					  if(condition=='1'){
						  jAlert('error', "Product code already exist!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
							document.getElementById("pro_code"+count).value="";
							
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
	
	function checkProductCodeExistSingle(val){
		if(val!=''){
			var dataObj={
				  	"val":""+val+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "checkproductcodeexistCatalogue",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				  var condition= data.val1;
				  if(condition=='1'){
					  jAlert('error', "Product code already exist!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						document.getElementById("pro_code").value="";
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
	 function checkNumberOrNotofindentproduct(inputtxt)
	 {
	 	 //var numbers = /^[0-9]+$/;  
	 	 var numbers =  /^-{0,1}\d*\.{0,1}\d+$/;
	      
	      if(inputtxt.match(numbers))  {
	         return true;
	      } else {
	           return false;
	      }
	 } 
	 
	function callGenericNameMaster() {
		openBlankPopup('genericnameMaster?selectedid=77');
	}
	function callMfgMaster() {
		openBlankPopup('listmfgMaster?selectedid=76');
	}
	
	function resetAddproductdata() {
		 document.getElementById("prodname").value = "";
		 document.getElementById("pack").value = "";
		 //document.getElementById("subcategory").value ="";
		 document.getElementById("productcategoryid").value="";
		 if(document.getElementById("pro_code")){
			 document.getElementById("pro_code").value=""; 
		 }
		/* document.getElementById("genericname").className ="";
		 document.getElementById("genericname").value='';
		 document.getElementById("genericname").className ="form-control chosen";
		 $("#genericname").trigger("chosen:updated");
	  	 $(".chosen").chosen({allow_single_deselect: true});*/
	  	 
	  /*	document.getElementById("subcategory").className ="";
		 document.getElementById("subcategory").value="0";
		 document.getElementById("subcategory").className ="form-control chosen";
		 $("#subcategory").trigger("chosen:updated");
	  	 $(".chosen").chosen({allow_single_deselect: true});
	  	 
	  	document.getElementById("productcategoryid").className ="";
		 document.getElementById("productcategoryid").value="0";
		 document.getElementById("productcategoryid").className ="form-control chosen";
		 $("#productcategoryid").trigger("chosen:updated");
	  	 $(".chosen").chosen({allow_single_deselect: true});
	  	 
	  	document.getElementById("addgst").className ="";
		 document.getElementById("addgst").value="";
		 document.getElementById("addgst").className ="form-control chosen";
		 $("#addgst").trigger("chosen:updated");
	  	 $(".chosen").chosen({allow_single_deselect: true});*/
	}
	
	function replaceallindentproduct(str, find, replace) {
		var x=true;
	    while(x){
	    	if(str.includes(find)){
	    		str=str.replace(find, replace);
	    	}else{
	    		x=false;
	    	}
	    }
	    return str;
	}
	
	
	function getsubcatagoryinitemwise(id) {
	    var url="getsubcatagoryinitemwiseCatalogue?id="+id+"";
	   
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	             
	   req.onreadystatechange = getsubcatagoryinitemwiseRequest;
	   req.open("GET", url, true); 
	            
	   req.send(null);   
	}



	function getsubcatagoryinitemwiseRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          document.getElementById("subcategorydiv").innerHTML=req.responseText;
		           $("#subcategoryid").trigger("chosen:updated");
				   $(".chosen").chosen({allow_single_deselect: true});
	       }
		}	 
	}
	
	function totalclosingstockpop(){
		 $("#closing_stock_popup").modal('show');
	}
	
	
	function getGRNWithPOSelectedList(){
		 var isfromworkorder ="0";
         if(document.getElementById("isfromworkorder")){
         	isfromworkorder = document.getElementById("isfromworkorder").value;
         }
			var val='0';
			var dataObj={
				  	"val":""+val+"",
				  	"isfromworkorder":""+isfromworkorder+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "getgrnwithposelectedlistPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				  var listdata= data.list5;
				  $("#dashboardloaderPopup").modal('hide');
				  document.getElementById("tbodygrnwithpo").innerHTML =listdata;
				  $('.case').each(function() { 
					 $("#vendoridsss"+this.value).trigger("chosen:updated");
					 $(".chosen").chosen({allow_single_deselect: true});
				  });
				  calculateLongPoTotal();
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
	
	function calculateLongPoTotal(){
		var totalRate = 0;
		var totalQty = 0;
		var gsttotal =0;
		var total=0;
		$('.case').each(function() {
			if (this.checked == true) {
				var i = this.value;
				var rate = document.getElementById("rate"+i).value;
				var qty = document.getElementById("qty"+i).value;
				qty = parseFloat(qty);  
				rate = parseFloat(rate);
				if (isNaN(rate)) {
					rate = 0;
				}
				if (isNaN(qty)) {
					qty = 0;
				}
				totalRate = totalRate + rate;
				totalQty = totalQty + qty;
				var totalAmt = rate * qty; 
				total = total + totalAmt;
				
				var vat = Number(document.getElementById("vat"+ i).value);
				var tempvatamount = parseFloat(rate) * parseFloat(vat)/ 100;
				tempvatamount = tempvatamount * qty;
				gsttotal = gsttotal + tempvatamount;
			}
		});
		//document.getElementById("longPoRateTotal").innerHTML=totalRate;
		//document.getElementById("longPoQtyTotal").innerHTML=totalQty;
		document.getElementById("longPoTotalAmt").innerHTML=total;
		
		var temptotal = total;
		var po_disc = document.getElementById("po_disc").value;
		var discratenew = (po_disc / total) * 100.0;
		var tempgst = gsttotal * discratenew /100;
		gsttotal = gsttotal - tempgst;
		document.getElementById('longGstAmt').innerHTML = roundTwo(gsttotal);
		var netamount = temptotal - po_disc;
		netamount = netamount + gsttotal; 
		document.getElementById('longnetAmt').innerHTML = roundAbs(netamount);
		
	}
	
	function saveintotemppodata(i,colname,val,tempdatagrnid){
		$("#dashboardloaderPopup").modal('show');
		
		var dataObj={
			"val":""+val+"",
			"colname":""+colname+"",
			"tempdatagrnid":""+tempdatagrnid+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "updatintotemppodataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			    var listdata= data.list5;
			    $("#dashboardloaderPopup").modal('hide');
			  	jAlert('success', "Successfully changed!", 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				calculateLongPoTotal();
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
	
function saveintotemppodata_rate(i,colname,val,tempdatagrnid) {
	 var flag = false;
	 var val1= Number(val);
	 var regexp = /^\d+(\.\d+)?$/;
	 if(isNaN(val)){
		 jAlert('error', "Please enter rate!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
			flag=true;
			return false;
	 }else if(!regexp.test(val)){
		 jAlert('error', "Please enter rate!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
			flag=true;
			return false;
	 }else if(val=='' || val=='0'){
		 jAlert('error', "Please enter rate!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
			flag=true;
			return false;
	 }else  if(val1<0){
		 jAlert('error', "Please enter non negative rate!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
			flag=true;
			return false;
	 }
	
     if(!flag){
    	 saveintotemppodata(i,colname,val,tempdatagrnid)
     }
}

function saveintotemppodata_qty(i,colname,val,tempdatagrnid) {
	var flag = false;
	var tqty= val;
    if(tqty=="0" || tqty==''){
    		jAlert('error', "Please enter qty !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			flag=true;
			return false;
    }
     if(Number(tqty)<0){
    		jAlert('error', "Please enter non negative qty !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			flag=true;
			return false;
    }
    if(!flag){
      var maxorder= Number(document.getElementById("maxorder"+i).value);
  	  var minorder= Number(document.getElementById("minorder"+i).value);
  	  if(val>maxorder){
  		  jAlert('Success', "Product is Crossing Max Order Guidelines > "+maxorder+" !!!", 'Success Dialog');
  			setTimeout(function() {
  				$("#popup_container").remove();
  				removeAlertCss();
  			}, 2000); 
  	  }else if(val<minorder){
  		  jAlert('Success', "Product is Below Min Order Guidelines > "+minorder+" !!!", 'Success Dialog');
  		  setTimeout(function() {
  				$("#popup_container").remove();
  				removeAlertCss();
  			}, 2000); 
  	  }
  		  saveintotemppodata(i,colname,val,tempdatagrnid)
  	  
    	
    }
}

function saveintotemppodata_poHsnno(i,colname,val,tempdatagrnid) {
	saveintotemppodata(i,colname,val,tempdatagrnid);
}

function saveintotemppodata_poDescription(i,colname,val,tempdatagrnid){
	saveintotemppodata(i,colname,val,tempdatagrnid);
}

function deletecompltedpo() {
	var data= document.getElementById("delete_reason").value;
	if(data=='' || data==' ' || data=='  '){
		jAlert('error', "Please enter cancel reason !", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
	 	var x = confirm("Are you sure want to cancel GRN!!");
		if (x) {
			$("#dashboardloaderPopup").modal('show');
			document.getElementById("cancelpopopupform").submit();
		}
	}
}

function deleteIndentPOListTempReq(r,table,id,catalogueid) {

    var d=window.confirm("Are you sure to Delete this Entry!");
    
    if(d==true){
    	
    	 var i = r.parentNode.parentNode.rowIndex;
         document.getElementById(table).deleteRow(i);
         
         var url="deletenewgrnfromindentProcurement?id="+id+"";  	  
         if (window.XMLHttpRequest) {
    		req = new XMLHttpRequest();
    	 }
    	 else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	 }   
                   
         req.onreadystatechange = deleteIndentPOListTempReqRequest;
         req.open("GET", url, true); 
                  
         req.send(null);  
    } 
    	
 }
 function deleteIndentPOListTempReqRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	
         }
	}	 
}
 
 function deleteadjustmentrequset(id) {

	    var d=window.confirm("Are you sure to Delete this Entry!");
	    
	    if(d==true){
	    	
	         var url="deleteadjustmentrequestProduct?id="+id+"";  	  
	         if (window.XMLHttpRequest) {
	    		req = new XMLHttpRequest();
	    	 }
	    	 else if (window.ActiveXObject) {
	    		isIE = true;
	    		req = new ActiveXObject("Microsoft.XMLHTTP");
	    	 }   
	                   
	         req.onreadystatechange = deleteadjustmentrequsetRequest;
	         req.open("GET", url, true); 
	                  
	         req.send(null);  
	    } 
	    	
	 }
	 function deleteadjustmentrequsetRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	   
	         }
		}	 
	}

function setinventorynoninventory(val) {
	if(val=='' || val=='1'){
		document.getElementById("issueproductenter").className ="col-lg-3 col-md-3 col-xs-3";
		document.getElementById("issueproductselect").className ="col-lg-3 col-md-3 col-xs-3 hidden";
	}else{
		var dataObj={
			"issue_date":"1",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getcataloguelistforisssuePharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   document.getElementById("issueproductselect").innerHTML =data.cataloguelist;
			   $("#issue_catalogueid").trigger("chosen:updated");
			   $(".chosen-select").chosen({allow_single_deselect: true});
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
		
		
		document.getElementById("issueproductenter").className ="col-lg-3 col-md-3 col-xs-3 hidden";
		document.getElementById("issueproductselect").className ="col-lg-3 col-md-3 col-xs-3";
	}
}

function addissuejobwork(){
	$("#dashboardloaderPopup").modal('show');
	var issue_date=document.getElementById("issue_date").value;
	var issue_vendorid = document.getElementById("issue_vendorid").value;
	var issue_isinventory = document.getElementById("issue_isinventory").value;
	var qty=document.getElementById("issue_qty").value;
	var exp_date=document.getElementById("issue_expected_date").value;
	var issue_catalogueid = document.getElementById("issue_catalogueid").value;
	var issue_productname = document.getElementById("issue_productname").value;
	var isnumberqty = checkNumberOrNotInIndent(qty);
	var flag = false;
	var flagerror=false;
	if(issue_isinventory=='0'){
		if(issue_catalogueid=='0'){
			flag = true;
		}
	}else if(issue_isinventory=='1'){
		if(issue_productname=='1'){
			flagerror =true;
		}
	}
	
	var flagstatus=false;
	if(issue_date=='' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select issue date!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(issue_vendorid=='0' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select supplier!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration); 
	}else if(issue_isinventory=='' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select product type!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration); 
	}else if(flag==true && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select product!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
		}, alertmsgduration); 
	}else if(flagerror==true && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please enter product!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
		}, alertmsgduration);
	}else if(qty=='' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		 jAlert('error', "Please enter quantity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(!isnumberqty && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		 jAlert('error', "Please enter valid qunatity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(qty=='0' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		 jAlert('error', "Please enter quantity greater than 0!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(exp_date=='' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		 flagstatus=true;
		 jAlert('error', "Please enter expected date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
			var dataObj={
				"issue_date":""+issue_date+"",
				"issue_vendorid":""+issue_vendorid+"",
				"issue_isinventory":""+issue_isinventory+"",
				"issue_catalogueid":""+issue_catalogueid+"",
				"issue_productname":""+issue_productname+"",
				"qty":""+qty+"",
				"exp_date":""+exp_date+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "addtempjobworkissuedataPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   	var listdata= data.checkalreadyproduct;
				   	if(listdata==1){
				   		$("#dashboardloaderPopup").modal('hide');
				   		jAlert('error', "Product already added!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   	}else{
				   		getTempJobWorkIssueData();
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

function openIssueForJobWork(){
		$("#dashboardloaderPopup").modal('show');
		var dataObj={
			"issue_date":"1",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "deletetempjobworkissuedataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			    document.getElementById("issue_date").value='';
			    document.getElementById("issue_vendorid").className='';
				document.getElementById("issue_vendorid").value='0';
				document.getElementById("issue_vendorid").className='form-control chosen-select';
				document.getElementById("issue_isinventory").className='';
				document.getElementById("issue_isinventory").value='';
				document.getElementById("issue_isinventory").className='form-control chosen-select';
				document.getElementById("issue_qty").value='';
				document.getElementById("issue_expected_date").value='';
				document.getElementById("issue_productname").value='';
				document.getElementById("issueproductenter").className ="col-lg-3 col-md-3 col-xs-3";
				document.getElementById("issueproductselect").className ="col-lg-3 col-md-3 col-xs-3 hidden";
				
				$("#issue_vendorid").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				$("#dashboardloaderPopup").modal('hide');
			  	$('#ireq').modal( "show" );
			  	getTempJobWorkIssueData();
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

function getTempJobWorkIssueData() {
	//$("#dashboardloaderPopup").modal('show');
	var dataObj={
		"issue_date":"1",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "gettempjobworkissuedataPharmacyAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   document.getElementById("jobworkissuebody").innerHTML=data.tempjobtitledada;
		   var flag=true;
		   $('.dclass').each(function() {
			   flag= false;
			   var temp_remark= document.getElementById("isssue_rmark_temp"+this.value).value;
			   document.getElementById("isssue_rmark"+this.value).value=temp_remark;
		   });
		   if(!flag){
			   document.getElementById("issue_date").disabled = true;
			   document.getElementById("issue_vendorid").className='';
			   document.getElementById("issue_vendorid").disabled = true;
			   document.getElementById("issue_vendorid").className='form-control chosen-select';
			   $("#issue_vendorid").trigger("chosen:updated");
			   $(".chosen-select").chosen({allow_single_deselect: true});
		   }else{
			   document.getElementById("issue_date").disabled = false;
			   document.getElementById("issue_vendorid").className='';
			   document.getElementById("issue_vendorid").disabled = false;
			   document.getElementById("issue_vendorid").className='form-control chosen-select';
			   $("#issue_vendorid").trigger("chosen:updated");
			   $(".chosen-select").chosen({allow_single_deselect: true});
		   }
		   $("#dashboardloaderPopup").modal('hide');
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

function updatetempjobworkissue(id,val){
	//$("#dashboardloaderPopup").modal('show');
	var dataObj={
		"id":""+id+"",
		"val":""+val+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "updatetempjobworkissuedataPharmacyAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   //$("#dashboardloaderPopup").modal('hide');
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

/*function updatetempjobworkissue(id,val){
	$("#dashboardloaderPopup").modal('show');
	var dataObj={
		"id":""+id+"",
		"val":""+val+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "updatetempjobworkissuedataPharmacyAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   $("#dashboardloaderPopup").modal('hide');
	   },
	   error : function(result) {
		   jAlert('error', "Something wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	   }
	}); 
}*/
function deletetempjobworkissue(id){
	$("#dashboardloaderPopup").modal('show');
	var dataObj={
		"id":""+id+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "deletetempjobworkissueentryPharmacyAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		   getTempJobWorkIssueData();
		   $("#dashboardloaderPopup").modal('hide');
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

function saveIssueJobWork(){
	$("#dashboardloaderPopup").modal('show');
	var issue_date=document.getElementById("issue_date").value;
	var issue_vendorid = document.getElementById("issue_vendorid").value;
	var flag=true;
	var flagremark= false;
	$('.dclass').each(function() {
		flag= false;
		var isssue_rmark = document.getElementById("isssue_rmark"+this.value).value;
		if(isssue_rmark==''){
			flagremark=true;
		}
	});
	
	var flagstatus=false;
	if(issue_date=='' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select issue date!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(issue_vendorid=='0' && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please select supplier!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration); 
	}else if (flag  && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please add at least one product!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration);
	}else if(flagremark==true && flagstatus==false){
		$("#dashboardloaderPopup").modal('hide');
		flagstatus=true;
		jAlert('error', "Please enter issue remark!", 'Error Dialog');	
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration);
	}else{
			var dataObj={
				"issue_date":""+issue_date+"",
				"issue_vendorid":""+issue_vendorid+"",	
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "savetempjobworkissuedataPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   window.location.reload();	     					  	
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

function openReceiptForJobWork(){
	$("#dashboardloaderPopup").modal('show');
	document.getElementById("jobworkreceiptbody").innerHTML="<tr></tr>";
	document.getElementById("receipt_vendorid").className="";
	document.getElementById("receipt_vendorid").className="";
	document.getElementById("receipt_vendorid").className="";
	
	document.getElementById("receipt_vendorid").className='';
	document.getElementById("receipt_vendorid").value = '0';
	document.getElementById("receipt_vendorid").className='form-control chosen-select';
	$("#receipt_vendorid").trigger("chosen:updated");
	$(".chosen-select").chosen({allow_single_deselect: true});
	$('#ireceipt').modal( "show" );
	$("#dashboardloaderPopup").modal('hide');
}

function checkreceiptjobwork(){
	$("#dashboardloaderPopup").modal('show');
	var receipt_vendorid = document.getElementById("receipt_vendorid").value;
	if(receipt_vendorid=='0'){
		$("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please select supplier!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else{
			var dataObj={
				"receipt_vendorid":""+receipt_vendorid+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "checkreceiptjobworkPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   	var listdata= data.productreceiptlist;
				   	document.getElementById("jobworkreceiptbody").innerHTML=listdata;
				   	$('.receiptclass').each(function() {
				   		val = this.value;
				   		$("#receipt_status"+val).trigger("chosen:updated");
						$(".chosen-select").chosen({allow_single_deselect: true});
					});
				   	
				   	$("#dashboardloaderPopup").modal('hide');
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

function saveReceiptJobWork(){
	var selectproduct=false;
	var selectstatus=false;
	var selectcomment=false;
	$('.receiptclass').each(function() {
   		val = this.value;
   		var receipt_status=document.getElementById("receipt_status"+val).value;
   		var receipt_comment = document.getElementById("receipt_comment"+val).value;
   		if(receipt_status=='1' || receipt_status=='2'){
   			selectproduct = true;
   		}
   		if(receipt_comment=='' && (receipt_status=='1' || receipt_status=='2')){
   			selectcomment = true;
   		}
	});
	if(!selectproduct){
		jAlert('error', "Please select at least one product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(selectcomment){
		jAlert('error', "Please enter remark!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal('hide');
	 	var x = confirm("Are You Sure!!");
		if(x){
			$("#dashboardloaderPopup").modal('show');
			document.getElementById("receiptform").submit();
		}
	}
}

function closeissuejobwork() {
	$("#ireq").modal('hide');
	window.location.reload();
}

function deleteJobwork(val){
	document.getElementById("parent_id").value = val;
	$('#deletemodel').modal( "show" );
}

function deleteJobWork1(){
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	if(delete_reason=='' || delete_reason==' ' || delete_reason=='   ' || delete_reason=='    '){
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="deletejobworkPharmacyAjax?parentid="+parentid+"&delete_reason="+delete_reason+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deleteJobWork1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function deleteJobWork1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}


function changecolorofreceipt(val,id) {
	if(val=='0'){
		document.getElementById("receipt_tr"+id).style.color="RGBA(85, 85, 85, 0.85)";
	}else if(val=='1' || val=='2'){
		document.getElementById("receipt_tr"+id).style.color="black";
	}
}

function setProductofStoreOCR(id) {

	var url="setproductofStoreOCRProduct?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setProductofStoreOCRRequest;
    req.open("GET", url, true); 
    req.send(null);

	
}
function setProductofStoreOCRRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				  document.getElementById("proddiv").innerHTML=req.responseText;
				   $("#product_id").trigger("chosen:updated");
			  	   $(".chosen").chosen({allow_single_deselect: true});
			  	   
	         }
		}	 
	}
function calculateLongPoDisc(val){
	var subTotal = document.getElementById("longPoTotalAmt").innerHTML;
	if(parseFloat(val)<0){
		document.getElementById("po_disc").value=0;
		jAlert('error', "Please enter valid discount!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(parseFloat(val)>parseFloat(subTotal)) {
		document.getElementById("po_disc").value=0;
		jAlert('error', "Please enter valid discount!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		calculateLongPoTotal();
	}
	
}

function addNewGatePassProduct(tableId){
	 var table = document.getElementById(tableId);
	 var rowCount = table.rows.length;
	 var pid=document.getElementById("product_id").value;
	 var qty=document.getElementById("qty").value;
	 var warehouse_id = document.getElementById("warehouse_id").value;
	 var supplierId = document.getElementById("supplierId").value;
	 var isnumberqty = checkNumberOrNotInIndent(qty);
	 var flag = false;
	 var data ='0';
	 $('.dclass').each(function() {
        var i=this.value;
        if(i==pid){
       	 flag = true;
       	 
        }
        data = data +","+this.value;
	 });
	 var flagstatus=false;
	if(warehouse_id=='0' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please select store!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(supplierId==0 && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please select supplier!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(pid=='0' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please select product!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(qty=='' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter quantity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(!isnumberqty && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter valid qunatity!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(qty=='0' && flagstatus==false){
		flagstatus=true;
		 jAlert('error', "Please enter quantity greater than 0!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	}else if(flag==false){
		 row=table.insertRow(rowCount);
		 rowCount--;
	     var url="addnewgatepassProduct?count="+rowCount+"&pid="+pid+"&qty="+qty+"&data="+data+"&warehouse_id="+warehouse_id;
	   
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	               
	     req.onreadystatechange = addNewGatePassProductRequest;
	     req.open("GET", url, true); 
	              
	     req.send(null);   
	 }else{
		 jAlert('error', "Already added this item!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }
	 
}
function addNewGatePassProductRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			if(data=='0'){
				 jAlert('error', "Already added this product!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
			}else if(data=='1'){
				jAlert('error', "Location is yet to be allocated to your ID. Please co-ordinate support team.!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				 row.innerHTML=req.responseText;
			}
        }
	}	 
}

function saveGatePass() {
	$("#dashboardloaderPopup").modal('show');
	var total=0;
	 $('.dclass').each(function() {
          var i=this.value;
         // total=total+i;   
          total=total+","+this.value;
          var comment = document.getElementById("comment"+i).value;
          if(comment!=''){
        	  var str = replaceallindentproduct(comment,"'",' ');
              document.getElementById("comment"+i).value= str;
              str = replaceallindentproduct(str,'"',' ');
              document.getElementById("comment"+i).value= str;
          }
     });
	 
	 var gatePassType = document.getElementById("gatePassType").value;
	 var date = document.getElementById("date").value;
	 var vehicleNumber= document.getElementById("vehicleNumber").value;
	 var dateOfReturn= document.getElementById("dateOfReturn").value;
	 var approximateValue = document.getElementById("approximateValue").value;
	 var warehouse_id = document.getElementById("warehouse_id").value;
	 var supplierId = document.getElementById("supplierId").value;
	 var flag = false;
	 if(gatePassType==''){
		 flag = true;
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please select gate pass type!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(!flag && date==''){
		 flag = true;
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter date!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(!flag && vehicleNumber==''){
		 flag = true;
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter vehicle number!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(flag && dateOfReturn==''){
		 flag = true;
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please select date of return!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(!flag && approximateValue==''){
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter approximate value!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(flag && warehouse_id==''){
		 flag = true;
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please enter approximate value!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else if(total==0){
		 $("#dashboardloaderPopup").modal('hide');
		 jAlert('error', "Please add at least one product!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	 }else{
		 $("#dashboardloaderPopup").modal('hide');
		 	var x = confirm("Are You Sure!!");
			if(x){
				$("#dashboardloaderPopup").modal('show');
				document.getElementById("allproductid").value= total;
			    document.getElementById("indentform").submit();
			}
		 
	 }
	 
}

function getsupplierListAccordingLocation(warehouse_id){
	var dataObj ={
			"warehouse_id" : ""+warehouse_id+"",
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url : "getsupplierlocationPharmacyAjax",
		data : ""+data+"",
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		async : true,
		success : function(data) {
			document.getElementById("supplierdiv").innerHTML=data.vendorList;
			$("#supplierId").trigger("chosen:updated");
		  	$(".chosen").chosen({allow_single_deselect: true});
		},
		error : function(result) {
			alert("error");
		}
		
		
	});
}

var modifyIndentStatus=0;
function modifyIndentRequest(parentid){
	document.getElementById("saveIndentButton").className='hidden';
	document.getElementById("saveIndentButton1").className='';
	/* var table = document.getElementById('indenttable');
	 var rowCount = table.rows.length;
	 row=table.insertRow(rowCount);
	 rowCount--;*/
	 var url="modifyindnetrequestProduct?parentid="+parentid+"";
	 
	 if (window.XMLHttpRequest) {
		 req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		 isIE = true;
		 req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	 req.onreadystatechange = modifyIndentRequestResponse;
	 req.open("GET", url, true); 
	 req.send(null);   
	 
}
function modifyIndentRequestResponse(){
if (req.readyState == 4) {
	if (req.status == 200) {
		modifyIndentStatus = 1;
		var str = req.responseText;
		var data=str.split("~~~");
		document.getElementById("indentbody").innerHTML = data[0];
		document.getElementById("parentTransferId").value= data[1];
		document.getElementById("parentTransferlocation").value= data[2];
		
		//row.innerHTML=req.responseText;
		$('#ireq').modal( "show" );
       }
	}	 
}

function openIndentPopup(){
	document.getElementById("saveIndentButton").className='';
	document.getElementById("saveIndentButton1").className='hidden';
	modifyIndentStatus = 0;
	document.getElementById("indentbody").innerHTML='<tr></tr>';
	document.getElementById("parentTransferId").value="0";
	$('#ireq').modal( "show" );
}

function checklimitqty(qty) {
	 var product_id = document.getElementById("product_id").value;
	var dataObj = {
		"qty" : "" + qty + "",
		"product_id" : "" + product_id + ""
	};
	var data1 = JSON.stringify(dataObj);
	$
			.ajax({

				url : "getlimitProduct",
				data : data1,
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,

				success : function(data) {
					var qty=data.qty;
					var limit_qty=data.limit_qty;
					
		       if(qty>limit_qty){
			
		          jAlert('error', "Please enter less qty than limit!", 'Error Dialog');	
			      setTimeout(function() {
				  $("#popup_container").remove();
				  removeAlertCss();
			   }, alertmsgduration); 
			      document.getElementById("qty").value='';
			}
			
	    },
				error : function(result) {
					console.log("Something goes wrong");
				}
			});
}
function showqtylimit(productid){
	
	
	var url="getlimitqtyProduct?productid="+productid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setProductofStoreOCRRequest;
    req.open("GET", url, true); 
    req.send(null);

	
}
function setProductofStoreOCRRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				  document.getElementById("prolimitqty").innerHTML=req.responseText;
				   
			  	   
	         }
		}	 
	}

function updatereqqtylimit(catalogueid){
	
	var url='';
	$('.indnetclass').each(function() {
          var i=this.value;
		var reqreqlimitqty = document.getElementById("reqlimitqty"+i).value;
        url = "updatereqlimitProduct?reqreqlimitqty="+reqreqlimitqty+"&catalogueid="+catalogueid+"";
	});
 
 
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatesittingRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}

function updatesittingRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			
			
         }
		
     }
			
   }
		
     
	

	
function requestedlimitqty(reqqty){
	
	
	var req_product_id = document.getElementById("product_id").value;
	
    var url = "saverequestedqtyProduct?req_product_id="+ req_product_id + "&reqqty="+reqqty +"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = reqqtyRequest;
			req.open("GET", url, true);
			req.send(null);
	
	
	
}
function reqqtyRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			
		if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
				window.location.reload();
                     // success
			}
			
         }
		
     }
	
  }
