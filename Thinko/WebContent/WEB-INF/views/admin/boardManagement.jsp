<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.purple-table thead th {
	background-color : #866ec7;
	font : 16px;
	color : white;
	font-weight: 400;
	vertical-align: middle;
    font-weight: 400;
    text-transform: capitalize;
    line-height: 1;
    padding: 22px 40px;
    white-space: nowrap;
}

.mouseOver:hover {
	cursor : pointer;
}

* {
	font-family: 'Noto Sans KR', sans-serif;
	color: rgb(37, 37, 37);
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="container-fluid mt-5">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="adminSide.jsp"></jsp:include>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-6"> 
						<div id="allBoard" class="statistic__item mouseOver">
							<h2 class="number">${boardCount}</h2>
							<span class="desc">총 게시글 수</span>
							<div class="icon">
								<i class="zmdi zmdi-calendar-note"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div id="allReply" class="statistic__item mouseOver">
							<h2 class="number">${replyCount}</h2>
							<span class="desc">총 댓글 수</span>
							<div class="icon">
								<i class="zmdi zmdi-calendar-note"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h2 id="reportedBoard" class="title-1 m-b-25 mouseOver">신고
							게시글</h2>
						<div class="table-responsive table--no-card m-b-40">
							<table class="table table-borderless table-striped purple-table">
								<thead>
									<tr class="text-center">
										<th>글번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>조회수</th>
										<th>작성일</th>
										<th>신고 횟수</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty rbList}">
											조회할 댓글이 존재하지 않습니다.
										</c:when>
										
										<c:otherwise>
											<c:forEach var="rBoard" items="${rbList}">
												<tr class="text-center">
													<td>${rBoard.boardNo}</td>
													<td>${rBoard.boardTitle}</td>
													<td>${rBoard.memberId}</td>
													<td>${rBoard.readCount}</td>
													<td>
														<%-- 날짜 출력 모양 지정 --%>
														<fmt:formatDate var="createDate" value="${rBoard.boardCreateDate}" pattern="yyyy-MM-dd"/>
														<fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>
														
														<c:choose>
															<%-- 글 작성일이 오늘이 아닐 경우 --%>
															<c:when test="${createDate != today}">
																${createDate}
															</c:when>
															
															<%-- 글 작성일이 오늘일 경우 --%>
															<c:otherwise>
																<fmt:formatDate value="${rBoard.boardCreateDate}" pattern="HH:mm"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>${rBoard.reportCount}</td>
												</tr>											
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h2 id="reportedReply" class="title-1 m-b-25 mouseOver">신고 댓글</h2>
						<div class="table-responsive table--no-card m-b-40">
							<table class="table table-borderless table-striped purple-table">
								<thead>
									<tr class="text-center">
										<th>댓글번호</th>
										<th>내용</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>신고 횟수</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty rrList}">
											조회할 댓글이 존재하지 않습니다.
										</c:when>
										
										<c:otherwise>
											<c:forEach var="rReply" items="${rrList}">
												<tr class="text-center">
													<td>${rReply.replyNo}</td>
													<td>${rReply.replyContent}</td>
													<td>${rReply.memberId}</td>
													<td>
														<%-- 날짜 출력 모양 지정 --%>
														<fmt:formatDate var="createDate" value="${rReply.replyCreateDate}" pattern="yyyy-MM-dd"/>
														<fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>
														
														<c:choose>
															<%-- 글 작성일이 오늘이 아닐 경우 --%>
															<c:when test="${createDate != today}">
																${createDate}
															</c:when>
															
															<%-- 글 작성일이 오늘일 경우 --%>
															<c:otherwise>
																<fmt:formatDate value="${rReply.replyCreateDate}" pattern="HH:mm"/>
															</c:otherwise>
														</c:choose>
													</td>
													<td>${rReply.reportCount}</td>
												</tr>											
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2">
			</div>
		</div>
	</div>

	<script>
		// 총 게시물 수 클릭 시 모든 게시물 조회 페이지로 이동 하는 script
		$("#allBoard").on("click", function() {
			location.href = "${contextPath}/admin/allBoardList.do";
		});

		// 총 댓글 수 클릭 시 모든 댓글 조회 페이지로 이동 하는 script
		$("#allReply").on("click", function() {
			location.href = "${contextPath}/admin/allReplyList.do";
		});

		// 신고 게시물 클릭 시 신고된 게시글 목록 조회 페이지로 이동 하는 script
		$("#reportedBoard").on("click", function() {
			location.href = "${contextPath}/admin/reportedBoardList.do";
		});

		// 신고 댓글 클릭 시 신고된 댓글 조회 페이지로 이동 하는 script
		$("#reportedReply").on("click", function() {
			location.href = "${contextPath}/admin/reportedReplyList.do";
		});
	</script>

</body>
</html>