package monami.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	//메인페이지 이동
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	//로그인 페이지 이동
	@GetMapping("/member/login")
	public String login(HttpServletRequest request, Model model) {
		String uri = request.getHeader("Referer");
		model.addAttribute("uri", uri);
		return "/member/login";
	}
	
	//회원가입 페이지1 이동
	@GetMapping("/member/reg-page1")
	public String reg_page1(){
		return "/member/reg-page1";
	}
	//회원가입 페이지2 이동
	@GetMapping("/member/reg-page2")
	public String reg_page2(){
		return "/member/reg-page2";
	}
	//회원가입 페이지2 이동
	@GetMapping("/member/regForm")
	public String reg_page3(){
		return "/member/regForm";
	}
	
	//게시판 등록 페이지 이동
	@GetMapping("/board/write")
	public String write() {
		return "/board/stationery_write";
	}
	
	//MyPage 비밀번호 확인 페이지 이동
	@GetMapping("/member/mypagePwdCheck")
	public String mypagePwdCheck() {
		return "/member/mypagePwdCheck";
	}
	
	//MyPage 이동
	@GetMapping("/member/mypage")
	public String mypage() {
		return "/member/mypage";
	}
	
}
