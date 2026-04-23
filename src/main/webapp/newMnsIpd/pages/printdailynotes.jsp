<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% Bed ipd=(Bed)session.getAttribute("bed"); 
LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%> 
<s:hidden name='regno' id='uhiid'></s:hidden>
<!DOCTYPE html>
<html>
<head>
    <title>Admission Summary Form</title>
   <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">   
    <script type="text/javascript" src="ipd/js/admissionform.js"></script> 

<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
    <style>
    .savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
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
		    border-color: #339966;
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
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
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
    margin-bottom: 0px !important;
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
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 0px !important;
}

.titleset {
    color: blank !important;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    font-weight: bold !important;
    line-height: 20px;
    padding: 2px 0px 0px 6px !important;
    background-color: #efefef !important;
    -webkit-print-color-adjust: exact !important; 
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}

.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
}
 .horizontal_line {
            width: 90%;
            height: 5px;
            border-top: 5px dotted black;
            line-height: 80%;
        }

        .line {
            border-bottom: 1px solid black;
            margin-top: 5px;
            width: 40%;
        }
    </style>
    <style>

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 175px;
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
    background-color: #efefef;
     
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
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.form-group {
    margin-top: 0px;
}

</style>

<script>
$(document).ready(function() {
	var uhid=document.getElementById('uhiid').value;
	JsBarcode("#barcode", uhid, {
		  format: "CODE128",
		  height: 25,
		  width:1,
		  displayValue:false
		});
});
function updateipdDeclration(id){
	document.getElementById("hdndecid").value = id;
	var ipdid = document.getElementById("id").value;
	document.getElementById("ipdid").value =ipdid;
	document.getElementById("decfrmid").submit();
	
}

</script>

<s:form action="decIpd" id="decfrmid">
	<s:hidden name="hdndecid" id="hdndecid"/>
	<s:hidden name="ipdid" id="ipdid"/>
</s:form>

</head>
<body>


<%

   String hstry="";
   String sysreview="";
   String obstretic_history="";
   String menstrual_history="";
   String substance_history="";
   String pediatric="";
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
  
<s:if test="paediatrichist==true">
            
  </s:if>
  <s:else>
      <% pediatric="hidden"; %>
 </s:else>


<s:form action="updateIpd" theme="simple" id="ipdadmissionfrm">
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
	<s:hidden name="id" id="id"/>
	
	<%-- <div class="col-lg-12 col-md-12 col-sm-12 topback2 hidden-print">
		<s:select name="dectitle" id="dectitle" list="declerationTitleList"
		listKey="id" listValue="name" cssClass="form-control chosen-select" onchange="updateipdDeclration(this.value)"
		headerKey="0" headerValue="Select Decleration"/>
	</div> --%>
	
	<div class="col-lg-12 col-xs-12 col-md-12 printmarginleft" style="padding: 0px;">
		<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogobillinv()){ %>
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
			</div>
			
		</div>
	</div>
	
	<div class="">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:2px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<s:if test="casualtyipd==0">
								<b class="setticolors">ADMISSION SUMMARY</b>
							</s:if>
							<s:elseif test="casualtyipd==2"><b class="setticolors">DAYCARE SUMMARY</b></s:elseif>
							<s:else>
								<b class="setticolors">Casualty SUMMARY</b>
							</s:else>
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
				</div>
				
				
				
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:1px;padding-right:1px;">
				<!--  Lok new div-->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">UHID </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="regno"/></span> 
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Patient Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="client"/></span> 
					</div>
					
					<%-- <s:if test="relativename!=''">
					<s:if test="relativename!=null">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Guardian Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left h" style="padding: 0px;">
						<span>: <s:property value="birthplace"/></span> 
					</div>
					</s:if>
					</s:if> --%>
					
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Age / Gender   </b>
					</div>
					<%if(!loginInfo.isBalgopal()){ %>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="agegender"/></span> 
					</div>
					<%}else{ %>
					 <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="Ageonadmn"/></span> 
					</div>
					
					<%} %>
					
					<%-- <s:if test="familyDetails!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Family Details1  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="familyDetails"/></span> 
					</div>
					</s:if> --%>
					<s:if test="familyDetails!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Family Details</b> 
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
								<span>:
								<s:if test="relativename!=null">
								 <s:property value="relativename"/>,
								</s:if>
								
								<s:if test="relationcont!=null">
								M.<s:property value="relationcont"/>
								</s:if>
								
								<s:if test="relation!=null">
								(<s:property value="relation"/>)
								</s:if>
								 </span> 
					</div>
					</s:if>
					
					<s:if test="fathername!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Father Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="fathername"/></span> 
					</div>
					</s:if>
					
					<s:if test="mothername!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Mother Name  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="mothername"/></span> 
					</div>
					</s:if>
					
					<s:if test="birthplace!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Birth Place </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="birthplace"/></span> 
					</div>
					</s:if>
					
					<s:if test="contact!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Contact  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="contact"/></span> 
					</div>
					</s:if>
					<s:if test="address!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Address  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="address"/></span> 
					</div>
					</s:if>
					
				</div>
				
                   
						
			 </div>
	<%String examplenote=(String)session.getAttribute("examplenote"); %>
			
	
	<%ArrayList<Bed>dailynoteslist = (ArrayList<Bed>)session.getAttribute("dailynoteslist"); %>
	<% for(Bed bed:dailynoteslist ) {%>
	 <table class="" style="width: 100%; margin-bottom: 2px; margin-top: 10px;">
	<tr class="line" style="padding-top: 10px;">
	<th>Daily Notes</th>
	</tr> 
	<tr class="">
		<td><%=bed.getNotes() %></td>
				
	</tr>
	</table>
	<%} %>
	
	
 
			 <%--  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Package</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=examplenote %></span> 
	                        			</div>
	                        		</div>
	                        	</div>  --%>
						
		 </div>
		 
		 
	</div>
		
	 <div class="col-lg-12 col-md-12 hidden-print" style="margin-bottom:10px;padding: 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;text-align: right;">
<!-- 			                                <a href="#" onclick="sendmail();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Send Email">Email</i></a>
 -->	                                  		<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" title="Print">Print</a>
		                                </div>
		                            </div>
		                        </div>
 
</s:form>

 	




</body>

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
					

</html>
