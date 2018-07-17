<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/service/sidebar.jsp"></jsp:include>
<script>
	$(function(){
		//보내기 버튼 클릭
		$("#sendBtn").on("click",(function(){
			alert("1:1 문의 메일이 발송되었습니다.");
		})
		); 
		//엔터키 발송 방지
		$('input').keydown(function(){
			if(event.keyCode === 13){
				event.preventDefault();
			}
		})
	})
</script>
<title>1 : 1</title>
<div class="col-md-9">

<!-- Material form contact -->
<form:form action="/service/sendMailOK" method="post">
    <p class="h4 text-center mb-4">Write to us</p>

    <!-- Material input text -->
    <div class="md-form">
        <i class="fa fa-user prefix grey-text"></i>
        <input type="text" id="materialFormContactNameEx" class="form-control" name="name">
        <label for="materialFormContactNameEx">Your name</label>
    </div>

    <!-- Material input email -->
    <div class="md-form">
        <i class="fa fa-envelope prefix grey-text"></i>
        <input type="email" id="materialFormContactEmailEx" class="form-control" name="sendEmail">
        <label for="materialFormContactEmailEx">Your email</label>
    </div>

    <!-- Material input subject -->
    <div class="md-form">
        <i class="fa fa-tag prefix grey-text"></i>
        <input type="text" id="materialFormContactSubjectEx" class="form-control" name="subject">
        <label for="materialFormContactSubjectEx">Subject</label>
    </div>

    <!-- Material textarea message -->
    <div class="md-form">
        <i class="fa fa-pencil prefix grey-text"></i>
        <textarea type="text" id="materialFormContactMessageEx" class="form-control md-textarea" rows="3" name="body"></textarea>
        <label for="materialFormContactMessageEx">Your message</label>
    </div>

    <div class="text-center mt-4">
        <button class="btn btn-outline-secondary" type="submit" id="sendBtn">Send<i class="fa fa-paper-plane-o ml-2"></i></button>
    </div>
</form:form>
<!-- Material form contact -->

</div>
</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>