<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>프로젝트 띵코 mainPage</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<style>
div {
	/* border: 1px solid;*/
	
}

.public, .lunch {
	height: 240px;
}

.h300 {
	height: 300px;
}

.board {
	margin-right: 10px;
	margin-top: 20px;
}

.board a {
	color: black;
}

.board a:hover {
	font-size: 1.2em;
}

#public>h5>a>i:hover {
}

#lunch>h5>a>i:hover {
}

#free>h5>a>i:hover {
}

#question>h5>a>i:hover {
}

#study>h5>a>i:hover {
}

#information>h5>a>i:hover {
	/* text-shadow: 1px 1px 10px green, 1px 1px 10px yellowgreen; */
	/* transition-duration: 200ms; */
}

.board>div>div:hover {
	cursor: pointer;
}

.board>div>div {
	height: 38px;
}

.board>div>div>a:not(:first-of-type) {
	float: right;
}

.public>h5, .lunch>h5 {
	border-bottom: 2px solid #814798;
}

.h300>h5 {
	border-bottom: 2px solid #7777;
}

.public>div>div, .lunch>div>div {
	border-bottom: 1px solid rgba(120, 40, 210, 0.1);
}

.h300>div>div {
	border-bottom: 1px solid #2222;
}

.board>div>div>a:not(:first-of-type) {
	font-size: 0.7em;
}

.row:last-of-type {
	margin-bottom: 100px;
}

.dropDown select {
	width: 90%;
	height: 45px;
}

.side>div {
	height: 300px;
	width: 100%;
	position: relative;
}

#sidebar1 {
	position: absolute;
	top: 20px;
	left: 10%;
	width: 160px;
}

#sidebar2 {
	position: absolute;
	height: 190px;
	top: 20px;
	left: 15%;
	width: 130px;
}

#sidebar2:hover {
	cursor : pointer;
}

#sidebar2>div {
	width: 100%;
	height: 100%;
	box-sizing: border-box;
	border: 1px solid #7777;
	border-radius: 5%;
	margin: auto;
}

#sidebar2>div {
	text-align: center;
	line-height: 4em;
}

#sidebar2>div>a {
	font-size : 1.1em;
	color: black;
}


.windowcon {
	height: 220px;
	margin-top: 20px;
	text-align: center;
	overflow: hidden;
	position: relative;
}

.board>div span {
	display: none;
}

.box-roulette {
	position: relative;
	margin: 50px auto;
	width: 500px;
	height: 500px;
	border: 10px solid #ccc;
	border-radius: 50%;
	background: #ccc;
	overflow: hidden;
}

.box-roulette .markers {
	position: absolute;
	left: 50%;
	top: 0;
	margin-left: -25px;
	width: 0;
	height: 0;
	border: 25px solid #fff;
	border-left-color: transparent;
	border-right-color: transparent;
	border-bottom-color: transparent;
	z-index: 9999;
}

.box-roulette .roulette {
	position: relative;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.box-roulette .item {
	position: absolute;
	top: 0;
	width: 0;
	height: 0;
	border: 0 solid transparent;
	transform-origin: 0 100%;
}

.box-roulette .label {
	position: absolute;
	left: 0;
	top: 0;
	color: #fff;
	white-space: nowrap;
	transform-origin: 0 0;
}

.box-roulette .label .text {
	display: inline-block;
	font-size: 20px;
	font-weight: bold;
	line-height: 1;
	vertical-align: middle;
}

#btn-start {
	display: block;
	position: absolute;
	left: 50%;
	top: 50%;
	margin: -50px 0 0 -50px;
	width: 100px;
	height: 100px;
	font-weight: bold;
	background: #fff;
	border-radius: 50px;
	z-index: 9999;
	cursor: pointer;
}

/* 베너 슬라이드쇼 */
.slideshow-container {
  height: 220px;
  width: 95%;
  position: relative;
  margin: auto;
}

