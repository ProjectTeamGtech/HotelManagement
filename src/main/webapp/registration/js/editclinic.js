// refresh captcha by clicking refresh button
	/*$("#captcha_refresh").click(function() {
	
		var date = new Date();
		// get new image captcha, and set it as a source of captcha image
		// date.getTime() is added to make request different from previous requests
		// since requests are cached by browser, so captcha does not change if random 
		// parameters not added to request, in our case random parameter is time in milliseconds
		$("#captcha_image").attr("src","/tiles/images/jcaptcha.jpg?"+ date.getTime());
	});*/
	
	var color2 =" ";
	
	
	
	
	$(document).ready(function(){
	
	var validator = $("#cliniclist_form").validate({
		onsubmit: true,
		rules : {
			userId : {
				required : true,
				//minlength : constraint.userId.minlength,
				nowhitespace : true
				//remote: 'CheckUserId'
			},	
			
			
			pkgsubscription: {
              required: true
              //minlength: 1
          	},
          	funSubscription : {
          		required : true
          	},
          	
          	clinicname : {
          		required : true,
          		regex :/^[a-zA-Z ]+$/
          	},
          	
          	clinicOwner : {
          		required : true,
          		regex :/^[a-zA-Z ]+$/
          	},
          	
          	name : {
				required : true,
				regex :/^[a-zA-Z ]+$/
			},
			pinCode : {
				integer : true,
				required : true
			},
			email : {
				required : true,
				email : true,
				//remote: '/UnlockJobs/user/CheckEmailId' // Action will be invoked to check if user with this email id already exist (Ajax call)
			},
			country : {
					required : true,
			},
			city : {
				required : true
			},
			address : {
				required : true
			},
			
			mobileNo : {
				required : true,
				mobilenumber : true
			},
			
			captchaCode : {
				required : true
			}	
			
				
		},
		
		
		messages : {	
			userId : {
				required : error.userId.required,
				minlength : jQuery.format(error.userId.minLength),	// format function replaces {0} with actual minimum length
				nowhitespace : error.userId.nowhitespace
				//remote: error.userId.alreadyExist
			},	
			
			
			/*pkgsubscription: {
				required : error.pkgsubscription.required
			},
			
			funSubscription : {
				required : error.funSubscription.required
			},*/
			
			clinicname : {
				required : error.clinicname.required,
				regex : error.clinicname.alphabets
			},
				
			clinicOwner : {
				required : error.clinicOwner.required,
				regex : error.clinicOwner.alphabets
			},
			
			name : {
				required : error.name.required,
				regex : error.name.alphabets
			},
			pinCode : {
				integer : error.pinCode.integer,
				required : error.pinCode.required
			},
			email : {
				required : error.email.required,
				email : error.email.email,
				remote: error.email.alreadyExist
			},
			country : {
					required : error.country.required
			},
			city : {
				required : error.city.required
			},
			
			address : {
				required : error.address.required
			},
			
			mobileNo : {
				required : error.mobileNo.required,
				mobilenumber : error.mobileNo.mobilenumber
			},
			
			captchaCode : {
				required : error.captchaCode.required
			}
			
		},
		
	});
	
	
});	


function addRow(tableID) 
        {
        	var table = document.getElementById(tableID);

                var rowCount = table.rows.length;
                var row = table.insertRow(rowCount);
                var counts = rowCount - 1;

                var cell1 = row.insertCell(0);
                var houseNo = document.createElement("input");
                houseNo.type = "text";
                houseNo.name = "location[" + counts + "].country";
                cell1.appendChild(houseNo);

                var cell2 = row.insertCell(1);
                var street = document.createElement("input");
                street.type = "text";
                street.name = "location[" + counts + "].city";
                cell2.appendChild(street);

                var cell3 = row.insertCell(2);
                var city = document.createElement("input");
                city.type = "text";
                city.name = "location[" + counts + "].pinCode";
                cell3.appendChild(city);

                var cell4 = row.insertCell(3);
                var country = document.createElement("textarea");
                country.type = "text";
                country.name = "location[" + counts + "].address";
                cell4.appendChild(country);

        }

function setColor(id){
	
	color2 = $("#"+id+" :selected").text();
	//alert(color2);
		
	
	var txt=document.getElementById('color1');
	txt.value=color2;
	//alert(document.getElementsById("color1").value);
	
	
}

