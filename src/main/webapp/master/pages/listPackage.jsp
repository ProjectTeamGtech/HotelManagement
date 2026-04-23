<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/packagemaster.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript">
function deletePacc(){
var agree=confirm("Are you sure you want to delete this package?");
	if(agree){
    	 return true ;
	}
	else{
    	 return false ;
	}
}
</script>
<style>
ul.breadcrumb {
  list-style: none;
  background-color: #eee;
}
ul.breadcrumb li {
  display: inline;
  font-size: 15px;
}
ul.breadcrumb li+li:before {
  color: black;
  content: ">\00a0";
}
ul.breadcrumb li a {
  color: #0275d8;
  text-decoration: none;
}
ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}
ul, ol {
    margin-top: 0 !important;
    margin-bottom: 0px !important;
}
.breadcrumb {
     padding: 0px 0px !important; 
     margin-bottom: 0px !important;
}
</style>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>All Package List</h4>

									</div>
								</div>
								<div class="print-hidden">
								<ul class="breadcrumb">
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
	<s:hidden name='clientid' id='clientid'/>
		<div class="col-lg-2 col-md-2">
			<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#pacakgesp"><i
				class="fa fa-plus"></i> Daycare/OPD/Investigation</a>
		</div>
		<div class="col-lg-2 col-md-2">
			<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#ipdpackage"><i
				class="fa fa-plus"></i> IPD Package (Self)</a>
		</div>
		<div class="col-lg-2 col-md-2" style="margin-left: -46px;">
			<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#tppackage"><i
				class="fa fa-plus"></i> IPD Package (TP)</a>
		</div>
	<s:form action="PackageMaster">	
		<div class="col-lg-3 col-md-2" style="margin-left: -58px;">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Package Name"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1" style="margin-left: -10px;">
			<input type="submit" value="Go" class="btn btn-primary"/>
		</div>
	</s:form>
</div>
<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('message').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
		</s:if>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Sr.No</th>
						<th class="text-center">Name</th>
						<th class="text-center">Package Type</th>
						<th class="text-center">Amount</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="packagelist!=null">
						<%int i =0; %>
						<s:iterator value="packagelist" status="rowstatus">
								<% ++i; %>
								<tr>
								<td class="text-center"><%=i%></td>
								<td class="text-center"><s:property value="Name"></s:property></td>
								<td class="text-center">
								<s:if test="tp">
								Third Party
								</s:if>
								<s:else>
								Self
								</s:else>
								<td class="text-center"><s:property value="amount"></s:property></td>
								<td class="text-center">
								<s:if test="tp">
								<a href="#" onclick="editTpPkg(<s:property value="id"/>)"><i class="fa fa-edit"></i>	</a>
								</s:if>
								<s:else>
								<a href="#" onclick="editPackage(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>
								</s:else>
								</td>
								<td class="text-center"><a href="deletePackageMaster?id=<s:property value="id"/>" onclick="return deletePacc()" class="text-danger">
										<i class="fa fa-trash-o"></i>
								</a></td>
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
							</div>
						</div>
						
						
