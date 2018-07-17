<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<title>Date Search</title>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <script>
 function addComma(num) {
      var regexp = /\B(?=(\d{3})+(?!\d))/g;
       return num.toString().replace(regexp, ',');
   }
   $(function() {
      var bin = "";
      var bout = "";
      var eoffice ="";
      $("#reservationBtn").hide();
     var dates = $( "#from, #to " ).datepicker({

     dateFormat: 'yy-mm-dd',
     showMonthAfterYear: true,
     minDate:-0,
     maxDate:'+30d',
     onSelect: function( selectedDate ) {
       var option = this.id == "from" ? "minDate" : "maxDate",
         instance = $( this ).data( "datepicker" ),
         date = $.datepicker.parseDate(
           instance.settings.dateFormat ||
           $.datepicker._defaults.dateFormat,
           selectedDate, instance.settings );
       dates.not( this ).datepicker( "option", option, date );
       
       var fromDate = $("#from").datepicker('getDate');
       $("#fromInput").val(fromDate.toLocaleDateString());
       
       var toDate = $("#to").datepicker('getDate');
       $("#toInput").val(toDate.toLocaleDateString());
     }
     });
     
     $("#select1").change(function() {
        $("#select2").val($("#select1 option:selected").val()).attr("selected","selected");
        $("#select2").attr("disabled","true");
        eoffice = $("#select1 option:selected").val();
      });
     
     $("#dateSearchBtn").on("click", function() {
        if($("#fromInput").val() == "" || $("#toInput").val() == "" ||
              $("#select1 option:selected").val() == "") {
           alert("날짜와 지점을 선택하세요!");
           return;
        }
        var bin2 = $("#fromInput").val().replace(/. /g,'-');
        bin = bin2.replace('.','');
        var bout2 = $("#toInput").val().replace(/. /g,'-');
        bout = bout2.replace('.','');
      
        var eoffice = $("#select1 option:selected").val();
        var str = "";
        $.ajax({
            type : "GET",
            url : "/search/dateSearchBtn/"+bin+"/"+bout+"/"+eoffice,
            dataType : "JSON",
            success : function(data) {
               var a = 1;
               $("#carListText").html('<br><h4 style="margin-left:40%;">차량선택</h4>*오늘 ('+'${nowDate }'+') 주유 시세');
               $.each(data, function() {
            	   
                  str += "<tr>";
                  
                  str += "<th scope='row'>";
                  str += '<div class="custom-control custom-radio">';
                  str += '<input type="radio" id="customRadio' + a + '" name="customRadio" class="custom-control-input" value="' + this.vnumber +'">';
                  str +=  '<label class="custom-control-label" for="customRadio' + a + '"></label>';
                  str += "</div>";
                  str += '<td style="width:5%;"><img src="/resources/IMG/car/' + this.vmodel + '.png" alt="" style="height:30px;"/></td>';
                  str += "<td>" +  this.vtype + "</td>";
                  str += "<td>" + this.vmodel+ "</td>";
                  str += "<td>" + this.vmaker+ "</td>";
                  
                  
                  if(this.voil=="디젤") {
                  	str += "<td>" + this.voil+ "<br>*${diesel}원/L</td>";
                  } else if(this.voil=="가솔린") {
                  	str += "<td>" + this.voil+ "<br>*${gasoline}원/L</td>";
                  } else if(this.voil=="lpg") {
                  	str += "<td>" + this.voil+ "<br>*${lpg}원/L</td>";
                  } else {
                  	str += "<td>" + this.voil+ "</td>";
                  }
                   
                  str += "<td style='color:" + this.vcolor + ";'><h3>●</h3></td>";  
                  str += "<td>" + addComma(this.vprice)+ "원</td>";
                  str += "</tr>";
                  a = a+1;
               });
               $("#carList").html(str);
              
            },
            error : function(e) {
            }
         });
        $("#reservationBtn").show();
     });
     
     $("#reservationBtn").on("click",function() {
    	 $.ajax ({
    		 type : "POST",
    		 url : "/bookingCheck",
    		 success : function(data) {
    			 if(data != 0) {
    				 alert("고객님은 연체상태이므로 예약이 불가능 합니다!");
    			 } else {
    				 console.log("예약가능");
    				 location.href = "/reservation/reservation/"+$("input:radio[name=customRadio]:checked").val()+"/"+bin+"/"+bout;
    			 }
    		 },
             error : function(e) {
             }
    	 })
     });
   });
</script>
   <style>
      #from div, #to div{
         background-color: white;
      }
      #from .ui-state-default, #to .ui-state-default{
         border: none;
      }
      #from .ui-state-highlight, #to .ui-state-highlight {
         background-color: #33b5e5;
         border: none;
      }
      
      #from span , #from a, #to span, #to a{
         font-family: 고딕;
      }
      .card{
      margin:auto;
      margin-top:30px;
      max-width:100%;
   }
   #from, #to{
		margin-left:50px;
   }
   #fromInput, #toInput, #select1, #select2{
   		width:272px;
   		margin-left:50px;
   		margin-bottom:15px;
   }
   #dateSearchBtn, #reservationBtn{
   	margin-left:50px;
   }
   </style>

<div class="container">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col row">
				<div class="col" style="margin-bottom:15px;"><h4 style="margin-left:30%;">시작일</h4>
				<div id="from" style="margin-bottom:15px;"></div>
				<input type="text" id="fromInput" class="form-control"/>
					<select class="custom-select" id="select1">
	                <option value="" disabled selected>Choose your option</option>
	                <option value="서울">서울</option>
	                <option value="대전">대전</option>
	                <option value="대구">대구</option>
	                <option value="부산">부산</option>
	                <option value="울산">울산</option>
	            	</select>
				</div>
				
				<div class="col"><h4 style="margin-left:30%;">반납일</h4>
				<div id="to" style="margin-bottom:15px;"></div>
				<input type="text" id="toInput" class="form-control"/>
					<select class="custom-select" id="select2">
	                <option value="" disabled selected>Choose your option</option>
	                <option value="서울">서울</option>
	                <option value="대전">대전</option>
	                <option value="대구">대구</option>
	                <option value="부산">부산</option>
	                <option value="울산">울산</option>
	            	</select>
	            	<button type="button" class="btn btn-primary" id="dateSearchBtn">조회하기</button>
				</div>
				
				</div>
					
				<div class="col-12"><span id="carListText"></span>
				
				<table id="carList" class="table table-hover table-responsive-md table-fixed" style="width:100%; background-color: #F6F6F6;">
				
				</table>
				
				<button type="submit" class="btn btn-primary" id="reservationBtn">예약하기</button>
					
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>