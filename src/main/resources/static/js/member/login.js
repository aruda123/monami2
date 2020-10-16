/**
 * 
 */
var loginCheck = false;
$(function(){
	
});



function login_form(){
	if($(".login_id").val().trim() == "" || $(".login_pwd").val().trim() == ""){
		if($(".login_id").val().trim() == ""){
			$(".loging-error_id").css("display", "block");
			$(".loging-error_pwd").css("display", "none");
			return false;
		}else{
			$(".loging-error_pwd").css("display", "block");
			$(".loging-error_id").css("display", "none");
			return false;
		}
	}else{
		var id = $(".login_id").val();
		var pwd = $(".login_pwd").val();
		$(".loging-error_id").css("display", "none");
		$(".loging-error_pwd").css("display", "none");
		$.ajax({
			url:"/member/ajax-login",
			type:"post",
			data:{"id" : id , "pwd" : pwd},
			success: function(result){
				if(result == "false"){
					alert("존재하지 않는 아이디이거나 \n아이디 또는 비밀번호를 잘못 입력하셨습니다.");
					location.href="/member/login";
				}
			}
		});
	}
}
