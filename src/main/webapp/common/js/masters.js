var openpopupwidth = 1400;
var openpopupheight = 600;

function openPopup(URL) {
	var oldwindow = window.open(URL, "Client Container",
			"status = 1, height = " + openpopupheight + ", width = "
					+ openpopupwidth + ", resizable = 0,scrollbars=yes");
	oldwindow.focus();
}

function selectAction(id) {

	if (id == 1) {
		openPopup('NewChargeType?selectedid=' + id + '');
	} else if (id == 2) {
		openPopup('AppointmentType?selectedid=' + id + '');
	} else if (id == 3) {
		openPopup('Occupation?selectedid=' + id + '');
	} else if (id == 4) {
		openPopup('JobTitle?selectedid=' + id + '');
	} else if (id == 5) {
		openPopup('Discipline?selectedid=' + id + '');
	} else if (id == 6) {
		openPopup('Reference?selectedid=' + id + '');
	} else if (id == 7) {
		openPopup('ThirdPartyType?selectedid=' + id + '');
	} else if (id == 8) {
		openPopup('Declaration?selectedid=' + id + '');
	} else if (id == 9) {
		openPopup('Specialize?selectedid=' + id + '');
	} else if (id == 10) {
		openPopup('TreatmentType?selectedid=' + id + '');
	} else if (id == 11) {
		openPopup('SourceOfIntro?selectedid=' + id + '');
	} else if (id == 12) {
		openPopup('NotAvailableReason?selectedid=' + id + '');
	} else if (id == 13) {
		openPopup('DischargeOutcome?selectedid=' + id + '');
	} else if (id == 14) {
		openPopup('DischargeStatus?selectedid=' + id + '');
	} else if (id == 15) {
		openPopup('OtherTemplate?selectedid=' + id + '');
	} else if (id == 16) {
		openPopup('InvestigationMaster?selectedid=' + id + '');
	} else if (id == 17) {
		openPopup('InvestigationNameMaster?selectedid=' + id + '');
	} else if (id == 18) {
		openPopup('Prescriptioncategory?selectedid=' + id + '');
	} else if (id == 19) {
		openPopup('Prescriptiondetails?selectedid=' + id + '');
	} else if (id == 20) {
		openPopup('wardlistmasterBed?selectedid=' + id + '');
	} else if (id == 21) {
		openPopup('sectionlistmasterBed?selectedid=' + id + '');
	} else if (id == 22) {
		openPopup('bedlistmasterBed?selectedid=' + id + '');
	} else if (id == 23) {
		openPopup('equipmentlistmasterBed?selectedid=' + id + '');
	}else if (id == 24) {
		openPopup('Expencecategory?selectedid=' + id + '');
	} else if (id == 25) {
			openPopup('DiagnosisProblem?selectedid=' + id + '');
	} else if (id == 26) {
			openPopup('DiagnosisProblemIntervention?selectedid=' + id + '');
	} else if (id == 27) {
			openPopup('Standardcharges?selectedid=' + id + '');
	} else if (id == 28) {
			openPopup('Nursingcategory?selectedid=' + id + '');
	} else if(id==29) {
	       openPopup('Nursingdetails?selectedid=' + id + '');
	} else if(id==30){
	       openPopup('Events?selectedid=' + id + '');   
	} else if(id==31){
		  openPopup('categoriesProductinventory?selectedid='+id+'');   		
	} else if(id==32){
	     openPopup('listsubcategoriesProductinventory?selectedid='+id+'');
	} else if(id==33){
	    openPopup('listbrandProductinventory?selectedid='+id+'');
	} else if(id==34){
	   openPopup('Inventory?selectedid='+id+'');
	   
	} else if(id==35){
	   openPopup('Godown?selectedid='+id+'');
	} else if(id==36){
	   openPopup('Ipdtemplate?selectedid='+id+'');
	   
	} else if(id==37){
	   openPopup('Sharecharge?selectedid='+id+'');   
	}  
	 else if(id==38){
	   openPopup('State?selectedid='+id+'');   
	}
	else if(id==39){
	   openPopup('CityMaster?selectedid='+id+'');   
	}
	
	else if(id==40){
	   openPopup('ShelfMaster?selectedid='+id+'');   
	}
	else if(id==41){
	   openPopup('Dietary?selectedid='+id+'');   
	}
	else if(id==42){
	   openPopup('Dietarydetails?selectedid='+id+'');   
	}
	else if(id==43){
	   openPopup('Ipdformsetting?selectedid='+id+'');   
	}
	else if(id==44){
	   openPopup('PackageMaster?selectedid='+id+'');   
	}
	else if(id==45){
	   openPopup('LocationMaster?selectedid='+id+'');   
	}else if(id==46){
	   openPopup('ProductTypeMaster?selectedid='+id+'');   
	}
	else if(id==47)	{
		openPopup('MedicineTypeMaster?selectedid='+id+''); 
	}
	else if(id==48)	{
		openPopup('InvestigationTemplate?selectedid='+id+''); 
	}
	else if(id==49){
		  openPopup('InvestigationSectionMaster?selectedid='+id+'');
		 }
	else if(id==50){
	    openPopup('TermsConditionMaster?selectedid='+id+'');
	   }
	else if(id==51){
	    openPopup('Vitalmaster?selectedid='+id+'');
	   }
	else if(id==52){
	    openPopup('NABHCatagoryMaster?selectedid='+id+'');
	   }
	else if(id==53){
	    openPopup('NABHSubCatagoryMaster?selectedid='+id+'');
	   }
	else if(id==54){
	    openPopup('NABHAreaMaster?selectedid='+id+'');
	   }
	else if(id==55){
	    openPopup('PriscTemplateMaster?selectedid='+id+'');
	   }
	 else if(id==56){
	     openPopup('SchedulerTask?selectedid='+id+'');
	    }
	 else if(id==57){
	     openPopup('SchedulerSubtask?selectedid='+id+'');
	    }
	 else if(id==59){
	      openPopup('NursingPlan?selectedid='+id+'');
	     }
	  else if(id==60){
	      openPopup('NursingCarePlanning?selectedid='+id+'');
	     }
	  else if(id==61){
	      openPopup('NursingCareIntervention?selectedid='+id+'');
	     }
	  else if(id==62){
	      openPopup('showallbookBook?selectedid='+id+'');
	     }
	  else if(id==63){
	      openPopup('showallbookChapterMaster?selectedid='+id+'');
	     }
	  else if(id==64){
	      openPopup('showallOutSourceMaster?selectedid='+id+'');
	     }
	  else if(id==65){
	      openPopup('showallOutSourceDataMaster?selectedid='+id+'');
	     }
	  else if(id==66){
	       openPopup('showallMedicineTemplateMaster?selectedid='+id+'');
	      }
	  else if(id==67){
	       openPopup('showallFeedackTemplateMaster?selectedid='+id+'');
	      }
	//  20 July 2018
	  else if(id==68){
	       openPopup('showallsharablechargeMaster?selectedid='+id+'');
	      }
	  else if(id==69){
		  checkHavingVaccineAccessOrNot();
	  }else if(id==71){
	       openPopup('listclinicalnotesmsterMaster?selectedid='+id+'');
	      }
	  else if(id==72){
	       openPopup('listclinicalproblemmsterMaster?selectedid='+id+'');
	      }
	  else if(id==73){
	       openPopup('listclinicalintervationMaster?selectedid='+id+'');
	      }
	  else if(id==74){
	       openPopup('listremarksMaster?selectedid='+id+'');
	      }
	  else if(id==75){
	       openPopup('listtaxesMaster?selectedid='+id+'');
	      }
	  else if(id==76){
	       openPopup('listmfgMaster?selectedid='+id+'');
	      }
	  else if(id==77){
	       openPopup('genericnameMaster?selectedid='+id+'');
	      }
	  else if(id==78){
	       openPopup('listdosageMaster?selectedid='+id+'');
	      }
	  else if(id==79){
	       openPopup('listsmsMaster?selectedid='+id+'');
	      }
	  else if(id==80){
	       openPopup('listoutsourcerateMaster?selectedid='+id+'');
	  }
	  else if(id==82){
	       openPopup('allUsersSignMaster?selectedid='+id+'');
	  }
	  else if(id==83){
	       openPopup('listbirthplaceMaster?selectedid='+id+'');
	  }else if(id==84){
		  openSamePopup('payerCreationMaster?selectedid='+id+'');
	  }else if(id==85){
		  openSamePopup('followUpProcedureMaster?selectedid='+id+'');
	  }
	  else if(id==86){
		  openSamePopup('Sitting?selectedid='+id+'');
	  }
	  else if(id==87){
		  openSamePopup('Unit?selectedid='+id+'');
	  }
	  else if(id==88){
		  openSamePopup('Procedure?selectedid='+id+'');
	  }
	  else if(id==90){
		  openSamePopup('Ambulance?selectedid='+id+'');
	  }
	
}

