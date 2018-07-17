package com.prj.nicarnaecar.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.vo.MemberVO;

@Repository
public class MemberDAOImplXML implements MemberDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void memberInsert(MemberVO memberVO) {
		sqlSession.insert("memberInsert",memberVO);
	}

	@Override
	public MemberVO getMember(String cemail) {
		return sqlSession.selectOne("getMember",cemail);
	}

	@Override
	public void memberUpdate(MemberVO memberVO) {
		sqlSession.update("memberUpdate",memberVO);
	}

	@Override
	public void memberDelete(String cemail) {
		sqlSession.delete("memberDelete",cemail);
	}

	@Override
	public MemberVO findID(MemberVO memberVO) {
		return sqlSession.selectOne("findID",memberVO);
	}

	@Override
	public MemberVO findPW(MemberVO memberVO) {
		return sqlSession.selectOne("findPW",memberVO);
	}

	@Override
	public int idCheck(String cemail) {
		return sqlSession.selectOne("idCheck", cemail);
	}

}