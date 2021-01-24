<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 좋아하는 글</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/member.css">
<style>

.list-wrapper2 {
	max-height: 640px;
	box-sizing: border-box;
}

#my-board td:not(:first-child):hover, #my-comment td:not(:first-child):hover {
	cursor: pointer;
}

#my-board tr>th:nth-of-type(3), #my-comment tr>th:nth-of-type(2) {
	width: 340px;
}

.like:hover {
  -webkit-transform: scale(1.5);
  transition: all 0.2s ease-in-out;  /* 서서히 확대 */
  cursor:pointer
}
.like {
  transform: scale(1.1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1.1);
  -ms-transform: scale(1.1);
  -o-transform: scale(1.1);
}

</style>
</head>

<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<div class="row my-5">
			<jsp:include page="memberSideMenu.jsp"></jsp:include>

			<div id="container" class="col-sm-8">
				<h5><i class="fas fa-heart"></i>&nbsp;&nbsp;M Y &nbsp; F A V O R I T E</h5><br>
				<div class="list-wrapper2 bg-white table-responsive table--no-card m-b-40">
					<table id="my-board" class="table table-hover ">
						<thead>
							<tr>
								<th></th>
								<th>게시판</th>
								<th>제목</th>
								<th>조회수</th>
								<th>추천수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<%-- 게시글 목록 출력 --%>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="my-1">
					<ul id="board-pagination" class="pagination"></ul>
				</div>
				</div>
				</div>
				</div>
				

	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="${contextPath}/resources/js/member.js"></script>

	<script>
	 jQuery.noConflict(); 

	var boardCp = 1;
	
	$(function(){
		selectList();
	});
	
	function selectList(currentCp) {
		
		boardCp = currentCp;
		
		$.ajax({
			url : "${contextPath}/member/myFavoriteList.do",
			data : {"boardCp" : boardCp},
			type : "POST",
			dataType: "JSON",
			success : function(boardMap) {
				
				$("#my-board > tbody").empty();
				
				var bList = boardMap.bList;
				
				$.each(bList, function(index, item){
					
					var tr = $("<tr>");
					
					var icon = $("<i>").addClass("like icon-pink fas fa-heart");
					icon.attr('id', item.boardNo);		
					var td1 = $("<td>").append(icon);
					var td2 = $("<td>").text(item.boardNo);
					var td3 = $("<td>").text(item.boardTypeName);
					var td4 = $("<td>").text(item.boardTitle + " [ " + item.replyCount + " ]");
					var td5 = $("<td>").text(item.readCount);
					var td6 = $("<td>").text(item.likeCount);
					var td7 = $("<td>").text(item.boardCreateDate);
					
					tr.append(td1, td2, td3, td4, td5, td6, td7);
					
					$("#my-board > tbody").append(tr);

				});
					
          var pInfo = boardMap.pInfo;
           boardCp = pInfo.currentPage; // 전역 변수 값 세팅
           var prev =  Math.floor((boardCp - 1)/pInfo.limit) * pInfo.limit;
           var next = Math.floor((boardCp + 9)/pInfo.limit) * pInfo.limit+1;
           
           var $pagination = $("#board-pagination").eq(0); 
           $pagination.empty();
           
					 var $liForm = $("<li>").addClass("page-item");
           var $btnForm = $("<button>").addClass("page-link");
           
           
           if(boardCp > pInfo.limit){
              // 첫페이지
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList(1)").text("<<");
              $pagination.append($li.append($btn));
              
              // 이전 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList("+prev+")").text("<");
              $pagination.append($li.append($btn));
              
           }            
           
           // 페이지 번호
           for(var i=pInfo.startPage ; i<=pInfo.endPage ; i++ ){
              $li = $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList("+i+")").text(i);
              $pagination.append($li.append($btn));
           }
           
           
           if(next <= pInfo.maxPage){
              // 다음 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList("+next+")").text(">");
              $pagination.append($li.append($btn));
              
              // 마지막 페이징바
              $li = $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList("+pInfo.maxPage+")").text(">>");
              $pagination.append($li.append($btn));
           }
					
				
			},
			error : function() {
				console.log("게시글 서버 통신 오류");
			}
		})
		
	}
	
	 
	
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(document).on("click", "#my-board td:not(:first-child)", function(){

							var boardNo = $(this).parent().children().eq(1).text();

							var url = "${contextPath}/board1/boardView.do?no="
									+ boardNo + "&mf=true";

							location.href = url;
			});
		

		// 좋아요 해제 기능
		$(document).on("click", ".like", function(){
				var id = $(this).attr("id");
				
				if (confirm("좋아요를 취소하시겠습니까?")) {

					$.ajax({
						url : "${contextPath}/member/updateLikeFl.do",
						type : "POST",
						traditional : true,
						data : {
							"id" : id
						},
						success : function(result) {
							if (result == 1) {
								swal({"icon" : "success" , "title" : "좋아요가 취소되었습니다."});
							} else {
								swal({"icon" : "error" , "title" : "좋아요 취소에 실패했습니다."});
							}
							
							selectList()
						},
						error : function() {
							console.log("서버 통신 오류");
						}
					});

				}
				
			});
		
		
		
		
		
		
		
		
	</script>




</body>
</html>
