package com.cuong.phonestore.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Exrate {

	@XmlAttribute(name="CurrencyCode")
	public String CurrencyCode;
	@XmlAttribute(name="CurrencyName")
	public String CurrencyName;
	@XmlAttribute(name="Buy")
	public String Buy;
	@XmlAttribute(name="Transfer")
	public String Transfer;
	@XmlAttribute(name="Sell")
	public String Sell;
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return CurrencyName;
	}
	public void setCurrencyName(String currencyName) {
		CurrencyName = currencyName;
	}
	public String getBuy() {
		return Buy;
	}
	public void setBuy(String buy) {
		Buy = buy;
	}
	public String getTransfer() {
		return Transfer;
	}
	public void setTransfer(String transfer) {
		Transfer = transfer;
	}
	public String getSell() {
		return Sell;
	}
	public void setSell(String sell) {
		Sell = sell;
	}
	
}
