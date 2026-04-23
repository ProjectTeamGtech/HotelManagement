
 var count="";
  
 function getsub(id,txt) {
           
      var str=txt.substring(11);
      count=str;
      var url="getsubcategoriesProduct?id="+id+"&index="+str+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = getsubRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }


 
function getsubRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("xsub"+count+"").innerHTML=req.responseText;
         }
	}	 
}


var second="";
function setvendor(id,txt) {
           
      var str=txt.substring(9);
      second=str;
      var url="getbrandsProduct?id="+id+"&index="+str+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = setvendorRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
 }


 
function setvendorRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("xven"+second+"").innerHTML=req.responseText;
         }
	}	 
}








var cell;
var row;
var index=1;
var btnid;
function addnewRow(tableId) {
  
    index++;
    var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;
    var url = "addnewrowProduct?rowcount="+counts+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addnewrowRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addnewrowRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          row.innerHTML=req.responseText;
	          
	          $("#tax"+tid+"").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			        //display error message
			        $("#errmsg").html("Digits Only").show().fadeOut("slow");
			               return false;
			    }
			   });
			   
			   
			  $("#expiry_date"+tid+"").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
    
	          
         }
	}	 
}

function submitProduct() {

    var t=isValid();
    if(t==true){
       
        document.productform.submit();
    }   
}


function addcategory() {

   var val=document.getElementById("txtmainmenus").value;
   var url="saveaddProductinventory?category="+val+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = addcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
     
     
     document.location.reload();
}

function addcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             document.location.reload(); 
             
         }
	}	 
	
}


function addSubMenu() {
   
   var catid=document.getElementById("selectlistofmenu").value;    
   var subcategory=document.getElementById("txtsubmenus").value;
   
   var url="savesubProductinventory?categoryid="+catid+"&subcategory="+subcategory+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = addSubMenuRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
     
     
     document.location.reload();  
    
}

function addSubMenuRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             document.location.reload();  
             
         }
	}	 
	
}


function isValid() {

     var flag=false;
   for(var i=0;i<index;i++){
   
        var vendor_id=document.getElementById("vendor"+i).value;
        var brand_id=document.getElementById("brand"+i).value;
        var genericname=document.getElementById("genericname"+i).value;
        var mrp=document.getElementById("mrp"+i).value;
        var purchase_price=document.getElementById("purchase_price"+i).value;
        var sale_price=document.getElementById("sale_price"+i).value;
        if(vendor_id==0){
        
            flag=true;
            jAlert('error', 'Please Select Supplier.', 'Error Dialog');
            return false;   
        }
        if(brand_id==0){
        
            flag=true;
            jAlert('error', 'Please Select Product NAme.', 'Error Dialog');
            return false;   
        }
        
        if(genericname.length<1){
        
           flag=true;
            jAlert('error', 'Please Enter Generic Name', 'Error Dialog');
            return false; 
        }
        if(mrp.length<1){
        
           flag=true;
            jAlert('error', 'Please Enter MRP.', 'Error Dialog');
            return false; 
        }
        if(purchase_price.length<1){
        
           flag=true;
            jAlert('error', 'Please Entet Purchase Price.', 'Error Dialog');
            return false; 
        }
        if(sale_price.length<1){
        
           flag=true;
            jAlert('error', 'Please Entet Sale Price.', 'Error Dialog');
            return false; 
        }
        
        if(flag==true){
          
           return false;
        } else {
           return true;
        }
        
   }
   
  
}


function deletemcategory(id) {

      var t=confirm("Are you sure you want to Delete?");
  
      if(t==true){
         
             var url="deletecategoryajaxProductinventory?category="+id+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = deletemcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
    }
}

function deletemcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.location.reload();
         }
	}	 
	
}


var temp="";
function editmcategory(id,category) {

      temp="m"+id;
      var url="editcategoryajaxProductinventory?id="+id+"&category="+category+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = editmcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
}

function editmcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.getElementById(temp).innerHTML=req.responseText;
         }
	}	 
	
}


function updatecategory(id) {

      var tempid="b"+id;
      var category=document.getElementById(tempid).value;

      var url="updatecategoryajaxProductinventory?id="+id+"&category="+category+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = updatecategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   


}


function updatecategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.location.reload();
         }
	}	 
	
}

function editsubcategory(id,subcategory){ 
    
      temp="s"+id;
      
      var url="editsubcategoryajaxProductinventory?id="+id+"&subcategory="+subcategory+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = editsubcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
     

}

function editsubcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.getElementById(temp).innerHTML=req.responseText;
         }
	}	 
	
}


function reloadthis() {
  document.location.reload();
}

function updatesubcategory(id) {

     var t="ss"+id;
     var subcategory=document.getElementById(t).value;
     var url="updatesubcategoryajaxProductinventory?id="+id+"&subcategory="+subcategory+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	  }
	   else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
               
      req.onreadystatechange = updatesubcategoryRequest;
      req.open("GET", url, true); 
              
      req.send(null);   
  
}

function updatesubcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.location.reload();
         }
	}	 
	
}


function deletesubcategory(id) {

     var t=confirm("Are you sure you want to Delete?");
  
      if(t==true){
         
             var url="deletesubcategoryajaxProductinventory?subcategory="+id+"";
     
      if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = deletesubcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
    }    

}


function deletesubcategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.location.reload();
         }
	}	 
}


function setCategory(val){

   var url="setcategoryProduct?category="+val+"";
  
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
               
     req.onreadystatechange = deletesubcategoryRequest;
     req.open("GET", url, true); 
              
     req.send(null);   
  
}
function setCategoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
             
             document.location.reload();
         }
	}	 
}

function deleteRow(tableID) {

		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		if(rowCount>3){
		      table.deleteRow(rowCount-1);
		} else {
		   jAlert('error', "Can Not Delete All Rows!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					return false;
		}
		
}


