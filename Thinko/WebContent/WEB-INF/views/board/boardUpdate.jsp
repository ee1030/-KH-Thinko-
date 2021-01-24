<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>게시글 수정</title>

<style>
body {
	background-color: white !important;
}

#submit-button {
	float: right;
}

#submit-cancle-button {
	float: left;
}

#boardTitle {
	border: none;
	font-size: 18px;
}

.button1 {
	width: 100px;
	height: 40px;
	border-radius: 5px;
	border: none;
	padding: 4px;
	margin-left: 10px;
	font-size: 18px;
	background-color: #814798;
	color: white;
}
</style>
</head>

<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid mt-5">
		<jsp:include page="boardSideMenu.jsp"></jsp:include>
		<div class="container my-5">
			<div class="row my-5">
				<div class="col-md-2"></div>
				<div class="container">
					<c:if test="${!empty param.sk || !empty param.sv}">
						<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
					</c:if>
					<form
						action="<%=request.getContextPath()%>/board3/update.do?cp=${param.cp}&type=${boardType}&no=${board.boardNo}${searchStr}"
						method="post" role="form" onsubmit="return boardValidate();">
						<c:set var="type" value="${boardType}" />
						<c:choose>
							<c:when test="${type == 10}">
								<h5>자 유 게 시 판</h5>
								<hr>
								<br>
								<div class="mb-2">
									&nbsp;&nbsp; <select class="custom-select" id="categoryCode"
										name="categoryCode" style="width: 150px;">
										<option value="10">사담</option>
										<option value="20">공부</option>
										<option value="30">취미</option>
										<option value="40">고민</option>
										<option value="50">연애</option>
										<option value="60">기타</option>
									</select>
								</div>
							</c:when>
							<c:when test="${type == 30}">
								<h5>질 문 게 시 판</h5>
								<hr>
								<br>
								<div class="mb-2">
									&nbsp;&nbsp; <select class="custom-select" id="categoryCode"
										name="categoryCode" style="width: 150px;">
										<option value="300">백엔드</option>
										<option value="310">프론트엔드</option>
										<option value="320">DB</option>
										<option value="330">기타</option>
									</select>
								</div>
							</c:when>
							<c:when test="${type == 40}">
								<h5>스 터 디 게 시 판</h5>
								<hr>
								<br>
								<div class="mb-2">
									&nbsp;&nbsp; <select class="custom-select" id="categoryCode"
										name="categoryCode" style="width: 150px;">
										<option value="400">스터디</option>
										<option value="410">공모전</option>
										<option value="420">동아리</option>
									</select>
								</div>
							</c:when>
							<c:when test="${type == 60}">
								<h5>정 보 게 시 판</h5>
								<hr>
								<br>
								<div class="mb-2">
									&nbsp;&nbsp; <select class="custom-select" id="categoryCode"
										name="categoryCode" style="width: 150px;">
										<option value="600">채용공고</option>
										<option value="610">자격증</option>
										<option value="620">면접후기</option>
										<option value="630">취뽀후기</option>
									</select>
								</div>
							</c:when>
							<c:when test="${type == 50}">
								<h5>맛 집 게 시 판</h5>
								<hr>
								<br>
								<div class="mb-2">
									&nbsp;&nbsp; <select class="custom-select" id="categoryCode"
										name="categoryCode" style="width: 150px;">
										<option value="500">종로</option>
										<option value="510">강남</option>
										<option value="520">당산</option>
									</select>
								</div>
							</c:when>
						</c:choose>
						<div class="form-inline mb-2">
							<input type="text" class="form-control" id="boardTitle"
								name="boardTitle" value="${board.boardTitle }" size="90"
								autocomplete="off" maxlength="100">
						</div>

						<hr class="mb-4">


						<div class="container my-5">


							<textarea id="summernote" name="boardContent">${board.boardContent }</textarea>


						</div>


						<hr class="mb-4">

						<div class="text-center">
							<button id=submit-cancle-button type="button" class="button1"
								onclick="javascript:history.back();">취소</button>
							<button id=submit-button type="submit" class="button1">수정</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<script>
		jQuery.noConflict();

		$('#summernote').summernote({
			minHeight : 500,
			maxWidth : 1050,
			focus : true,
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {

					for (var i = files.length - 1; i >= 0; i--) {
						sendFile(files[i], this);
					}
				}
			}

		});

		function sendFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "${contextPath}/board3/imageUpload.do",
				cache : false,
				contentType : false,
				processData : false,
				enctype : 'multipart/form-data',
				success : function(data) {
					$(editor).summernote('editor.insertImage',
							"${contextPath}/resources/uploadImages/" + data);
				}
			});
		}

		(function() {
			$("#categoryCode > option").each(function(index, item) {

				if ($(item).text() == "${board.boardCategoryName}") {
					$(item).prop("selected", true);
				}
			});
		})();

		function boardValidate() {
			if ($("#boardTitle").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#boardTitle").focus();
				return false;
			}

			if ($("#summernote").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#summernote").focus();
				return false;
			}

		}
	</script>

</body>

</html>