.mySlides{
	height:100%;
	width:100%;
}

.slideshow-container img {
	height:100%;
	width:100%;
}

.text {
  color: #000000;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* number text (1/5) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 4s;
  animation-name: fade;
  animation-duration: 4s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}


</style>

</head>
<body>
	<!-- <body onload="showImage()"> -->
	<!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
	<jsp:include page="WEB-INF/views/common/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="row">
					<div class="side side-select col-md-12 dropDown">
						<div>
							<select name="category" id="sidebar1">
								<option value="70">게시판 선택</option>
								<option value="0">공지사항</option>
								<option value="10">자유익명게시판</option>
								<option value="30">질문게시판</option>
								<option value="40">스터디게시판</option>
								<option value="50">맛집 게시판</option>
								<option value="60">정보게시판</option>
							</select>
						</div>
						<div>
							<div id="sidebar2" data-toggle="modal" data-target="#modal-random">
								<div>
									<img src="${contextPath}/resources/images/lunch.png">
									<a>랜덤 메뉴</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-8">
						<div id="public" class="board public">
							<h5>
								<a href="${contextPath}/board2/list.do?type=20"><i
									class="fas fa-star fa-1x mx-1"> 인기글</i></a>
							</h5>
							<div></div>
						</div>
					</div>
					<div class="col-md-4">
						<div id="lunch" class="board lunch">
							<h5>
								<a href="${contextPath}/board2/list.do?type=50"><i
									class="fas fa-hamburger fa-1x mx-1"> 맛집 게시판</i></a>
							</h5>
							<div></div>
						</div>
					</div>
				</div>
				<!-- 배너 부분 -->
				<div class="slideshow-container">
					<div class="mySlides fade">
						<div class="numbertext">1 / 5</div>
						<img src="${contextPath}/resources/images/main-image1.jpg" style="width: 100%">
						<div class="text"></div>
					</div>
					<div class="mySlides fade">
						<div class="numbertext">2 / 5</div>
						<img src="${contextPath}/resources/images/main-image2.jpg" style="width: 100%">
						<div class="text"></div>
					</div>
					<div class="mySlides fade">
						<div class="numbertext">3 / 5</div>
						<img src="${contextPath}/resources/images/main-image3.jpg" style="width: 100%">
						<div class="text"></div>
					</div>
					<div class="mySlides fade">
						<div class="numbertext">4 / 5</div>
						<img src="${contextPath}/resources/images/main-image4.jpg" style="width: 100%">
						<div class="text"></div>
					</div>
					<div class="mySlides fade">
						<div class="numbertext">5 / 5</div>
						<img src="${contextPath}/resources/images/main-image5.jpg" style="width: 100%">
						<div class="text"></div>
					</div>
				</div>

				<div style="text-align: center">
					<span class="dot"></span>
					<span class="dot"></span>
					<span class="dot"></span>
					<span class="dot"></span>
					<span class="dot"></span>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div id="free" class="board h300">
							<h5>
								<a href="${contextPath}/board2/list.do?type=10"><i
									class="fas fa-user-shield fa-1x mx-1"> 자유 익명 게시판</i></a>
							</h5>
							<div></div>
						</div>
					</div>
					<div class="col-md-6">
						<div id="question" class="board h300">
							<h5>
								<a href="${contextPath}/board2/list.do?type=30"><i
									class="fas fa-laptop-code fa-1x mx-1"> 질문 게시판</i></a>
							</h5>
							<div></div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div id="study" class="board h300">
							<h5>
								<a href="${contextPath}/board2/list.do?type=40"><i
									class="fas fa-pencil-alt fa-1x mx-1"> 스터디 게시판</i></a>
							</h5>
							<div></div>
						</div>
					</div>
					<div class="col-md-6">
						<div id="information" class="board h300">
							<h5>
								<a href="${contextPath}/board2/list.do?type=60"><i
									class="fas fa-book fa-1x mx-1"> 정보 게시판</i></a>
							</h5>
							<div></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

	<jsp:include page="WEB-INF/views/common/footer.jsp"></jsp:include>

	<div class="modal fade" id="modal-random" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">랜덤 메뉴</h5>
				</div>
				<div id="box-roulette" class="box-roulette">
				<div class="markers"></div>
					<button type="button" id="btn-start">
						돌려 돌려<br>돌림판
					</button>
					<div class="roulette" id="roulette"></div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<script>
