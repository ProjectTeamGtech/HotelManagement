
<%@taglib uri="/struts-tags" prefix="s" %>

	<%-- 	<style>
		p {
    margin: 0 0 0px;
    font-size: 12px;
    font-weight: bold;
}
			.paddniltopase{
				padding-top:50px;
			}
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
		</style> --%>

 		<style>
		p {
    margin: 0 0 0px;
    font-size: 12px;
}
			.paddniltopase{
				padding-top:40px;
			}
			label {
    margin-bottom: 1px;
}
.mbottom{
margin-bottom:48px;
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
    font-size: 10px;
}
.setprintlbl{
	margin-bottom: 17px;
}
.setprintlbl1{
	margin-bottom: 20px;
	padding: 0px 0px 0px 30px;
}
   }     
		</style>
		

			

  				<%String emergencydata = (String) session.getAttribute("emergencydata"); %>
  				<%String[] data = emergencydata.split("~"); %>
    			<%-- <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl paddinnil">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
	    		</div>
	    		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 setprintlbl paddinnil">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			<p><s:property value="emrgency_data"/></p>
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
	    		</div> --%>
    			<%for(int k=1;k<=8;k++){ %>
    	<div class="row">
    		<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 mbottom">
    			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 setprintlbl">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
    			</div>
    			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 setprintlbl">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
    			</div>
    			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 setprintlbl1">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
    			</div>
    			<%-- <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
	    			<p><label><s:property value="emrgency_title"/></label></p>
	    			<%for(int i = 0; i < data.length; i++){ %>
	    				<p><%=data[i] %></p>
	    			<%} %>
    			</div> --%>
    		</div>
    	</div>
    	<%} %>
    	<!-- Modal -->

    	
<script>
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
</script>
      	
       			
	