<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/member.css">
</head>

<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<div class="row my-5">
			<jsp:include page="memberSideMenu.jsp"></jsp:include>

			<div id="container" class="col-sm-8">
				<h5><i class="fas fa-key"></i>&nbsp;&nbsp;P A S S W O R D</h5>
				<hr>
				<br>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updatePwd.do"
						onsubmit="return pwdValidate();" class="form-horizontal"
						role="form">

						<div class="row form-row">
							<div class="infoList col-md-3">
								<label for="memberPwd">비밀번호</label>
							</div>
							<div class="inputDiv col-md-7">
								<input type="password" class="inputBox" id="pwd1" name="newPwd"
									value="" autocomplete="off" placeholder="새 비밀번호" maxlength="12">
								<div id="pwd1-check-circle" class="check-circle"></div>
							</div>
						</div>


						<div class="row mb-5 form-row">
							<div class="infoList col-md-3"></div>
							<div class="inputDiv col-md-7">
								<input type="password" class="inputBox" id="pwd2" value=""
									autocomplete="off" placeholder="새 비밀번호 확인" maxlength="12">
								<div id="pwd2-check-circle" class="check-circle"></div>
							</div>
						</div>
						<br>
						<br>


						<div class="row mb-5 form-row">
							<div class="infoList col-md-3">
								<label for="memberPwd"> 현재 비밀번호</label>
							</div>
							<div class="inputDiv col-md-7">
								<input type="password" class="inputBox" id="currPwd"
									name="currentPwd" value="" autocomplete="off"
									placeholder="현재 비밀번호를 입력해주세요." maxlength="12">
							</div>
						</div>

						<div id="changePwd-caution" class="row mb-5 form-row">
							<p>
								※ 비밀번호 변경 시 유의사항<br>
								<br> 개인정보 보호를 위해 안전한 비밀번호로 변경하여 주시기 바랍니다.<br> 띵코
								이용약관에서는 타인에게 계정 판매, 양도 및 대여 등을 금지하고 있습니다.<br> 비밀번호 변경 시 모든
								디바이스(앱, 브라우저 등)에서 즉시 로그아웃 처리됩니다.
							</p>
						</div>


						<button id="btn"
							class="btn btn btn-outline-light btn-lg btn-block" type="submit">비밀번호
							변경</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script src="${contextPath}/resources/js/member.js"></script>

</body>
</html>