// 사이드바 스크롤
$(document).ready(function() {

	// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
	var floatPosition = parseInt($("#sidebar1").css('top'));
	// 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );

	$(window).scroll(function() {
		// 현재 스크롤 위치를 가져온다.
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + floatPosition + "px";

		/* 애니메이션 없이 바로 따라감
		 $("#sidebar1").css('top', newPosition);
		 */

		$("#sidebar1").stop().animate({
			"top" : newPosition
		}, 400);

	}).scroll();
});
// 사이드바 스크롤2
$(document).ready(function() {
	var floatPosition = parseInt($("#sidebar2").css('top'));
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + floatPosition + "px";
		$("#sidebar2").stop().animate({
			"top" : newPosition
		}, 400);
	}).scroll();
});


// 인기 게시판 목록 조회
$(function(){
	$.ajax({
		url : "${contextPath}/main/list.do",
		type : "POST",
		dataType: "JSON",
		success : function(map) {
			
			$("#public>div").empty(); 
			$("#lunch>div").empty(); 
			$("#free>div").empty(); 
			$("#question>div").empty(); 
			$("#study>div").empty(); 
			$("#information>div").empty(); 
			
			var bList1 = map.bList1;
			var bList2 = map.bList2;
			var bList3 = map.bList3;
			var bList4 = map.bList4;
			var bList5 = map.bList5;
			var bList6 = map.bList6;
			
			
			// 인기 게시판 리스트
			$.each(bList2, function(index, item){
				
					var title = item.boardTitle;
					if(title.length > 25){
						title = title.substring(0,25) + "...";
					}
					
					var div = $("<div>");
					var span = $("<span>").text(item.boardNo);
					var a1 = $("<a>").text(title);
					var a2 = $("<a>");
					var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
					a2.append(i1);
					var a3 = $("<a>");
					var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
					a3.append(i2);
					div.append(span).append(a1).append(a2).append(a3);
					$("#public > div").append(div);
				
			});
			// 자유 익명 게시판 리스트
			$.each(bList1, function(index, item){
				
				var title = item.boardTitle;
				if(title.length > 25){
					title = title.substring(0,25) + "...";
				}
				
				var div = $("<div>");
				var span = $("<span>").text(item.boardNo);
				var a1 = $("<a>").text(item.boardTitle);
				var a2 = $("<a>");
				var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
				a2.append(i1);
				var a3 = $("<a>");
				var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
				a3.append(i2);
				div.append(span).append(a1).append(a2).append(a3);
				$("#free > div").append(div);
				
			});
			// 질문 게시판 리스트
			$.each(bList3, function(index, item){
				
				var title = item.boardTitle;
				if(title.length > 25){
					title = title.substring(0,25) + "...";
				}
				
				var div = $("<div>");
				var span = $("<span>").text(item.boardNo);
				var a1 = $("<a>").text(item.boardTitle);
				var a2 = $("<a>");
				var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
				a2.append(i1);
				var a3 = $("<a>");
				var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
				a3.append(i2);
				div.append(span).append(a1).append(a2).append(a3);
				$("#question > div").append(div);
				
			});
			// 스터디 게시판 리스트
			$.each(bList4, function(index, item){
				
				var title = item.boardTitle;
				if(title.length > 25){
					title = title.substring(0,25) + "...";
				}
				
				var div = $("<div>");
				var span = $("<span>").text(item.boardNo);
				var a1 = $("<a>").text(item.boardTitle);
				var a2 = $("<a>");
				var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
				a2.append(i1);
				var a3 = $("<a>");
				var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
				a3.append(i2);
				div.append(span).append(a1).append(a2).append(a3);
				$("#study > div").append(div);
				
			});
			// 맛집 게시판 리스트
			$.each(bList5, function(index, item){
				
					var title = item.boardTitle;
					if(title.length > 25){
						title = title.substring(0,25) + "...";
					}
					
					var div = $("<div>");
					var span = $("<span>").text(item.boardNo);
					var a1 = $("<a>").text(item.boardTitle);
					var a2 = $("<a>");
					var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
					a2.append(i1);
					var a3 = $("<a>");
					var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
					a3.append(i2);
					div.append(span).append(a1).append(a2).append(a3);
					$("#lunch > div").append(div);
			});
			// 정보게시판 리스트
			$.each(bList6, function(index, item){
				
				var title = item.boardTitle;
				if(title.length > 25){
					title = title.substring(0,25) + "...";
				}
				
				var div = $("<div>");
				var span = $("<span>").text(item.boardNo);
				var a1 = $("<a>").text(item.boardTitle);
				var a2 = $("<a>");
				var i1 = $("<i class='far fa-heart fa-1x mx-1'>").text(item.likeCount);
				a2.append(i1);
				var a3 = $("<a>");
				var i2 = $("<i class='far fa-eye fa-1x mx-1'>").text(item.readCount);
				a3.append(i2);
				div.append(span).append(a1).append(a2).append(a3);
				$("#information > div").append(div);
				
			});
		}
	});
			
});
// 인기글 게시판 상세조회로 이동
$(document).on("click", "#public>div>div", function(){

	var boardNo = $(this).children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=20";

	location.href = url;
});
// 맛집 게시판 상세조회로 이동
$(document).on("click", "#lunch>div>div", function(){

	var boardNo = $(this).children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=50&cp=1";

	location.href = url;
});
// 자유 익명 게시판 상세조회로 이동
$(document).on("click", "#free>div>div", function(){

	var boardNo = $(this).children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=10&cp=1";

	location.href = url;
});
// 질문 게시판 상세조회로 이동
$(document).on("click", "#question>div>div", function(){

	var boardNo = $(this).children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=30&cp=1";

	location.href = url;
});
// 스터디 게시판 상세조회로 이동
$(document).on("click", "#study>div>div", function(){

	var boardNo = $(this).parent().children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=40&cp=1";

	location.href = url;
});
// 정보 게시판 상세조회로 이동
$(document).on("click", "#information>div>div", function(){

	var boardNo = $(this).children().eq(0).text();

	var url = "${contextPath}/board1/boardView.do?no="
			+ boardNo + "&type=60&cp=1";

	location.href = url;
});
	
	
// 사이드메뉴 셀렉트 변경 이벤트
$("#sidebar1").on("change", function(){
	var type = $(this).val();
	
	if( type == 0){
		location.href = "${contextPath}/notice/noticeList.do";
	}else if(type == 70){
		location.href = "${contextPath}";
	}else{
		location.href = "${contextPath}/board2/list.do?type=" + type;
		
	}
	
	
});

