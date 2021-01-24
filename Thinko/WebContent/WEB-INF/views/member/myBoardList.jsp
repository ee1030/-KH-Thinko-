<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 게시물</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/member.css">
<style>

</style>
</head>

<body>
		<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">

		<div class="row my-5">
			<jsp:include page="memberSideMenu.jsp"></jsp:include>

			<div id="container" class="col-sm-8">
				<h5><i class="fas fa-clipboard"></i>&nbsp;&nbsp;M Y &nbsp; B O A R D</h5><br>
				<div class="list-wrapper bg-white table-responsive table--no-card m-b-40">
					<table id="my-board" class="table table-hover ">
						<thead>
							<tr>
								<th><input type="checkbox" id="chk_all"></th>
								<th>게시판</th>
								<th>제목</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<%-- 게시글 목록 출력 --%>
						<tbody>
						</tbody>
					</table>
				</div>
				<button id="delete-button" type="button" onclick="deleteMyBoard()">삭제</button>
				<div class="my-1">
					<ul id="board-pagination" class="pagination"></ul>
				</div>


				<br><hr><br> 
				<h5><i class="far fa-comments"></i>&nbsp;&nbsp;M Y &nbsp; R E P L Y</h5><br>
				<div class="list-wrapper bg-white table-responsive table--no-card m-b-40">
					<table id="my-comment" class="table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="chk_all2"></th>
								<th>댓글</th>
								<th>원문제목</th>
								<th>작성일</th>
							</tr>
						</thead>
						<%-- 댓글 목록 출력 --%>
						<tbody>
						</tbody>
					</table>
				</div>
				<button id="delete-button" type="button" onclick="deleteMyReply()">삭제</button>
				<div class="my-1">
					<ul id="reply-pagination" class="pagination"></ul>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="${contextPath}/resources/js/member.js"></script>

	<script>
	 jQuery.noConflict(); 

	var boardCp = 1;
	var replyCp = 1;
	
	$(function(){
		selectList1();
		selectList2();
	});
	
	function selectList1(currentCp) {
		
		boardCp = currentCp;
		
		$.ajax({
			url : "${contextPath}/member/myBoardList.do",
			data : {"boardCp" : boardCp},
			type : "POST",
			dataType: "JSON",
			success : function(boardMap) {
				
				$("#my-board > tbody").empty();
				
				var bList = boardMap.bList;
				
				$.each(bList, function(index, item){
					
					var tr = $("<tr>");
					
					var check = $("<input type='checkbox' class='chk' name='checkedRow'>");
					check.attr('id', item.boardNo);					
					var td1 = $("<td>").append(check);
					var td2 = $("<td>").text(item.boardNo);
					var td3 = $("<td>").text(item.boardTypeName);
					var td4 = $("<td>").text(item.boardTitle);
					var td5 = $("<td>").text(item.readCount);
					var td6 = $("<td>").text(item.boardCreateDate);
					
					tr.append(td1, td2, td3, td4, td5, td6);
					
					$("#my-board > tbody").append(tr);

				});
					
          var pInfo = boardMap.boardPInfo;
           boardCp = pInfo.currentPage; // 전역 변수 값 세팅
           var prev =  Math.floor((boardCp - 1)/pInfo.limit) * pInfo.limit;
           var next = Math.floor((boardCp + 4)/pInfo.limit) * pInfo.limit+1;
           
           var $pagination = $("#board-pagination").eq(0); 
           $pagination.empty();
           
					 var $liForm = $("<li>").addClass("page-item");
           var $btnForm = $("<button>").addClass("page-link");
           
           
           if(boardCp > pInfo.limit){
              // 첫페이지
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList1(1)").text("<<");
              $pagination.append($li.append($btn));
              
              // 이전 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList1("+prev+")").text("<");
              $pagination.append($li.append($btn));
              
           }            
           
   		    	if(pInfo.maxPage < pInfo.endPage){
 		  				 pInfo.endPage = pInfo.maxPage
 						}
           
           // 페이지 번호
           for(var i=pInfo.startPage ; i<=pInfo.endPage ; i++ ){
              $li = $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList1("+i+")").text(i);
              $pagination.append($li.append($btn));
           }
           
           
           if(next <= pInfo.maxPage){
              // 다음 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList1("+next+")").text(">");
              $pagination.append($li.append($btn));
              
              // 마지막 페이징바
              $li = $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList1("+pInfo.maxPage+")").text(">>");
              $pagination.append($li.append($btn));
           }
					
				
			},
			error : function() {
				console.log("게시글 서버 통신 오류");
			}
		})
		
	}
	
