<%@page import="com.apm.AssesmentForms.web.action.Template"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<script type="text/javascript" src="assesmentForms/js/createNewAssesmentForm.js"></script>
<%@page import="java.util.ArrayList"%>
<script type="text/javascript">
$(document).ready(function(){
	
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
   // var canvas = document.getElementById('Background');
  //  var context = canvas.getContext('2d');
 //   context.drawImage(imageObj, 0, 0);
    
    var canvas1 = document.getElementById('imgTemplate');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
    };
    
    
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

function editImageCanvas(){
	
	<%String imagedata1 = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata1);%>
	var dataURL = '<%=imagedata1%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('Background');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);
   
    };
	
	 $( '#imageCanvas' ).modal( "show" );
	
}


var i = 0;

function saveTemplateDetails(){
	var chk = 0;
	
	var clientName = document.getElementById('clientnameAssessment').value;	
	var userName = document.getElementById('diaryUserAssessment').value;	
	var conditionName = document.getElementById('conditionAssessment').value;	
	
	 document.getElementById('clientnameAssessmentError').innerHTML="";
	 document.getElementById('diaryUserAssessmentError').innerHTML="";
	 document.getElementById('conditionAssessmentError').innerHTML="";
	 
	 if(clientName == "0"){		
			var cname = document.createElement("label");
			cname.innerHTML = "Please Select Client name";
		    document.getElementById('clientnameAssessmentError').appendChild(cname);
		    chk=1;
		}
	 if(userName == "0"){	
			 var una = document.createElement("label");
			 una.innerHTML = "Select Practitioner Name!";
    		 document.getElementById('diaryUserAssessmentError').appendChild(una);
    		 chk=1;
	 }
	 if(conditionName == "0"){	
			 var condition = document.createElement("label");
			 condition.innerHTML = "Select Condition Name!";
			 document.getElementById('conditionAssessmentError').appendChild(condition);
		 	 chk=1;
 		}
	
	/*  validatePromoJSON = {
            "validatorData": [                    
                  
                    { "Element": "#clientnameAssessment", "FunctionName": "dropDown" } ,
                    { "Element": "#diaryUserAssessment", "FunctionName": "dropDown" } ,
                    { "Element": "#conditionAssessment", "FunctionName": "dropDown" } 
                    
            ]
        };
	
	
	 if(iterateThroughtElemsForValidations(validatePromoJSON)) */
	 if(chk==1)
	    {
	       return false;
	    } 
	 else{
	 	/* var Pic;
		 for(var i in LAYERS){
		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		// alert(i);
		} */
	 
		var Pic = document.getElementById('imgTemplate').toDataURL("image/png");
		//alert(Pic);
		 document.getElementById('imageData').value = Pic;
	  
		 document.getElementById('temp_form').submit();
	 
		 return true;
	 
	   }
	
}


function saveImageTemplate(){
	
	var Pic;
	 for(var i in LAYERS){
	  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
	// alert(i);
	}

	 document.getElementById('imageData1').value = Pic;

	 document.getElementById('image_form').submit();

	 
}

<%String assesmentTemplateName = (String)session.getAttribute("assesmentTemplateName");%>

</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4><%=assesmentTemplateName %></h4>

									</div>
								</div>
								<br>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="saveMrdTemplateAssesmentForms" id="temp_form" theme="simple"> 
