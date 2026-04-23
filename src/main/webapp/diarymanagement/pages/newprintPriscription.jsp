
<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>

<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>
<head>
    <title>priscription</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="_assets/newtheme/css/main.css">
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link href="common/css/printpreview.css" rel="stylesheet" />
   <link href="https://fonts.googleapis.com/css2?family=Share:wght@700&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap" rel="stylesheet">
   <!--  <script src="assets/js/priscription/jquery-2.1.1.min.js"></script>
    <script src="assets/css/priscription/bootstrap-3.3.1-dist/dist/js/bootstrap.min.js"></script>
    <script src="//google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script> -->
    
<style>
.thermal{
        font-family: 'Share' !important;
    line-height: 12px !important;
    font-size: 12px;!important
    }
    .thermal1{
        font-family: 'Share Tech Mono', monospace !important;
    line-height: 12px !important;
    font-size: 12px;!important
    }
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
            font-size: 11px;
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
    padding: 2px 5px 2px 15px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px;
    border-right: none !important;
    border-left: none !important;
}
.form-group {
    margin-top: 0px !important;
}
.cliqualif {
    font-size: 11px !important;
    font-weight: bold;
}
 @media print
{
body {
    font-size: 11px !important;
}
.thermal{
        font-family: 'Share' !important;
    line-height: 12px !important;
    font-size: 12px;!important
    }
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 2px 2px 15px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 11px !important;
    border-right: none !important;
    border-left: none !important;
}

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
	    margin: 0 0 2.5px !important;
	    font-size: 11px !important;
	}
	
	.form-group {
    margin-bottom: 0px !important;
}
.setotas {
    padding: 0px;
    text-align: right;
    color: #008000 !important;
    font-size: 11px;
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

}
    </style>
    <style>
    .table.table {
    color: #000;
}

    body {
    	color: #000;
	}
	.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
    height: 155px;
}
.clinicname {
    font-size: 20px;
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
}
</style>
</head>
<body>

