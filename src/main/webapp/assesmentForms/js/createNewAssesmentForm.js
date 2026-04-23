var c1 = 1;
var fieldId = 0;
var valueId = 0;
var validchk = 0;
var arrstrings = "";
var sizeString = "";
var isheading=false;

function addItem(tableID)
    {	 
	
	
	
	    try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
       
            var selectTag1 = "";
            for (var i = 1; i < table.rows.length; i++) {
            	
                 var selectTag2 = document.getElementById("list2");
                 for (var j = 0; j < selectTag2.options.length; j++) {
                     var optionTag2 = selectTag2.options[j];
                     if (optionTag2.selected) {
                    	// var a = optionTag2.options[i];
                    	// alert(optionTag2.text);
                         if (selectTag1.length > 0)
                        	 selectTag1 += ", ";
                         selectTag1 += optionTag2.text;
                         
             //   for (var j = 0; j < table.rows[i].cells.length; j++){                  
               // alert(table.rows[i].cells[j].value);
              // alert( table.rows[i].cells[j].innerHTML);
             //  var selectTag1 = document.getElementById("list2").value;
         	  
               var chkbox = table.rows[i].cells[0].innerHTML;
               if(optionTag2.text == chkbox){                  
                   table.deleteRow(i);
                   rowCount--;
                   i--;
               }
            
                }
            }
        
            }
            
      
            }catch(e) {
            	jAlert('error', e, 'Error Dialog');

            }
    	
        var selIndexes = "";
        var selectTag = document.getElementById("list2");
        for (var i = 0; i < selectTag.options.length; i++) {
            var optionTag = selectTag.options[i];
            if (optionTag.selected) {
                if (selIndexes.length > 0)
                    selIndexes += ", ";
                selIndexes += optionTag.text;
                var opt = document.createElement("option");
                document.getElementById("list1").options.add(opt);
                opt.text = optionTag.text;
                opt.value = optionTag.text;
             
               }
          
        }
        
        var src = document.getElementById("list2");
        
        //iterate through each option of the listbox
        for(var count= src.options.length-1; count >= 0; count--) {
     
             //if the option is selected, delete the option
            if(src.options[count].selected == true) {
      
                    try {
                             src.remove(count, null);
                             
                     } catch(error) {
                             
                             src.remove(count);
                    }
            }
        }
        
}
var value = '';
var value3 = '';

function removeItem(tableID){
	
	 var table = document.getElementById(tableID);
	 var rowCount = table.rows.length;
     //var row = table.insertRow(rowCount);
     var counts = rowCount - 1;
     var size = 1;
	 
	var selIndexes = "";
	var selIndexes3 = "";
	
    var selectTag = document.getElementById("list1");
    
    var selectTag1 = document.getElementById("list1").value;

   
    for (var i = 0; i < selectTag.options.length; i++) {
        var optionTag = selectTag.options[i];
        if (optionTag.selected) {
            if (selIndexes.length > 0)
                selIndexes += ", ";
            selIndexes += optionTag.text;
            var opt = document.createElement("option");
            document.getElementById("list2").options.add(opt);
            opt.text = optionTag.text;
            opt.value = optionTag.text;
           // opt.value = selectTag1;
          //  alert( opt.value);
            

            
            value = optionTag.text;
            
        	
				
				// var options = "<option value=\"select\">Select Size</option>";
		           /* var j = 10;
		            while(j<=500) {
		            options +="<option value=\""+j+"\">"+j+"</option>";
		            j=j+10;
		            }*/
		         /*   options = options + "<option value='10'>10</option>"
		            options = options + "<option value='20'>20</option>"
		            options = options + "<option value='50'>50</option>"
		            options = options + "<option value='100'>100</option>"
		            options = options + "<option value='200'>200</option>"
		            options = options + "<option value='500'>500</option>"
		            options = options + "<option value='1000'>1000</option>"
		            options = options + "<option value='5000'>5000</option>"
		            options = options + "</select>"
		            
		            
		            var options1 = "<option value=\"select\">Select Col</option>";
		            for(var k=1;k<=100;k++) {
		            options1 +="<option value=\""+k+"\">"+k+"</option>";
		           
		            }
		            
		           // alert(options);
		           $("#table1 tbody").append('<tr class="trow" id="rowid"><td>'+optionTag.text+'</td> ' +
		        		  '<td><select  class="form-control showToolTip type" id="type" name = "assessmentform[' + counts + '].type" onchange="return changeSize(this.value,'+counts+')"><option value="Single Line Text">Single Line Text</option><option value="Multi Line Text">Multi Line Text</option></select></td><td><select class="1-100 form-control showToolTip size"  id="size' + size + '" name = "assessmentform[' + counts + '].size" >'+options+'</select></tr>');

			*/
            
            reloadtable();
           
           
           size++;
           
                 
            
           }
        
       
        
    }
    
 
   
  //  c1 = c1 +1;
	var src = document.getElementById("list1");
    
    //iterate through each option of the listbox
    for(var count= src.options.length-1; count >= 0; count--) {
 
         //if the option is selected, delete the option
        if(src.options[count].selected == true) {
  
                try {
                         src.remove(count, null);
                         
                 } catch(error) {
                         
                         src.remove(count);
                }
        }
    }
    
	 
    
 
     
}


