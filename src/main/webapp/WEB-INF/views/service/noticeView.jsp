<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/service/sidebar.jsp"></jsp:include>
<script>
$(function() {
   $("#modifyOK").css({"display":"none"});
   
   $("#modifyBtn").on("click", function() {
      $("#ntitle").removeAttr("readonly");
      $("#ncontent").removeAttr("readonly");
      $("#modifyBtn").css({"display":"none"});
      $("#modifyOK").css({"display":""});
   });
   
   $("#modifyOK").on("click", function() {
      $("#noticeForm").attr("action","/service/noticeModifyOK").submit();
   })
   $("#deleteBtn").on("click", function() {
      $("#noticeForm").attr("action","/service/noticeDelete").submit();
   })
})
</script>
<title>Notice</title>
<div class="col-md-9">
<h2>Notice</h2>
   <div class="card">
    <div class="card-body">
        <!--Table-->
        <form id="noticeForm" method="post">
        <sec:csrfInput/>
        <table class="table table-responsive-md table-fixed">
            <tbody>
                <tr>
                    <td>글 번호</td>
                    <td colspan="4"><input type="text" name="nnumber" value="${view.nnumber }" readOnly class="form-control"/></td>
                </tr>
                <tr>
                    <td>작성일</td>
                    <td colspan="4"><input type="text" value="${view.ncdate }" readOnly class="form-control"/></td>
                </tr>
                <tr>
                    <td>글 제목</td>
                    <td colspan="4"><input type="text" id="ntitle" name="ntitle" value="${view.ntitle }" readOnly class="form-control"/></td>
                </tr>
                <tr>
                    <td>글 내용</td>
                    <td colspan="4"><textarea rows="5" id="ncontent" name="ncontent" readOnly class="form-control">${view.ncontent }</textarea></td>
                </tr>
                <tr>
                    <td>작성자 이메일</td>
                    <td colspan="4"><input type="text" value="${view.eemail }" readOnly class="form-control"/></td>
                </tr>
                <tr>
                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                         <td colspan="1">
                         <a id="modifyBtn" href="#" class="btn btn-primary">수정하기</a>
                         <a href="#" id="modifyOK" class="btn btn-primary">수정완료</a>
                         </td>
                   </sec:authorize>
                   <td colspan="2" align="right">
                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                      <a href="#" id="deleteBtn" class="btn btn-primary">삭제하기</a>
                   </sec:authorize>
                   <a href="/service/notice" class="btn btn-primary">목록으로</a>
                   </td>
                </tr>
            </tbody>
        </table>
        </form>
        <!--Table-->
    </div>
</div>

</div>

</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>