<!-- packages model -->
<div class="modal fade" id="pacakgesp" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Packages : (Fixed Cost Package) 
        	&nbsp;&nbsp;&nbsp;<input type="radio" id="packageA" value='packageA' onclick="resetAllPackageData(this.value)" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	(Service Base Package) <input type="radio" onclick="resetAllPackageData(this.value)" id="packageB">
        </h4>
      </div>
      <s:form action="savePackageMaster" theme="simple" id="savepackageform">
      <input type="hidden" name="packagetype" id="packagetype" value="0">
       <input type="hidden" name="invechargeid" id="invechargeid">
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
        
        	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				    <input type="text" class="form-control" onchange="checkPackageName(this.value)" name="package_name" id="package_name" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount" id="package_amount" placeholder="Package Amount" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <s:select cssClass="form-control" list="#{'0':'Other','1':'Investigation'}" onchange="setChargeNameList(this.value)" name="chargetype" id="chargetype" />
				  </div>
				</div>
        	</div>
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        		<div class="col-lg-9 col-md-9 col-xs-8" style="padding: 0px;">
        		<div class="col-lg-12 col-md-12 col-xs-12" style="padding-left: 0px;">
        		<div class="form-inline">
					  <div class="form-group" style="width: 78%;" id="chargetypediv">
					    	<s:select list="masterChageTypeList" headerKey="0" headerValue="Select Charge Type" name="charge" id="charge" listKey="id" listValue="name" cssClass="form-control chosen-select" style="width:100%;"></s:select>
					  </div>
					  <input type="button" onclick="addcharge()"  class="btn btn-warning" value="Add">
					  <!--<button class="btn btn-warning" onclick="addcharge()">Add</button>
					  --><!--<button class="btn btn-warning" data-toggle="collapse" data-target="#packagedemo">Add More</button>
					--></div>
        		</div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-4" style="padding-right: 0px;">
        		<div class="form-group hidden">  
        			<span style="float: right;font-size: 15px;color: green;" >Amount : Rs.1,00,000.00</span>
        		</div>
        	</div>
        	</div>
        	
        	<div id="packagedemo" class="collapse hidden">
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        			<div class="form-inline">
						  <div class="form-group" style="width: 426px;">
						    <label class="sr-only" for="exampleInputEmail3">Email address</label>
						    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="Enter name" style="width:100%;background-color: aliceblue;">
						  </div>
						  <div class="form-group" style="width: 45px;">
						    <label class="sr-only" for="exampleInputPassword3">Password</label>
						    <input type="text" class="form-control" id="exampleInputPassword3" placeholder="%" style="width:100%;background-color: aliceblue;">
						  </div>
						  <!--<button type="submit" class="btn btn-info">+</button>
						--></div>
        		</div>
			</div>
        	
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
	        		<table class="table" style="border: none;" id="tablecount">
	        		<thead id="addpackagetbody">
	        			<tr>
	        				<th style="width: 60%;background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        			</tr>
	        		</thead>
	        			<tbody id="addpackagetbody2">
	        				<!--<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Ortho Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="50%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.50,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">OT Charge</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="20%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.20,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Anesthesia Surgen</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Equipment</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;">
	        					<td style="font-size: 14px;">Other</td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="10%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><input style="text-align: right;" type="text" class="form-control" value="Rs.10,000.00"></td>
	        					<td><a href="#" style="color:#d9534f;font-size: 15px;"><i class="fa fa-times" aria-hidden="true"></i></a></td>
	        				</tr>
	        				<tr style="border-top: none !important;background-color: cornsilk;">
	        					<td style="font-size: 14px;"><b>Total</b></td>
	        					<td style="font-size: 14px;"><input type="text" class="form-control" value="100%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><b><input style="text-align: right;" type="text" class="form-control" value="Rs.1,00,000.00"></b></td>
	        					<td></td>
	        				</tr>
	        			--></tbody>
	        		</table>
	        		<input type="hidden" id="tempcount">
	        		<input type="hidden" id="tempcounttp">
	        		<input type="hidden" id="validname">
	        		<table class="table hidden" style="border: none;">
	        		<thead>
	        			<tr>
	        				<th style="width: 60%;background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        			</tr>
	        		</thead>
	        			<tbody>
	        				<tr style="border-top: none !important;background-color: cornsilk;">
	        					<td style="font-size: 14px;"><b>Total</b></td>
	        					<td style="font-size: 14px;"><input type="text"  class="form-control"  value="100%" style="width: 35%;"></td>
	        					<td style="text-align: right;font-size: 14px;"><b><input style="text-align: right;" type="text" class="form-control" value="Rs.1,00,000.00"></b></td>
	        					<td></td>
	        				</tr>
	        			</tbody>
	        		</table>
        	</div>
        </div>
      </div>
      <div class="modal-footer" style="padding-right: 10px;" id="savebtnid">
        <button type="button" class="btn btn-primary" onclick="savePackage()" style="margin-top: 15px;">Save</button>
      </div>
      </s:form>
      
      

      
    </div>
  </div>
