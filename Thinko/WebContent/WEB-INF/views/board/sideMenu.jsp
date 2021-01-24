<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	a {
	display: block;
	}

	.list-group{
		width: 150px;
	}
	
	.list-group-item{
		height: 100px;
		font-size: 18px;
		line-height: 80px;
		font-weight: bold;
	}
	
	.list-group-item > a{
		color: rgb(50, 50, 50);
	}

	#side-menu{
		float:left;
		margin-left: 0px;
	}

</style>
<div id="side-menu" class=" side-menu col-sm-1">
	<ul class="list-group">
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/notice/noticeList.do">공지사항</a></li>
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/board2/list.do?type=10">자유게시판</a></li>
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/board2/list.do?type=30">질문게시판</a></li>
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/board2/list.do?type=40">스터디게시판</a></li>
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/board2/list.do?type=60">정보게시판</a></li>
		<li class="li list-group-item list-group-item-action"><a href="${contextPath}/board2/list.do?type=50">맛집게시판</a></li>
	</ul>
</div>


<script>

</script>