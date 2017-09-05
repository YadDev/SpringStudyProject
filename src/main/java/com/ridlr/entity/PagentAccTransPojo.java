package com.ridlr.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "pagent_account_trans")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PagentAccTransPojo {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "AGENT_CD")
	private String agentCd;

	@Column(name = "OPENING_BALANCE")
	private float openingBalance;

	@Column(name = "CLOSING_BALANCE")
	private float closingBalance;


	@Column(name = "TKT_REMAINING")
	private int tktRemaining;

	@Column(name = "TKT_AMOUNT")
	private float tktAmount;

	@Column(name = "USER_CD")
	private String userCd;

	@Column(name = "TIME_STAMP")
	private Date timeStamp;

	@Column(name = "GUID")
	private String guid;

	@Column(name = "TICKET_NO")
	private int ticketNo;

	@Column(name = "BOOKING_CENTRE_CD")
	private String bookingCenterCD;

	@Column(name = "FIELD_VALUE")
	private String fieldValue;

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

	public int getTktRemaining() {
		return tktRemaining;
	}

	public void setTktRemaining(int tktRemaining) {
		this.tktRemaining = tktRemaining;
	}

	public float getTktAmount() {
		return tktAmount;
	}

	public void setTktAmount(float tktAmount) {
		this.tktAmount = tktAmount;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getBookingCenterCD() {
		return bookingCenterCD;
	}

	public void setBookingCenterCD(String bookingCenterCD) {
		this.bookingCenterCD = bookingCenterCD;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	
	

}
