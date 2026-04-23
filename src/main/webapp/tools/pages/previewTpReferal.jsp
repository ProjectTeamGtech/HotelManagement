<%@taglib uri="/struts-tags" prefix="s" %>


<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="TPReferal">TP Referal</a></li>
			<li class="active">Preview Email Templates</li>
		</ol>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">
`				<div class="row">
					<div class="col-lg-2 col-md-2">
						<h3><b>Header : </b></h3>
					</div>
					<div class="col-lg-9 col-md-9">
						<%=session.getAttribute("headerNote")%>
					</div>
				</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">
`				<div class="row">
					<div class="col-lg-2 col-md-2">
						<h3><b>Body 1 :</b></h3>
					</div>
					<div class="col-lg-9 col-md-9">
						<%=session.getAttribute("body1Note")%>
					</div>
				</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">
`				<div class="row">
					<div class="col-lg-2 col-md-2">
						<h3><b>Template Data :</b></h3>
					</div>
					<div class="col-lg-9 col-md-9">
						<%=session.getAttribute("templateData")%>
					</div>
				</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">
`				<div class="row">
					<div class="col-lg-2 col-md-2">
						<h3><b>Body 2 :</b></h3>
					</div>
					<div class="col-lg-9 col-md-9">
						<%=session.getAttribute("body2Note")%>
					</div>
				</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">
`				<div class="row">
					<div class="col-lg-2 col-md-2">
						<h3><b>Footer : </b></h3>
					</div>
					<div class="col-lg-9 col-md-9">
						<%=session.getAttribute("footerNote")%>
					</div>
				</div>
	</div>
</div>

<s:form action="TPReferal" id="tPReferal_form" theme="simple">
<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">		
			<a href="TPReferal" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>