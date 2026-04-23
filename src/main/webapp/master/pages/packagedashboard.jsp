<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/packagemaster.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="">

							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>All Package List</h4>

									</div>
								</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-12 col-md-12 topback2">
	<s:hidden name='clientid' id='clientid'/>
	<s:form action="packagelistPackageMaster">	
	<div class="col-lg-4 col-md-4">
	<s:textfield theme="simple" name="searchText" placeholder="Search By Package Name"  cssClass="form-control" />&nbsp;&nbsp;&nbsp;
	</div>
	<div class="col-lg-2 col-md-2">
	<s:select name="paymentmode" id="paymentmode" 
													list="#{'':'Payee ','0':'Self','1':'Third Party'}"
													cssClass="form-control" theme="simple" >
												</s:select>
	</div>
	<div class="col-lg-1 col-md-1">
	<input type="submit" value="Go" class="btn btn-primary"/>
	</div>
			
	</s:form>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Sr.No</th>
						<th class="text-center" style="width: 30%">Name</th>
						<th class="text-center">Package Type</th>
						<th class="text-center">Amount</th>
						<th class="text-center">Duration</th>
						<th class="text-center">Description</th>
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
								<a href="#" onclick="openPacsPopup('childpkglistPackageMaster?parentid=<s:property value="id"/>')" class="headprint"><strong><s:property value="name"/></strong></a>
								</td>
								<td class="text-center">
								<s:if test="tp">
								Third Party 
								</s:if>
								<s:else>
								Self
								</s:else>
								</td>
								<td class="text-center"><s:property value="amount"></s:property></td>
								<td class="text-center"><s:property value="days"></s:property></td>
								<td class="text-center"><s:property value="description"></s:property></td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
							There is no Category List found!!
					</s:else>
				</tbody>
			</table>
		</div>
	</div>
</div>
<s:form action="PackageMaster" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
						

											
										</div>
									</div>
								</div>

						