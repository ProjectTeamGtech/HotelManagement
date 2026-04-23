<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.ThirdParties.eu.entity.OutstandingReport"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/allocation.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									<div class="col-lg-6 col-md-6 col-xs-12">
										<h4>Allocation List - <small style="color:#fff;">Receipt no : <s:property value="recno"/></small> &nbsp; | &nbsp; <small style="color:#fff;">Allocated Amount : <s:property value="recamt"/></small> &nbsp; | &nbsp; <small style="color:#fff;">Company : <s:property value="thirdParty"/></small></h4> 
									</div>
									<div class="col-lg-6 col-md-6 col-xs-12 text-right">
										<span onclick="updatealt()" style="float:right;margin-top: 3px;"><input type="button" value="Save" class="btn btn-warning btn-sm"> </span>
									</div>
								
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>

<s:form action="updatealtOutstandingReport" id="altfrm"> 
<s:hidden name="recno"/>
<s:hidden name="tpid"/>
<s:if test="invoiceList!=null">
		
						<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
						<th style="width: 10%;">Patient</th>
						<th style="width: 3%;">B.No</th>
						<th style="width: 5%;">Date</th>
						<th style="width: 7%;text-align:right;">Balance Amt</th>
						<th style="width: 8%;text-align:right;">Dedtn</th>
						<th style="width: 8%;text-align:right;">TDS</th>
						<th style="width: 8%;text-align:right;">ST/Medicine</th>
						<th style="width: 8%;text-align:right;">Recd. Amt</th>
						<th style="width: 8%;text-align:right;">Running Balance</th>
						<th style="width: 6%;text-align:right;">Total Net</th>
						<th style="width: 7%;text-align:right;">Advance Amt</th>
						<th style="width: 6%;text-align:right;">Disc Amt</th>
						<th style="width: 5%;text-align:right;">Host Net</th>
						<th style="width: 6%;text-align:right;">Pharm Net</th>
						<th style="width: 6%;text-align:right;">Other Net</th>
						
					</thead>
					
					<tbody>
					<s:if test="invoiceList.size>0">
						<s:iterator value="invoiceList">
							
								<tr>
         	<td><s:property value="clientName"/></td>
        	 <td><s:property value="invoiceNo"/></td>
         
        	 <td><s:property value="date"/></td>
        	 <td><s:property value="balancex"/></td>
       	 	 <td><input onchange="showRniningBaCalc(<s:property value="invoiceNo"/>,<s:property value="debitAmountx"/>)" type="number" class="form-control" name="dedtn<s:property value="invoiceNo"/>" id="dedtn<s:property value="invoiceNo"/>"  style="text-align: right;" value="<s:property value="dedtn"/>" ></td>
       	  	<td><input onchange="showRniningBaCalc(<s:property value="invoiceNo"/>,<s:property value="debitAmountx"/>)" type="number" class="form-control" name="tds<s:property value="invoiceNo"/>" id="tds<s:property value="invoiceNo"/>"   style="text-align: right;" value="<s:property value="tds"/>" ></td>
         	<td><input onchange="showRniningBaCalc(<s:property value="invoiceNo"/>,<s:property value="debitAmountx"/>)" type="number" class="form-control" name="stmdcine<s:property value="invoiceNo"/>" id="stmdcine<s:property value="invoiceNo"/>"   style="text-align: right;" value="<s:property value="stmdcine"/>" ></td>
         	<td><input onchange="showRniningBaCalc(<s:property value="invoiceNo"/>,<s:property value="debitAmountx"/>)" type="number" class="form-control" name="recdamt<s:property value="invoiceNo"/>"  id="recdamt<s:property value="invoiceNo"/>"  style="text-align: right;"  value="<s:property value="recdamt"/>"></td>
         	<td><input type="number" class="form-control" name="runbal<s:property value="invoiceNo"/>" id="runbal<s:property value="invoiceNo"/>"  style="text-align: right;"  value="<s:property value="balancex"/>"></td>
         	<td><s:property value="debitAmountx"/></td>
         	<td>00.00</td>
         
         <td><s:property value="discAmmount"/></td>
         <td><s:property value="hospchargel"/></td>
         <td><s:property value="mdicinecharge"/></td>
         <td>00.00</td>
         
        </tr>
								
								       
										
									
									
								
							
								</s:iterator>
								 </s:if>
								 <s:else>	
								 	<h3 class="text-center"><i class="fa fa-times text-danger"></i> There is no Record found!!</h3>
								 </s:else>
								</tbody>
							
							</table>
					
		</s:if>
		</s:form>