</div>



<!-- packages model -->
<div class="modal fade" id="editpacakge" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="false" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Packages</h4>
      </div>
      <s:form action="updatePackageMaster" theme="simple" id="updatepackageform">
       <input type="hidden" name="invechargeid" id="editinvechargeid">
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
        	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				  	<input type="hidden" id="parentid" name="parentid">
				    <input type="text" class="form-control" name="package_name" id="editpackage_name" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount" id="editpackage_amount" placeholder="Package Amount" style="width:100%;">
				  </div>
				   <div class="form-group" id="editpkgtype">
				   
				  </div>
				</div>
        	</div>
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        		<div class="col-lg-9 col-md-9 col-xs-8" style="padding: 0px;">
        		<div class="col-lg-12 col-md-12 col-xs-12" style="padding-left: 0px;">
        		<div class="form-inline">
					  <div class="form-group " style="width: 78%;" id="editchargelistid">
					    	
					  </div>
					  <input type="button" onclick="editchargeadd()" class="btn btn-warning" value="Add">
				</div>
        	</div>
        	</div>
        	<div class="col-lg-3 col-md-3 col-xs-4" style="padding-right: 0px;">
        		
        	</div>
        	</div>
        	
        	<div  class="collapse hidden">
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;margin-top: 10px;">
        			<div class="form-inline">
					</div>
        		</div>
			</div>
        	
        	
        	<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
	        		<table class="table" style="border: none;" id="edittableidd">
	        		<thead>
	        			<tr>
	        				<th style="width: 60%;background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        				<th style="background-color: transparent;"></th>
	        			</tr>
	        		</thead>
	        			<tbody id="edittbodyid">
	        			
	        			</tbody>
	        		</table>
	        		<input type="hidden" id="edittempcount">
	        </div>
        </div>
      </div>
      <div class="modal-footer" style="padding-right: 10px;">
        <button type="button" class="btn btn-primary" onclick="updatePackage()" style="margin-top: 15px;">Update</button>
      </div>
      </s:form>
    </div>
  </div>
</div>

<!-- Third party -->
 <s:form action="saveTpPkgPackageMaster" theme="simple" id="savepackageformtp"> 			
<div id="tppackage" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -350px !important;margin-right: -350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">IPD Packages(TP) </h4>
      </div>
      <div class="modal-body">
  <input type="hidden" name="inveschargeidtp" id="inveschargeidtp">
  <input type="hidden"  name="pkgtypetp" id="pkgtypetp" value='1'>
			<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				    <input type="text" class="form-control" onchange="checkPackageName(this.value)" name="package_name_tp" id="package_name_tp" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount_tp" id="package_amount_tp" placeholder="Package Amount" style="width:100%;" >
				  </div>
				  <div class="form-group">
				    <s:select cssClass="form-control" list="#{'0':'Other','1':'Investigation','2':'IP/OP Procedures'}" onchange="setChargeNameListTP(this.value)" name="chargetype_tp" id="chargetype_tp" />
				  </div>
				  <input type="hidden"  name="payee" id="payee" value='1'>
				  
				</div>
        	</div>
        	
        <!--Add Charges  -->	
        	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
        	<div class="col-lg-8 col-md-8 col-xs-8" >
        	<div class="form-inline">
					  <div class="form-group" style="width: 78%;" id="chargetypedivtp">
					    	<s:select list="masterChageTypeList" headerKey="0" headerValue="Select Charge Type" name="charge" id="chargetp" listKey="id" listValue="name" cssClass="form-control chosen-select" style="width:100%;"></s:select>
					  </div>
					  
					 <input type="button" onclick="addchargetp()"  class="btn btn-warning" value="Add">
        	</div>
			</div>
			</div>
			<%-- <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
			<s:textarea name="description" id="description"/> 
			</div> --%>
			<table class="table" style="border: none;" id="tablecounttp">
			
	        		<thead id="addpackagetbodytp">
	        			
	        		</thead>
	        </table>		
			
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         <input type="button" class="btn btn-success" value="Save" onclick="saveTPpkg()">
        
      </div>
    </div>

  </div>
