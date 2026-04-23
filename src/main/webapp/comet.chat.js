/**
 * @author Semika siriwardana(semika.siriwardana@gmail.com)
 */
function setCometList(){
	
		var container = document.getElementById('members');
	    var childArray = container.children;
	    
	   
	   
	    for(var i = 0; i < childArray.length; i++){
	    	var usr = childArray[i].id;
	    	//cometAuthorizeUserAjax(usr);
	    	
	    	 $.ajax({
	    	        type: 'POST',
	    	        url: 'semikaClinicRegistration?usr='+usr+'',
	    	        data: '{ "imageData" : "usr" }',
	    	        contentType: 'application/json; charset=utf-8',
	    	        dataType: "html",
	    	        success: function (data, status, jqXHR) {
	    	          $("#container").html(data);
	    	         // alert("Local success callback."+data);
	    	          
	    	          var temp = data.split("/");
	    	          var res = temp[0];
	    	          var user = temp[1];
	    	          
	    	          if(res=='false'){
	    	        	  document.getElementById(user).style.display = 'none';
	    	          }
	    	        },
	    	        error: function (jqXHR, status, err) {
	    	          alert("Local error callback.");
	    	        },
	    	        complete: function (jqXHR, status) {
	    	         // alert("Local completion callback."+usr);
	    	        }
	    	    });
	    }  
		
	}
	

function join(userName){
		
		$.cometChat.join(userName);
		
		
	}

