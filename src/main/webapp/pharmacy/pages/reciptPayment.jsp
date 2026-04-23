<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link href="common/css/printpreview.css" rel="stylesheet" />
   
   <!--  <script src="assets/js/priscription/jquery-2.1.1.min.js"></script>
    <script src="assets/css/priscription/bootstrap-3.3.1-dist/dist/js/bootstrap.min.js"></script>
    <script src="//google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script> -->
    
   <style>
        hr {
            margin-top: 0px;
            margin-bottom: 10px;
        }
        .marbot10 {
            margin-bottom: 0px;
        }
        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            padding: 1px !important;
            vertical-align: top;
            border-top: 0px solid #DDD !important;
            
        }
        .marleft16 {
            margin-left: -16px;
        }
        .docsig {
            float: right;
            margin-right: -30px;
        }
     .rx{
     width: 39%;
    margin-bottom: 5px;
     }  
     .pristitle{
         margin-left: -51px;
    font-size: 14px;
     }
     .med21 {
    width: 30% !important;
}
.btn-lg, .btn-group-lg>.btn {
    padding: 8px 14px;
    font-size: 17px;
    line-height: 7px;
    border-radius: 0px;
}
.hadsetma{
 padding-left: 0px;
    margin-top: 0px;
}
.mabset{
    margin-bottom: 5px;
}
.panamse{
 padding-top: 5px;
    border-bottom: 1px solid #6699cc;
    padding-bottom: 5px;
    background-color: rgba(91, 192, 222, 0.16);
}
.form-group {
    margin-bottom: 9px !important;
}
p {
    margin: 0 0 2px !important;
}
.print_special { border: 1px solid #43b9be; } 
@media print
{
.marleft16 {
    margin-left: 0px !important;
    font-size: 9px !important;
}
.hfour {
    font-size: 9px !important;
}

.panamse {
    padding-top: 5px;
    border-bottom: 1px solid #6699cc;
    padding-bottom: 5px;
    background-color: rgba(91, 192, 222, 0.16) !important;
}
     
.clinicname {
    font-size: 15px !important;
    font-weight: bold;
}
td, th {
    padding: 0;
    font-size: 9px !important;
}
.logsetma{
	margin-top: 6px !important;
}
.clicniaddress {
    font-size: 10px !important;
    font-weight: Normal !important;
}
p {
    margin: 0 0 2px !important;
    font-size: 9px;
}
.tableback {
    background-color: #cccccc !important;
    color: #fff !important;
    font-style: normal;
}
.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #ccc !important;
    color: #000!important;
}
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 0px 5px 0px 5px !important;
    vertical-align: top;
    line-height: 12px !important; 
    border-top: 0px solid #DDD !important;
    font-size: 9px !important;
}
.setase{
	padding-right: 5px !important;
}
}

.setase{
	    padding-right: 5px;
}
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 0px 5px 0px 5px !important;
    vertical-align: top;
    border-top: 0px solid #DDD !important;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 7.5px;
    padding: 0px 0px 0px 15px !important;
}
.sepaymeth{
     position: absolute;
    margin-top: -25px;
}

.hfour{
 font-size: 12px;
}
.datest{
 padding: 0px;
    margin-top: 10px;
    border-bottom: 1px dashed #ddd;
    margin-bottom: 0px;
    text-align: right;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 7.5px;
    padding: 0px 0px 0px 0px !important;
    list-style: none;
}
h4, .h4, h5, .h5, h6, .h6 {
    margin-top: 3.5px;
    margin-bottom: 4.5px;
}
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
    line-height: 40px;
}

    </style>
    <script type="text/javascript">
    window.onload = function () {
    	document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
    	
        }
    </script>
    
    
    <style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>

