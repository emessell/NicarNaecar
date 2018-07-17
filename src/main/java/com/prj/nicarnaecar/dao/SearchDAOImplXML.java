package com.prj.nicarnaecar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Repository
public class SearchDAOImplXML implements SearchDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<VehicleVO> AllVehicleList() {
		return sqlSession.selectList("AllVehicleList");
	}

	@Override
	public List<VehicleVO> dateVehicleList(String bin, String bout, String eoffice) {
		Map<String,Object> map = new HashMap<>();
		map.put("bin", bin);
		map.put("bout", bout);
		map.put("eoffice", eoffice);
		return sqlSession.selectList("dateVehicleList", map);
	}

	@Override
	public List<VehicleVO> vehicleList() {
		return null;
	}

	@Override
	public VehicleVO reservationView(String vnumber) {
		return sqlSession.selectOne("reservationView", vnumber);
	}
	
	@Override
	public List<BookingVO> deliverySearch() {
		return sqlSession.selectList("delivery");
	}

	@Override
	public List<BookingVO> returnSearch() {
		return sqlSession.selectList("return");
	}

	@Override
	public void deliveryCar(int bnumber, String eemail) {
		Map<String,Object> map = new HashMap<>();
		map.put("bnumber", bnumber);
		map.put("eemail", eemail);
		sqlSession.update("deliveryCar", map);
	}

	@Override
	public void returnCar(BookingVO bookingVO) {
		sqlSession.update("returnCar", bookingVO);
	}

	@Override
	public List<BookingVO> deliverySearch(RecordCriteria recordCriteria) {
		return sqlSession.selectList("deliveryRecord", recordCriteria);
	}

	@Override
	public int deliverySearchCount() {
		return sqlSession.selectOne("deliveryCount");
	}

	@Override
	public List<BookingVO> returnSearch(RecordCriteria recordCriteria) {
		return sqlSession.selectList("returnRecord", recordCriteria);
	}

	@Override
	public int returnSearchCount() {
		return sqlSession.selectOne("returnCount");
	}

}