function reloadtable(){
	document.getElementById('table1').innerHTML = '';
	$("#table1").append('<thead><tr><th>Feild Name</th><th>Type</th><th>Size</th><th><th>Repeat</th> </tr></thead>');
	var selectTag22 = document.getElementById("list2");
	for (var j = 0; j < selectTag22.options.length; j++) {
		var optionTag22 = selectTag22.options[j];
		var zz = optionTag22.text;
		
		
		 var repeatopt = "<option value='0'>Select Repeat</option>";
       
		 repeatopt = repeatopt + "<option value='1'>1</option>"
		 repeatopt = repeatopt + "<option value='2'>2</option>"
		 repeatopt = repeatopt + "<option value='3'>3</option>"
		 repeatopt = repeatopt + "<option value='4'>4</option>"
		 repeatopt = repeatopt + "<option value='5'>5</option>"
		 repeatopt = repeatopt + "<option value='6'>6</option>"
		 repeatopt = repeatopt + "<option value='7'>7</option>"
		 repeatopt = repeatopt + "<option value='8'>8</option>"
		 repeatopt = repeatopt + "<option value='9'>9</option>"
		 repeatopt = repeatopt + "<option value='10'>10</option>"
		 
		
	
		
		
		 var options = "<option value=\"select\">Select Size</option>";
           /* var j = 10;
            while(j<=500) {
            options +="<option value=\""+j+"\">"+j+"</option>";
            j=j+10;
            }*/
            options = options + "<option value='10'>10</option>"
            options = options + "<option value='20'>20</option>"
            options = options + "<option value='50'>50</option>"
            options = options + "<option value='100'>100</option>"
            options = options + "<option value='200'>200</option>"
            options = options + "<option value='500'>500</option>"
            options = options + "<option value='1000'>1000</option>"
            options = options + "<option value='5000'>5000</option>"
            options = options + "</select>"
            
            
            var options1 = "<option value=\"select\">Select Col</option>";
            for(var k=1;k<=100;k++) {
            options1 +="<option value=\""+k+"\">"+k+"</option>";
           
            }
            
            var counts = j;
           
            var size = j;
           
            
            
           var strtxt = '';
           var hdlastchar = zz.slice(-1);
        	if(hdlastchar == '*'){
    			//$("#table1").append('<tr class="trow" id="rowid"><td style="font-size: 15px; font-weight: bold;">'+zz+" *"+'</td><td><select class="form-control showToolTip type"><option value="Heading">Heading</option></select></td><td><input type="hidden" class="1-100 form-control showToolTip size"></td></tr>');
    			strtxt = '<tr class="trow" id="rowid"><td style="font-size: 15px; font-weight: bold;">'+zz+" "+'</td><td><select id="type'+counts+'" class="form-control showToolTip type"><option value="Heading">Heading</option></select></td><td><input type="hidden" class="1-100 form-control showToolTip size"></td></tr>';
    		}else{
    			
    			strtxt = 	'<tr class="trow" id="rowid"><td>'+optionTag22.text+'</td> ' +
      		  /* '<td><select  class="form-control showToolTip types" id="types" name = "assessmentform[' + counts + '].types" onchange="return changeSelectedType(this.value,'+counts+')"><option value="1">S/M Text Dropdown</option><option value="2">Y/N Dropdown</option><option value="3">Custom Dropdown</option></select>'+*/
      		  '<td id="selected'+counts+'"><select  class="form-control showToolTip type" id="type'+counts+'" name = "assessmentform[' + counts + '].type" onchange="return changeSize(this.value,'+counts+')"><option value="Single Line Text">Single Line Text</option><option value="Multi Line Text">Multi Line Text</option><option value="Y/N Dropdown">Y/N Dropdown</option><option value="Custom Dropdown">Custom Dropdown</option></select></td><td><select class="1-100 form-control showToolTip size"  id="size' + size + '" name = "assessmentform[' + counts + '].size" >'+options+'</select></td><td><select class="1-100 form-control showToolTip size"  id="rptopt' + size + '" name = "assessmentform[' + counts + '].size" >'+repeatopt+'</select></td></tr>';
    		}
        	 counts++;
        	 
        	 $("#table1").append(strtxt);
        	
                  
           // alert(options);
         /*  $("#table1").append('<tr class="trow" id="rowid"><td>'+optionTag22.text+'</td> ' +
        		   '<td><select  class="form-control showToolTip types" id="types" name = "assessmentform[' + counts + '].types" onchange="return changeSelectedType(this.value,'+counts+')"><option value="1">S/M Text Dropdown</option><option value="2">Y/N Dropdown</option><option value="3">Custom Dropdown</option></select>'+
        		  '<td id="selected'+counts+'"><select  class="form-control showToolTip type" id="type" name = "assessmentform[' + counts + '].type" onchange="return changeSize(this.value,'+counts+')"><option value="Single Line Text">Single Line Text</option><option value="Multi Line Text">Multi Line Text</option><option value="Y/N Dropdown">Y/N Dropdown</option><option value="Custom Dropdown">Custom Dropdown</option></select></td><td><select class="1-100 form-control showToolTip size"  id="size' + size + '" name = "assessmentform[' + counts + '].size" >'+repeatopt+'</select></tr>');*/
       
	}
	
	 if(document.getElementById('tempListName').value!=0){
	/* document.getElementsByName('assessmentform[1].type')[1].value = 'Y/N Dropdown';
	 alert($("#table11 assessmentform[1].type")[0].value)*/
	
		 var arr =  arrstrings.split(',');
		 var sarr = sizeString.split(',');
		 var j = 0;
			 for(i=0;i<arr.length;i++){
				if(arr[i]!='Heading'){
					 document.getElementById('type'+i).value = ''+arr[i]+'';
					 document.getElementById('size'+i).value = ''+sarr[i]+'';
				 }
				
				 
			
	}

		 
		/* var p = 0;
		 for(k=0;k<sarr.length;k++){
			 p++;
			 document.getElementById('size'+p).value = ''+sarr[k]+'';
		 }*/
	 }
}



