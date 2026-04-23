<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.InstantMessage.eu.entity.*"%>
<!-- Start of Java Script file import here -->
<script type="text/javascript" src="instantMessage/js/instantMessaging.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<script type="text/javascript">
	
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('650px');
	        	 $('.nicEdit-panelContain').parent().next().width('650px');
	        	 
	        	 $('.nicEdit-main').width('99%');
	        	 $('.nicEdit-main').height('800px');
	        	
	        	 
	        	// document.getElementById('templateName').disabled = 'disabled';
	        	 document.getElementById("user").innerHTML = 'disabled';
	      });
	        
	      //  document.getElementById('templateName').disabled = 'disabled';
</script>
<!-- End of Java Script File -->
<!-- Start of CCS file import here -->
<link href="instantMessage/css/instantMessaging.css" rel="stylesheet" type="text/css" />
<!-- end of CCS file import here -->




<!-- Start of BreadCrumb -->
<!-- <div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Instant Messaging(IM)</a></li>
			
		</ol>
	</div>
</div> -->
<!-- End of BreadCrumb -->
<div><!-- start Parent Div -->
<div class="col-lg-1 col-md-1"><!-- Left Div -->
<div class="row">
	
	<label>Instant Message</label>
	
</div>
<br>
<div class="row">
	<input type="button" value="Compose New" class="btn btn-primary showToolTip"  data-placement = "bottom" onclick="openComposePopup()"  >
</div>
<br>
<%-- <div class="row">
 <a href="getInboxInstantMsg"><b>Inbox(<s:property value="inboxTotal"/>)</b></a>
</div> --%>
<div class="row">
 <a href="#" onclick="showSentMail()"><b>Sent Mail(<s:property value="sentMailTotal"/>)</b></a>
</div>
<div class="row">
 <a href="#" onclick="showDraftMail()"><b>Draft(<s:property value="draftTotal"/>)</b></a>
</div>
<div class="row">
 <a href="#" onclick="showChats()"><b>Chats</b></a>
</div>

</div><!-- End of Left Div -->
<div class="col-lg-10 col-md-10" style="margin-left: 25px"><!-- Right Div -->
<!-- Start of  Search  -->
<%-- <div class="row">
	
	
	<s:form action="UserProfile" theme="simple">
		<div class="input-group">
			
				<s:textfield theme="simple" name="searchText" id="searchText" 
					placeholder="Enter text to Search" size="80" cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit" value="Go" class="btn btn-primary" />
				</span>
				
		
		</div>
	</s:form>
<hr>	
</div> --%>
<!-- End of Search  -->

<!-- <div class="row">
	<a href="" class="btn btn-primary showToolTip"  data-placement = "bottom" data-toggle = "tooltip"  title="Refresh"><i class="fa fa-refresh" ></i></a> 
	&nbsp;&nbsp;
	
	<a href="" class="btn btn-primary showToolTip"  data-placement = "bottom" data-toggle = "tooltip"  title="Delete"><i class="fa fa-trash-o"></i></a>
	
</div> -->
<br>
<div class="row">
<div class="table-responsive" id = "listTable">
		<table class="table table-striped table-hover table-condensed table-bordered">
			
			<tbody>
			<%ArrayList<InstantMsg>inboxList = (ArrayList<InstantMsg>)session.getAttribute("inboxList"); 
								int i = 0;
			%>
			<% for(InstantMsg instantMsg:inboxList ) {%>
				<%if(instantMsg.getStatus().equals("read")) 
				{%>
				<tr>
					<td onclick="showDetailsOfInboxMail(<%=instantMsg.getId()%>)"><%=instantMsg.getSenderEmailId().toString()%>  <%=instantMsg.getSubject().toString()%> - <%=instantMsg.getDate().toString()%> <%=instantMsg.getTime().toString()%></td>												
	
				</tr>
				<%}else{
				%>
				<tr>
					<td onclick="showDetailsOfInboxMail(<%=instantMsg.getId()%>)"><b><label class="bg-danger">New - </label> <%=instantMsg.getSenderEmailId().toString()%>  <%=instantMsg.getSubject().toString()%> - <%=instantMsg.getDate().toString()%> <%=instantMsg.getTime().toString()%></b></td>												
	
				</tr>
				<%}%>
				
			<% i++;%>
			<%} %>
							
			
