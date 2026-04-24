package com.apm.InstantMessage.eu.bi;

import java.util.ArrayList;

import com.apm.InstantMessage.eu.entity.InstantMsg;

public interface InstantMsgDAO {

	ArrayList<InstantMsg> getAllContacts();

	String getUserEmailId(int id);

	ArrayList<InstantMsg> getAllInbox(int id);

	int saveSendMessage(InstantMsg instantMsg);

	int saveEmailAttachmentFiles(int id, String fileName);

	int getRecieverId(String string);

	int saveInbox(InstantMsg instantMsg);

	ArrayList<InstantMsg> getsendMailList(int id);

	InstantMsg getInboxMailDetails(int id);

	InstantMsg getSentMailDetails(int id);

	int updateStatus(int id);

	ArrayList<InstantMsg> getAttachmentList(int id);

	int getSendMailId(int id);

	int getUnreadMailTotal(int id);

	int saveDraft(InstantMsg instantMsg);

	ArrayList<InstantMsg> getDraftmail(int id);

	int saveDraftAttachmentFiles(int id, String fileName);

	ArrayList<InstantMsg> getDraftAttachmentList(int id);

	int deleteDraftMail(int id);

	int deleteDraftMailattachments(int id);

	int saveChatMessage(InstantMsg instantMsg);

	ArrayList<InstantMsg> getChatList(int id);

	ArrayList<InstantMsg> getChatConversation(int id, String senderUserId,
			String receiverUserId);

	int saveActivity(int clientId, int activity);

	

}
