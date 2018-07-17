package com.prj.nicarnaecar.dao;
import com.prj.nicarnaecar.vo.MemberVO;

public interface MemberDAO {
	// 회원등록
	public void memberInsert(MemberVO memberVO);
	
	// 회원정보 가져오기
	public MemberVO getMember(String cemail);
	
	// 회원정보 수정
	public void memberUpdate(MemberVO memberVO);
	
	// 회원정보 삭제
	public void memberDelete(String cemail);
	
	public MemberVO findID(MemberVO memberVO);
	public MemberVO findPW(MemberVO memberVO);
	
	public int idCheck(String cemail);
}
