

function selectAll(obj) {

      if(obj.checked==true){
           
            $('.case').each(function() { 
				this.checked = true; 
									
		});
      } else {
      
       		$('.case').each(function() { 
				this.checked = false; 
									
		   });	
      }

}


function isValid(){

      var vendor=0;       
  
       $('.case').each(function() {
        
				if(this.checked == true){
					vendor=vendor+","+this.value;		   
				}
		});

      var category_id=document.getElementById("category_id").value;
      var name=document.getElementById("name").value;
      
      if(category_id=='0'){
            	
             jAlert('error', "please select category!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
      		   return false;
             
      } else if(name==''){
      
      			jAlert('error', "please enter name!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
      		   return false;
      } else if(vendor==0){
           
           jAlert('error', "please select one supplier at least!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
      		   return false;
        
      } else {
      
            document.getElementById("allvendor").value=vendor;
      		return true;
      }
      
}
