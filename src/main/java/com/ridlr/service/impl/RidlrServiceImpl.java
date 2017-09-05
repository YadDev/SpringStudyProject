package com.ridlr.service.impl;
import com.ridlr.dao.TransDAO;
import com.ridlr.entity.MasterAccPojo;
import com.ridlr.entity.PagentAccPojo;
import com.ridlr.entity.PagentAccTransPojo;
import com.ridlr.entity.ReconcilationDataPojo;
import com.ridlr.entity.TransactionDataPojo;
import com.ridlr.service.RidlrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * @author Ranga Reddy
 * @version 1.0
 */
@Service
@Transactional
public class RidlrServiceImpl implements RidlrService {
    
    public RidlrServiceImpl() {
        System.out.println("EmployeeServiceImpl()");
    }
    
    @Autowired
    private TransDAO transDAO;

    @Override
    public int createTrans(MasterAccPojo employee) {
        return transDAO.createTrans(employee);
    }
    @Override
    public PagentAccPojo updateTrans(PagentAccPojo employee) {
        return transDAO.updateTrans(employee);
    }
    @Override
    public void deleteTrans(long id) {
    	transDAO.deleteTrans(id);
    }
    @Override
    public List<MasterAccPojo> getAllTrans() {
        return transDAO.getAllTrans();
    }
    @Override
    public PagentAccPojo getTrans(long id) {
        return transDAO.getTrans(id);
    }    
    @Override
    public List<PagentAccPojo> getAllTrans(String employeeName) {
        return transDAO.getAllTrans(employeeName);
    }
	@Override
	public Map<String,String> getAgentCd(String agentCD) {
		// TODO Auto-generated method stub
		return transDAO.getAgentCd(agentCD);
	}
	@Override
	public List<Map<String,String>> getSummaryDetail(String agentCD,String startDate,String endDate) {
		// TODO Auto-generated method stub
		return transDAO.getSummaryDetail(agentCD,startDate,endDate);
	}
	@Override
	public List<PagentAccTransPojo> getTransactionDetail(String agentCD,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return transDAO.getTransactionDetail(agentCD,startDate,endDate);
	}
	@Override
	public List<MasterAccPojo> agentAllTransactionDetailAjaxCall(
			String agentCD, String startDate, String endDate) {
		// TODO Auto-generated method stub     
		return transDAO.agentAllTransactionDetailAjaxCall(agentCD,startDate,endDate);
	}
	@Override
	public List<Map<String, String>> paymentReconciliationDetailsAjaxCall(
			String agentCD, String startDate, String endDate,String dataFor) {
		// TODO Auto-generated method stub
		return transDAO.paymentReconciliationDetailsAjaxCall(agentCD,startDate,endDate,dataFor);
	}
	@Override
	public int insertIntoReconcilation(	List<ReconcilationDataPojo> reconcilationData) {
		// TODO Auto-generated method stub
		
		return transDAO.insertIntoReconcilation(reconcilationData);
	}
	@Override
	public List<Map<String, String>> uploadRidlrData(
			List<TransactionDataPojo> transactionData, String startDate,
			String endDaste) {
		// TODO Auto-generated method stub
		return transDAO.uploadRidlrData(transactionData,startDate,endDaste);
	}
}