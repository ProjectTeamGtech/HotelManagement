<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
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
</head>
<body>
    <div>
        <!-- /.row -->
        <div>
<div class="col-lg-2 col-md-2 col-sm-2"></div>
            <div class="col-lg-8 col-md-12 col-xs-12 col-sm-12 print_special">
                <div class="">
                    <div class="">
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
                                	
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
                                		<p><b>BILL NO : </b> #<s:property value="billno"/></p>
                                	<p><b>NAME :</b> <%=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()%></p>
                                	<p><b>MOBILE :</b> <%=client.getMobNo()%></p>
                                	</div>
                                	<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
                                	<p><b>DATE :</b> <s:property value="commencing"/></p>
                                		<p><b>LOCATION :</b> <s:property value="wardname"/></p>
                                	<p><b>DR. NAME :</b> <s:property value="practitionerName"/></p>
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
                                          
                                        <th style="width: 26%;padding: 2px 3px 2px 0px;">Name of Drug</th>
                                        <th class="hidden" style="width: 4%;">Pkg</th>
                                        <th  class="hidden" style="width: 5%;padding: 2px 3px 2px 0px;">Mfg</th>
                                        <th style="width: 9%;padding: 2px 3px 2px 0px;">Batch No</th>
                                        <th style="width: 8%;padding: 2px 3px 2px 0px;">Exp. Dt</th>
                                        <th style="width: 6%;text-align:center;padding: 2px 3px 2px 0px;">Shelf</th>
                                        <th style="width: 4%;padding: 2px 3px 2px 0px;text-align: center;">Qty</th> 
                                        <th style="width: 6%;padding: 2px 3px 2px 0px;" class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                
                                
                                	  <tbody>
                                	  <s:iterator value="medicineChargeList">
                                    	<tr>
                                        <td><s:property value="mdicinenametxt"/></td>
                                        <td class="hidden">6</td>
                                        <td class="hidden"><s:property value="mfg"/></td>
                                        <td><s:property value="batch_no"/></td>
                                        <td><s:property value="expiry_date"/></td>
                                        <td style="text-align:center;text-transform: uppercase;"><s:property value="shelf"/></td>
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
                            	<p style="margin:0px;"><b>D.L.No</b> <span>: <s:property value="dlno"/></span></p>
								<p class="hidden" style="margin:0px;"><b>VAT TIN</b> <span>: <s:property value="tinno"/></span></p>
								<p style="margin:0px;"><b>Prescription ID</b> <span>: <s:property value="selectedid" /></span></p>
                            	<p style="margin:0px;" class="hidden"><b>Payment</b> <span>: <s:property value="paymode"/></span> 
                            	<p style="margin:0px;"><b>Sales Return</b> 
                            	 
                            	      
                            	      
                            	 </p>
                            </div>
                            </div>
                           
                           </div>
                           <div class="col-lg-6 col-md-6 col-xs-6" style="padding-right:0px;">
                           
                           <div class="text-right">
                            	<div class="" style="">
                            	<h4 class="hfour">Sub Total : Rs.<s:property value="subtotal"/></h4>
                            	<h4 class="hfour hidden" style="color: #868686;">Discount(%) : Rs.<s:property value="discount"/></h4>
                            	<h4 class="hidden" style="font-size: 13px;">Vat : Rs.<s:property value="vat"/></h4>
                            	
								<h4 class="hfour">Refund: Rs.<s:property value="refundamt"/></h4>
                            	<h4 class="hfour" style="font-weight: bold;color:green;">Total : Rs.<s:property value="total"/></h4>
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
        
       /*  var t = 0;
        window.onload = function(){
        	
        	//window.location.reload();
        } */
    </script>

 

</body>
</html>