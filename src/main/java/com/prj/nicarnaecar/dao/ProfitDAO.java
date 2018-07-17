package com.prj.nicarnaecar.dao;

import java.util.List;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.ProfitVO;

public interface ProfitDAO {
	
	//매출, 지출 추가
	public void insertProfit(ProfitVO profitVO);
	//삭제
	public void deleteProfit(int pnumber);
	//매출, 지출 목록
	public List<ProfitVO> profitList();
	
	// 매출, 지출 목록(페이징
	public List<ProfitVO> profitList(RecordCriteria recordCriteria);
	// profit 갯수
	public int profitCount();
	
	// 검색했을경우 목록, 갯수
	public List<ProfitVO> SearchProfitList(FindCriteria findCriteria);
	public int SearchProfitListCount(FindCriteria findCriteria);
	
	
	// 고객별 수익내역들 조회
	public List<ProfitVO> customerDetailProfit(int bnumber);
	
	public List<ProfitVO> profitChart();
}