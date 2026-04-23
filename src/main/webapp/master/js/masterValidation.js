$(document).ready(function(){
	var validator = $("#master_form").validate({
		
		onsubmit: true,
		rules : {
			occupation : {
				required : true
				
			},	
			
			jobTitle : {
				required : true
			},
			reference : {
				required : true
			}
			
			
		
		},
		messages : {
				
				
			occupation : {
			required : error.occupation.required,
			},	
			
			jobTitle : {
				required : error.jobTitle.required,
			},	
			reference : {
				required : error.reference.required,
			},	
			
		
	},
		
	});
	
	
	
	
});	

function validDiscipline(){
	
	var discipline =  document.getElementById('discipline').value;
	document.getElementById("disciplineError").innerHTML = "";
	if(discipline==""){
		jAlert('error', "Please Enter Discipline Name!", 'Error Dialog');
		setTimeout(function() {
	        $("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		
		//setError3(disciplineErrorl1);
	}else{
		//removeError3(disciplineErrorl1);
		document.getElementById('master_form').submit();
	}
	
	
}