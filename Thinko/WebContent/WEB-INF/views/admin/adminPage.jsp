<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 띵코</title>
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

*{
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
				<h2>대시 보드</h2>
				<div class="row">
					<div class="col-md-6">
						<div class="statistic__item">
							<h2 class="number">${visitorCount}</h2>
							<span class="desc">금일 방문자 수</span>
							<div class="icon">
								<i class="zmdi zmdi-account-o"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="statistic__item">
							<h2 class="number">${boardCount}</h2>
							<span class="desc">총 게시글 수</span>
							<div class="icon">
								<i class="zmdi zmdi-calendar-note"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h2 class="title-1 m-b-25">최근 게시글</h2>
						<div class="table-responsive table--no-card m-b-40">
							<table class="table table-borderless table-striped purple-table">
								<thead>
									<tr class="text-center">
										<th>글번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>댓글수</th>
										<th>좋아요</th>
										<th>조회수</th>
										<th>작성일</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty bList}">
											조회할 게시글이 존재하지 않습니다.
										</c:when>
										
										<c:otherwise>
											<c:forEach var="board" items="${bList}">
												<tr class="text-center">
													<td>${board.boardNo}</td>
													<td>
														<%-- 제목의 길이가 30글자를 넘어가는 경우 --%>
														<c:set var="title" value="${board.boardTitle}"/>
														<c:if test="${fn:length(title) > 30 }">
															<c:set var="title" value="${fn:substring(title,0,30) }..."/>
														</c:if>
														
														${title}
													</td>
													<td>${board.memberId}</td>
													<td>${board.replyCount}</td>
													<td>${board.likeCount}</td>
													<td>${board.readCount}</td>
													<td>
														<%-- 날짜 출력 모양 지정 --%>
														<fmt:formatDate var="createDate" value="${board.boardCreateDate}" pattern="yyyy-MM-dd"/>
														<fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>
														
														<c:choose>
															<%-- 글 작성일이 오늘이 아닐 경우 --%>
															<c:when test="${createDate != today}">
																${createDate}
															</c:when>
															
															<%-- 글 작성일이 오늘일 경우 --%>
															<c:otherwise>
																<fmt:formatDate value="${board.boardCreateDate}" pattern="HH:mm"/>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>											
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-6">
						<h2 class="title-1 m-b-25">최근 댓글</h2>
						<div class="table-responsive table--no-card m-b-40">
							<table class="table table-borderless table-striped purple-table">
								<thead>
									<tr class="text-center">
										<th>댓글번호</th>
										<th>내용</th>
										<th>작성자</th>
										<th>작성일</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${empty rList}">
											조회할 댓글이 존재하지 않습니다.
										</c:when>
										
										<c:otherwise>
											<c:forEach var="reply" items="${rList}">
												<tr class="text-center">
													<td>${reply.replyNo}</td>
													<td>
														<%-- 제목의 길이가 30글자를 넘어가는 경우 --%>
														<c:set var="content" value="${reply.replyContent}"/>
														<c:if test="${fn:length(content) > 30 }">
															<c:set var="content" value="${fn:substring(title,0,30) }..."/>
														</c:if>
														
														${content}
													</td>
													<td>${reply.memberId}</td>
													<td>
														<%-- 날짜 출력 모양 지정 --%>
														<fmt:formatDate var="createDate" value="${reply.replyCreateDate}" pattern="yyyy-MM-dd"/>
														<fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>
														
														<c:choose>
															<%-- 글 작성일이 오늘이 아닐 경우 --%>
															<c:when test="${createDate != today}">
																${createDate}
															</c:when>
															
															<%-- 글 작성일이 오늘일 경우 --%>
															<c:otherwise>
																<fmt:formatDate value="${reply.replyCreateDate}" pattern="HH:mm"/>
															</c:otherwise>
														</c:choose>
													</td>
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
					<div class="col-md-6">
						<h2 class="title-1 m-b-25">최근 신고 게시글</h2>
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
											조회할 글이 존재하지 않습니다.
										</c:when>
										
										<c:otherwise>
											<c:forEach var="rBoard" items="${rbList}">
												<tr class="text-center">
													<td>${rBoard.boardNo}</td>
													<td>
														<%-- 제목의 길이가 30글자를 넘어가는 경우 --%>
														<c:set var="title" value="${rBoard.boardTitle}"/>
														<c:if test="${fn:length(title) > 30 }">
															<c:set var="title" value="${fn:substring(title,0,30) }..."/>
														</c:if>
														
														${title}	
													</td>
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
					<div class="col-md-6">
						<h2 class="title-1 m-b-25">최근 신고 댓글</h2>
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
													<td>
														<%-- 제목의 길이가 30글자를 넘어가는 경우 --%>
														<c:set var="content" value="${rReply.replyContent}"/>
														<c:if test="${fn:length(content) > 30 }">
															<c:set var="content" value="${fn:substring(title,0,30) }..."/>
														</c:if>
														${content}
													</td>
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

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>