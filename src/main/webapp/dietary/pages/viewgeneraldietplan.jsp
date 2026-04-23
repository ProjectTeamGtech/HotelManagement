<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript">
    window.onload = function(){
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
    };
    
</SCRIPT>

<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.table-bordered>thead>tr>td {
    background-color: #fff; }
</style>
<style>
            .page {
                padding: 0px;
            }
            .tile .tile-header {
                padding: 0px 6px;
            }
            .portlets.sortable {
                padding-right: 2px;
                padding-left: 2px;
            }
            .tile .tile-body {
                padding: 0px 0px 6px 6px;
            }
            p {
                margin: 0 0 0px;
                font-size: 12px;
            }
            .tile .tile-header h1{
                font-size: 13px;
            }
            a {
                text-decoration: none !important;
            }
            .paddingnilleft {
                padding-left: 0px;
            }
            .minheight72 {
                min-height: 100px;
            }
            .tile {
                margin-bottom: 4px;
                border-radius: 10px;
            }
           
            .table > thead > tr > th {
                padding: 0px;
            }
            .table > thead > tr th:first-child{
                padding-left: 5px;
            }
            .table > tbody > tr td:first-child{
                padding-left: 6px;
            }
            .table > tbody > tr > td{
                padding: 0px;
            }
           
           
            .bghead {
                background-color: rgb(242, 242, 242);
            }
            .tile[class*="bg-"]:not(.bg-default) .dvd, .tile.dvd[class*="bg-"]:not(.bg-default), .tile-header[class*="bg-"]:not(.bg-default) .dvd, .tile-header.dvd[class*="bg-"]:not(.bg-default), .tile-widget[class*="bg-"]:not(.bg-default) .dvd, .tile-widget.dvd[class*="bg-"]:not(.bg-default), .tile-body[class*="bg-"]:not(.bg-default) .dvd, .tile-body.dvd[class*="bg-"]:not(.bg-default), .tile-footer[class*="bg-"]:not(.bg-default) .dvd, .tile-footer.dvd[class*="bg-"]:not(.bg-default) {
                border-color: none;
            }
            .tile .dvd.dvd-btm {
                border-bottom-width: 0px;
            }
            .bellcolr{
                color:rgb(242, 242, 242) !important;
            }
            .bellcolr:hover {
                color: #808080;
            }
            .tile .tile-header .controls .settings > i:last-of-type {
                display: block;
            }
            .padtop7 {
                padding-top: 4px;
    			margin-right: 5px;
            }
            .table > tbody > tr td {
                border-color: none;
            }
            .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
                border-top: none;
            }
            
            .mainbox {
                width: 215px;
            }
            .medumclass {
                padding: 6px 0px 0px 18px !important;
            }
            .tab-content .tab-pane {
                padding: 6px 16px 0px 16px;
            }
            .smallbell {
                font-size: 12px;
                float: right;
            }
            strong {
                font-weight: 700;
                color: yellow;
            }
           
            .martop4 {
                margin-top: 4px;
            }
            .minheightsmall{
                min-height: 66px;
            }
            .padsamll {
			    padding-top: 6px;
			    margin-right: -6px;
			}
			.font11 {
                font-size: 10px !important;
            }
            .nitific{
                margin-left: -8px;
            }
            .nitific15{
				margin-left:-15px;
				}
            .tabcolor{
            color: #555;
            }
            .extended{
            width: 370px;
            }
            .nitificsamll{
            float: right;
		    font-size: 10px;
		    margin-top: 6px;
            }
            .prstotip{
            font-size: 12px;
            }
            .webui-popover {
                width: 40% !important;
            }
            .marto36{
                margin-top: -34px !important;
            }
            input[type="checkbox"] {
    			margin-right: 5px;
			}
            
		.red {
		 color: red;
		}
		
		.modal-header {
		 border-bottom: 1px solid #e5e5e5;
		 background-color: #e5e5e5;
		 padding: 0px 10px 0px 10px;
		}
		
		
		
		.noborder {
		 border-top: none !important;
		}
		
		.border {
		 border-top: 1px solid rgba(192, 192, 192, 0.25) !important;
		}
		.width100{
			width:100%;
		}
		.table > tbody + tbody {
			    border-top: 0px solid #ddd !important;
			}
			.dosatable{
			    background-color: #D3D3D6 !important;
    			color: #555 !important;
			}
			.activeback{
				background-color: rgb(129, 171, 109);
    			min-height: 135px;
    			border-left: 1px solid #f0f0f0;
			}
			.disableback{
				background-color: #E6E6E6;
				min-height: 228px;
    			border-left: 1px solid #f0f0f0;
    			padding-left: 0px;
    			padding-right: 0px;
			}
			.dischptext{
				font-size: 11px !important;
			}
			.dischariconab{
				    position: absolute;
			}
			.textbomana{
			    font-size: 13px !important;
    			font-weight: bold !important;
    			margin-top: 0px !important;
    			min-height: 35px !important;
    			padding: 15px 0px 0px 0px !important;
			}
			.imagestfix{
				width: 56% !important;
			    margin-left: auto !important;
			    margin-right: auto !important;
			    display: block !important;
			    padding-top: 10px !important;
			}
			.completback{
				background-color: rgba(0, 128, 0, 0.43) !important;
			}
			.initaiback{
				background-color: rgba(255, 165, 0, 0.62) !important;
			}
			.btnwidtfixed{
				width: 75% !important;
			}
			.fontflag{
				font-size:18px;
			}
			
			.maxswidth{
				    width: 65%;
			}
			.minswidth{
				    width: 33%;
			}
			.backcolsets{
				    background-color: #f5f5f5;
    				padding: 10px;
    				margin-bottom: 15px;
			}
			hr {
    margin-top: 0px;
    margin-bottom: 10px;
    border: 0;
    border-top: 1px solid #eee;
}
.modal-header {
    min-height: 16.43px;
    padding: 6px 8px 4px 11px !important;
    border-bottom: 1px solid #E5E5E5;
    background: #6699CC none repeat scroll 0% 0% !important;
}
.modal-title {
    margin: 0;
    line-height: 1.42857143;
    font-size: 15px;
}
.botbtn{
	float: right;
    margin-top: -27px;
    margin-right: 10px;
    margin-bottom: 10px;
}
	.tooltip {
    position: absolute;
    z-index: 1070;
    display: block;
    visibility: visible;
    font-size: 12px !important;
    line-height: 1.4;
    opacity: 1;
    filter: alpha(opacity = 0);
    text-decoration: none !important;
}	
.follow{
	    width: 50px;
}	
 .setmiheight{
    	    height: 55px;
    }
    .belowhed{
    	background-color: cadetblue !important;
    }
    .browncolor{
    	    background-color: brown !important;
    }
    .catitle{
    	    background-color: #FCCE75;
    padding: 5px;
    }
    .backkichet{
    	    padding-right: 0px;
    background-color: #957EAC;
    min-height: 540px;
    padding-left: 0px;
    }
    .breakfast{
    	    color: #fff;
    background-color: #9BC28B;
    }
    .midmorning{
    	    color: #fff;
    background-color: #71AAB1;
    }
    .lunch{
    	color: #fff;
    background-color: #927DAA;
    }
     .midafternoon{
    	color: #fff;
    background-color: #B37988;
    }
     .dinner{
    	color: #fff;
    background-color: #F4555A;
    }
     .midevening{
    	color: #fff;
    background-color: #FE9F4C;
    }
    
    .breakfast1{
    	    color: #9BC28B;
    }
    .midmorning1{
    color: #71AAB1;
    }
    .lunch1{
    	color: #927DAA;
    }
     .midafternoon1{
    	color: #B37988;
    }
     .dinner1{
    color: #F4555A;
    }
     .midevening1{
   color: #FE9F4C;
    }
    .btnclock{
    	    background-color: transparent;
    color: #555;
    font-size: 18px;
    border: none;
    }
