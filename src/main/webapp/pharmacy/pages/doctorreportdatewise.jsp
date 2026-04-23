<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>  
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>



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

<% ArrayList<Priscription> doctorreport=(ArrayList<Priscription>) session.getAttribute("doctorreport"); 

    if(doctorreport==null){
    	doctorreport=new ArrayList<Priscription>();
    } 
%>

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
		     
		   document.addEventListener("contextmenu", function(e){
	    		e.preventDefault();
	    		}, false); 
    };
  
    
    function printdoctorreportExcel() {

        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Date Wise Doctor Report",
					filename: "date_wise_doctor_report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }

</SCRIPT>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
</head>
<body>
						<div class="row details mainheader hidden">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<h4>Sale Report</h4>
								</div>
							</div>
							
							
						
							
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
								
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									
										
										<div class="col-lg-12 col-md-12 topback2">
										<s:form action="doctorreportdatewisePharmacy" theme="simple" cssClass="form-inline search">
										<div class="form-group">
											<span class="text-uppercase"><b>Date Wise Doctor Report</b> &nbsp;
												- &nbsp;</span>
										</div>
										<div class="form-group">
											<s:textfield id="fromdate" readonly="true" name="fromdate" cssClass="form-control"   placeholder="From Date"/>
										</div>
										<div class="form-group">
											<s:textfield id="todate" readonly="true" name="todate" cssClass="form-control" placeholder="To Date"/>
										</div>
										<div class="form-group">
											<s:select list="pharmacydrlist" listKey="id" name="phar_practid" id="phar_practid" listValue="name"  headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
										</div>
										<div class="form-group hidden">
											<select class="form-control choosen">
											  <option>Report</option>
											  <option>Daily</option>
											  <option>Weekly</option>
											  <option>Monthly</option>
											</select>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary">Go</button>
											<!-- <a href="#" onclick="printDiv('page_printer3');" class="btn btn-sm btn-warning hidden-print" >Print</a> -->
											<a type="button"  title="Save As XLS" onclick="printdoctorreportExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
											<a type="button" class="btn btn-primary"  title="Print" onclick="printDiv('page_printer3')"><i class="fa fa-print"></i></a>
										</div>
										  
										 
										</s:form>
										
								</div>
								<div class="" id="page_printer3">
									    <div class="text-center visible-print">
											<h3><s:property value="clinicName"/></h3>
											<h6 class="hidden-lg hidden-md visible-print"><s:property value="clinicaddress"/></h6>
											<h6 class="hidden-lg hidden-md visible-print">Website:<s:property value="websiteurl"/>, Email:<s:property value="email"/>, Contact : <s:property value="landLine"/></h6>
											<h4><s:property value="location"/></h4>
											(From- <s:property value="fromdate"/> To <s:property value="todate"/>)
										</div>
								
									<table class="table table-striped table-bordered tablestock" id="tablesort" cellspacing="0" width="100%">
                                <thead>
                                <tr class="tableback">
                                        <th>Doctor Name</th>
                                        <!-- <th>Speciality</th>
                                        <th>Contact</th> -->
                                        <th>Date</th>
                                        <th>No. of Bill's</th>
                                        <th>Total Sale Amount</th>
                                        <!-- <th class="hidden">Total Sale GST</th> -->
                                        <th>Total Purchase Amount With GST</th>
                                        <th>Margin</th>
                                        <!-- <th></th> -->
                                    </tr>
                                </thead>
                                	  <tbody>
                                	 <s:iterator value="doctorreportList"> 
                                    <tr>
                                        <td><s:property value="fullname" /></td>
                                        <%-- <td><s:property value="description"/></td>
                                        <td><s:property value="mobile"/></td> --%>
                                        <td><s:property value="date"/></td>
                                        <td><s:property value="count"/></td>
                                        <td>Rs.<s:property value="debit"/></td>
                                       <%--  <td class="hidden">Rs.<s:property value="vat"/></td> --%>
                                        <td>Rs.<s:property value="purchase_price"/></td>
                                        <td>Rs.<s:property value="margintotal"/></td>
                                        <%-- <td><a href="#" onclick="viewdoctorBill(<s:property value="practitionerid"/>,'<s:property value="fullname"/>')" >history</a></td> --%>
                                    </tr>
                                    </s:iterator>
                                    <!--<tr>
                                        <td>Dr. Varun Gupta</td>
                                        <td>Ortho</td>
                                        <td>9568245625</td>
                                        <td>Rs.7254.00</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                    <tr>
                                        <td>Dr. Nitin Gupta</td>
                                        <td>Ortho</td>
                                        <td>9568245625</td>
                                        <td>Rs.1200.00</td>
                                        <td><a href="#" data-toggle="modal" data-target="#history">history</a></td>
                                    </tr>
                                    
                                --></tbody>
                            </table>
								</div>
								
										
									</div>
								
							</div>
							
							
							
							
							
							
							<script src="dtr/assets/js/vendor/hichart/highcharts.js"></script>
							<script src="dtr/assets/js/vendor/hichart/exporting.js"></script>
							
							
							
							<script>
							Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Doctors List'
    },
    
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: 'Total : <b>Rs.{point.y:.1f}</b>'
    },
    series: [{
        name: 'Doctor Name',
        data: [
           <%for(Priscription priscription:doctorreport) {%>
            ['<%=priscription.getFullname()%>', <%=priscription.getDebit()%>],   
         
           <%}%> 
        ],
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});

							</script>
				<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>			
							<script>
								$(function(){
								    $('.minhesigh').slimScroll({
								        height: '450px'
								    });
								});
							</script>
							
<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>
</body>
</html>