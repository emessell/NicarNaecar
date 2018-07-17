<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="/WEB-INF/views/header.jsp" />
<jsp:include page="/WEB-INF/views/nav.jsp" />
<jsp:include page="/WEB-INF/views/admin/sidebar.jsp" />

<title>직원관리</title>
<style>
 .hide {
	display: none;
}
.showT {
	display: table-row;
	background-color: #ececec;
}
.errmsg{color:red;}
.passErr{color:red;}
</style>
<script>
function changeS(){
	   var selparent = document.getElementById("oselect");
	   var selnam = document.getElementById("searchname").value;
	    var selval = selparent.options[selparent.selectedIndex].value;
	   console.log(selval, selnam);
	   self.location = 'employees?reqPage=1'
	      +'&oselect='
	      +selval
	      +'&ename='
	      +selnam
	      ;
	      
}
	$(function(){
	   $('#searchBtn').click(function(){
	      var selparent = document.getElementById("oselect");
	      var selnam = document.getElementById("searchname").value;
	       var selval = selparent.options[selparent.selectedIndex].value;
	       console.log(selval+":::");
	       self.location = 'employees?reqPage=1'
	          +'&oselect='
	         +selval
	         +'&ename='
	         +selnam
	         ; 
	   });
	});
	
	
$(function(){
	var idck = 0; 

	$(".Iresetbtn").click(function(){ 
      $("#insertemp").each(function() {
    	  	this.reset();
    	});
   });
	
   $("input[name=confirm]").on("keyup",function(){
       if($("#epassword").val() != $("input[name=confirm]").val()){
          $(".passErr").text('비밀번호가 틀립니다.');
         $(this).focus();
      }else{
         $(".passErr").text('');
      }
   });
   
   $("#idCheck").on("click",function(e){
	  var reqId = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/
 	  var eemail = $("#eemail").val();
 	  if(eemail == "") {
 		  alert("사용하실 이메일을 입력해주세요!");
 		  return false;
 	  }
 	  /* 아이디의 첫번째자리를 제외한곳에 특수문자 ㄴㄴ;  좀 이상한듯..걍넘어가자..*/
 	  if(reqId.test(eemail)) {
 		  console.log(eemail);
 		  alert("아이디에 사용할 수 없는 문자가 들어가있습니다!");
 		  idck = 0; 
		   	  return false;
 	  }
 	  $.ajax({
 	  	type : 'POST',
 	  	data : {
 	  		"eemail" : eemail
 	  	},
 	  	url : "/emplEmailCheck",
 	  	dataType : "json",
 	  	contentType : "application/json; charset=UTF-8",
 	  	success : function(data) {
 	  		console.log(data)
 	  		if(data == 0) {
 	  			idck = 1; 
 	  			alert("사용가능한 아이디 입니다.");
 	  			$("#idUncheck").val(eemail);
 	  		} else if(data >= 1){
 	  			alert("이미 사용중인 아이디 입니다.");
 	  		}
 	  	},
 	  	error : function(error) {
 	  		console.log(error);
 	  	}
 	  });
   });
   
   $("#EmplJoin").click(function(e) {
	   if(idck == 0) {
		   alert("아이디 중복 검사를 해주세요!");
		   return false;
	   } else if($("#idUncheck").val() != $("#eemail").val()) {
		   idck = 0;
		   alert("아이디 중복 검사를 다시 해주세요!");
		   return false;
	   } else if($("#ename").val() == "" || $("#ephone").val() == "" || $("#eaddress").val() == "" || $("#ebirth").val() == "" || $("#epassword").val() == "") {
		   alert("빈칸을 모두 입력해주세요!");
		   return false;
	   } else if($("#epassword").val() != $("input[name=confirm]").val()){ 
		   alert("비밀번호를 확인하세요!");
		   $("#epassword").focus();
		   return false;
	   } else if($("#ename").val().lenght > 20 || $("#ename").val().length < 4) {
		   alert("이름은 4-20자로 입력하여야 합니다!");
		   $("#ename").focus();
		   return false;
	   } else if($("#ephone").val().length <= 10 || $("#ephone").val().length > 11) {
		   alert("전화번호는 10 또는 11자리의 숫자로 입력햐여야 합니다!");
		   $("#ephone").focus();
		   return false;
	   } else if($("#ebirth").val().length != 8) {
		   alert("생년월일은 '19990101' 형식으로 입력해주세요!");
		   $("#ebirth").focus();
		   return false;
	   } else if($("#epassword").val().length > 20) {
		   alert("비밀번호는 20자리 이내로 입력하셔야 합니다!");
		   $("#epassword").focus();
		   return false;
	   }
	   else {
		   console.log($("#eemail").val())
		   console.log($("#epassword").val())
		   /* $("#eemail").val(($("#eeemail").val()+"@ncnc.com")); */
	         e.preventDefault();
	         $("#insertemp").submit();
	   }
   })

   
});
   $(function() {
      var article = (".recruit .showT");
      $(".recruit .title a").click(function() {
         var myArticle = $(this).parents().next("tr");
         if ($(myArticle).hasClass('hide')) {
            $(article).removeClass('showT').addClass('hide');
            $(myArticle).removeClass('hide').addClass('showT');
         } else {
            $(myArticle).addClass('hide').removeClass('showT');
         }
      });
   });

   function upupdate(i) {
	   if(document.getElementsByName('phone')[i].value == "" || document.getElementsByName('name')[i].value == "") {
		   alert("빈칸을 전부 채워주세요!");
		   return false;
	   } else if(document.getElementsByName('phone')[i].value <= 11) {
		   alert("전화번호는 10 또는 11자리의 숫자로 입력햐여야 합니다!");
		   document.getElementsByName('phone')[i].focus();
		   return false;
	   } else if(document.getElementsByName('name')[i].value > 20 || document.getElementsByName('name')[i].value < 4) {
		   alert("이름은 4-20자 이하로 입력하여야 합니다!");
		   document.getElementsByName('name')[i].focus();
		   return false;
	   }
	   else {
	      console.log(i);
	      document.vo.eoffice.value = document.getElementsByName('office')[i].value;
	      document.vo.ename.value = document.getElementsByName('name')[i].value;
	      document.vo.eposition.value = document.getElementsByName('position')[i].value;
	      document.vo.estate.value = document.getElementsByName('state')[i].value;
	      document.vo.ephone.value = document.getElementsByName('phone')[i].value;
	      document.vo.eemail.value = document.getElementsByName('email')[i].value;
	      document.vo.submit();
	   }
   }
