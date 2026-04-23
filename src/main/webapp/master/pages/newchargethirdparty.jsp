<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="thirdParties/js/tpcharges.js"></script>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<style>
.form-group {
    margin-bottom: 5px;
}
.backformset{
    background-color: #f5f5f5;
    min-height: 328px;
    padding: 15px 0px 0px 0px;
    border-right: 1px solid #ddd;
}
.text-align-right{
	text-align: right !important;
}
.text-align-center{
	text-align: center !important;;
}
</style>
<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Create New ThirdParty Charges</h4>

									</div>
								</div>
<s:hidden id="isfromaddchargepage"></s:hidden>
<s:form action="newThirdpartyChargeThirdParty" id="tpchargeform" theme="simple" method="post">
<s:hidden name="frompayercreation"></s:hidden>

<div class="panel panel-primary">
			<div class="">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12" id = "tpchargeTable">	
						<div class="col-lg-12 col-md-12 topback2">
							<div class="col-lg-2 col-md-2 col-xs-2">
								<div class="form-group">
									<label for="exampleInputEmail1">Select ThirdParty </label>
		    					<s:select list="thirdPartyList" onchange='' listKey="id" listValue="companyName" id="tpidold" headerKey="0" headerValue="Select Payer" name="tpidold" title="select Third Party"
										cssClass="form-control chosen-select"   > </s:select>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-2">
								<div class="form-group">
									<label for="exampleInputEmail1" id="hstp">Select New ThirdParty</label>
									<div id="">
										<s:select list="thirdPartyList" onchange='' listKey="id" listValue="companyName" id="tpid" headerKey="0" headerValue="Select Payer" name="tpid" title="select Third Party"
										cssClass="form-control chosen-select"   > </s:select>
									</div>
								</div>
							</div>
							
							<div class="col-lg-2 col-md-2 col-xs-2">
								<div class="form-group" style="padding-top: 20px;">
								
										<input type="button" class="btn btn-primary" value="GO" onclick="savenewtpcharges(1)"/>
										
									</div>
								</div>
							</div>
							</div>
							
						
					
					
										
						
					
			</div>
			

			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>

</s:form>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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

<script>
$(function () {
    $('#scroll').slimScroll({
        height: '450px'
    });
});
 
</script>
<script>
$("#search").keyup(function () {
    var value = this.value.toLowerCase().trim();

    $("table tr").each(function (index) {
        if (!index) return;
        $(this).find("td").each(function () {
            var id = $(this).text().toLowerCase().trim();
            var not_found = (id.indexOf(value) == -1);
            $(this).closest('tr').toggle(!not_found);
            return not_found;
        });
    });
});

</script>


