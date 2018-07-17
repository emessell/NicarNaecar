package com.prj.nicarnaecar.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.EmployeeVO;

@Repository
public class EmployeeDAOImplXML implements EmployeeDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<EmployeeVO> Elist() {
		return sqlSession.selectList("EmemberList");
	}

	@Override
	public void EUpdate(EmployeeVO employeeVO) {
		sqlSession.update("EUpdate",employeeVO);
	}

	@Override
	public void Einsert(EmployeeVO employeeVO) {
		sqlSession.insert("EInsert", employeeVO);
	}

	@Override
	public List<EmployeeVO> Elist(RecordCriteria recordCriteria) {
		return sqlSession.selectList("EmemberRecord", recordCriteria);
	}

	@Override
	public int ECount() {
		return sqlSession.selectOne("EmemberCount");
	}

	@Override
	public int eidCheck(String eemail) {
		return sqlSession.selectOne("eidCheck", eemail);
	}
	
	@Override
   public List<EmployeeVO> Elist(FindCriteria findCriteria) {
      return sqlSession.selectList("EmemberSearch", findCriteria);
   }
   @Override
   public int searchRec(FindCriteria findCriteria) {
      return sqlSession.selectOne("searchRec",findCriteria);
   }

}