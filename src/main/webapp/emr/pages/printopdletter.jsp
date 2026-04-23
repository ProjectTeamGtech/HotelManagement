<%@page import="com.a.a.a.g.m.l"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.Emr.eu.entity.Emr"%>
<%@page import="java.util.ArrayList"%>


<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
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
    line-height: 12px !important;
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
    padding: 3px 5px 3px 5px !important;
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
.settopbac {
    background-color: #ddd !important;
}
.totalbor {
    background-color: #f5f5f5 !important;
}

    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
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
	border-bottom: 4px double #ddd;
	font-size:12px !important;
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
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
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
.tableback {
    background-color: #4E7894 !important;
    color: #ffffff !important;
}
th {
    padding: 3px 3px 3px 5px !important;
    border-right: 1px solid #eee !important;
    color: #fff !important;
}

}
    </style>
    <style>
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 165px;
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
    padding: 3px 3px 3px 5px !important;
    border-right: 1px solid #eee !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
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

.tableback {
   background-color: #e0faff;
    color: #15536e;
    font-style: normal;
}
table {
    background-color: transparent;
    width: 100%;
        text-transform: uppercase;
}
.space16{
	line-height: 16px !important;
}
</style>

<style>
 @media print
   {
     .displaynoneprint{display: none;}
     .printmarginleft{margin-left: 25px;}
      .printmarginright{margin-right: 26px !important;}
   }
</style>
<%-- <s:if test="isfromdeptopd==1">
<script>
document.onreadystatechange = function(){
    if(document.readyState === 'complete'){
	
	printpage();
	
	
    }
}

</script>
</s:if>
 --%>

<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
<% String emrClientName = (String)session.getAttribute("emrClientName");%>

