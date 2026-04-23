<%@page import="com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO"%>
<%@page import="com.apm.AssesmentForms.eu.bi.AssessmentFormDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.apm.AssesmentForms.web.action.Template"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
<script type="text/javascript" src="assesmentForms/js//jquery.table2excel.js"></script>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript">
$(document).ready(function(){
	<%-- <%String columnStr = (String)session.getAttribute("columnStr");%>
	var columnstr1 = '<%=columnStr%>';
	var temp = columnstr1.split("#");
	//alert(temp);
	//alert(temp.length);
	
	var i = 0;
		<%ArrayList<String> list1 = (ArrayList<String>)session.getAttribute("assessmentFormsList");
     	for(String s : list1){%>
     	//alert(temp[i]);
     	var fieldId1 = temp[i];
     	//alert(fieldId1);
		document.getElementById(fieldId1).innerHTML = '<%=s%>';
		
		i++;
		<%}%>
      --%>
	
      
      <%Template template = (Template)session.getAttribute("assmnttemplatedetails");%>
  	<%if(template.isIncludeImage()){%>
  		document.getElementById('addcanvasdiv').style.display = 'none';
  	<%}%>
  	
	
	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('editupdateimgTemplate');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);
    
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
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-print">
		<ol class="breadcrumb">
			<li class="active">Print Assessment Form</li>
		</ol>
	</div>
</div>



<s:hidden name="imageData" id="imageData"/>
	<div class="col-lg-12 col-md-12 col-xs-12 widthcan">
		<div class="panel panel-primary">
			<div class="panel-body">
			

		<div class="row">
			<link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
		</div>
		<br><br>
	</div>
			
			
			<div class="row">
				<div class="col-lg-6 col-md-6 assesformhead" >
					<h3><s:property value="templateName"/> (<s:property value="clientFullName"/>)
						<s:if test="ipdid!=0">
							<s:property value="wardname"/> / <s:property value="bedname"/>
						</s:if>
					</h3>
				</div>
			</div>	
			
			
		<div class="row" id="addcanvasdiv">
			<div class="col-lg-12 col-md-12 col-xs-12">					
				<div class="col-lg-7 col-md-6 col-xs-12 canvwidth">
				`<canvas id="editupdateimgTemplate" height="450" width="700" ></canvas>
				</div>
			
				<!-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print" style="margin-left: 5px;">
					<h3><a href="#" class="text-warning" onclick="editUpdateImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div> -->
			</div>	
		</div>
			
				<br/>
				
				
				
			<%--  <div class="row">
				<div class="col-lg-2"></div>	
				<div class="col-lg-2 col-md-2">	
					<label>Client Name</label>
				</div>
				 <div class="col-lg-6 col-md-2">
					<s:textfield name="clientName" id="client" cssClass="form-control showToolTip" onclick="showPopUp2()" data-toggle="modal" data-target="#clientSearch" readonly="readonly" disabled="disabled"/>
					<s:hidden name="clientId" id="clientId"></s:hidden> 
				</div> 
			</div> --%>
			
			<s:if test="actionType != 2">
			
					<table class="table table-striped table-responsive" width="100%">
						<col width="20%"/>
						<col width="80%"/>
						<tr>
							<td><label>Practitioner</label></td>
							<td><s:property value="diaryUser"/></td>
				 		</tr>
			
			
			
					<tr>
						<td><label>Patient</label></td>
						<td><s:property value="clientName"/></td>
					</tr>
			
			
			
					<tr>
						<td><label>Speciality</label></td>
						<td><s:property value="condition"/></td>
					</tr>
				</table>
			</s:if> 
				
				
				<s:hidden name="diaryUser" id="diaryUser"/>
				<s:hidden name="clientName" id="diaryUser"/>
				<s:hidden name="condition" id="condition"/>
				<s:hidden name="actionType" id ="actionType"/>
				
			<table class="table table-striped table-responsive" width="100%">	
			 	<col width="20%"/>
				<col width="80%"/>
			<%int i =1; String bgcolor = "white"; %>					
			<%ArrayList<Assessment>  fieldNameList = (ArrayList<Assessment>)session.getAttribute("fieldNameList");
			if(fieldNameList!=null){ 
				for(Assessment assessment:fieldNameList){ 
				int type = Integer.parseInt(assessment.getType());
				if(type == 1){%>
				
				
					<tr>
						<td><h4><b><%=assessment.getFiledname() %></b></h4></td>
					</tr>
				
				<%}
				else{ boolean isRepeat = (Boolean)session.getAttribute("isRepeat"); if(isRepeat==false){%>
						<%if(i%2==0){bgcolor="#F5F0F0";}else{bgcolor="white";} %>
				
							<% if(!assessment.getFiledValue().equals("") && !assessment.getFiledValue().equals("0:")){%>
								<tr>	
									<%if (!assessment.getFiledname().equals("id")){ %>	 
   			 	 					<td><label id="lable<%=i%>"><%=assessment.getFiledname() %></label></td>
   			 				
   			 						<% }%>
   			 		
   			 		
   			 		
   			 		<%String sizeValue = assessment.getSizeValue();  
   			 		if(sizeValue.equals("0")){
   			 			sizeValue = "";
   			 		}
					 
   			 		if(type == 3){
   			 		%>
   			 				
   			 					<td id="<%=assessment.getFiledname()%>"><%=assessment.getFiledValue() %></td>
   			 				
   			 		<%}
   			 			else if(type == 2){%>
   			 			  
   			 					 <td><span id="<%=assessment.getFiledname()%>"> </span><%=assessment.getFiledValue() %></td>
   			 				 
   			 			
   			 			<%}
   			 			
   			 			else if(type == 4){%>
   			 			   
   			 			  <td id="<%=assessment.getFiledname()%>"></td>
   			 			
   			 			<%} else if(type==5){%>
   			 				<%String temp[] = assessment.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
			 				if(temp.length>1){
			 					note = temp[1];
			 				} %>
   			 				
   			 				<td><%=temp[0] %> &nbsp;(<b>Note : </b><%=note %>)</td>
   			 				
   			 			<% }else if(type==6){%>
   			 				<%String temp[] = assessment.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
			 				if(temp.length>1){
			 					note = temp[1];
			 				} %>
   			 				
   			 				<td><%=assessment.getCustomText() %> &nbsp;(<b>Note : </b><%=note %>)</td>
   			 				
   			 			<% }%>
				
				
				
				
		
   			 		
   			 	   			 		 	
   			 	 <%i++; 
				} %>
			
			</tr>
					
					
   			 		<% }else{%>
   			 		<%if(i%2==0){bgcolor="#F9F9F9";}else{bgcolor="white";} %>
   			 					<!--Repeat form code  -->
   			 					<table class="table table-responsive">
                            
                            <tbody>
				
                                <tr style="background-color: <%=bgcolor %>">
                                    <th width="24%"><label><%=assessment.getFiledname() %></label></th>
                                    
                                    <%Connection  connection = Connection_provider.getconnection();
                                    AssessmentFormDAO  assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
                                    String clientassesmentfieldid = (String)session.getAttribute("clientassesmentfieldid");
                                    ArrayList<Assessment>dataList = assessmentFormDAO.getRepeatFormData(template,assessment.getFiledname(),clientassesmentfieldid); %>
                                    
                                   	<%int lenght = Integer.parseInt(assessment.getRptvalue()); int r=0; for(Assessment data : dataList){ %>
                                   		<td><input name="<%=assessment.getFiledname()+r %>" id="rpeat<%=r %>" class="form-control" value="<%=data.getFiledValue() %>"></td>
                                   	<%r++;} %> 
                                   
                                    <%i++;%>
                                   
                                </tr>
                               </tbody>
                              </table>
                             
   			 		<%}%>
			
			<%}%>
			<%}%>
			
			</table>	
			
						<%-- <s:iterator value="fieldNameList">	
						<div class="row">
						<div class="col-lg-2"></div>	
						<div class="col-lg-2 col-md-2">						 
       			 	 		<label id="lable<%=i%>"><s:property value="filedname" /></label>
       			 		</div>
       			 		<div class="col-lg-6 col-md-2">
       			 	 		<input type="text" name="<s:property value='filedname'/>" id="filedname" class="form-control showToolTip filedname" data-toggle = "tooltip" value="" >
       			 		</div>	
       			 		</div>	
       			 		 <br/>	 	 --%>
       			 
       			<s:hidden name="templateId" id="templateId"> </s:hidden>
       			<s:hidden name="id" id="id"> </s:hidden>
			<%} %>
			 <br/>			
		
		</div>
	</div>
</div>




		<div class="row">
		<div class="col-lg-3 col-md-2 col-xs-4"></div>
		
		<div class="col-lg-6 col-md-8 col-sm-6 col-xs-4 hidden-print">
			<!-- <input type="submit"  class="btn btn-primary" value="Save" onclick="saveTemplateDetails();"/> -->
			<a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a>
			<button class="btn btn-primary" id="expbtn">Export</button>
		

		<script>
			$("#expbtn").click(function(){

				$(".table2excel").table2excel({
					exclude: ".noExl",
					name: "Excel Document Name",
					filename: "myFileName",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
			});
		</script>
		</div>
		<div class="col-lg-3 col-md-2 col-xs-4"></div>
		</div>
		

<br>

<!-- Modal -->
<div class="modal fade" id="editimageCanvas" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" style="width: 1000px; height: 900px;">
  <s:form action="updateImageTemplateListAssessmentForm" id="editimage_form" theme="simple"> 
  <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData1"/>
  <s:hidden name="actionType" id ="actionType1"/>
  <s:hidden name="id" id="id1"> </s:hidden>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Image Canvas</h4>
      </div>
      <div class="modal-body" >
      		<%@ include file="/minipaint/editor.jsp"%>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="saveEditImageTemplate();">Update Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
    </s:form>
  </div>
</div>	


	<table class="table2excel" style="display: none;">	
	<tr>
		<td colspan="6">
			<h3><s:property value="templateName"/> (<s:property value="clientFullName"/>)
						<s:if test="ipdid!=0">
							<s:property value="wardname"/> / <s:property value="bedname"/>
						</s:if>
					</h3>
		</td>
	</tr>
			 	<col width="20%"/>
				<col width="80%"/>
				<%int i22 =1; String bgcolor22 = "white"; %>				
			<%ArrayList<Assessment>  fieldNameList22 = (ArrayList<Assessment>)session.getAttribute("fieldNameList");
			if(fieldNameList22!=null){ 
				for(Assessment assessment22:fieldNameList22){ 
				int type = Integer.parseInt(assessment22.getType());
				if(type == 1){%>
				
				
					<tr>
						<td><h4><b><%=assessment22.getFiledname() %></b></h4></td>
					</tr>
				
				<%}
				else{ boolean isRepeat = (Boolean)session.getAttribute("isRepeat"); if(isRepeat==false){%>
						<%if(i22%2==0){bgcolor22="#F5F0F0";}else{bgcolor22="white";} %>
				
							<% if(!assessment22.getFiledValue().equals("") && !assessment22.getFiledValue().equals("0:")){%>
								<tr>	
									<%if (!assessment22.getFiledname().equals("id")){ %>	 
   			 	 					<td><label id="lable<%=i22%>"><%=assessment22.getFiledname() %></label></td>
   			 				
   			 						<% }%>
   			 		
   			 		
   			 		
   			 		<%String sizeValue = assessment22.getSizeValue();  
   			 		if(sizeValue.equals("0")){
   			 			sizeValue = "";
   			 		}
					 
   			 		if(type == 3){
   			 		%>
   			 				
   			 					<td id="<%=assessment22.getFiledname()%>"><%=assessment22.getFiledValue() %></td>
   			 				
   			 		<%}
   			 			else if(type == 2){%>
   			 			  
   			 					 <td><span id="<%=assessment22.getFiledname()%>"> </span><%=assessment22.getFiledValue() %></td>
   			 				 
   			 			
   			 			<%}
   			 			
   			 			else if(type == 4){%>
   			 			   
   			 			  <td id="<%=assessment22.getFiledname()%>"></td>
   			 			
   			 			<%} else if(type==5){%>
   			 				<%String temp[] = assessment22.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
			 				if(temp.length>1){
			 					note = temp[1];
			 				} %>
   			 				
   			 				<td><%=temp[0] %> &nbsp;(<b>Note : </b><%=note %>)</td>
   			 				
   			 			<% }else if(type==6){%>
   			 				<%String temp[] = assessment22.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
			 				if(temp.length>1){
			 					note = temp[1];
			 				} %>
   			 				
   			 				<td><%=assessment22.getCustomText() %> &nbsp;(<b>Note : </b><%=note %>)</td>
   			 				
   			 			<% }%>
				
				
				
				
		
   			 		
   			 	   			 		 	
   			 	 <%i22++; 
				} %>
			
			</tr>
					
					
   			 		<% }else{%>
   			 		<%if(i22%2==0){bgcolor22="#F9F9F9";}else{bgcolor22="white";} %>
   			 					<!--Repeat form code  -->
   			 					
                            
                            <tbody>
				
                                <tr style="background-color: <%=bgcolor22 %>">
                                    <td width="24%"><label><%=assessment22.getFiledname() %></label></td>
                                    
                                    <%Connection  connection = Connection_provider.getconnection();
                                    AssessmentFormDAO  assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
                                    String clientassesmentfieldid22 = (String)session.getAttribute("clientassesmentfieldid");
                                    ArrayList<Assessment>dataList22 = assessmentFormDAO.getRepeatFormData(template,assessment22.getFiledname(),clientassesmentfieldid22); %>
                                    
                                   	<%int lenght22 = Integer.parseInt(assessment22.getRptvalue()); int r22=0; for(Assessment data22 : dataList22){ %>
                                   		<!--<td><input name="<%=assessment22.getFiledname()+r22 %>" id="rpeat<%=r22 %>" class="form-control" value="<%=data22.getFiledValue() %>"></td>
                                   	-->
                                   	<td><%=data22.getFiledValue() %></td>
                                   	<%r22++;} %> 
                                   
                                    <%i22++;%>
                                  
                                </tr>
                                
                               </tbody>
                             
                            <%}%> 
   			 		
			
			<%}%>
			<%}%>
		<%}%>
			
			</table>	
