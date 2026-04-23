<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update book Chapter</h4>
								</div>
							</div>

<s:form action="updateMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="book_chapter_id" />
			    <label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="book_chapter_name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name"/>
						
					 <label>Select Book</label><label class="text-danger">*</label>
			    <s:select list="booklist" headerKey="0" headerValue="Seletc Book" id="book" name="id" listKey="id" listValue="name"  cssClass="form-control"></s:select>			   
				
					<label>Enter Link</label><label class="text-danger">*</label>
					<s:textfield id="link" name="book_chapter_link" 
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Link" placeholder="Enter link"/>
					
					<label>Enter Discription</label><label class="text-danger">*</label>
					<s:textfield id="discription" name="book_chapter_discription"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Discription" placeholder="Enter Discription"/>
					
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return saveshelf()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="Master" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
</body>
</html>