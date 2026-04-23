<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

   
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
        height: 68px !important;;
        }
    </style>
    <script type="text/javascript">
	/* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	            )
	        ); });  */
	        
	        
	      /*   $(document).ready(function() {

	    		$("#admissiondate").datepicker({

	    			dateFormat : 'dd-mm-yy',
	    			yearRange: yearrange,
	    			minDate : '30-12-1880',
	    			changeMonth : true,
	    			changeYear : true

	    		});

	    		
	    	});
	         */
	        
	        
	        bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
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
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fpnotest');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('nauseanotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fnunotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('chestpainnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('dimvisionnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hgunotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hmnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('jointpainnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('specialnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('edemafeetnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hematurianotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('oligurianotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pstgunotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('ihnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmenotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('tnenotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('weaknessnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('constpationnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pasthistory');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('personalhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('familyhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('onexamination');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('smnotes');
	        	 
	        	
	        	 
	        	 
	        	 $('.nicEdit-panelContain').parent().width('auto');
	        	 $('.nicEdit-panelContain').parent().next().width('auto');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
</script>
    
    
</head>
<body>
<s:form action="updateIpd" theme="simple" id="ipdadmissionfrm">


    <div class="container-fluid">
        <!-- /.row -->
         <div>
              <s:hidden name="id"/>
               <s:hidden id="clientid" name="clientid"/>
              <s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div class="row details">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

                                <img src="_assets/img/admissionform.png" class="admissionlogo">

                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
                                <div>
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Select Clienta</label>
                                            <s:textfield  name="client" id="client" readonly="true"
				                                 cssClass="form-control"  placeholder="Search by client" />
                                        </div>
                                    </div>
                                    
                                    
             
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Patient Bar Code</label>
                                            <s:textfield type="email" cssClass="form-control" id="patientbarcode" name="patientbarcode" />
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Admission Code</label>
                                            <s:textfield cssClass="form-control" id="exampleInputEmail1" name="admissioncode" />
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Admission Date</label>
                                            <s:textfield cssClass="form-control" id="admissiondate" name="admissiondate" readonly="true"  />
                                        </div>
                                    </div>
                                   <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                   
                                     <div class="form-group">
	                                            
	                                            <div class="row">
	                                            <div class="col-lg-6 col-md-6">
	                                            <label for="exampleInputEmail1">HH</label>
	                                            <s:select name="hour" id="hour" list="hourList" disabled="true"
	                                            	cssClass="form-control"
	                                            	headerKey="0" headerValue="Select"/>
	                                            </div>
	                                             <div class="col-lg-6 col-md-6">
	                                             <label for="exampleInputEmail1">MM</label>
	                                              <s:select name="minute" id="minute" list="minuteList" disabled="true"
	                                            	cssClass="form-control"
	                                            	headerKey="0" headerValue="Select"/>
	                                            </div>
	                                            </div>
	                                            
	                                           
												
	                                             <%-- <s:textfield type="email" cssClass="form-control" id="admissiontime" name="admissiontime" /> --%>
	                                             
	                                        </div>
                                    
                                    
                                    	
	                                 </div>
                                    <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Admission IP</label>
                                             <s:textfield type="email" cssClass="form-control" id="exampleInputEmail1" name="admissionip" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 adformback">
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Admitting Consultant</label>
                                            <div class="col-xs-9">
                                                <s:select list="userList" listKey="id" listValue="diaryUser" 
                                                cssClass="form-control" id="practitionerid" name="practitionerid"
                                                headerKey="0" headerValue="Select Consultant"/>
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Location</label>
                                            <div class="col-xs-9">
                                                 <s:textfield cssClass="form-control" id="department" name="department" />
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Referred From</label>
                                            <div class="col-xs-9">
                                                 <s:textfield cssClass="form-control" id="refferedby" name="refferedby"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Ward</label>
                                            <div class="col-xs-5">
                                               <s:select onchange="setBedList(this.value)" list="wardlist" listKey="id" listValue="wardname" 
                                               cssClass="form-control" name="wardid" id="wardid" 
                                               headerKey="0" headerValue="Select Ward"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Bed no.</label>
                                            <div class="col-xs-5" id="bedlistdiv">
                                                   <s:select  list="bedlist" listKey="id" listValue="bedname" 
                                                   cssClass="form-control" name="bedid" id="bedid"
                                                   headerKey="0" headerValue="Select Bed"/>
                                            </div>
                                            <a href="#"><img src="_assets/img/bedbtn.png" class="img-responsive bednosele" /></a>
                                        </div>

                                       
                                       
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Insurance</label>
                                            <div class="col-xs-4">
                                                 <%--  <s:textfield cssClass="form-control" id="tpid" name="tpid" /> --%>
                                                 <s:select disabled="true" id="tpid" name="tpid" listKey="id" 
													listValue="thirdParty"  headerKey="0" headerValue="All"
													list="thirdPartyList" cssClass="form-control"></s:select>
                                            </div>
                                           
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Status</label>
                                            <div class="col-xs-4">
                                         <s:textfield cssClass="form-control" id="status" name="status"/>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Admission For</label>
                                            <div class="col-xs-9">
                                                <s:textarea cssClass="form-control" rows="3" cols="5" id="addmissionfor"  name="addmissionfor"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Reimbursement</label>
                                            <div class="col-xs-9">
                                                   <s:textarea cssClass="form-control" rows="3" cols="5" id="reimbursment" name="reimbursment"/>
                                            </div>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Secondary Consultant</label>
                                            <div class="col-xs-9">
                                               <s:select list="userList" listKey="id" listValue="diaryUser" 
                                               cssClass="form-control"  name="secndryconsult" id="secndryconsult" 
                                               headerKey="0" headerValue="Select Consultant"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">MLC no.</label>
                                            <div class="col-xs-5">
                                                   <s:textfield cssClass="form-control" id="mlcno" name="mlcno"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <button class="btn btn-primary" disabled>MLC Details</button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Admission Details</label>
                                            <div class="col-xs-9">
                                                <s:textarea cssClass="form-control" rows="3" cols="5" name="admissiondetails" id="admissiondetails" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Sugested Treatment</label>
                                            <div class="col-xs-9">
                                                 <s:textarea cssClass="form-control" rows="3" name="suggestedtrtment" id="suggestedtrtment"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Hospital/Clinic</label>
                                            <div class="col-xs-9">
                                                <s:textarea cssClass="form-control" rows="3" id="hosp" name="hosp" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Package</label>
                                            <div class="col-xs-9">
                                          <s:textarea cssClass="form-control" rows="3" id="packagename" name="packagename" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 adformback">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <b class="diagtitle">Admission Summary</b>
                                    </div>

                                </div>
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop15">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-2">Chief complaints</label>
                                            <div class="col-xs-10">
                                                      <s:textarea cssClass="form-control" rows="6" maxlength="" name="chiefcomplains" id="chiefcomplains"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop15">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-2">Present Illness</label>
                                            <div class="col-xs-10">
                                                      <s:textarea cssClass="form-control" rows="6" maxlength="" name="presentillness" id="presentillness"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><b>Systemic Review <i class="fa fa-chevron-down"></i></b></a>
                                    <div class="collapse" id="collapseExample">
                                        <div class="">
                                            <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                                <div class="from-horizontal">


                                                   <%--  <div class="form-group martop10only">
                                                        <label for="inputEmail" class="control-label col-xs-3">Suggested Opr.</label>
                                                        <div class="col-xs-9 marbot15">
                                                                 <s:textarea cssClass="form-control textareaheight" rows="3" id="suggestoper" name="suggestoper" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="inputEmail" class="control-label col-xs-3">Department</label>
                                                        <div class="col-xs-9 marbot15">
                                                           <s:textarea cssClass="form-control textareaheight" rows="3" id="systdepartment" name="systdepartment" />
                                                        </div>
                                                    </div> --%>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Fever at present<br>
                                                        
                                                        </label>
                                                          <div class="col-xs-3">
                                                           <s:select name="fpcondition" id="fpcondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                               <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="fpnotest" id="fpnotest"/>
                                                        </div>
                                                       
                                                    </div>


                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Nausea<br>
                                                        
                                                        </label>
                                                         <div class="col-xs-3">
                                                            <s:select name="nauseacondition" id="nauseacondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" cols="5" placeholder="Note" name="nauseanotes" id="nauseanotes"/>
                                                        </div>
                                                       
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Frequent Nocturnal Urination<br>
                                                       
                                                        </label>
                                                         <div class="col-xs-3">
                                                             <s:select name="fnucondition" id="fnucondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" cols="5" placeholder="Note" name="fnunotes" id="fnunotes"></s:textarea>
                                                        </div>
                                                       
                                                    </div>

                                                    <div class="form-group col-xs-12"">
                                                        <label for="inputEmail" class="control-label col-xs-3">Straining at micturation<br>
                                                       
                                                        </label>
                                                         <div class="col-xs-3">
                                                             <s:select name="smcondition" id="smcondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="smnotes" id="smnotes"></s:textarea>
                                                        </div>
                                                       
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Chest pain<br>
                                                        
                                                        </label>
                                                       <div class="col-xs-3">
                                                           <s:select name="chestpaincond" id="chestpaincond" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                           <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="chestpainnotes" id="chestpainnotes"></s:textarea>
                                                        </div>
                                                         
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Dimness of vision <br>
                                                       
                                                        
                                                        </label>
                                                        <div class="col-xs-3">
                                                             <s:select name="dimvisioncond" id="dimvisioncond" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="dimvisionnotes" id="dimvisionnotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Headache, Giddiness, Unconsciousness<br>
                                                       
                                                        </label>
                                                        <div class="col-xs-3">
                                                            <s:select name="hgucondition" id="hgucondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="hgunotes" id="hgunotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Haemoptysis, Malena<br>
                                                       
                                                        </label>
                                                       <div class="col-xs-3">
                                                            <s:select name="hmcondition" id="hmcondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="hmnotes" id="hmnotes"></s:textarea>
                                                        </div>
                                                         
                                                    </div>

                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Joint pain<br>
                                                       
                                                        </label>
                                                         <div class="col-xs-3">
                                                             <s:select name="jointpaincond" id="jointpaincond" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="jointpainnotes" id="jointpainnotes"></s:textarea>
                                                        </div>
                                                       
                                                    </div>

                                                     <div class="form-group">
                                                        <label for="inputEmail" class="control-label col-xs-3">Special Notes/Remarks</label>
                                                        <div class="col-xs-9 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="specialnotes" id="specialnotes"></s:textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                                <div class="from-horizontal">
                                                   
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Edema feet<br>
                                                       
                                                        </label>
                                                        <div class="col-xs-3">
                                                             <s:select name="edemafeetcondi" id="edemafeetcondi" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="edemafeetnotes" id="edemafeetnotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Hematuria<br>
                                                        
                                                        </label>
                                                        <div class="col-xs-3">
                                                            <s:select name="hematuriacondi" id="hematuriacondi" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="hematurianotes" id="hematurianotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Burning micturation<br>
                                                       
                                                        </label>
                                                       <div class="col-xs-3">
                                                            <s:select name="bmcondition" id="bmcondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
													    </s:select> 
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="bmnotes" id="bmnotes"></s:textarea>
                                                        </div>
                                                         
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Oliguria<br>
                                                       
                                                        </label>
                                                        <div class="col-xs-3">
                                                             <s:select name="oliguriacondi" id="oliguriacondi" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="oligurianotes" id="oligurianotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Passing stone or gravel in the urine<br>
                                                       
                                                        
                                                        </label>
                                                         <div class="col-xs-3">
                                                             <s:select name="pstgucondi" id="pstgucondi" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                           <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="pstgunotes" id="pstgunotes"></s:textarea>
                                                        </div>
                                                       
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Impaired hearing<br>
                                                       
                                                        </label>
                                                        <div class="col-xs-3">
                                                             <s:select name="ihcondition" id="ihcondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="ihnotes" id="ihnotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Breathlessness on mild exertion<br>
                                                       
                                                        </label>
                                                       <div class="col-xs-3">
                                                            <s:select name="bmecondition" id="bmecondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                            <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="bmenotes" id="bmenotes"></s:textarea>
                                                        </div>
                                                         
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Tingling, Numbness in extremities<br>
                                                        
                                                        </label>
                                                         <div class="col-xs-3">
                                                            <s:select name="tnecondition" id="tnecondition" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                             <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="tnenotes" id="tnenotes"></s:textarea>
                                                        </div>
                                                       
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Weakness<br>
                                                        
                                                      
                                                        </label>
                                                        <div class="col-xs-3">
                                                               <s:select name="weaknesscondi" id="weaknesscondi" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                          <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="weaknessnotes" id="weaknessnotes"></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group col-xs-12">
                                                        <label for="inputEmail" class="control-label col-xs-3">Constipation<br>
                                                        
                                                        </label>
                                                        <div class="col-xs-3">
                                                            <s:select name="constipationcond" id="constipationcond" 
																list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control">
															</s:select>
                                                        </div>
                                                        <div class="col-xs-6 marbot15">
                                                         <s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" name="constpationnotes" id="constpationnotes" ></s:textarea>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                   
                                       
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Past History</label>
                                            <div class="col-xs-9">
                                           <s:textarea cssClass="form-control" rows="3" maxlength="" name="pasthistory" id="pasthistory"></s:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Personal History</label>
                                            <div class="col-xs-9">
                                            	<s:textarea cssClass="form-control" rows="3" maxlength="" name="personalhist" id="personalhist"></s:textarea>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">Family History</label>
                                            <div class="col-xs-9">
                                                 <s:textarea cssClass="form-control" rows="3" maxlength="" name="familyhist" id="familyhist"></s:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label col-xs-3">On Examination</label>
                                            <div class="col-xs-9">
                                                 <s:textarea cssClass="form-control" rows="3" maxlength="" name="onexamination" id="onexamination"></s:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 adformback daignosis">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                        <b class="diagtitle">Diagnosis Section</b>
                                    </div>

                                </div>
                               
                                 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                	<div class="col-lg-10 col-md-10">
                              		 <table class="table" width="100%">
                                     	<caption>.</caption>
                                    	 <thead> 
                                        	<tr> 
	                                         	 <th>Delete   #   Select Diagnosis</th> 
		                                        <th></th>
		                                        <th></th>
		                                         <th>Select Treatment Episode</th> 
                                        	 </tr> 
                                        </thead> 
                                      
                                    <tbody> 
                                        <tr> 
                                       		 <td>
                                       		 <div class="row">
		                                          <table id="innercondition" width="100%">
			                                          <% ArrayList<Bed>ipdConditionids = (ArrayList<Bed>)session.getAttribute("ipdConditionids");%>
			                                          <%ArrayList<Client> ipdCondtitionList = (ArrayList<Client>)session.getAttribute("ipdCondtitionList"); %>
			                                          <% int i = 0; for(Bed bed : ipdConditionids){%>
	                                          
                                           				<tr>
                                              				<td><INPUT type="checkbox" name="chk" title="Delete row" <% if(i==0){%> disabled <%} %>/></td>
                                             				<td scope="row"><%=i+1 %></td> 
                                            			
                                            			    <td>
                                               
		                                              			<select id="condition<%=i %>" name="conditions[<%=i%>].conditionid"    class="form-control">
		                                              				<% for(Client client : ipdCondtitionList){%>
		                                              					<option value="<%=client.getId() %>"><%=client.getTreatmentType() %></option>
		                                              				<% }%>
		                                              			</select>
                                              			
                                              			<script>
                                              				document.getElementById('condition<%=i %>').value = <%=bed.getConditionid()%> 
                                              			
                                              			</script>
                                              			
                                              		 </td>
                                              		
                                               		
                                               
                                              		 <!-- <td><input type="hidden" name="conditions[0].conditionname" id="conditionname0"/></td> -->
                                               
                                               		
                                          		</tr>
                                               
                                           <%i++;} %>
                                           
                                        </table>
                                        </div>
                                        </td>
                                       	 <td> <input type="button" class="btn btn-primary savebtn" value="Add More" onclick="addMoreCondition('innercondition')"></td>  
                                        			 <td><input type="button" class="btn btn-primary" value="Delete Row" onclick="deleteCondition('innercondition')"></td>
                                        
                                          <td id="treatmentepisodeajax"> 
                                         	<%-- <select name="treatmentEpisode" id="treatmentEpisode" class="form-control">
                                         		<option value="0">Select Treatment Episode</option>
                                         	</select> --%>
                                         	<s:select name="treatmentEpisode" id="treatmentEpisode" list="treatmentEpisodeList"
                                         	listKey="id" listValue="treatmentEpisodeName" headerKey="0" headerValue="Select Treatment Episode"
                                         	cssClass="form-control"/>
                                         </td>
                                        
                                        <td><i onclick="createTreatmentEpisode()" style="cursor: pointer;" title="Add Treatment Episode" class="fa fa-plus-square fa-2x aadpres"></i></td>
                                        
                                        </tr> 
                                        </tbody> 
                                    </table>
                                </div>
                                <div class="col-lg-2 col-md-2 martop38">
                           		 <div class="row">
                                	<div class="col-lg-12 col-md-12">
                                   		 <input type="button" class="btn btn-primary savebtn" value="Update" onclick="checkTreatmentEpisodeStatusAjax()">
                               		 </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
        <!-- /.row -->

    </div>
   

</s:form>



 

</body>

<!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
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

</html>
