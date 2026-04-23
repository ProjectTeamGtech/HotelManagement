var cell;
var row;
var indexq=0;	

function addRow(tableID) {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	indexq=rowCount-1;
	var url = "addlaundryHousekeepingdashboard?tableid="+table+"&rowcount="+rowCount+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


 function addRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;
		          
		          
		  $("#allot_date"+indexq+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
       
        $("#collected_date"+indexq+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		          
		          
		          
		          	
	         }
		}	 
	}
	
	
  
  function getmobvendor(id,index){
       indexq=index;
      var url = "getmobvendorHousekeepingdashboard?vendorid="+id+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getmobvendorRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
  
  }	
  
  function getmobvendorRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		        
		        document.getElementById("contact"+indexq+"").value=req.responseText;  	
	         }
		}	 
	}
	
	
	function getqty(id,index){
	   indexq=index;
	   var url = "getqtyHousekeepingdashboard?id="+id+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getqtyRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
	}
	function getqtyRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		        
		        var d=req.responseText;
		        document.getElementById("tdq"+indexq+"").innerHTML="<span>"+d+"/</span> <input type='text' name='quantity' id='qty"+indexq+"' class='form-control' />";  	
		        
		        if(d=='0'){
		              document.getElementById("save"+indexq+"").style="display:none";
		        
		              jAlert('success', 'Sorry No Product Remains...', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
		        
		            
		            
		        } else {
		            document.getElementById("save"+indexq+"").style="";
		            
		        } 
		
	         }
		}	 
	}
	
	function saveLinen(index){
	 
	    var pid=document.getElementById("product_id"+index+"").value;
	    var qty=document.getElementById("qty"+index+"").value;
	    var staff=document.getElementById("satff"+index+"").value;
	    var inhouse=document.getElementById("inhouse"+index+"").value;
	    var vendor=document.getElementById("vendor"+index+"").value;
	    var notes=document.getElementById("notes"+index+"").value;
	    
	    
	   var url = "savelinenHousekeepingdashboard?prodid="+pid+"&qty="+qty+"&staff="+staff+"&inhouse="+inhouse+"&vendor="+vendor+"&notes="+notes+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveLinenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
         	    
	    
	} 
	function saveLinenRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		        window.location.reload();
	         }
		}	 
	}
	

	
	function editLinen(id){
	    indexq=id;
	    var url = "editlinenHousekeepingdashboard?id="+id+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editLinenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null); 
	     
	}
	function editLinenRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			    document.getElementById(indexq).innerHTML=req.responseText;
			     $("#collected_date"+indexq+"").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});  
	         }
		}	 
	}
	
	
	function updateLinen(id){
	
	    var collect_by=document.getElementById("collect_by"+id+"").value;
	    var collected_date=document.getElementById("collected_date"+id+"").value;
	    var notes=document.getElementById("notes"+id+"").value;
	    var collect_linen=document.getElementById("collect_linen"+id+"").value;
	    
	   var url = "updatelinenHousekeepingdashboard?id="+id+"&collect_by="+collect_by+"&collected_date="+collected_date+"&notes="+notes+"&collect_linen="+collect_linen+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateLinenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function updateLinenRequest(){
	
	    if (req.readyState == 4) {
			if (req.status == 200) {
			
			   window.location.reload();
	         }
		}	 
	}
	
	
	function setYesNo(t,n){
	  
	   if(t=='No'){
	   
	       document.getElementById("vendor"+n+"").disabled=false;
	      
	   } else {
	       document.getElementById("vendor"+n+"").disabled=true;  
	   }
 	     
	 } 
	 
	function editQuantitylinen(id){
	     
	     indexq=id;
	    var url = "editlinenqtyHousekeepingdashboard?id="+id+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editQuantitylinenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null); 
	 }
	 
	 function editQuantitylinenRequest(){
	   if (req.readyState == 4) {
			if (req.status == 200) {
			
			    document.getElementById(indexq).innerHTML=req.responseText;
			       
	         }
		}	 
	}
	  
	
	function updateqtyLinen(id){
	
	    var collect_linen=document.getElementById("collect_linen"+id+"").value;
	    
	   var url = "updateqtylinenHousekeepingdashboard?id="+id+"&collect_linen="+collect_linen+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateqtyLinenRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function updateqtyLinenRequest(){
	
	    if (req.readyState == 4) {
			if (req.status == 200) {
			
			   window.location.reload();
	         }
		}	 
	}
	
	
	
	