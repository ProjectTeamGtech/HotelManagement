

var f=0;

function checkAll(){

   if(f==0){
           $('.case').each(function() { // loop through each checkbox
				this.checked =true; 
			 });
   
   	 	f=1;	
   } else {
   		  $('.case').each(function() { // loop through each checkbox
				this.checked =false; 
			 });
   	
      f=0;
   }


}


function getAllSubTp(val){

      var url = "getTPFollower?maintp="+val+"";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getAllSubTpRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

}


function getAllSubTpRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
						 document.getElementById("example").innerHTML=req.responseText;
					 
				}
			}
 }


 function saveallTp(){
 
    
       var radios = document.getElementsByName('main');
       var tpid=0;
       for(var i=0;i<radios.length;i++){
       
            if(radios[i].checked){
                  
                 tpid=radios[i].value;
            }
       } 
       
       
       var tempCheck=0;
       
       $('.case').each(function() { // loop through each checkbox
				if(this.checked==true){
				
				    tempCheck=tempCheck+','+this.value;     
				} 
			 });
       
       document.getElementById("tpid").value=tpid;
       document.getElementById("allcheckedlist").value=tempCheck;      
  
  
      document.getElementById("tpform").submit();
 }	




