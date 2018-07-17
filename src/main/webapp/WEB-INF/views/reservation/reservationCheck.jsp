<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyyMMdd" var="nowDate" />   --%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<script>
function addComma(num) {
    var regexp = /\B(?=(\d{3})+(?!\d))/g;
     return num.toString().replace(regexp, ',');
 }
$(function() {
	$("#cancelBtn").click(function() {
		alert("예약이 취소되었습니다.");
	});
});
function detail(i) {
	$.ajax( {
		type : "GET",
		url : "/detail/"+document.getElementsByName('bnumber')[i].value,
		dataType : "JSON",
        success : function(data) {
        	var str = "";
        	$.each(data, function() {
        		str += "<table><tr>";
        		str += "<td><b>" + this.pmemo +"</b></td><td>" +addComma(this.pamount)+ "원<br></td>";
        		str += "</tr></table>";
        	})
        	$(".modal-body").html(str);
        },
        error : function(e) {
        	console.log(e);
        }
	})
}
</script>
</head>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<title>Reservation Check</title>

<div class="container" style="margin-top:2%;">
<h2>Reservation Check</h2>
   <div class="card">
    <div class="card-body">
        <!--Table-->
        <table class="table table-hover table-responsive-md table-fixed">
            <!--Table head-->
            <thead>
                <tr>
                    <th>예약번호</th>
                    <th>예약내역</th>
                    <th>대여시작일</th>
                    <th>대여종료일</th>
                    <th>상태</th>
                    <th>지역</th>
                    <th>연장/취소</th>
                    <th>상세조회</th>
                </tr>
            </thead>
            <!--Table head-->

            <!--Table body-->
            <tbody>
            
            <c:forEach items="${list }" var="book" varStatus="i">
                <tr>
                    <th scope="row">${book.bnumber }</th>
                    <td>${book.vnumber }</td>
                    <td>${book.bin }</td>
                    <td>${book.bout }</td>
		            <td>${book.bstatus }</td>
		            <td>${book.eoffice }</td>
		            <td>
		                <c:choose>
			               <c:when test='${book.bstatus eq "예약"}'>
					           <a id="cancelBtn" href="/reservation/cancel/${book.bnumber }" class="btn btn-mdb-color btn-sm">취소</a>
					       </c:when>
					       <c:when test='${book.bstatus eq "사용중"}'>
					           <a id="extendBtn" href="/reservation/extend/${book.bnumber }" class="btn btn-mdb-color btn-sm">연장</a>
					       </c:when>
				        </c:choose>
                    </td>		                    
		            <td>
		               <input type="hidden" name="bnumber" value='${book.bnumber }' />
		               <button class="btn btn-mdb-color btn-sm" data-toggle='modal' data-target='#detail' onClick="detail('${i.index}');">상세조회</button>
		            </td>
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
                        href="reservationCheck?page.finalEndPage">◀</a></li>
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
                           href="reservationCheck?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
                     </c:if>
                  </c:forEach>

                  <c:if test="${page.next }">
                     <li class="page-item"><a class="page-link"
                        href="reservationCheck?${page.getmakeURL(page.endPage+1) }" aria-label="Next">
                           <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
                     </a></li>
                     <li class="page-item"><a class="page-link"
                        href="reservationCheck?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
                  </c:if>
               </ul>
            </td>
          </tr>
      </table> 
</div>


<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">예약 상세정보</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            
            
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>