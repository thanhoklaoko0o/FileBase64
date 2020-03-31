package com.cuong.phonestore.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExrateList {

	@XmlElement(name = "DateTime")
	public String DateTime;
	@XmlElement(name = "Exrate")
	public List<Exrate> Exrates;
	@XmlElement(name = "Source")
	public String Source;
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public List<Exrate> getExrates() {
		return Exrates;
	}
	public void setExrates(List<Exrate> exrates) {
		Exrates = exrates;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	
}
