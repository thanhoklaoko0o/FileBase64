package com.cuong.phonestore.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cuong.phonestore.model.Exrate;
import com.cuong.phonestore.model.ExrateList;

@Service
public class ExChangeRateService {

	public ExrateList getExChangeRateVCB(String url) throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
		 ExrateList exrateList = new ExrateList();
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
		 Document doc = db.parse(new URL(url).openStream());
		
		 doc.getDocumentElement().normalize();
		 exrateList.setDateTime(doc.getDocumentElement().getElementsByTagName("DateTime").item(0).getTextContent());
		 exrateList.setSource(doc.getDocumentElement().getElementsByTagName("Source").item(0).getTextContent());
		 List<Exrate> exrates = new ArrayList<>();
		 NodeList nodeList = doc.getElementsByTagName("Exrate");
		 
		 for (int temp = 0; temp < nodeList.getLength(); temp++) {

				Node nNode = nodeList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Exrate exrate = new Exrate();
					exrate.setCurrencyCode(eElement.getAttribute("CurrencyCode"));
					exrate.setCurrencyName(eElement.getAttribute("CurrencyName"));
					exrate.setBuy(eElement.getAttribute("Buy"));
					exrate.setTransfer(eElement.getAttribute("Transfer"));
					exrate.setSell(eElement.getAttribute("Sell"));
					exrates.add(exrate);
				}
			}
		 exrateList.setExrates(exrates);
		 return exrateList;
   }
	
	public Long USDParseToVND(String url, Long CurrencyVND) throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
		Exrate exrateOut = new Exrate();
		ExrateList exrateList = getExChangeRateVCB(url);
		List<Exrate> exrates = exrateList.getExrates();
		for(Exrate exrate: exrates) {
			if(exrate.getCurrencyCode().equals("USD")) {
				exrateOut = exrate;
			}
		}
		return CurrencyVND*Long.valueOf(exrateOut.getTransfer());
	}
}
