/**
 * 
 */
var locationUrl = location.href;
locationUrl = locationUrl.split("/");
url = locationUrl[locationUrl.length-1];
$(function(){
	$("#memberName1, #memberEmail1").keydown(function(key){
		if(key.keyCode == 13){
			findIdCheck();
		}
		
	});
	
	$("#memberId, #memberName2, #memberEmail2").keydown(function(key){
		if(key.keyCode == 13){
			findPwdCheck();
		}
	});
	
	
	$(".login_name").focusout(function(){
		if($(".login_name").val() != ""){
			$(".loging-error_name").css("display", "none");
		}
	});
	$(".login_email").focusout(function(){
		if($(".login_email").val() != ""){
			$(".loging-error_email").css("display", "none");
		}
	});
	
	
});


//처음 페이지 들어왔을 때만 url판단
window.onload = function(){
	if(url == "findPwd"){
		$("#findId").hide();
		$("#findPwd").show();
		$("#findIdTab").removeClass("active");
		$("#findPwdTab").addClass("active");
	}
}



//아이디 찾기 버튼
function idSearch(){
	if($(".findDate").text() != ""){
		$("#resultId").fadeIn();
		$("#findId").hide();
	}else{
		$("#findId").fadeIn();
	}
	$("#findPwd").hide();
	$("#resultPwd").hide();
	$("#findIdTab").addClass("active");
	$("#findPwdTab").removeClass("active");
	urlCheck = false;
}

//비밀번호 찾기 버튼
function pwdSearch(){
	if($(".findEmail").text() != ""){
		$("#resultPwd").fadeIn();
		$("findPwd").hide();
	}else{
		$("#findPwd").fadeIn();
	}
	$("#resultId").hide();
	$("#findId").hide();
	$("#findIdTab").removeClass("active");
	$("#findPwdTab").addClass("active");
}

//아이디 찾기 처리
function findIdCheck(){
	var name = $(".findId_name").val();
	var email = $(".findId_email").val();
	
	if(name == ""){
		$(".loging-error_name").css("display", "block");
		$(".findId_name").focus();
		return false;
	}else{
		$(".loging-error_name").css("display", "none");
	}
	
	
	if(email == ""){
		$(".loging-error_email").css("display", "block");
		$(".findId_email").focus();
		return false;
	}else{
		$(".loging-error_email").css("display", "none");
	}
	
	
	$.ajax({
		url: "/member/findIdAjax",
		data: {"name" : name, "email" : email},
		type: "post",
		success: function(result){
			if(result == "false"){
				alert("가입되지 않은 회원입니다.");
			}else{
				$("#findId").hide();
				$("#resultId").show();
				$("#resultId").html(result);	
			}
		}
	});
	
}


//비밀번호 찾기 처리
function findPwdCheck(){
	var id = $(".findPwd_id").val();
	var name = $(".findPwd_name").val();
	var email = $(".findPwd_email").val();
	
	if(id == ""){
		$(".loging-error_id").css("display", "block");
		$(".findPwd_id").focus();
		return false;
	}else{
		$(".loging-error_id").css("display", "none");
	}
	
	if(name == ""){
		$(".loging-error_name").css("display", "block");
		$(".findPwd_name").focus();
		return false;
	}else{
		$(".loging-error_name").css("display", "none");
	}
	
	
	if(email == ""){
		$(".loging-error_email").css("display", "block");
		$(".findPwd_email").focus();
		return false;
	}else{
		$(".loging-error_email").css("display", "none");
	}
	
	
	$.ajax({
		url: "/member/findPwdAjax",
		data: {"id" : id, "name" : name, "email" : email},
		type: "post",
		success: function(result){
			if(result == "false"){
				alert("가입되지 않은 회원입니다.");
			}else{
				$("#findPwd").hide();
				$("#resultPwd").show();
				$("#resultPwd").html(result);	
			}
		}
	});
	
}


