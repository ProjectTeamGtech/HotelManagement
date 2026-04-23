<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO"%>
<%@page import="com.apm.Appointment.eu.bi.AppointmentTypeDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% Clinic clinic=(Clinic) request.getAttribute("clinicaccess");%>
<% Clinic clinicaccess=(Clinic) request.getAttribute("clinic_access");%>
<% Clinic newclinicaccess=(Clinic) request.getAttribute("newclinicaccess");%>
<html>
<head>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
<%String checked=""; %>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<link rel="stylesheet" href="mis/kpiplugin/css/style.css">
<style>
.header{
 padding-bottom: 5px;
}
.headline{
    background-color: #666666;
    font-size: 13px;
    color: white;
    padding-left: 10px;
   
}
       .main { 
            display: block; 
            position: relative; 
            padding-left: 45px; 
            margin-bottom: 0px; 
            cursor: pointer; 
            font-size: 13px; 
        } 
          
        /* Hide the default checkbox */ 
        input[type=checkbox] { 
            visibility: hidden; 
        } 
          
        /* Creating a custom checkbox 
        based on demand */ 
        .geekmark { 
            position: absolute; 
            top: 0; 
            left: 0; 
            height: 21px; 
            width: 25px; 
            background-color: green; 
        } 
          
        /* Specify the background color to be 
        shown when hovering over checkbox */ 
        .main:hover input ~ .geekmark { 
            background-color: yellow; 
        } 
          
        /* Specify the background color to be 
        shown when checkbox is active */ 
        .main input:active ~ .geekmark { 
            background-color: red; 
        } 
          
        /* Specify the background color to be 
        shown when checkbox is checked */ 
        .main input:checked ~ .geekmark { 
            background-color: green; 
        } 
          
        /* Checkmark to be shown in checkbox */ 
        /* It is not be shown when not checked */ 
        .geekmark:after { 
            content: ""; 
            position: absolute; 
            display: none; 
        } 
          
        /* Display checkmark when checked */ 
        .main input:checked ~ .geekmark:after { 
            display: block; 
        } 
          
        /* Styling the checkmark using webkit */ 
        /* Rotated the rectangle by 45 degree and  
        showing only two border to make it look 
        like a tickmark */ 
        .main .geekmark:after { 
            left: 8px; 
            bottom: 5px; 
            width: 6px; 
            height: 12px; 
            border: solid white; 
            border-width: 0 4px 4px 0; 
            -webkit-transform: rotate(45deg); 
            -ms-transform: rotate(45deg); 
            transform: rotate(45deg); 
        } 
        
       .collapse{
       padding: 10px;
       } 
       
       
  .sss input[type=checkbox]{visibility: visible; }     
</style>
</head>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-bottom: 20px">
		<div class="row details" style="background-color: #43b9be !important;">
			<h4 align="center">Clinic Access</h4>
		</div>
