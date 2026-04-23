<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.marbot0set{
		margin-bottom:0px !important;
	}
	.borright{
		border-right: 1px solid #efefef;
    	min-height: 550px;
	}
</style>

</head>
<body>
					<div class=" col-lg-12 col-md-12 col-sm-12 topback2">
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         	<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">Payment</label><br>
							   	<select class="form-control">
								  <option>Credited</option>
								  <option>Debited</option>
								</select>
							</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">Amount</label><br>
							    <input type="text" class="form-control">
							</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">Date</label><br>
							    <input type="text" class="form-control">
							</div>
                         </div>
                         <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                         		<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">By</label><br>
							    <select class="form-control">
								  <option>Dr.Ookalkar</option>
								</select>
							</div>
                         </div>
                         <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                         		<div class="form-group marbot0set">
							    <label for="exampleInputEmail1">Remark</label><br>
							    <input type="text" class="form-control">
							</div>
                         </div>
                         <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-left" style="margin-top: 21px;">
                         	<a href="#" class="btn btn-primary">Add</a>
                         </div>
                        </div>
                        
                        
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 borright" style="padding-left:0px;">
                        		<h3>(Hospital Name) Account <span style="color:green;">Credited</span></h3>
                        		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th class="text-right">Amount</th>
                                        <th>Date</th>
                                        <th>Credited by</th>
                                        <th>Remarks</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-right">Rs.2000</td>
                                        <td>20/11/2016</td>
                                        <td>Dr.Ookalkar</td>
                                        <td>me credited account for day to day expenses</td>
                                    </tr>
                                     <tr>
                                        <td class="text-right">Rs.2000</td>
                                        <td>20/11/2016</td>
                                        <td>Dr.Ookalkar</td>
                                        <td>me credited account for day to day expenses</td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                        	</div>
                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
                        		<h3>(Hospital Name) Account <span style="color:red;">Debited</span></h3>
                        		<table class="table table-bordered table-responsive tablestbor" cellspacing="0">
                                <thead>
                                    <tr class="tableback">
                                        <th class="text-right">Amount</th>
                                        <th>Date</th>
                                        <th>Debited by</th>
                                        <th>Remarks</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                         <td class="text-right">Rs.2000</td>
                                        <td>20/11/2016</td>
                                        <td>Dr.Ookalkar</td>
                                        <td>Cash taken by Sachin Kulkarni</td>
                                    </tr>
                                </tbody>
                            </table>
                        	</div>
                        </div>
</body>
</html>