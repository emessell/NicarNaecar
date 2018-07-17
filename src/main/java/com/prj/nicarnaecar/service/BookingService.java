package com.prj.nicarnaecar.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.VehicleVO;

public interface BookingService {
	// 예약하기
	void bookingInsert(BookingVO bookingVO);
	
	// 예약취소
	void bookingCancel(int bnumber);
	
	// 예약수정
	void bookingModify(BookingVO bookingVO);
	
	// 예약조회
/*	List<BookingVO> bookingView(String cemail);*/
	List<BookingVO> bookingView(RecordCriteria recordCriteria, String cemail);
	void bookingView(String cemail, HttpServletRequest request);
	int bookingViewCount(String cemail);
	
	// 예약연장조회
	String bookingExtend(BookingVO bookingVO);
	
	// 예약정보 가져오기
	BookingVO getBookingInfo(int bnumber);
	
	// 예약연장하기
	void bookingExtendOK(BookingVO bookingVO);
	
	// 예약 전 연장여부 조회
	int bookingCheck(String cemail);
}
