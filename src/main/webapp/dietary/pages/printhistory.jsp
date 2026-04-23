<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<link href="_assets/css/priscription/Notification.css"rel="stylesheet" />
   <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
   
 
    
    <style>
        hr {
            margin-top: 0px;
            margin-bottom: 10px;
        }
        .marbot10 {
                margin-top: 50px;
        }
        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            padding: 0px 3px 1px 3px !important;
            vertical-align: top;
            border-top: 0px solid #DDD !important;
            border-bottom: 1px solid #ddd !important;
        }
        .marleft16 {
            margin-left: -16px;
        }
        .docsig {
            float: right;
            margin-right: -30px;
        }
        .invest8 {
            width: 11% !important;
        }
        .martop20 {
            margin-top: -20px;
        }
        .marto{
           margin-top: 45px;
    margin-bottom: 50px;
        }
        .invest6 {
    width: 4% !important;
}
.topsetline{
	    border-top: 2px solid #000;
}

    </style>

</head>
<body>
<div class="container-fluid">
        <!-- /.row -->
        <div>
			<div class="col-lg-2 col-md-2"></div>
            <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">

                <div class="">
                    <div class="panel-body">
                        <div class="row marto">
                           <link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
                        </div>
                        <br>
						<s:if test="reportType=='Numerical'" >
                          <div class="row">
                              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-left:-15px;">
                                      <p><strong>Ref.No</strong>: <s:property value="id"/></p>
                                      <p><strong>NAME</strong>: <s:property value="fullname"/></p>
                                      <p><strong>Ref.By</strong>: <s:property value="diaryUser"/></p>
                                  </div>
                                  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                      <p><strong>Lab ID</strong>:<s:property value="id"/> </p>
                                      <p><strong>AGE</strong>: <s:property value="ageandgender"/></p>
                                  </div>
                                  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                      <p><strong>Test Date</strong>: <s:property value="dateTime"/> </p>
                                      <p><strong>SEX</strong>: <s:property value="gender"/></p>
                                      <p><strong>Sample Collected</strong>: inside Lab</p>
                                  </div>
                                  
                              </div>
                          </div>
                      </s:if>
                      <s:else>
                      	<div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                              <form>
                                  
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-left:-15px;">
                                      	  <s:hidden id="parentid" name="parentid"></s:hidden>
                                          <p><strong>Name</strong>: <s:property value="clientname"></s:property></p>
                                          <!--<p><strong>Name</strong>: Mr.Sukhendu Das</p>-->
                                      </div>
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                          <p><strong>Age / Gender</strong>: <s:property value="age"/>/<s:property value="gender"/></p>
                                          <!--<p><strong>Age / Gender</strong>: 42 / Male</p>-->
                                      </div>
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                          <!--<p><strong>Ward / Bed</strong>: SEMIPVT / 23</p>-->
                                          <p><strong>Ward / Bed</strong>: <s:property value="wardname"/>/<s:property value="bedname"/></p>
                                      </div>
                                  </div>
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-left:-15px;">
                                          <p><strong>Consultant</strong>: <s:property value="consultant"/></p>
                                          <!--<p><strong>Consultant</strong>: Dr.Ookalkar</p>--></div>
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                           <p><strong>Adm. Date</strong>: <s:property value="admissiondate"/></p>
                                           <!--<p><strong>Admission Date</strong>: 05/10/2016</p>--></div>
                                      <%-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                          <p><strong>Discharge Date</strong>: <s:property value="disdate"/></p>
                                          <!--<p><strong>Discharge Date</strong>: </p>--></div> --%>
                                  </div>
                                <%--  <div class="col-md-3">
                                  <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield name="fromdate" cssClass="form-control" id="fromdate" placeholder="From Date"></s:textfield>
                            	</div>
                            	</div>
                            	<div class="col-md-3">
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield cssClass="form-control" name="todate" id="todate" placeholder="To Date"></s:textfield>
                                </div>
                                </div> --%>
                              </form>
                          </div>
                      </div>
                      </s:else>
                        <hr />
                       
                        <form action="printdiethistoryDietarydetails">
                        <div class="row rowblank">
                         	  <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                         	  <center><h3 class="text-left" style="margin-top: 0px;">Diet History &nbsp; </h3> </center>
                         	  </div>
                         	   <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                  <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                   <s:hidden name="parentid" ></s:hidden>
                                    <s:textfield name="fromdate" cssClass="form-control" id="fromdate" placeholder="From Date"></s:textfield>
                            	</div>
                            	</div>
                            	 <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <s:textfield cssClass="form-control" name="todate" id="todate" placeholder="To Date"></s:textfield>
                                </div>
                                </div>
                                 <button type="submit" class="btn btn-primary hidden-print">Go</button> 
                                 
                                <button type="submit" class="btn btn-primary hidden-print" title="Print" onclick="printpage()"><i class="fa fa-print"></i></button>
                                 
                               
                         	<table class="table my-table xlstable table-bordered">
                                    	<tbody>
                                    		<tr class="prinbgcolor">
                                       <td  class="prinbgcolor" style="width:10%;">Date</td>
                                       <td class="belowhed"  >Day Plan</td>
                                       <td class="belowhed"  >Diet Plan</td>
                                       <td class="belowhed"  >Diet</td>
                                       <td class="belowhed"  >Protein</td>
                                       <td class="belowhed"  >Calories</td>
                                        <td class="belowhed"  >Sodium</td>
                                         <td class="belowhed"  >Potassium</td>
                                       <td class="belowhed"  >Feed</td>
                                       <td class="belowhed"  >Given Time</td>
                                       <td class="belowhed"  >Delievered By</td>
                                       <td class="belowhed"  >Status</td>
                                      </tr>
                                    		<s:iterator value="viewdietplan">
                                    		<tr class="">
                                    			<td><s:property value="lastmodified"/></td>
                                    			<td><s:property value="dietplanname"/></td>
                                    			<td><s:property value="category"/></td>
                                    			<td><s:property value="subcategory"/></td>
                                    			<td><s:property value="protein"/></td>
                                    			<td><s:property value="calories"/></td>
                                    			<td><s:property value="sodium"/></td>
                                    			<td><s:property value="potassium"/></td>
                                    			<td><s:property value="feedname"/></td>
                                    			<td><s:property value="datetime"/></td>
                                    			<%-- <td><s:if test="dietstatus!=null">
                                    				<s:property value="cafename"/>
                                    			</s:if></td>  --%>
                                    			<td><s:property value="userid"/></td>
                                    			<td><s:if test="dietstatus!=null">
                                    				<i class="fa fa-check text-success"></i>
                                    				<s:property value="dietstatus"/>
                                    			</s:if></td>
                                    		</tr>
                                    	</s:iterator>
                                    	<tr>
                                    	<td><b>Total</b></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td><s:property value="totalProtein"/></td>
                                    	<td><s:property value="totalCalories"/></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	<td></td>
                                    	</tr>
                                    	
                                    	
											
                                    		<!--<tr class="">
                                    			<td>12/10/2016</td>
                                    			<td>Breakfast</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Orange Juice</td>
                                    			<td>45 calories</td>
                                    			<td>75 ML</td>
                                    			<td>07:45 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midmorning Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Applesauce</td>
                                    			<td>45 calories</td>
                                    			<td>50 ML</td>
                                    			<td>09:00 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Lunch</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pudding</td>
                                    			<td>45 calories</td>
                                    			<td>25 ML</td>
                                    			<td>11:45 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midafternoon Snack</td>
                                    			<td>Oral Liquid Diet</td>	
                                    			<td>Ice Cream</td>
                                    			<td>45 calories</td>
                                    			<td>75 ML</td>
                                    			<td>04:00 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Dinner</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pureed peaches</td>
                                    			<td>45 calories</td>
                                    			<td>50 ML</td>
                                    			<td>08:15 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midevening Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Milk</td>
                                    			<td>42 calories</td>
                                    			<td>75 ML</td>
                                    			<td>09:45 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="topsetline">
                                    			<td>13/10/2016</td>
                                    			<td>Breakfast</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Orange Juice</td>
                                    			<td>45 calories</td>
                                    			<td>75 ML</td>
                                    			<td>07:45 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midmorning Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Applesauce</td>
                                    			<td>45 calories</td>
                                    			<td>50 ML</td>
                                    			<td>09:00 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Lunch</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pudding</td>
                                    			<td>45 calories</td>
                                    			<td>25 ML</td>
                                    			<td>11:45 AM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midafternoon Snack</td>
                                    			<td>Oral Liquid Diet</td>	
                                    			<td>Ice Cream</td>
                                    			<td>45 calories</td>
                                    			<td>75 ML</td>
                                    			<td>04:00 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Dinner</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Pureed peaches</td>
                                    			<td>45 calories</td>
                                    			<td>50 ML</td>
                                    			<td>08:15 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    		<tr class="">
                                    			<td></td>
                                    			<td>Midevening Snack</td>
                                    			<td>Oral Liquid Diet</td>
                                    			<td>Milk</td>
                                    			<td>42 calories</td>
                                    			<td>75 ML</td>
                                    			<td>09:45 PM</td>
                                    			<td><i class="fa fa-check text-success"></i> Given</td>
                                    		</tr>
                                    	--></tbody>
                                    </table>
                            
                        </div>
                        </form>
