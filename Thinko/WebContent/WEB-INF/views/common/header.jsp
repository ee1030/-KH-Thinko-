<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%-- 
      프로젝트의 시작주소(context root)를 얻어와 간단하게 사용할 수 있도록
      별도의 변수를 생성
   --%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>프로젝트 띵코헤더</title>

<!-- Main CSS-->
<link href="${contextPath}/resources/css/theme.css" rel="stylesheet"
	media="all">

<!-- Fontfaces CSS-->
<link href="${contextPath}/resources/css/font-face.css" rel="stylesheet"
	media="all">
<link
	href="${contextPath}/resources/vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="${contextPath}/resources/vendor/font-awesome-5/css/fontawesome-all.min.css"
	rel="stylesheet" media="all">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">

<link
	href="${contextPath}/resources/vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">

<!-- Vendor CSS-->
<link
	href="${contextPath}/resources/vendor/animsition/animsition.min.css"
	rel="stylesheet" media="all">
<link
	href="${contextPath}/resources/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet" media="all">
<link href="${contextPath}/resources/vendor/wow/animate.css"
	rel="stylesheet" media="all">
<link
	href="${contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css"
	rel="stylesheet" media="all">
<link href="${contextPath}/resources/vendor/slick/slick.css"
	rel="stylesheet" media="all">
<link href="${contextPath}/resources/vendor/select2/select2.min.css"
	rel="stylesheet" media="all">
<link
	href="${contextPath}/resources/vendor/perfect-scrollbar/perfect-scrollbar.css"
	rel="stylesheet" media="all">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<!-- summer note -->

<script src="${contextPath}/resources/js/summernote/summernote-lite.js"></script>
<script
	src="${contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet"
	href="${contextPath}/resources/css/summernote/summernote-lite.css">

<script src="https://cdn.sobekrepository.org/includes/jquery-rotate/2.2/jquery-rotate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>


<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- noto snas 폰트 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap"
	rel="stylesheet">
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
	color: rgb(37, 37, 37);
}

div {
	/* border: 1px solid black; */
	box-sizing: border-box;
}

.wrapper {
	width: 100%;
	height: 160px;
	margin: auto;
}

.header {
	width: 100%;
	height: 100%;
}

#header-1 {
	position: relative;
	width: 20%;
}

#header-2 {
	position: relative;
	width: 60%;
}

#header-3 {
	width: 20%;
	position: relative;
}

#mainLogo {
	width: 100%;
	height: auto;
	margin: auto;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}

#search-form {
	width: 50%;
	height: 30%;
	position: absolute;
	margin: auto;
	bottom: 25%;
	left: 0;
	right: 0;
}

#search-area{
	text-align: center;
	width: 100%;
	height: 100%;
	border: 3px solid #814798;
	border-radius:30px;
	background-color: white;
}

/* #search-input-area {
	width : 60%;
	heigth : 100%;
	box-sizing: border-box;
}

#search-btn-area{
	float :right;
	width : 30%;
	heigth : 100%;
	box-sizing: border-box;
} */

#search-input {
	/* border:none; */
	width: 85%;
	height: 100%;
	box-sizing: border-box;
	background-color: transparent;
}

#search-btn {
	font-size: 1.5em;
	box-sizing: border-box;
	padding: 0;
}

#search-btn:hover{
	cursor:pointer;
}

#hidden-btn{
	border:none;
	background-color: transparent;
}
.charcoal{
	color: #353535
}
.purple {
	color: #814798;
	margin-left: 20px;
}

.header-float {
	height: 100%;
	float: left;
}

#login {
	width: 80%;
	height: 80%;
	position: absolute;
	margin: auto;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
}

#login i {
	text-align: center;
	font-size: 1.5em;
}

.nav-link abbr{
	font-size: 1.2em;
}

#login ul {
	list-style-type: none;
}

#login li {
	text-align: center;
	float :left;
}

#login a{
	color:black;
}
#h-logout, #h-myPage, #h-login, #admin-setting{
	width:40px;
	margin : 32px;
}

#h-logout, #h-myPage, #h-login, #admin-setting:hover{
	cursor:pointer;
}

</style>
</head>

<body>

	<c:set var="contextPath" scope="application"
		value="${pageContext.servletContext.contextPath }" />

	<c:if test="${!empty sessionScope.swalTitle }">
		<script>
			swal({
				title : "${swalTitle}",
				text : "${swalText}",
				icon : "${swalIcon}",
				button : "확인",
			});
		</script>

		<%-- 2) 한번 출력한 메세지를 Session에서 삭제  --%>
		<c:remove var="swalTitle" />
		<c:remove var="swalText" />
		<c:remove var="swalIcon" />

	</c:if>




	<div class="wrapper">
		<div class="header">
			<div id="header-1" class="header-float">
				<a class="navbar-brand" href="${contextPath}"> <img id="mainLogo" src="${contextPath}/resources/images/mainLogo_trans.png">
				</a>
			</div>

			<div id="header-2" class="header-float">
				<form id="search-form" action="${contextPath}/search.do" method="get">
					<div id="search-area">
							<input type="text" id="search-input" name="sv" placeholder=" 우리 모두가 소통하는 이 곳">
							<button type="submit" id="hidden-btn">
							<i class="fas fa-search fa-2x mx-1 purple" id="search-btn"></i>
							</button>
					</div>
				</form>
			</div>

			<div id="header-3" class="header-float">
				<div id="login">
					<ul>

						<c:choose>
							<%-- 회원으로 로그인 되었을때  --%>
							<c:when test="${sessionScope.loginMember.memberGrade == 'G'}">
								<li><i id="h-myPage"class="fas fa-user-cog fa-1x mx-1 purple"></i></li>
								<li><i id="h-logout" class="fas fa-door-open fa-1x mx-1 charcoal"></i></li>
							</c:when>

							<%-- 관리자로 로그인 되었을때  --%>
							<c:when test="${sessionScope.loginMember.memberGrade == 'A'}">
								<li><i id="admin-setting" class="fas fa-cogs fa-1x mx-1 charcoal"></i></li>
								<li><i id="h-logout" class="fas fa-door-open fa-1x mx-1 purple"></i></li>
							</c:when>

							<%-- 로그인 되어있지 않을때 --%>
							<c:otherwise>
								<li><a class="nav-link"
									href="${contextPath}/member2/memberLoginForm.do">
										<abbr title="로그인">
											<i id="h-login" class="fas fa-sign-in-alt fa-1x mx-1 purple"></i>
										</abbr>
									</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>

			</div>

		</div>
	</div>

<script>
	// 인풋버튼 포커스, 블러시 플레이스 홀더 속성 변경
	$("#search-input").on("focus", function() {
		$(this).removeAttr("placeholder");
	}).on("blur", function() {
		$(this).attr("placeholder", "우리 모두가 소통하는 이곳");
	});
	
	// 로그아웃 버튼 클릭시 컨펌창 활성
	$("#h-logout").on("click", function(){
		
		if(confirm("로그아웃 하시겠습니까?")){
			location.href = "${contextPath}/member2/logout.do";
		}
	});
	
	// 아이콘 클릭시 myPage.do 로 이동
	$("#h-myPage").on("click", function(){
		location.href = "${contextPath}/member/myPage.do";
	});
	
	// 관리자 톱니바퀴 아이콘 클릭시 관리자 페이지로 이동
	$("#admin-setting").on("click", function(){
		location.href = "${contextPath}/admin/adminPage.do";
	});
	
</script>

</body>
</html>