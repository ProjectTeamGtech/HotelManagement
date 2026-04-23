<%@page import="com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO"%>
<%@page import="com.apm.Master.eu.bi.MasterDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="com.apm.AssesmentForms.eu.bi.AssessmentFormDAO"%>
<%@page import="com.apm.AssesmentForms.web.action.Template"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
<%@page import="java.util.ArrayList"%>
<link href="common/css/Style.css" rel="stylesheet" type="text/css" /> 
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	
	<%Template template = (Template)session.getAttribute("assmnttemplatedetails");%>
	<%if(template.isIncludeImage()){%>
		document.getElementById('addcanvasdiv').style.display = 'none';
	<%}%>
	
	
	<%-- <%String columnStr = (String)session.getAttribute("columnStr");%>
	var columnstr1 = '<%=columnStr%>';
	var temp = columnstr1.split("#"); --%>
	//alert(temp);
	//alert(temp.length);
	
	var i = 0;
		<%-- <%ArrayList<String> list1 = (ArrayList<String>)session.getAttribute("assessmentFormsList");
     	for(String s : list1){%>
     	//alert(temp[i]);
     	var fieldId1 = temp[i];
     	//alert(fieldId1);
		 document.getElementById(fieldId1).value = '<%=s%>';
		
		i++;
		<%}%> --%>
     
	
	
	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
   /*  var canvas = document.getElementById('editupdateimgTemplate');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0); */
    
    var canvas1 = document.getElementById('editupdateimgTemplate');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
    
    var iwidth = imageObj.width;
    var iheight = imageObj.height;
    
    //alert(iwidth + '/' + iheight)
    };
    
    
    
	
});	

function editUpdateImageCanvas(){
	
	<%String imagedata1 = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata1);%>
	var dataURL = '<%=imagedata1%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas1 = document.getElementById('Background');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
   
    };
	
	 $( '#editimageCanvas' ).modal( "show" );
	
}



var i = 0;

function saveTemplateDetails(){
	var Pic;
	 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
	}
	 
	 document.getElementById('imageData').value = Pic;
	 
	 document.getElementById('temp_form').submit();
}
/* 
function print(){
	alert('hi');
	window.print();
} */

</script>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Edit Template Details</h4>

									</div>
								</div>



