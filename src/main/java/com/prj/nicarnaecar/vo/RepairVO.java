package com.prj.nicarnaecar.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class RepairVO {
   private int rnumber;
   private String vnumber;
   private int bnumber;
   private Date rin;
   private Date rout;
   private String rshop;
   private int rprice;
   private String rmemo;
   private String rtarget;
   private String rstate;
}