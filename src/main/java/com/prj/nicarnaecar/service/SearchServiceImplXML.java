package com.prj.nicarnaecar.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.SearchDAO;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Service
public class SearchServiceImplXML implements SearchService {

	@Autowired
	@Qualifier("searchDAOImplXML")
	SearchDAO searchdao;
	
	@Override
	public List<VehicleVO> AllVehicleList() {
		return searchdao.AllVehicleList();
	}

	@Override
	public List<VehicleVO> dateVehicleList(String bin, String bout, String eoffice) {
		return searchdao.dateVehicleList(bin, bout, eoffice);
	}

	@Override
	public List<VehicleVO> vehicleList() {
		return null;
	}

	@Override
	public VehicleVO reservationView(String vnumber) {
		return searchdao.reservationView(vnumber);
	}
	
	@Override
	public List<BookingVO> deliverySearch() {
		return searchdao.deliverySearch();
	}

	@Override
	public List<BookingVO> returnSearch() {
		return searchdao.returnSearch();
	}

	@Override
	public void deliveryCar(int bnumber, String eemail) {
		searchdao.deliveryCar(bnumber, eemail);
	}

	@Override
	public void returnCar(BookingVO bookingVO) {
		searchdao.returnCar(bookingVO);
	}

	@Override
	public List<BookingVO> deliverySearch(RecordCriteria recordCriteria) {
		return searchdao.deliverySearch(recordCriteria);
	}

	@Override
	public void deliverySearch(HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<BookingVO> list = deliverySearch(rc);
		int totalRec = deliveryCount();
		PageCriteria pc = new PageCriteria(rc,totalRec);
		
		request.setAttribute("list", list);
		request.setAttribute("page", pc);
	}

	@Override
	public int deliveryCount() {
		return searchdao.deliverySearchCount();
	}

	@Override
	public List<BookingVO> returnSearch(RecordCriteria recordCriteria) {
		return searchdao.returnSearch(recordCriteria);
	}

	@Override
	public void returnSearch(HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<BookingVO> list = returnSearch(rc);
		int totalRec = returnSearchCount();
		PageCriteria pc = new PageCriteria(rc,totalRec);
					
		request.setAttribute("list", list);
		request.setAttribute("page", pc);
	}

	@Override
	public int returnSearchCount() {
		return searchdao.returnSearchCount();
	}

}
