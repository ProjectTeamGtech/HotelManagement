<%@taglib uri="/struts-tags" prefix="s" %>

<div id="global_error" style="margin-top: 5%;color: red;font-size: 24px;text-align: center;padding-top: 25px;">
	<s:else>
		<img src="common/images/no-access.jpg" class="img-responsive" style="width: 10%;margin-left: auto;margin-right: auto;margin-bottom: 20px;"></img>
		Opps! You do not have global access to work from remote. Please request your admin to add you to global access user list.
	</s:else>
</div>
