/**
 * 
 */
var cell;
var row;
var gcatgory = 0;
function addRow(tableID) {
	
	var xpayment = document.getElementById('epayment').value;
	
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);
	var url = "addnewrowExpenceManagement?tableid="+table+"&rowcount="+rowCount+"&xpayment="+xpayment+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

}

function addRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          row.innerHTML=req.responseText;	
		          
		          $(".casedate").each(function() { //loop through each checkbox
		              var vid = this.id;
		       	   $('#'+vid).datepicker({
		       	    dateFormat : 'dd/mm/yy',
		       	    yearRange: yearrange,
		       	    minDate : '30/12/1880',
		       	    changeMonth : true,
		       	    changeYear : true
		       	   });  
		       	   });
		          
		          
		          if(document.getElementById("xpayment").value=='Payment'){
		        	  $('.caseddd').each(function() { //loop through each checkbox
		                  // this.checked = true;  //select all checkboxes with class "checkbox1"  
		      			  var d = this.id ;
		      			  document.getElementById(d).value = 0;
		      			  document.getElementById(d).readOnly  = 'readonly';
		               });
		          }
		          
		          
		          var config = {
		        	      '.chosen-select'           : {},
		        	      '.chosen-select-deselect'  : {allow_single_deselect:true},
		        	      '.chosen-select-no-single' : {disable_search_threshold:10},
		        	      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
		        	      '.chosen-select-width'     : {width:"100%"}
		        	    }
		        	    for (var selector in config) {
		        	      $(selector).chosen(config[selector]);
		        	    }
		          
		          // credit
		          if(document.getElementById("xpayment").value=='Receipt'){
		        	  $('.caseccc').each(function() { //loop through each checkbox
		                  // this.checked = true;  //select all checkboxes with class "checkbox1"  
		      			  var d = this.id ;
		      			  document.getElementById(d).value = 0;
		      			  document.getElementById(d).readOnly  = 'readonly';
		               });
		          }
		          
		          if(document.getElementById("epayment").value=='Purchase'){
		        	  document.getElementById("paymntvchrrowid").style.display = 'none';
		      		document.getElementById("upperrowvoucherid").style.display = 'none';
		      		
		        	  $('.caseccc').each(function() { //loop through each checkbox
		                  // this.checked = true;  //select all checkboxes with class "checkbox1"  
		      			  var d = this.id ;
		      			  document.getElementById(d).value = 0;
		      			  document.getElementById(d).readOnly  = 'readonly';
		               });
		          }
	         }
		}	 
}

function editPaymentVoucher(id) {
	
	
	getPaymentVoucherData(id);
	
	$('#editModel').modal( "show" ); 
}

function getPaymentVoucherData(id) {
	
	var url = "getVoucherExpenceManagement?selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
            
    req.onreadystatechange = getPaymentVoucherDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function getPaymentVoucherDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var tmp=req.responseText;
            var data=tmp.split("*");	
            document.getElementById("edtid").value=data[0];
			document.getElementById("edtdate").value=data[1];
			document.getElementById("edtamt").value=data[2];
			document.getElementById("edtmerchant").value=data[3];
			document.getElementById("edtcat").value=data[4];
			document.getElementById("edtcomments").value=data[6];
			document.getElementById("edtpaidby").value=data[5];
			document.getElementById("edtcurrency").value=data[8];
			
		}
	}
}

var isEditVoucher=false;
function editPaymentVoucherNew(id) {
	isEditVoucher=true;
	
	getPaymentVoucherDataNew(id);
	
	
	
	document.getElementById("adnewbtnid").style.display = 'none';
	document.getElementById("contratrans").disabled=true;
	document.getElementById("ledgername").disabled=true;
	document.getElementById("category").disabled=true;
	document.getElementById("editaction").value="1";
	
	$('#exampleModal').modal( "show" ); 
}



