
<%@taglib uri="/struts-tags" prefix="s"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>

   <script type="text/javascript" src="http://vaani.neechalkaran.com/Scripts/google_jsapi.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Remarks</h4>
								</div>
							</div>

<s:form action="saveremarkMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label id='ddf'>Enter Remark</label><label class="text-danger">*</label>
			   <s:textfield id="remark" name="remark"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark English" placeholder="Enter Remark English"/>
					<p></p>
				<label>Enter Remark(Hindi)</label><label class="text-danger">*</label>
				  <s:textfield id="remarkhindi" name="remarkhindi"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark Hindi" placeholder="Enter Remark Hindi"/>
					<p></p>
				<label>Enter Remark(Marathi)</label><label class="text-danger">*</label>
				  <s:textfield id="remarkmarathi" name="remarkmarathi"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Remark Hindi" placeholder="Enter Remark Hindi"/>
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


/* google.language.transliterate(["Namaste"], "en", "hi", function(result) {
	  if (!result.error) {
	    var container = document.getElementById("ddf");
	    if (result.transliterations && result.transliterations.length > 0 &&
	        result.transliterations[0].transliteratedWords.length > 0) {
	      container.innerHTML = result.transliterations[0].transliteratedWords[0];
	    }
	  }
	}); */
	
google.setOnLoadCallback(onLoad);
</script>

