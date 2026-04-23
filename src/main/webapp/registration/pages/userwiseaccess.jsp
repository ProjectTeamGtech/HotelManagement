<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.Registration.eu.entity.UserProfile"%>
<%@page import="com.apm.DiaryManagement.eu.entity.DiaryManagement"%>
<%@page import="com.apm.Registration.eu.entity.Clinic"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% UserProfile clinic=(UserProfile) request.getAttribute("userwiseaccesssetting");%>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

<html>
<head>
<%String checked=""; %>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<link rel="stylesheet" href="mis/kpiplugin/css/style.css">
<style>
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
</style>
</head>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<div class="row details" style="background-color: #339966 !important;height: 37px;">
			<h4 align="center">User Wise Access (<s:property value="fullname"/> - <s:property value="job_title"/> - <s:property value="userId"/>)</h4>
		</div>
</div>
<div class="clearfix" style="height: 50px;"></div>

			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				<div class='headline'data-toggle="collapse" data-target=".accounts" style="height: 31px;" ><h4 style="padding-top: 7px;">Account</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 accounts collapse out'>
					<%if(clinic.isAdd_charge_amt_edit()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Add Charge Amount Edit <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'add_charge_amt_edit','<s:property value="userId"/>','Add Charge Amount Edit')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isRefund_dashboard()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Refund Discount Dashboard <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'refund_dashboard','<s:property value="userId"/>','Refund Discount Dashboard')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isRef_dis_pay()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Refund Discount Pay <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'ref_dis_pay','<s:property value="userId"/>','Refund Discount Pay')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isModify_disc()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Discount Modify <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'modify_disc','<s:property value="userId"/>','Discount Modify')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
				</div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 accounts collapse out'>
					<%if(clinic.isDirect_refund_disc()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Direct Discount Refund <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'direct_refund_disc','<s:property value="userId"/>','Direct Discount Refund')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isAdd_manual_charge()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Manual Add Charge <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'add_manual_charge','<s:property value="userId"/>','Manual Add Charge')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isInvoicemodify()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Invoice Modify <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'invoicemodify','<s:property value="userId"/>','Invoice Modify')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isIndv_discount()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Individual Discount <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'indv_discount','<s:property value="userId"/>','Individual Discount')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
				</div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 accounts collapse out'>
					<%if(clinic.isAdditional_disc()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Additional Discount <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'additional_disc','<s:property value="userId"/>','Additional Discount')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isCancel_invoice_new()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Cancel Invoice <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'cancel_invoice','<s:property value="userId"/>','Cancel Invoice')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(DateTimeUtils.isNull(clinic.getAcaccess()).equals("1")){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Account Access <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'acaccess','<s:property value="userId"/>','Account Access')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(DateTimeUtils.isNull(clinic.getEditcharges()).equals("1")){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Charge Details  <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'editcharges','<s:property value="userId"/>','Edit Charge Details')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
				</div>
				<%if(loginInfo.getSuperadminid()==1){ %>
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 accounts collapse out'>
				<%if(clinic.isDelete_invoice_history()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Delete Invoice With All Data<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'delete_invoice_history','<s:property value="userId"/>','Delete Invoice With All Data')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
				</div>
				<%} %>
	       	</div>


			<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				<div class='headline'data-toggle="collapse" data-target=".inventory" style="height: 31px;" ><h4 style="padding-top: 7px;">Inventory</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inventory collapse out'>
					<%if(clinic.isEdit_paypo()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Supplier Payment Edit <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'edit_paypo','<s:property value="userId"/>','Supplier Payment Edit')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			<%if(clinic.isAdjustmentaccess()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Inventory Adjustment Process <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'adjustmentaccess','<s:property value="userId"/>','Inventory Adjustment Process')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>			
	       		
					<%if(clinic.isSupplier_add()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Inventory Supplier Add <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'supplier_add','<s:property value="userId"/>','Inventory Supplier Add')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			<%if(clinic.isEdit_catalogue_name()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Catalogue Name <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'edit_catalogue_name','<s:property value="userId"/>','Edit Catalogue Name')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       		</div>
	       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inventory collapse out'>
	       			<%if(clinic.isMulti_edit_product()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Multiple Edit Product <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'multi_edit_product','<s:property value="userId"/>','Multiple Edit Product')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isAdjustment_approve()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Product Adjustment Approve<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'adjustment_approve','<s:property value="userId"/>','Product Adjustment Approve')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isEdit_approve()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Product Approve <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'edit_approve','<s:property value="userId"/>','Edit Product Approve')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isMulti_adjust_approve()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Multiple Adjustment Process <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'multi_adjust_approve','<s:property value="userId"/>','Multiple Adjustment Process')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>					
				</div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inventory collapse out'>
	       			<%if(clinic.isEdit_product()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Stock Product<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'edit_product','<s:property value="userId"/>','Edit Stock Product')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isInventory_report_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Inventory Report<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'inventory_report_access','<s:property value="userId"/>','Inventory Report')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			
	       		<%if(loginInfo.isBalgopal() || loginInfo.isAyushman()){ %>	
	       			<%if(clinic.isSupplier_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show Vendor Name<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'Supplier','<s:property value="userId"/>','Supplier')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       			
	       			<%if(loginInfo.isBalgopal() || loginInfo.isAyushman()){ %>	
	       			  <%if(clinic.isMrp_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">MRP<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'mrp','<s:property value="userId"/>','MRP')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>	
	       		</div>
	       		
	       		<%if(loginInfo.isBalgopal() || loginInfo.isAyushman() || loginInfo.isLmh()){ %>
	       		<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inventory collapse out'>
	       			<%if(clinic.isSale_price_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show Sale Price <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'sale_price_access','<s:property value="userId"/>','Show Sale Price')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       			
	       			<%if(loginInfo.isBalgopal() || loginInfo.isAyushman()){ %>	
	       			  <%if(clinic.isGst_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show GST<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'gst_access','<s:property value="userId"/>','Show GST')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       			
	       			<%if(loginInfo.isBalgopal()|| loginInfo.isAyushman() || loginInfo.isLmh()){ %>	
	       			  <%if(clinic.isPurchase_price_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show Purchase Price<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'purchase_price_access','<s:property value="userId"/>','Show Purchase Price')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       			
	       			<%if(loginInfo.isBalgopal() || loginInfo.isAyushman()){ %>	
	       			  <%if(clinic.isSale_value_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show S.V<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'sale_value_access','<s:property value="userId"/>','Show S.V')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       				
	       		</div>
	       		
	       	    <div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 inventory collapse out'>
	       		     <%if(loginInfo.isBalgopal() || loginInfo.isAyushman()){ %>	
	       			  <%if(clinic.isNet_rate_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show N.R<input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'net_rate_access','<s:property value="userId"/>','Show N.R')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>
	       			<%} %>
	       		
	       	    </div>
	       	</div>
	       	
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".pharmacy" style="height: 31px;" ><h4 style="padding-top: 7px;">Pharmacy</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 pharmacy collapse out'>
					<%if(clinic.isMax_phar_discount()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Direct Discount greater than 10% <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'max_phar_discount','<s:property value="userId"/>','Direct Discount greater than 10%')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isPprice_sale()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show purchase price while doing sale <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'pprice_sale','<s:property value="userId"/>','Show purchase price while doing sale')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isEditpharmacy_bill()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Pharmacy Bill <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'editpharmacy_bill','<s:property value="userId"/>','Edit Pharmacy Bill')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       				
	       			
				</div>
				
				
	       	</div>
	       	
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".indent" style="height: 31px;" ><h4 style="padding-top: 7px;">Indent</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 indent collapse out'>
					
	       				<%if(clinic.isChange_indent_product()){checked="checked='checked'";}else{checked="";} %>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
						 	<label class="main">Replace Indent Product while transfer <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'change_indent_product','<s:property value="userId"/>','Replace Indent Product while transfer')" <%=checked %>> <span class="geekmark"></span> </label> 
		       			</div>	
		       			
		       			<%if(clinic.isIndent_self_approve()){checked="checked='checked'";}else{checked="";} %>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
						 	<label class="main">Self Department Request Approve Access <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'indent_self_approve','<s:property value="userId"/>','Self Department Request Approve Access')" <%=checked %>> <span class="geekmark"></span> </label> 
		       			</div>	
		       			
		       			<%if(clinic.isIndent_all_approve()){checked="checked='checked'";}else{checked="";} %>
						<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
						 	<label class="main">All Department Request Approve Access <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'indent_all_approve','<s:property value="userId"/>','All Department Request Approve Access')" <%=checked %>> <span class="geekmark"></span> </label> 
		       			</div>	
		       				
	       			
				</div>
				
				
	       	</div>
	       	<%if(loginInfo.getSuperadminid()==1){ %>
	       		<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
					<div class='headline'data-toggle="collapse" data-target=".ipd" style="height: 31px;" ><h4 style="padding-top: 7px;">IPD</h4></div>
					
					<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 ipd collapse out'>
						<%if(clinic.isFreeze_unfreeze()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Freeze And Unfreeze <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'freeze_unfreeze','<s:property value="userId"/>','Freeze And Unfreeze ')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
		       		<%if(clinic.isAdmsn_date_edit()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Edit Admission Date <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'admsn_date_edit','<s:property value="userId"/>','Edit Admission Date')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>		
					</div>
				
				
	       		</div>
	       	<%} %>
	       		<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".investigation" style="height: 31px;" ><h4 style="padding-top: 7px;">Investigation</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 investigation collapse out'>
					<%if(clinic.isUpdate_investigation_charge()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Update Investigation Charge <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'update_investigation_charge','<s:property value="userId"/>','Update Investigation Charge')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       				
	       			
				</div>
				
				
	       	</div>
	       	
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				<div class='headline'data-toggle="collapse" data-target=".prescription" style="height: 31px;" ><h4 style="padding-top: 7px;">Prescription</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 prescription collapse out'>
					<%if(clinic.isPrisc_temp_showother()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Show Other Doctor Template <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'prisc_temp_showother','<s:property value="userId"/>',Show Other Doctor Template)" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isPrisc_new_req_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Prescription New Request <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'prisc_new_req_access','<s:property value="userId"/>','Prescription New Request')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isAdd_medicine()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Add Medicine <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'add_prisc_medicine','<s:property value="userId"/>','Add Medicine')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			
	       			
				</div>
				
	       	</div>
			
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".payroll" style="height: 31px;" ><h4 style="padding-top: 7px;">Payroll</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 payroll collapse out'>
					<%if(clinic.isPayrollaccess()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Payroll Admin Access <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'payrollaccess','<s:property value="userId"/>','Payroll Admin Access')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       				
	       			
				</div>
				
				
	       	</div>
	       	
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".vaccination" style="height: 31px;" ><h4 style="padding-top: 7px;">Vaccination</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 vaccination collapse out'>
					<%if(clinic.isManual_sms_vaccine()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Manual SMS Of Vaccination Remainder <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'manual_sms_vaccine','<s:property value="userId"/>','Manual SMS Of Vaccination Remainder')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       				
	       			
				</div>
				
				
	       	</div>
	       	<%if(loginInfo.getSuperadminid()==1){ %>
	       		<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
					<div class='headline'data-toggle="collapse" data-target=".opd" style="height: 31px;" ><h4 style="padding-top: 7px;">OPD</h4></div>
					
					<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 opd collapse out'>
		       		<%if(clinic.isOpd_discount_access()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Opd Discount Access <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'Opd_discount_access','<s:property value="userId"/>','Opd Discount Access')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>		
					</div>
				
				
	       		</div>
	       	<%} %>
	       	<%if(loginInfo.isLmh()){ %>
	       	<div class='header col-lg-12 col-md-12 col-xs-12 col-sm-12'>
				
				<div class='headline'data-toggle="collapse" data-target=".departmentopd" style="height: 31px;" ><h4 style="padding-top: 7px;">Departmental OPD</h4></div>
				
				<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12 departmentopd collapse out'>
					<%if(clinic.getShow_dept_opd_list().equals("1")){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Login With  All Department List <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'show_dept_opd_list','<s:property value="userId"/>','Login With  All Department List')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       				
	       			<%-- 
					<%if(clinic.isLmh_consultation_charge()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">ODMR Consultation Charge <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'lmh_consultation_charge','<s:property value="userId"/>')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	--%>
	       			
					
	       			
					<%if(clinic.isLmh_discount()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">ODMR Discount <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'lmh_discount','<s:property value="userId"/>','ODMR Discount')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	
	       			
	       			<%if(clinic.isDeptOpdReport()){checked="checked='checked'";}else{checked="";} %>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3  ">
					 	<label class="main">Department OPD Report <input type="checkbox" onchange="updateUserWiseAccessNew(this.checked,'dept_opd_report','<s:property value="userId"/>','Department OPD Report')" <%=checked %>> <span class="geekmark"></span> </label> 
	       			</div>	 
	       				
	       		</div>
	       	</div>
			<%} %>
			<script>
			function updateUserWiseAccessNew(val,col,userid,showingcol_name){
				/* var val=0;
				if(obj.checked){
					val=1;
				} */
				
				var url = "updateuserwiseaccessUserProfile?userid="+userid+"&val="+val+"&cname="+col+"&showingcol_name="+showingcol_name+"";
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			    req.onreadystatechange = updateUserWiseAccessNewRequest;
			    req.open("GET", url, true); 
			    req.send(null);
				
			}
			
			
			function updateUserWiseAccessNewRequest(){
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