<s:form action="combinesaveAssesmentForms" id="temp_form" theme="simple"> 
<s:hidden name="repeat" id="repeat"/>
<s:hidden name="imageData" id="imageData"/>
	<div class="col-lg-12 col-md-12 col-xs-12 widthcan" style="padding: 0px;">
		<div class="">
			<div class="">
				<div class="row hidden">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp" %>
				
				</div>
		
			
		<div class="row hidden" id="addcanvasdiv">
			<div class="col-lg-12 col-md-12 col-xs-12">					
				<div class="col-lg-7 col-md-6 col-xs-12 canvwidth">
				`<canvas id="editupdateimgTemplate" height="450" width="700" ></canvas>
				</div>
			
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print" style="margin-left: 5px;">
					<h3><a href="#" class="text-warning" onclick="editUpdateImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div>
			</div>	
		</div>
			
				
				
		
			
			
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 6px 0px 0px 0px;margin-bottom: 10px;background-color: #f5f5f5;">
				<div class="col-lg-4 col-md-4 marlftright">
					<label><span class="red">*</span>Practitioner</label>
						<s:if test="%{#userList != 'null'}" >
							<s:select cssClass="form-control showToolTip chosen" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
						</s:if>
				 	<label  id = "diaryUserAssessmentError" class="text-danger"></label>	
				</div>
				<div class="col-lg-4 col-md-4 marlftright">
					<label><span class="red">*</span>Patient</label>		
					<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Patient"  onchange="setConditionAssessment(this.value)"  />
					<label  id = "clientnameAssessmentError" class="text-danger"></label>	
				</div> 
				<div class="col-lg-4 col-md-4 marlftright">	
						<label><span class="red">*</span>Speciality</label>
						<s:select onchange="setAssesmentCondition(this.value)" cssClass="form-control showToolTip chosen" id="conditionAssessment" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select Speciality"    />
					<label  id = "conditionAssessmentError" class="text-danger"></label>	
				</div> 
			</div> 
			
			<%-- </s:if>  --%>
				<s:hidden name="diaryUser" id="diaryUser"/>
				<s:hidden name="clientName" id="diaryUser"/>
				<s:hidden name="condition" id="condition"/>
				<s:hidden name="actionType" id ="actionType"/>
				<s:hidden name="mrdid" id ="mrdid"/>
				<s:hidden name="mrd_clientid" id ="mrd_clientid"/>
			<br/>	 	
	<% ArrayList<Master>leftNameList = (ArrayList<Master>)session.getAttribute("leftNameList");%>
			<% ArrayList<Master>topNameList = (ArrayList<Master>)session.getAttribute("topNameList");%>
			<% String assementtemplateId = (String)session.getAttribute("assementtemplateId");
			
			Connection connection = null;
			try{
				connection = Connection_provider.getconnection();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			%>
			
			
			<center><h3 style="text-transform: uppercase;color: brown;"><s:property value="templateName"/> (<s:property value="clientFullName"/>)<s:if test="ipdid!=0"><s:property value="wardname"/> / <s:property value="bedname"/></s:if></h3></center>
			<table class="table table-striped gridexample table-responsive" id = "allusertable">
				<col/>
				<% for (Master m : topNameList){%>
					<col/>
				<%} %>
			
			<thead>
				<tr>
					<th></th>
					<% for (Master m : topNameList){%>
					 <th style="text-align: center;" id="wn1"><%=m.getName() %></th>
					<%} %>
				</tr>
			</thead>
			
			<tbody>
				<% 
				int ct=0; 
					
				int clinicendtime = leftNameList.size();
				clinicendtime = clinicendtime - ct;
				clinicendtime = clinicendtime + 1;
					
					int countslot = 1;
					String weekName[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
					String tempCt = "";
					String tempMinute = "";
					String subjrctid = "";
					
				
				%>
				
				
					<div id="tgOver1" class="tg-col-overlaywrapper" style="display: none;">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				
				<%String classname="";int i=1;for(Master m :leftNameList){
					int cval=topNameList.size()*i;
					cval=cval-topNameList.size();
					int cp = cval+1;
					
					String dsplay = "";
					if(m.getFieldtype()!=null){
						if(!m.getFieldtype().equals("")){
							if(m.getFieldtype().equals("Heading")){
								dsplay = "none";
							}
						}
					}
					
					%>
					<tr>
					    <td style="font-size: 14px; font-weight: bold;"><span class="timeset"><%=m.getName() %></span></td>
						
						<%  for(int j=0;j<topNameList.size();j++){
								Master subj = topNameList.get(j);
							
								if(cp == countslot){
									if(j>0){
										classname = "yellow";
									}else{
										classname = "sub";
									}
									
								}else{
									
								}
							
						%>
						
							<td style="display: <%=dsplay %>" valign="top" id="<%=countslot %>" >
								<%for(int k=0;k<1;k++){ %>
										<%
											tempCt =  Integer.toString(ct);
											tempMinute = Integer.toString((5*k));
											if(ct <= 9) {
												tempCt = "0" + Integer.toString(ct);
											}
											if((5*k) <= 9) {
												tempMinute = "0" + Integer.toString((5*k));
											}
											
											MasterDAO masterDAO = new JDBCMasterDAO(connection);
											String combineformclientid = (String)session.getAttribute("combineformclientid");
											String combineformdoctorid = (String)session.getAttribute("combineformdoctorid");
											String combineformconditionid = (String)session.getAttribute("combineformconditionid");
											
											String marks = masterDAO.getCombineFormDetails(assementtemplateId,combineformclientid,combineformdoctorid,combineformconditionid,m.getId(),subj.getDisplayname());
											//String marks = masterDAO.getEnteredMarks(terms,classid,m.getId(),subj.getName());
										%>
											
										
									<%if(k==6){ %>
										<div    id="<%=(5*k) %>min<%=countslot %>-<%=subj.getId() %>-<%=m.getId() %>~<%=marks %>"  class="<%=classname %>" title="<%=tempCt %>:<%=tempMinute %>" style="text-align: center; " >
											
										</div>
									<%}else{ %>
										<div id="<%=(5*k) %>min<%=countslot %>-<%=subj.getId() %>-<%=m.getId() %>~<%=marks %>"  class="<%=classname %>" title="<%=tempCt %>:<%=tempMinute %>"  style="text-align: center;"></div>
									<% }%>
        							
        						<% }%>
								
							</td>
							<%countslot++; %>
								
						<% }%>
						
					
						<%ct++; %>
					</tr>
					<%i++; %>
				<% }%>
			</tbody>
				
				
			</table>
					
		
		</div>
	</div>
</div>

	<script>
window.onload = function(){
	 $('.sub').each(function() { //loop through each checkbox
		 var id = this.id;
		 var data = id.split('~');
		document.getElementById(id).innerHTML = '<input class="form-control" type="text" id="n'+id+'" name="n'+data[0]+'">';  
		
		
		var mid="n"+id;
		document.getElementById(mid).value = data[1];
		
     });
}
</script>										
				


		
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right hidden-print" style="padding:0px;">
		<s:reset cssClass="btn btn-primary" />		
			<input type="submit"  class="btn btn-primary" value="Save" onclick="saveTemplateDetails();"/>
			<!-- <a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a> -->
			<!-- <input type="button"  class="btn btn-primary" value="Print" onclick="print();"/> -->					

<!--         <button type="button"  class="btn btn-primary"  value="Save" onclick="savePreviewTemplate('templateTable')">Save</button>
 --> 		
		</div>
		
		
		
</s:form> 
<br>

<!-- Modal -->
<s:form action="updatecombineformimgListAssessmentForm" id="editimage_form" theme="simple"> 
 <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData1"/>
  <s:hidden name="actionType" id ="actionType1"/>
  <s:hidden name="id" id="id1"> </s:hidden>
   <s:hidden name="formtype" id="formtype"/>
  
     <s:hidden name="clientName" id="clientname11"/>
    <s:hidden id = "diaryUser1"  name = "diaryUser"></s:hidden>
    <s:hidden id = "condition1"  name = "condition"></s:hidden>
  
<div class="modal fade" id="editimageCanvas" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Image</h4>
				</div>
	     <div class="modal-body" style="height: 600px;  overflow-y: scroll;">
	      	<button type="button" class="btn btn-primary" onclick="saveEditImageTemplate();">Update Image</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
	      		<%@ include file="/minipaint/editor.jsp"%>
	      </div>
	      <div class="modal-footer">
	       <button type="button" class="btn btn-primary" onclick="saveEditImageTemplate();">Update Image</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
   
  </div>
</div>	
</s:form>
