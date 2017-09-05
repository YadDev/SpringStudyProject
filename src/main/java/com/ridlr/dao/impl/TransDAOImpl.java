package com.ridlr.dao.impl;

import com.ridlr.dao.TransDAO;
import com.ridlr.entity.MasterAccPojo;
import com.ridlr.entity.PagentAccPojo;
import com.ridlr.entity.PagentAccTransPojo;
import com.ridlr.entity.ReconcilationDataPojo;
import com.ridlr.entity.TransactionDataPojo;
import com.ridlr.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.mapping.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ParseConversionEvent;

/**
 * @author Ranga Reddy
 * @version 1.0
 */

@Repository
public class TransDAOImpl implements TransDAO {

	public TransDAOImpl() {
		System.out.println("EmployeeDAOImpl");
	}
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public Integer createTrans(MasterAccPojo employee) {

		PagentAccPojo pagentAcc = hibernateUtil.fetchById(
				String.valueOf(employee.getAgentCd()), PagentAccPojo.class);
		employee.setPaymentStatus("Pending");
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		employee.setDateTime(Timestamp.valueOf(timeStamp));

		if (pagentAcc == null) {

			pagentAcc = new PagentAccPojo();

			employee.setOpeningBal(pagentAcc.getCurrentBal());
			employee.setClosingBal(employee.getPaymentAmount()
					+ pagentAcc.getCurrentBal());

			pagentAcc.setCutOffLimit(190000);
			pagentAcc.setAgentCd(String.valueOf(employee.getAgentCd()));
			pagentAcc.setCurrentBal(employee.getPaymentAmount());
			pagentAcc.setCreditLimitDays(2);
			hibernateUtil.create(pagentAcc);
			return (Integer) hibernateUtil.create(employee);
		} else {

			employee.setOpeningBal(pagentAcc.getCurrentBal());
			employee.setClosingBal(employee.getPaymentAmount()
					+ pagentAcc.getCurrentBal());

			pagentAcc.setCurrentBal(employee.getPaymentAmount()
					+ pagentAcc.getCurrentBal());
			pagentAcc.setCutOffLimit(190000);
			pagentAcc.setCreditLimitDays(2);
			hibernateUtil.update(pagentAcc);
			// hibernateUtil.create(pagentAcc);
			return (Integer) hibernateUtil.create(employee);
		}

		// employee.setPaymentStatus("success");
		// return (int) hibernateUtil.create(employee);
	}

	@Override
	public PagentAccPojo updateTrans(PagentAccPojo employee) {
		return hibernateUtil.update(employee);
	}

	@Override
	public void deleteTrans(long id) {
		PagentAccPojo employee = new PagentAccPojo();
		employee.setAgentCd(String.valueOf(id));
		hibernateUtil.delete(employee);
	}

	@Override
	public List<MasterAccPojo> getAllTrans() {

		return hibernateUtil.fetchAll(MasterAccPojo.class);
	}

