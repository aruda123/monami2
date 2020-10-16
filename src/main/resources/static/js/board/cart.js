/**
 * 
 */



//장바구니 비었을 때 메시지
$(function(){
	if($(".cartUl").height() == 0){
		$(".nullText").text("장바구니가 비었습니다.");
		$("#chk_all").prop("checked", false);
	}else{
		$("#chk_all").prop("checked", true);
		$(".chk").prop("checked", true);	
	}

	
	//체크박스 전체선택 및 전체해제
	$("#chk_all").click(function(){
		if($("#chk_all").is(":checked")){
			$(".chk").prop("checked", true);
			priceSum();
		}else{
			$(".chk").prop("checked", false);
			$(".totalPrice").text("0");
		}
	});
	
	//체크박스 한개 해제시 전체선택 체크박스 선택해제
	$(".chk").click(function(){
		if($("input[name='chk']:checked").length == $("#cart-wrap .ul-wrap li").length){
			$("#chk_all").prop("checked", true);
		}else{
			$("#chk_all").prop("checked", false);
		}
	});
	
});
	
//checkbox 선택된 가격 계산, 콤마찍기
function priceSum(){
	var total = 0;
	$("input[name='chk']:checked").each(function(){
		total += parseInt($(this).val());
	});
	total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	$(".totalPrice").text(total);
}



function deleteIcon(){
	$.ajax({
		url: "/cart/cartDelete",
		type: "post",
		data: {"no" : $(".hidden_no").val(), "user_id" : $(".user_id").val()},
		success: function(result){
			alert(result);
			location.reload();
		}
	});
}