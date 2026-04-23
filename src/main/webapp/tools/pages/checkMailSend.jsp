<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/checkMailSend.js"></script>

<link rel="stylesheet" href="mis/assets/css/main.css">
<style>
	.panel-default>.panel-heading {
	    color: #fff;
	    background-color: #777;
	}
	p {
	    margin: 0px;
	}
	.details {
    margin-bottom: 0px;
}
</style>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<div class="">
<s:form action="editEmailConfigureEmailTemplate" id="emailConfigurefrm" theme="simple" method="post" enctype="multipart/form-data">
<div class="col-lg-12 col-xs-12 col-md-12">
	<div class="col-lg-2 col-md-2"></div>
	<div class="col-lg-8 col-md-8">
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 details">
			<h4>Email Setup</h4>
			<%if(loginInfo.getSuperadminid()==1){ %>
				<div><s:submit cssClass="btn btn-warning" value="Edit" style="float: right;margin-top: -30px;"/></div>
			<%} %>
		</div>
		
		<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
			<s:if test="hasActionMessages()">
				<script>
					var msg = " " + document.getElementById('message').value;
					showGrowl('', msg, 'success', 'fa fa-check');
				</script>
			</s:if>
	</div>
</div>
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border:1px solid #ddd">
		
			<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Information Mail</a>
      </h4>
    </div>
    <div id="collapse1" class="panel-collapse collapse in">
      <div class="panel-body">
      	<div class="col-lg-12 col-md-12 col-xs-12">
      		<div class="col-lg-6 col-md-6 col-xs-6" style="padding:0px;border-right: 1px solid #ddd;">
      			<div class="form-group">
      				<label>Host Name</label>
      				<p><s:property value="emailHostName" /></p>
      			</div>
      			<div class="form-group">
      				<label>User Name</label>
      				<p><s:property value="emailUserName"/></p>
      				<s:hidden name="emailConfigureId" id="emailConfigureId"/> 
      			</div>
      			<div class="form-group">
      				<label>Password</label>
      				<p><s:property value="emailPassword" /></p>
      				<P id="showpd" style="display: none;"></P>
      				<small><s:checkbox name="showPwd" id="showPwd"	fieldValue="showPwd" onclick="showPassword(this.value);" title="Show Email Configure Password" cssClass="chkGrp2"/>	Show Password</small>
      			</div>
      		</div>
      		<div class="col-lg-6 col-md-6 col-xs-6">
      			<div class="form-group">
      				<label>Enable / Disable Sending Email</label>
      				
      				<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;">
								<li>
									
									<div class="control-label">
                                                <div class="onoffswitch greensea">
                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="setChangeValueCheckEmail()" class="onoffswitch-checkbox" id="checkMailSend">
                                                    <label class="onoffswitch-label" for="checkMailSend">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                    </li>
							</ul>
      				
      				<%-- <p><s:checkbox name="checkMailSend" id="" onchange="" style="margin-top:15px;"/></p> --%>
      			</div>
      			<div class="form-group">
      				<label>Set Auto Reminder &nbsp;<small style="color: #999;">(24 / 48 / 72 hour)</small></label>
      				<p><s:select name="adDuration" disabled="true" list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}" cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select></p>
      			</div>
      		</div>
      	</div>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Account & Billing Mail</a>
      </h4>
    </div>
    <div id="collapse2" class="panel-collapse collapse">
      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
      commodo consequat.</div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Marketing Mail</a>
      </h4>
    </div>
    <div id="collapse3" class="panel-collapse collapse">
      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
      commodo consequat.</div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Procurement Mail</a>
      </h4>
    </div>
    <div id="collapse4" class="panel-collapse collapse">
      <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
      sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
      minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
      commodo consequat.</div>
    </div>
  </div>
  
</div>

	

		</div>
	</div>
	<div class="col-lg-2 col-md-2"></div>
</div>
</s:form>

							<div class="">
								
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>





<div class="topback2 hidden">
		 <div class="panel-body hidden">
		 
<div class="row">
	<div class="col-lg-6">
		<div class="form-group">
		<div class="col-lg-2">
		Note :
		</div>
		<div class="col-lg-10">
		<s:checkbox name="on" id="on" value="true" disabled="true"/>
		<label>Enable Sending Email</label>
			</div>
			
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-6">
		<div class="form-group">
		<div class="col-lg-2">
		</div>
		<div class="col-lg-10">
		<s:checkbox name="off" id="off" value="false"  disabled="true"/>
		<label>Disable Sending Email</label>
			</div>
			
		</div>
	</div>
</div>

</div></div>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>




