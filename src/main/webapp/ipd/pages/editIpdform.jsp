<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%-- <%@page pageEncoding="UTF-8" %> --%>
<%
response.setCharacterEncoding("UTF-8");
%>
<% int in=0; %>
<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <!--   <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /> -->
      <link href="_assets/newtheme/css/main.css" rel="stylesheet" />

    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
  <!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->
   
   <link href="plugin/Selectsearchajax/fm.selectator.jquery.css" rel="stylesheet" type="text/css">
   
   
  
   
   
       <script type="text/javascript" src="ipd/js/admissionform.js"></script>
       <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
       <script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
	<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
	<script type="text/javascript" src="ipd/js/addcharge.js"></script>
  <script>
  
  setInterval(function(){ 
   submitaddmissionFor();
   
    }, 1000*5);
   
  
  </script>
       
    <style>
    .x{
    padding-left: 30px !important;
    padding-right: 30px !important;
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
    	.admissionbackgreen {
		    width: 205px;
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
		.secconbtn{
			    width: 100%;
			    background-color: rgb(238, 248, 231);
			    color: #222222 !important;
			    text-align: left;
			    font-size: 12px;
			    height: 24px !important;
		}
		.tooltip {
    position: relative;
    display: inline-block;
    opacity: 1;
    z-index: 0;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 180px;
    text-align: justify;
    padding: 6px 9px;
    background-color:#555;
    color:#fff;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}

.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 10%;
    margin-left: 37px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1 !important;
}
		
    </style>
    
    
    <script type="text/javascript">
    
	        
	        bkLib.onDomLoaded(function() {
		           
	        	setsecondaryCon();
	        	setrefereddoc()
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 //new nicEditor({maxHeight : 250}).panelInstance('admissiondetails');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('admissiondetails');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('suggestedtrtment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chiefcomplains1');
	        	
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('addmissionfor');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('reimbursment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight :150}).panelInstance('hosp');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('packagename');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('presentillness');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('suggestoper');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('systdepartment');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('fpnotest');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('nauseanotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('fnunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chestpainnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('dimvisionnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('hgunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('hmnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('jointpainnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('specialnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('edemafeetnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('hematurianotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('bmnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('oligurianotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('pstgunotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('ihnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('bmenotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('tnenotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('weaknessnotes');
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('constpationnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('pasthistory');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('personalhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('familyhist');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('onexamination');
	            // new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('smnotes');
	        	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('alergies');
	        	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('earlierinvest');
	        	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('surgicalnotes');
	        	
	            // paditric 
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('developmenthist');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('birthhist');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('emmunizationhist');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('diethist');
	            
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('maternal_history');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('perinatal_history');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('ondiet');
	            new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('physio');


	        	
	        	 $('.nicEdit-panelContain').parent().width('auto');
	        	 $('.nicEdit-panelContain').parent().next().width('auto');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });
	      
	      window.onload = function() {
	    	  var todayDate = new Date().getDate();
	            /*  $("#admissiondate").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true,
					 maxDate: new Date(new Date().setDate(todayDate))
		         }); */
	             //for enable date  
		         $("#admissiondate").datepicker({

		 			dateFormat : 'dd-mm-yy',
		 			yearRange: yearrange,
		 			minDate : '30-12-1880',
		 			changeMonth : true,
		 			changeYear : true
		 			
		         });	
	             $('.classD').each(function() { 
	     			if(this.checked==true){
	     				selected=selected+","+this.value;
	     			}
	     								
	     	    }); 
	             
	      };
	      
	      
</script>

<script>
$(document).ready(function() {
    
	$("#fromDate").datepicker({

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

function printExcel() {

	$(".xlstable").table2excel({
		exclude : ".noExl",
		name : "PRO Patients",
		filename : "PROPatientsReport",
		fileext : ".xls",
		exclude_img : true,
		exclude_links : true,
		exclude_inputs : true
	});
}



</script>
    
    
</head>
<body>

<%

   String hstry="";
   String sysreview="";
   String obstretic_history="";
   String menstrual_history="";
   String substance_history="";
  String paediatrichist="";
  String nicuaccess="";
  boolean iconsts=false;
  String daycareaccess="";
%>
<s:if test="nicuaccess">
<%nicuaccess="hidden"; %>
</s:if>

<s:if test="daycare">
<%daycareaccess="hidden"; %>

</s:if>
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
 
 <s:if test="paediatrichist==true">
            
  </s:if>
  <s:else>
      <% paediatrichist="hidden"; %>
 </s:else>
  
<%  LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>
<%if(loginInfo.getClinicUserid().equals("iconhospital")){
	if(paediatrichist.equals("")){
		iconsts=true;
	}else{
		iconsts=false;
	}

}%>
	

<%-- <s:form action="updateIpd" theme="simple" id="ipdadmissionfrm"> --%>
<s:form action="updateCommonnew" theme="simple" id="ipdadmissionfrm">
<s:hidden name="saveandprintadmission" id="saveandprintadmission"></s:hidden>
 				<s:hidden name="disbedid" id="disbedid"></s:hidden>
 				<s:hidden name="id" id="editselectedid"/>
               <s:hidden id="clientid" name="clientid"/>
               <s:hidden id="cid" name="cid"/>
              <s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
              <s:hidden name="conditionid" id="conditionid"/>
              <input type="hidden" id="invstlistid" />
              
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
.fa-2x {
    font-size: 16px;
    cursor:pointer;
}
.neweditor{
    height: 250px!important;;
}
</style>
         <script type="text/javascript">
         
         function do_resize(textbox) {

        	 var maxrows=5; 
        	  var txt=textbox.value;
        	  var cols=textbox.cols;

        	 var arraytxt=txt.split('\n');
        	  var rows=arraytxt.length; 

        	 for (i=0;i<arraytxt.length;i++) 
        	  rows+=parseInt(arraytxt[i].length/cols);

        	 if (rows>maxrows) textbox.rows=maxrows;
        	  else textbox.rows=rows;
        	 }
         </script>     
    <div class="row">
    	<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen" style="background-color: #43b9be !important">
		<div class="col-lg-6 col-xs-6 col-md-6 text-left" style="padding-left:0px;">
			<!--<h3>Hospital Name</h3>
		-->
		<h3><s:property value="clinicName"/></h3>
		</div>
		<div class="col-lg-6 col-xs-6 col-md-6 text-right" style="padding-right:0px;">
			<h3>Patient Admission Form <small style="color:#fff;">(Edit)</small></h3>
		</div>
	</div>
	
	
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding-top: 5px;padding-bottom: 5px;">
		<span style="color: brown;">IMPORTANT:  Please completed all required field <font color="red">*</font></span>
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
		<h5>PERSONAL AND ADMINISTRATION DETAILS</h5> 
	</div>
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;border-bottom: 1px dashed #ddd;background-color: rgba(245, 245, 245, 0.95);">
		<div class="col-lg-2 col-xs-2 col-md-2" style="background-color: rgba(91, 192, 222, 0.16);padding-top: 15px;">
			<div class="form-group">
                                            <label for="exampleInputEmail1">Patient Name</label><br>
                                           <s:textfield  name="client" id="client" readonly="true" disabled="true" cssClass="form-control" />
                                           <s:textfield  name="address" id="taddress" readonly="true" disabled="true" cssClass="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Father / Guardian Details</label><br>
                                            <s:textfield name="relativename" id="relativename" cssClass="form-control" placeholder="Relative Name" />
                                            <s:textfield name="relation" id="relation" cssClass="form-control" placeholder="Relation" cssStyle="margin-top: 5px;" />
                                            <s:textfield name="relationcont" id="relationcont" cssClass="form-control" placeholder="Contact No" cssStyle="margin-top: 5px;" />
                                        </div>
		</div>
		<input type="hidden" id="iconsts" value="<%=iconsts%>" name="iconsts">
		<div class="col-lg-10 col-md-10 col-xs-6 col-sm-9" style="padding-top:15px;">
                                <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">DOB</label>
                                            <s:textfield cssClass="form-control" id="dob1" disabled="true" name="dob"/>
                                        </div>
                                    </div>
                                		<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">Age/Sex</label>
                                            <s:textfield cssClass="form-control" disabled="true" id="agegender" name="agegender" />
                                        </div>
                                    </div>
            					 <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">UHID</label>
                                          <s:textfield  cssClass="form-control" id="abrivationid" disabled="true" name="abrivationid" />
                                           <s:hidden  cssClass="form-control" id="editclientid" disabled="true" name="editclientid" />
                                            
                                        </div>
                                    </div>
                                    <%
		                            if (loginInfo.isAdmsn_date_edit()) {
		                            %>
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                    	 	<div class="form-group">
                                    	 	<label for="exampleInputEmail1" class="visible-xs visible-sm hidden-md hidden-lg">DOA</label>
                                            <label for="exampleInputEmail1" class="hidden-xs hidden-sm visible-md visible-lg">Date of Admission</label>
                                            <s:textfield cssClass="form-control" id="admissiondate" name="admissiondate" />
                                        </div>
                                    </div>
                                     <%}else{ %>
                                     
                                     <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                    	 	<div class="form-group">
                                    	 	<label for="exampleInputEmail1" class="visible-xs visible-sm hidden-md hidden-lg">DOA</label>
                                            <label for="exampleInputEmail1" class="hidden-xs hidden-sm visible-md visible-lg">Date of Admission</label>
                                            <s:textfield readonly="true" cssClass="form-control" id="admissiondate" name="admissiondate" />
                                        </div>
                                    </div>
                                     
                                   <%} %>  
                                     
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select  name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									     
									  </div>
									</div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                                        <div class="form-group marbot0 hidden">
                                        <label class="" for="exampleInputName2">Barcode</label>
                                             <img src="img/barcode.png" class="img-responsive imgbar"></img>
                                           <s:textfield type="email" cssClass="form-control hidden" id="patientbarcode" name="patientbarcode" />
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
			                        <div class="form-group">
											    <label for="inputEmail" class="control-label"><s:if test="daycare">DC ID</s:if><s:else>IPD ID</s:else></label>
											    <s:hidden id="clientip" name="clientip"></s:hidden>
											  
											   <%if(loginInfo.getIpd_abbr_access()==1){ %>
											   <s:textfield  cssClass="form-control" disabled="true" id="ipdseqno" name="newipdabbr" />
											   <%}else{ %>
											    <s:textfield  cssClass="form-control" disabled="true" id="ipdseqno" name="ipdseqno" />
											    <%} %>
											  </div>
			                        </div>
                                    <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                        			<div class="form-group">
									    <label for="exampleInputName2">Ward</label>
									   <s:select onchange="setBedList(this.value)" list="wardlist" listKey="id" listValue="wardname" 
                                               cssClass="form-control warres" name="wardid" id="wardid" 
                                               headerKey="0" headerValue="Select Ward"/>
									  </div>
                        </div>
                         <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                        			<div class="form-group">
									    <label for="exampleInputEmail2">Bed No</label>
									    <div id="bedlistdiv">
									      <s:select  list="bedlist" listKey="id" listValue="bedname" 
                                                   cssClass="form-control" name="bedid" id="bedid"
                                                   headerKey="0" headerValue="Select Bed"/>
                                                </div>
									  </div>
                        </div>
                        <s:hidden name="origbedid" id="origbedid" />
                               <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                           <div class="form-group">
								     <label for="inputEmail" class="control-label">Payee <font color="red">*</font></label>
								     <s:select id="payee" name="payee" 
										list="#{'s':'Select Payee','0':'Patient','1':'Third Party'}" onchange="settpname(this.value)" cssClass="form-control"></s:select>
								  </div>
                          </div>
                          <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                          	<div class="form-group" id="tpdiv">
								<label for="inputEmail" class="control-label">TP Name</label>
								 <s:select disabled="true" id="tpid" name="tpid" listKey="id" 
													listValue="thirdParty"  headerKey="0" headerValue=""
													list="thirdPartyList" cssClass="form-control"></s:select>
							</div>
                          </div>
                          <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                          		<div class="form-group mar0">
								     <input type="button" class="btn btn-info" onclick="editClientfromIpd()" value="Edit Patient" style="width:100%;"/>
								  </div>
                          </div>
                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                          <div class="form-group">
                        	<label for="inputEmail" class="control-label">MLC Case</label>
                        
                        		 <select id="ddlPassport" name="mlccase"  class="form-control" >
							        <s:if test="mlccase==1"><option value="0">No</option>
							        <option value="1" selected>Yes</option>   </s:if> 
							        <s:else><option value="0">No</option>
							        <option value="1" >Yes</option></s:else>        
							    </select>
							    
                        	</div>
                          </div>
                          
                          
                               <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
                          <div class="form-group">
                                            <label for="inputEmail" class="control-label">MLC No</label>
                                                   <s:textfield cssClass="form-control" id="mlcno"  name="mlcno"/>
                                                   <s:hidden id="mlcabrivationid" name="mlcabrivationid"></s:hidden>
                                                   <br><br>
                                                 <span ><b><s:property value="mlcabrivationid"/></b></span>
                                        </div>
                          </div>
                          
                            <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label">MLC Ref Doctor</label>
								     <s:select id="mlcrefdoctor" name="mlcrefdoctor" list="mlcdrlist" listKey="id" listValue="fullname" headerKey="0" headerValue="Select MLC Dr"  cssClass="form-control"></s:select>
								    <%-- <s:textfield name="mlcrefdoctor"  id="mlcrefdoctor" cssClass="form-control" ></s:textfield> --%>
								  </div>
                        </div> 
                          
                        <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3 hidden">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label">Admission No</label>
								   <s:textfield name="num_admission" disabled="true" id="num_admission" cssClass="form-control" ></s:textfield>
								  </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Primary Consultant</label>
								   <s:select list="userList" listKey="id" listValue="diaryUser" onchange="getEditFormData(this.value)"
                                                cssClass="form-control chosen-select sebaclcons" id="practitionerid" name="practitionerid"
                                                headerKey="0" headerValue="Select Consultant"/>
								  </div>
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        
                        <div class="form-group">
                       <s:hidden name="secndryconsult"  id="secndryconsult"></s:hidden>
								    <label for="inputEmail" class="control-label" style="color: crimson;">Associate Consultants</label>
								    <a href="#" class="btn btn-default sebaclcons" data-toggle="modal" data-target="#secondconsul" style="width: 100%;">Select Consultants</a>
								  </div>
								  
						<div id='ttt'>
						
						</div>	  
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <%-- <div class="form-group">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Referred From</label>
								    <s:select cssClass="form-control chosen-select sebaclcons" id="refferedby" name="refferedby" list="refrenceList" listKey="id" listValue="reference" headerKey="0" headerValue="Select Referred From"/>
								  </div> --%>
								  
								  
				<div class="form-group">
				<s:hidden name="refdocotor"  id="refdoctofid"></s:hidden>
				<input type="hidden" name="referedby" id="referedby">
					 <label for="inputPassword3" class="control-label" style="color: crimson;">Referred From<span class="red">*</span></label>
					 <span><a href="#" data-toggle="modal" data-target="#adddoctorpopup">+Add Doctor</a></span>
					 <div id="otprocedurediv">
<%-- 			           <s:select cssClass="form-control chosen-select sebaclcons" data-target="#referedpat" id="refferedby" name="refferedby" list="refrenceList" listKey="id" listValue="reference" headerKey="0" headerValue="Select Referred From"/>
 --%>		
 			            <a href="#" class="btn btn-default sebaclcons" class="control-label"  data-toggle="modal" data-target="#referedpat" style="width: 100%;">Select Consultants</a>
					</div>
			 </div>
						<div id='ref'>
						
						</div>												
																	
                        </div> 
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group" id="deptDiv">
								    <label for="inputEmail" class="control-label" style="color: crimson;">Speciality</label>
								     <s:select cssClass="form-control chosen-select sebaclcons" id="department" name="department" list="departmentList" headerKey="0" headerValue="Select Speciality"/>
								  </div>
                        </div>  
                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <!-- <div class="form-group" id="deptDiv">
								   <a href="" class="pull-right hidden-xs" data-toggle="modal" data-target="#miic" title="Voice Recorder" Title="Voice Recorder" style="color:#fff;"><i class="btn btn-sm btn-primary" aria-hidden="true">Voice Recorder</i></a>
								  </div> -->
                        </div>  
                                </div>
	</div>
	
	<div class="form-group mar0">
             <input type="button" class="btn btn-info" onclick="getIPDTempData()" value="Get Data"  align="right" style="width:10%;"/>
          </div>
         
                <!-- by lokesh -->
		                         	<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=paediatrichist %>" id="newtablespediatric"> --%>
		                         	 
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " id="newtablespediatric">
		                         	 <div class="">
		                         	 <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 ">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on Birth</label>
		                         	     <s:textfield name="wtonbirth"  placeholder="Weight on Birth" cssClass="form-control" ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on Admission</label>
		                         	     <s:textfield name="wtaddmission" id="wtaddmission" placeholder="Weight on Admission"  cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Head Circumference</label>
		                         	  <s:textfield name="headcircumference"  placeholder="Head Circumference" cssClass="form-control" ></s:textfield>
										
		                         	 </div>
		                        	</div>
		                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Length</label>
		                         	     <s:textfield name="length"  placeholder="Length" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Gestational Age</label>
		                         	     <s:textfield name="gstureage"  placeholder="Gestational Age"  cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	
		                         	<%
		                         	String midarm="hidden";
		                         	if(loginInfo.getClinicUserid().equals("nelson")){
		                         		midarm="";
		                         	} %>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 <%=midarm%>">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"> Mid Arm Circumference</label>
		                         	     <s:textfield name="midarmcircumference"  placeholder="Mid arm Circumference" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                        
		                         	
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 hidden">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight On discharge</label>
		                         	     <s:textfield name="wtdischarge"  placeholder="Weight on discharge" cssClass="form-control" ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	</div>
		                         	</div>
		                         	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad  <%=daycareaccess%>">
                        		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								  <div class="form-group">
								   <label for="inputEmail" class="control-label">Admission For</label>
								  <s:textarea cssClass="form-control neweditor" rows="3" cols="5" id="addmissionfor"  name="addmissionfor"/>
								  </div>
								  <div class="form-group hidden">
								   <label for="inputEmail" class="control-label">Reimbursement</label>
								  <s:textarea cssClass="form-control neweditor" rows="3" cols="5" id="reimbursment" name="reimbursment"/>
								  </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								     <div class="form-group ">
                                            <label for="inputEmail" class="control-label">H/O ALLERGIES</label>
                                          <s:textarea cssClass="form-control neweditor" rows="3" id="alergies" name="alergies" />
                                        </div>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Admission Details</label>
                                                <s:textarea cssClass="form-control neweditor" rows="3" cols="5" name="admissiondetails" id="admissiondetails" />
                                            
                                        </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 <%=nicuaccess%>">
                                <div class="form-group">
                                            <label for="inputEmail" class="control-label">Package</label>
                                          <s:textarea cssClass="form-control neweditor" rows="3" id="packagename" name="packagename" />
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
								   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        
                        </div>
								    <label for="exampleInputName2">CHIEF/PRESENT COMPLAINTS AND REASON FOR ADMISSION</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A subjective statement made by a patient describing the most significant or serious symptoms or signs of illness or dysfunction that caused him or her to seek health care></span></span>
								    <s:select cssClass="form-control chosen-select" list="chief_complaints_list"  listKey="id"  listValue="name" onchange="setChiefComplaints(this.value)" headerKey="0" headerValue="Select Template">
								    </s:select>
								  </div>
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
								  	<input type="text" name="chiefcomplatetempname" class="form-control setbackcolor" placeholder="Enter template Name">  
								  </div>
								   <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'chiefcomplains1')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
								  <!--<div class="form-group">
								    <select class="form-control">
									  <option>Select Day</option>
									  <option>Day 1</option>
									  <option>Day 2</option>
									  <option>Day 3</option>
									  <option>Day 4</option>
									</select>
								  </div>
								  --><!--<button type="submit" class="btn btn-primary">+</button>
								  
								--></div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control neweditor"  rows="50" cols="200" cssStyle="height:300px" name="chiefcomplains" id="chiefcomplains1" />
								</div>
                        	</div>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        		<div class="form-inline">
								  <div class="form-group">
								    <label for="exampleInputName2">HISTORY OF PRESENT ILLNESS</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The chief complaint in medical history taking, a history of the present illness (abbreviated HPI) (termed history of presenting complaint (HPC) refers to a detailed interview prompted by the chief complaint or presenting symptom.</span></span>
								    <s:select list="present_illness_list" cssClass="form-control chosen-select" headerKey="0" onchange="getpresentIllness(this.value)" headerValue="Select Template" listKey="id" listValue="name" >
								    </s:select>
								  </div>
								   <div class="form-group">
								  	<input type="text" name="presentillnesstempname" class="form-control setbackcolor" placeholder="Enter template Name">  
								  </div>
								  <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'presentillness')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
								  <!--<button type="submit" class="btn btn-primary">+</button>
								--></div>
								<div class="form-group" style="margin-top: 10px">
									<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="presentillness" id="presentillness"/>
								</div>
                        	</div>
                        	
                        	
                        
                        </div>
                      <%String oknic="hidden"; %>  
                        <s:if test="nicuaccess">
                        <%oknic=""; %>
                        </s:if>
                     <div class='<%=oknic %>' >
                     
                        <div class="col-lg-12 col-xs-12 col-md-12 textprime ">
							<h5>NICU HISTORY</h5> 
						</div>
						<div class='settopad'>
						<div class="col-lg-12 col-xs-12 col-md-12  " style="padding-top: 10px;">
							<h5><b>MATERNAL HISTORY</b></h5> 
						</div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="form-inline">
                        <div class="form-group"> 
                        <s:select list="maternal_histry_list" onchange="getipdmaternalhistryTemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
						</s:select>
                        </div>
                        <div class="form-group">
								  				<input type="text" name="maternalhisttemp" class="form-control setbackcolor" placeholder="Enter template name">  
						</div>
						<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'maternal_history')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
                        </div> 
                        <div class="form-group" style="margin-top:10px;">
								<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="maternal_history" id="maternal_history"></s:textarea>
						</div>
                        </div>
                       
                       
                        <div class="col-lg-12 col-xs-12 col-md-12  "   style="padding-top: 10px;">
							 <h5><b>PERINATAL HISTORY</b></h5> 
						</div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                         
                         <div class="form-inline">
                        <div class="form-group"> 
                        <s:select list="perintal_hisry_list" onchange="getperinatalHistrTemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
						</s:select>
                        </div>
                        <div class="form-group">
								  				<input type="text" name="perinataltemp" class="form-control setbackcolor" placeholder="Enter template name">  
						</div>
						<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'perinatal_history')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
                        </div> 
                         
                         
                        <div class="form-group" style="margin-top:10px">
								<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="perinatal_history" id="perinatal_history"></s:textarea>
						</div>
                        </div>
                       
                       </div>
                   </div>    
                       
                       <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 textprime">
											    <label for="exampleInputName2">ON EXAMINATION</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Inspection or investigation, especially as a means of diagnosing disease."></span></span>
											   </div> 
                      <div class='settopad'>
                      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                      
	                        					<div class="form-inline">
											  <div class="form-group">
											   
											    <s:select list="on_exam_list" onchange="getonexamtemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="onexaminationtempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'onexamination')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px" >
												<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="onexamination" id="onexamination"></s:textarea>
											</div>
	                       	</div> 
                       </div>
                       <!-- For Bams -->
                     <%if(loginInfo.isBams1()){ %>  
                       <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 textprime">
								<label for="exampleInputName2">ON Diet</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Inspection or investigation, especially as a means of diagnosing disease."></span></span>
						</div>
                       <div class='settopad'>
                      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                      
	                        					<div class="form-inline">
											  <div class="form-group">
											   
											    <s:select list="on_diet_list" onchange="getondiet(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="ondiettempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'ondiet')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px" >
												<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="ondiet" id="ondiet"></s:textarea>
											</div>
	                       	</div> 
                       </div>
                       <%}else{ %>
                       
                       	<input type="hidden" name="ondiet" id="ondiet">  
                       
                       <%} %>
                       
                       
                       <!-- for bms -->
                      <%if(loginInfo.isBams1()){ %>  
                       <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 textprime">
								<label for="exampleInputName2">Physio</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Inspection or investigation, especially as a means of diagnosing disease."></span></span>
						</div>
                       <div class='settopad'>
                      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                      
	                        					<div class="form-inline">
											  <div class="form-group">
											   
											    <s:select list="Physio_list" onchange="getphysio(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="Physiotempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								  			<label>From Date : </label>

								<s:textfield readonly="true" name="fromDate" id="fromDate"
									cssClass="form-control" theme="simple" placeholder="From date"></s:textfield>
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'physio')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px" >
												<s:textarea cssClass="form-control neweditor" rows="9" maxlength="" name="physio" id="physio"></s:textarea>
											</div>
	                       	</div> 
                       </div>
                         <%}else{ %>
                           <input type="hidden" name="physio" id="physio">  
                       <%} %>
                         <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=hstry %>">
							<h5>HISTORY</h5> 
						</div>
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                       			<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">PAST HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A narrative or record of past events and circumstances that are or may be relevant to a patient's current state of health. Informally, an account of past diseases, injuries, treatments, and other strictly medical facts"></span></span>
											     <s:select list="past_history_list" cssClass="form-control chosen-select" onchange="setpasthistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="pasthistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'pasthistory')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											<div class="form-group" style="margin-top:10px;width: 75%">
												<s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="pasthistory" id="pasthistory"></s:textarea>
											</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        					<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">FAMILY HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The family structure and relationships within the family, including information about diseases in family members."></span></span>
											     <s:select list="family_history_list" cssClass="form-control chosen-select" onchange="getFamilyhistory(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div><!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="familyhistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'familyhist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px;width: 75%">
												<s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="familyhist" id="familyhist"></s:textarea>
											</div>
	                        			</div>
	                        			
	                        				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="padding-top: 20px;">
	                        					<div class="form-inline">
													  <div class="form-group">
													    <label for="exampleInputName2">ANY SURGICAL HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical proceduresâand complications, if anyâ that a particular person has had"></span></span>
													  </div>
													  <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'surgicalnotes')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px;width: 75%">
												<s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="surgicalnotes" id="surgicalnotes"></s:textarea>
											</div>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">PERSONAL HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A personal history may include information about allergies, illnesses, surgeries, immunizations, and results of physical exams, tests, and screenings. It may also include information about medicines taken and health habits, such as diet and exercise. Also called personal health record, personal medical history, and PHR."></span></span>
											   <s:select list="personal_hist_list" onchange="getpersonaltemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="personalhistorytempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'personalhist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px;width: 75%">
												<s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="personalhist" id="personalhist"></s:textarea>
											</div>
	                        			</div>
	                        			
	                        		
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="row <%=hstry %>">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">TREATMENT HISTORY</label>
											    <s:select list="on_exam_list" cssClass="form-control chosen-select" onchange="gettreatmenttemp(this.value)" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
								   				 </s:select>
											  </div>
											  <!--<button type="submit" class="btn btn-primary">+</button>
											-->
											<div class="form-group">
								  				<input type="text" name="treatmentgiventempname" class="form-control setbackcolor" placeholder="Enter template name">  
								  			</div>
								  			<div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'suggestedtrtment')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
											</div>
											
											<div class="form-group" style="margin-top:10px;height: 150px">
												<s:textarea cssClass="form-control neweditor" rows="3" name="suggestedtrtment" id="suggestedtrtment"/>
											</div>
	                        			</div>
	                        			
	                        		</div>
	                        	</div>
                       
                       </div>
                        
                        
                      <!-- peditric -->
                      
                           <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=paediatrichist %>">
							<h5>PEDIATRIC HISTORY</h5> 
						</div>
                      
                      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                          <div class="row <%=paediatrichist %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">BIRTH HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A narrative or record of past events and circumstances that are or may be relevant to a patient's current state of health. Informally, an account of past diseases, injuries, treatments, and other strictly medical facts"></span></span>
               
             </div>
             <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'birthhist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
			</div>
           </div>
           <div class="form-group" style="margin-top:10px;padding-right: 150px">
            <s:textarea cssClass="form-control neweditor" rows="6" maxlength="" name="birthhist" id="birthhist"></s:textarea>
           </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 <%=nicuaccess%>">
                              <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DIET HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The family structure and relationships within the family, including information about diseases in family members."></span></span>
              
             </div>
         <div class="form-group">
			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'diethist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
			</div>
           </div>
           
           <div class="form-group" style="margin-top:10px;padding-right: 150px">
            <s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="diethist" id="diethist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                          <div class="row <%=paediatrichist %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 <%=nicuaccess%>">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DEVELOPMENT HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A personal history may include information about allergies, illnesses, surgeries, immunizations, and results of physical exams, tests, and screenings. It may also include information about medicines taken and health habits, such as diet and exercise. Also called personal health record, personal medical history, and PHR."></span></span>
              </div>
         <div class="form-group">
			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'developmenthist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
			</div>
         
           </div>
           
           <div class="form-group" style="margin-top:10px;padding-right: 150px">
            <s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="developmenthist" id="developmenthist"></s:textarea>
           </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              <div class="form-inline">
               <div class="form-group">
                 <label for="exampleInputName2">IMMUNIZATION HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical proceduresâand complications, if anyâ that a particular person has had"></span></span>
               </div>
                  <div class="form-group">
			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'emmunizationhist')" title="Microphone" id="changer" style="width: 4.5%;margin-left: 10px;margin-top: -16px;"></img>
			</div>
           </div>
           
           <div class="form-group" style="margin-top:10px;padding-right: 150px">
            <s:textarea cssClass="form-control neweditor" rows="3" maxlength="" name="emmunizationhist" id="emmunizationhist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                         
                       
                       </div>
                       
                             
                      
                        
                         <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=substance_history %>">
							<h5>SUBSTANCE HISTORY</h5> 
						</div>
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=substance_history %>">
                       			<div class="row">
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
											   	<s:textfield name="drugs"  cssClass="form-control" />
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
											      <s:textfield name="smoking"  cssClass="form-control" />
											  </div>
											 </div>
	                        			</div>
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Tobaco</label>
											   <s:textfield name="tobaconotes"  cssClass="form-control" />
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
                       			<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                        				<div class="">
											  <div class="form-group">
											    <label for="exampleInputName2">Age at menarche</label>
											   	 	<s:textfield name="age_menarche" placeholder="Yrs"  cssClass="form-control" />
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
										<label class=""><s:checkbox  name="nulligravida" /><i></i> Nulligravida</label>
										    <label class=""><s:checkbox  name="current_pregnent" /><i></i> Currently Pregnent</label>
										    <label class=""><s:checkbox  name="iud" /><i></i> IUD</label>
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			<div class="form-group">
											           	<lable>Live Boys</lable>
											           	<s:textfield  name="live_boys" onkeyup="addObsRow(this.value,'Boys')" cssClass="form-control" />
											           </div> 
											           
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			
											           <div class="form-group">
											           	<lable>Live Girls</lable>
											           	<s:textfield  name="live_girls" onkeyup="addObsRow(this.value,'Girls')"  cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
	                        			
											           <div class="form-group">
											           	<lable>Stillbirths</lable>
											           	<s:textfield  name="stillbirths"  onkeyup="addObsRow(this.value,'Stillbirths')" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
											          
											           <div class="form-group">
											           	<lable>Abortions</lable>
											           	  <s:textfield  name="abortions"  onkeyup="addObsRow(this.value,'Abortions')" cssClass="form-control" />
											           </div>   
	                        		</div>
											                   			
	                        		<div class="col-lg-2 col-md-2 col-xs-2">
											           <div class="form-group">
											           	<lable>Death Children</lable>
											           	   <s:textfield name="death_children"  onkeyup="addObsRow(this.value,'Death Children')" cssClass="form-control" />
											           </div> 
	                        		</div>
	                        	</div>
	                        	<div class="row">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
	                        				<table id="obstable" style="background-color: rgba(0, 191, 255, 0.12);">
	                        				   <%int i=0; %>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr>
	                        							<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value="<%=i+1%>" class="form-control"> <input type="hidden" name="obslist[<%=i%>].id" value="<s:property value="id"/>" /></td>
	                        							<td style="width: 8%;padding-right: 15px;"><input type="number" name="obslist[<%=i%>].year" value="<s:property value="year"/>"  class="form-control" placeholder="year"></td>
	                        							<td style="width: 7%;padding-right: 15px;"><s:property value="type"/>  <input type="hidden" name="obslist[<%=i%>].type" value="<s:property value="type"/>" />  </td>
	                        							<td style="width: 13%;padding-right: 15px;"> 
	                        							 
	                        							    <input type="hidden" id="tempD<%=i%>" value="<s:property value="type_delivery"/>" />
		                        							<select name="obslist[<%=i%>].type_delivery" id="tobs_type<%=i%>" class="form-control">
				    												<option value="0">Type of Delivery</option>
				    												<option value="Normal">Normal</option>
				    												<option value="Vaccume">Vaccume</option>
				    												<option value="Forceps">Forceps</option>
				    												<option value="LSCS">LSCS</option>
				    												
															</select>
															<script>
															     var tt=document.getElementById('tempD<%=i%>').value;
	                        							         document.getElementById('tobs_type<%=i%>').value=tt;
	                        							   </script>
															
	                        							</td>
	                        							
													    <td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].indications" value="<s:property value="indications"/>" class="form-control"  placeholder="Indications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].coamplications" value="<s:property value="coamplications"/>" class="form-control" placeholder="Coamplications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].institution" value="<s:property value="institution"/>" class="form-control" placeholder="Institution"></td>	                        							
	                        							<td><a href="" onclick="deleteRow('obstable')" ><i class="fa fa-trash" aria-hidden="true" style="color: #d9534f;"></i></a></td>
	                        						</tr>
	                        						 <%i++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        				<div class="form-group">
	                        					<label>Parity/Abortions Notes</label>
	                        					<s:textarea cssClass="form-control neweditor" theme="simple" name="parity_abortion_notes" rows="3" />
	                        				</div>
	                        			</div>
	                        			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<label>P</label>
			                        					<s:textfield name="p" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<label>L</label>
			                        					<s:textfield name="l" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<label>A</label>
			                        					<s:textfield name="a" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        					<div class="col-lg-3 col-md-3 col-xs-3">
	                        						<div class="form-group">
			                        					<label>D</label>
			                        					<s:textfield name="d" onkeyup="onlyNum(this)" cssClass="form-control" />
			                        				</div>
	                        					</div>
	                        			</div>
	                        		</div>
	                        	</div>
                       		</div> 
                        
                       
                        
                         <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=sysreview %>">
							<h5><a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="color:white;">SYSTEMIC REVIEW &nbsp;&nbsp;<i class="fa fa-chevron-down"></i></a></h5> 
						</div>
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=sysreview%> ">
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
									        <td><label for="exampleInputName2">Headache</label></td>
									        <td><s:select name="hgucondition" id="hgucondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="hgunotes" id="hgunotes"></s:textarea></td>
									      </tr>
									      <!--  27 July 2018  -->
									      <tr>
									        <td><label for="exampleInputName2">Giddiness</label></td>
									        <td><s:select name="giddinesscondition" id="giddinesscondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="giddinessnotes" id="giddinessnotes"></s:textarea></td>
									      </tr>
									      <tr>
									        <td><label for="exampleInputName2">Unconsciousness</label></td>
									        <td><s:select name="unconcondition" id="unconcondition" list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
									        <td><s:textarea cssClass="form-control textareaheight" rows="3" placeholder="Note" cols="5" name="unconnotes" id="unconnotes"></s:textarea></td>
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
                        
                        
                        <div class="row settopad">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 <%=daycareaccess%> ">
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        				<div class="form-inline">
											  <div class="form-group">
											    <label for="exampleInputName2">EARLIER INVESTIGATION</label>
											  </div>
											</div>
				 <div class="form-group" style="">
			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'earlierinvest')" title="Microphone" id="changer" style="width: 2.5%;margin-top: -27px !important;margin-left: 180px;"></img>
			</div>
											<div class="form-group" style="margin-top:10px">
												<s:textarea cssClass="form-control neweditor" rows="3" name="earlierinvest" id="earlierinvest"/>
											</div>
	                        			</div>
	                        			
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							Treatment Advice&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									written direction for the preparation, compounding, and
									administration of a medicine i.e. prescribed Medicine</span></span>
						</h5>
					</div>

<div class='settopad'>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="form-inline"
							style="margin-bottom: 5px; margin-top: 5px;">
							<a style="cursor: pointer" class="btn btn-info"
								onclick="onAdd(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add</a>
							<!-- <a style="cursor:pointer" class="btn btn-info" onclick=editdischargeprisc('<s:property value="parentpriscid"/>','<s:property value="clientid"/>','<s:property value="id"/>','<s:property value="practitionerid"/>','<s:property value="conditionid"/>') style="cursor: pointer;">Edit</a> -->
							<%-- <s:hidden id="admi_disc_ipd_prisclist"></s:hidden> --%>
						</div>

						<div class="form-group">
							<table class="table table-bordered" id="priscTable">
								<thead>
									<tr class="headings">
										<th style="width: 5%;">Sr.No</th>
										<th class="uppercaseirf">Medicine</th>
										<th>Dosage</th>
										<th>Days</th>
										<th>Notes</th>
										<th>Strength</th>
										<th>Remark</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="dischargedataid">
									<s:iterator value="admissionPriscList">
										<tr>
											<td><input type="number" class="form-control"
												name="dicpriscmed<s:property value="id"/>"
												value="<s:property value="dispriscsrno"/>"></td>
											<td><s:property value="mdicinenametxt" /></td>
											<td>
												<select id="discpriscdose<s:property value="id"/>" name="discpriscdose<s:property value="id"/>" class="form-control chosen-select">
													<s:iterator value="dosageList">
														<s:if test="name==priscdose">
															<option value="<s:property value="name" />" selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property value="name" /></option>
														</s:else>
													</s:iterator>
												</select>
												<%-- <s:property value="priscdose" /> --%>
											</td>
											<td>
												<input type="number" class="form-control"
												name="dicpriscdays<s:property value="id"/>"
												value="<s:property value="priscdays"/>">
												<%-- <s:property value="priscdays" /> <s:property value="priscdurationtype" /> --%>
											</td>
											<td><s:property value="dosenotes" /></td>
											<td><s:property value="strengthnew" /> </td>
											<td><s:property value="priscindivisualremark" /> </td>
											<td><a
												onclick="removeMedicineDisc(this,<s:property value="id"/>)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</s:iterator>
									<s:hidden name="totalchildmedids"></s:hidden>
								</tbody>
							</table>
						</div>
						<div id="priscnotes">
							<s:property value="advoice" />
						</div>
					</div>

</div>
					<br>
	                        	
	                        	
	                        	 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>PROVISIONAL DIAGNOSIS</h5> 
						</div>
	                        	
	                       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                
                               <table class="table" width="100%" >
                                     <thead> 
                                        <tr> 
                                         <th width="50%"><b>PROVISIONAL DIAGNOSIS</b>&nbsp; <input type="button" value="Add New" class="btn btn-info hidden" onclick="dispDIv()" /></th> 
                                         <th width="50%"></th>
                                          <th class='hidden' ><div id="viewtp" ><b>Select Treatment Episode</b>&nbsp; <a type="button" onclick="createTreatmentEpisode()" class="btn btn-info">+</a></div></th> 
                                         </tr> 
                                        </thead> 
                                        
                                        
                                    <tbody> 
                                        <tr> 
                                       
                                        <td><textarea  onkeyup="searchdiagnosisproJSON(this.value)" id="newdiagnosis" class="form-control" placeholder="type diagnosis here" style="height: 150px !important" ></textarea></td>
                                          <td>   <input type="button" value="Save" onclick="savediagnosisfastJSON()" class="btn btn-info" /> </td>
                                         <td id="treatmentepisodeajax" hidden> 
                                          <s:select name="treatmentEpisode"  id="treatmentEpisode" list="treatmentEpisodeList" 
                                         	listKey="id" listValue="treatmentEpisodeName"  
                                         	cssClass="form-control chosen-select"/>
                                         </td>
                                        
                                        <td class='hidden'><i onclick="createTreatmentEpisode()"  style="cursor: pointer;" title="Add Treatment Episode" class="fa fa-plus-square fa-2x aadpres hidden"></i></td>
                                        
                                        
                                        </tr> 
                                        <tr id="dispid" class="hidden">
                                         <td>
                                           <input type="text" class="form-control" placeholder="Enter New Diagnosis" id="newcondition" style="border: 1px solid #ddd;margin-top: 8px;width: 49%;">
                                                         <input type="text" class="form-control" placeholder="Enter ICD Code" id="icdcode" style="border: 1px solid #ddd;margin-top: 8px;width: 50%;">
                                            
                                        </td> 
                                        <td><input type="button" onclick="addnewCOndition(<%=i-1%>)" class="btn btn-sm btn-info" style="margin-top: 7px;" value="Add New"/></td>
                                        <td></td>
                                        </tr>
                                        	
                                        </tbody> 
                                    </table>
                                    
                                    <% ArrayList<Bed>ipdConditionids = (ArrayList<Bed>)session.getAttribute("ipdConditionids");%>
                                    <table id="searchDiagnosis" class="table"  style="height:200px;display:block;overflow:scroll;width: 50%";>
                                    
                                          <%i=0; %>
                                          <%for(Bed bed:ipdConditionids){ %>
                                          <tr>
                                            <td><input type="checkbox" class="classD" onclick="reoveThisRow(this,'<%=bed.getConditionid()%>')"  value="<%=bed.getConditionid() %>" checked='checked' />
                                              <input type='hidden' value="<%=bed.getConditionid() %>" name='conditions[<%=i%>].conditionid'  />
                                            </td>
                                             <td><%=bed.getConditionname()%></td>
                                             <td class="hidden"><a onclick="reoveThisRow('<%=bed.getConditionid()%>')"><i class='fa fa-trash-o'></i></a></td>
                                         </tr>
                                         <!--  
                                         <tr>
                                            <td><input type="checkbox" class="" /></td>
                                             <td>CSS</td>
                                         </tr> -->
                                         <% i++; } %>
                                    </table>
                                    
                                   <!--  <table id="searchDiagnosis" class="table"  style="width: 30%">
                                    
                                    </table> -->
                                    
                    			</div>
	                        </div>
	                        <div class="col-lg-12 col-md-12" style="margin-top: 15px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12">
		                                     <!--<input type="button" class="btn btn-primary savebtn savebigbtn" value="Print" onclick="showipdprintpoup()">
                                			 -->
                                			 <!--   said by praful sir 15 mar 2018 dont check treatment episode -->
                                			 <!-- <input type="button" class="btn btn-primary savebtn savebigbtn" value="Update" onclick="checkTreatmentEpisodeStatusAjax()"> -->
		                                	<!-- <input type="button" class="btn btn-primary savebtn savebigbtn" value="Update" onclick="savevalidate()"> -->
		                                	<input type="button" class="btn btn-primary savebtn savebigbtn" value="Update and Print" onclick="updatealreadyadmit(1)">
		                                	<input type="button" class="btn btn-primary savebtn savebigbtn" value="Update" onclick="updatealreadyadmit(0)">
		                                </div>
		                            </div>
		                        </div>
    
    
    </div>          
              
              
              
              
              
              
   

                <div class="panel panel-primary hidden">
                    
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad bordertopgreen backgrey">
                        
                        
                        <div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
                        <div class="form-group">
                        
                             
								     <label for="inputEmail" class="control-label">Associate Consultant</label><!--
								   <s:select list="userList" listKey="id" listValue="diaryUser" 
                                               cssClass="form-control chosen-select"  name="secndryconsult" id="secndryconsult" 
                                               headerKey="0" headerValue="Select Consultant"/>
								  -->
									
									<button type="button" style="width: 100%;" class="btn btn-default btn-sm dropdown-toggle hidden" data-toggle="dropdown">Associate Consultant <span class="caret"></span></button>
												<ul class="dropdown-menu hidden">
													<s:iterator value="userList">
													 <s:if test="status==1">
													   <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" checked="checked" class="" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="diaryUser"/></span></a></li>
													 </s:if>
													 <s:else>
													    <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="diaryUser"/></span></a></li>
													 </s:else>
													 
												  </s:iterator>
												 
											</ul>
								  
								  </div>
								  
                        </div>
                        
                        
                        </div>
                        
                        
                        
                        
                        
                         
                        
                         
                        
                        
                        
                        
                        
                        

        </div>
        <!-- /.row -->

    
   

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
					<h4 class="modal-title" id="myModalLabel">Create Treatment Episode</h4>
				</div>
				<div class="modal-body">
					<%@ include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary"
						onclick="saveTreatment()">Save</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Second Consultant -->
<div id="secondconsul" class="modal fade" role="dialog">
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
		                    <s:if test="status==1">
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" checked="checked" value="<s:property value="id"/>" class="doctors" onclick="setsecondaryCon()"><i></i> <span id='lok<s:property value="id"/>' style="padding-left:20px;"> <s:property value="diaryUser"/></span></label></li>
		                   </s:if>
		                   <s:else>
		                     <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" value="<s:property value="id"/>" class="doctors" onclick="setsecondaryCon()"><i></i>  <span id='lok<s:property value="id"/>' style="padding-left:20px;"><s:property value="diaryUser"/></span></label></li>
		                   </s:else>
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
		               <input id="refsearch" name="refsearch" class="form-control" placeholder="search here" type="text" data-list=".default_list" autocomplete="off">
		               <div class="refuserlist">
		               <ul class="vertical default_list" id="refallprod">
		                 <%-- <s:iterator value="RefrencedoctorList">
		                    <s:if test="status=='1'">
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" checked="checked" value="<s:property value="id"/>" class="refdoctors" onclick="setrefereddoc()"><i></i> <span id='reflok<s:property value="id"/>' style="padding-left:20px;"> <s:property value="reference"/></span></label></li>
		                   </s:if>
		                   <s:else>
		                     <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" value="<s:property value="id"/>" class="refdoctors" onclick="setrefereddoc()"><i></i>  <span id='reflok<s:property value="id"/>' style="padding-left:20px;"><s:property value="reference"/></span></label></li>
		                   </s:else>
		                 </s:iterator> --%>
		                 
		                 <s:iterator value="RefrencedoctorList">
		                     <s:if test="RefStatus==1">
		                  <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" checked="checked" value="<s:property value="id"/>" class="refdoctors" onclick="setrefereddoc()"><i></i> <span id='reflok<s:property value="id"/>' style="padding-left:20px;"> <s:property value="reference"/></span></label></li>
		                  </s:if>
		                   <s:else>
		                     <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" value="<s:property value="id"/>" class="refdoctors" onclick="setrefereddoc()"><i></i>  <span id='reflok<s:property value="id"/>' style="padding-left:20px;"><s:property value="reference"/></span></label></li>
		                   </s:else>
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
	
	
	
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
								     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-6" style="padding-left:0px;">
								     <div class="form-group">
                                            <label for="inputEmail" class="control-label">Hospital/Clinic</label>
                                                <s:textarea cssClass="form-control neweditor" rows="3" id="hosp" name="hosp" />
                                            
                                        </div>
                                </div>
                            </div>
    
    
    <div class="modal fade" id="priscriptionpopup" tabindex="-1"
		role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="priscmyModalLabel">
						Create Prescription For <b class="pname">NAME: </b>
					</h5>
				</div>
				<div class="modal-body">


					<%@ include file="/diarymanagement/pages/addpriscription.jsp"%>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						onclick="saveTemplae(0)">Save Template</button>
					<button type="button" class="btn btn-primary"
						onclick="insertAdmissionPriscription(0)" id="editipdformsavepricbtn">Save</button>
					<button type="button" class="btn btn-primary"
						onclick="insertAdmissionPriscription(1)" id="editipdformsaveprintpricbtn">Save & Print</button>

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
     <!-- Voice recorder  -->
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
												<s:textarea cssClass="form-control neweditor" rows="3" name="voice" id="voicemic"/>
											
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
 </div>
 
 <%-- <div id="adddoctorpopup" class="modal fade" role="dialog" style="z-index: 11000">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
        <h4 class="modal-title">Add Doctor</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding-top: 9px;">
        			<div class="form-group">
        			<label>Doctor Name</label><label class="text-danger">*</label>
					<s:textfield id="adddoctor"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						 placeholder="Enter Doctor Name"
						onkeyup="initialCap(this)" />
        			</div>
        	</div>
        </div>
        
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding-top: 9px;">
        			<div class="form-group">
        			<label>Share(%)</label><label class="text-danger">*</label>
					<s:textfield id="share"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						 placeholder="Enter Share"
						onkeyup="initialCap(this)" />
        			</div>
        	</div>
        </div>
        
     </div>
     
     <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="savenewdoctor()" style="margin-top: 20px;">Save</button>
     </div>
    </div>

  </div>
</div>   --%>

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
	  $('.refuserlist').slimScroll({
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
  
<script>
	var patientId = 0;
	var diaryuserId = 0;
	var editcondition_id = 0;
	function onAdd(cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		removeSession();
		repeatePriscDateAjax(cid,pid,conid);
		
	}
	
	function editviewparentprisc(cid,pid,conid,id){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		editparentpriscid = id;
		//editparentprisc(id);
		document.getElementById("repeatdate").value="0";
		
		repeateEditPriscDateAjax(cid,pid,conid);
		
	}
	
</script>
  
 <script>
  function voicerecord() {
	  $('#miic').modal( "show" );	
}
  
      function startConverting155(element,id) {

		   var abc=element.src.split('/');	
		   
		     var right = "cicon/mic_off.png";
		         var left = "cicon/mic.png";
		         element.src = element.bln ? right : left;
		         element.bln = !element.bln;
		         
		       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
		   if(abc[5]=="mic_off.png")
		   {
		    startConvertingadvicepres45(id);
		   }
		   else{
		   //var textvalue=document.getElementById("otnotes").value;
		  // localStorage.setItem("xx",textvalue);
		   
		   }
		     }
    
	
	</script> 
  <script type="text/javascript">
 
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
    </script>
<script>
  function startConvertingadvicepres45(id){
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
		nicEditors.findEditor(''+id).setContent(vtxt);
		//document.getElementById('voicemic').value=vtxt;
		
		
		
	};

}
  
  </script>
  <script type="text/javascript">
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

			</script>
</html>
