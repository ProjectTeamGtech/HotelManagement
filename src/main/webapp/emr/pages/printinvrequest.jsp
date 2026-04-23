<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
 <%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
 %>
<html lang="en">
<% LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
<head>
    <title>Print Investigation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
   <link href="https://fonts.googleapis.com/css2?family=Share:wght@700&display=swap" rel="stylesheet">
    <style>
    
    
    .thermal{
        font-family: 'Share' !important;
    line-height: 12px !important;
    font-size: 5px !important;
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
	   font-size: 10px;
	}
	
	.backcolor{
		background-color: rgba(91, 192, 222, 0.16) !important;
	}
	.setticolors{
		border-bottom: 4px double #ddd;
		font-size:11px !important;
		color: firebrick !important;
	}
	.titleset {
	    margin: 0px;
	    color: #6699cc !important;
	    border-bottom: 1px dashed #efefef;
	    font-size: 15px;
	    line-height: 20px;
	}
	.table>thead>tr>th {
	    vertical-align: bottom;
	    border-bottom: transparent;
	    background-color: #ccc !important;
	    color: #000 !important;
	    font-size: 11px !important;
	}
	.setotas{
		 padding: 0px 6px 4px 0px;
	    text-align: right;
	    color: green;
	    font-size: 10px !important;
	}
	.clicniaddress{
		font-size: 12px !important; font-weight: bold;
	}
	.thermal_heading{
        font-family: 'Share';
    	line-height: 12px !important;
    	font-size: 14px !important;
    }
	
}
</style>
    <style>
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 185px;
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
    <style type = "text/css">
   <!--
      @media screen {
         p.bodyText {font-family:verdana, arial, sans-serif;}
      }

      @media print {
         p.bodyText {font-family:georgia, times, serif;}
      }
      @media screen, print {
         p.bodyText {font-size:10pt}
      }
   -->
</style>
</head>
<body   oncontextmenu="return false;" id="printSetting">

 <%
    
       String letterhead="letterhead";
    %>
<div class="row details mainheader hidden-print">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
									<img src="dashboardicon/microscope.png" class="img-responsive prescripiconcircle"><h4>View Investigation Request </h4>
								</div>
								
								</div>
</div>
	<%-- 
			<div class="hidden-print">
								<ul class="breadcrumb hidden-print">
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
			</div> --%>
		
