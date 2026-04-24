package com.apm.Master.web.form;

public class NotificationForm {

	// apmt confirmation
	
		private boolean apmtConfirmationUser = true;
		
		private boolean apmtConfirmationPractitioner;
		
		private boolean apmtConfirmationAdmin;
		
		private boolean apmtConfirmationEmail;
		
		private boolean apmtConfirmationSms;
		
		// apmt cancell
		
		private boolean apmtCancelUser;
		
		private boolean apmtCancelPractitioner;
		
		private boolean apmtCancelAdmin;
		
		private boolean apmtCancelEmail;
		
		private boolean apmtCancelSms;
		
		private String mastername;
		
		
		
		// apmt reminder1
		
		public String getMastername() {
			return mastername;
		}
		public void setMastername(String mastername) {
			this.mastername = mastername;
		}
		private String apmtReminder1;
		
		private String apmtReminder1User;
		private String apmtReminder1Practitioner;
		private String apmtReminder1Admin;
		private String apmtReminder1Email;
		private String apmtReminder1Sms;
		
		// apmt reminder2
		private String apmtReminder2;
		
		private String apmtReminder2User;
		private String apmtReminder2Practitioner;
		private String apmtReminder2Admin;
		private String apmtReminder2Email;
		private String apmtReminder2Sms;
		
		//feedback for
		private String locationString;
		private String practitionerString;
		private String apmtTypeString;
		private String conditionString;
		
		private boolean feedbackUser;
		private boolean feedbackPractitioner;
		private boolean feedbackAdmin;
		private boolean feedbackEmail;
		private boolean feedbackSms;
		
		//on discharge
		private boolean dischargeUser;
		private boolean dischargePractitioner;
		private boolean dischargeAdmin;
		private boolean dischargeEmail;
		private boolean dischargeSms;
		
		//on report
		
