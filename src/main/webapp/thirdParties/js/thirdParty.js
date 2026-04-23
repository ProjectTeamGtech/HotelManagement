

var name = "";

function showAddThirdPartyPopup(){
	var type = document.getElementById('name').value;
	var chk = 0;
	var name = document.getElementById('name').value;
	
	if (name ==  "0") {
      	var name = document.createElement("label");
        name.innerHTML = "Select Filter Type";
        document.getElementById('nameError').appendChild(name);
       
        chk=1;
     }   
	
	if(chk==1)
    {
       return false;
    }
    else
    {
    	showPopWin("addDetailThirdParty?type="+type+"", 550, 600, null);
       return true;
    }
	//alert(type);
	
}

function showEditThirdPartyPopup(id,thirdPartyId,type,name,salutation,title,department,searchName,telephoneLine,email,emailCc,notes,companyDetails,companyName,address,town,country,postcode,compnyTelephone,fax,web,referenceNo,compnyEmail,warningMsg,accountsNotes,accountMustBeInCredit,accountCreditLimit,accountWarningLimit,defaultApmtBookingConfmTemp,defaultApmtDnaTemp,confContactEmail,dnaContactEmail,dnaLimit,outStandingIvo){
//alert(id);
	showPopWin("editDetailThirdParty?id="+id+"&thirdPartyId="+thirdPartyId+"&type="+type+"&name="+name+"&salutation="+salutation+"&title="+title+"&department="+department+"&searchName="+searchName+"&telephoneLine="+telephoneLine+"&email="+email+"&emailCc="+emailCc+"&notes="+notes+"&companyDetails="+companyDetails+"&companyName="+companyName+"&address="+address+"&town="+town+"&country="+country+"&postcode="+postcode+"&compnyTelephone="+compnyTelephone+"&fax="+fax+"&web="+web+"&referenceNo="+referenceNo+"&compnyEmail="+compnyEmail+"&warningMsg="+warningMsg+"&accountsNotes="+accountsNotes+"&accountMustBeInCredit="+accountMustBeInCredit+"&accountCreditLimit="+accountCreditLimit+"&accountWarningLimit="+accountWarningLimit+"&defaultApmtBookingConfmTemp="+defaultApmtBookingConfmTemp+"&defaultApmtDnaTemp="+defaultApmtDnaTemp+"&confContactEmail="+confContactEmail+"&dnaContactEmail="+dnaContactEmail+"&dnaLimit="+dnaLimit+"&outStandingIvo="+outStandingIvo+"", 550, 600, null);

}
function showListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			}
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

function showListOfTP(){
	document.getElementById('thirdParty_form').submit();
}

/*function setTPType(){
	alert("hi");
 var tptype = document.getElementById('name').value;
 document.getElementById('type1').value = tptype;
 alert(document.getElementById('type1').value);
 $("#type1").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
}*/