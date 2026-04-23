/**
 * 
 */

var visit_reason_ids="0";
var selected="0";

function openVisitPopup(d){
	
	   document.getElementById("reason").value=''; 
	   document.getElementById("quality").value='';
	   document.getElementById("periodicity").value='';
	   document.getElementById("influence").value='';
	   document.getElementById("since").value='';
	   document.getElementById("days").value='';
	   document.getElementById("notes").value='';	
	   document.getElementById('reasonvisit').value =d;
	   if(d=='Fetal movements'){
		   if(document.getElementById('region_hide_div')){
			   document.getElementById('region_hide_div').className ="col-lg-12 col-xs-12 col-md-12";
		   }
	   }else{
		   if(document.getElementById('region_hide_div')){
			   document.getElementById('region_hide_div').className ="col-lg-12 col-xs-12 col-md-12 hidden";
			   document.getElementById("region_perceives").checked = false;
			   document.getElementById("region_notperceives").checked = false;
			   document.getElementById("region_decreased").checked = false;
		   }
	   }
	   $('#rvisit').modal( "show" );
	
}


function addReasonForVisit(){
	   
	    $('#dashboardloaderPopup').modal( "show" );
	    var reasonvisit= document.getElementById("reasonvisit").value;
	    var clientid= document.getElementById("clientid").value;
	    var reason= document.getElementById("reason").value; 
	    var quality= document.getElementById("quality").value;
	    var periodicity =document.getElementById("periodicity").value;
	    var influence= document.getElementById("influence").value;
	    var si= document.getElementById("since").value;
	    var days= document.getElementById("days").value;
	    var since =si+" "+days;
	    var notes= document.getElementById("notes").value;
	    var region_perceives = document.getElementById("region_perceives").checked;
	    var region_notperceives = document.getElementById("region_notperceives").checked;
	    var region_decreased = document.getElementById("region_decreased").checked;
	    
	    var perceives = 0;
	    if(region_perceives){
	    	perceives=1;
	    }
	    
	    var notperceives = 0;
	    if(region_notperceives){
	    	notperceives=1;
	    }
	    
	    var decreased = 0;
	    if(region_decreased){
	    	decreased=1;
	    }
	    
	    var url="savereasonforvisitobjCommonnew?clientid="+clientid+"&reason="+reason+"&quality="+quality+"&periodicity="+periodicity+"&influence="+influence+"&since="+since+"&notes="+notes+"&reasonvisit="+reasonvisit+"&perceives="+perceives+"&notperceives="+notperceives+"&decreased="+decreased+"";
	    if (window.XMLHttpRequest) {
	    	req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addReasonForVisitRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function addReasonForVisitRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
						          
				    var str =req.responseText;
				    var data= str.split("~");
				    visit_reason_ids=document.getElementById("visit_reason_ids").value;
				    if(visit_reason_ids=='' || visit_reason_ids==' '){
				    	visit_reason_ids='0';
				    }
				    visit_reason_ids =visit_reason_ids+","+data[1];
					document.getElementById("scrolltable").innerHTML=data[0];
					document.getElementById("visit_reason_ids").value= visit_reason_ids;
					$('#rvisit').modal( "hide" );
					$('#dashboardloaderPopup').modal( "hide" );
					
			}
		}	 
	}


    function calEdd(lmp){
    	newgynicform1
    	if(document.getElementById('newgynicform1')){
    		
    	}else{
    		calTT1(lmp);
        	calTT2(lmp);
        	calinfluenza_vaccine(lmp);
    	}
    	var datestr= lmp.split("-");
		var day= Number(datestr[0]);
		var month= Number(datestr[1])-1;
		var year= Number(datestr[2]);
		
        var date = new Date(year,month,day); ////Remember, months are 0 based in JS
        var newdate = new Date();
        newdate.setDate(date.getDate() + 280);
        
        var dd = newdate.getDate();
        var mm = newdate.getMonth() + 1;
        var y = newdate.getFullYear();
        
        if(dd<10){
            dd='0'+dd;
        } 
        if(mm<10){
            mm='0'+mm;
        } 

        var someFormattedDate = dd + '-' + mm + '-' + y;
        document.getElementById('edd').value = someFormattedDate;
        
        // JavaScript program to illustrate  
        // calculation of no. of days between two date  
        // To set two dates to two variables 
        //mm-dd-yyyy
        var datestrnew= lmp.split("-");
        var lmdatenew= datestr[1]+"/"+datestr[0]+"/"+datestr[2];
        var date1 = new Date(lmdatenew); 
        
        var today = new Date();
        var dd1 = today.getDate();
        var mm1 = today.getMonth()+1; 
        var yyyy1 = today.getFullYear();
        if(dd1<10) {
        	dd1='0'+dd1;
        } 
        if(mm1<10) {
        	mm1='0'+mm1;
        } 
        var currentdate= mm1+"/"+dd1+"/"+yyyy1;
        var date2 = new Date(currentdate); 
      
	    // To calculate the time difference of two dates 
	    var Difference_In_Time = date2.getTime() - date1.getTime(); 
	      
	    // To calculate the no. of days between two dates 
	    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24); 
	    
	    var reminder = Difference_In_Days % 7;
	    var ans = Difference_In_Days / 7;
	    ans = Math.floor(ans);
	    if(document.getElementById('pog')){
	    	document.getElementById('pog').value=ans+"weeks "+reminder+"days";
	    }
    }
    
