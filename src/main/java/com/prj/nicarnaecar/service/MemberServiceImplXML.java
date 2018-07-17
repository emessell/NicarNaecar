package com.prj.nicarnaecar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.MemberDAO;
import com.prj.nicarnaecar.vo.MemberVO;

@Service
public class MemberServiceImplXML implements MemberService {

	@Autowired
	@Qualifier("memberDAOImplXML")
	MemberDAO memberdao;
	
	@Override
	public void memberInsert(MemberVO memberVO) {
		memberdao.memberInsert(memberVO);
	}

	@Override
	public MemberVO getMember(String cemail) {
		return memberdao.getMember(cemail);
	}

	@Override
	public void memberUpdate(MemberVO memberVO) {
		memberdao.memberUpdate(memberVO);
	}

	@Override
	public void memberDelete(String cemail) {
		memberdao.memberDelete(cemail);
	}

	@Override
	public MemberVO findID(MemberVO memberVO) {
		return memberdao.findID(memberVO);
	}

	@Override
	public MemberVO findPW(MemberVO memberVO) {
		return memberdao.findPW(memberVO);
	}

	@Override
	public int idCheck(String cemail) {
		return memberdao.idCheck(cemail);
	}

}
