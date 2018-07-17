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
	$("#modifyOK").on("click", function() {
		$("#faqForm").attr("action","/service/faqModifyOK").submit();
	})
	$("#deleteBtn").on("click", function() {
		$("#faqForm").attr("action","/service/faqDelete").submit();
	})
})
</script>
<title>FAQ</title>
<div class="col-md-9">
<h2>FAQ Modify</h2>
   <div class="card">
    <div class="card-body">
        <!--Table-->
        <form:form id="faqForm" method="post">
        <table class="table table-responsive-md table-fixed">
            <tbody>
            <input type="hidden" name=fnumber value="${faq.fnumber }"/>
                <tr>
                    <td>글 제목</td>
                    <td colspan="4"><input type="text" name="ftitle" class="form-control" value="${faq.ftitle }"/></td>
                </tr>
                <tr>
                    <td>글 내용</td>
                    <td colspan="4"><textarea rows="5" name="fcontent" class="form-control">${faq.fcontent }</textarea></td>
                </tr>
                <tr>
                    <td>작성자 이메일</td>
                    <td colspan="4"><input type="text" name="eemail" value="${user.username }" readOnly class="form-control"/></td>
                </tr>
                <tr>
                	<td>
                	<a href="#" id="modifyOK" class="btn btn-primary">수정완료</a>
                	</td>
                	<td></td>
                	<td align="right">
                	<a href="#" id="deleteBtn" class="btn btn-primary">삭제하기</a>
                	<a href="/service/faq" class="btn btn-primary">목록으로</a>
                	</td>
                </tr>
            </tbody>
        </table>
        </form:form>
        <!--Table-->
    </div>
</div>

</div>

</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>