function checkHavingVaccineAccessOrNot(){
	var url ="checkvaccinationaccessBookAppointmentAjax";
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange = checkHavingVaccineAccessOrNotRequest;
    req.open("GET", url, true); 
    req.send(null);
}
function checkHavingVaccineAccessOrNotRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			if(val!=1){
				jAlert('error', "You don't have vaccination access. Please contact software provider.", 'Error Dialog');
				setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
				}, alertmsgduration);
			}else{
				openPopup('allvaccinationlistMaster?selectedid=69');
			}
		}
	}	 
}


function checkexistmfg(){
	var val=document.getElementById('mfg_name').value;
	var url = "checkexistmfgMaster?mfgname="+val+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkexistmfgRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}


var gllll=0;
function invstNameType(id){
	gllll=id;
	var url = "invstlistInvestigationNameMaster?id="+id;
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = invstNameTypeReq;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
function invstNameTypeReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			document.getElementById('innertd'+gllll).innerHTML=val;
			/*document.getElementById('innerrow'+gllll).style.display='none';*/
		}
	}	
}
function checkexistmfgRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			if(val==1){
				document.getElementById('existlbl').className = "";
			}else{
				document.getElementById('existlbl').className = "text-danger hidden";
				document.getElementById('savemfgfrm').submit();
			}
       }
		}	 
}


function checkexistbirthplace(){
	var val=document.getElementById('birthplace_name').value;
	if(val==''){
		jAlert('error', "Please enter birth place!", 'Error Dialog');
			setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "checkexistbirthplaceMaster?birthplace_name="+val+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = checkexistbirthplaceRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
}

function checkexistbirthplaceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			if(val==1){
				document.getElementById('existlbl').className = "";
			}else{
				document.getElementById('existlbl').className = "text-danger hidden";
				document.getElementById('savebirthplacefrm').submit();
			}
       }
	}	 
}


