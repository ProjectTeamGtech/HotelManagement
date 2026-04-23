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
        
        <script type="text/javascript">
        
        /*   function demo1(){
        	 
        	   var f =document.getElementById("ad4");
        	   f.submit();
        	  
          }   */
        
        </script>
</head>
<body>
  
<div class="">
 <form class="form-inline" id="ad4" action="updategeneraldietplanDietarydetails" method="post">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                         
                            <div class="form-inline" >
                           
				                <div class="form-group titlepadright">
                                    <label for="exampleInputEmail3" ><b>Dietary Dashboard</b> <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
                                </div>
            <%--                     <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <!--<input type="text" class="form-control" name="fromdate" placeholder="Search Patient">
                                --><s:textfield name="searchtext"  cssClass="form-control"  placeholder="Search Patient Name / Reg.no"/>
                                </div>
                                <div class="form-group">
                                <s:select list="wardlist" id="wardnameid" name="wardnameid" listKey="id" listValue="wardname" headerKey="0" headerValue="Select Ward" cssClass="form-control"></s:select>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield name="fromdate" cssClass="form-control" id="fromdate" placeholder="From Date"></s:textfield>
                            	</div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield cssClass="form-control" name="todate" id="todate" placeholder="To Date"></s:textfield>
                                </div>
                                <button type="submit" class="btn btn-primary">Go</button> --%>
                               <!--  <a type="button" href="#" class="btn btn-primary" data-toggle="modal" data-target="#dietmodal">Create Diet Plan</a> -->
        <!--                        <div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('generaldietplanDietarydetails')"><i
				class="fa fa-plus"></i> Create General Diet Plan</a>
		</div> -->
		<a class="btn btn-primary" href="#" onclick="updateDietPlan()" style="float: right; margin-top:5px;"><i
				class="fa fa-plus"></i> Update Diet Plan</a>
                           	
              					 <select name="diettimeshift" class="form-control">
              					
              					    <option value="1">Morning </option>
              					    <option value="2">Noon </option>
              					    <option value="3">Evening </option>
              					 
              					 </select>
              				<div class=""><span>Date : <s:property value="date"/></span></div>
                            </div>
                        </div>
                    </div> 
                    
                    <div class="row">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                        <div class="col-lg-12 col-md-12 col-sm-12 borightset">
                       <s:hidden id="id" name="id" />
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                               <tr>
                                    <th>Sr.No</th>
                               <!--    <th>Date</th>  -->
                                    <th width="15%">Patient Name</th>
                                   <!--  <th width="10%">Ward / Bed</th> -->
                                     <th width="40%">Diagnosis</th>
                                    <th width="15%"> Diet Plan</th>
                                     
                                    <th width="40%">Remark</th>
                          
                                    <!-- <th>Edit</th> -->
                                    
                                    
                                </tr>
                            </thead>
                            <tbody>
                           
                            <%int i=0;%>
                            <s:iterator value="bedlist">
              				<s:if test="status==1">
              				<tr>
              				</s:if>
              				<s:if test="status==0">
              				<tr bgcolor="#d0f3e8">
              				</s:if>
              				
              				<td><%=i+1%></td>
              				<%-- <td><s:property value="date"/><s:hidden id="ipdid" ></s:hidden></td> --%>
              				<td><s:property value="clientname"/>
              				<div class="clearfix" style="height:15px; "></div>
              				<b>Ward / Bed</b>
              				<div class="clearfix"></div>
              				<s:property value="wardname"/>  
              					<s:if test="bedname!=null">
              						/<s:property value="bedname"/>
              					</s:if>
              				
              				</td>
              				<%-- <td><s:property value="wardname"/>  
              					<s:if test="bedname!=null">
              						/<s:property value="bedname"/>
              					</s:if></td> --%>
              					<td><s:property value="conditionname"/></td>
              					
              					
              					 <td>
              					  <input classs="form-group " type="hidden" class="myclass" id="diaetplan<%=i %>" value="<%=i %>" name="diaetplan<s:property value="id"/>" />
              					 <div class="scrolldietplan " id="scrolldietplan">
              					       <s:iterator value="catlist">
              					       <s:if test="status==1">
              					        <input checked="checked" type="checkbox" value="<s:property value="id"/>" class="fcase<%=i%>" /> <span id="check<s:property value="id"/>"><s:property value="name"/></span> <br>
              					        </s:if>
              					        <s:else>
              					         <input type="checkbox" value="<s:property value="id"/>" class="fcase<%=i%>" />  <s:property value="name"/> <br>
              					        </s:else>
              					       </s:iterator>  
              					 </div>
              					 </td>
              					<%-- <td>
              					 <select name="diaetplan<s:property value="id"/>" class="form-control">
              					 <s:iterator value="categoryList">
              					 <s:if test="select==1"><option selected="selected" value="<s:property value="id"/>"> <s:property value="name"/> </option></s:if>
              					 <s:else><option value="<s:property value="id"/>"> <s:property value="name"/> </option></s:else>
              					    
              					 </s:iterator>
              					 </select>
              					</td> --%> 
              					
              					
              					<td>
              					
              					<textarea rows="4" class="form-control dietplanwidthfull" name="remark<s:property value="id"/>"><s:property value="remark"/></textarea>
              					
              					
              					</td>
              				</tr>
     <%--          				
              					</td>
              				</tr> --%>
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
                                  <%++i;%>
              				</s:iterator>
              			
              			</tbody>
              				
                        </table>
                       
                       	 </form>
                        </div>
                      
	<!-- 		</div>
		</div>
	</div> -->
	<!-- </form> -->





                
                
                
</body>
</html>