		private boolean reportUser;
		private boolean reportPractitioner;
		private boolean reportAdmin;
		private boolean reportEmail;
		private boolean reportSms;
		public boolean isApmtConfirmationUser() {
			return apmtConfirmationUser;
		}
		public void setApmtConfirmationUser(boolean apmtConfirmationUser) {
			this.apmtConfirmationUser = apmtConfirmationUser;
		}
		public boolean isApmtConfirmationPractitioner() {
			return apmtConfirmationPractitioner;
		}
		public void setApmtConfirmationPractitioner(boolean apmtConfirmationPractitioner) {
			this.apmtConfirmationPractitioner = apmtConfirmationPractitioner;
		}
		public boolean isApmtConfirmationAdmin() {
			return apmtConfirmationAdmin;
		}
		public void setApmtConfirmationAdmin(boolean apmtConfirmationAdmin) {
			this.apmtConfirmationAdmin = apmtConfirmationAdmin;
		}
		public boolean isApmtConfirmationEmail() {
			return apmtConfirmationEmail;
		}
		public void setApmtConfirmationEmail(boolean apmtConfirmationEmail) {
			this.apmtConfirmationEmail = apmtConfirmationEmail;
		}
		public boolean isApmtConfirmationSms() {
			return apmtConfirmationSms;
		}
		public void setApmtConfirmationSms(boolean apmtConfirmationSms) {
			this.apmtConfirmationSms = apmtConfirmationSms;
		}
		public boolean isApmtCancelUser() {
			return apmtCancelUser;
		}
		public void setApmtCancelUser(boolean apmtCancelUser) {
			this.apmtCancelUser = apmtCancelUser;
		}
		public boolean isApmtCancelPractitioner() {
			return apmtCancelPractitioner;
		}
		public void setApmtCancelPractitioner(boolean apmtCancelPractitioner) {
			this.apmtCancelPractitioner = apmtCancelPractitioner;
		}
		public boolean isApmtCancelAdmin() {
			return apmtCancelAdmin;
		}
		public void setApmtCancelAdmin(boolean apmtCancelAdmin) {
			this.apmtCancelAdmin = apmtCancelAdmin;
		}
		public boolean isApmtCancelEmail() {
			return apmtCancelEmail;
		}
		public void setApmtCancelEmail(boolean apmtCancelEmail) {
			this.apmtCancelEmail = apmtCancelEmail;
		}
		public boolean isApmtCancelSms() {
			return apmtCancelSms;
		}
		public void setApmtCancelSms(boolean apmtCancelSms) {
			this.apmtCancelSms = apmtCancelSms;
		}
		public String getApmtReminder1() {
			return apmtReminder1;
		}
		public void setApmtReminder1(String apmtReminder1) {
			this.apmtReminder1 = apmtReminder1;
		}
		public String getApmtReminder1User() {
			return apmtReminder1User;
		}
		public void setApmtReminder1User(String apmtReminder1User) {
			this.apmtReminder1User = apmtReminder1User;
		}
		public String getApmtReminder1Practitioner() {
			return apmtReminder1Practitioner;
		}
		public void setApmtReminder1Practitioner(String apmtReminder1Practitioner) {
			this.apmtReminder1Practitioner = apmtReminder1Practitioner;
		}
		public String getApmtReminder1Admin() {
			return apmtReminder1Admin;
		}
		public void setApmtReminder1Admin(String apmtReminder1Admin) {
			this.apmtReminder1Admin = apmtReminder1Admin;
		}
		public String getApmtReminder1Email() {
			return apmtReminder1Email;
		}
		public void setApmtReminder1Email(String apmtReminder1Email) {
			this.apmtReminder1Email = apmtReminder1Email;
		}
		public String getApmtReminder1Sms() {
			return apmtReminder1Sms;
		}
		public void setApmtReminder1Sms(String apmtReminder1Sms) {
			this.apmtReminder1Sms = apmtReminder1Sms;
		}
		public String getApmtReminder2() {
			return apmtReminder2;
		}
		public void setApmtReminder2(String apmtReminder2) {
			this.apmtReminder2 = apmtReminder2;
		}
		public String getApmtReminder2User() {
			return apmtReminder2User;
		}
		public void setApmtReminder2User(String apmtReminder2User) {
			this.apmtReminder2User = apmtReminder2User;
		}
		public String getApmtReminder2Practitioner() {
			return apmtReminder2Practitioner;
		}
		public void setApmtReminder2Practitioner(String apmtReminder2Practitioner) {
			this.apmtReminder2Practitioner = apmtReminder2Practitioner;
		}
		public String getApmtReminder2Admin() {
			return apmtReminder2Admin;
		}
		public void setApmtReminder2Admin(String apmtReminder2Admin) {
			this.apmtReminder2Admin = apmtReminder2Admin;
		}
		public String getApmtReminder2Email() {
			return apmtReminder2Email;
		}
		public void setApmtReminder2Email(String apmtReminder2Email) {
			this.apmtReminder2Email = apmtReminder2Email;
		}
		public String getApmtReminder2Sms() {
			return apmtReminder2Sms;
		}
		public void setApmtReminder2Sms(String apmtReminder2Sms) {
			this.apmtReminder2Sms = apmtReminder2Sms;
		}
		public String getLocationString() {
			return locationString;
		}
		public void setLocationString(String locationString) {
			this.locationString = locationString;
		}
		public String getPractitionerString() {
			return practitionerString;
		}
		public void setPractitionerString(String practitionerString) {
			this.practitionerString = practitionerString;
		}
		public String getApmtTypeString() {
			return apmtTypeString;
		}
		public void setApmtTypeString(String apmtTypeString) {
			this.apmtTypeString = apmtTypeString;
		}
		public String getConditionString() {
			return conditionString;
		}
		public void setConditionString(String conditionString) {
			this.conditionString = conditionString;
		}
		public boolean isFeedbackUser() {
			return feedbackUser;
		}
		public void setFeedbackUser(boolean feedbackUser) {
			this.feedbackUser = feedbackUser;
		}
		public boolean isFeedbackPractitioner() {
			return feedbackPractitioner;
		}
		public void setFeedbackPractitioner(boolean feedbackPractitioner) {
			this.feedbackPractitioner = feedbackPractitioner;
		}
		public boolean isFeedbackAdmin() {
			return feedbackAdmin;
		}
		public void setFeedbackAdmin(boolean feedbackAdmin) {
			this.feedbackAdmin = feedbackAdmin;
		}
		public boolean isFeedbackEmail() {
			return feedbackEmail;
		}
		public void setFeedbackEmail(boolean feedbackEmail) {
			this.feedbackEmail = feedbackEmail;
		}
		public boolean isFeedbackSms() {
			return feedbackSms;
		}
		public void setFeedbackSms(boolean feedbackSms) {
			this.feedbackSms = feedbackSms;
		}
		public boolean isDischargeUser() {
			return dischargeUser;
		}
		public void setDischargeUser(boolean dischargeUser) {
			this.dischargeUser = dischargeUser;
		}
		public boolean isDischargePractitioner() {
			return dischargePractitioner;
		}
		public void setDischargePractitioner(boolean dischargePractitioner) {
			this.dischargePractitioner = dischargePractitioner;
		}
		public boolean isDischargeAdmin() {
			return dischargeAdmin;
		}
		public void setDischargeAdmin(boolean dischargeAdmin) {
			this.dischargeAdmin = dischargeAdmin;
		}
		public boolean isDischargeEmail() {
			return dischargeEmail;
		}
		public void setDischargeEmail(boolean dischargeEmail) {
			this.dischargeEmail = dischargeEmail;
		}
		public boolean isDischargeSms() {
			return dischargeSms;
		}
		public void setDischargeSms(boolean dischargeSms) {
			this.dischargeSms = dischargeSms;
		}
		public boolean isReportUser() {
			return reportUser;
		}
		public void setReportUser(boolean reportUser) {
			this.reportUser = reportUser;
		}
		public boolean isReportPractitioner() {
			return reportPractitioner;
		}
		public void setReportPractitioner(boolean reportPractitioner) {
			this.reportPractitioner = reportPractitioner;
		}
		public boolean isReportAdmin() {
			return reportAdmin;
		}
		public void setReportAdmin(boolean reportAdmin) {
			this.reportAdmin = reportAdmin;
		}
		public boolean isReportEmail() {
			return reportEmail;
		}
		public void setReportEmail(boolean reportEmail) {
			this.reportEmail = reportEmail;
		}
		public boolean isReportSms() {
			return reportSms;
		}
		public void setReportSms(boolean reportSms) {
			this.reportSms = reportSms;
		}
		
		
		
		
}
