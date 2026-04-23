<%@taglib uri="/struts-tags" prefix="s" %>



<%@page import="java.util.ArrayList"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="com.apm.Pacs.eu.bi.PacsDAO"%>
<%@page import="com.apm.Pacs.eu.blogic.JDBCPacsDAO"%>
<%@page import="com.apm.Pacs.eu.entity.Pacs"%>

<script type="text/javascript" src="pacs/js/pacs.js"></script>
<style>
			.paddniltopase{
				padding-top:35px;
			}
			p {
    margin: 0 0 0px;
    text-align: center;
}
.setimg{
    width: 100%;
    margin-left: auto;
    margin-right: auto;
        height: 300px;
     }       
		</style>
		
			

    			<% int p=0,i=0; 
    				PreparedStatement preparedStatement = null;
    				String fname = "";
    				String id = "";
    				
    				PacsDAO pacsDAO = new JDBCPacsDAO();
    				int mdbarcodecount = (Integer)session.getAttribute("pacscount");
    				String multipacsid = (String)session.getAttribute("multipacsid");
    				ArrayList<Pacs>pimglist = (ArrayList<Pacs>)session.getAttribute("pimglist");
    				
    			%>
    			
    			<%for(i=1;i<=mdbarcodecount;i=i+4) %><% {%>
    	<div class="row">
    		<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-bottom: 15px;">
    			<% for(int j=1;j<=4;j++){%>
    			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
    				<%
    				if(p==mdbarcodecount){
    					break;
    				}
    				Pacs pacs = pimglist.get(p);
    				fname = multipacsid + "_" + pacs.getFilename();
    				%>
    				
	    		<a href="#" id="myAnchor<%=pacs.getId() %>" onclick="setshowdicomid(<%=pacs.getId() %>)" >
	    			<img src="liveData/dicom/<%=fname %>.jpeg" class="img-responsive setimg"></img>
	    		</a>
	    			<%p++; %>
    			</div>
    			<%} %>
    		</div>
    	</div>
    	<%} %>
    	
<script>
function printpage()
  {
  window.print()
  }
  
  
  function showimgdicom(){
	  
	  var url = "imgjDicom";
		
	   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showimgdicomRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
  }
  
  function showimgdicomRequest(){
	  
	  if (req.readyState == 4) {
			if (req.status == 200) {
				
	         
	         }
		}
  }
</script>
      	
 
		
		
	
				
				
				
				
				
				
				
       			
       			
	