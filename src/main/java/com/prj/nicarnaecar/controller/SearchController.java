package com.prj.nicarnaecar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.prj.nicarnaecar.service.SearchService;
import com.prj.nicarnaecar.vo.VehicleVO;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	@Qualifier("searchServiceImplXML")
	SearchService searchService;
	
	
	private static String getTagValue(String tag, Element eElement) {
      NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
      Node nValue = (Node)nlList.item(0);
      if(nValue == null)
         return null;
      return nValue.getNodeValue();
      
   }
	@RequestMapping(value = "/dateSearch")
	public String dateSearch(HttpServletRequest request) throws Exception {
		 String url = "http://www.opinet.co.kr/api/avgAllPrice.do?out=xml&code=F316180710";
	      
	      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	      Document doc = dBuilder.parse(url);
	      
	      //root tag
	      doc.getDocumentElement().normalize();
	     // System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
	      //Root Element: RESULT

	      NodeList nList = doc.getElementsByTagName("OIL");
	    //  System.out.println("파싱할 리스트 수: "+ nList.getLength());
	      
	      for(int temp=0; temp < 5; temp++) {
	         Node nNode = nList.item(temp);
	         if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
	            if(getTagValue("PRODNM", eElement).equals("휘발유")) { // 가솔린
	            	request.setAttribute("gasoline", getTagValue("PRICE", eElement));
	            }else if(getTagValue("PRODNM", eElement).equals("자동차용경유")) { // 디젤
	            	 request.setAttribute("diesel", getTagValue("PRICE", eElement));
	            }else if(getTagValue("PRODNM", eElement).equals("자동차용부탄")) { // LPG
	            	request.setAttribute("lpg", getTagValue("PRICE", eElement));
	            }
	         }
	      }
		return "/search/dateSearch";
	}
	
	@RequestMapping(value = "/vehicleSearch")
	public String vehicleSearch(HttpServletRequest request) throws Exception {
		 String url = "http://www.opinet.co.kr/api/avgAllPrice.do?out=xml&code=F316180710";
	      
	      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	      Document doc = dBuilder.parse(url);
	      
	      //root tag
	      doc.getDocumentElement().normalize();
	     // System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
	      //Root Element: RESULT

	      NodeList nList = doc.getElementsByTagName("OIL");
	    //  System.out.println("파싱할 리스트 수: "+ nList.getLength());
	      
	      for(int temp=0; temp < 5; temp++) {
	         Node nNode = nList.item(temp);
	         if(nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
	            if(getTagValue("PRODNM", eElement).equals("휘발유")) { // 가솔린
	            	request.setAttribute("gasoline", getTagValue("PRICE", eElement));
	            }else if(getTagValue("PRODNM", eElement).equals("자동차용경유")) { // 디젤
	            	 request.setAttribute("diesel", getTagValue("PRICE", eElement));
	            }else if(getTagValue("PRODNM", eElement).equals("자동차용부탄")) { // LPG
	            	request.setAttribute("lpg", getTagValue("PRICE", eElement));
	            }
	         }
	      }
		
		
		request.setAttribute("list", searchService.AllVehicleList());
		return "/search/vehicleSearch";
	}
}