</script>
<div>
   <h2>직원 정보</h2>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addemp" style="float:right;padding-left: 20px;
    padding-right: 20px;">직원추가</button>
   <form:form modelAttribute="employeeVO" action="/admin/update"
      method="post" name="vo">
      <input type="hidden" name="eoffice" />
      <input type="hidden" name="ename" />
      <input type="hidden" name="eposition" />
      <input type="hidden" name="estate" />
      <input type="hidden" name="eemail" />
      <input type="hidden" name="ephone" />
   </form:form>
   <table
      class="table table-hover table-responsive-md table-fixed recruit"
      style="text-align: center; width: 950px;">
      <thead>
         <tr>
            <td>
               <select id="oselect" name="oselect" onchange="changeS()" style="border:0px;outline:0px;">
                  <option value="">영업소</option>
                  <option value="울산" <c:if test="${param.oselect eq '울산' }">selected</c:if>>울산</option>
                  <option value="서울" <c:if test="${param.oselect eq '서울' }">selected</c:if>>서울</option>
                  <option value="대구" <c:if test="${param.oselect eq '대구' }">selected</c:if>>대구</option>
                  <option value="부산" <c:if test="${param.oselect eq '부산' }">selected</c:if>>부산</option>
                  <option value="대전" <c:if test="${param.oselect eq '대전' }">selected</c:if>>대전</option>
               </select>
            </td>
				<td>이름</td>
				<td>직급</td>
				<td>재직상태</td>
				<td>EMAIL</td>
				<td>전화번호</td>
				<td>입사일</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="emp" varStatus="status">
				<tr class="title" style="width: 100%;">
					<td style="width: 8%;">${emp.eoffice }</td>
					<td style="width: 10%;">${emp.ename }</td>
					<td style="width: 8%;">${emp.eposition }</td>
					<td style="width: 8%;">${emp.estate }</td>
					<td style="width: 10%;">${emp.eemail }</td>
					<td style="width: 7%;">${emp.ephone }</td>
					<td style="width: 10%;">${emp.edate }</td>
					<td style="width: 7%;"><a class="light-blue-text" href="#">수정</a></td>
				</tr>
				<tr class="hide" style="width: 100%;">
					<td><select class="form-control form-control-sm" name="office">
							<option value="울산"
								<c:if test="${emp.eoffice eq '울산' }">selected</c:if>>울산</option>
							<option value="서울"
								<c:if test="${emp.eoffice eq '서울' }">selected</c:if>>서울</option>
							<option value="대구"
								<c:if test="${emp.eoffice eq '대구' }">selected</c:if>>대구</option>
							<option value="부산"
								<c:if test="${emp.eoffice eq '부산' }">selected</c:if>>부산</option>
							<option value="대전"
								<c:if test="${emp.eoffice eq '대전' }">selected</c:if>>대전</option>
					</select></td>
					<td><input type="text" class="form-control form-control-sm"
						name="name" value="${emp.ename }" /></td>
					<td><select class="form-control form-control-sm"
						name="position">
							<option value="소장"
								<c:if test="${emp.eposition eq '소장' }">selected</c:if>>소장</option>
							<option value="대리"
								<c:if test="${emp.eposition eq '대리' }">selected</c:if>>대리</option>
							<option value="사원"
								<c:if test="${emp.eposition eq '사원' }">selected</c:if>>사원</option>
					</select></td>
					<td><select class="form-control form-control-sm" name="state">
							<option value="재직"
								<c:if test="${emp.estate eq '재직' }">selected</c:if>>재직</option>
							<option value="퇴직"
								<c:if test="${emp.estate eq '퇴직' }">selected</c:if>>퇴직</option>
							<option value="휴직"
								<c:if test="${emp.estate eq '휴직' }">selected</c:if>>휴직</option>
					</select></td>

					<td><input type="text" class="form-control form-control-sm"
						name="email" value="${emp.eemail }" readOnly /></td>
					<td><input type="text" class="form-control form-control-sm"
						name="phone" value="${emp.ephone }" /></td>
					<td>${emp.edate }</td>
					<td style="width: 7%;"><a href="#" class="indigo-text"
						onclick="upupdate('${status.index}');">저장</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<table style="margin: auto;">
		<tr>
			<td>
				<ul id="pageing"
					class="pagination pagination-sm justify-content-center">
					<c:if test="${page.prev }">
						<li class="page-item"><a class="page-link"
							href="employees?page.finalEndPage">◀</a></li>
						<li class="page-item"><a class="page-link"
							href="employees?${page.getmakeURL(page.startPage-1) }"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								<span class="sr-only">Previous</span>
						</a></li>
					</c:if>
					<c:forEach begin="${page.startPage }" end="${page.endPage }"
						var="PAGE">
						<c:if test="${page.recordCriteria.reqPage == PAGE }">
							<li class="page-item active"><a class="page-link"
								href="javascript:void(0)">${PAGE }</a></li>
						</c:if>
						<c:if test="${page.recordCriteria.reqPage != PAGE }">
							<li class="page-item"><a class="page-link"
								href="employees?${page.getmakeURL(PAGE) }">${PAGE }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${page.next }">
						<li class="page-item"><a class="page-link"
							href="employees?${page.getmakeURL(page.endPage+1) }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
								class="sr-only">Next</span>
						</a></li>
						<li class="page-item"><a class="page-link"
							href="employees?${page.getmakeURL(page.finalEndPage) }">▶</a></li>
					</c:if>
				</ul>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="searchname" value="${param.ename }"/><button class="btn btn-primary btn-sm" id="searchBtn">Search</button>
			</td>
		</tr>
	</table>
	
