<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="exampleModalId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document" >
<!-- 아이디 찾기 폼 -->
    <div class="modal-content" id="beforeID">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Find ID</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="md-form">
	                <form:input path="cname"  class="form-control" />
	                <!-- <input type="text" class="form-control" /> -->
	                <label for="Form-email4">Name</label>
	            </div>
	    <div class="md-form">
		            <form:input path="cphone"    class="form-control" />
		            <!-- <input type="text"    class="form-control" /> -->
			        <label for="Form-pass4">PhoneNumber</label>
				</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="findid">FIND</button>
      </div>
    </div>
    <!-- 아이디 찾은 후 폼 -->
    <div class="modal-content" id="afterID">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your ID</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Your Id : <span id="findIDstring"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">BACK</button>
      </div>
    </div>
    
  </div>
</div>