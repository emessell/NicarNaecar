package com.prj.nicarnaecar.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.BookingDAO;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.NoticeVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Service
public class BookingServiceImplXML implements BookingService {

	@Autowired
	@Qualifier("bookingDAOImplXML")
	BookingDAO bookingdao;
	
	@Override
	public void bookingInsert(BookingVO bookingVO) {
		bookingdao.bookingInsert(bookingVO);
	}

	@Override
	public void bookingCancel(int bnumber) {
		bookingdao.bookingCancel(bnumber);
	}

	@Override
	public void bookingModify(BookingVO bookingVO) {
		bookingdao.bookingModify(bookingVO);
	}

/*	@Override
	public List<BookingVO> bookingView(String cemail) {
		return bookingdao.bookingView(cemail);
	}*/

	@Override
	public String bookingExtend(BookingVO bookingVO) {
		return bookingdao.bookingExtend(bookingVO);
	}

	@Override
	public BookingVO getBookingInfo(int bnumber) {
		return bookingdao.getBookingInfo(bnumber);
	}

	@Override
	public void bookingExtendOK(BookingVO bookingVO) {
		bookingdao.bookingExtendOK(bookingVO);
	}

	@Override
	public List<BookingVO> bookingView(RecordCriteria recordCriteria, String cemail) {
		return bookingdao.bookingView(recordCriteria, cemail);
	}

	@Override
	public int bookingViewCount(String cemail) {
		return bookingdao.bookingViewCount(cemail);
	}

	@Override
	public void bookingView(String cemail, HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<BookingVO> list = bookingView(rc, cemail);
		int totalRec = bookingViewCount(cemail);
		PageCriteria pc = new PageCriteria(rc,totalRec);
					
		request.setAttribute("list", list);
		request.setAttribute("page", pc);
		System.out.println(list.toString());
	}

	@Override
	public int bookingCheck(String cemail) {
		return bookingdao.bookingCheck(cemail);
	}


}
