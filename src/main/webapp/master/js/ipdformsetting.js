

function saveData(){ 

     var fields= 0;
     
     $('.case').each(function() {
            if(this.checked == true ){  
			    
			    fields=fields+","+this.value;
			}					
		});
     
     document.getElementById("fields").value=fields;
     
     document.getElementById("ipdformsetting").submit();

}