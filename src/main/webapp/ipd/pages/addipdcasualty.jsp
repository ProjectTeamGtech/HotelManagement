<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
	<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html>
<html lang="en">


<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
  <!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->
   
       <script type="text/javascript" src="ipd/js/admissionform.js"></script>
       <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
      
    <style>
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
		    padding-left: 25px;
		}
		ul, ol {
		    margin-top: 0;
		    margin-bottom: 8.5px;
		    list-style: none;
		    padding: 0px;
		}
	
    </style>
    <script type="text/javascript">
    
    
    $(document).ready(function() {

		$("#admissiondate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		var d=document.getElementById("clientid").value;
		
		
		if(d==0 || d==''){
			showPopUp();
		}  else {
		    getIpdClientInfo(d);
		}
		
		
		
		
	});
	
	        
	        bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 //new nicEditor({maxHeight : 250}).panelInstance('admissiondetails');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('admissiondetails');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('suggestedtrtment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chiefcomplains');
	        	
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('addmissionfor');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('reimbursment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hosp');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('packagename');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('presentillness');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('suggestoper');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('systdepartment');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fpnotest');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('nauseanotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fnunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('chestpainnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('dimvisionnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hgunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hmnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('jointpainnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('specialnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('edemafeetnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hematurianotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('oligurianotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pstgunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('ihnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmenotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('tnenotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('weaknessnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('constpationnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pasthistory');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('personalhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('familyhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('onexamination');
	        	 //new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('smnotes');
	        	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('alergies');
	        	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('earlierinvest');
	        	
	        	 $('.nicEdit-panelContain').parent().width('auto');
	        	 $('.nicEdit-panelContain').parent().next().width('auto');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
</script>
    
    
</head>
<body>

<%

   String hstry="";
   String sysreview="";
   String obstretic_history="";
   String menstrual_history="";
   String substance_history="";
   
%>

 <s:if test="history==true"> 
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
  
  
  
   

<s:form action="savecasualtyIpd" theme="simple" id="ipdadmissionfrm">

<s:hidden name="id" id="editselectedid"/>
<s:hidden id="clientid" name="clientid" />
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>

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
    background-color: rgba(221, 221, 221, 0.85);
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
</style>

<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen">
		<div class="col-lg-6 col-xs-6 col-md-6 text-left" style="padding-left:0px;">
			<!--<h3>Hospital Name</h3>
		-->
		<h3><s:property value="clinicName"/></h3>
		</div>
		<div class="col-lg-6 col-xs-6 col-md-6 text-right" style="padding-right:0px;">
			<h3>Patient Casualty Form</h3>
		</div>
	</div>
	
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding-top: 5px;padding-bottom: 5px;">
		<span style="color: brown;">IMPORTANT:  Please completed all required field <font color="red">*</font></span>
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
		<h5>PERSONAL AND ADMINISTRATION DETAILS</h5> 
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px; background-color: rgba(245, 245, 245, 0.95);">
		<div class="col-lg-2 col-xs-2 col-md-2" style="background-color: rgba(91, 192, 222, 0.16);padding-top: 15px;">
			<div class="form-group">
                 <label for="exampleInputEmail1">Patient Details</label><br>
                 <s:textfield  name="client" id="client" readonly="true" cssClass="form-control" onclick="showPopUp()" placeholder="Name"/>
                 <s:textfield  cssClass="form-control" placeholder="Address" disabled="true" name="" id="taddress" cssStyle="margin-top: 5px;" />
             </div>
             <div class="form-group">
                 <label for="exampleInputEmail1">Family / Emergency Details</label><br>
                 <input type="text" name="relativename" id="relativename" class="form-control" placeholder="Relative Name">
                 <input type="text" name="relation" id="relation" class="form-control" placeholder="Relation" style="margin-top: 5px;">
                 <input type="text" name="relationcont" id="relationcont" class="form-control" placeholder="Contact No" style="margin-top: 5px;">
             </div>
		</div>
		
		<div class="col-lg-10 col-md-10 col-xs-6 col-sm-9" style="padding-top: 15px;">
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
                                            <s:textfield  cssClass="form-control" id="cid" disabled="true" name="cid"/>
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
									    <s:select name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="0" headerValue="Select"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select name="minute" id="minute" list="minuteList" cssClass="form-control" headerKey="0" headerValue="Select"/>
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
                          <div class="form-group mar0">
								     <input type="button" class="btn btn-info" onclick="editClientfromIpd()" value="Edit Patient" style="width:100%;"/>
								  </div>
                          </div>
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                          <div class="form-group">
                                            <label for="inputEmail" class="control-label">MLC No</label>
                                                   <s:textfield cssClass="form-control" id="mlcno" name="mlcno"/>
                                        </div>
                          </div>
                          
                              <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label">MLC Ref Doctor</label>
								    <s:textfield name="mlcrefdoctor"  id="mlcrefdoctor" cssClass="form-control" ></s:textfield>
								  </div>
                        </div> 
                        
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label">Admission No</label>
								    <s:textfield name="num_admission" disabled="true" id="num_admission" cssClass="form-control" ></s:textfield>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Admitting Consultant</label>
								    <s:hidden name='actiontype' id='actiontype'></s:hidden>
								    <s:select list="userList" listKey="id" listValue="diaryUser" onchange="getFormDatacasualty(this.value)" 
                                                cssClass="form-control chosen-select" id="practitionerid" name="practitionerid"
                                                headerKey="0" headerValue="Select Consultant"/>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        
                        <div class="form-group">
                        <s:hidden name="secndryconsult"  id="secndryconsult"></s:hidden>
								    <label for="inputEmail" class="control-label" style="color: crimson;">Secondary Consultant</label>
								    <a href="#" class="btn btn-default secconbtn" data-toggle="modal" data-target="#secondconsul" style="width: 100%;">Select Consultant</a>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Referred From</label>
								    <s:select cssClass="form-control chosen-select" id="refferedby" name="refferedby" list="refrenceList" listKey="reference" listValue="reference" headerKey="0" headerValue="Select Referred From"/>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group" id="deptDiv">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Speciality</label>
								    <s:select cssClass="form-control chosen-select" id="department" name="department" list="departmentList" headerKey="0" headerValue="Select Speciality" />
								  </div>
                        </div>  
                                       
                                       
                                    </div>
		
		
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
	    <s:if test="practitionerid>0">
	       <h5><a data-toggle="collapse" href="#collapseExample12"   aria-expanded="false" aria-controls="collapseExample12" style="color:white;">ADMISSION DETAILS &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5>
	    </s:if>
	    <s:else>
	       <h5><a data-toggle="collapse" href="#" onclick="chkSpeciality()"  aria-expanded="false" aria-controls="collapseExample12" style="color:white;">ADMISSION DETAILS &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5>
	    </s:else>
		 
	</div>
	
	<div class="collapse" id="collapseExample12">
		                         
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad bordertopgray">
                        		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								  <div class="form-group">
								   <label for="inputEmail" class="control-label">Admission For</label>
								  <s:textarea cssClass="form-control" rows="3" cols="5" id="addmissionfor"  name="addmissionfor"/>
								  </div>
								  <div class="form-group hidden">
								   <label for="inputEmail" class="control-label">Reimbursement</label>
								  <s:textarea cssClass="form-control" rows="3" cols="5" id="reimbursment" name="reimbursment"/>
								  </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								     <div class="form-group">
                                            <label for="inputEmail" class="control-label">H/O ALLERGIES</label>
                                          <s:textarea cssClass="form-control" rows="3" id="alergies" name="alergies" />
                                        </div>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Admission Details</label>
                                                <s:textarea cssClass="form-control" rows="3" cols="5" name="admissiondetails" id="admissiondetails" />
                                            
                                        </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
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
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Past History</label>
											    <s:select list="past_history_list" cssClass="form-control" onchange="setpasthistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											--></div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="pasthistory" id="pasthistory"></s:textarea>
											</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        					<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Family History</label>
											    <s:select list="family_history_list" cssClass="form-control" onchange="getFamilyhistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											--></div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="familyhist" id="familyhist"></s:textarea>
											</div>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Personal History</label>
											    <s:select list="personal_hist_list" onchange="getpersonaltemp(this.value)" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											--></div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="personalhist" id="personalhist"></s:textarea>
											</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        					<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">On Examination</label>
											    <s:select list="on_exam_list" onchange="getonexamtemp(this.value)" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											--></div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" maxlength="" name="onexamination" id="onexamination"></s:textarea>
											</div>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">Treatment Given</label>
											     <s:select list="treatment_given_list" onchange="gettreatmenttemp(this.value)" listKey="id" cssClass="form-control" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											--></div>
											<div class="form-group" style="margin-top:10px;">
												<s:textarea cssClass="form-control" rows="3" name="suggestedtrtment" id="suggestedtrtment"/>
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
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Alcohal</label>
    											   <s:select list="#{'No':'No','Daily':'Daily','Weekly':'Weekly','Monthly':'Monthly','Occasionally':'Occasionally','Chronic':'Chronic','Quit Before Some Time':'Quit Before Some Time'}" theme="simple" name="alcohal" cssClass="form-control" ></s:select>
    											   	
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Drugs</label>
											   	<s:textfield name="drugs" onkeyup="onlyNum(this)"  cssClass="form-control" /> 
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Other Medications</label>
											   	<s:textfield name="other_medication"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Tobaco</label>
												<s:select list="#{'No':'No','Yes':'Yes'}" theme="simple" name="tobaco" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Smoking</label>
											   <s:textfield name="smoking" onkeyup="onlyNum(this)"   cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
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
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Age at menarche</label>
											   	<s:textfield name="age_menarche" onkeyup="onlyNum(this)"  placeholder="Yrs"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">L.M.P</label>
											   	
											   	<s:textfield name="lmp" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">L.L.M.P</label>
											   	<s:textfield name="llmp" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">PMC</label>
    												<s:select list="#{'0':'Select','Regular':'Regular','Irregular':'Irregular'}" theme="simple" name="pmc" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Cycle Length</label>
											   	<s:textfield name="cycle_length" placeholder="Days"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
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
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Amount of Flow</label>
    												
    												<s:select list="#{'0':'Select','Normal':'Normal','Lighter':'Lighter','Heavior':'Heavior','With Medication':'With Medication','Without Medication':'Without Medication'}" theme="simple" name="amount_flow" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Dysmenorrhea</label>
											   	
											   <s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="dysmenorrhea" theme="simple" cssClass="form-control" ></s:select> 	
												
												
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Dyspareunia</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="dyspareunia" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">H/O Passing Clots</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="hopassing_clots" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">White Discharge & Itching</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="white_disc_itching" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
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
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Galactorrea</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}" name="galactorrea" theme="simple" cssClass="form-control" ></s:select>
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">H/O Contraception</label>
											   	<s:textfield name="ho_contraception" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Rubella Vaccine</label>
											   		<s:textfield name="rubella_vaccine" placeholder=""  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Menopause</label>
											   	<s:textfield name="menopause" placeholder="Yrs"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	
	                        	
                       
                       </div>  
                       
                       		<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=obstretic_history %>">
								<h5>OBSTRETIC HISTORY</h5> 
							</div>
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=obstretic_history %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;border-bottom: 1px dashed #ddd;padding: 0px 0px 10px 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			<form>
											<label class=""><s:checkbox theme="simple" name="nulligravida" /><i></i> Nulligravida</label>
										    <label class=""><s:checkbox  name="current_pregnent" /><i></i> Currently Pregnent</label>
										    <label class=""><s:checkbox  name="iud" /><i></i> IUD</label>
										  </form>
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			<div class="form-group">
											           	<lable>Live Boys</lable>
											           	<s:textfield  name="live_boys" onkeyup="addObsRow(this.value,'Boys')" value="" cssClass="form-control" />
											           </div> 
											           
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			
											           <div class="form-group">
											           	<lable>Live Girls</lable>
											           	<s:textfield  name="live_girls" onkeyup="addObsRow(this.value,'Girls')" value="" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			
											           <div class="form-group">
											           	<lable>Stillbirths</lable>
											           	<s:textfield  name="stillbirths" value="" onkeyup="addObsRow(this.value,'Stillbirths')" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
											          
											           <div class="form-group">
											           	<lable>Abortions</lable>
											           	<s:textfield  name="abortions" value="" onkeyup="addObsRow(this.value,'Abortions')" cssClass="form-control" />
											           </div>   
	                        		</div>
											                   			
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
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
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        				<div class="form-group">
	                        					<lable>Parity/Abortions Notes</lable>
	                        					<s:textarea cssClass="form-control" theme="simple" name="parity_abortion_notes" rows="3" />
	                        				</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
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
                        
                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
	                        	</div>
                        
                              
                               <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>DIAGNOSIS</h5> 
							</div>
                            
	                        
	                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                
                               <table class="table" width="100%">
                                     <thead> 
                                        <tr> 
                                         <th><b> Select Diagnosis</b>&nbsp; <a type="button" onclick="addMoreCondition('innercondition','spn0')" class="btn btn-primary hidden" >+</a>  <input type="button" value="+" class="btn btn-info" onclick="dispDIv()" />  </th> 
                                         <th></th>
                                         <th id="viewtp"><b>Select Treatment Episode</b>&nbsp; <a type="button" onclick="createTreatmentEpisode()" class="btn btn-info">+</a></th> 
                                         </tr> 
                                        </thead> 
                                        
                                    <tbody> 
                                        <tr> 
                                        <td width="50%;">
                                          <table id="innercondition" width="100%">
                                          <tbody>
                                          <tr>
                                              <td>
                                                 <s:select list="condtitionList" listKey="id" listValue="treatmentType" 
                                                  name="conditions[0].conditionid" id="condition0"  cssClass="form-control marbo3dtab chosen-select" 
                                                  title="select condition" headerKey="0" headerValue="Select Diagnosis"/>
                                                
                                               </td>
                                               <td><input type="hidden" name="conditions[0].conditionname" id="conditionname0"/></td>
                                               <td width="3%"><span id="spn0"><a type="button" class="btn btn-success savebtn"  onclick="addMoreCondition('innercondition','spn0')">+</a></span></td>
                                           </tr>    
                                          </tbody>
                                        </table>
                                        </td>
                                        <td></td>
	                                         <td id="treatmentepisodeajax"> 
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
                                          <td><input type="button" onclick="addnewCOndition(0)" class="btn btn-sm btn-info" style="margin-top: 8px;" value="Save" /></td>
                                          <td></td>
                                        </tr> 
                                        </tbody>
                                         
                                    </table>
                    </div>
	                        	
	                        </div>
	                        
	                        
		                         
		                         </div>
		                         <div class="col-lg-12 col-md-12" style="margin-top: 15px;">
		                            <div class="row">
		                                <div class="col-lg-12 col-md-12">
		                                    <input type="button" class="btn btn-primary savebtn savebigbtn" value="Save" onclick="savevalidate()">
		                                </div>
		                            </div>
		                        </div>
	
	
	
</div>








   
                    <div class="panel-body hidden">
                        <div class="col-lg-12 col-md-12 col-xs-6 col-sm-12 settopad bordertopgray backgrey">
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
                             
								     <!--
								   <s:select list="userList" listKey="id" listValue="diaryUser" 
                                               cssClass="form-control chosen-select"  name="secndryconsult" id="secndryconsult" 
                                               headerKey="0" headerValue="Select Consultant"/>
								  -->
								  
									<button type="button" style="width: 100%;" class="btn btn-default btn-sm dropdown-toggle hidden" data-toggle="dropdown">Secondary Consultant <span class="caret"></span></button>
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
						    </div>
						   

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
	
	
	
	
	

<!-- Add Treatment Episode -->
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
	</div>
	
	
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
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" value="<s:property value="id"/>" class="doctors"><i></i>  <s:property value="diaryUser"/></label></li>
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
  <script>
	$(".modal-draggable .modal-dialog").draggable({
        handle: ".modal-header"
    });
    $(function() {
	  $('.addnewpat').slimScroll({
	        height: '500px',
	        railVisible: true,
			alwaysVisible: true
	  });
	  $('.patientlist').slimScroll({
	        height: '430px',
	        railVisible: true,
			alwaysVisible: true
	  });
	  $('.secoconsullist').slimScroll({
	        height: '300px',
	        railVisible: true,
			alwaysVisible: true
	  });
 	});
	</script>
  <!-- JS -->
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
  <script src="inventory/js/searchtext/javascripts/initializers.js"></script>
  <!-- JS ends -->
  
  
  
  
</html>
