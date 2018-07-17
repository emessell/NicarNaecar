package com.prj.nicarnaecar.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.vo.LoginVO;
import com.prj.nicarnaecar.vo.MemberVO;

@Repository
public class LoginDAOImplXML implements LoginDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO getMember(LoginVO loginVO) {
		return sqlSession.selectOne("getMemberLogin",loginVO);
	}

	
	
	
}
