<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO"%>
<%@page import="com.apm.Registration.eu.bi.ClinicDAO"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
<html lang="en">
<head>
    <title>Print Investigation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
   
    <style>
    .dd{
    width: 30%;
    height: 60px;
    }
    
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
    text-indent:1pt !important;
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
    font-size:13px;
    border-right: none !important;
    border-left: none !important;
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
    font-size: 13px !important;
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
.form-group {
    margin-bottom: 0px !important;
}
}
    </style>
    <style>
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 210px;
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

 @media print
   {
     .displaynoneprint{display: none;}
     .printmarginleft{margin-left: 25px;}
     
   /*   #headll{
     position: fixed !important;
     z-index: 1;
      
     }
   
   #lokbody{
  
  		margin-top:260px !important;
        overflow:scroll !important;
      
   } */
   
   }
   
 
</style>
    
</head>
<body>

 <%
    
       String letterhead="letterhead";
    %>
      <%
				LoginInfo loginInfo1 = LoginHelper.getLoginInfo(request);
      String kunalaccess="";
      if(loginInfo1.getIskunal()==1){
    	  kunalaccess="hidden";
      }
		   %>
    
<div class="row details mainheader hidden-print">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
									<img src="dashboardicon/microscope.png" class="img-responsive prescripiconcircle"><h4>View Investigation Report </h4>
								</div>
								
								</div>
</div>
<div class="hidden-print">
								<%-- <ul class="breadcrumb">
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
								</ul> --%>
			</div>							
							