function listbox_move(listID, direction) {
	 
    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;
 
    if(-1 == selIndex) {
        alert("Please select an option to move.");
        return;
    }
 
    var increment = -1;
    if(direction == 'up')
        increment = -1;
    else
        increment = 1;
 
    if((selIndex + increment) < 0 ||
        (selIndex + increment) > (listbox.options.length-1)) {
        return;
    }
 
    var selValue = listbox.options[selIndex].value;
    var selText = listbox.options[selIndex].text;
    listbox.options[selIndex].value = listbox.options[selIndex + increment].value;
    listbox.options[selIndex].text = listbox.options[selIndex + increment].text;
 
    listbox.options[selIndex + increment].value = selValue;
    listbox.options[selIndex + increment].text = selText;
 
    listbox.selectedIndex = selIndex + increment;
    
  
    reloadtable();
    	
}

var templateName = "";
var typeValueData = "";
var sizeValueData = "";
var repeatValueDatarepeatValueData = "";

function setPreview(tableID){
	templateName = document.getElementById('hiddenTemplateName').value;
	
	if(templateName==""){
		//alert('Please Enter Template Name');
		jAlert('error', 'Please Enter Template Name', 'Error Dialog');
	}
	else if(validchk==1){
		// setError3(tempdiv);
		 return false;
	}
	else{
		  document.getElementById('tname').innerHTML = templateName;
		
		  
		  previewAssessment(tableID);
		
	}
	
}



