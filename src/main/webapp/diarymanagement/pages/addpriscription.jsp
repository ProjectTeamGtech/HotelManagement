  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
  <style>
  .tables_ui {
  display:inline-block;
  margin:2px 2%;
  border:2px solid #3333fe;
  border-spacing:0;
}
.tables_ui ul li {
  min-width: 200px;
}
.dragging li.ui-state-hover {
  min-width: 240px;
}
.dragging .ui-state-hover a {
  color:green !important;
  font-weight: bold;
}
.tables_ui th,td {
  text-align: right;
  padding: 2px 4px;
  border: 1px solid;
}
.t_sortable tr, .ui-sortable-helper {
  cursor: move;
}
.t_sortable tr:first-child {
  cursor: default;
}
.ui-sortable-placeholder {
  background: yellow;
}
         
         .marleft14 {
             margin-left: 5px;
         }
         .priswidth {
             width: 13%;
         }
         .note {
             width: 25%;
         }
         /*.modal-lg {
             width: 1260px;
         }*/
         .manbtn {
             margin-left: -16px;
             margin-right: -13px;
         }

         .widset {
             width: 130%;
         }

         .marleft6 {
             margin-left: 6px;
         }

         .border {
             border: 1px solid;
             padding: 3px 25px 10px 10px;
         }

         .borderlanguage {
             border: 1px solid;
             padding: 3px 25px 8px 10px;
         }

        

         .thfont {
                 font-weight: normal !important;
    			color: #000;
         }

         .marleft17 {
             margin-left: 17px;
         }
         .open{
             color:red;
         }
         .deliverd {
             color: green;
         }
         .process {
             color: blue;
         }
         .payrece {
               
			    margin-top: -18px !important;
         }
         .backicon {
             background-color: #C0C0C0;
             border: 1px solid #3D3D3D;
             padding: 5px 3px 2px 5px;
             color: #000;
             width: 100%;
         }
         .wrapperfixed {
             display: inline-block;
             margin-top: 35px;
             padding: 0px 0px 0px;
             width: 100%;
             height: 600px;
         }
        
         
         .pname{
             color:#fff;
         }
         .marleft9 {
             margin-left: -9px;
         }
         .martopmin10 {
             margin-top: -10px;
         }
         .martpomin15 {
             margin-top: -15px;
         }
         .follow {
                 width: 45px !important;
    			margin-left: -11px;
         }
         .fodays {
		    width: 58px;
		    margin-left: 48px !important;  
		}
         .tableback {
    background-color: #4E7894;
    color: rgb(255, 255, 255);
    font-style: normal;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 0px 0px 0px 1px;
    line-height: 1.42857143;
    text-align: left;
    vertical-align: top;
    border-top: 0px solid #ddd;
    border-bottom: 1px solid #ddd !important;
}



.form-control {
    padding: 0px 4px !important;
}
        .martop6{
            margin-top: 6px;
        }
        .marleftmin6{
        margin-left: -6px !important;
        }
        .dayw{
	    margin-left: -22px !important;
	    margin-top: 3px !important;
        }
        .dosen{
            margin-top: 11px;
    margin-left: -51px;
        }
        .martop10{
         margin-top:10px;
        }
        h5, .h5 {
		    font-size: 14px !important;
		}
        .pdate{
    width: 10% !important;
}
.pnamewidth{
    width: 17% !important;
}
.setyerx{
    margin-top: 8px;
}
 
.btngrey{
	margin-top: -6px;
    background-color: #ddd;
    padding: 5px;
}
.well {
    min-height: 57px;
    padding: 5px;
    margin-bottom: 1px;
    background-color: #f5f5f5;
    border: 1px solid #e3e3e3;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
}
.backshdow{
			background-color:rgba(92, 184, 92, 0.13);
		    padding-top: 1px !important;
		    border: 2px solid #5cb85c;
		}
.chosen-container {
    text-transform: uppercase;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 0p;
    margin: 0p;
    padding: 0px !important;
    list-style: none !important;
}

.checkbox-custom-alt > i {
    border: none;
    color: green;
}
   
