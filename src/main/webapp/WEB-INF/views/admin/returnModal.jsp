<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<div class="modal fade" id="exampleModalReturn" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document" >
    <div class="modal-content" id="form">
      <div class="modal-header">
        <h5 class="modal-title">반납하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="md-form">
	                <input type="text" id="vkm" name="vkm" class="form-control" readOnly value=" "/>
	                <label for="Form-text4">주행량(전)</label>
	                <input type="hidden" id="bnumber" name="bnumber" value=""/>
	                <input type="hidden" id="vnumber" name="vnumber"/>
	                <input type="hidden" id="bout" name="bout"/>
	                <input type="hidden" id="bin" name="bin"/>
	                <input type="hidden" id="vprice" name="vprice"/>
	            </div>
	    <div class="md-form">
		            <input type="text" id="bkm" name="bkm" class="form-control" />
			        <label for="Form-text4">주행량(후)</label>
				</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="returnNext">Next</button>
      </div>
    </div>
    
    <div class="modal-content" id="view">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">반납하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<span id="kmPrice"></span>
        	<span id="extendPrice"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="bbtn">BACK</button>
        <button type="button" class="btn btn-primary" id="returnOK">RETURN</button>
      </div>
    </div>
    
    
  </div>
</div>