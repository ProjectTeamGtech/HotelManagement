<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content=s"text/html; charset=ISO-8859-1">
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">

<style>
	hr {
    margin-top: 8px;
    margin-bottom: 8px;
}
.imflag{
	display: inline-block;
    width: 88%;
}
.bg-lightred {
    background-color: #e05d6f !important;
}
ul, ol {
    margin-top: 0;
    margin-bottom: 8.5px;
    list-style: none;
    padding: 0px;
}
.checkbox-custom, .checkbox-custom-alt {
    padding-left: 20px;
    cursor: pointer;
    margin-top: 3px !important;
    margin-bottom: 0px !important;
}
</style>
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

<SCRIPT type="text/javascript">

       window.onload = function(){
       
                 
                 var p_val= document.getElementById("procurementType").value;
                 if(p_val=="1"){
                 	document.getElementById("prdemail").checked= true;
                 } else {
                	 document.getElementById("prdcall").checked= true;
                 }
                    var p_type= document.getElementById("printType").value;
                 if(p_type=="0"){
                 	document.getElementById("printnormal").checked= true;
                 } else {
                	 document.getElementById("printa4").checked= true;
                 }
                 document.addEventListener("contextmenu", function(e){
             		e.preventDefault();
             		}, false); 
       };

</SCRIPT>