function previewAssessment(tableID){	
	
	if( document.getElementById('includeImage').checked==false){
		 document.getElementById('mnipaint').style.display = '';
	}else{
		 document.getElementById('mnipaint').style.display = 'none';
	}
		
	$("#table2 tr").remove();
	typeValueData = '';
	sizeValueData = '';
	repeatValueData = '';
	 try {
		 
			         
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var counts = rowCount - 1;
        var i = 1;   
        var j = 0;
        var size = 0;
      
                     
        $(".type").each(function () {
            var typeValue = $(this).val();          
           // alert(typeValue);         
            var rowValue = table.rows[i].cells[0].innerHTML;
            var textValue = rowValue.replace(" ", "_");
            
            var rpt = $("#rptopt"+size).val();
            
            if(typeValue=='Y/N Dropdown'){
            	$("#table2 tbody").append('<tr><td><lable id =  "l'+j+'">'+rowValue+'</lable></td><td><select id="field" name="'+textValue+'" class="form-control showToolTip field1" disabled="disabled"><option value="Select Yes/No">Select Yes/No</option> </select></td></tr>');
            	typeValueData += 'Y/N Dropdown' + '#';  
            	 sizeValueData+= 'select' + '#'; 
            	 repeatValueData+= rpt + '#'; 
            }
           
            if(typeValue=='Custom Dropdown'){
            	$("#table2 tbody").append('<tr><td><lable id =  "l'+j+'">'+rowValue+'</lable></td><td><select id="field" name="'+textValue+'" class="form-control showToolTip field1" disabled="disabled"><option value="Select Custom Value">Select Custom Value</option> </select></td></tr>');
            	typeValueData += 'Custom Dropdown' + '#';  
            	sizeValueData+= 'select' + '#';
            	repeatValueData+= rpt + '#'; 
            }
           
          //  alert($('#Crd option:selected').text());
            var v = $("#size"+size).val();
          //  alert(v);
           // alert($("#size"+size+" option[value="+v+"]").text());
            
            if(typeValue == "Single Line Text"){           	
            	
            	$("#table2 tbody").append('<tr><td><lable id =  "l'+j+'">'+rowValue+'</lable></td><td><input type="text" id="field" name="'+textValue+'" class="form-control showToolTip field1" disabled="disabled" maxlength="'+v+'"/></td></tr>');
            	  typeValueData += typeValue + '#';     
            	  sizeValueData+= v + '#'; 
            	  repeatValueData+= rpt + '#'; 
            }
            else if(typeValue == "Multi Line Text"){
            	
            	$("#table2 tbody").append('<tr><td><lable id = "l'+j+'">'+rowValue+'</lable></td><td><textarea id="field" name="'+textValue+'" class="form-control showToolTip field1" rows="4" cols="40" disabled="disabled"></textarea></td></tr>');
            	 typeValueData += typeValue + '#'; 
            	 sizeValueData+= v + '#'; 
            	 repeatValueData+= rpt + '#'; 
            }
            else if(typeValue == "Heading"){
            	
            	$("#table2 tbody").append('<tr><td style="font-size: 15px; font-weight: bold; text-align: center;"><lable id = "l'+j+'">'+rowValue+'</lable></td><td><input type="hidden" class="form-control showToolTip field1"></td></tr>');
            	 typeValueData += typeValue + '#'; 
            	 sizeValueData+= '0' + '#'; 
            	 repeatValueData+= rpt + '#'; 
            }
            i++;
            j++;
            size++;
        });
        
       
        
        $( '#previewAssment' ).modal( "show" );
       
      
	 }catch(e) {
    	jAlert('error', e, 'Error Dialog');

    }
}



