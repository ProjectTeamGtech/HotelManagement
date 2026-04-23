<%@page import="com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO"%>
<%@page import="com.apm.Master.eu.bi.MasterDAO"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.Connection"%>
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

										<h4>Add Assessment Form</h4>

									</div>
								</div>
								
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="combinesaveAssesmentForms" id="temp_form" theme="simple"> 
<s:hidden action="oldpractid" name="oldpractid"/>
<s:hidden name="repeat" id="repeat"/>
<s:hidden name="imageData" id="imageData"/>
	<div class="col-lg-12 col-md-12" style="padding: 0px;">
			<%-- <%String headerData = (String)session.getAttribute("headerData");
			if(headerData!=null){ %>			
				<div class="row">
				<div class="col-lg-12 col-md-12">
					<label ><%= headerData%></label>
				</div>				
			<%} %> --%>
			
				<div class="col-lg-12 hidden">
				<link href="common/css/printpreview.css" rel="stylesheet" />
		 		<%@ include file="/accounts/pages/letterhead.jsp" %>
		 		
		 		</div>
				
			<div class="row hidden" id="addcanvasdiv">
				<div class="col-lg-12 col-md-12">					
					<div class="col-lg-7" style="height: 450px; width: 700px;">
						<canvas id="imgTemplate" height="450" width="700"></canvas>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print">
						<h3><a href="#" class="text-warning" onclick="editImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
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
			
			<center><h3 style="text-transform: uppercase;color: brown;"><s:property value="templateName"/></h3></center>
			<%String assmntformtype = (String)session.getAttribute("assmntformtype"); %>
			<table class="table table-striped gridexample table-responsive" id = "allusertable">
				<col/>
				<% for (Master m : topNameList){%>
					<col/>
				<%} %>
			<thead>
				<tr>
					<th></th>
					
					<% 
					for (Master m : topNameList){%>
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
						
							<td style="display: <%=dsplay %>;"  valign="top" id="<%=countslot %>" >
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


		<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right hidden-print">
			<input type="button"  class="btn btn-primary" value="Save" onclick="saveTemplateDetails();" style="float: right;"/>					
			<!-- <a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a> -->
<!--         <button type="button"  class="btn btn-primary"  value="Save" onclick="savePreviewTemplate('templateTable')">Save</button>
 --> 		<s:reset cssClass="btn btn-primary" style="margin-right: 10px;" />		
		</div>
		</div>
		
</s:form> 

											

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
										</div>
									</div>
								</div>
							</div>
						</div>







<br>

<!-- Modal -->
 <s:form action="savecombineimgAssesmentForms" id="image_form" theme="simple"> 
  <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData1"/>
  	
    <s:hidden id = "diaryUser1"  name = "diaryUser"></s:hidden>
    <s:hidden id = "condition1"  name = "condition"></s:hidden>
    <s:hidden name="clientName" id="clientname11"/>
    <s:hidden name="formtype" id="formtype"/>

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


 <script>
   /*!
   * based on formNavigation https://github.com/omichelsen/FormNavigation
   */
   (function ($) {
     $.fn.formNavigation = function () {
       $(this).each(function () {
         // Events triggered on keyup
         $(this).find('input').on('keyup', function(e) {
           switch (e.which) {
             // arrow right
             case 39:
               $(this).closest('td').next().find('input').focus();
               break;

             // arrow left
             case 37:
               $(this).closest('td').prev().find('input').focus();
               break;

             // arrow bottom
             case 40:
               $(this).closest('tr').next().children().eq($(this).closest('td').index()).find('input').focus();
               break;

             // arrow top
             case 38:
               $(this).closest('tr').prev().children().eq($(this).closest('td').index()).find('input').focus();
               break;

             // enter
             case 13:
               if ($(this).closest('td').next().find('input').length>0) {
                 // when there is another column on right
                 $(this).closest('td').next().find('input').focus();
               } else {
                 // when last column reached
                 $(this).closest('tr').next().children().eq(1).find('input').focus();
               }
               break;
           }
         });
         
         // Events triggered on keydown (repeatable when holding the key)
         $(this).find('input').on('keydown', function(e) {
           // Vertical navigation using tab as OP wanted
           if (e.which === 9 && !e.shiftKey) {
             // navigate forward
             if ($(this).closest('tr').next().find('input').length>0) {
               // when there is another row below
               e.preventDefault();
               $(this).closest('tr').next().children().eq($(this).closest('td').index()).find('input').focus();
             } else if ($(this).closest('tbody').find('tr:first').children().eq($(this).closest('td').index()+1).find('input').length>0) {
               // when last row reached
               e.preventDefault();
               $(this).closest('tbody').find('tr:first').children().eq($(this).closest('td').index()+1).find('input').focus();
             }
           } else if (e.which === 9 && e.shiftKey) {
             // navigate backward
             if ($(this).closest('tr').prev().find('input').length>0) {
               // when there is another row above
               e.preventDefault();
               $(this).closest('tr').prev().children().eq($(this).closest('td').index()).find('input').focus();
             } else if ($(this).closest('tbody').find('tr:last').children().eq($(this).closest('td').index()-1).find('input').length>0) {
               // when first row reached
               e.preventDefault();
               $(this).closest('tbody').find('tr:last').children().eq($(this).closest('td').index()-1).find('input').focus();
             }
           }
         });
       });
     };
   })(jQuery);

   // usage
   $('.gridexample').formNavigation();
   </script>
