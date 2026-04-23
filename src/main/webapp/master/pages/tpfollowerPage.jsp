<%@taglib uri="/struts-tags" prefix="s"%>

<script src="master/js/tpfollow.js"></script>
<style>
	.form-group {
	    margin-bottom: 5px;
	}
	.baccolors{
		background-color: #f4f4f4;
	    min-height: 430px;
	    padding-top: 10px;
	}
	input[type="checkbox"] {
    margin-right: 4px;
}
</style>





<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Third Party Follower </h4>
									</div>
								</div>



<s:form action="saveAllTPFollower" id="tpform" theme="simple">

  <s:hidden name="name" id="allcheckedlist"> </s:hidden>
  <s:hidden id="tpid" name="tpid" ></s:hidden>
  
<div class="">
			<div class="">
				<div class="row">
					<div class="col-lg-12" id = "tpchargeTable">			
						<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 baccolors">
							<div class="form-group">
							    <label for="exampleInputEmail1">Select Main Third Party</label>
							    
							     <s:iterator value="tpmainList">
							     
							        <div class="col-lg-8 col-md-8 col-sm-8">
							         	<input type="radio" value="<s:property value="id"/>" name="main" onclick="getAllSubTp(this.value)"   > <s:property value="companyName"/> <br>
							        </div>
							         
							     </s:iterator>
							    
							  </div>
						</div>
						<div class="col-lg-9 col-md-9 col-xs-12 col-sm-9" style="padding-top:10px;">
							<!-- <div class="form-group"> -->
							    <label for="exampleInputEmail1">Select Follower Third Party</label>
							  <!--   <div > -->
							    			<table class="my-table xlstable  table-striped table-bordered" style="width: 100% !important" id="example">	
							    			<thead >
							    			<!-- 
							    				<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;border-top: 1px solid #ddd;padding-top: 2px;">
														<div class="col-lg-8 col-md-8 col-sm-8" style="padding:0px;">
														 		<div class="form-group"> -->
														 		<tr>
														 		<td>
														 		 <input type="checkbox" onclick="checkAll()" > Select All 	
														 		</td>
														 		</tr>
																<!--   <input type="checkbox" onclick="checkAll()" > Select All 				 -->														 
															    <!-- </div>
														 </div>
														
													</div> -->
							    				
							    				
							    				</thead>
							    				
							    				
							    		<tbody>
												<s:iterator value="tpsubList" >
												
												 	<!-- <div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;border-top: 1px solid #ddd;padding-top: 2px;">
														<div class="col-lg-8 col-md-8 col-sm-8" style="padding:0px;">
														 		<div class="form-group"> -->
														 		
														 		<tr>
														 		<td>
														 		 <input type="checkbox" class="case" value="<s:property value="id"/>" > <s:property value="companyName"/>
														 		</td>
														 		</tr>
														 	
																<!--   																		 
															    </div>
														 </div>
														
													</div> -->
												
												</s:iterator>
													</table>
									<!-- 	</div>
							  </div> -->
							
						</div>
								
						
						<div class="form-group hidden">
								<a onclick="" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
					</div>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-12 col-md-12 text-right">
			<input type="button" class="btn btn-primary" value="Save" onclick="saveallTp()" />
			<a href="backInvestigationNameMaster" class="btn btn-primary">Back</a>
		</div>
	</div>
	<s:token></s:token>
</s:form>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
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
$(function () {
    $('#scroll').slimScroll({
        height: '400px',
        railVisible: true,
		alwaysVisible: true
    });
   
});

</script>
<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false
       
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.print.js"></script>
	


