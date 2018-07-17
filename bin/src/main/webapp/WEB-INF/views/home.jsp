<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    
        <jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>

        <div class="view intro-2">
            <div class="full-bg-img">
                <div class="mask rgba-strong flex-center">
                    <div class="container">
                        <div class="white-text text-center wow fadeInUp">
							<div class="card-deck">
    	<button class="btn btn-primary waves-effect" style="font-size:40px;">

    	<i class="fa fa-calendar" aria-hidden="true" style="font-size:70px;margin-bottom:10px;"></i>
    	<br />날짜 검색</button>
		<button class="btn btn-info waves-effect" style="font-size:40px;">
      	<i class="fa fa-car" aria-hidden="true" style="font-size:70px;margin-bottom:10px;"></i>
      	<br />차량 검색</button>
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    
    
    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
