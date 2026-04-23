<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<% Bed ipd=(Bed)session.getAttribute("bed"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admission Summary Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">   
    <script type="text/javascript" src="ipd/js/admissionform.js"></script> 


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
   
%>



<s:form action="updateIpd" theme="simple" id="ipdadmissionfrm">
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
	<s:hidden name="id" id="id"/>
	
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
		<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			
		</div>
	</div>
	
	<div class="">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								
								
								<s:if test="formtype==1">
									
									<b class="setticolors">Obstretics Assessment Form</b>  
								
								</s:if>
								<s:elseif test="formtype==2">
										<b class="setticolors">Gynaecology Assessment Form</b>
								
								</s:elseif>
								<s:else>
									<b class="setticolors">Infertility Assessment Form</b>
								
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
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID </b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Age/Gender</b>
							</div>
							<s:if test="contact!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="abrivationid"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="client"/> </span>
							</div>
							
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="agegender" /> </span>
							</div>
							<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: <s:property value="contact"/></span>
								</div>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="address"/> </span> 
							</div>
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right hidden" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Adm. ID</b>           		 
								<s:if test="admissiondate!=''">
								<br><b for="inputEmail3" class="control-label">Adm. Date</b>
							</s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Ward / Bed</b>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Payee</b>
							  <s:if test="mlcno!=''">
							 <b for="inputEmail3" class="control-label">/MLC</b>
							 </s:if>
							 
						</div>
						<s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Family Details</b> 
							</div> 
						</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<%-- <span>: <s:property value="id"/>/<s:property value="num_admission"/></span>   --%>
							<span>: <s:property value="ipdseqno"/></span>          		 
						<s:if test="admissiondate!=''">
								  <br><span>: <s:property value="admissiondate" /> </span>
								  </s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="wardid" /> / <s:property value="bedid"/></span> 
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="thirdParty"/></span>
							  <s:if test="mlcno!=''">
							 <span>/ <s:property value="mlcno"/></span>
							 </s:if>
							 <s:if test="mlcrefdoctor!=''">
							 	<span>/ <s:property value="mlcrefdoctor"/></span>
							 </s:if>
						</div>
						<s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="relativename"/>,<s:property value="relationcont"/> (<s:property value="relation"/>)</span> 
							</div>
						</s:if>
						</div>
						
					</div>
					</div>
				</div>
				
				<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;padding-left: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				  
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				<span><s:property value="doctor_name"/>   
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		
	                        	</div>
					</div>
					
							
					</div>
				</div>
				
				<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
                                 <% if(ipd.getAddmissionfor()!=null){  %>
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Admission For</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAddmissionfor() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	 <%} %>
	                        	 
	                        	<!--<% if(ipd.getReimbursment()!=null){  %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Reimbursement</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;">: <%=ipd.getReimbursment() %></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                <%} %>
                                --><% if(ipd.getAlergies()!=null) {  %>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O Allergies</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAlergies() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	
	                        	<!--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Admission Details</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;">: <%=ipd.getAdmissiondetails() %></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                --><%} %>
                                
                                <%if(ipd.getPackagename()!=null){ %>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Package</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPackagename()%></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                <%} %>
                             <% if(ipd.getSummary()!=null) { %>   
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="padding: 10px 0px 0px 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SUMMARY </h4>
	                        </div>
	                        <%} %>
	                        
	                     <%if(ipd.getChiefcomplains()!=null){ %>
	                     	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Chief Complaints</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getChiefcomplains() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
                        	<%} %>   
	                        
	                     <%if(ipd.getAdmission_reason()!=null){ %> 
	                     <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Reason For Admission</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAdmission_reason() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>  
                        <%} %>
                        	
                        	<%if(ipd.getPresentillness()!=null) { %>
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Present Illness</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPresentillness() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
                        	<%} %>
                        </div>
					</div>
				</div>
				
				
				
					<div class="">
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">REASON FOR VISIT </h4>
	                        </div>
	                        <div class="row ">
	                            <s:iterator value="allVisitReasonList">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> <s:property value="reasonvisit"/> </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="">Region  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="d">Quality  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="">Periodicity  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="">Influencing Factor </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="">Since  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="">Note  </label> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group margintopir">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><s:property value="reason"/></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><s:property value="quality"/></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><s:property value="periodicity"/></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><s:property value="influence"/></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><s:property value="since"/></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><s:property value="notes"/></span> 
	                        			</div>
	                        			 <br>
	                        	</div>
	                        	
	                        </div>
	                       
	                        </s:iterator>
					</div>
					
					
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SUBSTANCE HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> ALCOHOL</label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">OTHER MEDICATION </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">SMOKING </label> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class=margintoptwoirf>
	                        				 <span> <%=ipd.getAlcohal() %> </span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getOther_medication() %> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getSmoking() %> </span> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">TOBACCO </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">DRUGS</label> 
	                        			</div>
	                        			 
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">TOBACCO</label> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getTobaco() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getDrugs() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getTobaconotes() %> </span> 
	                        			</div>
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
					
					
					
					
					
					
				</div>
				 
				<div class="hidden">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OBSTRETIC HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Nulligravida </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Currently Pregnent </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">IUD </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				<span><%=ipd.getNulligravida() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getCurrent_pregnent() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getIud() %> </span> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Live Boys <span style="font-weight: normal;"><%=ipd.getLive_boys() %></span></label>, <label for="exampleInputName2">Live Girls <span style="font-weight: normal;">: <%=ipd.getLive_girls() %></span></label>, <label for="exampleInputName2">Still Births <span style="font-weight: normal;">: <%=ipd.getStillbirths() %></span></label>, <label for="exampleInputName2">Abortions <span style="font-weight: normal;">: <%=ipd.getAbortions() %></span></label>, <label for="exampleInputName2">Death Child <span style="font-weight: normal;">: <%=ipd.getDeath_children() %></span></label>  
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">P <span style="font-weight: normal;"><%=ipd.getP()%></span></label>, <label for="exampleInputName2">L <span style="font-weight: normal;">: <%=ipd.getL() %></span></label>, <label for="exampleInputName2">A <span style="font-weight: normal;">: <%=ipd.getA() %> </span></label>, <label for="exampleInputName2">D <span style="font-weight: normal;">: <%=ipd.getD() %> </span></label>  
	                        			</div>
	                        	</div>
	                        	</div>
	                        	</div>
	                        	<%int i=0; %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	  <s:if test="gynicobsList.size>0">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
	                        				<table class="table" style="border: 1px solid #eee;width: 100%;">
	                        					<thead>
	                        						<tr>
	                        							<th>Sr.No</th>
	                        							<th>Year</th>
	                        							<th>Child</th>
	                        							<th>Type of Delivery</th>
	                        							<th>Indications</th>
	                        							<th>Complications</th>
	                        							<th>Institutions</th>
	                        						</tr>
	                        					</thead>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr style="border-bottom: 1px solid #eee;">
	                        							<td style="width: 4%;padding-right: 15px;"> <%=i+1 %></td>
	                        							<td style="width: 5%;padding-right: 15px;"><s:property value="year"/></td>
	                        							<td style="width: 13%;padding-right: 15px;"><s:property value="type"/></td>
	                        							<td style="width: 14%;padding-right: 15px;">
															<s:property value="type_delivery"/>
	                        							</td>
	                        							
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="indications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="coamplications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="institution"/></td>
	                        						</tr>
	                        						 <%i++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
	                        		</div>
	                        		</s:if>
	                        		 <s:if test="parity_abortion_notes!=''">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Parity/Abortion Notes</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><s:property value="parity_abortion_notes"/> </span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</s:if>
	                        	</div>
	                        </div>
	                        
					</div>
				</div>
				
				<div class="hidden">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
					
					    <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      
	                        	      <h4 class="text-left titleset" style="color:#6699cc;">Surgical History</h4>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	      <%=ipd.getSurgical_ho() %>
	                        	</div>
	                        		
	                      </div>
					
				</div>
				</div>
				
				
				<!-- IVF -->
				
				<div class="hidden">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">IVF HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">IVF Date </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Down Regulation </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Ovarian Simulation </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">OS Dosage </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Sperm Quality </label> 
	                        			</div>
	                        				<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">ET Days </label> 
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Oocytes Obtained </label> 
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Oocytes Quality </label> 
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Embros Grade </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Embros Transeferred </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Embros Description </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Freezing </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Transfer Process </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Beta HCG-mIU/ml </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Remark </label> 
	                        			</div>
	                        			
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getIvf_date() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getDown_regulation() %> </span>
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getOvarian_stimulation()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getOs_dosage() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getSperm_quality()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getEt_day()%> </span> 
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <span> <%=ipd.getOocytes_obtained()%> </span> 
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <span> <%=ipd.getOocytes_quality()%> </span> 
	                        			</div>
	                        			</div>
	                        			
	                        				<div class="form-group">
	                        				 <span> <%=ipd.getEmbroyos_grade()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getEmbroyos_transfered()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getEmbroyos_description()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getFreezing()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getTransfer_process()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getBetahcgcm()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getRemark()%> </span> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px">
	                        			
	                        	</div>
	                        	</div>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	</div>
	                        </div>
	                        
					</div>
				</div>
				
				 
			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden " style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">FAMILY HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Age at Minarche </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O genetic anomalies in Family </label> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getAge_menarche()%> </span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getHo_fertility_family()%> </span> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O genetic anovalis in Family </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O premature menopause</label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getHo_genetic_family()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getHo_premature_family()%> </span> 
	                        			</div>
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
					
					
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MENSTRUAL H/O</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Age of menarche cycle </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Flow</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Episode of Giddiness / Blachouts</label> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getAge_of_menarche()%> </span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getFlow()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getBlachouts()%> </span> 
	                        			</div>
	                        			
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Dysmenorrhoe </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Sleep disruption due to bleeding</label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getDysmenorrhoe()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getSleep_disruption_bleeding()%> </span> 
	                        			</div>
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
					 
					 
					 
					 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MEDICAL HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">For all </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Family History</label> 
	                        			</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getFamily_history()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				<span> <%=ipd.getFamily_history_notes()%> </span> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Pt.History </label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPt_history_notes()%> </span> 
	                        			</div>
	                        			 
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
					
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MEDICAL HISTORY</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">SURGICAL H/O </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getSurgical_ho()%> </span> 
	                        			</div>
	                        			
	                        	</div>
	                         
	                        	</div>
	                        	
	                        </div>
					</div>
					
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">DIAGNOSIS</h4>
	                        </div>
	                        
	                        <s:if test="formtype==1">
	                        		<div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Gravida</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Live</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">MTP</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Death</label> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getGravida()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getLive()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getMtp()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getDeath()%> </span> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Para </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Abortion </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Still Born </label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">With High Risk Factor</label> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPara()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getAbortion()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getStill_born()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getHigh_risk_factor()%> </span> 
	                        			</div>
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
	                        </s:if>
	                        <s:else>
	                        
	                        		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px 0px 0px 12px;">
	                        			
	                        			<%=ipd.getDiagnosisgyn() %>
	                        			 
	                        	</div>
	                        
	                        
	                        </s:else>
	                        
					</div>
					
					
					
					
					
					
					
					
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MEDICAL & SURGICAL H/O</h4>
	                        </div>
					<div class="col-lg-12 col-xs-12 col-md-12 hidden" style="padding-bottom: 15px;">
									<h5 class="lighbdsub"><b> <label for="exampleInputName2" style="text-transform: uppercase;">Family H/o </label></b></h5>
									<table class=" ">
									  
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Date</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDate1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDate2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDate3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDate4()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <label for="exampleInputName2" style="text-transform: uppercase;">HV &lt; 1/M</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHv_1m()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Hb</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHb1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHb2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHb3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHb4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">HBs Ag</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHbs_ag()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">FBS</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getFbs1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getFbs2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getFbs3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getFbs4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">VDRL</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getVdrl()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">PPBS</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDpbs1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDpbs2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDpbs3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDpbs4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">SICKLING</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getHb_srecta()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">U &lt; R M</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getUrm1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getUrm2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getUrm3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getUrm4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">HBA1C</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getRemark()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">TSH</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getRemark()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getRemark()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getRemark()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getRemark()%> </span> </td>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Duet Markess</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getDuet_markess()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">ICT</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getIct1()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getIct2()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getIct3()%> </span> </td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getIct4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Triple Marker</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getTriple()%> </span> </td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">GTT</label></td>
													<td class="tabletdwidthnborderzero"> <span> <%=ipd.getGtt1()%> </span> </td>
													<td class="tabletdwidthnborderzero"> <span> <%=ipd.getGtt2()%> </span> </td>
													<td class="tabletdwidthnborderzero"> <span> <%=ipd.getGtt3()%> </span> </td>
													<td class="tabletdwidthnborderzero"> <span> <%=ipd.getGtt4()%> </span> </td>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Quradraple Marker</label></td>
												<td class="tabletdwidthnborderzero"> <span> <%=ipd.getQuadrple_maicers()%> </span> </td>
											</tr>
									 
									</table>
								 </div>
								 
								 
								 
 
 



 



 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">PERSONAL DETAILS </h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">11 Weeks  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Anomaly Scan  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Cervical Length  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Double Marker  </label> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        			 
	                        		 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        			<div class="form-group margintopir">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAnormaly_scan_11week()%></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			  <span><%=ipd.getCervical_length_11week()%></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getDouble_marker_11week()%></span> 
	                        			</div>
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">16 Weeks  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">All Investigation  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Sickling  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Triple Marker  </label> 
	                        			</div>
	                        			
	                        			
	                        		 
	                        			
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group margintopir">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAll_investigation_16week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getSikling_16week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getSikling_16week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        	 
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 margintopironefive">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">1st Visit  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Abstinence  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Barrier Contra.  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Bed Rest  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Book </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">CSV  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">GTT  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Drug Reaction  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">HCG  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Heparin  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Oral Hygeine  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Other Test  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Physio. & Diet  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Rubelle Status  </label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Smart Doc  </label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Stem cell  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Vaginitis  </label> 
	                        			</div>
	                        		 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        			<div class="form-group margintopir">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAbstinence_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getBarrier_contra_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getBed_rest_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getBook_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getCsv_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getDispi_test_1visit() %></span> 
	                        			</div>
	                        			<div class="form-group margintopsix">
	                        				 <span><%=ipd.getDrug_reaction_1visit() %></span>
	                        			</div>
	                        			<div class="form-group">
	                        				 <span><%=ipd.getHcg_1visit() %></span> 
	                        			</div>
	                        				<div class="form-group ">
	                        				 <span><%=ipd.getHeparin_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group ">
	                        			 <span><%=ipd.getOral_hygeine_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getOther_test_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group margintopsix">
	                        				 <span><%=ipd.getPhysio_diet_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getRubelle_status_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getSmart_doc_1visit() %></span> 
	                        			</div>
	                        			<div class="form-group ">
	                        				 <span><%=ipd.getStream_cell_1visit() %></span> 
	                        			</div>
	                        			<div class="form-group ">
	                        				 <span><%=ipd.getVaginities_1visit() %></span> 
	                        			</div>
	                        			
	                        			
	                        		 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">20 Weeks  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Anomally Scan  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Fetal Echo  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group margintopironefive">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">24 WEEKS - 28 WEEKS  </label> 
	                        			</div>
	                        			
	                        			
	                        		 
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">ANTI-D  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">GTT  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">ITC  </label> 
	                        			</div>
	                        			
	                        			
	                        			
	                        			
	                        			
	                        	 
	                        			
	                        			
	                        			<div class="form-group margintopironefive">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">30 WEEKS  </label> 
	                        			</div>
	                        			
	                        			
	                        		 
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">INVESTIGAION SOS  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">STEROIDS  </label> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">VAGINITIS TREATMENT  </label> 
	                        			</div>
	                        			
	                        			
	                        			
	                        			<div class="form-group margintopironefive">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">34 WEEKS - 36 WEEKS  </label> 
	                        			</div>
	                        			
	                        			
	                        		 
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">BREAST PREPARATION  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">COLOR DOPPLER
  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Epidural/Painless Delivery  </label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">NST  </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">VAGNITIES TREATMENT  </label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group margintopir">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAnimally_scan_20week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getFetal_eco_20week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			
	                        			 
	                        				<div class="form-group margintopironefive">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group margintopir">
	                        				 <span><%=ipd.getAnti_d_24week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getDipsi_24week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getItc_24week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			
	                        			
	                        			
	                        				<div class="form-group margintopironefive">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group margintopsix">
	                        				 <span><%=ipd.getInvestigation_sos_30week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getSteroids_30week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getVaginities_treatment_30week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			
	                        			
	                        			<div class="form-group margintopironefive">
	                        				<span> &nbsp;</span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getBreast_preparation_34week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        			 <span><%=ipd.getColor_doppler_34week() %></span> 
	                        			</div>
	                        			
	                        			
	                        			<div class="form-group">
	                        				 <span><%=ipd.getNst_34week() %></span>
	                        			</div>
	                        			<div class="form-group">
	                        				 <span><%=ipd.getVaginities_treatment_34week() %></span> 
	                        			</div>
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>

 </div>
								 
				


	
	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
<h4 class="text-left titleset" style="color:#6699cc;">NST CHART</h4>
                 </div>
	<div class="col-lg-12 col-xs-12 col-md-12 hidden" style="margin-bottom:10px;padding-top: 10px;">
									<table class="">
										<tbody>
											<tr>
											   <td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Date</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_date6()%> </span></td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Time</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_time6()%> </span></td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Indication for NST</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_indication6()%> </span></td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Duration of NST</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_duration6()%> </span></td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Interpretation</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_interpretation6()%> </span></td>
											</tr>
											<tr>
												<td class="tabletdwidthnborderzero"><label for="exampleInputName2" style="text-transform: uppercase;">Intervention</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention4()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention5()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getNst_intervention6()%> </span></td>
											</tr>
										</tbody>
									</table>
								 </div>	
								 
								 
								 
								 
								 
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden " style="padding:10px 0px 0px 0px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">Immunidation</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Inj. TT 1st Dose </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Influenza Vaccine </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getTt_dose1()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getInfluenza_vaccine()%> </span> 
	                        			</div>
	                        			
	                        			
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Inj. TT 2nd Dose </label> 
	                        			</div>
	                        			 
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getTt_dose2()%> </span> 
	                        			</div>
	                        			 
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
					
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">USG DETAILS</h4>
	                        </div>
					
					<div class="col-lg-12 col-xs-12 col-md-12 hidden">
									
									<div class="col-lg-10 col-md-10 col-xs-10" style="padding:0px;padding-top: 10px;">
										<table class="">
										<tbody>
											<tr>
												<td style="width: 19%;"><label for="exampleInputName2" style="text-transform: uppercase;">Date</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getUsgdate1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getUsgdate2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getUsgdate3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getUsgdate4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Amenorrhea</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAmenorrhea1() %> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAmenorrhea2() %> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAmenorrhea3() %> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAmenorrhea4() %> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Presentation</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPresentation1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPresentation2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPresentation3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPresentation4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">BPD/GS</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getBpdgs1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getBpdgs2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getBpdgs3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getBpdgs4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">HC</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getHc1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getHc2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getHc3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getHc4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">AC</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAc1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAc2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAc3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getAc4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">FL/CRL</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFlcrl1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFlcrl2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFlcrl3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFlcrl4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">GA by USG</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getGa_usg1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getGa_usg2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getGa_usg3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getGa_usg4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Liquor</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getLiquor1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getLiquor2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getLiquor3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getLiquor4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Placenta</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPlacenta1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPlacenta2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPlacenta3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getPlacenta4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Foetal Weight</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFoetal_weight1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFoetal_weight2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFoetal_weight3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getFoetal_weight4()%> </span></td>
											</tr>
											<tr>
												<td><label for="exampleInputName2" style="text-transform: uppercase;">Cervical Length</label></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getCervical_length1()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getCervical_length2()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getCervical_length3()%> </span></td>
												<td class="tabletdwidthnborderzero"><span> <%=ipd.getCervical_length4()%> </span></td>
											</tr>
										</tbody>
									</table>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-2" style="padding-right: 0px;">
										<div class="form-group">
											<label>Early anomaly / NT Scan</label>
											<span> <%=ipd.getNt_scan()%> </span>
										</div>
										<div class="form-group">
											<label>Anomaly Scan</label>
											<span> <%=ipd.getAnomaly_scan()%> </span>
										</div>
										<div class="form-group">
											<label>Colour Doppler Scan</label>
											<span> <%=ipd.getColour_doppler_scan()%> </span>
										</div>
									</div>
								 </div>
					
					
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
<h4 class="text-left titleset" style="color:#6699cc;">EXAMINATION / VITALS</h4>
                 </div>
                 <div class="col-md-4">
 
<div class="row">
<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="padding:0px;">
	                        	    
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Genral Condition</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Temp</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Pallor</label> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Pedal Edema</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Pulse</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">BMI</label> 
	                        			</div>
	                        			 	<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Height 
	                        				 </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Weight 
	                        				 </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Sys-BP 
	                        				 </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Dia-BP 
	                        				 </label> 
	                        			</div>
	                        			
	                        			 
	                        		 
	                        			
	                        			
	                        			 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-3 col-md-2 col-xs-2 col-sm-2" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getGen_condition()%> </span>
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				<span> <%=ipd.getTemp()%> </span>
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPallor()%> </span> 
	                        			</div>
	                        			<div class="form-group margintoptwoirf">
	                        				 <span> <%=ipd.getPedal_edema()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPulse()%> </span> 
	                        			</div>
	                        			<div class="form-group ">
	                        				 <span> <%=ipd.getBmi()%> </span> 
	                        			</div>
	                        			<div class="form-group margintoptwoirf">
	                        				 <span> <%=ipd.getHeight()%> </span> 
	                        			</div>
	                        			<div class="form-group margintoptwoirf">
	                        				 <span> <%=ipd.getWeight()%> </span> 
	                        			</div>
	                        			<div class="form-group margintoptwoirf">
	                        				 <span> <%=ipd.getSys_bp()%> </span> 
	                        			</div>
	                        			<div class="form-group margintoptwoirf">
	                        				 <span> <%=ipd.getDia_bp()%> </span> 
	                        			</div>
	                        			
	                        	</div>
	                        	</div>

</div>

<div class="col-md-4">

<div class="row hidden">
<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="padding:0px;">
	                        	    
	                        			<div class="form-group">
	                        				  <label for="exampleInputName2">Sketch</label>
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> cranio</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Placenta Previa</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Female Reproductive Organ</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">  Female Reproductive System
	                        				 </label> 
	                        			</div>
	                        			
	                        			  
	                        		 
	                        			
	                        			
	                        			 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding:0px 0px 0px 12px;">
	                        	      
	                        	      <div class="form-group">
	                        				 <span> &nbsp; </span> 
	                        			</div> 
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getRemark()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getRemark()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getRemark()%> </span> 
	                        			</div>
	                        			<div class="form-group margintopirtwofiven">
	                        				 <span> <%=ipd.getRemark()%> </span> 
	                        			</div> 
	                        		 
	                        			
	                        			 
	                        			
	                        			
	                        	</div>
	                        	</div>

</div>

				
				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
<h4 class="text-left titleset" style="color:#6699cc;">P/A</h4>
                 </div>
                 
                
                <s:if test="formtype==1">
                
                				 <div class="col-md-6">
  <label for="exampleInputName2">Abdominal Wall Edema</label>
<div class="row">
	                        	
	                        	<div class="col-lg-3 col-md-3 col-xs-4 col-sm-3" style="padding:0px 0px 0px 12px;">
	                        	       
	                        			<div class="form-group">
	                        			    <% if(ipd.getWall_edema()=="0") { %>
	                        				 <span> Absent</span>
	                        				 <%} else { %>
	                        				 <span> Present</span>
	                        				 <%} %> 
	                        			</div>
	                        			 
	                        			
	                        	</div>
	                        	</div>
	                        	
	                        	  <label for="exampleInputName2">Presentation</label>
	                        	
	                        	<div class="row">
<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-right" style="padding:0px;">
	                        	    
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Cephalic</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Breech</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Baley size</label> 
	                        			</div>	
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Transverse FHSe</label> 
	                        			</div> 
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Liquor</label> 
	                        			</div> 
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Uterine Contractions</label> 
	                        			</div> 
	                        			 	 
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-3 col-md-3 col-xs-4 col-sm-3" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getCephalic()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getBreech()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getBaley_size()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getTransverse_fhs()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getLiquor()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getUterine_contractions()%> </span> 
	                        			</div>
	                        			 
	                        			
	                        	</div>
	                        	</div>
	                        	
	                        	

</div>




	<div class="col-md-6">
  <label for="exampleInputName2">Fundal Height</label>
<div class="row">
<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-right" style="padding:0px;">
	                        	    
	                        			<!-- <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">12 W</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">14 W</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">16 W</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">18 W</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">20 W</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">22 W</label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">26 W</label> 
	                        			</div> 
	                        		 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">28 W</label> 
	                        			</div> 
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">30 W</label> 
	                        			</div> 
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">32 W</label> 
	                        			</div> 
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">34 W</label> 
	                        			</div> 
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">36 W</label> 
	                        			</div> 
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">38 W</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">40 W</label> 
	                        			</div>
	                        			  -->
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-3 col-md-3 col-xs-4 col-sm-3" style="padding:0px 0px 0px 12px;">
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getFundal_height()%> </span> 
	                        			</div>
	                        	</div>
	                        	</div>
		


                			
                </s:if>
                <s:else>
                
        						<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
        						
        						    <%=ipd.getPa_gynic() %>
        						</div>
        						
        						
        						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
								<h4 class="text-left titleset" style="color:#6699cc;">L/E</h4>
								
                 </div>
                 
                 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
                 
                 		<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Vulva </label> 
	                        				 
	                        				 
	                        				 <div class="form-group">
	                        				 <span> <%=ipd.getLe_vulva()%> </span> 
	                        			</div>
	                        				 
	                        			</div>
	                        			
	                        		 
	                        		 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Vagina</label> 
	                        				 
	                        				 <div class="form-group">
	                        				 <span> <%=ipd.getLe_vagina()%> </span> 
	                        			</div>
	                        			
	                        				 
	                        			</div>        	
								
								
								
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	                    
	                        		    
	                        			
	                        	</div>	
                 </div>
                 
                
      	          </s:else>


</div>
				
				
				
				
				
				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">P/S</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Cervix </label> 
	                        			</div>
	                        			
	                        		 
	                        		 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Vault</label> 
	                        			</div>
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	                    
	                        		    <div class="form-group">
	                        				 <span> <%=ipd.getPs_cervix()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPs_vault()%> </span> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Vagine </label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPs_vagine()%> </span> 
	                        			</div>
	                        			 
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
				
				
				
				
				
				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="padding:10px 0px 0px 0px;">
					   
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">P/V</h4>
	                        </div>
	                        <div class="row ">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;"> Trim </label> 
	                        			</div>
	                        			
	                        		 
	                        		 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">  ant</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">   Just Ettacced</label> 
	                        			</div>
	                        			
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">   Soft</label> 
	                        			</div> 
	                        			
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">   Post</label> 
	                        			</div>
	                        			
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">   os</label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">   Membranes</label> 
	                        			</div>
	                        			
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	                    
	                        		<div class="form-group">
	                        				 <span> <%=ipd.getPv_trim()%> </span> 
	                        			</div>
	                        			
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_ant()%> </span> 
	                        			</div>
	                        			 
	                        			 
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_just_ettacced()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_soft()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_post()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_os()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_membranes()%> </span> 
	                        			</div>
	                        			 
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Unettacced </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Tubular </label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Mid Posits  </label> 
	                        			</div>
	                        			 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Ettacced  </label> 
	                        			</div>
	                        			 
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_unettacced()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_tubular()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_mid_posits()%> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <span> <%=ipd.getPv_ettacced()%> </span> 
	                        			</div>
	                        			 
	                        			 
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
				
				
			   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
							<h4 class="text-left titleset" style="color:#6699cc;">Plan</h4>
							
							 <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
							     <%=ipd.getPlan() %>
							 </div>
                 </div>
                 
                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
							<h4 class="text-left titleset" style="color:#6699cc;">Final Diagnosis</h4>
							
							 <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
							  <%i=0; %>
							    <table style="width: 100%">
							  
							    <s:iterator value="diagnosisListGynic">
										<tr>
											<td><%=(++i) %></td>
											<td><s:property value="name" /></td>								
										</tr>								     
							    </s:iterator>
							    </table>
							 </div>
                 </div>
                  
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
							<h4 class="text-left titleset" style="color:#6699cc;">Priscription</h4>
							
							 <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px;">
							    <%=ipd.getPriscription() %>
							 </div>
							
                 </div>
                 
		
				
				
				
				<div class="col-lg-12" style="text-align:center ;padding-top: 15px;-webkit-print-color-adjust: exact;">
						<label style="font-size: 22px"></label><!--HIGH RISK CONSENT by   04 oct 2017   -->
					</div>
				<div class="">
	                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padsign">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-left" style="padding:0px;">
										
										<span><s:property value="doctor_name"/><br>(<s:property value="qualification"/>)</span><br><span>[FOR <s:property value = "clinicName"/>]</span>
										
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right" style="padding:0px;">
										<br>
										<span>Name & Signature of Patient / Attendant</span>
										<br><br>
										<span>Admitted By: <s:property value="addmitedbyuserid"/> <s:property value="admissiondate" /></span><br>
										<span>Printed By: <s:property value="printedBy"/></span>
									</div>
								</div>
	                        <div class="col-lg-12 col-md-12 hidden-print" style="padding: 10px 0px 0px 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;">
		                                <input type="button" class="btn btn-primary savebtn savebigbtn"  value="Print" onclick="printpage()">
		                                </div>
		                            </div>
		                        </div>
	                        </div>
	                        
	                        </div>
				
				
	
	 
 
</s:form>

 	


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
                            
                            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                <form>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Hospital/Clinic</label>
                                                <p class="help-block"><%=ipd.getHosp() %></p>
                                        </div>
								</form>
                                </div>
                                
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="display:none">
                                                <div class="from-horizontal">


                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
			                                        <label for="inputEmail" class="control-label textright"><b>Suggested Opr.:</b></label>
			                                        </div>
                                                        
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                              <%=ipd.getSuggestoper() %>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
				                                         <label for="inputEmail" class="control-label textright"><b>Location:</b></label>
				                                        </div>
                                                       
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                          <%=ipd.getDepartment() %>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                            </div>

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
