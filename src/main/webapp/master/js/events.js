
var jobtitles="";
function submitData(){
     jobtitles="0";
    
     $('.case').each(function() { //loop through each checkbox
		  		if(this.checked==true){
			  		jobtitles = jobtitles + ',' + this.value;   
		  		}
      }); 
     
      var time="";
     
      var t1=document.getElementById("time1").value;
      var t2=document.getElementById("time2").value;
      time=t1+" "+t2; 
      document.getElementById("time").value=time;
      document.getElementById("jobtitle").value=jobtitles;
 
     document.getElementById("eventsform").submit();
    
  
}
function updateData(){
     jobtitles="0";
    
     $('.case').each(function() { //loop through each checkbox
		  		if(this.checked==true){
			  		jobtitles = jobtitles + ',' + this.value;   
		  		}
      }); 
     
      var time="";
     
      var t1=document.getElementById("time1").value;
      var t2=document.getElementById("ampm").value;
      time=t1+" "+t2; 
      document.getElementById("time").value=time;
      document.getElementById("jobtitle").value=jobtitles;
 
     document.getElementById("eventsupdateform").submit();
    
  
}
