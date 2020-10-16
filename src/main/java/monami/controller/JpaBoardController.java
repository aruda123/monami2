package monami.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import monami.domain.dto.BoardDTO;
import monami.domain.dto.CommentDTO;
import monami.service.BoardService;
import monami.service.comment.CommentService;

@Controller
@RequiredArgsConstructor
public class JpaBoardController {
	
	private final BoardService service;
	private final CommentService comment;
	
	//Stationery게시판 DB데이터 가지고 이동
	@GetMapping("/board/stationery/{page}")
	public ModelAndView stationery(@PathVariable("page")int page) {
		// 전체 게시글 수
		int rowTotal = (int) service.boardTotal();
		
		// 게시글 목록
		ModelAndView mv = service.list(page);
		mv.addObject("rowTotal", rowTotal);
		mv.setViewName("/board/stationery");
		return mv;
	}
	
	//게시글 등록 처리
	@PostMapping("/board/write")
	public String write(@RequestParam("file")MultipartFile mf, BoardDTO dto) throws IllegalStateException, IOException {
		File dir = new File("/home/aruda123456/src/board/WEB-INF/classes/static/images/board/stationery");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String fileName = mf.getOriginalFilename();
		File file = new File(dir, fileName);
		mf.transferTo(file);
		dto.setUrl(fileName);
		
		service.fileUpload(dto);
		
		return "/board/stationery_write.html";
	}
	
	//게시글 상세보기 DB 처리 후 이동
	@GetMapping("/board/stationery_detail/{no}")
	public String detail(@PathVariable Long no, Model model) {
		BoardDTO result = service.detail(no);
		model.addAttribute("dto", result);
		
		//댓글 가져오기
		comment_list(no, model);
		
		return "/board/stationery_detail";
	}
	
	//게시글 수정
	@PostMapping("/board/stationery-update")
	public String update(@RequestParam("file")MultipartFile mf, BoardDTO dto) throws IllegalStateException, IOException {
		String path = "/home/aruda123456/src/board/WEB-INF/classes/static/images/board/stationery";
		if(!mf.isEmpty()) {
			//dto에 파일이름 저장
			String fileName = mf.getOriginalFilename();
			dto.setUrl(fileName);
			//업로드
			File file = new File(path, fileName);
			mf.transferTo(file);
		}
		
		service.update(dto);
		return "redirect:/board/stationery_detail/"+dto.getNo();
	}
	
	//게시글 삭제
	@GetMapping("/board/stationery-delete/{no}")
	public String delete(@PathVariable Long no) {
		service.delete(no);
		return "redirect:/board/stationery/1";
	}
	
	//댓글 등록 처리
	@PostMapping("/board/comment")
	@ResponseBody
	public String comment_write(CommentDTO dto, HttpServletRequest request) throws IOException {
		dto.setUser_ip(request.getRemoteAddr());
		comment.commet_write(dto);
		return "댓글이 등록되었습니다.";
		
	}
	
	//댓글 목록 가져오기
	@GetMapping("/board/commentList")
	public void comment_list(@Param("board_no")Long board_no, Model model) {
		List<CommentDTO> list = comment.comment(board_no);
		model.addAttribute("comment", list);
	}
	
	//댓글 삭제 처리
	@GetMapping("/board/commentDelete")
	@ResponseBody
	public String commentDelete(@Param("no") long no) throws IOException {
		comment.delete(no);
		return "삭제되었습니다.";
	}
	
}
