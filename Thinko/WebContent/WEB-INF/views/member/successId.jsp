<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 성공</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
body{
  background: rgba(232, 190, 240, 0.37);
  font-family: 'Montserrat', Arial;
  font-size: 1em;
}


.ribbon{
  background: rgba(200,200,200,.5);
  width: 50px;
  height: 70px;
  margin: 0 auto;
  position: relative;
  top: 19px;
  border: 1px solid rgba(255,255,255,.3);
  border-top: 2px solid rgba(255,255,255,.5);
  border-bottom: 0;  
  border-radius: 5px 5px 0 0;
  box-shadow: 0 0 3px rgba(0,0,0,.7); 
}
.ribbon:before{
  content:"";
  display: block;
  width: 15px;
  height: 15px;
  background: #4E535B;
  border: 4px solid #cfd0d1;
  margin: 18px auto;
  box-shadow: inset 0 0 5px #000, 0 0 2px #000, 0 1px 1px 1px #A7A8AB;
  border-radius: 100%;
}
.login{
  background: #F1F2F4;
  border-bottom: 2px solid #C5C5C8;
  border-radius: 5px;
  text-align: center;
  color: #36383C;
  text-shadow: 0 1px 0 #FFF;
  max-width: 300px;
  margin: 0 auto;
  padding: 15px 40px 20px 40px;
  box-shadow: 0 0 3px #000;
}
.login:before{
  content:"";
  display: block;
  width: 70px;
  height: 4px;
  background: #4E535B;
  border-radius: 5px;
  border-bottom: 1px solid #FFFFFF;
  border-top: 2px solid #CBCBCD;
  margin: 0 auto;
}

p{
  font-family:'Helvetica Neue';
  font-weight: 300;
  color: #7B808A;
  margin-top: 0;
  margin-bottom: 30px;
}
.input{
  text-align: center;
  background: #E5E7E9;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: inset 0 0 3px #65686E;
  border-bottom: 1px solid #FFF;
}
input{
  width: 260px;
  background: transparent;
  border: 0;
  line-height: 3.6em;
  box-sizing: border-box;
  color: #71747A;
  font-family:'Helvetica Neue';
  text-shadow: 0 1px 0 #FFF;
}
input:focus{
  outline: none;
}



::-webkit-input-placeholder {
  color: #71747A;
  font-family:'Helvetica Neue';
  text-shadow: 0 1px 0 #FFF;
}
button{
  margin-top: 20px;
  display: block;
  width: 100%;
  line-height: 2em;
  background: #814798;
  border-radius: 5px;
  border:0;
  border-top: 1px solid #7e4190;
  box-shadow: 0 0 0 1px #6920bd, 0 2px 2px #808389;
  color: #FFFFFF;
  font-size: 20px;
  text-shadow: 0 1px 2px #ad5fcc;
}
button:hover{
 background: linear-gradient(to bottom, rgb(161, 107, 198) 0%,rgb(123, 73, 146) 100%);  
}
button:active{
  box-shadow: inset 0 0 5px #000;
  background: linear-gradient(to bottom, rgba(57,175,154,1) 0%,rgba(107,198,186,1) 100%); 
}



hr{
  border-top: 1px solid rgba(0,0,0,.5);
  border-bottom: 1px solid (255,255,255,.5);
  border-left: 0;
  border-right: 0;
  margin-top: 30px;
}

.io{
  padding: 0;
  padding-bottom: 10px;
}
.press{
  background: #D73D32;
  height: 40px;
  margin-top: -7px;
  border-radius: 5px 5px 0 0;
  text-align: left;
  line-height: 40px;
  padding: 0 10px;
  color: #FFF;
  text-shadow: none;
}
.press span{
  float: right;
  font-family: Georgia;
}
.io:before{
  position: relative;
  top: 15px;
  border-top: 2px solid #E78B84;
  border-bottom: 1px solid #6C1E19;
}
.ior{
  position:relative;
  z-index: 1;
}
.io img{
  width: 150px;
  border-radius: 100%;
  border: 4px solid 
  margin-top: 10px;
  border: 4px solid #FFF;
  margin: 18px auto 0;
  box-shadow: 0 1px 1px 1px #A7A8AB;
}
.io h2{
  margin-top: 0;
}
.io p{
  font-size: 1.5em;
  margin-bottom: 5px;
}



</style>
</head>
<body>
	<%--
      로그인 실패 등의 서버로 부터 전달 받은 메세지를 경고창으로 출력하기
      
      1) 서버로 부터 전달받은 메세지가 있는지 검사
    --%>
	<c:if test="${!empty sessionScope.swalTitle }">
      <script>
         swal({
            title : "${swalTitle}",
            text : "${swalText}",
            icon : "${swalIcon}",
            button : "뒤로가기",
         });
      </script>

      <%-- 2) 한번 출력한 메세지를 Session에서 삭제 --%>
      <c:remove var="swalTitle" />
      <c:remove var="swalText" />
      <c:remove var="swalIcon" />

   </c:if>
   
       <div class="ribbon"></div>
    <div class="login">
      
        <img src="${contextPath}/resources/images/purplecheck.PNG"  width="150px" height="150px">
        <br>
        <br>

        
    <form accept-charset="UTF-8" role="form" method="POST" action="">
     <div class="input-group">
							<span><h3>회원가입 시 사용한 아이디는</h3> 
							<h2>${result}</h2> 
							<h3>입니다.</h3></span>
						</div>

        <button type="button" id="backTo" class="btn btn-default btn-block">로그인 화면으로 돌아가기</button>
        
        
        <br>

        <a href="${contextPath}/member2/searchIdForm.do">아이디찾기</a> | <a href="${contextPath}/member2/searchPwForm.do">비밀번호 찾기</a> | <a href="${contextPath}/member/joinForm.do">회원가입</a>
        
    </form>
    </div>
    
   
   


<script>
$("#backTo").on("click", function(){
	url = "${contextPath}/member2/memberLoginForm.do";
	location.href = url;
});
</script>

</body>
</html>