<%String showletterhd=(String)session.getAttribute("showletterhd");
if(showletterhd==null){
	showletterhd="false";
}
%>
</head>
<body>
						
							
							<div class="">
							<%-- <div class="hidden-print">
								<ul class="breadcrumb">
									&nbsp;
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getShowingname()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div> --%>
							<s:form theme="simple" action="updatesettingPharmacy" onsubmit="return updateSetting()" method="post">
								<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 15px;padding:0px;">
									
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="border-right: 1px solid #ddd;min-height: 500px;">
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
										<h4 style="color: indianred;"><b>Profile Setting</b> <small style="color: #f0ad4e;">(Header)</small></h4>
										
											<div class="col-lg-4 col-xs-4 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Pharmacy Name</label>
												    <s:textfield cssClass="form-control" name="clinicName" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">D.L.NO</label>
												    <s:textfield cssClass="form-control" name="dlno" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">GST NO</label>
												   <s:textfield cssClass="form-control" name="tinno" />
												  </div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-10 col-xs-10 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Address</label>
												    <s:textfield cssClass="form-control" name="address" />
												  </div>
											</div>
											<div class="col-lg-2 col-xs-2 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">City</label>
												    <s:textfield cssClass="form-control" name="city" />
												  </div>
											</div>
										</div>
										
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-4 col-xs-4 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Website</label>
												    <s:textfield cssClass="form-control" name="websiteUrl" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Email</label>
												    <s:textfield cssClass="form-control" name="email" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Contact</label>
												   <s:textfield cssClass="form-control" name="landLine" />
												  </div>
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;">
											<div class="col-lg-4 col-xs-4 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Username</label>
												    <s:textfield cssClass="form-control" name="userid" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Password</label>
												    <s:password cssClass="form-control" name="password" />
												  </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-xs-12">
												
											</div>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
										<h4><small style="color: #f0ad4e;">(Footer - Instruction)</small></h4>
										
											<div class="col-lg-6 col-xs-6 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 1</label>
												     <s:textfield cssClass="form-control" name="instruction1" />
												  </div>
											</div>
											<div class="col-lg-6 col-xs-6 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 2</label>
												     <s:textfield cssClass="form-control" name="instruction2" />
												  </div>
											</div>
											
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-6 col-xs-6 col-xs-12" style="padding-left: 0px;">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 3</label>
												    <s:textfield cssClass="form-control" name="instruction3" />
												  </div>
											</div>
											<div class="col-lg-6 col-xs-6 col-xs-12">
												<div class="form-group">
												    <label for="exampleInputEmail1">Instruction No 4</label>
												    <s:textfield cssClass="form-control" name="instruction4" />
												  </div>
											</div>
											
										</div>
										
										<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 20px;padding: 0px;">
           								<div class="col-lg-3 col-md-3 col-md-3" style="padding: 0px;">
								            <div class="form-group" id="toastTypeGroup">
									            <h4 style="color: indianred;"><b>Purchase Order </b></h4>
                                                <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                   <input type="radio" name="prd" id="prdcall" value="0" /><i></i> Short Process
                                                </label>
                                                <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="prd" id="prdemail" value="1" /><i></i> Long Process
                                                </label>
                                           	</div>
                                           	<div class="form-group">
									            <h4 style="color: indianred;"><b>By Default Discount</b></h4>
                                                <label>IPD:</label>
                                                <s:textfield  style="width: 30%;" name="ipddiscount" id="ipddiscount"></s:textfield>
                                                <label>OPD:</label>
									            <s:textfield  style="width: 30%;" name="opddiscount" id="opddiscount"></s:textfield>
                                                <label>Pharmacy Patient:</label>
									            <s:textfield  style="width: 30%;" name="pharpatientdiscount" id="pharpatientdiscount"></s:textfield>
									        </div>
                                           	
           							    </div>
           							<div class="col-lg-3 col-md-3 col-md-3">
             <h4 style="color: indianred;"><b>Printing Option </b></h4>
									            <div class="form-group" id="toastTypeGroup">
									            <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="print" id="" value="0" /><i></i> Paper Size 6x4
                                                </label>
                                                <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="print" id="printnormal" value="0" /><i></i> Paper Size 6x6
                                                </label>
                                                <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="print" id="printa4" value="1" /><i></i> Paper Size A4
                                                </label>
                                                <%if(showletterhd.equals("true")){ %>
                                                 <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="letterhd" id="letterhd" value="1" checked="checked"/><i></i> Show LetterHead
                                                </label>
                                                
                                                <%} else{%>
                                                 <label class="checkbox checkbox-custom-alt checkbox-custom-sm">
                                                    <input type="radio" name="letterhd" id="letterhd" value="1" /><i></i> Show LetterHead
                                                </label>
                                                <%} %>
                                                <label>
                                                <br>
                                                
                                                <input type="number" name="pagelimit" id="pagelimit" value='<s:property value="pagelimit"/>' min="1" max="10" ><i></i> Limit per page 
                                                
                                                </label>
                                                <label>
                                                <s:if test="isdotmatrix">
                                                <input type="checkbox" name="isdotmatrix" value="1" checked> <sapn>Dot Matrix Printer</sapn>
                                                </s:if>
                                                <s:else>
                                                    <input type="checkbox" name="isdotmatrix" value="1"> <sapn>Dot Matrix Printer</sapn>
                                                </s:else>
                                                </label>
                                            </div>
          
           </div>
            <div class="col-lg-3 col-md-3 col-md-3" style="padding: 0px;">
									            <div class="form-group">
									             <h4 style="color: indianred;"><b>Product Sale limit</b></h4>
									             <label>Product limit</label>
									             <input type="number" name="medlimit" id="medlimit" value='<s:property value="medlimit"/>'>
									              
                                         </div>
                                         <div class="form-group">
		             <h4 style="color: indianred;"><b>Product Barcode</b></h4>
		             <s:checkbox name="nonsystembarcode"></s:checkbox> <sapn>Non-System Barcode Scan(Which came with product)</sapn>
	            </div>
                                         </div>   
           <div class="col-lg-3 col-md-3 col-md-3" id="inhousepatientdiv">
           
           <ul class="vertical default_list" id="">
           
										                 
										                  
										               </ul>
           		
           </div>
           <div class="col-lg-3 col-md-3 col-md-3"></div>
          </div>
										<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding: 0px;">
											<h4 style="color: indianred;"><b>Location Setting</b></h4>
											<div class="col-lg-3 col-md-3 col-md-3" style="padding: 0px;">
												<div class="form-group">
													<lable>OPD</lable>
													<article>
										               <div class="secoconsullist">
										               <ul class="vertical default_list" id="">
										                <s:iterator value="locationListPharmacy">
										                <s:if test="opd==1">
										                	<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" checked="checked" value="<s:property value="id"/>" class="opd"><i></i>  <s:property value="name"/></label></li>
										                </s:if>
										                <s:else>
															<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"  value="<s:property value="id"/>" class="opd"><i></i>  <s:property value="name"/></label></li>						                
										                </s:else>
										                
										                </s:iterator>
										               </ul>
														</div>
										             </article>
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-md-3">
												<div class="form-group">
													<lable>IPD</lable>
													<article>
										               <div class="secoconsullist">
										               <ul class="vertical default_list" id="">
										                 <s:iterator value="locationListPharmacy"> 
										                      <s:if test="ipd==1">
										                         <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" checked="checked" value="<s:property value="id"/>" class="ipd"><i></i>  <s:property value="name"/></label></li>
										                      </s:if>
										                      <s:else>
																	<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" value="<s:property value="id"/>" class="ipd"><i></i>  <s:property value="name"/></label></li>					                      
										                      </s:else>
										                    
										                 </s:iterator>
										                  
										               </ul>
														</div>
										             </article>
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-md-3 ">
											<div class="form-group">
													<lable>INHOUSE PATIENT</lable>
													<article>
										               <div class="secoconsullist">
										               <ul class="vertical default_list" id="">
										                 <s:iterator value="locationListPharmacy"> 
												              <s:if test="ihpatient==1">
		           												<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="ihplocation" value="<s:property value="id"/>" checked="checked"  class="inhouse" ><i></i> <s:property value="name"/></label></li>
           													  </s:if>
           													  <s:else>
           													  <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="ihplocation" value="<s:property value="id"/>"   class="inhouse"  ><i></i>  <s:property value="name"/></label></li>
           													   </s:else>
										                 </s:iterator>
										                  
										               </ul>
														</div>
										             </article>
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-md-3"></div>
										</div>
										
									</div>
									<s:hidden name="ipdlocation" id="ipdlocation"></s:hidden>
									<s:hidden name="opdlocation" id="opdlocation"></s:hidden>
									 <s:hidden name="procurementType" id="procurementType"></s:hidden>
									 <s:hidden name="printType" id="printType"></s:hidden>
									 <s:hidden name="inhousepatient" id="inhousepatient"></s:hidden>
								 </s:form>
								
								
									<div class="col-lg-6 col-xs-6 col-sm-6 col-md-6" style="padding: 0px;">
										<div  class="col-lg-12 col-md-12 col-xs-12 minhesigh">
										<h4 style="color: indianred;"><b>User Setting</b><div class="pull-right"><a href="#" onclick="addNewuser('mytable')" class="btn btn-sm btn-warning" style="margin-top: -7px;">Add User</a></div></h4>
											<table class="table table-striped my-table xlstable" id="mytable" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 3%;">Sr.</th>
											<th style="width: 20%;">User Name</th>
											<!--<th style="width: 15%;">Contact No</th>
											-->
											<th style="width: 15%;">Location</th>
											<th style="width: 15%;">State</th>
											<th style="width: 15%;">User_ID</th>
											<th style="width: 14%;">Password</th>
											<th style="width: 2%;">Status</th>
											<th>Access</th>
											<th style="width: 2%;">Edit</th>
											<th class="hidden">Delete</th>
										</tr>
									</thead>
									
								 <tbody>
								 <%int i=0; %>
								  	<s:iterator value="pharmacyUserList">
										<tr id="<s:property value="id"/>">
											<td><%=(++i) %></td>
											<td><s:property value="fullname"/></td>
											<!--<td><s:property value="phone"/></td>
											-->
											<td><s:property value="location"/></td>
											<td><s:property value="state"/></td>
											<td><s:property value="userid"/></td>
											<td>***</td>
											<td>
												<div class="onoffswitch greensea">
													<s:if test="islogin==0">
														<input onclick="setLoginAccesss(<s:property value="id"/>,<s:property value="islogin"/>)" type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline<%=i %>" >
													</s:if>
													<s:else>
														<input onclick="setLoginAccesss(<s:property value="id"/>,<s:property value="islogin"/>)" type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline<%=i %>" checked="checked">
													</s:else>
                                                    
                                                    <label class="onoffswitch-label" for="show-offline<%=i %>">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
											</td>
											
											<td><a href="#" onclick="openUserSetting(<s:property value="id"/>,<s:property value="islogin"/>)">Setting</a></td>
											<td><a href="#" onclick=editUserSetting(<%=i%>,<s:property value="id"/>,<s:property value="islogin"/>) >Edit</a></td>
											<td class="text-center hidden"><a href="#" onclick="deletepharmacyuser(<s:property value="id"/>)"><i class="fa fa-trash-o" style="color:red;"></i></a></td>
										</tr>
								</s:iterator>			
									</tbody>
								</table>
										</div>
										
										
          								<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top: 15px;">
											<div class="col-lg-12 col-xs-12 col-xs-12 text-right" style="padding-left: 0px;">
												<button type="submit" class="btn btn-primary">Update</button>
											</div>
										</div>
									</div>
								</div>
							
							
							
							
							<!-- Setting Access -->
							<div id="accessset" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title">Access Setting</h4>
							      </div>
							      <div class="modal-body">
							      <div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
							      		<ul class="settings" style="padding: 0px;list-style: none;">
							      		<s:hidden id="uid" name="uid"></s:hidden>
                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Create Sale Bill</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="salebilldiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2" checked="">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Give Discount</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="discountdiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-fullname3">
                                                    <label class="onoffswitch-label" for="show-fullname3">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">View Stock</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="ledgerdiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-history4" checked="">
                                                    <label class="onoffswitch-label" for="show-history4">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">View Profit & Loss</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="accountdiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-location5" checked="">
                                                    <label class="onoffswitch-label" for="show-location5">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="hidden">
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Create Purchase Order</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="purchasediv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-notifications">
                                                    <label class="onoffswitch-label" for="show-notifications">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                            <label class="col-xs-9 control-label">Send SMS Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="sendSMSdiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2" checked="">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Edit Back Date Bill</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="backdatediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Edit Bill</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="editbilldiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2" checked="">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Cancel Bill</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="deletebilldiv">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline2" checked="">
                                                    <label class="onoffswitch-label" for="show-offline2">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Edit Purchase Order</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="purchaseordereditdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Cancel Purchase Order</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="purchaseorderdeletediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Edit Catalogue</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="edit_cataloguediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="hidden">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Cancel Catalogue</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="delete_cataloguediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Third Party Checkbox</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="thirdpartydiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                     <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Requisition Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="requisitiondiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Direct Transfer Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="directtransferdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Return Product Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="returnstockdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="hidden">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Check Availability Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="checkavailabilitydiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                     <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">View Inventory Report</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="inventoryreportdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Cancel Indent</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="cancelindentdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                     <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Product Return Authority</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="medretauthdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Indent Approve</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="indentapprovediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">PO Approve</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="poapprovediv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                      <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Cancel po</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="cancelpo1">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                      <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Sale Bill Price Edit</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="editpurchase">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                     <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Back Date Print Pharm</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="pharm_bkdt">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Session Added Product</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="session_added_med">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    
                                    <li class="">
                                        <div class="form-group">
                                           <label class="col-xs-9 control-label">Patient Edit</label>
                                            <div class="col-xs-3 control-label">
                                                <div class="onoffswitch greensea" id="patienteditdiv">
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                                
							      </div>
							      </div>
							      <div class="modal-footer">
							      	<button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
							      </div>
							    </div>
							  </div>
							</div>
							

							<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '520px'
								    });
								});
							</script>
							

</body>
</html>