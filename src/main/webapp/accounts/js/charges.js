var totalsubjectid = 0;
$(document).ready(function(){
	
	
	document.getElementById('totalassesment').value = totalsubjectid;
	
	
	
	$("#assementtable").click(function() {
		
		
		totalsubjectid=0;
		
		  var chk=$("#assementtable :checked").size();
		 
		   if(chk > 1){
               
	  		  $("#assementtable :checked").each(function() {
				 //alert("value = " + $(this).val());
				totalsubjectid= totalsubjectid + "," +	 $(this).val();
				});
				
			}else{
				$("#assementtable :checked").each(function() {
					totalsubjectid = $(this).val();
				});
			}
			
			document.getElementById('totalassesment').value = totalsubjectid;
			
		 
	});
	
	
});	


