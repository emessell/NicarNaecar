package com.prj.nicarnaecar;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prj.nicarnaecar.dao.SearchDAO;
import com.prj.nicarnaecar.service.SearchService;
import com.prj.nicarnaecar.vo.VehicleVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class searchTest {

	private static Logger Logger = LoggerFactory.getLogger(searchTest.class);
	
	@Autowired
	@Qualifier("searchDAOImplXML")
	SearchDAO searchdao;
	
	@Test
	@Disabled
	public void AllList() {
		List<VehicleVO> list = searchdao.AllVehicleList();
		for (VehicleVO vehicleVO : list) {
			System.out.println(vehicleVO.toString());
		}
	}
	
	@Autowired
	@Qualifier("searchServiceImplXML")
	SearchService searchService;
	
	@Test
	void dateSearchList() {
		List<VehicleVO> list = searchService.dateVehicleList("2018.6.26.", "2018.6.27.", "asdf");
		for (VehicleVO vehicleVO : list) {
			System.out.println(vehicleVO.toString());
		}
	}
}