// 베너 자동 슬라이드 함수
var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
       slides[i].style.display = "none";  
    }
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}    
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";  
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 4000); // Change image every 2 seconds
}


// 모달창 랜덤메뉴
(function($) {
	  $.fn.extend({

	    roulette: function(options) {

	      var defaults = {
	        angle: 0,
	        angleOffset: -45,
	        speed: 5000,
	        easing: "easeInOutElastic",
	      };

	      var opt = $.extend(defaults, options);

	      return this.each(function() {
	        var o = opt;

	        var data = [
						{
	            color: '#3f297e',
	            text: '김치찌개'
	          },
	          {
	            color: '#1d61ac',
	            text: '파스타'
	          },
	          {
	            color: '#169ed8',
	            text: '김밥'
	          },
	          {
	            color: '#209b6c',
	            text: '라면'
	          },
	          {
	            color: '#60b236',
	            text: '김치볶음밥'
	          },
	          {
	            color: '#efe61f',
	            text: '삽겹살'
	          },
	          {
	            color: '#f7a416',
	            text: '초밥'
	          },
	          {
	            color: '#e6471d',
	            text: '자장면'
	          },
	          {
	            color: '#dc0936',
	            text: '만두'
	          },
	          {
	            color: '#e5177b',
	            text: '햄버거'
	          },
	          {
	            color: '#be107f',
	            text: '피자'
	          },
	          {
	            color: '#881f7e',
	            text: '마라탕'
	          }
	        ];

	        var $wrap = $(this);
	        var $btnStart = $wrap.find("#btn-start");
	        var $roulette = $wrap.find(".roulette");
	        var wrapW = $wrap.width();
	        var angle = o.angle;
	        var angleOffset = o.angleOffset;
	        var speed = o.speed;
	        var esing = o.easing;
	        var itemSize = data.length;
	        var itemSelector = "item";
	        var labelSelector = "label";
	        var d = 360 / itemSize;
	        var borderTopWidth = wrapW;
	        var borderRightWidth = tanDeg(d);

	        for (i = 1; i <= itemSize; i += 1) {
	          var idx = i - 1;
	          var rt = i * d + angleOffset;
	          var itemHTML = $('<div class="' + itemSelector + '">');
	          var labelHTML = '';
	              labelHTML += '<p class="' + labelSelector + '">';
	              labelHTML += '	<span class="text">' + data[idx].text + '<\/span>';
	              labelHTML += '<\/p>';

	          $roulette.append(itemHTML);
	          $roulette.children("." + itemSelector).eq(idx).append(labelHTML);
	          $roulette.children("." + itemSelector).eq(idx).css({
	            "left": wrapW / 2,
	            "top": -wrapW / 2,
	            "border-top-width": borderTopWidth,
	            "border-right-width": borderRightWidth,
	            "border-top-color": data[idx].color,
	            "transform": "rotate(" + rt + "deg)"
	          });

	          var textH = parseInt(((2 * Math.PI * wrapW) / d) * .5);

	          $roulette.children("." + itemSelector).eq(idx).children("." + labelSelector).css({
	            "height": textH + 'px',
	            "line-height": textH + 'px',
	            "transform": 'translateX(' + (textH * 1.3) + 'px) translateY(' + (wrapW * -.3) + 'px) rotateZ(' + (90 + d * .5) + 'deg)'
	          });

	        }

	        function tanDeg(deg) {
	          var rad = deg * Math.PI / 180;
	          return wrapW / (1 / Math.tan(rad));
	        }


	        $btnStart.on("click", function() {
	          rotation();
	        });

	        function rotation() {

	          var completeA = 360 * r(5, 10) + r(0, 360);

	          $roulette.rotate({
	            angle: angle,
	            animateTo: completeA,
	            center: ["50%", "50%"],
	            easing: $.easing.esing,
	            callback: function() {
	              var currentA = $(this).getRotateAngle();

	              console.log(currentA);

	            },
	            duration: speed
	          });
	        }

	        function r(min, max) {
	          return Math.floor(Math.random() * (max - min + 1)) + min;
	        }

	      });
	    }
	  });
	})(jQuery);

	$(function() {

	  $('.box-roulette').roulette();

	});

	$(function () {
	    var angle = 0;
	    $("#btn-start").click(function(){
	        angle += 90;
	        $(".roulette").stop().css({'transform': 'rotate('+angle+'deg)'},1000);
	    });

	    $("#btn-start").click(function(){
	        angle -= 90;
	        $(".roulette").stop().css({'transform': 'rotate('+angle+'deg)'},1000);
	    });
	});
	
	
</script>
</body>
</html>