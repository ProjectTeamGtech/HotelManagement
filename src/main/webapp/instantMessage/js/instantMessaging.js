var counter = 0;
$(document).ready(function() {
	getAllContacts();

});
function openComposePopup(){
	closeAutoComplete();
	$( '#composeEmail' ).modal( "show" );
}
function closeAutoComplete(){
	if ($('#AutocompleteContainer').css('display') == 'block') {
		$("#AutocompleteContainer").css('display', 'none');
	} 
}
var UserEmailsAutocomplete;
function getAllContacts() {
	$.ajax({
		url : "instantMessage/pages/JQGridAllContacts.jsp",
		dataType : "json",
		success : function(json) {

			var data = JSON.parse(JSON.stringify(json));
			UserEmailsAutocomplete = data;

		}
	});
}
$(document).on(
		'click',
		"#UlAutocomplete li",
		function() {

			if ($('#' + $(this).attr("data-txtId")).val() == '') {
				$('#' + $(this).attr("data-txtId")).val(
						$(this).find(".spanEmail").html());
			} else {
				var txt = $('#' + $(this).attr("data-txtId")).val();
				$('#' + $(this).attr("data-txtId")).val(
						txt + ',' + $(this).find(".spanEmail").html());
			}
			if ($('#AutocompleteContainer').css('display') == 'block') {
				$("#AutocompleteContainer").css('display', 'none');
			} else {
				$("#AutocompleteContainer").css('display', 'block');
			}

		});
//$(document).on('click', "#searchText,#toEmailId,#ccEmailId", function() {
	$(document).on('click', "#searchText", function() {

	if ($('#AutocompleteContainer').css('display') == 'block') {
		$("#AutocompleteContainer").css('display', 'none');
	} else {
		$("#AutocompleteContainer").css('display', 'block');
	}

	// $("#AutocompleteContainer").css('display','block');
	PopulateAutocomplete('', $(this).attr('id'));
	var offset = $(this).offset();
	$("#AutocompleteContainer").css('top', (offset.top + 27));
	$("#AutocompleteContainer").css('left', offset.left);
});
	//$(document).on('keypress', "#searchText,#toEmailId,#ccEmailId", function() {
$(document).on('keypress', "#searchText", function() {

	PopulateAutocomplete($(this).val(), $(this).attr('id'));
});
function PopulateAutocomplete(txt, txtid) {
	$("#UlAutocomplete").html('');
	for (var i = 0; i < UserEmailsAutocomplete.jsonOutResult.length; i++) {
		if (txt == '') {
			$("#UlAutocomplete")
					.append(
							'<li class="btn btn-default"  data-txtId="'
									+ txtid
									+ '">'
									+ UserEmailsAutocomplete.jsonOutResult[i].name
									+ '('
									+ UserEmailsAutocomplete.jsonOutResult[i].designation
									+ ')<br><span class = "spanEmail">'
									+ UserEmailsAutocomplete.jsonOutResult[i].emailId
									+ '</span></li>');
		} else {
			if (UserEmailsAutocomplete.jsonOutResult[i].emailId.indexOf(txt) != -1) {
				$("#UlAutocomplete")
						.append(
								'<li class="btn btn-default"  data-txtId="'
										+ txtid
										+ '">'
										+ UserEmailsAutocomplete.jsonOutResult[i].name
										+ '('
										+ UserEmailsAutocomplete.jsonOutResult[i].designation
										+ ')<br><span class = "spanEmail">'
										+ UserEmailsAutocomplete.jsonOutResult[i].emailId
										+ '</span></li>');
			}
		}
	}
}

/*function hiddeDivShow(){
 if($('#hiddeDiv').css('display') == 'block')
 {
 $("#hiddeDiv").css('display','none');
 }
 else
 {
 $("#hiddeDiv").css('display','block');
 }
 }*/



function AddFileUpload()
{
     var div = document.createElement('DIV');
     div.id = counter;
     div.className = "col-lg-8 col-md-8";
     div.innerHTML = '<input id="file" name = "fileUpload" type="file" />'; 
     
     var div1 = document.createElement('DIV');
     div1.id = counter;
     div1.className = "col-lg-4 col-md-4";
     div1.innerHTML =  '<input id="Button' + counter + '" type="button" ' + 'value="Remove" onclick = "RemoveFileUpload('+counter+')" />';
                    
     document.getElementById("FileUploadContainer").appendChild(div);
     document.getElementById("FileUploadContainer").appendChild(div1);
     counter++;
}
function RemoveFileUpload(div)
{
	 var d = document.getElementById('FileUploadContainer');

	  var olddiv = document.getElementById(div);

	  d.removeChild(olddiv);
	  var d = document.getElementById('FileUploadContainer');

	  var olddiv = document.getElementById(div);

	  d.removeChild(olddiv);

}

function showSentMail(){
	
	var url = "sentMailListDisplayInstantMsg";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showSentMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function showSentMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

function showChats(){
	var url = "showChatsInstantMsg";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showChatsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function showChatsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

function showChatDetails(id,senderUserId,receiverUserId){
	var url = "showChatDetailsInstantMsg?id="+id+"&senderUserId="+senderUserId+"&receiverUserId="+receiverUserId+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showChatDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function showChatDetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

function showDetailsOfInboxMail(id){
	var url = "showDetailsOfInboxMailInstantMsg?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showDetailsOfInboxMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function showDetailsOfInboxMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}
function showSentMailDetails(id){
	
	var url = "showDetailsOfSentMailInstantMsg?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showSentMailDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function showSentMailDetailsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

function saveToDraft(){
	var to = document.getElementById('toEmailId').value;
	var subject = document.getElementById('subject').value;
	var emailBody = nicEditors.findEditor( "emailBody" ).getContent();

	//var file = document.getElementById('file').value;
	//alert(emailBody);
	var url = "saveToDraftInstantMsg?to="+to+"&subject="+subject+"&emailBody="+emailBody+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveToDraftRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function saveToDraftRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	         	
				
	         }
		}
}

function showDraftMail(){
	
	var url = "showDraftMailListInstantMsg";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showDraftMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function showDraftMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("listTable").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

$(document).on("click",".draft", function(){
	var id = $(this).attr("data-id");
	var email=$(this).attr("data-receivermailid");
	var sub = $(this).attr("data-sub");
	var emailbody = $(this).attr("data-emailbody");
	$("#toEmailId").val(email);
	$("#subject").val(sub);
	setTimeout(function(){
		$(".nicEdit-main").html(emailbody);
	}, 1000);
	var url = "showDraftAttachmentsInstantMsg?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPopupDraftRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
});

function showPopupDraftRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("draftAttachments").innerHTML = req.responseText;
				
				$( '#composeEmail' ).modal( "show" );
	         	
				
	         }
		}
}
	


function setActionSave(){
	
	$('#composeEmail').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	document.getElementById('actionStatus').value = "Save";
/*	var url = "checkEmailSettingClinicRegistration";

	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCheckEmailRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setCheckEmailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
			
				ver result = req.responseText;
				alert(result);
				if(result == 'false'){
					
					jAlert('error', 'Sorry Mail Not Send.. Change Email Configuration Setting !!', 'Error Dialog');
				}
				
				document.getElementById('actionStatus').value = "Save";
			}
	}*/
	}


function setActionDraft(){
	document.getElementById('actionStatus').value = "Cancel";
}


