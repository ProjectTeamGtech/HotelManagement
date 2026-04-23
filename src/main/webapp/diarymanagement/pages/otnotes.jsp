<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="org.jopendocument.dom.template.statements.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib uri="/struts-tags" prefix="s"%>
    <script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
 <link href="_assets/newtheme/css/main.css" rel="stylesheet" />
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<style>
.savebigbtn {
    width: 13%;
    height: 61px !important;
    font-size: 20px;
    background-color: #339966 !important;
    margin-bottom: 15px;
}

.topback2 {
        background-color: #6699CC;
    padding: 10px 10px 0px 10px !important;
    color:#fff;
}
.borderset{
	border: 1px solid #6699CC;
    padding: 0px;
}
.padbotom{
	padding-bottom: 10px;
	    color: #339966;
}
.setem{
	margin-left: -70px;
}
.micimg{
	    float: right;
    width: 9%;
    margin-top: 10px;
}
.newmicimg{
    float: right;
    width: 30%;
    margin-top: 10px;
}
.setticolors {
    border-bottom: 4px double #ddd;
    font-size: 16px;
    color: firebrick;
}
/* canvas {
    position: relative !important;} */
</style>

<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<% ArrayList<String> tempstringlist = (ArrayList<String>) session.getAttribute("tempstringlist");%>

<script>
<%ArrayList<Master>multiotImgList = (ArrayList<Master>)session.getAttribute("multiotImgList"); %>
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

function savevideoImage(){
	var data =	document.getElementById('tempcount').value;
	for (var j = 0; j <=data; j++) {
		document.getElementById("ottemppp"+j).value=  nicEditors.findEditor("otnotes"+j).getContent();
	}
	document.getElementById('vidroimage_form').submit();
}

function capturevideoimg(index){
	document.getElementById('savemoreidvideo').value = index;
	 var txt = nicEditors.findEditor("otnotes"+index).getContent();
 	 document.getElementById('otnotes23').value = txt;
	 $( '#videoimageCanvas' ).modal( "show" );
	 retake();
	
}

</script>

<script>
<%int i=0; %>

