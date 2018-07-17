<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/admin/sidebar.jsp"></jsp:include>
<div class="col-md-10">
<!-- 	<blockquote class="blockquote text-left">
		<p class="mb-0">ADMIN 페이지에서는 ADMIN 권한에 관한 설명을 볼 수 있습니다.</p>
		<p class="mb-0">DELIVERY 페이지에서는 차량 인도 관리를 할 수 있습니다.</p>
		<p class="mb-0">RETURN 페이지에서는 차량 반납 관리를 할 수 있습니다.</p>
		<p class="mb-0">PROFIT 페이지에서는 수익 관리를 할 수 있습니다.</p>
	</blockquote> -->
	<h2>최근 4개월 매출/지출 통계</h2>
	<div id="chart_div" style="width: 900px; height: 500px;"></div>
</div>


    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
     	  var data =
         google.visualization.arrayToDataTable([
         ['Month', '예약', '주행비용', '연체비용', '지출','평균'],
         <c:forEach items="${list }" var="list">
 		  ['${list.APDATE }',${list.APAMOUNT },${list.BPAMOUNT }, ${list.CPAMOUNT },${list.DPAMOUNT },(${list.APAMOUNT } + ${list.BPAMOUNT } + ${list.CPAMOUNT } + ${list.DPAMOUNT })/ 4],   
 		</c:forEach>
       ]);  

    var options = {
      title : '',
      vAxis: {title: '금액'},
      hAxis: {title: 'Month'},
      seriesType: 'bars',
      series: {4: {type: 'line'}}
    };

    var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }
    </script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>