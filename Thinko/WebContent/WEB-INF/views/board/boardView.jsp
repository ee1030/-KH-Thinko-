<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>띵코</title>
<style>

body {
	background-color: white !important;
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

.mt-5, .my-5 {
    margin-top: 0px !important;
}

.pt-5, .py-5 {
    padding-top: 3rem !important;
    height: 900px;
}

.map_wrap {position:relative;width:100%;height:350px;}
.title {font-weight:bold;display:block;}
.hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
#centerAddr {display:block;margin-top:2px;font-weight: normal;}
.bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- Page Content -->
	
	<div class="container-fluid mt-5">
	<jsp:include page="boardSideMenu.jsp"></jsp:include>
		<div class="row my-5">
			<div class="container">
			<!-- Post Content Column -->
			<div class="col-lg-12">
				<div id="board-area">
					<!-- Title -->
					<h1 class="mt-4">[${board.boardCategoryName}] ${board.boardTitle}</h1>

					<!-- Author -->
					<p class="lead">
					<c:choose>
						<c:when test="${param.type == '10'}">
							익명 
						</c:when>
						<c:otherwise>
							작성자 : ${board.memberId}
						</c:otherwise>
					</c:choose>
						
						
						
						<c:if test="${!empty loginMember && board.memberId != loginMember.memberId}">
							<abbr title="신고하기"> 
								<i class="fas fa-exclamation-circle fa-2x float-right mx-2 titleIcon1" data-toggle="modal" data-target="#modal-container-1"></i>
							</abbr> 
							
							<abbr title="따봉"> 
								<i class="far fa-thumbs-up fa-2x float-right mx-2 titleIcon2"></i>
							</abbr>
						</c:if>


					</p>

					<hr>

					<!-- Date/Time -->
					<p>
						조회수 : ${board.readCount} | 댓글 : ${board.replyCount} | 좋아요 :
						${board.likeCount } | 작성일 :
						<fmt:formatDate value="${board.boardCreateDate}"
							pattern="yyyy년 MM월 dd일 HH:mm:ss" />
					</p>

					<hr>

					<!-- Post Content -->
					<div class="content-area">
						${board.boardContent}
					</div>
					
					<c:if test="${param.type == 50}">
						<h4>&lt;맛집 주소&gt;</h4>
						<div class="map_wrap">
						    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
						    <div class="hAddr">
						        <span class="title">지도중심기준 행정동 주소정보</span>
						        <span id="centerAddr"></span>
						    </div>
						</div>
	
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dd19bab7101200951c3127b359fc8c3f&libraries=services"></script>
						<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					    mapOption = {
					        center: new kakao.maps.LatLng(${lat}, ${lng}), // 지도의 중심좌표
					        level: 3 // 지도의 확대 레벨
					    };  

						// 지도를 생성합니다    
						var map = new kakao.maps.Map(mapContainer, mapOption); 
	
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder();
						
						var marker = null;
						
						// 처음 나오는 주소 마커에도 주소가 표시될 수 있도록합니다
						searchDetailAddrFromCoords(map.getCenter(), function(result, status) {
					        if (status === kakao.maps.services.Status.OK) {
					            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
					            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
					            
					            var content = '<div class="bAddr">' +
					                            '<span class="title">법정동 주소정보</span>' + 
					                            detailAddr + 
					                        '</div>';
	
								// 지도를 클릭한 위치에 표출할 마커입니다
								marker = new kakao.maps.Marker({ 
								    // 지도 중심좌표에 마커를 생성합니다 
								    position: map.getCenter() 
								}), infowindow = new kakao.maps.InfoWindow({zindex:1});
								
								marker.setMap(map);
								
								// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
					            infowindow.setContent(content);
					            infowindow.open(map, marker);
					        }   
					    });
	
						// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
						searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	
						// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
						kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
						    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
						        if (status === kakao.maps.services.Status.OK) {
						            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
						            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
						            
						            var content = '<div class="bAddr">' +
						                            '<span class="title">법정동 주소정보</span>' + 
						                            detailAddr + 
						                        '</div>';
	
						            // 마커를 클릭한 위치에 표시합니다 
						            marker.setPosition(mouseEvent.latLng);
						            marker.setMap(map);
	
						            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
						            infowindow.setContent(content);
						            infowindow.open(map, marker);
						        }   
						    });
						});
	
						// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
						kakao.maps.event.addListener(map, 'idle', function() {
						    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
						});
	
						function searchAddrFromCoords(coords, callback) {
						    // 좌표로 행정동 주소 정보를 요청합니다
						    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
						}
	
						function searchDetailAddrFromCoords(coords, callback) {
						    // 좌표로 법정동 상세 주소 정보를 요청합니다
						    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
						}
	
						// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
						function displayCenterInfo(result, status) {
						    if (status === kakao.maps.services.Status.OK) {
						        var infoDiv = document.getElementById('centerAddr');
	
						        for(var i = 0; i < result.length; i++) {
						            // 행정동의 region_type 값은 'H' 이므로
						            if (result[i].region_type === 'H') {
						                infoDiv.innerHTML = result[i].address_name;
						                break;
						            }
						        }
						    }    
						}  
						</script>
					</c:if>
					<hr>
					<c:choose>
						<c:when test="${!empty param.ad}">
							<c:url var="goToList" value="../admin/allBoardList.do">
								<c:param name="cp">${param.cp}</c:param>
							</c:url>
						</c:when>

						<c:when test="${!empty param.adr}">
							<c:url var="goToList" value="../admin/allReplyList.do">
								<c:param name="cp">${param.cp}</c:param>
								<c:param name="type">${param.type}</c:param>
							</c:url>
						</c:when>

						<c:when test="${!empty param.adrb}">
							<c:url var="goToList" value="../admin/reportedBoardList.do">
								<c:param name="cp">${param.cp}</c:param>
							</c:url>
						</c:when>

						<c:when test="${!empty param.adrr}">
							<c:url var="goToList" value="../admin/reportedReplyList.do">
								<c:param name="cp">${param.cp}</c:param>
							</c:url>
						</c:when>
						
						<c:when test="${!empty param.mb}">
							<c:url var="goToList" value="../member/myList.do">
							</c:url>
						</c:when>
						
						<c:when test="${!empty param.mf}">
							<c:url var="goToList" value="../member/myFavorite.do">
							</c:url>
						</c:when>

						<c:otherwise>
							<c:url var="goToList" value="../board2/list.do">
								<c:param name="cp">${param.cp}</c:param>
								<c:param name="type">${param.type}</c:param>
							</c:url>
						</c:otherwise>
					</c:choose>

					<a href="${goToList}" class="float-right mx-1 button1">목록으로</a>

					<c:if
						test="${(!empty loginMember && (board.memberId == loginMember.memberId)) || loginMember.memberGrade eq 'A'}">

						<a id="deleteBtn" class="float-right mx-1 button1">삭제</a>

						<%-- 게시글 수정 후 상세조회 페이지로 돌아오기 위한 url 조합 --%>
						<c:if test="${!empty param.sv || !empty param.sk }">
							<%-- 검색을 통해 들어온 상세 조회 페이지인 경우 --%>
							<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}" />
						</c:if>
						
						<a href="${contextPath}/board3/updateForm.do?cp=${param.cp}&no=${board.boardNo}&type=${param.type}${searchStr}" class="float-right mx-1 button1">수정</a>
					</c:if>
				</div>

				
				<!-- 댓글 -->
				<jsp:include page="reply.jsp"></jsp:include>

			</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<!-- /.row -->
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<%-- Modal창에 해당하는 html 코드는 페이지 마지막에 작성하는게 좋다 --%>
	<div class="modal fade" id="modal-container-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">게시글 신고</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-signin" method="POST" action="${contextPath}/board1/reportBoard.do">
						<input type="hidden" name="boardNo" value="${board.boardNo}">
						<input type="hidden" name="memberNo" value="${loginMember.memberNo}">

						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory1" value="10" required>
							 <label class="form-check-label" for="reportCategory1"> 욕설 및 비방 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory2" value="20">
							<label class="form-check-label" for="reportCategory2"> 혐오 및 불쾌감 조성 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory3" value="30">
							<label class="form-check-label" for="reportCategory3"> 사행성 조장 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory4" value="40">
							<label class="form-check-label" for="reportCategory4"> 성적인 콘텐츠 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory5" value="50">
							<label class="form-check-label" for="reportCategory5"> 권리 침해 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rc" id="reportCategory6" value="60">
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
		$(".titleIcon2").on("click", function(){
			location.href = "${contextPath}/board1/updateLike.do?no=" + ${board.boardNo} + "&mn=" + ${loginMember.memberNo};
		});
	</script>
	
		<script>
		$("#deleteBtn").on("click",function(){
			
			if(confirm("게시글을 삭제 하시겠습니까?")){
				location.href = "${contextPath}/board3/updateStatus.do?cp=${param.cp}&no=${board.boardNo}&type=${param.type}";
			}
			
		});
		</script>
	
</body>
</html>