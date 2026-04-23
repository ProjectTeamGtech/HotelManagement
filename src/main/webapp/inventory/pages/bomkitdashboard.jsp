<!doctype html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<html class="no-js" lang="">
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    
    
    <style>
    
        .padright {
            padding-left: 40px;
        }
        .table.table {
            color: RGBA(85, 85, 85, 0.85);
            background-color: #fff;
        }

        .comtitle {
            font-size: 13px;
            background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
            color: rgb(255, 255, 255);
        }

        .marbot25 {
            margin-bottom: 25px;
        }

        .editcompany {
            float: right;
            font-size: 17px;
            color: #fff;
        }

        .borright {
            border-right: 1px dashed rgb(192, 192, 192);
        }

        .buildinglogo {
            width: 60%;
            margin-top: 30px;
        }
        #sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {
            border-top: 0;
            min-height: auto !important;
        }
        .miheight {
            min-height: auto !important;
        }
        .my-table th {
            background-color: #424A5D;
            color: #fff !important;
            border-bottom: 1px solid #DFD8D4;
            border-right: 1px solid #DFD8D4;
            border-top: 1px solid #DFD8D4;
            padding: 3px 3px 4px 5px;
            text-align: left;
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        input[type="checkbox"] {
		    margin: 0px 0 0 !important;
		    margin-top: 1px \9;
		    line-height: normal;
		    /* padding-top: 15px; */
		    position: absolute !important;
		}
        .sidebar-xs #header .branding > a {
            background-position: 6px 10px;
            width: 100% !important;
            font-size: 21px;
            padding: 0px 1px 2px 15px;
            text-align: center;
            color: #fff;
        }
            .sidebar-xs #header .branding > a > span {
                display: inline-block;
            }
        .sidebar-xs #header .branding {
            width: 100%;
            padding-top: 7px;
            text-align: center;
        }
        .theight {
            height: 21px;
        }
        .table>tbody>tr>td, .table>tfoot>tr>td {
		    padding: 2px 5px 0px 5px !important;
		}
    </style>


    <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
        }
        .addcatego {
            float: right;
            margin-top: -40px;
            margin-right: 30px;
        }
        .sort{
        width: 15%;padding-top: 2px;
        }
                   .setborba{
	background-color: #efefef !important;
    padding-top: 5px !important;
}
 .dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus {
    background-image: linear-gradient(to bottom, #777 0, #777 100%) !important;
    
}
.dropdown-menu {
    padding: 0px 0 !important;
    margin: 0px 0 0 !important;
}
ul.dt-button-collection.dropdown-menu>* {
    -webkit-column-break-inside: avoid;
    break-inside: avoid;
    border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}
     b, strong {
    font-weight: 900;
}   
    </style>
    
    <SCRIPT type="text/javascript">
    
    			function submitForm(){
    			
    					document.getElementById("myform").submit();
    			}
    		
    </SCRIPT>
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
    	document.addEventListener("contextmenu", function(e){
    		e.preventDefault();
    		}, false); 
    	
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
         $("#expiry_date").datepicker({

 			dateFormat : 'dd-mm-yy',
 			yearRange: yearrange,
 			minDate : '30-12-1880',
 			changeMonth : true,
 			changeYear : true

 		});
        
 		                  
    };
  
    function printReport() {
				
				  $("#tablesort").table2excel({
					exclude: ".noExl",
					name: "Stock Status",
					filename: "CatalogueReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});          
           }
    
    
    function printProductExcel() {

        $(".xlsproduct").table2excel({
					exclude: ".noExl",
					name: "Product List",
					filename: "ProductList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
    function printStockReportExcel() {

        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Stock Status Report",
					filename: "stcokstatusList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
     


</SCRIPT>
    
    
    <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
      <script type="text/javascript" src="common/js/pagination.js"></script>
      <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>




<body id="his" class="appWrapper sidebar-xs-forced">
		 						<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
    
        <section id="">

            <div class="">
                <div class="">
					                
                
                	<div class="hidden-print">
									<ul class="breadcrumb">
										&nbsp;
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
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                    
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden">
                    <!-- <div class="form-inline">
                   
                    </div> -->
	                       <div class="form-inline">
	                      	<!-- <div class="form-group" >
	                        <label  style="margin-right:200px"><b>Stock Status Dashboard</b></label>
	                      	</div> -->
	                      	
						  		&nbsp; &nbsp; 
	                      	
	                      	<s:if test="isfromcathlab==1">
	                      			&nbsp; &nbsp;
						  			<div class="form-group " id="issuediv" >
						  				<%-- <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#issuecart"><span >Issue BOM kit </span></a> --%>
						  				<a href="#" class="btn btn-primary" onclick="openbomkitpouup()"><span >ISSUE BOM KIT </span></a>
						  			</div>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
	                      	</s:if>
	                      	<s:else>
	                       		<%if(loginInfo.getUserType()==2) {%>
	                        	<a href="#" class="btn btn-primary" onclick="addToCart()">Add to Transfer</a>
						  		<div class="form-group" id="cartdiv">
						  			<%if(session.getAttribute("tcount")!=null){ %>
						  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
	                        <%}else{ %>
						  	<s:if test="direct_transfer==1">
						  		<a href="#" class="btn btn-primary" onclick="addToCart()">Transfer without indent</a>
						  		<div class="form-group" id="cartdiv">
						  			<%if(session.getAttribute("tcount")!=null){ %>
						  				<%  String p_id =  (String)session.getAttribute("tcount"); %>
         									<a href="#" data-toggle="modal" onclick="showCartPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=p_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#cart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	</s:if>
						  	<%} %>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
						  	<a href="#" class="btn btn-primary" onclick="addToReturn()">Return to Store</a>
						  		<div class="form-group" id="returndiv">
						  			<%if(session.getAttribute("returncount")!=null){ %>
						  				<%  String r_id =  (String)session.getAttribute("returncount"); %>
         									<a href="#" data-toggle="modal" onclick="showReturnPopUp()"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=r_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#returncart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; &nbsp;
						  	<a href="#" class="btn btn-primary" onclick="addToIssue()">Add to Issue</a>
						  		<div class="form-group" id="issuediv">
						  			<%if(session.getAttribute("issuecount")!=null){ %>
						  				<%  String i_id =  (String)session.getAttribute("issuecount"); %>
         									<a href="#" data-toggle="modal" onclick="showIssuePopUp(0)"> <span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : <%=i_id %> </span></a>
						  			<%}else{ %>
						  				<a href="#" data-toggle="modal" data-target="#issuecart"><span style="background-color: brown;padding: 5px 4px 6px 4px;color: #fff;">Total : 0 </span></a>
						  			<%} %>
						  		</div>
						  	&nbsp; &nbsp; &nbsp; &nbsp;
						  	  <a href="#" class="btn btn-primary" onclick="addToReturnQue()">Product Return Que</a>
						  	  </s:else>
	                       </div>
                       </div>
                    
                    
                       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top:5px">
                       <div class="form-inline">
                           <s:form theme="simple" action="bomkitdashboardProduct">
                           		<s:hidden name="isfromcathlab" id="isfromcathlab"></s:hidden>
	                            <div class="form-group">
	                           		<span class="text-uppercase"><b>Cathlab Dashboard</b>  &nbsp; - &nbsp; </span>
	                           </div> 
                           		<div class="form-group">
								  	<s:textfield name="fromdate" readonly="true"  id="fromdate" placeholder="Fromdate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								<div class="form-group">
								  	<s:textfield name="todate"  readonly="true" id="todate" placeholder="Todate" theme="simple" cssClass="form-control"></s:textfield>
								</div>
								
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								  	<a type="button"  title="Save As XLS" onclick="printStockReportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
									<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer')"><i class="fa fa-print"></i></a>
								</div>
								
								<s:if test="isfromcathlab==1">
	                      			&nbsp; &nbsp;
						  			<div class="form-group" style="float: right;" id="issuediv" >
						  				<%-- <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#issuecart"><span >Issue BOM kit </span></a> --%>
						  				<a href="#" class="btn btn-primary" onclick="openbomkitpouup()"><span >ISSUE BOM KIT </span></a>
						  			</div>
							  		&nbsp; &nbsp; &nbsp; &nbsp;
		                      	</s:if>
							
                           	</s:form>
                           		
                           </div>
                        </div>
                       </div>
                        
                    <div class="">
                        <table class="table my-table xlstable table-striped table-bordered" id="tablesort" style="width: 100%;">
                            <thead>
                               <tr>
                               		<th style="width: 5%;">Sr. No</th>
                                    <th style="width: 5%;">ID</th>
                                    <th style="width: 25%;">Procedure name</th>
                                    <th style="width: 25%;">Patient Name</th>
                                    <th style="width: 12%;">Date</th>
                                    <th style="width: 11%;">User Id</th>
                                    <th style="width: 15%;">Remark</th>
                                    <th style="width: 10%;">Status</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int xyz=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++xyz) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="clientname"/></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="userid"/></td>
                                    <td><s:property value="remark"/></td>
                                    <td><s:if test="status==0">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="setissuetotransfer(<s:property value="id"/>)"><span>Consume </span></a>
                                    	</s:if>
                                    	<s:elseif test="status==1">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="showAddchargePopupCath(<s:property value="id"/>,<s:property value="clientid"/>)"><span>Add charge </span></a>
                                    	</s:elseif> 
                                    	<s:else>
                                    		Consumed
                                    	</s:else>
                                    </td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>

                        </table><br/>
                    </div>



 <!-- <div class="hidden" id="page_printer"> -->
 <div style="width: 100%;font-size: 8px;display:none;" id="page_printer">
 						<h5><span class="text-uppercase"><b>Stock Status</b> &nbsp;  &nbsp;</span></h5>
                        <table class="my-table tablestock" id="productlistnew" style="width: 100%;font-size: 8px;">
                           <thead>
                               <tr>
                               		<th style="width: 5%;">Sr. No</th>
                                    <th style="width: 5%;">ID</th>
                                    <th style="width: 30%;">Procedure name</th>
                                    <th style="width: 30%;">Patient Name</th>
                                    <th style="width: 2%;">Date</th>
                                    <th style="width: 11%;">User Id</th>
                                    <th style="width: 15%;">Remark</th>
                                    <th style="width: 10%;">Status</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <%int xyz1=0; %>
                               <s:iterator value="productList" >
                               
                                <tr style='color:<s:property value="color"/>'>
                                	<td><%=(++xyz1) %></td>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="tempname"/></td>
                                    <td><s:property value="clientname"/></td>
                                    <td><s:property value="date"/></td>
                                    <td><s:property value="userid"/></td>
                                    <td><s:property value="remark"/></td>
                                    <td>
                                    	<s:if test="status==0">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="setissuetotransfer(<s:property value="id"/>)"><span>Consume </span></a>
                                    	</s:if>
                                    	<s:elseif test="status==1">
                                    		<a href="#" class="btn btn-primary" data-toggle="modal" onclick="showAddchargePopupCath(<s:property value="id"/>)"><span>Add charge </span></a>
                                    	</s:elseif> 
                                    	<s:else>
                                    		Consumed
                                    	</s:else>
                                    </td>
                                </tr>
                                
                                </s:iterator>
                            </tbody>


                        </table>
                    </div>
	  <s:form action="bomkitdashboardProduct" name="paginationForm" id="paginationForm" theme="simple">
							    
									<div class="col-lg-12">
									<s:hidden name="fromdate"></s:hidden>
									<s:hidden name="todate"></s:hidden>
									<s:hidden name="isfromcathlab"></s:hidden>
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" />
											Records
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>                   
                    

                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->


<div class="modal fade" id="addchargepopupcath" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges</span></h4>
				</div>
				<div class="modal-body">
				
					<%-- <%@ include file="/ipd/pages/addcharges.jsp"%> --%>
					<jsp:include  page="/ipd/pages/invaddcharge.jsp" />
				</div>
				
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				<%-- <%if(loginfo.isCash_desk() || loginfo.getUserType()==2){ %>
				<button type="button" class="btn btn-primary" 
							onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
				<%} %>
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button> --%>
				
				
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


<!-- Cart Modal -->
<div id="issuecart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="transferissueproductdataCatalogue" theme="simple" id="issueproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div id="consumeheadingdiv">
        <s:if test="isfromcathlab==1">
        	<h4 class="modal-title">Issue Bom Kit</h4>
      	</s:if>
      	<s:else>
      		<h4 class="modal-title">Item Issue To Patient/User</h4>
      	</s:else>
      	</div>
      </div>
      <div class="modal-body">
         <div class="form-group">
         <s:hidden name="isfromcathlab"></s:hidden>
         <s:hidden name="isfrombimkit" id="isfrombimkit"></s:hidden>
         	<s:if test="isfromcathlab==1">
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
         			
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="bomkittemplate" style="display: ">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select" list="cathtemplist" onchange="setcathTempValue(this.value)"  id="cathtempid" name="cathtempid" />
          		</div>
          		<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5" id="bomnewproddiv" style="display: ">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="genericname" headerValue="Select product" list="newproductlist"  id="cathproductid" name="cathproductid" />
          		</div>
          		<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1" id="bomnewprodadddiv">
          			<a href="#" onclick="addnewcathlabrow('tableissuecount')" class="btn btn-primary">Add</a>
          		</div>
         	</s:if>
         	<s:else>
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Department" list="issuedeptlist"  id="hisdepartmentfilter" name="hisdepartmentfilter" />
          		</div>
         		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          			<s:select cssClass="form-control chosen-select" list="#{'0':'Patient', '1':'YUVICARE User'}" onchange="setpatientuserlist(this.value)" id="hisuserfilter" name="hisuserfilter" />
          		</div>
          		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issuechangediv">
          			<!-- <label>Patient:</label> -->
          			<input type='hidden' class='form-control' name="issuepatientid" value="0" id='issuepatientid'>
         			<input type='text' readonly="readonly" placeholder="Select Patient/User" class='form-control' id='issuepatientname' onclick='getpatientpopForIssue()'>
          		</div>
           		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" id="issueprocediv">
           			<select id="issueproceid" name="issueproceid" class="from-control chosen-select">
           				<option value="0">Select Procedure</option>
           			</select>
          		</div>
         	</s:else>
         
        </div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="" style="overflow: scroll; width: 100%; height: 350px;">
         <table class="table my-table xlstable table-striped table-bordered" id="tableissuecount" style="width:100%;">
          <thead>
           <!-- <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
            <th style="width: 9%;">Current Stock</th>
             <th style="width: 13%;">Patient Name</th>
            <th style="width: 10%;">Transfer Qty</th>
            <th style=""></th>
           </tr> -->
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">Category</th>
            <th style="width: 9%;">Sub Category</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 9%;">Product Code</th>
            <th style="width: 8%;">Batch No</th>
            <th style="width: 8%;">MFG</th>
            <th style="width: 9%;">Exp Date</th>
           <!--  <th style="width: 9%;">Location</th> -->
            <th style="width: 4%;">Stock</th>
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
           
          </thead>
          <tbody id="issuebody">
           
          </tbody>
          </table>
          <input type="hidden" name="issuecount" id="issuecount" value="0">
        </div>
        
        <div class="form-group">
			<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="issuecomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="issuedivbtn">
      	 <s:if test="isfromcathlab==1">
      	 	<button type="button" class="btn btn-primary" onclick="return confirmIssueCathLab()">Submit</button>
         </s:if>
         <s:else>
           <button type="button" class="btn btn-primary" onclick="return confirmIssue()">Transfer</button>
         </s:else>
       
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<!-- Cart Modal -->
<div id="cathtempcart" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
   <s:form action="savecathlabtemplateCatalogue" theme="simple" id="cathtempproductform"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Create Cathlab Template</h4>
      </div>
      <div class="modal-body">
         <div class="form-group">
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<s:select cssClass="form-control chosen-select" headerKey="0" listKey="id" listValue="name" headerValue="Select Procedure" list="procedurelist"  id="cathtempprocedure" name="cathtempprocedure" />
          	</div>
         	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
          		<input type='text'  placeholder="Template name" class='form-control' id='cathtemplatename' name="cathtemplatename">
          	</div>
          	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
          		
          	</div>
           	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
           		
          	</div>
           
          	
      	</div>
      	<div class="clearfix" style="height: 15px;"></div>
        <div class="">
         <table class="table my-table xlstable table-striped table-bordered" id="tablecathcount" style="width:100%;">
          <thead>
           <tr>
            <th style="width: 4%;">Sr.no</th>
            <th style="width: 9%;">HSN Code</th>
            <th style="width: 26%;">Product Name</th>
            <th style="width: 10%;">Batch No</th>
            <th style="width: 10%;">Exp Date</th>
            <th style="width: 10%;">Location</th>
           <!--  <th style="width: 9%;">Current Stock</th> -->
           <!--  <th style="width: 13%;">Patient Name</th> -->
            <th style="width: 10%;">Qty</th>
            <th style=""></th>
           </tr>
          </thead>
          <tbody id="cathempbody">
           
          </tbody>
          </table>
          <input type="hidden" name="cathtempcount" id="cathtempcount">
        </div>
        
        <div class="form-group">
		<label for="comment">Remark:</label>
			<textarea class="form-control" rows="3" id="cathtempcomment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
		</div>
      </div>
      
      <div class="modal-footer" id="cathtempdivbtn">
       <button type="button" class="btn btn-primary" onclick="confirmCathTemp()">Create Template</button>
      </div>
      
      </s:form>
    </div>

  </div>
</div>

<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%-- <%@ include file="/ipd/pages/ipdPatientList.jsp"%> --%>
        <%@ include file="/inventory/pages/patientlist.jsp"%>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body addnewpat">
					<%-- <jsp:include page="/diarymanagement/pages/addPatientPage.jsp"/> --%>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
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
  </script>
<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>
</body>
</html>
