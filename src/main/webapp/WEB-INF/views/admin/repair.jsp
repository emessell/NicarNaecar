<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/admin/sidebar.jsp"></jsp:include>
<script src="https://d3js.org/d3.v4.min.js"></script>

<style>
   .table-fixed {
    table-layout: fixed;
    overflow: hidden;
   }
   label{
      width:100px;
      text-align:center;
   }
   /* Hide the browser's default radio button */
   .container input[type=radio] {
       position: absolute;
       opacity: 0;
       cursor: pointer;
   }
   .form-check-label{
      height:50px;
   }
   
</style>
<script>

   $(function(){
   // ajax실행시 마다 http header에 csrf토큰 추가-------------------------
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");
      $(document).ajaxSend(function(e, xhr, options) {
         xhr.setRequestHeader(header, token);
      });
   //----------------------------------------------------------------------
      $("#form1").change(function(){
           var seld = $("#form1 option:selected").val();
           console.log(seld);
           
           var str = "";
         $.ajax({
                type : "POST",
                url : "/vnumforbnum", 
                data : {vnumber : seld},
                  dataType : "json", 
                  contentType:"application/x-www-form-urlencoded; charset = utf-8",
               /*  headers:{
                      "Content-Type" : "application/json; charset=utf-8",
                      "x-www-form-urlencoded" : "POST"
                   }, */
                success : function(data) {
                   var a = 0;
                   str+="<option value='0'></option>";
                    $.each(data, function(){
                      console.log(data[a].bnumber);
                    str+="<option value='"+data[a].bnumber+"'>"+data[a].bnumber+"("+data[a].cemail+")"+"</option>";
                    a=a+1;
                   }); 
                   $("#form2").html(str);
                },
                error : function(e){
                   alert("에러발생. 다시 시도해주세요");
                }
                
         })
      });
      
      $('#searchBtn').click(function(){
         var search = $('#search').val();
         self.location = 'repair?reqPage=1'
               +'&search='
               +search;
      });
    $('.uupp').click(function(){
       var rnum = $(this).parent().parent().children().eq(0).text();
       var vnum = $(this).parent().parent().children().eq(1).text();
       var rout = $(this).parent().parent().children().eq(4).text();
       var rmemo = $(this).parent().parent().children().eq(7).text();
       
       var str ="";
       document.vo.rnumber.value = rnum;
       document.vo.rout.value = rout;
       document.vo.rmemo.value = rmemo;
       str +="<a class='btn btn-danger' href='/admin/repairDelete/"+rnum+"'>삭제</a>";
       str +="<button type='submit' class='btn btn-secondary'>수정</button>";
       str +="<a class='btn btn-primary' href='/admin/repairOut/"+vnum+"/"+rnum+"'>수리완료</a>";
       
       $("#modalbtns").html(str);
       
    })
      $("#div0").siblings().hide();
      
      $("#pin").on("click", function() {
         $("#div0, #div2").siblings().show();
         $("#div2").show();
         /* $("#form1").val("고객"); */
      });
      
      $("#pout").on("click", function() {
       $("#div0, #div2").siblings().show();
       $("#div2").hide();
       
       $("#form1").val("");
      });
      
   });
   
   function addComma(num) {
      var regexp = /\B(?=(\d{3})+(?!\d))/g;
       return num.toString().replace(regexp, ',');
   };
</script>

<div class="col-md-10">

<form:form action="/admin/repairIn" method="post">
   <div class="card">
      <div class="card-body row">
      <h2 class="container-fluid">수리내역</h2>
         <div class="container-fluid row">
            <div class="btn-group col" data-toggle="buttons"
            style="margin-top: 10px; margin-left: 20px; margin-bottom:20px;" id="div0">
               <label class="btn btn-primary active form-check-label" id="pin" style="padding-right:0px!important; padding-left:0px!important">
               <input class="form-check-input" type="radio" autocomplete="off"
                  value="고객과실" name="rtarget">고객과실
               </label>
               <label class="btn btn-primary form-check-label" id="pout">
                  <input class="form-check-input" type="radio" autocomplete="off"
                  value="그 외" name="rtarget">그 외
               </label>
         </div>
         <div class="container-fluid row">
         <div class="" id="div1">
         <label for="form1">차량번호</label>
            <select name="vnumber" class="form-control" id="form1" style="border:0px;outline:0px;">
                  <option disabled selected></option>
               <c:forEach items="${carlist }" var="cl">
                  <option value="${cl.vnumber }">${cl.vnumber }</option>               
               </c:forEach>
            </select>
               <!-- <input type="text" id="form1" class="form-control" name="vnumber" value=" "> -->
               
            </div>
               <div class="" id="div2">
               <label for="form2">예약번호</label>
                <select id="form2" class="form-control" name="bnumber" style="border:0px;outline:0px;">
                    <option value="0" selected></option>
               </select>
            </div>
            <div class="" id="div3">
            <label for="form3">수리시작일</label>
               <input type="text" id="form3" class="form-control" name="rin" value="${nowDate }" readonly style="border:0px;outline:0px;">
               
            </div>
            <div class="" id="div4">
            <label for="form4">종료예정일</label>
               <input type="date" id="form4" class="form-control" name="rout" style="border:0px;outline:0px;">
               
            </div>
            </div>
            <div class="container-fluid row">
            <div class="md-form col" id="div5">
               <input type="text" id="form4" class="form-control" name="rshop" value=" ">
               <label for="form4">정비소</label>
            </div>
            <div class="md-form col" id="div5">
               <input type="text" id="form4" class="form-control" name="rprice" value=" ">
               <label for="form4">비용</label>
            </div>
            <div class="md-form col" id="div6">
               <input type="text" id="form4" class="form-control" name="rmemo" value=" ">
               <label for="form4">내용</label>
            </div>
               <!-- <input type="hidden" name="rtarget"> -->
         </div>
            <input type="submit" class="btn btn-primary" style="margin-left:80%; margin-top:10px;" value="입력" />
         </div>
      </div>
   </div>
