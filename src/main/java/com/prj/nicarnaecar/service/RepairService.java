package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.RepairVO;
import com.prj.nicarnaecar.vo.VehicleVO;

public interface RepairService {
   public void repairList(HttpServletRequest request);
   public List<RepairVO> repairList();
   List<RepairVO> repairList(RecordCriteria recordCriteria);
   
   List<RepairVO> repairList(FindCriteria recordCriteria);
   public int totalRec();
   
   public List<VehicleVO> carList();
   
   public List<BookingVO> bookList(VehicleVO vehicleVO);
   
   public void repairIn(RepairVO repairVO);
   int searchRec(FindCriteria findCriteria);
   public void repairOut(String vnumber);
   public void repairUpdate(RepairVO repairVO);
   public void repairDelete(int rnumber);
   public void toComplete(int rnumber);
}