</div>
</div>
</s:form>						
						
<!--Edit Third party -->
 <s:form action="updateTpPkgPackageMaster" theme="simple" id="updatepackageformtp"> 			
<div id="tppackage_edit" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -350px !important;margin-right: -350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Third Party Packages </h4>
      </div>
      <input type="hidden" name="edittpparentid" id="edittpparentid">
      <div class="modal-body">
  <input type="hidden" name="inveschargeidtp_edit" id="inveschargeidtp_edit">
  <input type="hidden"  name="pkgtypetp_edit" id="pkgtypetp_edit" value='1'>
  <input type="hidden" name="edittpcount" id='edittpcount'>
			<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				    <input type="text" class="form-control" onchange="checkPackageName(this.value)" name="package_name_tp_edit" id="package_name_tp_edit" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount_tp_edit" id="package_amount_tp_edit" placeholder="Package Amount" style="width:100%;" readonly>
				  </div>
				  <div class="form-group">
				    <s:select cssClass="form-control" list="#{'0':'Other','1':'Investigation','2':'IP/OP Procedure'}" onchange="setChargeNameListTPEdit(this.value)" name="chargetype_tp_edit" id="chargetype_tp_edit" />
				  </div>
				</div>
        	</div>
        	
        <!--Add Charges  -->	
        	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
        	<div class="col-lg-8 col-md-8 col-xs-8" >
        	<div class="form-inline">
					  <div class="form-group" style="width: 78%;" id="chargetypediv1">
					    	<s:select list="masterChageTypeList" headerKey="0" headerValue="Select Charge Type" name="charge" id="chargetp_edit" listKey="id" listValue="name" cssClass="form-control chosen-select" style="width:100%;"></s:select>
					  </div>
					  <input type="button" onclick="addTotempedit()"  class="btn btn-warning" value="Add">
					 
					 
        	</div>
			</div>
			</div>
			
			<table class="table" style="border: none;" id="tablecounttp_edit">
	        		<tbody id="table_edit_tp">
	        		</tbody>
	        </table>		
			
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         <input type="button" class="btn btn-success" value="Save" onclick="saveTPpkg_edit()">
        
      </div>
    </div>

  </div>
</div>
</div>
</s:form>						
		<!-- IPD PAckage-->
 <s:form action="saveTpPkgPackageMaster" theme="simple" id="savepackageformtp1"> 			
