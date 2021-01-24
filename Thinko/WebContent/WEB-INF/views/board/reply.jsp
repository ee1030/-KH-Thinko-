<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
/*댓글*/

.rHidden {
	display : none;
}
.replyList {
	margin-bottom: 50px;
}

.replyWrite>table {
	width: 90%;
	margin-top: 100px;
}

#replyContentArea {
	width: 90%;
}

#replyContent{
	border : 1px solid gray;
}

#replyContentArea>textarea {
	resize: none;
	width: 100%;
}

#replyBtnArea {
	width: 100px;
	text-align: center;
}


.rWriter {
	display: inline-block;
	margin-right: 30px;
	vertical-align: top;
}

.rDate {
	display: inline-block;
	font-size: 0.5em;
	color: gray;
}

#replyListArea {
	list-style-type: none;
	background-color: #81479810;
}

#replyListArea * {
	margin-left: 6px;
	margin-right: 6px;
}

.rContent{

}

.rContent, .replyBtnArea {
	height: 100%;
	width: 100%;
}

.replyBtnArea {
	text-align: right;
}

.replyUpdateContent {
	resize: none;
	width: 100%;
}

.reply-row {
	border-bottom: 1px solid #ccc;
	padding: 15px 0;
}

.form-group {
	box-sizing: border-box;
	margin: 10px;
	position: relative;
	height : 60px;
}

.form-group>div:first-of-type {
	width: 85%;
	display: inline-block;
}

.form-group>div:last-of-type {
	width: 20%;
	display: inline-block;
	position: absolute;
	top: 1px;
	right: 2%;
	text-align: right;
}

.form-control {
	width: 100%;
}

.reportIconArea{
	heigth : 40px;
	text-align: right;
}

.dark-yellow:hover {
	cursor: pointer;
}

.dark-yellow {
	color: rgb(189, 170, 0);
}

</style>


<!-- 댓글 출력 부분 -->
<br>
<div class="replyList mt-5 pt-2">
	<ul id="replyListArea">

	</ul>
		
	<div id="reply-area">
		<!-- Comments Form -->
		<!-- <form id="reply-form" action="#" method="get"> -->
			<div class="form-group">
				<div>
					<textarea id="replyContent" class="form-control" rows="3" placeholder="댓글을 작성해주세요."></textarea>
				</div>
				<div>
					<button type="submit" id="addReply"
						class="btn btn-secondary reply-btn">등록</button>
				</div>
			</div>
		<!-- </form> -->
	</div>
</div>

<div class="modal fade" id="modal-container-2" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">댓글 신고</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-signin" method="POST" action="${contextPath}/reply/reportReply.do">
						<input type="hidden" name="replyNo">
						<input type="hidden" name="memberNo" value="${loginMember.memberNo}">

						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory1" value="110" required>
							 <label class="form-check-label" for="reportCategory1"> 욕설 및 비방 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory2" value="120">
							<label class="form-check-label" for="reportCategory2"> 혐오 및 불쾌감 조성 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory3" value="130">
							<label class="form-check-label" for="reportCategory3"> 사행성 조장 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory4" value="140">
							<label class="form-check-label" for="reportCategory4"> 성적인 콘텐츠 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory5" value="150">
							<label class="form-check-label" for="reportCategory5"> 권리 침해 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory6" value="160">
							<label class="form-check-label" for="reportCategory6"> 스팸 또는 오해의 소지가 있는 콘텐츠 </label>
						</div>
						<br>
						<button type="submit" class="btn btn-warning">신고하기</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