(function($){
	$.cometChat = {
		
		_connected:false,
		loginUserName:undefined,
		_disconnecting:undefined,
		_chatSubscription:undefined,
		_membersSubscription:undefined,
		memberListContainerID:undefined, //'id' of a 'div' or 'span' tag which keeps list of online users.
		
		/**
		 * This method can be invoked to disconnect from the chat server.
		 * When user logging off or user close the browser window, user should
		 * be disconnected from cometd server.
		 */
		leave: function() {
            $.cometd.batch(function() {
                $.cometChat._unsubscribe();
            });
            $.cometd.disconnect();

            $.cometChat.loginUserName  = null;
            $.cometChat._disconnecting = true;
        },
		
        /**
         * Handshake with the server. When user logging into your system, you can call this method
         * to connect that user to cometd server. With that user will subscribe with tow channel.
         * '/chat/demo' and '/members/demo' and start to listen to those channels. 
         */
		join: function(username) {
			
			$.cometChat._disconnecting = false;
			$.cometChat.loginUserName  = username;

            var cometdURL = location.protocol + "//" + location.host + config.contextPath + "/cometd.html";

            $.cometd.configure({
                url: cometdURL,
                logLevel: 'debug'
            });
            $.cometd.websocketEnabled = false;
            $.cometd.handshake();
		},
		
		/**
		 * Send the text message to peer as a private message. Private messages
		 * are visible only for relevant peer users.
		 */
		send:function(textMessage, peerUserName, updatedAppointmentData) {
            
			if (!textMessage || !textMessage.length) return;
			
            $.cometd.publish('/service/privatechat', {
                room: '/chat/demo',
                user: $.cometChat.loginUserName,
                chat: textMessage,
                peer: peerUserName,
                apmtdata: updatedAppointmentData
            });
        },
        
		 /**
         * Updates the members list.
         * This function is called when a message arrives on channel /chat/members
         */
        members:function(message) {
        	//alert("hi");
        	
            var sb = [];
            var i = 0;
            $.each(message.data, function() {
            	if ($.cometChat.loginUserName == this) { //login user
            		sb[sb.length] = "<span id='"+this+"' style=\";color: #FF0000;\">" + this + "</span><br>";
            	} else { //peer users
            		sb[sb.length] = "<span id='"+this+"' onclick=\"javascript:createWindow('" + $.cometChat.loginUserName + "', '" + this + "');\"  style=\"cursor: pointer;color: #0000FF;\">" + this + "</span><br>";
            	}
            	i++;
            	
            	//alert(i);
            });
            $('#' + $.cometChat.memberListContainerID).html(sb.join("")); 
        },
        
        /**
         * This function will be invoked every time when '/chat/demo' channel receives a message.
         */
		receive :function(message) {
			
            var fromUser = message.data.user;
            var text     = message.data.chat;
            var toUser   = message.data.peer;
            var dapmtdata = message.data.apmtdata;
            //toUser = 'ankerside123';
            //seting dumy data
            var dmyname = message.data.myname;
            
           
            //Handle receiving messages
            if ($.cometChat.loginUserName == toUser) {
            	
            	
            	//'toUser' is the loginUser and 'fromUser' is the peer user.
            	if(text == 'apmt_modified'){
            		var temp = dapmtdata.split('/');
            		var infoMessage = '<a href ="calNotAvailableSlot?actionType=week&selecteduserid='+temp[5]+'">New Appointment : </a>' + ' ' + ''+temp[0]+'' + ' ' + 'Time : '+ ' ' + ''+temp[1]+'-'+temp[2]+'' + '<br>  Practitioner : '+temp[3]+' <br> Patient : '+temp[4]+'' ;
            		//var infoMessage = "hello"+updatedAppointmentData;
            		//tempAlert("DashBoard has been modified, Please <a href='#' onclick='window.location.reload();'>Update</a>",5000);
            		$.sticky(infoMessage, {
						stickyClass : 'info'
					});
            		
            	
            		getWraperDivChildren('new'+temp[6]+'s');
            		setCommonAction();
            		tempAlert("DashBoard has been modified, Please <a href='#' onclick='window.location.reload();'>Update</a>",5000);
            		
            		
                	
            	}else if(text == "apmt_saved"){
            		var temp = dapmtdata.split('/');
            		setCommonAction();
            		tempAlert("DashBoard has been modified, Please <a href='#' onclick='window.location.reload();'>Update</a>",5000);
            		var infoMessage = '<a href ="calNotAvailableSlot?actionType=week&selecteduserid='+temp[5]+'">New Appointment : </a>' + ' ' + ''+temp[0]+'' + ' ' + 'Time : '+ ' ' + ''+temp[1]+'-'+temp[2]+'' + '<br>  Practitioner : '+temp[3]+' <br> Patient : '+temp[4]+'' ;
            		$.sticky(infoMessage, {
						stickyClass : 'info'
					});
            	}
            	else if(text == "apmt_other"){
            		setCommonAction();
            	}else{
            		var chatReceivingWindow = createWindow(toUser, fromUser);
                	chatReceivingWindow.appendMessage(fromUser, text, $.cometChat.loginUserName);
            	}
            	
            	
            	
            }
            
            //Handle sending messages
            if ($.cometChat.loginUserName == fromUser) {
            	
            	if(text == 'apmt_modified'){
            		tempAlert("Appointment Modified Successfully",5000);
            		
            	}else if(text == "apmt_saved"){
            		tempAlert("Appointment Saved Successfully",5000);
            	}
            	else if(text == "apmt_other"){
            		setCommonAction();
            	}else{
            		var	chatSendingWindow = createWindow(fromUser, toUser);
                	chatSendingWindow.appendMessage(fromUser, text, $.cometChat.loginUserName);
            	}
            	
            	//'fromUser' is the loginUser and 'toUser' is the peer user.
            /*	var	chatSendingWindow = createWindow(fromUser, toUser);
            	chatSendingWindow.appendMessage(fromUser, text, $.cometChat.loginUserName);*/
            }
        },
        
        _unsubscribe: function() {
            if ($.cometChat._chatSubscription) {
                $.cometd.unsubscribe($.cometChat._chatSubscription);
            }
            $.cometChat._chatSubscription = null;
            
            if ($.cometChat._membersSubscription) {
                $.cometd.unsubscribe($.cometChat._membersSubscription);
            }
            $.cometChat._membersSubscription = null;
        },
        
        _connectionEstablished: function() {
            // connection establish (maybe not for first time), so just
            // tell local user and update membership
            $.cometd.publish('/service/members', {
                user: $.cometChat.loginUserName,
                room: '/chat/demo'
            });
        },
        
        _connectionBroken: function() {
            $('#' + $.cometChat.memberListContainerID).empty();
        },
        
        _connectionClosed: function() {
           /* $.cometChat.receive({
                data: {
                    user: 'system',
                    chat: 'Connection to Server Closed'
                }
            });*/
        },
        
        _metaConnect: function(message) {
            if ($.cometChat._disconnecting) {
            	$.cometChat._connected = false;
            	$.cometChat._connectionClosed();
            } else {
                var wasConnected = $.cometChat._connected;
                $.cometChat._connected = message.successful === true;
                if (!wasConnected && $.cometChat._connected) {
                	$.cometChat._connectionEstablished();
                } else if (wasConnected && !$.cometChat._connected) {
                	$.cometChat._connectionBroken();
                }
            }
        },
        
        _subscribe: function() {
			$.cometChat._chatSubscription    = $.cometd.subscribe('/chat/demo',    $.cometChat.receive); //channel handling chat messages
			$.cometChat._membersSubscription = $.cometd.subscribe('/members/demo', $.cometChat.members); //channel handling members.
        },
        
        _connectionInitialized: function() {
            // first time connection for this client, so subscribe tell everybody.
            $.cometd.batch(function() {
            	$.cometChat._subscribe();
            });
        },
        
		_metaHandshake: function (message) {
            if (message.successful) {
            	$.cometChat._connectionInitialized();
            }
        },
        
		_initListeners: function() {
			$.cometd.addListener('/meta/handshake', $.cometChat._metaHandshake);
	        $.cometd.addListener('/meta/connect',   $.cometChat._metaConnect);
	        
	        $(window).unload(function() {
	        	$.cometd.reload();
                $.cometd.disconnect();
	        });
		},		
			
		onLoad: function(config) {
			
			$.cometChat.memberListContainerID = config.memberListContainerID;
			$.cometChat._initListeners();
			
		}		
	};
	
})(jQuery);
