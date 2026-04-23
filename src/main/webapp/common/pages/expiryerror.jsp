<%@taglib uri="/struts-tags" prefix="s" %>



<div id="global_error" >
	<s:if test="hasActionErrors()"> 
		<s:actionerror/>
	</s:if>
	<s:else>
	<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 25px;text-align: center;font-size: 22px;">
		<strong>Your Software Subscription  Has Been Expired.<%-- </strong><br><br>Please Contact With Customer Support No.<br><strong> 9404022226.</strong><br>
		<br>Or E-mail Us at <strong>info@pranam.co.in</strong><br><br> --%>
		
		<s:a href="inputLogin" style="background-color: rgba(239, 239, 239, 0.66);padding: 7px 20px 7px 20px;border-radius: 29px;"><i class="fa fa-sign-in" aria-hidden="true" style="padding-top: 6px"></i> Login Page</s:a>
	</div>
	</s:else>
</div>