<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap" rel="stylesheet">

<style>

   *{
      font-family: 'Noto Sans KR', sans-serif;
      color: rgb(37, 37, 37);
   }
   
   .m-0>ul{
      list-style-type: none;
      
   }

   .m-0 li{
      float: left;
      margin-left: 30px;
      font-size: 16px;
      
   }

   .footer{
      background-color: #7777;
   }

   #footer-text1{
      font-weight: bolder;
   }
   
   .m-0>ul>li:nth-of-type(2n){
      color: #9999;
   }

   .m-0 li>a{
      color: rgb(37, 37, 37);
      text-decoration: none;
   }

</style>

</head>
<body>
   <div class="py-4 footer">
     <div class="container">
       <div id="footer-text1" class="m-0 text-center text-black">
         <ul>
            <li><a href="#">이용약관</a></li>
            <li>|</li>
            <li><a href="#">개인정보 취급방침</a></li>
            <li>|</li>
            <li><a href="#">도움말</a></li>
         </ul>
      </div>
      <br>
       <div id="footer-text2" class="m-0 text-center text-balck">
         <ul>
            <li><a href="#">(주) 종로의 객체들 </a></li>
            <li>|</li>
            <li><a href="#">대표이사 : 정연정</a></li>
            <li>|</li>
            <li><a href="#">문의전화 : 7777-8888</a></li>
         </ul>
         <br>
      </div>
     </div>
   </div>
</body>
</html>