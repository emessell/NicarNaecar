<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<style>
.accordion {
    background-color: #fff;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    border-top: 1px solid #CFCFCF;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
}

.active, .accordion:hover {
    background: linear-gradient(to top right, #5ca0ed, #9fc9ea);
    z-depth: 1;
}

.panel {
    padding: 0 18px;
    display: none;
    background-color: white;
    overflow: hidden;
}
</style>
<jsp:include page="/WEB-INF/views/service/sidebar.jsp"></jsp:include>
<title>FAQ</title>
<div class="col-md-9">
<h2 class="animated pulse">FAQ</h2>

<c:forEach items="${list }" var="faq" varStatus="status">
<c:if test="${(status.count%2) eq 0 }">
<button class="accordion animated rotateInUpLeft">${faq.ftitle }</button>
</c:if>
<c:if test="${(status.count%2) eq 1 }">
<button class="accordion animated rotateInUpRight">${faq.ftitle }</button>
</c:if>
<div class="panel">
<br />
  <p>${faq.fcontent }</p>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/service/faqModify/${faq.fnumber }">수정하기</a>
  </sec:authorize>
  <br />
</div>
</c:forEach>
<table style="width:70%; margin:auto;">
         <tr>
            <td>
               <ul id="pageing"
                  class="pagination pagination-sm justify-content-center">
                  <c:if test="${page.prev }">
                     <li class="page-item"><a class="page-link"
                        href="faq?page.finalEndPage">◀</a></li>
                     <li class="page-item"><a class="page-link"
                        href="faq?${page.getmakeURL(page.startPage-1) }" aria-label="Previous">
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
                           href="faq?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
                     </c:if>
                  </c:forEach>

                  <c:if test="${page.next }">
                     <li class="page-item"><a class="page-link"
                        href="faq?${page.getmakeURL(page.endPage+1) }" aria-label="Next">
                           <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
                     </a></li>
                     <li class="page-item"><a class="page-link"
                        href="faq?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
                  </c:if>
               </ul>
            </td>
          </tr>
      </table> 
<sec:authorize access="hasRole('ROLE_ADMIN')">
<div align='right'>
   <a href="/service/faqWrite">글쓰기</a>
</div>
</sec:authorize>
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}
</script>
</div>
       


</div>
</div>



<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>