<div class="col-lg-12 col-xs-12 col-md-12" >
	<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
			<%if(!loginInfo.isBalgopal()){ %>
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogoinvst()){ %>
					<%@ include file="/accounts/pages/letterhead.jsp" %>
				 <%} %>
			<%}else {%>
				<%ArrayList<Clinic> list=(ArrayList<Clinic>)request.getAttribute("locationAdressList");
				int val=0;
				%>
				<h2 style="text-align: center;" id="hospital_heading"><strong ><%=list.get(val).getClinicName().toString() %></strong></h2>
				<h4 style="text-align: center;"><s:property value="clinicOwner"/></h4>
				<h6 style="text-align: center;">Phone:<s:property value ="landLine"/></h6>
				
			<%} %>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
		<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<%-- <s:if test="reportType=='Numerical'" > --%>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);font-size: 14px;">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
		
	
		
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<s:if test="bghsts">
									<b class="setticolors"><u>INVESTIGATION REQUISITION</u></b>
								</s:if>
								<s:else>
									<b class="setticolors"><u><s:property value="labname"/></u></b> <br>
									<b><s:property value="creport"/> </b>
								</s:else>
							</div>
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
						
					</div>
					</div>
		
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin: 0px;">
				<%if(loginInfo.getInvestigation_newaccess().equals("0")){ %>
					<label for="inputEmail3" class="control-label">INV.ID<span style="font-weight: normal;">: <s:property value="id"/> </span></label><span>&nbsp; | &nbsp;</span> <label for="inputEmail3" class="control-label">UHID<span class="" style="font-weight: normal;">: <s:property value="abrivationid"/></span></label> <label for="inputEmail3" class="control-label hidden">Lab ID<span class="hidden" style="font-weight: normal;">: <s:property value="id"/></span></label>
				<%} %>
				</div>
				<%if(loginInfo.isBalgopal()){ %>
				<label for="inputEmail3" class="control-label">UHID<span class="" style="font-weight: normal;">: <s:property value="abrivationid"/></span></label> 
				<%} %>
				<div class="form-group" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">Patient Name : <span style="font-weight: normal;"><s:property value="fullname"/></span> </label><span class="hidden-print" style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label hidden-print">Age / Gender<span class="hidden-print" style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group visible-print hidden-lg hidden-md hidden-sm" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">Age / Gender <span style="font-weight: normal;">: <s:property value="ageandgender"/> </span></label>
				</div>
				<div class="form-group" style="margin: 0px;">
					<label for="inputEmail3" class="control-label">OPD/IPD : 
					<s:if test="ipdid==0">
					   <span style="font-weight: normal;">OPD</span>
					    </label>
					</s:if>
					<s:else>
						<span style="font-weight: normal;">IPD</span>
						 </label>
						 <span style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label">Ward / Bed<span style="font-weight: normal;">: <s:property value="wardname"/> / <s:property value="bedname"/> </span></label>
						<%--  <%if(loginInfo.isBalgopal()){ %> <span style="font-weight: normal;">&nbsp; | &nbsp;</span><label for="inputEmail3" class="control-label">INV REQ ID<span style="font-weight: normal;">:  <s:property value="invreq"/> </span></label><%} %> --%>
					</s:else>
					
					
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
			<div class="form-group" style="margin: 0px;">
			
					<label for="inputEmail3" class="control-label">Requested By<span style="font-weight: normal;">: <s:property value="reqdiaryUser"/></span> <%if(loginInfo.getInvestigation_newaccess().equals("0")){ %><sapn class="hidden-print">&nbsp; | &nbsp;</span></label> <label for="inputEmail3" class="control-label">Department<span style="font-weight: normal;">: <s:property value="reqspecialization"/></span></label><%} %>
			
				</div>
				<div class="form-group" style="margin: 0px;">
					<%if(loginInfo.getInvestigation_newaccess().equals("0")){ %><label for="inputEmail3" class="control-label">INV Req.<span style="font-weight: normal;">: <s:property value="collect_date"/></span> <sapn class="">&nbsp; | &nbsp;</span></label><%} %> <label for="inputEmail3" class="control-label">Report On<span style="font-weight: normal;">: <s:property value="update_date"/></span></label>
				</div>
				<div class="form-group" style="margin: 0px;">
				    <s:if test="tpid!=0">
					<label for="inputEmail3" class="control-label">Third Party<span style="font-weight: normal;">: <s:property value="thirdPartyCompanyName"/></span></label>
					</s:if>
				</div>
				
			</div>
		</div>
		<%-- </s:if>
		<s:else>
                      	
                      
       </s:else> --%>
		
		<div class="col-lg-1 col-md-1"></div>
		
		<img src="emr/img/draft_report.png" class="img-responsive hidden" style="position: absolute;margin-left: auto;margin-right: auto;width: 100%;"></img>
	</div>
	
	<div>
	<%int i=1; %>
                        <div class="row">
                        	<div class="col-lg-1 col-md-1"></div>
                        	<div class="col-lg-10 col-md-10" style="padding:0px;">
                    <table class="table" cellspacing="0" width="100%">
                    <s:iterator value="departmentInvList">
                         	 	<thead>
                                    <tr>
                                    <th>Sr No.</th>
                                    
                                    	<th class="printth" ><s:property value="name"/></th>
                                    	
                                    </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="invTypeList">
                                	<tr>
                                	<td><%=i++ %></td>
                                		<td><s:property value="invstname"/></td>
                                	</tr>
                                	
                                </s:iterator>
                                </tbody>
                                
                        </s:iterator>
                         	 
                         	 </table>
					<%
					if(loginInfo.isBalgopal()) {
					%> 
					<br><br><br><br><br><br><br><br>
					<table>

						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							
						</tr>
					</table>
					 <%
					}
					%> 
					<div class="row">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
                                </div>
                                 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                 <br><br>
                                     <p style="text-align: right;"><strong> </strong> . </p>
                                     
                                     <br><br>
                               </div>
                    <!--  -->                              </div>
					<div style="margin-top: 75px;"></div>
                                    	
                         	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding:0px;text-align: right;">
                               
                          <%--  <%
							if(loginInfo.isBalgopal()) {
							%> --%>
                                <div class="form-check" style="margin-left: 74px;">
								<input class="form-check-input" type="checkbox"
									id="flexCheckChecked" onclick="thermalPrint(this)"> <label
									class="form-check-label" for="flexCheckChecked">
									Thermal Print </label>
							</div><br><br>
							<%--  <%} %>   --%>
                                
                                <a href="#" onclick="printpage()"  class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Print</a>
                            </div>
                        	</div>
                        	<div class="col-lg-1 col-md-1"></div>
                        </div>

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
  function thermalPrint(val){
		if(val.checked){
			document.getElementById('printSetting').className="thermal";
			document.getElementById('hospital_heading').className="thermal_heading";
		}else{
			document.getElementById('printSetting').className="";	
			document.getElementById('hospital_heading').className="";
		}
	}
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

</body>

</html>
