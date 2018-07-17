package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.EmployeeVO;

public interface EmployeeService {
		//직원리스트 불러오기
		public List<EmployeeVO> Elist();
		public List<EmployeeVO> Elist(RecordCriteria recordCriteria);
		public void Elist(HttpServletRequest request) throws Exception;
		int ECount();
		
		//직원정보 변경
		public void EUpdate(EmployeeVO employeeVO);
			
		//직원추가
		public void Einsert(EmployeeVO employeeVO);
		
		//직원 아이디 검색
		public int eidCheck(String eemail);
		
		//검색 리스트
		int searchRec(FindCriteria findCriteria) throws Exception;
      List<EmployeeVO> Elist(FindCriteria findCriteria);
}