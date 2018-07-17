package com.prj.nicarnaecar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.LoginDAO;
import com.prj.nicarnaecar.vo.LoginVO;
import com.prj.nicarnaecar.vo.MemberVO;

@Service
public class LoginServiceImplXML implements LoginService{

	@Autowired
	@Qualifier("loginDAOImplXML")
	LoginDAO loginDAO;
	
	@Override
	public MemberVO getMember(LoginVO loginVO) {
		return loginDAO.getMember(loginVO);
	}


}
