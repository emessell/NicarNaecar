package com.prj.nicarnaecar.dao;

import com.prj.nicarnaecar.vo.LoginVO;
import com.prj.nicarnaecar.vo.MemberVO;

public interface LoginDAO {
	
	// 아이디와 비밀번호로 회원정보 가져오기
	public MemberVO getMember(LoginVO loginVO);
}
