package com.prj.nicarnaecar.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;

@Repository
public class BookingDAOImplXML implements BookingDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void bookingInsert(BookingVO bookingVO) {
		sqlSession.insert("bookingInsert",bookingVO);
	}

	@Override
	public void bookingCancel(int bnumber) {
		sqlSession.update("bookingDelete",bnumber);
	}

	@Override
	public void bookingModify(BookingVO bookingVO) {
		sqlSession.update("bookingModify",bookingVO);
	}

/*	@Override
	public List<BookingVO> bookingView(String cemail) {
		return sqlSession.selectList("bookingView",cemail);
	}*/

	@Override
	public String bookingExtend(BookingVO bookingVO) {
		return sqlSession.selectOne("bookingExtendView", bookingVO);
	}

	@Override
	public BookingVO getBookingInfo(int bnumber) {
		return sqlSession.selectOne("getBookingInfo", bnumber);
	}

	@Override
	public void bookingExtendOK(BookingVO bookingVO) {
		sqlSession.update("bookingExtend", bookingVO);
	}

	@Override
	public List<BookingVO> bookingView(RecordCriteria recordCriteria, String cemail) {
		Map<String,Object> map = new HashMap<>();
		map.put("startRecord", recordCriteria.getStartRecord());
		map.put("endRecord", recordCriteria.getEndRecord());
		map.put("cemail", cemail);
		return sqlSession.selectList("bookingViewRecord", map);
	}

	@Override
	public int bookingViewCount(String cemail) {
		return sqlSession.selectOne("bookingViewCount", cemail);
	}

	@Override
	public int bookingCheck(String cemail) {
		return sqlSession.selectOne("bookingCheck", cemail);
	}

}
