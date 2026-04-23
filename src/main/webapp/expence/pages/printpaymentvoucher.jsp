<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <link rel="icon" href="assets/img/favicon.ico">
    <title>Advance Practice Management</title>
  
	<script type="text/javascript" src="expence/js/expencelist.js"></script>
	<script type="text/javascript" src="expence/js/expencemanagement.js"></script>
   
   	<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link href="common/css/printpreview.css" rel="stylesheet" />
   
    <!--Bootstrap select css-->
    <link href="_assets/css/priscription/bootstrap-select.css" rel="stylesheet" />

   
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
            border-top: 1px solid #DDD !important;
            border-bottom: 1px solid rgb(183, 180, 180) !important;
                border: solid 1px #b7b4b4 !important;
            
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
    border-bottom: 0px solid #000;
    padding-bottom: 5px;
    background-color: rgba(91, 192, 222, 0.16);
}
.form-group {
    margin-bottom: 9px !important;
}
p {
    margin: 0 0 2px !important;
}
.print_special { border: 1px solid #339966; } 
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
    border-bottom: 1px solid #000;
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
    border-bottom: #000;
    background-color: #ccc !important;
    color: #000!important;
}
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 0px 5px 0px 5px !important;
    vertical-align: top;
    line-height: 12px !important; 
    border-top: 1px solid #DDD !important;
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
    border-top: 1px solid #DDD !important;
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
    border-bottom: 1px dashed #000;
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
    background-color: #339966 !important;
    margin-bottom: 15px;
    line-height: 40px;
}
.expset{
     margin: 8px 0px 0px 0px;
    color: #333;
    text-align: center;
    font-size: 18px;
    border-bottom: 2px solid #b9d9e2;
    padding-bottom: 8px;
}
    </style>

</head>

<script>
 $(document).ready(function() {

  $("#date").datepicker({

   dateFormat : 'dd/mm/yy',
   yearRange: yearrange,
   minDate : '30/12/1880',
   changeMonth : true,
   changeYear : true

  }); 
  
  
  $("#todate").datepicker({

	   dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true

	  }); 
	  
  
  $("#fromdate").datepicker({

	  dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true
	  }); 
	  
  
  
  
  $("#edtdate").datepicker({

	   dateFormat : 'dd/mm/yy',
	   yearRange: yearrange,
	   minDate : '30/12/1880',
	   changeMonth : true,
	   changeYear : true

	  }); 
  
  
 
  
      
  
 });

</script>

<body>

<div class="container">
 
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;text-transform: uppercase;color: black;font-size: 13px;">
                
                <div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
                <div class="">
                    <div class="">
                        <div class="">
                        <div class="col-lg-12 col-md-12 col-xs-12 expset">
                        			<h5 class="" style="margin:0px;text-align: center;font-size: 18px;"> <s:property value="epayment"/> Voucher</h5>
                        		</div>
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse" style="text-transform: uppercase;">
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
                                		<p><b>VOUCHER NO :</b> <!-- 00 --><s:property value="parentid"/></p>
                                		<p><b>NAME :</b> <s:property value="paymantto"/> </p>
                                	</div>
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6" style="padding: 0px;">
	                                	<p><b>DATE :</b> <s:property value="caldate"/></p>
	                                	<p><b>PLACE :</b> <s:property value="place"/></p>
                                	</div>
                                </div>
                     </div>
                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                        <table class="table table-responsive table-striped" cellspacing="0" width="100%" style="margin-bottom: 0px;">
						          <thead>
						           <tr>
						            <th style="width: 5%;">Sr</th>
						            <th style="width: 51%;">Expense Type</th>
						            <th style="width: 29%;">Note</th>
						            <s:if test="showcd==1">
						            	<th style="">Credit</th> 
						            </s:if>
						           
						            <th style="text-align:right;">Amount</th>
						           </tr>
						          </thead>
							       <tfoot>
							        <tr style="color: green;">
							        	<td></td>
							        	<td></td>
							        	<td></td>
							        	 <s:if test="showcd==1">
						            		<td style="text-align:right;"><%=Constants.getCurrency(loginfo)%><s:property value="ctotal"/></td> 
						                </s:if>
							        	 
							        	<td style="text-align:right;"><%=Constants.getCurrency(loginfo)%><s:property value="amount"/></td>
							        </tr>
							      </tfoot>
						          <tbody>
						          <%int cnt = 1; %>
						          <s:iterator value="expenceList">
						          	<tr>
						          		<td><%=cnt %></td>
						          		<td><s:property value="category"/></td>
						          		<td><s:property value="comments"/></td>
						          		<%-- <td><s:property value="paidby"/></td> --%>
						          		<s:if test="showcd==1">
						          			<td style="text-align:right;"><%=Constants.getCurrency(loginfo)%><s:property value="credit"/><input type="hidden" value="<s:property value="amount"/>" id="wordtot"  /></td>
						          		</s:if>
						          		
						          		<td style="text-align:right;"><%=Constants.getCurrency(loginfo)%><s:property value="amount"/><input type="hidden" value="<s:property value="amount"/>" id="wordtot"  /></td>
						          	</tr>
						          	<%cnt++; %>
						          	</s:iterator>
						          </tbody>
						         </table>
                            
                                
                                
                            <div class="" style="padding-left: 0px;">
                            <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;padding-top: 5px;">
                            		
                            		
                            	 
                           
                           
                           
                         
                           <div class="text-right">
                            	<div class="" style="">
                            	<h4 class="hfour" style="font-weight: bold;color:green;font-size: 11px;">In words: <s:property value="totalinword"/> Only </h4><br>
                            	
                            </div>
                            </div>
                            
                            
                            
                             <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 padingleftrightzeroi">
                             
                              
                              <b>Status</b> : <s:property value="status"/>
                              
                             
                             </div> 
                             
                             <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 padingleftrightzeroi">
                             
                              
                              <b>Received by</b> : 
                              
                             
                             </div> 
                             
                             
                             <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 padingleftrightzeroi">
                             
                              
                               <b>Prepared by</b> : <s:property value="printby"/> | <s:property value="printdate"/>
                              
                             
                             </div>
                          
                            </div>
						</div>
						
						<div class=" marbot10">
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;"></div>
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 hfour" style="padding: 0px;">
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="margin-top: 20px;color: #bbb;padding: 0px;">
                                        <span class="hfour">(Checked By) Sign</span>
                                </div>
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