.borightset{
	    padding-left: 0px;
    border-right: 1px solid #efefef;
    min-height: 550px;
}

        </style>
        <script type="text/javascript" src="dietary/js/dietaryCatDetail.js"></script>
</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        
                            <div class="form-inline" >
                            <form class="form-inline" action="showgeneraldietplanDietarydetails" >
				                <div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Dietary Dashboard <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
                                </div>
                       <%--          <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <!--<input type="text" class="form-control" name="fromdate" placeholder="Search Patient">
                                --><s:textfield name="searchtext"  cssClass="form-control"  placeholder="Search Patient Name / Reg.no"/>
                                </div> --%>
                                /
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield name="fromdate" cssClass="form-control" id="fromdate" placeholder="From Date"></s:textfield>
                            	</div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield cssClass="form-control" name="todate" id="todate" placeholder="To Date"></s:textfield>
                                </div>
              <%--                				                        	<div class="form-group">
				<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="width:100%;">Select Bed/Ward <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<s:iterator value="invoiceTypeLis">
												 	 <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="pacss" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</ul>
			</div> --%>
                               <!--  <a type="button" href="#" class="btn btn-primary" data-toggle="modal" data-target="#dietmodal">Create Diet Plan</a> -->
                            	<!-- <a class="btn btn-primary" href="#" onclick="opencPopup('generaldietplanDietarydetails')"><i
    class="fa fa-plus"></i> Create General Diet Plan</a> -->
     <button type="submit" class="btn btn-primary">Go</button>
   <a class="btn btn-primary" href="#" onclick="openBlankPopup('generaldietplanDietarydetails')"><i
    class="fa fa-plus"></i> Create General Diet Plan</a>
   
                            	</form>
                            	
                            </div>
                        
                    </div> 
                    
                    <div class="row">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                        <div class="col-lg-12 col-md-12 col-sm-12 borightset">
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Sr.No</th>
                                    <th>Date</th>
                                    <th>Diet Plan For</th>
                                   <!--  <th>Ward / Bed</th> -->
                                  <!--   <th>View Diet Plan</th>
                                    <th>Diet Given</th>
                                     
                                    <th>History</th> -->
                                        <th>View Diet plan</th> 
                                        <th>Dietician Incharge</th>
                                    <th>Edit</th>
                                 <th>Canteen Print</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%int i=0;%>
                            <s:iterator value="showgeneraldietplanlist">
              				<%++i;%>
              				<tr>
              					<td><%=i%><s:hidden id="id" name="parentid"></s:hidden></td>
              					<td><s:property value="datetime"/><s:hidden id="ipdid" name="ipdid"></s:hidden></td>
              					<%-- <td><s:property value="diettimeshift"/></td> --%>
              					<td>
									<s:if test="diettimeshift==1">
										Morning
									</s:if>
									<s:elseif test="diettimeshift==2">Noon</s:elseif>
									<s:elseif test="diettimeshift==3">Evening</s:elseif>
									<%-- <s:else if test="diaettime==2">
										Lunch
									</s:if>
									<s:elseif test="diettimeshift==2">After Noon</s:elseif> --%>
								</td>
								
								
								
              					
              					<%-- <td><s:property value="wardname"/>  
              					<s:if test="bedname!=null">
              						/<s:property value="bedname"/>
              					</s:if>
              					</td> --%>
              					<%-- <td><a data-toggle="collapse" href="#viewDietplan<%=i%>" aria-expanded="false" aria-controls="viewDietplan" class="collapsed">View Diet Plan</a></td>
              					<td><s:property value="givendiet"/>/<s:property value="totaldiet"/></td> --%>
              					<td><a href="printgeneraldietplanDietarydetails?id=<s:property value="id"/>&date=<s:property value="datetime"/>" >View Diet Plan</a></td>
              					<td><s:property value="dietician_incharge"/></td>
              				
              					 <!--<a data-toggle="collapse" href="#editdietmodal" aria-expanded="false" aria-controls="editdietmodal" class="fa fa-pencil-square-o"></a>-->
              						<%-- <a href="#" onclick="editDiet(<s:property value="ipdid"/>,<s:property value="parentid"/>)" class="fa fa-pencil-square-o"></a> --%>
              					<td class="text-center"><a class="pull-left" href="#" onclick="opencPopup('editgeneraldietplanDietarydetails?id=<s:property value="id"/>&date=<s:property value="datetime"/>')"><i class="fa fa-edit"></i></a></td>
              			 	<td><a href="printgeneraldietplanDietarydetails?id=<s:property value="id"/>&date=<s:property value="datetime"/>&printnew=1" ><i class="fa fa-print"></i></a></td>
              					
              					<%-- <td>
              						<s:if test="givendiet!=totaldiet">
              							<a href="deleteplanDietarydetails?parentid=<s:property value="parentid"/>" onclick="return confirmDelete()">Remove</a>
              						</s:if>
              						<s:else>
              							<p>Completed</p>
              						</s:else>
              					</td> --%>
              				</tr>
              				<%-- <tr style="background-color: rgb(221, 221, 221); height: 0px;" class="collapse" id="viewDietplan<%=i%>" aria-expanded="true">
                                    <td colspan="999"> 
                                    <table class="table my-table xlstable table-bordered">
                                    	<tbody>
                                    		<tr>
                                    			<th class="belowhed" style="width:1%;"></th>
                                    			<th class="belowhed" style="width:15%;">Day Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet Plan</th>
                                    			<th class="belowhed" style="width:16%;">Diet</th>
                                    			<th class="belowhed" style="width:10%;">Feed</th>
                                    			<th class="belowhed" style="width:16%;">Protein</th>
                                    			<th class="belowhed" style="width:15%;">Calories</th>
                                    			
                                    			<th class="belowhed" style="width:10%;">Date/Time</th>
                                    			<th class="belowhed" style="width:15%;">Delivered By</th>
                                    			<th class="belowhed" style="width:18%;">Status</th>
                                    		</tr>
                                    	<%int ss=1; %>
                                    	<s:iterator value="viewdietplan">
                                    		<tr class="">
                                    			<td><s:hidden id="clientid" name="clientid"></s:hidden>
                                    				<s:if test="dietplan==1">
                                    				<i class="fa fa-square breakfast1" aria-hidden="true"></i>
                                    			</s:if>                                    			
                                    			<s:elseif test="dietplan==2">
                                    				<i class="fa fa-square midmorning1" aria-hidden="true">
                                    			</s:elseif>
                                    			<s:elseif test="dietplan==3">
                                    				<i class="fa fa-square lunch1" aria-hidden="true"></i>
                                    			</s:elseif>
                                    			<s:elseif test="dietplan==4">
                                    				<i class="fa fa-square midafternoon1" aria-hidden="true"></i>
                                    			</s:elseif>
                                    			<s:elseif test="dietplan==5">
                                    				<i class="fa fa-square dinner1" aria-hidden="true"></i>
                                    			</s:elseif>
                                    			<s:elseif test="dietplan==6">
                                    				<i class="fa fa-square midevening1" aria-hidden="true">
                                    			</s:elseif>   
                                    			<%=ss %>            			
                                    			</td>
                                    			<td><s:property value="dietplanname"/></td>
                                    			<td><s:property value="category"/></td>
                                    			<td><s:property value="subcategory"/></td>
                                    			<td><s:property value="feedname"/></td>
                                    			<td><s:property value="protein"/></td>
                                    			<td><s:property value="calories"/></td>
                                    			<td><s:property value="feedname"/></td>
                                    			<td><s:property value="datetime"/></td>
                                    			<td><s:property value="userid"/></td>
                                    			<td><s:if test="dietstatus!=null">
                                    				<s:property value="cafename"/>
                                    			</s:if></td>
                                    			<td><s:if test="dietstatus!=null">
                                    				<i class="fa fa-check text-success"></i>
                                    				<s:property value="dietstatus"/>
                                    			</s:if></td>
                                    		</tr>
                                    		<%ss++; %>
                                    	</s:iterator>
                                    	<tr>
                                    	<td><b>Total</b></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td><s:property value="totalProtein"/></td>
                                    	<td><s:property value="totalCalories"/></td>
                                    	
                                    	<td></td>
                                    	<td></td>
                                    	
                                    	
                                    	</tr>
                                    	
                                    	
                                    	
                                    	</tbody>
                                    </table>
                                   </td>
                                  </tr> --%>
              				</s:iterator>
              			</tbody>
                        </table>
                        </div>
                        <%-- <div class="col-lg-4 col-md-4 col-sm-4">
                        <center style="margin-top: -15px;"><h3 style="border-bottom: 1px solid #efefef;line-height: 36px;">Patient Growth Chart</h3></center>
                                <!-- tile body -->
                                <div class="tile-body p-0">
                                    <div role="tabpanel">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div>
                                                <div class="wrap-reset" style="max-height: 500px;overflow:auto;">
                                                    
                                                    <div class="media">
                                                        <div class="pull-left thumb">
                                                            <img class="media-object img-circle" src="dietary/dietimg/random-avatar7.jpg" alt="">
                                                        </div>
                                                        <div class="pull-right mt-10">
                                                            <a class="pull-right" role="button" tabindex="0" title="calories">
                                                    			<span class="sparklineChart" values="5, 8, 3, 4, 6, 2, 1, 9, 7" sparktype="bar" sparkbarcolor="#92424e" sparkbarwidth="6px" sparkheight="36px"><canvas width="62" height="36" style="display: inline-block; width: 62px; height: 36px; vertical-align: top;"></canvas></span>
                                                			</a>
                                                        </div>
                                                        <div class="media-body">
                                                            <p class="media-heading mb-0 mt-10">Mr.Sukhendu Das</p>
                                                            <small class="text-lightred">SEMIPVT / 23</small>
                                                        </div>
                                                    </div>
                                                    <div class="media">
                                                        <div class="pull-left thumb">
                                                            <img class="media-object img-circle" src="dietary/dietimg/random-avatar6.jpg" alt="">
                                                        </div>
                                                        <div class="pull-right mt-10">
                                                            <a class="pull-right" role="button" tabindex="0" title="calories">
                                                    			<span class="sparklineChart" values="2, 4, 5, 3, 8, 9, 7, 3, 5" sparktype="bar" sparkbarcolor="#397193" sparkbarwidth="6px" sparkheight="36px"><canvas width="62" height="36" style="display: inline-block; width: 62px; height: 36px; vertical-align: top;"></canvas></span>
                                                			</a>
                                                        </div>
                                                        <div class="media-body">
                                                        	<p class="media-heading mb-0 mt-10"><STRONG>Mr.Jaysigh Rathod</STRONG></p>
                                                            <small class="text-lightred">SEMIPVT / 25</small>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /tile body -->
                           
                        </div>
                        
                        </div>
                    </div>
                </div>
               --%>

                
                
 <%--       <!-- create Diet Management -->

		 <div class="modal fade" id="dietmodal" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg setbig">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title">Create Diet Chart</h5>
				</div>
				<s:form action="saveDietPlanDietarydetails" id="saveForm"  theme="simple">
				<div class="modal-body">
					<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                      <div class="form-group">
									    <label for="exampleInputEmail1">Select patient</label>
									    	<s:select list="bedlist" name="patient_id" id="patient_id" headerKey="0" headerValue="Select Patients" 
									    	listKey="addmissionid" listValue="clientname" onchange="selectedbed(this.value)" cssClass="form-control chosen-select">
									    	</s:select>
									  	</div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                    
                                       <div class="form-group">
                                          <b>Condition</b>
                                          <p id="condition"></p>
                                      </div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Ward / Bed</b>
                                         <div class="clearfix"></div> 
                                         <span id="ward"></span> / <span id="bedid"></span>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Age / Gender</b>
                                            <div class="clearfix"></div> 
                                          <span id="age"></span> / <span id="gender"></span>
                                           
                                       
                                      </div>
                                  </div>
                          </div>
                      </div>
                      <div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                            <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Consultant</b>
                                          <p id="consultant"></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                       <div class="form-group">
                                          <b>Nutrition Incharge</b>
                                          <p id="Nutrition_Incharge"></p>
                                      </div>
									  </div>
									  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                       <div class="form-group">
                                          <b>Date / Time</b>
                                          <p id="datetime"></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                     
                                  </div>
                                 
                                  </div>
                          </div>
                      
                      <hr>
                      
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <br>
                             
                              
                              
                             
                              <div class="clearfix"></div>
                              <div class="col-md-3 padingleferightzero">
                           <s:select list="dietarycategoryList" theme="simple" name="category" id="category" headerKey="0" headerValue="Select Diet Category" listKey="id" listValue="name" onchange="selectCategoryDetails(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
                              </div>
                              <div class="col-md-3 padingleferightzero"></div>
                               <div class="col-md-3" id="tempdiv">
                              <s:textfield id="templatename" name="templatename" cssClass="templatenamediet" placeholder= " Template Name"></s:textfield> 
                              </div>
                               
                               <div class="col-md-3 padingleferightzero">
                            <s:select list="templatelist" name='template' id='template' listKey="id" listValue="templatename" cssClass='form-control showToolTip chosen-select' onchange='selectedTemplate(this.value)' headerKey="0" headerValue="Select Template" ></s:select>
                              </div>
                             <div class="divhightsixpx"> &nbsp; <br> </div>
                              <table class="table" cellspacing="0" width="100%">
                                  <thead>
                                      <tr>
                                          <th class="thfont">Day Plan</th>
                                         
                                          <th class="thfont">Diet</th>
                                          <th class="thfont">Feed</th>
                                           <th class="thfont" >Protein</th>
                                          <th class="thfont">Calories</th>
                                          <!-- <th class="thfont">Feed</th> -->
                                          <th class="thfont">Duration</th>
                                          <th class="thfont"></th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td style="width: 15%">
                                           	<select  name="dietplan" id="dietplan" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Day plan</option>
											    <option value="Breakfast">Breakfast</option>
											    <option value="Midmorning Snack">Midmorning Snack</option>
											    <option value="Lunch">Lunch</option>
											    <option value="Midafternoon Snack">Midafternoon Snack</option>
											    <option value="Dinner">Dinner</option>
											    <option value="Midevening Snack">Midevening Snack</option>
											</select>
											<s:select list="dietplanlist" theme="simple" name="dietplan" id="dietplan" headerKey="0" headerValue="Select Day Plan" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
                                          </td>
                                          
                                          
     									  
                                          <td style="width: 10%" id="tdsubcat">
                                   		 <select name="subcategory" id="subcategory" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select SubCategory</option>                                     	
          									</select>    
          									                                   	
                                          </td>
                                          
                                          <td style="width: 12%">
                                             <select name="feed" id="feed" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Feed</option>
											    <option value="15 Ml">15 Ml</option>
											    <option value="25 ML">25 ML</option>
											    <option value="35 ML">35 ML</option>
											    <option value="45 ML">45 ML</option>
											    <option value="55 ML">55 ML</option>
											    <option value="1 Glass">1 Glass</option>
											    <option value="1 Bowl">1 Bowl</option>
											    <option value="1 Katori">1 Katori</option> 
											</select>
											<s:select list="dietfeedlist" theme="simple" name="feed" id="feed" headerKey="0" headerValue="Select Feed" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
											
                                          </td>
                                          
                                          <td style="width: 10%" id="tdproo">    
                                          	<select name="protein" id="protein" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Protein</option>                                     	
          									</select>
          									
          									<input  type="text" name="protein" id="protein"> 
                                          </td> 
                                          
                                          
                                          
                                          
                                          <td style="width: 10%" id="tdcal">    
                                          	<select name="calories" id="calories" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Calories</option>                                     	
          									</select>
          									<input type="text" name="calories" id="calories"> 
                                          </td>                                                                        
                                          
                                          <td style="width: 12%">
                                             <select name="feed" id="feed" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Feed</option>
											    <option value="15 Ml">15 Ml</option>
											    <option value="25 ML">25 ML</option>
											    <option value="35 ML">35 ML</option>
											    <option value="45 ML">45 ML</option>
											    <option value="55 ML">55 ML</option>
											    <option value="1 Glass">1 Glass</option>
											    <option value="1 Bowl">1 Bowl</option>
											    <option value="1 Katori">1 Katori</option> 
											</select>
											<s:select list="dietfeedlist" theme="simple" name="feed" id="feed" headerKey="0" headerValue="Select Feed" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
											
                                          </td>
                                          <td style="width: 9%;">
                                          <div class="col-sm-6 col-xs-6 col-md-6" style="padding-left:0px;">
                                             <select name="duration" id="duration" class="form-control follow">
                                             		<option value="1">1</option>
                                             		<option value="2">2</option>
                                             		<option value="3">3</option>
                                             		<option value="4">4</option>
                                              </select>
                                          </div>
                                          
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                          		<p class="dayw">Days</p>
                                          </div>
                                          </td>
                                          <td style="width: 5%;" id="addtd">
                                           		<a href="#" type="button" onclick="addTempDiet()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
										  </td>
                                      </tr>
                                  </tbody>
                              </table> 
                          </div>
                         <div class="slimScrollDiv" ><div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight scrolldiet" >
                              <table class="table table-bordered" cellspacing="0" width="100%" id="tabletrcount">
                                  <thead>
                                      <tr class="tableback">
                                      	  <th class="med8">Day Plan</th>
                                          <th class="med21">Diet Category</th>
                                          <th class="med9">Diet</th>
                                          <th class="med9">Feed</th>
                                           <th class="med9">Protein</th>
                                          <th class="med9">Calories</th>
                                          <!-- <th class="med9">Feed</th> -->
                                          <th class="med30">Duration</th>
                                          <th>Delete</th>
                                      </tr>
                                  </thead>
                                 
                                  <tbody id="diettable"></tbody>
                                  <input type="hidden" id="tcount">
                              </table>

                          </div>
                          <div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 190px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10">
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10">
                                  <form>
                                      <div class="form-group hidden">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language1" value="English" checked=""> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language2" value="Regional"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language3" value="Hindi"> Hindi
                                              </label>
                                          </div>
                                          
                                          
                                      </div>

                                  </form>
                                  <form class="form-horizontal hidden">
                                   
                                      <textarea name="dietadvoice" id="dietadvoice" class="form-control setyerx" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                              
                          </div>
                      </div>
				</div>
				<div id="frreload"></div>
				<div class="modal-footer" id="savebtndiv">
					<!-- <button type="button" class="btn btn-primary" onclick="saveTemplate()">Save Template</button> -->
					<button type="button" class="btn btn-primary" onclick="addTemplate()">Save Template</button>
					<button type="button" class="btn btn-primary" onclick="savedietaryInfo()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
				</s:form>
			</div>
		</div>
	</div>




       <!-- Edit Diet Management -->
	<div class="modal fade" id="editdietmodal" tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg setbig">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				<h5 class="modal-title">Edit Diet Chart</h5>
			</div>
				<s:form action="updateDietDataDietarydetails" id="updateForm"  theme="simple">
				<div class="modal-body">
					<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                      <div class="form-group" >
									    <label for="exampleInputEmail1">Select patient</label>
									    	<div id="seleditdiv">
									    	</div>
									</div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                    
                                       <div class="form-group">
                                          <b>Condition</b>
                                          <p id="econdition"></p>
                                      </div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Ward / Bed</b>
                                          <p id="eward"></p><p id="ebedid"></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Age / Gender</b>
                                          <p id="eage"></p><p id="egender"></p>
                                      </div>
                                  </div>
                          </div>
                      </div>
                      <div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                            <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Consultant</b>
                                          <p id="econsultant"></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4 marleft9">
                                       <div class="form-group">
                                          <b>Nutrition Incharge</b>
                                          <p id="eNutrition_Incharge"></p>
                                      </div>
									  </div>
									  <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                       <div class="form-group">
                                          <b>Date / Time</b>
                                          <p id="edatetime"></p>
                                      </div>
                                  </div>
                                   <div class="col-lg-3 col-md-4 col-xs-4 col-sm-4">
                                     <s:hidden id="pid" name="pid"></s:hidden>
                                  </div>
                                 
                                  </div>
                          </div>
                      
                      <hr>
                      
                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martpomin15">
                              <br>
                              <table class="table" cellspacing="0" width="100%">
                                  <thead>
                                      <tr>
                                          <th class="thfont">Day Plan</th>
                                          <th class="thfont">Diet Category</th>
                                          <th class="thfont">Diet</th>
                                          <th class="thfont">Feed</th>
                                          <th class="thfont">Protein</th> 
                                          <th class="thfont">Calories</th>
                                          <!-- <th class="thfont">Feed</th> -->
                                          <th class="thfont">Duration</th>
                                      	  <th class="thfont"></th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <tr>
                                          <td style="width: 15%">
                                          	
                                           	<select name="dietplan" id="e_dietplan" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Day plan</option>
											    <option value="Breakfast">Breakfast</option>
											    <option value="Midmorning Snack">Midmorning Snack</option>
											    <option value="Lunch">Lunch</option>
											    <option value="Midafternoon Snack">Midafternoon Snack</option>
											    <option value="Dinner">Dinner</option>
											    <option value="Midevening Snack">Midevening Snack</option>
											</select>
                                          	<s:select list="dietplanlist" theme="simple" name="dietplan" id="e_dietplan" headerKey="0" headerValue="Select Day plan" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
                                          </td>
                                          
                                          <td style="width: 10%">
                                          	<s:select list="dietarycategoryList" theme="simple" name="category" id="e_category" headerKey="0" headerValue="Select Category" listKey="id" listValue="name" onchange="selectSubDiet(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
     									  </td>
     									  
                                          <td style="width: 10%" id="subcat">
                                   			<select name="subcategory" id="e_subcategory" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select SubCategory</option>                                     	
          									</select>                                         	
                                          </td>
                                          
                                           <td style="width: 12%">
                                             <select name="feed" id="e_feed" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Feed</option>
											    <option value="15 Ml">15 Ml</option>
											    <option value="25 ML">25 ML</option>
											    <option value="35 ML">35 ML</option>
											    <option value="45 ML">45 ML</option>
											    <option value="55 ML">55 ML</option>
											    <option value="1 Glass">1 Glass</option>
											    <option value="1 Bowl">1 Bowl</option>
											    <option value="1 Katori">1 Katori</option> 
											</select>
											<s:select list="dietfeedlist" theme="simple" name="feed" id="e_feed" headerKey="0" headerValue="Select Feed" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
                                          </td>
                                           <td style="width: 10%" id="prooedit">    
                                          	<select name="protein" id="p_protein" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Protein</option>                                     	
          									</select>
          									
          									<input class="form-control" type="text" name="protein" id="p_protein">
                                          </td>
                                          
                                          
                                          <td style="width: 10%" id="subcal">    
                                          	<select name="calories" id="e_calories" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Calories</option>                                     	
          									</select>
          									
          									<input class="form-control" type="text" name="calories" id="e_calories">
                                          </td>                                                                        
                                          
                                          <td style="width: 12%">
                                             <select name="feed" id="e_feed" class="form-control showToolTip chosen-select" style="display: none;" data-original-title="" title="">
											    <option value="0">Select Feed</option>
											    <option value="15 Ml">15 Ml</option>
											    <option value="25 ML">25 ML</option>
											    <option value="35 ML">35 ML</option>
											    <option value="45 ML">45 ML</option>
											    <option value="55 ML">55 ML</option>
											    <option value="1 Glass">1 Glass</option>
											    <option value="1 Bowl">1 Bowl</option>
											    <option value="1 Katori">1 Katori</option> 
											</select>
											<s:select list="dietfeedlist" theme="simple" name="feed" id="e_feed" headerKey="0" headerValue="Select Feed" listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"></s:select>
                                          </td>
                                          <td style="width: 9%;">
                                          <div class="col-sm-6 col-xs-6 col-md-6" style="padding-left:0px;">
                                             <select name="duration" id="e_duration" class="form-control follow">
                                             		<option value="1">1</option>
                                             		<option value="2">2</option>
                                             		<option value="3">3</option>
                                             		<option value="4">4</option>
                                              </select>
                                          </div>
                                          
                                          <div class="col-sm-6 col-xs-6 col-md-6">
                                          		<p class="dayw">Days</p>
                                          </div>
                                          </td>
                                          
											
											<td style="width: 5%;">
                                           <a href="#" type="button" onclick="editTempDiet()" title="Add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
											</td>
                                      </tr>

                                  </tbody>
                              </table>
                   
                              
                          </div>
                          <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: 100%; height: 190px;"><div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 tableheight" style="overflow: hidden; width: 100%; height: 190px;">
                              <table class="table table-bordered" cellspacing="0" width="100%" id="tablecount">
                                  <thead>
                                      <tr class="tableback">
                                      	  <th class="med8">Day Plan</th>
                                          <th class="med21">Diet Category</th>
                                          <th class="med9">Diet</th>
                                          <th class="med9">Feed</th>
                                          <th class="med9">Protein</th>
                                          <th class="med9">Calories</th>
                                          <!-- <th class="med9">Feed</th> -->
                                          <th class="med30">Duration</th>
                                          <th>Delete</th>
									  </tr>
                                  </thead>
                                  <tbody id="editdietdiv"></tbody>
                                  <input type="hidden" id="tempcount">
                              </table>

                          </div>
                          <div class="slimScrollBar" style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 190px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 martop10">
                              <div class="col-lg-6 col-md-6 col-xs-6 martopmin10">
                                  <form>
                                      <div class="form-group hidden">
                                          <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                              <p for="exampleInputEmail1" class="marleft9">Advice</p>
                                          </div>
                                          
                                          <div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language1" value="English" checked=""> English
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language2" value="Regional"> Regional
                                              </label>
                                              <label class="radio-inline">
                                                  <input type="radio" name="language" id="language3" value="Hindi"> Hindi
                                              </label>
                                          </div>
                                          
                                          
                                      </div>

                                  </form>
                                  <form class="form-horizontal hidden">
                                   
                                      <textarea name="dietadvoice" id="dietadvoice" class="form-control setyerx" rows="3"></textarea>

                                  </form>
                              </div>
                              <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">


                              </div>
                              
                          </div>
                      </div>
				</div>
				<div id="frreload"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="updateDietData()">update</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
				</s:form>
			</div>
		</div>
	</div>
 --%>
                
                
                
</body>
</html>