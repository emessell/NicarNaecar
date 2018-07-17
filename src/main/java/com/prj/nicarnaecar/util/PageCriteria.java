package com.prj.nicarnaecar.util;

public class PageCriteria {
   private int pageNumPerPage = 10; // 한페이지에 보여줄 페이지수
   private int startPage; // 한페이지의 시작페이지
   private int endPage; // 한페이지의 종료페이지

   private int totalRec; // 전체 레코드 수
   private int finalEndPage; // 최종페이지

   private boolean prev; // 이전페이지
   private boolean next; // 다음페이지

   private RecordCriteria recordCriteria; // numPerPage 를 가져오기위해,,(한 페이지에 보여줄 레코드 수 참조)

   private PageCriteria(RecordCriteria recordCriteria) {
      this.recordCriteria = recordCriteria;
   }

   public PageCriteria(RecordCriteria recordCritera, int totalRec) {
      this(recordCritera);
      this.totalRec = totalRec;
      this.init();
   }
   
   public PageCriteria(RecordCriteria recordCriteria, int totalRec, int pageNumPerPage) {
      this(recordCriteria);
      this.totalRec = totalRec;
      this.pageNumPerPage = pageNumPerPage;
      init();
   }

   private void init() {
      // 1) endPage 계산
      this.endPage = (int) ((Math.ceil(this.recordCriteria.getReqPage()/(double)this.pageNumPerPage)) * pageNumPerPage);
      
      // 2) startPage 계산
      this.startPage = this.endPage - this.pageNumPerPage + 1;
      
      // 3) finalEndPage 계산
      this.finalEndPage = (int) Math.ceil(this.totalRec/(double)this.recordCriteria.getNumPerPage());
      if(endPage > finalEndPage) {
         endPage = finalEndPage;
      }
      
      // 4) prev 계산
      this.prev = this.startPage == 1 ? false : true;
      
      
      // 5) next 계산
      this.next = (this.endPage * this.recordCriteria.getNumPerPage() >= this.totalRec) ? false : true;
   }

   public int getPageNumPerPage() {
      return pageNumPerPage;
   }
   
   public void setPageNumPerPage(int pageNumPerPage) {
      this.pageNumPerPage = pageNumPerPage;
   }

   public int getStartPage() {
      return startPage;
   }

   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }

   public int getEndPage() {
      return endPage;
   }

   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }

   public int getTotalRec() {
      return totalRec;
   }

   public void setTotalRec(int totalRec) {
      this.totalRec = totalRec;
   }

   public int getFinalEndPage() {
      return finalEndPage;
   }

   public void setFinalEndPage(int finalEndPage) {
      this.finalEndPage = finalEndPage;
   }

   public boolean isPrev() {
      return prev;
   }

   public void setPrev(boolean prev) {
      this.prev = prev;
   }

   public boolean isNext() {
      return next;
   }

   public void setNext(boolean next) {
      this.next = next;
   }

   public RecordCriteria getRecordCriteria() {
      return recordCriteria;
   }

   public void setRecordCriteria(RecordCriteria recordCriteria) {
      this.recordCriteria = recordCriteria;
   }
   
   public String getmakeURL(int reqPage) {
      StringBuffer str = new StringBuffer();

      str.append("reqPage="+reqPage);
      
      if(recordCriteria instanceof FindCriteria) {   // 객체(부모) instanceof 클래스(자식)   :  자식을 부모타입으로 형변환할수있는지 확인..?용도? 
         if(((FindCriteria)recordCriteria).getOption() != null) {
            str.append("&option="+((FindCriteria)recordCriteria).getOption());
         }
         if(((FindCriteria)recordCriteria).getSearch() != null) {
            str.append("&search="+((FindCriteria)recordCriteria).getSearch());
         }
         if(((FindCriteria)recordCriteria).getOselect() != null) {
            str.append("&oselect="+((FindCriteria)recordCriteria).getOselect());
         }
         if(((FindCriteria)recordCriteria).getEname() != null) {
            str.append("&ename="+((FindCriteria)recordCriteria).getEname());
         }
      }
      
      return str.toString();
   }
   
   @Override
   public String toString() {
      return "PageCriteria [pageNumPerPage=" + pageNumPerPage + ", startPage=" + startPage
            + ", endPage=" + endPage + ", totalRec=" + totalRec + ", finalEndPage=" + finalEndPage + ", prev=" + prev
            + ", next=" + next + ", recordCriteria=" + recordCriteria + "]";
   }
   
   

}