package com.apm.Accounts.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.CashDesk;
import com.apm.common.utils.Pagination;

public interface CashDeskDAO {

	int getTotalCashDeskCount();

	ArrayList<CashDesk> getCashDeskList(Pagination pagination);

	int getTotalCashDeskCountOfSearch(int id, String searchClient);

	ArrayList<CashDesk> getCashDeskListOfSearch(int id, String searchClient,
			Pagination pagination);

	int saveCashDesk(CashDesk cashDesk);

	CashDesk getCashDeskData(int id);

	int updateCashdesk(int selectedid, int amount);

	int deleteCashDesk(int selectedid);

	boolean isClientIdExist(int clientId);

	int updateSaveCashdesk(CashDesk cashDesk);

	int saveCashDeskTransaction(int clientId, double payAmount1);

	int getCashdeskAmount(int clientId);

	int getTransactionCashdeskAmount(int clientId);

}