function editImageCanvas1(val){
	<%Master m =  new Master();String imagedata2="";%>
	var dataURL = '';
	MENU.do_menu(['file_new']);
	<%if(multiotImgList.size()>0){%>
	
		<%if(multiotImgList.size()==1){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
	    // load image from data url
	
	
		<%if(multiotImgList.size()==2){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==3){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==4){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==5){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==6){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		
		if(val==5){
			<% m = multiotImgList.get(5);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==7){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		
		if(val==5){
			<% m = multiotImgList.get(5);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==6){
			<% m = multiotImgList.get(6);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==8){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		
		if(val==5){
			<% m = multiotImgList.get(5);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==6){
			<% m = multiotImgList.get(6);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==7){
			<% m = multiotImgList.get(7);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==9){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		
		if(val==5){
			<% m = multiotImgList.get(5);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==6){
			<% m = multiotImgList.get(6);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==7){
			<% m = multiotImgList.get(7);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==8){
			<% m = multiotImgList.get(8);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
		
		<%if(multiotImgList.size()==10){%>
		if(val==0){
			<% m = multiotImgList.get(0);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==1){
			<% m = multiotImgList.get(1);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==2){
			<% m = multiotImgList.get(2);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==3){
			<% m = multiotImgList.get(3);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==4){
			<% m = multiotImgList.get(4);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		
		if(val==5){
			<% m = multiotImgList.get(5);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==6){
			<% m = multiotImgList.get(6);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==7){
			<% m = multiotImgList.get(7);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==8){
			<% m = multiotImgList.get(8);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		if(val==9){
			<% m = multiotImgList.get(9);imagedata2 = m.getName();%>
			 dataURL = '<%=imagedata2%>';
		}
		<%}%>
	
	<%}%>
	
	  // load image from data url
	var imageObj1 = new Image();
	imageObj1.src = dataURL;
	imageObj1.onload = function() {
    var canvas = document.getElementById('Background');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj1, 0, 0);
    };

	
	document.getElementById('savemoreid').value=val;
    $( '#imageCanvas' ).modal( "show" );
}


</script>




<body>



<s:form action="saveotnotesNotAvailableSlot" theme="simple">
<s:hidden name="id" id="id"/>
	<div class="col-lg-1 col-md-1"></div>
	
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padingleftrightzeroi">
	
	<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
		  		<div class="row" style="height: 135px;">
					<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
							<link href="common/css/printpreview.css" rel="stylesheet" />
							
							<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
				</div>
				</div> --%>
				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor topotnotsbg ">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 
					 
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
								<b class="setticolors">OPERATION NOTES</b>
						</div>
						 
						 
					</div>
					</div>
				</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							
								  <div class="form-group" style="margin-bottom: 3px;">
									<b for="inputEmail3" class="control-label">Age / Gender</b>
								</div>
							
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
						
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Address</b>
						</div>
						
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Ward / Bed</b>
						</div>
						
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>:  <s:property value="abrivationid"/></span>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="client"/></span>
							</div>
							
								<div class="form-group" style="margin-bottom: 3px;">
									<span>: <s:property value="agegender"/></span>
								</div>
							
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <span>: <s:property value="mobile"/></span>
							</div>
						
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="address"/></span> 
						</div>
						
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="wardbed"/></span> 
						</div>
						
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Adm. ID</b>  
							</div>
							
						
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							
								  <b for="inputEmail3" class="control-label">D.O.A</b>
								  
						</div>
							
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								
								<span>: <%if(loginInfo.getIpd_abbr_access()==1){ %>
								<s:property value="newipdabbr"/>
								<%}else{%>
								<s:property value="ipdid"/>
								<%} %>
								</span>   
							</div>    
							
							
							
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							
								  <span>: <s:property value="admitdate"/></span>
						</div>
							
						</div>
						</div>
					</div>
				
				
		
		<div class="">
				
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;padding-top: 20px;">
									
								
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Procedure</label>
									    <s:textfield name="procedure" id="operation" cssClass="form-control foemse"/>
									    
									  </div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Anaesthesia</label>
									    <s:textfield name="anesthesia" id="anesthesia" cssClass="form-control foemse"/>
									   
									  </div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Date</label>
									    <s:textfield name="commencing" id="commencing" cssClass="form-control foemse"/>
									    
									  </div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1" class="visible-print hidden-lg hidden-md">Time</label>
									    <label for="exampleInputEmail1" class="hidden-print">Time of Incision</label>
									   <%--  <s:textfield name="timeofincision" id="timeofincision" cssClass="form-control foemse"/> --%>
									     <s:select list="visitingtimeList" cssClass="form-control" name="timeofincision" id="timeofincision"/>
									  </div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Surgeon</label>
									    <s:textfield name="surgeon" id="surgeon" cssClass="form-control foemse"/>
									  </div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Asst.Surgeon</label>
									    <s:textfield name="asistantdoclist" id="surgeon" cssClass="form-control foemse"/>
									  </div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padingleftrightsm" style="padding:0px;">
									
									<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6 padingleftrightsm">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Anaesthetist Intime</label>
									     <s:textfield name="ansintime" id="ansintime" cssClass="form-control foemse"/>
									  </div>
									</div>
									
									<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
									 <%--  <div class="form-group">
									   <label for="exampleInputEmail1">Views</label>
									  <p style="    display: inline-flex;">
									  		<s:if test="viewtype=='Individual'">
									  			<label class="checkbox checkbox-custom-alt"><input type="radio" onclick="changeviewtype(this.value)" name="viewtype" value="Individual" checked="checked" id="type_0"><i></i> Single</label>&nbsp;&nbsp;&nbsp;&nbsp;
										  		<label class="checkbox checkbox-custom-alt"><input type="radio" onclick="changeviewtype(this.value)" name="viewtype" value="Company" id="type_1"><i></i> Multi</label>   
									  		</s:if>
									  		<s:else>
									  			<!-- <label class="checkbox checkbox-custom-alt"><input type="radio" onclick="changeviewtype(this.value)" name="viewtype"  value="Individual" id="type_0"><i></i> Single</label>&nbsp;&nbsp;&nbsp;&nbsp; -->
										  		<label class="checkbox checkbox-custom-alt"><input type="radio" onclick="changeviewtype(this.value)" name="viewtype" checked="checked" value="Company" id="type_1"><i></i> Multi</label>   
									  		</s:else>
										  	
										  </p>
									   
									  </div> --%>
									</div>
									
								</div>
								<div id="Individual_box">
								<s:if test="viewtype=='Individual'">
								<%i=0; %>
									<% for (String string : tempstringlist) {%>
									<%String otnotes = (String)session.getAttribute("otnotes"+i+"");%>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  padingleftrightzeroi">
									<div class="col-lg-12 col-xs-12 col-md-12 padingleftrightzeroi" style="padding: 5px 0px 0px 0px;background-color: #efefef59;">
										<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 ">
										<div class="form-group">
									   		<a href="#" type="button" class="btn btn-sm btn-primary" style="margin-left: 5px;" onclick="changetemplateList(<%=i%>)"><i class="fa fa-align-justify" aria-hidden="true"></i></a>
									   </div>
									  <div class="form-group" id="templistdiv<%=i%>">
									  <label for="exampleInputEmail1">Operation Notes </label>
										<select name="template<%=i%>" id="template<%=i%>" onchange="getNewOTtemplate(this.value,<%=i%>)" class="form-control showToolTip chosen-select">
											<option value="0">Select Template</option>
											<s:iterator value="otherTemplateList">
												<option value='<s:property value="id"/>'><s:property value="name"/></option>
											</s:iterator>
										</select>
									  </div>
									  <div class="form-group">
									 		<a href="#" onclick="opencPopup('OtherTemplate?selectedid=15')"><i style="color: white;" title="Add Template" class="fa fa-plus-square fa-2x aadpres"></i></a>
						        	  </div>
									</div>
									<div class="col-lg-2 col-md-2">
									<a href="#" class="btn btn-primary" onclick="editImageCanvas1(<%=i%>);" title="Edit Image" style="margin-top: 22px;">Upload Image </a>
									</div>
									<div class="col-lg-2 col-md-2">
									<a href="#" class="btn btn-primary" onclick="capturevideoimg(<%=i%>);" title="Edit Image" style="margin-top: 22px;">Capture Video Image</a>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									  <div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting<%=i%>(this)" title="Microphone" id="changer"></img>
									  </div>
									</div>
									</div>
									<div class="">
									    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;">
											<textarea style="height: 250px;" value="<%=otnotes%>"  id="otnotes<%=i%>" name="otnotes<%=i%>" cols="8" rows="10" title="Enter Other Template Text" placeholder="Enter Other Template Text"  data-toggle="tooltip" tabindex="119" class="showToolTip form-control" ></textarea>
									    </div>
									    <div class="col-lg-12 col-md-12" style="padding:0px;margin-top: 10px;" id="addcanvasdiv<%=i%>">	
											<canvas id="imgTemplate<%=i%>" height="450" width="700"></canvas>
										</div>
									  </div>
								</div>
								<%} %>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print" style="margin-top: 10px;">
									<div class="form-group">
											<s:hidden name="tempaddmorecount"></s:hidden>
									   		<input type="submit" class="btn btn-primary savebigbtn" value="Save">
									   		<a href="#" onclick="addsubmit()" class="btn btn-primary savebigbtn" style="line-height: 50px;">Add More</a>
									</div>
								</div>
								</s:if>
								<s:else>
								
								</s:else>
								</div>


				<div id="Company_box">
					<s:if test="viewtype=='Individual'">
					</s:if>
					<s:else>
						<%
							i = 0;
						%>
						<%
							for (String string : tempstringlist) {
						%>
						<%
							String otnotes = (String) session.getAttribute("otnotes" + i + "");
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padingleftrightzeroi">
							<div class="col-lg-12 col-xs-12 col-md-12 padingleftrightzeroi"
								style="padding: 5px 0px 0px 0px; background-color: #efefef59;">
								
								
								<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 padingleftrightzeroi">
									<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6 ">
										<a href="#" class="btn btn-primary"
										onclick="editImageCanvas1(<%=i%>);" title="Edit Image"
										style="margin-top: 22px;">Upload Image</a>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
										<a href="#" class="btn btn-primary"
										onclick="capturevideoimg(<%=i%>);" title="Edit Image"
										style="margin-top: 22px;">Capture Video Image</a>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
									
									<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6 padingleftrightzeroi">
											<div class="form-group" id="templistdiv<%=i%>">
										<%-- <label for="exampleInputEmail1">Operation Notes</label>
										<s:select onchange="getNewOTtemplate(this.value,<%=i%>)" name="template<%=i%>" id="template<%=i%>" list="otherTemplateList"
										listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"
										headerKey="0" headerValue="Select Template"/> --%>
										<label for="exampleInputEmail1">Operation Notes <a href="#" onclick="setOTemplateBySpeciality(<%=i%>,<s:property value="surgeonid"/>)"><i class="fa fa-refresh"></i></a></label> <select
											name="template<%=i%>" id="template<%=i%>"
											onchange="getNewOTtemplate(this.value,<%=i%>)"
											class="form-control showToolTip chosen-select">
											<option value="0">Select Template</option>
											<s:iterator value="otherTemplateList">
												<option value='<s:property value="id"/>'><s:property
														value="name" /></option>
											</s:iterator>
										</select>
									</div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
										<div class="form-group">
											<a href="#" type="button" class="btn btn-sm btn-primary barotnotesi"
												style="margin-top: 22px;"
												onclick="changetemplateList(<%=i%>)"><i
												class="fa fa-align-justify" aria-hidden="true"></i></a>
										</div>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
										<div class="form-group" style="margin-top: 22px;">
									 		<a href="#"  onclick="opencPopup('OtherTemplate?selectedid=36')"><i  title="Add Template" class="fa fa-plus-square fa-2x aadpres"></i></a>
						        	 	</div>
						        	 </div>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
										<div class="form-group">
										<img src="cicon/mic_off.png" class="img-responsive newmicimg" 
											onclick="startConverting<%=i%>(this)" title="Microphone"
											id="changer<%=i%>"></img>
									</div>
									</div>
								</div>
								
								<%-- <div class="col-lg-2 col-md-2">
									<a href="#" class="btn btn-primary" onclick="editImageCanvas1();" title="Edit Image" style="margin-top: 22px;">Upload Image</a>
									<a href="#" class="btn btn-primary"
										onclick="editImageCanvas1(<%=i%>);" title="Edit Image"
										style="margin-top: 22px;">Upload Image</a>
								</div>
								<div class="col-lg-1 col-md-1">
									<a href="#" class="btn btn-primary"
										onclick="capturevideoimg(<%=i%>);" title="Edit Image"
										style="margin-top: 22px;">Capture Video Image</a>
								</div>
								
								<div class="col-lg-1 col-md-1">
								
								</div>
								
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<div class="form-group" id="templistdiv<%=i%>">
										<label for="exampleInputEmail1">Operation Notes</label>
										<s:select onchange="getNewOTtemplate(this.value,<%=i%>)" name="template<%=i%>" id="template<%=i%>" list="otherTemplateList"
										listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"
										headerKey="0" headerValue="Select Template"/>
										<label for="exampleInputEmail1">Operation Notes</label> <select
											name="template<%=i%>" id="template<%=i%>"
											onchange="getNewOTtemplate(this.value,<%=i%>)"
											class="form-control showToolTip chosen-select">
											<option value="0">Select Template</option>
											<s:iterator value="otherTemplateList">
												<option value='<s:property value="id"/>'><s:property
														value="name" /></option>
											</s:iterator>
										</select>
									</div>
								</div>
								<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
									<div class="form-group">
											<a href="#" type="button" class="btn btn-sm btn-primary"
												style="margin-top: 22px;"
												onclick="changetemplateList(<%=i%>)"><i
												class="fa fa-align-justify" aria-hidden="true"></i></a>
										</div>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
									<div class="form-group">
										<img src="cicon/mic_off.png" class="img-responsive micimg"
											onclick="startConverting<%=i%>(this)" title="Microphone"
											id="changer<%=i%>"></img>
									</div>
								</div> --%>
							</div>
							
							<div class="">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 padingleftrightzeroi"
									style="margin-top: 10px; padding: 0px;">
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 padingleftrightzeroim"
										id="addcanvasdiv<%=i%>">
										<canvas class="canvasviewsave" id="imgTemplate<%=i%>"
											style="width:100%;"></canvas>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12"
										style="padding: 0px;">
										<%-- <s:textarea style="height: 250px;" rows="10" cols="8" id="otnotes<%=i%>" name="otnotes<%=i%>"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></s:textarea> --%>
										<textarea style="height: 250px;" id="otnotes<%=i%>"
											name="otnotes<%=i%>" cols="8" rows="10"
											title="Enter Other Template Text"
											placeholder="Enter Other Template Text" data-toggle="tooltip"
											tabindex="119" class="showToolTip form-control"><%=otnotes%></textarea>
									</div>

								</div>

							</div>
						</div>
						<%
							i++;
						%>
						<%
							}
						%>
						<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 5px 0px 0px 0px;background-color: #efefef59;">
										<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Operation Notes</label>
										<s:select onchange="getOTtemplate(this.value)" name="template" id="template" list="otherTemplateList"
										listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"
										headerKey="0" headerValue="Select Template"/>
									  </div>
									</div>
									<div class="col-lg-2 col-md-2">
									<a href="#" class="btn btn-primary" onclick="editImageCanvas1();" title="Edit Image" style="margin-top: 22px;">Upload Image</a>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									  <div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting2(this)" title="Microphone" id="changer1"></img>
									  </div>
									</div>
									</div>
									<div class="">
									    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;"><!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
									    
									    <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" id="addcanvasdiv1">
									    		<canvas id="imgTemplate1" height="450" width="700"></canvas>
									    	</div>
									    	<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" style="padding: 0px;">
									    		<s:textarea style="height: 250px;" rows="10" cols="8" id="otnotes1" name="otnotes1"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></s:textarea>
									    	</div>
									    	
									    </div>
									    
									  </div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 5px 0px 0px 0px;background-color: #efefef59;">
										<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									  <div class="form-group">
									    <label for="exampleInputEmail1">Operation Notes</label>
										<s:select onchange="getOTtemplate(this.value)" name="template" id="template" list="otherTemplateList"
										listKey="id" listValue="name" cssClass="form-control showToolTip chosen-select"
										headerKey="0" headerValue="Select Template"/>
									  </div>
									</div>
									<div class="col-lg-2 col-md-2">
									<a href="#" class="btn btn-primary" onclick="editImageCanvas1();" title="Edit Image" style="margin-top: 22px;">Upload Image</a>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									  <div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting3(this)" title="Microphone" id="changer2"></img>
									  </div>
									</div>
									</div>
									<div class="">
									    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;"><!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
									    	<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" id="addcanvasdiv2">
									    		<canvas id="imgTemplate1" height="450" width="700"></canvas>
									    	</div>
									    	
									    	<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" style="padding: 0px;">
									    		<s:textarea style="height: 250px;" rows="10" cols="8" id="otnotes2" name="otnotes2"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></s:textarea>
									    	</div>
									    	
									    </div>
									   
									  </div>
								</div> --%>

						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print"
							style="margin-top: 10px;">
							<div class="form-group">
								<input type="submit" class="btn btn-primary savebigbtn"
									value="Save">
								<s:hidden name="tempaddmorecount" id="tempcount"></s:hidden>
								<a href="#" onclick="addsubmit()"
									class="btn btn-primary savebigbtn" style="line-height: 50px;">Add
									More</a>
							</div>
						</div>
					</s:else>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print" style="margin-top: 10px;">
									<%-- <div class="form-group">
										<div id="Individual_box">
											<s:hidden name="tempaddmorecount"></s:hidden>
									   		<input type="submit" class="btn btn-primary savebigbtn" value="Save">
									   		
										</div>
										<div id="Company_box">
											<s:hidden name="tempaddmorecount"></s:hidden>
									   		<input type="submit" class="btn btn-primary savebigbtn" value="Save">
									   			<a href="#" onclick="addsubmit()" class="btn btn-primary savebigbtn" style="line-height: 50px;">Add More</a>
										</div>
										
									  </div> --%>
								</div>
		
		</div>
	
	</div>
	<div class="col-lg-1 col-md-1"></div>



								
								
								
								
								


</s:form>
<s:form id="otaddd" action="otaddmoreNotAvailableSlot">

<%i=0; %>
<% for (String string : tempstringlist) {%>
	<input type="hidden" id="ottemp<%=i%>" name="ottempnotes<%=i%>"> 
<%i++; %>
<%} %>
<s:hidden name="tempaddmorecount"></s:hidden>
<s:hidden name="viewtype"></s:hidden>
<s:hidden name="actiontype" value="1"></s:hidden>
 </s:form>

<s:form id="otchangeview" action="otaddmoreNotAvailableSlot">
<s:hidden name="tempaddmorecount"></s:hidden>
<s:hidden name="viewtype" id="viewtyype"></s:hidden>
<s:hidden name="actiontype" value="2"></s:hidden>
 </s:form>

<script>
<%i=0; %>
bkLib.onDomLoaded(function() {
	 //new nicEditor().panelInstance('declarationNotes');
	 //new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	// $('.nicEdit-main').height('480px');
	 <% for (String string : tempstringlist) {%>
	 //new nicEditor().panelInstance('declarationNotes');
	 new nicEditor({maxHeight : 250}).panelInstance('otnotes<%=i%>');
	 
	// $('.nicEdit-main').height('480px');
	 <%i++;%>
	 <%}%>
	 $('.nicEdit-panelContain').parent().width('98%');
	 $('.nicEdit-panelContain').parent().next().width('98%');
	 $('.nicEdit-main').width('100%');
	 
	
});


 
	
 

 //$(document).ready(function(){
	
	 
	 window.onload = function(){
	 <%i=0; %>
	 var dataURL = '';
	
	 <%if(multiotImgList.size()>0){%>
	//canvas 0
	
	
		var imageObj = new Image();
		<%if(multiotImgList.size()==1){%>
			<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
		<%}%>
		
	   
	  
	    //canvas 1
	 	 var imageObj1 = new Image();
	 	<%if(multiotImgList.size()==2){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
	 	<%}%>
		
	 	
	 	//canvas 2
	 	 var imageObj2 = new Image();
	 	<%if(multiotImgList.size()==3){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 3
	 	 var imageObj3 = new Image();
	 	<%if(multiotImgList.size()==4){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 4
	 	 var imageObj4 = new Image();
	 	<%if(multiotImgList.size()==5){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 5
	 	 var imageObj5 = new Image();
	 	<%if(multiotImgList.size()==6){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(5);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj5.src = dataURL;
		    imageObj5.onload = function() {
		 	    fill_canvas(imageObj5,5);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 6
	 	 var imageObj6 = new Image();
	 	<%if(multiotImgList.size()==7){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(5);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj5.src = dataURL;
		    imageObj5.onload = function() {
		 	    fill_canvas(imageObj5,5);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(6);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj6.src = dataURL;
		    imageObj6.onload = function() {
		 	    fill_canvas(imageObj6,6);
		 	  
		 	 };
	 	<%}%>
		
	 	//canvas 7
	 	 var imageObj7 = new Image();
	 	<%if(multiotImgList.size()==8){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(5);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj5.src = dataURL;
		    imageObj5.onload = function() {
		 	    fill_canvas(imageObj5,5);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(6);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj6.src = dataURL;
		    imageObj6.onload = function() {
		 	    fill_canvas(imageObj6,6);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(7);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj7.src = dataURL;
		    imageObj7.onload = function() {
		 	    fill_canvas(imageObj7,7);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 7
	 	 var imageObj8 = new Image();
	 	<%if(multiotImgList.size()==9){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(5);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj5.src = dataURL;
		    imageObj5.onload = function() {
		 	    fill_canvas(imageObj5,5);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(6);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj6.src = dataURL;
		    imageObj6.onload = function() {
		 	    fill_canvas(imageObj6,6);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(7);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj7.src = dataURL;
		    imageObj7.onload = function() {
		 	    fill_canvas(imageObj7,7);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(8);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj8.src = dataURL;
		    imageObj8.onload = function() {
		 	    fill_canvas(imageObj8,8);
		 	  
		 	 };
	 	<%}%>
	 	
	 	//canvas 9
	 	 var imageObj9 = new Image();
	 	<%if(multiotImgList.size()==10){%>
		 	<%Master master = multiotImgList.get(0);%>;
			dataURL = '<%=master.getName()%>'; 
			 imageObj.src = dataURL;
		    imageObj.onload = function() {
		 	    fill_canvas(imageObj,0);
		 	  
		 	 };
			 	
		 	
		 	<% master = multiotImgList.get(1);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj1.src = dataURL;
		    imageObj1.onload = function() {
		 	    fill_canvas(imageObj1,1);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(2);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj2.src = dataURL;
		    imageObj2.onload = function() {
		 	    fill_canvas(imageObj2,2);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(3);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj3.src = dataURL;
		    imageObj3.onload = function() {
		 	    fill_canvas(imageObj3,3);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(4);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj4.src = dataURL;
		    imageObj4.onload = function() {
		 	    fill_canvas(imageObj4,4);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(5);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj5.src = dataURL;
		    imageObj5.onload = function() {
		 	    fill_canvas(imageObj5,5);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(6);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj6.src = dataURL;
		    imageObj6.onload = function() {
		 	    fill_canvas(imageObj6,6);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(7);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj7.src = dataURL;
		    imageObj7.onload = function() {
		 	    fill_canvas(imageObj7,7);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(8);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj8.src = dataURL;
		    imageObj8.onload = function() {
		 	    fill_canvas(imageObj8,8);
		 	  
		 	 };
		 	 
		 	<% master = multiotImgList.get(9);%>;
		 	dataURL = '<%=master.getName()%>'; 
		 	imageObj9.src = dataURL;
		    imageObj9.onload = function() {
		 	    fill_canvas(imageObj9,9);
		 	  
		 	 };
	 	<%}%>
	 	
		  function fill_canvas(img,t) {
			  
			  <%if(multiotImgList.size()==1){%>
				  
					  var canvas1 = document.getElementById('imgTemplate0');
				  	    var context1 = canvas1.getContext('2d');
				  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
				  
			  <%}%>
			
			  <%if(multiotImgList.size()==2){%>
				  if(t==0){
					  var canvas1 = document.getElementById('imgTemplate0');
				  	    var context1 = canvas1.getContext('2d');
				  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
				  }
				  
				  if(t==1){
					  var canvas1 = document.getElementById('imgTemplate1');
				  	    var context1 = canvas1.getContext('2d');
				  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
				  }
			  
			  <%}%>
			
			  <%if(multiotImgList.size()==3){%>
			  if(t==0){
				  var canvas1 = document.getElementById('imgTemplate0');
			  	    var context1 = canvas1.getContext('2d');
			  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
			  }
			  
			  if(t==1){
				  var canvas1 = document.getElementById('imgTemplate1');
			  	    var context1 = canvas1.getContext('2d');
			  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
			  }
			  
			  if(t==2){
				  var canvas1 = document.getElementById('imgTemplate2');
			  	    var context1 = canvas1.getContext('2d');
			  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
			  }
		  
		  <%}%>
		  
		  <%if(multiotImgList.size()==4){%>
		  if(t==0){
			  var canvas1 = document.getElementById('imgTemplate0');
		  	    var context1 = canvas1.getContext('2d');
		  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
		  }
		  
		  if(t==1){
			  var canvas1 = document.getElementById('imgTemplate1');
		  	    var context1 = canvas1.getContext('2d');
		  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
		  }
		  
		  if(t==2){
			  var canvas1 = document.getElementById('imgTemplate2');
		  	    var context1 = canvas1.getContext('2d');
		  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
		  }
		  if(t==3){
			  var canvas1 = document.getElementById('imgTemplate3');
		  	    var context1 = canvas1.getContext('2d');
		  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
		  }
	  
	  <%}%>
	  
	  <%if(multiotImgList.size()==5){%>
	  if(t==0){
		  var canvas1 = document.getElementById('imgTemplate0');
	  	    var context1 = canvas1.getContext('2d');
	  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
	  }
	  
	  if(t==1){
		  var canvas1 = document.getElementById('imgTemplate1');
	  	    var context1 = canvas1.getContext('2d');
	  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
	  }
	  
	  if(t==2){
		  var canvas1 = document.getElementById('imgTemplate2');
	  	    var context1 = canvas1.getContext('2d');
	  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
	  }
	  if(t==3){
		  var canvas1 = document.getElementById('imgTemplate3');
	  	    var context1 = canvas1.getContext('2d');
	  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
	  }
	  if(t==4){
		  var canvas1 = document.getElementById('imgTemplate4');
	  	    var context1 = canvas1.getContext('2d');
	  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
	  }
  
  <%}%>
  
  <%if(multiotImgList.size()==6){%>
  if(t==0){
	  var canvas1 = document.getElementById('imgTemplate0');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }
  
  if(t==1){
	  var canvas1 = document.getElementById('imgTemplate1');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }
  
  if(t==2){
	  var canvas1 = document.getElementById('imgTemplate2');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }
  if(t==3){
	  var canvas1 = document.getElementById('imgTemplate3');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }
  if(t==4){
	  var canvas1 = document.getElementById('imgTemplate4');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }
  
  if(t==5){
	  var canvas1 = document.getElementById('imgTemplate5');
  	    var context1 = canvas1.getContext('2d');
  	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
  }

<%}%>

<%if(multiotImgList.size()==7){%>
if(t==0){
	  var canvas1 = document.getElementById('imgTemplate0');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==1){
	  var canvas1 = document.getElementById('imgTemplate1');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==2){
	  var canvas1 = document.getElementById('imgTemplate2');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==3){
	  var canvas1 = document.getElementById('imgTemplate3');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==4){
	  var canvas1 = document.getElementById('imgTemplate4');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==5){
	  var canvas1 = document.getElementById('imgTemplate5');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==6){
	  var canvas1 = document.getElementById('imgTemplate6');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

<%}%>

<%if(multiotImgList.size()==8){%>
if(t==0){
	  var canvas1 = document.getElementById('imgTemplate0');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==1){
	  var canvas1 = document.getElementById('imgTemplate1');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==2){
	  var canvas1 = document.getElementById('imgTemplate2');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==3){
	  var canvas1 = document.getElementById('imgTemplate3');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==4){
	  var canvas1 = document.getElementById('imgTemplate4');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==5){
	  var canvas1 = document.getElementById('imgTemplate5');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==6){
	  var canvas1 = document.getElementById('imgTemplate6');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==7){
	  var canvas1 = document.getElementById('imgTemplate7');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

<%}%>


<%if(multiotImgList.size()==9){%>
if(t==0){
	  var canvas1 = document.getElementById('imgTemplate0');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==1){
	  var canvas1 = document.getElementById('imgTemplate1');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==2){
	  var canvas1 = document.getElementById('imgTemplate2');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==3){
	  var canvas1 = document.getElementById('imgTemplate3');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==4){
	  var canvas1 = document.getElementById('imgTemplate4');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==5){
	  var canvas1 = document.getElementById('imgTemplate5');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==6){
	  var canvas1 = document.getElementById('imgTemplate6');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==7){
	  var canvas1 = document.getElementById('imgTemplate7');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==8){
	  var canvas1 = document.getElementById('imgTemplate8');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

<%}%>

<%if(multiotImgList.size()==10){%>
if(t==0){
	  var canvas1 = document.getElementById('imgTemplate0');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==1){
	  var canvas1 = document.getElementById('imgTemplate1');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==2){
	  var canvas1 = document.getElementById('imgTemplate2');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==3){
	  var canvas1 = document.getElementById('imgTemplate3');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==4){
	  var canvas1 = document.getElementById('imgTemplate4');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==5){
	  var canvas1 = document.getElementById('imgTemplate5');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}
if(t==6){
	  var canvas1 = document.getElementById('imgTemplate6');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==7){
	  var canvas1 = document.getElementById('imgTemplate7');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==8){
	  var canvas1 = document.getElementById('imgTemplate8');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

if(t==9){
	  var canvas1 = document.getElementById('imgTemplate9');
	    var context1 = canvas1.getContext('2d');
	    context1.drawImage(img, 0, 0);      // DRAW THE IMAGE TO THE CANVAS.
}

<%}%>
			
			  
        }
	  
	<%}%>
	    	 
 }
 
 



 
 
 var i = 0;

 function saveImageTemplate(){
 	var Pic;
 	 for(var i in LAYERS){
 		  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
 		// alert(i);
 	}
 	 var savemoreid1 =  document.getElementById('savemoreid').value;
 	 document.getElementById('imageData').value = Pic;
 	 var txt = nicEditors.findEditor("otnotes"+savemoreid1).getContent();
 	 document.getElementById('otnotes22').value = txt;
 	 
 	var data =	document.getElementById('tempcount').value;
	for (var j = 0; j <=data; j++) {
		document.getElementById("ottemporary"+j).value=  nicEditors.findEditor("otnotes"+j).getContent();
	}
 	 
 	document.getElementById('image_form').submit();
 }

</script>
<input type="hidden" name="action_attributes" id="action_attributes"/>

<!-- Modal -->
 <s:form action="otimgNotAvailableSlot" id="image_form" theme="simple"> 
  <s:hidden name="templateId" id="templateId1"> </s:hidden>
  <s:hidden name="imageData" id="imageData"/>
   <s:hidden name="newotnotes" id="otnotes22"/>
  <s:hidden name="savemoreid" id="savemoreid"></s:hidden>
  	<s:hidden name="viewtype"></s:hidden>
  	<s:hidden name="tempaddmorecount" id="tempaddmorecount"></s:hidden>
  	
  	<%i=0; %>
	<% for (String string : tempstringlist) {%>
		<input type="hidden" id="ottemporary<%=i%>" name="ottempnotes<%=i%>"> 
	<%i++; %>
	<%} %>
  	
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


<!-- capture video image -->
<!-- Modal -->
 <s:form action="otimgNotAvailableSlot" id="vidroimage_form" theme="simple"> 
 <s:hidden name="imageData" id="profileimg"/>
  <s:hidden name="savemoreid" id="savemoreidvideo"/>
  <s:hidden name="newotnotes" id="otnotes23"/>
  <s:hidden name="tempaddmorecount" />
  <s:hidden name="viewtype"></s:hidden>
  
  <%i=0; %>
	<% for (String string : tempstringlist) {%>
		<input type="hidden" id="ottemppp<%=i%>" name="ottempnotes<%=i%>"> 
	<%i++; %>
	<%} %>
  
 <div class="modal fade" id="videoimageCanvas" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header bg-primary">
				
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Image</h4>
				</div>
     <div class="modal-body" style="height: 500px;  overflow-y: scroll;">
     
     <a href="#" onclick="capture()" class="btn btn-primary">Capture</a>
	<a href="#" class="btn btn-primary" onclick="retake()">Update Photo</a>
     <button type="button" class="btn btn-primary" onclick="savevideoImage();">Save Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      		<div class="col-lg-12 col-md-12">
	      		<div id="vdivid" style="display: none;">
	      			<video id="videoID" autoplay style=";"></video>
	      		</div>
				<div id="cdivid" style="display: none;"><canvas id="canvasID" style="border: 1px solid black;width: 100%;"></canvas></div>
			</div>										
      		<br>
			<!-- <div class="col-lg-12 col-md-12">
				<a href="#" onclick="capture()" class="btn btn-primary">Capture</a>
				<a href="#" class="btn btn-primary" onclick="retake()">Update Photo</a>
			</div> -->
      		
      </div>
      <div class="modal-footer">
      <!--  <button type="button" class="btn btn-primary" onclick="savevideoImage();">Save Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
      </div>
    </div>
   
  </div>
</div>	
</s:form>

<div id="selectothertemlate" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.32941176470588235);">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Select Specialization</h4>
      </div>
      <div class="modal-body">
      			<input type="hidden" id="tempsrno">
  				  <s:select list="specializationTemplateList" onchange="setTemplateBySpeciality(this.value)" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="Select Specilization" ></s:select>			
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
 </div>		

	<script type="text/javascript">

	
											var video = document.getElementById('videoID');
											var canvas = document.getElementById('canvasID');
											var context = canvas.getContext('2d');
									
											window.URL = window.URL || window.webkitURL;
											navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia	|| 
									                                 navigator.mozGetUserMedia || navigator.msGetUserMedia;
									
											navigator.getUserMedia({
												video : true
												//audio : true
											}, function(stream) {
												video.src = window.URL.createObjectURL(stream);
											}, function(e) { console.log('Something wrong has happened:', e); });
									
											
											function capture() 
											{
												context.drawImage(video, 0, 0, canvas.width, canvas.height);
												document.getElementById('cdivid').style.display = '';
												document.getElementById('vdivid').style.display = 'none';
												
												var imageData =  canvas.toDataURL();
												document.getElementById('profileimg').value = imageData;
											};
											
											function retake(){
												document.getElementById('cdivid').style.display = 'none';
												document.getElementById('vdivid').style.display = '';
												document.getElementById('imgdivid').style.display = 'none';
												
												
											}
									
											
											function send()
									        {
												var imageData =  canvas.toDataURL();
												var xmlhttp = new XMLHttpRequest();
												xmlhttp.open("POST", "/ww/receiver", true);
												xmlhttp.send(imageData);
									        };
									        
									
										</script>


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
<script type="text/javascript">

var r = document.getElementById('result');

</script>

<script>

function startConverting(element) {
	   var abc=element.src.split('/');
	   var right = "cicon/mic_off.png";
	   var left = "cicon/mic.png";
	   element.src = element.bln ? right : left;
	   element.bln = !element.bln;
	   //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConverting();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	   // localStorage.setItem("xx",textvalue);
	   location.reload();
	   }
}


<%i=0; %>

<% for (String string : tempstringlist) {%>
function startConverting<%=i%>(element) {

   var abc=element.src.split('/');
   
     var right = "cicon/mic_off.png";
         var left = "cicon/mic.png";
         element.src = element.bln ? right : left;
         element.bln = !element.bln;
         
       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
   if(abc[5]=="mic_off.png")
   {
    startConvertingnow<%=i%>();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   //location.reload();
	   savevideoImage();
   }
 }
        <%i++;%>
<%}%>
</script>
<script>
<%i=0; %>

<% for (String string : tempstringlist) {%>
function startConvertingnow<%=i%>(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor("otnotes"+<%=i%>).getContent() + vtxt;
		nicEditors.findEditor("otnotes"+<%=i%>).setContent(vtxt);
		
		
		
	};

}
<%i++;%>
<%}%>
</script>

<%-- <script>
	$(document).ready(function(){
	  $("input[name$='type']").click(function(){
	  var value = $(this).val();
	  if(value=='Individual') {
	    $("#Individual_box").show();
	     $("#Company_box").hide();
	  }
	  else if(value=='Company') {
	   $("#Company_box").show();
	    $("#Individual_box").hide();
	   }
	  });
	  $("#Individual_box").show();
	  $("#Company_box").hide();
	});
</script>	 --%>	
</body>
</html>