<s:hidden action="oldpractid" name="oldpractid"/>
<s:hidden name="repeat" id="repeat"/>
<s:hidden name="imageData" id="imageData"/>
	<div class="col-lg-12 col-md-12">
		<div class="">
			<div class="">	
			<%-- <%String headerData = (String)session.getAttribute("headerData");
			if(headerData!=null){ %>			
				<div class="row">
				<div class="col-lg-12 col-md-12">
					<label ><%= headerData%></label>
				</div>				
			<%} %> --%>
			
				<div class="col-lg-12">
				<link href="common/css/printpreview.css" rel="stylesheet" />
		 		<%@ include file="/accounts/pages/letterhead.jsp" %>
		 		
		 		</div>
				<br><br>
			
			
				<div class="row col-lg-12">
					<div class="col-lg-6 col-md-6">
						<h3><s:property value="templateName"/></h3>
					</div>
				</div>	
			
			
			<div class="row" id="addcanvasdiv">
				<div class="col-lg-12 col-md-12">					
					<div class="col-lg-7" style="height: 450px; width: 700px;">
						<canvas id="imgTemplate" height="450" width="700"></canvas>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print">
						<h3><a href="#" class="text-warning" onclick="editImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
					</div>
				</div>
			</div>
		
			<br>
			
					<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 6px 0px 0px 0px;margin-bottom: 10px;background-color: #f5f5f5;">
				<div class="col-lg-4 col-md-4 marlftright">
					<label><span class="red">*</span>Practitioner</label>
						<!--<s:if test="%{#userList != 'null'}" >
							<s:select cssClass="form-control showToolTip chosen" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
						</s:if>
						-->
							<s:select cssClass="form-control showToolTip chosen" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
						<s:hidden id="ipdid" name="ipdid"></s:hidden>
						<s:hidden id="clientId" name="clientId"></s:hidden>
						<s:hidden id="mrdid" name="mrdid"></s:hidden>
					<label  id = "diaryUserAssessmentError" class="text-danger"></label>	
				</div>
				<div class="col-lg-4 col-md-4 marlftright">
					<label><span class="red">*</span>Patient</label>		
					<!--<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Patient"  onchange="setConditionAssessment(this.value)"  />
					-->
					<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Patient"  onchange="setConditionAssessment(this.value)"  />
					<label  id = "clientnameAssessmentError" class="text-danger"></label>	
				</div> 
				<div class="col-lg-4 col-md-4 marlftright">	
						<label><span class="red">*</span>Speciality</label>
						<s:select onchange="setAssesmentCondition(this.value)" cssClass="form-control showToolTip chosen" id="conditionAssessment" name="condition" list="conditionList" listKey="id" listValue="treatmentType" theme="simple"   />
					<label  id = "conditionAssessmentError" class="text-danger"></label>	
				</div> 
			</div> 
			
			<%int i =1,p=0; %>					
			<%ArrayList<Assessment>  fieldNameList = (ArrayList<Assessment>)session.getAttribute("fieldNameList");
			if(fieldNameList!=null){ 
				for(Assessment assessment:fieldNameList){ 
				int type = Integer.parseInt(assessment.getType());
				String fieldid = DateTimeUtils.removeAllSpecialChar(assessment.getFiledname());
				fieldid = fieldid.replace(" ", "");
				if(type == 1){%>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">							
						<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
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
							<label id="lable<%=i%>"><%=assessment.getFiledname() %></label>
   			 					<%String sizeValue = assessment.getSizeValue();
   			 					if(sizeValue.equals("0")){
   			 						sizeValue = "";
   			 					}
   			 					if(type == 3){
   			 					%>
   			 					<%String fldname2 = assessment.getFiledname().replaceAll(" ", "_"); %>
   			 					<textarea onblur="saveAssesmentDataTosession(this.id,this.value)" rows="4" cols="40" name="<%=assessment.getFiledname() %>" id="<%=fieldid %>" class="form-control showToolTip filedname" data-toggle = "tooltip" ></textarea>
   			 		<%}
   			 			else if(type == 2){%>
   			 				<%String fldname1 = assessment.getFiledname().replaceAll(" ", "_"); %>
   			 			   	<input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)" name="<%=assessment.getFiledname() %>" id="<%=fieldid %>" class="form-control showToolTip filedname" data-toggle = "tooltip" value="" maxlength="<%=sizeValue%>">
   			 			
   			 			<%} else if(type == 5){%>
   			 			
   			 				<div class="row">
   			 				<div class="col-lg-4 col-md-4">		
   			 			   	<select onchange="saveAssesmentDataTosession(this.id,this.value)" name="<%=assessment.getFiledname() %>" id="<%=fieldid %>" class="form-control showToolTip filedname" data-toggle = "tooltip">
   			 			   		<option value="0">Select</option>
   			 			   		<option value="Yes">Yes</option>
   			 			   		<option value="No">No</option>
   			 			   	</select>
   			 			   	</div>
   			 			   		<%String fldnames = assessment.getFiledname().replaceAll(" ", "_"); %>
   			 			   	<div class="col-lg-8 col-md-8">		
   			 			   		<%-- <input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note_<%=assessment.getFiledname() %>" rows="4" cols="40"/> --%> 
   			 			   		<textarea onblur="saveAssesmentDataTosession(this.id,this.value)" placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note<%=fieldid %>" rows="4" cols="40"></textarea>
   			 			   	</div>
   			 			   	</div>
   			 			  
   			 			
   			 			<%}else if(type == 6){ %>
   			 				<div class="row">
	   			 				<div class="col-lg-4 col-md-4">		
		   			 			   	<select  onchange="saveAssesmentDataTosession(this.id,this.value)" name="<%=assessment.getFiledname() %>" id="<%=fieldid %>" class="form-control showToolTip filedname" data-toggle = "tooltip">
		   			 			   	<%ArrayList<Master>list = (ArrayList<Master>)session.getAttribute("customdataList");%>
		   			 			   		<%for(Master master : list) {%>
		   			 			   			<option value="<%=master.getId() %>"><%=master.getName() %></option>
		   			 			   		<% }%>
		   			 			   	</select>
	   			 			   	</div>
	   			 			   	
	   			 			   	<%String fldname = assessment.getFiledname().replaceAll(" ", "_"); %>
	   			 			   	<div class="col-lg-8 col-md-8">		
	   			 			   		<%-- <input type="text" onblur="saveAssesmentDataTosession(this.id,this.value)"  placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note_<%=assessment.getFiledname() %>" rows="4" cols="40"/> --%> 
	   			 			   		<textarea onblur="saveAssesmentDataTosession(this.id,this.value)"  placeholder='Enter Note' name="note_<%=assessment.getFiledname() %>" class="form-control showToolTip filedname" id="note<%=fieldid %>" rows="4" cols="40"></textarea>
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
                                        
                                       	<%int lenght = Integer.parseInt(assessment.getRptvalue()); for(int r=0;r<lenght;r++){ %>
                                       		<td><input class="form-control" name="<%=assessment.getFiledname()+r%>" id="rpeat<%=p %>"></td>
                                       	<%p++;} %>
                                       
                                    </tr>
                                   </tbody>
                                  </table>
				<%} %>
			
			 <%}%>
			 		 
			 
			 	
			
			<%}%>
			
       			 
       			<s:hidden name="templateId" id="templateId"> </s:hidden>
			<%} %>
			 <br/>			
		
		</div>
	</div>
</div>


		<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-print">
			<input type="button"  class="btn btn-primary plbtnsave" value="Save" onclick="saveTemplateDetails();" />					
			<!-- <a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a> -->
<!--         <button type="button"  class="btn btn-primary"  value="Save" onclick="savePreviewTemplate('templateTable')">Save</button>
 --> 		<s:reset cssClass="btn btn-primary plbtnreset" />		
		</div>
		</div>
		
	</s:form> 										
</div>
</div>
</div>
</div>
</div>







<br>

<!-- Modal -->
 <s:form action="saveImageTemplateAssesmentForms" id="image_form" theme="simple"> 
  <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData1"/>
  	
    <s:hidden id = "diaryUser1"  name = "diaryUser"></s:hidden>
    <s:hidden id = "condition1"  name = "condition"></s:hidden>
    <s:hidden name="clientName" id="clientname11"/>

 <div class="modal fade" id="imageCanvas" tabindex="-1"
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
     <button type="button" class="btn btn-primary" onclick="saveImageTemplate();">Save Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      		<%@ include file="/minipaint/editor.jsp"%>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="saveImageTemplate();">Save Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
   
  </div>
</div>	
</s:form>
