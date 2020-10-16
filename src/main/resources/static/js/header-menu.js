/**
 * 
 */

var popCheck = true;

$(function(){
	//장바구니 전체 개수 가져오기
	if($(".hidden_id").length){
		$.ajax({
			url: "/cart/cartTotal",
			type: "post",
			data: {"id" : $(".hidden_id").val()},
			success: function(result){
				$(".cartCnt").text(result);
			}
		});
	}
	
	
	//전체메뉴 띄우기
	$(".btn-allmenu").click(function(){
		$(".all-side-menu").animate({left : '0', width : '100%'});
		$(".btn-allmenu-close").animate({left : '-3px', top : '-18px'});
		$(".allmenu-btn").animate({top : '-65px'});
	});
	//전체메뉴 없애기
	$(".btn-allmenu-close").click(function(){
		$(".all-side-menu").animate({left : '-400px', width : '0'}, 300);
		$(".btn-allmenu-close").animate({left : '-400px'});
		$(".allmenu-btn").animate({top : '0'});
	});
	
	//회원div창 띄우기
	$(".btn-member").mouseover(function(){
		$(".popinfo").css("visibility", "visible");
		$(".popinfo").animate({marginTop: '0', opacity: '1'}, 500);
		$(".popinfo").css("opacity", "1");
	});
	//회원div창 없애기
	$(".pop-member-li").mouseleave(function(){
		$(".popinfo").animate({marginTop: '20px', opacity: '0'}, 300);
		$(".popinfo").css("opacity", "0");
	});
	
	//상단사이드메뉴 띄우기
	TopMenuSlideDown($(".header_menu_a1").parent(), $(".header_menu_small1"), $(".header_menu_a1"), $(".stationery"));
	TopMenuSlideUp($(".header_menu_a1").parent(), $(".header_menu_small1"), $(".header_menu_a1") ,$(".stationery"));
	
	TopMenuSlideDown($(".header_menu_a2").parent(), $(".header_menu_small2"), $(".header_menu_a2"), $(".monamipet"));
	TopMenuSlideUp($(".header_menu_a2").parent(), $(".header_menu_small2"), $(".header_menu_a2") ,$(".monamipet"));
	
	TopMenuSlideDown($(".header_menu_a3").parent(), $(".header_menu_small3"), $(".header_menu_a3"), $(".hobby"));
	TopMenuSlideUp($(".header_menu_a3").parent(), $(".header_menu_small3"), $(".header_menu_a3") ,$(".hobby"));
	
	TopMenuSlideDown($(".header_menu_a4").parent(), $(".header_menu_small4"), $(".header_menu_a4"), $(".monarte"));
	TopMenuSlideUp($(".header_menu_a4").parent(), $(".header_menu_small4"), $(".header_menu_a4") ,$(".monarte"));
	
	TopMenuSlideDown($(".header_menu_a5").parent(), $(".header_menu_small5"), $(".header_menu_a5"), $(".storypick"));
	TopMenuSlideUp($(".header_menu_a5").parent(), $(".header_menu_small5"), $(".header_menu_a5") ,$(".storypick"));
	
	TopMenuSlideDown($(".header_menu_a6").parent(), $(".header_menu_small6"), $(".header_menu_a6"), $(".event"));
	TopMenuSlideUp($(".header_menu_a6").parent(), $(".header_menu_small6"), $(".header_menu_a6") ,$(".event"));
	
	
});

//상단메뉴 띄우기
function TopMenuSlideDown(aTagClass, smallClass, aClass, liClass){
	$(aTagClass).mouseover(function(){
		$(smallClass).css("opacity", "1");
		$(aClass).css("borderBottom", "3px solid #ff3c00");
		$(liClass).children(".subnav").slideDown();
	});
}
//상단메뉴 없애기
function TopMenuSlideUp(aTagClass, smallClass, aClass, liClass){
	$(aTagClass).mouseleave(function(){
		$(smallClass).css("opacity", "0");
		$(aClass).css("borderBottom", "none");
		$(liClass).children(".subnav").slideUp();
	});
}




//장바구니 로그인 체크
function cartList(){
	if($(".hidden_id").next().length == 0){
		if(confirm("장바구니는 로그인 후 이용이 가능합니다.")){
			location.href="/member/login";
		}
	}else{
		location.href="/cart/cartList?user_id="+$(".hidden_id").val();
	}
}


