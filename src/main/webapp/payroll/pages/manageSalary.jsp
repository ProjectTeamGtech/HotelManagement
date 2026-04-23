<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib prefix="s" uri="/struts-tags" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>YUVICARE</title>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
    <SCRIPT type="text/javascript">
    
        function paySal() {
        	 $('.pacss').each(function() { 
     			
     			if(this.checked){
     				this.value=true;
     			}
     		});
           document.myform1.submit();
        }
       
        function paySalslip() {
       	 $('.pacss').each(function() { 
    			
    			if(this.checked){
    				this.value=true;
    			}
    		});
       	document.getElementById("paysalslip").value=1;
          document.myform1.submit();
       }
    </SCRIPT>


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
           
        }
        .miheight {
            min-height: 650px;
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
        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 5px 7px 7px 7px !important;
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


    <SCRIPT type="text/javascript">
    
       function sortBranchSalary(id){ 
       
         document.getElementById("b1").value=id;
         document.branchform.submit();
       }
      
    </SCRIPT>
  <script  type="text/javascript">
	function setActionForAll(){
	/* 	var hideselect="";
	  $('.pacss').each(function() { 
			 if(this.checked==true) {
				if(hideselect==''){
					hideselect =this.value;
				} else{
					hideselect =hideselect + ',' + this.value;	
				}
			 }     
		}); */
		
	  $('.pacss').each(function() { 
			this.checked=true; 
		});
	  
	}
	
	  </script>

</head>

<body id="his" class="appWrapper sidebar-xs-forced">

    
        <section id="">

        <div class="container-fluid" style="background-color: #efefef;">
					<a>
                        <h4><strong>Salary</strong></h4>
                    </a>
                    </div>      
					
            <div class="page page-sidebar-xs-layout">

                <div class="pageheader">
                    <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                    <s:form action="salaryPayrollMaster" name="myform" method="post">
						
                   <s:hidden name="selectedyear" id="selectyr"/>
                   
							  <div class="col-lg-12 col-md-12 col-sm-12"> 
							<div class="form-inline">
							<div class="form-group">
							<label for="exampleInputName2">Month</label>
							<s:select cssClass="form-control"
										list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
										theme="simple" id="month" name="month" style="width: 45%" />
							</div>
							<div class="form-group">
							<label for="exampleInputName2">Year</label> <select name="year"
										id="year" class="form-control" style="width: 68%">
										<%
											for (int k = 1980; k <= 2020; k++) {
										%>
							<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
							</select>
							</div>
							

							<div class="form-group">
							<label for="exampleInputName2">Branch Name:</label>
							<%-- <s:select name="branch" id="branch" list="branchlist"
										listKey="branch_id" listValue="branch"
										onchange="sortBranchSalary(this.value)" cssClass="form-control" theme="simple">
							</s:select> --%>
							<s:select name="branch" id="branch" list="branchlist"
										listKey="branch_id" listValue="branch"
										 cssClass="form-control" theme="simple">
							</s:select>
							</div>
							<div class="form-group">
							<label for="exampleInputName2">Status</label>
							<s:select cssClass="form-control"
										list="#{'0':'WIP', '1':'Processed', '2':'Paid'}"
										theme="simple" id="status" name="status" style="width: 45%" />
							</div>
								&emsp;
							<div class="form-group">
							<input type="submit" class="btn btn-primary" value="Go">
							</div>
							</div>
						</div>
						<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
		<s:if test="hasActionMessages()">
			<script>
				var msg = " " + document.getElementById('message').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
			<s:actionerror/>
		</s:if>
	</div>
</div>
                     </s:form>   
                        <br />
                        <form action="finalizesalaryPayrollMaster" name="myform1" method="post">
                        <s:hidden name="selectedmonth"/>
                        <s:hidden name="year"/>
                        <s:hidden name="paysalslip" value="0" id="paysalslip"/>
                        <table class="table my-table xlstable table-bordered" style="width: 100%;">
                            <thead>
                                <tr><s:if test="status!=2">
                                	<th rowspan="5">Select All <br>
                                	<input type="checkbox"  class="pacssw" onclick="setActionForAll()" value="<s:property value="emp_id"/>"/>
                                	</th>
                                	</s:if>
                                    <th rowspan="9">Sr. No.</th>
                                    <th rowspan="9">Emp. Name</th>
                                    
                                    <th colspan="5" style="text-align: center;">Leave</th>
                                    
                                    <th colspan="2" style="text-align: center;">OT</th>
                                    <th colspan="11" style="text-align: center;">Earning</th>
                                     
                                    <th colspan="6" style="text-align: center;">Deductions</th>
                                    <th rowspan="10">Gross Pay</th>
                                    <th rowspan="10">Net Amount Pay</th>
                                    <th rowspan="10">Status</th>
                                   <!--  <th>Finalise</th> -->
                                </tr>
                                <tr>
                                <th>Days in Month</th>
                                    <th>Working days</th>
                                    <th>Days Work</th>
                                    <th>Paid Leave</th>
                                    <th>UnPaid Leave</th>
                                <th>Hours</th>
                                <th>Amount</th>
                                <th>Basic</th>
                                <th>Medical</th>
                                <th>DA on TA</th>
                                <th>Special Day</th>
                                <th>Personal Day</th>
                                <th>Transport</th>
                                <th>HRA</th>
                                <th>DA</th>
                                <th>NPA</th>
                                <th>Conveyance</th>
                                <!-- <th>Washing</th> -->
                                <th>Per Day Allowance</th>
                                <!-- <th>Vehicle Pass</th> -->
                                <th>Employee PF</th>
                                <th>Employee ESI</th>
                                <th>Other Deduction</th>
                                <th>Leave</th>
                                <th>Professional Tax</th>
                                <th>TDS</th>
                                </tr>
                               
                            </thead>
                            <tbody>
                                <%int i=0; int j=0; %>
                                <s:iterator value="salaryList">
                                
                                <tr>
                                <s:if test="status!=2">
                                	<td><input type="checkbox" name='collectionsal[<%=j%>].iselect' class="pacss"></td>
                                	</s:if>
                                    <td><%=(++i) %></td>
                                    <%-- <td><a href="editsalPayrollincrement?emp_id=<s:property value="emp_id"/>&month=<s:property value="selectedmonth"/>&year=<s:property value="selectedyear"/>"><s:property value="emp_name"/></a></td> --%>
                                        <td><s:property value="emp_name"/></td>
                                        
                                                <td style="border:0px;"><input type="hidden" name="collectionsal[<%=j%>].totaldays" value="<s:property value="totaldays"/>"><s:property value="totaldays"/></td>
                                                <td style="border:0px;"><input type="hidden" name="collectionsal[<%=j%>].workingdays" value="<s:property value="workingdays"/>"><s:property value="workingdays"/></td>
                                                <td style="border:0px;"><input type="hidden" name="collectionsal[<%=j%>].workeddays" value="<s:property value="workeddays"/>"><s:property value="workeddays"/></td>
                                                <td style="border:0px;"><input type="hidden" name="collectionsal[<%=j%>].holidays" value="<s:property value="holidays"/>"><s:property value="holidays"/></td>
                                                <td style="border:0px;"><input type="hidden" name="collectionsal[<%=j%>].total_leaves" value="<s:property value="total_leaves"/>"><s:property value="total_leaves"/></td>

                              
                                               <td style="border:0px;">0</td>
                                                <td style="border:0px;">0</td>
                                    <td>
                                       <s:if test="status==1">        
                                    <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="basic"/><input type="hidden" name="collectionsal[<%=j%>].basic" value="<s:property value="basic"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                     <s:property value="basic"/><input type="hidden" name="collectionsal[<%=j%>].basic" value="<s:property value="basic"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="basic"/><input type="hidden" name="collectionsal[<%=j%>].basic" value="<s:property value="basic"/>">
                                    </s:else>
                                    </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="medical_allowance"/><input type="hidden" name="collectionsal[<%=j%>].medical_allowance" value="<s:property value="medical_allowance"/>"></a>
                                  
                                   </s:if>
                                   <s:elseif test="status==2">
                                    <s:property value="medical_allowance"/><input type="hidden" name="collectionsal[<%=j%>].medical_allowance" value="<s:property value="medical_allowance"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="medical_allowance"/><input type="hidden" name="collectionsal[<%=j%>].medical_allowance" value="<s:property value="medical_allowance"/>">
                                    </s:else>
                                     </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="da_on_ta"/><input type="hidden" name="collectionsal[<%=j%>].da_on_ta" value="<s:property value="da_on_ta"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                     <s:property value="da_on_ta"/><input type="hidden" name="collectionsal[<%=j%>].da_on_ta" value="<s:property value="da_on_ta"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="da_on_ta"/><input type="hidden" name="collectionsal[<%=j%>].da_on_ta" value="<s:property value="da_on_ta"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="special_pay"/><input type="hidden" name="collectionsal[<%=j%>].special_pay" value="<s:property value="special_pay"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                    <s:property value="special_pay"/><input type="hidden" name="collectionsal[<%=j%>].special_pay" value="<s:property value="special_pay"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="special_pay"/><input type="hidden" name="collectionsal[<%=j%>].special_pay" value="<s:property value="special_pay"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Personal_pay"/><input type="hidden" name="collectionsal[<%=j%>].Personal_pay" value="<s:property value="Personal_pay"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                    <s:property value="Personal_pay"/><input type="hidden" name="collectionsal[<%=j%>].Personal_pay" value="<s:property value="Personal_pay"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Personal_pay"/><input type="hidden" name="collectionsal[<%=j%>].Personal_pay" value="<s:property value="Personal_pay"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Transport_allowance"/><input type="hidden" name="collectionsal[<%=j%>].Transport_allowance" value="<s:property value="Transport_allowance"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                    <s:property value="Transport_allowance"/><input type="hidden" name="collectionsal[<%=j%>].Transport_allowance" value="<s:property value="Transport_allowance"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Transport_allowance"/><input type="hidden" name="collectionsal[<%=j%>].Transport_allowance" value="<s:property value="Transport_allowance"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="hra"/><input type="hidden" name="collectionsal[<%=j%>].hra" value="<s:property value="hra"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                     <s:property value="hra"/><input type="hidden" name="collectionsal[<%=j%>].hra" value="<s:property value="hra"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="hra"/><input type="hidden" name="collectionsal[<%=j%>].hra" value="<s:property value="hra"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="da"/><input type="hidden" name="collectionsal[<%=j%>].da" value="<s:property value="da"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                    <s:property value="da"/><input type="hidden" name="collectionsal[<%=j%>].da" value="<s:property value="da"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="da"/><input type="hidden" name="collectionsal[<%=j%>].da" value="<s:property value="da"/>">
                                    </s:else>
                                   </td>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="npa"/><input type="hidden" name="collectionsal[<%=j%>].npa" value="<s:property value="npa"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                    <s:property value="npa"/><input type="hidden" name="collectionsal[<%=j%>].npa" value="<s:property value="npa"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="npa"/><input type="hidden" name="collectionsal[<%=j%>].npa" value="<s:property value="npa"/>">
                                    </s:else>
                                   </td>
                                    <td>
                                      <s:if test="status==1">
                                    <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="conveyance"/><input type="hidden" name="collectionsal[<%=j%>].conveyance" value="<s:property value="conveyance"/>"></a>
                                     </s:if>
                                     <s:elseif test="status==2">
                                         <s:property value="conveyance"/><input type="hidden" name="collectionsal[<%=j%>].conveyance" value="<s:property value="conveyance"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="conveyance"/><input type="hidden" name="collectionsal[<%=j%>].conveyance" value="<s:property value="conveyance"/>">
                                    </s:else>
                                    </td>
                                   <%-- <td><a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="washing"/><input type="hidden" name="collectionsal[<%=j%>].washing" value="<s:property value="washing"/>"></a></td> --%>
                                   <td>
                                     <s:if test="status==1">
                                   <a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Perdevallow"/><input type="hidden" name="collectionsal[<%=j%>].Perdevallow" value="<s:property value="Perdevallow"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                     <s:property value="Perdevallow"/><input type="hidden" name="collectionsal[<%=j%>].Perdevallow" value="<s:property value="Perdevallow"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Perdevallow"/><input type="hidden" name="collectionsal[<%=j%>].Perdevallow" value="<s:property value="Perdevallow"/>">
                                    </s:else>
                                   </td>
                                   <%-- <td><a href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="vehiclepass"/><input type="hidden" name="collectionsal[<%=j%>].vehiclepass" value="<s:property value="vehiclepass"/>"></a></td> --%>
                                     <td>
                                       <s:if test="status==1">
                                     <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Emp_pf"/><input type="hidden" name="collectionsal[<%=j%>].Emp_pf" value="<s:property value="Emp_pf"/>"></a>
                                      </s:if>
                                      <s:elseif test="status==2">
                                     <s:property value="Emp_pf"/><input type="hidden" name="collectionsal[<%=j%>].Emp_pf" value="<s:property value="Emp_pf"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Emp_pf"/><input type="hidden" name="collectionsal[<%=j%>].Emp_pf" value="<s:property value="Emp_pf"/>">
                                    </s:else>
                                     </td>
                                     <td>
                                       <s:if test="status==1">
                                     <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Emp_esi"/><input type="hidden" name="collectionsal[<%=j%>].Emp_esi" value="<s:property value="Emp_esi"/>"></a>
                                      </s:if>
                                      <s:elseif test="status==2">
                                    <s:property value="Emp_esi"/><input type="hidden" name="collectionsal[<%=j%>].Emp_esi" value="<s:property value="Emp_esi"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Emp_esi"/><input type="hidden" name="collectionsal[<%=j%>].Emp_esi" value="<s:property value="Emp_esi"/>">
                                    </s:else>
                                     </td>
                                     <td>
                                       <s:if test="status==1">
                                     <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Other_deduction"/><input type="hidden" name="collectionsal[<%=j%>].Other_deduction" value="<s:property value="Other_deduction"/>"></a>
                                      </s:if>
                                      <s:elseif test="status==2">
                                     <s:property value="Other_deduction"/><input type="hidden" name="collectionsal[<%=j%>].Other_deduction" value="<s:property value="Other_deduction"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Other_deduction"/><input type="hidden" name="collectionsal[<%=j%>].Other_deduction" value="<s:property value="Other_deduction"/>">
                                    </s:else>
                                     </td>
                                    <td>
                                      <s:if test="status==1">
                                    <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="leave"/><input type="hidden" name="collectionsal[<%=j%>].leave" value="<s:property value="leave"/>"></a>
                                     </s:if>
                                     <s:elseif test="status==2">
                                     <s:property value="leave"/><input type="hidden" name="collectionsal[<%=j%>].leave" value="<s:property value="leave"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="leave"/><input type="hidden" name="collectionsal[<%=j%>].leave" value="<s:property value="leave"/>">
                                    </s:else>
                                    </td>
                                     <td>
                                       <s:if test="status==1">
                                     <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Prefessional_tax"/><input type="hidden" name="collectionsal[<%=j%>].Prefessional_tax" value="<s:property value="Prefessional_tax"/>"></a>
                                     </s:if>
                                     <s:elseif test="status==2">
                                     <s:property value="Prefessional_tax"/><input type="hidden" name="collectionsal[<%=j%>].Prefessional_tax" value="<s:property value="Prefessional_tax"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Prefessional_tax"/><input type="hidden" name="collectionsal[<%=j%>].Prefessional_tax" value="<s:property value="Prefessional_tax"/>">
                                    </s:else>
                                     </td>
                                     
                                     <td>
                                       <s:if test="status==1">
                                     <a href="#" onclick="getdeduction(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)"><s:property value="Tds"/><input type="hidden" name="collectionsal[<%=j%>].Tds" value="<s:property value="Tds"/>"></a>
                                    </s:if>
                                    <s:elseif test="status==2">
                                     <s:property value="Tds"/><input type="hidden" name="collectionsal[<%=j%>].Tds" value="<s:property value="Tds"/>">
                                    </s:elseif>
                                    <s:else>
                                    <s:property value="Tds"/><input type="hidden" name="collectionsal[<%=j%>].Tds" value="<s:property value="Tds"/>">
                                    </s:else>
                                     </td>
                                   
                                     <td><s:property value="Gross_pay"/><input type="hidden" name="collectionsal[<%=j%>].Gross_pay" value="<s:property value="Gross_pay"/>"></td>
                                      <td><s:property value="calnetpay"/><input type="hidden" name="collectionsal[<%=j%>].calnetpay" value="<s:property value="calnetpay"/>"></td>
                                     <td><s:property value="Selectedstatus"/></td>
                                    <!-- <td><input type="checkbox"  class="theight">&nbsp;&nbsp;&nbsp;<a href="#" type="button" class="btn-primary btn-sm">Clear</a></td> -->
                                    <<input type="hidden" name="collectionsal[<%=j%>].Allowances" value="<s:property value="Allowances"/>">
                                    <input type="hidden" name="collectionsal[<%=j%>].deductions" value="<s:property value="deductions"/>">
                                    <input type="hidden" name="collectionsal[<%=j%>].emp_id" value="<s:property value="emp_id"/>"> 
                                    <input type="hidden" name="collectionsal[<%=j%>].id" value="<s:property value="id"/>">
									<s:hidden name="selectedempid" id="selectedempid"/>
                                </tr>
                                <% j++; %>
                                </s:iterator>
                            </tbody>
                        </table><br />
                        <input type="hidden" name="testnum" value="<%=j%>">
                        </form>
                        <a href="#" type="button" class="btn btn-primary" onclick="paySal()">Process Payroll</a>
                        <a href="#" type="button" class="btn btn-primary" onclick="paySalslip()">Process PaySlip</a>
                    </div>

                    

                </div>

                <form action="salaryPayrollMaster" name="branchform">
                
                  <input type="hidden" name="branch" id="b1">
                
                </form>

            </div>

        </section>
        <!--/ CONTENT -->








    <!--Edit Modal-->
    <!-- Modal -->
    <div class="modal fade" id="adddeduc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Deduction Details</h4>
                </div>
                <div class="modal-body row">
                    <div class="col-lg-12 col-md-12 col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Name</label>
                                <div class="col-sm-7">
                                    <input type="email" class="form-control" id="inputEmail3">
                                    <small>(Enter PF or ESI or others)</small>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Deduction Type</label>
                                <div class="col-sm-7">
                                    <select name="otstatus" class="selectstyle form-control">
                                        <option value="Percentage">Percentage</option>
                                        <option value="Fixed">Fixed</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-5 control-label">Amount</label>
                                <div class="col-sm-7">
                                    <input type="email" class="form-control" id="inputEmail3">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Add/Edit</button>
                </div>
            </div>
        </div>
    </div>
<!--Edit Modal-->
	<!-- Allowances Modal -->
	<div class="modal fade" id="Allowances" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="goreferesh()">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Allowances</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
							<table width="100%">
								<tbody>
									<tr>
										<td width="60%" valign="top" align="center">
											<div>
												<s:hidden name="empid" id="empid"/>
												<s:hidden name="month" id="month"/>
												<s:hidden name="year" id="year"/>
												<table width="90%" border="0" cellspacing="0"
													cellpadding="0" valign="top" align="right" class="table">
													<tbody>
														<tr>
															<td class="borright">Medical Allowance (fixed)</td>
															<td><s:textfield name="medical_allowance"
																	id="medical_allowance" onchange="updateallownces('medical_allowance',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">DA on TA</td>
															<td><s:textfield name="da_on_ta" id="da_on_ta" onchange="updateallownces('da_on_ta',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">SPECIAL PAY</td>
															<td><s:textfield name="special_pay" id="special_pay" onchange="updateallownces('special_pay',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">PERSONAL PAY</td>
															<td><s:textfield name="personal_pay"
																	id="personal_pay" onchange="updateallownces('personal_pay',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">TRANSPORT ALLOWANCE</td>
															<td><s:textfield name="transport_allowance"
																	id="transport_allowance" onchange="updateallownces('tarnsport_allowance',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">HRA (fixed)</td>
															<td><s:textfield name="hra" id="hra" onchange="updateallownces('hra',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">DA (fixed)</td>
															<td><s:textfield name="da" id="da" onchange="updateallownces('da',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">NPA ( fixed)</td>
															<td><s:textfield name="npa" id="npa" onchange="updateallownces('npa',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Conveyance</td>
															<td><s:textfield name="conveyance" id="conveyance" onchange="updateallownces('conveyance',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Washing</td>
															<td><s:textfield name="washing" id="washing" onchange="updateallownces('washing',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Per. Dev Allow</td>
															<td><s:textfield name="perdevallow" id="perdevallow" onchange="updateallownces('perdevallow',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Vehicle Pass</td>
															<td><s:textfield name="vehiclepass" id="vehiclepass" onchange="updateallownces('vehiclepass',this.value)"/></td>
														</tr>
														
													</tbody>
												</table>

											</div>
										</td>

									</tr>
								</tbody>
							</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="goreferesh()"
						class="btn btn-primary">Ok</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Deduction Modal -->
	<div class="modal fade" id="Deduction" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="goreferesh()">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Deduction</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<table width="100%">
							<tbody>
								<tr>
									<td width="60%" valign="top" align="center">
										<div>
											<s:form theme="simple" name="deductionform">
											<s:hidden name="empid" id="empid"/>
												<s:hidden name="month" id="month"/>
												<s:hidden name="year" id="year"/>
												<table width="90%" border="0" cellspacing="0"
													cellpadding="0" valign="top" align="right" class="table">
													<tbody>
														<tr>
															<td class="borright">Employee's PF</td>
															<td><s:textfield name="emp_pf" id="emp_pf" onchange="updatededuction('emp_pf',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">Employee's ESI</td>
															<td><s:textfield name="emp_esi" id="emp_esi" onchange="updatededuction('emp_esi',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Other Deduction</td>
															<td><s:textfield name="other_deduction"
																	id="other_deduction" onchange="updatededuction('other_deduction',this.value)"/></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Professional Tax</td>
															<td><s:textfield name="prefessional_tax"
																	id="prefessional_tax" onchange="updatededuction('professional_tax',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">TDS</td>
															<td><s:textfield name="tds" id="tds" onchange="updatededuction('tds',this.value)"/></td>
														</tr>
														<tr>
															<td class="borright">Leave</td>
															<td><s:textfield name="leave" id="leave" onchange="updatededuction('leaves',this.value)"/></td>
														</tr>
														<s:hidden name="loan" id="loan"></s:hidden>
													</tbody>
												</table>
											</s:form>
										</div>
									</td>

								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="goreferesh()"
						class="btn btn-primary">Ok</button>
				</div>
			</div>
		</div>
	</div>
<SCRIPT type="text/javascript">
  
     window.onload=function(){
    	 var year=document.getElementById("selectyr").value;
     	document.getElementById("year").value=year;
          
     };
    
  </SCRIPT>

   

</body>
</html>
