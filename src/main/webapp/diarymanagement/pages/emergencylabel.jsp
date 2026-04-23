
<%@taglib uri="/struts-tags" prefix="s" %>

		<style>
		p {
    margin: 0 0 0px;
    font-size: 12px;
    font-weight: bold;
}
			/* .paddniltopase{
				padding-top:50px;
			} */
			label {
    margin-bottom: 1px;
}
		
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 40px;
     }
     
      @media print
   {
      p {
    margin: 0 0 0px;
    font-size: 9px;
    font-weight: bold;
}
.setprintlbl{
	margin-top: 70px;
}

   }     
		</style>

								<div class="row <!-- details mainheader -->">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Emergency Label_02.png" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Emergency Label</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/mrd.png" class="img-responsive prescripiconcircle">
											<img src="manasmaindashboard/images/MRD_Icon.svg" style="filter: brightness(5);width: 21%;margin-top: 5px;margin-left: 7px;">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>MRD Dashboard</h4>
										</div> -->
									</div>
								</div>
								<div style="margin-top: 50px;"></div>
<form action="saveemrgencydataDiaryMangent" method="post">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 setprintlbl paddinnil">
    	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
    	<div class="form-group">
        			<label>Title</label><label class="text-danger">*</label>
					<s:textfield name="emrgency_title" cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Title" placeholder="Enter Title"
						 />
        			</div>
    	 </div>
    </div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 setprintlbl paddinnil">
    	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
    			<div class="form-group">
        			<label>Text</label><label class="text-danger">*</label>
					<s:textarea name="emrgency_data" cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Data" placeholder="Enter Data"/>
        			</div>
    	</div>
    	
    </div>
    
    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 setprintlbl paddinnil">
    	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
    			<div class="form-group">
        			<label class="text-danger">Note:</label>
					<label class="text-danger">Use ~ sign to show in next line in print. For eg. texting data~tested data.</label>
        			</div>
    	</div>
    	
    </div>
    
    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 setprintlbl paddinnil">
    	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
    	<div class="form-group">
        	<button type="submit" class="btn btn-primary" style="margin-left: 150px;">Save and print</button>
        </div>
    	 </div>
    </div>
</form>	
    	
<script>
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
</script>
      	
       			
	