function addPreview(tableID){	
	
	var table = document.getElementById(tableID);
	
	var i = 1;
	
/*	var Pic;
	for(var i in LAYERS){
		 Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");	
	}
	*/
	
	 $(".field1").each(function () {
         var textName = $(this).val();
         var lableValue = $("#l"+i+"").text();      
         i++;
        // alert(valueId);
         var url = "addPreviewAssesmentForms?lableValue="+lableValue+"&textName="+textName+"&valueId="+valueId+"&templateName="+templateName+"";

               
     	if (window.XMLHttpRequest) {
     			req = new XMLHttpRequest();
     		}
     		else if (window.ActiveXObject) {
     			isIE = true;
     			req = new ActiveXObject("Microsoft.XMLHTTP");
     		}   
     	               
     	    req.onreadystatechange = addPreviewRequest;
     	    req.open("GET", url, true); 
     	              
     	    req.send(null);
     	    return true;
     	
	 });
	 
	  /* $.ajax({
           type: 'POST',
           cache:false,
           async: false,
           url: "updateImageAssesmentForms?&templateName="+templateName+"&valueId="+valueId+"&imageData="+Pic+"",
           data: '{ "imageData" : '+Pic+' }',
           contentType: 'application/json; charset=utf-8',       	
           dataType: 'json',
           success: function (msg) {
               alert("Done, Picture Uploaded."); 
           }
       });*/
	
	
}

function addPreviewRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {				
				valueId = req.responseText;	
				 insertImage();
				 $( '#previewAssment' ).modal( "hide" );
			}
	}
}

var lableValueData = "";

function setTemplateName(tname){
	document.getElementById('hiddenTemplateName').value = tname;
	var tempname = document.getElementById('templateName').value;
	//alert(tempname);
	
	var url = "checkTemplateNameExistAssesmentForms?tempname="+tempname+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkTemplateNameValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkTemplateNameValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{					
					chk = 1;
					validchk = 1;
				}
				
				 if(chk == 1)
			     {	
					 document.getElementById('tempnameError').innerHTML="";
					 var temp = document.createElement("label");
					 temp.innerHTML = "Template Name already Exist Please try another";
				     document.getElementById('tempnameError').appendChild(temp);
				     chk = 1;
				     validchk = 1;
				     return false;
			     }
			     else
			     {			    	
			    	 document.getElementById('tempnameError').innerHTML="";			    	
			    	 chk = 0;
			    	 validchk = 0;
			    	 return true;
			     }	         
	         }
		}
	}


