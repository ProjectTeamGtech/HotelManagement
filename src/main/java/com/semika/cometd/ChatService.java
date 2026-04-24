/**
 * @author Semika siriwardana
 * CometD chat service.
 */
package com.semika.cometd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.cometd.annotation.Configure;
import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.cometd.server.filter.DataFilter;
import org.cometd.server.filter.DataFilterMessageListener;
import org.cometd.server.filter.JSONDataFilter;
import org.cometd.server.filter.NoMarkupFilter;

import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.InstantMessage.eu.bi.InstantMsgDAO;
import com.apm.InstantMessage.eu.blogic.jdbc.JDBCInstantMsgDAO;
import com.apm.InstantMessage.eu.entity.InstantMsg;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

@Service("chat")
public class ChatService {
	
	
    private final ConcurrentMap<String, Map<String, String>> _members = new ConcurrentHashMap<String, Map<String, String>>();
    @Inject
    private BayeuxServer _bayeux;
    @Session
    private ServerSession _session;

    @Configure ({"/chat/**','/members/**"})
    protected void configureChatStarStar(ConfigurableServerChannel channel) {
        DataFilterMessageListener noMarkup = new DataFilterMessageListener(new NoMarkupFilter(),new BadWordFilter());
        channel.addListener(noMarkup);
        channel.addAuthorizer(GrantAuthorizer.GRANT_ALL);
    }

    @Configure ("/service/members")
    protected void configureMembers(ConfigurableServerChannel channel) {
        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);
        channel.setPersistent(true);
    }

    @Listener("/service/members")
    public void handleMembership(ServerSession client, ServerMessage message) {
        Map<String, Object> data = message.getDataAsMap();
        final String room = ((String)data.get("room")).substring("/chat/".length());
        
        Map<String, String> roomMembers = _members.get(room);
        if (roomMembers == null) {
            Map<String, String> new_room = new ConcurrentHashMap<String, String>();
            roomMembers = _members.putIfAbsent(room, new_room);
            if (roomMembers == null) roomMembers = new_room;
        }
        final Map<String, String> members = roomMembers;
        String userName = (String)data.get("user");
        members.put(userName, client.getId());
        
        //setting dummy value for a while
        //members.put("Manoj D. Mishra", "808");
        
        client.addListener(new ServerSession.RemoveListener() {
            public void removed(ServerSession session, boolean timeout) {
                members.values().remove(session.getId());
                broadcastMembers(room, members.keySet());
            }
        });

        broadcastMembers(room, members.keySet());
        
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        StringBuffer str = new StringBuffer();
        for (Map.Entry<String, String> e : members.entrySet()){
        	str.append(e.getKey() + ",");
        	
        	
        }
        if(str.length()!=0){
        	String apmuserlist = str.substring(0, str.length()-1);
        	/*session.setAttribute("apmuserlist", apmuserlist);*/
        	String temp[] = apmuserlist.split(",");
        	for(int i=0;i<temp.length;i++){
        		String userid = temp[i];
        		boolean isUseridExist = checkUseridExist(userid);
            	if(!isUseridExist){
            		insertLoggedUser(userid);
            	}
        	}
        	
        }
        
    }

    private boolean checkUseridExist(String userid) {
    	
    	Connection connection = null;
    	boolean result = false;
    	try{
    		connection = Connection_provider.getconnection();
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
    		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
    		result = notAvailableSlotDAO.checLoggedkUseridExist(userid);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
		return result;
	}

	private void insertLoggedUser(String userid) {
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			connection = DriverManager.getConnection(""+Constants.DB_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			int result = notAvailableSlotDAO.saveLoggedInUserID(userid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void broadcastMembers(String room, Set<String> members) {
        // Broadcast the new members list
        ClientSessionChannel channel = _session.getLocalSession().getChannel("/members/"+room);
        channel.publish(members);
    }

    @Configure ("/service/privatechat")
    protected void configurePrivateChat(ConfigurableServerChannel channel) {
        DataFilterMessageListener noMarkup = new DataFilterMessageListener(new NoMarkupFilter(),new BadWordFilter());
        channel.setPersistent(true);
        channel.addListener(noMarkup);
        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);
    }

    @Listener("/service/privatechat")
    protected void privateChat(ServerSession client, ServerMessage message) {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
    	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);

    	HttpSession session = request.getSession(true);
    	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
        Map<String,Object> data = message.getDataAsMap();
        
        String room = ((String)data.get("room")).substring("/chat/".length());
        Map<String, String> membersMap = _members.get(room);
        if (membersMap == null) {
            Map<String,String>new_room=new ConcurrentHashMap<String, String>();
            membersMap=_members.putIfAbsent(room,new_room);
            if (membersMap==null)
                membersMap=new_room;
        }
        
        String peerName = (String)data.get("peer");
        String peerId = membersMap.get(peerName);
        String rpos = (String)data.get("msgrpos");
//        System.out.println(rpos);
        
        if (peerId != null) {
            
         ServerSession peer = _bayeux.getSession(peerId);
            
            if (peer != null) {
             Map<String, Object> chat = new HashMap<String, Object>();
                String text = (String)data.get("chat");
                chat.put("chat", text);
                chat.put("user", data.get("user"));
                chat.put("scope", "private");
                chat.put("peer", peerName);
                String apmtdata = (String)data.get("apmtdata");
                chat.put("apmtdata", apmtdata);
                chat.put("msgrpos", rpos);
               
                //Save Chat
                Connection connection = null;
        		try{
        			connection = Connection_provider.getconnection();
        			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
        			InstantMsg instantMsg = new InstantMsg();
        			String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
        			String datetemp[] = currentDate.split(" ");
        			String temp1[] = datetemp[0].split("-");
        			String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
        			String time = (datetemp[2]+" "+datetemp[3]);
        			instantMsg.setDate(date);
        			instantMsg.setTime(time);
        			instantMsg.setReceiverUserId(peerName);
        			instantMsg.setSenderUserId(loginInfo.getUserId());
        			instantMsg.setChatText(text);
        			instantMsg.setSenderId(loginInfo.getId());

        			int result = instantMsgDAO.saveChatMessage(instantMsg);
        		}
        		catch(Exception e){
        			e.printStackTrace();
        		}
                
                
                //seting dumy data
                chat.put("myname", "manoj d. mishra");
                
                ServerMessage.Mutable forward = _bayeux.newMessage();
                forward.setChannel("/chat/" + room);
                forward.setId(message.getId());
                forward.setData(chat);

                if (text.lastIndexOf("lazy") > 0) {
                    forward.setLazy(true);
                }
                if (peer != client) {
                    peer.deliver(_session, forward);
                }
                client.deliver(_session, forward);
            }
        }
    }

    class BadWordFilter extends JSONDataFilter {
        @Override
        protected Object filterString(String string) {
            if (string.indexOf("dang") >= 0) {
                throw new DataFilter.Abort();
            }
            return string;
        }
    }
}