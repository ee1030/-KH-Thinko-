<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/member.css">
</head>
<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="container">

		<c:if test="${!empty sessionScope.swalTitle }">
			<script>
				swal({
					icon : "${swalIcon}",
					title : "${swalTitle}",
				});
			</script>

			<c:remove var="swalIcon" />
			<c:remove var="swalTitle" />
		</c:if>

		<div class="row my-5">
			<jsp:include page="memberSideMenu.jsp"></jsp:include>

			<div id="container" class="col-sm-8">
				<h5><i class="far fa-address-card"></i>&nbsp;&nbsp;M Y &nbsp; P A G E</h5>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updateMember.do"
						onsubmit="return memberUpdateValidate();" class="form-horizontal"
						role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h3 id="id">${loginMember.memberId}</h3>
							</div>
						</div>

						<!-- 등급 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<c:if test="${loginMember.membershipType == 'T'}">
									<c:set var="type" value="KH 훈련생" />
								</c:if>
								<c:if test="${loginMember.membershipType == 'G'}">
									<c:set var="type" value="KH 졸업생" />
								</c:if>
								<h6 id="grade">${loginMember.memberNm}&nbsp;|&nbsp;${type}</h6>
							</div>
						</div>
						<hr class="mb-4">
						<br>

						<!-- 전화번호 -->
						<div class="row mb-3 form-row">
							<div class="infoList col-md-3">
								<label for="phone">전화번호</label>
							</div>
							<div class="inputDiv col-md-7">
								<input type="text" class="inputBox" id="phone" name="phone"
									value="${loginMember.memberPhone}" autocomplete="off"
									maxlength="13" placeholder='" - " 포함'>
								<div id="phone-check-circle" class="check-circle"></div>
							</div>
						</div>
						<br>


						<!-- 이메일 -->
						<div class="row mb-3 form-row">
							<div class="infoList col-md-3">
								<label for="memberEmail">이메일</label>
							</div>
							<div class="inputDiv col-md-7">
								<input type="email" class="inputBox" id="email" name="email"
									value="${loginMember.memberEmail}" autocomplete="off">
								<div id="email-check-circle" class="check-circle"></div>
							</div>
						</div>
						<br>


						<!-- 관심분야 -->
						<div class="row">
							<div class="col-md-3">
								<label>관심 분야</label>
							</div>

							<div class="col-md-9 custom-control custom-checkbox">

								<div class="form-check form-check-inline">
									<input type="checkbox"
										class="form-check-input custom-control-input"
										name="memberInterest" id="frontend" value="프론트엔드"> <label
										class="form-check-label custom-control-label " for="frontend">프론트엔드</label>
								</div>

								<div class="form-check form-check-inline">
									<input type="checkbox"
										class="form-check-input custom-control-input"
										name="memberInterest" id="backend" value="백엔드"> <label
										class="form-check-label custom-control-label" for="backend">백엔드</label>
								</div>

								<div class="form-check form-check-inline">
									<input type="checkbox"
										class="form-check-input custom-control-input"
										name="memberInterest" id="security" value="정보보안"> <label
										class="form-check-label custom-control-label" for="security">정보보안</label>
								</div>

								<div class="form-check form-check-inline">
									<input type="checkbox"
										class="form-check-input custom-control-input"
										name="memberInterest" id="bigData" value="빅데이터"> <label
										class="form-check-label custom-control-label" for="bigData">빅데이터</label>
								</div>


							</div>
						</div>

						<br>
						<br>
						<!-- 비밀번호 인증 -->
						<div class="row mb-5 form-row">
							<div class="infoList col-md-3">
								<label for="memberPwd">비밀번호 인증</label>
							</div>
							<div class="inputDiv col-md-7">
								<input type="password" class="inputBox" id="currentPwd"
									name="currentPwd" placeholder="회원 비밀번호를 입력해주세요."
									autocomplete="off" maxlength="12">
							</div>
						</div>
						<div id="myPage-caution" class="row mb-5 form-row">
							<p>
								※ 개인정보 변경 시 유의사항<br>
								<br> 반드시 본인의 정보를 입력해주시기 바랍니다.<br> 이메일은 계정 분실 시
								아이디/비밀번호 찾기에 사용됩니다.<br>
							</p>
						</div>

						<button id="btn"
							class="btn btn btn-outline-light btn-lg btn-block" type="submit">내
							정보 변경</button>

					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>

	<jsp:include page="../common/footer.jsp"></jsp:include>

	<!-- 회원 관련 Javascript 코드를 모아둘 wsp_member.js 파일을 작성 -->
	<script>
		(function() {

			var interest = "${loginMember.memberInterest}".split(",");

			$("input[name='memberInterest']").each(function(index, item) {

				// interest 배열 내에
				// 현재 접근중인 체크박스의 value와 일치하는 요소가 있는지 확인
				if (interest.indexOf($(item).val()) != -1) {
					$(item).prop("checked", true);
				}

			});

		})();
	</script>
	<script src="${contextPath}/resources/js/member.js"></script>







</body>
</html>