<div id="ipdpackage" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -350px !important;margin-right: -350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">IPD Packages (Self) </h4>
      </div>
      <div class="modal-body">
  <input type="hidden" name="inveschargeidtp" id="inveschargeidtp">
  <input type="hidden"  name="pkgtypetp" id="pkgtypetp" value='0'>
			<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				    <input type="text" class="form-control" onchange="checkPackageName(this.value)" name="package_name_tp" id="package_name_self" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount_tp" id="package_amount_self" placeholder="Package Amount" style="width:100%;" >
				  </div>
				  <div class="form-group" style="width: 25%">
				   <s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="id" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
															onchange="filterCharges(this.value)"/>
															</div>
				  <input type="hidden"  name="payee" id="payee" value='0'>
				</div>
        	</div>
        	
        <!--Add Charges  -->	
        	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
        	<div class="col-lg-8 col-md-8 col-xs-8" >
        	<div class="form-inline">
					 <!--  <div class="form-group" style="width: 78%;" id="chargetypedivtp"> -->
					    	<%-- <s:select list="masterChageTypeList" headerKey="0" headerValue="Select Charge Type" name="charge" id="chargetp" listKey="id" listValue="name" cssClass="form-control chosen-select" style="width:100%;"></s:select> --%>
					 <div class="form-group" style="width: 65%">
					 <div id="additionalChargeAjax" style="margin-bottom: 2px;">
														<s:select theme="simple" name="chargeTYpe" id="chargeTYpe" list="additionalChargesList" listKey="id" listValue="name"
														headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
														/>
														</div>
					 </div> 
					<div class="form-group">
					 <input type="Number" class="form-control" name="days" id="days" placeholder="Package Duration">
					 </div>
					 <div class="form-group">
					  
					  </div>
					 <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
			<s:textarea name="description" id="description" cssStyle="width: 155%;" cols="5" rows="5" placeholder="Package description"/> 
			
			</div>
			<input type="button" onclick="addchargeself()"  class="btn btn-warning" value="Add">
        	</div>
			</div>
			</div>
			
			<table class="table" style="border: none;" id="tablecountself">
	        		<thead id="addpackagetbodytp">
	        			
	        		</thead>
	        </table>		
			
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         <input type="button" class="btn btn-success" id="selfpkg" value="Save" onclick="saveSelfpkg()">
        
      </div>
    </div>

  </div>
</div>
</div>
</s:form>						
						
<!--Edit IPD PAckage -->
 <s:form action="updateTpPkgPackageMaster" theme="simple" id="updatepackageformtp"> 			
<div id="tppackage_edit" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="margin-left: -350px !important;margin-right: -350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">IPD Packages </h4>
      </div>
      <input type="hidden" name="edittpparentid" id="edittpparentid">
      <div class="modal-body">
  <input type="hidden" name="inveschargeidtp_edit" id="inveschargeidtp_edit">
  <input type="hidden"  name="pkgtypetp_edit" id="pkgtypetp_edit" value='1'>
  <input type="hidden" name="edittpcount" id='edittpcount'>
			<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: #efefef;padding: 5px 5px 5px 5px;">
        		<div class="form-inline">
				  <div class="form-group" style="width: 57%;">
				    <input type="text" class="form-control" onchange="checkPackageName(this.value)" name="package_name_tp_edit" id="package_name_tp_edit" placeholder="Package Name" style="width:100%;">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="package_amount_tp_edit" id="package_amount_tp_edit" placeholder="Package Amount" style="width:100%;" readonly>
				  </div>
				  <div class="form-group">
				    <s:select cssClass="form-control" list="#{'0':'Other','1':'Investigation','2':'IP/OP Procedure'}" onchange="setChargeNameListTPEdit(this.value)" name="chargetype_tp_edit" id="chargetype_tp_edit" />
				  </div>
				</div>
        	</div>
        	
        <!--Add Charges  -->	
        	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;margin-bottom: 20px;">
        	<div class="col-lg-8 col-md-8 col-xs-8" >
        	<div class="form-inline">
					  <div class="form-group" style="width: 78%;" id="chargetypediv1">
					    	<s:select list="masterChageTypeList" headerKey="0" headerValue="Select Charge Type" name="charge" id="chargetp_edit" listKey="id" listValue="name" cssClass="form-control chosen-select" style="width:100%;"></s:select>
					  </div>
					  <input type="button" onclick="addTotempedit()"  class="btn btn-warning" value="Add">
					 
					 
        	</div>
			</div>
			</div>
			
			<table class="table" style="border: none;" id="tablecounttp_edit">
	        		<tbody id="table_edit_tp">
	        		</tbody>
	        </table>		
			
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
         <input type="button" class="btn btn-success" value="Save" onclick="saveTPpkg_edit()">
        
      </div>
    </div>

  </div>
</div>
</div>
</s:form>				
	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
  </script>					