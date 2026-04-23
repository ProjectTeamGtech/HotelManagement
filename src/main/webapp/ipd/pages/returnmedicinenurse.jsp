<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
    
    
 <%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
 <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
 <link href="common/css/printpreview.css" rel="stylesheet" />
 <link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />  	
   
<title>Insert title here</title>
    <style>
    
    @media (max-width: 767px) {
    
    	.rightset {
		        min-height: 200px !important;
		}
		.padsets{
			padding: 50px 15px 0px 15px !important;
		}
		.disinset{
			display: inline-flex;
    		width: 100%;
		}
		.chosen-container {
		    height: 20px !important;
		    width: 100% !important;
		}
		.set100{
			width: 100% !important;
		}
		.qtyset{
			    width: 15% !important;
			    margin-left: 5px;
			    margin-top: -1px;
		}
		.plusbtnse{
			    margin-top: -3px;
    			margin-left: 5px;
		}
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
             border: solid 1px #c5c5c5 !important;
        }
        .marleft16 {
            margin-left: -16px;
        }
        .docsig {
            float: right;
            margin-right: -30px;
        }
        .bg-lightred {
    background-color: #e05d6f !important;
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
    font-size: 15px;
    line-height: 7px;
    border-radius: 0px;
}

.btn-md, .btn-group-lg>.btn {
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
.panamse {
    padding: 0px;
    padding-top: 10px;
    background-color: #f7f7f7;
    margin-top: 0px;
}
h4, .h4 {
    font-size: 16px;
}
.form-group {
    margin-bottom: 9px !important;
}
.print_special { border: 1px solid #339966; } 
@media print
{
    .print_special { border: none !important; } 
}
.bobotn{
    border-bottom: none !important;
}
.form-control {
    height: 26px !important;
    padding: 3px !important;
    margin-top: -4px;
}
.chosen-container {
    height: 20px !important;
}
.chosen-container-single .chosen-single {
    display: block;
    transition: none;
    border: none;
    color: #222222;
    font-size: 12px;
    font-weight: normal;
    height: 26px;
    letter-spacing: 0.04em;
    margin-bottom: 0px;
    padding: 0px 0px 0px 5px;
    width: 100%;
    line-height: 25px;
    background-color: #ffffff;
    border: 1px solid rgba(0, 0, 0, 0.1);
    margin-top: -5px;
}
.settdback{
	background-color: #fff !important;color: #555 !important;
}
.blankbx{
	background-color: #f3f9d4 !important;
}
.table > tbody > tr > td, .table > tfoot > tr > td {
    border: solid 1px #c5c5c5 !important; 
}
.rightset{
	    padding: 0px;
    padding-bottom: 10px;
    border-right: 1px solid #ddd;
    min-height: 440px;
}

.returfed{
	    border-top: 1px dashed #ddd;
    border-bottom: 1px solid #ddd;
    padding: 0px;
    padding-top: 13px;
    float: right;
    text-align: right;
}

.table.table {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
    color: RGBA(85, 85, 85, 0.85);
   
}
label {
    font-size: 12px;
}
.searchtab{
	padding:0px;border-bottom: 1px dashed #ddd;padding-bottom: 0px;    padding-bottom: 2px;padding-top: 4px;
}
.balancetext{
	width: 45%;
    float: right;
    height: 31px !important;
    color: #d9534f;
    font-size: 18px;
    text-align: right;
}
.setwismar{
	float: right;
    width: 30%;
    margin-bottom: 0px !important;
    margin-top: 5px;
}

.footerDrawer {
  background-color: #eeeeee;
  width: 100%;
  position: fixed;
  bottom: 0;
}

.footerDrawer .open {
  background-color: #eeeeee;
  padding: 0px 5px 0px 5px;
  text-align: right;
  cursor:pointer;
  height: 15px;
}

.footerDrawer .content {
  height: 28px;
  background-color: #ffffff;
  display: none;
}
.widthsets {
    height: 20px !important;
    width: 650px !important;
}
.liset{
	border-bottom: 1px dashed #ddd;padding: 2px 0px 2px 0px;
}

    </style>
     <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
    
    <!--   16 Aug 2018  -->
    
    <SCRIPT type="text/javascript">

    $(document).ready(function() {

		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
</SCRIPT>
    
   <script type="text/javascript" src="ipd/js/addcharge.js"></script>  
  
    
    
    
    
</head>
   <body>
							<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Update Sale</h4>
								</div>
							</div>
							<div class="">
							
							<%
								LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   					%>
							
							<form action="refundmedicinebynursePharmacy"  method="post" id="creditform">
							
       						
								
							<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 panamse">
							<div class="col-lg-12 col-md-12 col-sm-12 searchtab hidden">
							<div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
								<label for="inputEmail3" class="text-left">Search :</label>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-8 col-xs-3">
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 hidden" style="text-align: left;margin-top: -4px; display: inline-flex;">
								
								<ul class="vertical default_list" id="" style="list-style: none;">
								     <s:if test="tp_bill_access==1">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="istp" checked="checked" onclick="calsubTotal()" class="tp" ><i></i> Third Party Patient</label></li>								     
								     </s:if>
								     <s:else>
								           <input type="checkbox" id="istp" class="hidden" class="tp" >
								     </s:else>
								</ul>
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="text-align: right;">
							    <span class="payepharmacyi"> Payee: <span id="payee"></span></span>
								<span><i class="fa fa-square" aria-hidden="true" style="color: #e05d6f;"></i> Narcotics &nbsp; | &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #e69522;"></i> H1</span>
							</div>
							
							<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "sucessmsg" id = "sucessmsg"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('sucessmsg').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
			<s:actionerror/>
		</s:if>
	</div>
</div>
							
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;padding-top: 8px;">
									<div class="col-lg-3 col-md-3 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">PATIENT NAME</label> 
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'fullname')" cssClass="form-control" name="fullname" id="fullname" cssStyle="text-transform: uppercase;"/>
								  		</div>
								  		
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">MOBILE</label>
										    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'mobile')" cssClass="form-control" name="mobno" id="mobile" maxlength="10"/>
								  		</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
	                                		<div class="form-group">
											    <label for="inputEmail3" class="text-left">ADDRESS</label>
											    <s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'address')" cssClass="form-control" name="wardname" id="wardname" cssStyle="text-transform: uppercase;"/>
									  		</div>
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">DR. NAME</label>
										    	<!--<select class="form-control chosen hidden">
										    		<option>Select Doctor</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    	</select>
										      --><s:textfield readonly="true" onchange="updatePharmaClientInfo(this.value,'reference')" cssClass="form-control" name="practitionerName" id="doctor"  cssStyle="text-transform: uppercase;"/>
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-2 col-xs-4 hidden">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">B.NO</label>
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 hidden">
										<div class="form-group">
										    <label for="inputEmail3" class="text-left">Payee</label>
										    	<!--<select class="form-control chosen hidden">
										    		<option>Select Doctor</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    		<option>Dr.Abc</option>
										    	</select>
										      --><s:textfield readonly="true" cssClass="form-control" id="payee" />
								  		</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-4">
										<div class="form-group">
										    
								  		</div>
									</div>
							</div>
						</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
									<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 rightset">
									<s:hidden name="clientid" id="clientid"/>
       								<s:hidden name="ispharmacy" id="ispharmacy"/>
       								<s:hidden name="ipdid" id="ipdid"></s:hidden>
									<s:hidden name="paymode" id="paymode"></s:hidden>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padsets" style="background-color: #dff0d8;padding: 14px 0px 0px 11px;padding-bottom: 0px;">
								         <div class="form-inline disinset">
								         <div class="form-group hidden-xs" style="">
								                <input type="text" id="barcode" readonly="readonly" onchange="addBarcodeRequest(this.value)" class="form-control" placeholder="Barcode Here" autofocus="autofocus" style="width:100%;" />
								          </div>
								          <div class="form-group hidden-xs">
								           <p style="font-size: 13px;margin: 0px 0px 2px;">Product :</p> 
								          </div>
								          
								          <div class="form-group set100" id="plist">
								          	    <s:select list="allMedicieneList" listKey="id" id="newmedicine" listValue="genericname" headerKey="0" headerValue="select product" name="mdicinename" cssClass="form-control chosen-select set100 widthsets">
									            </s:select>  
								          </div>
								          <div class="form-group qtyset" style="width: 5%;">
								                <input type="text" id="qty" class="form-control" placeholder="Qty" style="width:100%;"/>
								          </div><!-- addnewreturnRow -->
								          <a href="#" onclick="addnursereturnRow('mytable')" title="Add New" class="plusbtnse"><i class="fa fa-plus-circle" aria-hidden="true" style="font-size: 25px;padding-top: -8px;"></i></a>
								         
								         </div>
         							</div>
                                
                                
									<div class="minhesigh">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
											<table id="mytable" class="table table-bordered" cellspacing="0" width="100%" style="margin-bottom: 0px;">
		                                		<thead>
		                                    		<tr class="tableback"> 
		                                     			<th class="pharmacytableheadingfont" style="width: 2%;">Sr</th> 
		                                        		<th class="pharmacytableheadingfont" style="width: 25%;">Name of Drug</th>
		                                        		<th class="pharmacytableheadingfont" style="width: 5%;">Sale. Qty</th>
		                                         		<th class="text-center pharmacytableheadingfont"  style="width: 5%;">Available</th>
		                                        		<th class="text-center pharmacytableheadingfont"  style="width: 5%;">Batch No</th>
		                                        		<th class="text-center pharmacytableheadingfont"  style="width: 5%;">Expiry</th>
		                                        		<th class="pharmacytableheadingfont" style="width: 4%;">Refund</th>
		                                        		<th style="width: 5%;" class="text-right pharmacytableheadingfont hidden">Unit Price</th>
		                                        		<th class="pharmacytableheadingfont hidden" style="width: 4%;text-align: center;">Shelf</th>
		                                        		<th style="width: 8%;" class="text-right pharmacytableheadingfont hidden">Total</th>
		                                        		<th class="pharmacytableheadingfont" style="width: 3%;"></th>
		                                        		<th class="pharmacytableheadingfont hidden" style="width: 2%;"></th>
		                                    		</tr>
		                                		</thead>
		                                		<tbody id="innerTableBody">
		                                	   		<tr></tr>
		                                		</tbody>
		                                    	<input type="hidden" id="tempcount" value="0" />
		                                    	<s:hidden name="selectedid" ></s:hidden>
		                            		</table>
								   		</div>
									</div>		
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 " style="padding-top:10px;padding:0px;">
									<button  class="btn btn-primary btn-lg" id="returnbtn" title="Return" onclick="refundMedicineByNurse()" style ="width:100%;padding: 32px 16px;" >Return</button>
                            	</div>
                            </form>
						</div>
							
							 
							 <div class="footerDrawer hidden">
							  <div class="open"></div>
							  <div class="content">
							  	<div class="col-lg-12 col-sm-12 col-md-12" style="padding: 0px;">
							  		<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;border-right: 1px solid #ddd;">
							  			<h5 style="margin-bottom: 6px;"><span>Sale Price&nbsp; : &nbsp;</span><span>Rs.<label id="lblSalePrice">500</label></span></h5>
							  		</div>
								  	<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;border-right: 1px solid #ddd;">
								  		<h5 style="margin-bottom: 6px;"><span>Purchase Price&nbsp; : &nbsp;</span><span>Rs.<label id="lblPurPrice">500</label></span></h5>
								  	</div>
								  	<div class="col-lg-4 col-sm-4 col-md-4" style="text-align: center;background-color: dimgray;color: #fff;">
								  		<h5 style="margin-bottom: 6px;"><span>Margin&nbsp; : &nbsp;</span><span>Rs.<label id="lblMargin">500</label></span></h5>
								  	</div>
							  	</div>
							  </div>
							</div>
		
		

		
		

		
		
							<script>
								$(document).ready(function() {
								  $('.footerDrawer .open').on('click', function() {
								    $('.footerDrawer .content').slideToggle();
								  });
								});
							</script>					 
							
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    /* document.getElementById("istp").disabled = true; */
    
  </script>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '415px',
								        railVisible: true,
										alwaysVisible: true
								    });
								});
							</script>
							
					


</body>
</html>