<br><br>
                        <div class="row marbot10">
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 marleft16">
                                 <b>Dietician:</b>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 " >
                                 <b style="margin-left: 200px;">Doctor Sign:</b>
                                </div>
                             
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
                                    <form class="form-inline">
                                        <div class="form-group docsig">
                                            <b><s:property value="pathLabuser"/></b> <br>
                                            <b><s:property value="qualification"/></b>
                                        </div>
                                    </form>
                                </div>
                                
                            </div>
                        </div>
                        <br>
                        <s:if test="reportType!='Numerical'" >
     
    	
   <div class="row hidden-print">
  
   		<s:iterator value="docList">
   			<div class="col-lg-3 col-md-3 col-xs-4 marlft21">
																<p class="marraig"
																	style="margin-bottom: -2px; font-size: 11px;">
																	<s:property value="lastModified" />
																</p>
		  														<a
																href="downloadDocEmr?filename=<s:property value="fileName"/>"
																title="Download" class="font11">
																<s:if test="invstid>0">
																	 <s:property value="invstFoleName" />
																 </s:if>
																 <s:else>
																 	<s:property value="fileName" />
																 </s:else>
															</a>
															</div>
										
   		</s:iterator>
   </div>

   </s:if>

                        
                    </div>
                </div>


            </div>
<div class="col-lg-2 col-md-2"></div>

        </div>
        <!-- /.row -->

    </div>
    
     <script>
        function myPrint() {
            window.print();
        }
    </script>
</body>
</html>