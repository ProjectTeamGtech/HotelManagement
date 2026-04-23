<%@taglib uri="/struts-tags" prefix="s" %>

<div id="global_error" >
	<s:if test="hasActionErrors()"> 
		<s:actionerror/>
	</s:if>
	<s:else>
	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 25px;text-align: center;font-size: 22px;">
		Your session has been expired.<br><br><s:a href="inputLogin" style="background-color: rgba(239, 239, 239, 0.66);padding: 7px 20px 7px 20px;border-radius: 29px;">Please <i class="fa fa-sign-in" aria-hidden="true" style="padding-top: 6px;"></i> login again</s:a>
	</div>
	</s:else>
</div>
