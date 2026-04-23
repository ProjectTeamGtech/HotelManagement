

function isValidate() {

   var branch=document.getElementById("branch").value;
   var address=document.getElementById("address").value;
   var city=document.getElementById("city").value;
   var state=document.getElementById("state").value;
   var country=document.getElementById("country").value;
   var phone1=document.getElementById("phone1").value;
   var phone2=document.getElementById("phone2").value;   
   var email=document.getElementById("email").value; 
   
   
   var isError=false;
   
   if(branch.length<1){
      
       document.getElementById("errbranch").innerText="please enter the branch name!";  
       isError=true;
   }
   else {
        document.getElementById("errbranch").innerText="";  
   }
   if(address.length<1){
      
       document.getElementById("erraddress").innerText="please enter the Address!";  
       isError=true;
   }
   else {
   
       document.getElementById("erraddress").innerText="";  
   }
   if(city.length<1){
      
      document.getElementById("errcity").innerText="please enter the City!";  
       isError=true;
   }
   else {
        document.getElementById("errcity").innerText=""; 
      
   }
   if(state.length<1){
      
      document.getElementById("errstate").innerText="please enter the State!";  
       isError=true;
   }
   else {
   
      document.getElementById("errstate").innerText="";  
   }
   if(country.length<1){
      
       document.getElementById("errcountry").innerText="please enter the Country!";  
       isError=true;
   }
   else {
     
       document.getElementById("errcountry").innerText="";   
   }
   if(phone1.length<1){
      
      document.getElementById("errphone1").innerText="please enter the Phone 1!";  
       isError=true;
   }
   else {
   
      document.getElementById("errphone1").innerText=""; 
   }
   if(phone2.length<1){
      
      document.getElementById("errphone2").innerText="please enter the Phone 2!";  
       isError=true;
   }
   else {
       document.getElementById("errphone2").innerText="";
      
   }
   if(email.search("@")<0 || email.search(".")<0){
       document.getElementById("erremail").innerText="please enter valid email!";
       isError=true;
   }
   else {
       document.getElementById("erremail").innerText="";
      
   }
   if(isError==true){
   
      return false;
   }
   else {
   
      return true;
   }
   
}


function addorEdit() {

   var t=isValidate();

   if(t==true) {

		var id=document.getElementById("id").value;
   		if(id>0) {
      //edit code

             var myform=document.getElementById("myform");
             myform.action="updateBranch";
             myform.submit();
         }
         else {
   
      //add code
        	var myform=document.getElementById("myform");
        	myform.action="addBranch";
        	myform.submit();
      
        }
    }
}

function editbranch(id) {

   var url = "editBranch?selectedid="+id+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   

    req.onreadystatechange = editBranchRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function editBranchRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str=req.responseText;
			
			var data=str.split("/");
            document.getElementById("id").value=data[0];            			
			document.getElementById("branch").value=data[1];
			document.getElementById("address").value=data[2];
			document.getElementById("city").value=data[3];
			document.getElementById("state").value=data[4];
 			document.getElementById("country").value=data[5];
 			document.getElementById("phone1").value=data[6];
 			document.getElementById("phone2").value=data[7];
 			document.getElementById("email").value=data[8];
 			
 			$('#addcbranch').modal( "show" );
 			
 		}
  }
}

