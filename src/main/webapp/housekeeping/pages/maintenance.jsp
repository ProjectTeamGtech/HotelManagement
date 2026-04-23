<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
</style>
</head>
<body>
<div class="">
                    <div class="col-lg-12 col-md-12 col-sm-12 topback2">
                        <div class="col-md-12 martop6">
                            <form class="form-inline">
                            	<div class="form-group titlepadright">
                                    <label for="exampleInputEmail3">Maintainance Schedule <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></label>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <select class="form-control">
                                        <option>Select</option>
                                        <option>Floor 1</option>
                                        <option>Floor 2</option>
                                        <option>Floor 3</option>
                                        <option>Floor 4</option>
                                        <option>Pathlab</option>
                                        <option>Garden Area</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <select class="form-control">
                                        <option>Select Staff</option>
                                        <option>Aruna Waghe</option>
                                        <option>Nilesh Sahu</option>
                                        <option>Dilip Verma</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputEmail3">Email address</label>
                                    <select class="form-control">
                                        <option>Select Status</option>
                                        <option>Processing</option>
                                        <option>Completed</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="exampleInputPassword3">Date</label>
                                    <input type="text" class="form-control" id="exampleInputPassword3" placeholder="Search Date">
                                </div>
                                <button type="submit" class="btn btn-primary">Go</button>
                                <button type="submit" class="btn btn-primary">View Reports</button>
                                <button title="print" class="btn btn-primary"><i class="fa fa-print"></i></button>
                            </form>
                        </div>
                    </div> 
                    <div class="row">
                        <div class=" col-lg-12 col-md-12 col-sm-12">
                            <table class="table table-bordered" cellspacing="0" width="100%" style="width:70%;">
                                <thead>
                                    <tr class="tableback">
                                        <th>Floor / General</th>
                                        <th>Ward</th>
                                        <th>Staff Name</th>
                                        <th>Work Start</th>
                                        <th>Work End</th>
                                        <th>Duration</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Floor 2</td>
                                        <td>Ward 3</td>
                                        <td>Aruna Waghe</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 09:00 AM</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 10:00 AM</td>
                                        <td>1 Hour</td>
                                        <td><i class="fa fa-check" style="color:green;"></i>&nbsp; Completed</td>
                                    </tr>
                                    <tr>
                                        <td>Pathlab</td>
                                        <td>-</td>
                                        <td>Nilesh Sahu</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 09:00 AM</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 09:30 AM</td>
                                        <td>30 Min</td>
                                        <td><i class="fa fa-check" style="color:green;"></i>&nbsp; Completed</td>
                                    </tr>
                                    <tr>
                                        <td>Garden Area</td>
                                        <td>-</td>
                                        <td>Dilip Verma</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 09:00 AM</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 10:00 AM</td>
                                        <td>1 Hour</td>
                                        <td><i class="fa fa-check" style="color:green;"></i>&nbsp; Completed</td>
                                    </tr>
                                    <tr>
                                        <td>Floor 1</td>
                                        <td>Ward 2</td>
                                        <td>Aruna Waghe</td>
                                        <td><input type="checkbox" checked> 15 sep 2016 / 09:00 AM</td>
                                        <td><input type="checkbox"> 15 sep 2016 / -</td>
                                        <td>-</td>
                                        <td><i class="fa fa-spinner" aria-hidden="true"></i>&nbsp; Processing</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    
                </div>     
</body>
</html>