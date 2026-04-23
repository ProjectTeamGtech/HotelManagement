
/** Validation error messages for jquery validation
 *  variable name in the format : error.fieldName.jqueryValidationType
 *  eg. error.userId.minlength */
var error = {
		
	/** userId validation messages */
	userId : {
		required : 'Please enter User Id',
		minLength : 'Please enter at least {0} characters',	// {0} will be replaced by jquery format function with proper value
		alphanumeric : 'Please enter letters, numbers and underscore only',
		nowhitespace : 'Sorry, white spaces not allowed',
		firstcharalpha : 'Sorry, first character must be alphabet',
		doubleunderscore : 'Sorry, no continuous "_" allowed',
		alreadyExist : 'User Id already taken, please try another'
	},
	
	/** password validation messages */
	password : {
		required : 'Please enter Password',
		minLength : 'Please enter at least {0} characters'	// {0} will be replaced by jquery format function with proper value
	},
	oldPassword : {
		required : 'Please enter Old Password'
	},
	/** confirmPassword validation messages */
	confirmPassword : {
		required : 'Please re-enter Password',
		minlength : 'Please enter at least {0} characters',	// {0} will be replaced by jquery format function with proper value
		equalTo : 'Please enter same password as above'
	},
	
	/** name validation message */
	name : {
		required : 'Please enter Name',
		alphabets : 'Please enter alphabets only'
	},
	
	firstname : {
		required : 'Please enter First Name',
		alphabets : 'Please enter alphabets only'
	},
	
	lastname : {
		required : 'Please enter Last Name',
		alphabets : 'Please enter alphabets only'
	},
	
	initial : {
		required : 'Please enter Initial',
		alphabets : 'Please enter alphabets only'
	},
	
	jobtitle : {
		required : 'Please enter Job Title',
		alphabets : 'Please enter alphabets only'
	},
	
	diarycolumnposition : {
		required : 'Please enter Diary coloumn position',
		integer : 'Please enter digit only'
	},
	
	compressionrate : {
		required : 'Please enter Compression Rate',
		integer : 'Please enter digit only'
	},
	
	changefre : {
		required : 'Please enter Change Frequently',
		integer : 'Please enter digit only'
	},
	
	/** mobileNo validation messages */
	mobile : {
		required : 'Please enter Mobile Number',
		mobilenumber : 'Please enter valid Mobile Number'
	},
	
	clinicName : {
		required : 'Please enter clinic name',
		alphabets : 'Please enter alphabets only'
	},
	
	clinicOwner : {
		required : 'Please enter clinic owner name',
		alphabets : 'Please enter alphabets only'
	},
	
	pkgsubscription : {
		required : 'Must select atleast one package subscription'
	},
	
	funSubscription : {
		required : 'Must select atleast one functionality subscription'
	},
	
	unit : {
		required : 'Please enter unit'
		
	},
	lbtNo : {
		required : 'Please enter lbt number'
	},
	
	vatTin : {
		required : 'Please enter vat tin'
	},
	
	cstIn : {
		required : 'Please enter cast tin'
	},
	
	foodNo : {
		required : 'Please enter food no'
	},
	
	
	pinCode : {
		required : 'Please enter PinCode',
		integer : 'Please enter digit only'
	},
	/**employerName validation message */
	employerName : {
		required : 'Please Enter  employer Name',
		alphabets : 'Please enter alphabets only'
	},
	
	/**joiningDateYear validation message */
	joiningDateYear : {
		required : 'Please Enter joiningDateYear'
	},
	
	/**joiningDateMonth validation message */
	joiningDateMonth : {
		required : 'Please Enter joiningDateMonth'
	},
	
	/**leavingDateYear validation message */
	leavingDateYear : {
		required : 'Please Enter leavingDateYear'
	},
	
	/**leavingDateMonth validation message */
	leavingDateMonth : {
		required : 'Please Enter leavingDateMonth'
	},
	
	/**jobProfile validation message */
	jobProfile : {
		required : 'Please Enter jobProfile'
	},
	
	/**dateOfBirth validation message */
	dateOfBirth : {
		required : 'Please Enter dateOfBirth'
	},
	
	/**employerLocation validation message */
	employerLocation : {
		required : 'Please Enter employerLocation'
	},
	
	/**client validation message */
	client : {
		required : 'Please client name'
	},
	
	/**date validation message */
	yearid : {
		required : 'Please specify valid date'
	},
	
	/**startYear validation message */
	startYear : {
		required : 'Please Enter Project Start Year'
	},
	
	/**startYear validation message */
	startMonth : {
		required : 'Please Enter Project Start Month'
	},
	
	/**EndYear validation message */
	EndYear : {
		required : 'Please Enter Project End Year'
	},
	
	/**EndMonth validation message */
	EndMonth : {
		required : 'Please Enter Project End Month'
	},
	
	
	
	/**startYear validation message */
	aboutUs : {
		required : 'Please select video profile to uplooad'
	},
	
	/**projectDetails validation message */
	projectDetails : {
		required : 'Please Enter projectDetails'
	},
	
	/**roleInProject validation message */
	roleInProject : {
		required : 'Please Enter roleInProject'
	},
	
	/**teamSize validation message */
	teamSize : {
		required : 'Please Enter teamSize'
	},
	
	/**skillUsed validation message */
	skillUsed : {
		required : 'Please Enter skillUsed'
	},
	
	
	/** profileName validation message */
	profileName : {
		required : 'Please enter profileName'
	},
	

	
	
	/** email validation messages */
	email : {
		required : 'Please enter Email Address',
		email : 'Please enter valid Email Address',
		alreadyExist : "User with this Email Id already exist. (Try <a href='Default'>Login</a>)",
		alreadyExist2 : "User with this Email Id already exist."
	},
	
	/** address validation messages */
	address : {
		required : 'Please enter Address'
	},
	
	/** address validation messages */
	weight : {
		required : 'Please enter weight'
	},
	/** address validation messages */
	flavour : {
		required : 'Please enter flavour'
	},
	/** address validation messages */
	notes : {
		required : 'Please enter model mesign notes'
	},
	/** address validation messages */
	address : {
		required : 'Please enter Address'
	},
	/** address validation messages */
	msgOnCake : {
		required : 'Please enter message on cake'
	},
	/** address validation messages */
	amount : {
		required : 'Please enter amount'
	},
	/** address validation messages */
	deliveryDate : {
		required : 'Please enter deliveryDate'
	},
	/** country validation messages */
	time : {
		selected : 'Please select hour'
	},
	/** country validation messages */
	minute : {
		selected : 'Please select minute'
	},
	/** country validation messages */
	amorpm : {
		selected : 'Please select AM or PM '
	},
	
	
	/** country validation messages */
	country : {
		required : 'Please enter country  name'
	},
	
	
	/** state validation messages */
	state : {
		selected : 'Please select State, if not in list select "Other"',
		required : 'Please specify State or select from list'
	},
	
	/** city validation messgage */
	city : {
		required : 'Please enter city  name'
		
	},
	
	/** mobileNo validation messages */
	mobileNo : {
		required : 'Please enter Mobile Number',
		mobilenumber : 'Please enter valid Mobile Number'
	},
	
	/** landline validation message */
	landLine : {
		integer : 'Please enter valid Landline Number',
		minlength : 'Plaese enter at least {0} digits'	// {0} will be replaced by jquery format function with proper value
	},
	
	/** experience validation messages */
	experience : {
		year : { selected : 'Please select Experience Years'},
		month : { selected : 'Please select Experience Months'}
	},
	
	/** functionArea validation message */
	functionalArea : {
		selected : 'Please select Functional Area',
		required : 'Please specify Functional Area or select from list'
	},
	
	/** keySkill validation message */
	keySkills : {
		required : 'Please specify Key Skills'
	},
	
	/** resumeHeadLine validation message */
	resumeHeadline : {
		required : 'Please specify Resume Headline',
		maxlength : 'Please keep Resume Headline within {0} characters'	
					// {0} will be replaced by jquery format function with proper value
	},
	
	/** graduation validation messages */
	graduation : {
		selected : 'Please select Graduation',
		required : 'Please specify Graduation or select from list'
	},
	
	/** agree to term validation message */
	agreeToTerms : {
		required : 'Please read and agree to Terms and Condition'
	},
	
	/** captcha code validation message  */
	captchaCode : {
		required : 'Please enter verification code, shown in image bellow'
	},
	/** designation validation message */
	designation : {
		required : 'Please enter designation',
		alphabets :'Please enter alphabets only'
	},
	/** company validation message */
	companyName : {
		required : 'Please enter company name'
	},
	
	/** email validation messages */
	officialEmail : {
		required : 'Please enter Email Address',
		email : 'Please enter valid Email Address',
		alreadyExist : "User with this Email Id already exist. (Try <a href='Default'>Login</a>)",
		alreadyExist2 : "User with this Email Id already exist."
	},
	/** Messages for login form */
	login : {
		/** userId validation messages */
		userId : {
			required : 'Please enter User Id',
			minLength : 'User Id should contain minimum {0} characters',	// {0} will be replaced by jquery format function with proper value
			alphanumeric : 'No special characters except "_" in User Id',
			nowhitespace : 'No white spaces in User Id',
			firstcharalpha : 'User ID starts with alphabet',
			doubleunderscore : 'User ID does not contains continuous "_"'
		},
		
		/** password validation messages */
		password : {
			required : 'Please enter Password',
			minLength : 'Password should contain minimum {0} characters'	// {0} will be replaced by jquery format function with proper value
		}
	},
	 /** activationCode validation message */
    activationCode : {
		required : 'Please enter Activation Code',
		minLength : 'Please enter {0} characters'	
    },
     jobId : {
		required : 'Please enter job Id',		
		nowhitespace : 'Sorry, white spaces not allowed',
		noSpecialCharacter : 'Sorry, No special character allow',
		alreadyExist: 'Job id already used by you, please try another'
	},
		/** title validation message */

		/** jobLocation validation message */
	jobLocations : {
		required : 'Please enter job Locations'
	},
		/** requiredSkills validation message */
	requiredSkills : {
		required : 'Please enter requiredSkills'
	},
		/** educationDetails validation message */
	educationDetails : {
		required : 'Please enter education Details'
	},
		/** keywords validation message */
	keywords : {
		required : 'Please enter keywords'
	},
		/** jobDescription validation message */
	jobDescription : {
		required : 'Please enter job Description'
	},	
    
    /** postGraduation validation messages */
    degree : {
   		 selected : 'Please select degree, if not in list select "Other"',
		 required : 'Please specify degree or select from list'
    },
    
    college : {
    	 required : 'Digit not allowed in college name'
    },
    
    extraCurricular : {
    	 required : 'Please specify extraCurricular'
    },
    
     experience : {
    	 required : 'Please enter experience'
    },
    
     achievements : {
    	 required : 'Please enter achievement'
    },
    
     skillType : {
    	 required : 'Please enter skill type'
    },
    
     skill : {
    	 required : 'Please enter skill'
    },
    
    Certification : {
    	 required : 'Please specify Certification'
    },
    
     yearOfPassing : {
    	 required : 'Please enter yearOfPassing'
    },
    
     percentage : {
    	 required : 'Please enter percentage'
    },
    
    postGraduation_college : {
    	required : 'Please enter college'
    },
    
    specialization : {
    	 required : 'Please specify specialization'
    }  ,
     branchName  : {
    	 required : 'Please enter branch name'
    },	
    
    categoryName : {
    	 required : 'Please enter Category name',
    	 alreadyExist : 'Category already taken, please try another'
    },
    
    description : {
    	 required : 'Please enter Description'
    },
    
     categoryID : {
   		 selected : 'Please select category'
   	},
   	
   	productName : {
    	 required : 'Please enter Product Name',
    	 alreadyExist : 'Product already taken, please try another'
    },  	
    quantity : {
    	 required : 'Please enter Quantity'
    },
    
    price : {
    	 required : 'Please enter Price'
    },  
    
    colorName : {
    	 	required : 'Please enter Color'
    },	
    diarycolor : {
    	 	alreadyExist : 'Color already taken, please try another Color'
    },	
      
    color : {
    		required : 'Please select color',
    	 	alreadyExist : 'Color already taken, please try another Color'
    },   
    
    diaryUser : {
    	 selected : 'Please Select Diary User'
    },
    
    dept : {
    	  selected : 'Please Select Department'
    },
    
    date : {
    	 required : 'Please enter Date'
    },
    
    status : {
    	required : 'Must select atleast one Status'
    },
    
    fromDate : {
    	 required : 'Please enter from Date'
    },
     toDate : {
    	 required : 'Please enter to Date'
    },
    
    name : {
    	required : 'Please enter Appointment Name',
    	alreadyExist : ' Already Exist, please try another Color'
    },
    duration : {
    	  selected : 'Select Duration'
    },
    charges : {
    	required : 'Enter Charges',
    	integer : 'Enter valid Number'
    },
    diaryUser : {
    	 selected : 'Select Diary User'
    },
     fromDate : {
    	required : 'Select From Date'
    },
     toDate : {
    	required : 'Select To Date'
    },
    name : {
    	selected : 'Select Filter Type'
    },
    telephoneLine : {
    	integer : 'enter TelePhone No.'
    },
    emailCc : {
    	email : 'Enter Valid Email'
    },
    compnyTelephone : {
    	integer : 'Enter TelePhone No.'
    },
    compnyEmail : {
    	email : 'Enter Valid Email'
    },
    confContactEmail : {
    	email : 'Enter Valid Email'
    },
    dnaContactEmail : {
    	email : 'Enter Valid Email'
    },
    accountCreditLimit : {
    	integer : 'Enter digit only'
    },
    accountWarningLimit : {
		integer : 'Enter digit only'
		    
    },
    type : {
    	required : 'Enter Third Party Type'
    },
    occupation : {
    	required : 'Enter Occupation'
    },
    jobTitle : {
    	required : 'Enter Job Title'
    },
    reference : {
    	required : 'Enter Reference'
    },
    chargeType : {
    	selected : 'Select Charge Type'
    },
    location : {
    	selected : 'Select Location'
    },
    commencing : {
    	required : 'Enter Commencing' 
    },
    title1 :  {
    	selected : 'Select Title'
    },
    firstName : {
    	required : 'Enter First Name'
    },
    gptypeName : {
    	selected : 'Select Surgery'
    },
    
    	
};

/** validation constraints for various form fields */
var constraint = {
	
	userId: {
		minlength : 6
	},
	password : {
		minlength : 6
	},
	landLine : {
		minlength : 6
	},
	resumeHeadline : {
		maxlength : 255
	},
	activationCode: {
	  minlength : 6
	}
	
		
};
