<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="modal fade" id="exampleModalPw" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
  
  <!-- 비밀번호 찾기 폼 -->
    <div class="modal-content" id="beforePW">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Find PW</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
       <div class="modal-body">
        <div class="md-form">
	                <input type="email" name="ids" class="form-control" />
	                <label for="Form-email4">Id</label>
	            </div>
	    	<div class="md-form">
		            <input type="text" name="cbirth"  class="form-control" />
			        <label for="Form-pass4">Birth</label>
				</div>
    	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="findpw">FIND</button>
      </div>
    </div>
    
    <!-- 비밀번호 찾은 후 폼 -->
    <div class="modal-content" id="afterPW">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Your PW : <span id="findPWstring"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">BACK</button>
      </div>
    </div>
    
    
  </div>
</div> 

    
    
    


