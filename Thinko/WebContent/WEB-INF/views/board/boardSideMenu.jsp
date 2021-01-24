<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<title>Sidebar 02</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/side-style.css">
<style>
#sidebar {
	float: left;
	margin-left: 0px;
	height: 100%
}

#footer{
  			background-color: #866ec7;
}

.icon{
	color: white;
}
</style>
</head>
<body>

	<nav id="sidebar">
		<div class="custom-menu">
			<button type="button" id="sidebarCollapse" class="btn btn-primary">
				<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
			</button>
		</div>
		<div class="p-4 pt-5">
			<h1>
				<a href="index.html" class="logo">B O A R D</a>
			</h1>
			<ul class="list-unstyled components mb-5">
				<li class="active"><a
					href="${contextPath}/notice/noticeList.do">공지사항</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=20">인기글게시판</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=10">자유게시판</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=30">질문게시판</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=40">스터디게시판</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=60">정보게시판</a></li>
				<li class="active"><a
					href="${contextPath}/board2/list.do?type=50">맛집게시판</a></li>
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