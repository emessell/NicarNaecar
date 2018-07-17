package com.prj.nicarnaecar.util;

public class FindCriteria extends RecordCriteria {
   private String option;   // 검색타입
   private String search;   // 검색단어
   private String oselect;
   private String ename;
   
   public FindCriteria() {   //
   }
   
   public FindCriteria(int reqPage,String search) {
      super(reqPage);
      this.search = search;
   }

   public FindCriteria(int reqPage, String option, String search) {
      super(reqPage);
      this.option = option;
      this.search = search;
   }
   
   public FindCriteria(int reqPage, String option, String oselect, String ename) {
      super(reqPage);
      this.ename = ename;
      this.oselect = oselect;
   }
   
   public String getOption() {
      return option;
   }

   public String getOselect() {
      return oselect;
   }

   public void setOselect(String oselect) {
      this.oselect = oselect;
   }

   public String getEname() {
      return ename;
   }

   public void setEname(String ename) {
      this.ename = ename;
   }

   public void setOption(String option) {
      this.option = option;
   }

   public String getSearch() {
      return search;
   }

   public void setSearch(String search) {
      this.search = search;
   }

   @Override
   public String toString() {
      return "FindCriteria [option=" + option + ", search=" + search + "]";
   }
}