function getPaymentVoucherDataNew(id) {
	
	var url = "getVoucherNewExpenceManagement?selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
            
    req.onreadystatechange = getPaymentVoucherDataNewRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function getPaymentVoucherDataNewRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var tmp=req.responseText;
            var data=tmp.split("*");	
          /*  document.getElementById("edtid").value=data[0];
			document.getElementById("edtdate").value=data[1];
			document.getElementById("edtamt").value=data[2];
			document.getElementById("edtmerchant").value=data[3];
			document.getElementById("edtcat").value=data[4];
			document.getElementById("edtcomments").value=data[6];
			document.getElementById("edtpaidby").value=data[5];
			document.getElementById("edtcurrency").value=data[8];*/
			
			/*document.getElementById("edtid").value=data[0];*/
			document.getElementById("datev").value=data[1];
			document.getElementById("comments1").value=data[6];
			if(document.getElementById("debit1")){
				document.getElementById("debit1").value=data[2];
			}
			if(document.getElementById("debit0")){
				document.getElementById("debit0").value=data[2];
			}
			document.getElementById("basic").value=data[8];
			document.getElementById("debiorname").value=data[9];
			document.getElementById("ledgername").className="";
			
			document.getElementById("ledgername").value=data[10];
			$("#ledgername").trigger("chosen:updated");
	  		$(".chosen").chosen({allow_single_deselect: true});
	  		
	  		document.getElementById("category").value=data[4];
	  		if(document.getElementById("credit1")){
	  			document.getElementById("credit1").value=data[11];
	  		}
	  		if(document.getElementById("credit0")){
	  			document.getElementById("credit0").value=data[11];
	  		}
			document.getElementById("contratrans").value=data[12];
			
			$("#contratrans").trigger("chosen:updated");
	  		$(".chosen").chosen({allow_single_deselect: true});
			
	  		
	  		
			
			document.getElementById("dbitspanid").innerHTML=data[2];
			document.getElementById("creditspanid").innerHTML=data[11];
			
			var xpayment = data[13];
			
			gcatgory = data[4];
			showselectedvouchrpopup(xpayment);
			
			
			
		}
	}
}

function commonConfirmedDelete(){

	var r=confirm("Are you sure you want to cancel this entry");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }

	}   



function addExpensesPopUp() {
	//  04 oct 2017 by default todate set
	var data = document.getElementById("defaultdate").value;
	document.getElementById("date").value = data;
	//$('#exampleModal').modal( "show" ); 
	
	$('#selectvouchermodelid').modal( "show" );
}

function showselectedvouchrpopup(vc){
	
	document.getElementById("xpayment").value = vc;
	$('#selectvouchermodelid').modal( "hide" );
	
	document.getElementById("epayment").value = vc;
	document.getElementById("headselectedvoucherid").innerHTML = vc + ' Voucher';
	
	
	if(vc=='Payment'){
		document.getElementById("paymnttodivid").style.display = 'none';
		document.getElementById("contradivid").style.display = 'none';
		
		//hide debit text
		$('.caseddd').each(function() { //loop through each checkbox
            // this.checked = true;  //select all checkboxes with class "checkbox1"  
			  var d = this.id ;
			  document.getElementById(d).value = 0;
			  document.getElementById(d).readOnly  = 'readonly';
         });
		ledgerlistontype(vc);
	}
	if(vc=='Receipt'){
		document.getElementById("paymntvchrrowid").style.display = 'none';
		document.getElementById("contradivid").style.display = 'none';
		
		$('.caseccc').each(function() { //loop through each checkbox
            // this.checked = true;  //select all checkboxes with class "checkbox1"  
			  var d = this.id ;
			  document.getElementById(d).value = 0;
			  document.getElementById(d).readOnly  = true;
         });
		ledgerlistontype(vc);
	}
	
	if(vc=='Contra'){
		document.getElementById("paymntvchrrowid").style.display = 'none';
		document.getElementById("paymnttodivid").style.display = 'none';
		showbanknamelistajax();
		
		
	}
	
	if(vc=='Journal'){
		document.getElementById("paymntvchrrowid").style.display = 'none';
		document.getElementById("upperrowvoucherid").style.display = 'none';
		
	}
	
	if(vc=='Opening'){
		document.getElementById("paymntvchrrowid").style.display = 'none';
		document.getElementById("upperrowvoucherid").style.display = 'none';
		
	}
	
	if(vc=='Purchase'){
		document.getElementById("paymntvchrrowid").style.display = 'none';
		document.getElementById("upperrowvoucherid").style.display = 'none';
		
		$('.caseccc').each(function() { //loop through each checkbox
            // this.checked = true;  //select all checkboxes with class "checkbox1"  
			  var d = this.id ;
			  document.getElementById(d).value = 0;
			  document.getElementById(d).readOnly  = true;
         });
		
	}
	
	
	
	$('#exampleModal').modal( "show" ); 
	
}

