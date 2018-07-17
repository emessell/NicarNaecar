<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/admin/sidebar.jsp"></jsp:include>

<script>
function addComma(num) {
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
    return num.toString().replace(regexp, ',');
}
</script>
<style>
	td{
		padding: 10px 10px 10px 10px;
	}
</style>
<div class="col-md-10">

	<table  class="table table-hover table-responsive-md table-fixed" style="width:100%;">
		<h2>인도할 차량</h2>
			<thead>
                <tr>
                    <th><h4>예약번호</h4></th>
                    <th><h4>차량번호</h4></th>
                    <th><h4>인도날짜</h4></th>
                    <th><h4>반납날짜</h4></th>
                    <th><h4>가격</h4></th>
                    <th><h4>지역</h4></th>
                    <th><h4>인도</h4></th>
                </tr>
            </thead>
	    <tbody id="deliveryTable">
		    <script>
		    var str = "";
			    <c:forEach items="${list }" var="list" varStatus="index">
			        str += "<tr>";
			        str += "<td>${list.bnumber}</td>";
			        str += "<td>${list.vnumber}</td>";
			        str += "<td>${list.bin}</td>";
			        str += "<td>${list.bout}</td>";
			        str += "<td>"+addComma(${list.bprice})+"원</td>";
			        str += "<td>${list.eoffice}</td>"
			        str += "<td><a class='btn btn-info px-3' href='/admin/deliveryOK/${list.bnumber}'>인도</a></td>";
			        str += "</tr>";
			     </c:forEach>
			     $("#deliveryTable").html(str);
			</script>
	    </tbody>
	</table>
	
	<table style=" margin:auto;">
         <tr>
            <td>
               <ul id="pageing"
                  class="pagination pagination-sm justify-content-center">
                  <c:if test="${page.prev }">
                     <li class="page-item"><a class="page-link"
                        href="delivery?page.finalEndPage">◀</a></li>
                     <li class="page-item"><a class="page-link"
                        href="delivery?${page.getmakeURL(page.startPage-1) }" aria-label="Previous">
                           <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
                     </a></li>
                  </c:if>
                  <c:forEach begin="${page.startPage }" end="${page.endPage }" var="PAGE">
                     <c:if test="${page.recordCriteria.reqPage == PAGE }">
                        <li class="page-item active"><a class="page-link" href="javascript:void(0)">${PAGE }</a></li>
                     </c:if>
                     <c:if test="${page.recordCriteria.reqPage != PAGE }">
                        <li class="page-item"><a class="page-link"
                           href="delivery?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
                     </c:if>
                  </c:forEach>
                  <c:if test="${page.next }">
                     <li class="page-item"><a class="page-link"
                        href="delivery?${page.getmakeURL(page.endPage+1) }" aria-label="Next">
                           <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
                     </a></li>
                     <li class="page-item"><a class="page-link"
                        href="delivery?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
                  </c:if>
               </ul>
            </td>
          </tr>
      </table>
	
	
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>