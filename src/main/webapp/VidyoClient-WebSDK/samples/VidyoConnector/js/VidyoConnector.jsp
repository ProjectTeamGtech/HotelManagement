<!DOCTYPE html>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.eu.blogic.jdbc.Connection_provider"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
   <style>

.btn:focus, .btn:active, button:focus, button:active {
  outline: none !important;
  box-shadow: none !important;
}

#image-gallery .modal-footer{
  display: block;
}

.thumb{
  margin-top: 15px;
  margin-bottom: 15px;
}

  </style> 
<% LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

	<title>VidyoConnector</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<script>if (typeof module === 'object') {window.module = module; module = undefined;}</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="jquery-3.1.1.min.js"><\/script>')</script>
	<script>if (window.module) module = window.module;</script>

	
	<!-- We've provide some simple styling to get you started. -->
	<link href="VidyoConnector.css" rel="stylesheet" type="text/css" >
	
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/thirdParties/js/nicEdit.js"></script>
	
<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>
	
	

<%String token = ""; %>
	<!-- Here we load the application which knows how to
	invoke the VidyoConnector API. -->
	<script src="VidyoConnector.js"></script>
	<script type="text/javascript">

	function emrSmall(URL) { 
		 var myWindow = window.open(URL, "", "width=800, height=1980, addressbar=no,");
		 myWindow.focus();
		} 

	
	
	var configParams = {};
	var platformInfo = {};
	var webrtcExtensionPath = "";
	var webrtcInitializeAttempts = 0;

	function onVidyoClientLoaded(status) {
		console.log("Status: " + status.state + "Description: " + status.description);
		switch (status.state) {
			case "READY":    // The library is operating normally
				$("#connectionStatus").html("Ready to Connect");
				$("#helper").addClass("hidden");
				$("#optionsVisibilityButton").removeClass("hidden");
				$("#renderer").removeClass("hidden");
				$("#toolbarLeft").removeClass("hidden");
				$("#toolbarCenter").removeClass("hidden");
				$("#toolbarRight").removeClass("hidden");

				// If configured to autoJoin, then show video full screen immediately
				if (configParams.autoJoin === "1") {
					$("#optionsVisibilityButton").addClass("showOptions").removeClass("hideOptions");
					$("#renderer").addClass("rendererFullScreen").removeClass("rendererWithOptions");
				} else
					$("#options").removeClass("hidden");

				// If WebRTC is being used, specify the screen share extension path.
				if (VCUtils.params.webrtc === "true") {
					if (status.hasOwnProperty("downloadPathWebRTCExtensionFirefox"))
						webrtcExtensionPath = status.downloadPathWebRTCExtensionFirefox;
					else if (status.hasOwnProperty("downloadPathWebRTCExtensionChrome"))
						webrtcExtensionPath = status.downloadPathWebRTCExtensionChrome;
				}

				// Determine which Vidyo stack will be used: Native WebRTC, Transcoding WebRTC or Native (Plugin/Electron)
				var useTranscodingWebRTC, performMonitorShare;
				if (status.description.indexOf("Native XMPP + WebRTC") > -1) {
					// Native WebRTC
					useTranscodingWebRTC = false;
					performMonitorShare  = false;
				} else if (status.description.indexOf("WebRTC successfully loaded") > -1) {
					// Transcoding WebRTC
					useTranscodingWebRTC = true;
					performMonitorShare  = false;
					++webrtcInitializeAttempts;
				} else {
					// Native (Plugin or Electron)
					useTranscodingWebRTC = false;
					performMonitorShare  = true;
				}

				// After the VidyoClient is successfully initialized a global VC object will become available
				// All of the VidyoConnector gui and logic is implemented in VidyoConnector.js
				StartVidyoConnector(VC, useTranscodingWebRTC, performMonitorShare, webrtcExtensionPath, configParams);

				break;
			case "RETRYING": // The library operating is temporarily paused
				$("#connectionStatus").html("Temporarily unavailable retrying in " + status.nextTimeout/1000 + " seconds");
				break;
			case "FAILED":   // The library operating has stopped
				// If WebRTC initialization failed, try again up to 3 times.
				if ((status.description.indexOf("Could not initialize WebRTC transport") > -1) && (webrtcInitializeAttempts < 3)) {
					// Attempt to start the VidyoConnector again.
					StartVidyoConnector(VC, VCUtils.params.webrtc, webrtcExtensionPath, configParams);
					++webrtcInitializeAttempts;
				} else {
					ShowFailed(status);
				}
				break;
			case "FAILEDVERSION":   // The library operating has stopped
				UpdateHelperPaths(status);
				ShowFailedVersion(status);
				$("#connectionStatus").html("Failed: " + status.description);
				break;
			case "NOTAVAILABLE": // The library is not available
				UpdateHelperPaths(status);
				$("#connectionStatus").html(status.description);
				break;
            case "TIMEDOUT":   // Transcoding Inactivity Timeout
                $("#connectionStatus").html("Failed: " + status.description);
                $("#messages #error").html('Page timed out due to inactivity. Please refresh your browser and try again.');
                break;
		}
		return true; // Return true to reload the plugins if not available
	}
	function UpdateHelperPaths(status) {
		$("#helperPlugInDownload").attr("href", status.downloadPathPlugIn);
		$("#helperAppDownload").attr("href", status.downloadPathApp);
	}
	function ShowFailed(status) {
		var helperText = '';
		 // Display the error
		helperText += '<h2>An error occurred, please reload</h2>';
		helperText += '<p>' + status.description + '</p>';

		$("#helperText").html(helperText);
		$("#failedText").html(helperText);
		$("#failed").removeClass("hidden");
		$("#connectionStatus").html("Failed: " + status.description);
		$("#optionsVisibilityButton").addClass("hidden");
		$("#options").addClass("hidden");
	}
	function ShowFailedVersion(status) {
		var helperText = '';
		 // Display the error
		helperText += '<h4>Please Download a new plugIn and restart the browser</h4>';
		helperText += '<p>' + status.description + '</p>';

		$("#helperText").html(helperText);
	}

	function loadVidyoClientLibrary(webrtc, plugin) {
		// If webrtc, then set webrtcLogLevel
		var webrtcLogLevel = "";
		if (webrtc) {
			// Set the WebRTC log level to either: 'info' (default), 'error', or 'none'
			if (configParams.webrtcLogLevel === 'info' || configParams.webrtcLogLevel === 'error' || configParams.webrtcLogLevel == 'none')
				webrtcLogLevel = '&webrtcLogLevel=' + configParams.webrtcLogLevel;
			else
				webrtcLogLevel = '&webrtcLogLevel=info';
		}

		//We need to ensure we're loading the VidyoClient library and listening for the callback.
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = 'https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded&webrtc=' + webrtc + '&plugin=' + plugin + webrtcLogLevel;
		document.getElementsByTagName('head')[0].appendChild(script);
	}
	
	window.onload = function(){
		joinViaBrowser();
		
		/* $("#options").addClass("hiddenPermanent");
		$("#optionsVisibilityButton").addClass("hiddenPermanent");
		$("#renderer").addClass("rendererFullScreenPermanent"); */
		
		<%Connection con = Connection_provider.getconnection();
			PreparedStatement p = null;
			String duserid=(String)session.getAttribute("diaryUserId");
			String sql = "select webrtc_token from apm_user where id = "+duserid+" ";
			
			try{
				p = con.prepareStatement(sql);
				ResultSet rs = p.executeQuery();
				if(rs.next()){
					token = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
		%>
	}
	
	function joinViaBrowser() {
		$("#helperText").html("Loading...");
		$("#helperPicker").addClass("hidden");
		$("#monitorShareParagraph").addClass("hidden");
		loadVidyoClientLibrary(true, false);
	}

	function joinViaPlugIn() {
		$("#helperText").html("Don't have the PlugIn?");
		$("#helperPicker").addClass("hidden");
		$("#helperPlugIn").removeClass("hidden");
		loadVidyoClientLibrary(false, true);
	}

	function joinViaElectron() {
		$("#helperText").html("Electron...");
		$("#helperPicker").addClass("hidden");
		loadVidyoClientLibrary(false, true);
	}
	function loadAppFromProtocolHandler(forceRedirect) {
		var protocolHandlerLink = 'vidyoconnector://' + window.location.search;

		if (platformInfo.isiOS || platformInfo.isAndroid || forceRedirect) {
			window.open(protocolHandlerLink, '_self');
		} else {
			$('body').append("<iframe src='" + protocolHandlerLink + "' style='width:0;height:0;border:0; border:none;'></iframe>");
		}
	}
	function joinViaApp() {
		$("#helperText").html("Don't have the app?");
		$("#helperPicker").addClass("hidden");
		$("#helperApp").removeClass("hidden");
		/* launch */
		loadAppFromProtocolHandler(false);
		loadVidyoClientLibrary(false, false);
	}
	function joinViaOtherApp() {
		$("#helperText").html("Don't have the app?");
		$("#helperPicker").addClass("hidden");
		$("#helperOtherApp").removeClass("hidden");
		/* launch */
		loadAppFromProtocolHandler(false);
		loadVidyoClientLibrary(false, false);
	}

	function loadPlatformInfo(platformInfo) {
		var userAgent = navigator.userAgent || navigator.vendor || window.opera;
		// Opera 8.0+
		platformInfo.isOpera = userAgent.indexOf("Opera") != -1 || userAgent.indexOf('OPR') != -1 ;
		// Firefox
		platformInfo.isFirefox = userAgent.indexOf("Firefox") != -1 || userAgent.indexOf('FxiOS') != -1 ;
		// Chrome 1+
		platformInfo.isChrome = userAgent.indexOf("Chrome") != -1 || userAgent.indexOf('CriOS') != -1 ;
		// Safari
		platformInfo.isSafari = !platformInfo.isFirefox && !platformInfo.isChrome && userAgent.indexOf("Safari") != -1;
		// AppleWebKit
		platformInfo.isAppleWebKit = !platformInfo.isSafari && !platformInfo.isFirefox && !platformInfo.isChrome && userAgent.indexOf("AppleWebKit") != -1;
		// Internet Explorer 6-11
		platformInfo.isIE = (userAgent.indexOf("MSIE") != -1 ) || (!!document.documentMode == true );
		// Edge 20+
		platformInfo.isEdge = !platformInfo.isIE && !!window.StyleMedia;
		// Check if Mac
		platformInfo.isMac = navigator.platform.indexOf('Mac') > -1;
		// Check if Windows
		platformInfo.isWin = navigator.platform.indexOf('Win') > -1;
		// Check if Linux
		platformInfo.isLinux = navigator.platform.indexOf('Linux') > -1;
		// Check if iOS
		platformInfo.isiOS = userAgent.indexOf("iPad") != -1 || userAgent.indexOf('iPhone') != -1 ;
		// Check if Android
		platformInfo.isAndroid = userAgent.indexOf("android") > -1;
		// Check if Electron
		platformInfo.isElectron = (typeof process === 'object') && process.versions && (process.versions.electron !== undefined);
		// Check if WebRTC is available
		platformInfo.isWebRTCAvailable = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || (navigator.mediaDevices ? navigator.mediaDevices.getUserMedia : undefined)) ? true : false;
		// Check if 64bit
		platformInfo.is64bit = navigator.userAgent.indexOf('WOW64') > -1 ||  navigator.userAgent.indexOf('Win64') > -1 || window.navigator.platform == 'Win64';	
	}
	
	function loadHelperOptions(platformInfo) {
		if (!platformInfo.isMac && !platformInfo.isWin && !platformInfo.isLinux) {
			/* Mobile App*/
			if (platformInfo.isAndroid || platformInfo.isiOS) {
				$("#joinViaApp").removeClass("hidden");
			} else {
				$("#joinViaOtherApp").removeClass("hidden");
			}
			if (platformInfo.isWebRTCAvailable) {
				/* Supports WebRTC */
				$("#joinViaBrowser").removeClass("hidden");
			}
		} else {
			/* Desktop App */
			$("#joinViaApp").removeClass("hidden");

			if (platformInfo.isWebRTCAvailable) {
				/* Supports WebRTC */
				$("#joinViaBrowser").removeClass("hidden");
			}
			if (platformInfo.isSafari || (platformInfo.isAppleWebKit && platformInfo.isMac) || (platformInfo.isIE && !platformInfo.isEdge)) {
				/* Supports Plugins */
				$("#joinViaPlugIn").removeClass("hidden");
			}
		}
	}

	// Runs when the page loads
	$(function() {
		var connectorType = getUrlParameterByName("connectorType");
		loadPlatformInfo(platformInfo);

		// Extract the desired parameter from the browser's location bar
		function getUrlParameterByName(name) {
			var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
			return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
		}

		// Fill in the form parameters from the URI
		var host = getUrlParameterByName("host");
		if (host)
			$(".host").val(host);
		var token = getUrlParameterByName("token");
		if (token)
			$(".token").val(token);
		var displayName = getUrlParameterByName("displayName");
		if (displayName)
			$(".displayName").val(displayName);
		var resourceId = getUrlParameterByName("resourceId");
		if (resourceId)
			$(".resourceId").val(resourceId);
		configParams.autoJoin    = getUrlParameterByName("autoJoin");
		configParams.enableDebug = getUrlParameterByName("enableDebug");
		configParams.microphonePrivacy = getUrlParameterByName("microphonePrivacy");
		configParams.cameraPrivacy = getUrlParameterByName("cameraPrivacy");
		configParams.webrtcLogLevel = getUrlParameterByName("webrtcLogLevel");
		configParams.returnURL = getUrlParameterByName("returnURL");
		configParams.isIE = platformInfo.isIE;
		var hideConfig = getUrlParameterByName("hideConfig");

		// If the parameters are passed in the URI, do not display options dialog
		if (host && token && displayName && resourceId) {
			$(".optionsParameters").addClass("hiddenPermanent");
		}

		if (hideConfig=="1") {
			$("#options").addClass("hiddenPermanent");
			$("#optionsVisibilityButton").addClass("hiddenPermanent");
			$("#renderer").addClass("rendererFullScreenPermanent");
		}

		if (connectorType == "app") {
			joinViaApp();
		} else if (connectorType == "browser") {
			joinViaBrowser();
		} else if (connectorType == "plugin") {
			joinViaPlugIn();
		} else if (connectorType == "other") {
			joinViaOtherApp();
		} else if (platformInfo.isElectron) {
			joinViaElectron();
		} else {
			loadHelperOptions(platformInfo);
		}
	});
	</script>
</head>

<!-- We execute the VidyoConnectorApp library on page load
to hook up all of the events to elements. -->
<body id="vidyoConnector">
<div id="mtoptionvisiblitydiv" class="">
	<!-- This button toggles the visibility of the options. -->
	<button id="optionsVisibilityButton" title="Toggle Options" class="optionsVisibiliyButtonElements hideOptions hidden"></button>


	<div id="options" class="options hidden">
		<!-- <img class="logo" src="Images/VidyoIcon.png"/> -->

		<form>
		<div class="optionsParameters hidden">
		<p>
			<!-- The host of our backend service. -->
			<label>Host</label>
			<input type="text" id="host" class="host" value="prod.vidyo.io">
		</p>
		<p>
			<!-- A token that is derived from the deveoper key assigned to your account which will allow access for this particular instance.
			The token will contain its expiration date and the user ID.
			For more information visit the developer section of http://vidyo.io -->
			<label>Token</label>
			<input type="text" id="token" class="token" placeholder="ACCESS-TOKEN" value="<%=token %>">
		</p>
		<p>
			<!-- This is the display name that other users will see.
			-->
			<label for="displayName">Display Name</label>
			<input id="displayName" class="displayName" type="text" placeholder="Display Name" value="Guest">
		</p>
		<p>
			<!-- This is the "room" or "space" to which you're connecting
			the user. Other users who join this same Resource will be able to see and hear each other.
			-->
			<label for="resourceId">Resource ID</label>
			<input id="resourceId" class="resourceId" type="text" placeholder="Conference Reference" value="demoRoom">
		</p>
		
		<p>
			<!-- On page load, this input is filled with a list of all the available cameras on the user's system. -->
			<label for="cameras">Camera</label>
			<select id="cameras">
				<option value='0'>None</option>
			</select>
		</p>
		<p>
			<!-- On page load, this input is filled with a list of all the available microphones on the user's system. -->
			<label for="microphones">Microphone</label>
			<select id="microphones">
				<option value='0'>None</option>
			</select>
		</p>
		<p>
			<!-- On page load, this input is filled with a list of all the available microphones on the user's system. -->
			<label for="speakers">Speaker</label>
			<select id="speakers">
				<option value='0'>None</option>
			</select>
		</p>
		
		<p id="monitorShareParagraph">
			<!-- On page load, this input is filled with a list of all the available monitor shares on the user's system. -->
			<label for="monitorShares">Monitor Share</label>
			<select id="monitorShares">
				<option value='0'>None</option>
			</select>
		</p>
		<p>
			<!-- On page load, this input is filled with a list of all the available window shares on the user's system. -->
			<label for="windowShares">Window Share</label>
			<select id="windowShares">
				<option value='0'>None</option>
			</select>
		</p>
		</div>
		<%String sq=request.getContextPath(); %>
		<%if(loginfo.getUserType()!=5){ %>
			<h2 ><a href="#"  onclick="emrSmall('<%=sq %>/getPatientRecordEmr')">Patient EMR<img src="<%=sq %>/cicon/emr.png" style="margin-bottom: -15px;"></a></h2>
			<h2 ><a href="#"  onclick="emrSmall('<%=sq %>/emrdocsEmr')"> EMR DOCs <img src="<%=sq %>/cicon/log.png" style="margin-bottom: -15px;"></a></h2>
			<%Client client = (Client) session.getAttribute("puresevavdyoclient"); %>
			
			<table>
				<thead>
					<tr>
						<th style="width: 25%"></th>
						<th style="width: 60%"></th>
					</tr>
				</thead>
				<tbody>
					<%if(!DateTimeUtils.isNull(client.getProfileImg()).equals("")){ %>
						<tr>
							<td  style="text-align: left;font-size: 13px;">Patient's Profile Photo</td>
							<td  style="text-align: left;font-size: 13px;">
								<a href="#" onclick="openSamllnew('<%=request.getContextPath() %>/liveData/document/<%=client.getDocImg() %>')">
									<img alt="" src="<%=request.getContextPath() %>/liveData/document/<%=client.getDocImg() %>" style="height: 100px;width: 100px;">
								</a>
							</td>
						</tr>
					<%} %>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Name</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getTitle() %> <%=client.getFirstName() %>  <%=client.getLastName() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">UHID</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getAbrivationid() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Address</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getAddress() %>,<%=client.getTown_village() %>,<%=client.getTown() %>,<%=client.getCounty() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Contact No</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getMobNo() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Email ID</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getEmail() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Gender</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getGender() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">DOB</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getDob() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Age</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getAge3() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Document</td>
						<td style="text-align: left;font-size: 13px;"><%=client.getDocType() %></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td  style="text-align: left;font-size: 13px;">Patient's Identity Document</td>
						<td style="text-align: left;font-size: 13px;">
						<%if(!DateTimeUtils.isNull(client.getProfileImg()).equals("")){ %>
							<a href="#" onclick="openSamllnew('<%=request.getContextPath() %>/liveData/document/<%=client.getProfileImg() %>')">
							<img alt="" src="<%=request.getContextPath() %>/liveData/document/<%=client.getProfileImg() %>" style="height: 100px;width: 100px;">
							</a>
							<%} %>
						</td>
					</tr>
				</tbody>
			</table>
			
			
		<%} %>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="margin-top: 10px;display: none;"><!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
									    	<textarea style="height: 250px;" rows="10" cols="8" id="otnotes" name="otnotes"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></textarea>
									    </div>
		<div id='notes'></div>
		</form>
		
		
		<div id="messages">
			<!-- All Vidyo-related messages will be inserted into these spans. -->
			<span id="error"></span>
			<span id="message"></span>
		</div>
	</div>
	</div>
	<!-- This is the div into which the Vidyo component will be inserted. -->
	<div id="renderer" class="rendererWithOptions pluginOverlay hidden">
	</div>
	<div id="toolbarLeft" class="toolbar hidden">
		<span id="participantStatus"></span>
	</div>
	<div id="toolbarRight" class="toolbar hidden">
		<span id="connectionStatus">Initializing</span>
		<span id="clientVersion"></span>
	</div>
	<div id="toolbarCenter" class="toolbar hidden">
		<!-- This button toggles the camera privacy on or off. -->
		<button id="cameraButton" title="Camera Privacy" class="toolbarButton cameraOn"></button>

		<!-- This button joins and leaves the conference. -->
		<button id="joinLeaveButton" title="Join Conference" class="toolbarButton callStart"></button>

		<!-- This button mutes and unmutes the users' microphone. -->
		<button id="microphoneButton" title="Microphone Privacy" class="toolbarButton microphoneOn"></button>
	</div>
	<div id="helper">
		<table>
			<tr>
				<td><!-- <img class="logo" src="Images/VidyoIO-LogoHorizontal-Dark@2x.png"/> --></td>
			</tr>
			<tr>
				<td id="helperText"></td>
			</tr>
			<tr id="helperPicker">
				<td>
					<table>
						<tr>
							<td id="joinViaBrowser" class="hidden">
								<div class="helperHeader">
									<img src="Images/web.svg" onclick="javascript:joinViaBrowser()"/>
								</div>
								<ul>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Join immediately
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										No install
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Good quality
									</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaBrowser()">Join via the browser</a>
								</div>
							</td>
							<td id="joinViaPlugIn" class="hidden">
								<div class="helperHeader">
									<img src="Images/download.svg" onclick="javascript:joinViaPlugIn()"/>
								</div>
								<ul>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Join from the browser
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										One-time install
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Best quality
									</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaPlugIn()">Join via the plugin</a>
								</div>
							</td>
							<td id="joinViaApp" class="hidden">
								<div class="helperHeader">
									<img src="Images/desktop.svg" onclick="javascript:joinViaApp()"/>
								</div>
								<ul>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Join with a click
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										One-time install
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Best quality
									</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaApp()">Join via the app</a>
								</div>
							</td>

							<td id="joinViaOtherApp" class="hidden">
								<div class="helperHeader">
									<img src="Images/download.svg" onclick="javascript:joinViaOtherApp()"/>
								</div>
								<ul>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Join from any device
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Advanced installation
									</li>
									<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
										Best quality
									</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaOtherApp()">Join via the app</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="helperPlugIn" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="Images/download.svg" onclick="javascript:joinViaBrowser()"/>
					</div>
					<ul>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							Download and install it now
						</li>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							The plugin will launch automatically once installed
						</li>
					</ul>
					<div class="helperFooter">
						<a id="helperPlugInDownload" href="">Download</a>
					</div>
				</td>
			</tr>
			<tr id="helperApp" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="Images/download.svg" onclick="javascript:joinViaApp()"/>
					</div>
					<ul>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							Download and install it now
						</li>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							Launch once installed
						</li>
					</ul>
					<div class="helperFooter helperFooterTwoButton">
						<a id="helperAppDownload" href="">Download</a>
						<a href="javascript:loadAppFromProtocolHandler(true)">Launch</a>
					</div>
				</td>
			</tr>
			<tr id="helperOtherApp" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="Images/download.svg" onclick="javascript:joinViaOtherApp()"/>
					</div>
					<ul>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							Build and install from the SDK
						</li>
						<li class="helperCheck"><img src="Images/checkmark.svg"/>&nbsp;&nbsp;
							Launch once installed
						</li>
					</ul>
					<div class="helperFooter">
						<a href="javascript:loadAppFromProtocolHandler(true)">Launch</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="downloadContainerLegal">
						<!-- By clicking &quot;Join&quot; or &quot;Download&quot;, you agree to our <a target="_blank" style="color: #6a6a6a;" href="http://www.vidyo.com/eula/">End-User License Agreement</a> & <a target="_blank" style="color: #6a6a6a;" href="http://www.vidyo.com/privacy-policy/">Privacy Policy</a>. -->
					</div>
        			</td>
			</tr>
		</table>
	</div>
	<div id="failed" class="hidden">
		<table>
			<tr>
				<td><!-- <img class="logo" src="Images/VidyoIcon.png"/> --></td>
			</tr>
			<tr>
				<td id="failedText">Error</td>
			</tr>
		</table>
	</div>
	
	
	<div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" style="height: 600px;">
                    <div class="modal-header">
                        <%-- <h4 class="modal-title" id="image-gallery-title">Preview</h4>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span>
                        </button> --%>
                        <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
						</button>
                    	<h4 class="modal-title hidden" id="image-gallery-title">Preview</h4>
                    	<h4 class="modal-title" >Preview</h4>
                    </div>
                    <div class="modal-body" >
                        <img style="height: 530px;" id="image-gallery-image" class="img-responsive col-md-12" src="">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary float-left" id="show-previous-image"><i class="fa fa-arrow-left"></i>
                        </button>

                        <button type="button" id="show-next-image" class="btn btn-secondary float-right"><i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>   
    
		
	<script type="text/javascript">
	let modalId = $('#image-gallery');

	$(document)
	  .ready(function () {

	    loadGallery(true, 'a.thumbnail');

	    //This function disables buttons when needed
	    function disableButtons(counter_max, counter_current) {
	      $('#show-previous-image, #show-next-image')
	        .show();
	      if (counter_max === counter_current) {
	        $('#show-next-image')
	          .hide();
	      } else if (counter_current === 1) {
	        $('#show-previous-image')
	          .hide();
	      }
	    }

	    /**
	     *
	     * @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.
	     * @param setClickAttr  Sets the attribute for the click handler.
	     */

	    function loadGallery(setIDs, setClickAttr) {
	      let current_image,
	        selector,
	        counter = 0;

	      $('#show-next-image, #show-previous-image')
	        .click(function () {
	          if ($(this)
	            .attr('id') === 'show-previous-image') {
	            current_image--;
	          } else {
	            current_image++;
	          }

	          selector = $('[data-image-id="' + current_image + '"]');
	          updateGallery(selector);
	        });

	      function updateGallery(selector) {
	        let $sel = selector;
	        current_image = $sel.data('image-id');
	        $('#image-gallery-title')
	          .text($sel.data('title'));
	        $('#image-gallery-image')
	          .attr('src', $sel.data('image'));
	        disableButtons(counter, $sel.data('image-id'));
	      }

	      if (setIDs == true) {
	        $('[data-image-id]')
	          .each(function () {
	            counter++;
	            $(this)
	              .attr('data-image-id', counter);
	          });
	      }
	      $(setClickAttr)
	        .on('click', function () {
	          updateGallery($(this));
	        });
	    }
	  });

	// build key actions
	$(document)
	  .keydown(function (e) {
	    switch (e.which) {
	      case 37: // left
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-previous-image').is(":visible")) {
	          $('#show-previous-image')
	            .click();
	        }
	        break;

	      case 39: // right
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-next-image').is(":visible")) {
	          $('#show-next-image')
	            .click();
	        }
	        break;

	      default:
	        return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	  });

	
	</script>
	<script type="text/javascript">
	function openSamllnew(URL){
		 myWindow = window.open(URL, "_blank", "width=800, height=600, addressbar=no,");
	}
	</script>
</body>
</html>
