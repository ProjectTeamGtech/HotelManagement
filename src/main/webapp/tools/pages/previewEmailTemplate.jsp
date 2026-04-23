<%@taglib uri="/struts-tags" prefix="s" %>

<div class="row">
<div class="col-lg-12 col-md-12">
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10" style="padding-left:0px;padding-right:0px;">
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Preview Email Templates</h4>
									</div>
								</div>
			</div>
			<div class="col-lg-1 col-md-1">
				<s:form action="backEmailTemplate" id="emailTemplate_form" theme="simple">
				<div class="row" style="margin-top: 20px;position: absolute;">
						<div class="col-lg-3 col-md-2"></div>
						<div class="col-lg-6 col-md-8">		
							<a href="backEmailTemplate" class="btn btn-primary">Back</a>
						</div>
						<div class="col-lg-3 col-md-2"></div>
					</div>
				</s:form>
			</div>
			</div>
</div>
<div class="">
<div class="col-lg-12 col-md-12" style="padding-left:0px;padding-right:0px;">
<div class="col-lg-1 col-md-1"><b>Header <i class="fa fa-long-arrow-right" aria-hidden="true"></i></b></div>
<div class="col-lg-10 col-md-10" style="padding-left: 0px;padding-right: 0px;padding-top: 20px;padding-bottom: 20px;border: 1px solid #efefef;">
<div class="panel-body">
				<div class="row">
					<div class="">
						<%=session.getAttribute("headerNote")%>
					</div>
				</div>
				
	</div>
</div>
<div class="col-lg-1 col-md-1"></div>
</div>
</div>
<div class="">
<div class="col-lg-12 col-md-12" style="padding-left:0px;padding-right:0px;">
<div class="col-lg-1 col-md-1"><b>Body 1 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></b></div>
<div class="col-lg-10 col-md-10" style="background-color:#f5f5f5;padding-top: 20px;padding-bottom: 20px;text-align: justify;">
<div class="panel-body">
				<div class="">
					<div class="">
						<%=session.getAttribute("body1Note")%>
					</div>
				</div>
	</div>
</div>
<div class="col-lg-1 col-md-1"></div>
</div>
</div>
<div class="">
<div class="col-lg-12 col-md-12" style="padding-left:0px;padding-right:0px;">
<div class="col-lg-1 col-md-1"><b>Body 2 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></b></div>
<div class="col-lg-10 col-md-10" style="background-color: #eee;padding-top: 20px;padding-bottom: 20px;text-align: justify;">
<div class="panel-body">
				<div class="">
					<div class="">
						<%=session.getAttribute("body2Note")%>
					</div>
				</div>
	</div>
</div>
<div class="col-lg-1 col-md-1"></div>
</div>
</div>
<div class="">
<div class="col-lg-12 col-md-12" style="padding-left:0px;padding-right:0px;">
<div class="col-lg-1 col-md-1"><b>Footer <i class="fa fa-long-arrow-right" aria-hidden="true"></i></b></div>
<div class="col-lg-10 col-md-10" style="background-color: #ddd;padding-top: 20px;padding-bottom: 20px;text-align: justify;">
<div class="panel-body">
				<div class="">
					<div class="">
						<%=session.getAttribute("footerNote")%>
					</div>
				</div>
	</div>
</div>
<div class="col-lg-1 col-md-1"></div>
</div>
</div>
