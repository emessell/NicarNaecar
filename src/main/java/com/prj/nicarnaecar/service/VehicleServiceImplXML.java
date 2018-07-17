package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.VehicleDAO;
import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.VehicleVO;

@Service
public class VehicleServiceImplXML implements VehicleService{

   @Autowired
   @Qualifier("vehicleDAOImplXML")
   VehicleDAO vehicleDAO;
   
   @Override
   public List<VehicleVO> VehicleList() {
      return vehicleDAO.VehicleList();
   }

   @Override
   public void insertCar(VehicleVO vehicleVO) {
      vehicleDAO.insertCar(vehicleVO);
   }

   @Override
   public void updateCar(VehicleVO vehicleVO) {
      vehicleDAO.updateCar(vehicleVO);
   }

   @Override
   public void deleteCar(String vnumber) {
      vehicleDAO.deleteCar(vnumber);
   }

	@Override
	public List<VehicleVO> VehicleList(RecordCriteria recordCriteria) {
		return vehicleDAO.VehicleList(recordCriteria);
	}

	@Override
	public void VehicleList(HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}
		
		String search = request.getParameter("search");
		
		List<VehicleVO> list = null;
		PageCriteria pc = null;
		RecordCriteria rc = null;
		
		
		// 검색조건이 없는 경우
				if(search == null || search.equals(null)) {
					rc = new RecordCriteria(reqPage);
					list = VehicleList(rc);
					int totalRec = VehicleListCount();
					pc = new PageCriteria(rc,totalRec);
					
		// 검색조건이 있는 경우			
				} else {
					rc = new FindCriteria(reqPage,search);
					list = SearchVehicleList((FindCriteria)rc);
					int totalRec = SearchVehicleListCount((FindCriteria)rc);
					pc = new PageCriteria(rc, totalRec);
					
					request.setAttribute("findCriteria", (FindCriteria)rc);
				}
				
				request.setAttribute("list", list);
				request.setAttribute("page", pc);
				request.setAttribute("search",search);
	}

	@Override
	public int VehicleListCount() {
		return vehicleDAO.VehicleListCount();
	}

	@Override
	public List<VehicleVO> SearchVehicleList(FindCriteria findCriteria) {
		return vehicleDAO.SearchVehicleList(findCriteria);
	}

	@Override
	public int SearchVehicleListCount(FindCriteria findCriteria) {
		return vehicleDAO.SearchVehicleListCount(findCriteria);
	}

   

   
}