
function allids(){
var ids = 0;
     $('.sssc').each(function() { //loop through each checkbox
        // this.checked = true;  //select all checkboxes with class "checkbox1" 
        if(this.checked==true){
        	ids = ids + ',' + this.value;
        }
         
     });
	var valu='';
	if(ids!='0'){
	var array = ids.split(",");
	
	array = array.filter(val => val !== "0");
	valu=array.toString();
	}else{
		valu='';
	}
     document.getElementById('selected').value = valu;
}