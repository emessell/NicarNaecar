package com.prj.nicarnaecar.dao;

import java.util.List;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.RepairVO;
import com.prj.nicarnaecar.vo.VehicleVO;

public interface RepairDAO {
   public List<RepairVO> repairList(); 
   
   public List<RepairVO> repairList(RecordCriteria recordCriteria); 
   
   public int totalRec(); 
   
   public List<VehicleVO> carList();
   
   public List<BookingVO> bookList(VehicleVO vehicleVO);
   
   public void repairIn(RepairVO repairVO);
   
   List<RepairVO> repairList(FindCriteria recordCriteria);
   
   public int searchRec(FindCriteria findCriteria);

   public void toReparing(RepairVO repairVO);

   public void repairOut(String vnumber);

   public void repairUpdate(RepairVO repairVO);

   public void repairDelete(int rnumber);
   
   public void toComplete(int rnumber);
   }