</form:form>
   <br />
   <div class="card">
    <div class="card-body">
        <!--Table-->
        <table class="table table-hover table-fixed" id="profitTable">
         <h2>목록</h2>
            <!--Table head-->
            <thead>
                <tr>
                    <th><h5>Number</h5></th>
                    <th><h5>차량번호</h5></th>
                    <th><h5>예약번호</h5></th>
                    <th><h5>수리시작일</h5></th>
                    <th><h5>종료예정일</h5></th>
                    <th><h5>정비소</h5></th>
                    <th><h5>수리비</h5></th>
                    <th><h5>수리내용</h5></th>
                    <th><h5>분류</h5></th>
                    <th><h5></h5></th>
                </tr>
            </thead>
            <!--Table head-->

            <!--Table body-->
            <tbody id="repairTableIn">
                <script>
            var str = "";
             <c:forEach items="${list }" var="repair">
                 str += "<tr>";
                 str += "<td>${repair.rnumber}</td>";
                 str += "<td>${repair.vnumber}</td>"; 
                 str += "<td>${repair.bnumber}</td>";
                 str += "<td>${repair.rin}</td>"; 
                 str += "<td>${repair.rout}</td>"; 
                 str += "<td>${repair.rshop}</td>"; 
                 str += "<td>"+addComma('${repair.rprice}')+"</td>"; 
                 str += "<td>${repair.rmemo}</td>"; 
                 str += "<td>${repair.rtarget}</td>"; 
                 str += "<td><button data-toggle='modal'";
                 <c:if test="${repair.rstate eq '수리완료' }">
                 str +=" disabled ";
                 </c:if> 
                 str +="style='margin-left: 0px; margin-right: 0px;' data-target='#updatemodal'  class='btn btn-info px-3 uupp'>";
                 <c:choose>
                <c:when test="${repair.rstate eq '수리완료' }">str+="수리완료";</c:when>
                <c:when test="${repair.rstate eq '수리중' }">str+="수정";</c:when>
             </c:choose>
                 str+="</button></td>"; 
                 
                 str += "</tr>";
              </c:forEach>
              $("#repairTableIn").html(str);
              
         </script>
            </tbody>
            <!--Table body-->

        </table>
        <!--Table-->

    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="updatemodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form:form modelAttribute="repairVO" name="vo" action="/admin/repairUpdate" method="post">
      <div class="modal-body">
      
         <input type="hidden" id="modalrnum" name="rnumber" />
         <label for="modalrout">종료예정일</label>
         <input type="date" id="modalrout" name="rout" class="form-control" />
         <label for="modalrmemo">수리내용</label>
         <input type="text" id="modalrmemo" name="rmemo" class="form-control"/>
      
      </div>
      <div class="modal-footer" id="modalbtns">
      <!-- <a class='btn btn-danger' href='/admin/repairDelete/'>삭제</a>
        <button type="submit" class="btn btn-secondary" data-dismiss="modal">수정</button>
        <a class='btn btn-primary' href='/admin/repairOut/'>수리완료</a> -->
      </div>
      </form:form>
    </div>
  </div>
</div>
<table style=" margin:auto;">
         <tr>
            <td>
               <ul id="pageing"
                  class="pagination pagination-sm justify-content-center">
                  <c:if test="${page.prev }">
                     <li class="page-item"><a class="page-link"
                        href="repair?page.finalEndPage">◀</a></li>
                     <li class="page-item"><a class="page-link"
                        href="repair?${page.getmakeURL(page.startPage-1) }" aria-label="Previous">
                           <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
                     </a></li>
                  </c:if>

                  <c:forEach begin="${page.startPage }" end="${page.endPage }"
                     var="PAGE">
                     <c:if test="${page.recordCriteria.reqPage == PAGE }">
                        <li class="page-item active"><a class="page-link" href="javascript:void(0)">${PAGE }</a></li>
                     </c:if>
                     <c:if test="${page.recordCriteria.reqPage != PAGE }">
                        <li class="page-item"><a class="page-link"
                           href="repair?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
                     </c:if>
                  </c:forEach>

                  <c:if test="${page.next }">
                     <li class="page-item"><a class="page-link"
                        href="repair?${page.getmakeURL(page.endPage+1) }" aria-label="Next">
                           <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
                     </a></li>
                     <li class="page-item"><a class="page-link"
                        href="repair?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
                  </c:if>
               </ul>
            </td>
          </tr>
          <tr>
             <td>
             <input type="text" id="search" value="${param.search }"/><button class="btn btn-primary btn-sm" id="searchBtn">검색</button>
             </td>
          </tr>
      </table> 
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>