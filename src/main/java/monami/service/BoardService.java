package monami.service;

import org.springframework.web.servlet.ModelAndView;

import monami.domain.dto.BoardDTO;

public interface BoardService {

	long boardTotal();
	
	ModelAndView list(int page);
	
	void fileUpload(BoardDTO dto);
	
	BoardDTO detail(Long no);

	void update(BoardDTO dto);

	void delete(Long no);


}
