/**
 * 
 */

var pwd_check1 = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,16}$/;	
var pwd_check2 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^?_~])[a-zA-Z!@#$%^?_~]{6,16}$ /;
var pwd_check3 = /^(?=.*[0-9])(?=.*[!@#$%^?_~])[0-9!@#$%^?_~]{6,16}$/;
var pwd_check4 = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^?_~])[a-zA-Z0-9!@#$%^?_~]{6,16}$/;
var email_check = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;


function mypagechange(){
	var pwd = $(".reg_pwd");
	var email = $(".reg_email");
	
	//pwd 빈값, 유효성 체크
	if(pwd.val() == ""){
		pwd.next('.alterror').css("display", "block");
		pwd.focus();
		return false;
	}else if(!pwd_check1.test(pwd.val()) && !pwd_check2.test(pwd.val()) && !pwd_check3.test(pwd.val()) && !pwd_check4.test(pwd.val())){
		pwd.next('.alterror').css("display", "block");
		pwd.next('.alterror').text("영문/숫자/특수문자 중 2가지 조합, 6~16자 이내");
		pwd.focus();
		return false;
	}else{
		pwd.next('.alterror').css("display", "none");
	}
	//비밀번호 확인 체크
	if(pwd.val() != $(".reg_pwd_check").val()){
		alert("입력한 비밀번호가 서로 일치하지 않습니다.");
		$(".reg_pwd_check").next('.alterror').css("display", "block");
		$(".reg_pwd_check").focus();
		return false;
	}else{
		$(".reg_pwd_check").next('.alterror').css("display", "none");
	}
	
	//email 빈값, 유효성 체크
	if(!email_check.test(email.val())){
		email.next('.alterror').css("display", "block");
		email.next('.alterror').text("아이디는 5~16자, 영문, 숫자 또는 영문+숫자 조합으로 입력해 주세요.");
		email.focus();
		return false;
	}else{
		email.next('.alterror').css("display", "none");
	}
	
	//이메일 변경 시 다시 중복체크 버튼 누르게 처리
	if(email.val() != $(".hidden_email").val()){
		alert("이메일 중복 확인을 해주세요.");
		email.next('.alterror').css("display", "block");
		email.next('.alterror').text("이메일 중복 확인을 해주세요.");
		email.focus();
		return false;
	}
	
}


//이메일 중복체크버튼 처리
function email_check_btn(){
	if($(".reg_email").val() == ""){
		$(".reg_email").next('.alterror').css("display", "block");
		$(".reg_email").focus();
	}else if(!email_check.test($(".reg_email").val())){
		$(".reg_email").next('.alterror').css("display", "block");
		$(".reg_email").next('.alterror').text("이메일 주소를 확인해 주세요.");
		$(".reg_email").focus();
	}else{
		$.ajax({
			url:"/member/reg_email_check",
			type:"post",
			data:{"email" : $(".reg_email").val()},
			success: function(result){
				if(result == "true"){
					alert("이미 사용중인 이메일입니다.");
				}else{
					alert("사용 가능한 이메일입니다.");
					$(".hidden_email").val($(".reg_email").val());
					$(".reg_email").next('.alterror').css("display", "none");
				}
			}
		});
	}
}


//취소버튼
function mypage_cancel_btn(){
	location.href="/";
}