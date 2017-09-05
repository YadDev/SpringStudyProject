package com.ridlr.dao;

import java.util.List;
import java.util.Map;

import com.ridlr.entity.MasterAccPojo;
import com.ridlr.entity.PagentAccPojo;
import com.ridlr.entity.PagentAccTransPojo;
import com.ridlr.entity.ReconcilationDataPojo;
import com.ridlr.entity.TransactionDataPojo;

/**
 * @author Ranga Reddy
 * @version 1.0
 */
public interface TransDAO {
	  	public Integer createTrans(MasterAccPojo employee);
	    public PagentAccPojo updateTrans(PagentAccPojo employee);
	    public void deleteTrans(long id);
	    public List<MasterAccPojo> getAllTrans();
	    public PagentAccPojo getTrans(long id);   
	    public List<PagentAccPojo> getAllTrans(String employeeName);
	    public Map<String,String> getAgentCd(String agentCD);
	    public List<Map<String,String>> getSummaryDetail(String agentCD,String startDate,String endDate);
	    public List<PagentAccTransPojo> getTransactionDetail(String agentCD,String startDate,String endDate);
	    public List<MasterAccPojo> agentAllTransactionDetailAjaxCall(String agentCD,String startDate,String endDate);
	    public List<Map<String,String>> paymentReconciliationDetailsAjaxCall(String agentCD,String startDate,String endDate,String dataFor);
	    public int insertIntoReconcilation(List<ReconcilationDataPojo> reconcilationData);
	    public List<Map<String, String>> uploadRidlrData(List<TransactionDataPojo> transactionData, String startDate,String endDaste);
}

