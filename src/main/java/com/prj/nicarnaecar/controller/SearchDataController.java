package com.prj.nicarnaecar.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.prj.nicarnaecar.service.SearchService;
import com.prj.nicarnaecar.vo.VehicleVO;

@RestController
@Controller
public class SearchDataController {
	
	@Autowired
	@Qualifier("searchServiceImplXML")
	SearchService searchService;
	
	@RequestMapping(value="/search/dateSearchBtn/{bin}/{bout}/{eoffice}", method=GET)
	public ResponseEntity<List<VehicleVO>> dateSearch(@PathVariable String bin, @PathVariable String bout, @PathVariable String eoffice) {
		ResponseEntity<List<VehicleVO>> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(searchService.dateVehicleList(bin, bout, eoffice),HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	
}
