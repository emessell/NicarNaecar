<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	$(function () {
		$(".btn-primary").on("click",function() {
			location.href="/search/dateSearch";
		});
		
		$(".btn-info").on("click",function() {
			location.href="/search/vehicleSearch";
		});
	})
</script>
<div class="view intro-2">
            <div class="full-bg-img">
                <div class="mask rgba-strong flex-center">
                    <div class="container">
                        <div class="white-text text-center wow fadeInUp">
                     <div class="card-deck">
       <button class="btn btn-primary waves-effect" style="font-size:40px;border-radius:10px;">
       <i class="fa fa-calendar" aria-hidden="true" style="font-size:70px;margin-bottom:10px;"></i>
       <br />날짜 검색</button>
      <button class="btn btn-info waves-effect" style="font-size:40px;border-radius:10px;">
         <i class="fa fa-car" aria-hidden="true" style="font-size:70px;margin-bottom:10px;"></i>
         <br />차량 검색</button>
                     </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<!--Main Layout-->
    <main class="text-center my-5">

        <div class="container">
            <div class="row">
                <div class="col-md-12">

                    <p align="justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                </div>
            </div>
        </div>
    
    </main>
    <!--Main Layout-->