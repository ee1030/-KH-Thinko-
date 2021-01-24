        var valObj = { // 유효성 검사 여부를 저장할 객체
            id: false,
            pwd1: false,
            name: false,
            email: false,
						checkEmail: false,
            phone: false
        }
        // 객체에 작성한 key값은 의도적으로
        // input 태그의 id와 같은 값을 작성함. 

        var y = "<i class='far fa-check-circle check-circle-blue'></i>";

        var n = "<i class='far fa-times-circle check-circle-red'></i>";

        $("#id").on("input", function () {

            var regExp = /^[a-zA-Z\d]{6,10}$/;

            if ($("#id").val().length == 0) {
                $("#id-check-circle").empty();
                $("#idDupResult").empty();
            } else {
                if (regExp.test($("#id").val())) {

                    $.ajax({
                        url : "idDupCheck.do",
                        data : { "inputId" : $("#id").val() },
                        type : "post" , 
                        success : function(result){
                            if(result == 0){ // "0" == 0  문자와 숫자 동등비교
                                $("#idDupResult").text("사용 가능한 아이디 입니다.").css("color", "rgb(0, 199, 0)");
                                $("#id-check-circle").empty();
                                $("#id-check-circle").append(y);
                                valObj.id = true;
                            }else{
                                $("#idDupResult").text("이미 사용 중인 아이디 입니다.").css("color", "red");
                                $("#id-check-circle").empty();
                                $("#id-check-circle").append(n);
                                valObj.id = false;
                            }
                        },
                        error : function(request, status, error){
                            console.log("ajax 통신 오류");
                            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });

                } else {
                    $("#idDupResult").text("영문, 숫자 6~10자리로 입력해주세요.").css("color", "red");
                    $("#id-check-circle").empty();
                    $("#id-check-circle").append(n);
                    valObj.id = false;
                }
            }
        });


        $("#pwd2").on("keyup", function () {

            if ($("#pwd1").val().length == 0) {
                $("#pwd2").val("");
                swal("비밀번호를 먼저 입력해주세요.");
                $("#pwd1").focus();
            }

        });

        $("#pwd1, #pwd2").on("keyup", function () {

            var regExp = /^[a-zA-Z\d]{8,12}$/;

            if (regExp.test($("#pwd1").val())){
                $("#pwd1-check-circle").empty();
                $("#pwd1-check-circle").append(y);
                $("#pwdResult").text("");

            } else {
                $("#pwd1-check-circle").empty();
                $("#pwd1-check-circle").append(n);
                $("#pwdResult").text("영문, 숫자 8~12자리로 입력해주세요.").css("color", "red");
         }
    


        });



        $("#pwd1, #pwd2").on("keyup", function () {

            if ($("#pwd1").val().length > 0 && $("#pwd2").val().length > 0) {

                if ($("#pwd1").val() == $("#pwd2").val()) {
                    $("#pwd2-check-circle").empty();
                    $("#pwd2-check-circle").append(y);
                    $("#pwdResult").text("");
                    valObj.pwd1 = true;
                } else {
                    $("#pwd2-check-circle").empty();
                    $("#pwd2-check-circle").append(n);
                    $("#pwdResult").text("비밀번호 확인이 일치하지 않습니다.").css("color", "red");
                    valObj.pwd1 = false;
                }
            }

        });

        
        $("#name").on("input", function(){
            var regExp = /^[가-힣]{2,}$/;

            var value = $("#name").val();
            if(regExp.test(value)){
                $("#name-check-circle").empty();
                $("#name-check-circle").append(y);
                $("#nameResult").text("");
                valObj.name = true;
            }else{
                $("#name-check-circle").empty();
                $("#name-check-circle").append(n);
                valObj.name = false;
            }
        });

        $("#email").on("keyup", function () {

            var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

            if ($("#email").val().length == 0) {
                $("#email-check-circle").empty();
            } else {
                if (regExp.test($("#email").val())) {
                    $("#email-check-circle").empty();
                    $("#email-check-circle").append(y);
                    $("#emailResult").text("");
                    valObj.email = true;
                } else {
                    $("#email-check-circle").empty();
                    $("#email-check-circle").append(n);
                    valObj.email = false;
                }
            }
        });

        $("#phone").on("keyup", function () {

            var regExp = /^[0][0-9]{1,2}-[0-9]{3,4}-[0-9]{4}/;


            if ($("#phone").val().length == 0) {
                $("#phone-check-circle").empty();
            } else {
                if (regExp.test($("#phone").val())) {
                    $("#phone-check-circle").empty();
                    $("#phone-check-circle").append(y);
                    $("#phoneResult").text("");
                    valObj.phone = true;
                } else {
                    $("#phone-check-circle").empty();
                    $("#phone-check-circle").append(n);
                    valObj.phone = false;
                }
            }
        });



        function validate() {

            // valObj 객체에 순차 접근
            for (var key in valObj) {
                if (!valObj[key]) {
                    // 객체에 저장된 값을 하나 꺼냈을 때
                    // false인 경우
                    var str = "";
                    switch (key) {
                        case "id": str = "아이디는 영문, 숫자 6~12자리를 입력해주세요."; break;
                        case "name": str = "올바른 이름을 입력해주세요."; break;
                        case "pwd1": str = "비밀번호는 영문, 숫자 8~12자리를 입력해주세요."; break;
                        case "email": str = "올바른 이메일을 입력해주세요."; break;
                        case "checkEmail": str = "이메일 인증에 실패했습니다."; break;
                        case "phone": str = "올바른 전화번호를 입력해주세요."; break;
                    }

                    swal(str);

                    $("#" + key).focus();
                    return false;

                }
            }

        }




// 회원 정보 수정----------------------------------------------------------------------------

// 회원 정보 수정 유효성 검사
function memberUpdateValidate(){


    var regExp1 = /^[0][0-9]{1,2}-[0-9]{3,4}-[0-9]{4}/;
    var regExp2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;

   // 전화번호 유효성 검사
   if(!regExp1.test( $("#phone").val() )){
    swal("올바른 전화번호를 입력해주세요.");   
    $("#phone").focus();
    return false;
}

   // 이메일 유효성 검사
   if(!regExp2.test( $("#email").val() )){
    swal("올바른 이메일을 입력해주세요.");   
    $("#email").focus();
    return false;
}
   
   if ($("#currPwd").val().length < 8) {
    swal("현재 비밀번호를 입력해주세요.");
    $("#currPwd").focus();
    return false;
    }

}


// 비밀번호 수정-----------------------------------------------------
function pwdValidate(){

    var regExp = /^[a-zA-Z\d]{8,12}$/;

    if(!regExp.test( $("#pwd1").val() )){
        swal("비밀번호는 영문, 숫자 12자리를 입력해주세요.");
        $("#pwd1").focus();
        return false;
    }

    // 새로운 비밀번호와 확인이 일치하지 않을 때
    if( $("#pwd1").val() != $("#pwd2").val() ){
        swal("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        $("#pwd2").focus();
        return false;
    }

    if ($("#currPwd").val().length < 8) {
        swal("현재 비밀번호를 입력해주세요.");
        $("#currPwd").focus();
        return false;
    }

}

// 회원 탈퇴 약관 동의 체크 확인-------------------------------
function secessionValidate(){

    if( !$("#agree").prop("checked")){
        // #agree 체크박스가 체크되어 있지 않다면
        swal("약관에 동의해주세요.");
        return false;
    } else{
	
				return confirm("정말로 탈퇴 하시겠습니까?")
				// 취소 선택 시 false가 반환되어 기본이벤트 제거 
		}

}