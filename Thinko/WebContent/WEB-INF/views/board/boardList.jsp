<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<style>
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


#list-table th, #list-table td {
	text-align: center;
}





#list-table td:hover {
	cursor: pointer;
}

.list-section th a {
	display: inline !important;
}

.mt-5, .my-5 {
    margin-top: 0px !important;
}

.pt-5, .py-5 {
    padding-top: 3rem !important;
    height: 900px;
}

.board-wrapper{
	min-height: 1087px;
}

.boardTitle{
	text-align: left !important;
}

.boardTitle > img{
	width: 50px;
}
</style>
<body>


	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid mt-5">
		<jsp:include page="boardSideMenu.jsp"></jsp:include>
		<div class="board-wrapper row my-5">
			<div class="col-md-1"></div>
			<div class="mt-5 list-section col-9" style="bottom:49px;">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-hover table-striped board-list-table" id="list-table">
							<thead>
								<tr>
									<th scope="col"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                                    </svg></th>
									<th scope="col" colspan="5" class="board-title"><c:choose>
											<c:when test="${!empty param.sv }">
												"${param.sv}"  검색 결과
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when test="${type==10}">자유게시판</c:when>
													<c:when test="${type==20}">인기글게시판</c:when>
													<c:when test="${type==30}">질문게시판</c:when>
													<c:when test="${type==40}">스터디게시판</c:when>
													<c:when test="${type==50}">맛집게시판</c:when>
													<c:when test="${type==60}">정보게시판</c:when>

												</c:choose>

											</c:otherwise>
										</c:choose></th>


									<c:choose>

										<%-- 검색을 했을 때 (/board/list.do?sk=title&sv=맛집)-> 드롭다운 --%>
										<c:when test="${!empty param.sv}">
											<th>

												<div class="dropdown">

													<select name="sk" class="form-control" style="width: 150px; display: inline-block;">
														
														<option value="title">제목</option>
														<option value="content">내용</option>
														<option value="titcont">제목+내용</option>
														<option value="writer">작성자</option>
													</select>
												</div>

											</th>
										</c:when>

										<%-- 검색을 안했을 때 (/board/list.do) -> 돋보기 --%>
										<c:otherwise>
											<th>
												<a data-toggle="modal" href="#modal-container-1"> 
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search mr-3" viewBox="0 0 16 16" name="sv">
			                     	<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
			                    </svg>
												</a> 
												
												
												
												
												<c:if test="${param.type != 20 }"> 
													<a href="${contextPath}/board3/insertForm.do?type=${type}"> 
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
					                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
					                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
				                  	</svg>
			                  	</a> 
		                  	</c:if>
		                  	
	                  	</th>
										</c:otherwise>


									</c:choose>

								</tr>
								<tr>

									<th scope="col">글번호</th>
									<th scope="col">카테고리</th>
									<th scope="col" width="40%">제목</th>
									<th scope="col">글쓴이</th>
									<th scope="col">작성일</th>
									<th scope="col">좋아요</th>
									<th scope="col">조회수</th>

								</tr>
							</thead>

							<%-- 게시글 목록 출력 --%>
							<tbody>

								<c:choose>
									<c:when test="${empty bList}">
										<tr>
											<td colspan="6">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>

									<c:otherwise>
										<c:forEach var="board" items="${bList}">
											<tr>
												<th scope="row">${board.boardNo}</th>
												
												<td>${board.boardCategoryName}</td>
												
												<td class="boardTitle">
												
													<%-- ㅆ ㅓㅁㄴㅔㅇㅣㄹ--%>
													<c:forEach var="thumbnail" items="${fList}">
														<c:if test="${board.boardNo == thumbnail.parentBoardNo }">
															<img src="${contextPath}/resources/uploadImages/${thumbnail.fileName}">
														</c:if>
													</c:forEach> 
													
													
												
												
													<%-- 제목의 길이가 30글자를 넘어가는 경우 --%>
													<c:set var="title" value="${board.boardTitle}"/>
													<c:if test="${fn:length(title) > 20 }">
														<c:set var="title" value="${fn:substring(title,0,20) }..."/>
													</c:if>
													
													${title}
												
													[${board.replyCount}]
												</td>
												
												
												
												<c:choose>
													<c:when test="${type==10}">
														<td>익명</td>
													</c:when>
													<c:otherwise>
														<td>${board.memberId}</td>
													</c:otherwise>
												</c:choose>
												
												<td><fmt:formatDate var="createDate" value="${board.boardCreateDate }" pattern="yyyy-MM-dd" /> <fmt:formatDate var="today" value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" /> <c:choose>
														<%-- 글 작성일이 오늘이 아닐 경우 --%>
														<c:when test="${createDate != today}">
                                       ${createDate}
                                    </c:when>
														<%-- 글 작성일이 오늘일 경우 --%>
														<c:otherwise>
															<fmt:formatDate value="${board.boardCreateDate }" pattern="HH:mm" />
														</c:otherwise>
													</c:choose></td>
												<td><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
												  <path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
												</svg> ${board.likeCount }</td>
												<td><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
												  <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z" />
												  <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z" />
												</svg> ${board.readCount }</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>

				<c:choose>
					<%-- 검색 내용이 파라미터에 존재할 때  == 검색을 통해 만들어진 페이지인가? --%>
					<c:when test="${!empty param.sv }">
						<c:url var="pageUrl" value="/search.do" />

						<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
						<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
					</c:when>

					<c:otherwise>
						<c:url var="pageUrl" value="/board2/list.do" />
					</c:otherwise>
				</c:choose>

				<c:set var="firstPage" value="${pageUrl}?type=${param.type}&cp=1${searchStr}" />
				<!-- pageUrl == /board/list.do -->
				<c:set var="lastPage" value="${pageUrl}?type=${param.type}&cp=${pInfo.maxPage}${searchStr}" />

				<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10}" integerOnly="true" />
				<fmt:parseNumber var="prev" value="${c1 * 10}" integerOnly="true" />
				<c:set var="prevPage" value="${pageUrl}?type=${param.type}&cp=${prev}${searchStr}" />

				<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10 }" integerOnly="true" />
				<fmt:parseNumber var="next" value="${c2 * 10 + 1 }" integerOnly="true" />
				<c:set var="nextPage" value="${pageUrl}?type=${param.type}&cp=${next}${searchStr}" />

				<div class="my-5">
					<ul class="pagination">
						<%-- 현재 페이지가 10페이지 초과인 경우 --%>
						<c:if test="${pInfo.currentPage > 10}">
							<li>
								<%-- 첫 페이지로 이동(<<) --%> <a class="page-link" href="${firstPage}">&lt;&lt;</a>
							</li>
							<li>
								<%-- 이전 페이지로 이동(<) --%> <a class="page-link" href="${prevPage}">Previous</a>
							</li>
						</c:if>
						
						
							
					
						<c:if test="${type != 20 }"> 
							<!--  페이지 목록   -->
							<c:forEach var="page" begin="${pInfo.startPage}" end="${pInfo.endPage}">
								<c:choose>
									<c:when test="${pInfo.currentPage == page}">
										<li><a class="page-link">${page}</a></li>
									</c:when>
	
									<c:otherwise>
										<li><a class="page-link" href="${pageUrl}?type=${param.type}&cp=${page}${searchStr}">${page}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</c:if>

						<%-- 다음 페이지가 마지막 페이지 이하인 경우 --%>
						<c:if test="${next <= pInfo.maxPage}">
							<li>
								<%-- 다음 페이지로 이동(>) --%> <a class="page-link" href="${nextPage}">Next</a>
							</li>
							<li>
								<%-- 마지막 페이지로 이동(>>) --%> <a class="page-link" href="${lastPage}">&gt;&gt;</a>
							</li>
						</c:if>

					</ul>
				</div>








			</div>
		</div>
	</div>


	<div class="modal fade" id="modal-container-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">검색창</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-signin" method="GET" action="${contextPath}/search.do">

						<input type="text" class="form-control" id="search" name="sv"> <br> 
						<input type="hidden" name="type" value="${param.type}"> <br>


						<button class="btn btn-lg btn-primary btn-block" type="submit">검색</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>



	<script>
		
		// 게시글 상세보기 기능 (jquery를 통해 작업)

		/* tr위에 td가 얹어져 있는 형식으로 되어있다  */
		$("#list-table td")
				.on(
						"click",
						function() {

							// 게시글 번호 얻어오기
							var boardNo = $(this).parent().children().eq(0)
									.text();
							// 이벤트가 발생한 요소의 부모의 자식 중에 첫번째 자식의 text를 얻어와라
							// console.log(boardNo);

							var url = "${contextPath}/board1/boardView.do?cp=${pInfo.currentPage}&no="
									+ boardNo + "${searchStr}&type=${param.type}";
							// contextPath == 최상위주소
							location.href = url;
							// 해당 글로 요청을 보냄
						});

		
		// 검색 내용이 있을 경우 검색창에 해당 내용을 작성해두는 기능
		(function() {
			var searchKey = "${param.sk}";
			// 파라미터 중 sk가 있을 경우 ex) "49"
			// 파라미터 중 sk가 없을 경우 ex) ""

			var searchValue = "${param.sv}";

			// 검색창 select의 option을 반복 접근
			// select태그의 속성 중 name값이 sk인 것의 자식 중 option 태그를 모두 선택해라
			$("select[name=sk] > option").each(function(index, item) {
				// index : 현재 접근중인 요소의 인덱스
				// item : 현재 접근중인 요소

				// title				title
				if ($(item).val() == searchKey) { // 제목으로 검색한 경우
					$(item).prop("selected", true);
				}
			});

			// 검색어 입력창에 searchValue값 출력
			$("input[name=sv]").val(searchValue);
			// input태그 중에서 name 속성이 sv인 요소의 값으로 searchValue 출력

		})();
		
		
		(function() {
			var searchKey = "${param.sk}";
			// 파라미터 중 sk가 있을 경우 ex) "49"
			// 파라미터 중 sk가 없을 경우 ex) ""

			var searchValue = "${param.sv}";

			// 검색창 select의 option을 반복 접근
			// select태그의 속성 중 name값이 sk인 것의 자식 중 option 태그를 모두 선택해라
			$("select[name=sk] > option").each(function(index, item) {
				// index : 현재 접근중인 요소의 인덱스
				// item : 현재 접근중인 요소

				// title				title
				if ($(item).val() == searchKey) { // 제목으로 검색한 경우
					$(item).prop("selected", true);
				}
			});

			// 검색어 입력창에 searchValue값 출력
			$("input[name=sv]").val(searchValue);
			// input태그 중에서 name 속성이 sv인 요소의 값으로 searchValue 출력

		})();
		
		
		// 검색 조건 변경
		$("select[name='sk']").on("change", function(){
			var sk = $(this).val();
			var sv = "${param.sv}";
			var type = "${param.type}";
			var cp = "${param.cp}";
			
			
			location.href = "${contextPath}/search.do?type="+type+"&sk="+sk+"&sv="+sv;
			
		});
		
		
	</script>


</body>
</html>