<div class="row">
<div class="col-lg-1 col-md-1"></div>
	<div class="col-lg-10 col-md-10 col-xs-12">
				<div class="row" >
					<%-- <div id="newpost"  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
						
					</div> --%>

				<div class=" letterheadhgt" style="height: 110px;">
					<div id="newpost"
						class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
							style="padding-left: 0px; padding-right: 0px;">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp"%>
						</div>
					</div>
				</div>
			</div>
				<div style="height: 20px;" class="clearfix"></div>
				<div class="row printinvestigationbackcolor">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" >
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
							
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">PRESCRIPTION</b>
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
					<%Client client = (Client)session.getAttribute("clientinfo"); %>
                                	<% Clinic clinic =(Clinic) session.getAttribute("clinicinfo"); %>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
						
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">UHID</label><span>:<%=client.getAbrivationid()%></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Patient Name</label><span>: <%=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()%></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Address</label><span>: <%=client.getAddress() + ", " + client.getTown() + ", " + client.getPostCode() %></span>
						</div>
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="form-group marbot3" style="margin-bottom: 3px;">
							<label for="inputEmail3" class="control-label">Date</label><span>: <% String data = clinic.getCurDateTime(); 
										    	  String[] data1 = data.split("-");
										    	  String [] date =data1[2].split(" ");
										    	  String date2 = date[0]+"-"+data1[1]+"-"+data1[0]+"  "+date[1]   ;
										    	%>
										    	<%
										    		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
										    		Calendar cal = Calendar.getInstance();
										    		String date3 = dateFormat.format(cal.getTime());
										    	%>
										    	<%=date2%></span> 
						</div>
						
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Gender/Age</label><span>: <%=client.getGender() %>/<%=client.getAge1()%></span>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 1px;">
							<label for="inputEmail3" class="control-label">Ward/Bed</label><span>: <s:property value="wardname"/>/<s:property value="bedname"/></span>
						</div>
						
						
						
					</div>
					</div>
				</div>
				
				<div class="row">
                            <table class="table table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <div class="row">
                               
                                
                                    <tr class="tableback">
										<th style="width:1%;">Sr.no</th>
                                        <th class="med9" style="display:none;">Type</th>
                                        <th class="hidden">Qty</th>	  
                                        <th style="width:10%;">Medicine Name</th>
                                       <%if(loginInfo.getOutoprisc()==1){ %>
                                        	<th style="width:5%;">Dose</th>
                                        <%} %>
                                         <th style="width:6%;">Frequency</th>
                                         <th style="width:1%;">Duration (in Days)</th>
                                         <th style="width:5%;">Total Qty(RO)</th>
                                        <%--  <s:if test="Isfromipd==1">
                                         	<s:if test="masterdosestatus==true">
                                        		<th style="width:5%;">Dose</th>
                                        	</s:if>
                                        </s:if> --%>
                                        
                                         
                                         
                                         
                                        <th class="hidden" style="width:5%;">Strength</th>
                                        
                                        
                                        <th class="hidden">Qty</th>
                                       
                                        <%-- <s:if test="prisctimestatus==true"> --%>
                                        <th style="width:7%;"> Remark Routes</th>
                                        <th style="width:5%;">Frequency Note</th>
                                        <%-- </s:if> --%>
                                        <th class="hidden">Pkg</th>
                                        <th class="hidden">Mfg</th>
                                        <th class="hidden">Batch No</th>
                                        <th class="hidden">Exp. Dt</th>
                                      
                                      <%if(loginInfo.getOutoprisc()!=1){ %>
                                        <s:if test="priscunitstatus==true">
                                        	<th style="width:5%;">Strength</th>
                                        </s:if>
                                        <%} %>
                                        
                                      	 
                                      	 <%if(loginInfo.getOutoprisc()!=1){ %>
                                      	 <s:if test="Isfromipd==1">
                                      	 	<s:if test="isipdtimeshow==1">
	                                         	<th style="width:3%;">Time</th>
	                                         	<th style="width:3%;">Sign</th>
	                                         	<th style="width:3%;">Time</th>
	                                         	<th style="width:3%;">Sign</th>
	                                         	<th style="width:3%;">Time</th>
	                                         	<th style="width:3%;">Sign</th>
                                         	</s:if>
                                         </s:if>
                                         <%} %>
                                      	 
                                      	 
                                         <%-- <s:if test="priscreamrkstatus==true"> --%>
                                         <th style="width:10%;">Time Remark</th>
                                         <%-- </s:if> --%>
                                    </tr>
                                </thead>
                                <%if(session.getAttribute("authorisedPrisc")!=null) { ArrayList<Priscription>list = (ArrayList<Priscription>)session.getAttribute("authorisedPrisc");%>
                                	  <tbody>
                                	  <%int i =1; %>
                                	  <%for(Priscription priscription : list) {%>
                                    <tr>
                                    	<td><%=i %></td>
                                        <td style="display:none;"><%=priscription.getPrisctype() %></td>
                                        <td class="hidden">10</td>
                                        <td class="text-uppercase"><%=priscription.getMdicinenametxt() %></td>
                                        
                                        <%if(loginInfo.getOutoprisc()==1){ %>
                                        	 <td><%=priscription.getDddose() + " " + priscription.getUnitextension() %></td>
                                        <%} %>
                                       
                                        
                                        <td> <%=priscription.getRegional() %> </td>
                                       
                                        
                                        <td class="hidden"><%=priscription.getPriscfreq() %></td>
                                         <%if(loginInfo.getOutoprisc()!=1){ %>
                                        <s:if test="priscunitstatus==true">
                                        	<td><%=priscription.getUnit()%><%=priscription.getUnitextension()%></td>
                                        </s:if>
                                        <%} %>
                                         
                                       <%--   <s:if test="Isfromipd==1">
                                         	<s:if test="masterdosestatus==true">
                                         		<td><%=priscription.getMasterdose() %></td>
                                         	</s:if>
                                         </s:if>
 --%>                                         
                                         <%if(!priscription.getPriscdays().equals("")){ %>
                                          <td><%=priscription.getPriscdays()%> Days</td>
                                          <%}else{ %>
                                          <%=priscription.getPriscdays()%>
                                          <%} %>
                                         <td><%=priscription.getPriscqty() %></td>
                                          <td ><%=priscription.getDosenotes() %></td>
                                        <td class="hidden"><%=priscription.getReqqty() %></td>
                                        
                                        
                                       <%--  <s:if test="prisctimestatus==true"> --%>
                                        	<td class=""><%=priscription.getPrisctimename()%></td>
                                        <%-- </s:if> --%>
                                        
                                         
                                         
                                        <td class="hidden"><%=priscription.getPkg()%></td>
                                        <td class="hidden"><%=priscription.getMfg()%></td>
                                        <td class="hidden"><%=priscription.getBatch_no()%></td>
                                        <td class="hidden"><%=priscription.getExpiry_date()%></td>
                                       
                                       
                                        
                                        <td class="hidden"><%=priscription.getPrisctotal() %></td>
                                       
                                      
                                       
                                        
                                        <%if(loginInfo.getOutoprisc()!=1){ %>
                                       <s:if test="Isfromipd==1">
                                        	<s:if test="isipdtimeshow==1">
	                                         	<td ><%=priscription.getIpdtimeshow1() %></td>
	                                         	<td></td>
	                                         	<td ><%=priscription.getIpdtimeshow2() %></td>
	                                         	<td></td>
	                                         	<td ><%=priscription.getIpdtimeshow3() %></td>
	                                         	<td></td>
	                                         </s:if>
                                         </s:if>
                                         <%} %>
                                        
                                        
                                        <%-- <s:if test="priscreamrkstatus==true"> --%>
                                         	<td><%=priscription.getPriscindivisualremark().toString()%></td>
                                        <%-- </s:if> --%>
                                    </tr>
                                  

                                </tbody>
                                	<%i++; %>
                                	 <%} %>
                                	
                                <%} %>
                              
                            </table>
                        </div>
                        <%Priscription pr = (Priscription)session.getAttribute("parentpriscdata"); %>
                       <div class="row">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
                                    <p><strong>Followup After</strong>: <%=pr.getFollowupsqty() + " " + pr.getFollowupstype()  %></p>
                                    <p><strong>ADVICE</strong>: <%=pr.getPriscadvoice() %></p>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px;">
                                      <p style="text-align: right;"><strong> </strong> <s:property value="practitionerName"/> </p>
                                    <p style="text-align: right;"><strong></strong> (<s:property value="qualification"/>) </p>
                                    <s:if test="drregno!=null">
                                		<p style="text-align: right;">Reg. No:<strong></strong> <s:property value="drregno"/> </p>
                                     </s:if>
                                    
                                        <br><br>
                                    <div class="form-group hidden-print">
                                    	<a href="#" onclick="printpage();" class="btn btn-primary savebtn savebigbtn" style="line-height: 45px;width: 30%;" title="Print">Print</a>
                                    </div>
                                </div>
                            </div>
                        </div>
	
	</div>
	<div class="col-lg-1 col-md-1"></div>
</div>

   

    <script>
        function myPrint() {
            window.print();
        }
        
       /*  var t = 0;
        window.onload = function(){
        	
        	//window.location.reload();
        } */
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
 

</body>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
</html>
