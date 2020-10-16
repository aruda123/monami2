/**
 * NAME    : 이미지 슬라이드 JQuery
 * AUTHOR  : 조재청
 * CONTACT : nick2615@naver.com
 */

var slideImg;
var winWidth;
var speed=1000;//애니메이션 시간
var delay=5000;//슬라이드 지연시간
var imgea;
var click=true;
$(function(){
	winWidth=$(window).width();
	imgea=$("#visual-area ul li").length;
	//페이지 로딩시 화면이미지랑...li의 사이즈 일치..
	$("#visual-area ul li").css("width", winWidth);
	$("#visual-area ul").css("width", winWidth*imgea);
	
	//이미지슬라이드 시작
	slideImg=setTimeout(start, delay);
	bgChange();
	
	//화면사이즈가 변경될때마다 li의 사이즈도 화면사이즈로 재조정
	$(window).resize(function(){
		winWidth=$(window).width();
		$("#visual-area ul li").css("width", winWidth);
		$("#visual-area ul").css("width", winWidth*imgea);
	});
	
	//visual 이미지에 마우스가 위로 올라오면 슬라이드 스톱, 나가면 시작
	$("#visual").hover(stop,function(){
		slideImg=setTimeout(start, delay);
	});
	
	//현재 윈도우가 선택되지 않으면 슬라이드 스톱
	/*$(window).blur(function(){
		stop();
	});*/
	
	
	//오른쪽 화살표 눌러졌을때
	$("#next").click(moveRight);
	$("#prev").click(moveLeft);
	
	//var x=$(".bg5").position();
	//alert("top: "+x.top+" left: "+x.left);
});



function start(){
	/*slideImg = setInterval(moveRight, 3000);*/
	
	moveRight();
	slideImg=setTimeout(start, delay);

}
function stop(){
	/*clearInterval(slideImg);*/
	clearTimeout(slideImg);
}
///이미지가 왼쪽에서 오른쪽으로 이동
function moveLeft(){
	if(click){//이중클릭방지
		click=!click;
		winWidth=$(window).width();
		
		var first=$("#visual-area ul li:first-child");
		var last=$("#visual-area ul li:last-child");
		$(last).insertBefore(first);
		$("#visual-area ul").css("left", -winWidth);
		$("#visual-area ul").animate({left: 0 },speed,function(){
			bgChange();
			click=true;
		});
		
	}
}
///이미지가 오른쪽에서 왼쪽으로 이동
function moveRight(){
	if(click){//이중클릭방지
		click=!click;
		winWidth=$(window).width();
		
		var first=$("#visual-area ul li:first-child");
		var last=$("#visual-area ul li:last-child");
		$("#visual-area ul").animate({left: -winWidth },speed,function(){
			$(first).insertAfter(last);
			$("#visual-area ul").css("left", 0);
			bgChange();
			click=true;
		});
	}
}
//리스트버튼 클릭시 이미지 슬라이드
function listClick(className){
	if(click){//이중클릭방지
		click=!click;
		var x=$(className).position();
		var idx=$(className).index();
		
		$("#visual-area ul").animate({left: -x.left },speed,function(){
			var i;
			for(i=0; i < idx ; i++){
				var first=$("#visual-area ul li:first-child");
				var last=$("#visual-area ul li:last-child");
				$(first).insertAfter(last);
			}
			$("#visual-area ul").css("left", 0);
			bgChange();
			click=true;
		});
	}
	
}

//이미지버튼 색변경
function bgChange(){
	$("#list-wrap a").removeClass("selected");
	var firstIdx=$("#visual-area ul li:first-child").val();
	var target=$("#list-wrap a:nth-of-type("+firstIdx+")");
	target.addClass("selected");
}
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
//스크롤 맨위로 올리기
$(window).scroll(function(){
	if($(this).scrollTop() > 200){
		$(".page_top").fadeIn();
	}else{
		$(".page_top").fadeOut();
	}
});

$(function(){
	$(".page_top").click(function(){
		$('html, body').animate({scrollTop : 0}, 400);
		return false;
	});
});








