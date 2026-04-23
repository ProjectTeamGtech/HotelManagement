<!DOCTYPE html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<link rel="stylesheet" href="common/Font-Awesome-master/css/font-awesome.css">
<script type="text/javascript" src="ipd/js/addcharge.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
 <script type="text/javascript" src="mrd/js/mrd.js"></script>

<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<SCRIPT type="text/javascript">

  $(document).ready(function(){
  
        $("#vdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});    
        $("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		/*  document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false);  */
  });

</SCRIPT>

<style>
.resub{
float: right;
    margin-right: 40px;
    margin-top: 20px;
}
.caktitle{
    background-color: #eeeeee;
}
.infoicon{
             	font-size: 19px !important;
    			line-height: 33px !important;
         }
         
         .planned {
    background-color: #95a2a9;
    color: #fff !important;
    padding: 4px 22px 4px 22px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
}
.visited {
    background-color: #7fcc80;
    color: #fff !important;
    padding: 4px 27px 4px 27px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
}
.notvisited {
    background-color: #555;
    color: #fff !important;
    padding: 4px 13px 4px 13px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
}

.canceled {
    background-color: #ff6767;
    color: #fff !important;
    padding: 4px 22px 4px 22px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
    width: 100%;
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

</head>

<body>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Consultants_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Visiting Consultant Dashboard</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/doctor.png" class="img-responsive prescripiconcircle">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>Visiting Consultant Dashboard</h4>
										</div> -->
									</div>
								</div>
								<div class="hidden-print">
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
					<div class="col-lg-12 col-md-12" style="padding-right:0px;padding-left:0px;">
						<div>
							<div class="">
								<div class="">
									<div class=" ">
								       <div class="col-lg-12 topback2">
								       <form action="viewvisitingconsultIpdAjax" method="post" class="form-inline">
								       		<div class="form-inline">
								       		
								       				<div class="form-group" style="width: 15%;">
								       					<!-- <input class="form-control" id="searchtext" name="searchtext" type="text" placeholder="Search by patient" style="width: 100%;"/> -->
								       				<s:textfield cssClass="form-control" id="searchtext" name="searchtext" placeholder="Search by patient" style="width: 100%;"></s:textfield>
								       				</div>
								       				<div class="form-group" style="width: 8%;">
								       					<!-- <input class="form-control" type="text" id="fromdate" name="fromdate" placeholder="From date" style="width: 100%;"/>
								       					 -->
								       					 <s:textfield cssClass="form-control" id="fromdate" name="fromdate" placeholder="From date" style="width: 100%;"></s:textfield>
								       				</div>
								       				<div class="form-group" style="width: 8%;">
								       					<!-- <input class="form-control" type="text" id="todate" name="todate" placeholder="To Date" style="width: 100%;"/> -->
								       				<s:textfield cssClass="form-control" id="todate" name="todate" placeholder="To date" style="width: 100%;"></s:textfield>
								       				</div>
								       				 <div class="form-group" >
					    								<s:select list="visitingConsultDoctors" listKey="id" onchange="setVistingConsultantFees(this.value)" id="searchdrname" name="searchdrname" listValue="fullname" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
					    							</div>
								       				      <div class="form-group">
								       					        <!-- <a herf="#" class="btn btn-primary" type="submit">Go</a> -->
								       					        <button class="btn btn-primary" type="submit">Go</button>
								       				      </div>
								       				     
								       				<div class="form-group">
								       					<a href="#" class="btn btn-warning" onclick="addvisitingconsult()">New Call</a>
								       				</div>
								       				<div class="form-inline" style="float: right;;text-transform: uppercase;">
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ffa3a3"></i> Planned
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#7fcc80"></i> Visited
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#555"></i> Not Visited
											    </label>
											  </div>
											
								       				<div class="form-group" style="float: right;margin-left: 15px;">
														<a href="#" onclick="openaddvistingchargepopup()" title="Add New Consultant">Add New Consultant</a>
														<!-- <i class="fa fa-cog fa-2x" aria-hidden="true"></i> -->
													</div>
													</div>
								       			</div>
								       			 </form>
								       			
										</div>
										<div class="">
								<table class="table table-bordered" cellspacing="0" width="100%">
								<thead>
								<tr>
								   <th class="manastableheader">ID</th>
								   <th class="manastableheader">Patient Details</th>
								   <th class="manastableheader">Doctor Name</th>
								   <th class="manastableheader">Date | Time</th>
								   <th class="text-right manastableheader">Fees</th>
								   <th class="text-center manastableheader">Status</th>
								   <th class="manastableheader">Payment</th>
								   <th class="manastableheader">Edit</th>
								   <th class="manastableheader">Delete</th>
								   
								</tr>
								</thead>
								<tbody id="tbvisited">
								  <s:iterator value="visitingConsultList">
								<tr>
								   <td><s:property value="id"/></td>
								   <td><s:property value="clientname"/> | <s:property value="wardname"/> | <s:property value="abrivationid"/></td>
								   <td><s:property value="practitionername"/></td>
								   <td><s:property value="date"/> | <s:property value="time"/></td>
								   <td class="text-right" style="color: green;font-weight: bold;">Rs.<s:property value="fees"/></td>
								   
								   <s:if test="cancel==0">
								   		<s:if test="status==1">
									     	<%-- <td id="btnisvisited<s:property value="id"/>" class="text-center"> <a class="visited" onclick="updateVisitedorNot(<s:property value="id"/>,0)" href="#">Visited</a></td> --%>
									   		<td id="btnisvisited<s:property value="id"/>" class="text-center"><span class="visited">Visited</span></td>
									   </s:if>
									   <s:else>
									      	<%-- <td id="btnisvisited<s:property value="id"/>" class="text-center"> <a class="planned" onclick="updateVisitedorNot(<s:property value="id"/>,1)" href="#">Planned</a></td> --%>
									   		<td id="btnisvisited<s:property value="id"/>" class="text-center"> <a class="planned" onclick="updateVisitedorNot(<s:property value="id"/>,1,<s:property value="ipdid"/>,<s:property value="clientid"/>)" href="#">Planned</a></td>
									   </s:else>
								   </s:if>
								   <s:else>
								   		<td class="text-center"> <a class="canceled" href="#">Canceled</a></td>
								   </s:else>
								   
								   <s:if test="cancel==0">
								   		<s:if test="payment==1">
									     <%-- <td style="font-weight: bold;" id="btnpvisit<s:property value="id"/>"> <a onclick="addchargornot(<s:property value="id"/>,<s:property value="ipdid"/>,<s:property value="clientid"/>,0)" href="#" style="color: green;">Paid</a></td> --%>
									     	<td style="font-weight: bold;" id="btnpvisit<s:property value="id"/>"> <span style="color: green;">Paid</span>(Rs.<span><s:property value="paid_amount"/></span>)</td>
									   </s:if>
									   <s:else>
									      <%-- <td style="font-weight: bold;" id="btnpvisit<s:property value="id"/>"> <a onclick="addchargornot(<s:property value="id"/>,<s:property value="ipdid"/>,<s:property value="clientid"/>,1)" href="#" style="color: red;">Not Paid</a></td> --%>
									      <td style="font-weight: bold;" id="btnpvisit<s:property value="id"/>"> <a onclick="payconsultantcharge(<s:property value="id"/>,<s:property value="ipdid"/>,<s:property value="clientid"/>,1)" href="#" style="color: red;">Not Paid</a></td>
									   </s:else>
								   </s:if>
								   <s:else>
								   		<td>-</td>
								   </s:else>
								   
								  
								   <td>
								   			 <s:if test="cancel==0">
								   			 	  <s:if test="status==1"> 
										             Cant Edit
										          </s:if>
										          <s:else>
										             <a href="#" onclick="editIpdVisit(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>
										          </s:else>
								   			 </s:if>
								   			 <s:else>
								   			 		-
								   			 </s:else>
								          
								  </td>
								   <td>
								   		 	<s:if test="cancel==0">
										   		<s:if test="status==1">
										           Can't Cancel
										        </s:if>
										        <s:else>
										           <a href="#" onclick="deleteIpdVisit(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a>
										        </s:else>
								   			 </s:if>
								   			 <s:else>
								   			 		-
								   			 </s:else>
								      
								       
								   </td>
								 </tr>  
								 </s:iterator>
								</tbody>
								</table>
								 <s:form action="viewvisitingconsultIpdAjax" name="paginationForm" id="paginationForm" theme="simple">
										<s:hidden name="searchtext"></s:hidden>
										<s:hidden name="fromdate"></s:hidden>
										<s:hidden name="todate"></s:hidden>
										<s:hidden name="searchdrname"></s:hidden>
										<div class="col-lg-12 col-md-12" style="padding:10px;margin-top:10px;">
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
					</div>
</div>
</div>


	<!-- /MAIN CONTENT -->
	<!--main content end-->

	
	 <!--Add Visiting Consult Modal-->
    <!-- Modal -->
    <div class="modal fade" id="addvisitingconsult" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add Visiting Consultant</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <form action="addvisitingIpdAjax" id="visitform" method="post">
                    	 <s:hidden name="clientId" id="vclientid"></s:hidden> 
						 <s:hidden name="ipdid" id="vipdid"></s:hidden>
						 <s:hidden name="id" id="visitid"></s:hidden>   
					  <div class="form-group">
					    <label for="exampleInputEmail1">Select Doctor <span class="red">*</span></label>
					    <div>
					           <%-- 
					           <input type="checkbox" onclick="allCheck(this)" /> Select All <br>
					           <s:iterator value="visitingConsultDoctors">
					           
					                <input type="checkbox" class="case" value="<s:property value="id"/>" /> <s:property value="fullname"/> <br>
					           
					           </s:iterator> --%>
					           <s:select list="visitingConsultDoctors" listKey="id" onchange="setVistingConsultantFees(this.value)" id="vdrid" listValue="fullname" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select" name="visitconslt"></s:select>
					    </div>
					     <input type="hidden" id="doctors" name="doctors" />
					     <!--<s:select list="visitingConsultDoctors" listKey="id" listValue="fullname" cssClass="form-control" name="practitionerid" id="visitingConsultDoctors" />
					  --></div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">Select Patient<span class="red">*</span></label>
					    <s:select list="activeIpdPatientList" listKey="clientid" listValue="clientname" cssClass="form-control chosen-select" name="clientid" id="clientname" />
					  </div>
					  <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding:0px;">
					  	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Date</label>
					    <s:textfield cssClass="form-control" readonly="true" name="date" id="vdate"></s:textfield>
					  </div>
					  	</div>
					  	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-right: 0px;">
					  		 <div class="form-group">
					    <label for="exampleInputPassword1">Time<span class="red">*</span></label>
					    <s:select list="visitingtimeList" cssClass="form-control" name="time" id="time"/>
					  </div>
					  	</div>
					  </div>
					  
					 
					   <div class="form-group">
					    <label for="exampleInputPassword1">Fees<span class="red">*</span></label>
					    <s:textfield cssClass="form-control" id="fees" name="fees"></s:textfield>
					  </div>
					  
					   <div class="form-group">
					    <label for="exampleInputPassword1">TDS<span class="red">*</span></label>
					   <%--  <s:textfield cssClass="form-control" id="tds" name="tds"></s:textfield> --%>
					   <input type="number" class="form-control" id="tds" name="tds" value="10">
					  </div>
					  
					  
					  
					</form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitvisitingconsult()" class="btn btn-primary">Add / Edit</button>
                </div>
            </div>
        </div>
    </div>
	
	
	 <div class="modal fade" id="addnewvisitingconsultdr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Visiting Consultant</h4>
                </div>
                <div class="modal-body">
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					  			<label for="exampleInputPassword1">System Doctor<span class="red">*</span></label>
			   					<s:select id="existingdr" name="existingdr" list="practionerlist" listKey="id" listValue="fullname"
								headerKey="0" headerValue="Select Existing Doctor"
								title="Select Existing Dr" cssClass="form-control showToolTip chosen-select" onchange="checkdralreadypresent(this.value)" data-toggle="tooltip" />  
					  			<s:hidden id="existingdr_update" value="0"></s:hidden>
					  		</div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
						   		<label for="exampleInputPassword1">Name<span class="red">*</span></label>
				   				<s:textfield cssClass="form-control" id="consultantname" name="consultantname" onkeyup="searchexistingDr(this.value)" data-toggle="tooltip" placeholder="Consultant Name" title="Consultant Name"></s:textfield>
			   				</div>
			   				 <s:hidden name="referid" id="referid"></s:hidden>
			   				 <div class="scrolldoc">
			   				 	<table class="table">
		                            <thead> 
		                            </thead> 
		                            <tbody id="tabledrname"> 
					   				</tbody>
							   </table>
			   				 </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   	<label for="exampleInputPassword1">Specialization<span class="red">*</span></label>
			   			<s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
								headerKey="0" headerValue="Select Specialization"
								title="Select Specialization" cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" />  
						</div>
					  	</div>
                	</div>
                	</div>
                	<div class="row">
                		<div class="col-lg-12 col-md-12 col-xs-12">
                		<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Mobile No<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mobileno" name="mobile" data-toggle="tooltip" placeholder="Mobile number" title="Mobile number" maxlength="10"></s:textfield>
					   </div>
					   <div class="form-group">
					   		<label for="exampleInputPassword1">Qualification<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="mlcqualification" name="mlcqualification" data-toggle="tooltip" placeholder="Qualification" title="Qualification"></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
					  		<div class="form-group">
					   		<label for="exampleInputPassword1">Email<span class="red">*</span></label>
					    	<s:textfield cssClass="form-control" id="emailid" name="emailid" data-toggle="tooltip" placeholder="Email" title="Email"></s:textfield>
					   	</div>
					   	<div class="form-group">
					   		<label for="exampleInputPassword1">Share %<span class="red">*</span></label>
					    	<s:textfield type="number" cssClass="form-control" id="sharepercentage" name="sharepercentage" data-toggle="tooltip" placeholder="Share Charges" title="Share Charges" value="70"></s:textfield>
					   </div>
					  	</div>
					  	<div class="col-lg-4 col-xs-12 col-md-4 col-sm-4">
        					<div class="form-group" id="reftypediv">
        						<label for="exampleInputPassword1">Reference Share Type </label>
     							<s:select name="refsharetype" id="refsharetype" 
    									list="#{'0':'%','1':'Rs'}" 
    									cssClass="form-control chosen-select"></s:select>
        					</div>
         <div class="form-group">
          <label for="exampleInputPassword1">Reference Share Amount</label>
          <s:textfield type="number" cssClass="form-control" id="refshareammount" name="refshareammount" data-toggle="tooltip" placeholder="Share Amount" title="Share Amount" value=""></s:textfield>
        </div>
        </div>
					  	<div class="col-lg-2 col-xs-12 col-md-2 col-sm-2">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Fees<span class="red">*</span></label>
					    <s:textfield cssClass="form-control" id="visitingconfees" name="fees" data-toggle="tooltip" placeholder="Fees" title="Fees"></s:textfield>
					  </div> 
					  	</div>
					  	<div class="col-lg-3 col-xs-12 col-md-3 col-sm-3">
					  		<div class="form-group">
					    		<article>
									<ul class="vertical default_list" style="list-style: none;padding: 0px;">
									   <li id="isvisitingconsdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isvisitingcons" name="isvisitingcons"><i></i> Visiting Consultant</label></li>
									   <li id="issurgeondiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="issurgeon" name="issurgeon"><i></i> Surgeon</label></li>
									   <li id="isanesthesiologistdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isanesthesiologist" name="isanesthesiologist"><i></i> Anesthesiologist</label></li>
									   <li id="isreferreddiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="isreferred" name="isreferred"><i></i> Referred</label></li>
									   <li id="ismlcdiv"><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="ismlc" name="ismlc"><i></i> MLC</label></li>
									 </ul>
								</article>
					  		</div> 
					  	</div>
                	</div>
                	</div>
                	
                	
                	
                	
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <!-- <form action="addnewvisitingIpdDashboard" id="newvisitdrform" method="post"> -->
					   <%-- <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="padding:0px;">
					   <div class="col-lg-3 col-md-3 col-xs-3" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">Initial</label>
					    <s:select id="initial" name="initial" list="initialList" headerKey="0"  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
					  </div>
					  	</div>
					  	<div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
					  		<div class="form-group">
					    <label for="exampleInputPassword1">First Name</label>
					   <s:textfield name="firstname" id="firstname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="First Name" title="Enter First name"></s:textfield>
					  </div>
					  	</div>
					  	<div class="col-lg-5 col-md-5 col-xs-5" style="padding: 0px;">
					  		 <div class="form-group">
					    <label for="exampleInputPassword1">Last Name<span class="red">*</span></label>
					    <s:textfield name="lastname" id="lastname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Last Name" title="Enter Last Name"></s:textfield>
					  </div>
					  	</div>
					  </div> --%>
					<!-- </form> -->
                    </div>
                </div>
                <div class="modal-footer" id="updatebtnid">
                    <button type="button" onclick="addnewconsultantvalidate()" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
	
	<!-- Modal -->
    <div class="modal fade" id="payvisitingconsultfees" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Pay Visiting Consultant</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    <s:hidden id="ipdvisiconsultantid"></s:hidden>
                     <div class="form-group">
					    <label for="exampleInputPassword1">Fees:</label>
					    <label>Rs.<span class="red" id="payfees"></span></label>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">Percentage:</label>
					    <label><span class="red" id="payperc"></span>%</label>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">Total Without TDS:</label>
					    <span id="paybeforetotal"></span>
					  </div>
					  
					  <div class="form-group">
					    <label for="exampleInputPassword1">TDS:</label>
					   	 <label><span id="paytds"></span>%</label>
					  </div>
					  
					  <div class="form-group">
					    <label for="exampleInputPassword1">Pay Amount:</label>
					   	<label>Rs. <span id="payaftertotal"></span></label>
					  </div>
					 
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="payconsultantfees()" class="btn btn-primary">Pay</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
	
	<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
			<script>
				$(function() {
					 $('.scrolldoc').slimScroll({
						height : '200px',
						railVisible: true,
						alwaysVisible: true
					});
				});
 				 
             </script>
	<script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	   });
	});
   </script>
   
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
</body>
</html>