	@Override
	public PagentAccPojo getTrans(long id) {
		return hibernateUtil.fetchById(id, PagentAccPojo.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PagentAccPojo> getAllTrans(String employeeName) {
		String query = "SELECT e.* FROM Employees e WHERE e.name like '%"
				+ employeeName + "%'";
		List<Object[]> employeeObjects = hibernateUtil.fetchAll(query);
		List<PagentAccPojo> employees = new ArrayList<PagentAccPojo>();
		for (Object[] employeeObject : employeeObjects) {
			PagentAccPojo employee = new PagentAccPojo();
			Integer id = (Integer) employeeObject[0];
			Float salary = (Float) employeeObject[3];
			employee.setAgentCd(String.valueOf(id));
			employee.setCurrentBal(salary);
			employees.add(employee);
		}
		System.out.println(employees);
		return employees;
	}

	@Override
	public Map<String, String> getAgentCd(String agentCD) {
		String query ="";
		if(agentCD.equalsIgnoreCase("admin")){
		 query = "select distinct agent_cd from pagent_account";
		}else{
			 query = "select distinct agent_cd from pagent_account where agent_cd='"+agentCD+"'";
		}
		List<String[]> allAgentCD = hibernateUtil.fetchAll(query);
		Map<String, String> agentCDs = new HashMap<String, String>();
		for (Object agentCD1 : allAgentCD) {
			agentCDs.put(agentCD1.toString(), agentCD1.toString());
		}
		return agentCDs;
	}

	@Override
	public List<Map<String, String>> getSummaryDetail(String agentCD,
			String startDate, String endDate) {
		String query = "select agent_cd,sum(payment_amount) as amount from master_account_trans where agent_cd= '"
				+ agentCD
				+ "' and date(datetime) between '"
				+ startDate
				+ "' and '" + endDate + "'";

		List<Object[]> agentAmountDetail = hibernateUtil.fetchAll(query);

		List<Map<String, String>> agentMap = new ArrayList<Map<String, String>>();
		for (Object[] agentCD1 : agentAmountDetail) {
			Map<String, String> agentRecord = new HashMap<String, String>();
			if(agentCD1[0]!=null && agentCD1[1]!=null){
			agentRecord.put("amount", agentCD1[1].toString());
			agentRecord.put("agentCD", agentCD1[0].toString());
			agentMap.add(agentRecord);
			}
			

		}
		return agentMap;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<PagentAccTransPojo> getTransactionDetail(String agentCD,
			String startDate, String endDate) {
		String query = "select id,agent_cd,tkt_amount,opening_balance,closing_balance,time_stamp,guid from pagent_account_trans where agent_cd ='"
				+ agentCD + "' and date(TIME_STAMP) between '" + startDate
				+ "' and '" + endDate + "'";
		
		List<Object[]> agentAmountDetail = hibernateUtil.fetchAll(query);

		List<PagentAccTransPojo> pagentAccTransList = new ArrayList<PagentAccTransPojo>();
		for (Object[] pagentTrans : agentAmountDetail) {
			PagentAccTransPojo pagentAccTrans = new PagentAccTransPojo();
			pagentAccTrans.setId(Integer.parseInt(pagentTrans[0].toString()));
			pagentAccTrans.setAgentCd(pagentTrans[1].toString());
			pagentAccTrans.setTktAmount(Float.parseFloat(pagentTrans[2].toString()));
			pagentAccTrans.setOpeningBalance(Float.parseFloat(pagentTrans[3].toString()));
			pagentAccTrans.setClosingBalance(Float.parseFloat(pagentTrans[4].toString()));
			String dbDate = pagentTrans[5].toString();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				date =  sdf.parse(dbDate);
				java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
				pagentAccTrans.setTimeStamp(sqlStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			pagentAccTrans.setGuid(pagentTrans[6].toString());
//			pagentAccTrans.setTktRemaining(Integer.parseInt(pagentTrans[3].toString()));			
//			pagentAccTrans.setUserCd(pagentTrans[5].toString());
//			pagentAccTrans.setTicketNo(Integer.parseInt(pagentTrans[8].toString()));
//			pagentAccTrans.setBookingCenterCD(pagentTrans[9].toString());
//			pagentAccTrans.setFieldValue(pagentTrans[10].toString());
			
			pagentAccTransList.add(pagentAccTrans);

		}
		return pagentAccTransList;
	}

	@Override
	public List<MasterAccPojo> agentAllTransactionDetailAjaxCall(
			String agentCD, String startDate, String endDate) {
		String query = "SELECT id,agent_cd,datetime,payment_amount,mode_pay,opening_amount,closing_amount,payment_status FROM master_account_trans where agent_cd ='"
				+ agentCD + "' and date(datetime) between '" + startDate	+ "' and '" + endDate + "'";		
		
		List<Object[]> agentAmountDetail = hibernateUtil.fetchAll(query);

		List<MasterAccPojo> masterAccPojoList = new ArrayList<MasterAccPojo>();
		for (Object[] pagentTrans : agentAmountDetail) {
			MasterAccPojo pagentAccTrans = new MasterAccPojo();
			pagentAccTrans.setId(Integer.parseInt(pagentTrans[0].toString()));
			pagentAccTrans.setAgentCd(pagentTrans[1].toString());
			String dbDate = pagentTrans[2].toString();		
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =   sdf.parse(dbDate);
				java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime()); //2017-08-01 15:36:55.0
				pagentAccTrans.setDateTime(sqlStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pagentAccTrans.setPaymentAmount(Float.parseFloat(pagentTrans[3].toString()));
			pagentAccTrans.setModePay(pagentTrans[4].toString());
			pagentAccTrans.setOpeningBal(Float.parseFloat(pagentTrans[5].toString()));
			pagentAccTrans.setClosingBal(Float.parseFloat(pagentTrans[6].toString()));
			pagentAccTrans.setPaymentStatus(pagentTrans[7].toString());
			masterAccPojoList.add(pagentAccTrans);

		}
		return masterAccPojoList;
	}

	@Override
	public List<Map<String, String>> paymentReconciliationDetailsAjaxCall(
			String agentCD, String startDate, String endDate,String dataFor) {
		
		String query ="";
		String trimaxDataQuery=		"select IF(pat.AGENT_CD is null,'',pat.AGENT_CD) AGENT_CD,IF(pat.TKT_AMOUNT is null,'',pat.TKT_AMOUNT) TKT_AMOUNT,IF(pat.GUID is null,'',pat.GUID) GUID,IF(mt.depot_name is null,'',mt.depot_name) depot_name,IF(mt.region_name is null,'',mt.region_name)  region_name,IF(mt.full_ticket_count is null,'',mt.full_ticket_count) full_ticket_count,IF(mt.half_ticket_count is null,'',mt.half_ticket_count) half_ticket_count,IF(mt.total_amount/100 is null,'',mt.total_amount/100)  as totalAmount,IF(mt.from_name is null,'',mt.from_name) from_name ,"
				+ "IF(mt.till_name is null,'',mt.till_name) till_name,IF(mt.transaction_date is null,'',mt.transaction_date) transaction_date, IF(mt.transaction_time is null,'',mt.transaction_time) transaction_time,IF(mt.concession_short_name is null,'',mt.concession_short_name) concession_short_name,"
				+ "IF(pat.guid=mt.concession_short_name and pat.TKT_AMOUNT*100=mt.total_amount,'Match','Not Match') as 'Match'  from   pagent_account_trans pat left join mobile_ticket mt on ( pat.guid=mt.concession_short_name and pat.TKT_AMOUNT*100=mt.total_amount) "
				+ "where (date(TIME_STAMP) between '" + startDate	+ "' and  '" + endDate	+ "')or (date(transaction_date) between   '" + startDate	+ "' and  '" + endDate	+ "') ";
		String union= " UNION ";
		String ridlrData= " select IF(pat.AGENT_CD is null,'',pat.AGENT_CD) AGENT_CD,IF(pat.TKT_AMOUNT is null,'',pat.TKT_AMOUNT) TKT_AMOUNT,IF(pat.GUID is null,'',pat.GUID) GUID,IF(mt.depot_name is null,'',mt.depot_name) depot_name,IF(mt.region_name is null,'',mt.region_name)  region_name,IF(mt.full_ticket_count is null,'',mt.full_ticket_count) full_ticket_count,IF(mt.half_ticket_count is null,'',mt.half_ticket_count) half_ticket_count,IF(mt.total_amount/100 is null,'',mt.total_amount/100)  as totalAmount,IF(mt.from_name is null,'',mt.from_name) from_name ,"
				+ "IF(mt.till_name is null,'',mt.till_name) till_name,IF(mt.transaction_date is null,'',mt.transaction_date) transaction_date, IF(mt.transaction_time is null,'',mt.transaction_time) transaction_time,IF(mt.concession_short_name is null,'',mt.concession_short_name) concession_short_name,"
				+ "IF(pat.guid=mt.concession_short_name and pat.TKT_AMOUNT*100=mt.total_amount,'Match','Not Match') as 'Match'  from mobile_ticket mt   left join  pagent_account_trans pat on ( pat.guid=mt.concession_short_name and pat.TKT_AMOUNT*100=mt.total_amount) where"
				+ " (date(TIME_STAMP) between  '" + startDate	+ "' and  '" + endDate	+ "')or (date(transaction_date) between  '" + startDate	+ "' and  '" + endDate	+ "') ";
		
		if (dataFor.equals("TrimaxData")) {
			query = trimaxDataQuery ;
		} else if (dataFor.equals("RidlrData")) {
			query = ridlrData;
		} else {
			query = trimaxDataQuery + union + ridlrData;
		}

		List<Object[]> reconciliationDetails = hibernateUtil.fetchAll(query);

		List<Map<String, String>> reconciliationDetailList = new ArrayList<Map<String, String>>();
		try {
			for (Object[] reconciliationDetail : reconciliationDetails) {
				Map<String, String> reconciliationDetailRecord = new HashMap<String, String>();
				reconciliationDetailRecord.put("agentCD", reconciliationDetail[0].toString());
				reconciliationDetailRecord.put("tktAmount", reconciliationDetail[1].toString());
				reconciliationDetailRecord.put("guid", reconciliationDetail[2].toString());
				reconciliationDetailRecord.put("depotName", reconciliationDetail[3].toString());
				reconciliationDetailRecord.put("regionName", reconciliationDetail[4].toString());
				reconciliationDetailRecord.put("fullTicketCount", reconciliationDetail[5].toString());
				reconciliationDetailRecord.put("halfTicketCount", reconciliationDetail[6].toString());
				String amount = reconciliationDetail[7].toString();
				if(!amount.equals("")){
					double amt=Double.parseDouble(amount);
					reconciliationDetailRecord.put("transactionDate", Double.toString(amt));
					}else{
						reconciliationDetailRecord.put("transactionDate", amount);
					}
				reconciliationDetailRecord.put("etimAmount", reconciliationDetail[7].toString());
				reconciliationDetailRecord.put("fromName", reconciliationDetail[8].toString());
				reconciliationDetailRecord.put("tillName", reconciliationDetail[9].toString());
				String dbDate = reconciliationDetail[10].toString();
				if(!dbDate.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date =  sdf.parse(dbDate);
				java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
				reconciliationDetailRecord.put("transactionDate", sqlStartDate +" "+reconciliationDetail[11].toString());
				}else{
					reconciliationDetailRecord.put("transactionDate", dbDate +" "+reconciliationDetail[11].toString());
				}
				
//				reconciliationDetailRecord.put("transactionTime", reconciliationDetail[11].toString());
				reconciliationDetailRecord.put("concessionShortName", reconciliationDetail[12].toString());
				reconciliationDetailRecord.put("match", reconciliationDetail[13].toString());
				reconciliationDetailList.add(reconciliationDetailRecord);
			}
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return reconciliationDetailList;
	}

	@Override
	public int insertIntoReconcilation(List<ReconcilationDataPojo> reconcilationData) {
		// TODO Auto-generated method stub
		String updateQuery="update ridlr_reconcilation_data set modified='Y' where transaction_id='";
		String searchQuery="select * from ridlr_reconcilation_data where transaction_id='";
		for(ReconcilationDataPojo dataPojo:reconcilationData){
			searchQuery=searchQuery+dataPojo.getTransactionId()+"'";
			List<Object[]> prevReconcilationData = hibernateUtil.fetchAll(searchQuery);
			if(prevReconcilationData!=null){
				Session session=sessionFactory.openSession();
				Transaction transaction=session.beginTransaction();
				updateQuery=updateQuery+dataPojo.getTransactionId()+"'";
				System.out.println("updateQuery====>"+updateQuery);
//				transaction.
					
			}
			hibernateUtil.create(dataPojo);
		}
		return 0;
	}

	@Override
	public List<Map<String, String>> uploadRidlrData(
			List<TransactionDataPojo> transactionData, String startDate,
			String endDaste) {
		String updateQuery="update ridlr_transaction_data set modified='Y' where transactionReferenceId='";
		String searchQuery="select * from ridlr_transaction_data where transactionReferenceId='";
		for(TransactionDataPojo dataPojo:transactionData){
			searchQuery=searchQuery+dataPojo.getTransactionReferenceId()+"'";
			List<Object[]> prevTransactionData = hibernateUtil.fetchAll(searchQuery);
			if(prevTransactionData!=null){
				Session session=sessionFactory.openSession();
				Transaction transaction=session.beginTransaction();
				updateQuery=updateQuery+dataPojo.getTransactionReferenceId()+"'";
				System.out.println("updateQuery====>"+updateQuery);
//				transaction.
					
			}
			hibernateUtil.create(dataPojo);
		}
		return null;
	}
}