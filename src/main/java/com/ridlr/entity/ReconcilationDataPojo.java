package com.ridlr.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "ridlr_reconcilation_data")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReconcilationDataPojo {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rrid")
	private int rrid;

	@Column(name = "agent_cd")
	private String agentCd;

	@Column(name = "opening_balance")
	private float openingBalance;

	@Column(name = "closing_balance")
	private float closingBalance;

	@Column(name = "ticket_amount")
	private float ticketAmount;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "transaction_date")
	private Timestamp transactionDate;

	@Column(name = "upload_date")
	private Timestamp uploadDate;

	@Column(name = "modified")
	private String modified;

	public int getRrid() {
		return rrid;
	}

	public void setRrid(int rrid) {
		this.rrid = rrid;
	}

	public String getAgentCd() {
		return agentCd;
	}

	public void setAgentCd(String agentCd) {
		this.agentCd = agentCd;
	}

	public float getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(float openingBalance) {
		this.openingBalance = openingBalance;
	}

	public float getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(float closingBalance) {
		this.closingBalance = closingBalance;
	}

	public float getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(float ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	

}
