package com.prj.nicarnaecar.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.ProfitVO;

@Repository
public class ProfitDAOImplXML implements ProfitDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insertProfit(ProfitVO profitVO) {
		sqlSession.insert("profitInsert", profitVO);
	}

	@Override
	public void deleteProfit(int pnumber) {
		sqlSession.delete("profitDelete",pnumber);
	}

	@Override
	public List<ProfitVO> profitList() {
		return sqlSession.selectList("profitList");
	}

	@Override
	public List<ProfitVO> profitList(RecordCriteria recordCriteria) {
		return sqlSession.selectList("profitRecord", recordCriteria);
	}

	@Override
	public int profitCount() {
		return sqlSession.selectOne("profitCount");
	}

	@Override
	public List<ProfitVO> customerDetailProfit(int bnumber) {
		return sqlSession.selectList("profitDetail", bnumber);
	}

	@Override
	public List<ProfitVO> SearchProfitList(FindCriteria findCriteria) {
		return sqlSession.selectList("profitFind",findCriteria);
	}

	@Override
	public int SearchProfitListCount(FindCriteria findCriteria) {
		return sqlSession.selectOne("profitFindCount", findCriteria);
	}

	@Override
	public List<ProfitVO> profitChart() {
		return sqlSession.selectList("profitChart");
	}

}