.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
    font-size: 14px;
    top: 1px;
}
.checkbox-custom-alt input:checked + i {
    background-color: transparent;
    border-color: #666;
    color: #5cb85c;
    font-size: 19px;
}
.radio, .checkbox {
    font-weight: normal;
}
.btnbigset{
	width: 100%;
    height: 85px !important;
    line-height: 72px;
    font-size: 38px;
}
/*.btnbigsetnew {
    width: 100%;
    height: 60px !important;
    line-height: 52px;
    font-size: 38px;
}*/
.form-group {
    margin-bottom: 5px !important;
}

     </style>
     
     <script>
     
     $(document).ready(function() {
         
 		$("#backDate").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange : yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});

 		$("#toDate").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange : yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});

 	});
     
     </script>
     <%LoginInfo loginfo2 = LoginHelper.getLoginInfo(request);%>
     
     <input type="hidden" id="outoprisc" value="<%=loginfo2.getOutoprisc()%>"/>
     
                  <s:form theme="simple">
                  		<%if(loginfo2.isBalgopal()){ %>
                  			<input type="hidden" id="saveandprinthalf" value="1">
                  		<%}else{ %>
                  			<input type="hidden" id="saveandprinthalf" value="0">
                  		<%} %>
                  		<%if(loginfo2.getOutoprisc()==1){ %>
                  			<s:hidden id="isfromoutoincriprisc" value="1"></s:hidden>
                  		<%}else{ %>
                  			<s:hidden id="isfromoutoincriprisc" value="0"></s:hidden>
                  		<%} %>
                  		<s:hidden id="admi_disc_ipd_prisclist"></s:hidden>
                  		<s:hidden name="priscdate" id="priscdate"/>
                         <s:hidden name="priscdateandtime" id="priscdateandtime"/>
                         <input type="hidden" name="priscautoid" id="priscautoid" value="0"/>
                         <input type="hidden" id="masterfrequency"/>
                         <input type="hidden" id="masteriswbd"/>
                         <input type="hidden" id="mastercaldose"/>
                         <input type="hidden" id="masterstrength"/>
                         <!-- <input type="hidden" id="invstlistid"  /> -->
                  		<div class="row">
                  			<div class="col-lg-12 col-xs-12 col-sm-12 cstm-popup-bx" style="padding: 0px; padding-left: 7px;">
                  			 <div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
                  				
                  					<%-- <div class="form-group" style="/*width: 81.5%;display: inline-flex;margin-bottom: 0px !important;*/">
                  						<label style="margin-bottom: 0px !important;"><b>Diagnosis</b></label>
                  					</div>
                  					 
                  					<div class="form-group notranslate" style="width: 86.5%;display: inline-flex;" id="diagnosdiv">
                  						
                  					    <s:select cssClass="form-control showToolTip chosen-select" class="btn btn-sm btn-primary" name="diagnosisname" id="diagnosisname" 
                  					    onchange="getmedondiagnosis(this.value)" list="Diagnosislist" tabindex="1" listKey="id" listValue="name" 
                  					    headerKey="0" headerValue="Select Diagnosis" theme="simple"/>	 
                  						 
                  					</div>
                  	 --%>
                  					<div class="form-group" style="/*width: 81.5%;display: inline-flex;margin-bottom: 0px !important;*/">
                  						<label style="margin-bottom: 0px !important;"><b>Medicine</b></label>
                  					</div>
                  					 
                  					<div class="form-group notranslate" style="width: 86.5%;display: inline-flex;" id="mdicinediv">
                  						<%if(loginfo2.isAdd_medicine() || loginfo2.getUserType()==2){ %>
                  							<a title="Add New Medicine" href="#" type="button" class=""  onclick="openhiddendiv('hiddendiv')" style="margin-right: 5px;"><i class="fa fa-pencil fa-2x cstm-icon" aria-hidden="true"></i></a>
                  						<%} else{%>
                  							<a title="Add New Medicine" href="#" type="button"  style="margin-right: 5px;"><i class="fa fa-pencil fa-2x" aria-hidden="true"></i></a>
                  						<%} %>
                  						<s:select cssClass="form-control showToolTip chosen-select" class="btn btn-sm btn-primary" name="mdicinename" id="mdicinename" onchange="getDoseNote(this.value)" list="medicineList" tabindex="1" listKey="id" listValue="genericname" headerKey="0" headerValue="Select Medicine">	</s:select>
                  						&nbsp;
                  						
                  					</div>
                  					 
                  					<div class="form-group"  style="display: inline-flex;width: 86%;margin-bottom: 0px !important;">	
                  						<label style="margin-bottom: 0px !important;"><b>Medicine Template</b></label>
                  					</div>
                  					<div class="form-group" id="prisctemplatediv" style="display: inline-flex;width: 86%;">
			                          	<a href="#" type="button" class="" style="margin-right:  5px;" data-toggle="modal" data-target="#selecttemplate"><i class="fa fa-align-justify cstm-icon" aria-hidden="true"></i></a>
			                          	<s:select onchange="showTemplatePrisc(this.value)" id="templatename" name="templatename" list="templateNameList" headerKey="0" headerValue="Select Template" listKey="id" listValue="templatename" cssClass="form-control chosen-select"/>
			                          	
                  					</div>
                  					
                  					<div class="form-group"  style="display: inline-flex;width: 86%;margin-bottom: 0px !important;">	
                  						<label style="margin-bottom: 0px !important;"><b>Repeat Prescription</b></label>
                  					</div>
                  					
                  					<div class="form-group" id="rpeatdivajax">
	                                    <s:select onchange="editparentprisc(this.value)" name="repeatdate" id="repeatdate" list="parentPriscList" listKey="id" listValue="date" cssClass="form-control chosen-select" headerKey="0" headerValue="Repeat Prescription" />
                  					</div>
                  					
                  					
                  					
                  				</div>
                  				
                  				<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 " style="padding-left: 15px;">
                  					 <%--  <div class="form-group dosaheight ">
                  						<ul>
                  						    <s:iterator value="dosageList">
                  							<li><label class="checkbox checkbox-custom-alt"><input class="jituradioclass" value="<s:property value="name"/>" type="radio" name="dosage" id="dosage<s:property value="id"/>"><i style="margin-right: 15px;"></i><s:property value="name"/></label></li>
                  							</s:iterator>
                  						</ul>
                  					</div>   --%>
                  					
                  					<div class="form-group"  style="margin-bottom: 0px !important;">	
                  						<label style="margin-bottom: 0px !important;"><b>Frequency</b></label>
                  					</div>
                  					
                  					<div class="form-group" id="prisccodediv">
                  						<s:select list="dosageList" id="prisccode" tabindex="2" onchange="setdosegiventiming(this.value)" headerKey="0" headerValue="Frequency" listKey="name" listValue="name" cssClass="form-control chosen-select"></s:select>
                  					</div>
                  					
                  					<div class="form-group"  style="margin-bottom: 0px !important;">	
                  						<label style="margin-bottom: 0px !important;"><b>Frequency Note</b></label>
                  					</div>
                  					
                  					 <div class="form-group" id="prisctimediv">
                  					 	<s:select list="medicinetimelist" headerKey="0" headerValue="Frequency Note" id="prisctime" listKey="id" listValue="priscriptiontime" cssClass="form-control chosen-select"></s:select>
                  					 </div>
                  					
                  					
                  					
                  				<!-- <div style=" height: 10px;"></div> -->
                  				
                  					<div class="form-group"  style="margin-bottom: 0px !important;">	
                  						<label style="margin-bottom: 0px !important;"><b>Routes</b></label>
                  					</div>
                  					
                  					<div class="form-group" id ="priscdosenotesdiv">
                  						<s:select id="priscdosenotes" name="priscdosenotes" list="dosagenoteList"
                                          listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"
                                          headerKey="0" headerValue="Select Routes"/>
                  					</div>
                  					
                  				</div>
                  				
                  				<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
                  					
                  					
                  				
                  					
                  					<!-- <div class="form-group" style="width: 100%;line-height: 27px;"> -->
                  					<div class="form-group" style="width: 100%;">
                  						<label style="margin-bottom: 0px !important;"><b>Days</b></label><label style="padding-left: 113px;margin-bottom: 0px !important;"><b> Quantity</b></label>
                  						<div style="display: inline-flex;">
                  						<!-- <input placeholder="Days" type="text" onchange="calculatePriscQty()" class="form-control" id="priscdays" size="2" onkeyup="movetoNext(this, 'second')" maxlength="2" style="margin-right: 5px;"> --> 
	                  						<%if(loginfo2.getOutoprisc()==0){ %>
	                  							<input placeholder="days" type="number" onchange="calculatePriscQty()" class="form-control" id="priscdays" size="5"  maxlength="5" style="margin-right: 5px;">
	                  							<input placeholder="Qty" type="number" class="form-control" id="priscqty" size="5"  maxlength="5" style="margin-right: 5px;">
	                  						<%}else{ %>
	                  							<input placeholder="days" type="number" onchange="calculatePriscQty()" class="form-control" id="priscdays" size="5"  maxlength="5" style="margin-right: 5px;">
	                  							<s:select name="priscqty" id="priscqty" list="nimaiqtylist"
	                  							listKey="name" listValue="name"
	                  							headerKey="0" headerValue="0"
	                  							cssClass="form-control"/>
	                  						<%} %>
                  						</div>
                  					</div>
                  					
                  					
                  					<div class="form-group" style="width: 100%;">
                  						<label style="margin-bottom: 0px !important;"><b>Strength</b></label><label style="padding-left: 90px;margin-bottom: 0px !important;"><b> Type</b></label>
                  						<!-- <input placeholder="dose" type="text" class="form-control" id="dddose" > -->
                  						 <!-- &nbsp; -->
                  						 <div style="display:inline-flex;">
                  							<%if(loginfo2.getOutoprisc()==0){ %>
	                  						    <input type="hidden" id="dddose" >
	                  							<!-- <input type="hidden" id="unitextension"> -->
	                  							
	                  							<input placeholder="Strength" type="number" class="form-control" id="unit" style="width: 47%;margin-right: 11px;" > &nbsp;
	                  							<s:select id="unitextension" list="priscUnitList" listKey="name" listValue="name" headerKey="0" headerValue="Select Type" style="width: 47%;" cssClass="form-control"></s:select>
	                  						<%}else{ %>
	                  							<input type="hidden" id="unit" >
	                  							
	                  							
	                  							<s:select name="dddose" id="dddose" list="nimaidoselist"
	                  							listKey="name" listValue="name"
	                  							headerKey="0" headerValue="0"
	                  							cssClass="form-control"/>
	                  							
	                  							<s:select id="unitextension" list="priscUnitList" 
	                  							listKey="name" listValue="name" headerKey="0" 
	                  							headerValue="Select Type"  cssClass="form-control"></s:select>
	                  						<%} %>
                  						</div>
                  					</div>  
                  					                  			<%-- 		 <div class="form-group" style="display: none;line-height: 27px;width: 100%;">
                  						<input placeholder="Strength" type="number" class="form-control" id="unit" > &nbsp;
                  						<s:select id="unit" list="priscUnitList" listKey="id" listValue="name" headerKey="0" headerValue="select unit"  cssClass="form-control"></s:select>  
                  						<s:select id="unitextension" list="priscUnitList" listKey="name" listValue="name" headerKey="0" headerValue="Select Type"  cssClass="form-control"></s:select>
                  						<select id="unitextension" class="form-control" >
                  							<option value="Units">Units</option>
                  							<option value="ML">ML</option>
                  						</select>  
                  					</div>  --%>
                  					
                  					<!-- <div class="form-group" style="width: 100%;display: inline-flex;line-height: 27px;">
                  						 <textarea name="priscindivisualremark" id="priscindivisualremark" class="form-control setyerx" placeholder="Remark" rows="1" style="background-color: rgba(255, 248, 220, 0.45) !important;  font-family: Arial, Helvetica, sans-serif; font-size: 14px;"></textarea>
                  					</div> -->
                  					<div id='remlang'>
                  					
                  					<div class="form-group" style="width:100%;display:inline-flex;">
                  					<%if(loginfo2.getOutoprisc()==0){ %>
                  						 <textarea name="priscindivisualremark" id="priscindivisualremark" class="form-control setyerx" placeholder="Remark" rows="2" style="background-color: rgba(255, 248, 220, 0.45) !important;  font-family: Arial, Helvetica, sans-serif; font-size: 14px;"></textarea>
                  					<% }else{%> 
                  						<s:select name="priscindivisualremark" 
										list="nimairemarklist" listKey="id" 
										listValue="name" cssClass="form-control chosen-select" 
										headerKey="0" headerValue="All Remarks" theme="simple" 
										id="priscindivisualremark" ></s:select>&nbsp;<div><i class="fa fa-plus" style="width:10px;" onclick="openEmrPopup('addremarkMaster')"></i></div>&nbsp;<div><i class="fa fa-refresh" style="width:10px;" onclick="refreshremarks()"></i></div>
									<%} %>
								
									</div>
                  				
                  					</div>
                                          
                  				</div>
                  				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12" >
                  					<a href="#" type="button" id="prischide" onclick="addTempPriscription()" title="Add" onkeyup="movetoNext(this, 'third')" class="btn btn-primary btnbigsetnew"><i class="fa fa-plus" style="color: white;"></i> Add</a>
                  			
                                   <div class="form-group" style="margin-top: 10px;font-size: 13px">
                  						Date:&nbsp;<span id="datetimeid"></span>
                  					</div>
                  					<%if(loginfo2.isBams1()){ %>
                  					<div class="form-group" style="margin-top: 10px;font-size: 13px">
                  						<s:textfield readonly="true" name="fromDate" id="backDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
                  					</div>
                  					<%} else{%>
                  					<input type="hidden" id="backDate">
                  					<%} %> 
                  					<br>
                  					<div class="form-group" style="display: inline-flex;font-size: 13px">
                                          <span id="priscdoctornameid" style="font-size: 13px"></span>
                  					</div>
                  					
                  					<%-- <div class="form-group" style="display: inline-flex;line-height: 27px; margin-top: 10px;">
                  						Wt:<span id="weightspan"></span>
                  						
                  					</div> --%>
                  					<div class="form-group" style="display: inline-flex;line-height: 27px; margin-top: 10px;">
                  						<div class="hidden">Dose:<span id="dosespan"></span>
                  						<input type="hidden" id="masterdose">
                  						<input type="hidden" id="masterweight"></div>
                  					</div>
                  				</div>
                  			</div>
                  			
                  			
                  			
                  			
                  		</div>
                  		
                  		<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12 cstm-ui" style="padding: 0px;margin-top: -5px;background-color: #fff;">
                  				<div class="addnewprescriptionbox" id="hiddendiv" style="display: none;">
                  					<div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<input type="text" id="newmedicine" class="form-control" placeholder="Enter Medicine Name" />
                  					</div>
                  					<div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<input type="text" id="newgeneric" class="form-control" placeholder="Enter Generic Name" />
                  					</div>
                  					<div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<input type="text" id="newcomponent" class="form-control" placeholder="Enter Component" />
                  					</div>
                  					<div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<input type="button" onclick="savenewmdicineJSON(0)" class="btn btn-theme" value="Save New" />
                            </div>
                            <div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<input type="button" onclick="savenewmdicineJSON(1)" class="btn btn-theme" value="Save Temp" />
                            </div>
                            <div class="form-group col-lg-2 col-md-2 col-sm-4 col-xs-12">
                  						<a href="#" type="button" class="btn btn-sm btn-danger" onclick="openhiddendiv('hiddendiv')" style="margin-left: 5px;">Cancel</a>
                  					</div>
                  					</div>
                  			</div>
                  			<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding: 0px;margin-top: -5px;background-color: #fff;display: none;"  >
                  				<div class="form-inline" id="manualdosetimediv" style="display: none;">
                  					<div class="form-group" id="dosetimimg1">
                  						<input type="number" id="dosegiventime1"  class="form-control" placeholder="Enter Dose Time" />
                  					</div>
                  					<div class="form-group" id="dosetimimg2">
                  						<input type="number" id="dosegiventime2" class="form-control" placeholder="Enter Dose Time" />
                  					</div>
                  					<div class="form-group" id="dosetimimg3">
                  						<input type="number" id="dosegiventime3" class="form-control" placeholder="Enter Dose Time" />
                  					</div>
                  				</div>
                  		</div> 
                  		
                  		<div class="row">
                  			<div class="col-lg-12 col-xs-12 col-sm-12" >
	                  			<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
	                  				<div class="tableheight" style="overflow: auto; min-height: 398px;">
		                          		<table class="table table-bordered table-responsive " cellspacing="0" width="100%" >
		                                  <thead>
		                                      <!-- <tr class="tableback">
		                                      	  <th width="10%" class="">Sr no.</th>
		                                          <th width="50%" class="" style="text-align: left;">Medicine Name</th>
		                                          <th width="25%" class="">Dosage</th>
		                                          <th width="15%" class="">Unit</th>
		                                          <th width="10%" class="">Duration</th>
		                                          <th width="10%" class="">Remarks</th>
		                                          <th>Delete</th>
		                                      </tr> -->
		                                     <tr class="tableback">
		                                      	  <th style="width: 2%;"  class="">Sr.no.</th>
		                                          <th style=" width: 20%" class="" style="text-align: left;">Medicine Name</th>
		                                          <%if(loginfo2.getOutoprisc()==1 || loginfo2.getClinicid1().equals("raiprachi") || loginfo2.getClinicid1().equals("dentalcl")){ %>
                  								  		<th style="width:6%" class="">Dose</th>
                  								  <%}else{%>
                  								  		<th style="width:6%" class="">Strength</th>
                  								  <%} %>
		                                          
		                                         
		                                          <th style="width:7%" class="">Frequency</th>
		                                           <%if(loginfo2.getClinicid1().equals("raiprachi") || loginfo2.getClinicid1().equals("dentalcl")){%>
		                                            <th style="width:4%" class="">Days</th>
		                                           <%}else{ %>
		                                          <th style="width:4%" class="">Duration</th>
		                                          <%} %>
		                                         <!--  <th width="7%" class="">Time</th> -->
		                                          <%if(!loginfo2.getClinicid1().equals("raiprachi") && !loginfo2.getClinicid1().equals("dentalcl")){%>
		                                          
		                                           <th style="width:10%" class="">Frequency Note</th>
		                                          <%}else{ %>
		                                          <th style="width:7%" class="">Remark</th>
		                                          <%} %>
		                                         <!--  <th width="7%" class="">Unit</th> -->
		                                          <!-- <th width="7%" class="">Strength</th> -->
		                                          <!-- <th width="7%" class=""> Notes</th> -->
		                                          
		                                        <%if(!loginfo2.getClinicid1().equals("raiprachi") && !loginfo2.getClinicid1().equals("dentalcl")){%>
		                                          <th style="width:4%" class=""> Qty</th>  
		                                          <th style="width:7%" class=""> Routes</th>
		                                          <th style="width:10%" class="">Remark</th>
		                                          <%} %>
		                                          <th style="width:2%"><a href="#" onclick="deletepriscdatacleanfull()"><i class='fa fa-trash-o' style="color: white;"></i></a> &nbsp;</th>
		                                      </tr>
		                                       <!-- <tr class="tableback">
		                                      	  <th width="7%" class="">Sr no</th>
		                                          <th width="26%" class="" style="text-align: left;">Medicine Name</th>
		                                          <th width="7%" class="">Strength</th>
		                                          <th width="10%" class="">Dose</th>
		                                          <th width="10%" class="">Duration</th>
		                                          <th width="35%" class="">Frequency / Note / Time / Route / Remark</th>
		                                          <th width="5%"><i class='fa fa-trash-o' ></i> &nbsp;</th>
		                                      </tr> -->
		                                  </thead>
		                                  <tbody id="prisctable" style="font-family: 'Open Sans', sans-serif;font-size: 12px">
		                                      
		
		                                  </tbody>
		                              </table>
		                          	</div>
	                  			</div>
	                  			<div class="col-lg-3 col-md-9 col-xs-12 col-sm-12">
                  					<%-- <div class="form-group">
                                          <span id="priscdoctornameid" style="font-size: 13px"></span>
                  					</div> --%>
                  					
									  <div class="form-group" style="display: inline-flex;">
									    <input type="text" name="mdtemplatename" id="mdtemplatename" class="form-control" placeholder="Template Name" style="width: 75%;">
									    <button type="button" onclick="saveTemplae(0)" title="Save Template" class="btn btn-primary btn-prescrption"><i class="fa fa-save"></i></button>
									    <button  type="button" onclick="updateTemplate(0)" title="Update Template" class="btn btn-primary hidden"><i class="fa fa-save"></i></button>
									  </div>
									  
									

                  					<!-- <div style="height: 30px;"></div> -->
									  <div class="form-group" id="toastTypeGroup">
                                          
                                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" onclick="changelanguage(1)" id="language1" value="option1" checked> English
                                              </label>
                                               <div class="clearfix"></div>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" onclick="changelanguage(2)" id="language2" value="option2"> Marathi
                                              </label>
                                               <div class="clearfix"></div>
                                              <label class="radio-inline">
                                                  <input type="radio" name="inlineRadioOptions" onclick="changelanguage(3)" id="language3" value="option3"> Hindi
                                              </label>
                                          </div>


                                      </div>
                                      
                                      
                                      
                                      
                                      
                                      
									<div class="form-group" style="margin-bottom: 0px;">
										<div class="manasnewipdimg">
											<img src="cicon/mic_off.png" style="width:40px;margin-top: -25px;" class="img-responsive micimg12 michhgt" onclick="startConverting15(this)" title="Microphone" id="changer"></img>
									  </div>
									  <div >
										<form class="form-horizontal">

	                                      <textarea name="priscadvoice" id="priscadvoice" class="form-control setyerx" placeholder="Write Remark" rows="3" style="background-color: rgba(255, 248, 220, 0.45) !important;"></textarea>
	                               
	                                  </form>
	                                  <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="margin-top:15px;">
										<textarea id="transliterateTextarea" class="form-control"></textarea>
									  </div>
									</div>
                  				  </div>
                  				
                  				
                  				<div class="form-group followupafterirf" id="followupdiv1" >
									    <span for="email" style="font-size: 13px;"><b>Follow up After:</b></span>
									    <select name="followupsqty" id="followupsqty" class="form-control fodays" onchange="settoemrfollowup()">
                                             	<%for(int i=0;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                 	                            	<%} %>
                                             </select><br> &nbsp;&nbsp;<b style="font-size: 13px;">Days/SOS</b>
									  </div>
                  				
                  				  <%if(loginfo2.isPrisc_location_list()){ %>
									  		<input type="hidden" id="prisc_location_list" value="1">
									  		<s:hidden name="requestlocationid1" id="bydefaultlocationid"></s:hidden>
									  <%}else{ %>
									  		<input type="hidden" id="prisc_location_list" value="0">
									  <%} %>
                  				<div class="form-group" style="font-size: 13px">
                  				<%if(loginfo2.getClinicid1().equals("raiprachi") || loginfo2.getClinicid1().equals("dentalcl")){ %>
                  				         <s:hidden name="requestlocationid" id="requestlocationid" value="32"></s:hidden>
                  				<%}else{ %>
									    <span for="email"><b>Request Location:</b></span>
									    <s:select list="requestlocationlist" id="requestlocationid" headerKey="0" headerValue="Select Location" name="requestlocationid" listKey="id" listValue="name" class="form-control"></s:select>
									    <%} %>
									  </div>
                  			</div>
                  		</div>
                  		
                  		
                  			<div class="col-lg-12 col-xs-12 col-sm-12">
                          			<table class="table table-responsive hidden">
                          				<tbody>
                          					<tr>
                          						<td  style="width: 31%;">
                          							
                          							<input type="text" class="form-control" id="genericName" placeholder="Genric Name">
                          						</td>
                          						<td style="width: 31%;"><s:select cssClass="form-control showToolTip" name="priscdose" tabindex="2" id="priscdose" list="dosageList" listKey="name" listValue="name" headerKey="0" headerValue="Select Dosage"/> <input type="text" class="form-control" id="medicineName" style="background-color: transparent;margin-top: 4px;" placeholder="Medicine Name"></td>
                          						<td class="hidden" style="width: 9%;">
                          							 <select name="priscdays" id="priscdays" class="form-control" tabindex="3">
                                             	<%for(int i=1;i<=90;i++){ %>
                                             		<option value="<%=i %>"><%=i %></option>
                                             	<%} %>
                                             </select>  
                                                <!-- <input type="number" id="priscdays" class="form-control" onk /> -->
                                             Days
                          						</td>
                          						
                          					</tr>
                          				</tbody>
                          			</table>
                          				<div class="col-lg-12 col-md-12" style="padding: 0px;">
                          					<table class="table" cellspacing="0" width="100%" >
                                  <tbody>
                                      <tr>
                                          <td style="width: 7%;display: none;">
                                              <select class="form-control" name="prisccode" id="prisccodexx">
                                                  <option value="2097">2097</option>
                                                  <option value="2098">2098</option>
                                                  <option value="2099">2099</option>
                                                  <option value="2099">3001</option>
                                                  <option value="3002">3002</option>
                                                  <option value="3003">3003</option>
                                                  <option value="3003">3004</option>
                                                  <option value="3005">3005</option>
                                                  <option value="3006">3006</option>
                                              </select>
                                          </td>
                                          <td class="hidden" style="width: 10%">
                                          	<s:select cssClass="form-control showToolTip chosen-select " name="prisctype" id="prisctype" list="mdicneTypeList" listKey="name" listValue="name" headerKey="0" headerValue="Select Type"/>
                                             <%--  <select class="form-control" name="prisctype" id="prisctype">
                                                  <option value="Tablet">Tablet</option>
                                                  <option value="Capsul">Capsul</option>
                                                  <option value="Oinment">Oinment</option>
                                                  <optionvalue="Liquied">Liquied</option>
                                                  <option value="Injection">Injection</option> --%>
                                                
                                              </select>
                                          </td>
                                          <td class="hidden" style="width: 20%">
                                             	<s:select onchange="setMedicineName(this.value)" cssClass="form-control showToolTip chosen-select" name="mdicinecategory" id="mdicinecategory" list="mdicinecategoryList" listKey="id" listValue="name" headerKey="0" headerValue="Select Category"/>
                                          </td>
                                          
                                          <td style="width:5%;display:none;">
                                          	<!--  <input type="text" class="form-control" id="priscfreq" name="priscfreq"> -->
                                              <select class="form-control" id="priscfreq" name="priscfreq">
                                                  <option>100 MG</option>
                                                  <option>40 MG</option>
                                                  <option>10 MG</option>
                                                  <option>500 MG</option>
                                                  <option>50 MG</option>
                                                  <option>500 Grm</option>

                                              </select> 
                                          </td> 
                                          
                                          <td style="width: 0%; display: none;"><input type="text" class="form-control" id="prisctotal" name="prisctotal" readonly="readonly"></td>
											
                                      </tr>
                                  </tbody>
                              </table>
                          				</div>
                          				
                          				
                          		</div>
                  		
                  		
						
						
						      
                  
                  
                  
                  
                  
                  
                     
                      
                      
                      
                      
                          
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                          	
                          	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                          		
                          		
                          		
                          		<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12" style="padding: 0px;">
                          			
									  
									  <div class="form-group hidden">
									    <select name="followupstype" id="followupstype" class="form-control fodays">
                                                  <option value="Days">Days</option>
                                                  <!-- <option value="Week">Week</option>
                                                  <option value="Month">Month</option> -->
                                              </select>
									  </div>
									  
									
                          		
                                  
                              </div>
                              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10" style="padding: 0px;">
                              
                              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									
									
									
									</div>
                              </div>
                          </div>
                          	</div>
                          	
                          </div>
                          
                          </s:form>
                      
                      
                      
