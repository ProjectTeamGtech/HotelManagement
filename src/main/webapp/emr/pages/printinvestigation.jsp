<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Print Investigation</title>
    <link rel="stylesheet" href="_assets/newtheme/css/main.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
  
    <style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
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
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #43b9be;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #43b9be;
}
  .panel-primary {
      border-color: #43b9be;
  }
  .padsign{
   padding-top: 40px;
  }
  .help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
    color: #737373;
}
h3, .h3 {
    font-size: 16px;
    font-weight: bold;
    color: #6699cc;
    margin-top: 0px;
    margin-bottom: 5px;
}
.form-group {
    margin-bottom: 4px !important;
}
p {
    margin: 0 0 5.5px !important;
}
.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 5px;
}
.settopbac {
    background-color: #ddd;
}
.totalbor {
    background-color: #f5f5f5;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}

@media screen {
  div.divFooter {
    display: none;
  }
}
@media print {
  div.divFooter {
    position: fixed;
    bottom: 0;
  }
}
 @media print
{

.printth {
    background-color: #efefef !important;
    color: #000 !important;
}

.minheieght500{
	    min-height: 0px !important;
}
.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}
.table>tbody>tr>td {
    padding: 2px 2px 2px 2px !important;
    font-size: 10px !important;
}
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
    	margin-bottom: 0px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 4px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 12px;
}
.wordscolr{
	    color: #d07878 !important;
    text-transform: uppercase;
}

.titleset {
    margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    line-height: 20px;
}
h4, .h4 {
    font-size: 12px;
}

.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	font-size:16px !important;
	color: firebrick !important;
}

.titleset {
    margin: 0px;
    color: #6699cc !important;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000 !important;
    
}
.setotas{
	 padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px !important;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
}
.table>thead>tr>th{
	font-size: 11px !important;
}
.cliqualif {
    font-size: 10px !important;
    font-weight: bold;
}
.clicniaddress {
    font-size: 10px !important;
    font-weight: bold;
}
 /* #myfooter
            {
                position: fixed;
                bottom: 0;
            }
            #myheader
            {
                position: fixed;
                top: 0;
            } */
}
    </style>
    <style>
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 135px;
}
.clinicname {
    font-size: 20px;
    font-weight: bold;
}
.clicniaddress {
    font-size: 12px;
    font-weight: bold;
}
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
}
label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: bold;
}
td, th {
    padding: 0px 3px 0px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	font-size:16px;
	color: firebrick;
}
.setotas{
	  padding: 0px 6px 4px 0px;
    text-align: right;
    color: green;
    font-size: 12px;
}
p {
    margin: 0 0 2.5px !important;
    font-size: 12px !important;
}
.table>thead>tr>th{
	font-size: 12px !important;
}





</style>
    
    

    
    
    
    
