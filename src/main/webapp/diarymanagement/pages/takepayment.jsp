<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo2 = LoginHelper.getLoginInfo(request);%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<style>
.form-group{
	margin-bottom: 15px !important;
}

/*  bhoechie tab */
div.bhoechie-tab-container{
  z-index: 10;
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 4px;
  -moz-border-radius: 4px;
  border:1px solid #ddd;
  margin-top: 20px;
 
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #5A55A3;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #5A55A3;
  background-image: #5A55A3;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #5A55A3;
}

div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 20px;
  padding-top: 10px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}
div.bhoechie-tab-menu div.list-group>a.active, div.bhoechie-tab-menu div.list-group>a.active .glyphicon, div.bhoechie-tab-menu div.list-group>a.active .fa {
    background-color: #339966 !important;
    background-image: #5A55A3;
    color: #ffffff;
    background-image: linear-gradient(to bottom, #339966 0, #339966 100%) !important;
}
.list-group-item.active, .list-group-item.active:hover, .list-group-item.active:focus {
    z-index: 2;
    color: #fff;
    border-color: #339966 !important;
}
.fomr-group{
	    margin-bottom: 15px !important;
}
.selftlefpaymenu{
	background-color: #fff;
    padding-left: 15px;
    padding-top: 0px;
    padding-bottom: 10px;
    min-height: 320px;
    border-left: 1px solid #ddd;
}
</style>
<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">

	<div class="row">
	
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 15px;">	
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
				<img src="common/images/iconpayment.jpg" class="img-responsive" style="width: 60%;margin-left: auto;margin-right: auto;"></img>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 selftlefpaymenu">
				<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">  
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
			    <label for="exampleInputEmail1">Payment Type</label>
			    <s:select name="invcetype" id="invcetype" list="invoiceTypeLis" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Payment Type"/>
			  </div>
			</div>
			<input type="hidden" id="dept" name="dept">
			<%-- <div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 hidden">
		  		<div class="form-group">
			    <label for="exampleInputEmail1">Doctor List</label>
			    <s:select name="paydoctorid" id="paydoctorid" list="doctorList" cssClass="form-control chosen-select" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor"/>
		  		</div>
			</div> --%>
			<!--   12 July 2018 -->
			<%
			if (loginfo2.isVrundawan()) {
			%>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
			   		<label for="exampleInputEmail1">Charge</label>
					 <s:textfield name="opdotcharge" id="opdotcharge" 
							cssClass="form-control" />
				</div>
			</div>
			
		   <%
			} else {
		   %>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
			   		<label for="exampleInputEmail1">Charge</label>
			   		<%if(loginfo2.getClinicid1().equals("mure") || loginfo2.getClinicid1().equals("muredemo") || loginfo2.isAyushman()){ %>
					 <s:textfield name="opdotcharge" id="opdotcharge" 
							cssClass="form-control" />
							<%} else if(loginfo2.isSjivh() && loginfo2.getJobTitle().equals("Admin")){%>
							   
							 <s:textfield name="opdotcharge" id="opdotcharge" 
							  cssClass="form-control" onchange="changeOPDcharge(this.value)"  />
							<%}else{ %>
							 <s:textfield name="opdotcharge" id="opdotcharge" 
							cssClass="form-control" readonly="true" />
							<%} %>
				</div>
			</div>
			<%
			}
			%>
			
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
			   		<label for="exampleInputEmail1">Reg. Charge</label>
					<s:textfield name="opdotregcharge" id="opdotregcharge" 
							cssClass="form-control" type="number" min="0" onchange="changeOPDRegcharge(this.value)" />
				</div>
			</div>
		</div>
	</div>
			
			
			<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">  
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label>Balance to Pay <%=Constants.getCurrency(loginfo2) %></label>
					<s:textfield onchange="setAmountDueTotal(this.value);"
							name="payAmount" id="totalamount" value="%{debitTotalx}"
							cssClass="form-control" readonly="true"/>
					  </div>
				</div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
		    <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="howpaid" id="howpaid" class="form-control" onchange="getchno(this.value)">
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
				<!-- <option value="Other">Other</option> -->
				<option value="Credit">Credit</option>
				
				<!--<option value="S/O">S/O</option>
				--><s:if test="creditDebitCharge==0">
					<option value="<%=Constants.PREPYMENT %>"> Credit Balance <%=Constants.getCurrency(loginfo2) %><s:property value="balanceAmount"/></option>
				</s:if>
			</select> 
		  </div>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
			<div class="form-group hidden" id="upi">
			<label>Transaction NO.</label>
			<input type="text" id="upino" name="upino" class="form-control" >
			
			</div></div>
			
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
		    <label for="exampleInputEmail1">Payment Mode</label>
		  <s:select name="bnkname" id="bnkname" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />
		  </div>
			</div>
			
		</div>
	</div>
<%
if(loginfo2.isBalgopal()){
  if (loginfo2.isOpd_discount_access() ||  loginfo2.getUserType() == 2) {
%>	
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdiscper()"/>
					   	
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <s:textfield name="discount" id="discount" cssClass="form-control" onchange="setAmountDue()" value="0" cssStyle="color: green;font-weight: bold;" />
					  </div>
				</div>
		</div>
	</div>
	<%}else{%>
  <div class="row hidden">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdiscper()"/>
					   	
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <s:textfield name="discount" id="discount" cssClass="form-control" onchange="setAmountDue()" value="0" cssStyle="color: green;font-weight: bold;" />
					  </div>
				</div>
		</div>
	</div>


<%}}else{ %>
	  <div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:select  list="#{'0':'Percent','1':'Rupees'}" disabled="true" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdiscper()"/>
					   	
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <s:textfield name="discount" id="discount" disabled="true" cssClass="form-control" onchange="setAmountDue()" value="0" cssStyle="color: green;font-weight: bold;" />
					  </div>
				</div>
		</div>
	</div>
	
	
	
	  <%} %>  
			<div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
				    <label for="exampleInputEmail1">Payment Received <%=Constants.getCurrency(loginfo2) %></label>
				   		<s:if test="creditDebitCharge==1">
								<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
								cssClass="form-control" readonly="true" />
							</s:if>
							<s:else>
									<s:textfield name="amount" id="payAmount" value="%{debitTotalx}"
								cssClass="form-control" />
							</s:else>
				  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					
				
				</div>
		</div>
	</div>
				<div class="form-group">
				<label>Remark</label>
				<textarea rows="2" cols="5" id="remark" name="remark" class="form-control"></textarea>
				</div>	
		<%if(loginfo2.isMarkhosp()){ %>
				<br>
			<div class="row">
			
		       <div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12">
			       <div class="form-group">	
			        
			       &emsp; 
		
				     <b><span id="checktext" style="font-size:13px;"> </span></b>
			
		         </div>
			 </div>
		  </div>
		<%}else{ %>
		 <b><span id="checktext" style="font-size:13px;"> </span></b>
		<%} %>
		</div>
		
		
		</div>
		
		
		 
		
	</div>
	<br>
	<div class="hidden">
                                            <div class="">
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container">
                                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
                                                        <div class="list-group">
                                                            <a href="#" class="list-group-item active text-center">
                                                               Credit Card
                                                            </a>
                                                            <a href="#" class="list-group-item text-center">
                                                                Debit Card
                                                            </a>
                                                            <a href="#" class="list-group-item text-center">
                                                                Net Banking
                                                            </a>
                                                            <a href="#" class="list-group-item text-center">
                                                               COD
                                                            </a>
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                                                        <!-- flight section -->
                                                        <div class="bhoechie-tab-content active">
                                                            <div class="backcolorrs">
                                                                <span>You Pay: <b class="">Rs. 430</b></span>
                                                            </div>
                                                                <div class="">
                                                                    <b class="pickupde">Pay using Credit Card</b>
                                                                </div>
                                                            <form class="form-horizontal">
                                                                <div class="form-group">
                                                                    <label for="inputEmail3" class="col-sm-4 control-label paddingnil">Card Number :</label>
                                                                    <div class="col-sm-8">
                                                                        <input type="email" class="form-control" id="inputEmail3" placeholder="Card Number">
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputPassword3" class="col-sm-4 control-label paddingnil">Expiry Date :</label>
                                                                    <div class="col-sm-8">
                                                                        <div class="creditcard-inlineblock col-xs-19">
                                                                            <div class="creditcard-expiry lfloat" style="display: inline-flex;">
                                                                                <select class="card_exp_month customized s-hidden form-control" name="expMonth" style="width: 70%;">
                                                                                    <option value="" selected="selected">MM</option>
                                                                                    <option value="1">01</option>
                                                                                    <option value="2">02</option>
                                                                                    <option value="3">03</option>
                                                                                    <option value="4">04</option>
                                                                                    <option value="5">05</option>
                                                                                    <option value="6">06</option>
                                                                                    <option value="7">07</option>
                                                                                    <option value="8">08</option>
                                                                                    <option value="9">09</option>
                                                                                    <option value="10">10</option>
                                                                                    <option value="11">11</option>
                                                                                    <option value="12">12</option>
                                                                                </select>
                                                                                <span class="lfloat lineht36px">&nbsp;/&nbsp;</span>
                                                                                <select class="card_exp_year_custom customized s-hidden form-control" style="width: 70%;" name="expYear">
                                                                                    <option value="" selected="selected">YY</option>
                                                                                    <option value="16">16</option>
                                                                                    <option value="17">17</option>
                                                                                    <option value="18">18</option>
                                                                                    <option value="19">19</option>
                                                                                    <option value="20">20</option>
                                                                                    <option value="21">21</option>
                                                                                    <option value="22">22</option>
                                                                                    <option value="23">23</option>
                                                                                    <option value="24">24</option>
                                                                                    <option value="25">25</option>
                                                                                    <option value="26">26</option>
                                                                                    <option value="27">27</option>
                                                                                    <option value="28">28</option>
                                                                                    <option value="29">29</option>
                                                                                    <option value="30">30</option>
                                                                                    <option value="31">31</option>
                                                                                    <option value="32">32</option>
                                                                                    <option value="33">33</option>
                                                                                    <option value="34">34</option>
                                                                                    <option value="35">35</option>
                                                                                    <option value="36">36</option>
                                                                                    <option value="37">37</option>
                                                                                    <option value="38">38</option>
                                                                                    <option value="39">39</option>
                                                                                    <option value="40">40</option>
                                                                                    <option value="41">41</option>
                                                                                </select>
                                                                                <div class="creditcard-cvv lfloat" style="width: 65%; margin-left: 8px;">
                                                                                    <input type="password" placeholder="CVV" autocomplete="new-password" value="" maxlength="3" class="security_code valid form-control" name="cvvValidate">

                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                    </div>
                                                                </div>
                                                                <div class="form-group text-right">
                                                                    <div class="col-sm-offset-2 col-sm-10 hidden">
                                                                        <button type="submit" class="btn btn-primary" style="background-color: orange;">Make Payment</button>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <small class="smalterms">This card will be saved for a faster payment experience[?]</small>
                                                                    <small class="smalterms">By placing the order, I have read and agreed to snapdeal.com   Terms of Use  |  Terms of Sale</small>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!-- train section -->
                                                        <div class="bhoechie-tab-content">
                                                            <div class="backcolorrs">
                                                                <span>You Pay: <b class="">Rs. 430</b></span>
                                                            </div>
                                                            <div class="">
                                                                <b class="pickupde">Pay using Debit Card</b>
                                                            </div>
                                                            <form class="form-horizontal">
                                                                <div class="form-group">
                                                                    <label for="inputEmail3" class="col-sm-4 control-label paddingnil">Card Number :</label>
                                                                    <div class="col-sm-8">
                                                                        <input type="email" class="form-control" id="inputEmail3" placeholder="Card Number">
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputPassword3" class="col-sm-4 control-label paddingnil">Expiry Date :</label>
                                                                    <div class="col-sm-8">
                                                                        <div class="creditcard-inlineblock col-xs-19">
                                                                            <div class="creditcard-expiry lfloat" style="display: inline-flex;">
                                                                                <select class="card_exp_month customized s-hidden form-control" name="expMonth" style="width: 70%;">
                                                                                    <option value="" selected="selected">MM</option>
                                                                                    <option value="1">01</option>
                                                                                    <option value="2">02</option>
                                                                                    <option value="3">03</option>
                                                                                    <option value="4">04</option>
                                                                                    <option value="5">05</option>
                                                                                    <option value="6">06</option>
                                                                                    <option value="7">07</option>
                                                                                    <option value="8">08</option>
                                                                                    <option value="9">09</option>
                                                                                    <option value="10">10</option>
                                                                                    <option value="11">11</option>
                                                                                    <option value="12">12</option>
                                                                                </select>
                                                                                <span class="lfloat lineht36px">&nbsp;/&nbsp;</span>
                                                                                <select class="card_exp_year_custom customized s-hidden form-control" style="width: 70%;" name="expYear">
                                                                                    <option value="" selected="selected">YY</option>
                                                                                    <option value="16">16</option>
                                                                                    <option value="17">17</option>
                                                                                    <option value="18">18</option>
                                                                                    <option value="19">19</option>
                                                                                    <option value="20">20</option>
                                                                                    <option value="21">21</option>
                                                                                    <option value="22">22</option>
                                                                                    <option value="23">23</option>
                                                                                    <option value="24">24</option>
                                                                                    <option value="25">25</option>
                                                                                    <option value="26">26</option>
                                                                                    <option value="27">27</option>
                                                                                    <option value="28">28</option>
                                                                                    <option value="29">29</option>
                                                                                    <option value="30">30</option>
                                                                                    <option value="31">31</option>
                                                                                    <option value="32">32</option>
                                                                                    <option value="33">33</option>
                                                                                    <option value="34">34</option>
                                                                                    <option value="35">35</option>
                                                                                    <option value="36">36</option>
                                                                                    <option value="37">37</option>
                                                                                    <option value="38">38</option>
                                                                                    <option value="39">39</option>
                                                                                    <option value="40">40</option>
                                                                                    <option value="41">41</option>
                                                                                </select>
                                                                                <div class="creditcard-cvv lfloat" style="width: 65%; margin-left: 8px;">
                                                                                    <input type="password" placeholder="CVV" autocomplete="new-password" value="" maxlength="3" class="security_code valid form-control" name="cvvValidate">

                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                                <div class="form-group text-right">
                                                                    <div class="col-sm-offset-2 col-sm-10 hidden">
                                                                        <button type="submit" class="btn btn-primary" style="background-color: orange;">Make Payment</button>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <small class="smalterms">This card will be saved for a faster payment experience[?]</small>
                                                                    <small class="smalterms">By placing the order, I have read and agreed to snapdeal.com   Terms of Use  |  Terms of Sale</small>
                                                                </div>
                                                            </form>
                                                        </div>

                                                        <!-- hotel search -->
                                                        <div class="bhoechie-tab-content">
                                                            <div class="backcolorrs">
                                                                <span>You Pay: <b class="">Rs. 430</b></span>
                                                            </div>
                                                            <div class="">
                                                                <b class="pickupde">Pay using Net Banking</b>
                                                            </div>
                                                            <form class="form-horizontal">
                                                                <div class="form-group">
                                                                    <label for="inputEmail3" class="col-sm-4 control-label paddingnil">State Bank :</label>
                                                                    <div class="col-sm-8 paddingnil">
                                                                        <label>
                                                                            <input type="radio" name="ccard"><img src="http://placehold.it/50x50">
                                                                        </label>
                                                                        <label>
                                                                            <input type="radio" name="ccard"> <img src="http://placehold.it/50x50">
                                                                        </label>
                                                                        <label>
                                                                            <input type="radio" name="ccard"><img src="http://placehold.it/50x50">
                                                                        </label>
                                                                       
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="inputPassword3" class="col-sm-4 control-label paddingnil">Other Bank :</label>
                                                                    <div class="col-sm-8">
                                                                        <div class="creditcard-inlineblock col-xs-19">
                                                                            <div class="creditcard-expiry lfloat" style="display: inline-flex; width: 100%;">
                                                                                <select class="card_exp_month customized s-hidden form-control" name="expMonth">
                                                                                    <option value="" selected="selected">Select</option>
                                                                                    <option value="">State Bank of India</option>
                                                                                    <option value="1">Andhra Bank</option>
                                                                                    <option value="2">Bank of Maharashtra</option>
                                                                                    <option value="3">Bank of India</option>
                                                                                </select>
                                                                               
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                                <div class="form-group text-right">
                                                                    <div class="col-sm-offset-2 col-sm-10 hidden">
                                                                        <button type="submit" class="btn btn-primary" style="background-color: orange;">Make Payment</button>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <small class="smalterms">By placing the order, I have read and agreed to snapdeal.com   Terms of Use  |  Terms of Sale</small>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <div class="bhoechie-tab-content">
                                                            <div class="backcolorrs">
                                                                <span>You Pay: <b class="">Rs. 430</b></span>
                                                            </div>
                                                            <div class="">
                                                                <b class="pickupde">Pay using Cash On Delivery</b>
                                                            </div>
                                                            <form class="form-horizontal">
                                                                <div class="form-group text-left">
                                                                    <div class=" col-sm-10 hidden">
                                                                        <a href="#" type="button" class="btn btn-primary" style="background-color:orange;">Place Order</a>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <small class="smalterms">By placing the order, I have read and agreed to snapdeal.com   Terms of Use  |  Terms of Sale</small>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
</div>
<br>

<% String clinicid=loginfo2.getClinicid1(); %>
		<input type="hidden" id="hiddenclinic" value="<%=clinicid%>">
		

<script>
$(document).ready(function() {
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
    });
});

function getchno(val) {
	
	var clinicid=document.getElementById('hiddenclinic').value;
	
	if(clinicid=='vrundavandemo'){
		document.getElementById('discount').value=0;
		document.getElementById('payAmount').value=document.getElementById('opdotcharge').value;;
	}else{
	if(val=='Credit'){
		document.getElementById('payAmount').value=0;
	}else{
		document.getElementById('payAmount').value=document.getElementById('totalamount').value;
	}
  }	
	showupino(val);
}
function showupino(val) {
	    if(val=="UPI" || val=="Cheque" || val=="NEFT" || val=="Card"){
		document.getElementById('upi').className='form-group';
	    }else{
	    	document.getElementById('upi').className='form-group hidden';
	    }
	
}
</script>
		
		
		<!--
		
		<div class="row">
		<div class="col-lg-4 col-md-4 col-xs-5 col-sm-2">	
			<label>Payment Note </label>
		</div>
		<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">	
			:
		</div>
		<div class="col-lg-4 col-md-4 col-xs-5 col-sm-2">
			<s:textarea name="paymentNote" id="paymentNote" rows="4" cols="50"/>
		</div>	
	</div>	
	-->