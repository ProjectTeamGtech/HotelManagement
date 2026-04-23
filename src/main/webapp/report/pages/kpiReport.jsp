<%@taglib uri="/struts-tags" prefix="s"%>


<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">

<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
	
<SCRIPT type="text/javascript" src="report/js/report.js"></SCRIPT>
<style>
.totcolor {
	background-color: #8a6d3b !important;
}
.checkbox-custom-alt > i {
    border: none;
    color: green;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 3px 0px 5px !important;
    }
   
.checkbox-custom-alt.checkbox-custom-sm input:checked + i:before {
    font-size: 14px;
    top: 1px;
}
.checkbox-custom-alt input:checked + i {
    background-color: transparent;
    border-color: #666;
    color: #5cb85c;
    font-size: 19px;
}
</style>


<script type="text/javascript">

    window.onload = function() {
    
    	$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
    
    };
</script>



<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<h4>KPI Selection</h4>

</div>
</div>






<div class="col-lg-12 col-md-12 topback2">
	<form action="kpidashboardKPI" id="kpidashboardform"  class="form-inline">
		<div class="form-group">
			<s:select list="nabhcatagorylist" name="nabhcatalistid" headerKey="0" headerValue="Select Catagory" listKey="id" listValue="name" onchange="setNabhSubcatagory(this.value)"  cssClass="form-control chosen-select"></s:select>
		</div>
		<div class="form-group" id="nabhsubcatdiv">
			<s:select list="nabhsubcatagorylist" name="nabhsubcatalistid" id="nabhsubcatalistid" headerKey="0" headerValue="Select Sub Catagory" listKey="id" listValue="name"  cssClass="form-control chosen-select"></s:select>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">Go</button>
		</div>
			<div style="float: right;">
				<a class="btn btn-primary" href="#" onclick="opencPopup('addIndicatorKPI')">Add New Indicator</a>
			</div>
			<%-- <div class="hidden">
				<s:select list="arealist" name="areaid" headerKey="0" headerValue="Select Area" listKey="id" listValue="name" onchange="selectKPIArea()" cssClass="form-control chosen-select"></s:select>
			</div> --%>
	</form>
	
	
</div>
<form action="addindicatortokpiKPI"  method="post" id="indicatorform">
<div class="col-lg-12 col-xs-12 col-md-12 kpiheight" style="padding: 0px;">
<table class="table table-bordered table-hover table-responsive" id="chargestbl1"
	style="width: 100%; font-size: 8px">
	<thead>
		<tr>
			<th>Sr.No</th>
			<th>Areas</th>
			<th><label class="checkbox checkbox-custom-alt m-0 checkbox-custom-sm"><input type="checkbox" onclick="selectAllIndicator(this)" id="select-all"><i style="border-color: #fff;color:#fff"></i> Indicator</label></th>
			<th>Formula</th>
			<th>Indicator Type</th>
		</tr>
	</thead>

	<tbody>
		<s:hidden name="allindicatorid" id="allindicatorid"></s:hidden>
		<%int i=1; %>
		<s:iterator value="indicatorlist">
		<tr>
		
		
			<%-- <s:hidden id="indicator<s:property value="id"/>" value="<s:property value="indicator"/>"></s:hidden> --%>
			<td><%=i%></td>
			<td><s:property value="area"/></td>
			<%-- <td id="kpitd<s:property value="id"/>"> --%>
			<td id="kpitd<s:property value="id"/>"> 
			<!-- <td> -->
				<s:if test="indi_check==1">
					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox" value="<s:property value="id"/>" id="checki<s:property value="id"/>" class="akash" checked="checked" onclick="disselectIndicatorCheckbox(this.value,'<s:property value="indicator"/>')"><i></i><s:property value="indicator"/></label>
					<%-- <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox" value="<s:property value="id"/>" class="akash" checked="checked"><i></i><s:property value="indicator"/></label> --%>
				</s:if>
				<s:else>
					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox" value="<s:property value="id"/>" id="checki<s:property value="id"/>" class="akash"><i></i><s:property value="indicator"/></label>
				</s:else>
				
			</td>
			<td><s:property value="formula_desc"/></td>
			<td>
				<s:if test="ismannual==0">
					User
				</s:if>
				<s:else>
					System
				</s:else>
			</td>
		</tr>
		<%i++;%>
		</s:iterator>
	</tbody>
</table>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;margin-top: 10px;">
	<div class="form-group">
			<input type="submit" onclick="saveKPIIndicator()" class="btn btn-primary" style="float: right;"  title="Save" value="Save">
	</div>
</div>
</form>






<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script> <script
	src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript"
	charset="utf-8"></script> <script type="text/javascript">
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
            $(window).load(function(){

                $('#select-all').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select input').prop('checked', false);
                    }
                });
                $('#select-all1').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select1 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select1 input').prop('checked', false);
                    }
                });
                $('#select-all2').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select2 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select2 input').prop('checked', false);
                    }
                });
                $('#select-all3').change(function() {
                    if ($(this).is(":checked")) {
                        $('#mails-list .mail-select3 input').prop('checked', true);
                    } else {
                        $('#mails-list .mail-select3 input').prop('checked', false);
                    }
                });

            });
        </script>
        
        <script>
        //Abhi added scroll
				             $(function() {
								  $('.kpiheight').slimScroll({
								   		height : '425px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>
  