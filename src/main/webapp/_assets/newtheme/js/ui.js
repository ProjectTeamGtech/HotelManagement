function printnursingplancare(printpage)
{
var headstr = "<html><head><title></title></head><body>";
var footstr = "</body>";
var newstr = document.all.item(printpage).innerHTML;
var oldstr = document.body.innerHTML;
document.body.innerHTML = headstr+newstr+footstr;
window.print();
document.body.innerHTML = oldstr;
return false;
}

function startConvertinghoscourse1(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("subjective").value =vtxt;
	  
	 };

	}

function startConvertinghoscourse2(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("objective").value =vtxt;
	  
	 };

	}
function startConvertinghoscourse3(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("diagnosiscare").value =vtxt;
	  
	 };

	}

function startConvertinghoscourse4(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("inferencecare").value =vtxt;
	  
	 };

	}

function startConvertinghoscourse5(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("planningcare").value =vtxt;
	  
	 };

	}

function startConvertinghoscourse6(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("interventioncare").value =vtxt;
	  
	 };

	}

function startConvertinghoscourse7(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("rationalecare").value =vtxt;
	  
	 };

	}


function startConvertinghoscourse8(){
	 var recognition = new webkitSpeechRecognition();
	 recognition.continuous = true;
	 recognition.interimResults = true;
	 recognition.lang = "en-IN";
	 recognition.start();

	 var finalTranscripts = '';
	 recognition.onresult = function(event){
	  var interimtranscripts = '';
	  for(var i=event.resultIndex;i<event.results.length;i++){
	   var transcript = event.results[i][0].transcript;
	   transcript.replace("/n","</br>");
	   
	   if(event.results[i].isFinal){
	    finalTranscripts += transcript;
	   }else{
	    interimtranscripts += transcript;
	   }
	  }
	  var vtxt  = finalTranscripts   + interimtranscripts  ;
	  
	  /*var con = nicEditors.findEditor('subjective').getContent() + vtxt;
	  nicEditors.findEditor('diagnosiscare').setContent(vtxt);*/
	  //var con = document.getElementById("diagnosiscare").value  + vtxt;
	  document.getElementById("evaluationcare").value =vtxt;
	  
	 };

	}

 


function startConverting1(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse1();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }


function startConverting2(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse2();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }

function startConverting3(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse3();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }


function startConverting4(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse4();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }

function startConverting5(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse5();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }


function startConverting6(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse6();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }

function startConverting7(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse7();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }

function startConverting8(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertinghoscourse8();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
	    
	     }

function switchVisibleimmunization() {
    if (document.getElementById('switchVisibleimmunization1')) {

        if (document.getElementById('switchVisibleimmunization1').style.display == 'none') {
            document.getElementById('switchVisibleimmunization1').style.display = 'block';
            document.getElementById('switchVisibleimmunization2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunization1').style.display = 'none';
            document.getElementById('switchVisibleimmunization2').style.display = 'block';
        }
    }
}




/*Immunization*/

function switchVisibleimmunizationindi() {
    if (document.getElementById('switchVisibleimmunizationindi1')) {

        if (document.getElementById('switchVisibleimmunizationindi1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi2').style.display = 'block';
        }
    }
}

function switchVisibleVaccinationData(id){
	$('.aaclass').each(function() { 
 		var val = this.value;
 		if(id!=val){
 			 if (document.getElementById('ddiv_'+val).style.display == 'none') {
 		         document.getElementById('ddiv_'+val).style.display = 'block';
 		     }
 			 
 			 if (document.getElementById('ddiv1_'+val).style.display == 'block') {
		         document.getElementById('ddiv1_'+val).style.display = 'none';
		     }
 		}
 	});
	
	 if (document.getElementById('ddiv_'+id).style.display == 'none') {
         document.getElementById('ddiv_'+id).style.display = 'block';
         document.getElementById('ddiv1_'+id).style.display = 'none';
     }else {
         document.getElementById('ddiv_'+id).style.display = 'none';
         document.getElementById('ddiv1_'+id).style.display = 'block';
     }
}

function printFullPage(){
	$('.aaclass').each(function() { 
 		 var val = this.value;
		 if (document.getElementById('ddiv_'+val).style.display == 'none') {
	         document.getElementById('ddiv_'+val).style.display = 'block';
	     }
		 
		 if (document.getElementById('ddiv1_'+val).style.display == 'block') {
	         document.getElementById('ddiv1_'+val).style.display = 'none';
	     }
 		
 	});
	print();
}


