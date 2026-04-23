
<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<html lang="en">
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title>priscriptionSFSDF</title>
    <meta charset="utf-8">
   
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
    
    .bg-lightred {
    background-color: #e05d6f !important;
}
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
           border-bottom: 1px solid #ddd !important;
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
.thermal{
        font-family: 'Share' !important;
    line-height: 12px !important;
    font-size: 12px;!important
    }
.print_special { 
	border: none !important; 
} 
.thermal_heading{
	font-family: 'Share';
    line-height: 12px !important;
    font-size: 14px;!important
}
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
                                	<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12 hidden" style="padding: 0px;margin-top: 15px;">
                                			<div class="form-group">
                                				<p><b>D.L.No</b> <span>: MH-NAG-120091</span></p>
										    	<p><b>VAT TIN</b> <span>: 27421170022 V</span></p>
										    	<p><b>DATE</b> <span>: </span></p>
										  </div>
                                	</div>
                                </div>
                        </div>
                        
                        
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
                                	<%Client client = (Client)session.getAttribute("clientinfo"); %>
                                	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden">
										<div class="form-group">
									    <label for="inputEmail3" class="text-left">B.NO</label>
									      <p>921661225</p>
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden">
										<div class="form-group">
									    <label for="inputEmail3" class="text-left">MEMO</label>
									      <p>Cash</p>
								  		</div>
									</div>
									
									
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">NAME</label>
										    <p style="margin:0px;"><%=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName()%>, <%=client.getAddress() + ", " + client.getTown() + ", " + client.getPostCode() %></p>
										   
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">SEX/AGE</label>
										    <p><%=client.getGender() %>/<%=client.getAge() %></p>
								  		</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<s:if test="ipdid>0">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">WARD/BED</label>
											    <p><s:property value="wardname"/>/<s:property value="bedname"/></p>
									  		</div>
								  		</s:if>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DATE</label>
										    <p><%=clinic.getCurDateTime() %></p>
								  		</div>
									</div>
									
									
									
                                </div>
                     </div>
                        <div class="row rowblank">
                            <table class="table table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <div class="row">
                               
                                
                                    <tr class="tableback">

                                        <th class="med9" style="display:none;">Type</th>
                                        <th class="hidden">Qty</th>	  
                                        <th class="med21">Name of Drug</th>
                                        <th class="hidden">Pkg</th>
                                        <th class="hidden">Mfg</th>
                                        <th class="hidden">Batch No</th>
                                        <th class="hidden">Exp. Dt</th>
                                        <th class="med9 hidden">Strength</th>
                                        <th class="med8">Dosage</th>
                                        <th class="med9">Days</th>
                                        <th class="med6">Qty</th>
                                         
                                        
                                         
                                         

                                    </tr>
                                </thead>
                                <%if(session.getAttribute("authorisedPrisc")!=null) { ArrayList<Priscription>list = (ArrayList<Priscription>)session.getAttribute("authorisedPrisc");%>
                                	  <tbody>
                                	  <%for(Priscription priscription : list) {%>
                                    <tr>
                                        <td style="display:none;"><%=priscription.getPrisctype() %></td>
                                        <td class="hidden">10</td>
                                        <td><%=priscription.getMdicinenametxt()+" ("+priscription.getGenericname()+")" %></td>
                                        <td class="hidden"><%=priscription.getPkg()%></td>
                                        <td class="hidden"><%=priscription.getMfg()%></td>
                                        <td class="hidden"><%=priscription.getBatch_no()%></td>
                                        <td class="hidden"><%=priscription.getExpiry_date()%></td>
                                        <td class="hidden"><%=priscription.getPriscfreq() %></td>
                                        <td><%=priscription.getPriscdose() %></td>
                                        <td><%=priscription.getPriscdays()%></td>
                                        <td class="hidden"><%=priscription.getPrisctotal() %></td>
                                        <td><%=priscription.getReqqty() %></td>
                                        
                                        
                                       
                                    </tr>
                                  
 <%} %>
                                </tbody>
                                
                                	
                                	
                                <%} %>
                              
                            </table>
                        </div>
                        
                        <%Priscription pr = (Priscription)session.getAttribute("parentpriscdata"); %>

                        <div class="row marbot10">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 marleft16">
                                    <p><strong>Followup After</strong>: <%=pr.getFollowupsqty() + " " + pr.getFollowupstype()  %></p>
                                    <p><strong>ADVICE</strong>: <%=pr.getPriscadvoice() %></p>
                                </div>
                                <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px;">
                                        <div class="form-group hidden" style="width: 66%;float: right;margin-bottom: 15px;">
                                            <textarea class="form-control" rows="3" placeholder="Signature"></textarea>
                                        </div>
                                        <br><br>
                                    <div class="form-group text-right hidden-print">
                                    	<a type="button" onclick="openPopup('checkavailabilityPharmacy?selectedid=<s:property value="id"/>')" class="btn btn-primary btn-lg">Check Availability</a>
                                    	<a type="button" class="btn btn-primary btn-lg hidden"  title="Print" onclick="printpage()">Print</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
