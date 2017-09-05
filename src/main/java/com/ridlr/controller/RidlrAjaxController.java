package com.ridlr.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.loader.custom.Return;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ridlr.entity.LoginPojo;
import com.ridlr.entity.MasterAccPojo;
import com.ridlr.entity.PagentAccTransPojo;
import com.ridlr.entity.TransactionDataPojo;
import com.ridlr.service.LoginService;
import com.ridlr.service.RidlrService;

@Controller
@SessionAttributes("loginSession")
@RequestMapping(value = "/ajax")
public class RidlrAjaxController {

	private static final Logger logger = Logger
			.getLogger(RidlrController.class);

	public RidlrAjaxController() {
		System.out.println("RidlrAjaxController()");
	}

	@Autowired
	private RidlrService ridlrService;

	@Autowired
	private LoginService loginService;
	

	@RequestMapping(value="/getSummaryDetail", method=RequestMethod.POST)
	public @ResponseBody String getSummaryDetail(HttpSession session,@RequestParam("agentCD") String agentCD,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate)
			throws Exception {
		String meaaseg = "success";
		System.out.println("RidlrController.getSummaryDetail()");		
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("getSummaryDetail : Agent Cd " + agentCD +" Start Date "+startDate +" End Date "+endDate);
			
			System.out.println("getSummaryDetail : Agent Cd " + agentCD +" Start Date "+startDate +" End Date "+endDate);
			List<Map<String,String>> agentRecord=ridlrService.getSummaryDetail(agentCD, startDate, endDate);
//			return new ModelAndView("ridlrSummary", "agentCode",this.referenceData());
			List<JSONObject> jsonObj = new ArrayList<JSONObject>();

			for(Map<String, String> data : agentRecord) {
			    JSONObject obj = new JSONObject(data);
			    jsonObj.add(obj);
			}

			JSONArray agentRecordArray = new JSONArray(jsonObj);
			System.out.println(agentRecordArray.toString());			
			return agentRecordArray.toString() ;
		} else {
			return null;
		}
	}
	
	
	@RequestMapping(value="/agentTransactionDetail", method=RequestMethod.POST)
	public @ResponseBody String agentTransactionDetail(HttpSession session,@RequestParam("agentCD") String agentCD,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate)
			throws Exception {
		String meaaseg = "success";
		System.out.println("RidlrController.getSummaryDetail()");		
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("getSummaryDetail : Agent Cd " + agentCD +" Start Date "+startDate +" End Date "+endDate);
			
			System.out.println("getSummaryDetail : Agent Cd " + agentCD +" Start Date "+startDate +" End Date "+endDate);
			List<PagentAccTransPojo> agentRecord=ridlrService.getTransactionDetail(agentCD, startDate, endDate);
//			return new ModelAndView("ridlrSummary", "agentCode",this.referenceData());
			List<JSONObject> jsonObj = new ArrayList<JSONObject>();

			for(PagentAccTransPojo data : agentRecord) {
			    JSONObject obj = new JSONObject(data);
			    jsonObj.add(obj);
			}

			JSONArray agentRecordArray = new JSONArray(jsonObj);
			System.out.println(agentRecordArray.toString());			
			return agentRecordArray.toString() ;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/agentAllTransactionDetailAjaxCall", method = RequestMethod.POST)
	public @ResponseBody String agentAllTransactionDetailAjaxCall(HttpSession session,
			@RequestParam("agentCD") String agentCD,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		String meaaseg = "success";
		System.out.println("RidlrController.getSummaryDetail()");
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);

			System.out.println("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);
			List<MasterAccPojo> agentRecord = ridlrService.agentAllTransactionDetailAjaxCall(agentCD, startDate, endDate);
			// return new ModelAndView("ridlrSummary",
			// "agentCode",this.referenceData());
			List<JSONObject> jsonObj = new ArrayList<JSONObject>();

			for (MasterAccPojo data : agentRecord) {
				JSONObject obj = new JSONObject(data);
				jsonObj.add(obj);
			}

			JSONArray agentRecordArray = new JSONArray(jsonObj);
			System.out.println(agentRecordArray.toString());
			return agentRecordArray.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/paymentReconciliationDetailsAjaxCall", method = RequestMethod.POST)
	public @ResponseBody String paymentReconciliationDetailsAjaxCall(HttpSession session,
			@RequestParam("agentCD") String agentCD,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,@RequestParam("dataFor") String dataFor) throws Exception {
		String meaaseg = "success";
		System.out.println("RidlrController.getSummaryDetail()");
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);

			System.out.println("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);
			 List<Map<String,String>> agentRecord = ridlrService.paymentReconciliationDetailsAjaxCall(agentCD, startDate, endDate,dataFor);
			// return new ModelAndView("ridlrSummary",
			// "agentCode",this.referenceData());
			List<JSONObject> jsonObj = new ArrayList<JSONObject>();

			for (Map<String,String> data : agentRecord) {
				JSONObject obj = new JSONObject(data);
				jsonObj.add(obj);
			}

			JSONArray agentRecordArray = new JSONArray(jsonObj);
			System.out.println(agentRecordArray.toString());
			return agentRecordArray.toString();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/dataUploadAjaxCall", method = RequestMethod.POST)
	public @ResponseBody String dataUploadAjaxCall(HttpSession session,
			@RequestParam("agentCD") String agentCD,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws Exception {
		System.out.println("RidlrController.getSummaryDetail()");
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);

			System.out.println("getSummaryDetail : Agent Cd " + agentCD+ " Start Date " + startDate + " End Date " + endDate);
			
		List<TransactionDataPojo> transDataList=	getridlrTransactionData(startDate,endDate);
			
			List<Map<String,String>> agentRecord = ridlrService.uploadRidlrData(transDataList,startDate,endDate);
			List<JSONObject> jsonObj = new ArrayList<JSONObject>();
			for (Map<String,String> data : agentRecord) {
				JSONObject obj = new JSONObject(data);
				jsonObj.add(obj);
			}

			JSONArray agentRecordArray = new JSONArray(jsonObj);
			System.out.println(agentRecordArray.toString());
			return agentRecordArray.toString();
		} else {
			return null;
		}
	}
	
	
	public List<TransactionDataPojo> getridlrTransactionData(String startDate,String endDate) {
		JSONParser parser = new JSONParser();
		List<TransactionDataPojo> transDataList = new ArrayList<TransactionDataPojo>();

		try {
			URL url = new URL("http://operations2.ridlr.in:88/upsrtcapi/transaction-status?method=TICKET&fromdate="+startDate+"&todate="+endDate); // URL
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			java.util.Date date1 = new java.util.Date();
			java.sql.Timestamp uploadDate = new java.sql.Timestamp(date1.getTime());
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) parser.parse(inputLine);
				org.json.simple.JSONArray jsonArr = (org.json.simple.JSONArray) jsonObj.get("data");
				// Loop through each item
				for (Object obj : jsonArr) {
					org.json.simple.JSONObject transaction = (org.json.simple.JSONObject) obj;
					TransactionDataPojo transData = new TransactionDataPojo();
					transData.setItemType(transaction.get("item_type").toString());
					transData.setTicketAmount(Float.parseFloat(transaction.get("total_amount").toString()));
					transData.setPaymentStatus(transaction.get("payment_status").toString());
					transData.setCompanyStatus(transaction.get("company_status").toString());
					String bookinDate = transaction.get("booking_date_time").toString();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date date = sdf.parse(bookinDate);
					java.sql.Timestamp sqlBookingDate = new java.sql.Timestamp(date.getTime());
					transData.setBookingDateTime(sqlBookingDate);
					transData.setAction(transaction.get("action").toString());
					transData.setTransactionReferenceId(transaction.get("transactionReferenceId").toString());
					transData.setAgentCd(transaction.get("agentCd").toString());
					transData.setOpeningBalance(Float.parseFloat(transaction.get("opening_balance").toString()));
					transData.setClosingBalance(Float.parseFloat(transaction.get("closeing_balance").toString()));
					transData.setUploadDate(uploadDate);
					transData.setModified("N");
					transDataList.add(transData);
				}
			}
			in.close();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transDataList;
	}

}