function calTT1(lmp){
    	
    	var datestr= lmp.split("-");
		var day= Number(datestr[0]);
		var month= Number(datestr[1])-1;
		var year= Number(datestr[2]);
		
        var date = new Date(year,month,day); ////Remember, months are 0 based in JS
        var newdate = new Date();
        newdate.setDate(date.getDate() + 140);
        
        var dd = newdate.getDate();
        var mm = newdate.getMonth() + 1;
        var y = newdate.getFullYear();
        
        if(dd<10){
            dd='0'+dd;
        } 
        if(mm<10){
            mm='0'+mm;
        } 

        var someFormattedDate = dd + '-' + mm + '-' + y;
        document.getElementById('tt_dose1').value = someFormattedDate;
    }
    
function calTT2(lmp){
	
	var datestr= lmp.split("-");
	var day= Number(datestr[0]);
	var month= Number(datestr[1])-1;
	var year= Number(datestr[2]);
	
    var date = new Date(year,month,day); ////Remember, months are 0 based in JS
    var newdate = new Date();
    newdate.setDate(date.getDate() + 168);
    
    var dd = newdate.getDate();
    var mm = newdate.getMonth() + 1;
    var y = newdate.getFullYear();
    
    if(dd<10){
        dd='0'+dd;
    } 
    if(mm<10){
        mm='0'+mm;
    } 

    var someFormattedDate = dd + '-' + mm + '-' + y;
    document.getElementById('tt_dose2').value = someFormattedDate;
}

function calinfluenza_vaccine(lmp){
	
	var datestr= lmp.split("-");
	var day= Number(datestr[0]);
	var month= Number(datestr[1])-1;
	var year= Number(datestr[2]);
	
    var date = new Date(year,month,day); ////Remember, months are 0 based in JS
    var newdate = new Date();
    newdate.setDate(date.getDate() + 196);
    
    var dd = newdate.getDate();
    var mm = newdate.getMonth() + 1;
    var y = newdate.getFullYear();
    
    if(dd<10){
        dd='0'+dd;
    } 
    if(mm<10){
        mm='0'+mm;
    } 

    var someFormattedDate = dd + '-' + mm + '-' + y;
    document.getElementById('influenza_vaccine').value = someFormattedDate;
}


function switchgynicfomr(d){
	
	 if(d=='1'){
		 	//obs
		  document.getElementById("formtype").value="1";
	 } else if(d=='2'){
		 	//gynic
		 document.getElementById("formtype").value="2";
	 } else {
		 	//infertility
		 document.getElementById("formtype").value="3";
	 }
	 
	 document.getElementById("ipdadmissionfrm").action="gynicassesmentformIpd";
	 document.getElementById("ipdadmissionfrm").submit();
	
}


function validdata(){
	
	    var diagosis=0;
	    $('.classD').each(function() { 
			if(this.checked == true){
				diagosis=diagosis+","+this.value;
			}
		});
	    if(document.getElementById("formtype").value!=4){
	    	document.getElementById("finaldiagnosis").value=diagosis;
	    }else{
	    	if(document.getElementById("investigation_advice")){
				document.getElementById("investigation_advice").value=nicEditors.findEditor( "investigation_advice" ).getContent();
			}
	    }
	    document.getElementById("ipdadmissionfrm").submit();
      	    
}