</tbody>
		</table>

</div>
</div>
</div><!-- End of Right div -->
</div><!-- end Parent div -->

<!-- Modal -->
<div class="modal fade" id="composeEmail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
  <div class="modal-dialog" style="width: 700px;">
    <div class="modal-content">
    	<s:form action="uploadSendMailInstantMsg" enctype="multipart/form-data" method="post" theme="simple">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" onclick="saveToDraft()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">New Message</h4>
      </div>
      <div class="modal-body" style="height: 500px; overflow: scroll;">
      <s:hidden name = "actionStatus" id = "actionStatus"></s:hidden>
      
        <div class="row">
         <div class="col-lg-4">
				<label>Select User</label>
				<s:select id="userName" name="userName"  
				list="{'Select','Client','Practitioner','ThirdParty','GP'}" cssClass="form-control  showToolTip chosen" 
				value="userName" onchange="setName(this.value)"></s:select>
				<label  id = "unameError" class="text-danger"></label>	
		</div>
		<div class="col-lg-4">
		<label>User Name</label>
		<s:textfield name="user" id="user" readonly="true" cssClass="form-control" 
				     onclick="showPopUp2()" data-toggle="modal" data-target="#userSearch"/>
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="user"></s:hidden>
		</div>
		
		
        <div class="col-lg-4">
				<label>Select Template</label>
				<select id = "templateName" name = "templateName" onchange="showTemplateDetails(this.id)" class="form-control showToolTip chosen" data-toggle="tooltip">
						<option value="0">Select Template Name</option>
				</select>
				<%-- <s:select id="templateName" name="templateName" listKey="id"
				headerValue="Select" headerKey="Select" listValue="templateName" list="templateNameList" 
				value="templateName" cssClass="form-control" onchange="showTemplateDetails(this.id)"></s:select> --%>
				<label  id = "tnameError" class="text-danger"></label>	
		</div>
        
     
			<div class="col-lg-12">
				<label>To:</label>
				<s:textfield theme="simple" id="toEmailId"
					name="toEmailId"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Email" placeholder="Enter Email Id"/>
			</div>
			<%-- <br/>
			<div class="col-lg-12">
				<label>Cc:</label>
				<s:textfield theme="simple" id="ccEmailId"
					name="ccEmailId"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Cc" placeholder="Enter Cc"/>
			</div> --%>
			<br/>
			<div class="col-lg-12">
				<label>Subject:</label>
				<s:textfield theme="simple" id="subject"
					name="subject"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter subject" placeholder="Enter subject"/>
			</div>
			<br/>
			<div class="col-lg-12 ">
				<label>Body:</label>
				<s:textarea class="form-control showToolTip textarea"   data-toggle="tooltip" title="Write content" placeholder="Write content" name="body" id="emailBody" cols="40" rows="2" />
			</div>
			<br/>
			<div class="col-lg-12 col-md-12">
				<label>Attachments:</label>
				
 				 <div id = "FileUploadContainer">
      			  <!--FileUpload Controls will be added here -->
      			  <div id = "draftAttachments">
				
				</div>
    			</div>
    			
    			<br/> <br/>
 				  <input id="Button1" class="btn btn-default"  type="button" value="Attach Files" onclick = "AddFileUpload()" />
    			
   			</div>
   			
		</div>
		
      </div>
      <div class="modal-footer">
      	<s:submit cssClass="btn btn-primary"  value="Send" onclick="setActionSave()"/>
      	<s:submit cssClass="btn btn-primary"  value="Close" onclick= "setActionDraft()"/>
      </div>
      </s:form>
    </div>
  </div>
</div>

<div id="AutocompleteContainer">
<ul id="UlAutocomplete">
</ul>
</div>



<!-- Modal Client Search-->
<div class="modal fade" id="userSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">User Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/tools/pages/allTemplatePatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

