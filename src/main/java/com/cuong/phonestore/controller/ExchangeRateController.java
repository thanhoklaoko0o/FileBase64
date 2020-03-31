/*package com.cuong.phonestore.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cuong.phonestore.model.Exrate;
import com.cuong.phonestore.model.ExrateList;


@Controller
@RequestMapping("/test")
public class ExchangeRateController {

	private final String USER_AGENT = "Mozilla/5.0";
	// link XML của Vietcombank
	private String url = "https://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx";
	@RequestMapping("/xml")
	@ResponseBody
	public ExrateList getXmlFromUrl() throws MalformedURLException, SAXException, IOException, ParserConfigurationException {
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
}
*/