</div>
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".opd" >OPD</div>
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 opd collapse out'>
				<%if(clinic.isOpd_tp_zero_invoice()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">OPD TP Payment Received 0 (Book With Payment)  <input type="checkbox" onchange="setfun(this,'opd_tp_zero_invoice')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       			
       			<%if(clinic.isOpd_video_icon_show()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Show All Icon in OPD Dashboard [Icon like video is Shown in opd appointment. (Checked: Show Icon. Unchecked: Hide Icon.)] <input type="checkbox" onchange="setfun(this,'opd_video_icon_show')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>				
       			<%if(newclinicaccess.isOpd_chat()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Show Chat icon in OPD Dashboard [Video icon is provided in OPD Appointment. (Checked: Show Icon. Unchecked: Hide Icon.)] <input type="checkbox" onchange="setfun2(this,'opd_chat')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			<%if(clinic.isOpd_recep_sp_list()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Show Select Department List in OPD Dashboard [Select Department List in OPD Dashboard. (Checked: Show Department List. Unchecked: Hide Department List.)] <input type="checkbox" onchange="setfun(this,'opd_recep_sp_list')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
				</div>
				
				
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".inv" >Investigations</div>
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inv collapse out'>
				<%if(clinic.isJobtitlewise_investigation()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">JobTitle Wise Investigation <input type="checkbox" onchange="setfun(this,'jobtitlewise_investigation')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>			
       			
				<%if(clinic.isHidelettinvst()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Hide Investigation LetterHead <input type="checkbox" onchange="setfun(this,'hidelogoinvst')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			
				<%if(clinic.isInvst_inv_apr()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Collect Investigation With Add Charges <input type="checkbox" onchange="setfun(this,'invst_inv_apr')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
				
				<%if(clinic.isInvest_order()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Investigation Order Dashboard <input type="checkbox" onchange="setfun(this,'invest_order')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			
       			<%if(clinic.isAnalytics_template()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Investigation Analytics Template <input type="checkbox" onchange="setfun(this,'analytics_template')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
				</div>
				
				
			</div>
			
			
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".emr" >EMR</div>
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 emr collapse out'>
				
				<%if(clinic.isEmr_vitals_show()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">EMR Print Vital Show <input type="checkbox" onchange="setfun(this,'emr_vitals_show')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>			
       
				</div>
				
				
				
			</div>
			
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".ipd">IPD</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 ipd collapse out'>
			<%if(clinic.isDirect_ipd()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Direct IPD <input type="checkbox" onchange="setfun(this,'direct_ipd')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
			<%if(clinic.isDrwise_ipd()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Doctor Wise IPD <input type="checkbox" onchange="setfun(this,'drwise_ipd')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       		<%if(clinic.isShow_wardname()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Show Ward Name <input type="checkbox" onchange="setfun(this,'show_wardname')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       		<%if(clinic.isSms_on_bedchange()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">SMS On Bed Change <input type="checkbox" onchange="setfun(this,'sms_on_bedchange')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       		</div>
       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 ipd collapse out'>	
       		<%if(clinic.isSms_on_newadm()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">SMS On New Admission<input type="checkbox" onchange="setfun(this,'sms_on_newadm')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       		<%if(clinic.getIpd_abbr_access()==1){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">IPD Abrivation Access<input type="checkbox" onchange="setfun(this,'ipd_abbr_access')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       		<%if(clinic.getDischarge_validation()==1){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Discharge Validation<input type="checkbox" onchange="setfun(this,'discharge_validation')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       		<%if(clinic.isNewdischargecard()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">New Discharge Card <input type="checkbox" onchange="setfun(this,'newdischargecard')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>						
       			</div>	
       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 ipd collapse out'>	
       		<%if(clinic.isDischarge_msg_hs()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">SMS on Discharge<input type="checkbox" onchange="setfun(this,'discharge_msg_hs')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       		</div>	
       			
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".ot">OT</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 ot collapse out'>
			<%if(clinic.isOt_patient_sms()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>OT Book SMS for Patient</b> -when <b>checked</b> then SMS will sent to patient register mobile number on OT appointment book.And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'ot_patient_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
       			
       			<%if(clinic.isOt_surgeon_sms()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>OT Book SMS for Surgeon</b> -when <b>checked</b> then SMS will sent to consultant register mobile number on OT appointment book. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'ot_surgeon_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
       		</div>	
       			
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".maindashboard">Main Dashboard</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 maindashboard collapse out'>
				<%if(clinic.isMaindash_graph()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Show Graph In Maindashboard</b> -when <b>checked</b> then graph will show in maindashboard. <input type="checkbox" onchange="setfun(this,'maindash_graph')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
       		</div>	
       			
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".invent">Inventory</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 invent collapse out'>
			<%if(clinic.isBarcode_productname_show()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					<label class="main"><b>Show Product Name On Barcode</b> -This access used to show product name while printing barcode. [Checked: Name show in barcode. Un-Checked: Name not show in barcode print.]<input type="checkbox" onchange="setfun(this,'barcode_productname_show')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	 
       		<%if(clinic.isAuto_generic_name()){checked="checked='checked'";}else{checked="";} %>
       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 	<label class="main"><b>Auto Generic name and MFG from Master</b> -When user don't want to enter generic name or manufacturer. And use drop down instead of entering text. then this access used. [Checked: Use drop down. Un-Checked: Manualy enter mfg and generic name.)] <input type="checkbox" onchange="setfun(this,'auto_generic_name')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 	<label ><b>Catalogue product auto add to prescription of location</b> -When user want to add newly added product in inventory to add into prescription request popup then below drop down selected product only added into prescription popup.
				 	<s:select list="locationListPharmacy" onchange="updateinventorygrnloction(this.value,'grn_to_prisc_location')" theme="simple" name="grn_to_prisc_location" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
					</s:select> 
					</label> 
       			</div>	
       		<%if(clinic.isHidecalinpoprint()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					<label class="main"><b>Hide Calculation In PO Print</b> -When user want only requested quantity and product name and hide other GST and amount information in purchase order print then this access used.[Checked: Hide Calculation, Un-Checked: Show Calculation] <input type="checkbox" onchange="setfun(this,'hidecalinpoprint')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       		<%if(clinic.isLmh_po_discount()){checked="checked='checked'";}else{checked="";} %>	
       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					<label class="main"><b>Show Discount option in PO </b> -When clinic want to give discount while creating PO or approve PO then this access used.[Checked: Enable discount option, Un-Checked: Disable discount option] <input type="checkbox" onchange="setfun(this,'lmh_po_discount')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			
			</div>	
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".pharamcy">Pharmacy</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 pharamcy collapse out'>
				<%if(clinic.isPhar_print_seq()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 	<label class="main"><b>Pharmacy Summary Print Assending Order</b>- When in Bill Transaction Report and bill summary print, showing 1st bill of patient on top and last bill on bottom then this access used. [When <b>checked</b> then show 1st bill on top and last bill on bottom. And when <b>Unchecked</b> then show vice versa.]  <input type="checkbox" onchange="setfun(this,'phar_print_seq')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			<%if(clinic.isPhar_batchwise_sale()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 	<label class="main"><b>Pharmacy Batchwise Sale List</b>- When <b>checked</b> then in pharmacy sale show one drop down for product name and other for batch name. And When it's <b>Unchecked</b> then shown only one drop down with product name while sale.<input type="checkbox" onchange="setfun(this,'phar_batchwise_sale')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
			</div>	
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".acc">Accounts</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 acc collapse out'>
			
				<%if(clinic.isShow_unpost()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>OPD Invoice Post</b> <input type="checkbox" onchange="setfun(this,'opd_invoice_post')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
       			<%if(clinic.isCreate_invoice_selected_charges()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Create Invoice Auto Select Charges</b> <input type="checkbox" onchange="setfun(this,'create_invoice_selected_charges')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>		
				<%if(clinic.isIsledgerhosp()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Ledger</b> When hospital want to use ledger then this access used. <b>Checked</b> means ledger is used. <b>Unchecked</b> means ledger not used. <input type="checkbox" onchange="setfun(this,'isledgerhosp')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			
       			<%if(clinic.isDirect_refund_disc()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Direct Refund Discount</b> - When hospital don't want approve discount and approve refund then this access used. <b>Checked</b> means direct discount and refund process. <b>Unchecked</b> means approve process. <input type="checkbox" onchange="setfun(this,'direct_refund_disc')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       				
       			
       				
			</div>	
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 acc collapse out'>
			<%if(clinic.isAdv_payamnt_sms()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Advance Payment SMS</b> -when <b>checked</b> then SMS will sent to patient register mobile number on advance taken. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'advance_payment_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
			
			<%if(clinic.isRefund_payamnt_sms()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Refund Payment SMS</b> -when <b>checked</b> then SMS will sent to patient register mobile number on refund amount. And when <b>Un-checked</b> then SMS not sent to patient.  <input type="checkbox" onchange="setfun(this,'ref_payment_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
			<%if(clinic.isInvoice_groupby()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Grouping Invoice Charges</b> <input type="checkbox" onchange="setfun(this,'invoice_groupby')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
			<%if(clinic.isInvoice_charge_seqno()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main"><b>Sequence Wise Master Charge Invoice</b> <input type="checkbox" onchange="setfun(this,'invoice_charge_seqno')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>
       			
			</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 acc collapse out'>
				<%if(clinic.isDisc_approve_sms()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>Discount Request SMS</b> -when <b>checked</b> then SMS will sent to selected consultant register mobile number. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'disc_approve_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			
	       			<%if(clinic.isHidelettbillinv()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>Hide Header In Bill</b> <input type="checkbox" onchange="setfun(this,'hidelogobillinv')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       		<%if(clinic.isDel_ipd_data_cancel_inv()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>Delete Paid Invoice</b>
					 -when <b>checked</b> then user will be able to delete the invoice of paid bill  from paid invoices dashboard. 
					  <input type="checkbox" onchange="setfun(this,'del_ipd_data_cancel_inv')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>			
       		</div>
			</div>
			
			
			<!--  23-03-2020 sms setting  -->
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				<div class='headline'data-toggle="collapse" data-target=".smssetting">SMS Setting</div>
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 smssetting collapse out'>
					<%if(clinicaccess.isSmscheck()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to patient on OPD booking [OPD]</b> -when <b>checked</b> then SMS will sent to patient register mobile number on OPD booked. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'smscheck')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			
	       			<%if(clinicaccess.isSmscheckdoctor()){checked="checked='checked'";}else{checked="";} %>
	       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to doctor on OPD booking [OPD]</b> -when <b>checked</b> then SMS will sent to consultant register mobile number on OPD booked. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'smscheckdoctor')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			
	       			 <%if(clinicaccess.isSms_on_bedchange()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to doctor on bed change [IPD]</b> -when <b>checked</b> then SMS will sent to consultant register mobile number on bed change. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'sms_on_bedchange')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			
	       			<%if(clinicaccess.isSms_on_newadm()){checked="checked='checked'";}else{checked="";} %>
	       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to doctor on new admission [IPD]</b> -when <b>checked</b> then SMS will sent to consultant register mobile number on patient admit to IPD. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'sms_on_newadm')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       		</div>
	       		
	       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 smssetting collapse out'>
	       		   <%if(clinicaccess.isSmsVisitingConslt()){checked="checked='checked'";}else{checked="";} %>
	       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to Visiting Consultant [IPD]</b> -when <b>checked</b> then SMS will sent to visiting consultant register mobile number on visiting consultant appointed to IPD patient. And when <b>Un-checked</b> then SMS not sent to consultant. <input type="checkbox" onchange="setfun(this,'consultsms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%if(clinicaccess.isSmsPayment()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to patient on payment [ACCOUNT]</b> -when <b>checked</b> then SMS will sent to patient register mobile number on payment taken. And when <b>Un-checked</b> then SMS not sent to patient.</b> <input type="checkbox" onchange="setfun(this,'smspayment')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%if(clinicaccess.isBdaysms()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to Patient on Birthday [PATIENT]</b> -when <b>checked</b> then SMS will sent to patient register mobile number for birthday wish. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'bdaysms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%if(clinicaccess.isImmusms()){checked="checked='checked'";}else{checked="";} %>
	       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main"><b>SMS to Patient on a day before vaccination [INVISTIGATION]</b> -when <b>checked</b> then SMS will sent to patient register mobile number before one day of vaccination. And when <b>Un-checked</b> then SMS not sent to patient.<input type="checkbox" onchange="setfun(this,'immusms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       		</div>
	       		
	       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 smssetting collapse out'>
					<%if(clinic.isSms_reg_patient()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>SMS on Patient Registration</b> -when <b>checked</b> then SMS will sent to patient register mobile number on patient registration. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'sms_reg_patient')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isSms_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>All SMS</b> -when <b>checked</b> then SMS will sent. And when <b>Un-checked</b> then all SMS not sent. <input type="checkbox" onchange="setfun(this,'sms_access')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			<%if(clinic.isVaci_sms_7day()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>Vaccination SMS before 7 day</b> -when <b>checked</b> then SMS will sent. And when <b>Un-checked</b> then SMS not sent. <input type="checkbox" onchange="setfun(this,'vaci_sms_7day')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%if(clinic.isOpd_payamnt_sms()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>SMS on Patient OPD Payment </b> -when <b>checked</b> then SMS will sent to Patient. And when <b>Un-checked</b> then SMS not sent to Patient. <input type="checkbox" onchange="setfun(this,'opd_paymnet_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
				</div>
	       		
	       			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 smssetting collapse out'>
	       			<%if(clinic.isIpd_payamnt_sms()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>SMS on Patient IPD Payment </b> -when <b>checked</b> then SMS will sent to Patient. And when <b>Un-checked</b> then SMS not sent to Patient. <input type="checkbox" onchange="setfun(this,'ipd_payment_sms')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%if(clinic.isSms_reg_patient()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>SMS on Patient Registration</b> -when <b>checked</b> then SMS will sent to patient register mobile number on patient registration. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'sms_reg_patient')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       		<%if(clinic.isSms_cancel_appointment()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 <label class="main"><b>SMS on Appointment Cancel</b> -when <b>checked</b> then SMS will sent to patient register mobile number on patient registration. And when <b>Un-checked</b> then SMS not sent to patient. <input type="checkbox" onchange="setfun(this,'sms_cancel_appointment')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>		
				</div>
       			
			</div>
			
			
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden'>
			<div class='headline'data-toggle="collapse" data-target=".other">Others</div>
			
				<%-- <%if(clinic.isBalgopal()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Is Balgopal ?<input type="checkbox" onchange="setfun(this,'isbalgopal')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	
       			<%if(clinic.isBalgopal()){checked="checked='checked'";}else{checked="";} %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 <label class="main">Is Prachi Clinic ?<input type="checkbox" onchange="setfun(this,'prachi_clinic')" <%=checked %>> <span class="geekmark"></span> </label> 
       			</div>	 --%>
       			
	       			
			</div>
			
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12 '>
			<div class='headline'data-toggle="collapse" data-target=".Ledgers">Ledgers</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 Ledgers collapse out'>
			<%
			Connection connection= Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			ArrayList<Master>ledgerserviceList = appointmentTypeDAO.getLedgerServiceList();
			%>
       			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
       			<h3>Select Services for Vendors</h3>
       			<div style="height: 200px;overflow: scroll;">
       				<%for(Master m:ledgerserviceList){ %>
       					<input type="checkbox" class='hari' value="<%=m.getId()%>" style="visibility: visible;" id='chledger<%=m.getId()%>'><b style="padding-left: 10px !important;"><%=m.getName() %></b><br>
    				<% } %>
    			</div>	
    			<br>
    			<input type="button" value="Save Services" class='btn btn-primary' onclick="saveledgerservices()">
       			</div>
			</div>	
			</div>
			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
			<div class='headline'data-toggle="collapse" data-target=".hospital">Hospital</div>
			<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 hospital collapse out'>
			<%if(loginInfo.getSuperadminid()==1) {%>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
				 	<label ><b>System Language Change</b> -Select Language so System will Change all labels in selected language.
				 	<%-- <select onchange="updatesystemlanguage(this.value,'regional_lang')" name="regional_lang" class="form-control chosen-select">
				 	<option value="en">English</option>
				 	<option value="gu">Gujarati</option>
				 	<option value="hi">Hindi</option>
				 	<option value="mr">Marathi</option>
				 	</select> --%>
				 	<s:select cssClass="form-control chosen-select" list="#{'en':'English', 'gu':'Gujarati', 'hi':'Hindi', 'mr':'Marathi'}" 
				 	name="regional_lang" onchange="updatesystemlanguage(this.value,'regional_lang')"/>
					</label> 
       			</div>	
       			<%} %>
			</div>
			</div>
			<script>
			function checkNumberOrNotofclinic(inputtxt)
			{
				 var numbers = /^[0-9]+$/;  
			     
			     if(inputtxt.match(numbers))  {
			        return true;
			     } else {
			          return false;
			     }
			} 
			function updateinventorygrnloction(val,col){
					/* if(!checkNumberOrNotofclinic(val)){
						 jAlert('error', "Please enter valid location!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}  */
						
					if(val==0){
						jAlert('error', "Please select valid location!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}else{
						 var url = "setaccessToThisCommonnew?col="+col+"&value="+val;
							if (window.XMLHttpRequest) {
								req = new XMLHttpRequest();
							}
							else if (window.ActiveXObject) {
								isIE = true;
								req = new ActiveXObject("Microsoft.XMLHTTP");
							}   
						    req.onreadystatechange =setfunReq;
						    req.open("GET", url, true); 
						    req.send(null)
					}
			}
			
		
			function setfun(obj,col){
				var val=0;
				if(obj.checked){
					val=1;
				}
				
				 var url = "setaccessToThisCommonnew?col="+col+"&value="+val;
					
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange =setfunReq;
				    req.open("GET", url, true); 
				    
				    req.send(null)
				
			}
			
			
			function setfunReq(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						var xx=Number(req.responseText);
						if(xx>0){
							 jAlert('success', 'Access Saved Successfully !.', 'Success Dialog');
								
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
						}
					}
				}
			}
			
			
			
			
			
			
			function setfun1(val,col){
				
				 var url = "setaccessToThisCommonnew?col="+col+"&value="+val;
					
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange =setfunReq1;
				    req.open("GET", url, true); 
				    
				    req.send(null)
				
			}
			
			
			function setfunReq1(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						var xx=Number(req.responseText);
						if(xx>0){
							 jAlert('success', 'Access Saved Successfully !.', 'Success Dialog');
								
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
						}
					}
				}
			}
			
			function updatesystemlanguage(val,col){
				/* if(!checkNumberOrNotofclinic(val)){
					 jAlert('error', "Please enter valid location!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}  */
					
				
					 var url = "setaccessToNewTableCommonnew?col="+col+"&value="+val;
						if (window.XMLHttpRequest) {
							req = new XMLHttpRequest();
						}
						else if (window.ActiveXObject) {
							isIE = true;
							req = new ActiveXObject("Microsoft.XMLHTTP");
						}   
					    req.onreadystatechange =setfunReq;
					    req.open("GET", url, true); 
					    req.send(null)
		}
			
			function saveledgerservices(){
				var init="0";
				 $('.hari').each(function() { 
				  		if(this.checked){	
				  			init=init+","+this.value;
				  		}		
				  	});
				 init=init+',';
				 setfun1(init,'def_vend_ledg_ser');
			}
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
    
    
    
    
    $(document).ready(function() {
    	var ledgerservices='<%=clinic.getDefaultVendorServices()%>';
    	/* var dbselectedservices =  document.getElementById('dbselectedservices').value;
    	 */
    	var tmp = ledgerservices.split(',');
    	
    	for(i=1;i<tmp.length;i++){
    		if(document.getElementById('chledger'+tmp[i])){
    			document.getElementById('chledger'+tmp[i]).checked = true;	
    		}
    		
    	}
    	
    });
    
    
    function setfun2(obj,col){
    	var val=0;
		if(obj.checked){
			val=1;
		}
		 var url = "setaccessToNewTableCommonnew?col="+col+"&value="+val;
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange =setfunReq1;
		    req.open("GET", url, true); 
		    
		    req.send(null)
		
	}
	
	
	function setfunReq2(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var xx=Number(req.responseText);
				if(xx>0){
					 jAlert('success', 'Access Saved Successfully !.', 'Success Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}
			}
		}
	}
  </script>
</html>
