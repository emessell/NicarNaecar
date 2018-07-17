package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.ServiceDAO;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.FaqVO;
import com.prj.nicarnaecar.vo.NoticeVO;

@Service
public class ServiceServiceImplXML implements ServiceService {

	@Autowired
	@Qualifier("serviceDAOImplXML")
	ServiceDAO serviceDAO;
	
	@Override
	public List<NoticeVO> noticeList() {
		return serviceDAO.noticeList();
	}

	@Override
	public List<FaqVO> faqList() {
		return serviceDAO.faqList();
	}

	@Override
	public NoticeVO noticeView(int nnumber) {
		return serviceDAO.noticeView(nnumber);
	}

	@Override
	public void noticeModify(NoticeVO noticeVO) {
		serviceDAO.noticeModify(noticeVO);
	}

	@Override
	public void noticeDelete(int nnumber) {
		serviceDAO.noticeDelete(nnumber);
	}

	@Override
	public void faqModify(FaqVO faqVO) {
		serviceDAO.faqModify(faqVO);
	}

	@Override
	public void faqDelete(int fnumber) {
		serviceDAO.faqDelete(fnumber);
	}

	@Override
	public void noticeInsert(NoticeVO noticeVO) {
		serviceDAO.noticeInsert(noticeVO);
	}

	@Override
	public void faqInsert(FaqVO faqVO) {
		serviceDAO.faqInsert(faqVO);
	}

	@Override
	public FaqVO faqView(int fnumber) {
		return serviceDAO.faqView(fnumber);
	}

	@Override
	public List<NoticeVO> noticeList(RecordCriteria recordCriteria) {
		return serviceDAO.noticeList(recordCriteria);
	}

	@Override
	public void noticeList(HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<NoticeVO> list = noticeList(rc);
		int totalRec = noticeCount();
		PageCriteria pc = new PageCriteria(rc,totalRec);
					
		request.setAttribute("list", list);
		request.setAttribute("page", pc);
	}

	@Override
	public int noticeCount() {
		return serviceDAO.noticeCount();
	}

	@Override
	public List<FaqVO> faqList(RecordCriteria recordCriteria) {
		return serviceDAO.faqList(recordCriteria);
	}

	@Override
	public void faqList(HttpServletRequest request) {
		int reqPage = 0;
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		RecordCriteria rc = new RecordCriteria(reqPage);
		List<FaqVO> list = faqList(rc);
		int totalRec = faqCount();
		PageCriteria pc = new PageCriteria(rc,totalRec);
					
		request.setAttribute("list", list);
		request.setAttribute("page", pc);
	}

	@Override
	public int faqCount() {
		return serviceDAO.faqCount();
	}

}
