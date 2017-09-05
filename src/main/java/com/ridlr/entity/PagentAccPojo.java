package com.ridlr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity  
@Table(name = "pagent_account")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class PagentAccPojo implements Serializable {  
	  
	 private static final long serialVersionUID = 1L;  
	  
	 @Id  
//	 @GeneratedValue  
	 @Column(name = "AGENT_CD")  
	 private String agentCd;  
	 
	 @Column(name = "SECURITY_DEPOSIT")  
	 private float securityDeposit;
	 
	 @Column(name = "CREDIT_LIMIT_AMT")  
	 private float creditLimitAmt;
	 
	 @Column(name = "CREDIT_LIMIT_DAYS")  
	 private int creditLimitDays;
	 
	 @Column(name = "CURRENT_BAL")  
	 private float currentBal;
	 
	 @Column(name = "CUT_OFF_LIMIT")  
	 private int cutOffLimit;

	public String getAgentCd() {
		return agentCd;
	}

	public void setAgentCd(String agentCd) {
		this.agentCd = agentCd;
	}

	public float getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(float securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public float getCreditLimitAmt() {
		return creditLimitAmt;
	}

	public void setCreditLimitAmt(float creditLimitAmt) {
		this.creditLimitAmt = creditLimitAmt;
	}

	public int getCreditLimitDays() {
		return creditLimitDays;
	}

	public void setCreditLimitDays(int creditLimitDays) {
		this.creditLimitDays = creditLimitDays;
	}

	public float getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(float currentBal) {
		this.currentBal = currentBal;
	}

	public int getCutOffLimit() {
		return cutOffLimit;
	}

	public void setCutOffLimit(int cutOffLimit) {
		this.cutOffLimit = cutOffLimit;
	}
	 
	 
	 
	 

}
