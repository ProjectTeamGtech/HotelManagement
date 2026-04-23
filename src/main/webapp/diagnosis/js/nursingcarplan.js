var diagnosisid="";
function selectplanningcare(id){
 diagnosisid=id;
 var url = "getplanningdetailsIpdDashboard?planning="+id+"";
 if (window.XMLHttpRequest) {
  req = new XMLHttpRequest();
 }
 else if (window.ActiveXObject) {
  isIE = true;
  req = new ActiveXObject("Microsoft.XMLHTTP");
 }   
    req.onreadystatechange = selectplanningcareRequest;
    req.open("GET", url, true); 
    req.send(null);  
}
function selectplanningcareRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) {   
      document.getElementById("tdplanning").innerHTML=req.responseText;
      $("#planning").trigger("chosen:updated");
      $(".chosen").chosen({allow_single_deselect: true});
      selectdiagnosis(diagnosisid);
  }
 }
}
function selectdiagnosis(id){
 var url ="getdiagnosisdetailsIpdDashboard?diagnosiscare="+id+"";
  if (window.XMLHttpRequest) {
   req = new XMLHttpRequest();
  }
  else if (window.ActiveXObject) {
   isIE = true;
   req = new ActiveXObject("Microsoft.XMLHTTP");
  }   
     req.onreadystatechange = selectdiagnosisRequest;
     req.open("GET", url, true); 
     req.send(null); 

}
function selectdiagnosisRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) {  
   var data = document.getElementById("diagnosiscare").value;
   var str = req.responseText;
   if(data==''){
    document.getElementById("diagnosiscare").value= str;
   }else{
   document.getElementById("diagnosiscare").value= data +'\r\n '+str;
  } 
  }
 }
}
function selectIntervention(id){
 diagnosisid=id;
 var url = "getinterventiondetailsIpdDashboard?intervention="+id+"";
 if (window.XMLHttpRequest) {
  req = new XMLHttpRequest();
 }
 else if (window.ActiveXObject) {
  isIE = true;
  req = new ActiveXObject("Microsoft.XMLHTTP");
 }   
    req.onreadystatechange = selectInterventionRequest;
    req.open("GET", url, true); 
    req.send(null); 
}
function selectInterventionRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) {   
      document.getElementById("tdintervention").innerHTML=req.responseText;
      $("#intervention").trigger("chosen:updated");
      $(".chosen").chosen({allow_single_deselect: true});
      selectplanningnotes(diagnosisid);
  }
 }
}
function selectplanningnotes(id){
 var url ="getplanningnotesdetailsIpdDashboard?planningcare="+id+"";
  if (window.XMLHttpRequest) {
   req = new XMLHttpRequest();
  }
  else if (window.ActiveXObject) {
   isIE = true;
   req = new ActiveXObject("Microsoft.XMLHTTP");
  }   
     req.onreadystatechange = selectplanningnotesRequest;
     req.open("GET", url, true); 
     req.send(null); 

}
function selectplanningnotesRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) { 
   var data = document.getElementById("planningcare").value;
   var str = req.responseText;
   if(data==''){
    document.getElementById("planningcare").value= str;
   }else{
   document.getElementById("planningcare").value= data +'\r\n '+str;
  } 
    
  }
 }
}
function selectinterventionnotes(id){
 var url = "getinterventionnotesdetailsIpdDashboard?interventioncare="+id+"";
  if (window.XMLHttpRequest) {
   req = new XMLHttpRequest();
  }
  else if (window.ActiveXObject) {
   isIE = true;
   req = new ActiveXObject("Microsoft.XMLHTTP");
  }   
     req.onreadystatechange = selectinterventionnotesRequest;
     req.open("GET", url, true); 
     req.send(null); 

}
function selectinterventionnotesRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) {  
   var data = document.getElementById("interventioncare").value;
   var str = req.responseText;
   if(data==''){
    document.getElementById("interventioncare").value= str;
   }else{
   document.getElementById("interventioncare").value= data +'\r\n '+str;
  } 
   
    
  }
 }
}
function selectrationalenotes(id){
 var url = "getrationalenotesdetailsIpdDashboard?rationalecare="+id+"";
  if (window.XMLHttpRequest) {
   req = new XMLHttpRequest();
  }
  else if (window.ActiveXObject) {
   isIE = true;
   req = new ActiveXObject("Microsoft.XMLHTTP");
  }   
     req.onreadystatechange = selectrationalenotesRequest;
     req.open("GET", url, true); 
     req.send(null); 

}
function selectrationalenotesRequest(){
 if (req.readyState == 4) {
  if (req.status == 200) {  
   var data = document.getElementById("rationalecare").value;
   var str = req.responseText;
   if(data==''){
    document.getElementById("rationalecare").value= str;
   }else{
   document.getElementById("rationalecare").value= data +'\r\n '+str;
  } 
   
    
   
   
   
  }
 }
}