<div class="col-lg-12 col-xs-12 col-md-12 printmarginleft " >
	
	<header>
	
	<div id='headll'>
	<%if(loginInfo1.isAyushman()){ %>
	<div class="row letterheadhgt" >
	<%}else{ %>
	<div class="row letterheadhgt" style="height: 135px;">
	<%} %>
		<div class="col-lg-1 col-md-1"></div>
		<%if(loginInfo1.isKalmegha()){ %>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot" style="height: 200px;">
		<%}else if(loginInfo1.isAyushman()){ %>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot" style="height: 100px;">
		<%}else{ %>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
		<%} %>
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo1.isHidelogoinvst() && !loginInfo1.isAyushman()){ %>
							<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	
	
		
	
	
	<div class="row headw " >
		<div class="col-lg-1 col-md-1"></div>
		<%-- <s:if test="reportType=='Numerical'" > --%>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);font-size: 14px;">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					<div class="col-lg-4 col-md-4 col-xs-4">
					
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4">
						<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<%if(loginInfo1.isLmh()){ %>
								<b class="setticolors">
									<s:if test="jobtitle=='Pathlab'">
										Oral Pathology & Microbiology
									</s:if>
									<s:elseif test="jobtitle=='Pathlab'">
									
									</s:elseif>
								</b> 
							<%} %>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
						
					</div>
				</div> 
	
		
		
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					<div class="col-lg-4 col-md-4 col-xs-4">
					
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4">
						<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<b class="setticolors"><s:property value="labname"/><s:if test="approve_status==0">
                                      			(Provisional)	
                                       	</s:if>    </b> <br>
							
							<b><s:property value="creport"/> </b>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
						<div class="form-group" style="margin-bottom: 0px !important;">
							<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
						</div>
					</div>
				</div>
		</div>
		
		<%if(loginInfo1.getIskunal()==0){ %>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin: 0px;">
			<%if(loginInfo1.getInvestigation_newaccess().equals("0")){ %>
					<label for="inputEmail3" class="control-label">INV.ID<span style="font-weight: normal;">: <s:property value="id"/> </span></label><span>&nbsp; | &nbsp;<%} %>	</span> <label for="inputEmail3" class="control-label">UHID<span class="" style="font-weight: normal;">: <s:property value="abrivationid"/></span></label> <label for="inputEmail3" class="control-label hidden">Lab ID<span class="hidden" style="font-weight: normal;">: <s:property value="id"/></span></label>
				<%if(loginInfo1.getInvestigation_newaccess().equals("0")){ %>	<span>&nbsp; | &nbsp;</span> <label for="inputEmail3" class="control-label">Req.No<span class="" style="font-weight: normal;">: <s:property value="invreq"/></span></label>
			<%} %>
				</div>
				<div class="form-group" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">Patient Name : <span style="font-weight: normal;"><s:property value="fullname"/></span> </label><span class="hidden-print" style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender<span class="hidden-print" style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">Age / Gender <span style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">OPD/IPD<s:if test="daycare">/DayCare</s:if> : 
					<s:if test="ipdid==0">
					   <span style="font-weight: normal;">OPD</span>
					    </label>
					</s:if>
					<s:else>
					<s:if test="daycare"><span style="font-weight: normal;">DayCare</span></s:if>
					<s:else>
					<span style="font-weight: normal;">IPD</span>
					</s:else>
						
						 </label>
						 <span style="font-weight: normal;">&nbsp; | &nbsp;</span>
						 <%if(loginInfo1.getIskunal()==1){ %>
						 <label for="inputEmail3" class="control-label">IPD NO.<span style="font-weight: normal;">: <s:property value="ipdid"/> </span></label>
						<%}else{ %>
						 <label for="inputEmail3" class="control-label">Ward / Bed<span style="font-weight: normal;">: <s:property value="wardname"/> / <s:property value="bedname"/> </span></label>
						<%} %>						
					</s:else>
					
					
				</div>
			</div>
			<%}else{ %>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 " style="padding-left:0px;padding-right:0px;">
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>UHID </label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="abrivationid"/>
			</div>
			</div>
		
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>Patient Name</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="fullname"/>
			</div>
			</div>
			
			
		<%-- 	<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>TP</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="thirdPartyCompanyName"/>
			</div>
			</div> --%>
			
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>Age / Gender</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="ageandgender"/>
			</div>
			</div>
			
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>OPD/IPD</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			: <s:if test="ipdid==0">OPD</s:if><s:else>IPD</s:else>
			</div>
			</div>
			
			<s:if test="ipdid!=0">
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>IPD NO.</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="ipdid"/>
			</div>
			</div>
			</s:if>
			</div>
			<%} %>
			
			
			
			
			
			
			
			<%if(loginInfo1.getIskunal()==0){ %>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin: 0px;">
					
					<label for="inputEmail3" class="control-label">Requested By<span style="font-weight: normal;">: <s:property value="reqdiaryUser"/> (<s:property value="Reqspecialization"/>)</span>
						
					<%if(loginInfo1.getInvestigation_newaccess().equals("0")){ %>
						<span class="hidden-print">
							&nbsp;| &nbsp;
						</span>
						</label>  
						<label for="inputEmail3" class="control-label">Department
							<span style="font-weight: normal;">: <%-- <s:property value="reqspecialization"/> --%>
								<s:property value="department"/>
							</span>
						</label>
					<%} %> 
					
				</div>
				
				<%if(loginInfo1.getIskunal()==0){ %>
				<div class="form-group" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">INV Req.<span style="font-weight: normal;">: <s:property value="collect_date"/></span> <sapn class="">&nbsp; | &nbsp;</span></label> <%} %><label for="inputEmail3" class="control-label">Report On<span style="font-weight: normal;">: 
					<s:if test="approve_status==1">
					<s:property value="complete_date"/>
					</s:if><s:else><s:property value="collect_date"/></s:else></span></label>
				</div>
				<div class="form-group" style="margin: 0px;">
				<label for="inputEmail3" class="control-label">INV REQ ID<span style="font-weight: normal;">: <s:property value="invreq"/></span></label>
				</div>
				<%-- <% if(loginInfo1.isLmh()) { %> --%>
				<div class="form-group" style="margin: 0px;">
				    <%-- <s:if test="tpid!=0">  --%>
				    <s:if test="tptype==12">
					<label for="inputEmail3" class="control-label">Self<span style="font-weight: normal;">: <s:property value="thirdPartyCompanyName"/></span></label>
					</s:if>
					<s:else>
					<label for="inputEmail3" class="control-label">Third Party<span style="font-weight: normal;">: <s:property value="thirdPartyCompanyName"/></span></label>
					</s:else>
					
				</div>
				<%-- <%} %> --%>
			</div>
			<%}else{ %>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 " style="padding-left:0px;padding-right:0px;">
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>Requested By</label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="reqdiaryUser"/>
			</div>
			</div>
			
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
			<label>Report On </label>
			</div>
			<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
			:  <s:property value="update_date"/>
			</div>
			</div>
			
			</div>
			<% }%>
		</div>
		<%-- </s:if>
		<s:else>
                      	
                      
       </s:else> --%>
		
		<div class="col-lg-1 col-md-1"></div>
		
		<img src="emr/img/draft_report.png" class="img-responsive hidden" style="position: absolute;margin-left: auto;margin-right: auto;width: 100%;"></img>
	</div>
	</div>
	</header>

	<div class="row" id='lokbody'>
	<div class="col-lg-1 col-md-1"></div>
	<div class="col-lg-10 col-md-10 minheieght500" style="margin-top: 0px;padding:0px;">
		<s:if test="reportType=='Numerical'" >
        </s:if>
        <s:else>
        	<h4 class="text-left hidden" style="margin-top: 0px;"><s:property value="sectionName"/></h4>
        </s:else>
        <!-- <table class="table" cellspacing="0" width="100%" >
        	<thead>
            	<tr>
                	<th class="printth" style="width:35%;">TEST</th>
                    <th class="printth" style="width:18%;">RESULT/FINDING</th>
                    <th class="printth" style="width:10%; " ><a href="#" onclick="setHideee(this)" style='color: white'>Unit</a></th>
                    <th class="printth" style="width:35%;" onclick="setHideee(this)"><a href="#" onclick="setHideee(this)" style='color: white'>REFERENCE RANGE</a></th>
                </tr>
            </thead>
        -->
        <%String dept=""; %>
        <%int i=0; %>
        <%ArrayList<Master>masterInvstTypeList = (ArrayList<Master>)session.getAttribute("masterInvstTypeList"); %>
        <% for(Master master : masterInvstTypeList){%>
        <div  id="tb<%=i%>">
        <%if(loginInfo1.getClinicUserid().equals("vermanh")){ %>
					<div style="background-color: #fff !important;width: 100%;color: #ea6a4d;font-weight: bold;padding: 0px !important;font-size: 15px; text-align: center;padding-top: 7px!important;padding-bottom: 7px !important;">
					<%=master.getName() %><a href="#" onclick="settohidden(<%=i %>)" style="text-align: center;margin-left: 0px;" class="">[hide]</a>
					</div>
                 	<%--  <td></td>
                  	<td style="background-color: #fff !important;width: 35%;color: #ea6a4d;font-weight: bold;padding: 0px !important;font-size: 15px;"><%=master.getName() %><a href="#" onclick="settohidden(<%=i %>)" style="text-align: center;margin-left: 0px;" class="hidden-print">[hide]</a></td> --%>
                <%}else{if(loginInfo1.isAyushman()){ %>
                
                	<div class="hidden-print" style="background-color: #fff !important;width: 100%;color: #ea6a4d;font-weight: bold;padding: 0px !important;font-size: 15px;text-align: center;">
						<%=master.getName() %><a href="#" onclick="settohidden(<%=i %>)" style="text-align: center;margin-left: 0px;" class="hidden-print">[hide]</a>
					</div>
					<%}else{ %>
					<div class="" style="background-color: #fff !important;width: 100%;color: #ea6a4d;font-weight: bold;padding: 0px !important;font-size: 15px;text-align: center;">
						<%=master.getName() %><a href="#" onclick="settohidden(<%=i %>)" style="text-align: center;margin-left: 0px;" class="hidden-print">[hide]</a>
					</div>
                <%}} %>
             <table class="table" cellspacing="0" width="100%" >
        	<thead>
        	<% if(!master.getReporttypex().equals("Text")){%>
            	<tr>
                	<th class="printth" style="width:35%;">TEST</th>
                	<%if(master.getName().equals("WIDAL") && loginInfo1.isParihar()){ %>
                	<th class="invest8">1/40</th>
                                          <th class="invest9">1/80</th>
                                          <th class="invest9">1/160</th>
                                          <th class="invest6">1/320</th>
                	<%}else{ %>
                    <th class="printth" style="width:18%;">RESULT/FINDING</th>
                   
                    <th class="printth" style="width:10%; " ><a href="#" onclick="setHideee(this)" style='color: white'>Unit</a></th>
                    <th class="printth" style="width:35%;" onclick="setHideee(this)"><a href="#" onclick="setHideee(this)" style='color: white'>REFERENCE RANGE</a></th>
                     <%} %>
                </tr>
                <%} %>
            </thead>
       
        
        	  <% if(master.getReporttypex().equals("Numerical")){%>  
                     <tbody> 
                     <%} %>
                
                <%dept=master.getDepartment(); %>
                <% if(master.getReporttypex().equals("Numerical")){%>
                <!-- 	<table class="table" cellspacing="0" width="100%"  onmouseover=""  >   -->
                	
                	<%} %>
                    	<%i=i+1; %>  
                   
                        <% if(master.getReporttypex().equals("Numerical")||master.getReporttypex().equals("Hybreed")){%>
                        	<%for(Investigation investigation : master.getSelectedInvstList()){ %>
                            	<%if(investigation.getObsvalue()!=null){ %>
                                	<%if(!investigation.getObsvalue().equals("0")){ %>
	                                	<tr> 
	                                 		<%--    	<%if(loginInfo1.getIskunal()==1){
	                                    		investigation.setObsvalue(investigation.getKunalobsval());
	                                    		 }%> --%>
	                                    	<td style="width: 36%;"><%=investigation.getInvstname() %></td> 
	                                    	<%if(investigation.getBold()==1) {%>
	                                    		<td style="font-weight: bold;color: red;width: 19%; "><u><%=investigation.getObsvalue() %>  </u></td>
	                                    	<%}else{ %>
	                                    		   <td style="width: 19%;"><%=investigation.getObsvalue() %> </u></td>
	                                    	<%} %>
	                                    	<td style="width: 10%;"> <%=investigation.getInvstUnit() %></td>
	                                    		<!--<td style="text-align: center;"><%=investigation.getFlag()%></td>
	                                    		-->
	                                    	<td><%=investigation.getNormvalue()%></td>
	                                    </tr>
	                                    	
	                                <%} %>
	                                
	                                <%if(investigation.getInvsttype().equals("WIDAL") && loginInfo1.isParihar()){ %>
	                                <tr>
	                                <td><%=investigation.getInvstname()%> :</td>
	                                     <td ><%=investigation.getFinding1()%></td>
	                                     
	                                      <%if(investigation.getFinding2()!=null){ %>
	                                       <td ><%=investigation.getFinding2()%></td>
	                                     
	                                      <%}if(investigation.getFinding3()!=null){ %>
	                                     <td ><%=investigation.getFinding3()%></td>
	                                     
	                                     <%}if(investigation.getFinding4()!=null){ %>
	                                     <td ><%=investigation.getFinding4()%></td>
	                                     <%} %>
	                                       </tr>
	                                    <%} %>
	                             
                                <%} %>
                            <%} %>
						<% }else if(master.getReporttypex().equals("Text")){%>
							<%for(Investigation investigation : master.getSelectedInvstList()){ %>
								<%-- <td><%=investigation.getInvstname()%></td> --%>
								  	<div class="container-fluid"><p><%=investigation.getFindings().toString()%></p></div>
							
							<%} %>
						<%}else{%>
							<%for(Investigation investigation : master.getSelectedInvstList()){ %>
                            	<% if(!investigation.getFindings().equals("")){%>
	                            	<tr><!--
	                                    		 <td > <%=investigation.getInvsttype()%></td> 
	                                    		 -->
	                                	<td style="width: 36%;"><%=investigation.getInvstname()%></td>
	                                    <td><%=investigation.getFindings()%></td>
	                                    <%-- <td class='hidden'><%=investigation.getBiorefrange()%></td> --%>
	                                    <td><%=investigation.getMethods()%></td>
	                                 </tr>
	                             <%} %>
	                        <%} %>
	                     <%} %>
                    </tbody>
                </table>
                
                <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                	<%master.setDescription(DateTimeUtils.removeBreaks(master.getDescription())); %>
                    	<% if(master.getDescription()!=null){%>
                        	<% if(!master.getDescription().equals("")){%>
                            	<%if(master.getReporttypex().equals("Text")){ %>
                            		<b> <p>Impression : 
                            	<%}else{ %>
                            		<b> <p>Remark : 
                            	<%} %>
                            	<span style="font-weight: normal;"> </p></b><%=master.getDescription() %> </span>
                            <%} %>
                        <%} %>
              	</div>
             	
            </div>
            <br>
          <%} %>
                          <%--   <% ArrayList<Master> masterInvstTypeList1 = (ArrayList<Master>)session.getAttribute("masterInvstTypeList"); %>
                          <% if(masterInvstTypeList1!=null){ Master mast= masterInvstTypeList1.get(0);
                            Investigation inv= mast.getSelectedInvstList().get(0); %> 
                            <%if(mast.getName()!=null){ %>
                               <%if(loginInfo1.getClinicUserid().equals("aureus")&&(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")||mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) (Dr. Lalit Raut)"))){%> 
                               
                               <% if(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")){%>
                            	<br><div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                            	
                           	 <p><b>PERIPHERAL SMEAR:</b></p>
                           	 <p><b>RBC's:</b> Preorminnantly Normocytic </p>
                           	 <p>Normochromic</p>
                           	 <p><b>WBC's</b>- As mentioned Below</p>
                           	 <p><b>Platletes </b>- Adequate</p>
                           </div>
                          <%  }} }%>  --%>
           <div class="col-lg-12 col-md-12 col-xs-12">
           		<p class="text-center">--- End of Report ---</p>
           </div>
	</div>
	<div class="col-lg-1 col-md-1"></div>
</div>
                        
                        <div class="row">
                        	<div class="col-lg-1 col-md-1"></div>
                        	<div class="col-lg-10 col-md-10" style="margin-top: 20px;padding-left:10px;">
                        	<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide1()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Footer</a>
							</div>
							
		<%
		String vermahospital="";
		if(loginInfo1.getClinicUserid().equals("vermanh")){
		
			vermahospital="hidden";
		} %>					
		
							
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="newpost1"  style="padding:0px;">
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 <%=kunalaccess %>" style="padding:0px;">
                                 <b class='<%=vermahospital %>' hidden>Sr. Technician</b>
                                 
                                 <div class="form-group <%=vermahospital %>" style="margin-bottom: 3px;">
					<label for="inputEmail3" class="control-label hidden">User ID</label><span hidden>: <s:property value="userid"/> | <script>
  var currentDate = new Date(),
      day = currentDate.getDate(),
      month = currentDate.getMonth() + 1,
      year = currentDate.getFullYear();
  document.write(day + "/" + month + "/" + year)
</script> 
<%if(loginInfo1.getInvestigation_newaccess().equals("0")){ %>
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
<%} %>
</span>
				</div>
                                </div>
                           <%--      <%if(loginInfo1.isAyushman() || loginInfo1.isBalgopal() || loginInfo1.isParihar()){ %> --%>
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:2px;  text-align: center; ">
                                    <form class="form-inline">
                                        <div class="form-group docsig">
                                         <% LoginInfo loginInfo = LoginHelper.getLoginInfo(request); 
                                        
                                          String clinicname=loginInfo.getClinicUserid();
                                          String userid= loginInfo.getUserId();
                                          String image= clinicname+"_"+userid;%>
                                       <br>
                                     	<s:if test="userImageFileName==null">
                                     	
                                     	</s:if>
                                     	<s:else>
                                       <img class="dd" src ="liveData/signature/<s:property value='userImageFileName'/>" alt="" ><br>
                                      	</s:else>
                                      	<%if(loginInfo1.isLmh()){ %> 
                                      	    <s:if test="Jobtitle!='Practitioner'">
                                     	       <b><s:property value="Jobtitle"/></b><br>
                                     	</s:if>
                                     	 <%}else{ %> 
                                     	     <b><s:property value="Jobtitle"/></b><br>
                                      	 <%} %>   
                                        	<b class="hidden-print">Verified By:</b> <b><s:property value="pathLabuser"/></b><br>
                                        	
                                            <b><s:property value="qualification"/></b><br>
                                        <%if(loginInfo1.isLmh()){ %>    
                                            Oral Pathology & Microbiology
                                        <%} %>    
                                            <%if(loginInfo.isParihar()){ %>
                                            <b>(CGMC8125/2018)</b>
                                            <%} %>
                                            <s:if test="printbr==1">
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            </s:if>
                                           
                                        </div>
                                        
                                    </form>
                                </div>
                              <%--   <%} %> --%>
                                <%if(dept==null){
                                	dept="";
                              
                                }   System.out.println(dept);	%>
                                
                               
                            </div>
                        	  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style=" margin-left: -15px;margin-top: 30px">
                         <%if(loginInfo1.isBalgopal()){ %>
                                 <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:2px;  text-align: left;">
                                   <%if(dept.equals("7")){ %>
                                       
                                         <%}else{ %>
                               
                                 <%} %>
                                 </div>
                                  <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:2px;  text-align: right;">
                                  <%if(!dept.equals("7")){ %>
                                  
                            
                                   <%}else{ %>
                                    <!--  <label>Dr. Ravi Bang(Radiologist)</label><br>
                                         <label>MBBS,DMRD</label><br>
                                         <label>Reg. No. 2013/04/1112</label>
                                         <label>(Radiologist)</label> -->
                                             <label>DR. SACHITENDRA MISHRA</label><br>
                                 
                                   <label>DMRD,DNB</label><br>
                                    <span>CONSULTANT RADIOLOGIST</span>
                                   <%} %>      
                                  </div> 
                                   </div>      
                                 <%} %>
                                 
                        </div>
                        
                        	</div>
                        	
                        	<div class="col-lg-1 col-md-1"></div>
                        
                        
                      <s:hidden name="investtemp" id='investtemp'></s:hidden>
                        <div class="row hidden-print">
                        	<div class="col-lg-2 col-md-2">
                        	<%-- <s:select list="allInvsUserList" listKey="id" listValue="name" headerKey="" headerValue="Select Doctor For Digital Sign"
                        	 cssClass="form-control chosen-select" onchange="savesignuser(this)"></s:select> --%>  <!-- //commented because vspm requirement sign as per approve person --> 
                        <%if(loginInfo1.isLmh()){ %> 
                        	<% if(loginInfo.getSuperadminid()==1) {%>
                        	     <!-- for @vspm sign user according to approved by --> 
                                 <s:select list="allInvsUserList" listKey="id" listValue="name" headerKey="" headerValue="Select Doctor For Digital Sign"
                        	                   name='Department' cssClass="form-control chosen-select" onchange="savesignuser(this)"></s:select>			
				           <%}else{ %>
                                 <s:select list="allInvsUserList" listKey="id" listValue="name" headerKey="" headerValue="Select Doctor For Digital Sign"
                        	                   name='Department' disabled="true" cssClass="form-control chosen-select" onchange="savesignuser(this)"></s:select>				
				           <%} %>
				       <%}else{ %>      	
                        	<s:select list="allInvsUserList" listKey="id" listValue="name" headerKey="" headerValue="Select Doctor For Digital Sign"
                        	 cssClass="form-control chosen-select" onchange="savesignuser(this)"></s:select> 
                        	
                        <%} %>	
                        	</div>
                        	<div class="col-lg-9 col-md-9" style="padding:0px;">
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;text-align: right;">
                                <a href="#" onclick="printpage()"  class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Print</a>
                            </div>
                        	</div>
                        	<div class="col-lg-1 col-md-1"></div>
                        </div>

</div>




    <div class="container-fluid hidden">
        <!-- /.row -->
        <div>
			
            <div class="row col-lg-8 col-md-12 col-xs-12 col-sm-12 printmarginleft">

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
    
    function settohidden(id){
    var x=document.getElementById("tb"+id).className;
    var table=document.getElementById("tb"+id);
    table.className=""+x+" hidden";
    }
    
    function setHideee(obj){
    	obj.innerHTML='';
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
    
    
    function savesignuser(obj){
    	var invtid= document.getElementById("investtemp").value;
   
    	var url = "updateusersignCommonnew?invstid="+invtid+"&userid="+obj.value;
 	   
    	if (window.XMLHttpRequest) {
    		req = new XMLHttpRequest();
    	}
    	else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	}   
                   
        req.onreadystatechange = savesignuserRequest;
        req.open("GET", url, true); 
                  
        req.send(null);
    	
    }
    function savesignuserRequest(){
    	if (req.readyState == 4) {
    		if (req.status == 200) {
    			document.location.reload();
    		}
    	}
    }
    
  </script>


</body>

</html>
