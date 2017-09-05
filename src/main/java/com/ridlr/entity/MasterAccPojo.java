package com.ridlr.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity  
@Table(name = "master_account_trans")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
public class MasterAccPojo implements Serializable {  
	  
	 private static final long serialVersionUID = 1L;  
	  
	 @Id  
//	 @GeneratedValue 
	 @Column(name = "id")  
	 private int id; 
	 
	 @Column(name = "AGENT_CD")  
	 private String agentCd;  
	 
	 @Column(name = "datetime")  
	 private Timestamp dateTime;
	 
	 @Column(name = "payment_amount")  
	 private float paymentAmount;
	 
	 @Column(name = "opening_amount")  
	 private float openingBal;
	 
	 @Column(name = "closing_amount")  
	 private float closingBal;
	 
	 @Column(name = "mode_pay")  
	 private String modePay;
	 
	 @Column(name = "payment_status",nullable = true)  
	 private String paymentStatus;
	 
	 @Transient
	 private String  dataFor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentCd() {
		return agentCd;
	}

	public void setAgentCd(String agentCd) {
		this.agentCd = agentCd;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getModePay() {
		return modePay;
	}

	public void setModePay(String modePay) {
		this.modePay = modePay;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public float getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(float openingBal) {
		this.openingBal = openingBal;
	}

	public float getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(float closingBal) {
		this.closingBal = closingBal;
	}

	public String getDataFor() {
		return dataFor;
	}

	public void setDataFor(String dataFor) {
		this.dataFor = dataFor;
	}
	
	
	 
	 
}
