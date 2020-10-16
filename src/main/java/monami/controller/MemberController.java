package monami.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import monami.domain.dto.MailDTO;
import monami.domain.dto.MemberLoginDTO;
import monami.domain.entity.Member;
import monami.service.MemberService;
import monami.service.SendEmailService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private SendEmailService sendEmail;
	
	//로그인 처리
	@PostMapping("/member/memberLogin")
	public String login(@Param("uri")String uri, MemberLoginDTO member, HttpSession session) throws Exception {
		String[] uris = uri.split("/");
		String url = uris[uris.length-2];
		String path = "redirect:/";
		
		if(url.equals("stationery_detail")) {
			path = "redirect:"+uri;
		}
		
		MemberLoginDTO dto = memberService.login(member);
		session.setAttribute("login", dto);
		return path;
	}
	
	//로그인 ajax
	@PostMapping("/member/ajax-login")
	public void ajaxLogin(MemberLoginDTO member, HttpServletResponse response) throws Exception {
		System.out.println(member);
		MemberLoginDTO dto = memberService.login(member);
		System.out.println(dto);
		PrintWriter out = response.getWriter();
		String loginCheck = "true";
		if(dto == null) {
			loginCheck = "false";
		}
		out.print(loginCheck);
		out.flush();
	}
	
	//로그아웃 처리
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/";
	}
	
	//아이디 중복체크버튼 처리
	@PostMapping("/member/reg_id_check")
	public void reg_id_check(@Param("id")String id, HttpServletResponse response) throws Exception {
		Member res = memberService.reg_id_check(id);
		PrintWriter out = response.getWriter();
		String result = "true";
		if(res == null) {
			result = "false";
		}
		out.print(result);
		out.flush();
	}
	
	//이메일 중복체크버튼 처리
	@PostMapping("/member/reg_email_check")
	public void reg_email_check(@Param("email")String email, HttpServletResponse response) throws Exception {
		Member res = memberService.reg_email_check(email);
		PrintWriter out = response.getWriter();
		String result = "true";
		if(res == null) {
			result = "false";
		}
		out.print(result);
		out.flush();
	}
	
	//회원가입 처리
	@PostMapping("/member/memberReg")
	public String memberReg(MemberLoginDTO dto, HttpSession session) {
		memberService.memberReg(dto);
		session.setAttribute("login", dto);
		return "redirect:/";
	}
	
	//아이디 비밀번호 찾기 페이지 이동
	@GetMapping("/member/findIdPwd/{find}")
	public String findIdPwd(@PathVariable String find) {
		return "/member/findIdPwd";
	}
	
	//아이디 찾기 ajax처리
	@PostMapping("/member/findIdAjax")
	public String findIdAjax(@Param("name")String name, @Param("email")String email, Model model, HttpServletResponse response) throws IOException {
		MemberLoginDTO dto = memberService.findIdPwd(name, email);
		if(dto == null) {
			PrintWriter out = response.getWriter();
			out.print("false");
			out.flush();
			return null;
		}
		model.addAttribute("findId", dto);
		return "/member/resultIdPwd";
		
	}
	
	//비밀번호 찾기 ajax, 이메일 인증 처리
	@PostMapping("/member/findPwdAjax")
	public String findPwdAjax(@Param("id")String id, @Param("name")String name, @Param("email")String email, Model model, HttpServletResponse response) throws IOException {
		MemberLoginDTO dto = memberService.findEmail(id, name, email);
		if(dto == null) {
			PrintWriter out = response.getWriter();
			out.print("false");
			out.flush();
			return null;
		}
		model.addAttribute("findPwd", dto);
		
		//이메일 인증 
		MailDTO mailDto = sendEmail.createMailAndChangePassword(dto.getEmail(), dto.getName());
		System.out.println(">>>>mailDto : " + mailDto);
		sendEmail.mailSend(mailDto);
		
		return "/member/resultIdPwd";
	}
	
	//마이페이지 들어가기전 비밀번호 확인 ajax
	@PostMapping("/member/myPagePwdCheck")
	@ResponseBody
	public MemberLoginDTO myPagePwdCheck(@Param("id")String id, @Param("pwd")String pwd) {
		MemberLoginDTO dto = memberService.myPagePwdCheck(id, pwd);
		return dto;
	}
	
	//마이페이지 정보수정 처리
	@PostMapping("/member/mypageChange")
	public String mypageChange(MemberLoginDTO dto, HttpServletResponse response, HttpSession session) throws IOException {
		memberService.mypageChange(dto);
		session.removeAttribute("login");
		session.setAttribute("login", dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('정보가 수정되었습니다.'); location.href='/';</script>");
		out.flush();
		return "/member/mypage";
	}
	

}
