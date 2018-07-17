package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.ProfitDAO;
import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.NoticeVO;
import com.prj.nicarnaecar.vo.ProfitVO;

@Service
public class ProfitServiceImplXML implements ProfitService {

	@Autowired
	@Qualifier("profitDAOImplXML")
	private ProfitDAO profitDAO;
	
	@Override
	public void profitInsert(ProfitVO profitVO) {
		profitDAO.insertProfit(profitVO);
	}

	@Override
	public void profitDelete(int pnumber) {
		profitDAO.deleteProfit(pnumber);
	}

	@Override
	public List<ProfitVO> profitList() {
		return profitDAO.profitList();
	}

	@Override
	public List<ProfitVO> profitList(RecordCriteria recordCriteria) {
		return profitDAO.profitList(recordCriteria);
	}

	@Override
	public void profitList(HttpServletRequest request) {
		/*int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<ProfitVO> list = profitList(rc);
		int totalRec = profitCount();
		PageCriteria pc = new PageCriteria(rc,totalRec);
					
		request.setAttribute("list", list);
		request.setAttribute("page", pc);*/
		
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}
		
		String option = request.getParameter("option");
		String search = request.getParameter("search");
		
		List<ProfitVO> list = null;
		PageCriteria pc = null;
		RecordCriteria rc = null;
				
		// 검색조건이 없는 경우
				if(option == null || search.trim().equals("")) {
					rc = new RecordCriteria(reqPage);
					list = profitList(rc);
					int totalRec = profitCount();
					pc = new PageCriteria(rc,totalRec);
					
		// 검색조건이 있는 경우			
				} else {
					rc = new FindCriteria(reqPage,option,search);
					list = SearchProfitList((FindCriteria)rc);
					int totalRec = SearchProfitListCount((FindCriteria)rc);
					pc = new PageCriteria(rc, totalRec);
					
					request.setAttribute("findCriteria", (FindCriteria)rc);
				}
				
				request.setAttribute("list", list);
				request.setAttribute("page", pc);
				request.setAttribute("option",option);
				request.setAttribute("search",search);
		
	}

	@Override
	public int profitCount() {
		return profitDAO.profitCount();
	}

	@Override
	public List<ProfitVO> customerDetailProfit(int bnumber) {
		return profitDAO.customerDetailProfit(bnumber);
	}

	@Override
	public List<ProfitVO> SearchProfitList(FindCriteria findCriteria) {
		return profitDAO.SearchProfitList(findCriteria);
	}

	@Override
	public int SearchProfitListCount(FindCriteria findCriteria) {
		return profitDAO.SearchProfitListCount(findCriteria);
	}
	
	@Override
	public List<ProfitVO> profitChart() {
		return profitDAO.profitChart();
	}

	
}