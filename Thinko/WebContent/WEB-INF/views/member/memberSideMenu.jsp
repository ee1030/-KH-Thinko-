<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
  	<title>Sidebar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/side-style.css">
  	<style>
  		#footer{
  			background-color: #866ec7;
  		}
  		.icon{
  			color: white;
  		}
  		
  		.icon-pink{
  			color: rgb(247,131,172);
  		}
  		
  	</style>
  </head>
  <body>
		
			<nav id="sidebar">
				<div class="custom-menu">
					<button type="button" id="sidebarCollapse" class="btn btn-primary">
	          <i class="fa fa-bars"></i>
	          <span class="sr-only">Toggle Menu</span>
	        </button>
        </div>
				<div class="p-4 pt-5">
		  		<h1><a href="index.html" class="logo">MY PAGE</a></h1>
	        <ul class="list-unstyled components mb-5">
	          <li class="active">
	            <a href="myPage.do">회원정보</a>
	          </li>
	          <li>
              <a href="changePwdForm.do">비밀번호 변경</a>
	          </li>
	          <li>
              <a href="myList.do">내 게시물</a>
	          </li>
	          <li>
              <a href="myFavorite.do">좋아요&nbsp;<i class="icon far fa-heart"></i></a>
	          </li>
	          <li>
              <a href="secessionForm.do">회원탈퇴</a>
	          </li>
						<br><br>
	        </ul>

	        <div class="mb-5">
						<h3 class="h6"><i class="icon fas fa-level-up-alt"></i>&nbsp;&nbsp;Coding, Thinko</h3>

					</div>

	        <div class="footer">
	        	<p id="footer"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib.com</a>
						  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
	        </div>

	      </div>
    	</nav>



    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/popper.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
  </body>
</html>