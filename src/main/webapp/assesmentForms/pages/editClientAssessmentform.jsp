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
    
    
    
    <%ArrayList<Assessment>  fieldNameLists = (ArrayList<Assessment>)session.getAttribute("fieldNameList");%>
	<%for(Assessment assessment : fieldNameLists){
		int types = Integer.parseInt(assessment.getType());
			
	%>
	
	
	<%boolean checkRepeat = (Boolean)session.getAttribute("isRepeat");if(checkRepeat==false){%>
	<%if(types==5){
		String temp[] = assessment.getFiledValue().split(":"); 
		String fieldVlaue = temp[0];
	%>
	
		 <%String fldnames = DateTimeUtils.removeAllSpecialChar(assessment.getFiledname());
		 fldnames = fldnames.replace(" ", "");
		 %>;
		document.getElementById('<%=fldnames %>').value = '<%=temp[0]%>';
	
	<%}%> 
	

	<%if(types==6){
		String tempd[] = assessment.getFiledValue().split(":"); 
		String fieldVlaue = tempd[0];
	%>
	
	 <%String fldnamer = DateTimeUtils.removeAllSpecialChar(assessment.getFiledname());
	 fldnamer = fldnamer.replace(" ", "");
	 %>;
		document.getElementById('<%=fldnamer %>').value = '<%=tempd[0]%>';
	
	<%}%>
	

<%}%>

	
	<%}%>
		
    
   
	<%
	 HashMap<String, String>map1 = (HashMap<String, String>)session.getAttribute("sessionassement");
	%>
	<%if(session.getAttribute("sessionassement")!=null){%>
		<%for ( Map.Entry<String, String> entry : map1.entrySet()) {
			String strkey = DateTimeUtils.removeAllSpecialChar(entry.getKey());
			strkey = strkey.replace(" ", "");
		%>
		
		var key = '<%=strkey%>';
		var val = <%=entry.getValue()%>;
		document.getElementById(key).value = val;
	<%}%>
	
	
		
	<%}%>
	
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



