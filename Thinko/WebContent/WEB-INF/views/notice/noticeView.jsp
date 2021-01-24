<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.row {
	min-height: 1000px;
}

#board-area {
	margin-bottom: 50px;
}

.content-area {
	min-height: 500px;
	margin-bottom: 50px;
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
	text-align: center;
}

#board-area a {
	text-decoration: none;
	color: #ffffff;
}

.titleIcon2 {
	color: #814798;
}

.titleIcon1 {
	color: gold;
}

.titleIcon2:hover {
	cursor: pointer;
}

.titleIcon1:hover {
	cursor: pointer;
}

#deleteBtn{
	cursor: pointer;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- Page Content -->
	<div class="container-fluid mt-5">
		<jsp:include page="../board/boardSideMenu.jsp"></jsp:include>
		<div class="row my-5">
			<div class="container">
			<!-- Post Content Column -->
			<div class="col-lg-12">
				<div id="board-area">
					<!-- Title -->
					<h1 class="mt-4">${notice.noticeTitle}</h1>

					<!-- Author -->
					<p class="lead">
						작성자 : ${notice.memberId} 
					</p>

					<hr>

					<!-- Date/Time -->
					<p>
						조회수 : ${notice.readCount} | 작성일 : 
						<fmt:formatDate value="${notice.noticeCreateDate}"
							pattern="yyyy년 MM월 dd일 HH:mm:ss" />
					</p>

					<hr>

					<!-- Post Content -->
					<div class="content-area">
						${notice.noticeContent}
					</div>
					<hr>

					<a href="../notice/noticeList.do?cp=${param.cp}" class="float-right mx-1 button1">목록으로</a>

					<c:if
						test="${!empty loginMember && loginMember.memberGrade eq 'A'}">

						<a id="deleteBtn" class="float-right mx-1 button1">삭제</a>

						<%-- 게시글 수정 후 상세조회 페이지로 돌아오기 위한 url 조합 --%>
						<c:if test="${!empty param.sv && !empty param.sk }">
							<%-- 검색을 통해 들어온 상세 조회 페이지인 경우 --%>
							<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.kv}" />
						</c:if>
						
						<a href="${contextPath}/notice/updateForm.do?cp=${param.cp}&no=${notice.noticeNo}" class="float-right mx-1 button1">수정</a>
					</c:if>
				</div>
			</div>

			<div class="col-lg-2"></div>
		</div>
		<!-- /.row -->
	</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script>
		$("#deleteBtn").on("click",function(){
			
			if(confirm("게시글을 삭제 하시겠습니까?")){
				location.href = "${contextPath}/notice/updateStatus.do?cp=${param.cp}&no=${notice.noticeNo}";
			}
			
		});
	</script>
</body>
</html>