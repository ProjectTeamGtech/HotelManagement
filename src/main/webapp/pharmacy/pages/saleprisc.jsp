<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="java.util.ArrayList"%>
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
            margin-bottom: 10px;
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
    margin-top: 8px;
}
.mabset{
    margin-bottom: 12px;
}
.panamse{
	padding: 0px;
    padding-top: 10px;
    border-bottom: 1px solid #ddd;
    background-color: rgba(71, 165, 118, 0.17);
}
.form-group {
    margin-bottom: 9px !important;
}
.print_special { border: 1px solid #43b9be; } 
@media print
{
    .print_special { border: none !important; } 
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
                             <%Clinic clinic = (Clinic)session.getAttribute("clinicinfo"); %>
                                <div class="col-lg-8 col-md-8 col-xs-10 col-sm-8 hadsetma">
                                    <div class="clinicname"><%=clinic.getClinicName() %></div>
                                   	<%if(!clinic.getClinicOwner().equals("")) {%>
										<div class="cliqualif"><%=clinic.getClinicOwner() %>, <%=clinic.getOwner_qualification() %> </div>
									<%} %>
									<div class="clicniaddress">
										<%=clinic.getLocationname() %> <br>
									
									</div>
									<div class="bottext mabset">Tel/Fax:<%=clinic.getLandLine() %>, E: <%=clinic.getEmail() %>, <%if(!clinic.getWebsiteUrl().equals("")) {%>
										W: <%=clinic.getWebsiteUrl() %><br>
									<%} %> </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-2 col-sm-4 text-right" style="padding:0px;">
                                	<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12" style="padding: 0px;margin-top: 15px;">
                                			<div class="form-group">
                                				<p style="margin:0px;"><b>D.L.No</b> <span>: MH-NAG-120091</span></p>
										    	<p style="margin:0px;"><b>VAT TIN</b> <span>: 27421170022 V</span></p>
										    	<p style="margin:0px;"><b>Prescription ID</b> <span>: 1234</span></p>
										    	<p style="margin:0px;"><b>DATE</b> <span>: <%=clinic.getCurDateTime() %></span></p>
										  </div>
                                	</div>
                                </div>
                        </div>
                        
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
                                	<%Client client = (Client)session.getAttribute("clientinfo"); %>
                                	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
									    <label for="inputEmail3" class="text-left">BILL NO</label>
									      <p>#1234</p>
								  		</div>
									</div>
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">NAME</label>
										    <p style="margin:0px;"><%=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()%>, <%=client.getTown()%></p>
										   
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">SEX/AGE</label>
										    <p><%=client.getGender() %>/<%=client.getAge() %></p>
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">LOCATION</label>
											    <p>GENRAL/18</p>
									  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
									    <label for="inputEmail3" class="text-left">DR. NAME</label>
									      <p>Dr.Ookalkar</p>
								  		</div>
									</div>
                                </div>
                     </div>
                        <div class="row rowblank">
                            <table class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
                                <thead>
                                    <tr class="tableback">
                                        	  
                                        <th style="width: 38%;">Name of Drug</th>
                                        <th style="width: 4%;">Pkg</th>
                                        <th style="width: 4%;">Mfg</th>
                                        <th style="width: 6%;">Batch No</th>
                                        <th style="width: 8%;">Exp. Dt</th>
                                        <th style="width: 4%;">Qty</th>
                                        <th style="width: 6%;" class="text-right">Amount</th>
                                    </tr>
                                </thead>
                                <%if(session.getAttribute("authorisedPrisc")!=null) { ArrayList<Priscription>list = (ArrayList<Priscription>)session.getAttribute("authorisedPrisc");%>
                                	  <tbody>
                                	  <%for(Priscription priscription : list) {%>
                                    <tr>
                                        <td><%=priscription.getMdicinenametxt() %></td>
                                        <td>6</td>
                                        <td>ZUV</td>
                                        <td>123456</td>
                                        <td>MAR-2017</td>
                                        <td>10</td>
                                        <td class="text-right">Rs.120.00</td>
                                    </tr>
                                </tbody>
                                
                                	 <%} %>
                                	
                                <%} %>
                              
                            </table>
                            <div class="" style="border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;">
                           
                            <div class="text-right">
                            	<div class="" style="">
                            	<h4 style="font-size: 13px;">Sub Total : Rs.482.00</h4>
                            	<h4 style="color: #868686;font-size: 13px;">Discount(%) : Rs.30.00</h4>
                            	<h4 style="color: #868686;font-size: 13px;">Refund: Rs.120.00</h4>
                            	<h4 style="font-size: 13px;">Vat : Rs.100.00</h4>
                            	<h4 style="font-weight: bold;font-size: 13px;color:green;">Total : Rs.582.00</h4>
                            </div>
                            </div>
                            <div class="text-left sepaymeth">
                            	<div class="" style="">
                            	<p style="margin:0px;"><b>Payment</b> <span>: Cash</span></p>
                            </div>
                            </div>
						</div>
                        </div>
                        
                        
                        <%Priscription pr = (Priscription)session.getAttribute("parentpriscdata"); %>

                        <div class="row marbot10">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 marleft16">
                                	<ul>
									  <li>Refund be taken if there by any overcharges through oversight.</li>
									  <li>I/We hereby certify that our Regtn.No. under M.S.VAT Act.2002 is in force on the date of this sale.</li>
									  <li>NO RETURN, NO EXCHANGE</li>
									  <li>E. & O.E</li>
									</ul>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 text-right" style="padding:0px;padding-top: 36px;color: #bbb;">
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