<div class="col-lg-12 col-xs-12 col-md-12 printmarginleft" style="font-family: 'Open Sans', sans-serif;">
	<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogoemr()){%>
				<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row">
	
										
									
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: #e0faff;font-size:11px;">
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
				<div class="col-lg-5 col-xs-5 col-md-5 col-sm-5 text-right">
					<div class="form-group hidden-print text-center" style="margin-bottom: 3px;">
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
				        <span>UHID<!-- /ID --> :</span>
					</div>
				<%if(loginInfo.isMatrusevasang()){%>
					<div class="form-group" style="margin-bottom: 3px;">
				        <span>Registration No.  :</span>
					</div>
				<%} %>	
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient Name :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Department :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Category :</span>
					</div>
				<%if(loginInfo.isMatrusevasang()){%>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient Occupation :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient Monthly Income :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient Education :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Religion :</span>
					</div>
					
				<%} %>	
					<s:if test="statusenrollcode==1">
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Enrollment No. :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Employee Name :</span>
					</div>
					</s:if>
					
					<s:hidden value="campArea1"></s:hidden>
					<%-- <s:if test="campArea1==null">
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Camp Area:</span>
					</div>
					</s:if> --%>
					
					
				</div>
					
				<div class="col-lg-7 col-xs-7 col-md-7 col-sm-7 text-left" style="padding:0px;">
					<div class="form-group hidden-print text-center" style="margin-bottom: 3px;">
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
				       <span>&nbsp;<s:property value="abrivationid"/></span>
					</div>
					<%if(loginInfo.isMatrusevasang()){%>
					<div class="form-group" style="margin-bottom: 3px;">
				       <span>&nbsp;<s:property value="regino"/></span>
					</div>
					<%} %>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="fullname"/> </span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="lmh_department"/> </span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="patientcategory"/></span>  <span>-&nbsp;<s:property value="campArea"/></span>
						
					</div>
				 <%if(loginInfo.isMatrusevasang()){%>
					  <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Patientoccc"/> </span>
					  </div>
					  
					  <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Patientincm"/> </span>
					  </div>
					
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="PatientEductn"/> </span>
					  </div>
					  
					  <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Religion"/> </span>
					  </div>
					
					<%} %>
					
					<s:if test="statusenrollcode==1">
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="enrollcode"/> </span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="clientname"/> </span>
					</div>
					</s:if>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:0px;padding-right:0px;">
			<div class="form-group hidden-print text-center" style="margin-bottom: 3px;">
					<a href="#" id="button" class="hidden-print" onclick="showhide()" style="background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a><br>
				</div>
				<div class="col-lg-5 col-xs-5 col-md-5 col-sm-5 text-right">
					<div class="form-group" style="margin-bottom: 3px;">
				        <span>Date :</span>
					</div>
					<div class="form-group " style="margin-bottom: 3px;">
						<span>Age / Gender :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Contact :</span>
					</div>
					<%if(loginInfo.isMatrusevasang()){%>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient husband Occupation :</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient husband Income:</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Patient Husband Education:</span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>Cast :</span>
					</div>
				<%} %>	
				</div>
				<div class="col-lg-7 col-xs-7 col-md-7 col-sm-7 text-left" style="padding:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
				       <span>&nbsp;<s:property value="dateTime"/></span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="agegender"/></span>
					</div>
					<div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="mobno"/> </span>
					</div>
					<%if(loginInfo.isMatrusevasang()){%>
					  <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Patienthusocc"/> </span>
					 </div>
					
					 <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Patienthusincome"/> </span>
					 </div>
					 
					  <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="PathusbEductn"/> </span>
					 </div>
					 
					 <div class="form-group" style="margin-bottom: 3px;">
						<span>&nbsp;<s:property value="Cast"/> </span>
					  </div>
					
					<%} %>
				</div>
				
			</div>
			
			
			
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	
	<div class="row">
		<div class="col-lg-1 col-md-1"></div>
		<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding:0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 text-left" style="padding:0px;margin-top: 5px;">
			
			
   <div align="right" >
    <a href="#" onclick="hidediv()" class='hidden-print' style="text-align: right;background-color: gray;color: white;">Hide below</a>
</div>	
			
			<%if(loginInfo.isEmr_vitals_show()){ %> 
			<div id='rem' onclick="">
				<%if(loginInfo.isVermanh()){ %>
					<s:if test="Temprature!=''"><div class="col-lg-3 col-md-3 col-xs-3"><b>Temp :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="Temprature"/>°F</div></s:if>
				<%}else{ %>
					<div class="col-lg-3 col-md-3 col-xs-3"><b>Temp :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="Temprature"/>°F</div>
				<%} %> 
				<%if(loginInfo.isVermanh()){ %>
					<s:if test="spo2!=''"><div class="col-lg-3 col-md-3 col-xs-3"><b>SPO2 :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="spo2"/>%</div></s:if> 
				<%}else{ %>
					<div class="col-lg-3 col-md-3 col-xs-3"><b>SPO2 :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="spo2"/>%</div> 
				<%} %> 
				<%if(loginInfo.isVermanh()){ %>
					<s:if test="weight!=''"><div class="col-lg-3 col-md-3 col-xs-3"><b>Wt/Ht :</b><s:property value="weight"/>&nbsp;&nbsp;&nbsp;&nbsp;/<s:property value="height"/>&nbsp;&nbsp;&nbsp;&nbsp; kg/cm</div></s:if>
	   			<%}else{ %>
					<div class="col-lg-3 col-md-3 col-xs-3"><b>Wt/Ht :</b><s:property value="weight"/>&nbsp;&nbsp;&nbsp;&nbsp;/<s:property value="height"/>&nbsp;&nbsp;&nbsp;&nbsp; kg/cm</div>
				<%} %> 
				<%if(loginInfo.isVermanh()){ %>
	   			
	   			<%}else{ %>
	   			<div class="col-lg-3 col-md-3 col-xs-3" style="white-space: nowrap;"><b>BMI:</b><s:property value="bmi"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; kg/m2</div>
				<%} %> 
				<%if(loginInfo.isVermanh()){ %>
	   			<s:if test="pulse!=''"><div class="col-lg-3 col-md-3 col-xs-3" style="white-space: nowrap;"><b>Pulse :</b><s:property value="pulse"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;min</div></s:if>
	   			<%}else{ %>
				<div class="col-lg-3 col-md-3 col-xs-3" style="white-space: nowrap;"><b>Pulse :</b><s:property value="pulse"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;min</div>
				<%} %> 
	   			<s:if test="suagarfasting!=''">
	   				<div class="col-lg-3 col-md-3 col-xs-3"><b>Sugarfasting:&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="suagarfasting"/></div>
	   			</s:if>
	   			<s:if test="postmeal!=''">
	   				<div class="col-lg-3 col-md-3 col-xs-3"><b>Postmeal :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="postmeal" /></div> 
	   			</s:if>
   			 	<%if(loginInfo.isVermanh()){ %>
					<s:if test="sysbp!='' || diabp!=''"><div class="col-lg-3 col-md-3 col-xs-3" style="white-space: nowrap;"><b>Sys-BP/Dia-BP :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="sysbp"/>&nbsp;/<s:property value="diabp" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mmHg</div></s:if>
				<%}else{ %>
					<div class="col-lg-3 col-md-3 col-xs-3" style="white-space: nowrap;"><b>Sys-BP/Dia-BP :&nbsp;&nbsp;&nbsp;&nbsp;</b><s:property value="sysbp"/>&nbsp;/<s:property value="diabp" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mmHg</div>
				<%} %>
				
				<div class="clearfix"></div>
				</div>
			<%} %> 
			
			
			
			</div>  
			
			
<%if(!loginInfo.isMatrusevasang() && !loginInfo.isBalgopal()){ %>		
   <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" >	
    
			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-top: 660px">
			     <h4 style="text-align: center;"> सम्मती पत्र </h4>
			       
		              <span style="font-size: xx-small;">मी खाली सही करणार ,श्री /श्रीमती ......................................
                      .....................................(सम्मती देण्याचे नाव  ),खालील सम्मती पत्र लिहून देत आहे कि मला माझ्या भाषेमध्ये रुग्णावर होणाऱ्या तपासणीची  व उपचारांची संपूर्ण माहिती देण्यात आली  आहे .मी संपूर्ण शुद्धी मध्ये सहमती पत्र'
                        देत आहे कि ,मी रुग्णावर होणाऱ्या संपूर्ण तपासण्यासाठी बधिकारणासाठी शस्त्रक्रियेच्या प्रक्रियेसाठी व बाकी सर्व उपचारासाठी तयार आहे .मी डॉक्टरांना ही परवानगी देत आहे कि,डॉक्टर आपातकालीन परीस्ठीमध्ये रुग्णावरील 
                         उपचाराच्या पद्धतीत काही /संपूर्ण बदल करू शकतात.रुग्णावर होणाऱ्या तपासण्या व उपचार शिक्षक डॉक्टर आणि विद्यार्थी जे भिन्न विभागातून आहेत.त्याच्या देखरेखी खाली होईल याची मला संपूर्ण जाणीव आहे .
                         मी या बाबतीत स्वीकृती देत आहे कि ,उपचार दरम्यान व तपासणी करतेवेडी रुग्णाचे काही छायाचित्र माहिती ,चझचीत्र अथवा श -किरण घेण्यात येतिल व ते डॉक्टरांच्या अभ्यास हेतू अथवा प्रकाशना हेतू वापरता येतील.
                         
                      </span>
                      <br><br><br><br><br>
                      <span>रोगी /त्यांच्या नातेवाईकाची स्वाक्षरी</span>
                      <span style="float: right; margin-right: 22px;">साक्षदार</span>
                      <!-- <br><br><br><br><br><br><br> -->
		    </div>
	
			 <br>
             <br>
  </div> 
		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding: 0px;text-align: right;padding-top: 75px;">
				<%-- <p><%=emr.getPractitionerName() + " " + emr.getHeading()%>&nbsp;<%=emr.getLastModified()%></p> --%>
				<p  class="printmarginright"><s:property value="practitionerName"/></p>
				<p  class="printmarginright"><s:property value="dateTime"/></p>
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
   </div>
		
	<div class="row hidden-print">
		<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;padding:0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
					<s:if test="isfromdeptopd==''">
							<a href="addCompleteInfoRegistration" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Registration</a>
					</s:if>
					<input type="button" class="btn btn-primary savebtn savebigbtn"  value="Print" onclick="printpage()">
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>
	
	<%}else{ %>
	           
	        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding: 0px;text-align: right;padding-top: 75px;">
				<%-- <p><%=emr.getPractitionerName() + " " + emr.getHeading()%>&nbsp;<%=emr.getLastModified()%></p> --%>
				<p  class="printmarginright"><s:property value="practitionerName"/></p>
				<p  class="printmarginright"><s:property value="dateTime"/></p>
			</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>

	           
	  <div class="row hidden-print">
		<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10 col-xs-12 col-sm-12" style="padding-top: 10px;padding:0px;margin-top: 200px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding-left:0px;padding-right:0px;">
					<div class="form-group" style="margin-bottom: 3px;">
					<s:if test="isfromdeptopd==''">
							<a href="addCompleteInfoRegistration" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;" >Registration</a>
					</s:if>
					<input type="button" class="btn btn-primary savebtn savebigbtn"  value="Print" onclick="printpage()">
					</div>
				</div>
			</div>
		<div class="col-lg-1 col-md-1"></div>
		
	</div>        
	           
<%} %>



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
    
    var t="";
    function hidediv(){
    	var div= document.getElementById("rem");
    	 if (div.style.display !== "none") {
    	        div.style.display = "none";
    	    }
    	    else {
    	        div.style.display = "block";
    	    }
    }
  </script>



	<%-- <div style="font-size: 26px; font-weight: bold;"><s:property value = "clinicName"/></div>
		<s:if test="clinicOwner!='' ">
			<div style="font-size: 18px; font-weight: bold;"><s:property value = "clinicOwner"/>, <s:property value = "owner_qualification"/> </div>
		</s:if>
		<div style="font-size: 16px; font-weight: bold;">
		<s:iterator value="locationAdressList">
			<s:property value = "address"/> <br>
		</s:iterator>
		</div>
		<div style="font-size: 16px; font-weight: normal;">Tel/Fax:<s:property value = "landLine"/></div>
		<div style="font-size: 16px; font-weight: normal;">E: <s:property value = "clinicemail"/> 
		<s:if test="websiteUrl!='' ">
		W: <s:property value = "websiteUrl"/>
		</s:if>
		 </div> --%>

						
						