</head>
<body oncontextmenu="return false;">

 <%
    
       String letterhead="letterhead";
    %>
    	 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
   
    <div class="col-lg-12 col-md-12 col-xs-12">
    	<div class="col-lg-1 col-md-1"></div>
    	<div class="col-lg-10 col-md-10">
    		<div id="myheader">
    		
	           	 <div class="row letterheadhgt" >
	    			<div id="newpost" class="">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogoinvst()){ %>
							<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
		</div>
	    		</div>
	    		<div class="clearfix"></div>
	    		<div class="row printinvestigationbackcolor">
    				<%-- <s:if test="reportType=='Numerical'" > --%>
		<div class="backcolor">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
			<div class="hidden-print">
								<ul class="breadcrumb">
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div>
							
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<%-- <b class="setticolors"><s:property value="invsttype"/></b> --%>
								<b class="setticolors">Lab Test Report <s:if test="approve_status==0">
                                       			(Provisional)	
                                        	</s:if></b>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
					
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left">
				<div class="form-group" style="margin-bottom: 3px;">
				<%if(loginInfo.getInvestigation_newaccess().equals("0")){ %>
					<label for="inputEmail3" class="control-label">INV.ID<span style="font-weight: normal;">: <s:property value="id"/> </span></label><span>&nbsp; | &nbsp;	<%} %></span> <label for="inputEmail3" class="control-label">UHID<span class="" style="font-weight: normal;">: <s:property value="abrivationid"/></span></label><%if(loginInfo.getInvestigation_newaccess().equals("0")){ %> <label for="inputEmail3" class="control-label hidden">Lab ID<span class="hidden" style="font-weight: normal;">: <s:property value="id"/></span></label>
				<%} %>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Patient Name : <span style="font-weight: normal;"><s:property value="fullname"/></span> </label><span class="hidden-print" style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender<span class="hidden-print" style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Age / Gender <span style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">OPD/IPD<s:if test="daycare">/DayCare</s:if> :  </label>
					<s:if test="ipdid==0">
					   <span style="font-weight: normal;">OPD</span>
					</s:if>
					<s:else>
					<s:if test="daycare">
					<span style="font-weight: normal;">DayCare</span>
					</s:if>
					<s:else>
					<span style="font-weight: normal;">IPD</span>
					</s:else>
						
						 <span style="font-weight: normal;">&nbsp; | &nbsp;</span>
						 <%if(loginInfo.getIskunal()==1){ %>
						  <label for="inputEmail3" class="control-label">IPD NO.<span style="font-weight: normal;">: <s:property value="ipdid"/>  </span></label>
						 <%}else{ %>
						  <label for="inputEmail3" class="control-label">Ward / Bed<span style="font-weight: normal;">: <s:property value="wardname"/> / <s:property value="bedname"/> </span></label>
						 <%} %>
						
					</s:else>
				</div>
			</div>
				
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
			<div class="form-group" style="margin-bottom: 3px;">
			<%if(loginInfo.getInvestigation_newaccess().equals("0")){ %>
					<label for="inputEmail3" class="control-label">
						Requested By
						<span style="font-weight: normal;">: 
							<s:property value="practitionerName"/>
							<%-- /<s:property value="reqdiaryUser"/> --%>
						</span> 
						<span class="hidden-print">&nbsp; | &nbsp;</span>
					</label> 
					<label for="inputEmail3" class="control-label">
						Department
						<span style="font-weight: normal;">: 
							<s:property value="department"/>
						</span>
					</label>
			<%} %>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Sample On<span style="font-weight: normal;">: (<s:property value="collect_date"/>)</span></label><span style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label">Report On<span style="font-weight: normal;">: <s:property value="dateTime"/></span></label>
				</div>
				<div class="form-group hidden-print" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label hidden-print">Sample On<span class="hidden-print" style="font-weight: normal;">: (<s:property value="collect_date"/>)</span> <sapn class="hidden-print">&nbsp; | &nbsp;</span></label> <label for="inputEmail3" class="control-label">Report On<span style="font-weight: normal;">: <s:property value="update_date"/></span></label>
				</div>
				<div class="form-group" style="margin-bottom: 3px;">
				    <s:if test="tpid!=0">
					<label for="inputEmail3" class="control-label">Third Party<span style="font-weight: normal;">: (<s:property value="thirdPartyCompanyName"/>)</span></label>
					</s:if>
				</div>
				
			</div>
	<%-- 	</s:if>
		<s:else>
       </s:else> --%>
    		</div>
	    		
        	</div>
       
	       
    		
    		<div class="row">
    			<div class="minheieght500" style="margin-top: 0px;padding:0px;">
		<s:if test="reportType=='Numerical'" >
		<h4 class="text-left" style="margin-top: 0px;margin-top: 5px;margin-bottom: 5PX;color: #ea8d19;font-weight: bold;font-size: 13px;"><s:property value="invsttype"/></h4>
                         	 </s:if>
                         	 <s:else>
                         	 	<h4 class="text-left" style="margin-top: 0px;margin-top: 5px;margin-bottom: 5PX;color: #ea8d19;font-weight: bold;font-size: 13px;"><s:property value="invsttype"/></h4>
                         	 </s:else>
                         	
                            <table class="table" cellspacing="0" width="100%" style="border-bottom: 1px solid #000;" id="example">
                                <thead>
                                    <tr>
                                    	<s:if test="reportType=='Numerical'" >
	                                        <!--<th class="invest27">Investigation</th>
	                                        --><th class="printth">TEST</th>
	                                        <th class="printth">RESULT/FINDING</th><!--
	                                        <th class="invest6">Flag</th>
	                                        --><th class="printth">REFERENCE RANGE</th>
                                       </s:if>
                                       	<s:elseif test="reportType=='Hybreed'" >
	                                        <!--<th class="invest27">Investigation</th>
	                                        --><th class="printth">TEST</th>
	                                        <th class="printth">RESULT/FINDING</th>
	                                        <th>Unit</th>
	                                        <!--
	                                        <th class="invest6">Flag</th>
	                                        
	                                        --><th class="printth">REFERENCE RANGE</th>
                                       </s:elseif>
                                       <s:elseif test="reportType=='Text'" >
                                              <th class="printth">TEST</th>
	                                          <th class="printth">FINDINGS</th>
                                       </s:elseif>
                                       <s:else>
                                          <!--
                                       		  <th class="invest27">Investigation</th>
                                       		  --><th class="printth">TEST</th>
	                                          <th class="printth">FINDINGS</th>
	                                          <th class="printth" style="display:none;">BIOLOGICAL REF. RANGE</th>
	                                          <th class="printth">METHOD</th>
                                       </s:else>
                                    </tr>
                                </thead>
                                <tbody>
                                   	<s:if test="reportType=='Numerical'" >
                                    <s:iterator value="selectedInvstList">
                                    	<s:if test="obsvalue>0">
                                    		<s:if test="obsvalue!=''">
	                                    	<tr> 
	                                    		<td><s:property value="invstname"/></td>
	                                    		<s:if test="bold==1">
	                                    		  <td style="font-weight: bold;color: red; "><s:property value="obsvalue"/> <s:property value="invstUnit"/></td>
	                                    		</s:if>
	                                    		<s:else>
	                                    		   <td ><s:property value="obsvalue"/> <s:property value="invstUnit"/></td>
	                                    		</s:else>
	                                    		
	                                    		
	                                    		<!--<td style="text-align: center;"><s:property value="flag"/></td>
	                                    		
	                                    		--><td><s:property value="normvalue"/></td>
	                                    	
	                                    	</tr>
	                                    	
	                                    	</s:if>
                                    	</s:if>
                                    </s:iterator> 
								</s:if>
								 	<s:elseif test="reportType=='Hybreed'" >
                                    <s:iterator value="selectedInvstList">
                                    	
                                    		<s:if test="obsvalue!=''">
	                                    	<tr> 
	                                    		<td style="font-weight: bold;"><s:property value="invstname"/></td>
	                                    		<s:if test="bold==1">
	                                    		  <td style="font-weight: bold;color: red; "><s:property value="obsvalue"/> </td>
	                                    		</s:if>
	                                    		<s:else>
	                                    		   <td><span  style="font-weight: bold;"><s:property value="obsvalue"/></span></td>
	                                    		</s:else>
	                                    		
	                                    		<td> &nbsp;<span ><s:property value="invstUnit"/></span></td>
	                                    		<!--<td style="text-align: center;"><s:property value="flag"/></td>
	                                    		
	                                    		--><td style="font-weight: bold;"><s:property value="normvalue"/></td>
	                                    	
	                                    	</tr>
	                                    	
	                                    	</s:if>
                                    	
                                    </s:iterator> 
								</s:elseif>
								<s:elseif test="reportType=='Text'">
								   <s:iterator value="selectedInvstList">
								       <tr>
								       <td><s:property value="invstname"/></td>
								       <td><s:property value="findings"/></td>
								       </tr>
								   </s:iterator>
								</s:elseif>
								  <s:else>
								  		 <s:iterator value="selectedInvstList">
                                    
                                    		<s:if test="findings!=''">
	                                    	<tr><!--
	                                    		 <td><s:property value="invsttype"/></td> 
	                                    		 --><td><s:property value="invstname"/></td>
	                                    		<td><s:property value="findings"/></td>
	                                    		<td style="display:none;"><s:property value="biorefrange"/></td>
	                                    		
	                                    		<td><s:property value="methods"/></td>
	                                    		
	                                    		
	                                    	
	                                    	</tr>
	                                    	</s:if>
	                                    </s:iterator> 
	                                       
                                  </s:else>
                                    
                                </tbody>
                            </table>
                            <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                           
                            	<%--  <b>Remark : <span style="font-weight: normal;"> remark comes here</span></b> --%>
                            </div>
                          
                            <div class="col-lg-12 col-md-12 col-xs-12">
                            	<p class="text-center">--- End of Report ---</p>
                            </div>
                            
	</div>
	</div>
	
				
			<div class="row">
			
	           <div class="" style="padding:0px;">
	           				<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide1()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Footer</a>
							</div>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="newpost1"  style="padding:0px;">
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
                                
                                 <div class="form-group" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label">Printed by</label><span>:<%=loginInfo.getFirstName() +" "+loginInfo.getLastName() %> | <script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script> 
&nbsp;|&nbsp;
<script>
	var currentTime = new Date(),
      hours = currentTime.getHours(),
      minutes = currentTime.getMinutes();

	if (minutes < 10) {
	 minutes = "0" + minutes;
  }

	var suffix = "AM";
	if (hours >= 12) {
    suffix = "PM";
    hours = hours - 12;
	}
	if (hours == 0) {
	 hours = 12;
	}

	document.write(hours + ":" + minutes + " " + suffix)
</script>
</span>
				</div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:2px; text-align: right;">
                                    <form class="form-inline">
                                        <div class="form-group docsig">
                                        <%if(loginInfo.getInvestigation_newaccess().equals("0")){ %>
                                            <label>Verified By: <s:property value="pathLabuser"/></label> <br>
                                            <label><s:property value="qualification"/></label>
                                         <%}else{%>
                                         <%if(loginInfo.getJobTitle().equals("Radiologist")){ %>
                                         <label>Dr. Ravi Bang(Radiologist)</label><br>
                                         <label>MBBS,DMRD</label><br>
                                         <label>Reg. No. 2013/04/1112</label>
                                         <%}else{ %>
                                           <label>Dr. Dilip Wankar</label><br>
                                         <label>MBBS,MD Patho.</label><br>
                                         <label>Reg. No. 39971</label>
                                           <%} %>   
                                         <%} %>   
                                        </div>
                                    </form>
                                </div>
                                
                            </div>
                        	
                        	</div>
	           
	          
	        </div>
				
	
	
	<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;text-align: right;">
                                <a href="#" onclick="printpage()"  class="btn btn-primary savebtn savebigbtn hidden-print " style="line-height: 45px;" >Print</a>
                            </div>
	
	</div>
    		
    		
    		</div>
    	</div>
    	<div class="col-lg-1 col-md-1"></div>
    </div>
    
    
    
    
    





    <div class="container-fluid hidden">
        <!-- /.row -->
        <div>
			
            <div class="row col-lg-8 col-md-12 col-xs-12 col-sm-12">

                <div class="">
                    <div class="panel-body">
                       
                       
                       
                        
              
                        <s:if test="reportType!='Numerical'" >
     <div class="row hidden-print" >
	   <div class="col-lg-2 col-md-2 col-xs-4 marlft21">
	   		<span style="font-weight:bold">Attachments : </span>
	   </div>
   </div>
    	
   <div class="row hidden-print">
  
   		<s:iterator value="docList">
   			<div class="col-lg-3 col-md-3 col-xs-4 marlft21">
																<p class="marraig"
																	style="margin-bottom: -2px; font-size: 11px;">
																	<s:property value="lastModified" />
																</p>
		  														<a
																href="downloadDocEmr?filename=<s:property value="fileName"/>"
																title="Download" class="font11">
																<s:if test="invstid>0">
																	 <s:property value="invstFoleName" />
																 </s:if>
																 <s:else>
																 	<s:property value="fileName" />
																 </s:else>
															</a>
															</div>
										
   		</s:iterator>
   </div>

   </s:if>

                        
                    </div>
                </div>


            </div>


        </div>
        <!-- /.row -->

    </div>
    
    
    

    <script>
        function myPrint() {
            window.print();
        }
    </script>

 <script>
    function showhide()
     {
           var div = document.getElementById("newpost");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }  
  </script>
  <script>
    function showhide1()
     {
           var div = document.getElementById("newpost1");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }  
  </script>
  
  
  <script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'colvis' ]
    	
    }
    
    
    );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
    
    
   
    $('#example_paginate').addClass('hidden');
    $('.setborba').addClass('hidden-print');
    
     } );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>


  

</body>



</html>
