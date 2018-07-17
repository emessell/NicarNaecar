<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:authentication property="principal" var="user" scope="session"/>
   
    <style>
        .intro-2 {
            background: url("https://www.rhinocarhire.com/CorporateSite/media/Site-Images/new/background.jpg")no-repeat center center;
            background-size: cover;
        }
        .view {
            margin-top: -56px;
        }
        .navbar {
            z-index: 1;
        }
    </style>
<script>
$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
</script>
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.0/js/mdb.min.js"></script>
    <!--Main Navigation-->
    <header>
	<nav class="navbar navbar-expand-lg navbar-dark blue darken-1">
            <div class="container">
            
            <sec:authorize access="isAnonymous() or hasRole('ROLE_USER')">
                <a class="navbar-brand" href="/"><img  src="/resources/IMG/Logo2.png"></a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            	<a class="navbar-brand" href="/admin/profitChart"><img  src="/resources/IMG/Logo2.png"></a>
            </sec:authorize>  
            
            
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent, #navbarSupportedContent2" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-right:40%;">
                    <ul class="navbar-nav mr-auto">
                    
                    <sec:authorize access="isAnonymous() or hasRole('ROLE_USER')">
                        <li class="nav-item">
                           <a class="nav-link" href="/reservation/reservationCheck" style="font-family: '고딕'"><strong>예약 조회</strong></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/search/vehicleSearch" style="font-family: '고딕'"><strong>차량 조회</strong></a>
                        </li>
                    </sec:authorize>    
                    
                        <li class="nav-item">
                            <a class="nav-link" href="/service/notice" style="font-family:'고딕'"><strong>고객센터</strong></a>
                        </li>
                        
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li >
                        	<a class="nav-link" href="/admin/profitChart" style="font-family:'고딕'"><strong>관리자 페이지</strong></a>
                        </li>
                    </sec:authorize> 
                    
                    </ul>
                    </div>
                <div class="collapse navbar-collapse" id="navbarSupportedContent2">
                <ul class="navbar-nav mr-auto">
                
                	<sec:authorize access="isAnonymous()">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login/login">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/member/join">회원가입</a>
                        </li>
                    </sec:authorize>
				
				
					<sec:authorize access="hasRole('ROLE_USER')">
							<li class="nav-item">
                            	<a class="nav-link" href="${pageContext.request.contextPath}/reservation/myPage">내 정보 확인</a>
                        	</li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        	<li class="nav-item">
                            	<a class="nav-link" href="${pageContext.request.contextPath}/login/logout">로그아웃</a>
                        	</li>
					</sec:authorize>
                    </ul>
                </div>
            </div>
        </nav>
     </header>
     <!--Main Navigation-->
        