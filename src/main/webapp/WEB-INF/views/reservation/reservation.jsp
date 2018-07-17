<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
</head>
<style>
   .md-form{
      max-width:90%;
   }
   .card{
      margin:auto;
      margin-top:10%;
      max-width:90%;
   }
</style>
<script>
   function addComma(num) {
      var regexp = /\B(?=(\d{3})+(?!\d))/g;
       return num.toString().replace(regexp, ',');
   }

   $(function() {
         var bin2 = "${bookingVO.bin }";
        var bout2 = "${bookingVO.bout}";
        var bin = bin2.split('-');
        var bout = bout2.split('-');
        var dat1 = new Date(bin[0], bin[1], bin[2]);
        var dat2 = new Date(bout[0], bout[1], bout[2]);

      var diff = dat2 - dat1;
      var currDay = 24 * 60 * 60 * 1000;

      var num = addComma((parseInt(diff/currDay)+1) * ${vehicleVO.vprice});
      
      var priceInput = (parseInt(diff/currDay)+1) * ${vehicleVO.vprice};
      $("#priceInput").val(priceInput);
      $("#total").html("총 결제 금액은 <b>"+num+"원</b> 입니다.");
      $("#cancel").on("click", function() {
         location.href="/";
      });
   });
</script>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<title>Reservation Check</title>

<form action="/reservation/reservationOK" method="post">
<sec:csrfInput/>
<div class="container">
<div class="card">
<div class="card-body">
<div class="row">
   <div class="col-6" style="border-right: 1px dotted gray;">
         <div class="md-form">
             <input type="text" id="form1" readOnly class="form-control" readOnly value="${user.name }">
             <label for="form1" >예약자 명</label>
         </div>
         <div class="md-form">
             <input type="text" id="form2" readOnly class="form-control" readOnly value="${user.phone }">
             <label for="form2" >연락처</label>
         </div>
         <div class="md-form">
             <input type="text" id="form3" class="form-control" readOnly value="${bookingVO.bin }" name="bin">
             <label for="form3" >대여 일시</label>
         </div>
         <div class="md-form">
             <input type="text" id="form4" class="form-control" readOnly value="${bookingVO.bout }" name="bout">
             <label for="form4" >반납 일시</label>
         </div>
         <div class="md-form">
             <input type="text" id="form5" class="form-control" readOnly value="${vehicleVO.eoffice }">
             <label for="form5" >배차/반납지</label>
         </div>
         <div class="md-form">
             <input type="text" id="form7" class="form-control" readOnly value="${vehicleVO.vmodel }">
             <label for="form7" >차종</label>
         </div>
         <input type="hidden" value="${vehicleVO.vnumber }" name="vnumber">
         <input type="hidden" id="priceInput" name="bprice">
         <input type="hidden" name="eoffice" value="${vehicleVO.eoffice }"/>
   </div>
   <div class="col-6">
         <div class="row-6" >
         <h4>
            <span id="total"></span>
         </h4>
         <div class="row-6" style="float:right;">
         <br />
            <button type="submit" class="btn btn-pink" id="reservationOK"><i class="fa fa-plane pr-2" aria-hidden="true"></i>예약하기</button>
              <button type="button" class="btn btn-purple" id="cancel"><i class="fa fa-times pr-2" aria-hidden="true"></i>예약취소</button>
         </div>
   </div>
   </div>
</div>
</div>
</div>
</div>
</form>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>