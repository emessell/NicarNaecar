<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" style="margin-top:3%;height:100%">
<div class="row">
<style>
.sideBarPointer {
    border-left: 2px solid gray;
    padding-top: 0px;
    padding-bottom: 0px;
    margin-top: 6px;
    margin-bottom: 6px;
    font-weight : 500;
}
.col ul li a:hover{
	border-left: 1px solid gray;
}
</style>
<script>
$(document).ready(function(){
    var link = document.location.href;
	var arr = link.split('/');
	link = "/"+arr[3]+"/"+arr[4];
	console.log(link);
	
		$(".col ul li a[href = '"+link+"']").addClass('sideBarPointer');
   });
</script>
<div class="col" style="display:inline; width:24%;">
    <ul class="list-group-flush list-unstyled">
      <li class="nav-item"><a class="nav-link" href="/admin/profitChart">매출 그래프</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/delivery">인도하기</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/return">반납하기</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/profit">수익관리</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/carList">차량관리</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/repair">수리내역</a></li>
      <li class="nav-item"><a class="nav-link" href="/admin/employees">직원관리</a></li>
    </ul>
</div>