function switchVisibleimmunizationindiinput() {
    if (document.getElementById('switchVisibleimmunizationindi1')) {

        if (document.getElementById('switchVisibleimmunizationindi1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi2').style.display = 'block';
        }
    }
}


function switchVisibleimmunizationindiSixWeek() {
    if (document.getElementById('switchVisibleimmunizationindiSixWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiSixWeek2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiSixWeek2').style.display = 'block';
        }
    }
}


function switchVisibleimmunizationindiSixWeekInput() {
    if (document.getElementById('switchVisibleimmunizationindiSixWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiSixWeek2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiSixWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiSixWeek2').style.display = 'block';
        }
    }
}




function switchVisibleimmunizationindiTenWeek() {
    if (document.getElementById('switchVisibleimmunizationindiTenWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiTenWeek2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiTenWeek2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindiTenWeekInput() {
    if (document.getElementById('switchVisibleimmunizationindiTenWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiTenWeek2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiTenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiTenWeek2').style.display = 'block';
        }
    }
}



function switchVisibleimmunizationindiFourteenWeek() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindiFourteenWeekInput() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeek1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek2').style.display = 'block';
        }
    }
}




function switchVisibleimmunizationindiFourteenWeekSixMonths() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindiFourteenWeekSixMonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekSixMonths2').style.display = 'block';
        }
    }
}





function switchVisibleimmunizationindiFourteenWeekNineMonths() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths2').style.display = 'block';
        }
    }
}


function switchVisibleimmunizationindiFourteenWeekNineMonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeekNineMonths2').style.display = 'block';
        }
    }
}




function switchVisibleimmunizationindiFourteenWeek9To12Months() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months2').style.display = 'block';
        }
    }
}



function switchVisibleimmunizationindiFourteenWeek9To12MonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1')) {

        if (document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindiFourteenWeek9To12Months2').style.display = 'block';
        }
    }
}



function switchVisibleimmunizationindi12Months() {
    if (document.getElementById('switchVisibleimmunizationindi12Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi12Months1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Months2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi12Months1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi12Months2').style.display = 'block';
        }
    }
}




function switchVisibleimmunizationindi12MonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindi12Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi12Months1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Months2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi12Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Months2').style.display = 'block';
        }
    }
}


function switchVisibleimmunizationindi15Months() {
    if (document.getElementById('switchVisibleimmunizationindi15Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi15Months1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi15Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi15Months2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi15Months1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi15Months2').style.display = 'block';
        }
    }
}



function switchVisibleimmunizationindi15MonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindi15Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi15Months1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi15Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi15Months2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi15Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi15Months2').style.display = 'block';
        }
    }
}



