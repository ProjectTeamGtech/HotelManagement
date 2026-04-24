package com.apm.Emr.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.a.a.a.a.a.a;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JDBCPrescriptionDAO implements PrescriptionDAO {

	Connection connection;

	public JDBCPrescriptionDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<Priscription> getAllPriscriptionList(Pagination pagination, String searchText, String fromdate,
			String todate, LoginInfo loginInfo, String phar_wardid, String filter_location,
			String filter_phar_location) {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			StringBuffer sql = new StringBuffer();
			/*
			 * if(searchText.equals("")){ sql.
			 * append("select id, clientid, practitionerid, conditionid, dosenotes, followupcount, followupstype, advoice, english, regional, hindi, prepay, postpay, other, lastmodified, ipdid,dstatus,billno,estimate,delivered,prisc_status,location_s,opdid from apm_client_parent_priscription where lastmodified between '"
			 * +fromdate+"' and '"+todate+"' order by id desc "); }else{ sql.
			 * append("select apm_client_parent_priscription.id, clientid, practitionerid, conditionid, dosenotes, followupcount, followupstype, advoice, english, regional, hindi, prepay, postpay, other, apm_client_parent_priscription.lastmodified, ipdid,dstatus,billno,estimate,delivered,prisc_status,location_s,opdid "
			 * );
			 * sql.append("from apm_client_parent_priscription inner join apm_patient on ");
			 * sql.append("apm_patient.id = apm_client_parent_priscription.clientid ");
			 * sql.append("where firstname like('%"+searchText+"') or surname like('"
			 * +searchText+"%') and apm_client_parent_priscription.lastmodified between '"
			 * +fromdate+"' and '"+todate+"' "); sql.append("order by id desc ");
			 * 
			 * }
			 */
			sql.append(
					"select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, apm_client_parent_priscription.practitionerid, apm_client_parent_priscription.conditionid, dosenotes, followupcount, ");
			sql.append(
					"followupstype, advoice, english, regional, hindi, prepay, postpay, other, apm_client_parent_priscription.lastmodified, ");
			sql.append("ipdid,dstatus,billno,estimate,delivered,prisc_status,location_s,opdid, ");
			sql.append(
					"concat(title,' ',apm_patient.firstname,' ',apm_patient.middlename,' ',surname),apm_patient.dob,gender,mobno, ");
			sql.append(
					"concat(initial,' ',apm_user.firstname,' ',lastname),apm_client_parent_priscription.userid,prisc_status,discharge,admission,whopay,prisc_delete,default_location,prisc_print_taken  ");
			sql.append("from apm_client_parent_priscription ");
			sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
			sql.append("left join apm_user on apm_user.id = apm_client_parent_priscription.practitionerid ");
			if (!phar_wardid.equals("0")) {
				sql.append(
						"inner join ipd_addmission_form on ipd_addmission_form.id = apm_client_parent_priscription.ipdid ");
			}
			sql.append("where apm_client_parent_priscription.lastmodified between '" + fromdate + "' and '" + todate
					+ "'  ");
			if (!searchText.equals("")) {
				/*
				 * sql.append("and (apm_patient.firstname like('%"
				 * +searchText+"') or surname like('"+searchText+"%'))  ");
				 */
				sql.append("and (apm_patient.firstname like ('" + searchText + "%') or apm_patient.surname like ('"
						+ searchText + "%') or apm_patient.abrivationid='" + searchText + "'   ");
				sql.append("or apm_patient.middlename like ('" + searchText + "%') or apm_patient.fullname like ('"
						+ searchText + "%') or apm_client_parent_priscription.id like ('" + searchText + "')) ");
			}
			if (!phar_wardid.equals("0")) {
				sql.append("and  ipd_addmission_form.wardid='" + phar_wardid + "' ");
			}
			if (!filter_location.equals("10")) {
				sql.append("and apm_client_parent_priscription.location_s='" + filter_location + "' ");
			}
			if (!filter_phar_location.equals("0")) {
				sql.append("and apm_client_parent_priscription.default_location='" + filter_phar_location + "' ");
			}
			sql.append("order by id desc ");
			String sql1 = pagination.getSQLQuery(sql.toString());
			PreparedStatement ps = connection.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			while (rs.next()) {

				Priscription priscription = new Priscription();

				int id = rs.getInt(1);

				/*
				 * int checkChargeRaised = checkChargeRaised(id);
				 * priscription.setCheckChargeRaised(checkChargeRaised);
				 * 
				 * int chargeid = getChargedInvoiceid(id); int checkInvoiceCreated =
				 * checkInvoiceCreated(chargeid);
				 * priscription.setCheckInvoiceCreated(checkInvoiceCreated);
				 */

				String clientid = rs.getString(2);

				/* Client client = clientDAO.getClientDetails(clientid); */

				/*
				 * String fullname = client.getTitle() + " " + client.getFirstName() + " " +
				 * client.getLastName();
				 */
				String fullname = rs.getString(24);
				String ageandgender = DateTimeUtils.getAge1(rs.getString(25)) + " / " + rs.getString(26);
				priscription.setFullname(fullname);
				priscription.setAgeandgender(ageandgender);
				if (rs.getString(33) != null) {
					if (rs.getString(33).equals("Client") || rs.getString(33).equals("Self")) {
						priscription.setWhopay("Self");
					} else {
						priscription.setWhopay("Third Party");
					}
				} else {
					priscription.setWhopay("Self");
				}

				priscription.setMobile(rs.getString(27));

				String practitionerid = rs.getString(3);

				/*
				 * String practitionername=userProfileDAO.getUserprofileDetails(rs.getInt(3)).
				 * getFullname();
				 */
				String practitionername = rs.getString(28);
				priscription.setPractitionername(practitionername);
				String conditionid = rs.getString(4);
				String dosenotes = rs.getString(5);
				String folloupcount = rs.getString(6);
				String followupstype = rs.getString(7);
				String advoice = rs.getString(8);
				String english = rs.getString(9);
				String regional = rs.getString(10);
				String hindi = rs.getString(11);
				String prepay = rs.getString(12);
				String postpay = rs.getString(13);
				String other = rs.getString(14);

				String lastmodified = "";
				if (rs.getString(15) != null) {
					lastmodified = DateTimeUtils.getDBDate(rs.getString(15));
				}

				String ipdid = rs.getString(16);
				boolean daycare = bedDao.isDayCare(ipdid);
				if (rs.getInt(16) > 0) {
					priscription.setIpdid(ipdid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					String wardname = ipdDAO.getIpdWardName(bed.getWardid());
					priscription.setWardname(wardname);
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					priscription.setBedname(bedname);
				} else {
					priscription.setIpdid("0");
				}

				String dstatus = rs.getString(17);
				priscription.setDstatus(dstatus);
				priscription.setBillno(rs.getString(18));
				priscription.setEstimate(rs.getInt(19));
				priscription.setDelivered(rs.getInt(20));
				priscription.setPrisc_status(rs.getString(21));
				priscription.setUserid(rs.getString(29));
				priscription.setId(id);
				priscription.setClientId(clientid);
				priscription.setPrectionerid(practitionerid);
				priscription.setConditionid(conditionid);
				priscription.setDosenotes(dosenotes);
				priscription.setFollowupsqty(folloupcount);
				priscription.setFollowupstype(followupstype);
				priscription.setAdvoice(advoice);
				priscription.setEnglish(english);
				priscription.setRegional(regional);
				priscription.setHindi(hindi);
				priscription.setPrepay(prepay);
				priscription.setPostpay(postpay);
				priscription.setOtherpay(other);
				priscription.setLastmodified(lastmodified);
				boolean shownewrequest = true;
				if (loginInfo.isBalgopal()) {
					if (rs.getInt(31) == 0 && rs.getInt(32) == 0) {
						int count = getNewPriscRequestedCount(id, false);
						priscription.setCount(count);
						int isdeliverstaus = getNewPriscDeliverStatus(id, false);
						if (count > 1) {
							isdeliverstaus = 2;
						}
						priscription.setIsdeliverstaus(isdeliverstaus);
					} else {
						shownewrequest = false;
					}
				} else {
					int count = getNewPriscRequestedCount(id, false);
					priscription.setCount(count);
					int isdeliverstaus = getNewPriscDeliverStatus(id, false);
					if (count > 1) {
						isdeliverstaus = 2;
					}
					priscription.setIsdeliverstaus(isdeliverstaus);
				}
				priscription.setShownewrequest(shownewrequest);
				/*
				 * int count = getNewPriscRequestedCount(id,loginInfo.isBalgopal());
				 * priscription.setCount(count); int isdeliverstaus =
				 * getNewPriscDeliverStatus(id,loginInfo.isBalgopal());
				 * priscription.setIsdeliverstaus(isdeliverstaus);
				 */

				int location = rs.getInt(22);
				if (location == 0) {
					if (daycare) {
						priscription.setLocation("DayCare");
					} else {
						priscription.setLocation("IPD");
					}

				} else if (location == 2) {
					priscription.setLocation("OT");
				} else {
					priscription.setLocation("OPD");
				}
				int deliverystatus = getNewPriscDeliverStatus(id, false);
				priscription.setDeliverystatus(deliverystatus);
				priscription.setNew_prisc_status(rs.getInt(30));
				priscription.setDeleted(rs.getInt(34));
				int prisc_print_taken = 2;
				int locationid = getPriscLastLocationId(id);
				if (locationid == 0) {
					locationid = rs.getInt(35);
				}
				// int locationid = rs.getInt(35);
				if (loginInfo.isBalgopal()) {
					/* if(locationid==33){ */
					if (rs.getInt(36) == 1) {
						prisc_print_taken = 1;
						priscription.setIsdeliverstaus(3);
					} else {
						prisc_print_taken = 0;
					}
					/* } */
				}
				String req_location = pharmacyDAO.getLocationName("" + locationid);
				priscription.setReq_location(req_location);
				priscription.setPrisc_print_taken(prisc_print_taken);
				priscriptions.add(priscription);
			}

			/*
			 * ArrayList<Priscription>
			 * externalClientList=getExternalClientData(fromdate,todate);
			 * priscriptions.addAll(externalClientList);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;
	}

	public int getPriscLastLocationId(int id) {
		int res = 0;
		try {
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select default_location_new from apm_parent_prisc ");
			buffer.append(
					"inner join apm_client_parent_priscription on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
			buffer.append("where oldparentid='" + id + "' ");
			/*
			 * buffer.
			 * append("group by apm_client_parent_priscription.id order by apm_parent_prisc.id desc limit 1 "
			 * );
			 */
			buffer.append("order by apm_parent_prisc.id desc limit 1 ");
			sql = buffer.toString();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getNewPriscDeliverStatus(int id, boolean balgopal) {
		int res = 0;
		try {
			String sql = "";
			if (balgopal) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select status from apm_parent_prisc ");
				buffer.append(
						"inner join apm_client_parent_priscription on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				buffer.append("where oldparentid='" + id + "' and status=1 ");
				buffer.append(
						"and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				buffer.append("group by apm_client_parent_priscription.id ");
				sql = buffer.toString();
			} else {
				sql = "select status from apm_parent_prisc where oldparentid='" + id + "' and status=1 limit 1";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int getNewPriscRequestedCount(int id, boolean balgoapal) {
		int res = 0;
		try {
			String sql = "";
			if (balgoapal) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from apm_parent_prisc ");
				buffer.append(
						"inner join apm_client_parent_priscription on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				buffer.append("where oldparentid='" + id + "' and directtransfer=1 ");
				buffer.append(
						"and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				buffer.append("group by apm_client_parent_priscription.id ");
				sql = buffer.toString();
			} else {
				sql = "select count(id) from apm_parent_prisc where oldparentid='" + id + "' and directtransfer=1  ";
			}

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Priscription> getAllPriscriptionListWithoutPagination(String fromdate, String todate) {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();

		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			StringBuffer sql = new StringBuffer();

			sql.append(
					"select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, apm_client_parent_priscription.practitionerid, apm_client_parent_priscription.conditionid, apm_client_parent_priscription.dosenotes, apm_client_parent_priscription.followupcount, apm_client_parent_priscription.followupstype, apm_client_parent_priscription.advoice, apm_client_parent_priscription.english, apm_client_parent_priscription.regional, apm_client_parent_priscription.hindi, apm_client_parent_priscription.prepay, apm_client_parent_priscription.postpay, apm_client_parent_priscription.other, apm_client_parent_priscription.lastmodified,apm_client_parent_priscription.ipdid,apm_client_parent_priscription.dstatus,apm_client_parent_priscription.billno,apm_client_parent_priscription.estimate,apm_client_parent_priscription.delivered,apm_patient.abrivationid,apm_client_parent_priscription.deliverydate from apm_client_parent_priscription inner join apm_patient on apm_patient.id=apm_client_parent_priscription.clientid where apm_client_parent_priscription.lastmodified between '"
							+ fromdate + "' and '" + todate + "' order by apm_client_parent_priscription.id desc ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Priscription priscription = new Priscription();

				int id = rs.getInt(1);

				int checkChargeRaised = checkChargeRaised(id);
				priscription.setCheckChargeRaised(checkChargeRaised);

				int chargeid = getChargedInvoiceid(id);
				int checkInvoiceCreated = checkInvoiceCreated(chargeid);
				priscription.setCheckInvoiceCreated(checkInvoiceCreated);

				String clientid = rs.getString(2);
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);

				String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				String ageandgender = getAge(client.getDob()) + " / " + client.getGender();
				priscription.setFullname(fullname);
				priscription.setAgeandgender(ageandgender);
				priscription.setWhopay(client.getWhopay());
				priscription.setMobile(client.getMobNo());

				String practitionerid = rs.getString(3);

				String practitionername = userProfileDAO.getUserprofileDetails(rs.getInt(3)).getFullname();
				priscription.setPractitionername(practitionername);
				String conditionid = rs.getString(4);
				String dosenotes = rs.getString(5);
				String folloupcount = rs.getString(6);
				String followupstype = rs.getString(7);
				String advoice = rs.getString(8);
				String english = rs.getString(9);
				String regional = rs.getString(10);
				String hindi = rs.getString(11);
				String prepay = rs.getString(12);
				String postpay = rs.getString(13);
				String other = rs.getString(14);

				String lastmodified = "";
				String deliverydate = "";
				String lastmodified1 = "";
				String deliverydate1 = "";
				if (rs.getString(15) != null) {
					lastmodified = DateTimeUtils.getDBDate(rs.getString(15));
					lastmodified1 = rs.getString(15);
				}
				if (rs.getString(22) != null) {
					deliverydate = DateTimeUtils.getDBDate(rs.getString(22));
					priscription.setDeliverydate(deliverydate);
					deliverydate1 = rs.getString(22);
				} else {
					priscription.setDeliverydate("");

				}

				if (!lastmodified.equals("") && !deliverydate.equals("")) {
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

					simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("GMT"));
					Date date1 = simpleDateFormat1.parse(lastmodified1);
					Date date2 = simpleDateFormat1.parse(deliverydate1);
					// in milliseconds
					long diff = date2.getTime() - date1.getTime();
//			            System.out.println("Difference : "+diff);
					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					priscription.setAveragetime(diffDays + " days " + diffHours + " Hr");

					/*
					 * SimpleDateFormat format = new SimpleDateFormat("MM-dd/yyyy HH:mm:ss"); Date
					 * d1 = format.parse(lastmodified); Date d2 = format.parse(deliverydate); long
					 * diff = d2.getTime() - d1.getTime();
					 * 
					 * long diffSeconds = diff / 1000 % 60; long diffMinutes = diff / (60 * 1000) %
					 * 60; long diffHours = diff / (60 * 60 * 1000) % 24; long diffDays = diff / (24
					 * * 60 * 60 * 1000); priscription.setAveragetime(diffDays+" days"
					 * +diffHours+" Hr");
					 */

				} else {
					priscription.setAveragetime("");
				}

				String ipdid = rs.getString(16);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				Bed bed = bedDao.getEditIpdData(ipdid);

				String dstatus = rs.getString(17);

				if (dstatus.equals("0")) {
					priscription.setDstatus("Not Delivered");
				} else {
					priscription.setDstatus("Delivered");
				}

				priscription.setBillno(rs.getString(18));
				priscription.setEstimate(rs.getInt(19));
				priscription.setDelivered(rs.getInt(20));

				priscription.setAbrivationid(rs.getString(21));

				if (ipdid == null || ipdid.equals("0")) {
					priscription.setIpdid("0");
				} else {
					priscription.setIpdid(ipdid);
				}

				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				priscription.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				priscription.setBedname(bedname);

				priscription.setWardbed(wardname + "/" + bedname);

				priscription.setId(id);
				priscription.setClientId(clientid);
				priscription.setPrectionerid(practitionerid);
				priscription.setConditionid(conditionid);
				if (dosenotes.equals("0")) {
					priscription.setDosenotes("");
				} else {
					priscription.setDosenotes(dosenotes);
				}

				priscription.setFollowupsqty(folloupcount);
				priscription.setFollowupstype(followupstype);
				priscription.setAdvoice(advoice);
				priscription.setEnglish(english);
				priscription.setRegional(regional);
				priscription.setHindi(hindi);
				priscription.setPrepay(prepay);
				priscription.setPostpay(postpay);
				priscription.setOtherpay(other);
				priscription.setLastmodified(lastmodified);
				priscriptions.add(priscription);
			}

			/*
			 * ArrayList<Priscription>
			 * externalClientList=getExternalClientData(fromdate,todate);
			 * priscriptions.addAll(externalClientList);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;
	}

	public ArrayList<Priscription> getExternalClientData(Pagination pagination, String fromdate, String todate,
			String location, String searchtext, String paymode, LoginInfo loginInfo, String isreturn) {

		ArrayList<Priscription> list = new ArrayList<Priscription>();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		try {
			StringBuffer buffer = new StringBuffer();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			buffer.append("select apm_pharmacy_client.id,apm_medicine_bill.id, fullname, address,  ");
			buffer.append("reference, age, gender, apm_medicine_bill.date,apm_pharmacy_client.deliverstatus,");
			buffer.append("mobile,apm_medicine_bill.isreturn,apm_medicine_bill.balance,apm_medicine_bill.debit,");
			buffer.append("retunbillid,apm_medicine_bill.time,apm_medicine_bill.refundamt,deleted,");
			buffer.append("apm_medicine_bill.discount,apm_medicine_payment.paymode,apm_medicine_bill.refundid,");
			buffer.append("dummybillno,discount_status,abrivationid,edited  ");
			buffer.append("from apm_pharmacy_client inner join apm_medicine_bill on ");
			buffer.append("apm_medicine_bill.pclientid=apm_pharmacy_client.id ");
			buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_bill.date between '" + fromdate + "' and '" + todate + "' ");
			if (!location.equals("0")) {
				buffer.append("and apm_medicine_bill.location='" + location + "'  ");
			}

			if (searchtext != null) {
				buffer.append("and (apm_pharmacy_client.fullname like ('%" + searchtext
						+ "%') or apm_pharmacy_client.fullname like ('%" + searchtext + "') or apm_medicine_bill.id='"
						+ searchtext + "'  or apm_medicine_bill.refundid='" + searchtext
						+ "' or apm_pharmacy_client.abrivationid='" + searchtext + "') ");
			}

			if (isreturn.equals("1")) {
				buffer.append("and apm_medicine_bill.isreturn=1 ");
			} else if (isreturn.equals("2")) {
				buffer.append("and apm_medicine_bill.isreturn=0 ");
			}

			if (!paymode.equals("0")) {
				buffer.append("and apm_medicine_payment.paymode='" + paymode + "' ");
			}

			buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
			buffer.append("group by apm_medicine_bill.id desc ");
			String sql = "";
			if (pagination != null) {
				sql = pagination.getSQLQuery(buffer.toString());
			} else {
				sql = buffer.toString();
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double totalReceived = 0;
			double totalRefund = 0;
			double totalBalance = 0;

			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setIpdid("0");
				priscription.setPclientid("" + rs.getInt(1) + "");
				priscription.setBillno(rs.getString(2));

				double payamt = pharmacyDAO.getPayAmount(rs.getInt(2));
				// set userid
				String userid = getuseridbybillno(rs.getString(2));
				priscription.setUserid(userid);

				priscription.setFullname(rs.getString(3));
				priscription.setAddress(rs.getString(4));
				priscription.setPractitionername(rs.getString(5));
				String agegender = rs.getString(6) + " " + rs.getString(7);
				priscription.setAgeandgender(agegender);
				String dateandtime = DateTimeUtils.getCommencingDate1(rs.getString(8)) + " " + rs.getString(15);
				priscription.setLastmodified(dateandtime);
				priscription.setDelivered(rs.getInt(9));
				priscription.setMobile(rs.getString(10));
				priscription.setReturnbill(rs.getInt(11));
				priscription.setBalance(rs.getString(12));

				priscription.setPayment(DateTimeUtils.changeFormat(payamt));

				if (paymode.equals("Credit")) {
					priscription.setNewpaymentmode(rs.getString(19));
				} else {
					priscription.setNewpaymentmode(pharmacyDAO.getBillPaymodeNew(rs.getInt(2)));
				}

				priscription.setTotal(rs.getDouble(13));
				if (rs.getInt(11) > 0 && rs.getDouble(13) == 0) {
					priscription.setTotal(rs.getDouble(16));
				}
				priscription.setReturnbillid(rs.getInt(14));
				priscription.setDeleted(rs.getInt(17));
				priscription.setDiscount(rs.getDouble(18));
				priscription.setOutp(1);

				// 14 July 2018
				int isdeletable = 0;
				if (rs.getInt(11) > 0) {
					totalRefund = totalRefund + rs.getDouble(13);
				} else {
					isdeletable = pharmacyDAO.checkbillalreadydeletedornot(rs.getInt(2));
				}
				priscription.setIsdeletable(isdeletable);
				totalReceived = totalReceived + payamt;
				totalBalance = totalBalance + rs.getDouble(12);
				priscription.setTotalBalance(totalBalance);
				priscription.setTotalReceived(totalReceived);
				priscription.setTotalrefund(totalRefund);

				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				// lokesh 17-aug
				if (priscription.getDate() != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					String checkdate = dateFormat.format(cal.getTime());
					if (priscription.getDate().equals(checkdate) || loginInfo.isPharm_print_backdate()) {
						priscription.setPrintflag(true);
					} else {
						priscription.setPrintflag(false);
					}
				}
				if (rs.getInt(20) > 0) {
					priscription.setRefundid(rs.getInt(20));
				} else {
					priscription.setRefundid(rs.getInt(2));
				}
				priscription.setDummybillno(rs.getInt(21));
				priscription.setDiscount_status(rs.getInt(22));
				priscription.setAbrivationid(rs.getString(23));
				priscription.setEdited(rs.getInt(24));

				if (DateTimeUtils.numberCheck(priscription.getAbrivationid()).equals("0")) {
					priscription.setAbrivationid("");
				}

				// delete, balance
				if (rs.getInt(17) == 0 && rs.getDouble(12) > 0) {
					// discount
					if (rs.getDouble(18) > 0) {

					} else if (rs.getInt(22) == 0) {
						// request status 1
						int res = pharmacyDAO.checkProductReturnAgainstBill(rs.getString(2));
						if (res == 2) {
							priscription.setDiscount_status(3);
						}
					}
				}

				int sameeditdate = 0;
				if (date.equals(rs.getString(8))) {
					sameeditdate = 1;
				}
				priscription.setSameeditdate(sameeditdate);

				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private int checkInvoiceCreated(int chargeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT * FROM apm_charges_assesment where invoiceid = " + chargeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getChargedInvoiceid(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_invoice where gpriscid = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int checkChargeRaised(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT * FROM apm_invoice where gpriscid = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getAge(String dob) {
		int age = 0;
		try {

			String arr[] = new String[3];

			int i = -1;
			for (String str : dob.split("/")) {
				arr[++i] = str;
			}
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			age = year - Integer.parseInt(arr[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf(age);
	}

	public int getAllPriscriptionCount(String searchText, String fromdate, String todate, String location) {

		int result = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from apm_client_parent_priscription";

			StringBuffer sql = new StringBuffer();
			if (searchText == null) {
				sql.append("select count(*) from apm_client_parent_priscription where lastmodified between '" + fromdate
						+ "' and '" + todate + "' ");
			} else {
				sql.append("select count(*) ");
				sql.append("from apm_client_parent_priscription inner join apm_patient on ");
				sql.append("apm_patient.id = apm_client_parent_priscription.clientid ");
				sql.append("where firstname like('%" + searchText
						+ "') and apm_client_parent_priscription.lastmodified between '" + fromdate + "' and '" + todate
						+ "' ");
			}

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

			int temp = getCountExternal(location, fromdate, todate);
			int r = getCountInternal(location, fromdate, todate);
			result = result + temp + r;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

//	//@ruchi get total delivered/notdelivered prescription by sort date for MIS presctiption report
	public int getAllPriscriptionCountWithoutLocation(String fromdate, String todate) {

		int result = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from apm_client_parent_priscription";

			StringBuffer sql = new StringBuffer();

			sql.append("select count(*) from apm_client_parent_priscription where lastmodified between '" + fromdate
					+ "' and '" + todate + "' ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	// @ruchi get delivered prescription by sort date for MIS presctiption report
	public int getAllPriscriptionCountDelivered(String fromdate, String todate) {

		int result = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from apm_client_parent_priscription";

			StringBuffer sql = new StringBuffer();

			sql.append("select count(*) from apm_client_parent_priscription where dstatus=1 and lastmodified between '"
					+ fromdate + "' and '" + todate + "' ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	// @ruchi get not delivered prescription by sort date for MIS presctiption
	// report
	public int getAllPriscriptionCountNotDelivered(String fromdate, String todate) {

		int result = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from apm_client_parent_priscription";

			StringBuffer sql = new StringBuffer();

			sql.append("select count(*) from apm_client_parent_priscription where dstatus=0 and lastmodified between '"
					+ fromdate + "' and '" + todate + "' ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getDstatus(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT dstatus FROM apm_client_parent_priscription where id=" + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				if (result == 0) {
					result = 1;
				} else {
					result = 0;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateDstatus(String id, int status, String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_parent_priscription set dstatus=" + status + ",deliverydate='" + date
				+ "' where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Priscription> getchargedMdicineList(String selectedid) {
		PreparedStatement preparedStatement = null;

		PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		String sql = "SELECT clientid,dose,days,mdicineid,notes,mdicinename FROM apm_client_priscription where parentid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setClientId(rs.getString(1));
				priscription.setDosage(rs.getString(2));
				priscription.setDays(rs.getInt(3));
				priscription.setMdicinenameid(rs.getString(4));
				priscription.setDosagenote(rs.getString(5));

				Priscription master = prescriptionMasterDAO.getPrescriptionDetails(rs.getString(4));
				if (master.getDrug() == null) {
					priscription.setDrug(rs.getString(6));
				} else {
					priscription.setDrug(master.getDrug());
				}

				list.add(priscription);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public Product getProductDetails(String mdicinenameid) {
		PreparedStatement preparedStatement = null;
		Product product = new Product();
		String sql = "SELECT prodname,saleprice,stock,id FROM inventory_product where mdicinenameid = " + mdicinenameid
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				product.setProduct_name(rs.getString(1));
				product.setSale_price(rs.getString(2));
				product.setStock(rs.getString(3));
				product.setId(rs.getInt(4));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return product;
	}

	public int updateMedicineStatus(String treatmentepisodeid, String datetime, String mstatus) {
		int result = 0;
		try {

			if (mstatus.equals("0")) {

				String sql2 = "update apm_treatment_episode set dis_mdicine_status=?,dis_mdicine_time=? where id="
						+ treatmentepisodeid + "";
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setInt(1, 1);
				ps1.setString(2, datetime);

				result = ps1.executeUpdate();
			} else {

				String sql2 = "update apm_treatment_episode set dis_mdicine_status=?,dis_mdicine_time=? where id="
						+ treatmentepisodeid + "";
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setInt(1, 0);
				ps1.setString(2, null);

				result = ps1.executeUpdate();
			}
			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(treatmentepisodeid);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Priscription> getAllPharmacyList(Pagination pagination, String searchText, String fromdate,
			String todate, String ipdloc, String opdloc, String location, LoginInfo loginInfo) {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			StringBuffer sql = new StringBuffer();
			/*
			 * if(searchText==null){ sql.
			 * append("select id, clientid, practitionerid, conditionid, dosenotes, followupcount, followupstype, advoice, english, regional, hindi, prepay, postpay, other, lastmodified, ipdid,dstatus,billno,estimate,delivered from apm_client_parent_priscription where lastmodified between '"
			 * +fromdate+"' and '"+todate+"' and billno=0 order by id desc "); }else{ sql.
			 * append("select apm_client_parent_priscription.id, clientid, practitionerid, conditionid, dosenotes, followupcount, followupstype, advoice, english, regional, hindi, prepay, postpay, other, apm_client_parent_priscription.lastmodified, ipdid,dstatus,billno,estimate,delivered "
			 * );
			 * sql.append("from apm_client_parent_priscription inner join apm_patient on ");
			 * sql.append("apm_patient.id = apm_client_parent_priscription.clientid ");
			 * sql.append("where firstname like('"+searchText+"%') or surname like('"
			 * +searchText+"%')or middlename like('"+searchText+"%') or fullname like('"
			 * +searchText+"%') and apm_client_parent_priscription.lastmodified between '"
			 * +fromdate+"' and '"+todate+"' and billno=0 ");
			 * sql.append("order by id desc "); }
			 */

			// 20 June 2018

			if (searchText == null) {
				sql.append(
						"select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, practitionerid, ");
				sql.append(
						"apm_client_parent_priscription.conditionid,dosenotes,followupcount, followupstype, advoice, english, ");
				sql.append(
						"regional, hindi,prepay, postpay, other, lastmodified, apm_client_parent_priscription.ipdid, ");
				sql.append(
						"dstatus,apm_client_parent_priscription.billno,estimate,delivered,location_s,apm_client_parent_priscription.userid ");
				sql.append("from apm_client_parent_priscription ");
				sql.append(
						"inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("where apm_parent_prisc.date between '" + fromdate + "' and '" + todate
						+ "' and apm_parent_prisc.billno='0' and apm_client_parent_priscription.prisc_delete=0 ");
				sql.append("and directtransfer=1 ");
				if (!location.equals("0")) {
					sql.append("and apm_parent_prisc.default_location_new='" + location + "' ");
				}
				if (loginInfo.isBalgopal()) {
					sql.append(
							"and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_parent_prisc.id desc ");
			} else if(loginInfo.getClinicid1().equals("chraibgh") || loginInfo.getClinicid1().equals("demo") || loginInfo.getClinicid1().equals("bams")){
				
				sql.append("select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, ");
				sql.append("practitionerid, apm_client_parent_priscription.conditionid,dosenotes,followupcount, ");
				sql.append("followupstype, advoice, english, regional, hindi,prepay, postpay, other, ");
				sql.append ("apm_client_parent_priscription.lastmodified, apm_client_parent_priscription.ipdid, ");
				sql.append ("dstatus,apm_client_parent_priscription.billno,estimate,delivered,location_s, ");
				sql.append ("apm_client_parent_priscription.userid from apm_client_parent_priscription inner join ");
				sql.append ("apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append ("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
				sql.append("where apm_parent_prisc.date between '" + fromdate + "' and '" + todate + "' ");
				sql.append("and (firstname like('" + searchText + "%') or surname like('" + searchText
						+ "%')or middlename like('" + searchText + "%') or fullname like('" + searchText
						+ "%') or abrivationid='" + searchText + "' or apm_client_parent_priscription.clientid='"+searchText+"') ");
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_parent_prisc.id desc ");
				
			}else{
				sql.append(
						"select apm_client_parent_priscription.id, apm_client_parent_priscription.clientid, practitionerid, ");
				sql.append(
						"apm_client_parent_priscription.conditionid,dosenotes,followupcount, followupstype, advoice, english, ");
				sql.append("regional, hindi,prepay, postpay, other, apm_client_parent_priscription.lastmodified, ");
				sql.append(
						"apm_client_parent_priscription.ipdid,dstatus,apm_client_parent_priscription.billno,estimate,delivered,location_s,apm_client_parent_priscription.userid ");
				sql.append("from apm_client_parent_priscription ");
				sql.append(
						"inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid ");
				sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
				sql.append("where apm_parent_prisc.date between '" + fromdate + "' and '" + todate
						+ "' and apm_parent_prisc.billno='0' and apm_client_parent_priscription.prisc_delete=0 ");
				/*
				 * sql.append("and firstname like('"+searchText+"%') or surname like('"
				 * +searchText+"%') or middlename like('"+searchText+"%') or fullname like('"
				 * +searchText+"%') or abrivationid='"+searchText+"' ");
				 */
				sql.append("and directtransfer=1 ");
				sql.append("and (firstname like('" + searchText + "%') or surname like('" + searchText
						+ "%')or middlename like('" + searchText + "%') or fullname like('" + searchText
						+ "%') or abrivationid='" + searchText + "' or apm_client_parent_priscription.clientid='"+searchText+"') ");
				if (!location.equals("0")) {
					sql.append("and apm_parent_prisc.default_location_new='" + location + "' ");
				}
				if (loginInfo.isBalgopal()) {
					sql.append(
							"and apm_client_parent_priscription.discharge=0 and apm_client_parent_priscription.admission=0 ");
				}
				sql.append("group by apm_client_parent_priscription.id ");
				sql.append("order by apm_parent_prisc.id desc ");
			}

			// String sql="select id, clientid, practitionerid, conditionid, dosenotes,
			// followupcount, followupstype, advoice, english, regional, hindi, prepay,
			// postpay, other, lastmodified, ipdid from apm_client_parent_priscription order
			// by id desc";
			String sql1 = pagination.getSQLQuery(sql.toString());
			PreparedStatement ps = connection.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Priscription priscription = new Priscription();
				int id = rs.getInt(1);
				int checkChargeRaised = checkChargeRaised(id);
				priscription.setCheckChargeRaised(checkChargeRaised);

				int chargeid = getChargedInvoiceid(id);
				int checkInvoiceCreated = checkInvoiceCreated(chargeid);
				priscription.setCheckInvoiceCreated(checkInvoiceCreated);

				String clientid = rs.getString(2);
				Client client = clientDAO.getClientDetails(clientid);

				String fullname = /*
									 * client.getTitle() + " " + client.getFirstName() + " " + client.getLastName()
									 */DateTimeUtils.getPatientFullname(client);
				String ageandgender = getAge(client.getDob()) + " / " + client.getGender();
				priscription.setFullname(fullname);
				priscription.setAgeandgender(ageandgender);

				if (client.getWhopay() != null) {
					if (client.getWhopay().equals("Client") || client.getWhopay().equals("Self")) {
						priscription.setWhopay("Self");
					} else {
						priscription.setWhopay("Third Party");
					}
				} else {
					priscription.setWhopay("Self");
				}
				// priscription.setWhopay(client.getWhopay());

				priscription.setMobile(client.getMobNo());
				priscription.setAbrivationid(client.getAbrivationid());

				String practitionerid = rs.getString(3);
				String practitionername = userProfileDAO.getUserprofileDetails(rs.getInt(3)).getFullname();
				priscription.setPractitionername(practitionername);
				String conditionid = rs.getString(4);
				String dosenotes = rs.getString(5);
				String folloupcount = rs.getString(6);
				String followupstype = rs.getString(7);
				String advoice = rs.getString(8);
				String english = rs.getString(9);
				String regional = rs.getString(10);
				String hindi = rs.getString(11);
				String prepay = rs.getString(12);
				String postpay = rs.getString(13);
				String other = rs.getString(14);

				String lastmodified = "";
				if (rs.getString(15) != null) {
					lastmodified = DateTimeUtils.getDBDate(rs.getString(15));
				}
				String ipdid = rs.getString(16);
				Bed bed = bedDao.getEditIpdData(ipdid);

				String dstatus = rs.getString(17);
				priscription.setDstatus(dstatus);
				priscription.setBillno(rs.getString(18));

				// set userid
				String userid = getuseridbybillno(rs.getString(18));
				priscription.setUserid(userid);

				priscription.setEstimate(rs.getInt(19));
				priscription.setDelivered(rs.getInt(20));
				priscription.setLocation("" + rs.getInt(21));
				String paymode = pharmacyDAO.getBillPaymode(rs.getInt(18));
				priscription.setPaymode(paymode);

				if (ipdid == null || ipdid.equals("0")) {
					priscription.setIpdid("0");
				} else {
					priscription.setIpdid(ipdid);
				}

				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				priscription.setWardname(wardname);

				String urgentward = checkICUorCasulty(bed.getWardid());
				if (!urgentward.equals("")) {
					priscription.setUrgentward("1");
				}

				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				priscription.setBedname(bedname);

				priscription.setId(id);
				priscription.setClientId(clientid);
				priscription.setPrectionerid(practitionerid);
				priscription.setConditionid(conditionid);
				priscription.setDosenotes(dosenotes);
				priscription.setFollowupsqty(folloupcount);
				priscription.setFollowupstype(followupstype);
				priscription.setAdvoice(advoice);
				priscription.setEnglish(english);
				priscription.setRegional(regional);
				priscription.setHindi(hindi);
				priscription.setOutp(0);
				priscription.setPrepay(prepay);
				priscription.setPostpay(postpay);
				priscription.setOtherpay(other);
				priscription.setLastmodified(lastmodified);
				priscription.setOpdipdprisc("1");
				/*
				 * if(ipdid==null || ipdid.equals("0")){
				 * 
				 * //opd if(opdloc==null){ priscriptions.add(priscription); } else
				 * if(opdloc.equals("0")) {
				 * 
				 * } else { priscriptions.add(priscription); } } else {
				 * 
				 * if(ipdloc==null){ priscriptions.add(priscription); } else
				 * if(ipdloc.equals("0")) {
				 * 
				 * } else { priscriptions.add(priscription); } }
				 */
				priscription.setRequestuserid(rs.getString(22));
				priscriptions.add(priscription);

			}

			/*
			 * ArrayList<Priscription>
			 * externalClientList=getExternalClientData(pagination,fromdate,todate,location,
			 * searchText); priscriptions.addAll(externalClientList);
			 * 
			 * ArrayList<Priscription> internalClientList =
			 * getInternalClientData(pagination,fromdate,todate,location,searchText);
			 * 
			 * ArrayList<Priscription> estimateClientList= getEstimateBills(pagination,
			 * fromdate, todate, location, searchText);
			 * 
			 * priscriptions.addAll(internalClientList);
			 * priscriptions.addAll(estimateClientList);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;
	}

	private String getuseridbybillno(String billno) {
		String userid = "";
		try {
			String sql = "select userid from apm_medicine_payment where billno='" + billno + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}

	public int getCountExternal(String location, String fromdate, String todate) {

		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();

			buffer.append("select count(*) ");
			buffer.append("from apm_pharmacy_client inner join apm_medicine_bill on ");
			buffer.append("apm_medicine_bill.pclientid=apm_pharmacy_client.id where ");
			buffer.append("apm_medicine_bill.date between '" + fromdate + "' and '" + todate
					+ "' and apm_medicine_bill.tpid='0' ");
			if (!location.equals("0")) {
				buffer.append("and apm_medicine_bill.location='" + location + "'  ");
			}

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getCountInternal(String location, String fromdate, String todate) {

		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();

			buffer.append("select count(*) ");
			buffer.append("from apm_patient inner join apm_medicine_bill on ");
			buffer.append("apm_medicine_bill.clientid=apm_patient.id where ");
			buffer.append("apm_medicine_bill.date between '" + fromdate + "' and '" + todate + "'  ");
			if (!location.equals("0")) {
				buffer.append("and apm_medicine_bill.location='" + location + "'  ");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Priscription> getInternalClientData(Pagination pagination, String fromdate, String todate,
			String location, String searchtext, String paymode, LoginInfo loginInfo, String isreturn) {
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		try {
			StringBuffer buffer = new StringBuffer();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			buffer.append(
					"select apm_patient.id,apm_medicine_bill.id, fullname, address, dob, gender, apm_medicine_bill.date,mobno,apm_medicine_bill.isreturn,apm_medicine_bill.balance,apm_medicine_bill.debit,apm_medicine_bill.time,apm_medicine_bill.userid,apm_medicine_bill.deleted,apm_patient.abrivationid,apm_medicine_bill.discount,apm_medicine_payment.paymode,apm_medicine_bill.refundid,apm_medicine_bill.phar_ipdid,apm_medicine_bill.oldparentid,dummybillno,discount_status,edited ");
			buffer.append("from apm_patient inner join apm_medicine_bill on ");
			buffer.append("apm_medicine_bill.clientid=apm_patient.id  ");
			buffer.append("left join apm_medicine_payment on apm_medicine_bill.id=apm_medicine_payment.billno ");
			buffer.append("where apm_medicine_bill.date between '" + fromdate + "' and '" + todate + " 23:59:59' ");
			if (!location.equals("0")) {
				buffer.append("and apm_medicine_bill.location='" + location + "'  ");
			}

			if (searchtext != null) {
				buffer.append(" and (apm_patient.firstname like ('" + searchtext + "%') or apm_patient.surname like ('"
						+ searchtext + "%') or apm_patient.abrivationid like ('" + searchtext
						+ "')  or apm_patient.middlename like ('" + searchtext + "%') or apm_patient.fullname like ('"
						+ searchtext + "%') or apm_medicine_bill.id like ('" + searchtext
						+ "') or apm_medicine_bill.refundid like ('" + searchtext + "')) ");
			}
			if (isreturn.equals("1")) {
				buffer.append("and apm_medicine_bill.isreturn=1 ");
			} else if (isreturn.equals("2")) {
				buffer.append("and apm_medicine_bill.isreturn=0 ");
			}

			if (!paymode.equals("0")) {
				buffer.append("and apm_medicine_payment.paymode='" + paymode + "' ");
			}

			buffer.append("and (apm_medicine_bill.tpid='0' || apm_medicine_bill.tpid is NULL) ");
			buffer.append("group by apm_medicine_bill.id desc;");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			double totalReceived = 0;
			double totalRefund = 0;
			double totalBalance = 0;
			while (rs.next()) {

				Priscription priscription = new Priscription();
				// priscription.setIpdid("0");
				priscription.setClientId(rs.getString(1));
				priscription.setBillno(rs.getString(2));

				double payamt = pharmacyDAO.getPayAmount(rs.getInt(2));
				// set userid
				String userid = getuseridbybillno(rs.getString(2));
				priscription.setUserid(userid);

				priscription.setFullname(rs.getString(3));
				priscription.setAddress(rs.getString(4));
				// priscription.setPractitionername(rs.getString(5));

				String agegender = getAge(rs.getString(5)) + " " + rs.getString(6);
				priscription.setAgeandgender(agegender);
				// priscription.setDelivered(rs.getInt(9));
				priscription.setMobile(rs.getString(8));
				priscription.setReturnbill(rs.getInt(9));
				priscription.setBalance(rs.getString(10));
				priscription.setPayment(DateTimeUtils.changeFormat(payamt));
				priscription.setTotal(rs.getDouble(11));
				String dateandtime = DateTimeUtils.getCommencingDate1(rs.getString(7)) + " " + rs.getString(12);
				priscription.setLastmodified(dateandtime);
				priscription.setOutp(0);
				priscription.setDelivered(1);
				priscription.setUserid(rs.getString(13));
				priscription.setDeleted(rs.getInt(14));
				priscription.setAbrivationid(rs.getString(15));
				priscription.setDiscount(rs.getDouble(16));
				// String ipdid = pharmacyDAO.getIpdIdFromClientID(rs.getInt(1));
				String ipdid = "" + rs.getInt(19);
				String pract_name = "";
				if (ipdid.equals("0")) {
					pract_name = pharmacyDAO.getappointmentinfo(rs.getInt(1));
					priscription.setReqfromlocation("1");
				} else {
					priscription.setReqfromlocation("0");
					Bed bed = bedDao.getEditIpdData(ipdid);

					pract_name = profileDAO.getFullName(bed.getPractitionerid());
					String wardname = ipdDAO.getIpdWardName(bed.getWardid());
					// @ruchi to check casualty and icu patient to blink
					String urgentward = checkICUorCasulty(bed.getWardid());

					if (!urgentward.equals("")) {
						priscription.setUrgentward("1");
					}
					// end
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					priscription.setWardname(wardname);
					priscription.setBedname(bedname);

				}
				if (rs.getInt(20) > 0) {
					String reqfromlocation = pharmacyDAO.getRequestFromPriscriptionBill(rs.getString(2));
					priscription.setReqfromlocation(reqfromlocation);
				}
				priscription.setIpdid(ipdid);
				priscription.setPractitionername(pract_name);

				// 14 July 2018
				int isdeletable = 0;
				if (rs.getInt(9) > 0) {
					totalRefund = totalRefund + rs.getDouble(11);
				} else {
					isdeletable = pharmacyDAO.checkbillalreadydeletedornot(rs.getInt(2));
				}
				priscription.setIsdeletable(isdeletable);
				totalReceived = totalReceived + payamt;
				totalBalance = totalBalance + rs.getDouble(10);
				priscription.setTotalBalance(totalBalance);
				priscription.setTotalReceived(totalReceived);
				priscription.setTotalrefund(totalRefund);

				if (paymode.equals("Credit")) {
					priscription.setNewpaymentmode(rs.getString(17));
				} else {
					priscription.setNewpaymentmode(pharmacyDAO.getBillPaymodeNew(rs.getInt(2)));
				}

				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(7)));
				// lokesh 17-aug
				if (priscription.getDate() != null) {

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();

					String checkdate = dateFormat.format(cal.getTime());
					if (priscription.getDate().equals(checkdate) || loginInfo.isPharm_print_backdate()) {
						priscription.setPrintflag(true);
					} else {
						priscription.setPrintflag(false);
					}
				}
				if (rs.getInt(18) > 0) {
					priscription.setRefundid(rs.getInt(18));
				} else {
					priscription.setRefundid(rs.getInt(2));
				}
				priscription.setDummybillno(rs.getInt(21));
				priscription.setDiscount_status(rs.getInt(22));
				priscription.setEdited(rs.getInt(23));
				// delete, balance
				if (rs.getInt(14) == 0 && rs.getDouble(10) > 0) {
					// discount
					if (rs.getDouble(16) > 0) {

					} else if (rs.getInt(22) == 0) {
						// request status 1
						int res = pharmacyDAO.checkProductReturnAgainstBill(rs.getString(2));
						if (res == 2) {
							priscription.setDiscount_status(3);
						}
					}
				}
				int sameeditdate = 0;
				if (date.equals(rs.getString(7))) {
					sameeditdate = 1;
				}
				priscription.setSameeditdate(sameeditdate);
				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Priscription> getEstimateBills(Pagination pagination, String fromdate, String todate,
			String location, String searchtext, LoginInfo loginInfo) {

		ArrayList<Priscription> list = new ArrayList<Priscription>();
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select apm_medicine_estimate_bill.id,apm_medicine_estimate_bill.date,apm_medicine_estimate_bill.debit,apm_medicine_estimate_bill.clientid,apm_medicine_estimate_bill.pclientid,isreturn,apm_medicine_estimate_bill.refundamt,balance,apm_medicine_estimate_payment.payment,apm_medicine_estimate_bill.time,retunbillid,apm_medicine_estimate_bill.userid,apm_medicine_estimate_payment.paymode ");
			buffer.append(
					"from apm_medicine_estimate_bill inner join apm_medicine_estimate_payment on apm_medicine_estimate_bill.id=apm_medicine_estimate_payment.billno  ");
			buffer.append("where apm_medicine_estimate_bill.date between '" + fromdate + "' and '" + todate + " 23:59:59' ");
			if (!location.equals("0")) {
				buffer.append("and apm_medicine_estimate_bill.location='" + location + "' ");
			}

			buffer.append(
					"and (apm_medicine_estimate_bill.tpid='0' || apm_medicine_estimate_bill.tpid is NULL) and isestimatecancel='0' ");
			buffer.append("group by apm_medicine_estimate_bill.id desc ");
			String sql = "";
			if (pagination != null) {
				sql = pagination.getSQLQuery(buffer.toString());
			} else {
				sql = buffer.toString();
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setBillno(rs.getString(1));
				priscription.setEstimatebill(rs.getInt(1));
				priscription.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				priscription.setDebit(rs.getDouble(3));
				priscription.setTotal(rs.getDouble(3));
				priscription.setClientId(rs.getString(4));
				priscription.setPclientid(rs.getString(5));
				priscription.setIsreturn(rs.getInt(6));
				priscription.setReturnbill(rs.getInt(6));
				double refundtot = rs.getDouble(7);
				priscription.setBalance(rs.getString(8));
				double payamt = rs.getDouble(9);
				priscription.setPayment(DateTimeUtils.changeFormat(payamt));
				String dateTime = priscription.getDate() + " " + rs.getString(10);
				priscription.setReturnbillid(rs.getInt(11));
				priscription.setUserid(rs.getString(12));
				priscription.setPaymode(rs.getString(13));
				priscription.setLastmodified(dateTime);
				priscription.setDelivered(1);

				if (priscription.getIsreturn() > 0 && rs.getDouble(3) == 0) {
					priscription.setTotal(refundtot);
				}

				if (rs.getInt(5) > 0) {
					// pharmacy patient
					Priscription masterClient = pharmacyDAO.getPharmacyPatient(priscription.getPclientid());
					priscription.setFullname(masterClient.getFullname());
					priscription.setMobile(masterClient.getMobile());
					priscription.setPractitionername(masterClient.getPractitionername());
					priscription.setAddress(masterClient.getAddress());
					priscription.setAgeandgender(masterClient.getAgeandgender());
					priscription.setOutp(1);

				} else {
					// ipd/opd patient
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					BedDao bedDao = new JDBCBedDao(connection);
					Client client = clientDAO.getClientDetails(priscription.getClientId());
					String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					priscription.setFullname(fullname);
					String agegender = DateTimeUtils.getAge(client.getDob()) + " " + client.getGender();
					priscription.setAgeandgender(agegender);
					String ipdid = pharmacyDAO.getIpdIdFromClientID(Integer.parseInt(priscription.getClientId()));
					String pract_name = "";
					if (ipdid.equals("0")) {
						pract_name = pharmacyDAO.getappointmentinfo(Integer.parseInt(priscription.getClientId()));
					} else {

						Bed bed = bedDao.getEditIpdData(ipdid);
						UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
						pract_name = profileDAO.getFullName(bed.getPractitionerid());
						// @ruchi to check casualty and icu patient to blink
						String urgentward = checkICUorCasulty(bed.getWardid());

						if (!urgentward.equals("")) {
							priscription.setUrgentward("1");
						}
						// end
					}
					priscription.setIpdid(ipdid);
					priscription.setPractitionername(pract_name);
					priscription.setAddress(client.getAddress());
					priscription.setMobile(client.getMobNo());
					priscription.setOutp(0);

				}

				// lokesh 17-aug
				if (priscription.getDate() != null) {

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();

					String checkdate = dateFormat.format(cal.getTime());
					if (priscription.getDate().equals(checkdate) || loginInfo.isPharm_print_backdate()) {
						priscription.setPrintflag(true);
					} else {
						priscription.setPrintflag(false);
					}
				}
				priscription.setEstimate(1);
				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private String checkICUorCasulty(String wardid) {
		String name = "";
		try {
			String sql = "select id from apm_ipd_ward where id='" + wardid
					+ "' and (wardname like('%ICU%') or wardname like('%CASUALTY%'))";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public ArrayList<Priscription> getPriscclientforApiList(String clientid) {

		ArrayList<Priscription> list = new ArrayList<Priscription>();
		try {

			String sql = "select id,lastmodified from apm_client_parent_priscription where clientid=" + clientid
					+ " order by id desc ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				String dateTime = DateTimeUtils.getDBDate(rs.getString(2));
				priscription.setDateTime(dateTime);

				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Priscription getPriscriptionParentData(String parentid) {

		Priscription priscription = new Priscription();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		try {

			String sql = "select practitionerid, lastmodified from apm_client_parent_priscription where id=" + parentid
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String fullname = userProfileDAO.getFullName(rs.getString(1));
				String dateTime = DateTimeUtils.getDBDate(rs.getString(2));

				priscription.setPractitionername(fullname);
				priscription.setDateTime(dateTime);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscription;
	}

	public ArrayList<Priscription> getMultiplePriscRequest(String id, String location) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			// String sql ="select id,date,status,billno from apm_parent_prisc where
			// oldparentid='"+id+"' order by id desc ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,date,status,billno,prisc_tokenno from apm_parent_prisc where oldparentid='" + id
					+ "' and directtransfer=1 ");
			if (!location.equals("0")) {
				buffer.append("and default_location_new='" + location + "' ");
			}
			buffer.append("order by id desc");

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				String[] datetime = rs.getString(2).split(" ");
				String date = DateTimeUtils.getCommencingDate1(datetime[0]) + " " + datetime[1];
				priscription.setDate(date);
				priscription.setStatus(rs.getString(3));
				priscription.setBillno(rs.getString(4));
				priscription.setTokenno(rs.getString(5));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Priscription> getMultiplePriscRequestNew(String id) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			String sql = "select id,date,status,billno,userid,default_location_new,prisc_tokenno from apm_parent_prisc where oldparentid='"
					+ id + "' and directtransfer=1  order by id desc ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				String[] datetime = rs.getString(2).split(" ");
				String date = DateTimeUtils.getCommencingDate1(datetime[0]) + " " + datetime[1];
				priscription.setDate(date);
				priscription.setStatus(rs.getString(3));
				priscription.setBillno(rs.getString(4));
				priscription.setUserid(rs.getString(5));
				priscription.setReq_location(pharmacyDAO.getLocationName(rs.getString(6)));
				priscription.setTokenno(rs.getString(7));
				ArrayList<Priscription> childprisclist = getChildPriscData(rs.getInt(1));
				priscription.setChildprisclist(childprisclist);
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Priscription> getChildPriscData(int int1) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select apm_child_prisc.id,medicinename,medicineid,qty,date,apm_parent_prisc.userid from apm_child_prisc ");
			buffer.append("inner join apm_parent_prisc on apm_parent_prisc.id =apm_child_prisc.parentid ");
			buffer.append("where parentid=" + int1 + " ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setMdicinenameid(rs.getString(3));
				priscription.setPriscqty("" + rs.getInt(4));
				priscription.setUserid(rs.getString(6));
				// for save and print
				priscription.setNewqty(rs.getInt(4));
				priscription.setPriscdays("0");
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int getAllPriscriptionCountNew(String searchText, String fromdate, String todate, String string,
			String phar_wardid, String filter_location, String filter_phar_location) {
		int result = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from apm_client_parent_priscription";

			StringBuffer sql = new StringBuffer();
			/*
			 * if(searchText==null){ sql.
			 * append("select count(*) from apm_client_parent_priscription where lastmodified between '"
			 * +fromdate+"' and '"+todate+"' "); }else{ sql.append("select count(*) ");
			 * sql.append("from apm_client_parent_priscription inner join apm_patient on ");
			 * sql.append("apm_patient.id = apm_client_parent_priscription.clientid ");
			 * sql.append("where firstname like('%"
			 * +searchText+"') and apm_client_parent_priscription.lastmodified between '"
			 * +fromdate+"' and '"+todate+"' "); }
			 */

			/*
			 * if(searchText.equals("")){ sql.
			 * append("select count(*) from apm_client_parent_priscription where lastmodified between '"
			 * +fromdate+"' and '"+todate+"' order by id desc "); }else{
			 * sql.append("select count(*) ");
			 * sql.append("from apm_client_parent_priscription inner join apm_patient on ");
			 * sql.append("apm_patient.id = apm_client_parent_priscription.clientid ");
			 * sql.append("where firstname like('%"+searchText+"') or surname like('"
			 * +searchText+"%') and apm_client_parent_priscription.lastmodified between '"
			 * +fromdate+"' and '"+todate+"' "); sql.append("order by id desc "); }
			 */

			sql.append("select count(0) from apm_client_parent_priscription ");
			sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_priscription.clientid ");
			sql.append("left join apm_user on apm_user.id = apm_client_parent_priscription.practitionerid ");
			if (!phar_wardid.equals("0")) {
				sql.append(
						"inner join ipd_addmission_form on ipd_addmission_form.id = apm_client_parent_priscription.ipdid ");
			}
			sql.append("where apm_client_parent_priscription.lastmodified between '" + fromdate + "' and '" + todate
					+ "' ");
			if (!searchText.equals("")) {
				sql.append("and (apm_patient.firstname like ('" + searchText + "%') or apm_patient.surname like ('"
						+ searchText + "%') or apm_patient.abrivationid='" + searchText + "'   ");
				sql.append("or apm_patient.middlename like ('" + searchText + "%') or apm_patient.fullname like ('"
						+ searchText + "%') or apm_client_parent_priscription.id like ('" + searchText + "')) ");
			}
			if (!phar_wardid.equals("0")) {
				sql.append("and  ipd_addmission_form.wardid='" + phar_wardid + "' ");
			}
			if (!filter_location.equals("10")) {
				sql.append("and apm_client_parent_priscription.location_s='" + filter_location + "' ");
			}
			if (!filter_phar_location.equals("0")) {
				sql.append("and apm_client_parent_priscription.default_location='" + filter_phar_location + "' ");
			}
			//
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

			/*
			 * int temp= getCountExternal(location, fromdate, todate); int r=
			 * getCountInternal(location, fromdate, todate); result = result+temp+r;
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	private int isOT(int opdid) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(" select procedures from apm_available_slot where id=" + opdid + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String res1 = rs.getString(1);
				if (res1 == null) {
					res = 0;
				} else if (res1.equals("0")) {
					res = 0;
				} else if (res1.equals("")) {
					res = 0;
				} else {
					res = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Priscription> getPriscriptionAgainstSale(String formdate, String todate) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			BedDao bedDao = new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			StringBuffer buffer = new StringBuffer();
			todate = todate + " " + "59:59:59";
			buffer.append(
					"select concat(title,' ',apm_patient.firstname,' ',surname),apm_patient.dob,gender,apm_parent_prisc.userid,apm_medicine_bill.id,apm_parent_prisc.id, ");
			buffer.append("apm_parent_prisc.date,apm_medicine_bill.date,abrivationid,apm_parent_prisc.ipdid ");
			buffer.append("from apm_parent_prisc ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_bill.newparentid = apm_parent_prisc.id ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_parent_prisc.clientid ");
			buffer.append("where apm_parent_prisc.date between '" + formdate + "' and '" + todate + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StringBuffer buffer2 = new StringBuffer();
				buffer2.append(
						"select medicinename,qty from apm_child_prisc where parentid='" + rs.getString(6) + "' ");
				PreparedStatement ps = connection.prepareStatement(buffer2.toString());
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Priscription priscription = new Priscription();
					priscription.setClientname(rs.getString(1));
					String ageandgender = DateTimeUtils.getAge1(rs.getString(2)) + " / " + rs.getString(3);
					priscription.setAgeandgender(ageandgender);
					priscription.setProductname(resultSet.getString(1));
					priscription.setReqqty(resultSet.getInt(2));
					int saleqty = salepriscriptionagainstqty(resultSet.getString(1), rs.getInt(5));
					priscription.setSaleqty(saleqty);
					priscription.setBillno(rs.getString(5));
					String date = rs.getString(7).split(" ")[0];
					String billdate = rs.getString(8);
					priscription.setDate(DateTimeUtils.getCommencingDate1(date));
					priscription.setDateTime(DateTimeUtils.getCommencingDate1(billdate));
					priscription.setAbrivationid(rs.getString(9));
					String ipdid = "" + rs.getInt(10);
					if (rs.getInt(10) > 0) {
						priscription.setIpdid(ipdid);
						Bed bed = bedDao.getEditIpdData(ipdid);
						String wardname = ipdDAO.getIpdWardName(bed.getWardid());
						priscription.setWardname(wardname);
						String bedname = ipdDAO.getIpdBedName(bed.getBedid());
						priscription.setBedname(bedname);
					} else {
						priscription.setIpdid("0");
					}

					arrayList.add(priscription);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private int salepriscriptionagainstqty(String productname, int billno) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(apm_medicine_charges.quantity) from apm_medicine_charges ");
			buffer.append("inner join inventory_product on inventory_product.id = apm_medicine_charges.product_id ");
			buffer.append("where invoiceid='" + billno + "' and inventory_product.prodname='" + productname + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatePriscChildDeliverStatus(String childid, String date, String userId) {
		int res = 0;
		try {
			String sql = "update apm_client_priscription set deliver_statuss=1, deliverd_userid='" + userId
					+ "', deliverd_datetime='" + date + "' where id='" + childid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getAllChildPriscCount(String parentid, int i) {
		int count = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from apm_client_priscription where parentid='" + parentid + "' ");
			if (i == 1) {
				buffer.append("and deliver_statuss=1 ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateParentPriscDeliverStatus(String parentid, int i, String userId, String date) {
		int res = 0;
		try {
			String sql = "";
			if (i == 0) {
				sql = "update apm_client_parent_priscription set prisc_status=0 where id='" + parentid + "'";
			} else if (i == 1) {
				sql = "update apm_client_parent_priscription set prisc_status=1, deliver_userid='" + userId
						+ "', deliver_datetime='" + date + "' where id='" + parentid + "'";
			} else {
				sql = "update apm_client_parent_priscription set prisc_status=2, pending_userid='" + userId
						+ "', pending_datetime='" + date + "' where id='" + parentid + "'";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateNotDeilverStatusChild(String priscreqids, String parentid) {
		int res = 0;
		try {
			String sql = "update apm_client_priscription set deliver_statuss=0 where id not in (" + priscreqids
					+ ") and parentid=" + parentid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveParentReturnPrisc(Priscription priscription, String remark, String parentid) {
		int result = 0;
		try {
			String sql = "insert into priscription_parent_return(clientid, practitionerid, date, userid, prisc_parentid, ipdid, location, remark) values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getDate());
			preparedStatement.setString(4, priscription.getUserid());
			preparedStatement.setString(5, parentid);
			preparedStatement.setString(6, priscription.getIpdid());
			preparedStatement.setString(7, "" + priscription.getLocationid());
			preparedStatement.setString(8, remark);

			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveChildReturnPrisc(Priscription priscription, String mdrequestqty, String parentid, String childid,
			int newparentid) {
		int res = 0;
		try {
			String sql = "insert into priscription_child_return(parentid, medid, medname, childid, qty) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + newparentid);
			preparedStatement.setString(2, priscription.getMdicinenameid());
			preparedStatement.setString(3, priscription.getMdicinenametxt());
			preparedStatement.setString(4, childid);
			preparedStatement.setString(5, mdrequestqty);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getReturnQtyAgainstPrisc(int id) {
		int res = 0;
		try {
			String sql = "select sum(qty) from priscription_child_return where childid='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getReturnPriscDashboardCount(String searchText, String fromdate, String todate) {
		int count = 0;
		if (todate != null) {
			todate = todate + " 23:59:59";
		}
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) FROM priscription_parent_return ");
			sql.append(
					"inner join apm_client_parent_priscription on apm_client_parent_priscription.id = priscription_parent_return.prisc_parentid ");
			sql.append("inner join apm_patient on priscription_parent_return.clientid = apm_patient.id ");
			sql.append("left join apm_user on apm_user.id = apm_client_parent_priscription.practitionerid ");
			sql.append("where priscription_parent_return.date between '" + fromdate + "' and '" + todate + "' ");
			if (searchText != null) {
				sql.append("and (apm_patient.firstname like ('" + searchText + "%') or surname like ('" + searchText
						+ "'%) or abrivationid='" + searchText + "')");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<Priscription> getReturnPriscDashboard(Pagination pagination, String searchText, String fromdate,
			String todate) {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		if (todate != null) {
			todate = todate + " 23:59:59";
		}
		try {

			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT priscription_parent_return.id,priscription_parent_return.clientid,priscription_parent_return.date,priscription_parent_return.userid, ");
			sql.append(
					"priscription_parent_return.location,concat(title,' ',apm_patient.firstname,' ',surname),apm_patient.dob,gender,mobno, ");
			sql.append(
					"concat(initial,' ',apm_user.firstname,' ',lastname),priscription_parent_return.remark,priscription_parent_return.ipdid ");
			sql.append("FROM priscription_parent_return ");
			sql.append(
					"inner join apm_client_parent_priscription on apm_client_parent_priscription.id = priscription_parent_return.prisc_parentid ");
			sql.append("inner join apm_patient on priscription_parent_return.clientid = apm_patient.id ");
			sql.append("left join apm_user on apm_user.id = apm_client_parent_priscription.practitionerid ");
			sql.append("where priscription_parent_return.date between '" + fromdate + "' and '" + todate + "' ");
			if (searchText != null) {
				sql.append("and (apm_patient.firstname like ('" + searchText + "%') or surname like ('" + searchText
						+ "'%) or abrivationid='" + searchText + "')");
			}
			String sql1 = pagination.getSQLQuery(sql.toString());
			PreparedStatement ps = connection.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Priscription priscription = new Priscription();

				int id = rs.getInt(1);
				String clientid = rs.getString(2);

				String fullname = rs.getString(6);
				String ageandgender = DateTimeUtils.getAge1(rs.getString(7)) + " / " + rs.getString(8);
				priscription.setFullname(fullname);
				priscription.setAgeandgender(ageandgender);
				priscription.setMobile(rs.getString(9));
				String practitionername = rs.getString(10);
				priscription.setPractitionername(practitionername);
				priscription.setRemark(rs.getString(11));
				String lastmodified = "";
				if (rs.getString(3) != null) {
					lastmodified = DateTimeUtils.getDBDate(rs.getString(3));
				}
				String ipdid = rs.getString(12);
				boolean daycare = bedDao.isDayCare(ipdid);
				if (rs.getInt(12) > 0) {
					priscription.setIpdid(ipdid);
					Bed bed = bedDao.getEditIpdData(ipdid);
					String wardname = ipdDAO.getIpdWardName(bed.getWardid());
					priscription.setWardname(wardname);
					String bedname = ipdDAO.getIpdBedName(bed.getBedid());
					priscription.setBedname(bedname);
				} else {
					priscription.setIpdid("0");
				}

				priscription.setUserid(rs.getString(4));
				priscription.setId(id);
				priscription.setClientId(clientid);
				priscription.setLastmodified(lastmodified);

				int location = rs.getInt(5);
				if (location == 0) {
					if (daycare) {
						priscription.setLocation("DayCare");
					} else {
						priscription.setLocation("IPD");
					}

				} else if (location == 2) {
					priscription.setLocation("OT");
				} else {
					priscription.setLocation("OPD");
				}
				priscriptions.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;

	}

	public int cancelPrescription(String parentid, String delete_reason, String userid, String date) {
		int res = 0;
		try {
			String sql = "update apm_client_parent_priscription set prisc_delete=1,delete_remark=?,delete_userid=?,delete_datetime=? where id ='"
					+ parentid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, delete_reason);
			preparedStatement.setString(2, userid);
			preparedStatement.setString(3, date);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveprinttakenstatus(String id, String val1) {
		int res = 0;
		try {
			String sql = "update apm_client_parent_priscription set prisc_print_taken='" + val1 + "' where id ='" + id
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getPriscTokenNo(int newsessiid) {
		String tokenno = "";
		try {
			String sql = "select prisc_tokenno from apm_parent_prisc where id=" + newsessiid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tokenno = rs.getString(1);
				if (DateTimeUtils.isNull(tokenno).equals("0")) {
					tokenno = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenno;
	}

	public String getPriscParentTokenNo(int selectedid) {
		String tokenno = "";
		try {
			String sql = "select prisc_tokenno from apm_client_parent_priscription "
					+ "inner join apm_parent_prisc on apm_parent_prisc.oldparentid = apm_client_parent_priscription.id "
					+ " where apm_client_parent_priscription.id=" + selectedid
					+ " order by apm_parent_prisc.id desc limit 1 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tokenno = rs.getString(1);
				if (DateTimeUtils.isNull(tokenno).equals("0")) {
					tokenno = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenno;
	}

	public int updatePriscTokenStatus(int selectedid, int status) {
		int res = 0;
		try {
			String sql = "update apm_parent_prisc set prisc_token_status='" + status + "' where id ='" + selectedid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getfakedatebyClientid(String fakclientid) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		String result = "";
		try {
			String sql = "select min(id),lastmodified from apm_client_parent_priscription where clientid= '"
					+ fakclientid + "' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				result = rs.getString(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
