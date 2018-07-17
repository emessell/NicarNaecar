<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
   .md-form{
      max-width:90%;
   }
   .card{
      margin:auto;
      margin-top:10%;
      max-width:90%;
   }
   
   #datepicker div{
       background-color: white;
    }
    
    #datepicker span , #datepicker a{
       font-family: 고딕;
   }
    
    #datepicker .ui-state-default{
        border: none;
    }
      #datepicker .ui-state-highlight{
         background-color: #33b5e5;
         border: none;
      }
      
      #datepicker span , #datepicker a{
         font-family: 고딕;
      }
</style>
<script>
   function addComma(num) {
      var regexp = /\B(?=(\d{3})+(?!\d))/g;
       return num.toString().replace(regexp, ',');
   }
$(function() {
      var minDate = '${bookingVO.bout }';
      var maxDate = '';
      var bout = '';
      
      if(${date == null}) {
         maxDate = '+30d';
      } else {
         maxDate = '${date }-1';
      }
       $( "#datepicker" ).datepicker({
            dateFormat: 'yy-mm-dd',
              minDate: minDate,
              maxDate: maxDate,
              
            onSelect: function( selectedDate ) {
               bout = $("#datepicker").datepicker('getDate');
               bout = bout.toLocaleDateString();
               var bout2 = bout.replace(/. /g,'-');
             bout = bout2.replace('.','');
                  
           $("#bout").val(bout);
            }
        })
        
        $("#priceView").on("click",function() {
              var vbout = '${bookingVO.bout }';
              var vbin = '${bookingVO.bin }';
              var vbout2 = vbout.split('-');
              var vbin2 = vbin.split('-');
              var vdat1 = new Date(vbin2[0], vbin2[1], vbin2[2]);
              var vdat2 = new Date(vbout2[0], vbout2[1], vbout2[2]);
              var vdiff = vdat2 - vdat1;
            var vcurrDay = 24 * 60 * 60 * 1000;
              var vprice = '${bookingVO.bprice}' / ((vdiff/vcurrDay)+1);
              console.log(vbout);
              console.log(vbin);
              
              var pbin = minDate.split('-');
              var pbout = bout.split('-');
              var dat1 = new Date(pbin[0], pbin[1], pbin[2]);
              var dat2 = new Date(pbout[0], pbout[1], pbout[2]);
              var diff = dat2 - dat1;
            var currDay = 24 * 60 * 60 * 1000;

            var num = addComma((parseInt(diff/currDay)) * vprice);
            var priceInput = (parseInt(diff/currDay)) * vprice;
            $("#priceInput").val(${bookingVO.bprice}+priceInput);
            $("#total").html("총 결제 금액은 <b>"+num+"원</b> 입니다.");
        })
        
        $("#checkbtn").hide();
       $("#priceView").click(function() {
          $("#checkbtn").show();
       })
       
       
       $("#cancel").click(function() {
    	   window.history.go(-1);
       })
   });
</script>
<title>Reservation Check</title>

<form:form modelAttribute="bookingVO" action="/reservation/extendOK" method="post">
<sec:csrfInput/>
<div class="container">
<div class="card">
<div class="card-body">
<div class="row">
   <div class="col" style="border-right: 1px dotted gray;">
         <h4 style="margin-left:20%;">날짜 선택</h4>
         <div id="datepicker"></div>
         <input type="hidden" name="bnumber" value="${bookingVO.bnumber }"/>
         <input type="text" name="bout" id="bout"/>
         <br />
         <br />
         <br />
         <button type="button" id="priceView" class="btn btn-primary">조회하기</button>
   </div>
   
   <div class="col">
         <div class="row" >
         <input type="hidden" id="priceInput" name="bprice"/>
         <h2>
            <span id="total"></span>
         </h2>
         </div>
         <div class="row" id="checkbtn" style="float:right;">
            <button type="submit" class="btn btn-pink" id="extendOK"><i class="fa fa-plane pr-2" aria-hidden="true"></i>예약연장</button>
            <button type="button" class="btn btn-purple" id="cancel"><i class="fa fa-times pr-2" aria-hidden="true"></i>돌아가기</button>
   </div>
   </div>
</div>
</div>
</div>
</div>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>