<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.form-control {
	border: 1px solid #ced4da !important;
}

.list-section {
	float: left;
	width: 65%;
	padding-left: 50px;
	padding-right: 50px;
	margin: 0 !important;
}

.list-section>table {
	vertical-align: middle;
}

.list-section tr {
	height: 70px;
}

.list-section>.row:first-of-type {
	min-height: 90%;
}

.pagenation-section {
	clear: both;
	text-align: center;
}

.bi {
	size: 30px;
}

thead th {
	padding-bottom: 50px !important;
}

tbody, td, tfoot, th, thead, tr {
	border-style: none !important;
}

thead svg {
	width: 25px;
	height: 25px;
}

.board-title {
	font-size: x-large;
}

.pagination {
	justify-content: center;
}

#list-table td:hover {
	cursor: pointer;
}

.list-section th a {
	display: inline !important;
}

.board-wrapper {
	min-height: 1000px;
}

.mt-5, .my-5 {
    margin-top: 0px !important;
}

.pt-5, .py-5 {
    padding-top: 3rem !important;
    height: 900px;
}
/* .board-list-table > thead th{
	text-align: center;
} */
</style>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid mt-5">
		<jsp:include page="../board/boardSideMenu.jsp"></jsp:include>
		<div class="board-wrapper row my-5">
			<div class="col-md-1"></div>
			<div class="mt-5 list-section col-9">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-hover table-striped" id="list-table">
							<thead>
								<tr>
									<th scope="col"><svg xmlns="http://www.w3.org/2000/svg"
											width="16" height="16" fill="currentColor" class="bi bi-list"
											viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
												d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                                    </svg></th>
									<th scope="col" colspan="4" class="board-title">공지사항</th>
											<th><a data-toggle="modal" href="#modal-container-1">
													<svg xmlns="http://www.w3.org/2000/svg" width="16"
														height="16" fill="currentColor" class="bi bi-search mr-3"
														viewBox="0 0 16 16" name="sv">
			                     						<path
															d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
			                    					</svg>
											</a> <c:if
													test="${!empty loginMember && loginMember.memberGrade eq 'A' }">
													<a href="${contextPath}/notice/noticeInsertForm.do"> <svg
															xmlns="http://www.w3.org/2000/svg" width="16" height="16"
															fill="currentColor" class="bi bi-pencil-square"
															viewBox="0 0 16 16">
									                    <path
																d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
									                    <path fill-rule="evenodd"
																d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
									                 	</svg>
													</a>
												</c:if></th>
								<tr class="text-center">

									<th>글번호</th>
									<th colspan="2">제목</th>
									<th>글쓴이</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty nList}">
										<tr>
											<td colspan="6" class="text-center">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>

									<c:otherwise>
										<%-- 조회된 게시글 목록이 있을 때 --%>
										<c:forEach var="notice" items="${nList}">
											<tr class="text-center">
												<td>${notice.noticeNo}</td>
												<td colspan="2">${notice.noticeTitle}</td>
												<td>${notice.memberId}</td>
												<td>
													<%-- 날짜 출력 모양 지정 --%> <fmt:formatDate var="createDate"
														value="${notice.noticeCreateDate}" pattern="yyyy-MM-dd" />
													<fmt:formatDate var="today"
														value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />

													<c:choose>
														<%-- 글 작성일이 오늘이 아닐 경우 --%>
														<c:when test="${createDate != today}">
																${createDate}
															</c:when>

														<%-- 글 작성일이 오늘일 경우 --%>
														<c:otherwise>
															<fmt:formatDate value="${notice.noticeCreateDate}"
																pattern="HH:mm" />
														</c:otherwise>
													</c:choose>
												</td>
												<td>${notice.readCount}</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>

						<%---------------------- Pagination ----------------------%>
						<%-- 페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장 --%>
						<c:choose>
							<%-- 검색 내용이 파라미터에 존재할 때 == 검색을 통해 만들어진 페이지인가? --%>
							<c:when test="${!empty param.sk && !empty param.sv }">
								<c:url var="pageUrl" value="/notice/noticeSearch.do" />

								<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
								<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
							</c:when>

							<c:otherwise>
								<c:url var="pageUrl" value="/notice/noticeList.do" />
							</c:otherwise>
						</c:choose>


						<!-- 화살표에 들어갈 주소를 변수로 생성 -->
						<%--
								검색을 안했을 때 : /board/list.do?cp=1
								검색을 했을 때 : /search.do?cp=1&sk=title&sv=49
							 --%>
						<c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}" />
						<c:set var="lastPage"
							value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}" />

						<%-- EL을 이용한 숫자 연산의 단점 : 연산이 자료형의 영향을 받지 않는다. --%>
						<%-- 
								<fmt:parseNumber>		:	숫자 형태를 지정하여 변수 선언
								integerOnly="true"	:	정수로만 숫자 표현(소수점 버림)
							--%>

						<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10 }"
							integerOnly="true" />
						<fmt:parseNumber var="prev" value="${c1 * 10}" integerOnly="true" />
						<c:set var="prevPage" value="${pageUrl}?cp=${prev}" />

						<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10}"
							integerOnly="true" />
						<fmt:parseNumber var="next" value="${c2 * 10 + 1}"
							integerOnly="true" />
						<c:set var="nextPage" value="${pageUrl}?cp=${next}" />

						<div class="my-5">
							<ul class="pagination">

								<%-- 현재 페이지가 10페이지 초과인 경우 --%>
								<c:if test="${pInfo.currentPage > 10}">
									<li>
										<!-- 첫페이지로 이동(<<) --> <a class="page-link"
										href="${firstPage}${searchStr}">&lt;&lt;</a>
									</li>

									<li>
										<!-- 이전 페이지로 이동(<) --> <a class="page-link"
										href="${prevPage}${searchStr}">&lt;</a>
									</li>
								</c:if>

								<!-- 페이지 목록 -->
								<c:forEach var="page" begin="${pInfo.startPage}"
									end="${pInfo.endPage}">
									<c:choose>
										<c:when test="${pInfo.currentPage == page }">
											<li><a class="page-link">${page}</a></li>
										</c:when>

										<c:otherwise>
											<li><a class="page-link"
												href="${pageUrl}?cp=${page}${searchStr}">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<%-- 다음 페이지가 마지막 페이지 미만인 경우 --%>
								<c:if test="${next <= pInfo.maxPage}">
									<li>
										<!-- 다음 페이지로 이동(>) --> <a class="page-link"
										href="${nextPage}${searchStr}">&gt;</a>
									</li>

									<li>
										<!-- 마지막페이지로 이동(>>) --> <a class="page-link"
										href="${lastPage}${searchStr}">&gt;&gt;</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-container-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">검색창</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-signin" method="POST" action="${contextPath}/notice/noticeSearch.do">
						<select name="sk" class="form-control" style="width: 110px; display: inline-block;">
						<option value="title">글제목</option>
						<option value="content">내용</option>
						<option value="titcont">제목+내용</option>
						<option value="writer">작성자</option>
						</select>
						<input type="text" class="form-control" id="search" name="sv">
						
						<br> 
						
						<input type="hidden" name="type" value="${param.type}">
						
						<br>
						<button class="btn btn-lg btn-primary btn-block" type="submit">검색</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script>
		$("#insertBtn").on("click", function() {
			var url = "${contextPath}/notice/noticeInsert.do";
			location.href = url;
		});

		$(".table td").on("click", function() {
			var noticeNo = $(this).parent().children().eq(0).text();
	
			var url = "${contextPath}/notice/noticeView.do?cp=${pInfo.currentPage}&no="
					+ noticeNo + "${searchStr}";
	
			location.href = url;
		});
	</script>
</body>
</html>