<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/packagemaster.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<div class="">
<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>

							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Package Information</h4>

									</div>
								</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-12 col-md-12 topback2">
<a type="button" class="btn btn-primary hidden-print"  title="Print" onclick="printpage()"><i class="fa fa-print"> Print</i></a>
</div>

<div class="row" >
	<div class="col-lg-12">
	<div style="text-align: center;">
	<strong>Package Name :- <s:property value="Package_name"/></strong><br>
	<strong>Package Total :- <s:property value="Package_amount"/> <%=Constants.getCurrency(loginfo)%></strong><br>
	<strong>Package Duration :- <s:property value="days"/> Days</strong><br>
	</div><br><br>
	
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<td class="text-center">Sr.No</td>
						<td class="text-center" style="width: 30%"> Charges Name</td>
						<td class="text-center">Amount</td>
					</tr>
				</thead>
				<tbody>
					<s:if test="packagelist!=null">
						<%int i =0; %>
						<s:iterator value="packagelist" status="rowstatus">
								<% ++i; %>
								<tr>
								<td class="text-center"><%=i%></td>
								<td class="text-center">
								<s:property value="Chargename"/></a>
								</td>
								<td class="text-center"><s:property value="Cal_amount"/> <%=Constants.getCurrency(loginfo)%></td>
							</tr>
						</s:iterator>
						<tr>
						<td class="text-center"></td>
							<td class="text-center"><strong>Total</strong></td>
								<td class="text-center"><strong><s:property value="Package_amount"/> <%=Constants.getCurrency(loginfo)%></strong></td>
						</tr>
					</s:if>
					<s:else>
							There is no Category List found!!
					</s:else>
				</tbody>
			</table>
		</div>
		<strong>Package Description:-</strong><s:property value="Description"/>
	</div>
</div>
						

											
										</div>
									</div>
								</div>

						