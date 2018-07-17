package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prj.nicarnaecar.dao.RepairDAO;
import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.PageCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.RepairVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Service
public class RepairServiceImplXML implements RepairService{
   
   @Autowired
   @Qualifier("repairDAOImplXML")
   RepairDAO repairDAO;
   
   @Override
   public List<RepairVO> repairList() {
      return repairDAO.repairList();
   }
   
   @Override
   public List<RepairVO> repairList(RecordCriteria recordCriteria) {
      return repairDAO.repairList(recordCriteria);
   }
   @Override
   public List<RepairVO> repairList(FindCriteria recordCriteria) {
      return repairDAO.repairList(recordCriteria);
   }
   
   @Override
   public int totalRec() {
      return repairDAO.totalRec();
   }
   @Override
   public int searchRec(FindCriteria findCriteria) {
      return repairDAO.searchRec(findCriteria);
   }
   @Override
   public List<VehicleVO> carList(){
      return repairDAO.carList();
   }
   @Override
   public List<BookingVO> bookList(VehicleVO vehicleVO){
      return repairDAO.bookList(vehicleVO);
   }
   
   @Override
   public void repairIn(RepairVO repairVO) {
      repairDAO.repairIn(repairVO);
      repairDAO.toReparing(repairVO);
   }
   
   @Override
   public void repairList(HttpServletRequest request) {
      int reqPage = 0;
      if(request.getParameter("reqPage") == null) {
         reqPage=1;
      }else {
         reqPage=Integer.parseInt(request.getParameter("reqPage"));
      }
      
      String search = null;
      if(request.getParameter("serach")=="") {
         search = null;
      }else {
         search = request.getParameter("search");
      }
       List<RepairVO> list = null;
         RecordCriteria rc = null;
         PageCriteria pc = null;
         List<VehicleVO> carlist = carList();
         
       if(search==null) {
          rc = new RecordCriteria(reqPage);
         int totalrec = this.totalRec();
         list = repairList(rc);
         pc = new PageCriteria(rc, totalrec);
       }else {
      rc = new FindCriteria(reqPage, null, search);
      int totalrec = searchRec((FindCriteria)rc);
      list = repairList((FindCriteria)rc);
      pc = new PageCriteria(rc, totalrec);
       }
      
      request.setAttribute("carlist", carlist);
      request.setAttribute("list", list);
      request.setAttribute("pc", pc);
   }

   @Override
   public void repairOut(String vnumber) {
      repairDAO.repairOut(vnumber);
   }

   @Override
   public void repairUpdate(RepairVO repairVO) {
      repairDAO.repairUpdate(repairVO);
   }

   @Override
   public void repairDelete(int rnumber) {
      repairDAO.repairDelete(rnumber);
   }

   @Override
   public void toComplete(int rnumber) {
      repairDAO.toComplete(rnumber);
   }
}