function showbanknamelistajax(){
	var xpayment = document.getElementById("xpayment").value;
	var url = "bnkExpenceManagement?xpayment="+xpayment+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
            
    req.onreadystatechange = showbanknamelistajaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function showbanknamelistajaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("expencetypetdid").innerHTML = req.responseText;
			
			if(document.getElementById("editaction").value==1){
				document.getElementById("category").value=gcatgory;
				document.getElementById("category").disabled=true;
			}
			
		}
	}
}

function govcreload(){
	window.location.reload();
}

// Adarsh 13march fro cancel expense
function deleteExpenceManagement(val){
	document.getElementById("cancelexpenseid").value = val;
	$('#deletemodel').modal( "show" );
}

function deleteExpenceDetails(){
	 var id = document.getElementById("cancelexpenseid").value;
		var delete_reason = document.getElementById("delete_reason").value;
		var adarsh = "deletedataExpenceManagement?id="+id+"&delete_reason="+delete_reason+"";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deleteExpenceDetailsRequest;
		  req.open("GET", adarsh, true); 
		  req.send(null);  
  }
	function deleteExpenceDetailsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();	 
		         }
			}	 
} 
	var dtotal = 0;
	var ctotal = 0;
	function showcvdbittotal(){
		 dtotal = 0;
		  $('.caseddd').each(function() { //loop through each checkbox
             // this.checked = true;  //select all checkboxes with class "checkbox1"  
			  var d = this.value ;
			  if(d==''){
				  d = 0;
			  }
			  dtotal = parseFloat(dtotal) + parseFloat(d) ;
          });
		  document.getElementById("dbitspanid").innerHTML = dtotal;
	}
	
	function showcvcredittotal(){
		 ctotal = 0;
		  $('.caseccc').each(function() { //loop through each checkbox
           // this.checked = true;  //select all checkboxes with class "checkbox1"   
			  var d = this.value ;
			  if(d==''){
				  d = 0;
			  }
			  ctotal = parseFloat(ctotal) + parseFloat(d) ;
        });
		  document.getElementById("creditspanid").innerHTML = ctotal;
	}
	
	
	function deleteRow(r){
		   
        var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("tableexpence").deleteRow(i);
    	
    	
    
}
	function showVendorBalance(name){
		 //  19-12-2018 ledger inventory total balance amount
		var dataObj={
			  	"name":""+name+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "showvendorbalanceExpenceManagement",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			  document.getElementById("vendortotalamt").innerHTML=data.totalAmt;
	          document.getElementById("vendorpaidamt").innerHTML=data.sumofpayamount;
	          document.getElementById("vendorreturnamt").innerHTML= data.sumofreturn;
	          document.getElementById("vendorbalanceamt").innerHTML= data.balance;
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		});
		
	}

	
	function showLedgerAccountdata(){
		if(document.getElementById("expenseType").value==0){
			 jAlert('error', "Please Select Ledger!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			openPopup('ExpenceManagement?action=1');
		}
		
	} 
	
	
	function showsupplierledaccount(){
		
		document.getElementById("viewldgerfrm").submit();
	}
	
	function refreshlegdersetcode() {
		 $("#dashboardloaderPopup").modal('show');
		openSamePopup('refreshlegdersetcodeExpenceManagement');
	}
	

	function ledgerlistontype(type){
		if((type=='Payment'||type=='Receipt')&&(!isEditVoucher)){
			var url = "listOfLedgersCommonnew?type="+type;
			  if (window.XMLHttpRequest) {
				  req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				  isIE = true;
				  req = new ActiveXObject("Microsoft.XMLHTTP");
			  }   
			  req.onreadystatechange = ledgerlistontypeRequest;
			  req.open("GET", url, true); 
			  req.send(null);  
		}
		
	}	

	function ledgerlistontypeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('ledgernamediv').innerHTML= req.responseText;
				$("#ledgername").trigger("chosen-select:updated");
		  		$(".chosen-select").chosen({allow_single_deselect: true});
				
			}
		}
	}