</div>
<div>
</div>
<!--  -->
<!--  -->

<div class="modal fade" id="addemp" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">직원 추가</h5>
			</div>
			<form:form action="/admin/insert" method="post"	modelAttribute="employeeVO" id="insertemp" name="insertemp">
				<div class="modal-body">
					<div class="card">
						<div class="card-body mx-4 mt-4">
							<div class="md-form">
								<input type="text" id="eemail" name="eemail" class="form-control" style="width: 40%; display: inline;" />
								@ncnc.com
								<label for="Form-email4">Your email</label>
								<button type="button" id="idCheck" class="btn btn-primary btn-sm">E-mail Check</button>
								<input type="hidden" id="idUncheck"/> 
							</div>
							<div class="md-form pb-3">
								<input type="password" id="epassword" name="epassword" class="form-control" />
								<label for="Form-pass4">Your password</label>
								<form:errors path="epassword" cssClass="errmsg" />
							</div>
							<div class="md-form">
								<input type="password" name="confirm" class="form-control" /> <label
									for="Form-email4">Check your password</label>
								<i class="passErr"></i>
							</div>
							<div class="md-form">
								<input type="text" id="ename" name="ename" class="form-control" />
								<label for="Form-email4">Your Name</label>
								<form:errors path="ename" cssClass="errmsg" />
							</div>
							<div class="md-form">
								<input type="text" id="ebirth" name="ebirth" class="form-control" /> <label
									for="Form-email4">Your birthday</label>
									<form:errors path="ebirth" cssClass="errmsg" />
							</div>
							<div class="md-form">
								<input type="text" id="ephone" name="ephone" class="form-control" /> <label
									for="Form-email4">Your Phone Number</label>
									<form:errors path="ephone" cssClass="errmsg" />
							</div>
							<div class="md-form">
								<input type="text" id="eaddress" name="eaddress" class="form-control" /> <label
									for="Form-email4">Your Address</label>
							</div>
							<div class="md-form">
								<select class="form-control form-control-sm" name="eoffice">
									<option value="울산"
										<c:if test="${emp.eoffice eq '울산' }">selected</c:if>>울산</option>
									<option value="서울"
										<c:if test="${emp.eoffice eq '서울' }">selected</c:if>>서울</option>
									<option value="대구"
										<c:if test="${emp.eoffice eq '대구' }">selected</c:if>>대구</option>
									<option value="부산"
										<c:if test="${emp.eoffice eq '부산' }">selected</c:if>>부산</option>
									<option value="대전"
										<c:if test="${emp.eoffice eq '대전' }">selected</c:if>>대전</option>
								</select>
							</div>
							<div class="md-form">
								<select class="form-control form-control-sm" name="eposition">
									<option value="소장"
										<c:if test="${emp.eposition eq '소장' }">selected</c:if>>소장</option>
									<option value="대리"
										<c:if test="${emp.eposition eq '대리' }">selected</c:if>>대리</option>
									<option value="사원"
										<c:if test="${emp.eposition eq '사원' }">selected</c:if>>사원</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary Iresetbtn"
						data-dismiss="modal">취소</button>
					<button type="button" id="EmplJoin" class="btn btn-primary">추가</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>