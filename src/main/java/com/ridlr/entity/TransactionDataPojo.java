package com.ridlr.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "ridlr_transaction_data")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TransactionDataPojo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rtid")
	private int rtid;

	@Column(name = "agent_cd")
	private String agentCd;

	@Column(name = "item_type")
	private String itemType;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "company_status")
	private String companyStatus;

	@Column(name = "opening_balance")
	private float openingBalance;

	@Column(name = "closing_balance")
	private float closingBalance;

	@Column(name = "ticket_amount")
	private float ticketAmount;

	@Column(name = "transactionReferenceId")
	private String transactionReferenceId;

	@Column(name = "booking_date_time")
	private Timestamp bookingDateTime;

	@Column(name = "action")
	private String action;

	@Column(name = "upload_date")
	private Timestamp uploadDate;

	@Column(name = "modified")
	private String modified;

	public int getRtid() {
		return rtid;
	}

	public void setRtid(int rtid) {
		this.rtid = rtid;
	}

	public String getAgentCd() {
		return agentCd;
	}

	public void setAgentCd(String agentCd) {
		this.agentCd = agentCd;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
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

	public String getTransactionReferenceId() {
		return transactionReferenceId;
	}

	public void setTransactionReferenceId(String transactionReferenceId) {
		this.transactionReferenceId = transactionReferenceId;
	}

	public Timestamp getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(Timestamp bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