<!-- Consultant -->
<div id="selecttemplate" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.32941176470588235);">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Specialization</h4>
      </div>
      <div class="modal-body">
      				
      	          <s:select list="specializationTemplateList" onchange="setPriscTemplate(this.value)" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="Select Specilization" ></s:select>			
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
 </div>
                      
         	<!-- Modal for adding remark  - lokesh -->
<div id="addremarkmaster" class="modal fade h" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" onclick="closerem()">&times;</button>
        <h4 class="modal-title">Add Remark To List </h4>
      </div>
      <div class="modal-body">
    <p><span>  Remark(Eng)</span><input type="text" class="form-control" id="remarkenglish"></p>
     <p><span>  Remark(hindi)</span><input type="text" class="form-control" id="remarkhindi" ></p>
     <p><span>  Remark(Marathi)</span><input type="text" class="form-control" id="remarkmarathi"></p>
      
      		      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="addremarktomaster()" data-dismiss="h" value="Add">
         
        
      </div>
    </div>

  </div>
</div>  

<!--   31-10-2019  -->
<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>    
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="baselayout1loaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>         
            
                    
          <script type="text/javascript" src="https://www.google.com/jsapi">
    </script>
   <%--  <script type="text/javascript">
 
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
            control.makeTransliteratable(['priscadvoice']);
        }
        google.setOnLoadCallback(onLoad);
    </script>
           --%>
            
            

              
            <script>
				function startConverting8(element) {
				
				   var abc=element.src.split('/');
				   
				     var right = "cicon/mic_off.png";
				         var left = "cicon/mic.png";
				         element.src = element.bln ? right : left;
				         element.bln = !element.bln;
				         
				       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
				   if(abc[5]=="mic_off.png")
				   {
				    startConvertingpresadvice();
				   }
				   else{
				   //var textvalue=document.getElementById("otnotes").value;
				  // localStorage.setItem("xx",textvalue);
				  // location.reload();
				   }
				   
				     }
				
			/* 	$(document).ready(function() {
					  var $tabs = $('#t_draggable1')
					  $("tbody.t_sortable").sortable({
					    connectWith: ".t_sortable",
					    items: "> tr",
					    appendTo: $tabs,
					    helper:"clone",
					    zIndex: 999990
					  }).disableSelection();
					  
					  var $tab_items = $(".nav-tabs > li", $tabs).droppable({
					    accept: ".t_sortable tr",
					    hoverClass: "ui-state-hover",
					    drop: function( event, ui ) { return false; }
					  });
					});
				
			 */	
				function addremarkinotherlang(){
					$('#addremarkmaster').modal( "show" );
				} 
				function closerem(){
					$('#addremarkmaster').modal( "hide" );
				}
			</script>
            
            
             <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
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
           
           
           
             <script>
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
			   location.reload();
			   }
			    
			     }
             
             
             $(function() {
				  $('.tableheight').slimScroll({
				   		height : '340px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				  $('.templheight').slimScroll({
				   		height : '150px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				  $('.dosaheight').slimScroll({
				   		height : '84px',
				   		size : '15px',
				   		railVisible: true,
				   	 	size: '15px',
						alwaysVisible: true
				  });
 				});
 				  $(function () {
              $('[data-toggle="tooltip"]').tooltip()
          })
          
         
             </script>
             
             <%-- <script type="text/javascript">
             	//Code By Abhi 10-10-2017
				function movetoNext(current, nextFieldID) {
					if (current.value.length >= current.maxLength) {
						document.getElementById(nextFieldID).focus();
					}
				}
             	
             	
             	
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
             
             
             

     