</head>
<body>
<div class="row">
<div class="col-lg-1 col-md-1"></div>
<div class="col-lg-10 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 15px;text-transform: uppercase;color: black;font-size: 13px;">
                <div class="">
                    <div class="">
                    
                        <div class="">
                        <s:if test="shownavigation==1">
                        	<%-- <div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
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
							</s:if>
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 2px solid #6699cc;">
	                            <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
	                            	<img class="img-responsive logsetma" src="liveData/clinicLogo/<s:property value="clinicLogo"/>"/>
	                            </div>
                                <div class="col-lg-10 col-md-70 col-xs-10 col-sm-10 hadsetma">
                                	 	<div class="clinicname"><s:property value="clinicName"/>
									<div class="clicniaddress">
										<s:property value="clinicaddress"/>
									</div>
									<div class="bottext mabset">
									    Phone:<s:property value="landLine"/>, Email: <s:property value="email"/>,
										Website: <s:property value="websiteUrl"/><br>
									 </div>
                                	 </div>
                                </div>
                                
                                
                        </div>
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse" style="text-transform: uppercase;">
                        		<div class="col-lg-12 col-md-12 col-xs-12" style="margin-bottom: 7px;">
                        			<h5 class="" style="margin:0px;color: chocolate;text-align: center;font-size: 13px;">BALANCE CLEAR RECIEPT</h5>
                        		</div>
                        
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
                                		<p><b>RECIEPT NO :</b> <s:property value="regno"/></p>
                                		<p><b>UHID :</b> <s:property value="abrivationid"/></p>
                                		<s:if test="ipdoropd!=null">
                                			<s:if test="ipdoropd>0">
                                				<p><b>OPD/IPD :</b> 
                                				IPD<s:if test="thirdPartyCompanyName==0"></s:if>
                                				<s:else>
													(<s:property value="thirdPartyCompanyName"/>)                            				
                                				</s:else>
                                			</s:if>
                                			<s:else>
                                				<p><b>OPD/IPD :</b> 
                                				OPD<s:if test="thirdPartyCompanyName==0"></s:if>
                                				<s:else>
													(<s:property value="thirdPartyCompanyName"/>)                            				
                                				</s:else>
                                			</s:else>
                                			</s:if>
                                		</p>
                                		<p><b>NAME :</b> <s:property value="fullname"/> </p>
                                		<p><b>MOBILE :</b> <s:property value="mobno"/></p>
                                		<p><b>ADDRESS :</b><s:property value="address"/></p>
                                	</div>
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
	                                	<p><b>DATE :</b> <s:property value="dateTime"/></p>
	                                	<p><b>DR. NAME :</b> <s:property value="practitionerName"/></p>
	                                	<p><b>PHARMACY :</b> <s:property value="locationName"/></p>
	                                	<p><b>PLACE :</b> <s:property value="place"/></p>
                                	</div>
                                	<s:if test="shownavigation==1">
	                                	<div class="form-group text-right">
											<a class="btn btn-primary btn-lg" href="salepriscPharmacy"  title="New Sale" >New Sale</a>
											<a class="btn btn-primary btn-lg" href="Pharmacy"  title="Pharmacy Dashboard" ><i class="fa fa-users" ></i> </a>
	                        			</div>
                        			</s:if>
                                </div>
                     </div>
                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                        <table class="table table-responsive table-striped" cellspacing="0" width="100%" style="margin-bottom: 0px;">
						          <thead>
						           <tr>
						            <th>Sr</th>
						            <th>Bill ID</th>
						            <th>Bill Date</th>
						            <th>Bill TYPE</th>
						            <th style="text-align:right;">Balance</th>
						            <!-- <th style="text-align:right;">Discount</th> -->
						            <th style="text-align:right;">Paid Amt</th>
						           </tr>
						          </thead>
							       <tfoot>
							        <tr style="color: green;">
							        	<td></td>
							        	<td></td>
							        	<td></td>
							        	<td></td>
							        	<td></td>
							        	<td></td>
							        	<!-- <td></td> -->
							        </tr>
							      </tfoot>
						          <tbody>
						           <% int i=0; %>
						            <s:iterator value="clearBillList">
						          	<tr>
						          		<td><%=(++i) %></td>
						          		<td><s:property value="billno"/></td>
						          		<td><s:property value="dateTime"/></td>
						          		<td><s:property value="paymode"/></td>
						          		<td style="text-align:right;">Rs.<s:property value="balance"/></td>
						          		<%-- <td style="text-align:right;">Rs.<s:property value="discount"/></td> --%>
						          		<td style="text-align:right;">Rs.<s:property value="payment"/></td>
						          		<%-- <td style="text-align:right;">Rs.<s:property value="total"/></td> --%>
						          	</tr>
						          	</s:iterator>
						          </tbody>
						         </table>
                            
                                
                                
                            <div class="" style="padding-left: 0px;">
                            <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;padding-top: 5px;">
                            		<div class="col-lg-6 col-md-6 col-xs-6">
                           <div class="text-left">
                            	<div class="" style="">
                            	<p style="margin:0px;"><b>D.L.No</b> <span>: <s:property value="dlno"/></span></p>
								<p class="" style="margin:0px;"><b>GSTIN</b> <span>: <s:property value="tinno"/></span></p>
								<p style="margin:0px;"><b>Payment</b> <span>: <s:property value="paymode"/>
								  <p style="margin:0px;"><b>Discount</b> <span>: <s:property value="discount"/>
								 
								</span></p>
                            	  <br>
                            	 <p style="margin:0px;">	
                            	 	<b>Note</b> : <span> <s:property value="notes"/> </span>
                            	 </p>
                            </div>
                            </div>
                           
                           </div>
                           <div class="col-lg-6 col-md-6 col-xs-6 setase" style="padding-right:0px;">
                           <div class="text-right">
                            	<div class="" style="">
                            	<h4 class="hfour" style="font-weight: bold;">Total : Rs.<s:property value="total"/></h4>
                            	<h4 class="hfour" style="font-weight: bold;">Discount : Rs.<s:property value="discount"/></h4>
								<h4 class="hfour" style="font-weight: bold;color:green;">Received : Rs.<s:property value="payamt"/> <input type="hidden" value="<s:property value="payamt"/>" id="wordtot"> </h4>
                            	<h4 class="hfour" style="font-weight: bold;color:green;font-size: 11px;">In words: <span id="words">One Hundred Sixteen </span> Only </h4><br>
                            	<p style="margin:0px;">	
                            	 	<b>Received by</b> : <span><s:property value="createdby"/></span>
                            	 </p>
                            	 <p style="margin:0px;">	
                            	 	<b>Printed by</b> : <span><s:property value="printedby"/></span>
                            	 </p>
                            </div>
                            </div>
                           </div>
                            </div>
						</div>
                        </div>
                        
                        

                        <div class=" marbot10">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;border-top: 1px dashed #ddd;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 hfour" style="padding: 0px;">
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="margin-top: 20px;color: #bbb;padding: 0px;">
                                        <span class="hfour">Sign Of Q.P</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

									<div class="form-group text-right hidden-print">
									    <a type="button" class="btn btn-primary btn-lg savebigbtn" title="Print" onclick="printpage()">Print</a>
                                    </div>
            </div>
<div class="col-lg-1 col-md-1"></div>
</div>

 <script>
        function myPrint() {
            window.print();
        }
        
        window.onload = function(){
        	
           var t=  document.getElementById("wordtot").value;
           document.getElementById("words").innerHTML=convertNumberToWords(t);
           	
        };
    </script>
    <script>
	function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string;
}
</script>  
 

</body>



</html>