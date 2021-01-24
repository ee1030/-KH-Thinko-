<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 띵띵코</title>
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


tbody td:hover {
	cursor : pointer;
}

.pagination {
	justify-content: center;
}

.col-md-8 {
	min-height: 700px;
}

.greenIcon {
	color: green;
}

.redIcon {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="adminSide.jsp"></jsp:include>
			</div>
			<div class="col-md-8">
				<h2 class="title-1 m-b-25">회원관리</h2>
				<div class="table-responsive table--no-card m-b-40">
					<table class="table table-borderless table-striped purple-table">
						<thead>
							<tr class="text-center">
								<th>회원 번호</th>
								<th>회원 아이디</th>
								<th>이름</th>
								<th>이메일</th>
								<th>연락처</th>
								<th>가입일자</th>
								<th>회원상태</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty mList}">
									<tr class="text-center">
										<td colspan=6>회원 조회 결과가 없습니다.</td>
								</c:when>
								<c:otherwise>
									<c:forEach var="member" items="${mList}">
										<tr class="text-center">
											<td>${member.memberNo}</td>
											<td>${member.memberId}( ${member.memberGrade} )</td>
											<td>${member.memberNm}</td>
											<td>${member.memberEmail}</td>
											<td>${member.memberPhone}</td>
											<td>${member.memberEnrollDate}</td>
											<td><c:choose>
													<c:when test="${member.memberStatus == 'Y'}">
														활동 <i class="fas fa-circle greenIcon"></i>
													</c:when>
													<c:otherwise>
														탈퇴 <i class="fas fa-circle redIcon"></i>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>
				</div>

				<%---------------------- Pagination ----------------------%>
				<%-- 페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장 --%>
				<c:choose>
					<%-- 검색 내용이 파라미터에 존재할 때 == 검색을 통해 만들어진 페이지인가? --%>
					<c:when test="${!empty param.sk && !empty param.sv }">
						<c:url var="pageUrl" value="/adminSearch/memberSearch.do" />

						<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
						<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
					</c:when>

					<c:otherwise>
						<c:url var="pageUrl" value="/admin/memberManagement.do" />
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
			<div class="col-md-2"></div>
		</div>
	</div>
	<div class="my-5">
		<form action="${contextPath}/adminSearch/memberSearch.do"
			method="GET" class="text-center" id="searchForm">
			<select name="sk" class="form-control"
				style="width: 100px; display: inline-block;">
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="email">이메일</option>
			</select> <input type="text" name="sv" class="form-control"
				style="width: 25%; display: inline-block;">
			<button class="form-control btn btn-primary"
				style="width: 100px; display: inline-block; color: white;">검색</button>
		</form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
		$(".table td")
				.on(
						"click",
						function() {
							var memberNo = $(this).parent().children().eq(0)
									.text();
							var memberStatus = $(this).parent().children()
									.eq(6).text().trim();

							if (confirm("회원의 가입 상태를 변경 하시겠습니까?")) {
								location.href = "${contextPath}/admin/updateMemberStatus.do?memNo="
										+ memberNo + "&memSt=" + memberStatus;
							}
						});
	</script>
</body>
</html>