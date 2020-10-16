/**
 * 
 */

$(function(){
	commentList();
	//$("#reply .deleteBtn").click(deleteBtn);
});


function cancelBtn(){
	$(".detail-div").show();
	$("#comment").show();
	$(".comment-write").show();
	$(".update-div").hide();
}
function updateBtn(){
	$(".detail-div").hide();
	$("#comment").hide();
	$(".comment-write").hide();
	$(".update-div").show();
}
function deleteBtn(){
	return confirm("삭제하시겠습니까?");
}
function updateSubmit(){
	return confirm("수정하시겠습니까?");
}


//댓글 목록 가져오기
function commentList(){
	var board_no = $(".comment_boardNo").val();
	$.ajax({
		url: "/board/commentList",
		data: {"board_no" : board_no},
		success: function(result){
			$("#comment").html(result);
		}
	});
}

//댓글 등록 처리
function commentBtn(){
	var board_no = $(".comment_boardNo").val();
	var content = $(".comment_content").val();
	var id = $(".comment_id").val();
	var href = location.href;
	//댓글 등록 시 로그인 체크
	if($(".login_check").val() == "null"){
		if(confirm("로그인 후 이용이 가능합니다.")){
			location.href="/member/login?href="+href;
		}
	}else if(content == ""){
		alert("댓글을 입력하지 않았습니다.");
	}else{
		if(confirm("댓글을 등록하시겠습니까?")){
			$.ajax({
				url: "/board/comment",
				type: "post",
				data: {"board_no" : board_no, "content" : content, "id" : id},
				success: function(result){
					commentList();
					alert(result);
					$(".comment_content").val("");
				}
			});
		}
	}
}





