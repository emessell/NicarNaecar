package com.prj.nicarnaecar.dao;

import java.util.List;

import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.FaqVO;
import com.prj.nicarnaecar.vo.NoticeVO;

public interface ServiceDAO {
	
	List<NoticeVO> noticeList();
	List<NoticeVO> noticeList(RecordCriteria recordCriteria);
	int noticeCount();
	
	NoticeVO noticeView(int nnumber);
	
	void noticeModify(NoticeVO noticeVO);
	void noticeDelete(int nnumber);
	void noticeInsert(NoticeVO noticeVO);

	List<FaqVO> faqList();
	List<FaqVO> faqList(RecordCriteria recordCriteria);
	int faqCount();

	FaqVO faqView(int fnumber);
	void faqModify(FaqVO faqVO);
	void faqDelete(int fnumber);
	void faqInsert(FaqVO faqVO);
}
