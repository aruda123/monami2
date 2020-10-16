package monami.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import monami.domain.dto.MybatisBoardDTO;
import monami.service.MybatisBoardService;

@Controller
public class MybatisBoardController {
	
	@Autowired
	private MybatisBoardService service;
	
	//monamipet 게시판 페이지 이동
	@GetMapping("/board/monamipet/{page}")
	public ModelAndView list(@PathVariable int page) {
		ModelAndView mv = service.list(page);
		mv.setViewName("/board/monamipet");
		return mv;
	}
	
	//게시글 등록 페이지 이동
	@GetMapping("/monamipet/write")
	public String write() {
		return "/board/monamipet_write";
	}
	
	//게시글 등록 처리 - 파일업로드
	@PostMapping("/monamipet/write")
	public String write(@RequestParam("file")MultipartFile mf, MybatisBoardDTO dto) throws IllegalStateException, IOException {
		File dir = new File("/home/aruda123456/src/board/WEB-INF/classes/static/images/board/monamipet"); 
							
		if(dir.exists()) {
			dir.mkdirs();
		}
		
		String fileName = mf.getOriginalFilename();
		File file = new File(dir, fileName);
		mf.transferTo(file);
		dto.setUrl(fileName);
		
		service.fileUpload(dto);
		
		return "redirect:/board/monamipet";
	}
	
	//상세보기 페이지 DB처리 후 이동
	@GetMapping("/board/monamipet-detail/{no}")
	public String detail(Model model, @PathVariable Long no) {
		MybatisBoardDTO result = service.detail(no);
		model.addAttribute("dto", result);
		return "/board/monamipet_detail";
	}
	
	//게시글 수정
	@PostMapping("/board/monamipet-update")
	public String update(@RequestParam("file")MultipartFile mf, MybatisBoardDTO dto) throws IllegalStateException, IOException {
		String path = "/home/aruda123456/src/board/WEB-INF/classes/static/images/board/monamipet";
		if(!mf.isEmpty()) {
			String fileName = mf.getOriginalFilename();
			dto.setUrl(fileName);
			File file = new File(path, fileName);
			mf.transferTo(file);
		}
		service.update(dto);
		return "redirect:/board/monamipet-detail/"+dto.getNo();
	}
	
	//게시글 삭제
	@GetMapping("/board/monamipet-delete/{no}")
	public String delete(@PathVariable Long no) {
		service.delete(no);
		return "redirect:/board/monamipet";
	}

}