function selectList2(currentCp) {
		
		replyCp = currentCp;
		
		$.ajax({
			url : "${contextPath}/member/myReplyList.do",
			data : {"replyCp" : replyCp},
			type : "POST",
			dataType: "JSON",
			traditional : true,
			success : function(replyMap) {
				
		$("#my-comment > tbody").empty();
				
				var rList = replyMap.rList;
				
				$.each(rList, function(index, item){
					
					var tr = $("<tr>");
					
					var check = $("<input type='checkbox' class='chk2' name='checkedRow2'>");
					check.attr('id', item.replyNo);			
					var td1 = $("<td>").append(check);
					var td2 = $("<td>").text(item.parentBoardNo);
					var td3 = $("<td>").text(item.replyContent);
					var td4 = $("<td>").text(item.boardTitle);
					var td5 = $("<td>").text(item.replyCreateDate);
					
					tr.append(td1, td2, td3, td4, td5);
					
					$("#my-comment > tbody").append(tr);

				});
					
          var pInfo = replyMap.replyPInfo;
           replyCp = pInfo.currentPage; // 전역 변수 값 세팅
           var prev =  Math.floor((replyCp - 1)/pInfo.limit) * pInfo.limit;
           var next = Math.floor((replyCp + 4)/pInfo.limit) * pInfo.limit+1;
           
           var $pagination = $("#reply-pagination").eq(0); 
           $pagination.empty();
           
					 var $liForm = $("<li>").addClass("page-item");
           var $btnForm = $("<button>").addClass("page-link");
           
           
           if(replyCp > pInfo.limit){
              // 첫페이지
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList2(1)").text("<<");
              $pagination.append($li.append($btn));
              
              // 이전 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList2("+prev+")").text("<");
              $pagination.append($li.append($btn));
              
           }            
           
        	if(pInfo.maxPage < pInfo.endPage){
        		   pInfo.endPage = pInfo.maxPage
        	}
        		   
           // 페이지 번호
	           for(var i=pInfo.startPage ; i<=pInfo.endPage ; i++ ){
	        	   
	              $li = $("<li>").addClass("page-item");
	              $btn = $("<button>").addClass("page-link");
	              $btn.attr("onclick", "selectList2("+i+")").text(i);
	              $pagination.append($li.append($btn));
			
	           }
           
           
           if(next <= pInfo.maxPage){
              // 다음 페이징바
              $li= $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList2("+next+")").text(">");
              $pagination.append($li.append($btn));
              
              // 마지막 페이징바
              $li = $("<li>").addClass("page-item");
              $btn = $("<button>").addClass("page-link");
              $btn.attr("onclick", "selectList2("+pInfo.maxPage+")").text(">>");
              $pagination.append($li.append($btn));
           }
				
			},
			error : function() {
				console.log("댓글 서버 통신 오류");
			}
		})
		
	}
	
	 
	
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		
		$(document).on("click", "#my-board td:not(:first-child)", function(){

							var boardNo = $(this).parent().children().eq(1).text();

							var url = "${contextPath}/board1/boardView.do?no="
									+ boardNo + "&mb=true";

							location.href = url;
			});
		
		$(document).on("click", "#my-comment td:not(:first-child)", function(){

							var boardNo = $(this).parent().children().eq(1).text();

							var url = "${contextPath}/board1/boardView.do?no="
									+ boardNo + "&mb=true";

							location.href = url;
			});
		
		

		// 게시글 삭제 기능
		function deleteMyBoard() {
			var checkedRow = $("input[name='checkedRow']:checked").length;
			var boardArr = new Array();
			$("input[name='checkedRow']:checked").each(function() {
				boardArr.push($(this).attr('id'));
			});

			if (checkedRow == 0) {
				alert("선택된 글이 없습니다.");
			} else {

				if (confirm("선택한 글을 삭제하시겠습니까?")) {

					$.ajax({
						url : "${contextPath}/member/deleteMyBoard.do",
						type : "POST",
						traditional : true,
						data : {
							"boardArr" : boardArr
						},
						success : function(result) {
							if (result == 1) {
								swal({"icon" : "success" , "title" : "게시글이 삭제되었습니다."});
							} else {
								swal({"icon" : "error" , "title" : "게시글 삭제에 실패했습니다."});
							}
							
							selectList1()
						},
						error : function() {
							console.log("서버 통신 오류");
						}
					});

				}
			}

		}
		
		// 댓글 삭제 기능
		function deleteMyReply() {
			var checkedRow2 = $("input[name='checkedRow2']:checked").length;
			var replyArr = new Array();
			$("input[name='checkedRow2']:checked").each(function() {
				replyArr.push($(this).attr('id'));
			});

			if (checkedRow2 == 0) {
				alert("선택된 댓글이 없습니다.");
			} else {

				if (confirm("선택한 댓글을 삭제하시겠습니까?")) {

					$.ajax({
						url : "${contextPath}/member/deleteMyReply.do",
						type : "POST",
						traditional : true,
						data : {
							"replyArr" : replyArr
						},
						success : function(result) {
							if (result == 1) {
								swal({"icon" : "success" , "title" : "댓글 삭제되었습니다."});
							} else {
								swal({"icon" : "error" , "title" : "댓글 삭제에 실패했습니다."});
							}
							
							selectList2()
						},
						error : function() {
							console.log("서버 통신 오류");
						}
					});

				}
			}
		}
		
		
		$(document).on("click", "#chk_all", function(){

				if($("#chk_all").is(":checked")){
					$(".chk").prop("checked", true);
				}
				else{
					
					$(".chk").prop("checked", false);
				}
			
		})
		
		$(document).on("click", ".chk", function(){

				$("#chk_all").prop("checked", false);
		})
		
					
		$(document).on("click", "#chk_all2", function(){

				if($("#chk_all2").is(":checked")){
					$(".chk2").prop("checked", true);
				}
				else{
					$(".chk2").prop("checked", false);
				}
			
		})
		
		$(document).on("click", ".chk2", function(){
			$("#chk_all2").prop("checked", false);
		})
		
		

		
		
		
	</script>




</body>
</html>
