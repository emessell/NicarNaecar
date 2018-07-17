package com.prj.nicarnaecar.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.VehicleVO;

public interface VehicleService {

   // 차량 전체 목록
         public List<VehicleVO> VehicleList();
         public List<VehicleVO> VehicleList(RecordCriteria recordCriteria);
         public void VehicleList(HttpServletRequest request);
         public int VehicleListCount();
         public List<VehicleVO> SearchVehicleList(FindCriteria findCriteria);
         public int SearchVehicleListCount(FindCriteria findCriteria);
      
            
   // 차량 추가
         public void insertCar(VehicleVO vehicleVO);
   
   // 차량 수정
         public void updateCar(VehicleVO vehicleVO);
                  
   // 폐차
         public void deleteCar(String vnumber); 
}