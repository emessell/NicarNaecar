<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<style>
   .table-fixed {
    table-layout: fixed;
    overflow: hidden;
}
</style>
<jsp:include page="/WEB-INF/views/service/sidebar.jsp"></jsp:include>
<title>Notice</title>
<div class="col-md-9">
<h2>Notice</h2>
   <div class="card">
    <div class="card-body">

        <!--Table-->
        <table class="table table-hover table-fixed">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
         <span style="float:right;"><a href="/service/noticeWrite">글쓰기</a></span>
      </sec:authorize>
            <!--Table head-->
            <thead>
                <tr>
                    <th>#</th>
                    <th colspan="4">제목</th>
                    <th colspan="2">작성일</th>
                </tr>
            </thead>
            <!--Table head-->

            <!--Table body-->
            <tbody>
            <c:forEach items="${list }" var="notice">
                <tr>
                    <th scope="row">${notice.nnumber }</th>
                    <td colspan="4"><a class="text-dark" href="/service/noticeView?nnumber=${notice.nnumber }">${notice.ntitle }</a></td>
                    <td colspan="2">${notice.ncdate }</td>
                </tr>
                </c:forEach>
            </tbody>
            <!--Table body-->
        </table>
        <!--Table-->
    </div>
</div>

<table style=" margin:auto;">
         <tr>
            <td>
               <ul id="pageing"
                  class="pagination pagination-sm justify-content-center">
                  <c:if test="${page.prev }">
                     <li class="page-item"><a class="page-link"
                        href="notice?page.finalEndPage">◀</a></li>
                     <li class="page-item"><a class="page-link"
                        href="notice?${page.getmakeURL(page.startPage-1) }" aria-label="Previous">
                           <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
                     </a></li>
                  </c:if>

                  <c:forEach begin="${page.startPage }" end="${page.endPage }"
                     var="PAGE">
                     <c:if test="${page.recordCriteria.reqPage == PAGE }">
                        <li class="page-item active"><a class="page-link" href="javascript:void(0)">${PAGE }</a></li>
                     </c:if>
                     <c:if test="${page.recordCriteria.reqPage != PAGE }">
                        <li class="page-item"><a class="page-link"
                           href="notice?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
                     </c:if>
                  </c:forEach>

                  <c:if test="${page.next }">
                     <li class="page-item"><a class="page-link"
                        href="notice?${page.getmakeURL(page.endPage+1) }" aria-label="Next">
                           <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
                     </a></li>
                     <li class="page-item"><a class="page-link"
                        href="notice?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
                  </c:if>
               </ul>
            </td>
          </tr>
      </table> 

</div>

</div>
</div>



<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>