<script>
jQuery.noConflict(); 
	var loginMemberId = "${loginMember.memberId}";
	var loginMemberNo = "${loginMember.memberNo}";
	var parentBoardNo = ${board.boardNo};
	var loginMemberGrade = "${loginMember.memberGrade}"
	
	/* var rNo = $(".dark-yellow").parent().parent().parent().childen().childen("h6").val(); */
	
	// 페이지 로딩 완료 시 목록 호출
	$(function() {
		selectReplyList();
	});

	// 댓글 목록 조회 ajax.
	function selectReplyList() {

		$.ajax({
			url : "${contextPath}/reply/selectList.do",
			data : {"parentBoardNo" : parentBoardNo},
			type : "post",
			dataType : "JSON",
			success : function(rList) {

				
				$("#replyListArea").html("");
				
				$.each(rList, function(index, item) {
					
					var li = $("<li>").addClass("reply-row");
					var rNo = $("<p>").addClass("rHidden").text(item.replyNo);
					var mNo = $("<p>").addClass("rHidden").text(item.memberNo);
					var rWriter = $("<p>").addClass("rWriter").text(item.memberId);
					var rDate = $("<p>").addClass("rDate").text(item.replyCreateDate);

					var div = $("<div>");
					div.append(rNo).append(mNo).append(rWriter).append(rDate);
					
					var rContent = $("<p>").addClass("rContent").html(item.replyContent);

					li.append(div).append(rContent);
					
					// 현재 댓글의 작성자와 로그인한 멤버의 아이디가 같을 때 버튼 추가
					if (item.memberNo == loginMemberNo || loginMemberGrade == "A") {

						// 댓글, 수정, 삭제 버튼 영역
						var replyBtnArea = $("<div>").addClass("replyBtnArea");

						// ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
						var showUpdate = $("<button>").addClass("btn btn-secondary btn-sm ml-1").text("수정")
								.attr("onclick","showUpdateReply(" + item.replyNo + ", this)");
						
						var deleteReply = $("<button>").addClass("btn btn-secondary btn-sm ml-1").text("삭제")
								.attr("onclick", "deleteReply(" + item.replyNo + ")");

						replyBtnArea.append(showUpdate).append(deleteReply);

						li.append(replyBtnArea);
						
					// 로그인이 되어있으면서, 아이디가 다를때 신고버튼 추가하기
					}else if(loginMemberNo != "" && item.memberNo != loginMemberNo){
						// 신고버튼 영역
						var reportIconArea = $("<div>").addClass("reportIconArea");
						// 신고하기 mouseover시 설명
						var abbr = $("<abbr>").attr("title","신고하기");
						// 신고하기 버튼 생성 클릭스 모달창 활성화
						var reportBtn = $("<i>").addClass("fas fa-exclamation-circle fa-1x mx-2 dark-yellow")
						.attr("data-toggle","modal").attr("data-target","#modal-container-2").attr("onclick", "reportReply("+item.replyNo+")");
						
						abbr.append(reportBtn);

						reportIconArea.append(abbr);
						
						li.append(reportIconArea);
					}

					$("#replyListArea").append(li);
				});
			},
			error : function() {
				console.log("댓글 목록 조회 실패");
			}
		});
	}

	// 댓글 등록 (ajax)
	$("#addReply").on("click", function() {

		// 댓글 내용을 변수에 저장
		var replyContent = $("#replyContent").val().trim();

		// 로그인이 안되있는 경우
		if (loginMemberId == "") {
			swal({"title" : "로그인 후 이용해주세요."});
			
		} else {
			
			if (replyContent.length == 0) {
				swal({"title" : "댓글 작성 후 클릭해주세요."});
				
			} else {
				// 회원 번호
				var replyWriter = "${loginMember.memberNo}";

				$.ajax({
					url : "${contextPath}/reply/insertReply.do",
					data : {
							"replyWriter" : replyWriter,
							"replyContent" : replyContent,
							"parentBoardNo" : parentBoardNo
							},
					type : "post",
					success : function(result) {

						if (result > 0) {
							// 작성한 내용을 input태그에서 삭제
							$("#replyContent").val("");

							// 댓글 목록을 다시 조회  -> 새로 삽입한 댓글도 조회하여 화면에 출력
							selectReplyList();
						}
					},
					error : function() {
						console.log("댓글 등록 실패");
					}
				});
			}
		}
	});

	// 댓글 수정 폼 출력 함수

	var beforeReplyRow;

	// 댓글 수정 폼 출력 함수
	function showUpdateReply(replyNo, el) {

		// 이미 열려있는 댓글 수정 창이 있을 경우 닫는 구문
		if ($(".replyUpdateContent").length > 0) {
			$(".replyUpdateContent").eq(0).parent().html(beforeReplyRow);
		}

		// 댓글 수정화면 출력 전 요소를 저장
		beforeReplyRow = $(el).parent().parent().html();

		// 작성되어있던 내용 변수에 저장
		var beforeContent = $(el).parent().prev().html();

		// 이전 댓글 내용의 크로스사이트 스크립트 처리 해제, 개행문자 변경
		// -> 자바스크립트에는 replaceAll() 메소드가 없으므로 정규 표현식을 이용하여 변경
		beforeContent = beforeContent.replace(/&amp;/g, "&");
		beforeContent = beforeContent.replace(/&lt;/g, "<");
		beforeContent = beforeContent.replace(/&gt;/g, ">");
		beforeContent = beforeContent.replace(/&quot;/g, "\"");
		// 개행문자 변경
		beforeContent = beforeContent.replace(/<br>/g, "\n");

		//기존 댓글 영역을 삭제하고 textarea를 새로 만들어 추가
		$(el).parent().prev().remove();
		var textarea = $("<textarea>").addClass("replyUpdateContent form-control").attr(
				"rows", "3").attr("placeholder", "댓글을 작성해주세요.").css("width", "98%").val(beforeContent);
		$(el).parent().before(textarea);

		// 수정 버튼
		var updateReply = $("<button>").addClass("btn btn-secondary reply-btn")
				.text("댓글 수정").attr("onclick",
						"updateReply(" + replyNo + ", this)");

		// 취소 버튼
		var cancelBtn = $("<button>").addClass("btn btn-secondary reply-btn")
				.text("취소").attr("onclick", "updateCancel(this)");

		var replyBtnArea = $(el).parent();

		$(replyBtnArea).empty();
		$(replyBtnArea).append(updateReply);
		$(replyBtnArea).append(cancelBtn);
	}

	// 댓글 수정 함수
	function updateReply(replyNo, el) {

		// 수정된 댓글 내용
		var replyContent = $(el).parent().prev().val();

		$.ajax({
			url : "${contextPath}/reply/updateReply.do",
			type : "post",
			data : {
				"replyNo" : replyNo,
				"replyContent" : replyContent
			},
			success : function(result) {
				if (result > 0) {
					selectReplyList(parentBoardNo);
				}

			},
			error : function() {
				console.log("댓글 수정 실패");
			}
		});
	}

	// 댓글 수정 취소 시 원래대로 돌아가는 함수
	function updateCancel(el) {
		$(el).parent().parent().html(beforeReplyRow);
	}

	//댓글 삭제 함수
	function deleteReply(replyNo) {
		// 클릭시에 컨펌창 활성화
		if (confirm("정말로 삭제하시겠습니까?")) {
			var url = "${contextPath}/reply/deleteReply.do";

			$.ajax({
				url : url,
				data : {
					"replyNo" : replyNo
				},
				success : function(result) {
					if (result > 0) {
						selectReplyList(parentBoardNo);
					}
				},
				error : function() {
					console.log("ajax 통신 실패");
				}
			});
		}
	}
	
	// 댓글 신고 함수
	function reportReply(replyNo){
		console.log(replyNo);
		// 모달창에 replyNo를 전달하기
		// name 속성을 가진 태그에 벨류 삽입
		$("input[name='replyNo']").val(replyNo);
	}
	
</script>
