<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.sideBarPointer {
    border-left: 2px solid gray;
    padding-top: 0px;
    padding-bottom: 0px;
    margin-top: 6px;
    margin-bottom: 6px;
/*     font-weight : 500; */
}
.col-md-3 ul li a:hover{
	border-left: 1px solid gray;
}
</style>
<script>
$(document).ready(function(){
    var link = document.location.href;
	var arr = link.split('/');
	link = "/"+arr[3]+"/"+arr[4];
	console.log(link);
		$(".col-md-3 ul li a[href = '"+link+"']").addClass('sideBarPointer');
   });
</script>
<div class="container" style="margin-top:3%;">
<div class="row">
<div class="col-md-3">
    <ul class="list-group-flush list-unstyled">
      <li class="nav-item"><a class="nav-link" href="/service/notice">N O T I C E</a></li>
      <li class="nav-item"><a class="nav-link" href="/service/faq">F A Q</a></li>
      <li class="nav-item"><a class="nav-link" href="/service/oneonone">1 : 1</a></li>
    </ul>
</div>