<s:form action="updateTemplateEmr" id="temp_form" theme="simple"> 
<s:hidden name="repeat" id="repeat"/>
<s:hidden name="imageData" id="imageData"/>
	<div class="col-lg-12 col-md-12 col-xs-12 widthcan" style="padding: 0px;">
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp" %>
				
				</div>
		<br><br>
			
		
	
			
			<div class="row">
				<div class="col-lg-6 col-md-6 assesformhead">
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
			
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print" style="margin-left: 5px;">
					<h3><a href="#" class="text-warning" onclick="editUpdateImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div>
			</div>	
		</div>
			
				<br/>
				
				
				
		
			
			
			
			<div class="row rownew">
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Practitioner</label><label><span class="text-danger">*</span></label>
					<s:if test="%{#userList != 'null'}" >
						<s:select cssClass="form-control showToolTip chosen hidden-print" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
					</s:if>
				</div>
				<label  id = "diaryUserAssessmentError" class="text-danger"></label>
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Patient</label><label><span class="text-danger">*</span></label>
					<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Patient"  onchange="setConditionAssessment(this.value)"  />
					
				</div>
				<label  id = "clientnameAssessmentError" class="text-danger"></label>	
			</div>
			<div class="col-lg-4 col-md-4 col-xs-4">
				<div class="form-group">
					<label>Speciality</label><label><span class="text-danger">*</span></label>
					<s:select onchange="setAssesmentCondition(this.value)" cssClass="form-control showToolTip chosen" id="conditionAssessment" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select Speciality"    />
					
				</div>
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
			<%int i =1; %>					
			<%ArrayList<Assessment>  fieldNameList = (ArrayList<Assessment>)session.getAttribute("fieldNameList");
			if(fieldNameList!=null){ 
				for(Assessment assessment:fieldNameList){ 
				int type = Integer.parseInt(assessment.getType());
				String fieldid = DateTimeUtils.removeAllSpecialChar(assessment.getFiledname());
				fieldid = fieldid.replace(" ", "");
				if(type == 1){%>
				<div class="row">
					<div class="col-lg-12 col-md-12">						
						<div class="col-lg-2 col-md-2">	
							
								<label><h4><b><u><%=assessment.getFiledname() %></u></b></h4></label>
							
						</div>
					</div>
				</div>
							
				<%}
				else{ boolean isRepeat = (Boolean)session.getAttribute("isRepeat"); if(isRepeat==false){%>
				
				<%if(template.getLayout().equals("0")){ %>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					  <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
					  <% }else if(template.getLayout().equals("1")){%>	
					  	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" >
					  <%} else if(template.getLayout().equals("2")){%>
					  	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" >
					  <% }%>
				<%if (!assessment.getFiledname().equals("id")){ %>
					<label id="lable<%=i%>"><%=assessment.getFiledname() %></label>
				<% }%>
			
					<%String sizeValue = assessment.getSizeValue();  
   			 		if(sizeValue.equals("0")){
   			 			sizeValue = "";
   			 		}
					 
   			 		if(type == 3){
   			 		%>
   			 				<textarea onblur="saveAssesmentDataTosession(this.id,this.value)" rows="4" cols="40" name="<%=assessment.getFiledname() %>"  id="<%=assessment.getFiledname()%>" class="form-control showToolTip filedname" data-toggle = "tooltip" ><%=assessment.getFiledValue() %></textarea>
   			 		<%}
   			 			else if(type == 2){%>
   			 			   	<input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)" name="<%=assessment.getFiledname() %>" id="<%=fieldid%>" class="form-control showToolTip filedname" data-toggle = "tooltip" value="<%=assessment.getFiledValue() %>" maxlength="<%=sizeValue%>">
   			 			
   			 			<%}
   			 			
   			 			else if(type == 4){%>
   			 			   	<input style="display: none;" type="text" name="<%=assessment.getFiledname() %>" id="<%=fieldid%>" class="form-control showToolTip filedname" data-toggle = "tooltip" value="" maxlength="<%=sizeValue%>" disabled="disabled" readonly="readonly" >
   			 			
   			 			<%} else if(type == 5){ 
   			 				String temp[] = assessment.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
   			 				if(temp.length>1){
   			 					note = temp[1];
   			 				}
   			 				
   			 				String fldname = assessment.getFiledname().replaceAll(" ", "_");
   			 				%>
   			 				
   			 				
   			 				<div class="row rownew">
   			 					
   			 				<div class="col-lg-4 col-md-4">		
   			 			   	<select onchange="saveAssesmentDataTosession(this.id,this.value)"  name="<%=assessment.getFiledname() %>" id="<%=fieldid%>" class="form-control showToolTip filedname" data-toggle = "tooltip">
   			 			   		<option value="0">Select Yes/No</option>
   			 			   		<option value="Yes">Yes</option>
   			 			   		<option value="No">No</option>
   			 			   	</select>
   			 			   	</div>
   			 			   	<div class="col-lg-8 col-md-8">		
   			 			   		<%-- <input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note_<%=assessment.getFiledname() %>" value="<%=note %>"/> --%> 
   			 			   		<textarea onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note<%=fieldid %>" rows="4" cols="40"><%=note %></textarea>
   			 			   	</div>
   			 			   	</div>
   			 			  
   			 			
   			 			<%}else if(type == 6){ 
   			 				String temp[] = assessment.getFiledValue().split(":"); 
   			 				String fieldVlaue = temp[0];
   			 				String note = "";
			 				if(temp.length>1){
			 					note = temp[1];
			 				}
   			 			String fldname = assessment.getFiledname().replaceAll(" ", "_");
   			 				%>
   			 				<div class="row rownew">
   			 						
	   			 				<div class="col-lg-4 col-md-4">		
		   			 			   	<select onchange="saveAssesmentDataTosession(this.id,this.value)" name="<%=assessment.getFiledname() %>" id="<%=fieldid%>" class="form-control showToolTip filedname" data-toggle = "tooltip">
		   			 			   		 	<%ArrayList<Master>list = (ArrayList<Master>)session.getAttribute("customdataList");%>
		   			 			   			<%for(Master master : list) {%>
		   			 			   				<option value="<%=master.getId() %>"><%=master.getName() %></option>
		   			 			   			<% }%>
		   			 			   	</select>
	   			 			   	</div>
	   			 			   	<div class="col-lg-8 col-md-8">		
	   			 			   		<%-- <input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note_<%=assessment.getFiledname()%>" value="<%=note %>"/> --%> 
	   			 			   		<textarea onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note<%=fieldid%>" rows="4" cols="40" ><%=note %></textarea>
	   			 			   	</div>
   			 			   	</div>
   			 			   	
   			 			
   			 			<%} %>
   			 		<%if(template.getLayout().equals("0")){ %>
						  	</div>
   			 			   	</div>
					  <% }else if(template.getLayout().equals("1")){%>	
					  	</div>
					  <%} else if(template.getLayout().equals("2")){%>
					  	</div>
					  <% }%>
   			 	
   			 	
   			 	   			 		 	
   			 	 <%i++; 
				} else{%>
				<!--Repeat form code  -->
				<table class="table table-responsive">
                            
                            <tbody>
                                <tr>
                                    <th width="24%"><%=assessment.getFiledname() %></th>
                                    
                                    <%Connection  connection = Connection_provider.getconnection();
                                    AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
                                    
                                    String clientassesmentfieldid = (String)session.getAttribute("clientassesmentfieldid");
                                    
                                    ArrayList<Assessment>dataList = assessmentFormDAO.getRepeatFormData(template,assessment.getFiledname(),clientassesmentfieldid); %>
                                    
                                   	<%int lenght = Integer.parseInt(assessment.getRptvalue()); int r=0; for(Assessment data : dataList){ %>
                                   		<td><input name="<%=assessment.getFiledname()+r %>" id="rpeat<%=r %>" class="form-control" value="<%=data.getFiledValue() %>"></td>
                                   	<%r++;} %> 
                                   
                                </tr>
                               </tbody>
                              </table>
			<%} %>
		
		 <%}%>
		 		 
		 
		 	
		
		<%}%>
		
			
			
       			<s:hidden name="templateId" id="templateId"> </s:hidden>
       			<s:hidden name="id" id="id"> </s:hidden>
			<%} %>
			 <br/>			
		
		</div>
	</div>
</div>




		
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right hidden-print">
			<input type="submit"  class="btn btn-primary" value="Save" onclick="saveTemplateDetails();"/>
			<!-- <a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a> -->
			<!-- <input type="button"  class="btn btn-primary" value="Print" onclick="print();"/> -->					

<!--         <button type="button"  class="btn btn-primary"  value="Save" onclick="savePreviewTemplate('templateTable')">Save</button>
 --> 		<s:reset cssClass="btn btn-primary" />		
		</div>
		
		
		
</s:form> 
<br>

<!-- Modal -->
<s:form action="updateImageTemplateListAssessmentForm" id="editimage_form" theme="simple"> 
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