function insertImage(){
	
	  if(document.getElementById('templateName').value==''){
		  document.getElementById('tempaction').value = 'edit';
	  }else{
		  document.getElementById('tempaction').value = 'save';
	  }
	
	//alert('hi');
	lableValueData = "";
	var Pic;
	//var j = 1;
	 for(var i in LAYERS){
		 Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
	}

	 var k = 0;
	 $(".field1").each(function () {
         var textName = $(this).val();
         var lableValue = $("#l"+k+"").text();   
        //alert(lableValue);
        lableValueData += lableValue + '#';
         
         k++;
         
        
        
	 });
	 
	 
	 document.getElementById('lableValueData').value = lableValueData;
	 
	 document.getElementById('typeValueData').value = typeValueData;
	 
	 document.getElementById('sizeValueData').value = sizeValueData;
	 
	 document.getElementById('repeatValueData').value = repeatValueData;
	 
	 
	 
	document.getElementById('imageData').value = Pic;
	
	//document.getElementById('tName').value = templateName;
	
	//document.getElementById('savefrm').submit();
	

}



function savePreviewTemplate(){
	
	//var table = document.getElementById(tableID);
	var tempId = document.getElementById('templateId').value;
	
	var i = 1;
	 $(".filedname").each(function () {
         var textName = $(this).val();
       //  alert(textName);
         var lableName = $("#lable"+i+"").text();  
       //  alert(lableName);
         i++;
         var url = "saveTemplateAssesmentForms?lableName="+lableName+"&textName="+textName+"&fieldId="+fieldId+"&tempId="+tempId+" ";

      	if (window.XMLHttpRequest) {
      			req = new XMLHttpRequest();
      		}
      		else if (window.ActiveXObject) {
      			isIE = true;
      			req = new ActiveXObject("Microsoft.XMLHTTP");
      		}   
      	               
      	    req.onreadystatechange = savePreviewTemplateRequest;
      	    req.open("GET", url, true); 
      	              
      	    req.send(null);
      	    return true;
      	
 	 });
 	
 }

 function savePreviewTemplateRequest(){
 	if (req.readyState == 4) {
 			if (req.status == 200) {				
 				fieldId = req.responseText;			
 				//alert(valueId);
 			}
 	}
 }
 
 function showPopUp2(){
	 
		var url = "showListClient";
		
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showAllPatientPopUpRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

	}
	function showAllPatientPopUpRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					document.getElementById("allPatient").innerHTML = req.responseText;
					
		         	
					
		         }
			}
	}
	
function addHeading(){
		
		var headingData = prompt("Enter Heading");
		//headingData =  headingData.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
		headingData = headingData + '*';
		//alert(headingData);
		if(headingData!=null){
		var opt = document.createElement("option");
		document.getElementById("list2").options.add(opt);
		opt.text = headingData;
		opt.value = headingData;
		
		
		$("#table1 tbody").append('<tr class="trow" id="rowid"><td style="font-size: 15px; font-weight: bold;">'+headingData+" "+'</td><td><select class="form-control showToolTip type"><option value="Heading">Heading</option></select></td><td><input type="hidden" class="1-100 form-control showToolTip size"></td></tr>');

		}
	}
	

function confirmedDelete(){

	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }

	}
	
var editTempName = '';

function setTemplateDetails(){
	
	var editTempId = document.getElementById('tempListName');
	editTempName = editTempId.options[editTempId.selectedIndex].text;
	//alert(editTempName);
	
	var tempListNameId = document.getElementById('tempListName').value;
	
	if(tempListNameId != 0){
		document.getElementById('templateName').value = '';
		//document.getElementById('templateName').disabled = 'disabled';
		/* document.getElementById('btn1').style.display = 'none';
		 document.getElementById('btn2').style.display = '';*/
		 document.getElementById('hiddenTemplateName').value = editTempName;
			
	}
	else if(tempListNameId == 0){
		document.getElementById('templateName').disabled = '';
		/*document.getElementById('btn2').style.display = 'none';
		document.getElementById('btn1').style.display = '';*/
		document.getElementById('hiddenTemplateName').value = '';
		//document.getElementById('table1').innerHTML = '';
	}
	
	   var url = "availableSelectedListAssesmentForms?tempListNameId="+tempListNameId+" ";

     	if (window.XMLHttpRequest) {
     			req = new XMLHttpRequest();
     		}
     		else if (window.ActiveXObject) {
     			isIE = true;
     			req = new ActiveXObject("Microsoft.XMLHTTP");
     		}   
     	               
     	    req.onreadystatechange = setTemplateDetailsRequest;
     	    req.open("GET", url, true); 
     	              
     	    req.send(null);
     	 
 
	
}

function setTemplateDetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				
				
				$("#table1 tr").remove();
				var data = req.responseText;		
				var temp = data.split("#");
				var str = temp[0];
				var list2 = temp[1];
				var list1 = temp[2];
				var str2 = temp[3];
				$("#table1 thead").append(str2);
				document.getElementById('available1').innerHTML = list1;
				document.getElementById('available2').style.display = 'none';
				document.getElementById('selected1').innerHTML = list2;
				
				 $("#table1 tbody").append(str);
				 
				
				  //set filters value 
				  var flterdata = document.getElementById('filterhdn').value;
				  var ftmp = flterdata.split('~');
				  
				  document.getElementById('layout').value = ftmp[0];
				  document.getElementById('specialization').value = ftmp[1];
				  document.getElementById('includeImage').checked = ftmp[2];
				  document.getElementById('templateNote').value = ftmp[3];
				
				 
				 document.getElementById('selected2').style.display = 'none';
				 
				 	var dataURL = temp[4];
				 	//var dataURL = 'chart/zen.jpg'; 
				    // load image from data url
					var imageObj = new Image();
				    imageObj.src = dataURL;
				    imageObj.onload = function() {
				    var canvas = document.getElementById('Background');
				    var context = canvas.getContext('2d');
				    context.drawImage(imageObj, 0, 0);
				    };
				    
					var i = 0;
					var Pic;
					 for(var i in LAYERS){
					  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
					// alert(i);
					 }

					 document.getElementById('imageData').value = Pic;
					
					  arrstrings = document.getElementById('arrstring').value;
					  sizeString = document.getElementById('sizeStringdata').value;
					  
					
			}
	}
}
	
	
function setClientsAssessment(practId){
	document.getElementById('diaryUser1').value = practId;
	var url = "setClientsAssesmentForms?practId="+practId+"";	
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setClientsAssessmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
	}

	function setClientsAssessmentRequest(){
	if (req.readyState == 4) {
			
			
				if (req.status == 200) {
					document.getElementById("clientnameAssessment").innerHTML =  req.responseText;
					$("#clientnameAssessment").trigger("chosen:updated");
					$(".chosen").chosen({allow_single_deselect: true});	
					
				}
				
		}
	}
	
	function setConditionAssessment(clientId){
		var url = "setClientConditionAssesmentForms?clientId="+clientId+"";	
		globalClientId = clientId;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setConditionAssessmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function setConditionAssessmentRequest(){
		if (req.readyState == 4) {
			
			
			if (req.status == 200) {
				document.getElementById("conditionAssessment").innerHTML =  req.responseText;
				$("#conditionAssessment").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});	
				
				document.getElementById('clientname11').value = globalClientId;
				
			}
			
	}

	}
	
	
	function setAssesmentCondition(conditionid){
		document.getElementById('condition1').value = conditionid;
		
	}
	
	function saveEditImageTemplate(){
		
		var Pic;
		 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
		}

		 document.getElementById('imageData1').value = Pic;
		 
		 document.getElementById('editimage_form').submit();
		 
	}
	
	function saveAssesmentDataTosession(id,value){
		
		var url = "setAssesmentForms?kid='"+id+"'&value='"+value+"' ";	
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveAssesmentDataTosessionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
	}
	
	function saveAssesmentDataTosessionRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
			}
		}
	}

	
	function clearEditor(){
	/*	ZOOM = 100;
		MAIN.init();*/
	}