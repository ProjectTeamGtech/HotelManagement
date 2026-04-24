package com.apm.Master.eu.bi;

import java.util.ArrayList;
import java.util.List;

import com.apm.Ipd.eu.entity.Bams;
import com.apm.Master.eu.entity.Master;
import com.apm.common.web.common.helper.LoginInfo;

public interface DischargeOutcomeDAO {

      	public ArrayList<Master> getAllDischargeOutcome();
      	public int addDischargeOutcome(Master master);
      	public Master getMaster(int id);
      	public int updateDischargeOutcome(Master master);
      	public int deleteDischargeOutcome(int id);
      	public ArrayList<Master> getMasterList(LoginInfo loginInfo);
		public ArrayList<Master> getNewChargeTypeList();
		public ArrayList<Master> getNewChargeTypeListProc();
		public ArrayList<Master> getMasterChargeTypeList();
		public ArrayList<Bams> getVitals(String fromdate, String todate, String ipdid);
		public Bams getKarmaproceduredata(String clientid, String apmtId);
}
