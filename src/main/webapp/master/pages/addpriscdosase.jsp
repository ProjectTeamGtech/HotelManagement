
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4 align="center">Add Dosage</h4>
								</div>
							</div>

<s:form action="savedosageMaster" id="master_form" theme="simple">
<div class="row" style="padding: 30px;">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label>Enter Dose</label><label class="text-danger">*</label>
			   <s:textfield id="remark" name="remark"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark English" placeholder="Enter Remark English"/>
					<p></p>
				<label>Enter Dose(Hindi)</label><label class="text-danger">*</label>
				  <s:textfield id="remarkhindi" name="remarkhindi"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark Hindi" placeholder="Enter Remark Hindi"/>
					<p></p>
			<div class='hidden'>
				<label>Enter Remark(Marathi)</label><label class="text-danger">*</label>
				  <s:textfield id="remarkmarathi" name="remarkmarathi"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark Hindi" placeholder="Enter Remark Hindi"/>
			</div>				
				<%-- <label><s:property value="remarkmarathi"/></label> --%>
				<%--  <%String x=(String) session.getAttribute("marathi"); %>
				<p><%=x%>1</p>  --%>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<a href="CityMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
<script>

google.load("elements", "1", {
      packages: "transliteration"
    });

function onLoad() {
  var options = {
      sourceLanguage:
          google.elements.transliteration.LanguageCode.ENGLISH,
      destinationLanguage:
          [google.elements.transliteration.LanguageCode.HINDI],
      shortcutKey: 'ctrl+g',
      transliterationEnabled: true
  };
  var options1 = {
	      sourceLanguage:
	          google.elements.transliteration.LanguageCode.ENGLISH,
	      destinationLanguage:
	          [google.elements.transliteration.LanguageCode.MARATHI],
	      shortcutKey: 'ctrl+g',
	      transliterationEnabled: true
	  };

  // Create an instance on TransliterationControl with the required
  // options.
  var control =
      new google.elements.transliteration.TransliterationControl(options);
  var control1 =
      new google.elements.transliteration.TransliterationControl(options1);

  // Enable transliteration in the textbox with id
  // 'transliterateTextarea'.
  control.makeTransliteratable(['remarkhindi']);
  control1.makeTransliteratable(['remarkmarathi']);
}
google.setOnLoadCallback(onLoad);
</script>

