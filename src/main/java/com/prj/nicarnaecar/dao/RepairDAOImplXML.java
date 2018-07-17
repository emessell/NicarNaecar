package com.prj.nicarnaecar.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.util.FindCriteria;
import com.prj.nicarnaecar.util.RecordCriteria;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.RepairVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Repository
public class RepairDAOImplXML implements RepairDAO {
   
   @Autowired
   SqlSession sqlSession;

   @Override
   public List<RepairVO> repairList() {
      return sqlSession.selectList("listAll");
   }

   @Override
   public List<RepairVO> repairList(RecordCriteria recordCriteria) {
      return sqlSession.selectList("repairlist", recordCriteria);
   }
   @Override
   public List<RepairVO> repairList(FindCriteria recordCriteria) {
      return sqlSession.selectList("repairsearch", recordCriteria);
   }

   @Override
   public int totalRec() {
      return sqlSession.selectOne("totalRec");
   }

   @Override
   public List<VehicleVO> carList() {
      return sqlSession.selectList("carList");
   }

   @Override
   public List<BookingVO> bookList(VehicleVO vehicleVO) {
      return sqlSession.selectList("bookList", vehicleVO);
   }

   @Override
   public void repairIn(RepairVO repairVO) {
      sqlSession.insert("repairIn", repairVO);
   }

   @Override
   public int searchRec(FindCriteria findCriteria) {
      return sqlSession.selectOne("rsearchRec", findCriteria);
   }

   @Override
   public void toReparing(RepairVO repairVO) {
      sqlSession.update("toReparing", repairVO);
   }

   @Override
   public void repairOut(String vnumber) {
      sqlSession.update("toWaiting",vnumber);
   }

   @Override
   public void repairUpdate(RepairVO repairVO) {
      sqlSession.update("repairUp",repairVO);
   }

   @Override
   public void repairDelete(int rnumber) {
      sqlSession.delete("rdelete", rnumber);
   }
   
   @Override
   public void toComplete(int rnumber) {
      sqlSession.update("toComplete", rnumber);
   }
}