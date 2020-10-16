/**
 * 
 */
var name_check = /^[가-힣a-zA-Z]+$/; 
var id_check = /^[a-z0-9]{5,16}$/; 
var pwd_check1 = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,16}$/;	
var pwd_check2 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^?_~])[a-zA-Z!@#$%^?_~]{6,16}$ /;
var pwd_check3 = /^(?=.*[0-9])(?=.*[!@#$%^?_~])[0-9!@#$%^?_~]{6,16}$/;
var pwd_check4 = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^?_~])[a-zA-Z0-9!@#$%^?_~]{6,16}$/;
var home_number_check = /^\d{2,3}-\d{3,4}-\d{4}$/; /*일반전화 유효성*/
var phone_check = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
var email_check = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

$(function(){
	//체크박스 전체선택 및 전체해제
	$("#chk_all").click(function(){
		if($("#chk_all").is(":checked")){
			$(".chk").prop("checked", true);
		}else{
			$(".chk").prop("checked", false);
		}
	});
	
	//체크박스 한개 해제시 전체선택 체크박스 선택해제
	$(".chk").click(function(){
		if($("input[name='chk']:checked").length == 3){
			$("#chk_all").prop("checked", true);
		}else{
			$("#chk_all").prop("checked", false);
		}
	});
	
	
});


//페이지2 취소버튼
function reg_cancel_btn(){
	location.href="/member/reg-page1";
}

//페이지2 확인버튼
function reg_btn(){
	if(!$(".chk1").is(":checked")){
		alert("서비스 이용약관에 동의해주세요.");	
	}else if(!$(".chk2").is(":checked")){
		alert("개인정보수집/이용동의에 동의해주세요.");	
	}else if(!$(".chk3").is(":checked")){
		alert("개인정보취급/위탁동의에 동의해주세요.");	
	}else{
		location.href="/member/regForm";
	}
}

//회원가입 페이지 취소버튼
function regForm_cancel_btn(){
	if(confirm("입력된 회원정보가 초기화됩니다. 이동하시겠습니까?") == true){
		location.href="/member/reg-page2";		
	}
}

//회원가입 페이지 확인버튼
function reg_form(){
	var name = $(".reg_name");
	var id = $(".reg_id");
	var pwd = $(".reg_pwd");
	var email = $(".reg_email");
	var phone = $(".reg_phone");
	var birth = $(".reg_birth");
	
	//아이디 이메일 변경 시 다시 중복체크 버튼 누르게 처리
	if(id.val() != $(".hidden_id").val()){
		alert("아이디 중복 확인을 해주세요.");
		id.next('.alterror').css("display", "block");
		id.next('.alterror').text("아이디 중복 확인을 해주세요.");
		id.focus();
		return false;
	}
	if(email.val() != $(".hidden_email").val()){
		alert("이메일 중복 확인을 해주세요.");
		email.next('.alterror').css("display", "block");
		email.next('.alterror').text("이메일 중복 확인을 해주세요.");
		email.focus();
		return false;
	}
	
	//name 빈값, 유효성 체크
	if(name.val() == ""){
		name.next('.alterror').css("display", "block");
		name.focus();
		return false;
	}else if(!name_check.test(name.val())){
		name.next('.alterror').css("display", "block");
		name.next('.alterror').text("이름은 영문,한글만 사용할 수 있습니다.");
		name.focus();
		return false;
	}else{
		name.next('.alterror').css("display", "none");
	}
	
	//id 빈값, 유효성 체크
	if(id.val() == ""){
		id.next('.alterror').css("display", "block");
		id.focus();
		return false;
	}else if(!id_check.test(id.val())){
		id.next('.alterror').css("display", "block");
		id.next('.alterror').text("아이디는 5~16자, 영문, 숫자 또는 영문+숫자 조합으로 입력해 주세요.");
		id.focus();
		return false;
	}else{
		id.next('.alterror').css("display", "none");
	}
	
	//pwd 빈값, 유효성 체크
	if(pwd.val() == ""){
		pwd.next('.alterror').css("display", "block");
		pwd.focus();
		return false;
	}else if(!pwd_check1.test(pwd.val()) && !pwd_check2.test(pwd.val()) && !pwd_check3.test(pwd.val()) && !pwd_check4.test(pwd.val())){
		pwd.next('.alterror').css("display", "block");
		pwd.next('.alterror').text("입력한 비밀번호를 확인해주세요.");
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
	if(email.val() == ""){
		email.next('.alterror').css("display", "block");
		email.focus();
		return false;
	}else if(!email_check.test(email.val())){
		email.next('.alterror').css("display", "block");
		email.next('.alterror').text("아이디는 5~16자, 영문, 숫자 또는 영문+숫자 조합으로 입력해 주세요.");
		email.focus();
		return false;
	}else{
		email.next('.alterror').css("display", "none");
	}
	
	//전화번호 빈값, 유효성 체크
	if(phone.val() == ""){
		phone.next('.alterror').css("display", "block");
		phone.focus();
		return false;
	}else if(!phone_check.test(phone.val())){
		phone.next('.alterror').css("display", "block");
		phone.next('.alterror').text("핸드폰 번호를 확인해 주세요.");
		phone.focus();
		return false;
	}else{
		phone.next('.alterror').css("display", "none");
	}
	
	//14세 이상 체크박스 체크
	if(!$(".small").is(":checked")){
		alert("만 14세 이상 체크는 필수 입니다.");	
		return false;
	}
	
	
	return true;
}


//아이디 중복체크버튼 처리
function id_check_btn(){
	if($(".reg_id").val() == ""){
		$(".reg_id").next('.alterror').css("display", "block");
		$(".reg_id").focus();
	}else if(!id_check.test($(".reg_id").val())){
		$(".reg_id").next('.alterror').css("display", "block");
		$(".reg_id").next('.alterror').text("아이디는 5~16자, 영문, 숫자 또는 영문+숫자 조합으로 입력해 주세요.");
		$(".reg_id").focus();
	}else{
		$.ajax({
			url:"/member/reg_id_check",
			type:"post",
			data:{"id" : $(".reg_id").val()},
			success: function(result){
				if(result == "true"){
					alert("이미 사용중인 아이디입니다.");
				}else{
					alert("사용 가능한 아이디입니다.");
					$(".hidden_id").val($(".reg_id").val());
					$(".reg_id").next('.alterror').css("display", "none");
				}
			}
		});
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






