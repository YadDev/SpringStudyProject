package com.ridlr.controller;

import com.opencsv.CSVReader;
import com.ridlr.entity.LoginPojo;
import com.ridlr.entity.MasterAccPojo;
import com.ridlr.entity.ReconcilationDataPojo;
import com.ridlr.service.LoginService;
import com.ridlr.service.RidlrService;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ranga Reddy
 * @version 1.0
 */
@Controller
@SessionAttributes("loginSession")
public class RidlrController {

	private static final Logger logger = Logger
			.getLogger(RidlrController.class);

	public RidlrController() {
		System.out.println("RidlrController()");
	}

	Map<String, Double> agentRecord;

	public Map<String, Double> getAgentRecord() {
		return agentRecord;
	}

	public void setAgentRecord(Map<String, Double> agentRecord) {
		this.agentRecord = agentRecord;
	}

	@Autowired
	private RidlrService ridlrService;

	@Autowired
	private LoginService loginService;

	@RequestMapping("createTran")
	public ModelAndView createTran(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);
			// this.referenceData();
			return new ModelAndView("ridlrPay", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping("saveRidlrPay")
	public ModelAndView saveTran(
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc) {
		logger.info("Saving the Employee. Data : " + pagentAcc);

		System.out.println("pagentAcc-----------" + pagentAcc.getDateTime());

		System.out.println("------------------->" + pagentAcc.getAgentCd());
		if (pagentAcc.getAgentCd().isEmpty()) {
			// if employee id is 0 then
			// creating the employee other
			// updating the employee
			ridlrService.createTrans(pagentAcc);
		} else {
			System.out.println("---------------------------------"
					+ pagentAcc.getAgentCd());
			ridlrService.createTrans(pagentAcc);
		}
		return new ModelAndView("redirect:transactionDetails");
	}

	protected Map referenceData(String agentCD) throws Exception {
		// Map referenceData = new HashMap();
		Map<String, String> country = new LinkedHashMap<String, String>();
		country = ridlrService.getAgentCd(agentCD);

		// country.put("Ridlr", "Ridlr");
		// referenceData.put("countryList", country);
		return country;
	}

	@RequestMapping("/getAllTrans")
	public ModelAndView getAllTrans(HttpSession session) {
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Getting the all Trans.");
			List<MasterAccPojo> employeeList = ridlrService.getAllTrans();
			return new ModelAndView("ridlrTran", "tranList", employeeList);
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping(value = { "Goto", "/" })
	public ModelAndView goToLogin(@ModelAttribute("login") LoginPojo login) {
		logger.info("Getting the all Trans.");

		// List<MasterAccPojo> employeeList = ridlrService.getAllTrans();
		return new ModelAndView("login");
		// return new ModelAndView("redirect:createTran");
	}

	@RequestMapping("loginProcess")
	public ModelAndView doLogin(@ModelAttribute("login") LoginPojo login) {
		logger.info("Getting the all Trans.");
		ModelAndView mav = new ModelAndView();
		LoginPojo userExists = loginService.checkLogin(login.getUserName(),
				login.getPassword());
		if (userExists != null) {
			List<MasterAccPojo> employeeList = ridlrService.getAllTrans();
			// return new ModelAndView("ridlrTran", "tranList", employeeList);
			mav.setViewName("redirect:transactionDetails");
			mav.addObject("loginSession", userExists);
			// mav.addObject("tranList", employeeList);
			// return new ModelAndView("ridlrTran","loginSession",userExists);
			// return new ModelAndView("redirect:transactionDetails");
			return mav;
		} else {
			System.out.println("username and password mismatch");
			return new ModelAndView("login", "message",
					"Failed username and password mismatch!!");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUser(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, SessionStatus sessionStatus) {
		System.out.println("LoginController.logoutUser()");
		// session = request.getSession(false);
		System.out.println("--------------------------_>befor"+ session.getId());
		if (session != null) {
			session.invalidate();
		}
		// Added by Sameer
		sessionStatus.setComplete();
		// CommonUtil.handleLogOutResponseCookie(response,
		// request,session.getId());
		System.out.println("--------------------------_After>"
				+ session.getId());
		// CommonUtil.logout(request, response);
		System.out.println("--------------------------_After2>"
				+ session.getId());

		// session = request.getSession(true);//new session created
		// Login login=new Login();
		// session.setAttribute("login", login);
		// ModelAndView mav = new ModelAndView("login");
		return "redirect:/";
	}

	@RequestMapping("summaryDetail")
	public ModelAndView summaryDetail(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);
			// this.referenceData();
			return new ModelAndView("ridlrSummary", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping("transactionDetails")
	public ModelAndView transactionDetails(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);
			// this.referenceData();
			return new ModelAndView("ridlrTransactions", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping("paymentTransactionDetails")
	public ModelAndView paymentTransactionDetails(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);
			// this.referenceData();
			return new ModelAndView("agentAllTrans", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping("paymentReconciliationDetails")
	public ModelAndView paymentReconciliationDetails(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);

			// this.referenceData();
			return new ModelAndView("ridlrReconciliation", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping("csvFileUpload")
	public ModelAndView csvFileUpload(HttpSession session,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {
		// System.out.println("pagentAcc-----------"+pagentAcc.getDateTime());
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("Creating Ridlr. Data: " + pagentAcc);
			// this.referenceData();
			return new ModelAndView("ridlrDataUpload", "agentCode",
					this.referenceData(loggedUser.getRole()));
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

	@RequestMapping(value = "/fileSubmit", headers = ("content-type=multipart/form-data"), method = RequestMethod.POST)
	public ModelAndView fileSubmitAjaxCall(HttpSession session,
			@RequestParam("agentCd") String agentCD,
			@RequestParam("fileDate") String fileDate,
			@RequestParam("csvFile") MultipartFile csvFile,
			HttpServletRequest request,
			@ModelAttribute("pagentAcc") MasterAccPojo pagentAcc)
			throws Exception {

		String message = "success";
		System.out.println("RidlrAjaxController.fileSubmit()");
		String msg = "";
		ModelAndView model = new ModelAndView();
		java.util.Date currentDate = new java.util.Date();
		java.sql.Timestamp currentSQLDate = new java.sql.Timestamp(
				currentDate.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(fileDate);
		java.sql.Timestamp sqlFileDate = new java.sql.Timestamp(date.getTime());
		List<ReconcilationDataPojo> reconcilationData = new ArrayList<ReconcilationDataPojo>();
		LoginPojo loggedUser = (LoginPojo) session.getAttribute("loginSession");
		if (loggedUser != null) {
			logger.info("fileSubmit : Agent Cd " + agentCD + " Start Date "
					+ fileDate + " End Date ");
			if (csvFile.isEmpty()) {
				msg = "File should contain data";

			} else {
				String rootPath = request.getSession().getServletContext()
						.getRealPath("/");
				System.out.println("root Path" + rootPath);
				File dir = new File(rootPath + File.separator + "uploadedfile");
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + csvFile.getOriginalFilename());
				System.out.println("File Path" + serverFile.getAbsolutePath());
				try {

					// file uploading code started
					try (InputStream is = csvFile.getInputStream();
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(serverFile))) {
						int i;
						// write file to server
						while ((i = is.read()) != -1) {
							stream.write(i);
						}
						stream.flush();
					}
					// file uploading code End
				} catch (IOException e) {
					msg = "Failed to process file because : " + e.getMessage();
					e.printStackTrace();

				}
				if (msg.equals("")) {
					String[] nextLine;
					try {
						FileReader fileReader = new FileReader(serverFile);
						CSVReader reader = new CSVReader(fileReader, ',', '\'');
						System.out.println("content : ");
						String[] headers = reader.readNext();
						if (headers.length == 5) {
							if (!headers[0].equals("AgentCD")
									|| !headers[1].equals("OpeningBalance")
									|| !headers[2].equals("ClosingBalance")
									|| !headers[3].equals("TktAmount")
									|| !headers[4].equals("TransactionID")) {
								// msg== header error
								msg = "File header is not Properly";
							}

						} else {
							msg = "Error in file header";
						}
						if (msg.equals("")) {
							while ((nextLine = reader.readNext()) != null) {
								if (nextLine.length == 5) {
									ReconcilationDataPojo data = new ReconcilationDataPojo();
									try {
										data.setAgentCd(nextLine[0]);
										data.setOpeningBalance(Float.parseFloat(nextLine[1]));
										data.setClosingBalance(Float.parseFloat(nextLine[2]));
										data.setTicketAmount(Float.parseFloat(nextLine[3]));
										data.setTransactionId(nextLine[4]);
										data.setTransactionDate(sqlFileDate);
										data.setUploadDate(currentSQLDate);
										data.setModified("N");
										reconcilationData.add(data);
									} catch (Exception e) {
										msg = "Error in file data Please correct the file";
										break;
									}
									
									
								} else {
									msg = "Error in file data Please correct the file";
									break;
								}
							}
							if(msg.equals("")){
								ridlrService.insertIntoReconcilation(reconcilationData);
								msg = "File Successfully uploaded";
							}
							
						}
						
					} catch (IOException e) {
						msg = "Error while reading csv and put to db : "+ e.getMessage();
						e.printStackTrace();
					}
				}
			}
			System.out.println("Message "+msg);
			model.setViewName("ridlrFileUpload");
			model.addObject("agentCode",this.referenceData(loggedUser.getRole()));
			model.addObject("msg", msg);
			return model;
		} else {
			return new ModelAndView("redirect:Goto");
		}
	}

}
