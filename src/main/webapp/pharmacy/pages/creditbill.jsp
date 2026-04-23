<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<html lang="en">
<%@ taglib prefix="s" uri="/struts-tags" %>

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
 padding: 0px;
    padding-top: 0px;
    border-bottom: 1px solid #ddd;
    background-color: rgba(71, 165, 118, 0.17);
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

      .print_special { border: none !important; font-size: 8px;} 
      
    .clinicname {
    font-size: 10px !important;
    font-weight: bold;
}


.bottext {

    font-size: 8px !important;
    font-weight: normal;
}
.clicniaddress {
    font-size: 8px !important;
    font-weight: bold;
}
p {
    margin: 0 0 8.5px;
    font-size: 8px !important;
}

.bottext {
    font-size: 10px !important;
    font-weight: normal;
}
label {
    font-size: 8px !important;
}

   .hfour{
 font-size: 8px !important;
} 
td, th {
    padding: 0;
    font-size: 8px !important;
}


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

    </style>
    
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
    <SCRIPT type="text/javascript" src="pharmacy/js/pharmacy.js"></SCRIPT>
    
</head>
<body oncontextmenu="return false">
    <div>
        <!-- /.row -->
         <div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getShowingname()%></li>
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
        <div>
<div class="col-lg-2 col-md-2 col-sm-2"></div>
            <div class="col-lg-8 col-md-12 col-xs-12 col-sm-12 print_special">
                <div class="">
                    <div class="">
                    <%
								LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   			%>
                        <div class="row marbot10">
                            
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border-bottom: 1px solid #ddd;">
                            
                                <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hadsetma">
                                	 	<div class="clinicname"><s:property value="clinicName"/>
                                	 	<div class="cliqualif hidden">
												<s:property value="fullname"/>												
										</div>
									<div class="clicniaddress">
										<s:property value="clinicaddress"/>
									</div>
									<div class="bottext mabset">Tel/Fax:<s:property value="landLine"/>, E: <s:property value="email"/>,
										W: <s:property value="websiteUrl"/><br>
									 </div>
                                	 </div>
                                </div>
                                
                        </div>
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
                                	<%Client client = (Client)session.getAttribute("clientinfo"); %>
                                	
                                	<div class="col-lg-4 col-xs-4 col-md-4 col-sm-4">
                                		<p><b>BILL NO : </b> #<s:property value="billno"/></p>
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
                                		<p><b>NAME :</b> <%=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()%></p>
                                	
                                	</div>
                                	
                                <div class="col-lg-4 col-xs-4 col-md-4 col-sm-4">
                                	<p><b>MOBILE :</b> <%=client.getMobNo()%></p>
                                	<p><b>ADDRESS :</b> <s:property value="wardname"/></p>
                                	<p><b>DATE :</b> <s:property value="commencing"/> <s:property value="invoiceTime"/> </p>
                                		
                                	<p><b>DR. NAME :</b> <s:property value="practitionerName"/></p>
                                	</div>
                                	
                                	
                                	  <div class="col-lg-4 col-xs-4 col-md-4 col-sm-4">
                                
                                	<s:if test="ipdoropd!=null">
                                		<s:if test="ipdoropd>0">
                                			<p><b>WARD/BED :</b> <s:property value="wardbed"/> </p>
                                		</s:if>
                               		</s:if>
                                	<p><b>PHARMACY :</b> <s:property value="location"/></p>
                                	<p><b>Place :</b> <s:property value="place"/></p>
                                	<p><b>Tax paid on Invoice :</b> Rs.<s:property value="gst" /> </p>
                                	</div>
                                	
                                	
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">SEX/AGE</label>
										    <p><%=client.getGender() %>/<%=client.getAge() %></p>
								  		</div>
									</div>
                                </div>
                     </div>
                        <div class="row rowblank">
                            <table class="" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback">
 										 <th class="" style="width: 4%;">Sr.No</th>                                         
                                        <th style="width: 26%;padding: 2px 3px 2px 0px;">Name of Drug</th>
                                        <th class="hidden" style="width: 4%;">Pkg</th>
                                        <th  class="hidden" style="width: 5%;padding: 2px 3px 2px 0px;">Mfg</th>
                                        <th style="width: 9%;padding: 2px 3px 2px 0px;">Batch No</th>
                                        <th style="width: 9%;padding: 2px 3px 2px 0px;">HSN No</th>
                                        <th style="width: 8%;padding: 2px 3px 2px 0px;">Exp. Dt</th>
                                        <th style="width: 6%;text-align:center;padding: 2px 3px 2px 0px;">GST</th>
                                         <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">Unit Price</th>
                                        <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">Qty</th> 
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;" class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <%int i=0; %>
                                
                                	  <tbody>
                                	  <s:iterator value="medicineChargeList">
                                    	<tr>
                                    	<td><%=(++i) %></td>
                                        <td><s:property value="mdicinenametxt"/></td>
                                        <td class="hidden">6</td>
                                        <td class="hidden"><s:property value="mfg"/></td>
                                        <td><s:property value="batch_no"/></td>
                                         <td><s:property value="hsnno"/></td>
                                        <td><s:property value="expiry_date"/></td>
                                        <td style="text-align:center;text-transform: uppercase;"><s:property value="vat"/>%</td>
                                        <td style="text-align: center;"><s:property value="sale_price"/></td>
                                        <td style="text-align: center;"><s:property value="saleqty"/></td>
                                        <td class="text-right">Rs.<s:property value="total"/></td>
                                    </tr>
                                     </s:iterator>

                                </tbody>
                            </table>
                            <div class="" style="border-top: 1px solid #000;padding-left: 0px;">
                            
                            <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;padding-top: 0px;border-bottom: 1px dashed #555;">
                            		<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left:0px;">
                           <div class="text-left">
                            	<div class="" style="">
                            	<p class="" style="margin:0px;"><b>D.L.No</b> <span>: <s:property value="dlno"/></span></p>
								<p class="" style="margin:0px;"><b>GSTIN</b> <span>: <s:property value="tinno"/></span></p>
								<p class="hidden" style="margin:0px;"><b>Prescription ID</b> <span>: <s:property value="selectedid" /></span></p>
                            	<p class="hidden-print" style="margin:0px;"><b>Payment</b> <span>: <s:property value="paymode"/></span> 
                            	 
                            	      <s:if test="cardBill!=''">
                            	      	(<s:property value="cardBill"/>)
                            	      </s:if>
                            	 </p>
                            	 <p  class="hidden-print" style="margin:0px;">	
                            	 	<span>demo<br>date/time</span>
                            	 </p>
                            	
                            </div>
                             
                            </div>
                           
                           </div>
                           <div class="col-lg-6 col-md-6 col-xs-6" style="padding-right:0px;">
                           
                           <div class="text-right">
                            	<div class="" style="">
                            	<h4 class="hfour">Sub Total : Rs.<s:property value="subtotal"/></h4>
                            	<h4 class="hfour" style="color: #868686;">Discount(%) : Rs.<s:property value="discount"/></h4>
                            	<h4 class="hidden" style="font-size: 13px;">Vat : Rs.<s:property value="vat"/></h4>
                            	<h4 class="hfour">CGST  : Rs.<s:property value="cgst"/></h4>
                            	<h4 class="hfour">SGST  : Rs.<s:property value="sgst"/></h4>
                            	
								<s:if test="refundamt!=0">
									<h4 class="hfour">Refund: Rs.<s:property value="refundamt"/></h4>
								</s:if>     
								<h4  class="hfour hidden-print">Balance : Rs.<s:property value="balance"/></h4>
                            	<h4 class="hfour" style="font-weight: bold;color:green;">Total : Rs.<s:property value="total"/> <input type="hidden" value="<s:property value="total"/>" id="wordtot"  /> </h4>
                            	<h4 class="hfour hidden-print" style="font-weight: bold;color:green;">Received : Rs.<s:property value="payamt"/> </h4>
                            	<h4 class="hidden-print" style="font-weight: bold;color:green;font-size: 11px;">In words: <span id="words"> </span> Only </h4>
                            </div>
                            </div>
                           </div>
                            </div>
						</div>
                        </div>
                        
                        

                        <div class="row marbot10">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 marleft16">
                                	<ul>
									  <li><s:property value="instruction1"/></li>
									  <li><s:property value="instruction2"/></li>
									  <li><s:property value="instruction3"/></li>
									  <li><s:property value="instruction4"/></li>
									</ul>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="padding:0px;padding-top: 10px;color: #bbb;">
                                        <span>Sign Of Q.P</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

									<div class="form-group text-right hidden-print">
											<%if(loginInfo.isSMS_authority() || loginInfo.getUserType()==2) {%>
			                            		<a type="button" class="btn btn-primary btn-lg"  title="Print" onclick="sendSms(<s:property value="billno"/>)">Send SMS</a>
                                    		<%} else { %>
			                            	   <a type="button" class="btn btn-primary btn-lg hidden"  title="Print" onclick="sendSms(<s:property value="billno"/>)">Send SMS</a>
                                    		<%} %>
									    <a type="button" class="btn btn-primary btn-lg"  title="Print" onclick="printpage()">Print</a>
                                    </div>
            </div>
				<div class="col-lg-2 col-md-2 col-sm-2"></div>
        </div>
        <!-- /.row -->

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