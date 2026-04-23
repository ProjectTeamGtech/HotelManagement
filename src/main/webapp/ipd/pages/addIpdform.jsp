<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
	<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
    
    
    <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
  <!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->
   
       <script type="text/javascript" src="ipd/js/admissionform.js"></script>
       <script type="text/javascript" src="ipd/js/addcharge.js"></script>
       <%-- <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script> --%>
       
       <%String casualtyclientId=(String)request.getAttribute("casualtyShiftClientId");  %>
         <script>
       
 
  </script>
       
       
    <style>
    
    .nicEdit-main {
    height: 100px !important;
   
    }
    .settopad{
    padding-left: 30px !important;
    padding-right: 30px !important;
    }
    .viewsetimagse{
    	    width: 68%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 15px;
    }
    .setbackcolor{
    	    background-color: beige;
    }
        .adformback {
            border: 1px solid;
            padding: 10px 0px 0px;
            margin-top: 0px;
            width: 98%;
            margin-left: 9px;
        }
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0px;
            text-align: right;
            font-size: 12px;
        }
        .marbot15 {
            margin-bottom: 15px;
        }
        .martop15 {
            margin-top: 15px;
        }
        .diagtitle {
            background-color: #000;
            color: #FFF;
            padding: 10px;
            font-weight: normal;
            padding-top: 12px !important;
        }
        .bednosele {
            width: 10%;
            margin-top: -40px;
        }
        .textareaheight{
        height: 30px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 22%;
        }
        .widthtabhedth2{
        	width: 6%;
        }
       .backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.mar0{
			margin-top:23px;
		}
		.hhmm{
			width: 120%;
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
    			padding-top: 5px;
		}
		.admissionbackgreen {
		    width: 205px;
		}
		.minbarheaight{
			min-height: 65px;
		}
		
		.panel-primary {
		    border-color: #339966;
		}
		.checkbox-custom, .checkbox-custom-alt {
		    padding-left: 5px !important;
		}
		ul, ol {
		    margin-top: 0;
		    margin-bottom: 8.5px;
		    list-style: none;
		    padding: 0px;
		}
		.checkbox-custom > i, .checkbox-custom-alt > i {
    margin-left: 0px !important;}
    </style>
    <script type="text/javascript">
    <%--  <%if(DateTimeUtils.isNull(casualtyclientId).equals("") || DateTimeUtils.isNull(casualtyclientId).equals("0")){%>
   		window.onpaint = showPopUp();
    <%}%>  --%>
    
    $(document).ready(function() {

		$("#admissiondate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		/* localStorage.getItem("mlctemp");
   	    document.getElementById("ddlPassport").value=sessionStorage.getItem("mlctemp"); */
		
		
		var d=document.getElementById("clientid").value;
		<%if(casualtyclientId!=null){%>
		d=document.getElementById("cdd").value;
		document.getElementById("clientid").value=d;
		<%}%>
		if(d==0 || d==''){
			showPopUp();
		}  else {
			$('#clientSearchDiv').modal( "hide" );	
		    getIpdClientInfo(d);
		} 
		
		
	});
	
</script>
    
    
    
</head>
<body>

<%-- <%

   String hstry="hidden";
   String sysreview="hidden";
   String obstretic_history="hidden";
   String menstrual_history="hidden";
   String substance_history="hidden";
   String pediatric_hist="hidden";
   boolean iconsts=false;
%> --%>


<%--  <s:if test="history==true"> 
 </s:if>
 <s:else>
   <%hstry="hidden"; %> 
 </s:else>
 
 <s:if test="issystemicreview==true">
 
 </s:if>
 <s:else>
  <% sysreview="hidden"; %>
 </s:else>
  
  <s:if test="obstretic_history==true">
            
  </s:if>
  <s:else>
      <% obstretic_history="hidden"; %>
 </s:else>
    
    <s:if test="menstrual_history==true">
            
  </s:if>
  <s:else>
      <% menstrual_history="hidden"; %>
 </s:else>
  
   <s:if test="substance_history==true">
            
  </s:if>
  <s:else>
      <% substance_history="hidden"; %>
 </s:else>
  
  <s:if test="paediatrichist==true">
            
  </s:if>
  <s:else>
      <% pediatric_hist="hidden"; %>
 </s:else> --%>
 <%-- <%  LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%> --%>
<%-- <%if(loginInfo.getClinicUserid().equals("iconhospital")){
	if(pediatric_hist.equals("")){
		iconsts=true;
	}else{
		iconsts=false;
	}

}%> --%>
   

<%-- <s:form action="saveformIpd?action=0" theme="simple" id="ipdadmissionfrm"> --%>
<s:form action="saveformCommonnew?action=0" theme="simple" id="ipdadmissionfrm">
<s:hidden name="id" id="editselectedid"/>
<s:hidden id="clientid" name="clientid" />
<input type="hidden" id="disbedid" value="1" /> 
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
<s:hidden name="conditionid" id="conditionid"/>
<s:hidden name="Locationshift" id="Locationshift"/>
<input type="hidden" name='ttyy' id='cdd' value='<%=casualtyclientId%>'>
<s:hidden name="saveandprintadmission" id="saveandprintadmission"></s:hidden>

   <style>
h3, .h3 {
    margin-top: 9px !important;
    margin-bottom: 9px !important;
}
.textprimegreen{
 background-color: #339966;
    color: #fff;
}
.textprime {
    background-color: rgba(102, 153, 204, 0.85) !important;
    color: #fff;
}
hr {
    margin-top: 4px;
    margin-bottom: 5px;
    border: 0;
    border-top: 1px solid #eee;
}
 .secconbtn{
       width: 100%;
       background-color: #f5f5f5;
       color: #222222 !important;
       text-align: left;
       font-size: 12px;
       height: 24px !important;
  }
  .sebaclcons{
     background-color: rgb(246, 246, 246) !important;
    text-shadow: none;
    color: #222 !important;
    text-align: left;
    font-size: 12px;
  }
  
  .table>thead>tr>th {
    background-color: #f5f5f5;
    color: #222;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
}
h4, .h4, h5, .h5, h6, .h6 {
    margin-top: 3.5px;
    margin-bottom: 3.5px;
}
.form-control {
    border: 1px solid #ddd}
    
    .table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    border: 1px solid #ddd;
}
.lighbdsub{
background-color: #f5f5f5;
    margin: 0px;
    padding: 5px 25px 5px 5px;
}
.form-group {
    margin-bottom: 10px;
    margin-top: 5px;
}
</style>

<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen" style="background-color: #43b9be !important">
		<div class="col-lg-6 col-xs-12 col-md-6 text-left" style="padding-left:0px;">
			<h3><s:property value="clinicName"/></h3>
		</div>
		<div class="col-lg-6 col-xs-6 col-md-6 text-right hidden-xs" style="padding-right:0px;">
			<h3>Patient Admission Form</h3>
		</div>
	</div>
	
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding-top: 5px;padding-bottom: 5px;">
		<span style="color: brown;">IMPORTANT:  Please completed all required field <font color="red">*</font></span>
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
		<h5>PERSONAL AND ADMINISTRATION DETAILS</h5> 
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px; background-color: rgba(245, 245, 245, 0.95);">
		<div class="col-lg-2 col-xs-12 col-md-2" style="background-color: rgba(91, 192, 222, 0.16);padding-top: 15px;">
			<div class="form-group">
                 <label for="exampleInputEmail1">Patient Details &nbsp;&nbsp;<a href="#" onclick="editClientfromIpd()" title="Edit Patient"><i class="fa fa-pencil"></i></a></label><br>
                 <s:textfield  name="client" id="client" readonly="true" cssClass="form-control" onclick="showPopUp()" placeholder="Name"/>
                 <s:textfield  cssClass="form-control" placeholder="Address" disabled="true" name="" id="taddress" cssStyle="margin-top: 5px;" />
             </div>
             <div class="form-group">
                 <label for="exampleInputEmail1">Father / Guardian Details</label><br>
                 <input type="text" name="relativename" id="relativename" class="form-control" placeholder="Relative Name">
                 <input type="text" name="relation" id="relation" class="form-control" placeholder="Relation" style="margin-top: 5px;">
                 <input type="text" name="relationcont" id="relationcont" class="form-control" placeholder="Contact No" style="margin-top: 5px;">
             </div>
		</div>
		<%-- <input type="hidden" id="iconsts" value="<%=iconsts%>" name="iconsts"> --%>
		<input type="hidden" id="iconsts" value="false" name="iconsts">
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-9" style="padding-top: 15px;">
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">DOB</label>
                                             <s:textfield cssClass="form-control" id="dob1" disabled="true" name="dob"/>
                                        </div>
                                    </div>
                                       <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Age/Sex</label>
                                            <s:textfield cssClass="form-control" disabled="true" id="agegender" name="agegender"/>
                                        </div>
                                    </div>
            					 <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">UHID</label>
                                             <s:textfield  cssClass="form-control" id="abrivationid" disabled="true" name="abrivationid"/>
                                            <s:hidden  cssClass="form-control" id="cid" disabled="true" name="cid"/>
                                              
                                        </div>
                                    </div>
                                       <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                    	 	<div class="form-group">
                                            <label for="exampleInputEmail1" class="visible-xs visible-sm hidden-md hidden-lg">DOA</label>
                                            <label for="exampleInputEmail1" class="hidden-xs hidden-sm visible-md visible-lg">Date of Admission</label>
                                            <s:textfield cssClass="form-control" id="admissiondate" name="admissiondate" />
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select name="minute" id="minute" list="minuteList" cssClass="form-control" headerKey="00" headerValue="Select"/>
									  </div>
									</div>
                                    </div>
                                       <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3 hidden minbarheaight">
                                        <div class="form-group marbot0">
                                            <label for="exampleInputEmail1">BarCode</label>
                                            <img src="img/barcode.png" class="img-responsive imgbar hidden"></img>
                                            <s:textfield type="email" cssClass="form-control hidden" id="patientbarcode" name="patientbarcode" />
                                        </div>
                                    </div>
                                       <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3 hidden">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Admission ID</label>
                                            <p id="exampleInputEmail1"><p>
                                             <!--<s:textfield type="email" cssClass="form-control "  name="admissionip" />
                                        --></div>
                                    </div>
                                       <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        			<div class="form-group">
									    <label for="exampleInputName2">Ward</label>
									    <s:select onchange="setBedList(this.value)" list="wardlist" listKey="id" listValue="wardname" 
                                               cssClass="form-control warres" name="wardid" id="wardid" 
                                               headerKey="0" headerValue="Select Ward"/>
									  </div>
                        </div>
                         <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        			<div class="form-group">
									    <label for="exampleInputEmail2">Bed No</label>
									    <div id="bedlistdiv">
									      <s:select  list="bedlist" listKey="id" listValue="bedname" 
                                                   cssClass="form-control" name="bedid" id="bedid"
                                                   headerKey="0" headerValue="Select Bed"/>
                                                </div>
									  </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                           <div class="form-group">
								     <label for="inputEmail" class="control-label">Payee <font color="red">*</font></label>
								     <s:select id="payee" name="payee" 
										list="#{'s':'Select Payee','0':'Patient','1':'Third Party'}" onchange="settpname(this.value)" cssClass="form-control"></s:select>
								  </div>
                          </div>
                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                          	<div class="form-group" id="tpdiv">
								<label for="inputEmail" class="control-label">TP Name</label>
								<s:select id="tpid" disabled="true" name="tpid" listKey="id" listValue="thirdParty"  headerKey="0" headerValue="" list="thirdPartyList" cssClass="form-control"></s:select>
							</div>
                          </div>
                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                          <div class="form-group">
                        	<label for="inputEmail" class="control-label">MLC Case</label>
                        		 <select id="ddlPassport" name="mlccase" class="form-control" onchange = "ShowHideDiv()">
							        <option value="0">No</option>
							        <option value="1">Yes</option>            
							    </select>
                        	</div>
                          </div>
                          <div id="dvPassport" style="display: none">
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        	
                        	<div class="form-group">
                                            <label for="inputEmail" class="control-label">MLC No</label>
                                            
                                                   <s:textfield cssClass="form-control" id="mlcno" name="mlcno"/>
                                        </div>
                          
                          </div>
                          
                              <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                              <div class="form-group">
								    <label for="inputEmail" class="control-label">MLC Ref Doctor</label>
								     <s:select id="mlcrefdoctor" name="mlcrefdoctor" list="mlcdrlist" listKey="id" listValue="fullname"   cssClass="form-control"></s:select>
								    <%-- <s:textfield name="mlcrefdoctor"  id="mlcrefdoctor" cssClass="form-control" ></s:textfield> --%>
								  </div>
                              
                        </div> 
                        </div>
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3 hidden">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label">Admission No</label>
								    <s:textfield name="num_admission" disabled="true" id="num_admission" cssClass="form-control" ></s:textfield>
								  </div>
                        </div> 
                        
                                       <div class="col-md-12 col-xs-12 col-lg-12" style="padding: 0px;">
                                    	<div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Primary Consultant</label>
								   <%--  <s:select list="userList" listKey="id" listValue="diaryUser" onchange="getFormData(this.value)" 
                                                cssClass="form-control chosen-select" id="practitionerid" name="practitionerid" 
                                                headerKey="0" headerValue="Select Consultant"/> --%>
                                    <s:select list="userList" listKey="id" listValue="diaryUser"  onchange="getspecialityData(this.value)" 
                                     		cssClass="form-control chosen-select" id="practitionerid" name="practitionerid"
                                            headerKey="0" headerValue="Select Consultant"/>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        
                        <div class="form-group">
                        <s:hidden name="secndryconsult"  id="secndryconsult"></s:hidden>
								    <label for="inputEmail" class="control-label" style="color: crimson;">Associate Consultants</label>
								    <a href="#" class="btn btn-default secconbtn" data-toggle="modal" data-target="#secondconsul" style="width: 100%;">Select Consultants</a>
								  </div>
						<div id='ttt'>
						
						</div>		  
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                       <%--  <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Referred From</label>
								    <s:select cssClass="form-control chosen-select" id="refferedby" name="refferedby" list="refrenceList" listKey="id" listValue="reference" headerKey="0" headerValue="Select Referred From"/>
								  </div> --%>
				<div class="form-group">
								  <s:hidden name="refdocotor"  id="refdoctofid"></s:hidden>
								  <input type="hidden" name="referedby" id="referedby">
					 <label for="inputPassword3" class="control-label" style="color: crimson;">Referred From<span class="red">*</span></label>
					 <span><a href="#" data-toggle="modal" data-target="#adddoctorpopup">+Add Doctor</a></span>
					 <div id="otprocedurediv">
<%-- 			           <s:select cssClass="form-control chosen-select sebaclcons" id="refferedby" name="refferedby" list="refrenceList" listKey="id" listValue="reference" headerKey="0" headerValue="Select Referred From"/>
 --%>					  <a href="#" class="btn btn-default sebaclcons" data-toggle="modal" data-target="#referedpat" style="width: 100%;">Select Consultants</a>
					</div>
			 </div>
			 
			  <div id='ref'>
						
				</div>
                        </div> 
                        
                      
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group" id="deptDiv">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Speciality</label>
								    <s:select cssClass="form-control chosen-select" id="department" name="department" list="departmentList" headerKey="0" headerValue="Select Speciality" />
								  </div>
                        </div>  
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <!-- <div class="form-group" id="deptDiv">
								   <a href="" class="pull-right hidden-xs" data-toggle="modal" data-target="#miic" title="Voice Recorder" Title="Voice Recorder" style="color:#fff;"><i class="btn btn-sm btn-primary" aria-hidden="true">Voice Recorder</i></a>
								  </div> -->
								  
								  
								
                        			<div class="form-group">
									    <label for="exampleInputName2">Rate Ward</label>
									    <s:select onchange="" list="wardlist" listKey="id" listValue="wardname" 
                                               cssClass="form-control warres" name="ratewardid" id="" 
                                               headerKey="0" headerValue="Select Ward"/>
									  </div>
                    
                        </div>   
											
								
                                    </div>
                                   
                                    </div>
                                    
		
											<div id="treatmentepisodeajax" class="hidden"> 
	                                          <select name="treatmentEpisode" id="treatmentEpisode" class="form-control chosen-select">
	                                           <option value="0">Select Treatment Episode</option>
	                                          </select>
	                                         </div>
	</div>
	<%-- <div class="col-lg-12 col-xs-12 col-md-12 textprime hidden">
	    <s:if test="practitionerid>0">
	       <h5><a data-toggle="collapse" href="#collapseExample12"   aria-expanded="false" aria-controls="collapseExample12" style="color:white;">ADMISSION DETAILS &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5>
	    </s:if>
	    <s:else>
	       <h5><a data-toggle="collapse" href="#" onclick="chkSpeciality()"  aria-expanded="false" aria-controls="collapseExample12" style="color:white;">ADMISSION DETAILS &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5>
	    </s:else>
		 
	</div> --%>
	
	<%-- <div class="collapse hidden" id="collapseExample12">
		                         
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad bordertopgray">
		                         <!-- by lokesh -->
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=pediatric_hist%>" id="newtablespediatric">
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Head Circumference</label><br>
		                         	  <s:textfield name="headcircumference"  placeholder="cm" cssClass="form-control"  cssStyle="width:50%"></s:textfield>
										
		                         	 </div>
		                        	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"> Mid Arm Circumference</label>
		                         	     <s:textfield name="midarmcircumference"  placeholder="cm" cssClass="form-control"  cssStyle="width:50%"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Length</label><br>
		                         	     <s:textfield name="length"  placeholder="cm" cssClass="form-control"  cssStyle="width:50%"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on Addmisssion</label>
		                         	     <s:textfield name="wtaddmission" id="wtaddmission" placeholder="Kg" cssClass="form-control"  cssStyle="width:50%" ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on discharge</label>
		                         	     <s:textfield name="wtdischarge"  placeholder="Kg" cssClass="form-control"  cssStyle="width:50%"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	</div>
                        		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
								  <div class="form-group">
								   <label for="inputEmail" class="control-label">Admission For</label>
								  <s:textarea cssClass="form-control" rows="6"  id="addmissionfor"  name="addmissionfor"/>
								  </div>
								  <div class="form-group hidden">
								   <label for="inputEmail" class="control-label">Reimbursement</label>
								  <s:textarea cssClass="form-control" rows="3" cols="5" id="reimbursment" name="reimbursment"/>
								  </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
								     <div class="form-group">
                                            <label for="inputEmail" class="control-label">H/O ALLERGIES</label>
                                          <s:textarea cssClass="form-control" rows="3" id="alergies" name="alergies" />
                                        </div>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Admission Details</label>
                                                <s:textarea cssClass="form-control" rows="3" cols="5" name="admissiondetails" id="admissiondetails" />
                                            
                                        </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
                                <div class="form-group">
                                            <label for="inputEmail" class="control-label">Package</label>
                                          <s:textarea cssClass="form-control" rows="3" id="packagename" name="packagename" />
                                        </div>
                                
                                </div>
                        
                        </div>
                        
	                        <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>ADMISSION SUMMARY</h5> 
							</div>
	                        
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        	<div class="form-group hidden">
                        			<label for="exampleInputName2">Reason For Admission</label>
								   <s:textfield name="admission_reason" id="admission_reason" cssClass="form-control" />
								   
							</div>
                        </div>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        		<div class="form-inline">
								  <div class="form-group">
								    <label for="exampleInputName2">Chief Complaints</label>
								    <s:select cssClass="form-control" list="chief_complaints_list" listKey="id" listValue="name" onchange="setChiefComplaints(this.value)" headerKey="0" headerValue="Select Template">
								            
								    </s:select>
								    <div class="form-group">
								    <select class="form-control" onchange="setchiefcomp(this.value)">
									  <option>Select</option>
									  <option>1</option>
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									  <option>5</option>
									  <option>6</option>
									  <option>7</option>
									  <option>8</option>
									  <option>9</option>
									  <option>10</option>
									  <option>11</option>
									  <option>12</option>
									  <option>13</option>
									  <option>14</option>
									  <option>15</option>
									  <option>16</option>
									  <option>17</option>
									  <option>18</option>
									  <option>19</option>
									  <option>20</option>
									  <option>21</option>
									  <option>22</option>
									  <option>23</option>
									  <option>24</option>
									  <option>25</option>
									  <option>26</option>
									  <option>27</option>
									  <option>28</option>
									  <option>29</option>
									  <option>30</option>
									</select>
								  </div>
								  <div class="form-group">
								    <select class="form-control" onchange="setchiefcomp(this.value)">
								        <option>Days</option>    
								        <option>Month</option>    
								        <option>Year</option>     
								    </select>
								  </div>
								  <div class="form-group">
								  	<input type="text" name="chiefcomplatetempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  </div>
								    <!--<select class="form-control" style="margin-right: 10px;">
									  <option>Select DD</option>
									  <option>1</option>
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									</select>
								  --></div>
								  <!--
								  -->
								  <!--<button type="submit" class="btn btn-primary">+</button>
								  
								--></div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="6" maxlength="" name="chiefcomplains" id="chiefcomplains"/>
								</div>
                        	</div>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        		<div class="form-inline">
								  <div class="form-group">
								    <label for="exampleInputName2">Present Illness</label>
								    <s:select list="present_illness_list" cssClass="form-control" headerKey="0" onchange="getpresentIllness(this.value)" headerValue="Select Template" listKey="id" listValue="name" >
								    </s:select>
								  </div>
								  <div class="form-group">
								  	<input type="text" name="presentillnesstempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  </div>
								  <!--<button type="submit" class="btn btn-primary">+</button>
								--></div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="6" maxlength="" name="presentillness" id="presentillness"/>
								</div>
                        	</div>
                        
                        </div>
                        
                       
                           <div class="<%=hstry %>">
                           <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>HISTORY</h5> 
							</div>
                           	
                           </div>
                         
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                       			<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Past History</label>
											    <s:select list="past_history_list" cssClass="form-control" onchange="setpasthistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="pasthistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="pasthistory" id="pasthistory"></s:textarea>
											</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        					<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Family History</label>
											    <s:select list="family_history_list" cssClass="form-control" onchange="getFamilyhistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="familyhistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="familyhist" id="familyhist"></s:textarea>
											</div>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Personal History</label>
											    <s:select list="personal_hist_list" onchange="getpersonaltemp(this.value)" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="personalhistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="personalhist" id="personalhist"></s:textarea>
											</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        					<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">On Examination</label>
											    <s:select list="on_exam_list" onchange="getonexamtemp(this.value)" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="onexaminationtempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="onexamination" id="onexamination"></s:textarea>
											</div>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	
	                        		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        					<div class="form-inline">
													  <div class="form-group">
													    <label for="exampleInputName2">ANY SURGICAL HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical procedures—and complications, if any— that a particular person has had"></span></span>
													  </div>
											</div>
											
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="surgicalnotes" id="surgicalnotes"></s:textarea>
											</div>
	                        			</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Treatment History</label>
											     <s:select list="treatment_given_list" onchange="gettreatmenttemp(this.value)" listKey="id" cssClass="form-control" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group"> 
								  				<input type="text" name="treatmentgiventempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" name="suggestedtrtment" id="suggestedtrtment"/>
											</div>
	                        			</div>
	                        			
	                        		</div>
	                        	</div>
                       
                       </div>
                       
                       <!-- peditric -->
                       
                       
                         <div class="col-lg-12 col-xs-12 col-md-12 textprime<%=pediatric_hist%>">
							<h5>PEDITRIC HISTORY</h5> 
						</div>
                      
                      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                          <div class="row <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">BIRTH HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A narrative or record of past events and circumstances that are or may be relevant to a patient's current state of health. Informally, an account of past diseases, injuries, treatments, and other strictly medical facts"></span></span>
               
             </div>
          
           </div>
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="birthhist" id="birthhist"></s:textarea>
           </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DIET HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The family structure and relationships within the family, including information about diseases in family members."></span></span>
              
             </div>
        
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="diethist" id="diethist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                          <div class="row <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DEVELOPMENT HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A personal history may include information about allergies, illnesses, surgeries, immunizations, and results of physical exams, tests, and screenings. It may also include information about medicines taken and health habits, such as diet and exercise. Also called personal health record, personal medical history, and PHR."></span></span>
              </div>
        
         
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="developmenthist" id="developmenthist"></s:textarea>
           </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              <div class="form-inline">
               <div class="form-group">
                 <label for="exampleInputName2">IMMUNIZATION HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical procedures—and complications, if any— that a particular person has had"></span></span>
               </div>
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="emmunizationhist" id="emmunizationhist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                         
                       
                       </div>
                       
                       
                       
                        
                        <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=substance_history %>">
								<h5>SUBSTANCE HISTORY</h5> 
							</div>
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                       			<div class="row <%=substance_history %>" >
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Alcohal</label>
    											   <s:select list="#{'No':'No','Daily':'Daily','Weekly':'Weekly','Monthly':'Monthly','Occasionally':'Occasionally','Chronic':'Chronic','Quit Before Some Time':'Quit Before Some Time'}" theme="simple" name="alcohal" cssClass="form-control" ></s:select>
    											   	
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Drugs</label>
											   	<s:textfield name="drugs" onkeyup="onlyNum(this)"  cssClass="form-control" /> 
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Other Medications</label>
											   	<s:textfield name="other_medication"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Tobaco</label>
												<s:select list="#{'No':'No','Yes':'Yes'}" theme="simple" name="tobaco" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Smoking</label>
											   <s:textfield name="smoking" onkeyup="onlyNum(this)"   cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Tobaco</label>
											   	 <s:textfield name="tobaconotes" onkeyup="onlyNum(this)"   cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			
	                        			
	                        		</div>
	                        	</div>
	                        	
                       </div> 
                        
                        	<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=menstrual_history %>">
								<h5>MENSTRUAL HISTORY</h5> 
							</div>
                             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=menstrual_history %>">
                       			<div class="row" <%=menstrual_history %>>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Age at menarche</label>
											   	<s:textfield name="age_menarche" onkeyup="onlyNum(this)"  placeholder="Yrs"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">L.M.P</label>
											   	
											   	<s:textfield name="lmp" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">L.L.M.P</label>
											   	<s:textfield name="llmp" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">PMC</label>
    												<s:select list="#{'0':'Select','Regular':'Regular','Irregular':'Irregular'}" theme="simple" name="pmc" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Cycle Length</label>
											   	<s:textfield name="cycle_length" placeholder="Days"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Duration of Flow</label>
											    
											   	<s:textfield name="duration_flow" placeholder="Days"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			
	                        		</div>
	                        	</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Amount of Flow</label>
    												
    												<s:select list="#{'0':'Select','Normal':'Normal','Lighter':'Lighter','Heavior':'Heavior','With Medication':'With Medication','Without Medication':'Without Medication'}" theme="simple" name="amount_flow" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Dysmenorrhea</label>
											   	
											   <s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="dysmenorrhea" theme="simple" cssClass="form-control" ></s:select> 	
												
												
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Dyspareunia</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="dyspareunia" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">H/O Passing Clots</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="hopassing_clots" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">White Discharge & Itching</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="white_disc_itching" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Intercourse Frequency</label>
											   		<s:textfield name="intercourse_freq" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			
	                        		</div>
	                        	</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Galactorrea</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="galactorrea" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">H/O Contraception</label>
											   	<s:textfield name="ho_contraception" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Rubella Vaccine</label>
											   		<s:textfield name="rubella_vaccine" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Menopause</label>
											   	<s:textfield name="menopause" placeholder="Yrs"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                        				
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	
	                        	
                       
                       </div>  
                       
                       		<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=obstretic_history %>">
								<h5>OBSTRETIC HISTORY</h5> 
							</div>
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=obstretic_history %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;border-bottom: 1px dashed #ddd;padding: 0px 0px 10px 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-12">
	                        			<form>
											<label class=""><s:checkbox theme="simple" name="nulligravida" /><i></i> Nulligravida</label>
										    <label class=""><s:checkbox  name="current_pregnent" /><i></i> Currently Pregnent</label>
										    <label class=""><s:checkbox  name="iud" /><i></i> IUD</label>
										  </form>
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-6">
	                        			<div class="form-group">
											           	<lable>Live Boys</lable>
											           	<s:textfield  name="live_boys" onkeyup="addObsRow(this.value,'Boys')" value="" cssClass="form-control" />
											           </div> 
											           
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-6">
	                        			
											           <div class="form-group">
											           	<lable>Live Girls</lable>
											           	<s:textfield  name="live_girls" onkeyup="addObsRow(this.value,'Girls')" value="" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-6">
	                        			
											           <div class="form-group">
											           	<lable>Stillbirths</lable>
											           	<s:textfield  name="stillbirths" value="" onkeyup="addObsRow(this.value,'Stillbirths')" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-6">
											          
											           <div class="form-group">
											           	<lable>Abortions</lable>
											           	<s:textfield  name="abortions" value="" onkeyup="addObsRow(this.value,'Abortions')" cssClass="form-control" />
											           </div>   
	                        		</div>
											                   			
	                        		<div class="col-lg-2 col-md-2 col-xs-6">
											           <div class="form-group">
											           	<label>Death Children</label>
											           	<s:textfield name="death_children" value="" onkeyup="addObsRow(this.value,'Death Children')" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        	</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
	                        				<table id="obstable" style="background-color: rgba(0, 191, 255, 0.12);">
	                        					<tbody>
	                        						<!--<tr>
	                        							<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value="1" class="form-control"></td>
	                        							<td style="width: 8%;padding-right: 15px;"><input type="number" name="" value="" class="form-control" placeholder="year"></td>
	                        							<td style="width: 7%;padding-right: 15px;">Boys</td>
	                        							<td style="width: 13%;padding-right: 15px;">
		                        							<select name="" class="form-control">
				    												<option value="0">Type of Delivery</option>
				    												<option value="Normal">Normal</option>
				    												<option value="Vaccume">Vaccume</option>
				    												<option value="Forceps">Forceps</option>
				    												<option value="LSCS">LSCS</option>
															</select>
	                        							</td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="" value="" class="form-control" placeholder="Indications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="" value="" class="form-control" placeholder="Coamplications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="" value="" class="form-control" placeholder="Institution"></td>
	                        						</tr>
	                        					--></tbody>
	                        				</table>
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" >
	                        				<div class="form-group">
	                        					<lable>Parity/Abortions Notes</lable>
	                        					<s:textarea cssClass="form-control" theme="simple" name="parity_abortion_notes" rows="3" />
	                        				</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" style="padding: 0px;">
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<lable>P</lable>
			                        					<s:textfield name="p" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<lable>L</lable>
			                        					<s:textfield name="l" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<lable>A</lable>
			                        					<s:textfield name="a" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<lable>D</lable>
			                        					<s:textfield name="d" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        			</div>
	                        		</div>
	                        	</div>
                       		</div>  
                       		
                       	 
                              
                              
                              
                              <s:if test="issystemicreview==true">
                              <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5><a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="color:white;">SYSTEMIC REVIEW &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5> 
							</div>
		                         </s:if>
                              
                              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad bordertopgreen <%=sysreview %> ">
	                        <div class="collapse" id="collapseExample">
	                        	<div class="row">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		                        	<table class="table table-condensed table-responsive">
									    <tbody>
									      
									      <tr>
									        <td class="widthtabhedth1"><label for="exampleInputName2">Fever at present</label></td>
									        <td class="widthtabhedth2"><s:select name="fpcondition" id="fpcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="fpnotest" id="fpnotest"/></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Nausea</label></td>
									        <td> <s:select name="nauseacondition" id="nauseacondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" cols="5" placeholder="Note" name="nauseanotes" id="nauseanotes"/></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Frequent Nocturnal Urination</label></td>
									        <td><s:select name="fnucondition" id="fnucondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" cols="5" placeholder="Note" name="fnunotes" id="fnunotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Straining at micturation</label></td>
									        <td><s:select name="smcondition" id="smcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="smnotes" id="smnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Chest pain</label></td>
									        <td><s:select name="chestpaincond" id="chestpaincond" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="chestpainnotes" id="chestpainnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Dimness of vision</label></td>
									        <td><s:select name="dimvisioncond" id="dimvisioncond" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="dimvisionnotes" id="dimvisionnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Headache, Giddiness, Unconsciousness</label></td>
									        <td><s:select name="hgucondition" id="hgucondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="hgunotes" id="hgunotes"></s:textarea></td>
									      </tr>
									       <tr>
									        <td><label for="exampleInputName2">Haemoptysis, Malena</label></td>
									        <td><s:select name="hmcondition" id="hmcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="hmnotes" id="hmnotes"></s:textarea></td>
									      </tr>
									       <tr>
									        <td><label for="exampleInputName2">Joint pain</label></td>
									        <td><s:select name="jointpaincond" id="jointpaincond" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="jointpainnotes" id="jointpainnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Edema feet</label></td>
									        <td><s:select name="edemafeetcondi" id="edemafeetcondi" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="edemafeetnotes" id="edemafeetnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Hematuria</label></td>
									        <td><s:select name="hematuriacondi" id="hematuriacondi" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="hematurianotes" id="hematurianotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Burning micturation</label></td>
									        <td><s:select name="bmcondition" id="bmcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="bmnotes" id="bmnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Oliguria</label></td>
									        <td><s:select name="oliguriacondi" id="oliguriacondi" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="oligurianotes" id="oligurianotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Passing stone or gravel in the urine</label></td>
									        <td><s:select name="pstgucondi" id="pstgucondi" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="pstgunotes" id="pstgunotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Impaired hearing</label></td>
									        <td><s:select name="ihcondition" id="ihcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="ihnotes" id="ihnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Breathlessness on mild exertion</label></td>
									        <td><s:select name="bmecondition" id="bmecondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="bmenotes" id="bmenotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Tingling, Numbness in extremities</label></td>
									        <td><s:select name="tnecondition" id="tnecondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="tnenotes" id="tnenotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Weakness</label></td>
									        <td><s:select name="weaknesscondi" id="weaknesscondi" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="weaknessnotes" id="weaknessnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Constipation</label></td>
									        <td><s:select name="constipationcond" id="constipationcond" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="constpationnotes" id="constpationnotes" ></s:textarea></td>
									      </tr>
									    
									     <tr>
									        <td><label for="exampleInputName2">Special Notes/Remarks</label></td>
									        <td></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="specialnotes" id="specialnotes"></s:textarea></td>
									      </tr>
									    </tbody>
									  </table>
	                        	</div>
	                        	</div>
	                        	</div>
	                        	
	                        </div>
                        </div>
                        
                        	
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 settopad">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Earlier Investigations</label>
											  </div>
											</div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" name="earlierinvest" id="earlierinvest"/>
											</div>
	                        		</div>		
	                        		</div>
	                        
                        
                              
                               <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>PROVISIONAL DIAGNOSIS</h5> 
							</div>
                            
	                        
	                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                
                               <table class="table" width="100%">
                                     <thead> 
                                        <tr> 
                                         <th><b> Select Diagnosis</b>&nbsp; <a type="button" onclick="addMoreCondition('innercondition','spn0')" class="btn btn-primary hidden" >+</a>  <input type="button" value="+" class="btn btn-info hidden" onclick="dispDIv()" />  </th> 
                                         <th></th>
                                         <th id="viewtp" class="hidden"><b>Select Treatment Episode</b>&nbsp; <a type="button" onclick="createTreatmentEpisode()" class="btn btn-info">+</a></th> 
                                         </tr> 
                                        </thead> 
                                        
                                    <tbody> 
                                        <tr> 
                                        <td width="50%;">
                                          <table id="innercondition" width="100%">
                                          <tbody>
                                          <tr>
                                                <td><input type="text" onkeyup="searchdiagnosisJSON(this.value)" id="newdiagnosis" class="form-control" placeholder="type diagnosis here" /></td>
                                               <td></td>
                                               <td width="3%"><span id="spn0"><a type="button" class="btn btn-success savebtn"  onclick="addMoreCondition('innercondition','spn0')">+</a></span></td>
                                           </tr>    
                                          </tbody>
                                        </table>
                                        </td>
                                         <td>   <input type="button" value="Save" onclick="savediagnosisfastJSON()" class="btn btn-info" /> </td>
	                                         <td id="treatmentepisodeajax" class="hidden"> 
	                                          <select name="treatmentEpisode" id="treatmentEpisode" class="form-control chosen-select">
	                                           <option value="0">Select Treatment Episode</option>
	                                          </select>
	                                         </td>
                                        
	                                        <td width="3%" id="tprow" class="paddtop hidden">
	                                       	 <a type="button" class="btn btn-primary savebtn" title="Add Treatment Episode" onclick="createTreatmentEpisode()">+</a>
	                                      	 </td>
                                        </tr>
                                        <tr id="dispid" class="hidden">
                                          <td>
                                              <input type="text" class="form-control" placeholder="Enter New Diagnosis" id="newcondition" style="border: 1px solid #ddd;margin-top: 8px;width: 49%;">
                                              <input type="text" class="form-control" placeholder="Enter ICD Code" id="icdcode" style="border: 1px solid #ddd;margin-top: 8px;width: 50%;">
                                          </td>
                                          <td><input type="button" onclick="addnewCOndition(0)" class="btn btn-sm btn-info" style="margin-top: 8px;" value="Add New" /></td>
                                          <td></td>
                                        </tr> 
                                        </tbody>
                                         
                                    </table>
                                     <table id="searchDiagnosis" class="table"  style="height:200px;display:block;overflow:scroll;width: 50%";>
                                    
                                    </table>
                    </div>
	                        	
	                        </div>
		                         
		                         </div> --%>
		                         
		                         
		                         <!-- Hide by Abhi 12 Dec 2017 create for gyne -->
		                       <%--   <div <%=obstretic_history %>>
		                         <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>REASON FOR VISIT</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
									<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
										<article>
										  <div class="scrolltable">
										     <ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Routine Follow up</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Amenorrhoea</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> UPT +ve</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Pain in lower abdomen</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Nausea</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Vomiting</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Nausea + Vomiting</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Bleeding PV</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Burning Micturition</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> White discharge PV</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Bad Obstetric History</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Others</label></li>
									          </ul>
										   </div>
										 </article>
									</div>
									<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding: 0px;">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
										<div class="scrolltable">
												<h5 style="margin-bottom: 5px;margin-top: 5px;"><b>Routine Follow up</b>&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#rvisit"><i class="fa fa-pencil"></i></a></h5>
												<p style="margin: 0px;">Region :</p>
												<p style="margin: 0px;">Quality :</p>
												<p style="margin: 0px;">Periodicity :</p>
												<p style="margin: 0px;">Influencing Factor :</p>
												<p style="margin: 0px;">Since :</p>
												<p style="margin: 0px;">Note :</p>
												<hr>
												<h5 style="margin-bottom: 5px;margin-top: 5px;"><b>Pain in lower abdomen</b>&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#rvisit"><i class="fa fa-pencil"></i></a></h5>
												<p style="margin: 0px;">Region :</p>
												<p style="margin: 0px;">Quality :</p>
												<p style="margin: 0px;">Periodicity :</p>
												<p style="margin: 0px;">Influencing Factor :</p>
												<p style="margin: 0px;">Since :</p>
												<p style="margin: 0px;">Note :</p>
										</div>
										</div>
										
									</div>
									
									<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
										<h5 class="lighbdsub"><b>Present Preg</b></h5>
										<div class="form-group">
							       				<label>LMP</label>
							       				<input type="text" class="form-control">
							       			</div>
							       			<div class="form-group">
							       				<label>EDD</label><br>
							       				<input type="text" class="form-control"> 
							       			</div>
							       			<div class="form-group">
							       				<label>USG/Corrected FDD</label>
							       				<input type="text" class="form-control"> 
							       			</div>
									</div>
									
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>MEDICAL HISTORY</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px 0px 0px 0px;">
								 	<div class="col-lg-2 col-md-2 col-xs-2">
								 	<h5 class="lighbdsub"><b>For all</b></h5>
								 		<div class="form-group">
								 			<label>Known Case of</label>
								 			<select class="form-control">
							       				<option>Select</option>
							       			</select>
								 		</div>
								 	</div>
								 	<div class="col-lg-10 col-md-10 col-xs-10">
								 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
								 			<div class="col-lg-6 col-md-6 col-xs-6">
								 			<h5 class="lighbdsub"><b>Pt.History</b></h5>
								 			<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Present 
                                             </label>
							       		</div>
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
							       		
									</div>
								 			</div>
								 			<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Absent 
                                             </label>
							       		</div>
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										
							       		
									</div>
								 			</div>
								 			<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
								 			</div>
								 				
									
								 			</div>
								 			<div class="col-lg-6 col-md-6 col-xs-6">
								 			<h5 class="lighbdsub"><b>Family History</b></h5>
								 				<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Father 
                                             </label>
							       		</div>
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Mother 
                                             </label>
							       		</div>
							       		
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Brother 
                                             </label>
							       		</div>
							       		
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Sister 
                                             </label>
							       		</div>
							       		
									</div>
								 			</div>
								 			<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Spouse 
                                             </label>
							       		</div>
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Pat.Relative 
                                             </label>
							       		</div>
							       		
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Mat.Relative 
                                             </label>
							       		</div>
							       		
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3" style="padding-left:0px;">
										<div class="form-group">
							       			<label class="checkbox checkbox-custom">
                                                    <input name="customRadio1" type="radio"><i></i> Other 
                                             </label>
							       		</div>
							       		
									</div>
								 			</div>
								 			<div class="col-lg-12 col-md-12" style="padding:0px;">
								 				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
								 			</div>
								 			</div>
								 		</div>
								 	</div>
								 </div>
								
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>DIAGNOSIS</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="padding:10px 0px 0px 0px;">
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Gravida</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Para</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Live</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Abortion</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>MTP</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Still Born</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
										<div class="form-group">
							       			<label>Death</label>
							       			<input type="number" class="form-control" min="2"> 
							       		</div>
									</div>
									<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
										<div class="form-group">
							       			<label>With High Risk Factor</label>
							       			<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
							       		</div>
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>MEDICAL & SURGICAL H/O</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="padding-bottom: 15px;">
									<h5 class="lighbdsub"><b>Family H/o</b></h5>
									<table class="table table-responsive table-borderd">
										<tbody>
											<tr>
												<td style="width: 10%;">Date</td>
												<td style="width: 10%;"><input type="number" class="form-control"> </td>
												<td style="width: 10%;"><input type="number" class="form-control"> </td>
												<td style="width: 10%;"><input type="number" class="form-control"> </td>
												<td style="width: 10%;"><input type="number" class="form-control"> </td>
												<td style="width: 10%;">HV < 1/M</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>Hb</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>HBs Ag</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>FBS</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>VDRL</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>DPBS</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>Hb Srectraphoreris</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>U < R M</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>HB A/C</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>TSH</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>Duet Markess</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>ICT</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>Triple</td>
												<td><input type="number" class="form-control"></td>
											</tr>
											<tr>
												<td>GTT</td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td><input type="number" class="form-control"></td>
												<td>Quradraple Maicers</td>
												<td><input type="number" class="form-control"></td>
											</tr>
										</tbody>
									</table>
								 </div>
		                         
		                         <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>PERSONAL DETAILS</h5> <span style="float: right;margin-top: -17px;"><a href="#" style="color:#fff;" class="hidden">Add New</a></span>
								 </div>
								  <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>11 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Anomaly Scan</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Cervical Length</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Double Marker</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>16 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>All Investigation</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Sickling</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Triple Marker</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>1st Visit</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Abstinence</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Barrier Contra.</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Bed Rest</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Book</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>CSV</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										<div class="form-group">
							       			<label>DIPSI Test</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Drug Reaction</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>HCG</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Heparin</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Oral Hygeine</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Other Test</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										<div class="form-group">
							       			<label>Physio. & Diet</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Rubelle Status</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Smart Doc</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Steam cell</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Vaginitis</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
									
								 </div>
		                         <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>20 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Animally Scan</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Fetal Eco</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>24 Weeks - 28 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>ANTI-D</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>DIPSI</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>ITC</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>30 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Investigaion SOS</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Steroids</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Vaginitis Treatment</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>34 Weeks - 36 Weeks</b> <span style="float: right;"><a href="#" style="color:#555;" class="hidden"><i class="fa fa-trash"></i></a></span></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Breast Preparation</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Color Doppler</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Labour Councelling</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>NST</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Vagnities Treatment</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 
								  <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>NST CHART</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="margin-bottom:10px;padding-top: 10px;">
									<table class="table table-responsive table-borderd">
										<tbody>
											<tr>
												<td>Date</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Time</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Indication for NST</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Duration of NST</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Interpretation</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Intervention</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
										</tbody>
									</table>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>Immunidation</b></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<label>Inj. TT 1st Dose</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Inj. TT 2nd Dose</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<label>Influenza Vaccine</label>
							       			<input type="text" class="form-control"> 
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>USG DETAILS</h5>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12">
									
									<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;padding-top: 10px;">
										<table class="table table-responsive table-borderd">
										<tbody>
											<tr>
												<td style="width: 19%;">Date</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Amenorrhea</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Presentation</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>BPD/GS</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>HC</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>AC</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>FL/CRL</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>GA by USG</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Liquor</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Placenta</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Foetal Weight</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
											<tr>
												<td>Cervical Length</td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
												<td><input type="text" class="form-control"></td>
											</tr>
										</tbody>
									</table>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-2" style="padding-right: 0px;">
										<div class="form-group">
											<label>Early anomaly / NT Scan</label>
											<textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
										</div>
										<div class="form-group">
											<label>Anomaly Scan</label>
											<textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
										</div>
										<div class="form-group">
											<label>Colour Doppler Scan</label>
											<textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
										</div>
									</div>
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>EXAMINATION / VITALS </h5> <span style="float: right;margin-top: -17px;"><a href="#" style="color:#fff;" class="hidden">Add Examination</a><a href="#" style="color:#fff;">Sketch</a></span>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="padding-left: 0px;">
								 <div class="col-lg-8 col-md-8 col-sm-8" style="padding-left:0px;border-right: 1px solid #ddd;">
								 <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Genral Condition</label>
							       			<select class="form-control">
							       				<option>Select</option>
							       			</select>
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Temp</label>
							       			<select class="form-control">
							       				<option>Select</option>
							       			</select>
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Pallor</label>
							       			<select class="form-control">
							       				<option>Select</option>
							       			</select>
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Pedal Edema</label>
							       			<select class="form-control">
							       				<option>Select</option>
							       			</select>
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		
								 	</div>
								 </div>
								 <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Pulse</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>BMI</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Height</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Weight</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Sys-BP</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2">
								 		<div class="form-group">
							       			<label>Dia-BP</label>
							       			<input type="text" class="form-control">
							       		</div>
								 	</div>
								 </div>
								 	
								 </div>
								 <div class="col-lg-2 col-md-2 col-sm-2" style="padding:0px;">
								 	<img src="img/le2.jpg" class="img-responsive viewsetimagse"/>
								 </div>
								 
								 
								 	<div class="col-lg-8 col-md-8 col-sm-8 hidden" style="padding:0px;">
								 		<div class="scrolltable1">
								 			<h5 class="lighbdsub"><b>P/S</b> <span style="float: right;"><a href="#" data-toggle="modal" data-target="#examiform" style="color:#555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></span></h5>
								 		<div class="col-lg-12 col-xs-12 col-sm-12">
								 			<table class="table-responsive table">
								 				<tbody>
								 					<tr>
								 						<td style="width:10%;">Cervix</td>
								 						<td style="width:15%;">
								 							<select class="form-control" style="width:100%;">
											       				<option>Select</option>
											       			</select>
								 						</td>
								 						<td style="width:72%;"><input type="text" class="form-control"></td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></td>
								 					</tr>
								 					<tr>
								 						<td>Vagina</td>
								 						<td>
								 							<select class="form-control" style="width:100%;">
											       				<option>Select</option>
											       			</select>
								 						</td>
								 						<td><input type="text" class="form-control"></td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></td>
								 					</tr>
								 				</tbody>
								 			</table>
								 			
								 		
								 		</div>
								 		</div>
								 		
								 		
								 		
								 	</div>
								 	<div class="col-lg-2 col-md-2 col-sm-2" style="padding-right:0px;padding-top:10px;">
								 		<article>
								 		<input id="search" name="search" class="form-control" placeholder="Search Sketch" type="text" data-list=".default_list" autocomplete="off">
										  <div class="scrolltable7">
										     <ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> cranio</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Placenta Previa</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Female Reproductive Organ</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Female Reproductive System</label></li>
									          </ul>
										   </div>
										 </article>
										 <div>
										 	
										 </div>
										 
								 	</div>
									
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>P/A </h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>Abdominal Wall Edema</b></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  <div class="">
										     <ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Present</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Absent</label></li>
									                      
									          </ul>
										   </div>
										 </article>
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>Fundal Height</b></h5>
									<div class="form-inline">
										<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                12 W
							       		</div>
										<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                14 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                16 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                18 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                20 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                22 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                24 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                26 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                28 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                30 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                32 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                34 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                36 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                38 W
							       		</div>
							       		<div class="form-group" style="margin-right: 10px;">
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio" type="radio"><i></i> 
                                                </label><br>
                                                40 W
							       		</div>
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>Presentation</b></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop1" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Cephalic</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop1" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio4" type="radio"><i></i> Mobile  
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio4" type="radio"><i></i> Fixed 
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									             <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Breech</label></li>
									          </ul>
										   </div>
										 </article>
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop2" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Baley size</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop2" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio3" type="radio"><i></i> Average  
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio3" type="radio"><i></i> Small 
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio3" type="radio"><i></i> Large 
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop3" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Transverse FHS</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop3" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Present  
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Absent 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Regular 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Irregular 
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
											<label>Liquor</label>
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio1" type="radio"><i></i> Adequate 
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio1" type="radio"><i></i> Reduced 
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio1" type="radio"><i></i> More 
                                             </label>
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										<div class="form-group">
											<label>Uterine Contractions</label>
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio2" type="radio"><i></i> Relaxed 
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio2" type="radio"><i></i> Irritable 
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                    <input name="customRadio2" type="radio"><i></i> Acting 
                                             </label>
							       		</div>
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>P/S </h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop4" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Cervix</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop4" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Normal  
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Erosion 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Bleeding 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Small Polyp - Present 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Irregular Margins 
                                             </label>
											</div>
										</div>
							       		
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop5" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Vagine</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop5" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Normal  
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Vaginitis 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Candidiasis 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Anterior Vaginal wall cyst
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop6" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Vault</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop6" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Normal  
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <input type="checkbox"><i></i> Granuletion Tissue 
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
							       		
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>P/V </h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12" style="margin-top:10px;">
									<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
								 </div>
								 
								 
								</div>  --%>
		                      <!-- Hidden Code End by abhi for gyne -->   
		                         
		                         <div class="col-lg-12 col-md-12" style="margin-top: 15px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12">
		                                	<input type="button" class="btn btn-primary savebtn savebigbtn" value="Save and Print" onclick="alreadyadmit(1)">
		                                    <input type="button" class="btn btn-primary savebtn savebigbtn" value="Save" onclick="alreadyadmit(0)">
		                                </div>
		                            </div>
		                        </div>
	
	
	
</div>





<%-- <!-- Reason for Visit Modal -->
<div id="rvisit" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Comes For Check Up</h4>
      </div>
      <div class="modal-body">
       <div class="row">
       	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
       		<div class="col-lg-3 col-md-3 col-xs-3">
       			<div class="form-group">
       				<label>Region</label>
       				<input type="text" class="form-control">
       			</div>
       		</div>
       		<div class="col-lg-3 col-md-3 col-xs-3">
       			<div class="form-group">
       				<label>Quality</label>
       				<input type="text" class="form-control">
       			</div>
       		</div>
       		<div class="col-lg-3 col-md-3 col-xs-3">
       			<div class="form-group">
       				<label>Periodicity</label>
       				<input type="text" class="form-control">
       			</div>
       		</div>
       		<div class="col-lg-3 col-md-3 col-xs-3">
       			<div class="form-group">
       				<label>Influencing Factor</label>
       				<input type="text" class="form-control">
       			</div>
       		</div>
       	</div>
       	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
       		<div class="col-lg-3 col-md-3 col-xs-3">
       			<div class="form-group">
       			<div class="col-lg-12 col-md-12" style="padding: 0px;">
       					<label>Since</label>
       				</div>
       				
       				<div class="col-lg-6 col-md-6" style="padding-left: 0px;">
       					<input type="number" class="form-control">
       				</div>
       				<div class="col-lg-6 col-md-6" style="padding: 0px;">
       					<select class="form-control" id="exampleFormControlSelect1">
					      <option>Days</option>
					      <option>Week</option>
					      <option>Month</option>
					    </select>
       				</div>
       			</div>
       		</div>
       		<div class="col-lg-9 col-md-9 col-xs-9">
       			<div class="form-group">
       				<label>Note</label>
       				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
       			</div>
       		</div>
       	</div>
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>

  </div>
</div> --%>


<%-- <!-- Examination Popup  -->
<div id="examiform" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Examination</h4>
      </div>
      <div class="modal-body">
       <div class="">
       <div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
       		<div class="form-inline">
       			<div class="form-group" style="width:92%;">
       				<input type="text" placeholder="Add Examination" class="form-control" style="width:100%;">
       			</div>
       			<div class="form-group">
       				<a href="#" class="btn btn-primary">Add</a>
       			</div>
       		</div>
       </div>
       <div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
       			<div class="form-group">
       				<a href="#" class="btn btn-info">P / S</a>&nbsp;&nbsp;<a href="#" class="btn btn-info">A / V</a>
       			</div>
       			<div class="form-group">
       				<span class="btn btn-default">C.N.S &nbsp;&nbsp;<span style="float: right;"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
       				<span class="btn btn-default">C.N.S &nbsp;&nbsp;<span style="float: right;"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
       				<span class="btn btn-default">P / A &nbsp;&nbsp;<span style="float: right;"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
       				<span class="btn btn-default">P / S &nbsp;&nbsp;<span style="float: right;"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
       			</div>
       </div>
       	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
       		<div class="col-lg-7 col-md-7 col-xs-7" style="padding: 0px;">
       			<div class="form-inline">
       			<div class="form-group" style="width:88%;">
       				<input type="text" placeholder="Add Parameter" class="form-control" style="width:100%;">
       			</div>
       			<div class="form-group">
       				<a href="#" class="btn btn-primary">Add</a>
       			</div>
       		</div>
       		<div class="scrolltable" style="margin-top:3px;">
       			<table class="table-responsive table">
								 				<tbody>
								 					<tr>
								 						<td style="width:65%;"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Cervix</label></td>
								 						<td style="width:25%;">
								 							<select class="form-control" style="width:100%;">
											       				<option>Text Box</option>
											       				<option>Text Area</option>
											       				<option>Dropdown</option>
											       				<option>Multiselect</option>
											       				<option>Date</option>
											       			</select>
								 						</td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a></td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></td>
								 					</tr>
								 					<tr>
								 						<td><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Vagina</label></td>
								 						<td>
								 							<select class="form-control" style="width:100%;">
											       				<option>Text Box</option>
											       				<option>Text Area</option>
											       				<option>Dropdown</option>
											       				<option>Multiselect</option>
											       				<option>Date</option>
											       			</select>
								 						</td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-pencil"></i></a></td>
								 						<td class="text-center"><a href="#" style="color:#555;"><i class="fa fa-trash"></i></a></td>
								 					</tr>
								 				</tbody>
								 			</table>
       		</div>
       		
       		</div>
       		<div class="col-lg-5 col-md-5 col-xs-5" style="padding-right: 0px;">
       			<div class="form-inline">
       			<div class="form-group" style="width:80%;">
       				<input type="text" placeholder="Add Parameter" class="form-control" style="width:100%;">
       			</div>
       			<div class="form-group">
       				<a href="#" class="btn btn-primary">Add</a>
       			</div>
       		</div>
       		<article>
										  <div class="scrolltable">
										     <ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Normal</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Bleeding Through OS +</label></li>
									          </ul>
										   </div>
										 </article>
       		</div>
       		
       	</div>
       	
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>

  </div>
</div> --%>

   
                   <%--  <div class="panel-body hidden">
                        <div class="col-lg-12 col-md-12 col-xs-6 col-sm-12 settopad bordertopgray backgrey">
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
                             
								     <!--
								   <s:select list="userList" listKey="id" listValue="diaryUser" 
                                               cssClass="form-control chosen-select"  name="secndryconsult" id="secndryconsult" 
                                               headerKey="0" headerValue="Select Consultant"/>
								  -->
								  
									<button type="button" style="width: 100%;" class="btn btn-default btn-sm dropdown-toggle hidden" data-toggle="dropdown">Associate Consultant <span class="caret"></span></button>
												<ul class="dropdown-menu hidden" style="margin-top: -15px;margin-left: 15px;width: 85%;">
													<s:iterator value="userList">
													 <s:if test="status==1">
													   <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" checked="checked" class="" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="diaryUser"/></span></a></li>
													 </s:if>
													 <s:else>
													    <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>"  value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="diaryUser"/></span></a></li>
													 </s:else>
												  </s:iterator>
											</ul>
								  
								  </div>
                        </div>
                        </div>
                			
						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 hidden">
														     
														     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-6" style="margin-top: 23px;">
														     <div class="form-group hidden">
						                                            <label for="inputEmail" class="control-label">Hospital/Clinic</label>
						                                                <s:textarea cssClass="form-control" rows="3" id="hosp" name="hosp" />
						                                        </div>
						                                </div>
						                            </div>
						    </div> --%>
						   

</s:form>



 

</body>


<!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body addnewpat">
					<%@ include file="/diarymanagement/pages/addPatientPage.jsp"%>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="savePatientNow"
						onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	

<%-- <!-- Add Treatment Episode -->
	<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create Treatment
						Episode</h4>
				</div>
				<div class="modal-body">
					<%@ include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary"
						onclick="saveTreatment();">Save</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> --%>
	
	
	<!-- Second Consultant -->
<div id="secondconsul" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Consultant</h4>
      </div>
      <div class="modal-body">
      				<article>
		               <input id="search" name="search" class="form-control" placeholder="search here" type="text" data-list=".default_list" autocomplete="off">
		               <div class="secoconsullist">
		               <ul class="vertical default_list" id="allprod">
		                 <s:iterator value="userList">
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5 lok"><input type="checkbox" value="<s:property value="id"/>" class="doctors" onclick="setsecondaryCon()"><i></i> <span id='lok<s:property value="id"/>' style="padding-left:20px;"> <s:property value="diaryUser"/></span></label></li>
		                 </s:iterator>
		               </ul>
						</div>
		             </article>
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
 </div>
 
 <div id="referedpat" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Consultant</h4>
      </div>
       <div class="modal-body">
      				<article>
		               <input id="search" name="search" class="form-control" placeholder="search here" type="text" data-list=".default_list" autocomplete="off">
		               <div class="RefrencedoctorList">
		               <ul class="vertical default_list" id="allref">
		                 <s:iterator value="RefrencedoctorList">
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5 reflok"><input type="checkbox" value="<s:property value="id"/>" class="refdoctors" onclick="setrefereddoc()"><i></i> <span id='reflok<s:property value="id"/>' style="padding-left:20px;"> <s:property value="reference"/></span></label></li>
		                 </s:iterator>
		               </ul>
						</div>
		             </article>
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
	
<div class="modal fade" id="adddoctorpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Visiting Consultant</h4>
                </div>
                <div class="modal-body">
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					  			<label for="exampleInputPassword1">System Doctor<span class="red">*</span></label>
			   					<s:select id="existingdr" name="existingdr" list="practionerlist" listKey="id" listValue="fullname"
								headerKey="0" headerValue="Select Existing Doctor"
								title="Select Existing Dr" cssClass="form-control showToolTip chosen-select" onchange="checkdralreadypresent(this.value)" data-toggle="tooltip" />  
					  			<s:hidden id="existingdr_update" value="0"></s:hidden>
					  		</div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
						   		<label for="exampleInputPassword1">Name<span class="red">*</span></label>
				   				<s:textfield cssClass="form-control" id="consultantname" name="consultantname" onkeyup="searchexistingDr(this.value)" data-toggle="tooltip" placeholder="Consultant Name" ></s:textfield>
			   				</div>
			   				 <s:hidden name="referid" id="referid"></s:hidden>
			   				 <div class="scrolldoc">
			   				 	<table class="table">
		                            <thead> 
		                            </thead> 
		                            <tbody id="tabledrname"> 
					   				</tbody>
							   </table>
			   				 </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   	<label for="exampleInputPassword1">Specialization<span class="red">*</span></label>
			   			<s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
								headerKey="0" headerValue="Select Specialization"
								title="Select Specialization" cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" />  
						</div>
					  	</div>
                	</div>
                	</div>
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Mobile No<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mobileno" name="mobile" data-toggle="tooltip" placeholder="Mobile number"  maxlength="10"></s:textfield>
					   </div>
					   <div class="form-group">
					   		<label for="exampleInputPassword1">Qualification<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mlcqualification" name="mlcqualification" data-toggle="tooltip" placeholder="Qualification" ></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Email<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="emailid" name="emailid" data-toggle="tooltip" placeholder="Email" ></s:textfield>
					   	</div>
					   	<div class="form-group">
					   		<label for="exampleInputPassword1">Share %<span class="red">*</span></label>
					    	<s:textfield type="number" cssClass="form-control" id="sharepercentage" name="sharepercentage" data-toggle="tooltip" placeholder="Share Charges"  value="70"></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
        					<div class="form-group" id="reftypediv">
        						<label for="exampleInputPassword1">Reference Share Type </label>
     							<s:select name="refsharetype" id="refsharetype" 
    									list="#{'0':'%','1':'Rs'}" 
    									cssClass="form-control chosen-select"></s:select>
        					</div>
         <div class="form-group">
          <label for="exampleInputPassword1">Reference Share Amount</label>
          <s:textfield type="number" cssClass="form-control" id="refshareammount" name="refshareammount" data-toggle="tooltip" placeholder="Share Amount"  value=""></s:textfield>
        </div>
        </div>
					  	<div class="col-lg-2 col-xs-12 col-md-2 col-sm-2">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Fees<span class="red">*</span></label>
					    <s:textfield cssClass="form-control" id="visitingconfees" name="fees" data-toggle="tooltip" placeholder="Fees" ></s:textfield>
					  </div> 
					  	</div>
					  	<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					    		<article>
									<ul class="vertical default_list" style="list-style: none;padding: 0px;">
									   <li id="isvisitingconsdiv"><label class=""><input type="checkbox" id="isvisitingcons" name="isvisitingcons"><i></i> Visiting Consultant</label></li>
									   <li id="issurgeondiv"><label class=""><input type="checkbox" id="issurgeon" name="issurgeon"><i></i> Surgeon</label></li>
									   <li id="isanesthesiologistdiv"><label class=""><input type="checkbox" id="isanesthesiologist" name="isanesthesiologist"><i></i> Anesthesiologist</label></li>
									   <li id="isreferreddiv"><label class=""><input type="checkbox" id="isreferred" name="isreferred"><i></i> Referred</label></li>
									   <li id="ismlcdiv"><label class=""><input type="checkbox" id="ismlc" name="ismlc"><i></i> MLC</label></li>
									 </ul>
								</article>
					  		</div> 
					  	</div>
                	</div>
                	</div>
                	
                	
                	
                	
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <!-- <form action="addnewvisitingIpdDashboard" id="newvisitdrform" method="post"> -->
					   <%-- <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding:0px;">
					   <div class="col-lg-3 col-md-3 col-xs-3" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Initial</label>
					    <s:select id="initial" name="initial" list="initialList" headerKey="0"  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
					  </div>
					  	</div>
					  	<div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">First Name</label>
					   <s:textfield name="firstname" id="firstname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="First Name" title="Enter First name"></s:textfield>
					  </div>
					  	</div>
					  	<div class="col-lg-5 col-md-5 col-xs-5" style="padding: 0px;">
					  		 <div class="form-group">
					    <label for="exampleInputPassword1">Last Name<span class="red">*</span></label>
					    <s:textfield name="lastname" id="lastname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Last Name" title="Enter Last Name"></s:textfield>
					  </div>
					  	</div>
					  </div> --%>
					<!-- </form> -->
                    </div>
                </div>
                <div class="modal-footer" id="updatebtnid">
                    <button type="button" onclick="addnewconsultantvalidate()" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
<%-- 	 <!-- Voice recorder  -->
<div id="miic" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-sm" style="width: 70%">
    <!-- Modal content-->
    <div class="modal-content" style="height: 202px !important">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Voice Over</h4>
      </div>
      <div class="modal-body" style=" padding-top: 15px !important">
      			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="margin-left: -119px">
      			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting15(this)" title="Microphone" id="changer" style="width: 25%"></img>
      			
      			</div>
      				<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
      				</div>
		               <div class="form-group" style="margin-top:41px;">
												<s:textarea cssClass="form-control" rows="3" name="voice" id="voicemic"/>
											
					<div >

	                           
	                                  
									</div>	
		            </div>
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
 </div>
 </div> --%>
	
	 
	
	
	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
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
    
    
  </script>
  
  <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<%--   <script>
  function voicerecord() {
	  $('#miic').modal( "show" );	
}
  
      function startConverting15(element) {

		   var abc=element.src.split('/');	
		   
		     var right = "cicon/mic_off.png";
		         var left = "cicon/mic.png";
		         element.src = element.bln ? right : left;
		         element.bln = !element.bln;
		         
		       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
		   if(abc[5]=="mic_off.png")
		   {
		    startConvertingadvicepres();
		   }
		   else{
		   //var textvalue=document.getElementById("otnotes").value;
		  // localStorage.setItem("xx",textvalue);
		   
		   }
		     }
    
	
	</script> --%>
  <!-- JS -->
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
  <script src="inventory/js/searchtext/javascripts/initializers.js"></script>
  <!-- JS ends -->
  
  
  <script type="text/javascript">
    function ShowHideDiv() {
        var ddlPassport = document.getElementById("ddlPassport");
        var dvPassport = document.getElementById("dvPassport");
        dvPassport.style.display = ddlPassport.value == "1" ? "block" : "none";
        mlctemp=ddlPassport.value;
        sessionStorage.setItem("mlctemp",mlctemp);
    }
</script>
<%-- <script type="text/javascript">
 
        // Load the Google Transliterate API
        google.load("elements", "1", {
            packages: "transliteration"
        });
 
        function onLoad() {
            var options = {
                sourceLanguage:
                google.elements.transliteration.LanguageCode.ENGLISH,
                destinationLanguage:
                [google.elements.transliteration.LanguageCode.HINDI],
                transliterationEnabled: true
            };
 
            // Create an instance on TransliterationControl with the required
            // options.
            var control =
            new google.elements.transliteration.TransliterationControl(options);
 
            // Enable transliteration in the textbox with id
            // 'transliterateTextarea'.
            control.makeTransliteratable(['voicemic']);
        }
        google.setOnLoadCallback(onLoad);
    </script> --%>
<%-- <script>
  function startConvertingadvicepres(){
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
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		document.getElementById('voicemic').value=vtxt;
		
		
		
	};

}
  
  </script> --%>
  
             <%-- <script type="text/javascript">
             	//Code By Abhi 10-10-2017
			
             	
             	
             	
				google.load("elements", "1", {
				      packages: "transliteration"
				    });

				function onLoad() {
				  var options = {
				      sourceLanguage:
				          google.elements.transliteration.LanguageCode.ENGLISH,
				      destinationLanguage:
				          [google.elements.transliteration.LanguageCode.HINDI],
				      shortcutKey: 'ctrl+g',
				      transliterationEnabled: true
				  };
				  var options1 = {
					      sourceLanguage:
					          google.elements.transliteration.LanguageCode.ENGLISH,
					      destinationLanguage:
					          [google.elements.transliteration.LanguageCode.MARATHI],
					      shortcutKey: 'ctrl+g',
					      transliterationEnabled: true
					  };

				  // Create an instance on TransliterationControl with the required
				  // options.
				  var control =
				      new google.elements.transliteration.TransliterationControl(options);
				  var control1 =
				      new google.elements.transliteration.TransliterationControl(options1);

				  // Enable transliteration in the textbox with id
				  // 'transliterateTextarea'.
				  control.makeTransliteratable(['remarkhindi']);
				  control1.makeTransliteratable(['remarkmarathi']);
				}
				google.setOnLoadCallback(onLoad);
			</script> --%>
			<%-- <script type="text/javascript">
			function startConvertingadvicepres(){
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
					var vtxt  = finalTranscripts  + interimtranscripts ;
					
					//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
				//	nicEditors.findEditor('adharsearch').setContent(vtxt);
					document.getElementById('voicemic').value=vtxt;
					
					
					
				};

			}

			</script> --%>
</html>
