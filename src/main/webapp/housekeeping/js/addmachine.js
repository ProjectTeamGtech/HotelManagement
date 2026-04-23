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
	var url = "addmachineHousekeepingdashboard?tableid="+table+"&rowcount="+rowCount+"";
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
		          
		          
		  	$("#due_date"+indexq+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		 $("#remainder_on"+indexq+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
       
       
		
	      }
	  }	 
 }
 
 
 var index=0;
 
 function saveMachine(id){
 
     index=id;
     
     var equipment=document.getElementById("equipment"+id+"").value;
     var brand=document.getElementById("brand"+id+"").value;
     var machine=document.getElementById("machine"+id+"").value;
     var vendor=document.getElementById("vendor"+id+"").value;
     var frequency=document.getElementById("frequency"+id+"").value;
     var due_date=document.getElementById("due_date"+id+"").value;
     var remainder_on=document.getElementById("remainder_on"+id+"").value;
     
      var url = "savemachineHousekeepingdashboard?equipment="+equipment+"&brand="+brand+"&machine="+machine+"&vendor="+vendor+"&frequency="+frequency+"&due_date="+due_date+"&remainder_on="+remainder_on+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveMachineRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
     
 }
 function saveMachineRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		        window.location.reload();
	         }
		}	 
	}
	
	
	
   function editMachine(id) {
       index=id;   
       var url="editmachineHousekeepingdashboard?id="+id+""
       if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editMachineRequest;
	    req.open("GET", url, true); 
	    req.send(null);
       
   }	
 
   function editMachineRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		        
		          document.getElementById(""+index+"").innerHTML=req.responseText;
		          $("#due_date"+index+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		 $("#remainder_on"+index+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		}); 
		          	 
	         }
		}	 
	}
	
	
	function updateMachine(id){
	     
     var equipment=document.getElementById("equipment"+id+"").value;
     var brand=document.getElementById("brand"+id+"").value;
     var machine=document.getElementById("machine"+id+"").value;
     var vendor=document.getElementById("vendor"+id+"").value;
     var frequency=document.getElementById("frequency"+id+"").value;
     var due_date=document.getElementById("due_date"+id+"").value;
     var remainder_on=document.getElementById("remainder_on"+id+"").value;
     
      var url = "updatemachineHousekeepingdashboard?id="+id+"&equipment="+equipment+"&brand="+brand+"&machine="+machine+"&vendor="+vendor+"&frequency="+frequency+"&due_date="+due_date+"&remainder_on="+remainder_on+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
   
    function updateRequest(){
       if (req.readyState == 4) {
			if (req.status == 200) {
		        
		          window.location.reload();
	         }
		}	 
    }
 
 
   function getmachineservice(id){
   
      var url="getservicehistoryHousekeepingdashboard?id="+id+"";
      if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getmachineserviceRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
   
   }
   
   var machineid=0;
   
   function getmachineserviceRequest(){
       if (req.readyState == 4) {
			if (req.status == 200) {
		        
		         var s=req.responseText;
		         var data=s.split("~");
		         document.getElementById("tbodyservice").innerHTML=data[0];
		         index=data[1];
		         machineid=data[2];
		         
		         
		          $("#service_date"+index+"").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		}); 
		         
		         
		         $('#history').modal( "show" ); 
		        
	         }
		}	 
    }
 
    function savemachineservice(id){
    
         
         var service_date=document.getElementById("service_date"+id+"").value;
         var notes=document.getElementById("notes"+id+"").value;
         var status=document.getElementById("status"+id+"").value;

         var url="savemachineserviceHousekeepingdashboard?service_date="+service_date+"&notes="+notes+"&status="+status+"&machine_id="+machineid+"";
              
         if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = savemachineserviceRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
    }
 
    function savemachineserviceRequest(){
       if (req.readyState == 4) {
			if (req.status == 200) {
		        
		          window.location.reload();
	         }
		}	 
    }
 
 
 