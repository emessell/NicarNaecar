<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
</head>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<title>My Page</title>

<style>
   #BtnDiv {
      margin:auto;
      margin-bottom: 10px;
   }
   .errmsg{color:red;}
   .passErr{color:red;}
</style>
<script>
   $(function() {
      
      $("input[name=confirm]").on("keyup",function(){
          if($("input[name=cpasswd]").val() != $("input[name=confirm]").val()){
             $(".passErr").text('비밀번호가 틀립니다.');
            $(this).focus();
         }else{
            $(".passErr").text('');
         }
      });
      
      $("#modifyBtn").on("click",function(e) {
    	  if($("input[name=cpasswd]").val() != $("input[name=confirm]").val()){
    		alert("비밀번호를 확인해주세요!");
    		return false;
    	  }
    	  else {
         	e.preventDefault();
          	$("form").submit();
    	  }
      });
   })
</script>

<div class="container" style="margin-top:2%;max-width:500px;">
   <form:form modelAttribute="memberVO" action="/reservation/modifyOK" method="post" id="modifyForm">
       <div class="card">
           <div class="header pt-3 blue darken-1">
               <div class="row d-flex justify-content-start">
                   <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5" style="color:white;">Modify</h3>
               </div>
           </div>
           <div class="card-body mx-4 mt-4">
               <div class="md-form">
                  <i class="fa fa-envelope prefix grey-text"></i>
                   <form:input path="cemail" class="form-control" readOnly="readOnly"/>
                   <label for="cemail">Your email</label>
               </div>
               <div class="md-form">
               <i class="fa fa-key prefix grey-text" aria-hidden="true"></i>
                 <form:password path="cpasswd" class="form-control" />
                 <label for="cpasswd">Your password</label>
                 <form:errors path="cpasswd" cssClass="errmsg" />
               </div>
               <div class="md-form">
               <i class="fa fa-key prefix grey-text" aria-hidden="true"></i>
               <input type="password" name="confirm" class="form-control" />
               <label for="password">Confirm password</label>
               <i class="passErr"></i>
            </div>
            <div class="md-form">
            <i class="fa fa-child prefix grey-text"></i>
            <form:input path="cname" class="form-control"/>
            <label for="cname">Your Name </label>
            <form:errors path="cname" cssClass="errmsg" />
            </div>
            <div class="md-form">
            <i class="fa fa-birthday-cake prefix grey-text"></i>
            <form:input path="cbirth" class="form-control"/>
            <label for="cbirth">Your Birth </label>
            <form:errors path="cbirth" cssClass="errmsg" />
            </div>
            <div class="md-form">
            <i class="fa fa-phone prefix grey-text" aria-hidden="true"></i>
            <form:input class="form-control" path="cphone"/>
            <label for="cphone">Your Phone</label>
            <form:errors path="cphone" cssClass="errmsg" />
            </div>
            </div>
               <div class="row" id="BtnDiv">
                   <div class="col"><button type="button" class="btn btn-primary btn-block z-depth-2" id="modifyBtn">MODIFY</button></div>
               </div>
           </div>
           </form:form> 
       </div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>