<!DOCTYPE html>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html>
<head>

<!-- websocket js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd/AckExtension.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/org/cometd/ReloadExtension.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cometd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cometd-reload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/chat.window.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/comet.chat.js"></script>

<script type="text/javascript">
    
    var chatWindowArray = [];
    
    var config = {
        contextPath: '${pageContext.request.contextPath}'
    };
	
	function getChatWindowByUserPair(loginUserName, peerUserName) {
		
		var chatWindow;
		
		for(var i = 0; i < chatWindowArray.length; i++) {
			var windowInfo = chatWindowArray[i];
			if (windowInfo.loginUserName == loginUserName && windowInfo.peerUserName == peerUserName) {
				chatWindow =  windowInfo.windowObj;
			}
		}
		
		return chatWindow;
	}
	
	function createWindow(loginUserName, peerUserName) {
		
		//alert('hello');
		
		var chatWindow = getChatWindowByUserPair(loginUserName, peerUserName);
		
		if (chatWindow == null) { //Not chat window created before for this user pair.
			chatWindow = new ChatWindow(); //Create new chat window.
			chatWindow.initWindow({
				loginUserName:loginUserName, 
				peerUserName:peerUserName,
				windowArray:chatWindowArray});
			
			//collect all chat windows opended so far.
			var chatWindowInfo = { peerUserName:peerUserName, 
					               loginUserName:loginUserName,
					               windowObj:chatWindow 
					             };
			
			chatWindowArray.push(chatWindowInfo);
    	}
		
		chatWindow.show();
		
		return chatWindow;
	}

	
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/comet.chat.css"/>
</head>
<body>
<script type="text/javascript">

	<%LoginInfo loginInfos = LoginHelper.getLoginInfo(request);%>
	var userName = '<%=loginInfos.getUserId()%>';
	$(document).ready(function(){ 
		$.cometChat.onLoad({memberListContainerID:'members'});
		join(userName);
		//setname();
		
		 
		    
	});
	
	var selectedusr = "";
	
	
	function cometAuthorizeUserAjax(usr){
		var url = "semikaClinicRegistration?usr="+usr+" ";
		selectedusr = usr;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = cometAuthorizeUserAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function cometAuthorizeUserAjaxRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				//alert(req.responseText)
				var result = req.responseText;
				
				if(result=='false'){
					
					document.getElementById(selectedusr).style.display = 'none';
				}
			}
		}
	}
	
</script>
<!--  <div id="members"></div>  -->
<!-- <input type="text" name="username">
<input type="submit" value="Join" onclick="setname()">
 -->
<!-- <div id="websockettesting"></div> -->

</body>
</html>