function switchVisibleimmunizationindi16To18Months() {
    if (document.getElementById('switchVisibleimmunizationindi16To18Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi16To18Months2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi16To18Months2').style.display = 'block';
        }
    }
}
function switchVisibleimmunizationindi16To18MonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindi16To18Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi16To18Months2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi16To18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi16To18Months2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindi18Months() {
    if (document.getElementById('switchVisibleimmunizationindi18Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi18Months1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi18Months2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi18Months1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi18Months2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindi18MonthsInput() {
    if (document.getElementById('switchVisibleimmunizationindi18Months1')) {

        if (document.getElementById('switchVisibleimmunizationindi18Months1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi18Months2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi18Months1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi18Months2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindi12Years() {
    if (document.getElementById('switchVisibleimmunizationindi12Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi12Years1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Years2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi12Years1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi12Years2').style.display = 'block';
        }
    }
}
function switchVisibleimmunizationindi12YearsInput() {
    if (document.getElementById('switchVisibleimmunizationindi12Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi12Years1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Years2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi12Years2').style.display = 'block';
        }
    }
}


function switchVisibleimmunizationindi4to6Years() {
    if (document.getElementById('switchVisibleimmunizationindi4to6Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi4to6Years2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi4to6Years2').style.display = 'block';
        }
    }
}
function switchVisibleimmunizationindi4to6YearsInput() {
    if (document.getElementById('switchVisibleimmunizationindi4to6Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi4to6Years2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi4to6Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi4to6Years2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindi10to12Years() {
    if (document.getElementById('switchVisibleimmunizationindi10to12Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display == 'none') {
            document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi10to12Years2').style.display = 'none';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display = 'none';
            document.getElementById('switchVisibleimmunizationindi10to12Years2').style.display = 'block';
        }
    }
}

function switchVisibleimmunizationindi10to12YearsInput() {
    if (document.getElementById('switchVisibleimmunizationindi10to12Years1')) {

        if (document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display == 'block') {
            document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi10to12Years2').style.display = 'block';
        }
        else {
            document.getElementById('switchVisibleimmunizationindi10to12Years1').style.display = 'block';
            document.getElementById('switchVisibleimmunizationindi10to12Years2').style.display = 'block';
        }
    }
}

 
/*Immunization*/





document.onkeyup = function(e) {
	  /*if (e.which == 78) {
		  openBlankPopup('salepriscPharmacy');
		  alert("New Sale Press N");
	  }*/
	 if (e.altKey && e.which == 83) {
		 newsale();
		   /* alert("Alt + S  Save and Print");*/
		    
	  } 
	/*if(e.ctrlKey && e.altKey && e.which == 83) {
		newsale();
	    alert("Ctrl + Alt + S  Save and Print");
	  } */
	/*else if(e.ctrlKey && e.altKey && e.which == 80) {
		newsale();
	    alert("Ctrl + Alt + P  Save and Print");
	  } */
	/*(e.which == 83) {
		  newsale();
		  alert("Save and Print Press S or P");
	  }*/
	 /* else if (e.which == 80) {
		  newsale();
		  alert("Save and Print Press S or P");
	  }
	 */
	 /* else if (e.which == 67) {
		  clearBalanceTemp();
		  alert("Clear Balance  Press C");
	  }*/
	 /* else if (e.which == 66) {
		 
		document.getElementById("barcode").focus();
		alert("Go to bar code  Press B");
		 
	  }*/
	  else if (e.altKey && e.which == 78) {
		  openBlankPopup('salepriscPharmacy');
		   /*alert("Alt + N New Sale");*/
		    
	  } 
	  else if (e.altKey && e.which == 80) {
		  $('#addPatientDiv').modal( "show" );
		   /*alert("Alt + P  Add New Patient");*/
		    
	  } 
	  else if (e.altKey && e.which == 88) {
		  $('#addPatientDiv').modal('hide')
		  $('#clientSearchDiv').modal('hide')
		   $('#shortcutkeystips').modal('hide')
		   /*alert("Alt + X  Close Popup");*/
		  
	  } 
	  else if (e.altKey && e.which == 65) {
		  showInhousePatientPopup();
		   /*alert("Ctrl + Alt + A  Search Patient");*/
		    
	  } 
	  else if (e.altKey && e.which == 66) {
		  document.getElementById("barcode").focus();
			/*alert("Go to bar code  Press Alt B");*/
		    
	  } 
	  else if (e.altKey && e.which == 68) {
		  document.getElementById("distype").focus();
			/*alert("Go to Discount   Press Alt D");*/
		    
	  } 
	  else if (e.altKey && e.which == 67) {
		  clearBalanceTemp();
			 /* alert("Ctrl + Alt C Clear Balance");*/
		    
	  } 
	 /* else if (e.ctrlKey && e.which == 88) {
		  $('#clientSearchDiv').modal('hide')
		   alert("Ctrl + X  Close Popup");
		    
	  } */
	 /* else if (e.ctrlKey && e.altKey && e.which == 65) {
		  showInhousePatientPopup();
	    alert("Ctrl + Alt + A  Search Patient");
	  } */
	 /* else if (e.ctrlKey && e.altKey && e.which == 66) {
		  document.getElementById("barcode").focus();
			alert("Go to bar code  Press ctrl + Alt B");
	  } */
	  /*else if (e.ctrlKey && e.altKey && e.which == 78) {
		  openBlankPopup('salepriscPharmacy');
		  alert("Ctrl + Alt N New Sale");
	  } */
	 /* else if (e.ctrlKey && e.altKey && e.which == 67) {
		  clearBalanceTemp();
		  alert("Ctrl + Alt C Clear Balance");
	  } */
	 /* else if (e.ctrlKey && e.altKey && e.shiftKey && e.which == 85) {
	    alert("Ctrl + Alt + Shift + U shortcut combination was pressed");
	  }*/
	
	};

/*autoClick*/
	function autoClick(){
		if(document.getElementById('location')){
			if(document.getElementById('location').value==0){
				jAlert('error', 'Please select location.', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
			}else{
				document.getElementById('inhousepid').click();
			}
		}else{
			document.getElementById('inhousepid').click();
		}
		
		}