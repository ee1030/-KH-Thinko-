<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
 
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
				font-size: 18px;
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
 
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}

    </style>
  </head>

  <body>

    <jsp:include page="../common/header.jsp"></jsp:include>
    	<div class="container-fluid mt-5">
      <jsp:include page="boardSideMenu.jsp"></jsp:include>
  	  <div class="container my-5">
      <div class="row my-5">
      <div class="col-md-2">
			</div>
      <div class="container">
      <%-- <form action="<%=request.getContextPath()%>/board3/insert.do?type=${type}" method="post" enctype="multipart/form-data"
        role="form" onsubmit="return boardValidate();"> --%>
      <form action="<%=request.getContextPath()%>/board3/insert.do?type=${type}" method="post" role="form" onsubmit="return boardValidate();">
        <c:set var = "type" value = "${type}"/>
	      <c:choose>
	         <c:when test = "${type == 10}">
	         	  <h5> 자 유 게 시 판 </h5><hr><br>
			        <div class="mb-2">
			        	&nbsp;&nbsp;
			          <select class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
			            <option value="10">사담</option>
			            <option value="20">공부</option>
			            <option value="30">취미</option>
			            <option value="40">고민</option>
			            <option value="50">연애</option>
			            <option value="60">기타</option>
			          </select>
			        </div>
	         </c:when>
	         <c:when test = "${type == 30}">
	         	  <h5> 질 문 게 시 판 </h5><hr><br>
	     		     <div class="mb-2">			        	
	     		     	&nbsp;&nbsp;
			          <select class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
			            <option value="300">백엔드</option>
			            <option value="310">프론트엔드</option>
			            <option value="320">DB</option>
			            <option value="330">기타</option>
			          </select>
			        </div>
	         </c:when>
	         <c:when test = "${type == 40}">
	     		      <h5> 스 터 디 게 시 판 </h5><hr><br>
	     		     <div class="mb-2">
	     		     	&nbsp;&nbsp;
			          <select class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
			            <option value="400">스터디</option>
			            <option value="410">공모전</option>
			            <option value="420">동아리</option>
			          </select>
			        </div>
	         </c:when>
	         <c:when test = "${type == 60}">
	     		      <h5> 정 보 게 시 판 </h5><hr><br>
	     		     <div class="mb-2">
	     		     	&nbsp;&nbsp;
			          <select class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
			            <option value="600">채용공고</option>
			            <option value="610">자격증</option>
			            <option value="620">면접후기</option>
			            <option value="630">취뽀후기</option>
			          </select>
			        </div>
	         </c:when>
	         <c:when test = "${param.type == 50}">
	     		      <h5> 맛 집 게 시 판 </h5><hr><br>
	     		    <div class="mb-2">
	     		     	&nbsp;&nbsp;
			          <select class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
			            <option value="500">종로</option>
			            <option value="510">강남</option>
			            <option value="520">당산</option>
			          </select>
			        </div>
	         </c:when>
	      </c:choose>
        <div class="form-inline mb-2">
          <input type="text" class="form-control" id="boardTitle" name="boardTitle" 
          size="90" placeholder="제목을 입력해주세요." autocomplete="off" maxlength="100">
        </div>

        <hr class="mb-4">


        <div class="container my-5" id="text-container">


          <textarea id="summernote" name="boardContent"></textarea>


        </div>
		
		<c:if test = "${param.type == 50}">
	        <input type="hidden" id="lat" name="lat">
			<input type="hidden" id="lng" name="lng">
	        <div class="map_wrap">
		    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
			    <div class="hAddr">
			        <span class="title">지도중심기준 행정동 주소정보</span>
			        <span id="centerAddr"></span>
			    </div>
			</div>
		</c:if>
			
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dd19bab7101200951c3127b359fc8c3f&libraries=services"></script>
			<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(37.567923304617736, 126.98306788882377), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };
	
				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();
	
				// 지도를 클릭한 위치에 표출할 마커입니다
				var marker = new kakao.maps.Marker({ 
				    // 지도 중심좌표에 마커를 생성합니다 
				    position: map.getCenter() 
				}), infowindow = new kakao.maps.InfoWindow({zindex:1});
				
				// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
				searchAddrFromCoords(map.getCenter(), displayCenterInfo);
				
				// 지도에 마커를 표시합니다
				marker.setMap(map);
	
				// 지도에 클릭 이벤트를 등록합니다
				// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
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
				            
				            // input 태그에 위도와 경도를 표시합니다
				            $("#lat").val(mouseEvent.latLng.getLat());
							$("#lng").val(mouseEvent.latLng.getLng());
							
				        }   
				    });
				    
				    // 클릭한 위도, 경도 정보를 가져옵니다 
				    //var latlng = mouseEvent.latLng; 
				    
				    // 마커 위치를 클릭한 위치로 옮깁니다
				    //marker.setPosition(latlng);
				    
						    
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
        
        <hr class="mb-4">

        <div class="text-center">
          <button id=submit-cancle-button type="button" class="button1" onclick="javascript:history.back();">취소</button>
          <button id=submit-button type="submit" class="button1">등록</button>
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
      maxWidth: 1050,
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
    	         url: "${contextPath}/board3/imageUpload.do",
    	         cache: false,
    	         contentType: false,
    	         processData: false,
    	         enctype: 'multipart/form-data',
    	         success: function(data) {
       					  $(editor).summernote('editor.insertImage', "${contextPath}/resources/uploadImages/"+data);
    	         }
    	       });
    	    }
    	
		function boardValidate() {
			if ($("#boardTitle").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#boardTitle").focus();
				return false;
			}
			
			if ($("#summernote").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#summernote").focus();
				return false;
			}

		}

    </script>

  </body>

  </html>