<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/member.css">

</head>

<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<div class="row my-5">
			<jsp:include page="memberSideMenu.jsp"></jsp:include>

			<div id="container" class="col-sm-8">
				<h5><i class="fas fa-door-open"></i>&nbsp;&nbsp;S E C E S S I O N</h5>
				<hr>
				<br>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updateStatus.do"
						onsubmit="return secessionValidate();" class="form-horizontal"
						role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memberId }</h5>
							</div>
						</div>

						<!-- 비밀번호 -->
						<div class="mb-3 form-row">
							<div class="infoList col-md-3">
								<label for="currentPwd">비밀번호</label>
							</div>

							<div class="inputDiv col-md-6">
								<input type="password" class="inputBox" id="currentPwd"
									name="currentPwd" autocomplete="off"
									placeholder="회원 비밀번호를 입력해주세요." maxlength="12">
							</div>
						</div>

						<hr>
						<div class="panel panel-default">

							<div class="panel-body">
								<div id="text-form" class="form-group">
									<br><label class="control-label"> ※ 안내사항 </label>
									<div class="col-xs-12">
										<textarea id="text-area" class="form-control" readonly rows="10" cols="100">
사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.
탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시기 바랍니다.

탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.
개인형 서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.
삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.

탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.
게시글 및 댓글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다.

삭제를 원하는 게시글이 있다면 반드시 탈퇴 전 삭제하시기 바랍니다.
탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다.
</textarea>
									</div>
									<div class="checkbox pull-right">
										<div class="custom-checkbox">
											<div class="form-check">
												<input type="checkbox" name="agree" id="agree"
													class="form-check-input custom-control-input"> <br>
												<label class="form-check-label custom-control-label"
													for="agree">안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br><br><br>
						<button id="btn" class="btn btn-outline-light btn-lg btn-block"
							type="submit">회원 탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script src="${contextPath}/resources/js/member.js"></script>

</body>
</html>
