<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
 
    <style>
    	body {
		   background-color : white!important;
			}
			
      #submit-button{
        float: right;
      }

      #submit-cancle-button{
        float: left;
      }

      #boardTitle{
      	border:none;
      }
      
      .button1 {
		    width: 100px;
		    height: 40px;
		    border-radius: 5px;
		    border: none;
		    padding: 4px;
		    margin-left: 10px;
		    font-size: 18px;
		    background-color:#814798;
		    color: white;
			}
	
    </style>
  </head>

  <body>

    	<jsp:include page="../common/header.jsp"></jsp:include>
     	<div class="container-fluid mt-5">
      <jsp:include page="../board/boardSideMenu.jsp"></jsp:include>
	    <div class="container my-5">
      <div class="row my-5">
      <div class="col-md-2">
			</div>
      <div class="col-sm-9">
      <form action="<%=request.getContextPath()%>/notice/update.do?&no=${notice.noticeNo}" method="post" enctype="multipart/form-data"
        role="form" onsubmit="return boardValidate();">
  
        <div class="form-inline mb-2">
          <input type="text" class="form-control" id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle}" size="90" autocomplete="off">
        </div>

        <hr class="mb-4">


        <div class="container my-5" id="text-container">


          <textarea id="summernote" name="noticeContent">${notice.noticeContent}</textarea>


        </div>

        
        <hr class="mb-4">

        <div class="text-center">
          <button id=submit-cancle-button type="button" class="button1">취소</button>
          <button id=submit-button type="submit" class="button1">수정</button>
        </div>
      </form>
      </div>
      </div>
			</div>
    </div>
		


    <script>
    jQuery.noConflict(); 
    
    	$('#summernote').summernote({
      minHeight: 500,
      width: 830,
    	focus: true,
	    callbacks: {
	    	onImageUpload: function(files, editor, welEditable) {
	    		
	    	   for (var i = files.length - 1; i >= 0; i--) {
	    	        sendFile(files[i], this);
	    	   }
	    	 }
	    }

    	});

    	function sendFile(file, editor) {
    			data = new FormData();
    	 		data.append("file", file);
    	       $.ajax({
    	         data: data,
    	         type: "POST",
    	         url: "${contextPath}/notice/imageUpload.do",
    	         cache: false,
    	         contentType: false,
    	         processData: false,
    	         enctype: 'multipart/form-data',
    	         success: function(data) {
       					  $(editor).summernote('editor.insertImage', "${contextPath}/resources/uploadImages/"+data);
    	         }
    	       });
    	    }
    </script>

  </body>

  </html>