function calculatedatingscan(){
	 // JavaScript program to illustrate  
    // calculation of no. of days between two date  
    // To set two dates to two variables 
    //mm-dd-yyyy
	var lmp = document.getElementById('dating_usg_date').value;
	var weeks_dating_scan = document.getElementById('weeks_dating_scan').value;
	if(lmp==''){
		var lmpd = document.getElementById('lmp').value; 
	    if(lmpd!=''){
	    	calEdd(lmpd);
	    }
	}else{
		var datestr= lmp.split("-");
		
        // JavaScript program to illustrate  
        // calculation of no. of days between two date  
        // To set two dates to two variables 
        //mm-dd-yyyy
        var datestrnew= lmp.split("-");
        var lmdatenew= datestr[1]+"/"+datestr[0]+"/"+datestr[2];
        var date1 = new Date(lmdatenew); 
        
        var today = new Date();
        var dd1 = today.getDate();
        var mm1 = today.getMonth()+1; 
        var yyyy1 = today.getFullYear();
        if(dd1<10) {
        	dd1='0'+dd1;
        } 
        if(mm1<10) {
        	mm1='0'+mm1;
        } 
        var currentdate= mm1+"/"+dd1+"/"+yyyy1;
        var date2 = new Date(currentdate); 
      
	    // To calculate the time difference of two dates 
	    var Difference_In_Time = date2.getTime() - date1.getTime(); 
	      
	    // To calculate the no. of days between two dates 
	    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24); 
	    
		if(weeks_dating_scan==''){
			weeks_dating_scan=0;
		}
		var datestrnew= lmp.split("-");
	    var days_dating_scan = document.getElementById('days_dating_scan').value;
		if(days_dating_scan==''){
			days_dating_scan=0;
		}
		var daysss = weeks_dating_scan * 7;
		daysss =  Number(daysss) + Number(days_dating_scan);
 
	    if(document.getElementById('pog_dating_scan')){
	    	Difference_In_Days = Number(Difference_In_Days) + Number(daysss)
	    	var reminder = Difference_In_Days % 7;
		    var ans = Difference_In_Days / 7;
		    ans = Math.floor(ans);
		    document.getElementById('pog_dating_scan').value=ans+"weeks "+reminder+"days";
	    }
	    var lmpd = document.getElementById('lmp').value; 
	    if(lmpd!=''){
	    	calEdd(lmpd);
	    }
	    
		
	}
	
}

function disablePAFiled(val){
	if(val==true){
		document.getElementById("fnhide_dv").className="col-lg-3 col-xs-3 col-md-3 hidden";
		document.getElementById("fnhide_div").className="col-lg-6 col-xs-6 col-md-6 hidden";
		document.getElementById("fnhide_div1").className="col-lg-2 col-xs-2 col-md-2 hidden";
		document.getElementById("fnhide_div2").className="col-lg-2 col-xs-2 col-md-2 hidden";
		document.getElementById("fnhide_div3").className="col-lg-2 col-xs-2 col-md-2 hidden";
	}else{
		document.getElementById("fnhide_dv").className="col-lg-3 col-xs-3 col-md-3";
		document.getElementById("fnhide_div").className="col-lg-6 col-xs-6 col-md-6";
		document.getElementById("fnhide_div1").className="col-lg-2 col-xs-2 col-md-2";
		document.getElementById("fnhide_div2").className="col-lg-2 col-xs-2 col-md-2";
		document.getElementById("fnhide_div3").className="col-lg-2 col-xs-2 col-md-2";
	}
}

function setcollapsOrNot(id,val){
	if(val){
		document.getElementById(id).className="collapse in";
	}else{
		document.getElementById(id).className="collapse";
	}
}

function deleteGynicRow(r,tablename){
	var i = parseInt(r);
	document.getElementById(tablename).deleteRow(i);		
}


function addObsRowNew(num,type) {
	  
    var i=parseInt(num);
    for(var j=0;j<i;j++) {
    	
            var table = document.getElementById("obstable");
			//var rowCount = table.rows.length;
			var rowCount = Number(document.getElementById("parityabortioncount").value)
			row=table.insertRow(rowCount);
			var str= '<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value='+(rowCount+1)+' class="form-control"></td> '
	     	+ '<td style="width: 8%;padding-right: 15px;"><input type="number" name="obslist['+rowCount+'].year" value="" class="form-control" placeholder="year"></td>'
			+ '<td style="width: 7%;padding-right: 15px;">'+type+' <input type="hidden" value="'+type+'" name="obslist['+rowCount+'].type" /></td>'
			+ '<td style="width: 13%;padding-right: 15px;">'
			+ '<select name="obslist['+rowCount+'].type_delivery" class="form-control">'
			+ '<option value="0">Type of Delivery</option>'
			+ '<option value="Normal">Normal</option>'
			+ '<option value="Vaccume">Vaccume</option>'
			+ '<option value="Forceps">Forceps</option>'
			+ '<option value="LSCS">LSCS</option>'
			+ '</select>' 
			+ '</td>'
			+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].indications" value="" class="form-control" placeholder="Indications"></td>'
			+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].coamplications" value="" class="form-control" placeholder="Coamplications"></td>'
			+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].institution" value="" class="form-control" placeholder="Institution"></td>'
			+ '<td><a class="cursorpointer" onclick=deleteGynicRow('+rowCount+',"obstable") ><i class="fa fa-trash" style="color: #d9534f;"></i></a></td>'; 
			rowCount = rowCount+1;
			document.getElementById("parityabortioncount").value=rowCount;	
           row.innerHTML= str; 
  }
}	


function calculategynicbmi() {
	var weight = document.getElementById("weight").value;
	var height = document.getElementById("height").value;
	if(weight==''){
	}else if(height==''){
	}else{
		var divider =  parseFloat(height)/100;
		var result = parseFloat(weight)/(divider*divider);
		document.getElementById("bmi").value=Math.round(result * 100) / 100;
	}
}




