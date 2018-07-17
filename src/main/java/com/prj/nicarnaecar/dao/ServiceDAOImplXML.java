package com.prj.nicarnaecar.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.FaqVO;
import com.prj.nicarnaecar.vo.NoticeVO;

@Repository
public class ServiceDAOImplXML implements ServiceDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<NoticeVO> noticeList() {
		return sqlSession.selectList("noticeList");
	}

	@Override
	public List<FaqVO> faqList() {
		return sqlSession.selectList("faqList");
	}

	@Override
	public NoticeVO noticeView(int nnumber) {
		return sqlSession.selectOne("noticeView",nnumber);
	}

	@Override
	public void noticeModify(NoticeVO noticeVO) {
		sqlSession.update("noticeModify", noticeVO);
	}

	@Override
	public void noticeDelete(int nnumber) {
		sqlSession.delete("noticeDelete", nnumber);
	}

	@Override
	public void faqModify(FaqVO faqVO) {
		sqlSession.update("faqModify", faqVO);
	}

	@Override
	public void faqDelete(int fnumber) {
		sqlSession.delete("faqDelete", fnumber);
	}

	@Override
	public void noticeInsert(NoticeVO noticeVO) {
		sqlSession.insert("noticeInsert",noticeVO);
	}

	@Override
	public void faqInsert(FaqVO faqVO) {
		sqlSession.insert("faqInsert", faqVO);
	}

	@Override
	public FaqVO faqView(int fnumber) {
		return sqlSession.selectOne("faqView", fnumber);
	}

	@Override
	public List<NoticeVO> noticeList(RecordCriteria recordCriteria) {
		return sqlSession.selectList("noticeRecordList",recordCriteria);
	}

	@Override
	public int noticeCount() {
		return sqlSession.selectOne("noticeCount");
	}

	@Override
	public List<FaqVO> faqList(RecordCriteria recordCriteria) {
		return sqlSession.selectList("faqRecordList", recordCriteria);
	}

	@Override
	public int faqCount() {
		return sqlSession.selectOne("faqCount");
	}

}
