package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.EmployeeDAO;
import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.EmployeeVO;

@Service
public class EmployeeServiceImplXML implements EmployeeService {
	
	@Autowired
	@Qualifier("employeeDAOImplXML")
	EmployeeDAO employeeDAO;
	
	@Override
	public List<EmployeeVO> Elist() {
		return employeeDAO.Elist();
	}

	@Override
	public void EUpdate(EmployeeVO employeeVO) {
		employeeDAO.EUpdate(employeeVO);
	}

	@Override
	public void Einsert(EmployeeVO employeeVO) {
		employeeDAO.Einsert(employeeVO);
	}

	@Override
	public List<EmployeeVO> Elist(RecordCriteria recordCriteria) {
		return employeeDAO.Elist(recordCriteria);
	}

	@Override
	public int ECount() {
		return employeeDAO.ECount();
	}

	@Override
	public int eidCheck(String eemail) {
		return employeeDAO.eidCheck(eemail);
	}
	
	@Override
   public List<EmployeeVO> Elist(FindCriteria findCriteria) {
      return employeeDAO.Elist(findCriteria);
   }
   @Override
   public int searchRec(FindCriteria findCriteria) throws Exception {
      return employeeDAO.searchRec(findCriteria);
   }

   @Override
   public void Elist(HttpServletRequest request) throws Exception{
      
      int reqPage = 0;
      if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
         reqPage = 1;
      } else {
         reqPage = Integer.parseInt(request.getParameter("reqPage"));
      }
      String oselect = null;
      String ename = null;
      if(request.getParameter("oselect")=="") {
         oselect = null;
      }else {
         oselect = request.getParameter("oselect");
      }
      if(request.getParameter("ename")=="") {
         ename = null;
      }else {
         ename = request.getParameter("ename");
      }
      
      List<EmployeeVO> list = null;
      RecordCriteria rc = null;
      PageCriteria pc = null;
      
      
      if(oselect == null && ename == null) {
         rc = new RecordCriteria(reqPage);
         list = Elist(rc);
         int totalRec = ECount();
         pc = new PageCriteria(rc,totalRec);
      }else {
         rc= new FindCriteria(reqPage, null, oselect, ename);
         list = this.Elist((FindCriteria)rc);
         int totalRec=searchRec((FindCriteria)rc);
         pc = new PageCriteria(rc,totalRec);
         
         request.setAttribute("findCriteria", (FindCriteria)rc);
      }
               
      request.setAttribute("list", list);
      request.setAttribute("page", pc);
      request.setAttribute("employeeVO", new EmployeeVO());
   }


}