<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<html class="no-js" lang="">
<!--<![endif]-->

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
           
            font-weight: bold;
            font-size: 11px;
            background-size: 100% 100%;
        }
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 1px 7px 1px 7px !important;
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
        .baksetp{
        	    background-color: #616f77;
			    color: #fff;
			    padding: 1px 5px 1px 5px;
			    text-align: center;
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
    
     <style>
        .topheadbaxck {
            background-color: rgb(239, 239, 239);
            padding: 8px 0px;
        }
        .red{
            color:red;
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

<script type="text/javascript" src="common/js/pagination.js"></script>  
<script type="text/javascript">
window.onload= function(){
	document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
}

</script>
</head>


<body id="his" class="appWrapper sidebar-xs-forced">

        <section id="">

            <div class="">
                <div class="">
                
                	<div class="hidden-print">
						<ul class="breadcrumb">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
							<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
								<%if(breadcrumbs.isIscurrent()){ %>
									<li><%=breadcrumbs.getShowingname()%></li>
								<%}else{ %>
									<%if(breadcrumbs.getOn()){ %>
										<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
									<%}else{ %>
										<li><%=breadcrumbs.getShowingname()%></li>
									<%} %>
								<%} %>
								
							<%} %>
						</ul>
					</div>
                    
                    <div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
                    <div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
                        <div class="col-md-12" style="padding:0px;">
                        	<div class="col-md-6">
                           <div class="form-inline">
                           <s:form action="PoPayment" theme="simple">
	                            <div class="form-group">
	                           		<span class="text-uppercase"><b>SUPPLIER ACCOUNTS</b> &nbsp; - &nbsp;</span>
	                           </div>
                           		<div class="form-group">
								  	<s:textfield id="searchText" name="searchtext"  cssClass="form-control" placeholder="Search by supplier name" />
								</div>
								<div class="form-group hidden">
									<s:select name="filter_location"   list="locationListPharmacy" listKey="id" listValue="name"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Locations"  ></s:select>
								</div>
								<div class="form-group">
								  	<button type="submit" class="btn btn-primary">Go</button>
								</div>
								
                           	</s:form>
                           		
                           </div>
                        </div>
                       
                        <div class="col-md-6 text-right">
                        
                        </div>
                        </div>
                    </div> 
                    <div class="">
                    
                    <table class="table my-table table-striped xlstable table-bordered table-hover" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th style="width: 2%;">No</th>
                                    <th style="width: 6%;">Last Date</th>
                                    <th style="width: 20%;">Supplier Name</th>
                                    <th style="text-align:right;">Credit Note</th>
                                    <th style="text-align:right;">Debit Note</th>
                                    <th style="text-align:right;">Order Amount</th>
                                    <th style="text-align:right;">Paid Amt.</th>
                                 	<th style="text-align:right;">Return Amt. Settled</th>
                                    <th style="text-align:right;">Total Paid Amt.</th>
                                    <th style="text-align:right;">Balance Amt.</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            
                            	<s:iterator value="paymentList">
                                <tr>
                                    <td><s:property value="procurementid"/></td>
                                    <td><s:property value="date"/> </td>
                                    <td><s:property value="vendor"/></td>
                                    <td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="credit"/></td>
                                    <td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="debit"/></td>
                                    <td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="total"/></td>
                                    <td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="paidpayment"/>  </td>
                                 	<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="returnpayment"/></td>
                                    <td class="text-right" style="color:green;">Rs. <s:property value="paymentAmount"/> </td>
                                    <td class="text-right" style="color: #d9534f;"><%=Constants.getCurrency(loginfo)%><s:property value="balance"/></td>
                                    <td class="">
                                            <a href="#" class="baksetp" style="color:#fff;" onclick="openSamePopup('paymentProcurement?selectedid=<s:property value="procurementid"/>&vendorid=<s:property value="vendor_id"/>')">Payment and History</a>
                                    </td>
                                </tr>
                                
                                </s:iterator>
                                
                                              <tr>
                                                       <td></td>
                                    <td> </td>
                                    <td></td>
                                    <td ></td>
                                    <td ></td>
                                                  <%if(loginfo.isParihar()){ %>
                                					   <td class ="hidden" colspan="4"><s:property value="Totalorderamt"/></td>
                                
                                					   <td class ="hidden" colspan="4"><s:property value="Totalpaymentamt"/></td>
                                
                                					   <td class ="hidden" colspan="4"><s:property value="Totalreturnsettledamt"/></td>
                                
                                					   <td class ="hidden" colspan="4"><s:property value="Totalpaidamt"/></td>
                                
                                					   <td style="margin-left: 30%;"><b>Total Balance Amount : <s:property value="Totalbalanceamt"/></b></td>
                                                    <%} %>
                                             </tr>
                            </tbody>

                        </table>
                        <br>
                        
                    </div>
                        
                    <s:form action="PoPayment" name="paginationForm" id="paginationForm" theme="simple">
							    <s:hidden name="searchtext"></s:hidden>
									<s:hidden name="location"></s:hidden>
								 <div class="col-lg-12">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /> Records
										</label>
									</div>
									<%@ include file="/common/pages/pagination.jsp"%>
								</div>
							</s:form>  
                        
                       <%--  <s:form action="PoPayment" name="paginationForm" id="paginationForm" theme="simple">
							     
							     	<div class="">
									<div class="col-lg-4 col-md-4 text-right">
										Showing all <label class="text-info">(<s:property
												value="pagerecords" /> of <s:property value="totalRecords" />
											Records)
										</label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form>  --%>
                        
                          
                    </div>


                </div>

               

            </div>

        </section>
        <!--/ CONTENT -->




<script>
	function myFunction(btn) {

	  var x = document.getElementById('myDIV');
	if(btn.value=="check" || btn.value==1)
	{
	 x.style.display = 'none';
	}
	else
	{
	 x.style.display = 'block';
	}
	
  
   
}
</script>



</body>
</html>
