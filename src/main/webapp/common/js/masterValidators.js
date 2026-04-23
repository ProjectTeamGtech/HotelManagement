
  /*****************************************/
 /*<<<<<<<<JS Validators Plugin>>>>>>>>>>>*/
/*****************************************/

//function to set error for client side validation
function setError(element) {
    $(element).css("background-color", "#F2DEDE");
    $(element).parent().addClass("has-error");
    $(element).focus();
    $(element).tooltip('show');
}

//function to remove error for client side validation
function removeError(element) {
    $(element).css("background-color", "#FFFFFF");
    if ($(element).parent().hasClass("has-error")) {
        $(element).parent().removeClass("has-error");
    }
    $(element).tooltip('hide');
}

function validateCompulsaryFeild(elem, regEX) {
    var elemValue = $(elem).val();
    if (elemValue.length == 0 || elemValue == undefined || elemValue == null || elemValue == "") {
        setError(elem);
        return false;
    }
    if (regEX.test(elemValue)) {
        removeError(elem);
        return true;
    }
    else {
        setError(elem);
        return false;
    }
}


function validateOptionalFeild(elem, regEX) {
    var elemValue = $(elem).val();
    if (elemValue.length = 0 || elemValue == undefined || elemValue == null || elemValue == "") {
        removeError(elem);
        return true;
    }
    else {
        if (regEX.test(elemValue)) {
            removeError(elem);
            return true;
        }
        else {
            setError(elem);
            return false;
        }
    }
}

function webmailOptional(elem){
	var regEx =  /(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	return validateOptionalFeild(elem, regEx);
}

function webmailCompulsary(elem){
	var regEx =  /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	return validateCompulsaryFeild(elem, regEx);
}

function numbersOnlyCompulsary(elem){
	var regEx = /^[0-9]+$/;
	return validateCompulsaryFeild(elem, regEx);
}

function numbersOnlyOptional(elem){
	var regEx = /^[0-9]+$/;
	return validateOptionalFeild(elem, regEx);
}

function nonZeroTenDigitNumberOnly(elem) {
    var regEx =  /^[1-9]{1,10}$/;
    return validateCompulsaryFeild(elem, regEx);
}

function nonZeroTwoDigitNumberOnly(elem) {
    var regEx = /^[1-9]\d{2}$/;
    return validateCompulsaryFeild(elem, regEx);
}

function mobileNo(elem) {
	//elem = '0'+elem;
    var regEx = /^[0789]\d{10}$/;
    var elemValue = $(elem).val();
    if (elemValue != undefined || elemValue != null || elemValue != "") {
    	elemValue = '0'+elemValue;
    }
    if (elemValue == undefined || elemValue == null || elemValue == "") {
        setError(elem);
        return false;
    }else if(regEx.test(elemValue)==false){
    	 setError(elem);
         return false;
    }else {
        removeError(elem);
        return true;
    }
  // return validateCompulsaryFeild(elem, regEx);
}

function maxeightchar(elem){
	var regEx = /^[A-Za-z0-9 ]{4,20}$/;
	 return validateCompulsaryFeild(elem, regEx);
}

function mobileNoOptional(elem) {
    var regEx = /^[A-Za-z0-9_]{3,20}$/;
    return validateOptionalFeild(elem, regEx);
}

function phoneNo(elem) {
    var regEx = /\d{10,12}/;
    return validateCompulsaryFeild(elem, regEx);
}

function phoneOptional(elem) {
    var regEx = /\d{10,12}/;
    return validateOptionalFeild(elem, regEx);
}

function alphabetsOnlyWithSpace(elem) {
    var regEx = /^[a-zA-Z-]*$/;
    return validateCompulsaryFeild(elem, regEx);
}


function alphabetsOnlyWithSpaceOptional(elem) {
    var regEx = /^[a-zA-Z-]*$/;
    return validateOptionalFeild(elem, regEx);
}


function alphabetsOnlyWithoughtSpace(elem) {
    var regEx = /^[a-zA-Z]*$/;
    return validateCompulsaryFeild(elem, regEx);
}

function validDoubleValuesOptional(elem){
	//var regEx = /^[0-9]*\.[0-9]$/;
	var regEx = /^(?:0|[1-9][0-9]*)\.[0-9]+$/;
	return validateOptionalFeild(elem, regEx);
}
function validDoubleValuesCompulsary(elem){
	var regEx = /^[0-9]*\.[0-9]$/;
	return validateCompulsaryFeild(elem, regEx);
}

function alphabetsOnlyWithoughtSpaceOptional(elem) {
    var regEx = /^[a-zA-Z]*$/;
    return validateOptionalFeild(elem, regEx);
}

function emailId(elem) {
    var regEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return validateCompulsaryFeild(elem, regEx);
}

function emailIdOptional(elem) {
    var regEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return validateOptionalFeild(elem, regEx);
}

function zipCode(elem) {
    var regEx = /^[0-9]\d{5}$/;
    return validateCompulsaryFeild(elem, regEx);
}

function zipCodeOptional(elem) {
    var regEx = /^[0-9]\d{5}$/;
    return validateOptionalFeild(elem, regEx);
}

function dropDown(elem) {
    var elemValue = $(elem).val();
    if (elemValue == 0 || elemValue == "0") {
        setError(elem);
        return false;
    }
    else {
        removeError(elem);
        return true;
    }
}

function notEmpty(elem) {
    var elemValue = $(elem).val();
    if (elemValue == undefined || elemValue == null || elemValue == "") {
        setError(elem);
        return false;
    }
    else {
        removeError(elem);
        return true;
    }
}

function password(elem1, elem2) {
    var elem1Value = $(elem1).val();
    var elem2Value = $(elem2).val();
   var rg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{8,}$/;
    if (elem1Value == undefined || elem1Value == null || elem1Value == "") {
        setError(elem1);
        return false;
    }else if(rg.test(elem1Value)==false){
    	setError(elem1);
    }else if (elem1Value != elem2Value) {
        setError(elem2);
        return false;
    }
    else {
        removeError(elem1);
        removeError(elem2);
        return true;
    }
}

function validateChkGrp(elem){
	var result=false;
	$(elem).each(function (){
		if($(this).prop('checked')){
			result=true;
		}
	});	
	
	if(!result){
		setError(elem);
	}
	else{
		removeError(elem);
	}
	return result;
}


function iterateThroughtElemsForValidations(jsonObj) {
    var i = 0;
    for (key in jsonObj.validatorData) {
        if (jsonObj.validatorData[i].FunctionName == "password") {
            if (window[jsonObj.validatorData[i].FunctionName](jsonObj.validatorData[i].Element, jsonObj.validatorData[i].ElementToCompare)) {

            }
            else {
                return false;
            }
        }
        else {
            if (window[jsonObj.validatorData[i].FunctionName](jsonObj.validatorData[i].Element)) {

            }
            else {
                return false;
            }
        }
        i++;
    }
    return true;
}

  /*****************************************/
 /*<<<<<<<<JS Validators Plugin>>>>>>>>>>>*/
/*****************************************/