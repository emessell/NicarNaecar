package com.prj.nicarnaecar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prj.nicarnaecar.dao.MemberDAO;
import com.prj.nicarnaecar.vo.MemberVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
class MemberTest {
	
	@Autowired
	@Qualifier("memberDAOImplXML")
	MemberDAO memberDAO;
	
	@Test
	void test() {
		MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.getMember("GUEST1@kh.com");
		System.out.println(memberVO.toString());
	}

}
