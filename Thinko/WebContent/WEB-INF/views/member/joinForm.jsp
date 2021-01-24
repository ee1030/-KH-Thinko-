<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/member.css">
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<!-- noto snas 폰트 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap"
	rel="stylesheet">

<!-- Fontfaces CSS-->
<link href="${contextPath}/resources/css/font-face.css" rel="stylesheet"
	media="all">
<link
	href="${contextPath}/resources/vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="${contextPath}/resources/vendor/font-awesome-5/css/fontawesome-all.min.css"
	rel="stylesheet" media="all">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">

<link
	href="${contextPath}/resources/vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">

<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
	color: rgb(37, 37, 37);
}
</style>
</head>
<body>
	<c:set var="contextPath" scope="application"
		value="${pageContext.servletContext.contextPath}"></c:set>

	<form method="POST" action="${contextPath}/member/join.do"
		onsubmit="return validate()">
		<div id="join-wrapper"
			class="bg-white rounded shadow-sm container p-3">
			<a href="/Thinko"><img id="join-logo" src="${contextPath}/resources/images/join-logo.png"></a> 
			<br>
			<table id="sign-up-table">
				<tr>
					<td><span>아이디 *</span></td>
					<td><div class="reg-result" id="idDupResult"></div></td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
						<div class="inputDiv">
							<input id="id" name="id" class="inputBox" type="text"
								placeholder="&nbsp;&nbsp;&nbsp; 영문, 숫자 6~10자" autocomplete="off"
								maxlength="10">
							<div id="id-check-circle" class="check-circle"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><span>비밀번호 *</span></td>
					<td><div class="reg-result" id="pwdResult"></div></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="inputDiv">
							<input id="pwd1" name="pwd" class="inputBox" type="password"
								placeholder="&nbsp;&nbsp;&nbsp; 영문, 숫자 8~12자" autocomplete="off"
								maxlength="12">
							<div id="pwd1-check-circle" class="check-circle"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
						<div class="inputDiv">
							<input id="pwd2" class="inputBox" type="password"
								placeholder="&nbsp;&nbsp;&nbsp; 비밀번호 재확인" autocomplete="off"
								maxlength="12">
							<div id="pwd2-check-circle" class="check-circle"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><span>이름 *</span></td>
					<td><div class="reg-result" id="nameResult"></div></td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
						<div class="inputDiv">
							<input id="name" name="name" class="inputBox" type="text"
								autocomplete="off">
							<div id="name-check-circle" class="check-circle"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><span>핸드폰번호 *</span></td>
					<td><div class="reg-result" id="phoneResult"></div></td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
						<div class="inputDiv">
							<input id="phone" name="phone" class="inputBox" type="text"
								autocomplete="off" maxlength="13"
								placeholder='&nbsp;&nbsp;&nbsp;" - " 포함'>
							<div id="phone-check-circle" class="check-circle"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td><span>이메일 *</span></td>
					<td><div class="reg-result"></div></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="emailDiv">
							<input id="email" name="email" class="inputBox" type="email"
								autocomplete="off">
						</div>
						<button type="button" id="emailCheck" class="button2"
							onclick="emailSend()">인증번호 발송</button>
					</td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
						<div class="emailDiv">
							<input id="certificationNumber" class="inputBox" type="text"
								placeholder="인증번호를 입력해주세요." autocomplete="off">
						</div>
						<button type="button" id="certificationBtn" class="button2"
							onclick="emailCertification()">인증번호 확인</button>
					</td>
				</tr>
				<tr>
					<td colspan="2"><span>관심분야</span></td>
				</tr>
				<tr>
					<td class="input-td" colspan="2">
					 	<label for="frontEnd">프론트엔드</label>
						<input type="checkbox" name="memberInterest" id="frontEnd"value="프론트엔드"> &nbsp;&nbsp;&nbsp;&nbsp; 
						<label for="backEnd">백엔드</label> 
						<input type="checkbox" name="memberInterest" id="backEnd" value="백엔드">	&nbsp;&nbsp;&nbsp;&nbsp;
						<label for="security">정보보안</label> 
						<input type="checkbox" name="memberInterest" id="security" value="정보보안"> &nbsp;&nbsp;&nbsp;&nbsp; 
						<label for="bigdata">빅데이터</label> 
						<input type="checkbox" name="memberInterest" id="bigdata" value="빅데이터"></td>
				</tr>
				<tr>
					<td colspan="2"><span>가입유형</span></td>
				</tr>
				<tr>
					<td class="input-td" colspan="2"><label for="trainee">훈련생</label>
						<input type="radio" name="type" id="trainee" value="T" checked>
						&nbsp;&nbsp;&nbsp; <label for="graduate">수료생</label> <input
						type="radio" name="type" id="graduate" value="G"></td>
				</tr>
				<tr></tr>
				<tr>
					<td class="input-td" colspan="2">
						<button type="button" id="join-cancle-button" class="button1"
							onclick="location.href='${contextPath}/member2/memberLoginForm.do';">취소</button>
						&nbsp;&nbsp;&nbsp;
						<button type="submit" id="join-button" class="button1">가입</button>
					</td>
				</tr>
				<tr></tr>
				<tr></tr>
			</table>
		</div>
	</form>

	<script src="${contextPath}/resources/js/member.js"></script>

	<script>
		var number = 0;

		function emailSend() {
			var clientEmail = $("#email").val();
			var emailYN = isEmail(clientEmail);
			valObj.checkEmail = false;
			sessionStorage.removeItem("number");

			if (emailYN == true) {
				$.ajax({
					type : "POST",
					url : "${contextPath}/member/email.do",
					data : {
						userEmail : clientEmail
					},
					success : function(number) {
						sessionStorage.setItem("number", number);
						swal({
							"icon" : "success",
							"title" : "인증번호가 발송되었습니다."
						});
					},
					error : function(e) {
						console.log("통신오류");
					}
				});
			} else {
				alert("올바른 이메일을 입력해주세요");
			}
		}

		function emailCertification() {
			var number = sessionStorage.getItem("number");
			var inputNumber = $("#certificationNumber").val();

			if (number == inputNumber) {
				valObj.checkEmail = true;
				swal({
					"icon" : "success",
					"title" : "인증되었습니다."
				});
				sessionStorage.removeItem("number");
			} else {
				valObj.checkEmail = false;
				swal({
					"icon" : "error",
					"title" : "인증번호가 올바르지 않습니다."
				});
			}
			console.log(valObj.checkEmail);
		}

		function isEmail(asValue) {
			var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
			return regExp.test(asValue);
		}

		$("#certificationNumber").on("keyup", function() {

			if ($("#email").val().length == 0) {
				$("#certificationNumber").val("");
				swal("이메일을 먼저 입력해주세요.");
				$("#email").focus();
			}

		});
	</script>


</body>
</html>